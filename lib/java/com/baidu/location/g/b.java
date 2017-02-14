package com.baidu.location.g;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import com.baidu.location.Address;
import com.baidu.location.Address.Builder;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.b.k;
import com.baidu.location.b.o;
import com.baidu.location.h.h;
import com.baidu.location.h.j;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.http.util.EntityUtils;

public class b
  implements com.baidu.location.b.f
{
  private static SimpleDateFormat ji = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  long je = 0L;
  h jf = null;
  private com.baidu.location.b.l jg = new com.baidu.location.b.l();
  com.baidu.location.h.f jh = null;
  boolean jj = false;
  a jk = null;

  public b()
  {
  }

  public b(h paramh, com.baidu.location.h.f paramf, boolean paramBoolean)
  {
    this.jf = paramh;
    this.jh = paramf;
    this.jj = paramBoolean;
    this.jg.aj();
    long l = SystemClock.uptimeMillis();
    this.jg.jdMethod_do(l);
    this.jg.jdMethod_if(l);
  }

  public void cB()
  {
    Object localObject2 = null;
    StringBuffer localStringBuffer = new StringBuffer(1024);
    if ((this.jh != null) && (this.jh.dn() > 1));
    for (String str = this.jh.i(15); ; str = null)
    {
      Object localObject1 = localObject2;
      if (this.jf != null)
      {
        localObject1 = localObject2;
        if (this.jf.du())
          localObject1 = this.jf.dy();
      }
      if ((str != null) || (localObject1 != null))
      {
        localStringBuffer.append("<LocationRQ xmlns=\"http://skyhookwireless.com/wps/2005\"\nversion=\"2.21\"\nstreet-address-lookup=\"full\">\n<authentication version=\"2.2\">\n<key key=\"" + Jni.dI() + "\"\n" + "username=\"BAIDULOC\"/></authentication>\n");
        if (str != null)
          localStringBuffer.append(str);
        if (localObject1 != null)
          localStringBuffer.append((String)localObject1);
        localStringBuffer.append("</LocationRQ>");
        new a().a(localStringBuffer.toString());
        this.je = System.currentTimeMillis();
      }
      return;
    }
  }

  class a extends com.baidu.location.b.m
  {
    public a()
    {
    }

    void a(String paramString)
    {
      this.da = paramString;
      av();
    }

    public void au()
    {
    }

    public void jdMethod_int(boolean paramBoolean)
    {
      b.jdMethod_if(b.this).jdMethod_for(SystemClock.uptimeMillis());
      if ((paramBoolean) && (this.c6 != null))
      {
        try
        {
          Object localObject1 = EntityUtils.toString(this.c6, "utf-8");
          b.this.jk = new a((String)localObject1);
          if ((b.this.jj) && (b.this.jk.b()))
          {
            localObject1 = new BDLocation();
            ((BDLocation)localObject1).setLongitude(b.this.jk.d());
            ((BDLocation)localObject1).setLatitude(b.this.jk.c());
            ((BDLocation)localObject1).setRadius(b.this.jk.e());
            ((BDLocation)localObject1).setLocType(161);
            ((BDLocation)localObject1).setLocationWhere(0);
            ((BDLocation)localObject1).setCoorType("wgs84");
            ((BDLocation)localObject1).setNetworkLocationType("sky");
            Object localObject2 = b.this.jk.c;
            Object localObject3 = b.this.jk.e;
            String str1 = b.this.jk.b;
            String str2 = b.this.jk.f;
            String str3 = b.this.jk.g;
            String str4 = b.this.jk.h;
            localObject2 = new Address.Builder().country((String)localObject2).province((String)localObject3).city(str1).district(str2).street(str3).streetNumber(str4).build();
            localObject3 = new Date();
            ((BDLocation)localObject1).setTime(b.cA().format((Date)localObject3));
            ((BDLocation)localObject1).setOperators(com.baidu.location.h.c.a().cS());
            if (com.baidu.location.e.l.cg().cd())
              ((BDLocation)localObject1).setDirection(com.baidu.location.e.l.cg().ci());
            if (k.cf.equals("all"))
              ((BDLocation)localObject1).setAddr((Address)localObject2);
            if (b.this.jk.a())
            {
              localObject2 = Jni.jdMethod_if(b.this.jk.d(), b.this.jk.c(), "gps2gcj");
              ((BDLocation)localObject1).setCoorType("gcj02");
              ((BDLocation)localObject1).setLongitude(localObject2[0]);
              ((BDLocation)localObject1).setLatitude(localObject2[1]);
            }
            localObject2 = com.baidu.location.e.m.bb().fO.obtainMessage(21);
            ((Message)localObject2).obj = localObject1;
            ((Message)localObject2).sendToTarget();
            b.jdMethod_if(b.this).jdMethod_int(SystemClock.uptimeMillis());
            b.jdMethod_if(b.this).jdMethod_char("skys");
            if (b.this.jf != null)
              b.jdMethod_if(b.this).jdMethod_else(b.this.jf.dz());
            o.aY().jdMethod_if(b.jdMethod_if(b.this));
            return;
          }
          if ((!b.this.jj) || (b.this.jk.b()))
            return;
          localObject1 = new BDLocation();
          ((BDLocation)localObject1).setLocType(167);
          com.baidu.location.e.c.br().jdMethod_try((BDLocation)localObject1);
          b.jdMethod_if(b.this).jdMethod_int(SystemClock.uptimeMillis());
          b.jdMethod_if(b.this).jdMethod_char("skyf");
          if (b.this.jf != null)
            b.jdMethod_if(b.this).jdMethod_else(b.this.jf.dz());
          o.aY().jdMethod_if(b.jdMethod_if(b.this));
          return;
        }
        catch (Exception localException)
        {
        }
      }
      else
      {
        BDLocation localBDLocation = new BDLocation();
        localBDLocation.setLocType(63);
        com.baidu.location.e.c.br().jdMethod_try(localBDLocation);
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.g.b
 * JD-Core Version:    0.6.2
 */