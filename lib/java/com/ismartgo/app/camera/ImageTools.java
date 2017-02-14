package com.ismartgo.app.camera;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;

public final class ImageTools
{
  private static int computeInitialSampleSize(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
  {
    double d1 = paramOptions.outWidth;
    double d2 = paramOptions.outHeight;
    int i;
    int j;
    if (paramInt2 == -1)
    {
      i = 1;
      if (paramInt1 != -1)
        break label60;
      j = 150;
      label31: if (j >= i)
        break label84;
    }
    label60: label84: 
    do
    {
      return i;
      i = (int)Math.ceil(Math.sqrt(d1 * d2 / paramInt2));
      break;
      j = (int)Math.min(Math.floor(d1 / paramInt1), Math.floor(d2 / paramInt1));
      break label31;
      if ((paramInt2 == -1) && (paramInt1 == -1))
        return 1;
    }
    while (paramInt1 == -1);
    return j;
  }

  public static int computeSampleSize(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
  {
    paramInt2 = computeInitialSampleSize(paramOptions, paramInt1, paramInt2);
    if (paramInt2 <= 8)
    {
      paramInt1 = 1;
      while (true)
      {
        if (paramInt1 >= paramInt2)
          return paramInt1;
        paramInt1 <<= 1;
      }
    }
    return (paramInt2 + 7) / 8 * 8;
  }

  public static String getAbsoluteImagePath(Uri paramUri, Activity paramActivity)
  {
    paramUri = paramActivity.managedQuery(paramUri, new String[] { "_data" }, null, null, null);
    int i = paramUri.getColumnIndexOrThrow("_data");
    paramUri.moveToFirst();
    return paramUri.getString(i);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.camera.ImageTools
 * JD-Core Version:    0.6.2
 */