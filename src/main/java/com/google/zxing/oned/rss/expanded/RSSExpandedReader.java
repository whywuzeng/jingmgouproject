package com.google.zxing.oned.rss.expanded;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import com.google.zxing.oned.rss.AbstractRSSReader;
import com.google.zxing.oned.rss.DataCharacter;
import com.google.zxing.oned.rss.FinderPattern;
import com.google.zxing.oned.rss.RSSUtils;
import com.google.zxing.oned.rss.expanded.decoders.AbstractExpandedDecoder;
import java.util.Hashtable;
import java.util.Vector;

public final class RSSExpandedReader extends AbstractRSSReader
{
  private static final int[] EVEN_TOTAL_SUBSET;
  private static final int[][] FINDER_PATTERNS;
  private static final int[][] FINDER_PATTERN_SEQUENCES = { arrayOfInt1, { 0, 1, 1 }, { 0, 2, 1, 3 }, { 0, 4, 1, 3, 2 }, arrayOfInt2, arrayOfInt3, { 0, 0, 1, 1, 2, 2, 3, 3 }, arrayOfInt4, arrayOfInt5, arrayOfInt6 };
  private static final int FINDER_PAT_A = 0;
  private static final int FINDER_PAT_B = 1;
  private static final int FINDER_PAT_C = 2;
  private static final int FINDER_PAT_D = 3;
  private static final int FINDER_PAT_E = 4;
  private static final int FINDER_PAT_F = 5;
  private static final int[] GSUM;
  private static final int LONGEST_SEQUENCE_SIZE = FINDER_PATTERN_SEQUENCES[(FINDER_PATTERN_SEQUENCES.length - 1)].length;
  private static final int MAX_PAIRS = 11;
  private static final int[] SYMBOL_WIDEST = { 7, 5, 4, 3, 1 };
  private static final int[][] WEIGHTS;
  private final int[] currentSequence = new int[LONGEST_SEQUENCE_SIZE];
  private final Vector pairs = new Vector(11);
  private final int[] startEnd = new int[2];

