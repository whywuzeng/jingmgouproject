package com.baidu.location.h;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import com.baidu.location.e.a;
import com.baidu.location.e.m;
import com.baidu.location.e.o;
import java.lang.reflect.Method;
import java.util.List;

public class e extends k
  implements com.baidu.location.b.f
{
  public static final long j4 = 3000L;
  public static final int j5 = 15;
  public static long j7 = 0L;
  public static final long ka = 3000L;
  public static final long kc = 5000L;
  private static e ke = null;
  private boolean j6 = true;
  private f j8 = null;
  private Object j9 = null;
  private long kb = 0L;
  private WifiManager kd = null;
  private Method kf = null;
  private boolean kg = false;
  private long kh = 0L;
  private a ki = null;

  private void df()
  {
    if (this.kd == null);
    while (true)
    {
      return;
      try
      {
        Object localObject = this.kd.getScanResults();
        localObject = new f((List)localObject, this.kb);
        if ((this.j8 == null) || (!((f)localObject).jdMethod_new(this.j8)))
        {
          this.j8 = ((f)localObject);
          return;
        }
      }
      catch (Exception localException)
      {
      }
    }
  }

  public static e dg()
  {
    if (ke == null)
      ke = new e();
    return ke;
  }

  public static double jdMethod_if(f paramf1, f paramf2)
  {
    double d2 = 1.0D;
    double d1;
    if ((paramf1 == null) || (paramf2 == null))
      d1 = 0.0D;
    int m;
    int n;
    float f;
    do
    {
      do
      {
        return d1;
        paramf1 = paramf1.km;
        paramf2 = paramf2.km;
        d1 = d2;
      }
      while (paramf1 == paramf2);
      if ((paramf1 == null) || (paramf2 == null))
        return 0.0D;
      m = paramf1.size();
      n = paramf2.size();
      f = m + n;
      if (m != 0)
        break;
      d1 = d2;
    }
    while (n == 0);
    if ((m == 0) || (n == 0))
      return 0.0D;
    int j = 0;
    int i = 0;
    String str;
    if (j < m)
    {
      str = ((ScanResult)paramf1.get(j)).BSSID;
      if (str != null);
    }
    while (true)
    {
      j += 1;
      break;
      int k = 0;
      while (true)
        if (k < n)
        {
          if (str.equals(((ScanResult)paramf2.get(k)).BSSID))
          {
            i += 1;
            break;
          }
          k += 1;
          continue;
          if (f <= 0.0F)
            return 0.0D;
          return i / f;
        }
    }
  }

  public static boolean jdMethod_if(f paramf1, f paramf2, float paramFloat)
  {
    if ((paramf1 == null) || (paramf2 == null))
      return false;
    paramf1 = paramf1.km;
    paramf2 = paramf2.km;
    if (paramf1 == paramf2)
      return true;
    if ((paramf1 == null) || (paramf2 == null))
      return false;
    int m = paramf1.size();
    int n = paramf2.size();
    float f = m + n;
    if ((m == 0) && (n == 0))
      return true;
    if ((m == 0) || (n == 0))
      return false;
    int j = 0;
    int i = 0;
    String str;
    if (j < m)
    {
      str = ((ScanResult)paramf1.get(j)).BSSID;
      if (str != null);
    }
    while (true)
    {
      j += 1;
      break;
      int k = 0;
      while (true)
        if (k < n)
        {
          if (str.equals(((ScanResult)paramf2.get(k)).BSSID))
          {
            i += 1;
            break;
          }
          k += 1;
          continue;
          return i * 2 >= f * paramFloat;
        }
    }
  }

  public static boolean jdMethod_if(List paramList1, List paramList2, float paramFloat)
  {
    if ((paramList1 == null) || (paramList2 == null))
      return false;
    if (paramList1 == paramList2)
      return true;
    if ((paramList1 == null) || (paramList2 == null))
      return false;
    int m = paramList1.size();
    int n = paramList2.size();
    float f = m + n;
    if ((m == 0) && (n == 0))
      return true;
    if ((m == 0) || (n == 0))
      return false;
    int j = 0;
    int i = 0;
    String str;
    if (j < m)
    {
      str = ((ScanResult)paramList1.get(j)).BSSID;
      if (str != null);
    }
    while (true)
    {
      j += 1;
      break;
      int k = 0;
      while (true)
        if (k < n)
        {
          if (str.equals(((ScanResult)paramList2.get(k)).BSSID))
          {
            i += 1;
            break;
          }
          k += 1;
          continue;
          return i * 2 >= f * paramFloat;
        }
    }
  }

  public boolean D(String paramString)
  {
    return super.D(paramString);
  }

  public boolean c0()
  {
    long l = System.currentTimeMillis();
    if (l - this.kh <= 5000L)
      return false;
    this.kh = l;
    return c5();
  }

  public boolean c1()
  {
    return (this.kd.isWifiEnabled()) && (3 == this.kd.getWifiState());
  }

  public boolean c2()
  {
    if (this.kd == null)
      return false;
    try
    {
      boolean bool = this.kd.isScanAlwaysAvailable();
      return bool;
    }
    catch (NoSuchMethodError localNoSuchMethodError)
    {
    }
    return false;
  }

  public void c3()
  {
    super.c3();
  }

  public f c4()
  {
    if (this.kd != null)
      try
      {
        f localf = new f(this.kd.getScanResults(), this.kb);
        return localf;
      }
      catch (Exception localException)
      {
      }
    return new f(null, 0L);
  }

  public boolean c5()
  {
    if (this.kd == null);
    while (System.currentTimeMillis() - this.kb <= 3000L)
      return false;
    return da();
  }

  // ERROR //
  public void c6()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 64	com/baidu/location/h/e:kg	Z
    //   6: istore_1
    //   7: iload_1
    //   8: iconst_1
    //   9: if_icmpne +6 -> 15
    //   12: aload_0
    //   13: monitorexit
    //   14: return
    //   15: getstatic 166	com/baidu/location/f:isServing	Z
    //   18: ifeq -6 -> 12
    //   21: aload_0
    //   22: invokestatic 170	com/baidu/location/f:getServiceContext	()Landroid/content/Context;
    //   25: ldc 172
    //   27: invokevirtual 178	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   30: checkcast 75	android/net/wifi/WifiManager
    //   33: putfield 54	com/baidu/location/h/e:kd	Landroid/net/wifi/WifiManager;
    //   36: aload_0
    //   37: new 10	com/baidu/location/h/e$a
    //   40: dup
    //   41: aload_0
    //   42: aconst_null
    //   43: invokespecial 181	com/baidu/location/h/e$a:<init>	(Lcom/baidu/location/h/e;Lcom/baidu/location/h/e$1;)V
    //   46: putfield 56	com/baidu/location/h/e:ki	Lcom/baidu/location/h/e$a;
    //   49: invokestatic 170	com/baidu/location/f:getServiceContext	()Landroid/content/Context;
    //   52: aload_0
    //   53: getfield 56	com/baidu/location/h/e:ki	Lcom/baidu/location/h/e$a;
    //   56: new 183	android/content/IntentFilter
    //   59: dup
    //   60: ldc 185
    //   62: invokespecial 188	android/content/IntentFilter:<init>	(Ljava/lang/String;)V
    //   65: invokevirtual 192	android/content/Context:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   68: pop
    //   69: aload_0
    //   70: iconst_1
    //   71: putfield 64	com/baidu/location/h/e:kg	Z
    //   74: ldc 194
    //   76: invokestatic 200	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   79: ldc 202
    //   81: invokevirtual 206	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   84: astore_2
    //   85: aload_2
    //   86: ifnull -74 -> 12
    //   89: aload_2
    //   90: iconst_1
    //   91: invokevirtual 212	java/lang/reflect/Field:setAccessible	(Z)V
    //   94: aload_0
    //   95: aload_2
    //   96: aload_0
    //   97: getfield 54	com/baidu/location/h/e:kd	Landroid/net/wifi/WifiManager;
    //   100: invokevirtual 215	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   103: putfield 66	com/baidu/location/h/e:j9	Ljava/lang/Object;
    //   106: aload_0
    //   107: getfield 66	com/baidu/location/h/e:j9	Ljava/lang/Object;
    //   110: invokevirtual 221	java/lang/Object:getClass	()Ljava/lang/Class;
    //   113: pop
    //   114: goto -102 -> 12
    //   117: astore_2
    //   118: goto -106 -> 12
    //   121: astore_2
    //   122: aload_0
    //   123: monitorexit
    //   124: aload_2
    //   125: athrow
    //   126: astore_2
    //   127: goto -58 -> 69
    //
    // Exception table:
    //   from	to	target	type
    //   74	85	117	java/lang/Exception
    //   89	114	117	java/lang/Exception
    //   2	7	121	finally
    //   15	49	121	finally
    //   49	69	121	finally
    //   69	74	121	finally
    //   74	85	121	finally
    //   89	114	121	finally
    //   49	69	126	java/lang/Exception
  }

  public String c7()
  {
    String str = null;
    try
    {
      WifiInfo localWifiInfo = this.kd.getConnectionInfo();
      if (localWifiInfo != null)
        str = localWifiInfo.getMacAddress();
      return str;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public f c8()
  {
    if ((this.j8 == null) || (!this.j8.ds()))
      return c4();
    return this.j8;
  }

  public String c9()
  {
    if (this.kd == null);
    Object localObject1;
    do
    {
      return null;
      localObject1 = this.kd.getConnectionInfo();
    }
    while (localObject1 == null);
    while (true)
    {
      try
      {
        localObject1 = ((WifiInfo)localObject1).getBSSID();
        if (localObject1 != null)
        {
          localObject1 = ((String)localObject1).replace(":", "");
          if ("000000000000".equals(localObject1))
            break;
          boolean bool = "".equals(localObject1);
          if (bool)
            break;
          return localObject1;
        }
      }
      catch (Exception localException)
      {
        return null;
      }
      Object localObject2 = null;
    }
  }

  public boolean da()
  {
    try
    {
      if ((this.kd.isWifiEnabled()) || ((Build.VERSION.SDK_INT > 17) && (this.kd.isScanAlwaysAvailable())))
        if (this.kf != null)
        {
          Object localObject = this.j9;
          if (localObject != null)
            try
            {
              this.kf.invoke(this.j9, new Object[] { Boolean.valueOf(this.j6) });
              this.kb = System.currentTimeMillis();
              return true;
            }
            catch (Exception localException1)
            {
              while (true)
                this.kd.startScan();
            }
        }
    }
    catch (NoSuchMethodError localNoSuchMethodError)
    {
      while (true)
      {
        this.kd.startScan();
        this.kb = System.currentTimeMillis();
        return true;
        this.kd.startScan();
      }
    }
    catch (Exception localException2)
    {
    }
    return false;
  }

  public boolean db()
  {
    try
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)com.baidu.location.f.getServiceContext().getSystemService("connectivity")).getActiveNetworkInfo();
      if (localNetworkInfo != null)
      {
        int i = localNetworkInfo.getType();
        if (i == 1)
          return true;
      }
      return false;
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  public int dc()
  {
    if (this.kd == null);
    while (true)
    {
      return -1;
      Object localObject = this.kd.getConnectionInfo();
      if (localObject != null)
        try
        {
          String str = ((WifiInfo)localObject).getBSSID();
          int j = ((WifiInfo)localObject).getRssi();
          int i = j;
          if (j < 0)
            i = -j;
          if (str != null)
          {
            localObject = str.replace(":", "");
            if (!"000000000000".equals(localObject))
            {
              boolean bool = "".equals(localObject);
              if (bool);
            }
          }
          else
          {
            return i;
          }
        }
        catch (Exception localException)
        {
        }
    }
    return -1;
  }

  // ERROR //
  public void dd()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 64	com/baidu/location/h/e:kg	Z
    //   6: istore_1
    //   7: iload_1
    //   8: ifne +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: invokestatic 170	com/baidu/location/f:getServiceContext	()Landroid/content/Context;
    //   17: aload_0
    //   18: getfield 56	com/baidu/location/h/e:ki	Lcom/baidu/location/h/e$a;
    //   21: invokevirtual 295	android/content/Context:unregisterReceiver	(Landroid/content/BroadcastReceiver;)V
    //   24: lconst_0
    //   25: putstatic 48	com/baidu/location/h/e:j7	J
    //   28: aload_0
    //   29: aconst_null
    //   30: putfield 56	com/baidu/location/h/e:ki	Lcom/baidu/location/h/e$a;
    //   33: aload_0
    //   34: aconst_null
    //   35: putfield 54	com/baidu/location/h/e:kd	Landroid/net/wifi/WifiManager;
    //   38: aload_0
    //   39: iconst_0
    //   40: putfield 64	com/baidu/location/h/e:kg	Z
    //   43: goto -32 -> 11
    //   46: astore_2
    //   47: aload_0
    //   48: monitorexit
    //   49: aload_2
    //   50: athrow
    //   51: astore_2
    //   52: goto -24 -> 28
    //
    // Exception table:
    //   from	to	target	type
    //   2	7	46	finally
    //   14	28	46	finally
    //   28	43	46	finally
    //   14	28	51	java/lang/Exception
  }

  public f de()
  {
    if ((this.j8 == null) || (!this.j8.dp()))
      return c4();
    return this.j8;
  }

  public f jdMethod_do(List paramList)
  {
    return super.jdMethod_do(paramList);
  }

  private class a extends BroadcastReceiver
  {
    private a()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramContext == null);
      do
      {
        do
          return;
        while (!paramIntent.getAction().equals("android.net.wifi.SCAN_RESULTS"));
        e.j7 = System.currentTimeMillis() / 1000L;
        e.jdMethod_if(e.this);
        m.bb().ba();
      }
      while (System.currentTimeMillis() - a.jdMethod_for() > 5000L);
      o.jdMethod_do(a.a(), e.this.c8(), a.jdMethod_if(), a.jdMethod_do());
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.h.e
 * JD-Core Version:    0.6.2
 */