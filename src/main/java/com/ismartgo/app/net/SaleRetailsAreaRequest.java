package com.ismartgo.app.net;

import android.content.Context;
import android.util.Log;
import com.ismartgo.app.bean.SalesArea;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.interfaces.OnCompleteListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaleRetailsAreaRequest
  implements HttpCallback<String>
{
  List<SalesArea> area_list;
  private Context context;
  private OnCompleteListener onCompleteListener = null;
  private Map<String, String> params = new HashMap();
  private String url;

  public SaleRetailsAreaRequest(Context paramContext, String paramString)
  {
    this.url = paramString;
    this.context = paramContext;
  }

  public void initParams(int paramInt)
  {
    this.params.put("shopTypeId", paramInt);
  }

  public void initParams(String paramString1, String paramString2)
  {
    this.params.put(paramString1, paramString2);
  }

  public void onFailed(int paramInt, String paramString, Object paramObject, CharSequence paramCharSequence)
  {
    Log.i("Test", "零售商数据失败：");
    if (this.onCompleteListener != null)
      this.onCompleteListener.onFailure(null, paramInt, paramCharSequence.toString());
  }

  // ERROR //
  public void onSucceed(int paramInt, com.yolanda.nohttp.Response<String> paramResponse)
  {
    // Byte code:
    //   0: ldc 89
    //   2: new 42	java/lang/StringBuilder
    //   5: dup
    //   6: ldc 91
    //   8: invokespecial 51	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   11: aload_2
    //   12: invokeinterface 97 1 0
    //   17: checkcast 44	java/lang/String
    //   20: invokevirtual 98	java/lang/String:toString	()Ljava/lang/String;
    //   23: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   26: invokevirtual 55	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   29: invokestatic 74	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   32: pop
    //   33: iconst_0
    //   34: istore_1
    //   35: aload_0
    //   36: new 104	java/util/ArrayList
    //   39: dup
    //   40: invokespecial 105	java/util/ArrayList:<init>	()V
    //   43: putfield 107	com/ismartgo/app/net/SaleRetailsAreaRequest:area_list	Ljava/util/List;
    //   46: aload_2
    //   47: ifnull +93 -> 140
    //   50: aload_0
    //   51: getfield 26	com/ismartgo/app/net/SaleRetailsAreaRequest:onCompleteListener	Lcom/ismartgo/app/interfaces/OnCompleteListener;
    //   54: ifnull +86 -> 140
    //   57: iload_1
    //   58: istore_3
    //   59: new 109	org/json/JSONObject
    //   62: dup
    //   63: aload_2
    //   64: invokeinterface 97 1 0
    //   69: checkcast 44	java/lang/String
    //   72: invokevirtual 98	java/lang/String:toString	()Ljava/lang/String;
    //   75: invokespecial 110	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   78: astore_2
    //   79: iload_1
    //   80: istore_3
    //   81: aload_2
    //   82: ldc 112
    //   84: invokevirtual 116	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   87: ldc 118
    //   89: invokevirtual 122	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   92: astore 5
    //   94: iload_1
    //   95: istore_3
    //   96: aload_2
    //   97: ldc 124
    //   99: invokevirtual 128	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   102: invokestatic 133	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   105: invokevirtual 137	java/lang/Integer:intValue	()I
    //   108: istore_1
    //   109: iconst_0
    //   110: istore 4
    //   112: aconst_null
    //   113: astore_2
    //   114: aload 5
    //   116: invokevirtual 142	org/json/JSONArray:length	()I
    //   119: istore_3
    //   120: iload 4
    //   122: iload_3
    //   123: if_icmplt +18 -> 141
    //   126: aload_0
    //   127: getfield 26	com/ismartgo/app/net/SaleRetailsAreaRequest:onCompleteListener	Lcom/ismartgo/app/interfaces/OnCompleteListener;
    //   130: aload_0
    //   131: getfield 107	com/ismartgo/app/net/SaleRetailsAreaRequest:area_list	Ljava/util/List;
    //   134: iload_1
    //   135: invokeinterface 146 3 0
    //   140: return
    //   141: aload 5
    //   143: iload 4
    //   145: invokevirtual 149	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   148: checkcast 109	org/json/JSONObject
    //   151: astore 6
    //   153: new 151	com/ismartgo/app/bean/SalesArea
    //   156: dup
    //   157: invokespecial 152	com/ismartgo/app/bean/SalesArea:<init>	()V
    //   160: astore_2
    //   161: iload_1
    //   162: istore_3
    //   163: aload_2
    //   164: getstatic 158	com/ismartgo/app/service/IbeaconService:districtId	I
    //   167: invokevirtual 161	com/ismartgo/app/bean/SalesArea:setDistrictId	(I)V
    //   170: iload_1
    //   171: istore_3
    //   172: aload_2
    //   173: getstatic 164	com/ismartgo/app/service/IbeaconService:townId	I
    //   176: invokevirtual 167	com/ismartgo/app/bean/SalesArea:setTownId	(I)V
    //   179: iload_1
    //   180: istore_3
    //   181: aload_2
    //   182: getstatic 169	com/ismartgo/app/service/IbeaconService:shopTypeId	I
    //   185: invokevirtual 172	com/ismartgo/app/bean/SalesArea:setShopTypeId	(I)V
    //   188: iload_1
    //   189: istore_3
    //   190: aload_2
    //   191: aload 6
    //   193: ldc 174
    //   195: invokevirtual 178	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   198: invokevirtual 181	com/ismartgo/app/bean/SalesArea:setRetailId	(I)V
    //   201: iload_1
    //   202: istore_3
    //   203: aload_2
    //   204: aload 6
    //   206: ldc 183
    //   208: invokevirtual 128	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   211: invokevirtual 186	com/ismartgo/app/bean/SalesArea:setName	(Ljava/lang/String;)V
    //   214: iload_1
    //   215: istore_3
    //   216: aload_0
    //   217: getfield 107	com/ismartgo/app/net/SaleRetailsAreaRequest:area_list	Ljava/util/List;
    //   220: aload_2
    //   221: invokeinterface 192 2 0
    //   226: pop
    //   227: iload 4
    //   229: iconst_1
    //   230: iadd
    //   231: istore 4
    //   233: goto -119 -> 114
    //   236: astore_2
    //   237: iload_3
    //   238: istore_1
    //   239: aload_2
    //   240: invokevirtual 195	org/json/JSONException:printStackTrace	()V
    //   243: goto -117 -> 126
    //   246: astore_2
    //   247: goto -8 -> 239
    //
    // Exception table:
    //   from	to	target	type
    //   59	79	236	org/json/JSONException
    //   81	94	236	org/json/JSONException
    //   96	109	236	org/json/JSONException
    //   163	170	236	org/json/JSONException
    //   172	179	236	org/json/JSONException
    //   181	188	236	org/json/JSONException
    //   190	201	236	org/json/JSONException
    //   203	214	236	org/json/JSONException
    //   216	227	236	org/json/JSONException
    //   114	120	246	org/json/JSONException
    //   141	161	246	org/json/JSONException
  }

  public void setOnCompleteListener(OnCompleteListener paramOnCompleteListener)
  {
    this.onCompleteListener = paramOnCompleteListener;
  }

  public void startRequest()
  {
    Log.e("Home", "零售商地址： " + this.url + "?" + this.params);
    HttpRequest.getInstance().executePostStringRequest(this.context, this.url, 21, this.params, this);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.net.SaleRetailsAreaRequest
 * JD-Core Version:    0.6.2
 */