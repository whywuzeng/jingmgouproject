package com.umeng.message.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import com.ta.utdid2.device.UTDevice;
import java.util.LinkedHashMap;
import java.util.Map;
import org.android.Config;
import org.android.agoo.a;
import org.android.agoo.ut.UT;
import org.android.agoo.ut.UTFactroy;

public final class aW
{
  private static final String A = "agoo_checkpackage_stop";
  private static final String B = "agoo_start_method";
  private static final String C = "agoo_pushService_connect_success";
  private static final String D = "agoo_pushservice_connect_error";
  private static final String E = "agoo_somanager_start";
  private static final String F = "agoo_event_register_log";
  private static final String G = "agoo_event_election_log";
  private static final String H = "agoo_event_connect_log";
  private static final String I = "agoo_event_getServiceMsg_log";
  private static final String J = "agoo_event_dns_log";
  private static final String K = "agoo_event_election_result_log";
  private static final String L = "agoo_event_register_begin_log";
  private static final String M = "agoo_event_force_http";
  private static final String N = "agoo_event_force_WifiProxy";
  private static volatile boolean O = false;
  private static volatile UT P = null;
  private static final String Q = "utdid_null";
  private static final String R = "utdid_error";
  private static final int a = 273791437;
  private static final int b = 66002;
  private static final String c = "agoo_connect";
  private static final String d = "agoo_connect_timeout";
  private static final String e = "agoo_service";
  private static final String f = "agoo_ervice_sec_error_start";

  @Deprecated
  private static final String g = "agoo_service_error_start";
  private static final String h = "agoo_message";

  @Deprecated
  private static final String i = "agoo_message_http_error";
  private static final String j = "agoo_message_parse_error";
  private static final String k = "agoo_message_decrypted_error";
  private static final String l = "agoo_message_body_null";

  @Deprecated
  private static final String m = "agoo_message_size_large";

  @Deprecated
  private static final String n = "agoo_event_message_notify";
  private static final String o = "agoo_election_times";

  @Deprecated
  private static final String p = "agoo_election";
  private static final String q = "agoo_election_no_result";

  @Deprecated
  private static final String r = "agoo_election_error";

  @Deprecated
  private static final String s = "agoo_election_register";
  private static final String t = "agoo_sysfile_error";
  private static final String u = "agoo_register_error";

  @Deprecated
  private static final String v = "agoo_unregister";
  private static final String w = "agoo_register";

  @Deprecated
  private static final String x = "agoo_app_ping";

  @Deprecated
  private static final String y = "agoo_android_module";
  private static final String z = "agoo_spyd_force_chunked";

