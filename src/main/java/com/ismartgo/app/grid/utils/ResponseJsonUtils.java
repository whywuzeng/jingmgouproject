package com.ismartgo.app.grid.utils;

import android.text.TextUtils;
import android.util.Log;
import com.ismartgo.app.bean.BeanAllRecord;
import com.ismartgo.app.bean.BeanCostRecord;
import com.ismartgo.app.bean.BeanDetail;
import com.ismartgo.app.bean.BeanGetRecord;
import com.ismartgo.app.bean.ReceiptActivityInfo;
import com.ismartgo.app.bean.ReceiptInfo;
import com.ismartgo.app.url.ResultCode;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResponseJsonUtils
{
  public static List<ReceiptInfo> parseReceipt(String paramString)
  {
    JSONArray localJSONArray = null;
    Object localObject2 = null;
    ReceiptInfo localReceiptInfo = null;
    Object localObject1 = localJSONArray;
    try
    {
      if (!TextUtils.isEmpty(paramString))
      {
        localObject1 = localJSONArray;
        localObject2 = new JSONObject(paramString);
        paramString = localReceiptInfo;
        localObject1 = localJSONArray;
        if (((JSONObject)localObject2).optInt("status") == ResultCode.RESULT_OK)
        {
          localObject1 = localJSONArray;
          paramString = new ArrayList();
        }
        localObject1 = paramString;
        localJSONArray = ((JSONObject)localObject2).getJSONObject("data").optJSONArray("receipts");
        localObject2 = paramString;
        if (localJSONArray != null)
        {
          int i = 0;
          while (true)
          {
            localObject1 = paramString;
            if (i >= localJSONArray.length())
              return paramString;
            localObject1 = paramString;
            localObject2 = localJSONArray.getJSONObject(i);
            localObject1 = paramString;
            localReceiptInfo = new ReceiptInfo();
            localObject1 = paramString;
            localReceiptInfo.setId(((JSONObject)localObject2).optInt("id"));
            localObject1 = paramString;
            localReceiptInfo.setGameId(((JSONObject)localObject2).optInt("gameid"));
            localObject1 = paramString;
            localReceiptInfo.setTitle(((JSONObject)localObject2).optString("title"));
            localObject1 = paramString;
            localReceiptInfo.setBegindate(((JSONObject)localObject2).optString("begindate"));
            localObject1 = paramString;
            localReceiptInfo.setEnddate(((JSONObject)localObject2).optString("enddate"));
            localObject1 = paramString;
            localReceiptInfo.setPrizename(((JSONObject)localObject2).optString("prizename"));
            localObject1 = paramString;
            localReceiptInfo.setClickcnt(((JSONObject)localObject2).optInt("clickcnt"));
            localObject1 = paramString;
            localReceiptInfo.setJoinno(((JSONObject)localObject2).optInt("joinno"));
            localObject1 = paramString;
            localReceiptInfo.setStatus(((JSONObject)localObject2).optInt("status"));
            localObject1 = paramString;
            localReceiptInfo.setImgurl(((JSONObject)localObject2).optString("imgurl"));
            localObject1 = paramString;
            localReceiptInfo.setDetailurl(((JSONObject)localObject2).optString("detailurl"));
            localObject1 = paramString;
            paramString.add(localReceiptInfo);
            i += 1;
          }
        }
      }
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
      localObject2 = localObject1;
    }
    return localObject2;
  }

  public static List<ReceiptActivityInfo> parseReceiptActivity(String paramString)
  {
    JSONArray localJSONArray = null;
    Object localObject3 = null;
    ReceiptActivityInfo localReceiptActivityInfo = null;
    Object localObject2 = localObject3;
    if (!TextUtils.isEmpty(paramString))
    {
      Object localObject1 = localJSONArray;
      try
      {
        JSONObject localJSONObject = new JSONObject(paramString);
        localObject1 = localJSONArray;
        localObject2 = localObject3;
        if (localJSONObject.optInt("status") == ResultCode.RESULT_OK)
        {
          paramString = localReceiptActivityInfo;
          if (0 == 0)
          {
            localObject1 = localJSONArray;
            paramString = new ArrayList();
          }
          localObject1 = paramString;
          localJSONArray = localJSONObject.getJSONObject("data").optJSONArray("gamelist");
          localObject2 = paramString;
          if (localJSONArray != null)
          {
            int i = 0;
            while (true)
            {
              localObject1 = paramString;
              if (i >= localJSONArray.length())
                return paramString;
              localObject1 = paramString;
              localObject2 = localJSONArray.getJSONObject(i);
              localObject1 = paramString;
              localReceiptActivityInfo = new ReceiptActivityInfo();
              localObject1 = paramString;
              localReceiptActivityInfo.setId(((JSONObject)localObject2).optInt("id"));
              localObject1 = paramString;
              localReceiptActivityInfo.setTitle(((JSONObject)localObject2).optString("title"));
              localObject1 = paramString;
              localReceiptActivityInfo.setBegindate(((JSONObject)localObject2).optString("begindate"));
              localObject1 = paramString;
              localReceiptActivityInfo.setEnddate(((JSONObject)localObject2).optString("enddate"));
              localObject1 = paramString;
              localReceiptActivityInfo.setDistance(((JSONObject)localObject2).optInt("distance"));
              localObject1 = paramString;
              localReceiptActivityInfo.setPrizename(((JSONObject)localObject2).optString("prizename"));
              localObject1 = paramString;
              localReceiptActivityInfo.setClickcnt(((JSONObject)localObject2).optInt("clickcnt"));
              localObject1 = paramString;
              localReceiptActivityInfo.setJoined(((JSONObject)localObject2).optInt("joined"));
              localObject1 = paramString;
              localReceiptActivityInfo.setImgurl(((JSONObject)localObject2).optString("imgurl"));
              localObject1 = paramString;
              localReceiptActivityInfo.setPrizedetail(((JSONObject)localObject2).optString("prizedetail"));
              localObject1 = paramString;
              localReceiptActivityInfo.setShops(((JSONObject)localObject2).optString("shops"));
              localObject1 = paramString;
              localReceiptActivityInfo.setDesc(((JSONObject)localObject2).optString("desc"));
              localObject1 = paramString;
              localReceiptActivityInfo.setRules(((JSONObject)localObject2).optString("rules"));
              localObject1 = paramString;
              localReceiptActivityInfo.setDetailurl(((JSONObject)localObject2).optString("detailurl"));
              localObject1 = paramString;
              paramString.add(localReceiptActivityInfo);
              i += 1;
            }
          }
        }
      }
      catch (JSONException paramString)
      {
        paramString.printStackTrace();
        localObject2 = localObject1;
      }
    }
    return localObject2;
  }

  public static BeanDetail parseUserBeanDetailJSON(String paramString)
  {
    BeanDetail localBeanDetail = new BeanDetail();
    if (!TextUtils.isEmpty(paramString))
      try
      {
        paramString = new JSONObject(paramString);
        if (paramString.getInt("status") == ResultCode.RESULT_OK)
        {
          paramString = paramString.getJSONObject("data");
          localBeanDetail.setUserBeanNum(paramString.getInt("userBeanNum"));
          localBeanDetail.setBeanGetSum(paramString.getInt("beanGetSum"));
          localBeanDetail.setBeanUseSum(paramString.getInt("beanUseSum"));
          ArrayList localArrayList;
          Object localObject1;
          int i;
          if (!paramString.isNull("beanAllRecord"))
          {
            localArrayList = new ArrayList();
            localObject1 = paramString.getJSONArray("beanAllRecord");
            i = 0;
            if (i >= ((JSONArray)localObject1).length())
              localBeanDetail.setAllRecordList(localArrayList);
          }
          else
          {
            if (!paramString.isNull("beanGetRecord"))
            {
              localArrayList = new ArrayList();
              localObject1 = paramString.getJSONArray("beanGetRecord");
              i = 0;
              label141: if (i < ((JSONArray)localObject1).length())
                break label277;
              localBeanDetail.setGetRecordList(localArrayList);
            }
            if (paramString.isNull("beanUseRecord"))
              break label458;
            localArrayList = new ArrayList();
            paramString = paramString.getJSONArray("beanUseRecord");
            i = 0;
          }
          while (true)
          {
            if (i >= paramString.length())
            {
              localBeanDetail.setCostRecordList(localArrayList);
              return localBeanDetail;
              localObject2 = new BeanAllRecord();
              JSONObject localJSONObject = ((JSONArray)localObject1).getJSONObject(i);
              ((BeanAllRecord)localObject2).setDate(localJSONObject.getString("date"));
              ((BeanAllRecord)localObject2).setExplanation(localJSONObject.getString("item"));
              ((BeanAllRecord)localObject2).setNum(localJSONObject.getInt("num"));
              ((BeanAllRecord)localObject2).setType(localJSONObject.getInt("type"));
              localArrayList.add(localObject2);
              i += 1;
              break;
              label277: localObject2 = new BeanGetRecord();
              localJSONObject = ((JSONArray)localObject1).getJSONObject(i);
              ((BeanGetRecord)localObject2).setDate(localJSONObject.getString("date"));
              ((BeanGetRecord)localObject2).setExplanation(localJSONObject.getString("item"));
              ((BeanGetRecord)localObject2).setNum(localJSONObject.getInt("num"));
              ((BeanGetRecord)localObject2).setType(1);
              localArrayList.add(localObject2);
              i += 1;
              break label141;
            }
            localObject1 = new BeanCostRecord();
            Object localObject2 = paramString.getJSONObject(i);
            ((BeanCostRecord)localObject1).setDate(((JSONObject)localObject2).getString("date"));
            ((BeanCostRecord)localObject1).setExplanation(((JSONObject)localObject2).getString("item"));
            ((BeanCostRecord)localObject1).setNum(((JSONObject)localObject2).getInt("num"));
            ((BeanCostRecord)localObject1).setType(2);
            localArrayList.add(localObject1);
            i += 1;
          }
        }
      }
      catch (JSONException paramString)
      {
        paramString.printStackTrace();
        Log.e("Exception", "解析异常： " + paramString.getMessage());
      }
    label458: return localBeanDetail;
  }

  public static String parseUserInviteCode(String paramString)
  {
    String str = null;
    if (TextUtils.isEmpty(paramString))
      str = "";
    try
    {
      paramString = new JSONObject(paramString).getJSONObject("data").getJSONObject("user").getString("byInviteCode");
      str = paramString;
      Log.i("respose", "respose   : " + paramString);
      return paramString;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
      Log.i("Exception", paramString.getMessage().toString());
    }
    return str;
  }

  public static int parseUserJSON(String paramString)
  {
    int i = 0;
    if (TextUtils.isEmpty(paramString))
      return 0;
    try
    {
      int j = new JSONObject(paramString).getJSONObject("data").getInt("enablereplyinvite");
      i = j;
      return i;
    }
    catch (JSONException paramString)
    {
      while (true)
      {
        paramString.printStackTrace();
        Log.i("Exception", paramString.getMessage().toString());
      }
    }
  }

  // ERROR //
  public static ArrayList<com.ismartgo.app.bean.Store> parseWaterFallJson(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +16 -> 17
    //   4: aload_0
    //   5: invokevirtual 312	java/lang/String:trim	()Ljava/lang/String;
    //   8: ldc_w 290
    //   11: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   14: ifeq +11 -> 25
    //   17: new 38	java/util/ArrayList
    //   20: dup
    //   21: invokespecial 39	java/util/ArrayList:<init>	()V
    //   24: areturn
    //   25: aconst_null
    //   26: astore 5
    //   28: new 21	org/json/JSONObject
    //   31: dup
    //   32: aload_0
    //   33: invokespecial 24	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   36: astore_0
    //   37: aload_0
    //   38: ifnull +10 -> 48
    //   41: aload_0
    //   42: invokevirtual 316	org/json/JSONObject:length	()I
    //   45: ifne +11 -> 56
    //   48: new 38	java/util/ArrayList
    //   51: dup
    //   52: invokespecial 39	java/util/ArrayList:<init>	()V
    //   55: areturn
    //   56: new 38	java/util/ArrayList
    //   59: dup
    //   60: invokespecial 39	java/util/ArrayList:<init>	()V
    //   63: astore 4
    //   65: aload_0
    //   66: ldc 41
    //   68: invokevirtual 45	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   71: ldc_w 318
    //   74: invokevirtual 209	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   77: astore 5
    //   79: aload 5
    //   81: ifnull +701 -> 782
    //   84: aload 5
    //   86: invokevirtual 57	org/json/JSONArray:length	()I
    //   89: ifle +693 -> 782
    //   92: iconst_0
    //   93: istore_1
    //   94: iload_1
    //   95: aload 5
    //   97: invokevirtual 57	org/json/JSONArray:length	()I
    //   100: if_icmplt +6 -> 106
    //   103: aload 4
    //   105: areturn
    //   106: aload 5
    //   108: iload_1
    //   109: invokevirtual 60	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   112: astore 6
    //   114: new 320	java/util/LinkedList
    //   117: dup
    //   118: invokespecial 321	java/util/LinkedList:<init>	()V
    //   121: astore 7
    //   123: new 323	com/ismartgo/app/bean/Goods
    //   126: dup
    //   127: invokespecial 324	com/ismartgo/app/bean/Goods:<init>	()V
    //   130: astore 8
    //   132: new 326	com/ismartgo/app/bean/Store
    //   135: dup
    //   136: invokespecial 327	com/ismartgo/app/bean/Store:<init>	()V
    //   139: astore 9
    //   141: aload 6
    //   143: ldc_w 329
    //   146: invokevirtual 206	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   149: ifeq +422 -> 571
    //   152: iconst_0
    //   153: istore_2
    //   154: aload 8
    //   156: iload_2
    //   157: invokevirtual 332	com/ismartgo/app/bean/Goods:setGoodsId	(I)V
    //   160: new 267	java/lang/StringBuilder
    //   163: dup
    //   164: getstatic 338	com/ismartgo/app/url/Url:SERVER_URL2	Ljava/lang/String;
    //   167: invokestatic 342	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   170: invokespecial 270	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   173: astore 10
    //   175: aload 6
    //   177: ldc_w 344
    //   180: invokevirtual 206	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   183: ifeq +400 -> 583
    //   186: ldc_w 290
    //   189: astore_0
    //   190: aload 8
    //   192: aload 10
    //   194: aload_0
    //   195: invokevirtual 278	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   198: invokevirtual 281	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   201: invokevirtual 347	com/ismartgo/app/bean/Goods:setGoodsLogo	(Ljava/lang/String;)V
    //   204: aload 8
    //   206: aload 6
    //   208: ldc_w 349
    //   211: invokevirtual 185	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   214: invokevirtual 352	com/ismartgo/app/bean/Goods:setHeight	(I)V
    //   217: aload 8
    //   219: aload 6
    //   221: ldc_w 354
    //   224: invokevirtual 185	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   227: invokevirtual 357	com/ismartgo/app/bean/Goods:setWidth	(I)V
    //   230: aload 6
    //   232: ldc_w 359
    //   235: invokevirtual 206	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   238: ifeq +357 -> 595
    //   241: ldc_w 290
    //   244: astore_0
    //   245: aload 8
    //   247: aload_0
    //   248: invokevirtual 362	com/ismartgo/app/bean/Goods:setGoodsName	(Ljava/lang/String;)V
    //   251: aload 6
    //   253: ldc_w 364
    //   256: invokevirtual 206	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   259: ifeq +348 -> 607
    //   262: ldc_w 290
    //   265: astore_0
    //   266: aload 8
    //   268: aload_0
    //   269: invokevirtual 367	com/ismartgo/app/bean/Goods:setGoodsEndDate	(Ljava/lang/String;)V
    //   272: aload 6
    //   274: ldc_w 369
    //   277: invokevirtual 206	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   280: ifeq +339 -> 619
    //   283: iconst_2
    //   284: istore_2
    //   285: aload 8
    //   287: iload_2
    //   288: invokevirtual 372	com/ismartgo/app/bean/Goods:setGoodsScan	(I)V
    //   291: aload 6
    //   293: ldc_w 374
    //   296: invokevirtual 206	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   299: ifeq +338 -> 637
    //   302: iconst_0
    //   303: istore_3
    //   304: aload 8
    //   306: iload_3
    //   307: invokevirtual 378	com/ismartgo/app/bean/Goods:setCollect	(Z)V
    //   310: aload 6
    //   312: ldc_w 380
    //   315: invokevirtual 231	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   318: ldc_w 290
    //   321: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   324: ifeq +331 -> 655
    //   327: ldc_w 382
    //   330: astore_0
    //   331: aload 8
    //   333: aload_0
    //   334: invokevirtual 385	com/ismartgo/app/bean/Goods:setH5Url	(Ljava/lang/String;)V
    //   337: aload 6
    //   339: ldc_w 387
    //   342: invokevirtual 206	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   345: ifeq +322 -> 667
    //   348: iconst_0
    //   349: istore_2
    //   350: aload 8
    //   352: iload_2
    //   353: invokevirtual 390	com/ismartgo/app/bean/Goods:setShopId	(I)V
    //   356: aload 6
    //   358: ldc_w 392
    //   361: invokevirtual 206	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   364: ifeq +315 -> 679
    //   367: ldc_w 290
    //   370: astore_0
    //   371: aload 8
    //   373: aload_0
    //   374: invokevirtual 395	com/ismartgo/app/bean/Goods:setGoodsDescribe	(Ljava/lang/String;)V
    //   377: aload 6
    //   379: ldc_w 387
    //   382: invokevirtual 206	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   385: ifeq +306 -> 691
    //   388: iconst_0
    //   389: istore_2
    //   390: aload 9
    //   392: iload_2
    //   393: invokevirtual 396	com/ismartgo/app/bean/Store:setShopId	(I)V
    //   396: new 267	java/lang/StringBuilder
    //   399: dup
    //   400: getstatic 338	com/ismartgo/app/url/Url:SERVER_URL2	Ljava/lang/String;
    //   403: invokestatic 342	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   406: invokespecial 270	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   409: astore 10
    //   411: aload 6
    //   413: ldc_w 398
    //   416: invokevirtual 206	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   419: ifeq +284 -> 703
    //   422: ldc_w 290
    //   425: astore_0
    //   426: aload 9
    //   428: aload 10
    //   430: aload_0
    //   431: invokevirtual 278	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   434: invokevirtual 281	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   437: invokevirtual 401	com/ismartgo/app/bean/Store:setShopLogo	(Ljava/lang/String;)V
    //   440: aload 6
    //   442: ldc_w 403
    //   445: invokevirtual 206	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   448: ifeq +267 -> 715
    //   451: ldc_w 290
    //   454: astore_0
    //   455: aload 9
    //   457: aload_0
    //   458: invokevirtual 406	com/ismartgo/app/bean/Store:setShopName	(Ljava/lang/String;)V
    //   461: aload 6
    //   463: ldc_w 408
    //   466: invokevirtual 206	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   469: ifeq +258 -> 727
    //   472: ldc_w 290
    //   475: astore_0
    //   476: aload 9
    //   478: aload_0
    //   479: invokevirtual 411	com/ismartgo/app/bean/Store:setShopAddress	(Ljava/lang/String;)V
    //   482: aload 9
    //   484: aload 6
    //   486: ldc_w 413
    //   489: invokevirtual 231	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   492: invokestatic 419	java/lang/Double:parseDouble	(Ljava/lang/String;)D
    //   495: invokevirtual 423	com/ismartgo/app/bean/Store:setLon	(D)V
    //   498: aload 9
    //   500: aload 6
    //   502: ldc_w 425
    //   505: invokevirtual 231	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   508: invokestatic 419	java/lang/Double:parseDouble	(Ljava/lang/String;)D
    //   511: invokevirtual 428	com/ismartgo/app/bean/Store:setLat	(D)V
    //   514: aload 9
    //   516: aload 6
    //   518: ldc 144
    //   520: invokevirtual 231	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   523: invokevirtual 430	com/ismartgo/app/bean/Store:setDistance	(Ljava/lang/String;)V
    //   526: aload 9
    //   528: aload 6
    //   530: ldc_w 432
    //   533: invokevirtual 231	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   536: invokevirtual 435	com/ismartgo/app/bean/Store:setReatilName	(Ljava/lang/String;)V
    //   539: aload 7
    //   541: aload 8
    //   543: invokeinterface 127 2 0
    //   548: pop
    //   549: aload 9
    //   551: aload 7
    //   553: invokevirtual 438	com/ismartgo/app/bean/Store:setGoods_list	(Ljava/util/List;)V
    //   556: aload 4
    //   558: aload 9
    //   560: invokevirtual 439	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   563: pop
    //   564: iload_1
    //   565: iconst_1
    //   566: iadd
    //   567: istore_1
    //   568: goto -474 -> 94
    //   571: aload 6
    //   573: ldc_w 329
    //   576: invokevirtual 185	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   579: istore_2
    //   580: goto -426 -> 154
    //   583: aload 6
    //   585: ldc_w 344
    //   588: invokevirtual 231	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   591: astore_0
    //   592: goto -402 -> 190
    //   595: aload 6
    //   597: ldc_w 359
    //   600: invokevirtual 231	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   603: astore_0
    //   604: goto -359 -> 245
    //   607: aload 6
    //   609: ldc_w 364
    //   612: invokevirtual 231	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   615: astore_0
    //   616: goto -350 -> 266
    //   619: aload 6
    //   621: ldc_w 369
    //   624: invokevirtual 231	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   627: invokestatic 444	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   630: invokevirtual 447	java/lang/Integer:intValue	()I
    //   633: istore_2
    //   634: goto -349 -> 285
    //   637: aload 6
    //   639: ldc_w 374
    //   642: invokevirtual 231	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   645: ldc_w 449
    //   648: invokevirtual 452	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   651: istore_3
    //   652: goto -348 -> 304
    //   655: aload 6
    //   657: ldc_w 380
    //   660: invokevirtual 231	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   663: astore_0
    //   664: goto -333 -> 331
    //   667: aload 6
    //   669: ldc_w 387
    //   672: invokevirtual 185	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   675: istore_2
    //   676: goto -326 -> 350
    //   679: aload 6
    //   681: ldc_w 392
    //   684: invokevirtual 231	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   687: astore_0
    //   688: goto -317 -> 371
    //   691: aload 6
    //   693: ldc_w 387
    //   696: invokevirtual 185	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   699: istore_2
    //   700: goto -310 -> 390
    //   703: aload 6
    //   705: ldc_w 398
    //   708: invokevirtual 231	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   711: astore_0
    //   712: goto -286 -> 426
    //   715: aload 6
    //   717: ldc_w 403
    //   720: invokevirtual 231	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   723: astore_0
    //   724: goto -269 -> 455
    //   727: aload 6
    //   729: ldc_w 408
    //   732: invokevirtual 231	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   735: astore_0
    //   736: goto -260 -> 476
    //   739: astore_0
    //   740: aload 5
    //   742: astore 4
    //   744: ldc_w 454
    //   747: new 267	java/lang/StringBuilder
    //   750: dup
    //   751: ldc_w 456
    //   754: invokespecial 270	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   757: aload_0
    //   758: invokevirtual 274	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   761: invokevirtual 278	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   764: invokevirtual 281	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   767: invokestatic 301	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   770: pop
    //   771: aload_0
    //   772: invokevirtual 130	org/json/JSONException:printStackTrace	()V
    //   775: aload 4
    //   777: areturn
    //   778: astore_0
    //   779: goto -35 -> 744
    //   782: aload 4
    //   784: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   28	37	739	org/json/JSONException
    //   41	48	739	org/json/JSONException
    //   48	56	739	org/json/JSONException
    //   56	65	739	org/json/JSONException
    //   65	79	778	org/json/JSONException
    //   84	92	778	org/json/JSONException
    //   94	103	778	org/json/JSONException
    //   106	152	778	org/json/JSONException
    //   154	186	778	org/json/JSONException
    //   190	241	778	org/json/JSONException
    //   245	262	778	org/json/JSONException
    //   266	283	778	org/json/JSONException
    //   285	302	778	org/json/JSONException
    //   304	327	778	org/json/JSONException
    //   331	348	778	org/json/JSONException
    //   350	367	778	org/json/JSONException
    //   371	388	778	org/json/JSONException
    //   390	422	778	org/json/JSONException
    //   426	451	778	org/json/JSONException
    //   455	472	778	org/json/JSONException
    //   476	564	778	org/json/JSONException
    //   571	580	778	org/json/JSONException
    //   583	592	778	org/json/JSONException
    //   595	604	778	org/json/JSONException
    //   607	616	778	org/json/JSONException
    //   619	634	778	org/json/JSONException
    //   637	652	778	org/json/JSONException
    //   655	664	778	org/json/JSONException
    //   667	676	778	org/json/JSONException
    //   679	688	778	org/json/JSONException
    //   691	700	778	org/json/JSONException
    //   703	712	778	org/json/JSONException
    //   715	724	778	org/json/JSONException
    //   727	736	778	org/json/JSONException
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.utils.ResponseJsonUtils
 * JD-Core Version:    0.6.2
 */