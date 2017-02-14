package com.ismartgo.app.net;

import android.content.Context;
import android.util.Log;
import com.ismartgo.app.bean.IsNewMsg;
import com.ismartgo.app.bean.Message;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.interfaces.OnCompleteListener;
import com.yolanda.nohttp.Response;
import com.yolanda.nohttp.Response<Ljava.lang.String;>;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.tsz.afinal.FinalHttp;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MessageRequest
  implements HttpCallback<String>
{
  private String TAG = "MessageRequest";
  private Context context;
  private FinalHttp http = null;
  IsNewMsg isNewMsg;
  List<Message> msg_list;
  private OnCompleteListener onCompleteListener = null;
  private Map<String, String> params;
  private String url;

  public MessageRequest(Context paramContext, String paramString)
  {
    this.context = paramContext;
    this.params = new HashMap();
    this.http = new FinalHttp();
    this.http.configTimeout(5000);
    this.url = paramString;
  }

  public void initParams(long paramLong, int paramInt1, int paramInt2)
  {
    if (paramLong >= 0L)
    {
      this.params.put("uid", paramLong);
      this.params.put("pages", paramInt2);
      this.params.put("pageSize", String.valueOf(30));
      if (paramInt1 > 0)
        this.params.put("mesId", paramInt1);
      Log.e(this.TAG, "第几页-------" + paramInt2);
    }
    while (paramInt1 <= 0)
      return;
    this.params.put("mesId", paramInt1);
  }

  public void onFailed(int paramInt, String paramString, Object paramObject, CharSequence paramCharSequence)
  {
    if (this.onCompleteListener != null)
      this.onCompleteListener.onFailure(null, paramInt, paramCharSequence.toString());
  }

  public void onSucceed(int paramInt, Response<String> paramResponse)
  {
    Log.i("Test", "新消息数据： " + ((String)paramResponse.get()).toString());
    paramInt = 0;
    int i = 0;
    Object localObject1 = null;
    if ((paramResponse != null) && (this.onCompleteListener != null));
    try
    {
      localObject2 = new JSONObject(((String)paramResponse.get()).toString());
      localObject3 = ((JSONObject)localObject2).getJSONObject("data");
      paramResponse = new IsNewMsg();
      i = paramInt;
    }
    catch (JSONException localJSONException1)
    {
      try
      {
        if (((JSONObject)localObject3).getString("isNew").equals("Y"))
        {
          bool = true;
          i = paramInt;
          paramResponse.setNews(bool);
          i = paramInt;
          if (!((JSONObject)localObject3).isNull("messages"))
            break label208;
          localObject1 = null;
          i = paramInt;
          paramInt = Integer.valueOf(((JSONObject)localObject2).getString("status")).intValue();
          if (localObject1 == null)
            break label445;
          i = paramInt;
          this.msg_list = new ArrayList();
          j = 0;
          localObject2 = null;
        }
      }
      catch (JSONException localJSONException1)
      {
        label445: 
        while (true)
          try
          {
            Object localObject2;
            Object localObject3;
            boolean bool;
            int j;
            if (j >= ((JSONArray)localObject1).length())
            {
              paramResponse.setMsg_list(this.msg_list);
              this.onCompleteListener.onResult(paramResponse, paramInt);
              return;
              bool = false;
              continue;
              label208: i = paramInt;
              localObject1 = ((JSONObject)localObject3).getJSONArray("messages");
            }
            else
            {
              JSONObject localJSONObject = (JSONObject)((JSONArray)localObject1).get(j);
              localObject3 = new Message();
              i = paramInt;
              int k;
              if (localJSONObject.isNull("id"))
              {
                k = -1;
                i = paramInt;
                ((Message)localObject3).setId(k);
                i = paramInt;
                if (localJSONObject.isNull("title"))
                {
                  localObject2 = "";
                  i = paramInt;
                  ((Message)localObject3).setTitle((String)localObject2);
                  i = paramInt;
                  if (!localJSONObject.isNull("time"))
                    continue;
                  localObject2 = "";
                  i = paramInt;
                  ((Message)localObject3).setTime((String)localObject2);
                  i = paramInt;
                  ((Message)localObject3).setContent(localJSONObject.getString("h5Url"));
                  i = paramInt;
                  ((Message)localObject3).setIsRead(localJSONObject.getInt("isRead"));
                  i = paramInt;
                  this.msg_list.add(localObject3);
                  j += 1;
                  localObject2 = localObject3;
                }
              }
              else
              {
                i = paramInt;
                k = localJSONObject.getInt("id");
                continue;
                i = paramInt;
                localObject2 = localJSONObject.getString("title");
                continue;
                i = paramInt;
                localObject2 = localJSONObject.getString("time");
                continue;
                localJSONException3 = localJSONException3;
                paramInt = i;
                paramResponse = (Response<String>)localObject1;
                localObject1 = localJSONException3;
                ((JSONException)localObject1).printStackTrace();
                continue;
                localJSONException1 = localJSONException1;
                paramInt = i;
              }
            }
          }
          catch (JSONException localJSONException2)
          {
          }
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
    HttpRequest.getInstance().executePostStringRequest(this.context, this.url, 8, this.params, this);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.net.MessageRequest
 * JD-Core Version:    0.6.2
 */