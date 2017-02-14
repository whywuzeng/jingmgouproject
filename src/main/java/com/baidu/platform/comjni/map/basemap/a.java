package com.baidu.platform.comjni.map.basemap;

import android.os.Bundle;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.VersionInfo;
import com.baidu.mapapi.common.BaiduMapSDKException;
import com.baidu.platform.comapi.map.K;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class a
{
  private static final String a = a.class.getSimpleName();
  private static List<JNIBaseMap> d = new ArrayList();
  private long b = 0L;
  private JNIBaseMap c = null;

  static
  {
    try
    {
      if (!VersionInfo.getApiVersion().equals(K.a()))
        throw new BaiduMapSDKException("the version of map is not match with base");
    }
    catch (Error localError)
    {
      System.out.println(K.b() + " so Failed to load.");
      return;
    }
    a(BMapManager.getContext());
    System.loadLibrary(K.b());
  }

  public static int a(long paramLong, int paramInt1, int paramInt2, int paramInt3)
  {
    return JNIBaseMap.MapProc(paramLong, paramInt1, paramInt2, paramInt3);
  }

  // ERROR //
  private static void a(android.content.Context paramContext)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_3
    //   2: iconst_0
    //   3: istore_2
    //   4: aload_0
    //   5: ifnonnull +4 -> 9
    //   8: return
    //   9: new 108	java/io/File
    //   12: dup
    //   13: invokestatic 113	com/baidu/mapapi/common/SysOSUtil:getModuleFileName	()Ljava/lang/String;
    //   16: invokespecial 114	java/io/File:<init>	(Ljava/lang/String;)V
    //   19: astore 4
    //   21: aload 4
    //   23: invokevirtual 118	java/io/File:exists	()Z
    //   26: ifne +9 -> 35
    //   29: aload 4
    //   31: invokevirtual 121	java/io/File:mkdirs	()Z
    //   34: pop
    //   35: aload_0
    //   36: invokevirtual 127	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   39: astore_0
    //   40: bipush 12
    //   42: anewarray 43	java/lang/String
    //   45: astore 4
    //   47: aload 4
    //   49: iconst_0
    //   50: ldc 129
    //   52: aastore
    //   53: aload 4
    //   55: iconst_1
    //   56: ldc 131
    //   58: aastore
    //   59: aload 4
    //   61: iconst_2
    //   62: ldc 133
    //   64: aastore
    //   65: aload 4
    //   67: iconst_3
    //   68: ldc 135
    //   70: aastore
    //   71: aload 4
    //   73: iconst_4
    //   74: ldc 137
    //   76: aastore
    //   77: aload 4
    //   79: iconst_5
    //   80: ldc 139
    //   82: aastore
    //   83: aload 4
    //   85: bipush 6
    //   87: ldc 141
    //   89: aastore
    //   90: aload 4
    //   92: bipush 7
    //   94: ldc 143
    //   96: aastore
    //   97: aload 4
    //   99: bipush 8
    //   101: ldc 145
    //   103: aastore
    //   104: aload 4
    //   106: bipush 9
    //   108: ldc 147
    //   110: aastore
    //   111: aload 4
    //   113: bipush 10
    //   115: ldc 149
    //   117: aastore
    //   118: aload 4
    //   120: bipush 11
    //   122: ldc 151
    //   124: aastore
    //   125: new 108	java/io/File
    //   128: dup
    //   129: new 62	java/lang/StringBuilder
    //   132: dup
    //   133: invokespecial 63	java/lang/StringBuilder:<init>	()V
    //   136: invokestatic 113	com/baidu/mapapi/common/SysOSUtil:getModuleFileName	()Ljava/lang/String;
    //   139: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: ldc 153
    //   144: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   147: invokevirtual 74	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   150: invokespecial 114	java/io/File:<init>	(Ljava/lang/String;)V
    //   153: astore 6
    //   155: bipush 6
    //   157: newarray byte
    //   159: astore 5
    //   161: aload 5
    //   163: dup
    //   164: iconst_0
    //   165: ldc 154
    //   167: bastore
    //   168: dup
    //   169: iconst_1
    //   170: ldc 155
    //   172: bastore
    //   173: dup
    //   174: iconst_2
    //   175: ldc 156
    //   177: bastore
    //   178: dup
    //   179: iconst_3
    //   180: ldc 156
    //   182: bastore
    //   183: dup
    //   184: iconst_4
    //   185: ldc 156
    //   187: bastore
    //   188: dup
    //   189: iconst_5
    //   190: ldc 156
    //   192: bastore
    //   193: pop
    //   194: iload_3
    //   195: istore_1
    //   196: aload 6
    //   198: invokevirtual 118	java/io/File:exists	()Z
    //   201: ifeq +102 -> 303
    //   204: new 158	java/io/FileInputStream
    //   207: dup
    //   208: aload 6
    //   210: invokespecial 161	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   213: astore 7
    //   215: aload 7
    //   217: invokevirtual 165	java/io/FileInputStream:available	()I
    //   220: newarray byte
    //   222: astore 8
    //   224: aload 7
    //   226: aload 8
    //   228: invokevirtual 169	java/io/FileInputStream:read	([B)I
    //   231: pop
    //   232: aload 7
    //   234: invokevirtual 172	java/io/FileInputStream:close	()V
    //   237: iload_3
    //   238: istore_1
    //   239: aload 8
    //   241: aload 5
    //   243: invokestatic 177	java/util/Arrays:equals	([B[B)Z
    //   246: ifeq +57 -> 303
    //   249: new 108	java/io/File
    //   252: dup
    //   253: new 62	java/lang/StringBuilder
    //   256: dup
    //   257: invokespecial 63	java/lang/StringBuilder:<init>	()V
    //   260: invokestatic 113	com/baidu/mapapi/common/SysOSUtil:getModuleFileName	()Ljava/lang/String;
    //   263: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   266: ldc 179
    //   268: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   271: invokevirtual 74	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   274: invokespecial 114	java/io/File:<init>	(Ljava/lang/String;)V
    //   277: astore 7
    //   279: iload_3
    //   280: istore_1
    //   281: aload 7
    //   283: invokevirtual 118	java/io/File:exists	()Z
    //   286: ifeq +17 -> 303
    //   289: iload_3
    //   290: istore_1
    //   291: aload 7
    //   293: invokevirtual 183	java/io/File:length	()J
    //   296: lconst_0
    //   297: lcmp
    //   298: ifle +5 -> 303
    //   301: iconst_0
    //   302: istore_1
    //   303: iload_1
    //   304: ifeq +178 -> 482
    //   307: aload 6
    //   309: invokevirtual 118	java/io/File:exists	()Z
    //   312: ifeq +9 -> 321
    //   315: aload 6
    //   317: invokevirtual 186	java/io/File:delete	()Z
    //   320: pop
    //   321: aload 6
    //   323: invokevirtual 189	java/io/File:createNewFile	()Z
    //   326: pop
    //   327: new 191	java/io/FileOutputStream
    //   330: dup
    //   331: aload 6
    //   333: invokespecial 192	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   336: astore 6
    //   338: aload 6
    //   340: aload 5
    //   342: invokevirtual 196	java/io/FileOutputStream:write	([B)V
    //   345: aload 6
    //   347: invokevirtual 197	java/io/FileOutputStream:close	()V
    //   350: new 108	java/io/File
    //   353: dup
    //   354: new 62	java/lang/StringBuilder
    //   357: dup
    //   358: invokespecial 63	java/lang/StringBuilder:<init>	()V
    //   361: invokestatic 113	com/baidu/mapapi/common/SysOSUtil:getModuleFileName	()Ljava/lang/String;
    //   364: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   367: ldc 199
    //   369: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   372: invokevirtual 74	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   375: invokespecial 114	java/io/File:<init>	(Ljava/lang/String;)V
    //   378: astore 5
    //   380: aload 5
    //   382: invokevirtual 118	java/io/File:exists	()Z
    //   385: ifne +9 -> 394
    //   388: aload 5
    //   390: invokevirtual 121	java/io/File:mkdirs	()Z
    //   393: pop
    //   394: new 108	java/io/File
    //   397: dup
    //   398: new 62	java/lang/StringBuilder
    //   401: dup
    //   402: invokespecial 63	java/lang/StringBuilder:<init>	()V
    //   405: invokestatic 113	com/baidu/mapapi/common/SysOSUtil:getModuleFileName	()Ljava/lang/String;
    //   408: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   411: ldc 201
    //   413: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   416: invokevirtual 74	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   419: invokespecial 114	java/io/File:<init>	(Ljava/lang/String;)V
    //   422: astore 5
    //   424: aload 5
    //   426: invokevirtual 118	java/io/File:exists	()Z
    //   429: ifne +9 -> 438
    //   432: aload 5
    //   434: invokevirtual 121	java/io/File:mkdirs	()Z
    //   437: pop
    //   438: new 108	java/io/File
    //   441: dup
    //   442: new 62	java/lang/StringBuilder
    //   445: dup
    //   446: invokespecial 63	java/lang/StringBuilder:<init>	()V
    //   449: invokestatic 113	com/baidu/mapapi/common/SysOSUtil:getModuleFileName	()Ljava/lang/String;
    //   452: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   455: ldc 203
    //   457: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   460: invokevirtual 74	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   463: invokespecial 114	java/io/File:<init>	(Ljava/lang/String;)V
    //   466: astore 5
    //   468: aload 5
    //   470: invokevirtual 118	java/io/File:exists	()Z
    //   473: ifne +9 -> 482
    //   476: aload 5
    //   478: invokevirtual 121	java/io/File:mkdirs	()Z
    //   481: pop
    //   482: iload_1
    //   483: ifeq -475 -> 8
    //   486: iload_2
    //   487: istore_1
    //   488: iload_1
    //   489: aload 4
    //   491: arraylength
    //   492: if_icmpge -484 -> 8
    //   495: aload_0
    //   496: bipush 12
    //   498: anewarray 43	java/lang/String
    //   501: dup
    //   502: iconst_0
    //   503: ldc 205
    //   505: aastore
    //   506: dup
    //   507: iconst_1
    //   508: ldc 131
    //   510: aastore
    //   511: dup
    //   512: iconst_2
    //   513: ldc 133
    //   515: aastore
    //   516: dup
    //   517: iconst_3
    //   518: ldc 135
    //   520: aastore
    //   521: dup
    //   522: iconst_4
    //   523: ldc 137
    //   525: aastore
    //   526: dup
    //   527: iconst_5
    //   528: ldc 139
    //   530: aastore
    //   531: dup
    //   532: bipush 6
    //   534: ldc 141
    //   536: aastore
    //   537: dup
    //   538: bipush 7
    //   540: ldc 143
    //   542: aastore
    //   543: dup
    //   544: bipush 8
    //   546: ldc 145
    //   548: aastore
    //   549: dup
    //   550: bipush 9
    //   552: ldc 147
    //   554: aastore
    //   555: dup
    //   556: bipush 10
    //   558: ldc 149
    //   560: aastore
    //   561: dup
    //   562: bipush 11
    //   564: ldc 151
    //   566: aastore
    //   567: iload_1
    //   568: aaload
    //   569: invokevirtual 211	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   572: astore 6
    //   574: aload 6
    //   576: invokevirtual 214	java/io/InputStream:available	()I
    //   579: newarray byte
    //   581: astore 5
    //   583: aload 6
    //   585: aload 5
    //   587: invokevirtual 215	java/io/InputStream:read	([B)I
    //   590: pop
    //   591: aload 6
    //   593: invokevirtual 216	java/io/InputStream:close	()V
    //   596: new 108	java/io/File
    //   599: dup
    //   600: new 62	java/lang/StringBuilder
    //   603: dup
    //   604: invokespecial 63	java/lang/StringBuilder:<init>	()V
    //   607: invokestatic 113	com/baidu/mapapi/common/SysOSUtil:getModuleFileName	()Ljava/lang/String;
    //   610: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   613: ldc 218
    //   615: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   618: aload 4
    //   620: iload_1
    //   621: aaload
    //   622: invokevirtual 69	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   625: invokevirtual 74	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   628: invokespecial 114	java/io/File:<init>	(Ljava/lang/String;)V
    //   631: astore 6
    //   633: aload 6
    //   635: invokevirtual 118	java/io/File:exists	()Z
    //   638: ifeq +9 -> 647
    //   641: aload 6
    //   643: invokevirtual 186	java/io/File:delete	()Z
    //   646: pop
    //   647: aload 6
    //   649: invokevirtual 189	java/io/File:createNewFile	()Z
    //   652: pop
    //   653: new 191	java/io/FileOutputStream
    //   656: dup
    //   657: aload 6
    //   659: invokespecial 192	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   662: astore 6
    //   664: aload 6
    //   666: aload 5
    //   668: invokevirtual 196	java/io/FileOutputStream:write	([B)V
    //   671: aload 6
    //   673: invokevirtual 197	java/io/FileOutputStream:close	()V
    //   676: iload_1
    //   677: iconst_1
    //   678: iadd
    //   679: istore_1
    //   680: goto -192 -> 488
    //   683: astore_0
    //   684: aload_0
    //   685: invokevirtual 221	java/lang/Exception:printStackTrace	()V
    //   688: return
    //   689: astore_0
    //   690: aload_0
    //   691: invokevirtual 221	java/lang/Exception:printStackTrace	()V
    //   694: return
    //   695: astore 5
    //   697: aload 5
    //   699: invokevirtual 221	java/lang/Exception:printStackTrace	()V
    //   702: goto -26 -> 676
    //
    // Exception table:
    //   from	to	target	type
    //   9	35	683	java/lang/Exception
    //   125	194	689	java/lang/Exception
    //   196	237	689	java/lang/Exception
    //   239	279	689	java/lang/Exception
    //   281	289	689	java/lang/Exception
    //   291	301	689	java/lang/Exception
    //   307	321	689	java/lang/Exception
    //   321	394	689	java/lang/Exception
    //   394	438	689	java/lang/Exception
    //   438	482	689	java/lang/Exception
    //   495	647	695	java/lang/Exception
    //   647	676	695	java/lang/Exception
  }

  public int a(int paramInt)
  {
    return this.c.SetMapControlMode(this.b, paramInt);
  }

  public long a(int paramInt1, int paramInt2, String paramString)
  {
    return this.c.AddLayer(this.b, paramInt1, paramInt2, paramString);
  }

  public String a(int paramInt1, int paramInt2)
  {
    return this.c.ScrPtToGeoPoint(this.b, paramInt1, paramInt2);
  }

  public String a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return this.c.GetNearlyObjID(this.b, paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public String a(String paramString)
  {
    return this.c.OnSchcityGet(this.b, paramString);
  }

  public void a(long paramLong)
  {
    this.c.UpdateLayers(this.b, paramLong);
  }

  public void a(long paramLong, boolean paramBoolean)
  {
    this.c.ShowLayers(this.b, paramLong, paramBoolean);
  }

  public void a(Bundle paramBundle)
  {
    this.c.SetMapStatus(this.b, paramBundle);
  }

  public void a(String paramString, Bundle paramBundle)
  {
    this.c.SaveScreenToLocal(this.b, paramString, paramBundle);
  }

  public void a(boolean paramBoolean)
  {
    this.c.ShowSatelliteMap(this.b, paramBoolean);
  }

  public boolean a()
  {
    if (d.size() == 0);
    for (this.b = this.c.Create(); ; this.b = this.c.CreateDuplicate(((JNIBaseMap)d.get(0)).a))
    {
      this.c.a = this.b;
      d.add(this.c);
      this.c.SetCallback(this.b, null);
      return true;
    }
  }

  public boolean a(int paramInt, boolean paramBoolean)
  {
    return this.c.OnRecordReload(this.b, paramInt, paramBoolean);
  }

  public boolean a(int paramInt1, boolean paramBoolean, int paramInt2)
  {
    return this.c.OnRecordStart(this.b, paramInt1, paramBoolean, paramInt2);
  }

  public boolean a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
  {
    return this.c.Init(this.b, paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7);
  }

  public boolean a(boolean paramBoolean1, boolean paramBoolean2)
  {
    return this.c.OnRecordImport(this.b, paramBoolean1, paramBoolean2);
  }

  public int[] a(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    return this.c.GetScreenBuf(this.b, paramArrayOfInt, paramInt1, paramInt2);
  }

  public float b(Bundle paramBundle)
  {
    return this.c.GetZoomToBound(this.b, paramBundle);
  }

  public String b(int paramInt1, int paramInt2)
  {
    return this.c.GeoPtToScrPoint(this.b, paramInt1, paramInt2);
  }

  public void b(boolean paramBoolean)
  {
    this.c.ShowHotMap(this.b, paramBoolean);
  }

  public boolean b()
  {
    this.c.Release(this.b);
    d.remove(this.c);
    return true;
  }

  public boolean b(int paramInt)
  {
    return this.c.OnRecordAdd(this.b, paramInt);
  }

  public boolean b(int paramInt, boolean paramBoolean)
  {
    return this.c.OnRecordRemove(this.b, paramInt, paramBoolean);
  }

  public boolean b(int paramInt1, boolean paramBoolean, int paramInt2)
  {
    return this.c.OnRecordSuspend(this.b, paramInt1, paramBoolean, paramInt2);
  }

  public boolean b(long paramLong)
  {
    return this.c.cleanSDKTileDataCache(this.b, paramLong);
  }

  public long c()
  {
    return this.b;
  }

  public String c(int paramInt)
  {
    return this.c.OnRecordGetAt(this.b, paramInt);
  }

  public void c(long paramLong)
  {
    this.c.ClearLayer(this.b, paramLong);
  }

  public void c(boolean paramBoolean)
  {
    this.c.ShowTrafficMap(this.b, paramBoolean);
  }

  public boolean c(Bundle paramBundle)
  {
    return this.c.updateSDKTile(this.b, paramBundle);
  }

  public void d()
  {
    this.c.OnPause(this.b);
  }

  public void d(boolean paramBoolean)
  {
    this.c.enableDrawHouseHeight(this.b, paramBoolean);
  }

  public boolean d(Bundle paramBundle)
  {
    return this.c.addtileOverlay(this.b, paramBundle);
  }

  public void e()
  {
    this.c.OnResume(this.b);
  }

  public void e(Bundle paramBundle)
  {
    this.c.addOneOverlayItem(this.b, paramBundle);
  }

  public void f()
  {
    this.c.ResetImageRes(this.b);
  }

  public void f(Bundle paramBundle)
  {
    this.c.updateOneOverlayItem(this.b, paramBundle);
  }

  public Bundle g()
  {
    return this.c.GetMapStatus(this.b);
  }

  public void g(Bundle paramBundle)
  {
    this.c.removeOneOverlayItem(this.b, paramBundle);
  }

  public Bundle h()
  {
    return this.c.getDrawingMapStatus(this.b);
  }

  public boolean i()
  {
    return this.c.GetBaiduHotMapCityInfo(this.b);
  }

  public String j()
  {
    return this.c.OnRecordGetAll(this.b);
  }

  public String k()
  {
    return this.c.OnHotcityGet(this.b);
  }

  public void l()
  {
    this.c.PostStatInfo(this.b);
  }

  public boolean m()
  {
    return this.c.isDrawHouseHeightEnable(this.b);
  }

  public void n()
  {
    this.c.clearHeatMapLayerCache(this.b);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.platform.comjni.map.basemap.a
 * JD-Core Version:    0.6.2
 */