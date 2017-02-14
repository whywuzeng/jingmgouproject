package com.yolanda.nohttp.download;

import android.content.Context;
import com.yolanda.nohttp.BasicConnection;
import com.yolanda.nohttp.UserAgent;

public class DownloadConnection extends BasicConnection
  implements Downloader
{
  private static DownloadConnection _Downloader;
  private Context mContext;
  private final String userAgent;

  private DownloadConnection(Context paramContext)
  {
    this.mContext = paramContext;
    this.userAgent = UserAgent.getUserAgent(paramContext.getApplicationContext());
  }

  public static Downloader getInstance(Context paramContext)
  {
    if (_Downloader == null)
      _Downloader = new DownloadConnection(paramContext.getApplicationContext());
    return _Downloader;
  }

  // ERROR //
  public void download(int paramInt, DownloadRequest paramDownloadRequest, DownloadListener paramDownloadListener)
  {
    // Byte code:
    //   0: aload_2
    //   1: ifnonnull +13 -> 14
    //   4: new 52	java/lang/IllegalArgumentException
    //   7: dup
    //   8: ldc 54
    //   10: invokespecial 57	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   13: athrow
    //   14: aload_3
    //   15: ifnonnull +25 -> 40
    //   18: aload_2
    //   19: invokeinterface 63 1 0
    //   24: iconst_0
    //   25: invokeinterface 69 2 0
    //   30: new 52	java/lang/IllegalArgumentException
    //   33: dup
    //   34: ldc 71
    //   36: invokespecial 57	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   39: athrow
    //   40: aload_0
    //   41: getfield 19	com/yolanda/nohttp/download/DownloadConnection:mContext	Landroid/content/Context;
    //   44: invokestatic 77	com/yolanda/nohttp/tools/NetUtil:isNetworkAvailable	(Landroid/content/Context;)Z
    //   47: ifne +28 -> 75
    //   50: aload_2
    //   51: invokeinterface 63 1 0
    //   56: iconst_0
    //   57: invokeinterface 69 2 0
    //   62: aload_3
    //   63: iload_1
    //   64: getstatic 83	com/yolanda/nohttp/download/StatusCode:ERROR_NETWORK_NOT_AVAILABLE	Lcom/yolanda/nohttp/download/StatusCode;
    //   67: ldc 85
    //   69: invokeinterface 91 4 0
    //   74: return
    //   75: aload_2
    //   76: invokeinterface 63 1 0
    //   81: invokeinterface 95 1 0
    //   86: invokestatic 101	android/webkit/URLUtil:isValidUrl	(Ljava/lang/String;)Z
    //   89: ifne +28 -> 117
    //   92: aload_2
    //   93: invokeinterface 63 1 0
    //   98: iconst_0
    //   99: invokeinterface 69 2 0
    //   104: aload_3
    //   105: iload_1
    //   106: getstatic 104	com/yolanda/nohttp/download/StatusCode:ERROR_URL_SYNTAX_ERROR	Lcom/yolanda/nohttp/download/StatusCode;
    //   109: ldc 106
    //   111: invokeinterface 91 4 0
    //   116: return
    //   117: aconst_null
    //   118: astore 27
    //   120: aconst_null
    //   121: astore 28
    //   123: aconst_null
    //   124: astore 20
    //   126: aconst_null
    //   127: astore 26
    //   129: aconst_null
    //   130: astore 14
    //   132: aconst_null
    //   133: astore 25
    //   135: aconst_null
    //   136: astore 24
    //   138: aconst_null
    //   139: astore 16
    //   141: aload 26
    //   143: astore 23
    //   145: aload 16
    //   147: astore 17
    //   149: aload 27
    //   151: astore 21
    //   153: aload 14
    //   155: astore 19
    //   157: aload 28
    //   159: astore 22
    //   161: aload 25
    //   163: astore 18
    //   165: aload 20
    //   167: astore 15
    //   169: aload 24
    //   171: astore 13
    //   173: new 108	java/io/File
    //   176: dup
    //   177: aload_2
    //   178: invokeinterface 111 1 0
    //   183: invokespecial 112	java/io/File:<init>	(Ljava/lang/String;)V
    //   186: astore 29
    //   188: aload 26
    //   190: astore 23
    //   192: aload 16
    //   194: astore 17
    //   196: aload 27
    //   198: astore 21
    //   200: aload 14
    //   202: astore 19
    //   204: aload 28
    //   206: astore 22
    //   208: aload 25
    //   210: astore 18
    //   212: aload 20
    //   214: astore 15
    //   216: aload 24
    //   218: astore 13
    //   220: aload 29
    //   222: invokevirtual 116	java/io/File:exists	()Z
    //   225: ifne +41 -> 266
    //   228: aload 26
    //   230: astore 23
    //   232: aload 16
    //   234: astore 17
    //   236: aload 27
    //   238: astore 21
    //   240: aload 14
    //   242: astore 19
    //   244: aload 28
    //   246: astore 22
    //   248: aload 25
    //   250: astore 18
    //   252: aload 20
    //   254: astore 15
    //   256: aload 24
    //   258: astore 13
    //   260: aload 29
    //   262: invokevirtual 119	java/io/File:mkdirs	()Z
    //   265: pop
    //   266: aload 26
    //   268: astore 23
    //   270: aload 16
    //   272: astore 17
    //   274: aload 27
    //   276: astore 21
    //   278: aload 14
    //   280: astore 19
    //   282: aload 28
    //   284: astore 22
    //   286: aload 25
    //   288: astore 18
    //   290: aload 20
    //   292: astore 15
    //   294: aload 24
    //   296: astore 13
    //   298: new 108	java/io/File
    //   301: dup
    //   302: aload 29
    //   304: aload_2
    //   305: invokeinterface 122 1 0
    //   310: invokespecial 125	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   313: astore 30
    //   315: aload 26
    //   317: astore 23
    //   319: aload 16
    //   321: astore 17
    //   323: aload 27
    //   325: astore 21
    //   327: aload 14
    //   329: astore 19
    //   331: aload 28
    //   333: astore 22
    //   335: aload 25
    //   337: astore 18
    //   339: aload 20
    //   341: astore 15
    //   343: aload 24
    //   345: astore 13
    //   347: new 127	java/lang/StringBuilder
    //   350: dup
    //   351: ldc 129
    //   353: invokespecial 130	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   356: aload 30
    //   358: invokevirtual 133	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   361: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   364: invokevirtual 140	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   367: invokestatic 145	com/yolanda/nohttp/Logger:d	(Ljava/lang/String;)V
    //   370: aload 26
    //   372: astore 23
    //   374: aload 16
    //   376: astore 17
    //   378: aload 27
    //   380: astore 21
    //   382: aload 14
    //   384: astore 19
    //   386: aload 28
    //   388: astore 22
    //   390: aload 25
    //   392: astore 18
    //   394: aload 20
    //   396: astore 15
    //   398: aload 24
    //   400: astore 13
    //   402: aload 30
    //   404: invokevirtual 116	java/io/File:exists	()Z
    //   407: ifeq +82 -> 489
    //   410: aload 26
    //   412: astore 23
    //   414: aload 16
    //   416: astore 17
    //   418: aload 27
    //   420: astore 21
    //   422: aload 14
    //   424: astore 19
    //   426: aload 28
    //   428: astore 22
    //   430: aload 25
    //   432: astore 18
    //   434: aload 20
    //   436: astore 15
    //   438: aload 24
    //   440: astore 13
    //   442: aload_2
    //   443: invokeinterface 148 1 0
    //   448: ifeq +990 -> 1438
    //   451: aload 26
    //   453: astore 23
    //   455: aload 16
    //   457: astore 17
    //   459: aload 27
    //   461: astore 21
    //   463: aload 14
    //   465: astore 19
    //   467: aload 28
    //   469: astore 22
    //   471: aload 25
    //   473: astore 18
    //   475: aload 20
    //   477: astore 15
    //   479: aload 24
    //   481: astore 13
    //   483: aload 30
    //   485: invokevirtual 151	java/io/File:delete	()Z
    //   488: pop
    //   489: aload 26
    //   491: astore 23
    //   493: aload 16
    //   495: astore 17
    //   497: aload 27
    //   499: astore 21
    //   501: aload 14
    //   503: astore 19
    //   505: aload 28
    //   507: astore 22
    //   509: aload 25
    //   511: astore 18
    //   513: aload 20
    //   515: astore 15
    //   517: aload 24
    //   519: astore 13
    //   521: new 108	java/io/File
    //   524: dup
    //   525: aload_2
    //   526: invokeinterface 111 1 0
    //   531: new 127	java/lang/StringBuilder
    //   534: dup
    //   535: aload_2
    //   536: invokeinterface 122 1 0
    //   541: invokestatic 157	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   544: invokespecial 130	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   547: ldc 159
    //   549: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   552: invokevirtual 140	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   555: invokespecial 162	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   558: astore 29
    //   560: lconst_0
    //   561: lstore 8
    //   563: lload 8
    //   565: lstore 6
    //   567: aload 26
    //   569: astore 23
    //   571: aload 16
    //   573: astore 17
    //   575: aload 27
    //   577: astore 21
    //   579: aload 14
    //   581: astore 19
    //   583: aload 28
    //   585: astore 22
    //   587: aload 25
    //   589: astore 18
    //   591: aload 20
    //   593: astore 15
    //   595: aload 24
    //   597: astore 13
    //   599: aload 29
    //   601: invokevirtual 116	java/io/File:exists	()Z
    //   604: ifeq +83 -> 687
    //   607: aload 26
    //   609: astore 23
    //   611: aload 16
    //   613: astore 17
    //   615: aload 27
    //   617: astore 21
    //   619: aload 14
    //   621: astore 19
    //   623: aload 28
    //   625: astore 22
    //   627: aload 25
    //   629: astore 18
    //   631: aload 20
    //   633: astore 15
    //   635: aload 24
    //   637: astore 13
    //   639: aload_2
    //   640: invokeinterface 165 1 0
    //   645: ifeq +1051 -> 1696
    //   648: aload 26
    //   650: astore 23
    //   652: aload 16
    //   654: astore 17
    //   656: aload 27
    //   658: astore 21
    //   660: aload 14
    //   662: astore 19
    //   664: aload 28
    //   666: astore 22
    //   668: aload 25
    //   670: astore 18
    //   672: aload 20
    //   674: astore 15
    //   676: aload 24
    //   678: astore 13
    //   680: aload 29
    //   682: invokevirtual 169	java/io/File:length	()J
    //   685: lstore 6
    //   687: aload 26
    //   689: astore 23
    //   691: aload 16
    //   693: astore 17
    //   695: aload 27
    //   697: astore 21
    //   699: aload 14
    //   701: astore 19
    //   703: aload 28
    //   705: astore 22
    //   707: aload 25
    //   709: astore 18
    //   711: aload 20
    //   713: astore 15
    //   715: aload 24
    //   717: astore 13
    //   719: aload 29
    //   721: invokevirtual 116	java/io/File:exists	()Z
    //   724: ifne +121 -> 845
    //   727: aload 26
    //   729: astore 23
    //   731: aload 16
    //   733: astore 17
    //   735: aload 27
    //   737: astore 21
    //   739: aload 14
    //   741: astore 19
    //   743: aload 28
    //   745: astore 22
    //   747: aload 25
    //   749: astore 18
    //   751: aload 20
    //   753: astore 15
    //   755: aload 24
    //   757: astore 13
    //   759: aload 29
    //   761: invokevirtual 172	java/io/File:createNewFile	()Z
    //   764: pop
    //   765: aload 26
    //   767: astore 23
    //   769: aload 16
    //   771: astore 17
    //   773: aload 27
    //   775: astore 21
    //   777: aload 14
    //   779: astore 19
    //   781: aload 28
    //   783: astore 22
    //   785: aload 25
    //   787: astore 18
    //   789: aload 20
    //   791: astore 15
    //   793: aload 24
    //   795: astore 13
    //   797: aload 29
    //   799: iconst_1
    //   800: iconst_1
    //   801: invokevirtual 176	java/io/File:setReadable	(ZZ)Z
    //   804: pop
    //   805: aload 26
    //   807: astore 23
    //   809: aload 16
    //   811: astore 17
    //   813: aload 27
    //   815: astore 21
    //   817: aload 14
    //   819: astore 19
    //   821: aload 28
    //   823: astore 22
    //   825: aload 25
    //   827: astore 18
    //   829: aload 20
    //   831: astore 15
    //   833: aload 24
    //   835: astore 13
    //   837: aload 29
    //   839: iconst_1
    //   840: iconst_1
    //   841: invokevirtual 179	java/io/File:setWritable	(ZZ)Z
    //   844: pop
    //   845: aload 26
    //   847: astore 23
    //   849: aload 16
    //   851: astore 17
    //   853: aload 27
    //   855: astore 21
    //   857: aload 14
    //   859: astore 19
    //   861: aload 28
    //   863: astore 22
    //   865: aload 25
    //   867: astore 18
    //   869: aload 20
    //   871: astore 15
    //   873: aload 24
    //   875: astore 13
    //   877: aload_0
    //   878: aload_2
    //   879: invokeinterface 63 1 0
    //   884: invokevirtual 183	com/yolanda/nohttp/download/DownloadConnection:getHttpConnection	(Lcom/yolanda/nohttp/BasicAnalyzeRequest;)Ljava/net/HttpURLConnection;
    //   887: astore 20
    //   889: aload 20
    //   891: astore 23
    //   893: aload 16
    //   895: astore 17
    //   897: aload 20
    //   899: astore 21
    //   901: aload 14
    //   903: astore 19
    //   905: aload 20
    //   907: astore 22
    //   909: aload 25
    //   911: astore 18
    //   913: aload 20
    //   915: astore 15
    //   917: aload 24
    //   919: astore 13
    //   921: aload_2
    //   922: invokeinterface 165 1 0
    //   927: ifeq +64 -> 991
    //   930: aload 20
    //   932: astore 23
    //   934: aload 16
    //   936: astore 17
    //   938: aload 20
    //   940: astore 21
    //   942: aload 14
    //   944: astore 19
    //   946: aload 20
    //   948: astore 22
    //   950: aload 25
    //   952: astore 18
    //   954: aload 20
    //   956: astore 15
    //   958: aload 24
    //   960: astore 13
    //   962: aload 20
    //   964: ldc 185
    //   966: new 127	java/lang/StringBuilder
    //   969: dup
    //   970: ldc 187
    //   972: invokespecial 130	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   975: lload 6
    //   977: invokevirtual 190	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   980: ldc 192
    //   982: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   985: invokevirtual 140	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   988: invokevirtual 197	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   991: aload 20
    //   993: astore 23
    //   995: aload 16
    //   997: astore 17
    //   999: aload 20
    //   1001: astore 21
    //   1003: aload 14
    //   1005: astore 19
    //   1007: aload 20
    //   1009: astore 22
    //   1011: aload 25
    //   1013: astore 18
    //   1015: aload 20
    //   1017: astore 15
    //   1019: aload 24
    //   1021: astore 13
    //   1023: ldc 199
    //   1025: invokestatic 202	com/yolanda/nohttp/Logger:i	(Ljava/lang/String;)V
    //   1028: aload 20
    //   1030: astore 23
    //   1032: aload 16
    //   1034: astore 17
    //   1036: aload 20
    //   1038: astore 21
    //   1040: aload 14
    //   1042: astore 19
    //   1044: aload 20
    //   1046: astore 22
    //   1048: aload 25
    //   1050: astore 18
    //   1052: aload 20
    //   1054: astore 15
    //   1056: aload 24
    //   1058: astore 13
    //   1060: aload 20
    //   1062: invokevirtual 206	java/net/HttpURLConnection:getResponseCode	()I
    //   1065: istore 4
    //   1067: aload 20
    //   1069: astore 23
    //   1071: aload 16
    //   1073: astore 17
    //   1075: aload 20
    //   1077: astore 21
    //   1079: aload 14
    //   1081: astore 19
    //   1083: aload 20
    //   1085: astore 22
    //   1087: aload 25
    //   1089: astore 18
    //   1091: aload 20
    //   1093: astore 15
    //   1095: aload 24
    //   1097: astore 13
    //   1099: new 127	java/lang/StringBuilder
    //   1102: dup
    //   1103: ldc 208
    //   1105: invokespecial 130	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1108: iload 4
    //   1110: invokevirtual 211	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1113: invokevirtual 140	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1116: invokestatic 145	com/yolanda/nohttp/Logger:d	(Ljava/lang/String;)V
    //   1119: aload 20
    //   1121: astore 23
    //   1123: aload 16
    //   1125: astore 17
    //   1127: aload 20
    //   1129: astore 21
    //   1131: aload 14
    //   1133: astore 19
    //   1135: aload 20
    //   1137: astore 22
    //   1139: aload 25
    //   1141: astore 18
    //   1143: aload 20
    //   1145: astore 15
    //   1147: aload 24
    //   1149: astore 13
    //   1151: aload 20
    //   1153: invokevirtual 215	java/net/HttpURLConnection:getHeaderFields	()Ljava/util/Map;
    //   1156: astore 26
    //   1158: aload 20
    //   1160: astore 23
    //   1162: aload 16
    //   1164: astore 17
    //   1166: aload 20
    //   1168: astore 21
    //   1170: aload 14
    //   1172: astore 19
    //   1174: aload 20
    //   1176: astore 22
    //   1178: aload 25
    //   1180: astore 18
    //   1182: aload 20
    //   1184: astore 15
    //   1186: aload 24
    //   1188: astore 13
    //   1190: aload 26
    //   1192: invokeinterface 221 1 0
    //   1197: invokeinterface 227 1 0
    //   1202: astore 28
    //   1204: aload 20
    //   1206: astore 23
    //   1208: aload 16
    //   1210: astore 17
    //   1212: aload 20
    //   1214: astore 21
    //   1216: aload 14
    //   1218: astore 19
    //   1220: aload 20
    //   1222: astore 22
    //   1224: aload 25
    //   1226: astore 18
    //   1228: aload 20
    //   1230: astore 15
    //   1232: aload 24
    //   1234: astore 13
    //   1236: aload 28
    //   1238: invokeinterface 232 1 0
    //   1243: ifne +592 -> 1835
    //   1246: aload 20
    //   1248: astore 23
    //   1250: aload 16
    //   1252: astore 17
    //   1254: aload 20
    //   1256: astore 21
    //   1258: aload 14
    //   1260: astore 19
    //   1262: aload 20
    //   1264: astore 22
    //   1266: aload 25
    //   1268: astore 18
    //   1270: aload 20
    //   1272: astore 15
    //   1274: aload 24
    //   1276: astore 13
    //   1278: aload_2
    //   1279: invokeinterface 235 1 0
    //   1284: ifeq +1111 -> 2395
    //   1287: aload 20
    //   1289: astore 23
    //   1291: aload 16
    //   1293: astore 17
    //   1295: aload 20
    //   1297: astore 21
    //   1299: aload 14
    //   1301: astore 19
    //   1303: aload 20
    //   1305: astore 22
    //   1307: aload 25
    //   1309: astore 18
    //   1311: aload 20
    //   1313: astore 15
    //   1315: aload 24
    //   1317: astore 13
    //   1319: aload_2
    //   1320: invokeinterface 63 1 0
    //   1325: iconst_0
    //   1326: invokeinterface 69 2 0
    //   1331: aload 20
    //   1333: astore 23
    //   1335: aload 16
    //   1337: astore 17
    //   1339: aload 20
    //   1341: astore 21
    //   1343: aload 14
    //   1345: astore 19
    //   1347: aload 20
    //   1349: astore 22
    //   1351: aload 25
    //   1353: astore 18
    //   1355: aload 20
    //   1357: astore 15
    //   1359: aload 24
    //   1361: astore 13
    //   1363: ldc 237
    //   1365: ldc 239
    //   1367: invokestatic 244	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   1370: pop
    //   1371: aload 20
    //   1373: astore 23
    //   1375: aload 16
    //   1377: astore 17
    //   1379: aload 20
    //   1381: astore 21
    //   1383: aload 14
    //   1385: astore 19
    //   1387: aload 20
    //   1389: astore 22
    //   1391: aload 25
    //   1393: astore 18
    //   1395: aload 20
    //   1397: astore 15
    //   1399: aload 24
    //   1401: astore 13
    //   1403: aload_3
    //   1404: iload_1
    //   1405: invokeinterface 248 2 0
    //   1410: ldc 250
    //   1412: invokestatic 202	com/yolanda/nohttp/Logger:i	(Ljava/lang/String;)V
    //   1415: iconst_0
    //   1416: ifeq +11 -> 1427
    //   1419: new 252	java/lang/NullPointerException
    //   1422: dup
    //   1423: invokespecial 253	java/lang/NullPointerException:<init>	()V
    //   1426: athrow
    //   1427: aload 20
    //   1429: ifnull -1355 -> 74
    //   1432: aload 20
    //   1434: invokevirtual 256	java/net/HttpURLConnection:disconnect	()V
    //   1437: return
    //   1438: aload 26
    //   1440: astore 23
    //   1442: aload 16
    //   1444: astore 17
    //   1446: aload 27
    //   1448: astore 21
    //   1450: aload 14
    //   1452: astore 19
    //   1454: aload 28
    //   1456: astore 22
    //   1458: aload 25
    //   1460: astore 18
    //   1462: aload 20
    //   1464: astore 15
    //   1466: aload 24
    //   1468: astore 13
    //   1470: aload_2
    //   1471: invokeinterface 63 1 0
    //   1476: iconst_0
    //   1477: invokeinterface 69 2 0
    //   1482: aload 26
    //   1484: astore 23
    //   1486: aload 16
    //   1488: astore 17
    //   1490: aload 27
    //   1492: astore 21
    //   1494: aload 14
    //   1496: astore 19
    //   1498: aload 28
    //   1500: astore 22
    //   1502: aload 25
    //   1504: astore 18
    //   1506: aload 20
    //   1508: astore 15
    //   1510: aload 24
    //   1512: astore 13
    //   1514: aload_3
    //   1515: iload_1
    //   1516: iconst_1
    //   1517: aload 30
    //   1519: invokevirtual 169	java/io/File:length	()J
    //   1522: new 258	com/yolanda/nohttp/Headers
    //   1525: dup
    //   1526: invokespecial 259	com/yolanda/nohttp/Headers:<init>	()V
    //   1529: aload 30
    //   1531: invokevirtual 169	java/io/File:length	()J
    //   1534: invokeinterface 263 8 0
    //   1539: aload 26
    //   1541: astore 23
    //   1543: aload 16
    //   1545: astore 17
    //   1547: aload 27
    //   1549: astore 21
    //   1551: aload 14
    //   1553: astore 19
    //   1555: aload 28
    //   1557: astore 22
    //   1559: aload 25
    //   1561: astore 18
    //   1563: aload 20
    //   1565: astore 15
    //   1567: aload 24
    //   1569: astore 13
    //   1571: aload_3
    //   1572: iload_1
    //   1573: bipush 100
    //   1575: aload 30
    //   1577: invokevirtual 169	java/io/File:length	()J
    //   1580: invokeinterface 267 5 0
    //   1585: aload 26
    //   1587: astore 23
    //   1589: aload 16
    //   1591: astore 17
    //   1593: aload 27
    //   1595: astore 21
    //   1597: aload 14
    //   1599: astore 19
    //   1601: aload 28
    //   1603: astore 22
    //   1605: aload 25
    //   1607: astore 18
    //   1609: aload 20
    //   1611: astore 15
    //   1613: aload 24
    //   1615: astore 13
    //   1617: ldc_w 269
    //   1620: invokestatic 145	com/yolanda/nohttp/Logger:d	(Ljava/lang/String;)V
    //   1623: aload 26
    //   1625: astore 23
    //   1627: aload 16
    //   1629: astore 17
    //   1631: aload 27
    //   1633: astore 21
    //   1635: aload 14
    //   1637: astore 19
    //   1639: aload 28
    //   1641: astore 22
    //   1643: aload 25
    //   1645: astore 18
    //   1647: aload 20
    //   1649: astore 15
    //   1651: aload 24
    //   1653: astore 13
    //   1655: aload_3
    //   1656: iload_1
    //   1657: aload 30
    //   1659: invokevirtual 133	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   1662: invokeinterface 273 3 0
    //   1667: ldc 250
    //   1669: invokestatic 202	com/yolanda/nohttp/Logger:i	(Ljava/lang/String;)V
    //   1672: iconst_0
    //   1673: ifeq +11 -> 1684
    //   1676: new 252	java/lang/NullPointerException
    //   1679: dup
    //   1680: invokespecial 253	java/lang/NullPointerException:<init>	()V
    //   1683: athrow
    //   1684: iconst_0
    //   1685: ifeq -1611 -> 74
    //   1688: new 252	java/lang/NullPointerException
    //   1691: dup
    //   1692: invokespecial 253	java/lang/NullPointerException:<init>	()V
    //   1695: athrow
    //   1696: aload 26
    //   1698: astore 23
    //   1700: aload 16
    //   1702: astore 17
    //   1704: aload 27
    //   1706: astore 21
    //   1708: aload 14
    //   1710: astore 19
    //   1712: aload 28
    //   1714: astore 22
    //   1716: aload 25
    //   1718: astore 18
    //   1720: aload 20
    //   1722: astore 15
    //   1724: aload 24
    //   1726: astore 13
    //   1728: aload 29
    //   1730: invokevirtual 151	java/io/File:delete	()Z
    //   1733: pop
    //   1734: lload 8
    //   1736: lstore 6
    //   1738: goto -1051 -> 687
    //   1741: astore 14
    //   1743: aload 23
    //   1745: astore 15
    //   1747: aload 17
    //   1749: astore 13
    //   1751: aload_2
    //   1752: invokeinterface 63 1 0
    //   1757: iconst_0
    //   1758: invokeinterface 69 2 0
    //   1763: aload 23
    //   1765: astore 15
    //   1767: aload 17
    //   1769: astore 13
    //   1771: aload_0
    //   1772: aload 14
    //   1774: invokevirtual 277	com/yolanda/nohttp/download/DownloadConnection:getExcetionMessage	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   1777: astore_2
    //   1778: aload 23
    //   1780: astore 15
    //   1782: aload 17
    //   1784: astore 13
    //   1786: aload_2
    //   1787: invokestatic 280	com/yolanda/nohttp/Logger:e	(Ljava/lang/String;)V
    //   1790: aload 23
    //   1792: astore 15
    //   1794: aload 17
    //   1796: astore 13
    //   1798: aload_3
    //   1799: iload_1
    //   1800: getstatic 283	com/yolanda/nohttp/download/StatusCode:ERROR_DOWNLOAD_TIMEOUT	Lcom/yolanda/nohttp/download/StatusCode;
    //   1803: aload_2
    //   1804: invokeinterface 91 4 0
    //   1809: ldc 250
    //   1811: invokestatic 202	com/yolanda/nohttp/Logger:i	(Ljava/lang/String;)V
    //   1814: aload 17
    //   1816: ifnull +8 -> 1824
    //   1819: aload 17
    //   1821: invokevirtual 288	java/io/InputStream:close	()V
    //   1824: aload 23
    //   1826: ifnull -1752 -> 74
    //   1829: aload 23
    //   1831: invokevirtual 256	java/net/HttpURLConnection:disconnect	()V
    //   1834: return
    //   1835: aload 20
    //   1837: astore 23
    //   1839: aload 16
    //   1841: astore 17
    //   1843: aload 20
    //   1845: astore 21
    //   1847: aload 14
    //   1849: astore 19
    //   1851: aload 20
    //   1853: astore 22
    //   1855: aload 25
    //   1857: astore 18
    //   1859: aload 20
    //   1861: astore 15
    //   1863: aload 24
    //   1865: astore 13
    //   1867: aload 28
    //   1869: invokeinterface 292 1 0
    //   1874: checkcast 153	java/lang/String
    //   1877: astore 27
    //   1879: aload 20
    //   1881: astore 23
    //   1883: aload 16
    //   1885: astore 17
    //   1887: aload 20
    //   1889: astore 21
    //   1891: aload 14
    //   1893: astore 19
    //   1895: aload 20
    //   1897: astore 22
    //   1899: aload 25
    //   1901: astore 18
    //   1903: aload 20
    //   1905: astore 15
    //   1907: aload 24
    //   1909: astore 13
    //   1911: aload 26
    //   1913: aload 27
    //   1915: invokeinterface 296 2 0
    //   1920: checkcast 298	java/util/List
    //   1923: invokeinterface 299 1 0
    //   1928: astore 32
    //   1930: aload 20
    //   1932: astore 23
    //   1934: aload 16
    //   1936: astore 17
    //   1938: aload 20
    //   1940: astore 21
    //   1942: aload 14
    //   1944: astore 19
    //   1946: aload 20
    //   1948: astore 22
    //   1950: aload 25
    //   1952: astore 18
    //   1954: aload 20
    //   1956: astore 15
    //   1958: aload 24
    //   1960: astore 13
    //   1962: aload 32
    //   1964: invokeinterface 232 1 0
    //   1969: ifeq -765 -> 1204
    //   1972: aload 20
    //   1974: astore 23
    //   1976: aload 16
    //   1978: astore 17
    //   1980: aload 20
    //   1982: astore 21
    //   1984: aload 14
    //   1986: astore 19
    //   1988: aload 20
    //   1990: astore 22
    //   1992: aload 25
    //   1994: astore 18
    //   1996: aload 20
    //   1998: astore 15
    //   2000: aload 24
    //   2002: astore 13
    //   2004: aload 32
    //   2006: invokeinterface 292 1 0
    //   2011: checkcast 153	java/lang/String
    //   2014: astore 31
    //   2016: aload 20
    //   2018: astore 23
    //   2020: aload 16
    //   2022: astore 17
    //   2024: aload 20
    //   2026: astore 21
    //   2028: aload 14
    //   2030: astore 19
    //   2032: aload 20
    //   2034: astore 22
    //   2036: aload 25
    //   2038: astore 18
    //   2040: aload 20
    //   2042: astore 15
    //   2044: aload 24
    //   2046: astore 13
    //   2048: new 301	java/lang/StringBuffer
    //   2051: dup
    //   2052: invokespecial 302	java/lang/StringBuffer:<init>	()V
    //   2055: astore 33
    //   2057: aload 20
    //   2059: astore 23
    //   2061: aload 16
    //   2063: astore 17
    //   2065: aload 20
    //   2067: astore 21
    //   2069: aload 14
    //   2071: astore 19
    //   2073: aload 20
    //   2075: astore 22
    //   2077: aload 25
    //   2079: astore 18
    //   2081: aload 20
    //   2083: astore 15
    //   2085: aload 24
    //   2087: astore 13
    //   2089: aload 27
    //   2091: invokestatic 308	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2094: ifne +84 -> 2178
    //   2097: aload 20
    //   2099: astore 23
    //   2101: aload 16
    //   2103: astore 17
    //   2105: aload 20
    //   2107: astore 21
    //   2109: aload 14
    //   2111: astore 19
    //   2113: aload 20
    //   2115: astore 22
    //   2117: aload 25
    //   2119: astore 18
    //   2121: aload 20
    //   2123: astore 15
    //   2125: aload 24
    //   2127: astore 13
    //   2129: aload 33
    //   2131: aload 27
    //   2133: invokevirtual 311	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   2136: pop
    //   2137: aload 20
    //   2139: astore 23
    //   2141: aload 16
    //   2143: astore 17
    //   2145: aload 20
    //   2147: astore 21
    //   2149: aload 14
    //   2151: astore 19
    //   2153: aload 20
    //   2155: astore 22
    //   2157: aload 25
    //   2159: astore 18
    //   2161: aload 20
    //   2163: astore 15
    //   2165: aload 24
    //   2167: astore 13
    //   2169: aload 33
    //   2171: ldc_w 313
    //   2174: invokevirtual 311	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   2177: pop
    //   2178: aload 20
    //   2180: astore 23
    //   2182: aload 16
    //   2184: astore 17
    //   2186: aload 20
    //   2188: astore 21
    //   2190: aload 14
    //   2192: astore 19
    //   2194: aload 20
    //   2196: astore 22
    //   2198: aload 25
    //   2200: astore 18
    //   2202: aload 20
    //   2204: astore 15
    //   2206: aload 24
    //   2208: astore 13
    //   2210: aload 31
    //   2212: invokestatic 308	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2215: ifne +43 -> 2258
    //   2218: aload 20
    //   2220: astore 23
    //   2222: aload 16
    //   2224: astore 17
    //   2226: aload 20
    //   2228: astore 21
    //   2230: aload 14
    //   2232: astore 19
    //   2234: aload 20
    //   2236: astore 22
    //   2238: aload 25
    //   2240: astore 18
    //   2242: aload 20
    //   2244: astore 15
    //   2246: aload 24
    //   2248: astore 13
    //   2250: aload 33
    //   2252: aload 31
    //   2254: invokevirtual 311	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   2257: pop
    //   2258: aload 20
    //   2260: astore 23
    //   2262: aload 16
    //   2264: astore 17
    //   2266: aload 20
    //   2268: astore 21
    //   2270: aload 14
    //   2272: astore 19
    //   2274: aload 20
    //   2276: astore 22
    //   2278: aload 25
    //   2280: astore 18
    //   2282: aload 20
    //   2284: astore 15
    //   2286: aload 24
    //   2288: astore 13
    //   2290: aload 33
    //   2292: invokevirtual 314	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   2295: invokestatic 145	com/yolanda/nohttp/Logger:d	(Ljava/lang/String;)V
    //   2298: goto -368 -> 1930
    //   2301: astore 14
    //   2303: aload 21
    //   2305: astore 15
    //   2307: aload 19
    //   2309: astore 13
    //   2311: aload_2
    //   2312: invokeinterface 63 1 0
    //   2317: iconst_0
    //   2318: invokeinterface 69 2 0
    //   2323: aload 21
    //   2325: astore 15
    //   2327: aload 19
    //   2329: astore 13
    //   2331: aload_0
    //   2332: aload 14
    //   2334: invokevirtual 277	com/yolanda/nohttp/download/DownloadConnection:getExcetionMessage	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   2337: astore_2
    //   2338: aload 21
    //   2340: astore 15
    //   2342: aload 19
    //   2344: astore 13
    //   2346: aload_2
    //   2347: invokestatic 280	com/yolanda/nohttp/Logger:e	(Ljava/lang/String;)V
    //   2350: aload 21
    //   2352: astore 15
    //   2354: aload 19
    //   2356: astore 13
    //   2358: aload_3
    //   2359: iload_1
    //   2360: getstatic 317	com/yolanda/nohttp/download/StatusCode:ERROR_SERVER_NOT_FOUND	Lcom/yolanda/nohttp/download/StatusCode;
    //   2363: aload_2
    //   2364: invokeinterface 91 4 0
    //   2369: ldc 250
    //   2371: invokestatic 202	com/yolanda/nohttp/Logger:i	(Ljava/lang/String;)V
    //   2374: aload 19
    //   2376: ifnull +8 -> 2384
    //   2379: aload 19
    //   2381: invokevirtual 288	java/io/InputStream:close	()V
    //   2384: aload 21
    //   2386: ifnull -2312 -> 74
    //   2389: aload 21
    //   2391: invokevirtual 256	java/net/HttpURLConnection:disconnect	()V
    //   2394: return
    //   2395: lconst_0
    //   2396: lstore 8
    //   2398: iload 4
    //   2400: sipush 206
    //   2403: if_icmpne +453 -> 2856
    //   2406: aload 20
    //   2408: astore 23
    //   2410: aload 16
    //   2412: astore 17
    //   2414: aload 20
    //   2416: astore 21
    //   2418: aload 14
    //   2420: astore 19
    //   2422: aload 20
    //   2424: astore 22
    //   2426: aload 25
    //   2428: astore 18
    //   2430: aload 20
    //   2432: astore 15
    //   2434: aload 24
    //   2436: astore 13
    //   2438: aload 20
    //   2440: ldc_w 319
    //   2443: invokevirtual 323	java/net/HttpURLConnection:getHeaderField	(Ljava/lang/String;)Ljava/lang/String;
    //   2446: astore 27
    //   2448: aload 20
    //   2450: astore 23
    //   2452: aload 16
    //   2454: astore 17
    //   2456: aload 20
    //   2458: astore 21
    //   2460: aload 14
    //   2462: astore 19
    //   2464: aload 20
    //   2466: astore 22
    //   2468: aload 25
    //   2470: astore 18
    //   2472: aload 20
    //   2474: astore 15
    //   2476: aload 24
    //   2478: astore 13
    //   2480: aload 27
    //   2482: invokestatic 308	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2485: istore 12
    //   2487: iload 12
    //   2489: ifne +46 -> 2535
    //   2492: aload 20
    //   2494: astore 23
    //   2496: aload 16
    //   2498: astore 17
    //   2500: aload 20
    //   2502: astore 21
    //   2504: aload 14
    //   2506: astore 19
    //   2508: aload 20
    //   2510: astore 15
    //   2512: aload 24
    //   2514: astore 13
    //   2516: aload 27
    //   2518: aload 27
    //   2520: bipush 47
    //   2522: invokevirtual 327	java/lang/String:indexOf	(I)I
    //   2525: iconst_1
    //   2526: iadd
    //   2527: invokevirtual 331	java/lang/String:substring	(I)Ljava/lang/String;
    //   2530: invokestatic 337	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   2533: lstore 8
    //   2535: aload 20
    //   2537: astore 23
    //   2539: aload 16
    //   2541: astore 17
    //   2543: aload 20
    //   2545: astore 21
    //   2547: aload 14
    //   2549: astore 19
    //   2551: aload 20
    //   2553: astore 22
    //   2555: aload 25
    //   2557: astore 18
    //   2559: aload 20
    //   2561: astore 15
    //   2563: aload 24
    //   2565: astore 13
    //   2567: aload_2
    //   2568: invokeinterface 111 1 0
    //   2573: invokestatic 342	com/yolanda/nohttp/tools/FileUtil:getDirSize	(Ljava/lang/String;)J
    //   2576: lload 8
    //   2578: lcmp
    //   2579: ifge +460 -> 3039
    //   2582: aload 20
    //   2584: astore 23
    //   2586: aload 16
    //   2588: astore 17
    //   2590: aload 20
    //   2592: astore 21
    //   2594: aload 14
    //   2596: astore 19
    //   2598: aload 20
    //   2600: astore 22
    //   2602: aload 25
    //   2604: astore 18
    //   2606: aload 20
    //   2608: astore 15
    //   2610: aload 24
    //   2612: astore 13
    //   2614: aload_2
    //   2615: invokeinterface 63 1 0
    //   2620: iconst_0
    //   2621: invokeinterface 69 2 0
    //   2626: aload 20
    //   2628: astore 23
    //   2630: aload 16
    //   2632: astore 17
    //   2634: aload 20
    //   2636: astore 21
    //   2638: aload 14
    //   2640: astore 19
    //   2642: aload 20
    //   2644: astore 22
    //   2646: aload 25
    //   2648: astore 18
    //   2650: aload 20
    //   2652: astore 15
    //   2654: aload 24
    //   2656: astore 13
    //   2658: aload_3
    //   2659: iload_1
    //   2660: getstatic 345	com/yolanda/nohttp/download/StatusCode:ERROR_STORAGE_NOT_ENOUGH	Lcom/yolanda/nohttp/download/StatusCode;
    //   2663: ldc_w 347
    //   2666: invokeinterface 91 4 0
    //   2671: ldc 250
    //   2673: invokestatic 202	com/yolanda/nohttp/Logger:i	(Ljava/lang/String;)V
    //   2676: iconst_0
    //   2677: ifeq +11 -> 2688
    //   2680: new 252	java/lang/NullPointerException
    //   2683: dup
    //   2684: invokespecial 253	java/lang/NullPointerException:<init>	()V
    //   2687: athrow
    //   2688: aload 20
    //   2690: ifnull -2616 -> 74
    //   2693: aload 20
    //   2695: invokevirtual 256	java/net/HttpURLConnection:disconnect	()V
    //   2698: return
    //   2699: astore 13
    //   2701: aload 20
    //   2703: astore 23
    //   2705: aload 16
    //   2707: astore 17
    //   2709: aload 20
    //   2711: astore 21
    //   2713: aload 14
    //   2715: astore 19
    //   2717: aload 20
    //   2719: astore 22
    //   2721: aload 25
    //   2723: astore 18
    //   2725: aload 20
    //   2727: astore 15
    //   2729: aload 24
    //   2731: astore 13
    //   2733: aload_2
    //   2734: invokeinterface 63 1 0
    //   2739: iconst_0
    //   2740: invokeinterface 69 2 0
    //   2745: aload 20
    //   2747: astore 23
    //   2749: aload 16
    //   2751: astore 17
    //   2753: aload 20
    //   2755: astore 21
    //   2757: aload 14
    //   2759: astore 19
    //   2761: aload 20
    //   2763: astore 22
    //   2765: aload 25
    //   2767: astore 18
    //   2769: aload 20
    //   2771: astore 15
    //   2773: aload 24
    //   2775: astore 13
    //   2777: ldc_w 349
    //   2780: invokestatic 280	com/yolanda/nohttp/Logger:e	(Ljava/lang/String;)V
    //   2783: aload 20
    //   2785: astore 23
    //   2787: aload 16
    //   2789: astore 17
    //   2791: aload 20
    //   2793: astore 21
    //   2795: aload 14
    //   2797: astore 19
    //   2799: aload 20
    //   2801: astore 22
    //   2803: aload 25
    //   2805: astore 18
    //   2807: aload 20
    //   2809: astore 15
    //   2811: aload 24
    //   2813: astore 13
    //   2815: aload_3
    //   2816: iload_1
    //   2817: getstatic 352	com/yolanda/nohttp/download/StatusCode:ERROR_SERVER_EXCEPTION	Lcom/yolanda/nohttp/download/StatusCode;
    //   2820: ldc_w 349
    //   2823: invokeinterface 91 4 0
    //   2828: ldc 250
    //   2830: invokestatic 202	com/yolanda/nohttp/Logger:i	(Ljava/lang/String;)V
    //   2833: iconst_0
    //   2834: ifeq +11 -> 2845
    //   2837: new 252	java/lang/NullPointerException
    //   2840: dup
    //   2841: invokespecial 253	java/lang/NullPointerException:<init>	()V
    //   2844: athrow
    //   2845: aload 20
    //   2847: ifnull -2773 -> 74
    //   2850: aload 20
    //   2852: invokevirtual 256	java/net/HttpURLConnection:disconnect	()V
    //   2855: return
    //   2856: iload 4
    //   2858: sipush 200
    //   2861: if_icmpne +46 -> 2907
    //   2864: aload 20
    //   2866: astore 23
    //   2868: aload 16
    //   2870: astore 17
    //   2872: aload 20
    //   2874: astore 21
    //   2876: aload 14
    //   2878: astore 19
    //   2880: aload 20
    //   2882: astore 22
    //   2884: aload 25
    //   2886: astore 18
    //   2888: aload 20
    //   2890: astore 15
    //   2892: aload 24
    //   2894: astore 13
    //   2896: aload 20
    //   2898: invokevirtual 355	java/net/HttpURLConnection:getContentLength	()I
    //   2901: i2l
    //   2902: lstore 8
    //   2904: goto -369 -> 2535
    //   2907: aload 20
    //   2909: astore 23
    //   2911: aload 16
    //   2913: astore 17
    //   2915: aload 20
    //   2917: astore 21
    //   2919: aload 14
    //   2921: astore 19
    //   2923: aload 20
    //   2925: astore 22
    //   2927: aload 25
    //   2929: astore 18
    //   2931: aload 20
    //   2933: astore 15
    //   2935: aload 24
    //   2937: astore 13
    //   2939: aload_2
    //   2940: invokeinterface 63 1 0
    //   2945: iconst_0
    //   2946: invokeinterface 69 2 0
    //   2951: aload 20
    //   2953: astore 23
    //   2955: aload 16
    //   2957: astore 17
    //   2959: aload 20
    //   2961: astore 21
    //   2963: aload 14
    //   2965: astore 19
    //   2967: aload 20
    //   2969: astore 22
    //   2971: aload 25
    //   2973: astore 18
    //   2975: aload 20
    //   2977: astore 15
    //   2979: aload 24
    //   2981: astore 13
    //   2983: aload_3
    //   2984: iload_1
    //   2985: getstatic 358	com/yolanda/nohttp/download/StatusCode:ERROR_OTHER	Lcom/yolanda/nohttp/download/StatusCode;
    //   2988: new 127	java/lang/StringBuilder
    //   2991: dup
    //   2992: ldc_w 360
    //   2995: invokespecial 130	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2998: iload 4
    //   3000: invokevirtual 211	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   3003: invokevirtual 140	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3006: invokeinterface 91 4 0
    //   3011: ldc 250
    //   3013: invokestatic 202	com/yolanda/nohttp/Logger:i	(Ljava/lang/String;)V
    //   3016: iconst_0
    //   3017: ifeq +11 -> 3028
    //   3020: new 252	java/lang/NullPointerException
    //   3023: dup
    //   3024: invokespecial 253	java/lang/NullPointerException:<init>	()V
    //   3027: athrow
    //   3028: aload 20
    //   3030: ifnull -2956 -> 74
    //   3033: aload 20
    //   3035: invokevirtual 256	java/net/HttpURLConnection:disconnect	()V
    //   3038: return
    //   3039: aload 20
    //   3041: astore 23
    //   3043: aload 16
    //   3045: astore 17
    //   3047: aload 20
    //   3049: astore 21
    //   3051: aload 14
    //   3053: astore 19
    //   3055: aload 20
    //   3057: astore 22
    //   3059: aload 25
    //   3061: astore 18
    //   3063: aload 20
    //   3065: astore 15
    //   3067: aload 24
    //   3069: astore 13
    //   3071: ldc_w 362
    //   3074: invokestatic 145	com/yolanda/nohttp/Logger:d	(Ljava/lang/String;)V
    //   3077: lload 6
    //   3079: lconst_0
    //   3080: lcmp
    //   3081: ifle +629 -> 3710
    //   3084: iconst_1
    //   3085: istore 12
    //   3087: aload 20
    //   3089: astore 23
    //   3091: aload 16
    //   3093: astore 17
    //   3095: aload 20
    //   3097: astore 21
    //   3099: aload 14
    //   3101: astore 19
    //   3103: aload 20
    //   3105: astore 22
    //   3107: aload 25
    //   3109: astore 18
    //   3111: aload 20
    //   3113: astore 15
    //   3115: aload 24
    //   3117: astore 13
    //   3119: aload_3
    //   3120: iload_1
    //   3121: iload 12
    //   3123: lload 6
    //   3125: aload 26
    //   3127: invokestatic 366	com/yolanda/nohttp/Headers:parseMultimap	(Ljava/util/Map;)Lcom/yolanda/nohttp/Headers;
    //   3130: lload 8
    //   3132: invokeinterface 263 8 0
    //   3137: aload 20
    //   3139: astore 23
    //   3141: aload 16
    //   3143: astore 17
    //   3145: aload 20
    //   3147: astore 21
    //   3149: aload 14
    //   3151: astore 19
    //   3153: aload 20
    //   3155: astore 22
    //   3157: aload 25
    //   3159: astore 18
    //   3161: aload 20
    //   3163: astore 15
    //   3165: aload 24
    //   3167: astore 13
    //   3169: aload 20
    //   3171: invokevirtual 370	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   3174: astore 16
    //   3176: aload 20
    //   3178: astore 23
    //   3180: aload 16
    //   3182: astore 17
    //   3184: aload 20
    //   3186: astore 21
    //   3188: aload 16
    //   3190: astore 19
    //   3192: aload 16
    //   3194: astore 14
    //   3196: aload 20
    //   3198: astore 22
    //   3200: aload 16
    //   3202: astore 18
    //   3204: aload 20
    //   3206: astore 15
    //   3208: aload 16
    //   3210: astore 13
    //   3212: aload 20
    //   3214: invokevirtual 373	java/net/HttpURLConnection:getContentEncoding	()Ljava/lang/String;
    //   3217: invokestatic 378	com/yolanda/nohttp/HeaderParser:isGzipContent	(Ljava/lang/String;)Z
    //   3220: ifeq +46 -> 3266
    //   3223: aload 20
    //   3225: astore 23
    //   3227: aload 16
    //   3229: astore 17
    //   3231: aload 20
    //   3233: astore 21
    //   3235: aload 16
    //   3237: astore 19
    //   3239: aload 20
    //   3241: astore 22
    //   3243: aload 16
    //   3245: astore 18
    //   3247: aload 20
    //   3249: astore 15
    //   3251: aload 16
    //   3253: astore 13
    //   3255: new 380	java/util/zip/GZIPInputStream
    //   3258: dup
    //   3259: aload 16
    //   3261: invokespecial 383	java/util/zip/GZIPInputStream:<init>	(Ljava/io/InputStream;)V
    //   3264: astore 14
    //   3266: aload 20
    //   3268: astore 23
    //   3270: aload 14
    //   3272: astore 17
    //   3274: aload 20
    //   3276: astore 21
    //   3278: aload 14
    //   3280: astore 19
    //   3282: aload 20
    //   3284: astore 22
    //   3286: aload 14
    //   3288: astore 18
    //   3290: aload 20
    //   3292: astore 15
    //   3294: aload 14
    //   3296: astore 13
    //   3298: new 385	java/io/RandomAccessFile
    //   3301: dup
    //   3302: aload 29
    //   3304: ldc_w 387
    //   3307: invokespecial 388	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   3310: astore 24
    //   3312: aload 20
    //   3314: astore 23
    //   3316: aload 14
    //   3318: astore 17
    //   3320: aload 20
    //   3322: astore 21
    //   3324: aload 14
    //   3326: astore 19
    //   3328: aload 20
    //   3330: astore 22
    //   3332: aload 14
    //   3334: astore 18
    //   3336: aload 20
    //   3338: astore 15
    //   3340: aload 14
    //   3342: astore 13
    //   3344: aload 24
    //   3346: lload 6
    //   3348: invokevirtual 392	java/io/RandomAccessFile:seek	(J)V
    //   3351: aload 20
    //   3353: astore 23
    //   3355: aload 14
    //   3357: astore 17
    //   3359: aload 20
    //   3361: astore 21
    //   3363: aload 14
    //   3365: astore 19
    //   3367: aload 20
    //   3369: astore 22
    //   3371: aload 14
    //   3373: astore 18
    //   3375: aload 20
    //   3377: astore 15
    //   3379: aload 14
    //   3381: astore 13
    //   3383: sipush 1024
    //   3386: newarray byte
    //   3388: astore 16
    //   3390: iconst_0
    //   3391: istore 4
    //   3393: aload 20
    //   3395: astore 23
    //   3397: aload 14
    //   3399: astore 17
    //   3401: aload 20
    //   3403: astore 21
    //   3405: aload 14
    //   3407: astore 19
    //   3409: aload 20
    //   3411: astore 22
    //   3413: aload 14
    //   3415: astore 18
    //   3417: aload 20
    //   3419: astore 15
    //   3421: aload 14
    //   3423: astore 13
    //   3425: aload 14
    //   3427: aload 16
    //   3429: invokevirtual 396	java/io/InputStream:read	([B)I
    //   3432: istore 5
    //   3434: iload 5
    //   3436: iconst_m1
    //   3437: if_icmpne +279 -> 3716
    //   3440: aload 20
    //   3442: astore 23
    //   3444: aload 14
    //   3446: astore 17
    //   3448: aload 20
    //   3450: astore 21
    //   3452: aload 14
    //   3454: astore 19
    //   3456: aload 20
    //   3458: astore 22
    //   3460: aload 14
    //   3462: astore 18
    //   3464: aload 20
    //   3466: astore 15
    //   3468: aload 14
    //   3470: astore 13
    //   3472: aload 24
    //   3474: invokevirtual 397	java/io/RandomAccessFile:close	()V
    //   3477: aload 20
    //   3479: astore 23
    //   3481: aload 14
    //   3483: astore 17
    //   3485: aload 20
    //   3487: astore 21
    //   3489: aload 14
    //   3491: astore 19
    //   3493: aload 20
    //   3495: astore 22
    //   3497: aload 14
    //   3499: astore 18
    //   3501: aload 20
    //   3503: astore 15
    //   3505: aload 14
    //   3507: astore 13
    //   3509: aload_2
    //   3510: invokeinterface 235 1 0
    //   3515: ifne +169 -> 3684
    //   3518: aload 20
    //   3520: astore 23
    //   3522: aload 14
    //   3524: astore 17
    //   3526: aload 20
    //   3528: astore 21
    //   3530: aload 14
    //   3532: astore 19
    //   3534: aload 20
    //   3536: astore 22
    //   3538: aload 14
    //   3540: astore 18
    //   3542: aload 20
    //   3544: astore 15
    //   3546: aload 14
    //   3548: astore 13
    //   3550: aload_2
    //   3551: invokeinterface 63 1 0
    //   3556: iconst_0
    //   3557: invokeinterface 69 2 0
    //   3562: aload 20
    //   3564: astore 23
    //   3566: aload 14
    //   3568: astore 17
    //   3570: aload 20
    //   3572: astore 21
    //   3574: aload 14
    //   3576: astore 19
    //   3578: aload 20
    //   3580: astore 22
    //   3582: aload 14
    //   3584: astore 18
    //   3586: aload 20
    //   3588: astore 15
    //   3590: aload 14
    //   3592: astore 13
    //   3594: aload 29
    //   3596: aload 30
    //   3598: invokevirtual 401	java/io/File:renameTo	(Ljava/io/File;)Z
    //   3601: pop
    //   3602: aload 20
    //   3604: astore 23
    //   3606: aload 14
    //   3608: astore 17
    //   3610: aload 20
    //   3612: astore 21
    //   3614: aload 14
    //   3616: astore 19
    //   3618: aload 20
    //   3620: astore 22
    //   3622: aload 14
    //   3624: astore 18
    //   3626: aload 20
    //   3628: astore 15
    //   3630: aload 14
    //   3632: astore 13
    //   3634: ldc_w 269
    //   3637: invokestatic 145	com/yolanda/nohttp/Logger:d	(Ljava/lang/String;)V
    //   3640: aload 20
    //   3642: astore 23
    //   3644: aload 14
    //   3646: astore 17
    //   3648: aload 20
    //   3650: astore 21
    //   3652: aload 14
    //   3654: astore 19
    //   3656: aload 20
    //   3658: astore 22
    //   3660: aload 14
    //   3662: astore 18
    //   3664: aload 20
    //   3666: astore 15
    //   3668: aload 14
    //   3670: astore 13
    //   3672: aload_3
    //   3673: iload_1
    //   3674: aload 30
    //   3676: invokevirtual 133	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   3679: invokeinterface 273 3 0
    //   3684: ldc 250
    //   3686: invokestatic 202	com/yolanda/nohttp/Logger:i	(Ljava/lang/String;)V
    //   3689: aload 14
    //   3691: ifnull +8 -> 3699
    //   3694: aload 14
    //   3696: invokevirtual 288	java/io/InputStream:close	()V
    //   3699: aload 20
    //   3701: ifnull -3627 -> 74
    //   3704: aload 20
    //   3706: invokevirtual 256	java/net/HttpURLConnection:disconnect	()V
    //   3709: return
    //   3710: iconst_0
    //   3711: istore 12
    //   3713: goto -626 -> 3087
    //   3716: aload 20
    //   3718: astore 23
    //   3720: aload 14
    //   3722: astore 17
    //   3724: aload 20
    //   3726: astore 21
    //   3728: aload 14
    //   3730: astore 19
    //   3732: aload 20
    //   3734: astore 22
    //   3736: aload 14
    //   3738: astore 18
    //   3740: aload 20
    //   3742: astore 15
    //   3744: aload 14
    //   3746: astore 13
    //   3748: aload_2
    //   3749: invokeinterface 235 1 0
    //   3754: ifeq +223 -> 3977
    //   3757: aload 20
    //   3759: astore 23
    //   3761: aload 14
    //   3763: astore 17
    //   3765: aload 20
    //   3767: astore 21
    //   3769: aload 14
    //   3771: astore 19
    //   3773: aload 20
    //   3775: astore 22
    //   3777: aload 14
    //   3779: astore 18
    //   3781: aload 20
    //   3783: astore 15
    //   3785: aload 14
    //   3787: astore 13
    //   3789: aload_2
    //   3790: invokeinterface 63 1 0
    //   3795: iconst_0
    //   3796: invokeinterface 69 2 0
    //   3801: aload 20
    //   3803: astore 23
    //   3805: aload 14
    //   3807: astore 17
    //   3809: aload 20
    //   3811: astore 21
    //   3813: aload 14
    //   3815: astore 19
    //   3817: aload 20
    //   3819: astore 22
    //   3821: aload 14
    //   3823: astore 18
    //   3825: aload 20
    //   3827: astore 15
    //   3829: aload 14
    //   3831: astore 13
    //   3833: ldc 237
    //   3835: ldc 239
    //   3837: invokestatic 244	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   3840: pop
    //   3841: aload 20
    //   3843: astore 23
    //   3845: aload 14
    //   3847: astore 17
    //   3849: aload 20
    //   3851: astore 21
    //   3853: aload 14
    //   3855: astore 19
    //   3857: aload 20
    //   3859: astore 22
    //   3861: aload 14
    //   3863: astore 18
    //   3865: aload 20
    //   3867: astore 15
    //   3869: aload 14
    //   3871: astore 13
    //   3873: aload_3
    //   3874: iload_1
    //   3875: invokeinterface 248 2 0
    //   3880: goto -440 -> 3440
    //   3883: astore 14
    //   3885: aload 22
    //   3887: astore 15
    //   3889: aload 18
    //   3891: astore 13
    //   3893: aload_2
    //   3894: invokeinterface 63 1 0
    //   3899: iconst_0
    //   3900: invokeinterface 69 2 0
    //   3905: aload 22
    //   3907: astore 15
    //   3909: aload 18
    //   3911: astore 13
    //   3913: aload_0
    //   3914: aload 14
    //   3916: invokevirtual 277	com/yolanda/nohttp/download/DownloadConnection:getExcetionMessage	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   3919: astore_2
    //   3920: aload 22
    //   3922: astore 15
    //   3924: aload 18
    //   3926: astore 13
    //   3928: aload_2
    //   3929: invokestatic 280	com/yolanda/nohttp/Logger:e	(Ljava/lang/String;)V
    //   3932: aload 22
    //   3934: astore 15
    //   3936: aload 18
    //   3938: astore 13
    //   3940: aload_3
    //   3941: iload_1
    //   3942: getstatic 358	com/yolanda/nohttp/download/StatusCode:ERROR_OTHER	Lcom/yolanda/nohttp/download/StatusCode;
    //   3945: aload_2
    //   3946: invokeinterface 91 4 0
    //   3951: ldc 250
    //   3953: invokestatic 202	com/yolanda/nohttp/Logger:i	(Ljava/lang/String;)V
    //   3956: aload 18
    //   3958: ifnull +8 -> 3966
    //   3961: aload 18
    //   3963: invokevirtual 288	java/io/InputStream:close	()V
    //   3966: aload 22
    //   3968: ifnull -3894 -> 74
    //   3971: aload 22
    //   3973: invokevirtual 256	java/net/HttpURLConnection:disconnect	()V
    //   3976: return
    //   3977: aload 20
    //   3979: astore 23
    //   3981: aload 14
    //   3983: astore 17
    //   3985: aload 20
    //   3987: astore 21
    //   3989: aload 14
    //   3991: astore 19
    //   3993: aload 20
    //   3995: astore 22
    //   3997: aload 14
    //   3999: astore 18
    //   4001: aload 20
    //   4003: astore 15
    //   4005: aload 14
    //   4007: astore 13
    //   4009: aload 24
    //   4011: aload 16
    //   4013: iconst_0
    //   4014: iload 5
    //   4016: invokevirtual 405	java/io/RandomAccessFile:write	([BII)V
    //   4019: lload 6
    //   4021: iload 5
    //   4023: i2l
    //   4024: ladd
    //   4025: lstore 10
    //   4027: lload 10
    //   4029: lstore 6
    //   4031: lload 8
    //   4033: lconst_0
    //   4034: lcmp
    //   4035: ifeq -642 -> 3393
    //   4038: aload 20
    //   4040: astore 23
    //   4042: aload 14
    //   4044: astore 17
    //   4046: aload 20
    //   4048: astore 21
    //   4050: aload 14
    //   4052: astore 19
    //   4054: aload 20
    //   4056: astore 22
    //   4058: aload 14
    //   4060: astore 18
    //   4062: aload 20
    //   4064: astore 15
    //   4066: aload 14
    //   4068: astore 13
    //   4070: ldc2_w 406
    //   4073: lload 10
    //   4075: lmul
    //   4076: lload 8
    //   4078: ldiv
    //   4079: l2i
    //   4080: istore 5
    //   4082: iload 5
    //   4084: iconst_2
    //   4085: irem
    //   4086: ifeq +29 -> 4115
    //   4089: iload 5
    //   4091: iconst_3
    //   4092: irem
    //   4093: ifeq +22 -> 4115
    //   4096: iload 5
    //   4098: iconst_5
    //   4099: irem
    //   4100: ifeq +15 -> 4115
    //   4103: lload 10
    //   4105: lstore 6
    //   4107: iload 5
    //   4109: bipush 7
    //   4111: irem
    //   4112: ifne -719 -> 3393
    //   4115: lload 10
    //   4117: lstore 6
    //   4119: iload 4
    //   4121: iload 5
    //   4123: if_icmpeq -730 -> 3393
    //   4126: iload 5
    //   4128: istore 4
    //   4130: aload 20
    //   4132: astore 23
    //   4134: aload 14
    //   4136: astore 17
    //   4138: aload 20
    //   4140: astore 21
    //   4142: aload 14
    //   4144: astore 19
    //   4146: aload 20
    //   4148: astore 22
    //   4150: aload 14
    //   4152: astore 18
    //   4154: aload 20
    //   4156: astore 15
    //   4158: aload 14
    //   4160: astore 13
    //   4162: aload_3
    //   4163: iload_1
    //   4164: iload 4
    //   4166: lload 10
    //   4168: invokeinterface 267 5 0
    //   4173: lload 10
    //   4175: lstore 6
    //   4177: goto -784 -> 3393
    //   4180: astore_2
    //   4181: ldc 250
    //   4183: invokestatic 202	com/yolanda/nohttp/Logger:i	(Ljava/lang/String;)V
    //   4186: aload 13
    //   4188: ifnull +8 -> 4196
    //   4191: aload 13
    //   4193: invokevirtual 288	java/io/InputStream:close	()V
    //   4196: aload 15
    //   4198: ifnull +8 -> 4206
    //   4201: aload 15
    //   4203: invokevirtual 256	java/net/HttpURLConnection:disconnect	()V
    //   4206: aload_2
    //   4207: athrow
    //   4208: astore_2
    //   4209: goto -2525 -> 1684
    //   4212: astore_2
    //   4213: goto -2786 -> 1427
    //   4216: astore_2
    //   4217: goto -1372 -> 2845
    //   4220: astore_2
    //   4221: goto -1193 -> 3028
    //   4224: astore_2
    //   4225: goto -1537 -> 2688
    //   4228: astore_2
    //   4229: goto -2405 -> 1824
    //   4232: astore_2
    //   4233: goto -1849 -> 2384
    //   4236: astore_2
    //   4237: goto -271 -> 3966
    //   4240: astore_3
    //   4241: goto -45 -> 4196
    //   4244: astore_2
    //   4245: goto -546 -> 3699
    //
    // Exception table:
    //   from	to	target	type
    //   173	188	1741	java/net/SocketTimeoutException
    //   220	228	1741	java/net/SocketTimeoutException
    //   260	266	1741	java/net/SocketTimeoutException
    //   298	315	1741	java/net/SocketTimeoutException
    //   347	370	1741	java/net/SocketTimeoutException
    //   402	410	1741	java/net/SocketTimeoutException
    //   442	451	1741	java/net/SocketTimeoutException
    //   483	489	1741	java/net/SocketTimeoutException
    //   521	560	1741	java/net/SocketTimeoutException
    //   599	607	1741	java/net/SocketTimeoutException
    //   639	648	1741	java/net/SocketTimeoutException
    //   680	687	1741	java/net/SocketTimeoutException
    //   719	727	1741	java/net/SocketTimeoutException
    //   759	765	1741	java/net/SocketTimeoutException
    //   797	805	1741	java/net/SocketTimeoutException
    //   837	845	1741	java/net/SocketTimeoutException
    //   877	889	1741	java/net/SocketTimeoutException
    //   921	930	1741	java/net/SocketTimeoutException
    //   962	991	1741	java/net/SocketTimeoutException
    //   1023	1028	1741	java/net/SocketTimeoutException
    //   1060	1067	1741	java/net/SocketTimeoutException
    //   1099	1119	1741	java/net/SocketTimeoutException
    //   1151	1158	1741	java/net/SocketTimeoutException
    //   1190	1204	1741	java/net/SocketTimeoutException
    //   1236	1246	1741	java/net/SocketTimeoutException
    //   1278	1287	1741	java/net/SocketTimeoutException
    //   1319	1331	1741	java/net/SocketTimeoutException
    //   1363	1371	1741	java/net/SocketTimeoutException
    //   1403	1410	1741	java/net/SocketTimeoutException
    //   1470	1482	1741	java/net/SocketTimeoutException
    //   1514	1539	1741	java/net/SocketTimeoutException
    //   1571	1585	1741	java/net/SocketTimeoutException
    //   1617	1623	1741	java/net/SocketTimeoutException
    //   1655	1667	1741	java/net/SocketTimeoutException
    //   1728	1734	1741	java/net/SocketTimeoutException
    //   1867	1879	1741	java/net/SocketTimeoutException
    //   1911	1930	1741	java/net/SocketTimeoutException
    //   1962	1972	1741	java/net/SocketTimeoutException
    //   2004	2016	1741	java/net/SocketTimeoutException
    //   2048	2057	1741	java/net/SocketTimeoutException
    //   2089	2097	1741	java/net/SocketTimeoutException
    //   2129	2137	1741	java/net/SocketTimeoutException
    //   2169	2178	1741	java/net/SocketTimeoutException
    //   2210	2218	1741	java/net/SocketTimeoutException
    //   2250	2258	1741	java/net/SocketTimeoutException
    //   2290	2298	1741	java/net/SocketTimeoutException
    //   2438	2448	1741	java/net/SocketTimeoutException
    //   2480	2487	1741	java/net/SocketTimeoutException
    //   2516	2535	1741	java/net/SocketTimeoutException
    //   2567	2582	1741	java/net/SocketTimeoutException
    //   2614	2626	1741	java/net/SocketTimeoutException
    //   2658	2671	1741	java/net/SocketTimeoutException
    //   2733	2745	1741	java/net/SocketTimeoutException
    //   2777	2783	1741	java/net/SocketTimeoutException
    //   2815	2828	1741	java/net/SocketTimeoutException
    //   2896	2904	1741	java/net/SocketTimeoutException
    //   2939	2951	1741	java/net/SocketTimeoutException
    //   2983	3011	1741	java/net/SocketTimeoutException
    //   3071	3077	1741	java/net/SocketTimeoutException
    //   3119	3137	1741	java/net/SocketTimeoutException
    //   3169	3176	1741	java/net/SocketTimeoutException
    //   3212	3223	1741	java/net/SocketTimeoutException
    //   3255	3266	1741	java/net/SocketTimeoutException
    //   3298	3312	1741	java/net/SocketTimeoutException
    //   3344	3351	1741	java/net/SocketTimeoutException
    //   3383	3390	1741	java/net/SocketTimeoutException
    //   3425	3434	1741	java/net/SocketTimeoutException
    //   3472	3477	1741	java/net/SocketTimeoutException
    //   3509	3518	1741	java/net/SocketTimeoutException
    //   3550	3562	1741	java/net/SocketTimeoutException
    //   3594	3602	1741	java/net/SocketTimeoutException
    //   3634	3640	1741	java/net/SocketTimeoutException
    //   3672	3684	1741	java/net/SocketTimeoutException
    //   3748	3757	1741	java/net/SocketTimeoutException
    //   3789	3801	1741	java/net/SocketTimeoutException
    //   3833	3841	1741	java/net/SocketTimeoutException
    //   3873	3880	1741	java/net/SocketTimeoutException
    //   4009	4019	1741	java/net/SocketTimeoutException
    //   4070	4082	1741	java/net/SocketTimeoutException
    //   4162	4173	1741	java/net/SocketTimeoutException
    //   173	188	2301	java/net/UnknownHostException
    //   220	228	2301	java/net/UnknownHostException
    //   260	266	2301	java/net/UnknownHostException
    //   298	315	2301	java/net/UnknownHostException
    //   347	370	2301	java/net/UnknownHostException
    //   402	410	2301	java/net/UnknownHostException
    //   442	451	2301	java/net/UnknownHostException
    //   483	489	2301	java/net/UnknownHostException
    //   521	560	2301	java/net/UnknownHostException
    //   599	607	2301	java/net/UnknownHostException
    //   639	648	2301	java/net/UnknownHostException
    //   680	687	2301	java/net/UnknownHostException
    //   719	727	2301	java/net/UnknownHostException
    //   759	765	2301	java/net/UnknownHostException
    //   797	805	2301	java/net/UnknownHostException
    //   837	845	2301	java/net/UnknownHostException
    //   877	889	2301	java/net/UnknownHostException
    //   921	930	2301	java/net/UnknownHostException
    //   962	991	2301	java/net/UnknownHostException
    //   1023	1028	2301	java/net/UnknownHostException
    //   1060	1067	2301	java/net/UnknownHostException
    //   1099	1119	2301	java/net/UnknownHostException
    //   1151	1158	2301	java/net/UnknownHostException
    //   1190	1204	2301	java/net/UnknownHostException
    //   1236	1246	2301	java/net/UnknownHostException
    //   1278	1287	2301	java/net/UnknownHostException
    //   1319	1331	2301	java/net/UnknownHostException
    //   1363	1371	2301	java/net/UnknownHostException
    //   1403	1410	2301	java/net/UnknownHostException
    //   1470	1482	2301	java/net/UnknownHostException
    //   1514	1539	2301	java/net/UnknownHostException
    //   1571	1585	2301	java/net/UnknownHostException
    //   1617	1623	2301	java/net/UnknownHostException
    //   1655	1667	2301	java/net/UnknownHostException
    //   1728	1734	2301	java/net/UnknownHostException
    //   1867	1879	2301	java/net/UnknownHostException
    //   1911	1930	2301	java/net/UnknownHostException
    //   1962	1972	2301	java/net/UnknownHostException
    //   2004	2016	2301	java/net/UnknownHostException
    //   2048	2057	2301	java/net/UnknownHostException
    //   2089	2097	2301	java/net/UnknownHostException
    //   2129	2137	2301	java/net/UnknownHostException
    //   2169	2178	2301	java/net/UnknownHostException
    //   2210	2218	2301	java/net/UnknownHostException
    //   2250	2258	2301	java/net/UnknownHostException
    //   2290	2298	2301	java/net/UnknownHostException
    //   2438	2448	2301	java/net/UnknownHostException
    //   2480	2487	2301	java/net/UnknownHostException
    //   2516	2535	2301	java/net/UnknownHostException
    //   2567	2582	2301	java/net/UnknownHostException
    //   2614	2626	2301	java/net/UnknownHostException
    //   2658	2671	2301	java/net/UnknownHostException
    //   2733	2745	2301	java/net/UnknownHostException
    //   2777	2783	2301	java/net/UnknownHostException
    //   2815	2828	2301	java/net/UnknownHostException
    //   2896	2904	2301	java/net/UnknownHostException
    //   2939	2951	2301	java/net/UnknownHostException
    //   2983	3011	2301	java/net/UnknownHostException
    //   3071	3077	2301	java/net/UnknownHostException
    //   3119	3137	2301	java/net/UnknownHostException
    //   3169	3176	2301	java/net/UnknownHostException
    //   3212	3223	2301	java/net/UnknownHostException
    //   3255	3266	2301	java/net/UnknownHostException
    //   3298	3312	2301	java/net/UnknownHostException
    //   3344	3351	2301	java/net/UnknownHostException
    //   3383	3390	2301	java/net/UnknownHostException
    //   3425	3434	2301	java/net/UnknownHostException
    //   3472	3477	2301	java/net/UnknownHostException
    //   3509	3518	2301	java/net/UnknownHostException
    //   3550	3562	2301	java/net/UnknownHostException
    //   3594	3602	2301	java/net/UnknownHostException
    //   3634	3640	2301	java/net/UnknownHostException
    //   3672	3684	2301	java/net/UnknownHostException
    //   3748	3757	2301	java/net/UnknownHostException
    //   3789	3801	2301	java/net/UnknownHostException
    //   3833	3841	2301	java/net/UnknownHostException
    //   3873	3880	2301	java/net/UnknownHostException
    //   4009	4019	2301	java/net/UnknownHostException
    //   4070	4082	2301	java/net/UnknownHostException
    //   4162	4173	2301	java/net/UnknownHostException
    //   2516	2535	2699	java/lang/Exception
    //   173	188	3883	java/lang/Exception
    //   220	228	3883	java/lang/Exception
    //   260	266	3883	java/lang/Exception
    //   298	315	3883	java/lang/Exception
    //   347	370	3883	java/lang/Exception
    //   402	410	3883	java/lang/Exception
    //   442	451	3883	java/lang/Exception
    //   483	489	3883	java/lang/Exception
    //   521	560	3883	java/lang/Exception
    //   599	607	3883	java/lang/Exception
    //   639	648	3883	java/lang/Exception
    //   680	687	3883	java/lang/Exception
    //   719	727	3883	java/lang/Exception
    //   759	765	3883	java/lang/Exception
    //   797	805	3883	java/lang/Exception
    //   837	845	3883	java/lang/Exception
    //   877	889	3883	java/lang/Exception
    //   921	930	3883	java/lang/Exception
    //   962	991	3883	java/lang/Exception
    //   1023	1028	3883	java/lang/Exception
    //   1060	1067	3883	java/lang/Exception
    //   1099	1119	3883	java/lang/Exception
    //   1151	1158	3883	java/lang/Exception
    //   1190	1204	3883	java/lang/Exception
    //   1236	1246	3883	java/lang/Exception
    //   1278	1287	3883	java/lang/Exception
    //   1319	1331	3883	java/lang/Exception
    //   1363	1371	3883	java/lang/Exception
    //   1403	1410	3883	java/lang/Exception
    //   1470	1482	3883	java/lang/Exception
    //   1514	1539	3883	java/lang/Exception
    //   1571	1585	3883	java/lang/Exception
    //   1617	1623	3883	java/lang/Exception
    //   1655	1667	3883	java/lang/Exception
    //   1728	1734	3883	java/lang/Exception
    //   1867	1879	3883	java/lang/Exception
    //   1911	1930	3883	java/lang/Exception
    //   1962	1972	3883	java/lang/Exception
    //   2004	2016	3883	java/lang/Exception
    //   2048	2057	3883	java/lang/Exception
    //   2089	2097	3883	java/lang/Exception
    //   2129	2137	3883	java/lang/Exception
    //   2169	2178	3883	java/lang/Exception
    //   2210	2218	3883	java/lang/Exception
    //   2250	2258	3883	java/lang/Exception
    //   2290	2298	3883	java/lang/Exception
    //   2438	2448	3883	java/lang/Exception
    //   2480	2487	3883	java/lang/Exception
    //   2567	2582	3883	java/lang/Exception
    //   2614	2626	3883	java/lang/Exception
    //   2658	2671	3883	java/lang/Exception
    //   2733	2745	3883	java/lang/Exception
    //   2777	2783	3883	java/lang/Exception
    //   2815	2828	3883	java/lang/Exception
    //   2896	2904	3883	java/lang/Exception
    //   2939	2951	3883	java/lang/Exception
    //   2983	3011	3883	java/lang/Exception
    //   3071	3077	3883	java/lang/Exception
    //   3119	3137	3883	java/lang/Exception
    //   3169	3176	3883	java/lang/Exception
    //   3212	3223	3883	java/lang/Exception
    //   3255	3266	3883	java/lang/Exception
    //   3298	3312	3883	java/lang/Exception
    //   3344	3351	3883	java/lang/Exception
    //   3383	3390	3883	java/lang/Exception
    //   3425	3434	3883	java/lang/Exception
    //   3472	3477	3883	java/lang/Exception
    //   3509	3518	3883	java/lang/Exception
    //   3550	3562	3883	java/lang/Exception
    //   3594	3602	3883	java/lang/Exception
    //   3634	3640	3883	java/lang/Exception
    //   3672	3684	3883	java/lang/Exception
    //   3748	3757	3883	java/lang/Exception
    //   3789	3801	3883	java/lang/Exception
    //   3833	3841	3883	java/lang/Exception
    //   3873	3880	3883	java/lang/Exception
    //   4009	4019	3883	java/lang/Exception
    //   4070	4082	3883	java/lang/Exception
    //   4162	4173	3883	java/lang/Exception
    //   173	188	4180	finally
    //   220	228	4180	finally
    //   260	266	4180	finally
    //   298	315	4180	finally
    //   347	370	4180	finally
    //   402	410	4180	finally
    //   442	451	4180	finally
    //   483	489	4180	finally
    //   521	560	4180	finally
    //   599	607	4180	finally
    //   639	648	4180	finally
    //   680	687	4180	finally
    //   719	727	4180	finally
    //   759	765	4180	finally
    //   797	805	4180	finally
    //   837	845	4180	finally
    //   877	889	4180	finally
    //   921	930	4180	finally
    //   962	991	4180	finally
    //   1023	1028	4180	finally
    //   1060	1067	4180	finally
    //   1099	1119	4180	finally
    //   1151	1158	4180	finally
    //   1190	1204	4180	finally
    //   1236	1246	4180	finally
    //   1278	1287	4180	finally
    //   1319	1331	4180	finally
    //   1363	1371	4180	finally
    //   1403	1410	4180	finally
    //   1470	1482	4180	finally
    //   1514	1539	4180	finally
    //   1571	1585	4180	finally
    //   1617	1623	4180	finally
    //   1655	1667	4180	finally
    //   1728	1734	4180	finally
    //   1751	1763	4180	finally
    //   1771	1778	4180	finally
    //   1786	1790	4180	finally
    //   1798	1809	4180	finally
    //   1867	1879	4180	finally
    //   1911	1930	4180	finally
    //   1962	1972	4180	finally
    //   2004	2016	4180	finally
    //   2048	2057	4180	finally
    //   2089	2097	4180	finally
    //   2129	2137	4180	finally
    //   2169	2178	4180	finally
    //   2210	2218	4180	finally
    //   2250	2258	4180	finally
    //   2290	2298	4180	finally
    //   2311	2323	4180	finally
    //   2331	2338	4180	finally
    //   2346	2350	4180	finally
    //   2358	2369	4180	finally
    //   2438	2448	4180	finally
    //   2480	2487	4180	finally
    //   2516	2535	4180	finally
    //   2567	2582	4180	finally
    //   2614	2626	4180	finally
    //   2658	2671	4180	finally
    //   2733	2745	4180	finally
    //   2777	2783	4180	finally
    //   2815	2828	4180	finally
    //   2896	2904	4180	finally
    //   2939	2951	4180	finally
    //   2983	3011	4180	finally
    //   3071	3077	4180	finally
    //   3119	3137	4180	finally
    //   3169	3176	4180	finally
    //   3212	3223	4180	finally
    //   3255	3266	4180	finally
    //   3298	3312	4180	finally
    //   3344	3351	4180	finally
    //   3383	3390	4180	finally
    //   3425	3434	4180	finally
    //   3472	3477	4180	finally
    //   3509	3518	4180	finally
    //   3550	3562	4180	finally
    //   3594	3602	4180	finally
    //   3634	3640	4180	finally
    //   3672	3684	4180	finally
    //   3748	3757	4180	finally
    //   3789	3801	4180	finally
    //   3833	3841	4180	finally
    //   3873	3880	4180	finally
    //   3893	3905	4180	finally
    //   3913	3920	4180	finally
    //   3928	3932	4180	finally
    //   3940	3951	4180	finally
    //   4009	4019	4180	finally
    //   4070	4082	4180	finally
    //   4162	4173	4180	finally
    //   1676	1684	4208	java/io/IOException
    //   1419	1427	4212	java/io/IOException
    //   2837	2845	4216	java/io/IOException
    //   3020	3028	4220	java/io/IOException
    //   2680	2688	4224	java/io/IOException
    //   1819	1824	4228	java/io/IOException
    //   2379	2384	4232	java/io/IOException
    //   3961	3966	4236	java/io/IOException
    //   4191	4196	4240	java/io/IOException
    //   3694	3699	4244	java/io/IOException
  }

  protected String getUserAgent()
  {
    return this.userAgent;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.download.DownloadConnection
 * JD-Core Version:    0.6.2
 */