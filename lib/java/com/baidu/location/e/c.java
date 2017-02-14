package com.baidu.location.e;

import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Message;
import android.os.Messenger;
import com.baidu.location.Address;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.LocationClientOption;
import com.baidu.location.b.f;
import com.baidu.location.b.k;
import com.baidu.location.h.d;
import com.baidu.location.h.g;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class c
  implements f
{
  private static c gm = null;
  private boolean gk = false;
  private ArrayList gl = null;
  public boolean gn = true;
  private boolean go = false;

  private void bn()
  {
    bq();
    bs();
  }

  private void bq()
  {
    Iterator localIterator = this.gl.iterator();
    boolean bool1 = false;
    boolean bool2 = false;
    if (localIterator.hasNext())
    {
      a locala = (a)localIterator.next();
      if (locala.c.openGps)
        bool2 = true;
      if (!locala.c.location_change_notify)
        break label89;
      bool1 = true;
    }
    label89: 
    while (true)
    {
      break;
      k.cm = bool1;
      if (this.go != bool2)
      {
        this.go = bool2;
        d.a().jdMethod_char(this.go);
      }
      return;
    }
  }

  public static c br()
  {
    if (gm == null)
      gm = new c();
    return gm;
  }

  private a jdMethod_if(Messenger paramMessenger)
  {
    if (this.gl == null)
      return null;
    Iterator localIterator = this.gl.iterator();
    while (localIterator.hasNext())
    {
      a locala = (a)localIterator.next();
      if (locala.b.equals(paramMessenger))
        return locala;
    }
    return null;
  }

  private void jdMethod_if(a parama)
  {
    if (parama == null)
      return;
    if (jdMethod_if(parama.b) != null)
    {
      a.a(parama, 14);
      return;
    }
    this.gl.add(parama);
    a.a(parama, 13);
  }

  public void bl()
  {
    Iterator localIterator = this.gl.iterator();
    while (localIterator.hasNext())
      ((a)localIterator.next()).b();
  }

  public void bm()
  {
    Iterator localIterator = this.gl.iterator();
    while (localIterator.hasNext())
      ((a)localIterator.next()).a();
  }

  public boolean bo()
  {
    return this.gn;
  }

  public boolean bp()
  {
    return this.go;
  }

  public void bs()
  {
    Iterator localIterator = this.gl.iterator();
    while (localIterator.hasNext())
      ((a)localIterator.next()).c();
  }

  public void bt()
  {
    this.gl.clear();
    bn();
  }

  public String bu()
  {
    Object localObject = new StringBuffer(256);
    if (this.gl.isEmpty())
      return "&prod=" + com.baidu.location.b.c.bj + ":" + com.baidu.location.b.c.bn;
    a locala = (a)this.gl.get(0);
    if (locala.c.prodName != null)
      ((StringBuffer)localObject).append(locala.c.prodName);
    if (locala.a != null)
    {
      ((StringBuffer)localObject).append(":");
      ((StringBuffer)localObject).append(locala.a);
      ((StringBuffer)localObject).append("|");
    }
    localObject = ((StringBuffer)localObject).toString();
    if ((localObject != null) && (!((String)localObject).equals("")))
      return "&prod=" + (String)localObject;
    return null;
  }

  public int jdMethod_case(Message paramMessage)
  {
    if ((paramMessage == null) || (paramMessage.replyTo == null));
    do
    {
      return 1000;
      paramMessage = jdMethod_if(paramMessage.replyTo);
    }
    while ((paramMessage == null) || (paramMessage.c == null));
    return paramMessage.c.scanSpan;
  }

  public void jdMethod_char(Message paramMessage)
  {
    paramMessage = jdMethod_if(paramMessage.replyTo);
    if (paramMessage != null)
      this.gl.remove(paramMessage);
    i.bY().b0();
    l.cg().ch();
    bn();
  }

  public void jdMethod_do(BDLocation paramBDLocation, int paramInt)
  {
    Iterator localIterator = this.gl.iterator();
    try
    {
      while (localIterator.hasNext())
      {
        a locala = (a)localIterator.next();
        locala.a(paramBDLocation, paramInt);
        if (locala.d > 4)
          localIterator.remove();
      }
    }
    catch (Exception paramBDLocation)
    {
    }
  }

  public int jdMethod_else(Message paramMessage)
  {
    if ((paramMessage == null) || (paramMessage.replyTo == null));
    do
    {
      return 1;
      paramMessage = jdMethod_if(paramMessage.replyTo);
    }
    while ((paramMessage == null) || (paramMessage.c == null));
    return paramMessage.c.priority;
  }

  public boolean jdMethod_goto(Message paramMessage)
  {
    boolean bool2 = true;
    a locala = jdMethod_if(paramMessage.replyTo);
    if (locala == null)
      return false;
    int i = locala.c.scanSpan;
    locala.c.scanSpan = paramMessage.getData().getInt("scanSpan", locala.c.scanSpan);
    if (locala.c.scanSpan < 1000)
    {
      i.bY().bV();
      l.cg().ch();
      this.gn = false;
      if ((locala.c.scanSpan <= 999) || (i >= 1000))
        break label423;
      if (!locala.c.mIsNeedDeviceDirect)
      {
        bool1 = bool2;
        if (!locala.c.isNeedAltitude);
      }
      else
      {
        l.cg().jdMethod_byte(locala.c.mIsNeedDeviceDirect);
        l.cg().jdMethod_case(locala.c.isNeedAltitude);
        l.cg().ce();
      }
    }
    label410: label423: for (boolean bool1 = bool2; ; bool1 = false)
    {
      locala.c.openGps = paramMessage.getData().getBoolean("openGPS", locala.c.openGps);
      String str = paramMessage.getData().getString("coorType");
      LocationClientOption localLocationClientOption = locala.c;
      if ((str != null) && (!str.equals("")))
      {
        label228: localLocationClientOption.coorType = str;
        str = paramMessage.getData().getString("addrType");
        localLocationClientOption = locala.c;
        if ((str == null) || (str.equals("")))
          break label410;
      }
      while (true)
      {
        localLocationClientOption.addrType = str;
        if (!k.cf.equals(locala.c.addrType))
          m.bb().bf();
        locala.c.timeOut = paramMessage.getData().getInt("timeOut", locala.c.timeOut);
        locala.c.location_change_notify = paramMessage.getData().getBoolean("location_change_notify", locala.c.location_change_notify);
        locala.c.priority = paramMessage.getData().getInt("priority", locala.c.priority);
        bn();
        return bool1;
        i.bY().bW();
        this.gn = true;
        break;
        str = locala.c.coorType;
        break label228;
        str = locala.c.addrType;
      }
    }
  }

  public void jdMethod_if(Bundle paramBundle, int paramInt)
  {
    Iterator localIterator = this.gl.iterator();
    try
    {
      while (localIterator.hasNext())
      {
        a locala = (a)localIterator.next();
        a.a(locala, paramInt, paramBundle);
        if (locala.d > 4)
          localIterator.remove();
      }
    }
    catch (Exception paramBundle)
    {
    }
  }

  public void jdMethod_if(BDLocation paramBDLocation, Message paramMessage)
  {
    if ((paramBDLocation == null) || (paramMessage == null));
    do
    {
      do
      {
        return;
        paramMessage = jdMethod_if(paramMessage.replyTo);
      }
      while (paramMessage == null);
      paramMessage.a(paramBDLocation);
    }
    while (paramMessage.d <= 4);
    this.gl.remove(paramMessage);
  }

  public void l(String paramString)
  {
    paramString = new BDLocation(paramString);
    Object localObject = m.bb().jdMethod_new(paramString);
    String str = m.bb().be();
    List localList = m.bb().bg();
    if (localObject != null)
      paramString.setAddr((Address)localObject);
    if (str != null)
      paramString.setLocationDescribe(str);
    if (localList != null)
      paramString.setPoiList(localList);
    localObject = this.gl.iterator();
    while (((Iterator)localObject).hasNext())
      ((a)((Iterator)localObject).next()).b(paramString);
  }

  public void jdMethod_long(Message paramMessage)
  {
    if ((paramMessage == null) || (paramMessage.replyTo == null))
      return;
    jdMethod_if(new a(paramMessage));
    bn();
  }

  public void jdMethod_try(BDLocation paramBDLocation)
  {
    Iterator localIterator = this.gl.iterator();
    try
    {
      while (localIterator.hasNext())
      {
        a locala = (a)localIterator.next();
        locala.a(paramBDLocation);
        if (locala.d > 4)
          localIterator.remove();
      }
    }
    catch (Exception paramBDLocation)
    {
    }
  }

  private class a
  {
    public String a = null;
    public Messenger b = null;
    public LocationClientOption c = new LocationClientOption();
    public int d = 0;

    public a(Message arg2)
    {
      Object localObject;
      this.b = localObject.replyTo;
      this.a = localObject.getData().getString("packName");
      this.c.prodName = localObject.getData().getString("prodName");
      com.baidu.location.b.c.N().jdMethod_if(this.c.prodName, this.a);
      this.c.coorType = localObject.getData().getString("coorType");
      this.c.addrType = localObject.getData().getString("addrType");
      this.c.enableSimulateGps = localObject.getData().getBoolean("enableSimulateGps", false);
      if ((k.cj) || (this.c.enableSimulateGps))
      {
        bool1 = true;
        k.cj = bool1;
        if (!k.cf.equals("all"))
          k.cf = this.c.addrType;
        this.c.openGps = localObject.getData().getBoolean("openGPS");
        this.c.scanSpan = localObject.getData().getInt("scanSpan");
        this.c.timeOut = localObject.getData().getInt("timeOut");
        this.c.priority = localObject.getData().getInt("priority");
        this.c.location_change_notify = localObject.getData().getBoolean("location_change_notify");
        this.c.mIsNeedDeviceDirect = localObject.getData().getBoolean("needDirect", false);
        this.c.isNeedAltitude = localObject.getData().getBoolean("isneedaltitude", false);
        if ((!k.bX) && (!localObject.getData().getBoolean("isneedaptag", false)))
          break label428;
      }
      label428: for (boolean bool1 = true; ; bool1 = false)
      {
        k.bX = bool1;
        if (!k.bP)
        {
          bool1 = bool2;
          if (!localObject.getData().getBoolean("isneedaptagd", false));
        }
        else
        {
          bool1 = true;
        }
        k.bP = bool1;
        if (this.c.scanSpan >= 1000)
          i.bY().bX();
        if ((this.c.mIsNeedDeviceDirect) || (this.c.isNeedAltitude))
        {
          l.cg().jdMethod_byte(this.c.mIsNeedDeviceDirect);
          l.cg().jdMethod_case(this.c.isNeedAltitude);
          l.cg().ce();
        }
        return;
        bool1 = false;
        break;
      }
    }

    private void a(int paramInt)
    {
      Message localMessage = Message.obtain(null, paramInt);
      try
      {
        if (this.b != null)
          this.b.send(localMessage);
        this.d = 0;
        return;
      }
      catch (Exception localException)
      {
        while (!(localException instanceof DeadObjectException));
        this.d += 1;
      }
    }

    private void a(int paramInt, Bundle paramBundle)
    {
      Message localMessage = Message.obtain(null, paramInt);
      localMessage.setData(paramBundle);
      try
      {
        if (this.b != null)
          this.b.send(localMessage);
        this.d = 0;
        return;
      }
      catch (Exception paramBundle)
      {
        if ((paramBundle instanceof DeadObjectException))
          this.d += 1;
        paramBundle.printStackTrace();
      }
    }

    private void a(int paramInt, String paramString, BDLocation paramBDLocation)
    {
      Bundle localBundle = new Bundle();
      localBundle.putParcelable(paramString, paramBDLocation);
      paramString = Message.obtain(null, paramInt);
      paramString.setData(localBundle);
      try
      {
        if (this.b != null)
          this.b.send(paramString);
        this.d = 0;
        return;
      }
      catch (Exception paramString)
      {
        while (!(paramString instanceof DeadObjectException));
        this.d += 1;
      }
    }

    public void a()
    {
      a(23);
    }

    public void a(BDLocation paramBDLocation)
    {
      a(paramBDLocation, 21);
    }

    public void a(BDLocation paramBDLocation, int paramInt)
    {
      paramBDLocation = new BDLocation(paramBDLocation);
      if ((l.cg().cc()) && ((paramBDLocation.getLocType() == 161) || (paramBDLocation.getLocType() == 66)))
        paramBDLocation.setAltitude(l.cg().cf());
      if (paramInt == 21)
        a(27, "locStr", paramBDLocation);
      double d1;
      double d2;
      double[] arrayOfDouble;
      if ((this.c.coorType != null) && (!this.c.coorType.equals("gcj02")))
      {
        d1 = paramBDLocation.getLongitude();
        d2 = paramBDLocation.getLatitude();
        if ((d1 != 4.9E-324D) && (d2 != 4.9E-324D))
        {
          if (((paramBDLocation.getCoorType() == null) || (!paramBDLocation.getCoorType().equals("gcj02"))) && (paramBDLocation.getCoorType() != null))
            break label192;
          arrayOfDouble = Jni.jdMethod_if(d1, d2, this.c.coorType);
          paramBDLocation.setLongitude(arrayOfDouble[0]);
          paramBDLocation.setLatitude(arrayOfDouble[1]);
          paramBDLocation.setCoorType(this.c.coorType);
        }
      }
      while (true)
      {
        a(paramInt, "locStr", paramBDLocation);
        return;
        label192: if ((paramBDLocation.getCoorType() != null) && (paramBDLocation.getCoorType().equals("wgs84")) && (!this.c.coorType.equals("bd09ll")))
        {
          arrayOfDouble = Jni.jdMethod_if(d1, d2, "wgs842mc");
          paramBDLocation.setLongitude(arrayOfDouble[0]);
          paramBDLocation.setLatitude(arrayOfDouble[1]);
          paramBDLocation.setCoorType("wgs84mc");
        }
      }
    }

    public void b()
    {
      a(111);
    }

    public void b(BDLocation paramBDLocation)
    {
      if (this.c.location_change_notify == true)
        a(paramBDLocation);
    }

    public void c()
    {
      if (this.c.location_change_notify == true)
      {
        if (k.cG)
          a(54);
      }
      else
        return;
      a(55);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.e.c
 * JD-Core Version:    0.6.2
 */