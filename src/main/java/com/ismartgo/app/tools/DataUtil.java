package com.ismartgo.app.tools;

import android.text.method.NumberKeyListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataUtil
{
  public static NumberKeyListener numKeyListener = new NumberKeyListener()
  {
    protected char[] getAcceptedChars()
    {
      return new char[] { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57 };
    }

    public int getInputType()
    {
      return 1;
    }
  };
  public static NumberKeyListener pwdKeyListener = new NumberKeyListener()
  {
    protected char[] getAcceptedChars()
    {
      return new char[] { 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 109, 110, 108, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 77, 78, 76, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57 };
    }

    public int getInputType()
    {
      return 1;
    }
  };

  public static Boolean isEmail(String paramString)
  {
    try
    {
      boolean bool = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$").matcher(paramString).matches();
      return Boolean.valueOf(bool);
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return Boolean.valueOf(false);
  }

  public static Boolean isUrl(String paramString)
  {
    Object localObject = Boolean.valueOf(false);
    try
    {
      Boolean localBoolean1 = Boolean.valueOf(Pattern.compile("^http://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$").matcher(paramString).matches());
      Boolean localBoolean2 = localBoolean1;
      localObject = localBoolean1;
      if (!localBoolean1.booleanValue())
      {
        localObject = localBoolean1;
        boolean bool = Pattern.compile("^[a-zA-z]+://(w+(-w+)*)(.(w+(-w+)*))*(?S*)?$").matcher(paramString).matches();
        localBoolean2 = Boolean.valueOf(bool);
      }
      return localBoolean2;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return localObject;
  }

  public static String md5(String paramString)
  {
    String str = new String();
    while (true)
    {
      int i;
      try
      {
        Object localObject = MessageDigest.getInstance("MD5");
        ((MessageDigest)localObject).update(paramString.getBytes());
        paramString = ((MessageDigest)localObject).digest();
        localObject = new StringBuffer("");
        i = 0;
        if (i >= paramString.length)
        {
          return ((StringBuffer)localObject).toString();
          if (j < 16)
            ((StringBuffer)localObject).append("0");
          ((StringBuffer)localObject).append(Integer.toHexString(j));
          i += 1;
          continue;
        }
      }
      catch (NoSuchAlgorithmException paramString)
      {
        paramString.printStackTrace();
        return str;
      }
      int k = paramString[i];
      int j = k;
      if (k < 0)
        j = k + 256;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.tools.DataUtil
 * JD-Core Version:    0.6.2
 */