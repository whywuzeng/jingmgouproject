package com.baidu.location.h;

import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.GpsStatus.NmeaListener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import com.baidu.location.Jni;
import com.baidu.location.e.o;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Set;

public class a extends g
  implements com.baidu.location.b.f
{
  private static int jH = 0;
  private static final int jv = 3000;
  private static String jy = null;
  private static a jz = null;
  private int jA;
  private final int jB = 1;
  private long jC = 0L;
  private c jD = null;
  private Handler jE = null;
  private Context jF;
  private boolean jG = false;
  private b jI = null;
  private Location jJ;
  private final long jK = 1000L;
  private LocationManager jL = null;
  private HashMap jl;
  private boolean jm = false;
  private final long jn = 9000L;
  private final int jo = 2;
  private int jp;
  private long jq = 0L;
  private String jr = null;
  private final int js = 4;
  private boolean jt = false;
  private final int ju = 3;
  private GpsStatus jw;
  private a jx = null;

  public static a cN()
  {
    if (jz == null)
      jz = new a();
    return jz;
  }

  private void jdMethod_case(Location paramLocation)
  {
    int j;
    int i;
    if (paramLocation != null)
    {
      j = jH;
      i = j;
      if (j != 0);
    }
    try
    {
      i = paramLocation.getExtras().getInt("satellites");
      if ((i == 0) && (!com.baidu.location.b.k.cj));
      while (true)
      {
        return;
        this.jJ = null;
        return;
        this.jJ = paramLocation;
        if (this.jJ == null)
          this.jr = null;
        try
        {
          com.baidu.location.e.k.b8().jdMethod_try(this.jJ);
          if (this.jJ != null)
            com.baidu.location.e.h.bK().jdMethod_for(this.jJ);
          if ((!cJ()) || (this.jJ == null))
            continue;
          com.baidu.location.e.c.br().l(cF());
          if ((jH <= 2) || (!o.jdMethod_if(this.jJ, true)))
            continue;
          boolean bool = e.dg().c0();
          com.baidu.location.e.a.a(new h(b.cW().cO()));
          com.baidu.location.e.a.a(System.currentTimeMillis());
          com.baidu.location.e.a.a(new Location(this.jJ));
          com.baidu.location.e.a.a(com.baidu.location.e.c.br().bu());
          if (bool)
            continue;
          o.jdMethod_do(com.baidu.location.e.a.a(), null, com.baidu.location.e.a.jdMethod_if(), com.baidu.location.e.c.br().bu());
          return;
          l = System.currentTimeMillis();
          this.jJ.setTime(l);
          f = (float)(this.jJ.getSpeed() * 3.6D);
          if (!this.jJ.hasSpeed())
            f = -1.0F;
          j = jH;
          i = j;
          if (j != 0);
        }
        catch (Exception paramLocation)
        {
          try
          {
            while (true)
            {
              long l;
              float f;
              i = this.jJ.getExtras().getInt("satellites");
              this.jr = String.format(Locale.CHINA, "&ll=%.5f|%.5f&s=%.1f&d=%.1f&ll_n=%d&ll_t=%d", new Object[] { Double.valueOf(this.jJ.getLongitude()), Double.valueOf(this.jJ.getLatitude()), Float.valueOf(f), Float.valueOf(this.jJ.getBearing()), Integer.valueOf(i), Long.valueOf(l) });
              jdMethod_if(this.jJ.getLongitude(), this.jJ.getLatitude(), f);
            }
            paramLocation = paramLocation;
          }
          catch (Exception paramLocation)
          {
            while (true)
              i = j;
          }
        }
      }
    }
    catch (Exception localException)
    {
      while (true)
        i = j;
    }
  }

  private void jdMethod_char(Location paramLocation)
  {
    paramLocation = this.jE.obtainMessage(1, paramLocation);
    this.jE.sendMessage(paramLocation);
  }

  public static String jdMethod_else(Location paramLocation)
  {
    String str = jdMethod_long(paramLocation);
    paramLocation = str;
    if (str != null)
      paramLocation = str + jy;
    return paramLocation;
  }

  private void jdMethod_else(boolean paramBoolean)
  {
    this.jG = paramBoolean;
    if ((paramBoolean) && (!cJ()));
  }

  public static String jdMethod_goto(Location paramLocation)
  {
    String str = jdMethod_long(paramLocation);
    paramLocation = str;
    if (str != null)
      paramLocation = str + "&g_tp=0";
    return paramLocation;
  }

  private int jdMethod_if(m paramm, int paramInt)
  {
    if (jH >= com.baidu.location.b.k.cq);
    do
    {
      double d;
      do
      {
        do
        {
          return 1;
          if (jH <= com.baidu.location.b.k.b7)
            return 4;
          d = paramm.c();
        }
        while (d <= com.baidu.location.b.k.cQ);
        if (d >= com.baidu.location.b.k.bW)
          return 4;
        d = paramm.b();
      }
      while (d <= com.baidu.location.b.k.ck);
      if (d >= com.baidu.location.b.k.cA)
        return 4;
    }
    while (paramInt >= com.baidu.location.b.k.bJ);
    if (paramInt <= com.baidu.location.b.k.cC)
      return 4;
    if (this.jl != null)
      return jdMethod_if(this.jl);
    return 3;
  }

  private int jdMethod_if(HashMap paramHashMap)
  {
    ArrayList localArrayList1;
    ArrayList localArrayList2;
    int i;
    Object localObject;
    int j;
    if (this.jA > 4)
    {
      localArrayList1 = new ArrayList();
      localArrayList2 = new ArrayList();
      paramHashMap = paramHashMap.entrySet().iterator();
      i = 0;
      if (paramHashMap.hasNext())
      {
        localObject = (List)((Map.Entry)paramHashMap.next()).getValue();
        if (localObject == null)
          break label301;
        localObject = jdMethod_if((List)localObject);
        if (localObject == null)
          break label301;
        localArrayList1.add(localObject);
        j = i + 1;
        localArrayList2.add(Integer.valueOf(i));
        i = j;
      }
    }
    label301: 
    while (true)
    {
      break;
      if (!localArrayList1.isEmpty())
      {
        paramHashMap = new double[2];
        j = localArrayList1.size();
        i = 0;
        while (i < j)
        {
          localObject = (double[])localArrayList1.get(i);
          int k = ((Integer)localArrayList2.get(i)).intValue();
          localObject[0] *= k;
          localObject[1] *= k;
          paramHashMap[0] += localObject[0];
          double d = paramHashMap[1];
          localObject[1] += d;
          i += 1;
        }
        paramHashMap[0] /= j;
        paramHashMap[1] /= j;
        paramHashMap = jdMethod_new(paramHashMap[0], paramHashMap[1]);
        if (paramHashMap[0] <= com.baidu.location.b.k.cI)
          return 1;
        if (paramHashMap[0] >= com.baidu.location.b.k.bQ)
          return 4;
      }
      return 3;
    }
  }

  private String jdMethod_if(GpsSatellite paramGpsSatellite, HashMap paramHashMap)
  {
    int j = (int)Math.floor(paramGpsSatellite.getAzimuth() / 6.0F);
    float f1 = paramGpsSatellite.getElevation();
    int k = (int)Math.floor(f1 / 1.5D);
    float f2 = paramGpsSatellite.getSnr();
    int m = Math.round(f2 / 5.0F);
    int i = paramGpsSatellite.getPrn();
    if (i >= 65)
      i -= 32;
    while (true)
    {
      if ((f2 >= 10.0F) && (f1 >= 1.0F))
      {
        List localList = (List)paramHashMap.get(Integer.valueOf(m));
        Object localObject = localList;
        if (localList == null)
          localObject = new ArrayList();
        ((List)localObject).add(paramGpsSatellite);
        paramHashMap.put(Integer.valueOf(m), localObject);
        this.jA += 1;
      }
      if ((j < 64) || ((k < 64) || (i >= 65)));
      return null;
    }
  }

  private void jdMethod_if(double paramDouble1, double paramDouble2, float paramFloat)
  {
    int j = 0;
    if (!com.baidu.location.e.f.bC().gv);
    while (true)
    {
      return;
      int i = j;
      if (paramDouble1 >= 73.146973000000003D)
      {
        i = j;
        if (paramDouble1 <= 135.25268600000001D)
        {
          i = j;
          if (paramDouble2 <= 54.258806999999997D)
          {
            i = j;
            if (paramDouble2 >= 14.604846999999999D)
            {
              if (paramFloat <= 18.0F)
                break label88;
              i = j;
            }
          }
        }
      }
      while (com.baidu.location.b.k.co != i)
      {
        com.baidu.location.b.k.co = i;
        return;
        label88: double d1 = com.baidu.location.b.k.bG;
        double d2 = com.baidu.location.b.k.b4;
        i = (int)((paramDouble1 - d1) * 1000.0D);
        int k = (int)((d2 - paramDouble2) * 1000.0D);
        if ((i > 0) && (i < 50) && (k > 0) && (k < 50))
        {
          k = i + k * 50;
          i = j;
          if (com.baidu.location.b.k.cM)
            i = com.baidu.location.b.k.b2[(k >> 2)] >> (k & 0x3) * 2 & 0x3;
        }
        else
        {
          String str = String.format(Locale.CHINA, "&ll=%.5f|%.5f", new Object[] { Double.valueOf(paramDouble1), Double.valueOf(paramDouble2) });
          str = str + "&im=" + com.baidu.location.b.c.N().L();
          com.baidu.location.b.k.cp = paramDouble1;
          com.baidu.location.b.k.cE = paramDouble2;
          com.baidu.location.e.f.bC().m(str);
          i = j;
        }
      }
    }
  }

  private void jdMethod_if(String paramString, Location paramLocation)
  {
    if (paramLocation == null);
    boolean bool;
    do
    {
      return;
      paramString = paramString + com.baidu.location.e.c.br().bu();
      bool = e.dg().c0();
      com.baidu.location.e.a.a(new h(b.cW().cO()));
      com.baidu.location.e.a.a(System.currentTimeMillis());
      com.baidu.location.e.a.a(new Location(paramLocation));
      com.baidu.location.e.a.a(paramString);
    }
    while (bool);
    o.jdMethod_do(com.baidu.location.e.a.a(), null, com.baidu.location.e.a.jdMethod_if(), paramString);
  }

  public static boolean jdMethod_if(Location paramLocation1, Location paramLocation2, boolean paramBoolean)
  {
    boolean bool2 = true;
    boolean bool1;
    if (paramLocation1 == paramLocation2)
      bool1 = false;
    float f2;
    do
    {
      do
      {
        float f1;
        do
        {
          do
          {
            do
            {
              do
              {
                return bool1;
                bool1 = bool2;
              }
              while (paramLocation1 == null);
              bool1 = bool2;
            }
            while (paramLocation2 == null);
            f1 = paramLocation2.getSpeed();
            if ((!paramBoolean) || ((com.baidu.location.b.k.co != 3) && (com.baidu.location.b.h.W().jdMethod_for(paramLocation2.getLongitude(), paramLocation2.getLatitude()))))
              break;
            bool1 = bool2;
          }
          while (f1 < 5.0F);
          f2 = paramLocation2.distanceTo(paramLocation1);
          if (f1 <= com.baidu.location.b.k.cg)
            break;
          bool1 = bool2;
        }
        while (f2 > com.baidu.location.b.k.cx);
        return false;
        if (f1 <= com.baidu.location.b.k.ch)
          break;
        bool1 = bool2;
      }
      while (f2 > com.baidu.location.b.k.cO);
      return false;
      bool1 = bool2;
    }
    while (f2 > 5.0F);
    return false;
  }

  private double[] jdMethod_if(List paramList)
  {
    if ((paramList == null) || (paramList.isEmpty()))
      return null;
    double[] arrayOfDouble = new double[2];
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (GpsSatellite)localIterator.next();
      if (localObject != null)
      {
        localObject = jdMethod_int(90.0F - ((GpsSatellite)localObject).getElevation(), ((GpsSatellite)localObject).getAzimuth());
        arrayOfDouble[0] += localObject[0];
        arrayOfDouble[1] += localObject[1];
      }
    }
    int i = paramList.size();
    arrayOfDouble[0] /= i;
    arrayOfDouble[1] /= i;
    return arrayOfDouble;
  }

  private double[] jdMethod_int(double paramDouble1, double paramDouble2)
  {
    return new double[] { Math.sin(Math.toRadians(paramDouble2)) * paramDouble1, Math.cos(Math.toRadians(paramDouble2)) * paramDouble1 };
  }

  public static String jdMethod_long(Location paramLocation)
  {
    float f3 = -1.0F;
    if (paramLocation == null)
      return null;
    float f1 = (float)(paramLocation.getSpeed() * 3.6D);
    if (!paramLocation.hasSpeed())
      f1 = -1.0F;
    float f2;
    int i;
    if (paramLocation.hasAccuracy())
    {
      f2 = paramLocation.getAccuracy();
      i = (int)f2;
      if (!paramLocation.hasAltitude())
        break label178;
    }
    label178: for (double d = paramLocation.getAltitude(); ; d = 555.0D)
    {
      f2 = f3;
      if (paramLocation.hasBearing())
        f2 = paramLocation.getBearing();
      return String.format(Locale.CHINA, "&ll=%.5f|%.5f&s=%.1f&d=%.1f&ll_r=%d&ll_n=%d&ll_h=%.2f&ll_t=%d", new Object[] { Double.valueOf(paramLocation.getLongitude()), Double.valueOf(paramLocation.getLatitude()), Float.valueOf(f1), Float.valueOf(f2), Integer.valueOf(i), Integer.valueOf(jH), Double.valueOf(d), Long.valueOf(paramLocation.getTime() / 1000L) });
      f2 = -1.0F;
      break;
    }
  }

  private double[] jdMethod_new(double paramDouble1, double paramDouble2)
  {
    double d = 0.0D;
    if (paramDouble2 == 0.0D)
      if (paramDouble1 > 0.0D)
        d = 90.0D;
    while (true)
    {
      return new double[] { Math.sqrt(paramDouble1 * paramDouble1 + paramDouble2 * paramDouble2), d };
      if (paramDouble1 < 0.0D)
      {
        d = 270.0D;
        continue;
        d = Math.toDegrees(Math.atan(paramDouble1 / paramDouble2));
      }
    }
  }

  // ERROR //
  public void cC()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic 643	com/baidu/location/f:isServing	Z
    //   5: istore_1
    //   6: iload_1
    //   7: ifne +6 -> 13
    //   10: aload_0
    //   11: monitorexit
    //   12: return
    //   13: aload_0
    //   14: invokestatic 647	com/baidu/location/f:getServiceContext	()Landroid/content/Context;
    //   17: putfield 649	com/baidu/location/h/a:jF	Landroid/content/Context;
    //   20: aload_0
    //   21: aload_0
    //   22: getfield 649	com/baidu/location/h/a:jF	Landroid/content/Context;
    //   25: ldc_w 651
    //   28: invokevirtual 657	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   31: checkcast 659	android/location/LocationManager
    //   34: putfield 80	com/baidu/location/h/a:jL	Landroid/location/LocationManager;
    //   37: aload_0
    //   38: new 8	com/baidu/location/h/a$a
    //   41: dup
    //   42: aload_0
    //   43: aconst_null
    //   44: invokespecial 662	com/baidu/location/h/a$a:<init>	(Lcom/baidu/location/h/a;Lcom/baidu/location/h/i;)V
    //   47: putfield 86	com/baidu/location/h/a:jx	Lcom/baidu/location/h/a$a;
    //   50: aload_0
    //   51: getfield 80	com/baidu/location/h/a:jL	Landroid/location/LocationManager;
    //   54: aload_0
    //   55: getfield 86	com/baidu/location/h/a:jx	Lcom/baidu/location/h/a$a;
    //   58: invokevirtual 666	android/location/LocationManager:addGpsStatusListener	(Landroid/location/GpsStatus$Listener;)Z
    //   61: pop
    //   62: aload_0
    //   63: new 14	com/baidu/location/h/a$c
    //   66: dup
    //   67: aload_0
    //   68: aconst_null
    //   69: invokespecial 667	com/baidu/location/h/a$c:<init>	(Lcom/baidu/location/h/a;Lcom/baidu/location/h/i;)V
    //   72: putfield 84	com/baidu/location/h/a:jD	Lcom/baidu/location/h/a$c;
    //   75: aload_0
    //   76: getfield 80	com/baidu/location/h/a:jL	Landroid/location/LocationManager;
    //   79: ldc_w 669
    //   82: ldc2_w 75
    //   85: fconst_0
    //   86: aload_0
    //   87: getfield 84	com/baidu/location/h/a:jD	Lcom/baidu/location/h/a$c;
    //   90: invokevirtual 673	android/location/LocationManager:requestLocationUpdates	(Ljava/lang/String;JFLandroid/location/LocationListener;)V
    //   93: aload_0
    //   94: new 675	com/baidu/location/h/i
    //   97: dup
    //   98: aload_0
    //   99: invokespecial 678	com/baidu/location/h/i:<init>	(Lcom/baidu/location/h/a;)V
    //   102: putfield 100	com/baidu/location/h/a:jE	Landroid/os/Handler;
    //   105: goto -95 -> 10
    //   108: astore_2
    //   109: aload_0
    //   110: monitorexit
    //   111: aload_2
    //   112: athrow
    //   113: astore_2
    //   114: goto -21 -> 93
    //
    // Exception table:
    //   from	to	target	type
    //   2	6	108	finally
    //   13	20	108	finally
    //   20	93	108	finally
    //   93	105	108	finally
    //   20	93	113	java/lang/Exception
  }

  public Location cD()
  {
    return this.jJ;
  }

  public boolean cE()
  {
    return (this.jJ != null) && (this.jJ.getLatitude() != 0.0D) && (this.jJ.getLongitude() != 0.0D);
  }

  public String cF()
  {
    Object localObject = null;
    String str;
    float f;
    int j;
    int i;
    if (this.jJ != null)
    {
      str = "{\"result\":{\"time\":\"" + com.baidu.location.b.k.ad() + "\",\"error\":\"61\"},\"content\":{\"point\":{\"x\":" + "\"%f\",\"y\":\"%f\"},\"radius\":\"%d\",\"d\":\"%f\"," + "\"s\":\"%f\",\"n\":\"%d\"";
      if (!this.jJ.hasAccuracy())
        break label349;
      f = this.jJ.getAccuracy();
      j = (int)f;
      f = (float)(this.jJ.getSpeed() * 3.6D);
      if (!this.jJ.hasSpeed())
        f = -1.0F;
      if (!com.baidu.location.b.h.W().jdMethod_for(this.jJ.getLongitude(), this.jJ.getLatitude()))
        break label356;
      localObject = Jni.jdMethod_if(this.jJ.getLongitude(), this.jJ.getLatitude(), "gps2gcj");
      if ((localObject[0] > 0.0D) || (localObject[1] > 0.0D))
        break label408;
      localObject[0] = this.jJ.getLongitude();
      localObject[1] = this.jJ.getLatitude();
      i = 1;
    }
    while (true)
    {
      str = String.format(Locale.CHINA, str, new Object[] { Double.valueOf(localObject[0]), Double.valueOf(localObject[1]), Integer.valueOf(j), Float.valueOf(this.jJ.getBearing()), Float.valueOf(f), Integer.valueOf(jH) });
      localObject = str;
      if (i == 0)
        localObject = str + ",\"in_cn\":\"0\"";
      if (this.jJ.hasAltitude())
      {
        localObject = (String)localObject + String.format(Locale.CHINA, ",\"h\":%.2f}}", new Object[] { Double.valueOf(this.jJ.getAltitude()) });
        return localObject;
        label349: f = 10.0F;
        break;
        label356: localObject = new double[] { this.jJ.getLongitude(), this.jJ.getLatitude() };
        i = 0;
        continue;
      }
      return (String)localObject + "}}";
      label408: i = 1;
    }
  }

  // ERROR //
  public void cG()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 707	com/baidu/location/h/a:cK	()V
    //   6: aload_0
    //   7: getfield 80	com/baidu/location/h/a:jL	Landroid/location/LocationManager;
    //   10: astore_1
    //   11: aload_1
    //   12: ifnonnull +6 -> 18
    //   15: aload_0
    //   16: monitorexit
    //   17: return
    //   18: aload_0
    //   19: getfield 86	com/baidu/location/h/a:jx	Lcom/baidu/location/h/a$a;
    //   22: ifnull +14 -> 36
    //   25: aload_0
    //   26: getfield 80	com/baidu/location/h/a:jL	Landroid/location/LocationManager;
    //   29: aload_0
    //   30: getfield 86	com/baidu/location/h/a:jx	Lcom/baidu/location/h/a$a;
    //   33: invokevirtual 711	android/location/LocationManager:removeGpsStatusListener	(Landroid/location/GpsStatus$Listener;)V
    //   36: aload_0
    //   37: getfield 80	com/baidu/location/h/a:jL	Landroid/location/LocationManager;
    //   40: aload_0
    //   41: getfield 84	com/baidu/location/h/a:jD	Lcom/baidu/location/h/a$c;
    //   44: invokevirtual 715	android/location/LocationManager:removeUpdates	(Landroid/location/LocationListener;)V
    //   47: aload_0
    //   48: aconst_null
    //   49: putfield 86	com/baidu/location/h/a:jx	Lcom/baidu/location/h/a$a;
    //   52: aload_0
    //   53: aconst_null
    //   54: putfield 80	com/baidu/location/h/a:jL	Landroid/location/LocationManager;
    //   57: goto -42 -> 15
    //   60: astore_1
    //   61: aload_0
    //   62: monitorexit
    //   63: aload_1
    //   64: athrow
    //   65: astore_1
    //   66: goto -19 -> 47
    //
    // Exception table:
    //   from	to	target	type
    //   2	11	60	finally
    //   18	36	60	finally
    //   36	47	60	finally
    //   47	57	60	finally
    //   18	36	65	java/lang/Exception
    //   36	47	65	java/lang/Exception
  }

  public void cH()
  {
    cC();
    if (this.jt)
      return;
    try
    {
      this.jI = new b(null);
      this.jL.requestLocationUpdates("gps", 1000L, 0.0F, this.jI);
      this.jL.addNmeaListener(this.jx);
      this.jt = true;
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public String cI()
  {
    if ((cJ()) && (this.jJ != null))
      return jdMethod_long(this.jJ);
    return null;
  }

  public boolean cJ()
  {
    if (!cE());
    while (System.currentTimeMillis() - this.jC > 10000L)
      return false;
    long l = System.currentTimeMillis();
    if ((this.jm) && (l - this.jq < 3000L))
      return true;
    return this.jG;
  }

  public void cK()
  {
    if (!this.jt)
      return;
    if (this.jL != null);
    try
    {
      if (this.jI != null)
        this.jL.removeUpdates(this.jI);
      if (this.jx != null)
        this.jL.removeNmeaListener(this.jx);
      label51: com.baidu.location.b.k.b1 = 0;
      com.baidu.location.b.k.co = 0;
      this.jI = null;
      this.jt = false;
      jdMethod_else(false);
      return;
    }
    catch (Exception localException)
    {
      break label51;
    }
  }

  public String cL()
  {
    return this.jr;
  }

  public boolean cM()
  {
    return this.jt;
  }

  public void jdMethod_char(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      cH();
      return;
    }
    cK();
  }

  private class a
    implements GpsStatus.Listener, GpsStatus.NmeaListener
  {
    long a = 0L;
    private long c = 0L;
    private final int d = 400;
    private boolean e = false;
    private List f = new ArrayList();
    private String g = null;
    private String h = null;
    private String i = null;

    private a()
    {
    }

    public void a(String paramString)
    {
      if ((System.currentTimeMillis() - this.c > 400L) && (this.e) && (this.f.size() > 0));
      try
      {
        m localm = new m(this.f, this.g, this.h, this.i);
        if (localm.a())
        {
          com.baidu.location.b.k.b1 = a.jdMethod_if(a.this, localm, a.jdMethod_case(a.this));
          if (com.baidu.location.b.k.b1 > 0)
            a.B(String.format(Locale.CHINA, "&nmea=%.1f|%.1f&g_tp=%d", new Object[] { Double.valueOf(localm.c()), Double.valueOf(localm.b()), Integer.valueOf(com.baidu.location.b.k.b1) }));
        }
        while (true)
        {
          this.f.clear();
          this.i = null;
          this.h = null;
          this.g = null;
          this.e = false;
          if (!paramString.startsWith("$GPGGA"))
            break;
          this.e = true;
          this.g = paramString.trim();
          this.c = System.currentTimeMillis();
          return;
          com.baidu.location.b.k.b1 = 0;
        }
      }
      catch (Exception localException)
      {
        while (true)
        {
          com.baidu.location.b.k.b1 = 0;
          continue;
          if (paramString.startsWith("$GPGSV"))
            this.f.add(paramString.trim());
          else if (paramString.startsWith("$GPGSA"))
            this.i = paramString.trim();
        }
      }
    }

    public void onGpsStatusChanged(int paramInt)
    {
      if (a.jdMethod_for(a.this) == null);
      do
      {
        return;
        switch (paramInt)
        {
        case 3:
        default:
          return;
        case 2:
          a.jdMethod_do(a.this, null);
          a.jdMethod_if(a.this, false);
          a.jdMethod_else(0);
          return;
        case 4:
        }
      }
      while (!a.jdMethod_int(a.this));
      try
      {
        if (a.jdMethod_new(a.this) == null)
          a.jdMethod_if(a.this, a.jdMethod_for(a.this).getGpsStatus(null));
        while (true)
        {
          Iterator localIterator = a.jdMethod_new(a.this).getSatellites().iterator();
          a.jdMethod_do(a.this, 0);
          a.jdMethod_if(a.this, 0);
          a.jdMethod_if(a.this, new HashMap());
          paramInt = 0;
          while (localIterator.hasNext())
          {
            GpsSatellite localGpsSatellite = (GpsSatellite)localIterator.next();
            if (localGpsSatellite.usedInFix())
            {
              paramInt += 1;
              if (localGpsSatellite.getSnr() >= com.baidu.location.b.k.bH)
                a.jdMethod_if(a.this);
              a.jdMethod_if(a.this, localGpsSatellite, a.jdMethod_byte(a.this));
            }
          }
          a.jdMethod_for(a.this).getGpsStatus(a.jdMethod_new(a.this));
        }
        a.jdMethod_else(paramInt);
        return;
      }
      catch (Exception localException)
      {
      }
    }

    public void onNmeaReceived(long paramLong, String paramString)
    {
      if (!a.jdMethod_int(a.this));
      do
      {
        return;
        if (!com.baidu.location.e.f.bC().gz)
        {
          com.baidu.location.b.k.b1 = 0;
          return;
        }
      }
      while ((paramString == null) || (paramString.equals("")) || (paramString.length() < 9) || (paramString.length() > 150) || (!a.this.cJ()));
      a.jdMethod_try(a.this).sendMessage(a.jdMethod_try(a.this).obtainMessage(2, paramString));
    }
  }

  private class b
    implements LocationListener
  {
    private b()
    {
    }

    public void onLocationChanged(Location paramLocation)
    {
      a.jdMethod_do(a.this, System.currentTimeMillis());
      a.jdMethod_if(a.this, true);
      a.jdMethod_do(a.this, paramLocation);
      a.jdMethod_do(a.this, false);
    }

    public void onProviderDisabled(String paramString)
    {
      a.jdMethod_do(a.this, null);
      a.jdMethod_if(a.this, false);
    }

    public void onProviderEnabled(String paramString)
    {
    }

    public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle)
    {
      switch (paramInt)
      {
      default:
        return;
      case 0:
        a.jdMethod_do(a.this, null);
        a.jdMethod_if(a.this, false);
        return;
      case 1:
        a.jdMethod_if(a.this, System.currentTimeMillis());
        a.jdMethod_do(a.this, true);
        a.jdMethod_if(a.this, false);
        return;
      case 2:
      }
      a.jdMethod_do(a.this, false);
    }
  }

  private class c
    implements LocationListener
  {
    private long b = 0L;

    private c()
    {
    }

    public void onLocationChanged(Location paramLocation)
    {
      if (a.jdMethod_int(a.this));
      while ((paramLocation == null) || (paramLocation.getProvider() != "gps") || (System.currentTimeMillis() - this.b < 10000L) || (!o.jdMethod_if(paramLocation, false)))
        return;
      this.b = System.currentTimeMillis();
      paramLocation = a.jdMethod_try(a.this).obtainMessage(4, paramLocation);
      a.jdMethod_try(a.this).sendMessage(paramLocation);
    }

    public void onProviderDisabled(String paramString)
    {
    }

    public void onProviderEnabled(String paramString)
    {
    }

    public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle)
    {
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.h.a
 * JD-Core Version:    0.6.2
 */