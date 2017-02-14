package com.ismartgo.app.activity;

import android.app.Application;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ab.view.ioc.AbIocView;
import com.ismartgo.app.application.AndroidApplication;
import com.ismartgo.app.bean.Infos;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.interfaces.OnCompleteListener;
import com.ismartgo.app.listener.ShakeListener;
import com.ismartgo.app.listener.ShakeListener.OnShakeListener;
import com.ismartgo.app.net.EarnSignBeansRequest;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.tools.SharedPreferenceUtil;
import com.ismartgo.app.url.ResultCode;
import com.ismartgo.app.url.Url;
import com.umeng.analytics.MobclickAgent;
import java.util.Timer;
import java.util.TimerTask;

public class ShakeSignActivity extends BaseActivity
{
  AndroidApplication application;
  private int beanNumber;
  private TextView get_beannumber;

  @AbIocView(id=2131231058)
  private TextView in_shop_txt;
  private boolean isInShop;

  @AbIocView(id=2131231057)
  private ImageView isnt_in_img;

  @AbIocView(id=2131231056)
  private TextView isnt_inshop;
  private MyDialog mDialog;

  @AbIocView(id=2131231054)
  private RelativeLayout mImgDn;

  @AbIocView(id=2131231051)
  private RelativeLayout mImgUp;
  ShakeListener mShakeListener = null;
  Vibrator mVibrator;

  @AbIocView(id=2131231000)
  private TextView pv_back;

  @AbIocView(id=2131231002)
  private ImageView pv_screening;

  @AbIocView(id=2131231046)
  RelativeLayout shake_layout;

  @AbIocView(id=2131231055)
  private ImageView share_img_bottom;

  @AbIocView(id=2131231053)
  private ImageView share_img_top;
  private int shopId;
  private String shopName;

  @AbIocView(id=2131231052)
  private TextView sign_tips;
  User user;

  private void getExtrasValue()
  {
    this.isInShop = getIntent().getBooleanExtra("isInShop", false);
    this.shopId = getIntent().getIntExtra("shopId", 0);
    this.shopName = getIntent().getStringExtra("shopName");
    this.beanNumber = getIntent().getIntExtra("beanNumber", 0);
  }

