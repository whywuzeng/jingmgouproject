package com.ismartgo.app.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter
{
  private ArrayList<View> views;

  public ViewPagerAdapter(ArrayList<View> paramArrayList)
  {
    this.views = paramArrayList;
  }

  public void destroyItem(View paramView, int paramInt, Object paramObject)
  {
    ((ViewPager)paramView).removeView((View)this.views.get(paramInt));
  }

  public ArrayList<View> getAdapterData()
  {
    return this.views;
  }

  public int getCount()
  {
    if (this.views != null)
      return this.views.size();
    return 0;
  }

  public Object instantiateItem(View paramView, int paramInt)
  {
    ((ViewPager)paramView).addView((View)this.views.get(paramInt), 0);
    return this.views.get(paramInt);
  }

  public boolean isViewFromObject(View paramView, Object paramObject)
  {
    return paramView == paramObject;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.adapter.ViewPagerAdapter
 * JD-Core Version:    0.6.2
 */