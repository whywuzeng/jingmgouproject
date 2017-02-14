package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;

public final class Code128Reader extends OneDReader
{
  private static final int CODE_CODE_A = 101;
  private static final int CODE_CODE_B = 100;
  private static final int CODE_CODE_C = 99;
  private static final int CODE_FNC_1 = 102;
  private static final int CODE_FNC_2 = 97;
  private static final int CODE_FNC_3 = 96;
  private static final int CODE_FNC_4_A = 101;
  private static final int CODE_FNC_4_B = 100;
  static final int[][] CODE_PATTERNS = { arrayOfInt1, arrayOfInt2, arrayOfInt3, arrayOfInt4, arrayOfInt5, { 1, 3, 1, 2, 2, 2 }, arrayOfInt6, arrayOfInt7, arrayOfInt8, arrayOfInt9, arrayOfInt10, arrayOfInt11, arrayOfInt12, arrayOfInt13, arrayOfInt14, arrayOfInt15, arrayOfInt16, arrayOfInt17, { 2, 2, 3, 2, 1, 1 }, arrayOfInt18, { 2, 2, 1, 2, 3, 1 }, { 2, 1, 3, 2, 1, 2 }, { 2, 2, 3, 1, 1, 2 }, { 3, 1, 2, 1, 3, 1 }, arrayOfInt19, arrayOfInt20, arrayOfInt21, { 3, 1, 2, 2, 1, 2 }, arrayOfInt22, { 3, 2, 2, 2, 1, 1 }, arrayOfInt23, arrayOfInt24, arrayOfInt25, arrayOfInt26, arrayOfInt27, arrayOfInt28, arrayOfInt29, arrayOfInt30, arrayOfInt31, arrayOfInt32, arrayOfInt33, { 2, 3, 1, 3, 1, 1 }, arrayOfInt34, arrayOfInt35, { 1, 3, 2, 1, 3, 1 }, arrayOfInt36, arrayOfInt37, arrayOfInt38, { 3, 1, 3, 1, 2, 1 }, arrayOfInt39, arrayOfInt40, arrayOfInt41, arrayOfInt42, arrayOfInt43, arrayOfInt44, arrayOfInt45, arrayOfInt46, { 3, 1, 2, 1, 1, 3 }, arrayOfInt47, arrayOfInt48, arrayOfInt49, arrayOfInt50, arrayOfInt51, arrayOfInt52, arrayOfInt53, arrayOfInt54, arrayOfInt55, arrayOfInt56, arrayOfInt57, arrayOfInt58, arrayOfInt59, arrayOfInt60, arrayOfInt61, arrayOfInt62, arrayOfInt63, arrayOfInt64, arrayOfInt65, arrayOfInt66, { 2, 4, 1, 1, 1, 2 }, { 1, 3, 4, 1, 1, 1 }, arrayOfInt67, arrayOfInt68, arrayOfInt69, arrayOfInt70, arrayOfInt71, arrayOfInt72, arrayOfInt73, { 4, 2, 1, 1, 1, 2 }, { 4, 2, 1, 2, 1, 1 }, arrayOfInt74, arrayOfInt75, arrayOfInt76, arrayOfInt77, arrayOfInt78, arrayOfInt79, arrayOfInt80, arrayOfInt81, arrayOfInt82, arrayOfInt83, arrayOfInt84, arrayOfInt85, arrayOfInt86, arrayOfInt87, arrayOfInt88, arrayOfInt89, arrayOfInt90, arrayOfInt91 };
  private static final int CODE_SHIFT = 98;
  private static final int CODE_START_A = 103;
  private static final int CODE_START_B = 104;
  private static final int CODE_START_C = 105;
  private static final int CODE_STOP = 106;
  private static final int MAX_AVG_VARIANCE = 64;
  private static final int MAX_INDIVIDUAL_VARIANCE = 179;

