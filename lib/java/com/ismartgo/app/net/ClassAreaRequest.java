package com.ismartgo.app.net;

import android.content.Context;
import android.util.Log;
import com.ismartgo.app.bean.GoodsClazz;
import com.ismartgo.app.bean.ScreenStore;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.interfaces.OnCompleteListener;
import com.yolanda.nohttp.Response;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.tsz.afinal.FinalHttp;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ClassAreaRequest
  implements HttpCallback<String>
{
  public static FinalHttp http = null;
  private Context context;
  private List<GoodsClazz> goodsClazzList;
  private int level = 1;
  private OnCompleteListener onCompleteListener = null;
  private Map<String, String> params;
  private List<ScreenStore> screenStoreList;
  public int shopTypeId;
  private String url;

  public ClassAreaRequest(Context paramContext, String paramString)
  {
    this.context = paramContext;
    if (this.params == null)
      this.params = new HashMap();
    if (http == null)
      http = new FinalHttp();
    this.url = paramString;
  }

  public void execute()
  {
    Log.i("hahaha", "品类数据： " + this.url);
    HttpRequest.getInstance().executePostStringRequest(this.context, this.url, 19, null, this);
  }

  public void initParams(int paramInt)
  {
    this.params.put("parentId", paramInt);
    this.level = 2;
  }

  public void onFailed(int paramInt, String paramString, Object paramObject, CharSequence paramCharSequence)
  {
    if (this.onCompleteListener != null)
      this.onCompleteListener.onFailure(null, paramInt, paramCharSequence.toString());
  }

  public void onSucceed(int paramInt, Response<String> paramResponse)
  {
    Log.i("hahaha", "品类数据： " + ((String)paramResponse.get()).toString());
    this.goodsClazzList = new ArrayList();
    this.screenStoreList = new ArrayList();
    while (true)
    {
      int k;
      try
      {
        paramResponse = new JSONObject(((String)paramResponse.get()).toString());
        k = Integer.valueOf(paramResponse.optString("status")).intValue();
        if (k != 10001)
          break label455;
        paramResponse = paramResponse.optJSONObject("data").optJSONArray("shopTypes");
        if (paramResponse != null)
          break label474;
        return;
        if (paramInt >= paramResponse.length())
        {
          com.ismartgo.app.service.IbeaconService.screenStore_area = this.screenStoreList;
          this.onCompleteListener.onResult(this.goodsClazzList, k);
          return;
        }
      }
      catch (JSONException paramResponse)
      {
        Log.i("hahaha", "json异常： " + paramResponse.getMessage());
        paramResponse.printStackTrace();
        return;
      }
      Object localObject1 = paramResponse.optJSONObject(paramInt);
      int m = ((JSONObject)localObject1).optInt("shopTypeId");
      String str = ((JSONObject)localObject1).optString("shopTypeName");
      Object localObject2 = new ScreenStore();
      ((ScreenStore)localObject2).setShopTypeId(m);
      ((ScreenStore)localObject2).setName(str);
      this.screenStoreList.add(localObject2);
      localObject1 = ((JSONObject)localObject1).getJSONArray("category1");
      localObject2 = new ArrayList();
      int i = 0;
      if (i >= ((JSONArray)localObject1).length())
      {
        this.goodsClazzList.addAll((Collection)localObject2);
        paramInt += 1;
      }
      else
      {
        Object localObject3 = ((JSONArray)localObject1).optJSONObject(i);
        GoodsClazz localGoodsClazz1 = new GoodsClazz();
        localGoodsClazz1.setShopTypeId(m);
        localGoodsClazz1.setShopTypeName(str);
        localGoodsClazz1.setClazzId(((JSONObject)localObject3).optInt("id"));
        localGoodsClazz1.setName(((JSONObject)localObject3).optString("name"));
        localObject3 = ((JSONObject)localObject3).optJSONArray("category2");
        ArrayList localArrayList = new ArrayList();
        int j = 0;
        while (true)
        {
          if (j >= ((JSONArray)localObject3).length())
          {
            localGoodsClazz1.setGoodsClazzList(localArrayList);
            ((List)localObject2).add(localGoodsClazz1);
            i += 1;
            break;
          }
          JSONObject localJSONObject = ((JSONArray)localObject3).optJSONObject(j);
          GoodsClazz localGoodsClazz2 = new GoodsClazz();
          localGoodsClazz2.setClazzId(localJSONObject.optInt("id"));
          localGoodsClazz2.setName(localJSONObject.optString("name"));
          localArrayList.add(localGoodsClazz2);
          j += 1;
        }
        label455: this.onCompleteListener.onFailure(null, k, paramResponse.getString("msg"));
        return;
        label474: paramInt = 0;
      }
    }
  }

  public void setOnCompleteListener(OnCompleteListener paramOnCompleteListener)
  {
    this.onCompleteListener = paramOnCompleteListener;
  }

  public void startRequest()
  {
    Log.e("Home", this.shopTypeId + "---------------------------------------------------------");
    if (this.shopTypeId <= 0)
    {
      this.params.put("shopTypeId", "-1");
      return;
    }
    this.params.put("shopTypeId", this.shopTypeId);
    this.params.put("level", this.level);
    HttpRequest.getInstance().executePostStringRequest(this.context, this.url, 19, this.params, this);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.net.ClassAreaRequest
 * JD-Core Version:    0.6.2
 */