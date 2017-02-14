package com.baidu.location;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.text.TextUtils;
import com.baidu.location.b.j;
import com.baidu.location.d.a;
import com.baidu.location.e.p;
import com.baidu.location.e.p.a;
import java.util.ArrayList;
import java.util.Iterator;

public final class LocationClient
  implements com.baidu.location.b.f, p.a
{
  private static final int lF = 5;
  private static final int lG = 12;
  private static final int lI = 6;
  private static final int lJ = 2;
  private static final int lQ = 11;
  private static final int lU = 4;
  private static final int lf = 10;
  private static final String lh = "baidu_location_Client";
  private static final int lk = 1;
  private static final int lo = 1000;
  private static final int lr = 3;
  private static final int ls = 8;
  private static final int lv = 9;
  private static final int lx = 7;
  private Context lA = null;
  private boolean lB = false;
  private Messenger lC = null;
  private long lD = 0L;
  private LocationClientOption lE = new LocationClientOption();
  private Boolean lH = Boolean.valueOf(true);
  private boolean lK = false;
  private long lL = 0L;
  private long lM = 0L;
  private ServiceConnection lN = new b(this);
  private String lO;
  private BDLocation lP = null;
  private String lR = null;
  private boolean lS = false;
  private String lT = null;
  private boolean lV = true;
  private p lW = null;
  private ArrayList lX = null;
  private boolean le = false;
  private a lg = new a(null);
  private boolean li = false;
  private final Object lj = new Object();
  private b ll = null;
  private Boolean lm = Boolean.valueOf(false);
  private a ln = null;
  private Boolean lp = Boolean.valueOf(false);
  private boolean lq = false;
  private BDLocationListener lt = null;
  private boolean lu = false;
  private boolean lw = false;
  private boolean ly;
  private final Messenger lz = new Messenger(this.lg);

  public LocationClient(Context paramContext)
  {
    this.lA = paramContext;
    this.lE = new LocationClientOption();
  }

  public LocationClient(Context paramContext, LocationClientOption paramLocationClientOption)
  {
    this.lA = paramContext;
    this.lE = paramLocationClientOption;
    if (this.lW == null)
    {
      this.lW = new p(this.lA, this.lE, this);
      this.lW.ct();
    }
  }

  private void jdMethod_byte(BDLocation paramBDLocation)
  {
    if (this.lV);
    while (true)
    {
      return;
      this.lP = paramBDLocation;
      if ((!this.li) && (paramBDLocation.getLocType() == 161))
        this.lB = true;
      if (this.lX != null)
      {
        Iterator localIterator = this.lX.iterator();
        while (localIterator.hasNext())
          ((BDLocationListener)localIterator.next()).onReceiveLocation(paramBDLocation);
      }
    }
  }

  private void d(Message paramMessage)
  {
    if ((paramMessage == null) || (paramMessage.obj == null));
    do
    {
      return;
      paramMessage = (BDLocationListener)paramMessage.obj;
    }
    while ((this.lX == null) || (!this.lX.contains(paramMessage)));
    this.lX.remove(paramMessage);
  }

  private Bundle dJ()
  {
    if (this.lE == null)
      return null;
    Bundle localBundle = new Bundle();
    localBundle.putString("packName", this.lT);
    localBundle.putString("prodName", this.lE.prodName);
    localBundle.putString("coorType", this.lE.coorType);
    localBundle.putString("addrType", this.lE.addrType);
    localBundle.putBoolean("openGPS", this.lE.openGps);
    localBundle.putBoolean("location_change_notify", this.lE.location_change_notify);
    localBundle.putInt("scanSpan", this.lE.scanSpan);
    localBundle.putInt("timeOut", this.lE.timeOut);
    localBundle.putInt("priority", this.lE.priority);
    localBundle.putBoolean("map", this.lp.booleanValue());
    localBundle.putBoolean("import", this.lm.booleanValue());
    localBundle.putBoolean("needDirect", this.lE.mIsNeedDeviceDirect);
    localBundle.putBoolean("isneedaptag", this.lE.isNeedAptag);
    localBundle.putBoolean("isneedpoiregion", this.lE.isNeedPoiRegion);
    localBundle.putBoolean("isneedregular", this.lE.isNeedRegular);
    localBundle.putBoolean("isneedaptagd", this.lE.isNeedAptagd);
    localBundle.putBoolean("isneedaltitude", this.lE.isNeedAltitude);
    return localBundle;
  }

  private void dK()
  {
    if (this.lK == true)
      return;
    if (this.lH.booleanValue())
    {
      if (this.lW == null)
      {
        this.lW = new p(this.lA, this.lE, this);
        this.lW.ct();
      }
      this.lW.cr();
      this.lH = Boolean.valueOf(false);
    }
    this.lT = this.lA.getPackageName();
    this.lR = (this.lT + "_bdls_v2.9");
    Intent localIntent = new Intent(this.lA, f.class);
    try
    {
      localIntent.putExtra("debug_dev", this.ly);
      label134: if (this.lE == null)
        this.lE = new LocationClientOption();
      localIntent.putExtra("cache_exception", this.lE.isIgnoreCacheException);
      localIntent.putExtra("kill_process", this.lE.isIgnoreKillProcess);
      try
      {
        this.lA.bindService(localIntent, this.lN, 1);
        return;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        this.lK = false;
        return;
      }
    }
    catch (Exception localException2)
    {
      break label134;
    }
  }

  // ERROR //
  private void dL()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 108	com/baidu/location/LocationClient:lK	Z
    //   4: ifeq +10 -> 14
    //   7: aload_0
    //   8: getfield 112	com/baidu/location/LocationClient:lC	Landroid/os/Messenger;
    //   11: ifnonnull +4 -> 15
    //   14: return
    //   15: aconst_null
    //   16: bipush 12
    //   18: invokestatic 394	android/os/Message:obtain	(Landroid/os/Handler;I)Landroid/os/Message;
    //   21: astore_1
    //   22: aload_1
    //   23: aload_0
    //   24: getfield 124	com/baidu/location/LocationClient:lz	Landroid/os/Messenger;
    //   27: putfield 397	android/os/Message:replyTo	Landroid/os/Messenger;
    //   30: aload_0
    //   31: getfield 112	com/baidu/location/LocationClient:lC	Landroid/os/Messenger;
    //   34: aload_1
    //   35: invokevirtual 400	android/os/Messenger:send	(Landroid/os/Message;)V
    //   38: aload_0
    //   39: getfield 110	com/baidu/location/LocationClient:lA	Landroid/content/Context;
    //   42: aload_0
    //   43: getfield 179	com/baidu/location/LocationClient:lN	Landroid/content/ServiceConnection;
    //   46: invokevirtual 404	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
    //   49: aload_0
    //   50: getfield 140	com/baidu/location/LocationClient:lj	Ljava/lang/Object;
    //   53: astore_1
    //   54: aload_1
    //   55: monitorenter
    //   56: aload_0
    //   57: getfield 134	com/baidu/location/LocationClient:lw	Z
    //   60: iconst_1
    //   61: if_icmpne +19 -> 80
    //   64: aload_0
    //   65: getfield 117	com/baidu/location/LocationClient:lg	Lcom/baidu/location/LocationClient$a;
    //   68: aload_0
    //   69: getfield 136	com/baidu/location/LocationClient:ll	Lcom/baidu/location/LocationClient$b;
    //   72: invokevirtual 408	com/baidu/location/LocationClient$a:removeCallbacks	(Ljava/lang/Runnable;)V
    //   75: aload_0
    //   76: iconst_0
    //   77: putfield 134	com/baidu/location/LocationClient:lw	Z
    //   80: aload_1
    //   81: monitorexit
    //   82: aload_0
    //   83: getfield 146	com/baidu/location/LocationClient:ln	Lcom/baidu/location/d/a;
    //   86: ifnull +10 -> 96
    //   89: aload_0
    //   90: getfield 146	com/baidu/location/LocationClient:ln	Lcom/baidu/location/d/a;
    //   93: invokevirtual 413	com/baidu/location/d/a:a4	()V
    //   96: aload_0
    //   97: aconst_null
    //   98: putfield 112	com/baidu/location/LocationClient:lC	Landroid/os/Messenger;
    //   101: aload_0
    //   102: iconst_0
    //   103: putfield 132	com/baidu/location/LocationClient:lS	Z
    //   106: aload_0
    //   107: iconst_0
    //   108: putfield 152	com/baidu/location/LocationClient:lq	Z
    //   111: aload_0
    //   112: iconst_0
    //   113: putfield 108	com/baidu/location/LocationClient:lK	Z
    //   116: aload_0
    //   117: iconst_0
    //   118: putfield 170	com/baidu/location/LocationClient:lB	Z
    //   121: aload_0
    //   122: iconst_0
    //   123: putfield 172	com/baidu/location/LocationClient:li	Z
    //   126: return
    //   127: astore_1
    //   128: aload_1
    //   129: invokevirtual 389	java/lang/Exception:printStackTrace	()V
    //   132: goto -94 -> 38
    //   135: astore_2
    //   136: aload_1
    //   137: monitorexit
    //   138: aload_2
    //   139: athrow
    //   140: astore_2
    //   141: goto -61 -> 80
    //   144: astore_1
    //   145: goto -96 -> 49
    //
    // Exception table:
    //   from	to	target	type
    //   30	38	127	java/lang/Exception
    //   56	80	135	finally
    //   80	82	135	finally
    //   136	138	135	finally
    //   56	80	140	java/lang/Exception
    //   38	49	144	java/lang/Exception
  }

  private void dM()
  {
    if (this.lC == null)
      return;
    Message localMessage = Message.obtain(null, 22);
    try
    {
      localMessage.replyTo = this.lz;
      this.lC.send(localMessage);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  private void dN()
  {
    Message localMessage = Message.obtain(null, 28);
    try
    {
      localMessage.replyTo = this.lz;
      this.lC.send(localMessage);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  private void dO()
  {
    if (this.lC == null)
      return;
    if (((System.currentTimeMillis() - this.lD > 3000L) || (!this.lE.location_change_notify) || (this.lS)) && ((!this.lq) || (System.currentTimeMillis() - this.lM > 20000L) || (this.lS)))
    {
      ??? = Message.obtain(null, 22);
      if (this.lS)
      {
        Bundle localBundle = new Bundle();
        localBundle.putBoolean("isWaitingLocTag", this.lS);
        ((Message)???).setData(localBundle);
      }
    }
    try
    {
      ((Message)???).replyTo = this.lz;
      this.lC.send((Message)???);
      this.lL = System.currentTimeMillis();
      this.lu = true;
      synchronized (this.lj)
      {
        if ((this.lE != null) && (this.lE.scanSpan >= 1000) && (!this.lw))
        {
          if (this.ll == null)
            this.ll = new b(null);
          this.lg.postDelayed(this.ll, this.lE.scanSpan);
          this.lw = true;
        }
        return;
      }
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  private void e(Message paramMessage)
  {
    this.lS = false;
    if ((paramMessage == null) || (paramMessage.obj == null));
    while (true)
    {
      return;
      paramMessage = (LocationClientOption)paramMessage.obj;
      if (this.lE.equals(paramMessage))
        continue;
      if (this.lE.scanSpan != paramMessage.scanSpan);
      try
      {
        synchronized (this.lj)
        {
          if (this.lw == true)
          {
            this.lg.removeCallbacks(this.ll);
            this.lw = false;
          }
          if ((paramMessage.scanSpan >= 1000) && (!this.lw))
          {
            if (this.ll == null)
              this.ll = new b(null);
            this.lg.postDelayed(this.ll, paramMessage.scanSpan);
            this.lw = true;
          }
          label142: this.lE = new LocationClientOption(paramMessage);
          if (this.lC == null)
            continue;
          try
          {
            paramMessage = Message.obtain(null, 15);
            paramMessage.replyTo = this.lz;
            paramMessage.setData(dJ());
            this.lC.send(paramMessage);
            return;
          }
          catch (Exception paramMessage)
          {
            paramMessage.printStackTrace();
            return;
          }
        }
      }
      catch (Exception localException)
      {
        break label142;
      }
    }
  }

  public static BDLocation getBDLocationInCoorType(BDLocation paramBDLocation, String paramString)
  {
    BDLocation localBDLocation = new BDLocation(paramBDLocation);
    paramBDLocation = Jni.jdMethod_if(paramBDLocation.getLongitude(), paramBDLocation.getLatitude(), paramString);
    localBDLocation.setLatitude(paramBDLocation[1]);
    localBDLocation.setLongitude(paramBDLocation[0]);
    return localBDLocation;
  }

  private void h(Message paramMessage)
  {
    paramMessage = paramMessage.getData();
    paramMessage.setClassLoader(BDLocation.class.getClassLoader());
    paramMessage = (BDLocation)paramMessage.getParcelable("locStr");
    if ((this.lt == null) || ((this.lE != null) && (this.lE.isDisableCache()) && (paramMessage.getLocType() == 65)))
      return;
    this.lt.onReceiveLocation(paramMessage);
  }

  private void i(Message paramMessage)
  {
    if ((paramMessage == null) || (paramMessage.obj == null));
    do
    {
      return;
      paramMessage = (BDNotifyListener)paramMessage.obj;
    }
    while (this.ln == null);
    this.ln.jdMethod_for(paramMessage);
  }

  private void jdMethod_if(Message paramMessage, int paramInt)
  {
    if (!this.lK)
      return;
    paramMessage = paramMessage.getData();
    paramMessage.setClassLoader(BDLocation.class.getClassLoader());
    this.lP = ((BDLocation)paramMessage.getParcelable("locStr"));
    if (this.lP.getLocType() == 61)
      this.lD = System.currentTimeMillis();
    k(paramInt);
  }

  private void j(Message paramMessage)
  {
    if ((paramMessage == null) || (paramMessage.obj == null));
    do
    {
      return;
      paramMessage = (BDLocationListener)paramMessage.obj;
      if (this.lX == null)
        this.lX = new ArrayList();
    }
    while (this.lX.contains(paramMessage));
    this.lX.add(paramMessage);
  }

  private void k(int paramInt)
  {
    if (this.lP.getCoorType() == null)
      this.lP.setCoorType(this.lE.coorType);
    if ((this.lu == true) || ((this.lE.location_change_notify == true) && (this.lP.getLocType() == 61)) || (this.lP.getLocType() == 66) || (this.lP.getLocType() == 67) || (this.lq) || (this.lP.getLocType() == 161))
    {
      if (this.lX != null)
      {
        Iterator localIterator = this.lX.iterator();
        while (localIterator.hasNext())
          ((BDLocationListener)localIterator.next()).onReceiveLocation(this.lP);
      }
      if ((this.lP.getLocType() != 66) && (this.lP.getLocType() != 67));
    }
    else
    {
      return;
    }
    this.lu = false;
    this.lM = System.currentTimeMillis();
  }

  private void k(Message paramMessage)
  {
    if ((paramMessage == null) || (paramMessage.obj == null))
      return;
    this.lt = ((BDLocationListener)paramMessage.obj);
  }

  private void jdMethod_void(Message paramMessage)
  {
    if ((paramMessage == null) || (paramMessage.obj == null))
      return;
    paramMessage = (BDNotifyListener)paramMessage.obj;
    if (this.ln == null)
      this.ln = new a(this.lA, this);
    this.ln.jdMethod_do(paramMessage);
  }

  public String getAccessKey()
  {
    try
    {
      this.lO = j.a(this.lA);
      if (TextUtils.isEmpty(this.lO))
        throw new IllegalStateException("please setting key from Manifest.xml");
      String str = String.format("KEY=%s;SHA1=%s", new Object[] { this.lO, j.jdMethod_if(this.lA) });
      return str;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public BDLocation getLastKnownLocation()
  {
    return this.lP;
  }

  public LocationClientOption getLocOption()
  {
    return this.lE;
  }

  public String getVersion()
  {
    return "6.1.2";
  }

  public boolean isStarted()
  {
    return this.lK;
  }

  public void onReceiveLocation(BDLocation paramBDLocation)
  {
    if ((this.li == true) && (!this.lB));
    while (paramBDLocation == null)
      return;
    Message localMessage = this.lg.obtainMessage(701);
    localMessage.obj = paramBDLocation;
    localMessage.sendToTarget();
  }

  public void registerLocationListener(BDLocationListener paramBDLocationListener)
  {
    if (paramBDLocationListener == null)
      throw new IllegalStateException("please set a non-null listener");
    Message localMessage = this.lg.obtainMessage(5);
    localMessage.obj = paramBDLocationListener;
    localMessage.sendToTarget();
  }

  public void registerNotify(BDNotifyListener paramBDNotifyListener)
  {
    Message localMessage = this.lg.obtainMessage(9);
    localMessage.obj = paramBDNotifyListener;
    localMessage.sendToTarget();
  }

  public void registerNotifyLocationListener(BDLocationListener paramBDLocationListener)
  {
    Message localMessage = this.lg.obtainMessage(8);
    localMessage.obj = paramBDLocationListener;
    localMessage.sendToTarget();
  }

  public void removeNotifyEvent(BDNotifyListener paramBDNotifyListener)
  {
    Message localMessage = this.lg.obtainMessage(10);
    localMessage.obj = paramBDNotifyListener;
    localMessage.sendToTarget();
  }

  public int requestLocation()
  {
    if ((this.lC == null) || (this.lz == null))
      return 1;
    if ((this.lX == null) || (this.lX.size() < 1))
      return 2;
    if (System.currentTimeMillis() - this.lL < 1000L)
      return 6;
    Message localMessage = this.lg.obtainMessage(4);
    localMessage.arg1 = 0;
    localMessage.sendToTarget();
    return 0;
  }

  public void requestNotifyLocation()
  {
    this.lg.obtainMessage(11).sendToTarget();
  }

  public int requestOfflineLocation()
  {
    if ((this.lC == null) || (this.lz == null))
      return 1;
    if ((this.lX == null) || (this.lX.size() < 1))
      return 2;
    this.lg.obtainMessage(12).sendToTarget();
    return 0;
  }

  public void setLocOption(LocationClientOption paramLocationClientOption)
  {
    LocationClientOption localLocationClientOption = paramLocationClientOption;
    if (paramLocationClientOption == null)
      localLocationClientOption = new LocationClientOption();
    if (this.lW == null)
    {
      this.lW = new p(this.lA, localLocationClientOption, this);
      this.lW.ct();
    }
    paramLocationClientOption = this.lg.obtainMessage(3);
    paramLocationClientOption.obj = localLocationClientOption;
    paramLocationClientOption.sendToTarget();
  }

  public void start()
  {
    this.lV = false;
    this.lg.obtainMessage(1).sendToTarget();
  }

  public void stop()
  {
    this.lV = true;
    this.lg.obtainMessage(2).sendToTarget();
    this.lW = null;
  }

  public void unRegisterLocationListener(BDLocationListener paramBDLocationListener)
  {
    if (paramBDLocationListener == null)
      throw new IllegalStateException("please set a non-null listener");
    Message localMessage = this.lg.obtainMessage(6);
    localMessage.obj = paramBDLocationListener;
    localMessage.sendToTarget();
  }

  public boolean updateLocation(Location paramLocation)
  {
    if ((this.lC == null) || (this.lz == null) || (paramLocation == null))
      return false;
    try
    {
      Message localMessage = Message.obtain(null, 57);
      localMessage.obj = paramLocation;
      this.lC.send(localMessage);
      return true;
    }
    catch (Exception paramLocation)
    {
      while (true)
        paramLocation.printStackTrace();
    }
  }

  private class a extends Handler
  {
    private a()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        super.handleMessage(paramMessage);
      case 7:
      case 3:
      case 8:
      case 5:
      case 6:
      case 9:
      case 10:
      case 1:
      case 2:
      case 11:
      case 4:
      case 12:
      case 54:
      case 55:
      case 21:
        Object localObject;
        do
        {
          do
          {
            do
            {
              return;
              LocationClient.jdMethod_byte(LocationClient.this, paramMessage);
              return;
              LocationClient.jdMethod_try(LocationClient.this, paramMessage);
              return;
              LocationClient.jdMethod_new(LocationClient.this, paramMessage);
              return;
              LocationClient.jdMethod_int(LocationClient.this, paramMessage);
              return;
              LocationClient.jdMethod_do(LocationClient.this, paramMessage);
              return;
              LocationClient.jdMethod_for(LocationClient.this, paramMessage);
              return;
              LocationClient.jdMethod_for(LocationClient.this);
              return;
              LocationClient.jdMethod_else(LocationClient.this);
              return;
              LocationClient.i(LocationClient.this);
              return;
              LocationClient.jdMethod_byte(LocationClient.this);
              return;
              LocationClient.e(LocationClient.this);
              return;
            }
            while (!LocationClient.jdMethod_char(LocationClient.this).location_change_notify);
            LocationClient.jdMethod_do(LocationClient.this, true);
            return;
          }
          while (!LocationClient.jdMethod_char(LocationClient.this).location_change_notify);
          LocationClient.jdMethod_do(LocationClient.this, false);
          return;
          localObject = paramMessage.getData();
          ((Bundle)localObject).setClassLoader(BDLocation.class.getClassLoader());
          localObject = (BDLocation)((Bundle)localObject).getParcelable("locStr");
        }
        while ((!LocationClient.jdMethod_goto(LocationClient.this)) && (LocationClient.j(LocationClient.this) == true) && (((BDLocation)localObject).getLocType() == 66));
        if ((!LocationClient.jdMethod_goto(LocationClient.this)) && (LocationClient.j(LocationClient.this) == true))
        {
          LocationClient.jdMethod_if(LocationClient.this, true);
          return;
        }
        if (!LocationClient.jdMethod_goto(LocationClient.this))
          LocationClient.jdMethod_if(LocationClient.this, true);
        LocationClient.jdMethod_if(LocationClient.this, paramMessage, 21);
        return;
      case 26:
        LocationClient.jdMethod_if(LocationClient.this, paramMessage, 26);
        return;
      case 27:
        LocationClient.jdMethod_if(LocationClient.this, paramMessage);
        return;
      case 701:
      }
      LocationClient.jdMethod_if(LocationClient.this, (BDLocation)paramMessage.obj);
    }
  }

  private class b
    implements Runnable
  {
    private b()
    {
    }

    public void run()
    {
      synchronized (LocationClient.jdMethod_long(LocationClient.this))
      {
        LocationClient.jdMethod_int(LocationClient.this, false);
        if ((LocationClient.h(LocationClient.this) == null) || (LocationClient.jdMethod_try(LocationClient.this) == null))
          return;
        if ((LocationClient.d(LocationClient.this) == null) || (LocationClient.d(LocationClient.this).size() < 1))
          return;
      }
      if (LocationClient.jdMethod_if(LocationClient.this))
      {
        if (LocationClient.jdMethod_void(LocationClient.this) == null)
          LocationClient.jdMethod_if(LocationClient.this, new b(LocationClient.this));
        LocationClient.jdMethod_new(LocationClient.this).postDelayed(LocationClient.jdMethod_void(LocationClient.this), LocationClient.jdMethod_char(LocationClient.this).scanSpan);
        return;
      }
      LocationClient.jdMethod_new(LocationClient.this).obtainMessage(4).sendToTarget();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.LocationClient
 * JD-Core Version:    0.6.2
 */