  static
  {
    int[] arrayOfInt1 = { 2, 1, 2, 2, 2, 2 };
    int[] arrayOfInt2 = { 2, 2, 2, 1, 2, 2 };
    int[] arrayOfInt3 = { 2, 2, 2, 2, 2, 1 };
    int[] arrayOfInt4 = { 1, 2, 1, 2, 2, 3 };
    int[] arrayOfInt5 = { 1, 2, 1, 3, 2, 2 };
    int[] arrayOfInt6 = { 1, 2, 2, 2, 1, 3 };
    int[] arrayOfInt7 = { 1, 2, 2, 3, 1, 2 };
    int[] arrayOfInt8 = { 1, 3, 2, 2, 1, 2 };
    int[] arrayOfInt9 = { 2, 2, 1, 2, 1, 3 };
    int[] arrayOfInt10 = { 2, 2, 1, 3, 1, 2 };
    int[] arrayOfInt11 = { 2, 3, 1, 2, 1, 2 };
    int[] arrayOfInt12 = { 1, 1, 2, 2, 3, 2 };
    int[] arrayOfInt13 = { 1, 2, 2, 1, 3, 2 };
    int[] arrayOfInt14 = { 1, 2, 2, 2, 3, 1 };
    int[] arrayOfInt15 = { 1, 1, 3, 2, 2, 2 };
    int[] arrayOfInt16 = { 1, 2, 3, 1, 2, 2 };
    int[] arrayOfInt17 = { 1, 2, 3, 2, 2, 1 };
    int[] arrayOfInt18 = { 2, 2, 1, 1, 3, 2 };
    int[] arrayOfInt19 = { 3, 1, 1, 2, 2, 2 };
    int[] arrayOfInt20 = { 3, 2, 1, 1, 2, 2 };
    int[] arrayOfInt21 = { 3, 2, 1, 2, 2, 1 };
    int[] arrayOfInt22 = { 3, 2, 2, 1, 1, 2 };
    int[] arrayOfInt23 = { 2, 1, 2, 1, 2, 3 };
    int[] arrayOfInt24 = { 2, 1, 2, 3, 2, 1 };
    int[] arrayOfInt25 = { 2, 3, 2, 1, 2, 1 };
    int[] arrayOfInt26 = { 1, 1, 1, 3, 2, 3 };
    int[] arrayOfInt27 = { 1, 3, 1, 1, 2, 3 };
    int[] arrayOfInt28 = { 1, 3, 1, 3, 2, 1 };
    int[] arrayOfInt29 = { 1, 1, 2, 3, 1, 3 };
    int[] arrayOfInt30 = { 1, 3, 2, 1, 1, 3 };
    int[] arrayOfInt31 = { 1, 3, 2, 3, 1, 1 };
    int[] arrayOfInt32 = { 2, 1, 1, 3, 1, 3 };
    int[] arrayOfInt33 = { 2, 3, 1, 1, 1, 3 };
    int[] arrayOfInt34 = { 1, 1, 2, 1, 3, 3 };
    int[] arrayOfInt35 = { 1, 1, 2, 3, 3, 1 };
    int[] arrayOfInt36 = { 1, 1, 3, 1, 2, 3 };
    int[] arrayOfInt37 = { 1, 1, 3, 3, 2, 1 };
    int[] arrayOfInt38 = { 1, 3, 3, 1, 2, 1 };
    int[] arrayOfInt39 = { 2, 1, 1, 3, 3, 1 };
    int[] arrayOfInt40 = { 2, 3, 1, 1, 3, 1 };
    int[] arrayOfInt41 = { 2, 1, 3, 1, 1, 3 };
    int[] arrayOfInt42 = { 2, 1, 3, 3, 1, 1 };
    int[] arrayOfInt43 = { 2, 1, 3, 1, 3, 1 };
    int[] arrayOfInt44 = { 3, 1, 1, 1, 2, 3 };
    int[] arrayOfInt45 = { 3, 1, 1, 3, 2, 1 };
    int[] arrayOfInt46 = { 3, 3, 1, 1, 2, 1 };
    int[] arrayOfInt47 = { 3, 1, 2, 3, 1, 1 };
    int[] arrayOfInt48 = { 3, 3, 2, 1, 1, 1 };
    int[] arrayOfInt49 = { 3, 1, 4, 1, 1, 1 };
    int[] arrayOfInt50 = { 2, 2, 1, 4, 1, 1 };
    int[] arrayOfInt51 = { 4, 3, 1, 1, 1, 1 };
    int[] arrayOfInt52 = { 1, 1, 1, 2, 2, 4 };
    int[] arrayOfInt53 = { 1, 1, 1, 4, 2, 2 };
    int[] arrayOfInt54 = { 1, 2, 1, 1, 2, 4 };
    int[] arrayOfInt55 = { 1, 2, 1, 4, 2, 1 };
    int[] arrayOfInt56 = { 1, 4, 1, 1, 2, 2 };
    int[] arrayOfInt57 = { 1, 4, 1, 2, 2, 1 };
    int[] arrayOfInt58 = { 1, 1, 2, 2, 1, 4 };
    int[] arrayOfInt59 = { 1, 1, 2, 4, 1, 2 };
    int[] arrayOfInt60 = { 1, 2, 2, 1, 1, 4 };
    int[] arrayOfInt61 = { 1, 2, 2, 4, 1, 1 };
    int[] arrayOfInt62 = { 1, 4, 2, 1, 1, 2 };
    int[] arrayOfInt63 = { 1, 4, 2, 2, 1, 1 };
    int[] arrayOfInt64 = { 2, 4, 1, 2, 1, 1 };
    int[] arrayOfInt65 = { 2, 2, 1, 1, 1, 4 };
    int[] arrayOfInt66 = { 4, 1, 3, 1, 1, 1 };
    int[] arrayOfInt67 = { 1, 1, 1, 2, 4, 2 };
    int[] arrayOfInt68 = { 1, 2, 1, 1, 4, 2 };
    int[] arrayOfInt69 = { 1, 2, 1, 2, 4, 1 };
    int[] arrayOfInt70 = { 1, 1, 4, 2, 1, 2 };
    int[] arrayOfInt71 = { 1, 2, 4, 1, 1, 2 };
    int[] arrayOfInt72 = { 1, 2, 4, 2, 1, 1 };
    int[] arrayOfInt73 = { 4, 1, 1, 2, 1, 2 };
    int[] arrayOfInt74 = { 2, 1, 2, 1, 4, 1 };
    int[] arrayOfInt75 = { 2, 1, 4, 1, 2, 1 };
    int[] arrayOfInt76 = { 4, 1, 2, 1, 2, 1 };
    int[] arrayOfInt77 = { 1, 1, 1, 1, 4, 3 };
    int[] arrayOfInt78 = { 1, 1, 1, 3, 4, 1 };
    int[] arrayOfInt79 = { 1, 3, 1, 1, 4, 1 };
    int[] arrayOfInt80 = { 1, 1, 4, 1, 1, 3 };
    int[] arrayOfInt81 = { 1, 1, 4, 3, 1, 1 };
    int[] arrayOfInt82 = { 4, 1, 1, 1, 1, 3 };
    int[] arrayOfInt83 = { 4, 1, 1, 3, 1, 1 };
    int[] arrayOfInt84 = { 1, 1, 3, 1, 4, 1 };
    int[] arrayOfInt85 = { 1, 1, 4, 1, 3, 1 };
    int[] arrayOfInt86 = { 3, 1, 1, 1, 4, 1 };
    int[] arrayOfInt87 = { 4, 1, 1, 1, 3, 1 };
    int[] arrayOfInt88 = { 2, 1, 1, 4, 1, 2 };
    int[] arrayOfInt89 = { 2, 1, 1, 2, 1, 4 };
    int[] arrayOfInt90 = { 2, 1, 1, 2, 3, 2 };
    int[] arrayOfInt91 = { 2, 3, 3, 1, 1, 1, 2 };
  }

