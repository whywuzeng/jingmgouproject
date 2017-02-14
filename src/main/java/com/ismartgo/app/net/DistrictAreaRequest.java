package com.ismartgo.app.net;

import android.content.Context;
import android.util.Log;
import com.ismartgo.app.bean.DistrictArea;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.http.HttpWhat;
import com.ismartgo.app.interfaces.OnCompleteListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistrictAreaRequest
  implements HttpCallback<String>
{
  List<DistrictArea> area_list;
  private Context context;
  private String disname;
  public String near_by = "附近";
  private OnCompleteListener onCompleteListener = null;
  private Map<String, String> params;
  private String url;

  public DistrictAreaRequest(Context paramContext, String paramString)
  {
    this.context = paramContext;
    this.url = paramString;
    this.params = new HashMap();
  }

  public void initParams(String paramString1, int paramInt, String paramString2)
  {
    this.disname = paramString2;
    this.params.put("cityName", paramString1);
    paramString2 = this.params;
    if (paramInt == 0);
    for (paramString1 = ""; ; paramString1 = paramInt)
    {
      paramString2.put("districtId", paramString1);
      return;
    }
  }

  public void onFailed(int paramInt, String paramString, Object paramObject, CharSequence paramCharSequence)
  {
    if (this.onCompleteListener != null)
      this.onCompleteListener.onFailure(null, paramInt, paramString);
  }

  // ERROR //
  public void onSucceed(int paramInt, com.yolanda.nohttp.Response<String> paramResponse)
  {
    // Byte code:
    //   0: getstatic 93	java/lang/System:out	Ljava/io/PrintStream;
    //   3: new 60	java/lang/StringBuilder
    //   6: dup
    //   7: ldc 95
    //   9: invokespecial 69	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   12: aload_2
    //   13: invokeinterface 101 1 0
    //   18: checkcast 62	java/lang/String
    //   21: invokevirtual 102	java/lang/String:toString	()Ljava/lang/String;
    //   24: invokevirtual 106	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: invokevirtual 73	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   30: invokevirtual 111	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   33: iconst_0
    //   34: istore_1
    //   35: aload_0
    //   36: new 113	java/util/ArrayList
    //   39: dup
    //   40: invokespecial 114	java/util/ArrayList:<init>	()V
    //   43: putfield 116	com/ismartgo/app/net/DistrictAreaRequest:area_list	Ljava/util/List;
    //   46: aload_2
    //   47: invokeinterface 101 1 0
    //   52: checkcast 62	java/lang/String
    //   55: invokevirtual 102	java/lang/String:toString	()Ljava/lang/String;
    //   58: ifnull +98 -> 156
    //   61: aload_0
    //   62: getfield 28	com/ismartgo/app/net/DistrictAreaRequest:onCompleteListener	Lcom/ismartgo/app/interfaces/OnCompleteListener;
    //   65: ifnull +91 -> 156
    //   68: iload_1
    //   69: istore_3
    //   70: new 118	org/json/JSONObject
    //   73: dup
    //   74: aload_2
    //   75: invokeinterface 101 1 0
    //   80: checkcast 62	java/lang/String
    //   83: invokevirtual 102	java/lang/String:toString	()Ljava/lang/String;
    //   86: invokespecial 119	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   89: astore_2
    //   90: iload_1
    //   91: istore_3
    //   92: aload_2
    //   93: ldc 121
    //   95: invokevirtual 125	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   98: ldc 127
    //   100: invokevirtual 125	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   103: ldc 129
    //   105: invokevirtual 133	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   108: astore 6
    //   110: iload_1
    //   111: istore_3
    //   112: aload_2
    //   113: ldc 135
    //   115: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   118: invokestatic 144	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   121: invokevirtual 148	java/lang/Integer:intValue	()I
    //   124: istore_1
    //   125: iconst_0
    //   126: istore 4
    //   128: aconst_null
    //   129: astore_2
    //   130: aload 6
    //   132: invokevirtual 153	org/json/JSONArray:length	()I
    //   135: istore_3
    //   136: iload 4
    //   138: iload_3
    //   139: if_icmplt +18 -> 157
    //   142: aload_0
    //   143: getfield 28	com/ismartgo/app/net/DistrictAreaRequest:onCompleteListener	Lcom/ismartgo/app/interfaces/OnCompleteListener;
    //   146: aload_0
    //   147: getfield 116	com/ismartgo/app/net/DistrictAreaRequest:area_list	Ljava/util/List;
    //   150: iload_1
    //   151: invokeinterface 157 3 0
    //   156: return
    //   157: aload 6
    //   159: iload 4
    //   161: invokevirtual 160	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   164: checkcast 118	org/json/JSONObject
    //   167: astore 7
    //   169: new 162	com/ismartgo/app/bean/DistrictArea
    //   172: dup
    //   173: invokespecial 163	com/ismartgo/app/bean/DistrictArea:<init>	()V
    //   176: astore_2
    //   177: iload_1
    //   178: istore_3
    //   179: aload_0
    //   180: getfield 46	com/ismartgo/app/net/DistrictAreaRequest:disname	Ljava/lang/String;
    //   183: aload_0
    //   184: getfield 32	com/ismartgo/app/net/DistrictAreaRequest:near_by	Ljava/lang/String;
    //   187: invokevirtual 167	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   190: istore 5
    //   192: iload 5
    //   194: ifeq +86 -> 280
    //   197: iload_1
    //   198: istore_3
    //   199: aload_2
    //   200: aload 7
    //   202: ldc 169
    //   204: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   207: invokevirtual 102	java/lang/String:toString	()Ljava/lang/String;
    //   210: invokestatic 173	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   213: invokevirtual 177	com/ismartgo/app/bean/DistrictArea:setDistance	(I)V
    //   216: iload_1
    //   217: istore_3
    //   218: aload_2
    //   219: aload 7
    //   221: ldc 169
    //   223: invokevirtual 139	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   226: invokevirtual 180	com/ismartgo/app/bean/DistrictArea:setName	(Ljava/lang/String;)V
    //   229: iload_1
    //   230: istore_3
    //   231: aload_0
    //   232: getfield 116	com/ismartgo/app/net/DistrictAreaRequest:area_list	Ljava/util/List;
    //   235: aload_2
    //   236: invokeinterface 185 2 0
    //   241: pop
    //   242: iload 4
    //   244: iconst_1
    //   245: iadd
    //   246: istore 4
    //   248: goto -118 -> 130
    //   251: astore 8
    //   253: iload_1
    //   254: istore_3
    //   255: aload_2
    //   256: iconst_m1
    //   257: invokevirtual 188	com/ismartgo/app/bean/DistrictArea:setTownId	(I)V
    //   260: iload_1
    //   261: istore_3
    //   262: aload_2
    //   263: iconst_m1
    //   264: invokevirtual 191	com/ismartgo/app/bean/DistrictArea:setDistrictId	(I)V
    //   267: goto -51 -> 216
    //   270: astore_2
    //   271: iload_3
    //   272: istore_1
    //   273: aload_2
    //   274: invokevirtual 194	org/json/JSONException:printStackTrace	()V
    //   277: goto -135 -> 142
    //   280: iload_1
    //   281: istore_3
    //   282: aload 7
    //   284: ldc 58
    //   286: invokevirtual 198	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   289: ifeq +19 -> 308
    //   292: iload_1
    //   293: istore_3
    //   294: aload_2
    //   295: aload 7
    //   297: ldc 200
    //   299: invokevirtual 203	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   302: invokevirtual 188	com/ismartgo/app/bean/DistrictArea:setTownId	(I)V
    //   305: goto -89 -> 216
    //   308: iload_1
    //   309: istore_3
    //   310: aload 7
    //   312: ldc 200
    //   314: invokevirtual 198	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   317: ifeq -101 -> 216
    //   320: iload_1
    //   321: istore_3
    //   322: aload_2
    //   323: aload 7
    //   325: ldc 58
    //   327: invokevirtual 203	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   330: invokevirtual 191	com/ismartgo/app/bean/DistrictArea:setDistrictId	(I)V
    //   333: goto -117 -> 216
    //   336: astore_2
    //   337: goto -64 -> 273
    //
    // Exception table:
    //   from	to	target	type
    //   199	216	251	java/lang/Exception
    //   70	90	270	org/json/JSONException
    //   92	110	270	org/json/JSONException
    //   112	125	270	org/json/JSONException
    //   179	192	270	org/json/JSONException
    //   199	216	270	org/json/JSONException
    //   218	229	270	org/json/JSONException
    //   231	242	270	org/json/JSONException
    //   255	260	270	org/json/JSONException
    //   262	267	270	org/json/JSONException
    //   282	292	270	org/json/JSONException
    //   294	305	270	org/json/JSONException
    //   310	320	270	org/json/JSONException
    //   322	333	270	org/json/JSONException
    //   130	136	336	org/json/JSONException
    //   157	177	336	org/json/JSONException
  }

  public void setOnCompleteListener(OnCompleteListener paramOnCompleteListener)
  {
    this.onCompleteListener = paramOnCompleteListener;
  }

  public void startRequest()
  {
    Log.e("Home", this.url + "?" + this.params);
    HttpRequest.getInstance().executePostStringRequest(this.context, this.url, HttpWhat.DISTRICT_AREA, this.params, this);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.net.DistrictAreaRequest
 * JD-Core Version:    0.6.2
 */