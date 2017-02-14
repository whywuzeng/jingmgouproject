package com.google.zxing.multi.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.Collections;
import com.google.zxing.common.Comparator;
import com.google.zxing.qrcode.detector.FinderPattern;
import com.google.zxing.qrcode.detector.FinderPatternFinder;
import com.google.zxing.qrcode.detector.FinderPatternInfo;
import java.util.Hashtable;
import java.util.Vector;

final class MultiFinderPatternFinder extends FinderPatternFinder
{
  private static final float DIFF_MODSIZE_CUTOFF = 0.5F;
  private static final float DIFF_MODSIZE_CUTOFF_PERCENT = 0.05F;
  private static final FinderPatternInfo[] EMPTY_RESULT_ARRAY = new FinderPatternInfo[0];
  private static final float MAX_MODULE_COUNT_PER_EDGE = 180.0F;
  private static final float MIN_MODULE_COUNT_PER_EDGE = 9.0F;

  MultiFinderPatternFinder(BitMatrix paramBitMatrix)
  {
    super(paramBitMatrix);
  }

  MultiFinderPatternFinder(BitMatrix paramBitMatrix, ResultPointCallback paramResultPointCallback)
  {
    super(paramBitMatrix, paramResultPointCallback);
  }

  private FinderPattern[][] selectBestPatterns()
    throws NotFoundException
  {
    Object localObject1 = getPossibleCenters();
    int m = ((Vector)localObject1).size();
    if (m < 3)
      throw NotFoundException.getNotFoundInstance();
    if (m == 3)
      return new FinderPattern[][] { { (FinderPattern)((Vector)localObject1).elementAt(0), (FinderPattern)((Vector)localObject1).elementAt(1), (FinderPattern)((Vector)localObject1).elementAt(2) } };
    Collections.insertionSort((Vector)localObject1, new ModuleSizeComparator(null));
    Vector localVector = new Vector();
    int i = 0;
    if (i < m - 2)
    {
      FinderPattern localFinderPattern1 = (FinderPattern)((Vector)localObject1).elementAt(i);
      if (localFinderPattern1 == null);
      int j;
      FinderPattern localFinderPattern2;
      label178: float f1;
      do
      {
        while (true)
        {
          i += 1;
          break;
          j = i + 1;
          while (j < m - 1)
          {
            localFinderPattern2 = (FinderPattern)((Vector)localObject1).elementAt(j);
            if (localFinderPattern2 != null)
              break label178;
            j += 1;
          }
        }
        f1 = (localFinderPattern1.getEstimatedModuleSize() - localFinderPattern2.getEstimatedModuleSize()) / Math.min(localFinderPattern1.getEstimatedModuleSize(), localFinderPattern2.getEstimatedModuleSize());
      }
      while ((Math.abs(localFinderPattern1.getEstimatedModuleSize() - localFinderPattern2.getEstimatedModuleSize()) > 0.5F) && (f1 >= 0.05F));
      int k = j + 1;
      label237: Object localObject2;
      if (k < m)
      {
        localObject2 = (FinderPattern)((Vector)localObject1).elementAt(k);
        if (localObject2 != null)
          break label270;
      }
      while (true)
      {
        k += 1;
        break label237;
        break;
        label270: f1 = (localFinderPattern2.getEstimatedModuleSize() - ((FinderPattern)localObject2).getEstimatedModuleSize()) / Math.min(localFinderPattern2.getEstimatedModuleSize(), ((FinderPattern)localObject2).getEstimatedModuleSize());
        if ((Math.abs(localFinderPattern2.getEstimatedModuleSize() - ((FinderPattern)localObject2).getEstimatedModuleSize()) > 0.5F) && (f1 >= 0.05F))
          break;
        FinderPattern[] arrayOfFinderPattern = new FinderPattern[3];
        arrayOfFinderPattern[0] = localFinderPattern1;
        arrayOfFinderPattern[1] = localFinderPattern2;
        arrayOfFinderPattern[2] = localObject2;
        ResultPoint.orderBestPatterns(arrayOfFinderPattern);
        localObject2 = new FinderPatternInfo(arrayOfFinderPattern);
        float f2 = ResultPoint.distance(((FinderPatternInfo)localObject2).getTopLeft(), ((FinderPatternInfo)localObject2).getBottomLeft());
        f1 = ResultPoint.distance(((FinderPatternInfo)localObject2).getTopRight(), ((FinderPatternInfo)localObject2).getBottomLeft());
        float f3 = ResultPoint.distance(((FinderPatternInfo)localObject2).getTopLeft(), ((FinderPatternInfo)localObject2).getTopRight());
        float f4 = (f2 + f3) / localFinderPattern1.getEstimatedModuleSize() / 2.0F;
        if ((f4 <= 180.0F) && (f4 >= 9.0F) && (Math.abs((f2 - f3) / Math.min(f2, f3)) < 0.1F))
        {
          f2 = (float)Math.sqrt(f3 * f3 + f2 * f2);
          if (Math.abs((f1 - f2) / Math.min(f1, f2)) < 0.1F)
            localVector.addElement(arrayOfFinderPattern);
        }
      }
    }
    if (!localVector.isEmpty())
    {
      localObject1 = new FinderPattern[localVector.size()][];
      i = 0;
      while (i < localVector.size())
      {
        localObject1[i] = ((FinderPattern[])(FinderPattern[])localVector.elementAt(i));
        i += 1;
      }
      return localObject1;
    }
    throw NotFoundException.getNotFoundInstance();
  }

