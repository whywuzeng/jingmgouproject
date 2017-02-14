package com.ismartgo.app.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.ab.view.ioc.AbIocView;
import com.ismartgo.app.application.AndroidApplication;
import com.ismartgo.app.bean.Infos;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.interfaces.OnCompleteListener;
import com.ismartgo.app.net.EarnSignBeansRequest;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.tools.SharedPreferenceUtil;
import com.ismartgo.app.url.ResultCode;
import com.ismartgo.app.url.Url;
import com.umeng.analytics.MobclickAgent;

public class SignInActivity extends BaseActivity
{
  private static final int SENSOR_SHAKE = 10;
  public static String TAG = "SignInActivity";
  private AndroidApplication application;
  private int beanNumber;
  Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      super.handleMessage(paramAnonymousMessage);
      switch (paramAnonymousMessage.what)
      {
      default:
        return;
      case 10:
      }
      paramAnonymousMessage = AnimationUtils.loadAnimation(SignInActivity.this, 2130968593);
      SignInActivity.this.shake_img.startAnimation(paramAnonymousMessage);
      SignInActivity.this.vibrator.vibrate(200L);
      if (SharedPreferenceUtil.getVoiceSwitch(SignInActivity.this) == 1)
        SignInActivity.this.mediaPlayer1.start();
      try
      {
        Thread.sleep(2000L);
        if (SharedPreferenceUtil.getVoiceSwitch(SignInActivity.this) == 1)
          SignInActivity.this.mediaPlayer2.start();
        paramAnonymousMessage = new PopupWindow(LayoutInflater.from(SignInActivity.this).inflate(2130903118, null), -1, -2);
        paramAnonymousMessage.setTouchable(true);
        paramAnonymousMessage.setOutsideTouchable(true);
        paramAnonymousMessage.setBackgroundDrawable(new BitmapDrawable(SignInActivity.this.getResources(), null));
        paramAnonymousMessage.setAnimationStyle(2131427346);
        paramAnonymousMessage.showAtLocation(SignInActivity.this.shake_layout, 17, 0, 0);
        return;
      }
      catch (InterruptedException paramAnonymousMessage)
      {
        while (true)
          paramAnonymousMessage.printStackTrace();
      }
    }
  };
  private MediaPlayer mediaPlayer1;
  private MediaPlayer mediaPlayer2;

  @AbIocView(click="signInClick", id=2131231000)
  private TextView pv_back;

  @AbIocView(id=2131231002)
  private ImageView pv_screening;

  @AbIocView(id=2131231105)
  private LinearLayout screen_lt;
  SensorEventListener sensorEventListener = new SensorEventListener()
  {
    public void onAccuracyChanged(Sensor paramAnonymousSensor, int paramAnonymousInt)
    {
    }

    public void onSensorChanged(SensorEvent paramAnonymousSensorEvent)
    {
      int i = paramAnonymousSensorEvent.sensor.getType();
      paramAnonymousSensorEvent = paramAnonymousSensorEvent.values;
      float f1 = paramAnonymousSensorEvent[0];
      float f2 = paramAnonymousSensorEvent[1];
      float f3 = paramAnonymousSensorEvent[2];
      Log.i(SignInActivity.TAG, "x轴方向的重力加速度" + f1 + "；y轴方向的重力加速度" + f2 + "；z轴方向的重力加速度" + f3);
      if ((i == 1) && (((Math.abs(f1) > 14) && (Math.abs(f2) > 14)) || ((Math.abs(f2) > 14) && (Math.abs(f3) > 14)) || ((Math.abs(f1) > 14) && (Math.abs(f3) > 14))))
      {
        paramAnonymousSensorEvent = new Message();
        paramAnonymousSensorEvent.what = 10;
        SignInActivity.this.handler.sendMessage(paramAnonymousSensorEvent);
      }
    }
  };
  private SensorManager sensorManager;

  @AbIocView(id=2131231048)
  private ImageView shake_img;

  @AbIocView(id=2131231046)
  private LinearLayout shake_layout;

  @AbIocView(click="signInClick", id=2131231047)
  private LinearLayout shake_no;

  @AbIocView(click="signInClick", id=2131231049)
  private LinearLayout shake_ok;
  private int shopId;
  private User user;
  private Vibrator vibrator;

  private void initExtraValue()
  {
    this.shopId = getIntent().getExtras().getInt("shopId");
    this.beanNumber = getIntent().getExtras().getInt("beanNumber");
  }

  private void initView()
  {
    this.application = ((AndroidApplication)getApplication());
    this.user = this.application.getUser();
    initTitleBar();
    this.tv_title.setText("到店签到");
    this.pv_screening.setVisibility(4);
    this.sensorManager = ((SensorManager)getSystemService("sensor"));
    this.vibrator = ((Vibrator)getSystemService("vibrator"));
    this.mediaPlayer1 = MediaPlayer.create(this, 2131034117);
    this.mediaPlayer2 = MediaPlayer.create(this, 2131034116);
  }

  private void signEarnBeansRequest()
  {
    EarnSignBeansRequest localEarnSignBeansRequest = new EarnSignBeansRequest(this, Url.EARNSIGNBEANS_URL);
    localEarnSignBeansRequest.initParams(Integer.valueOf(this.user.getId()).intValue(), this.shopId);
    localEarnSignBeansRequest.setOnCompleteListener(new OnCompleteListener()
    {
      public void onFailure(Throwable paramAnonymousThrowable, int paramAnonymousInt, String paramAnonymousString)
      {
        SignInActivity.this.toast.setMessage(SignInActivity.this.getString(2131296392));
        SignInActivity.this.toast.show();
      }

      public void onResult(Object paramAnonymousObject, int paramAnonymousInt)
      {
        Infos localInfos = (Infos)paramAnonymousObject;
        if ((paramAnonymousObject != null) && (paramAnonymousInt == ResultCode.RESULT_OK))
        {
          SignInActivity.this.user.setBeanNumber(SignInActivity.this.beanNumber + SignInActivity.this.user.getBeanNumber());
          SignInActivity.this.toast.setMessage("签到成功");
          SignInActivity.this.toast.show();
          return;
        }
        SignInActivity.this.toast.setMessage(localInfos.getMsg());
        SignInActivity.this.toast.show();
      }
    });
    localEarnSignBeansRequest.startRequest();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903092);
    initView();
    initExtraValue();
  }

  protected void onDestroy()
  {
    super.onDestroy();
    this.mediaPlayer1.release();
    this.mediaPlayer2.release();
  }

  protected void onPause()
  {
    super.onPause();
    MobclickAgent.onPause(this);
  }

  protected void onResume()
  {
    super.onResume();
    if (this.sensorManager != null)
      this.sensorManager.registerListener(this.sensorEventListener, this.sensorManager.getDefaultSensor(1), 3);
    MobclickAgent.onResume(this);
  }

  protected void onStop()
  {
    super.onStop();
    if (this.sensorManager != null)
      this.sensorManager.unregisterListener(this.sensorEventListener);
  }

  public void signInClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131231000:
      finish();
      return;
    case 2131231047:
      signEarnBeansRequest();
      this.shake_no.setVisibility(8);
      this.shake_ok.setVisibility(0);
      return;
    case 2131231049:
    }
    this.shake_no.setVisibility(0);
    this.shake_ok.setVisibility(8);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.SignInActivity
 * JD-Core Version:    0.6.2
 */