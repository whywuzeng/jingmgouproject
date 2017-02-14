package com.ab.view.slidingmenu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import java.lang.reflect.Method;

public class SlidingMenu extends RelativeLayout
{
  public static final int LEFT = 0;
  public static final int LEFT_RIGHT = 2;
  public static final int RIGHT = 1;
  public static final int SLIDING_CONTENT = 1;
  public static final int SLIDING_WINDOW = 0;
  private static final String TAG = "SlidingMenu";
  public static final int TOUCHMODE_FULLSCREEN = 1;
  public static final int TOUCHMODE_MARGIN = 0;
  public static final int TOUCHMODE_NONE = 2;
  private boolean mActionbarOverlay = false;
  private OnCloseListener mCloseListener;
  private Handler mHandler = new Handler();
  private OnOpenListener mOpenListener;
  private OnOpenListener mSecondaryOpenListner;
  private CustomViewAbove mViewAbove;
  private CustomViewBehind mViewBehind;

  public SlidingMenu(Activity paramActivity, int paramInt)
  {
    this(paramActivity, null);
    attachToActivity(paramActivity, paramInt);
  }

  public SlidingMenu(Context paramContext)
  {
    this(paramContext, null);
  }

  public SlidingMenu(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public SlidingMenu(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramAttributeSet = new RelativeLayout.LayoutParams(-1, -1);
    this.mViewBehind = new CustomViewBehind(paramContext);
    addView(this.mViewBehind, paramAttributeSet);
    paramAttributeSet = new RelativeLayout.LayoutParams(-1, -1);
    this.mViewAbove = new CustomViewAbove(paramContext);
    addView(this.mViewAbove, paramAttributeSet);
    this.mViewAbove.setCustomViewBehind(this.mViewBehind);
    this.mViewBehind.setCustomViewAbove(this.mViewAbove);
    this.mViewAbove.setOnPageChangeListener(new CustomViewAbove.OnPageChangeListener()
    {
      public static final int POSITION_CLOSE = 1;
      public static final int POSITION_OPEN = 0;
      public static final int POSITION_SECONDARY_OPEN = 2;

      public void onPageScrolled(int paramAnonymousInt1, float paramAnonymousFloat, int paramAnonymousInt2)
      {
      }

      public void onPageSelected(int paramAnonymousInt)
      {
        if ((paramAnonymousInt == 0) && (SlidingMenu.this.mOpenListener != null))
          SlidingMenu.this.mOpenListener.onOpen();
        do
        {
          return;
          if ((paramAnonymousInt == 1) && (SlidingMenu.this.mCloseListener != null))
          {
            SlidingMenu.this.mCloseListener.onClose();
            return;
          }
        }
        while ((paramAnonymousInt != 2) || (SlidingMenu.this.mSecondaryOpenListner == null));
        SlidingMenu.this.mSecondaryOpenListner.onOpen();
      }
    });
    setMode(0);
    if (-1 != -1)
    {
      setContent(-1);
      if (-1 == -1)
        break label192;
      setMenu(-1);
    }
    while (true)
    {
      setTouchModeAbove(0);
      setTouchModeBehind(0);
      if ((-1 == -1) || (-1 == -1))
        break label207;
      throw new IllegalStateException("Cannot set both behindOffset and behindWidth for a SlidingMenu");
      setContent(new FrameLayout(paramContext));
      break;
      label192: setMenu(new FrameLayout(paramContext));
    }
    label207: if (-1 != -1)
      setBehindOffset(-1);
    while (true)
    {
      setBehindScrollScale(0.33F);
      if (-1 != -1)
        setShadowDrawable(-1);
      setShadowWidth(0);
      setFadeEnabled(true);
      setFadeDegree(0.33F);
      setSelectorEnabled(false);
      if (-1 != -1)
        setSelectorDrawable(-1);
      return;
      if (-1 != -1)
        setBehindWidth(-1);
      else
        setBehindOffset(0);
    }
  }

  public void addIgnoredView(View paramView)
  {
    this.mViewAbove.addIgnoredView(paramView);
  }

  public void attachToActivity(Activity paramActivity, int paramInt)
  {
    attachToActivity(paramActivity, paramInt, false);
  }

  public void attachToActivity(Activity paramActivity, int paramInt, boolean paramBoolean)
  {
    if ((paramInt != 0) && (paramInt != 1))
      throw new IllegalArgumentException("slideStyle must be either SLIDING_WINDOW or SLIDING_CONTENT");
    if (getParent() != null)
      throw new IllegalStateException("This SlidingMenu appears to already be attached");
    Object localObject = paramActivity.getTheme().obtainStyledAttributes(new int[] { 16842836 });
    int i = ((TypedArray)localObject).getResourceId(0, 0);
    ((TypedArray)localObject).recycle();
    switch (paramInt)
    {
    default:
    case 0:
    case 1:
    }
    do
    {
      return;
      this.mActionbarOverlay = false;
      paramActivity = (ViewGroup)paramActivity.getWindow().getDecorView();
      localObject = (ViewGroup)paramActivity.getChildAt(0);
      ((ViewGroup)localObject).setBackgroundResource(i);
      paramActivity.removeView((View)localObject);
      paramActivity.addView(this);
      setContent((View)localObject);
      return;
      this.mActionbarOverlay = paramBoolean;
      paramActivity = (ViewGroup)paramActivity.findViewById(16908290);
      localObject = paramActivity.getChildAt(0);
      paramActivity.removeView((View)localObject);
      paramActivity.addView(this);
      setContent((View)localObject);
    }
    while (((View)localObject).getBackground() != null);
    ((View)localObject).setBackgroundResource(i);
  }

  public void clearIgnoredViews()
  {
    this.mViewAbove.clearIgnoredViews();
  }

  @SuppressLint({"NewApi"})
  protected boolean fitSystemWindows(Rect paramRect)
  {
    int i = paramRect.left;
    int j = paramRect.right;
    int k = paramRect.top;
    int m = paramRect.bottom;
    if (!this.mActionbarOverlay)
    {
      Log.v("SlidingMenu", "setting padding!");
      setPadding(i, k, j, m);
    }
    return true;
  }

  public int getBehindOffset()
  {
    return ((RelativeLayout.LayoutParams)this.mViewBehind.getLayoutParams()).rightMargin;
  }

  public float getBehindScrollScale()
  {
    return this.mViewBehind.getScrollScale();
  }

  public View getContent()
  {
    return this.mViewAbove.getContent();
  }

  public View getMenu()
  {
    return this.mViewBehind.getContent();
  }

  public int getMode()
  {
    return this.mViewBehind.getMode();
  }

  public View getSecondaryMenu()
  {
    return this.mViewBehind.getSecondaryContent();
  }

  public int getTouchModeAbove()
  {
    return this.mViewAbove.getTouchMode();
  }

  public int getTouchmodeMarginThreshold()
  {
    return this.mViewBehind.getMarginThreshold();
  }

  public boolean isMenuShowing()
  {
    return (this.mViewAbove.getCurrentItem() == 0) || (this.mViewAbove.getCurrentItem() == 2);
  }

  public boolean isSecondaryMenuShowing()
  {
    return this.mViewAbove.getCurrentItem() == 2;
  }

  public boolean isSlidingEnabled()
  {
    return this.mViewAbove.isSlidingEnabled();
  }

  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    paramParcelable = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    this.mViewAbove.setCurrentItem(paramParcelable.getItem());
  }

