package com.ismartgo.app.net;

import android.content.Context;
import android.util.Log;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.interfaces.OnCompleteListener;
import com.ismartgo.app.tools.TelephoneUtils;
import com.yolanda.nohttp.Response;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class SynchronousBeansRequest
  implements HttpCallback<String>
{
  private Context context;
  private OnCompleteListener onCompleteListener = null;
  private Map<String, String> params = new HashMap();
  private String url;

  public SynchronousBeansRequest(Context paramContext, String paramString)
  {
    this.url = paramString;
    this.context = paramContext;
  }

  public void initParams(Context paramContext, String paramString)
  {
    this.params.put("uid", paramString);
    this.params.put("imei", TelephoneUtils.getIMEI(paramContext));
  }

  public void initParams(String paramString1, String paramString2, int paramInt)
  {
    this.params.put("uid", paramString1);
    this.params.put("touristid", "");
    this.params.put("beanNumber", paramInt);
  }

  public void onFailed(int paramInt, String paramString, Object paramObject, CharSequence paramCharSequence)
  {
    if (this.onCompleteListener != null)
      this.onCompleteListener.onFailure(null, paramInt, paramCharSequence.toString());
  }

  public void onSucceed(int paramInt, Response<String> paramResponse)
  {
    Log.e("LoginActivity", "登录同步精明豆数量结果: " + ((String)paramResponse.get()).toString());
    try
    {
      JSONObject localJSONObject = new JSONObject(((String)paramResponse.get()).toString());
      this.onCompleteListener.onResult(paramResponse, localJSONObject.getInt("status"));
      return;
    }
    catch (JSONException paramResponse)
    {
      paramResponse.printStackTrace();
    }
  }

  public void setOnCompleteListener(OnCompleteListener paramOnCompleteListener)
  {
    this.onCompleteListener = paramOnCompleteListener;
  }

  public void startRequest()
  {
    Log.e("LoginActivity", "登录同步精明豆数量url: " + this.url + "?" + this.params);
    HttpRequest.getInstance().executePostStringRequest(this.context, this.url, 37, this.params, this);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.net.SynchronousBeansRequest
 * JD-Core Version:    0.6.2
 */