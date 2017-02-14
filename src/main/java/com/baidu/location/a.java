package com.baidu.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class a
  implements Parcelable.Creator
{
  public BDLocation a(Parcel paramParcel)
  {
    return new BDLocation(paramParcel, null);
  }

  public BDLocation[] a(int paramInt)
  {
    return new BDLocation[paramInt];
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.a
 * JD-Core Version:    0.6.2
 */