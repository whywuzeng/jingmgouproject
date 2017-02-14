package com.google.zxing.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.Collections;
import com.google.zxing.common.Comparator;
import java.util.Hashtable;
import java.util.Vector;

public class FinderPatternFinder
{
  private static final int CENTER_QUORUM = 2;
  private static final int INTEGER_MATH_SHIFT = 8;
  protected static final int MAX_MODULES = 57;
  protected static final int MIN_SKIP = 3;
  private final int[] crossCheckStateCount;
  private boolean hasSkipped;
  private final BitMatrix image;
  private final Vector possibleCenters;
  private final ResultPointCallback resultPointCallback;

  public FinderPatternFinder(BitMatrix paramBitMatrix)
  {
    this(paramBitMatrix, null);
  }

  public FinderPatternFinder(BitMatrix paramBitMatrix, ResultPointCallback paramResultPointCallback)
  {
    this.image = paramBitMatrix;
    this.possibleCenters = new Vector();
    this.crossCheckStateCount = new int[5];
    this.resultPointCallback = paramResultPointCallback;
  }

  private static float centerFromEnd(int[] paramArrayOfInt, int paramInt)
  {
    return paramInt - paramArrayOfInt[4] - paramArrayOfInt[3] - paramArrayOfInt[2] / 2.0F;
  }

