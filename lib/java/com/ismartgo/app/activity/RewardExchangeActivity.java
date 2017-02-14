package com.ismartgo.app.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ab.view.ioc.AbIocView;
import com.ismartgo.app.adapter.RewardExchangeAdapter;
import com.ismartgo.app.bean.BeanScope;
import com.ismartgo.app.bean.Gift;
import com.ismartgo.app.bean.GiftCategory;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.grid.utils.DisplayUtil;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.http.HttpWhat;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.popwindow.ViewMiddle_OneString;
import com.ismartgo.app.popwindow.ViewMiddle_OneString.OnSelectListener;
import com.ismartgo.app.tools.ImgLoader;
import com.ismartgo.app.tools.StringUtils;
import com.ismartgo.app.url.ResultCode;
import com.ismartgo.app.url.Url;
import com.miloisbadboy.view.PullToRefreshView;
import com.miloisbadboy.view.PullToRefreshView.OnFooterRefreshListener;
import com.miloisbadboy.view.PullToRefreshView.OnHeaderRefreshListener;
import com.umeng.analytics.MobclickAgent;
import com.yolanda.nohttp.Response;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class RewardExchangeActivity extends BaseActivity
  implements View.OnClickListener, PullToRefreshView.OnHeaderRefreshListener, PullToRefreshView.OnFooterRefreshListener
{
  public static String TAG = "RewardConvertActivity";
  private RewardExchangeAdapter adapter;
  private LinkedList<BeanScope> beanScopeList = new LinkedList();
  private LinkedList<String> beanScopeStringList = new LinkedList();
  GiftCategory category = null;
  private LinkedList<GiftCategory> giftCategoryList = new LinkedList();
  private LinkedList<String> giftCategoryStringList = new LinkedList();
  private List<Gift> giftList;
  private LinkedList<Gift> giftList1;
  private LinkedList<Gift> giftList2;
  private ImageView imgNoData;
  private boolean isFirstLoading;
  private boolean isRunning;

  @AbIocView(click="onClick", id=2131230806)
  private LinearLayout layout_beans;

  @AbIocView(click="onClick", id=2131230901)
  private LinearLayout layout_clazz;
  private RelativeLayout layout_my_beans;
  private View load_nothing_layout;
  private MyDialog mDialog;
  private GridView mGridView;
  private PullToRefreshView mPullToRefreshView;
  private int pages;
  private PopupWindow popWin;
  BeanScope scope = null;
  private boolean showBeanScope = false;
  private boolean showClazz = false;
  private TextView tvMyBeacon;
  private TextView tvNoData;

  @AbIocView(click="onClick", id=2131230783)
  private TextView tv_beans;

  @AbIocView(click="onClick", id=2131230902)
  private TextView tv_class;

  @AbIocView(id=2131230972)
  private TextView tv_num;
  private ViewMiddle_OneString viewMiddle_BeanScope;
  private ViewMiddle_OneString viewMiddle_giftCategory;

  private void clickBeanScope(View paramView)
  {
    if ((!this.showBeanScope) && ((this.popWin == null) || (!this.popWin.isShowing())))
    {
      this.popWin = new PopupWindow(this.viewMiddle_BeanScope, -1, -2, false);
      this.popWin.setBackgroundDrawable(new BitmapDrawable());
      this.popWin.setOutsideTouchable(true);
      this.popWin.showAsDropDown(paramView, 0, DisplayUtil.dip2px(this, 1.0F));
      this.showBeanScope = true;
    }
    do
    {
      return;
      if (this.showBeanScope)
        this.showBeanScope = false;
    }
    while ((this.popWin == null) || (!this.popWin.isShowing()));
    this.popWin.dismiss();
  }

  private void clickClass(View paramView)
  {
    if ((!this.showClazz) && ((this.popWin == null) || (!this.popWin.isShowing())))
    {
      this.popWin = new PopupWindow(this.viewMiddle_giftCategory, -1, -2, false);
      this.popWin.setBackgroundDrawable(new BitmapDrawable());
      this.popWin.setOutsideTouchable(true);
      this.popWin.showAsDropDown(paramView, 0, DisplayUtil.dip2px(this, 1.0F));
      this.showClazz = true;
    }
    do
    {
      return;
      if (this.showClazz)
        this.showClazz = false;
    }
    while ((this.popWin == null) || (!this.popWin.isShowing()));
    this.popWin.dismiss();
  }

  private void dismissPop()
  {
    this.popWin.dismiss();
    this.showBeanScope = false;
    this.showClazz = false;
  }

  private void getBeansScope()
  {
    HttpRequest.getInstance().executePostStringRequest(this, Url.BEANS_SCOPE_URL, HttpWhat.BEANS_SCOPE, null, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
        RewardExchangeActivity.this.toast.setMessage("亲，网络好像出问题了~");
        RewardExchangeActivity.this.toast.show();
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        System.out.println("--->t=" + ((String)paramAnonymousResponse.get()).toString());
        try
        {
          paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
          if (Integer.valueOf(paramAnonymousResponse.getString("status")).intValue() == ResultCode.RESULT_OK)
          {
            paramAnonymousResponse = paramAnonymousResponse.getJSONObject("data").getJSONArray("BeanList");
            RewardExchangeActivity.this.beanScopeList.clear();
            RewardExchangeActivity.this.beanScopeStringList.clear();
            RewardExchangeActivity.this.beanScopeStringList.add("所有");
            paramAnonymousInt = 0;
            while (true)
            {
              if (paramAnonymousInt >= paramAnonymousResponse.length())
              {
                RewardExchangeActivity.this.viewMiddle_BeanScope = new ViewMiddle_OneString(RewardExchangeActivity.this, RewardExchangeActivity.this.beanScopeStringList);
                RewardExchangeActivity.this.initListener();
                return;
              }
              JSONObject localJSONObject = paramAnonymousResponse.getJSONObject(paramAnonymousInt).getJSONObject("BeanScope");
              BeanScope localBeanScope = new BeanScope();
              localBeanScope.setId(localJSONObject.getInt("id"));
              localBeanScope.setBeanScope(localJSONObject.getString("beanScope"));
              localBeanScope.setBeforeBean(localJSONObject.getInt("beforeBean"));
              localBeanScope.setAfterBean(localJSONObject.getInt("afterBean"));
              RewardExchangeActivity.this.beanScopeList.add(localBeanScope);
              RewardExchangeActivity.this.beanScopeStringList.add(localBeanScope.getBeanScope());
              paramAnonymousInt += 1;
            }
          }
          RewardExchangeActivity.this.toast.setMessage(paramAnonymousResponse.getString("msg"));
          RewardExchangeActivity.this.toast.show();
          return;
        }
        catch (Exception paramAnonymousResponse)
        {
          paramAnonymousResponse.printStackTrace();
        }
      }
    });
  }

  private void getGiftCategory()
  {
    HttpRequest.getInstance().executePostStringRequest(this, Url.GIFT_CATEGORY_URL, HttpWhat.GIFT_CATEGORY, null, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
        RewardExchangeActivity.this.toast.setMessage("亲，网络好像出问题了哦~");
        RewardExchangeActivity.this.toast.show();
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        System.out.println("--->t=" + ((String)paramAnonymousResponse.get()).toString());
        try
        {
          paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
          if (Integer.valueOf(paramAnonymousResponse.getString("status")).intValue() == ResultCode.RESULT_OK)
          {
            paramAnonymousResponse = paramAnonymousResponse.getJSONObject("data").getJSONArray("GiftCategoryList");
            RewardExchangeActivity.this.giftCategoryList.clear();
            RewardExchangeActivity.this.giftCategoryStringList.clear();
            RewardExchangeActivity.this.giftCategoryStringList.add("全部分类");
            paramAnonymousInt = 0;
            while (true)
            {
              if (paramAnonymousInt >= paramAnonymousResponse.length())
              {
                RewardExchangeActivity.this.viewMiddle_giftCategory = new ViewMiddle_OneString(RewardExchangeActivity.this, RewardExchangeActivity.this.giftCategoryStringList);
                RewardExchangeActivity.this.initListener();
                return;
              }
              JSONObject localJSONObject = paramAnonymousResponse.getJSONObject(paramAnonymousInt).getJSONObject("GiftCategory");
              GiftCategory localGiftCategory = new GiftCategory();
              localGiftCategory.setId(localJSONObject.getInt("id"));
              localGiftCategory.setGiftCategoryName(localJSONObject.getString("giftCategoryName"));
              localGiftCategory.setGiftType(localJSONObject.getInt("giftType"));
              RewardExchangeActivity.this.giftCategoryList.add(localGiftCategory);
              RewardExchangeActivity.this.giftCategoryStringList.add(localGiftCategory.getGiftCategoryName());
              paramAnonymousInt += 1;
            }
          }
          RewardExchangeActivity.this.toast.setMessage(paramAnonymousResponse.getString("msg"));
          RewardExchangeActivity.this.toast.show();
          return;
        }
        catch (Exception paramAnonymousResponse)
        {
          paramAnonymousResponse.printStackTrace();
        }
      }
    });
  }

  private void initListener()
  {
    this.viewMiddle_BeanScope.setOnSelectListener(new ViewMiddle_OneString.OnSelectListener()
    {
      public void getValue(String paramAnonymousString)
      {
        String str = paramAnonymousString;
        if (paramAnonymousString.equals("所有"))
          str = "豆子范围";
        RewardExchangeActivity.this.onSelect(RewardExchangeActivity.this.tv_beans, str);
      }
    });
    this.viewMiddle_giftCategory.setOnSelectListener(new ViewMiddle_OneString.OnSelectListener()
    {
      public void getValue(String paramAnonymousString)
      {
        RewardExchangeActivity.this.onSelect(RewardExchangeActivity.this.tv_class, paramAnonymousString);
      }
    });
  }

  private void initView()
  {
    initTitleBar();
    this.tv_title.setText("礼品兑换");
    this.layout_my_beans = ((RelativeLayout)findViewById(2131230824));
    this.layout_my_beans.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent(RewardExchangeActivity.this, MyBeansActivity.class);
        RewardExchangeActivity.this.startActivity(paramAnonymousView);
        RewardExchangeActivity.this.finish();
      }
    });
    this.tvMyBeacon = ((TextView)findViewById(2131230900));
    if (loginUser != null)
      this.tvMyBeacon.setText(loginUser.getBeanNumber() + "豆");
    this.load_nothing_layout = findViewById(2131230903);
    this.imgNoData = ((ImageView)findViewById(2131231099));
    this.tvNoData = ((TextView)findViewById(2131231100));
    this.mGridView = ((GridView)findViewById(2131230904));
    this.mPullToRefreshView = ((PullToRefreshView)findViewById(2131230808));
    this.mPullToRefreshView.setOnFooterRefreshListener(this);
    this.mPullToRefreshView.setOnHeaderRefreshListener(this);
  }

  private void onSelect(TextView paramTextView, String paramString)
  {
    this.pages = 0;
    dismissPop();
    paramTextView.setText(paramString);
    paramTextView = this.tv_beans.getText().toString();
    int i = this.beanScopeStringList.indexOf(paramTextView);
    this.scope = null;
    if (i > 0)
      this.scope = ((BeanScope)this.beanScopeList.get(i - 1));
    paramTextView = this.tv_class.getText().toString();
    i = this.giftCategoryStringList.indexOf(paramTextView);
    System.out.println("--->p=" + i);
    this.category = null;
    if (i > 0)
      this.category = ((GiftCategory)this.giftCategoryList.get(i - 1));
    searchGifts(this.scope, this.category);
  }

  private void searchGifts(BeanScope paramBeanScope, GiftCategory paramGiftCategory)
  {
    this.isRunning = true;
    HashMap localHashMap = new HashMap();
    if (paramBeanScope == null)
    {
      localHashMap.put("beforeBean", "");
      localHashMap.put("afterBean", "");
      if (paramGiftCategory != null)
        break label209;
      localHashMap.put("categoryId", "");
    }
    while (true)
    {
      int i = this.pages + 1;
      this.pages = i;
      localHashMap.put("pages", String.valueOf(i));
      localHashMap.put("pageSize", String.valueOf(30));
      if (!this.isFirstLoading)
      {
        this.isFirstLoading = true;
        this.mDialog.show();
      }
      HttpRequest.getInstance().executePostStringRequest(this, Url.GIFT_SEARCH_URL, HttpWhat.GIFT_SEARCH, localHashMap, new HttpCallback()
      {
        public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
        {
          RewardExchangeActivity.this.mPullToRefreshView.onFooterRefreshComplete();
          RewardExchangeActivity.this.mPullToRefreshView.onHeaderRefreshComplete();
          RewardExchangeActivity.this.mDialog.dismiss();
          RewardExchangeActivity.this.toast.setMessage("亲，网络好像出问题了~");
          RewardExchangeActivity.this.toast.show();
          RewardExchangeActivity.this.isRunning = false;
          paramAnonymousString = RewardExchangeActivity.this;
          paramAnonymousString.pages -= 1;
        }

        public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
        {
          RewardExchangeActivity.this.mDialog.dismiss();
          RewardExchangeActivity.this.mPullToRefreshView.onFooterRefreshComplete();
          RewardExchangeActivity.this.mPullToRefreshView.onHeaderRefreshComplete();
          Log.i(RewardExchangeActivity.TAG, "数据： " + ((String)paramAnonymousResponse.get()).toString());
          if (paramAnonymousResponse != null)
          {
            if (RewardExchangeActivity.this.giftList == null)
              RewardExchangeActivity.this.giftList = new ArrayList();
            if (RewardExchangeActivity.this.pages == 1)
              RewardExchangeActivity.this.giftList.clear();
          }
          try
          {
            paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
            if (Integer.valueOf(paramAnonymousResponse.getString("status")).intValue() == ResultCode.RESULT_OK)
            {
              paramAnonymousResponse = paramAnonymousResponse.getJSONObject("data").getJSONArray("giftList");
              paramAnonymousInt = 0;
              if (paramAnonymousInt >= paramAnonymousResponse.length())
              {
                if (RewardExchangeActivity.this.adapter != null)
                  break label447;
                RewardExchangeActivity.this.adapter = new RewardExchangeAdapter(RewardExchangeActivity.this, RewardExchangeActivity.this.giftList, RewardExchangeActivity.this.mGridView);
                RewardExchangeActivity.this.mGridView.setAdapter(RewardExchangeActivity.this.adapter);
              }
              while (true)
              {
                if (RewardExchangeActivity.this.giftList.size() != 0)
                  break label468;
                RewardExchangeActivity.this.load_nothing_layout.setVisibility(0);
                RewardExchangeActivity.this.mPullToRefreshView.setVisibility(8);
                RewardExchangeActivity.this.imgNoData.setImageResource(2130837650);
                RewardExchangeActivity.this.tvNoData.setText("亲，没有找到您要的礼品哦~");
                RewardExchangeActivity.this.isRunning = false;
                return;
                JSONObject localJSONObject = paramAnonymousResponse.getJSONObject(paramAnonymousInt);
                Gift localGift = new Gift();
                localGift.setGiftId(localJSONObject.getInt("giftId"));
                localGift.setGiftName(localJSONObject.getString("giftName"));
                localGift.setGiftLogo(Url.ADS_URL + localJSONObject.getString("giftLogo"));
                localGift.setRequiredBean(localJSONObject.getInt("requiredBean"));
                localGift.setGiftType(String.valueOf(localJSONObject.getInt("giftMode")));
                localGift.setH5Url(localJSONObject.getString("h5Url"));
                localGift.setNewGift(localJSONObject.optInt("isnew"));
                RewardExchangeActivity.this.giftList.add(localGift);
                paramAnonymousInt += 1;
                break;
                label447: RewardExchangeActivity.this.adapter.notifyDataSetChanged();
              }
            }
          }
          catch (Exception paramAnonymousResponse)
          {
            while (true)
            {
              paramAnonymousResponse.printStackTrace();
              continue;
              label468: RewardExchangeActivity.this.load_nothing_layout.setVisibility(8);
              RewardExchangeActivity.this.mPullToRefreshView.setVisibility(0);
              continue;
              RewardExchangeActivity.this.toast.setMessage(paramAnonymousResponse.getString("msg"));
              RewardExchangeActivity.this.toast.show();
            }
          }
        }
      });
      return;
      localHashMap.put("beforeBean", paramBeanScope.getBeforeBean());
      localHashMap.put("afterBean", paramBeanScope.getAfterBean());
      break;
      label209: localHashMap.put("categoryId", paramGiftCategory.getId());
    }
  }

  private void setGift(Gift paramGift, View paramView)
  {
    ((TextView)paramView.findViewById(2131230878)).setText(paramGift.getGiftName());
    ((TextView)paramView.findViewById(2131230783)).setText(paramGift.getRequiredBean() + "豆");
    ((TextView)paramView.findViewById(2131230783)).setTextColor(Color.parseColor("#2D8DE3"));
    ImgLoader.getInstance(this).showPic(StringUtils.getImgUrl(paramGift.getGiftLogo()), (ImageView)paramView.findViewById(2131230957), false);
  }

  private void setGone(View paramView)
  {
    paramView.setVisibility(8);
  }

  private void setVisible(View paramView)
  {
    paramView.setVisibility(0);
  }

  private void toMoreAvtivity(int paramInt, String paramString)
  {
    Intent localIntent = new Intent(this, RewardExchangeMoreActivity.class);
    localIntent.putExtra("tag", paramInt);
    localIntent.putExtra("tagName", paramString);
    localIntent.putExtra("scope", this.scope);
    localIntent.putExtra("category", this.category);
    startActivity(localIntent);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131230783:
    case 2131230806:
      clickBeanScope(paramView);
      return;
    case 2131230901:
    case 2131230902:
    }
    clickClass(paramView);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903058);
    initView();
    this.mDialog = new MyDialog(this);
    searchGifts(null, null);
    getGiftCategory();
    getBeansScope();
  }

  public void onFooterRefresh(PullToRefreshView paramPullToRefreshView)
  {
    searchGifts(this.scope, this.category);
  }

  public void onHeaderRefresh(PullToRefreshView paramPullToRefreshView)
  {
    this.pages = 0;
    searchGifts(this.scope, this.category);
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  protected void onPause()
  {
    super.onPause();
    MobclickAgent.onPause(this);
  }

  protected void onResume()
  {
    super.onResume();
    if (loginUser != null)
      this.tvMyBeacon.setText(loginUser.getBeanNumber() + "豆");
    MobclickAgent.onResume(this);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.RewardExchangeActivity
 * JD-Core Version:    0.6.2
 */