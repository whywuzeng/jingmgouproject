package com.ismartgo.app.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class PictureActivity extends BaseActivity
  implements View.OnClickListener
{
  private Button btBack;
  private Button btUse;
  private float destHeight;
  private float destWidth;
  private String imgPath;
  private ImageView mPicture;

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131230994:
      paramView = new Intent(this, ModifyReceiptActivity.class);
      paramView.putExtra("intentFlag", 2);
      paramView.putExtra("path", this.imgPath);
      startActivity(paramView);
      finish();
      return;
    case 2131230993:
    }
    startActivity(new Intent(this, CameraActivity1.class));
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    super.onCreate(paramBundle);
    setContentView(2130903079);
    this.mPicture = ((ImageView)findViewById(2131230995));
    this.btBack = ((Button)findViewById(2131230993));
    this.btUse = ((Button)findViewById(2131230994));
    this.btBack.setOnClickListener(this);
    this.btUse.setOnClickListener(this);
    this.imgPath = getIntent().getStringExtra("path");
    paramBundle = getWindowManager().getDefaultDisplay();
    this.destWidth = paramBundle.getWidth();
    this.destHeight = paramBundle.getHeight();
    paramBundle = new BitmapFactory.Options();
    paramBundle.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(this.imgPath, paramBundle);
    float f1 = paramBundle.outWidth;
    float f2 = paramBundle.outHeight;
    int i = 1;
    if ((f2 > this.destHeight) || (f1 > this.destWidth))
      if (f1 <= f2)
        break label243;
    label243: for (i = Math.round(f2 / this.destHeight); ; i = Math.round(f1 / this.destWidth))
    {
      paramBundle = new BitmapFactory.Options();
      paramBundle.inSampleSize = i;
      paramBundle = BitmapFactory.decodeFile(this.imgPath, paramBundle);
      paramBundle = new BitmapDrawable(getResources(), paramBundle);
      this.mPicture.setImageDrawable(paramBundle);
      return;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.PictureActivity
 * JD-Core Version:    0.6.2
 */