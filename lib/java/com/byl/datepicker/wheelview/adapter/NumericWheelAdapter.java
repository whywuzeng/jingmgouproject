package com.byl.datepicker.wheelview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NumericWheelAdapter extends AbstractWheelTextAdapter
{
  public static final int DEFAULT_MAX_VALUE = 9;
  private static final int DEFAULT_MIN_VALUE = 0;
  private String format;
  private String label;
  private int maxValue;
  private int minValue;

  public NumericWheelAdapter(Context paramContext)
  {
    this(paramContext, 0, 9);
  }

  public NumericWheelAdapter(Context paramContext, int paramInt1, int paramInt2)
  {
    this(paramContext, paramInt1, paramInt2, null);
  }

  public NumericWheelAdapter(Context paramContext, int paramInt1, int paramInt2, String paramString)
  {
    super(paramContext);
    this.minValue = paramInt1;
    this.maxValue = paramInt2;
    this.format = paramString;
  }

  public View getItem(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if ((paramInt >= 0) && (paramInt < getItemsCount()))
    {
      View localView = paramView;
      if (paramView == null)
        localView = getView(this.itemResourceId, paramViewGroup);
      TextView localTextView = getTextView(localView, this.itemTextResourceId);
      if (localTextView != null)
      {
        paramViewGroup = getItemText(paramInt);
        paramView = paramViewGroup;
        if (paramViewGroup == null)
          paramView = "";
        localTextView.setText(paramView + this.label);
        if (this.itemResourceId == -1)
          configureTextView(localTextView);
      }
      return localView;
    }
    return null;
  }

  public CharSequence getItemText(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < getItemsCount()))
    {
      paramInt = this.minValue + paramInt;
      if (this.format != null)
        return String.format(this.format, new Object[] { Integer.valueOf(paramInt) });
      return Integer.toString(paramInt);
    }
    return null;
  }

  public int getItemsCount()
  {
    return this.maxValue - this.minValue + 1;
  }

  public void setLabel(String paramString)
  {
    this.label = paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.byl.datepicker.wheelview.adapter.NumericWheelAdapter
 * JD-Core Version:    0.6.2
 */