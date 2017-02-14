package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

final class AnyAIDecoder extends AbstractExpandedDecoder
{
  private static final int HEADER_SIZE = 5;

  AnyAIDecoder(BitArray paramBitArray)
  {
    super(paramBitArray);
  }

  public String parseInformation()
    throws NotFoundException
  {
    StringBuffer localStringBuffer = new StringBuffer();
    return this.generalDecoder.decodeAllCodes(localStringBuffer, 5);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.rss.expanded.decoders.AnyAIDecoder
 * JD-Core Version:    0.6.2
 */