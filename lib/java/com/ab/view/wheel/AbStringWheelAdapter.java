package com.ab.view.wheel;

import com.ab.util.AbStrUtil;
import java.util.List;

public class AbStringWheelAdapter
  implements AbWheelAdapter
{
  public static final int DEFAULT_LENGTH = -1;
  private List<String> items;
  private int length = -1;

  public AbStringWheelAdapter(List<String> paramList)
  {
    this(paramList, -1);
  }

  public AbStringWheelAdapter(List<String> paramList, int paramInt)
  {
    this.items = paramList;
    this.length = paramInt;
  }

  public String getItem(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < this.items.size()))
      return (String)this.items.get(paramInt);
    return null;
  }

  public int getItemsCount()
  {
    return this.items.size();
  }

  public int getMaximumLength()
  {
    int k;
    if (this.length != -1)
    {
      k = this.length;
      return k;
    }
    int i = 0;
    int j = 0;
    while (true)
    {
      k = i;
      if (j >= this.items.size())
        break;
      int m = AbStrUtil.strLength((String)this.items.get(j));
      k = i;
      if (i < m)
        k = m;
      j += 1;
      i = k;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.wheel.AbStringWheelAdapter
 * JD-Core Version:    0.6.2
 */