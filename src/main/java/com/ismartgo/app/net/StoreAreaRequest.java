package com.ismartgo.app.net;

import android.content.Context;
import android.util.Log;
import com.ismartgo.app.bean.ScreenStore;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.interfaces.OnCompleteListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreAreaRequest
  implements HttpCallback<String>
{
  private Context context;
  private OnCompleteListener onCompleteListener = null;
  private Map<String, String> params = new HashMap();
  List<ScreenStore> screenStore_list;
  private String url;

  public StoreAreaRequest(Context paramContext, String paramString)
  {
    this.url = paramString;
    this.context = paramContext;
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
    //   0: iconst_0
    //   1: istore_1
    //   2: aload_0
    //   3: new 56	java/util/ArrayList
    //   6: dup
    //   7: invokespecial 57	java/util/ArrayList:<init>	()V
    //   10: putfield 59	com/ismartgo/app/net/StoreAreaRequest:screenStore_list	Ljava/util/List;
    //   13: aload_2
    //   14: ifnull +93 -> 107
    //   17: aload_0
    //   18: getfield 26	com/ismartgo/app/net/StoreAreaRequest:onCompleteListener	Lcom/ismartgo/app/interfaces/OnCompleteListener;
    //   21: ifnull +86 -> 107
    //   24: iload_1
    //   25: istore_3
    //   26: new 61	org/json/JSONObject
    //   29: dup
    //   30: aload_2
    //   31: invokeinterface 67 1 0
    //   36: checkcast 69	java/lang/String
    //   39: invokevirtual 70	java/lang/String:toString	()Ljava/lang/String;
    //   42: invokespecial 73	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   45: astore_2
    //   46: iload_1
    //   47: istore_3
    //   48: aload_2
    //   49: ldc 75
    //   51: invokevirtual 79	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   54: ldc 81
    //   56: invokevirtual 85	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   59: astore 6
    //   61: iload_1
    //   62: istore_3
    //   63: aload_2
    //   64: ldc 87
    //   66: invokevirtual 91	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   69: invokestatic 97	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   72: invokevirtual 101	java/lang/Integer:intValue	()I
    //   75: istore_1
    //   76: iconst_0
    //   77: istore 4
    //   79: aconst_null
    //   80: astore_2
    //   81: aload 6
    //   83: invokevirtual 106	org/json/JSONArray:length	()I
    //   86: istore_3
    //   87: iload 4
    //   89: iload_3
    //   90: if_icmplt +18 -> 108
    //   93: aload_0
    //   94: getfield 26	com/ismartgo/app/net/StoreAreaRequest:onCompleteListener	Lcom/ismartgo/app/interfaces/OnCompleteListener;
    //   97: aload_0
    //   98: getfield 59	com/ismartgo/app/net/StoreAreaRequest:screenStore_list	Ljava/util/List;
    //   101: iload_1
    //   102: invokeinterface 110 3 0
    //   107: return
    //   108: aload 6
    //   110: iload 4
    //   112: invokevirtual 113	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   115: checkcast 61	org/json/JSONObject
    //   118: astore 7
    //   120: new 115	com/ismartgo/app/bean/ScreenStore
    //   123: dup
    //   124: invokespecial 116	com/ismartgo/app/bean/ScreenStore:<init>	()V
    //   127: astore_2
    //   128: iload_1
    //   129: istore_3
    //   130: aload 7
    //   132: ldc 118
    //   134: invokevirtual 122	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   137: ifeq +49 -> 186
    //   140: iconst_m1
    //   141: istore 5
    //   143: iload_1
    //   144: istore_3
    //   145: aload_2
    //   146: iload 5
    //   148: invokevirtual 126	com/ismartgo/app/bean/ScreenStore:setShopTypeId	(I)V
    //   151: iload_1
    //   152: istore_3
    //   153: aload_2
    //   154: aload 7
    //   156: ldc 128
    //   158: invokevirtual 91	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   161: invokevirtual 131	com/ismartgo/app/bean/ScreenStore:setName	(Ljava/lang/String;)V
    //   164: iload_1
    //   165: istore_3
    //   166: aload_0
    //   167: getfield 59	com/ismartgo/app/net/StoreAreaRequest:screenStore_list	Ljava/util/List;
    //   170: aload_2
    //   171: invokeinterface 137 2 0
    //   176: pop
    //   177: iload 4
    //   179: iconst_1
    //   180: iadd
    //   181: istore 4
    //   183: goto -102 -> 81
    //   186: iload_1
    //   187: istore_3
    //   188: aload 7
    //   190: ldc 118
    //   192: invokevirtual 141	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   195: istore 5
    //   197: goto -54 -> 143
    //   200: astore_2
    //   201: iload_3
    //   202: istore_1
    //   203: aload_2
    //   204: invokevirtual 144	org/json/JSONException:printStackTrace	()V
    //   207: goto -114 -> 93
    //   210: astore_2
    //   211: goto -8 -> 203
    //
    // Exception table:
    //   from	to	target	type
    //   26	46	200	org/json/JSONException
    //   48	61	200	org/json/JSONException
    //   63	76	200	org/json/JSONException
    //   130	140	200	org/json/JSONException
    //   145	151	200	org/json/JSONException
    //   153	164	200	org/json/JSONException
    //   166	177	200	org/json/JSONException
    //   188	197	200	org/json/JSONException
    //   81	87	210	org/json/JSONException
    //   108	128	210	org/json/JSONException
  }

  public void setOnCompleteListener(OnCompleteListener paramOnCompleteListener)
  {
    this.onCompleteListener = paramOnCompleteListener;
  }

  public void startRequest()
  {
    Log.e("Home", this.url + "?" + this.params);
    HttpRequest.getInstance().executePostStringRequest(this.context, this.url, 35, this.params, this);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.net.StoreAreaRequest
 * JD-Core Version:    0.6.2
 */