  static
  {
    EVEN_TOTAL_SUBSET = new int[] { 4, 20, 52, 104, 204 };
    GSUM = new int[] { 0, 348, 1388, 2948, 3988 };
    int[] arrayOfInt1 = { 1, 8, 4, 1 };
    int[] arrayOfInt2 = { 3, 6, 4, 1 };
    int[] arrayOfInt3 = { 3, 2, 8, 1 };
    int[] arrayOfInt4 = { 2, 6, 5, 1 };
    int[] arrayOfInt5 = { 2, 2, 9, 1 };
    FINDER_PATTERNS = new int[][] { arrayOfInt1, arrayOfInt2, { 3, 4, 6, 1 }, arrayOfInt3, arrayOfInt4, arrayOfInt5 };
    arrayOfInt1 = new int[] { 1, 3, 9, 27, 81, 32, 96, 77 };
    arrayOfInt2 = new int[] { 20, 60, 180, 118, 143, 7, 21, 63 };
    arrayOfInt3 = new int[] { 189, 145, 13, 39, 117, 140, 209, 205 };
    arrayOfInt4 = new int[] { 193, 157, 49, 147, 19, 57, 171, 91 };
    arrayOfInt5 = new int[] { 62, 186, 136, 197, 169, 85, 44, 132 };
    int[] arrayOfInt6 = { 185, 133, 188, 142, 4, 12, 36, 108 };
    int[] arrayOfInt7 = { 150, 28, 84, 41, 123, 158, 52, 156 };
    int[] arrayOfInt8 = { 76, 17, 51, 153, 37, 111, 122, 155 };
    int[] arrayOfInt9 = { 43, 129, 176, 106, 107, 110, 119, 146 };
    int[] arrayOfInt10 = { 16, 48, 144, 10, 30, 90, 59, 177 };
    int[] arrayOfInt11 = { 109, 116, 137, 200, 178, 112, 125, 164 };
    int[] arrayOfInt12 = { 70, 210, 208, 202, 184, 130, 179, 115 };
    int[] arrayOfInt13 = { 134, 191, 151, 31, 93, 68, 204, 190 };
    int[] arrayOfInt14 = { 148, 22, 66, 198, 172, 94, 71, 2 };
    int[] arrayOfInt15 = { 6, 18, 54, 162, 64, 192, 154, 40 };
    int[] arrayOfInt16 = { 120, 149, 25, 75, 14, 42, 126, 167 };
    int[] arrayOfInt17 = { 103, 98, 83, 38, 114, 131, 182, 124 };
    int[] arrayOfInt18 = { 161, 61, 183, 127, 170, 88, 53, 159 };
    int[] arrayOfInt19 = { 55, 165, 73, 8, 24, 72, 5, 15 };
    WEIGHTS = new int[][] { arrayOfInt1, arrayOfInt2, arrayOfInt3, arrayOfInt4, arrayOfInt5, arrayOfInt6, { 113, 128, 173, 97, 80, 29, 87, 50 }, arrayOfInt7, { 46, 138, 203, 187, 139, 206, 196, 166 }, arrayOfInt8, arrayOfInt9, arrayOfInt10, arrayOfInt11, arrayOfInt12, arrayOfInt13, arrayOfInt14, arrayOfInt15, arrayOfInt16, { 79, 26, 78, 23, 69, 207, 199, 175 }, arrayOfInt17, arrayOfInt18, arrayOfInt19, { 45, 135, 194, 160, 58, 174, 100, 89 } };
    arrayOfInt1 = new int[] { 0, 0 };
    arrayOfInt2 = new int[] { 0, 4, 1, 3, 3, 5 };
    arrayOfInt3 = new int[] { 0, 4, 1, 3, 4, 5, 5 };
    arrayOfInt4 = new int[] { 0, 0, 1, 1, 2, 2, 3, 4, 4 };
    arrayOfInt5 = new int[] { 0, 0, 1, 1, 2, 2, 3, 4, 5, 5 };
    arrayOfInt6 = new int[] { 0, 0, 1, 1, 2, 3, 3, 4, 4, 5, 5 };
  }

  private void adjustOddEvenCounts(int paramInt)
    throws NotFoundException
  {
    int k = 0;
    int i1 = 1;
    int i2 = count(this.oddCounts);
    int i3 = count(this.evenCounts);
    int i4 = i2 + i3 - paramInt;
    int m;
    int n;
    label54: int i;
    if ((i2 & 0x1) == 1)
    {
      m = 1;
      if ((i3 & 0x1) != 0)
        break label103;
      n = 1;
      if (i2 <= 13)
        break label109;
      i = 1;
      paramInt = 0;
    }
    while (true)
    {
      label65: int j;
      if (i3 > 13)
      {
        j = 0;
        k = 1;
      }
      while (true)
      {
        if (i4 == 1)
          if (m != 0)
          {
            if (n != 0)
            {
              throw NotFoundException.getNotFoundInstance();
              m = 0;
              break;
              label103: n = 0;
              break label54;
              label109: if (i2 >= 4)
                break label372;
              i = 0;
              paramInt = 1;
              break label65;
              if (i3 >= 4)
                break label367;
              j = 1;
              continue;
            }
            m = 1;
            i = paramInt;
            paramInt = m;
          }
        while (true)
          if (i != 0)
          {
            if (paramInt != 0)
            {
              throw NotFoundException.getNotFoundInstance();
              if (n == 0)
                throw NotFoundException.getNotFoundInstance();
              k = 1;
              m = paramInt;
              paramInt = i;
              i = m;
              continue;
              if (i4 == -1)
              {
                if (m != 0)
                {
                  if (n != 0)
                    throw NotFoundException.getNotFoundInstance();
                  paramInt = i;
                  i = 1;
                }
                else
                {
                  if (n == 0)
                    throw NotFoundException.getNotFoundInstance();
                  m = paramInt;
                  j = i1;
                  paramInt = i;
                  i = m;
                }
              }
              else if (i4 == 0)
              {
                if (m != 0)
                {
                  if (n == 0)
                    throw NotFoundException.getNotFoundInstance();
                  if (i2 < i3)
                  {
                    k = 1;
                    paramInt = i;
                    i = 1;
                  }
                  else
                  {
                    m = 1;
                    i = paramInt;
                    j = i1;
                    paramInt = m;
                  }
                }
                else if (n != 0)
                {
                  throw NotFoundException.getNotFoundInstance();
                }
              }
              else
                throw NotFoundException.getNotFoundInstance();
            }
            else
            {
              increment(this.oddCounts, this.oddRoundingErrors);
            }
          }
          else
          {
            if (paramInt != 0)
              decrement(this.oddCounts, this.oddRoundingErrors);
            if (j != 0)
            {
              if (k != 0)
                throw NotFoundException.getNotFoundInstance();
              increment(this.evenCounts, this.oddRoundingErrors);
            }
            if (k != 0)
              decrement(this.evenCounts, this.evenRoundingErrors);
            return;
            m = paramInt;
            paramInt = i;
            i = m;
          }
        label367: j = 0;
      }
      label372: i = 0;
      paramInt = 0;
    }
  }

