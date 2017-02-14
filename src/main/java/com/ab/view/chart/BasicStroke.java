package com.ab.view.chart;

import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import java.io.Serializable;

public class BasicStroke
  implements Serializable
{
  public static final BasicStroke DASHED = new BasicStroke(Paint.Cap.ROUND, Paint.Join.BEVEL, 10.0F, new float[] { 10.0F, 10.0F }, 1.0F);
  public static final BasicStroke DOTTED = new BasicStroke(Paint.Cap.ROUND, Paint.Join.BEVEL, 5.0F, new float[] { 2.0F, 10.0F }, 1.0F);
  public static final BasicStroke SOLID = new BasicStroke(Paint.Cap.BUTT, Paint.Join.MITER, 4.0F, null, 0.0F);
  private Paint.Cap mCap;
  private float[] mIntervals;
  private Paint.Join mJoin;
  private float mMiter;
  private float mPhase;

  public BasicStroke(Paint.Cap paramCap, Paint.Join paramJoin, float paramFloat1, float[] paramArrayOfFloat, float paramFloat2)
  {
    this.mCap = paramCap;
    this.mJoin = paramJoin;
    this.mMiter = paramFloat1;
    this.mIntervals = paramArrayOfFloat;
  }

  public Paint.Cap getCap()
  {
    return this.mCap;
  }

  public float[] getIntervals()
  {
    return this.mIntervals;
  }

  public Paint.Join getJoin()
  {
    return this.mJoin;
  }

  public float getMiter()
  {
    return this.mMiter;
  }

  public float getPhase()
  {
    return this.mPhase;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.chart.BasicStroke
 * JD-Core Version:    0.6.2
 */