  private static int decodeCode(BitArray paramBitArray, int[] paramArrayOfInt, int paramInt)
    throws NotFoundException
  {
    recordPattern(paramBitArray, paramInt, paramArrayOfInt);
    int i = 64;
    int k = -1;
    paramInt = 0;
    while (paramInt < CODE_PATTERNS.length)
    {
      int m = patternMatchVariance(paramArrayOfInt, CODE_PATTERNS[paramInt], 179);
      int j = i;
      if (m < i)
      {
        k = paramInt;
        j = m;
      }
      paramInt += 1;
      i = j;
    }
    if (k >= 0)
      return k;
    throw NotFoundException.getNotFoundInstance();
  }

  private static int[] findStartPattern(BitArray paramBitArray)
    throws NotFoundException
  {
    int i4 = paramBitArray.getSize();
    int i = 0;
    int[] arrayOfInt;
    int i5;
    int i1;
    int m;
    int k;
    int j;
    while (true)
    {
      if ((i >= i4) || (paramBitArray.get(i)))
      {
        arrayOfInt = new int[6];
        i5 = arrayOfInt.length;
        i1 = i;
        m = 0;
        k = i;
        j = 0;
        i = m;
        while (true)
        {
          if (i1 >= i4)
            break label287;
          if (!(paramBitArray.get(i1) ^ i))
            break;
          arrayOfInt[j] += 1;
          i1 += 1;
        }
      }
      i += 1;
    }
    int i2;
    if (j == i5 - 1)
    {
      int n = 64;
      i2 = -1;
      m = 103;
      label109: if (m <= 105)
      {
        int i3 = patternMatchVariance(arrayOfInt, CODE_PATTERNS[m], 179);
        if (i3 >= n)
          break label291;
        i2 = m;
        n = i3;
      }
    }
    label261: label287: label291: 
    while (true)
    {
      m += 1;
      break label109;
      if ((i2 >= 0) && (paramBitArray.isRange(Math.max(0, k - (i1 - k) / 2), k, false)))
        return new int[] { k, i1, i2 };
      m = arrayOfInt[0] + arrayOfInt[1] + k;
      k = 2;
      while (k < i5)
      {
        arrayOfInt[(k - 2)] = arrayOfInt[k];
        k += 1;
      }
      arrayOfInt[(i5 - 2)] = 0;
      arrayOfInt[(i5 - 1)] = 0;
      j -= 1;
      k = m;
      arrayOfInt[j] = 1;
      if (i == 0);
      for (i = 1; ; i = 0)
      {
        break;
        j += 1;
        break label261;
      }
      throw NotFoundException.getNotFoundInstance();
    }
  }

