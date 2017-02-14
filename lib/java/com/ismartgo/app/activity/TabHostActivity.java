package com.ismartgo.app.activity;

import android.annotation.SuppressLint;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;
import com.ismartgo.app.application.AndroidApplication;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.eventbus.HomeActivityEvent;
import com.ismartgo.app.grid.view.PopwindowUtils;
import com.ismartgo.app.tools.SharedPreferenceUtil;
import com.ismartgo.app.tools.SystemBarTintManager;
import com.ismartgo.app.utils.UMengStatisticsUtils;
import de.greenrobot.event.EventBus;

public abstract class TabHostActivity extends TabActivity
{
  AndroidApplication application;
  private LayoutInflater mLayoutflater;
  private TabHost mTabHost;
  private TabWidget mTabWidget;
  public ImageView tvPhoto;

  private void applyKitKatTranslucency()
  {
    if (Build.VERSION.SDK_INT >= 19)
    {
      setTranslucentStatus(true);
      SystemBarTintManager localSystemBarTintManager = new SystemBarTintManager(this);
      localSystemBarTintManager.setStatusBarTintEnabled(true);
      localSystemBarTintManager.setNavigationBarTintEnabled(true);
      localSystemBarTintManager.setTintColor(getResources().getColor(2131099700));
    }
  }

  @SuppressLint({"InflateParams"})
  private void initTabSpec()
  {
    int j = getTabItemCount();
    int i = 0;
    while (true)
    {
      if (i >= j)
        return;
      View localView = this.mLayoutflater.inflate(2130903131, null);
      Object localObject = (LinearLayout)localView.findViewById(2131231130);
      setTabItemTextView((TextView)localView.findViewById(2131231132), (ImageView)localView.findViewById(2131231131), (LinearLayout)localObject, i);
      localObject = getTabItemId(i);
      localObject = this.mTabHost.newTabSpec((String)localObject);
      ((TabHost.TabSpec)localObject).setIndicator(localView);
      ((TabHost.TabSpec)localObject).setContent(getTabItemIntent(i));
      this.mTabHost.addTab((TabHost.TabSpec)localObject);
      i += 1;
    }
  }

  @SuppressLint({"InlinedApi"})
  private void setTranslucentStatus(boolean paramBoolean)
  {
    Window localWindow = getWindow();
    WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
    if (paramBoolean);
    for (localLayoutParams.flags |= 67108864; ; localLayoutParams.flags &= -67108865)
    {
      localWindow.setAttributes(localLayoutParams);
      return;
    }
  }

  protected void focusCurrentTab(int paramInt)
  {
    this.mTabWidget.focusCurrentTab(paramInt);
  }

  protected int getTabCount()
  {
    return this.mTabHost.getTabWidget().getTabCount();
  }

  protected abstract int getTabItemCount();

  protected abstract String getTabItemId(int paramInt);

  protected abstract Intent getTabItemIntent(int paramInt);

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903130);
    this.tvPhoto = ((ImageView)findViewById(2131231129));
    this.tvPhoto.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        TabHostActivity.this.application = ((AndroidApplication)TabHostActivity.this.getApplication());
        paramAnonymousView = TabHostActivity.this.application.getUser();
        if ((paramAnonymousView == null) || (paramAnonymousView.getLoginType() == 6))
        {
          paramAnonymousView = new Intent(TabHostActivity.this, LoginActivity.class);
          TabHostActivity.this.startActivity(paramAnonymousView);
          return;
        }
        paramAnonymousView = new Intent(TabHostActivity.this, CameraActivity1.class);
        TabHostActivity.this.startActivity(paramAnonymousView);
      }
    });
    AndroidApplication.getInstance().addActivity(this);
    this.mLayoutflater = getLayoutInflater();
    this.mTabHost = getTabHost();
    this.mTabWidget = getTabWidget();
    prepare();
    initTabSpec();
    this.mTabHost.getCurrentTabView().setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Log.v("taghost", "点击了");
        if (TabHostActivity.this.mTabHost.getCurrentTab() == 0)
        {
          EventBus.getDefault().post(new HomeActivityEvent("com.ismartgo.home.scrollTop"));
          return;
        }
        TabHostActivity.this.mTabHost.setCurrentTab(0);
      }
    });
    this.mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener()
    {
      public void onTabChanged(String paramAnonymousString)
      {
        if ((!TextUtils.isEmpty(paramAnonymousString)) && (!paramAnonymousString.equals("home")))
        {
          if (!paramAnonymousString.equals("promotion"))
            break label33;
          UMengStatisticsUtils.promotion(TabHostActivity.this);
        }
        label33: 
        do
        {
          return;
          if (paramAnonymousString.equals("bean"))
          {
            UMengStatisticsUtils.bean(TabHostActivity.this);
            return;
          }
        }
        while (!paramAnonymousString.equals("me"));
        UMengStatisticsUtils.me(TabHostActivity.this);
      }
    });
  }

  protected void onDestroy()
  {
    super.onDestroy();
    Log.i("Test", "tabhostactivity 退出程序");
    AndroidApplication.getInstance().stopService();
  }

  protected void onPause()
  {
    super.onPause();
  }

  protected void onResume()
  {
    super.onResume();
  }

  public void onWindowFocusChanged(boolean paramBoolean)
  {
    int i = SharedPreferenceUtil.getMyReceiptCount(this);
    if ((paramBoolean) && (i <= 0))
    {
      SharedPreferenceUtil.setMyReceiptCount(this, 1);
      PopwindowUtils.remainedPopwindow(this, this.tvPhoto);
    }
  }

  protected void prepare()
  {
  }

  protected void setCurrentTab(int paramInt)
  {
    this.mTabHost.setCurrentTab(paramInt);
  }

  protected abstract void setTabItemTextView(TextView paramTextView, ImageView paramImageView, LinearLayout paramLinearLayout, int paramInt);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.TabHostActivity
 * JD-Core Version:    0.6.2
 */