  protected Parcelable onSaveInstanceState()
  {
    return new SavedState(super.onSaveInstanceState(), this.mViewAbove.getCurrentItem());
  }

  public void removeIgnoredView(View paramView)
  {
    this.mViewAbove.removeIgnoredView(paramView);
  }

  public void setAboveOffset(int paramInt)
  {
    this.mViewAbove.setAboveOffset(paramInt);
  }

  public void setAboveOffsetRes(int paramInt)
  {
    setAboveOffset((int)getContext().getResources().getDimension(paramInt));
  }

  public void setBehindCanvasTransformer(CanvasTransformer paramCanvasTransformer)
  {
    this.mViewBehind.setCanvasTransformer(paramCanvasTransformer);
  }

  public void setBehindOffset(int paramInt)
  {
    this.mViewBehind.setWidthOffset(paramInt);
  }

  public void setBehindOffsetRes(int paramInt)
  {
    setBehindOffset((int)getContext().getResources().getDimension(paramInt));
  }

  public void setBehindScrollScale(float paramFloat)
  {
    if ((paramFloat < 0.0F) && (paramFloat > 1.0F))
      throw new IllegalStateException("ScrollScale must be between 0 and 1");
    this.mViewBehind.setScrollScale(paramFloat);
  }

  public void setBehindWidth(int paramInt)
  {
    Display localDisplay = ((WindowManager)getContext().getSystemService("window")).getDefaultDisplay();
    try
    {
      Point localPoint = new Point();
      Display.class.getMethod("getSize", new Class[] { Point.class }).invoke(localDisplay, new Object[] { localPoint });
      i = localPoint.x;
      setBehindOffset(i - paramInt);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        int i = localDisplay.getWidth();
    }
  }

