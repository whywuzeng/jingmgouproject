package com.ismartgo.beacon.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import com.ismartgo.beacon.util.ScreenUtil;

public class DeleteView extends View
{
  private Paint backPaint = new Paint();
  private Context context;
  private Paint deletePaint = new Paint();
  private int lineHeight;
  private int lineMargin;
  private OnDelClickListener onDelClickListener;
  private int viewHeight;
  private int viewWidth;

  public DeleteView(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
    initView();
  }

  public DeleteView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView();
  }

  public DeleteView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initView();
  }

  private void initView()
  {
    this.viewHeight = ScreenUtil.dip2px(this.context, 30.0F);
    this.viewHeight = ScreenUtil.dip2px(this.context, 30.0F);
    this.lineMargin = ScreenUtil.dip2px(this.context, 4.0F);
    this.lineHeight = ScreenUtil.dip2px(this.context, 2.0F);
    this.backPaint.setAntiAlias(true);
    this.backPaint.setStyle(Paint.Style.STROKE);
    this.backPaint.setStrokeWidth(ScreenUtil.dip2px(this.context, 2.0F));
    this.backPaint.setColor(Color.parseColor("#ffffff"));
    this.deletePaint.setAntiAlias(true);
    this.deletePaint.setColor(Color.parseColor("#ffffff"));
    this.deletePaint.setStrokeWidth(this.lineHeight);
  }

  private int measureSpec(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getSize(paramInt1);
    switch (View.MeasureSpec.getMode(paramInt1))
    {
    default:
      if (paramInt2 == 0)
        return this.viewWidth;
      break;
    case 1073741824:
      return i;
    }
    return this.viewHeight;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    paramCanvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - this.lineHeight, this.backPaint);
    paramCanvas.drawLine((float)(getWidth() / 2 - Math.sqrt(2.0D) * getWidth() / 4.0D + this.lineMargin), (float)(getWidth() / 2 - Math.sqrt(2.0D) * getWidth() / 4.0D + this.lineMargin), (float)(getWidth() / 2 + Math.sqrt(2.0D) * getWidth() / 4.0D - this.lineMargin), (float)(getWidth() / 2 + Math.sqrt(2.0D) * getWidth() / 4.0D - this.lineMargin), this.deletePaint);
    paramCanvas.drawLine((float)(getWidth() / 2 - Math.sqrt(2.0D) * getWidth() / 4.0D + this.lineMargin), (float)(getWidth() / 2 + Math.sqrt(2.0D) * getWidth() / 4.0D - this.lineMargin), (float)(getWidth() / 2 + Math.sqrt(2.0D) * getWidth() / 4.0D - this.lineMargin), (float)(getWidth() / 2 - Math.sqrt(2.0D) * getWidth() / 4.0D + this.lineMargin), this.deletePaint);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    setMeasuredDimension(measureSpec(paramInt1, 0), measureSpec(paramInt2, 1));
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    case 0:
    case 2:
    default:
    case 1:
    }
    while (true)
    {
      return true;
      this.onDelClickListener.onDeleteListener(this);
    }
  }

  public void setOnDelClickListener(OnDelClickListener paramOnDelClickListener)
  {
    this.onDelClickListener = paramOnDelClickListener;
  }

  public static abstract interface OnDelClickListener
  {
    public abstract void onDeleteListener(View paramView);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.beacon.view.DeleteView
 * JD-Core Version:    0.6.2
 */