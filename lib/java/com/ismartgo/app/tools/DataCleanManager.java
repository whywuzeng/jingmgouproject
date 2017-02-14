package com.ismartgo.app.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import java.math.BigDecimal;

@SuppressLint({"SdCardPath"})
public class DataCleanManager
{
  public static void cleanApplicationData(Context paramContext, String[] paramArrayOfString)
  {
    cleanInternalCache(paramContext);
    cleanExternalCache(paramContext);
    cleanDatabases(paramContext);
    cleanSharedPreference(paramContext);
    cleanFiles(paramContext);
    if (paramArrayOfString == null);
    while (true)
    {
      return;
      int j = paramArrayOfString.length;
      int i = 0;
      while (i < j)
      {
        cleanCustomCache(paramArrayOfString[i]);
        i += 1;
      }
    }
  }

  public static void cleanCustomCache(String paramString)
  {
    deleteFilesByDirectory(new File(paramString));
  }

  public static void cleanDatabaseByName(Context paramContext, String paramString)
  {
    paramContext.deleteDatabase(paramString);
  }

  public static void cleanDatabases(Context paramContext)
  {
    deleteFilesByDirectory(new File("/data/data/" + paramContext.getPackageName() + "/databases"));
  }

  public static void cleanExternalCache(Context paramContext)
  {
    if (Environment.getExternalStorageState().equals("mounted"))
      deleteFilesByDirectory(paramContext.getExternalCacheDir());
  }

  public static void cleanFiles(Context paramContext)
  {
    deleteFilesByDirectory(paramContext.getFilesDir());
  }

  public static void cleanInternalCache(Context paramContext)
  {
    deleteFilesByDirectory(paramContext.getCacheDir());
  }

  public static void cleanSharedPreference(Context paramContext)
  {
    deleteFilesByDirectory(new File("/data/data/" + paramContext.getPackageName() + "/shared_prefs"));
  }

  private static void deleteFilesByDirectory(File paramFile)
  {
    int j;
    int i;
    if ((paramFile != null) && (paramFile.exists()) && (paramFile.isDirectory()))
    {
      paramFile = paramFile.listFiles();
      j = paramFile.length;
      i = 0;
    }
    while (true)
    {
      if (i >= j)
        return;
      paramFile[i].delete();
      i += 1;
    }
  }

  public static void deleteFolderFile(String paramString, boolean paramBoolean)
  {
    if (!TextUtils.isEmpty(paramString))
      try
      {
        paramString = new File(paramString);
        File[] arrayOfFile;
        int i;
        if (paramString.isDirectory())
        {
          arrayOfFile = paramString.listFiles();
          i = 0;
        }
        while (true)
        {
          if (i >= arrayOfFile.length)
          {
            if (!paramBoolean)
              return;
            if (paramString.isDirectory())
              break;
            paramString.delete();
            return;
          }
          deleteFolderFile(arrayOfFile[i].getAbsolutePath(), true);
          i += 1;
        }
        if (paramString.listFiles().length == 0)
        {
          paramString.delete();
          return;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
  }

  public static String getCacheSize(File paramFile)
    throws Exception
  {
    return getFormatSize(getFolderSize(paramFile));
  }

  public static String getCacheSize(File paramFile1, File paramFile2)
    throws Exception
  {
    return getFormatSize(getFolderSize(paramFile1) + getFolderSize(paramFile2));
  }

  public static long getFolderSize(File paramFile)
    throws Exception
  {
    long l1 = 0L;
    long l2 = l1;
    while (true)
    {
      int i;
      try
      {
        paramFile = paramFile.listFiles();
        i = 0;
        l2 = l1;
        if (i >= paramFile.length)
          return l1;
        l2 = l1;
        if (paramFile[i].isDirectory())
        {
          l2 = l1;
          l1 += getFolderSize(paramFile[i]);
        }
        else
        {
          l2 = l1;
          long l3 = paramFile[i].length();
          l1 += l3;
        }
      }
      catch (Exception paramFile)
      {
        paramFile.printStackTrace();
        return l2;
      }
      i += 1;
    }
  }

  public static String getFormatSize(double paramDouble)
  {
    double d = paramDouble / 1024.0D;
    if (d < 1.0D)
      return paramDouble + "Byte";
    paramDouble = d / 1024.0D;
    if (paramDouble < 1.0D)
      return new BigDecimal(Double.toString(d)).setScale(2, 4).toPlainString() + "KB";
    d = paramDouble / 1024.0D;
    if (d < 1.0D)
      return new BigDecimal(Double.toString(paramDouble)).setScale(2, 4).toPlainString() + "MB";
    paramDouble = d / 1024.0D;
    if (paramDouble < 1.0D)
      return new BigDecimal(Double.toString(d)).setScale(2, 4).toPlainString() + "GB";
    return new BigDecimal(paramDouble).setScale(2, 4).toPlainString() + "TB";
  }

  public static String getTotalCacheSize(Context paramContext)
    throws Exception
  {
    long l2 = getFolderSize(paramContext.getCacheDir()) + getFolderSize(new File("/data/data/" + paramContext.getPackageName() + "/databases")) + getFolderSize(new File("/data/data/" + paramContext.getPackageName() + "/shared_prefs"));
    long l1 = l2;
    if (Environment.getExternalStorageState().equals("mounted"))
      l1 = l2 + getFolderSize(paramContext.getExternalCacheDir());
    return getFormatSize(l1);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.tools.DataCleanManager
 * JD-Core Version:    0.6.2
 */