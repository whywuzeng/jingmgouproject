package com.baidu.location.c;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import com.baidu.location.BDLocation;
import com.baidu.location.b.b;
import com.baidu.location.b.c;
import com.baidu.location.b.o;
import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class d
  implements b
{
  static final String ab = "com.baidu.lbs.offlinelocationprovider";
  private static final String ad = "http://180.149.144.31:8091/offline_loc";
  private static final String ae = "com.baidu.lbs.offlinelocationprovider";
  private static final String ag = "ofld";
  public static final String ai = "1";
  static final String ak = "http://loc.map.baidu.com/offline_loc";
  private static final String am = "http://loc.map.baidu.com/offline_loc";
  private static Context an;
  public static final String ao = "oflv2";
  public static final String ap = "oflv1";
  private static d aq;
  private static final String ar = "content://%s/";
  private static final String as = "com.baidu.lbs.offlinelocationprovider.debug";
  private static final int at = 2000;
  private final j ac;
  private final File af;
  private final k ah;
  private final q aj;
  private final a al;

  private d()
  {
    try
    {
      localFile3 = new File(an.getFilesDir(), "ofld");
      localFile1 = localFile3;
    }
    catch (Exception localException1)
    {
      try
      {
        if (!localFile3.exists())
          localFile3.mkdir();
        Object localObject;
        for (File localFile1 = localFile3; ; localObject = null)
        {
          this.af = localFile1;
          this.al = new a(this);
          this.aj = new q(this.al.a());
          this.ac = new j(this, this.al.a());
          this.ah = new k(this, this.al.a(), this.ac.n());
          return;
          localException1 = localException1;
        }
      }
      catch (Exception localException2)
      {
        while (true)
        {
          File localFile3;
          File localFile2 = localFile3;
        }
      }
    }
  }

  private BDLocation jdMethod_do(String[] paramArrayOfString)
  {
    new BDLocation();
    ExecutorService localExecutorService = Executors.newSingleThreadExecutor();
    paramArrayOfString = (FutureTask)localExecutorService.submit(new i(this, paramArrayOfString));
    try
    {
      BDLocation localBDLocation = (BDLocation)paramArrayOfString.get(2000L, TimeUnit.MILLISECONDS);
      return localBDLocation;
    }
    catch (InterruptedException localInterruptedException)
    {
      paramArrayOfString.cancel(true);
      return null;
    }
    catch (ExecutionException localExecutionException)
    {
      paramArrayOfString.cancel(true);
      return null;
    }
    catch (TimeoutException localTimeoutException)
    {
      o.aY().j("offlineLocation Timeout Exception!");
      paramArrayOfString.cancel(true);
      return null;
    }
    finally
    {
      localExecutorService.shutdown();
    }
    throw paramArrayOfString;
  }

  public static void jdMethod_if(Context paramContext)
  {
    if (an == null)
    {
      an = paramContext;
      c.N().jdMethod_do(an);
    }
  }

  private static final Uri jdMethod_int(String paramString)
  {
    return Uri.parse(String.format("content://%s/", new Object[] { paramString }));
  }

  private void jdMethod_int()
  {
    this.ac.g();
  }

  public static d jdMethod_try()
  {
    if (aq == null);
    try
    {
      if (aq == null)
      {
        if (an == null)
          jdMethod_if(com.baidu.location.f.getServiceContext());
        aq = new d();
      }
      aq.jdMethod_int();
      return aq;
    }
    finally
    {
    }
  }

  private boolean jdMethod_void()
  {
    boolean bool = false;
    String str = an.getPackageName();
    ProviderInfo localProviderInfo = an.getPackageManager().resolveContentProvider(ab, 0);
    int i;
    if (localProviderInfo == null)
    {
      String[] arrayOfString = this.ac.o();
      i = 0;
      if (i < arrayOfString.length)
      {
        localProviderInfo = an.getPackageManager().resolveContentProvider(arrayOfString[i], 0);
        if (localProviderInfo == null)
          break label73;
      }
    }
    while (true)
    {
      if (localProviderInfo == null)
        bool = true;
      label73: 
      while (!str.equals(localProviderInfo.packageName))
      {
        return bool;
        i += 1;
        break;
      }
      return true;
    }
  }

  public double jdMethod_byte()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)an.getSystemService("connectivity")).getActiveNetworkInfo();
    a locala2 = a.a;
    a locala1 = locala2;
    int i;
    if (localNetworkInfo != null)
    {
      locala1 = locala2;
      if (localNetworkInfo.isConnected())
      {
        if (localNetworkInfo.getType() == 1)
          locala2 = a.b;
        locala1 = locala2;
        if (localNetworkInfo.getType() == 0)
        {
          i = localNetworkInfo.getSubtype();
          if ((i != 1) && (i != 2) && (i != 4) && (i != 7) && (i != 11))
            break label113;
          locala1 = a.c;
        }
      }
    }
    while (locala1 == a.a)
    {
      return this.ac.b();
      label113: if ((i == 3) || (i == 5) || (i == 6) || (i == 8) || (i == 9) || (i == 10) || (i == 12) || (i == 14) || (i == 15))
      {
        locala1 = a.d;
      }
      else
      {
        locala1 = locala2;
        if (i == 13)
          locala1 = a.e;
      }
    }
    if (locala1 == a.b)
      return this.ac.c();
    if (locala1 == a.c)
      return this.ac.d();
    if (locala1 == a.d)
      return this.ac.e();
    if (locala1 == a.e)
      return this.ac.f();
    return 0.0D;
  }

  public boolean jdMethod_case()
  {
    return this.ac.j();
  }

  public Context jdMethod_char()
  {
    return an;
  }

  File d()
  {
    return this.af;
  }

  public boolean jdMethod_do(String paramString)
  {
    return this.ac.b(paramString);
  }

  public boolean e()
  {
    return this.ac.i();
  }

  public Cursor jdMethod_for(String[] paramArrayOfString)
  {
    paramArrayOfString = new h.a(paramArrayOfString);
    return this.al.a(paramArrayOfString);
  }

  public boolean jdMethod_goto()
  {
    return this.ac.k();
  }

  public void h()
  {
  }

  public boolean i()
  {
    return this.ac.m();
  }

  public long jdMethod_if(String paramString)
  {
    return this.ac.a(paramString);
  }

  public BDLocation jdMethod_if(com.baidu.location.h.h paramh, com.baidu.location.h.f paramf, BDLocation paramBDLocation, c paramc, b paramb)
  {
    int i;
    if (paramc == c.do)
    {
      i = this.ac.a();
      paramc = c.N().O() + "&mixMode=1";
      if (paramb != b.a)
        break label120;
    }
    label120: for (paramb = Boolean.valueOf(true); ; paramb = Boolean.valueOf(false))
    {
      paramf = h.a(paramh, paramf, paramBDLocation, paramc, paramb.booleanValue(), i);
      paramh = null;
      if (paramf.length > 0)
      {
        paramf = jdMethod_do(paramf);
        paramh = paramf;
        if (paramf != null)
        {
          paramh = paramf;
          if (paramf.getLocType() == 67);
        }
      }
      return paramh;
      paramc = c.N().O();
      i = 0;
      break;
    }
  }

  q j()
  {
    return this.aj;
  }

  public void k()
  {
    this.aj.a();
  }

  k l()
  {
    return this.ah;
  }

  public boolean jdMethod_long()
  {
    return this.ac.h();
  }

  public boolean m()
  {
    return this.ac.l();
  }

  public void n()
  {
    if (jdMethod_void())
      this.al.b();
  }

  j jdMethod_new()
  {
    return this.ac;
  }

  private static enum a
  {
  }

  public static enum b
  {
  }

  public static enum c
  {
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.c.d
 * JD-Core Version:    0.6.2
 */