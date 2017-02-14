package com.ismartgo.beacon.http;

import com.ismartgo.beacon.pojo.InitAppInfo;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpJsonParse
{
  // ERROR //
  public static java.util.List<com.ismartgo.beacon.pojo.BeaconActivityInfo> parseJson_ActivityInfo(String paramString)
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
    //   14: invokevirtual 24	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   17: sipush 10001
    //   20: if_icmpne +148 -> 168
    //   23: new 26	java/util/ArrayList
    //   26: dup
    //   27: invokespecial 27	java/util/ArrayList:<init>	()V
    //   30: astore_2
    //   31: aload_0
    //   32: ldc 29
    //   34: invokevirtual 33	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   37: ldc 35
    //   39: invokevirtual 39	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   42: astore_3
    //   43: iconst_0
    //   44: istore_1
    //   45: iload_1
    //   46: aload_3
    //   47: invokevirtual 45	org/json/JSONArray:length	()I
    //   50: if_icmplt +5 -> 55
    //   53: aload_2
    //   54: areturn
    //   55: aload_3
    //   56: iload_1
    //   57: invokevirtual 48	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   60: astore 4
    //   62: new 50	com/ismartgo/beacon/pojo/BeaconActivityInfo
    //   65: dup
    //   66: aload 4
    //   68: ldc 52
    //   70: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   73: aload 4
    //   75: ldc 58
    //   77: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   80: aload 4
    //   82: ldc 60
    //   84: invokevirtual 24	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   87: aload 4
    //   89: ldc 62
    //   91: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   94: invokevirtual 68	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   97: aload 4
    //   99: ldc 70
    //   101: invokevirtual 24	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   104: aload 4
    //   106: ldc 72
    //   108: invokevirtual 24	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   111: invokespecial 75	com/ismartgo/beacon/pojo/BeaconActivityInfo:<init>	(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;II)V
    //   114: astore 5
    //   116: aload 5
    //   118: aload_0
    //   119: ldc 77
    //   121: invokevirtual 56	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   124: invokevirtual 80	com/ismartgo/beacon/pojo/BeaconActivityInfo:setTime	(Ljava/lang/String;)V
    //   127: aload 5
    //   129: aload 4
    //   131: ldc 82
    //   133: invokevirtual 24	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   136: invokevirtual 86	com/ismartgo/beacon/pojo/BeaconActivityInfo:setActivityId	(I)V
    //   139: aload_2
    //   140: aload 5
    //   142: invokeinterface 92 2 0
    //   147: pop
    //   148: iload_1
    //   149: iconst_1
    //   150: iadd
    //   151: istore_1
    //   152: goto -107 -> 45
    //   155: astore_0
    //   156: aload_3
    //   157: astore_2
    //   158: aload_0
    //   159: invokevirtual 95	org/json/JSONException:printStackTrace	()V
    //   162: aload_2
    //   163: areturn
    //   164: astore_0
    //   165: goto -7 -> 158
    //   168: aconst_null
    //   169: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   2	31	155	org/json/JSONException
    //   31	43	164	org/json/JSONException
    //   45	53	164	org/json/JSONException
    //   55	148	164	org/json/JSONException
  }

  public static InitAppInfo parseJson_InitApp(String paramString)
  {
    InitAppInfo localInitAppInfo = new InitAppInfo();
    try
    {
      paramString = new JSONObject(paramString);
      localInitAppInfo.setStatus(paramString.getInt("status"));
      localInitAppInfo.setMsg(paramString.getString("msg"));
      localInitAppInfo.setTime(paramString.getString("time"));
      if (paramString.getInt("status") == 10001)
        localInitAppInfo.setUuid(paramString.getJSONObject("data").getString("uuid"));
      return localInitAppInfo;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
    return localInitAppInfo;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.beacon.http.HttpJsonParse
 * JD-Core Version:    0.6.2
 */