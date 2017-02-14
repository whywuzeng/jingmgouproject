package com.ab.view.wheel;

public class AbObjectWheelAdapter<T>
  implements AbWheelAdapter
{
  public static final int DEFAULT_LENGTH = -1;
  private T[] items;
  private int length;

  public AbObjectWheelAdapter(T[] paramArrayOfT)
  {
    this(paramArrayOfT, -1);
  }

  public AbObjectWheelAdapter(T[] paramArrayOfT, int paramInt)
  {
    this.items = paramArrayOfT;
    this.length = paramInt;
  }

  public String getItem(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < this.items.length))
      return this.items[paramInt].toString();
    return null;
  }

  public int getItemsCount()
  {
    return this.items.length;
  }

  public int getMaximumLength()
  {
    return this.length;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.wheel.AbObjectWheelAdapter
 * JD-Core Version:    0.6.2
 */