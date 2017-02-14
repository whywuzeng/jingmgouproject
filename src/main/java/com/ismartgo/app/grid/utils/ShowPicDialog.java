package com.ismartgo.app.grid.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import com.ismartgo.app.grid.view.TouchImageView;
import com.ismartgo.app.grid.view.TouchImageView.OnTouchImageViewListener;
import com.ismartgo.app.tools.ImgLoader;

public class ShowPicDialog extends Dialog
{
  float destHeight;
  float destWidth;
  private boolean isNet;
  private Context mContext;
  private TouchImageView mPicture;
  private String path;

  public ShowPicDialog(Context paramContext, String paramString, boolean paramBoolean)
  {
    super(paramContext, 2131427357);
    this.mContext = paramContext;
    this.path = paramString;
    this.isNet = paramBoolean;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903115);
    this.mPicture = ((TouchImageView)findViewById(2131231114));
    paramBundle = getWindow();
    WindowManager.LayoutParams localLayoutParams = paramBundle.getAttributes();
    localLayoutParams.width = -1;
    localLayoutParams.height = -1;
    paramBundle.setAttributes(localLayoutParams);
    this.destWidth = DisplayUtil.getScreenWidth(this.mContext);
    this.destHeight = DisplayUtil.getScreenHeight(this.mContext);
    if (this.isNet)
    {
      ImgLoader.getInstance(this.mContext).showPic(this.path, this.mPicture, false);
      this.mPicture.setOnTouchImageViewListener(new TouchImageView.OnTouchImageViewListener()
      {
        public void onMove()
        {
        }
      });
      return;
    }
    paramBundle = new BitmapFactory.Options();
    paramBundle.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(this.path, paramBundle);
    float f1 = paramBundle.outWidth;
    float f2 = paramBundle.outHeight;
    int i = 1;
    if ((f2 > this.destHeight) || (f1 > this.destWidth))
      if (f1 <= f2)
        break label241;
    label241: for (i = Math.round(f2 / this.destHeight); ; i = Math.round(f1 / this.destWidth))
    {
      paramBundle = new BitmapFactory.Options();
      paramBundle.inSampleSize = i;
      paramBundle = BitmapFactory.decodeFile(this.path, paramBundle);
      paramBundle = new BitmapDrawable(this.mContext.getResources(), paramBundle);
      this.mPicture.setImageDrawable(paramBundle);
      break;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.utils.ShowPicDialog
 * JD-Core Version:    0.6.2
 */