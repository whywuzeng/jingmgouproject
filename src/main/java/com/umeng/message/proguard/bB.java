package com.umeng.message.proguard;

import android.text.TextUtils;
import java.io.ByteArrayOutputStream;

public final class bB
{
  private static final String a = "0123456789ABCDEF";

  private static final byte a(byte paramByte1, byte paramByte2)
  {
    return (byte)((byte)(Byte.decode("0x" + new String(new byte[] { paramByte1 })).byteValue() << 4) | Byte.decode("0x" + new String(new byte[] { paramByte2 })).byteValue());
  }

  private static final byte a(char paramChar)
  {
    return (byte)"0123456789ABCDEF".indexOf(paramChar);
  }

  public static final String a(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    while (i < paramString.length())
    {
      localStringBuffer.append(Integer.toHexString(paramString.charAt(i)));
      i += 1;
    }
    return localStringBuffer.toString();
  }

  public static final String a(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder("");
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length <= 0))
      return null;
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      String str = Integer.toHexString(paramArrayOfByte[i] & 0xFF);
      if (str.length() < 2)
        localStringBuilder.append(0);
      localStringBuilder.append(str);
      i += 1;
    }
    return localStringBuilder.toString();
  }

  public static final String b(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    paramString = paramString.getBytes();
    StringBuilder localStringBuilder = new StringBuilder(paramString.length * 2);
    int i = 0;
    while (i < paramString.length)
    {
      localStringBuilder.append("0123456789ABCDEF".charAt((paramString[i] & 0xF0) >> 4));
      localStringBuilder.append("0123456789ABCDEF".charAt((paramString[i] & 0xF) >> 0));
      i += 1;
    }
    return localStringBuilder.toString();
  }

  public static final String c(String paramString)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(paramString.length() / 2);
    int i = 0;
    while (i < paramString.length())
    {
      localByteArrayOutputStream.write("0123456789ABCDEF".indexOf(paramString.charAt(i)) << 4 | "0123456789ABCDEF".indexOf(paramString.charAt(i + 1)));
      i += 2;
    }
    return new String(localByteArrayOutputStream.toByteArray());
  }

  public static final byte[] d(String paramString)
  {
    byte[] arrayOfByte = new byte[6];
    paramString = paramString.getBytes();
    int i = 0;
    while (i < 6)
    {
      arrayOfByte[i] = a(paramString[(i * 2)], paramString[(i * 2 + 1)]);
      i += 1;
    }
    return arrayOfByte;
  }

  public static final byte[] e(String paramString)
  {
    if ((paramString == null) || (paramString.equals("")))
    {
      paramString = null;
      return paramString;
    }
    paramString = paramString.toUpperCase();
    int j = paramString.length() / 2;
    char[] arrayOfChar = paramString.toCharArray();
    byte[] arrayOfByte = new byte[j];
    int i = 0;
    while (true)
    {
      paramString = arrayOfByte;
      if (i >= j)
        break;
      int k = i * 2;
      int m = a(arrayOfChar[k]);
      arrayOfByte[i] = ((byte)(a(arrayOfChar[(k + 1)]) | m << 4));
      i += 1;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.bB
 * JD-Core Version:    0.6.2
 */