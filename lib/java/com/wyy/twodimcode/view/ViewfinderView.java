package com.wyy.twodimcode.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import com.google.zxing.ResultPoint;
import com.wyy.twodimcode.camera.CameraManager;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public final class ViewfinderView extends View
{
  private static final long ANIMATION_DELAY = 10L;
  private static final int CORNER_WIDTH = 16;
  private static final int MIDDLE_LINE_PADDING = 5;
  private static final int MIDDLE_LINE_WIDTH = 6;
  private static final int OPAQUE = 255;
  private static final int[] SCANNER_ALPHA = arrayOfInt;
  private static final int SPEEN_DISTANCE = 5;
  private static final int TEXT_PADDING_TOP = 30;
  private static final int TEXT_SIZE = 16;
  private static float density;
  private int ScreenRate;
  private final int frameColor;
  private boolean isFirst;
  private final int laserColor;
  private Collection<ResultPoint> lastPossibleResultPoints;
  private final int maskColor;
  private final Paint paint;
  private Collection<ResultPoint> possibleResultPoints;
  private Bitmap resultBitmap;
  private final int resultColor;
  private final int resultPointColor;
  private int scannerAlpha;
  private int slideBottom;
  private int slideTop;

  static
  {
    int[] arrayOfInt = new int[8];
    arrayOfInt[1] = 64;
    arrayOfInt[2] = 128;
    arrayOfInt[3] = 192;
    arrayOfInt[4] = 255;
    arrayOfInt[5] = 192;
    arrayOfInt[6] = 128;
    arrayOfInt[7] = 64;
  }

  public ViewfinderView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    density = paramContext.getResources().getDisplayMetrics().density;
    this.ScreenRate = ((int)(25.0F * density));
    this.paint = new Paint();
    paramContext = getResources();
    this.maskColor = paramContext.getColor(2131099652);
    this.resultColor = paramContext.getColor(2131099654);
    this.frameColor = -1;
    this.laserColor = paramContext.getColor(2131099651);
    this.resultPointColor = paramContext.getColor(2131099649);
    this.scannerAlpha = 0;
    this.possibleResultPoints = new HashSet(5);
  }

  public void addPossibleResultPoint(ResultPoint paramResultPoint)
  {
    this.possibleResultPoints.add(paramResultPoint);
  }

  public void drawResultBitmap(Bitmap paramBitmap)
  {
    this.resultBitmap = paramBitmap;
    invalidate();
  }

  public void drawViewfinder()
  {
    this.resultBitmap = null;
    invalidate();
  }

  public void onDraw(Canvas paramCanvas)
  {
    Rect localRect = CameraManager.get().getFramingRect();
    if (localRect == null)
      return;
    if (!this.isFirst)
    {
      this.isFirst = true;
      this.slideTop = (localRect.top + 16);
      this.slideBottom = (localRect.bottom - 16);
    }
    int j = paramCanvas.getWidth();
    int k = paramCanvas.getHeight();
    Object localObject1 = this.paint;
    if (this.resultBitmap != null);
    for (int i = this.resultColor; ; i = this.maskColor)
    {
      ((Paint)localObject1).setColor(i);
      paramCanvas.drawRect(0.0F, 0.0F, j, localRect.top, this.paint);
      paramCanvas.drawRect(0.0F, localRect.top, localRect.left, localRect.bottom + 1, this.paint);
      paramCanvas.drawRect(localRect.right + 1, localRect.top, j, localRect.bottom + 1, this.paint);
      paramCanvas.drawRect(0.0F, localRect.bottom + 1, j, k, this.paint);
      if (this.resultBitmap == null)
        break;
      this.paint.setAlpha(255);
      paramCanvas.drawBitmap(this.resultBitmap, localRect.left, localRect.top, this.paint);
      return;
    }
    this.paint.setColor(this.frameColor);
    paramCanvas.drawRect(localRect.left, localRect.top, localRect.right + 1, localRect.top + 2, this.paint);
    paramCanvas.drawRect(localRect.left, localRect.top + 2, localRect.left + 2, localRect.bottom - 1, this.paint);
    paramCanvas.drawRect(localRect.right - 1, localRect.top, localRect.right + 1, localRect.bottom - 1, this.paint);
    paramCanvas.drawRect(localRect.left, localRect.bottom - 1, localRect.right + 1, localRect.bottom + 1, this.paint);
    paramCanvas.drawRect(localRect.left - 8, localRect.top - 8, localRect.left + this.ScreenRate, localRect.top + 8, this.paint);
    paramCanvas.drawRect(localRect.left - 8, localRect.top - 8, localRect.left + 8, localRect.top + this.ScreenRate, this.paint);
    paramCanvas.drawRect(localRect.left - 8, localRect.bottom - this.ScreenRate, localRect.left + 8, localRect.bottom + 8, this.paint);
    paramCanvas.drawRect(localRect.left - 8, localRect.bottom - 8, localRect.left + this.ScreenRate, localRect.bottom + 8, this.paint);
    paramCanvas.drawRect(localRect.right - this.ScreenRate, localRect.top - 8, localRect.right + 8, localRect.top + 8, this.paint);
    paramCanvas.drawRect(localRect.right - 8, localRect.top - 8, localRect.right + 8, localRect.top + this.ScreenRate, this.paint);
    paramCanvas.drawRect(localRect.right - 8, localRect.bottom - this.ScreenRate, localRect.right + 8, localRect.bottom + 8, this.paint);
    paramCanvas.drawRect(localRect.right - this.ScreenRate, localRect.bottom - 8, localRect.right + 8, localRect.bottom + 8, this.paint);
    this.slideTop += 5;
    if (this.slideTop >= this.slideBottom)
      this.slideTop = (localRect.top + 16);
    localObject1 = new Rect();
    ((Rect)localObject1).left = localRect.left;
    ((Rect)localObject1).right = localRect.right;
    ((Rect)localObject1).top = this.slideTop;
    ((Rect)localObject1).bottom = (this.slideTop + 5);
    paramCanvas.drawBitmap(((BitmapDrawable)getResources().getDrawable(2130837830)).getBitmap(), null, (Rect)localObject1, this.paint);
    this.paint.setColor(-1);
    this.paint.setTextSize(16.0F * density);
    this.paint.setTypeface(Typeface.create("System", 1));
    this.paint.setTextAlign(Paint.Align.CENTER);
    paramCanvas.drawText(getResources().getString(2131296383), localRect.centerX(), localRect.top - 30.0F * density, this.paint);
    Object localObject2 = this.possibleResultPoints;
    localObject1 = this.lastPossibleResultPoints;
    if (((Collection)localObject2).isEmpty())
    {
      this.lastPossibleResultPoints = null;
      if (localObject1 != null)
      {
        this.paint.setAlpha(127);
        this.paint.setColor(this.resultPointColor);
        localObject1 = ((Collection)localObject1).iterator();
      }
    }
    while (true)
    {
      if (!((Iterator)localObject1).hasNext())
      {
        postInvalidateDelayed(10L, localRect.left, localRect.top, localRect.right, localRect.bottom);
        return;
        this.possibleResultPoints = new HashSet(5);
        this.lastPossibleResultPoints = ((Collection)localObject2);
        this.paint.setAlpha(255);
        this.paint.setColor(this.resultPointColor);
        localObject2 = ((Collection)localObject2).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          ResultPoint localResultPoint = (ResultPoint)((Iterator)localObject2).next();
          paramCanvas.drawCircle(localRect.left + localResultPoint.getX(), localRect.top + localResultPoint.getY(), 6.0F, this.paint);
        }
        break;
      }
      localObject2 = (ResultPoint)((Iterator)localObject1).next();
      paramCanvas.drawCircle(localRect.left + ((ResultPoint)localObject2).getX(), localRect.top + ((ResultPoint)localObject2).getY(), 3.0F, this.paint);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.wyy.twodimcode.view.ViewfinderView
 * JD-Core Version:    0.6.2
 */