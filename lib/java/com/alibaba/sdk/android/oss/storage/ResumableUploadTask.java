package com.alibaba.sdk.android.oss.storage;

import com.alibaba.sdk.android.dpa.util.HttpdnsMini;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.callback.GenericProgressHandler;
import com.alibaba.sdk.android.oss.callback.SaveCallback;
import com.alibaba.sdk.android.oss.model.ClientConfiguration;
import com.alibaba.sdk.android.oss.model.MeasuableInputStream;
import com.alibaba.sdk.android.oss.model.OSSException;
import com.alibaba.sdk.android.oss.model.OSSException.ExceptionType;
import com.alibaba.sdk.android.oss.model.ResourceToQuery;
import com.alibaba.sdk.android.oss.model.ResumableTaskOption;
import com.alibaba.sdk.android.oss.model.SharedComponent;
import com.alibaba.sdk.android.oss.util.OSSLog;
import com.alibaba.sdk.android.oss.util.OSSToolKit;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;

public class ResumableUploadTask
  implements Runnable
{
  private static final int BLOCK_BYTES = 40;
  private static final int HEADER_BYTES = 48;
  private static final int LONG_BYTES = 8;
  private static final int UPLOAD_ID_BYTES = 40;
  private int blockNum;
  private int blockSize;
  private byte[][] buffer;
  private HttpClient client;
  private AtomicInteger completedBlockNum = new AtomicInteger(0);
  private AtomicLong currentUpload = new AtomicLong(0L);
  private Exception exception;
  private File fileToUpload;
  private AtomicBoolean isCancel;
  private AtomicBoolean isFailure = new AtomicBoolean(false);
  private long lastModifiedTime;
  private int maxRetryTime = 2;
  private OSSFile ossFile;
  private Queue<Integer> partsToUpload = new LinkedBlockingDeque();
  private AtomicInteger[] progressInEachThread;
  private RandomAccessFile rafTorecordFile;
  private String recordFilePath;
  private Queue<BlockInfo> recordQueue = new LinkedBlockingDeque();
  private SaveCallback saveCallback;
  private String tag = "Upload-";
  private int threadNum = 3;
  private long totalSize;
  private String uploadId;

  public ResumableUploadTask(OSSFile paramOSSFile, SaveCallback paramSaveCallback)
  {
    this(paramOSSFile, null, paramSaveCallback);
  }

  public ResumableUploadTask(OSSFile paramOSSFile, ResumableTaskOption paramResumableTaskOption, SaveCallback paramSaveCallback)
  {
    this.ossFile = paramOSSFile;
    this.isCancel = paramOSSFile.getCancelFlag();
    this.fileToUpload = new File(paramOSSFile.getUploadFilePath());
    this.lastModifiedTime = this.fileToUpload.lastModified();
    this.client = SharedComponent.getSharedClient();
    this.totalSize = this.fileToUpload.length();
    String str2 = OSSClient.getDataDir().getAbsolutePath();
    String str1 = str2;
    if (paramResumableTaskOption != null)
    {
      if ((paramResumableTaskOption.getThreadNum() > 0) && (paramResumableTaskOption.getThreadNum() <= 5))
        this.threadNum = paramResumableTaskOption.getThreadNum();
      if ((paramResumableTaskOption.getAutoRetryTime() > 0) && (paramResumableTaskOption.getAutoRetryTime() <= 3))
        this.maxRetryTime = paramResumableTaskOption.getAutoRetryTime();
      str1 = str2;
      if (paramResumableTaskOption.getRecordFileDirectory() != null)
        str1 = paramResumableTaskOption.getRecordFileDirectory();
    }
    paramResumableTaskOption = null;
    try
    {
      paramOSSFile = OSSToolKit.calMd5sumString((this.tag + paramOSSFile.getBucketName() + "/" + paramOSSFile.getObjectKey() + "-" + this.fileToUpload.getAbsolutePath()).getBytes());
      paramResumableTaskOption = paramOSSFile;
      OSSLog.logD("[resumableTask] - recordName: " + paramOSSFile);
      this.recordFilePath = (str1 + "/" + paramOSSFile);
      OSSLog.logD("[ResumableTask] - recordFilePath: " + this.recordFilePath);
      this.saveCallback = paramSaveCallback;
      this.buffer = new byte[this.threadNum][];
      this.progressInEachThread = new AtomicInteger[this.threadNum];
      int i = 0;
      while (i < this.threadNum)
      {
        this.progressInEachThread[i] = new AtomicInteger(0);
        i += 1;
      }
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
      {
        paramOSSFile = paramResumableTaskOption;
        if (OSSLog.isEnableLog())
        {
          localNoSuchAlgorithmException.printStackTrace();
          paramOSSFile = paramResumableTaskOption;
        }
      }
    }
  }

  private HttpUriRequest buildInitialRequest(BaseObject.HttpMethod paramHttpMethod, boolean paramBoolean, ResourceToQuery paramResourceToQuery)
    throws UnsupportedEncodingException
  {
    String str4 = "/" + URLEncoder.encode(this.ossFile.getObjectKey(), "UTF-8") + "?" + paramResourceToQuery.getOnlyUriQuery();
    String str3 = this.ossFile.getLabeledBucket().chooseProperHeaderHost(false);
    String str1 = HttpdnsMini.getInstance().getIpByHostAsync(str3);
    if ((str1 == null) || (OSSClient.detectIfProxyExist()))
      str1 = str3;
    String str2;
    if (OSSClient.getClientConfiguration().isSecurityTunnelRequired())
    {
      str2 = "https://";
      str2 = str2 + str1 + str4;
      str1 = null;
      switch (1.$SwitchMap$com$alibaba$sdk$android$oss$storage$BaseObject$HttpMethod[paramHttpMethod.ordinal()])
      {
      default:
        paramHttpMethod = str1;
      case 1:
      case 2:
      case 3:
      case 4:
      }
    }
    while (true)
    {
      paramResourceToQuery.setBaseResource("/" + this.ossFile.getBucketName() + "/" + this.ossFile.getObjectKey());
      paramHttpMethod.setHeader("Host", str3);
      paramHttpMethod.setHeader("Content-Type", "");
      paramHttpMethod.setHeader("Accept-Encoding", "*");
      if (!paramBoolean)
        break label325;
      OSSToolKit.buildRequestWithCompleteInfo(paramHttpMethod, this.ossFile, paramResourceToQuery);
      return paramHttpMethod;
      str2 = "http://";
      break;
      paramHttpMethod = new HttpGet(str2);
      continue;
      paramHttpMethod = new HttpPut(str2);
      continue;
      paramHttpMethod = new HttpPost(str2);
      continue;
      paramHttpMethod = new HttpDelete(str2);
    }
    label325: OSSToolKit.buildMutilPartUploadRequest(paramHttpMethod, this.ossFile, paramResourceToQuery);
    return paramHttpMethod;
  }

  private void completeMultiPartUpload()
    throws Exception
  {
    Object localObject1 = new ResourceToQuery();
    ((ResourceToQuery)localObject1).setQuery("uploadId=" + this.uploadId);
    localObject1 = (HttpPost)buildInitialRequest(BaseObject.HttpMethod.POST, false, (ResourceToQuery)localObject1);
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("<CompleteMultipartUpload>\n");
    int i = 0;
    while (i < this.blockNum)
    {
      ((StringBuilder)localObject2).append("<Part>\n");
      ((StringBuilder)localObject2).append("<PartNumber>" + (i + 1) + "</PartNumber>\n");
      ((StringBuilder)localObject2).append("<ETag>" + readSpecifiedBlockEtag(this.rafTorecordFile, i) + "</ETag>\n");
      ((StringBuilder)localObject2).append("</Part>\n");
      i += 1;
    }
    ((StringBuilder)localObject2).append("</CompleteMultipartUpload>\n");
    localObject2 = ((StringBuilder)localObject2).toString().getBytes();
    ((HttpPost)localObject1).setEntity(new InputStreamEntity(new ByteArrayInputStream((byte[])localObject2), localObject2.length));
    localObject2 = OSSToolKit.executeRequestWithLog(this.client, (HttpUriRequest)localObject1);
    if (((HttpResponse)localObject2).getStatusLine().getStatusCode() != 200)
      throw OSSToolKit.handleExceptionalResponse((HttpResponse)localObject2, (HttpUriRequest)localObject1, this.ossFile.getBucketName(), this.ossFile.getObjectKey());
    OSSToolKit.consumeResponseEntity((HttpResponse)localObject2);
  }

  private void createTask()
    throws Exception
  {
    RandomAccessFile localRandomAccessFile = new RandomAccessFile(new File(this.recordFilePath), "r");
    int j = 48;
    int i = 0;
    if (i < this.blockNum)
    {
      localRandomAccessFile.seek(j);
      if (!localRandomAccessFile.readBoolean())
        this.partsToUpload.add(Integer.valueOf(i));
      while (true)
      {
        j += 40;
        i += 1;
        break;
        if (i == this.blockNum - 1)
          this.currentUpload.addAndGet(this.totalSize - this.blockSize * i);
        else
          this.currentUpload.addAndGet(this.blockSize);
      }
    }
    OSSLog.logD("[createTask] - still need to upload: " + this.partsToUpload.size() + "  total: " + this.blockNum);
    this.completedBlockNum.set(this.blockNum - this.partsToUpload.size());
    localRandomAccessFile.close();
  }

  private void deleteRecordFile()
  {
    try
    {
      File localFile = new File(this.recordFilePath);
      if (localFile.exists())
        localFile.delete();
      return;
    }
    catch (Exception localException)
    {
      while (!OSSLog.isEnableLog());
      localException.printStackTrace();
    }
  }

  private void executeTask()
    throws Exception
  {
    int i = 0;
    while (i < this.threadNum)
    {
      new Thread(new UploadPartTask(i)).start();
      i += 1;
    }
  }

  private void fetchUploadId()
    throws Exception
  {
    Object localObject = new ResourceToQuery();
    ((ResourceToQuery)localObject).setQuery("uploads");
    localObject = (HttpPost)buildInitialRequest(BaseObject.HttpMethod.POST, true, (ResourceToQuery)localObject);
    ((HttpPost)localObject).setEntity(new StringEntity("<init multipart uploads>"));
    HttpResponse localHttpResponse = OSSToolKit.executeRequestWithLog(this.client, (HttpUriRequest)localObject);
    if (localHttpResponse.getStatusLine().getStatusCode() == 200)
    {
      this.uploadId = OSSToolKit.parseUploadIdFromXml(localHttpResponse.getEntity().getContent());
      OSSToolKit.consumeResponseEntity(localHttpResponse);
      return;
    }
    throw OSSToolKit.handleExceptionalResponse(localHttpResponse, (HttpUriRequest)localObject, this.ossFile.getBucketName(), this.ossFile.getObjectKey());
  }

  private void initRecordFile()
    throws IOException
  {
    Object localObject = new File(this.recordFilePath);
    if (!((File)localObject).exists())
      ((File)localObject).createNewFile();
    localObject = new RandomAccessFile((File)localObject, "rwd");
    writeUploadId((RandomAccessFile)localObject, this.uploadId);
    writeLastModifiedTime((RandomAccessFile)localObject, this.lastModifiedTime);
    int j = 48;
    int i = 0;
    while (i < this.blockNum)
    {
      ((RandomAccessFile)localObject).seek(j);
      ((RandomAccessFile)localObject).writeBoolean(false);
      j += 40;
      i += 1;
    }
    ((RandomAccessFile)localObject).close();
  }

  private void prepareTask()
    throws Exception
  {
    recoveryFromFile();
    properBlockSize();
    if ((this.uploadId != null) && (!checkIfUploadIdExists()))
    {
      OSSLog.logD("[prepareTask] - Local uploadId exists but cant find remote upload id.");
      this.uploadId = null;
      deleteRecordFile();
    }
    if (this.uploadId == null)
    {
      fetchUploadId();
      initRecordFile();
    }
    int i = 0;
    while (i < this.threadNum)
    {
      this.buffer[i] = new byte[this.blockSize];
      i += 1;
    }
  }

  private void properBlockSize()
  {
    if (this.totalSize < 10485760L)
    {
      this.blockSize = 131072;
      if (this.totalSize % this.blockSize != 0L)
        break label109;
    }
    label109: for (long l = this.totalSize / this.blockSize; ; l = this.totalSize / this.blockSize + 1L)
    {
      this.blockNum = ((int)l);
      return;
      if (this.totalSize < 52428800L)
      {
        this.blockSize = 262144;
        break;
      }
      if (this.totalSize < 209715200L)
      {
        this.blockSize = 524288;
        break;
      }
      this.blockSize = ((int)(this.totalSize / 500L));
      break;
    }
  }

  private long readLastModifiedTime(RandomAccessFile paramRandomAccessFile)
    throws IOException
  {
    paramRandomAccessFile.seek(40L);
    return paramRandomAccessFile.readLong();
  }

  private String readSpecifiedBlockEtag(RandomAccessFile paramRandomAccessFile, int paramInt)
    throws IOException
  {
    paramRandomAccessFile.seek(paramInt * 40 + 48 + 1);
    return paramRandomAccessFile.readLine();
  }

  private String readUploadId(RandomAccessFile paramRandomAccessFile)
    throws IOException
  {
    paramRandomAccessFile.seek(0L);
    return paramRandomAccessFile.readLine();
  }

  private void recordToFile(RandomAccessFile paramRandomAccessFile, int paramInt, String paramString)
    throws IOException
  {
    paramRandomAccessFile.seek(paramInt * 40 + 48 + 1);
    paramRandomAccessFile.write((paramString + "\n").getBytes());
    paramRandomAccessFile.seek(paramInt * 40 + 48);
    paramRandomAccessFile.writeBoolean(true);
  }

  private void recoveryFromFile()
  {
    try
    {
      File localFile = new File(this.recordFilePath);
      if (localFile.exists())
      {
        RandomAccessFile localRandomAccessFile = new RandomAccessFile(localFile, "r");
        if (readLastModifiedTime(localRandomAccessFile) == this.lastModifiedTime)
        {
          this.uploadId = readUploadId(localRandomAccessFile);
          OSSLog.logD("[recovery] - " + this.uploadId);
        }
        while (true)
        {
          localRandomAccessFile.close();
          return;
          localFile.delete();
        }
      }
    }
    catch (Exception localException)
    {
      deleteRecordFile();
      if (OSSLog.isEnableLog())
        localException.printStackTrace();
    }
  }

  private void writeLastModifiedTime(RandomAccessFile paramRandomAccessFile, long paramLong)
    throws IOException
  {
    paramRandomAccessFile.seek(40L);
    paramRandomAccessFile.writeLong(paramLong);
  }

  private void writeUploadId(RandomAccessFile paramRandomAccessFile, String paramString)
    throws IOException
  {
    paramRandomAccessFile.seek(0L);
    paramRandomAccessFile.write((paramString + "\n").getBytes());
  }

  public boolean checkIfUploadIdExists()
    throws Exception
  {
    boolean bool2 = false;
    Object localObject = new ResourceToQuery();
    ((ResourceToQuery)localObject).setQuery("uploadId=" + this.uploadId);
    localObject = (HttpGet)buildInitialRequest(BaseObject.HttpMethod.GET, false, (ResourceToQuery)localObject);
    localObject = this.client.execute((HttpUriRequest)localObject);
    OSSToolKit.consumeResponseEntity((HttpResponse)localObject);
    boolean bool1 = bool2;
    if (((HttpResponse)localObject).getStatusLine().getStatusCode() >= 200)
    {
      bool1 = bool2;
      if (((HttpResponse)localObject).getStatusLine().getStatusCode() < 300)
        bool1 = true;
    }
    return bool1;
  }

  public void deleteUploadId()
    throws Exception
  {
    Object localObject = new ResourceToQuery();
    ((ResourceToQuery)localObject).setQuery("uploadId=" + this.uploadId);
    localObject = (HttpDelete)buildInitialRequest(BaseObject.HttpMethod.DELETE, false, (ResourceToQuery)localObject);
    localObject = this.client.execute((HttpUriRequest)localObject);
    if (((HttpResponse)localObject).getStatusLine().getStatusCode() == 204)
    {
      OSSToolKit.printResponseHeader((HttpResponse)localObject);
      return;
    }
    throw OSSToolKit.buildOssException((HttpResponse)localObject, this.ossFile.getBucketName(), this.ossFile.getObjectKey());
  }

  public void run()
  {
    while (true)
    {
      try
      {
        prepareTask();
        createTask();
        executeTask();
        OSSLog.logD("[run] - begin executing task!");
        this.rafTorecordFile = new RandomAccessFile(new File(this.recordFilePath), "rwd");
        int j = -1;
        int k;
        if (this.completedBlockNum.get() < this.blockNum)
        {
          int i = (int)this.currentUpload.get();
          k = 0;
          if (k < this.threadNum)
          {
            i += this.progressInEachThread[k].get();
            k += 1;
            continue;
          }
          k = j;
          if (i > j)
          {
            k = j;
            if (this.totalSize / (i - j) < 250L)
            {
              this.saveCallback.onProgress(this.ossFile.getObjectKey(), i, (int)this.totalSize);
              k = i;
            }
          }
          if (!this.recordQueue.isEmpty())
          {
            BlockInfo localBlockInfo = (BlockInfo)this.recordQueue.remove();
            recordToFile(this.rafTorecordFile, localBlockInfo.blockIndex, localBlockInfo.eTag);
            this.completedBlockNum.incrementAndGet();
            continue;
          }
        }
      }
      catch (Exception localException1)
      {
        if (this.isFailure.compareAndSet(false, true))
          this.exception = localException1;
        if (((localException1 instanceof OSSException)) && (((OSSException)localException1).getExceptionType() == OSSException.ExceptionType.OSS_EXCEPTION))
          deleteRecordFile();
      }
      try
      {
        deleteUploadId();
        label249: this.saveCallback.onFailure(this.ossFile.objectKey, OSSToolKit.buildLocalException(this.ossFile.bucketName, this.ossFile.objectKey, this.exception));
        return;
        if (this.isFailure.get())
          throw this.exception;
        OSSToolKit.suspend(256L);
        j = k;
        continue;
        this.saveCallback.onProgress(this.ossFile.getObjectKey(), (int)this.totalSize, (int)this.totalSize);
        completeMultiPartUpload();
        deleteRecordFile();
        this.rafTorecordFile.close();
        this.saveCallback.onSuccess(this.ossFile.objectKey);
        return;
      }
      catch (Exception localException2)
      {
        break label249;
      }
    }
  }

  private class BlockInfo
  {
    public int blockIndex;
    public String eTag;

    private BlockInfo()
    {
    }
  }

  private class UploadPartTask
    implements Runnable
  {
    private int threadIndex;

    public UploadPartTask(int arg2)
    {
      int i;
      this.threadIndex = i;
    }

    private void uploadOnePart(int paramInt)
      throws Exception
    {
      RandomAccessFile localRandomAccessFile = new RandomAccessFile(ResumableUploadTask.this.fileToUpload, "r");
      localRandomAccessFile.seek(ResumableUploadTask.this.blockSize * paramInt);
      int i = 0;
      while (ResumableUploadTask.this.blockSize - i > 0)
      {
        int j = localRandomAccessFile.read(ResumableUploadTask.this.buffer[this.threadIndex], i, ResumableUploadTask.this.blockSize - i);
        if (j == -1)
          break;
        i += j;
      }
      localRandomAccessFile.close();
      uploadPart(this.threadIndex, paramInt, ResumableUploadTask.this.buffer[this.threadIndex], i);
    }

    private void uploadPart(final int paramInt1, int paramInt2, byte[] paramArrayOfByte, int paramInt3)
      throws Exception
    {
      paramArrayOfByte = new InputStreamEntity(new MeasuableInputStream(new ByteArrayInputStream(paramArrayOfByte, 0, paramInt3), new GenericProgressHandler()
      {
        public void onProgress(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
        {
          ResumableUploadTask.this.progressInEachThread[paramInt1].set(paramAnonymousInt2);
        }
      }
      , (int)ResumableUploadTask.this.totalSize), paramInt3);
      Object localObject = new ResourceToQuery();
      ((ResourceToQuery)localObject).setQuery("partNumber=" + (paramInt2 + 1));
      ((ResourceToQuery)localObject).setQuery("uploadId=" + ResumableUploadTask.this.uploadId);
      localObject = (HttpPut)ResumableUploadTask.this.buildInitialRequest(BaseObject.HttpMethod.PUT, false, (ResourceToQuery)localObject);
      ((HttpPut)localObject).setEntity(paramArrayOfByte);
      paramArrayOfByte = ResumableUploadTask.this.client.execute((HttpUriRequest)localObject);
      if (paramArrayOfByte.getStatusLine().getStatusCode() == 200)
      {
        localObject = paramArrayOfByte.getFirstHeader("ETag").getValue();
        OSSLog.logD("[uploadThisPart] - complete a part uploading, etag: " + (String)localObject);
        ResumableUploadTask.BlockInfo localBlockInfo = new ResumableUploadTask.BlockInfo(ResumableUploadTask.this, null);
        localBlockInfo.blockIndex = paramInt2;
        localBlockInfo.eTag = ((String)localObject);
        ResumableUploadTask.this.recordQueue.add(localBlockInfo);
        ResumableUploadTask.this.progressInEachThread[paramInt1].set(0);
        ResumableUploadTask.this.currentUpload.addAndGet(paramInt3);
        OSSToolKit.consumeResponseEntity(paramArrayOfByte);
        return;
      }
      throw OSSToolKit.handleExceptionalResponse(paramArrayOfByte, (HttpUriRequest)localObject, ResumableUploadTask.this.ossFile.getBucketName(), ResumableUploadTask.this.ossFile.getObjectKey());
    }

    public void run()
    {
      int i;
      int m;
      int k;
      if (!ResumableUploadTask.this.isFailure.get())
      {
        Integer localInteger = (Integer)ResumableUploadTask.this.partsToUpload.poll();
        if (localInteger != null)
        {
          i = 0;
          m = localInteger.intValue();
          k = 0;
        }
      }
      while (true)
      {
        int j = k;
        if (i <= ResumableUploadTask.this.maxRetryTime)
        {
          if (!ResumableUploadTask.this.isCancel.get())
            break label108;
          ResumableUploadTask.access$402(ResumableUploadTask.this, new InterruptedIOException("Task is canceled!"));
          j = k;
        }
        while (j == 0)
        {
          ResumableUploadTask.this.isFailure.set(true);
          return;
          try
          {
            label108: uploadOnePart(m);
            j = 1;
          }
          catch (Exception localException)
          {
            ResumableUploadTask.access$402(ResumableUploadTask.this, localException);
            i += 1;
          }
        }
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.oss.storage.ResumableUploadTask
 * JD-Core Version:    0.6.2
 */