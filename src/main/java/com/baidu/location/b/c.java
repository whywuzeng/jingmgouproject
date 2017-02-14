package com.baidu.location.b;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.baidu.location.f.a.a;

public class c
  implements f
{
  public static String bj = null;
  public static String bk = null;
  public static String bl = null;
  public static String bn;
  private static c bq = null;
  public String bm = null;
  public String bo = null;
  private boolean bp = false;

  static
  {
    bn = null;
  }

  private c()
  {
    if (com.baidu.location.f.getServiceContext() != null)
      jdMethod_do(com.baidu.location.f.getServiceContext());
  }

  public static c N()
  {
    if (bq == null)
      bq = new c();
    return bq;
  }

  public String K()
  {
    if (bn != null)
      return L() + "|" + bn;
    return L();
  }

  public String L()
  {
    if (this.bm != null)
      return "v6.12|" + this.bm + "|" + Build.MODEL;
    return "v6.12|" + this.bo + "|" + Build.MODEL;
  }

  public String M()
  {
    return "&sdk=6.12" + P();
  }

  public String O()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (this.bm == null)
    {
      localStringBuffer.append("&im=");
      localStringBuffer.append(this.bo);
    }
    while (true)
    {
      localStringBuffer.append("&mb=");
      localStringBuffer.append(Build.MODEL);
      localStringBuffer.append("&os=A");
      localStringBuffer.append(Build.VERSION.SDK);
      localStringBuffer.append("&prod=");
      localStringBuffer.append(bj + ":" + bn);
      localStringBuffer.append("&resid=");
      localStringBuffer.append("12");
      return localStringBuffer.toString();
      localStringBuffer.append("&cu=");
      localStringBuffer.append(this.bm);
    }
  }

  public String P()
  {
    StringBuffer localStringBuffer = new StringBuffer(200);
    if (this.bm != null)
    {
      localStringBuffer.append("&cu=");
      localStringBuffer.append(this.bm);
    }
    try
    {
      while (true)
      {
        localStringBuffer.append("&mb=");
        localStringBuffer.append(Build.MODEL);
        label49: localStringBuffer.append("&pack=");
        try
        {
          localStringBuffer.append(bn);
          label64: localStringBuffer.append("&sdk=");
          localStringBuffer.append(6.12F);
          return localStringBuffer.toString();
          localStringBuffer.append("&im=");
          localStringBuffer.append(this.bo);
        }
        catch (Exception localException1)
        {
          break label64;
        }
      }
    }
    catch (Exception localException2)
    {
      break label49;
    }
  }

  public String jdMethod_do(boolean paramBoolean)
  {
    return jdMethod_if(paramBoolean, null);
  }

  public void jdMethod_do(Context paramContext)
  {
    if ((paramContext == null) || (this.bp))
      return;
    try
    {
      this.bo = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
    }
    catch (Exception localException2)
    {
      try
      {
        this.bm = a.jdMethod_if(paramContext);
      }
      catch (Exception localException2)
      {
        try
        {
          while (true)
          {
            bn = paramContext.getPackageName();
            this.bp = true;
            return;
            localException1 = localException1;
            this.bo = "NULL";
            continue;
            localException2 = localException2;
            this.bm = null;
          }
        }
        catch (Exception paramContext)
        {
          while (true)
            bn = null;
        }
      }
    }
  }

  public String jdMethod_if(boolean paramBoolean, String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer(256);
    localStringBuffer.append("&sdk=");
    localStringBuffer.append(6.12F);
    if (paramBoolean)
    {
      if (k.cf.equals("all"))
        localStringBuffer.append("&addr=all");
      if ((k.bX) || (k.ci) || (k.bS) || (k.bP))
      {
        localStringBuffer.append("&sema=");
        if (k.bX)
          localStringBuffer.append("aptag|");
        if (k.bP)
          localStringBuffer.append("aptagd|");
        if (k.ci)
          localStringBuffer.append("poiregion|");
        if (k.bS)
          localStringBuffer.append("regular");
      }
    }
    if (paramBoolean)
    {
      if (paramString == null)
        localStringBuffer.append("&coor=gcj02");
    }
    else
    {
      if (this.bm != null)
        break label327;
      localStringBuffer.append("&im=");
      localStringBuffer.append(this.bo);
    }
    while (true)
    {
      localStringBuffer.append("&fw=");
      localStringBuffer.append(com.baidu.location.f.getFrameVersion());
      localStringBuffer.append("&lt=1");
      localStringBuffer.append("&mb=");
      localStringBuffer.append(Build.MODEL);
      localStringBuffer.append("&resid=");
      localStringBuffer.append("12");
      localStringBuffer.append("&os=A");
      localStringBuffer.append(Build.VERSION.SDK);
      if (paramBoolean)
      {
        localStringBuffer.append("&sv=");
        String str = Build.VERSION.RELEASE;
        paramString = str;
        if (str != null)
        {
          paramString = str;
          if (str.length() > 6)
            paramString = str.substring(0, 6);
        }
        localStringBuffer.append(paramString);
      }
      return localStringBuffer.toString();
      localStringBuffer.append("&coor=");
      localStringBuffer.append(paramString);
      break;
      label327: localStringBuffer.append("&cu=");
      localStringBuffer.append(this.bm);
    }
  }

  public void jdMethod_if(String paramString1, String paramString2)
  {
    bj = paramString1;
    bn = paramString2;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.b.c
 * JD-Core Version:    0.6.2
 */