package com.google.zxing.pdf417.detector;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.GridSampler;
import java.util.Hashtable;

public final class Detector
{
  private static final int MAX_AVG_VARIANCE = 107;
  private static final int MAX_INDIVIDUAL_VARIANCE = 204;
  private static final int SKEW_THRESHOLD = 2;
  private static final int[] START_PATTERN = { 8, 1, 1, 1, 1, 1, 1, 3 };
  private static final int[] START_PATTERN_REVERSE = { 3, 1, 1, 1, 1, 1, 1, 8 };
  private static final int[] STOP_PATTERN = { 7, 1, 1, 3, 1, 1, 1, 2, 1 };
  private static final int[] STOP_PATTERN_REVERSE = { 1, 2, 1, 1, 1, 3, 1, 1, 7 };
  private final BinaryBitmap image;

  public Detector(BinaryBitmap paramBinaryBitmap)
  {
    this.image = paramBinaryBitmap;
  }

  private static int computeDimension(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, ResultPoint paramResultPoint3, ResultPoint paramResultPoint4, float paramFloat)
  {
    return ((round(ResultPoint.distance(paramResultPoint1, paramResultPoint2) / paramFloat) + round(ResultPoint.distance(paramResultPoint3, paramResultPoint4) / paramFloat) >> 1) + 8) / 17 * 17;
  }

  private static float computeModuleWidth(ResultPoint[] paramArrayOfResultPoint)
  {
    return ((ResultPoint.distance(paramArrayOfResultPoint[0], paramArrayOfResultPoint[4]) + ResultPoint.distance(paramArrayOfResultPoint[1], paramArrayOfResultPoint[5])) / 34.0F + (ResultPoint.distance(paramArrayOfResultPoint[6], paramArrayOfResultPoint[2]) + ResultPoint.distance(paramArrayOfResultPoint[7], paramArrayOfResultPoint[3])) / 36.0F) / 2.0F;
  }

  private static void correctCodeWordVertices(ResultPoint[] paramArrayOfResultPoint, boolean paramBoolean)
  {
    float f2 = paramArrayOfResultPoint[4].getY() - paramArrayOfResultPoint[6].getY();
    float f1 = f2;
    if (paramBoolean)
      f1 = -f2;
    if (f1 > 2.0F)
    {
      f1 = paramArrayOfResultPoint[4].getX();
      f2 = paramArrayOfResultPoint[0].getX();
      f3 = paramArrayOfResultPoint[6].getX();
      f4 = paramArrayOfResultPoint[0].getX();
      f1 = (f1 - f2) * (paramArrayOfResultPoint[6].getY() - paramArrayOfResultPoint[0].getY()) / (f3 - f4);
    }
    do
    {
      paramArrayOfResultPoint[4] = new ResultPoint(paramArrayOfResultPoint[4].getX(), f1 + paramArrayOfResultPoint[4].getY());
      while (true)
      {
        f2 = paramArrayOfResultPoint[7].getY() - paramArrayOfResultPoint[5].getY();
        f1 = f2;
        if (paramBoolean)
          f1 = -f2;
        if (f1 <= 2.0F)
          break;
        f1 = paramArrayOfResultPoint[5].getX();
        f2 = paramArrayOfResultPoint[1].getX();
        f3 = paramArrayOfResultPoint[7].getX();
        f4 = paramArrayOfResultPoint[1].getX();
        f1 = (f1 - f2) * (paramArrayOfResultPoint[7].getY() - paramArrayOfResultPoint[1].getY()) / (f3 - f4);
        paramArrayOfResultPoint[5] = new ResultPoint(paramArrayOfResultPoint[5].getX(), f1 + paramArrayOfResultPoint[5].getY());
        return;
        if (-f1 > 2.0F)
        {
          f1 = paramArrayOfResultPoint[2].getX();
          f2 = paramArrayOfResultPoint[6].getX();
          f3 = paramArrayOfResultPoint[2].getX();
          f4 = paramArrayOfResultPoint[4].getX();
          f1 = (f1 - f2) * (paramArrayOfResultPoint[2].getY() - paramArrayOfResultPoint[4].getY()) / (f3 - f4);
          paramArrayOfResultPoint[6] = new ResultPoint(paramArrayOfResultPoint[6].getX(), paramArrayOfResultPoint[6].getY() - f1);
        }
      }
    }
    while (-f1 <= 2.0F);
    f1 = paramArrayOfResultPoint[3].getX();
    f2 = paramArrayOfResultPoint[7].getX();
    float f3 = paramArrayOfResultPoint[3].getX();
    float f4 = paramArrayOfResultPoint[5].getX();
    f1 = (f1 - f2) * (paramArrayOfResultPoint[3].getY() - paramArrayOfResultPoint[5].getY()) / (f3 - f4);
    paramArrayOfResultPoint[7] = new ResultPoint(paramArrayOfResultPoint[7].getX(), paramArrayOfResultPoint[7].getY() - f1);
  }

