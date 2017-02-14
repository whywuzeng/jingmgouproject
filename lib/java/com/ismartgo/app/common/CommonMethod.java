package com.ismartgo.app.common;

import java.text.SimpleDateFormat;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonMethod {
    public static String digitalFormat(int paramInt) {
        if (paramInt > 9999) {
            String str2 = String.valueOf(paramInt / 10000.0F);
            String str1 = "";
            if (str2.contains(".")) {
                str1 = str2.substring(str2.indexOf(".") + 1, str2.indexOf(".") + 2);
            }
            if (str1.equals("0")) {
            }
            for (str1 = str2.substring(0, str2.indexOf(".")); ; str1 = str2.substring(0, str2.indexOf(".") + 2)) {
                return str1 + "万";
            }
        }
        return String.valueOf(paramInt);
    }

    public static String formatMap(Map<String, String> paramMap) {
        paramMap = new ArrayList(paramMap.entrySet());
        Collections.sort(paramMap, new Comparator() {
            public int compare(Map.Entry<String, String> paramAnonymousEntry1, Map.Entry<String, String> paramAnonymousEntry2) {
                return ((String) paramAnonymousEntry1.getKey()).toLowerCase().toString().compareTo(((String) paramAnonymousEntry2.getKey()).toLowerCase());
            }
        });
        StringBuilder localStringBuilder = new StringBuilder();
        int i = 0;
        if (i >= paramMap.size()) {
            if (localStringBuilder.length() > 1) {
                localStringBuilder.deleteCharAt(localStringBuilder.length() - 1);
            }
            return localStringBuilder.toString();
        }
        Map.Entry localEntry = (Map.Entry) paramMap.get(i);
        if ((isEmpty((String) localEntry.getKey())) || (isEmpty((String) localEntry.getValue()))) {
        }
        for (; ; ) {
            i += 1;
            break;
            localStringBuilder.append(((String) localEntry.getKey()).toLowerCase()).append("=").append(URLEncoder.encode((String) localEntry.getValue())).append("&");
        }
    }

    public static int getAppVersionCode(Context paramContext) {
        try {
            int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
            return i;
        } catch (PackageManager.NameNotFoundException paramContext) {
            paramContext.printStackTrace();
        }
        return 0;
    }

    public static String getCurrentDate(long paramLong, String paramString) {
        return new SimpleDateFormat(paramString).format(new Date(paramLong));
    }

    public static String getCurrentDate(String paramString) {
        return new SimpleDateFormat(paramString).format(new Date());
    }

    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static String getMd5ByFile(File paramFile)
            throws FileNotFoundException {
        File localFile = null;
        FileInputStream localFileInputStream = new FileInputStream(paramFile);
        try {
            paramFile = localFileInputStream.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, paramFile.length());
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(paramFile);
            paramFile = new BigInteger(1, localMessageDigest.digest()).toString(16);
            localFile = paramFile;
            if (localFileInputStream != null) {
            }
            return paramFile;
        } catch (Exception paramFile) {
            do {
                paramFile = paramFile;
                paramFile.printStackTrace();
            } while (localFileInputStream == null);
            try {
                localFileInputStream.close();
                return null;
            } catch (IOException paramFile) {
                paramFile.printStackTrace();
                return null;
            }
        } finally {
            if (localFileInputStream != null) {
            }
            try {
                localFileInputStream.close();
                throw paramFile;
            } catch (IOException localIOException1) {
                for (; ; ) {
                    localIOException1.printStackTrace();
                }
            }
        }
    }

    public static void install(Context paramContext, String paramString) {
        Intent localIntent = new Intent("android.intent.action.VIEW");
        localIntent.setDataAndType(Uri.parse("file://" + paramString), "application/vnd.android.package-archive");
        localIntent.addFlags(268435456);
        paramContext.startActivity(localIntent);
    }

    public static boolean isEmpty(String paramString) {
        return (paramString == null) || (paramString.length() == 0) || (paramString.trim().length() == 0) || ("null".equals(paramString.trim().toLowerCase())) || (paramString.trim().equals(""));
    }

    public static String parseGivedTime(String paramString)
            throws ParseException {
        Calendar localCalendar = Calendar.getInstance();
        paramString = new SimpleDateFormat("yyyy-MM-dd").parse(paramString);
        return localCalendar.get(1) + "年" + (paramString.getMonth() + 1) + "月" + paramString.getDate() + "日";
    }

    public static String parseMonth(String paramString) {
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM");
        Object localObject = null;
        try {
            paramString = localSimpleDateFormat.parse(paramString);
            return paramString.getMonth() + 1 + "月";
        } catch (ParseException paramString) {
            for (; ; ) {
                paramString.printStackTrace();
                paramString = (String) localObject;
            }
        }
    }

    public static String parseTime(String paramString)
            throws ParseException {
        paramString = new SimpleDateFormat("yyyy-MM-dd").parse(paramString);
        return paramString.getMonth() + 1 + "月" + paramString.getDate() + "日";
    }

    public static boolean patternAddress(String paramString) {
        return !Pattern.compile("[`~!@#$%^&*()+=|{}':;',~!@#$%^&*()+=|{}':;',\\[\\]\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]").matcher(paramString).find();
    }
}


