package com.umeng.fb.util;

import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class a
{
  public static int a;

  public static String a(byte[] paramArrayOfByte, String paramString)
    throws UnsupportedEncodingException, DataFormatException
  {
    paramArrayOfByte = b(paramArrayOfByte);
    if (paramArrayOfByte != null)
      return new String(paramArrayOfByte, paramString);
    return null;
  }

  public static byte[] a(String paramString1, String paramString2)
    throws IOException
  {
    if (TextUtils.isEmpty(paramString1))
      return null;
    return a(paramString1.getBytes(paramString2));
  }

  public static byte[] a(byte[] paramArrayOfByte)
    throws IOException
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length <= 0))
      return null;
    Deflater localDeflater = new Deflater();
    localDeflater.setInput(paramArrayOfByte);
    localDeflater.finish();
    paramArrayOfByte = new byte[8192];
    a = 0;
    try
    {
      localByteArrayOutputStream = new ByteArrayOutputStream();
      try
      {
        while (!localDeflater.finished())
        {
          int i = localDeflater.deflate(paramArrayOfByte);
          a += i;
          localByteArrayOutputStream.write(paramArrayOfByte, 0, i);
        }
      }
      finally
      {
      }
      if (localByteArrayOutputStream != null)
        localByteArrayOutputStream.close();
      throw paramArrayOfByte;
      localDeflater.end();
      if (localByteArrayOutputStream != null)
        localByteArrayOutputStream.close();
      return localByteArrayOutputStream.toByteArray();
    }
    finally
    {
      while (true)
        ByteArrayOutputStream localByteArrayOutputStream = null;
    }
  }

  public static byte[] b(byte[] paramArrayOfByte)
    throws UnsupportedEncodingException, DataFormatException
  {
    int i = 0;
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0))
      return null;
    Inflater localInflater = new Inflater();
    localInflater.setInput(paramArrayOfByte, 0, paramArrayOfByte.length);
    paramArrayOfByte = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte[1024];
    while (!localInflater.needsInput())
    {
      int j = localInflater.inflate(arrayOfByte);
      paramArrayOfByte.write(arrayOfByte, i, j);
      i += j;
    }
    localInflater.end();
    return paramArrayOfByte.toByteArray();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.util.a
 * JD-Core Version:    0.6.2
 */