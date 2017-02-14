package com.nostra13.universalimageloader.utils;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.IOException;

public final class StorageUtils
{
  private static final String EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";
  private static final String INDIVIDUAL_DIR_NAME = "uil-images";

  public static File getCacheDirectory(Context paramContext)
  {
    return getCacheDirectory(paramContext, true);
  }

  public static File getCacheDirectory(Context paramContext, boolean paramBoolean)
  {
    Object localObject3 = null;
    try
    {
      localObject2 = Environment.getExternalStorageState();
      Object localObject1 = localObject3;
      if (paramBoolean)
      {
        localObject1 = localObject3;
        if ("mounted".equals(localObject2))
        {
          localObject1 = localObject3;
          if (hasExternalStoragePermission(paramContext))
            localObject1 = getExternalCacheDir(paramContext);
        }
      }
      localObject2 = localObject1;
      if (localObject1 == null)
        localObject2 = paramContext.getCacheDir();
      localObject1 = localObject2;
      if (localObject2 == null)
      {
        paramContext = "/data/data/" + paramContext.getPackageName() + "/cache/";
        L.w("Can't define system cache directory! '%s' will be used.", new Object[] { paramContext });
        localObject1 = new File(paramContext);
      }
      return localObject1;
    }
    catch (NullPointerException localNullPointerException)
    {
      while (true)
        Object localObject2 = "";
    }
  }

  private static File getExternalCacheDir(Context paramContext)
  {
    File localFile = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data"), paramContext.getPackageName()), "cache");
    paramContext = localFile;
    if (!localFile.exists())
    {
      if (!localFile.mkdirs())
      {
        L.w("Unable to create external cache directory", new Object[0]);
        paramContext = null;
      }
    }
    else
      return paramContext;
    try
    {
      new File(localFile, ".nomedia").createNewFile();
      return localFile;
    }
    catch (IOException paramContext)
    {
      L.i("Can't create \".nomedia\" file in application external cache directory", new Object[0]);
    }
    return localFile;
  }

  public static File getIndividualCacheDirectory(Context paramContext)
  {
    File localFile1 = getCacheDirectory(paramContext);
    File localFile2 = new File(localFile1, "uil-images");
    paramContext = localFile2;
    if (!localFile2.exists())
    {
      paramContext = localFile2;
      if (!localFile2.mkdir())
        paramContext = localFile1;
    }
    return paramContext;
  }

  public static File getOwnCacheDirectory(Context paramContext, String paramString)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if ("mounted".equals(Environment.getExternalStorageState()))
    {
      localObject1 = localObject2;
      if (hasExternalStoragePermission(paramContext))
        localObject1 = new File(Environment.getExternalStorageDirectory(), paramString);
    }
    if (localObject1 != null)
    {
      paramString = (String)localObject1;
      if (!((File)localObject1).exists())
      {
        paramString = (String)localObject1;
        if (((File)localObject1).mkdirs());
      }
    }
    else
    {
      paramString = paramContext.getCacheDir();
    }
    return paramString;
  }

  private static boolean hasExternalStoragePermission(Context paramContext)
  {
    return paramContext.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.utils.StorageUtils
 * JD-Core Version:    0.6.2
 */