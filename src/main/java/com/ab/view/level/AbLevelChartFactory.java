package com.ab.view.level;

import android.content.Context;

public class AbLevelChartFactory
{
  private static void checkParameters(AbLevelSeriesDataset paramAbLevelSeriesDataset, AbLevelSeriesRenderer paramAbLevelSeriesRenderer)
  {
    if ((paramAbLevelSeriesDataset == null) || (paramAbLevelSeriesRenderer == null))
      throw new IllegalArgumentException("Dataset and renderer should be not null");
  }

  public static final AbLevelView getLevelChartView(Context paramContext, AbLevelSeriesDataset paramAbLevelSeriesDataset, AbLevelSeriesRenderer paramAbLevelSeriesRenderer)
  {
    checkParameters(paramAbLevelSeriesDataset, paramAbLevelSeriesRenderer);
    return new AbLevelView(paramContext, new AbLevelChart(paramAbLevelSeriesDataset, paramAbLevelSeriesRenderer));
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.level.AbLevelChartFactory
 * JD-Core Version:    0.6.2
 */