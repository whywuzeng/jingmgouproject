package com.example.android.bitmapfun.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import java.io.File;

public class ImageFetcher extends ImageResizer
{
  public static final String HTTP_CACHE_DIR = "http";
  private static final int HTTP_CACHE_SIZE = 10485760;
  private static final String TAG = "ImageFetcher";

  public ImageFetcher(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    init(paramContext);
  }

  public ImageFetcher(Context paramContext, int paramInt1, int paramInt2)
  {
    super(paramContext, paramInt1, paramInt2);
    init(paramContext);
  }

  private void checkConnection(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((paramContext == null) || (!paramContext.isConnectedOrConnecting()))
      Log.e("ImageFetcher", "checkConnection - no connection found");
  }

  // ERROR //
  public static File downloadBitmap(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_0
    //   2: ldc 8
    //   4: invokestatic 64	com/example/android/bitmapfun/util/DiskLruCache:getDiskCacheDir	(Landroid/content/Context;Ljava/lang/String;)Ljava/io/File;
    //   7: ldc2_w 65
    //   10: invokestatic 70	com/example/android/bitmapfun/util/DiskLruCache:openCache	(Landroid/content/Context;Ljava/io/File;J)Lcom/example/android/bitmapfun/util/DiskLruCache;
    //   13: astore_0
    //   14: new 72	java/io/File
    //   17: dup
    //   18: aload_0
    //   19: aload_1
    //   20: invokevirtual 76	com/example/android/bitmapfun/util/DiskLruCache:createFilePath	(Ljava/lang/String;)Ljava/lang/String;
    //   23: invokespecial 79	java/io/File:<init>	(Ljava/lang/String;)V
    //   26: astore 7
    //   28: aload_0
    //   29: aload_1
    //   30: invokevirtual 83	com/example/android/bitmapfun/util/DiskLruCache:containsKey	(Ljava/lang/String;)Z
    //   33: ifeq +6 -> 39
    //   36: aload 7
    //   38: areturn
    //   39: invokestatic 89	com/example/android/bitmapfun/util/Utils:disableConnectionReuseIfNecessary	()V
    //   42: aconst_null
    //   43: astore 4
    //   45: aconst_null
    //   46: astore_0
    //   47: aconst_null
    //   48: astore 5
    //   50: aconst_null
    //   51: astore 6
    //   53: aload 5
    //   55: astore_3
    //   56: new 91	java/net/URL
    //   59: dup
    //   60: aload_1
    //   61: invokespecial 92	java/net/URL:<init>	(Ljava/lang/String;)V
    //   64: invokevirtual 96	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   67: checkcast 98	java/net/HttpURLConnection
    //   70: astore_1
    //   71: aload 5
    //   73: astore_3
    //   74: aload_1
    //   75: astore_0
    //   76: aload_1
    //   77: astore 4
    //   79: new 100	java/io/BufferedInputStream
    //   82: dup
    //   83: aload_1
    //   84: invokevirtual 104	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   87: sipush 8192
    //   90: invokespecial 107	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;I)V
    //   93: astore 8
    //   95: aload 5
    //   97: astore_3
    //   98: aload_1
    //   99: astore_0
    //   100: aload_1
    //   101: astore 4
    //   103: new 109	java/io/BufferedOutputStream
    //   106: dup
    //   107: new 111	java/io/FileOutputStream
    //   110: dup
    //   111: aload 7
    //   113: invokespecial 114	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   116: sipush 8192
    //   119: invokespecial 117	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;I)V
    //   122: astore 5
    //   124: aload 8
    //   126: invokevirtual 123	java/io/InputStream:read	()I
    //   129: istore_2
    //   130: iload_2
    //   131: iconst_m1
    //   132: if_icmpne +50 -> 182
    //   135: aload_1
    //   136: ifnull +7 -> 143
    //   139: aload_1
    //   140: invokevirtual 126	java/net/HttpURLConnection:disconnect	()V
    //   143: aload 5
    //   145: ifnull -109 -> 36
    //   148: aload 5
    //   150: invokevirtual 129	java/io/BufferedOutputStream:close	()V
    //   153: aload 7
    //   155: areturn
    //   156: astore_0
    //   157: ldc 14
    //   159: new 131	java/lang/StringBuilder
    //   162: dup
    //   163: ldc 133
    //   165: invokespecial 134	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   168: aload_0
    //   169: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   172: invokevirtual 142	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   175: invokestatic 55	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   178: pop
    //   179: aload 7
    //   181: areturn
    //   182: aload 5
    //   184: iload_2
    //   185: invokevirtual 146	java/io/BufferedOutputStream:write	(I)V
    //   188: goto -64 -> 124
    //   191: astore_0
    //   192: aload_1
    //   193: astore 4
    //   195: aload 5
    //   197: astore_1
    //   198: aload_0
    //   199: astore 5
    //   201: aload_1
    //   202: astore_3
    //   203: aload 4
    //   205: astore_0
    //   206: ldc 14
    //   208: new 131	java/lang/StringBuilder
    //   211: dup
    //   212: ldc 133
    //   214: invokespecial 134	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   217: aload 5
    //   219: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   222: invokevirtual 142	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   225: invokestatic 55	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   228: pop
    //   229: aload 4
    //   231: ifnull +8 -> 239
    //   234: aload 4
    //   236: invokevirtual 126	java/net/HttpURLConnection:disconnect	()V
    //   239: aload_1
    //   240: ifnull +7 -> 247
    //   243: aload_1
    //   244: invokevirtual 129	java/io/BufferedOutputStream:close	()V
    //   247: aconst_null
    //   248: areturn
    //   249: astore_0
    //   250: ldc 14
    //   252: new 131	java/lang/StringBuilder
    //   255: dup
    //   256: ldc 133
    //   258: invokespecial 134	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   261: aload_0
    //   262: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   265: invokevirtual 142	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   268: invokestatic 55	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   271: pop
    //   272: goto -25 -> 247
    //   275: astore_1
    //   276: aload_0
    //   277: ifnull +7 -> 284
    //   280: aload_0
    //   281: invokevirtual 126	java/net/HttpURLConnection:disconnect	()V
    //   284: aload_3
    //   285: ifnull +7 -> 292
    //   288: aload_3
    //   289: invokevirtual 129	java/io/BufferedOutputStream:close	()V
    //   292: aload_1
    //   293: athrow
    //   294: astore_0
    //   295: ldc 14
    //   297: new 131	java/lang/StringBuilder
    //   300: dup
    //   301: ldc 133
    //   303: invokespecial 134	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   306: aload_0
    //   307: invokevirtual 138	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   310: invokevirtual 142	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   313: invokestatic 55	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   316: pop
    //   317: goto -25 -> 292
    //   320: astore 4
    //   322: aload 5
    //   324: astore_3
    //   325: aload_1
    //   326: astore_0
    //   327: aload 4
    //   329: astore_1
    //   330: goto -54 -> 276
    //   333: astore 5
    //   335: aload 6
    //   337: astore_1
    //   338: goto -137 -> 201
    //
    // Exception table:
    //   from	to	target	type
    //   148	153	156	java/io/IOException
    //   124	130	191	java/io/IOException
    //   182	188	191	java/io/IOException
    //   243	247	249	java/io/IOException
    //   56	71	275	finally
    //   79	95	275	finally
    //   103	124	275	finally
    //   206	229	275	finally
    //   288	292	294	java/io/IOException
    //   124	130	320	finally
    //   182	188	320	finally
    //   56	71	333	java/io/IOException
    //   79	95	333	java/io/IOException
    //   103	124	333	java/io/IOException
  }

  private void init(Context paramContext)
  {
    checkConnection(paramContext);
  }

  private Bitmap processBitmap(String paramString)
  {
    paramString = downloadBitmap(this.mContext, paramString);
    if (paramString != null)
      return decodeSampledBitmapFromFile(paramString.toString(), this.mImageWidth, this.mImageHeight);
    return null;
  }

  protected Bitmap processBitmap(Object paramObject)
  {
    return processBitmap(String.valueOf(paramObject));
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.example.android.bitmapfun.util.ImageFetcher
 * JD-Core Version:    0.6.2
 */