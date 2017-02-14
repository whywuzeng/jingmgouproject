package com.baidu.mapapi.model.inner;

import java.io.Serializable;

public class Point
  implements Serializable
{
  public int x;
  public int y;

  public Point()
  {
  }

  public Point(int paramInt1, int paramInt2)
  {
    this.x = paramInt1;
    this.y = paramInt2;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    do
    {
      return true;
      if (paramObject == null)
        return false;
      if (getClass() != paramObject.getClass())
        return false;
      paramObject = (Point)paramObject;
      if (this.x != paramObject.x)
        return false;
    }
    while (this.y == paramObject.y);
    return false;
  }

  public int getmPtx()
  {
    return this.x;
  }

  public int getmPty()
  {
    return this.y;
  }

  public int hashCode()
  {
    return (this.x + 31) * 31 + this.y;
  }

  public void setmPtx(int paramInt)
  {
    this.x = paramInt;
  }

  public void setmPty(int paramInt)
  {
    this.y = paramInt;
  }

  public String toString()
  {
    return "Point [x=" + this.x + ", y=" + this.y + "]";
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.model.inner.Point
 * JD-Core Version:    0.6.2
 */