package com.baidu.location.d;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.BDNotifyListener;
import com.baidu.location.Jni;
import com.baidu.location.LocationClient;
import com.baidu.location.b.f;
import java.util.ArrayList;
import java.util.Iterator;

public class a
  implements f
{
  public static final String fA = "android.com.baidu.location.TIMER.NOTIFY";
  private PendingIntent fB = null;
  private ArrayList fC = null;
  private BDLocation fD = null;
  private long fE = 0L;
  private b fF = null;
  private float fG = 3.4028235E+38F;
  private boolean fH = false;
  private long fI = 0L;
  private boolean fJ = false;
  private LocationClient fK = null;
  private int fw = 0;
  private Context fx = null;
  private AlarmManager fy = null;
  private a fz = new a();

  public a(Context paramContext, LocationClient paramLocationClient)
  {
    this.fx = paramContext;
    this.fK = paramLocationClient;
    this.fK.registerNotifyLocationListener(this.fz);
    this.fy = ((AlarmManager)this.fx.getSystemService("alarm"));
    this.fF = new b();
    this.fJ = false;
  }

  private void a3()
  {
    int j = 10000;
    if (!a5())
      return;
    int i;
    if (this.fG > 5000.0F)
    {
      i = 600000;
      if (!this.fH)
        break label148;
      this.fH = false;
      i = j;
    }
    label146: label148: 
    while (true)
    {
      if (this.fw != 0)
      {
        long l1 = this.fE;
        long l2 = this.fw;
        long l3 = System.currentTimeMillis();
        if (i <= l1 + l2 - l3);
      }
      for (j = 0; ; j = 1)
      {
        if (j == 0)
          break label146;
        this.fw = i;
        this.fE = System.currentTimeMillis();
        jdMethod_new(this.fw);
        return;
        if (this.fG > 1000.0F)
        {
          i = 120000;
          break;
        }
        if (this.fG > 500.0F)
        {
          i = 60000;
          break;
        }
        i = 10000;
        break;
      }
      break;
    }
  }

  private boolean a5()
  {
    boolean bool2;
    if ((this.fC == null) || (this.fC.isEmpty()))
      bool2 = false;
    Iterator localIterator;
    boolean bool1;
    do
    {
      return bool2;
      localIterator = this.fC.iterator();
      bool1 = false;
      bool2 = bool1;
    }
    while (!localIterator.hasNext());
    if (((BDNotifyListener)localIterator.next()).Notified < 3)
      bool1 = true;
    while (true)
      break;
  }

  private void jdMethod_for(BDLocation paramBDLocation)
  {
    if ((paramBDLocation.getLocType() != 61) && (paramBDLocation.getLocType() != 161) && (paramBDLocation.getLocType() != 65))
      jdMethod_new(120000L);
    while ((System.currentTimeMillis() - this.fI < 5000L) || (this.fC == null))
      return;
    this.fD = paramBDLocation;
    this.fI = System.currentTimeMillis();
    float[] arrayOfFloat = new float[1];
    Iterator localIterator = this.fC.iterator();
    float f1 = 3.4028235E+38F;
    if (localIterator.hasNext())
    {
      BDNotifyListener localBDNotifyListener = (BDNotifyListener)localIterator.next();
      Location.distanceBetween(paramBDLocation.getLatitude(), paramBDLocation.getLongitude(), localBDNotifyListener.mLatitudeC, localBDNotifyListener.mLongitudeC, arrayOfFloat);
      float f2 = arrayOfFloat[0] - localBDNotifyListener.mRadius - paramBDLocation.getRadius();
      if (f2 > 0.0F)
      {
        if (f2 >= f1)
          break label210;
        f1 = f2;
      }
      label210: 
      while (true)
      {
        break;
        if (localBDNotifyListener.Notified < 3)
        {
          localBDNotifyListener.Notified += 1;
          localBDNotifyListener.onNotify(paramBDLocation, arrayOfFloat[0]);
          if (localBDNotifyListener.Notified < 3)
            this.fH = true;
        }
      }
    }
    if (f1 < this.fG)
      this.fG = f1;
    this.fw = 0;
    a3();
  }

  private void jdMethod_new(long paramLong)
  {
    try
    {
      if (this.fB != null)
        this.fy.cancel(this.fB);
      this.fB = PendingIntent.getBroadcast(this.fx, 0, new Intent("android.com.baidu.location.TIMER.NOTIFY"), 134217728);
      if (this.fB == null)
        return;
      this.fy.set(0, System.currentTimeMillis() + paramLong, this.fB);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void a4()
  {
    if (this.fB != null)
      this.fy.cancel(this.fB);
    this.fD = null;
    this.fI = 0L;
    if (this.fJ)
      this.fx.unregisterReceiver(this.fF);
    this.fJ = false;
  }

  public int jdMethod_do(BDNotifyListener paramBDNotifyListener)
  {
    if (this.fC == null)
      this.fC = new ArrayList();
    this.fC.add(paramBDNotifyListener);
    paramBDNotifyListener.isAdded = true;
    paramBDNotifyListener.mNotifyCache = this;
    if (!this.fJ)
    {
      this.fx.registerReceiver(this.fF, new IntentFilter("android.com.baidu.location.TIMER.NOTIFY"));
      this.fJ = true;
    }
    if (paramBDNotifyListener.mCoorType == null)
      return 1;
    Object localObject;
    if (!paramBDNotifyListener.mCoorType.equals("gcj02"))
    {
      localObject = Jni.jdMethod_if(paramBDNotifyListener.mLongitude, paramBDNotifyListener.mLatitude, paramBDNotifyListener.mCoorType + "2gcj");
      paramBDNotifyListener.mLongitudeC = localObject[0];
      paramBDNotifyListener.mLatitudeC = localObject[1];
    }
    if ((this.fD == null) || (System.currentTimeMillis() - this.fI > 30000L))
      this.fK.requestNotifyLocation();
    while (true)
    {
      a3();
      return 1;
      localObject = new float[1];
      Location.distanceBetween(this.fD.getLatitude(), this.fD.getLongitude(), paramBDNotifyListener.mLatitudeC, paramBDNotifyListener.mLongitudeC, (float[])localObject);
      float f = localObject[0] - paramBDNotifyListener.mRadius - this.fD.getRadius();
      if (f > 0.0F)
      {
        if (f < this.fG)
          this.fG = f;
      }
      else if (paramBDNotifyListener.Notified < 3)
      {
        paramBDNotifyListener.Notified += 1;
        paramBDNotifyListener.onNotify(this.fD, localObject[0]);
        if (paramBDNotifyListener.Notified < 3)
          this.fH = true;
      }
    }
  }

  public int jdMethod_for(BDNotifyListener paramBDNotifyListener)
  {
    if (this.fC == null)
      return 0;
    if (this.fC.contains(paramBDNotifyListener))
      this.fC.remove(paramBDNotifyListener);
    if ((this.fC.size() == 0) && (this.fB != null))
      this.fy.cancel(this.fB);
    return 1;
  }

  public void jdMethod_if(BDNotifyListener paramBDNotifyListener)
  {
    if (paramBDNotifyListener.mCoorType == null)
      return;
    Object localObject;
    if (!paramBDNotifyListener.mCoorType.equals("gcj02"))
    {
      localObject = Jni.jdMethod_if(paramBDNotifyListener.mLongitude, paramBDNotifyListener.mLatitude, paramBDNotifyListener.mCoorType + "2gcj");
      paramBDNotifyListener.mLongitudeC = localObject[0];
      paramBDNotifyListener.mLatitudeC = localObject[1];
    }
    if ((this.fD == null) || (System.currentTimeMillis() - this.fI > 300000L))
      this.fK.requestNotifyLocation();
    while (true)
    {
      a3();
      return;
      localObject = new float[1];
      Location.distanceBetween(this.fD.getLatitude(), this.fD.getLongitude(), paramBDNotifyListener.mLatitudeC, paramBDNotifyListener.mLongitudeC, (float[])localObject);
      float f = localObject[0] - paramBDNotifyListener.mRadius - this.fD.getRadius();
      if (f > 0.0F)
      {
        if (f < this.fG)
          this.fG = f;
      }
      else if (paramBDNotifyListener.Notified < 3)
      {
        paramBDNotifyListener.Notified += 1;
        paramBDNotifyListener.onNotify(this.fD, localObject[0]);
        if (paramBDNotifyListener.Notified < 3)
          this.fH = true;
      }
    }
  }

  public class a
    implements BDLocationListener
  {
    public a()
    {
    }

    public void onReceiveLocation(BDLocation paramBDLocation)
    {
      if ((a.jdMethod_do(a.this) != null) && (a.jdMethod_do(a.this).size() > 0))
        a.jdMethod_if(a.this, paramBDLocation);
    }
  }

  public class b extends BroadcastReceiver
  {
    public b()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if ((a.jdMethod_do(a.this) == null) || (a.jdMethod_do(a.this).isEmpty()))
        return;
      a.jdMethod_if(a.this).requestNotifyLocation();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.d.a
 * JD-Core Version:    0.6.2
 */