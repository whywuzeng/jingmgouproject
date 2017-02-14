package com.baidu.location.a;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.util.Log;
import com.baidu.location.LLSInterface;
import com.baidu.location.c.b;
import com.baidu.location.e.e;
import com.baidu.location.e.h;
import com.baidu.location.e.i;
import com.baidu.location.e.m;
import com.baidu.location.e.n;
import com.baidu.location.e.o;
import com.baidu.location.h.g;
import com.baidu.location.h.j;
import com.baidu.location.h.k;
import com.baidu.location.h.l;

public class a extends Service
  implements LLSInterface, com.baidu.location.b.f
{
  static a l3 = null;
  private static long l7 = 0L;
  private HandlerThread l2;
  private boolean l4 = false;
  Messenger l5 = null;
  private Looper l6;

  public static long dP()
  {
    return l7;
  }

  public static Handler dQ()
  {
    return l3;
  }

  private void dR()
  {
    com.baidu.location.h.c.a().cT();
    m.bb().bc();
    o.cj();
    com.baidu.location.e.c.br().bt();
    h.bK().bL();
    if (!this.l4)
      Process.killProcess(Process.myPid());
  }

  private void dS()
  {
    com.baidu.location.h.c.a().cP();
    l.a().c6();
    com.baidu.location.b.c.N();
    m.bb().bj();
    b.a0().a1();
    e.bx().bz();
  }

  private void l(Message paramMessage)
  {
    com.baidu.location.e.c.br().jdMethod_char(paramMessage);
  }

  private void m(Message paramMessage)
  {
    Log.d("baidu_location_service", "baidu location service register ...");
    com.baidu.location.e.c.br().jdMethod_long(paramMessage);
    com.baidu.location.c.d.jdMethod_try();
    com.baidu.location.e.f.bC().bF();
    com.baidu.location.e.d.aR().aQ();
  }

  private void n(Message paramMessage)
  {
    com.baidu.location.e.c.br().jdMethod_goto(paramMessage);
  }

  public double getVersion()
  {
    return 6.119999885559082D;
  }

  public IBinder onBind(Intent paramIntent)
  {
    paramIntent = paramIntent.getExtras();
    if (paramIntent != null)
    {
      com.baidu.location.b.c.bl = paramIntent.getString("key");
      com.baidu.location.b.c.bk = paramIntent.getString("sign");
      this.l4 = paramIntent.getBoolean("kill_process");
    }
    return this.l5.getBinder();
  }

  public void onCreate(Context paramContext)
  {
    l7 = System.currentTimeMillis();
    this.l2 = n.a();
    this.l6 = this.l2.getLooper();
    l3 = new a(this.l6);
    this.l5 = new Messenger(l3);
    l3.sendEmptyMessage(0);
    Log.d("baidu_location_service", "baidu location service start1 ..." + Process.myPid());
  }

  public void onDestroy()
  {
    l.a().dd();
    i.bY().b0();
    com.baidu.location.c.d.jdMethod_try().h();
    com.baidu.location.e.f.bC().bI();
    com.baidu.location.h.d.a().cG();
    e.bx().bA();
    l3.sendEmptyMessage(1);
    Log.d("baidu_location_service", "baidu location service stop ...");
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    return 1;
  }

  public boolean onUnBind(Intent paramIntent)
  {
    return false;
  }

  public class a extends Handler
  {
    public a(Looper arg2)
    {
      super();
    }

    public void handleMessage(Message paramMessage)
    {
      if (com.baidu.location.f.isServing == true)
        switch (paramMessage.what)
        {
        case 110:
        case 111:
        case 112:
        case 121:
        case 122:
        case 302:
        default:
        case 11:
        case 12:
        case 15:
        case 22:
        case 28:
        case 41:
        case 401:
        }
      while (true)
      {
        if (paramMessage.what == 1)
          a.jdMethod_if(a.this);
        if (paramMessage.what == 0)
          a.jdMethod_do(a.this);
        super.handleMessage(paramMessage);
        return;
        a.jdMethod_do(a.this, paramMessage);
        continue;
        a.jdMethod_for(a.this, paramMessage);
        continue;
        a.jdMethod_if(a.this, paramMessage);
        continue;
        m.bb().jdMethod_do(paramMessage);
        continue;
        m.bb().bd();
        continue;
        m.bb().ba();
        continue;
        try
        {
          paramMessage.getData();
        }
        catch (Exception localException)
        {
        }
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.a.a
 * JD-Core Version:    0.6.2
 */