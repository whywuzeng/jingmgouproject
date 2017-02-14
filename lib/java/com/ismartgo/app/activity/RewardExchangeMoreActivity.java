package com.ismartgo.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.ab.view.ioc.AbIocView;
import com.ismartgo.app.adapter.RewardExchangeMoreListAdapter;
import com.ismartgo.app.bean.BeanScope;
import com.ismartgo.app.bean.Gift;
import com.ismartgo.app.bean.GiftCategory;
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
import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class RewardExchangeMoreActivity extends BaseActivity
  implements AbPullToRefreshView.OnHeaderRefreshListener, AbPullToRefreshView.OnFooterLoadListener
{
  public static String TAG = "RewardExchangeMoreActivity";
  private RewardExchangeMoreListAdapter adapter;
  private GiftCategory category;
  private LinkedList<Gift> giftList1 = new LinkedList();
  private LinkedList<Gift> giftList2 = new LinkedList();
  private LinearLayout load_nothing_layout;

  @AbIocView(id=2131230906)
  private ListView lv_gift;
  private MyDialog mDialog;
  private AbPullToRefreshView mPullToRefreshView;
  private int page = 1;
  private BeanScope scope;
  private int tag;
  private String tagName;
  private TextView tv_load_nothing;

  private void initGifts()
  {
    this.adapter = new RewardExchangeMoreListAdapter(this, this, this.giftList1, this.giftList2);
    this.lv_gift.setAdapter(this.adapter);
    this.adapter.notifyDataSetInvalidated();
  }

  private void initView()
  {
    initTitleBar();
    this.tv_title.setText("奖励兑换-" + this.tagName);
    searchGifts(this.tag, this.tagName);
    this.mDialog.show();
    this.load_nothing_layout = ((LinearLayout)findViewById(2131230855));
    this.tv_load_nothing = ((TextView)findViewById(2131231100));
    this.mPullToRefreshView = ((AbPullToRefreshView)findViewById(2131230905));
    this.mPullToRefreshView.setOnHeaderRefreshListener(this);
    this.mPullToRefreshView.setOnFooterLoadListener(this);
  }

  private void searchGifts(int paramInt, String paramString)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("cityName", city);
    if (this.scope == null)
    {
      localHashMap.put("beforeBean", "");
      localHashMap.put("afterBean", "");
      if (this.category != null)
        break label221;
      localHashMap.put("categoryId", "");
    }
    while (true)
    {
      localHashMap.put("giftTag", paramInt);
      localHashMap.put("giftTagName", paramString);
      localHashMap.put("pages", this.page);
      localHashMap.put("pageSize", "30");
      HttpRequest.getInstance().executePostStringRequest(this, Url.GIFT_SEARCH_URL, HttpWhat.GIFT_SEARCH, localHashMap, new HttpCallback()
      {
        public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
        {
          RewardExchangeMoreActivity.this.mPullToRefreshView.onFooterLoadFinish();
          RewardExchangeMoreActivity.this.mPullToRefreshView.onHeaderRefreshFinish();
          RewardExchangeMoreActivity.this.mDialog.dismiss();
          RewardExchangeMoreActivity.this.toast.setMessage("亲，网络好像出问题了哦~");
          RewardExchangeMoreActivity.this.toast.show();
        }

        public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
        {
          System.out.println("--->t=" + ((String)paramAnonymousResponse.get()).toString());
          Log.e(RewardExchangeMoreActivity.TAG, RewardExchangeMoreActivity.this.page + "<-----------------page");
          RewardExchangeMoreActivity.this.mDialog.dismiss();
          RewardExchangeMoreActivity.this.mPullToRefreshView.onFooterLoadFinish();
          RewardExchangeMoreActivity.this.mPullToRefreshView.onHeaderRefreshFinish();
          while (true)
          {
            try
            {
              paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
              if (Integer.valueOf(paramAnonymousResponse.getString("status")).intValue() != ResultCode.RESULT_OK)
                break label332;
              if (paramAnonymousResponse.getJSONObject("data").getJSONArray("giftList").isNull(0))
              {
                if (RewardExchangeMoreActivity.this.page == 1)
                {
                  RewardExchangeMoreActivity.this.lv_gift.setVisibility(8);
                  RewardExchangeMoreActivity.this.mPullToRefreshView.setVisibility(8);
                  RewardExchangeMoreActivity.this.load_nothing_layout.setVisibility(0);
                  return;
                }
                RewardExchangeMoreActivity.this.toast.setMessage(RewardExchangeMoreActivity.this.getString(2131296376));
                RewardExchangeMoreActivity.this.toast.show();
              }
              if (!paramAnonymousResponse.getJSONObject("data").getJSONArray("giftList").isNull(0));
              paramAnonymousResponse = paramAnonymousResponse.getJSONObject("data").getJSONArray("giftList");
              paramAnonymousInt = 0;
              if (paramAnonymousInt >= paramAnonymousResponse.length())
              {
                RewardExchangeMoreActivity.this.initGifts();
                return;
              }
            }
            catch (Exception paramAnonymousResponse)
            {
              paramAnonymousResponse.printStackTrace();
              return;
            }
            Object localObject = paramAnonymousResponse.getJSONObject(paramAnonymousInt);
            localObject = RewardExchangeMoreActivity.this.Json2Gift((JSONObject)localObject);
            if (paramAnonymousInt % 2 == 0)
            {
              RewardExchangeMoreActivity.this.giftList1.add(localObject);
            }
            else
            {
              RewardExchangeMoreActivity.this.giftList2.add(localObject);
              break label359;
              label332: RewardExchangeMoreActivity.this.toast.setMessage(paramAnonymousResponse.getString("msg"));
              RewardExchangeMoreActivity.this.toast.show();
              return;
            }
            label359: paramAnonymousInt += 1;
          }
        }
      });
      return;
      localHashMap.put("beforeBean", this.scope.getBeforeBean());
      localHashMap.put("afterBean", this.scope.getAfterBean());
      break;
      label221: localHashMap.put("categoryId", this.category.getId());
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903059);
    this.mDialog = new MyDialog(this);
    this.tag = getIntent().getIntExtra("tag", -1);
    this.tagName = getIntent().getStringExtra("tagName");
    this.scope = ((BeanScope)getIntent().getSerializableExtra("scope"));
    this.category = ((GiftCategory)getIntent().getSerializableExtra("category"));
    initView();
  }

  public void onFooterLoad(AbPullToRefreshView paramAbPullToRefreshView)
  {
    this.mPullToRefreshView.postDelayed(new Runnable()
    {
      public void run()
      {
        Log.e(RewardExchangeMoreActivity.TAG, RewardExchangeMoreActivity.this.page);
        RewardExchangeMoreActivity localRewardExchangeMoreActivity = RewardExchangeMoreActivity.this;
        localRewardExchangeMoreActivity.page += 1;
        RewardExchangeMoreActivity.this.searchGifts(RewardExchangeMoreActivity.this.tag, RewardExchangeMoreActivity.this.tagName);
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
        RewardExchangeMoreActivity.this.page = 1;
        RewardExchangeMoreActivity.this.giftList1.clear();
        RewardExchangeMoreActivity.this.giftList2.clear();
        RewardExchangeMoreActivity.this.searchGifts(RewardExchangeMoreActivity.this.tag, RewardExchangeMoreActivity.this.tagName);
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
    localIntent.putExtra("gift", paramGift);
    startActivity(localIntent);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.RewardExchangeMoreActivity
 * JD-Core Version:    0.6.2
 */