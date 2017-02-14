package com.example.android.bitmapfun.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import com.ismartgo.app.tools.ImgLoader;
import java.lang.ref.WeakReference;

public abstract class ImageWorker
{
  private static final int FADE_IN_TIME = 200;
  private static final String TAG = "ImageWorker";
  protected Context mContext;
  private boolean mExitTasksEarly = false;
  private boolean mFadeInBitmap = true;
  private ImageCache mImageCache;
  protected ImageWorkerAdapter mImageWorkerAdapter;
  private Bitmap mLoadingBitmap;

  protected ImageWorker(Context paramContext)
  {
    this.mContext = paramContext;
  }

  public static boolean cancelPotentialWork(Object paramObject, ImageView paramImageView)
  {
    paramImageView = getBitmapWorkerTask(paramImageView);
    if (paramImageView != null)
    {
      Object localObject = paramImageView.data;
      if ((localObject == null) || (!localObject.equals(paramObject)))
      {
        paramImageView.cancel(true);
        Log.d("ImageWorker", "cancelPotentialWork - cancelled work for " + paramObject);
      }
    }
    else
    {
      return true;
    }
    return false;
  }

  public static void cancelWork(ImageView paramImageView)
  {
    paramImageView = getBitmapWorkerTask(paramImageView);
    if (paramImageView != null)
    {
      paramImageView.cancel(true);
      paramImageView = paramImageView.data;
      Log.d("ImageWorker", "cancelWork - cancelled work for " + paramImageView);
    }
  }

  private static BitmapWorkerTask getBitmapWorkerTask(ImageView paramImageView)
  {
    if (paramImageView != null)
    {
      paramImageView = paramImageView.getDrawable();
      if ((paramImageView instanceof AsyncDrawable))
        return ((AsyncDrawable)paramImageView).getBitmapWorkerTask();
    }
    return null;
  }

  private void setImageBitmap(ImageView paramImageView, Bitmap paramBitmap)
  {
    if (this.mFadeInBitmap)
    {
      paramBitmap = new TransitionDrawable(new Drawable[] { new ColorDrawable(17170445), new BitmapDrawable(this.mContext.getResources(), paramBitmap) });
      paramImageView.setBackgroundDrawable(new BitmapDrawable(this.mContext.getResources(), this.mLoadingBitmap));
      paramImageView.setImageDrawable(paramBitmap);
      paramBitmap.startTransition(200);
      return;
    }
    paramImageView.setImageBitmap(paramBitmap);
  }

  public ImageWorkerAdapter getAdapter()
  {
    return this.mImageWorkerAdapter;
  }

  public ImageCache getImageCache()
  {
    return this.mImageCache;
  }

  public void loadImage(int paramInt, ImageView paramImageView)
  {
    if (this.mImageWorkerAdapter != null)
    {
      loadImage(this.mImageWorkerAdapter.getItem(paramInt), paramImageView);
      return;
    }
    throw new NullPointerException("Data not set, must call setAdapter() first.");
  }

  public void loadImage(Object paramObject, ImageView paramImageView)
  {
    Object localObject = null;
    if (this.mImageCache != null)
      localObject = this.mImageCache.getBitmapFromMemCache(String.valueOf(paramObject));
    if (localObject != null)
      paramImageView.setImageBitmap((Bitmap)localObject);
    while (!cancelPotentialWork(paramObject, paramImageView))
      return;
    localObject = new BitmapWorkerTask(paramImageView);
    Resources localResources1 = this.mContext.getResources();
    Resources localResources2 = this.mContext.getResources();
    new ImgLoader(this.mContext);
    paramImageView.setImageDrawable(new AsyncDrawable(localResources1, BitmapFactory.decodeResource(localResources2, ImgLoader.randImageRes()), (BitmapWorkerTask)localObject));
    ((BitmapWorkerTask)localObject).execute(new Object[] { paramObject });
  }

  protected abstract Bitmap processBitmap(Object paramObject);

