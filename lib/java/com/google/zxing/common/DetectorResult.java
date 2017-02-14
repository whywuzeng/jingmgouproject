package com.google.zxing.common;

import com.google.zxing.ResultPoint;

public final class DetectorResult
{
  private final BitMatrix bits;
  private final ResultPoint[] points;

  public DetectorResult(BitMatrix paramBitMatrix, ResultPoint[] paramArrayOfResultPoint)
  {
    this.bits = paramBitMatrix;
    this.points = paramArrayOfResultPoint;
  }

  public BitMatrix getBits()
  {
    return this.bits;
  }

  public ResultPoint[] getPoints()
  {
    return this.points;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.common.DetectorResult
 * JD-Core Version:    0.6.2
 */