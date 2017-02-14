package com.ab.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;

public class AbViewPagerAdapter extends PagerAdapter
{
  private Context mContext;
  private ArrayList<View> mListViews = null;
  private HashMap<Integer, View> mViews = null;

  public AbViewPagerAdapter(Context paramContext, ArrayList<View> paramArrayList)
  {
    this.mContext = paramContext;
    this.mListViews = paramArrayList;
    this.mViews = new HashMap();
  }

  public void destroyItem(View paramView, int paramInt, Object paramObject)
  {
    ((ViewPager)paramView).removeView((View)paramObject);
  }

  public int getCount()
  {
    return this.mListViews.size();
  }

  public int getItemPosition(Object paramObject)
  {
    return -2;
  }

  public Object instantiateItem(View paramView, int paramInt)
  {
    View localView = (View)this.mListViews.get(paramInt);
    ((ViewPager)paramView).addView(localView);
    return localView;
  }

  public boolean isViewFromObject(View paramView, Object paramObject)
  {
    return paramView == paramObject;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.adapter.AbViewPagerAdapter
 * JD-Core Version:    0.6.2
 */