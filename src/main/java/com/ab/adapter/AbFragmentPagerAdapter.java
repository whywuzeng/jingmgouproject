package com.ab.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;

public class AbFragmentPagerAdapter extends FragmentPagerAdapter
{
  private ArrayList<Fragment> mFragmentList = null;

  public AbFragmentPagerAdapter(FragmentManager paramFragmentManager, ArrayList<Fragment> paramArrayList)
  {
    super(paramFragmentManager);
    this.mFragmentList = paramArrayList;
  }

  public int getCount()
  {
    return this.mFragmentList.size();
  }

  public Fragment getItem(int paramInt)
  {
    if (paramInt < this.mFragmentList.size())
      return (Fragment)this.mFragmentList.get(paramInt);
    return (Fragment)this.mFragmentList.get(0);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.adapter.AbFragmentPagerAdapter
 * JD-Core Version:    0.6.2
 */