  public void setBehindWidthRes(int paramInt)
  {
    setBehindWidth((int)getContext().getResources().getDimension(paramInt));
  }

  public void setContent(int paramInt)
  {
    setContent(LayoutInflater.from(getContext()).inflate(paramInt, null));
  }

  public void setContent(View paramView)
  {
    this.mViewAbove.setContent(paramView);
    showContent();
  }

  public void setFadeDegree(float paramFloat)
  {
    this.mViewBehind.setFadeDegree(paramFloat);
  }

  public void setFadeEnabled(boolean paramBoolean)
  {
    this.mViewBehind.setFadeEnabled(paramBoolean);
  }

  public void setMenu(int paramInt)
  {
    setMenu(LayoutInflater.from(getContext()).inflate(paramInt, null));
  }

  public void setMenu(View paramView)
  {
    this.mViewBehind.setContent(paramView);
  }

  public void setMode(int paramInt)
  {
    if ((paramInt != 0) && (paramInt != 1) && (paramInt != 2))
      throw new IllegalStateException("SlidingMenu mode must be LEFT, RIGHT, or LEFT_RIGHT");
    this.mViewBehind.setMode(paramInt);
  }

  public void setOnCloseListener(OnCloseListener paramOnCloseListener)
  {
    this.mCloseListener = paramOnCloseListener;
  }

  public void setOnClosedListener(OnClosedListener paramOnClosedListener)
  {
    this.mViewAbove.setOnClosedListener(paramOnClosedListener);
  }

  public void setOnOpenListener(OnOpenListener paramOnOpenListener)
  {
    this.mOpenListener = paramOnOpenListener;
  }

  public void setOnOpenedListener(OnOpenedListener paramOnOpenedListener)
  {
    this.mViewAbove.setOnOpenedListener(paramOnOpenedListener);
  }

  public void setSecondaryMenu(int paramInt)
  {
    setSecondaryMenu(LayoutInflater.from(getContext()).inflate(paramInt, null));
  }

  public void setSecondaryMenu(View paramView)
  {
    this.mViewBehind.setSecondaryContent(paramView);
  }

  public void setSecondaryOnOpenListner(OnOpenListener paramOnOpenListener)
  {
    this.mSecondaryOpenListner = paramOnOpenListener;
  }

  public void setSecondaryShadowDrawable(int paramInt)
  {
    setSecondaryShadowDrawable(getContext().getResources().getDrawable(paramInt));
  }

  public void setSecondaryShadowDrawable(Drawable paramDrawable)
  {
    this.mViewBehind.setSecondaryShadowDrawable(paramDrawable);
  }

  public void setSelectedView(View paramView)
  {
    this.mViewBehind.setSelectedView(paramView);
  }

  public void setSelectorBitmap(Bitmap paramBitmap)
  {
    this.mViewBehind.setSelectorBitmap(paramBitmap);
  }

  public void setSelectorDrawable(int paramInt)
  {
    this.mViewBehind.setSelectorBitmap(BitmapFactory.decodeResource(getResources(), paramInt));
  }

