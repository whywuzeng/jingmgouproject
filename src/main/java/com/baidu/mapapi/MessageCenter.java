package com.baidu.mapapi;

import android.os.Handler;
import com.baidu.platform.comjni.engine.a;

public class MessageCenter
{
  public static void registMessage(int paramInt, Handler paramHandler)
  {
    a.a(paramInt, paramHandler);
  }

  public static void unregistMessage(int paramInt, Handler paramHandler)
  {
    a.b(paramInt, paramHandler);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.MessageCenter
 * JD-Core Version:    0.6.2
 */