  public void setAdapter(ImageWorkerAdapter paramImageWorkerAdapter)
  {
    this.mImageWorkerAdapter = paramImageWorkerAdapter;
  }

  public void setExitTasksEarly(boolean paramBoolean)
  {
    this.mExitTasksEarly = paramBoolean;
  }

  public void setImageCache(ImageCache paramImageCache)
  {
    this.mImageCache = paramImageCache;
  }

  public void setImageFadeIn(boolean paramBoolean)
  {
    this.mFadeInBitmap = paramBoolean;
  }

  public void setLoadingImage(int paramInt)
  {
    this.mLoadingBitmap = BitmapFactory.decodeResource(this.mContext.getResources(), paramInt);
  }

  public void setLoadingImage(Bitmap paramBitmap)
  {
    this.mLoadingBitmap = paramBitmap;
  }

  private static class AsyncDrawable extends BitmapDrawable
  {
    private final WeakReference<ImageWorker.BitmapWorkerTask> bitmapWorkerTaskReference;

    public AsyncDrawable(Resources paramResources, Bitmap paramBitmap, ImageWorker.BitmapWorkerTask paramBitmapWorkerTask)
    {
      super(paramBitmap);
      this.bitmapWorkerTaskReference = new WeakReference(paramBitmapWorkerTask);
    }

    public ImageWorker.BitmapWorkerTask getBitmapWorkerTask()
    {
      return (ImageWorker.BitmapWorkerTask)this.bitmapWorkerTaskReference.get();
    }
  }

  private class BitmapWorkerTask extends AsyncTask<Object, Void, Bitmap>
  {
    private Object data;
    private final WeakReference<ImageView> imageViewReference;

    public BitmapWorkerTask(ImageView arg2)
    {
      Object localObject;
      this.imageViewReference = new WeakReference(localObject);
    }

    private ImageView getAttachedImageView()
    {
      ImageView localImageView = (ImageView)this.imageViewReference.get();
      if (this == ImageWorker.getBitmapWorkerTask(localImageView))
        return localImageView;
      return null;
    }

    protected Bitmap doInBackground(Object[] paramArrayOfObject)
    {
      this.data = paramArrayOfObject[0];
      String str = String.valueOf(this.data);
      Object localObject2 = null;
      Object localObject1 = localObject2;
      if (ImageWorker.this.mImageCache != null)
      {
        localObject1 = localObject2;
        if (!isCancelled())
        {
          localObject1 = localObject2;
          if (getAttachedImageView() != null)
          {
            localObject1 = localObject2;
            if (!ImageWorker.this.mExitTasksEarly)
              localObject1 = ImageWorker.this.mImageCache.getBitmapFromDiskCache(str);
          }
        }
      }
      localObject2 = localObject1;
      if (localObject1 == null)
      {
        localObject2 = localObject1;
        if (!isCancelled())
        {
          localObject2 = localObject1;
          if (getAttachedImageView() != null)
          {
            localObject2 = localObject1;
            if (!ImageWorker.this.mExitTasksEarly)
              localObject2 = ImageWorker.this.processBitmap(paramArrayOfObject[0]);
          }
        }
      }
      if ((localObject2 != null) && (ImageWorker.this.mImageCache != null))
        ImageWorker.this.mImageCache.addBitmapToCache(str, (Bitmap)localObject2);
      return localObject2;
    }

    protected void onPostExecute(Bitmap paramBitmap)
    {
      if ((isCancelled()) || (ImageWorker.this.mExitTasksEarly))
        paramBitmap = null;
      ImageView localImageView = getAttachedImageView();
      if ((paramBitmap != null) && (localImageView != null))
        ImageWorker.this.setImageBitmap(localImageView, paramBitmap);
    }
  }

  public static abstract class ImageWorkerAdapter
  {
    public abstract Object getItem(int paramInt);

    public abstract int getSize();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.example.android.bitmapfun.util.ImageWorker
 * JD-Core Version:    0.6.2
 */