package com.ismartgo.app.tools;

import android.content.Context;
import com.ismartgo.app.activity.BaseActivity;
import com.ismartgo.app.application.AndroidApplication;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.ibeacon.iBeaconClass.iBeacon;
import com.ismartgo.app.url.Url;
import com.yolanda.nohttp.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import net.tsz.afinal.http.AjaxCallBack;
import org.json.JSONException;
import org.json.JSONObject;

public class IsInShopAndIsScan
{
  AjaxCallBack<Object> ajaxCallBack = new AjaxCallBack()
  {
    public void onFailure(Throwable paramAnonymousThrowable, int paramAnonymousInt, String paramAnonymousString)
    {
      super.onFailure(paramAnonymousThrowable, paramAnonymousInt, paramAnonymousString);
    }

    public void onSuccess(Object paramAnonymousObject)
    {
      super.onSuccess(paramAnonymousObject);
    }
  };
  AndroidApplication application;
  private int beanNumber;
  private Context context;
  private boolean isInShop;
  private boolean isScan;
  private OnCompleteListener mCompleteListener;
  private MyDialog mDialog;
  private String message;
  private int shopId;
  private String shopName;
  User user;

  public IsInShopAndIsScan(Context paramContext, MyDialog paramMyDialog)
  {
    this.context = paramContext;
    this.mDialog = paramMyDialog;
    this.application = ((AndroidApplication)paramContext.getApplicationContext());
    this.user = this.application.getUser();
  }

  public boolean IsInShop()
  {
    return this.isInShop;
  }

  public int getBeanNumber()
  {
    return this.beanNumber;
  }

  public String getMessage()
  {
    return this.message;
  }

  public int getShopId()
  {
    return this.shopId;
  }

  public String getShopName()
  {
    return this.shopName;
  }

