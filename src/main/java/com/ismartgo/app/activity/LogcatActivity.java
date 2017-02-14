package com.ismartgo.app.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.ab.view.ioc.AbIocView;
import com.ismartgo.app.tools.BufferUtils;
import com.umeng.analytics.MobclickAgent;

public class LogcatActivity extends BaseActivity
{
  public static String location = "定位信息:";

  @AbIocView(id=2131230812)
  private TextView tv_ibeacon_shop_name;

  @AbIocView(id=2131230813)
  private TextView tv_location;

  @AbIocView(id=2131231001)
  private TextView tv_title;

  public void back(View paramView)
  {
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903044);
    this.tv_title.setText("程序调试信息");
    if (BufferUtils.getLocation() == null)
      return;
    location = location + BufferUtils.getLocation() + "\nlon=" + BaseActivity.log + ", lat=" + BaseActivity.lat + ", 位置:" + BaseActivity.location;
    this.tv_location.setText(location);
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
 * Qualified Name:     com.ismartgo.app.activity.LogcatActivity
 * JD-Core Version:    0.6.2
 */