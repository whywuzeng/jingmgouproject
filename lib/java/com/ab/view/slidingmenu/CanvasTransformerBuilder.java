package com.ab.view.slidingmenu;

import android.graphics.Canvas;
import android.view.animation.Interpolator;

public class CanvasTransformerBuilder
{
  private static Interpolator lin = new Interpolator()
  {
    public float getInterpolation(float paramAnonymousFloat)
    {
      return paramAnonymousFloat;
    }
  };
  private SlidingMenu.CanvasTransformer mTrans;

  private void initTransformer()
  {
    if (this.mTrans == null)
      this.mTrans = new SlidingMenu.CanvasTransformer()
      {
        public void transformCanvas(Canvas paramAnonymousCanvas, float paramAnonymousFloat)
        {
        }
      };
  }

  public SlidingMenu.CanvasTransformer concatTransformer(final SlidingMenu.CanvasTransformer paramCanvasTransformer)
  {
    initTransformer();
    this.mTrans = new SlidingMenu.CanvasTransformer()
    {
      public void transformCanvas(Canvas paramAnonymousCanvas, float paramAnonymousFloat)
      {
        CanvasTransformerBuilder.this.mTrans.transformCanvas(paramAnonymousCanvas, paramAnonymousFloat);
        paramCanvasTransformer.transformCanvas(paramAnonymousCanvas, paramAnonymousFloat);
      }
    };
    return this.mTrans;
  }

  public SlidingMenu.CanvasTransformer rotate(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return rotate(paramInt1, paramInt2, paramInt3, paramInt4, lin);
  }

  public SlidingMenu.CanvasTransformer rotate(final int paramInt1, final int paramInt2, final int paramInt3, final int paramInt4, final Interpolator paramInterpolator)
  {
    initTransformer();
    this.mTrans = new SlidingMenu.CanvasTransformer()
    {
      public void transformCanvas(Canvas paramAnonymousCanvas, float paramAnonymousFloat)
      {
        CanvasTransformerBuilder.this.mTrans.transformCanvas(paramAnonymousCanvas, paramAnonymousFloat);
        paramAnonymousFloat = paramInterpolator.getInterpolation(paramAnonymousFloat);
        paramAnonymousCanvas.rotate((paramInt1 - paramInt2) * paramAnonymousFloat + paramInt2, paramInt3, paramInt4);
      }
    };
    return this.mTrans;
  }

  public SlidingMenu.CanvasTransformer translate(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return translate(paramInt1, paramInt2, paramInt3, paramInt4, lin);
  }

  public SlidingMenu.CanvasTransformer translate(final int paramInt1, final int paramInt2, final int paramInt3, final int paramInt4, final Interpolator paramInterpolator)
  {
    initTransformer();
    this.mTrans = new SlidingMenu.CanvasTransformer()
    {
      public void transformCanvas(Canvas paramAnonymousCanvas, float paramAnonymousFloat)
      {
        CanvasTransformerBuilder.this.mTrans.transformCanvas(paramAnonymousCanvas, paramAnonymousFloat);
        paramAnonymousFloat = paramInterpolator.getInterpolation(paramAnonymousFloat);
        paramAnonymousCanvas.translate((paramInt1 - paramInt2) * paramAnonymousFloat + paramInt2, (paramInt3 - paramInt4) * paramAnonymousFloat + paramInt4);
      }
    };
    return this.mTrans;
  }

  public SlidingMenu.CanvasTransformer zoom(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    return zoom(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, lin);
  }

  public SlidingMenu.CanvasTransformer zoom(final int paramInt1, final int paramInt2, final int paramInt3, final int paramInt4, final int paramInt5, final int paramInt6, final Interpolator paramInterpolator)
  {
    initTransformer();
    this.mTrans = new SlidingMenu.CanvasTransformer()
    {
      public void transformCanvas(Canvas paramAnonymousCanvas, float paramAnonymousFloat)
      {
        CanvasTransformerBuilder.this.mTrans.transformCanvas(paramAnonymousCanvas, paramAnonymousFloat);
        paramAnonymousFloat = paramInterpolator.getInterpolation(paramAnonymousFloat);
        paramAnonymousCanvas.scale((paramInt1 - paramInt2) * paramAnonymousFloat + paramInt2, (paramInt3 - paramInt4) * paramAnonymousFloat + paramInt4, paramInt5, paramInt6);
      }
    };
    return this.mTrans;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.slidingmenu.CanvasTransformerBuilder
 * JD-Core Version:    0.6.2
 */