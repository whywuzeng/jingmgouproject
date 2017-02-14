package com.ismartgo.app.grid.wave;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.view.View;

public class DynamicWave extends View
{
  private static final int OFFSET_Y = 0;
  private static final float STRETCH_FACTOR_A = 50.0F;
  private static final int TRANSLATE_X_SPEED_ONE = 10;
  private static final int WAVE_PAINT_COLOR = Color.rgb(242, 87, 45);
  int count;
  int height = UiUtils.dipToPx(getContext(), 50);
  private float mCycleFactorW;
  private DrawFilter mDrawFilter;
  private float[] mResetOneYPositions;
  private float[] mResetTwoYPositions;
  private int mTotalHeight;
  private int mTotalWidth;
  private Paint mWavePaint;
  private int mXOffsetSpeedOne;
  private int mXOffsetSpeedTwo;
  private int mXOneOffset;
  private int mXTwoOffset;
  private float[] mYPositions;

  public DynamicWave(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mXOffsetSpeedOne = UiUtils.dipToPx(paramContext, 10);
    this.mWavePaint = new Paint();
    this.mWavePaint.setAntiAlias(true);
    this.mWavePaint.setStyle(Paint.Style.FILL);
    this.mWavePaint.setColor(WAVE_PAINT_COLOR);
    this.mDrawFilter = new PaintFlagsDrawFilter(0, 3);
  }

  private void resetPositonY()
  {
    int i = this.mYPositions.length - this.mXOneOffset;
    System.arraycopy(this.mYPositions, this.mXOneOffset, this.mResetOneYPositions, 0, i);
    System.arraycopy(this.mYPositions, 0, this.mResetOneYPositions, i, this.mXOneOffset);
    i = this.mYPositions.length - this.mXTwoOffset;
    System.arraycopy(this.mYPositions, this.mXTwoOffset, this.mResetTwoYPositions, 0, i);
    System.arraycopy(this.mYPositions, 0, this.mResetTwoYPositions, i, this.mXTwoOffset);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    paramCanvas.setDrawFilter(this.mDrawFilter);
    resetPositonY();
    int i = 0;
    while (true)
    {
      if (i >= this.mTotalWidth)
      {
        this.mXOneOffset += this.mXOffsetSpeedOne;
        this.mXTwoOffset += this.mXOffsetSpeedTwo;
        if (this.mXOneOffset >= this.mTotalWidth)
          this.mXOneOffset = 0;
        if (this.mXTwoOffset > this.mTotalWidth)
          this.mXTwoOffset = 0;
        if ((this.mXOneOffset != 0) || (this.mXTwoOffset != 0))
          break;
        return;
      }
      paramCanvas.drawLine(i, this.mTotalHeight - this.mResetOneYPositions[i] - this.height, i, this.mTotalHeight, this.mWavePaint);
      i += 1;
    }
    postInvalidate();
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    this.mTotalWidth = paramInt1;
    this.mTotalHeight = paramInt2;
    this.mYPositions = new float[this.mTotalWidth];
    this.mResetOneYPositions = new float[this.mTotalWidth];
    this.mResetTwoYPositions = new float[this.mTotalWidth];
    this.mCycleFactorW = ((float)(6.283185307179586D / this.mTotalWidth));
    paramInt1 = 0;
    while (true)
    {
      if (paramInt1 >= this.mTotalWidth)
        return;
      this.mYPositions[paramInt1] = ((float)(50.0D * Math.sin(this.mCycleFactorW * paramInt1) + 0.0D));
      paramInt1 += 1;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.wave.DynamicWave
 * JD-Core Version:    0.6.2
 */