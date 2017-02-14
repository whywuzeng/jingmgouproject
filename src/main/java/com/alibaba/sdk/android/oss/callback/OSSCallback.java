package com.alibaba.sdk.android.oss.callback;

import com.alibaba.sdk.android.oss.model.OSSException;

public abstract class OSSCallback
{
  public abstract void onFailure(String paramString, OSSException paramOSSException);

  public abstract void onProgress(String paramString, int paramInt1, int paramInt2);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.oss.callback.OSSCallback
 * JD-Core Version:    0.6.2
 */