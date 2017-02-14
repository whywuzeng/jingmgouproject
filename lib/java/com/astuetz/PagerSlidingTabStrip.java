package com.astuetz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.ismartgo.apppub.R.styleable;
import java.util.Locale;

public class PagerSlidingTabStrip extends HorizontalScrollView
{
  private static final int[] ATTRS = { 16842901, 16842904 };
  private int currentPosition = 0;
  private float currentPositionOffset = 0.0F;
  private LinearLayout.LayoutParams defaultTabLayoutParams;
  public ViewPager.OnPageChangeListener delegatePageListener;
  private int dividerColor = 0;
  private int dividerPadding = 12;
  private Paint dividerPaint;
  private int dividerWidth = 1;
  private LinearLayout.LayoutParams expandedTabLayoutParams;
  private int indicatorColor = -10066330;
  private int indicatorHeight = 8;
  private int lastScrollX = 0;
  private Locale locale;
  private final PageListener pageListener = new PageListener(null);
  private ViewPager pager;
  private Paint rectPaint;
  private int scrollOffset = 52;
  private boolean shouldExpand = false;
  private int tabBackgroundResId = 2130837515;
  private int tabCount;
  private int tabPadding = 24;
  private int tabTextColor = -10066330;
  private int tabTextSize = 28;
  private Typeface tabTypeface = null;
  private int tabTypefaceStyle = 0;
  private LinearLayout tabsContainer;
  private boolean textAllCaps = true;
  private int underlineColor = 436207616;
  private int underlineHeight = 2;

  public PagerSlidingTabStrip(Context paramContext)
  {
    this(paramContext, null);
  }

  public PagerSlidingTabStrip(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public PagerSlidingTabStrip(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setFillViewport(true);
    setWillNotDraw(false);
    this.tabsContainer = new LinearLayout(paramContext);
    this.tabsContainer.setOrientation(0);
    this.tabsContainer.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
    addView(this.tabsContainer);
    Object localObject = getResources().getDisplayMetrics();
    this.scrollOffset = ((int)TypedValue.applyDimension(1, this.scrollOffset, (DisplayMetrics)localObject));
    this.indicatorHeight = ((int)TypedValue.applyDimension(1, this.indicatorHeight, (DisplayMetrics)localObject));
    this.underlineHeight = ((int)TypedValue.applyDimension(1, this.underlineHeight, (DisplayMetrics)localObject));
    this.dividerPadding = ((int)TypedValue.applyDimension(1, this.dividerPadding, (DisplayMetrics)localObject));
    this.tabPadding = ((int)TypedValue.applyDimension(1, this.tabPadding, (DisplayMetrics)localObject));
    this.dividerWidth = ((int)TypedValue.applyDimension(1, this.dividerWidth, (DisplayMetrics)localObject));
    this.tabTextSize = ((int)TypedValue.applyDimension(2, this.tabTextSize, (DisplayMetrics)localObject));
    localObject = paramContext.obtainStyledAttributes(paramAttributeSet, ATTRS);
    this.tabTextSize = ((TypedArray)localObject).getDimensionPixelSize(0, this.tabTextSize);
    this.tabTextColor = ((TypedArray)localObject).getColor(1, this.tabTextColor);
    ((TypedArray)localObject).recycle();
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.PagerSlidingTabStrip);
    this.indicatorColor = paramContext.getColor(0, this.indicatorColor);
    this.underlineColor = paramContext.getColor(1, this.underlineColor);
    this.dividerColor = paramContext.getColor(2, this.dividerColor);
    this.indicatorHeight = paramContext.getDimensionPixelSize(3, this.indicatorHeight);
    this.underlineHeight = paramContext.getDimensionPixelSize(4, this.underlineHeight);
    this.dividerPadding = paramContext.getDimensionPixelSize(5, this.dividerPadding);
    this.tabPadding = paramContext.getDimensionPixelSize(6, this.tabPadding);
    this.tabBackgroundResId = paramContext.getResourceId(8, this.tabBackgroundResId);
    this.shouldExpand = paramContext.getBoolean(9, this.shouldExpand);
    this.scrollOffset = paramContext.getDimensionPixelSize(7, this.scrollOffset);
    this.textAllCaps = paramContext.getBoolean(10, this.textAllCaps);
    paramContext.recycle();
    this.rectPaint = new Paint();
    this.rectPaint.setAntiAlias(true);
    this.rectPaint.setStyle(Paint.Style.FILL);
    this.dividerPaint = new Paint();
    this.dividerPaint.setAntiAlias(true);
    this.dividerPaint.setStrokeWidth(this.dividerWidth);
    this.defaultTabLayoutParams = new LinearLayout.LayoutParams(-2, -1);
    this.expandedTabLayoutParams = new LinearLayout.LayoutParams(0, -1, 1.0F);
    if (this.locale == null)
      this.locale = getResources().getConfiguration().locale;
  }

