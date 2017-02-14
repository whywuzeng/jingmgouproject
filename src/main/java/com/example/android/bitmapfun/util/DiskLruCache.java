package com.example.android.bitmapfun.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DiskLruCache
{
  private static final String CACHE_FILENAME_PREFIX = "cache_";
  private static final int INITIAL_CAPACITY = 32;
  private static final float LOAD_FACTOR = 0.75F;
  private static final int MAX_REMOVALS = 4;
  private static final String TAG = "DiskLruCache";
  private static final FilenameFilter cacheFileFilter = new FilenameFilter()
  {
    public boolean accept(File paramAnonymousFile, String paramAnonymousString)
    {
      return paramAnonymousString.startsWith("cache_");
    }
  };
  private int cacheByteSize = 0;
  private int cacheSize = 0;
  private final File mCacheDir;
  private Bitmap.CompressFormat mCompressFormat = Bitmap.CompressFormat.JPEG;
  private int mCompressQuality = 70;
  private final Map<String, String> mLinkedHashMap = Collections.synchronizedMap(new LinkedHashMap(32, 0.75F, true));
  private long maxCacheByteSize = 5242880L;
  private final int maxCacheItemSize = 64;

  private DiskLruCache(File paramFile, long paramLong)
  {
    this.mCacheDir = paramFile;
    this.maxCacheByteSize = paramLong;
  }

  public static void clearCache(Context paramContext, String paramString)
  {
    clearCache(getDiskCacheDir(paramContext, paramString));
  }

  private static void clearCache(File paramFile)
  {
    paramFile = paramFile.listFiles(cacheFileFilter);
    int i = 0;
    while (true)
    {
      if (i >= paramFile.length)
        return;
      paramFile[i].delete();
      i += 1;
    }
  }

  public static String createFilePath(File paramFile, String paramString)
  {
    try
    {
      paramFile = paramFile.getAbsolutePath() + File.separator + "cache_" + URLEncoder.encode(paramString.replace("*", ""), "UTF-8");
      return paramFile;
    }
    catch (UnsupportedEncodingException paramFile)
    {
      Log.e("DiskLruCache", "createFilePath - " + paramFile);
    }
    return null;
  }

  private void flushCache()
  {
    int i = 0;
    while (true)
    {
      if ((i >= 4) || ((this.cacheSize <= 64) && (this.cacheByteSize <= this.maxCacheByteSize)))
        return;
      Map.Entry localEntry = (Map.Entry)this.mLinkedHashMap.entrySet().iterator().next();
      File localFile = new File((String)localEntry.getValue());
      long l = localFile.length();
      this.mLinkedHashMap.remove(localEntry.getKey());
      localFile.delete();
      this.cacheSize = this.mLinkedHashMap.size();
      this.cacheByteSize = ((int)(this.cacheByteSize - l));
      i += 1;
      Log.d("DiskLruCache", "flushCache - Removed cache file, " + localFile + ", " + l);
    }
  }

  public static File getDiskCacheDir(Context paramContext, String paramString)
  {
    if ((Environment.getExternalStorageState() == "mounted") || (!Utils.isExternalStorageRemovable()));
    for (paramContext = Utils.getExternalCacheDir(paramContext).getPath(); ; paramContext = paramContext.getCacheDir().getPath())
      return new File(paramContext + File.separator + paramString);
  }

  public static DiskLruCache openCache(Context paramContext, File paramFile, long paramLong)
  {
    if (!paramFile.exists())
      paramFile.mkdir();
    if ((paramFile.isDirectory()) && (paramFile.canWrite()) && (Utils.getUsableSpace(paramFile) > paramLong))
      return new DiskLruCache(paramFile, paramLong);
    return null;
  }

  private void put(String paramString1, String paramString2)
  {
    this.mLinkedHashMap.put(paramString1, paramString2);
    this.cacheSize = this.mLinkedHashMap.size();
    this.cacheByteSize = ((int)(this.cacheByteSize + new File(paramString2).length()));
  }

  // ERROR //
  private boolean writeBitmapToFile(Bitmap paramBitmap, String paramString)
    throws IOException, FileNotFoundException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: new 263	java/io/BufferedOutputStream
    //   6: dup
    //   7: new 265	java/io/FileOutputStream
    //   10: dup
    //   11: aload_2
    //   12: invokespecial 266	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   15: sipush 8192
    //   18: invokespecial 269	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;I)V
    //   21: astore_2
    //   22: aload_1
    //   23: aload_0
    //   24: getfield 63	com/example/android/bitmapfun/util/DiskLruCache:mCompressFormat	Landroid/graphics/Bitmap$CompressFormat;
    //   27: aload_0
    //   28: getfield 65	com/example/android/bitmapfun/util/DiskLruCache:mCompressQuality	I
    //   31: aload_2
    //   32: invokevirtual 275	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   35: istore_3
    //   36: aload_2
    //   37: ifnull +7 -> 44
    //   40: aload_2
    //   41: invokevirtual 280	java/io/OutputStream:close	()V
    //   44: iload_3
    //   45: ireturn
    //   46: astore_2
    //   47: aload 4
    //   49: astore_1
    //   50: aload_1
    //   51: ifnull +7 -> 58
    //   54: aload_1
    //   55: invokevirtual 280	java/io/OutputStream:close	()V
    //   58: aload_2
    //   59: athrow
    //   60: astore 4
    //   62: aload_2
    //   63: astore_1
    //   64: aload 4
    //   66: astore_2
    //   67: goto -17 -> 50
    //
    // Exception table:
    //   from	to	target	type
    //   3	22	46	finally
    //   22	36	60	finally
  }

  public void clearCache()
  {
    clearCache(this.mCacheDir);
  }

  public boolean containsKey(String paramString)
  {
    if (this.mLinkedHashMap.containsKey(paramString))
      return true;
    String str = createFilePath(this.mCacheDir, paramString);
    if (new File(str).exists())
    {
      put(paramString, str);
      return true;
    }
    return false;
  }

  public String createFilePath(String paramString)
  {
    return createFilePath(this.mCacheDir, paramString);
  }

  public Bitmap get(String paramString)
  {
    synchronized (this.mLinkedHashMap)
    {
      String str = (String)this.mLinkedHashMap.get(paramString);
      if (str != null)
      {
        Log.d("DiskLruCache", "Disk cache hit");
        paramString = BitmapFactory.decodeFile(str);
        return paramString;
      }
      str = createFilePath(this.mCacheDir, paramString);
      if (new File(str).exists())
      {
        put(paramString, str);
        Log.d("DiskLruCache", "Disk cache hit (existing file)");
        paramString = BitmapFactory.decodeFile(str);
        return paramString;
      }
    }
    return null;
  }

  public void put(String paramString, Bitmap paramBitmap)
  {
    Object localObject;
    synchronized (this.mLinkedHashMap)
    {
      localObject = this.mLinkedHashMap.get(paramString);
      if (localObject != null);
    }
    try
    {
      localObject = createFilePath(this.mCacheDir, paramString);
      if (writeBitmapToFile(paramBitmap, (String)localObject))
      {
        put(paramString, (String)localObject);
        flushCache();
      }
      return;
    }
    catch (FileNotFoundException paramString)
    {
      while (true)
        Log.e("DiskLruCache", "Error in put: " + paramString.getMessage());
      paramString = finally;
      throw paramString;
    }
    catch (IOException paramString)
    {
      while (true)
        Log.e("DiskLruCache", "Error in put: " + paramString.getMessage());
    }
  }

  public void setCompressParams(Bitmap.CompressFormat paramCompressFormat, int paramInt)
  {
    this.mCompressFormat = paramCompressFormat;
    this.mCompressQuality = paramInt;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.example.android.bitmapfun.util.DiskLruCache
 * JD-Core Version:    0.6.2
 */