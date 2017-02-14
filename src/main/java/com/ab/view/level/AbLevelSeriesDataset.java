package com.ab.view.level;

import java.io.Serializable;

public class AbLevelSeriesDataset
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private AbLevelSeries mSeries = null;

  public AbLevelSeries getmSeries()
  {
    return this.mSeries;
  }

  public void setmSeries(AbLevelSeries paramAbLevelSeries)
  {
    this.mSeries = paramAbLevelSeries;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.level.AbLevelSeriesDataset
 * JD-Core Version:    0.6.2
 */