package com.ab.view.progress;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.ab.view.listener.AbOnProgressListener;

public class AbCircleProgressBar extends View
{
  private int[] arcColors = { -16596970, -12717242, -12521003, -16596970 };
  float blur = 3.5F;
  float[] direction = { 1.0F, 1.0F, 1.0F };
  private EmbossMaskFilter emboss = null;
  private Paint fillArcPaint = null;
  private int height;
  float light = 0.4F;
  private AbOnProgressListener mAbOnProgressListener = null;
  private BlurMaskFilter mBlur = null;
  private int max = 100;
  private RectF oval;
  private int pathBorderColor = -2960956;
  private int pathColor = -987425;
  private Paint pathPaint = null;
  private int pathWidth = 35;
  private int progress = 0;
  private int radius = 120;
  private boolean reset = false;
  private int[] shadowsColors = { -15658735, 11184810, 11184810 };
  float specular = 6.0F;
  private int width;

  public AbCircleProgressBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.pathPaint.setAntiAlias(true);
    this.pathPaint.setFlags(1);
    this.pathPaint.setStyle(Paint.Style.STROKE);
    this.pathPaint.setDither(true);
    this.pathPaint.setStrokeJoin(Paint.Join.ROUND);
    this.fillArcPaint = new Paint();
    this.fillArcPaint.setAntiAlias(true);
    this.fillArcPaint.setFlags(1);
    this.fillArcPaint.setStyle(Paint.Style.STROKE);
    this.fillArcPaint.setDither(true);
    this.fillArcPaint.setStrokeJoin(Paint.Join.ROUND);
    this.oval = new RectF();
    this.emboss = new EmbossMaskFilter(this.direction, this.light, this.specular, this.blur);
    this.mBlur = new BlurMaskFilter(20.0F, BlurMaskFilter.Blur.NORMAL);
  }

  public AbOnProgressListener getAbOnProgressListener()
  {
    return this.mAbOnProgressListener;
  }

  public int getMax()
  {
    return this.max;
  }

  public int getProgress()
  {
    return this.progress;
  }

  public int getRadius()
  {
    return this.radius;
  }

  @SuppressLint({"DrawAllocation"})
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (this.reset)
    {
      paramCanvas.drawColor(0);
      this.reset = false;
    }
    this.width = getMeasuredWidth();
    this.height = getMeasuredHeight();
    this.radius = (getMeasuredWidth() / 2 - this.pathWidth);
    this.pathPaint.setColor(this.pathColor);
    this.pathPaint.setStrokeWidth(this.pathWidth);
    this.pathPaint.setMaskFilter(this.emboss);
    paramCanvas.drawCircle(this.width / 2, this.height / 2, this.radius, this.pathPaint);
    this.pathPaint.setStrokeWidth(0.5F);
    this.pathPaint.setColor(this.pathBorderColor);
    paramCanvas.drawCircle(this.width / 2, this.height / 2, this.radius + this.pathWidth / 2 + 0.5F, this.pathPaint);
    paramCanvas.drawCircle(this.width / 2, this.height / 2, this.radius - this.pathWidth / 2 - 0.5F, this.pathPaint);
    SweepGradient localSweepGradient = new SweepGradient(this.width / 2, this.height / 2, this.arcColors, null);
    this.fillArcPaint.setShader(localSweepGradient);
    this.fillArcPaint.setMaskFilter(this.mBlur);
    this.fillArcPaint.setStrokeCap(Paint.Cap.ROUND);
    this.fillArcPaint.setStrokeWidth(this.pathWidth);
    this.oval.set(this.width / 2 - this.radius, this.height / 2 - this.radius, this.width / 2 + this.radius, this.height / 2 + this.radius);
    paramCanvas.drawArc(this.oval, -90.0F, 360.0F * (this.progress / this.max), false, this.fillArcPaint);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), paramInt2);
  }

  public void reset()
  {
    this.reset = true;
    this.progress = 0;
    invalidate();
  }

  public void setAbOnProgressListener(AbOnProgressListener paramAbOnProgressListener)
  {
    this.mAbOnProgressListener = paramAbOnProgressListener;
  }

  public void setMax(int paramInt)
  {
    this.max = paramInt;
  }

  public void setProgress(int paramInt)
  {
    this.progress = paramInt;
    invalidate();
    if (this.mAbOnProgressListener != null)
    {
      if (this.max <= this.progress)
        this.mAbOnProgressListener.onComplete(paramInt);
    }
    else
      return;
    this.mAbOnProgressListener.onProgress(paramInt);
  }

  public void setRadius(int paramInt)
  {
    this.radius = paramInt;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.progress.AbCircleProgressBar
 * JD-Core Version:    0.6.2
 */