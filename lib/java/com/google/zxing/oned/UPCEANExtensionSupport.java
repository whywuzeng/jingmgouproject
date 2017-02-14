package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;

final class UPCEANExtensionSupport
{
  private static final int[] CHECK_DIGIT_ENCODINGS = { 24, 20, 18, 17, 12, 6, 3, 10, 9, 5 };
  private static final int[] EXTENSION_START_PATTERN = { 1, 1, 2 };
  private final int[] decodeMiddleCounters = new int[4];
  private final StringBuffer decodeRowStringBuffer = new StringBuffer();

  private static int determineCheckDigit(int paramInt)
    throws NotFoundException
  {
    int i = 0;
    while (i < 10)
    {
      if (paramInt == CHECK_DIGIT_ENCODINGS[i])
        return i;
      i += 1;
    }
    throw NotFoundException.getNotFoundInstance();
  }

  private static int extensionChecksum(String paramString)
  {
    int k = paramString.length();
    int j = 0;
    int i = k - 2;
    while (i >= 0)
    {
      j += paramString.charAt(i) - '0';
      i -= 2;
    }
    j *= 3;
    i = k - 1;
    while (i >= 0)
    {
      j += paramString.charAt(i) - '0';
      i -= 2;
    }
    return j * 3 % 10;
  }

  private static Integer parseExtension2String(String paramString)
  {
    return Integer.valueOf(paramString);
  }

  private static String parseExtension5String(String paramString)
  {
    String str = null;
    switch (paramString.charAt(0))
    {
    default:
      str = "";
    case '0':
    case '5':
    case '9':
    }
    do
    {
      while (true)
      {
        int i = Integer.parseInt(paramString.substring(1));
        return str + i / 100 + '.' + i % 100;
        str = "拢";
        continue;
        str = "$";
      }
      if ("99991".equals(paramString))
        return "0.00";
    }
    while (!"99990".equals(paramString));
    return "Used";
  }

  private static Hashtable parseExtensionString(String paramString)
  {
    ResultMetadataType localResultMetadataType;
    switch (paramString.length())
    {
    case 3:
    case 4:
    default:
      return null;
    case 2:
      localResultMetadataType = ResultMetadataType.ISSUE_NUMBER;
    case 5:
    }
    for (paramString = parseExtension2String(paramString); paramString != null; paramString = parseExtension5String(paramString))
    {
      Hashtable localHashtable = new Hashtable(1);
      localHashtable.put(localResultMetadataType, paramString);
      return localHashtable;
      localResultMetadataType = ResultMetadataType.SUGGESTED_PRICE;
    }
  }

  int decodeMiddle(BitArray paramBitArray, int[] paramArrayOfInt, StringBuffer paramStringBuffer)
    throws NotFoundException
  {
    int[] arrayOfInt = this.decodeMiddleCounters;
    arrayOfInt[0] = 0;
    arrayOfInt[1] = 0;
    arrayOfInt[2] = 0;
    arrayOfInt[3] = 0;
    int n = paramBitArray.getSize();
    int i = paramArrayOfInt[1];
    int m = 0;
    int j = 0;
    int k;
    if ((m < 5) && (i < n))
    {
      int i1 = UPCEANReader.decodeDigit(paramBitArray, arrayOfInt, i, UPCEANReader.L_AND_G_PATTERNS);
      paramStringBuffer.append((char)(i1 % 10 + 48));
      k = 0;
      while (k < arrayOfInt.length)
      {
        i += arrayOfInt[k];
        k += 1;
      }
      if (i1 < 10)
        break label262;
      j = 1 << 4 - m | j;
    }
    label262: 
    while (true)
    {
      k = i;
      if (m != 4)
      {
        k = i;
        while (true)
        {
          i = k;
          if (k >= n)
            break;
          i = k;
          if (paramBitArray.get(k))
            break;
          k += 1;
        }
        while (true)
        {
          k = i;
          if (i >= n)
            break;
          k = i;
          if (!paramBitArray.get(i))
            break;
          i += 1;
        }
      }
      m += 1;
      i = k;
      break;
      if (paramStringBuffer.length() != 5)
        throw NotFoundException.getNotFoundInstance();
      j = determineCheckDigit(j);
      if (extensionChecksum(paramStringBuffer.toString()) != j)
        throw NotFoundException.getNotFoundInstance();
      return i;
    }
  }

  Result decodeRow(int paramInt1, BitArray paramBitArray, int paramInt2)
    throws NotFoundException
  {
    Object localObject2 = UPCEANReader.findGuardPattern(paramBitArray, paramInt2, false, EXTENSION_START_PATTERN);
    Object localObject1 = this.decodeRowStringBuffer;
    ((StringBuffer)localObject1).setLength(0);
    paramInt2 = decodeMiddle(paramBitArray, (int[])localObject2, (StringBuffer)localObject1);
    localObject1 = ((StringBuffer)localObject1).toString();
    paramBitArray = parseExtensionString((String)localObject1);
    int i = localObject2[0];
    localObject2 = new ResultPoint((localObject2[1] + i) / 2.0F, paramInt1);
    ResultPoint localResultPoint = new ResultPoint(paramInt2, paramInt1);
    BarcodeFormat localBarcodeFormat = BarcodeFormat.UPC_EAN_EXTENSION;
    localObject1 = new Result((String)localObject1, null, new ResultPoint[] { localObject2, localResultPoint }, localBarcodeFormat);
    if (paramBitArray != null)
      ((Result)localObject1).putAllMetadata(paramBitArray);
    return localObject1;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.UPCEANExtensionSupport
 * JD-Core Version:    0.6.2
 */