package com.ismartgo.app.net;

import android.content.Context;
import android.util.Log;
import com.ismartgo.app.activity.BaseActivity;
import com.ismartgo.app.bean.Goods;
import com.ismartgo.app.bean.Infos;
import com.ismartgo.app.bean.Store;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.interfaces.OnCompleteListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.tsz.afinal.FinalHttp;

public class PromotionGoodsRequest
  implements HttpCallback<String>
{
  private Context context;
  private List<Goods> goods_list = new ArrayList();
  private FinalHttp http = null;
  private Infos infos;
  private OnCompleteListener onCompleteListener = null;
  private Map<String, String> params;
  private List<Store> store_list = new ArrayList();
  private String url;

  public PromotionGoodsRequest(Context paramContext, String paramString)
  {
    this.context = paramContext;
    this.params = new HashMap();
    this.http = new FinalHttp();
    this.http.configTimeout(30000);
    this.http.addHeader("Accept-Charset", "UTF-8");
    this.http.configCharset("UTF-8");
    this.http.configCookieStore(null);
    this.http.configRequestExecutionRetryCount(3);
    this.url = paramString;
    this.infos = new Infos();
  }

  public void initParams(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, String paramString4, int paramInt6, int paramInt7, int paramInt8, String paramString5)
  {
    this.params.put("cityName", BaseActivity.city);
    this.params.put("lon", paramString2);
    this.params.put("lat", paramString3);
    if (paramInt6 == -1)
      this.params.put("distance", "");
    while (true)
    {
      this.params.put("districtId", String.valueOf(paramInt1));
      this.params.put("townId", String.valueOf(paramInt3));
      this.params.put("shopTypeId", String.valueOf(paramInt2));
      this.params.put("retailId", String.valueOf(paramInt4));
      this.params.put("categoryId", "");
      this.params.put("brandIds", "");
      this.params.put("pages", String.valueOf(paramInt7));
      this.params.put("pageSize", String.valueOf(paramInt8));
      this.params.put("uid", paramString5);
      return;
      this.params.put("distance", String.valueOf(paramInt6));
    }
  }

  public void onFailed(int paramInt, String paramString, Object paramObject, CharSequence paramCharSequence)
  {
    if (this.onCompleteListener != null)
      this.onCompleteListener.onFailure(null, paramInt, paramCharSequence.toString());
  }

  // ERROR //
  public void onSucceed(int paramInt, com.yolanda.nohttp.Response<String> paramResponse)
  {
    // Byte code:
    //   0: getstatic 161	java/lang/System:out	Ljava/io/PrintStream;
    //   3: new 101	java/lang/StringBuilder
    //   6: dup
    //   7: ldc 163
    //   9: invokespecial 109	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   12: aload_2
    //   13: invokeinterface 169 1 0
    //   18: checkcast 103	java/lang/String
    //   21: invokevirtual 170	java/lang/String:toString	()Ljava/lang/String;
    //   24: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: invokevirtual 113	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   30: invokevirtual 179	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   33: ldc 181
    //   35: new 101	java/lang/StringBuilder
    //   38: dup
    //   39: ldc 183
    //   41: invokespecial 109	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   44: aload_2
    //   45: invokeinterface 169 1 0
    //   50: checkcast 103	java/lang/String
    //   53: invokevirtual 170	java/lang/String:toString	()Ljava/lang/String;
    //   56: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: invokevirtual 113	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   62: invokestatic 189	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   65: pop
    //   66: iconst_0
    //   67: istore_1
    //   68: aconst_null
    //   69: astore 7
    //   71: aload_2
    //   72: ifnull +145 -> 217
    //   75: aload_0
    //   76: getfield 32	com/ismartgo/app/net/PromotionGoodsRequest:onCompleteListener	Lcom/ismartgo/app/interfaces/OnCompleteListener;
    //   79: ifnull +138 -> 217
    //   82: iload_1
    //   83: istore_3
    //   84: new 191	org/json/JSONObject
    //   87: dup
    //   88: aload_2
    //   89: invokeinterface 169 1 0
    //   94: checkcast 103	java/lang/String
    //   97: invokevirtual 170	java/lang/String:toString	()Ljava/lang/String;
    //   100: invokespecial 192	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   103: astore 8
    //   105: iload_1
    //   106: istore_3
    //   107: aload 8
    //   109: ldc 194
    //   111: invokevirtual 198	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   114: ldc 200
    //   116: invokevirtual 204	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   119: astore 9
    //   121: iload_1
    //   122: istore_3
    //   123: aload 8
    //   125: ldc 206
    //   127: invokevirtual 210	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   130: istore_1
    //   131: iconst_0
    //   132: istore 4
    //   134: aconst_null
    //   135: astore 6
    //   137: aload 7
    //   139: astore_2
    //   140: iload 4
    //   142: aload 9
    //   144: invokevirtual 216	org/json/JSONArray:length	()I
    //   147: if_icmplt +71 -> 218
    //   150: aload_0
    //   151: getfield 81	com/ismartgo/app/net/PromotionGoodsRequest:infos	Lcom/ismartgo/app/bean/Infos;
    //   154: aload 8
    //   156: ldc 218
    //   158: invokevirtual 222	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   161: invokevirtual 225	com/ismartgo/app/bean/Infos:setMsg	(Ljava/lang/String;)V
    //   164: aload_0
    //   165: getfield 81	com/ismartgo/app/net/PromotionGoodsRequest:infos	Lcom/ismartgo/app/bean/Infos;
    //   168: aload 8
    //   170: ldc 206
    //   172: invokevirtual 210	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   175: invokevirtual 228	com/ismartgo/app/bean/Infos:setStatus	(I)V
    //   178: aload_0
    //   179: getfield 81	com/ismartgo/app/net/PromotionGoodsRequest:infos	Lcom/ismartgo/app/bean/Infos;
    //   182: aload 8
    //   184: ldc 230
    //   186: invokevirtual 222	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   189: invokevirtual 233	com/ismartgo/app/bean/Infos:setTime	(Ljava/lang/String;)V
    //   192: aload_0
    //   193: getfield 81	com/ismartgo/app/net/PromotionGoodsRequest:infos	Lcom/ismartgo/app/bean/Infos;
    //   196: aload_0
    //   197: getfield 41	com/ismartgo/app/net/PromotionGoodsRequest:store_list	Ljava/util/List;
    //   200: invokevirtual 237	com/ismartgo/app/bean/Infos:setStore_list	(Ljava/util/List;)V
    //   203: aload_0
    //   204: getfield 32	com/ismartgo/app/net/PromotionGoodsRequest:onCompleteListener	Lcom/ismartgo/app/interfaces/OnCompleteListener;
    //   207: aload_0
    //   208: getfield 81	com/ismartgo/app/net/PromotionGoodsRequest:infos	Lcom/ismartgo/app/bean/Infos;
    //   211: iload_1
    //   212: invokeinterface 241 3 0
    //   217: return
    //   218: aload 9
    //   220: iload 4
    //   222: invokevirtual 244	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   225: astore 10
    //   227: new 246	com/ismartgo/app/bean/Store
    //   230: dup
    //   231: invokespecial 247	com/ismartgo/app/bean/Store:<init>	()V
    //   234: astore 7
    //   236: iload_1
    //   237: istore_3
    //   238: aload 7
    //   240: aload 10
    //   242: ldc 249
    //   244: invokevirtual 210	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   247: invokevirtual 252	com/ismartgo/app/bean/Store:setShopId	(I)V
    //   250: iload_1
    //   251: istore_3
    //   252: aload 7
    //   254: aload 10
    //   256: ldc 117
    //   258: invokevirtual 222	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   261: invokevirtual 255	com/ismartgo/app/bean/Store:setDistance	(Ljava/lang/String;)V
    //   264: iload_1
    //   265: istore_3
    //   266: aload 7
    //   268: aload 10
    //   270: ldc_w 257
    //   273: invokevirtual 210	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   276: invokevirtual 260	com/ismartgo/app/bean/Store:setSaleCount	(I)V
    //   279: iload_1
    //   280: istore_3
    //   281: aload 7
    //   283: new 101	java/lang/StringBuilder
    //   286: dup
    //   287: getstatic 265	com/ismartgo/app/url/Url:SERVER_URL2	Ljava/lang/String;
    //   290: invokestatic 107	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   293: invokespecial 109	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   296: aload 10
    //   298: ldc_w 267
    //   301: invokevirtual 222	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   304: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   307: invokevirtual 113	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   310: invokevirtual 270	com/ismartgo/app/bean/Store:setShopLogo	(Ljava/lang/String;)V
    //   313: iload_1
    //   314: istore_3
    //   315: aload 7
    //   317: aload 10
    //   319: ldc_w 272
    //   322: invokevirtual 222	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   325: invokevirtual 275	com/ismartgo/app/bean/Store:setShopName	(Ljava/lang/String;)V
    //   328: iload_1
    //   329: istore_3
    //   330: aload 7
    //   332: aload 10
    //   334: ldc_w 277
    //   337: invokevirtual 222	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   340: invokevirtual 280	com/ismartgo/app/bean/Store:setShopAddress	(Ljava/lang/String;)V
    //   343: iload_1
    //   344: istore_3
    //   345: aload 7
    //   347: aload 10
    //   349: ldc 99
    //   351: invokevirtual 284	org/json/JSONObject:getDouble	(Ljava/lang/String;)D
    //   354: invokevirtual 288	com/ismartgo/app/bean/Store:setLon	(D)V
    //   357: iload_1
    //   358: istore_3
    //   359: aload 7
    //   361: aload 10
    //   363: ldc 115
    //   365: invokevirtual 284	org/json/JSONObject:getDouble	(Ljava/lang/String;)D
    //   368: invokevirtual 291	com/ismartgo/app/bean/Store:setLat	(D)V
    //   371: iload_1
    //   372: istore_3
    //   373: aload 10
    //   375: ldc 128
    //   377: invokevirtual 295	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   380: ifeq +181 -> 561
    //   383: iconst_m1
    //   384: istore 5
    //   386: iload_1
    //   387: istore_3
    //   388: aload 7
    //   390: iload 5
    //   392: invokevirtual 298	com/ismartgo/app/bean/Store:setShopTypeId	(I)V
    //   395: iload_1
    //   396: istore_3
    //   397: aload 10
    //   399: ldc_w 300
    //   402: invokevirtual 295	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   405: ifeq +170 -> 575
    //   408: iconst_m1
    //   409: istore 5
    //   411: iload_1
    //   412: istore_3
    //   413: aload 7
    //   415: iload 5
    //   417: invokevirtual 303	com/ismartgo/app/bean/Store:setReatilId	(I)V
    //   420: iload_1
    //   421: istore_3
    //   422: aload 7
    //   424: aload 10
    //   426: ldc_w 305
    //   429: invokevirtual 222	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   432: invokevirtual 308	com/ismartgo/app/bean/Store:setReatilName	(Ljava/lang/String;)V
    //   435: iload_1
    //   436: istore_3
    //   437: aload 10
    //   439: ldc_w 310
    //   442: invokevirtual 295	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   445: ifeq +145 -> 590
    //   448: ldc_w 312
    //   451: astore 6
    //   453: iload_1
    //   454: istore_3
    //   455: aload 7
    //   457: aload 6
    //   459: invokevirtual 315	com/ismartgo/app/bean/Store:setIsSign	(Ljava/lang/String;)V
    //   462: iload_1
    //   463: istore_3
    //   464: aload 10
    //   466: ldc_w 317
    //   469: invokevirtual 295	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   472: ifeq +133 -> 605
    //   475: ldc_w 319
    //   478: astore 6
    //   480: iload_1
    //   481: istore_3
    //   482: aload 7
    //   484: aload 6
    //   486: invokevirtual 322	com/ismartgo/app/bean/Store:setUserIsSign	(Ljava/lang/String;)V
    //   489: iload_1
    //   490: istore_3
    //   491: aload 10
    //   493: ldc_w 324
    //   496: invokevirtual 204	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   499: astore 11
    //   501: iload_1
    //   502: istore_3
    //   503: aload_0
    //   504: new 326	java/util/LinkedList
    //   507: dup
    //   508: invokespecial 327	java/util/LinkedList:<init>	()V
    //   511: putfield 39	com/ismartgo/app/net/PromotionGoodsRequest:goods_list	Ljava/util/List;
    //   514: iconst_0
    //   515: istore 5
    //   517: iload 5
    //   519: aload 11
    //   521: invokevirtual 216	org/json/JSONArray:length	()I
    //   524: if_icmplt +96 -> 620
    //   527: aload 7
    //   529: aload_0
    //   530: getfield 39	com/ismartgo/app/net/PromotionGoodsRequest:goods_list	Ljava/util/List;
    //   533: invokevirtual 330	com/ismartgo/app/bean/Store:setGoods_list	(Ljava/util/List;)V
    //   536: aload_0
    //   537: getfield 41	com/ismartgo/app/net/PromotionGoodsRequest:store_list	Ljava/util/List;
    //   540: aload 7
    //   542: invokeinterface 336 2 0
    //   547: pop
    //   548: iload 4
    //   550: iconst_1
    //   551: iadd
    //   552: istore 4
    //   554: aload 7
    //   556: astore 6
    //   558: goto -418 -> 140
    //   561: iload_1
    //   562: istore_3
    //   563: aload 10
    //   565: ldc 128
    //   567: invokevirtual 210	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   570: istore 5
    //   572: goto -186 -> 386
    //   575: iload_1
    //   576: istore_3
    //   577: aload 10
    //   579: ldc_w 300
    //   582: invokevirtual 210	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   585: istore 5
    //   587: goto -176 -> 411
    //   590: iload_1
    //   591: istore_3
    //   592: aload 10
    //   594: ldc_w 310
    //   597: invokevirtual 222	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   600: astore 6
    //   602: goto -149 -> 453
    //   605: iload_1
    //   606: istore_3
    //   607: aload 10
    //   609: ldc_w 317
    //   612: invokevirtual 222	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   615: astore 6
    //   617: goto -137 -> 480
    //   620: aload 11
    //   622: iload 5
    //   624: invokevirtual 244	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   627: astore 12
    //   629: new 338	com/ismartgo/app/bean/Goods
    //   632: dup
    //   633: invokespecial 339	com/ismartgo/app/bean/Goods:<init>	()V
    //   636: astore 6
    //   638: iload_1
    //   639: istore_3
    //   640: aload 6
    //   642: aload 10
    //   644: ldc 249
    //   646: invokevirtual 210	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   649: invokevirtual 340	com/ismartgo/app/bean/Goods:setShopId	(I)V
    //   652: iload_1
    //   653: istore_3
    //   654: aload 6
    //   656: iload 5
    //   658: invokevirtual 343	com/ismartgo/app/bean/Goods:setGoodsTag	(I)V
    //   661: iload_1
    //   662: istore_3
    //   663: aload 6
    //   665: aload 12
    //   667: ldc_w 345
    //   670: invokevirtual 210	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   673: invokevirtual 348	com/ismartgo/app/bean/Goods:setGoodsId	(I)V
    //   676: iload_1
    //   677: istore_3
    //   678: aload 6
    //   680: new 101	java/lang/StringBuilder
    //   683: dup
    //   684: getstatic 265	com/ismartgo/app/url/Url:SERVER_URL2	Ljava/lang/String;
    //   687: invokestatic 107	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   690: invokespecial 109	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   693: aload 12
    //   695: ldc_w 350
    //   698: invokevirtual 222	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   701: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   704: invokevirtual 113	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   707: invokevirtual 353	com/ismartgo/app/bean/Goods:setGoodsLogo	(Ljava/lang/String;)V
    //   710: iload_1
    //   711: istore_3
    //   712: aload 6
    //   714: aload 12
    //   716: ldc_w 355
    //   719: invokevirtual 222	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   722: invokevirtual 358	com/ismartgo/app/bean/Goods:setGoodsName	(Ljava/lang/String;)V
    //   725: iload_1
    //   726: istore_3
    //   727: aload 6
    //   729: aload 12
    //   731: ldc_w 360
    //   734: invokevirtual 210	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   737: invokevirtual 363	com/ismartgo/app/bean/Goods:setGoodsScan	(I)V
    //   740: iload_1
    //   741: istore_3
    //   742: aload 6
    //   744: aload 12
    //   746: ldc_w 365
    //   749: invokevirtual 222	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   752: invokevirtual 368	com/ismartgo/app/bean/Goods:setGoodsEndDate	(Ljava/lang/String;)V
    //   755: iload_1
    //   756: istore_3
    //   757: aload 6
    //   759: aload 12
    //   761: ldc_w 370
    //   764: invokevirtual 222	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   767: ldc_w 372
    //   770: invokevirtual 375	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   773: invokevirtual 379	com/ismartgo/app/bean/Goods:setCollect	(Z)V
    //   776: iload_1
    //   777: istore_3
    //   778: aload 12
    //   780: ldc_w 381
    //   783: invokevirtual 295	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   786: ifeq +65 -> 851
    //   789: ldc_w 383
    //   792: astore_2
    //   793: iload_1
    //   794: istore_3
    //   795: aload 6
    //   797: aload_2
    //   798: invokevirtual 386	com/ismartgo/app/bean/Goods:setH5Url	(Ljava/lang/String;)V
    //   801: iload_1
    //   802: istore_3
    //   803: aload 12
    //   805: ldc_w 388
    //   808: invokevirtual 295	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   811: ifeq +54 -> 865
    //   814: ldc 119
    //   816: astore_2
    //   817: iload_1
    //   818: istore_3
    //   819: aload 6
    //   821: aload_2
    //   822: invokevirtual 391	com/ismartgo/app/bean/Goods:setGoodsDescribe	(Ljava/lang/String;)V
    //   825: iload_1
    //   826: istore_3
    //   827: aload_0
    //   828: getfield 39	com/ismartgo/app/net/PromotionGoodsRequest:goods_list	Ljava/util/List;
    //   831: aload 6
    //   833: invokeinterface 336 2 0
    //   838: pop
    //   839: iload 5
    //   841: iconst_1
    //   842: iadd
    //   843: istore 5
    //   845: aload 6
    //   847: astore_2
    //   848: goto -331 -> 517
    //   851: iload_1
    //   852: istore_3
    //   853: aload 12
    //   855: ldc_w 381
    //   858: invokevirtual 222	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   861: astore_2
    //   862: goto -69 -> 793
    //   865: iload_1
    //   866: istore_3
    //   867: aload 12
    //   869: ldc_w 388
    //   872: invokevirtual 222	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   875: astore_2
    //   876: goto -59 -> 817
    //   879: astore_2
    //   880: iload_3
    //   881: istore_1
    //   882: ldc_w 393
    //   885: new 101	java/lang/StringBuilder
    //   888: dup
    //   889: ldc_w 395
    //   892: invokespecial 109	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   895: aload_2
    //   896: invokevirtual 398	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   899: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   902: invokevirtual 113	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   905: invokestatic 189	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   908: pop
    //   909: aload_2
    //   910: invokevirtual 401	org/json/JSONException:printStackTrace	()V
    //   913: goto -721 -> 192
    //   916: astore_2
    //   917: goto -35 -> 882
    //   920: astore_2
    //   921: goto -39 -> 882
    //
    // Exception table:
    //   from	to	target	type
    //   84	105	879	org/json/JSONException
    //   107	121	879	org/json/JSONException
    //   123	131	879	org/json/JSONException
    //   238	250	879	org/json/JSONException
    //   252	264	879	org/json/JSONException
    //   266	279	879	org/json/JSONException
    //   281	313	879	org/json/JSONException
    //   315	328	879	org/json/JSONException
    //   330	343	879	org/json/JSONException
    //   345	357	879	org/json/JSONException
    //   359	371	879	org/json/JSONException
    //   373	383	879	org/json/JSONException
    //   388	395	879	org/json/JSONException
    //   397	408	879	org/json/JSONException
    //   413	420	879	org/json/JSONException
    //   422	435	879	org/json/JSONException
    //   437	448	879	org/json/JSONException
    //   455	462	879	org/json/JSONException
    //   464	475	879	org/json/JSONException
    //   482	489	879	org/json/JSONException
    //   491	501	879	org/json/JSONException
    //   503	514	879	org/json/JSONException
    //   563	572	879	org/json/JSONException
    //   577	587	879	org/json/JSONException
    //   592	602	879	org/json/JSONException
    //   607	617	879	org/json/JSONException
    //   640	652	879	org/json/JSONException
    //   654	661	879	org/json/JSONException
    //   663	676	879	org/json/JSONException
    //   678	710	879	org/json/JSONException
    //   712	725	879	org/json/JSONException
    //   727	740	879	org/json/JSONException
    //   742	755	879	org/json/JSONException
    //   757	776	879	org/json/JSONException
    //   778	789	879	org/json/JSONException
    //   795	801	879	org/json/JSONException
    //   803	814	879	org/json/JSONException
    //   819	825	879	org/json/JSONException
    //   827	839	879	org/json/JSONException
    //   853	862	879	org/json/JSONException
    //   867	876	879	org/json/JSONException
    //   140	192	916	org/json/JSONException
    //   218	236	916	org/json/JSONException
    //   517	548	920	org/json/JSONException
    //   620	638	920	org/json/JSONException
  }

  public void setOnCompleteListener(OnCompleteListener paramOnCompleteListener)
  {
    this.onCompleteListener = paramOnCompleteListener;
  }

  public void setSearchKey(String paramString)
  {
    if ((paramString != null) && (!paramString.equals("")))
      this.params.put("searchname", paramString);
  }

  public void startRequest()
  {
    System.out.println("--->url=" + this.url + "?" + this.params.toString());
    Log.e("hahaha", "搜索商店url: " + this.url + "?" + this.params);
    HttpRequest.getInstance().executePostStringRequest(this.context, this.url, 18, this.params, this);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.net.PromotionGoodsRequest
 * JD-Core Version:    0.6.2
 */