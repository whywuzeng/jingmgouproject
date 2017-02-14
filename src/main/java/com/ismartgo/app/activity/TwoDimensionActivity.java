package com.ismartgo.app.activity;

import android.os.Bundle;
import android.widget.TextView;

public class TwoDimensionActivity extends BaseActivity
{
  private void initView()
  {
    initTitleBar();
    this.tv_title.setText("扫描下载");
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903098);
    initView();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.TwoDimensionActivity
 * JD-Core Version:    0.6.2
 */