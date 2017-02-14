package com.ab.view.sliding;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.ab.adapter.AbFragmentPagerAdapter;
import com.ab.global.AbAppData;
import com.ab.util.AbViewUtil;
import com.ab.view.listener.AbOnScrollListener;
import com.ab.view.sample.AbHorizontalScrollView;
import java.util.ArrayList;
import java.util.List;

public class AbSlidingTabView2 extends LinearLayout
{
  private static final boolean D = AbAppData.DEBUG;
  private static String TAG = "AbSlidingTabView";
  private Context context;
  public LinearLayout.LayoutParams layoutParamsFF = null;
  public LinearLayout.LayoutParams layoutParamsFW = null;
  public LinearLayout.LayoutParams layoutParamsWW = null;
  private AbFragmentPagerAdapter mFragmentPagerAdapter = null;
  private int mSelectedTabIndex = 0;
  private ImageView mTabImg;
  private LinearLayout mTabLayout = null;
  private AbHorizontalScrollView mTabScrollView = null;
  private ViewPager mViewPager;
  private ArrayList<Fragment> pagerItemList = null;
  private int scrollX = 0;
  private int startX = 0;
  private int tabColor = -16777216;
  private ArrayList<TextView> tabItemList = null;
  private List<String> tabItemTextList = null;
  private int tabSelectColor = -16777216;
  private int tabSlidingHeight = 5;
  private int tabTextSize = 16;

  public AbSlidingTabView2(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
    this.layoutParamsFW = new LinearLayout.LayoutParams(-1, -2);
    this.layoutParamsFF = new LinearLayout.LayoutParams(-1, -1);
    this.layoutParamsWW = new LinearLayout.LayoutParams(-2, -2);
    setOrientation(1);
    setBackgroundColor(Color.rgb(255, 255, 255));
    this.mTabScrollView = new AbHorizontalScrollView(paramContext);
    this.mTabScrollView.setHorizontalScrollBarEnabled(false);
    this.mTabLayout = new LinearLayout(paramContext);
    this.mTabLayout.setOrientation(0);
    this.mTabLayout.setGravity(17);
    this.mTabScrollView.addView(this.mTabLayout, new ViewGroup.LayoutParams(-2, -1));
    this.tabItemList = new ArrayList();
    this.tabItemTextList = new ArrayList();
    addView(this.mTabScrollView, this.layoutParamsFW);
    this.mTabImg = new ImageView(paramContext);
    addView(this.mTabImg, new LinearLayout.LayoutParams(-2, this.tabSlidingHeight));
    this.mViewPager = new ViewPager(paramContext);
    this.mViewPager.setId(1985);
    this.pagerItemList = new ArrayList();
    addView(this.mViewPager, this.layoutParamsFF);
    if (!(this.context instanceof FragmentActivity))
      Log.e(TAG, "构造AbSlidingTabView的参数context,必须是FragmentActivity的实例。");
    this.mFragmentPagerAdapter = new AbFragmentPagerAdapter(((FragmentActivity)this.context).getSupportFragmentManager(), this.pagerItemList);
    this.mViewPager.setAdapter(this.mFragmentPagerAdapter);
    this.mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    this.mViewPager.setOffscreenPageLimit(3);
    this.mTabScrollView.setSmoothScrollingEnabled(true);
    this.mTabScrollView.setOnScrollListener(new AbOnScrollListener()
    {
      public void onScroll(int paramAnonymousInt)
      {
        AbSlidingTabView2.this.scrollX = paramAnonymousInt;
        paramAnonymousInt = AbSlidingTabView2.this.mTabLayout.getChildAt(AbSlidingTabView2.this.mSelectedTabIndex).getLeft() - AbSlidingTabView2.this.scrollX;
        if (AbSlidingTabView2.D)
          Log.d(AbSlidingTabView2.TAG, "滑动X" + AbSlidingTabView2.this.startX + "to" + paramAnonymousInt);
        AbSlidingTabView2.this.imageSlide(AbSlidingTabView2.this.mTabImg, AbSlidingTabView2.this.startX, paramAnonymousInt, 0, 0);
        AbSlidingTabView2.this.startX = paramAnonymousInt;
      }

      public void onScrollStoped()
      {
        if (AbSlidingTabView2.D)
          Log.d(AbSlidingTabView2.TAG, "onScrollStoped");
      }

      public void onScrollToLeft()
      {
        if (AbSlidingTabView2.D)
          Log.d(AbSlidingTabView2.TAG, "onScrollToLeft");
      }

      public void onScrollToRight()
      {
        if (AbSlidingTabView2.D)
          Log.d(AbSlidingTabView2.TAG, "onScrollToRight");
      }
    });
  }

