package com.baidu.mapapi.model.inner;

import java.io.Serializable;

public class MapBound
  implements Serializable
{
  public Point ptLB;
  public Point ptRT;

  public MapBound()
  {
    if (this.ptLB == null)
      this.ptLB = new Point();
    if (this.ptRT == null)
      this.ptRT = new Point();
  }

  public Point getPtLB()
  {
    return this.ptLB;
  }

  public Point getPtRT()
  {
    return this.ptRT;
  }

  public void setPtLB(Point paramPoint)
  {
    this.ptLB = paramPoint;
  }

  public void setPtRT(Point paramPoint)
  {
    this.ptRT = paramPoint;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.model.inner.MapBound
 * JD-Core Version:    0.6.2
 */