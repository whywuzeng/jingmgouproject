package com.baidu.mapapi.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class LatLng
  implements Parcelable
{
  public static final Parcelable.Creator<LatLng> CREATOR = new a();
  private static final String a = LatLng.class.getSimpleName();
  public final double latitude;
  public final double latitudeE6;
  public final double longitude;
  public final double longitudeE6;

  public LatLng(double paramDouble1, double paramDouble2)
  {
    paramDouble1 *= 1000000.0D;
    paramDouble2 *= 1000000.0D;
    this.latitudeE6 = paramDouble1;
    this.longitudeE6 = paramDouble2;
    this.latitude = (paramDouble1 / 1000000.0D);
    this.longitude = (paramDouble2 / 1000000.0D);
  }

  protected LatLng(Parcel paramParcel)
  {
    this.latitude = paramParcel.readDouble();
    this.longitude = paramParcel.readDouble();
    this.latitudeE6 = paramParcel.readDouble();
    this.longitudeE6 = paramParcel.readDouble();
  }

  public int describeContents()
  {
    return 0;
  }

  public String toString()
  {
    String str = new String("latitude: ");
    str = str + this.latitude;
    str = str + ", longitude: ";
    return str + this.longitude;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeDouble(this.latitude);
    paramParcel.writeDouble(this.longitude);
    paramParcel.writeDouble(this.latitudeE6);
    paramParcel.writeDouble(this.longitudeE6);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.model.LatLng
 * JD-Core Version:    0.6.2
 */