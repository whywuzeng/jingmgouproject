package com.yolanda.nohttp.download;

import com.yolanda.nohttp.Headers;

public abstract interface DownloadListener
{
  public abstract void onCancel(int paramInt);

  public abstract void onDownloadError(int paramInt, StatusCode paramStatusCode, CharSequence paramCharSequence);

  public abstract void onFinish(int paramInt, String paramString);

  public abstract void onProgress(int paramInt1, int paramInt2, long paramLong);

  public abstract void onStart(int paramInt, boolean paramBoolean, long paramLong1, Headers paramHeaders, long paramLong2);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.download.DownloadListener
 * JD-Core Version:    0.6.2
 */