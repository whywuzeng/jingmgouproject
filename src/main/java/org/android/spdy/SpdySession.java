package org.android.spdy;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.concurrent.atomic.AtomicBoolean;

public final class SpdySession
{
  private static volatile int count = 0;
  private SpdyAgent agent;
  private String authority;
  private AtomicBoolean closed = new AtomicBoolean();
  private Handler handler;
  Intenalcb intenalcb;
  private Object lock = new Object();
  SessionCb sessionCallBack = null;
  private boolean sessionClearedFromSessionMgr = false;
  private volatile long sessionNativePtr;
  private NetSparseArray<SpdyStreamContext> spdyStream = null;
  private int streamcount = 1;
  private HandlerThread thread;

  SpdySession(long paramLong, SpdyAgent paramSpdyAgent, String paramString, SessionCb paramSessionCb)
  {
    this.sessionNativePtr = paramLong;
    this.agent = paramSpdyAgent;
    this.authority = paramString;
    this.intenalcb = new a();
    this.spdyStream = new NetSparseArray(5);
    this.sessionCallBack = paramSessionCb;
    this.closed.set(false);
  }

  private int closeprivate()
  {
    synchronized (this.lock)
    {
      if (!this.sessionClearedFromSessionMgr)
      {
        this.agent.clearSpdySession(this.authority);
        this.sessionClearedFromSessionMgr = true;
      }
      this.sessionNativePtr = 0L;
    }
    synchronized (this.lock)
    {
      this.spdyStream.clear();
      return 0;
      localObject2 = finally;
      throw localObject2;
    }
  }

  private native int sendCustomControlFrameN(long paramLong, int paramInt1, int paramInt2, int paramInt3, int paramInt4, byte[] paramArrayOfByte);

  private native int sendHeadersN(long paramLong, int paramInt, String[] paramArrayOfString, boolean paramBoolean);

  private native int setOptionN(long paramLong, int paramInt1, int paramInt2);

  private native int streamCloseN(long paramLong, int paramInt1, int paramInt2);

  private native int streamSendDataN(long paramLong, int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3, boolean paramBoolean);

  private native int submitPingN(long paramLong);

  private native int submitRequestN(long paramLong, String paramString, byte paramByte, String[] paramArrayOfString, byte[] paramArrayOfByte, boolean paramBoolean, int paramInt1, int paramInt2);

  public int cleanUp()
  {
    spduLog.Logd("tnet-jni", "[SpdySession.cleanUp] - ");
    int i = 0;
    if (!this.closed.getAndSet(true))
    {
      this.agent.removeSession(this);
      i = closeprivate();
    }
    return i;
  }

  public void clearAllStreamCb()
  {
    spduLog.Logd("tnet-jni", "[SpdySession.clearAllStreamCb] - ");
    synchronized (this.lock)
    {
      this.spdyStream.clear();
      return;
    }
  }

  int closeInternal()
  {
    int i = 0;
    if (!this.closed.getAndSet(true))
      i = closeprivate();
    return i;
  }

  public int closeSession()
  {
    spduLog.Logd("tnet-jni", "[SpdySession.closeSession] - ");
    while (true)
    {
      synchronized (this.lock)
      {
        if (!this.sessionClearedFromSessionMgr)
        {
          spduLog.Logd("tnet-jni", "[SpdySession.closeSession] - " + this.authority);
          this.agent.clearSpdySession(this.authority);
          this.sessionClearedFromSessionMgr = true;
          try
          {
            i = this.agent.closeSession(this.sessionNativePtr);
            return i;
          }
          catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
          {
            localUnsatisfiedLinkError.printStackTrace();
          }
        }
      }
      int i = 0;
    }
  }

  public SpdyStreamContext[] getAllStreamCb()
  {
    SpdyStreamContext[] arrayOfSpdyStreamContext = null;
    synchronized (this.lock)
    {
      int i = this.spdyStream.size();
      if (i > 0)
      {
        arrayOfSpdyStreamContext = new SpdyStreamContext[i];
        this.spdyStream.toArray(arrayOfSpdyStreamContext);
      }
      return arrayOfSpdyStreamContext;
    }
  }

  Handler getMsgHandler()
  {
    return this.handler;
  }

  long getSessionNativePtr()
  {
    return this.sessionNativePtr;
  }

  SpdyAgent getSpdyAgent()
  {
    return this.agent;
  }

  SpdyStreamContext getSpdyStream(int paramInt)
  {
    if (paramInt > 0)
      synchronized (this.lock)
      {
        SpdyStreamContext localSpdyStreamContext = (SpdyStreamContext)this.spdyStream.get(paramInt);
        return localSpdyStreamContext;
      }
    return null;
  }

