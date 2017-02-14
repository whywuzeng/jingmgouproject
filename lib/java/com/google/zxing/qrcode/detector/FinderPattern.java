package com.google.zxing.qrcode.detector;

import com.google.zxing.ResultPoint;

public final class FinderPattern extends ResultPoint
{
  private int count;
  private final float estimatedModuleSize;

  FinderPattern(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    super(paramFloat1, paramFloat2);
    this.estimatedModuleSize = paramFloat3;
    this.count = 1;
  }

  boolean aboutEquals(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (Math.abs(paramFloat2 - getY()) <= paramFloat1)
    {
      bool1 = bool2;
      if (Math.abs(paramFloat3 - getX()) <= paramFloat1)
      {
        paramFloat1 = Math.abs(paramFloat1 - this.estimatedModuleSize);
        if (paramFloat1 > 1.0F)
        {
          bool1 = bool2;
          if (paramFloat1 / this.estimatedModuleSize > 1.0F);
        }
        else
        {
          bool1 = true;
        }
      }
    }
    return bool1;
  }

  int getCount()
  {
    return this.count;
  }

  public float getEstimatedModuleSize()
  {
    return this.estimatedModuleSize;
  }

  void incrementCount()
  {
    this.count += 1;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.detector.FinderPattern
 * JD-Core Version:    0.6.2
 */