  private boolean checkChecksum()
  {
    ExpandedPair localExpandedPair = (ExpandedPair)this.pairs.elementAt(0);
    DataCharacter localDataCharacter = localExpandedPair.getLeftChar();
    int i = localExpandedPair.getRightChar().getChecksumPortion();
    int j = 2;
    int k = 1;
    while (k < this.pairs.size())
    {
      localExpandedPair = (ExpandedPair)this.pairs.elementAt(k);
      int m = i + localExpandedPair.getLeftChar().getChecksumPortion();
      int n = j + 1;
      j = n;
      i = m;
      if (localExpandedPair.getRightChar() != null)
      {
        i = m + localExpandedPair.getRightChar().getChecksumPortion();
        j = n + 1;
      }
      k += 1;
    }
    return i % 211 + (j - 4) * 211 == localDataCharacter.getValue();
  }

  private boolean checkPairSequence(Vector paramVector, FinderPattern paramFinderPattern)
    throws NotFoundException
  {
    boolean bool = false;
    int k = paramVector.size() + 1;
    if (k > this.currentSequence.length)
      throw NotFoundException.getNotFoundInstance();
    int i = 0;
    while (i < paramVector.size())
    {
      this.currentSequence[i] = ((ExpandedPair)paramVector.elementAt(i)).getFinderPattern().getValue();
      i += 1;
    }
    this.currentSequence[(k - 1)] = paramFinderPattern.getValue();
    i = 0;
    if (i < FINDER_PATTERN_SEQUENCES.length)
    {
      paramVector = FINDER_PATTERN_SEQUENCES[i];
      if (paramVector.length >= k)
      {
        j = 0;
        label101: if (j >= k)
          break label163;
        if (this.currentSequence[j] == paramVector[j]);
      }
    }
    label163: for (int j = 0; ; j = 1)
    {
      if (j != 0)
      {
        if (k == paramVector.length)
          bool = true;
        return bool;
        j += 1;
        break label101;
      }
      i += 1;
      break;
      throw NotFoundException.getNotFoundInstance();
    }
  }

