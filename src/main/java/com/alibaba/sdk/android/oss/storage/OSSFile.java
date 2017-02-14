package com.alibaba.sdk.android.oss.storage;

import com.alibaba.sdk.android.dpa.util.ToolKit;
import com.alibaba.sdk.android.oss.callback.GetFileCallback;
import com.alibaba.sdk.android.oss.callback.OSSCallback;
import com.alibaba.sdk.android.oss.callback.SaveCallback;
import com.alibaba.sdk.android.oss.callback.ServerCallback;
import com.alibaba.sdk.android.oss.model.OSSException;
import com.alibaba.sdk.android.oss.model.ObjectMeta;
import com.alibaba.sdk.android.oss.model.OperationCode;
import com.alibaba.sdk.android.oss.model.ResumableTaskOption;
import com.alibaba.sdk.android.oss.util.OSSToolKit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

public class OSSFile extends NormalDataObject
{
  private String filePath;

  public OSSFile(OSSBucket paramOSSBucket, String paramString)
  {
    super(paramOSSBucket, paramString);
  }

  public TaskHandler ResumableDownloadToInBackground(String paramString, GetFileCallback paramGetFileCallback)
  {
    this.filePath = paramString;
    this.esService.execute(new ResumableDownloadTask(this, paramGetFileCallback));
    return new TaskHandler(this.cancelFlag);
  }

  public TaskHandler ResumableDownloadToInBackground(String paramString, ResumableTaskOption paramResumableTaskOption, GetFileCallback paramGetFileCallback)
  {
    this.filePath = paramString;
    this.esService.execute(new ResumableDownloadTask(this, paramResumableTaskOption, paramGetFileCallback));
    return new TaskHandler(this.cancelFlag);
  }

  public TaskHandler ResumableUploadInBackground(SaveCallback paramSaveCallback)
  {
    this.esService.execute(new ResumableUploadTask(this, paramSaveCallback));
    return new TaskHandler(this.cancelFlag);
  }

  public TaskHandler ResumableUploadInBackground(ResumableTaskOption paramResumableTaskOption, SaveCallback paramSaveCallback)
  {
    this.esService.execute(new ResumableUploadTask(this, paramResumableTaskOption, paramSaveCallback));
    return new TaskHandler(this.cancelFlag);
  }

  protected void asynDownload(GetFileCallback paramGetFileCallback)
  {
    paramGetFileCallback = new OSSAsyncTask(this, OperationCode.GETFILE, paramGetFileCallback, this.filePath);
    paramGetFileCallback.setSwitch(this.cancelFlag);
    this.esService.execute(paramGetFileCallback);
  }

  protected void asynUpload(OSSCallback paramOSSCallback, boolean paramBoolean)
  {
    if (paramBoolean);
    for (OperationCode localOperationCode = OperationCode.SAVEFILEWITHSERVERCALLBACK; ; localOperationCode = OperationCode.SAVEFILE)
    {
      paramOSSCallback = new OSSAsyncTask(this, localOperationCode, paramOSSCallback, this.filePath, this.checkUploadMd5sum);
      paramOSSCallback.setSwitch(this.cancelFlag);
      this.esService.execute(paramOSSCallback);
      return;
    }
  }

  public void downloadTo(String paramString)
    throws OSSException
  {
    this.method = BaseObject.HttpMethod.GET;
    this.filePath = paramString;
    syncDownload();
  }

  public TaskHandler downloadToInBackground(String paramString, GetFileCallback paramGetFileCallback)
  {
    this.method = BaseObject.HttpMethod.GET;
    this.filePath = paramString;
    asynDownload(paramGetFileCallback);
    return new TaskHandler(this.cancelFlag);
  }

  public String getDownloadFilePath()
  {
    return this.filePath;
  }

  public String getUploadFilePath()
  {
    return this.filePath;
  }

  public void setUploadFilePath(String paramString1, String paramString2)
    throws FileNotFoundException
  {
    ToolKit.checkNotEmptyStringArg(paramString1, "The upload file path can't be empty");
    if (!new File(paramString1).exists())
      throw new FileNotFoundException("the file doesn't exist.");
    this.filePath = paramString1;
    this.requestMeta.setContentType(OSSToolKit.formatContentType(paramString2));
  }

  protected void syncDownload()
    throws OSSException
  {
    HttpResponse localHttpResponse = syncRequest(generateRequest());
    try
    {
      OSSToolKit.readFullyToLocalFile(localHttpResponse.getEntity().getContent(), this.filePath);
      return;
    }
    catch (IOException localIOException)
    {
      throw new OSSException(this.bucketName, this.objectKey, localIOException);
    }
  }

