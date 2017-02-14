package com.ismartgo.app.grid.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Environment;
import android.util.DisplayMetrics;
import java.io.File;

public class CameraUtils
{
  public static Rect calculateTapArea(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    paramInt1 = (int)(paramInt1 * paramFloat1);
    paramInt2 = (int)(paramInt2 * paramFloat1);
    int j = (paramInt3 + paramInt4) / 2;
    int i = (paramInt5 + paramInt6) / 2;
    double d1 = (paramInt4 - paramInt3) / 2000.0D;
    double d2 = (paramInt6 - paramInt5) / 2000.0D;
    paramInt3 = clamp((int)((paramFloat2 - paramInt1 / 2 - j) / d1), -1000, 1000);
    paramInt4 = clamp((int)((paramFloat3 - paramInt2 / 2 - i) / d2), -1000, 1000);
    return new Rect(paramInt3, paramInt4, clamp((int)(paramInt3 + paramInt1 / d1), -1000, 1000), clamp((int)(paramInt4 + paramInt2 / d2), -1000, 1000));
  }

  public static boolean checkCameraHardware(Context paramContext)
  {
    return (paramContext != null) && (paramContext.getPackageManager().hasSystemFeature("android.hardware.camera"));
  }

  public static int clamp(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 > paramInt3)
      return paramInt3;
    if (paramInt1 < paramInt2)
      return paramInt2;
    return paramInt1;
  }

  public static String getDBDir(Context paramContext)
  {
    String str = null;
    if ("mounted".equals(Environment.getExternalStorageState()))
    {
      str = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "bbk" + File.separator + "cloudteacher" + File.separator + "db";
      localObject = paramContext.getExternalCacheDir();
      if (localObject != null)
        str = ((File)localObject).getPath();
    }
    Object localObject = str;
    if (str == null)
    {
      paramContext = paramContext.getCacheDir();
      localObject = str;
      if (paramContext != null)
      {
        localObject = str;
        if (paramContext.exists())
          localObject = paramContext.getPath();
      }
    }
    return localObject;
  }

  public static String getDataColumn(Context paramContext, Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    Context localContext = null;
    try
    {
      paramContext = paramContext.getContentResolver().query(paramUri, new String[] { "_data" }, paramString, paramArrayOfString, null);
      if (paramContext != null)
      {
        localContext = paramContext;
        if (paramContext.moveToFirst())
        {
          localContext = paramContext;
          paramUri = paramContext.getString(paramContext.getColumnIndexOrThrow("_data"));
          return paramUri;
        }
      }
    }
    finally
    {
      if (localContext != null)
        localContext.close();
    }
    if (paramContext != null)
      paramContext.close();
    return null;
  }

  public static final int getHeightInPx(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().heightPixels;
  }

  public static DisplayMetrics getScreenWH(Context paramContext)
  {
    new DisplayMetrics();
    return paramContext.getResources().getDisplayMetrics();
  }

  public static final int getWidthInPx(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().widthPixels;
  }

  public static boolean isDownloadsDocument(Uri paramUri)
  {
    return "com.android.providers.downloads.documents".equals(paramUri.getAuthority());
  }

  public static boolean isExternalStorageDocument(Uri paramUri)
  {
    return "com.android.externalstorage.documents".equals(paramUri.getAuthority());
  }

  public static boolean isGooglePhotosUri(Uri paramUri)
  {
    return "com.google.android.apps.photos.content".equals(paramUri.getAuthority());
  }

  public static boolean isMediaDocument(Uri paramUri)
  {
    return "com.android.providers.media.documents".equals(paramUri.getAuthority());
  }

  public static Bitmap rotate(Bitmap paramBitmap, int paramInt)
  {
    Object localObject = paramBitmap;
    if (paramInt != 0)
    {
      localObject = paramBitmap;
      if (paramBitmap != null)
      {
        localObject = new Matrix();
        ((Matrix)localObject).setRotate(paramInt, paramBitmap.getWidth() / 2.0F, paramBitmap.getHeight() / 2.0F);
      }
    }
    try
    {
      Bitmap localBitmap = Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), (Matrix)localObject, true);
      localObject = paramBitmap;
      if (paramBitmap != localBitmap)
      {
        paramBitmap.recycle();
        localObject = localBitmap;
      }
      return localObject;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
    }
    return paramBitmap;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.utils.CameraUtils
 * JD-Core Version:    0.6.2
 */