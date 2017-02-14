package com.ismartgo.app.activity;

import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.ab.view.ioc.AbIocView;
import com.ismartgo.app.adapter.ImagePagerAdapter;
import com.ismartgo.app.bean.Advertise;
import com.ismartgo.app.bean.Infos;
import com.ismartgo.app.bean.InviteHistoryInfo;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.grid.utils.DisplayUtil;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.http.HttpWhat;
import com.ismartgo.app.interfaces.OnCompleteListener;
import com.ismartgo.app.net.HomeAdvertiseRequest;
import com.ismartgo.app.ownself.view.CircleFlowIndicator;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.ownself.view.ViewFlow;
import com.ismartgo.app.share.ShareUtils;
import com.ismartgo.app.tools.ImgLoader;
import com.ismartgo.app.tools.StringUtils;
import com.ismartgo.app.url.ResultCode;
import com.ismartgo.app.url.Url;
import com.miloisbadboy.view.PullToRefreshView;
import com.miloisbadboy.view.PullToRefreshView.OnFooterRefreshListener;
import com.miloisbadboy.view.PullToRefreshView.OnHeaderRefreshListener;
import com.umeng.analytics.MobclickAgent;
import com.yolanda.nohttp.Response;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InviteFriendsActivity extends BaseActivity
  implements View.OnClickListener, PullToRefreshView.OnFooterRefreshListener, PullToRefreshView.OnHeaderRefreshListener
{
  private ArrayList<String> adIdList = new ArrayList();
  private LinearLayout btn_qq;
  private LinearLayout btn_qzone;
  private LinearLayout btn_send_msg;
  private LinearLayout btn_wechat_moments;
  private LinearLayout btn_weibo;
  private GestureDetector detector;
  private int gapHeight;
  private InviteHistoryAdapter historyAdapter;
  private ListView historyListView;
  int historyPage = 1;
  private ArrayList<String> imageUrlList = new ArrayList();
  private ImagePagerAdapter imgAdapter;
  private ImageView imgArrow;
  private ImageView imgLeft;
  private ImageView imgRight;
  private int incrementHeight;
  private boolean isBreak = false;
  private boolean isRunning;
  private boolean isUp;
  private LinearLayout layoutArrow;
  private LinearLayout layoutBeans;
  private LinearLayout layoutHistory;
  private LinearLayout layoutInviteFriend;
  private RelativeLayout layoutInviteHistory;
  private ArrayList<String> linkUrlArray = new ArrayList();
  private List<InviteHistoryInfo> list;
  private CircleFlowIndicator mFlowIndicator;
  private HorizontalScrollView mScrollView;
  private ViewFlow mViewFlow;
  private int panelHeight;
  private PullToRefreshView pull_refresh_view;
  private int screenHeight;
  private int screenWidth;
  private int shareGap;
  private int targetHeight;
  private boolean taskRun = false;
  private int temHeight;
  private long time = 300L;
  private Timer timer;
  private TimerTask timerTask;
  ToastDefine toast;
  private TextView tvCopy;
  private TextView tvInviteBeans;

  @AbIocView(id=2131230791)
  private TextView tv_my_invite_code;
  private View upViewBg;
  private long updateUiTime = 30L;

  private int calculateGap()
  {
    return (int)((this.screenWidth - (DisplayUtil.dip2px(this, 10.0F) + 3.5D * DisplayUtil.dip2px(this, 66.0F))) / 3.0D);
  }

  private void collapsePane()
  {
    this.upViewBg.setVisibility(8);
    this.isUp = false;
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams.addRule(3, 2131230790);
    localLayoutParams.topMargin = DisplayUtil.dip2px(this, 10.0F);
    this.layoutInviteHistory.setLayoutParams(localLayoutParams);
    this.imgArrow.setImageResource(2130837682);
    this.temHeight = this.panelHeight;
    this.isBreak = true;
  }

  private void expandPane()
  {
    this.upViewBg.setVisibility(0);
    this.isUp = true;
    if (this.targetHeight <= this.panelHeight)
      return;
    if (!this.taskRun)
    {
      if (this.timer != null)
        this.timer.schedule(this.timerTask, 0L, this.time / this.updateUiTime);
      this.taskRun = true;
      return;
    }
    this.isBreak = false;
  }

  private void getAdvertiseImg()
  {
    HomeAdvertiseRequest localHomeAdvertiseRequest = new HomeAdvertiseRequest(this, Url.ADVERTISE_URL);
    localHomeAdvertiseRequest.initParams(city, 4);
    localHomeAdvertiseRequest.setOnCompleteListener(new OnCompleteListener()
    {
      public void onFailure(Throwable paramAnonymousThrowable, int paramAnonymousInt, String paramAnonymousString)
      {
        paramAnonymousThrowable = new ToastDefine(InviteFriendsActivity.this.getApplicationContext());
        paramAnonymousThrowable.setMessage(InviteFriendsActivity.this.getString(2131296392));
        paramAnonymousThrowable.show();
      }

      public void onResult(Object paramAnonymousObject, int paramAnonymousInt)
      {
        Object localObject = (Infos)paramAnonymousObject;
        if ((paramAnonymousObject != null) && (paramAnonymousInt == ResultCode.RESULT_OK))
        {
          paramAnonymousObject = ((Infos)localObject).getAdver_list();
          paramAnonymousInt = 0;
          if (paramAnonymousInt >= paramAnonymousObject.size())
          {
            paramAnonymousObject = InviteFriendsActivity.this;
            localObject = new ImagePagerAdapter(InviteFriendsActivity.this, InviteFriendsActivity.this.imageUrlList, InviteFriendsActivity.this.linkUrlArray, InviteFriendsActivity.this.adIdList);
            if (InviteFriendsActivity.this.imageUrlList.size() <= 1)
              break label304;
            bool = true;
            paramAnonymousObject.imgAdapter = ((ImagePagerAdapter)localObject).setInfiniteLoop(bool);
            InviteFriendsActivity.this.initAdBanner();
          }
        }
        label304: 
        while ((((Infos)localObject).getMsg().toString().trim().equals("")) || (((Infos)localObject).getMsg().toString().trim().equals("成功")))
          while (true)
          {
            return;
            Log.e("TAG", ((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getPicPath() + "---" + ((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getThirdLink());
            InviteFriendsActivity.this.adIdList.add(((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getId());
            InviteFriendsActivity.this.imageUrlList.add(StringUtils.getImgUrl(((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getPicPath()));
            if (!((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getThirdLink().equals(""))
              InviteFriendsActivity.this.linkUrlArray.add(((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getThirdLink());
            while (true)
            {
              paramAnonymousInt += 1;
              break;
              InviteFriendsActivity.this.linkUrlArray.add(((Advertise)paramAnonymousObject.get(paramAnonymousInt)).getDetail());
            }
            boolean bool = false;
          }
        paramAnonymousObject = new ToastDefine(InviteFriendsActivity.this.getApplicationContext());
        paramAnonymousObject.setMessage(((Infos)localObject).getMsg());
        paramAnonymousObject.show();
      }
    });
    localHomeAdvertiseRequest.startRequest();
  }

  private void getUserInviteHistory()
  {
    if ((loginUser == null) || (loginUser.getLoginType() == 6))
      return;
    this.isRunning = true;
    HashMap localHashMap = new HashMap();
    localHashMap.put("uid", loginUser.getId());
    int i = this.historyPage;
    this.historyPage = (i + 1);
    localHashMap.put("pages", String.valueOf(i));
    HttpRequest.getInstance().executePostStringRequest(this, Url.GET_USER_INVITE_HISTORY, HttpWhat.GET_USER_INVITE_HISTORY, localHashMap, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
        InviteFriendsActivity.this.toast.setMessage(InviteFriendsActivity.this.getString(2131296392));
        InviteFriendsActivity.this.isRunning = false;
        paramAnonymousString = InviteFriendsActivity.this;
        paramAnonymousString.historyPage -= 1;
        InviteFriendsActivity.this.pull_refresh_view.onFooterRefreshComplete();
        InviteFriendsActivity.this.pull_refresh_view.onHeaderRefreshComplete();
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        Log.i("Test", "获取用户邀请记录结果： " + ((String)paramAnonymousResponse.get()).toString());
        InviteFriendsActivity.this.parseJson(((String)paramAnonymousResponse.get()).toString());
        if (InviteFriendsActivity.this.historyAdapter == null)
        {
          InviteFriendsActivity.this.historyAdapter = new InviteFriendsActivity.InviteHistoryAdapter(InviteFriendsActivity.this, InviteFriendsActivity.this);
          InviteFriendsActivity.this.historyListView.setAdapter(InviteFriendsActivity.this.historyAdapter);
        }
        while (true)
        {
          InviteFriendsActivity.this.isRunning = false;
          InviteFriendsActivity.this.pull_refresh_view.onFooterRefreshComplete();
          InviteFriendsActivity.this.pull_refresh_view.onHeaderRefreshComplete();
          return;
          InviteFriendsActivity.this.historyAdapter.notifyDataSetChanged();
        }
      }
    });
  }

  private void initAdBanner()
  {
    this.mViewFlow = ((ViewFlow)findViewById(2131231084));
    this.mViewFlow.setAdapter(this.imgAdapter);
    this.mViewFlow.setmSideBuffer(this.imageUrlList.size());
    this.mViewFlow.setFlowIndicator(this.mFlowIndicator);
    if (this.imageUrlList.size() > 1)
    {
      this.mViewFlow.setmSideBuffer(this.imageUrlList.size());
      this.mViewFlow.setAdapter(this.imgAdapter);
      this.mViewFlow.setFlowIndicator(this.mFlowIndicator);
      this.mViewFlow.setTimeSpan(5000L);
      this.mViewFlow.setSelection(0);
      this.mViewFlow.startAutoFlowTimer();
      return;
    }
    this.mFlowIndicator.setVisibility(8);
  }

  private void initData()
  {
    this.timer = new Timer();
    this.timerTask = new TimerTask()
    {
      public void run()
      {
        if ((InviteFriendsActivity.this.temHeight >= InviteFriendsActivity.this.targetHeight) || (InviteFriendsActivity.this.isBreak))
          return;
        InviteFriendsActivity localInviteFriendsActivity = InviteFriendsActivity.this;
        localInviteFriendsActivity.temHeight += InviteFriendsActivity.this.incrementHeight;
        InviteFriendsActivity.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, InviteFriendsActivity.this.temHeight);
            localLayoutParams.addRule(12, -1);
            InviteFriendsActivity.this.imgArrow.setImageResource(2130837680);
            InviteFriendsActivity.this.layoutInviteHistory.setLayoutParams(localLayoutParams);
          }
        });
      }
    };
    this.detector = new GestureDetector(this, new GestureDetector.OnGestureListener()
    {
      public boolean onDown(MotionEvent paramAnonymousMotionEvent)
      {
        return false;
      }

      public boolean onFling(MotionEvent paramAnonymousMotionEvent1, MotionEvent paramAnonymousMotionEvent2, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        if (!InviteFriendsActivity.this.isUp)
          if (paramAnonymousMotionEvent1.getY() > paramAnonymousMotionEvent2.getY())
            InviteFriendsActivity.this.expandPane();
        while (true)
        {
          return true;
          if (paramAnonymousMotionEvent1.getY() < paramAnonymousMotionEvent2.getY())
            InviteFriendsActivity.this.collapsePane();
        }
      }

      public void onLongPress(MotionEvent paramAnonymousMotionEvent)
      {
      }

      public boolean onScroll(MotionEvent paramAnonymousMotionEvent1, MotionEvent paramAnonymousMotionEvent2, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        return false;
      }

      public void onShowPress(MotionEvent paramAnonymousMotionEvent)
      {
      }

      public boolean onSingleTapUp(MotionEvent paramAnonymousMotionEvent)
      {
        return false;
      }
    });
    this.shareGap = calculateGap();
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
    localLayoutParams.leftMargin = this.shareGap;
    this.btn_wechat_moments.setLayoutParams(localLayoutParams);
    this.btn_qq.setLayoutParams(localLayoutParams);
    this.btn_weibo.setLayoutParams(localLayoutParams);
    this.btn_qzone.setLayoutParams(localLayoutParams);
    localLayoutParams.rightMargin = DisplayUtil.dip2px(this, 10.0F);
    this.btn_send_msg.setLayoutParams(localLayoutParams);
  }

  private void initView()
  {
    initTitleBar();
    this.tv_title.setText("邀请好友");
    this.tv_left.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (InviteFriendsActivity.this.isUp)
        {
          InviteFriendsActivity.this.collapsePane();
          return;
        }
        if (InviteFriendsActivity.this.timer != null)
        {
          InviteFriendsActivity.this.timer.cancel();
          InviteFriendsActivity.this.timer = null;
          InviteFriendsActivity.this.timerTask = null;
        }
        InviteFriendsActivity.this.finish();
      }
    });
    this.tvCopy = ((TextView)findViewById(2131230792));
    this.tvCopy.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ((ClipboardManager)InviteFriendsActivity.this.getSystemService("clipboard")).setText(InviteFriendsActivity.loginUser.getInviteCode());
        InviteFriendsActivity.this.toast.setMessage("复制成功");
        InviteFriendsActivity.this.toast.show();
      }
    });
    if ((loginUser != null) && (loginUser.getInviteCode() != null))
    {
      this.tv_my_invite_code.setTextSize(2, 22.0F);
      this.tv_my_invite_code.setText(loginUser.getInviteCode());
    }
    while (true)
    {
      this.mFlowIndicator = ((CircleFlowIndicator)findViewById(2131231085));
      this.layoutInviteFriend = ((LinearLayout)findViewById(2131230793));
      this.layoutInviteFriend.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          try
          {
            MobclickAgent.onEvent(InviteFriendsActivity.this, "viewqr");
            InviteFriendsActivity.this.startActivity(new Intent(InviteFriendsActivity.this, TwoDimensionActivity.class));
            return;
          }
          catch (Exception paramAnonymousView)
          {
            while (true)
              paramAnonymousView.printStackTrace();
          }
        }
      });
      this.tvInviteBeans = ((TextView)findViewById(2131230807));
      this.layoutArrow = ((LinearLayout)findViewById(2131230810));
      this.layoutBeans = ((LinearLayout)findViewById(2131230806));
      this.layoutHistory = ((LinearLayout)findViewById(2131230805));
      this.layoutInviteHistory = ((RelativeLayout)findViewById(2131230804));
      this.layoutInviteHistory.setOnClickListener(this);
      this.layoutInviteHistory.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          return InviteFriendsActivity.this.detector.onTouchEvent(paramAnonymousMotionEvent);
        }
      });
      this.imgArrow = ((ImageView)findViewById(2131230811));
      this.toast = new ToastDefine(this);
      this.historyListView = ((ListView)findViewById(2131230809));
      this.pull_refresh_view = ((PullToRefreshView)findViewById(2131230808));
      this.pull_refresh_view.setOnFooterRefreshListener(this);
      this.pull_refresh_view.setOnHeaderRefreshListener(this);
      this.mScrollView = ((HorizontalScrollView)findViewById(2131230794));
      this.imgLeft = ((ImageView)findViewById(2131230795));
      this.imgRight = ((ImageView)findViewById(2131230802));
      this.imgLeft.setOnClickListener(this);
      this.imgRight.setOnClickListener(this);
      this.screenHeight = DisplayUtil.getScreenHeight(this);
      this.screenWidth = DisplayUtil.getScreenWidth(this);
      this.upViewBg = findViewById(2131230803);
      this.btn_wechat_moments = ((LinearLayout)findViewById(2131230797));
      this.btn_qq = ((LinearLayout)findViewById(2131230798));
      this.btn_weibo = ((LinearLayout)findViewById(2131230799));
      this.btn_qzone = ((LinearLayout)findViewById(2131230800));
      this.btn_send_msg = ((LinearLayout)findViewById(2131230801));
      return;
      this.tv_my_invite_code.setTextSize(2, 14.0F);
      this.tv_my_invite_code.setHint("登录获取邀请码");
      this.tvCopy.setVisibility(8);
    }
  }

  private void parseJson(Object paramObject)
  {
    if (paramObject == null);
    JSONObject localJSONObject;
    while (true)
    {
      return;
      paramObject = String.valueOf(paramObject);
      if ((paramObject != null) && (!"".equals(paramObject.trim())))
        try
        {
          paramObject = new JSONObject(paramObject);
          if (paramObject.getInt("status") == 10001)
          {
            localJSONObject = paramObject.getJSONObject("data");
            paramObject = localJSONObject.getJSONArray("history");
            if (paramObject.length() == 0)
            {
              this.historyPage -= 1;
              this.pull_refresh_view.onFooterRefreshComplete();
              this.pull_refresh_view.onHeaderRefreshComplete();
              return;
            }
          }
        }
        catch (JSONException paramObject)
        {
          Log.i("Exception", "解析用户邀请豆数异常： " + paramObject.getMessage().toString());
          paramObject.printStackTrace();
          return;
        }
    }
    int i = localJSONObject.getInt("totaldouqty");
    this.tvInviteBeans.setText(String.valueOf(i) + "豆");
    i = 0;
    while (i < paramObject.length())
    {
      localJSONObject = paramObject.getJSONObject(i);
      if (this.list == null)
        this.list = new ArrayList();
      InviteHistoryInfo localInviteHistoryInfo = new InviteHistoryInfo();
      localInviteHistoryInfo.setUserId(localJSONObject.getLong("userid"));
      try
      {
        new URLDecoder();
        localInviteHistoryInfo.setNickName(URLDecoder.decode(localJSONObject.optString("nickname")));
        localInviteHistoryInfo.setLogoPath(localJSONObject.getString("logopath"));
        localInviteHistoryInfo.setDays(localJSONObject.getString("days"));
        localInviteHistoryInfo.setStatus(localJSONObject.getString("status"));
        localInviteHistoryInfo.setInviteUserCount(localJSONObject.optInt("InviteeInviteUserCount"));
        localInviteHistoryInfo.setInviteBeans(localJSONObject.optInt("InviteeInviteDouQty"));
        this.list.add(localInviteHistoryInfo);
        i += 1;
      }
      catch (Exception localException)
      {
        while (true)
        {
          localInviteHistoryInfo.setNickName(localJSONObject.optString("nickname"));
          localException.printStackTrace();
        }
      }
    }
  }

  public void doSendSMSTo(String paramString1, String paramString2)
  {
    paramString1 = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + paramString1));
    paramString1.putExtra("sms_body", paramString2);
    startActivity(paramString1);
  }

  public void onClick(View paramView)
  {
    if (loginUser == null)
      return;
    String str2 = getString(2131296276);
    ShareUtils localShareUtils = new ShareUtils(paramView.getContext());
    if ((loginUser != null) && (loginUser.getInviteCode() != null));
    for (String str1 = loginUser.getInviteCode(); ; str1 = "")
      switch (paramView.getId())
      {
      case 2131230803:
      case 2131230805:
      case 2131230807:
      case 2131230808:
      case 2131230809:
      default:
        return;
      case 2131230795:
        this.mScrollView.pageScroll(66);
        return;
      case 2131230796:
      case 2131230797:
      case 2131230799:
      case 2131230800:
      case 2131230798:
      case 2131230801:
      case 2131230804:
      case 2131230806:
      case 2131230810:
      case 2131230802:
      }
    String str3;
    if (loginUser != null)
    {
      paramView = loginUser.getNickname();
      str3 = paramView + "请你帮帮忙，免费兑好礼";
      if (loginUser.getLoginType() == 6)
        break label288;
    }
    label288: for (paramView = "亲，使用我的邀请码" + str1 + "，领取500精明豆，可以免费换好礼，同时也帮我赚点豆。"; ; paramView = "亲，领取500精明豆，可以免费换好礼。")
    {
      localShareUtils.setPlatform("Wechat");
      localShareUtils.initShare(str3, paramView, "http://firicon.fir.im/1e7646900a9911e59743aa364a84ae5247bfb4e8?e=1433416046…oken=KMHm2Srw8ucAeUwTrkfXSgx35GMiSYWo5N4QCy-B:xokNKaRgHILFMXQVspVn4OxEaDM=", str2, null);
      localShareUtils.startShareToInvite();
      try
      {
        paramView = new HashMap();
        paramView.put("channel", "微信");
        MobclickAgent.onEvent(this, "shareinvite", paramView);
        return;
      }
      catch (Exception paramView)
      {
        paramView.printStackTrace();
        return;
      }
      paramView = "";
      break;
    }
    if (loginUser != null)
    {
      paramView = loginUser.getNickname();
      str3 = paramView + "请你帮帮忙，免费兑好礼";
      if (loginUser.getLoginType() == 6)
        break label432;
    }
    label432: for (paramView = "亲，使用我的邀请码" + str1 + "，领取500精明豆，可以免费换好礼，同时也帮我赚点豆。"; ; paramView = "亲，领取500精明豆，可以免费换好礼。")
    {
      localShareUtils.setPlatform("WechatMoments");
      localShareUtils.initShare(str3, paramView, "http://firicon.fir.im/1e7646900a9911e59743aa364a84ae5247bfb4e8?e=1433416046…oken=KMHm2Srw8ucAeUwTrkfXSgx35GMiSYWo5N4QCy-B:xokNKaRgHILFMXQVspVn4OxEaDM=", str2, null);
      localShareUtils.startShareToInvite();
      try
      {
        paramView = new HashMap();
        paramView.put("channel", "微信好友圈");
        MobclickAgent.onEvent(this, "shareinvite", paramView);
        return;
      }
      catch (Exception paramView)
      {
        paramView.printStackTrace();
        return;
      }
      paramView = "";
      break;
    }
    if (loginUser.getLoginType() != 6);
    for (paramView = "亲，使用我的邀请码" + str1 + "，领取500精明豆，可以免费换好礼，同时也帮我赚点豆。" + str2; ; paramView = "亲，领取500精明豆，可以免费换好礼。" + str2)
    {
      localShareUtils.setPlatform("SinaWeibo");
      localShareUtils.initShare("", paramView, "http://firicon.fir.im/1e7646900a9911e59743aa364a84ae5247bfb4e8?e=1433416046…oken=KMHm2Srw8ucAeUwTrkfXSgx35GMiSYWo5N4QCy-B:xokNKaRgHILFMXQVspVn4OxEaDM=", str2, null);
      localShareUtils.startShareToInvite();
      try
      {
        paramView = new HashMap();
        paramView.put("channel", "微博");
        MobclickAgent.onEvent(this, "shareinvite", paramView);
        return;
      }
      catch (Exception paramView)
      {
        paramView.printStackTrace();
        return;
      }
    }
    if (loginUser.getLoginType() != 6);
    for (paramView = "送你500精明豆，同时帮我赚点豆"; ; paramView = "送你500精明豆")
    {
      localShareUtils.setPlatform("QZone");
      localShareUtils.initShare("", paramView, "http://firicon.fir.im/1e7646900a9911e59743aa364a84ae5247bfb4e8?e=1433416046…oken=KMHm2Srw8ucAeUwTrkfXSgx35GMiSYWo5N4QCy-B:xokNKaRgHILFMXQVspVn4OxEaDM=", str2, null);
      localShareUtils.startShareToInvite();
      try
      {
        paramView = new HashMap();
        paramView.put("channel", "QQ空间");
        MobclickAgent.onEvent(this, "shareinvite", paramView);
        return;
      }
      catch (Exception paramView)
      {
        paramView.printStackTrace();
        return;
      }
    }
    if (loginUser.getLoginType() != 6);
    for (paramView = "送你500精明豆，同时帮我赚点豆。\n亲，邀请码" + str1 + "，" + str2; ; paramView = "送你500精明豆。" + str2)
    {
      localShareUtils.setPlatform("QQ");
      localShareUtils.initShare("", paramView, "http://firicon.fir.im/1e7646900a9911e59743aa364a84ae5247bfb4e8?e=1433416046…oken=KMHm2Srw8ucAeUwTrkfXSgx35GMiSYWo5N4QCy-B:xokNKaRgHILFMXQVspVn4OxEaDM=", str2, null);
      localShareUtils.startShareToInvite();
      try
      {
        paramView = new HashMap();
        paramView.put("channel", "QQ");
        MobclickAgent.onEvent(this, "shareinvite", paramView);
        return;
      }
      catch (Exception paramView)
      {
        paramView.printStackTrace();
        return;
      }
    }
    if (loginUser.getLoginType() != 6);
    for (paramView = "亲，使用我的邀请码" + str1 + "，领取500精明豆，可以免费换好礼，同时也帮我赚点豆。" + str2; ; paramView = "亲，领取500精明豆，可以免费换好礼。" + str2)
    {
      doSendSMSTo("", paramView);
      try
      {
        paramView = new HashMap();
        paramView.put("channel", "短信");
        MobclickAgent.onEvent(this, "shareinvite", paramView);
        return;
      }
      catch (Exception paramView)
      {
        paramView.printStackTrace();
        return;
      }
    }
    if (!this.isUp)
    {
      expandPane();
      return;
    }
    collapsePane();
    return;
    this.mScrollView.pageScroll(17);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903043);
    initView();
    getAdvertiseImg();
    getUserInviteHistory();
    initData();
  }

  public void onFooterRefresh(PullToRefreshView paramPullToRefreshView)
  {
    if (!this.isRunning)
    {
      getUserInviteHistory();
      return;
    }
    this.pull_refresh_view.onFooterRefreshComplete();
    this.pull_refresh_view.onHeaderRefreshComplete();
  }

  public void onHeaderRefresh(PullToRefreshView paramPullToRefreshView)
  {
    this.pull_refresh_view.onFooterRefreshComplete();
    this.pull_refresh_view.onHeaderRefreshComplete();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (paramKeyEvent.getAction() == 0))
    {
      if (this.isUp)
      {
        collapsePane();
        return true;
      }
      if (this.timer != null)
      {
        this.timer.cancel();
        this.timer = null;
        this.timerTask = null;
      }
    }
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
    MobclickAgent.onResume(this);
  }

  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    if ((paramBoolean) && (!this.isUp))
    {
      this.panelHeight = this.layoutInviteHistory.getHeight();
      this.temHeight = this.panelHeight;
      this.targetHeight = (this.screenHeight / 3 * 2);
      this.gapHeight = (this.targetHeight - this.panelHeight);
      this.incrementHeight = ((int)(this.gapHeight / (this.time / this.updateUiTime)));
    }
  }

  class InviteHistoryAdapter extends BaseAdapter
  {
    private LayoutInflater inflater;

    public InviteHistoryAdapter(Context arg2)
    {
      Context localContext;
      this.inflater = LayoutInflater.from(localContext);
    }

    public int getCount()
    {
      if (InviteFriendsActivity.this.list == null)
        return 0;
      return InviteFriendsActivity.this.list.size();
    }

    public Object getItem(int paramInt)
    {
      return InviteFriendsActivity.this.list.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return ((InviteHistoryInfo)InviteFriendsActivity.this.list.get(paramInt)).hashCode();
    }

    @SuppressLint({"InflateParams"})
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        paramViewGroup = new ViewHolder();
        paramView = this.inflater.inflate(2130903139, null);
        paramViewGroup.imgLogo = ((ImageView)paramView.findViewById(2131231163));
        paramViewGroup.tvNickName = ((TextView)paramView.findViewById(2131231159));
        paramViewGroup.tvDays = ((TextView)paramView.findViewById(2131231160));
        paramViewGroup.tvStatus = ((TextView)paramView.findViewById(2131231161));
        paramViewGroup.tvInvitePerson = ((TextView)paramView.findViewById(2131231162));
        paramViewGroup.tvInviteBeans = ((TextView)paramView.findViewById(2131230807));
        paramView.setTag(paramViewGroup);
      }
      while (true)
      {
        InviteHistoryInfo localInviteHistoryInfo = (InviteHistoryInfo)InviteFriendsActivity.this.list.get(paramInt);
        ImgLoader.getInstance(InviteFriendsActivity.this).showPic(localInviteHistoryInfo.getLogoPath(), paramViewGroup.imgLogo, false);
        paramViewGroup.tvDays.setText(localInviteHistoryInfo.getDays());
        paramViewGroup.tvNickName.setText(localInviteHistoryInfo.getNickName());
        paramViewGroup.tvStatus.setText(localInviteHistoryInfo.getStatus());
        paramViewGroup.tvInvitePerson.setText(String.valueOf(localInviteHistoryInfo.getInviteUserCount()));
        paramViewGroup.tvInviteBeans.setText(String.valueOf(localInviteHistoryInfo.getInviteBeans()));
        return paramView;
        paramViewGroup = (ViewHolder)paramView.getTag();
      }
    }

    class ViewHolder
    {
      ImageView imgLogo;
      TextView tvDays;
      TextView tvInviteBeans;
      TextView tvInvitePerson;
      TextView tvNickName;
      TextView tvStatus;

      ViewHolder()
      {
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.InviteFriendsActivity
 * JD-Core Version:    0.6.2
 */