  public void setSelectorEnabled(boolean paramBoolean)
  {
    this.mViewBehind.setSelectorEnabled(true);
  }

  public void setShadowDrawable(int paramInt)
  {
    setShadowDrawable(getContext().getResources().getDrawable(paramInt));
  }

  public void setShadowDrawable(Drawable paramDrawable)
  {
    this.mViewBehind.setShadowDrawable(paramDrawable);
  }

  public void setShadowWidth(int paramInt)
  {
    this.mViewBehind.setShadowWidth(paramInt);
  }

  public void setShadowWidthRes(int paramInt)
  {
    setShadowWidth((int)getResources().getDimension(paramInt));
  }

  public void setSlidingEnabled(boolean paramBoolean)
  {
    this.mViewAbove.setSlidingEnabled(paramBoolean);
  }

  public void setStatic(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      setSlidingEnabled(false);
      this.mViewAbove.setCustomViewBehind(null);
      this.mViewAbove.setCurrentItem(1);
      return;
    }
    this.mViewAbove.setCurrentItem(1);
    this.mViewAbove.setCustomViewBehind(this.mViewBehind);
    setSlidingEnabled(true);
  }

  public void setTouchModeAbove(int paramInt)
  {
    if ((paramInt != 1) && (paramInt != 0) && (paramInt != 2))
      throw new IllegalStateException("TouchMode must be set to eitherTOUCHMODE_FULLSCREEN or TOUCHMODE_MARGIN or TOUCHMODE_NONE.");
    this.mViewAbove.setTouchMode(paramInt);
  }

  public void setTouchModeBehind(int paramInt)
  {
    if ((paramInt != 1) && (paramInt != 0) && (paramInt != 2))
      throw new IllegalStateException("TouchMode must be set to eitherTOUCHMODE_FULLSCREEN or TOUCHMODE_MARGIN or TOUCHMODE_NONE.");
    this.mViewBehind.setTouchMode(paramInt);
  }

  public void setTouchmodeMarginThreshold(int paramInt)
  {
    this.mViewBehind.setMarginThreshold(paramInt);
  }

  public void showContent()
  {
    showContent(true);
  }

  public void showContent(boolean paramBoolean)
  {
    this.mViewAbove.setCurrentItem(1, paramBoolean);
  }

  public void showMenu()
  {
    showMenu(true);
  }

  public void showMenu(boolean paramBoolean)
  {
    this.mViewAbove.setCurrentItem(0, paramBoolean);
  }

  public void showSecondaryMenu()
  {
    showSecondaryMenu(true);
  }

  public void showSecondaryMenu(boolean paramBoolean)
  {
    this.mViewAbove.setCurrentItem(2, paramBoolean);
  }

  public void toggle()
  {
    toggle(true);
  }

  public void toggle(boolean paramBoolean)
  {
    if (isMenuShowing())
    {
      showContent(paramBoolean);
      return;
    }
    showMenu(paramBoolean);
  }

  public static abstract interface CanvasTransformer
  {
    public abstract void transformCanvas(Canvas paramCanvas, float paramFloat);
  }

  public static abstract interface OnCloseListener
  {
    public abstract void onClose();
  }

  public static abstract interface OnClosedListener
  {
    public abstract void onClosed();
  }

  public static abstract interface OnOpenListener
  {
    public abstract void onOpen();
  }

  public static abstract interface OnOpenedListener
  {
    public abstract void onOpened();
  }

  public static class SavedState extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator()
    {
      public SlidingMenu.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new SlidingMenu.SavedState(paramAnonymousParcel, null);
      }

      public SlidingMenu.SavedState[] newArray(int paramAnonymousInt)
      {
        return new SlidingMenu.SavedState[paramAnonymousInt];
      }
    };
    private final int mItem;

    private SavedState(Parcel paramParcel)
    {
      super();
      this.mItem = paramParcel.readInt();
    }

    public SavedState(Parcelable paramParcelable, int paramInt)
    {
      super();
      this.mItem = paramInt;
    }

    public int getItem()
    {
      return this.mItem;
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(this.mItem);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.slidingmenu.SlidingMenu
 * JD-Core Version:    0.6.2
 */