  private float crossCheckHorizontal(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    BitMatrix localBitMatrix = this.image;
    int k = localBitMatrix.getWidth();
    int[] arrayOfInt = getCrossCheckStateCount();
    int i = paramInt1;
    while ((i >= 0) && (localBitMatrix.get(i, paramInt2)))
    {
      arrayOfInt[2] += 1;
      i -= 1;
    }
    int j = i;
    if (i < 0);
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              return (0.0F / 0.0F);
              while ((j >= 0) && (!localBitMatrix.get(j, paramInt2)) && (arrayOfInt[1] <= paramInt3))
              {
                arrayOfInt[1] += 1;
                j -= 1;
              }
            }
            while ((j < 0) || (arrayOfInt[1] > paramInt3));
            while ((j >= 0) && (localBitMatrix.get(j, paramInt2)) && (arrayOfInt[0] <= paramInt3))
            {
              arrayOfInt[0] += 1;
              j -= 1;
            }
          }
          while (arrayOfInt[0] > paramInt3);
          paramInt1 += 1;
          while ((paramInt1 < k) && (localBitMatrix.get(paramInt1, paramInt2)))
          {
            arrayOfInt[2] += 1;
            paramInt1 += 1;
          }
        }
        while (paramInt1 == k);
        while ((paramInt1 < k) && (!localBitMatrix.get(paramInt1, paramInt2)) && (arrayOfInt[3] < paramInt3))
        {
          arrayOfInt[3] += 1;
          paramInt1 += 1;
        }
      }
      while ((paramInt1 == k) || (arrayOfInt[3] >= paramInt3));
      while ((paramInt1 < k) && (localBitMatrix.get(paramInt1, paramInt2)) && (arrayOfInt[4] < paramInt3))
      {
        arrayOfInt[4] += 1;
        paramInt1 += 1;
      }
    }
    while ((arrayOfInt[4] >= paramInt3) || (Math.abs(arrayOfInt[0] + arrayOfInt[1] + arrayOfInt[2] + arrayOfInt[3] + arrayOfInt[4] - paramInt4) * 5 >= paramInt4) || (!foundPatternCross(arrayOfInt)));
    return centerFromEnd(arrayOfInt, paramInt1);
  }

  private float crossCheckVertical(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    BitMatrix localBitMatrix = this.image;
    int k = localBitMatrix.getHeight();
    int[] arrayOfInt = getCrossCheckStateCount();
    int i = paramInt1;
    while ((i >= 0) && (localBitMatrix.get(paramInt2, i)))
    {
      arrayOfInt[2] += 1;
      i -= 1;
    }
    int j = i;
    if (i < 0);
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              return (0.0F / 0.0F);
              while ((j >= 0) && (!localBitMatrix.get(paramInt2, j)) && (arrayOfInt[1] <= paramInt3))
              {
                arrayOfInt[1] += 1;
                j -= 1;
              }
            }
            while ((j < 0) || (arrayOfInt[1] > paramInt3));
            while ((j >= 0) && (localBitMatrix.get(paramInt2, j)) && (arrayOfInt[0] <= paramInt3))
            {
              arrayOfInt[0] += 1;
              j -= 1;
            }
          }
          while (arrayOfInt[0] > paramInt3);
          paramInt1 += 1;
          while ((paramInt1 < k) && (localBitMatrix.get(paramInt2, paramInt1)))
          {
            arrayOfInt[2] += 1;
            paramInt1 += 1;
          }
        }
        while (paramInt1 == k);
        while ((paramInt1 < k) && (!localBitMatrix.get(paramInt2, paramInt1)) && (arrayOfInt[3] < paramInt3))
        {
          arrayOfInt[3] += 1;
          paramInt1 += 1;
        }
      }
      while ((paramInt1 == k) || (arrayOfInt[3] >= paramInt3));
      while ((paramInt1 < k) && (localBitMatrix.get(paramInt2, paramInt1)) && (arrayOfInt[4] < paramInt3))
      {
        arrayOfInt[4] += 1;
        paramInt1 += 1;
      }
    }
    while ((arrayOfInt[4] >= paramInt3) || (Math.abs(arrayOfInt[0] + arrayOfInt[1] + arrayOfInt[2] + arrayOfInt[3] + arrayOfInt[4] - paramInt4) * 5 >= paramInt4 * 2) || (!foundPatternCross(arrayOfInt)));
    return centerFromEnd(arrayOfInt, paramInt1);
  }

  private int findRowSkip()
  {
    int j = this.possibleCenters.size();
    if (j <= 1)
      return 0;
    Object localObject = null;
    int i = 0;
    FinderPattern localFinderPattern;
    if (i < j)
    {
      localFinderPattern = (FinderPattern)this.possibleCenters.elementAt(i);
      if (localFinderPattern.getCount() < 2)
        break label98;
      if (localObject == null)
        localObject = localFinderPattern;
    }
    label98: 
    while (true)
    {
      i += 1;
      break;
      this.hasSkipped = true;
      return (int)(Math.abs(localObject.getX() - localFinderPattern.getX()) - Math.abs(localObject.getY() - localFinderPattern.getY())) / 2;
      return 0;
    }
  }

  protected static boolean foundPatternCross(int[] paramArrayOfInt)
  {
    boolean bool = true;
    int i = 0;
    int j = 0;
    if (i < 5)
    {
      k = paramArrayOfInt[i];
      if (k != 0);
    }
    while (j < 7)
    {
      int k;
      return false;
      j += k;
      i += 1;
      break;
    }
    i = (j << 8) / 7;
    j = i / 2;
    if ((Math.abs(i - (paramArrayOfInt[0] << 8)) < j) && (Math.abs(i - (paramArrayOfInt[1] << 8)) < j) && (Math.abs(i * 3 - (paramArrayOfInt[2] << 8)) < j * 3) && (Math.abs(i - (paramArrayOfInt[3] << 8)) < j) && (Math.abs(i - (paramArrayOfInt[4] << 8)) < j));
    while (true)
    {
      return bool;
      bool = false;
    }
  }

  private int[] getCrossCheckStateCount()
  {
    this.crossCheckStateCount[0] = 0;
    this.crossCheckStateCount[1] = 0;
    this.crossCheckStateCount[2] = 0;
    this.crossCheckStateCount[3] = 0;
    this.crossCheckStateCount[4] = 0;
    return this.crossCheckStateCount;
  }

  private boolean haveMultiplyConfirmedCenters()
  {
    float f2 = 0.0F;
    int k = this.possibleCenters.size();
    int j = 0;
    float f1 = 0.0F;
    int i = 0;
    if (j < k)
    {
      FinderPattern localFinderPattern = (FinderPattern)this.possibleCenters.elementAt(j);
      if (localFinderPattern.getCount() < 2)
        break label149;
      f1 = localFinderPattern.getEstimatedModuleSize() + f1;
      i += 1;
    }
    label149: 
    while (true)
    {
      j += 1;
      break;
      if (i < 3)
        return false;
      float f3 = f1 / k;
      i = 0;
      while (i < k)
      {
        f2 += Math.abs(((FinderPattern)this.possibleCenters.elementAt(i)).getEstimatedModuleSize() - f3);
        i += 1;
      }
      if (f2 <= 0.05F * f1);
      for (boolean bool = true; ; bool = false)
        return bool;
    }
  }

  private FinderPattern[] selectBestPatterns()
    throws NotFoundException
  {
    float f3 = 0.0F;
    int j = this.possibleCenters.size();
    if (j < 3)
      throw NotFoundException.getNotFoundInstance();
    int i;
    float f1;
    if (j > 3)
    {
      i = 0;
      f1 = 0.0F;
      float f2 = 0.0F;
      while (i < j)
      {
        float f4 = ((FinderPattern)this.possibleCenters.elementAt(i)).getEstimatedModuleSize();
        f2 += f4;
        f1 += f4 * f4;
        i += 1;
      }
      f2 /= j;
      f1 = (float)Math.sqrt(f1 / j - f2 * f2);
      Collections.insertionSort(this.possibleCenters, new FurthestFromAverageComparator(f2));
      f1 = Math.max(0.2F * f2, f1);
      for (i = 0; (i < this.possibleCenters.size()) && (this.possibleCenters.size() > 3); i = j + 1)
      {
        j = i;
        if (Math.abs(((FinderPattern)this.possibleCenters.elementAt(i)).getEstimatedModuleSize() - f2) > f1)
        {
          this.possibleCenters.removeElementAt(i);
          j = i - 1;
        }
      }
    }
    if (this.possibleCenters.size() > 3)
    {
      i = 0;
      f1 = f3;
      while (i < this.possibleCenters.size())
      {
        f1 += ((FinderPattern)this.possibleCenters.elementAt(i)).getEstimatedModuleSize();
        i += 1;
      }
      f1 /= this.possibleCenters.size();
      Collections.insertionSort(this.possibleCenters, new CenterComparator(f1));
      this.possibleCenters.setSize(3);
    }
    return new FinderPattern[] { (FinderPattern)this.possibleCenters.elementAt(0), (FinderPattern)this.possibleCenters.elementAt(1), (FinderPattern)this.possibleCenters.elementAt(2) };
  }

  FinderPatternInfo find(Hashtable paramHashtable)
    throws NotFoundException
  {
    int i1;
    int i2;
    int j;
    if ((paramHashtable != null) && (paramHashtable.containsKey(DecodeHintType.TRY_HARDER)))
    {
      i = 1;
      i1 = this.image.getHeight();
      i2 = this.image.getWidth();
      j = i1 * 3 / 228;
      if ((j >= 3) && (i == 0))
        break label459;
    }
    label156: label305: label451: label459: for (int i = 3; ; i = j)
    {
      paramHashtable = new int[5];
      int k = i - 1;
      boolean bool1 = false;
      int m;
      label210: if ((k < i1) && (!bool1))
      {
        paramHashtable[0] = 0;
        paramHashtable[1] = 0;
        paramHashtable[2] = 0;
        paramHashtable[3] = 0;
        paramHashtable[4] = 0;
        j = 0;
        int n = 0;
        m = i;
        i = n;
        label109: if (j < i2)
        {
          if (this.image.get(j, k))
          {
            n = i;
            if ((i & 0x1) == 1)
              n = i + 1;
            paramHashtable[n] += 1;
          }
          for (i = n; ; i = 0)
          {
            j += 1;
            break label109;
            i = 0;
            break;
            if ((i & 0x1) != 0)
              break label351;
            if (i != 4)
              break label336;
            if (!foundPatternCross(paramHashtable))
              break label305;
            if (!handlePossibleCenter(paramHashtable, k, j))
              break label274;
            if (!this.hasSkipped)
              break label238;
            bool1 = haveMultiplyConfirmedCenters();
            paramHashtable[0] = 0;
            paramHashtable[1] = 0;
            paramHashtable[2] = 0;
            paramHashtable[3] = 0;
            paramHashtable[4] = 0;
            m = 2;
          }
          label238: i = findRowSkip();
          if (i <= paramHashtable[2])
            break label451;
          j = k + (i - paramHashtable[2] - 2);
          i = i2 - 1;
        }
      }
      while (true)
      {
        k = j;
        j = i;
        break label210;
        paramHashtable[0] = paramHashtable[2];
        paramHashtable[1] = paramHashtable[3];
        paramHashtable[2] = paramHashtable[4];
        paramHashtable[3] = 1;
        paramHashtable[4] = 0;
        i = 3;
        break label156;
        paramHashtable[0] = paramHashtable[2];
        paramHashtable[1] = paramHashtable[3];
        paramHashtable[2] = paramHashtable[4];
        paramHashtable[3] = 1;
        paramHashtable[4] = 0;
        i = 3;
        break label156;
        label336: i += 1;
        paramHashtable[i] += 1;
        break label156;
        label351: paramHashtable[i] += 1;
        break label156;
        boolean bool2 = bool1;
        i = m;
        if (foundPatternCross(paramHashtable))
        {
          bool2 = bool1;
          i = m;
          if (handlePossibleCenter(paramHashtable, k, i2))
          {
            j = paramHashtable[0];
            bool2 = bool1;
            i = j;
            if (this.hasSkipped)
            {
              bool2 = haveMultiplyConfirmedCenters();
              i = j;
            }
          }
        }
        k += i;
        bool1 = bool2;
        break;
        paramHashtable = selectBestPatterns();
        ResultPoint.orderBestPatterns(paramHashtable);
        return new FinderPatternInfo(paramHashtable);
        i = j;
        j = k;
      }
    }
  }

  protected BitMatrix getImage()
  {
    return this.image;
  }

  protected Vector getPossibleCenters()
  {
    return this.possibleCenters;
  }

  protected boolean handlePossibleCenter(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    int i = 0;
    int j = paramArrayOfInt[0] + paramArrayOfInt[1] + paramArrayOfInt[2] + paramArrayOfInt[3] + paramArrayOfInt[4];
    float f2 = centerFromEnd(paramArrayOfInt, paramInt2);
    float f1 = crossCheckVertical(paramInt1, (int)f2, paramArrayOfInt[2], j);
    if (!Float.isNaN(f1))
    {
      f2 = crossCheckHorizontal((int)f2, (int)f1, paramArrayOfInt[2], j);
      if (!Float.isNaN(f2))
      {
        float f3 = j / 7.0F;
        j = this.possibleCenters.size();
        paramInt1 = 0;
        while (true)
        {
          paramInt2 = i;
          if (paramInt1 < j)
          {
            paramArrayOfInt = (FinderPattern)this.possibleCenters.elementAt(paramInt1);
            if (paramArrayOfInt.aboutEquals(f3, f1, f2))
            {
              paramArrayOfInt.incrementCount();
              paramInt2 = 1;
            }
          }
          else
          {
            if (paramInt2 == 0)
            {
              paramArrayOfInt = new FinderPattern(f2, f1, f3);
              this.possibleCenters.addElement(paramArrayOfInt);
              if (this.resultPointCallback != null)
                this.resultPointCallback.foundPossibleResultPoint(paramArrayOfInt);
            }
            return true;
          }
          paramInt1 += 1;
        }
      }
    }
    return false;
  }

  private static class CenterComparator
    implements Comparator
  {
    private final float average;

    public CenterComparator(float paramFloat)
    {
      this.average = paramFloat;
    }

    public int compare(Object paramObject1, Object paramObject2)
    {
      if (((FinderPattern)paramObject2).getCount() != ((FinderPattern)paramObject1).getCount())
        return ((FinderPattern)paramObject2).getCount() - ((FinderPattern)paramObject1).getCount();
      float f1 = Math.abs(((FinderPattern)paramObject2).getEstimatedModuleSize() - this.average);
      float f2 = Math.abs(((FinderPattern)paramObject1).getEstimatedModuleSize() - this.average);
      if (f1 < f2)
        return 1;
      if (f1 == f2)
        return 0;
      return -1;
    }
  }

  private static class FurthestFromAverageComparator
    implements Comparator
  {
    private final float average;

    public FurthestFromAverageComparator(float paramFloat)
    {
      this.average = paramFloat;
    }

    public int compare(Object paramObject1, Object paramObject2)
    {
      float f1 = Math.abs(((FinderPattern)paramObject2).getEstimatedModuleSize() - this.average);
      float f2 = Math.abs(((FinderPattern)paramObject1).getEstimatedModuleSize() - this.average);
      if (f1 < f2)
        return -1;
      if (f1 == f2)
        return 0;
      return 1;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.detector.FinderPatternFinder
 * JD-Core Version:    0.6.2
 */