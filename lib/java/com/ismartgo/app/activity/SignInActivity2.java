package com.ismartgo.app.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ismartgo.app.application.AndroidApplication;
import com.ismartgo.app.bean.CurrentLocationInfo;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.interfaces.OnCompleteListener;
import com.ismartgo.app.net.EarnSignBeansRequest;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.tools.ImgLoader;
import com.ismartgo.app.tools.SharedPreferenceUtil;
import com.ismartgo.app.url.Url;
import com.umeng.analytics.MobclickAgent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class SignInActivity2 extends BaseActivity
{
  private static final String TAG = "SignInActivity2";
  private AndroidApplication application;
  int beanNum;
  private TextView get_beannumber;
  private ImageView imgFlashLight;
  private ImageView imgLogo;
  private ImageView imgSigned;
  private LinearLayout layoutFailure;
  private LinearLayout layoutSuccess;
  private Timer mTimer;
  private int signresult;
  TextView tvOtherShop;
  private TextView tvShopAddress;
  private TextView tv_earn_empty;
  private User user;

  private void back()
  {
    if (this.mTimer != null)
      this.mTimer.cancel();
    Object localObject1 = Calendar.getInstance();
    Object localObject2 = new SimpleDateFormat("yyyy-MM-dd").format(((Calendar)localObject1).getTime());
    localObject1 = AndroidApplication.getInstance().getCurrentLocation();
    ((CurrentLocationInfo)localObject1).setLatestSignDate((String)localObject2);
    localObject2 = new Intent();
    ((Intent)localObject2).putExtra("isInShop", true);
    ((Intent)localObject2).putExtra("shopId", ((CurrentLocationInfo)localObject1).getSignShopId());
    ((Intent)localObject2).putExtra("shopName", ((CurrentLocationInfo)localObject1).getSignShopName());
    ((Intent)localObject2).setClass(this, ScanInStoreGoodsActivity.class);
    startActivity((Intent)localObject2);
    AndroidApplication.getInstance().isInShop(this.user.getId(), BaseActivity.city + "市");
    finish();
    MobclickAgent.onPageEnd(getClass().getName());
  }

  private void dismissPop()
  {
    Animation localAnimation = AnimationUtils.loadAnimation(this, 2130968597);
    if (this.signresult == 1)
    {
      this.layoutSuccess.startAnimation(localAnimation);
      this.layoutSuccess.setVisibility(8);
    }
    while (true)
    {
      localAnimation.setAnimationListener(new Animation.AnimationListener()
      {
        public void onAnimationEnd(Animation paramAnonymousAnimation)
        {
          paramAnonymousAnimation = AndroidApplication.getInstance().getCurrentLocation();
          Intent localIntent = new Intent();
          localIntent.putExtra("isInShop", true);
          localIntent.putExtra("shopId", paramAnonymousAnimation.getSignShopId());
          localIntent.putExtra("shopName", paramAnonymousAnimation.getSignShopName());
          localIntent.setClass(SignInActivity2.this, ScanInStoreGoodsActivity.class);
          SignInActivity2.this.startActivity(localIntent);
          AndroidApplication.getInstance().isInShop(SignInActivity2.this.user.getId(), BaseActivity.city + "市");
          SignInActivity2.this.finish();
          MobclickAgent.onPageEnd(getClass().getName());
        }

        public void onAnimationRepeat(Animation paramAnonymousAnimation)
        {
        }

        public void onAnimationStart(Animation paramAnonymousAnimation)
        {
        }
      });
      return;
      if (this.signresult == 2)
      {
        this.layoutFailure.startAnimation(localAnimation);
        this.layoutFailure.setVisibility(8);
      }
    }
  }

  private void hasSignedDismiss()
  {
    new Timer().schedule(new TimerTask()
    {
      public void run()
      {
        SignInActivity2.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            SignInActivity2.this.back();
          }
        });
      }
    }
    , 3000L);
  }

  private void initView()
  {
    MobclickAgent.onPageStart(getClass().getName());
    this.application = ((AndroidApplication)getApplication());
    this.user = this.application.getUser();
    initTitleBar();
    this.tv_title.setText("到店签到");
    this.imgFlashLight = ((ImageView)findViewById(2131231041));
    this.imgSigned = ((ImageView)findViewById(2131231040));
    this.layoutSuccess = ((LinearLayout)findViewById(2131231042));
    this.layoutFailure = ((LinearLayout)findViewById(2131231044));
    this.get_beannumber = ((TextView)findViewById(2131231043));
    this.tv_earn_empty = ((TextView)findViewById(2131231045));
    new Timer().schedule(new TimerTask()
    {
      public void run()
      {
        SignInActivity2.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            SignInActivity2.this.imgFlashLight.setVisibility(0);
          }
        });
      }
    }
    , 1000L);
    this.imgLogo = ((ImageView)findViewById(2131230936));
    CurrentLocationInfo localCurrentLocationInfo = AndroidApplication.getInstance().getCurrentLocation();
    ImgLoader.getInstance(this).showPic(localCurrentLocationInfo.getSignRetailLogo(), this.imgLogo, false);
    this.tvShopAddress = ((TextView)findViewById(2131230937));
    this.tvShopAddress.setText(localCurrentLocationInfo.getSignShopName());
    EarnSignBeansRequest localEarnSignBeansRequest = new EarnSignBeansRequest(this, Url.SIGN_IN);
    localEarnSignBeansRequest.initParams(loginUser.getId(), BaseActivity.log, BaseActivity.lat, localCurrentLocationInfo.getSignShopId(), localCurrentLocationInfo.getBeaconId());
    localEarnSignBeansRequest.startRequest();
    localEarnSignBeansRequest.setOnCompleteListener(new OnCompleteListener()
    {
      public void onFailure(Throwable paramAnonymousThrowable, int paramAnonymousInt, String paramAnonymousString)
      {
        SignInActivity2.this.toast.setMessage(SignInActivity2.this.getString(2131296392));
        SignInActivity2.this.toast.show();
      }

      // ERROR //
      public void onResult(Object paramAnonymousObject, int paramAnonymousInt)
      {
        // Byte code:
        //   0: ldc 48
        //   2: new 50	java/lang/StringBuilder
        //   5: dup
        //   6: ldc 52
        //   8: invokespecial 54	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
        //   11: aload_1
        //   12: invokevirtual 58	java/lang/Object:toString	()Ljava/lang/String;
        //   15: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   18: invokevirtual 63	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   21: invokestatic 69	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
        //   24: pop
        //   25: iconst_1
        //   26: putstatic 75	com/ismartgo/app/activity/PromotionActivity:isClickInto	Z
        //   29: new 77	org/json/JSONObject
        //   32: dup
        //   33: aload_1
        //   34: invokevirtual 58	java/lang/Object:toString	()Ljava/lang/String;
        //   37: invokespecial 78	org/json/JSONObject:<init>	(Ljava/lang/String;)V
        //   40: astore_1
        //   41: aload_1
        //   42: ldc 80
        //   44: invokevirtual 84	org/json/JSONObject:getInt	(Ljava/lang/String;)I
        //   47: getstatic 90	com/ismartgo/app/url/ResultCode:RESULT_OK	I
        //   50: if_icmpne +246 -> 296
        //   53: aload_0
        //   54: getfield 17	com/ismartgo/app/activity/SignInActivity2$2:this$0	Lcom/ismartgo/app/activity/SignInActivity2;
        //   57: invokestatic 94	com/ismartgo/app/activity/SignInActivity2:access$1	(Lcom/ismartgo/app/activity/SignInActivity2;)Landroid/widget/ImageView;
        //   60: iconst_0
        //   61: invokevirtual 100	android/widget/ImageView:setVisibility	(I)V
        //   64: aload_0
        //   65: getfield 17	com/ismartgo/app/activity/SignInActivity2$2:this$0	Lcom/ismartgo/app/activity/SignInActivity2;
        //   68: aload_1
        //   69: ldc 102
        //   71: invokevirtual 106	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
        //   74: ldc 108
        //   76: invokevirtual 84	org/json/JSONObject:getInt	(Ljava/lang/String;)I
        //   79: putfield 111	com/ismartgo/app/activity/SignInActivity2:beanNum	I
        //   82: aload_1
        //   83: ldc 102
        //   85: invokevirtual 106	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
        //   88: ldc 113
        //   90: invokevirtual 84	org/json/JSONObject:getInt	(Ljava/lang/String;)I
        //   93: istore_2
        //   94: aload_0
        //   95: getfield 17	com/ismartgo/app/activity/SignInActivity2$2:this$0	Lcom/ismartgo/app/activity/SignInActivity2;
        //   98: invokestatic 117	com/ismartgo/app/activity/SignInActivity2:access$2	(Lcom/ismartgo/app/activity/SignInActivity2;)Lcom/ismartgo/app/bean/User;
        //   101: iload_2
        //   102: invokevirtual 122	com/ismartgo/app/bean/User:setBeanNumber	(I)V
        //   105: aload_0
        //   106: getfield 17	com/ismartgo/app/activity/SignInActivity2$2:this$0	Lcom/ismartgo/app/activity/SignInActivity2;
        //   109: aload_1
        //   110: ldc 102
        //   112: invokevirtual 106	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
        //   115: ldc 124
        //   117: invokevirtual 127	org/json/JSONObject:optInt	(Ljava/lang/String;)I
        //   120: invokestatic 131	com/ismartgo/app/activity/SignInActivity2:access$3	(Lcom/ismartgo/app/activity/SignInActivity2;I)V
        //   123: aload_0
        //   124: getfield 17	com/ismartgo/app/activity/SignInActivity2$2:this$0	Lcom/ismartgo/app/activity/SignInActivity2;
        //   127: invokestatic 135	com/ismartgo/app/activity/SignInActivity2:access$4	(Lcom/ismartgo/app/activity/SignInActivity2;)I
        //   130: iconst_1
        //   131: if_icmpne +118 -> 249
        //   134: aload_0
        //   135: getfield 17	com/ismartgo/app/activity/SignInActivity2$2:this$0	Lcom/ismartgo/app/activity/SignInActivity2;
        //   138: invokestatic 139	com/ismartgo/app/activity/SignInActivity2:access$5	(Lcom/ismartgo/app/activity/SignInActivity2;)Landroid/widget/TextView;
        //   141: aload_0
        //   142: getfield 17	com/ismartgo/app/activity/SignInActivity2$2:this$0	Lcom/ismartgo/app/activity/SignInActivity2;
        //   145: getfield 111	com/ismartgo/app/activity/SignInActivity2:beanNum	I
        //   148: invokestatic 144	java/lang/String:valueOf	(I)Ljava/lang/String;
        //   151: invokevirtual 150	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
        //   154: aload_0
        //   155: getfield 17	com/ismartgo/app/activity/SignInActivity2$2:this$0	Lcom/ismartgo/app/activity/SignInActivity2;
        //   158: invokestatic 153	com/ismartgo/app/activity/SignInActivity2:access$7	(Lcom/ismartgo/app/activity/SignInActivity2;)V
        //   161: invokestatic 159	java/util/Calendar:getInstance	()Ljava/util/Calendar;
        //   164: astore_1
        //   165: new 161	java/text/SimpleDateFormat
        //   168: dup
        //   169: ldc 163
        //   171: invokespecial 164	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
        //   174: aload_1
        //   175: invokevirtual 168	java/util/Calendar:getTime	()Ljava/util/Date;
        //   178: invokevirtual 172	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
        //   181: astore_3
        //   182: invokestatic 177	com/ismartgo/app/application/AndroidApplication:getInstance	()Lcom/ismartgo/app/application/AndroidApplication;
        //   185: invokevirtual 181	com/ismartgo/app/application/AndroidApplication:getCurrentLocation	()Lcom/ismartgo/app/bean/CurrentLocationInfo;
        //   188: astore_1
        //   189: aload_1
        //   190: aload_3
        //   191: invokevirtual 186	com/ismartgo/app/bean/CurrentLocationInfo:setLatestSignDate	(Ljava/lang/String;)V
        //   194: new 188	java/util/HashMap
        //   197: dup
        //   198: invokespecial 189	java/util/HashMap:<init>	()V
        //   201: astore_3
        //   202: aload_3
        //   203: ldc 191
        //   205: aload_1
        //   206: invokevirtual 195	com/ismartgo/app/bean/CurrentLocationInfo:getSignShopId	()I
        //   209: invokestatic 144	java/lang/String:valueOf	(I)Ljava/lang/String;
        //   212: invokeinterface 201 3 0
        //   217: pop
        //   218: aload_3
        //   219: ldc 203
        //   221: aload_1
        //   222: invokevirtual 206	com/ismartgo/app/bean/CurrentLocationInfo:getSignRetailName	()Ljava/lang/String;
        //   225: invokeinterface 201 3 0
        //   230: pop
        //   231: aload_0
        //   232: getfield 17	com/ismartgo/app/activity/SignInActivity2$2:this$0	Lcom/ismartgo/app/activity/SignInActivity2;
        //   235: ldc 208
        //   237: aload_3
        //   238: aload_0
        //   239: getfield 17	com/ismartgo/app/activity/SignInActivity2$2:this$0	Lcom/ismartgo/app/activity/SignInActivity2;
        //   242: getfield 111	com/ismartgo/app/activity/SignInActivity2:beanNum	I
        //   245: invokestatic 214	com/umeng/analytics/MobclickAgent:onEventValue	(Landroid/content/Context;Ljava/lang/String;Ljava/util/Map;I)V
        //   248: return
        //   249: aload_0
        //   250: getfield 17	com/ismartgo/app/activity/SignInActivity2$2:this$0	Lcom/ismartgo/app/activity/SignInActivity2;
        //   253: invokestatic 135	com/ismartgo/app/activity/SignInActivity2:access$4	(Lcom/ismartgo/app/activity/SignInActivity2;)I
        //   256: iconst_2
        //   257: if_icmpne -103 -> 154
        //   260: aload_0
        //   261: getfield 17	com/ismartgo/app/activity/SignInActivity2$2:this$0	Lcom/ismartgo/app/activity/SignInActivity2;
        //   264: invokestatic 217	com/ismartgo/app/activity/SignInActivity2:access$6	(Lcom/ismartgo/app/activity/SignInActivity2;)Landroid/widget/TextView;
        //   267: aload_1
        //   268: ldc 102
        //   270: invokevirtual 106	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
        //   273: ldc 219
        //   275: invokevirtual 223	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
        //   278: invokevirtual 150	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
        //   281: goto -127 -> 154
        //   284: astore_1
        //   285: aload_1
        //   286: invokevirtual 226	org/json/JSONException:printStackTrace	()V
        //   289: return
        //   290: astore_1
        //   291: aload_1
        //   292: invokevirtual 227	java/lang/Exception:printStackTrace	()V
        //   295: return
        //   296: invokestatic 159	java/util/Calendar:getInstance	()Ljava/util/Calendar;
        //   299: astore_3
        //   300: new 161	java/text/SimpleDateFormat
        //   303: dup
        //   304: ldc 163
        //   306: invokespecial 164	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
        //   309: aload_3
        //   310: invokevirtual 168	java/util/Calendar:getTime	()Ljava/util/Date;
        //   313: invokevirtual 172	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
        //   316: astore_3
        //   317: invokestatic 177	com/ismartgo/app/application/AndroidApplication:getInstance	()Lcom/ismartgo/app/application/AndroidApplication;
        //   320: invokevirtual 181	com/ismartgo/app/application/AndroidApplication:getCurrentLocation	()Lcom/ismartgo/app/bean/CurrentLocationInfo;
        //   323: aload_3
        //   324: invokevirtual 186	com/ismartgo/app/bean/CurrentLocationInfo:setLatestSignDate	(Ljava/lang/String;)V
        //   327: aload_0
        //   328: getfield 17	com/ismartgo/app/activity/SignInActivity2$2:this$0	Lcom/ismartgo/app/activity/SignInActivity2;
        //   331: getfield 26	com/ismartgo/app/activity/SignInActivity2:toast	Lcom/ismartgo/app/ownself/view/ToastDefine;
        //   334: aload_1
        //   335: ldc 229
        //   337: invokevirtual 231	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
        //   340: invokevirtual 37	com/ismartgo/app/ownself/view/ToastDefine:setMessage	(Ljava/lang/String;)V
        //   343: aload_0
        //   344: getfield 17	com/ismartgo/app/activity/SignInActivity2$2:this$0	Lcom/ismartgo/app/activity/SignInActivity2;
        //   347: getfield 26	com/ismartgo/app/activity/SignInActivity2:toast	Lcom/ismartgo/app/ownself/view/ToastDefine;
        //   350: invokevirtual 40	com/ismartgo/app/ownself/view/ToastDefine:show	()V
        //   353: aload_0
        //   354: getfield 17	com/ismartgo/app/activity/SignInActivity2$2:this$0	Lcom/ismartgo/app/activity/SignInActivity2;
        //   357: invokestatic 94	com/ismartgo/app/activity/SignInActivity2:access$1	(Lcom/ismartgo/app/activity/SignInActivity2;)Landroid/widget/ImageView;
        //   360: iconst_0
        //   361: invokevirtual 100	android/widget/ImageView:setVisibility	(I)V
        //   364: aload_0
        //   365: getfield 17	com/ismartgo/app/activity/SignInActivity2$2:this$0	Lcom/ismartgo/app/activity/SignInActivity2;
        //   368: invokestatic 234	com/ismartgo/app/activity/SignInActivity2:access$8	(Lcom/ismartgo/app/activity/SignInActivity2;)V
        //   371: return
        //
        // Exception table:
        //   from	to	target	type
        //   29	154	284	org/json/JSONException
        //   154	194	284	org/json/JSONException
        //   194	248	284	org/json/JSONException
        //   249	281	284	org/json/JSONException
        //   291	295	284	org/json/JSONException
        //   296	371	284	org/json/JSONException
        //   194	248	290	java/lang/Exception
      }
    });
    this.tvOtherShop = ((TextView)findViewById(2131230938));
    this.tvOtherShop.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SignInActivity2.this.back();
      }
    });
    this.tv_left.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (SignInActivity2.this.mTimer != null)
          SignInActivity2.this.mTimer.cancel();
        SignInActivity2.this.finish();
      }
    });
  }

  private void showPop()
  {
    if (this.signresult == 1)
    {
      this.layoutSuccess.setVisibility(0);
      this.layoutSuccess.startAnimation(AnimationUtils.loadAnimation(this, 2130968598));
      if (SharedPreferenceUtil.getVoiceSwitch(this) == 1)
        startSuccessPlayer();
    }
    while (true)
    {
      this.mTimer = new Timer();
      this.mTimer.schedule(new TimerTask()
      {
        public void run()
        {
          SignInActivity2.this.runOnUiThread(new Runnable()
          {
            public void run()
            {
              SignInActivity2.this.dismissPop();
            }
          });
        }
      }
      , 6250L);
      return;
      if (this.signresult == 2)
      {
        this.layoutFailure.setVisibility(0);
        this.layoutFailure.startAnimation(AnimationUtils.loadAnimation(this, 2130968598));
        if (SharedPreferenceUtil.getVoiceSwitch(this) == 1)
          startSuccessPlayer();
      }
    }
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
    setContentView(2130903091);
    MobclickAgent.setDebugMode(true);
    initView();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (paramKeyEvent.getAction() == 0) && (this.mTimer != null))
      this.mTimer.cancel();
    MobclickAgent.onPageEnd(getClass().getName());
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
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.SignInActivity2
 * JD-Core Version:    0.6.2
 */