  // ERROR //
  protected void syncUpload()
    throws OSSException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 152	com/alibaba/sdk/android/oss/storage/OSSFile:generateRequest	()Lorg/apache/http/client/methods/HttpUriRequest;
    //   4: checkcast 186	org/apache/http/client/methods/HttpPut
    //   7: astore_3
    //   8: new 122	java/io/File
    //   11: dup
    //   12: aload_0
    //   13: getfield 15	com/alibaba/sdk/android/oss/storage/OSSFile:filePath	Ljava/lang/String;
    //   16: invokespecial 124	java/io/File:<init>	(Ljava/lang/String;)V
    //   19: astore 4
    //   21: aconst_null
    //   22: astore_1
    //   23: new 188	java/io/FileInputStream
    //   26: dup
    //   27: aload 4
    //   29: invokespecial 191	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   32: astore_2
    //   33: aload_0
    //   34: getfield 79	com/alibaba/sdk/android/oss/storage/OSSFile:checkUploadMd5sum	Z
    //   37: ifeq +89 -> 126
    //   40: ldc 193
    //   42: invokestatic 199	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   45: astore_1
    //   46: new 201	java/security/DigestInputStream
    //   49: dup
    //   50: aload_2
    //   51: aload_1
    //   52: invokespecial 204	java/security/DigestInputStream:<init>	(Ljava/io/InputStream;Ljava/security/MessageDigest;)V
    //   55: astore_2
    //   56: aload_3
    //   57: new 206	org/apache/http/entity/InputStreamEntity
    //   60: dup
    //   61: aload_2
    //   62: aload 4
    //   64: invokevirtual 210	java/io/File:length	()J
    //   67: invokespecial 213	org/apache/http/entity/InputStreamEntity:<init>	(Ljava/io/InputStream;J)V
    //   70: invokevirtual 217	org/apache/http/client/methods/HttpPut:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   73: aload_0
    //   74: aload_3
    //   75: invokevirtual 156	com/alibaba/sdk/android/oss/storage/OSSFile:syncRequest	(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
    //   78: astore_2
    //   79: aload_0
    //   80: getfield 79	com/alibaba/sdk/android/oss/storage/OSSFile:checkUploadMd5sum	Z
    //   83: ifeq +16 -> 99
    //   86: aload_0
    //   87: getfield 175	com/alibaba/sdk/android/oss/storage/OSSFile:bucketName	Ljava/lang/String;
    //   90: aload_0
    //   91: getfield 178	com/alibaba/sdk/android/oss/storage/OSSFile:objectKey	Ljava/lang/String;
    //   94: aload_1
    //   95: aload_2
    //   96: invokestatic 221	com/alibaba/sdk/android/oss/util/OSSToolKit:checkETagMd5Invalid	(Ljava/lang/String;Ljava/lang/String;Ljava/security/MessageDigest;Lorg/apache/http/HttpResponse;)V
    //   99: aload_2
    //   100: invokestatic 225	com/alibaba/sdk/android/oss/util/OSSToolKit:consumeResponseEntity	(Lorg/apache/http/HttpResponse;)V
    //   103: return
    //   104: astore_1
    //   105: new 89	com/alibaba/sdk/android/oss/model/OSSException
    //   108: dup
    //   109: aload_0
    //   110: getfield 175	com/alibaba/sdk/android/oss/storage/OSSFile:bucketName	Ljava/lang/String;
    //   113: aload_0
    //   114: getfield 178	com/alibaba/sdk/android/oss/storage/OSSFile:objectKey	Ljava/lang/String;
    //   117: aload_1
    //   118: invokespecial 181	com/alibaba/sdk/android/oss/model/OSSException:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V
    //   121: athrow
    //   122: astore_1
    //   123: goto -18 -> 105
    //   126: goto -70 -> 56
    //
    // Exception table:
    //   from	to	target	type
    //   23	33	104	java/lang/Exception
    //   33	56	122	java/lang/Exception
  }

  public void upload()
    throws FileNotFoundException, OSSException
  {
    this.method = BaseObject.HttpMethod.PUT;
    syncUpload();
  }

  public TaskHandler uploadInBackground(SaveCallback paramSaveCallback)
  {
    this.method = BaseObject.HttpMethod.PUT;
    asynUpload(paramSaveCallback, false);
    return new TaskHandler(this.cancelFlag);
  }

  public TaskHandler uploadInBackground(ServerCallback paramServerCallback)
  {
    this.method = BaseObject.HttpMethod.PUT;
    asynUpload(paramServerCallback, true);
    return new TaskHandler(this.cancelFlag);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.oss.storage.OSSFile
 * JD-Core Version:    0.6.2
 */