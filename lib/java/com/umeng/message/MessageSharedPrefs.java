package com.umeng.message;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.common.message.UmengMessageDeviceConfig;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MessageSharedPrefs
{
  private static final String a = MessageSharedPrefs.class.getName();
  private static MessageSharedPrefs d;
  private Context b;
  private SharedPreferences c;
  private int e;

  private MessageSharedPrefs(Context paramContext)
  {
    this.b = paramContext;
    this.e = 0;
    if (Build.VERSION.SDK_INT > 11)
      this.e |= 4;
    this.c = paramContext.getSharedPreferences("umeng_message_state", this.e);
    Log.d(a, "Constructor()");
  }

  public static MessageSharedPrefs getInstance(Context paramContext)
  {
    try
    {
      if (d == null)
        d = new MessageSharedPrefs(paramContext);
      paramContext = d;
      return paramContext;
    }
    finally
    {
    }
    throw paramContext;
  }

  int a()
  {
    return this.c.getInt("KEY_NO_DISTURB_START_HOUR", 23);
  }

  void a(int paramInt)
  {
    this.c.edit().putInt("mute_duration", paramInt).commit();
  }

  void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.c.edit().putInt("KEY_NO_DISTURB_START_HOUR", paramInt1).putInt("KEY_NO_DISTURB_START_MINUTE", paramInt2).putInt("KEY_NO_DISTURB_END_HOUR", paramInt3).putInt("KEY_NO_DISTURB_END_MINUTE", paramInt4).commit();
  }

  void a(String paramString, boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = this.c.edit();
    Object localObject = this.c.getAll();
    if ((localObject != null) && (((Map)localObject).size() > 0))
    {
      localObject = ((Map)localObject).entrySet().iterator();
      while (((Iterator)localObject).hasNext())
      {
        String str = (String)((Map.Entry)((Iterator)localObject).next()).getKey();
        if (str.startsWith("KEY_REGISTERED_TO_UMENG_"))
          localEditor.remove(str);
      }
    }
    localEditor.putBoolean("KEY_REGISTERED_TO_UMENG_" + paramString, paramBoolean).commit();
  }

  void a(boolean paramBoolean)
  {
    this.b.getSharedPreferences("umeng_message_state", this.e).edit().putBoolean("KEY_SET_LOCALNOTIFICATION_INTERVAL_LIMIT", paramBoolean).commit();
  }

  boolean a(String paramString)
  {
    paramString = "KEY_REGISTERED_TO_UMENG_" + paramString;
    return this.b.getSharedPreferences("umeng_message_state", this.e).getBoolean(paramString, false);
  }

  public void addAlias(String paramString1, String paramString2)
  {
    paramString2 = String.format("ALIAS_%s", new Object[] { paramString2 });
    String str = this.c.getString(paramString2, null);
    int i = getAliasCount();
    SharedPreferences.Editor localEditor = this.c.edit();
    if (str == null)
      localEditor.putInt("ALIAS_COUNT", i + 1);
    localEditor.putString(paramString2, paramString1).commit();
  }

  public void addTags(String[] paramArrayOfString)
  {
    SharedPreferences.Editor localEditor = this.c.edit();
    int j = paramArrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str = String.format("UMENG_TAG_%s", new Object[] { paramArrayOfString[i] });
      if (!this.c.getBoolean(str, false))
      {
        localEditor.putBoolean(str, true);
        localEditor.putInt("UMENG_TAG_COUNT", getTagCount() + 1);
      }
      i += 1;
    }
    localEditor.commit();
  }

  int b()
  {
    return this.c.getInt("KEY_NO_DISTURB_START_MINUTE", 0);
  }

  boolean b(String paramString)
  {
    return this.c.getBoolean("KEY_MSG_RESOURCE_DOWNLOAD_PREFIX" + paramString, false);
  }

  int c()
  {
    return this.c.getInt("KEY_NO_DISTURB_END_HOUR", 7);
  }

  void c(String paramString)
  {
    this.c.edit().putBoolean("KEY_MSG_RESOURCE_DOWNLOAD_PREFIX" + paramString, true).commit();
  }

  int d()
  {
    return this.c.getInt("KEY_NO_DISTURB_END_MINUTE", 0);
  }

  void d(String paramString)
  {
    this.c.edit().remove("KEY_MSG_RESOURCE_DOWNLOAD_PREFIX" + paramString).commit();
  }

  void e()
  {
    this.c.edit().putBoolean("KEY_ENEABLED", true).commit();
  }

  void e(String paramString)
  {
    this.c.edit().putString("last_msg_id", paramString).commit();
  }

  void f()
  {
    this.c.edit().putBoolean("KEY_ENEABLED", false).commit();
  }

  boolean g()
  {
    return this.c.getBoolean("KEY_ENEABLED", false);
  }

  public int getAliasCount()
  {
    return this.c.getInt("ALIAS_COUNT", 0);
  }

  public int getAppLaunchLogSendPolicy()
  {
    return this.c.getInt("KEY_APP_LAUNCH_LOG_SEND_POLICY", -1);
  }

  public long getAppLaunchLogSentAt()
  {
    return this.c.getLong("KEY_LAUNCH_LOG_SENT_MARK", 0L);
  }

  public String getLastAlias(String paramString)
  {
    paramString = String.format("ALIAS_%s", new Object[] { paramString });
    return this.c.getString(paramString, "");
  }

  public boolean getMergeNotificaiton()
  {
    return this.c.getBoolean("KEY_MERGE_NOTIFICATION", true);
  }

  public String getMessageAppKey()
  {
    return this.c.getString("KEY_UMENG_MESSAGE_APP_KEY", "");
  }

  public String getMessageAppSecret()
  {
    return this.c.getString("KEY_UMENG_MESSAGE_APP_SECRET", "");
  }

  public String getMessageChannel()
  {
    return this.c.getString("KEY_UMENG_MESSAGE_APP_CHANNEL", "");
  }

  public boolean getNotificaitonOnForeground()
  {
    return this.c.getBoolean("KEY_SET_NOTIFICATION_ON_FOREGROUND", true);
  }

  public String getPushIntentServiceClass()
  {
    String str1 = this.c.getString("KEY_PUSH_INTENT_SERVICE_CLASSNAME", MsgConstant.DEFAULT_INTENT_SERVICE_CLASS_NAME);
    String str2 = this.c.getString("KEY_SET_PUSH_INTENT_SERVICE_VERSION_CODE", null);
    String str3 = UmengMessageDeviceConfig.getAppVersionCode(this.b);
    try
    {
      Class.forName(str1);
      if ((TextUtils.equals(str2, str3)) && (!TextUtils.equals(str3, "Unknown")))
        return str1;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      return MsgConstant.DEFAULT_INTENT_SERVICE_CLASS_NAME;
    }
    return MsgConstant.DEFAULT_INTENT_SERVICE_CLASS_NAME;
  }

  public String getResourcePackageName()
  {
    return this.c.getString("KEY_SET_RESOURCE_PACKAGENAME", "");
  }

  public int getSerialNo()
  {
    return this.b.getSharedPreferences("umeng_message_state", this.e).getInt("serial_no", 1);
  }

  public int getTagCount()
  {
    return this.c.getInt("UMENG_TAG_COUNT", 0);
  }

  public int getTagRemain()
  {
    return this.c.getInt("UMENG_TAG_REMAIN", 64);
  }

  public int getTagSendPolicy()
  {
    return this.c.getInt("KEY_TAG_SEND_POLICY", -1);
  }

  boolean h()
  {
    return this.c.getBoolean("KEY_CACHE_FILE_TRANSFER_TO_SQL", false);
  }

  public boolean hasAppLaunchLogSentToday()
  {
    Calendar localCalendar1 = Calendar.getInstance();
    try
    {
      localCalendar1.setTimeInMillis(MsgLogStore.getInstance(this.b).getMsgConfigInfo_AppLaunchAt());
      Calendar localCalendar2 = Calendar.getInstance();
      if ((localCalendar1.get(6) == localCalendar2.get(6)) && (localCalendar1.get(1) == localCalendar2.get(1)))
        return true;
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        Log.d(a, localException.toString());
      }
    }
    return false;
  }

  boolean i()
  {
    return this.c.edit().putBoolean("KEY_CACHE_FILE_TRANSFER_TO_SQL", true).commit();
  }

  public boolean isAliasSet(String paramString1, String paramString2)
  {
    paramString2 = String.format("ALIAS_%s", new Object[] { paramString2 });
    paramString2 = this.c.getString(paramString2, null);
    return (paramString1 != null) && (paramString1.equals(paramString2));
  }

  public boolean isAliaseTypeSet(String paramString)
  {
    paramString = String.format("ALIAS_%s", new Object[] { paramString });
    return this.c.contains(paramString);
  }

  public boolean isTagSet(String paramString)
  {
    paramString = String.format("UMENG_TAG_%s", new Object[] { paramString });
    return this.c.getBoolean(paramString, false);
  }

  String j()
  {
    return this.c.getString("last_msg_id", "");
  }

  int k()
  {
    return this.c.getInt("mute_duration", 60);
  }

  boolean l()
  {
    return this.c.getBoolean("KEY_SET_LOCALNOTIFICATION_INTERVAL_LIMIT", true);
  }

  public void removeAlias(String paramString1, String paramString2)
  {
    paramString1 = String.format("ALIAS_%s", new Object[] { paramString2 });
    if (this.c.contains(paramString1))
    {
      int i = getAliasCount();
      paramString2 = this.c.edit();
      paramString2.remove(paramString1);
      paramString2.putInt("ALIAS_COUNT", i - 1);
      paramString2.commit();
    }
  }

  public void removeTags(String[] paramArrayOfString)
  {
    SharedPreferences.Editor localEditor = this.c.edit();
    int j = paramArrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str = String.format("UMENG_TAG_%s", new Object[] { paramArrayOfString[i] });
      if (this.c.getBoolean(str, false))
      {
        localEditor.remove(str);
        localEditor.putInt("UMENG_TAG_COUNT", getTagCount() - 1);
      }
      i += 1;
    }
    localEditor.commit();
  }

  public void resetTags()
  {
    SharedPreferences.Editor localEditor = this.c.edit();
    Object localObject = this.c.getAll();
    if ((localObject != null) && (((Map)localObject).size() > 0))
    {
      localObject = ((Map)localObject).entrySet().iterator();
      while (((Iterator)localObject).hasNext())
      {
        String str = (String)((Map.Entry)((Iterator)localObject).next()).getKey();
        if (str.startsWith("UMENG_TAG_"))
          localEditor.remove(str);
      }
    }
    localEditor.commit();
  }

  public void setAppLaunchLogSendPolicy(int paramInt)
  {
    this.c.edit().putInt("KEY_APP_LAUNCH_LOG_SEND_POLICY", paramInt).commit();
  }

  public void setAppLaunchLogSentAt(long paramLong)
  {
    this.c.edit().putLong("KEY_LAUNCH_LOG_SENT_MARK", Calendar.getInstance().getTimeInMillis()).commit();
  }

  public void setMergeNotificaiton(boolean paramBoolean)
  {
    this.c.edit().putBoolean("KEY_MERGE_NOTIFICATION", paramBoolean).commit();
  }

  public void setMessageAppKey(String paramString)
  {
    this.c.edit().putString("KEY_UMENG_MESSAGE_APP_KEY", paramString).commit();
  }

  public void setMessageAppSecret(String paramString)
  {
    this.c.edit().putString("KEY_UMENG_MESSAGE_APP_SECRET", paramString).commit();
  }

  public void setMessageChannel(String paramString)
  {
    this.c.edit().putString("KEY_UMENG_MESSAGE_APP_CHANNEL", paramString).commit();
  }

  public void setNotificaitonOnForeground(boolean paramBoolean)
  {
    this.c.edit().putBoolean("KEY_SET_NOTIFICATION_ON_FOREGROUND", paramBoolean).commit();
  }

  public <U extends UmengBaseIntentService> void setPushIntentServiceClass(Class<U> paramClass)
  {
    if (paramClass == null)
    {
      this.c.edit().remove("KEY_PUSH_INTENT_SERVICE_CLASSNAME").remove("KEY_SET_PUSH_INTENT_SERVICE_VERSION_CODE").commit();
      return;
    }
    paramClass = paramClass.getName();
    String str = UmengMessageDeviceConfig.getAppVersionCode(this.b);
    this.c.edit().putString("KEY_PUSH_INTENT_SERVICE_CLASSNAME", paramClass).putString("KEY_SET_PUSH_INTENT_SERVICE_VERSION_CODE", str).commit();
  }

  public void setResourcePackageName(String paramString)
  {
    this.c.edit().putString("KEY_SET_RESOURCE_PACKAGENAME", paramString).commit();
  }

  public void setSerialNo(int paramInt)
  {
    this.b.getSharedPreferences("umeng_message_state", this.e).edit().putInt("serial_no", paramInt).commit();
  }

  public void setTagRemain(int paramInt)
  {
    SharedPreferences.Editor localEditor = this.c.edit();
    localEditor.putInt("UMENG_TAG_REMAIN", paramInt);
    localEditor.commit();
  }

  public void setTagSendPolicy(int paramInt)
  {
    this.c.edit().putInt("KEY_TAG_SEND_POLICY", paramInt).commit();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.MessageSharedPrefs
 * JD-Core Version:    0.6.2
 */