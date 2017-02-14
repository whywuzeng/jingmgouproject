package com.ab.view.listener;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

public abstract interface AbOnPageChangeListener extends ViewPager.OnPageChangeListener
{
  public abstract void notifyDataSetChanged();

  public abstract void setCurrentItem(int paramInt);

  public abstract void setOnPageChangeListener(ViewPager.OnPageChangeListener paramOnPageChangeListener);

  public abstract void setViewPager(ViewPager paramViewPager);

  public abstract void setViewPager(ViewPager paramViewPager, int paramInt);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.listener.AbOnPageChangeListener
 * JD-Core Version:    0.6.2
 */