package com.baidu.location.e;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class e
  implements com.baidu.location.b.f
{
  private static e gq = null;
  private int gp = -1;
  private String gr = null;
  private a gs = null;
  private boolean gt = false;

  public static e bx()
  {
    try
    {
      if (gq == null)
        gq = new e();
      e locale = gq;
      return locale;
    }
    finally
    {
    }
  }

  public void bA()
  {
    if (this.gs != null);
    try
    {
      com.baidu.location.f.getServiceContext().unregisterReceiver(this.gs);
      label17: this.gs = null;
      return;
    }
    catch (Exception localException)
    {
      break label17;
    }
  }

  public boolean bv()
  {
    return this.gt;
  }

  public String bw()
  {
    return this.gr;
  }

  public int by()
  {
    return this.gp;
  }

  public void bz()
  {
    this.gs = new a();
    com.baidu.location.f.getServiceContext().registerReceiver(this.gs, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
  }

  public class a extends BroadcastReceiver
  {
    public a()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      paramContext = paramIntent.getAction();
      int j;
      while (true)
      {
        int i;
        try
        {
          if (!paramContext.equals("android.intent.action.BATTERY_CHANGED"))
            return;
          e.jdMethod_if(e.this, false);
          i = paramIntent.getIntExtra("status", 0);
          j = paramIntent.getIntExtra("plugged", 0);
          int k = paramIntent.getIntExtra("level", -1);
          int m = paramIntent.getIntExtra("scale", -1);
          if ((k > 0) && (m > 0))
          {
            e.jdMethod_if(e.this, k * 100 / m);
            break label188;
            e.jdMethod_if(e.this, null);
            break;
          }
          else
          {
            e.jdMethod_if(e.this, -1);
          }
        }
        catch (Exception paramContext)
        {
          e.jdMethod_if(e.this, null);
          return;
        }
        e.jdMethod_if(e.this, "4");
        break;
        e.jdMethod_if(e.this, "3");
        break;
        e.jdMethod_if(e.this, "6");
        e.jdMethod_if(e.this, true);
        return;
        e.jdMethod_if(e.this, "5");
        e.jdMethod_if(e.this, true);
        return;
        label188: switch (i)
        {
        case 2:
        case 3:
        case 4:
        }
      }
      switch (j)
      {
      case 1:
      case 2:
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.e.e
 * JD-Core Version:    0.6.2
 */