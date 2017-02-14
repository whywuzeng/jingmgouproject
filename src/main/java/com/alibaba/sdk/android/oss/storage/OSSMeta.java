package com.alibaba.sdk.android.oss.storage;

import com.alibaba.sdk.android.oss.callback.GetMetaCallback;
import com.alibaba.sdk.android.oss.model.OSSException;
import com.alibaba.sdk.android.oss.model.ObjectMeta;
import com.alibaba.sdk.android.oss.model.OperationCode;
import com.alibaba.sdk.android.oss.util.OSSToolKit;
import java.util.List;
import java.util.concurrent.ExecutorService;
import org.apache.http.HttpResponse;
import org.apache.http.message.BasicNameValuePair;

public class OSSMeta extends BaseObject
{
  public OSSMeta(OSSBucket paramOSSBucket, String paramString)
  {
    super(paramOSSBucket, paramString);
  }

  public List<BasicNameValuePair> getMeta()
    throws OSSException
  {
    this.method = BaseObject.HttpMethod.HEAD;
    HttpResponse localHttpResponse = syncRequest(generateRequest());
    List localList = this.responseMeta.getMetaNameValues();
    OSSToolKit.consumeResponseEntity(localHttpResponse);
    return localList;
  }

  public void getMetaInBackground(GetMetaCallback paramGetMetaCallback)
  {
    this.method = BaseObject.HttpMethod.HEAD;
    this.esService.execute(new OSSAsyncTask(this, OperationCode.META, paramGetMetaCallback));
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.oss.storage.OSSMeta
 * JD-Core Version:    0.6.2
 */