package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;

public abstract class UPCEANReader extends OneDReader
{
  static final int[][] L_AND_G_PATTERNS;
  static final int[][] L_PATTERNS;
  private static final int MAX_AVG_VARIANCE = 107;
  private static final int MAX_INDIVIDUAL_VARIANCE = 179;
  static final int[] MIDDLE_PATTERN;
  static final int[] START_END_PATTERN = { 1, 1, 1 };
  private final StringBuffer decodeRowStringBuffer = new StringBuffer(20);
  private final EANManufacturerOrgSupport eanManSupport = new EANManufacturerOrgSupport();
  private final UPCEANExtensionSupport extensionReader = new UPCEANExtensionSupport();

  static
  {
    MIDDLE_PATTERN = new int[] { 1, 1, 1, 1, 1 };
    int[] arrayOfInt1 = { 2, 2, 2, 1 };
    int[] arrayOfInt2 = { 1, 4, 1, 1 };
    int[] arrayOfInt3 = { 1, 1, 3, 2 };
    int[] arrayOfInt4 = { 1, 2, 3, 1 };
    int[] arrayOfInt5 = { 1, 1, 1, 4 };
    int[] arrayOfInt6 = { 1, 3, 1, 2 };
    int[] arrayOfInt7 = { 1, 2, 1, 3 };
    int[] arrayOfInt8 = { 3, 1, 1, 2 };
    L_PATTERNS = new int[][] { { 3, 2, 1, 1 }, arrayOfInt1, { 2, 1, 2, 2 }, arrayOfInt2, arrayOfInt3, arrayOfInt4, arrayOfInt5, arrayOfInt6, arrayOfInt7, arrayOfInt8 };
    L_AND_G_PATTERNS = new int[20][];
    int i = 0;
    while (i < 10)
    {
      L_AND_G_PATTERNS[i] = L_PATTERNS[i];
      i += 1;
    }
    i = 10;
    while (i < 20)
    {
      arrayOfInt1 = L_PATTERNS[(i - 10)];
      arrayOfInt2 = new int[arrayOfInt1.length];
      int j = 0;
      while (j < arrayOfInt1.length)
      {
        arrayOfInt2[j] = arrayOfInt1[(arrayOfInt1.length - j - 1)];
        j += 1;
      }
      L_AND_G_PATTERNS[i] = arrayOfInt2;
      i += 1;
    }
  }

  private static boolean checkStandardUPCEANChecksum(String paramString)
    throws FormatException
  {
    int k = paramString.length();
    if (k == 0);
    int j;
    do
    {
      return false;
      int i = k - 2;
      j = 0;
      while (i >= 0)
      {
        int m = paramString.charAt(i) - '0';
        if ((m < 0) || (m > 9))
          throw FormatException.getFormatInstance();
        j += m;
        i -= 2;
      }
      j *= 3;
      i = k - 1;
      while (i >= 0)
      {
        k = paramString.charAt(i) - '0';
        if ((k < 0) || (k > 9))
          throw FormatException.getFormatInstance();
        j += k;
        i -= 2;
      }
    }
    while (j % 10 != 0);
    return true;
  }

  static int decodeDigit(BitArray paramBitArray, int[] paramArrayOfInt, int paramInt, int[][] paramArrayOfInt1)
    throws NotFoundException
  {
    recordPattern(paramBitArray, paramInt, paramArrayOfInt);
    int i = 107;
    int j = -1;
    int m = paramArrayOfInt1.length;
    paramInt = 0;
    if (paramInt < m)
    {
      int k = patternMatchVariance(paramArrayOfInt, paramArrayOfInt1[paramInt], 179);
      if (k >= i)
        break label70;
      j = paramInt;
      i = k;
    }
    label70: 
    while (true)
    {
      paramInt += 1;
      break;
      if (j >= 0)
        return j;
      throw NotFoundException.getNotFoundInstance();
    }
  }

  static int[] findGuardPattern(BitArray paramBitArray, int paramInt, boolean paramBoolean, int[] paramArrayOfInt)
    throws NotFoundException
  {
    int m = paramArrayOfInt.length;
    int[] arrayOfInt = new int[m];
    int n = paramBitArray.getSize();
    boolean bool = false;
    label36: int j;
    int i;
    label51: int k;
    if (paramInt < n)
    {
      if (!paramBitArray.get(paramInt))
      {
        bool = true;
        if (paramBoolean != bool)
          break label110;
      }
    }
    else
    {
      j = paramInt;
      i = 0;
      paramBoolean = bool;
      if (j >= n)
        break label256;
      if (!(paramBitArray.get(j) ^ paramBoolean))
        break label117;
      arrayOfInt[i] += 1;
      k = i;
      i = paramInt;
    }
    while (true)
    {
      j += 1;
      paramInt = i;
      i = k;
      break label51;
      bool = false;
      break label36;
      label110: paramInt += 1;
      break;
      label117: if (i == m - 1)
      {
        if (patternMatchVariance(arrayOfInt, paramArrayOfInt, 179) < 107)
          return new int[] { paramInt, j };
        k = paramInt + (arrayOfInt[0] + arrayOfInt[1]);
        paramInt = 2;
        while (paramInt < m)
        {
          arrayOfInt[(paramInt - 2)] = arrayOfInt[paramInt];
          paramInt += 1;
        }
        arrayOfInt[(m - 2)] = 0;
        arrayOfInt[(m - 1)] = 0;
        paramInt = i - 1;
        i = k;
      }
      while (true)
      {
        arrayOfInt[paramInt] = 1;
        if (paramBoolean)
          break label248;
        paramBoolean = true;
        k = paramInt;
        break;
        k = i + 1;
        i = paramInt;
        paramInt = k;
      }
      label248: paramBoolean = false;
      k = paramInt;
    }
    label256: throw NotFoundException.getNotFoundInstance();
  }