  private static Result constructResult(Vector paramVector)
    throws NotFoundException
  {
    String str = AbstractExpandedDecoder.createDecoder(BitArrayBuilder.buildBitArray(paramVector)).parseInformation();
    Object localObject1 = ((ExpandedPair)paramVector.elementAt(0)).getFinderPattern().getResultPoints();
    Object localObject3 = ((ExpandedPair)paramVector.lastElement()).getFinderPattern().getResultPoints();
    paramVector = localObject1[0];
    localObject1 = localObject1[1];
    Object localObject2 = localObject3[0];
    localObject3 = localObject3[1];
    BarcodeFormat localBarcodeFormat = BarcodeFormat.RSS_EXPANDED;
    return new Result(str, null, new ResultPoint[] { paramVector, localObject1, localObject2, localObject3 }, localBarcodeFormat);
  }

  private void findNextPair(BitArray paramBitArray, Vector paramVector, int paramInt)
    throws NotFoundException
  {
    int[] arrayOfInt = this.decodeFinderCounters;
    arrayOfInt[0] = 0;
    arrayOfInt[1] = 0;
    arrayOfInt[2] = 0;
    arrayOfInt[3] = 0;
    int i2 = paramBitArray.getSize();
    int j;
    label48: int i;
    label51: label68: int k;
    int n;
    int m;
    if (paramInt >= 0)
    {
      if (paramVector.size() % 2 == 0)
        break label168;
      j = 1;
      i = 0;
      if (paramInt < i2)
      {
        if (paramBitArray.get(paramInt))
          break label174;
        i = 1;
        if (i != 0)
          break label180;
      }
      k = paramInt;
      n = 0;
      m = i;
      label83: if (k >= i2)
        break label340;
      if (!(paramBitArray.get(k) ^ m))
        break label187;
      arrayOfInt[n] += 1;
      i = m;
      m = paramInt;
    }
    while (true)
    {
      k += 1;
      paramInt = m;
      m = i;
      break label83;
      if (paramVector.isEmpty())
      {
        paramInt = 0;
        break;
      }
      paramInt = ((ExpandedPair)paramVector.lastElement()).getFinderPattern().getStartEnd()[1];
      break;
      label168: j = 0;
      break label48;
      label174: i = 0;
      break label68;
      label180: paramInt += 1;
      break label51;
      label187: if (n == 3)
      {
        if (j != 0)
          reverseCounters(arrayOfInt);
        if (isFinderPattern(arrayOfInt))
        {
          this.startEnd[0] = paramInt;
          this.startEnd[1] = k;
          return;
        }
        if (j != 0)
          reverseCounters(arrayOfInt);
        i = paramInt + (arrayOfInt[0] + arrayOfInt[1]);
        arrayOfInt[0] = arrayOfInt[2];
        arrayOfInt[1] = arrayOfInt[3];
        arrayOfInt[2] = 0;
        arrayOfInt[3] = 0;
      }
      for (paramInt = n - 1; ; paramInt = n)
      {
        arrayOfInt[paramInt] = 1;
        if (m != 0)
          break label323;
        i1 = 1;
        m = i;
        n = paramInt;
        i = i1;
        break;
        n += 1;
        i = paramInt;
      }
      label323: int i1 = 0;
      m = i;
      n = paramInt;
      i = i1;
    }
    label340: throw NotFoundException.getNotFoundInstance();
  }

  private static int getNextSecondBar(BitArray paramBitArray, int paramInt)
  {
    boolean bool = paramBitArray.get(paramInt);
    while ((paramInt < paramBitArray.size) && (paramBitArray.get(paramInt) == bool))
      paramInt += 1;
    if (!bool)
      bool = true;
    while ((paramInt < paramBitArray.size) && (paramBitArray.get(paramInt) == bool))
    {
      paramInt += 1;
      continue;
      bool = false;
    }
    return paramInt;
  }

  private static boolean isNotA1left(FinderPattern paramFinderPattern, boolean paramBoolean1, boolean paramBoolean2)
  {
    return (paramFinderPattern.getValue() != 0) || (!paramBoolean1) || (!paramBoolean2);
  }

