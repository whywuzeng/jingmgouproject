package com.umeng.message.proguard;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.util.Map;

public class O
{
  private HandlerThread a = null;
  private Handler b = null;
  private a c = null;

  public void a()
  {
    this.a = new HandlerThread("ut");
    this.a.start();
    this.b = new Handler(this.a.getLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        if ((paramAnonymousMessage.what == 1) && (paramAnonymousMessage.obj != null))
        {
          paramAnonymousMessage = (Map)paramAnonymousMessage.obj;
          if (O.a(O.this) != null)
            O.a(O.this).b(paramAnonymousMessage);
        }
      }
    };
  }

  public void a(a parama)
  {
    this.c = parama;
  }

  public void a(Map<String, String> paramMap)
  {
    if (paramMap != null)
    {
      if (!paramMap.containsKey("_sls"))
        break label42;
      paramMap.remove("_sls");
      if (this.c != null)
        this.c.b(paramMap);
    }
    return;
    label42: Message localMessage = Message.obtain();
    localMessage.what = 1;
    localMessage.obj = paramMap;
    this.b.sendMessage(localMessage);
  }

  public static abstract interface a
  {
    public abstract void b(Map<String, String> paramMap);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.O
 * JD-Core Version:    0.6.2
 */