package com.mob.tools.gui;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.Ln;
import com.mob.tools.utils.UIHandler;
import java.util.Random;

public class AsyncImageView extends ImageView
  implements BitmapProcessor.BitmapCallback, Handler.Callback
{
  private static final int MSG_IMG_GOT = 1;
  private static final Random rnd = new Random();
  private Bitmap defaultBm;
  private int defaultRes;
  private float[] rect;
  private boolean scaleToCrop;
  private String url;

  public AsyncImageView(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }

  public AsyncImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }

  public AsyncImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }

  private int[] getSize()
  {
    int k = getWidth();
    int m = getHeight();
    int i;
    int j;
    if (k != 0)
    {
      i = m;
      j = k;
      if (m != 0);
    }
    else
    {
      ViewGroup.LayoutParams localLayoutParams = getLayoutParams();
      i = m;
      j = k;
      if (localLayoutParams != null)
      {
        j = localLayoutParams.width;
        i = localLayoutParams.height;
      }
    }
    if (j != 0)
    {
      k = i;
      if (i != 0);
    }
    else
    {
      measure(0, 0);
      j = getMeasuredWidth();
      k = getMeasuredHeight();
    }
    return new int[] { j, k };
  }

  private Bitmap goCrop(Bitmap paramBitmap)
  {
    float f1 = paramBitmap.getWidth();
    float f2 = paramBitmap.getHeight();
    if ((f1 == 0.0F) || (f2 == 0.0F));
    Object localObject;
    float f3;
    do
    {
      do
      {
        return paramBitmap;
        localObject = getSize();
      }
      while ((localObject[0] == 0) || (localObject[1] == 0));
      f3 = localObject[1] * f1 / localObject[0];
    }
    while (f3 == f2);
    int[] arrayOfInt = new int[4];
    if (f3 < f2)
    {
      arrayOfInt[1] = ((int)((f2 - f3) / 2.0F));
      arrayOfInt[3] = arrayOfInt[1];
    }
    try
    {
      while (true)
      {
        localObject = BitmapHelper.cropBitmap(paramBitmap, arrayOfInt[0], arrayOfInt[1], arrayOfInt[2], arrayOfInt[3]);
        return localObject;
        arrayOfInt[0] = ((int)((f1 - f2 * localObject[0] / localObject[1]) / 2.0F));
        arrayOfInt[2] = arrayOfInt[0];
      }
    }
    catch (Throwable localThrowable)
    {
      Ln.w(localThrowable);
    }
    return paramBitmap;
  }

  private Bitmap goRound(Bitmap paramBitmap)
  {
    try
    {
      Object localObject = getSize();
      localObject = BitmapHelper.roundBitmap(paramBitmap, localObject[0], localObject[1], this.rect[0], this.rect[1], this.rect[2], this.rect[3]);
      return localObject;
    }
    catch (Throwable localThrowable)
    {
      Ln.w(localThrowable);
    }
    return paramBitmap;
  }

  private void init(Context paramContext)
  {
    if (isInEditMode())
    {
      setBackgroundColor(-16777216);
      return;
    }
    BitmapProcessor.prepare(paramContext);
  }

  public void execute(String paramString, int paramInt)
  {
    Ln.d("AsyncImageView, %s", new Object[] { paramString });
    this.url = paramString;
    this.defaultRes = paramInt;
    if (TextUtils.isEmpty(paramString))
    {
      setImageResource(paramInt);
      return;
    }
    Bitmap localBitmap = BitmapProcessor.getBitmapFromCache(paramString);
    if ((localBitmap != null) && (!localBitmap.isRecycled()))
    {
      setBitmap(localBitmap);
      return;
    }
    if (paramInt > 0)
      setImageResource(paramInt);
    Ln.w("BitmapProcessor, %s", new Object[] { paramString });
    BitmapProcessor.process(paramString, this);
  }

  public void execute(String paramString, Bitmap paramBitmap)
  {
    Ln.d("AsyncImageView, %s", new Object[] { paramString });
    this.url = paramString;
    this.defaultBm = paramBitmap;
    if (TextUtils.isEmpty(paramString))
    {
      setImageBitmap(paramBitmap);
      return;
    }
    Bitmap localBitmap = BitmapProcessor.getBitmapFromCache(paramString);
    if ((localBitmap != null) && (!localBitmap.isRecycled()))
    {
      setBitmap(localBitmap);
      return;
    }
    if ((paramBitmap != null) && (!paramBitmap.isRecycled()))
      setImageBitmap(paramBitmap);
    Ln.w("BitmapProcessor, %s", new Object[] { paramString });
    BitmapProcessor.process(paramString, this);
  }

  public boolean handleMessage(Message paramMessage)
  {
    if (paramMessage.what == 1)
    {
      Object localObject = ((Object[])(Object[])paramMessage.obj)[0];
      paramMessage = ((Object[])(Object[])paramMessage.obj)[1];
      if ((paramMessage != null) && (localObject != null) && (localObject.equals(this.url)))
        setImageBitmap((Bitmap)paramMessage);
    }
    else
    {
      return false;
    }
    if ((this.defaultBm != null) && (!this.defaultBm.isRecycled()))
    {
      setImageBitmap(this.defaultBm);
      return false;
    }
    setImageResource(this.defaultRes);
    return false;
  }

  public void onImageGot(String paramString, Bitmap paramBitmap)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramString != null)
    {
      localObject1 = localObject2;
      if (paramString.trim().length() > 0)
      {
        localObject1 = localObject2;
        if (paramString.equals(this.url))
          localObject1 = paramBitmap;
      }
    }
    paramBitmap = (Bitmap)localObject1;
    if (localObject1 != null)
    {
      localObject2 = localObject1;
      if (this.scaleToCrop)
        localObject2 = goCrop((Bitmap)localObject1);
      paramBitmap = (Bitmap)localObject2;
      if (this.rect != null)
        paramBitmap = goRound((Bitmap)localObject2);
    }
    localObject1 = new Message();
    ((Message)localObject1).what = 1;
    ((Message)localObject1).obj = new Object[] { paramString, paramBitmap };
    UIHandler.sendMessageDelayed((Message)localObject1, rnd.nextInt(300), this);
  }

  public void setBitmap(Bitmap paramBitmap)
  {
    Bitmap localBitmap = paramBitmap;
    if (this.scaleToCrop)
      localBitmap = goCrop(paramBitmap);
    paramBitmap = localBitmap;
    if (this.rect != null)
      paramBitmap = goRound(localBitmap);
    setImageBitmap(paramBitmap);
  }

  public void setRound(float paramFloat)
  {
    setRound(paramFloat, paramFloat, paramFloat, paramFloat);
  }

  public void setRound(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    this.rect = new float[] { paramFloat1, paramFloat2, paramFloat3, paramFloat4 };
  }

  public void setScaleToCropCenter(boolean paramBoolean)
  {
    this.scaleToCrop = paramBoolean;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.gui.AsyncImageView
 * JD-Core Version:    0.6.2
 */