package com.umeng.message;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import com.umeng.common.message.Log;
import com.umeng.common.message.UmengMessageDeviceConfig;
import com.umeng.message.local.UmengLocalNotification;
import com.umeng.message.local.UmengLocalNotificationManager;
import com.umeng.message.local.UmengLocalNotificationService;
import com.umeng.message.proguard.g;
import com.umeng.message.proguard.k.e;
import com.umeng.message.tag.TagManager;
import java.util.List;
import java.util.Random;
import org.android.agoo.a;
import org.json.JSONException;

public class PushAgent
{
  public static boolean DEBUG = false;
  private static PushAgent a;
  private static boolean d = false;
  private static final String e = PushAgent.class.getName();
  private TagManager b;
  private Context c;
  private UHandler f;
  private UHandler g;
  private boolean h = false;
  private boolean i = true;
  private Handler j;
  private IUmengRegisterCallback k;
  private IUmengUnregisterCallback l;

  private PushAgent(Context paramContext)
  {
    try
    {
      this.c = paramContext;
      this.b = TagManager.getInstance(paramContext);
      this.f = new UmengMessageHandler();
      this.g = new UmengNotificationClickHandler();
      this.j = new Handler(paramContext.getMainLooper())
      {
        public void handleMessage(Message paramAnonymousMessage)
        {
          super.handleMessage(paramAnonymousMessage);
        }
      };
      return;
    }
    catch (Exception localException)
    {
      while (true)
        Log.b(e, localException.getMessage());
    }
  }

  public static PushAgent getInstance(Context paramContext)
  {
    try
    {
      if (a == null)
        a = new PushAgent(paramContext.getApplicationContext());
      paramContext = a;
      return paramContext;
    }
    finally
    {
    }
    throw paramContext;
  }

  public static boolean isAppLaunchByMessage()
  {
    return d;
  }

  public static void setAppLaunchByMessage()
  {
    d = true;
  }

  public boolean addAlias(String paramString1, String paramString2)
    throws k.e, JSONException, Exception
  {
    return UTrack.getInstance(this.c).addAlias(paramString1, paramString2);
  }

  public boolean addExclusiveAlias(String paramString1, String paramString2)
    throws k.e, JSONException, Exception
  {
    return UTrack.getInstance(this.c).addExclusiveAlias(paramString1, paramString2);
  }

  public boolean addLocalNotification(UmengLocalNotification paramUmengLocalNotification)
  {
    return UmengLocalNotificationManager.getInstance(this.c).addLocalNotification(paramUmengLocalNotification);
  }

  public boolean clearLocalNotifications()
  {
    return UmengLocalNotificationManager.getInstance(this.c).deleteAllLocalNotifications();
  }

  public boolean deleteLocalNotification(String paramString)
  {
    return UmengLocalNotificationManager.getInstance(this.c).deleteLocalNotification(paramString);
  }

  public void disable()
  {
    try
    {
      String str = g.a(this.c, Process.myPid());
      Log.c(e, "processName=" + str);
      if (!this.c.getPackageName().equals(str))
        return;
      MessageSharedPrefs.getInstance(this.c).f();
      if (UmengRegistrar.isRegistered(this.c))
      {
        UmengRegistrar.unregister(this.c);
        return;
      }
    }
    catch (Exception localException)
    {
      Log.b(e, localException.getMessage());
    }
  }

  public void disable(IUmengUnregisterCallback paramIUmengUnregisterCallback)
  {
    setUnregisterCallback(paramIUmengUnregisterCallback);
    disable();
  }

  public void enable()
  {
    try
    {
      if (!g.c(this.c, UmengLocalNotificationService.class.getName()))
        UmengLocalNotificationManager.getInstance(this.c).resetLocalNotifications();
      String str = g.a(this.c, Process.myPid());
      Log.c(e, "processName=" + str);
      if (!this.c.getPackageName().equals(str))
        return;
      if (Build.VERSION.SDK_INT < 8)
      {
        Log.b(e, "Push SDK does not work for Android Verion < 8");
        return;
      }
    }
    catch (Exception localException)
    {
      Log.b(e, localException.getMessage());
      return;
    }
    if (!g.a(this.c, this.j))
    {
      Log.b(e, "Need to correct AndroidManifest config according to instruction from http://dev.umeng.com/push/android/integration");
      return;
    }
    Log.c(e, "The AndroidManifest config is right");
    g.a(this.c, UmengMessageCallbackHandlerService.class);
    MessageSharedPrefs.getInstance(this.c).e();
    Log.c(e, "enable(): register");
    UmengRegistrar.register(this.c, getMessageAppkey(), getMessageSecret());
  }

