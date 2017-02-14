package com.alibaba.sdk.android.oss.storage;

import com.alibaba.sdk.android.dpa.util.ToolKit;
import com.alibaba.sdk.android.oss.callback.GenericProgressHandler;
import com.alibaba.sdk.android.oss.callback.GetBytesCallback;
import com.alibaba.sdk.android.oss.callback.GetFileCallback;
import com.alibaba.sdk.android.oss.callback.GetMetaCallback;
import com.alibaba.sdk.android.oss.callback.NoRespCallback;
import com.alibaba.sdk.android.oss.callback.OSSCallback;
import com.alibaba.sdk.android.oss.callback.SaveCallback;
import com.alibaba.sdk.android.oss.callback.ServerCallback;
import com.alibaba.sdk.android.oss.model.MeasuableInputStream;
import com.alibaba.sdk.android.oss.model.ObjectMeta;
import com.alibaba.sdk.android.oss.model.OperationCode;
import com.alibaba.sdk.android.oss.model.SharedComponent;
import com.alibaba.sdk.android.oss.util.OSSLog;
import com.alibaba.sdk.android.oss.util.OSSToolKit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.InputStreamEntity;

public class OSSAsyncTask
  implements Runnable
{
  private InputStream byteStreamToUpload;
  private boolean checkMd5sum;
  private HttpClient client;
  private MessageDigest digester;
  private String filePath;
  private int inputLength;
  private AtomicBoolean isCancel = null;
  private String objectKey;
  private OperationCode operCode;
  private OSSCallback ossCallback;
  private BaseObject ossObject;
  private HttpUriRequest request;

  public OSSAsyncTask(BaseObject paramBaseObject, OperationCode paramOperationCode, OSSCallback paramOSSCallback)
  {
    this.ossObject = paramBaseObject;
    this.objectKey = paramBaseObject.getObjectKey();
    this.operCode = paramOperationCode;
    this.ossCallback = paramOSSCallback;
  }

  public OSSAsyncTask(BaseObject paramBaseObject, OperationCode paramOperationCode, OSSCallback paramOSSCallback, InputStream paramInputStream, int paramInt, boolean paramBoolean)
  {
    this.ossObject = paramBaseObject;
    this.objectKey = paramBaseObject.getObjectKey();
    this.operCode = paramOperationCode;
    this.ossCallback = paramOSSCallback;
    this.byteStreamToUpload = paramInputStream;
    this.inputLength = paramInt;
    this.checkMd5sum = paramBoolean;
  }

  public OSSAsyncTask(BaseObject paramBaseObject, OperationCode paramOperationCode, OSSCallback paramOSSCallback, String paramString)
  {
    this.ossObject = paramBaseObject;
    this.objectKey = paramBaseObject.getObjectKey();
    this.operCode = paramOperationCode;
    this.ossCallback = paramOSSCallback;
    this.filePath = paramString;
  }

  public OSSAsyncTask(BaseObject paramBaseObject, OperationCode paramOperationCode, OSSCallback paramOSSCallback, String paramString, boolean paramBoolean)
  {
    this.ossObject = paramBaseObject;
    this.objectKey = paramBaseObject.getObjectKey();
    this.operCode = paramOperationCode;
    this.ossCallback = paramOSSCallback;
    this.filePath = paramString;
    this.checkMd5sum = paramBoolean;
  }

  public void detectCancelFlag()
    throws IOException
  {
    if ((this.isCancel != null) && (this.isCancel.get()))
    {
      this.isCancel.set(false);
      throw new InterruptedIOException("Canceled");
    }
  }

  public void run()
  {
    this.client = SharedComponent.getSharedClient();
    int i = -1;
    try
    {
      this.request = this.ossObject.generateRequest();
      detectCancelFlag();
      localObject3 = new GenericProgressHandler()
      {
        private int lastPoint = 0;
        private int thredhold = 0;

        public void onProgress(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
        {
          if (this.thredhold == 0)
            this.thredhold = (paramAnonymousInt3 / 100);
          if (this.lastPoint == 0)
            this.lastPoint = paramAnonymousInt2;
          if (paramAnonymousInt2 - this.lastPoint > this.thredhold)
          {
            OSSAsyncTask.this.ossCallback.onProgress(OSSAsyncTask.this.objectKey, paramAnonymousInt2, paramAnonymousInt3);
            this.lastPoint = paramAnonymousInt2;
          }
        }
      };
      Object localObject1;
      if ((this.operCode == OperationCode.SAVEFILE) || (this.operCode == OperationCode.SAVEFILEWITHSERVERCALLBACK))
      {
        File localFile = new File(this.filePath);
        localObject2 = new FileInputStream(localFile);
        localObject1 = localObject2;
        if (this.checkMd5sum)
        {
          this.digester = MessageDigest.getInstance("MD5");
          localObject1 = new DigestInputStream((InputStream)localObject2, this.digester);
        }
        localObject1 = new MeasuableInputStream((InputStream)localObject1, (GenericProgressHandler)localObject3, (int)localFile.length());
        ((MeasuableInputStream)localObject1).setSwitch(this.isCancel);
        ((HttpPut)this.request).setEntity(new InputStreamEntity((InputStream)localObject1, (int)localFile.length()));
        i = (int)localFile.length();
        localObject1 = OSSToolKit.executeRequestWithLog(this.client, this.request);
        detectCancelFlag();
        OSSLog.logD("Done checking cancel flag");
        if ((((HttpResponse)localObject1).getStatusLine().getStatusCode() < 200) || (((HttpResponse)localObject1).getStatusLine().getStatusCode() >= 300) || (((HttpResponse)localObject1).getStatusLine().getStatusCode() == 203))
          break label885;
        OSSLog.logD("[run] - " + this.operCode);
        if (OSSToolKit.checkRequestIsGetOrHead(this.request))
          this.ossObject.responseMeta = OSSToolKit.getObjectMetadataFromResponse((HttpResponse)localObject1);
      }
      switch (2.$SwitchMap$com$alibaba$sdk$android$oss$model$OperationCode[this.operCode.ordinal()])
      {
      case 1:
        while (true)
        {
          OSSToolKit.consumeResponseEntity((HttpResponse)localObject1);
          return;
          if ((this.operCode != OperationCode.SAVEBYTES) && (this.operCode != OperationCode.SAVEBYTESWITHSERVERCALLBACK))
            break;
          if (this.checkMd5sum)
          {
            this.digester = MessageDigest.getInstance("MD5");
            this.byteStreamToUpload = new DigestInputStream(this.byteStreamToUpload, this.digester);
          }
          localObject1 = new MeasuableInputStream(this.byteStreamToUpload, (GenericProgressHandler)localObject3, this.inputLength);
          ((MeasuableInputStream)localObject1).setSwitch(this.isCancel);
          ((HttpPut)this.request).setEntity(new InputStreamEntity((InputStream)localObject1, this.inputLength));
          i = this.inputLength;
          break;
          localObject2 = (GetBytesCallback)this.ossCallback;
          i = (int)((HttpResponse)localObject1).getEntity().getContentLength();
          localObject3 = new MeasuableInputStream(((HttpResponse)localObject1).getEntity().getContent(), (GenericProgressHandler)localObject3, i);
          ((MeasuableInputStream)localObject3).setSwitch(this.isCancel);
          localObject3 = OSSToolKit.readFullyToByteArray((InputStream)localObject3);
          detectCancelFlag();
          ((GetBytesCallback)localObject2).onProgress(this.objectKey, i, i);
          ((GetBytesCallback)localObject2).onSuccess(this.objectKey, (byte[])localObject3);
        }
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      }
    }
    catch (Exception localException1)
    {
      while (true)
        try
        {
          this.request.abort();
          OSSLog.logD("Error occur: " + localException1.toString());
          if (OSSLog.isEnableLog())
            localException1.printStackTrace();
          this.ossCallback.onFailure(this.objectKey, OSSToolKit.buildLocalException(this.ossObject.getBucketName(), this.objectKey, localException1));
          return;
          Object localObject2 = (GetFileCallback)this.ossCallback;
          i = (int)localException1.getEntity().getContentLength();
          Object localObject3 = new MeasuableInputStream(localException1.getEntity().getContent(), (GenericProgressHandler)localObject3, i);
          ((MeasuableInputStream)localObject3).setSwitch(this.isCancel);
          OSSToolKit.readFullyToLocalFile((InputStream)localObject3, this.filePath);
          detectCancelFlag();
          ((GetFileCallback)localObject2).onProgress(this.objectKey, i, i);
          ((GetFileCallback)localObject2).onSuccess(this.objectKey, this.filePath);
          continue;
          localObject2 = (SaveCallback)this.ossCallback;
          if (this.digester != null)
            OSSToolKit.checkETagMd5Invalid(this.ossObject.getBucketName(), this.objectKey, this.digester, localException1);
          ((SaveCallback)localObject2).onProgress(this.objectKey, i, i);
          ((SaveCallback)localObject2).onSuccess(this.objectKey);
          continue;
          localObject2 = (ServerCallback)this.ossCallback;
          if (this.digester != null)
            OSSToolKit.checkETagMd5Invalid(this.ossObject.getBucketName(), this.objectKey, this.digester, localException1);
          localObject3 = ToolKit.readFullyToString(localException1.getEntity().getContent());
          ((ServerCallback)localObject2).onProgress(this.objectKey, i, i);
          ((ServerCallback)localObject2).onSuccess(this.objectKey, (String)localObject3);
          continue;
          ((NoRespCallback)this.ossCallback).onSuccess(this.objectKey);
          continue;
          ((GetMetaCallback)this.ossCallback).onSuccess(this.objectKey, this.ossObject.responseMeta.getMetaNameValues());
          continue;
          label885: this.ossCallback.onFailure(this.objectKey, OSSToolKit.handleExceptionalResponse(localException1, this.request, this.ossObject.getBucketName(), this.objectKey));
          return;
        }
        catch (Exception localException2)
        {
        }
    }
  }

  public void setSwitch(AtomicBoolean paramAtomicBoolean)
  {
    this.isCancel = paramAtomicBoolean;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.oss.storage.OSSAsyncTask
 * JD-Core Version:    0.6.2
 */