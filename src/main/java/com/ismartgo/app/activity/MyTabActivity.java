package com.ismartgo.app.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import com.ismartgo.app.tools.SystemBarTintManager;

public class MyTabActivity extends TabActivity
{
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

  private void initView()
  {
    TabHost localTabHost = getTabHost();
    localTabHost.addTab(localTabHost.newTabSpec("首页").setIndicator("首页", getResources().getDrawable(2130837677)).setContent(new Intent(this, HomeActivity.class)));
    localTabHost.addTab(localTabHost.newTabSpec("促销优惠").setIndicator("促销优惠", getResources().getDrawable(2130837827)).setContent(new Intent(this, PromotionActivity.class)));
    localTabHost.addTab(localTabHost.newTabSpec("赚精明豆").setIndicator("赚精明豆", getResources().getDrawable(2130837521)).setContent(new Intent(this, EarnBeansActivity.class)));
    localTabHost.addTab(localTabHost.newTabSpec("我的").setIndicator("我的", getResources().getDrawable(2130837795)).setContent(new Intent(this, MineActivity.class)));
  }

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

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903182);
    initView();
  }

  protected void onDestroy()
  {
    super.onDestroy();
    Log.i("Test", "tabhostactivity onDestroy");
  }

  protected void onPause()
  {
    super.onPause();
    Log.i("Test", "tabhostactivity onPause");
  }

  protected void onResume()
  {
    super.onResume();
    Log.i("Test", "tabhostactivity onResume");
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.MyTabActivity
 * JD-Core Version:    0.6.2
 */