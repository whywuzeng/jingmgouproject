package com.alimama.mobile.csdk.umupdate.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.alimama.mobile.a;
import com.alimama.mobile.csdk.umupdate.a.g;
import org.json.JSONObject;

public class c
{
  public static final int a = 0;
  public static final int b = 1;
  private static final String j = "EXCHANGE_PRELOAD_ADS";
  protected String c;
  protected String d = "";
  protected String e = "";
  private final String f = "PROMOTERS_FIRST_PAGE_";
  private final String g = "PROMOTERS_NEXT_PAGE_";
  private String h = "";
  private String i = "";

  public JSONObject a(boolean paramBoolean1, long paramLong, boolean paramBoolean2)
  {
    int k = 0;
    try
    {
      SharedPreferences localSharedPreferences = a.a().c().getSharedPreferences(this.c, 0);
      String str1;
      if (paramBoolean1)
      {
        g.b("Request data from first-cache..", new Object[0]);
        long l = localSharedPreferences.getLong(this.i, 0L);
        if ((System.currentTimeMillis() - Long.valueOf(l).longValue()) / 1000L > 86400L * paramLong - 3600L)
          k = 1;
        if (k != 0)
        {
          a();
          g.e("Cache data is inactivation...", new Object[0]);
          return null;
        }
        str1 = this.h;
      }
      while (true)
      {
        String str2 = localSharedPreferences.getString(str1, null);
        if (str2 != null)
        {
          if (paramBoolean2);
          try
          {
            SharedPreferences.Editor localEditor = localSharedPreferences.edit();
            localEditor.remove(str1);
            localEditor.commit();
            g.c("destroy the used cache data.", new Object[0]);
            return new JSONObject(str2);
            g.b("Request data from second-cache..", new Object[0]);
            str1 = this.d;
          }
          finally
          {
          }
        }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public void a()
  {
    g.c("remove cache....[" + this.c + "]", new Object[0]);
    synchronized (a.a().c().getSharedPreferences(this.c, 0))
    {
      SharedPreferences.Editor localEditor = ???.edit();
      localEditor.remove(this.h);
      localEditor.remove(this.d);
      localEditor.remove(this.i);
      localEditor.commit();
      return;
    }
  }

  public void a(int paramInt)
  {
    SharedPreferences.Editor localEditor = a.a().c().getSharedPreferences(this.c, 0).edit();
    localEditor.putInt(this.e, paramInt);
    localEditor.commit();
    g.b("Save the " + this.e + "   " + paramInt, new Object[0]);
  }

  protected void a(MMEntity paramMMEntity)
  {
    this.c = ("EXCHANGE_PRELOAD_ADS_" + paramMMEntity.keywords + "_" + paramMMEntity.autofill);
    if ((TextUtils.isEmpty(this.e)) || (TextUtils.isEmpty(this.h)) || (TextUtils.isEmpty(this.d)) || (TextUtils.isEmpty(this.i)))
    {
      if (!TextUtils.isEmpty(paramMMEntity.slotId))
        break label203;
      paramMMEntity = paramMMEntity.appkey;
      if (TextUtils.isEmpty(paramMMEntity))
        g.d("No found Slot_id or Appkey!!!!!", new Object[0]);
    }
    else
    {
      return;
    }
    this.e = ("PRELOAD_KEY_" + paramMMEntity);
    this.h = ("PROMOTERS_FIRST_PAGE_" + paramMMEntity);
    this.d = ("PROMOTERS_NEXT_PAGE_" + paramMMEntity);
    this.i = ("PRELOAD_UPDATE_DATE_" + paramMMEntity);
    return;
    label203: paramMMEntity = paramMMEntity.slotId;
    this.e = ("PRELOAD_KEY_" + paramMMEntity);
    this.h = ("PROMOTERS_FIRST_PAGE_" + paramMMEntity);
    this.d = ("PROMOTERS_NEXT_PAGE_" + paramMMEntity);
    this.i = ("PRELOAD_UPDATE_DATE_" + paramMMEntity);
  }

  public boolean a(boolean paramBoolean, JSONObject paramJSONObject)
  {
    SharedPreferences localSharedPreferences = a.a().c().getSharedPreferences(this.c, 0);
    if (paramJSONObject != null)
    {
      g.c("save json to cache....", new Object[0]);
      try
      {
        SharedPreferences.Editor localEditor = localSharedPreferences.edit();
        localEditor.putLong(this.i, System.currentTimeMillis());
        if (paramBoolean)
          localEditor.putString(this.h, paramJSONObject.toString());
        while (true)
        {
          localEditor.commit();
          return true;
          localEditor.putString(this.d, paramJSONObject.toString());
        }
      }
      finally
      {
      }
    }
    return false;
  }

  public int b()
  {
    return a.a().c().getSharedPreferences(this.c, 0).getInt(this.e, 0);
  }

  public String c()
  {
    return this.c;
  }

  public String d()
  {
    return this.e;
  }

  public String e()
  {
    return this.d;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alimama.mobile.csdk.umupdate.models.c
 * JD-Core Version:    0.6.2
 */