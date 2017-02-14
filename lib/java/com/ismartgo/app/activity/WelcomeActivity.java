package com.ismartgo.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import cn.jpush.android.api.JPushInterface;
import com.ismartgo.app.application.AndroidApplication;
import com.ismartgo.app.tools.SharedPreferenceUtil;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;
import java.io.PrintStream;

public class WelcomeActivity extends Activity
{
  public String TAG = "JPush";
  public Animation.AnimationListener animationListener = new Animation.AnimationListener()
  {
    public void onAnimationEnd(Animation paramAnonymousAnimation)
    {
      WelcomeActivity.this.startMainActivity();
    }

    public void onAnimationRepeat(Animation paramAnonymousAnimation)
    {
    }

    public void onAnimationStart(Animation paramAnonymousAnimation)
    {
    }
  };
  private ImageView imgv;
  private int isCommingGuide = 0;

  private void initData()
  {
    System.out.println("请求用户数据");
    AndroidApplication.getInstance().setApplicationUser();
    AndroidApplication.getInstance().openBle();
    AndroidApplication.getInstance().startIbeaconService();
  }

  private void initView()
  {
    this.imgv = ((ImageView)findViewById(2131231082));
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(255.0F, 255.0F);
    localAlphaAnimation.setDuration(1500L);
    this.imgv.startAnimation(localAlphaAnimation);
    localAlphaAnimation.setAnimationListener(this.animationListener);
  }

  private void startMainActivity()
  {
    Intent localIntent = new Intent();
    this.isCommingGuide = SharedPreferenceUtil.getGuideCount(this);
    if (this.isCommingGuide <= 0)
      localIntent.setClass(this, GuideActivity.class);
    while (true)
    {
      startActivity(localIntent);
      finish();
      return;
      localIntent.setClass(this, Tab_Container_Activity.class);
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903102);
    initView();
    initData();
    com.ismartgo.app.jpush.JPushReceiver.isAppRunning = true;
    MobclickAgent.updateOnlineConfig(this);
    AnalyticsConfig.enableEncrypt(true);
  }

  protected void onPause()
  {
    super.onPause();
    MobclickAgent.onPause(this);
    JPushInterface.onPause(this);
  }

  protected void onResume()
  {
    super.onResume();
    MobclickAgent.onResume(this);
    JPushInterface.onResume(this);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.WelcomeActivity
 * JD-Core Version:    0.6.2
 */