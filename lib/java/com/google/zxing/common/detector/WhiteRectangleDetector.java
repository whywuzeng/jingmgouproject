package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

public final class WhiteRectangleDetector
{
  private static final int CORR = 1;
  private static final int INIT_SIZE = 40;
  private final int height;
  private final BitMatrix image;
  private final int width;

  public WhiteRectangleDetector(BitMatrix paramBitMatrix)
  {
    this.image = paramBitMatrix;
    this.height = paramBitMatrix.getHeight();
    this.width = paramBitMatrix.getWidth();
  }

  private ResultPoint[] centerEdges(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, ResultPoint paramResultPoint3, ResultPoint paramResultPoint4)
  {
    float f1 = paramResultPoint1.getX();
    float f2 = paramResultPoint1.getY();
    float f3 = paramResultPoint2.getX();
    float f4 = paramResultPoint2.getY();
    float f5 = paramResultPoint3.getX();
    float f6 = paramResultPoint3.getY();
    float f7 = paramResultPoint4.getX();
    float f8 = paramResultPoint4.getY();
    if (f1 < this.width / 2)
      return new ResultPoint[] { new ResultPoint(f7 - 1.0F, f8 + 1.0F), new ResultPoint(f3 + 1.0F, f4 + 1.0F), new ResultPoint(f5 - 1.0F, f6 - 1.0F), new ResultPoint(f1 + 1.0F, f2 - 1.0F) };
    return new ResultPoint[] { new ResultPoint(f7 + 1.0F, f8 + 1.0F), new ResultPoint(f3 + 1.0F, f4 - 1.0F), new ResultPoint(f5 - 1.0F, f6 + 1.0F), new ResultPoint(f1 - 1.0F, f2 - 1.0F) };
  }

  private boolean containsBlackPoint(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    int i = paramInt1;
    if (paramBoolean)
      while (paramInt1 <= paramInt2)
      {
        if (this.image.get(paramInt1, paramInt3))
          return true;
        paramInt1 += 1;
      }
    do
    {
      i += 1;
      if (i > paramInt2)
        break;
    }
    while (!this.image.get(paramInt3, i));
    return true;
    return false;
  }

  private static int distanceL2(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    paramFloat1 -= paramFloat3;
    paramFloat2 -= paramFloat4;
    return round((float)Math.sqrt(paramFloat1 * paramFloat1 + paramFloat2 * paramFloat2));
  }

