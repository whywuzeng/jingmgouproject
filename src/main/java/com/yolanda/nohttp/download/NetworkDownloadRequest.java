package com.yolanda.nohttp.download;

class NetworkDownloadRequest
{
  public final DownloadListener downloadListener;
  public final DownloadRequest downloadRequest;
  public final int what;

  public NetworkDownloadRequest(int paramInt, DownloadRequest paramDownloadRequest, DownloadListener paramDownloadListener)
  {
    this.what = paramInt;
    this.downloadRequest = paramDownloadRequest;
    this.downloadListener = paramDownloadListener;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.download.NetworkDownloadRequest
 * JD-Core Version:    0.6.2
 */