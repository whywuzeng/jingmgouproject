package com.umeng.update;

public abstract interface UmengDownloadListener
{
  public abstract void OnDownloadEnd(int paramInt, String paramString);

  public abstract void OnDownloadStart();

  public abstract void OnDownloadUpdate(int paramInt);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.update.UmengDownloadListener
 * JD-Core Version:    0.6.2
 */