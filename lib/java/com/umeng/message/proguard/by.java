package com.umeng.message.proguard;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class by
{
  public static final int a = 128;
  public static final int b = 0;
  public static final int c = 1;
  public static final int d = 2;
  private static final String e = "AES/CBC/PKCS5Padding";
  private static byte[] f = { 82, 22, 50, 44, -16, 124, -40, -114, -87, -40, 37, 23, -56, 23, -33, 75 };
  private static ThreadLocal<Cipher> g = new ThreadLocal();
  private static final AlgorithmParameterSpec h = new IvParameterSpec(f);
  private static final SecureRandom i = new SecureRandom();
  private static final String j = "HmacSHA1";

  public static final String a(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = new SecretKeySpec(paramString1.getBytes(), "HmacSHA1");
      Mac localMac = Mac.getInstance("HmacSHA1");
      localMac.init(paramString1);
      paramString1 = bv.a(localMac.doFinal(paramString2.getBytes()));
      return paramString1;
    }
    catch (Throwable paramString1)
    {
    }
    return null;
  }

  public static final String a(String paramString1, String paramString2, int paramInt)
  {
    try
    {
      paramString1 = a(paramString1.getBytes(), paramInt);
      paramString1 = bv.a(a(paramString2.getBytes("utf-8"), paramString1), 8);
      return paramString1;
    }
    catch (Throwable paramString1)
    {
    }
    return null;
  }

  private static final Cipher a()
  {
    Cipher localCipher2 = (Cipher)g.get();
    Cipher localCipher1 = localCipher2;
    if (localCipher2 == null);
    try
    {
      localCipher1 = Cipher.getInstance("AES/CBC/PKCS5Padding");
      g.set(localCipher1);
      return localCipher1;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      throw new RuntimeException("get Chipher error:" + localNoSuchAlgorithmException.getMessage(), localNoSuchAlgorithmException);
    }
    catch (NoSuchPaddingException localNoSuchPaddingException)
    {
      throw new RuntimeException("get Chipher error:" + localNoSuchPaddingException.getMessage(), localNoSuchPaddingException);
    }
  }

  private static final Cipher a(SecretKeySpec paramSecretKeySpec, int paramInt)
  {
    Cipher localCipher = a();
    try
    {
      localCipher.init(paramInt, paramSecretKeySpec, h, i);
      return localCipher;
    }
    catch (InvalidKeyException paramSecretKeySpec)
    {
      throw new RuntimeException("init Chipher error:" + paramSecretKeySpec.getMessage(), paramSecretKeySpec);
    }
    catch (InvalidAlgorithmParameterException paramSecretKeySpec)
    {
      throw new RuntimeException("init Chipher error:" + paramSecretKeySpec.getMessage(), paramSecretKeySpec);
    }
    catch (IllegalArgumentException paramSecretKeySpec)
    {
    }
    throw new RuntimeException("init Chipher error:" + paramSecretKeySpec.getMessage(), paramSecretKeySpec);
  }

  private static final SecretKeySpec a(byte[] paramArrayOfByte, int paramInt)
    throws IllegalArgumentException
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("secret key can not be null");
    byte[] arrayOfByte2 = new byte[16];
    byte[] arrayOfByte1;
    switch (paramInt)
    {
    default:
      arrayOfByte1 = arrayOfByte2;
    case 0:
    case 1:
    case 2:
    }
    while (true)
    {
      return new SecretKeySpec(arrayOfByte1, "AES");
      arrayOfByte1 = arrayOfByte2;
      if (128 != paramArrayOfByte.length * 8)
      {
        System.arraycopy(paramArrayOfByte, 0, arrayOfByte2, 0, 16);
        arrayOfByte1 = arrayOfByte2;
        continue;
        System.arraycopy(b(paramArrayOfByte), 0, arrayOfByte2, 0, 16);
        arrayOfByte1 = arrayOfByte2;
        continue;
        arrayOfByte1 = a(paramArrayOfByte);
      }
    }
  }

  private static final byte[] a(byte[] paramArrayOfByte)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramArrayOfByte);
      paramArrayOfByte = localMessageDigest.digest();
      return paramArrayOfByte;
    }
    catch (Throwable paramArrayOfByte)
    {
    }
    throw new RuntimeException("md5 value Throwable", paramArrayOfByte);
  }

  private static final byte[] a(byte[] paramArrayOfByte, SecretKeySpec paramSecretKeySpec)
    throws IllegalArgumentException
  {
    try
    {
      paramArrayOfByte = a(paramSecretKeySpec, 1).doFinal(paramArrayOfByte);
      return paramArrayOfByte;
    }
    catch (IllegalBlockSizeException paramArrayOfByte)
    {
      throw new IllegalArgumentException("AES decrypt error:" + paramArrayOfByte.getMessage(), paramArrayOfByte);
    }
    catch (BadPaddingException paramArrayOfByte)
    {
    }
    throw new IllegalArgumentException("AES decrypt error:" + paramArrayOfByte.getMessage(), paramArrayOfByte);
  }

  public static final byte[] a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt)
  {
    try
    {
      paramArrayOfByte1 = b(paramArrayOfByte1, a(paramArrayOfByte2, paramInt));
      return paramArrayOfByte1;
    }
    catch (IllegalArgumentException paramArrayOfByte1)
    {
    }
    throw new IllegalArgumentException("AES encrypt error:" + paramArrayOfByte1.getMessage(), paramArrayOfByte1);
  }

  public static final String b(String paramString1, String paramString2, int paramInt)
  {
    try
    {
      paramString1 = a(paramString1.getBytes(), paramInt);
      paramString1 = new String(b(bv.a(paramString2, 8), paramString1), "utf-8");
      return paramString1;
    }
    catch (Throwable paramString1)
    {
    }
    return null;
  }

  private static final byte[] b(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      return null;
    byte[] arrayOfByte = new byte[paramArrayOfByte.length];
    int k = 0;
    while (k < paramArrayOfByte.length)
    {
      arrayOfByte[k] = paramArrayOfByte[(paramArrayOfByte.length - 1 - k)];
      k += 1;
    }
    return arrayOfByte;
  }

  private static final byte[] b(byte[] paramArrayOfByte, SecretKeySpec paramSecretKeySpec)
    throws IllegalArgumentException
  {
    try
    {
      paramArrayOfByte = a(paramSecretKeySpec, 2).doFinal(paramArrayOfByte);
      return paramArrayOfByte;
    }
    catch (IllegalBlockSizeException paramArrayOfByte)
    {
      throw new IllegalArgumentException("AES decrypt error:" + paramArrayOfByte.getMessage(), paramArrayOfByte);
    }
    catch (BadPaddingException paramArrayOfByte)
    {
    }
    throw new IllegalArgumentException("AES decrypt error:" + paramArrayOfByte.getMessage(), paramArrayOfByte);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.by
 * JD-Core Version:    0.6.2
 */