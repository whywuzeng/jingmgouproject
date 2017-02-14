package com.baidu.location.e;

import android.location.Location;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.b.m;
import com.baidu.location.h.h;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public abstract class b
  implements com.baidu.location.b.f
{
  public static String fM = null;
  private boolean fL = false;
  private boolean fN = true;
  final Handler fO = new b();
  public h fP = null;
  public com.baidu.location.h.f fQ = null;

  public abstract void a6();

  public abstract void jdMethod_if(Message paramMessage);

  public String k(String paramString)
  {
    if ((this.fP == null) || (!this.fP.dw()))
      this.fP = com.baidu.location.h.c.a().cO();
    if ((this.fQ == null) || (!this.fQ.ds()))
      this.fQ = com.baidu.location.h.l.a().de();
    if (com.baidu.location.h.d.a().cJ());
    for (Location localLocation = com.baidu.location.h.d.a().cD(); ; localLocation = null)
    {
      if (((this.fP == null) || (this.fP.dx())) && ((this.fQ == null) || (this.fQ.dn() == 0)) && (localLocation == null))
        return null;
      String str1 = c.br().bu();
      Object localObject1;
      String str2;
      Object localObject2;
      if (com.baidu.location.h.l.a().db())
      {
        localObject1 = "&cn=32";
        if (!this.fN)
          break label300;
        this.fN = false;
        com.baidu.location.b.o.aY().aV().jdMethod_for(true);
        str2 = com.baidu.location.h.l.a().c7();
        localObject2 = localObject1;
        if (!TextUtils.isEmpty(str2))
        {
          localObject2 = str2.replace(":", "");
          localObject2 = String.format(Locale.CHINA, "%s&mac=%s", new Object[] { localObject1, localObject2 });
        }
        if (Build.VERSION.SDK_INT <= 17);
      }
      while (true)
      {
        localObject2 = (String)localObject2 + str1;
        localObject1 = localObject2;
        if (paramString != null)
          localObject1 = paramString + (String)localObject2;
        return com.baidu.location.b.k.jdMethod_if(this.fP, this.fQ, localLocation, (String)localObject1, 0);
        localObject1 = String.format(Locale.CHINA, "&cn=%d", new Object[] { Integer.valueOf(com.baidu.location.h.c.a().cR()) });
        break;
        label300: localObject2 = localObject1;
        if (!this.fL)
        {
          str2 = o.ck();
          localObject2 = localObject1;
          if (str2 != null)
            localObject2 = (String)localObject1 + str2;
          this.fL = true;
        }
      }
    }
  }

  class a extends m
  {
    String b = null;
    String c = null;

    public a()
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
      this.c5 = com.baidu.location.b.k.Z();
      Object localObject = com.baidu.location.b.d.U().S();
      if ((c8 == com.baidu.location.b.g.for) || (c8 == com.baidu.location.b.g.o))
        this.c5 = ("http://" + (String)localObject + "/sdk.php");
      if (localObject != null)
        com.baidu.location.b.o.aY().aV().jdMethod_else("&host=" + (String)localObject);
      localObject = Jni.K(this.c);
      this.c = null;
      if (this.b == null)
        this.b = o.cn();
      this.c7.add(new BasicNameValuePair("bloc", (String)localObject));
      if (this.b != null)
        this.c7.add(new BasicNameValuePair("up", this.b));
      localObject = new StringBuffer(512);
      ((StringBuffer)localObject).append(String.format(Locale.CHINA, "&ki=%s&sn=%s", new Object[] { com.baidu.location.b.j.a(com.baidu.location.f.getServiceContext()), com.baidu.location.b.j.jdMethod_if(com.baidu.location.f.getServiceContext()) }));
      if (((StringBuffer)localObject).length() > 0)
        this.c7.add(new BasicNameValuePair("ext", Jni.H(((StringBuffer)localObject).toString())));
      localObject = String.format(Locale.CHINA, "%d", new Object[] { Long.valueOf(System.currentTimeMillis()) });
      this.c7.add(new BasicNameValuePair("trtm", (String)localObject));
    }

    public void jdMethod_int(boolean paramBoolean)
    {
      if ((b.this.fP != null) && (b.this.fP.dv() == 0))
        return;
      if ((paramBoolean) && (this.c6 != null));
      while (true)
      {
        try
        {
          while (true)
          {
            Object localObject1 = EntityUtils.toString(this.c6, "utf-8");
            b.fM = (String)localObject1;
            try
            {
              localObject2 = new BDLocation((String)localObject1);
              ((BDLocation)localObject2).setOperators(com.baidu.location.h.c.a().cS());
              localObject1 = localObject2;
              if (l.cg().cd())
              {
                ((BDLocation)localObject2).setDirection(l.cg().ci());
                localObject1 = localObject2;
              }
              this.b = null;
              if ((((BDLocation)localObject1).getLocType() == 0) && (((BDLocation)localObject1).getLatitude() == 4.9E-324D) && (((BDLocation)localObject1).getLongitude() == 4.9E-324D))
              {
                localObject1 = b.this.fO.obtainMessage(63);
                ((Message)localObject1).obj = "HttpStatus error";
                ((Message)localObject1).sendToTarget();
                if (this.c7 == null)
                  break;
                this.c7.clear();
                return;
              }
            }
            catch (Exception localException1)
            {
              while (true)
              {
                BDLocation localBDLocation = new BDLocation();
                localBDLocation.setLocType(0);
              }
            }
          }
        }
        catch (Exception localException2)
        {
          localMessage = b.this.fO.obtainMessage(63);
          localMessage.obj = "HttpStatus error";
          localMessage.sendToTarget();
          continue;
          Object localObject2 = b.this.fO.obtainMessage(21);
          ((Message)localObject2).obj = localMessage;
          ((Message)localObject2).sendToTarget();
          continue;
        }
        Message localMessage = b.this.fO.obtainMessage(63);
        localMessage.obj = "HttpStatus error";
        localMessage.sendToTarget();
      }
    }
  }

  public class b extends Handler
  {
    public b()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      if (!com.baidu.location.f.isServing)
        return;
      switch (paramMessage.what)
      {
      default:
        return;
      case 21:
        b.this.jdMethod_if(paramMessage);
        return;
      case 62:
      case 63:
      }
      b.this.a6();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.e.b
 * JD-Core Version:    0.6.2
 */