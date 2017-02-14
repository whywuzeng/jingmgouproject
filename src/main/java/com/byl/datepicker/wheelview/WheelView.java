package com.byl.datepicker.wheelview;

import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import com.byl.datepicker.wheelview.adapter.WheelViewAdapter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class WheelView extends View
{
  private static final int DEF_VISIBLE_ITEMS = 5;
  private static final int ITEM_OFFSET_PERCENT = 0;
  private static final int PADDING = 10;
  private int[] SHADOWS_COLORS = { -269882903, -806753815, 1072294377 };
  private GradientDrawable bottomShadow;
  private Drawable centerDrawable;
  private List<OnWheelChangedListener> changingListeners = new LinkedList();
  private List<OnWheelClickedListener> clickingListeners = new LinkedList();
  private int currentItem = 0;
  private DataSetObserver dataObserver = new DataSetObserver()
  {
    public void onChanged()
    {
      WheelView.this.invalidateWheel(false);
    }

    public void onInvalidated()
    {
      WheelView.this.invalidateWheel(true);
    }
  };
  private boolean drawShadows = true;
  private int firstItem;
  boolean isCyclic = false;
  private boolean isScrollingPerformed;
  private int itemHeight = 0;
  private LinearLayout itemsLayout;
  String label = "";
  private WheelRecycle recycle = new WheelRecycle(this);
  private WheelScroller scroller;
  WheelScroller.ScrollingListener scrollingListener = new WheelScroller.ScrollingListener()
  {
    public void onFinished()
    {
      if (WheelView.this.isScrollingPerformed)
      {
        WheelView.this.notifyScrollingListenersAboutEnd();
        WheelView.this.isScrollingPerformed = false;
      }
      WheelView.this.scrollingOffset = 0;
      WheelView.this.invalidate();
    }

    public void onJustify()
    {
      if (Math.abs(WheelView.this.scrollingOffset) > 1)
        WheelView.this.scroller.scroll(WheelView.this.scrollingOffset, 0);
    }

    public void onScroll(int paramAnonymousInt)
    {
      WheelView.this.doScroll(paramAnonymousInt);
      paramAnonymousInt = WheelView.this.getHeight();
      if (WheelView.this.scrollingOffset > paramAnonymousInt)
      {
        WheelView.this.scrollingOffset = paramAnonymousInt;
        WheelView.this.scroller.stopScrolling();
      }
      while (WheelView.this.scrollingOffset >= -paramAnonymousInt)
        return;
      WheelView.this.scrollingOffset = (-paramAnonymousInt);
      WheelView.this.scroller.stopScrolling();
    }

    public void onStarted()
    {
      WheelView.this.isScrollingPerformed = true;
      WheelView.this.notifyScrollingListenersAboutStart();
    }
  };
  private List<OnWheelScrollListener> scrollingListeners = new LinkedList();
  private int scrollingOffset;
  private GradientDrawable topShadow;
  private WheelViewAdapter viewAdapter;
  private int visibleItems = 5;
  private int wheelBackground = 2130837953;
  private int wheelForeground = 2130837954;

  public WheelView(Context paramContext)
  {
    super(paramContext);
    initData(paramContext);
  }

  public WheelView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initData(paramContext);
  }

  public WheelView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initData(paramContext);
  }

  private boolean addViewItem(int paramInt, boolean paramBoolean)
  {
    boolean bool = false;
    View localView = getItemView(paramInt);
    if (localView != null)
    {
      if (!paramBoolean)
        break label32;
      this.itemsLayout.addView(localView, 0);
    }
    while (true)
    {
      bool = true;
      return bool;
      label32: this.itemsLayout.addView(localView);
    }
  }

  private void buildViewForMeasuring()
  {
    int j;
    int i;
    if (this.itemsLayout != null)
    {
      this.recycle.recycleItems(this.itemsLayout, this.firstItem, new ItemsRange());
      j = this.visibleItems / 2;
      i = this.currentItem + j;
    }
    while (true)
    {
      if (i < this.currentItem - j)
      {
        return;
        createItemsLayout();
        break;
      }
      if (addViewItem(i, true))
        this.firstItem = i;
      i -= 1;
    }
  }

  private int calculateLayoutWidth(int paramInt1, int paramInt2)
  {
    initResourcesIfNecessary();
    this.itemsLayout.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
    this.itemsLayout.measure(View.MeasureSpec.makeMeasureSpec(paramInt1, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
    int i = this.itemsLayout.getMeasuredWidth();
    if (paramInt2 == 1073741824)
      i = paramInt1;
    while (true)
    {
      this.itemsLayout.measure(View.MeasureSpec.makeMeasureSpec(i - 20, 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
      return i;
      int j = Math.max(i + 20, getSuggestedMinimumWidth());
      i = j;
      if (paramInt2 == -2147483648)
      {
        i = j;
        if (paramInt1 < j)
          i = paramInt1;
      }
    }
  }

  private void createItemsLayout()
  {
    if (this.itemsLayout == null)
    {
      this.itemsLayout = new LinearLayout(getContext());
      this.itemsLayout.setOrientation(1);
    }
  }

  private void doScroll(int paramInt)
  {
    this.scrollingOffset += paramInt;
    int n = getItemHeight();
    int j = this.scrollingOffset / n;
    int k = this.currentItem - j;
    int i1 = this.viewAdapter.getItemsCount();
    paramInt = this.scrollingOffset % n;
    int m = paramInt;
    if (Math.abs(paramInt) <= n / 2)
      m = 0;
    int i;
    if ((this.isCyclic) && (i1 > 0))
      if (m > 0)
      {
        i = k - 1;
        paramInt = j + 1;
        if (i < 0)
          break label184;
        i %= i1;
        label103: j = this.scrollingOffset;
        if (i == this.currentItem)
          break label290;
        setCurrentItem(i, false);
      }
    while (true)
    {
      this.scrollingOffset = (j - paramInt * n);
      if (this.scrollingOffset > getHeight())
        this.scrollingOffset = (this.scrollingOffset % getHeight() + getHeight());
      return;
      paramInt = j;
      i = k;
      if (m >= 0)
        break;
      i = k + 1;
      paramInt = j - 1;
      break;
      label184: i += i1;
      break;
      if (k < 0)
      {
        paramInt = this.currentItem;
        i = 0;
        break label103;
      }
      if (k >= i1)
      {
        paramInt = this.currentItem - i1 + 1;
        i = i1 - 1;
        break label103;
      }
      if ((k > 0) && (m > 0))
      {
        i = k - 1;
        paramInt = j + 1;
        break label103;
      }
      paramInt = j;
      i = k;
      if (k >= i1 - 1)
        break label103;
      paramInt = j;
      i = k;
      if (m >= 0)
        break label103;
      i = k + 1;
      paramInt = j - 1;
      break label103;
      label290: invalidate();
    }
  }

  private void drawCenterRect(Canvas paramCanvas)
  {
    int i = getHeight() / 2;
    int j = (int)(getItemHeight() / 2 * 1.2D);
    Paint localPaint = new Paint();
    localPaint.setColor(getResources().getColor(2131099697));
    localPaint.setStrokeWidth(3.0F);
    paramCanvas.drawLine(0.0F, i - j, getWidth(), i - j, localPaint);
    paramCanvas.drawLine(0.0F, i + j, getWidth(), i + j, localPaint);
  }

  private void drawItems(Canvas paramCanvas)
  {
    paramCanvas.save();
    paramCanvas.translate(10.0F, -((this.currentItem - this.firstItem) * getItemHeight() + (getItemHeight() - getHeight()) / 2) + this.scrollingOffset);
    this.itemsLayout.draw(paramCanvas);
    paramCanvas.restore();
  }

  private void drawShadows(Canvas paramCanvas)
  {
    int i = getItemHeight() * 3;
    this.topShadow.setBounds(0, 0, getWidth(), i);
    this.topShadow.draw(paramCanvas);
    this.bottomShadow.setBounds(0, getHeight() - i, getWidth(), getHeight());
    this.bottomShadow.draw(paramCanvas);
  }

  private int getDesiredHeight(LinearLayout paramLinearLayout)
  {
    if ((paramLinearLayout != null) && (paramLinearLayout.getChildAt(0) != null))
      this.itemHeight = paramLinearLayout.getChildAt(0).getMeasuredHeight();
    return Math.max(this.itemHeight * this.visibleItems - this.itemHeight * 0 / 50, getSuggestedMinimumHeight());
  }

  private int getItemHeight()
  {
    if (this.itemHeight != 0)
      return this.itemHeight;
    if ((this.itemsLayout != null) && (this.itemsLayout.getChildAt(0) != null))
    {
      this.itemHeight = this.itemsLayout.getChildAt(0).getHeight();
      return this.itemHeight;
    }
    return getHeight() / this.visibleItems;
  }

  private View getItemView(int paramInt)
  {
    if ((this.viewAdapter == null) || (this.viewAdapter.getItemsCount() == 0))
      return null;
    int j = this.viewAdapter.getItemsCount();
    int i = paramInt;
    if (!isValidItemIndex(paramInt))
      return this.viewAdapter.getEmptyItem(this.recycle.getEmptyItem(), this.itemsLayout);
    while (i < 0)
      i += j;
    return this.viewAdapter.getItem(i % j, this.recycle.getItem(), this.itemsLayout);
  }

  private ItemsRange getItemsRange()
  {
    if (getItemHeight() == 0)
      return null;
    int i = this.currentItem;
    int j = 1;
    while (true)
    {
      if (getItemHeight() * j >= getHeight())
      {
        int m = j;
        int k = i;
        if (this.scrollingOffset != 0)
        {
          k = i;
          if (this.scrollingOffset > 0)
            k = i - 1;
          i = this.scrollingOffset / getItemHeight();
          k -= i;
          m = (int)(j + 1 + Math.asin(i));
        }
        return new ItemsRange(k, m);
      }
      i -= 1;
      j += 2;
    }
  }

  private void initData(Context paramContext)
  {
    this.scroller = new WheelScroller(getContext(), this.scrollingListener);
  }

  private void initResourcesIfNecessary()
  {
    if (this.centerDrawable == null)
      this.centerDrawable = getContext().getResources().getDrawable(this.wheelForeground);
    if (this.topShadow == null)
      this.topShadow = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, this.SHADOWS_COLORS);
    if (this.bottomShadow == null)
      this.bottomShadow = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, this.SHADOWS_COLORS);
    setBackgroundResource(this.wheelBackground);
  }

  private boolean isValidItemIndex(int paramInt)
  {
    return (this.viewAdapter != null) && (this.viewAdapter.getItemsCount() > 0) && ((this.isCyclic) || ((paramInt >= 0) && (paramInt < this.viewAdapter.getItemsCount())));
  }

  private void layout(int paramInt1, int paramInt2)
  {
    this.itemsLayout.layout(0, 0, paramInt1 - 20, paramInt2);
  }

  private boolean rebuildItems()
  {
    ItemsRange localItemsRange = getItemsRange();
    int i;
    boolean bool1;
    label47: boolean bool2;
    label86: label117: label126: int j;
    if (this.itemsLayout != null)
    {
      i = this.recycle.recycleItems(this.itemsLayout, this.firstItem, localItemsRange);
      if (this.firstItem != i)
      {
        bool1 = true;
        this.firstItem = i;
        bool2 = bool1;
        if (!bool1)
        {
          if ((this.firstItem != localItemsRange.getFirst()) || (this.itemsLayout.getChildCount() != localItemsRange.getCount()))
            break label172;
          bool2 = false;
        }
        if ((this.firstItem <= localItemsRange.getFirst()) || (this.firstItem > localItemsRange.getLast()))
          break label199;
        i = this.firstItem - 1;
        if (i >= localItemsRange.getFirst())
          break label178;
        j = this.firstItem;
        i = this.itemsLayout.getChildCount();
      }
    }
    while (true)
    {
      if (i >= localItemsRange.getCount())
      {
        this.firstItem = j;
        return bool2;
        bool1 = false;
        break;
        createItemsLayout();
        bool1 = true;
        break label47;
        label172: bool2 = true;
        break label86;
        label178: if (!addViewItem(i, true))
          break label126;
        this.firstItem = i;
        i -= 1;
        break label117;
        label199: this.firstItem = localItemsRange.getFirst();
        break label126;
      }
      int k = j;
      if (!addViewItem(this.firstItem + i, false))
      {
        k = j;
        if (this.itemsLayout.getChildCount() == 0)
          k = j + 1;
      }
      i += 1;
      j = k;
    }
  }

  private void updateView()
  {
    if (rebuildItems())
    {
      calculateLayoutWidth(getWidth(), 1073741824);
      layout(getWidth(), getHeight());
    }
  }

  public void addChangingListener(OnWheelChangedListener paramOnWheelChangedListener)
  {
    this.changingListeners.add(paramOnWheelChangedListener);
  }

  public void addClickingListener(OnWheelClickedListener paramOnWheelClickedListener)
  {
    this.clickingListeners.add(paramOnWheelClickedListener);
  }

  public void addScrollingListener(OnWheelScrollListener paramOnWheelScrollListener)
  {
    this.scrollingListeners.add(paramOnWheelScrollListener);
  }

  public boolean drawShadows()
  {
    return this.drawShadows;
  }

  public int getCurrentItem()
  {
    return this.currentItem;
  }

  public WheelViewAdapter getViewAdapter()
  {
    return this.viewAdapter;
  }

  public int getVisibleItems()
  {
    return this.visibleItems;
  }

  public void invalidateWheel(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.recycle.clearAll();
      if (this.itemsLayout != null)
        this.itemsLayout.removeAllViews();
      this.scrollingOffset = 0;
    }
    while (true)
    {
      invalidate();
      return;
      if (this.itemsLayout != null)
        this.recycle.recycleItems(this.itemsLayout, this.firstItem, new ItemsRange());
    }
  }

  public boolean isCyclic()
  {
    return this.isCyclic;
  }

  protected void notifyChangingListeners(int paramInt1, int paramInt2)
  {
    Iterator localIterator = this.changingListeners.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((OnWheelChangedListener)localIterator.next()).onChanged(this, paramInt1, paramInt2);
    }
  }

  protected void notifyClickListenersAboutClick(int paramInt)
  {
    Iterator localIterator = this.clickingListeners.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((OnWheelClickedListener)localIterator.next()).onItemClicked(this, paramInt);
    }
  }

  protected void notifyScrollingListenersAboutEnd()
  {
    Iterator localIterator = this.scrollingListeners.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((OnWheelScrollListener)localIterator.next()).onScrollingFinished(this);
    }
  }

  protected void notifyScrollingListenersAboutStart()
  {
    Iterator localIterator = this.scrollingListeners.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((OnWheelScrollListener)localIterator.next()).onScrollingStarted(this);
    }
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if ((this.viewAdapter != null) && (this.viewAdapter.getItemsCount() > 0))
    {
      updateView();
      drawItems(paramCanvas);
      drawCenterRect(paramCanvas);
    }
    if (this.drawShadows)
      drawShadows(paramCanvas);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    layout(paramInt3 - paramInt1, paramInt4 - paramInt2);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getMode(paramInt1);
    int j = View.MeasureSpec.getMode(paramInt2);
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    buildViewForMeasuring();
    int k = calculateLayoutWidth(paramInt1, i);
    if (j == 1073741824)
      paramInt1 = paramInt2;
    while (true)
    {
      setMeasuredDimension(k, paramInt1);
      return;
      i = getDesiredHeight(this.itemsLayout);
      paramInt1 = i;
      if (j == -2147483648)
        paramInt1 = Math.min(i, paramInt2);
    }
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((!isEnabled()) || (getViewAdapter() == null))
      return true;
    switch (paramMotionEvent.getAction())
    {
    default:
    case 2:
    case 1:
    }
    do
      while (true)
      {
        return this.scroller.onTouchEvent(paramMotionEvent);
        if (getParent() != null)
          getParent().requestDisallowInterceptTouchEvent(true);
      }
    while (this.isScrollingPerformed);
    int i = (int)paramMotionEvent.getY() - getHeight() / 2;
    if (i > 0)
      i += getItemHeight() / 2;
    while (true)
    {
      i /= getItemHeight();
      if ((i == 0) || (!isValidItemIndex(this.currentItem + i)))
        break;
      notifyClickListenersAboutClick(this.currentItem + i);
      break;
      i -= getItemHeight() / 2;
    }
  }

  public void removeChangingListener(OnWheelChangedListener paramOnWheelChangedListener)
  {
    this.changingListeners.remove(paramOnWheelChangedListener);
  }

  public void removeClickingListener(OnWheelClickedListener paramOnWheelClickedListener)
  {
    this.clickingListeners.remove(paramOnWheelClickedListener);
  }

  public void removeScrollingListener(OnWheelScrollListener paramOnWheelScrollListener)
  {
    this.scrollingListeners.remove(paramOnWheelScrollListener);
  }

  public void scroll(int paramInt1, int paramInt2)
  {
    int i = getItemHeight();
    int j = this.scrollingOffset;
    this.scroller.scroll(i * paramInt1 - j, paramInt2);
  }

  public void setCurrentItem(int paramInt)
  {
    setCurrentItem(paramInt, false);
  }

  public void setCurrentItem(int paramInt, boolean paramBoolean)
  {
    if ((this.viewAdapter == null) || (this.viewAdapter.getItemsCount() == 0));
    int k;
    int i;
    do
    {
      do
      {
        return;
        k = this.viewAdapter.getItemsCount();
        if (paramInt >= 0)
        {
          i = paramInt;
          if (paramInt < k)
            break;
        }
      }
      while (!this.isCyclic);
      if (paramInt < 0)
        break;
      i = paramInt % k;
    }
    while (i == this.currentItem);
    if (paramBoolean)
    {
      int j = i - this.currentItem;
      paramInt = j;
      if (this.isCyclic)
      {
        i = Math.min(i, this.currentItem) + k - Math.max(i, this.currentItem);
        paramInt = j;
        if (i < Math.abs(j))
          if (j >= 0)
            break label144;
      }
      label144: for (paramInt = i; ; paramInt = -i)
      {
        scroll(paramInt, 0);
        return;
        paramInt += k;
        break;
      }
    }
    this.scrollingOffset = 0;
    paramInt = this.currentItem;
    this.currentItem = i;
    notifyChangingListeners(paramInt, this.currentItem);
    invalidate();
  }

  public void setCyclic(boolean paramBoolean)
  {
    this.isCyclic = paramBoolean;
    invalidateWheel(false);
  }

  public void setDrawShadows(boolean paramBoolean)
  {
    this.drawShadows = paramBoolean;
  }

  public void setInterpolator(Interpolator paramInterpolator)
  {
    this.scroller.setInterpolator(paramInterpolator);
  }

  public void setShadowColor(int paramInt1, int paramInt2, int paramInt3)
  {
    this.SHADOWS_COLORS = new int[] { paramInt1, paramInt2, paramInt3 };
  }

  public void setViewAdapter(WheelViewAdapter paramWheelViewAdapter)
  {
    if (this.viewAdapter != null)
      this.viewAdapter.unregisterDataSetObserver(this.dataObserver);
    this.viewAdapter = paramWheelViewAdapter;
    if (this.viewAdapter != null)
      this.viewAdapter.registerDataSetObserver(this.dataObserver);
    invalidateWheel(true);
  }

  public void setVisibleItems(int paramInt)
  {
    this.visibleItems = paramInt;
  }

  public void setWheelBackground(int paramInt)
  {
    this.wheelBackground = paramInt;
    setBackgroundResource(this.wheelBackground);
  }

  public void setWheelForeground(int paramInt)
  {
    this.wheelForeground = paramInt;
    this.centerDrawable = getContext().getResources().getDrawable(this.wheelForeground);
  }

  public void stopScrolling()
  {
    this.scroller.stopScrolling();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.byl.datepicker.wheelview.WheelView
 * JD-Core Version:    0.6.2
 */