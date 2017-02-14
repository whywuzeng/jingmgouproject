package cn.jpush.android.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Inflater;

public final class aa
{
  private static final String z;

  static
  {
    Object localObject1 = "\002!mIU".toCharArray();
    int j = localObject1.length;
    int m = 0;
    int i = 0;
    Object localObject2 = localObject1;
    int k = j;
    label30: int n;
    if (j <= 1)
    {
      m = i;
      k = i;
      localObject2 = localObject1;
      n = localObject2[k];
      switch (m % 5)
      {
      default:
        i = 109;
      case 0:
      case 1:
      case 2:
      case 3:
      }
    }
    while (true)
    {
      localObject2[k] = ((char)(i ^ n));
      m += 1;
      if (j == 0)
      {
        k = j;
        break label30;
      }
      k = j;
      localObject2 = localObject1;
      localObject1 = localObject2;
      j = k;
      i = m;
      if (k > m)
        break;
      z = new String(localObject2).intern();
      return;
      i = 87;
      continue;
      i = 117;
      continue;
      i = 43;
      continue;
      i = 100;
    }
  }

  private static String a(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0))
      return "";
    Inflater localInflater = new Inflater();
    localInflater.reset();
    localInflater.setInput(paramArrayOfByte);
    paramArrayOfByte = new ByteArrayOutputStream();
    try
    {
      byte[] arrayOfByte = new byte[1024];
      while (!localInflater.finished())
        paramArrayOfByte.write(arrayOfByte, 0, localInflater.inflate(arrayOfByte));
    }
    catch (Exception localException)
    {
      x.j();
      localInflater.end();
      try
      {
        paramArrayOfByte.close();
        return "";
      }
      catch (IOException paramArrayOfByte)
      {
        x.j();
        return "";
      }
      String str = new String(paramArrayOfByte.toByteArray(), z);
      localInflater.end();
      try
      {
        paramArrayOfByte.close();
        return str;
      }
      catch (IOException paramArrayOfByte)
      {
        x.j();
        return "";
      }
    }
    finally
    {
      localInflater.end();
      try
      {
        paramArrayOfByte.close();
        throw localObject;
      }
      catch (IOException paramArrayOfByte)
      {
        x.j();
      }
    }
    return "";
  }

  public static String a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = b(paramInt2);
    System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, 0, arrayOfByte.length);
    return a(arrayOfByte);
  }

  private static byte[] a(int paramInt)
  {
    byte[] arrayOfByte = new byte[4];
    int i = 0;
    while (i < 4)
    {
      arrayOfByte[i] = ((byte)(paramInt >>> (arrayOfByte.length - 1 - i) * 8 & 0xFF));
      i += 1;
    }
    return arrayOfByte;
  }

  public static byte[] a(int paramInt1, int paramInt2)
  {
    int j = 0;
    byte[] arrayOfByte1 = a(paramInt1);
    int i = 2;
    byte[] arrayOfByte2 = new byte[2];
    paramInt1 = arrayOfByte1.length;
    if (2 > arrayOfByte1.length)
    {
      paramInt2 = 2 - arrayOfByte1.length;
      i = arrayOfByte1.length;
      paramInt1 = j;
    }
    while (true)
    {
      System.arraycopy(arrayOfByte1, paramInt1, arrayOfByte2, paramInt2, i);
      return arrayOfByte2;
      paramInt2 = 0;
      paramInt1 -= 2;
    }
  }

  public static byte[] a(byte[] paramArrayOfByte, String paramString, int paramInt)
  {
    try
    {
      paramString = paramString.getBytes();
      System.arraycopy(paramString, 0, paramArrayOfByte, paramInt, paramString.length);
      return paramArrayOfByte;
    }
    catch (Exception paramArrayOfByte)
    {
    }
    return null;
  }

  public static int b(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = b(paramInt2);
    System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, 0, arrayOfByte.length);
    return (int)b(arrayOfByte);
  }

  private static final long b(byte[] paramArrayOfByte)
  {
    long l = 0L;
    int i = 0;
    while (i < paramArrayOfByte.length - 1)
    {
      l += ((paramArrayOfByte[i] & 0xFF) << (paramArrayOfByte.length - 1 - i) * 8);
      i += 1;
    }
    return (paramArrayOfByte[(paramArrayOfByte.length - 1)] & 0xFF) + l;
  }

  private static byte[] b(int paramInt)
  {
    byte[] arrayOfByte = new byte[paramInt];
    paramInt = 0;
    while (paramInt < arrayOfByte.length)
    {
      arrayOfByte[paramInt] = 0;
      paramInt += 1;
    }
    return arrayOfByte;
  }

  public static long c(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = b(8);
    System.arraycopy(paramArrayOfByte, 21, arrayOfByte, 0, arrayOfByte.length);
    return b(arrayOfByte);
  }

  public static String d(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    try
    {
      byte[] arrayOfByte = b(paramInt2);
      System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, 0, arrayOfByte.length);
      paramArrayOfByte = new String(arrayOfByte, z);
      return paramArrayOfByte;
    }
    catch (Exception paramArrayOfByte)
    {
      x.j();
    }
    return "";
  }

  public static byte[] e(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    try
    {
      System.arraycopy(a(paramInt1), 0, paramArrayOfByte, paramInt2, 4);
      return paramArrayOfByte;
    }
    catch (Exception paramArrayOfByte)
    {
      x.j();
    }
    return null;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.util.aa
 * JD-Core Version:    0.6.2
 */