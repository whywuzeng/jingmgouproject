package com.squareup.okhttp;

import com.squareup.okhttp.internal.Util;
import com.squareup.okhttp.internal.http.HttpEngine;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class Dispatcher
{
  private final Deque<Call> executedCalls = new ArrayDeque();
  private ExecutorService executorService;
  private int maxRequests = 64;
  private int maxRequestsPerHost = 5;
  private final Deque<Call.AsyncCall> readyCalls = new ArrayDeque();
  private final Deque<Call.AsyncCall> runningCalls = new ArrayDeque();

  public Dispatcher()
  {
  }

  public Dispatcher(ExecutorService paramExecutorService)
  {
    this.executorService = paramExecutorService;
  }

  private void promoteCalls()
  {
    if (this.runningCalls.size() >= this.maxRequests);
    do
    {
      Iterator localIterator;
      do
      {
        return;
        while (this.readyCalls.isEmpty());
        localIterator = this.readyCalls.iterator();
      }
      while (!localIterator.hasNext());
      Call.AsyncCall localAsyncCall = (Call.AsyncCall)localIterator.next();
      if (runningCallsForHost(localAsyncCall) < this.maxRequestsPerHost)
      {
        localIterator.remove();
        this.runningCalls.add(localAsyncCall);
        getExecutorService().execute(localAsyncCall);
      }
    }
    while (this.runningCalls.size() < this.maxRequests);
  }

  private int runningCallsForHost(Call.AsyncCall paramAsyncCall)
  {
    int i = 0;
    Iterator localIterator = this.runningCalls.iterator();
    while (localIterator.hasNext())
      if (((Call.AsyncCall)localIterator.next()).host().equals(paramAsyncCall.host()))
        i += 1;
    return i;
  }

  public void cancel(Object paramObject)
  {
    Object localObject;
    try
    {
      localIterator = this.readyCalls.iterator();
      while (localIterator.hasNext())
      {
        localObject = (Call.AsyncCall)localIterator.next();
        if (Util.equal(paramObject, ((Call.AsyncCall)localObject).tag()))
          ((Call.AsyncCall)localObject).cancel();
      }
    }
    finally
    {
    }
    Iterator localIterator = this.runningCalls.iterator();
    while (localIterator.hasNext())
    {
      localObject = (Call.AsyncCall)localIterator.next();
      if (Util.equal(paramObject, ((Call.AsyncCall)localObject).tag()))
      {
        ((Call.AsyncCall)localObject).get().canceled = true;
        localObject = ((Call.AsyncCall)localObject).get().engine;
        if (localObject != null)
          ((HttpEngine)localObject).disconnect();
      }
    }
    localIterator = this.executedCalls.iterator();
    while (localIterator.hasNext())
    {
      localObject = (Call)localIterator.next();
      if (Util.equal(paramObject, ((Call)localObject).tag()))
        ((Call)localObject).cancel();
    }
  }

  void enqueue(Call.AsyncCall paramAsyncCall)
  {
    try
    {
      if ((this.runningCalls.size() < this.maxRequests) && (runningCallsForHost(paramAsyncCall) < this.maxRequestsPerHost))
      {
        this.runningCalls.add(paramAsyncCall);
        getExecutorService().execute(paramAsyncCall);
      }
      while (true)
      {
        return;
        this.readyCalls.add(paramAsyncCall);
      }
    }
    finally
    {
    }
    throw paramAsyncCall;
  }

  void executed(Call paramCall)
  {
    try
    {
      this.executedCalls.add(paramCall);
      return;
    }
    finally
    {
      paramCall = finally;
    }
    throw paramCall;
  }

  void finished(Call.AsyncCall paramAsyncCall)
  {
    try
    {
      if (!this.runningCalls.remove(paramAsyncCall))
        throw new AssertionError("AsyncCall wasn't running!");
    }
    finally
    {
    }
    promoteCalls();
  }

  void finished(Call paramCall)
  {
    try
    {
      if (!this.executedCalls.remove(paramCall))
        throw new AssertionError("Call wasn't in-flight!");
    }
    finally
    {
    }
  }

  public ExecutorService getExecutorService()
  {
    try
    {
      if (this.executorService == null)
        this.executorService = new ThreadPoolExecutor(0, 2147483647, 60L, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp Dispatcher", false));
      ExecutorService localExecutorService = this.executorService;
      return localExecutorService;
    }
    finally
    {
    }
  }

  public int getMaxRequests()
  {
    try
    {
      int i = this.maxRequests;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int getMaxRequestsPerHost()
  {
    try
    {
      int i = this.maxRequestsPerHost;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int getQueuedCallCount()
  {
    try
    {
      int i = this.readyCalls.size();
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int getRunningCallCount()
  {
    try
    {
      int i = this.runningCalls.size();
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setMaxRequests(int paramInt)
  {
    if (paramInt < 1)
      try
      {
        throw new IllegalArgumentException("max < 1: " + paramInt);
      }
      finally
      {
      }
    this.maxRequests = paramInt;
    promoteCalls();
  }

  public void setMaxRequestsPerHost(int paramInt)
  {
    if (paramInt < 1)
      try
      {
        throw new IllegalArgumentException("max < 1: " + paramInt);
      }
      finally
      {
      }
    this.maxRequestsPerHost = paramInt;
    promoteCalls();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.Dispatcher
 * JD-Core Version:    0.6.2
 */