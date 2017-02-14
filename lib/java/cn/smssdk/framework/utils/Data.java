package cn.smssdk.framework.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.Key;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.zip.CRC32;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Data
{
  private static c a = new c();

  public static String AES128Decode(String paramString, byte[] paramArrayOfByte)
  {
    return new String(AES128Decode(paramString.getBytes("UTF-8"), paramArrayOfByte), "UTF-8");
  }

  public static byte[] AES128Decode(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    Object localObject = new byte[16];
    System.arraycopy(paramArrayOfByte1, 0, localObject, 0, Math.min(paramArrayOfByte1.length, 16));
    localObject = new SecretKeySpec((byte[])localObject, "AES");
    paramArrayOfByte1 = Cipher.getInstance("AES/ECB/NoPadding", "BC");
    paramArrayOfByte1.init(2, (Key)localObject);
    localObject = new byte[paramArrayOfByte1.getOutputSize(paramArrayOfByte2.length)];
    int i = paramArrayOfByte1.update(paramArrayOfByte2, 0, paramArrayOfByte2.length, (byte[])localObject, 0);
    paramArrayOfByte1.doFinal((byte[])localObject, i);
    return localObject;
  }

  public static byte[] AES128Encode(String paramString1, String paramString2)
  {
    paramString1 = paramString1.getBytes("UTF-8");
    Object localObject = new byte[16];
    System.arraycopy(paramString1, 0, localObject, 0, Math.min(paramString1.length, 16));
    paramString1 = paramString2.getBytes("UTF-8");
    localObject = new SecretKeySpec((byte[])localObject, "AES");
    paramString2 = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
    paramString2.init(1, (Key)localObject);
    localObject = new byte[paramString2.getOutputSize(paramString1.length)];
    paramString2.doFinal((byte[])localObject, paramString2.update(paramString1, 0, paramString1.length, (byte[])localObject, 0));
    return localObject;
  }

  public static byte[] AES128Encode(byte[] paramArrayOfByte, String paramString)
  {
    paramString = paramString.getBytes("UTF-8");
    Object localObject = new SecretKeySpec(paramArrayOfByte, "AES");
    paramArrayOfByte = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
    paramArrayOfByte.init(1, (Key)localObject);
    localObject = new byte[paramArrayOfByte.getOutputSize(paramString.length)];
    paramArrayOfByte.doFinal((byte[])localObject, paramArrayOfByte.update(paramString, 0, paramString.length, (byte[])localObject, 0));
    return localObject;
  }

  // ERROR //
  public static String Base64AES(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_1
    //   1: aload_0
    //   2: invokestatic 87	cn/smssdk/framework/utils/Data:AES128Encode	(Ljava/lang/String;Ljava/lang/String;)[B
    //   5: iconst_0
    //   6: invokestatic 93	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
    //   9: astore_0
    //   10: aload_0
    //   11: astore_1
    //   12: aload_0
    //   13: ldc 95
    //   15: invokevirtual 99	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   18: ifeq +12 -> 30
    //   21: aload_0
    //   22: ldc 95
    //   24: ldc 101
    //   26: invokevirtual 105	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   29: astore_1
    //   30: aload_1
    //   31: areturn
    //   32: astore_1
    //   33: aconst_null
    //   34: astore_0
    //   35: aload_1
    //   36: invokestatic 111	cn/smssdk/framework/utils/d:c	(Ljava/lang/Throwable;)I
    //   39: pop
    //   40: aload_0
    //   41: areturn
    //   42: astore_1
    //   43: goto -8 -> 35
    //
    // Exception table:
    //   from	to	target	type
    //   0	10	32	java/lang/Throwable
    //   12	30	42	java/lang/Throwable
  }

  public static String CRC32(byte[] paramArrayOfByte)
  {
    CRC32 localCRC32 = new CRC32();
    localCRC32.update(paramArrayOfByte);
    long l = localCRC32.getValue();
    paramArrayOfByte = new StringBuilder();
    paramArrayOfByte.append(String.format("%02x", new Object[] { Integer.valueOf((byte)(int)(l >>> 56) & 0xFF) }));
    paramArrayOfByte.append(String.format("%02x", new Object[] { Integer.valueOf((byte)(int)(l >>> 48) & 0xFF) }));
    paramArrayOfByte.append(String.format("%02x", new Object[] { Integer.valueOf((byte)(int)(l >>> 40) & 0xFF) }));
    paramArrayOfByte.append(String.format("%02x", new Object[] { Integer.valueOf((byte)(int)(l >>> 32) & 0xFF) }));
    paramArrayOfByte.append(String.format("%02x", new Object[] { Integer.valueOf((byte)(int)(l >>> 24) & 0xFF) }));
    paramArrayOfByte.append(String.format("%02x", new Object[] { Integer.valueOf((byte)(int)(l >>> 16) & 0xFF) }));
    paramArrayOfByte.append(String.format("%02x", new Object[] { Integer.valueOf((byte)(int)(l >>> 8) & 0xFF) }));
    paramArrayOfByte.append(String.format("%02x", new Object[] { Integer.valueOf((byte)(int)l & 0xFF) }));
    while (paramArrayOfByte.charAt(0) == '0')
      paramArrayOfByte = paramArrayOfByte.deleteCharAt(0);
    return paramArrayOfByte.toString().toLowerCase();
  }

  public static String MD5(File paramFile)
  {
    if (paramFile == null);
    while (true)
    {
      return null;
      try
      {
        paramFile = new FileInputStream(paramFile);
        byte[] arrayOfByte = rawMD5(paramFile);
        paramFile.close();
        if (arrayOfByte != null)
          return b.a(arrayOfByte);
      }
      catch (Exception paramFile)
      {
        d.c(paramFile);
      }
    }
    return null;
  }

  public static String MD5(String paramString)
  {
    if (paramString == null);
    do
    {
      return null;
      paramString = rawMD5(paramString);
    }
    while (paramString == null);
    return b.a(paramString);
  }

  public static String MD5(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null);
    do
    {
      return null;
      paramArrayOfByte = rawMD5(paramArrayOfByte);
    }
    while (paramArrayOfByte == null);
    return b.a(paramArrayOfByte);
  }

  public static byte[] SHA1(String paramString)
  {
    paramString = paramString.getBytes("utf-8");
    MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-1");
    localMessageDigest.update(paramString);
    return localMessageDigest.digest();
  }

  public static String base62(long paramLong)
  {
    String str;
    if (paramLong == 0L)
      str = "0";
    while (paramLong > 0L)
    {
      int i = (int)(paramLong % 62L);
      paramLong /= 62L;
      str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(i) + str;
      continue;
      str = "";
    }
    return str;
  }

  public static String byteToHex(byte[] paramArrayOfByte)
  {
    return byteToHex(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public static String byteToHex(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    while (paramInt1 < paramInt2)
    {
      localStringBuffer.append(String.format("%02x", new Object[] { Byte.valueOf(paramArrayOfByte[paramInt1]) }));
      paramInt1 += 1;
    }
    return localStringBuffer.toString();
  }

  public static String parseJson(HashMap<String, Object> paramHashMap)
  {
    return a.a(paramHashMap);
  }

  public static HashMap<String, Object> parseJson(String paramString)
  {
    return a.a(paramString);
  }

  public static byte[] rawMD5(InputStream paramInputStream)
  {
    if (paramInputStream == null)
      return null;
    try
    {
      byte[] arrayOfByte = new byte[1024];
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      for (int i = paramInputStream.read(arrayOfByte); i != -1; i = paramInputStream.read(arrayOfByte))
        localMessageDigest.update(arrayOfByte, 0, i);
      paramInputStream = localMessageDigest.digest();
      return paramInputStream;
    }
    catch (Throwable paramInputStream)
    {
      d.b(paramInputStream);
    }
    return null;
  }

  public static byte[] rawMD5(String paramString)
  {
    if (paramString == null)
      return null;
    try
    {
      paramString = rawMD5(paramString.getBytes("utf-8"));
      return paramString;
    }
    catch (Exception paramString)
    {
      d.c(paramString);
    }
    return null;
  }

  public static byte[] rawMD5(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      return null;
    try
    {
      ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
      paramArrayOfByte = rawMD5(localByteArrayInputStream);
      localByteArrayInputStream.close();
      return paramArrayOfByte;
    }
    catch (Exception paramArrayOfByte)
    {
      while (true)
      {
        d.c(paramArrayOfByte);
        paramArrayOfByte = null;
      }
    }
  }

  public static String urlEncode(String paramString)
  {
    try
    {
      paramString = urlEncode(paramString, "utf-8");
      return paramString;
    }
    catch (Throwable paramString)
    {
      d.c(paramString);
    }
    return null;
  }

  public static String urlEncode(String paramString1, String paramString2)
  {
    return URLEncoder.encode(paramString1, paramString2).replace("+", "%20");
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.framework.utils.Data
 * JD-Core Version:    0.6.2
 */