package com.ab.view.cropimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public abstract class CropViewBase extends ImageView
{
  static final float SCALE_RATE = 1.25F;
  public static final int STATE_DOODLE = 1;
  public static final int STATE_HIGHLIGHT = 0;
  public static final int STATE_NONE = 2;
  private static final String TAG = "ImageViewTouchBase";
  protected Matrix mBaseMatrix = new Matrix();
  public final RotateBitmap mBitmapDisplayed = new RotateBitmap(null);
  private final Matrix mDisplayMatrix = new Matrix();
  protected Handler mHandler = new Handler();
  protected int mLastXTouchPos;
  protected int mLastYTouchPos;
  private final float[] mMatrixValues = new float[9];
  float mMaxZoom;
  private Runnable mOnLayoutRunnable = null;
  private Recycler mRecycler;
  protected int mState = 0;
  protected Matrix mSuppMatrix = new Matrix();
  int mThisHeight = -1;
  int mThisWidth = -1;

  public CropViewBase(Context paramContext)
  {
    super(paramContext);
    init();
  }

  public CropViewBase(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }

  private void getProperBaseMatrix(RotateBitmap paramRotateBitmap, Matrix paramMatrix)
  {
    float f1 = getWidth();
    float f2 = getHeight();
    float f3 = paramRotateBitmap.getWidth();
    float f4 = paramRotateBitmap.getHeight();
    paramMatrix.reset();
    float f5 = Math.min(Math.min(f1 / f3, 2.0F), Math.min(f2 / f4, 2.0F));
    paramMatrix.postConcat(paramRotateBitmap.getRotateMatrix());
    paramMatrix.postScale(f5, f5);
    paramMatrix.postTranslate((f1 - f3 * f5) / 2.0F, (f2 - f4 * f5) / 2.0F);
  }

  private void init()
  {
    setScaleType(ImageView.ScaleType.MATRIX);
  }

  private void setImageBitmap(Bitmap paramBitmap, int paramInt)
  {
    super.setImageBitmap(paramBitmap);
    Object localObject = getDrawable();
    if (localObject != null)
      ((Drawable)localObject).setDither(true);
    localObject = this.mBitmapDisplayed.getBitmap();
    this.mBitmapDisplayed.setBitmap(paramBitmap);
    this.mBitmapDisplayed.setRotation(paramInt);
    if ((localObject != null) && (localObject != paramBitmap) && (this.mRecycler != null))
      this.mRecycler.recycle((Bitmap)localObject);
  }

  public void center(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (this.mBitmapDisplayed.getBitmap() == null)
      return;
    Matrix localMatrix = getImageViewMatrix();
    RectF localRectF = new RectF(0.0F, 0.0F, this.mBitmapDisplayed.getBitmap().getWidth(), this.mBitmapDisplayed.getBitmap().getHeight());
    localMatrix.mapRect(localRectF);
    float f5 = localRectF.height();
    float f4 = localRectF.width();
    float f3 = 0.0F;
    float f2 = 0.0F;
    float f1 = f2;
    int i;
    if (paramBoolean2)
    {
      i = getHeight();
      if (f5 < i)
        f1 = (i - f5) / 2.0F - localRectF.top;
    }
    else
    {
      f2 = f3;
      if (paramBoolean1)
      {
        i = getWidth();
        if (f4 >= i)
          break label220;
        f2 = (i - f4) / 2.0F - localRectF.left;
      }
    }
    while (true)
    {
      postTranslate(f2, f1);
      setImageMatrix(getImageViewMatrix());
      return;
      if (localRectF.top > 0.0F)
      {
        f1 = -localRectF.top;
        break;
      }
      f1 = f2;
      if (localRectF.bottom >= i)
        break;
      f1 = getHeight() - localRectF.bottom;
      break;
      label220: if (localRectF.left > 0.0F)
      {
        f2 = -localRectF.left;
      }
      else
      {
        f2 = f3;
        if (localRectF.right < i)
          f2 = i - localRectF.right;
      }
    }
  }

  public void clear()
  {
    setImageBitmapResetBase(null, true);
  }

  protected Matrix getImageViewMatrix()
  {
    this.mDisplayMatrix.set(this.mBaseMatrix);
    this.mDisplayMatrix.postConcat(this.mSuppMatrix);
    return this.mDisplayMatrix;
  }

  public float getScale()
  {
    return getScale(this.mSuppMatrix);
  }

  protected float getScale(Matrix paramMatrix)
  {
    return getValue(paramMatrix, 0);
  }

  protected float getValue(Matrix paramMatrix, int paramInt)
  {
    paramMatrix.getValues(this.mMatrixValues);
    return this.mMatrixValues[paramInt];
  }

  protected float maxZoom()
  {
    if (this.mBitmapDisplayed.getBitmap() == null)
      return 1.0F;
    float f2 = Math.max(this.mBitmapDisplayed.getWidth() / this.mThisWidth, this.mBitmapDisplayed.getHeight() / this.mThisHeight) * 4.0F;
    float f1 = f2;
    if (f2 < 1.0F)
      f1 = 1.0F;
    return f1;
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (getScale() > 1.0F))
    {
      zoomTo(1.0F);
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    this.mThisWidth = (paramInt3 - paramInt1);
    this.mThisHeight = (paramInt4 - paramInt2);
    Runnable localRunnable = this.mOnLayoutRunnable;
    if (localRunnable != null)
    {
      this.mOnLayoutRunnable = null;
      localRunnable.run();
    }
    if (this.mBitmapDisplayed.getBitmap() != null)
    {
      getProperBaseMatrix(this.mBitmapDisplayed, this.mBaseMatrix);
      setImageMatrix(getImageViewMatrix());
    }
  }

  protected void panBy(float paramFloat1, float paramFloat2)
  {
    postTranslate(paramFloat1, paramFloat2);
    setImageMatrix(getImageViewMatrix());
  }

  protected void postTranslate(float paramFloat1, float paramFloat2)
  {
    this.mSuppMatrix.postTranslate(paramFloat1, paramFloat2);
  }

  public void setImageBitmap(Bitmap paramBitmap)
  {
    setImageBitmap(paramBitmap, 0);
  }

  public void setImageBitmapResetBase(Bitmap paramBitmap, boolean paramBoolean)
  {
    setImageRotateBitmapResetBase(new RotateBitmap(paramBitmap), paramBoolean);
  }

  public void setImageRotateBitmapResetBase(final RotateBitmap paramRotateBitmap, final boolean paramBoolean)
  {
    if (getWidth() <= 0)
    {
      this.mOnLayoutRunnable = new Runnable()
      {
        public void run()
        {
          CropViewBase.this.setImageRotateBitmapResetBase(paramRotateBitmap, paramBoolean);
        }
      };
      return;
    }
    if (paramRotateBitmap.getBitmap() != null)
    {
      getProperBaseMatrix(paramRotateBitmap, this.mBaseMatrix);
      setImageBitmap(paramRotateBitmap.getBitmap(), paramRotateBitmap.getRotation());
    }
    while (true)
    {
      if (paramBoolean)
        this.mSuppMatrix.reset();
      setImageMatrix(getImageViewMatrix());
      this.mMaxZoom = maxZoom();
      return;
      this.mBaseMatrix.reset();
      setImageBitmap(null);
    }
  }

  public void setRecycler(Recycler paramRecycler)
  {
    this.mRecycler = paramRecycler;
  }

  protected void zoomIn()
  {
    zoomIn(1.25F);
  }

  protected void zoomIn(float paramFloat)
  {
    if (getScale() >= this.mMaxZoom);
    while (this.mBitmapDisplayed.getBitmap() == null)
      return;
    float f1 = getWidth() / 2.0F;
    float f2 = getHeight() / 2.0F;
    this.mSuppMatrix.postScale(paramFloat, paramFloat, f1, f2);
    setImageMatrix(getImageViewMatrix());
  }

  protected void zoomOut()
  {
    zoomOut(1.25F);
  }

  protected void zoomOut(float paramFloat)
  {
    if (this.mBitmapDisplayed.getBitmap() == null)
      return;
    float f1 = getWidth() / 2.0F;
    float f2 = getHeight() / 2.0F;
    Matrix localMatrix = new Matrix(this.mSuppMatrix);
    localMatrix.postScale(1.0F / paramFloat, 1.0F / paramFloat, f1, f2);
    if (getScale(localMatrix) < 1.0F)
      this.mSuppMatrix.setScale(1.0F, 1.0F, f1, f2);
    while (true)
    {
      setImageMatrix(getImageViewMatrix());
      center(true, true);
      return;
      this.mSuppMatrix.postScale(1.0F / paramFloat, 1.0F / paramFloat, f1, f2);
    }
  }

  protected void zoomTo(float paramFloat)
  {
    zoomTo(paramFloat, getWidth() / 2.0F, getHeight() / 2.0F);
  }

  protected void zoomTo(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    float f = paramFloat1;
    if (paramFloat1 > this.mMaxZoom)
      f = this.mMaxZoom;
    paramFloat1 = f / getScale();
    this.mSuppMatrix.postScale(paramFloat1, paramFloat1, paramFloat2, paramFloat3);
    setImageMatrix(getImageViewMatrix());
    center(true, true);
  }

  protected void zoomTo(final float paramFloat1, final float paramFloat2, final float paramFloat3, final float paramFloat4)
  {
    paramFloat1 = (paramFloat1 - getScale()) / paramFloat4;
    float f = getScale();
    final long l = System.currentTimeMillis();
    this.mHandler.post(new Runnable()
    {
      public void run()
      {
        long l = System.currentTimeMillis();
        float f1 = Math.min(paramFloat4, (float)(l - l));
        float f2 = paramFloat1;
        float f3 = paramFloat2;
        CropViewBase.this.zoomTo(f2 + f3 * f1, paramFloat3, this.val$centerY);
        if (f1 < paramFloat4)
          CropViewBase.this.mHandler.post(this);
      }
    });
  }

  public static abstract interface Recycler
  {
    public abstract void recycle(Bitmap paramBitmap);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.cropimage.CropViewBase
 * JD-Core Version:    0.6.2
 */