  public FinderPatternInfo[] findMulti(Hashtable paramHashtable)
    throws NotFoundException
  {
    int i1 = 0;
    int i;
    int i2;
    int i3;
    int m;
    if ((paramHashtable != null) && (paramHashtable.containsKey(DecodeHintType.TRY_HARDER)))
    {
      i = 1;
      paramHashtable = getImage();
      i2 = paramHashtable.getHeight();
      i3 = paramHashtable.getWidth();
      m = (int)(i2 / 228.0F * 3.0F);
      if ((m >= 3) && (i == 0))
        break label461;
      m = 3;
    }
    label154: label458: label461: 
    while (true)
    {
      Object localObject = new int[5];
      int n = m - 1;
      int j;
      if (n < i2)
      {
        localObject[0] = 0;
        localObject[1] = 0;
        localObject[2] = 0;
        localObject[3] = 0;
        localObject[4] = 0;
        i = 0;
        j = 0;
        while (true)
          if (i < i3)
          {
            if (paramHashtable.get(i, n))
            {
              int k = j;
              if ((j & 0x1) == 1)
                k = j + 1;
              localObject[k] += 1;
              j = k;
              i += 1;
              continue;
              i = 0;
              break;
            }
            if ((j & 0x1) == 0)
              if (j == 4)
                if (foundPatternCross((int[])localObject))
                {
                  if (handlePossibleCenter((int[])localObject, n, i))
                    break label458;
                  do
                  {
                    j = i + 1;
                    if (j >= i3)
                      break;
                    i = j;
                  }
                  while (!paramHashtable.get(j, n));
                  i = j - 1;
                }
          }
      }
      while (true)
      {
        localObject[0] = 0;
        localObject[1] = 0;
        localObject[2] = 0;
        localObject[3] = 0;
        localObject[4] = 0;
        j = 0;
        break label154;
        localObject[0] = localObject[2];
        localObject[1] = localObject[3];
        localObject[2] = localObject[4];
        localObject[3] = 1;
        localObject[4] = 0;
        j = 3;
        break label154;
        j += 1;
        localObject[j] += 1;
        break label154;
        localObject[j] += 1;
        break label154;
        if (foundPatternCross((int[])localObject))
          handlePossibleCenter((int[])localObject, n, i3);
        n += m;
        break;
        localObject = selectBestPatterns();
        paramHashtable = new Vector();
        i = 0;
        while (i < localObject.length)
        {
          ResultPoint[] arrayOfResultPoint = localObject[i];
          ResultPoint.orderBestPatterns(arrayOfResultPoint);
          paramHashtable.addElement(new FinderPatternInfo(arrayOfResultPoint));
          i += 1;
        }
        if (paramHashtable.isEmpty())
          return EMPTY_RESULT_ARRAY;
        localObject = new FinderPatternInfo[paramHashtable.size()];
        i = i1;
        while (i < paramHashtable.size())
        {
          localObject[i] = ((FinderPatternInfo)paramHashtable.elementAt(i));
          i += 1;
        }
        return localObject;
      }
    }
  }

  private static class ModuleSizeComparator
    implements Comparator
  {
    private ModuleSizeComparator()
    {
    }

    ModuleSizeComparator(MultiFinderPatternFinder.1 param1)
    {
      this();
    }

    public int compare(Object paramObject1, Object paramObject2)
    {
      float f = ((FinderPattern)paramObject2).getEstimatedModuleSize() - ((FinderPattern)paramObject1).getEstimatedModuleSize();
      if (f < 0.0D)
        return -1;
      if (f > 0.0D)
        return 1;
      return 0;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.multi.qrcode.detector.MultiFinderPatternFinder
 * JD-Core Version:    0.6.2
 */