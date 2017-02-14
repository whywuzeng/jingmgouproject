package com.ab.view.app;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class AbPopoverView extends RelativeLayout
  implements View.OnTouchListener
{
  public static final int PopoverArrowDirectionAny = 15;
  public static final int PopoverArrowDirectionDown = 2;
  public static final int PopoverArrowDirectionLeft = 4;
  public static final int PopoverArrowDirectionRight = 8;
  public static final int PopoverArrowDirectionUp = 1;
  private Drawable arrowDownDrawable;
  private ImageView arrowImageView = null;
  private Drawable arrowLeftDrawable;
  private Drawable arrowRightDrawable;
  private Drawable arrowUpDrawable;
  private Drawable backgroundDrawable;
  private Point contentSizeForViewInPopover = new Point(0, 0);
  private int fadeAnimationTime = 300;
  private boolean isAnimating = false;
  private View popoverContentView = null;
  private Rect popoverLayoutRect;
  private RelativeLayout popoverView;
  private PopoverViewListener popoverViewListener;
  private Map<Integer, Rect> possibleRects;
  private Point realContentSize = new Point(0, 0);
  private ViewGroup superview;

  public AbPopoverView(Context paramContext)
  {
    super(paramContext);
    initPopoverView();
  }

  public AbPopoverView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initPopoverView();
  }

  public AbPopoverView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initPopoverView();
  }

  private void addAvailableRects(Rect paramRect, int paramInt)
  {
    this.possibleRects = new HashMap();
    if ((paramInt & 0x1) != 0)
      this.possibleRects.put(Integer.valueOf(1), getRectForArrowUp(paramRect));
    if ((paramInt & 0x2) != 0)
      this.possibleRects.put(Integer.valueOf(2), getRectForArrowDown(paramRect));
    if ((paramInt & 0x8) != 0)
      this.possibleRects.put(Integer.valueOf(8), getRectForArrowRight(paramRect));
    if ((paramInt & 0x4) != 0)
      this.possibleRects.put(Integer.valueOf(4), getRectForArrowLeft(paramRect));
  }

  private void addPopoverInRect(Rect paramRect)
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(paramRect.width(), paramRect.height());
    localLayoutParams.leftMargin = paramRect.left;
    localLayoutParams.topMargin = paramRect.top;
    addView(this.popoverView, localLayoutParams);
  }

  private Integer getBestRect()
  {
    Object localObject = null;
    Iterator localIterator = this.possibleRects.keySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localObject;
      Integer localInteger = (Integer)localIterator.next();
      if (localObject == null)
      {
        localObject = localInteger;
      }
      else
      {
        Rect localRect1 = (Rect)this.possibleRects.get(localObject);
        Rect localRect2 = (Rect)this.possibleRects.get(localInteger);
        if (localRect1.width() * localRect1.height() < localRect2.width() * localRect2.height())
          localObject = localInteger;
      }
    }
  }

  public static Rect getFrameForView(View paramView)
  {
    int[] arrayOfInt = new int[2];
    paramView.getLocationOnScreen(arrayOfInt);
    return new Rect(arrayOfInt[0], arrayOfInt[1], arrayOfInt[0] + paramView.getWidth(), arrayOfInt[1] + paramView.getHeight());
  }

  private Rect getRectForArrowDown(Rect paramRect)
  {
    int i = this.popoverLayoutRect.width();
    int j = i;
    if (i < 0)
      j = 0;
    int k = paramRect.top - this.popoverLayoutRect.top;
    i = k;
    if (k < 0)
      i = 0;
    k = j;
    j = k;
    if (this.realContentSize.x > 0)
    {
      j = k;
      if (this.realContentSize.x < k)
        j = this.realContentSize.x;
    }
    k = i;
    if (this.realContentSize.y > 0)
    {
      k = i;
      if (this.realContentSize.y < i)
        k = this.realContentSize.y;
    }
    int m = paramRect.centerX() - this.popoverLayoutRect.left - j / 2;
    if (m < 0)
      i = 0;
    while (true)
    {
      m = paramRect.top - this.popoverLayoutRect.top - k;
      return new Rect(i, m, i + j, m + k);
      i = m;
      if (m + j > this.popoverLayoutRect.width())
        i = this.popoverLayoutRect.width() - j;
    }
  }

  private Rect getRectForArrowLeft(Rect paramRect)
  {
    int i = this.popoverLayoutRect.width() - (paramRect.right - this.popoverLayoutRect.left);
    int j = i;
    if (i < 0)
      j = 0;
    int k = this.popoverLayoutRect.height();
    i = k;
    if (k < 0)
      i = 0;
    k = j;
    j = k;
    if (this.realContentSize.x > 0)
    {
      j = k;
      if (this.realContentSize.x < k)
        j = this.realContentSize.x;
    }
    k = i;
    if (this.realContentSize.y > 0)
    {
      k = i;
      if (this.realContentSize.y < i)
        k = this.realContentSize.y;
    }
    int n = paramRect.right - this.popoverLayoutRect.left;
    int m = paramRect.centerY() - this.popoverLayoutRect.top - k / 2;
    if (m < 0)
      i = 0;
    while (true)
    {
      return new Rect(n, i, n + j, i + k);
      i = m;
      if (m + k > this.popoverLayoutRect.height())
        i = this.popoverLayoutRect.height() - k;
    }
  }

  private Rect getRectForArrowRight(Rect paramRect)
  {
    int i = paramRect.left - this.popoverLayoutRect.left;
    int j = i;
    if (i < 0)
      j = 0;
    int k = this.popoverLayoutRect.height();
    i = k;
    if (k < 0)
      i = 0;
    k = j;
    j = k;
    if (this.realContentSize.x > 0)
    {
      j = k;
      if (this.realContentSize.x < k)
        j = this.realContentSize.x;
    }
    k = i;
    if (this.realContentSize.y > 0)
    {
      k = i;
      if (this.realContentSize.y < i)
        k = this.realContentSize.y;
    }
    int n = paramRect.left - this.popoverLayoutRect.left - j;
    int m = paramRect.centerY() - this.popoverLayoutRect.top - k / 2;
    if (m < 0)
      i = 0;
    while (true)
    {
      return new Rect(n, i, n + j, i + k);
      i = m;
      if (m + k > this.popoverLayoutRect.height())
        i = this.popoverLayoutRect.height() - k;
    }
  }

  private Rect getRectForArrowUp(Rect paramRect)
  {
    int i = this.popoverLayoutRect.width();
    int j = i;
    if (i < 0)
      j = 0;
    int k = this.popoverLayoutRect.height() - (paramRect.bottom - this.popoverLayoutRect.top);
    i = k;
    if (k < 0)
      i = 0;
    k = j;
    j = k;
    if (this.realContentSize.x > 0)
    {
      j = k;
      if (this.realContentSize.x < k)
        j = this.realContentSize.x;
    }
    k = i;
    if (this.realContentSize.y > 0)
    {
      k = i;
      if (this.realContentSize.y < i)
        k = this.realContentSize.y;
    }
    int m = paramRect.centerX() - this.popoverLayoutRect.left - j / 2;
    if (m < 0)
      i = 0;
    while (true)
    {
      m = paramRect.bottom - this.popoverLayoutRect.top;
      return new Rect(i, m, i + j, m + k);
      i = m;
      if (m + j > this.popoverLayoutRect.width())
        i = this.popoverLayoutRect.width() - j;
    }
  }

  private void initArrow(Rect paramRect, Integer paramInteger)
  {
    if (this.arrowImageView != null)
      removeView(this.arrowImageView);
    this.arrowImageView = new ImageView(getContext());
    Drawable localDrawable = null;
    int k = 0;
    int j = 0;
    int m = 0;
    int i = 0;
    if (paramInteger.intValue() == 1)
    {
      localDrawable = this.arrowUpDrawable;
      j = localDrawable.getIntrinsicWidth();
      i = localDrawable.getIntrinsicHeight();
      k = paramRect.centerX() - j / 2 - this.popoverLayoutRect.left;
      m = paramRect.bottom - this.popoverLayoutRect.top;
    }
    while (true)
    {
      this.arrowImageView.setImageDrawable(localDrawable);
      paramRect = new RelativeLayout.LayoutParams(j, i);
      paramRect.leftMargin = k;
      paramRect.topMargin = m;
      addView(this.arrowImageView, paramRect);
      return;
      if (paramInteger.intValue() == 2)
      {
        localDrawable = this.arrowDownDrawable;
        j = localDrawable.getIntrinsicWidth();
        i = localDrawable.getIntrinsicHeight();
        k = paramRect.centerX() - j / 2 - this.popoverLayoutRect.left;
        m = paramRect.top - i - this.popoverLayoutRect.top;
      }
      else if (paramInteger.intValue() == 4)
      {
        localDrawable = this.arrowLeftDrawable;
        j = localDrawable.getIntrinsicWidth();
        i = localDrawable.getIntrinsicHeight();
        k = paramRect.right - this.popoverLayoutRect.left;
        m = paramRect.centerY() - i / 2 - this.popoverLayoutRect.top;
      }
      else if (paramInteger.intValue() == 8)
      {
        localDrawable = this.arrowRightDrawable;
        j = localDrawable.getIntrinsicWidth();
        i = localDrawable.getIntrinsicHeight();
        k = paramRect.left - j - this.popoverLayoutRect.left;
        m = paramRect.centerY() - i / 2 - this.popoverLayoutRect.top;
      }
    }
  }

  private void initPopoverView()
  {
    this.popoverView = new RelativeLayout(getContext());
    setBackgroundColor(-1);
    setOnTouchListener(this);
  }

  public void dissmissPopover(boolean paramBoolean)
  {
    if (this.popoverViewListener != null)
      this.popoverViewListener.popoverViewWillDismiss(this);
    if (!paramBoolean)
    {
      this.popoverView.removeAllViews();
      removeAllViews();
      this.superview.removeView(this);
      if (this.popoverViewListener != null)
        this.popoverViewListener.popoverViewDidDismiss(this);
    }
    while (this.isAnimating)
      return;
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(1.0F, 0.0F);
    localAlphaAnimation.setDuration(this.fadeAnimationTime);
    localAlphaAnimation.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        AbPopoverView.this.popoverView.removeAllViews();
        AbPopoverView.this.removeAllViews();
        AbPopoverView.this.superview.removeView(AbPopoverView.this);
        AbPopoverView.this.isAnimating = false;
        if (AbPopoverView.this.popoverViewListener != null)
          AbPopoverView.this.popoverViewListener.popoverViewDidDismiss(AbPopoverView.this);
      }

      public void onAnimationRepeat(Animation paramAnonymousAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnonymousAnimation)
      {
      }
    });
    this.isAnimating = true;
    startAnimation(localAlphaAnimation);
  }

  public Drawable getArrowDownDrawable()
  {
    return this.arrowDownDrawable;
  }

  public Drawable getArrowLeftDrawable()
  {
    return this.arrowLeftDrawable;
  }

  public Drawable getArrowRightDrawable()
  {
    return this.arrowRightDrawable;
  }

  public Drawable getArrowUpDrawable()
  {
    return this.arrowUpDrawable;
  }

  public Drawable getBackgroundDrawable()
  {
    return this.backgroundDrawable;
  }

  public Point getContentSizeForViewInPopover()
  {
    return this.contentSizeForViewInPopover;
  }

  public int getFadeAnimationTime()
  {
    return this.fadeAnimationTime;
  }

  public View getPopoverContentView()
  {
    return this.popoverContentView;
  }

  public PopoverViewListener getPopoverViewListener()
  {
    return this.popoverViewListener;
  }

  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if ((!this.isAnimating) && (paramView == this))
      dissmissPopover(true);
    return true;
  }

  public void setArrowDownDrawable(Drawable paramDrawable)
  {
    this.arrowDownDrawable = paramDrawable;
  }

  public void setArrowLeftDrawable(Drawable paramDrawable)
  {
    this.arrowLeftDrawable = paramDrawable;
  }

  public void setArrowRightDrawable(Drawable paramDrawable)
  {
    this.arrowRightDrawable = paramDrawable;
  }

  public void setArrowUpDrawable(Drawable paramDrawable)
  {
    this.arrowUpDrawable = paramDrawable;
  }

  public void setBackgroundDrawable(Drawable paramDrawable)
  {
    this.backgroundDrawable = paramDrawable;
    this.popoverView.setBackgroundDrawable(paramDrawable);
  }

  public void setContentSizeForViewInPopover(Point paramPoint)
  {
    this.contentSizeForViewInPopover = paramPoint;
    this.realContentSize = new Point(paramPoint);
    paramPoint = this.realContentSize;
    paramPoint.x += this.popoverView.getPaddingLeft() + this.popoverView.getPaddingRight();
    paramPoint = this.realContentSize;
    paramPoint.y += this.popoverView.getPaddingTop() + this.popoverView.getPaddingBottom();
  }

  public void setFadeAnimationTime(int paramInt)
  {
    this.fadeAnimationTime = paramInt;
  }

  public void setPopoverContentView(View paramView)
  {
    this.popoverContentView = paramView;
    this.popoverView.removeAllViews();
    this.popoverView.addView(paramView, -1, -1);
  }

  public void setPopoverViewListener(PopoverViewListener paramPopoverViewListener)
  {
    this.popoverViewListener = paramPopoverViewListener;
  }

  public void showPopoverFromRectInViewGroup(ViewGroup paramViewGroup, Rect paramRect, int paramInt, boolean paramBoolean)
  {
    if (this.popoverViewListener != null)
      this.popoverViewListener.popoverViewWillShow(this);
    this.superview = paramViewGroup;
    paramViewGroup.addView(this, new ViewGroup.LayoutParams(-1, -1));
    this.popoverLayoutRect = getFrameForView(this.superview);
    addAvailableRects(paramRect, paramInt);
    paramViewGroup = getBestRect();
    addPopoverInRect((Rect)this.possibleRects.get(paramViewGroup));
    initArrow(paramRect, paramViewGroup);
    if (!paramBoolean)
      if (this.popoverViewListener != null)
        this.popoverViewListener.popoverViewDidShow(this);
    while (this.isAnimating)
      return;
    paramViewGroup = new AlphaAnimation(0.0F, 1.0F);
    paramViewGroup.setDuration(this.fadeAnimationTime);
    paramViewGroup.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        AbPopoverView.this.isAnimating = false;
        if (AbPopoverView.this.popoverViewListener != null)
          AbPopoverView.this.popoverViewListener.popoverViewDidShow(AbPopoverView.this);
      }

      public void onAnimationRepeat(Animation paramAnonymousAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnonymousAnimation)
      {
      }
    });
    this.isAnimating = true;
    startAnimation(paramViewGroup);
  }

  public static abstract interface PopoverViewListener
  {
    public abstract void popoverViewDidDismiss(AbPopoverView paramAbPopoverView);

    public abstract void popoverViewDidShow(AbPopoverView paramAbPopoverView);

    public abstract void popoverViewWillDismiss(AbPopoverView paramAbPopoverView);

    public abstract void popoverViewWillShow(AbPopoverView paramAbPopoverView);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.app.AbPopoverView
 * JD-Core Version:    0.6.2
 */