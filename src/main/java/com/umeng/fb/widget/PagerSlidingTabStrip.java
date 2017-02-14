package com.umeng.fb.widget;

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
import com.umeng.fb.res.d;
import java.util.Locale;

public class PagerSlidingTabStrip extends HorizontalScrollView
{
  private static final int[] b = { 16842901, 16842904 };
  private int A = -10066330;
  private Typeface B = null;
  private int C = 0;
  private int D = 0;
  private int E;
  private Locale F;
  public ViewPager.OnPageChangeListener a;
  private LinearLayout.LayoutParams c;
  private LinearLayout.LayoutParams d;
  private final b e = new b(null);
  private LinearLayout f;
  private ViewPager g;
  private int h;
  private int i = 0;
  private int j = 0;
  private float k = 0.0F;
  private Paint l;
  private Paint m;
  private int n = -10066330;
  private int o = 436207616;
  private int p = 436207616;
  private boolean q = false;
  private boolean r = true;
  private int s = 52;
  private int t = 8;
  private int u = 2;
  private int v = 12;
  private int w = 24;
  private int x = 1;
  private int y = 12;
  private int z = -10066330;

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
    this.E = d.e(paramContext);
    this.f = new LinearLayout(paramContext);
    this.f.setOrientation(0);
    this.f.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
    addView(this.f);
    DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
    this.s = ((int)TypedValue.applyDimension(1, this.s, localDisplayMetrics));
    this.t = ((int)TypedValue.applyDimension(1, this.t, localDisplayMetrics));
    this.u = ((int)TypedValue.applyDimension(1, this.u, localDisplayMetrics));
    this.v = ((int)TypedValue.applyDimension(1, this.v, localDisplayMetrics));
    this.w = ((int)TypedValue.applyDimension(1, this.w, localDisplayMetrics));
    this.x = ((int)TypedValue.applyDimension(1, this.x, localDisplayMetrics));
    this.y = ((int)TypedValue.applyDimension(2, this.y, localDisplayMetrics));
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, b);
    this.y = paramContext.getDimensionPixelSize(0, this.y);
    this.z = paramContext.getColor(1, this.z);
    paramContext.recycle();
    this.l = new Paint();
    this.l.setAntiAlias(true);
    this.l.setStyle(Paint.Style.FILL);
    this.m = new Paint();
    this.m.setAntiAlias(true);
    this.m.setStrokeWidth(this.x);
    this.c = new LinearLayout.LayoutParams(-2, -1);
    this.d = new LinearLayout.LayoutParams(0, -1, 1.0F);
    if (this.F == null)
      this.F = getResources().getConfiguration().locale;
  }

  private void a(int paramInt1, int paramInt2)
  {
    ImageButton localImageButton = new ImageButton(getContext());
    localImageButton.setImageResource(paramInt2);
    a(paramInt1, localImageButton);
  }

  private void a(final int paramInt, View paramView)
  {
    paramView.setFocusable(true);
    paramView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PagerSlidingTabStrip.a(PagerSlidingTabStrip.this).setCurrentItem(paramInt);
      }
    });
    paramView.setPadding(this.w, 0, this.w, 0);
    LinearLayout localLinearLayout = this.f;
    if (this.q);
    for (LinearLayout.LayoutParams localLayoutParams = this.d; ; localLayoutParams = this.c)
    {
      localLinearLayout.addView(paramView, paramInt, localLayoutParams);
      return;
    }
  }

  private void a(int paramInt, String paramString)
  {
    TextView localTextView = new TextView(getContext());
    localTextView.setText(paramString);
    localTextView.setGravity(17);
    localTextView.setSingleLine();
    a(paramInt, localTextView);
  }

  private void b(int paramInt1, int paramInt2)
  {
    if (this.h == 0);
    do
    {
      return;
      int i1 = this.f.getChildAt(paramInt1).getLeft() + paramInt2;
      if (paramInt1 <= 0)
      {
        paramInt1 = i1;
        if (paramInt2 <= 0);
      }
      else
      {
        paramInt1 = i1 - this.s;
      }
    }
    while (paramInt1 == this.D);
    this.D = paramInt1;
    scrollTo(paramInt1, 0);
  }

  private void c()
  {
    int i1 = 0;
    if (i1 < this.h)
    {
      Object localObject = this.f.getChildAt(i1);
      ((View)localObject).setBackgroundResource(this.E);
      if ((localObject instanceof TextView))
      {
        localObject = (TextView)localObject;
        ((TextView)localObject).setTextSize(0, this.y);
        ((TextView)localObject).setTypeface(this.B, this.C);
        ((TextView)localObject).setTextColor(this.z);
        if (this.r)
        {
          if (Build.VERSION.SDK_INT < 14)
            break label112;
          ((TextView)localObject).setAllCaps(true);
        }
      }
      while (true)
      {
        if (i1 == this.j)
          ((TextView)localObject).setTextColor(this.A);
        i1 += 1;
        break;
        label112: ((TextView)localObject).setText(((TextView)localObject).getText().toString().toUpperCase(this.F));
      }
    }
  }

  public void a()
  {
    this.f.removeAllViews();
    this.h = this.g.getAdapter().getCount();
    int i1 = 0;
    if (i1 < this.h)
    {
      if ((this.g.getAdapter() instanceof a))
        a(i1, ((a)this.g.getAdapter()).a(i1));
      while (true)
      {
        i1 += 1;
        break;
        a(i1, this.g.getAdapter().getPageTitle(i1).toString());
      }
    }
    c();
    getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
    {
      public void onGlobalLayout()
      {
        PagerSlidingTabStrip.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        PagerSlidingTabStrip.a(PagerSlidingTabStrip.this, PagerSlidingTabStrip.a(PagerSlidingTabStrip.this).getCurrentItem());
        PagerSlidingTabStrip.a(PagerSlidingTabStrip.this, PagerSlidingTabStrip.b(PagerSlidingTabStrip.this), 0);
      }
    });
  }

  public void a(Typeface paramTypeface, int paramInt)
  {
    this.B = paramTypeface;
    this.C = paramInt;
    c();
  }

  public boolean b()
  {
    return this.r;
  }

  public int getDividerColor()
  {
    return this.p;
  }

  public int getDividerPadding()
  {
    return this.v;
  }

  public int getIndicatorColor()
  {
    return this.n;
  }

  public int getIndicatorHeight()
  {
    return this.t;
  }

  public int getScrollOffset()
  {
    return this.s;
  }

  public int getSelectedTextColor()
  {
    return this.A;
  }

  public boolean getShouldExpand()
  {
    return this.q;
  }

  public int getTabBackground()
  {
    return this.E;
  }

  public int getTabPaddingLeftRight()
  {
    return this.w;
  }

  public int getTextColor()
  {
    return this.z;
  }

  public int getTextSize()
  {
    return this.y;
  }

  public int getUnderlineColor()
  {
    return this.o;
  }

  public int getUnderlineHeight()
  {
    return this.u;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if ((isInEditMode()) || (this.h == 0))
      return;
    int i2 = getHeight();
    this.l.setColor(this.o);
    paramCanvas.drawRect(0.0F, i2 - this.u, this.f.getWidth(), i2, this.l);
    this.l.setColor(this.n);
    View localView = this.f.getChildAt(this.i);
    float f1 = localView.getLeft();
    float f2 = localView.getRight();
    if ((this.k > 0.0F) && (this.i < this.h - 1))
    {
      localView = this.f.getChildAt(this.i + 1);
      float f4 = localView.getLeft();
      float f3 = localView.getRight();
      f1 = this.k * f4 + f1 * (1.0F - this.k);
      f2 = this.k * f3 + (1.0F - this.k) * f2;
    }
    while (true)
    {
      paramCanvas.drawRect(f1, i2 - this.t, f2, i2, this.l);
      this.m.setColor(this.p);
      int i1 = 0;
      while (i1 < this.h - 1)
      {
        localView = this.f.getChildAt(i1);
        paramCanvas.drawLine(localView.getRight(), this.v, localView.getRight(), i2 - this.v, this.m);
        i1 += 1;
      }
      break;
    }
  }

  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    paramParcelable = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    this.i = paramParcelable.a;
    requestLayout();
  }

  public Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    localSavedState.a = this.i;
    return localSavedState;
  }

  public void setAllCaps(boolean paramBoolean)
  {
    this.r = paramBoolean;
  }

  public void setDividerColor(int paramInt)
  {
    this.p = paramInt;
    invalidate();
  }

  public void setDividerColorResource(int paramInt)
  {
    this.p = getResources().getColor(paramInt);
    invalidate();
  }

  public void setDividerPadding(int paramInt)
  {
    this.v = paramInt;
    invalidate();
  }

  public void setIndicatorColor(int paramInt)
  {
    this.n = paramInt;
    invalidate();
  }

  public void setIndicatorColorResource(int paramInt)
  {
    this.n = getResources().getColor(paramInt);
    invalidate();
  }

  public void setIndicatorHeight(int paramInt)
  {
    this.t = paramInt;
    invalidate();
  }

  public void setOnPageChangeListener(ViewPager.OnPageChangeListener paramOnPageChangeListener)
  {
    this.a = paramOnPageChangeListener;
  }

  public void setScrollOffset(int paramInt)
  {
    this.s = paramInt;
    invalidate();
  }

  public void setSelectedTextColor(int paramInt)
  {
    this.A = paramInt;
    c();
  }

  public void setSelectedTextColorResource(int paramInt)
  {
    this.A = getResources().getColor(paramInt);
    c();
  }

  public void setShouldExpand(boolean paramBoolean)
  {
    this.q = paramBoolean;
    a();
  }

  public void setTabBackground(int paramInt)
  {
    this.E = paramInt;
    c();
  }

  public void setTabPaddingLeftRight(int paramInt)
  {
    this.w = paramInt;
    c();
  }

  public void setTextColor(int paramInt)
  {
    this.z = paramInt;
    c();
  }

  public void setTextColorResource(int paramInt)
  {
    this.z = getResources().getColor(paramInt);
    c();
  }

  public void setTextSize(int paramInt)
  {
    this.y = paramInt;
    c();
  }

  public void setUnderlineColor(int paramInt)
  {
    this.o = paramInt;
    invalidate();
  }

  public void setUnderlineColorResource(int paramInt)
  {
    this.o = getResources().getColor(paramInt);
    invalidate();
  }

  public void setUnderlineHeight(int paramInt)
  {
    this.u = paramInt;
    invalidate();
  }

  public void setViewPager(ViewPager paramViewPager)
  {
    this.g = paramViewPager;
    if (paramViewPager.getAdapter() == null)
      throw new IllegalStateException("ViewPager does not have adapter instance.");
    paramViewPager.setOnPageChangeListener(this.e);
    a();
  }

  static class SavedState extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator()
    {
      public PagerSlidingTabStrip.SavedState a(Parcel paramAnonymousParcel)
      {
        return new PagerSlidingTabStrip.SavedState(paramAnonymousParcel, null);
      }

      public PagerSlidingTabStrip.SavedState[] a(int paramAnonymousInt)
      {
        return new PagerSlidingTabStrip.SavedState[paramAnonymousInt];
      }
    };
    int a;

    private SavedState(Parcel paramParcel)
    {
      super();
      this.a = paramParcel.readInt();
    }

    public SavedState(Parcelable paramParcelable)
    {
      super();
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(this.a);
    }
  }

  public static abstract interface a
  {
    public abstract int a(int paramInt);
  }

  private class b
    implements ViewPager.OnPageChangeListener
  {
    private b()
    {
    }

    public void onPageScrollStateChanged(int paramInt)
    {
      if (paramInt == 0)
        PagerSlidingTabStrip.a(PagerSlidingTabStrip.this, PagerSlidingTabStrip.a(PagerSlidingTabStrip.this).getCurrentItem(), 0);
      if (PagerSlidingTabStrip.this.a != null)
        PagerSlidingTabStrip.this.a.onPageScrollStateChanged(paramInt);
    }

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
    {
      PagerSlidingTabStrip.a(PagerSlidingTabStrip.this, paramInt1);
      PagerSlidingTabStrip.a(PagerSlidingTabStrip.this, paramFloat);
      PagerSlidingTabStrip.a(PagerSlidingTabStrip.this, paramInt1, (int)(PagerSlidingTabStrip.c(PagerSlidingTabStrip.this).getChildAt(paramInt1).getWidth() * paramFloat));
      PagerSlidingTabStrip.this.invalidate();
      if (PagerSlidingTabStrip.this.a != null)
        PagerSlidingTabStrip.this.a.onPageScrolled(paramInt1, paramFloat, paramInt2);
    }

    public void onPageSelected(int paramInt)
    {
      PagerSlidingTabStrip.b(PagerSlidingTabStrip.this, paramInt);
      PagerSlidingTabStrip.d(PagerSlidingTabStrip.this);
      if (PagerSlidingTabStrip.this.a != null)
        PagerSlidingTabStrip.this.a.onPageSelected(paramInt);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.widget.PagerSlidingTabStrip
 * JD-Core Version:    0.6.2
 */