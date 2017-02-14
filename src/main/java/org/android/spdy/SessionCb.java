package org.android.spdy;

public abstract interface SessionCb
{
  public abstract void spdyCustomControlFrameRecvCallback(SpdySession paramSpdySession, Object paramObject, int paramInt1, int paramInt2, int paramInt3, int paramInt4, byte[] paramArrayOfByte);

  public abstract void spdyPingRecvCallback(SpdySession paramSpdySession, long paramLong, Object paramObject);

  public abstract void spdySessionCloseCallback(SpdySession paramSpdySession, Object paramObject, SuperviseConnectInfo paramSuperviseConnectInfo, int paramInt);

  public abstract void spdySessionConnectCB(SpdySession paramSpdySession, SuperviseConnectInfo paramSuperviseConnectInfo);

  public abstract void spdySessionFailedError(SpdySession paramSpdySession, int paramInt, Object paramObject);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.spdy.SessionCb
 * JD-Core Version:    0.6.2
 */