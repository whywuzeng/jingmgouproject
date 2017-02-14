package com.ab.view.chart;

public class FitZoom extends AbstractTool
{
  public FitZoom(AbstractChart paramAbstractChart)
  {
    super(paramAbstractChart);
  }

  public void apply()
  {
    if ((this.mChart instanceof XYChart))
    {
      if (((XYChart)this.mChart).getDataset() == null);
      int k;
      int m;
      do
      {
        while (true)
        {
          return;
          k = this.mRenderer.getScalesCount();
          if (!this.mRenderer.isInitialRangeSet())
            break;
          i = 0;
          while (i < k)
          {
            if (this.mRenderer.isInitialRangeSet(i))
              this.mRenderer.setRange(this.mRenderer.getInitialRange(i), i);
            i += 1;
          }
        }
        localObject = ((XYChart)this.mChart).getDataset().getSeries();
        m = localObject.length;
      }
      while (m <= 0);
      int i = 0;
      label120: double[] arrayOfDouble;
      int j;
      if (i < k)
      {
        arrayOfDouble = new double[4];
        double[] tmp134_132 = arrayOfDouble;
        tmp134_132[0] = 1.7976931348623157E+308D;
        double[] tmp140_134 = tmp134_132;
        tmp140_134[1] = -1.797693134862316E+308D;
        double[] tmp146_140 = tmp140_134;
        tmp146_140[2] = 1.7976931348623157E+308D;
        double[] tmp152_146 = tmp146_140;
        tmp152_146[3] = -1.797693134862316E+308D;
        tmp152_146;
        j = 0;
      }
      while (true)
      {
        if (j >= m)
        {
          double d1 = Math.abs(tmp140_134[1] - tmp140_134[0]) / 40.0D;
          double d2 = Math.abs(tmp140_134[3] - tmp140_134[2]) / 40.0D;
          this.mRenderer.setRange(new double[] { tmp140_134[0] - d1, tmp140_134[1] + d1, tmp140_134[2] - d2, tmp140_134[3] + d2 }, i);
          i += 1;
          break label120;
          break;
        }
        if (i == tmp134_132[j].getScaleNumber())
        {
          tmp140_134[0] = Math.min(tmp140_134[0], tmp134_132[j].getMinX());
          tmp140_134[1] = Math.max(tmp140_134[1], tmp134_132[j].getMaxX());
          tmp140_134[2] = Math.min(tmp140_134[2], tmp134_132[j].getMinY());
          tmp140_134[3] = Math.max(tmp140_134[3], tmp134_132[j].getMaxY());
        }
        j += 1;
      }
    }
    double[] tmp134_132 = ((RoundChart)this.mChart).getRenderer();
    ((DefaultRenderer)tmp134_132).setScale(((DefaultRenderer)tmp134_132).getOriginalScale());
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.FitZoom
 * JD-Core Version:    0.6.2
 */