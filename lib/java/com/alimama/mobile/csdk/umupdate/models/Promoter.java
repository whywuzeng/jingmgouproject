package com.alimama.mobile.csdk.umupdate.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Log;
import com.alimama.mobile.csdk.umupdate.a.g;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONObject;

public class Promoter
  implements Parcelable
{
  public static final Parcelable.Creator<Promoter> CREATOR = new Parcelable.Creator()
  {
    public Promoter a(Parcel paramAnonymousParcel)
    {
      return new Promoter(paramAnonymousParcel);
    }

    public Promoter[] a(int paramAnonymousInt)
    {
      return new Promoter[paramAnonymousInt];
    }
  };
  public static final int LANDING_TYPE_ALIP4P = 5;
  public static final int LANDING_TYPE_BROWSER = 3;
  public static final int LANDING_TYPE_DIRECT_DOWNLOAD = 1;
  public static final int LANDING_TYPE_POPUP = 0;
  public static final int LANDING_TYPE_UMENGAPP = 6;
  public static final int LANDING_TYPE_WAP_WEBVIEW = 4;
  public static final int LANDING_TYPE_WEBVIEW = 2;
  public static final int REPORT_CLICK_TO_LAUNCH = 4;
  public static final int REPORT_CLICK_TO_LAUNCH_DETAIL_PAGE = 5;
  public static final int REPORT_CLICK_TO_PROMOTE = 2;
  public static final int REPORT_DOWNLOAD = 1;
  public static final int REPORT_DOWNLOAD_CLICK = 3;
  public static final int REPORT_DOWNLOAD_CLICK_DIRECT = 7;
  public static final int REPORT_DOWNLOAD_FAILED = -2;
  public static final int REPORT_ENTRANCE_CLICK = 15;
  public static final int REPORT_ENTRANCE_IMPRESSION = 14;
  public static final int REPORT_FEED_NOMATCH = -4;
  public static final int REPORT_FEED_UNIMPRESSION = -3;
  public static final int REPORT_FILTERED = -1;
  public static final int REPORT_IMPRESSION = 0;
  private static final String a = Promoter.class.getSimpleName();
  public String ad_words;
  public int anim_in;
  public String app_package_name = "";
  public int app_version_code;
  public String app_version_name;
  public double bid;
  public int category;
  public int content_type;
  public String description;
  public int display_type;
  public boolean filterInstalledApp = false;
  public String icon;
  public int image_type;
  public String img;
  public String[] imgs;
  public String landing_size;
  public int landing_type;
  public int new_tip = 0;
  public String price;
  public String prom_act_pams = "";
  public String promoter;
  public String promoterPrice;
  public String provider;
  public int sell;
  public long size;
  public String slot_act_pams = "";
  public String text_color;
  public String text_font;
  public String text_size;
  public String title;
  public String url;
  public String url_in_app;

  public Promoter()
  {
  }

  protected Promoter(Parcel paramParcel)
  {
    if (paramParcel != null)
    {
      this.promoter = paramParcel.readString();
      this.category = paramParcel.readInt();
      this.content_type = paramParcel.readInt();
      this.display_type = paramParcel.readInt();
      this.img = paramParcel.readString();
      this.image_type = paramParcel.readInt();
      this.anim_in = paramParcel.readInt();
      this.landing_type = paramParcel.readInt();
      this.text_font = paramParcel.readString();
      this.text_size = paramParcel.readString();
      this.text_color = paramParcel.readString();
      this.title = paramParcel.readString();
      this.provider = paramParcel.readString();
      this.ad_words = paramParcel.readString();
      this.description = paramParcel.readString();
      this.icon = paramParcel.readString();
      this.url = paramParcel.readString();
      this.app_version_code = paramParcel.readInt();
      this.url_in_app = paramParcel.readString();
      this.size = paramParcel.readLong();
      this.app_package_name = paramParcel.readString();
      this.app_version_name = paramParcel.readString();
      this.new_tip = paramParcel.readInt();
      if (paramParcel.readInt() != 0)
        break label302;
    }
    while (true)
    {
      this.filterInstalledApp = bool;
      int i = paramParcel.readInt();
      String[] arrayOfString = new String[i];
      paramParcel.readStringArray(arrayOfString);
      if (i > 0)
        this.imgs = arrayOfString;
      this.prom_act_pams = paramParcel.readString();
      this.price = paramParcel.readString();
      this.promoterPrice = paramParcel.readString();
      this.sell = paramParcel.readInt();
      this.landing_size = paramParcel.readString();
      return;
      label302: bool = true;
    }
  }

  public Promoter(JSONObject paramJSONObject)
  {
    a(paramJSONObject);
  }

  private static Class<? extends Promoter> a(String paramString)
  {
    try
    {
      Class localClass = Class.forName(paramString);
      return localClass;
    }
    catch (Exception localException)
    {
      Log.e(com.alimama.mobile.csdk.umupdate.a.e.e, "can`t found the class " + paramString);
    }
    return Promoter.class;
  }

  private void a(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null)
      return;
    this.promoter = paramJSONObject.optString("promoter", "");
    this.category = paramJSONObject.optInt("category", 0);
    this.content_type = paramJSONObject.optInt("content_type");
    this.display_type = paramJSONObject.optInt("display_type", 0);
    this.image_type = paramJSONObject.optInt("img_type", 0);
    this.img = paramJSONObject.optString("img", "");
    this.anim_in = paramJSONObject.optInt("anim_in", 0);
    this.landing_type = paramJSONObject.optInt("landing_type", 0);
    this.text_size = paramJSONObject.optString("text_size", "");
    this.text_color = paramJSONObject.optString("text_color");
    this.text_font = paramJSONObject.optString("text_font");
    this.title = paramJSONObject.optString("title", "");
    this.provider = paramJSONObject.optString("provider", "");
    this.ad_words = paramJSONObject.optString("ad_words", "");
    this.description = paramJSONObject.optString("description", "");
    if (paramJSONObject.optInt("filter", 0) == 0);
    for (boolean bool = false; ; bool = true)
      while (true)
      {
        this.filterInstalledApp = bool;
        this.icon = paramJSONObject.optString("icon", "");
        this.url = paramJSONObject.optString("url", "");
        this.new_tip = paramJSONObject.optInt("new", 0);
        this.app_version_code = paramJSONObject.optInt("app_version_code", 0);
        this.url_in_app = paramJSONObject.optString("url_in_app");
        this.size = paramJSONObject.optLong("size", 0L);
        this.app_package_name = paramJSONObject.optString("app_package_name", "");
        this.app_version_name = paramJSONObject.optString("app_version_name", "");
        this.prom_act_pams = paramJSONObject.optString("act_pams", "");
        this.price = paramJSONObject.optString("price");
        this.promoterPrice = paramJSONObject.optString("promoprice", "");
        this.sell = paramJSONObject.optInt("sell");
        this.bid = paramJSONObject.optDouble("bid", 0.0D);
        this.landing_size = paramJSONObject.optString("landing_size");
        try
        {
          if (!paramJSONObject.has("imgs"))
            break;
          paramJSONObject = paramJSONObject.optString("imgs", "");
          if (TextUtils.isEmpty(paramJSONObject))
            break;
          this.imgs = paramJSONObject.split("\\,");
          return;
        }
        catch (Exception paramJSONObject)
        {
          return;
        }
      }
  }

  public static Promoter buildMockPromoter()
  {
    return new Promoter();
  }

  public static Class<? extends Promoter> getAdapterPromoterClz(f paramf, e parame)
  {
    if (e.b == parame)
      return a("com.taobao.newxp.TBItemPromoter");
    if (e.c == parame)
      return a("com.taobao.newxp.view.handler.UMTuanPromoter");
    return Promoter.class;
  }

  public static <T extends Promoter> T getPromoterFromJson(JSONObject paramJSONObject, Class<T> paramClass)
  {
    try
    {
      paramJSONObject = (Promoter)paramClass.getConstructor(new Class[] { JSONObject.class }).newInstance(new Object[] { paramJSONObject });
      return paramJSONObject;
    }
    catch (SecurityException paramJSONObject)
    {
      g.d("SecurityException", new Object[] { paramJSONObject });
      return null;
    }
    catch (NoSuchMethodException paramJSONObject)
    {
      while (true)
        g.d("NoSuchMethodException", new Object[] { paramJSONObject });
    }
    catch (IllegalArgumentException paramJSONObject)
    {
      while (true)
        g.d("IllegalArgumentException", new Object[] { paramJSONObject });
    }
    catch (InstantiationException paramJSONObject)
    {
      while (true)
        g.d("InstantiationException", new Object[] { paramJSONObject });
    }
    catch (IllegalAccessException paramJSONObject)
    {
      while (true)
        g.d("IllegalAccessException", new Object[] { paramJSONObject });
    }
    catch (InvocationTargetException paramJSONObject)
    {
      while (true)
        g.d("InvocationTargetException", new Object[] { paramJSONObject });
    }
  }

  public int describeContents()
  {
    return 0;
  }

  public JSONObject toJson()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("promoter", this.promoter);
      localJSONObject.put("category", this.category);
      localJSONObject.put("content_type", this.content_type);
      localJSONObject.put("display_type", this.display_type);
      localJSONObject.put("img", this.img);
      localJSONObject.put("img_type", this.image_type);
      localJSONObject.put("anim_in", this.anim_in);
      localJSONObject.put("display_type", this.display_type);
      localJSONObject.put("img", this.img);
      localJSONObject.put("landing_type", this.landing_type);
      localJSONObject.put("text_size", this.text_size);
      localJSONObject.put("text_color", this.text_color);
      localJSONObject.put("text_font", this.text_font);
      localJSONObject.put("title", this.title);
      localJSONObject.put("provider", this.provider);
      localJSONObject.put("ad_words", this.ad_words);
      localJSONObject.put("description", this.description);
      localJSONObject.put("icon", this.icon);
      localJSONObject.put("url", this.url);
      localJSONObject.put("new", this.new_tip);
      localJSONObject.put("app_version_code", this.app_version_code);
      localJSONObject.put("url_in_app", this.url_in_app);
      localJSONObject.put("size", this.size);
      localJSONObject.put("app_package_name", this.app_package_name);
      localJSONObject.put("app_version_name", this.app_version_name);
      localJSONObject.put("price", this.price);
      localJSONObject.put("promoprice", this.promoterPrice);
      localJSONObject.put("sell", this.sell);
      localJSONObject.put("bid", this.bid);
      localJSONObject.put("landing_size", this.landing_size);
      if ((this.imgs != null) && (this.imgs.length > 0))
        localJSONObject.put("imgs", Arrays.toString(this.imgs));
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
    }
    return null;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.promoter);
    paramParcel.writeInt(this.category);
    paramParcel.writeInt(this.content_type);
    paramParcel.writeInt(this.display_type);
    paramParcel.writeString(this.img);
    paramParcel.writeInt(this.image_type);
    paramParcel.writeInt(this.anim_in);
    paramParcel.writeInt(this.landing_type);
    paramParcel.writeString(this.text_font);
    paramParcel.writeString(this.text_size);
    paramParcel.writeString(this.text_color);
    paramParcel.writeString(this.title);
    paramParcel.writeString(this.provider);
    paramParcel.writeString(this.ad_words);
    paramParcel.writeString(this.description);
    paramParcel.writeString(this.icon);
    paramParcel.writeString(this.url);
    paramParcel.writeInt(this.app_version_code);
    paramParcel.writeString(this.url_in_app);
    paramParcel.writeLong(this.size);
    paramParcel.writeString(this.app_package_name);
    paramParcel.writeString(this.app_version_name);
    paramParcel.writeInt(this.new_tip);
    if (this.filterInstalledApp)
    {
      paramInt = 1;
      paramParcel.writeInt(paramInt);
      if (this.imgs != null)
        break label275;
      paramInt = 0;
      label207: paramParcel.writeInt(paramInt);
      if (this.imgs != null)
        break label284;
    }
    label275: label284: for (String[] arrayOfString = new String[0]; ; arrayOfString = this.imgs)
    {
      paramParcel.writeStringArray(arrayOfString);
      paramParcel.writeString(this.prom_act_pams);
      paramParcel.writeString(this.price);
      paramParcel.writeString(this.promoterPrice);
      paramParcel.writeInt(this.sell);
      paramParcel.writeString(this.landing_size);
      return;
      paramInt = 0;
      break;
      paramInt = this.imgs.length;
      break label207;
    }
  }

  public static abstract interface a<T extends Promoter>
  {
    public abstract Collection<T> a();

    public abstract String b();

    public abstract Class<T> c();
  }

  public static enum b
  {
    public static String[] a()
    {
      b[] arrayOfb = values();
      String[] arrayOfString = new String[arrayOfb.length];
      int i = 0;
      while (i < arrayOfb.length)
      {
        arrayOfString[i] = arrayOfb[i].toString();
        i += 1;
      }
      return arrayOfString;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alimama.mobile.csdk.umupdate.models.Promoter
 * JD-Core Version:    0.6.2
 */