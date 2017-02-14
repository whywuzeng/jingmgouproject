package com.ismartgo.app.tools;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;

public class LoginDialog
{
  Dialog dialog;

  public LoginDialog(Context paramContext)
  {
    View localView = LayoutInflater.from(paramContext).inflate(2130903111, null);
    this.dialog = new Dialog(paramContext, 2131427354);
    this.dialog.setContentView(localView, new ViewGroup.LayoutParams(((Activity)paramContext).getWindowManager().getDefaultDisplay().getWidth() / 5 * 4, ((Activity)paramContext).getWindowManager().getDefaultDisplay().getHeight() / 3 * 2));
  }

  public void dismiss()
  {
    this.dialog.dismiss();
  }

  public boolean isShowing()
  {
    return this.dialog.isShowing();
  }

  public void show()
  {
    this.dialog.show();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.tools.LoginDialog
 * JD-Core Version:    0.6.2
 */