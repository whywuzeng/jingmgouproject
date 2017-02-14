package com.baidu.mapapi.map;

public class MyLocationData
{
  public final float accuracy;
  public final float direction;
  public final double latitude;
  public final double longitude;
  public final int satellitesNum;
  public final float speed;

  MyLocationData(double paramDouble1, double paramDouble2, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt)
  {
    this.latitude = paramDouble1;
    this.longitude = paramDouble2;
    this.speed = paramFloat1;
    this.direction = paramFloat2;
    this.accuracy = paramFloat3;
    this.satellitesNum = paramInt;
  }

  public static class Builder
  {
    private double a;
    private double b;
    private float c;
    private float d;
    private float e;
    private int f;

    public Builder accuracy(float paramFloat)
    {
      this.e = paramFloat;
      return this;
    }

    public MyLocationData build()
    {
      return new MyLocationData(this.a, this.b, this.c, this.d, this.e, this.f);
    }

    public Builder direction(float paramFloat)
    {
      this.d = paramFloat;
      return this;
    }

    public Builder latitude(double paramDouble)
    {
      this.a = paramDouble;
      return this;
    }

    public Builder longitude(double paramDouble)
    {
      this.b = paramDouble;
      return this;
    }

    public Builder satellitesNum(int paramInt)
    {
      this.f = paramInt;
      return this;
    }

    public Builder speed(float paramFloat)
    {
      this.c = paramFloat;
      return this;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.MyLocationData
 * JD-Core Version:    0.6.2
 */