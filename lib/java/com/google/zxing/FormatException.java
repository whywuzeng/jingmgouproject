package com.google.zxing;

public final class FormatException extends ReaderException
{
  private static final FormatException instance = new FormatException();

  public static FormatException getFormatInstance()
  {
    return instance;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.FormatException
 * JD-Core Version:    0.6.2
 */