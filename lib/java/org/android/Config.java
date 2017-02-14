package org.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import org.android.agoo.ut.UTFactroy;

public class Config
{
  public static final String AGOO_CONNECT_TYPE = "agoo_connect_type";
  public static final String AGOO_DNS_ERRORID = "agoo_dns_errorid";
  public static final String AGOO_DNS_EVENTID = "agoo_dns_eventid";
  public static final String AGOO_DNS_PATH = "agoo_dns_path";
  public static final String AGOO_NOTIC_ELECTION_RESULT = "agoo_notic_election_result";
  public static final String AGOO_PING_MESSAGE = "agoo_ping_message";
  public static final String AGOO_PUSH_ERRORID = "agoo_push_errorid";
  public static final String AGOO_PUSH_PATH = "agoo_push_path";
  public static final String AGOO_RELEASE_TIME = "agoo_release_time";
  public static final String AGOO_SERVICE_PROTECT = "agoo_service_protect";
  public static final int DEFAULT_BACKOFF_MS = 3000;
  public static final String PREFERENCES = "AppStore";
  public static final String PROPERTY_AGOO_AUTOUPDATE = "agoo_autoupdate";
  public static final String PROPERTY_AGOO_END_TIME = "agoo_end_time";
  public static final String PROPERTY_AGOO_HTTP_CONNECT = "agoo_http_connect";
  public static final String PROPERTY_AGOO_IFNEEDAUTOUPDATE = "agoo_ifNeedAutoUpdate";
  public static final String PROPERTY_AGOO_IFNEEDNOTAUTOUPDATE = "agoo_ifNeedNotAutoUpdate";
  public static final String PROPERTY_AGOO_MODE = "agoo_mode";
  public static final String PROPERTY_AGOO_MULTIPLEX = "agoo_multiplex";
  public static final String PROPERTY_AGOO_POSTDATA = "agoo_postData";
  public static final String PROPERTY_AGOO_SECURITY_MODE = "agoo_security_mode";
  public static final String PROPERTY_AGOO_SERVICE_MODE = "agoo_service_mode";
  public static final String PROPERTY_AGOO_SPDY = "agoo_spdy";
  public static final String PROPERTY_AGOO_SPDY_CONNECT = "agoo_spdy_connect";
  public static final String PROPERTY_AGOO_START_TIME = "agoo_start_time";
  public static final String PROPERTY_AGOO_UPDATECYCLE = "agoo_updateCycle";
  public static final String PROPERTY_AGOO_UPDATE_TIME = "agoo_update_time";
  public static final String PROPERTY_APP_DEBUG = "app_debug";
  public static final String PROPERTY_APP_KEY = "app_key";
  public static final String PROPERTY_APP_KEY_TEMP = "app_key_temp";
  public static final String PROPERTY_APP_LOG2FIlE = "app_log_to_file";
  public static final String PROPERTY_APP_SECRET = "app_sercet";
  public static final String PROPERTY_APP_SECRET_TEMP = "app_sercet_temp";
  public static final String PROPERTY_APP_VERSION = "app_version";
  public static final String PROPERTY_DEVICE_TOKEN = "app_device_token";
  public static final String PROPERTY_DEVICE_TOKEN_TEMP = "app_device_token_temp";
  public static final String PROPERTY_LOGGER_CLASSNAME = "logger_class_name";
  private static final String PROPERTY_OLD_DEVICE_TOKEN = "old_app_device_token";
  public static final String PROPERTY_PUSH_TEST_HOST = "app_push_test_host";
  public static final String PROPERTY_PUSH_TEST_PORT = "app_push_test_port";
  public static final String PROPERTY_PUSH_USER_TOKEN = "app_push_user_token";
  public static final String PROPERTY_TT_ID = "app_tt_id";
  public static final String PROPERTY_TT_ID_TEMP = "app_tt_id_temp";
  public static final String PROPERTY_XTOKEN = "agoo_xtoken ";
  public static final String UNREGISTER_FLAG = "unregister_flag";
  public static final String UPDATE_APP_LAST_APP_VERSION = "update_app_last_app_version ";

