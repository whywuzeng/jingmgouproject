package com.ismartgo.app.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.jpush.android.api.JPushInterface;
import com.ab.view.ioc.AbIocView;
import com.dodowaterfall.widget.HeadScrollView;
import com.ismartgo.app.application.AndroidApplication;
import com.ismartgo.app.bean.Gift;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.grid.CircleImageView;
import com.ismartgo.app.grid.utils.MyDialog;
import com.ismartgo.app.http.HttpCallback;
import com.ismartgo.app.http.HttpRequest;
import com.ismartgo.app.http.HttpWhat;
import com.ismartgo.app.jpush.JPushSetAlias;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.tools.DataCleanManager;
import com.ismartgo.app.tools.ImgLoader;
import com.ismartgo.app.tools.SharedPreferenceUtil;
import com.ismartgo.app.tools.StringUtils;
import com.ismartgo.app.url.ResultCode;
import com.ismartgo.app.url.Url;
import com.ismartgo.app.utils.LogUtils;
import com.ismartgo.app.utils.UMengStatisticsUtils;
import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;
import com.yolanda.nohttp.Response;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"NewApi", "InflateParams"})
public class MineActivity extends BaseActivity
  implements View.OnClickListener
{
  public static String TAG = "MineActivity";
  private AndroidApplication application;

  @AbIocView(click="onClick", id=2131230816)
  private Button btn_login;

  @AbIocView(click="onClick", id=2131230843)
  private Button btn_logout;

  @AbIocView(click="onClick", id=2131230839)
  private CheckBox cb_push_settings;
  private CircleImageView civHead;
  int clickCheck = 2001;
  int defalutCheck = 2000;
  private Gift gift1;
  private Gift gift2;
  private boolean hasUpdateVersion;
  private CircleImageView imgHeadIcon;
  JPushSetAlias jpush;
  private LinearLayout layoutClientLogin;
  private LinearLayout layoutSetting;
  private LinearLayout layoutThirdLogin;
  private LinearLayout layoutUserRank;

  @AbIocView(click="onClick", id=2131230841)
  private View layout_check_update;

  @AbIocView(click="onClick", id=2131231169)
  private View layout_exchange_1;

  @AbIocView(click="onClick", id=2131231201)
  private View layout_exchange_2;
  private View layout_head_logined;

  @AbIocView(id=2131230822)
  private View layout_head_no_login;

  @AbIocView(click="onClick", id=2131230836)
  private View layout_logcat;

  @AbIocView(click="onClick", id=2131231242)
  private LinearLayout layout_login;

  @AbIocView(click="onClick", id=2131230824)
  private View layout_my_beans;
  private MyDialog mDialog;
  private int quitCount = 0;
  private int refreshGiftCount;
  private RelativeLayout relativeLayout;
  private HeadScrollView scrollView;
  private TextView tvRank;
  private TextView tvSignDays;

  @AbIocView(click="onClick", id=2131230834)
  private TextView tvUsuallyQuestion;

  @AbIocView(click="onClick", id=2131230833)
  private TextView tv_about;

  @AbIocView(id=2131230825)
  private TextView tv_beans_num;

  @AbIocView(click="onClick", id=2131230840)
  private TextView tv_clear_cache;

  @AbIocView(click="onClick", id=2131230835)
  private TextView tv_feedback;

  @AbIocView(click="onClick", id=2131230831)
  private TextView tv_modify_phone;

  @AbIocView(click="onClick", id=2131230832)
  private TextView tv_modify_pwd;

  @AbIocView(click="onClick", id=2131231265)
  private TextView tv_more_gifts;

  @AbIocView(click="onClick", id=2131230829)
  private TextView tv_my_collect;

  @AbIocView(click="onClick", id=2131230828)
  private TextView tv_my_consumption_coupon;

  @AbIocView(click="onClick", id=2131230826)
  private TextView tv_my_exchange;

  @AbIocView(click="onClick", id=2131230827)
  private TextView tv_my_receipte;

  @AbIocView(id=2131231159)
  private TextView tv_nickName;

  @AbIocView(id=2131230862)
  private TextView tv_phone;
  private TextView tv_third_phone;

  @AbIocView(id=2131230842)
  private TextView tv_version;
  public int updatePhone_RequestCode = 20003;
  public int updatePhone_ResultCode = 20004;
  public int updatePwd_RequestCode = 20001;
  public int updatePwd_ResultCode = 20002;
  int version_Code;
  String version_Name;

  private void clearCache()
  {
    try
    {
      this.toast.setMessage("清除缓存成功");
      this.toast.show();
      DataCleanManager.cleanInternalCache(this);
      DataCleanManager.cleanExternalCache(this);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  private void initGift()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("cityName", city);
    HttpRequest.getInstance().executePostStringRequest(this, Url.MY_GIFT_URL, HttpWhat.MY_GIFT, localHashMap, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
        MineActivity.this.toast.show();
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        Log.i(MineActivity.TAG, "礼品json数据： " + ((String)paramAnonymousResponse.get()).toString());
        while (true)
        {
          try
          {
            paramAnonymousResponse = new JSONObject(((String)paramAnonymousResponse.get()).toString());
            if (Integer.valueOf(paramAnonymousResponse.getString("status")).intValue() == ResultCode.RESULT_OK)
            {
              paramAnonymousResponse = paramAnonymousResponse.getJSONObject("data").getJSONArray("giftList");
              paramAnonymousInt = 0;
              if (paramAnonymousInt >= paramAnonymousResponse.length())
              {
                MineActivity.this.setGift();
                return;
              }
              if (paramAnonymousInt == 0)
              {
                if (MineActivity.this.gift1 == null)
                  MineActivity.this.gift1 = new Gift();
                MineActivity.this.gift1.setGiftId(paramAnonymousResponse.getJSONObject(paramAnonymousInt).getLong("giftId"));
                MineActivity.this.gift1.setGiftLogo(Url.ADS_URL + paramAnonymousResponse.getJSONObject(paramAnonymousInt).getString("giftLogo"));
                MineActivity.this.gift1.setGiftName(paramAnonymousResponse.getJSONObject(paramAnonymousInt).getString("giftName"));
                MineActivity.this.gift1.setRequiredBean(paramAnonymousResponse.getJSONObject(paramAnonymousInt).getInt("requiredBean"));
                MineActivity.this.gift1.setH5Url(paramAnonymousResponse.getJSONObject(paramAnonymousInt).getString("h5Url"));
                MineActivity.this.gift1.setGiftType(paramAnonymousResponse.getJSONObject(paramAnonymousInt).getString("giftMode"));
                MineActivity.this.gift1.setNewGift(paramAnonymousResponse.getJSONObject(paramAnonymousInt).optInt("isnew"));
              }
              else if (paramAnonymousInt == 1)
              {
                if (MineActivity.this.gift2 == null)
                  MineActivity.this.gift2 = new Gift();
                MineActivity.this.gift2.setGiftId(paramAnonymousResponse.getJSONObject(paramAnonymousInt).getLong("giftId"));
                MineActivity.this.gift2.setGiftLogo(Url.ADS_URL + paramAnonymousResponse.getJSONObject(paramAnonymousInt).getString("giftLogo"));
                MineActivity.this.gift2.setGiftName(paramAnonymousResponse.getJSONObject(paramAnonymousInt).getString("giftName"));
                MineActivity.this.gift2.setRequiredBean(paramAnonymousResponse.getJSONObject(paramAnonymousInt).getInt("requiredBean"));
                MineActivity.this.gift2.setH5Url(paramAnonymousResponse.getJSONObject(paramAnonymousInt).getString("h5Url"));
                MineActivity.this.gift2.setGiftType(paramAnonymousResponse.getJSONObject(paramAnonymousInt).getString("giftMode"));
                MineActivity.this.gift2.setNewGift(paramAnonymousResponse.getJSONObject(paramAnonymousInt).optInt("isnew"));
              }
            }
          }
          catch (Exception paramAnonymousResponse)
          {
            System.out.println("异常： " + paramAnonymousResponse.getMessage());
            paramAnonymousResponse.printStackTrace();
            return;
          }
          MineActivity.this.toast.setMessage(paramAnonymousResponse.getString("msg"));
          MineActivity.this.toast.show();
          return;
          paramAnonymousInt += 1;
        }
      }
    });
  }

  private void initView()
  {
    this.layoutThirdLogin = ((LinearLayout)findViewById(2131231237));
    this.layoutClientLogin = ((LinearLayout)findViewById(2131231236));
    this.tv_third_phone = ((TextView)findViewById(2131231238));
    this.layout_head_logined = findViewById(2131230823);
    this.layout_head_logined.setOnClickListener(this);
    this.imgHeadIcon = ((CircleImageView)this.layout_head_logined.findViewById(2131231235));
    this.application = ((AndroidApplication)getApplication());
    this.jpush = new JPushSetAlias(getApplicationContext());
    this.relativeLayout = ((RelativeLayout)findViewById(2131231087));
    this.iv_right = ((ImageView)this.relativeLayout.findViewById(2131231090));
    this.tv_left = ((ImageView)this.relativeLayout.findViewById(2131231076));
    this.tv_title = ((TextView)this.relativeLayout.findViewById(2131231001));
    this.tv_right = ((TextView)this.relativeLayout.findViewById(2131231091));
    this.civHead = ((CircleImageView)this.relativeLayout.findViewById(2131231089));
    this.tv_title.setText("我");
    this.tv_title.setCompoundDrawables(null, null, null, null);
    this.tv_title.setVisibility(8);
    this.tv_left.setVisibility(8);
    this.civHead.setVisibility(8);
    CheckBox localCheckBox = this.cb_push_settings;
    if (pushSwitch == 1);
    for (boolean bool = true; ; bool = false)
    {
      localCheckBox.setChecked(bool);
      this.layoutSetting = ((LinearLayout)findViewById(2131230837));
      this.layoutSetting.setOnClickListener(this);
      this.layoutUserRank = ((LinearLayout)findViewById(2131231239));
      this.tvSignDays = ((TextView)findViewById(2131231240));
      this.tvRank = ((TextView)findViewById(2131231241));
      this.scrollView = ((HeadScrollView)findViewById(2131230821));
      return;
    }
  }

  private void setGift()
  {
    if (this.gift1 != null)
    {
      setGift(this.gift1, this.layout_exchange_1);
      if (this.gift2 != null)
      {
        setGift(this.gift2, this.layout_exchange_2);
        return;
      }
      setInvisible(this.layout_exchange_2);
      return;
    }
    setInvisible(this.layout_exchange_1);
    setInvisible(this.layout_exchange_2);
  }

  private void setGift(Gift paramGift, View paramView)
  {
    ((TextView)paramView.findViewById(2131230878)).setText(paramGift.getGiftName());
    ((TextView)paramView.findViewById(2131230783)).setText(paramGift.getRequiredBean() + "豆");
    ImgLoader.getInstance(this).showPic(StringUtils.getImgUrl(paramGift.getGiftLogo()), (ImageView)paramView.findViewById(2131230957), false);
    if (paramGift.getNewGift() == 1)
    {
      paramView.findViewById(2131231195).setVisibility(0);
      return;
    }
    paramView.findViewById(2131231195).setVisibility(8);
  }

  private void setUserNewInfo()
  {
    if (loginUser != null)
    {
      this.jpush.setAlias(loginUser.getId());
      this.layout_head_logined.setVisibility(0);
      this.layout_head_no_login.setVisibility(8);
      if (!loginUser.getNickname().equals(""))
        this.tv_nickName.setText(loginUser.getNickname());
      while (true)
      {
        label106: String str1;
        String str2;
        if ((loginUser.getLoginType() == 3) || (loginUser.getLoginType() == 4) || (loginUser.getLoginType() == 5))
        {
          findViewById(2131230830).setVisibility(8);
          if ((loginUser.getLoginType() != 1) && (loginUser.getLoginType() != 3) && (loginUser.getLoginType() != 4) && (loginUser.getLoginType() != 5))
            break label634;
          if (loginUser.getLoginType() != 1)
            break label482;
          this.layoutClientLogin.setVisibility(0);
          this.layoutThirdLogin.setVisibility(8);
          str1 = loginUser.getUsername().substring(0, 3);
          str2 = loginUser.getUsername().substring(7, 11);
          str1 = str1 + "****" + str2;
          this.tv_phone.setText(str1);
          label232: Log.i(TAG, "我的精明豆数： " + loginUser.getBeanNumber());
          this.tv_beans_num.setText(loginUser.getBeanNumber() + "豆");
          TextUtils.isEmpty("");
        }
        try
        {
          this.tvSignDays.setText(String.valueOf(loginUser.getSignDays()) + "天");
          if ((loginUser.getSignRankRate() == null) || ("null".equals(loginUser.getSignRankRate())) || (TextUtils.isEmpty(loginUser.getSignRankRate())))
            this.tvRank.setText("0");
          while (true)
          {
            if ((loginUser.getHeadIcon() == null) || ("".equals(loginUser.getHeadIcon().trim())))
              break label611;
            ImgLoader.getInstance(this).showPic(loginUser.getHeadIcon(), this.imgHeadIcon, false);
            ImgLoader.getInstance(this).showPic(loginUser.getHeadIcon(), this.civHead, false);
            updateBeansNum(loginUser.getId());
            return;
            this.tv_nickName.setText(2131296398);
            break;
            findViewById(2131230830).setVisibility(0);
            break label106;
            label482: this.layoutClientLogin.setVisibility(8);
            this.layoutThirdLogin.setVisibility(0);
            if ((TextUtils.isEmpty(loginUser.getPhone())) || (loginUser.getPhone().length() != 11))
              break label232;
            str1 = loginUser.getPhone().substring(0, 3);
            str2 = loginUser.getPhone().substring(7, 11);
            str1 = str1 + "****" + str2;
            this.tv_third_phone.setText(str1);
            break label232;
            this.tvRank.setText(loginUser.getSignRankRate());
          }
        }
        catch (Exception localException)
        {
          while (true)
          {
            localException.printStackTrace();
            continue;
            label611: this.imgHeadIcon.setImageResource(2130837726);
            this.civHead.setImageResource(2130837727);
          }
        }
      }
      label634: if (loginUser.getLoginType() == 6)
      {
        this.layout_head_logined.setVisibility(8);
        this.layout_head_no_login.setVisibility(0);
        this.tv_beans_num.setText(loginUser.getBeanNumber() + "豆");
        this.btn_logout.setVisibility(8);
        findViewById(2131230830).setVisibility(8);
        this.imgHeadIcon.setImageResource(2130837726);
        this.civHead.setImageResource(2130837727);
        updateBeansNum(loginUser.getId());
        return;
      }
      this.tv_phone.setText("");
      return;
    }
    this.layout_head_logined.setVisibility(8);
    this.layout_head_no_login.setVisibility(0);
    this.tv_beans_num.setText("0豆");
    this.btn_logout.setVisibility(8);
  }

  private void updateBeansNum(String paramString)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("uid", paramString);
    HttpRequest.getInstance().executePostStringRequest(this, Url.GET_USER_BEAN, 9, localHashMap, new HttpCallback()
    {
      public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
      {
      }

      public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
      {
        try
        {
          paramAnonymousInt = new JSONObject(((String)paramAnonymousResponse.get()).toString()).getJSONObject("data").getInt("userBeanNum");
          MineActivity.this.tv_beans_num.setText(paramAnonymousInt + "豆");
          MineActivity.loginUser.setBeanNumber(paramAnonymousInt);
          return;
        }
        catch (JSONException paramAnonymousResponse)
        {
          Log.i(MineActivity.TAG, "获取用户精明豆数结果解析有误 " + paramAnonymousResponse.getMessage());
          paramAnonymousResponse.printStackTrace();
        }
      }
    });
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == this.updatePwd_RequestCode) && (paramInt2 == this.updatePwd_ResultCode))
    {
      this.application = ((AndroidApplication)getApplication());
      this.application.setApplicationUser();
    }
    do
    {
      return;
      if ((paramInt1 == this.updatePhone_RequestCode) && (paramInt2 == this.updatePhone_ResultCode))
      {
        this.application = ((AndroidApplication)getApplication());
        this.application.setApplicationUser();
        setUserNewInfo();
        return;
      }
    }
    while ((paramInt1 != 17) || (paramInt2 != -1));
    this.application = ((AndroidApplication)getApplication());
    this.application.setApplicationUser();
    setUserNewInfo();
  }

  public void onClick(final View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131230816:
    case 2131231242:
    case 2131230832:
    case 2131230831:
    case 2131230840:
    case 2131230835:
    case 2131230833:
    case 2131230841:
      do
      {
        return;
        startActivity(new Intent(this, LoginActivity.class));
        return;
        if ((loginUser != null) && (loginUser.getLoginType() != 6))
        {
          if (loginUser.getLoginType() != 1)
          {
            this.toast.setMessage("第三方登录不支持修改密码...");
            this.toast.show();
            return;
          }
          startActivityForResult(new Intent(this, ModifyPwdActivity.class), this.updatePwd_RequestCode);
          return;
        }
        startActivity(new Intent(this, LoginActivity.class));
        return;
        if ((loginUser != null) && (loginUser.getLoginType() != 6))
        {
          if (loginUser.getLoginType() != 1)
          {
            this.toast.setMessage("第三方登录不支持修改手机号...");
            this.toast.show();
            return;
          }
          startActivityForResult(new Intent(this, ModifyPhoneActivity.class), this.updatePhone_RequestCode);
          return;
        }
        startActivity(new Intent(this, LoginActivity.class));
        return;
        paramView = new AlertDialog.Builder(this).create();
        paramView.show();
        paramView.setContentView(2130903159);
        paramView.findViewById(2131230873).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            paramView.dismiss();
          }
        });
        paramView.findViewById(2131230782).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            paramView.dismiss();
            SharedPreferenceUtil.clearLoginCount(MineActivity.this);
            MineActivity.this.clearCache();
          }
        });
        return;
        startActivity(new Intent(this, UMengFbActivity.class));
        return;
        startActivity(new Intent(this, AboutActivity.class));
        return;
      }
      while (!this.hasUpdateVersion);
      UmengUpdateAgent.forceUpdate(getApplicationContext());
      return;
    case 2131230836:
      startActivity(new Intent(this, LogcatActivity.class));
      return;
    case 2131230824:
      startActivity(new Intent(this, MyBeansActivity.class));
      return;
    case 2131231265:
      UMengStatisticsUtils.meGift(this);
      startActivity(new Intent(this, RewardExchangeActivity.class));
      return;
    case 2131230826:
      if (!isLogin())
      {
        startActivity(new Intent(this, LoginActivity.class));
        return;
      }
      startActivity(new Intent(this, MyExchangeActivity.class));
      return;
    case 2131230827:
      if (!isLogin())
      {
        startActivity(new Intent(this, LoginActivity.class));
        return;
      }
      startActivity(new Intent(this, MyReceiptListActivity.class));
      return;
    case 2131230829:
      if (!isLogin())
      {
        startActivity(new Intent(this, LoginActivity.class));
        return;
      }
      startActivity(new Intent(this, MyCollectActivity.class));
      return;
    case 2131230828:
      if (!isLogin())
      {
        startActivity(new Intent(this, LoginActivity.class));
        return;
      }
      startActivity(new Intent(this, MyConsumptionCouponActivity.class));
      return;
    case 2131230839:
      if (this.cb_push_settings.isChecked())
      {
        SharedPreferenceUtil.setPushSwitch(this, 1);
        pushSwitch = 1;
        JPushInterface.resumePush(getApplicationContext());
        return;
      }
      SharedPreferenceUtil.setPushSwitch(this, 0);
      pushSwitch = 0;
      JPushInterface.stopPush(getApplicationContext());
      return;
    case 2131230823:
      startActivity(new Intent(this, MyDataActivity.class));
      return;
    case 2131231169:
      toGiftDetail(this.gift1);
      return;
    case 2131231201:
      toGiftDetail(this.gift2);
      return;
    case 2131230843:
      paramView = new AlertDialog.Builder(this).create();
      paramView.show();
      paramView.setContentView(2130903159);
      ((TextView)paramView.findViewById(2131231101)).setText("您确定要退出登录吗？");
      paramView.findViewById(2131230873).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramView.dismiss();
        }
      });
      paramView.findViewById(2131230782).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          HomeActivity.isChanged = true;
          MineActivity.this.application.setUser(null);
          SharedPreferenceUtil.clearLoginPwd(MineActivity.this);
          MineActivity.loginUser = SharedPreferenceUtil.getTouriseUser(MineActivity.this.getApplicationContext());
          MineActivity.this.application.setUser(MineActivity.loginUser);
          new JPushSetAlias(MineActivity.this.getApplicationContext()).setAlias(MineActivity.loginUser.getId());
          MineActivity.this.setUserNewInfo();
          paramView.dismiss();
          AndroidApplication.getInstance().isInShop(MineActivity.loginUser.getId(), BaseActivity.city + "市");
          if (MineActivity.this.getUserUsuallyAddress() != null)
            MineActivity.this.getUserUsuallyAddress().clear();
        }
      });
      return;
    case 2131230837:
      startActivityForResult(new Intent(this, SettingActivity.class), 17);
      return;
    case 2131230834:
    }
    startActivity(new Intent(this, UsuallyQuestionActivity.class));
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903046);
    this.mDialog = new MyDialog(this);
    initView();
    initGift();
    this.application.addActivity(this);
  }

  protected void onDestroy()
  {
    super.onDestroy();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      if (this.quitCount == 1)
      {
        LogUtils.i("Test", "MineActivity退出");
        AndroidApplication.getInstance().stopService();
        super.onKeyDown(paramInt, paramKeyEvent);
        return true;
      }
      this.quitCount += 1;
      this.toast.setMessage("再按一次退出精明购！");
      this.toast.setDisplay(false);
      this.toast.show();
      new QuitThread(null).start();
      return true;
    }
    return false;
  }

  protected void onPause()
  {
    super.onPause();
    PromotionActivity.isClickInto = true;
    MobclickAgent.onPause(this);
  }

  protected void onResume()
  {
    super.onResume();
    this.refreshGiftCount += 1;
    if (this.refreshGiftCount == 5)
    {
      initGift();
      this.refreshGiftCount = 0;
    }
    setUserNewInfo();
    MobclickAgent.onResume(this);
  }

  private class QuitThread extends Thread
  {
    private QuitThread()
    {
    }

    public void run()
    {
      try
      {
        sleep(2000L);
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
      finally
      {
        MineActivity.this.quitCount = 0;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.MineActivity
 * JD-Core Version:    0.6.2
 */