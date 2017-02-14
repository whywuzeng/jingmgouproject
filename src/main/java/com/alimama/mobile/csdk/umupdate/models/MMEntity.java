package com.alimama.mobile.csdk.umupdate.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class MMEntity extends a
  implements Parcelable, Cloneable
{
  public static Parcelable.Creator<MMEntity> CREATOR = new Parcelable.Creator()
  {
    public MMEntity a(Parcel paramAnonymousParcel)
    {
      return new MMEntity(paramAnonymousParcel, null);
    }

    public MMEntity[] a(int paramAnonymousInt)
    {
      return new MMEntity[paramAnonymousInt];
    }
  };
  protected int a = 0;
  protected long b = -1L;
  protected boolean c = false;
  public int cache = -1;
  public int displayStyle = 0;
  public String displayType = "bigImg";
  public HashMap<String, Object> ecomparms = new HashMap();
  public String entryStr = "";
  public long expire = 0L;
  public boolean filterInstalledApp = true;
  public int image_type = 0;
  public String iscache = "";
  public String ispreload = "";
  public String landingUrl = "";
  public String landing_image = "";
  public String landing_size;
  public d module = d.a;
  public int newTips = -1;
  public String new_image = "";
  public String opensize = "";
  public int preload = 0;
  public int sid_expired = 1;
  public long[] timeline = new long[4];
  public boolean wallSwitch = false;

  private MMEntity(Parcel paramParcel)
  {
    super(paramParcel);
    int i = paramParcel.readInt();
    d locald;
    if (i == -1)
    {
      locald = null;
      this.module = locald;
      this.opensize = paramParcel.readString();
      this.landing_image = paramParcel.readString();
      this.landingUrl = paramParcel.readString();
      this.new_image = paramParcel.readString();
      this.image_type = paramParcel.readInt();
      this.displayStyle = paramParcel.readInt();
      this.displayType = paramParcel.readString();
      this.newTips = paramParcel.readInt();
      this.cache = paramParcel.readInt();
      if (paramParcel.readByte() == 0)
        break label381;
      bool1 = true;
      label243: this.filterInstalledApp = bool1;
      if (paramParcel.readByte() == 0)
        break label386;
      bool1 = true;
      label257: this.wallSwitch = bool1;
      this.a = paramParcel.readInt();
      this.b = paramParcel.readLong();
      if (paramParcel.readByte() == 0)
        break label391;
    }
    label386: label391: for (boolean bool1 = bool2; ; bool1 = false)
    {
      this.c = bool1;
      this.expire = paramParcel.readLong();
      this.sid_expired = paramParcel.readInt();
      this.entryStr = paramParcel.readString();
      this.ispreload = paramParcel.readString();
      this.preload = paramParcel.readInt();
      this.iscache = paramParcel.readString();
      this.landing_size = paramParcel.readString();
      this.timeline = paramParcel.createLongArray();
      this.ecomparms = paramParcel.readHashMap(HashMap.class.getClassLoader());
      return;
      locald = d.values()[i];
      break;
      label381: bool1 = false;
      break label243;
      bool1 = false;
      break label257;
    }
  }

  public MMEntity(String paramString)
  {
    this.slotId = paramString;
    this.layoutType = 17;
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }

  public int describeContents()
  {
    return 0;
  }

  public void extendFields(MMEntity paramMMEntity)
  {
    this.tabId = paramMMEntity.tabId;
    this.slot_act_params = paramMMEntity.slot_act_params;
    this.urlParams = paramMMEntity.urlParams;
  }

  public String getTimeConsuming()
  {
    if ((this.timeline != null) && (this.timeline.length == 4) && (this.timeline[0] > 0L))
    {
      long l3 = this.timeline[0];
      long l4 = this.timeline[1];
      long l1 = this.timeline[2];
      long l2 = this.timeline[3];
      l3 = l4 - l3;
      if (l3 > 0L)
        return l3 + "_" + (l2 - l1);
    }
    return null;
  }

  public void warp(JSONObject paramJSONObject)
  {
    super.warp(paramJSONObject);
    this.landing_size = paramJSONObject.optString("landing_size");
    boolean bool;
    if (paramJSONObject.optInt("filter", 1) == 1)
      bool = true;
    while (true)
    {
      this.filterInstalledApp = bool;
      this.cache = paramJSONObject.optInt("cache", -1);
      this.sid_expired = paramJSONObject.optInt("sid_expire", 1);
      this.expire = paramJSONObject.optLong("expire", 0L);
      this.landing_image = paramJSONObject.optString("landing_image", "");
      this.landingUrl = paramJSONObject.optString("landing_url", "");
      this.new_image = paramJSONObject.optString("new_img", "");
      this.displayType = paramJSONObject.optString("display_type", "bigImg");
      Object localObject = paramJSONObject.optString("module", "");
      if (!TextUtils.isEmpty((CharSequence)localObject))
      {
        d locald = d.a((String)localObject);
        localObject = locald;
        if (locald == null)
          localObject = d.a;
        this.module = ((d)localObject);
      }
      this.image_type = paramJSONObject.optInt("img_type", 0);
      localObject = paramJSONObject.optJSONArray("walls");
      if ((localObject != null) && (((JSONArray)localObject).length() > 1))
        this.wallSwitch = true;
      if (paramJSONObject.has("opensize"));
      try
      {
        float f = Float.parseFloat(paramJSONObject.getString("opensize")) / 100.0F;
        this.opensize = ("" + f);
        label245: localObject = paramJSONObject.optJSONObject("dm");
        if (localObject != null)
          this.a = ((JSONObject)localObject).optInt("on");
        for (this.b = (((JSONObject)localObject).optInt("tm") * 60 * 60 * 1000); ; this.b = -1L)
        {
          this.newTips = paramJSONObject.optInt("new_num", -1);
          this.entryStr = paramJSONObject.optString("landing_text", "");
          this.ispreload = paramJSONObject.optString("ispreload", "");
          this.preload = paramJSONObject.optInt("preload", 0);
          this.iscache = paramJSONObject.optString("iscache", "");
          return;
          bool = false;
          break;
          this.a = 0;
        }
      }
      catch (Exception localException)
      {
        break label245;
      }
    }
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    byte b2 = 1;
    super.writeToParcel(paramParcel, paramInt);
    if (this.module == null)
    {
      paramInt = -1;
      paramParcel.writeInt(paramInt);
      paramParcel.writeString(this.opensize);
      paramParcel.writeString(this.landing_image);
      paramParcel.writeString(this.landingUrl);
      paramParcel.writeString(this.new_image);
      paramParcel.writeInt(this.image_type);
      paramParcel.writeInt(this.displayStyle);
      paramParcel.writeString(this.displayType);
      paramParcel.writeInt(this.newTips);
      paramParcel.writeInt(this.cache);
      if (!this.filterInstalledApp)
        break label238;
      b1 = 1;
      label104: paramParcel.writeByte(b1);
      if (!this.wallSwitch)
        break label243;
      b1 = 1;
      label118: paramParcel.writeByte(b1);
      paramParcel.writeInt(this.a);
      paramParcel.writeLong(this.b);
      if (!this.c)
        break label248;
    }
    label238: label243: label248: for (byte b1 = b2; ; b1 = 0)
    {
      paramParcel.writeByte(b1);
      paramParcel.writeLong(this.expire);
      paramParcel.writeInt(this.sid_expired);
      paramParcel.writeString(this.entryStr);
      paramParcel.writeString(this.ispreload);
      paramParcel.writeInt(this.preload);
      paramParcel.writeString(this.iscache);
      paramParcel.writeString(this.landing_size);
      paramParcel.writeLongArray(this.timeline);
      paramParcel.writeMap(this.ecomparms);
      return;
      paramInt = this.module.ordinal();
      break;
      b1 = 0;
      break label104;
      b1 = 0;
      break label118;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alimama.mobile.csdk.umupdate.models.MMEntity
 * JD-Core Version:    0.6.2
 */