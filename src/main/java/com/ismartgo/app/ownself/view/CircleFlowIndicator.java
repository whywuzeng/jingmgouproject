package com.ismartgo.app.ownself.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import com.ismartgo.apppub.R.styleable;

@SuppressLint({"Recycle", "WrongCall"})
public class CircleFlowIndicator extends View
  implements FlowIndicator, Animation.AnimationListener
{
  private static final int STYLE_FILL = 1;
  private static final int STYLE_STROKE = 0;
  private float activeRadius = 0.5F;
  private Animation animation;
  public Animation.AnimationListener animationListener = this;
  private float circleSeparation = 2.0F * this.radius + this.radius;
  private int count = 1;
  private int currentScroll = 0;
  private int fadeOutTime = 0;
  private int flowWidth = 0;
  private int h = 0;
  private boolean mCentered = false;
  private final Paint mPaintActive = new Paint(1);
  private final Paint mPaintInactive = new Paint(1);
  private float radius = 4.0F;
  private FadeTimer timer;
  private ViewFlow viewFlow;
  private int w = 0;

  public CircleFlowIndicator(Context paramContext)
  {
    super(paramContext);
    initColors(-1, -1, 1, 0);
  }

  public CircleFlowIndicator(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.CircleFlowIndicator);
    int i = paramContext.getInt(6, 1);
    int j = paramContext.getColor(0, -1);
    int k = paramContext.getInt(5, 0);
    int m = paramContext.getColor(1, 1157627903);
    this.radius = paramContext.getDimension(2, 4.0F);
    this.circleSeparation = paramContext.getDimension(7, 2.0F * this.radius + this.radius);
    this.activeRadius = paramContext.getDimension(8, 0.5F);
    this.fadeOutTime = paramContext.getInt(4, 0);
    this.mCentered = paramContext.getBoolean(3, false);
    initColors(j, m, i, k);
  }

  private void initColors(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    switch (paramInt4)
    {
    default:
      this.mPaintInactive.setStyle(Paint.Style.STROKE);
      this.mPaintInactive.setColor(paramInt2);
      switch (paramInt3)
      {
      default:
        this.mPaintActive.setStyle(Paint.Style.FILL);
      case 0:
      }
      break;
    case 1:
    }
    while (true)
    {
      this.mPaintActive.setColor(paramInt1);
      return;
      this.mPaintInactive.setStyle(Paint.Style.FILL);
      break;
      this.mPaintActive.setStyle(Paint.Style.STROKE);
    }
  }

  private int measureHeight(int paramInt)
  {
    int k = View.MeasureSpec.getMode(paramInt);
    int i = View.MeasureSpec.getSize(paramInt);
    if (k == 1073741824)
      paramInt = i;
    int j;
    do
    {
      return paramInt;
      j = (int)(2.0F * this.radius + getPaddingTop() + getPaddingBottom() + 1.0F);
      paramInt = j;
    }
    while (k != -2147483648);
    return Math.min(j, i);
  }

  private int measureWidth(int paramInt)
  {
    int k = View.MeasureSpec.getMode(paramInt);
    int i = View.MeasureSpec.getSize(paramInt);
    if (k == 1073741824)
      paramInt = i;
    int j;
    do
    {
      return paramInt;
      if (this.viewFlow != null)
        this.count = this.viewFlow.getViewsCount();
      float f1 = this.circleSeparation;
      float f2 = this.radius;
      j = (int)(getPaddingLeft() + getPaddingRight() + this.count * 2 * this.radius + (this.count - 1) * (f1 - 2.0F * f2) + 1.0F);
      paramInt = j;
    }
    while (k != -2147483648);
    return Math.min(j, i);
  }

  private void resetTimer()
  {
    if (this.fadeOutTime > 0)
    {
      if ((this.timer == null) || (!this.timer._run))
      {
        this.timer = new FadeTimer(null);
        this.timer.execute(new Void[0]);
      }
    }
    else
      return;
    this.timer.resetTimer();
  }

  public void onAnimationEnd(Animation paramAnimation)
  {
    setVisibility(8);
  }

  public void onAnimationRepeat(Animation paramAnimation)
  {
  }

  public void onAnimationStart(Animation paramAnimation)
  {
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (this.viewFlow != null)
      this.count = this.viewFlow.getViewsCount();
    int j = getPaddingLeft();
    float f1 = j;
    float f2 = this.radius;
    float f3 = this.count;
    float f4 = this.circleSeparation;
    f3 = (getWidth() - (f1 + f2 + f3 * f4)) / 2.0F;
    int i = 0;
    while (true)
    {
      if (i >= this.count)
      {
        f1 = 0.0F;
        if (this.flowWidth != 0)
          f1 = this.currentScroll * this.circleSeparation / this.flowWidth;
        f2 = j + this.radius + f1 + f3;
        f1 = f2;
        if (f2 > j + this.radius + (this.count - 1) * this.circleSeparation + f3)
          f1 = j + this.radius + f3;
        paramCanvas.drawCircle(f1, getPaddingTop() + this.radius, this.radius + this.activeRadius, this.mPaintActive);
        return;
      }
      paramCanvas.drawCircle(j + this.radius + i * this.circleSeparation + f3, getPaddingTop() + this.radius, this.radius, this.mPaintInactive);
      i += 1;
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.w = paramInt1;
    this.h = paramInt2;
    setMeasuredDimension(measureWidth(paramInt1), measureHeight(paramInt2));
    invalidate();
  }

  public void onScrolled(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    setVisibility(0);
    resetTimer();
    this.flowWidth = this.viewFlow.getWidth();
    if (this.viewFlow.getViewsCount() * this.flowWidth != 0);
    for (this.currentScroll = (paramInt1 % (this.viewFlow.getViewsCount() * this.flowWidth)); ; this.currentScroll = paramInt1)
    {
      invalidate();
      return;
    }
  }

  public void onSwitched(View paramView, int paramInt)
  {
  }

  public void setFillColor(int paramInt)
  {
    this.mPaintActive.setColor(paramInt);
    invalidate();
  }

  public void setStrokeColor(int paramInt)
  {
    this.mPaintInactive.setColor(paramInt);
    invalidate();
  }

  public void setViewFlow(ViewFlow paramViewFlow)
  {
    resetTimer();
    this.viewFlow = paramViewFlow;
    this.flowWidth = this.viewFlow.getWidth();
    if ((this.w != 0) && (this.h != 0))
      onMeasure(this.w, this.h);
  }

  private class FadeTimer extends AsyncTask<Void, Void, Void>
  {
    private boolean _run = true;
    private int timer = 0;

    private FadeTimer()
    {
    }

    protected Void doInBackground(Void[] paramArrayOfVoid)
    {
      while (true)
      {
        if (!this._run)
          return null;
        try
        {
          Thread.sleep(1L);
          this.timer += 1;
          if (this.timer == CircleFlowIndicator.this.fadeOutTime)
            this._run = false;
        }
        catch (InterruptedException paramArrayOfVoid)
        {
          paramArrayOfVoid.printStackTrace();
        }
      }
    }

    protected void onPostExecute(Void paramVoid)
    {
      CircleFlowIndicator.this.animation = AnimationUtils.loadAnimation(CircleFlowIndicator.this.getContext(), 17432577);
      CircleFlowIndicator.this.animation.setAnimationListener(CircleFlowIndicator.this.animationListener);
      CircleFlowIndicator.this.startAnimation(CircleFlowIndicator.this.animation);
    }

    public void resetTimer()
    {
      this.timer = 0;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.ownself.view.CircleFlowIndicator
 * JD-Core Version:    0.6.2
 */