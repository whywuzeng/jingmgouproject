package cn.sharesdk.framework;

import java.util.HashMap;

public abstract interface PlatformActionListener
{
  public abstract void onCancel(Platform paramPlatform, int paramInt);

  public abstract void onComplete(Platform paramPlatform, int paramInt, HashMap<String, Object> paramHashMap);

  public abstract void onError(Platform paramPlatform, int paramInt, Throwable paramThrowable);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.PlatformActionListener
 * JD-Core Version:    0.6.2
 */