  private ResultPoint getBlackPointOnSegment(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    int j = distanceL2(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
    paramFloat3 = (paramFloat3 - paramFloat1) / j;
    paramFloat4 = (paramFloat4 - paramFloat2) / j;
    int i = 0;
    while (i < j)
    {
      int k = round(i * paramFloat3 + paramFloat1);
      int m = round(i * paramFloat4 + paramFloat2);
      if (this.image.get(k, m))
        return new ResultPoint(k, m);
      i += 1;
    }
    return null;
  }

  private static int round(float paramFloat)
  {
    return (int)(0.5F + paramFloat);
  }

  public ResultPoint[] detect()
    throws NotFoundException
  {
    int i8 = 0;
    int i7 = 1;
    int k = this.width - 40 >> 1;
    int i3 = this.width + 40 >> 1;
    int i = this.height - 40 >> 1;
    int i1 = this.height + 40 >> 1;
    int i5 = 0;
    int i6 = 1;
    int i9;
    int m;
    int j;
    boolean bool;
    int n;
    if (i6 != 0)
    {
      i9 = 1;
      m = 0;
      j = i3;
      while ((i9 != 0) && (j < this.width))
      {
        bool = containsBlackPoint(i, i1, j, false);
        i9 = bool;
        if (bool)
        {
          j += 1;
          m = 1;
          i9 = bool;
        }
      }
      if (j >= this.width)
      {
        m = 1;
        n = j;
        j = k;
        k = i;
        i = i1;
      }
    }
    while (true)
    {
      label136: ResultPoint localResultPoint1;
      if ((m == 0) && (i5 != 0))
      {
        i1 = n - j;
        m = 1;
        localResultPoint1 = null;
        label158: if (m >= i1)
          break label732;
        localResultPoint1 = getBlackPointOnSegment(j, i - m, j + m, i);
        if (localResultPoint1 == null);
      }
      label732: for (ResultPoint localResultPoint2 = localResultPoint1; ; localResultPoint2 = localResultPoint1)
      {
        if (localResultPoint2 == null)
        {
          throw NotFoundException.getNotFoundInstance();
          i9 = 1;
          n = m;
          m = i1;
          while ((i9 != 0) && (m < this.height))
          {
            bool = containsBlackPoint(k, j, m, true);
            i9 = bool;
            if (bool)
            {
              m += 1;
              n = 1;
              i9 = bool;
            }
          }
          if (m >= this.height)
          {
            i2 = 1;
            n = j;
            i1 = m;
            j = k;
            k = i;
            m = i2;
            i = i1;
            break label136;
          }
          i9 = 1;
          i1 = n;
          n = k;
          while ((i9 != 0) && (n >= 0))
          {
            bool = containsBlackPoint(i, m, n, false);
            i9 = bool;
            if (bool)
            {
              n -= 1;
              i1 = 1;
              i9 = bool;
            }
          }
          if (n < 0)
          {
            i2 = 1;
            i1 = j;
            k = m;
            j = n;
            n = i;
            m = i2;
            i = k;
            k = n;
            n = i1;
            break label136;
          }
          i9 = 1;
          int i4 = i1;
          int i2 = i;
          while ((i9 != 0) && (i2 >= 0))
          {
            bool = containsBlackPoint(n, j, i2, true);
            i9 = bool;
            if (bool)
            {
              i2 -= 1;
              i4 = 1;
              i9 = bool;
            }
          }
          if (i2 < 0)
          {
            i3 = 1;
            i1 = j;
            i = m;
            j = n;
            k = i2;
            m = i3;
            n = i1;
            break label136;
          }
          i1 = m;
          i = i2;
          i3 = j;
          k = n;
          i6 = i4;
          if (i4 == 0)
            break;
          i5 = 1;
          i1 = m;
          i = i2;
          i3 = j;
          k = n;
          i6 = i4;
          break;
          m += 1;
          break label158;
        }
        m = 1;
        localResultPoint1 = null;
        if (m < i1)
        {
          localResultPoint1 = getBlackPointOnSegment(j, k + m, j + m, k);
          if (localResultPoint1 == null);
        }
        for (ResultPoint localResultPoint3 = localResultPoint1; ; localResultPoint3 = localResultPoint1)
        {
          if (localResultPoint3 == null)
          {
            throw NotFoundException.getNotFoundInstance();
            m += 1;
            break;
          }
          j = 1;
          localResultPoint1 = null;
          if (j < i1)
          {
            localResultPoint1 = getBlackPointOnSegment(n, k + j, n - j, k);
            if (localResultPoint1 == null);
          }
          for (ResultPoint localResultPoint4 = localResultPoint1; ; localResultPoint4 = localResultPoint1)
          {
            if (localResultPoint4 == null)
            {
              throw NotFoundException.getNotFoundInstance();
              j += 1;
              break;
            }
            localResultPoint1 = null;
            j = i7;
            while (true)
            {
              if (j < i1)
              {
                localResultPoint1 = getBlackPointOnSegment(n, i - j, n - j, i);
                if (localResultPoint1 == null);
              }
              else
              {
                if (localResultPoint1 != null)
                  break;
                throw NotFoundException.getNotFoundInstance();
              }
              j += 1;
            }
            return centerEdges(localResultPoint1, localResultPoint2, localResultPoint4, localResultPoint3);
            throw NotFoundException.getNotFoundInstance();
          }
        }
      }
      n = i3;
      j = k;
      k = i;
      m = i8;
      i = i1;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.common.detector.WhiteRectangleDetector
 * JD-Core Version:    0.6.2
 */