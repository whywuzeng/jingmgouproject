package com.ismartgo.app.net;

import android.content.Context;
import android.util.Log;
import com.ismartgo.app.bean.Store;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.interfaces.OnCompleteListener;
import com.ismartgo.app.service.IbeaconService;
import com.ismartgo.app.tools.StringUtils;
import com.yolanda.nohttp.Response;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class StoreDetailRequest
  implements HttpCallback<String>
{
  private Context context;
  private OnCompleteListener onCompleteListener = null;
  private Map<String, String> params = new HashMap();
  private String url;

  public StoreDetailRequest(Context paramContext, String paramString)
  {
    this.url = paramString;
    this.context = paramContext;
  }

  public void initParams(String paramString1, long paramLong, String paramString2, String paramString3, int paramInt1, String paramString4, int paramInt2, int paramInt3)
  {
    Map localMap = this.params;
    String str = paramString1;
    if (paramString1.equals(""))
      str = "";
    localMap.put("uid", str);
    this.params.put("shopid", paramLong);
    this.params.put("lon", paramString2);
    this.params.put("lat", paramString3);
    this.params.put("categoryId", IbeaconService.category1Id);
    this.params.put("categoryId2", IbeaconService.category2Id);
    paramString2 = this.params;
    paramString1 = paramString4;
    if (paramString4.equals("-1"))
      paramString1 = "";
    paramString2.put("brandIds", paramString1);
    this.params.put("pages", paramInt2);
    this.params.put("pageSize", paramInt3);
  }

  public void onFailed(int paramInt, String paramString, Object paramObject, CharSequence paramCharSequence)
  {
    if (this.onCompleteListener != null)
      this.onCompleteListener.onFailure(null, paramInt, paramCharSequence.toString());
  }

  public void onSucceed(int paramInt, Response<String> paramResponse)
  {
    Store localStore = new Store();
    try
    {
      localObject = new JSONObject(((String)paramResponse.get()).toString());
      JSONObject localJSONObject = ((JSONObject)localObject).getJSONObject("data");
      if (localJSONObject.length() == 0)
      {
        if ((paramResponse != null) && (this.onCompleteListener != null))
          this.onCompleteListener.onResult(((JSONObject)localObject).getString("msg"), 20003);
      }
      else
      {
        localJSONObject = localJSONObject.getJSONObject("shop");
        if (localJSONObject.isNull("shopId"))
        {
          paramInt = 0;
          localStore.setShopId(paramInt);
          localStore.setShopLogo(StringUtils.getImgUrl(localJSONObject.getString("shopLogo")));
          localStore.setShopName(localJSONObject.getString("shopName"));
          localStore.setShopAddress(localJSONObject.getString("address"));
          localStore.setDistance(localJSONObject.getString("distance"));
          localStore.setLon(localJSONObject.getDouble("lon"));
          localStore.setLat(localJSONObject.getDouble("lat"));
          if (!localJSONObject.isNull("isSign"))
            break label327;
          localObject = "2";
          label190: localStore.setIsSign((String)localObject);
          if (!localJSONObject.isNull("isSign"))
            break label338;
          localObject = "0";
          label209: localStore.setShopSign(Integer.valueOf((String)localObject).intValue());
          if (!localJSONObject.isNull("userIsSign"))
            break label349;
          localObject = "N";
          label234: localStore.setUserIsSign((String)localObject);
          if (!localJSONObject.isNull("reatilId"))
            break label360;
          paramInt = -1;
          label252: localStore.setReatilId(paramInt);
          localStore.setReatilName(localJSONObject.getString("reatilName"));
          if (!localJSONObject.isNull("shopTypeId"))
            break label371;
        }
        label327: label338: label349: label360: label371: for (paramInt = -1; ; paramInt = localJSONObject.getInt("shopTypeId"))
        {
          localStore.setShopTypeId(paramInt);
          localObject = localStore;
          if ((paramResponse == null) || (this.onCompleteListener == null))
            return;
          this.onCompleteListener.onResult(localObject, 10001);
          return;
          paramInt = localJSONObject.getInt("shopId");
          break;
          localObject = localJSONObject.getString("isSign");
          break label190;
          localObject = localJSONObject.getString("isSign");
          break label209;
          localObject = localJSONObject.getString("userIsSign");
          break label234;
          paramInt = localJSONObject.getInt("reatilId");
          break label252;
        }
      }
    }
    catch (JSONException localJSONException)
    {
      while (true)
      {
        Object localObject = null;
        localJSONException.printStackTrace();
      }
    }
  }

  public void setOnCompleteListener(OnCompleteListener paramOnCompleteListener)
  {
    this.onCompleteListener = paramOnCompleteListener;
  }

  public void startRequest()
  {
    Log.e("Home", "在店内url: " + this.url + "?" + this.params);
    HttpRequest.getInstance().executePostStringRequest(this.context, this.url, 36, this.params, this);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.net.StoreDetailRequest
 * JD-Core Version:    0.6.2
 */