  private FinderPattern parseFoundFinderPattern(BitArray paramBitArray, int paramInt, boolean paramBoolean)
  {
    int i;
    int k;
    int j;
    if (paramBoolean)
    {
      i = this.startEnd[0] - 1;
      while ((i >= 0) && (!paramBitArray.get(i)))
        i -= 1;
      i += 1;
      k = this.startEnd[0] - i;
      j = this.startEnd[1];
    }
    while (true)
    {
      paramBitArray = this.decodeFinderCounters;
      int m = paramBitArray.length - 1;
      while (m > 0)
      {
        paramBitArray[m] = paramBitArray[(m - 1)];
        m -= 1;
      }
      m = this.startEnd[0];
      i = this.startEnd[1] + 1;
      while ((paramBitArray.get(i)) && (i < paramBitArray.size))
        i += 1;
      k = this.startEnd[1];
      j = i;
      k = i - k;
      i = m;
    }
    paramBitArray[0] = k;
    try
    {
      k = parseFinderValue(paramBitArray, FINDER_PATTERNS);
      return new FinderPattern(k, new int[] { i, j }, i, j, paramInt);
    }
    catch (NotFoundException paramBitArray)
    {
    }
    return null;
  }

  private static void reverseCounters(int[] paramArrayOfInt)
  {
    int j = paramArrayOfInt.length;
    int i = 0;
    while (i < j / 2)
    {
      int k = paramArrayOfInt[i];
      paramArrayOfInt[i] = paramArrayOfInt[(j - i - 1)];
      paramArrayOfInt[(j - i - 1)] = k;
      i += 1;
    }
  }

  DataCharacter decodeDataCharacter(BitArray paramBitArray, FinderPattern paramFinderPattern, boolean paramBoolean1, boolean paramBoolean2)
    throws NotFoundException
  {
    int[] arrayOfInt1 = this.dataCharacterCounters;
    arrayOfInt1[0] = 0;
    arrayOfInt1[1] = 0;
    arrayOfInt1[2] = 0;
    arrayOfInt1[3] = 0;
    arrayOfInt1[4] = 0;
    arrayOfInt1[5] = 0;
    arrayOfInt1[6] = 0;
    arrayOfInt1[7] = 0;
    int[] arrayOfInt2;
    float[] arrayOfFloat2;
    label103: float f2;
    int i;
    if (paramBoolean2)
    {
      recordPatternInReverse(paramBitArray, paramFinderPattern.getStartEnd()[0], arrayOfInt1);
      float f1 = count(arrayOfInt1) / 17;
      paramBitArray = this.oddCounts;
      arrayOfInt2 = this.evenCounts;
      float[] arrayOfFloat1 = this.oddRoundingErrors;
      arrayOfFloat2 = this.evenRoundingErrors;
      j = 0;
      if (j >= arrayOfInt1.length)
        break label289;
      f2 = 1.0F * arrayOfInt1[j] / f1;
      k = (int)(0.5F + f2);
      if (k >= 1)
        break label250;
      i = 1;
      label141: k = j >> 1;
      if ((j & 0x1) != 0)
        break label268;
      paramBitArray[k] = i;
      arrayOfFloat1[k] = (f2 - i);
    }
    while (true)
    {
      j += 1;
      break label103;
      recordPattern(paramBitArray, paramFinderPattern.getStartEnd()[1] + 1, arrayOfInt1);
      j = 0;
      i = arrayOfInt1.length - 1;
      while (j < i)
      {
        k = arrayOfInt1[j];
        arrayOfInt1[j] = arrayOfInt1[i];
        arrayOfInt1[i] = k;
        j += 1;
        i -= 1;
      }
      break;
      label250: i = k;
      if (k <= 8)
        break label141;
      i = 8;
      break label141;
      label268: arrayOfInt2[k] = i;
      arrayOfFloat2[k] = (f2 - i);
    }
    label289: adjustOddEvenCounts(17);
    int k = paramFinderPattern.getValue();
    if (paramBoolean1)
    {
      i = 0;
      if (!paramBoolean2)
        break label418;
    }
    int i2;
    label418: for (int j = 0; ; j = 1)
    {
      i2 = j + (k * 4 + i) - 1;
      i = paramBitArray.length;
      j = 0;
      k = i - 1;
      for (i = 0; k >= 0; i = m)
      {
        m = i;
        if (isNotA1left(paramFinderPattern, paramBoolean1, paramBoolean2))
          m = i + WEIGHTS[i2][(k * 2)] * paramBitArray[k];
        i = paramBitArray[k];
        k -= 1;
        j = i + j;
      }
      i = 2;
      break;
    }
    int m = arrayOfInt2.length;
    k = 0;
    int n = 0;
    m -= 1;
    while (m >= 0)
    {
      i1 = k;
      if (isNotA1left(paramFinderPattern, paramBoolean1, paramBoolean2))
        i1 = k + WEIGHTS[i2][(m * 2 + 1)] * arrayOfInt2[m];
      n += arrayOfInt2[m];
      m -= 1;
      k = i1;
    }
    if (((j & 0x1) != 0) || (j > 13) || (j < 4))
      throw NotFoundException.getNotFoundInstance();
    j = (13 - j) / 2;
    n = SYMBOL_WIDEST[j];
    m = RSSUtils.getRSSvalue(paramBitArray, n, true);
    n = RSSUtils.getRSSvalue(arrayOfInt2, 9 - n, false);
    int i1 = EVEN_TOTAL_SUBSET[j];
    return new DataCharacter(GSUM[j] + (m * i1 + n), i + k);
  }

