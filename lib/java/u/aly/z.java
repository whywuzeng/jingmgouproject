package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.umeng.analytics.AnalyticsConfig;
import java.util.Arrays;
import java.util.List;

public class z
{
  private static final String a = "session_start_time";
  private static final String b = "session_end_time";
  private static final String c = "session_id";
  private static final String f = "activities";
  private static String g = null;
  private final String d = "a_start_time";
  private final String e = "a_end_time";

  private String a(Context paramContext, SharedPreferences paramSharedPreferences)
  {
    l locall = l.a(paramContext);
    String str = b(paramContext);
    paramContext = a(paramContext);
    paramSharedPreferences = paramSharedPreferences.edit();
    paramSharedPreferences.putString("session_id", str);
    paramSharedPreferences.putLong("session_start_time", System.currentTimeMillis());
    paramSharedPreferences.putLong("session_end_time", 0L);
    paramSharedPreferences.putLong("a_start_time", System.currentTimeMillis());
    paramSharedPreferences.putLong("a_end_time", 0L);
    paramSharedPreferences.commit();
    if (paramContext != null)
    {
      locall.a(paramContext);
      return str;
    }
    locall.a((aj)null);
    return str;
  }

  private void a(SharedPreferences paramSharedPreferences)
  {
    paramSharedPreferences = paramSharedPreferences.edit();
    paramSharedPreferences.remove("session_start_time");
    paramSharedPreferences.remove("session_end_time");
    paramSharedPreferences.remove("a_start_time");
    paramSharedPreferences.remove("a_end_time");
    paramSharedPreferences.putString("activities", "");
    paramSharedPreferences.commit();
  }

  private boolean b(SharedPreferences paramSharedPreferences)
  {
    long l1 = paramSharedPreferences.getLong("a_start_time", 0L);
    long l2 = paramSharedPreferences.getLong("a_end_time", 0L);
    long l3 = System.currentTimeMillis();
    if ((l1 != 0L) && (l3 - l1 < AnalyticsConfig.kContinueSessionMillis))
      br.b("MobclickAgent", "onResume called before onPause");
    while (l3 - l2 <= AnalyticsConfig.kContinueSessionMillis)
      return false;
    return true;
  }

  public static String g(Context paramContext)
  {
    if (g == null)
      g = x.a(paramContext).getString("session_id", null);
    return g;
  }

  public aj a(Context paramContext)
  {
    SharedPreferences localSharedPreferences = x.a(paramContext);
    Object localObject = localSharedPreferences.getString("session_id", null);
    if (localObject == null)
      return null;
    long l3 = localSharedPreferences.getLong("session_start_time", 0L);
    long l4 = localSharedPreferences.getLong("session_end_time", 0L);
    long l1 = 0L;
    if (l4 != 0L)
    {
      long l2 = l4 - l3;
      l1 = l2;
      if (Math.abs(l2) > 86400000L)
        l1 = 0L;
    }
    aj localaj = new aj();
    localaj.a((String)localObject);
    localaj.a(l3);
    localaj.b(l4);
    localaj.c(l1);
    localObject = AnalyticsConfig.getLocation();
    if (localObject != null)
    {
      localObject = new be(localObject[0], localObject[1], System.currentTimeMillis());
      if (!localaj.y())
        break label218;
      localaj.a((be)localObject);
    }
    while (true)
    {
      paramContext = ac.a(paramContext);
      if (paramContext != null)
        localaj.a(paramContext);
      paramContext = ad.a(localSharedPreferences);
      if ((paramContext != null) && (paramContext.size() > 0))
        localaj.a(paramContext);
      a(localSharedPreferences);
      return localaj;
      label218: localaj.b(Arrays.asList(new be[] { localObject }));
    }
  }

  public String b(Context paramContext)
  {
    String str = bq.f(paramContext);
    paramContext = AnalyticsConfig.getAppkey(paramContext);
    long l = System.currentTimeMillis();
    if (paramContext == null)
      throw new RuntimeException("Appkey is null or empty, Please check AndroidManifest.xml");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(l).append(paramContext).append(str);
    g = cd.a(localStringBuilder.toString());
    return g;
  }

  public void c(Context paramContext)
  {
    Object localObject = x.a(paramContext);
    if (localObject == null)
      return;
    if (b((SharedPreferences)localObject))
    {
      paramContext = a(paramContext, (SharedPreferences)localObject);
      br.a("MobclickAgent", "Start new session: " + paramContext);
      return;
    }
    paramContext = ((SharedPreferences)localObject).getString("session_id", null);
    localObject = ((SharedPreferences)localObject).edit();
    ((SharedPreferences.Editor)localObject).putLong("a_start_time", System.currentTimeMillis());
    ((SharedPreferences.Editor)localObject).putLong("a_end_time", 0L);
    ((SharedPreferences.Editor)localObject).commit();
    br.a("MobclickAgent", "Extend current session: " + paramContext);
  }

  public void d(Context paramContext)
  {
    paramContext = x.a(paramContext);
    if (paramContext == null)
      return;
    if ((paramContext.getLong("a_start_time", 0L) == 0L) && (AnalyticsConfig.ACTIVITY_DURATION_OPEN))
    {
      br.b("MobclickAgent", "onPause called before onResume");
      return;
    }
    long l = System.currentTimeMillis();
    paramContext = paramContext.edit();
    paramContext.putLong("a_start_time", 0L);
    paramContext.putLong("a_end_time", l);
    paramContext.putLong("session_end_time", l);
    paramContext.commit();
  }

  public boolean e(Context paramContext)
  {
    boolean bool3 = false;
    boolean bool2 = false;
    Object localObject = x.a(paramContext);
    if (localObject == null);
    boolean bool1;
    do
    {
      do
        return bool2;
      while (((SharedPreferences)localObject).getString("session_id", null) == null);
      long l1 = ((SharedPreferences)localObject).getLong("a_start_time", 0L);
      long l2 = ((SharedPreferences)localObject).getLong("a_end_time", 0L);
      bool1 = bool3;
      if (l1 > 0L)
      {
        bool1 = bool3;
        if (l2 == 0L)
        {
          bool1 = true;
          d(paramContext);
        }
      }
      localObject = l.a(paramContext);
      paramContext = a(paramContext);
      bool2 = bool1;
    }
    while (paramContext == null);
    ((l)localObject).b(paramContext);
    return bool1;
  }

  public void f(Context paramContext)
  {
    Object localObject = x.a(paramContext);
    if (localObject == null)
      return;
    paramContext = b(paramContext);
    localObject = ((SharedPreferences)localObject).edit();
    ((SharedPreferences.Editor)localObject).putString("session_id", paramContext);
    ((SharedPreferences.Editor)localObject).putLong("session_start_time", System.currentTimeMillis());
    ((SharedPreferences.Editor)localObject).putLong("session_end_time", 0L);
    ((SharedPreferences.Editor)localObject).putLong("a_start_time", System.currentTimeMillis());
    ((SharedPreferences.Editor)localObject).putLong("a_end_time", 0L);
    ((SharedPreferences.Editor)localObject).commit();
    br.a("MobclickAgent", "Restart session: " + paramContext);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.z
 * JD-Core Version:    0.6.2
 */