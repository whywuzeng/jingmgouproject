package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ad
{
  private static final String a = "activities";
  private final Map<String, Long> b = new HashMap();
  private final ArrayList<ab> c = new ArrayList();

  public static List<bg> a(SharedPreferences paramSharedPreferences)
  {
    Object localObject = paramSharedPreferences.getString("activities", "");
    if ("".equals(localObject))
      return null;
    paramSharedPreferences = new ArrayList();
    while (true)
    {
      int i;
      try
      {
        localObject = ((String)localObject).split(";");
        i = 0;
        if (i < localObject.length)
        {
          CharSequence localCharSequence = localObject[i];
          if (TextUtils.isEmpty(localCharSequence))
            break label89;
          paramSharedPreferences.add(new ai(localCharSequence));
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      if (paramSharedPreferences.size() <= 0)
        break;
      return paramSharedPreferences;
      label89: i += 1;
    }
  }

  public void a()
  {
    String str = null;
    long l = 0L;
    while (true)
      synchronized (this.b)
      {
        Iterator localIterator = this.b.entrySet().iterator();
        if (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          if (((Long)localEntry.getValue()).longValue() > l)
          {
            l = ((Long)localEntry.getValue()).longValue();
            str = (String)localEntry.getKey();
          }
        }
        else
        {
          if (str != null)
            b(str);
          return;
        }
      }
  }

  public void a(Context paramContext)
  {
    Object localObject1 = x.a(paramContext);
    paramContext = ((SharedPreferences)localObject1).edit();
    if (this.c.size() > 0)
    {
      ??? = ((SharedPreferences)localObject1).getString("activities", "");
      localObject1 = new StringBuilder();
      if (!TextUtils.isEmpty((CharSequence)???))
      {
        ((StringBuilder)localObject1).append((String)???);
        ((StringBuilder)localObject1).append(";");
      }
      synchronized (this.c)
      {
        Iterator localIterator = this.c.iterator();
        if (localIterator.hasNext())
        {
          ab localab = (ab)localIterator.next();
          ((StringBuilder)localObject1).append(String.format("[\"%s\",%d]", new Object[] { localab.a, Long.valueOf(localab.b) }));
          ((StringBuilder)localObject1).append(";");
        }
      }
      this.c.clear();
      ((StringBuilder)localObject1).deleteCharAt(((StringBuilder)localObject1).length() - 1);
      paramContext.remove("activities");
      paramContext.putString("activities", ((StringBuilder)localObject1).toString());
    }
    paramContext.commit();
  }

  public void a(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
      synchronized (this.b)
      {
        this.b.put(paramString, Long.valueOf(System.currentTimeMillis()));
        return;
      }
  }

  public void b(String paramString)
  {
    if (!TextUtils.isEmpty(paramString));
    Long localLong;
    synchronized (this.b)
    {
      localLong = (Long)this.b.remove(paramString);
      if (localLong == null)
      {
        br.e("MobclickAgent", String.format("please call 'onPageStart(%s)' before onPageEnd", new Object[] { paramString }));
        return;
      }
    }
    long l1 = System.currentTimeMillis();
    long l2 = localLong.longValue();
    synchronized (this.c)
    {
      this.c.add(new ab(paramString, l1 - l2));
      return;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.ad
 * JD-Core Version:    0.6.2
 */