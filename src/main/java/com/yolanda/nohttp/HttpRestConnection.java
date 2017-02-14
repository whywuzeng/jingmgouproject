package com.yolanda.nohttp;

import android.content.Context;

public final class HttpRestConnection extends BasicConnection
  implements BasicConnectionRest
{
  private static HttpRestConnection _INSTANCE;
  private final Context mContext;
  private final String userAgent;

  private HttpRestConnection(Context paramContext)
  {
    this.mContext = paramContext;
    this.userAgent = UserAgent.getUserAgent(paramContext);
  }

  public static BasicConnectionRest getInstance(Context paramContext)
  {
    try
    {
      if (_INSTANCE == null)
        _INSTANCE = new HttpRestConnection(paramContext.getApplicationContext());
      return _INSTANCE;
    }
    finally
    {
    }
    throw paramContext;
  }

  protected String getUserAgent()
  {
    return this.userAgent;
  }

  // ERROR //
  public <T> Response<T> request(Request<T> paramRequest)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +13 -> 14
    //   4: new 49	java/lang/IllegalArgumentException
    //   7: dup
    //   8: ldc 51
    //   10: invokespecial 54	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   13: athrow
    //   14: aload_1
    //   15: invokeinterface 60 1 0
    //   20: checkcast 62	com/yolanda/nohttp/AnalyzeRequest
    //   23: astore 15
    //   25: aload 15
    //   27: ifnonnull +25 -> 52
    //   30: aload_1
    //   31: invokeinterface 60 1 0
    //   36: iconst_0
    //   37: invokeinterface 68 2 0
    //   42: new 49	java/lang/IllegalArgumentException
    //   45: dup
    //   46: ldc 70
    //   48: invokespecial 54	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   51: athrow
    //   52: ldc 72
    //   54: invokestatic 77	com/yolanda/nohttp/Logger:d	(Ljava/lang/String;)V
    //   57: aload 15
    //   59: invokeinterface 80 1 0
    //   64: astore 13
    //   66: aload 15
    //   68: invokeinterface 84 1 0
    //   73: astore 14
    //   75: iconst_0
    //   76: istore 4
    //   78: iconst_m1
    //   79: istore_3
    //   80: aconst_null
    //   81: astore 11
    //   83: aconst_null
    //   84: astore 7
    //   86: aconst_null
    //   87: astore 9
    //   89: aconst_null
    //   90: astore 12
    //   92: aload 15
    //   94: invokeinterface 80 1 0
    //   99: invokestatic 90	android/webkit/URLUtil:isValidUrl	(Ljava/lang/String;)Z
    //   102: ifne +75 -> 177
    //   105: ldc 92
    //   107: invokevirtual 98	java/lang/String:getBytes	()[B
    //   110: astore 6
    //   112: iload_3
    //   113: istore_2
    //   114: aload 12
    //   116: astore 8
    //   118: iload 4
    //   120: ifeq +31 -> 151
    //   123: aload 12
    //   125: astore 8
    //   127: aload 6
    //   129: ifnull +22 -> 151
    //   132: aload_1
    //   133: aload 13
    //   135: aload 7
    //   137: ldc 100
    //   139: invokevirtual 106	com/yolanda/nohttp/Headers:get	(Ljava/lang/String;)Ljava/lang/String;
    //   142: aload 6
    //   144: invokeinterface 110 4 0
    //   149: astore 8
    //   151: ldc 112
    //   153: invokestatic 77	com/yolanda/nohttp/Logger:d	(Ljava/lang/String;)V
    //   156: new 114	com/yolanda/nohttp/RestResponser
    //   159: dup
    //   160: aload 13
    //   162: iload 4
    //   164: iload_2
    //   165: aload 7
    //   167: aload 6
    //   169: aload 14
    //   171: aload 8
    //   173: invokespecial 117	com/yolanda/nohttp/RestResponser:<init>	(Ljava/lang/String;ZILcom/yolanda/nohttp/Headers;[BLjava/lang/Object;Ljava/lang/Object;)V
    //   176: areturn
    //   177: aload_0
    //   178: getfield 19	com/yolanda/nohttp/HttpRestConnection:mContext	Landroid/content/Context;
    //   181: invokestatic 123	com/yolanda/nohttp/tools/NetUtil:isNetworkAvailable	(Landroid/content/Context;)Z
    //   184: ifne +15 -> 199
    //   187: ldc 125
    //   189: invokevirtual 98	java/lang/String:getBytes	()[B
    //   192: astore 6
    //   194: iload_3
    //   195: istore_2
    //   196: goto -82 -> 114
    //   199: aconst_null
    //   200: astore 8
    //   202: aconst_null
    //   203: astore 7
    //   205: iload_3
    //   206: istore_2
    //   207: aload 11
    //   209: astore 6
    //   211: aload_0
    //   212: aload 15
    //   214: invokevirtual 129	com/yolanda/nohttp/HttpRestConnection:getHttpConnection	(Lcom/yolanda/nohttp/BasicAnalyzeRequest;)Ljava/net/HttpURLConnection;
    //   217: astore 10
    //   219: iload_3
    //   220: istore_2
    //   221: aload 11
    //   223: astore 6
    //   225: aload 10
    //   227: astore 7
    //   229: aload 10
    //   231: astore 8
    //   233: aload 10
    //   235: invokevirtual 134	java/net/HttpURLConnection:connect	()V
    //   238: iload_3
    //   239: istore_2
    //   240: aload 11
    //   242: astore 6
    //   244: aload 10
    //   246: astore 7
    //   248: aload 10
    //   250: astore 8
    //   252: aload_0
    //   253: aload 10
    //   255: aload 15
    //   257: invokevirtual 138	com/yolanda/nohttp/HttpRestConnection:sendRequestParam	(Ljava/net/HttpURLConnection;Lcom/yolanda/nohttp/AnalyzeRequest;)V
    //   260: iload_3
    //   261: istore_2
    //   262: aload 11
    //   264: astore 6
    //   266: aload 10
    //   268: astore 7
    //   270: aload 10
    //   272: astore 8
    //   274: ldc 140
    //   276: invokestatic 143	com/yolanda/nohttp/Logger:i	(Ljava/lang/String;)V
    //   279: iload_3
    //   280: istore_2
    //   281: aload 11
    //   283: astore 6
    //   285: aload 10
    //   287: astore 7
    //   289: aload 10
    //   291: astore 8
    //   293: aload 10
    //   295: invokevirtual 147	java/net/HttpURLConnection:getResponseCode	()I
    //   298: istore_3
    //   299: iload_3
    //   300: istore_2
    //   301: aload 11
    //   303: astore 6
    //   305: aload 10
    //   307: astore 7
    //   309: aload 10
    //   311: astore 8
    //   313: new 149	java/lang/StringBuilder
    //   316: dup
    //   317: ldc 151
    //   319: invokespecial 152	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   322: iload_3
    //   323: invokevirtual 156	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   326: invokevirtual 159	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   329: invokestatic 77	com/yolanda/nohttp/Logger:d	(Ljava/lang/String;)V
    //   332: iload_3
    //   333: istore_2
    //   334: aload 11
    //   336: astore 6
    //   338: aload 10
    //   340: astore 7
    //   342: aload 10
    //   344: astore 8
    //   346: aload 10
    //   348: invokevirtual 163	java/net/HttpURLConnection:getHeaderFields	()Ljava/util/Map;
    //   351: astore 16
    //   353: iload_3
    //   354: istore_2
    //   355: aload 11
    //   357: astore 6
    //   359: aload 10
    //   361: astore 7
    //   363: aload 10
    //   365: astore 8
    //   367: aload 16
    //   369: invokestatic 167	com/yolanda/nohttp/Headers:parseMultimap	(Ljava/util/Map;)Lcom/yolanda/nohttp/Headers;
    //   372: astore 11
    //   374: iload_3
    //   375: istore_2
    //   376: aload 11
    //   378: astore 6
    //   380: aload 10
    //   382: astore 7
    //   384: aload 10
    //   386: astore 8
    //   388: aload 16
    //   390: invokeinterface 173 1 0
    //   395: invokeinterface 179 1 0
    //   400: astore 17
    //   402: iload_3
    //   403: istore_2
    //   404: aload 11
    //   406: astore 6
    //   408: aload 10
    //   410: astore 7
    //   412: aload 10
    //   414: astore 8
    //   416: aload 17
    //   418: invokeinterface 185 1 0
    //   423: ifne +216 -> 639
    //   426: iload_3
    //   427: istore_2
    //   428: aload 11
    //   430: astore 6
    //   432: aload 10
    //   434: astore 7
    //   436: aload 10
    //   438: astore 8
    //   440: invokestatic 191	com/yolanda/nohttp/NoHttp:getDefaultCookieManager	()Ljava/net/CookieManager;
    //   443: new 193	java/net/URI
    //   446: dup
    //   447: aload 15
    //   449: invokeinterface 80 1 0
    //   454: invokespecial 194	java/net/URI:<init>	(Ljava/lang/String;)V
    //   457: aload 16
    //   459: invokevirtual 200	java/net/CookieManager:put	(Ljava/net/URI;Ljava/util/Map;)V
    //   462: iconst_1
    //   463: istore 4
    //   465: iconst_1
    //   466: istore 5
    //   468: iload_3
    //   469: istore_2
    //   470: aload 11
    //   472: astore 6
    //   474: aload 10
    //   476: astore 7
    //   478: aload 10
    //   480: astore 8
    //   482: aload 15
    //   484: invokeinterface 203 1 0
    //   489: iload_3
    //   490: invokestatic 207	com/yolanda/nohttp/HttpRestConnection:hasResponseBody	(II)Z
    //   493: ifeq +118 -> 611
    //   496: iload_3
    //   497: istore_2
    //   498: aload 11
    //   500: astore 6
    //   502: aload 10
    //   504: astore 7
    //   506: aload 10
    //   508: astore 8
    //   510: aload 10
    //   512: invokevirtual 210	java/net/HttpURLConnection:getContentEncoding	()Ljava/lang/String;
    //   515: astore 15
    //   517: iload_3
    //   518: istore_2
    //   519: aload 11
    //   521: astore 6
    //   523: aload 10
    //   525: astore 7
    //   527: aload 10
    //   529: astore 8
    //   531: aload 10
    //   533: invokevirtual 214	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   536: astore 9
    //   538: iload 5
    //   540: istore 4
    //   542: iload_3
    //   543: istore_2
    //   544: aload 11
    //   546: astore 6
    //   548: aload 10
    //   550: astore 7
    //   552: aload 10
    //   554: astore 8
    //   556: aload 15
    //   558: invokestatic 219	com/yolanda/nohttp/HeaderParser:isGzipContent	(Ljava/lang/String;)Z
    //   561: ifeq +464 -> 1025
    //   564: iload_3
    //   565: istore_2
    //   566: aload 11
    //   568: astore 6
    //   570: aload 10
    //   572: astore 7
    //   574: aload 10
    //   576: astore 8
    //   578: new 221	java/util/zip/GZIPInputStream
    //   581: dup
    //   582: aload 9
    //   584: invokespecial 224	java/util/zip/GZIPInputStream:<init>	(Ljava/io/InputStream;)V
    //   587: astore 9
    //   589: iload_3
    //   590: istore_2
    //   591: aload 11
    //   593: astore 6
    //   595: aload 10
    //   597: astore 7
    //   599: aload 10
    //   601: astore 8
    //   603: aload_0
    //   604: aload 9
    //   606: invokevirtual 228	com/yolanda/nohttp/HttpRestConnection:readResponseBody	(Ljava/io/InputStream;)[B
    //   609: astore 9
    //   611: aload 10
    //   613: ifnull +8 -> 621
    //   616: aload 10
    //   618: invokevirtual 231	java/net/HttpURLConnection:disconnect	()V
    //   621: ldc 233
    //   623: invokestatic 143	com/yolanda/nohttp/Logger:i	(Ljava/lang/String;)V
    //   626: iload_3
    //   627: istore_2
    //   628: aload 11
    //   630: astore 7
    //   632: aload 9
    //   634: astore 6
    //   636: goto -522 -> 114
    //   639: iload_3
    //   640: istore_2
    //   641: aload 11
    //   643: astore 6
    //   645: aload 10
    //   647: astore 7
    //   649: aload 10
    //   651: astore 8
    //   653: aload 17
    //   655: invokeinterface 236 1 0
    //   660: checkcast 94	java/lang/String
    //   663: astore 18
    //   665: iload_3
    //   666: istore_2
    //   667: aload 11
    //   669: astore 6
    //   671: aload 10
    //   673: astore 7
    //   675: aload 10
    //   677: astore 8
    //   679: aload 16
    //   681: aload 18
    //   683: invokeinterface 239 2 0
    //   688: checkcast 241	java/util/List
    //   691: invokeinterface 242 1 0
    //   696: astore 19
    //   698: iload_3
    //   699: istore_2
    //   700: aload 11
    //   702: astore 6
    //   704: aload 10
    //   706: astore 7
    //   708: aload 10
    //   710: astore 8
    //   712: aload 19
    //   714: invokeinterface 185 1 0
    //   719: ifeq -317 -> 402
    //   722: iload_3
    //   723: istore_2
    //   724: aload 11
    //   726: astore 6
    //   728: aload 10
    //   730: astore 7
    //   732: aload 10
    //   734: astore 8
    //   736: aload 19
    //   738: invokeinterface 236 1 0
    //   743: checkcast 94	java/lang/String
    //   746: astore 20
    //   748: iload_3
    //   749: istore_2
    //   750: aload 11
    //   752: astore 6
    //   754: aload 10
    //   756: astore 7
    //   758: aload 10
    //   760: astore 8
    //   762: new 244	java/lang/StringBuffer
    //   765: dup
    //   766: invokespecial 245	java/lang/StringBuffer:<init>	()V
    //   769: astore 21
    //   771: iload_3
    //   772: istore_2
    //   773: aload 11
    //   775: astore 6
    //   777: aload 10
    //   779: astore 7
    //   781: aload 10
    //   783: astore 8
    //   785: aload 18
    //   787: invokestatic 251	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   790: ifne +48 -> 838
    //   793: iload_3
    //   794: istore_2
    //   795: aload 11
    //   797: astore 6
    //   799: aload 10
    //   801: astore 7
    //   803: aload 10
    //   805: astore 8
    //   807: aload 21
    //   809: aload 18
    //   811: invokevirtual 254	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   814: pop
    //   815: iload_3
    //   816: istore_2
    //   817: aload 11
    //   819: astore 6
    //   821: aload 10
    //   823: astore 7
    //   825: aload 10
    //   827: astore 8
    //   829: aload 21
    //   831: ldc_w 256
    //   834: invokevirtual 254	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   837: pop
    //   838: iload_3
    //   839: istore_2
    //   840: aload 11
    //   842: astore 6
    //   844: aload 10
    //   846: astore 7
    //   848: aload 10
    //   850: astore 8
    //   852: aload 20
    //   854: invokestatic 251	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   857: ifne +25 -> 882
    //   860: iload_3
    //   861: istore_2
    //   862: aload 11
    //   864: astore 6
    //   866: aload 10
    //   868: astore 7
    //   870: aload 10
    //   872: astore 8
    //   874: aload 21
    //   876: aload 20
    //   878: invokevirtual 254	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   881: pop
    //   882: iload_3
    //   883: istore_2
    //   884: aload 11
    //   886: astore 6
    //   888: aload 10
    //   890: astore 7
    //   892: aload 10
    //   894: astore 8
    //   896: aload 21
    //   898: invokevirtual 257	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   901: invokestatic 77	com/yolanda/nohttp/Logger:d	(Ljava/lang/String;)V
    //   904: goto -206 -> 698
    //   907: astore 10
    //   909: aload 7
    //   911: astore 8
    //   913: aload_1
    //   914: invokeinterface 60 1 0
    //   919: iconst_0
    //   920: invokeinterface 68 2 0
    //   925: iconst_0
    //   926: istore 4
    //   928: aload 7
    //   930: astore 8
    //   932: aload_0
    //   933: aload 10
    //   935: invokevirtual 261	com/yolanda/nohttp/HttpRestConnection:getExcetionMessage	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   938: invokevirtual 98	java/lang/String:getBytes	()[B
    //   941: astore 9
    //   943: aload 7
    //   945: astore 8
    //   947: aload 10
    //   949: invokestatic 265	com/yolanda/nohttp/Logger:e	(Ljava/lang/Throwable;)V
    //   952: aload 7
    //   954: ifnull +8 -> 962
    //   957: aload 7
    //   959: invokevirtual 231	java/net/HttpURLConnection:disconnect	()V
    //   962: ldc 233
    //   964: invokestatic 143	com/yolanda/nohttp/Logger:i	(Ljava/lang/String;)V
    //   967: aload 6
    //   969: astore 7
    //   971: aload 9
    //   973: astore 6
    //   975: goto -861 -> 114
    //   978: astore 6
    //   980: iconst_0
    //   981: istore 4
    //   983: iload_3
    //   984: istore_2
    //   985: aload 11
    //   987: astore 6
    //   989: aload 10
    //   991: astore 7
    //   993: aload 10
    //   995: astore 8
    //   997: aload 10
    //   999: invokevirtual 268	java/net/HttpURLConnection:getErrorStream	()Ljava/io/InputStream;
    //   1002: astore 9
    //   1004: goto -462 -> 542
    //   1007: astore_1
    //   1008: aload 8
    //   1010: ifnull +8 -> 1018
    //   1013: aload 8
    //   1015: invokevirtual 231	java/net/HttpURLConnection:disconnect	()V
    //   1018: ldc 233
    //   1020: invokestatic 143	com/yolanda/nohttp/Logger:i	(Ljava/lang/String;)V
    //   1023: aload_1
    //   1024: athrow
    //   1025: goto -436 -> 589
    //
    // Exception table:
    //   from	to	target	type
    //   211	219	907	java/lang/Exception
    //   233	238	907	java/lang/Exception
    //   252	260	907	java/lang/Exception
    //   274	279	907	java/lang/Exception
    //   293	299	907	java/lang/Exception
    //   313	332	907	java/lang/Exception
    //   346	353	907	java/lang/Exception
    //   367	374	907	java/lang/Exception
    //   388	402	907	java/lang/Exception
    //   416	426	907	java/lang/Exception
    //   440	462	907	java/lang/Exception
    //   482	496	907	java/lang/Exception
    //   510	517	907	java/lang/Exception
    //   531	538	907	java/lang/Exception
    //   556	564	907	java/lang/Exception
    //   578	589	907	java/lang/Exception
    //   603	611	907	java/lang/Exception
    //   653	665	907	java/lang/Exception
    //   679	698	907	java/lang/Exception
    //   712	722	907	java/lang/Exception
    //   736	748	907	java/lang/Exception
    //   762	771	907	java/lang/Exception
    //   785	793	907	java/lang/Exception
    //   807	815	907	java/lang/Exception
    //   829	838	907	java/lang/Exception
    //   852	860	907	java/lang/Exception
    //   874	882	907	java/lang/Exception
    //   896	904	907	java/lang/Exception
    //   997	1004	907	java/lang/Exception
    //   531	538	978	java/io/IOException
    //   211	219	1007	finally
    //   233	238	1007	finally
    //   252	260	1007	finally
    //   274	279	1007	finally
    //   293	299	1007	finally
    //   313	332	1007	finally
    //   346	353	1007	finally
    //   367	374	1007	finally
    //   388	402	1007	finally
    //   416	426	1007	finally
    //   440	462	1007	finally
    //   482	496	1007	finally
    //   510	517	1007	finally
    //   531	538	1007	finally
    //   556	564	1007	finally
    //   578	589	1007	finally
    //   603	611	1007	finally
    //   653	665	1007	finally
    //   679	698	1007	finally
    //   712	722	1007	finally
    //   736	748	1007	finally
    //   762	771	1007	finally
    //   785	793	1007	finally
    //   807	815	1007	finally
    //   829	838	1007	finally
    //   852	860	1007	finally
    //   874	882	1007	finally
    //   896	904	1007	finally
    //   913	925	1007	finally
    //   932	943	1007	finally
    //   947	952	1007	finally
    //   997	1004	1007	finally
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.HttpRestConnection
 * JD-Core Version:    0.6.2
 */