package com.ab.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.ab.global.AbAppData;
import java.io.File;
import java.io.FileFilter;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class AbAppUtil
{
  public static int getNumCores()
  {
    try
    {
      int i = new File("/sys/devices/system/cpu/").listFiles(new FileFilter()
      {
        public boolean accept(File paramAnonymousFile)
        {
          return Pattern.matches("cpu[0-9]", paramAnonymousFile.getName());
        }
      }).length;
      return i;
    }
    catch (Exception localException)
    {
    }
    return 1;
  }

  // ERROR //
  public static boolean importDatabase(Context paramContext, String paramString, int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 11
    //   3: aconst_null
    //   4: astore 12
    //   6: aconst_null
    //   7: astore 13
    //   9: aconst_null
    //   10: astore 9
    //   12: aconst_null
    //   13: astore 10
    //   15: aconst_null
    //   16: astore 8
    //   18: iconst_0
    //   19: istore_3
    //   20: aload 10
    //   22: astore 6
    //   24: aload 11
    //   26: astore 5
    //   28: aload 12
    //   30: astore 7
    //   32: new 17	java/io/File
    //   35: dup
    //   36: new 31	java/lang/StringBuilder
    //   39: dup
    //   40: ldc 33
    //   42: invokespecial 34	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   45: aload_0
    //   46: invokevirtual 40	android/content/Context:getPackageName	()Ljava/lang/String;
    //   49: invokevirtual 44	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: ldc 46
    //   54: invokevirtual 44	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: aload_1
    //   58: invokevirtual 44	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   61: invokevirtual 49	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   64: invokespecial 22	java/io/File:<init>	(Ljava/lang/String;)V
    //   67: astore 14
    //   69: aload 13
    //   71: astore_1
    //   72: aload 10
    //   74: astore 6
    //   76: aload 11
    //   78: astore 5
    //   80: aload 12
    //   82: astore 7
    //   84: aload 14
    //   86: invokevirtual 53	java/io/File:exists	()Z
    //   89: ifne +133 -> 222
    //   92: aload 10
    //   94: astore 6
    //   96: aload 11
    //   98: astore 5
    //   100: aload 12
    //   102: astore 7
    //   104: aload 14
    //   106: invokevirtual 57	java/io/File:getParentFile	()Ljava/io/File;
    //   109: invokevirtual 53	java/io/File:exists	()Z
    //   112: ifne +24 -> 136
    //   115: aload 10
    //   117: astore 6
    //   119: aload 11
    //   121: astore 5
    //   123: aload 12
    //   125: astore 7
    //   127: aload 14
    //   129: invokevirtual 57	java/io/File:getParentFile	()Ljava/io/File;
    //   132: invokevirtual 60	java/io/File:mkdirs	()Z
    //   135: pop
    //   136: aload 10
    //   138: astore 6
    //   140: aload 11
    //   142: astore 5
    //   144: aload 12
    //   146: astore 7
    //   148: aload 14
    //   150: invokevirtual 63	java/io/File:createNewFile	()Z
    //   153: pop
    //   154: aload 10
    //   156: astore 6
    //   158: aload 11
    //   160: astore 5
    //   162: aload 12
    //   164: astore 7
    //   166: aload_0
    //   167: invokevirtual 67	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   170: iload_2
    //   171: invokevirtual 73	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   174: astore_0
    //   175: aload 10
    //   177: astore 6
    //   179: aload_0
    //   180: astore 5
    //   182: aload_0
    //   183: astore 7
    //   185: new 75	java/io/FileOutputStream
    //   188: dup
    //   189: aload 14
    //   191: invokespecial 78	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   194: astore_1
    //   195: sipush 1024
    //   198: newarray byte
    //   200: astore 5
    //   202: aload_0
    //   203: aload 5
    //   205: invokevirtual 84	java/io/InputStream:read	([B)I
    //   208: istore_2
    //   209: iload_2
    //   210: ifgt +41 -> 251
    //   213: aload_1
    //   214: invokevirtual 87	java/io/FileOutputStream:flush	()V
    //   217: aload_1
    //   218: astore 8
    //   220: aload_0
    //   221: astore_1
    //   222: iconst_1
    //   223: istore 4
    //   225: aload 8
    //   227: ifnull +8 -> 235
    //   230: aload 8
    //   232: invokevirtual 90	java/io/FileOutputStream:close	()V
    //   235: iload 4
    //   237: istore_3
    //   238: aload_1
    //   239: ifnull +10 -> 249
    //   242: aload_1
    //   243: invokevirtual 91	java/io/InputStream:close	()V
    //   246: iload 4
    //   248: istore_3
    //   249: iload_3
    //   250: ireturn
    //   251: aload_1
    //   252: aload 5
    //   254: iconst_0
    //   255: iload_2
    //   256: invokevirtual 95	java/io/FileOutputStream:write	([BII)V
    //   259: goto -57 -> 202
    //   262: astore 8
    //   264: aload_1
    //   265: astore 6
    //   267: aload_0
    //   268: astore 5
    //   270: aload 8
    //   272: invokevirtual 98	java/lang/Exception:printStackTrace	()V
    //   275: aload_1
    //   276: ifnull +7 -> 283
    //   279: aload_1
    //   280: invokevirtual 90	java/io/FileOutputStream:close	()V
    //   283: aload_0
    //   284: ifnull -35 -> 249
    //   287: aload_0
    //   288: invokevirtual 91	java/io/InputStream:close	()V
    //   291: iconst_0
    //   292: ireturn
    //   293: astore_0
    //   294: iconst_0
    //   295: ireturn
    //   296: astore_0
    //   297: aload 6
    //   299: ifnull +8 -> 307
    //   302: aload 6
    //   304: invokevirtual 90	java/io/FileOutputStream:close	()V
    //   307: aload 5
    //   309: ifnull +8 -> 317
    //   312: aload 5
    //   314: invokevirtual 91	java/io/InputStream:close	()V
    //   317: aload_0
    //   318: athrow
    //   319: astore_1
    //   320: goto -37 -> 283
    //   323: astore_1
    //   324: goto -17 -> 307
    //   327: astore_1
    //   328: goto -11 -> 317
    //   331: astore_0
    //   332: goto -97 -> 235
    //   335: astore_0
    //   336: iconst_1
    //   337: ireturn
    //   338: astore 7
    //   340: aload_1
    //   341: astore 6
    //   343: aload_0
    //   344: astore 5
    //   346: aload 7
    //   348: astore_0
    //   349: goto -52 -> 297
    //   352: astore 8
    //   354: aload 9
    //   356: astore_1
    //   357: aload 7
    //   359: astore_0
    //   360: goto -96 -> 264
    //
    // Exception table:
    //   from	to	target	type
    //   195	202	262	java/lang/Exception
    //   202	209	262	java/lang/Exception
    //   213	217	262	java/lang/Exception
    //   251	259	262	java/lang/Exception
    //   287	291	293	java/lang/Exception
    //   32	69	296	finally
    //   84	92	296	finally
    //   104	115	296	finally
    //   127	136	296	finally
    //   148	154	296	finally
    //   166	175	296	finally
    //   185	195	296	finally
    //   270	275	296	finally
    //   279	283	319	java/lang/Exception
    //   302	307	323	java/lang/Exception
    //   312	317	327	java/lang/Exception
    //   230	235	331	java/lang/Exception
    //   242	246	335	java/lang/Exception
    //   195	202	338	finally
    //   202	209	338	finally
    //   213	217	338	finally
    //   251	259	338	finally
    //   32	69	352	java/lang/Exception
    //   84	92	352	java/lang/Exception
    //   104	115	352	java/lang/Exception
    //   127	136	352	java/lang/Exception
    //   148	154	352	java/lang/Exception
    //   166	175	352	java/lang/Exception
    //   185	195	352	java/lang/Exception
  }

  public static void installApk(Context paramContext, File paramFile)
  {
    Intent localIntent = new Intent();
    localIntent.addFlags(268435456);
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.setDataAndType(Uri.fromFile(paramFile), "application/vnd.android.package-archive");
    paramContext.startActivity(localIntent);
  }

  public static boolean is3G(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.getType() == 0);
  }

  public static boolean isGpsEnabled(Context paramContext)
  {
    return ((LocationManager)paramContext.getSystemService("location")).isProviderEnabled("gps");
  }

  public static boolean isNetworkAvailable(Context paramContext)
  {
    boolean bool2 = false;
    try
    {
      paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
      boolean bool1 = bool2;
      if (paramContext != null)
      {
        paramContext = paramContext.getActiveNetworkInfo();
        bool1 = bool2;
        if (paramContext != null)
        {
          bool1 = bool2;
          if (paramContext.isConnected())
          {
            paramContext = paramContext.getState();
            NetworkInfo.State localState = NetworkInfo.State.CONNECTED;
            bool1 = bool2;
            if (paramContext == localState)
              bool1 = true;
          }
        }
      }
      return bool1;
    }
    catch (Exception paramContext)
    {
    }
    return false;
  }

  public static boolean isServiceRunning(Context paramContext, String paramString)
  {
    boolean bool = false;
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(2147483647).iterator();
    while (true)
    {
      if (!paramContext.hasNext())
        return bool;
      if (paramString.equals(((ActivityManager.RunningServiceInfo)paramContext.next()).service.getClassName()))
        bool = true;
    }
  }

  public static boolean isWifi(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.getType() == 1);
  }

  public static boolean isWifiEnabled(Context paramContext)
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    return ((localConnectivityManager.getActiveNetworkInfo() != null) && (localConnectivityManager.getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED)) || (paramContext.getNetworkType() == 3);
  }

  public static void logEndTime(boolean paramBoolean, String paramString1, String paramString2)
  {
    long l = Calendar.getInstance().getTimeInMillis();
    if (paramBoolean)
      Log.d(paramString1, paramString2 + ":" + (l - AbAppData.startLogTimeInMillis) + "ms");
  }

  public static void prepareStartTime()
  {
    AbAppData.startLogTimeInMillis = Calendar.getInstance().getTimeInMillis();
  }

  public static void setDebug(boolean paramBoolean)
  {
    AbAppData.DEBUG = paramBoolean;
  }

  public static boolean stopRunningService(Context paramContext, String paramString)
  {
    Object localObject = null;
    boolean bool = false;
    try
    {
      paramString = new Intent(paramContext, Class.forName(paramString));
      if (paramString != null)
        bool = paramContext.stopService(paramString);
      return bool;
    }
    catch (Exception paramString)
    {
      while (true)
      {
        paramString.printStackTrace();
        paramString = localObject;
      }
    }
  }

  public static void uninstallApk(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.DELETE");
    localIntent.setData(Uri.parse("package:" + paramString));
    paramContext.startActivity(localIntent);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.util.AbAppUtil
 * JD-Core Version:    0.6.2
 */