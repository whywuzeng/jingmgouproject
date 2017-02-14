package org.android.agoo.net.channel;

import com.umeng.message.proguard.bm;
import com.umeng.message.proguard.bn;
import com.umeng.message.proguard.bo;
import java.util.Map;

public abstract interface IPushHandler
{
  public abstract void onCommand(Object paramObject, long paramLong, String paramString, byte[] paramArrayOfByte);

  public abstract void onConnected(Object paramObject, long paramLong1, long paramLong2, Map<String, String> paramMap, bm parambm);

  public abstract void onData(Object paramObject, long paramLong, String paramString, byte[] paramArrayOfByte, bo parambo);

  public abstract void onDisconnected(Object paramObject, long paramLong, bm parambm);

  public abstract void onError(Object paramObject, long paramLong, ChannelError paramChannelError, Map<String, String> paramMap, Throwable paramThrowable, bm parambm);

  public abstract void onPing(Object paramObject, long paramLong);

  public abstract void onReportDNS(bn parambn);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.channel.IPushHandler
 * JD-Core Version:    0.6.2
 */