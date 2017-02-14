package com.squareup.okhttp;

import com.squareup.okhttp.internal.Platform;
import com.squareup.okhttp.internal.Util;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class ConnectionPool
{
  private static final long DEFAULT_KEEP_ALIVE_DURATION_MS = 300000L;
  private static final ConnectionPool systemDefault = new ConnectionPool(5, l);
  private final LinkedList<Connection> connections = new LinkedList();
  private final Runnable connectionsCleanupRunnable = new Runnable()
  {
    public void run()
    {
      ConnectionPool.this.runCleanupUntilPoolIsEmpty();
    }
  };
  private Executor executor = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory("OkHttp ConnectionPool", true));
  private final long keepAliveDurationNs;
  private final int maxIdleConnections;

  static
  {
    String str1 = System.getProperty("http.keepAlive");
    String str2 = System.getProperty("http.keepAliveDuration");
    String str3 = System.getProperty("http.maxConnections");
    if (str2 != null);
    for (long l = Long.parseLong(str2); (str1 != null) && (!Boolean.parseBoolean(str1)); l = 300000L)
    {
      systemDefault = new ConnectionPool(0, l);
      return;
    }
    if (str3 != null)
    {
      systemDefault = new ConnectionPool(Integer.parseInt(str3), l);
      return;
    }
  }

  public ConnectionPool(int paramInt, long paramLong)
  {
    this.maxIdleConnections = paramInt;
    this.keepAliveDurationNs = (paramLong * 1000L * 1000L);
  }

  private void addConnection(Connection paramConnection)
  {
    boolean bool = this.connections.isEmpty();
    this.connections.addFirst(paramConnection);
    if (bool)
    {
      this.executor.execute(this.connectionsCleanupRunnable);
      return;
    }
    notifyAll();
  }

  public static ConnectionPool getDefault()
  {
    return systemDefault;
  }

  private void runCleanupUntilPoolIsEmpty()
  {
    while (performCleanup());
  }

  public void evictAll()
  {
    try
    {
      ArrayList localArrayList = new ArrayList(this.connections);
      this.connections.clear();
      notifyAll();
      int i = 0;
      int j = localArrayList.size();
      while (i < j)
      {
        Util.closeQuietly(((Connection)localArrayList.get(i)).getSocket());
        i += 1;
      }
    }
    finally
    {
    }
  }

  public Connection get(Address paramAddress)
  {
    Object localObject2 = null;
    try
    {
      ListIterator localListIterator = this.connections.listIterator(this.connections.size());
      while (true)
      {
        Object localObject1 = localObject2;
        if (localListIterator.hasPrevious())
        {
          localObject1 = (Connection)localListIterator.previous();
          if ((((Connection)localObject1).getRoute().getAddress().equals(paramAddress)) && (((Connection)localObject1).isAlive()) && (System.nanoTime() - ((Connection)localObject1).getIdleStartTimeNs() < this.keepAliveDurationNs))
          {
            localListIterator.remove();
            boolean bool = ((Connection)localObject1).isFramed();
            if (bool);
          }
        }
        else
        {
          try
          {
            Platform.get().tagSocket(((Connection)localObject1).getSocket());
            if ((localObject1 != null) && (((Connection)localObject1).isFramed()))
              this.connections.addFirst(localObject1);
            return localObject1;
          }
          catch (SocketException localSocketException)
          {
            Util.closeQuietly(((Connection)localObject1).getSocket());
            Platform.get().logW("Unable to tagSocket(): " + localSocketException);
          }
        }
      }
    }
    finally
    {
    }
    throw paramAddress;
  }

  public int getConnectionCount()
  {
    try
    {
      int i = this.connections.size();
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  List<Connection> getConnections()
  {
    try
    {
      ArrayList localArrayList = new ArrayList(this.connections);
      return localArrayList;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int getHttpConnectionCount()
  {
    try
    {
      int i = this.connections.size();
      int j = getMultiplexedConnectionCount();
      return i - j;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int getMultiplexedConnectionCount()
  {
    int i = 0;
    try
    {
      Iterator localIterator = this.connections.iterator();
      while (localIterator.hasNext())
      {
        boolean bool = ((Connection)localIterator.next()).isFramed();
        if (bool)
          i += 1;
      }
      return i;
    }
    finally
    {
    }
  }

  @Deprecated
  public int getSpdyConnectionCount()
  {
    try
    {
      int i = getMultiplexedConnectionCount();
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  boolean performCleanup()
  {
    int i;
    long l2;
    long l1;
    Connection localConnection;
    while (true)
    {
      long l3;
      try
      {
        if (this.connections.isEmpty())
          return false;
        ArrayList localArrayList = new ArrayList();
        i = 0;
        l2 = System.nanoTime();
        l1 = this.keepAliveDurationNs;
        localListIterator = this.connections.listIterator(this.connections.size());
        if (!localListIterator.hasPrevious())
          break;
        localConnection = (Connection)localListIterator.previous();
        l3 = localConnection.getIdleStartTimeNs() + this.keepAliveDurationNs - l2;
        if ((l3 <= 0L) || (!localConnection.isAlive()))
        {
          localListIterator.remove();
          localArrayList.add(localConnection);
          continue;
        }
      }
      finally
      {
      }
      if (localConnection.isIdle())
      {
        i += 1;
        l1 = Math.min(l1, l3);
      }
    }
    ListIterator localListIterator = this.connections.listIterator(this.connections.size());
    while ((localListIterator.hasPrevious()) && (i > this.maxIdleConnections))
    {
      localConnection = (Connection)localListIterator.previous();
      if (localConnection.isIdle())
      {
        localObject.add(localConnection);
        localListIterator.remove();
        i -= 1;
      }
    }
    boolean bool = localObject.isEmpty();
    if (bool);
    try
    {
      l2 = l1 / 1000000L;
      wait(l2, (int)(l1 - 1000000L * l2));
      return true;
      label272: i = 0;
      int j = localObject.size();
      while (i < j)
      {
        Util.closeQuietly(((Connection)localObject.get(i)).getSocket());
        i += 1;
      }
      return true;
    }
    catch (InterruptedException localInterruptedException)
    {
      break label272;
    }
  }

  void recycle(Connection paramConnection)
  {
    if (paramConnection.isFramed());
    while (!paramConnection.clearOwner())
      return;
    if (!paramConnection.isAlive())
    {
      Util.closeQuietly(paramConnection.getSocket());
      return;
    }
    try
    {
      Platform.get().untagSocket(paramConnection.getSocket());
      try
      {
        addConnection(paramConnection);
        paramConnection.incrementRecycleCount();
        paramConnection.resetIdleStartTime();
        return;
      }
      finally
      {
      }
    }
    catch (SocketException localSocketException)
    {
      Platform.get().logW("Unable to untagSocket(): " + localSocketException);
      Util.closeQuietly(paramConnection.getSocket());
    }
  }

  void replaceCleanupExecutorForTests(Executor paramExecutor)
  {
    this.executor = paramExecutor;
  }

  void share(Connection paramConnection)
  {
    if (!paramConnection.isFramed())
      throw new IllegalArgumentException();
    if (!paramConnection.isAlive())
      return;
    try
    {
      addConnection(paramConnection);
      return;
    }
    finally
    {
    }
    throw paramConnection;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.ConnectionPool
 * JD-Core Version:    0.6.2
 */