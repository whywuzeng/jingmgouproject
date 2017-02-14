package com.baidu.location.e;

import android.os.Bundle;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.b.m;
import com.baidu.location.c.d.b;
import com.baidu.location.c.d.c;
import com.baidu.location.h.h;
import com.baidu.location.h.j;
import com.baidu.location.h.l;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

class w
  implements com.baidu.location.b.f
{
  private static w b;
  private com.baidu.location.h.f c = null;
  private h d = null;
  private long e = 0L;
  private final long f = 1000L;
  private String g = null;

  public static w a()
  {
    try
    {
      if (b == null)
        b = new w();
      w localw = b;
      return localw;
    }
    finally
    {
    }
  }

  private void a(String paramString)
  {
    this.g = paramString;
    Bundle localBundle = new Bundle();
    if ((paramString != null) && (!paramString.equals("")))
      localBundle.putByteArray("locationtag", paramString.getBytes());
    while (true)
    {
      c.br().jdMethod_if(localBundle, 601);
      return;
      localBundle.putByteArray("locationtag", null);
    }
  }

  private boolean a(com.baidu.location.h.f paramf)
  {
    boolean bool2 = true;
    com.baidu.location.h.f localf = l.a().de();
    boolean bool1;
    if (paramf == localf)
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
        while (localf == null);
        bool1 = bool2;
      }
      while (paramf == null);
      bool1 = bool2;
    }
    while (!paramf.jdMethod_try(localf));
    return false;
  }

  private boolean a(h paramh)
  {
    boolean bool2 = true;
    h localh = com.baidu.location.h.c.a().cO();
    boolean bool1;
    if (localh == paramh)
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
        while (localh == null);
        bool1 = bool2;
      }
      while (paramh == null);
      bool1 = bool2;
    }
    while (!paramh.jdMethod_case(localh));
    return false;
  }

  public void b()
  {
    if ((System.currentTimeMillis() - this.e < 1000L) && (this.g != null))
    {
      a(this.g);
      return;
    }
    this.e = System.currentTimeMillis();
    boolean bool1 = a(this.c);
    boolean bool2 = a(this.d);
    if ((!bool1) && (!bool2) && (this.g != null))
    {
      a(this.g);
      return;
    }
    this.d = com.baidu.location.h.c.a().cO();
    this.c = l.a().de();
    StringBuffer localStringBuffer = new StringBuffer(1024);
    if ((this.d != null) && (this.d.du()))
      localStringBuffer.append(this.d.dz());
    if ((this.c != null) && (this.c.dn() > 1))
      localStringBuffer.append(this.c.e(15));
    String str = com.baidu.location.h.d.a().cI();
    if (str != null)
      localStringBuffer.append(str);
    localStringBuffer.append("&sema=aptag");
    localStringBuffer.append(com.baidu.location.b.c.N().jdMethod_do(false));
    localStringBuffer.append(c.br().bu());
    new a().a(localStringBuffer.toString());
  }

  class a extends m
  {
    private String c = null;

    a()
    {
      this.c7 = new ArrayList();
    }

    public void a(String paramString)
    {
      this.c = paramString;
      ao();
    }

    public void au()
    {
      this.dg = 1;
      this.c5 = com.baidu.location.b.k.Z();
      String str = com.baidu.location.b.d.U().S();
      if ((c8 == com.baidu.location.b.g.for) || (c8 == com.baidu.location.b.g.o))
        this.c5 = ("http://" + str + "/sdk.php");
      str = Jni.H(this.c);
      this.c = null;
      this.c7.add(new BasicNameValuePair("bloc", str));
    }

    public void jdMethod_int(boolean paramBoolean)
    {
      if ((paramBoolean) && (this.c6 != null));
      label175: 
      while (true)
      {
        try
        {
          BDLocation localBDLocation1 = new BDLocation(EntityUtils.toString(this.c6, "utf-8"));
          w.a(w.this, localBDLocation1.getLocationDescribe());
          if (this.c7 != null)
            this.c7.clear();
          return;
        }
        catch (Exception localException)
        {
          w.a(w.this, null);
          continue;
        }
        paramBoolean = com.baidu.location.b.k.bX;
        com.baidu.location.b.k.bX = true;
        BDLocation localBDLocation2;
        if ((com.baidu.location.c.d.jdMethod_try().jdMethod_long()) && (com.baidu.location.c.d.jdMethod_try().e()))
        {
          localBDLocation2 = com.baidu.location.c.d.jdMethod_try().jdMethod_if(com.baidu.location.h.c.a().cO(), l.a().c8(), null, d.c.if, d.b.a);
          if ((localBDLocation2 == null) || (localBDLocation2.getLocType() == 67))
            w.a(w.this, null);
        }
        while (true)
        {
          if (paramBoolean)
            break label175;
          com.baidu.location.b.k.bX = false;
          break;
          w.a(w.this, localBDLocation2.getLocationDescribe());
          continue;
          w.a(w.this, null);
        }
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.e.w
 * JD-Core Version:    0.6.2
 */