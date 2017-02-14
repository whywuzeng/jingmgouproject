package com.ab.view.sliding;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import com.ab.adapter.AbViewPagerAdapter;
import com.ab.global.AbAppData;
import com.ab.util.AbFileUtil;
import com.ab.view.listener.AbOnChangeListener;
import com.ab.view.listener.AbOnItemClickListener;
import com.ab.view.listener.AbOnScrollListener;
import com.ab.view.listener.AbOnTouchListener;
import com.ab.view.sample.AbInnerViewPager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AbSlidingPlayView extends LinearLayout
{
  private static final boolean D = AbAppData.DEBUG;
  private static String TAG = "AbSlidingPlayView";
  private Context context;
  private int count;
  private Bitmap displayImage;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      int i;
      int j;
      if (paramAnonymousMessage.what == 0)
      {
        i = AbSlidingPlayView.this.mListViews.size();
        j = AbSlidingPlayView.this.mViewPager.getCurrentItem();
        if (AbSlidingPlayView.this.playingDirection != 0)
          break label109;
        if (j != i - 1)
          break label102;
        AbSlidingPlayView.this.playingDirection = -1;
        i = j - 1;
      }
      while (true)
      {
        AbSlidingPlayView.this.mViewPager.setCurrentItem(i, true);
        if (AbSlidingPlayView.this.play)
          AbSlidingPlayView.this.handler.postDelayed(AbSlidingPlayView.this.runnable, 5000L);
        return;
        label102: i = j + 1;
        continue;
        label109: if (j == 0)
        {
          AbSlidingPlayView.this.playingDirection = 0;
          i = j + 1;
        }
        else
        {
          i = j - 1;
        }
      }
    }
  };
  private Bitmap hideImage;
  public LinearLayout.LayoutParams layoutParamsFF = null;
  public LinearLayout.LayoutParams layoutParamsFW = null;
  public LinearLayout.LayoutParams layoutParamsWF = null;
  private AbOnChangeListener mAbChangeListener;
  private AbOnTouchListener mAbOnTouchListener;
  private AbOnScrollListener mAbScrolledListener;
  private AbViewPagerAdapter mAbViewPagerAdapter = null;
  private ArrayList<View> mListViews = null;
  private AbOnItemClickListener mOnItemClickListener;
  private LinearLayout mPageLineLayoutParent = null;
  private AbInnerViewPager mViewPager;
  private int pageLineHorizontalGravity = 5;
  private LinearLayout pageLineLayout;
  public LinearLayout.LayoutParams pageLineLayoutParams = null;
  private boolean play = false;
  private int playingDirection = 0;
  private int position;
  private Runnable runnable = new Runnable()
  {
    public void run()
    {
      if (AbSlidingPlayView.this.mViewPager != null)
        AbSlidingPlayView.this.handler.sendEmptyMessage(0);
    }
  };

  public AbSlidingPlayView(Context paramContext)
  {
    super(paramContext);
    initView(paramContext);
  }

  public AbSlidingPlayView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView(paramContext);
  }

  private void onPageScrolledCallBack(int paramInt)
  {
    if (this.mAbScrolledListener != null)
      this.mAbScrolledListener.onScroll(paramInt);
  }

  private void onPageSelectedCallBack(int paramInt)
  {
    if (this.mAbChangeListener != null)
      this.mAbChangeListener.onChange(paramInt);
  }

  public void addView(View paramView)
  {
    this.mListViews.add(paramView);
    if (!(paramView instanceof AbsListView))
    {
      paramView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (AbSlidingPlayView.this.mOnItemClickListener != null)
            AbSlidingPlayView.this.mOnItemClickListener.onClick(AbSlidingPlayView.this.position);
        }
      });
      paramView.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          if (AbSlidingPlayView.this.mAbOnTouchListener != null)
            AbSlidingPlayView.this.mAbOnTouchListener.onTouch(paramAnonymousMotionEvent);
          return false;
        }
      });
    }
    this.mAbViewPagerAdapter.notifyDataSetChanged();
    creatIndex();
  }

  public void addViews(List<View> paramList)
  {
    this.mListViews.addAll(paramList);
    paramList = paramList.iterator();
    while (true)
    {
      if (!paramList.hasNext())
      {
        this.mAbViewPagerAdapter.notifyDataSetChanged();
        creatIndex();
        return;
      }
      View localView = (View)paramList.next();
      localView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (AbSlidingPlayView.this.mOnItemClickListener != null)
            AbSlidingPlayView.this.mOnItemClickListener.onClick(AbSlidingPlayView.this.position);
        }
      });
      localView.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          if (AbSlidingPlayView.this.mAbOnTouchListener != null)
            AbSlidingPlayView.this.mAbOnTouchListener.onTouch(paramAnonymousMotionEvent);
          return false;
        }
      });
    }
  }

  public void creatIndex()
  {
    this.pageLineLayout.removeAllViews();
    this.mPageLineLayoutParent.setHorizontalGravity(this.pageLineHorizontalGravity);
    this.pageLineLayout.setGravity(17);
    this.pageLineLayout.setVisibility(0);
    this.count = this.mListViews.size();
    int i = 0;
    if (i >= this.count)
      return;
    ImageView localImageView = new ImageView(this.context);
    this.pageLineLayoutParams.setMargins(5, 5, 5, 5);
    localImageView.setLayoutParams(this.pageLineLayoutParams);
    if (i == 0)
      localImageView.setImageBitmap(this.displayImage);
    while (true)
    {
      this.pageLineLayout.addView(localImageView, i);
      i += 1;
      break;
      localImageView.setImageBitmap(this.hideImage);
    }
  }

  public int getCount()
  {
    return this.mListViews.size();
  }

  public ViewPager getViewPager()
  {
    return this.mViewPager;
  }

  public void initView(Context paramContext)
  {
    this.context = paramContext;
    this.layoutParamsFF = new LinearLayout.LayoutParams(-1, -1);
    this.layoutParamsFW = new LinearLayout.LayoutParams(-1, -2);
    this.layoutParamsWF = new LinearLayout.LayoutParams(-2, -1);
    this.pageLineLayoutParams = new LinearLayout.LayoutParams(-2, -2);
    setOrientation(1);
    setBackgroundColor(Color.rgb(255, 255, 255));
    RelativeLayout localRelativeLayout = new RelativeLayout(paramContext);
    this.mViewPager = new AbInnerViewPager(paramContext);
    this.mViewPager.setId(1985);
    this.mPageLineLayoutParent = new LinearLayout(paramContext);
    this.mPageLineLayoutParent.setPadding(0, 5, 0, 5);
    this.pageLineLayout = new LinearLayout(paramContext);
    this.pageLineLayout.setPadding(15, 1, 15, 1);
    this.pageLineLayout.setVisibility(4);
    this.mPageLineLayoutParent.addView(this.pageLineLayout, new LinearLayout.LayoutParams(-2, -2));
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.addRule(12, -1);
    localLayoutParams.addRule(14, -1);
    localLayoutParams.addRule(15, -1);
    localRelativeLayout.addView(this.mViewPager, localLayoutParams);
    localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams.addRule(12, -1);
    localRelativeLayout.addView(this.mPageLineLayoutParent, localLayoutParams);
    addView(localRelativeLayout, this.layoutParamsFW);
    this.displayImage = AbFileUtil.getBitmapFormSrc("image/play_display.png");
    this.hideImage = AbFileUtil.getBitmapFormSrc("image/play_hide.png");
    this.mListViews = new ArrayList();
    this.mAbViewPagerAdapter = new AbViewPagerAdapter(paramContext, this.mListViews);
    this.mViewPager.setAdapter(this.mAbViewPagerAdapter);
    this.mViewPager.setFadingEdgeLength(0);
    this.mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
    {
      public void onPageScrollStateChanged(int paramAnonymousInt)
      {
      }

      public void onPageScrolled(int paramAnonymousInt1, float paramAnonymousFloat, int paramAnonymousInt2)
      {
        AbSlidingPlayView.this.onPageScrolledCallBack(paramAnonymousInt1);
      }

      public void onPageSelected(int paramAnonymousInt)
      {
        AbSlidingPlayView.this.makesurePosition();
        AbSlidingPlayView.this.onPageSelectedCallBack(paramAnonymousInt);
      }
    });
  }

  public void makesurePosition()
  {
    this.position = this.mViewPager.getCurrentItem();
    int i = 0;
    if (i >= this.count)
      return;
    if (this.position == i)
      ((ImageView)this.pageLineLayout.getChildAt(this.position)).setImageBitmap(this.displayImage);
    while (true)
    {
      i += 1;
      break;
      ((ImageView)this.pageLineLayout.getChildAt(i)).setImageBitmap(this.hideImage);
    }
  }

  public void removeAllViews()
  {
    this.mListViews.clear();
    this.mAbViewPagerAdapter.notifyDataSetChanged();
    creatIndex();
  }

  public void setOnItemClickListener(AbOnItemClickListener paramAbOnItemClickListener)
  {
    this.mOnItemClickListener = paramAbOnItemClickListener;
  }

  public void setOnPageChangeListener(AbOnChangeListener paramAbOnChangeListener)
  {
    this.mAbChangeListener = paramAbOnChangeListener;
  }

  public void setOnPageScrolledListener(AbOnScrollListener paramAbOnScrollListener)
  {
    this.mAbScrolledListener = paramAbOnScrollListener;
  }

  public void setOnTouchListener(AbOnTouchListener paramAbOnTouchListener)
  {
    this.mAbOnTouchListener = paramAbOnTouchListener;
  }

  public void setPageLineHorizontalGravity(int paramInt)
  {
    this.pageLineHorizontalGravity = paramInt;
  }

  public void setPageLineImage(Bitmap paramBitmap1, Bitmap paramBitmap2)
  {
    this.displayImage = paramBitmap1;
    this.hideImage = paramBitmap2;
    creatIndex();
  }

  public void setPageLineLayoutBackground(int paramInt)
  {
    this.pageLineLayout.setBackgroundResource(paramInt);
  }

  public void setParentListView(ListView paramListView)
  {
    this.mViewPager.setParentListView(paramListView);
  }

  public void setParentScrollView(ScrollView paramScrollView)
  {
    this.mViewPager.setParentScrollView(paramScrollView);
  }

  public void startPlay()
  {
    if (this.handler != null)
    {
      this.play = true;
      this.handler.postDelayed(this.runnable, 5000L);
    }
  }

  public void stopPlay()
  {
    if (this.handler != null)
    {
      this.play = false;
      this.handler.removeCallbacks(this.runnable);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.sliding.AbSlidingPlayView
 * JD-Core Version:    0.6.2
 */