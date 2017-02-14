package com.baidu.mapapi.model.inner;

public class GeoPoint
{
  private double a;
  private double b;

  public GeoPoint(double paramDouble1, double paramDouble2)
  {
    this.a = paramDouble1;
    this.b = paramDouble2;
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    while (paramObject.getClass() != getClass())
      return false;
    if ((this.a == ((GeoPoint)paramObject).a) && (this.b == ((GeoPoint)paramObject).b));
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public double getLatitudeE6()
  {
    return this.a;
  }

  public double getLongitudeE6()
  {
    return this.b;
  }

  public void setLatitudeE6(double paramDouble)
  {
    this.a = paramDouble;
  }

  public void setLongitudeE6(double paramDouble)
  {
    this.b = paramDouble;
  }

  public String toString()
  {
    return "GeoPoint: Latitude: " + this.a + ", Longitude: " + this.b;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.model.inner.GeoPoint
 * JD-Core Version:    0.6.2
 */