  static int[] findStartGuardPattern(BitArray paramBitArray)
    throws NotFoundException
  {
    int i = 0;
    Object localObject = null;
    boolean bool = false;
    while (!bool)
    {
      int[] arrayOfInt = findGuardPattern(paramBitArray, i, false, START_END_PATTERN);
      int k = arrayOfInt[0];
      int j = arrayOfInt[1];
      int m = k - (j - k);
      i = j;
      localObject = arrayOfInt;
      if (m >= 0)
      {
        bool = paramBitArray.isRange(m, k, false);
        i = j;
        localObject = arrayOfInt;
      }
    }
    return localObject;
  }

  boolean checkChecksum(String paramString)
    throws ChecksumException, FormatException
  {
    return checkStandardUPCEANChecksum(paramString);
  }

  int[] decodeEnd(BitArray paramBitArray, int paramInt)
    throws NotFoundException
  {
    return findGuardPattern(paramBitArray, paramInt, false, START_END_PATTERN);
  }

  protected abstract int decodeMiddle(BitArray paramBitArray, int[] paramArrayOfInt, StringBuffer paramStringBuffer)
    throws NotFoundException;

  public Result decodeRow(int paramInt, BitArray paramBitArray, Hashtable paramHashtable)
    throws NotFoundException, ChecksumException, FormatException
  {
    return decodeRow(paramInt, paramBitArray, findStartGuardPattern(paramBitArray), paramHashtable);
  }

  public Result decodeRow(int paramInt, BitArray paramBitArray, int[] paramArrayOfInt, Hashtable paramHashtable)
    throws NotFoundException, ChecksumException, FormatException
  {
    if (paramHashtable == null);
    int[] arrayOfInt;
    for (paramHashtable = null; ; paramHashtable = (ResultPointCallback)paramHashtable.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK))
    {
      if (paramHashtable != null)
        paramHashtable.foundPossibleResultPoint(new ResultPoint((paramArrayOfInt[0] + paramArrayOfInt[1]) / 2.0F, paramInt));
      localObject = this.decodeRowStringBuffer;
      ((StringBuffer)localObject).setLength(0);
      int i = decodeMiddle(paramBitArray, paramArrayOfInt, (StringBuffer)localObject);
      if (paramHashtable != null)
        paramHashtable.foundPossibleResultPoint(new ResultPoint(i, paramInt));
      arrayOfInt = decodeEnd(paramBitArray, i);
      if (paramHashtable != null)
        paramHashtable.foundPossibleResultPoint(new ResultPoint((arrayOfInt[0] + arrayOfInt[1]) / 2.0F, paramInt));
      i = arrayOfInt[1];
      int j = i - arrayOfInt[0] + i;
      if ((j < paramBitArray.getSize()) && (paramBitArray.isRange(i, j, false)))
        break;
      throw NotFoundException.getNotFoundInstance();
    }
    paramHashtable = ((StringBuffer)localObject).toString();
    if (!checkChecksum(paramHashtable))
      throw ChecksumException.getChecksumInstance();
    float f1 = (paramArrayOfInt[1] + paramArrayOfInt[0]) / 2.0F;
    float f2 = (arrayOfInt[1] + arrayOfInt[0]) / 2.0F;
    Object localObject = getBarcodeFormat();
    paramArrayOfInt = new Result(paramHashtable, null, new ResultPoint[] { new ResultPoint(f1, paramInt), new ResultPoint(f2, paramInt) }, (BarcodeFormat)localObject);
    try
    {
      paramBitArray = this.extensionReader.decodeRow(paramInt, paramBitArray, arrayOfInt[1]);
      paramArrayOfInt.putAllMetadata(paramBitArray.getResultMetadata());
      paramArrayOfInt.addResultPoints(paramBitArray.getResultPoints());
      label313: if ((BarcodeFormat.EAN_13.equals(localObject)) || (BarcodeFormat.UPC_A.equals(localObject)))
      {
        paramBitArray = this.eanManSupport.lookupCountryIdentifier(paramHashtable);
        if (paramBitArray != null)
          paramArrayOfInt.putMetadata(ResultMetadataType.POSSIBLE_COUNTRY, paramBitArray);
      }
      return paramArrayOfInt;
    }
    catch (ReaderException paramBitArray)
    {
      break label313;
    }
  }

  abstract BarcodeFormat getBarcodeFormat();
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.UPCEANReader
 * JD-Core Version:    0.6.2
 */