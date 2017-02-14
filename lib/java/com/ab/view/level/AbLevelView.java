package com.ab.view.level;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class AbLevelView extends View
{
  private int height;
  private AbLevelChart mAbLevelChart;
  private Context mContext;
  private Paint mPaint;
  private int screenHeight = 0;
  private int screenWidth = 0;
  private int width;

  public AbLevelView(Context paramContext)
  {
    super(paramContext);
  }

  public AbLevelView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public AbLevelView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public AbLevelView(Context paramContext, AbLevelAbstractChart paramAbLevelAbstractChart)
  {
    super(paramContext);
    this.mContext = paramContext;
    this.mAbLevelChart = ((AbLevelChart)paramAbLevelAbstractChart);
    this.mPaint = new Paint();
    paramAbLevelAbstractChart = new DisplayMetrics();
    ((Activity)paramContext).getWindowManager().getDefaultDisplay().getMetrics(paramAbLevelAbstractChart);
    this.screenWidth = paramAbLevelAbstractChart.widthPixels;
    this.screenHeight = paramAbLevelAbstractChart.heightPixels;
  }

  public void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    this.width = getMeasuredWidth();
    this.height = getMeasuredHeight();
    this.mAbLevelChart.draw(paramCanvas, 0, 0, this.width, this.height, this.screenWidth, this.screenHeight, this.mPaint);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.level.AbLevelView
 * JD-Core Version:    0.6.2
 */