package com.baidu.mapapi.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ParcelItem
  implements Parcelable
{
  public static final Parcelable.Creator<ParcelItem> CREATOR = new b();
  private Bundle a;

  public int describeContents()
  {
    return 0;
  }

  public Bundle getBundle()
  {
    return this.a;
  }

  public void setBundle(Bundle paramBundle)
  {
    this.a = paramBundle;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeBundle(this.a);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.model.ParcelItem
 * JD-Core Version:    0.6.2
 */