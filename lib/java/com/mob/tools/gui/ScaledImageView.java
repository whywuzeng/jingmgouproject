package com.mob.tools.gui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.Ln;

public class ScaledImageView extends ImageView
  implements View.OnTouchListener
{
  private static final int DRAG_1 = 1;
  private static final int DRAG_2 = 2;
  private static final int NONE = 0;
  private static final int ZOOM = 3;
  private Bitmap bitmap;
  private float distSquare;
  private float[] downPoint;
  private int dragScrollMinDistSquare;
  private OnMatrixChangedListener listener;
  private Matrix matrix;
  private int mode;
  private Matrix savedMatrix;

  public ScaledImageView(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }

  public ScaledImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }

  public ScaledImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }

  private void init(Context paramContext)
  {
    this.dragScrollMinDistSquare = ViewConfiguration.get(paramContext).getScaledTouchSlop();
    this.dragScrollMinDistSquare *= this.dragScrollMinDistSquare;
    setOnTouchListener(this);
  }

  public Bitmap getCropedBitmap(Rect paramRect)
  {
    try
    {
      Bitmap localBitmap = BitmapHelper.captureView(this, getWidth(), getHeight());
      if (localBitmap == null)
      {
        Ln.w("ivPhoto.getDrawingCache() returns null", new Object[0]);
        return null;
      }
      paramRect = Bitmap.createBitmap(localBitmap, paramRect.left, paramRect.top, paramRect.width(), paramRect.height());
      localBitmap.recycle();
      return paramRect;
    }
    catch (Throwable paramRect)
    {
      Ln.w(paramRect);
    }
    return null;
  }

  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    try
    {
      switch (paramMotionEvent.getAction())
      {
      case 0:
        this.matrix = new Matrix();
        this.matrix.set(getImageMatrix());
        this.savedMatrix = new Matrix();
        this.savedMatrix.set(this.matrix);
        this.downPoint = new float[] { paramMotionEvent.getX(0), paramMotionEvent.getY(0) };
      case 1:
        for (this.mode = 1; ; this.mode = 0)
        {
          setImageMatrix(this.matrix);
          break;
          if (this.listener != null)
            this.listener.onMactrixChage(this.matrix);
          f1 = paramMotionEvent.getX(0) - this.downPoint[0];
          f2 = paramMotionEvent.getY(0) - this.downPoint[1];
          if ((this.mode == 1) && (f1 * f1 + f2 * f2 <= this.dragScrollMinDistSquare))
            performClick();
        }
      case 5:
      case 6:
      case 261:
      case 262:
      case 2:
      }
    }
    catch (Throwable paramView)
    {
      while (true)
      {
        Ln.w(paramView);
        break;
        paramView = new float[2];
        paramView[0] = paramMotionEvent.getX(0);
        paramView[1] = paramMotionEvent.getY(0);
        float[] arrayOfFloat = new float[2];
        arrayOfFloat[0] = paramMotionEvent.getX(1);
        arrayOfFloat[1] = paramMotionEvent.getY(1);
        float f1 = paramView[0] - arrayOfFloat[0];
        float f2 = paramView[1] - arrayOfFloat[1];
        this.distSquare = (f2 * f2 + f1 * f1);
        this.mode = 3;
        continue;
        this.downPoint = new float[] { paramMotionEvent.getX(1), paramMotionEvent.getY(1) };
        this.savedMatrix.set(this.matrix);
        this.mode = 2;
        continue;
        paramView = new float[2];
        paramView[0] = paramMotionEvent.getX(0);
        paramView[1] = paramMotionEvent.getY(0);
        arrayOfFloat = new float[2];
        arrayOfFloat[0] = paramMotionEvent.getX(1);
        arrayOfFloat[1] = paramMotionEvent.getY(1);
        f1 = paramView[0] - arrayOfFloat[0];
        f2 = paramView[1] - arrayOfFloat[1];
        this.distSquare = (f2 * f2 + f1 * f1);
        this.mode = 3;
        continue;
        this.downPoint = new float[] { paramMotionEvent.getX(0), paramMotionEvent.getY(0) };
        this.savedMatrix.set(this.matrix);
        this.mode = 1;
        continue;
        if (this.mode == 1)
        {
          paramView = new float[2];
          paramView[0] = paramMotionEvent.getX(0);
          paramView[1] = paramMotionEvent.getY(0);
          this.matrix.set(this.savedMatrix);
          this.matrix.postTranslate(paramView[0] - this.downPoint[0], paramView[1] - this.downPoint[1]);
        }
        else if (this.mode == 2)
        {
          paramView = new float[2];
          paramView[0] = paramMotionEvent.getX(1);
          paramView[1] = paramMotionEvent.getY(1);
          this.matrix.set(this.savedMatrix);
          this.matrix.postTranslate(paramView[0] - this.downPoint[0], paramView[1] - this.downPoint[1]);
        }
        else if (this.mode == 3)
        {
          paramView = new float[2];
          paramView[0] = paramMotionEvent.getX(0);
          paramView[1] = paramMotionEvent.getY(0);
          arrayOfFloat = new float[2];
          arrayOfFloat[0] = paramMotionEvent.getX(1);
          arrayOfFloat[1] = paramMotionEvent.getY(1);
          f1 = paramView[0] - arrayOfFloat[0];
          f2 = paramView[1] - arrayOfFloat[1];
          this.matrix.set(this.savedMatrix);
          f1 = FloatMath.sqrt((f1 * f1 + f2 * f2) / this.distSquare);
          paramMotionEvent = new float[2];
          paramMotionEvent[0] = ((paramView[0] + arrayOfFloat[0]) / 2.0F);
          paramMotionEvent[1] = ((paramView[1] + arrayOfFloat[1]) / 2.0F);
          this.matrix.postScale(f1, f1, paramMotionEvent[0], paramMotionEvent[1]);
        }
      }
      return false;
    }
    return true;
  }

  public void rotateLeft()
  {
    try
    {
      this.matrix = new Matrix();
      float[] arrayOfFloat1 = new float[9];
      float[] tmp17_16 = arrayOfFloat1;
      tmp17_16[0] = 0.0F;
      float[] tmp21_17 = tmp17_16;
      tmp21_17[1] = 1.0F;
      float[] tmp25_21 = tmp21_17;
      tmp25_21[2] = 0.0F;
      float[] tmp29_25 = tmp25_21;
      tmp29_25[3] = -1.0F;
      float[] tmp34_29 = tmp29_25;
      tmp34_29[4] = 0.0F;
      float[] tmp38_34 = tmp34_29;
      tmp38_34[5] = 0.0F;
      float[] tmp42_38 = tmp38_34;
      tmp42_38[6] = 0.0F;
      float[] tmp47_42 = tmp42_38;
      tmp47_42[7] = 0.0F;
      float[] tmp52_47 = tmp47_42;
      tmp52_47[8] = 1.0F;
      tmp52_47;
      this.matrix.setValues(arrayOfFloat1);
      Object localObject = Bitmap.createBitmap(this.bitmap, 0, 0, this.bitmap.getWidth(), this.bitmap.getHeight(), this.matrix, true);
      if ((localObject != null) && (!((Bitmap)localObject).isRecycled()))
      {
        this.bitmap.recycle();
        this.bitmap = ((Bitmap)localObject);
      }
      setImageBitmap(this.bitmap);
      this.matrix = new Matrix();
      this.matrix.set(getImageMatrix());
      this.matrix.getValues(arrayOfFloat1);
      localObject = new int[2];
      localObject[0] = getWidth();
      localObject[1] = getHeight();
      float[] arrayOfFloat2 = new float[2];
      arrayOfFloat1[0] *= this.bitmap.getWidth();
      arrayOfFloat2[1] = (arrayOfFloat1[4] * this.bitmap.getHeight());
      float[] arrayOfFloat3 = new float[2];
      arrayOfFloat3[0] = ((localObject[0] - arrayOfFloat2[0]) / 2.0F);
      arrayOfFloat3[1] = ((localObject[1] - arrayOfFloat2[1]) / 2.0F);
      arrayOfFloat1[2] = arrayOfFloat3[0];
      arrayOfFloat1[5] = arrayOfFloat3[1];
      this.matrix.setValues(arrayOfFloat1);
      if (this.listener != null)
        this.listener.onMactrixChage(this.matrix);
      setImageMatrix(this.matrix);
      return;
    }
    catch (Throwable localThrowable)
    {
      Ln.w(localThrowable);
    }
  }

  public void rotateRight()
  {
    try
    {
      this.matrix = new Matrix();
      float[] arrayOfFloat1 = new float[9];
      float[] tmp17_16 = arrayOfFloat1;
      tmp17_16[0] = 0.0F;
      float[] tmp21_17 = tmp17_16;
      tmp21_17[1] = -1.0F;
      float[] tmp26_21 = tmp21_17;
      tmp26_21[2] = 0.0F;
      float[] tmp30_26 = tmp26_21;
      tmp30_26[3] = 1.0F;
      float[] tmp34_30 = tmp30_26;
      tmp34_30[4] = 0.0F;
      float[] tmp38_34 = tmp34_30;
      tmp38_34[5] = 0.0F;
      float[] tmp42_38 = tmp38_34;
      tmp42_38[6] = 0.0F;
      float[] tmp47_42 = tmp42_38;
      tmp47_42[7] = 0.0F;
      float[] tmp52_47 = tmp47_42;
      tmp52_47[8] = 1.0F;
      tmp52_47;
      this.matrix.setValues(arrayOfFloat1);
      Object localObject = Bitmap.createBitmap(this.bitmap, 0, 0, this.bitmap.getWidth(), this.bitmap.getHeight(), this.matrix, true);
      if ((localObject != null) && (!((Bitmap)localObject).isRecycled()))
      {
        this.bitmap.recycle();
        this.bitmap = ((Bitmap)localObject);
      }
      setImageBitmap(this.bitmap);
      this.matrix = new Matrix();
      this.matrix.set(getImageMatrix());
      this.matrix.getValues(arrayOfFloat1);
      localObject = new int[2];
      localObject[0] = getWidth();
      localObject[1] = getHeight();
      float[] arrayOfFloat2 = new float[2];
      arrayOfFloat1[0] *= this.bitmap.getWidth();
      arrayOfFloat2[1] = (arrayOfFloat1[4] * this.bitmap.getHeight());
      float[] arrayOfFloat3 = new float[2];
      arrayOfFloat3[0] = ((localObject[0] - arrayOfFloat2[0]) / 2.0F);
      arrayOfFloat3[1] = ((localObject[1] - arrayOfFloat2[1]) / 2.0F);
      arrayOfFloat1[2] = arrayOfFloat3[0];
      arrayOfFloat1[5] = arrayOfFloat3[1];
      this.matrix.setValues(arrayOfFloat1);
      if (this.listener != null)
        this.listener.onMactrixChage(this.matrix);
      setImageMatrix(this.matrix);
      return;
    }
    catch (Throwable localThrowable)
    {
      Ln.w(localThrowable);
    }
  }

  public void setBitmap(Bitmap paramBitmap)
  {
    this.bitmap = paramBitmap;
    setImageBitmap(paramBitmap);
    Object localObject = new int[2];
    localObject[0] = getWidth();
    localObject[1] = getHeight();
    paramBitmap = new int[2];
    paramBitmap[0] = this.bitmap.getWidth();
    paramBitmap[1] = this.bitmap.getHeight();
    int[] arrayOfInt1 = BitmapHelper.fixRect(paramBitmap, (int[])localObject);
    int[] arrayOfInt2 = new int[2];
    arrayOfInt2[0] = ((localObject[0] - arrayOfInt1[0]) / 2);
    arrayOfInt2[1] = ((localObject[1] - arrayOfInt1[1]) / 2);
    localObject = new float[2];
    localObject[0] = (arrayOfInt1[0] / paramBitmap[0]);
    localObject[1] = (arrayOfInt1[1] / paramBitmap[1]);
    this.matrix = new Matrix();
    this.matrix.set(getImageMatrix());
    this.matrix.postScale(localObject[0], localObject[1]);
    this.matrix.postTranslate(arrayOfInt2[0], arrayOfInt2[1]);
    if (this.listener != null)
      this.listener.onMactrixChage(this.matrix);
    setImageMatrix(this.matrix);
  }

  public void setOnMatrixChangedListener(OnMatrixChangedListener paramOnMatrixChangedListener)
  {
    this.listener = paramOnMatrixChangedListener;
    if (this.matrix != null)
    {
      if (this.listener != null)
        this.listener.onMactrixChage(this.matrix);
      setImageMatrix(this.matrix);
    }
  }

  public void zoomIn()
  {
    this.matrix = new Matrix();
    this.matrix.set(getImageMatrix());
    this.matrix.postScale(1.072F, 1.072F);
    if (this.listener != null)
      this.listener.onMactrixChage(this.matrix);
    setImageMatrix(this.matrix);
  }

  public void zoomOut()
  {
    this.matrix = new Matrix();
    this.matrix.set(getImageMatrix());
    this.matrix.postScale(0.933F, 0.933F);
    if (this.listener != null)
      this.listener.onMactrixChage(this.matrix);
    setImageMatrix(this.matrix);
  }

  public static abstract interface OnMatrixChangedListener
  {
    public abstract void onMactrixChage(Matrix paramMatrix);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.gui.ScaledImageView
 * JD-Core Version:    0.6.2
 */