package com.google.zxing.common;

import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import java.lang.reflect.Array;

public final class HybridBinarizer extends GlobalHistogramBinarizer
{
  private static final int MINIMUM_DIMENSION = 40;
  private BitMatrix matrix = null;

  public HybridBinarizer(LuminanceSource paramLuminanceSource)
  {
    super(paramLuminanceSource);
  }

  private void binarizeEntireImage()
    throws NotFoundException
  {
    if (this.matrix == null)
    {
      Object localObject = getLuminanceSource();
      if ((((LuminanceSource)localObject).getWidth() >= 40) && (((LuminanceSource)localObject).getHeight() >= 40))
      {
        byte[] arrayOfByte = ((LuminanceSource)localObject).getMatrix();
        int m = ((LuminanceSource)localObject).getWidth();
        int n = ((LuminanceSource)localObject).getHeight();
        int j = m >> 3;
        int i = j;
        if ((m & 0x7) != 0)
          i = j + 1;
        int k = n >> 3;
        j = k;
        if ((n & 0x7) != 0)
          j = k + 1;
        localObject = calculateBlackPoints(arrayOfByte, i, j, m, n);
        this.matrix = new BitMatrix(m, n);
        calculateThresholdForBlock(arrayOfByte, i, j, m, n, (int[][])localObject, this.matrix);
      }
    }
    else
    {
      return;
    }
    this.matrix = super.getBlackMatrix();
  }

  private static int[][] calculateBlackPoints(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int[][] arrayOfInt = (int[][])Array.newInstance(Integer.TYPE, new int[] { paramInt2, paramInt1 });
    int k = 0;
    int i;
    int n;
    int i4;
    int i5;
    int i2;
    label106: int i3;
    int j;
    label124: int i6;
    if (k < paramInt2)
    {
      i = k << 3;
      int m = i;
      if (i + 8 >= paramInt4)
        m = paramInt4 - 8;
      n = 0;
      if (n < paramInt1)
      {
        i = n << 3;
        int i1 = i;
        if (i + 8 >= paramInt3)
          i1 = paramInt3 - 8;
        i = 0;
        i4 = 255;
        i5 = 0;
        i2 = 0;
        if (i2 < 8)
        {
          i3 = 0;
          j = i;
          i = i5;
          if (i3 < 8)
          {
            i5 = paramArrayOfByte[((m + i2) * paramInt3 + i1 + i3)] & 0xFF;
            i6 = j + i5;
            j = i4;
            if (i5 < i4)
              j = i5;
            if (i5 <= i)
              break label287;
            i = i5;
          }
        }
      }
    }
    label287: 
    while (true)
    {
      i3 += 1;
      i4 = j;
      j = i6;
      break label124;
      i2 += 1;
      i5 = i;
      i = j;
      break label106;
      if (i5 - i4 > 24)
        i >>= 6;
      while (true)
      {
        arrayOfInt[k][n] = i;
        n += 1;
        break;
        if (i5 == 0)
          i = 1;
        else
          i = i4 >> 1;
      }
      k += 1;
      break;
      return arrayOfInt;
    }
  }

  private static void calculateThresholdForBlock(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[][] paramArrayOfInt, BitMatrix paramBitMatrix)
  {
    int i = 0;
    while (i < paramInt2)
    {
      int j = i << 3;
      int m = j;
      if (j + 8 >= paramInt4)
        m = paramInt4 - 8;
      j = 0;
      while (j < paramInt1)
      {
        int k = j << 3;
        int n = k;
        if (k + 8 >= paramInt3)
          n = paramInt3 - 8;
        int i1;
        if (j > 1)
        {
          k = j;
          if (k >= paramInt1 - 2)
            break label190;
          i1 = k;
          label92: if (i <= 1)
            break label198;
          k = i;
          label102: if (k >= paramInt2 - 2)
            break label204;
        }
        int i3;
        while (true)
        {
          i3 = 0;
          int i2 = -2;
          while (i2 <= 2)
          {
            int[] arrayOfInt = paramArrayOfInt[(k + i2)];
            i3 = i3 + arrayOfInt[(i1 - 2)] + arrayOfInt[(i1 - 1)] + arrayOfInt[i1] + arrayOfInt[(i1 + 1)] + arrayOfInt[(i1 + 2)];
            i2 += 1;
          }
          k = 2;
          break;
          label190: i1 = paramInt1 - 3;
          break label92;
          label198: k = 2;
          break label102;
          label204: k = paramInt2 - 3;
        }
        threshold8x8Block(paramArrayOfByte, n, m, i3 / 25, paramInt3, paramBitMatrix);
        j += 1;
      }
      i += 1;
    }
  }

  private static void threshold8x8Block(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4, BitMatrix paramBitMatrix)
  {
    int i = 0;
    while (i < 8)
    {
      int j = 0;
      while (j < 8)
      {
        if ((paramArrayOfByte[((paramInt2 + i) * paramInt4 + paramInt1 + j)] & 0xFF) < paramInt3)
          paramBitMatrix.set(paramInt1 + j, paramInt2 + i);
        j += 1;
      }
      i += 1;
    }
  }

  public Binarizer createBinarizer(LuminanceSource paramLuminanceSource)
  {
    return new HybridBinarizer(paramLuminanceSource);
  }

  public BitMatrix getBlackMatrix()
    throws NotFoundException
  {
    binarizeEntireImage();
    return this.matrix;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.common.HybridBinarizer
 * JD-Core Version:    0.6.2
 */