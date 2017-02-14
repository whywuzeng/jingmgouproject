package com.ismartgo.app.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration.Builder;
import com.nostra13.universalimageloader.utils.StorageUtils;
import java.io.File;

public class ImageUrlLoader
{
  public static ImageLoader imageLoader = null;
  private File cacheDir;
  ImageLoaderConfiguration config;
  Context context;
  private DisplayImageOptions options;

  public ImageUrlLoader(Context paramContext)
  {
    this.context = paramContext;
    if (imageLoader == null)
    {
      imageLoader = ImageLoader.getInstance();
      this.cacheDir = StorageUtils.getCacheDirectory(paramContext);
      if (!this.cacheDir.exists())
        this.cacheDir.mkdirs();
      if (this.config == null)
      {
        this.config = new ImageLoaderConfiguration.Builder(paramContext).discCache(new UnlimitedDiscCache(this.cacheDir)).build();
        imageLoader.init(this.config);
      }
    }
  }

  public Bitmap getBitmap(String paramString)
  {
    return imageLoader.loadImageSync(paramString);
  }

  public void showPic(String paramString, ImageView paramImageView)
  {
    this.options = new DisplayImageOptions.Builder().showImageForEmptyUri(2130837700).showImageOnFail(2130837700).cacheInMemory(true).cacheOnDisc(true).build();
    imageLoader.displayImage(paramString, paramImageView, this.options);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.tools.ImageUrlLoader
 * JD-Core Version:    0.6.2
 */