package cn.smssdk.framework.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class R
{
  private static String a;
  private static float b;

  public static int[] covertTimeInYears(long paramLong)
  {
    paramLong = System.currentTimeMillis() - paramLong;
    if (paramLong <= 0L)
      return new int[] { 0, 0 };
    paramLong /= 1000L;
    if (paramLong < 60L)
      return new int[] { (int)paramLong, 0 };
    paramLong /= 60L;
    if (paramLong < 60L)
      return new int[] { (int)paramLong, 1 };
    paramLong /= 60L;
    if (paramLong < 24L)
      return new int[] { (int)paramLong, 2 };
    paramLong /= 24L;
    if (paramLong < 30L)
      return new int[] { (int)paramLong, 3 };
    paramLong /= 30L;
    if (paramLong < 12L)
      return new int[] { (int)paramLong, 4 };
    return new int[] { (int)(paramLong / 12L), 5 };
  }

  public static long dateStrToLong(String paramString)
  {
    return new SimpleDateFormat("yyyy-MM-dd").parse(paramString, new ParsePosition(0)).getTime();
  }

  public static Bundle decodeUrl(String paramString)
  {
    Bundle localBundle = new Bundle();
    if (paramString != null)
    {
      paramString = paramString.split("&");
      int j = paramString.length;
      int i = 0;
      if (i < j)
      {
        String[] arrayOfString = paramString[i].split("=");
        if ((arrayOfString.length < 2) || (arrayOfString[1] == null))
          localBundle.putString(URLDecoder.decode(arrayOfString[0]), "");
        while (true)
        {
          i += 1;
          break;
          localBundle.putString(URLDecoder.decode(arrayOfString[0]), URLDecoder.decode(arrayOfString[1]));
        }
      }
    }
    return localBundle;
  }

  public static int dipToPx(Context paramContext, int paramInt)
  {
    if (b <= 0.0F)
      b = paramContext.getResources().getDisplayMetrics().density;
    return (int)(paramInt * b + 0.5F);
  }

  public static String encodeUrl(Bundle paramBundle)
  {
    if (paramBundle == null)
      return "";
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramBundle.keySet().iterator();
    int i = 1;
    if (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      Object localObject2 = paramBundle.get(str);
      Object localObject1 = localObject2;
      if (localObject2 == null)
        localObject1 = "";
      if (i != 0)
        i = 0;
      while (true)
      {
        localStringBuilder.append(Data.urlEncode(str) + "=" + Data.urlEncode(String.valueOf(localObject1)));
        break;
        localStringBuilder.append("&");
      }
    }
    return localStringBuilder.toString();
  }

  public static String encodeUrl(ArrayList<cn.smssdk.framework.network.d<String>> paramArrayList)
  {
    if (paramArrayList == null)
      return null;
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramArrayList.iterator();
    int i = 0;
    while (localIterator.hasNext())
    {
      paramArrayList = (cn.smssdk.framework.network.d)localIterator.next();
      if (i > 0)
        localStringBuilder.append('&');
      String str2 = paramArrayList.a;
      String str1 = (String)paramArrayList.b;
      if (str2 != null)
      {
        paramArrayList = str1;
        if (str1 == null)
          paramArrayList = "";
        localStringBuilder.append(Data.urlEncode(str2) + "=" + Data.urlEncode(paramArrayList));
        i += 1;
      }
    }
    return localStringBuilder.toString();
  }

  public static int getBitmapRes(Context paramContext, String paramString)
  {
    String str = paramContext.getPackageName();
    try
    {
      i = getResId(Class.forName(str + ".R$drawable"), paramString);
      int j = i;
      if (i <= 0)
        j = paramContext.getResources().getIdentifier(paramString.toLowerCase(), "drawable", str);
      return j;
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        d.c(localThrowable);
        int i = 0;
      }
    }
  }

  public static String getCachePath(Context paramContext, String paramString)
  {
    String str = a;
    Object localObject = str;
    if (TextUtils.isEmpty(str))
      localObject = "ShareSDK";
    str = paramContext.getFilesDir().getAbsolutePath() + "/" + (String)localObject + "/cache/";
    paramContext = a.a(paramContext);
    if (paramContext.n());
    for (paramContext = paramContext.o() + "/" + (String)localObject + "/" + paramContext.l() + "/cache/"; ; paramContext = str)
    {
      localObject = paramContext;
      if (!TextUtils.isEmpty(paramString))
        localObject = paramContext + paramString + "/";
      paramContext = new File((String)localObject);
      if (!paramContext.exists())
        paramContext.mkdirs();
      return localObject;
    }
  }

  public static int getColorRes(Context paramContext, String paramString)
  {
    String str = paramContext.getPackageName();
    try
    {
      i = getResId(Class.forName(str + ".R$color"), paramString);
      int j = i;
      if (i <= 0)
        j = paramContext.getResources().getIdentifier(paramString, "color", str);
      return j;
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        d.c(localThrowable);
        int i = 0;
      }
    }
  }

  public static int getIdRes(Context paramContext, String paramString)
  {
    String str = paramContext.getPackageName();
    try
    {
      i = getResId(Class.forName(str + ".R$id"), paramString);
      int j = i;
      if (i <= 0)
        j = paramContext.getResources().getIdentifier(paramString, "id", str);
      return j;
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        d.c(localThrowable);
        int i = 0;
      }
    }
  }

  public static int getLayoutRes(Context paramContext, String paramString)
  {
    String str = paramContext.getPackageName();
    try
    {
      i = getResId(Class.forName(str + ".R$layout"), paramString);
      int j = i;
      if (i <= 0)
        j = paramContext.getResources().getIdentifier(paramString, "layout", str);
      return j;
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        d.c(localThrowable);
        int i = 0;
      }
    }
  }

  public static int getRawRes(Context paramContext, String paramString)
  {
    String str = paramContext.getPackageName();
    try
    {
      i = getResId(Class.forName(str + ".R$raw"), paramString);
      int j = i;
      if (i <= 0)
        j = paramContext.getResources().getIdentifier(paramString, "raw", str);
      return j;
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        d.c(localThrowable);
        int i = 0;
      }
    }
  }

  public static int getResId(Class<?> paramClass, String paramString)
  {
    if (paramString != null);
    while (true)
    {
      try
      {
        Field localField = paramClass.getField(paramString);
        localField.setAccessible(true);
        i = ((Integer)localField.get(null)).intValue();
        if (i <= 0)
          d.c("resource " + paramClass.getName() + "." + paramString + " not found!", new Object[0]);
        return i;
      }
      catch (Throwable localThrowable1)
      {
        Object localObject = paramString.toLowerCase();
        try
        {
          localObject = paramClass.getField((String)localObject);
          ((Field)localObject).setAccessible(true);
          i = ((Integer)((Field)localObject).get(null)).intValue();
        }
        catch (Throwable localThrowable2)
        {
          i = 0;
        }
        continue;
      }
      int i = 0;
    }
  }

  public static int getScreenHeight(Context paramContext)
  {
    return getScreenSize(paramContext)[1];
  }

  public static int[] getScreenSize(Context paramContext)
  {
    // Byte code:
    //   0: getstatic 308	android/os/Build$VERSION:SDK_INT	I
    //   3: bipush 17
    //   5: if_icmpge +29 -> 34
    //   8: aload_0
    //   9: invokevirtual 91	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   12: invokevirtual 97	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   15: astore_0
    //   16: iconst_2
    //   17: newarray int
    //   19: dup
    //   20: iconst_0
    //   21: aload_0
    //   22: getfield 311	android/util/DisplayMetrics:widthPixels	I
    //   25: iastore
    //   26: dup
    //   27: iconst_1
    //   28: aload_0
    //   29: getfield 314	android/util/DisplayMetrics:heightPixels	I
    //   32: iastore
    //   33: areturn
    //   34: aload_0
    //   35: ldc_w 316
    //   38: invokevirtual 319	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   41: checkcast 321	android/view/WindowManager
    //   44: invokeinterface 325 1 0
    //   49: astore_0
    //   50: new 327	android/graphics/Point
    //   53: dup
    //   54: invokespecial 328	android/graphics/Point:<init>	()V
    //   57: astore_3
    //   58: aload_0
    //   59: invokevirtual 332	java/lang/Object:getClass	()Ljava/lang/Class;
    //   62: ldc_w 334
    //   65: iconst_1
    //   66: anewarray 176	java/lang/Class
    //   69: dup
    //   70: iconst_0
    //   71: ldc_w 327
    //   74: aastore
    //   75: invokevirtual 338	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   78: astore 4
    //   80: aload 4
    //   82: iconst_1
    //   83: invokevirtual 341	java/lang/reflect/Method:setAccessible	(Z)V
    //   86: aload 4
    //   88: aload_0
    //   89: iconst_1
    //   90: anewarray 4	java/lang/Object
    //   93: dup
    //   94: iconst_0
    //   95: aload_3
    //   96: aastore
    //   97: invokevirtual 345	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   100: pop
    //   101: aload_3
    //   102: getfield 348	android/graphics/Point:x	I
    //   105: istore_1
    //   106: aload_3
    //   107: getfield 351	android/graphics/Point:y	I
    //   110: istore_2
    //   111: iconst_2
    //   112: newarray int
    //   114: dup
    //   115: iconst_0
    //   116: iload_1
    //   117: iastore
    //   118: dup
    //   119: iconst_1
    //   120: iload_2
    //   121: iastore
    //   122: areturn
    //   123: astore_0
    //   124: iconst_2
    //   125: newarray int
    //   127: dup
    //   128: iconst_0
    //   129: iconst_0
    //   130: iastore
    //   131: dup
    //   132: iconst_1
    //   133: iconst_0
    //   134: iastore
    //   135: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   34	111	123	java/lang/Throwable
  }

  public static int getScreenWidth(Context paramContext)
  {
    return getScreenSize(paramContext)[0];
  }

  public static int getStringArrayRes(Context paramContext, String paramString)
  {
    String str = paramContext.getPackageName();
    try
    {
      i = getResId(Class.forName(str + ".R$array"), paramString);
      int j = i;
      if (i <= 0)
        j = paramContext.getResources().getIdentifier(paramString.toLowerCase(), "array", str);
      return j;
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        d.c(localThrowable);
        int i = 0;
      }
    }
  }

  public static int getStringRes(Context paramContext, String paramString)
  {
    String str = paramContext.getPackageName();
    try
    {
      i = getResId(Class.forName(str + ".R$string"), paramString);
      int j = i;
      if (i <= 0)
        j = paramContext.getResources().getIdentifier(paramString.toLowerCase(), "string", str);
      return j;
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        d.c(localThrowable);
        int i = 0;
      }
    }
  }

  public static int getStyleRes(Context paramContext, String paramString)
  {
    String str = paramContext.getPackageName();
    try
    {
      i = getResId(Class.forName(str + ".R$style"), paramString);
      int j = i;
      if (i <= 0)
        j = paramContext.getResources().getIdentifier(paramString, "style", str);
      return j;
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        d.c(localThrowable);
        int i = 0;
      }
    }
  }

  public static Date longToDate(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramLong);
    return localCalendar.getTime();
  }

  public static String longToTime(long paramLong, int paramInt)
  {
    String str = "yyyy-MM-dd kk:mm:ss";
    switch (paramInt)
    {
    default:
    case 12:
    case 10:
    case 5:
    case 2:
    case 1:
    }
    while (true)
    {
      return new SimpleDateFormat(str).format(Long.valueOf(paramLong));
      str = "yyyy-MM-dd kk:mm";
      continue;
      str = "yyyy-MM-dd kk";
      continue;
      str = "yyyy-MM-dd";
      continue;
      str = "yyyy-MM";
      continue;
      str = "yyyy";
    }
  }

  // ERROR //
  public static long parseTwitterDate(String paramString)
  {
    // Byte code:
    //   0: iconst_2
    //   1: istore_1
    //   2: iconst_0
    //   3: istore 7
    //   5: aload_0
    //   6: ldc_w 405
    //   9: invokevirtual 67	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   12: astore 10
    //   14: aload 10
    //   16: iconst_1
    //   17: aaload
    //   18: invokevirtual 408	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   21: astore 11
    //   23: aload 11
    //   25: ldc_w 410
    //   28: invokevirtual 414	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   31: istore 9
    //   33: iload 9
    //   35: ifeq +214 -> 249
    //   38: iconst_1
    //   39: istore_1
    //   40: aload 10
    //   42: iconst_2
    //   43: aaload
    //   44: invokestatic 418	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   47: istore_2
    //   48: aload 10
    //   50: iconst_4
    //   51: aaload
    //   52: ldc_w 420
    //   55: invokevirtual 414	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   58: ifeq +15 -> 73
    //   61: aload 10
    //   63: iconst_4
    //   64: aload 10
    //   66: iconst_4
    //   67: aaload
    //   68: iconst_1
    //   69: invokevirtual 424	java/lang/String:substring	(I)Ljava/lang/String;
    //   72: aastore
    //   73: aload 10
    //   75: iconst_4
    //   76: aaload
    //   77: invokestatic 418	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   80: istore_3
    //   81: sipush 1970
    //   84: istore 4
    //   86: aload 10
    //   88: iconst_5
    //   89: aaload
    //   90: invokestatic 418	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   93: istore 5
    //   95: iload 5
    //   97: istore 4
    //   99: aload 10
    //   101: iconst_3
    //   102: aaload
    //   103: ldc_w 426
    //   106: invokevirtual 67	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   109: astore 10
    //   111: aload 10
    //   113: iconst_0
    //   114: aaload
    //   115: invokestatic 418	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   118: istore 5
    //   120: aload 10
    //   122: iconst_1
    //   123: aaload
    //   124: invokestatic 418	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   127: istore 6
    //   129: aload 10
    //   131: iconst_2
    //   132: aaload
    //   133: invokestatic 418	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   136: istore 8
    //   138: iload 8
    //   140: istore 7
    //   142: invokestatic 375	java/util/Calendar:getInstance	()Ljava/util/Calendar;
    //   145: astore 10
    //   147: aload 10
    //   149: iconst_1
    //   150: iload 4
    //   152: invokevirtual 430	java/util/Calendar:set	(II)V
    //   155: aload 10
    //   157: iconst_2
    //   158: iload_1
    //   159: invokevirtual 430	java/util/Calendar:set	(II)V
    //   162: aload 10
    //   164: iconst_5
    //   165: iload_2
    //   166: invokevirtual 430	java/util/Calendar:set	(II)V
    //   169: aload 10
    //   171: bipush 11
    //   173: iload 5
    //   175: iload_3
    //   176: isub
    //   177: bipush 8
    //   179: iadd
    //   180: invokevirtual 430	java/util/Calendar:set	(II)V
    //   183: aload 10
    //   185: bipush 12
    //   187: iload 6
    //   189: invokevirtual 430	java/util/Calendar:set	(II)V
    //   192: aload 10
    //   194: bipush 13
    //   196: iload 7
    //   198: invokevirtual 430	java/util/Calendar:set	(II)V
    //   201: new 107	java/lang/StringBuilder
    //   204: dup
    //   205: invokespecial 108	java/lang/StringBuilder:<init>	()V
    //   208: ldc_w 432
    //   211: invokevirtual 141	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   214: aload_0
    //   215: invokevirtual 141	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   218: ldc_w 434
    //   221: invokevirtual 141	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   224: aload 10
    //   226: invokevirtual 435	java/util/Calendar:toString	()Ljava/lang/String;
    //   229: invokevirtual 141	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   232: invokevirtual 149	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   235: iconst_0
    //   236: anewarray 4	java/lang/Object
    //   239: invokestatic 437	cn/smssdk/framework/utils/d:b	(Ljava/lang/Object;[Ljava/lang/Object;)I
    //   242: pop
    //   243: aload 10
    //   245: invokevirtual 440	java/util/Calendar:getTimeInMillis	()J
    //   248: lreturn
    //   249: aload 11
    //   251: ldc_w 442
    //   254: invokevirtual 414	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   257: ifne -217 -> 40
    //   260: aload 11
    //   262: ldc_w 444
    //   265: invokevirtual 414	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   268: ifeq +8 -> 276
    //   271: iconst_3
    //   272: istore_1
    //   273: goto -233 -> 40
    //   276: aload 11
    //   278: ldc_w 446
    //   281: invokevirtual 414	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   284: ifeq +8 -> 292
    //   287: iconst_4
    //   288: istore_1
    //   289: goto -249 -> 40
    //   292: aload 11
    //   294: ldc_w 448
    //   297: invokevirtual 414	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   300: ifeq +8 -> 308
    //   303: iconst_5
    //   304: istore_1
    //   305: goto -265 -> 40
    //   308: aload 11
    //   310: ldc_w 450
    //   313: invokevirtual 414	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   316: ifeq +9 -> 325
    //   319: bipush 6
    //   321: istore_1
    //   322: goto -282 -> 40
    //   325: aload 11
    //   327: ldc_w 452
    //   330: invokevirtual 414	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   333: ifeq +9 -> 342
    //   336: bipush 7
    //   338: istore_1
    //   339: goto -299 -> 40
    //   342: aload 11
    //   344: ldc_w 454
    //   347: invokevirtual 414	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   350: ifeq +9 -> 359
    //   353: bipush 8
    //   355: istore_1
    //   356: goto -316 -> 40
    //   359: aload 11
    //   361: ldc_w 456
    //   364: invokevirtual 414	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   367: ifeq +9 -> 376
    //   370: bipush 9
    //   372: istore_1
    //   373: goto -333 -> 40
    //   376: aload 11
    //   378: ldc_w 458
    //   381: invokevirtual 414	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   384: ifeq +9 -> 393
    //   387: bipush 10
    //   389: istore_1
    //   390: goto -350 -> 40
    //   393: aload 11
    //   395: ldc_w 460
    //   398: invokevirtual 414	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   401: ifeq +93 -> 494
    //   404: bipush 11
    //   406: istore_1
    //   407: goto -367 -> 40
    //   410: astore 11
    //   412: aload 11
    //   414: invokestatic 199	cn/smssdk/framework/utils/d:c	(Ljava/lang/Throwable;)I
    //   417: pop
    //   418: iconst_1
    //   419: istore_2
    //   420: goto -372 -> 48
    //   423: astore 11
    //   425: aload 11
    //   427: invokestatic 199	cn/smssdk/framework/utils/d:c	(Ljava/lang/Throwable;)I
    //   430: pop
    //   431: iconst_0
    //   432: istore_3
    //   433: goto -352 -> 81
    //   436: astore 11
    //   438: aload 11
    //   440: invokestatic 199	cn/smssdk/framework/utils/d:c	(Ljava/lang/Throwable;)I
    //   443: pop
    //   444: goto -345 -> 99
    //   447: astore_0
    //   448: aload_0
    //   449: invokestatic 199	cn/smssdk/framework/utils/d:c	(Ljava/lang/Throwable;)I
    //   452: pop
    //   453: lconst_0
    //   454: lreturn
    //   455: astore 11
    //   457: aload 11
    //   459: invokestatic 199	cn/smssdk/framework/utils/d:c	(Ljava/lang/Throwable;)I
    //   462: pop
    //   463: iconst_0
    //   464: istore 5
    //   466: goto -346 -> 120
    //   469: astore 11
    //   471: aload 11
    //   473: invokestatic 199	cn/smssdk/framework/utils/d:c	(Ljava/lang/Throwable;)I
    //   476: pop
    //   477: iconst_0
    //   478: istore 6
    //   480: goto -351 -> 129
    //   483: astore 10
    //   485: aload 10
    //   487: invokestatic 199	cn/smssdk/framework/utils/d:c	(Ljava/lang/Throwable;)I
    //   490: pop
    //   491: goto -349 -> 142
    //   494: iconst_0
    //   495: istore_1
    //   496: goto -456 -> 40
    //
    // Exception table:
    //   from	to	target	type
    //   40	48	410	java/lang/Throwable
    //   73	81	423	java/lang/Throwable
    //   86	95	436	java/lang/Throwable
    //   5	33	447	java/lang/Throwable
    //   48	73	447	java/lang/Throwable
    //   99	111	447	java/lang/Throwable
    //   142	249	447	java/lang/Throwable
    //   249	271	447	java/lang/Throwable
    //   276	287	447	java/lang/Throwable
    //   292	303	447	java/lang/Throwable
    //   308	319	447	java/lang/Throwable
    //   325	336	447	java/lang/Throwable
    //   342	353	447	java/lang/Throwable
    //   359	370	447	java/lang/Throwable
    //   376	387	447	java/lang/Throwable
    //   393	404	447	java/lang/Throwable
    //   412	418	447	java/lang/Throwable
    //   425	431	447	java/lang/Throwable
    //   438	444	447	java/lang/Throwable
    //   457	463	447	java/lang/Throwable
    //   471	477	447	java/lang/Throwable
    //   485	491	447	java/lang/Throwable
    //   111	120	455	java/lang/Throwable
    //   120	129	469	java/lang/Throwable
    //   129	138	483	java/lang/Throwable
  }

  public static Uri pathToContentUri(Context paramContext, String paramString)
  {
    Object localObject = null;
    Cursor localCursor = paramContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[] { "_id" }, "_data=? ", new String[] { paramString }, null);
    if ((localCursor != null) && (localCursor.moveToFirst()))
    {
      i = localCursor.getInt(localCursor.getColumnIndex("_id"));
      localObject = Uri.withAppendedPath(Uri.parse("content://media/external/images/media"), "" + i);
    }
    while (!new File(paramString).exists())
    {
      int i;
      return localObject;
    }
    localObject = new ContentValues();
    ((ContentValues)localObject).put("_data", paramString);
    paramString = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    return paramContext.getContentResolver().insert(paramString, (ContentValues)localObject);
  }

  public static int pxToDip(Context paramContext, int paramInt)
  {
    if (b <= 0.0F)
      b = paramContext.getResources().getDisplayMetrics().density;
    return (int)(paramInt / b + 0.5F);
  }

  public static void setCacheRoot(String paramString)
  {
    a = paramString;
  }

  public static long strToDate(String paramString)
  {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(paramString, new ParsePosition(0)).getTime();
  }

  public static String toWordText(String paramString, int paramInt)
  {
    paramString = paramString.toCharArray();
    StringBuilder localStringBuilder = new StringBuilder();
    int m = paramString.length;
    int j = paramInt * 2;
    paramInt = 0;
    while (paramInt < m)
    {
      int i = paramString[paramInt];
      if (i < 256);
      for (int k = 1; ; k = 2)
      {
        j -= k;
        if (j >= 0)
          break;
        return localStringBuilder.toString();
      }
      localStringBuilder.append(i);
      paramInt += 1;
    }
    return localStringBuilder.toString();
  }

  public static Bundle urlToBundle(String paramString)
  {
    int i = paramString.indexOf("://");
    if (i >= 0)
      paramString = "http://" + paramString.substring(i + 1);
    try
    {
      while (true)
      {
        paramString = new URL(paramString);
        Bundle localBundle = decodeUrl(paramString.getQuery());
        localBundle.putAll(decodeUrl(paramString.getRef()));
        return localBundle;
        paramString = "http://" + paramString;
      }
    }
    catch (Throwable paramString)
    {
      d.c(paramString);
    }
    return new Bundle();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.framework.utils.R
 * JD-Core Version:    0.6.2
 */