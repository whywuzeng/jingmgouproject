package net.tsz.afinal.bitmap.core;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import java.io.FileDescriptor;

public class BitmapDecoder
{
  private static int calculateInSampleSize(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
  {
    int j = paramOptions.outHeight;
    int k = paramOptions.outWidth;
    int i = 1;
    float f1;
    float f2;
    if ((j > paramInt2) || (k > paramInt1))
    {
      if (k <= j)
        break label77;
      i = Math.round(j / paramInt2);
      f1 = k * j;
      f2 = paramInt1 * paramInt2 * 2;
    }
    while (true)
    {
      if (f1 / (i * i) <= f2)
      {
        return i;
        label77: i = Math.round(k / paramInt1);
        break;
      }
      i += 1;
    }
  }

  public static Bitmap decodeSampledBitmapFromByteArray(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    localOptions.inPurgeable = true;
    BitmapFactory.decodeByteArray(paramArrayOfByte, paramInt1, paramInt2, localOptions);
    localOptions.inSampleSize = calculateInSampleSize(localOptions, paramInt3, paramInt4);
    localOptions.inJustDecodeBounds = false;
    return BitmapFactory.decodeByteArray(paramArrayOfByte, paramInt1, paramInt2, localOptions);
  }

  public static Bitmap decodeSampledBitmapFromDescriptor(FileDescriptor paramFileDescriptor, int paramInt1, int paramInt2)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    localOptions.inPurgeable = true;
    BitmapFactory.decodeFileDescriptor(paramFileDescriptor, null, localOptions);
    localOptions.inSampleSize = calculateInSampleSize(localOptions, paramInt1, paramInt2);
    localOptions.inJustDecodeBounds = false;
    try
    {
      paramFileDescriptor = BitmapFactory.decodeFileDescriptor(paramFileDescriptor, null, localOptions);
      return paramFileDescriptor;
    }
    catch (OutOfMemoryError paramFileDescriptor)
    {
      paramFileDescriptor.printStackTrace();
    }
    return null;
  }

  public static Bitmap decodeSampledBitmapFromResource(Resources paramResources, int paramInt1, int paramInt2, int paramInt3)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    localOptions.inPurgeable = true;
    BitmapFactory.decodeResource(paramResources, paramInt1, localOptions);
    localOptions.inSampleSize = calculateInSampleSize(localOptions, paramInt2, paramInt3);
    localOptions.inJustDecodeBounds = false;
    try
    {
      paramResources = BitmapFactory.decodeResource(paramResources, paramInt1, localOptions);
      return paramResources;
    }
    catch (OutOfMemoryError paramResources)
    {
      paramResources.printStackTrace();
    }
    return null;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.bitmap.core.BitmapDecoder
 * JD-Core Version:    0.6.2
 */