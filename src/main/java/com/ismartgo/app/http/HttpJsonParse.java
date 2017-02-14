package com.ismartgo.app.http;

import android.text.TextUtils;
import com.ismartgo.app.bean.Goods;
import com.ismartgo.app.bean.Logistics;
import com.ismartgo.app.bean.Logistics.DataEntity;
import com.ismartgo.app.bean.Logistics.DataEntity.OrderTrackEntity;
import com.ismartgo.app.bean.Store;
import com.ismartgo.app.url.Url;
import com.umeng.common.message.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpJsonParse
{
  // ERROR //
  public static List<com.ismartgo.app.bean.FunctionMode> getHomeFunctionInfo(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 15	org/json/JSONObject
    //   5: dup
    //   6: aload_0
    //   7: invokespecial 18	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   10: astore_0
    //   11: aload_0
    //   12: ldc 20
    //   14: invokevirtual 24	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   17: astore_2
    //   18: aload_0
    //   19: ldc 26
    //   21: invokevirtual 30	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   24: sipush 10001
    //   27: if_icmpne +109 -> 136
    //   30: aload_2
    //   31: ifnull +105 -> 136
    //   34: aload_2
    //   35: ldc 32
    //   37: invokevirtual 36	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   40: astore_2
    //   41: aload_2
    //   42: ifnull +94 -> 136
    //   45: aload_2
    //   46: invokevirtual 42	org/json/JSONArray:length	()I
    //   49: ifle +87 -> 136
    //   52: new 44	java/util/ArrayList
    //   55: dup
    //   56: invokespecial 45	java/util/ArrayList:<init>	()V
    //   59: astore_0
    //   60: iconst_0
    //   61: istore_1
    //   62: iload_1
    //   63: aload_2
    //   64: invokevirtual 42	org/json/JSONArray:length	()I
    //   67: if_icmplt +5 -> 72
    //   70: aload_0
    //   71: areturn
    //   72: aload_2
    //   73: iload_1
    //   74: invokevirtual 48	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   77: astore_3
    //   78: aload_0
    //   79: new 50	com/ismartgo/app/bean/FunctionMode
    //   82: dup
    //   83: aload_3
    //   84: ldc 52
    //   86: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   89: aload_3
    //   90: ldc 58
    //   92: invokevirtual 30	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   95: aload_3
    //   96: ldc 60
    //   98: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   101: aload_3
    //   102: ldc 62
    //   104: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   107: invokespecial 65	com/ismartgo/app/bean/FunctionMode:<init>	(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V
    //   110: invokeinterface 71 2 0
    //   115: pop
    //   116: iload_1
    //   117: iconst_1
    //   118: iadd
    //   119: istore_1
    //   120: goto -58 -> 62
    //   123: astore_2
    //   124: aload_3
    //   125: astore_0
    //   126: aload_2
    //   127: invokevirtual 74	org/json/JSONException:printStackTrace	()V
    //   130: aload_0
    //   131: areturn
    //   132: astore_2
    //   133: goto -7 -> 126
    //   136: aconst_null
    //   137: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   2	30	123	org/json/JSONException
    //   34	41	123	org/json/JSONException
    //   45	60	123	org/json/JSONException
    //   62	70	132	org/json/JSONException
    //   72	116	132	org/json/JSONException
  }

  // ERROR //
  public static List<Store> getHomeStoreData(String paramString, android.content.Context paramContext, int paramInt)
  {
    // Byte code:
    //   0: new 44	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 45	java/util/ArrayList:<init>	()V
    //   7: astore 7
    //   9: ldc 82
    //   11: astore 6
    //   13: aload_1
    //   14: invokestatic 88	com/dodowaterfall/Helper:checkConnection	(Landroid/content/Context;)Z
    //   17: ifeq +9 -> 26
    //   20: aload_0
    //   21: invokestatic 91	com/dodowaterfall/Helper:getStringFromUrl	(Ljava/lang/String;)Ljava/lang/String;
    //   24: astore 6
    //   26: aload 6
    //   28: ifnull +48 -> 76
    //   31: new 15	org/json/JSONObject
    //   34: dup
    //   35: aload 6
    //   37: invokespecial 18	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   40: ldc 20
    //   42: invokevirtual 24	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   45: ldc 93
    //   47: invokevirtual 36	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   50: astore_1
    //   51: aload_1
    //   52: ifnull +24 -> 76
    //   55: aload_1
    //   56: invokevirtual 42	org/json/JSONArray:length	()I
    //   59: ifle +17 -> 76
    //   62: iconst_0
    //   63: istore_3
    //   64: aload_1
    //   65: invokevirtual 42	org/json/JSONArray:length	()I
    //   68: istore 4
    //   70: iload_3
    //   71: iload 4
    //   73: if_icmplt +23 -> 96
    //   76: aload 7
    //   78: areturn
    //   79: astore_0
    //   80: ldc 95
    //   82: aload_0
    //   83: invokevirtual 99	java/io/IOException:toString	()Ljava/lang/String;
    //   86: invokestatic 105	com/umeng/common/message/Log:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   89: aload_0
    //   90: invokevirtual 106	java/io/IOException:printStackTrace	()V
    //   93: aload 7
    //   95: areturn
    //   96: aload_1
    //   97: iload_3
    //   98: invokevirtual 48	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   101: astore 10
    //   103: new 108	java/util/LinkedList
    //   106: dup
    //   107: invokespecial 109	java/util/LinkedList:<init>	()V
    //   110: astore 6
    //   112: new 111	com/ismartgo/app/bean/Goods
    //   115: dup
    //   116: invokespecial 112	com/ismartgo/app/bean/Goods:<init>	()V
    //   119: astore 8
    //   121: new 114	com/ismartgo/app/bean/Store
    //   124: dup
    //   125: invokespecial 115	com/ismartgo/app/bean/Store:<init>	()V
    //   128: astore 9
    //   130: aload 10
    //   132: ldc 117
    //   134: invokevirtual 121	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   137: ifeq +427 -> 564
    //   140: iconst_0
    //   141: istore 4
    //   143: aload 8
    //   145: iload 4
    //   147: invokevirtual 125	com/ismartgo/app/bean/Goods:setGoodsId	(I)V
    //   150: new 127	java/lang/StringBuilder
    //   153: dup
    //   154: getstatic 133	com/ismartgo/app/url/Url:SERVER_URL2	Ljava/lang/String;
    //   157: invokestatic 139	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   160: invokespecial 140	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   163: astore 11
    //   165: aload 10
    //   167: ldc 142
    //   169: invokevirtual 121	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   172: ifeq +404 -> 576
    //   175: ldc 82
    //   177: astore_0
    //   178: aload 8
    //   180: aload 11
    //   182: aload_0
    //   183: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   186: invokevirtual 147	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   189: invokevirtual 150	com/ismartgo/app/bean/Goods:setGoodsLogo	(Ljava/lang/String;)V
    //   192: aload 10
    //   194: ldc 152
    //   196: invokevirtual 121	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   199: ifne +592 -> 791
    //   202: aload 10
    //   204: ldc 152
    //   206: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   209: ldc 82
    //   211: invokevirtual 155	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   214: ifeq +373 -> 587
    //   217: goto +574 -> 791
    //   220: aload 8
    //   222: iload 4
    //   224: invokevirtual 158	com/ismartgo/app/bean/Goods:setHeight	(I)V
    //   227: aload 10
    //   229: ldc 160
    //   231: invokevirtual 121	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   234: ifeq +375 -> 609
    //   237: ldc 82
    //   239: astore_0
    //   240: aload 8
    //   242: aload_0
    //   243: invokevirtual 163	com/ismartgo/app/bean/Goods:setGoodsName	(Ljava/lang/String;)V
    //   246: aload 10
    //   248: ldc 165
    //   250: invokevirtual 121	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   253: ifeq +367 -> 620
    //   256: ldc 82
    //   258: astore_0
    //   259: aload 8
    //   261: aload_0
    //   262: invokevirtual 168	com/ismartgo/app/bean/Goods:setGoodsEndDate	(Ljava/lang/String;)V
    //   265: aload 10
    //   267: ldc 170
    //   269: invokevirtual 121	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   272: ifeq +359 -> 631
    //   275: iconst_2
    //   276: istore 4
    //   278: aload 8
    //   280: iload 4
    //   282: invokevirtual 173	com/ismartgo/app/bean/Goods:setGoodsScan	(I)V
    //   285: aload 10
    //   287: ldc 175
    //   289: invokevirtual 121	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   292: ifeq +357 -> 649
    //   295: iconst_0
    //   296: istore 5
    //   298: aload 8
    //   300: iload 5
    //   302: invokevirtual 179	com/ismartgo/app/bean/Goods:setCollect	(Z)V
    //   305: aload 10
    //   307: ldc 181
    //   309: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   312: ldc 82
    //   314: invokevirtual 155	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   317: ifeq +350 -> 667
    //   320: ldc 183
    //   322: astore_0
    //   323: aload 8
    //   325: aload_0
    //   326: invokevirtual 186	com/ismartgo/app/bean/Goods:setH5Url	(Ljava/lang/String;)V
    //   329: aload 10
    //   331: ldc 188
    //   333: invokevirtual 121	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   336: ifeq +342 -> 678
    //   339: iconst_0
    //   340: istore 4
    //   342: aload 8
    //   344: iload 4
    //   346: invokevirtual 191	com/ismartgo/app/bean/Goods:setShopId	(I)V
    //   349: aload 10
    //   351: ldc 193
    //   353: invokevirtual 121	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   356: ifeq +334 -> 690
    //   359: ldc 82
    //   361: astore_0
    //   362: aload 8
    //   364: aload_0
    //   365: invokevirtual 196	com/ismartgo/app/bean/Goods:setGoodsDescribe	(Ljava/lang/String;)V
    //   368: aload 10
    //   370: ldc 188
    //   372: invokevirtual 121	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   375: ifeq +326 -> 701
    //   378: iconst_0
    //   379: istore 4
    //   381: aload 9
    //   383: iload 4
    //   385: invokevirtual 197	com/ismartgo/app/bean/Store:setShopId	(I)V
    //   388: new 127	java/lang/StringBuilder
    //   391: dup
    //   392: getstatic 133	com/ismartgo/app/url/Url:SERVER_URL2	Ljava/lang/String;
    //   395: invokestatic 139	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   398: invokespecial 140	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   401: astore 11
    //   403: aload 10
    //   405: ldc 199
    //   407: invokevirtual 121	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   410: ifeq +303 -> 713
    //   413: ldc 82
    //   415: astore_0
    //   416: aload 9
    //   418: aload 11
    //   420: aload_0
    //   421: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   424: invokevirtual 147	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   427: invokevirtual 202	com/ismartgo/app/bean/Store:setShopLogo	(Ljava/lang/String;)V
    //   430: aload 10
    //   432: ldc 204
    //   434: invokevirtual 121	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   437: ifeq +287 -> 724
    //   440: ldc 82
    //   442: astore_0
    //   443: aload 9
    //   445: aload_0
    //   446: invokevirtual 207	com/ismartgo/app/bean/Store:setShopName	(Ljava/lang/String;)V
    //   449: aload 10
    //   451: ldc 209
    //   453: invokevirtual 121	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   456: ifeq +279 -> 735
    //   459: ldc 82
    //   461: astore_0
    //   462: aload 9
    //   464: aload_0
    //   465: invokevirtual 212	com/ismartgo/app/bean/Store:setShopAddress	(Ljava/lang/String;)V
    //   468: aload 9
    //   470: aload 10
    //   472: ldc 214
    //   474: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   477: invokestatic 220	java/lang/Double:parseDouble	(Ljava/lang/String;)D
    //   480: invokevirtual 224	com/ismartgo/app/bean/Store:setLon	(D)V
    //   483: aload 9
    //   485: aload 10
    //   487: ldc 226
    //   489: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   492: invokestatic 220	java/lang/Double:parseDouble	(Ljava/lang/String;)D
    //   495: invokevirtual 229	com/ismartgo/app/bean/Store:setLat	(D)V
    //   498: aload 10
    //   500: ldc 231
    //   502: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   505: ldc 233
    //   507: invokevirtual 237	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   510: astore_0
    //   511: aload 10
    //   513: ldc 231
    //   515: invokevirtual 121	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   518: ifeq +228 -> 746
    //   521: ldc 82
    //   523: astore_0
    //   524: aload 9
    //   526: aload_0
    //   527: invokevirtual 240	com/ismartgo/app/bean/Store:setDistance	(Ljava/lang/String;)V
    //   530: aload 6
    //   532: aload 8
    //   534: invokeinterface 71 2 0
    //   539: pop
    //   540: aload 9
    //   542: aload 6
    //   544: invokevirtual 244	com/ismartgo/app/bean/Store:setGoods_list	(Ljava/util/List;)V
    //   547: aload 7
    //   549: aload 9
    //   551: invokeinterface 71 2 0
    //   556: pop
    //   557: iload_3
    //   558: iconst_1
    //   559: iadd
    //   560: istore_3
    //   561: goto -497 -> 64
    //   564: aload 10
    //   566: ldc 117
    //   568: invokevirtual 30	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   571: istore 4
    //   573: goto -430 -> 143
    //   576: aload 10
    //   578: ldc 142
    //   580: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   583: astore_0
    //   584: goto -406 -> 178
    //   587: aload 10
    //   589: ldc 152
    //   591: invokevirtual 30	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   594: iload_2
    //   595: imul
    //   596: aload 10
    //   598: ldc 246
    //   600: invokevirtual 30	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   603: idiv
    //   604: istore 4
    //   606: goto -386 -> 220
    //   609: aload 10
    //   611: ldc 160
    //   613: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   616: astore_0
    //   617: goto -377 -> 240
    //   620: aload 10
    //   622: ldc 165
    //   624: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   627: astore_0
    //   628: goto -369 -> 259
    //   631: aload 10
    //   633: ldc 170
    //   635: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   638: invokestatic 251	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   641: invokevirtual 254	java/lang/Integer:intValue	()I
    //   644: istore 4
    //   646: goto -368 -> 278
    //   649: aload 10
    //   651: ldc 175
    //   653: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   656: ldc_w 256
    //   659: invokevirtual 259	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   662: istore 5
    //   664: goto -366 -> 298
    //   667: aload 10
    //   669: ldc 181
    //   671: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   674: astore_0
    //   675: goto -352 -> 323
    //   678: aload 10
    //   680: ldc 188
    //   682: invokevirtual 30	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   685: istore 4
    //   687: goto -345 -> 342
    //   690: aload 10
    //   692: ldc 193
    //   694: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   697: astore_0
    //   698: goto -336 -> 362
    //   701: aload 10
    //   703: ldc 188
    //   705: invokevirtual 30	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   708: istore 4
    //   710: goto -329 -> 381
    //   713: aload 10
    //   715: ldc 199
    //   717: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   720: astore_0
    //   721: goto -305 -> 416
    //   724: aload 10
    //   726: ldc 204
    //   728: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   731: astore_0
    //   732: goto -289 -> 443
    //   735: aload 10
    //   737: ldc 209
    //   739: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   742: astore_0
    //   743: goto -281 -> 462
    //   746: new 127	java/lang/StringBuilder
    //   749: dup
    //   750: aload_0
    //   751: iconst_0
    //   752: aaload
    //   753: invokestatic 139	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   756: invokespecial 140	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   759: ldc_w 261
    //   762: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   765: aload_0
    //   766: iconst_1
    //   767: aaload
    //   768: iconst_0
    //   769: iconst_1
    //   770: invokevirtual 265	java/lang/String:substring	(II)Ljava/lang/String;
    //   773: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   776: invokevirtual 147	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   779: astore_0
    //   780: goto -256 -> 524
    //   783: astore_0
    //   784: aload_0
    //   785: invokevirtual 74	org/json/JSONException:printStackTrace	()V
    //   788: aload 7
    //   790: areturn
    //   791: sipush 300
    //   794: istore 4
    //   796: goto -576 -> 220
    //
    // Exception table:
    //   from	to	target	type
    //   20	26	79	java/io/IOException
    //   31	51	783	org/json/JSONException
    //   55	62	783	org/json/JSONException
    //   64	70	783	org/json/JSONException
    //   96	140	783	org/json/JSONException
    //   143	175	783	org/json/JSONException
    //   178	217	783	org/json/JSONException
    //   220	237	783	org/json/JSONException
    //   240	256	783	org/json/JSONException
    //   259	275	783	org/json/JSONException
    //   278	295	783	org/json/JSONException
    //   298	320	783	org/json/JSONException
    //   323	339	783	org/json/JSONException
    //   342	359	783	org/json/JSONException
    //   362	378	783	org/json/JSONException
    //   381	413	783	org/json/JSONException
    //   416	440	783	org/json/JSONException
    //   443	459	783	org/json/JSONException
    //   462	521	783	org/json/JSONException
    //   524	557	783	org/json/JSONException
    //   564	573	783	org/json/JSONException
    //   576	584	783	org/json/JSONException
    //   587	606	783	org/json/JSONException
    //   609	617	783	org/json/JSONException
    //   620	628	783	org/json/JSONException
    //   631	646	783	org/json/JSONException
    //   649	664	783	org/json/JSONException
    //   667	675	783	org/json/JSONException
    //   678	687	783	org/json/JSONException
    //   690	698	783	org/json/JSONException
    //   701	710	783	org/json/JSONException
    //   713	721	783	org/json/JSONException
    //   724	732	783	org/json/JSONException
    //   735	743	783	org/json/JSONException
    //   746	780	783	org/json/JSONException
  }

  public static List<Store> getMyCollectData(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramString != null);
    while (true)
    {
      try
      {
        paramString = new JSONObject(paramString).getJSONObject("data");
        if (paramString.isNull("myCollectList"))
          return localArrayList;
        JSONArray localJSONArray = paramString.getJSONArray("myCollectList");
        if ((localJSONArray != null) && (localJSONArray.length() > 0))
        {
          int i = 0;
          if (i < localJSONArray.length())
          {
            JSONObject localJSONObject = localJSONArray.getJSONObject(i);
            LinkedList localLinkedList = new LinkedList();
            Goods localGoods = new Goods();
            Store localStore = new Store();
            if (localJSONObject.isNull("goodsId"))
            {
              j = 0;
              localGoods.setGoodsId(j);
              StringBuilder localStringBuilder = new StringBuilder(String.valueOf(Url.SERVER_URL2));
              if (!localJSONObject.isNull("goodsLogo"))
                continue;
              paramString = "";
              localGoods.setGoodsLogo(paramString);
              localGoods.setHeight(localJSONObject.getInt("imgHeight"));
              localGoods.setWidth(localJSONObject.getInt("imgWidth"));
              if (!localJSONObject.isNull("goodsName"))
                continue;
              paramString = "";
              localGoods.setGoodsName(paramString);
              if (!localJSONObject.isNull("goodsEndDate"))
                continue;
              paramString = "";
              localGoods.setGoodsEndDate(paramString);
              if (!localJSONObject.isNull("goodsScan"))
                continue;
              j = 2;
              localGoods.setGoodsScan(j);
              if (!localJSONObject.isNull("isCollect"))
                continue;
              bool = false;
              localGoods.setCollect(bool);
              if (localJSONObject.isNull("h5url"))
                break label703;
              if (!localJSONObject.getString("h5url").equals(""))
                continue;
              break label703;
              localGoods.setH5Url(paramString);
              if (!localJSONObject.isNull("shopId"))
                continue;
              j = 0;
              localGoods.setShopId(j);
              if (!localJSONObject.isNull("promotion"))
                continue;
              paramString = "";
              localGoods.setGoodsDescribe(paramString);
              if (!localJSONObject.isNull("shopId"))
                continue;
              j = 0;
              localStore.setShopId(j);
              localStringBuilder = new StringBuilder(String.valueOf(Url.SERVER_URL2));
              if (!localJSONObject.isNull("shopLogo"))
                continue;
              paramString = "";
              localStore.setShopLogo(paramString);
              if (!localJSONObject.isNull("shopName"))
                continue;
              paramString = "";
              localStore.setShopName(paramString);
              if (!localJSONObject.isNull("address"))
                continue;
              paramString = "";
              localStore.setShopAddress(paramString);
              localStore.setLon(Double.parseDouble(localJSONObject.getString("lon")));
              localStore.setLat(Double.parseDouble(localJSONObject.getString("lat")));
              localStore.setReatilId(localJSONObject.getInt("retailId"));
              localStore.setReatilName(localJSONObject.getString("retailName"));
              localStore.setDistance(localJSONObject.getString("distance"));
              localLinkedList.add(localGoods);
              localStore.setGoods_list(localLinkedList);
              localArrayList.add(localStore);
              i += 1;
              continue;
            }
            int j = localJSONObject.getInt("goodsId");
            continue;
            paramString = localJSONObject.getString("goodsLogo");
            continue;
            paramString = localJSONObject.getString("goodsName");
            continue;
            paramString = localJSONObject.getString("goodsEndDate");
            continue;
            j = Integer.valueOf(localJSONObject.getString("goodsScan")).intValue();
            continue;
            boolean bool = localJSONObject.getString("isCollect").equalsIgnoreCase("Y");
            continue;
            paramString = localJSONObject.getString("h5url");
            continue;
            j = localJSONObject.getInt("shopId");
            continue;
            paramString = localJSONObject.getString("promotion");
            continue;
            j = localJSONObject.getInt("shopId");
            continue;
            paramString = localJSONObject.getString("shopLogo");
            continue;
            paramString = localJSONObject.getString("shopName");
            continue;
            paramString = localJSONObject.getString("address");
            continue;
          }
        }
      }
      catch (JSONException paramString)
      {
        paramString.printStackTrace();
      }
      return localArrayList;
      label703: paramString = "https://";
    }
  }

  public static List<Store> getPromotionInStoreData(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramString != null)
      try
      {
        paramString = new JSONObject(paramString).getJSONObject("data");
        if (paramString.isNull("goodsList"))
          return localArrayList;
        JSONArray localJSONArray = paramString.getJSONArray("goodsList");
        if ((localJSONArray != null) && (localJSONArray.length() > 0))
        {
          int i = 0;
          if (i < localJSONArray.length())
          {
            JSONObject localJSONObject = localJSONArray.getJSONObject(i);
            LinkedList localLinkedList = new LinkedList();
            Goods localGoods = new Goods();
            Store localStore = new Store();
            int j;
            label118: boolean bool;
            if (localJSONObject.isNull("goodsId"))
            {
              j = 0;
              localGoods.setGoodsId(j);
              StringBuilder localStringBuilder = new StringBuilder(String.valueOf(Url.SERVER_URL2));
              if (!localJSONObject.isNull("goodsLogo"))
                break label516;
              paramString = "";
              label152: localGoods.setGoodsLogo(paramString);
              localGoods.setHeight(localJSONObject.getInt("imgHeight"));
              localGoods.setWidth(localJSONObject.getInt("imgWidth"));
              if (!localJSONObject.isNull("goodsName"))
                break label527;
              paramString = "";
              label203: localGoods.setGoodsName(paramString);
              if (!localJSONObject.isNull("goodsEndDate"))
                break label538;
              paramString = "";
              label222: localGoods.setGoodsEndDate(paramString);
              if (!localJSONObject.isNull("goodsScan"))
                break label549;
              j = 2;
              label240: localGoods.setGoodsScan(j);
              if (!localJSONObject.isNull("isCollect"))
                break label566;
              bool = false;
              label258: localGoods.setCollect(bool);
              if (!localJSONObject.getString("h5Url").equals(""))
                break label583;
              paramString = "https://";
              label282: localGoods.setH5Url(paramString);
              if (!localJSONObject.isNull("shopId"))
                break label594;
              j = 0;
              label300: localGoods.setShopId(j);
              localGoods.setGoodsDescribe(localJSONObject.getString("promotion"));
              if (!localJSONObject.isNull("shopId"))
                break label605;
              j = 0;
              label330: localStore.setShopId(j);
              localStringBuilder = new StringBuilder(String.valueOf(Url.ADS_URL));
              if (!localJSONObject.isNull("shopLogo"))
                break label616;
              paramString = "";
              label364: localStore.setShopLogo(paramString);
              if (!localJSONObject.isNull("shopName"))
                break label627;
              paramString = "";
              label391: localStore.setShopName(paramString);
              if (!localJSONObject.isNull("address"))
                break label638;
            }
            label516: label527: label538: label549: label566: label583: label594: label605: label616: label627: label638: for (paramString = ""; ; paramString = localJSONObject.getString("address"))
            {
              localStore.setShopAddress(paramString);
              localStore.setLon(Double.parseDouble(localJSONObject.getString("lon")));
              localStore.setLat(Double.parseDouble(localJSONObject.getString("lat")));
              localStore.setReatilName(localJSONObject.getString("retailName"));
              localStore.setDistance(localJSONObject.getString("distance"));
              localLinkedList.add(localGoods);
              localStore.setGoods_list(localLinkedList);
              localArrayList.add(localStore);
              i += 1;
              break;
              j = localJSONObject.getInt("goodsId");
              break label118;
              paramString = localJSONObject.getString("goodsLogo");
              break label152;
              paramString = localJSONObject.getString("goodsName");
              break label203;
              paramString = localJSONObject.getString("goodsEndDate");
              break label222;
              j = Integer.valueOf(localJSONObject.getString("goodsScan")).intValue();
              break label240;
              bool = localJSONObject.getString("isCollect").equalsIgnoreCase("Y");
              break label258;
              paramString = localJSONObject.getString("h5Url");
              break label282;
              j = localJSONObject.getInt("shopId");
              break label300;
              j = localJSONObject.getInt("shopId");
              break label330;
              paramString = localJSONObject.getString("shopLogo");
              break label364;
              paramString = localJSONObject.getString("shopName");
              break label391;
            }
          }
        }
      }
      catch (JSONException paramString)
      {
        Log.d("hahaha", "解析json异常: " + paramString.getMessage());
        paramString.printStackTrace();
      }
    return localArrayList;
  }

  public static List<Store> getPromotionStoreData(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramString != null)
      try
      {
        JSONObject localJSONObject = new JSONObject(paramString).getJSONObject("data").getJSONObject("shop");
        if (localJSONObject.isNull("goodsList"))
        {
          Log.e("Test", "goodsList为空");
          return localArrayList;
        }
        JSONArray localJSONArray = localJSONObject.getJSONArray("goodsList");
        if ((localJSONArray != null) && (localJSONArray.length() > 0))
        {
          int i = 0;
          if (i < localJSONArray.length())
          {
            Object localObject = localJSONArray.getJSONObject(i);
            LinkedList localLinkedList = new LinkedList();
            Goods localGoods = new Goods();
            Store localStore = new Store();
            int j;
            label136: label170: label221: label240: boolean bool;
            if (((JSONObject)localObject).isNull("goodsId"))
            {
              j = 0;
              localGoods.setGoodsId(j);
              StringBuilder localStringBuilder = new StringBuilder(String.valueOf(Url.SERVER_URL2));
              if (!((JSONObject)localObject).isNull("goodsLogo"))
                break label516;
              paramString = "";
              localGoods.setGoodsLogo(paramString);
              localGoods.setHeight(((JSONObject)localObject).getInt("imgHeight"));
              localGoods.setWidth(((JSONObject)localObject).getInt("imgWidth"));
              if (!((JSONObject)localObject).isNull("goodsName"))
                break label527;
              paramString = "";
              localGoods.setGoodsName(paramString);
              if (!((JSONObject)localObject).isNull("goodsEndDate"))
                break label538;
              paramString = "";
              localGoods.setGoodsEndDate(paramString);
              if (!((JSONObject)localObject).isNull("goodsScan"))
                break label549;
              j = 2;
              label258: localGoods.setGoodsScan(j);
              if (!((JSONObject)localObject).isNull("isCollect"))
                break label566;
              bool = false;
              label276: localGoods.setCollect(bool);
              if (!((JSONObject)localObject).isNull("h5Url"))
                break label583;
              paramString = "https://";
              label295: localGoods.setH5Url(paramString);
              if (!((JSONObject)localObject).isNull("shopId"))
                break label594;
              j = 0;
              label313: localGoods.setShopId(j);
              localGoods.setGoodsDescribe(((JSONObject)localObject).getString("promotion"));
              if (!localJSONObject.isNull("shopId"))
                break label605;
              j = 0;
              label343: localStore.setShopId(j);
              localObject = new StringBuilder(String.valueOf(Url.ADS_URL));
              if (!localJSONObject.isNull("shopLogo"))
                break label616;
              paramString = "";
              label377: localStore.setShopLogo(paramString);
              if (!localJSONObject.isNull("shopName"))
                break label627;
              paramString = "";
              label404: localStore.setShopName(paramString);
              if (!localJSONObject.isNull("address"))
                break label638;
            }
            label516: label527: label538: label549: label566: label583: label594: label605: label616: label627: label638: for (paramString = ""; ; paramString = localJSONObject.getString("address"))
            {
              localStore.setShopAddress(paramString);
              localStore.setLon(Double.parseDouble(localJSONObject.getString("lon")));
              localStore.setLat(Double.parseDouble(localJSONObject.getString("lat")));
              localStore.setDistance(localJSONObject.getString("distance"));
              localLinkedList.add(localGoods);
              localStore.setGoods_list(localLinkedList);
              localArrayList.add(localStore);
              i += 1;
              break;
              j = ((JSONObject)localObject).getInt("goodsId");
              break label136;
              paramString = ((JSONObject)localObject).getString("goodsLogo");
              break label170;
              paramString = ((JSONObject)localObject).getString("goodsName");
              break label221;
              paramString = ((JSONObject)localObject).getString("goodsEndDate");
              break label240;
              j = Integer.valueOf(((JSONObject)localObject).getString("goodsScan")).intValue();
              break label258;
              bool = ((JSONObject)localObject).getString("isCollect").equalsIgnoreCase("Y");
              break label276;
              paramString = ((JSONObject)localObject).getString("h5Url");
              break label295;
              j = ((JSONObject)localObject).getInt("shopId");
              break label313;
              j = localJSONObject.getInt("shopId");
              break label343;
              paramString = localJSONObject.getString("shopLogo");
              break label377;
              paramString = localJSONObject.getString("shopName");
              break label404;
            }
          }
        }
      }
      catch (JSONException paramString)
      {
        Log.e("Test", "促销优惠数据解析异常: " + paramString.getMessage());
        paramString.printStackTrace();
      }
    return localArrayList;
  }

  // ERROR //
  public static com.ismartgo.app.bean.MyReceiptInfo getReceiptInfo(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 15	org/json/JSONObject
    //   5: dup
    //   6: aload_0
    //   7: invokespecial 18	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   10: astore_0
    //   11: aload_0
    //   12: ldc 26
    //   14: invokevirtual 30	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   17: istore_1
    //   18: aload_0
    //   19: ldc 20
    //   21: invokevirtual 24	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   24: astore 4
    //   26: iload_1
    //   27: sipush 10001
    //   30: if_icmpne +279 -> 309
    //   33: aload 4
    //   35: ifnull +274 -> 309
    //   38: aload 4
    //   40: ldc 32
    //   42: invokevirtual 36	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   45: astore 5
    //   47: aload 5
    //   49: ifnull +260 -> 309
    //   52: aload 5
    //   54: invokevirtual 42	org/json/JSONArray:length	()I
    //   57: ifle +252 -> 309
    //   60: new 312	com/ismartgo/app/bean/MyReceiptInfo
    //   63: dup
    //   64: invokespecial 313	com/ismartgo/app/bean/MyReceiptInfo:<init>	()V
    //   67: astore_2
    //   68: new 44	java/util/ArrayList
    //   71: dup
    //   72: invokespecial 45	java/util/ArrayList:<init>	()V
    //   75: astore_3
    //   76: new 44	java/util/ArrayList
    //   79: dup
    //   80: invokespecial 45	java/util/ArrayList:<init>	()V
    //   83: astore_0
    //   84: iconst_0
    //   85: istore_1
    //   86: iload_1
    //   87: aload 5
    //   89: invokevirtual 42	org/json/JSONArray:length	()I
    //   92: if_icmplt +34 -> 126
    //   95: aload_2
    //   96: aload_3
    //   97: invokevirtual 316	com/ismartgo/app/bean/MyReceiptInfo:setItems	(Ljava/util/List;)V
    //   100: aload 4
    //   102: ldc_w 318
    //   105: invokevirtual 36	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   108: astore_3
    //   109: iconst_0
    //   110: istore_1
    //   111: iload_1
    //   112: aload_3
    //   113: invokevirtual 42	org/json/JSONArray:length	()I
    //   116: if_icmplt +121 -> 237
    //   119: aload_2
    //   120: aload_0
    //   121: invokevirtual 321	com/ismartgo/app/bean/MyReceiptInfo:setMonthinfos	(Ljava/util/List;)V
    //   124: aload_2
    //   125: areturn
    //   126: aload 5
    //   128: iload_1
    //   129: invokevirtual 48	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   132: astore 6
    //   134: aload 6
    //   136: ldc_w 323
    //   139: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   142: astore 7
    //   144: aload 6
    //   146: ldc_w 325
    //   149: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   152: astore 8
    //   154: aload 6
    //   156: ldc_w 327
    //   159: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   162: astore 9
    //   164: aload_3
    //   165: new 329	com/ismartgo/app/bean/ReceiptItems
    //   168: dup
    //   169: aload 7
    //   171: aload 6
    //   173: ldc_w 331
    //   176: invokevirtual 334	org/json/JSONObject:getDouble	(Ljava/lang/String;)D
    //   179: aload 6
    //   181: ldc 58
    //   183: invokevirtual 30	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   186: aload 6
    //   188: ldc 60
    //   190: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   193: aload 6
    //   195: ldc_w 336
    //   198: invokevirtual 334	org/json/JSONObject:getDouble	(Ljava/lang/String;)D
    //   201: aload 6
    //   203: ldc_w 338
    //   206: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   209: aload 6
    //   211: ldc_w 340
    //   214: invokevirtual 30	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   217: aload 9
    //   219: aload 8
    //   221: invokespecial 343	com/ismartgo/app/bean/ReceiptItems:<init>	(Ljava/lang/String;DILjava/lang/String;DLjava/lang/String;ILjava/lang/String;Ljava/lang/String;)V
    //   224: invokeinterface 71 2 0
    //   229: pop
    //   230: iload_1
    //   231: iconst_1
    //   232: iadd
    //   233: istore_1
    //   234: goto -148 -> 86
    //   237: aload_3
    //   238: iload_1
    //   239: invokevirtual 48	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   242: astore 4
    //   244: aload_0
    //   245: new 345	com/ismartgo/app/bean/ReceiptMonthInfo
    //   248: dup
    //   249: aload 4
    //   251: ldc 58
    //   253: invokevirtual 30	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   256: aload 4
    //   258: ldc_w 336
    //   261: invokevirtual 334	org/json/JSONObject:getDouble	(Ljava/lang/String;)D
    //   264: aload 4
    //   266: ldc_w 338
    //   269: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   272: aload 4
    //   274: ldc_w 347
    //   277: invokevirtual 30	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   280: invokespecial 350	com/ismartgo/app/bean/ReceiptMonthInfo:<init>	(IDLjava/lang/String;I)V
    //   283: invokeinterface 71 2 0
    //   288: pop
    //   289: iload_1
    //   290: iconst_1
    //   291: iadd
    //   292: istore_1
    //   293: goto -182 -> 111
    //   296: astore_0
    //   297: aload_3
    //   298: astore_2
    //   299: aload_0
    //   300: invokevirtual 74	org/json/JSONException:printStackTrace	()V
    //   303: aload_2
    //   304: areturn
    //   305: astore_0
    //   306: goto -7 -> 299
    //   309: aconst_null
    //   310: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   2	26	296	org/json/JSONException
    //   38	47	296	org/json/JSONException
    //   52	68	296	org/json/JSONException
    //   68	84	305	org/json/JSONException
    //   86	109	305	org/json/JSONException
    //   111	124	305	org/json/JSONException
    //   126	230	305	org/json/JSONException
    //   237	289	305	org/json/JSONException
  }

  // ERROR //
  public static com.ismartgo.app.bean.ReceiptStatisticInfo getReceiptStatistic(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: new 15	org/json/JSONObject
    //   6: dup
    //   7: aload_0
    //   8: invokespecial 18	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   11: astore_0
    //   12: aload_0
    //   13: ldc 26
    //   15: invokevirtual 30	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   18: sipush 10001
    //   21: if_icmpne +190 -> 211
    //   24: aload_0
    //   25: ldc 20
    //   27: invokevirtual 24	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   30: astore_0
    //   31: aload_0
    //   32: ldc 32
    //   34: invokevirtual 36	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   37: astore 5
    //   39: aload_0
    //   40: ldc_w 354
    //   43: invokevirtual 334	org/json/JSONObject:getDouble	(Ljava/lang/String;)D
    //   46: dstore_1
    //   47: aload_0
    //   48: ldc_w 356
    //   51: invokevirtual 30	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   54: istore_3
    //   55: aload 5
    //   57: ifnull +154 -> 211
    //   60: aload 5
    //   62: invokevirtual 42	org/json/JSONArray:length	()I
    //   65: ifle +146 -> 211
    //   68: new 358	com/ismartgo/app/bean/ReceiptStatisticInfo
    //   71: dup
    //   72: invokespecial 359	com/ismartgo/app/bean/ReceiptStatisticInfo:<init>	()V
    //   75: astore_0
    //   76: aload_0
    //   77: dload_1
    //   78: invokevirtual 362	com/ismartgo/app/bean/ReceiptStatisticInfo:setTotalmoney	(D)V
    //   81: aload_0
    //   82: iload_3
    //   83: invokevirtual 365	com/ismartgo/app/bean/ReceiptStatisticInfo:setTotaltimes	(I)V
    //   86: new 44	java/util/ArrayList
    //   89: dup
    //   90: invokespecial 45	java/util/ArrayList:<init>	()V
    //   93: astore 4
    //   95: iconst_0
    //   96: istore_3
    //   97: iload_3
    //   98: aload 5
    //   100: invokevirtual 42	org/json/JSONArray:length	()I
    //   103: if_icmplt +11 -> 114
    //   106: aload_0
    //   107: aload 4
    //   109: invokevirtual 366	com/ismartgo/app/bean/ReceiptStatisticInfo:setItems	(Ljava/util/List;)V
    //   112: aload_0
    //   113: areturn
    //   114: aload 5
    //   116: iload_3
    //   117: invokevirtual 48	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   120: astore 6
    //   122: aload_0
    //   123: invokevirtual 370	java/lang/Object:getClass	()Ljava/lang/Class;
    //   126: pop
    //   127: new 372	com/ismartgo/app/bean/ReceiptStatisticInfo$Items
    //   130: dup
    //   131: aload_0
    //   132: invokespecial 375	com/ismartgo/app/bean/ReceiptStatisticInfo$Items:<init>	(Lcom/ismartgo/app/bean/ReceiptStatisticInfo;)V
    //   135: astore 7
    //   137: aload 7
    //   139: aload 6
    //   141: ldc 58
    //   143: invokevirtual 30	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   146: invokevirtual 378	com/ismartgo/app/bean/ReceiptStatisticInfo$Items:setId	(I)V
    //   149: aload 7
    //   151: aload 6
    //   153: ldc_w 336
    //   156: invokevirtual 334	org/json/JSONObject:getDouble	(Ljava/lang/String;)D
    //   159: invokevirtual 381	com/ismartgo/app/bean/ReceiptStatisticInfo$Items:setMoney	(D)V
    //   162: aload 7
    //   164: aload 6
    //   166: ldc_w 347
    //   169: invokevirtual 30	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   172: invokevirtual 384	com/ismartgo/app/bean/ReceiptStatisticInfo$Items:setTimes	(I)V
    //   175: aload 4
    //   177: aload 7
    //   179: invokeinterface 71 2 0
    //   184: pop
    //   185: iload_3
    //   186: iconst_1
    //   187: iadd
    //   188: istore_3
    //   189: goto -92 -> 97
    //   192: astore_0
    //   193: aload_0
    //   194: invokevirtual 74	org/json/JSONException:printStackTrace	()V
    //   197: aload 4
    //   199: areturn
    //   200: astore 5
    //   202: aload_0
    //   203: astore 4
    //   205: aload 5
    //   207: astore_0
    //   208: goto -15 -> 193
    //   211: aconst_null
    //   212: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   3	55	192	org/json/JSONException
    //   60	76	192	org/json/JSONException
    //   76	95	200	org/json/JSONException
    //   97	112	200	org/json/JSONException
    //   114	185	200	org/json/JSONException
  }

  public static List<Store> getScanStoreGoods(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramString != null);
    while (true)
    {
      int i;
      JSONObject localJSONObject;
      try
      {
        JSONArray localJSONArray = new JSONObject(paramString).getJSONObject("data").getJSONArray("goodsScanList");
        if ((localJSONArray == null) || (localJSONArray.length() <= 0))
          break label741;
        i = 0;
        if (i >= localJSONArray.length())
          return localArrayList;
        localJSONObject = localJSONArray.getJSONObject(i);
        Goods localGoods = new Goods();
        Store localStore = new Store();
        StringBuilder localStringBuilder;
        if (localJSONObject.isNull("goodsId"))
        {
          j = 0;
          localGoods.setGoodsId(j);
          localStringBuilder = new StringBuilder(String.valueOf(Url.SERVER_URL2));
          if (localJSONObject.isNull("goodsLogo"))
          {
            paramString = "";
            localGoods.setGoodsLogo(paramString);
            if (!localJSONObject.isNull("goodsName"))
              continue;
            paramString = "";
            localGoods.setGoodsName(paramString);
            if (!localJSONObject.isNull("goodsEndDate"))
              continue;
            paramString = "";
            localGoods.setGoodsEndDate(paramString);
            if (!localJSONObject.isNull("goodsScan"))
              continue;
            j = 2;
            localGoods.setGoodsScan(j);
            if (!localJSONObject.isNull("isCollect"))
              continue;
            bool = false;
            localGoods.setCollect(bool);
            if (!localJSONObject.getString("h5Url").equals(""))
              continue;
            paramString = "https://";
            localGoods.setH5Url(paramString);
            localGoods.setBeannumber(Integer.valueOf(localJSONObject.getString("beannumber")).intValue());
            if (!localJSONObject.isNull("shopId"))
              continue;
            j = 0;
            localGoods.setShopId(j);
            if (!localJSONObject.isNull("isScan"))
              continue;
            bool = false;
            localGoods.setScaned(bool);
            if ((localJSONObject.get("imgHeight") instanceof Integer))
              continue;
            break label744;
          }
        }
        else
        {
          j = localJSONObject.getInt("goodsId");
          continue;
        }
        paramString = localJSONObject.getString("goodsLogo");
        continue;
        paramString = localJSONObject.getString("goodsName");
        continue;
        paramString = localJSONObject.getString("goodsEndDate");
        continue;
        j = Integer.valueOf(localJSONObject.getString("goodsScan")).intValue();
        continue;
        bool = localJSONObject.getString("isCollect").equalsIgnoreCase("Y");
        continue;
        paramString = localJSONObject.getString("h5Url");
        continue;
        j = localJSONObject.getInt("shopId");
        continue;
        if (!localJSONObject.getString("isScan").equals("Y"))
          break label751;
        bool = true;
        continue;
        localGoods.setHeight(localJSONObject.getInt("imgHeight"));
        localGoods.setWidth(localJSONObject.getInt("imgWidth"));
        if (localJSONObject.isNull("promotion"))
        {
          paramString = "";
          localGoods.setGoodsDescribe(paramString);
          if (!localJSONObject.isNull("shopId"))
            break label697;
          j = 0;
          localStore.setShopId(j);
          localStringBuilder = new StringBuilder(String.valueOf(Url.ADS_URL));
          if (!localJSONObject.isNull("shopLogo"))
            break label708;
          paramString = "";
          localStore.setShopLogo(paramString);
          if (!localJSONObject.isNull("shopName"))
            break label719;
          paramString = "";
          localStore.setShopName(paramString);
          if (!localJSONObject.isNull("address"))
            break label730;
          paramString = "";
          localStore.setShopAddress(paramString);
          localStore.setDistance(localJSONObject.getString("distance"));
          localStore.setLat(localJSONObject.getDouble("lat"));
          localStore.setLon(localJSONObject.getDouble("lon"));
          paramString = new ArrayList();
          paramString.add(localGoods);
          localStore.setGoods_list(paramString);
          localArrayList.add(localStore);
        }
      }
      catch (JSONException paramString)
      {
        Log.e("Test", "异常： " + paramString.getMessage());
        paramString.printStackTrace();
        return localArrayList;
      }
      paramString = localJSONObject.getString("promotion");
      continue;
      label697: int j = localJSONObject.getInt("shopId");
      continue;
      label708: paramString = localJSONObject.getString("shopLogo");
      continue;
      label719: paramString = localJSONObject.getString("shopName");
      continue;
      label730: paramString = localJSONObject.getString("address");
      continue;
      label741: return localArrayList;
      label744: i += 1;
      continue;
      label751: boolean bool = false;
    }
  }

  public static List<Store> getSearchForPromotionData(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramString != null)
      try
      {
        paramString = new JSONObject(paramString).getJSONObject("data");
        if (paramString.isNull("goodsList"))
          return localArrayList;
        JSONArray localJSONArray = paramString.getJSONArray("goodsList");
        if ((localJSONArray != null) && (localJSONArray.length() > 0))
        {
          int i = 0;
          if (i < localJSONArray.length())
          {
            JSONObject localJSONObject = localJSONArray.getJSONObject(i);
            LinkedList localLinkedList = new LinkedList();
            Goods localGoods = new Goods();
            Store localStore = new Store();
            int j;
            label118: boolean bool;
            if (localJSONObject.isNull("goodsId"))
            {
              j = 0;
              localGoods.setGoodsId(j);
              StringBuilder localStringBuilder = new StringBuilder(String.valueOf(Url.SERVER_URL2));
              if (!localJSONObject.isNull("goodsLogo"))
                break label485;
              paramString = "";
              label152: localGoods.setGoodsLogo(paramString);
              localGoods.setHeight(localJSONObject.getInt("imgHeight"));
              localGoods.setWidth(localJSONObject.getInt("imgWidth"));
              localGoods.setGoodsName(localJSONObject.getString("goodsName"));
              if (!localJSONObject.isNull("goodsEndDate"))
                break label496;
              paramString = "";
              label215: localGoods.setGoodsEndDate(paramString);
              if (!localJSONObject.isNull("goodsScan"))
                break label507;
              j = 2;
              label233: localGoods.setGoodsScan(j);
              if (!localJSONObject.isNull("isCollect"))
                break label524;
              bool = false;
              label251: localGoods.setCollect(bool);
              if (!localJSONObject.getString("h5Url").equals(""))
                break label541;
              paramString = "https://";
              label275: localGoods.setH5Url(paramString);
              if (!localJSONObject.isNull("shopId"))
                break label552;
              j = 0;
              label293: localGoods.setShopId(j);
              localGoods.setGoodsDescribe(localJSONObject.getString("promotion"));
              if (!localJSONObject.isNull("shopId"))
                break label563;
              j = 0;
              label323: localStore.setShopId(j);
              localStore.setShopLogo(Url.ADS_URL + localJSONObject.getString("shopLogo"));
              if (!localJSONObject.isNull("shopName"))
                break label574;
              paramString = "";
              label373: localStore.setShopName(paramString);
              if (!localJSONObject.isNull("address"))
                break label585;
            }
            label524: label541: label552: label563: label574: label585: for (paramString = ""; ; paramString = localJSONObject.getString("address"))
            {
              localStore.setShopAddress(paramString);
              localStore.setLon(Double.parseDouble(localJSONObject.getString("lon")));
              localStore.setLat(Double.parseDouble(localJSONObject.getString("lat")));
              localStore.setDistance(localJSONObject.getString("distance"));
              localLinkedList.add(localGoods);
              localStore.setGoods_list(localLinkedList);
              localArrayList.add(localStore);
              i += 1;
              break;
              j = localJSONObject.getInt("goodsId");
              break label118;
              label485: paramString = localJSONObject.getString("goodsLogo");
              break label152;
              label496: paramString = localJSONObject.getString("goodsEndDate");
              break label215;
              label507: j = Integer.valueOf(localJSONObject.getString("goodsScan")).intValue();
              break label233;
              bool = localJSONObject.getString("isCollect").equalsIgnoreCase("Y");
              break label251;
              paramString = localJSONObject.getString("h5Url");
              break label275;
              j = localJSONObject.getInt("shopId");
              break label293;
              j = localJSONObject.getInt("shopId");
              break label323;
              paramString = localJSONObject.getString("shopName");
              break label373;
            }
          }
        }
      }
      catch (JSONException paramString)
      {
        paramString.printStackTrace();
      }
    return localArrayList;
  }

  public static List<Store> getSearchStorePromotionData(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramString != null)
      try
      {
        paramString = new JSONObject(paramString).getJSONObject("data");
        if (paramString.isNull("goodsList"))
          return localArrayList;
        JSONArray localJSONArray = paramString.getJSONArray("goodsList");
        if ((localJSONArray != null) && (localJSONArray.length() > 0))
        {
          int i = 0;
          if (i < localJSONArray.length())
          {
            JSONObject localJSONObject = localJSONArray.getJSONObject(i);
            LinkedList localLinkedList = new LinkedList();
            Goods localGoods = new Goods();
            Store localStore = new Store();
            int j;
            label118: boolean bool;
            if (localJSONObject.isNull("goodsId"))
            {
              j = 0;
              localGoods.setGoodsId(j);
              StringBuilder localStringBuilder = new StringBuilder(String.valueOf(Url.SERVER_URL2));
              if (!localJSONObject.isNull("goodsLogo"))
                break label516;
              paramString = "";
              label152: localGoods.setGoodsLogo(paramString);
              localGoods.setHeight(localJSONObject.getInt("imgHeight"));
              localGoods.setWidth(localJSONObject.getInt("imgWidth"));
              if (!localJSONObject.isNull("goodsName"))
                break label527;
              paramString = "";
              label203: localGoods.setGoodsName(paramString);
              if (!localJSONObject.isNull("goodsEndDate"))
                break label538;
              paramString = "";
              label222: localGoods.setGoodsEndDate(paramString);
              if (!localJSONObject.isNull("goodsScan"))
                break label549;
              j = 2;
              label240: localGoods.setGoodsScan(j);
              if (!localJSONObject.isNull("isCollect"))
                break label566;
              bool = false;
              label258: localGoods.setCollect(bool);
              if (!localJSONObject.getString("h5Url").equals(""))
                break label583;
              paramString = "https://";
              label282: localGoods.setH5Url(paramString);
              if (!localJSONObject.isNull("shopId"))
                break label594;
              j = 0;
              label300: localGoods.setShopId(j);
              localGoods.setGoodsDescribe(localJSONObject.getString("promotion"));
              if (!localJSONObject.isNull("shopId"))
                break label605;
              j = 0;
              label330: localStore.setShopId(j);
              localStringBuilder = new StringBuilder(String.valueOf(Url.ADS_URL));
              if (!localJSONObject.isNull("shopLogo"))
                break label616;
              paramString = "";
              label364: localStore.setShopLogo(paramString);
              if (!localJSONObject.isNull("shopName"))
                break label627;
              paramString = "";
              label391: localStore.setShopName(paramString);
              if (!localJSONObject.isNull("address"))
                break label638;
            }
            label516: label527: label538: label549: label566: label583: label594: label605: label616: label627: label638: for (paramString = ""; ; paramString = localJSONObject.getString("address"))
            {
              localStore.setShopAddress(paramString);
              localStore.setLon(Double.parseDouble(localJSONObject.getString("lon")));
              localStore.setLat(Double.parseDouble(localJSONObject.getString("lat")));
              localStore.setDistance(localJSONObject.getString("distance"));
              localStore.setReatilName(localJSONObject.getString("retailName"));
              localLinkedList.add(localGoods);
              localStore.setGoods_list(localLinkedList);
              localArrayList.add(localStore);
              i += 1;
              break;
              j = localJSONObject.getInt("goodsId");
              break label118;
              paramString = localJSONObject.getString("goodsLogo");
              break label152;
              paramString = localJSONObject.getString("goodsName");
              break label203;
              paramString = localJSONObject.getString("goodsEndDate");
              break label222;
              j = Integer.valueOf(localJSONObject.getString("goodsScan")).intValue();
              break label240;
              bool = localJSONObject.getString("isCollect").equalsIgnoreCase("Y");
              break label258;
              paramString = localJSONObject.getString("h5Url");
              break label282;
              j = localJSONObject.getInt("shopId");
              break label300;
              j = localJSONObject.getInt("shopId");
              break label330;
              paramString = localJSONObject.getString("shopLogo");
              break label364;
              paramString = localJSONObject.getString("shopName");
              break label391;
            }
          }
        }
      }
      catch (JSONException paramString)
      {
        Log.e("hahaha", "json解析异常: " + paramString.getMessage());
        paramString.printStackTrace();
      }
    return localArrayList;
  }

  // ERROR //
  public static List<com.ismartgo.app.bean.SmartHeadInfo> getSmartHead(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_0
    //   3: ifnull +133 -> 136
    //   6: new 15	org/json/JSONObject
    //   9: dup
    //   10: aload_0
    //   11: invokespecial 18	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   14: astore_0
    //   15: aload_0
    //   16: ldc 26
    //   18: invokevirtual 30	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   21: istore_1
    //   22: aload_0
    //   23: ldc 20
    //   25: invokevirtual 24	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   28: astore_0
    //   29: iload_1
    //   30: sipush 10001
    //   33: if_icmpne +103 -> 136
    //   36: aload_0
    //   37: ifnull +99 -> 136
    //   40: aload_0
    //   41: ldc 32
    //   43: invokevirtual 36	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   46: astore_2
    //   47: aload_2
    //   48: ifnull +88 -> 136
    //   51: aload_2
    //   52: invokevirtual 42	org/json/JSONArray:length	()I
    //   55: ifle +81 -> 136
    //   58: new 44	java/util/ArrayList
    //   61: dup
    //   62: invokespecial 45	java/util/ArrayList:<init>	()V
    //   65: astore_0
    //   66: iconst_0
    //   67: istore_1
    //   68: iload_1
    //   69: aload_2
    //   70: invokevirtual 42	org/json/JSONArray:length	()I
    //   73: if_icmplt +5 -> 78
    //   76: aload_0
    //   77: areturn
    //   78: aload_2
    //   79: iload_1
    //   80: invokevirtual 48	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   83: astore_3
    //   84: aload_0
    //   85: new 410	com/ismartgo/app/bean/SmartHeadInfo
    //   88: dup
    //   89: aload_3
    //   90: ldc 52
    //   92: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   95: aload_3
    //   96: ldc 58
    //   98: invokevirtual 30	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   101: aload_3
    //   102: ldc 62
    //   104: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   107: invokespecial 413	com/ismartgo/app/bean/SmartHeadInfo:<init>	(Ljava/lang/String;ILjava/lang/String;)V
    //   110: invokeinterface 71 2 0
    //   115: pop
    //   116: iload_1
    //   117: iconst_1
    //   118: iadd
    //   119: istore_1
    //   120: goto -52 -> 68
    //   123: astore_2
    //   124: aload_3
    //   125: astore_0
    //   126: aload_2
    //   127: invokevirtual 74	org/json/JSONException:printStackTrace	()V
    //   130: aload_0
    //   131: areturn
    //   132: astore_2
    //   133: goto -7 -> 126
    //   136: aconst_null
    //   137: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   6	29	123	org/json/JSONException
    //   40	47	123	org/json/JSONException
    //   51	66	123	org/json/JSONException
    //   68	76	132	org/json/JSONException
    //   78	116	132	org/json/JSONException
  }

  // ERROR //
  public static List<com.ismartgo.app.bean.ReceiptMode> jsonParse_receiptMode(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 15	org/json/JSONObject
    //   5: dup
    //   6: aload_0
    //   7: invokespecial 18	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   10: astore_0
    //   11: aload_0
    //   12: ldc 26
    //   14: invokevirtual 30	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   17: sipush 10001
    //   20: if_icmpne +144 -> 164
    //   23: aload_0
    //   24: ldc 20
    //   26: invokevirtual 24	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   29: ldc 32
    //   31: invokevirtual 36	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   34: astore_3
    //   35: aload_3
    //   36: ifnull +128 -> 164
    //   39: aload_3
    //   40: invokevirtual 42	org/json/JSONArray:length	()I
    //   43: ifle +121 -> 164
    //   46: new 44	java/util/ArrayList
    //   49: dup
    //   50: invokespecial 45	java/util/ArrayList:<init>	()V
    //   53: astore_0
    //   54: iconst_0
    //   55: istore_1
    //   56: iload_1
    //   57: aload_3
    //   58: invokevirtual 42	org/json/JSONArray:length	()I
    //   61: if_icmplt +5 -> 66
    //   64: aload_0
    //   65: areturn
    //   66: aload_3
    //   67: iload_1
    //   68: invokevirtual 48	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   71: astore_2
    //   72: new 417	com/ismartgo/app/bean/ReceiptMode
    //   75: dup
    //   76: invokespecial 418	com/ismartgo/app/bean/ReceiptMode:<init>	()V
    //   79: astore 4
    //   81: aload 4
    //   83: aload_2
    //   84: ldc 58
    //   86: invokevirtual 30	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   89: invokevirtual 419	com/ismartgo/app/bean/ReceiptMode:setId	(I)V
    //   92: aload 4
    //   94: aload_2
    //   95: ldc_w 421
    //   98: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   101: invokevirtual 424	com/ismartgo/app/bean/ReceiptMode:setUrl	(Ljava/lang/String;)V
    //   104: aload 4
    //   106: aload_2
    //   107: ldc 62
    //   109: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   112: invokevirtual 427	com/ismartgo/app/bean/ReceiptMode:setTitle	(Ljava/lang/String;)V
    //   115: aload 4
    //   117: aload_2
    //   118: ldc_w 429
    //   121: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   124: invokevirtual 432	com/ismartgo/app/bean/ReceiptMode:setColor	(Ljava/lang/String;)V
    //   127: aload 4
    //   129: iconst_0
    //   130: invokevirtual 435	com/ismartgo/app/bean/ReceiptMode:setHasSetDrawable	(Z)V
    //   133: aload_0
    //   134: aload 4
    //   136: invokeinterface 71 2 0
    //   141: pop
    //   142: iload_1
    //   143: iconst_1
    //   144: iadd
    //   145: istore_1
    //   146: goto -90 -> 56
    //   149: astore_0
    //   150: aload_0
    //   151: invokevirtual 74	org/json/JSONException:printStackTrace	()V
    //   154: aload_2
    //   155: areturn
    //   156: astore_3
    //   157: aload_0
    //   158: astore_2
    //   159: aload_3
    //   160: astore_0
    //   161: goto -11 -> 150
    //   164: aconst_null
    //   165: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   2	35	149	org/json/JSONException
    //   39	54	149	org/json/JSONException
    //   56	64	156	org/json/JSONException
    //   66	142	156	org/json/JSONException
  }

  public static Logistics logisticsJSONParse(String paramString)
  {
    Object localObject1;
    if (TextUtils.isEmpty(paramString))
      localObject1 = null;
    while (true)
    {
      return localObject1;
      Object localObject2 = null;
      localObject1 = null;
      try
      {
        localObject3 = new JSONObject(paramString);
        paramString = (String)localObject2;
        if (localObject3 != null)
          paramString = new Logistics();
      }
      catch (JSONException localJSONException2)
      {
        try
        {
          Object localObject3;
          paramString.setStatus(((JSONObject)localObject3).optInt("status"));
          paramString.setMsg(((JSONObject)localObject3).optString("msg"));
          paramString.setTime(((JSONObject)localObject3).optString("time"));
          localObject2 = ((JSONObject)localObject3).optJSONObject("data");
          localObject1 = paramString;
          if (localObject2 != null)
          {
            localObject1 = paramString;
            if (((JSONObject)localObject2).length() != 0)
            {
              localObject1 = new Logistics.DataEntity();
              ((Logistics.DataEntity)localObject1).setLogisticsNum(((JSONObject)localObject2).optString("logisticsnum"));
              localObject2 = ((JSONObject)localObject2).optJSONArray("orderTrack");
              int i;
              if ((localObject2 != null) && (((JSONArray)localObject2).length() > 0))
              {
                localObject3 = new ArrayList();
                i = 0;
              }
              while (true)
              {
                if (i >= ((JSONArray)localObject2).length())
                {
                  ((Logistics.DataEntity)localObject1).setOrderTrack((List)localObject3);
                  paramString.setData((Logistics.DataEntity)localObject1);
                  break;
                }
                Logistics.DataEntity.OrderTrackEntity localOrderTrackEntity = new Logistics.DataEntity.OrderTrackEntity();
                JSONObject localJSONObject = ((JSONArray)localObject2).optJSONObject(i);
                localOrderTrackEntity.setContent(localJSONObject.optString("content"));
                localOrderTrackEntity.setMsgTime(localJSONObject.optString("time"));
                ((List)localObject3).add(localOrderTrackEntity);
                i += 1;
              }
              localJSONException2 = localJSONException2;
              paramString = (String)localObject1;
              localObject1 = localJSONException2;
              label233: ((JSONException)localObject1).printStackTrace();
            }
          }
        }
        catch (JSONException localJSONException1)
        {
          break label233;
        }
      }
    }
    return paramString;
  }

  public static HashMap<String, Object> parse_SelectView(String paramString)
  {
    HashMap localHashMap = new HashMap();
    try
    {
      paramString = new JSONObject(paramString);
      paramString.getInt("status");
      paramString.getString("msg");
      paramString = paramString.getJSONObject("data").getJSONArray("data");
      if ((paramString != null) && (paramString.length() > 0))
      {
        int i = 0;
        while (true)
        {
          if (i >= paramString.length())
            return localHashMap;
          JSONObject localJSONObject = paramString.getJSONObject(i);
          localHashMap.put(localJSONObject.optString("name"), localJSONObject.get("id"));
          i += 1;
        }
      }
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
    return localHashMap;
  }

  // ERROR //
  public static com.ismartgo.app.bean.Session sessionJsonParse(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: new 519	com/ismartgo/app/bean/Session
    //   6: dup
    //   7: invokespecial 520	com/ismartgo/app/bean/Session:<init>	()V
    //   10: astore_3
    //   11: new 15	org/json/JSONObject
    //   14: dup
    //   15: aload_0
    //   16: invokevirtual 521	java/lang/String:toString	()Ljava/lang/String;
    //   19: invokespecial 18	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   22: astore_0
    //   23: aload_0
    //   24: ldc 26
    //   26: invokevirtual 30	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   29: sipush 10001
    //   32: if_icmpne +122 -> 154
    //   35: aload_0
    //   36: ldc 20
    //   38: invokevirtual 469	org/json/JSONObject:optJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   41: astore 5
    //   43: aload 5
    //   45: ldc_w 523
    //   48: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   51: astore_0
    //   52: aload 5
    //   54: ldc_w 525
    //   57: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   60: astore 4
    //   62: aload 5
    //   64: ldc_w 527
    //   67: invokevirtual 30	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   70: istore_1
    //   71: aload 5
    //   73: ldc_w 529
    //   76: invokevirtual 30	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   79: istore_2
    //   80: aload 5
    //   82: ldc_w 531
    //   85: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   88: astore 5
    //   90: aload_3
    //   91: aload_0
    //   92: invokevirtual 534	com/ismartgo/app/bean/Session:setSessionid	(Ljava/lang/String;)V
    //   95: aload_3
    //   96: aload 4
    //   98: invokevirtual 537	com/ismartgo/app/bean/Session:setExpire	(Ljava/lang/String;)V
    //   101: aload_3
    //   102: iload_1
    //   103: invokevirtual 540	com/ismartgo/app/bean/Session:setHomebuttonversion	(I)V
    //   106: aload_3
    //   107: iload_2
    //   108: invokevirtual 543	com/ismartgo/app/bean/Session:setReceiptshoptypeversion	(I)V
    //   111: aload_3
    //   112: aload 5
    //   114: invokevirtual 546	com/ismartgo/app/bean/Session:setReceiptsvurl	(Ljava/lang/String;)V
    //   117: invokestatic 552	com/ismartgo/app/application/AndroidApplication:getInstance	()Lcom/ismartgo/app/application/AndroidApplication;
    //   120: aload 4
    //   122: invokestatic 558	com/ismartgo/app/tools/SharedPreferenceUtil:setSessionExpire	(Landroid/content/Context;Ljava/lang/String;)V
    //   125: invokestatic 552	com/ismartgo/app/application/AndroidApplication:getInstance	()Lcom/ismartgo/app/application/AndroidApplication;
    //   128: aload_0
    //   129: invokestatic 561	com/ismartgo/app/tools/SharedPreferenceUtil:setSessionId	(Landroid/content/Context;Ljava/lang/String;)V
    //   132: invokestatic 552	com/ismartgo/app/application/AndroidApplication:getInstance	()Lcom/ismartgo/app/application/AndroidApplication;
    //   135: invokestatic 565	com/ismartgo/app/tools/SharedPreferenceUtil:setSessionVersion	(Landroid/content/Context;)V
    //   138: aload_3
    //   139: areturn
    //   140: astore_0
    //   141: aload 4
    //   143: astore_3
    //   144: aload_0
    //   145: invokevirtual 74	org/json/JSONException:printStackTrace	()V
    //   148: aload_3
    //   149: areturn
    //   150: astore_0
    //   151: goto -7 -> 144
    //   154: aload_3
    //   155: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   3	11	140	org/json/JSONException
    //   11	138	150	org/json/JSONException
  }

  // ERROR //
  public static com.ismartgo.app.bean.Session sessionNewJsonParse(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: new 519	com/ismartgo/app/bean/Session
    //   6: dup
    //   7: invokespecial 520	com/ismartgo/app/bean/Session:<init>	()V
    //   10: astore_3
    //   11: new 15	org/json/JSONObject
    //   14: dup
    //   15: aload_0
    //   16: invokevirtual 521	java/lang/String:toString	()Ljava/lang/String;
    //   19: invokespecial 18	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   22: astore_0
    //   23: aload_0
    //   24: ldc 26
    //   26: invokevirtual 30	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   29: sipush 10001
    //   32: if_icmpne +116 -> 148
    //   35: aload_0
    //   36: ldc 20
    //   38: invokevirtual 469	org/json/JSONObject:optJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   41: astore 5
    //   43: aload 5
    //   45: ldc_w 523
    //   48: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   51: astore_0
    //   52: aload 5
    //   54: ldc_w 525
    //   57: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   60: astore 4
    //   62: aload 5
    //   64: ldc_w 527
    //   67: invokevirtual 30	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   70: istore_1
    //   71: aload 5
    //   73: ldc_w 529
    //   76: invokevirtual 30	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   79: istore_2
    //   80: aload 5
    //   82: ldc_w 531
    //   85: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   88: astore 5
    //   90: aload_3
    //   91: aload_0
    //   92: invokevirtual 534	com/ismartgo/app/bean/Session:setSessionid	(Ljava/lang/String;)V
    //   95: aload_3
    //   96: aload 4
    //   98: invokevirtual 537	com/ismartgo/app/bean/Session:setExpire	(Ljava/lang/String;)V
    //   101: aload_3
    //   102: iload_1
    //   103: invokevirtual 540	com/ismartgo/app/bean/Session:setHomebuttonversion	(I)V
    //   106: aload_3
    //   107: iload_2
    //   108: invokevirtual 543	com/ismartgo/app/bean/Session:setReceiptshoptypeversion	(I)V
    //   111: aload_3
    //   112: aload 5
    //   114: invokevirtual 546	com/ismartgo/app/bean/Session:setReceiptsvurl	(Ljava/lang/String;)V
    //   117: invokestatic 552	com/ismartgo/app/application/AndroidApplication:getInstance	()Lcom/ismartgo/app/application/AndroidApplication;
    //   120: aload 4
    //   122: invokestatic 558	com/ismartgo/app/tools/SharedPreferenceUtil:setSessionExpire	(Landroid/content/Context;Ljava/lang/String;)V
    //   125: invokestatic 552	com/ismartgo/app/application/AndroidApplication:getInstance	()Lcom/ismartgo/app/application/AndroidApplication;
    //   128: aload_0
    //   129: invokestatic 561	com/ismartgo/app/tools/SharedPreferenceUtil:setSessionId	(Landroid/content/Context;Ljava/lang/String;)V
    //   132: aload_3
    //   133: areturn
    //   134: astore_0
    //   135: aload 4
    //   137: astore_3
    //   138: aload_0
    //   139: invokevirtual 74	org/json/JSONException:printStackTrace	()V
    //   142: aload_3
    //   143: areturn
    //   144: astore_0
    //   145: goto -7 -> 138
    //   148: aload_3
    //   149: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   3	11	134	org/json/JSONException
    //   11	132	144	org/json/JSONException
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.http.HttpJsonParse
 * JD-Core Version:    0.6.2
 */