//public class CommonMethod
//{
//  public static String formatMap(Map<String, String> paramMap)
//  {
//    paramMap = new ArrayList(paramMap.entrySet());
//    Collections.sort(paramMap, new Comparator()
//    {
//      public int compare(Map.Entry<String, String> paramAnonymousEntry1, Map.Entry<String, String> paramAnonymousEntry2)
//      {
//        return ((String)paramAnonymousEntry1.getKey()).toLowerCase().toString().compareTo(((String)paramAnonymousEntry2.getKey()).toLowerCase());
//      }
//    });
//    StringBuilder localStringBuilder = new StringBuilder();
//    int i = 0;
//    while (true)
//    {
//      if (i >= paramMap.size())
//      {
//        localStringBuilder.deleteCharAt(localStringBuilder.length() - 1);
//        return localStringBuilder.toString();
//      }
//      Map.Entry localEntry = (Map.Entry)paramMap.get(i);
//      localStringBuilder.append(((String)localEntry.getKey()).toLowerCase()).append("=").append(URLEncoder.encode((String)localEntry.getValue())).append("&");
//      i += 1;
//    }
//  }
//
//  public static String getCurrentDate(String paramString)
//  {
//    return new SimpleDateFormat(paramString).format(new Date());
//  }
//
//  public static String getCurrentTime()
//  {
//    return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//  }
//
//  // ERROR //
//  public static String getMd5ByFile(java.io.File paramFile)
//    throws java.io.FileNotFoundException
//  {
//    // Byte code:
//    //   0: aconst_null
//    //   1: astore_1
//    //   2: new 111	java/io/FileInputStream
//    //   5: dup
//    //   6: aload_0
//    //   7: invokespecial 114	java/io/FileInputStream:<init>	(Ljava/io/File;)V
//    //   10: astore_2
//    //   11: aload_2
//    //   12: invokevirtual 118	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
//    //   15: getstatic 124	java/nio/channels/FileChannel$MapMode:READ_ONLY	Ljava/nio/channels/FileChannel$MapMode;
//    //   18: lconst_0
//    //   19: aload_0
//    //   20: invokevirtual 129	java/io/File:length	()J
//    //   23: invokevirtual 135	java/nio/channels/FileChannel:map	(Ljava/nio/channels/FileChannel$MapMode;JJ)Ljava/nio/MappedByteBuffer;
//    //   26: astore_0
//    //   27: ldc 137
//    //   29: invokestatic 143	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
//    //   32: astore_3
//    //   33: aload_3
//    //   34: aload_0
//    //   35: invokevirtual 147	java/security/MessageDigest:update	(Ljava/nio/ByteBuffer;)V
//    //   38: new 149	java/math/BigInteger
//    //   41: dup
//    //   42: iconst_1
//    //   43: aload_3
//    //   44: invokevirtual 153	java/security/MessageDigest:digest	()[B
//    //   47: invokespecial 156	java/math/BigInteger:<init>	(I[B)V
//    //   50: bipush 16
//    //   52: invokevirtual 159	java/math/BigInteger:toString	(I)Ljava/lang/String;
//    //   55: astore_0
//    //   56: aload_0
//    //   57: astore_1
//    //   58: aload_2
//    //   59: ifnull +9 -> 68
//    //   62: aload_2
//    //   63: invokevirtual 162	java/io/FileInputStream:close	()V
//    //   66: aload_0
//    //   67: astore_1
//    //   68: aload_1
//    //   69: areturn
//    //   70: astore_0
//    //   71: aload_0
//    //   72: invokevirtual 165	java/lang/Exception:printStackTrace	()V
//    //   75: aload_2
//    //   76: ifnull -8 -> 68
//    //   79: aload_2
//    //   80: invokevirtual 162	java/io/FileInputStream:close	()V
//    //   83: aconst_null
//    //   84: areturn
//    //   85: astore_0
//    //   86: aload_0
//    //   87: invokevirtual 166	java/io/IOException:printStackTrace	()V
//    //   90: aconst_null
//    //   91: areturn
//    //   92: astore_0
//    //   93: aload_2
//    //   94: ifnull +7 -> 101
//    //   97: aload_2
//    //   98: invokevirtual 162	java/io/FileInputStream:close	()V
//    //   101: aload_0
//    //   102: athrow
//    //   103: astore_1
//    //   104: aload_1
//    //   105: invokevirtual 166	java/io/IOException:printStackTrace	()V
//    //   108: goto -7 -> 101
//    //   111: astore_1
//    //   112: aload_1
//    //   113: invokevirtual 166	java/io/IOException:printStackTrace	()V
//    //   116: aload_0
//    //   117: areturn
//    //
//    // Exception table:
//    //   from	to	target	type
//    //   11	56	70	java/lang/Exception
//    //   79	83	85	java/io/IOException
//    //   11	56	92	finally
//    //   71	75	92	finally
//    //   97	101	103	java/io/IOException
//    //   62	66	111	java/io/IOException
//  }
//
//  public static void install(Context paramContext, String paramString)
//  {
//    Intent localIntent = new Intent("android.intent.action.VIEW");
//    localIntent.setDataAndType(Uri.parse("file://" + paramString), "application/vnd.android.package-archive");
//    localIntent.addFlags(268435456);
//    paramContext.startActivity(localIntent);
//  }
//
//  public static boolean isEmpty(String paramString)
//  {
//    return (paramString == null) || (paramString.length() == 0) || (paramString.trim().length() == 0) || ("null".equals(paramString.trim().toLowerCase()));
//  }
//
//  public static String parseGivedTime(String paramString)
//    throws ParseException
//  {
//    Calendar localCalendar = Calendar.getInstance();
//    paramString = new SimpleDateFormat("yyyy-MM-dd").parse(paramString);
//    return localCalendar.get(1) + "年" + (paramString.getMonth() + 1) + "月" + paramString.getDate() + "日";
//  }
//
//  public static String parseMonth(String paramString)
//  {
//    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM");
//    Object localObject = null;
//    try
//    {
//      paramString = localSimpleDateFormat.parse(paramString);
//      return paramString.getMonth() + 1 + "月";
//    }
//    catch (ParseException paramString)
//    {
//      while (true)
//      {
//        paramString.printStackTrace();
//        paramString = localObject;
//      }
//    }
//  }
//
//  public static String parseTime(String paramString)
//    throws ParseException
//  {
//    paramString = new SimpleDateFormat("yyyy-MM-dd").parse(paramString);
//    return paramString.getMonth() + 1 + "月" + paramString.getDate() + "日";
//  }
//
//  public static boolean patternAddress(String paramString)
//  {
//    return !Pattern.compile("[`~!@#$%^&*()+=|{}':;',~!@#$%^&*()+=|{}':;',\\[\\]\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]").matcher(paramString).find();
//  }
//}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.common.CommonMethod
 * JD-Core Version:    0.6.2
 */