package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

final class AI013x0x1xDecoder extends AI01weightDecoder
{
  private static final int dateSize = 16;
  private static final int headerSize = 8;
  private static final int weightSize = 20;
  private final String dateCode;
  private final String firstAIdigits;

  AI013x0x1xDecoder(BitArray paramBitArray, String paramString1, String paramString2)
  {
    super(paramBitArray);
    this.dateCode = paramString2;
    this.firstAIdigits = paramString1;
  }

  private void encodeCompressedDate(StringBuffer paramStringBuffer, int paramInt)
  {
    int i = this.generalDecoder.extractNumericValueFromBitArray(paramInt, 16);
    if (i == 38400)
      return;
    paramStringBuffer.append('(');
    paramStringBuffer.append(this.dateCode);
    paramStringBuffer.append(')');
    paramInt = i % 32;
    int j = i / 32;
    i = j % 12 + 1;
    j /= 12;
    if (j / 10 == 0)
      paramStringBuffer.append('0');
    paramStringBuffer.append(j);
    if (i / 10 == 0)
      paramStringBuffer.append('0');
    paramStringBuffer.append(i);
    if (paramInt / 10 == 0)
      paramStringBuffer.append('0');
    paramStringBuffer.append(paramInt);
  }

  protected void addWeightCode(StringBuffer paramStringBuffer, int paramInt)
  {
    paramInt /= 100000;
    paramStringBuffer.append('(');
    paramStringBuffer.append(this.firstAIdigits);
    paramStringBuffer.append(paramInt);
    paramStringBuffer.append(')');
  }

  protected int checkWeight(int paramInt)
  {
    return paramInt % 100000;
  }

  public String parseInformation()
    throws NotFoundException
  {
    if (this.information.size != 84)
      throw NotFoundException.getNotFoundInstance();
    StringBuffer localStringBuffer = new StringBuffer();
    encodeCompressedGtin(localStringBuffer, 8);
    encodeCompressedWeight(localStringBuffer, 48, 20);
    encodeCompressedDate(localStringBuffer, 68);
    return localStringBuffer.toString();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.rss.expanded.decoders.AI013x0x1xDecoder
 * JD-Core Version:    0.6.2
 */