package com.umeng.message;

import android.content.Context;
import android.text.TextUtils;
import com.ta.utdid2.device.UTDevice;
import com.umeng.message.proguard.ab;
import com.umeng.message.proguard.ac;
import com.umeng.message.proguard.ay;
import com.umeng.message.proguard.m;
import com.umeng.message.proguard.r;
import org.android.Config;
import org.android.agoo.ut.UT;

public class UmengUT
  implements UT
{
  private volatile boolean a = false;

  private String a(String[] paramArrayOfString)
  {
    int i = 0;
    if ((paramArrayOfString != null) && (paramArrayOfString.length == 0))
      return null;
    StringBuffer localStringBuffer = new StringBuffer();
    if ((paramArrayOfString != null) && (paramArrayOfString.length > 0))
    {
      int k;
      for (int j = 0; i < paramArrayOfString.length; j = k)
      {
        k = j;
        if (!TextUtils.isEmpty(paramArrayOfString[i]))
        {
          if (j != 0)
            localStringBuffer.append(",");
          localStringBuffer.append(paramArrayOfString[i]);
          k = 1;
        }
        i += 1;
      }
    }
    return localStringBuffer.toString();
  }

  public static String convertObjectToString(Object paramObject)
  {
    if (paramObject != null)
    {
      if ((paramObject instanceof String))
        return ((String)paramObject).toString();
      if ((paramObject instanceof Integer))
        return "" + ((Integer)paramObject).intValue();
      if ((paramObject instanceof Long))
        return "" + ((Long)paramObject).longValue();
      if ((paramObject instanceof Double))
        return "" + ((Double)paramObject).doubleValue();
      if ((paramObject instanceof Float))
        return "" + ((Float)paramObject).floatValue();
      if ((paramObject instanceof Short))
        return "" + ((Short)paramObject).shortValue();
      if ((paramObject instanceof Byte))
        return "" + ((Byte)paramObject).byteValue();
      if ((paramObject instanceof Boolean))
        return ((Boolean)paramObject).toString();
      if ((paramObject instanceof Character))
        return ((Character)paramObject).toString();
      return paramObject.toString();
    }
    return "";
  }

  public void commitEvent(int paramInt, Object paramObject)
  {
    try
    {
      paramObject = new ay("Agoo", paramInt, convertObjectToString(paramObject), null, null, null);
      m.a().b("agoo").a(paramObject.a());
      return;
    }
    catch (Throwable paramObject)
    {
    }
  }

  public void commitEvent(int paramInt, Object paramObject1, Object paramObject2)
  {
    try
    {
      paramObject1 = new ay("Agoo", paramInt, convertObjectToString(paramObject1), convertObjectToString(paramObject2), null, null);
      m.a().b("agoo").a(paramObject1.a());
      return;
    }
    catch (Throwable paramObject1)
    {
    }
  }

  public void commitEvent(int paramInt, Object paramObject1, Object paramObject2, Object paramObject3)
  {
    try
    {
      paramObject1 = new ay("Agoo", paramInt, convertObjectToString(paramObject1), convertObjectToString(paramObject2), convertObjectToString(paramObject3), null);
      m.a().b("agoo").a(paramObject1.a());
      return;
    }
    catch (Throwable paramObject1)
    {
    }
  }

  public void commitEvent(int paramInt, Object paramObject1, Object paramObject2, Object paramObject3, String[] paramArrayOfString)
  {
    try
    {
      paramObject1 = new ay("Agoo", paramInt, convertObjectToString(paramObject1), convertObjectToString(paramObject2), convertObjectToString(paramObject3), null);
      paramObject1.a("_field_args", a(paramArrayOfString));
      m.a().b("agoo").a(paramObject1.a());
      return;
    }
    catch (Throwable paramObject1)
    {
    }
  }

  public String getUtdId(Context paramContext)
  {
    try
    {
      paramContext = UTDevice.getUtdid(paramContext);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
    }
    return null;
  }

  public void onCaughException(Throwable paramThrowable)
  {
  }

  public void start(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    try
    {
      m.a().a(paramContext);
      m.a().c(paramString3);
      m.a().c();
      paramString3 = paramString2;
      if (TextUtils.isEmpty(paramString2))
        paramString3 = "test";
      if (Config.isAgooSoSecurityMode(paramContext))
      {
        m.a().a(new ac(paramString1));
        return;
      }
      m.a().a(new ab(paramString1, paramString3));
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public void stop(Context paramContext)
  {
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.UmengUT
 * JD-Core Version:    0.6.2
 */