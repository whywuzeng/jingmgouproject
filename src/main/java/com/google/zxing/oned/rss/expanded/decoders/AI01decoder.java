package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

abstract class AI01decoder extends AbstractExpandedDecoder
{
  protected static final int gtinSize = 40;

  AI01decoder(BitArray paramBitArray)
  {
    super(paramBitArray);
  }

  private static void appendCheckDigit(StringBuffer paramStringBuffer, int paramInt)
  {
    int i = 0;
    int j = 0;
    while (i < 13)
    {
      int m = paramStringBuffer.charAt(i + paramInt) - '0';
      int k = m;
      if ((i & 0x1) == 0)
        k = m * 3;
      j += k;
      i += 1;
    }
    i = 10 - j % 10;
    paramInt = i;
    if (i == 10)
      paramInt = 0;
    paramStringBuffer.append(paramInt);
  }

  protected void encodeCompressedGtin(StringBuffer paramStringBuffer, int paramInt)
  {
    paramStringBuffer.append("(01)");
    int i = paramStringBuffer.length();
    paramStringBuffer.append('9');
    encodeCompressedGtinWithoutAI(paramStringBuffer, paramInt, i);
  }

  protected void encodeCompressedGtinWithoutAI(StringBuffer paramStringBuffer, int paramInt1, int paramInt2)
  {
    int i = 0;
    while (i < 4)
    {
      int j = this.generalDecoder.extractNumericValueFromBitArray(i * 10 + paramInt1, 10);
      if (j / 100 == 0)
        paramStringBuffer.append('0');
      if (j / 10 == 0)
        paramStringBuffer.append('0');
      paramStringBuffer.append(j);
      i += 1;
    }
    appendCheckDigit(paramStringBuffer, paramInt2);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.rss.expanded.decoders.AI01decoder
 * JD-Core Version:    0.6.2
 */