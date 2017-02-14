package com.baidu.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class Poi
  implements Parcelable
{
  public static final Parcelable.Creator CREATOR = new c();
  private final double a;
  private final String jdField_do;
  private final String jdField_if;

  public Poi(String paramString1, String paramString2, double paramDouble)
  {
    this.jdField_do = paramString1;
    this.jdField_if = paramString2;
    this.a = paramDouble;
  }

  public int describeContents()
  {
    return 0;
  }

  public String getId()
  {
    return this.jdField_do;
  }

  public String getName()
  {
    return this.jdField_if;
  }

  public double getRank()
  {
    return this.a;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.jdField_do);
    paramParcel.writeString(this.jdField_if);
    paramParcel.writeDouble(this.a);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.Poi
 * JD-Core Version:    0.6.2
 */