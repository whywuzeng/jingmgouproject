package org.android.spdy;

import java.util.List;
import java.util.Map;

final class a
  implements Intenalcb
{
  public void spdyCustomControlFrameRecvCallback(SpdySession paramSpdySession, Object paramObject, int paramInt1, int paramInt2, int paramInt3, int paramInt4, byte[] paramArrayOfByte)
  {
    spduLog.Logi("tnet-jni", "[SpdySessionCallBack.spdyCustomControlFrameRecvCallback] - " + paramSpdySession);
    if (paramSpdySession.sessionCallBack != null)
    {
      paramSpdySession.sessionCallBack.spdyCustomControlFrameRecvCallback(paramSpdySession, paramObject, paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfByte);
      return;
    }
    spduLog.Loge("tnet-jni", "[SpdySessionCallBack.spdyCustomControlFrameRecvCallback] - no sessionCallBack.");
  }

  public void spdyDataChunkRecvCB(SpdySession paramSpdySession, boolean paramBoolean, long paramLong, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    spduLog.Logi("tnet-jni", "[SpdySessionCallBack.spdyDataChunkRecvCB] - " + paramSpdySession);
    long l = NetTimeGaurd.begin();
    SpdyStreamContext localSpdyStreamContext = paramSpdySession.getSpdyStream(paramInt2);
    if ((localSpdyStreamContext != null) && (localSpdyStreamContext.callBack != null))
      localSpdyStreamContext.callBack.spdyDataChunkRecvCB(paramSpdySession, paramBoolean, paramLong, paramArrayOfByte, paramInt1, localSpdyStreamContext.streamContext);
    while (true)
    {
      NetTimeGaurd.end("spdyDataChunkRecvCB", 3, l);
      return;
      spduLog.Loge("tnet-jni", "[SpdySessionCallBack.spdyDataChunkRecvCB] - no sessionCallBack.");
    }
  }

  public void spdyDataRecvCallback(SpdySession paramSpdySession, boolean paramBoolean, long paramLong, int paramInt1, int paramInt2)
  {
    spduLog.Logi("tnet-jni", "[SpdySessionCallBack.spdyDataRecvCallback] - " + paramSpdySession);
    long l = NetTimeGaurd.begin();
    SpdyStreamContext localSpdyStreamContext = paramSpdySession.getSpdyStream(paramInt2);
    if ((localSpdyStreamContext != null) && (localSpdyStreamContext.callBack != null))
      localSpdyStreamContext.callBack.spdyDataRecvCallback(paramSpdySession, paramBoolean, paramLong, paramInt1, localSpdyStreamContext.streamContext);
    while (true)
    {
      NetTimeGaurd.end("spdyDataRecvCallback", 3, l);
      return;
      spduLog.Loge("tnet-jni", "[SpdySessionCallBack.spdyDataRecvCallback] - no sessionCallBack.");
    }
  }

  public void spdyDataSendCallback(SpdySession paramSpdySession, boolean paramBoolean, long paramLong, int paramInt1, int paramInt2)
  {
    spduLog.Logi("tnet-jni", "[SpdySessionCallBack.spdyDataSendCallback] - ");
    SpdyStreamContext localSpdyStreamContext = paramSpdySession.getSpdyStream(paramInt2);
    if ((localSpdyStreamContext != null) && (localSpdyStreamContext.callBack != null))
    {
      localSpdyStreamContext.callBack.spdyDataSendCallback(paramSpdySession, paramBoolean, paramLong, paramInt1, localSpdyStreamContext.streamContext);
      return;
    }
    spduLog.Loge("tnet-jni", "[SpdySessionCallBack.spdyDataSendCallback] - no sessionCallBack.");
  }

  public void spdyOnStreamResponse(SpdySession paramSpdySession, long paramLong, Map<String, List<String>> paramMap, int paramInt)
  {
    spduLog.Logi("tnet-jni", "[SpdySessionCallBack.spdyOnStreamResponse] - " + paramSpdySession);
    NetTimeGaurd.start(3);
    long l = NetTimeGaurd.begin();
    SpdyStreamContext localSpdyStreamContext = paramSpdySession.getSpdyStream(paramInt);
    if ((localSpdyStreamContext != null) && (localSpdyStreamContext.callBack != null))
      localSpdyStreamContext.callBack.spdyOnStreamResponse(paramSpdySession, paramLong, paramMap, localSpdyStreamContext.streamContext);
    while (true)
    {
      NetTimeGaurd.end("spdyOnStreamResponse", 3, l);
      return;
      spduLog.Loge("tnet-jni", "[SpdySessionCallBack.spdyOnStreamResponse] - no sessionCallBack.");
    }
  }

  public void spdyPingRecvCallback(SpdySession paramSpdySession, long paramLong, Object paramObject)
  {
    spduLog.Logi("tnet-jni", "[SpdySessionCallBack.spdyPingRecvCallback] - " + paramSpdySession);
    NetTimeGaurd.start(1);
    if (paramSpdySession.sessionCallBack != null)
    {
      long l = NetTimeGaurd.begin();
      paramSpdySession.sessionCallBack.spdyPingRecvCallback(paramSpdySession, paramLong, paramObject);
      NetTimeGaurd.end("spdyPingRecvCallback", 1, l);
    }
    while (true)
    {
      NetTimeGaurd.finish(1);
      return;
      spduLog.Loge("tnet-jni", "[SpdySessionCallBack.spdyPingRecvCallback] - no sessionCallBack.");
    }
  }

  public void spdyRequestRecvCallback(SpdySession paramSpdySession, long paramLong, int paramInt)
  {
    spduLog.Logd("tnet-jni", "[SpdySessionCallBack.spdyOnStreamResponse] - " + paramSpdySession);
    long l = NetTimeGaurd.begin();
    SpdyStreamContext localSpdyStreamContext = paramSpdySession.getSpdyStream(paramInt);
    if ((localSpdyStreamContext != null) && (localSpdyStreamContext.callBack != null))
      localSpdyStreamContext.callBack.spdyRequestRecvCallback(paramSpdySession, paramLong, localSpdyStreamContext.streamContext);
    while (true)
    {
      NetTimeGaurd.end("spdyPingRecvCallback", 3, l);
      return;
      spduLog.Loge("tnet-jni", "[SpdySessionCallBack.spdyRequestRecvCallback] - no sessionCallBack.");
    }
  }

  public void spdySessionCloseCallback(SpdySession paramSpdySession, Object paramObject, SuperviseConnectInfo paramSuperviseConnectInfo, int paramInt)
  {
    spduLog.Logi("tnet-jni", "[SpdySessionCallBack.spdySessionCloseCallback] - " + paramSpdySession);
    if (paramSpdySession.sessionCallBack != null)
    {
      paramSpdySession.sessionCallBack.spdySessionCloseCallback(paramSpdySession, paramObject, paramSuperviseConnectInfo, paramInt);
      return;
    }
    spduLog.Loge("tnet-jni", "[SpdySessionCallBack.spdySessionCloseCallback] - no sessionCallBack.");
  }

  public void spdySessionConnectCB(SpdySession paramSpdySession, SuperviseConnectInfo paramSuperviseConnectInfo)
  {
    spduLog.Logi("tnet-jni", "[SpdySessionCallBack.spdySessionConnectCB] - " + paramSpdySession);
    NetTimeGaurd.start(0);
    if (paramSpdySession.sessionCallBack != null)
    {
      long l = NetTimeGaurd.begin();
      paramSpdySession.sessionCallBack.spdySessionConnectCB(paramSpdySession, paramSuperviseConnectInfo);
      NetTimeGaurd.end("spdySessionConnectCB", 0, l);
    }
    while (true)
    {
      NetTimeGaurd.finish(0);
      return;
      spduLog.Loge("tnet-jni", "[SpdySessionCallBack.spdySessionConnectCB] - no sessionCallBack.");
    }
  }

  public void spdySessionFailedError(SpdySession paramSpdySession, int paramInt, Object paramObject)
  {
    spduLog.Logi("tnet-jni", "[SpdySessionCallBack.spdySessionFailedError] - " + paramSpdySession);
    NetTimeGaurd.start(2);
    if (paramSpdySession.sessionCallBack != null)
    {
      long l = NetTimeGaurd.begin();
      paramSpdySession.sessionCallBack.spdySessionFailedError(paramSpdySession, paramInt, paramObject);
      paramSpdySession.clearAllStreamCb();
      NetTimeGaurd.end("spdySessionFailedError", 2, l);
    }
    while (true)
    {
      NetTimeGaurd.finish(2);
      return;
      spduLog.Loge("tnet-jni", "[SpdySessionCallBack.spdySessionFailedError] - no sessionCallBack.");
    }
  }

  public void spdyStreamCloseCallback(SpdySession paramSpdySession, long paramLong, int paramInt1, int paramInt2, SuperviseData paramSuperviseData)
  {
    spduLog.Logi("tnet-jni", "[SpdySessionCallBack.spdyStreamCloseCallback] - " + paramSpdySession);
    long l = NetTimeGaurd.begin();
    SpdyStreamContext localSpdyStreamContext = paramSpdySession.getSpdyStream(paramInt2);
    if ((localSpdyStreamContext != null) && (localSpdyStreamContext.callBack != null))
    {
      spduLog.Logi("tnet-jni", "index=" + paramInt2 + "    " + "endtime=" + System.currentTimeMillis());
      localSpdyStreamContext.callBack.spdyStreamCloseCallback(paramSpdySession, paramLong, paramInt1, localSpdyStreamContext.streamContext, paramSuperviseData);
      paramSpdySession.removeSpdyStream(paramInt2);
    }
    while (true)
    {
      NetTimeGaurd.end("spdyStreamCloseCallback", 3, l);
      NetTimeGaurd.finish(3);
      return;
      spduLog.Loge("tnet-jni", "[SpdySessionCallBack.spdyStreamCloseCallback] - no sessionCallBack.");
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.spdy.a
 * JD-Core Version:    0.6.2
 */