  public void addItemView(String paramString, Fragment paramFragment)
  {
    this.tabItemTextList.add(paramString);
    this.pagerItemList.add(paramFragment);
    this.tabItemList.clear();
    this.mTabLayout.removeAllViews();
    final int i = 0;
    while (true)
    {
      if (i >= this.tabItemTextList.size())
      {
        Log.d(TAG, "addItemView finish");
        this.mFragmentPagerAdapter.notifyDataSetChanged();
        this.mViewPager.setCurrentItem(0);
        computeTabImg(0);
        return;
      }
      paramString = (String)this.tabItemTextList.get(i);
      paramFragment = new TextView(this.context);
      paramFragment.setTextColor(this.tabColor);
      paramFragment.setTextSize(this.tabTextSize);
      paramFragment.setText(paramString);
      paramFragment.setGravity(17);
      paramFragment.setLayoutParams(new LinearLayout.LayoutParams(0, -1, 1.0F));
      paramFragment.setPadding(12, 5, 12, 5);
      paramFragment.setFocusable(false);
      this.tabItemList.add(paramFragment);
      this.mTabLayout.addView(paramFragment);
      paramFragment.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          AbSlidingTabView2.this.mViewPager.setCurrentItem(i);
        }
      });
      i += 1;
    }
  }

  public void addItemViews(List<String> paramList, List<Fragment> paramList1)
  {
    this.tabItemTextList.addAll(paramList);
    this.pagerItemList.addAll(paramList1);
    this.tabItemList.clear();
    this.mTabLayout.removeAllViews();
    final int i = 0;
    while (true)
    {
      if (i >= this.tabItemTextList.size())
      {
        this.mFragmentPagerAdapter.notifyDataSetChanged();
        this.mViewPager.setCurrentItem(0);
        computeTabImg(0);
        return;
      }
      paramList = (String)this.tabItemTextList.get(i);
      paramList1 = new TextView(this.context);
      paramList1.setTextColor(this.tabColor);
      paramList1.setTextSize(this.tabTextSize);
      paramList1.setText(paramList);
      paramList1.setGravity(17);
      paramList1.setLayoutParams(new LinearLayout.LayoutParams(0, -1, 1.0F));
      paramList1.setPadding(12, 5, 12, 5);
      paramList1.setFocusable(false);
      this.tabItemList.add(paramList1);
      this.mTabLayout.addView(paramList1);
      paramList1.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          AbSlidingTabView2.this.mViewPager.setCurrentItem(i);
        }
      });
      i += 1;
    }
  }

  public void computeTabImg(int paramInt)
  {
    int i = 0;
    Object localObject;
    if (i >= this.tabItemList.size())
    {
      localObject = this.mTabLayout.getChildAt(paramInt);
      AbViewUtil.measureView((View)localObject);
      LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(((View)localObject).getMeasuredWidth(), this.tabSlidingHeight);
      localLayoutParams.topMargin = (-this.tabSlidingHeight);
      this.mTabImg.setLayoutParams(localLayoutParams);
      if (D)
        Log.d(TAG, "old--startX:" + this.startX);
      if (D)
        Log.d(TAG, "view宽度" + paramInt + ":" + ((View)localObject).getMeasuredWidth());
      if (D)
        Log.d(TAG, "ScrollView宽度:" + this.mTabScrollView.getWidth());
      if (D)
        Log.d(TAG, "scrollX:" + this.scrollX);
      if (D)
        Log.d(TAG, "tabView right:" + ((View)localObject).getRight());
      if (D)
        Log.d(TAG, "tabView left:" + ((View)localObject).getLeft());
      if ((this.mSelectedTabIndex >= paramInt) || (((View)localObject).getRight() - this.scrollX <= this.mTabScrollView.getWidth()))
        break label607;
      if (D)
        Log.d(TAG, "右边被遮挡");
      if (paramInt != this.mTabLayout.getChildCount() - 1)
        break label507;
      i = ((View)localObject).getRight() - this.mTabScrollView.getWidth() - this.scrollX;
      this.mTabScrollView.smoothScrollBy(i, 0);
      this.scrollX += i;
      if (D)
        Log.d(TAG, "startX:" + this.startX + ",offsetX:" + i);
      imageSlide(this.mTabImg, this.startX, this.mTabScrollView.getWidth() - ((View)localObject).getMeasuredWidth(), 0, 0);
      this.startX = (this.mTabScrollView.getWidth() - ((View)localObject).getMeasuredWidth());
    }
    while (true)
    {
      this.mSelectedTabIndex = paramInt;
      return;
      localObject = (TextView)this.tabItemList.get(i);
      ((TextView)localObject).setTextColor(this.tabColor);
      ((TextView)localObject).setSelected(false);
      if (paramInt == i)
      {
        ((TextView)localObject).setTextColor(this.tabSelectColor);
        ((TextView)localObject).setSelected(true);
      }
      i += 1;
      break;
      label507: i = ((View)localObject).getMeasuredWidth();
      this.mTabScrollView.smoothScrollBy(i, 0);
      this.scrollX += i;
      if (D)
        Log.d(TAG, "startX:" + this.startX + ",offsetX:" + i);
      i = ((View)localObject).getLeft() - this.scrollX;
      imageSlide(this.mTabImg, this.startX, i, 0, 0);
      this.startX = i;
      continue;
      label607: if ((this.mSelectedTabIndex > paramInt) && (((View)localObject).getLeft() < this.scrollX))
      {
        if (D)
          Log.d(TAG, "左边被遮挡");
        if (paramInt == 0)
        {
          i = -this.scrollX;
          this.mTabScrollView.smoothScrollBy(i, 0);
          this.scrollX += i;
          imageSlide(this.mTabImg, this.startX, 0, 0, 0);
          this.startX = 0;
        }
        else
        {
          i = -((View)localObject).getMeasuredWidth();
          this.mTabScrollView.smoothScrollBy(i, 0);
          this.scrollX += i;
          if (D)
            Log.d(TAG, "startX2:" + this.startX + ",offsetX:" + i);
          i = ((View)localObject).getLeft() - this.scrollX;
          imageSlide(this.mTabImg, this.startX, i, 0, 0);
          this.startX = i;
        }
      }
      else
      {
        i = ((View)localObject).getLeft() - this.scrollX;
        imageSlide(this.mTabImg, this.startX, i, 0, 0);
        this.startX = i;
      }
    }
  }

  public int getTabColor()
  {
    return this.tabColor;
  }

  public LinearLayout getTabLayout()
  {
    return this.mTabLayout;
  }

  public int getTabSlidingHeight()
  {
    return this.tabSlidingHeight;
  }

  public int getTabTextSize()
  {
    return this.tabTextSize;
  }

  public ViewPager getViewPager()
  {
    return this.mViewPager;
  }

  public void imageSlide(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(paramInt1, paramInt2, paramInt3, paramInt4);
    localTranslateAnimation.setDuration(100L);
    localTranslateAnimation.setFillAfter(true);
    paramView.startAnimation(localTranslateAnimation);
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
  }

  public void removeAllItemView(int paramInt)
  {
    this.tabItemList.clear();
    this.mTabLayout.removeAllViews();
    this.pagerItemList.clear();
    this.mFragmentPagerAdapter.notifyDataSetChanged();
  }

  public void removeItemView(int paramInt)
  {
    this.tabItemList.remove(paramInt);
    this.mTabLayout.removeViewAt(paramInt);
    this.pagerItemList.remove(paramInt);
    this.mFragmentPagerAdapter.notifyDataSetChanged();
  }

  public void setTabColor(int paramInt)
  {
    this.tabColor = paramInt;
  }

  public void setTabLayoutBackground(int paramInt)
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
    this.mTabImg.setBackgroundColor(paramInt);
  }

  public void setTabSlidingHeight(int paramInt)
  {
    this.tabSlidingHeight = paramInt;
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
    }

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
    {
    }

    public void onPageSelected(int paramInt)
    {
      AbSlidingTabView2.this.computeTabImg(paramInt);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.sliding.AbSlidingTabView2
 * JD-Core Version:    0.6.2
 */