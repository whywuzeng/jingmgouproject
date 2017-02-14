package com.mob.tools.utils;

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
import android.view.Display;
import android.view.WindowManager;
import com.mob.tools.network.KVPair;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
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
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class R
{
  private static String cacheRoot;
  private static float density;

  public static void clearCache(Context paramContext)
    throws Throwable
  {
    deleteFileAndFolder(new File(getCachePath(paramContext, null)));
  }

  public static String contentUriToPath(Context paramContext, Uri paramUri)
  {
    if (paramUri == null)
      return null;
    if (new File(paramUri.getPath()).exists())
      return paramUri.getPath();
    try
    {
      if (Build.VERSION.SDK_INT >= 19)
      {
        localObject = Class.forName("android.provider.DocumentsContract");
        Method localMethod = ((Class)localObject).getMethod("isDocumentUri", new Class[] { Context.class, Uri.class });
        localMethod.setAccessible(true);
        if (Boolean.TRUE.equals(localMethod.invoke(null, new Object[] { paramContext, paramUri })))
        {
          localObject = ((Class)localObject).getMethod("getDocumentId", new Class[] { Uri.class });
          ((Method)localObject).setAccessible(true);
          localObject = String.valueOf(localObject.invoke(null, new Object[] { paramUri })).split(":")[1];
          localObject = paramContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[] { "_data" }, "_id=?", new String[] { localObject }, null);
          if (localObject == null)
          {
            paramUri = paramContext.getContentResolver().query(paramUri, null, null, null, null);
            if (paramUri != null)
              if (paramUri.moveToFirst())
              {
                paramContext = paramUri.getString(paramUri.getColumnIndex("_data"));
                paramUri.close();
                return paramContext;
              }
          }
        }
      }
    }
    catch (Throwable paramContext)
    {
      while (true)
      {
        Ln.w(paramContext);
        paramContext = null;
        continue;
        paramContext = null;
        continue;
        paramContext = null;
        continue;
        paramUri = (Uri)localObject;
        continue;
        Object localObject = null;
      }
    }
  }

  public static void copyFile(FileInputStream paramFileInputStream, FileOutputStream paramFileOutputStream)
    throws Throwable
  {
    byte[] arrayOfByte = new byte[65536];
    for (int i = paramFileInputStream.read(arrayOfByte); i > 0; i = paramFileInputStream.read(arrayOfByte))
      paramFileOutputStream.write(arrayOfByte, 0, i);
    paramFileInputStream.close();
    paramFileOutputStream.close();
  }

  public static boolean copyFile(String paramString1, String paramString2)
  {
    if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2)));
    while (!new File(paramString1).exists())
      return false;
    try
    {
      copyFile(new FileInputStream(paramString1), new FileOutputStream(paramString2));
      return true;
    }
    catch (Throwable paramString1)
    {
    }
    return false;
  }

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

  public static long dateToLong(String paramString)
  {
    try
    {
      paramString = new Date(paramString);
      Calendar localCalendar = Calendar.getInstance();
      localCalendar.setTime(paramString);
      long l = localCalendar.getTimeInMillis();
      return l;
    }
    catch (Throwable paramString)
    {
      Ln.w(paramString);
    }
    return 0L;
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

  public static void deleteFileAndFolder(File paramFile)
    throws Throwable
  {
    if ((paramFile == null) || (!paramFile.exists()))
      return;
    if (paramFile.isFile())
    {
      paramFile.delete();
      return;
    }
    String[] arrayOfString = paramFile.list();
    if ((arrayOfString == null) || (arrayOfString.length <= 0))
    {
      paramFile.delete();
      return;
    }
    int j = arrayOfString.length;
    int i = 0;
    if (i < j)
    {
      File localFile = new File(paramFile, arrayOfString[i]);
      if (localFile.isDirectory())
        deleteFileAndFolder(localFile);
      while (true)
      {
        i += 1;
        break;
        localFile.delete();
      }
    }
    paramFile.delete();
  }

  private static int digit(int paramInt1, int paramInt2)
  {
    int i;
    if ((paramInt2 < 2) || (paramInt2 > 36))
    {
      i = -1;
      return i;
    }
    if ((48 <= paramInt1) && (paramInt1 <= 57))
      paramInt1 -= 48;
    while (true)
    {
      i = paramInt1;
      if (paramInt1 < paramInt2)
        break;
      return -1;
      if ((97 <= paramInt1) && (paramInt1 <= 122))
        paramInt1 = paramInt1 - 97 + 10;
      else if ((65 <= paramInt1) && (paramInt1 <= 90))
        paramInt1 = paramInt1 - 65 + 10;
      else
        paramInt1 = -1;
    }
  }

  public static int dipToPx(Context paramContext, int paramInt)
  {
    if (density <= 0.0F)
      density = paramContext.getResources().getDisplayMetrics().density;
    return (int)(paramInt * density + 0.5F);
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

  public static String encodeUrl(ArrayList<KVPair<String>> paramArrayList)
  {
    if (paramArrayList == null)
      return null;
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramArrayList.iterator();
    int i = 0;
    while (localIterator.hasNext())
    {
      paramArrayList = (KVPair)localIterator.next();
      if (i > 0)
        localStringBuilder.append('&');
      String str2 = paramArrayList.name;
      String str1 = (String)paramArrayList.value;
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
    return getResId(paramContext, "drawable", paramString);
  }

  public static String getCachePath(Context paramContext, String paramString)
  {
    String str = cacheRoot;
    Object localObject = str;
    if (TextUtils.isEmpty(str))
      localObject = "ShareSDK";
    str = paramContext.getFilesDir().getAbsolutePath() + "/" + (String)localObject + "/cache/";
    paramContext = DeviceHelper.getInstance(paramContext);
    if (paramContext.getSdcardState());
    for (paramContext = paramContext.getSdcardPath() + "/" + (String)localObject + "/" + paramContext.getPackageName() + "/cache/"; ; paramContext = str)
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
    return getResId(paramContext, "color", paramString);
  }

  public static int getIdRes(Context paramContext, String paramString)
  {
    return getResId(paramContext, "id", paramString);
  }

  public static String getImageCachePath(Context paramContext)
  {
    return getCachePath(paramContext, "images");
  }

  public static int getLayoutRes(Context paramContext, String paramString)
  {
    return getResId(paramContext, "layout", paramString);
  }

  public static int getPluralsRes(Context paramContext, String paramString)
  {
    return getResId(paramContext, "plurals", paramString);
  }

  public static int getRawRes(Context paramContext, String paramString)
  {
    return getResId(paramContext, "raw", paramString);
  }

  public static int getResId(Context paramContext, String paramString1, String paramString2)
  {
    int i;
    if ((paramContext == null) || (TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2)))
      i = 0;
    while (true)
    {
      return i;
      String str = paramContext.getPackageName();
      if (TextUtils.isEmpty(str))
        return 0;
      try
      {
        Class localClass = Class.forName(str + ".R$" + paramString1);
        if (localClass == null)
          i = 0;
        while (true)
        {
          int j = i;
          if (i <= 0)
          {
            i = paramContext.getResources().getIdentifier(paramString2, paramString1, str);
            j = i;
            if (i <= 0)
              j = paramContext.getResources().getIdentifier(paramString2.toLowerCase(), paramString1, str);
          }
          i = j;
          if (j > 0)
            break;
          System.err.println("failed to parse " + paramString1 + " resource \"" + paramString2 + "\"");
          return j;
          Field localField2 = localClass.getField(paramString2);
          Field localField1 = localField2;
          if (localField2 == null)
          {
            localField2 = localClass.getField(paramString2.toLowerCase());
            localField1 = localField2;
            if (localField2 == null)
              i = 0;
          }
          else
          {
            localField1.setAccessible(true);
            i = ((Integer)localField1.get(null)).intValue();
          }
        }
      }
      catch (Throwable localThrowable)
      {
        while (true)
        {
          Ln.w(localThrowable);
          i = 0;
        }
      }
    }
  }

  public static int getScreenHeight(Context paramContext)
  {
    return getScreenSize(paramContext)[1];
  }

  public static int[] getScreenSize(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc_w 446
    //   4: invokevirtual 449	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   7: checkcast 451	android/view/WindowManager
    //   10: astore_0
    //   11: aload_0
    //   12: ifnonnull +26 -> 38
    //   15: iconst_2
    //   16: newarray int
    //   18: dup
    //   19: iconst_0
    //   20: iconst_0
    //   21: iastore
    //   22: dup
    //   23: iconst_1
    //   24: iconst_0
    //   25: iastore
    //   26: areturn
    //   27: astore_0
    //   28: aload_0
    //   29: invokestatic 454	com/mob/tools/utils/Ln:e	(Ljava/lang/Throwable;)I
    //   32: pop
    //   33: aconst_null
    //   34: astore_0
    //   35: goto -24 -> 11
    //   38: aload_0
    //   39: invokeinterface 458 1 0
    //   44: astore_0
    //   45: getstatic 49	android/os/Build$VERSION:SDK_INT	I
    //   48: bipush 13
    //   50: if_icmpge +34 -> 84
    //   53: new 278	android/util/DisplayMetrics
    //   56: dup
    //   57: invokespecial 459	android/util/DisplayMetrics:<init>	()V
    //   60: astore_3
    //   61: aload_0
    //   62: aload_3
    //   63: invokevirtual 465	android/view/Display:getMetrics	(Landroid/util/DisplayMetrics;)V
    //   66: iconst_2
    //   67: newarray int
    //   69: dup
    //   70: iconst_0
    //   71: aload_3
    //   72: getfield 468	android/util/DisplayMetrics:widthPixels	I
    //   75: iastore
    //   76: dup
    //   77: iconst_1
    //   78: aload_3
    //   79: getfield 471	android/util/DisplayMetrics:heightPixels	I
    //   82: iastore
    //   83: areturn
    //   84: new 473	android/graphics/Point
    //   87: dup
    //   88: invokespecial 474	android/graphics/Point:<init>	()V
    //   91: astore_3
    //   92: aload_0
    //   93: invokevirtual 478	java/lang/Object:getClass	()Ljava/lang/Class;
    //   96: ldc_w 480
    //   99: iconst_1
    //   100: anewarray 53	java/lang/Class
    //   103: dup
    //   104: iconst_0
    //   105: ldc_w 473
    //   108: aastore
    //   109: invokevirtual 65	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   112: astore 4
    //   114: aload 4
    //   116: iconst_1
    //   117: invokevirtual 71	java/lang/reflect/Method:setAccessible	(Z)V
    //   120: aload 4
    //   122: aload_0
    //   123: iconst_1
    //   124: anewarray 4	java/lang/Object
    //   127: dup
    //   128: iconst_0
    //   129: aload_3
    //   130: aastore
    //   131: invokevirtual 81	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   134: pop
    //   135: aload_3
    //   136: getfield 483	android/graphics/Point:x	I
    //   139: istore_1
    //   140: aload_3
    //   141: getfield 486	android/graphics/Point:y	I
    //   144: istore_2
    //   145: iconst_2
    //   146: newarray int
    //   148: dup
    //   149: iconst_0
    //   150: iload_1
    //   151: iastore
    //   152: dup
    //   153: iconst_1
    //   154: iload_2
    //   155: iastore
    //   156: areturn
    //   157: astore_0
    //   158: aload_0
    //   159: invokestatic 141	com/mob/tools/utils/Ln:w	(Ljava/lang/Throwable;)I
    //   162: pop
    //   163: iconst_2
    //   164: newarray int
    //   166: dup
    //   167: iconst_0
    //   168: iconst_0
    //   169: iastore
    //   170: dup
    //   171: iconst_1
    //   172: iconst_0
    //   173: iastore
    //   174: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	11	27	java/lang/Throwable
    //   84	145	157	java/lang/Throwable
  }

  public static int getScreenWidth(Context paramContext)
  {
    return getScreenSize(paramContext)[0];
  }

  public static int getStringArrayRes(Context paramContext, String paramString)
  {
    return getResId(paramContext, "array", paramString);
  }

  public static int getStringRes(Context paramContext, String paramString)
  {
    return getResId(paramContext, "string", paramString);
  }

  public static int getStyleRes(Context paramContext, String paramString)
  {
    return getResId(paramContext, "style", paramString);
  }

  public static int getTextLengthInWord(String paramString)
  {
    int i = 0;
    int j;
    if (paramString == null)
    {
      paramString = new char[0];
      j = 0;
      label12: if (i >= paramString.length)
        break label53;
      if (paramString[i] >= 'Ā')
        break label48;
    }
    label48: for (int k = 1; ; k = 2)
    {
      j += k;
      i += 1;
      break label12;
      paramString = paramString.toCharArray();
      break;
    }
    label53: return j;
  }

  private static Throwable invalidInt(String paramString)
    throws Throwable
  {
    throw new Throwable("Invalid int: \"" + paramString + "\"");
  }

  private static Throwable invalidLong(String paramString)
    throws Throwable
  {
    throw new Throwable("Invalid long: \"" + paramString + "\"");
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

  public static int parseInt(String paramString)
    throws Throwable
  {
    return parseInt(paramString, 10);
  }

  public static int parseInt(String paramString, int paramInt)
    throws Throwable
  {
    int i = 1;
    if ((paramInt < 2) || (paramInt > 36))
      throw new Throwable("Invalid radix: " + paramInt);
    if (paramString == null)
      throw invalidInt(paramString);
    int j = paramString.length();
    if (j == 0)
      throw invalidInt(paramString);
    if (paramString.charAt(0) == '-');
    for (boolean bool = true; bool; bool = false)
    {
      if (1 != j)
        break label100;
      throw invalidInt(paramString);
    }
    i = 0;
    label100: return parseInt(paramString, i, paramInt, bool);
  }

  private static int parseInt(String paramString, int paramInt1, int paramInt2, boolean paramBoolean)
    throws Throwable
  {
    int k = -2147483648 / paramInt2;
    int j = 0;
    int m = paramString.length();
    int i = paramInt1;
    paramInt1 = j;
    while (i < m)
    {
      j = digit(paramString.charAt(i), paramInt2);
      if (j == -1)
        throw invalidInt(paramString);
      if (k > paramInt1)
        throw invalidInt(paramString);
      j = paramInt1 * paramInt2 - j;
      if (j > paramInt1)
        throw invalidInt(paramString);
      paramInt1 = j;
      i += 1;
    }
    paramInt2 = paramInt1;
    if (!paramBoolean)
    {
      paramInt1 = -paramInt1;
      paramInt2 = paramInt1;
      if (paramInt1 < 0)
        throw invalidInt(paramString);
    }
    return paramInt2;
  }

  public static long parseLong(String paramString)
    throws Throwable
  {
    return parseLong(paramString, 10);
  }

  public static long parseLong(String paramString, int paramInt)
    throws Throwable
  {
    int i = 1;
    if ((paramInt < 2) || (paramInt > 36))
      throw new Throwable("Invalid radix: " + paramInt);
    if (paramString == null)
      throw invalidLong(paramString);
    int j = paramString.length();
    if (j == 0)
      throw invalidLong(paramString);
    if (paramString.charAt(0) == '-');
    for (boolean bool = true; bool; bool = false)
    {
      if (1 != j)
        break label100;
      throw invalidLong(paramString);
    }
    i = 0;
    label100: return parseLong(paramString, i, paramInt, bool);
  }

  private static long parseLong(String paramString, int paramInt1, int paramInt2, boolean paramBoolean)
    throws Throwable
  {
    long l3 = -9223372036854775808L / paramInt2;
    long l1 = 0L;
    long l4 = paramString.length();
    while (paramInt1 < l4)
    {
      int i = digit(paramString.charAt(paramInt1), paramInt2);
      if (i == -1)
        throw invalidLong(paramString);
      if (l3 > l1)
        throw invalidLong(paramString);
      l2 = paramInt2 * l1 - i;
      if (l2 > l1)
        throw invalidLong(paramString);
      l1 = l2;
      paramInt1 += 1;
    }
    long l2 = l1;
    if (!paramBoolean)
    {
      l1 = -l1;
      l2 = l1;
      if (l1 < 0L)
        throw invalidLong(paramString);
    }
    return l2;
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
    if (density <= 0.0F)
      density = paramContext.getResources().getDisplayMetrics().density;
    return (int)(paramInt / density + 0.5F);
  }

  public static Object readObjectFromFile(String paramString)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (!TextUtils.isEmpty(paramString));
    try
    {
      paramString = new File(paramString);
      boolean bool = paramString.exists();
      if (!bool)
        paramString = null;
      localObject1 = localObject2;
      if (paramString == null);
    }
    catch (Throwable paramString)
    {
      try
      {
        paramString = new ObjectInputStream(new GZIPInputStream(new FileInputStream(paramString)));
        localObject1 = paramString.readObject();
        paramString.close();
        return localObject1;
        paramString = paramString;
        paramString.printStackTrace();
        paramString = null;
      }
      catch (Throwable paramString)
      {
        paramString.printStackTrace();
      }
    }
    return null;
  }

  public static boolean saveObjectToFile(String paramString, Object paramObject)
  {
    if (!TextUtils.isEmpty(paramString))
      try
      {
        paramString = new File(paramString);
        if (paramString.exists())
          paramString.delete();
        if (!paramString.getParentFile().exists())
          paramString.getParentFile().mkdirs();
        paramString.createNewFile();
        if (paramString == null);
      }
      catch (Throwable paramString)
      {
        try
        {
          paramString = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(paramString)));
          paramString.writeObject(paramObject);
          paramString.flush();
          paramString.close();
          return true;
          paramString = paramString;
          paramString.printStackTrace();
          paramString = null;
        }
        catch (Throwable paramString)
        {
          paramString.printStackTrace();
        }
      }
    return false;
  }

  public static void setCacheRoot(String paramString)
  {
    cacheRoot = paramString;
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
      Ln.w(paramString);
    }
    return new Bundle();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.utils.R
 * JD-Core Version:    0.6.2
 */