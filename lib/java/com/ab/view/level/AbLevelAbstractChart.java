package com.ab.view.level;

import android.graphics.Canvas;
import android.graphics.Paint;
import java.io.Serializable;

public abstract class AbLevelAbstractChart
  implements Serializable
{
  private static final long serialVersionUID = 1L;

  public abstract void draw(Canvas paramCanvas, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, Paint paramPaint);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.level.AbLevelAbstractChart
 * JD-Core Version:    0.6.2
 */