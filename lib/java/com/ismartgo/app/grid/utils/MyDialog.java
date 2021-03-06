package com.ismartgo.app.grid.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class MyDialog
{
  private Dialog dialog;
  private boolean isClick = false;

  public MyDialog(Context paramContext)
  {
    View localView = LayoutInflater.from(paramContext).inflate(2130903189, null);
    this.dialog = new Dialog(paramContext, 2131427362);
    this.dialog.setContentView(localView, new ViewGroup.LayoutParams(-2, -2));
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
 * Qualified Name:     com.ismartgo.app.grid.utils.MyDialog
 * JD-Core Version:    0.6.2
 */