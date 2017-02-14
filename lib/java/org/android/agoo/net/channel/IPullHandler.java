package org.android.agoo.net.channel;

import java.util.Map;

public abstract interface IPullHandler
{
  public abstract void onResponse(Object paramObject, String paramString, int paramInt, Map<String, String> paramMap, byte[] paramArrayOfByte);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.channel.IPullHandler
 * JD-Core Version:    0.6.2
 */