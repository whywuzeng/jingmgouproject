package org.android.agoo.client;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.message.proguard.bM;
import org.android.Config;
import org.android.agoo.IControlService;
import org.android.agoo.IMtopService;
import org.android.agoo.IUpdateService;
import org.android.agoo.callback.IControlCallBack;
import org.android.agoo.intent.IntentUtil;
import org.android.agoo.proxy.ProxyFactroy;
import org.android.agoo.ut.UTFactroy;
import org.android.du.DuSdk;
import org.android.du.Update;
import org.json.JSONObject;

public abstract class BaseIntentService extends IntentService
  implements IControlCallBack
{
  private static final String AGOO_COMMAND_LOADUPDATEJAR = "loadUpdateJar";
  private static final String TAG = "BaseIntentService";

  protected BaseIntentService()
  {
    super("AgooIntentService");
  }

  private void autoUpdate(Context paramContext, String paramString)
  {
    if (paramContext != null)
      try
      {
        if (TextUtils.isEmpty(paramString))
          return;
        boolean bool = Config.isTimeToUpdate(paramContext, System.currentTimeMillis());
        if ((bool) && (BaseRegistrar.isRegistered(paramContext)))
        {
          getSwitchConfig(paramContext);
          if ((Config.isEnableSwitchConfig(paramContext)) && (bool) && (BaseRegistrar.isRegistered(paramContext)))
          {
            Log.w("BaseIntentService", "auto update [download]");
            IUpdateService localIUpdateService = (IUpdateService)ProxyFactroy.getInstance(paramContext, "org.android.agoo.impl.UpdateService");
            String str = paramString;
            if (!TextUtils.isEmpty(paramString))
            {
              str = paramString;
              if (paramString.contains("."))
              {
                String[] arrayOfString = paramString.split("\\.");
                str = paramString;
                if (arrayOfString != null)
                {
                  str = paramString;
                  if (arrayOfString.length > 0)
                    str = arrayOfString[(arrayOfString.length - 1)];
                }
              }
            }
            localIUpdateService.downloadUpdate(paramContext, "push", str);
            return;
          }
        }
      }
      catch (Throwable paramString)
      {
        Log.w("BaseIntentService", " onHandleIntent---isNeedAutoUpdate", paramString);
        bM.e(paramContext, paramString.toString(), "push");
      }
  }

  private final void getSwitchConfig(Context paramContext)
  {
    boolean bool4 = true;
    if (paramContext != null);
    try
    {
      Log.v("BaseIntentService", "auto update [updateSwitchConfig]");
      Object localObject1 = (IMtopService)ProxyFactroy.getInstance(paramContext, "org.android.agoo.impl.MtopService");
      Object localObject2 = new MtopProxyRequest();
      ((MtopProxyRequest)localObject2).setApi("mtop.wswitch.syncconfiggroup");
      ((MtopProxyRequest)localObject2).setV("1.0");
      ((MtopProxyRequest)localObject2).putParams("configName", "client_sdk_switch");
      localObject1 = ((IMtopService)localObject1).getV3(paramContext, (MtopProxyRequest)localObject2);
      if (((MtopSyncResult)localObject1).isSuccess())
        localObject1 = ((MtopSyncResult)localObject1).getData();
      while (true)
      {
        long l;
        boolean bool1;
        boolean bool2;
        label183: boolean bool3;
        try
        {
          Object localObject3 = new JSONObject((String)localObject1);
          localObject2 = (String)((JSONObject)localObject3).get("autoUpdate");
          String str1 = (String)((JSONObject)localObject3).get("multiplex");
          String str2 = (String)((JSONObject)localObject3).get("spdy");
          l = Long.parseLong((String)((JSONObject)localObject3).get("updateCycle"));
          localObject3 = (String)((JSONObject)localObject3).get("postData");
          if (!"true".equals(localObject2))
            break label347;
          bool1 = true;
          if (!"true".equals(str1))
            break label342;
          bool2 = true;
          if (!"true".equals(str2))
            break label336;
          bool3 = true;
          label196: if (!"true".equals(localObject3))
            break label330;
          break label352;
          label209: Config.set(paramContext, bool1, bool2, bool3, l, bool4);
          Config.setUpdateTime(paramContext, System.currentTimeMillis());
          return;
        }
        catch (Throwable localThrowable)
        {
          UTFactroy.getInstance().commitEvent(paramContext, "updateConfig", new String[] { "ERROR_EVENT_UPDATECONFIG_ERROR", "content=" + (String)localObject1 });
          continue;
        }
        UTFactroy.getInstance().commitEvent(paramContext, "updateConfig", new String[] { "ERROR_EVENT_UPDATECONFIG_ERROR", "result=" + ((MtopSyncResult)localObject1).toString() });
        continue;
        label330: label336: label342: label347: label352: 
        do
        {
          l = 86400L;
          break label209;
          bool4 = false;
          continue;
          bool3 = false;
          break label196;
          bool2 = false;
          break label183;
          bool1 = false;
          break;
        }
        while (l == 0L);
      }
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  private static void reportAutoUpdate(Context paramContext)
  {
    if (paramContext != null);
    try
    {
      Log.v("BaseIntentService", "auto update [reportAutoUpdate]");
      Object localObject1 = (IMtopService)ProxyFactroy.getInstance(paramContext, "org.android.agoo.impl.MtopService");
      String str1 = bM.b(paramContext, "push");
      String str2 = bM.c(paramContext, "push");
      String str3 = bM.d(paramContext, "push");
      String str4 = bM.f(paramContext, "push");
      String str5 = bM.e(paramContext, "push");
      Object localObject2 = new MtopProxyRequest();
      ((MtopProxyRequest)localObject2).setApi("mtop.push.device.updateReport");
      ((MtopProxyRequest)localObject2).setV("4.0");
      ((MtopProxyRequest)localObject2).putParams("app_version", Config.getAppVersionName(paramContext));
      ((MtopProxyRequest)localObject2).putParams("sdk_version", Long.valueOf(Config.getAgooReleaseTime(paramContext)));
      ((MtopProxyRequest)localObject2).putParams("app_pack", paramContext.getPackageName());
      ((MtopProxyRequest)localObject2).putParams("c0", Build.BRAND);
      ((MtopProxyRequest)localObject2).putParams("c1", Build.MODEL);
      ((MtopProxyRequest)localObject2).putParams("phoneOsInfo", Build.FINGERPRINT);
      if ((!str3.equals("5")) || (!str3.equals("-1")))
        ((MtopProxyRequest)localObject2).putParams("app_version", Config.getAppVersionName(paramContext) + ",netStatus=" + str4 + ",exception=" + str5);
      ((MtopProxyRequest)localObject2).putParams("currentVersion", str1 + ",targetVersion=" + str2 + ",isUpdateFlag=" + str3 + ",deviceId=" + BaseRegistrar.getRegistrationId(paramContext) + ",ttid=" + Config.getTtId(paramContext));
      localObject1 = ((IMtopService)localObject1).getV3(paramContext, (MtopProxyRequest)localObject2);
      localObject2 = (IControlService)ProxyFactroy.getInstance(paramContext, "org.android.agoo.impl.ControlService");
      Log.d("BaseIntentService", "reportAutoUpdate result=" + ((MtopSyncResult)localObject1).isSuccess() + ",controlService class=" + localObject2.getClass().toString() + ",currentVersion=" + str1 + ",targetVersion=" + str2 + ",isUpdateFlag=" + str3 + ",deviceId=" + BaseRegistrar.getRegistrationId(paramContext) + ",ttid=" + Config.getTtId(paramContext) + ",netStatus=" + str4 + ",exception=" + str5);
      if (!((MtopSyncResult)localObject1).isSuccess())
        UTFactroy.getInstance().commitEvent(paramContext, "reportAutoUpdate", new String[] { "reportAutoUpdate", "result=" + ((MtopSyncResult)localObject1).toString() });
      return;
    }
    catch (Throwable paramContext)
    {
      Log.d("BaseIntentService", "reportAutoUpdate error=" + paramContext.getMessage());
    }
  }

  static final void runIntentInService(Context paramContext, Intent paramIntent, String paramString)
  {
    try
    {
      Log.d("BaseIntentService", "runIntent to receive one message" + paramIntent + "|" + paramString);
      paramIntent.setClassName(paramContext, paramString);
      paramContext.startService(paramIntent);
      return;
    }
    catch (Throwable paramContext)
    {
      paramContext = paramContext;
      Log.w("BaseIntentService", "runIntentInService", paramContext);
      return;
    }
    finally
    {
    }
    throw paramContext;
  }

  public Class<?> callAgooService()
  {
    try
    {
      Class localClass = getAgooService();
      return localClass;
    }
    catch (Throwable localThrowable)
    {
      Log.w("BaseIntentService", "callAgooService", localThrowable);
    }
    return null;
  }

  public final void callDeletedMessages(Context paramContext, int paramInt)
  {
    try
    {
      onDeletedMessages(paramContext, paramInt);
      return;
    }
    catch (Throwable paramContext)
    {
      Log.w("BaseIntentService", "callDeletedMessages", paramContext);
    }
  }

  public final void callError(Context paramContext, String paramString)
  {
    try
    {
      Log.w("BaseIntentService", "callError[" + paramString + "]");
      onError(paramContext, paramString);
      return;
    }
    catch (Throwable paramContext)
    {
      Log.w("BaseIntentService", "callError", paramContext);
    }
  }

  public final void callMessage(Context paramContext, Intent paramIntent)
  {
    try
    {
      onMessage(paramContext, paramIntent);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public boolean callRecoverableError(Context paramContext, String paramString)
  {
    return true;
  }

  public final void callRegistered(Context paramContext, String paramString)
  {
    try
    {
      onRegistered(paramContext, paramString);
      return;
    }
    catch (Throwable paramContext)
    {
      Log.w("BaseIntentService", "callRegistered", paramContext);
    }
  }

  public final boolean callShouldProcessMessage(Context paramContext, Intent paramIntent)
  {
    try
    {
      boolean bool = shouldProcessMessage(paramContext, paramIntent);
      return bool;
    }
    catch (Throwable paramContext)
    {
      Log.w("BaseIntentService", "callShouldProcessMessage", paramContext);
    }
    return false;
  }

  public final void callUnregistered(Context paramContext, String paramString)
  {
    try
    {
      onUnregistered(paramContext, paramString);
      return;
    }
    catch (Throwable paramContext)
    {
      Log.w("BaseIntentService", "callUnregistered", paramContext);
    }
  }

  public final void callUserCommand(Context paramContext, Intent paramIntent)
  {
    try
    {
      onUserCommand(paramContext, paramIntent);
      return;
    }
    catch (Throwable paramContext)
    {
      Log.w("BaseIntentService", "callUserCommand", paramContext);
    }
  }

  public final void callUserHandleIntent(Context paramContext, Intent paramIntent)
  {
    try
    {
      onUserHandleIntent(paramContext, paramIntent);
      return;
    }
    catch (Throwable paramContext)
    {
      Log.w("BaseIntentService", "callUserHandleIntent", paramContext);
    }
  }

  protected Class<?> getAgooService()
  {
    return null;
  }

  protected void onDeletedMessages(Context paramContext, int paramInt)
  {
  }

  protected abstract void onError(Context paramContext, String paramString);

  public final void onHandleIntent(Intent paramIntent)
  {
    Object localObject5 = null;
    Object localObject4 = null;
    Object localObject6 = "0";
    int j = 0;
    boolean bool2 = false;
    boolean bool1 = bool2;
    Object localObject1 = localObject6;
    int i = j;
    Object localObject2 = localObject6;
    try
    {
      Context localContext = getApplicationContext();
      bool1 = bool2;
      localObject1 = localObject6;
      localObject4 = localContext;
      i = j;
      localObject2 = localObject6;
      localObject5 = localContext;
      bool2 = Config.isEnableUpdate_ByApp(localContext);
      Object localObject3 = localObject6;
      if (bool2)
      {
        bool1 = bool2;
        localObject1 = localObject6;
        localObject4 = localContext;
        i = bool2;
        localObject2 = localObject6;
        localObject5 = localContext;
        localObject6 = bM.a(localContext, "push");
        localObject3 = localObject6;
        bool1 = bool2;
        localObject1 = localObject6;
        localObject4 = localContext;
        i = bool2;
        localObject2 = localObject6;
        localObject5 = localContext;
        if (!TextUtils.equals((CharSequence)localObject6, "0"))
        {
          bool1 = bool2;
          localObject1 = localObject6;
          localObject4 = localContext;
          i = bool2;
          localObject2 = localObject6;
          localObject5 = localContext;
          Update localUpdate = DuSdk.getUpdate(localContext, "push");
          localObject3 = localObject6;
          if (localUpdate != null)
          {
            localObject3 = localObject6;
            bool1 = bool2;
            localObject1 = localObject6;
            localObject4 = localContext;
            i = bool2;
            localObject2 = localObject6;
            localObject5 = localContext;
            if (localUpdate.ismLoadclassSuccess())
            {
              bool1 = bool2;
              localObject1 = localObject6;
              localObject4 = localContext;
              i = bool2;
              localObject2 = localObject6;
              localObject5 = localContext;
              Log.w("BaseIntentService", "auto update setExtrasClassLoader[" + localUpdate.getClassLoader().toString() + "]");
              bool1 = bool2;
              localObject1 = localObject6;
              localObject4 = localContext;
              i = bool2;
              localObject2 = localObject6;
              localObject5 = localContext;
              paramIntent.setExtrasClassLoader(localUpdate.getClassLoader());
              localObject3 = localObject6;
            }
          }
        }
      }
      bool1 = bool2;
      localObject1 = localObject3;
      localObject4 = localContext;
      i = bool2;
      localObject2 = localObject3;
      localObject5 = localContext;
      localObject6 = (IControlService)ProxyFactroy.getInstance(localContext, "org.android.agoo.impl.ControlService");
      if (localObject6 != null)
      {
        bool1 = bool2;
        localObject1 = localObject3;
        localObject4 = localContext;
        i = bool2;
        localObject2 = localObject3;
        localObject5 = localContext;
        ((IControlService)localObject6).onHandleIntent(localContext, paramIntent, this);
      }
      bool1 = bool2;
      localObject1 = localObject3;
      localObject4 = localContext;
      i = bool2;
      localObject2 = localObject3;
      localObject5 = localContext;
      if (TextUtils.equals(paramIntent.getAction(), IntentUtil.getAgooCommand(localContext)))
      {
        bool1 = bool2;
        localObject1 = localObject3;
        localObject4 = localContext;
        i = bool2;
        localObject2 = localObject3;
        localObject5 = localContext;
        if ("loadUpdateJar".equals(paramIntent.getStringExtra("command")))
        {
          bool1 = bool2;
          localObject1 = localObject3;
          localObject4 = localContext;
          i = bool2;
          localObject2 = localObject3;
          localObject5 = localContext;
          reportAutoUpdate(localContext);
        }
      }
      return;
    }
    catch (Throwable paramIntent)
    {
      i = bool1;
      localObject2 = localObject1;
      localObject5 = localObject4;
      Log.w("BaseIntentService", " onHandleIntent", paramIntent);
      return;
    }
    finally
    {
      if (i != 0)
        autoUpdate(localObject5, localObject2);
    }
    throw paramIntent;
  }

  protected abstract void onMessage(Context paramContext, Intent paramIntent);

  protected boolean onRecoverableError(Context paramContext, String paramString)
  {
    return true;
  }

  protected abstract void onRegistered(Context paramContext, String paramString);

  protected abstract void onUnregistered(Context paramContext, String paramString);

  protected void onUserCommand(Context paramContext, Intent paramIntent)
  {
  }

  protected void onUserHandleIntent(Context paramContext, Intent paramIntent)
  {
  }

  protected boolean shouldProcessMessage(Context paramContext, Intent paramIntent)
  {
    return true;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.client.BaseIntentService
 * JD-Core Version:    0.6.2
 */