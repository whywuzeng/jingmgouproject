package com.ismartgo.app.grid.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.PopupWindow;
import com.ismartgo.app.grid.utils.DisplayUtil;

public class PopwindowUtils
{
  public static PopupWindow getPopwindow(Context paramContext)
  {
    paramContext = new PopupWindow(LayoutInflater.from(paramContext).inflate(2130903149, null), -1, -2);
    paramContext.setBackgroundDrawable(new BitmapDrawable());
    paramContext.setFocusable(true);
    paramContext.setOutsideTouchable(true);
    return paramContext;
  }

  public static PopupWindow remainedPopwindow(Context paramContext, View paramView)
  {
    View localView = LayoutInflater.from(paramContext).inflate(2130903183, null);
    PopupWindow localPopupWindow = new PopupWindow(paramContext);
    localPopupWindow.setWidth(-2);
    localPopupWindow.setHeight(-2);
    localPopupWindow.setOutsideTouchable(true);
    localPopupWindow.setBackgroundDrawable(new BitmapDrawable(paramContext.getResources()));
    localPopupWindow.setFocusable(true);
    localPopupWindow.setTouchable(true);
    localPopupWindow.setTouchInterceptor(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        if ((paramAnonymousMotionEvent.getAction() == 4) && (PopwindowUtils.this != null) && (PopwindowUtils.this.isShowing()))
        {
          PopwindowUtils.this.dismiss();
          return true;
        }
        return false;
      }
    });
    localView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if ((PopwindowUtils.this != null) && (PopwindowUtils.this.isShowing()))
          PopwindowUtils.this.dismiss();
      }
    });
    localPopupWindow.setContentView(localView);
    localPopupWindow.showAtLocation(paramView, 81, 0, DisplayUtil.dip2px(paramContext, 65.0F));
    return localPopupWindow;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.view.PopwindowUtils
 * JD-Core Version:    0.6.2
 */