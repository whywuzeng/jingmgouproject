package com.ismartgo.app.grid.view;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;
import com.ismartgo.app.activity.BaseActivity;
import com.ismartgo.app.activity.EarnBeansActivity;
import com.ismartgo.app.activity.HomeActivity;
import com.ismartgo.app.activity.HomeActivity.StaggeredAdapter;
import com.ismartgo.app.activity.InviteFriendsActivity;
import com.ismartgo.app.activity.LoginActivity;
import com.ismartgo.app.activity.MyBeansActivity;
import com.ismartgo.app.activity.MyReceiptListActivity;
import com.ismartgo.app.activity.ShootReceiptActivity;
import com.ismartgo.app.activity.UploadReceiptActivity;
import com.ismartgo.app.adapter.ImagePagerAdapter;
import com.ismartgo.app.application.AndroidApplication;
import com.ismartgo.app.bean.Advertise;
import com.ismartgo.app.bean.CurrentLocationInfo;
import com.ismartgo.app.bean.FunctionMode;
import com.ismartgo.app.bean.Infos;
import com.ismartgo.app.bean.SmartHeadInfo;
import com.ismartgo.app.bean.Store;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.grid.SmartStraggeredGridView;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpJsonParse;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.http.HttpWhat;
import com.ismartgo.app.interfaces.OnCompleteListener;
import com.ismartgo.app.net.HomeAdvertiseRequest;
import com.ismartgo.app.ownself.view.CircleFlowIndicator;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.ownself.view.ViewFlow;
import com.ismartgo.app.tools.ImgLoader;
import com.ismartgo.app.tools.SharedPreferenceUtil;
import com.ismartgo.app.tools.StringUtils;
import com.ismartgo.app.url.ResultCode;
import com.ismartgo.app.url.Url;
import com.ismartgo.app.utils.UMengStatisticsUtils;
import com.yolanda.nohttp.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class HomeHeadView extends LinearLayout
  implements View.OnClickListener, ViewSwitcher.ViewFactory
{
  private ArrayList<String> adIdList = new ArrayList();
  private Context context;
  private int currentPosition = 0;
  private Handler handler = new Handler();
  private ArrayList<String> imageUrlList = new ArrayList();
  private ImagePagerAdapter imgAdapter;
  private List<FunctionMode> items = new ArrayList();
  private LinearLayout linearLayout;
  private ArrayList<String> linkUrlArray = new ArrayList();
  private CircleFlowIndicator mFlowIndicator;
  private SmartStraggeredGridView mGridView;
  private ViewFlow mViewFlow;
  private List<SmartHeadInfo> smartHeads = new ArrayList();
  private TextSwitcher switcher;
  private int tempPosition = 0;
  private Timer timer;
  public ToastDefine toast;
  private View view;

  public HomeHeadView(Context paramContext, SmartStraggeredGridView paramSmartStraggeredGridView, HomeActivity.StaggeredAdapter paramStaggeredAdapter, LinkedList<Store> paramLinkedList)
  {
    super(paramContext);
    this.context = paramContext;
    this.mGridView = paramSmartStraggeredGridView;
    initView();
  }

  private void initAdBanner()
  {
    this.mViewFlow = ((ViewFlow)this.view.findViewById(2131231084));
    this.mViewFlow.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        int i = paramAnonymousMotionEvent.getAction();
        paramAnonymousView = (HomeActivity)HomeHeadView.this.context;
        if ((i == 0) || (i == 2) || (i == 1))
        {
          if (i == 2)
          {
            HomeHeadView.this.mGridView.requestDisallowInterceptTouchEvent(true);
            paramAnonymousView.refreshLayout.setEnabled(false);
          }
        }
        else
          return false;
        HomeHeadView.this.mGridView.requestDisallowInterceptTouchEvent(false);
        paramAnonymousView.refreshLayout.setEnabled(true);
        return false;
      }
    });
    this.mViewFlow.setmSideBuffer(this.imageUrlList.size());
    this.mViewFlow.setAdapter(this.imgAdapter);
    this.mViewFlow.setFlowIndicator(this.mFlowIndicator);
    this.mViewFlow.setTimeSpan(5000L);
    this.mViewFlow.setSelection(this.imageUrlList.size() * 20);
    this.mViewFlow.startAutoFlowTimer();
    if (this.imageUrlList.size() <= 1)
      this.mFlowIndicator.setVisibility(8);
  }

  private void initChildView(List<FunctionMode> paramList, int paramInt)
  {
    LinearLayout localLinearLayout = (LinearLayout)((HomeActivity)this.context).getLayoutInflater().inflate(2130903121, this.linearLayout, false);
    localLinearLayout.setId(((FunctionMode)paramList.get(paramInt)).getId());
    ((TextView)localLinearLayout.findViewById(2131231121)).setText(((FunctionMode)paramList.get(paramInt)).getTitle());
    ImageView localImageView = (ImageView)localLinearLayout.findViewById(2131231120);
    ImgLoader.getInstance(this.context).showPic(((FunctionMode)paramList.get(paramInt)).getImgUrl(), localImageView, true);
    localLinearLayout.setOnClickListener(this);
    this.linearLayout.addView(localLinearLayout);
  }

  private void initView()
  {
    this.view = LayoutInflater.from(this.context).inflate(2130903042, this);
    this.linearLayout = ((LinearLayout)this.view.findViewById(2131230784));
    this.mFlowIndicator = ((CircleFlowIndicator)this.view.findViewById(2131231085));
    this.switcher = ((TextSwitcher)this.view.findViewById(2131230785));
    this.switcher.setFactory(this);
    Animation localAnimation1 = AnimationUtils.loadAnimation(this.context, 2130968583);
    Animation localAnimation2 = AnimationUtils.loadAnimation(this.context, 2130968600);
    this.switcher.setInAnimation(localAnimation1);
    this.switcher.setOutAnimation(localAnimation2);
    this.switcher.setOnClickListener(this);
    this.toast = new ToastDefine(this.context);
  }

  private void smartgoappUrl(String paramString, Intent paramIntent)
  {
    if (paramString.equals("smartgoapp://sign"))
    {
      UMengStatisticsUtils.homeSignShop(this.context);
      EarnBeansActivity.clickSignInAndScan(this.context, 1);
      return;
    }
    if (paramString.equals("smartgoapp://saomiao"))
    {
      UMengStatisticsUtils.homeScanMsg(this.context);
      EarnBeansActivity.clickSignInAndScan(this.context, 2);
      return;
    }
    if (paramString.equals("smartgoapp://invite_friend"))
    {
      UMengStatisticsUtils.homeReplyInvite(this.context);
      paramString = new Intent(this.context, InviteFriendsActivity.class);
      this.context.startActivity(paramString);
      return;
    }
    if (paramString.equals("smartgoapp://receipt"))
    {
      UMengStatisticsUtils.homeTicket(this.context);
      paramString = new Intent(this.context, UploadReceiptActivity.class);
      this.context.startActivity(paramString);
      return;
    }
    if (paramString.equals("smartgoapp://receipt/list"))
    {
      if ((BaseActivity.loginUser != null) && (BaseActivity.loginUser.getLoginType() != 6))
      {
        paramString = new Intent(this.context, MyReceiptListActivity.class);
        this.context.startActivity(paramString);
        return;
      }
      paramString = new Intent(this.context, LoginActivity.class);
      this.context.startActivity(paramString);
      return;
    }
    if (paramString.equals("smartgoapp://my/bean"))
    {
      paramString = new Intent(this.context, MyBeansActivity.class);
      this.context.startActivity(paramString);
      return;
    }
    if ((BaseActivity.loginUser != null) && (BaseActivity.loginUser.getLoginType() != 6))
    {
      paramIntent = new Intent(this.context, ShootReceiptActivity.class);
      paramIntent.putExtra("url", paramString);
      this.context.startActivity(paramIntent);
      return;
    }
    paramString = new Intent(this.context, LoginActivity.class);
    this.context.startActivity(paramString);
  }

  public void getAdvertiseImg(String paramString)
  {
    HomeAdvertiseRequest localHomeAdvertiseRequest = new HomeAdvertiseRequest(this.context, Url.ADVERTISE_URL);
    localHomeAdvertiseRequest.initParams(paramString, 8);
    localHomeAdvertiseRequest.setOnCompleteListener(new OnCompleteListener()
    {
      public void onFailure(Throwable paramAnonymousThrowable, int paramAnonymousInt, String paramAnonymousString)
      {
        if (HomeHeadView.this.imageUrlList.size() == 0)
        {
          HomeHeadView.this.toast.setMessage("亲，网络好像出问题了哦~");
          HomeHeadView.this.toast.show();
        }
      }

      public void onResult(Object paramAnonymousObject, int paramAnonymousInt)
      {
        Object localObject = (Infos)paramAnonymousObject;
        if ((paramAnonymousObject != null) && (paramAnonymousInt == ResultCode.RESULT_OK))
        {
          paramAnonymousObject = ((Infos)localObject).getAdver_list();
          HomeHeadView.this.imageUrlList.clear();
          HomeHeadView.this.linkUrlArray.clear();
          paramAnonymousInt = 0;
          if (paramAnonymousInt < paramAnonymousObject.size())
            break label131;
          paramAnonymousObject = HomeHeadView.this;
          localObject = new ImagePagerAdapter(HomeHeadView.this.context, HomeHeadView.this.imageUrlList, HomeHeadView.this.linkUrlArray, HomeHeadView.this.adIdList);
          if (HomeHeadView.this.imageUrlList.size() <= 1)
            break label274;
        }
        label131: label274: for (boolean bool = true; ; bool = false)
        {
          paramAnonymousObject.imgAdapter = ((ImagePagerAdapter)localObject).setInfiniteLoop(bool);
          HomeHeadView.this.initAdBanner();
          return;
          HomeHeadView.this.adIdList.add(((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getId());
          HomeHeadView.this.imageUrlList.add(StringUtils.getImgUrl(((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getPicPath()));
          if (!((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getThirdLink().equals(""))
            HomeHeadView.this.linkUrlArray.add(((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getThirdLink());
          while (true)
          {
            paramAnonymousInt += 1;
            break;
            HomeHeadView.this.linkUrlArray.add(((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getDetail());
          }
        }
      }
    });
    localHomeAdvertiseRequest.startRequest();
  }

  public void getData(String paramString, boolean paramBoolean)
  {
    this.items = HttpJsonParse.getHomeFunctionInfo(paramString);
    int i;
    if (this.items != null)
    {
      if (paramBoolean)
      {
        SharedPreferenceUtil.setHomeHeadButton(this.context, paramString);
        SharedPreferenceUtil.setHomeBottonFlag(this.context, 1);
      }
      i = 0;
    }
    while (true)
    {
      if (i >= this.items.size())
        return;
      initChildView(this.items, i);
      i += 1;
    }
  }

  public View getView()
  {
    return this.view;
  }

  public void initData(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (this.timer != null)
        this.timer.cancel();
      this.items.clear();
      this.smartHeads.clear();
      this.linearLayout.removeAllViews();
      this.currentPosition = 0;
      this.tempPosition = 0;
    }
    String str = SharedPreferenceUtil.getHomeHeadButton(this.context);
    HashMap localHashMap;
    if ((SharedPreferenceUtil.getHomeBottonFlag(this.context) == 0) || (str.equals("")))
    {
      HttpRequest.getInstance().executePostStringRequest(this.context, Url.HOME_HEAD_MODE, HttpWhat.GET_HOME_HEAD_MODE, null, new HttpCallback()
      {
        public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
        {
        }

        public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
        {
          HomeHeadView.this.getData(((String)paramAnonymousResponse.get()).toString(), true);
        }
      });
      if (paramBoolean)
      {
        localHashMap = new HashMap();
        if (BaseActivity.loginUser.getLoginType() != 6)
          break label255;
        str = "0";
        label133: localHashMap.put("uid", str);
        if (AndroidApplication.getInstance().getCurrentLocation() != null)
          break label265;
        str = "0";
        label157: localHashMap.put("cityid", str);
        if (BaseActivity.log != null)
          break label291;
        str = "0";
        label178: localHashMap.put("gpslon", str);
        if (BaseActivity.lat != null)
          break label298;
      }
    }
    label265: label291: label298: for (str = "0"; ; str = BaseActivity.lat)
    {
      localHashMap.put("gpslat", str);
      HttpRequest.getInstance().executePostStringRequest(this.context, Url.HOME_HEAD_TOP, HttpWhat.GET_HOME_HEAD_TOP, localHashMap, new HttpCallback()
      {
        public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
        {
        }

        public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
        {
          HomeHeadView.this.smartHeads = HttpJsonParse.getSmartHead(((String)paramAnonymousResponse.get()).toString());
          Log.d("smartgo", ((String)paramAnonymousResponse.get()).toString());
          if ((HomeHeadView.this.smartHeads != null) && (HomeHeadView.this.smartHeads.size() > 0))
          {
            HomeHeadView.this.timer = new Timer();
            HomeHeadView.this.timer.schedule(new TimerTask()
            {
              public void run()
              {
                HomeHeadView.this.handler.post(new Runnable()
                {
                  public void run()
                  {
                    if (HomeHeadView.this.tempPosition > HomeHeadView.this.smartHeads.size() - 1)
                      HomeHeadView.this.tempPosition = 0;
                    HomeHeadView.this.currentPosition = HomeHeadView.this.tempPosition;
                    HomeHeadView.this.switcher.setText(((SmartHeadInfo)HomeHeadView.this.smartHeads.get(HomeHeadView.this.currentPosition)).getTitle());
                    HomeHeadView localHomeHeadView = HomeHeadView.this;
                    localHomeHeadView.tempPosition += 1;
                  }
                });
              }
            }
            , 0L, 3000L);
          }
        }
      });
      return;
      Log.d("smartgo", "头部使用了缓存");
      getData(str, false);
      break;
      label255: str = BaseActivity.loginUser.getId();
      break label133;
      str = AndroidApplication.getInstance().getCurrentLocation().getCityId();
      break label157;
      str = BaseActivity.log;
      break label178;
    }
  }

  public View makeView()
  {
    TextView localTextView = new TextView(this.context);
    localTextView.setGravity(16);
    localTextView.setSingleLine();
    localTextView.setEllipsize(TextUtils.TruncateAt.valueOf("END"));
    localTextView.setTextSize(12.0F);
    return localTextView;
  }

  public void onClick(View paramView)
  {
    Log.d("smartgo", paramView.getId());
    int i = paramView.getId();
    paramView = new Intent();
    switch (i)
    {
    default:
      if ((i <= this.items.size()) && (i > 0))
        smartgoappUrl(((FunctionMode)this.items.get(i - 1)).getClickUrl(), paramView);
      break;
    case 2131230785:
    }
    do
      return;
    while ((this.smartHeads == null) || (this.smartHeads.size() <= 0));
    smartgoappUrl(((SmartHeadInfo)this.smartHeads.get(this.currentPosition)).getClickurl(), paramView);
  }

  public void setView(View paramView)
  {
    this.view = paramView;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.view.HomeHeadView
 * JD-Core Version:    0.6.2
 */