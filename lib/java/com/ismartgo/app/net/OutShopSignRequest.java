package com.ismartgo.app.net;

import android.content.Context;
import android.util.Log;
import com.ismartgo.app.bean.Store;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.interfaces.OnCompleteListener;
import com.ismartgo.app.tools.TelephoneUtils;
import com.ismartgo.app.url.Url;
import com.yolanda.nohttp.Response;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OutShopSignRequest
  implements HttpCallback<String>
{
  private Context context;
  private OnCompleteListener onCompleteListener = null;
  private Map<String, String> params = new HashMap();
  private List<Store> store_list;
  private String url;

  public OutShopSignRequest(Context paramContext, String paramString)
  {
    this.url = paramString;
    this.context = paramContext;
  }

  public void initParams(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt1, int paramInt2)
  {
    this.params.put("uid", paramString1);
    this.params.put("cityName", paramString2);
    this.params.put("lon", paramString3);
    this.params.put("lat", paramString4);
    this.params.put("pages", paramInt1);
    this.params.put("pageSize", paramInt2);
    this.params.put("devCode", TelephoneUtils.getIMEI(this.context));
    System.out.println("到店签到接口参数------------>" + this.params);
  }

  public void onFailed(int paramInt, String paramString, Object paramObject, CharSequence paramCharSequence)
  {
    if (this.onCompleteListener != null)
      this.onCompleteListener.onFailure(null, paramInt, paramCharSequence.toString());
  }

  public void onSucceed(int paramInt, Response<String> paramResponse)
  {
    System.out.println("success之后的数据： " + ((String)paramResponse.get()).toString());
    int i = 0;
    if ((paramResponse != null) && (this.onCompleteListener != null))
      paramInt = i;
    try
    {
      paramResponse = new JSONObject(((String)paramResponse.get()).toString());
      paramInt = i;
      i = paramResponse.getInt("status");
      paramInt = i;
      paramResponse = paramResponse.getJSONObject("data").getJSONArray("shopSignList");
      paramInt = i;
      if (paramResponse.length() > 0)
      {
        paramInt = i;
        if (this.store_list == null)
        {
          paramInt = i;
          this.store_list = new ArrayList();
        }
        paramInt = i;
        this.store_list.clear();
        break label396;
        while (true)
        {
          paramInt = i;
          int k = paramResponse.length();
          if (j >= k)
          {
            paramInt = i;
            this.onCompleteListener.onResult(this.store_list, paramInt);
            return;
          }
          paramInt = i;
          JSONObject localJSONObject = (JSONObject)paramResponse.get(j);
          paramInt = i;
          Store localStore = new Store();
          paramInt = i;
          localStore.setShopId(localJSONObject.getInt("shopid"));
          paramInt = i;
          localStore.setShopLogo(Url.ADS_URL + localJSONObject.getString("logopath"));
          paramInt = i;
          localStore.setShopName(localJSONObject.getString("shopname"));
          paramInt = i;
          localStore.setShopAddress(localJSONObject.getString("address"));
          paramInt = i;
          localStore.setBeans(localJSONObject.getInt("beannumber"));
          paramInt = i;
          localStore.setLat(localJSONObject.getDouble("lat"));
          paramInt = i;
          localStore.setLon(localJSONObject.getDouble("lon"));
          paramInt = i;
          localStore.setUserIsSign(localJSONObject.getString("userIsSign"));
          paramInt = i;
          localStore.setDistance(localJSONObject.getString("distance"));
          paramInt = i;
          localStore.setTotalBean(localJSONObject.optString("totalbean"));
          paramInt = i;
          localStore.setRemainBean(localJSONObject.optInt("leavebean"));
          paramInt = i;
          this.store_list.add(localStore);
          j += 1;
        }
      }
    }
    catch (JSONException paramResponse)
    {
      while (true)
      {
        paramResponse.printStackTrace();
        continue;
        label396: int j = 0;
      }
    }
  }

  public void setOnCompleteListener(OnCompleteListener paramOnCompleteListener)
  {
    this.onCompleteListener = paramOnCompleteListener;
  }

  public void startRequest()
  {
    Log.e("Home", this.url + "?" + this.params);
    HttpRequest.getInstance().executePostStringRequest(this.context, this.url, 34, this.params, this);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.net.OutShopSignRequest
 * JD-Core Version:    0.6.2
 */