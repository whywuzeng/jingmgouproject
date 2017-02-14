package com.umeng.update;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import com.alimama.mobile.MMAdView;
import java.io.File;
import u.upd.a;
import u.upd.c;

public class UpdateDialogActivity extends Activity
{
  int a = 6;
  UpdateResponse b;
  boolean c = false;
  File d = null;
  ViewGroup e;
  MMAdView f;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(c.a(this).d("umeng_update_dialog"));
    this.b = ((UpdateResponse)getIntent().getExtras().getSerializable("response"));
    paramBundle = getIntent().getExtras().getString("file");
    boolean bool2 = getIntent().getExtras().getBoolean("force");
    boolean bool1;
    int j;
    int k;
    final int m;
    int n;
    final int i1;
    int i2;
    int i3;
    label262: Object localObject;
    if (paramBundle != null)
    {
      bool1 = true;
      if (bool1)
        this.d = new File(paramBundle);
      j = c.a(this).b("umeng_update_content");
      k = c.a(this).b("umeng_update_wifi_indicator");
      m = c.a(this).b("umeng_update_id_ok");
      n = c.a(this).b("umeng_update_id_cancel");
      i1 = c.a(this).b("umeng_update_id_ignore");
      i2 = c.a(this).b("umeng_update_id_close");
      i3 = c.a(this).b("umeng_update_id_check");
      this.e = ((ViewGroup)findViewById(c.a(this).b("umeng_update_frame")));
      if ((this.e != null) && (this.b.display_ads))
      {
        paramBundle = UpdateConfig.getSlotId();
        if (TextUtils.isEmpty(paramBundle))
          break label420;
        this.f = new MMAdView(this);
        if (this.f.init(paramBundle))
          this.e.addView(this.f, new ViewGroup.LayoutParams(-1, -2));
      }
      paramBundle = new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (m == paramAnonymousView.getId())
            UpdateDialogActivity.this.a = 5;
          while (true)
          {
            UpdateDialogActivity.this.finish();
            return;
            if (i1 == paramAnonymousView.getId())
              UpdateDialogActivity.this.a = 7;
            else if (UpdateDialogActivity.this.c)
              UpdateDialogActivity.this.a = 7;
          }
        }
      };
      localObject = new CompoundButton.OnCheckedChangeListener()
      {
        public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
        {
          UpdateDialogActivity.this.c = paramAnonymousBoolean;
        }
      };
      if (k > 0)
        if (!a.k(this))
          break label431;
    }
    label420: label431: for (int i = 8; ; i = 0)
    {
      findViewById(k).setVisibility(i);
      if (bool2)
        findViewById(i3).setVisibility(8);
      findViewById(m).setOnClickListener(paramBundle);
      findViewById(n).setOnClickListener(paramBundle);
      findViewById(i1).setOnClickListener(paramBundle);
      findViewById(i2).setOnClickListener(paramBundle);
      ((CheckBox)findViewById(i3)).setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener)localObject);
      paramBundle = this.b.a(this, bool1);
      localObject = (TextView)findViewById(j);
      ((TextView)localObject).requestFocus();
      ((TextView)localObject).setText(paramBundle);
      return;
      bool1 = false;
      break;
      Log.w("update", "尚未设置推广位id,无法展示推广内容。");
      break label262;
    }
  }

  protected void onDestroy()
  {
    super.onDestroy();
    UmengUpdateAgent.a(this.a, this, this.b, this.d);
    if (this.f != null)
      this.f.destroy();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.update.UpdateDialogActivity
 * JD-Core Version:    0.6.2
 */