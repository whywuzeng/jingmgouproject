package com.google.zxing.common;

import com.google.zxing.NotFoundException;

public abstract class GridSampler
{
  private static GridSampler gridSampler = new DefaultGridSampler();

  protected static void checkAndNudgePoints(BitMatrix paramBitMatrix, float[] paramArrayOfFloat)
    throws NotFoundException
  {
    int k = paramBitMatrix.getWidth();
    int m = paramBitMatrix.getHeight();
    int j = 0;
    int i = 1;
    int n;
    if ((j < paramArrayOfFloat.length) && (i != 0))
    {
      i = (int)paramArrayOfFloat[j];
      n = (int)paramArrayOfFloat[(j + 1)];
      if ((i < -1) || (i > k) || (n < -1) || (n > m))
        throw NotFoundException.getNotFoundInstance();
      if (i == -1)
      {
        paramArrayOfFloat[j] = 0.0F;
        i = 1;
      }
    }
    while (true)
    {
      label78: if (n == -1)
      {
        paramArrayOfFloat[(j + 1)] = 0.0F;
        i = 1;
      }
      while (true)
      {
        j += 2;
        break;
        if (i != k)
          break label275;
        paramArrayOfFloat[j] = (k - 1);
        i = 1;
        break label78;
        if (n == m)
        {
          paramArrayOfFloat[(j + 1)] = (m - 1);
          i = 1;
        }
      }
      j = paramArrayOfFloat.length - 2;
      i = 1;
      if ((j >= 0) && (i != 0))
      {
        i = (int)paramArrayOfFloat[j];
        n = (int)paramArrayOfFloat[(j + 1)];
        if ((i < -1) || (i > k) || (n < -1) || (n > m))
          throw NotFoundException.getNotFoundInstance();
        if (i == -1)
        {
          paramArrayOfFloat[j] = 0.0F;
          i = 1;
        }
      }
      while (true)
      {
        label207: if (n == -1)
        {
          paramArrayOfFloat[(j + 1)] = 0.0F;
          i = 1;
        }
        while (true)
        {
          j -= 2;
          break;
          if (i != k)
            break label270;
          paramArrayOfFloat[j] = (k - 1);
          i = 1;
          break label207;
          if (n == m)
          {
            paramArrayOfFloat[(j + 1)] = (m - 1);
            i = 1;
          }
        }
        return;
        label270: i = 0;
      }
      label275: i = 0;
    }
  }

  public static GridSampler getInstance()
  {
    return gridSampler;
  }

  public static void setGridSampler(GridSampler paramGridSampler)
  {
    if (paramGridSampler == null)
      throw new IllegalArgumentException();
    gridSampler = paramGridSampler;
  }

  public abstract BitMatrix sampleGrid(BitMatrix paramBitMatrix, int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, float paramFloat11, float paramFloat12, float paramFloat13, float paramFloat14, float paramFloat15, float paramFloat16)
    throws NotFoundException;

  public BitMatrix sampleGrid(BitMatrix paramBitMatrix, int paramInt, PerspectiveTransform paramPerspectiveTransform)
    throws NotFoundException
  {
    throw new IllegalStateException();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.common.GridSampler
 * JD-Core Version:    0.6.2
 */