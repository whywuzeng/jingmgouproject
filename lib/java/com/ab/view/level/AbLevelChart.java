package com.ab.view.level;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextPaint;
import com.ab.util.AbGraphical;
import com.ab.util.AbViewUtil;

public class AbLevelChart extends AbLevelAbstractChart
{
  private static final long serialVersionUID = 1L;
  private int arrowHeight = 10;
  private int arrowMarginTop = 10;
  private int arrowWidth = 20;
  private int[] color = null;
  private int levelHeight = 20;
  protected AbLevelSeriesDataset mDataset;
  protected AbLevelSeriesRenderer mRenderer;
  private int marginTop = 30;
  private int measureHeight;
  private int measureWidth;
  private float[] part = null;
  private int partTextSize = 15;
  private float[] partValue = null;
  private String textDesc = null;
  private int textDescSize = 22;
  private int textLevelSize = 30;
  private String textValue = null;
  private int textlevelIndex = 0;

  protected AbLevelChart()
  {
  }

  public AbLevelChart(AbLevelSeriesDataset paramAbLevelSeriesDataset, AbLevelSeriesRenderer paramAbLevelSeriesRenderer)
  {
    this.mDataset = paramAbLevelSeriesDataset;
    this.mRenderer = paramAbLevelSeriesRenderer;
    this.measureWidth = paramAbLevelSeriesRenderer.getWidth();
    this.measureHeight = paramAbLevelSeriesRenderer.getHeight();
  }

