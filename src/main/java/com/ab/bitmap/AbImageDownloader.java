package com.ab.bitmap;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.view.View;
import android.widget.ImageView;
import com.ab.global.AbAppData;
import com.ab.util.AbImageUtil;
import com.ab.util.AbStrUtil;

public class AbImageDownloader
{
  private static final boolean D = AbAppData.DEBUG;
  private static String TAG = "AbImageDownloader";
  private static Context context = null;
  private boolean animation;
  private Drawable errorImage;
  private int height;
  private Drawable loadingImage;
  private View loadingView;
  private AbImageDownloadPool mAbImageDownloadPool = null;
  private Drawable noImage;
  private int type = 2;
  private int width;

  public AbImageDownloader(Context paramContext)
  {
    context = paramContext;
    this.mAbImageDownloadPool = AbImageDownloadPool.getInstance();
  }

  public void display(final ImageView paramImageView, String paramString)
  {
    if (AbStrUtil.isEmpty(paramString))
    {
      if (this.noImage != null)
      {
        if (this.loadingView != null)
        {
          this.loadingView.setVisibility(4);
          paramImageView.setVisibility(0);
        }
        paramImageView.setImageDrawable(this.noImage);
      }
      return;
    }
    AbImageDownloadItem localAbImageDownloadItem = new AbImageDownloadItem();
    localAbImageDownloadItem.width = this.width;
    localAbImageDownloadItem.height = this.height;
    localAbImageDownloadItem.type = this.type;
    localAbImageDownloadItem.imageUrl = paramString;
    localAbImageDownloadItem.bitmap = AbImageCache.getBitmapFromCache(AbImageCache.getCacheKey(localAbImageDownloadItem.imageUrl, localAbImageDownloadItem.width, localAbImageDownloadItem.height, localAbImageDownloadItem.type));
    if (localAbImageDownloadItem.bitmap == null)
    {
      if (this.loadingView != null)
      {
        this.loadingView.setVisibility(0);
        paramImageView.setVisibility(4);
      }
      while (true)
      {
        localAbImageDownloadItem.listener = new AbImageDownloadListener()
        {
          public void update(Bitmap paramAnonymousBitmap, String paramAnonymousString)
          {
            if (AbImageDownloader.this.loadingView != null)
            {
              AbImageDownloader.this.loadingView.setVisibility(4);
              paramImageView.setVisibility(0);
            }
            if (paramAnonymousBitmap != null)
              if (AbImageDownloader.this.animation)
              {
                paramAnonymousBitmap = AbImageUtil.bitmapToTransitionDrawable(paramAnonymousBitmap);
                paramImageView.setImageDrawable(paramAnonymousBitmap);
                paramAnonymousBitmap.startTransition(200);
              }
            while (AbImageDownloader.this.errorImage == null)
            {
              return;
              paramImageView.setImageBitmap(paramAnonymousBitmap);
              return;
            }
            if (AbImageDownloader.this.animation)
            {
              paramAnonymousBitmap = AbImageUtil.drawableToTransitionDrawable(AbImageDownloader.this.errorImage);
              paramImageView.setImageDrawable(paramAnonymousBitmap);
              paramAnonymousBitmap.startTransition(200);
              return;
            }
            paramImageView.setImageDrawable(AbImageDownloader.this.errorImage);
          }
        };
        this.mAbImageDownloadPool.download(localAbImageDownloadItem);
        return;
        if (this.loadingImage != null)
          if (this.animation)
          {
            paramString = AbImageUtil.drawableToTransitionDrawable(this.loadingImage);
            paramImageView.setImageDrawable(paramString);
            paramString.startTransition(200);
          }
          else
          {
            paramImageView.setImageDrawable(this.loadingImage);
          }
      }
    }
    if (this.loadingView != null)
    {
      this.loadingView.setVisibility(4);
      paramImageView.setVisibility(0);
    }
    paramImageView.setImageBitmap(localAbImageDownloadItem.bitmap);
  }

  public int getHeight()
  {
    return this.height;
  }

  public int getType()
  {
    return this.type;
  }

  public int getWidth()
  {
    return this.width;
  }

  public void setAnimation(boolean paramBoolean)
  {
    this.animation = paramBoolean;
  }

  public void setErrorImage(int paramInt)
  {
    this.errorImage = context.getResources().getDrawable(paramInt);
  }

  public void setHeight(int paramInt)
  {
    this.height = paramInt;
  }

  public void setLoadingImage(int paramInt)
  {
    this.loadingImage = context.getResources().getDrawable(paramInt);
  }

  public void setLoadingView(View paramView)
  {
    this.loadingView = paramView;
  }

  public void setNoImage(int paramInt)
  {
    this.noImage = context.getResources().getDrawable(paramInt);
  }

  public void setType(int paramInt)
  {
    this.type = paramInt;
  }

  public void setWidth(int paramInt)
  {
    this.width = paramInt;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.bitmap.AbImageDownloader
 * JD-Core Version:    0.6.2
 */