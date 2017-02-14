package com.baidu.mapapi.map;

public class MyLocationConfiguration
{
  public final BitmapDescriptor customMarker;
  public final boolean enableDirection;
  public final LocationMode locationMode;

  public MyLocationConfiguration(LocationMode paramLocationMode, boolean paramBoolean, BitmapDescriptor paramBitmapDescriptor)
  {
    LocationMode localLocationMode = paramLocationMode;
    if (paramLocationMode == null)
      localLocationMode = LocationMode.NORMAL;
    this.locationMode = localLocationMode;
    this.enableDirection = paramBoolean;
    this.customMarker = paramBitmapDescriptor;
  }

  public static enum LocationMode
  {
    static
    {
      FOLLOWING = new LocationMode("FOLLOWING", 1);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.MyLocationConfiguration
 * JD-Core Version:    0.6.2
 */