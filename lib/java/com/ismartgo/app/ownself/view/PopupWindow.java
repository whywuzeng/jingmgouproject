package com.ismartgo.app.ownself.view;

import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class PopupWindow
{
  android.widget.PopupWindow popupWindow;

  public android.widget.PopupWindow makePopupWindow(Context paramContext)
  {
    this.popupWindow = new android.widget.PopupWindow(paramContext);
    Button localButton1 = new Button(paramContext);
    localButton1.setText("first");
    localButton1.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
    Button localButton2 = new Button(paramContext);
    localButton2.setText("Second");
    localButton2.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
    paramContext = new LinearLayout(paramContext);
    paramContext.addView(localButton1);
    paramContext.addView(localButton2);
    paramContext.setOrientation(1);
    this.popupWindow.setContentView(paramContext);
    this.popupWindow.setWidth(150);
    this.popupWindow.setHeight(150);
    this.popupWindow.setFocusable(true);
    this.popupWindow.setTouchable(true);
    this.popupWindow.setOutsideTouchable(true);
    return this.popupWindow;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.ownself.view.PopupWindow
 * JD-Core Version:    0.6.2
 */