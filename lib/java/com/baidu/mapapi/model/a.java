package com.baidu.mapapi.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class a
  implements Parcelable.Creator<LatLng>
{
  public LatLng a(Parcel paramParcel)
  {
    return new LatLng(paramParcel);
  }

  public LatLng[] a(int paramInt)
  {
    return new LatLng[paramInt];
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.model.a
 * JD-Core Version:    0.6.2
 */