package com.ismartgo.app.net;

import android.content.Context;
import android.util.Log;
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

public class DelMessageRequest
  implements HttpCallback<String>
{
  private Context context;
  List<Message> msg_list;
  private OnCompleteListener onCompleteListener = null;
  private Map<String, String> params = new HashMap();
  private String url;

  public DelMessageRequest(Context paramContext, String paramString)
  {
    this.url = paramString;
    this.context = paramContext;
  }

  public void initParams(int paramInt)
  {
    this.params.put("mesId", paramInt);
  }

  public void onFailed(int paramInt, String paramString, Object paramObject, CharSequence paramCharSequence)
  {
    if (this.onCompleteListener != null)
      this.onCompleteListener.onFailure(null, paramInt, paramCharSequence.toString());
  }

  public void onSucceed(int paramInt, Response<String> paramResponse)
  {
    paramInt = 0;
    this.msg_list = new ArrayList();
    if ((paramResponse != null) && (this.onCompleteListener != null));
    try
    {
      int i = Integer.valueOf(new JSONObject(((String)paramResponse.get()).toString()).getString("status")).intValue();
      paramInt = i;
      this.onCompleteListener.onResult(null, paramInt);
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
    HttpRequest.getInstance().executePostStringRequest(this.context, this.url, 32, this.params, this);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.net.DelMessageRequest
 * JD-Core Version:    0.6.2
 */