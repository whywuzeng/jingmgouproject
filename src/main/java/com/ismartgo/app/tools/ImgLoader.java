package com.ismartgo.app.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.widget.ImageView;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration.Builder;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import java.io.File;
import java.util.Random;

public class ImgLoader
{
  private static File cacheDir;
  private static Context context;
  private static ImageLoader imageLoader;
  private static ImgLoader mImgLoader;
  private static DisplayImageOptions options;
  public static int[] randomColors = { 2130837622, 2130837623, 2130837640, 2130837641, 2130837643, 2130837644, 2130837645, 2130837646, 2130837624, 2130837627, 2130837628, 2130837630, 2130837633, 2130837635, 2130837636, 2130837639 };
  public static String[] randomColors1 = { "#eaa383", "#e1c233", "#1c1879", "#3d5e3f", "#fad6b6", "#959d90", "#5e6963", "#b1a599", "#f1ebd3", "#dbe4e3", "#a8c9ae", "#aa9373", "#eee4d8", "#afa65b", "#6f7957", "#ceb191", "#afafaf", "#d9cfcd" };

  public ImgLoader()
  {
  }

  public ImgLoader(Context paramContext)
  {
    context = paramContext;
    if (imageLoader == null)
    {
      imageLoader = ImageLoader.getInstance();
      cacheDir = StorageUtils.getCacheDirectory(paramContext);
      if (!cacheDir.exists())
        cacheDir.mkdirs();
      paramContext = new ImageLoaderConfiguration.Builder(paramContext).threadPoolSize(5).discCache(new UnlimitedDiscCache(cacheDir)).memoryCache(new WeakMemoryCache()).build();
      imageLoader.init(paramContext);
    }
  }

  public static ImgLoader getInstance(Context paramContext)
  {
    context = paramContext;
    if (mImgLoader == null)
    {
      mImgLoader = new ImgLoader();
      initImageLoader();
    }
    return mImgLoader;
  }

  private static void initImageLoader()
  {
    if (imageLoader == null)
    {
      imageLoader = ImageLoader.getInstance();
      cacheDir = StorageUtils.getCacheDirectory(context);
      if (!cacheDir.exists())
        cacheDir.mkdirs();
      ImageLoaderConfiguration localImageLoaderConfiguration = new ImageLoaderConfiguration.Builder(context).threadPoolSize(5).discCache(new UnlimitedDiscCache(cacheDir)).memoryCache(new WeakMemoryCache()).build();
      imageLoader.init(localImageLoaderConfiguration);
    }
  }

  public static String randImage()
  {
    Random localRandom = new Random();
    return randomColors1[localRandom.nextInt(randomColors1.length)];
  }

  public static int randImageRes()
  {
    Random localRandom = new Random();
    return randomColors[localRandom.nextInt(randomColors.length)];
  }

  public Bitmap getBitmap(String paramString)
  {
    return imageLoader.loadImageSync(paramString);
  }

  public void showPic(String paramString, ImageView paramImageView, boolean paramBoolean)
  {
    if (paramBoolean);
    for (options = new DisplayImageOptions.Builder().showStubImage(2130837653).showImageForEmptyUri(randImageRes()).showImageOnFail(randImageRes()).cacheInMemory(true).cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565).imageScaleType(ImageScaleType.EXACTLY).build(); ; options = new DisplayImageOptions.Builder().showStubImage(randImageRes()).showImageForEmptyUri(randImageRes()).showImageOnFail(randImageRes()).cacheInMemory(true).cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565).imageScaleType(ImageScaleType.EXACTLY).build())
    {
      imageLoader.displayImage(paramString, paramImageView, options);
      return;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.tools.ImgLoader
 * JD-Core Version:    0.6.2
 */