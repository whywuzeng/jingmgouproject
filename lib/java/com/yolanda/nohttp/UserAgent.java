package com.yolanda.nohttp;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.lang.reflect.Field;
import java.util.Locale;

public class UserAgent
{
  public static String getUserAgent(Context paramContext)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramContext != null);
    try
    {
      localObject1 = paramContext.getString(((Integer)Class.forName("com.android.internal.R$string").getDeclaredField("web_user_agent").get(null)).intValue());
      paramContext = (Context)localObject1;
      if (TextUtils.isEmpty((CharSequence)localObject1))
        paramContext = "Mozilla/5.0 (Linux; U; Android %s) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 %sSafari/533.1";
      localObject2 = Locale.getDefault();
      localObject1 = new StringBuffer();
      String str = Build.VERSION.RELEASE;
      if (str.length() > 0)
      {
        ((StringBuffer)localObject1).append(str);
        ((StringBuffer)localObject1).append("; ");
        str = ((Locale)localObject2).getLanguage();
        if (str == null)
          break label213;
        ((StringBuffer)localObject1).append(str.toLowerCase((Locale)localObject2));
        str = ((Locale)localObject2).getCountry();
        if (str != null)
        {
          ((StringBuffer)localObject1).append("-");
          ((StringBuffer)localObject1).append(str.toLowerCase((Locale)localObject2));
        }
      }
      while (true)
      {
        if ("REL".equals(Build.VERSION.CODENAME))
        {
          localObject2 = Build.MODEL;
          if (((String)localObject2).length() > 0)
          {
            ((StringBuffer)localObject1).append("; ");
            ((StringBuffer)localObject1).append((String)localObject2);
          }
        }
        localObject2 = Build.ID;
        if (((String)localObject2).length() > 0)
        {
          ((StringBuffer)localObject1).append(" Build/");
          ((StringBuffer)localObject1).append((String)localObject2);
        }
        return String.format(paramContext, new Object[] { localObject1, "Mobile " });
        ((StringBuffer)localObject1).append("1.0");
        break;
        label213: ((StringBuffer)localObject1).append("en");
      }
    }
    catch (Exception paramContext)
    {
      while (true)
        localObject1 = localObject2;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.UserAgent
 * JD-Core Version:    0.6.2
 */