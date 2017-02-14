package com.yolanda.nohttp;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import java.util.concurrent.BlockingQueue;

public class RequestDispatcher extends Thread
{
  private static final Object HANDLER_LOCK = new Object();
  private static Handler sRequestHandler;
  private final BasicConnectionRest mConnectionRest;
  private volatile boolean mQuit = false;
  private final BlockingQueue<NetworkRequestor<?>> mRequestQueue;

  public RequestDispatcher(BlockingQueue<NetworkRequestor<?>> paramBlockingQueue, BasicConnectionRest paramBasicConnectionRest)
  {
    this.mRequestQueue = paramBlockingQueue;
    this.mConnectionRest = paramBasicConnectionRest;
  }

  private Handler getPosterHandler()
  {
    synchronized (HANDLER_LOCK)
    {
      if (sRequestHandler == null)
        sRequestHandler = new Handler(Looper.getMainLooper());
      return sRequestHandler;
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
    while (true)
    {
      ThreadPoster localThreadPoster;
      Response localResponse;
      try
      {
        NetworkRequestor localNetworkRequestor = (NetworkRequestor)this.mRequestQueue.take();
        if (localNetworkRequestor.request.isCanceled())
          continue;
        localThreadPoster = new ThreadPoster(localNetworkRequestor.what, localNetworkRequestor.responseListener);
        localThreadPoster.onStart();
        getPosterHandler().post(localThreadPoster);
        localThreadPoster = new ThreadPoster(localNetworkRequestor.what, localNetworkRequestor.responseListener);
        localResponse = this.mConnectionRest.request(localNetworkRequestor.request);
        if (!localNetworkRequestor.request.isCanceled())
          break label143;
        localThreadPoster.onFinished();
        localNetworkRequestor.request.getAnalyzeReqeust().takeQueue(false);
        getPosterHandler().post(localThreadPoster);
        continue;
      }
      catch (InterruptedException localInterruptedException)
      {
      }
      if (this.mQuit)
      {
        return;
        label143: localThreadPoster.onResponse(localResponse);
      }
    }
  }

  public class ThreadPoster
    implements Runnable
  {
    public static final int COMMAND_FINISH = 2;
    public static final int COMMAND_RESPONSE = 1;
    public static final int COMMAND_START = 0;
    private int command;
    private Response response;
    private final OnResponseListener responseListener;
    private final int what;

    public ThreadPoster(OnResponseListener<?> arg2)
    {
      int i;
      this.what = i;
      Object localObject;
      this.responseListener = localObject;
    }

    public void onFinished()
    {
      this.command = 2;
    }

    public void onResponse(Response paramResponse)
    {
      this.command = 1;
      this.response = paramResponse;
    }

    public void onStart()
    {
      this.command = 0;
    }

    public void run()
    {
      if (this.responseListener != null)
      {
        if (this.command != 0)
          break label28;
        this.responseListener.onStart(this.what);
      }
      label28: 
      do
      {
        return;
        if (this.command == 2)
        {
          this.responseListener.onFinish(this.what);
          return;
        }
        if ((this.command == 1) && (this.response != null))
        {
          this.responseListener.onFinish(this.what);
          if (this.response.isSucceed())
          {
            this.responseListener.onSucceed(this.what, this.response);
            return;
          }
          this.responseListener.onFailed(this.what, this.response.url(), this.response.getTag(), this.response.getErrorMessage());
          return;
        }
      }
      while (this.response != null);
      this.responseListener.onFinish(this.what);
      this.responseListener.onFailed(this.what, null, null, null);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.RequestDispatcher
 * JD-Core Version:    0.6.2
 */