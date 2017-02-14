package com.google.zxing.common;

import com.google.zxing.NotFoundException;

public final class DefaultGridSampler extends GridSampler
{
  public BitMatrix sampleGrid(BitMatrix paramBitMatrix, int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8, float paramFloat9, float paramFloat10, float paramFloat11, float paramFloat12, float paramFloat13, float paramFloat14, float paramFloat15, float paramFloat16)
    throws NotFoundException
  {
    return sampleGrid(paramBitMatrix, paramInt, PerspectiveTransform.quadrilateralToQuadrilateral(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramFloat5, paramFloat6, paramFloat7, paramFloat8, paramFloat9, paramFloat10, paramFloat11, paramFloat12, paramFloat13, paramFloat14, paramFloat15, paramFloat16));
  }

  public BitMatrix sampleGrid(BitMatrix paramBitMatrix, int paramInt, PerspectiveTransform paramPerspectiveTransform)
    throws NotFoundException
  {
    BitMatrix localBitMatrix = new BitMatrix(paramInt);
    float[] arrayOfFloat = new float[paramInt << 1];
    int i = 0;
    while (i < paramInt)
    {
      int k = arrayOfFloat.length;
      float f = i;
      int j = 0;
      while (j < k)
      {
        arrayOfFloat[j] = ((j >> 1) + 0.5F);
        arrayOfFloat[(j + 1)] = (f + 0.5F);
        j += 2;
      }
      paramPerspectiveTransform.transformPoints(arrayOfFloat);
      checkAndNudgePoints(paramBitMatrix, arrayOfFloat);
      j = 0;
      while (j < k)
        try
        {
          if (paramBitMatrix.get((int)arrayOfFloat[j], (int)arrayOfFloat[(j + 1)]))
            localBitMatrix.set(j >> 1, i);
          j += 2;
        }
        catch (ArrayIndexOutOfBoundsException paramBitMatrix)
        {
          throw NotFoundException.getNotFoundInstance();
        }
      i += 1;
    }
    return localBitMatrix;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.common.DefaultGridSampler
 * JD-Core Version:    0.6.2
 */