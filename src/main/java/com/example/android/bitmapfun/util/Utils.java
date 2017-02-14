package com.example.android.bitmapfun.util;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import java.io.File;

public class Utils
{
  public static final int IO_BUFFER_SIZE = 8192;

  public static void disableConnectionReuseIfNecessary()
  {
    if (hasHttpConnectionBug())
      System.setProperty("http.keepAlive", "false");
  }

  @SuppressLint({"NewApi"})
  public static int getBitmapSize(Bitmap paramBitmap)
  {
    if (Build.VERSION.SDK_INT >= 12)
      return paramBitmap.getByteCount();
    return paramBitmap.getRowBytes() * paramBitmap.getHeight();
  }

  @SuppressLint({"NewApi"})
  public static File getExternalCacheDir(Context paramContext)
  {
    if (hasExternalCacheDir())
      return paramContext.getExternalCacheDir();
    paramContext = "/Android/data/" + paramContext.getPackageName() + "/cache/";
    return new File(Environment.getExternalStorageDirectory().getPath() + paramContext);
  }

  public static int getMemoryClass(Context paramContext)
  {
    return ((ActivityManager)paramContext.getSystemService("activity")).getMemoryClass();
  }

  @SuppressLint({"NewApi"})
  public static long getUsableSpace(File paramFile)
  {
    if (Build.VERSION.SDK_INT >= 9)
      return paramFile.getUsableSpace();
    paramFile = new StatFs(paramFile.getPath());
    return paramFile.getBlockSize() * paramFile.getAvailableBlocks();
  }

  public static boolean hasActionBar()
  {
    return Build.VERSION.SDK_INT >= 11;
  }

  public static boolean hasExternalCacheDir()
  {
    return Build.VERSION.SDK_INT >= 8;
  }

  public static boolean hasHttpConnectionBug()
  {
    return Build.VERSION.SDK_INT < 8;
  }

  @SuppressLint({"NewApi"})
  public static boolean isExternalStorageRemovable()
  {
    if (Build.VERSION.SDK_INT >= 9)
      return Environment.isExternalStorageRemovable();
    return true;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.example.android.bitmapfun.util.Utils
 * JD-Core Version:    0.6.2
 */