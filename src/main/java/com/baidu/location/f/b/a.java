package com.baidu.location.f.b;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class a
{
  private static final String a = "AES";
  private static final String jdField_if = "AES/CBC/PKCS5Padding";

  public static byte[] a(String paramString1, String paramString2, byte[] paramArrayOfByte)
    throws Exception
  {
    paramString2 = new SecretKeySpec(paramString2.getBytes(), "AES");
    Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    localCipher.init(2, paramString2, new IvParameterSpec(paramString1.getBytes()));
    return localCipher.doFinal(paramArrayOfByte);
  }

  public static byte[] jdField_if(String paramString1, String paramString2, byte[] paramArrayOfByte)
    throws Exception
  {
    paramString2 = new SecretKeySpec(paramString2.getBytes(), "AES");
    Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    localCipher.init(1, paramString2, new IvParameterSpec(paramString1.getBytes()));
    return localCipher.doFinal(paramArrayOfByte);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.f.b.a
 * JD-Core Version:    0.6.2
 */