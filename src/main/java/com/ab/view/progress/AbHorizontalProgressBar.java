package com.ab.view.progress;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.EmbossMaskFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.view.View;
import com.ab.view.listener.AbOnProgressListener;

@SuppressLint({"DrawAllocation"})
public class AbHorizontalProgressBar extends View
{
  float blur = 3.5F;
  float[] direction = { 1.0F, 1.0F, 1.0F };
  private EmbossMaskFilter emboss = null;
  private int[] fillColors = { -12717242, -16596970 };
  private Paint fillPaint = null;
  private int height;
  float light = 0.4F;
  private AbOnProgressListener mAbOnProgressListener = null;
  private BlurMaskFilter mBlur = null;
  private int max = 100;
  private int pathBorderColor = -2960956;
  private int pathColor = -987425;
  private Paint pathPaint = null;
  private int pathWidth = 35;
  private int progress = 0;
  private boolean reset = false;
  float specular = 6.0F;
  private int width;

  public AbHorizontalProgressBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.pathPaint.setAntiAlias(true);
    this.pathPaint.setFlags(1);
    this.pathPaint.setStyle(Paint.Style.FILL);
    this.pathPaint.setDither(true);
    this.fillPaint = new Paint();
    this.fillPaint.setAntiAlias(true);
    this.fillPaint.setFlags(1);
    this.fillPaint.setStyle(Paint.Style.FILL);
    this.fillPaint.setDither(true);
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
    this.pathPaint.setColor(this.pathColor);
    this.pathPaint.setStrokeWidth(this.pathWidth);
    this.pathPaint.setMaskFilter(this.emboss);
    paramCanvas.drawRect(0.0F, 0.0F, this.width, this.height, this.pathPaint);
    this.pathPaint.setColor(this.pathBorderColor);
    paramCanvas.drawRect(0.0F, 0.0F, this.width, this.height, this.pathPaint);
    LinearGradient localLinearGradient = new LinearGradient(0.0F, 0.0F, this.width, this.height, this.fillColors[0], this.fillColors[1], Shader.TileMode.CLAMP);
    this.fillPaint.setShader(localLinearGradient);
    this.fillPaint.setMaskFilter(this.mBlur);
    this.fillPaint.setStrokeCap(Paint.Cap.ROUND);
    this.fillPaint.setStrokeWidth(this.pathWidth);
    paramCanvas.drawRect(0.0F, 0.0F, this.progress / this.max * this.width, this.height, this.fillPaint);
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
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.progress.AbHorizontalProgressBar
 * JD-Core Version:    0.6.2
 */