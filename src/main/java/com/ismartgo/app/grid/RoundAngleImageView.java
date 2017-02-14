package com.ismartgo.app.grid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;
import com.ismartgo.apppub.R.styleable;

public class RoundAngleImageView extends ImageView
{
  private static final Xfermode mXferMode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
  private static final Paint paint = new Paint();
  public boolean debug;
  private boolean leftDown;
  private boolean leftUp;
  private Paint paint2;
  private boolean rightDown;
  private boolean rightUp;
  private int roundHeight = 5;
  private int roundWidth = 5;

  static
  {
    paint.setAntiAlias(true);
    paint.setFilterBitmap(false);
    paint.setDither(true);
    paint.setXfermode(mXferMode);
    paint.setColor(-1);
  }

  public RoundAngleImageView(Context paramContext)
  {
    super(paramContext);
    init(paramContext, null);
  }

  public RoundAngleImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet);
  }

  public RoundAngleImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet);
  }

  private void drawLeftDown(Canvas paramCanvas)
  {
    Path localPath = new Path();
    localPath.moveTo(0.0F, getHeight() - this.roundHeight);
    localPath.lineTo(0.0F, getHeight());
    localPath.lineTo(this.roundWidth, getHeight());
    localPath.arcTo(new RectF(0.0F, getHeight() - this.roundHeight * 2, this.roundWidth * 2 + 0, getHeight()), 90.0F, 90.0F);
    localPath.close();
    paramCanvas.drawPath(localPath, paint);
  }

  private void drawLeftUp(Canvas paramCanvas)
  {
    Path localPath = new Path();
    localPath.moveTo(0.0F, this.roundHeight);
    localPath.lineTo(0.0F, 0.0F);
    localPath.lineTo(this.roundWidth, 0.0F);
    localPath.arcTo(new RectF(0.0F, 0.0F, this.roundWidth * 2, this.roundHeight * 2), -90.0F, -90.0F);
    localPath.close();
    paramCanvas.drawPath(localPath, paint);
  }

  private void drawRightDown(Canvas paramCanvas)
  {
    Path localPath = new Path();
    localPath.moveTo(getWidth() - this.roundWidth, getHeight());
    localPath.lineTo(getWidth(), getHeight());
    localPath.lineTo(getWidth(), getHeight() - this.roundHeight);
    localPath.arcTo(new RectF(getWidth() - this.roundWidth * 2, getHeight() - this.roundHeight * 2, getWidth(), getHeight()), 0.0F, 90.0F);
    localPath.close();
    paramCanvas.drawPath(localPath, paint);
  }

  private void drawRightUp(Canvas paramCanvas)
  {
    Path localPath = new Path();
    localPath.moveTo(getWidth(), this.roundHeight);
    localPath.lineTo(getWidth(), 0.0F);
    localPath.lineTo(getWidth() - this.roundWidth, 0.0F);
    localPath.arcTo(new RectF(getWidth() - this.roundWidth * 2, 0.0F, getWidth(), this.roundHeight * 2 + 0), -90.0F, 90.0F);
    localPath.close();
    paramCanvas.drawPath(localPath, paint);
  }

  @SuppressLint({"Recycle"})
  private void init(Context paramContext, AttributeSet paramAttributeSet)
  {
    if (paramAttributeSet != null)
    {
      paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.RoundAngleImageView);
      this.roundWidth = paramContext.getDimensionPixelSize(0, this.roundWidth);
      this.roundHeight = paramContext.getDimensionPixelSize(1, this.roundHeight);
      this.leftUp = paramContext.getBoolean(2, false);
      this.rightUp = paramContext.getBoolean(3, false);
      this.leftDown = paramContext.getBoolean(4, false);
    }
    for (this.rightDown = paramContext.getBoolean(5, false); ; this.rightDown = false)
    {
      Log.i("hahaha", "leftUp: " + this.leftUp + "  rightUp: " + this.rightUp);
      this.paint2 = new Paint();
      this.paint2.setXfermode(null);
      return;
      float f = paramContext.getResources().getDisplayMetrics().density;
      this.roundWidth = ((int)(this.roundWidth * f));
      this.roundHeight = ((int)(this.roundHeight * f));
      this.leftUp = false;
      this.rightUp = false;
      this.leftDown = false;
    }
  }

  @SuppressLint({"DrawAllocation"})
  public void onDraw(Canvas paramCanvas)
  {
    if (getDrawable() == null)
    {
      super.onDraw(paramCanvas);
      return;
    }
    int i = paramCanvas.saveLayer(new RectF(0.0F, 0.0F, getWidth(), getHeight()), null, 31);
    super.onDraw(paramCanvas);
    if (this.leftUp)
    {
      Log.i("hahaha", "drawLeftUp");
      drawLeftUp(paramCanvas);
    }
    if (this.rightUp)
    {
      Log.i("hahaha", "drawRightUp");
      drawRightUp(paramCanvas);
    }
    if (this.leftDown)
      drawLeftDown(paramCanvas);
    if (this.rightDown)
      drawRightDown(paramCanvas);
    paramCanvas.restoreToCount(i);
  }

  public void setLeftDown(boolean paramBoolean)
  {
    this.leftDown = paramBoolean;
  }

  public void setLeftUp(boolean paramBoolean)
  {
    this.leftUp = paramBoolean;
  }

  public void setRightDown(boolean paramBoolean)
  {
    this.rightDown = paramBoolean;
  }

  public void setRightUp(boolean paramBoolean)
  {
    this.rightUp = paramBoolean;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.RoundAngleImageView
 * JD-Core Version:    0.6.2
 */