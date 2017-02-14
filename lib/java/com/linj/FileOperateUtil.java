package com.linj;

import android.content.Context;
import java.io.File;
import java.io.FilenameFilter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FileOperateUtil
{
  public static final int ROOT = 0;
  public static final String TAG = "FileOperateUtil";
  public static final int TYPE_IMAGE = 1;
  public static final int TYPE_THUMBNAIL = 2;
  public static final int TYPE_VIDEO = 3;

  public static String createFileNmae(String paramString)
  {
    String str2 = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
    String str1 = paramString;
    if (!paramString.startsWith("."))
      str1 = "." + paramString;
    return str2 + str1;
  }

  public static boolean deleteSourceFile(String paramString, Context paramContext)
  {
    File localFile = new File(paramString);
    if (!localFile.exists())
      return false;
    boolean bool = localFile.delete();
    paramString = new File(paramString.replace(paramContext.getString(2131296408), paramContext.getString(2131296409)));
    if (!paramString.exists())
      return bool;
    return paramString.delete();
  }

  public static boolean deleteThumbFile(String paramString, Context paramContext)
  {
    File localFile = new File(paramString);
    if (!localFile.exists())
      return false;
    boolean bool = localFile.delete();
    paramString = new File(paramString.replace(paramContext.getString(2131296409), paramContext.getString(2131296408)));
    if (!paramString.exists())
      return bool;
    return paramString.delete();
  }

  public static String getFolderPath(Context paramContext, int paramInt, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramContext.getExternalFilesDir(null).getAbsolutePath());
    localStringBuilder.append(File.separator);
    localStringBuilder.append(paramContext.getString(2131296407));
    localStringBuilder.append(File.separator);
    localStringBuilder.append(paramString);
    localStringBuilder.append(File.separator);
    switch (paramInt)
    {
    default:
    case 1:
    case 3:
    case 2:
    }
    while (true)
    {
      return localStringBuilder.toString();
      localStringBuilder.append(paramContext.getString(2131296408));
      continue;
      localStringBuilder.append(paramContext.getString(2131296410));
      continue;
      localStringBuilder.append(paramContext.getString(2131296409));
    }
  }

  public static List<File> listFiles(File paramFile, final String paramString1, String paramString2)
  {
    if ((paramFile == null) || (!paramFile.exists()) || (!paramFile.isDirectory()));
    do
    {
      return null;
      paramFile = paramFile.listFiles(new FilenameFilter()
      {
        public boolean accept(File paramAnonymousFile, String paramAnonymousString)
        {
          if ((FileOperateUtil.this == null) || (FileOperateUtil.this.equals("")))
            return paramAnonymousString.endsWith(paramString1);
          return (paramAnonymousString.contains(FileOperateUtil.this)) && (paramAnonymousString.endsWith(paramString1));
        }
      });
    }
    while (paramFile == null);
    paramFile = new ArrayList(Arrays.asList(paramFile));
    sortList(paramFile, false);
    return paramFile;
  }

  public static List<File> listFiles(String paramString1, String paramString2)
  {
    return listFiles(new File(paramString1), paramString2, null);
  }

  public static List<File> listFiles(String paramString1, String paramString2, String paramString3)
  {
    return listFiles(new File(paramString1), paramString2, paramString3);
  }

  public static void sortList(List<File> paramList, boolean paramBoolean)
  {
    Collections.sort(paramList, new Comparator()
    {
      public int compare(File paramAnonymousFile1, File paramAnonymousFile2)
      {
        if (paramAnonymousFile1.lastModified() > paramAnonymousFile2.lastModified())
          if (!this.val$asc);
        do
        {
          return 1;
          return -1;
          if (paramAnonymousFile1.lastModified() == paramAnonymousFile2.lastModified())
            return 0;
        }
        while (!this.val$asc);
        return -1;
      }
    });
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.linj.FileOperateUtil
 * JD-Core Version:    0.6.2
 */