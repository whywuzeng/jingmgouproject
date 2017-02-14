package com.ismartgo.app.net;

import android.content.Context;
import android.util.Log;
import com.ismartgo.app.bean.Infos;
import com.ismartgo.app.bean.Message;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.interfaces.OnCompleteListener;
import com.yolanda.nohttp.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class DelCollcetGoodsRequest
  implements HttpCallback<String>
{
  private Context context;
  List<Message> msg_list;
  private OnCompleteListener onCompleteListener = null;
  private Map<String, String> params = new HashMap();
  private String url;

  public DelCollcetGoodsRequest(Context paramContext, String paramString)
  {
    this.context = paramContext;
    this.url = paramString;
  }

  public void initParams(String paramString, int paramInt1, int paramInt2)
  {
    this.params.put("uid", paramString);
    this.params.put("goodsid", paramInt1);
    this.params.put("shopid", paramInt2);
  }

  public void onFailed(int paramInt, String paramString, Object paramObject, CharSequence paramCharSequence)
  {
    if (this.onCompleteListener != null)
      this.onCompleteListener.onFailure(null, paramInt, paramCharSequence.toString());
  }

  public void onSucceed(int paramInt, Response<String> paramResponse)
  {
    int i = 0;
    Infos localInfos = new Infos();
    this.msg_list = new ArrayList();
    if ((paramResponse != null) && (this.onCompleteListener != null))
      paramInt = i;
    try
    {
      paramResponse = new JSONObject(((String)paramResponse.get()).toString());
      paramInt = i;
      i = Integer.valueOf(paramResponse.getString("status")).intValue();
      paramInt = i;
      localInfos.setMsg(paramResponse.getString("msg"));
      paramInt = i;
      this.onCompleteListener.onResult(localInfos, paramInt);
      return;
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
    Log.e("Home", this.url + "?" + this.params);
    HttpRequest.getInstance().executePostStringRequest(this.context, this.url, 20, this.params, this);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.net.DelCollcetGoodsRequest
 * JD-Core Version:    0.6.2
 */