  int putSpdyStreamCtx(SpdyStreamContext paramSpdyStreamContext)
  {
    synchronized (this.lock)
    {
      int i = this.streamcount;
      this.streamcount = (i + 1);
      this.spdyStream.put(i, paramSpdyStreamContext);
      return i;
    }
  }

  void removeSpdyStream(int paramInt)
  {
    if (paramInt > 0)
      synchronized (this.lock)
      {
        this.spdyStream.remove(paramInt);
        return;
      }
  }

  public int sendCustomControlFrame(int paramInt1, int paramInt2, int paramInt3, int paramInt4, byte[] paramArrayOfByte)
    throws SpdyErrorException
  {
    sessionIsOpen();
    if ((paramArrayOfByte != null) && (paramArrayOfByte.length <= 0))
      paramArrayOfByte = null;
    while (true)
    {
      spduLog.Logi("tnet-jni", "[sendCustomControlFrame] - type: " + paramInt2);
      paramInt1 = sendCustomControlFrameN(this.sessionNativePtr, paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfByte);
      if (paramInt1 != 0)
        throw new SpdyErrorException("sendCustomControlFrame error: " + paramInt1, paramInt1);
      return paramInt1;
    }
  }

  void sessionIsOpen()
  {
    if (this.closed.get())
      throw new SpdyErrorException("submitPing error: -1104", -1104);
  }

  public int setOption(int paramInt1, int paramInt2)
    throws SpdyErrorException
  {
    sessionIsOpen();
    paramInt1 = setOptionN(this.sessionNativePtr, paramInt1, paramInt2);
    if (paramInt1 != 0)
      throw new SpdyErrorException("setOption error: " + paramInt1, paramInt1);
    return paramInt1;
  }

  void setSessionNativePtr(long paramLong)
  {
    this.sessionNativePtr = paramLong;
  }

  public int streamReset(long paramLong, int paramInt)
    throws SpdyErrorException
  {
    sessionIsOpen();
    spduLog.Logd("tnet-jni", "[SpdySession.streamReset] - ");
    paramInt = streamCloseN(this.sessionNativePtr, (int)paramLong, paramInt);
    if (paramInt != 0)
      throw new SpdyErrorException("streamReset error: " + paramInt, paramInt);
    return paramInt;
  }

  public int submitPing()
    throws SpdyErrorException
  {
    sessionIsOpen();
    int i = submitPingN(this.sessionNativePtr);
    if (i != 0)
      throw new SpdyErrorException("submitPing error: " + i, i);
    return i;
  }

  public int submitRequest(SpdyRequest paramSpdyRequest, SpdyDataProvider paramSpdyDataProvider, Object paramObject, Spdycb paramSpdycb)
    throws SpdyErrorException
  {
    if ((paramSpdyRequest == null) || (paramObject == null) || (paramSpdyRequest.getAuthority() == null))
      throw new SpdyErrorException("submitRequest error: -1102", -1102);
    sessionIsOpen();
    byte[] arrayOfByte2 = SpdyAgent.dataproviderToByteArray(paramSpdyRequest, paramSpdyDataProvider);
    byte[] arrayOfByte1 = arrayOfByte2;
    if (arrayOfByte2 != null)
    {
      arrayOfByte1 = arrayOfByte2;
      if (arrayOfByte2.length <= 0)
        arrayOfByte1 = null;
    }
    boolean bool = true;
    if (paramSpdyDataProvider != null)
      bool = paramSpdyDataProvider.finished;
    if (!paramSpdyRequest.getAuthority().equals(this.authority))
      throw new SpdyErrorException("submitPing error: -1102", -1102);
    int i = putSpdyStreamCtx(new SpdyStreamContext(paramObject, paramSpdycb));
    paramSpdyDataProvider = SpdyAgent.mapToByteArray(paramSpdyRequest.getHeaders());
    spduLog.Logi("tnet-jni", "index=" + i + "  " + "starttime=" + System.currentTimeMillis());
    int j = submitRequestN(this.sessionNativePtr, paramSpdyRequest.getUrlPath(), (byte)paramSpdyRequest.getPriority(), paramSpdyDataProvider, arrayOfByte1, bool, i, paramSpdyRequest.getRequestTimeoutMs());
    spduLog.Logi("tnet-jni", "index=" + i + "  " + " calltime=" + System.currentTimeMillis());
    if (j < 0)
      removeSpdyStream(i);
    if (j != 0)
      throw new SpdyErrorException("submitRequest error: " + j, j);
    return j;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.spdy.SpdySession
 * JD-Core Version:    0.6.2
 */