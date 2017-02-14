package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

abstract class AI01weightDecoder extends AI01decoder
{
  AI01weightDecoder(BitArray paramBitArray)
  {
    super(paramBitArray);
  }

  protected abstract void addWeightCode(StringBuffer paramStringBuffer, int paramInt);

  protected abstract int checkWeight(int paramInt);

  protected void encodeCompressedWeight(StringBuffer paramStringBuffer, int paramInt1, int paramInt2)
  {
    paramInt1 = this.generalDecoder.extractNumericValueFromBitArray(paramInt1, paramInt2);
    addWeightCode(paramStringBuffer, paramInt1);
    int i = checkWeight(paramInt1);
    paramInt2 = 100000;
    paramInt1 = 0;
    while (paramInt1 < 5)
    {
      if (i / paramInt2 == 0)
        paramStringBuffer.append('0');
      paramInt2 /= 10;
      paramInt1 += 1;
    }
    paramStringBuffer.append(i);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.rss.expanded.decoders.AI01weightDecoder
 * JD-Core Version:    0.6.2
 */