package com.google.zxing.oned.rss;

import com.google.zxing.NotFoundException;
import com.google.zxing.oned.OneDReader;

public abstract class AbstractRSSReader extends OneDReader
{
  private static final int MAX_AVG_VARIANCE = 51;
  private static final float MAX_FINDER_PATTERN_RATIO = 0.8928571F;
  private static final int MAX_INDIVIDUAL_VARIANCE = 102;
  private static final float MIN_FINDER_PATTERN_RATIO = 0.7916667F;
  protected final int[] dataCharacterCounters = new int[8];
  protected final int[] decodeFinderCounters = new int[4];
  protected final int[] evenCounts = new int[this.dataCharacterCounters.length / 2];
  protected final float[] evenRoundingErrors = new float[4];
  protected final int[] oddCounts = new int[this.dataCharacterCounters.length / 2];
  protected final float[] oddRoundingErrors = new float[4];

  protected static int count(int[] paramArrayOfInt)
  {
    int i = 0;
    int j = 0;
    while (i < paramArrayOfInt.length)
    {
      j += paramArrayOfInt[i];
      i += 1;
    }
    return j;
  }

  protected static void decrement(int[] paramArrayOfInt, float[] paramArrayOfFloat)
  {
    int j = 0;
    float f1 = paramArrayOfFloat[0];
    int i = 1;
    while (i < paramArrayOfInt.length)
    {
      float f2 = f1;
      if (paramArrayOfFloat[i] < f1)
      {
        f2 = paramArrayOfFloat[i];
        j = i;
      }
      i += 1;
      f1 = f2;
    }
    paramArrayOfInt[j] -= 1;
  }

  protected static void increment(int[] paramArrayOfInt, float[] paramArrayOfFloat)
  {
    int j = 0;
    float f1 = paramArrayOfFloat[0];
    int i = 1;
    while (i < paramArrayOfInt.length)
    {
      float f2 = f1;
      if (paramArrayOfFloat[i] > f1)
      {
        f2 = paramArrayOfFloat[i];
        j = i;
      }
      i += 1;
      f1 = f2;
    }
    paramArrayOfInt[j] += 1;
  }

  protected static boolean isFinderPattern(int[] paramArrayOfInt)
  {
    boolean bool2 = false;
    int i = paramArrayOfInt[0] + paramArrayOfInt[1];
    int j = paramArrayOfInt[2];
    int k = paramArrayOfInt[3];
    float f = i / (j + i + k);
    boolean bool1 = bool2;
    if (f >= 0.7916667F)
    {
      bool1 = bool2;
      if (f <= 0.8928571F)
      {
        i = 2147483647;
        int m = -2147483648;
        j = 0;
        while (j < paramArrayOfInt.length)
        {
          int n = paramArrayOfInt[j];
          k = m;
          if (n > m)
            k = n;
          int i1 = i;
          if (n < i)
            i1 = n;
          j += 1;
          m = k;
          i = i1;
        }
        bool1 = bool2;
        if (m < i * 10)
          bool1 = true;
      }
    }
    return bool1;
  }

  protected static int parseFinderValue(int[] paramArrayOfInt, int[][] paramArrayOfInt1)
    throws NotFoundException
  {
    int i = 0;
    while (i < paramArrayOfInt1.length)
    {
      if (patternMatchVariance(paramArrayOfInt, paramArrayOfInt1[i], 102) < 51)
        return i;
      i += 1;
    }
    throw NotFoundException.getNotFoundInstance();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.rss.AbstractRSSReader
 * JD-Core Version:    0.6.2
 */