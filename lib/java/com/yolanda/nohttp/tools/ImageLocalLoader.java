package com.yolanda.nohttp.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import com.yolanda.nohttp.Logger;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageLocalLoader
{
  private static final Object SINGLE_OBJECT = new Object();
  private static ImageLocalLoader mInstance;
  private Drawable mDefaultDrawable = new ColorDrawable(-7829368);
  private ExecutorService mExecutorService = Executors.newSingleThreadExecutor();
  private LruCache<String, Bitmap> mLruCache = new LruCache((int)(Runtime.getRuntime().maxMemory() / 8L))
  {
    @SuppressLint({"NewApi"})
    protected int sizeOf(String paramAnonymousString, Bitmap paramAnonymousBitmap)
    {
      if (Build.VERSION.SDK_INT >= 19)
        return paramAnonymousBitmap.getByteCount();
      return paramAnonymousBitmap.getRowBytes() * paramAnonymousBitmap.getHeight();
    }
  };
  private Handler mPosterHandler;

  private void addImageToCache(String paramString, Bitmap paramBitmap)
  {
    if ((getImageFromCache(paramString) == null) && (paramBitmap != null))
      this.mLruCache.put(paramString, paramBitmap);
  }

  private Bitmap getImageFromCache(String paramString)
  {
    return (Bitmap)this.mLruCache.get(paramString);
  }

  public static ImageLocalLoader getInstance()
  {
    synchronized (SINGLE_OBJECT)
    {
      if (mInstance == null)
        mInstance = new ImageLocalLoader();
      return mInstance;
    }
  }

  private Handler getPostHandler()
  {
    synchronized (SINGLE_OBJECT)
    {
      if (this.mPosterHandler == null)
        this.mPosterHandler = new Handler(Looper.getMainLooper());
      return this.mPosterHandler;
    }
  }

  public void loadImage(ImageView paramImageView, String paramString)
  {
    loadImage(paramImageView, paramString, 0, 0, null);
  }

  public void loadImage(ImageView paramImageView, String paramString, int paramInt1, int paramInt2)
  {
    loadImage(paramImageView, paramString, paramInt1, paramInt2, null);
  }

  public void loadImage(ImageView paramImageView, String paramString, int paramInt1, int paramInt2, ImageLoadListener paramImageLoadListener)
  {
    if (paramImageLoadListener == null)
      paramImageView.setTag(paramString);
    Bitmap localBitmap = getImageFromCache(paramString + paramInt1 + paramInt2);
    if (localBitmap != null)
    {
      ImgBeanHolder localImgBeanHolder = new ImgBeanHolder(null);
      localImgBeanHolder.imageView = paramImageView;
      localImgBeanHolder.imagePath = paramString;
      localImgBeanHolder.bitmap = localBitmap;
      localImgBeanHolder.imageLoadListener = paramImageLoadListener;
      getPostHandler().post(localImgBeanHolder);
      return;
    }
    paramImageView.setImageDrawable(this.mDefaultDrawable);
    this.mExecutorService.execute(new TaskThread(paramImageView, paramString, paramInt1, paramInt2, paramImageLoadListener));
  }

  public void loadImage(ImageView paramImageView, String paramString, ImageLoadListener paramImageLoadListener)
  {
    loadImage(paramImageView, paramString, 0, 0, paramImageLoadListener);
  }

  public void measureSize(ImageView paramImageView, int[] paramArrayOfInt)
  {
    DisplayMetrics localDisplayMetrics = paramImageView.getContext().getResources().getDisplayMetrics();
    ViewGroup.LayoutParams localLayoutParams = paramImageView.getLayoutParams();
    int i;
    int k;
    if (localLayoutParams.width == -2)
    {
      j = 0;
      i = j;
      if (j <= 0)
        i = localLayoutParams.width;
      k = i;
      if (i <= 0)
        k = localDisplayMetrics.widthPixels;
      if (localLayoutParams.height != -2)
        break label120;
    }
    label120: for (int j = 0; ; j = paramImageView.getHeight())
    {
      i = j;
      if (j <= 0)
        i = localLayoutParams.height;
      j = i;
      if (i <= 0)
        j = localDisplayMetrics.heightPixels;
      paramArrayOfInt[0] = k;
      paramArrayOfInt[1] = j;
      return;
      j = paramImageView.getWidth();
      break;
    }
  }

  public Bitmap readImage(String paramString, int paramInt1, int paramInt2)
  {
    Object localObject = new File(paramString);
    if (((File)localObject).exists())
      try
      {
        BufferedInputStream localBufferedInputStream = new BufferedInputStream(new FileInputStream((File)localObject));
        localObject = new BitmapFactory.Options();
        ((BitmapFactory.Options)localObject).inJustDecodeBounds = true;
        BitmapFactory.decodeStream(localBufferedInputStream, null, (BitmapFactory.Options)localObject);
        localBufferedInputStream.close();
        int i = 0;
        while (true)
        {
          if ((((BitmapFactory.Options)localObject).outWidth >> i <= paramInt1) && (((BitmapFactory.Options)localObject).outHeight >> i <= paramInt2))
          {
            localBufferedInputStream = new BufferedInputStream(new FileInputStream(new File(paramString)));
            ((BitmapFactory.Options)localObject).inSampleSize = ((int)Math.pow(2.0D, i));
            ((BitmapFactory.Options)localObject).inJustDecodeBounds = false;
            localObject = BitmapFactory.decodeStream(localBufferedInputStream, null, (BitmapFactory.Options)localObject);
            localBufferedInputStream.close();
            return localObject;
          }
          i += 1;
        }
      }
      catch (IOException localIOException)
      {
        Logger.e("This path does not exist" + paramString, new Object[] { localIOException });
      }
    return null;
  }

  @SuppressLint({"NewApi"})
  public void setDefaultImage(Context paramContext, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      this.mDefaultDrawable = paramContext.getDrawable(paramInt);
      return;
    }
    this.mDefaultDrawable = paramContext.getResources().getDrawable(paramInt);
  }

  public void setDefaultImageColor(int paramInt)
  {
    this.mDefaultDrawable = new ColorDrawable(paramInt);
  }

  public static abstract interface ImageLoadListener
  {
    public abstract void onLoadFalied(ImageView paramImageView, String paramString);

    public abstract void onLoadSucceed(ImageView paramImageView, Bitmap paramBitmap, String paramString);
  }

  private class ImgBeanHolder
    implements Runnable
  {
    Bitmap bitmap;
    ImageLocalLoader.ImageLoadListener imageLoadListener;
    String imagePath;
    ImageView imageView;

    private ImgBeanHolder()
    {
    }

    public void run()
    {
      if (this.imageLoadListener == null)
      {
        if (this.imagePath.equals(this.imageView.getTag()))
          this.imageView.setImageBitmap(this.bitmap);
        return;
      }
      this.imageLoadListener.onLoadSucceed(this.imageView, this.bitmap, this.imagePath);
    }
  }

  private class TaskThread
    implements Runnable
  {
    private int height;
    private ImageLocalLoader.ImageLoadListener imageLoadListener;
    private String mImagePath;
    private ImageView mImageView;
    private int width;

    TaskThread(ImageView paramString, String paramInt1, int paramInt2, int paramImageLoadListener, ImageLocalLoader.ImageLoadListener arg6)
    {
      this.mImagePath = paramInt1;
      this.mImageView = paramString;
      this.width = paramInt2;
      this.height = paramImageLoadListener;
      Object localObject;
      this.imageLoadListener = localObject;
    }

    public void run()
    {
      if ((this.width != 0) && (this.height != 0));
      for (Object localObject = ImageLocalLoader.this.readImage(this.mImagePath, this.width, this.height); ; localObject = ImageLocalLoader.this.readImage(this.mImagePath, localObject[0], localObject[1]))
      {
        ImageLocalLoader.this.addImageToCache(this.mImagePath + this.width + this.height, (Bitmap)localObject);
        localObject = new ImageLocalLoader.ImgBeanHolder(ImageLocalLoader.this, null);
        ((ImageLocalLoader.ImgBeanHolder)localObject).bitmap = ImageLocalLoader.this.getImageFromCache(this.mImagePath + this.width + this.height);
        ((ImageLocalLoader.ImgBeanHolder)localObject).imageView = this.mImageView;
        ((ImageLocalLoader.ImgBeanHolder)localObject).imagePath = this.mImagePath;
        ((ImageLocalLoader.ImgBeanHolder)localObject).imageLoadListener = this.imageLoadListener;
        ImageLocalLoader.this.getPostHandler().post((Runnable)localObject);
        return;
        localObject = new int[2];
        ImageLocalLoader.this.measureSize(this.mImageView, (int[])localObject);
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.tools.ImageLocalLoader
 * JD-Core Version:    0.6.2
 */