package com.yolanda.nohttp;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

public class RequestQueue
{
  private final BasicConnectionRest mConnectionRest;
  private RequestDispatcher[] mDispatchers;
  private final LinkedBlockingQueue<NetworkRequestor<?>> mRequestQueue = new LinkedBlockingQueue();

  public RequestQueue(BasicConnectionRest paramBasicConnectionRest, int paramInt)
  {
    this.mConnectionRest = paramBasicConnectionRest;
    this.mDispatchers = new RequestDispatcher[paramInt];
  }

  public <T> void add(int paramInt, Request<T> paramRequest, OnResponseListener<T> paramOnResponseListener)
  {
    if (!paramRequest.getAnalyzeReqeust().inQueue())
    {
      paramRequest.getAnalyzeReqeust().takeQueue(true);
      paramRequest.getAnalyzeReqeust().start();
      this.mRequestQueue.add(new NetworkRequestor(paramInt, paramRequest, paramOnResponseListener));
    }
  }

  public void cancelAll(Object paramObject)
  {
    synchronized (this.mRequestQueue)
    {
      Iterator localIterator = this.mRequestQueue.iterator();
      if (!localIterator.hasNext())
        return;
      ((NetworkRequestor)localIterator.next()).request.cancelBySign(paramObject);
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
      RequestDispatcher localRequestDispatcher = new RequestDispatcher(this.mRequestQueue, this.mConnectionRest);
      this.mDispatchers[i] = localRequestDispatcher;
      localRequestDispatcher.start();
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
 * Qualified Name:     com.yolanda.nohttp.RequestQueue
 * JD-Core Version:    0.6.2
 */