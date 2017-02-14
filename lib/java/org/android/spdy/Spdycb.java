package org.android.spdy;

import java.util.List;
import java.util.Map;

public abstract interface Spdycb
{
  public abstract void spdyDataChunkRecvCB(SpdySession paramSpdySession, boolean paramBoolean, long paramLong, byte[] paramArrayOfByte, int paramInt, Object paramObject);

  public abstract void spdyDataRecvCallback(SpdySession paramSpdySession, boolean paramBoolean, long paramLong, int paramInt, Object paramObject);

  public abstract void spdyDataSendCallback(SpdySession paramSpdySession, boolean paramBoolean, long paramLong, int paramInt, Object paramObject);

  public abstract void spdyOnStreamResponse(SpdySession paramSpdySession, long paramLong, Map<String, List<String>> paramMap, Object paramObject);

  public abstract void spdyRequestRecvCallback(SpdySession paramSpdySession, long paramLong, Object paramObject);

  public abstract void spdyStreamCloseCallback(SpdySession paramSpdySession, long paramLong, int paramInt, Object paramObject, SuperviseData paramSuperviseData);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.spdy.Spdycb
 * JD-Core Version:    0.6.2
 */