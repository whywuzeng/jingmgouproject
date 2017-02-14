package com.yolanda.nohttp.tools;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.os.StatFs;

public class FileUtil
{
  @SuppressLint({"NewApi"})
  public static long getDirSize(String paramString)
  {
    paramString = new StatFs(paramString);
    long l2;
    if (Build.VERSION.SDK_INT >= 18)
      l2 = paramString.getBlockSizeLong();
    for (long l1 = paramString.getAvailableBlocksLong(); ; l1 = paramString.getAvailableBlocks())
    {
      return l1 * l2;
      l2 = paramString.getBlockSize();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.tools.FileUtil
 * JD-Core Version:    0.6.2
 */