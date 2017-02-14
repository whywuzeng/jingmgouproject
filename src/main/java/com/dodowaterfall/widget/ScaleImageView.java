package com.dodowaterfall.widget;

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
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View.MeasureSpec;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import com.ismartgo.apppub.R.styleable;

public class ScaleImageView extends ImageView
{
  private Bitmap currentBitmap;
  private ImageChangeListener imageChangeListener;
  private int imageHeight;
  private int imageWidth;
  private Paint paint;
  private Paint paint2;
  private int roundHeight = 5;
  private boolean roundLeftDown;
  private boolean roundLeftUp;
  private boolean roundRightDown;
  private boolean roundRightUp;
  private int roundWidth = 5;
  private boolean scaleToWidth = false;

  public ScaleImageView(Context paramContext)
  {
    super(paramContext);
    init(paramContext, null);
  }

  public ScaleImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet);
  }

  public ScaleImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
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
      this.roundHeight = paramContext.getDimensionPixelSize(1, this.roundHeight);
      this.roundLeftUp = paramContext.getBoolean(2, false);
      this.roundLeftDown = paramContext.getBoolean(4, false);
      this.roundRightUp = paramContext.getBoolean(3, false);
      this.roundRightDown = paramContext.getBoolean(5, false);
    }
    while (true)
    {
      this.paint = new Paint();
      this.paint.setColor(-1);
      this.paint.setAntiAlias(true);
      this.paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
      this.paint2 = new Paint();
      this.paint2.setXfermode(null);
      return;
      float f = paramContext.getResources().getDisplayMetrics().density;
      this.roundWidth = ((int)(this.roundWidth * f));
      this.roundHeight = ((int)(this.roundHeight * f));
    }
  }

  public void draw(Canvas paramCanvas)
  {
    Bitmap localBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    super.draw(localCanvas);
    if (this.roundLeftUp)
      drawLiftUp(localCanvas);
    if (this.roundRightUp)
      drawRightUp(localCanvas);
    if (this.roundLeftDown)
      drawLiftDown(localCanvas);
    if (this.roundRightDown)
      drawRightDown(localCanvas);
    paramCanvas.drawBitmap(localBitmap, 0.0F, 0.0F, this.paint2);
    localBitmap.recycle();
  }

  public ImageChangeListener getImageChangeListener()
  {
    return this.imageChangeListener;
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int k = View.MeasureSpec.getMode(paramInt1);
    int m = View.MeasureSpec.getMode(paramInt2);
    int j = View.MeasureSpec.getSize(paramInt1);
    int i = View.MeasureSpec.getSize(paramInt2);
    if ((k == 1073741824) || (k == -2147483648))
      this.scaleToWidth = true;
    while (this.imageWidth == 0)
    {
      super.onMeasure(paramInt1, paramInt2);
      return;
      if ((m == 1073741824) || (m == -2147483648))
        this.scaleToWidth = false;
      else
        throw new IllegalStateException("width or height needs to be set to match_parent or a specific dimension");
    }
    if (this.scaleToWidth)
    {
      m = this.imageWidth;
      int n = this.imageHeight;
      k = j * n / m;
      paramInt2 = k;
      paramInt1 = j;
      if (i > 0)
      {
        paramInt2 = k;
        paramInt1 = j;
        if (k > i)
        {
          paramInt2 = i;
          paramInt1 = paramInt2 * m / n;
        }
      }
      setScaleType(ImageView.ScaleType.CENTER_CROP);
      setMeasuredDimension(paramInt1, paramInt2);
      return;
    }
    paramInt2 = 0;
    paramInt1 = paramInt2;
    if (getParent() != null)
    {
      paramInt1 = paramInt2;
      if (getParent().getParent() != null)
        paramInt1 = 0 + ((RelativeLayout)getParent().getParent()).getPaddingTop() + ((RelativeLayout)getParent().getParent()).getPaddingBottom();
    }
    setMeasuredDimension(i * this.imageWidth / this.imageHeight, i - paramInt1);
  }

  public void recycle()
  {
    setImageBitmap(null);
    if ((this.currentBitmap == null) || (this.currentBitmap.isRecycled()))
      return;
    this.currentBitmap.recycle();
    this.currentBitmap = null;
  }

  public void setImageBitmap(Bitmap paramBitmap)
  {
    this.currentBitmap = paramBitmap;
    super.setImageBitmap(this.currentBitmap);
    if (this.imageChangeListener != null)
    {
      paramBitmap = this.imageChangeListener;
      if (this.currentBitmap != null)
        break label42;
    }
    label42: for (boolean bool = true; ; bool = false)
    {
      paramBitmap.changed(bool);
      return;
    }
  }

  public void setImageChangeListener(ImageChangeListener paramImageChangeListener)
  {
    this.imageChangeListener = paramImageChangeListener;
  }

  public void setImageDrawable(Drawable paramDrawable)
  {
    super.setImageDrawable(paramDrawable);
    ImageChangeListener localImageChangeListener;
    if (this.imageChangeListener != null)
    {
      localImageChangeListener = this.imageChangeListener;
      if (paramDrawable != null)
        break label31;
    }
    label31: for (boolean bool = true; ; bool = false)
    {
      localImageChangeListener.changed(bool);
      return;
    }
  }

  public void setImageHeight(int paramInt)
  {
    this.imageHeight = paramInt;
  }

  public void setImageResource(int paramInt)
  {
    super.setImageResource(paramInt);
  }

  public void setImageWidth(int paramInt)
  {
    this.imageWidth = paramInt;
  }

  public static abstract interface ImageChangeListener
  {
    public abstract void changed(boolean paramBoolean);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.dodowaterfall.widget.ScaleImageView
 * JD-Core Version:    0.6.2
 */