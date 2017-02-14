package com.mob.tools.gui;

import android.view.View;
import android.view.ViewGroup;

public abstract class ViewPagerAdapter
{
  public abstract int getCount();

  public abstract View getView(int paramInt, ViewGroup paramViewGroup);

  public void onScreenChange(int paramInt1, int paramInt2)
  {
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.gui.ViewPagerAdapter
 * JD-Core Version:    0.6.2
 */