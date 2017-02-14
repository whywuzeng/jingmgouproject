package com.umeng.message.proguard;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public final class aE
  implements Serializable
{
  public static final aE a = a(new byte[0]);
  private static final char[] c = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
  private static final long d = 1L;
  final byte[] b;
  private transient int e;
  private transient String f;

  aE(byte[] paramArrayOfByte)
  {
    this.b = paramArrayOfByte;
  }

  private static int a(char paramChar)
  {
    if ((paramChar >= '0') && (paramChar <= '9'))
      return paramChar - '0';
    if ((paramChar >= 'a') && (paramChar <= 'f'))
      return paramChar - 'a' + 10;
    if ((paramChar >= 'A') && (paramChar <= 'F'))
      return paramChar - 'A' + 10;
    throw new IllegalArgumentException("Unexpected hex digit: " + paramChar);
  }

  public static aE a(InputStream paramInputStream, int paramInt)
    throws IOException
  {
    byte[] arrayOfByte = new byte[paramInt];
    int i = 0;
    while (i < paramInt)
    {
      int j = paramInputStream.read(arrayOfByte, i, paramInt - i);
      if (j == -1)
        throw new EOFException();
      i += j;
    }
    return new aE(arrayOfByte);
  }

  public static aE a(String paramString)
  {
    aE localaE = new aE(paramString.getBytes(aR.a));
    localaE.f = paramString;
    return localaE;
  }

  public static aE a(byte[] paramArrayOfByte)
  {
    return new aE((byte[])paramArrayOfByte.clone());
  }

  public static aE a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = new byte[paramInt2];
    System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, 0, paramInt2);
    return new aE(arrayOfByte);
  }

  private void a(ObjectInputStream paramObjectInputStream)
    throws IOException
  {
    paramObjectInputStream = a(paramObjectInputStream, paramObjectInputStream.readInt());
    try
    {
      Field localField = aE.class.getDeclaredField("b");
      localField.setAccessible(true);
      localField.set(this, paramObjectInputStream.b);
      return;
    }
    catch (NoSuchFieldException paramObjectInputStream)
    {
      throw new AssertionError();
    }
    catch (IllegalAccessException paramObjectInputStream)
    {
    }
    throw new AssertionError();
  }

  private void a(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.writeInt(this.b.length);
    paramObjectOutputStream.write(this.b);
  }

  public static aE b(String paramString)
  {
    paramString = aA.a(paramString);
    if (paramString != null)
      return new aE(paramString);
    return null;
  }

  public static aE c(String paramString)
  {
    if (paramString.length() % 2 != 0)
      throw new IllegalArgumentException("Unexpected hex string: " + paramString);
    byte[] arrayOfByte = new byte[paramString.length() / 2];
    int i = 0;
    while (i < arrayOfByte.length)
    {
      arrayOfByte[i] = ((byte)((a(paramString.charAt(i * 2)) << 4) + a(paramString.charAt(i * 2 + 1))));
      i += 1;
    }
    return a(arrayOfByte);
  }

  public byte a(int paramInt)
  {
    return this.b[paramInt];
  }

  public String a()
  {
    String str = this.f;
    if (str != null)
      return str;
    str = new String(this.b, aR.a);
    this.f = str;
    return str;
  }

  public void a(OutputStream paramOutputStream)
    throws IOException
  {
    paramOutputStream.write(this.b);
  }

  public String b()
  {
    return aA.a(this.b);
  }

  public String c()
  {
    int i = 0;
    char[] arrayOfChar = new char[this.b.length * 2];
    byte[] arrayOfByte = this.b;
    int k = arrayOfByte.length;
    int j = 0;
    while (i < k)
    {
      int m = arrayOfByte[i];
      int n = j + 1;
      arrayOfChar[j] = c[(m >> 4 & 0xF)];
      j = n + 1;
      arrayOfChar[n] = c[(m & 0xF)];
      i += 1;
    }
    return new String(arrayOfChar);
  }

  public aE d()
  {
    int i = 0;
    int j;
    while (true)
    {
      localObject = this;
      if (i >= this.b.length)
        break label111;
      j = this.b[i];
      if ((j >= 65) && (j <= 90))
        break;
      i += 1;
    }
    Object localObject = (byte[])this.b.clone();
    localObject[i] = ((byte)(j + 32));
    i += 1;
    if (i < localObject.length)
    {
      j = localObject[i];
      if ((j < 65) || (j > 90));
      while (true)
      {
        i += 1;
        break;
        localObject[i] = ((byte)(j + 32));
      }
    }
    localObject = new aE((byte[])localObject);
    label111: return localObject;
  }

  public aE e()
  {
    int i = 0;
    int j;
    while (true)
    {
      localObject = this;
      if (i >= this.b.length)
        break label111;
      j = this.b[i];
      if ((j >= 97) && (j <= 122))
        break;
      i += 1;
    }
    Object localObject = (byte[])this.b.clone();
    localObject[i] = ((byte)(j - 32));
    i += 1;
    if (i < localObject.length)
    {
      j = localObject[i];
      if ((j < 97) || (j > 122));
      while (true)
      {
        i += 1;
        break;
        localObject[i] = ((byte)(j - 32));
      }
    }
    localObject = new aE((byte[])localObject);
    label111: return localObject;
  }

  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof aE)) && (Arrays.equals(((aE)paramObject).b, this.b)));
  }

  public int f()
  {
    return this.b.length;
  }

  public byte[] g()
  {
    return (byte[])this.b.clone();
  }

  public int hashCode()
  {
    int i = this.e;
    if (i != 0)
      return i;
    i = Arrays.hashCode(this.b);
    this.e = i;
    return i;
  }

  public String toString()
  {
    if (this.b.length == 0)
      return "ByteString[size=0]";
    if (this.b.length <= 16)
      return String.format("ByteString[size=%s data=%s]", new Object[] { Integer.valueOf(this.b.length), c() });
    try
    {
      String str = String.format("ByteString[size=%s md5=%s]", new Object[] { Integer.valueOf(this.b.length), a(MessageDigest.getInstance("MD5").digest(this.b)).c() });
      return str;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
    }
    throw new AssertionError();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.aE
 * JD-Core Version:    0.6.2
 */