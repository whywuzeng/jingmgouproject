package com.ismartgo.app.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ismartgo.app.application.AndroidApplication;
import com.ismartgo.app.item.TabItem;
import com.ismartgo.app.tools.SharedPreferenceUtil;
import com.ismartgo.app.tools.SystemBarTintManager;
import java.util.ArrayList;
import java.util.List;

public class Tab_Container_Activity extends TabHostActivity
{
  private int index;
  List<TabItem> mItems;

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

  protected int getTabItemCount()
  {
    return this.mItems.size();
  }

  protected String getTabItemId(int paramInt)
  {
    if (paramInt == 0)
      return "home";
    if (paramInt == 1)
      return "promotion";
    if (paramInt == 2)
      return "bean";
    if (paramInt == 3)
      return "me";
    return null;
  }

  protected Intent getTabItemIntent(int paramInt)
  {
    return ((TabItem)this.mItems.get(paramInt)).getIntent();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    BaseActivity.pushSwitch = SharedPreferenceUtil.getPushSwitch(this);
    this.index = getIntent().getIntExtra("index", 0);
    setCurrentTab(this.index);
    AndroidApplication.getInstance().addActivity(this);
  }

  protected void onDestroy()
  {
    super.onDestroy();
    AndroidApplication.getInstance().stopService();
    System.exit(0);
    Log.i("Test", "tabcontaineractivity退出程序");
  }

  protected void onPause()
  {
    super.onPause();
  }

  protected void onResume()
  {
    super.onResume();
  }

  protected void onStop()
  {
    super.onStop();
    Log.i("Test", "tabcontaineractivity onStop");
  }

  protected void prepare()
  {
    TabItem localTabItem1 = new TabItem("首页", 2130837677, 17170445, new Intent(this, HomeActivity.class));
    TabItem localTabItem2 = new TabItem("促销优惠", 2130837827, 17170445, new Intent(this, PromotionActivity.class));
    TabItem localTabItem3 = new TabItem("", 17170445, 17170445, new Intent(this, TestActivity.class));
    TabItem localTabItem4 = new TabItem("赚精明豆", 2130837521, 17170445, new Intent(this, EarnBeansActivity.class));
    TabItem localTabItem5 = new TabItem("我", 2130837795, 17170445, new Intent(this, MineActivity.class));
    this.mItems = new ArrayList();
    this.mItems.add(localTabItem1);
    this.mItems.add(localTabItem2);
    this.mItems.add(localTabItem3);
    this.mItems.add(localTabItem4);
    this.mItems.add(localTabItem5);
  }

  protected void setTabItemTextView(TextView paramTextView, ImageView paramImageView, LinearLayout paramLinearLayout, int paramInt)
  {
    paramTextView.setText(((TabItem)this.mItems.get(paramInt)).getTitle());
    paramImageView.setImageResource(((TabItem)this.mItems.get(paramInt)).getIcon());
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.Tab_Container_Activity
 * JD-Core Version:    0.6.2
 */