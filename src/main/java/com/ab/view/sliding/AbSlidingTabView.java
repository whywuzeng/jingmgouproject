package com.ab.view.sliding;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.ab.adapter.AbFragmentPagerAdapter;
import com.ab.global.AbAppData;
import java.util.ArrayList;
import java.util.List;

public class AbSlidingTabView extends LinearLayout
{
  private static final boolean D = AbAppData.DEBUG;
  private static String TAG = "AbSlidingTabView";
  private Context context;
  private AbFragmentPagerAdapter mFragmentPagerAdapter = null;
  private ViewPager.OnPageChangeListener mListener;
  public int mMaxTabWidth;
  private int mSelectedTabIndex;
  private View.OnClickListener mTabClickListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      paramAnonymousView = (AbTabItemView)paramAnonymousView;
      AbSlidingTabView.this.mViewPager.setCurrentItem(paramAnonymousView.getIndex());
    }
  };
  private LinearLayout mTabLayout = null;
  private HorizontalScrollView mTabScrollView = null;
  private Runnable mTabSelector;
  private ViewPager mViewPager;
  private ArrayList<Fragment> pagerItemList = null;
  private int tabBackgroundResource = -1;
  private List<Drawable> tabItemDrawableList = null;
  private ArrayList<TextView> tabItemList = null;
  private List<String> tabItemTextList = null;
  private int tabSelectColor = -16777216;
  private int tabTextColor = -16777216;
  private int tabTextSize = 16;

  public AbSlidingTabView(Context paramContext)
  {
    this(paramContext, null);
  }

  public AbSlidingTabView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
    setOrientation(1);
    setBackgroundColor(Color.rgb(255, 255, 255));
    this.mTabScrollView = new HorizontalScrollView(paramContext);
    this.mTabScrollView.setHorizontalScrollBarEnabled(false);
    this.mTabScrollView.setSmoothScrollingEnabled(true);
    this.mTabLayout = new LinearLayout(paramContext);
    this.mTabLayout.setOrientation(0);
    this.mTabLayout.setGravity(17);
    this.mTabScrollView.addView(this.mTabLayout, new ViewGroup.LayoutParams(-2, -1));
    addView(this.mTabScrollView, new ViewGroup.LayoutParams(-1, -2));
    this.mViewPager = new ViewPager(paramContext);
    this.mViewPager.setId(1985);
    this.pagerItemList = new ArrayList();
    this.tabItemList = new ArrayList();
    this.tabItemTextList = new ArrayList();
    this.tabItemDrawableList = new ArrayList();
    if (!(this.context instanceof FragmentActivity))
      Log.e(TAG, "构造AbSlidingTabView的参数context,必须是FragmentActivity的实例。");
    this.mFragmentPagerAdapter = new AbFragmentPagerAdapter(((FragmentActivity)this.context).getSupportFragmentManager(), this.pagerItemList);
    this.mViewPager.setAdapter(this.mFragmentPagerAdapter);
    this.mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    this.mViewPager.setOffscreenPageLimit(3);
    addView(this.mViewPager, new LinearLayout.LayoutParams(-1, -1));
  }

  private void addTab(String paramString, int paramInt)
  {
    addTab(paramString, paramInt, null);
  }

  private void addTab(String paramString, int paramInt, Drawable paramDrawable)
  {
    AbTabItemView localAbTabItemView = new AbTabItemView(this.context);
    if (this.tabBackgroundResource != -1)
      localAbTabItemView.setTabBackgroundResource(this.tabBackgroundResource);
    if (paramDrawable != null)
      localAbTabItemView.setTabCompoundDrawables(null, paramDrawable, null, null);
    localAbTabItemView.setTabTextColor(this.tabTextColor);
    localAbTabItemView.setTabTextSize(this.tabTextSize);
    localAbTabItemView.init(paramInt, paramString);
    this.tabItemList.add(localAbTabItemView.getTextView());
    localAbTabItemView.setOnClickListener(this.mTabClickListener);
    this.mTabLayout.addView(localAbTabItemView, new LinearLayout.LayoutParams(0, -1, 1.0F));
  }

  private void animateToTab(int paramInt)
  {
    final View localView = this.mTabLayout.getChildAt(paramInt);
    if (this.mTabSelector != null)
      removeCallbacks(this.mTabSelector);
    this.mTabSelector = new Runnable()
    {
      public void run()
      {
        int i = localView.getLeft();
        int j = (AbSlidingTabView.this.getWidth() - localView.getWidth()) / 2;
        AbSlidingTabView.this.mTabScrollView.smoothScrollTo(i - j, 0);
        AbSlidingTabView.this.mTabSelector = null;
      }
    };
    post(this.mTabSelector);
  }

  public void addItemView(String paramString, Drawable paramDrawable, Fragment paramFragment)
  {
    this.tabItemTextList.add(paramString);
    this.pagerItemList.add(paramFragment);
    this.tabItemDrawableList.add(paramDrawable);
    this.mFragmentPagerAdapter.notifyDataSetChanged();
    notifyTabDataSetChanged();
  }

  public void addItemView(String paramString, Fragment paramFragment)
  {
    this.tabItemTextList.add(paramString);
    this.pagerItemList.add(paramFragment);
    this.mFragmentPagerAdapter.notifyDataSetChanged();
    notifyTabDataSetChanged();
  }

  public void addItemViews(List<String> paramList, List<Fragment> paramList1)
  {
    this.tabItemTextList.addAll(paramList);
    this.pagerItemList.addAll(paramList1);
    this.mFragmentPagerAdapter.notifyDataSetChanged();
    notifyTabDataSetChanged();
  }

  public void addItemViews(List<String> paramList, List<Drawable> paramList1, List<Fragment> paramList2)
  {
    this.tabItemTextList.addAll(paramList);
    this.pagerItemList.addAll(paramList2);
    this.tabItemDrawableList.addAll(paramList1);
    this.mFragmentPagerAdapter.notifyDataSetChanged();
    notifyTabDataSetChanged();
  }

  public ViewPager getViewPager()
  {
    return this.mViewPager;
  }

  public void notifyTabDataSetChanged()
  {
    this.mTabLayout.removeAllViews();
    this.tabItemList.clear();
    int j = this.mFragmentPagerAdapter.getCount();
    int i = 0;
    if (i >= j)
    {
      if (this.mSelectedTabIndex > j)
        this.mSelectedTabIndex = (j - 1);
      setCurrentItem(this.mSelectedTabIndex);
      requestLayout();
      return;
    }
    if (this.tabItemDrawableList.size() > 0)
      addTab((String)this.tabItemTextList.get(i), i, (Drawable)this.tabItemDrawableList.get(i));
    while (true)
    {
      i += 1;
      break;
      addTab((String)this.tabItemTextList.get(i), i);
    }
  }

  public void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if (this.mTabSelector != null)
      post(this.mTabSelector);
  }

  public void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (this.mTabSelector != null)
      removeCallbacks(this.mTabSelector);
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getMode(paramInt1);
    boolean bool;
    if (i == 1073741824)
    {
      bool = true;
      this.mTabScrollView.setFillViewport(bool);
      int j = this.mTabLayout.getChildCount();
      if ((j <= 1) || ((i != 1073741824) && (i != -2147483648)))
        break label127;
      if (j <= 2)
        break label114;
      this.mMaxTabWidth = ((int)(View.MeasureSpec.getSize(paramInt1) * 0.4F));
    }
    while (true)
    {
      i = getMeasuredWidth();
      super.onMeasure(paramInt1, paramInt2);
      paramInt1 = getMeasuredWidth();
      if ((bool) && (i != paramInt1))
        setCurrentItem(this.mSelectedTabIndex);
      return;
      bool = false;
      break;
      label114: this.mMaxTabWidth = (View.MeasureSpec.getSize(paramInt1) / 2);
      continue;
      label127: this.mMaxTabWidth = -1;
    }
  }

  public void removeAllItemViews()
  {
    this.mTabLayout.removeAllViews();
    this.pagerItemList.clear();
    this.tabItemList.clear();
    this.tabItemDrawableList.clear();
    this.mFragmentPagerAdapter.notifyDataSetChanged();
    notifyTabDataSetChanged();
  }

  public void removeItemView(int paramInt)
  {
    this.mTabLayout.removeViewAt(paramInt);
    this.pagerItemList.remove(paramInt);
    this.tabItemList.remove(paramInt);
    this.tabItemDrawableList.remove(paramInt);
    this.mFragmentPagerAdapter.notifyDataSetChanged();
    notifyTabDataSetChanged();
  }

  public void setCurrentItem(int paramInt)
  {
    if (this.mViewPager == null)
      throw new IllegalStateException("ViewPager has not been bound.");
    this.mSelectedTabIndex = paramInt;
    int j = this.mTabLayout.getChildCount();
    int i = 0;
    if (i >= j)
      return;
    AbTabItemView localAbTabItemView = (AbTabItemView)this.mTabLayout.getChildAt(i);
    boolean bool;
    if (i == paramInt)
    {
      bool = true;
      label60: localAbTabItemView.setSelected(bool);
      if (!bool)
        break label99;
      localAbTabItemView.setTabTextColor(this.tabSelectColor);
      animateToTab(paramInt);
    }
    while (true)
    {
      i += 1;
      break;
      bool = false;
      break label60;
      label99: localAbTabItemView.setTabTextColor(this.tabTextColor);
    }
  }

  public void setOnPageChangeListener(ViewPager.OnPageChangeListener paramOnPageChangeListener)
  {
    this.mListener = paramOnPageChangeListener;
  }

  public void setTabBackgroundResource(int paramInt)
  {
    this.tabBackgroundResource = paramInt;
  }

  public void setTabLayoutBackgroundResource(int paramInt)
  {
    this.mTabLayout.setBackgroundResource(paramInt);
  }

  public void setTabPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = 0;
    while (true)
    {
      if (i >= this.tabItemList.size())
        return;
      ((TextView)this.tabItemList.get(i)).setPadding(paramInt1, paramInt2, paramInt3, paramInt4);
      i += 1;
    }
  }

  public void setTabSelectColor(int paramInt)
  {
    this.tabSelectColor = paramInt;
  }

  public void setTabTextColor(int paramInt)
  {
    this.tabTextColor = paramInt;
  }

  public void setTabTextSize(int paramInt)
  {
    this.tabTextSize = paramInt;
  }

  public class MyOnPageChangeListener
    implements ViewPager.OnPageChangeListener
  {
    public MyOnPageChangeListener()
    {
    }

    public void onPageScrollStateChanged(int paramInt)
    {
      if (AbSlidingTabView.this.mListener != null)
        AbSlidingTabView.this.mListener.onPageScrollStateChanged(paramInt);
    }

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
    {
      if (AbSlidingTabView.this.mListener != null)
        AbSlidingTabView.this.mListener.onPageScrolled(paramInt1, paramFloat, paramInt2);
    }

    public void onPageSelected(int paramInt)
    {
      AbSlidingTabView.this.setCurrentItem(paramInt);
      if (AbSlidingTabView.this.mListener != null)
        AbSlidingTabView.this.mListener.onPageSelected(paramInt);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.sliding.AbSlidingTabView
 * JD-Core Version:    0.6.2
 */