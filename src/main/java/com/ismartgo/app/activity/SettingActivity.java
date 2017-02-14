package com.ismartgo.app.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import cn.jpush.android.api.JPushInterface;
import com.ab.view.ioc.AbIocView;
import com.ismartgo.app.application.AndroidApplication;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.eventbus.HomeActivityEvent;
import com.ismartgo.app.jpush.JPushSetAlias;
import com.ismartgo.app.ownself.view.ToastDefine;
import com.ismartgo.app.tools.DataCleanManager;
import com.ismartgo.app.tools.SharedPreferenceUtil;
import com.umeng.analytics.MobclickAgent;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;

public class SettingActivity extends BaseActivity
  implements View.OnClickListener
{
  private AndroidApplication application;

  @AbIocView(click="onClick", id=2131230843)
  private Button btn_logout;

  @AbIocView(click="onClick", id=2131230839)
  private CheckBox cb_push_settings;

  @AbIocView(click="onClick", id=2131231035)
  private CheckBox cb_voice_settings;
  private TextView tvAbout;
  private TextView tvCache;

  @AbIocView(click="onClick", id=2131230840)
  private TextView tv_clear_cache;

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

  private void finishUpdateUser()
  {
    setResult(-1);
    finish();
  }

  private String getTotalCache()
  {
    try
    {
      String str = DataCleanManager.getTotalCacheSize(this);
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  private void initView()
  {
    boolean bool2 = true;
    initTitleBar();
    this.tv_title.setText("设置");
    this.tv_title.setCompoundDrawables(null, null, null, null);
    this.tvAbout = ((TextView)findViewById(2131230833));
    this.tvAbout.setOnClickListener(this);
    this.tvCache = ((TextView)findViewById(2131231036));
    this.tvCache.setText(getTotalCache());
    CheckBox localCheckBox = this.cb_push_settings;
    if (pushSwitch == 1)
    {
      bool1 = true;
      localCheckBox.setChecked(bool1);
      localCheckBox = this.cb_voice_settings;
      if (voiceSwich != 1)
        break label160;
    }
    label160: for (boolean bool1 = bool2; ; bool1 = false)
    {
      localCheckBox.setChecked(bool1);
      if ((loginUser != null) && (loginUser.getLoginType() == 6))
        this.btn_logout.setVisibility(8);
      this.application = ((AndroidApplication)getApplication());
      this.tv_left.setOnClickListener(this);
      return;
      bool1 = false;
      break;
    }
  }

  public void onClick(final View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131230840:
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
          SharedPreferenceUtil.clearLoginCount(SettingActivity.this);
          SettingActivity.this.clearCache();
        }
      });
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
    case 2131230833:
      startActivity(new Intent(this, AboutActivity.class));
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
          SettingActivity.this.application.setUser(null);
          SharedPreferenceUtil.clearLoginPwd(SettingActivity.this);
          SettingActivity.loginUser = SharedPreferenceUtil.getTouriseUser(SettingActivity.this.getApplicationContext());
          SettingActivity.this.application.setUser(SettingActivity.loginUser);
          new JPushSetAlias(SettingActivity.this.getApplicationContext()).setAlias(SettingActivity.loginUser.getId());
          paramView.dismiss();
          AndroidApplication.getInstance().isInShop(SettingActivity.loginUser.getId(), BaseActivity.city + "市");
          if (SettingActivity.this.getUserUsuallyAddress() != null)
            SettingActivity.this.getUserUsuallyAddress().clear();
          SettingActivity.this.btn_logout.setVisibility(8);
          SettingActivity.this.finishUpdateUser();
          EventBus.getDefault().post(new HomeActivityEvent("com.ismartgo.home.headview.loginrefreshData"));
        }
      });
      return;
    case 2131231076:
      finishUpdateUser();
    case 2131231035:
    }
    if (this.cb_voice_settings.isChecked())
    {
      SharedPreferenceUtil.setVoiceSwitch(this, 1);
      voiceSwich = 1;
      return;
    }
    SharedPreferenceUtil.setVoiceSwitch(this, 0);
    voiceSwich = 0;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903086);
    initView();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (paramKeyEvent.getAction() == 0))
    {
      finishUpdateUser();
      return true;
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
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.SettingActivity
 * JD-Core Version:    0.6.2
 */