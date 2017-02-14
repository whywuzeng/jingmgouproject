package com.alibaba.sdk.android.dpa.util;

import android.content.Context;
import android.net.Proxy;
import android.os.Build.VERSION;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.DigestException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.List<Ljava.lang.String;>;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class ToolKit
{
  private static final int BUFFER_SIZE = 4096;
  private static Pattern hostPattern = Pattern.compile("^(?=.{1,255}$)[0-9A-Za-z](?:(?:[0-9A-Za-z]|-){0,61}[0-9A-Za-z])?(?:\\.[0-9A-Za-z](?:(?:[0-9A-Za-z]|-){0,61}[0-9A-Za-z])?)*\\.?$");

  public static byte[] calMd5sum(byte[] paramArrayOfByte)
    throws NoSuchAlgorithmException
  {
    MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
    localMessageDigest.update(paramArrayOfByte);
    return localMessageDigest.digest();
  }

  public static String calMd5sumString(byte[] paramArrayOfByte)
    throws NoSuchAlgorithmException
  {
    return getMd5StrFromBytes(calMd5sum(paramArrayOfByte));
  }

  public static void checkFileReadable(String paramString)
    throws IllegalArgumentException
  {
    paramString = new File(paramString);
    if (!paramString.exists())
      throw new IllegalArgumentException("File not exist. ");
    if (paramString.isDirectory())
      throw new IllegalArgumentException("File is directory. ");
    if (!paramString.canRead())
      throw new IllegalArgumentException("File can not read. ");
  }

  public static void checkFileWritable(String paramString)
    throws IllegalArgumentException
  {
    File localFile = new File(paramString);
    if ((localFile.exists()) && ((localFile.isDirectory()) || (!localFile.canWrite())))
      throw new IllegalArgumentException(paramString + " can not write. ");
    localFile = localFile.getParentFile();
    if (!localFile.exists())
      throw new IllegalArgumentException(localFile + " not exist. ");
    if (!localFile.canWrite())
      throw new IllegalArgumentException(paramString + " can not write. ");
  }

  public static void checkNotEmptyStringArg(String paramString1, String paramString2)
    throws IllegalArgumentException
  {
    if (isEmptyOrNullString(paramString1))
      throw new IllegalArgumentException(paramString2);
  }

  public static void checkNotNullArg(Object paramObject, String paramString)
    throws IllegalArgumentException
  {
    if (paramObject == null)
      throw new IllegalArgumentException(paramString);
  }

  public static void checkNotNullPointer(Object paramObject, String paramString)
    throws NullPointerException
  {
    if (paramObject == null)
      throw new NullPointerException(paramString);
  }

  public static String currentTimeInGMTFormat()
  {
    return date2GMTFormat(new Date());
  }

  public static String date2GMTFormat(Date paramDate)
  {
    if (paramDate == null)
      return null;
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss 'GMT'", Locale.ENGLISH);
    localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    return localSimpleDateFormat.format(paramDate);
  }

  public static boolean detectIfProxyExist(Context paramContext)
  {
    int i;
    String str;
    if (Build.VERSION.SDK_INT >= 14)
    {
      i = 1;
      if (i == 0)
        break label59;
      str = System.getProperty("http.proxyHost");
      paramContext = System.getProperty("http.proxyPort");
      if (paramContext == null)
        break label53;
      label30: i = Integer.parseInt(paramContext);
    }
    for (paramContext = str; ; paramContext = str)
    {
      if ((paramContext == null) || (i == -1))
        break label74;
      return true;
      i = 0;
      break;
      label53: paramContext = "-1";
      break label30;
      label59: str = Proxy.getHost(paramContext);
      i = Proxy.getPort(paramContext);
    }
    label74: return false;
  }

  public static String epoch2GMTFormat(long paramLong)
  {
    return date2GMTFormat(new Date(1000L * paramLong));
  }

  public static String getBase64Md5FromFile(String paramString)
    throws IOException, DigestException, NoSuchAlgorithmException
  {
    MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
    byte[] arrayOfByte = new byte[4096];
    paramString = new FileInputStream(new File(paramString));
    while (true)
    {
      int i = paramString.read(arrayOfByte);
      if (i == -1)
        break;
      localMessageDigest.update(arrayOfByte, 0, i);
    }
    paramString.close();
    return new String(Base64.encode(localMessageDigest.digest(), 0)).trim();
  }

  public static String getHmacSha1Signature(String paramString1, String paramString2)
    throws NoSuchAlgorithmException, InvalidKeyException
  {
    paramString2 = new SecretKeySpec(paramString2.getBytes(), "HmacSHA1");
    Mac localMac = Mac.getInstance("HmacSHA1");
    localMac.init(paramString2);
    return new String(Base64.encode(localMac.doFinal(paramString1.getBytes()), 0)).trim();
  }

  public static String getMd5StrFromBytes(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      localStringBuilder.append(String.format("%02x", new Object[] { Byte.valueOf(paramArrayOfByte[i]) }));
      i += 1;
    }
    return localStringBuilder.toString();
  }

  public static Date gmtFormat2Date(String paramString)
    throws ParseException
  {
    return string2DateInSpecifyFormat("E, dd MMM yyyy HH:mm:ss Z", paramString);
  }

  public static boolean isEmptyOrNullString(String paramString)
  {
    return (paramString == null) || (paramString.equals(""));
  }

  public static int readFullyToBuffer(byte[] paramArrayOfByte, int paramInt, InputStream paramInputStream)
    throws IOException
  {
    int i = 0;
    paramInt = Math.min(paramArrayOfByte.length, paramInt);
    while (paramInt > 0)
    {
      int j = paramInputStream.read(paramArrayOfByte, i, paramInt);
      if (j == -1)
        break;
      i += j;
      paramInt -= j;
    }
    return i;
  }

  public static byte[] readFullyToByteArray(InputStream paramInputStream)
    throws IOException
  {
    int i;
    if ((paramInputStream instanceof ByteArrayInputStream))
    {
      i = paramInputStream.available();
      localObject = new byte[i];
      paramInputStream.read((byte[])localObject, 0, i);
      return localObject;
    }
    Object localObject = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte[4096];
    while (true)
    {
      i = paramInputStream.read(arrayOfByte, 0, 4096);
      if (i == -1)
        break;
      ((ByteArrayOutputStream)localObject).write(arrayOfByte, 0, i);
    }
    return ((ByteArrayOutputStream)localObject).toByteArray();
  }

  public static int readFullyToLocalFile(InputStream paramInputStream, String paramString)
    throws IOException
  {
    paramString = new FileOutputStream(new File(paramString));
    byte[] arrayOfByte = new byte[4096];
    int i = 0;
    while (true)
    {
      int j = paramInputStream.read(arrayOfByte);
      if (-1 == j)
        break;
      paramString.write(arrayOfByte, 0, j);
      i += j;
    }
    paramString.close();
    return i;
  }

  public static String readFullyToString(InputStream paramInputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte[4096];
    StringBuilder localStringBuilder = new StringBuilder();
    while (true)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1)
        break;
      localStringBuilder.append(new String(arrayOfByte, 0, i));
    }
    return localStringBuilder.toString();
  }

  public static Date string2DateInSpecifyFormat(String paramString1, String paramString2)
    throws ParseException
  {
    return new SimpleDateFormat(paramString1, Locale.ENGLISH).parse(paramString2);
  }

  public static String trimAndJoin(List<String> paramList, String paramString)
  {
    if (paramList.isEmpty())
    {
      localObject = "";
      return localObject;
    }
    Object localObject = null;
    Iterator localIterator = paramList.iterator();
    paramList = (List<String>)localObject;
    while (true)
    {
      localObject = paramList;
      if (!localIterator.hasNext())
        break;
      localObject = (String)localIterator.next();
      if (paramList == null)
        paramList = ((String)localObject).trim();
      else
        paramList = paramList + paramString + ((String)localObject).trim();
    }
  }

  public static void validateHostName(String paramString)
  {
    if ((paramString == null) || (!hostPattern.matcher(paramString).matches()))
      throw new IllegalArgumentException("The endpoint hostName is invalid! Must be like \"oss-cn-hangzhou.aliyuncs.com\" format");
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.dpa.util.ToolKit
 * JD-Core Version:    0.6.2
 */