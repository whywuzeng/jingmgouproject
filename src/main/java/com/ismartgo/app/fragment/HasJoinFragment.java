package com.ismartgo.app.fragment;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.astuetz.PagerSlidingTabStrip;
import com.ismartgo.app.grid.view.DecoratorViewPager;
import com.umeng.analytics.MobclickAgent;
import java.util.ArrayList;
import java.util.List;

public class HasJoinFragment extends Fragment
{
  private static final String TAG = "HasJoinFragment";
  private Activity context;
  private List<Fragment> fragmentList;
  private ViewPager mViewPager;
  private PagerSlidingTabStrip tabs;
  private List<String> titleList;

  private void initView(View paramView)
  {
    this.tabs = ((PagerSlidingTabStrip)paramView.findViewById(2131231119));
    this.tabs.setShouldExpand(true);
    this.tabs.setIndicatorHeight(5);
    this.tabs.setIndicatorColorResource(2131099700);
    this.tabs.setTextSize(14);
    this.tabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
    {
      public void onPageScrollStateChanged(int paramAnonymousInt)
      {
      }

      public void onPageScrolled(int paramAnonymousInt1, float paramAnonymousFloat, int paramAnonymousInt2)
      {
      }

      public void onPageSelected(int paramAnonymousInt)
      {
        HasJoinFragment.this.tabs.setTextColorResource(2131099700, paramAnonymousInt);
      }
    });
    this.fragmentList = new ArrayList();
    this.fragmentList.add(new HasJoinAllFragment());
    this.fragmentList.add(new HasJoinPassFragment());
    this.fragmentList.add(new HasJoinUnPassFragment());
    this.titleList = new ArrayList();
    this.titleList.add("全部");
    this.titleList.add("通过");
    this.titleList.add("未通过");
    this.mViewPager = ((DecoratorViewPager)paramView.findViewById(2131230927));
    this.mViewPager.setOffscreenPageLimit(1);
    paramView = getChildFragmentManager();
    this.mViewPager.setAdapter(new HashJoinAdapter(paramView, this.fragmentList, this.titleList));
    int i = (int)TypedValue.applyDimension(1, 4.0F, getResources().getDisplayMetrics());
    this.mViewPager.setPageMargin(i);
    this.tabs.setViewPager(this.mViewPager);
    this.tabs.setTextColorResource(2131099700, 0);
    this.mViewPager.requestDisallowInterceptTouchEvent(true);
  }

  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    this.context = paramActivity;
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    Log.i("HasJoinFragment", "HasJoinFragment onCreateView");
    paramLayoutInflater = paramLayoutInflater.inflate(2130903120, paramViewGroup, false);
    initView(paramLayoutInflater);
    return paramLayoutInflater;
  }

  public void onPause()
  {
    super.onPause();
    MobclickAgent.onPageEnd("HasJoinFragment");
  }

  public void onResume()
  {
    super.onResume();
    MobclickAgent.onPageStart("HasJoinFragment");
  }

  class HashJoinAdapter extends FragmentPagerAdapter
  {
    private List<Fragment> fragmentList;
    private List<String> titleList;

    public HashJoinAdapter(List<Fragment> paramList, List<String> arg3)
    {
      super();
      Object localObject1;
      this.fragmentList = localObject1;
      Object localObject2;
      this.titleList = localObject2;
    }

    public int getCount()
    {
      if (this.fragmentList == null)
        return 0;
      return this.fragmentList.size();
    }

    public Fragment getItem(int paramInt)
    {
      if ((this.fragmentList == null) || (this.fragmentList.size() == 0))
        return null;
      return (Fragment)this.fragmentList.get(paramInt);
    }

    public CharSequence getPageTitle(int paramInt)
    {
      if (this.titleList.size() > paramInt)
        return (String)this.titleList.get(paramInt);
      return "";
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.fragment.HasJoinFragment
 * JD-Core Version:    0.6.2
 */