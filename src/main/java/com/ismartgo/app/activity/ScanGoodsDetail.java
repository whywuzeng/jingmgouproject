package com.ismartgo.app.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ab.view.ioc.AbIocView;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.tools.SharedPreferenceUtil;
import com.umeng.analytics.MobclickAgent;
import java.util.Timer;
import java.util.TimerTask;

@SuppressLint({"SetJavaScriptEnabled"})
public class ScanGoodsDetail extends BaseActivity
{
  private int beanNumber;

  @AbIocView(id=2131230942)
  private LinearLayout bt_select;

  @AbIocView(id=2131231361)
  public TextView count_down_time;
  private int flag = 0;

  @AbIocView(id=2131230955)
  private WebView goods_detail;
  Handler handler;
  Handler handler2 = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage.what == 17)
      {
        ScanGoodsDetail.this.pv_back.setEnabled(true);
        ScanGoodsDetail.this.popupWindow.dismiss();
      }
    }
  };
  private LinearLayout llTitle;
  private MyDialog mDialog;
  PopupWindow popupWindow;

  @AbIocView(id=2131231000)
  private TextView pv_back;

  @AbIocView(id=2131231002)
  private ImageView pv_screening;
  private String qrcode;
  private RelativeLayout rlScan;

  @AbIocView(id=2131231360)
  private RelativeLayout scan_layout;
  private int shopId;
  private String theUrl;
  TimeCount timeCount = null;
  int timeTotal = 10;
  private int userBeanNum;

  private void dismissDialog()
  {
    this.mDialog.dismiss();
  }

  private void getBeansNumber()
  {
    loginUser.setBeanNumber(this.userBeanNum);
    Object localObject = LayoutInflater.from(this).inflate(2130903118, null);
    ((TextView)((View)localObject).findViewById(2131231043)).setText(String.valueOf(this.beanNumber));
    if (loginUser.getLoginType() == 6)
      SharedPreferenceUtil.setTouristBeanNumber(this, SharedPreferenceUtil.getTouristBeanNumber(this) + this.beanNumber);
    this.popupWindow = new PopupWindow((View)localObject, -1, -2);
    this.popupWindow.setTouchable(true);
    this.popupWindow.setOutsideTouchable(true);
    this.popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), null));
    this.popupWindow.setAnimationStyle(2131427346);
    this.popupWindow.showAtLocation(this.scan_layout, 17, 0, 0);
    if (SharedPreferenceUtil.getVoiceSwitch(this) == 1)
      startSuccessPlayer();
    localObject = new TimerTask()
    {
      public void run()
      {
        Message localMessage = new Message();
        localMessage.what = 17;
        ScanGoodsDetail.this.handler2.sendMessage(localMessage);
      }
    };
    new Timer().schedule((TimerTask)localObject, 3000L);
  }

  private void getExtrasValue()
  {
    this.beanNumber = getIntent().getIntExtra("beannumber", 0);
    this.userBeanNum = getIntent().getIntExtra("userBeanNum", 0);
    this.theUrl = getIntent().getStringExtra("url");
  }

  private void getGoodsAdRequest()
  {
    this.goods_detail.loadUrl(addUserIdUrl(this.theUrl));
    this.goods_detail.getSettings().setJavaScriptEnabled(true);
    this.goods_detail.setWebViewClient(new WebViewClient()
    {
      public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        paramAnonymousWebView.loadUrl(paramAnonymousString);
        return false;
      }
    });
    this.pv_back.setEnabled(false);
    this.timeCount = new TimeCount(this.timeTotal * 1000, 1000L);
    this.timeCount.start();
  }

  private void initView()
  {
    initTitleBar();
    this.tv_title.setText("商品详情");
    this.pv_screening.setVisibility(4);
    this.llTitle = ((LinearLayout)findViewById(2131230952));
    this.llTitle.setBackgroundColor(Color.parseColor("#ffffff"));
    Drawable localDrawable = getResources().getDrawable(2130837803);
    localDrawable.setBounds(0, 0, localDrawable.getMinimumWidth(), localDrawable.getMinimumHeight());
    this.pv_back.setCompoundDrawables(localDrawable, null, null, null);
    this.pv_back.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ScanGoodsDetail.this.finish();
      }
    });
  }

  private void startSuccessPlayer()
  {
    MediaPlayer localMediaPlayer = MediaPlayer.create(this, 2131034116);
    localMediaPlayer.setLooping(false);
    localMediaPlayer.start();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903205);
    this.mDialog = new MyDialog(this);
    getExtrasValue();
    initView();
    getGoodsAdRequest();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (!this.pv_back.isEnabled()))
      return false;
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  protected void onPause()
  {
    super.onPause();
    if (this.timeCount != null)
    {
      this.timeCount.cancel();
      this.timeCount = null;
    }
    MobclickAgent.onPause(this);
  }

  protected void onResume()
  {
    super.onPause();
    if (this.flag == 1)
    {
      this.timeCount = new TimeCount(this.timeTotal * 1000, 1000L);
      this.timeCount.start();
    }
    MobclickAgent.onResume(this);
  }

  class TimeCount extends CountDownTimer
  {
    public TimeCount(long arg2, long arg4)
    {
      super(localObject);
    }

    public void onFinish()
    {
      ScanGoodsDetail.this.flag = 0;
      ScanGoodsDetail.this.bt_select.setVisibility(8);
      ScanGoodsDetail.this.getBeansNumber();
    }

    public void onTick(long paramLong)
    {
      if (ScanGoodsDetail.this.flag == 0)
        ScanGoodsDetail.this.flag = 1;
      ScanGoodsDetail.this.count_down_time.setText(paramLong / 1000L);
      ScanGoodsDetail.this.timeTotal = ((int)(paramLong / 1000L));
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.ScanGoodsDetail
 * JD-Core Version:    0.6.2
 */