  public void draw(Canvas paramCanvas, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, Paint paramPaint)
  {
    this.color = this.mRenderer.getColor();
    this.part = this.mRenderer.getPart();
    this.partValue = this.mRenderer.getPartValue();
    this.textValue = this.mRenderer.getTextValue();
    this.textDesc = this.mRenderer.getTextDesc();
    this.textlevelIndex = this.mRenderer.getTextlevelIndex();
    this.textLevelSize = this.mRenderer.getTextLevelSize();
    this.textLevelSize = AbViewUtil.resizeTextSize(paramInt5, paramInt6, this.textLevelSize);
    this.marginTop = this.mRenderer.getMarginTop();
    this.arrowWidth = this.mRenderer.getArrowWidth();
    this.arrowHeight = this.mRenderer.getArrowHeight();
    this.levelHeight = this.mRenderer.getLevelHeight();
    this.arrowMarginTop = this.mRenderer.getArrowMarginTop();
    this.partTextSize = this.mRenderer.getPartTextSize();
    this.textDescSize = this.mRenderer.getTextDescSize();
    TextPaint localTextPaint = new TextPaint(1);
    localTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
    localTextPaint.setTextSize(this.textLevelSize);
    Object localObject1 = localTextPaint.getFontMetrics();
    paramInt2 = (int)Math.ceil(((Paint.FontMetrics)localObject1).descent - ((Paint.FontMetrics)localObject1).ascent) + 2 - 20;
    paramInt4 = (int)AbGraphical.getStringWidth(this.textValue, localTextPaint);
    paramInt5 = (paramInt5 - paramInt3) / 2;
    paramInt6 = this.marginTop + paramInt2 + this.arrowHeight + this.arrowMarginTop;
    localObject1 = new RectF(paramInt5, paramInt6, paramInt5 + paramInt3, this.levelHeight + paramInt6);
    paramPaint.setStyle(Paint.Style.FILL);
    paramPaint.setAntiAlias(true);
    paramPaint.setStrokeWidth(2.0F);
    paramPaint.setColor(Color.rgb(228, 228, 228));
    paramCanvas.drawRoundRect((RectF)localObject1, 1.0F, 1.0F, paramPaint);
    paramInt3 /= 10;
    float f2 = 0.0F;
    float f1 = 0.0F;
    paramInt1 = 0;
    if (paramInt1 >= this.color.length)
      return;
    if (paramInt1 == 0)
    {
      f2 = paramInt5;
      f1 = f2 + this.part[paramInt1] * paramInt3;
    }
    for (localObject1 = new RectF(f2, paramInt6, f1, this.levelHeight + paramInt6); ; localObject1 = new RectF(1.0F + f2, paramInt6, f1, this.levelHeight + paramInt6))
    {
      paramPaint.setColor(this.color[paramInt1]);
      int i;
      if (this.textlevelIndex == paramInt1)
      {
        paramPaint.setFlags(1);
        paramPaint.setTextSize(this.textLevelSize);
        paramPaint.setTypeface(Typeface.DEFAULT_BOLD);
        float f3 = (this.part[paramInt1] * paramInt3 - paramInt4) / 2.0F;
        paramCanvas.drawText(this.textValue, f2 + f3, this.marginTop, paramPaint);
        f3 = (this.part[paramInt1] * paramInt3 - this.arrowWidth) / 2.0F;
        float f4 = f2 + f3 + this.arrowWidth / 2;
        paramPaint.setStyle(Paint.Style.FILL);
        paramPaint.setColor(Color.rgb(153, 234, 71));
        Object localObject2 = new Path();
        ((Path)localObject2).moveTo(f4, this.marginTop + paramInt2 + this.arrowHeight);
        ((Path)localObject2).lineTo(f2 + f3, this.marginTop + paramInt2);
        ((Path)localObject2).lineTo(f2 + f3 + this.arrowWidth, this.marginTop + paramInt2);
        ((Path)localObject2).close();
        paramCanvas.drawPath((Path)localObject2, paramPaint);
        paramPaint.setColor(Color.rgb(227, 227, 227));
        paramPaint.setStyle(Paint.Style.FILL);
        localObject2 = new Path();
        ((Path)localObject2).moveTo(f4, this.marginTop + paramInt2 + this.arrowHeight + this.levelHeight + this.arrowMarginTop * 2);
        ((Path)localObject2).lineTo(f2 + f3, this.marginTop + paramInt2 + this.levelHeight + this.arrowHeight * 2 + this.arrowMarginTop * 2);
        ((Path)localObject2).lineTo(f2 + f3 + this.arrowWidth, this.marginTop + paramInt2 + this.levelHeight + this.arrowHeight * 2 + this.arrowMarginTop * 2);
        ((Path)localObject2).close();
        paramCanvas.drawPath((Path)localObject2, paramPaint);
        i = this.marginTop + paramInt2 + this.arrowHeight * 2 + this.arrowMarginTop * 2 + this.levelHeight;
        paramCanvas.drawRoundRect(new RectF(f4 - this.mRenderer.getTextRectWidth() / 2, i, this.mRenderer.getTextRectWidth() / 2 + f4, this.mRenderer.getTextRectHeight() + i), 5.0F, 5.0F, paramPaint);
        paramPaint.setTypeface(Typeface.DEFAULT_BOLD);
        paramPaint.setTextSize(this.textDescSize);
        paramPaint.setColor(Color.rgb(157, 157, 157));
        localTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        localTextPaint.setTextSize(this.textDescSize);
        localObject2 = localTextPaint.getFontMetrics();
        int j = (int)Math.ceil(((Paint.FontMetrics)localObject2).descent - ((Paint.FontMetrics)localObject2).ascent);
        int k = (int)AbGraphical.getStringWidth(this.textDesc, localTextPaint);
        paramCanvas.drawText(this.textDesc, f4 - k / 2, i + 20 + (this.mRenderer.getTextRectHeight() - (j + 2)) / 2, paramPaint);
        paramPaint.setColor(this.color[paramInt1]);
      }
      paramCanvas.drawRoundRect((RectF)localObject1, 1.0F, 1.0F, paramPaint);
      if ((this.partValue != null) && (this.partValue.length == this.color.length))
      {
        paramPaint.setTextSize(this.partTextSize);
        localTextPaint.setTextSize(this.partTextSize);
        i = (int)AbGraphical.getStringWidth(String.valueOf(this.partValue[paramInt1]), localTextPaint);
        paramCanvas.drawText(String.valueOf(this.partValue[paramInt1]), ((RectF)localObject1).left - i / 2, ((RectF)localObject1).top + this.levelHeight + 15.0F, paramPaint);
      }
      paramInt1 += 1;
      break;
      f2 += this.part[(paramInt1 - 1)] * paramInt3;
      f1 += this.part[paramInt1] * paramInt3;
    }
  }

  public int getHeight()
  {
    return this.measureHeight;
  }

  public int getWidth()
  {
    return this.measureWidth;
  }

  protected void setDatasetRenderer(AbLevelSeriesDataset paramAbLevelSeriesDataset, AbLevelSeriesRenderer paramAbLevelSeriesRenderer)
  {
    this.mDataset = paramAbLevelSeriesDataset;
    this.mRenderer = paramAbLevelSeriesRenderer;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.level.AbLevelChart
 * JD-Core Version:    0.6.2
 */