  private static int[] findGuardPattern(BitMatrix paramBitMatrix, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int[] paramArrayOfInt)
  {
    int n = paramArrayOfInt.length;
    int[] arrayOfInt = new int[n];
    int j = 0;
    int k = paramInt1;
    int i = paramInt1;
    if (k < paramInt1 + paramInt3)
    {
      int m;
      if ((paramBitMatrix.get(k, paramInt2) ^ paramBoolean))
      {
        arrayOfInt[j] += 1;
        m = j;
        j = i;
      }
      while (true)
      {
        k += 1;
        i = j;
        j = m;
        break;
        if (j == n - 1)
        {
          if (patternMatchVariance(arrayOfInt, paramArrayOfInt, 204) < 107)
            return new int[] { i, k };
          m = i + (arrayOfInt[0] + arrayOfInt[1]);
          i = 2;
          while (i < n)
          {
            arrayOfInt[(i - 2)] = arrayOfInt[i];
            i += 1;
          }
          arrayOfInt[(n - 2)] = 0;
          arrayOfInt[(n - 1)] = 0;
          i = j - 1;
          j = m;
        }
        while (true)
        {
          arrayOfInt[i] = 1;
          if (paramBoolean)
            break label225;
          paramBoolean = true;
          m = i;
          break;
          m = j + 1;
          j = i;
          i = m;
        }
        label225: paramBoolean = false;
        m = i;
      }
    }
    return null;
  }

  private static ResultPoint[] findVertices(BitMatrix paramBitMatrix)
  {
    int k = 0;
    int n = paramBitMatrix.getHeight();
    int m = paramBitMatrix.getWidth();
    ResultPoint[] arrayOfResultPoint = new ResultPoint[8];
    int i = 0;
    int[] arrayOfInt;
    if (i < n)
    {
      arrayOfInt = findGuardPattern(paramBitMatrix, 0, i, m, false, START_PATTERN);
      if (arrayOfInt != null)
      {
        arrayOfResultPoint[0] = new ResultPoint(arrayOfInt[0], i);
        arrayOfResultPoint[4] = new ResultPoint(arrayOfInt[1], i);
      }
    }
    for (int j = 1; ; j = 0)
    {
      i = j;
      if (j != 0)
      {
        i = n - 1;
        label97: if (i <= 0)
          break label350;
        arrayOfInt = findGuardPattern(paramBitMatrix, 0, i, m, false, START_PATTERN);
        if (arrayOfInt == null)
          break label317;
        arrayOfResultPoint[1] = new ResultPoint(arrayOfInt[0], i);
        arrayOfResultPoint[5] = new ResultPoint(arrayOfInt[1], i);
      }
      label166: label317: label324: label331: label345: label350: for (i = 1; ; i = 0)
      {
        j = i;
        if (i != 0)
        {
          i = 0;
          if (i >= n)
            break label345;
          arrayOfInt = findGuardPattern(paramBitMatrix, 0, i, m, false, STOP_PATTERN);
          if (arrayOfInt == null)
            break label324;
          arrayOfResultPoint[2] = new ResultPoint(arrayOfInt[1], i);
          arrayOfResultPoint[6] = new ResultPoint(arrayOfInt[0], i);
        }
        for (j = 1; ; j = 0)
        {
          if (j != 0)
          {
            j = n - 1;
            label239: i = k;
            if (j > 0)
            {
              arrayOfInt = findGuardPattern(paramBitMatrix, 0, j, m, false, STOP_PATTERN);
              if (arrayOfInt == null)
                break label331;
              arrayOfResultPoint[3] = new ResultPoint(arrayOfInt[1], j);
              arrayOfResultPoint[7] = new ResultPoint(arrayOfInt[0], j);
            }
          }
          for (i = 1; ; i = j)
          {
            if (i != 0)
            {
              return arrayOfResultPoint;
              i += 1;
              break;
              i -= 1;
              break label97;
              i += 1;
              break label166;
              j -= 1;
              break label239;
            }
            return null;
          }
        }
      }
    }
  }