  public static void clear(Context paramContext)
  {
    try
    {
      paramContext = getAgooPreferences(paramContext).edit();
      paramContext.putInt("app_version", -2147483648);
      paramContext.remove("app_device_token");
      paramContext.remove("app_key");
      paramContext.remove("app_sercet");
      paramContext.remove("app_tt_id");
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void clearPushUserToken(Context paramContext)
  {
    try
    {
      paramContext = getAgooPreferences(paramContext).edit();
      paramContext.remove("app_push_user_token");
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void clearXToken(Context paramContext)
  {
    try
    {
      paramContext = getAgooPreferences(paramContext).edit();
      paramContext.remove("agoo_xtoken ");
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static String getAgooGroup(Context paramContext)
  {
    try
    {
      paramContext = getAgooPreferences(paramContext).getString("agoo_service_mode", "taobao");
      return paramContext;
    }
    catch (Throwable paramContext)
    {
    }
    return "";
  }

  public static int getAgooMode(Context paramContext)
  {
    try
    {
      int i = getAgooPreferences(paramContext).getInt("agoo_mode", -1);
      return i;
    }
    catch (Throwable paramContext)
    {
    }
    return 0;
  }

  private static SharedPreferences getAgooPreferences(Context paramContext)
  {
    return paramContext.getSharedPreferences("AppStore", 4);
  }

  public static long getAgooReleaseTime(Context paramContext)
  {
    try
    {
      long l = getAgooPreferences(paramContext).getLong("agoo_release_time", 0L);
      return l;
    }
    catch (Throwable paramContext)
    {
    }
    return 0L;
  }

  public static String getAppKey(Context paramContext)
  {
    try
    {
      paramContext = getAgooPreferences(paramContext).getString("app_key", "");
      return paramContext;
    }
    catch (Throwable paramContext)
    {
    }
    return "";
  }

  public static String getAppSecret(Context paramContext)
  {
    try
    {
      paramContext = getAgooPreferences(paramContext).getString("app_sercet", "");
      return paramContext;
    }
    catch (Throwable paramContext)
    {
    }
    return "";
  }

  public static int getAppVersion(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
      String str = paramContext.versionName;
      int i = paramContext.versionCode;
      i = (str + "." + i).hashCode();
      return i;
    }
    catch (Throwable paramContext)
    {
    }
    return -1;
  }

  public static String getAppVersionName(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (Throwable paramContext)
    {
    }
    return null;
  }

  // ERROR //
  public static java.util.LinkedHashMap<String, String> getConnectError(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc_w 258
    //   4: iconst_4
    //   5: invokevirtual 201	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   8: astore_2
    //   9: aload_2
    //   10: ldc_w 260
    //   13: aconst_null
    //   14: invokeinterface 187 3 0
    //   19: astore_3
    //   20: aload_2
    //   21: ldc_w 262
    //   24: iconst_m1
    //   25: invokeinterface 195 3 0
    //   30: istore_1
    //   31: aload_0
    //   32: ldc 41
    //   34: iconst_4
    //   35: invokevirtual 201	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   38: ldc 8
    //   40: aconst_null
    //   41: invokeinterface 187 3 0
    //   46: astore 4
    //   48: new 264	java/util/LinkedHashMap
    //   51: dup
    //   52: invokespecial 265	java/util/LinkedHashMap:<init>	()V
    //   55: astore_2
    //   56: aload_2
    //   57: ldc_w 267
    //   60: aload_3
    //   61: invokevirtual 271	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   64: pop
    //   65: aload_2
    //   66: ldc_w 273
    //   69: iload_1
    //   70: invokestatic 278	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   73: invokevirtual 271	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   76: pop
    //   77: aload_2
    //   78: ldc_w 280
    //   81: aload 4
    //   83: invokevirtual 271	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   86: pop
    //   87: aload_2
    //   88: ldc_w 282
    //   91: aload_0
    //   92: invokestatic 284	org/android/Config:getAppKey	(Landroid/content/Context;)Ljava/lang/String;
    //   95: invokevirtual 271	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   98: pop
    //   99: aload_2
    //   100: ldc_w 286
    //   103: aload_0
    //   104: invokestatic 289	org/android/Config:getDeviceToken	(Landroid/content/Context;)Ljava/lang/String;
    //   107: invokevirtual 271	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   110: pop
    //   111: aload_2
    //   112: ldc_w 291
    //   115: aload_0
    //   116: invokestatic 293	org/android/Config:getAgooReleaseTime	(Landroid/content/Context;)J
    //   119: invokestatic 298	java/lang/Long:toString	(J)Ljava/lang/String;
    //   122: invokevirtual 271	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   125: pop
    //   126: aload_0
    //   127: invokestatic 301	org/android/Config:getPushServiceErrorId	(Landroid/content/Context;)Ljava/util/LinkedHashMap;
    //   130: astore_3
    //   131: aload_3
    //   132: ifnull +15 -> 147
    //   135: aload_3
    //   136: invokevirtual 304	java/util/LinkedHashMap:size	()I
    //   139: ifle +8 -> 147
    //   142: aload_2
    //   143: aload_3
    //   144: invokevirtual 308	java/util/LinkedHashMap:putAll	(Ljava/util/Map;)V
    //   147: aload_0
    //   148: invokestatic 311	org/android/Config:getDnsErrorId	(Landroid/content/Context;)Ljava/util/LinkedHashMap;
    //   151: astore_0
    //   152: aload_0
    //   153: ifnull +15 -> 168
    //   156: aload_0
    //   157: invokevirtual 304	java/util/LinkedHashMap:size	()I
    //   160: ifle +8 -> 168
    //   163: aload_2
    //   164: aload_0
    //   165: invokevirtual 308	java/util/LinkedHashMap:putAll	(Ljava/util/Map;)V
    //   168: aload_2
    //   169: areturn
    //   170: astore_2
    //   171: aconst_null
    //   172: astore_0
    //   173: ldc_w 312
    //   176: new 234	java/lang/StringBuilder
    //   179: dup
    //   180: invokespecial 235	java/lang/StringBuilder:<init>	()V
    //   183: ldc_w 314
    //   186: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   189: aload_2
    //   190: invokevirtual 317	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   193: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   196: invokevirtual 247	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   199: invokestatic 323	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   202: pop
    //   203: aload_0
    //   204: areturn
    //   205: astore_3
    //   206: aload_2
    //   207: astore_0
    //   208: aload_3
    //   209: astore_2
    //   210: goto -37 -> 173
    //
    // Exception table:
    //   from	to	target	type
    //   0	56	170	java/lang/Throwable
    //   56	131	205	java/lang/Throwable
    //   135	147	205	java/lang/Throwable
    //   147	152	205	java/lang/Throwable
    //   156	168	205	java/lang/Throwable
  }

  // ERROR //
  public static java.util.LinkedHashMap<String, String> getConnectHeader(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc_w 258
    //   4: iconst_4
    //   5: invokevirtual 201	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   8: astore_2
    //   9: aload_2
    //   10: ldc_w 260
    //   13: aconst_null
    //   14: invokeinterface 187 3 0
    //   19: astore_3
    //   20: aload_2
    //   21: ldc_w 262
    //   24: iconst_m1
    //   25: invokeinterface 195 3 0
    //   30: istore_1
    //   31: aload_0
    //   32: ldc 41
    //   34: iconst_4
    //   35: invokevirtual 201	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   38: ldc 8
    //   40: aconst_null
    //   41: invokeinterface 187 3 0
    //   46: astore 4
    //   48: new 264	java/util/LinkedHashMap
    //   51: dup
    //   52: invokespecial 265	java/util/LinkedHashMap:<init>	()V
    //   55: astore_2
    //   56: aload_2
    //   57: ldc_w 267
    //   60: aload_3
    //   61: invokevirtual 271	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   64: pop
    //   65: aload_2
    //   66: ldc_w 273
    //   69: iload_1
    //   70: invokestatic 278	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   73: invokevirtual 271	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   76: pop
    //   77: aload_2
    //   78: ldc_w 280
    //   81: aload 4
    //   83: invokevirtual 271	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   86: pop
    //   87: aload_2
    //   88: ldc_w 282
    //   91: aload_0
    //   92: invokestatic 284	org/android/Config:getAppKey	(Landroid/content/Context;)Ljava/lang/String;
    //   95: invokevirtual 271	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   98: pop
    //   99: aload_2
    //   100: ldc_w 286
    //   103: aload_0
    //   104: invokestatic 289	org/android/Config:getDeviceToken	(Landroid/content/Context;)Ljava/lang/String;
    //   107: invokevirtual 271	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   110: pop
    //   111: aload_2
    //   112: ldc_w 291
    //   115: aload_0
    //   116: invokestatic 293	org/android/Config:getAgooReleaseTime	(Landroid/content/Context;)J
    //   119: invokestatic 298	java/lang/Long:toString	(J)Ljava/lang/String;
    //   122: invokevirtual 271	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   125: pop
    //   126: aload_2
    //   127: areturn
    //   128: astore_2
    //   129: aconst_null
    //   130: astore_0
    //   131: ldc_w 327
    //   134: new 234	java/lang/StringBuilder
    //   137: dup
    //   138: invokespecial 235	java/lang/StringBuilder:<init>	()V
    //   141: ldc_w 314
    //   144: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   147: aload_2
    //   148: invokevirtual 317	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   151: invokevirtual 239	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   154: invokevirtual 247	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   157: invokestatic 323	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   160: pop
    //   161: aload_0
    //   162: areturn
    //   163: astore_3
    //   164: aload_2
    //   165: astore_0
    //   166: aload_3
    //   167: astore_2
    //   168: goto -37 -> 131
    //
    // Exception table:
    //   from	to	target	type
    //   0	56	128	java/lang/Throwable
    //   56	126	163	java/lang/Throwable
  }

  // ERROR //
  public static String getDeviceToken(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 156	org/android/Config:getAgooPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   4: astore 4
    //   6: aload 4
    //   8: ldc 110
    //   10: ldc 189
    //   12: invokeinterface 187 3 0
    //   17: astore_3
    //   18: aload 4
    //   20: ldc 107
    //   22: ldc 163
    //   24: invokeinterface 195 3 0
    //   29: istore_1
    //   30: aload_0
    //   31: invokestatic 329	org/android/Config:getAppVersion	(Landroid/content/Context;)I
    //   34: istore_2
    //   35: iload_1
    //   36: ldc 163
    //   38: if_icmpeq +51 -> 89
    //   41: iload_1
    //   42: iload_2
    //   43: if_icmpeq +46 -> 89
    //   46: aload 4
    //   48: invokeinterface 162 1 0
    //   53: astore_0
    //   54: aload_0
    //   55: ldc 107
    //   57: ldc 163
    //   59: invokeinterface 169 3 0
    //   64: pop
    //   65: aload_0
    //   66: ldc 110
    //   68: invokeinterface 173 2 0
    //   73: pop
    //   74: aload_0
    //   75: invokeinterface 177 1 0
    //   80: pop
    //   81: aconst_null
    //   82: areturn
    //   83: astore_0
    //   84: aconst_null
    //   85: areturn
    //   86: astore_0
    //   87: aload_3
    //   88: areturn
    //   89: aload_3
    //   90: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	18	83	java/lang/Throwable
    //   18	35	86	java/lang/Throwable
    //   46	81	86	java/lang/Throwable
  }

  // ERROR //
  public static java.util.LinkedHashMap<String, String> getDnsErrorId(Context paramContext)
  {
    // Byte code:
    //   0: new 264	java/util/LinkedHashMap
    //   3: dup
    //   4: invokespecial 265	java/util/LinkedHashMap:<init>	()V
    //   7: astore_1
    //   8: aload_0
    //   9: invokestatic 156	org/android/Config:getAgooPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   12: astore_3
    //   13: aload_3
    //   14: ldc 11
    //   16: aconst_null
    //   17: invokeinterface 187 3 0
    //   22: astore_0
    //   23: aload_3
    //   24: ldc 17
    //   26: aconst_null
    //   27: invokeinterface 187 3 0
    //   32: astore_2
    //   33: aload_3
    //   34: ldc 14
    //   36: aconst_null
    //   37: invokeinterface 187 3 0
    //   42: astore_3
    //   43: aload_1
    //   44: ldc_w 331
    //   47: aload_0
    //   48: invokevirtual 271	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   51: pop
    //   52: aload_1
    //   53: ldc_w 333
    //   56: aload_2
    //   57: invokevirtual 271	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   60: pop
    //   61: aload_1
    //   62: ldc_w 335
    //   65: aload_3
    //   66: invokevirtual 271	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   69: pop
    //   70: aload_1
    //   71: areturn
    //   72: astore_0
    //   73: aconst_null
    //   74: areturn
    //   75: astore_0
    //   76: aload_1
    //   77: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	8	72	java/lang/Throwable
    //   8	70	75	java/lang/Throwable
  }

  public static int getHttpConnectTimes(Context paramContext)
  {
    try
    {
      int i = getAgooPreferences(paramContext).getInt("agoo_http_connect", 0);
      return i;
    }
    catch (Throwable paramContext)
    {
    }
    return 0;
  }

  public static int getLastAppVersion(Context paramContext)
  {
    try
    {
      int i = getAgooPreferences(paramContext).getInt("update_app_last_app_version ", 0);
      return i;
    }
    catch (Throwable paramContext)
    {
    }
    return 0;
  }

  public static String getLoggerClassName(Context paramContext)
  {
    try
    {
      paramContext = getAgooPreferences(paramContext).getString("logger_class_name", null);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
    }
    return null;
  }

  public static String getOldDeviceToken(Context paramContext)
  {
    try
    {
      paramContext = getAgooPreferences(paramContext).getString("old_app_device_token", null);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
    }
    return null;
  }

  public static boolean getPingMessage(Context paramContext)
  {
    try
    {
      boolean bool = getAgooPreferences(paramContext).getBoolean("agoo_ping_message", true);
      return bool;
    }
    catch (Throwable paramContext)
    {
    }
    return false;
  }

  // ERROR //
  public static java.util.LinkedHashMap<String, String> getPushServiceErrorId(Context paramContext)
  {
    // Byte code:
    //   0: new 264	java/util/LinkedHashMap
    //   3: dup
    //   4: invokespecial 265	java/util/LinkedHashMap:<init>	()V
    //   7: astore_1
    //   8: aload_0
    //   9: invokestatic 156	org/android/Config:getAgooPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   12: astore_2
    //   13: aload_2
    //   14: ldc 26
    //   16: aconst_null
    //   17: invokeinterface 187 3 0
    //   22: astore_0
    //   23: aload_2
    //   24: ldc 29
    //   26: aconst_null
    //   27: invokeinterface 187 3 0
    //   32: astore_2
    //   33: aload_1
    //   34: ldc_w 347
    //   37: aload_0
    //   38: invokevirtual 271	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   41: pop
    //   42: aload_1
    //   43: ldc_w 349
    //   46: aload_2
    //   47: invokevirtual 271	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   50: pop
    //   51: aload_1
    //   52: areturn
    //   53: astore_0
    //   54: aconst_null
    //   55: areturn
    //   56: astore_0
    //   57: aload_1
    //   58: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	8	53	java/lang/Throwable
    //   8	51	56	java/lang/Throwable
  }

  public static String getPushUserToken(Context paramContext)
  {
    try
    {
      paramContext = getAgooPreferences(paramContext).getString("app_push_user_token", "");
      return paramContext;
    }
    catch (Throwable paramContext)
    {
    }
    return null;
  }

  public static boolean getServiceProtect(Context paramContext)
  {
    try
    {
      boolean bool = getAgooPreferences(paramContext).getBoolean("agoo_service_protect", true);
      return bool;
    }
    catch (Throwable paramContext)
    {
    }
    return false;
  }

  public static int getSpdyConnectTimes(Context paramContext)
  {
    try
    {
      int i = getAgooPreferences(paramContext).getInt("agoo_spdy_connect", 0);
      return i;
    }
    catch (Throwable paramContext)
    {
    }
    return 0;
  }

  public static String getTtId(Context paramContext)
  {
    try
    {
      paramContext = getAgooPreferences(paramContext).getString("app_tt_id", "");
      return paramContext;
    }
    catch (Throwable paramContext)
    {
    }
    return "";
  }

  public static boolean getUnregisterFlag(Context paramContext)
  {
    try
    {
      boolean bool = getAgooPreferences(paramContext).getBoolean("unregister_flag", false);
      return bool;
    }
    catch (Throwable paramContext)
    {
    }
    return false;
  }

  public static final String getXToken(Context paramContext)
  {
    try
    {
      paramContext = getAgooPreferences(paramContext).getString("agoo_xtoken ", "");
      return paramContext;
    }
    catch (Throwable paramContext)
    {
    }
    return "";
  }

  public static final boolean hasNoticeElection(Context paramContext)
  {
    try
    {
      boolean bool = getAgooPreferences(paramContext).getBoolean("agoo_notic_election_result", false);
      return bool;
    }
    catch (Throwable paramContext)
    {
    }
    return false;
  }

  public static boolean ifNeedNotAutoUpdate(Context paramContext)
  {
    try
    {
      boolean bool = getAgooPreferences(paramContext).getBoolean("agoo_ifNeedNotAutoUpdate", false);
      return bool;
    }
    catch (Throwable paramContext)
    {
    }
    return false;
  }

  public static boolean isAgooSoSecurityMode(Context paramContext)
  {
    return getAgooPreferences(paramContext).getBoolean("agoo_security_mode", false);
  }

  public static boolean isEnablePostData(Context paramContext)
  {
    try
    {
      boolean bool = getAgooPreferences(paramContext).getBoolean("agoo_postData", false);
      return bool;
    }
    catch (Throwable paramContext)
    {
    }
    return false;
  }

  public static boolean isEnableSwitchConfig(Context paramContext)
  {
    try
    {
      boolean bool = getAgooPreferences(paramContext).getBoolean("agoo_autoupdate", false);
      return bool;
    }
    catch (Throwable paramContext)
    {
    }
    return false;
  }

  public static boolean isEnableUpdate_ByApp(Context paramContext)
  {
    try
    {
      boolean bool = getAgooPreferences(paramContext).getBoolean("agoo_ifNeedAutoUpdate", true);
      return bool;
    }
    catch (Throwable paramContext)
    {
    }
    return false;
  }

  public static boolean isTimeToUpdate(Context paramContext, long paramLong)
  {
    try
    {
      paramContext = getAgooPreferences(paramContext);
      long l1 = paramContext.getLong("agoo_update_time", 0L);
      long l2 = paramContext.getLong("agoo_updateCycle", 86400L);
      l1 = l2 * 1000L + l1;
      if ((l1 != 0L) && (paramLong != 0L) && (paramLong - l1 > 0L))
        return true;
    }
    catch (Throwable paramContext)
    {
    }
    return false;
  }

  public static void set(Context paramContext, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, long paramLong, boolean paramBoolean4)
  {
    try
    {
      paramContext = getAgooPreferences(paramContext).edit();
      paramContext.putBoolean("agoo_autoupdate", paramBoolean1);
      paramContext.putBoolean("agoo_multiplex", paramBoolean2);
      paramContext.putBoolean("agoo_spdy", paramBoolean2);
      paramContext.putLong("agoo_updateCycle", paramLong);
      paramContext.putBoolean("agoo_postData", paramBoolean4);
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static void setAgooGroup(Context paramContext, String paramString)
  {
    if (paramContext != null);
    try
    {
      paramContext = getAgooPreferences(paramContext).edit();
      paramContext.putString("agoo_service_mode", paramString);
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static void setAgooMode(Context paramContext, int paramInt)
  {
    if (paramContext != null);
    try
    {
      paramContext = getAgooPreferences(paramContext).edit();
      paramContext.putInt("agoo_mode", paramInt);
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static void setAgooReleaseTime(Context paramContext, long paramLong)
  {
    try
    {
      paramContext = getAgooPreferences(paramContext).edit();
      paramContext.putLong("agoo_release_time", paramLong);
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static void setAgooSecurityMode(Context paramContext, boolean paramBoolean)
  {
    if (paramContext != null);
    try
    {
      paramContext = getAgooPreferences(paramContext).edit();
      paramContext.putBoolean("agoo_security_mode", paramBoolean);
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static void setAppInfo(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    try
    {
      Object localObject = getAgooPreferences(paramContext);
      String str1 = ((SharedPreferences)localObject).getString("app_key", null);
      String str2 = ((SharedPreferences)localObject).getString("app_device_token", null);
      localObject = ((SharedPreferences)localObject).edit();
      if ((!TextUtils.isEmpty(str1)) && (!TextUtils.isEmpty(paramString1)) && (!TextUtils.equals(paramString1, str1)))
        clear(paramContext);
      if (!TextUtils.isEmpty(str2))
        ((SharedPreferences.Editor)localObject).putString("old_app_device_token", str2);
      ((SharedPreferences.Editor)localObject).putString("app_key", paramString1);
      ((SharedPreferences.Editor)localObject).putString("app_tt_id", paramString3);
      ((SharedPreferences.Editor)localObject).putString("app_key_temp", paramString1);
      ((SharedPreferences.Editor)localObject).putString("app_tt_id_temp", paramString3);
      if (!TextUtils.isEmpty(paramString2))
      {
        ((SharedPreferences.Editor)localObject).putString("app_sercet", paramString2);
        ((SharedPreferences.Editor)localObject).putString("app_sercet_temp", paramString2);
      }
      ((SharedPreferences.Editor)localObject).commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static void setAvailableTime(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    try
    {
      paramContext = getAgooPreferences(paramContext).edit();
      paramContext.putInt("agoo_start_time", paramInt1 * 60 * 60 + paramInt2 * 60 + 0);
      paramContext.putInt("agoo_end_time", paramInt3 * 60 * 60 + paramInt4 * 60 + 0);
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static void setDebug(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    try
    {
      paramContext = getAgooPreferences(paramContext).edit();
      paramContext.putBoolean("app_debug", paramBoolean1);
      paramContext.putBoolean("app_log_to_file", paramBoolean2);
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static void setHttpConnectTimes(Context paramContext, int paramInt)
  {
    int i = paramInt;
    if (paramInt == -1);
    try
    {
      i = getHttpConnectTimes(paramContext) + 1;
      paramContext = getAgooPreferences(paramContext).edit();
      paramContext.putInt("agoo_http_connect", i);
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static void setIfNeedAutoUpdate(Context paramContext, boolean paramBoolean)
  {
    try
    {
      paramContext = getAgooPreferences(paramContext).edit();
      paramContext.putBoolean("agoo_ifNeedAutoUpdate", paramBoolean);
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void setLastAppVersion(Context paramContext, int paramInt)
  {
    try
    {
      paramContext = getAgooPreferences(paramContext).edit();
      paramContext.putInt("update_app_last_app_version ", paramInt);
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static void setNeedNotAutoUpdate(Context paramContext, boolean paramBoolean)
  {
    try
    {
      paramContext = getAgooPreferences(paramContext).edit();
      paramContext.putBoolean("agoo_ifNeedNotAutoUpdate", paramBoolean);
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void setNoticeResult(Context paramContext, boolean paramBoolean)
  {
    try
    {
      paramContext = getAgooPreferences(paramContext).edit();
      paramContext.putBoolean("agoo_notic_election_result", paramBoolean);
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static void setPingMessage(Context paramContext, boolean paramBoolean)
  {
    try
    {
      SharedPreferences.Editor localEditor = getAgooPreferences(paramContext).edit();
      localEditor.putBoolean("agoo_ping_message", paramBoolean);
      localEditor.commit();
      UTFactroy.getInstance().commitEvent(paramContext, "agoo_ping_message", new String[] { "agoo_ping_message", "ping=" + paramBoolean });
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static void setPushUserToken(Context paramContext, String paramString)
  {
    try
    {
      paramContext = getAgooPreferences(paramContext).edit();
      paramContext.putString("app_push_user_token", paramString);
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static void setServiceProtect(Context paramContext, boolean paramBoolean)
  {
    try
    {
      SharedPreferences.Editor localEditor = getAgooPreferences(paramContext).edit();
      localEditor.putBoolean("agoo_service_protect", paramBoolean);
      localEditor.commit();
      UTFactroy.getInstance().commitEvent(paramContext, "setServiceProtect", new String[] { "setServiceProtect", "protect=" + paramBoolean });
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static void setSpdyConnectTimes(Context paramContext, int paramInt)
  {
    int i = paramInt;
    if (paramInt == -1);
    try
    {
      i = getSpdyConnectTimes(paramContext) + 1;
      paramContext = getAgooPreferences(paramContext).edit();
      paramContext.putInt("agoo_spdy_connect", i);
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static void setTestHostAndPort(Context paramContext, String paramString, int paramInt)
  {
    try
    {
      paramContext = getAgooPreferences(paramContext).edit();
      paramContext.putInt("app_push_test_port", paramInt);
      paramContext.putString("app_push_test_host", paramString);
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static void setUTClassName(Context paramContext, String paramString)
  {
    try
    {
      paramContext = getAgooPreferences(paramContext).edit();
      paramContext.putString("logger_class_name", paramString);
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void setUnregisterFlag(Context paramContext, boolean paramBoolean)
  {
    try
    {
      paramContext = getAgooPreferences(paramContext).edit();
      paramContext.putBoolean("unregister_flag", paramBoolean);
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static void setUpdateTime(Context paramContext, long paramLong)
  {
    try
    {
      paramContext = getAgooPreferences(paramContext).edit();
      paramContext.putLong("agoo_update_time", paramLong);
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void setXToken(Context paramContext, String paramString)
  {
    try
    {
      paramContext = getAgooPreferences(paramContext).edit();
      paramContext.putString("agoo_xtoken ", paramString);
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.Config
 * JD-Core Version:    0.6.2
 */