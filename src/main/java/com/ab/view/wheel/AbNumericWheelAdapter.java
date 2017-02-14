package com.ab.view.wheel;

public class AbNumericWheelAdapter
  implements AbWheelAdapter
{
  public static final int DEFAULT_MAX_VALUE = 9;
  private static final int DEFAULT_MIN_VALUE = 0;
  private String format;
  private int maxValue;
  private int minValue;

  public AbNumericWheelAdapter()
  {
    this(0, 9);
  }

  public AbNumericWheelAdapter(int paramInt1, int paramInt2)
  {
    this(paramInt1, paramInt2, null);
  }

  public AbNumericWheelAdapter(int paramInt1, int paramInt2, String paramString)
  {
    this.minValue = paramInt1;
    this.maxValue = paramInt2;
    this.format = paramString;
  }

  public String getItem(int paramInt)
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

  public int getMaximumLength()
  {
    int j = Integer.toString(Math.max(Math.abs(this.maxValue), Math.abs(this.minValue))).length();
    int i = j;
    if (this.minValue < 0)
      i = j + 1;
    return i;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.wheel.AbNumericWheelAdapter
 * JD-Core Version:    0.6.2
 */