  private void initView()
  {
    this.application = ((AndroidApplication)getApplication());
    this.user = this.application.getUser();
    initTitleBar();
    this.tv_title.setText("到店签到");
    this.pv_screening.setVisibility(4);
    this.pv_back.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ShakeSignActivity.this.finish();
      }
    });
    if (this.user == null)
    {
      this.share_img_top.setImageResource(2130837807);
      this.share_img_bottom.setImageResource(2130837806);
      this.sign_tips.setVisibility(8);
      this.isnt_in_img.setVisibility(0);
      this.in_shop_txt.setText(2131296390);
      return;
    }
    if (this.isInShop)
    {
      this.sign_tips.setText("摇一摇签到，马上赚取" + this.beanNumber + "精明豆");
      this.share_img_top.setImageResource(2130837750);
      this.share_img_bottom.setImageResource(2130837749);
      this.sign_tips.setVisibility(0);
      this.isnt_in_img.setVisibility(8);
      this.in_shop_txt.setText(this.shopName);
      this.in_shop_txt.setClickable(true);
      this.in_shop_txt.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = new Intent(ShakeSignActivity.this, ScanInStoreGoodsActivity.class);
          paramAnonymousView.putExtra("shopId", ShakeSignActivity.this.shopId);
          paramAnonymousView.putExtra("shopName", ShakeSignActivity.this.shopName);
          ShakeSignActivity.this.startActivity(paramAnonymousView);
        }
      });
      this.mShakeListener = new ShakeListener(this);
      this.mShakeListener.setOnShakeListener(new ShakeListener.OnShakeListener()
      {
        public void onShake()
        {
          ShakeSignActivity.this.startAnim();
          ShakeSignActivity.this.mShakeListener.stop();
          ShakeSignActivity.this.startVibrato();
          new Handler().postDelayed(new Runnable()
          {
            public void run()
            {
              ShakeSignActivity.this.signEarnBeansRequest();
              ShakeSignActivity.this.mVibrator.cancel();
            }
          }
          , 2000L);
        }
      });
      return;
    }
    this.share_img_top.setImageResource(2130837807);
    this.share_img_bottom.setImageResource(2130837806);
    this.sign_tips.setVisibility(8);
    this.isnt_in_img.setVisibility(0);
    this.in_shop_txt.setText(2131296370);
  }

  private void signEarnBeansRequest()
  {
    this.mDialog.show();
    EarnSignBeansRequest localEarnSignBeansRequest = new EarnSignBeansRequest(this, Url.EARNSIGNBEANS_URL);
    localEarnSignBeansRequest.initParams(Integer.valueOf(this.user.getId()).intValue(), this.shopId);
    localEarnSignBeansRequest.setOnCompleteListener(new OnCompleteListener()
    {
      public void onFailure(Throwable paramAnonymousThrowable, int paramAnonymousInt, String paramAnonymousString)
      {
        ShakeSignActivity.this.toast.setMessage(ShakeSignActivity.this.getString(2131296392));
        ShakeSignActivity.this.toast.show();
        ShakeSignActivity.this.mShakeListener.start();
      }

      public void onResult(Object paramAnonymousObject, int paramAnonymousInt)
      {
        PromotionActivity.isClickInto = true;
        Infos localInfos = (Infos)paramAnonymousObject;
        if ((paramAnonymousObject != null) && (paramAnonymousInt == ResultCode.RESULT_OK))
        {
          ShakeSignActivity.this.mDialog.dismiss();
          ShakeSignActivity.this.user.setBeanNumber(ShakeSignActivity.this.beanNumber + ShakeSignActivity.this.user.getBeanNumber());
          ShakeSignActivity.this.sign_tips.setText(2131296371);
          ShakeSignActivity.this.share_img_top.setImageResource(2130837807);
          ShakeSignActivity.this.share_img_bottom.setImageResource(2130837806);
          paramAnonymousObject = LayoutInflater.from(ShakeSignActivity.this).inflate(2130903118, null);
          ShakeSignActivity.this.get_beannumber = ((TextView)paramAnonymousObject.findViewById(2131231043));
          ShakeSignActivity.this.get_beannumber.setText(String.valueOf(ShakeSignActivity.this.beanNumber));
          if (ShakeSignActivity.this.user.getLoginType() == 6)
            SharedPreferenceUtil.setTouristBeanNumber(ShakeSignActivity.this, SharedPreferenceUtil.getTouristBeanNumber(ShakeSignActivity.this) + ShakeSignActivity.this.beanNumber);
          paramAnonymousObject = new PopupWindow(paramAnonymousObject, -1, -2);
          paramAnonymousObject.setTouchable(true);
          paramAnonymousObject.setOutsideTouchable(true);
          paramAnonymousObject.setBackgroundDrawable(new BitmapDrawable(ShakeSignActivity.this.getResources(), null));
          paramAnonymousObject.setAnimationStyle(2131427346);
          paramAnonymousObject.showAtLocation(ShakeSignActivity.this.shake_layout, 17, 0, 0);
          if (SharedPreferenceUtil.getVoiceSwitch(ShakeSignActivity.this) == 1)
            ShakeSignActivity.this.startSuccessPlayer();
          paramAnonymousObject = new TimerTask()
          {
            public void run()
            {
              ShakeSignActivity.this.finish();
              Intent localIntent = new Intent(ShakeSignActivity.this, ScanInStoreGoodsActivity.class);
              localIntent.putExtra("shopId", ShakeSignActivity.this.shopId);
              localIntent.putExtra("shopName", ShakeSignActivity.this.shopName);
              ShakeSignActivity.this.startActivity(localIntent);
            }
          };
          new Timer().schedule(paramAnonymousObject, 2000L);
          return;
        }
        ShakeSignActivity.this.mDialog.dismiss();
        ShakeSignActivity.this.toast.setMessage(localInfos.getMsg());
        ShakeSignActivity.this.toast.show();
        ShakeSignActivity.this.mShakeListener.start();
      }
    });
    localEarnSignBeansRequest.startRequest();
  }

  private void startSuccessPlayer()
  {
    MediaPlayer localMediaPlayer = MediaPlayer.create(this, 2131034116);
    localMediaPlayer.setLooping(false);
    localMediaPlayer.start();
  }

  public void linshi(View paramView)
  {
    startAnim();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903093);
    this.mDialog = new MyDialog(this);
    getExtrasValue();
    initView();
    this.mVibrator = ((Vibrator)getApplication().getSystemService("vibrator"));
  }

  protected void onDestroy()
  {
    super.onDestroy();
    if (this.mShakeListener != null)
      this.mShakeListener.stop();
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

  public void startAnim()
  {
    AnimationSet localAnimationSet = new AnimationSet(true);
    TranslateAnimation localTranslateAnimation1 = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, 0.0F, 1, -0.5F);
    localTranslateAnimation1.setDuration(1000L);
    TranslateAnimation localTranslateAnimation2 = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, 0.0F, 1, 0.5F);
    localTranslateAnimation2.setDuration(1000L);
    localTranslateAnimation2.setStartOffset(1000L);
    localAnimationSet.addAnimation(localTranslateAnimation1);
    localAnimationSet.addAnimation(localTranslateAnimation2);
    this.mImgUp.startAnimation(localAnimationSet);
    localAnimationSet = new AnimationSet(true);
    localTranslateAnimation1 = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, 0.0F, 1, 0.5F);
    localTranslateAnimation1.setDuration(1000L);
    localTranslateAnimation2 = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, 0.0F, 1, -0.5F);
    localTranslateAnimation2.setDuration(1000L);
    localTranslateAnimation2.setStartOffset(1000L);
    localAnimationSet.addAnimation(localTranslateAnimation1);
    localAnimationSet.addAnimation(localTranslateAnimation2);
    this.mImgDn.startAnimation(localAnimationSet);
  }

  public void startVibrato()
  {
    MediaPlayer localMediaPlayer = MediaPlayer.create(this, 2131034117);
    localMediaPlayer.setLooping(false);
    localMediaPlayer.start();
    this.mVibrator.vibrate(new long[] { 500L, 200L, 500L, 200L }, -1);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.ShakeSignActivity
 * JD-Core Version:    0.6.2
 */