package org.android.agoo.client;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import org.android.Config;
import org.android.agoo.IMtopService;
import org.android.agoo.intent.IntentUtil;
import org.android.agoo.proxy.ProxyFactroy;

public class BaseRegistrar
{
  private static final String AGOO_COMMAND_REGISTRATION = "register";
  private static final String AGOO_COMMAND_UNREGISTRATION = "unregister";

  protected static final void baseRegister(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    baseRegister(paramContext, paramString1, paramString2, paramString3, true);
  }

  protected static final void baseRegister(Context paramContext, String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    if (paramContext != null);
    try
    {
      if ((!TextUtils.isEmpty(paramString3)) && (!TextUtils.isEmpty(paramString1)))
      {
        if (TextUtils.isEmpty(paramString2))
          Config.setAgooSecurityMode(paramContext, true);
        Config.setAppInfo(paramContext, paramString1, paramString2, paramString3);
        paramString1 = IntentUtil.createComandIntent(paramContext, "register");
        if (paramString1 != null)
          paramContext.sendBroadcast(paramString1);
      }
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  @Deprecated
  public static final void checkDevice(Context paramContext)
  {
  }

  protected static final IMtopService getMtopService(Context paramContext)
  {
    if (paramContext != null);
    while (true)
    {
      try
      {
        paramContext = (IMtopService)ProxyFactroy.getInstance(paramContext, "org.android.agoo.impl.MtopService");
        return paramContext;
      }
      catch (Throwable paramContext)
      {
        return null;
      }
      paramContext = null;
    }
  }

  public static final String getRegistrationId(Context paramContext)
  {
    String str = null;
    if (paramContext != null);
    try
    {
      str = Config.getDeviceToken(paramContext);
      return str;
    }
    catch (Throwable paramContext)
    {
    }
    return null;
  }

  public static final boolean isRegistered(Context paramContext)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramContext != null);
    try
    {
      boolean bool3 = TextUtils.isEmpty(Config.getDeviceToken(paramContext));
      bool1 = bool2;
      if (!bool3)
        bool1 = true;
      return bool1;
    }
    catch (Throwable paramContext)
    {
    }
    return false;
  }

  public static final void pingMessage(Context paramContext, boolean paramBoolean)
  {
    if (paramContext != null);
    try
    {
      Config.setPingMessage(paramContext, paramBoolean);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void sendAsynMtop(Context paramContext, MtopProxyRequest paramMtopProxyRequest)
  {
    try
    {
      IMtopService localIMtopService = getMtopService(paramContext);
      if (localIMtopService != null)
        localIMtopService.sendMtop(paramContext, paramMtopProxyRequest);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void sendAsynMtop(Context paramContext, MtopProxyRequest paramMtopProxyRequest, MtopProxyResponseHandler paramMtopProxyResponseHandler)
  {
    if (paramContext != null);
    try
    {
      IMtopService localIMtopService = getMtopService(paramContext);
      if (localIMtopService != null)
        localIMtopService.sendMtop(paramContext, paramMtopProxyRequest, paramMtopProxyResponseHandler);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void sendSynMtop(Context paramContext, MtopProxyRequest paramMtopProxyRequest)
  {
    try
    {
      IMtopService localIMtopService = getMtopService(paramContext);
      if (localIMtopService != null)
        localIMtopService.getV3(paramContext, paramMtopProxyRequest);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  protected static final void set(Context paramContext, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, long paramLong, boolean paramBoolean4)
  {
    Config.set(paramContext, paramBoolean1, paramBoolean2, paramBoolean3, paramLong, paramBoolean4);
  }

  protected static void setAgooGroup(Context paramContext, String paramString)
  {
    if (paramContext != null);
    try
    {
      Config.setAgooGroup(paramContext, paramString);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void setAgooMode(Context paramContext, Mode paramMode)
  {
    if (paramContext != null);
    try
    {
      Config.setAgooMode(paramContext, paramMode.getValue());
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void setAutoUpdate(Context paramContext, boolean paramBoolean)
  {
    if (paramContext != null);
    try
    {
      Config.setIfNeedAutoUpdate(paramContext, paramBoolean);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void setDebug(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramContext != null);
    try
    {
      Config.setDebug(paramContext, paramBoolean1, paramBoolean2);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void setServiceProtect(Context paramContext, boolean paramBoolean)
  {
    if (paramContext != null);
    try
    {
      Config.setServiceProtect(paramContext, paramBoolean);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  protected static final void setUTVersion(Context paramContext, UT paramUT)
  {
    if (paramContext != null);
    try
    {
      Config.setUTClassName(paramContext, paramUT.getUTClassName());
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static void unregister(Context paramContext)
  {
    if (paramContext != null);
    try
    {
      Intent localIntent = IntentUtil.createComandIntent(paramContext, "unregister");
      if (localIntent != null)
        paramContext.sendBroadcast(localIntent);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.client.BaseRegistrar
 * JD-Core Version:    0.6.2
 */