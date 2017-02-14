package com.byl.datepicker.wheelview;

public class ItemsRange
{
  private int count;
  private int first;

  public ItemsRange()
  {
    this(0, 0);
  }

  public ItemsRange(int paramInt1, int paramInt2)
  {
    this.first = paramInt1;
    this.count = paramInt2;
  }

  public boolean contains(int paramInt)
  {
    return (paramInt >= getFirst()) && (paramInt <= getLast());
  }

  public int getCount()
  {
    return this.count;
  }

  public int getFirst()
  {
    return this.first;
  }

  public int getLast()
  {
    return getFirst() + getCount() - 1;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.byl.datepicker.wheelview.ItemsRange
 * JD-Core Version:    0.6.2
 */