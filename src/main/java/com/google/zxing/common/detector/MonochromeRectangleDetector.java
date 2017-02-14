package com.google.zxing.common.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;

public final class MonochromeRectangleDetector
{
  private static final int MAX_MODULES = 32;
  private final BitMatrix image;

  public MonochromeRectangleDetector(BitMatrix paramBitMatrix)
  {
    this.image = paramBitMatrix;
  }

  private int[] blackWhiteRange(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
  {
    int j = paramInt3 + paramInt4 >> 1;
    int i = j;
    int k;
    label62: int m;
    if (i >= paramInt3)
    {
      if (paramBoolean)
      {
        if (!this.image.get(i, paramInt1));
      }
      else
        while (this.image.get(paramInt1, i))
        {
          i -= 1;
          break;
        }
      k = i;
      do
      {
        m = k - 1;
        if (m < paramInt3)
          break;
        if (!paramBoolean)
          break label150;
        k = m;
      }
      while (!this.image.get(m, paramInt1));
      label96: if ((m >= paramInt3) && (i - m <= paramInt2));
    }
    else
    {
      k = i + 1;
      paramInt3 = j;
    }
    while (true)
    {
      label120: if (paramInt3 < paramInt4)
      {
        if (paramBoolean)
        {
          if (!this.image.get(paramInt3, paramInt1));
        }
        else
          while (this.image.get(paramInt1, paramInt3))
          {
            paramInt3 += 1;
            break label120;
            k = m;
            if (!this.image.get(paramInt1, m))
              break label62;
            break label96;
            i = m;
            break;
          }
        i = paramInt3;
        do
        {
          j = i + 1;
          if (j >= paramInt4)
            break;
          if (!paramBoolean)
            break label265;
          i = j;
        }
        while (!this.image.get(j, paramInt1));
      }
      while (true)
      {
        label150: if ((j < paramInt4) && (j - paramInt3 <= paramInt2))
          break label285;
        paramInt1 = paramInt3 - 1;
        if (paramInt1 <= k)
          break label291;
        return new int[] { k, paramInt1 };
        label265: i = j;
        if (!this.image.get(paramInt1, j))
          break;
      }
      label285: paramInt3 = j;
    }
    label291: return null;
  }

  private ResultPoint findCornerFromCenter(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9)
    throws NotFoundException
  {
    int j = paramInt1;
    int i = paramInt5;
    int[] arrayOfInt;
    for (Object localObject = null; (i < paramInt8) && (i >= paramInt7) && (j < paramInt4) && (j >= paramInt3); localObject = arrayOfInt)
    {
      if (paramInt2 == 0)
        arrayOfInt = blackWhiteRange(i, paramInt9, paramInt3, paramInt4, true);
      while (arrayOfInt == null)
        if (localObject == null)
        {
          throw NotFoundException.getNotFoundInstance();
          arrayOfInt = blackWhiteRange(j, paramInt9, paramInt7, paramInt8, false);
        }
        else
        {
          float f1;
          if (paramInt2 == 0)
          {
            paramInt2 = i - paramInt6;
            if (localObject[0] < paramInt1)
            {
              if (localObject[1] > paramInt1)
              {
                if (paramInt6 > 0);
                for (f1 = localObject[0]; ; f1 = localObject[1])
                  return new ResultPoint(f1, paramInt2);
              }
              return new ResultPoint(localObject[0], paramInt2);
            }
            return new ResultPoint(localObject[1], paramInt2);
          }
          paramInt1 = j - paramInt2;
          if (localObject[0] < paramInt5)
          {
            if (localObject[1] > paramInt5)
            {
              float f2 = paramInt1;
              if (paramInt2 < 0);
              for (f1 = localObject[0]; ; f1 = localObject[1])
                return new ResultPoint(f2, f1);
            }
            return new ResultPoint(paramInt1, localObject[0]);
          }
          return new ResultPoint(paramInt1, localObject[1]);
        }
      j += paramInt2;
      i += paramInt6;
    }
    throw NotFoundException.getNotFoundInstance();
  }

  public ResultPoint[] detect()
    throws NotFoundException
  {
    int i1 = this.image.getHeight();
    int i2 = this.image.getWidth();
    int i = i1 >> 1;
    int j = i2 >> 1;
    int k = Math.max(1, i1 / 256);
    int i3 = Math.max(1, i2 / 256);
    int m = (int)findCornerFromCenter(j, 0, 0, i2, i, -k, 0, i1, j >> 1).getY() - 1;
    ResultPoint localResultPoint1 = findCornerFromCenter(j, -i3, 0, i2, i, 0, m, i1, i >> 1);
    int n = (int)localResultPoint1.getX() - 1;
    ResultPoint localResultPoint2 = findCornerFromCenter(j, i3, n, i2, i, 0, m, i1, i >> 1);
    i2 = (int)localResultPoint2.getX() + 1;
    ResultPoint localResultPoint3 = findCornerFromCenter(j, 0, n, i2, i, k, m, i1, j >> 1);
    i1 = (int)localResultPoint3.getY();
    return new ResultPoint[] { findCornerFromCenter(j, 0, n, i2, i, -k, m, i1 + 1, j >> 2), localResultPoint1, localResultPoint2, localResultPoint3 };
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.common.detector.MonochromeRectangleDetector
 * JD-Core Version:    0.6.2
 */