  public void enable(IUmengRegisterCallback paramIUmengRegisterCallback)
  {
    setRegisterCallback(paramIUmengRegisterCallback);
    enable();
  }

  public List<UmengLocalNotification> findAllLocalNotifications()
  {
    return UmengLocalNotificationManager.getInstance(this.c).findAllLocalNotifications();
  }

  public UmengLocalNotification findLocalNotification(String paramString)
  {
    return UmengLocalNotificationManager.getInstance(this.c).findLocalNotification(paramString);
  }

  public List<UmengLocalNotification> findLocalNotifications(String paramString)
  {
    return UmengLocalNotificationManager.getInstance(this.c).findLocalNotifications(paramString);
  }

  public boolean getLocalNotificationIntervalLimit()
  {
    return MessageSharedPrefs.getInstance(this.c).l();
  }

  public boolean getMergeNotificaiton()
  {
    return MessageSharedPrefs.getInstance(this.c).getMergeNotificaiton();
  }

  public String getMessageAppkey()
  {
    String str2 = MessageSharedPrefs.getInstance(this.c).getMessageAppKey();
    String str1 = str2;
    if (TextUtils.isEmpty(str2))
      str1 = UmengMessageDeviceConfig.getAppkey(this.c);
    return str1;
  }

  public String getMessageChannel()
  {
    String str2 = MessageSharedPrefs.getInstance(this.c).getMessageChannel();
    String str1 = str2;
    if (TextUtils.isEmpty(str2))
      str1 = UmengMessageDeviceConfig.getChannel(this.c);
    return str1;
  }

  public UHandler getMessageHandler()
  {
    return this.f;
  }

  public String getMessageSecret()
  {
    String str2 = MessageSharedPrefs.getInstance(this.c).getMessageAppSecret();
    String str1 = str2;
    if (TextUtils.isEmpty(str2))
      str1 = UmengMessageDeviceConfig.getMetaData(this.c, "UMENG_MESSAGE_SECRET");
    return str1;
  }

  public int getMuteDurationSeconds()
  {
    return MessageSharedPrefs.getInstance(this.c).k();
  }

  public int getNoDisturbEndHour()
  {
    return MessageSharedPrefs.getInstance(this.c).c();
  }

  public int getNoDisturbEndMinute()
  {
    return MessageSharedPrefs.getInstance(this.c).d();
  }

  public int getNoDisturbStartHour()
  {
    return MessageSharedPrefs.getInstance(this.c).a();
  }

  public int getNoDisturbStartMinute()
  {
    return MessageSharedPrefs.getInstance(this.c).b();
  }

  public UHandler getNotificationClickHandler()
  {
    return this.g;
  }

  public boolean getNotificationOnForeground()
  {
    return MessageSharedPrefs.getInstance(this.c).getNotificaitonOnForeground();
  }

  public String getPushIntentServiceClass()
  {
    return MessageSharedPrefs.getInstance(this.c).getPushIntentServiceClass();
  }

  public IUmengRegisterCallback getRegisterCallback()
  {
    return this.k;
  }

  public String getRegistrationId()
  {
    return UmengRegistrar.getRegistrationId(this.c);
  }

  public String getResourcePackageName()
  {
    return MessageSharedPrefs.getInstance(this.c).getResourcePackageName();
  }

  public TagManager getTagManager()
  {
    return this.b;
  }

  public IUmengUnregisterCallback getUnregisterCallback()
  {
    return this.l;
  }

  public boolean isEnabled()
  {
    try
    {
      boolean bool = MessageSharedPrefs.getInstance(this.c).g();
      return bool;
    }
    catch (Exception localException)
    {
      Log.b(e, localException.getMessage());
    }
    return false;
  }

