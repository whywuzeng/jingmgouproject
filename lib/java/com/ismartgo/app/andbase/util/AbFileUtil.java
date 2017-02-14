package com.ismartgo.app.andbase.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.StatFs;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.Header;
import org.apache.http.HttpResponse;

public class AbFileUtil
{
  private static String cacheDownloadDir = null;
  private static String dbDownloadDir = null;
  private static String downloadRootDir = null;
  private static String fileDownloadDir;
  private static int freeSdSpaceNeededToCache = 209715200;
  private static String imageDownloadDir = null;

  static
  {
    fileDownloadDir = null;
  }

  public static boolean clearDownloadFile()
  {
    while (true)
    {
      try
      {
        if (!isCanUseSD())
          return false;
        File[] arrayOfFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + downloadRootDir).listFiles();
        if (arrayOfFile == null)
        {
          return true;
          if (i >= arrayOfFile.length)
            return true;
          arrayOfFile[i].delete();
          i += 1;
          continue;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return false;
      }
      int i = 0;
    }
  }

  // ERROR //
  public static String downloadFile(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 15
    //   3: aconst_null
    //   4: astore 13
    //   6: aconst_null
    //   7: astore 14
    //   9: aconst_null
    //   10: astore 11
    //   12: aconst_null
    //   13: astore 8
    //   15: aconst_null
    //   16: astore 9
    //   18: aconst_null
    //   19: astore 10
    //   21: aconst_null
    //   22: astore 16
    //   24: aconst_null
    //   25: astore_1
    //   26: aconst_null
    //   27: astore 12
    //   29: aload_1
    //   30: astore 4
    //   32: aload 8
    //   34: astore 5
    //   36: aload 15
    //   38: astore 6
    //   40: aload 16
    //   42: astore 7
    //   44: invokestatic 41	com/ismartgo/app/andbase/util/AbFileUtil:isCanUseSD	()Z
    //   47: istore_3
    //   48: iload_3
    //   49: ifne +67 -> 116
    //   52: iconst_0
    //   53: ifeq +11 -> 64
    //   56: new 86	java/lang/NullPointerException
    //   59: dup
    //   60: invokespecial 87	java/lang/NullPointerException:<init>	()V
    //   63: athrow
    //   64: iconst_0
    //   65: ifeq +11 -> 76
    //   68: new 86	java/lang/NullPointerException
    //   71: dup
    //   72: invokespecial 87	java/lang/NullPointerException:<init>	()V
    //   75: athrow
    //   76: iconst_0
    //   77: ifeq +11 -> 88
    //   80: new 86	java/lang/NullPointerException
    //   83: dup
    //   84: invokespecial 87	java/lang/NullPointerException:<init>	()V
    //   87: athrow
    //   88: aconst_null
    //   89: astore_1
    //   90: aload_1
    //   91: areturn
    //   92: astore_0
    //   93: aload_0
    //   94: invokevirtual 82	java/lang/Exception:printStackTrace	()V
    //   97: goto -33 -> 64
    //   100: astore_0
    //   101: aload_0
    //   102: invokevirtual 82	java/lang/Exception:printStackTrace	()V
    //   105: goto -29 -> 76
    //   108: astore_0
    //   109: aload_0
    //   110: invokevirtual 82	java/lang/Exception:printStackTrace	()V
    //   113: goto -25 -> 88
    //   116: aload_1
    //   117: astore 4
    //   119: aload 8
    //   121: astore 5
    //   123: aload 15
    //   125: astore 6
    //   127: aload 16
    //   129: astore 7
    //   131: aload_0
    //   132: invokestatic 91	com/ismartgo/app/andbase/util/AbFileUtil:getCacheFileNameFromUrl	(Ljava/lang/String;)Ljava/lang/String;
    //   135: astore 19
    //   137: aload_1
    //   138: astore 4
    //   140: aload 8
    //   142: astore 5
    //   144: aload 15
    //   146: astore 6
    //   148: aload 16
    //   150: astore 7
    //   152: new 43	java/io/File
    //   155: dup
    //   156: getstatic 21	com/ismartgo/app/andbase/util/AbFileUtil:imageDownloadDir	Ljava/lang/String;
    //   159: invokespecial 72	java/io/File:<init>	(Ljava/lang/String;)V
    //   162: invokevirtual 76	java/io/File:listFiles	()[Ljava/io/File;
    //   165: astore 17
    //   167: iconst_0
    //   168: istore_2
    //   169: aload_1
    //   170: astore 4
    //   172: aload 8
    //   174: astore 5
    //   176: aload 15
    //   178: astore 6
    //   180: aload 16
    //   182: astore 7
    //   184: iload_2
    //   185: aload 17
    //   187: arraylength
    //   188: if_icmplt +223 -> 411
    //   191: aload_1
    //   192: astore 4
    //   194: aload 8
    //   196: astore 5
    //   198: aload 15
    //   200: astore 6
    //   202: aload 16
    //   204: astore 7
    //   206: new 93	java/net/URL
    //   209: dup
    //   210: aload_0
    //   211: invokespecial 94	java/net/URL:<init>	(Ljava/lang/String;)V
    //   214: invokevirtual 98	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   217: checkcast 100	java/net/HttpURLConnection
    //   220: astore_1
    //   221: aload_1
    //   222: astore 4
    //   224: aload 8
    //   226: astore 5
    //   228: aload 15
    //   230: astore 6
    //   232: aload_1
    //   233: astore 7
    //   235: aload_1
    //   236: invokevirtual 103	java/net/HttpURLConnection:connect	()V
    //   239: aload_1
    //   240: astore 4
    //   242: aload 8
    //   244: astore 5
    //   246: aload 15
    //   248: astore 6
    //   250: aload_1
    //   251: astore 7
    //   253: aload_0
    //   254: aload_1
    //   255: invokestatic 106	com/ismartgo/app/andbase/util/AbFileUtil:getCacheFileNameFromUrl	(Ljava/lang/String;Ljava/net/HttpURLConnection;)Ljava/lang/String;
    //   258: astore_0
    //   259: aload_1
    //   260: astore 4
    //   262: aload 8
    //   264: astore 5
    //   266: aload 15
    //   268: astore 6
    //   270: aload_1
    //   271: astore 7
    //   273: new 43	java/io/File
    //   276: dup
    //   277: getstatic 21	com/ismartgo/app/andbase/util/AbFileUtil:imageDownloadDir	Ljava/lang/String;
    //   280: aload_0
    //   281: invokespecial 109	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   284: astore 8
    //   286: aload 13
    //   288: astore 5
    //   290: aload 14
    //   292: astore 4
    //   294: aload 8
    //   296: invokevirtual 112	java/io/File:getPath	()Ljava/lang/String;
    //   299: astore 7
    //   301: aload 13
    //   303: astore 5
    //   305: aload 14
    //   307: astore 4
    //   309: aload 8
    //   311: invokevirtual 115	java/io/File:exists	()Z
    //   314: ifne +248 -> 562
    //   317: aload 13
    //   319: astore 5
    //   321: aload 14
    //   323: astore 4
    //   325: aload 8
    //   327: invokevirtual 118	java/io/File:createNewFile	()Z
    //   330: pop
    //   331: aload 13
    //   333: astore 5
    //   335: aload 14
    //   337: astore 4
    //   339: aload_1
    //   340: invokevirtual 122	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   343: astore_0
    //   344: aload_0
    //   345: astore 5
    //   347: aload_0
    //   348: astore 4
    //   350: new 124	java/io/FileOutputStream
    //   353: dup
    //   354: aload 8
    //   356: invokespecial 127	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   359: astore 6
    //   361: sipush 1024
    //   364: newarray byte
    //   366: astore 4
    //   368: aload_0
    //   369: aload 4
    //   371: invokevirtual 133	java/io/InputStream:read	([B)I
    //   374: istore_2
    //   375: iload_2
    //   376: iconst_m1
    //   377: if_icmpne +261 -> 638
    //   380: aload_0
    //   381: ifnull +7 -> 388
    //   384: aload_0
    //   385: invokevirtual 136	java/io/InputStream:close	()V
    //   388: aload 6
    //   390: ifnull +8 -> 398
    //   393: aload 6
    //   395: invokevirtual 137	java/io/FileOutputStream:close	()V
    //   398: aload_1
    //   399: ifnull +464 -> 863
    //   402: aload_1
    //   403: invokevirtual 140	java/net/HttpURLConnection:disconnect	()V
    //   406: aload 7
    //   408: astore_0
    //   409: aload_0
    //   410: areturn
    //   411: aload_1
    //   412: astore 4
    //   414: aload 8
    //   416: astore 5
    //   418: aload 15
    //   420: astore 6
    //   422: aload 16
    //   424: astore 7
    //   426: aload 17
    //   428: iload_2
    //   429: aaload
    //   430: invokevirtual 143	java/io/File:getName	()Ljava/lang/String;
    //   433: astore 18
    //   435: aload_1
    //   436: astore 4
    //   438: aload 8
    //   440: astore 5
    //   442: aload 15
    //   444: astore 6
    //   446: aload 16
    //   448: astore 7
    //   450: aload 18
    //   452: iconst_0
    //   453: aload 18
    //   455: ldc 145
    //   457: invokevirtual 149	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   460: invokevirtual 153	java/lang/String:substring	(II)Ljava/lang/String;
    //   463: aload 19
    //   465: invokevirtual 157	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   468: ifeq +87 -> 555
    //   471: aload_1
    //   472: astore 4
    //   474: aload 8
    //   476: astore 5
    //   478: aload 15
    //   480: astore 6
    //   482: aload 16
    //   484: astore 7
    //   486: aload 17
    //   488: iload_2
    //   489: aaload
    //   490: invokevirtual 112	java/io/File:getPath	()Ljava/lang/String;
    //   493: astore_0
    //   494: iconst_0
    //   495: ifeq +11 -> 506
    //   498: new 86	java/lang/NullPointerException
    //   501: dup
    //   502: invokespecial 87	java/lang/NullPointerException:<init>	()V
    //   505: athrow
    //   506: iconst_0
    //   507: ifeq +11 -> 518
    //   510: new 86	java/lang/NullPointerException
    //   513: dup
    //   514: invokespecial 87	java/lang/NullPointerException:<init>	()V
    //   517: athrow
    //   518: aload_0
    //   519: astore_1
    //   520: iconst_0
    //   521: ifeq -431 -> 90
    //   524: new 86	java/lang/NullPointerException
    //   527: dup
    //   528: invokespecial 87	java/lang/NullPointerException:<init>	()V
    //   531: athrow
    //   532: astore_1
    //   533: aload_1
    //   534: invokevirtual 82	java/lang/Exception:printStackTrace	()V
    //   537: aload_0
    //   538: areturn
    //   539: astore_1
    //   540: aload_1
    //   541: invokevirtual 82	java/lang/Exception:printStackTrace	()V
    //   544: goto -38 -> 506
    //   547: astore_1
    //   548: aload_1
    //   549: invokevirtual 82	java/lang/Exception:printStackTrace	()V
    //   552: goto -34 -> 518
    //   555: iload_2
    //   556: iconst_1
    //   557: iadd
    //   558: istore_2
    //   559: goto -390 -> 169
    //   562: aload 13
    //   564: astore 5
    //   566: aload 14
    //   568: astore 4
    //   570: aload 8
    //   572: invokevirtual 112	java/io/File:getPath	()Ljava/lang/String;
    //   575: astore_0
    //   576: iconst_0
    //   577: ifeq +11 -> 588
    //   580: new 86	java/lang/NullPointerException
    //   583: dup
    //   584: invokespecial 87	java/lang/NullPointerException:<init>	()V
    //   587: athrow
    //   588: iconst_0
    //   589: ifeq +11 -> 600
    //   592: new 86	java/lang/NullPointerException
    //   595: dup
    //   596: invokespecial 87	java/lang/NullPointerException:<init>	()V
    //   599: athrow
    //   600: aload_1
    //   601: ifnull +7 -> 608
    //   604: aload_1
    //   605: invokevirtual 140	java/net/HttpURLConnection:disconnect	()V
    //   608: aload_0
    //   609: areturn
    //   610: astore 4
    //   612: aload 4
    //   614: invokevirtual 82	java/lang/Exception:printStackTrace	()V
    //   617: goto -29 -> 588
    //   620: astore 4
    //   622: aload 4
    //   624: invokevirtual 82	java/lang/Exception:printStackTrace	()V
    //   627: goto -27 -> 600
    //   630: astore_1
    //   631: aload_1
    //   632: invokevirtual 82	java/lang/Exception:printStackTrace	()V
    //   635: goto -27 -> 608
    //   638: aload 6
    //   640: aload 4
    //   642: iconst_0
    //   643: iload_2
    //   644: invokevirtual 161	java/io/FileOutputStream:write	([BII)V
    //   647: goto -279 -> 368
    //   650: astore 4
    //   652: aload 6
    //   654: astore 7
    //   656: aload 8
    //   658: astore 9
    //   660: aload 4
    //   662: astore 8
    //   664: aload_1
    //   665: astore 4
    //   667: aload 7
    //   669: astore 5
    //   671: aload_0
    //   672: astore 6
    //   674: aload 8
    //   676: invokevirtual 82	java/lang/Exception:printStackTrace	()V
    //   679: aload_1
    //   680: astore 4
    //   682: aload 7
    //   684: astore 5
    //   686: aload_0
    //   687: astore 6
    //   689: ldc 2
    //   691: ldc 163
    //   693: invokestatic 169	com/ismartgo/app/andbase/util/AbLogUtil:e	(Ljava/lang/Class;Ljava/lang/String;)V
    //   696: aload 9
    //   698: ifnull +19 -> 717
    //   701: aload_1
    //   702: astore 4
    //   704: aload 7
    //   706: astore 5
    //   708: aload_0
    //   709: astore 6
    //   711: aload 9
    //   713: invokevirtual 79	java/io/File:delete	()Z
    //   716: pop
    //   717: aconst_null
    //   718: astore 4
    //   720: aload_0
    //   721: ifnull +7 -> 728
    //   724: aload_0
    //   725: invokevirtual 136	java/io/InputStream:close	()V
    //   728: aload 7
    //   730: ifnull +8 -> 738
    //   733: aload 7
    //   735: invokevirtual 137	java/io/FileOutputStream:close	()V
    //   738: aload 4
    //   740: astore_0
    //   741: aload_1
    //   742: ifnull -333 -> 409
    //   745: aload_1
    //   746: invokevirtual 140	java/net/HttpURLConnection:disconnect	()V
    //   749: aload 4
    //   751: astore_0
    //   752: goto -343 -> 409
    //   755: astore_0
    //   756: aload_0
    //   757: invokevirtual 82	java/lang/Exception:printStackTrace	()V
    //   760: aload 4
    //   762: astore_0
    //   763: goto -354 -> 409
    //   766: astore_0
    //   767: aload_0
    //   768: invokevirtual 82	java/lang/Exception:printStackTrace	()V
    //   771: goto -43 -> 728
    //   774: astore_0
    //   775: aload_0
    //   776: invokevirtual 82	java/lang/Exception:printStackTrace	()V
    //   779: goto -41 -> 738
    //   782: astore_0
    //   783: aload 5
    //   785: astore_1
    //   786: aload 6
    //   788: ifnull +8 -> 796
    //   791: aload 6
    //   793: invokevirtual 136	java/io/InputStream:close	()V
    //   796: aload_1
    //   797: ifnull +7 -> 804
    //   800: aload_1
    //   801: invokevirtual 137	java/io/FileOutputStream:close	()V
    //   804: aload 4
    //   806: ifnull +8 -> 814
    //   809: aload 4
    //   811: invokevirtual 140	java/net/HttpURLConnection:disconnect	()V
    //   814: aload_0
    //   815: athrow
    //   816: astore 5
    //   818: aload 5
    //   820: invokevirtual 82	java/lang/Exception:printStackTrace	()V
    //   823: goto -27 -> 796
    //   826: astore_1
    //   827: aload_1
    //   828: invokevirtual 82	java/lang/Exception:printStackTrace	()V
    //   831: goto -27 -> 804
    //   834: astore_1
    //   835: aload_1
    //   836: invokevirtual 82	java/lang/Exception:printStackTrace	()V
    //   839: goto -25 -> 814
    //   842: astore_0
    //   843: aload_0
    //   844: invokevirtual 82	java/lang/Exception:printStackTrace	()V
    //   847: goto -459 -> 388
    //   850: astore_0
    //   851: aload_0
    //   852: invokevirtual 82	java/lang/Exception:printStackTrace	()V
    //   855: goto -457 -> 398
    //   858: astore_0
    //   859: aload_0
    //   860: invokevirtual 82	java/lang/Exception:printStackTrace	()V
    //   863: aload 7
    //   865: astore_0
    //   866: goto -457 -> 409
    //   869: astore_0
    //   870: aload_1
    //   871: astore 4
    //   873: aload 9
    //   875: astore_1
    //   876: aload 5
    //   878: astore 6
    //   880: goto -94 -> 786
    //   883: astore 5
    //   885: aload_1
    //   886: astore 4
    //   888: aload 6
    //   890: astore_1
    //   891: aload_0
    //   892: astore 6
    //   894: aload 5
    //   896: astore_0
    //   897: goto -111 -> 786
    //   900: astore 8
    //   902: aload 7
    //   904: astore_1
    //   905: aload 12
    //   907: astore 9
    //   909: aload 10
    //   911: astore 7
    //   913: aload 11
    //   915: astore_0
    //   916: goto -252 -> 664
    //   919: astore_0
    //   920: aload 8
    //   922: astore 9
    //   924: aload_0
    //   925: astore 8
    //   927: aload 10
    //   929: astore 7
    //   931: aload 4
    //   933: astore_0
    //   934: goto -270 -> 664
    //
    // Exception table:
    //   from	to	target	type
    //   56	64	92	java/lang/Exception
    //   68	76	100	java/lang/Exception
    //   80	88	108	java/lang/Exception
    //   524	532	532	java/lang/Exception
    //   498	506	539	java/lang/Exception
    //   510	518	547	java/lang/Exception
    //   580	588	610	java/lang/Exception
    //   592	600	620	java/lang/Exception
    //   604	608	630	java/lang/Exception
    //   361	368	650	java/lang/Exception
    //   368	375	650	java/lang/Exception
    //   638	647	650	java/lang/Exception
    //   745	749	755	java/lang/Exception
    //   724	728	766	java/lang/Exception
    //   733	738	774	java/lang/Exception
    //   44	48	782	finally
    //   131	137	782	finally
    //   152	167	782	finally
    //   184	191	782	finally
    //   206	221	782	finally
    //   235	239	782	finally
    //   253	259	782	finally
    //   273	286	782	finally
    //   426	435	782	finally
    //   450	471	782	finally
    //   486	494	782	finally
    //   674	679	782	finally
    //   689	696	782	finally
    //   711	717	782	finally
    //   791	796	816	java/lang/Exception
    //   800	804	826	java/lang/Exception
    //   809	814	834	java/lang/Exception
    //   384	388	842	java/lang/Exception
    //   393	398	850	java/lang/Exception
    //   402	406	858	java/lang/Exception
    //   294	301	869	finally
    //   309	317	869	finally
    //   325	331	869	finally
    //   339	344	869	finally
    //   350	361	869	finally
    //   570	576	869	finally
    //   361	368	883	finally
    //   368	375	883	finally
    //   638	647	883	finally
    //   44	48	900	java/lang/Exception
    //   131	137	900	java/lang/Exception
    //   152	167	900	java/lang/Exception
    //   184	191	900	java/lang/Exception
    //   206	221	900	java/lang/Exception
    //   235	239	900	java/lang/Exception
    //   253	259	900	java/lang/Exception
    //   273	286	900	java/lang/Exception
    //   426	435	900	java/lang/Exception
    //   450	471	900	java/lang/Exception
    //   486	494	900	java/lang/Exception
    //   294	301	919	java/lang/Exception
    //   309	317	919	java/lang/Exception
    //   325	331	919	java/lang/Exception
    //   339	344	919	java/lang/Exception
    //   350	361	919	java/lang/Exception
    //   570	576	919	java/lang/Exception
  }

