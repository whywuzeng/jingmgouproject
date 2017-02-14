package com.ab.view.wheel;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.LayerDrawable;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import com.ab.global.AbAppData;
import com.ab.util.AbGraphical;
import com.ab.util.AbViewUtil;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AbWheelView extends View
{
  private static final boolean D = AbAppData.DEBUG;
  private static final int DEF_VISIBLE_ITEMS = 5;
  private static final int ITEMS_TEXT_COLOR = -16777216;
  private static final int LABEL_OFFSET = 8;
  private static final int MIN_DELTA_FOR_SCROLLING = 1;
  private static final int PADDING = 5;
  private static final int SCROLLING_DURATION = 400;
  private static int[] SHADOWS_COLORS = { -15658735, 11184810, 11184810 };
  private static String TAG = "AbWheelView";
  private final int MESSAGE_JUSTIFY = 1;
  private final int MESSAGE_SCROLL = 0;
  private AbWheelAdapter adapter = null;
  private int additionalItemHeight = 30;
  private Handler animationHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      AbWheelView.this.scroller.computeScrollOffset();
      int i = AbWheelView.this.scroller.getCurrY();
      int j = AbWheelView.this.lastScrollY - i;
      AbWheelView.this.lastScrollY = i;
      if (j != 0)
        AbWheelView.this.doScroll(j);
      if (Math.abs(i - AbWheelView.this.scroller.getFinalY()) < 1)
      {
        AbWheelView.this.scroller.getFinalY();
        AbWheelView.this.scroller.forceFinished(true);
      }
      if (!AbWheelView.this.scroller.isFinished())
      {
        AbWheelView.this.animationHandler.sendEmptyMessage(paramAnonymousMessage.what);
        return;
      }
      if (paramAnonymousMessage.what == 0)
      {
        AbWheelView.this.justify();
        return;
      }
      AbWheelView.this.finishScrolling();
    }
  };
  private int[] bottomGradientColors = { 819, 3549, 819 };
  private GradientDrawable bottomShadow;
  private Drawable centerSelectDrawable;
  private int[] centerSelectGradientColors = { 1881285154, 1881285154, 1894706926 };
  private int centerSelectStrokeColor = 1882403635;
  private int centerSelectStrokeWidth = 1;
  private List<AbOnWheelChangedListener> changingListeners = new LinkedList();
  private int currentItem = 0;
  private GestureDetector gestureDetector;
  private GestureDetector.SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener()
  {
    public boolean onDown(MotionEvent paramAnonymousMotionEvent)
    {
      if (AbWheelView.this.isScrollingPerformed)
      {
        AbWheelView.this.scroller.forceFinished(true);
        AbWheelView.this.clearMessages();
        return true;
      }
      return false;
    }

    public boolean onFling(MotionEvent paramAnonymousMotionEvent1, MotionEvent paramAnonymousMotionEvent2, float paramAnonymousFloat1, float paramAnonymousFloat2)
    {
      AbWheelView.this.lastScrollY = (AbWheelView.this.currentItem * AbWheelView.this.getItemHeight() + AbWheelView.this.scrollingOffset);
      int i;
      if (AbWheelView.this.isCyclic)
      {
        i = 2147483647;
        if (!AbWheelView.this.isCyclic)
          break label125;
      }
      label125: for (int j = -i; ; j = 0)
      {
        AbWheelView.this.scroller.fling(0, AbWheelView.this.lastScrollY, 0, (int)-paramAnonymousFloat2 / 2, 0, 0, j, i);
        AbWheelView.this.setNextMessage(0);
        return true;
        i = AbWheelView.this.adapter.getItemsCount() * AbWheelView.this.getItemHeight();
        break;
      }
    }

    public boolean onScroll(MotionEvent paramAnonymousMotionEvent1, MotionEvent paramAnonymousMotionEvent2, float paramAnonymousFloat1, float paramAnonymousFloat2)
    {
      AbWheelView.this.startScrolling();
      AbWheelView.this.doScroll((int)-paramAnonymousFloat2);
      return true;
    }
  };
  boolean isCyclic = false;
  private boolean isScrollingPerformed;
  private int itemHeight = 0;
  private int itemOffset = this.valueTextSize / 5;
  private StaticLayout itemsLayout;
  private TextPaint itemsPaint;
  private int itemsWidth = 0;
  private String label;
  private StaticLayout labelLayout;
  private TextPaint labelPaint;
  private int labelTextColor = -268435456;
  private int labelTextSize = 35;
  private int labelWidth = 0;
  private int lastScrollY;
  private Context mContext = null;
  private int screenHeight = 0;
  private int screenWidth = 0;
  private Scroller scroller;
  private List<AbOnWheelScrollListener> scrollingListeners = new LinkedList();
  private int scrollingOffset;
  private int[] topGradientColors = { 2730, 4095, 2730 };
  private GradientDrawable topShadow;
  private int topStrokeColor = -13421773;
  private int topStrokeWidth = 1;
  private StaticLayout valueLayout;
  private TextPaint valuePaint;
  private int valueTextColor = -268435456;
  private int valueTextSize = 35;
  private int visibleItems = 5;

  public AbWheelView(Context paramContext)
  {
    super(paramContext);
    initData(paramContext);
  }

  public AbWheelView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initData(paramContext);
  }

  public AbWheelView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initData(paramContext);
  }

  private String buildText(boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int j = this.visibleItems / 2 + 1;
    int i = this.currentItem - j;
    while (true)
    {
      if (i > this.currentItem + j)
        return localStringBuilder.toString();
      if ((paramBoolean) || (i != this.currentItem))
      {
        String str = getTextItem(i);
        if (str != null)
          localStringBuilder.append(str);
      }
      if (i < this.currentItem + j)
        localStringBuilder.append("\n");
      i += 1;
    }
  }

  private int calculateLayoutWidth(int paramInt1, int paramInt2)
  {
    initResourcesIfNecessary();
    int i = getMaxTextLength();
    int k;
    int j;
    if (i > 0)
    {
      float f = (int)AbGraphical.getStringWidth("0", this.labelPaint);
      this.itemsWidth = ((int)(i * f));
      this.itemsWidth += 8;
      this.labelWidth = 0;
      if ((this.label != null) && (this.label.length() > 0))
      {
        this.labelWidth = ((int)AbGraphical.getStringWidth(this.label, this.labelPaint));
        if (D)
          Log.d(TAG, "itemsWidth:" + this.itemsWidth + ",labelWidth:" + this.labelWidth);
      }
      k = 0;
      if (paramInt2 != 1073741824)
        break label245;
      j = 1;
      i = paramInt1;
      label149: if (j != 0)
      {
        paramInt2 = i - 8 - 10;
        if (paramInt2 <= 0)
        {
          this.labelWidth = 0;
          this.itemsWidth = 0;
        }
        if (this.labelWidth <= 0)
          break label339;
        paramInt1 = paramInt2 / 2;
        paramInt2 -= this.itemsWidth;
        if (paramInt1 >= this.itemsWidth)
          break label326;
        this.itemsWidth = (paramInt1 + paramInt2 - this.labelWidth);
      }
    }
    while (true)
    {
      if (this.itemsWidth > 0)
        createLayouts(this.itemsWidth, this.labelWidth);
      return i;
      this.itemsWidth = 0;
      break;
      label245: j = this.itemsWidth + this.labelWidth + 10;
      i = j;
      if (this.labelWidth > 0)
        i = j + 8;
      int m = Math.max(i, getSuggestedMinimumWidth());
      j = k;
      i = m;
      if (paramInt2 != -2147483648)
        break label149;
      j = k;
      i = m;
      if (paramInt1 >= m)
        break label149;
      j = 1;
      i = paramInt1;
      break label149;
      label326: this.labelWidth = paramInt2;
      this.itemsWidth = paramInt1;
      continue;
      label339: this.itemsWidth = (paramInt2 + 8);
    }
  }

  private void clearMessages()
  {
    this.animationHandler.removeMessages(0);
    this.animationHandler.removeMessages(1);
  }

  private void createLayouts(int paramInt1, int paramInt2)
  {
    Layout.Alignment localAlignment = null;
    Object localObject1;
    if ((this.itemsLayout == null) || (this.itemsLayout.getWidth() > paramInt1))
    {
      Object localObject2 = buildText(this.isScrollingPerformed);
      TextPaint localTextPaint = this.itemsPaint;
      if (paramInt2 > 0)
      {
        localObject1 = Layout.Alignment.ALIGN_OPPOSITE;
        this.itemsLayout = new StaticLayout((CharSequence)localObject2, localTextPaint, paramInt1, (Layout.Alignment)localObject1, 1.0F, this.additionalItemHeight, false);
        label69: if ((this.isScrollingPerformed) || ((this.valueLayout != null) && (this.valueLayout.getWidth() <= paramInt1)))
          break label247;
        localObject1 = localAlignment;
        if (getAdapter() != null)
          localObject1 = getAdapter().getItem(this.currentItem);
        if (localObject1 == null)
          break label232;
        label122: localObject2 = this.valuePaint;
        if (paramInt2 <= 0)
          break label239;
        localAlignment = Layout.Alignment.ALIGN_OPPOSITE;
        label137: this.valueLayout = new StaticLayout((CharSequence)localObject1, (TextPaint)localObject2, paramInt1, localAlignment, 1.0F, this.additionalItemHeight, false);
      }
    }
    while (true)
    {
      if (paramInt2 > 0)
      {
        if ((this.labelLayout != null) && (this.labelLayout.getWidth() <= paramInt2))
          break label273;
        this.labelLayout = new StaticLayout(this.label, this.labelPaint, paramInt2, Layout.Alignment.ALIGN_NORMAL, 1.0F, this.additionalItemHeight, false);
      }
      return;
      localObject1 = Layout.Alignment.ALIGN_CENTER;
      break;
      this.itemsLayout.increaseWidthTo(paramInt1);
      break label69;
      label232: localObject1 = "";
      break label122;
      label239: localAlignment = Layout.Alignment.ALIGN_CENTER;
      break label137;
      label247: if (this.isScrollingPerformed)
        this.valueLayout = null;
      else
        this.valueLayout.increaseWidthTo(paramInt1);
    }
    label273: this.labelLayout.increaseWidthTo(paramInt2);
  }

  private void doScroll(int paramInt)
  {
    this.scrollingOffset += paramInt;
    int i = this.scrollingOffset / getItemHeight();
    int j = this.currentItem - i;
    if ((this.isCyclic) && (this.adapter.getItemsCount() > 0))
      if (j >= 0)
      {
        paramInt = j % this.adapter.getItemsCount();
        label62: j = this.scrollingOffset;
        if (paramInt == this.currentItem)
          break label229;
        setCurrentItem(paramInt, false);
      }
    while (true)
    {
      this.scrollingOffset = (j - getItemHeight() * i);
      if (this.scrollingOffset > getHeight())
        this.scrollingOffset = (this.scrollingOffset % getHeight() + getHeight());
      return;
      j += this.adapter.getItemsCount();
      break;
      if (this.isScrollingPerformed)
      {
        if (j < 0)
        {
          i = this.currentItem;
          paramInt = 0;
          break label62;
        }
        paramInt = j;
        if (j < this.adapter.getItemsCount())
          break label62;
        i = this.currentItem - this.adapter.getItemsCount() + 1;
        paramInt = this.adapter.getItemsCount() - 1;
        break label62;
      }
      paramInt = Math.min(Math.max(j, 0), this.adapter.getItemsCount() - 1);
      break label62;
      label229: invalidate();
    }
  }

  private void drawCenterRect(Canvas paramCanvas)
  {
    int i = getHeight() / 2;
    int j = getItemHeight() / 2;
    this.centerSelectDrawable.setBounds(0, i - j, getWidth(), i + j);
    this.centerSelectDrawable.draw(paramCanvas);
  }

  private void drawItems(Canvas paramCanvas)
  {
    paramCanvas.save();
    paramCanvas.translate(0.0F, -this.itemsLayout.getLineTop(1) + this.scrollingOffset);
    this.itemsPaint.setColor(-16777216);
    this.itemsPaint.drawableState = getDrawableState();
    this.itemsLayout.draw(paramCanvas);
    paramCanvas.restore();
  }

  private void drawShadows(Canvas paramCanvas)
  {
    this.topShadow.setBounds(0, 0, getWidth(), getHeight() / this.visibleItems);
    this.topShadow.draw(paramCanvas);
    this.bottomShadow.setBounds(0, getHeight() - getHeight() / this.visibleItems, getWidth(), getHeight());
    this.bottomShadow.draw(paramCanvas);
  }

  private void drawValue(Canvas paramCanvas)
  {
    this.valuePaint.setColor(this.valueTextColor);
    this.valuePaint.drawableState = getDrawableState();
    this.labelPaint.setColor(this.labelTextColor);
    this.labelPaint.drawableState = getDrawableState();
    Rect localRect = new Rect();
    this.itemsLayout.getLineBounds(this.visibleItems / 2, localRect);
    if (this.labelLayout != null)
    {
      paramCanvas.save();
      paramCanvas.translate(this.itemsLayout.getWidth() + 8, localRect.top);
      this.labelLayout.draw(paramCanvas);
      paramCanvas.restore();
    }
    if (this.valueLayout != null)
    {
      paramCanvas.save();
      paramCanvas.translate(0.0F, localRect.top + this.scrollingOffset);
      this.valueLayout.draw(paramCanvas);
      paramCanvas.restore();
    }
  }

  private int getDesiredHeight(Layout paramLayout)
  {
    if (paramLayout == null)
      return 0;
    return Math.max(getItemHeight() * this.visibleItems - this.itemOffset * 2 - this.additionalItemHeight, getSuggestedMinimumHeight());
  }

  private int getItemHeight()
  {
    if (this.itemHeight != 0)
      return this.itemHeight;
    if ((this.itemsLayout != null) && (this.itemsLayout.getLineCount() > 2))
    {
      this.itemHeight = (this.itemsLayout.getLineTop(2) - this.itemsLayout.getLineTop(1));
      return this.itemHeight;
    }
    return getHeight() / this.visibleItems;
  }

  private int getMaxTextLength()
  {
    AbWheelAdapter localAbWheelAdapter = getAdapter();
    int i;
    if (localAbWheelAdapter == null)
      i = 0;
    int j;
    do
    {
      return i;
      j = localAbWheelAdapter.getMaximumLength();
      i = j;
    }
    while (j > 0);
    return 0;
  }

  private String getTextItem(int paramInt)
  {
    if ((this.adapter == null) || (this.adapter.getItemsCount() == 0));
    int j;
    do
    {
      return null;
      j = this.adapter.getItemsCount();
      if (paramInt >= 0)
      {
        i = paramInt;
        if (paramInt < j)
          break;
      }
    }
    while (!this.isCyclic);
    int i = paramInt;
    while (true)
    {
      if (i >= 0)
        return this.adapter.getItem(i % j);
      i += j;
    }
  }

  private void initData(Context paramContext)
  {
    this.mContext = paramContext;
    this.gestureDetector = new GestureDetector(paramContext, this.gestureListener);
    this.gestureDetector.setIsLongpressEnabled(false);
    this.scroller = new Scroller(paramContext);
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((Activity)paramContext).getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    this.screenWidth = localDisplayMetrics.widthPixels;
    this.screenHeight = localDisplayMetrics.heightPixels;
  }

  private void initResourcesIfNecessary()
  {
    if (this.itemsPaint == null)
    {
      this.itemsPaint = new TextPaint(33);
      this.itemsPaint.setTextSize(this.valueTextSize);
    }
    if (this.valuePaint == null)
    {
      this.valuePaint = new TextPaint(37);
      this.valuePaint.setTextSize(this.valueTextSize);
    }
    if (this.labelPaint == null)
    {
      this.labelPaint = new TextPaint(37);
      this.labelPaint.setTextSize(this.labelTextSize);
      this.labelPaint.setShadowLayer(0.5F, 0.0F, 1.0F, -1);
    }
    Object localObject;
    if (this.centerSelectDrawable == null)
    {
      localObject = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, this.centerSelectGradientColors);
      ((GradientDrawable)localObject).setStroke(this.centerSelectStrokeWidth, this.centerSelectStrokeColor);
      this.centerSelectDrawable = ((Drawable)localObject);
    }
    if (this.topShadow == null)
      this.topShadow = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, SHADOWS_COLORS);
    if (this.bottomShadow == null)
      this.bottomShadow = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, SHADOWS_COLORS);
    if (getBackground() == null)
    {
      localObject = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, this.topGradientColors);
      GradientDrawable localGradientDrawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, this.bottomGradientColors);
      ((GradientDrawable)localObject).setStroke(this.topStrokeWidth, this.topStrokeColor);
      ((GradientDrawable)localObject).setShape(0);
      localGradientDrawable.setShape(0);
      ((GradientDrawable)localObject).setGradientType(0);
      localGradientDrawable.setGradientType(0);
      localObject = new LayerDrawable(new GradientDrawable[] { localObject, localGradientDrawable });
      ((LayerDrawable)localObject).setLayerInset(0, 0, 0, 0, 0);
      ((LayerDrawable)localObject).setLayerInset(1, 4, 1, 4, 1);
      setBackgroundDrawable((Drawable)localObject);
    }
  }

  private void invalidateLayouts()
  {
    this.itemsLayout = null;
    this.valueLayout = null;
    this.scrollingOffset = 0;
  }

  private void justify()
  {
    if (this.adapter == null)
      return;
    this.lastScrollY = 0;
    int k = this.scrollingOffset;
    int m = getItemHeight();
    int i;
    if (k > 0)
      if (this.currentItem < this.adapter.getItemsCount())
      {
        i = 1;
        if (!this.isCyclic)
        {
          j = k;
          if (i == 0);
        }
        else
        {
          j = k;
          if (Math.abs(k) > m / 2.0F)
            if (k >= 0)
              break label136;
        }
      }
    label136: for (int j = k + (m + 1); ; j = k - (m + 1))
    {
      if (Math.abs(j) <= 1)
        break label146;
      this.scroller.startScroll(0, 0, 0, j, 400);
      setNextMessage(1);
      return;
      i = 0;
      break;
      if (this.currentItem > 0)
      {
        i = 1;
        break;
      }
      i = 0;
      break;
    }
    label146: finishScrolling();
  }

  private void setNextMessage(int paramInt)
  {
    clearMessages();
    this.animationHandler.sendEmptyMessage(paramInt);
  }

  private void startScrolling()
  {
    if (!this.isScrollingPerformed)
    {
      this.isScrollingPerformed = true;
      notifyScrollingListenersAboutStart();
    }
  }

  public void addChangingListener(AbOnWheelChangedListener paramAbOnWheelChangedListener)
  {
    this.changingListeners.add(paramAbOnWheelChangedListener);
  }

  public void addScrollingListener(AbOnWheelScrollListener paramAbOnWheelScrollListener)
  {
    this.scrollingListeners.add(paramAbOnWheelScrollListener);
  }

  void finishScrolling()
  {
    if (this.isScrollingPerformed)
    {
      notifyScrollingListenersAboutEnd();
      this.isScrollingPerformed = false;
    }
    invalidateLayouts();
    invalidate();
  }

  public AbWheelAdapter getAdapter()
  {
    return this.adapter;
  }

  public int[] getCenterSelectGradientColors()
  {
    return this.centerSelectGradientColors;
  }

  public int getCenterSelectStrokeColor()
  {
    return this.centerSelectStrokeColor;
  }

  public int getCenterSelectStrokeWidth()
  {
    return this.centerSelectStrokeWidth;
  }

  public int getCurrentItem()
  {
    return this.currentItem;
  }

  public String getLabel()
  {
    return this.label;
  }

  public int getVisibleItems()
  {
    return this.visibleItems;
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
      ((AbOnWheelChangedListener)localIterator.next()).onChanged(this, paramInt1, paramInt2);
    }
  }

  protected void notifyScrollingListenersAboutEnd()
  {
    Iterator localIterator = this.scrollingListeners.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((AbOnWheelScrollListener)localIterator.next()).onScrollingFinished(this);
    }
  }

  protected void notifyScrollingListenersAboutStart()
  {
    Iterator localIterator = this.scrollingListeners.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((AbOnWheelScrollListener)localIterator.next()).onScrollingStarted(this);
    }
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (this.itemsLayout == null)
    {
      if (this.itemsWidth != 0)
        break label81;
      calculateLayoutWidth(getWidth(), 1073741824);
    }
    while (true)
    {
      if (this.itemsWidth > 0)
      {
        paramCanvas.save();
        paramCanvas.translate(5.0F, -this.itemOffset);
        drawItems(paramCanvas);
        drawValue(paramCanvas);
        paramCanvas.restore();
      }
      drawCenterRect(paramCanvas);
      drawShadows(paramCanvas);
      return;
      label81: createLayouts(this.itemsWidth, this.labelWidth);
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getMode(paramInt1);
    int j = View.MeasureSpec.getMode(paramInt2);
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    int k = calculateLayoutWidth(paramInt1, i);
    if (j == 1073741824)
      paramInt1 = paramInt2;
    while (true)
    {
      if (D)
        Log.d(TAG, "onMeasure:" + k);
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
    if (getAdapter() == null);
    while ((this.gestureDetector.onTouchEvent(paramMotionEvent)) || (paramMotionEvent.getAction() != 1))
      return true;
    justify();
    return true;
  }

  public void removeChangingListener(AbOnWheelChangedListener paramAbOnWheelChangedListener)
  {
    this.changingListeners.remove(paramAbOnWheelChangedListener);
  }

  public void removeScrollingListener(AbOnWheelScrollListener paramAbOnWheelScrollListener)
  {
    this.scrollingListeners.remove(paramAbOnWheelScrollListener);
  }

  public void scroll(int paramInt1, int paramInt2)
  {
    this.scroller.forceFinished(true);
    this.lastScrollY = this.scrollingOffset;
    int i = getItemHeight();
    this.scroller.startScroll(0, this.lastScrollY, 0, paramInt1 * i - this.lastScrollY, paramInt2);
    setNextMessage(0);
    startScrolling();
  }

  public void setAdapter(AbWheelAdapter paramAbWheelAdapter)
  {
    this.adapter = paramAbWheelAdapter;
    invalidateLayouts();
    invalidate();
  }

  public void setAdditionalItemHeight(int paramInt)
  {
    this.additionalItemHeight = paramInt;
  }

  public void setCenterSelectDrawable(Drawable paramDrawable)
  {
    this.centerSelectDrawable = paramDrawable;
  }

  public void setCenterSelectGradientColors(int[] paramArrayOfInt)
  {
    this.centerSelectGradientColors = paramArrayOfInt;
  }

  public void setCenterSelectStrokeColor(int paramInt)
  {
    this.centerSelectStrokeColor = paramInt;
  }

  public void setCenterSelectStrokeWidth(int paramInt)
  {
    this.centerSelectStrokeWidth = paramInt;
  }

  public void setCurrentItem(int paramInt)
  {
    setCurrentItem(paramInt, false);
  }

  public void setCurrentItem(int paramInt, boolean paramBoolean)
  {
    if ((this.adapter == null) || (this.adapter.getItemsCount() == 0));
    int i;
    do
    {
      return;
      if (paramInt >= 0)
      {
        i = paramInt;
        if (paramInt < this.adapter.getItemsCount())
          break;
      }
    }
    while (!this.isCyclic);
    while (true)
    {
      if (paramInt >= 0)
      {
        i = paramInt % this.adapter.getItemsCount();
        if (i == this.currentItem)
          break;
        if (!paramBoolean)
          break label103;
        scroll(i - this.currentItem, 400);
        return;
      }
      paramInt += this.adapter.getItemsCount();
    }
    label103: invalidateLayouts();
    paramInt = this.currentItem;
    this.currentItem = i;
    notifyChangingListeners(paramInt, this.currentItem);
    invalidate();
  }

  public void setCyclic(boolean paramBoolean)
  {
    this.isCyclic = paramBoolean;
    invalidate();
    invalidateLayouts();
  }

  public void setInterpolator(Interpolator paramInterpolator)
  {
    this.scroller.forceFinished(true);
    this.scroller = new Scroller(getContext(), paramInterpolator);
  }

  public void setLabel(String paramString)
  {
    if ((this.label == null) || (!this.label.equals(paramString)))
    {
      this.label = paramString;
      this.labelLayout = null;
      invalidate();
    }
  }

  public void setLabelTextColor(int paramInt)
  {
    this.labelTextColor = paramInt;
  }

  public void setLabelTextSize(int paramInt)
  {
    this.labelTextSize = AbViewUtil.resizeTextSize(this.screenWidth, this.screenHeight, paramInt);
  }

  public void setValueTextColor(int paramInt)
  {
    this.valueTextColor = paramInt;
  }

  public void setValueTextSize(int paramInt)
  {
    this.valueTextSize = AbViewUtil.resizeTextSize(this.screenWidth, this.screenHeight, paramInt);
    this.itemOffset = (this.valueTextSize / 5);
  }

  public void setVisibleItems(int paramInt)
  {
    this.visibleItems = paramInt;
    invalidate();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.wheel.AbWheelView
 * JD-Core Version:    0.6.2
 */