package com.ab.util;

import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class AbStrUtil
{
  public static int chineseLength(String paramString)
  {
    int i = 0;
    int j = 0;
    int k;
    if (!isEmpty(paramString))
    {
      k = 0;
      i = j;
      j = k;
    }
    while (true)
    {
      if (j >= paramString.length())
        return i;
      k = i;
      if (paramString.substring(j, j + 1).matches("[Α-￥]"))
        k = i + 2;
      j += 1;
      i = k;
    }
  }

  // ERROR //
  public static String convertStreamToString(java.io.InputStream paramInputStream)
  {
    // Byte code:
    //   0: new 36	java/io/BufferedReader
    //   3: dup
    //   4: new 38	java/io/InputStreamReader
    //   7: dup
    //   8: aload_0
    //   9: invokespecial 41	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   12: invokespecial 44	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   15: astore_2
    //   16: new 46	java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial 47	java/lang/StringBuilder:<init>	()V
    //   23: astore_1
    //   24: aload_2
    //   25: invokevirtual 51	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   28: astore_3
    //   29: aload_3
    //   30: ifnonnull +56 -> 86
    //   33: aload_1
    //   34: ldc 53
    //   36: invokevirtual 56	java/lang/StringBuilder:indexOf	(Ljava/lang/String;)I
    //   39: iconst_m1
    //   40: if_icmpeq +37 -> 77
    //   43: aload_1
    //   44: ldc 53
    //   46: invokevirtual 59	java/lang/StringBuilder:lastIndexOf	(Ljava/lang/String;)I
    //   49: aload_1
    //   50: invokevirtual 60	java/lang/StringBuilder:length	()I
    //   53: iconst_1
    //   54: isub
    //   55: if_icmpne +22 -> 77
    //   58: aload_1
    //   59: aload_1
    //   60: ldc 53
    //   62: invokevirtual 59	java/lang/StringBuilder:lastIndexOf	(Ljava/lang/String;)I
    //   65: aload_1
    //   66: ldc 53
    //   68: invokevirtual 59	java/lang/StringBuilder:lastIndexOf	(Ljava/lang/String;)I
    //   71: iconst_1
    //   72: iadd
    //   73: invokevirtual 64	java/lang/StringBuilder:delete	(II)Ljava/lang/StringBuilder;
    //   76: pop
    //   77: aload_0
    //   78: invokevirtual 69	java/io/InputStream:close	()V
    //   81: aload_1
    //   82: invokevirtual 72	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   85: areturn
    //   86: aload_1
    //   87: new 46	java/lang/StringBuilder
    //   90: dup
    //   91: aload_3
    //   92: invokestatic 76	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   95: invokespecial 79	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   98: ldc 53
    //   100: invokevirtual 83	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   103: invokevirtual 72	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   106: invokevirtual 83	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: pop
    //   110: goto -86 -> 24
    //   113: astore_2
    //   114: aload_2
    //   115: invokevirtual 86	java/io/IOException:printStackTrace	()V
    //   118: aload_0
    //   119: invokevirtual 69	java/io/InputStream:close	()V
    //   122: goto -41 -> 81
    //   125: astore_0
    //   126: aload_0
    //   127: invokevirtual 86	java/io/IOException:printStackTrace	()V
    //   130: goto -49 -> 81
    //   133: astore_1
    //   134: aload_0
    //   135: invokevirtual 69	java/io/InputStream:close	()V
    //   138: aload_1
    //   139: athrow
    //   140: astore_0
    //   141: aload_0
    //   142: invokevirtual 86	java/io/IOException:printStackTrace	()V
    //   145: goto -7 -> 138
    //   148: astore_0
    //   149: aload_0
    //   150: invokevirtual 86	java/io/IOException:printStackTrace	()V
    //   153: goto -72 -> 81
    //
    // Exception table:
    //   from	to	target	type
    //   24	29	113	java/io/IOException
    //   33	77	113	java/io/IOException
    //   86	110	113	java/io/IOException
    //   118	122	125	java/io/IOException
    //   24	29	133	finally
    //   33	77	133	finally
    //   86	110	133	finally
    //   114	118	133	finally
    //   134	138	140	java/io/IOException
    //   77	81	148	java/io/IOException
  }

  public static String cutString(String paramString, int paramInt)
  {
    return cutString(paramString, paramInt, "");
  }

  public static String cutString(String paramString1, int paramInt, String paramString2)
  {
    if (strlen(paramString1, "GBK") <= paramInt)
      return paramString1;
    int j = 0;
    StringBuffer localStringBuffer = new StringBuffer(paramInt);
    paramString1 = paramString1.toCharArray();
    int m = paramString1.length;
    int k = 0;
    while (true)
    {
      if (k >= m)
        return localStringBuffer.toString();
      int i = paramString1[k];
      localStringBuffer.append(i);
      if (i > 256)
        j += 2;
      while (true)
      {
        if (j < paramInt)
          break label104;
        if (paramString2 == null)
          break;
        localStringBuffer.append(paramString2);
        break;
        j += 1;
      }
      label104: k += 1;
    }
  }

  public static String cutStringFromChar(String paramString1, String paramString2, int paramInt)
  {
    if (isEmpty(paramString1))
      return "";
    int i = paramString1.indexOf(paramString2);
    if ((i != -1) && (paramString1.length() > i + paramInt))
      return paramString1.substring(i + paramInt);
    return "";
  }

  public static String dateTimeFormat(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    while (true)
    {
      try
      {
        if (isEmpty(paramString))
          return null;
        paramString = paramString.split(" ");
        int i;
        if (paramString.length > 0)
        {
          int k = paramString.length;
          i = 0;
          if (i < k);
        }
        else
        {
          return localStringBuilder.toString();
        }
        String[] arrayOfString = paramString[i];
        int j;
        if (arrayOfString.indexOf("-") != -1)
        {
          arrayOfString = arrayOfString.split("-");
          j = 0;
          if (j < arrayOfString.length)
          {
            localStringBuilder.append(strFormat2(arrayOfString[j]));
            if (j >= arrayOfString.length - 1)
              break label204;
            localStringBuilder.append("-");
            break label204;
          }
        }
        else if (arrayOfString.indexOf(":") != -1)
        {
          localStringBuilder.append(" ");
          arrayOfString = arrayOfString.split(":");
          j = 0;
          if (j < arrayOfString.length)
          {
            localStringBuilder.append(strFormat2(arrayOfString[j]));
            if (j < arrayOfString.length - 1)
              localStringBuilder.append(":");
            j += 1;
            continue;
          }
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return null;
      }
      i += 1;
      continue;
      label204: j += 1;
    }
  }

  public static String getSizeDesc(long paramLong)
  {
    String str = "B";
    long l = paramLong;
    if (paramLong >= 1024L)
    {
      str = "K";
      paramLong >>= 10;
      l = paramLong;
      if (paramLong >= 1024L)
      {
        str = "M";
        paramLong >>= 10;
        l = paramLong;
        if (paramLong >= 1024L)
        {
          str = "G";
          l = paramLong >> 10;
        }
      }
    }
    return l + str;
  }

  public static long ip2int(String paramString)
  {
    paramString = paramString.replace(".", ",").split(",");
    return Long.valueOf(paramString[0]).longValue() << 24 | Long.valueOf(paramString[1]).longValue() << 16 | Long.valueOf(paramString[2]).longValue() << 8 | Long.valueOf(paramString[3]).longValue();
  }

  public static Boolean isChinese(String paramString)
  {
    Boolean localBoolean1 = Boolean.valueOf(true);
    Boolean localBoolean2 = localBoolean1;
    int i;
    if (!isEmpty(paramString))
      i = 0;
    while (true)
    {
      if (i >= paramString.length())
      {
        localBoolean2 = localBoolean1;
        return localBoolean2;
      }
      if (!paramString.substring(i, i + 1).matches("[Α-￥]"))
        localBoolean1 = Boolean.valueOf(false);
      i += 1;
    }
  }

  public static Boolean isContainChinese(String paramString)
  {
    Boolean localBoolean1 = Boolean.valueOf(false);
    Boolean localBoolean2 = localBoolean1;
    int i;
    if (!isEmpty(paramString))
      i = 0;
    while (true)
    {
      if (i >= paramString.length())
      {
        localBoolean2 = localBoolean1;
        return localBoolean2;
      }
      if (paramString.substring(i, i + 1).matches("[Α-￥]"))
        localBoolean1 = Boolean.valueOf(true);
      i += 1;
    }
  }

  public static Boolean isEmail(String paramString)
  {
    Boolean localBoolean = Boolean.valueOf(false);
    if (paramString.matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"))
      localBoolean = Boolean.valueOf(true);
    return localBoolean;
  }

  public static boolean isEmpty(String paramString)
  {
    return (paramString == null) || (paramString.trim().length() == 0);
  }

  public static Boolean isMobileNo(String paramString)
  {
    try
    {
      boolean bool = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$").matcher(paramString).matches();
      return Boolean.valueOf(bool);
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return Boolean.valueOf(false);
  }

  public static Boolean isNumber(String paramString)
  {
    Boolean localBoolean = Boolean.valueOf(false);
    if (paramString.matches("^[0-9]+$"))
      localBoolean = Boolean.valueOf(true);
    return localBoolean;
  }

  public static Boolean isNumberLetter(String paramString)
  {
    Boolean localBoolean = Boolean.valueOf(false);
    if (paramString.matches("^[A-Za-z0-9]+$"))
      localBoolean = Boolean.valueOf(true);
    return localBoolean;
  }

  public static void main(String[] paramArrayOfString)
  {
    System.out.println(dateTimeFormat("2012-3-2 12:2:20"));
  }

  public static String parseEmpty(String paramString)
  {
    String str;
    if (paramString != null)
    {
      str = paramString;
      if (!"null".equals(paramString.trim()));
    }
    else
    {
      str = "";
    }
    return str.trim();
  }

  public static String strFormat2(String paramString)
  {
    String str = paramString;
    try
    {
      if (paramString.length() <= 1)
        str = "0" + paramString;
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return paramString;
  }

  public static int strLength(String paramString)
  {
    int i = 0;
    int j = 0;
    if (!isEmpty(paramString))
    {
      int k = 0;
      i = j;
      j = k;
      if (j < paramString.length());
    }
    else
    {
      return i;
    }
    if (paramString.substring(j, j + 1).matches("[Α-￥]"))
      i += 2;
    while (true)
    {
      j += 1;
      break;
      i += 1;
    }
  }

  public static int strlen(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString1.length() == 0))
      return 0;
    try
    {
      int i = paramString1.getBytes(paramString2).length;
      return i;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return 0;
  }

  public static int subStringLength(String paramString, int paramInt)
  {
    int i = 0;
    int j = 0;
    while (true)
    {
      if (j >= paramString.length())
        return 0;
      if (paramString.substring(j, j + 1).matches("[Α-￥]"))
        i += 2;
      while (i >= paramInt)
      {
        return j;
        i += 1;
      }
      j += 1;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.util.AbStrUtil
 * JD-Core Version:    0.6.2
 */