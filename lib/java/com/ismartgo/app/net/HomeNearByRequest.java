package com.ismartgo.app.net;

import android.content.Context;
import com.ismartgo.app.bean.Goods;
import com.ismartgo.app.bean.Store;
import com.ismartgo.app.interfaces.OnCompleteListener;
import java.util.ArrayList;
import java.util.List;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeNearByRequest
{
  private AjaxCallBack<Object> callBack = new AjaxCallBack()
  {
    public int getRate()
    {
      return super.getRate();
    }

    public boolean isProgress()
    {
      return super.isProgress();
    }

    public void onFailure(Throwable paramAnonymousThrowable, int paramAnonymousInt, String paramAnonymousString)
    {
      super.onFailure(paramAnonymousThrowable, paramAnonymousInt, paramAnonymousString);
      if (HomeNearByRequest.this.onCompleteListener != null)
        HomeNearByRequest.this.onCompleteListener.onFailure(paramAnonymousThrowable, paramAnonymousInt, paramAnonymousString);
    }

    public void onSuccess(Object paramAnonymousObject)
    {
      super.onSuccess(paramAnonymousObject);
      int j = 0;
      HomeNearByRequest.this.near_goods_list = new ArrayList();
      HomeNearByRequest.this.near_store_list = new ArrayList();
      int i;
      if ((paramAnonymousObject != null) && (HomeNearByRequest.this.onCompleteListener != null))
        i = j;
      try
      {
        JSONObject localJSONObject = new JSONObject(paramAnonymousObject.toString());
        i = j;
        paramAnonymousObject = localJSONObject.getJSONObject("data").getJSONArray("goodsList");
        i = j;
        j = Integer.valueOf(localJSONObject.getString("status")).intValue();
        int k = 0;
        while (true)
        {
          i = j;
          int m = paramAnonymousObject.length();
          if (k >= m)
          {
            i = j;
            HomeNearByRequest.this.onCompleteListener.onResult(HomeNearByRequest.this.near_goods_list, i);
            return;
          }
          i = j;
          localJSONObject = (JSONObject)paramAnonymousObject.get(k);
          i = j;
          Goods localGoods = new Goods();
          i = j;
          Store localStore = new Store();
          i = j;
          localGoods.setGoodsId(localJSONObject.getInt("goodsid"));
          i = j;
          localGoods.setGoodsName(localJSONObject.getString("goodsName"));
          i = j;
          localGoods.setGoodsLogo(localJSONObject.getString("goodsLogo"));
          i = j;
          localGoods.setGoodsEndDate(localJSONObject.getString("goodsEndDate"));
          i = j;
          localGoods.setGoodsScan(Integer.valueOf(localJSONObject.getString("goodsScan")).intValue());
          i = j;
          localGoods.setCollect(localJSONObject.getString("isCollect").equalsIgnoreCase("Y"));
          i = j;
          localStore.setShopId(localJSONObject.getInt("shopId"));
          i = j;
          localStore.setShopName(localJSONObject.getString("shopName"));
          i = j;
          localStore.setShopLogo(localJSONObject.getString("shopLogo"));
          i = j;
          localStore.setDistance(localJSONObject.getString("distance"));
          i = j;
          HomeNearByRequest.this.near_goods_list.add(localGoods);
          i = j;
          localStore.setGoods_list(HomeNearByRequest.this.near_goods_list);
          i = j;
          HomeNearByRequest.this.near_store_list.add(localStore);
          k += 1;
        }
      }
      catch (JSONException paramAnonymousObject)
      {
        while (true)
          paramAnonymousObject.printStackTrace();
      }
    }

    public AjaxCallBack<Object> progress(boolean paramAnonymousBoolean, int paramAnonymousInt)
    {
      return super.progress(paramAnonymousBoolean, paramAnonymousInt);
    }
  };
  private FinalHttp http = null;
  List<Goods> near_goods_list;
  List<Store> near_store_list;
  private OnCompleteListener onCompleteListener = null;
  private AjaxParams params = new AjaxParams();
  private String url;

  public HomeNearByRequest(Context paramContext, String paramString)
  {
    this.url = paramString;
  }

  public void setOnCompleteListener(OnCompleteListener paramOnCompleteListener)
  {
    this.onCompleteListener = paramOnCompleteListener;
  }

  public void startRequest()
  {
    this.http.post(this.url, this.params, this.callBack);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.net.HomeNearByRequest
 * JD-Core Version:    0.6.2
 */