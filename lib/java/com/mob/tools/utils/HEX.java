package com.mob.tools.utils;

public class HEX
{
  private static final char[] DIGITS = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };

  public static byte[] decodeHex(char[] paramArrayOfChar)
  {
    int j = 0;
    int k = paramArrayOfChar.length;
    Object localObject;
    if ((k & 0x1) != 0)
    {
      Ln.w(new RuntimeException("Odd number of characters."));
      localObject = null;
      return localObject;
    }
    byte[] arrayOfByte = new byte[k >> 1];
    int i = 0;
    while (true)
    {
      localObject = arrayOfByte;
      if (j >= k)
        break;
      int m = toDigit(paramArrayOfChar[j], j);
      j += 1;
      int n = toDigit(paramArrayOfChar[j], j);
      j += 1;
      arrayOfByte[i] = ((byte)((m << 4 | n) & 0xFF));
      i += 1;
    }
  }

  public static byte[] decodeHexString(String paramString)
  {
    return decodeHex(paramString.toCharArray());
  }

  public static char[] encodeHex(byte[] paramArrayOfByte)
  {
    int j = 0;
    int k = paramArrayOfByte.length;
    char[] arrayOfChar = new char[k << 1];
    int i = 0;
    while (i < k)
    {
      int m = j + 1;
      arrayOfChar[j] = DIGITS[((paramArrayOfByte[i] & 0xF0) >>> 4)];
      j = m + 1;
      arrayOfChar[m] = DIGITS[(paramArrayOfByte[i] & 0xF)];
      i += 1;
    }
    return arrayOfChar;
  }

  public static String encodeHexString(byte[] paramArrayOfByte)
  {
    return new String(encodeHex(paramArrayOfByte));
  }

  public static byte[] toByte(String paramString)
  {
    if (paramString == null);
    do
    {
      return null;
      i = paramString.length();
    }
    while (i % 2 == 1);
    int j = i / 2;
    byte[] arrayOfByte = new byte[j];
    int i = 0;
    while (i < j)
      try
      {
        arrayOfByte[i] = ((byte)R.parseInt(paramString.substring(i * 2, i * 2 + 2), 16));
        i += 1;
      }
      catch (Throwable paramString)
      {
        Ln.w(paramString);
        return null;
      }
    return arrayOfByte;
  }

  protected static int toDigit(char paramChar, int paramInt)
  {
    int i = Character.digit(paramChar, 16);
    if (i == -1)
      throw new RuntimeException("Illegal hexadecimal charcter " + paramChar + " at index " + paramInt);
    return i;
  }

  public static String toHex(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      localStringBuffer.append(String.format("%02x", new Object[] { Byte.valueOf(paramArrayOfByte[i]) }));
      i += 1;
    }
    return localStringBuffer.toString();
  }

  public byte[] decode(Object paramObject)
  {
    try
    {
      if ((paramObject instanceof String));
      for (paramObject = ((String)paramObject).toCharArray(); ; paramObject = (char[])paramObject)
        return decodeHex(paramObject);
    }
    catch (ClassCastException paramObject)
    {
    }
    throw new RuntimeException(paramObject.getMessage());
  }

  public byte[] decode(byte[] paramArrayOfByte)
  {
    return decodeHex(new String(paramArrayOfByte).toCharArray());
  }

  public byte[] encode(byte[] paramArrayOfByte)
  {
    return new String(encodeHex(paramArrayOfByte)).getBytes();
  }

  public char[] encode(Object paramObject)
  {
    try
    {
      if ((paramObject instanceof String));
      for (paramObject = ((String)paramObject).getBytes(); ; paramObject = (byte[])paramObject)
        return encodeHex(paramObject);
    }
    catch (ClassCastException paramObject)
    {
    }
    throw new RuntimeException(paramObject.getMessage());
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.utils.HEX
 * JD-Core Version:    0.6.2
 */