  public static int freeSpaceOnSD()
  {
    StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
    return (int)(localStatFs.getAvailableBlocks() * localStatFs.getBlockSize() / 1024.0D * 1024.0D);
  }

  public static Bitmap getBitmapFromAsset(Context paramContext, String paramString)
  {
    try
    {
      paramContext = BitmapFactory.decodeStream(paramContext.getAssets().open(paramString));
      return paramContext;
    }
    catch (Exception paramContext)
    {
      AbLogUtil.d(AbFileUtil.class, "获取图片异常：" + paramContext.getMessage());
    }
    return null;
  }

  // ERROR //
  public static Bitmap getBitmapFromByte(byte[] paramArrayOfByte, String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 14
    //   3: aconst_null
    //   4: astore 17
    //   6: aconst_null
    //   7: astore 16
    //   9: aconst_null
    //   10: astore 7
    //   12: aconst_null
    //   13: astore 10
    //   15: aconst_null
    //   16: astore 18
    //   18: aconst_null
    //   19: astore 12
    //   21: aconst_null
    //   22: astore 9
    //   24: aconst_null
    //   25: astore 13
    //   27: aconst_null
    //   28: astore 19
    //   30: aconst_null
    //   31: astore 15
    //   33: aconst_null
    //   34: astore 8
    //   36: aconst_null
    //   37: astore 11
    //   39: aconst_null
    //   40: astore 6
    //   42: aload_0
    //   43: ifnull +138 -> 181
    //   46: aload 19
    //   48: astore 7
    //   50: aload 18
    //   52: astore 8
    //   54: aload 17
    //   56: astore 9
    //   58: new 43	java/io/File
    //   61: dup
    //   62: new 45	java/lang/StringBuilder
    //   65: dup
    //   66: getstatic 21	com/ismartgo/app/andbase/util/AbFileUtil:imageDownloadDir	Ljava/lang/String;
    //   69: invokestatic 61	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   72: invokespecial 64	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   75: aload_1
    //   76: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: invokevirtual 71	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   82: invokespecial 72	java/io/File:<init>	(Ljava/lang/String;)V
    //   85: astore 17
    //   87: aload 17
    //   89: invokevirtual 115	java/io/File:exists	()Z
    //   92: ifne +9 -> 101
    //   95: aload 17
    //   97: invokevirtual 118	java/io/File:createNewFile	()Z
    //   100: pop
    //   101: new 124	java/io/FileOutputStream
    //   104: dup
    //   105: aload 17
    //   107: invokespecial 127	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   110: astore_1
    //   111: new 214	java/io/ByteArrayInputStream
    //   114: dup
    //   115: aload_0
    //   116: invokespecial 217	java/io/ByteArrayInputStream:<init>	([B)V
    //   119: astore_0
    //   120: new 219	java/io/DataInputStream
    //   123: dup
    //   124: aload_0
    //   125: invokespecial 222	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   128: astore 6
    //   130: sipush 1024
    //   133: newarray byte
    //   135: astore 7
    //   137: aload 6
    //   139: aload 7
    //   141: invokevirtual 223	java/io/DataInputStream:read	([B)I
    //   144: istore 5
    //   146: iload 5
    //   148: iconst_m1
    //   149: if_icmpne +70 -> 219
    //   152: aload_1
    //   153: invokevirtual 226	java/io/FileOutputStream:flush	()V
    //   156: aload 17
    //   158: iload_2
    //   159: iload_3
    //   160: iload 4
    //   162: invokestatic 230	com/ismartgo/app/andbase/util/AbFileUtil:getBitmapFromSD	(Ljava/io/File;III)Landroid/graphics/Bitmap;
    //   165: astore 8
    //   167: aload_1
    //   168: astore 7
    //   170: aload 6
    //   172: astore 9
    //   174: aload 8
    //   176: astore 6
    //   178: aload_0
    //   179: astore 8
    //   181: aload 9
    //   183: ifnull +8 -> 191
    //   186: aload 9
    //   188: invokevirtual 231	java/io/DataInputStream:close	()V
    //   191: aload 8
    //   193: ifnull +8 -> 201
    //   196: aload 8
    //   198: invokevirtual 232	java/io/ByteArrayInputStream:close	()V
    //   201: aload 6
    //   203: astore_0
    //   204: aload 7
    //   206: ifnull +11 -> 217
    //   209: aload 7
    //   211: invokevirtual 137	java/io/FileOutputStream:close	()V
    //   214: aload 6
    //   216: astore_0
    //   217: aload_0
    //   218: areturn
    //   219: aload_1
    //   220: aload 7
    //   222: iconst_0
    //   223: iload 5
    //   225: invokevirtual 161	java/io/FileOutputStream:write	([BII)V
    //   228: ldc2_w 233
    //   231: invokestatic 240	java/lang/Thread:sleep	(J)V
    //   234: goto -97 -> 137
    //   237: astore 8
    //   239: goto -102 -> 137
    //   242: astore_0
    //   243: aload 14
    //   245: astore_1
    //   246: aload 13
    //   248: astore 6
    //   250: aload 6
    //   252: astore 7
    //   254: aload 10
    //   256: astore 8
    //   258: aload_1
    //   259: astore 9
    //   261: aload_0
    //   262: invokevirtual 82	java/lang/Exception:printStackTrace	()V
    //   265: aload 10
    //   267: ifnull +8 -> 275
    //   270: aload 10
    //   272: invokevirtual 231	java/io/DataInputStream:close	()V
    //   275: aload 6
    //   277: ifnull +8 -> 285
    //   280: aload 6
    //   282: invokevirtual 232	java/io/ByteArrayInputStream:close	()V
    //   285: aload 11
    //   287: astore_0
    //   288: aload_1
    //   289: ifnull -72 -> 217
    //   292: aload_1
    //   293: invokevirtual 137	java/io/FileOutputStream:close	()V
    //   296: aconst_null
    //   297: areturn
    //   298: astore_0
    //   299: aconst_null
    //   300: areturn
    //   301: astore_1
    //   302: aload 9
    //   304: astore_0
    //   305: aload 7
    //   307: astore 6
    //   309: aload 8
    //   311: ifnull +8 -> 319
    //   314: aload 8
    //   316: invokevirtual 231	java/io/DataInputStream:close	()V
    //   319: aload 6
    //   321: ifnull +8 -> 329
    //   324: aload 6
    //   326: invokevirtual 232	java/io/ByteArrayInputStream:close	()V
    //   329: aload_0
    //   330: ifnull +7 -> 337
    //   333: aload_0
    //   334: invokevirtual 137	java/io/FileOutputStream:close	()V
    //   337: aload_1
    //   338: athrow
    //   339: astore_0
    //   340: goto -65 -> 275
    //   343: astore_0
    //   344: goto -59 -> 285
    //   347: astore 7
    //   349: goto -30 -> 319
    //   352: astore 6
    //   354: goto -25 -> 329
    //   357: astore_0
    //   358: goto -21 -> 337
    //   361: astore_0
    //   362: goto -171 -> 191
    //   365: astore_0
    //   366: goto -165 -> 201
    //   369: astore_0
    //   370: aload 6
    //   372: areturn
    //   373: astore_1
    //   374: aload 15
    //   376: astore 6
    //   378: aload 12
    //   380: astore 8
    //   382: aload 16
    //   384: astore_0
    //   385: goto -76 -> 309
    //   388: astore 7
    //   390: aload_1
    //   391: astore_0
    //   392: aload 15
    //   394: astore 6
    //   396: aload 12
    //   398: astore 8
    //   400: aload 7
    //   402: astore_1
    //   403: goto -94 -> 309
    //   406: astore 7
    //   408: aload_0
    //   409: astore 6
    //   411: aload_1
    //   412: astore_0
    //   413: aload 12
    //   415: astore 8
    //   417: aload 7
    //   419: astore_1
    //   420: goto -111 -> 309
    //   423: astore 7
    //   425: aload 6
    //   427: astore 8
    //   429: aload_0
    //   430: astore 6
    //   432: aload_1
    //   433: astore_0
    //   434: aload 7
    //   436: astore_1
    //   437: goto -128 -> 309
    //   440: astore_0
    //   441: aload 13
    //   443: astore 6
    //   445: aload 14
    //   447: astore_1
    //   448: goto -198 -> 250
    //   451: astore_0
    //   452: aload 13
    //   454: astore 6
    //   456: goto -206 -> 250
    //   459: astore 7
    //   461: aload_0
    //   462: astore 6
    //   464: aload 7
    //   466: astore_0
    //   467: goto -217 -> 250
    //   470: astore 7
    //   472: aload 6
    //   474: astore 10
    //   476: aload_0
    //   477: astore 6
    //   479: aload 7
    //   481: astore_0
    //   482: goto -232 -> 250
    //
    // Exception table:
    //   from	to	target	type
    //   228	234	237	java/lang/Exception
    //   58	87	242	java/lang/Exception
    //   292	296	298	java/lang/Exception
    //   58	87	301	finally
    //   261	265	301	finally
    //   270	275	339	java/lang/Exception
    //   280	285	343	java/lang/Exception
    //   314	319	347	java/lang/Exception
    //   324	329	352	java/lang/Exception
    //   333	337	357	java/lang/Exception
    //   186	191	361	java/lang/Exception
    //   196	201	365	java/lang/Exception
    //   209	214	369	java/lang/Exception
    //   87	101	373	finally
    //   101	111	373	finally
    //   111	120	388	finally
    //   120	130	406	finally
    //   130	137	423	finally
    //   137	146	423	finally
    //   152	167	423	finally
    //   219	228	423	finally
    //   228	234	423	finally
    //   87	101	440	java/lang/Exception
    //   101	111	440	java/lang/Exception
    //   111	120	451	java/lang/Exception
    //   120	130	459	java/lang/Exception
    //   130	137	470	java/lang/Exception
    //   137	146	470	java/lang/Exception
    //   152	167	470	java/lang/Exception
    //   219	228	470	java/lang/Exception
  }

