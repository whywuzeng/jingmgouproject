package com.umeng.message.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class bM
{
  private static final String a = "s_update";
  private static final String b = "c_version";
  private static final String c = "b_version";
  private static final String d = "t_version";
  private static final String e = "auto_update_success";
  private static final String f = "download_file_path";
  private static final String g = "2.0";
  private static final String h = "auto_update_exception";
  private static final String i = "auto_update_netstatus";

  public static final String a()
  {
    return "2.0";
  }

  public static String a(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("s_update", 4).getString(paramString + "c_version", "0");
  }

  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    paramContext.getSharedPreferences("s_update", 4).edit().putString(paramString1 + "c_version", paramString2).commit();
  }

  public static String b(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("s_update", 4).getString(paramString + "b_version", "0");
  }

  public static void b(Context paramContext, String paramString1, String paramString2)
  {
    paramContext.getSharedPreferences("s_update", 4).edit().putString(paramString1 + "b_version", paramString2).commit();
  }

  public static String c(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("s_update", 4).getString(paramString + "t_version", "0");
  }

  public static void c(Context paramContext, String paramString1, String paramString2)
  {
    paramContext.getSharedPreferences("s_update", 4).edit().putString(paramString1 + "t_version", paramString2).commit();
  }

  public static String d(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("s_update", 4).getString(paramString + "auto_update_success", "-1");
  }

  public static void d(Context paramContext, String paramString1, String paramString2)
  {
    paramContext.getSharedPreferences("s_update", 4).edit().putString(paramString2 + "auto_update_success", paramString1).commit();
  }

  public static String e(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("s_update", 4).getString(paramString + "auto_update_exception", "");
  }

  public static void e(Context paramContext, String paramString1, String paramString2)
  {
    paramContext.getSharedPreferences("s_update", 4).edit().putString(paramString2 + "auto_update_exception", paramString1).commit();
  }

  public static String f(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("s_update", 4).getString(paramString + "auto_update_netstatus", "");
  }

  public static void f(Context paramContext, String paramString1, String paramString2)
  {
    paramContext.getSharedPreferences("s_update", 4).edit().putString(paramString2 + "auto_update_netstatus", paramString1).commit();
  }

  public static String g(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("s_update", 4).getString(paramString + "download_file_path", "");
  }

  public static void g(Context paramContext, String paramString1, String paramString2)
  {
    paramContext.getSharedPreferences("s_update", 4).edit().putString(paramString1 + "download_file_path", paramString2).commit();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.bM
 * JD-Core Version:    0.6.2
 */