  public Result decodeRow(int paramInt, BitArray paramBitArray, Hashtable paramHashtable)
    throws NotFoundException
  {
    reset();
    decodeRow2pairs(paramInt, paramBitArray);
    return constructResult(this.pairs);
  }

  Vector decodeRow2pairs(int paramInt, BitArray paramBitArray)
    throws NotFoundException
  {
    ExpandedPair localExpandedPair;
    do
    {
      do
      {
        localExpandedPair = retrieveNextPair(paramBitArray, this.pairs, paramInt);
        this.pairs.addElement(localExpandedPair);
      }
      while (!localExpandedPair.mayBeLast());
      if (checkChecksum())
        return this.pairs;
    }
    while (!localExpandedPair.mustBeLast());
    throw NotFoundException.getNotFoundInstance();
  }

  public void reset()
  {
    this.pairs.setSize(0);
  }

  ExpandedPair retrieveNextPair(BitArray paramBitArray, Vector paramVector, int paramInt)
    throws NotFoundException
  {
    boolean bool1;
    if (paramVector.size() % 2 == 0)
      bool1 = true;
    while (true)
    {
      int k = -1;
      int i = 1;
      FinderPattern localFinderPattern;
      int j;
      do
      {
        findNextPair(paramBitArray, paramVector, k);
        localFinderPattern = parseFoundFinderPattern(paramBitArray, paramInt, bool1);
        if (localFinderPattern != null)
          break;
        k = getNextSecondBar(paramBitArray, this.startEnd[0]);
        j = i;
        i = j;
      }
      while (j != 0);
      boolean bool2 = checkPairSequence(paramVector, localFinderPattern);
      paramVector = decodeDataCharacter(paramBitArray, localFinderPattern, bool1, true);
      try
      {
        paramBitArray = decodeDataCharacter(paramBitArray, localFinderPattern, bool1, false);
        return new ExpandedPair(paramVector, paramBitArray, localFinderPattern, bool2);
        bool1 = false;
        continue;
        j = 0;
      }
      catch (NotFoundException paramBitArray)
      {
        while (bool2)
          paramBitArray = null;
      }
    }
    throw paramBitArray;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.rss.expanded.RSSExpandedReader
 * JD-Core Version:    0.6.2
 */