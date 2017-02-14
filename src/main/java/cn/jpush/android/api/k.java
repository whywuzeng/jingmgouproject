package cn.jpush.android.api;

import android.app.Application;
import java.util.HashMap;

public final class k
{
  public static boolean a;
  private static boolean b;
  private static String c = new String(localObject2).intern();
  private static HashMap<String, Integer> d = new HashMap();
  private static String e = null;
  private static String f = null;
  private static String g = null;

  static
  {
    int m = 0;
    int i = 0;
    b = true;
    a = false;
    Object localObject1 = "诲坩悫殥专D\"w\003Ol5z目Vk\023f\031Lh$+C咵j/S\013Lv$+C皽v4s\017K-h名豩甑盽儲络诋斀泐｛I:Lv)J\004M`3e\013Z`ol\004k`2v\007\\-h#哦\031O\021v\031QL/w\017Kc `\017\027j/S\013Lv$+C".toCharArray();
    int j = localObject1.length;
    Object localObject2 = localObject1;
    int k = j;
    if (j <= 1);
    label127: 
    do
    {
      m = i;
      k = i;
      localObject2 = localObject1;
      int n = localObject2[k];
      switch (m % 5)
      {
      default:
        i = 57;
      case 0:
      case 1:
      case 2:
      case 3:
      }
      while (true)
      {
        localObject2[k] = ((char)(i ^ n));
        m += 1;
        if (j != 0)
          break label127;
        k = j;
        break;
        i = 5;
        continue;
        i = 65;
        continue;
        i = 3;
        continue;
        i = 106;
      }
      k = j;
      localObject2 = localObject1;
      localObject1 = localObject2;
      j = k;
      i = m;
    }
    while (k > m);
  }

  public static void a(Application paramApplication)
  {
    l locall = new l();
    paramApplication.unregisterActivityLifecycleCallbacks(locall);
    paramApplication.registerActivityLifecycleCallbacks(locall);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.api.k
 * JD-Core Version:    0.6.2
 */