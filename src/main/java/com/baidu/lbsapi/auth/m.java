package com.baidu.lbsapi.auth;

import android.os.Handler;
import android.os.Looper;

class m extends Thread
{
  Handler a = null;
  private Object b = new Object();
  private boolean c = false;
  private boolean d = true;

  m()
  {
  }

  m(String paramString)
  {
    super(paramString);
  }

  public void a()
  {
    if (a.a)
      a.a("Looper thread quit()");
    this.a.getLooper().quit();
  }

  public void b()
  {
    synchronized (this.b)
    {
      try
      {
        if (!this.c)
          this.b.wait();
        return;
      }
      catch (InterruptedException localInterruptedException)
      {
        while (true)
          localInterruptedException.printStackTrace();
      }
    }
  }

  public void c()
  {
    synchronized (this.b)
    {
      this.c = true;
      this.b.notifyAll();
      return;
    }
  }

  public void run()
  {
    Looper.prepare();
    this.a = new Handler();
    if (a.a)
      a.a("new Handler() finish!!");
    Looper.loop();
    if (a.a)
      a.a("LooperThread run() thread id:" + String.valueOf(Thread.currentThread().getId()));
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.lbsapi.auth.m
 * JD-Core Version:    0.6.2
 */