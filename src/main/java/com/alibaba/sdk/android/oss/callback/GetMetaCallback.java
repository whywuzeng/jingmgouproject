package com.alibaba.sdk.android.oss.callback;

import java.util.List;
import org.apache.http.message.BasicNameValuePair;

public abstract class GetMetaCallback extends OSSCallback
{
  public void onProgress(String paramString, int paramInt1, int paramInt2)
  {
  }

  public abstract void onSuccess(String paramString, List<BasicNameValuePair> paramList);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.oss.callback.GetMetaCallback
 * JD-Core Version:    0.6.2
 */