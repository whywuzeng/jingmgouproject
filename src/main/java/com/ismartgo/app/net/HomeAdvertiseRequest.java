package com.ismartgo.app.net;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.ismartgo.app.bean.Advertise;
import com.ismartgo.app.bean.Infos;
import com.ismartgo.app.common.CommonMethod;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.interfaces.OnCompleteListener;
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

public class HomeAdvertiseRequest
  implements HttpCallback<String>
{
  List<Advertise> adver_list;
  private Context context;
  private OnCompleteListener onCompleteListener = null;
  Map<String, String> params;
  private String url;

  public HomeAdvertiseRequest(Context paramContext, String paramString)
  {
    this.context = paramContext;
    this.url = paramString;
  }

  public void initParams(String paramString, int paramInt)
  {
    this.params = new HashMap();
    if (!CommonMethod.isEmpty(paramString))
      this.params.put("cityName", paramString);
    while (true)
    {
      this.params.put("moduleid", paramInt);
      try
      {
        this.params.put("version", String.valueOf(this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionCode));
        this.params.put("devtype", "android");
        return;
        this.params.put("cityName", "广州");
      }
      catch (PackageManager.NameNotFoundException paramString)
      {
        while (true)
          paramString.printStackTrace();
      }
    }
  }

  public void onFailed(int paramInt, String paramString, Object paramObject, CharSequence paramCharSequence)
  {
    if (this.onCompleteListener != null)
      this.onCompleteListener.onFailure(null, paramInt, paramCharSequence.toString());
  }

  public void onSucceed(int paramInt, Response<String> paramResponse)
  {
    System.out.println("--->t=" + ((String)paramResponse.get()).toString());
    int j = 0;
    int i = 0;
    Infos localInfos;
    if ((paramResponse != null) && (this.onCompleteListener != null))
    {
      this.adver_list = new ArrayList();
      localInfos = new Infos();
      paramInt = j;
    }
    try
    {
      JSONObject localJSONObject1 = new JSONObject(((String)paramResponse.get()).toString());
      paramInt = j;
      paramResponse = localJSONObject1.getJSONObject("data");
      paramInt = j;
      JSONArray localJSONArray;
      if (!paramResponse.isNull("photoList"))
      {
        paramInt = j;
        localJSONArray = paramResponse.getJSONArray("photoList");
        paramInt = j;
        i = Integer.valueOf(localJSONObject1.getString("status")).intValue();
        j = 0;
        paramInt = i;
        if (j < localJSONArray.length());
      }
      else
      {
        paramInt = i;
        localInfos.setStatus(i);
        paramInt = i;
        localInfos.setMsg(localJSONObject1.getString("msg"));
        paramInt = i;
        localInfos.setTime(localJSONObject1.getString("time"));
        paramInt = i;
        localInfos.setAdver_list(this.adver_list);
        paramInt = i;
        this.onCompleteListener.onResult(localInfos, paramInt);
        return;
      }
      paramInt = i;
      JSONObject localJSONObject2 = (JSONObject)localJSONArray.get(j);
      paramInt = i;
      Advertise localAdvertise = new Advertise();
      paramInt = i;
      localAdvertise.setId(localJSONObject2.getInt("id"));
      paramInt = i;
      localAdvertise.setPicPath(Url.ADS_URL + localJSONObject2.getString("pic_path"));
      paramInt = i;
      if (localJSONObject2.isNull("third_link"));
      for (paramResponse = ""; ; paramResponse = localJSONObject2.getString("third_link"))
      {
        paramInt = i;
        localAdvertise.setThirdLink(paramResponse);
        paramInt = i;
        localAdvertise.setDetail(localJSONObject2.getString("ads_details"));
        paramInt = i;
        this.adver_list.add(localAdvertise);
        j += 1;
        break;
        paramInt = i;
      }
    }
    catch (JSONException paramResponse)
    {
      while (true)
        paramResponse.printStackTrace();
    }
  }

  public void setOnCompleteListener(OnCompleteListener paramOnCompleteListener)
  {
    this.onCompleteListener = paramOnCompleteListener;
  }

  public void startRequest()
  {
    Log.e("HomeActivity", this.url + "?" + this.params);
    HttpRequest.getInstance().executePostStringRequest(this.context, this.url, 5, this.params, this);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.net.HomeAdvertiseRequest
 * JD-Core Version:    0.6.2
 */