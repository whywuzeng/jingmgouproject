package cn.jpush.android.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class f
{
  private static SharedPreferences a = null;
  private static SharedPreferences.Editor b = null;

  public static void a(Context paramContext)
  {
    if (a == null)
      a = PreferenceManager.getDefaultSharedPreferences(paramContext);
  }

  protected static void a(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = a.edit();
    b = localEditor;
    localEditor.putString(paramString1, paramString2);
    b.commit();
  }

  protected static String b(String paramString1, String paramString2)
  {
    return a.getString(paramString1, paramString2);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.util.f
 * JD-Core Version:    0.6.2
 */