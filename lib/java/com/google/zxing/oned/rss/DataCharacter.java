package com.google.zxing.oned.rss;

public class DataCharacter
{
  private final int checksumPortion;
  private final int value;

  public DataCharacter(int paramInt1, int paramInt2)
  {
    this.value = paramInt1;
    this.checksumPortion = paramInt2;
  }

  public int getChecksumPortion()
  {
    return this.checksumPortion;
  }

  public int getValue()
  {
    return this.value;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.rss.DataCharacter
 * JD-Core Version:    0.6.2
 */