  private static String a(bm parambm)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("utdId=" + parambm.a());
    localStringBuffer.append("-->");
    localStringBuffer.append("deviceId=" + parambm.b());
    localStringBuffer.append("-->");
    localStringBuffer.append("appkey=" + parambm.c());
    localStringBuffer.append("-->");
    localStringBuffer.append("startTime=" + parambm.d());
    localStringBuffer.append("-->");
    localStringBuffer.append("ret=" + parambm.e());
    localStringBuffer.append("-->");
    localStringBuffer.append("fail_reasons=" + parambm.f());
    localStringBuffer.append("-->");
    localStringBuffer.append("session_id=" + parambm.g());
    localStringBuffer.append("-->");
    localStringBuffer.append("connection_start_date=" + parambm.h());
    localStringBuffer.append("-->");
    localStringBuffer.append("connection_stop_date=" + parambm.i());
    localStringBuffer.append("-->");
    localStringBuffer.append("close_connection_type=" + parambm.j());
    localStringBuffer.append("-->");
    localStringBuffer.append("close_connection_date=" + parambm.k());
    localStringBuffer.append("-->");
    localStringBuffer.append("connectCount=" + parambm.m());
    localStringBuffer.append("-->");
    localStringBuffer.append("actionType=connect");
    return localStringBuffer.toString();
  }

  private static String a(bn parambn)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("utdId=" + parambn.a());
    localStringBuffer.append("-->");
    localStringBuffer.append("deviceId=" + parambn.b());
    localStringBuffer.append("-->");
    localStringBuffer.append("appkey=" + parambn.c());
    localStringBuffer.append("-->");
    localStringBuffer.append("startTime=" + parambn.d());
    localStringBuffer.append("-->");
    localStringBuffer.append("ret=" + parambn.e());
    localStringBuffer.append("-->");
    localStringBuffer.append("fail_reasons=" + parambn.f());
    localStringBuffer.append("-->");
    localStringBuffer.append("ip=" + parambn.g());
    localStringBuffer.append("-->");
    localStringBuffer.append("port=" + parambn.h());
    localStringBuffer.append("-->");
    localStringBuffer.append("headers=" + parambn.i());
    localStringBuffer.append("-->");
    localStringBuffer.append("statusCode=" + parambn.j());
    localStringBuffer.append("-->");
    localStringBuffer.append("responseBody=" + parambn.k());
    return localStringBuffer.toString();
  }

  private static String a(bo parambo)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("utdId=" + parambo.a());
    localStringBuffer.append("-->");
    localStringBuffer.append("deviceId=" + parambo.b());
    localStringBuffer.append("-->");
    localStringBuffer.append("appkey=" + parambo.c());
    localStringBuffer.append("-->");
    localStringBuffer.append("startTime=" + parambo.d());
    localStringBuffer.append("-->");
    localStringBuffer.append("ret=" + parambo.e());
    localStringBuffer.append("-->");
    localStringBuffer.append("fail_reasons=" + parambo.f());
    localStringBuffer.append("-->");
    localStringBuffer.append("ackTime=" + parambo.g());
    localStringBuffer.append("-->");
    localStringBuffer.append("ackIsSuccess=" + parambo.h());
    localStringBuffer.append("-->");
    localStringBuffer.append("msgIds=" + parambo.j());
    localStringBuffer.append("-->");
    localStringBuffer.append("actionType=get_service_msg");
    return localStringBuffer.toString();
  }

  public static final void a(Context paramContext)
  {
    try
    {
      if (O)
        return;
      P = UTFactroy.getInstance().getLogger(paramContext);
      if (P != null)
      {
        O = true;
        return;
      }
    }
    catch (Throwable paramContext)
    {
    }
  }

  @Deprecated
  public static final void a(Context paramContext, int paramInt, String paramString)
  {
    try
    {
      String str = a.n(paramContext);
      a(paramContext);
      if (P != null)
        P.commitEvent(273791437, "agoo_connect", Integer.valueOf(paramInt), paramString, new String[] { str });
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void a(Context paramContext, long paramLong)
  {
    try
    {
      String str = a.n(paramContext);
      long l1 = System.currentTimeMillis();
      a(paramContext);
      if (P != null)
        P.commitEvent(273791437, "agoo_service", "" + paramLong, "" + (l1 - paramLong), new String[] { str });
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void a(Context paramContext, long paramLong1, long paramLong2)
  {
    try
    {
      String str = a.n(paramContext);
      a(paramContext);
      if (P != null)
        P.commitEvent(273791437, "agoo_connect_timeout", "" + paramLong1, "" + paramLong2, new String[] { str });
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void a(Context paramContext, long paramLong, String paramString)
  {
    a(paramContext, paramLong, paramString, null);
  }

  public static final void a(Context paramContext, long paramLong, String paramString1, String paramString2)
  {
    if (paramLong != -1L);
    while (true)
    {
      try
      {
        l1 = System.currentTimeMillis() - paramLong;
        String str = a.n(paramContext);
        a(paramContext);
        if (P != null)
          P.commitEvent(273791437, "agoo_connect", "" + l1, "" + paramLong, new String[] { str, paramString1, paramString2 });
        return;
      }
      catch (Throwable paramContext)
      {
        return;
      }
      long l1 = 0L;
    }
  }

  public static final void a(Context paramContext, bm parambm)
  {
    if (parambm == null);
    while (true)
    {
      return;
      try
      {
        String str = a.n(paramContext);
        a(paramContext);
        paramContext = a(parambm);
        if (P != null)
        {
          P.commitEvent(66002, "agoo_event_connect_log", str, parambm.e(), new String[] { paramContext });
          return;
        }
      }
      catch (Throwable paramContext)
      {
        Log.d("UTHelper", "doConnectLog error,exception=" + paramContext);
      }
    }
  }

  public static final void a(Context paramContext, bn parambn)
  {
    if (parambn == null);
    while (true)
    {
      return;
      try
      {
        String str = a.n(paramContext);
        a(paramContext);
        paramContext = a(parambn);
        if (P != null)
        {
          P.commitEvent(66002, "agoo_event_dns_log", str, parambn.e(), new String[] { paramContext });
          return;
        }
      }
      catch (Throwable paramContext)
      {
        Log.d("UTHelper", "doDNSLog error,exception=" + paramContext);
      }
    }
  }

  public static final void a(Context paramContext, bo parambo)
  {
    if (parambo == null);
    while (true)
    {
      return;
      try
      {
        String str = a.n(paramContext);
        a(paramContext);
        paramContext = a(parambo);
        if (P != null)
        {
          P.commitEvent(66002, "agoo_event_getServiceMsg_log", str, parambo.e(), new String[] { paramContext });
          return;
        }
      }
      catch (Throwable paramContext)
      {
        Log.d("UTHelper", "doGetServiceMsgLog error,exception=" + paramContext);
      }
    }
  }

  @Deprecated
  public static final void a(Context paramContext, Object paramObject, String[] paramArrayOfString)
  {
    try
    {
      String str = a.n(paramContext);
      a(paramContext);
      if (P != null)
        P.commitEvent(273791437, "agoo_android_module", str, paramObject, paramArrayOfString);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  @Deprecated
  public static final void a(Context paramContext, String paramString)
  {
    try
    {
      String str = a.n(paramContext);
      a(paramContext);
      if (P != null)
        P.commitEvent(273791437, "agoo_app_ping", paramString, str);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  @Deprecated
  public static final void a(Context paramContext, String paramString, long paramLong1, long paramLong2)
  {
    try
    {
      String str = a.n(paramContext);
      a(paramContext);
      if (P != null)
        P.commitEvent(273791437, "agoo_election_register", paramString, "" + paramLong1, new String[] { str, "" + paramLong2 });
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  @Deprecated
  public static final void a(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      String str = a.n(paramContext);
      a(paramContext);
      if (P != null)
        P.commitEvent(273791437, "agoo_event_message_notify", paramString1, paramString2, new String[] { str });
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void a(Context paramContext, Throwable paramThrowable)
  {
    try
    {
      String str = a.n(paramContext);
      a(paramContext);
      if (P != null)
        P.commitEvent(273791437, "agoo_spyd_force_chunked", str, paramThrowable);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void a(Throwable paramThrowable)
  {
    try
    {
      if (P != null)
        P.onCaughException(paramThrowable);
      return;
    }
    catch (Throwable paramThrowable)
    {
    }
  }

  public static final void b(Context paramContext)
  {
    try
    {
      if (!O)
        return;
      O = false;
      if (P != null)
      {
        P.stop(paramContext);
        return;
      }
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void b(Context paramContext, String paramString)
  {
    try
    {
      String str = a.n(paramContext);
      a(paramContext);
      if (P != null)
        P.commitEvent(273791437, "agoo_message", paramString, str);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  @Deprecated
  public static final void b(Context paramContext, String paramString, long paramLong1, long paramLong2)
  {
    try
    {
      String str = a.n(paramContext);
      a(paramContext);
      if (P != null)
        P.commitEvent(273791437, "agoo_election", paramString, "" + paramLong1, new String[] { str, "" + paramLong2 });
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void b(Context paramContext, String paramString1, String paramString2)
  {
    String str = a.n(paramContext);
    a(paramContext);
    if (P != null)
      P.commitEvent(273791437, "agoo_message_decrypted_error", paramString1, paramString2, new String[] { str });
  }

  public static final String c(Context paramContext)
  {
    Object localObject2 = "utdid_error";
    Object localObject1 = localObject2;
    try
    {
      a(paramContext);
      localObject1 = localObject2;
      paramContext = UTDevice.getUtdid(paramContext);
      localObject2 = paramContext;
      localObject1 = paramContext;
      if (TextUtils.isEmpty(paramContext))
        localObject2 = "utdid_null";
      return localObject2;
    }
    catch (Throwable paramContext)
    {
    }
    return localObject1;
  }

  @Deprecated
  public static final void c(Context paramContext, String paramString)
  {
    try
    {
      String str = a.n(paramContext);
      a(paramContext);
      if (P != null)
        P.commitEvent(273791437, "agoo_message_http_error", paramString, str);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void c(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      String str1 = a.n(paramContext);
      String str2 = paramContext.getPackageName();
      a(paramContext);
      if (P != null)
        P.commitEvent(273791437, "agoo_start_method", str1, paramString1, new String[] { paramString2, str2 });
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void d(Context paramContext)
  {
    try
    {
      String str = a.n(paramContext);
      a(paramContext);
      if (P != null)
        P.commitEvent(273791437, "agoo_ervice_sec_error_start", str);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void d(Context paramContext, String paramString)
  {
    try
    {
      String str = a.n(paramContext);
      a(paramContext);
      if (P != null)
        P.commitEvent(273791437, "agoo_message_parse_error", paramString, str);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void d(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      String str = a.n(paramContext);
      a(paramContext);
      if (P != null)
        P.commitEvent(66002, "agoo_event_register_log", str, paramString1, new String[] { paramString2 });
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  @Deprecated
  public static final void e(Context paramContext)
  {
    try
    {
      String str = a.n(paramContext);
      a(paramContext);
      if (P != null)
        P.commitEvent(273791437, "agoo_service_error_start", str);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void e(Context paramContext, String paramString)
  {
    try
    {
      String str = a.n(paramContext);
      a(paramContext);
      if (P != null)
        P.commitEvent(273791437, "agoo_message_body_null", paramString, str);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void e(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      String str = a.n(paramContext);
      a(paramContext);
      if (P != null)
        P.commitEvent(66002, "agoo_event_election_log", str, paramString1, new String[] { paramString2 });
      return;
    }
    catch (Throwable paramContext)
    {
      Log.d("UTHelper", "doElectionLog error,exception=" + paramContext);
    }
  }

  @Deprecated
  public static final void f(Context paramContext)
  {
    try
    {
      String str = a.n(paramContext);
      a(paramContext);
      if (P != null)
        P.commitEvent(273791437, "agoo_message_size_large", "5", str);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  @Deprecated
  public static final void f(Context paramContext, String paramString)
  {
    try
    {
      paramString = a.n(paramContext);
      a(paramContext);
      if (P != null)
        P.commitEvent(273791437, "agoo_election_error", paramString);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void g(Context paramContext)
  {
    try
    {
      paramContext = a.n(paramContext);
      if (P != null)
        P.commitEvent(273791437, "agoo_election_times", paramContext);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void g(Context paramContext, String paramString)
  {
    try
    {
      String str = a.n(paramContext);
      a(paramContext);
      if (P != null)
        P.commitEvent(273791437, "agoo_sysfile_error", paramString, str);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void h(Context paramContext)
  {
    try
    {
      paramContext = a.n(paramContext);
      if (P != null)
        P.commitEvent(273791437, "agoo_election_no_result", paramContext);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void h(Context paramContext, String paramString)
  {
    try
    {
      String str = a.n(paramContext);
      a(paramContext);
      if (P != null)
        P.commitEvent(273791437, "agoo_register_error", paramString, str);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void i(Context paramContext)
  {
    try
    {
      String str = a.n(paramContext);
      a(paramContext);
      if (P != null)
        P.commitEvent(273791437, "agoo_register", str);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void i(Context paramContext, String paramString)
  {
    try
    {
      String str = a.n(paramContext);
      a(paramContext);
      if (P != null)
        P.commitEvent(273791437, paramString, str);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  @Deprecated
  public static final void j(Context paramContext)
  {
    try
    {
      String str = a.n(paramContext);
      a(paramContext);
      if (P != null)
        P.commitEvent(273791437, "agoo_unregister", str);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void j(Context paramContext, String paramString)
  {
    if (paramString == null);
    while (true)
    {
      return;
      try
      {
        String str = a.n(paramContext);
        a(paramContext);
        if (P != null)
        {
          P.commitEvent(66002, "agoo_event_election_result_log", str, null, new String[] { paramString });
          return;
        }
      }
      catch (Throwable paramContext)
      {
        Log.d("UTHelper", "doConnectLog error,exception=" + paramContext);
      }
    }
  }

  public static final void k(Context paramContext)
  {
    try
    {
      String str1 = a.n(paramContext);
      String str2 = paramContext.getPackageName();
      a(paramContext);
      if (P != null)
        P.commitEvent(273791437, "agoo_checkpackage_stop", str1, str2);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void k(Context paramContext, String paramString)
  {
    if (paramString == null);
    while (true)
    {
      return;
      try
      {
        String str = a.n(paramContext);
        a(paramContext);
        if (P != null)
        {
          P.commitEvent(66002, "agoo_event_register_begin_log", str, null, new String[] { paramString });
          return;
        }
      }
      catch (Throwable paramContext)
      {
        Log.d("UTHelper", "doConnectLog error,exception=" + paramContext);
      }
    }
  }

  public static final void l(Context paramContext)
  {
    try
    {
      String str1 = a.n(paramContext);
      Object localObject = paramContext.getSharedPreferences("AGOO_CONNECT", 4);
      String str2 = ((SharedPreferences)localObject).getString("AGOO_CONNECT_HOST", null);
      int i1 = ((SharedPreferences)localObject).getInt("AGOO_CONNECT_PORT", -1);
      localObject = paramContext.getSharedPreferences("AppStore", 4).getString("agoo_connect_type", null);
      String str3 = new bl(paramContext).c();
      a(paramContext);
      if (P != null)
        P.commitEvent(273791437, "agoo_pushService_connect_success", str1, localObject, new String[] { "ip=" + str2 + "&port=" + Integer.toString(i1) + "&netType" + str3 + "&sdkVersion=" + a.a() });
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void m(Context paramContext)
  {
    try
    {
      String str1 = a.n(paramContext);
      Object localObject = paramContext.getSharedPreferences("AGOO_CONNECT", 4);
      String str2 = ((SharedPreferences)localObject).getString("AGOO_CONNECT_HOST", null);
      int i1 = ((SharedPreferences)localObject).getInt("AGOO_CONNECT_PORT", -1);
      localObject = paramContext.getSharedPreferences("AppStore", 4).getString("agoo_connect_type", null);
      String str3 = new bl(paramContext).c();
      LinkedHashMap localLinkedHashMap = Config.getPushServiceErrorId(paramContext);
      a(paramContext);
      if ((localLinkedHashMap != null) && (localLinkedHashMap.size() > 0) && (P != null))
        P.commitEvent(273791437, "agoo_pushservice_connect_error", str1, localObject, new String[] { "ip=" + str2 + "&port=" + Integer.toString(i1) + "&netType=" + str3 + "&sdkVersion=" + a.a() + "&errorId=" + (String)localLinkedHashMap.get("errorId") + "&url=" + (String)localLinkedHashMap.get("url") });
      paramContext = Config.getDnsErrorId(paramContext);
      if ((paramContext != null) && (paramContext.size() > 0) && (P != null))
        P.commitEvent(273791437, "agoo_pushservice_connect_error", str1, localObject, new String[] { "ip=" + str2 + "&port=" + Integer.toString(i1) + "&netType=" + str3 + "&sdkVersion=" + a.a() + "&dnsErrorId=" + (String)paramContext.get("dnsErrorId") + "&dnsUrl=" + (String)paramContext.get("dnsUrl") });
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void n(Context paramContext)
  {
    try
    {
      a(paramContext);
      if (P != null)
        P.commitEvent(273791437, "agoo_somanager_start", "");
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final void o(Context paramContext)
  {
    try
    {
      String str1 = a.n(paramContext);
      a(paramContext);
      paramContext = new bl(paramContext);
      String str2 = paramContext.c();
      if (P != null)
        P.commitEvent(66002, "agoo_event_force_http", str1, "netType=" + str2 + "&isWapNetwork=" + paramContext.a() + "&sdkVersion=" + a.a());
      return;
    }
    catch (Throwable paramContext)
    {
      Log.d("UTHelper", "doElectionLog error,exception=" + paramContext);
    }
  }

  public static final void p(Context paramContext)
  {
    try
    {
      String str1 = a.n(paramContext);
      a(paramContext);
      paramContext = new bl(paramContext);
      String str2 = paramContext.c();
      if (P != null)
        P.commitEvent(66002, "agoo_event_force_WifiProxy", str1, "netType=" + str2 + "&isWapNetwork=" + paramContext.a() + "&sdkVersion=" + a.a());
      return;
    }
    catch (Throwable paramContext)
    {
      Log.d("UTHelper", "doElectionLog error,exception=" + paramContext);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.aW
 * JD-Core Version:    0.6.2
 */