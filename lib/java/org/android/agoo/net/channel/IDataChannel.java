package org.android.agoo.net.channel;

import android.content.Context;
import com.umeng.message.proguard.bm;
import com.umeng.message.proguard.bo;
import java.util.Map;

public abstract interface IDataChannel
{
  public abstract void asyncDisconnect();

  public abstract void close();

  public abstract void connect(Object paramObject, Context paramContext, String paramString1, Map<String, String> paramMap, long paramLong, IPushHandler paramIPushHandler, bm parambm, String paramString2);

  public abstract long ping();

  public abstract ChannelState readyChannelState();

  public abstract int send(String paramString, byte[] paramArrayOfByte, IPullHandler paramIPullHandler, bo parambo);

  public abstract void shutdown();

  public abstract void syncDisconnect();
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.channel.IDataChannel
 * JD-Core Version:    0.6.2
 */