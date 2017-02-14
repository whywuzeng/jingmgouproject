package com.ismartgo.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.ab.view.ioc.AbIocView;
import com.ismartgo.app.adapter.MyExchangeListAdapter;
import com.ismartgo.app.bean.Gift;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.http.HttpWhat;
import com.ismartgo.app.ownself.view.AbPullToRefreshView;
import com.ismartgo.app.ownself.view.AbPullToRefreshView.OnFooterLoadListener;
import com.ismartgo.app.ownself.view.AbPullToRefreshView.OnHeaderRefreshListener;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.url.ResultCode;
import com.ismartgo.app.url.Url;
import com.umeng.analytics.MobclickAgent;
import com.yolanda.nohttp.Response;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyExchangeActivity extends BaseActivity
  implements View.OnClickListener, AbPullToRefreshView.OnHeaderRefreshListener, AbPullToRefreshView.OnFooterLoadListener
{
  public static String TAG = "MyExchangeActivity";
  private MyExchangeListAdapter adapter;
  private ArrayList<Gift> giftList = new ArrayList();
  private LinearLayout load_nothing_layout;

  @AbIocView(id=2131230875)
  private ListView lv_my_exchange;
  private MyDialog mDialog;
  private AbPullToRefreshView mPullToRefreshView;
  private int page = 1;

  @AbIocView(id=2131230856)
  private TextView tv_no_data;

  private void delete(final int paramInt)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("giftid", ((Gift)this.giftList.get(paramInt)).getGiftId());
    HttpRequest.getInstance().executePostStringRequest(this, Url.DELETE_USER_INFORMATION, HttpWhat.DELETE_USER_INFORMATION, localHashMap, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
        MyExchangeActivity.this.toast.setMessage("删除用户礼品信息失败！");
        MyExchangeActivity.this.toast.show();
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        try
        {
          paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
          MyExchangeActivity.this.toast.setMessage(paramAnonymousResponse.getString("msg"));
          MyExchangeActivity.this.giftList.remove(paramInt);
          if (MyExchangeActivity.this.giftList.isEmpty())
          {
            MyExchangeActivity.this.mPullToRefreshView.setVisibility(8);
            MyExchangeActivity.this.lv_my_exchange.setVelocityScale(8.0F);
            MyExchangeActivity.this.tv_no_data.setVisibility(0);
          }
          MyExchangeActivity.this.adapter.notifyDataSetChanged();
          MyExchangeActivity.this.toast.show();
          return;
        }
        catch (JSONException paramAnonymousResponse)
        {
          while (true)
            paramAnonymousResponse.printStackTrace();
        }
      }
    });
  }

  private void getExchangedGifts()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("uid", loginUser.getId());
    localHashMap.put("giftType", "1");
    localHashMap.put("pageSize", "30");
    localHashMap.put("pages", this.page);
    HttpRequest.getInstance().executePostStringRequest(this, Url.MY_GIFT_LIST_URL, HttpWhat.MY_GIFT_TICKET, localHashMap, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
        MyExchangeActivity.this.mPullToRefreshView.onFooterLoadFinish();
        MyExchangeActivity.this.mPullToRefreshView.onHeaderRefreshFinish();
        MyExchangeActivity.this.mDialog.dismiss();
        MyExchangeActivity.this.toast.setMessage("亲，网络好像出问题了哦~");
        MyExchangeActivity.this.toast.show();
        if (MyExchangeActivity.this.page == 1)
        {
          MyExchangeActivity.this.mPullToRefreshView.setVisibility(8);
          MyExchangeActivity.this.load_nothing_layout.setVisibility(0);
        }
        paramAnonymousString = MyExchangeActivity.this;
        paramAnonymousString.page -= 1;
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        MyExchangeActivity.this.mDialog.dismiss();
        Log.i(MyExchangeActivity.TAG, "我的兑换结果: " + ((String)paramAnonymousResponse.get()).toString());
        MyExchangeActivity.this.mPullToRefreshView.onFooterLoadFinish();
        MyExchangeActivity.this.mPullToRefreshView.onHeaderRefreshFinish();
        while (true)
        {
          try
          {
            paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
            if (Integer.valueOf(paramAnonymousResponse.getString("status")).intValue() != ResultCode.RESULT_OK)
              break label648;
            if (((paramAnonymousResponse.getJSONObject("data").isNull("userGiftList")) || (MyExchangeActivity.this.page == 1)) && ((!paramAnonymousResponse.getJSONObject("data").isNull("userGiftList")) || (MyExchangeActivity.this.page != 1)) && ((paramAnonymousResponse.getJSONObject("data").isNull("userGiftList")) || (MyExchangeActivity.this.page != 1)))
              break label617;
            if ((paramAnonymousResponse.getJSONObject("data").getJSONArray("userGiftList").length() == 0) && (MyExchangeActivity.this.page == 1))
            {
              MyExchangeActivity.this.giftList.clear();
              MyExchangeActivity.this.load_nothing_layout.setVisibility(0);
              MyExchangeActivity.this.lv_my_exchange.setVisibility(8);
              MyExchangeActivity.this.mPullToRefreshView.setVisibility(8);
              return;
            }
            paramAnonymousResponse = paramAnonymousResponse.getJSONObject("data").getJSONArray("userGiftList");
            if (MyExchangeActivity.this.page != 1)
              break label676;
            MyExchangeActivity.this.giftList.clear();
            break label676;
            if (paramAnonymousInt >= paramAnonymousResponse.length())
            {
              MyExchangeActivity.this.adapter.setList(MyExchangeActivity.this.giftList);
              MyExchangeActivity.this.adapter.notifyDataSetChanged();
              return;
            }
          }
          catch (Exception paramAnonymousResponse)
          {
            paramAnonymousResponse.printStackTrace();
            return;
          }
          JSONObject localJSONObject = paramAnonymousResponse.getJSONObject(paramAnonymousInt);
          Gift localGift = new Gift();
          localGift.setCount(localJSONObject.getInt("count"));
          localGift.setRequiredBean(localJSONObject.getInt("beanNumber"));
          localGift.setDate(localJSONObject.getString("exchangeTime"));
          localGift.setGiftName(localJSONObject.getString("giftName"));
          localGift.setFlag(localJSONObject.getString("giftStatus"));
          localGift.setGiftNumber(localJSONObject.getString("giftNumber"));
          localGift.setGiftLogo(localJSONObject.getString("logoPath"));
          localGift.setGiftId(localJSONObject.getInt("giftId"));
          localGift.setGiftLogo(Url.ADS_URL + localJSONObject.getString("logoPath"));
          localGift.setShopName(localJSONObject.getString("shopName"));
          localGift.setGiftId(localJSONObject.getInt("giftId"));
          localGift.setAddress(localJSONObject.getString("receiverAddress"));
          localGift.setH5Url(localJSONObject.getString("h5Url"));
          localGift.setGiftType(String.valueOf(localJSONObject.getInt("gift_type")));
          localGift.setPhoneNum(localJSONObject.getString("receiverMobile"));
          localGift.setReceiver(localJSONObject.getString("receiver"));
          localGift.setChannelReason(localJSONObject.optString("channel_reason"));
          localGift.setLogisticsNumber(localJSONObject.optString("logisticsnum"));
          localGift.setLogisticsCompany(localJSONObject.optString("logisticscompany"));
          localGift.setIsexpire(localJSONObject.optInt("isexpire"));
          MyExchangeActivity.this.giftList.add(localGift);
          paramAnonymousInt += 1;
          continue;
          label617: MyExchangeActivity.this.toast.setMessage(MyExchangeActivity.this.getString(2131296376));
          MyExchangeActivity.this.toast.show();
          return;
          label648: MyExchangeActivity.this.toast.setMessage(paramAnonymousResponse.getString("msg"));
          MyExchangeActivity.this.toast.show();
          return;
          label676: paramAnonymousInt = 0;
        }
      }
    });
  }

  private void initView()
  {
    initTitleBar();
    this.tv_title.setText("我的兑换");
    this.mPullToRefreshView = ((AbPullToRefreshView)findViewById(2131230857));
    this.mPullToRefreshView.setOnHeaderRefreshListener(this);
    this.mPullToRefreshView.setOnFooterLoadListener(this);
    this.adapter = new MyExchangeListAdapter(this, this.giftList);
    this.lv_my_exchange.setAdapter(this.adapter);
    this.mDialog.show();
    this.lv_my_exchange.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView = new Intent(MyExchangeActivity.this, MyExchangeDetailActivity.class);
        paramAnonymousAdapterView.putExtra("consumptionCoupon", (Serializable)MyExchangeActivity.this.giftList.get(paramAnonymousInt));
        MyExchangeActivity.this.startActivity(paramAnonymousAdapterView);
      }
    });
    this.load_nothing_layout = ((LinearLayout)findViewById(2131230855));
  }

  public void onClick(View paramView)
  {
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903054);
    this.mDialog = new MyDialog(this);
    initView();
    getExchangedGifts();
  }

  public void onFooterLoad(AbPullToRefreshView paramAbPullToRefreshView)
  {
    this.mPullToRefreshView.postDelayed(new Runnable()
    {
      public void run()
      {
        MyExchangeActivity localMyExchangeActivity = MyExchangeActivity.this;
        localMyExchangeActivity.page += 1;
        MyExchangeActivity.this.getExchangedGifts();
      }
    }
    , 1000L);
  }

  public void onHeaderRefresh(AbPullToRefreshView paramAbPullToRefreshView)
  {
    this.mPullToRefreshView.postDelayed(new Runnable()
    {
      public void run()
      {
        MyExchangeActivity.this.page = 1;
        MyExchangeActivity.this.getExchangedGifts();
      }
    }
    , 1000L);
  }

  protected void onPause()
  {
    super.onPause();
    MobclickAgent.onPause(this);
  }

  protected void onResume()
  {
    super.onResume();
    MobclickAgent.onResume(this);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.MyExchangeActivity
 * JD-Core Version:    0.6.2
 */