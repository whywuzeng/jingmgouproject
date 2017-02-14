package com.ismartgo.app.net;

import android.content.Context;
import android.util.Log;
import com.ismartgo.app.activity.BaseActivity;
import com.ismartgo.app.bean.GoodsDetail;
import com.ismartgo.app.bean.Infos;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.interfaces.OnCompleteListener;
import java.io.PrintStream;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import org.json.JSONException;
import org.json.JSONObject;

public class GoodsDetailRequest
{
  private AjaxCallBack<Object> callBack = new AjaxCallBack()
  {
    public void onFailure(Throwable paramAnonymousThrowable, int paramAnonymousInt, String paramAnonymousString)
    {
      super.onFailure(paramAnonymousThrowable, paramAnonymousInt, paramAnonymousString);
      if (GoodsDetailRequest.this.onCompleteListener != null)
        GoodsDetailRequest.this.onCompleteListener.onFailure(paramAnonymousThrowable, paramAnonymousInt, paramAnonymousString);
    }

    public void onSuccess(Object paramAnonymousObject)
    {
      Object localObject3 = null;
      super.onSuccess(paramAnonymousObject);
      System.out.println("--->t=" + paramAnonymousObject.toString());
      int i = 0;
      Object localObject2 = null;
      Object localObject4 = null;
      if ((paramAnonymousObject != null) && (GoodsDetailRequest.this.onCompleteListener != null))
      {
        int j = i;
        try
        {
          localJSONObject = new JSONObject(paramAnonymousObject.toString());
          j = i;
          paramAnonymousObject = localJSONObject.getJSONObject("data");
          j = i;
          if (paramAnonymousObject.isNull("goodsDetails"))
          {
            j = i;
            if (GoodsDetailRequest.this.onCompleteListener != null)
            {
              j = i;
              GoodsDetailRequest.this.onCompleteListener.onFailure(null, 0, null);
            }
          }
          else
          {
            j = i;
            if (paramAnonymousObject.isNull("goodsDetails"))
            {
              localObject1 = null;
              j = i;
              i = Integer.valueOf(localJSONObject.getString("status")).intValue();
              paramAnonymousObject = localObject4;
              j = i;
              if (localObject1 != null)
              {
                j = i;
                paramAnonymousObject = new GoodsDetail();
              }
            }
          }
        }
        catch (JSONException localJSONException1)
        {
          try
          {
            JSONObject localJSONObject;
            Object localObject1;
            if (((JSONObject)localObject1).isNull("id"))
            {
              j = -1;
              label183: paramAnonymousObject.setId(j);
              if (!((JSONObject)localObject1).isNull("barcode"))
                break label400;
              localObject2 = "";
              label202: paramAnonymousObject.setBarcode((String)localObject2);
              if (!((JSONObject)localObject1).isNull("h5url"))
                break label412;
              localObject2 = "";
              label222: paramAnonymousObject.setH5url((String)localObject2);
              if (!((JSONObject)localObject1).isNull("isscan"))
                break label424;
              j = 1;
              label240: paramAnonymousObject.setIsscan(j);
              if (!((JSONObject)localObject1).isNull("productName"))
                break label435;
              localObject2 = localObject3;
              label259: paramAnonymousObject.setProductName((String)localObject2);
              if (!((JSONObject)localObject1).isNull("promotion"))
                break label447;
              localObject1 = "";
              label279: paramAnonymousObject.setPromotion((String)localObject1);
              GoodsDetailRequest.this.infos.setStatus(Integer.valueOf(localJSONObject.getString("status")).intValue());
              GoodsDetailRequest.this.infos.setMsg(localJSONObject.getString("msg"));
              GoodsDetailRequest.this.infos.setTime(localJSONObject.getString("time"));
            }
            for (j = i; ; j = i)
            {
              GoodsDetailRequest.this.infos.setGoods_detail(paramAnonymousObject);
              GoodsDetailRequest.this.onCompleteListener.onResult(GoodsDetailRequest.this.infos, j);
              return;
              j = i;
              localObject1 = paramAnonymousObject.getJSONObject("goodsDetails");
              break;
              j = ((JSONObject)localObject1).getInt("id");
              break label183;
              label400: localObject2 = ((JSONObject)localObject1).getString("barcode");
              break label202;
              label412: localObject2 = ((JSONObject)localObject1).getString("h5url");
              break label222;
              label424: j = ((JSONObject)localObject1).getInt("isscan");
              break label240;
              label435: localObject2 = ((JSONObject)localObject1).getString("productName");
              break label259;
              label447: localObject1 = ((JSONObject)localObject1).getString("promotion");
              break label279;
              localJSONException1 = localJSONException1;
              i = j;
              paramAnonymousObject = localObject2;
              label466: localJSONException1.printStackTrace();
            }
          }
          catch (JSONException localJSONException2)
          {
            break label466;
          }
        }
      }
    }
  };
  private FinalHttp http = null;
  private Infos infos;
  private OnCompleteListener onCompleteListener = null;
  private AjaxParams params = new AjaxParams();
  private String url;

  public GoodsDetailRequest(Context paramContext, String paramString)
  {
    this.url = paramString;
    this.infos = new Infos();
  }

  public void initParams(long paramLong1, long paramLong2, String paramString)
  {
    if (paramLong2 > 0L)
      this.params.put("goodsId", paramLong2);
    while (true)
    {
      this.params.put("shopid", paramLong1);
      paramString = "";
      if (BaseActivity.loginUser != null)
        paramString = BaseActivity.loginUser.getId();
      this.params.put("uid", paramString);
      return;
      if (paramString != null)
        this.params.put("goodsNumber", paramString);
    }
  }

  public void setOnCompleteListener(OnCompleteListener paramOnCompleteListener)
  {
    this.onCompleteListener = paramOnCompleteListener;
  }

  public void startRequest()
  {
    Log.e("Home", this.url + "?" + this.params);
    this.http.post(this.url, this.params, this.callBack);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.net.GoodsDetailRequest
 * JD-Core Version:    0.6.2
 */