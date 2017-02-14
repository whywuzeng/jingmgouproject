package kankan.wheel.widget.adapters;

import android.content.Context;

public class NumericWheelAdapter extends AbstractWheelTextAdapter
{
  public static final int DEFAULT_MAX_VALUE = 9;
  private static final int DEFAULT_MIN_VALUE = 0;
  private String format;
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
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     kankan.wheel.widget.adapters.NumericWheelAdapter
 * JD-Core Version:    0.6.2
 */