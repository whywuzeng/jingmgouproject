package com.ismartgo.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.ismartgo.app.application.AndroidApplication;
import com.ismartgo.app.bean.CurrentLocationInfo;
import com.ismartgo.app.tools.ImgLoader;
import com.umeng.analytics.MobclickAgent;
import java.util.Timer;
import java.util.TimerTask;

public class HasSignedActivity extends BaseActivity
{
  ImageView imgLogo;
  private Timer timer;
  TextView tvOtherShop;
  TextView tvShopAddress;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903070);
    initTitleBar();
    this.tv_title.setText("到店签到");
    this.tvOtherShop = ((TextView)findViewById(2131230938));
    this.imgLogo = ((ImageView)findViewById(2131230936));
    paramBundle = AndroidApplication.getInstance().getCurrentLocation();
    ImgLoader.getInstance(this).showPic(paramBundle.getSignRetailLogo(), this.imgLogo, false);
    this.tvShopAddress = ((TextView)findViewById(2131230937));
    this.tvShopAddress.setText(paramBundle.getSignShopName());
    this.timer = new Timer();
    this.timer.schedule(new TimerTask()
    {
      public void run()
      {
        HasSignedActivity.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            HasSignedActivity.this.otherShop();
          }
        });
      }
    }
    , 3000L);
    this.tvOtherShop.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        HasSignedActivity.this.timer.cancel();
        HasSignedActivity.this.otherShop();
      }
    });
    this.tv_left.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        HasSignedActivity.this.timer.cancel();
        HasSignedActivity.this.finish();
      }
    });
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (paramKeyEvent.getAction() == 0))
      this.timer.cancel();
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

  public void otherShop()
  {
    startActivity(new Intent(this, IntoStoreSignInActivity.class));
    finish();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.HasSignedActivity
 * JD-Core Version:    0.6.2
 */