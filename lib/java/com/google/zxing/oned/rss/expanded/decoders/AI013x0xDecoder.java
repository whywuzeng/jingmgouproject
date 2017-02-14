package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

abstract class AI013x0xDecoder extends AI01weightDecoder
{
  private static final int headerSize = 5;
  private static final int weightSize = 15;

  AI013x0xDecoder(BitArray paramBitArray)
  {
    super(paramBitArray);
  }

  public String parseInformation()
    throws NotFoundException
  {
    if (this.information.size != 60)
      throw NotFoundException.getNotFoundInstance();
    StringBuffer localStringBuffer = new StringBuffer();
    encodeCompressedGtin(localStringBuffer, 5);
    encodeCompressedWeight(localStringBuffer, 45, 15);
    return localStringBuffer.toString();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.rss.expanded.decoders.AI013x0xDecoder
 * JD-Core Version:    0.6.2
 */