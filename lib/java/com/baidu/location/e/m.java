package com.baidu.location.e;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import com.baidu.location.Address;
import com.baidu.location.BDLocation;
import com.baidu.location.c.d.b;
import com.baidu.location.c.d.c;
import com.baidu.location.h.e;
import com.baidu.location.h.g;
import com.baidu.location.h.h;
import com.baidu.location.h.j;
import java.util.List;

public class m extends b
  implements com.baidu.location.b.f
{
  private static m fV = null;
  private h f0 = null;
  private List f1 = null;
  private long f2 = 0L;
  private long f3 = 0L;
  private h f4 = null;
  private boolean f5 = false;
  private double f6;
  private com.baidu.location.h.f f7 = null;
  private boolean f8 = false;
  private double f9;
  public final Handler fO = new b.b(this);
  final int fR = 1000;
  public b.a fS = null;
  private volatile boolean fT = false;
  private boolean fU = false;
  private long fW = 0L;
  private boolean fX = false;
  private b fY = null;
  private String fZ = null;
  private long ga = 0L;
  private boolean gb = true;
  private boolean gc = true;
  private boolean gd = true;
  private Address ge = null;
  private BDLocation gf = null;
  private BDLocation gg = null;
  final int gh = 2000;
  private String gi = null;
  private com.baidu.location.h.f gj = null;

  private boolean a8()
  {
    return true;
  }

  private void a9()
  {
    this.fT = false;
    this.fX = false;
    this.gd = false;
    this.fU = false;
    bk();
  }

  public static m bb()
  {
    if (fV == null)
      fV = new m();
    return fV;
  }

  private boolean bi()
  {
    com.baidu.location.b.l locall = null;
    boolean bool2 = false;
    double d = Math.random();
    long l = SystemClock.uptimeMillis();
    Object localObject = com.baidu.location.h.c.a().cO();
    com.baidu.location.h.f localf = com.baidu.location.h.l.a().c8();
    if ((localObject != null) && (((h)localObject).dA()) && ((localf == null) || (localf.dn() == 0)));
    for (int i = 1; ; i = 0)
    {
      localObject = locall;
      if (com.baidu.location.c.d.jdMethod_try().jdMethod_long())
      {
        localObject = locall;
        if (com.baidu.location.c.d.jdMethod_try().jdMethod_case())
          if (i == 0)
          {
            localObject = locall;
            if (0.0D < d)
            {
              localObject = locall;
              if (d >= com.baidu.location.c.d.jdMethod_try().jdMethod_byte());
            }
          }
          else
          {
            localObject = com.baidu.location.c.d.jdMethod_try().jdMethod_if(com.baidu.location.h.c.a().cO(), com.baidu.location.h.l.a().c8(), null, d.c.do, d.b.a);
          }
      }
      boolean bool1 = bool2;
      if (localObject != null)
      {
        bool1 = bool2;
        if (((BDLocation)localObject).getLocType() == 66)
        {
          bool1 = bool2;
          if (this.fT)
          {
            locall = new com.baidu.location.b.l();
            locall.jdMethod_do(this.fW);
            locall.jdMethod_if(l);
            locall.jdMethod_for(l);
            locall.jdMethod_int(SystemClock.uptimeMillis());
            locall.jdMethod_char("ofs");
            if (this.f4 != null)
            {
              locall.jdMethod_else(this.f4.dz());
              locall.jdMethod_else("&offtag=1");
            }
            localObject = new BDLocation((BDLocation)localObject);
            ((BDLocation)localObject).setLocType(161);
            bool1 = bool2;
            if (this.fT)
            {
              com.baidu.location.b.o.aY().jdMethod_if(locall);
              this.fX = true;
              c.br().jdMethod_try((BDLocation)localObject);
              this.gf = ((BDLocation)localObject);
              bool1 = true;
            }
          }
        }
      }
      return bool1;
    }
  }

  private void bk()
  {
    if (this.gf != null)
      o.co().cq();
  }

  private void jdMethod_byte(Message paramMessage)
  {
    if (com.baidu.location.h.d.a().cJ())
    {
      jdMethod_for(paramMessage);
      return;
    }
    jdMethod_int(paramMessage);
  }

  private boolean jdMethod_do(com.baidu.location.h.f paramf)
  {
    boolean bool2 = true;
    this.fQ = com.baidu.location.h.l.a().de();
    boolean bool1;
    if (paramf == this.fQ)
      bool1 = false;
    do
    {
      do
      {
        do
        {
          return bool1;
          bool1 = bool2;
        }
        while (this.fQ == null);
        bool1 = bool2;
      }
      while (paramf == null);
      bool1 = bool2;
    }
    while (!paramf.jdMethod_try(this.fQ));
    return false;
  }

  private boolean jdMethod_do(h paramh)
  {
    boolean bool2 = true;
    this.fP = com.baidu.location.h.c.a().cO();
    boolean bool1;
    if (this.fP == paramh)
      bool1 = false;
    do
    {
      do
      {
        do
        {
          return bool1;
          bool1 = bool2;
        }
        while (this.fP == null);
        bool1 = bool2;
      }
      while (paramh == null);
      bool1 = bool2;
    }
    while (!paramh.jdMethod_case(this.fP));
    return false;
  }

  private void jdMethod_for(Message paramMessage)
  {
    BDLocation localBDLocation = new BDLocation(com.baidu.location.h.d.a().cF());
    if ((com.baidu.location.b.k.cf.equals("all")) || (com.baidu.location.b.k.bX) || (com.baidu.location.b.k.bP))
    {
      float[] arrayOfFloat = new float[2];
      Location.distanceBetween(this.f9, this.f6, localBDLocation.getLatitude(), localBDLocation.getLongitude(), arrayOfFloat);
      if (arrayOfFloat[0] >= 100.0F)
        break label136;
      if (this.ge != null)
        localBDLocation.setAddr(this.ge);
      if (this.fZ != null)
        localBDLocation.setLocationDescribe(this.fZ);
      if (this.f1 != null)
        localBDLocation.setPoiList(this.f1);
    }
    while (true)
    {
      this.gf = localBDLocation;
      this.gg = null;
      c.br().jdMethod_if(localBDLocation, paramMessage);
      return;
      label136: this.fU = true;
      jdMethod_int(null);
    }
  }

  private boolean jdMethod_if(com.baidu.location.h.f paramf)
  {
    boolean bool2 = true;
    boolean bool1;
    if (paramf == null)
      bool1 = false;
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      }
      while (this.gj == null);
      bool1 = bool2;
    }
    while (!e.jdMethod_if(paramf, this.gj, 0.1F));
    return false;
  }

  private boolean jdMethod_if(h paramh)
  {
    boolean bool2 = true;
    boolean bool1;
    if (paramh == null)
      bool1 = false;
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      }
      while (this.f0 == null);
      bool1 = bool2;
    }
    while (!paramh.jdMethod_case(this.f0));
    return false;
  }

  private void jdMethod_int(Message paramMessage)
  {
    if (this.gc)
    {
      this.fW = SystemClock.uptimeMillis();
      jdMethod_new(paramMessage);
    }
    while (this.fT)
      return;
    this.fW = SystemClock.uptimeMillis();
    if (com.baidu.location.h.l.a().c5())
    {
      this.f8 = true;
      this.fO.postDelayed(new a(null), 2000L);
      return;
    }
    jdMethod_new(paramMessage);
  }

  private void jdMethod_new(Message paramMessage)
  {
    if (this.fT);
    String str2;
    long l;
    do
    {
      return;
      if ((System.currentTimeMillis() - this.f2 < 1000L) && (this.gf != null))
      {
        c.br().jdMethod_try(this.gf);
        a9();
        return;
      }
      if (this.fW > 0L)
        com.baidu.location.b.o.aY().aV().jdMethod_do(this.fW);
      while (true)
      {
        this.fT = true;
        this.gb = jdMethod_do(this.f4);
        if ((jdMethod_do(this.f7)) || (this.gb) || (this.gf == null) || (this.fU))
          break;
        if ((this.gg != null) && (System.currentTimeMillis() - this.f3 > 30000L))
        {
          this.gf = this.gg;
          this.gg = null;
        }
        if (l.cg().cd())
          this.gf.setDirection(l.cg().ci());
        c.br().jdMethod_try(this.gf);
        a9();
        return;
        com.baidu.location.b.o.aY().aV().jdMethod_do(SystemClock.uptimeMillis());
      }
      str2 = k(null);
      if (str2 != null)
        break;
      if (this.gf != null)
      {
        c.br().jdMethod_try(this.gf);
        a9();
        return;
      }
      paramMessage = new BDLocation();
      paramMessage.setLocType(62);
      c.br().jdMethod_try(paramMessage);
      a9();
      l = System.currentTimeMillis();
    }
    while (l - this.ga <= 60000L);
    this.ga = l;
    com.baidu.location.b.o.aY().j("TypeCriteriaException");
    return;
    String str1 = str2;
    if (this.gi != null)
    {
      str1 = str2 + this.gi;
      this.gi = null;
    }
    com.baidu.location.b.o.aY().aV().jdMethod_if(SystemClock.uptimeMillis());
    this.fS.a(str1);
    this.f4 = this.fP;
    this.f7 = this.fQ;
    if ((this.f4 != null) && (this.f4.dv() == 0))
    {
      new com.baidu.location.g.b(this.f4, this.f7, true).cB();
      a9();
    }
    while (true)
    {
      if (this.gc == true)
      {
        this.gc = false;
        if ((com.baidu.location.h.l.a().db()) && (paramMessage != null) && (c.br().jdMethod_case(paramMessage) < 1000) && (com.baidu.location.c.d.jdMethod_try().jdMethod_long()))
          new x(this).start();
      }
      this.f2 = System.currentTimeMillis();
      return;
      if (bi())
      {
        this.f4 = this.fP;
        this.f7 = this.fQ;
      }
      if (com.baidu.location.c.d.jdMethod_try().i())
      {
        if (this.fY == null)
          this.fY = new b(null);
        l = com.baidu.location.c.d.jdMethod_try().jdMethod_if(com.baidu.location.h.c.a(com.baidu.location.h.c.a().cR()));
        this.fO.postDelayed(this.fY, l);
        this.f5 = true;
      }
    }
  }

  private void jdMethod_try(Message paramMessage)
  {
    if (paramMessage.getData().getBoolean("isWaitingLocTag", false))
      w.a().b();
    int i = c.br().jdMethod_else(paramMessage);
    switch (i)
    {
    default:
      throw new IllegalArgumentException(String.format("this type %d is illegal", new Object[] { Integer.valueOf(i) }));
    case 3:
      if (com.baidu.location.h.d.a().cJ())
        jdMethod_for(paramMessage);
      return;
    case 1:
      jdMethod_byte(paramMessage);
      return;
    case 2:
    }
    jdMethod_int(paramMessage);
  }

  public void a6()
  {
    int j = 1;
    if ((this.fY != null) && (this.f5))
    {
      this.f5 = false;
      this.fO.removeCallbacks(this.fY);
    }
    for (int i = 1; ; i = 0)
    {
      Object localObject2;
      if (com.baidu.location.h.d.a().cJ())
      {
        localObject1 = new BDLocation(com.baidu.location.h.d.a().cF());
        if ((com.baidu.location.b.k.cf.equals("all")) || (com.baidu.location.b.k.bX) || (com.baidu.location.b.k.bP))
        {
          localObject2 = new float[2];
          Location.distanceBetween(this.f9, this.f6, ((BDLocation)localObject1).getLatitude(), ((BDLocation)localObject1).getLongitude(), (float[])localObject2);
          if (localObject2[0] < 100.0F)
          {
            if (this.ge != null)
              ((BDLocation)localObject1).setAddr(this.ge);
            if (this.fZ != null)
              ((BDLocation)localObject1).setLocationDescribe(this.fZ);
            if (this.f1 != null)
              ((BDLocation)localObject1).setPoiList(this.f1);
          }
        }
        c.br().jdMethod_do((BDLocation)localObject1, 21);
        a9();
        return;
      }
      if (this.fX)
      {
        a9();
        return;
      }
      com.baidu.location.b.o.aY().aV().jdMethod_for(SystemClock.uptimeMillis());
      if (i != 0)
      {
        if ((!com.baidu.location.c.d.jdMethod_try().jdMethod_long()) || (!com.baidu.location.c.d.jdMethod_try().e()))
          break label591;
        localObject2 = com.baidu.location.c.d.jdMethod_try().jdMethod_if(com.baidu.location.h.c.a().cO(), com.baidu.location.h.l.a().c8(), null, d.c.if, d.b.a);
        localObject1 = localObject2;
        if (localObject2 != null)
        {
          localObject1 = localObject2;
          if (((BDLocation)localObject2).getLocType() == 66)
            c.br().jdMethod_do((BDLocation)localObject2, 21);
        }
      }
      label591: for (Object localObject1 = localObject2; ; localObject1 = null)
      {
        if (localObject1 != null)
        {
          localObject2 = localObject1;
          if (((BDLocation)localObject1).getLocType() != 67);
        }
        else
        {
          if ((!this.gb) && (this.gf != null))
            break label493;
          localObject1 = com.baidu.location.c.b.a0().jdMethod_try(false);
          c.br().jdMethod_do((BDLocation)localObject1, 21);
          i = j;
          if (com.baidu.location.b.k.cf.equals("all"))
          {
            i = j;
            if (((BDLocation)localObject1).getAddrStr() == null)
              i = 0;
          }
          j = i;
          if (com.baidu.location.b.k.bX)
          {
            j = i;
            if (((BDLocation)localObject1).getLocationDescribe() == null)
              j = 0;
          }
          i = j;
          if (com.baidu.location.b.k.bP)
          {
            i = j;
            if (((BDLocation)localObject1).getPoiList() == null)
              i = 0;
          }
          localObject2 = localObject1;
          if (i == 0)
          {
            ((BDLocation)localObject1).setLocType(67);
            localObject2 = localObject1;
          }
        }
        com.baidu.location.b.o.aY().aV().jdMethod_int(SystemClock.uptimeMillis());
        if ((localObject2 != null) && (((BDLocation)localObject2).getLocType() != 67))
        {
          this.gf = ((BDLocation)localObject2);
          com.baidu.location.b.o.aY().aV().jdMethod_char("ofs");
          if (this.f4 != null)
            com.baidu.location.b.o.aY().aV().jdMethod_else(this.f4.dz());
          com.baidu.location.b.o.aY().aX();
        }
        while (true)
        {
          this.gg = null;
          a9();
          return;
          label493: c.br().jdMethod_do(this.gf, 21);
          localObject2 = localObject1;
          break;
          this.gf = null;
          com.baidu.location.b.o.aY().aV().jdMethod_char("off");
          if (this.f4 != null)
            com.baidu.location.b.o.aY().aV().jdMethod_else(this.f4.dz());
          com.baidu.location.b.o.aY().aX();
          continue;
          localObject1 = new BDLocation();
          ((BDLocation)localObject1).setLocType(63);
          this.gf = null;
          c.br().jdMethod_do((BDLocation)localObject1, 21);
        }
      }
    }
  }

  public BDLocation a7()
  {
    return this.gf;
  }

  public void ba()
  {
    if (this.f8)
    {
      jdMethod_new(null);
      this.f8 = false;
    }
  }

  public void bc()
  {
    this.fT = false;
    this.f8 = false;
    this.fX = false;
    this.gd = true;
    bf();
  }

  public void bd()
  {
    BDLocation localBDLocation;
    if ((com.baidu.location.c.d.jdMethod_try().jdMethod_long()) && (com.baidu.location.c.d.jdMethod_try().jdMethod_goto()))
    {
      localBDLocation = com.baidu.location.c.d.jdMethod_try().jdMethod_if(com.baidu.location.h.c.a().cO(), com.baidu.location.h.l.a().c8(), null, d.c.if, d.b.a);
      if ((localBDLocation != null) && (localBDLocation.getLocType() != 67))
        break label168;
      localBDLocation = com.baidu.location.c.b.a0().jdMethod_try(false);
    }
    label168: 
    while (true)
    {
      if ((localBDLocation != null) && (localBDLocation.getLocType() == 66))
      {
        int j = 1;
        int i = j;
        if (com.baidu.location.b.k.cf.equals("all"))
        {
          i = j;
          if (localBDLocation.getAddrStr() == null)
            i = 0;
        }
        j = i;
        if (com.baidu.location.b.k.bX)
        {
          j = i;
          if (localBDLocation.getLocationDescribe() == null)
            j = 0;
        }
        i = j;
        if (com.baidu.location.b.k.bP)
        {
          i = j;
          if (localBDLocation.getPoiList() == null)
            i = 0;
        }
        if (i != 0)
          c.br().jdMethod_do(localBDLocation, 21);
      }
      return;
      localBDLocation = com.baidu.location.c.b.a0().jdMethod_try(false);
    }
  }

  public String be()
  {
    return this.fZ;
  }

  public void bf()
  {
    this.gf = null;
  }

  public List bg()
  {
    return this.f1;
  }

  public boolean bh()
  {
    return this.gb;
  }

  public void bj()
  {
    this.gc = true;
    this.fT = false;
  }

  public void jdMethod_do(Message paramMessage)
  {
    jdMethod_try(paramMessage);
  }

  public void jdMethod_if(Message paramMessage)
  {
    if ((this.fY != null) && (this.f5))
    {
      this.f5 = false;
      this.fO.removeCallbacks(this.fY);
    }
    paramMessage = (BDLocation)paramMessage.obj;
    Object localObject = new BDLocation(paramMessage);
    if (paramMessage.hasAddr())
    {
      this.ge = paramMessage.getAddress();
      this.f6 = paramMessage.getLongitude();
      this.f9 = paramMessage.getLatitude();
    }
    if (paramMessage.getLocationDescribe() != null)
    {
      this.fZ = paramMessage.getLocationDescribe();
      this.f6 = paramMessage.getLongitude();
      this.f9 = paramMessage.getLatitude();
    }
    if (paramMessage.getPoiList() != null)
    {
      this.f1 = paramMessage.getPoiList();
      this.f6 = paramMessage.getLongitude();
      this.f9 = paramMessage.getLatitude();
    }
    if (com.baidu.location.h.d.a().cJ())
    {
      paramMessage = new BDLocation(com.baidu.location.h.d.a().cF());
      if ((com.baidu.location.b.k.cf.equals("all")) || (com.baidu.location.b.k.bX) || (com.baidu.location.b.k.bP))
      {
        localObject = new float[2];
        Location.distanceBetween(this.f9, this.f6, paramMessage.getLatitude(), paramMessage.getLongitude(), (float[])localObject);
        if (localObject[0] < 100.0F)
        {
          if (this.ge != null)
            paramMessage.setAddr(this.ge);
          if (this.fZ != null)
            paramMessage.setLocationDescribe(this.fZ);
          if (this.f1 != null)
            paramMessage.setPoiList(this.f1);
        }
      }
      c.br().jdMethod_do(paramMessage, 21);
      a9();
      return;
    }
    if ((paramMessage.getNetworkLocationType() != null) && (paramMessage.getNetworkLocationType().equals("sky")))
    {
      paramMessage.setNetworkLocationType("wf");
      c.br().jdMethod_do(paramMessage, 21);
      this.f3 = System.currentTimeMillis();
      this.gf = paramMessage;
      return;
    }
    if (this.fX)
    {
      localObject = new float[2];
      if (this.gf != null)
        Location.distanceBetween(this.gf.getLatitude(), this.gf.getLongitude(), paramMessage.getLatitude(), paramMessage.getLongitude(), (float[])localObject);
      if (localObject[0] > 10.0F)
      {
        this.gf = paramMessage;
        if (!this.gd)
        {
          this.gd = false;
          c.br().jdMethod_do(paramMessage, 21);
        }
      }
      a9();
      return;
    }
    com.baidu.location.b.o.aY().aV().jdMethod_for(SystemClock.uptimeMillis());
    this.gg = null;
    if ((this.f4 != null) && (this.f4.dv() != 0) && ((paramMessage.getLocationWhere() == 2) || (paramMessage.getLocationWhere() == 0)) && (paramMessage.getLocType() == 167))
      new com.baidu.location.g.b(this.f4, this.f7, true).cB();
    int i;
    if ((paramMessage.getLocType() == 161) && ("cl".equals(paramMessage.getNetworkLocationType())) && (this.gf != null) && (this.gf.getLocType() == 161) && ("wf".equals(this.gf.getNetworkLocationType())) && (System.currentTimeMillis() - this.f3 < 30000L))
    {
      i = 1;
      this.gg = paramMessage;
    }
    while (true)
    {
      if (i != 0)
      {
        c.br().jdMethod_do(this.gf, 21);
        if (!com.baidu.location.b.k.jdMethod_do(paramMessage))
          break label931;
        if (i == 0)
          this.gf = paramMessage;
        label605: i = com.baidu.location.b.k.jdMethod_for(fM, "ssid\":\"", "\"");
        if ((i == -2147483648) || (this.f7 == null))
          break label939;
      }
      label931: label939: for (this.gi = this.f7.j(i); ; this.gi = null)
      {
        if ((com.baidu.location.c.d.jdMethod_try().jdMethod_long()) && (paramMessage.getLocType() == 161) && ("cl".equals(paramMessage.getNetworkLocationType())) && (jdMethod_if(this.f4)))
        {
          com.baidu.location.c.d.jdMethod_try().jdMethod_if(this.f4, null, (BDLocation)localObject, d.c.if, d.b.do);
          this.f0 = this.f4;
        }
        if ((com.baidu.location.c.d.jdMethod_try().jdMethod_long()) && (paramMessage.getLocType() == 161) && ("wf".equals(paramMessage.getNetworkLocationType())))
        {
          com.baidu.location.c.d.jdMethod_try().jdMethod_if(null, this.f7, (BDLocation)localObject, d.c.if, d.b.do);
          this.gj = this.f7;
        }
        com.baidu.location.c.b.a0().jdMethod_if(fM, this.f4, this.f7, (BDLocation)localObject);
        if (com.baidu.location.h.l.a().db())
          com.baidu.location.c.d.jdMethod_try().k();
        a9();
        return;
        c.br().jdMethod_do(paramMessage, 21);
        this.f3 = System.currentTimeMillis();
        com.baidu.location.b.o.aY().aV().jdMethod_int(SystemClock.uptimeMillis());
        if (paramMessage.getLocType() == 161)
        {
          com.baidu.location.b.o.aY().aV().jdMethod_char("ons");
          if (this.f4 == null)
            break;
          com.baidu.location.b.o.aY().aV().jdMethod_else(this.f4.dz());
          break;
        }
        com.baidu.location.b.o.aY().aV().jdMethod_char("onf");
        if (this.f4 != null)
          com.baidu.location.b.o.aY().aV().jdMethod_else(this.f4.dz());
        com.baidu.location.b.o.aY().aX();
        break;
        this.gf = null;
        break label605;
      }
      i = 0;
    }
  }

  public void jdMethod_int(BDLocation paramBDLocation)
  {
    bf();
    this.gf = paramBDLocation;
    this.gf.setIndoorLocMode(false);
  }

  public Address jdMethod_new(BDLocation paramBDLocation)
  {
    if ((com.baidu.location.b.k.cf.equals("all")) || (com.baidu.location.b.k.bX) || (com.baidu.location.b.k.bP))
    {
      float[] arrayOfFloat = new float[2];
      Location.distanceBetween(this.f9, this.f6, paramBDLocation.getLatitude(), paramBDLocation.getLongitude(), arrayOfFloat);
      if (arrayOfFloat[0] < 100.0F)
      {
        if (this.ge != null)
          return this.ge;
      }
      else
      {
        this.fZ = null;
        this.f1 = null;
        this.fU = true;
        jdMethod_int(null);
      }
    }
    return null;
  }

  private class a
    implements Runnable
  {
    private a()
    {
    }

    public void run()
    {
      if (m.jdMethod_do(m.this) == true)
      {
        m.jdMethod_do(m.this, false);
        m.jdMethod_if(m.this, null);
      }
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
      if (m.jdMethod_for(m.this) == true)
      {
        m.jdMethod_if(m.this, false);
        if (!m.jdMethod_if(m.this))
          m.this.bd();
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.e.m
 * JD-Core Version:    0.6.2
 */