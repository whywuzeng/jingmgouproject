package com.baidu.location.e;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Handler;
import com.baidu.location.b.b;
import com.baidu.location.b.k;
import com.baidu.location.b.o;
import com.baidu.location.c.d;

public class i
  implements com.baidu.location.b.f
{
  private static i hv = null;
  private boolean hA = false;
  private boolean hB = false;
  private a hC = null;
  private boolean hw = false;
  private boolean hx = true;
  final Handler hy = new Handler();
  private boolean hz = false;

  private void b1()
  {
    Object localObject = NetworkInfo.State.UNKNOWN;
    try
    {
      NetworkInfo.State localState = ((ConnectivityManager)com.baidu.location.f.getServiceContext().getSystemService("connectivity")).getNetworkInfo(1).getState();
      localObject = localState;
      label25: if (NetworkInfo.State.CONNECTED == localObject)
        if (this.hA)
          return;
    }
    catch (Exception localException)
    {
      break label25;
      this.hA = true;
      this.hy.postDelayed(new b(null), k.cy);
      this.hB = true;
      return;
    }
    this.hA = false;
  }

  public static i bY()
  {
    if (hv == null)
      hv = new i();
    return hv;
  }

  // ERROR //
  public void b0()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 46	com/baidu/location/e/i:hw	Z
    //   6: istore_1
    //   7: iload_1
    //   8: ifne +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: invokestatic 66	com/baidu/location/f:getServiceContext	()Landroid/content/Context;
    //   17: aload_0
    //   18: getfield 36	com/baidu/location/e/i:hC	Lcom/baidu/location/e/i$a;
    //   21: invokevirtual 118	android/content/Context:unregisterReceiver	(Landroid/content/BroadcastReceiver;)V
    //   24: aload_0
    //   25: iconst_0
    //   26: putfield 44	com/baidu/location/e/i:hx	Z
    //   29: aload_0
    //   30: iconst_0
    //   31: putfield 46	com/baidu/location/e/i:hw	Z
    //   34: aload_0
    //   35: aconst_null
    //   36: putfield 36	com/baidu/location/e/i:hC	Lcom/baidu/location/e/i$a;
    //   39: goto -28 -> 11
    //   42: astore_2
    //   43: aload_0
    //   44: monitorexit
    //   45: aload_2
    //   46: athrow
    //   47: astore_2
    //   48: goto -24 -> 24
    //
    // Exception table:
    //   from	to	target	type
    //   2	7	42	finally
    //   14	24	42	finally
    //   24	39	42	finally
    //   14	24	47	java/lang/Exception
  }

  public void bV()
  {
    this.hx = false;
  }

  public void bW()
  {
    if (!this.hw);
    do
    {
      return;
      this.hx = true;
    }
    while ((this.hB) || (!this.hx));
    this.hy.postDelayed(new b(null), k.cy);
    this.hB = true;
  }

  // ERROR //
  public void bX()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic 124	com/baidu/location/f:isServing	Z
    //   5: istore_1
    //   6: iload_1
    //   7: ifne +6 -> 13
    //   10: aload_0
    //   11: monitorexit
    //   12: return
    //   13: aload_0
    //   14: getfield 46	com/baidu/location/e/i:hw	Z
    //   17: istore_1
    //   18: iload_1
    //   19: ifne -9 -> 10
    //   22: aload_0
    //   23: new 10	com/baidu/location/e/i$a
    //   26: dup
    //   27: aload_0
    //   28: aconst_null
    //   29: invokespecial 125	com/baidu/location/e/i$a:<init>	(Lcom/baidu/location/e/i;Lcom/baidu/location/e/i$1;)V
    //   32: putfield 36	com/baidu/location/e/i:hC	Lcom/baidu/location/e/i$a;
    //   35: new 127	android/content/IntentFilter
    //   38: dup
    //   39: invokespecial 128	android/content/IntentFilter:<init>	()V
    //   42: astore_2
    //   43: aload_2
    //   44: ldc 130
    //   46: invokevirtual 134	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   49: invokestatic 66	com/baidu/location/f:getServiceContext	()Landroid/content/Context;
    //   52: aload_0
    //   53: getfield 36	com/baidu/location/e/i:hC	Lcom/baidu/location/e/i$a;
    //   56: aload_2
    //   57: invokevirtual 138	android/content/Context:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   60: pop
    //   61: aload_0
    //   62: iconst_1
    //   63: putfield 40	com/baidu/location/e/i:hz	Z
    //   66: aload_0
    //   67: invokespecial 111	com/baidu/location/e/i:b1	()V
    //   70: aload_0
    //   71: iconst_1
    //   72: putfield 44	com/baidu/location/e/i:hx	Z
    //   75: aload_0
    //   76: iconst_1
    //   77: putfield 46	com/baidu/location/e/i:hw	Z
    //   80: goto -70 -> 10
    //   83: astore_2
    //   84: aload_0
    //   85: monitorexit
    //   86: aload_2
    //   87: athrow
    //   88: astore_2
    //   89: goto -19 -> 70
    //
    // Exception table:
    //   from	to	target	type
    //   2	6	83	finally
    //   13	18	83	finally
    //   22	70	83	finally
    //   70	80	83	finally
    //   22	70	88	java/lang/Exception
  }

  public void bZ()
  {
    if (this.hC == null)
      this.hC = new a(null);
    try
    {
      if (!this.hz)
      {
        IntentFilter localIntentFilter = new IntentFilter();
        localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        com.baidu.location.f.getServiceContext().registerReceiver(this.hC, localIntentFilter);
        b1();
        this.hz = true;
      }
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private class a extends BroadcastReceiver
  {
    private a()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if ((paramContext == null) || (i.this.hy == null))
        return;
      i.jdMethod_for(i.this);
    }
  }

  private class b
    implements b, Runnable
  {
    private b()
    {
    }

    public void run()
    {
      if (!i.jdMethod_if(i.this))
        return;
      if ((i.jdMethod_do(i.this)) && (e.bx().bv()) && (d.jdMethod_try().jdMethod_long()))
        new v(this).start();
      if ((i.jdMethod_do(i.this)) && (e.bx().bv()))
        o.aY().aW();
      if ((i.jdMethod_do(i.this)) && (i.jdMethod_if(i.this)))
      {
        i.this.hy.postDelayed(this, k.cy);
        i.jdMethod_if(i.this, true);
        return;
      }
      i.jdMethod_if(i.this, false);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.e.i
 * JD-Core Version:    0.6.2
 */