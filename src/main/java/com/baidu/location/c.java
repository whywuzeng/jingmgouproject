package com.baidu.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class c
  implements Parcelable.Creator
{
  public Poi a(Parcel paramParcel)
  {
    return new Poi(paramParcel.readString(), paramParcel.readString(), paramParcel.readDouble());
  }

  public Poi[] a(int paramInt)
  {
    return new Poi[paramInt];
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.c
 * JD-Core Version:    0.6.2
 */