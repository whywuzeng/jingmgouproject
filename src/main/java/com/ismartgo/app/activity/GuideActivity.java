package com.ismartgo.app.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.ismartgo.app.adapter.ViewPagerAdapter;
import com.ismartgo.app.application.AndroidApplication;
import com.ismartgo.app.grid.location.ToolLocation;
import com.ismartgo.app.tools.SharedPreferenceUtil;
import com.umeng.analytics.MobclickAgent;
import java.util.ArrayList;

public class GuideActivity extends BaseActivity
  implements View.OnClickListener
{
  private Button btn_casual_look;
  private Button btn_enter;
  private Button btn_shortcut_login;
  private ImageView imgIndex1;
  private ImageView imgIndex2;
  private ImageView imgIndex3;
  private ImageView imgIndex4;
  private LinearLayout layoutIndex;
  private ViewPager viewPager;
  private ArrayList<View> views;
  private ViewPagerAdapter vpAdapter;

  private void changeIndexImg(int paramInt)
  {
  }

  private void enterMain()
  {
    startActivity(new Intent(this, Tab_Container_Activity.class));
    finish();
  }

  @SuppressLint({"InflateParams"})
  private void initView()
  {
    Object localObject1 = getWindowManager();
    Object localObject2 = new DisplayMetrics();
    ((WindowManager)localObject1).getDefaultDisplay().getMetrics((DisplayMetrics)localObject2);
    int i = ((DisplayMetrics)localObject2).widthPixels;
    this.layoutIndex = ((LinearLayout)findViewById(2131230928));
    localObject1 = (LinearLayout.LayoutParams)this.layoutIndex.getLayoutParams();
    ((LinearLayout.LayoutParams)localObject1).width = (i / 3);
    this.layoutIndex.setLayoutParams((ViewGroup.LayoutParams)localObject1);
    this.views = new ArrayList();
    this.viewPager = ((ViewPager)findViewById(2131230927));
    Object localObject3 = LayoutInflater.from(this);
    localObject1 = ((LayoutInflater)localObject3).inflate(2130903124, null);
    localObject2 = ((LayoutInflater)localObject3).inflate(2130903125, null);
    localObject3 = ((LayoutInflater)localObject3).inflate(2130903127, null);
    this.views.add(localObject1);
    this.views.add(localObject2);
    this.views.add(localObject3);
    this.vpAdapter = new ViewPagerAdapter(this.views);
    this.viewPager.setAdapter(this.vpAdapter);
    this.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
    {
      public void onPageScrollStateChanged(int paramAnonymousInt)
      {
      }

      public void onPageScrolled(int paramAnonymousInt1, float paramAnonymousFloat, int paramAnonymousInt2)
      {
      }

      public void onPageSelected(int paramAnonymousInt)
      {
        GuideActivity.this.changeIndexImg(paramAnonymousInt);
      }
    });
    this.btn_casual_look = ((Button)findViewById(2131230933));
    this.btn_shortcut_login = ((Button)findViewById(2131230934));
    this.btn_enter = ((Button)((View)localObject3).findViewById(2131231125));
    this.btn_casual_look.setOnClickListener(this);
    this.btn_enter.setOnClickListener(this);
    this.btn_shortcut_login.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SharedPreferenceUtil.setUsed(GuideActivity.this);
        paramAnonymousView = new Intent(GuideActivity.this, Tab_Container_Activity.class);
        paramAnonymousView.putExtra("index", 3);
        GuideActivity.this.startActivity(paramAnonymousView);
        GuideActivity.this.finish();
      }
    });
  }

  private boolean valideLocate()
  {
    Object localObject = SharedPreferenceUtil.getLocationInfo(this);
    String str = ((SharedPreferences)localObject).getString("log", "");
    localObject = ((SharedPreferences)localObject).getString("lat", "");
    return ((!TextUtils.isEmpty(str)) && (!TextUtils.isEmpty((CharSequence)localObject))) || ((!TextUtils.isEmpty(BaseActivity.log)) && (!TextUtils.isEmpty(BaseActivity.lat)));
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131230933:
    case 2131231125:
    }
    enterMain();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903069);
    ToolLocation.requestLocation(AndroidApplication.getInstance(), AndroidApplication.getInstance(), false);
    initView();
  }

  protected void onDestroy()
  {
    super.onDestroy();
    if ((this.views != null) && (this.views.size() > 0))
    {
      this.views.clear();
      this.views = null;
    }
    this.vpAdapter = null;
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
 * Qualified Name:     com.ismartgo.app.activity.GuideActivity
 * JD-Core Version:    0.6.2
 */