  public boolean isIncludesUmengUpdateSDK()
  {
    try
    {
      Class localClass = Class.forName("com.umeng.update.UmengUpdateAgent");
      if (localClass != null)
        return true;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      while (true)
      {
        localClassNotFoundException.printStackTrace();
        Object localObject = null;
      }
    }
    return false;
  }

  public boolean isPushCheck()
  {
    return this.h;
  }

  public boolean isRegistered()
  {
    return UmengRegistrar.isRegistered(this.c);
  }

  public void onAppStart()
  {
    UmengRegistrar.checkRegisteredToUmeng(this.c);
    if (!UmengRegistrar.isRegistered(this.c))
      return;
    if (MessageSharedPrefs.getInstance(this.c).getAppLaunchLogSendPolicy() == 1)
      Log.c(e, "launch_policy=1, skip sending app launch info.");
    while (true)
    {
      long l1 = 0L;
      if (isAppLaunchByMessage())
        l1 = Math.abs(new Random().nextLong() % MsgConstant.a);
      UTrack.getInstance(this.c).sendCachedMsgLog(l1);
      return;
      if (!MessageSharedPrefs.getInstance(this.c).hasAppLaunchLogSentToday())
        UTrack.getInstance(this.c).trackAppLaunch(10000L);
    }
  }

  public boolean removeAlias(String paramString1, String paramString2)
    throws k.e, JSONException, Exception
  {
    return UTrack.getInstance(this.c).removeAlias(paramString1, paramString2);
  }

  public void setAppkeyAndSecret(String paramString1, String paramString2)
  {
    MessageSharedPrefs.getInstance(this.c).setMessageAppKey(paramString1);
    MessageSharedPrefs.getInstance(this.c).setMessageAppSecret(paramString2);
  }

  public void setDebugMode(boolean paramBoolean)
  {
    Log.LOG = paramBoolean;
    a.a(this.c, paramBoolean, false);
  }

  public void setLocalNotificationIntervalLimit(boolean paramBoolean)
  {
    MessageSharedPrefs.getInstance(this.c).a(paramBoolean);
  }

  public void setMergeNotificaiton(boolean paramBoolean)
  {
    MessageSharedPrefs.getInstance(this.c).setMergeNotificaiton(paramBoolean);
  }

  public void setMessageChannel(String paramString)
  {
    MessageSharedPrefs.getInstance(this.c).setMessageChannel(paramString);
  }

  public void setMessageHandler(UHandler paramUHandler)
  {
    this.f = paramUHandler;
  }

  public void setMuteDurationSeconds(int paramInt)
  {
    MessageSharedPrefs.getInstance(this.c).a(paramInt);
  }

  public void setNoDisturbMode(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    MessageSharedPrefs.getInstance(this.c).a(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public void setNotificaitonOnForeground(boolean paramBoolean)
  {
    MessageSharedPrefs.getInstance(this.c).setNotificaitonOnForeground(paramBoolean);
  }

  public void setNotificationClickHandler(UHandler paramUHandler)
  {
    this.g = paramUHandler;
  }

  public void setPushCheck(boolean paramBoolean)
  {
    this.h = paramBoolean;
  }

  public <U extends UmengBaseIntentService> void setPushIntentServiceClass(Class<U> paramClass)
  {
    MessageSharedPrefs.getInstance(this.c).setPushIntentServiceClass(paramClass);
  }

  public void setRegisterCallback(IUmengRegisterCallback paramIUmengRegisterCallback)
  {
    this.k = paramIUmengRegisterCallback;
  }

  public void setResourcePackageName(String paramString)
  {
    MessageSharedPrefs.getInstance(this.c).setResourcePackageName(paramString);
  }

  public void setUnregisterCallback(IUmengUnregisterCallback paramIUmengUnregisterCallback)
  {
    this.l = paramIUmengUnregisterCallback;
  }

  public boolean updateLocalNotification(UmengLocalNotification paramUmengLocalNotification)
  {
    return UmengLocalNotificationManager.getInstance(this.c).updateLocalNotification(paramUmengLocalNotification);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.PushAgent
 * JD-Core Version:    0.6.2
 */