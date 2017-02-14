package com.ab.view.chart;

import android.content.Context;
import android.util.Log;

public class ChartFactory
{
  public static final String CHART = "chart";
  public static final String TITLE = "title";

  private static boolean checkMultipleSeriesItems(MultipleCategorySeries paramMultipleCategorySeries, int paramInt)
  {
    int i = paramMultipleCategorySeries.getCategoriesCount();
    boolean bool = true;
    paramInt = 0;
    if ((paramInt >= i) || (!bool))
      return bool;
    if (paramMultipleCategorySeries.getValues(paramInt).length == paramMultipleCategorySeries.getTitles(paramInt).length);
    for (bool = true; ; bool = false)
    {
      paramInt += 1;
      break;
    }
  }

  private static void checkParameters(CategorySeries paramCategorySeries, DefaultRenderer paramDefaultRenderer)
  {
    if ((paramCategorySeries == null) || (paramDefaultRenderer == null) || (paramCategorySeries.getItemCount() != paramDefaultRenderer.getSeriesRendererCount()))
      throw new IllegalArgumentException("Dataset and renderer should be not null and the dataset number of items should be equal to the number of series renderers");
  }

  private static void checkParameters(MultipleCategorySeries paramMultipleCategorySeries, DefaultRenderer paramDefaultRenderer)
  {
    if ((paramMultipleCategorySeries == null) || (paramDefaultRenderer == null) || (!checkMultipleSeriesItems(paramMultipleCategorySeries, paramDefaultRenderer.getSeriesRendererCount())))
      throw new IllegalArgumentException("Titles and values should be not null and the dataset number of items should be equal to the number of series renderers");
  }

  private static void checkParameters(XYMultipleSeriesDataset paramXYMultipleSeriesDataset, XYMultipleSeriesRenderer paramXYMultipleSeriesRenderer)
  {
    if ((paramXYMultipleSeriesDataset == null) || (paramXYMultipleSeriesRenderer == null) || (paramXYMultipleSeriesDataset.getSeriesCount() != paramXYMultipleSeriesRenderer.getSeriesRendererCount()))
      throw new IllegalArgumentException("Dataset and renderer should be not null and should have the same number of series");
  }

  public static final GraphicalView getAreaChartView(Context paramContext, XYMultipleSeriesDataset paramXYMultipleSeriesDataset, XYMultipleSeriesRenderer paramXYMultipleSeriesRenderer, float paramFloat)
  {
    checkParameters(paramXYMultipleSeriesDataset, paramXYMultipleSeriesRenderer);
    return new GraphicalView(paramContext, new AreaChart(paramXYMultipleSeriesDataset, paramXYMultipleSeriesRenderer, paramFloat));
  }

  public static final GraphicalView getBarChartView(Context paramContext, XYMultipleSeriesDataset paramXYMultipleSeriesDataset, XYMultipleSeriesRenderer paramXYMultipleSeriesRenderer, BarChart.Type paramType)
  {
    checkParameters(paramXYMultipleSeriesDataset, paramXYMultipleSeriesRenderer);
    return new GraphicalView(paramContext, new BarChart(paramXYMultipleSeriesDataset, paramXYMultipleSeriesRenderer, paramType));
  }

  public static final GraphicalView getLineChartView(Context paramContext, XYMultipleSeriesDataset paramXYMultipleSeriesDataset, XYMultipleSeriesRenderer paramXYMultipleSeriesRenderer)
  {
    checkParameters(paramXYMultipleSeriesDataset, paramXYMultipleSeriesRenderer);
    return new GraphicalView(paramContext, new LineChart(paramXYMultipleSeriesDataset, paramXYMultipleSeriesRenderer));
  }

  public static final GraphicalView getPieChartView(Context paramContext, CategorySeries paramCategorySeries, DefaultRenderer paramDefaultRenderer)
  {
    checkParameters(paramCategorySeries, paramDefaultRenderer);
    return new GraphicalView(paramContext, new PieChart(paramCategorySeries, paramDefaultRenderer));
  }

  public static final GraphicalView getTimeChartView(Context paramContext, XYMultipleSeriesDataset paramXYMultipleSeriesDataset, XYMultipleSeriesRenderer paramXYMultipleSeriesRenderer, String paramString)
  {
    checkParameters(paramXYMultipleSeriesDataset, paramXYMultipleSeriesRenderer);
    paramXYMultipleSeriesDataset = new TimeChart(paramXYMultipleSeriesDataset, paramXYMultipleSeriesRenderer);
    Log.d("ChartFactory", "TimeChart:" + paramXYMultipleSeriesDataset.mDataset);
    paramXYMultipleSeriesDataset.setDateFormat(paramString);
    return new GraphicalView(paramContext, paramXYMultipleSeriesDataset);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.ChartFactory
 * JD-Core Version:    0.6.2
 */