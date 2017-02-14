package com.alibaba.sdk.android.oss.storage;

import com.alibaba.sdk.android.dpa.util.HttpdnsMini;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.callback.GetFileCallback;
import com.alibaba.sdk.android.oss.model.ClientConfiguration;
import com.alibaba.sdk.android.oss.model.ObjectMeta;
import com.alibaba.sdk.android.oss.model.Range;
import com.alibaba.sdk.android.oss.model.ResumableTaskOption;
import com.alibaba.sdk.android.oss.model.SharedComponent;
import com.alibaba.sdk.android.oss.util.OSSLog;
import com.alibaba.sdk.android.oss.util.OSSToolKit;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.RandomAccessFile;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

public class ResumableDownloadTask
  implements Runnable
{
  private static final int BLOCK_NUM_BYTE = 4;
  private static final int HEADER_BYTE = 12;
  private static final int INT_BYTE = 4;
  private static final int LAST_MOD_TIME_BYTE = 8;
  private static final int LONG_BYTE = 8;
  private int blockNum = 3;
  private int blockSize;
  private AtomicInteger[] breakPointOfEachBlock;
  private AtomicInteger completedNum = new AtomicInteger(0);
  private AtomicLong currentDownload = new AtomicLong(0L);
  private volatile Exception exception;
  private GetFileCallback getFileCallback;
  private AtomicBoolean isCancel;
  private AtomicBoolean isFailure = new AtomicBoolean(false);
  private AtomicBoolean localCancelledState;
  private int maxRetryTime = 2;
  private String objectKey;
  private int objectSize;
  private OSSFile ossFile;
  private String recordFilePath;
  private long recordLastModifiedT;
  private long remoteLastModifiedT;
  private String tag = "Download-";
  private File tmpFile;

  public ResumableDownloadTask(OSSFile paramOSSFile, GetFileCallback paramGetFileCallback)
  {
    this(paramOSSFile, null, paramGetFileCallback);
  }

  public ResumableDownloadTask(OSSFile paramOSSFile, ResumableTaskOption paramResumableTaskOption, GetFileCallback paramGetFileCallback)
  {
    this.ossFile = paramOSSFile;
    this.isCancel = this.ossFile.getCancelFlag();
    this.localCancelledState = new AtomicBoolean(false);
    this.tmpFile = new File(this.ossFile.getDownloadFilePath() + ".tmp");
    this.objectKey = this.ossFile.getObjectKey();
    this.getFileCallback = paramGetFileCallback;
    paramOSSFile = OSSClient.getDataDir().getAbsolutePath();
    paramGetFileCallback = paramOSSFile;
    if (paramResumableTaskOption != null)
    {
      if ((paramResumableTaskOption.getThreadNum() > 0) && (paramResumableTaskOption.getThreadNum() <= 5))
        this.blockNum = paramResumableTaskOption.getThreadNum();
      if ((paramResumableTaskOption.getAutoRetryTime() > 0) && (paramResumableTaskOption.getAutoRetryTime() <= 3))
        this.maxRetryTime = paramResumableTaskOption.getAutoRetryTime();
      paramGetFileCallback = paramOSSFile;
      if (paramResumableTaskOption.getRecordFileDirectory() != null)
        paramGetFileCallback = paramResumableTaskOption.getRecordFileDirectory();
    }
    paramOSSFile = null;
    try
    {
      paramResumableTaskOption = OSSToolKit.calMd5sumString((this.tag + this.ossFile.getBucketName() + "/" + this.ossFile.getObjectKey() + "-" + this.tmpFile.getAbsolutePath()).getBytes());
      paramOSSFile = paramResumableTaskOption;
      OSSLog.logD("[resumableTask] - recordName: " + paramResumableTaskOption);
      this.recordFilePath = (paramGetFileCallback + "/" + paramResumableTaskOption);
      OSSLog.logD("[ResumableTask] - recordFilePath: " + this.recordFilePath);
      return;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
      {
        paramResumableTaskOption = paramOSSFile;
        if (OSSLog.isEnableLog())
        {
          localNoSuchAlgorithmException.printStackTrace();
          paramResumableTaskOption = paramOSSFile;
        }
      }
    }
  }

  private void createNewTasks()
    throws IOException
  {
    RandomAccessFile localRandomAccessFile = new RandomAccessFile(new File(this.recordFilePath), "rwd");
    writeBlockNum(localRandomAccessFile, this.blockNum);
    writeLastModT(localRandomAccessFile, this.remoteLastModifiedT);
    this.blockSize = (this.objectSize / this.blockNum);
    this.breakPointOfEachBlock = new AtomicInteger[this.blockNum];
    int i = 0;
    while (i < this.blockNum)
    {
      this.breakPointOfEachBlock[i] = new AtomicInteger(this.blockSize * i);
      localRandomAccessFile.seek(12L);
      localRandomAccessFile.writeBoolean(false);
      i += 1;
    }
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
    catch (SecurityException localSecurityException)
    {
      while (!OSSLog.isEnableLog());
      localSecurityException.printStackTrace();
    }
  }

  private void detectCanceled()
    throws InterruptedIOException
  {
    try
    {
      if (this.localCancelledState.get())
        throw new InterruptedIOException("Canceled!");
    }
    finally
    {
    }
    if (this.isCancel.get())
    {
      this.localCancelledState.set(true);
      this.isCancel.set(false);
      throw new InterruptedIOException("Canceled!");
    }
  }

  private void fetchObjectInfo()
    throws Exception
  {
    OSSMeta localOSSMeta = new OSSMeta(this.ossFile.getLabeledBucket(), this.objectKey);
    localOSSMeta.getMeta();
    this.remoteLastModifiedT = localOSSMeta.getResponseMeta().getLastModified().getTime();
    this.objectSize = Integer.valueOf(localOSSMeta.getResponseMeta().getContentLength()).intValue();
    OSSLog.logD("[fetchObjectInfo] - objectSize: " + this.objectSize);
  }

  private void prepareDownload()
    throws Exception
  {
    fetchObjectInfo();
    recoveryFromFile();
    if (this.recordLastModifiedT == 0L)
      new File(this.recordFilePath).createNewFile();
    if ((this.recordLastModifiedT != 0L) && (!this.tmpFile.exists()))
    {
      this.tmpFile.createNewFile();
      this.recordLastModifiedT = 0L;
    }
    if (this.recordLastModifiedT == 0L)
    {
      createNewTasks();
      return;
    }
    resumeOldTask();
  }

  private int readBlockNum(RandomAccessFile paramRandomAccessFile)
    throws IOException
  {
    paramRandomAccessFile.seek(0L);
    return paramRandomAccessFile.readInt();
  }

  private long readLastModT(RandomAccessFile paramRandomAccessFile)
    throws IOException
  {
    paramRandomAccessFile.seek(4L);
    return paramRandomAccessFile.readLong();
  }

  private void recoveryFromFile()
  {
    try
    {
      Object localObject = new File(this.recordFilePath);
      OSSLog.logD("[recoveryFromFile] - " + ((File)localObject).exists());
      if (((File)localObject).exists())
      {
        localObject = new RandomAccessFile((File)localObject, "r");
        this.recordLastModifiedT = readLastModT((RandomAccessFile)localObject);
        if (this.remoteLastModifiedT != this.recordLastModifiedT)
          this.recordLastModifiedT = 0L;
        ((RandomAccessFile)localObject).close();
      }
      return;
    }
    catch (IOException localIOException)
    {
      do
      {
        OSSLog.logD("[recoveryFromFile] - " + localIOException.toString());
        deleteRecordFile();
        this.recordLastModifiedT = 0L;
      }
      while (!OSSLog.isEnableLog());
      localIOException.printStackTrace();
    }
  }

  private void resumeOldTask()
    throws IOException
  {
    RandomAccessFile localRandomAccessFile = new RandomAccessFile(new File(this.recordFilePath), "rwd");
    this.blockNum = readBlockNum(localRandomAccessFile);
    this.blockSize = (this.objectSize / this.blockNum);
    long l = 12L;
    this.breakPointOfEachBlock = new AtomicInteger[this.blockNum];
    int i = 0;
    while (true)
    {
      int k;
      if (i < this.blockNum)
      {
        this.breakPointOfEachBlock[i] = new AtomicInteger(this.blockSize * i);
        k = 0;
      }
      try
      {
        localRandomAccessFile.seek(l);
        boolean bool = localRandomAccessFile.readBoolean();
        k = bool;
        label109: if (k != 0)
        {
          int j = localRandomAccessFile.readInt();
          OSSLog.logD("[resumeOldTask] - " + i + ": " + j);
          this.breakPointOfEachBlock[i].set(j);
          this.currentDownload.addAndGet(j - this.blockSize * i);
        }
        l += 5L;
        i += 1;
        continue;
        return;
      }
      catch (IOException localIOException)
      {
        break label109;
      }
    }
  }

  private void startDownloadThread()
  {
    int i = 0;
    while (i < this.blockNum)
    {
      new Thread(new DownloadPartTask(i)).start();
      i += 1;
    }
  }

  private void writeBlockBreakPoint(RandomAccessFile paramRandomAccessFile, int paramInt1, int paramInt2)
    throws IOException
  {
    paramInt1 = paramInt1 * 5 + 12;
    paramRandomAccessFile.seek(paramInt1 + 1);
    paramRandomAccessFile.writeInt(paramInt2);
    paramRandomAccessFile.seek(paramInt1);
    paramRandomAccessFile.writeBoolean(true);
  }

  private void writeBlockNum(RandomAccessFile paramRandomAccessFile, int paramInt)
    throws IOException
  {
    paramRandomAccessFile.seek(0L);
    paramRandomAccessFile.writeInt(paramInt);
  }

  private void writeLastModT(RandomAccessFile paramRandomAccessFile, long paramLong)
    throws IOException
  {
    paramRandomAccessFile.seek(4L);
    paramRandomAccessFile.writeLong(paramLong);
  }

  public void run()
  {
    while (true)
    {
      try
      {
        prepareDownload();
        detectCanceled();
        startDownloadThread();
        RandomAccessFile localRandomAccessFile = new RandomAccessFile(new File(this.recordFilePath), "rw");
        if (this.completedNum.get() >= this.blockNum)
          break;
        OSSLog.logD("[run] - 1");
        this.getFileCallback.onProgress(this.objectKey, (int)this.currentDownload.get(), this.objectSize);
        int i = 0;
        if (i < this.blockNum)
        {
          writeBlockBreakPoint(localRandomAccessFile, i, this.breakPointOfEachBlock[i].get());
          i += 1;
          continue;
        }
        detectCanceled();
        if (this.isFailure.get())
          throw this.exception;
      }
      catch (Exception localException)
      {
        if (OSSLog.isEnableLog())
          localException.printStackTrace();
        if (this.isFailure.compareAndSet(false, true))
          this.exception = localException;
        this.isCancel.set(false);
        this.getFileCallback.onFailure(this.objectKey, OSSToolKit.buildLocalException(this.ossFile.bucketName, this.ossFile.objectKey, this.exception));
        return;
      }
      OSSToolKit.suspend(256L);
    }
    File localFile = new File(this.ossFile.getDownloadFilePath());
    if (localFile.exists())
      localFile.delete();
    this.tmpFile.renameTo(localFile);
    this.getFileCallback.onProgress(this.objectKey, this.objectSize, this.objectSize);
    this.getFileCallback.onSuccess(this.objectKey, this.ossFile.getUploadFilePath());
    deleteRecordFile();
  }

  public class DownloadPartTask
    implements Runnable
  {
    private int blockIndex;
    private HttpClient client;

    public DownloadPartTask(int arg2)
    {
      int i;
      this.blockIndex = i;
      this.client = SharedComponent.getSharedClient();
    }

    private void downloading()
      throws Exception
    {
      Object localObject3 = ResumableDownloadTask.this.ossFile.getLabeledBucket().chooseProperHeaderHost(true);
      Object localObject1 = HttpdnsMini.getInstance().getIpByHostAsync((String)localObject3);
      if ((localObject1 == null) || (OSSClient.detectIfProxyExist()))
        localObject1 = localObject3;
      if (OSSClient.getClientConfiguration().isSecurityTunnelRequired());
      RandomAccessFile localRandomAccessFile;
      int j;
      int i;
      for (Object localObject2 = "https://"; ; localObject2 = "http://")
      {
        localObject1 = (String)localObject2 + (String)localObject1 + "/" + URLEncoder.encode(ResumableDownloadTask.this.objectKey, "UTF-8");
        localRandomAccessFile = new RandomAccessFile(ResumableDownloadTask.this.tmpFile, "rw");
        localRandomAccessFile.setLength(ResumableDownloadTask.this.objectSize);
        j = ResumableDownloadTask.this.breakPointOfEachBlock[this.blockIndex].get();
        i = ResumableDownloadTask.this.blockSize * (this.blockIndex + 1) - 1;
        if (this.blockIndex == ResumableDownloadTask.this.blockNum - 1)
          i = ResumableDownloadTask.this.objectSize - 1;
        if (j <= i)
          break;
        localRandomAccessFile.close();
        return;
      }
      HttpGet localHttpGet = new HttpGet((String)localObject1);
      localHttpGet.setHeader("Accept-Encoding", "*");
      OSSToolKit.buildRequest(localHttpGet, ResumableDownloadTask.this.ossFile);
      localHttpGet.setHeader("Range", new Range(j, i).toString());
      localHttpGet.setHeader("Host", (String)localObject3);
      localObject1 = null;
      try
      {
        localObject2 = this.client.execute(localHttpGet);
        localObject1 = localObject2;
        ResumableDownloadTask.this.detectCanceled();
        if ((((HttpResponse)localObject2).getStatusLine().getStatusCode() < 200) || (((HttpResponse)localObject2).getStatusLine().getStatusCode() >= 300))
          break label488;
        ResumableDownloadTask.this.ossFile.responseMeta = OSSToolKit.getObjectMetadataFromResponse((HttpResponse)localObject2);
        localObject1 = ((HttpResponse)localObject2).getEntity().getContent();
        localObject3 = new byte[4096];
        OSSLog.logD("[run] - from breakPoint: " + j);
        localRandomAccessFile.seek(j);
        i = j;
        while (true)
        {
          j = ((InputStream)localObject1).read((byte[])localObject3);
          if (j != -1)
          {
            localRandomAccessFile.write((byte[])localObject3, 0, j);
            i += j;
            ResumableDownloadTask.this.breakPointOfEachBlock[this.blockIndex].set(i);
            ResumableDownloadTask.this.currentDownload.addAndGet(j);
            try
            {
              ResumableDownloadTask.this.detectCanceled();
            }
            catch (Exception localException1)
            {
              OSSToolKit.consumeResponseEntity((HttpResponse)localObject2);
              localRandomAccessFile.close();
              throw localException1;
            }
          }
        }
      }
      catch (Exception localException2)
      {
        OSSToolKit.consumeResponseEntity(localException1);
        throw localException2;
      }
      localRandomAccessFile.close();
      OSSToolKit.consumeResponseEntity(localException2);
      return;
      label488: localRandomAccessFile.close();
      throw OSSToolKit.handleExceptionalResponse(localException2, localHttpGet, ResumableDownloadTask.this.ossFile.getBucketName(), ResumableDownloadTask.this.objectKey);
    }

    public void run()
    {
      int j = 0;
      int i = 0;
      int k = i;
      if (!ResumableDownloadTask.this.isFailure.get())
      {
        if (j > ResumableDownloadTask.this.maxRetryTime)
          break label126;
        OSSLog.logD("[run] - 2");
        if (!ResumableDownloadTask.this.localCancelledState.get())
          break label85;
        ResumableDownloadTask.access$1202(ResumableDownloadTask.this, new InterruptedIOException("Canceled!"));
      }
      label85: label126: for (k = i; ; k = i)
      {
        while (true)
        {
          if (k == 0)
            ResumableDownloadTask.this.isFailure.set(true);
          return;
          try
          {
            downloading();
            i = 1;
            k = 1;
            ResumableDownloadTask.this.completedNum.incrementAndGet();
          }
          catch (Exception localException)
          {
            ResumableDownloadTask.access$1202(ResumableDownloadTask.this, localException);
            j += 1;
          }
        }
        break;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.oss.storage.ResumableDownloadTask
 * JD-Core Version:    0.6.2
 */