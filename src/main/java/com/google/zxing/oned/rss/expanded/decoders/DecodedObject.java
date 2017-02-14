package com.google.zxing.oned.rss.expanded.decoders;

abstract class DecodedObject
{
  protected final int newPosition;

  DecodedObject(int paramInt)
  {
    this.newPosition = paramInt;
  }

  int getNewPosition()
  {
    return this.newPosition;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.rss.expanded.decoders.DecodedObject
 * JD-Core Version:    0.6.2
 */