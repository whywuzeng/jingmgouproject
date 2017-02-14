package com.nostra13.universalimageloader.core.decode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.ExifInterface;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.nostra13.universalimageloader.utils.ImageSizeUtils;
import com.nostra13.universalimageloader.utils.IoUtils;
import com.nostra13.universalimageloader.utils.L;
import java.io.IOException;
import java.io.InputStream;

public class BaseImageDecoder
  implements ImageDecoder
{
  protected static final String ERROR_CANT_DECODE_IMAGE = "Image can't be decoded [%s]";
  protected static final String LOG_FLIP_IMAGE = "Flip image horizontally [%s]";
  protected static final String LOG_ROTATE_IMAGE = "Rotate image on %1$d° [%2$s]";
  protected static final String LOG_SCALE_IMAGE = "Scale subsampled image (%1$s) to %2$s (scale = %3$.5f) [%4$s]";
  protected static final String LOG_SUBSAMPLE_IMAGE = "Subsample original image (%1$s) to %2$s (scale = %3$d) [%4$s]";
  protected final boolean loggingEnabled;

  public BaseImageDecoder(boolean paramBoolean)
  {
    this.loggingEnabled = paramBoolean;
  }

  private boolean canDefineExifParams(String paramString1, String paramString2)
  {
    return ("image/jpeg".equalsIgnoreCase(paramString2)) && (ImageDownloader.Scheme.ofUri(paramString1) == ImageDownloader.Scheme.FILE);
  }

  protected Bitmap considerExactScaleAndOrientatiton(Bitmap paramBitmap, ImageDecodingInfo paramImageDecodingInfo, int paramInt, boolean paramBoolean)
  {
    Matrix localMatrix = new Matrix();
    ImageScaleType localImageScaleType = paramImageDecodingInfo.getImageScaleType();
    ImageSize localImageSize1;
    ImageSize localImageSize2;
    ViewScaleType localViewScaleType;
    if ((localImageScaleType == ImageScaleType.EXACTLY) || (localImageScaleType == ImageScaleType.EXACTLY_STRETCHED))
    {
      localImageSize1 = new ImageSize(paramBitmap.getWidth(), paramBitmap.getHeight(), paramInt);
      localImageSize2 = paramImageDecodingInfo.getTargetSize();
      localViewScaleType = paramImageDecodingInfo.getViewScaleType();
      if (localImageScaleType != ImageScaleType.EXACTLY_STRETCHED)
        break label257;
    }
    label257: for (boolean bool = true; ; bool = false)
    {
      float f = ImageSizeUtils.computeImageScale(localImageSize1, localImageSize2, localViewScaleType, bool);
      if (Float.compare(f, 1.0F) != 0)
      {
        localMatrix.setScale(f, f);
        if (this.loggingEnabled)
          L.d("Scale subsampled image (%1$s) to %2$s (scale = %3$.5f) [%4$s]", new Object[] { localImageSize1, localImageSize1.scale(f), Float.valueOf(f), paramImageDecodingInfo.getImageKey() });
      }
      if (paramBoolean)
      {
        localMatrix.postScale(-1.0F, 1.0F);
        if (this.loggingEnabled)
          L.d("Flip image horizontally [%s]", new Object[] { paramImageDecodingInfo.getImageKey() });
      }
      if (paramInt != 0)
      {
        localMatrix.postRotate(paramInt);
        if (this.loggingEnabled)
          L.d("Rotate image on %1$d° [%2$s]", new Object[] { Integer.valueOf(paramInt), paramImageDecodingInfo.getImageKey() });
      }
      paramImageDecodingInfo = Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), localMatrix, true);
      if (paramImageDecodingInfo != paramBitmap)
        paramBitmap.recycle();
      return paramImageDecodingInfo;
    }
  }

  public Bitmap decode(ImageDecodingInfo paramImageDecodingInfo)
    throws IOException
  {
    InputStream localInputStream2 = getImageStream(paramImageDecodingInfo);
    InputStream localInputStream1 = localInputStream2;
    ImageFileInfo localImageFileInfo;
    Bitmap localBitmap;
    try
    {
      localImageFileInfo = defineImageSizeAndRotation(localInputStream2, paramImageDecodingInfo);
      localInputStream1 = localInputStream2;
      localInputStream2 = resetStream(localInputStream2, paramImageDecodingInfo);
      localInputStream1 = localInputStream2;
      localBitmap = BitmapFactory.decodeStream(localInputStream2, null, prepareDecodingOptions(localImageFileInfo.imageSize, paramImageDecodingInfo));
      IoUtils.closeSilently(localInputStream2);
      if (localBitmap == null)
      {
        L.e("Image can't be decoded [%s]", new Object[] { paramImageDecodingInfo.getImageKey() });
        return localBitmap;
      }
    }
    finally
    {
      IoUtils.closeSilently(localInputStream1);
    }
    return considerExactScaleAndOrientatiton(localBitmap, paramImageDecodingInfo, localImageFileInfo.exif.rotation, localImageFileInfo.exif.flipHorizontal);
  }

  protected ExifInfo defineExifOrientation(String paramString)
  {
    int i = 0;
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool4 = false;
    boolean bool5 = false;
    try
    {
      int j = new ExifInterface(ImageDownloader.Scheme.FILE.crop(paramString)).getAttributeInt("Orientation", 1);
      switch (j)
      {
      default:
        bool1 = bool5;
      case 2:
      case 1:
      case 7:
      case 6:
      case 4:
      case 3:
      case 5:
      case 8:
      }
      while (true)
      {
        return new ExifInfo(i, bool1);
        bool1 = true;
        i = 0;
        continue;
        bool2 = true;
        i = 90;
        bool1 = bool2;
        continue;
        bool3 = true;
        i = 180;
        bool1 = bool3;
        continue;
        bool4 = true;
        i = 270;
        bool1 = bool4;
      }
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        L.w("Can't read EXIF tags from file [%s]", new Object[] { paramString });
        bool1 = bool5;
      }
    }
  }

  protected ImageFileInfo defineImageSizeAndRotation(InputStream paramInputStream, ImageDecodingInfo paramImageDecodingInfo)
    throws IOException
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeStream(paramInputStream, null, localOptions);
    paramInputStream = paramImageDecodingInfo.getImageUri();
    if ((paramImageDecodingInfo.shouldConsiderExifParams()) && (canDefineExifParams(paramInputStream, localOptions.outMimeType)));
    for (paramInputStream = defineExifOrientation(paramInputStream); ; paramInputStream = new ExifInfo())
      return new ImageFileInfo(new ImageSize(localOptions.outWidth, localOptions.outHeight, paramInputStream.rotation), paramInputStream);
  }

  protected InputStream getImageStream(ImageDecodingInfo paramImageDecodingInfo)
    throws IOException
  {
    return paramImageDecodingInfo.getDownloader().getStream(paramImageDecodingInfo.getImageUri(), paramImageDecodingInfo.getExtraForDownloader());
  }

  protected BitmapFactory.Options prepareDecodingOptions(ImageSize paramImageSize, ImageDecodingInfo paramImageDecodingInfo)
  {
    ImageScaleType localImageScaleType = paramImageDecodingInfo.getImageScaleType();
    if (localImageScaleType == ImageScaleType.NONE);
    for (int i = 1; ; i = ImageSizeUtils.computeMinImageSampleSize(paramImageSize))
    {
      if ((i > 1) && (this.loggingEnabled))
        L.d("Subsample original image (%1$s) to %2$s (scale = %3$d) [%4$s]", new Object[] { paramImageSize, paramImageSize.scaleDown(i), Integer.valueOf(i), paramImageDecodingInfo.getImageKey() });
      paramImageSize = paramImageDecodingInfo.getDecodingOptions();
      paramImageSize.inSampleSize = i;
      return paramImageSize;
      if (localImageScaleType != ImageScaleType.NONE_SAFE)
        break;
    }
    ImageSize localImageSize = paramImageDecodingInfo.getTargetSize();
    if (localImageScaleType == ImageScaleType.IN_SAMPLE_POWER_OF_2);
    for (boolean bool = true; ; bool = false)
    {
      i = ImageSizeUtils.computeImageSampleSize(paramImageSize, localImageSize, paramImageDecodingInfo.getViewScaleType(), bool);
      break;
    }
  }

  protected InputStream resetStream(InputStream paramInputStream, ImageDecodingInfo paramImageDecodingInfo)
    throws IOException
  {
    try
    {
      paramInputStream.reset();
      return paramInputStream;
    }
    catch (IOException localIOException)
    {
      IoUtils.closeSilently(paramInputStream);
    }
    return getImageStream(paramImageDecodingInfo);
  }

  protected static class ExifInfo
  {
    public final boolean flipHorizontal;
    public final int rotation;

    protected ExifInfo()
    {
      this.rotation = 0;
      this.flipHorizontal = false;
    }

    protected ExifInfo(int paramInt, boolean paramBoolean)
    {
      this.rotation = paramInt;
      this.flipHorizontal = paramBoolean;
    }
  }

  protected static class ImageFileInfo
  {
    public final BaseImageDecoder.ExifInfo exif;
    public final ImageSize imageSize;

    protected ImageFileInfo(ImageSize paramImageSize, BaseImageDecoder.ExifInfo paramExifInfo)
    {
      this.imageSize = paramImageSize;
      this.exif = paramExifInfo;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.core.decode.BaseImageDecoder
 * JD-Core Version:    0.6.2
 */