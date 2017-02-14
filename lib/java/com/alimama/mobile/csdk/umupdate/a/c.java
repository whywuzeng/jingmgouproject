package com.alimama.mobile.csdk.umupdate.a;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.TypedValue;
import java.util.List;
import java.util.Locale;

public class c
  implements a
{
  private a a;
  private String b;
  private String c;
  private String d;
  private String e;
  private String f;
  private String g;
  private String h;
  private String i;
  private String j;
  private String k;
  private String l;
  private String m;
  private String n;
  private float o;
  private int p;
  private int q;
  private String r;
  private String s;
  private Context t;
  private boolean u = false;
  private boolean v = false;
  private boolean w = false;
  private boolean x = false;
  private String y = null;

  public static boolean a(Activity paramActivity)
  {
    Intent localIntent = b(paramActivity);
    if (localIntent != null)
    {
      paramActivity.startActivity(localIntent);
      return true;
    }
    return false;
  }

  public static Intent b(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.LAUNCHER");
    localIntent.setPackage(paramContext.getPackageName());
    paramContext = localPackageManager.queryIntentActivities(localIntent, 0);
    if ((paramContext != null) && (paramContext.size() > 0))
    {
      paramContext = (ResolveInfo)paramContext.get(0);
      localIntent.setFlags(67108864);
      localIntent.setComponent(new ComponentName(paramContext.activityInfo.applicationInfo.packageName, paramContext.activityInfo.name));
      return localIntent;
    }
    return null;
  }

  private String g(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return this.a.b;
    return paramString.replaceAll(" ", "");
  }

  public int A()
  {
    Intent localIntent = this.t.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    return localIntent.getIntExtra("level", 0) * 100 / localIntent.getIntExtra("scale", 100);
  }

  public String B()
  {
    return this.h;
  }

  public String[] C()
  {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = this.a.b;
    arrayOfString[1] = this.a.b;
    if (this.t != null)
    {
      if (!e("android.permission.ACCESS_NETWORK_STATE"))
        return arrayOfString;
      Object localObject = (ConnectivityManager)this.t.getSystemService("connectivity");
      if (localObject == null)
        return arrayOfString;
      if (((ConnectivityManager)localObject).getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED)
      {
        arrayOfString[0] = this.a.d;
        return arrayOfString;
      }
      localObject = ((ConnectivityManager)localObject).getNetworkInfo(0);
      if (((NetworkInfo)localObject).getState() == NetworkInfo.State.CONNECTED)
      {
        arrayOfString[0] = this.a.c;
        arrayOfString[1] = ((NetworkInfo)localObject).getSubtypeName();
        return arrayOfString;
      }
    }
    return arrayOfString;
  }

  public Location D()
  {
    try
    {
      Object localObject = (LocationManager)this.t.getSystemService("location");
      if (e("android.permission.ACCESS_FINE_LOCATION"))
      {
        Location localLocation = ((LocationManager)localObject).getLastKnownLocation("gps");
        if (localLocation != null)
        {
          g.b("get location from gps:" + localLocation.getLatitude() + "," + localLocation.getLongitude(), new Object[0]);
          return localLocation;
        }
      }
      if (e("android.permission.ACCESS_COARSE_LOCATION"))
      {
        localObject = ((LocationManager)localObject).getLastKnownLocation("network");
        if (localObject != null)
        {
          g.b("get location from network:" + ((Location)localObject).getLatitude() + "," + ((Location)localObject).getLongitude(), new Object[0]);
          return localObject;
        }
      }
    }
    catch (Exception localException)
    {
      g.a(localException, "", new Object[0]);
    }
    while (true)
    {
      return null;
      g.c("Could not get loction from GPS or Cell-id, lack ACCESS_COARSE_LOCATION or ACCESS_COARSE_LOCATION permission?", new Object[0]);
    }
  }

  public String E()
  {
    return this.r;
  }

  public String F()
  {
    return this.s;
  }

  public float a(float paramFloat)
  {
    return TypedValue.applyDimension(1, paramFloat, this.t.getResources().getDisplayMetrics());
  }

  public void a(Context paramContext)
  {
    a(paramContext, new a());
  }

  // ERROR //
  public void a(Context paramContext, a parama)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokevirtual 168	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   5: putfield 164	com/alimama/mobile/csdk/umupdate/a/c:t	Landroid/content/Context;
    //   8: aload_0
    //   9: aload_2
    //   10: putfield 149	com/alimama/mobile/csdk/umupdate/a/c:a	Lcom/alimama/mobile/csdk/umupdate/a/c$a;
    //   13: aload_0
    //   14: aload_0
    //   15: aload_1
    //   16: invokevirtual 87	android/content/Context:getPackageName	()Ljava/lang/String;
    //   19: invokespecial 317	com/alimama/mobile/csdk/umupdate/a/c:g	(Ljava/lang/String;)Ljava/lang/String;
    //   22: putfield 318	com/alimama/mobile/csdk/umupdate/a/c:b	Ljava/lang/String;
    //   25: aload_1
    //   26: invokevirtual 70	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   29: astore_2
    //   30: aload_0
    //   31: aload_2
    //   32: aload_2
    //   33: aload_0
    //   34: getfield 318	com/alimama/mobile/csdk/umupdate/a/c:b	Ljava/lang/String;
    //   37: iconst_0
    //   38: invokevirtual 322	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   41: invokevirtual 326	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   44: invokevirtual 327	java/lang/Object:toString	()Ljava/lang/String;
    //   47: putfield 328	com/alimama/mobile/csdk/umupdate/a/c:c	Ljava/lang/String;
    //   50: aload_0
    //   51: getfield 328	com/alimama/mobile/csdk/umupdate/a/c:c	Ljava/lang/String;
    //   54: invokestatic 147	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   57: ifeq +11 -> 68
    //   60: aload_0
    //   61: aload_0
    //   62: getfield 318	com/alimama/mobile/csdk/umupdate/a/c:b	Ljava/lang/String;
    //   65: putfield 328	com/alimama/mobile/csdk/umupdate/a/c:c	Ljava/lang/String;
    //   68: aload_1
    //   69: invokevirtual 70	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   72: aload_1
    //   73: invokevirtual 87	android/content/Context:getPackageName	()Ljava/lang/String;
    //   76: iconst_0
    //   77: invokevirtual 332	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   80: astore_2
    //   81: aload_0
    //   82: aload_0
    //   83: aload_2
    //   84: getfield 337	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   87: invokespecial 317	com/alimama/mobile/csdk/umupdate/a/c:g	(Ljava/lang/String;)Ljava/lang/String;
    //   90: putfield 339	com/alimama/mobile/csdk/umupdate/a/c:e	Ljava/lang/String;
    //   93: aload_0
    //   94: aload_0
    //   95: aload_2
    //   96: getfield 342	android/content/pm/PackageInfo:versionCode	I
    //   99: invokestatic 346	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   102: invokespecial 317	com/alimama/mobile/csdk/umupdate/a/c:g	(Ljava/lang/String;)Ljava/lang/String;
    //   105: putfield 348	com/alimama/mobile/csdk/umupdate/a/c:f	Ljava/lang/String;
    //   108: aload_0
    //   109: ldc_w 350
    //   112: invokevirtual 195	com/alimama/mobile/csdk/umupdate/a/c:e	(Ljava/lang/String;)Z
    //   115: ifeq +58 -> 173
    //   118: aload_1
    //   119: ldc_w 352
    //   122: invokevirtual 201	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   125: checkcast 354	android/telephony/TelephonyManager
    //   128: astore_2
    //   129: aload_0
    //   130: aload_2
    //   131: invokevirtual 357	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   134: putfield 188	com/alimama/mobile/csdk/umupdate/a/c:h	Ljava/lang/String;
    //   137: aload_0
    //   138: aload_0
    //   139: aload_2
    //   140: invokevirtual 360	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   143: invokespecial 317	com/alimama/mobile/csdk/umupdate/a/c:g	(Ljava/lang/String;)Ljava/lang/String;
    //   146: putfield 362	com/alimama/mobile/csdk/umupdate/a/c:k	Ljava/lang/String;
    //   149: aload_0
    //   150: aload_0
    //   151: aload_2
    //   152: invokevirtual 365	android/telephony/TelephonyManager:getSubscriberId	()Ljava/lang/String;
    //   155: invokespecial 317	com/alimama/mobile/csdk/umupdate/a/c:g	(Ljava/lang/String;)Ljava/lang/String;
    //   158: putfield 367	com/alimama/mobile/csdk/umupdate/a/c:l	Ljava/lang/String;
    //   161: aload_0
    //   162: aload_0
    //   163: aload_2
    //   164: invokevirtual 370	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   167: invokespecial 317	com/alimama/mobile/csdk/umupdate/a/c:g	(Ljava/lang/String;)Ljava/lang/String;
    //   170: putfield 289	com/alimama/mobile/csdk/umupdate/a/c:r	Ljava/lang/String;
    //   173: aload_0
    //   174: getfield 188	com/alimama/mobile/csdk/umupdate/a/c:h	Ljava/lang/String;
    //   177: invokestatic 147	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   180: ifeq +17 -> 197
    //   183: aload_0
    //   184: aload_0
    //   185: invokestatic 376	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   188: invokevirtual 379	java/util/Locale:getCountry	()Ljava/lang/String;
    //   191: invokespecial 317	com/alimama/mobile/csdk/umupdate/a/c:g	(Ljava/lang/String;)Ljava/lang/String;
    //   194: putfield 188	com/alimama/mobile/csdk/umupdate/a/c:h	Ljava/lang/String;
    //   197: aload_0
    //   198: ldc_w 381
    //   201: invokevirtual 195	com/alimama/mobile/csdk/umupdate/a/c:e	(Ljava/lang/String;)Z
    //   204: ifeq +27 -> 231
    //   207: aload_0
    //   208: aload_0
    //   209: aload_1
    //   210: ldc_w 383
    //   213: invokevirtual 201	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   216: checkcast 385	android/net/wifi/WifiManager
    //   219: invokevirtual 389	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   222: invokevirtual 394	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   225: invokespecial 317	com/alimama/mobile/csdk/umupdate/a/c:g	(Ljava/lang/String;)Ljava/lang/String;
    //   228: putfield 396	com/alimama/mobile/csdk/umupdate/a/c:m	Ljava/lang/String;
    //   231: new 398	android/util/DisplayMetrics
    //   234: dup
    //   235: invokespecial 399	android/util/DisplayMetrics:<init>	()V
    //   238: astore_2
    //   239: aload_1
    //   240: ldc_w 401
    //   243: invokevirtual 201	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   246: checkcast 403	android/view/WindowManager
    //   249: checkcast 403	android/view/WindowManager
    //   252: invokeinterface 407 1 0
    //   257: aload_2
    //   258: invokevirtual 413	android/view/Display:getMetrics	(Landroid/util/DisplayMetrics;)V
    //   261: aload_0
    //   262: aload_2
    //   263: getfield 416	android/util/DisplayMetrics:widthPixels	I
    //   266: putfield 418	com/alimama/mobile/csdk/umupdate/a/c:p	I
    //   269: aload_0
    //   270: aload_2
    //   271: getfield 421	android/util/DisplayMetrics:heightPixels	I
    //   274: putfield 423	com/alimama/mobile/csdk/umupdate/a/c:q	I
    //   277: aload_0
    //   278: new 244	java/lang/StringBuilder
    //   281: dup
    //   282: invokespecial 245	java/lang/StringBuilder:<init>	()V
    //   285: aload_0
    //   286: getfield 423	com/alimama/mobile/csdk/umupdate/a/c:q	I
    //   289: invokestatic 346	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   292: invokevirtual 251	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   295: aload_0
    //   296: getfield 149	com/alimama/mobile/csdk/umupdate/a/c:a	Lcom/alimama/mobile/csdk/umupdate/a/c$a;
    //   299: getfield 425	com/alimama/mobile/csdk/umupdate/a/c$a:a	Ljava/lang/String;
    //   302: invokevirtual 251	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   305: aload_0
    //   306: getfield 418	com/alimama/mobile/csdk/umupdate/a/c:p	I
    //   309: invokestatic 346	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   312: invokevirtual 251	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   315: invokevirtual 268	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   318: putfield 427	com/alimama/mobile/csdk/umupdate/a/c:n	Ljava/lang/String;
    //   321: aload_0
    //   322: aload_2
    //   323: getfield 430	android/util/DisplayMetrics:density	F
    //   326: putfield 432	com/alimama/mobile/csdk/umupdate/a/c:o	F
    //   329: aload_1
    //   330: invokevirtual 296	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   333: invokevirtual 436	android/content/res/Resources:getConfiguration	()Landroid/content/res/Configuration;
    //   336: astore_1
    //   337: aload_1
    //   338: ifnull +225 -> 563
    //   341: aload_1
    //   342: getfield 442	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   345: ifnull +218 -> 563
    //   348: aload_0
    //   349: aload_0
    //   350: aload_1
    //   351: getfield 442	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   354: invokevirtual 445	java/util/Locale:getDisplayName	()Ljava/lang/String;
    //   357: invokespecial 317	com/alimama/mobile/csdk/umupdate/a/c:g	(Ljava/lang/String;)Ljava/lang/String;
    //   360: putfield 447	com/alimama/mobile/csdk/umupdate/a/c:i	Ljava/lang/String;
    //   363: aload_0
    //   364: aload_0
    //   365: aload_1
    //   366: getfield 442	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   369: invokevirtual 448	java/util/Locale:toString	()Ljava/lang/String;
    //   372: invokespecial 317	com/alimama/mobile/csdk/umupdate/a/c:g	(Ljava/lang/String;)Ljava/lang/String;
    //   375: putfield 450	com/alimama/mobile/csdk/umupdate/a/c:g	Ljava/lang/String;
    //   378: aload_0
    //   379: aload_0
    //   380: aload_1
    //   381: getfield 442	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   384: invokestatic 456	java/util/Calendar:getInstance	(Ljava/util/Locale;)Ljava/util/Calendar;
    //   387: invokevirtual 460	java/util/Calendar:getTimeZone	()Ljava/util/TimeZone;
    //   390: invokevirtual 465	java/util/TimeZone:getRawOffset	()I
    //   393: ldc_w 466
    //   396: idiv
    //   397: invokestatic 346	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   400: invokespecial 317	com/alimama/mobile/csdk/umupdate/a/c:g	(Ljava/lang/String;)Ljava/lang/String;
    //   403: putfield 468	com/alimama/mobile/csdk/umupdate/a/c:j	Ljava/lang/String;
    //   406: new 470	java/io/FileReader
    //   409: dup
    //   410: ldc_w 472
    //   413: invokespecial 473	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   416: astore 4
    //   418: aload 4
    //   420: ifnull +67 -> 487
    //   423: new 475	java/io/BufferedReader
    //   426: dup
    //   427: aload 4
    //   429: sipush 1024
    //   432: invokespecial 478	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   435: astore_2
    //   436: aload_2
    //   437: astore_1
    //   438: aload_2
    //   439: invokevirtual 481	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   442: astore_3
    //   443: aload_3
    //   444: ifnull +25 -> 469
    //   447: aload_2
    //   448: astore_1
    //   449: aload_0
    //   450: aload_0
    //   451: aload_3
    //   452: aload_3
    //   453: bipush 58
    //   455: invokevirtual 485	java/lang/String:indexOf	(I)I
    //   458: iconst_1
    //   459: iadd
    //   460: invokevirtual 488	java/lang/String:substring	(I)Ljava/lang/String;
    //   463: invokespecial 317	com/alimama/mobile/csdk/umupdate/a/c:g	(Ljava/lang/String;)Ljava/lang/String;
    //   466: putfield 291	com/alimama/mobile/csdk/umupdate/a/c:s	Ljava/lang/String;
    //   469: aload_2
    //   470: ifnull +7 -> 477
    //   473: aload_2
    //   474: invokevirtual 491	java/io/BufferedReader:close	()V
    //   477: aload 4
    //   479: ifnull +8 -> 487
    //   482: aload 4
    //   484: invokevirtual 492	java/io/FileReader:close	()V
    //   487: return
    //   488: astore_2
    //   489: aload_2
    //   490: ldc_w 494
    //   493: iconst_0
    //   494: anewarray 4	java/lang/Object
    //   497: invokestatic 282	com/alimama/mobile/csdk/umupdate/a/g:a	(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
    //   500: goto -450 -> 50
    //   503: astore_2
    //   504: aload_2
    //   505: ldc_w 496
    //   508: iconst_0
    //   509: anewarray 4	java/lang/Object
    //   512: invokestatic 282	com/alimama/mobile/csdk/umupdate/a/g:a	(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
    //   515: goto -407 -> 108
    //   518: astore_2
    //   519: aload_2
    //   520: ldc_w 498
    //   523: iconst_0
    //   524: anewarray 4	java/lang/Object
    //   527: invokestatic 282	com/alimama/mobile/csdk/umupdate/a/g:a	(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
    //   530: goto -357 -> 173
    //   533: astore_2
    //   534: aload_2
    //   535: ldc_w 500
    //   538: iconst_0
    //   539: anewarray 4	java/lang/Object
    //   542: invokestatic 282	com/alimama/mobile/csdk/umupdate/a/g:a	(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
    //   545: goto -314 -> 231
    //   548: astore_2
    //   549: aload_2
    //   550: ldc_w 502
    //   553: iconst_0
    //   554: anewarray 4	java/lang/Object
    //   557: invokestatic 282	com/alimama/mobile/csdk/umupdate/a/g:a	(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
    //   560: goto -231 -> 329
    //   563: aload_0
    //   564: getfield 149	com/alimama/mobile/csdk/umupdate/a/c:a	Lcom/alimama/mobile/csdk/umupdate/a/c$a;
    //   567: getfield 151	com/alimama/mobile/csdk/umupdate/a/c$a:b	Ljava/lang/String;
    //   570: astore_1
    //   571: aload_0
    //   572: aload_1
    //   573: putfield 450	com/alimama/mobile/csdk/umupdate/a/c:g	Ljava/lang/String;
    //   576: aload_0
    //   577: aload_1
    //   578: putfield 447	com/alimama/mobile/csdk/umupdate/a/c:i	Ljava/lang/String;
    //   581: goto -175 -> 406
    //   584: astore_1
    //   585: aload_1
    //   586: ldc_w 504
    //   589: iconst_0
    //   590: anewarray 4	java/lang/Object
    //   593: invokestatic 282	com/alimama/mobile/csdk/umupdate/a/g:a	(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
    //   596: goto -190 -> 406
    //   599: astore_3
    //   600: aconst_null
    //   601: astore_2
    //   602: aload_2
    //   603: astore_1
    //   604: ldc_w 506
    //   607: iconst_1
    //   608: anewarray 4	java/lang/Object
    //   611: dup
    //   612: iconst_0
    //   613: aload_3
    //   614: aastore
    //   615: invokestatic 508	com/alimama/mobile/csdk/umupdate/a/g:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   618: aload_2
    //   619: ifnull +7 -> 626
    //   622: aload_2
    //   623: invokevirtual 491	java/io/BufferedReader:close	()V
    //   626: aload 4
    //   628: ifnull -141 -> 487
    //   631: aload 4
    //   633: invokevirtual 492	java/io/FileReader:close	()V
    //   636: return
    //   637: astore_1
    //   638: ldc_w 510
    //   641: iconst_1
    //   642: anewarray 4	java/lang/Object
    //   645: dup
    //   646: iconst_0
    //   647: aload_1
    //   648: aastore
    //   649: invokestatic 508	com/alimama/mobile/csdk/umupdate/a/g:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   652: return
    //   653: astore_2
    //   654: aconst_null
    //   655: astore_1
    //   656: aload_1
    //   657: ifnull +7 -> 664
    //   660: aload_1
    //   661: invokevirtual 491	java/io/BufferedReader:close	()V
    //   664: aload 4
    //   666: ifnull +8 -> 674
    //   669: aload 4
    //   671: invokevirtual 492	java/io/FileReader:close	()V
    //   674: aload_2
    //   675: athrow
    //   676: astore_2
    //   677: goto -21 -> 656
    //   680: astore_3
    //   681: goto -79 -> 602
    //
    // Exception table:
    //   from	to	target	type
    //   25	50	488	java/lang/Exception
    //   68	108	503	java/lang/Exception
    //   108	173	518	java/lang/Exception
    //   197	231	533	java/lang/Exception
    //   231	329	548	java/lang/Exception
    //   329	337	584	java/lang/Exception
    //   341	406	584	java/lang/Exception
    //   563	581	584	java/lang/Exception
    //   423	436	599	java/io/IOException
    //   406	418	637	java/lang/Exception
    //   473	477	637	java/lang/Exception
    //   482	487	637	java/lang/Exception
    //   622	626	637	java/lang/Exception
    //   631	636	637	java/lang/Exception
    //   660	664	637	java/lang/Exception
    //   669	674	637	java/lang/Exception
    //   674	676	637	java/lang/Exception
    //   423	436	653	finally
    //   438	443	676	finally
    //   449	469	676	finally
    //   604	618	676	finally
    //   438	443	680	java/io/IOException
    //   449	469	680	java/io/IOException
  }

  protected void a(Context paramContext, Class<?>[] paramArrayOfClass)
  {
    int i2 = paramArrayOfClass.length;
    int i1 = 0;
    while (i1 < i2)
    {
      paramContext = paramArrayOfClass[i1];
      if (c(paramContext) == null)
        g.e("No activity element declared for [" + paramContext.getName() + "].  Please ensure you have included this in your AndroidManifest.xml", new Object[0]);
      i1 += 1;
    }
  }

  void a(boolean paramBoolean)
  {
    this.x = paramBoolean;
  }

  public boolean a()
  {
    return false;
  }

  public boolean a(Intent paramIntent)
  {
    return this.t.getPackageManager().queryIntentActivities(paramIntent, 65536).size() > 0;
  }

  public boolean a(Class<?> paramClass)
  {
    return a(new Intent(this.t, paramClass));
  }

  public boolean a(String paramString)
  {
    try
    {
      boolean bool = a(Class.forName(paramString));
      return bool;
    }
    catch (ClassNotFoundException paramString)
    {
    }
    return false;
  }

  public boolean b()
  {
    return false;
  }

  public boolean b(Class<?> paramClass)
  {
    return this.t.getPackageManager().queryIntentServices(new Intent(this.t, paramClass), 65536).size() > 0;
  }

  public boolean b(String paramString)
  {
    return a(new Intent(paramString));
  }

  public int c(Context paramContext)
  {
    return paramContext.getApplicationInfo().icon;
  }

  public ActivityInfo c(Class<?> paramClass)
  {
    try
    {
      ActivityInfo localActivityInfo = this.t.getPackageManager().getActivityInfo(new ComponentName(this.t, paramClass), 0);
      return localActivityInfo;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      g.a(localNameNotFoundException, "Failed to locate info for activity [" + paramClass.getName() + "]", new Object[0]);
    }
    return null;
  }

  public boolean c()
  {
    try
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)this.t.getSystemService("connectivity")).getActiveNetworkInfo();
      if (localNetworkInfo != null)
      {
        boolean bool = localNetworkInfo.isConnectedOrConnecting();
        return bool;
      }
    }
    catch (Exception localException)
    {
      return false;
    }
    return false;
  }

  public boolean c(String paramString)
  {
    if (this.t != null)
    {
      PackageManager localPackageManager = this.t.getPackageManager();
      try
      {
        localPackageManager.getPackageInfo(paramString, 1);
        return true;
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        return false;
      }
    }
    return false;
  }

  public String d(String paramString)
  {
    if ((paramString != null) && (paramString.equalsIgnoreCase("amazon")))
      return "amz";
    return null;
  }

  public boolean d()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }

  public boolean d(Class<?> paramClass)
  {
    return this.t.getPackageManager().queryBroadcastReceivers(new Intent(this.t, paramClass), 65536).size() > 0;
  }

  public boolean e()
  {
    boolean bool = false;
    if (this.t != null)
      bool = this.t.getResources().getConfiguration().locale.toString().equals(Locale.CHINA.toString());
    return bool;
  }

  public boolean e(String paramString)
  {
    return this.t.getPackageManager().checkPermission(paramString, this.t.getPackageName()) == 0;
  }

  public String f()
  {
    return this.c;
  }

  public String f(String paramString)
  {
    if (this.t != null)
      try
      {
        ApplicationInfo localApplicationInfo = this.t.getPackageManager().getApplicationInfo(this.t.getPackageName(), 128);
        if ((localApplicationInfo != null) && (localApplicationInfo.metaData != null))
        {
          paramString = localApplicationInfo.metaData.get(paramString);
          if (paramString != null)
          {
            paramString = paramString.toString();
            return paramString;
          }
        }
      }
      catch (Exception paramString)
      {
        g.d("Could not read meta-data from AndroidManifest.xml.", new Object[0]);
      }
    return null;
  }

  public String g()
  {
    return this.b;
  }

  public String h()
  {
    return this.e;
  }

  public String i()
  {
    return this.f;
  }

  public String j()
  {
    return this.d;
  }

  public void k()
  {
  }

  public boolean l()
  {
    return (this.t != null) && (this.t.getResources().getConfiguration().orientation == 1);
  }

  public String m()
  {
    return this.g;
  }

  public String n()
  {
    return this.i;
  }

  public String o()
  {
    return this.j;
  }

  public String p()
  {
    return this.m;
  }

  public String q()
  {
    return this.k;
  }

  public String r()
  {
    if ((!TextUtils.isEmpty(this.k)) && (!this.a.b.equals(this.k)))
      return this.k;
    if ((!TextUtils.isEmpty(this.m)) && (!this.a.b.equals(this.m)))
      return this.m;
    return Settings.Secure.getString(this.t.getContentResolver(), "android_id");
  }

  public String s()
  {
    return this.l;
  }

  public String t()
  {
    return this.n;
  }

  public float u()
  {
    return this.o;
  }

  public int v()
  {
    int i1 = 0;
    if (this.t != null);
    try
    {
      i1 = Settings.System.getInt(this.t.getContentResolver(), "screen_brightness");
      return i1;
    }
    catch (Settings.SettingNotFoundException localSettingNotFoundException)
    {
      g.e("Get screen bright exception,info:" + localSettingNotFoundException.toString(), new Object[0]);
    }
    return 0;
  }

  // ERROR //
  public int w()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: iconst_m1
    //   4: istore_2
    //   5: new 470	java/io/FileReader
    //   8: dup
    //   9: ldc_w 645
    //   12: invokespecial 473	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   15: astore_3
    //   16: new 475	java/io/BufferedReader
    //   19: dup
    //   20: aload_3
    //   21: sipush 1024
    //   24: invokespecial 478	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   27: astore 4
    //   29: aload 4
    //   31: astore 5
    //   33: aload_3
    //   34: astore 6
    //   36: aload 4
    //   38: invokevirtual 481	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   41: astore 7
    //   43: iload_2
    //   44: istore_1
    //   45: aload 7
    //   47: ifnull +51 -> 98
    //   50: iload_2
    //   51: istore_1
    //   52: aload 4
    //   54: astore 5
    //   56: aload_3
    //   57: astore 6
    //   59: aload 7
    //   61: invokevirtual 648	java/lang/String:trim	()Ljava/lang/String;
    //   64: invokevirtual 651	java/lang/String:length	()I
    //   67: ifle +31 -> 98
    //   70: aload 4
    //   72: astore 5
    //   74: aload_3
    //   75: astore 6
    //   77: aload 7
    //   79: ldc_w 653
    //   82: invokevirtual 657	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   85: iconst_1
    //   86: aaload
    //   87: invokestatic 662	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   90: invokevirtual 665	java/lang/Integer:intValue	()I
    //   93: sipush 1024
    //   96: idiv
    //   97: istore_1
    //   98: aload 4
    //   100: ifnull +8 -> 108
    //   103: aload 4
    //   105: invokevirtual 491	java/io/BufferedReader:close	()V
    //   108: iload_1
    //   109: istore_2
    //   110: aload_3
    //   111: ifnull +9 -> 120
    //   114: aload_3
    //   115: invokevirtual 492	java/io/FileReader:close	()V
    //   118: iload_1
    //   119: istore_2
    //   120: iload_2
    //   121: ireturn
    //   122: astore 4
    //   124: new 244	java/lang/StringBuilder
    //   127: dup
    //   128: invokespecial 245	java/lang/StringBuilder:<init>	()V
    //   131: ldc_w 667
    //   134: invokevirtual 251	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   137: aload 4
    //   139: invokevirtual 668	java/io/IOException:toString	()Ljava/lang/String;
    //   142: invokevirtual 251	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: invokevirtual 268	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   148: iconst_0
    //   149: anewarray 4	java/lang/Object
    //   152: invokestatic 525	com/alimama/mobile/csdk/umupdate/a/g:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   155: goto -47 -> 108
    //   158: astore_3
    //   159: new 244	java/lang/StringBuilder
    //   162: dup
    //   163: invokespecial 245	java/lang/StringBuilder:<init>	()V
    //   166: ldc_w 670
    //   169: invokevirtual 251	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   172: aload_3
    //   173: invokevirtual 668	java/io/IOException:toString	()Ljava/lang/String;
    //   176: invokevirtual 251	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   179: invokevirtual 268	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   182: iconst_0
    //   183: anewarray 4	java/lang/Object
    //   186: invokestatic 525	com/alimama/mobile/csdk/umupdate/a/g:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   189: iload_1
    //   190: ireturn
    //   191: astore 5
    //   193: aconst_null
    //   194: astore_3
    //   195: new 244	java/lang/StringBuilder
    //   198: dup
    //   199: invokespecial 245	java/lang/StringBuilder:<init>	()V
    //   202: ldc_w 672
    //   205: invokevirtual 251	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   208: aload 5
    //   210: invokevirtual 668	java/io/IOException:toString	()Ljava/lang/String;
    //   213: invokevirtual 251	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   216: invokevirtual 268	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   219: iconst_0
    //   220: anewarray 4	java/lang/Object
    //   223: invokestatic 525	com/alimama/mobile/csdk/umupdate/a/g:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   226: aload_3
    //   227: ifnull +7 -> 234
    //   230: aload_3
    //   231: invokevirtual 491	java/io/BufferedReader:close	()V
    //   234: aload 4
    //   236: ifnull -116 -> 120
    //   239: aload 4
    //   241: invokevirtual 492	java/io/FileReader:close	()V
    //   244: iconst_m1
    //   245: ireturn
    //   246: astore_3
    //   247: new 244	java/lang/StringBuilder
    //   250: dup
    //   251: invokespecial 245	java/lang/StringBuilder:<init>	()V
    //   254: ldc_w 670
    //   257: invokevirtual 251	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   260: aload_3
    //   261: invokevirtual 668	java/io/IOException:toString	()Ljava/lang/String;
    //   264: invokevirtual 251	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   267: invokevirtual 268	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   270: iconst_0
    //   271: anewarray 4	java/lang/Object
    //   274: invokestatic 525	com/alimama/mobile/csdk/umupdate/a/g:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   277: iconst_m1
    //   278: ireturn
    //   279: astore_3
    //   280: new 244	java/lang/StringBuilder
    //   283: dup
    //   284: invokespecial 245	java/lang/StringBuilder:<init>	()V
    //   287: ldc_w 667
    //   290: invokevirtual 251	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   293: aload_3
    //   294: invokevirtual 668	java/io/IOException:toString	()Ljava/lang/String;
    //   297: invokevirtual 251	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   300: invokevirtual 268	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   303: iconst_0
    //   304: anewarray 4	java/lang/Object
    //   307: invokestatic 525	com/alimama/mobile/csdk/umupdate/a/g:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   310: goto -76 -> 234
    //   313: astore 7
    //   315: aconst_null
    //   316: astore 4
    //   318: aconst_null
    //   319: astore_3
    //   320: aload 4
    //   322: astore 5
    //   324: aload_3
    //   325: astore 6
    //   327: new 244	java/lang/StringBuilder
    //   330: dup
    //   331: invokespecial 245	java/lang/StringBuilder:<init>	()V
    //   334: ldc_w 672
    //   337: invokevirtual 251	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   340: aload 7
    //   342: invokevirtual 673	java/lang/Exception:toString	()Ljava/lang/String;
    //   345: invokevirtual 251	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   348: invokevirtual 268	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   351: iconst_0
    //   352: anewarray 4	java/lang/Object
    //   355: invokestatic 525	com/alimama/mobile/csdk/umupdate/a/g:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   358: aload 4
    //   360: ifnull +8 -> 368
    //   363: aload 4
    //   365: invokevirtual 491	java/io/BufferedReader:close	()V
    //   368: aload_3
    //   369: ifnull -249 -> 120
    //   372: aload_3
    //   373: invokevirtual 492	java/io/FileReader:close	()V
    //   376: iconst_m1
    //   377: ireturn
    //   378: astore_3
    //   379: new 244	java/lang/StringBuilder
    //   382: dup
    //   383: invokespecial 245	java/lang/StringBuilder:<init>	()V
    //   386: ldc_w 670
    //   389: invokevirtual 251	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   392: aload_3
    //   393: invokevirtual 668	java/io/IOException:toString	()Ljava/lang/String;
    //   396: invokevirtual 251	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   399: invokevirtual 268	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   402: iconst_0
    //   403: anewarray 4	java/lang/Object
    //   406: invokestatic 525	com/alimama/mobile/csdk/umupdate/a/g:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   409: iconst_m1
    //   410: ireturn
    //   411: astore 4
    //   413: new 244	java/lang/StringBuilder
    //   416: dup
    //   417: invokespecial 245	java/lang/StringBuilder:<init>	()V
    //   420: ldc_w 667
    //   423: invokevirtual 251	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   426: aload 4
    //   428: invokevirtual 668	java/io/IOException:toString	()Ljava/lang/String;
    //   431: invokevirtual 251	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   434: invokevirtual 268	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   437: iconst_0
    //   438: anewarray 4	java/lang/Object
    //   441: invokestatic 525	com/alimama/mobile/csdk/umupdate/a/g:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   444: goto -76 -> 368
    //   447: astore 4
    //   449: aconst_null
    //   450: astore 5
    //   452: aconst_null
    //   453: astore_3
    //   454: aload 5
    //   456: ifnull +8 -> 464
    //   459: aload 5
    //   461: invokevirtual 491	java/io/BufferedReader:close	()V
    //   464: aload_3
    //   465: ifnull +7 -> 472
    //   468: aload_3
    //   469: invokevirtual 492	java/io/FileReader:close	()V
    //   472: aload 4
    //   474: athrow
    //   475: astore 5
    //   477: new 244	java/lang/StringBuilder
    //   480: dup
    //   481: invokespecial 245	java/lang/StringBuilder:<init>	()V
    //   484: ldc_w 667
    //   487: invokevirtual 251	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   490: aload 5
    //   492: invokevirtual 668	java/io/IOException:toString	()Ljava/lang/String;
    //   495: invokevirtual 251	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   498: invokevirtual 268	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   501: iconst_0
    //   502: anewarray 4	java/lang/Object
    //   505: invokestatic 525	com/alimama/mobile/csdk/umupdate/a/g:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   508: goto -44 -> 464
    //   511: astore_3
    //   512: new 244	java/lang/StringBuilder
    //   515: dup
    //   516: invokespecial 245	java/lang/StringBuilder:<init>	()V
    //   519: ldc_w 670
    //   522: invokevirtual 251	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   525: aload_3
    //   526: invokevirtual 668	java/io/IOException:toString	()Ljava/lang/String;
    //   529: invokevirtual 251	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   532: invokevirtual 268	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   535: iconst_0
    //   536: anewarray 4	java/lang/Object
    //   539: invokestatic 525	com/alimama/mobile/csdk/umupdate/a/g:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   542: goto -70 -> 472
    //   545: astore 4
    //   547: aconst_null
    //   548: astore 5
    //   550: goto -96 -> 454
    //   553: astore 4
    //   555: aload 6
    //   557: astore_3
    //   558: goto -104 -> 454
    //   561: astore 5
    //   563: aload 4
    //   565: astore 6
    //   567: aload 5
    //   569: astore 4
    //   571: aload_3
    //   572: astore 5
    //   574: aload 6
    //   576: astore_3
    //   577: goto -123 -> 454
    //   580: astore 7
    //   582: aconst_null
    //   583: astore 4
    //   585: goto -265 -> 320
    //   588: astore 7
    //   590: goto -270 -> 320
    //   593: astore 5
    //   595: aconst_null
    //   596: astore 6
    //   598: aload_3
    //   599: astore 4
    //   601: aload 6
    //   603: astore_3
    //   604: goto -409 -> 195
    //   607: astore 5
    //   609: aload_3
    //   610: astore 6
    //   612: aload 4
    //   614: astore_3
    //   615: aload 6
    //   617: astore 4
    //   619: goto -424 -> 195
    //
    // Exception table:
    //   from	to	target	type
    //   103	108	122	java/io/IOException
    //   114	118	158	java/io/IOException
    //   5	16	191	java/io/IOException
    //   239	244	246	java/io/IOException
    //   230	234	279	java/io/IOException
    //   5	16	313	java/lang/Exception
    //   372	376	378	java/io/IOException
    //   363	368	411	java/io/IOException
    //   5	16	447	finally
    //   459	464	475	java/io/IOException
    //   468	472	511	java/io/IOException
    //   16	29	545	finally
    //   36	43	553	finally
    //   59	70	553	finally
    //   77	98	553	finally
    //   327	358	553	finally
    //   195	226	561	finally
    //   16	29	580	java/lang/Exception
    //   36	43	588	java/lang/Exception
    //   59	70	588	java/lang/Exception
    //   77	98	588	java/lang/Exception
    //   16	29	593	java/io/IOException
    //   36	43	607	java/io/IOException
    //   59	70	607	java/io/IOException
    //   77	98	607	java/io/IOException
  }

  public int x()
  {
    int i1 = -1;
    if (this.t != null)
    {
      ActivityManager localActivityManager = (ActivityManager)this.t.getSystemService("activity");
      ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
      localActivityManager.getMemoryInfo(localMemoryInfo);
      i1 = new Long(localMemoryInfo.availMem / 1048576L).intValue();
    }
    return i1;
  }

  public int y()
  {
    return 0;
  }

  public int z()
  {
    return 0;
  }

  public static class a
  {
    public String a = "x";
    public String b = "unknown";
    public String c = "cell";
    public String d = "wifi";
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alimama.mobile.csdk.umupdate.a.c
 * JD-Core Version:    0.6.2
 */