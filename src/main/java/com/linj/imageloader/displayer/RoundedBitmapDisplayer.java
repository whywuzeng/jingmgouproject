package com.linj.imageloader.displayer;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class RoundedBitmapDisplayer
  implements BitmapDisplayer
{
  protected final int cornerRadius;
  protected final int margin;

  public RoundedBitmapDisplayer(int paramInt)
  {
    this(paramInt, 0);
  }

  public RoundedBitmapDisplayer(int paramInt1, int paramInt2)
  {
    this.cornerRadius = paramInt1;
    this.margin = paramInt2;
  }

  public void display(int paramInt, ImageView paramImageView)
  {
    paramImageView.setImageResource(paramInt);
  }

  public void display(Bitmap paramBitmap, ImageView paramImageView)
  {
    paramImageView.setImageDrawable(new RoundedDrawable(paramBitmap, this.cornerRadius, this.margin));
  }

  public static class RoundedDrawable extends Drawable
  {
    protected final BitmapShader bitmapShader;
    protected final float cornerRadius;
    protected final RectF mBitmapRect;
    protected final RectF mRect = new RectF();
    protected final int margin;
    protected final Paint paint;

    public RoundedDrawable(Bitmap paramBitmap, int paramInt1, int paramInt2)
    {
      this.cornerRadius = paramInt1;
      this.margin = paramInt2;
      this.bitmapShader = new BitmapShader(paramBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
      this.mBitmapRect = new RectF(paramInt2, paramInt2, paramBitmap.getWidth() - paramInt2, paramBitmap.getHeight() - paramInt2);
      this.paint = new Paint();
      this.paint.setAntiAlias(true);
      this.paint.setShader(this.bitmapShader);
    }

    public void draw(Canvas paramCanvas)
    {
      paramCanvas.drawRoundRect(this.mRect, this.cornerRadius, this.cornerRadius, this.paint);
    }

    public int getOpacity()
    {
      return -3;
    }

    protected void onBoundsChange(Rect paramRect)
    {
      super.onBoundsChange(paramRect);
      this.mRect.set(this.margin, this.margin, paramRect.width() - this.margin, paramRect.height() - this.margin);
      paramRect = new Matrix();
      paramRect.setRectToRect(this.mBitmapRect, this.mRect, Matrix.ScaleToFit.FILL);
      this.bitmapShader.setLocalMatrix(paramRect);
    }

    public void setAlpha(int paramInt)
    {
      if (paramInt != this.paint.getAlpha())
      {
        this.paint.setAlpha(paramInt);
        invalidateSelf();
      }
    }

    public void setAntiAlias(boolean paramBoolean)
    {
      this.paint.setAntiAlias(paramBoolean);
      invalidateSelf();
    }

    public void setColorFilter(int paramInt, PorterDuff.Mode paramMode)
    {
      this.paint.setColorFilter(new PorterDuffColorFilter(paramInt, paramMode));
      invalidateSelf();
    }

    public void setColorFilter(ColorFilter paramColorFilter)
    {
      this.paint.setColorFilter(paramColorFilter);
      invalidateSelf();
    }

    public void setDither(boolean paramBoolean)
    {
      this.paint.setDither(paramBoolean);
      invalidateSelf();
    }

    public void setFilterBitmap(boolean paramBoolean)
    {
      this.paint.setFilterBitmap(paramBoolean);
      invalidateSelf();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.linj.imageloader.displayer.RoundedBitmapDisplayer
 * JD-Core Version:    0.6.2
 */