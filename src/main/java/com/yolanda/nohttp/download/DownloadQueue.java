package com.yolanda.nohttp.download;

import com.yolanda.nohttp.BasicAnalyzeRequest;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

public class DownloadQueue
{
  private DownloadDispatch[] mDispatchers;
  private final LinkedBlockingQueue<NetworkDownloadRequest> mDownloadQueue = new LinkedBlockingQueue();
  private final Downloader mDownloader;

  public DownloadQueue(Downloader paramDownloader, int paramInt)
  {
    this.mDownloader = paramDownloader;
    this.mDispatchers = new DownloadDispatch[paramInt];
  }

  public void add(int paramInt, DownloadRequest paramDownloadRequest, DownloadListener paramDownloadListener)
  {
    if (!paramDownloadRequest.getAnalyzeReqeust().inQueue())
    {
      paramDownloadRequest.getAnalyzeReqeust().takeQueue(true);
      paramDownloadRequest.getAnalyzeReqeust().start();
      this.mDownloadQueue.add(new NetworkDownloadRequest(paramInt, paramDownloadRequest, paramDownloadListener));
    }
  }

  public void cancelAll(Object paramObject)
  {
    synchronized (this.mDownloadQueue)
    {
      Iterator localIterator = this.mDownloadQueue.iterator();
      if (!localIterator.hasNext())
        return;
      ((NetworkDownloadRequest)localIterator.next()).downloadRequest.cancelBySign(paramObject);
    }
  }

  public void start()
  {
    stop();
    int i = 0;
    while (true)
    {
      if (i >= this.mDispatchers.length)
        return;
      DownloadDispatch localDownloadDispatch = new DownloadDispatch(this.mDownloadQueue, this.mDownloader);
      this.mDispatchers[i] = localDownloadDispatch;
      localDownloadDispatch.start();
      i += 1;
    }
  }

  public void stop()
  {
    int i = 0;
    while (true)
    {
      if (i >= this.mDispatchers.length)
        return;
      if (this.mDispatchers[i] != null)
        this.mDispatchers[i].quit();
      i += 1;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.download.DownloadQueue
 * JD-Core Version:    0.6.2
 */