  public Result decodeRow(int paramInt, BitArray paramBitArray, Hashtable paramHashtable)
    throws NotFoundException, FormatException, ChecksumException
  {
    paramHashtable = findStartPattern(paramBitArray);
    int m = paramHashtable[2];
    int i;
    switch (m)
    {
    default:
      throw FormatException.getFormatInstance();
    case 103:
      i = 101;
    case 104:
    case 105:
    }
    Object localObject1;
    int i3;
    int i1;
    Object localObject2;
    int k;
    int i5;
    int i4;
    int n;
    int i6;
    int i2;
    int j;
    label101: int i9;
    int i8;
    int i7;
    while (true)
    {
      localObject1 = new StringBuffer(20);
      i3 = paramHashtable[0];
      i1 = paramHashtable[1];
      localObject2 = new int[6];
      k = 1;
      i5 = 0;
      i4 = 0;
      n = 0;
      i6 = 0;
      i2 = 0;
      j = i;
      i = i4;
      if (i != 0)
        break label1130;
      i9 = 0;
      i8 = decodeCode(paramBitArray, (int[])localObject2, i1);
      if (i8 != 106)
        k = 1;
      i7 = n;
      i6 = m;
      if (i8 != 106)
      {
        i7 = n + 1;
        i6 = m + i7 * i8;
      }
      m = 0;
      i3 = i1;
      while (m < localObject2.length)
      {
        i3 += localObject2[m];
        m += 1;
      }
      i = 100;
      continue;
      i = 99;
    }
    switch (i8)
    {
    default:
      switch (j)
      {
      default:
        n = j;
        i4 = 0;
        j = k;
        m = i;
        k = i4;
        i = n;
      case 101:
      case 100:
      case 99:
      }
      break;
    case 103:
    case 104:
    case 105:
    }
    while (true)
    {
      n = i;
      if (i5 != 0);
      switch (i)
      {
      default:
        n = i;
        label328: i4 = j;
        i5 = k;
        i = m;
        j = n;
        k = i2;
        i2 = i8;
        i8 = i3;
        n = i7;
        m = i6;
        i6 = k;
        i3 = i1;
        i1 = i8;
        k = i4;
        break label101;
        throw FormatException.getFormatInstance();
        if (i8 < 64)
        {
          ((StringBuffer)localObject1).append((char)(i8 + 32));
          m = i;
          n = 0;
          i = j;
          j = k;
          k = n;
        }
        else if (i8 < 96)
        {
          ((StringBuffer)localObject1).append((char)(i8 - 64));
          m = i;
          n = 0;
          i = j;
          j = k;
          k = n;
        }
        else
        {
          m = k;
          if (i8 != 106)
            m = 0;
          switch (i8)
          {
          case 103:
          case 104:
          case 105:
          default:
            n = 0;
            k = j;
            j = i;
            i = n;
          case 96:
          case 97:
          case 101:
          case 102:
          case 98:
          case 100:
          case 99:
          case 106:
          }
          while (true)
          {
            n = j;
            j = m;
            m = k;
            k = i;
            i = m;
            m = n;
            break;
            i4 = 0;
            k = i;
            n = j;
            i = i4;
            j = k;
            k = n;
            continue;
            n = 1;
            k = 100;
            j = i;
            i = n;
            continue;
            j = i;
            k = 100;
            i = 0;
            continue;
            j = i;
            k = 99;
            i = 0;
            continue;
            k = j;
            i = 0;
            j = 1;
          }
          if (i8 >= 96)
            break label712;
          ((StringBuffer)localObject1).append((char)(i8 + 32));
          m = i;
          n = 0;
          i = j;
          j = k;
          k = n;
        }
        break;
      case 101:
      case 100:
      case 99:
      }
    }
    label712: if (i8 != 106);
    for (m = 0; ; m = k)
    {
      n = i9;
      i4 = i;
      k = j;
      switch (i8)
      {
      default:
        k = j;
        i4 = i;
        n = i9;
      case 96:
      case 97:
      case 100:
      case 102:
      case 103:
      case 104:
      case 105:
      case 98:
      case 101:
      case 99:
      case 106:
      }
      while (true)
      {
        i = k;
        k = n;
        j = m;
        m = i4;
        break;
        n = 1;
        k = 99;
        i4 = i;
        continue;
        k = 101;
        n = i9;
        i4 = i;
        continue;
        k = 99;
        n = i9;
        i4 = i;
        continue;
        i4 = 1;
        n = i9;
        k = j;
      }
      if (i8 < 100)
      {
        if (i8 < 10)
          ((StringBuffer)localObject1).append('0');
        ((StringBuffer)localObject1).append(i8);
        m = i;
        n = 0;
        i = j;
        j = k;
        k = n;
        break;
      }
      if (i8 != 106)
        k = 0;
      while (true)
        switch (i8)
        {
        case 103:
        case 104:
        case 105:
        default:
          m = i;
          n = 0;
          i = j;
          j = k;
          k = n;
          break;
        case 102:
          m = i;
          n = 0;
          i = j;
          j = k;
          k = n;
          break;
        case 101:
          i4 = 101;
          m = i;
          n = 0;
          j = k;
          i = i4;
          k = n;
          break;
        case 100:
          i4 = 100;
          m = i;
          n = 0;
          j = k;
          i = i4;
          k = n;
          break;
        case 106:
          i = j;
          m = 1;
          n = 0;
          j = k;
          k = n;
          break;
          n = 99;
          break label328;
          n = 101;
          break label328;
          n = 100;
          break label328;
          label1130: i = paramBitArray.getSize();
          while ((i1 < i) && (paramBitArray.get(i1)))
            i1 += 1;
          if (!paramBitArray.isRange(i1, Math.min(i, (i1 - i3) / 2 + i1), false))
            throw NotFoundException.getNotFoundInstance();
          if ((m - n * i6) % 103 != i6)
            throw ChecksumException.getChecksumInstance();
          i = ((StringBuffer)localObject1).length();
          if ((i > 0) && (k != 0))
          {
            if (j != 99)
              break label1263;
            ((StringBuffer)localObject1).delete(i - 2, i);
          }
          while (true)
          {
            paramBitArray = ((StringBuffer)localObject1).toString();
            if (paramBitArray.length() != 0)
              break;
            throw FormatException.getFormatInstance();
            label1263: ((StringBuffer)localObject1).delete(i - 1, i);
          }
          float f1 = (paramHashtable[1] + paramHashtable[0]) / 2.0F;
          float f2 = (i1 + i3) / 2.0F;
          paramHashtable = new ResultPoint(f1, paramInt);
          localObject1 = new ResultPoint(f2, paramInt);
          localObject2 = BarcodeFormat.CODE_128;
          return new Result(paramBitArray, null, new ResultPoint[] { paramHashtable, localObject1 }, (BarcodeFormat)localObject2);
        }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.Code128Reader
 * JD-Core Version:    0.6.2
 */