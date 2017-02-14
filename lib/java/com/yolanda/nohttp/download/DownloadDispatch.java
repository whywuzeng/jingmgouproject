package com.yolanda.nohttp.download;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import com.yolanda.nohttp.Headers;
import java.util.concurrent.BlockingQueue;

class DownloadDispatch extends Thread
{
  private static final Object HANDLER_LOCK = new Object();
  private static Handler sDownloadHandler;
  private final BlockingQueue<NetworkDownloadRequest> mDownloadQueue;
  private final Downloader mDownloader;
  private volatile boolean mQuit = false;

  public DownloadDispatch(BlockingQueue<NetworkDownloadRequest> paramBlockingQueue, Downloader paramDownloader)
  {
    this.mDownloadQueue = paramBlockingQueue;
    this.mDownloader = paramDownloader;
  }

  private Handler getPosterHandler()
  {
    synchronized (HANDLER_LOCK)
    {
      if (sDownloadHandler == null)
        sDownloadHandler = new Handler(Looper.getMainLooper());
      return sDownloadHandler;
    }
  }

  public void quit()
  {
    this.mQuit = true;
    interrupt();
  }

  public void run()
  {
    Process.setThreadPriority(10);
    do
      try
      {
        while (true)
        {
          final NetworkDownloadRequest localNetworkDownloadRequest = (NetworkDownloadRequest)this.mDownloadQueue.take();
          if (!localNetworkDownloadRequest.downloadRequest.isCanceled())
            this.mDownloader.download(localNetworkDownloadRequest.what, localNetworkDownloadRequest.downloadRequest, new DownloadListener()
            {
              public void onCancel(int paramAnonymousInt)
              {
                DownloadDispatch.ThreadPoster localThreadPoster = new DownloadDispatch.ThreadPoster(DownloadDispatch.this, localNetworkDownloadRequest.what, localNetworkDownloadRequest.downloadListener);
                localThreadPoster.onCancel();
                DownloadDispatch.this.getPosterHandler().post(localThreadPoster);
              }

              public void onDownloadError(int paramAnonymousInt, StatusCode paramAnonymousStatusCode, CharSequence paramAnonymousCharSequence)
              {
                DownloadDispatch.ThreadPoster localThreadPoster = new DownloadDispatch.ThreadPoster(DownloadDispatch.this, localNetworkDownloadRequest.what, localNetworkDownloadRequest.downloadListener);
                localThreadPoster.onError(paramAnonymousStatusCode, paramAnonymousCharSequence);
                DownloadDispatch.this.getPosterHandler().post(localThreadPoster);
              }

              public void onFinish(int paramAnonymousInt, String paramAnonymousString)
              {
                DownloadDispatch.ThreadPoster localThreadPoster = new DownloadDispatch.ThreadPoster(DownloadDispatch.this, localNetworkDownloadRequest.what, localNetworkDownloadRequest.downloadListener);
                localThreadPoster.onFinish(paramAnonymousString);
                DownloadDispatch.this.getPosterHandler().post(localThreadPoster);
              }

              public void onProgress(int paramAnonymousInt1, int paramAnonymousInt2, long paramAnonymousLong)
              {
                DownloadDispatch.ThreadPoster localThreadPoster = new DownloadDispatch.ThreadPoster(DownloadDispatch.this, localNetworkDownloadRequest.what, localNetworkDownloadRequest.downloadListener);
                localThreadPoster.onProgress(paramAnonymousInt2, paramAnonymousLong);
                DownloadDispatch.this.getPosterHandler().post(localThreadPoster);
              }

              public void onStart(int paramAnonymousInt, boolean paramAnonymousBoolean, long paramAnonymousLong1, Headers paramAnonymousHeaders, long paramAnonymousLong2)
              {
                DownloadDispatch.ThreadPoster localThreadPoster = new DownloadDispatch.ThreadPoster(DownloadDispatch.this, localNetworkDownloadRequest.what, localNetworkDownloadRequest.downloadListener);
                localThreadPoster.onStart(paramAnonymousBoolean, paramAnonymousLong1, paramAnonymousHeaders, paramAnonymousLong2);
                DownloadDispatch.this.getPosterHandler().post(localThreadPoster);
              }
            });
        }
      }
      catch (InterruptedException localInterruptedException)
      {
      }
    while (!this.mQuit);
  }

  public class ThreadPoster
    implements Runnable
  {
    public static final int COMMAND_CANCEL = 4;
    public static final int COMMAND_ERROR = 2;
    public static final int COMMAND_FINISH = 3;
    public static final int COMMAND_PROGRESS = 1;
    public static final int COMMAND_START = 0;
    private long allCount;
    private long beforeLength;
    private int command;
    private final DownloadListener downloadListener;
    private CharSequence errorMessage;
    private long fileCount;
    private String filePath;
    private boolean isResume;
    private int progress;
    private Headers responseHeaders;
    private StatusCode statusCode;
    private final int what;

    public ThreadPoster(int paramDownloadListener, DownloadListener arg3)
    {
      this.what = paramDownloadListener;
      Object localObject;
      this.downloadListener = localObject;
    }

    public void onCancel()
    {
      this.command = 4;
    }

    public void onError(StatusCode paramStatusCode, CharSequence paramCharSequence)
    {
      this.command = 2;
      this.statusCode = paramStatusCode;
      this.errorMessage = paramCharSequence;
    }

    public void onFinish(String paramString)
    {
      this.command = 3;
      this.filePath = paramString;
    }

    public void onProgress(int paramInt, long paramLong)
    {
      this.command = 1;
      this.progress = paramInt;
      this.fileCount = paramLong;
    }

    public void onStart(boolean paramBoolean, long paramLong1, Headers paramHeaders, long paramLong2)
    {
      this.command = 0;
      this.isResume = paramBoolean;
      this.beforeLength = paramLong1;
      this.responseHeaders = paramHeaders;
      this.allCount = paramLong2;
    }

    public void run()
    {
      switch (this.command)
      {
      default:
        return;
      case 0:
        this.downloadListener.onStart(this.what, this.isResume, this.beforeLength, this.responseHeaders, this.allCount);
        return;
      case 1:
        this.downloadListener.onProgress(this.what, this.progress, this.fileCount);
        return;
      case 2:
        this.downloadListener.onDownloadError(this.what, this.statusCode, this.errorMessage);
        return;
      case 3:
        this.downloadListener.onFinish(this.what, this.filePath);
        return;
      case 4:
      }
      this.downloadListener.onCancel(this.what);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.download.DownloadDispatch
 * JD-Core Version:    0.6.2
 */