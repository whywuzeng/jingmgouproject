package com.ismartgo.app.ownself.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import com.ismartgo.apppub.R.styleable;

public class FilletImageView extends ImageView
{
  private Paint paint;
  private Paint paint2;
  private int roundHeight = 5;
  private int roundWidth = 5;

  public FilletImageView(Context paramContext)
  {
    super(paramContext);
    init(paramContext, null);
  }

  public FilletImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet);
  }

  public FilletImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet);
  }

  private void drawLiftDown(Canvas paramCanvas)
  {
    Path localPath = new Path();
    localPath.moveTo(0.0F, getHeight() - this.roundHeight);
    localPath.lineTo(0.0F, getHeight());
    localPath.lineTo(this.roundWidth, getHeight());
    localPath.arcTo(new RectF(0.0F, getHeight() - this.roundHeight * 2, this.roundWidth * 2 + 0, getHeight()), 90.0F, 90.0F);
    localPath.close();
    paramCanvas.drawPath(localPath, this.paint);
  }

  private void drawLiftUp(Canvas paramCanvas)
  {
    Path localPath = new Path();
    localPath.moveTo(0.0F, this.roundHeight);
    localPath.lineTo(0.0F, 0.0F);
    localPath.lineTo(this.roundWidth, 0.0F);
    localPath.arcTo(new RectF(0.0F, 0.0F, this.roundWidth * 2, this.roundHeight * 2), -90.0F, -90.0F);
    localPath.close();
    paramCanvas.drawPath(localPath, this.paint);
  }

  private void drawRightDown(Canvas paramCanvas)
  {
    Path localPath = new Path();
    localPath.moveTo(getWidth() - this.roundWidth, getHeight());
    localPath.lineTo(getWidth(), getHeight());
    localPath.lineTo(getWidth(), getHeight() - this.roundHeight);
    localPath.arcTo(new RectF(getWidth() - this.roundWidth * 2, getHeight() - this.roundHeight * 2, getWidth(), getHeight()), 0.0F, 90.0F);
    localPath.close();
    paramCanvas.drawPath(localPath, this.paint);
  }

  private void drawRightUp(Canvas paramCanvas)
  {
    Path localPath = new Path();
    localPath.moveTo(getWidth(), this.roundHeight);
    localPath.lineTo(getWidth(), 0.0F);
    localPath.lineTo(getWidth() - this.roundWidth, 0.0F);
    localPath.arcTo(new RectF(getWidth() - this.roundWidth * 2, 0.0F, getWidth(), this.roundHeight * 2 + 0), -90.0F, 90.0F);
    localPath.close();
    paramCanvas.drawPath(localPath, this.paint);
  }

  private void init(Context paramContext, AttributeSet paramAttributeSet)
  {
    if (paramAttributeSet != null)
    {
      paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.RoundAngleImageView);
      this.roundWidth = paramContext.getDimensionPixelSize(0, this.roundWidth);
    }
    float f;
    for (this.roundHeight = paramContext.getDimensionPixelSize(1, this.roundHeight); ; this.roundHeight = ((int)(this.roundHeight * f)))
    {
      this.paint = new Paint();
      this.paint.setColor(-1);
      this.paint.setAntiAlias(true);
      this.paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
      this.paint2 = new Paint();
      this.paint2.setXfermode(null);
      return;
      f = paramContext.getResources().getDisplayMetrics().density;
      this.roundWidth = ((int)(this.roundWidth * f));
    }
  }

  public void draw(Canvas paramCanvas)
  {
    Bitmap localBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    super.draw(localCanvas);
    drawLiftUp(localCanvas);
    drawRightUp(localCanvas);
    drawLiftDown(localCanvas);
    drawRightDown(localCanvas);
    paramCanvas.drawBitmap(localBitmap, 0.0F, 0.0F, this.paint2);
    localBitmap.recycle();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.ownself.view.FilletImageView
 * JD-Core Version:    0.6.2
 */