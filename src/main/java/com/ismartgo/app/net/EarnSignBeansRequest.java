package com.ismartgo.app.net;

import android.content.Context;
import android.util.Log;
import com.ismartgo.app.bean.Infos;
import com.ismartgo.app.grid.utils.Helper;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.interfaces.OnCompleteListener;
import com.ismartgo.app.tools.TelephoneUtils;
import com.yolanda.nohttp.Response;
import java.util.HashMap;
import java.util.Map;

public class EarnSignBeansRequest
  implements HttpCallback<String>
{
  private Context context;
  Infos infos = new Infos();
  private OnCompleteListener onCompleteListener = null;
  private Map<String, String> params = new HashMap();
  private String url;

  public EarnSignBeansRequest(Context paramContext, String paramString)
  {
    this.url = paramString;
    this.context = paramContext;
  }

  public void initParams(long paramLong, int paramInt)
  {
    this.params.put("uid", paramLong);
    this.params.put("shopid", paramInt);
  }

  public void initParams(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2)
  {
    this.params.put("uid", paramString1);
    this.params.put("shopId", String.valueOf(paramInt1));
    this.params.put("lon", paramString2);
    this.params.put("lat", paramString3);
    this.params.put("beacon", String.valueOf(paramInt2));
    this.params.put("devcode", TelephoneUtils.getIMEI(this.context));
    paramString1 = Helper.MD5Params(new String[] { paramString1, String.valueOf(paramInt1), String.valueOf(paramInt2) });
    this.params.put("key", paramString1);
  }

  public void onFailed(int paramInt, String paramString, Object paramObject, CharSequence paramCharSequence)
  {
    if (this.onCompleteListener != null)
      this.onCompleteListener.onFailure(null, paramInt, paramCharSequence.toString());
  }

  public void onSucceed(int paramInt, Response<String> paramResponse)
  {
    this.onCompleteListener.onResult(paramResponse.get(), 0);
  }

  public void setOnCompleteListener(OnCompleteListener paramOnCompleteListener)
  {
    this.onCompleteListener = paramOnCompleteListener;
  }

  public void startRequest()
  {
    Log.e("Home", this.url + "?" + this.params);
    HttpRequest.getInstance().executePostStringRequest(this.context, this.url, 33, this.params, this);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.net.EarnSignBeansRequest
 * JD-Core Version:    0.6.2
 */