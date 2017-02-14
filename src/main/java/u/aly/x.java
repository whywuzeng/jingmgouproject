package u.aly;

import android.content.Context;
import android.content.SharedPreferences;

public class x
{
  private static final String a = "umeng_general_config";

  public static SharedPreferences a(Context paramContext)
  {
    return paramContext.getSharedPreferences("umeng_general_config", 0);
  }

  public static SharedPreferences a(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences(paramString, 0);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.x
 * JD-Core Version:    0.6.2
 */