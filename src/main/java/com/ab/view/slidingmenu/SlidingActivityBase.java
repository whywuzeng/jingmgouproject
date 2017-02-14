package com.ab.view.slidingmenu;

import android.view.View;
import android.view.ViewGroup.LayoutParams;

public abstract interface SlidingActivityBase
{
  public abstract SlidingMenu getSlidingMenu();

  public abstract void setBehindContentView(int paramInt);

  public abstract void setBehindContentView(View paramView);

  public abstract void setBehindContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams);

  public abstract void setSlidingActionBarEnabled(boolean paramBoolean);

  public abstract void showContent();

  public abstract void showMenu();

  public abstract void showSecondaryMenu();

  public abstract void toggle();
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.slidingmenu.SlidingActivityBase
 * JD-Core Version:    0.6.2
 */