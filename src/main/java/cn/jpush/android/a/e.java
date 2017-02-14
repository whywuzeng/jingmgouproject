package cn.jpush.android.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import cn.jpush.android.util.x;
import java.util.List;

final class e extends BroadcastReceiver
{
  private static final String[] z;

  static
  {
    String[] arrayOfString = new String[3];
    Object localObject2 = "3\f\025t\005;\006_h\017&L\006o\f;L\"E+\034=#C9\007.%U";
    int i = -1;
    int j = 0;
    Object localObject1 = arrayOfString;
    localObject2 = ((String)localObject2).toCharArray();
    int k = localObject2.length;
    int m;
    Object localObject3;
    int n;
    int i1;
    label50: int i2;
    label99: label121: Object localObject4;
    int i3;
    if (k <= 1)
    {
      m = 0;
      localObject3 = localObject1;
      n = j;
      localObject1 = localObject2;
      i1 = i;
      j = m;
      while (true)
      {
        localObject2 = localObject1;
        i2 = localObject2[m];
        switch (j % 5)
        {
        default:
          i = 106;
          localObject2[m] = ((char)(i ^ i2));
          j += 1;
          if (k != 0)
            break label121;
          m = k;
        case 0:
        case 1:
        case 2:
        case 3:
        }
      }
      i = k;
      localObject4 = localObject3;
      i3 = n;
      localObject2 = localObject1;
      i2 = i1;
    }
    while (true)
    {
      i1 = i2;
      localObject1 = localObject2;
      n = i3;
      localObject3 = localObject4;
      k = i;
      m = j;
      if (i > j)
        break label50;
      localObject1 = new String((char[])localObject2).intern();
      switch (i2)
      {
      default:
        localObject4[i3] = localObject1;
        localObject2 = "%\013\027o5!\026\020r\017";
        j = 1;
        i = 0;
        break;
      case 0:
        localObject4[i3] = localObject1;
        j = 2;
        localObject2 = "3\f\025t\005;\006_h\017&L\006o\f;L&O,\033=\"R+\006'.E\"\023,6C.";
        i = 1;
        break;
      case 1:
        localObject4[i3] = localObject1;
        z = arrayOfString;
        return;
        i = 82;
        break label99;
        i = 98;
        break label99;
        i = 113;
        break label99;
        i = 6;
        break label99;
        m = 0;
        i2 = i;
        i3 = j;
        localObject4 = localObject1;
        i = k;
        j = m;
      }
    }
  }

  private e(d paramd)
  {
  }

  public final void onReceive(Context paramContext, Intent paramIntent)
  {
    boolean bool;
    long l;
    if ((!d.i(this.a)) || (!d.a(d.a(this.a))))
    {
      if (paramIntent.getIntExtra(z[1], 4) == 3)
      {
        bool = d.e(this.a).b().startScan();
        l = System.currentTimeMillis();
      }
    }
    else
      while (bool)
      {
        try
        {
          if (paramIntent.getIntExtra(z[1], 4) != 3)
            return;
          paramContext = d.e(this.a).b().getScanResults();
          if ((paramContext == null) || (paramContext.size() == 0))
          {
            if (System.currentTimeMillis() - l <= 10000L)
              continue;
            d.a(this.a, d.e(this.a).c());
            d.a(this.a).unregisterReceiver(d.j(this.a));
            if (d.i(this.a))
              break;
            this.a.d();
            return;
          }
        }
        catch (Exception paramContext)
        {
          x.j();
          return;
        }
        d.a(this.a, d.e(this.a).c());
        d.a(this.a).unregisterReceiver(d.j(this.a));
        if (!d.i(this.a))
          this.a.d();
        bool = false;
        continue;
        if (d.d(this.a) == 2)
        {
          paramContext = paramIntent.getAction();
          if (z[0].equals(paramContext))
          {
            d.c(this.a).removeMessages(5);
            if (System.currentTimeMillis() - d.k(this.a) > 4000L)
            {
              d.c(this.a).sendEmptyMessageDelayed(5, 4000L);
              return;
            }
            d.c(this.a).sendEmptyMessage(5);
            return;
          }
          if (d.l(this.a))
          {
            paramContext = paramIntent.getAction();
            if ((z[2].equals(paramContext)) && (paramIntent.getIntExtra(z[1], 4) == 2))
            {
              this.a.d = true;
              d.a(this.a, false);
              x.c();
            }
          }
        }
      }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.a.e
 * JD-Core Version:    0.6.2
 */