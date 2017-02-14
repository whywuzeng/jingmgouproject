package org.android.agoo.ut;

import android.content.Context;
import android.text.TextUtils;
import org.android.Config;

public class UTFactroy
{
  private static final String AGOO_EVENT_ANDROID_MODULE = "agoo_android_module";
  private static final UTFactroy instance = new UTFactroy();
  private volatile UT logger = null;

  public static UTFactroy getInstance()
  {
    return instance;
  }

  public final void commitEvent(Context paramContext, Object paramObject, String[] paramArrayOfString)
  {
    try
    {
      if (this.logger != null)
      {
        String str = Config.getDeviceToken(paramContext);
        getLogger(paramContext).commitEvent(273791437, "agoo_android_module", str, paramObject, paramArrayOfString);
      }
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public final UT getLogger(Context paramContext)
    throws Throwable
  {
    try
    {
      if (this.logger == null)
      {
        String str1 = Config.getLoggerClassName(paramContext);
        if (!TextUtils.isEmpty(str1))
        {
          this.logger = ((UT)Class.forName(str1).newInstance());
          str1 = Config.getAppKey(paramContext);
          String str2 = Config.getTtId(paramContext);
          if ((TextUtils.isEmpty(str1)) || (TextUtils.isEmpty(str2)))
            break label82;
          String str3 = Config.getAppSecret(paramContext);
          this.logger.start(paramContext, str1, str3, str2);
        }
      }
      while (true)
      {
        return this.logger;
        label82: this.logger = null;
      }
    }
    catch (Throwable paramContext)
    {
    }
    throw paramContext;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.ut.UTFactroy
 * JD-Core Version:    0.6.2
 */