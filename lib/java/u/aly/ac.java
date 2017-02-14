package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import java.lang.reflect.Method;

public class ac
{
  private static final String a = "uptr";
  private static final String b = "dntr";

  public static bm a(Context paramContext)
  {
    long[] arrayOfLong;
    label151: label153: 
    do
    {
      long l1;
      long l2;
      try
      {
        bm localbm = new bm();
        arrayOfLong = b(paramContext);
        if ((arrayOfLong[0] > 0L) && (arrayOfLong[1] > 0L))
        {
          paramContext = x.a(paramContext);
          l1 = paramContext.getLong("uptr", -1L);
          l2 = paramContext.getLong("dntr", -1L);
          paramContext.edit().putLong("uptr", arrayOfLong[1]).putLong("dntr", arrayOfLong[0]).commit();
          if (l1 <= 0L)
            break label151;
          if (l2 > 0L)
            break label153;
          break label151;
          localbm.c((int)arrayOfLong[0]);
          localbm.a((int)arrayOfLong[1]);
          return localbm;
        }
      }
      catch (Exception paramContext)
      {
        br.e("MobclickAgent", "sdk less than 2.2 has get no traffic");
        return null;
      }
      return null;
      return null;
      arrayOfLong[0] -= l2;
      arrayOfLong[1] -= l1;
    }
    while ((arrayOfLong[0] > 0L) && (arrayOfLong[1] > 0L));
    return null;
  }

  private static long[] b(Context paramContext)
    throws Exception
  {
    Object localObject = Class.forName("android.net.TrafficStats");
    Method localMethod = ((Class)localObject).getMethod("getUidRxBytes", new Class[] { Integer.TYPE });
    localObject = ((Class)localObject).getMethod("getUidTxBytes", new Class[] { Integer.TYPE });
    int i = paramContext.getApplicationInfo().uid;
    if (i == -1)
      return null;
    return new long[] { ((Long)localMethod.invoke(null, new Object[] { Integer.valueOf(i) })).longValue(), ((Long)((Method)localObject).invoke(null, new Object[] { Integer.valueOf(i) })).longValue() };
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.ac
 * JD-Core Version:    0.6.2
 */