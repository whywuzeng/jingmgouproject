package com.google.zxing.oned;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Enumeration;
import java.util.Hashtable;

public abstract class OneDReader
  implements Reader
{
  protected static final int INTEGER_MATH_SHIFT = 8;
  protected static final int PATTERN_MATCH_RESULT_SCALE_FACTOR = 256;

  private Result doDecode(BinaryBitmap paramBinaryBitmap, Hashtable paramHashtable)
    throws NotFoundException
  {
    int i1 = paramBinaryBitmap.getWidth();
    int m = paramBinaryBitmap.getHeight();
    Object localObject1 = new BitArray(i1);
    int i;
    int j;
    label47: int i2;
    if ((paramHashtable != null) && (paramHashtable.containsKey(DecodeHintType.TRY_HARDER)))
    {
      i = 1;
      if (i == 0)
        break label134;
      j = 8;
      i2 = Math.max(1, m >> j);
      if (i == 0)
        break label140;
      i = m;
      label65: j = 0;
    }
    while (true)
    {
      int n;
      if (j < i)
      {
        n = j + 1 >> 1;
        if ((j & 0x1) != 0)
          break label146;
        k = 1;
        label92: if (k == 0)
          break label152;
      }
      label134: label140: label146: label152: for (int k = n; ; k = -n)
      {
        n = (m >> 1) + k * i2;
        if ((n >= 0) && (n < m))
          break label160;
        throw NotFoundException.getNotFoundInstance();
        i = 0;
        break;
        j = 5;
        break label47;
        i = 15;
        break label65;
        k = 0;
        break label92;
      }
      try
      {
        label160: Object localObject2 = paramBinaryBitmap.getBlackRow(n, (BitArray)localObject1);
        localObject1 = localObject2;
        k = 0;
        while (true)
        {
          localObject3 = localObject1;
          localObject2 = paramHashtable;
          if (k >= 2)
            break;
          localObject2 = paramHashtable;
          if (k == 1)
          {
            ((BitArray)localObject1).reverse();
            localObject2 = paramHashtable;
            if (paramHashtable != null)
            {
              localObject2 = paramHashtable;
              if (paramHashtable.containsKey(DecodeHintType.NEED_RESULT_POINT_CALLBACK))
              {
                localObject2 = new Hashtable();
                localObject3 = paramHashtable.keys();
                while (((Enumeration)localObject3).hasMoreElements())
                {
                  Object localObject4 = ((Enumeration)localObject3).nextElement();
                  if (!localObject4.equals(DecodeHintType.NEED_RESULT_POINT_CALLBACK))
                    ((Hashtable)localObject2).put(localObject4, paramHashtable.get(localObject4));
                }
              }
            }
          }
          try
          {
            paramHashtable = decodeRow(n, (BitArray)localObject1, (Hashtable)localObject2);
            if (k == 1)
            {
              paramHashtable.putMetadata(ResultMetadataType.ORIENTATION, new Integer(180));
              localObject3 = paramHashtable.getResultPoints();
              localObject3[0] = new ResultPoint(i1 - localObject3[0].getX() - 1.0F, localObject3[0].getY());
              localObject3[1] = new ResultPoint(i1 - localObject3[1].getX() - 1.0F, localObject3[1].getY());
            }
            return paramHashtable;
          }
          catch (ReaderException paramHashtable)
          {
            k += 1;
            paramHashtable = (Hashtable)localObject2;
          }
        }
      }
      catch (NotFoundException localNotFoundException)
      {
        Hashtable localHashtable = paramHashtable;
        Object localObject3 = localObject1;
        j += 1;
        localObject1 = localObject3;
        paramHashtable = localHashtable;
      }
    }
  }

  protected static int patternMatchVariance(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt)
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

  protected static void recordPattern(BitArray paramBitArray, int paramInt, int[] paramArrayOfInt)
    throws NotFoundException
  {
    int m = paramArrayOfInt.length;
    int i = 0;
    while (i < m)
    {
      paramArrayOfInt[i] = 0;
      i += 1;
    }
    int n = paramBitArray.getSize();
    if (paramInt >= n)
      throw NotFoundException.getNotFoundInstance();
    if (!paramBitArray.get(paramInt));
    int j;
    for (i = 1; ; i = 0)
    {
      k = 0;
      j = paramInt;
      paramInt = k;
      while (true)
      {
        if (j >= n)
          break label162;
        if (!(paramBitArray.get(j) ^ i))
          break;
        paramArrayOfInt[paramInt] += 1;
        j += 1;
      }
    }
    int k = paramInt + 1;
    if (k == m);
    while (true)
    {
      if ((k != m) && ((k != m - 1) || (j != n)))
      {
        throw NotFoundException.getNotFoundInstance();
        paramArrayOfInt[k] = 1;
        if (i == 0);
        for (paramInt = 1; ; paramInt = 0)
        {
          i = paramInt;
          paramInt = k;
          break;
        }
      }
      return;
      label162: k = paramInt;
    }
  }

  protected static void recordPatternInReverse(BitArray paramBitArray, int paramInt, int[] paramArrayOfInt)
    throws NotFoundException
  {
    int i = paramArrayOfInt.length;
    boolean bool = paramBitArray.get(paramInt);
    while ((paramInt > 0) && (i >= 0))
    {
      int j = paramInt - 1;
      paramInt = j;
      if (paramBitArray.get(j) != bool)
      {
        i -= 1;
        if (!bool)
        {
          bool = true;
          paramInt = j;
        }
        else
        {
          bool = false;
          paramInt = j;
        }
      }
    }
    if (i >= 0)
      throw NotFoundException.getNotFoundInstance();
    recordPattern(paramBitArray, paramInt + 1, paramArrayOfInt);
  }

  public Result decode(BinaryBitmap paramBinaryBitmap)
    throws NotFoundException, FormatException
  {
    return decode(paramBinaryBitmap, null);
  }

  public Result decode(BinaryBitmap paramBinaryBitmap, Hashtable paramHashtable)
    throws NotFoundException, FormatException
  {
    try
    {
      Result localResult = doDecode(paramBinaryBitmap, paramHashtable);
      return localResult;
    }
    catch (NotFoundException localNotFoundException)
    {
      if (paramHashtable == null)
        break label171;
    }
    Object localObject;
    if (paramHashtable.containsKey(DecodeHintType.TRY_HARDER))
    {
      i = 1;
      if ((i == 0) || (!paramBinaryBitmap.isRotateSupported()))
        break label178;
      paramBinaryBitmap = paramBinaryBitmap.rotateCounterClockwise();
      paramHashtable = doDecode(paramBinaryBitmap, paramHashtable);
      localObject = paramHashtable.getResultMetadata();
      if ((localObject == null) || (!((Hashtable)localObject).containsKey(ResultMetadataType.ORIENTATION)))
        break label181;
    }
    label171: label178: label181: for (int i = (((Integer)((Hashtable)localObject).get(ResultMetadataType.ORIENTATION)).intValue() + 270) % 360; ; i = 270)
    {
      paramHashtable.putMetadata(ResultMetadataType.ORIENTATION, new Integer(i));
      localObject = paramHashtable.getResultPoints();
      int j = paramBinaryBitmap.getHeight();
      i = 0;
      while (true)
        if (i < localObject.length)
        {
          localObject[i] = new ResultPoint(j - localObject[i].getY() - 1.0F, localObject[i].getX());
          i += 1;
          continue;
          i = 0;
          break;
        }
      return paramHashtable;
      throw ((Throwable)localObject);
    }
  }

  public abstract Result decodeRow(int paramInt, BitArray paramBitArray, Hashtable paramHashtable)
    throws NotFoundException, ChecksumException, FormatException;

  public void reset()
  {
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.OneDReader
 * JD-Core Version:    0.6.2
 */