  public static Bitmap getBitmapFromSD(File paramFile)
  {
    Object localObject = null;
    try
    {
      if (!isCanUseSD())
        return null;
      if (paramFile.exists())
      {
        paramFile = AbImageUtil.getBitmap(paramFile);
        return paramFile;
      }
    }
    catch (Exception paramFile)
    {
      while (true)
      {
        paramFile.printStackTrace();
        paramFile = localObject;
      }
    }
    return null;
  }

  public static Bitmap getBitmapFromSD(File paramFile, int paramInt1, int paramInt2, int paramInt3)
  {
    Object localObject = null;
    try
    {
      if (!isCanUseSD())
        return null;
      if (paramFile.exists())
        if (paramInt1 == 0)
          paramFile = AbImageUtil.cutImg(paramFile, paramInt2, paramInt3);
        else if (paramInt1 == 1)
          paramFile = AbImageUtil.scaleImg(paramFile, paramInt2, paramInt3);
        else
          paramFile = AbImageUtil.getBitmap(paramFile);
    }
    catch (Exception paramFile)
    {
      paramFile.printStackTrace();
      paramFile = localObject;
    }
    return null;
    return paramFile;
  }

  public static Bitmap getBitmapFromSD(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    try
    {
      if (AbStrUtil.isEmpty(paramString))
        return null;
      if ((!isCanUseSD()) || (freeSdSpaceNeededToCache < freeSpaceOnSD()))
        return getBitmapFromURL(paramString, paramInt1, paramInt2, paramInt3);
      paramString = downloadFile(paramString, imageDownloadDir);
      if (paramString != null)
      {
        paramString = getBitmapFromSD(new File(paramString), paramInt1, paramInt2, paramInt3);
        return paramString;
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  public static Bitmap getBitmapFromSrc(String paramString)
  {
    try
    {
      paramString = BitmapFactory.decodeStream(AbFileUtil.class.getResourceAsStream(paramString));
      return paramString;
    }
    catch (Exception paramString)
    {
      AbLogUtil.d(AbFileUtil.class, "获取图片异常：" + paramString.getMessage());
    }
    return null;
  }

  public static Bitmap getBitmapFromURL(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    try
    {
      paramString = AbImageUtil.getBitmap(paramString, paramInt1, paramInt2, paramInt3);
      return paramString;
    }
    catch (Exception paramString)
    {
      AbLogUtil.d(AbFileUtil.class, "下载图片异常：" + paramString.getMessage());
    }
    return null;
  }

  // ERROR //
  public static byte[] getByteArrayFromSD(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 7
    //   3: aconst_null
    //   4: astore 6
    //   6: aconst_null
    //   7: astore 8
    //   9: aload 6
    //   11: astore 5
    //   13: new 43	java/io/File
    //   16: dup
    //   17: aload_0
    //   18: invokespecial 72	java/io/File:<init>	(Ljava/lang/String;)V
    //   21: astore 9
    //   23: aload 6
    //   25: astore 5
    //   27: invokestatic 41	com/ismartgo/app/andbase/util/AbFileUtil:isCanUseSD	()Z
    //   30: istore 4
    //   32: iload 4
    //   34: ifne +17 -> 51
    //   37: iconst_0
    //   38: ifeq +11 -> 49
    //   41: new 86	java/lang/NullPointerException
    //   44: dup
    //   45: invokespecial 87	java/lang/NullPointerException:<init>	()V
    //   48: athrow
    //   49: aconst_null
    //   50: areturn
    //   51: aload 6
    //   53: astore 5
    //   55: aload 9
    //   57: invokevirtual 115	java/io/File:exists	()Z
    //   60: istore 4
    //   62: iload 4
    //   64: ifne +18 -> 82
    //   67: iconst_0
    //   68: ifeq -19 -> 49
    //   71: new 86	java/lang/NullPointerException
    //   74: dup
    //   75: invokespecial 87	java/lang/NullPointerException:<init>	()V
    //   78: athrow
    //   79: astore_0
    //   80: aconst_null
    //   81: areturn
    //   82: aload 6
    //   84: astore 5
    //   86: aload 9
    //   88: invokevirtual 284	java/io/File:length	()J
    //   91: lstore_2
    //   92: lload_2
    //   93: ldc2_w 285
    //   96: lcmp
    //   97: ifle +18 -> 115
    //   100: iconst_0
    //   101: ifeq -52 -> 49
    //   104: new 86	java/lang/NullPointerException
    //   107: dup
    //   108: invokespecial 87	java/lang/NullPointerException:<init>	()V
    //   111: athrow
    //   112: astore_0
    //   113: aconst_null
    //   114: areturn
    //   115: aload 6
    //   117: astore 5
    //   119: new 288	java/io/FileInputStream
    //   122: dup
    //   123: aload_0
    //   124: invokespecial 289	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   127: astore 9
    //   129: aload 6
    //   131: astore 5
    //   133: new 291	java/io/ByteArrayOutputStream
    //   136: dup
    //   137: sipush 1024
    //   140: invokespecial 294	java/io/ByteArrayOutputStream:<init>	(I)V
    //   143: astore_0
    //   144: sipush 1024
    //   147: newarray byte
    //   149: astore 5
    //   151: aload 9
    //   153: aload 5
    //   155: invokevirtual 295	java/io/FileInputStream:read	([B)I
    //   158: istore_1
    //   159: iload_1
    //   160: iconst_m1
    //   161: if_icmpne +25 -> 186
    //   164: aload 9
    //   166: invokevirtual 296	java/io/FileInputStream:close	()V
    //   169: aload_0
    //   170: invokevirtual 300	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   173: astore 5
    //   175: aload_0
    //   176: ifnull +102 -> 278
    //   179: aload_0
    //   180: invokevirtual 301	java/io/ByteArrayOutputStream:close	()V
    //   183: aload 5
    //   185: areturn
    //   186: aload_0
    //   187: aload 5
    //   189: iconst_0
    //   190: iload_1
    //   191: invokevirtual 302	java/io/ByteArrayOutputStream:write	([BII)V
    //   194: goto -43 -> 151
    //   197: astore 6
    //   199: aload_0
    //   200: astore 5
    //   202: aload 6
    //   204: invokevirtual 82	java/lang/Exception:printStackTrace	()V
    //   207: aload 7
    //   209: astore 5
    //   211: aload_0
    //   212: ifnull -29 -> 183
    //   215: aload_0
    //   216: invokevirtual 301	java/io/ByteArrayOutputStream:close	()V
    //   219: aload 7
    //   221: astore 5
    //   223: goto -40 -> 183
    //   226: astore_0
    //   227: aload 7
    //   229: astore 5
    //   231: goto -48 -> 183
    //   234: astore_0
    //   235: aload 5
    //   237: ifnull +8 -> 245
    //   240: aload 5
    //   242: invokevirtual 301	java/io/ByteArrayOutputStream:close	()V
    //   245: aload_0
    //   246: athrow
    //   247: astore_0
    //   248: goto -65 -> 183
    //   251: astore_0
    //   252: aconst_null
    //   253: areturn
    //   254: astore 5
    //   256: goto -11 -> 245
    //   259: astore 6
    //   261: aload_0
    //   262: astore 5
    //   264: aload 6
    //   266: astore_0
    //   267: goto -32 -> 235
    //   270: astore 6
    //   272: aload 8
    //   274: astore_0
    //   275: goto -76 -> 199
    //   278: goto -95 -> 183
    //
    // Exception table:
    //   from	to	target	type
    //   71	79	79	java/lang/Exception
    //   104	112	112	java/lang/Exception
    //   144	151	197	java/lang/Exception
    //   151	159	197	java/lang/Exception
    //   164	175	197	java/lang/Exception
    //   186	194	197	java/lang/Exception
    //   215	219	226	java/lang/Exception
    //   13	23	234	finally
    //   27	32	234	finally
    //   55	62	234	finally
    //   86	92	234	finally
    //   119	129	234	finally
    //   133	144	234	finally
    //   202	207	234	finally
    //   179	183	247	java/lang/Exception
    //   41	49	251	java/lang/Exception
    //   240	245	254	java/lang/Exception
    //   144	151	259	finally
    //   151	159	259	finally
    //   164	175	259	finally
    //   186	194	259	finally
    //   13	23	270	java/lang/Exception
    //   27	32	270	java/lang/Exception
    //   55	62	270	java/lang/Exception
    //   86	92	270	java/lang/Exception
    //   119	129	270	java/lang/Exception
    //   133	144	270	java/lang/Exception
  }

  public static String getCacheDownloadDir(Context paramContext)
  {
    if (downloadRootDir == null)
      initFileDir(paramContext);
    return cacheDownloadDir;
  }

  public static String getCacheFileNameFromUrl(String paramString)
  {
    if (AbStrUtil.isEmpty(paramString))
      return null;
    try
    {
      paramString = AbMd5.MD5(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  public static String getCacheFileNameFromUrl(String paramString, HttpURLConnection paramHttpURLConnection)
  {
    if (AbStrUtil.isEmpty(paramString))
      return null;
    try
    {
      String str = getMIMEFromUrl(paramString, paramHttpURLConnection);
      paramHttpURLConnection = str;
      if (AbStrUtil.isEmpty(str))
        paramHttpURLConnection = ".ab";
      paramString = AbMd5.MD5(paramString) + paramHttpURLConnection;
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  public static String getCacheFileNameFromUrl(String paramString, HttpResponse paramHttpResponse)
  {
    if (AbStrUtil.isEmpty(paramString))
      return null;
    try
    {
      String str = getMIMEFromUrl(paramString, paramHttpResponse);
      paramHttpResponse = str;
      if (AbStrUtil.isEmpty(str))
        paramHttpResponse = ".ab";
      paramString = AbMd5.MD5(paramString) + paramHttpResponse;
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  public static int getContentLengthFromUrl(String paramString)
  {
    int i = 0;
    try
    {
      HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL(paramString).openConnection();
      localHttpURLConnection.setConnectTimeout(5000);
      localHttpURLConnection.setRequestMethod("GET");
      localHttpURLConnection.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
      localHttpURLConnection.setRequestProperty("Accept-Language", "zh-CN");
      localHttpURLConnection.setRequestProperty("Referer", paramString);
      localHttpURLConnection.setRequestProperty("Charset", "UTF-8");
      localHttpURLConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
      localHttpURLConnection.setRequestProperty("Connection", "Keep-Alive");
      localHttpURLConnection.connect();
      if (localHttpURLConnection.getResponseCode() == 200)
        i = localHttpURLConnection.getContentLength();
      return i;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      AbLogUtil.d(AbFileUtil.class, "获取长度异常：" + paramString.getMessage());
    }
    return 0;
  }

  public static String getDbDownloadDir(Context paramContext)
  {
    if (downloadRootDir == null)
      initFileDir(paramContext);
    return dbDownloadDir;
  }

  public static String getDownloadRootDir(Context paramContext)
  {
    if (downloadRootDir == null)
      initFileDir(paramContext);
    return downloadRootDir;
  }

  public static Drawable getDrawableFromAsset(Context paramContext, String paramString)
  {
    try
    {
      paramContext = Drawable.createFromStream(paramContext.getAssets().open(paramString), null);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      AbLogUtil.d(AbFileUtil.class, "获取图片异常：" + paramContext.getMessage());
    }
    return null;
  }

  public static String getFileDownloadDir(Context paramContext)
  {
    if (downloadRootDir == null)
      initFileDir(paramContext);
    return fileDownloadDir;
  }

  public static int getFreeSdSpaceNeededToCache()
  {
    return freeSdSpaceNeededToCache;
  }

  public static String getImageDownloadDir(Context paramContext)
  {
    if (downloadRootDir == null)
      initFileDir(paramContext);
    return imageDownloadDir;
  }

  public static String getMIMEFromUrl(String paramString, HttpURLConnection paramHttpURLConnection)
  {
    if (AbStrUtil.isEmpty(paramString))
    {
      paramString = null;
      return paramString;
    }
    Object localObject2 = null;
    String str = null;
    Object localObject1 = localObject2;
    while (true)
    {
      try
      {
        if (paramString.lastIndexOf(".") != -1)
        {
          localObject1 = localObject2;
          paramString = paramString.substring(paramString.lastIndexOf("."));
          localObject1 = paramString;
          if (paramString.indexOf("/") == -1)
          {
            localObject1 = paramString;
            if (paramString.indexOf("?") == -1)
            {
              str = paramString;
              localObject1 = paramString;
              if (paramString.indexOf("&") != -1);
            }
          }
        }
        else
        {
          paramString = str;
          localObject1 = str;
          if (!AbStrUtil.isEmpty(str))
            break;
          localObject1 = str;
          paramHttpURLConnection = getRealFileName(paramHttpURLConnection);
          paramString = str;
          if (paramHttpURLConnection == null)
            break;
          paramString = str;
          localObject1 = str;
          if (paramHttpURLConnection.lastIndexOf(".") == -1)
            break;
          localObject1 = str;
          paramString = paramHttpURLConnection.substring(paramHttpURLConnection.lastIndexOf("."));
          return paramString;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return localObject1;
      }
      str = null;
    }
  }

  public static String getMIMEFromUrl(String paramString, HttpResponse paramHttpResponse)
  {
    if (AbStrUtil.isEmpty(paramString))
    {
      paramString = null;
      return paramString;
    }
    Object localObject2 = null;
    String str = null;
    Object localObject1 = localObject2;
    while (true)
    {
      try
      {
        if (paramString.lastIndexOf(".") != -1)
        {
          localObject1 = localObject2;
          paramString = paramString.substring(paramString.lastIndexOf("."));
          localObject1 = paramString;
          if (paramString.indexOf("/") == -1)
          {
            localObject1 = paramString;
            if (paramString.indexOf("?") == -1)
            {
              str = paramString;
              localObject1 = paramString;
              if (paramString.indexOf("&") != -1);
            }
          }
        }
        else
        {
          paramString = str;
          localObject1 = str;
          if (!AbStrUtil.isEmpty(str))
            break;
          localObject1 = str;
          paramHttpResponse = getRealFileName(paramHttpResponse);
          paramString = str;
          if (paramHttpResponse == null)
            break;
          paramString = str;
          localObject1 = str;
          if (paramHttpResponse.lastIndexOf(".") == -1)
            break;
          localObject1 = str;
          paramString = paramHttpResponse.substring(paramHttpResponse.lastIndexOf("."));
          return paramString;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return localObject1;
      }
      str = null;
    }
  }

  public static String getRealFileName(HttpURLConnection paramHttpURLConnection)
  {
    if (paramHttpURLConnection == null);
    while (true)
    {
      return null;
      try
      {
        if (paramHttpURLConnection.getResponseCode() == 200)
        {
          int i = 0;
          while (true)
          {
            Object localObject = paramHttpURLConnection.getHeaderField(i);
            if (localObject == null)
              break;
            if ("content-disposition".equals(paramHttpURLConnection.getHeaderFieldKey(i).toLowerCase()))
            {
              localObject = Pattern.compile(".*filename=(.*)").matcher(((String)localObject).toLowerCase());
              if (((Matcher)localObject).find())
              {
                paramHttpURLConnection = ((Matcher)localObject).group(1).replace("\"", "");
                return paramHttpURLConnection;
              }
            }
            i += 1;
          }
        }
      }
      catch (Exception paramHttpURLConnection)
      {
        paramHttpURLConnection.printStackTrace();
        AbLogUtil.e(AbFileUtil.class, "网络上获取文件名失败");
      }
    }
    return null;
  }

  public static String getRealFileName(HttpResponse paramHttpResponse)
  {
    HttpResponse localHttpResponse = null;
    Object localObject = null;
    if (paramHttpResponse == null)
      return null;
    try
    {
      Header[] arrayOfHeader = paramHttpResponse.getHeaders("content-disposition");
      int i = 0;
      for (paramHttpResponse = (HttpResponse)localObject; ; paramHttpResponse = (HttpResponse)localObject)
      {
        localHttpResponse = paramHttpResponse;
        if (i >= arrayOfHeader.length)
          break;
        localHttpResponse = paramHttpResponse;
        Matcher localMatcher = Pattern.compile(".*filename=(.*)").matcher(arrayOfHeader[i].getValue());
        localObject = paramHttpResponse;
        localHttpResponse = paramHttpResponse;
        if (localMatcher.find())
        {
          localHttpResponse = paramHttpResponse;
          localObject = localMatcher.group(1).replace("\"", "");
        }
        i += 1;
      }
    }
    catch (Exception paramHttpResponse)
    {
      paramHttpResponse.printStackTrace();
      AbLogUtil.e(AbFileUtil.class, "网络上获取文件名失败");
      paramHttpResponse = localHttpResponse;
    }
    return paramHttpResponse;
  }

  public static String getRealFileNameFromUrl(String paramString)
  {
    try
    {
      if (AbStrUtil.isEmpty(paramString))
        return null;
      HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL(paramString).openConnection();
      localHttpURLConnection.setConnectTimeout(5000);
      localHttpURLConnection.setRequestMethod("GET");
      localHttpURLConnection.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
      localHttpURLConnection.setRequestProperty("Accept-Language", "zh-CN");
      localHttpURLConnection.setRequestProperty("Referer", paramString);
      localHttpURLConnection.setRequestProperty("Charset", "UTF-8");
      localHttpURLConnection.setRequestProperty("User-Agent", "");
      localHttpURLConnection.setRequestProperty("Connection", "Keep-Alive");
      localHttpURLConnection.connect();
      if (localHttpURLConnection.getResponseCode() == 200)
      {
        int i = 0;
        while (true)
        {
          paramString = localHttpURLConnection.getHeaderField(i);
          if (paramString == null)
            break;
          if ("content-disposition".equals(localHttpURLConnection.getHeaderFieldKey(i).toLowerCase()))
          {
            paramString = Pattern.compile(".*filename=(.*)").matcher(paramString.toLowerCase());
            if (paramString.find())
            {
              paramString = paramString.group(1).replace("\"", "");
              return paramString;
            }
          }
          i += 1;
        }
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      AbLogUtil.e(AbFileUtil.class, "网络上获取文件名失败");
    }
    return null;
  }

  public static void initFileDir(Context paramContext)
  {
    paramContext = AbAppUtil.getPackageInfo(paramContext);
    Object localObject4 = File.separator + AbAppConfig.DOWNLOAD_ROOT_DIR + File.separator + paramContext.packageName + File.separator;
    Object localObject2 = localObject4 + AbAppConfig.DOWNLOAD_IMAGE_DIR + File.separator;
    Object localObject1 = localObject4 + AbAppConfig.DOWNLOAD_FILE_DIR + File.separator;
    Object localObject3 = localObject4 + AbAppConfig.CACHE_DIR + File.separator;
    paramContext = localObject4 + AbAppConfig.DB_DIR + File.separator;
    try
    {
      if (!isCanUseSD())
        return;
      File localFile = Environment.getExternalStorageDirectory();
      localObject4 = new File(localFile.getAbsolutePath() + (String)localObject4);
      if (!((File)localObject4).exists())
        ((File)localObject4).mkdirs();
      downloadRootDir = ((File)localObject4).getPath();
      localObject3 = new File(localFile.getAbsolutePath() + (String)localObject3);
      if (!((File)localObject3).exists())
        ((File)localObject3).mkdirs();
      cacheDownloadDir = ((File)localObject3).getPath();
      localObject2 = new File(localFile.getAbsolutePath() + (String)localObject2);
      if (!((File)localObject2).exists())
        ((File)localObject2).mkdirs();
      imageDownloadDir = ((File)localObject2).getPath();
      localObject1 = new File(localFile.getAbsolutePath() + (String)localObject1);
      if (!((File)localObject1).exists())
        ((File)localObject1).mkdirs();
      fileDownloadDir = ((File)localObject1).getPath();
      paramContext = new File(localFile.getAbsolutePath() + paramContext);
      if (!paramContext.exists())
        paramContext.mkdirs();
      dbDownloadDir = paramContext.getPath();
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }

  public static boolean isCanUseSD()
  {
    try
    {
      boolean bool = Environment.getExternalStorageState().equals("mounted");
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  // ERROR //
  public static String readAssetsByName(Context paramContext, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 4
    //   6: aconst_null
    //   7: astore 7
    //   9: aconst_null
    //   10: astore_3
    //   11: aconst_null
    //   12: astore 8
    //   14: aconst_null
    //   15: astore 6
    //   17: new 491	java/io/InputStreamReader
    //   20: dup
    //   21: aload_0
    //   22: invokevirtual 190	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   25: aload_1
    //   26: invokevirtual 196	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   29: invokespecial 492	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   32: astore_0
    //   33: new 494	java/io/BufferedReader
    //   36: dup
    //   37: aload_0
    //   38: invokespecial 497	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   41: astore_1
    //   42: new 499	java/lang/StringBuffer
    //   45: dup
    //   46: invokespecial 500	java/lang/StringBuffer:<init>	()V
    //   49: astore_3
    //   50: aload_1
    //   51: invokevirtual 503	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   54: astore 4
    //   56: aload 4
    //   58: ifnonnull +39 -> 97
    //   61: new 57	java/lang/String
    //   64: dup
    //   65: aload_3
    //   66: invokevirtual 504	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   69: invokevirtual 507	java/lang/String:getBytes	()[B
    //   72: aload_2
    //   73: invokespecial 510	java/lang/String:<init>	([BLjava/lang/String;)V
    //   76: astore_2
    //   77: aload_1
    //   78: ifnull +7 -> 85
    //   81: aload_1
    //   82: invokevirtual 511	java/io/BufferedReader:close	()V
    //   85: aload_0
    //   86: ifnull +93 -> 179
    //   89: aload_0
    //   90: invokevirtual 512	java/io/InputStreamReader:close	()V
    //   93: aload_2
    //   94: astore_1
    //   95: aload_1
    //   96: areturn
    //   97: aload_3
    //   98: aload 4
    //   100: invokevirtual 515	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   103: pop
    //   104: goto -54 -> 50
    //   107: astore_2
    //   108: aload_1
    //   109: astore_3
    //   110: aload_0
    //   111: astore 4
    //   113: aload_2
    //   114: invokevirtual 82	java/lang/Exception:printStackTrace	()V
    //   117: aload_1
    //   118: ifnull +7 -> 125
    //   121: aload_1
    //   122: invokevirtual 511	java/io/BufferedReader:close	()V
    //   125: aload 5
    //   127: astore_1
    //   128: aload_0
    //   129: ifnull -34 -> 95
    //   132: aload_0
    //   133: invokevirtual 512	java/io/InputStreamReader:close	()V
    //   136: aconst_null
    //   137: areturn
    //   138: astore_0
    //   139: aload_0
    //   140: invokevirtual 82	java/lang/Exception:printStackTrace	()V
    //   143: aconst_null
    //   144: areturn
    //   145: astore_0
    //   146: aload_3
    //   147: ifnull +7 -> 154
    //   150: aload_3
    //   151: invokevirtual 511	java/io/BufferedReader:close	()V
    //   154: aload 4
    //   156: ifnull +8 -> 164
    //   159: aload 4
    //   161: invokevirtual 512	java/io/InputStreamReader:close	()V
    //   164: aload_0
    //   165: athrow
    //   166: astore_1
    //   167: aload_1
    //   168: invokevirtual 82	java/lang/Exception:printStackTrace	()V
    //   171: goto -7 -> 164
    //   174: astore_0
    //   175: aload_0
    //   176: invokevirtual 82	java/lang/Exception:printStackTrace	()V
    //   179: aload_2
    //   180: areturn
    //   181: astore_1
    //   182: aload 8
    //   184: astore_3
    //   185: aload_0
    //   186: astore 4
    //   188: aload_1
    //   189: astore_0
    //   190: goto -44 -> 146
    //   193: astore_2
    //   194: aload_1
    //   195: astore_3
    //   196: aload_0
    //   197: astore 4
    //   199: aload_2
    //   200: astore_0
    //   201: goto -55 -> 146
    //   204: astore_2
    //   205: aload 6
    //   207: astore_1
    //   208: aload 7
    //   210: astore_0
    //   211: goto -103 -> 108
    //   214: astore_2
    //   215: aload 6
    //   217: astore_1
    //   218: goto -110 -> 108
    //
    // Exception table:
    //   from	to	target	type
    //   42	50	107	java/lang/Exception
    //   50	56	107	java/lang/Exception
    //   61	77	107	java/lang/Exception
    //   97	104	107	java/lang/Exception
    //   121	125	138	java/lang/Exception
    //   132	136	138	java/lang/Exception
    //   17	33	145	finally
    //   113	117	145	finally
    //   150	154	166	java/lang/Exception
    //   159	164	166	java/lang/Exception
    //   81	85	174	java/lang/Exception
    //   89	93	174	java/lang/Exception
    //   33	42	181	finally
    //   42	50	193	finally
    //   50	56	193	finally
    //   61	77	193	finally
    //   97	104	193	finally
    //   17	33	204	java/lang/Exception
    //   33	42	214	java/lang/Exception
  }

  // ERROR //
  public static String readRawByName(Context paramContext, int paramInt, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore 4
    //   6: aconst_null
    //   7: astore 7
    //   9: aconst_null
    //   10: astore_3
    //   11: aconst_null
    //   12: astore 8
    //   14: aconst_null
    //   15: astore 5
    //   17: new 491	java/io/InputStreamReader
    //   20: dup
    //   21: aload_0
    //   22: invokevirtual 521	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   25: iload_1
    //   26: invokevirtual 527	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   29: invokespecial 492	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   32: astore_0
    //   33: new 494	java/io/BufferedReader
    //   36: dup
    //   37: aload_0
    //   38: invokespecial 497	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   41: astore_3
    //   42: new 499	java/lang/StringBuffer
    //   45: dup
    //   46: invokespecial 500	java/lang/StringBuffer:<init>	()V
    //   49: astore 4
    //   51: aload_3
    //   52: invokevirtual 503	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   55: astore 5
    //   57: aload 5
    //   59: ifnonnull +38 -> 97
    //   62: new 57	java/lang/String
    //   65: dup
    //   66: aload 4
    //   68: invokevirtual 504	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   71: invokevirtual 507	java/lang/String:getBytes	()[B
    //   74: aload_2
    //   75: invokespecial 510	java/lang/String:<init>	([BLjava/lang/String;)V
    //   78: astore_2
    //   79: aload_3
    //   80: ifnull +7 -> 87
    //   83: aload_3
    //   84: invokevirtual 511	java/io/BufferedReader:close	()V
    //   87: aload_0
    //   88: ifnull +96 -> 184
    //   91: aload_0
    //   92: invokevirtual 512	java/io/InputStreamReader:close	()V
    //   95: aload_2
    //   96: areturn
    //   97: aload 4
    //   99: aload 5
    //   101: invokevirtual 515	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   104: pop
    //   105: goto -54 -> 51
    //   108: astore 5
    //   110: aload_3
    //   111: astore_2
    //   112: aload_2
    //   113: astore_3
    //   114: aload_0
    //   115: astore 4
    //   117: aload 5
    //   119: invokevirtual 82	java/lang/Exception:printStackTrace	()V
    //   122: aload_2
    //   123: ifnull +7 -> 130
    //   126: aload_2
    //   127: invokevirtual 511	java/io/BufferedReader:close	()V
    //   130: aload 6
    //   132: astore_2
    //   133: aload_0
    //   134: ifnull -39 -> 95
    //   137: aload_0
    //   138: invokevirtual 512	java/io/InputStreamReader:close	()V
    //   141: aconst_null
    //   142: areturn
    //   143: astore_0
    //   144: aload_0
    //   145: invokevirtual 82	java/lang/Exception:printStackTrace	()V
    //   148: aconst_null
    //   149: areturn
    //   150: astore_0
    //   151: aload_3
    //   152: ifnull +7 -> 159
    //   155: aload_3
    //   156: invokevirtual 511	java/io/BufferedReader:close	()V
    //   159: aload 4
    //   161: ifnull +8 -> 169
    //   164: aload 4
    //   166: invokevirtual 512	java/io/InputStreamReader:close	()V
    //   169: aload_0
    //   170: athrow
    //   171: astore_2
    //   172: aload_2
    //   173: invokevirtual 82	java/lang/Exception:printStackTrace	()V
    //   176: goto -7 -> 169
    //   179: astore_0
    //   180: aload_0
    //   181: invokevirtual 82	java/lang/Exception:printStackTrace	()V
    //   184: aload_2
    //   185: areturn
    //   186: astore_2
    //   187: aload 8
    //   189: astore_3
    //   190: aload_0
    //   191: astore 4
    //   193: aload_2
    //   194: astore_0
    //   195: goto -44 -> 151
    //   198: astore_2
    //   199: aload_0
    //   200: astore 4
    //   202: aload_2
    //   203: astore_0
    //   204: goto -53 -> 151
    //   207: astore_0
    //   208: aload 5
    //   210: astore_2
    //   211: aload_0
    //   212: astore 5
    //   214: aload 7
    //   216: astore_0
    //   217: goto -105 -> 112
    //   220: astore_3
    //   221: aload 5
    //   223: astore_2
    //   224: aload_3
    //   225: astore 5
    //   227: goto -115 -> 112
    //
    // Exception table:
    //   from	to	target	type
    //   42	51	108	java/lang/Exception
    //   51	57	108	java/lang/Exception
    //   62	79	108	java/lang/Exception
    //   97	105	108	java/lang/Exception
    //   126	130	143	java/lang/Exception
    //   137	141	143	java/lang/Exception
    //   17	33	150	finally
    //   117	122	150	finally
    //   155	159	171	java/lang/Exception
    //   164	169	171	java/lang/Exception
    //   83	87	179	java/lang/Exception
    //   91	95	179	java/lang/Exception
    //   33	42	186	finally
    //   42	51	198	finally
    //   51	57	198	finally
    //   62	79	198	finally
    //   97	105	198	finally
    //   17	33	207	java/lang/Exception
    //   33	42	220	java/lang/Exception
  }

  // ERROR //
  public static void writeByteArrayToSD(String paramString, byte[] paramArrayOfByte, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore 5
    //   6: aload 6
    //   8: astore 4
    //   10: new 43	java/io/File
    //   13: dup
    //   14: aload_0
    //   15: invokespecial 72	java/io/File:<init>	(Ljava/lang/String;)V
    //   18: astore 8
    //   20: aload 6
    //   22: astore 4
    //   24: invokestatic 41	com/ismartgo/app/andbase/util/AbFileUtil:isCanUseSD	()Z
    //   27: istore_3
    //   28: iload_3
    //   29: ifne +16 -> 45
    //   32: iconst_0
    //   33: ifeq +11 -> 44
    //   36: new 86	java/lang/NullPointerException
    //   39: dup
    //   40: invokespecial 87	java/lang/NullPointerException:<init>	()V
    //   43: athrow
    //   44: return
    //   45: aload 6
    //   47: astore 4
    //   49: aload 8
    //   51: invokevirtual 115	java/io/File:exists	()Z
    //   54: ifne +50 -> 104
    //   57: iload_2
    //   58: ifeq +73 -> 131
    //   61: aload 6
    //   63: astore 4
    //   65: aload 8
    //   67: invokevirtual 532	java/io/File:getParentFile	()Ljava/io/File;
    //   70: astore 7
    //   72: aload 6
    //   74: astore 4
    //   76: aload 7
    //   78: invokevirtual 115	java/io/File:exists	()Z
    //   81: ifne +23 -> 104
    //   84: aload 6
    //   86: astore 4
    //   88: aload 7
    //   90: invokevirtual 482	java/io/File:mkdirs	()Z
    //   93: pop
    //   94: aload 6
    //   96: astore 4
    //   98: aload 8
    //   100: invokevirtual 118	java/io/File:createNewFile	()Z
    //   103: pop
    //   104: aload 6
    //   106: astore 4
    //   108: new 124	java/io/FileOutputStream
    //   111: dup
    //   112: aload_0
    //   113: invokespecial 533	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   116: astore_0
    //   117: aload_0
    //   118: aload_1
    //   119: invokevirtual 535	java/io/FileOutputStream:write	([B)V
    //   122: aload_0
    //   123: ifnull +78 -> 201
    //   126: aload_0
    //   127: invokevirtual 137	java/io/FileOutputStream:close	()V
    //   130: return
    //   131: iconst_0
    //   132: ifeq -88 -> 44
    //   135: new 86	java/lang/NullPointerException
    //   138: dup
    //   139: invokespecial 87	java/lang/NullPointerException:<init>	()V
    //   142: athrow
    //   143: astore_0
    //   144: return
    //   145: astore_1
    //   146: aload 5
    //   148: astore_0
    //   149: aload_0
    //   150: astore 4
    //   152: aload_1
    //   153: invokevirtual 82	java/lang/Exception:printStackTrace	()V
    //   156: aload_0
    //   157: ifnull -113 -> 44
    //   160: aload_0
    //   161: invokevirtual 137	java/io/FileOutputStream:close	()V
    //   164: return
    //   165: astore_0
    //   166: return
    //   167: astore_0
    //   168: aload 4
    //   170: ifnull +8 -> 178
    //   173: aload 4
    //   175: invokevirtual 137	java/io/FileOutputStream:close	()V
    //   178: aload_0
    //   179: athrow
    //   180: astore_0
    //   181: return
    //   182: astore_0
    //   183: return
    //   184: astore_1
    //   185: goto -7 -> 178
    //   188: astore_1
    //   189: aload_0
    //   190: astore 4
    //   192: aload_1
    //   193: astore_0
    //   194: goto -26 -> 168
    //   197: astore_1
    //   198: goto -49 -> 149
    //   201: return
    //
    // Exception table:
    //   from	to	target	type
    //   135	143	143	java/lang/Exception
    //   10	20	145	java/lang/Exception
    //   24	28	145	java/lang/Exception
    //   49	57	145	java/lang/Exception
    //   65	72	145	java/lang/Exception
    //   76	84	145	java/lang/Exception
    //   88	94	145	java/lang/Exception
    //   98	104	145	java/lang/Exception
    //   108	117	145	java/lang/Exception
    //   160	164	165	java/lang/Exception
    //   10	20	167	finally
    //   24	28	167	finally
    //   49	57	167	finally
    //   65	72	167	finally
    //   76	84	167	finally
    //   88	94	167	finally
    //   98	104	167	finally
    //   108	117	167	finally
    //   152	156	167	finally
    //   126	130	180	java/lang/Exception
    //   36	44	182	java/lang/Exception
    //   173	178	184	java/lang/Exception
    //   117	122	188	finally
    //   117	122	197	java/lang/Exception
  }

  public static class FileLastModifSort
    implements Comparator<File>
  {
    public int compare(File paramFile1, File paramFile2)
    {
      if (paramFile1.lastModified() > paramFile2.lastModified())
        return 1;
      if (paramFile1.lastModified() == paramFile2.lastModified())
        return 0;
      return -1;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.andbase.util.AbFileUtil
 * JD-Core Version:    0.6.2
 */