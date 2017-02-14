package com.ismartgo.app.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.ab.view.ioc.AbIocView;
import com.ismartgo.app.adapter.CunsumptionCouponListAdapter;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyConsumptionCouponActivity extends BaseActivity
  implements View.OnClickListener, AbPullToRefreshView.OnHeaderRefreshListener, AbPullToRefreshView.OnFooterLoadListener
{
  public static String TAG = "MyConsumptionCouponActivity";
  private CunsumptionCouponListAdapter adapter;
  private ArrayList<Gift> giftList = new ArrayList();
  private LinearLayout load_nothing_layout;

  @AbIocView(id=2131230858)
  private ListView lv_my_consumption_coupon;
  private MyDialog mDialog;
  private AbPullToRefreshView mPullToRefreshView;
  private int page = 1;

  @AbIocView(id=2131230856)
  private TextView tv_no_data;

  private void delete(int paramInt)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("giftid", ((Gift)this.giftList.get(paramInt)).getGiftId());
    HttpRequest.getInstance().executePostStringRequest(this, Url.DELETE_USER_INFORMATION, HttpWhat.DELETE_USER_INFORMATION, localHashMap, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
        MyConsumptionCouponActivity.this.toast.setMessage("删除用户礼品信息失败！");
        MyConsumptionCouponActivity.this.toast.show();
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        try
        {
          paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
          MyConsumptionCouponActivity.this.toast.setMessage(paramAnonymousResponse.getString("msg"));
          MyConsumptionCouponActivity.this.toast.show();
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
    if ((loginUser == null) || (loginUser.getId() == null))
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("uid", loginUser.getId());
    localHashMap.put("giftType", "2");
    localHashMap.put("pageSize", "30");
    localHashMap.put("pages", this.page);
    HttpRequest.getInstance().executePostStringRequest(this, Url.MY_GIFT_LIST_URL, HttpWhat.MY_GIFT_TICKET, localHashMap, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
        MyConsumptionCouponActivity.this.mPullToRefreshView.onFooterLoadFinish();
        MyConsumptionCouponActivity.this.mPullToRefreshView.onHeaderRefreshFinish();
        MyConsumptionCouponActivity.this.mDialog.dismiss();
        MyConsumptionCouponActivity.this.toast.setMessage("亲，网络好像出问题了哦~");
        MyConsumptionCouponActivity.this.toast.show();
        if (MyConsumptionCouponActivity.this.page == 1)
        {
          MyConsumptionCouponActivity.this.load_nothing_layout.setVisibility(0);
          MyConsumptionCouponActivity.this.lv_my_consumption_coupon.setVisibility(8);
          MyConsumptionCouponActivity.this.mPullToRefreshView.setVisibility(8);
        }
        paramAnonymousString = MyConsumptionCouponActivity.this;
        paramAnonymousString.page -= 1;
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        MyConsumptionCouponActivity.this.mDialog.dismiss();
        Log.i(MyConsumptionCouponActivity.TAG, "我的消费券结果: " + ((String)paramAnonymousResponse.get()).toString());
        MyConsumptionCouponActivity.this.mPullToRefreshView.onFooterLoadFinish();
        MyConsumptionCouponActivity.this.mPullToRefreshView.onHeaderRefreshFinish();
        while (true)
        {
          try
          {
            paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
            if (Integer.valueOf(paramAnonymousResponse.getString("status")).intValue() != ResultCode.RESULT_OK)
              break label526;
            if (((paramAnonymousResponse.getJSONObject("data").isNull("userGiftList")) || (MyConsumptionCouponActivity.this.page == 1)) && ((!paramAnonymousResponse.getJSONObject("data").isNull("userGiftList")) || (MyConsumptionCouponActivity.this.page != 1)) && ((paramAnonymousResponse.getJSONObject("data").isNull("userGiftList")) || (MyConsumptionCouponActivity.this.page != 1)))
              break label495;
            if ((paramAnonymousResponse.getJSONObject("data").getJSONArray("userGiftList").length() == 0) && (MyConsumptionCouponActivity.this.page == 1))
            {
              MyConsumptionCouponActivity.this.giftList.clear();
              MyConsumptionCouponActivity.this.load_nothing_layout.setVisibility(0);
              MyConsumptionCouponActivity.this.lv_my_consumption_coupon.setVisibility(8);
              MyConsumptionCouponActivity.this.mPullToRefreshView.setVisibility(8);
              return;
            }
            paramAnonymousResponse = paramAnonymousResponse.getJSONObject("data").getJSONArray("userGiftList");
            if (MyConsumptionCouponActivity.this.page != 1)
              break label554;
            MyConsumptionCouponActivity.this.giftList.clear();
            break label554;
            if (paramAnonymousInt >= paramAnonymousResponse.length())
            {
              MyConsumptionCouponActivity.this.adapter.setList(MyConsumptionCouponActivity.this.giftList);
              MyConsumptionCouponActivity.this.adapter.notifyDataSetChanged();
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
          localGift.setRequiredBean(localJSONObject.getInt("beanNumber"));
          localGift.setDate(localJSONObject.getString("expireDate"));
          localGift.setGiftName(localJSONObject.getString("giftName"));
          localGift.setFlag(localJSONObject.getString("giftStatus"));
          localGift.setGiftLogo(Url.ADS_URL + localJSONObject.getString("logoPath"));
          localGift.setGiftNumber(localJSONObject.getString("giftNumber"));
          localGift.setShopName(localJSONObject.getString("shopName"));
          localGift.setGiftId(localJSONObject.getInt("giftId"));
          localGift.setAddress(localJSONObject.getString("address"));
          localGift.setH5Url(localJSONObject.getString("h5Url"));
          MyConsumptionCouponActivity.this.giftList.add(localGift);
          paramAnonymousInt += 1;
          continue;
          label495: MyConsumptionCouponActivity.this.toast.setMessage(MyConsumptionCouponActivity.this.getString(2131296376));
          MyConsumptionCouponActivity.this.toast.show();
          return;
          label526: MyConsumptionCouponActivity.this.toast.setMessage(paramAnonymousResponse.getString("msg"));
          MyConsumptionCouponActivity.this.toast.show();
          return;
          label554: paramAnonymousInt = 0;
        }
      }
    });
  }

  private void initView()
  {
    initTitleBar();
    this.tv_title.setText("我的消费券");
    this.mPullToRefreshView = ((AbPullToRefreshView)findViewById(2131230857));
    this.mPullToRefreshView.setOnHeaderRefreshListener(this);
    this.mPullToRefreshView.setOnFooterLoadListener(this);
    this.adapter = new CunsumptionCouponListAdapter(this, this.giftList);
    this.lv_my_consumption_coupon.setAdapter(this.adapter);
    this.mDialog.show();
    this.lv_my_consumption_coupon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
    {
      public boolean onItemLongClick(final AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, final int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView = new AlertDialog.Builder(MyConsumptionCouponActivity.this).create();
        paramAnonymousAdapterView.show();
        paramAnonymousAdapterView.setContentView(2130903159);
        ((TextView)paramAnonymousAdapterView.findViewById(2131231101)).setText("是否确认删除该消费券?");
        paramAnonymousAdapterView.findViewById(2131230873).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            paramAnonymousAdapterView.dismiss();
          }
        });
        paramAnonymousAdapterView.findViewById(2131230782).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            paramAnonymousAdapterView.dismiss();
            MyConsumptionCouponActivity.this.delete(paramAnonymousInt);
            MyConsumptionCouponActivity.this.getExchangedGifts();
          }
        });
        return true;
      }
    });
    this.lv_my_consumption_coupon.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        MyConsumptionCouponActivity.this.toGiftDetail((Gift)MyConsumptionCouponActivity.this.giftList.get(paramAnonymousInt));
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
    setContentView(2130903051);
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
        MyConsumptionCouponActivity localMyConsumptionCouponActivity = MyConsumptionCouponActivity.this;
        localMyConsumptionCouponActivity.page += 1;
        MyConsumptionCouponActivity.this.getExchangedGifts();
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
        MyConsumptionCouponActivity.this.page = 1;
        MyConsumptionCouponActivity.this.getExchangedGifts();
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

  public void toGiftDetail(Gift paramGift)
  {
    if (paramGift == null)
      return;
    Intent localIntent = new Intent(this, WebViewActivity.class);
    localIntent.putExtra("url", paramGift.getH5Url());
    startActivity(localIntent);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.MyConsumptionCouponActivity
 * JD-Core Version:    0.6.2
 */