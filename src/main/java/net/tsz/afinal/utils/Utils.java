package net.tsz.afinal.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import java.io.File;

public class Utils
{
  private static final long INITIALCRC = -1L;
  private static final long POLY64REV = -7661587058870466123L;
  private static final String TAG = "BitmapCommonUtils";
  private static long[] sCrcTable = new long[256];

  static
  {
    int i = 0;
    long l1;
    int j;
    while (true)
    {
      if (i >= 256)
        return;
      l1 = i;
      j = 0;
      if (j < 8)
        break;
      sCrcTable[i] = l1;
      i += 1;
    }
    if (((int)l1 & 0x1) != 0);
    for (long l2 = -7661587058870466123L; ; l2 = 0L)
    {
      l1 = l1 >> 1 ^ l2;
      j += 1;
      break;
    }
  }

  public static byte[] copyOfRange(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i = paramInt2 - paramInt1;
    if (i < 0)
      throw new IllegalArgumentException(paramInt1 + " > " + paramInt2);
    byte[] arrayOfByte = new byte[i];
    System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, 0, Math.min(paramArrayOfByte.length - paramInt1, i));
    return arrayOfByte;
  }

  public static final long crc64Long(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
      return 0L;
    return crc64Long(getBytes(paramString));
  }

  public static final long crc64Long(byte[] paramArrayOfByte)
  {
    long l = -1L;
    int i = 0;
    int j = paramArrayOfByte.length;
    while (true)
    {
      if (i >= j)
        return l;
      l = sCrcTable[(((int)l ^ paramArrayOfByte[i]) & 0xFF)] ^ l >> 8;
      i += 1;
    }
  }

  public static int getBitmapSize(Bitmap paramBitmap)
  {
    return paramBitmap.getRowBytes() * paramBitmap.getHeight();
  }

  public static byte[] getBytes(String paramString)
  {
    byte[] arrayOfByte = new byte[paramString.length() * 2];
    paramString = paramString.toCharArray();
    int k = paramString.length;
    int i = 0;
    int j = 0;
    while (true)
    {
      if (i >= k)
        return arrayOfByte;
      int m = paramString[i];
      int n = j + 1;
      arrayOfByte[j] = ((byte)(m & 0xFF));
      j = n + 1;
      arrayOfByte[n] = ((byte)(m >> 8));
      i += 1;
    }
  }

  public static File getDiskCacheDir(Context paramContext, String paramString)
  {
    if ("mounted".equals(Environment.getExternalStorageState()));
    for (paramContext = getExternalCacheDir(paramContext).getPath(); ; paramContext = paramContext.getCacheDir().getPath())
      return new File(paramContext + File.separator + paramString);
  }

  public static File getExternalCacheDir(Context paramContext)
  {
    paramContext = "/Android/data/" + paramContext.getPackageName() + "/cache/";
    return new File(Environment.getExternalStorageDirectory().getPath() + paramContext);
  }

  public static long getUsableSpace(File paramFile)
  {
    try
    {
      paramFile = new StatFs(paramFile.getPath());
      long l = paramFile.getBlockSize();
      int i = paramFile.getAvailableBlocks();
      return l * i;
    }
    catch (Exception paramFile)
    {
      Log.e("BitmapCommonUtils", "获取 sdcard 缓存大小 出错，请查看AndroidManifest.xml 是否添加了sdcard的访问权限");
      paramFile.printStackTrace();
    }
    return -1L;
  }

  public static boolean isSameKey(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    int j = paramArrayOfByte1.length;
    if (paramArrayOfByte2.length < j)
      return false;
    int i = 0;
    while (true)
    {
      if (i >= j)
        return true;
      if (paramArrayOfByte1[i] != paramArrayOfByte2[i])
        break;
      i += 1;
    }
  }

  public static byte[] makeKey(String paramString)
  {
    return getBytes(paramString);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.utils.Utils
 * JD-Core Version:    0.6.2
 */