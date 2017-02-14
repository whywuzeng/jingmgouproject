package com.linj.album.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class MatrixImageView extends ImageView
{
  public static final String TAG = "MatrixImageView";
  private GestureDetector mGestureDetector;
  private float mImageHeight;
  private float mImageWidth;
  private Matrix mMatrix = new Matrix();
  private float mScale;
  private OnMovingListener moveListener;
  private OnSingleTapListener singleTapListener;

  public MatrixImageView(Context paramContext)
  {
    super(paramContext, null);
    paramContext = new MatrixTouchListener();
    setOnTouchListener(paramContext);
    this.mGestureDetector = new GestureDetector(getContext(), new GestureListener(paramContext));
    setBackgroundColor(-16777216);
    setScaleType(ImageView.ScaleType.FIT_CENTER);
  }

  public MatrixImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    paramContext = new MatrixTouchListener();
    setOnTouchListener(paramContext);
    this.mGestureDetector = new GestureDetector(getContext(), new GestureListener(paramContext));
    setBackgroundColor(-16777216);
    setScaleType(ImageView.ScaleType.FIT_CENTER);
  }

  private void initData()
  {
    this.mMatrix.set(getImageMatrix());
    float[] arrayOfFloat = new float[9];
    this.mMatrix.getValues(arrayOfFloat);
    this.mImageWidth = (getWidth() / arrayOfFloat[0]);
    this.mImageHeight = ((getHeight() - arrayOfFloat[5] * 2.0F) / arrayOfFloat[4]);
    this.mScale = arrayOfFloat[0];
  }

  public void setImageBitmap(Bitmap paramBitmap)
  {
    super.setImageBitmap(paramBitmap);
    if (getWidth() == 0)
    {
      getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener()
      {
        public boolean onPreDraw()
        {
          MatrixImageView.this.initData();
          MatrixImageView.this.getViewTreeObserver().removeOnPreDrawListener(this);
          return true;
        }
      });
      return;
    }
    initData();
  }

  public void setOnMovingListener(OnMovingListener paramOnMovingListener)
  {
    this.moveListener = paramOnMovingListener;
  }

  public void setOnSingleTapListener(OnSingleTapListener paramOnSingleTapListener)
  {
    this.singleTapListener = paramOnSingleTapListener;
  }

  private class GestureListener extends GestureDetector.SimpleOnGestureListener
  {
    private final MatrixImageView.MatrixTouchListener listener;

    public GestureListener(MatrixImageView.MatrixTouchListener arg2)
    {
      Object localObject;
      this.listener = localObject;
    }

    public boolean onDoubleTap(MotionEvent paramMotionEvent)
    {
      this.listener.onDoubleClick();
      return true;
    }

    public boolean onDoubleTapEvent(MotionEvent paramMotionEvent)
    {
      return super.onDoubleTapEvent(paramMotionEvent);
    }

    public boolean onDown(MotionEvent paramMotionEvent)
    {
      return true;
    }

    public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
    {
      return super.onFling(paramMotionEvent1, paramMotionEvent2, paramFloat1, paramFloat2);
    }

    public void onLongPress(MotionEvent paramMotionEvent)
    {
      super.onLongPress(paramMotionEvent);
    }

    public boolean onScroll(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
    {
      return super.onScroll(paramMotionEvent1, paramMotionEvent2, paramFloat1, paramFloat2);
    }

    public void onShowPress(MotionEvent paramMotionEvent)
    {
      super.onShowPress(paramMotionEvent);
    }

    public boolean onSingleTapConfirmed(MotionEvent paramMotionEvent)
    {
      if (MatrixImageView.this.singleTapListener != null)
        MatrixImageView.this.singleTapListener.onSingleTap();
      return super.onSingleTapConfirmed(paramMotionEvent);
    }

    public boolean onSingleTapUp(MotionEvent paramMotionEvent)
    {
      return super.onSingleTapUp(paramMotionEvent);
    }
  }

  public class MatrixTouchListener
    implements View.OnTouchListener
  {
    private static final int MODE_DRAG = 1;
    private static final int MODE_UNABLE = 3;
    private static final int MODE_ZOOM = 2;
    private Matrix mCurrentMatrix = new Matrix();
    float mDobleClickScale = 2.0F;
    boolean mFirstMove = false;
    boolean mLeftDragable;
    float mMaxScale = 6.0F;
    private int mMode = 0;
    boolean mRightDragable;
    private float mStartDis;
    private PointF mStartPoint = new PointF();

    public MatrixTouchListener()
    {
    }

    private void checkDragable()
    {
      this.mLeftDragable = true;
      this.mRightDragable = true;
      this.mFirstMove = true;
      float[] arrayOfFloat = new float[9];
      MatrixImageView.this.getImageMatrix().getValues(arrayOfFloat);
      if (arrayOfFloat[2] >= 0.0F)
        this.mRightDragable = false;
      if (MatrixImageView.this.mImageWidth * arrayOfFloat[0] + arrayOfFloat[2] <= MatrixImageView.this.getWidth())
        this.mLeftDragable = false;
    }

    private float checkDxBound(float[] paramArrayOfFloat, float paramFloat1, float paramFloat2)
    {
      float f = MatrixImageView.this.getWidth();
      if ((!this.mLeftDragable) && (paramFloat1 < 0.0F))
        if ((Math.abs(paramFloat1) * 0.4F > Math.abs(paramFloat2)) && (this.mFirstMove))
          stopDrag();
      do
      {
        do
        {
          return 0.0F;
          if ((this.mRightDragable) || (paramFloat1 <= 0.0F))
            break;
        }
        while ((Math.abs(paramFloat1) * 0.4F <= Math.abs(paramFloat2)) || (!this.mFirstMove));
        stopDrag();
        return 0.0F;
        this.mLeftDragable = true;
        this.mRightDragable = true;
        if (this.mFirstMove)
          this.mFirstMove = false;
      }
      while (MatrixImageView.this.mImageWidth * paramArrayOfFloat[0] < f);
      if (paramArrayOfFloat[2] + paramFloat1 > 0.0F)
        paramFloat2 = -paramArrayOfFloat[2];
      while (true)
      {
        return paramFloat2;
        paramFloat2 = paramFloat1;
        if (paramArrayOfFloat[2] + paramFloat1 < -(MatrixImageView.this.mImageWidth * paramArrayOfFloat[0] - f))
          paramFloat2 = -(MatrixImageView.this.mImageWidth * paramArrayOfFloat[0] - f) - paramArrayOfFloat[2];
      }
    }

    private float checkDyBound(float[] paramArrayOfFloat, float paramFloat)
    {
      float f2 = MatrixImageView.this.getHeight();
      if (MatrixImageView.this.mImageHeight * paramArrayOfFloat[4] < f2)
        return 0.0F;
      float f1;
      if (paramArrayOfFloat[5] + paramFloat > 0.0F)
        f1 = -paramArrayOfFloat[5];
      while (true)
      {
        return f1;
        f1 = paramFloat;
        if (paramArrayOfFloat[5] + paramFloat < -(MatrixImageView.this.mImageHeight * paramArrayOfFloat[4] - f2))
          f1 = -(MatrixImageView.this.mImageHeight * paramArrayOfFloat[4] - f2) - paramArrayOfFloat[5];
      }
    }

    private float checkMaxScale(float paramFloat, float[] paramArrayOfFloat)
    {
      float f = paramFloat;
      if (paramArrayOfFloat[0] * paramFloat > this.mMaxScale)
        f = this.mMaxScale / paramArrayOfFloat[0];
      return f;
    }

    private boolean checkRest()
    {
      boolean bool = false;
      float[] arrayOfFloat = new float[9];
      MatrixImageView.this.getImageMatrix().getValues(arrayOfFloat);
      if (arrayOfFloat[0] < MatrixImageView.this.mScale)
        bool = true;
      return bool;
    }

    private float distance(MotionEvent paramMotionEvent)
    {
      float f1 = paramMotionEvent.getX(1) - paramMotionEvent.getX(0);
      float f2 = paramMotionEvent.getY(1) - paramMotionEvent.getY(0);
      return (float)Math.sqrt(f1 * f1 + f2 * f2);
    }

    private PointF getCenter(float paramFloat, float[] paramArrayOfFloat)
    {
      if ((paramArrayOfFloat[0] * paramFloat < MatrixImageView.this.mScale) || (paramFloat >= 1.0F))
        return new PointF(MatrixImageView.this.getWidth() / 2, MatrixImageView.this.getHeight() / 2);
      float f1 = MatrixImageView.this.getWidth() / 2;
      float f2 = MatrixImageView.this.getHeight() / 2;
      if ((MatrixImageView.this.getWidth() / 2 - paramArrayOfFloat[2]) * paramFloat < MatrixImageView.this.getWidth() / 2)
        f1 = 0.0F;
      if ((MatrixImageView.this.mImageWidth * paramArrayOfFloat[0] + paramArrayOfFloat[2]) * paramFloat < MatrixImageView.this.getWidth())
        f1 = MatrixImageView.this.getWidth();
      return new PointF(f1, f2);
    }

    private void isMatrixEnable()
    {
      if (MatrixImageView.this.getScaleType() != ImageView.ScaleType.CENTER)
      {
        MatrixImageView.this.setScaleType(ImageView.ScaleType.MATRIX);
        return;
      }
      this.mMode = 3;
    }

    private boolean isZoomChanged()
    {
      boolean bool = false;
      float[] arrayOfFloat = new float[9];
      MatrixImageView.this.getImageMatrix().getValues(arrayOfFloat);
      if (arrayOfFloat[0] != MatrixImageView.this.mScale)
        bool = true;
      return bool;
    }

    private void reSetMatrix()
    {
      if (checkRest())
      {
        this.mCurrentMatrix.set(MatrixImageView.this.mMatrix);
        MatrixImageView.this.setImageMatrix(this.mCurrentMatrix);
      }
      float[] arrayOfFloat;
      float f;
      do
      {
        do
        {
          return;
          arrayOfFloat = new float[9];
          MatrixImageView.this.getImageMatrix().getValues(arrayOfFloat);
          f = MatrixImageView.this.mImageHeight * arrayOfFloat[4];
        }
        while (f >= MatrixImageView.this.getHeight());
        f = (MatrixImageView.this.getHeight() - f) / 2.0F;
      }
      while (f == arrayOfFloat[5]);
      this.mCurrentMatrix.set(MatrixImageView.this.getImageMatrix());
      this.mCurrentMatrix.postTranslate(0.0F, f - arrayOfFloat[5]);
      MatrixImageView.this.setImageMatrix(this.mCurrentMatrix);
    }

    private void setZoomMatrix(MotionEvent paramMotionEvent)
    {
      if (paramMotionEvent.getPointerCount() < 2);
      do
      {
        return;
        f1 = distance(paramMotionEvent);
      }
      while (f1 <= 10.0F);
      float f2 = f1 / this.mStartDis;
      this.mStartDis = f1;
      this.mCurrentMatrix.set(MatrixImageView.this.getImageMatrix());
      paramMotionEvent = new float[9];
      this.mCurrentMatrix.getValues(paramMotionEvent);
      float f1 = checkMaxScale(f2, paramMotionEvent);
      paramMotionEvent = getCenter(f1, paramMotionEvent);
      this.mCurrentMatrix.postScale(f1, f1, paramMotionEvent.x, paramMotionEvent.y);
      MatrixImageView.this.setImageMatrix(this.mCurrentMatrix);
    }

    private void startDrag()
    {
      if (MatrixImageView.this.moveListener != null)
        MatrixImageView.this.moveListener.startDrag();
    }

    private void stopDrag()
    {
      if (MatrixImageView.this.moveListener != null)
        MatrixImageView.this.moveListener.stopDrag();
    }

    public void onDoubleClick()
    {
      if (isZoomChanged());
      for (float f = 1.0F; ; f = this.mDobleClickScale)
      {
        this.mCurrentMatrix.set(MatrixImageView.this.mMatrix);
        this.mCurrentMatrix.postScale(f, f, MatrixImageView.this.getWidth() / 2, MatrixImageView.this.getHeight() / 2);
        MatrixImageView.this.setImageMatrix(this.mCurrentMatrix);
        return;
      }
    }

    public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
    {
      boolean bool = true;
      switch (paramMotionEvent.getActionMasked())
      {
      case 4:
      case 6:
      default:
      case 0:
      case 1:
      case 3:
      case 2:
      case 5:
      }
      while (true)
      {
        bool = MatrixImageView.this.mGestureDetector.onTouchEvent(paramMotionEvent);
        do
        {
          return bool;
          this.mMode = 1;
          this.mStartPoint.set(paramMotionEvent.getX(), paramMotionEvent.getY());
          isMatrixEnable();
          startDrag();
          checkDragable();
          break;
          reSetMatrix();
          stopDrag();
          break;
          if (this.mMode == 2)
          {
            setZoomMatrix(paramMotionEvent);
            break;
          }
          if (this.mMode == 1)
          {
            setDragMatrix(paramMotionEvent);
            break;
          }
          stopDrag();
          break;
        }
        while (this.mMode == 3);
        this.mMode = 2;
        this.mStartDis = distance(paramMotionEvent);
      }
    }

    public void setDragMatrix(MotionEvent paramMotionEvent)
    {
      if (isZoomChanged())
      {
        float f1 = paramMotionEvent.getX() - this.mStartPoint.x;
        float f2 = paramMotionEvent.getY() - this.mStartPoint.y;
        if (Math.sqrt(f1 * f1 + f2 * f2) > 10.0D)
        {
          this.mStartPoint.set(paramMotionEvent.getX(), paramMotionEvent.getY());
          this.mCurrentMatrix.set(MatrixImageView.this.getImageMatrix());
          paramMotionEvent = new float[9];
          this.mCurrentMatrix.getValues(paramMotionEvent);
          f2 = checkDyBound(paramMotionEvent, f2);
          f1 = checkDxBound(paramMotionEvent, f1, f2);
          this.mCurrentMatrix.postTranslate(f1, f2);
          MatrixImageView.this.setImageMatrix(this.mCurrentMatrix);
        }
        return;
      }
      stopDrag();
    }
  }

  public static abstract interface OnMovingListener
  {
    public abstract void startDrag();

    public abstract void stopDrag();
  }

  public static abstract interface OnSingleTapListener
  {
    public abstract void onSingleTap();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.linj.album.view.MatrixImageView
 * JD-Core Version:    0.6.2
 */