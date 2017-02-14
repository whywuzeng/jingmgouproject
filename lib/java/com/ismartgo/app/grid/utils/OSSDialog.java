package com.ismartgo.app.grid.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

public class OSSDialog
{
  private AnimationDrawable animationDrawable;
  private Dialog dialog;
  private boolean isClick = false;

  public OSSDialog(Context paramContext)
  {
    View localView = View.inflate(paramContext, 2130903113, null);
    ImageView localImageView = (ImageView)localView.findViewById(2131231107);
    localImageView.setImageResource(2130837658);
    this.animationDrawable = ((AnimationDrawable)localImageView.getDrawable());
    this.animationDrawable.start();
    this.dialog = new Dialog(paramContext, 2131427362);
    this.dialog.setContentView(localView, new ViewGroup.LayoutParams(DisplayUtil.dip2px(paramContext, 125.0F), DisplayUtil.dip2px(paramContext, 125.0F)));
    this.dialog.setCancelable(true);
  }

  public void dismiss()
  {
    if ((this.dialog != null) && (this.dialog.isShowing()))
      this.dialog.dismiss();
  }

  public boolean getIsClick()
  {
    return this.isClick;
  }

  public boolean isShowing()
  {
    return this.dialog.isShowing();
  }

  public void setCancelable(boolean paramBoolean)
  {
    if (this.dialog != null)
      this.dialog.setCancelable(paramBoolean);
  }

  public void setIsClick(boolean paramBoolean)
  {
    this.isClick = paramBoolean;
  }

  public void show()
  {
    if ((this.dialog != null) && (!this.dialog.isShowing()))
      this.dialog.show();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.utils.OSSDialog
 * JD-Core Version:    0.6.2
 */