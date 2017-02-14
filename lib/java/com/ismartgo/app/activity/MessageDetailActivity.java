package com.ismartgo.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import com.ab.view.ioc.AbIocView;
import com.umeng.analytics.MobclickAgent;

public class MessageDetailActivity extends BaseActivity
{

  @AbIocView(id=2131230968)
  private WebView msg_detail;

  @AbIocView(click="merchandiseClick", id=2131231000)
  private TextView pv_back;

  @AbIocView(id=2131231002)
  private ImageView pv_screening;

  private void initView()
  {
    this.pv_screening.setVisibility(4);
    initTitleBar();
    this.tv_title.setText("消息详情");
  }

  public int getMsgId()
  {
    return getIntent().getIntExtra("msgId", 0);
  }

  public void merchandiseClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131231000:
    }
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903075);
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
 * Qualified Name:     com.ismartgo.app.activity.MessageDetailActivity
 * JD-Core Version:    0.6.2
 */