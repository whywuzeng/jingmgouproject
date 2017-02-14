package com.nostra13.universalimageloader.core.imageaware;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.utils.L;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class ViewAware
  implements ImageAware
{
  public static final String WARN_CANT_SET_BITMAP = "Can't set a bitmap into view. You should call ImageLoader on UI thread for it.";
  public static final String WARN_CANT_SET_DRAWABLE = "Can't set a drawable into view. You should call ImageLoader on UI thread for it.";
  protected boolean checkActualViewSize;
  protected Reference<View> viewRef;

  public ViewAware(View paramView)
  {
    this(paramView, true);
  }

  public ViewAware(View paramView, boolean paramBoolean)
  {
    if (paramView == null)
      throw new IllegalArgumentException("view must not be null");
    this.viewRef = new WeakReference(paramView);
    this.checkActualViewSize = paramBoolean;
  }

  public int getHeight()
  {
    View localView = (View)this.viewRef.get();
    if (localView != null)
    {
      ViewGroup.LayoutParams localLayoutParams = localView.getLayoutParams();
      int j = 0;
      int i = j;
      if (this.checkActualViewSize)
      {
        i = j;
        if (localLayoutParams != null)
        {
          i = j;
          if (localLayoutParams.height != -2)
            i = localView.getHeight();
        }
      }
      j = i;
      if (i <= 0)
      {
        j = i;
        if (localLayoutParams != null)
          j = localLayoutParams.height;
      }
      return j;
    }
    return 0;
  }

  public int getId()
  {
    View localView = (View)this.viewRef.get();
    if (localView == null)
      return super.hashCode();
    return localView.hashCode();
  }

  public ViewScaleType getScaleType()
  {
    return ViewScaleType.CROP;
  }

  public int getWidth()
  {
    View localView = (View)this.viewRef.get();
    if (localView != null)
    {
      ViewGroup.LayoutParams localLayoutParams = localView.getLayoutParams();
      int j = 0;
      int i = j;
      if (this.checkActualViewSize)
      {
        i = j;
        if (localLayoutParams != null)
        {
          i = j;
          if (localLayoutParams.width != -2)
            i = localView.getWidth();
        }
      }
      j = i;
      if (i <= 0)
      {
        j = i;
        if (localLayoutParams != null)
          j = localLayoutParams.width;
      }
      return j;
    }
    return 0;
  }

  public View getWrappedView()
  {
    return (View)this.viewRef.get();
  }

  public boolean isCollected()
  {
    return this.viewRef.get() == null;
  }

  public boolean setImageBitmap(Bitmap paramBitmap)
  {
    boolean bool = false;
    if (Looper.myLooper() == Looper.getMainLooper())
    {
      View localView = (View)this.viewRef.get();
      if (localView != null)
      {
        setImageBitmapInto(paramBitmap, localView);
        bool = true;
      }
      return bool;
    }
    L.w("Can't set a bitmap into view. You should call ImageLoader on UI thread for it.", new Object[0]);
    return false;
  }

  protected abstract void setImageBitmapInto(Bitmap paramBitmap, View paramView);

  public boolean setImageDrawable(Drawable paramDrawable)
  {
    boolean bool = false;
    if (Looper.myLooper() == Looper.getMainLooper())
    {
      View localView = (View)this.viewRef.get();
      if (localView != null)
      {
        setImageDrawableInto(paramDrawable, localView);
        bool = true;
      }
      return bool;
    }
    L.w("Can't set a drawable into view. You should call ImageLoader on UI thread for it.", new Object[0]);
    return false;
  }

  protected abstract void setImageDrawableInto(Drawable paramDrawable, View paramView);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.core.imageaware.ViewAware
 * JD-Core Version:    0.6.2
 */