package com.ismartgo.app.grid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.TextView;

public class DynamicHeightTextView extends TextView
{
  private double mHeightRatio;

  public DynamicHeightTextView(Context paramContext)
  {
    super(paramContext);
  }

  public DynamicHeightTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public double getHeightRatio()
  {
    return this.mHeightRatio;
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (this.mHeightRatio > 0.0D)
    {
      paramInt1 = View.MeasureSpec.getSize(paramInt1);
      setMeasuredDimension(paramInt1, (int)(paramInt1 * this.mHeightRatio));
      return;
    }
    super.onMeasure(paramInt1, paramInt2);
  }

  public void setHeightRatio(double paramDouble)
  {
    if (paramDouble != this.mHeightRatio)
    {
      this.mHeightRatio = paramDouble;
      requestLayout();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.DynamicHeightTextView
 * JD-Core Version:    0.6.2
 */