package com.google.zxing.common;

public abstract class ECI
{
  private final int value;

  ECI(int paramInt)
  {
    this.value = paramInt;
  }

  public static ECI getECIByValue(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > 999999))
      throw new IllegalArgumentException("Bad ECI value: " + paramInt);
    if (paramInt < 900)
      return CharacterSetECI.getCharacterSetECIByValue(paramInt);
    return null;
  }

  public int getValue()
  {
    return this.value;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.common.ECI
 * JD-Core Version:    0.6.2
 */