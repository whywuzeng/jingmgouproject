package com.ab.view.cropimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class CropImageView extends CropViewBase
{
  private CropImage mCropImage;
  public ArrayList<HighlightView> mHighlightViews = new ArrayList();
  float mLastX;
  float mLastY;
  int mMotionEdge;
  HighlightView mMotionHighlightView = null;

  public CropImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void centerBasedOnHighlightView(HighlightView paramHighlightView)
  {
    Object localObject = paramHighlightView.mDrawRect;
    float f1 = ((Rect)localObject).width();
    float f2 = ((Rect)localObject).height();
    float f3 = getWidth();
    float f4 = getHeight();
    f1 = Math.max(1.0F, Math.min(f3 / f1 * 0.6F, f4 / f2 * 0.6F) * getScale());
    if (Math.abs(f1 - getScale()) / f1 > 0.1D)
    {
      localObject = new float[2];
      localObject[0] = paramHighlightView.mCropRect.centerX();
      localObject[1] = paramHighlightView.mCropRect.centerY();
      getImageMatrix().mapPoints((float[])localObject);
      zoomTo(f1, localObject[0], localObject[1], 300.0F);
    }
    ensureVisible(paramHighlightView);
  }

  private void ensureVisible(HighlightView paramHighlightView)
  {
    paramHighlightView = paramHighlightView.mDrawRect;
    int i = Math.max(0, getLeft() - paramHighlightView.left);
    int m = Math.min(0, getRight() - paramHighlightView.right);
    int j = Math.max(0, getTop() - paramHighlightView.top);
    int k = Math.min(0, getBottom() - paramHighlightView.bottom);
    if (i != 0)
      if (j == 0)
        break label94;
    while (true)
    {
      if ((i != 0) || (j != 0))
        panBy(i, j);
      return;
      i = m;
      break;
      label94: j = k;
    }
  }

  private void recomputeFocus(MotionEvent paramMotionEvent)
  {
    int i = 0;
    if (i >= this.mHighlightViews.size())
      i = 0;
    while (true)
    {
      if (i >= this.mHighlightViews.size());
      while (true)
      {
        invalidate();
        return;
        HighlightView localHighlightView = (HighlightView)this.mHighlightViews.get(i);
        localHighlightView.setFocus(false);
        localHighlightView.invalidate();
        i += 1;
        break;
        localHighlightView = (HighlightView)this.mHighlightViews.get(i);
        if (localHighlightView.getHit(paramMotionEvent.getX(), paramMotionEvent.getY()) == 1)
          break label106;
        if (!localHighlightView.hasFocus())
        {
          localHighlightView.setFocus(true);
          localHighlightView.invalidate();
        }
      }
      label106: i += 1;
    }
  }

  public void add(HighlightView paramHighlightView)
  {
    this.mHighlightViews.clear();
    this.mHighlightViews.add(paramHighlightView);
    invalidate();
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    int i = 0;
    while (true)
    {
      if (i >= this.mHighlightViews.size())
        return;
      ((HighlightView)this.mHighlightViews.get(i)).draw(paramCanvas);
      i += 1;
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    Iterator localIterator;
    if (this.mBitmapDisplayed.getBitmap() != null)
      localIterator = this.mHighlightViews.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      HighlightView localHighlightView = (HighlightView)localIterator.next();
      localHighlightView.mMatrix.set(getImageMatrix());
      localHighlightView.invalidate();
      if (localHighlightView.mIsFocused)
        centerBasedOnHighlightView(localHighlightView);
    }
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    Object localObject = this.mCropImage;
    if (((CropImage)localObject).mSaving)
      return false;
    switch (paramMotionEvent.getAction())
    {
    default:
      switch (paramMotionEvent.getAction())
      {
      default:
      case 1:
      case 2:
      }
      break;
    case 0:
    case 1:
    case 2:
    }
    while (true)
    {
      return true;
      if (((CropImage)localObject).mWaitingToPick)
      {
        recomputeFocus(paramMotionEvent);
        break;
      }
      int i = 0;
      int j;
      HighlightView localHighlightView;
      while (i < this.mHighlightViews.size())
      {
        localObject = (HighlightView)this.mHighlightViews.get(i);
        j = ((HighlightView)localObject).getHit(paramMotionEvent.getX(), paramMotionEvent.getY());
        if (j != 1)
        {
          this.mMotionEdge = j;
          this.mMotionHighlightView = ((HighlightView)localObject);
          this.mLastX = paramMotionEvent.getX();
          this.mLastY = paramMotionEvent.getY();
          localHighlightView = this.mMotionHighlightView;
          if (j == 32);
          for (localObject = HighlightView.ModifyMode.Move; ; localObject = HighlightView.ModifyMode.Grow)
          {
            localHighlightView.setMode((HighlightView.ModifyMode)localObject);
            break;
          }
        }
        i += 1;
      }
      if (((CropImage)localObject).mWaitingToPick)
      {
        i = 0;
        label218: if (i < this.mHighlightViews.size());
      }
      while (true)
      {
        this.mMotionHighlightView = null;
        break;
        localHighlightView = (HighlightView)this.mHighlightViews.get(i);
        if (localHighlightView.hasFocus())
        {
          ((CropImage)localObject).mCrop = localHighlightView;
          j = 0;
          if (j >= this.mHighlightViews.size())
          {
            centerBasedOnHighlightView(localHighlightView);
            this.mCropImage.mWaitingToPick = false;
            return true;
          }
          if (j == i);
          while (true)
          {
            j += 1;
            break;
            ((HighlightView)this.mHighlightViews.get(j)).setHidden(true);
          }
        }
        i += 1;
        break label218;
        if (this.mMotionHighlightView != null)
        {
          centerBasedOnHighlightView(this.mMotionHighlightView);
          this.mMotionHighlightView.setMode(HighlightView.ModifyMode.None);
        }
      }
      if (((CropImage)localObject).mWaitingToPick)
      {
        recomputeFocus(paramMotionEvent);
        break;
      }
      if (this.mMotionHighlightView == null)
        break;
      this.mMotionHighlightView.handleMotion(this.mMotionEdge, paramMotionEvent.getX() - this.mLastX, paramMotionEvent.getY() - this.mLastY);
      this.mLastX = paramMotionEvent.getX();
      this.mLastY = paramMotionEvent.getY();
      ensureVisible(this.mMotionHighlightView);
      break;
      center(true, true);
      continue;
      center(true, true);
    }
  }

  protected void postTranslate(float paramFloat1, float paramFloat2)
  {
    super.postTranslate(paramFloat1, paramFloat2);
    int i = 0;
    while (true)
    {
      if (i >= this.mHighlightViews.size())
        return;
      HighlightView localHighlightView = (HighlightView)this.mHighlightViews.get(i);
      localHighlightView.mMatrix.postTranslate(paramFloat1, paramFloat2);
      localHighlightView.invalidate();
      i += 1;
    }
  }

  public void resetView(Bitmap paramBitmap)
  {
    setImageBitmap(paramBitmap);
    setImageBitmapResetBase(paramBitmap, true);
    setImageMatrix(getImageViewMatrix());
    int k = this.mBitmapDisplayed.getWidth();
    int j = this.mBitmapDisplayed.getHeight();
    paramBitmap = new Rect(0, 0, k, j);
    int i = Math.min(k, j) * 4 / 5;
    k = (k - i) / 2;
    j = (j - i) / 2;
    RectF localRectF = new RectF(k, j, k + i, j + i);
    HighlightView localHighlightView = new HighlightView(this);
    localHighlightView.setup(getImageViewMatrix(), paramBitmap, localRectF, false, true);
    localHighlightView.setFocus(true);
    add(localHighlightView);
    centerBasedOnHighlightView(localHighlightView);
    localHighlightView.setMode(HighlightView.ModifyMode.None);
    center(true, true);
    invalidate();
  }

  public void setCropImage(CropImage paramCropImage)
  {
    this.mCropImage = paramCropImage;
  }

  protected void zoomIn()
  {
    super.zoomIn();
    Iterator localIterator = this.mHighlightViews.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      HighlightView localHighlightView = (HighlightView)localIterator.next();
      localHighlightView.mMatrix.set(getImageMatrix());
      localHighlightView.invalidate();
    }
  }

  protected void zoomOut()
  {
    super.zoomOut();
    Iterator localIterator = this.mHighlightViews.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      HighlightView localHighlightView = (HighlightView)localIterator.next();
      localHighlightView.mMatrix.set(getImageMatrix());
      localHighlightView.invalidate();
    }
  }

  protected void zoomTo(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    super.zoomTo(paramFloat1, paramFloat2, paramFloat3);
    Iterator localIterator = this.mHighlightViews.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      HighlightView localHighlightView = (HighlightView)localIterator.next();
      localHighlightView.mMatrix.set(getImageMatrix());
      localHighlightView.invalidate();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.cropimage.CropImageView
 * JD-Core Version:    0.6.2
 */