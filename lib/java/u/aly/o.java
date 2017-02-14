package u.aly;

import android.content.Context;
import com.umeng.analytics.AnalyticsConfig;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class o
{
  private final int a = 128;
  private final int b = 256;
  private m c;
  private Context d;
  private l e;

  public o(Context paramContext)
  {
    if (paramContext == null)
      throw new RuntimeException("Context is null, can't track event");
    this.d = paramContext.getApplicationContext();
    this.c = new m(this.d);
    paramContext = this.c;
    if (!AnalyticsConfig.ENABLE_MEMORY_BUFFER);
    for (boolean bool = true; ; bool = false)
    {
      paramContext.a(bool);
      this.e = l.a(this.d);
      return;
    }
  }

  private boolean a(String paramString)
  {
    if (paramString != null)
    {
      int i = paramString.trim().getBytes().length;
      if ((i > 0) && (i <= 128))
        return true;
    }
    br.b("MobclickAgent", "Event id is empty or too long in tracking Event");
    return false;
  }

  private boolean a(Map<String, Object> paramMap)
  {
    if ((paramMap == null) || (paramMap.isEmpty()))
    {
      br.b("MobclickAgent", "map is null or empty in onEvent");
      return false;
    }
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      if (!a((String)localEntry.getKey()))
        return false;
      if (localEntry.getValue() == null)
        return false;
      if (((localEntry.getValue() instanceof String)) && (!b(localEntry.getValue().toString())))
        return false;
    }
    return true;
  }

  private boolean b(String paramString)
  {
    if (paramString == null);
    while (paramString.trim().getBytes().length <= 256)
      return true;
    br.b("MobclickAgent", "Event label or value is empty or too long in tracking Event");
    return false;
  }

  public void a(String paramString1, String paramString2)
  {
    if ((!a(paramString1)) || (!b(paramString2)))
      return;
    this.c.a(af.b(paramString1, paramString2, null), af.a(paramString1, paramString2, null));
  }

  public void a(String paramString1, String paramString2, long paramLong, int paramInt)
  {
    if ((!a(paramString1)) || (!b(paramString2)))
      return;
    HashMap localHashMap = new HashMap();
    String str = paramString2;
    if (paramString2 == null)
      str = "";
    localHashMap.put(paramString1, str);
    this.e.a(new af(paramString1, localHashMap, paramLong, paramInt));
  }

  public void a(String paramString, Map<String, Object> paramMap)
  {
    try
    {
      if (!a(paramString))
        return;
      this.e.a(new ah(paramString, paramMap));
      return;
    }
    catch (Exception paramString)
    {
      br.b("MobclickAgent", "Exception occurred in Mobclick.onEvent(). ", paramString);
    }
  }

  public void a(String paramString, Map<String, Object> paramMap, long paramLong)
  {
    try
    {
      if (!a(paramString))
        return;
      if (a(paramMap))
      {
        this.e.a(new af(paramString, paramMap, paramLong, -1));
        return;
      }
    }
    catch (Exception paramString)
    {
      br.b("MobclickAgent", "Exception occurred in Mobclick.onEvent(). ", paramString);
    }
  }

  public void a(String paramString1, Map<String, Object> paramMap, String paramString2)
  {
    if (!a(paramString1));
    while (!a(paramMap))
      return;
    this.c.a(af.b(paramString1, paramString2, paramMap), af.a(paramString1, paramString2, paramMap));
  }

  public void b(String paramString1, String paramString2)
  {
    if ((!a(paramString1)) || (!b(paramString2)));
    ae localae;
    do
    {
      return;
      localae = this.c.b(af.b(paramString1, paramString2, null));
    }
    while (localae == null);
    a(paramString1, paramString2, (int)(System.currentTimeMillis() - localae.a), 0);
  }

  public void c(String paramString1, String paramString2)
  {
    if (!a(paramString1));
    do
    {
      return;
      paramString2 = this.c.b(af.b(paramString1, paramString2, null));
    }
    while (paramString2 == null);
    int i = (int)(System.currentTimeMillis() - paramString2.a);
    a(paramString1, paramString2.d, i);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.o
 * JD-Core Version:    0.6.2
 */