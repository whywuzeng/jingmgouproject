package com.ismartgo.app.ownself.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.ismartgo.apppub.R.styleable;

public class CircleImageView extends ImageView
{
  private int defaultColor = -1;
  private int defaultHeight = 0;
  private int defaultWidth = 0;
  private int mBorderInsideColor = 0;
  private int mBorderOutsideColor = 0;
  private int mBorderThickness = 0;
  private Context mContext;

  public CircleImageView(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
  }

  public CircleImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    setCustomAttributes(paramAttributeSet);
  }

  public CircleImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.mContext = paramContext;
    setCustomAttributes(paramAttributeSet);
  }

  private void drawCircleBorder(Canvas paramCanvas, int paramInt1, int paramInt2)
  {
    Paint localPaint = new Paint();
    localPaint.setAntiAlias(true);
    localPaint.setFilterBitmap(true);
    localPaint.setDither(true);
    localPaint.setColor(paramInt2);
    localPaint.setStyle(Paint.Style.STROKE);
    localPaint.setStrokeWidth(this.mBorderThickness);
    paramCanvas.drawCircle(this.defaultWidth / 2, this.defaultHeight / 2, paramInt1, localPaint);
  }

  private void setCustomAttributes(AttributeSet paramAttributeSet)
  {
    paramAttributeSet = this.mContext.obtainStyledAttributes(paramAttributeSet, R.styleable.RoundImageView);
    this.mBorderThickness = paramAttributeSet.getDimensionPixelSize(0, 0);
    this.mBorderOutsideColor = paramAttributeSet.getColor(2, this.defaultColor);
    this.mBorderInsideColor = paramAttributeSet.getColor(1, this.defaultColor);
  }

  public Bitmap getCroppedRoundBitmap(Bitmap paramBitmap, int paramInt)
  {
    paramInt *= 2;
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    if (j > i)
    {
      paramBitmap = Bitmap.createBitmap(paramBitmap, 0, (j - i) / 2, i, i);
      if ((paramBitmap.getWidth() == paramInt) && (paramBitmap.getHeight() == paramInt))
        break label228;
      paramBitmap = Bitmap.createScaledBitmap(paramBitmap, paramInt, paramInt, true);
    }
    label228: 
    while (true)
    {
      Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), Bitmap.Config.ARGB_8888);
      Canvas localCanvas = new Canvas(localBitmap);
      Paint localPaint = new Paint();
      Rect localRect = new Rect(0, 0, paramBitmap.getWidth(), paramBitmap.getHeight());
      localPaint.setAntiAlias(true);
      localPaint.setFilterBitmap(true);
      localPaint.setDither(true);
      localCanvas.drawARGB(0, 0, 0, 0);
      localCanvas.drawCircle(paramBitmap.getWidth() / 2, paramBitmap.getHeight() / 2, paramBitmap.getWidth() / 2, localPaint);
      localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
      localCanvas.drawBitmap(paramBitmap, localRect, localRect, localPaint);
      return localBitmap;
      if (j < i)
      {
        paramBitmap = Bitmap.createBitmap(paramBitmap, (i - j) / 2, 0, j, j);
        break;
      }
      break;
    }
  }

  protected void onDraw(Canvas paramCanvas)
  {
    Object localObject = getDrawable();
    if (localObject == null);
    do
    {
      do
        return;
      while ((getWidth() == 0) || (getHeight() == 0));
      measure(0, 0);
    }
    while (localObject.getClass() == NinePatchDrawable.class);
    localObject = ((BitmapDrawable)localObject).getBitmap().copy(Bitmap.Config.ARGB_8888, true);
    if (this.defaultWidth == 0)
      this.defaultWidth = getWidth();
    if (this.defaultHeight == 0)
      this.defaultHeight = getHeight();
    if ((this.mBorderInsideColor != this.defaultColor) && (this.mBorderOutsideColor != this.defaultColor))
    {
      if (this.defaultWidth < this.defaultHeight);
      for (i = this.defaultWidth; ; i = this.defaultHeight)
      {
        i = i / 2 - this.mBorderThickness * 2;
        drawCircleBorder(paramCanvas, this.mBorderThickness / 2 + i, this.mBorderInsideColor);
        drawCircleBorder(paramCanvas, this.mBorderThickness + i + this.mBorderThickness / 2, this.mBorderOutsideColor);
        paramCanvas.drawBitmap(getCroppedRoundBitmap((Bitmap)localObject, i), this.defaultWidth / 2 - i, this.defaultHeight / 2 - i, null);
        return;
      }
    }
    if ((this.mBorderInsideColor != this.defaultColor) && (this.mBorderOutsideColor == this.defaultColor))
    {
      if (this.defaultWidth < this.defaultHeight);
      for (i = this.defaultWidth; ; i = this.defaultHeight)
      {
        i = i / 2 - this.mBorderThickness;
        drawCircleBorder(paramCanvas, this.mBorderThickness / 2 + i, this.mBorderInsideColor);
        break;
      }
    }
    if ((this.mBorderInsideColor == this.defaultColor) && (this.mBorderOutsideColor != this.defaultColor))
    {
      if (this.defaultWidth < this.defaultHeight);
      for (i = this.defaultWidth; ; i = this.defaultHeight)
      {
        i = i / 2 - this.mBorderThickness;
        drawCircleBorder(paramCanvas, this.mBorderThickness / 2 + i, this.mBorderOutsideColor);
        break;
      }
    }
    if (this.defaultWidth < this.defaultHeight);
    for (int i = this.defaultWidth; ; i = this.defaultHeight)
    {
      i /= 2;
      break;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.ownself.view.CircleImageView
 * JD-Core Version:    0.6.2
 */