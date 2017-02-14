package org.android.agoo.intent;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public final class IntentUtil
{
  private static final String AGOO_ACTION = "org.agoo.android.intent.action.";
  public static final String AGOO_COMMAND = "command";
  public static final String AGOO_COMMAND_BIND_USER = "command_bind_user";
  public static final String AGOO_COMMAND_BIND_USER_XTOKEN = "command_bind_user_xtoken";
  public static final String AGOO_COMMAND_OTHER_CHANNEL = "command_other_channel";
  public static final String AGOO_COMMAND_REGISTRATION_CALLBACK = "registration";
  public static final String AGOO_COMMAND_RESTART_SUDO = "command_restart_sudo";
  public static final String AGOO_COMMAND_RESTART_SUDO_APP = "agoo_command_restart_sudo_app";
  public static final String AGOO_COMMAND_RE_BIND_USER = "re_user";
  public static final String AGOO_COMMAND_UNBIND_USER = "command_unbind_user";
  private static final String AGOO_PACKAGE_NAME = "org.agoo.android";
  private static final String INTENT_FROM_AGOO_COCKROACH = ".intent.action.COCKROACH";
  private static final String INTENT_FROM_AGOO_COMMAND = ".intent.action.COMMAND";
  public static final String INTENT_FROM_AGOO_ELECTION_RESULT = "org.agoo.android.intent.action.ELECTION_RESULT_V4";
  public static final String INTENT_FROM_AGOO_MESSAGE = "org.agoo.android.intent.action.RECEIVE";
  public static final String INTENT_FROM_AGOO_PING = "org.agoo.android.intent.action.PING_V4";
  public static final String INTENT_FROM_AGOO_SEND = ".intent.action.SEND";
  private static final String INTENT_FROM_AGOO_START = ".intent.action.START";
  public static final String OTHER_CHANNEL_ANDROID_DEVICE_TOKEN = "channel_android_device_token";
  public static final String OTHER_CHANNEL_ANDROID_DEVICE_TYPE = "channel_android_device_type";
  private static final String TAG = "IntentUtil";

  // ERROR //
  public static final Intent createComandIntent(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: new 79	android/content/Intent
    //   3: dup
    //   4: invokespecial 80	android/content/Intent:<init>	()V
    //   7: astore_2
    //   8: aload_2
    //   9: aload_0
    //   10: invokestatic 84	org/android/agoo/intent/IntentUtil:getAgooCommand	(Landroid/content/Context;)Ljava/lang/String;
    //   13: invokevirtual 88	android/content/Intent:setAction	(Ljava/lang/String;)Landroid/content/Intent;
    //   16: pop
    //   17: aload_2
    //   18: aload_0
    //   19: invokevirtual 94	android/content/Context:getPackageName	()Ljava/lang/String;
    //   22: invokevirtual 97	android/content/Intent:setPackage	(Ljava/lang/String;)Landroid/content/Intent;
    //   25: pop
    //   26: aload_2
    //   27: ldc 11
    //   29: aload_1
    //   30: invokevirtual 101	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   33: pop
    //   34: aload_2
    //   35: areturn
    //   36: astore_1
    //   37: aconst_null
    //   38: astore_0
    //   39: ldc 68
    //   41: ldc 102
    //   43: aload_1
    //   44: invokestatic 108	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   47: pop
    //   48: aload_0
    //   49: areturn
    //   50: astore_1
    //   51: aload_2
    //   52: astore_0
    //   53: goto -14 -> 39
    //
    // Exception table:
    //   from	to	target	type
    //   0	8	36	java/lang/Throwable
    //   8	34	50	java/lang/Throwable
  }

  public static final String getAgooCockroach(Context paramContext)
  {
    if (paramContext != null)
      try
      {
        paramContext = paramContext.getPackageName() + ".intent.action.COCKROACH";
        return paramContext;
      }
      catch (Throwable paramContext)
      {
        Log.w("IntentUtil", "getAgooCockroach", paramContext);
      }
    return null;
  }

  public static final String getAgooCommand(Context paramContext)
  {
    if (paramContext != null)
      try
      {
        paramContext = paramContext.getPackageName() + ".intent.action.COMMAND";
        return paramContext;
      }
      catch (Throwable paramContext)
      {
        Log.w("IntentUtil", "getAgooCommand", paramContext);
      }
    return null;
  }

  public static String getAgooSendAction(Context paramContext)
  {
    if (paramContext != null)
      try
      {
        paramContext = paramContext.getPackageName() + ".intent.action.SEND";
        return paramContext;
      }
      catch (Throwable paramContext)
      {
        Log.w("IntentUtil", "getAgooSendAction", paramContext);
      }
    return null;
  }

  public static final String getAgooStart(Context paramContext)
  {
    if (paramContext != null)
      try
      {
        paramContext = paramContext.getPackageName() + ".intent.action.START";
        return paramContext;
      }
      catch (Throwable paramContext)
      {
        Log.w("IntentUtil", "getAgooStart", paramContext);
      }
    return null;
  }

  public static final void sendOtherChannel(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      Intent localIntent = createComandIntent(paramContext, "command_other_channel");
      localIntent.putExtra("channel_android_device_token", paramString1);
      localIntent.putExtra("channel_android_device_type", paramString2);
      localIntent.setPackage(paramContext.getPackageName());
      paramContext.sendBroadcast(localIntent);
      return;
    }
    catch (Throwable paramContext)
    {
      Log.w("IntentUtil", "sendOtherChannel", paramContext);
    }
  }

  public static final void sendOtherMessage(Context paramContext, Intent paramIntent, String paramString)
  {
    try
    {
      Intent localIntent = new Intent();
      localIntent.setAction("org.agoo.android.intent.action.RECEIVE");
      localIntent.putExtra("message_source", paramString);
      localIntent.putExtras(paramIntent.getExtras());
      localIntent.setPackage(paramContext.getPackageName());
      paramContext.sendBroadcast(localIntent);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.intent.IntentUtil
 * JD-Core Version:    0.6.2
 */