  private void addIconTab(int paramInt1, int paramInt2)
  {
    ImageButton localImageButton = new ImageButton(getContext());
    localImageButton.setImageResource(paramInt2);
    addTab(paramInt1, localImageButton);
  }

  private void addTab(final int paramInt, View paramView)
  {
    paramView.setFocusable(true);
    paramView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PagerSlidingTabStrip.this.pager.setCurrentItem(paramInt);
      }
    });
    paramView.setPadding(this.tabPadding, 0, this.tabPadding, 0);
    LinearLayout localLinearLayout = this.tabsContainer;
    if (this.shouldExpand);
    for (LinearLayout.LayoutParams localLayoutParams = this.expandedTabLayoutParams; ; localLayoutParams = this.defaultTabLayoutParams)
    {
      localLinearLayout.addView(paramView, paramInt, localLayoutParams);
      return;
    }
  }

  private void addTextTab(int paramInt, String paramString)
  {
    TextView localTextView = new TextView(getContext());
    localTextView.setText(paramString);
    localTextView.setGravity(17);
    localTextView.setSingleLine();
    addTab(paramInt, localTextView);
  }

  private void scrollToChild(int paramInt1, int paramInt2)
  {
    if (this.tabCount == 0);
    do
    {
      return;
      int i = this.tabsContainer.getChildAt(paramInt1).getLeft() + paramInt2;
      if (paramInt1 <= 0)
      {
        paramInt1 = i;
        if (paramInt2 <= 0);
      }
      else
      {
        paramInt1 = i - this.scrollOffset;
      }
    }
    while (paramInt1 == this.lastScrollX);
    this.lastScrollX = paramInt1;
    scrollTo(paramInt1, 0);
  }

  private void updateTabStyles()
  {
    int i = 0;
    if (i >= this.tabCount)
      return;
    Object localObject = this.tabsContainer.getChildAt(i);
    ((View)localObject).setBackgroundResource(this.tabBackgroundResId);
    if ((localObject instanceof TextView))
    {
      localObject = (TextView)localObject;
      ((TextView)localObject).setTextSize(2, this.tabTextSize);
      ((TextView)localObject).setTypeface(this.tabTypeface, this.tabTypefaceStyle);
      ((TextView)localObject).setTextColor(this.tabTextColor);
      if (this.textAllCaps)
      {
        if (Build.VERSION.SDK_INT < 14)
          break label97;
        ((TextView)localObject).setAllCaps(true);
      }
    }
    while (true)
    {
      i += 1;
      break;
      label97: ((TextView)localObject).setText(((TextView)localObject).getText().toString().toUpperCase(this.locale));
    }
  }

  public int getDividerColor()
  {
    return this.dividerColor;
  }

  public int getDividerPadding()
  {
    return this.dividerPadding;
  }

  public int getIndicatorColor()
  {
    return this.indicatorColor;
  }

  public int getIndicatorHeight()
  {
    return this.indicatorHeight;
  }

  public int getScrollOffset()
  {
    return this.scrollOffset;
  }

  public boolean getShouldExpand()
  {
    return this.shouldExpand;
  }

  public int getTabBackground()
  {
    return this.tabBackgroundResId;
  }

  public int getTabPaddingLeftRight()
  {
    return this.tabPadding;
  }

  public int getTextColor()
  {
    return this.tabTextColor;
  }

  public int getTextSize()
  {
    return this.tabTextSize;
  }

  public int getUnderlineColor()
  {
    return this.underlineColor;
  }

  public int getUnderlineHeight()
  {
    return this.underlineHeight;
  }

  public boolean isTextAllCaps()
  {
    return this.textAllCaps;
  }

  public void notifyDataSetChanged()
  {
    this.tabsContainer.removeAllViews();
    this.tabCount = this.pager.getAdapter().getCount();
    int i = 0;
    if (i >= this.tabCount)
    {
      updateTabStyles();
      getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
      {
        @SuppressLint({"NewApi"})
        public void onGlobalLayout()
        {
          if (Build.VERSION.SDK_INT < 16)
            PagerSlidingTabStrip.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
          while (true)
          {
            PagerSlidingTabStrip.this.currentPosition = PagerSlidingTabStrip.this.pager.getCurrentItem();
            PagerSlidingTabStrip.this.scrollToChild(PagerSlidingTabStrip.this.currentPosition, 0);
            return;
            PagerSlidingTabStrip.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
          }
        }
      });
      return;
    }
    if ((this.pager.getAdapter() instanceof IconTabProvider))
      addIconTab(i, ((IconTabProvider)this.pager.getAdapter()).getPageIconResId(i));
    while (true)
    {
      i += 1;
      break;
      addTextTab(i, this.pager.getAdapter().getPageTitle(i).toString());
    }
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if ((isInEditMode()) || (this.tabCount == 0));
    while (true)
    {
      return;
      int j = getHeight();
      this.rectPaint.setColor(this.indicatorColor);
      View localView = this.tabsContainer.getChildAt(this.currentPosition);
      float f4 = localView.getLeft();
      float f3 = localView.getRight();
      float f2 = f4;
      float f1 = f3;
      if (this.currentPositionOffset > 0.0F)
      {
        f2 = f4;
        f1 = f3;
        if (this.currentPosition < this.tabCount - 1)
        {
          localView = this.tabsContainer.getChildAt(this.currentPosition + 1);
          f2 = localView.getLeft();
          f1 = localView.getRight();
          f2 = this.currentPositionOffset * f2 + (1.0F - this.currentPositionOffset) * f4;
          f1 = this.currentPositionOffset * f1 + (1.0F - this.currentPositionOffset) * f3;
        }
      }
      paramCanvas.drawRect(f2, j - this.indicatorHeight, f1, j, this.rectPaint);
      this.rectPaint.setColor(this.underlineColor);
      paramCanvas.drawRect(0.0F, j - this.underlineHeight, this.tabsContainer.getWidth(), j, this.rectPaint);
      this.dividerPaint.setColor(this.dividerColor);
      int i = 0;
      while (i < this.tabCount - 1)
      {
        localView = this.tabsContainer.getChildAt(i);
        paramCanvas.drawLine(localView.getRight(), this.dividerPadding, localView.getRight(), j - this.dividerPadding, this.dividerPaint);
        i += 1;
      }
    }
  }

  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    paramParcelable = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    this.currentPosition = paramParcelable.currentPosition;
    requestLayout();
  }

  public Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    localSavedState.currentPosition = this.currentPosition;
    return localSavedState;
  }

  public void setAllCaps(boolean paramBoolean)
  {
    this.textAllCaps = paramBoolean;
  }

  public void setDividerColor(int paramInt)
  {
    this.dividerColor = paramInt;
    invalidate();
  }

  public void setDividerColorResource(int paramInt)
  {
    this.dividerColor = getResources().getColor(paramInt);
    invalidate();
  }

  public void setDividerPadding(int paramInt)
  {
    this.dividerPadding = paramInt;
    invalidate();
  }

  public void setIndicatorColor(int paramInt)
  {
    this.indicatorColor = paramInt;
    invalidate();
  }

  public void setIndicatorColorResource(int paramInt)
  {
    this.indicatorColor = getResources().getColor(paramInt);
    invalidate();
  }

  public void setIndicatorHeight(int paramInt)
  {
    this.indicatorHeight = paramInt;
    invalidate();
  }

  public void setOnPageChangeListener(ViewPager.OnPageChangeListener paramOnPageChangeListener)
  {
    this.delegatePageListener = paramOnPageChangeListener;
  }

  public void setScrollOffset(int paramInt)
  {
    this.scrollOffset = paramInt;
    invalidate();
  }

  public void setShouldExpand(boolean paramBoolean)
  {
    this.shouldExpand = paramBoolean;
    requestLayout();
  }

  public void setTabBackground(int paramInt)
  {
    this.tabBackgroundResId = paramInt;
  }

  public void setTabPaddingLeftRight(int paramInt)
  {
    this.tabPadding = paramInt;
    updateTabStyles();
  }

  public void setTextColor(int paramInt)
  {
    this.tabTextColor = paramInt;
    updateTabStyles();
  }

  public void setTextColor(int paramInt1, int paramInt2)
  {
    updateTabStyles(paramInt1, paramInt2);
  }

  public void setTextColorResource(int paramInt)
  {
    this.tabTextColor = getResources().getColor(paramInt);
    updateTabStyles();
  }

  public void setTextColorResource(int paramInt1, int paramInt2)
  {
    updateTabStyles(getResources().getColor(paramInt1), paramInt2);
  }

  public void setTextSize(int paramInt)
  {
    this.tabTextSize = paramInt;
    updateTabStyles();
  }

  public void setTypeface(Typeface paramTypeface, int paramInt)
  {
    this.tabTypeface = paramTypeface;
    this.tabTypefaceStyle = paramInt;
    updateTabStyles();
  }

  public void setUnderlineColor(int paramInt)
  {
    this.underlineColor = paramInt;
    invalidate();
  }

  public void setUnderlineColorResource(int paramInt)
  {
    this.underlineColor = getResources().getColor(paramInt);
    invalidate();
  }

  public void setUnderlineHeight(int paramInt)
  {
    this.underlineHeight = paramInt;
    invalidate();
  }

  public void setViewPager(ViewPager paramViewPager)
  {
    this.pager = paramViewPager;
    if (paramViewPager.getAdapter() == null)
      throw new IllegalStateException("ViewPager does not have adapter instance.");
    paramViewPager.setOnPageChangeListener(this.pageListener);
    notifyDataSetChanged();
  }

  public void updateTabStyles(int paramInt1, int paramInt2)
  {
    int i = 0;
    if (i >= this.tabCount)
      return;
    Object localObject;
    if (i != paramInt2)
    {
      localObject = this.tabsContainer.getChildAt(i);
      ((View)localObject).setBackgroundResource(this.tabBackgroundResId);
      if ((localObject instanceof TextView))
      {
        localObject = (TextView)localObject;
        ((TextView)localObject).setTextSize(2, this.tabTextSize);
        ((TextView)localObject).setTypeface(this.tabTypeface, this.tabTypefaceStyle);
        ((TextView)localObject).setTextColor(this.tabTextColor);
        if (this.textAllCaps)
        {
          if (Build.VERSION.SDK_INT < 14)
            break label111;
          ((TextView)localObject).setAllCaps(true);
        }
      }
    }
    while (true)
    {
      i += 1;
      break;
      label111: ((TextView)localObject).setText(((TextView)localObject).getText().toString().toUpperCase(this.locale));
      continue;
      localObject = this.tabsContainer.getChildAt(i);
      ((View)localObject).setBackgroundResource(this.tabBackgroundResId);
      if ((localObject instanceof TextView))
      {
        localObject = (TextView)localObject;
        ((TextView)localObject).setTextSize(2, this.tabTextSize);
        ((TextView)localObject).setTypeface(this.tabTypeface, this.tabTypefaceStyle);
        ((TextView)localObject).setTextColor(paramInt1);
        if (this.textAllCaps)
          if (Build.VERSION.SDK_INT >= 14)
            ((TextView)localObject).setAllCaps(true);
          else
            ((TextView)localObject).setText(((TextView)localObject).getText().toString().toUpperCase(this.locale));
      }
    }
  }

  public static abstract interface IconTabProvider
  {
    public abstract int getPageIconResId(int paramInt);
  }

  private class PageListener
    implements ViewPager.OnPageChangeListener
  {
    private PageListener()
    {
    }

    public void onPageScrollStateChanged(int paramInt)
    {
      if (paramInt == 0)
        PagerSlidingTabStrip.this.scrollToChild(PagerSlidingTabStrip.this.pager.getCurrentItem(), 0);
      if (PagerSlidingTabStrip.this.delegatePageListener != null)
        PagerSlidingTabStrip.this.delegatePageListener.onPageScrollStateChanged(paramInt);
    }

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
    {
      PagerSlidingTabStrip.this.currentPosition = paramInt1;
      PagerSlidingTabStrip.this.currentPositionOffset = paramFloat;
      PagerSlidingTabStrip.this.scrollToChild(paramInt1, (int)(PagerSlidingTabStrip.this.tabsContainer.getChildAt(paramInt1).getWidth() * paramFloat));
      PagerSlidingTabStrip.this.invalidate();
      if (PagerSlidingTabStrip.this.delegatePageListener != null)
        PagerSlidingTabStrip.this.delegatePageListener.onPageScrolled(paramInt1, paramFloat, paramInt2);
    }

    public void onPageSelected(int paramInt)
    {
      if (PagerSlidingTabStrip.this.delegatePageListener != null)
        PagerSlidingTabStrip.this.delegatePageListener.onPageSelected(paramInt);
    }
  }

  static class SavedState extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator()
    {
      public PagerSlidingTabStrip.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new PagerSlidingTabStrip.SavedState(paramAnonymousParcel, null);
      }

      public PagerSlidingTabStrip.SavedState[] newArray(int paramAnonymousInt)
      {
        return new PagerSlidingTabStrip.SavedState[paramAnonymousInt];
      }
    };
    int currentPosition;

    private SavedState(Parcel paramParcel)
    {
      super();
      this.currentPosition = paramParcel.readInt();
    }

    public SavedState(Parcelable paramParcelable)
    {
      super();
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(this.currentPosition);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.astuetz.PagerSlidingTabStrip
 * JD-Core Version:    0.6.2
 */