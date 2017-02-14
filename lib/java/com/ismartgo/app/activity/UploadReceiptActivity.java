package com.ismartgo.app.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.fragment.AllReceiptFragment;
import com.ismartgo.app.fragment.HasJoinFragment;
import com.ismartgo.app.tools.SystemBarTintManager;
import com.umeng.analytics.MobclickAgent;
import java.util.List;

public class UploadReceiptActivity extends FragmentActivity
  implements View.OnClickListener
{
  private Fragment allReceiptFragment;
  private FragmentManager fm;
  private Fragment hasJoinFragment;
  private ImageView imgBack;
  private RadioButton rbAll;
  private RadioButton rbMine;
  private RadioGroup rgReceipt;
  private TextView tvAll;
  private TextView tvHasJoin;

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
    this.imgBack = ((ImageView)findViewById(2131231076));
    this.imgBack.setOnClickListener(this);
    this.allReceiptFragment = new AllReceiptFragment();
    this.hasJoinFragment = new HasJoinFragment();
    this.fm = getSupportFragmentManager();
    FragmentTransaction localFragmentTransaction = this.fm.beginTransaction();
    localFragmentTransaction.add(2131231080, this.allReceiptFragment);
    localFragmentTransaction.commit();
    this.rgReceipt = ((RadioGroup)findViewById(2131231077));
    this.rbAll = ((RadioButton)findViewById(2131231078));
    this.rbMine = ((RadioButton)findViewById(2131231079));
    this.rgReceipt.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
    {
      public void onCheckedChanged(RadioGroup paramAnonymousRadioGroup, int paramAnonymousInt)
      {
        paramAnonymousRadioGroup = UploadReceiptActivity.this.fm.beginTransaction();
        if (paramAnonymousInt == 2131231078)
        {
          if (UploadReceiptActivity.this.fm.getFragments().contains(UploadReceiptActivity.this.hasJoinFragment))
            paramAnonymousRadioGroup.hide(UploadReceiptActivity.this.hasJoinFragment);
          paramAnonymousRadioGroup.show(UploadReceiptActivity.this.allReceiptFragment);
          paramAnonymousRadioGroup.commit();
          UploadReceiptActivity.this.rbAll.setTextColor(UploadReceiptActivity.this.getResources().getColor(2131099700));
          UploadReceiptActivity.this.rbMine.setTextColor(-1);
        }
        while (paramAnonymousInt != 2131231079)
          return;
        UploadReceiptActivity.this.rbAll.setTextColor(-1);
        UploadReceiptActivity.this.rbMine.setTextColor(UploadReceiptActivity.this.getResources().getColor(2131099700));
        if (BaseActivity.loginUser.getLoginType() == 6)
        {
          UploadReceiptActivity.this.rbAll.setChecked(true);
          UploadReceiptActivity.this.startActivity(new Intent(UploadReceiptActivity.this, LoginActivity.class));
          return;
        }
        if (UploadReceiptActivity.this.fm.getFragments().contains(UploadReceiptActivity.this.allReceiptFragment))
          paramAnonymousRadioGroup.hide(UploadReceiptActivity.this.allReceiptFragment);
        if (UploadReceiptActivity.this.fm.getFragments().contains(UploadReceiptActivity.this.hasJoinFragment))
          paramAnonymousRadioGroup.show(UploadReceiptActivity.this.hasJoinFragment);
        while (true)
        {
          paramAnonymousRadioGroup.commit();
          return;
          paramAnonymousRadioGroup.add(2131231080, UploadReceiptActivity.this.hasJoinFragment);
        }
      }
    });
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

  public void onClick(View paramView)
  {
    this.fm.beginTransaction();
    switch (paramView.getId())
    {
    default:
      return;
    case 2131231076:
    }
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903100);
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
 * Qualified Name:     com.ismartgo.app.activity.UploadReceiptActivity
 * JD-Core Version:    0.6.2
 */