  public void isInShopRequest()
  {
    MonitorIBeaconIsInShop localMonitorIBeaconIsInShop = new MonitorIBeaconIsInShop(this.context);
    localMonitorIBeaconIsInShop.setIbeaconListener(new MonitorIBeaconIsInShop.iBeaconData()
    {
      public void setIbeaconData(Vector<iBeaconClass.iBeacon> paramAnonymousVector, boolean paramAnonymousBoolean)
      {
        if (IsInShopAndIsScan.this.mDialog != null)
          IsInShopAndIsScan.this.mDialog.show();
        HashMap localHashMap = new HashMap();
        if ((IsInShopAndIsScan.this.user == null) || (Integer.parseInt(IsInShopAndIsScan.this.user.getId()) < 0));
        for (String str = ""; ; str = IsInShopAndIsScan.this.user.getId())
        {
          localHashMap.put("uid", str);
          if ((paramAnonymousBoolean) && (paramAnonymousVector.size() > 0))
          {
            localHashMap.put("uuid", ((iBeaconClass.iBeacon)paramAnonymousVector.get(0)).proximityUuid);
            localHashMap.put("major", ((iBeaconClass.iBeacon)paramAnonymousVector.get(0)).major);
            localHashMap.put("minor", ((iBeaconClass.iBeacon)paramAnonymousVector.get(0)).minor);
          }
          localHashMap.put("lon", BaseActivity.log);
          localHashMap.put("lat", BaseActivity.lat);
          HttpRequest.getInstance().executePostStringRequest(IsInShopAndIsScan.this.context, Url.ISINSHOP_ISSCAN_URL, 54, localHashMap, new HttpCallback()
          {
            public void onFailed(int paramAnonymous2Int, String paramAnonymous2String, Object paramAnonymous2Object, CharSequence paramAnonymous2CharSequence)
            {
              IsInShopAndIsScan.this.mCompleteListener.onFailed();
              if (IsInShopAndIsScan.this.mDialog != null)
                IsInShopAndIsScan.this.mDialog.dismiss();
            }

            public void onSucceed(int paramAnonymous2Int, Response<String> paramAnonymous2Response)
            {
              if (IsInShopAndIsScan.this.mDialog != null)
                IsInShopAndIsScan.this.mDialog.dismiss();
              while (true)
              {
                try
                {
                  JSONObject localJSONObject = new JSONObject(((String)paramAnonymous2Response.get()).toString()).getJSONObject("data").getJSONObject("isInShop");
                  paramAnonymous2Response = IsInShopAndIsScan.this;
                  if (localJSONObject.getString("isinshop").equals("Y"))
                  {
                    bool = true;
                    paramAnonymous2Response.setInShop(bool);
                    paramAnonymous2Response = IsInShopAndIsScan.this;
                    if (localJSONObject.isNull("isScan"))
                    {
                      bool = false;
                      paramAnonymous2Response.setScan(bool);
                      if (IsInShopAndIsScan.this.IsInShop())
                      {
                        paramAnonymous2Response = IsInShopAndIsScan.this;
                        if (localJSONObject.isNull("shopId"))
                        {
                          paramAnonymous2Int = 0;
                          paramAnonymous2Response.setShopId(paramAnonymous2Int);
                          IsInShopAndIsScan localIsInShopAndIsScan = IsInShopAndIsScan.this;
                          if (!localJSONObject.isNull("shopName"))
                            continue;
                          paramAnonymous2Response = "";
                          localIsInShopAndIsScan.setShopName(paramAnonymous2Response);
                          localIsInShopAndIsScan = IsInShopAndIsScan.this;
                          if (!localJSONObject.isNull("message"))
                            continue;
                          paramAnonymous2Response = "";
                          localIsInShopAndIsScan.setMessage(paramAnonymous2Response);
                          paramAnonymous2Response = IsInShopAndIsScan.this;
                          if (!localJSONObject.isNull("beannumber"))
                            continue;
                          paramAnonymous2Int = 0;
                          paramAnonymous2Response.setBeanNumber(paramAnonymous2Int);
                        }
                      }
                      else
                      {
                        IsInShopAndIsScan.this.mCompleteListener.onComplete();
                      }
                    }
                    else
                    {
                      if (!localJSONObject.getString("isScan").equals("Y"))
                        break label363;
                      bool = true;
                      continue;
                    }
                    paramAnonymous2Int = localJSONObject.getInt("shopId");
                    continue;
                    paramAnonymous2Response = localJSONObject.getString("shopName");
                    continue;
                    paramAnonymous2Response = localJSONObject.getString("message");
                    continue;
                    paramAnonymous2Int = localJSONObject.getInt("beannumber");
                    continue;
                  }
                }
                catch (JSONException paramAnonymous2Response)
                {
                  IsInShopAndIsScan.this.mCompleteListener.onFailed();
                  if (IsInShopAndIsScan.this.mDialog != null)
                    IsInShopAndIsScan.this.mDialog.dismiss();
                  paramAnonymous2Response.printStackTrace();
                  return;
                }
                boolean bool = false;
                continue;
                label363: bool = false;
              }
            }
          });
          return;
        }
      }
    });
    localMonitorIBeaconIsInShop.findIbeacon();
  }

  public boolean isScan()
  {
    return this.isScan;
  }

  public void setBeanNumber(int paramInt)
  {
    this.beanNumber = paramInt;
  }

  public void setInShop(boolean paramBoolean)
  {
    this.isInShop = paramBoolean;
  }

  public void setMessage(String paramString)
  {
    this.message = paramString;
  }

  public void setOnCompleteListener(OnCompleteListener paramOnCompleteListener)
  {
    this.mCompleteListener = paramOnCompleteListener;
  }

  public void setScan(boolean paramBoolean)
  {
    this.isScan = paramBoolean;
  }

  public void setShopId(int paramInt)
  {
    this.shopId = paramInt;
  }

  public void setShopName(String paramString)
  {
    this.shopName = paramString;
  }

  public static abstract interface OnCompleteListener
  {
    public abstract void onComplete();

    public abstract void onFailed();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.tools.IsInShopAndIsScan
 * JD-Core Version:    0.6.2
 */