  private static ResultPoint[] findVertices180(BitMatrix paramBitMatrix)
  {
    int k = 1;
    int m = paramBitMatrix.getHeight();
    int n = paramBitMatrix.getWidth() >> 1;
    ResultPoint[] arrayOfResultPoint = new ResultPoint[8];
    int i = m - 1;
    int[] arrayOfInt;
    if (i > 0)
    {
      arrayOfInt = findGuardPattern(paramBitMatrix, n, i, n, true, START_PATTERN_REVERSE);
      if (arrayOfInt != null)
      {
        arrayOfResultPoint[0] = new ResultPoint(arrayOfInt[1], i);
        arrayOfResultPoint[4] = new ResultPoint(arrayOfInt[0], i);
      }
    }
    for (int j = 1; ; j = 0)
    {
      i = j;
      if (j != 0)
      {
        i = 0;
        label98: if (i >= m)
          break label357;
        arrayOfInt = findGuardPattern(paramBitMatrix, n, i, n, true, START_PATTERN_REVERSE);
        if (arrayOfInt == null)
          break label319;
        arrayOfResultPoint[1] = new ResultPoint(arrayOfInt[1], i);
        arrayOfResultPoint[5] = new ResultPoint(arrayOfInt[0], i);
      }
      label173: label319: label326: label352: label357: for (i = 1; ; i = 0)
      {
        j = i;
        if (i != 0)
        {
          i = m - 1;
          if (i <= 0)
            break label352;
          arrayOfInt = findGuardPattern(paramBitMatrix, 0, i, n, false, STOP_PATTERN_REVERSE);
          if (arrayOfInt == null)
            break label326;
          arrayOfResultPoint[2] = new ResultPoint(arrayOfInt[0], i);
          arrayOfResultPoint[6] = new ResultPoint(arrayOfInt[1], i);
        }
        for (j = 1; ; j = 0)
        {
          if (j != 0)
          {
            i = 0;
            label241: if (i < m)
            {
              arrayOfInt = findGuardPattern(paramBitMatrix, 0, i, n, false, STOP_PATTERN_REVERSE);
              if (arrayOfInt != null)
              {
                arrayOfResultPoint[3] = new ResultPoint(arrayOfInt[0], i);
                arrayOfResultPoint[7] = new ResultPoint(arrayOfInt[1], i);
                i = k;
              }
            }
          }
          while (true)
          {
            if (i != 0)
            {
              return arrayOfResultPoint;
              i -= 1;
              break;
              i += 1;
              break label98;
              i -= 1;
              break label173;
              i += 1;
              break label241;
            }
            return null;
            i = 0;
            continue;
            i = j;
          }
        }
      }
    }
  }

  private static int patternMatchVariance(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt)
  {
    int n = paramArrayOfInt1.length;
    int j = 0;
    int k = 0;
    int i = 0;
    while (j < n)
    {
      i += paramArrayOfInt1[j];
      k += paramArrayOfInt2[j];
      j += 1;
    }
    if (i < k);
    label143: 
    while (true)
    {
      return 2147483647;
      int i1 = (i << 8) / k;
      j = 0;
      k = 0;
      if (j >= n)
        break;
      int m = paramArrayOfInt1[j] << 8;
      int i2 = paramArrayOfInt2[j] * i1;
      if (m > i2)
        m -= i2;
      while (true)
      {
        if (m > paramInt * i1 >> 8)
          break label143;
        k += m;
        j += 1;
        break;
        m = i2 - m;
      }
    }
    return k / i;
  }

  private static int round(float paramFloat)
  {
    return (int)(0.5F + paramFloat);
  }

  private static BitMatrix sampleGrid(BitMatrix paramBitMatrix, ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, ResultPoint paramResultPoint3, ResultPoint paramResultPoint4, int paramInt)
    throws NotFoundException
  {
    return GridSampler.getInstance().sampleGrid(paramBitMatrix, paramInt, 0.0F, 0.0F, paramInt, 0.0F, paramInt, paramInt, 0.0F, paramInt, paramResultPoint1.getX(), paramResultPoint1.getY(), paramResultPoint3.getX(), paramResultPoint3.getY(), paramResultPoint4.getX(), paramResultPoint4.getY(), paramResultPoint2.getX(), paramResultPoint2.getY());
  }

  public DetectorResult detect()
    throws NotFoundException
  {
    return detect(null);
  }

  public DetectorResult detect(Hashtable paramHashtable)
    throws NotFoundException
  {
    BitMatrix localBitMatrix = this.image.getBlackMatrix();
    paramHashtable = findVertices(localBitMatrix);
    Object localObject;
    if (paramHashtable == null)
    {
      localObject = findVertices180(localBitMatrix);
      paramHashtable = (Hashtable)localObject;
      if (localObject == null)
        break label54;
      correctCodeWordVertices((ResultPoint[])localObject, true);
    }
    while (localObject == null)
    {
      throw NotFoundException.getNotFoundInstance();
      correctCodeWordVertices(paramHashtable, false);
      label54: localObject = paramHashtable;
    }
    float f = computeModuleWidth((ResultPoint[])localObject);
    if (f < 1.0F)
      throw NotFoundException.getNotFoundInstance();
    int i = computeDimension(localObject[4], localObject[6], localObject[5], localObject[7], f);
    if (i < 1)
      throw NotFoundException.getNotFoundInstance();
    return new DetectorResult(sampleGrid(localBitMatrix, localObject[4], localObject[5], localObject[6], localObject[7], i), new ResultPoint[] { localObject[4], localObject[5], localObject[6], localObject[7] });
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.pdf417.detector.Detector
 * JD-Core Version:    0.6.2
 */