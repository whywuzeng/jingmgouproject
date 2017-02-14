package com.ismartgo.app.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.ab.view.ioc.AbIocView;
import com.ismartgo.app.bean.Gift;
import com.ismartgo.app.tools.ImgLoader;
import com.ismartgo.app.tools.StringUtils;
import com.umeng.analytics.MobclickAgent;

@SuppressLint({"NewApi"})
public class ConsumptionCouponDetailActivity extends BaseActivity
{
  public static String TAG = "ConsumptionCouponDetailActivity";
  private Gift gift;

  @AbIocView(id=2131230877)
  private ImageView iv_pic;

  @AbIocView(id=2131230878)
  private TextView tv_name;

  @AbIocView(id=2131230922)
  private TextView tv_number;

  private void initView()
  {
    initTitleBar();
    this.tv_title.setText("消费券详情");
    ImgLoader.getInstance(this).showPic(StringUtils.getImgUrl(this.gift.getGiftLogo()), this.iv_pic, false);
    this.tv_name.setText(this.gift.getGiftName());
    this.tv_number.setText(this.gift.getGiftNumber());
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903066);
    this.gift = ((Gift)getIntent().getSerializableExtra("consumptionCoupon"));
    initView();
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
 * Qualified Name:     com.ismartgo.app.activity.ConsumptionCouponDetailActivity
 * JD-Core Version:    0.6.2
 */