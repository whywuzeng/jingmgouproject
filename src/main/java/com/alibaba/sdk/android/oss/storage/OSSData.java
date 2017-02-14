package com.alibaba.sdk.android.oss.storage;

import com.alibaba.sdk.android.dpa.util.ToolKit;
import com.alibaba.sdk.android.oss.callback.GetBytesCallback;
import com.alibaba.sdk.android.oss.callback.OSSCallback;
import com.alibaba.sdk.android.oss.callback.SaveCallback;
import com.alibaba.sdk.android.oss.callback.ServerCallback;
import com.alibaba.sdk.android.oss.model.OSSException;
import com.alibaba.sdk.android.oss.model.ObjectMeta;
import com.alibaba.sdk.android.oss.model.OperationCode;
import com.alibaba.sdk.android.oss.util.OSSToolKit;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.concurrent.ExecutorService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.InputStreamEntity;

public class OSSData extends NormalDataObject
{
  private int inputLength;
  private InputStream inputStream;

  public OSSData(OSSBucket paramOSSBucket, String paramString)
  {
    super(paramOSSBucket, paramString);
  }

  protected void asynGet(GetBytesCallback paramGetBytesCallback)
  {
    paramGetBytesCallback = new OSSAsyncTask(this, OperationCode.GETBYTES, paramGetBytesCallback);
    paramGetBytesCallback.setSwitch(this.cancelFlag);
    this.esService.execute(paramGetBytesCallback);
  }

  protected void asynUpload(OSSCallback paramOSSCallback, boolean paramBoolean)
  {
    if (paramBoolean);
    for (OperationCode localOperationCode = OperationCode.SAVEBYTESWITHSERVERCALLBACK; ; localOperationCode = OperationCode.SAVEBYTES)
    {
      paramOSSCallback = new OSSAsyncTask(this, localOperationCode, paramOSSCallback, this.inputStream, this.inputLength, this.checkUploadMd5sum);
      paramOSSCallback.setSwitch(this.cancelFlag);
      this.esService.execute(paramOSSCallback);
      return;
    }
  }

  public byte[] get()
    throws OSSException
  {
    this.method = BaseObject.HttpMethod.GET;
    return syncGet();
  }

  public TaskHandler getInBackground(GetBytesCallback paramGetBytesCallback)
  {
    this.method = BaseObject.HttpMethod.GET;
    asynGet(paramGetBytesCallback);
    return new TaskHandler(this.cancelFlag);
  }

  public void setData(byte[] paramArrayOfByte, String paramString)
  {
    ToolKit.checkNotNullArg(paramArrayOfByte, "The data to be uploaded can't be null");
    this.inputLength = paramArrayOfByte.length;
    this.inputStream = new ByteArrayInputStream(paramArrayOfByte);
    this.requestMeta.setContentType(OSSToolKit.formatContentType(paramString));
  }

  public void setInputstream(InputStream paramInputStream, int paramInt, String paramString)
  {
    ToolKit.checkNotNullArg(paramInputStream, "The inputstream to be uploaded can't be null");
    String str = paramString;
    if (paramString == null)
      str = "";
    this.inputLength = paramInt;
    this.inputStream = paramInputStream;
    this.requestMeta.setContentType(str);
  }

  protected byte[] syncGet()
    throws OSSException
  {
    HttpResponse localHttpResponse = syncRequest(generateRequest());
    try
    {
      byte[] arrayOfByte = OSSToolKit.readFullyToByteArray(localHttpResponse.getEntity().getContent());
      OSSToolKit.consumeResponseEntity(localHttpResponse);
      return arrayOfByte;
    }
    catch (IOException localIOException)
    {
      throw new OSSException(getBucketName(), this.objectKey, localIOException);
    }
  }

  protected void syncUpload()
    throws OSSException
  {
    HttpPut localHttpPut = (HttpPut)generateRequest();
    InputStream localInputStream = this.inputStream;
    MessageDigest localMessageDigest = null;
    Object localObject = localInputStream;
    if (this.checkUploadMd5sum);
    try
    {
      localMessageDigest = MessageDigest.getInstance("MD5");
      localObject = new DigestInputStream(localInputStream, localMessageDigest);
      localHttpPut.setEntity(new InputStreamEntity((InputStream)localObject, this.inputLength));
      localObject = syncRequest(localHttpPut);
      if (this.checkUploadMd5sum)
        OSSToolKit.checkETagMd5Invalid(this.bucketName, this.objectKey, localMessageDigest, (HttpResponse)localObject);
      OSSToolKit.consumeResponseEntity((HttpResponse)localObject);
      return;
    }
    catch (Exception localException)
    {
      throw new OSSException(this.bucketName, this.objectKey, localException);
    }
  }

  public void upload()
    throws OSSException
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
 * Qualified Name:     com.alibaba.sdk.android.oss.storage.OSSData
 * JD-Core Version:    0.6.2
 */