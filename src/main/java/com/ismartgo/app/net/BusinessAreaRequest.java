package com.ismartgo.app.net;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.ismartgo.app.bean.Area;
import com.ismartgo.app.bean.DistrictArea;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.interfaces.OnCompleteListener;
import com.yolanda.nohttp.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.tsz.afinal.FinalHttp;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BusinessAreaRequest
  implements HttpCallback<String>
{
  public static FinalHttp http = null;
  List<Area> area_list;
  private Context context;
  List<DistrictArea> district_list;
  private OnCompleteListener onCompleteListener = null;
  private Map<String, String> params;
  private String url;

  public BusinessAreaRequest(Context paramContext, String paramString)
  {
    this.url = paramString;
    this.context = paramContext;
  }

  public void initParams(String paramString)
  {
    this.params = new HashMap();
    if (TextUtils.isEmpty(paramString))
    {
      this.params.put("cityName", "广州");
      return;
    }
    this.params.put("cityName", paramString);
  }

  public void onFailed(int paramInt, String paramString, Object paramObject, CharSequence paramCharSequence)
  {
    if (this.onCompleteListener != null)
      this.onCompleteListener.onFailure(null, paramInt, paramCharSequence.toString());
  }

  public void onSucceed(int paramInt, Response<String> paramResponse)
  {
    int i = 0;
    this.area_list = new ArrayList();
    if ((paramResponse != null) && (this.onCompleteListener != null))
      paramInt = i;
    try
    {
      paramResponse = new JSONObject(((String)paramResponse.get()).toString());
      paramInt = i;
      i = Integer.valueOf(paramResponse.optString("status")).intValue();
      paramInt = i;
      int j;
      if (i == 10001)
      {
        paramInt = i;
        paramResponse = paramResponse.optJSONObject("data").optJSONArray("districts");
        j = 0;
        paramInt = i;
        k = paramResponse.length();
        if (j >= k)
          paramInt = i;
      }
      else
      {
        this.onCompleteListener.onResult(this.area_list, paramInt);
        return;
      }
      paramInt = i;
      Area localArea = new Area();
      paramInt = i;
      JSONObject localJSONObject1 = paramResponse.optJSONObject(j);
      paramInt = i;
      localArea.setDistrictId(localJSONObject1.optInt("districtId"));
      paramInt = i;
      localArea.setAreaName(localJSONObject1.optString("districtName"));
      paramInt = i;
      ArrayList localArrayList = new ArrayList();
      paramInt = i;
      JSONArray localJSONArray = localJSONObject1.optJSONArray("towns");
      int k = 0;
      while (true)
      {
        paramInt = i;
        if (k >= localJSONArray.length())
        {
          paramInt = i;
          localArea.setDistrictList(localArrayList);
          paramInt = i;
          this.area_list.add(localArea);
          j += 1;
          break;
        }
        paramInt = i;
        JSONObject localJSONObject2 = localJSONArray.optJSONObject(k);
        paramInt = i;
        DistrictArea localDistrictArea = new DistrictArea();
        paramInt = i;
        localDistrictArea.setDistrictId(localJSONObject1.optInt("districtId"));
        paramInt = i;
        localDistrictArea.setTownId(localJSONObject2.optInt("townId"));
        paramInt = i;
        localDistrictArea.setName(localJSONObject2.optString("townName"));
        paramInt = i;
        localArrayList.add(localDistrictArea);
        k += 1;
      }
    }
    catch (JSONException paramResponse)
    {
      while (true)
      {
        Log.e("Test", "区域解析有误： " + paramResponse.getMessage());
        paramResponse.printStackTrace();
      }
    }
  }

  public void setOnCompleteListener(OnCompleteListener paramOnCompleteListener)
  {
    this.onCompleteListener = paramOnCompleteListener;
  }

  public void startRequest()
  {
    Log.i("XXNet", "获取area数据");
    HttpRequest.getInstance().executePostStringRequest(this.context, this.url, 4, this.params, this);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.net.BusinessAreaRequest
 * JD-Core Version:    0.6.2
 */