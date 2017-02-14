package net.tsz.afinal.bitmap.download;

import android.util.Log;
import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class SimpleDownloader
  implements Downloader
{
  private static final int IO_BUFFER_SIZE = 8192;
  private static final String TAG = SimpleDownloader.class.getSimpleName();

  // ERROR //
  private byte[] getFromFile(File paramFile)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: aconst_null
    //   7: astore_3
    //   8: aconst_null
    //   9: astore 6
    //   11: new 36	java/io/FileInputStream
    //   14: dup
    //   15: aload_1
    //   16: invokespecial 39	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   19: astore 4
    //   21: new 41	java/io/ByteArrayOutputStream
    //   24: dup
    //   25: invokespecial 42	java/io/ByteArrayOutputStream:<init>	()V
    //   28: astore_3
    //   29: sipush 1024
    //   32: newarray byte
    //   34: astore 5
    //   36: aload 4
    //   38: aload 5
    //   40: invokevirtual 46	java/io/FileInputStream:read	([B)I
    //   43: istore_2
    //   44: iload_2
    //   45: iconst_m1
    //   46: if_icmpne +20 -> 66
    //   49: aload_3
    //   50: invokevirtual 50	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   53: astore_3
    //   54: aload 4
    //   56: ifnull +108 -> 164
    //   59: aload 4
    //   61: invokevirtual 53	java/io/FileInputStream:close	()V
    //   64: aload_3
    //   65: areturn
    //   66: aload_3
    //   67: aload 5
    //   69: iconst_0
    //   70: iload_2
    //   71: invokevirtual 57	java/io/ByteArrayOutputStream:write	([BII)V
    //   74: goto -38 -> 36
    //   77: astore 5
    //   79: aload 4
    //   81: astore_3
    //   82: getstatic 24	net/tsz/afinal/bitmap/download/SimpleDownloader:TAG	Ljava/lang/String;
    //   85: new 59	java/lang/StringBuilder
    //   88: dup
    //   89: ldc 61
    //   91: invokespecial 64	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   94: aload_1
    //   95: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   98: ldc 70
    //   100: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   103: aload 5
    //   105: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   108: invokevirtual 76	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   111: invokestatic 82	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   114: pop
    //   115: aload 4
    //   117: ifnull -113 -> 4
    //   120: aload 4
    //   122: invokevirtual 53	java/io/FileInputStream:close	()V
    //   125: aconst_null
    //   126: areturn
    //   127: astore_1
    //   128: aload_3
    //   129: areturn
    //   130: astore_1
    //   131: aload_3
    //   132: ifnull +7 -> 139
    //   135: aload_3
    //   136: invokevirtual 53	java/io/FileInputStream:close	()V
    //   139: aload_1
    //   140: athrow
    //   141: astore_1
    //   142: aconst_null
    //   143: areturn
    //   144: astore_3
    //   145: goto -6 -> 139
    //   148: astore_1
    //   149: aload 4
    //   151: astore_3
    //   152: goto -21 -> 131
    //   155: astore 5
    //   157: aload 6
    //   159: astore 4
    //   161: goto -82 -> 79
    //   164: aload_3
    //   165: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   21	36	77	java/lang/Exception
    //   36	44	77	java/lang/Exception
    //   49	54	77	java/lang/Exception
    //   66	74	77	java/lang/Exception
    //   59	64	127	java/io/IOException
    //   11	21	130	finally
    //   82	115	130	finally
    //   120	125	141	java/io/IOException
    //   135	139	144	java/io/IOException
    //   21	36	148	finally
    //   36	44	148	finally
    //   49	54	148	finally
    //   66	74	148	finally
    //   11	21	155	java/lang/Exception
  }

  // ERROR //
  private byte[] getFromHttp(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore 4
    //   6: aconst_null
    //   7: astore 7
    //   9: aconst_null
    //   10: astore 9
    //   12: aload 7
    //   14: astore 5
    //   16: new 86	java/net/URL
    //   19: dup
    //   20: aload_1
    //   21: invokespecial 87	java/net/URL:<init>	(Ljava/lang/String;)V
    //   24: invokevirtual 91	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   27: checkcast 93	java/net/HttpURLConnection
    //   30: astore_3
    //   31: aload 7
    //   33: astore 5
    //   35: aload_3
    //   36: astore 4
    //   38: aload_3
    //   39: astore 6
    //   41: new 8	net/tsz/afinal/bitmap/download/SimpleDownloader$FlushedInputStream
    //   44: dup
    //   45: aload_0
    //   46: new 95	java/io/BufferedInputStream
    //   49: dup
    //   50: aload_3
    //   51: invokevirtual 99	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   54: sipush 8192
    //   57: invokespecial 102	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;I)V
    //   60: invokespecial 105	net/tsz/afinal/bitmap/download/SimpleDownloader$FlushedInputStream:<init>	(Lnet/tsz/afinal/bitmap/download/SimpleDownloader;Ljava/io/InputStream;)V
    //   63: astore 7
    //   65: new 41	java/io/ByteArrayOutputStream
    //   68: dup
    //   69: invokespecial 42	java/io/ByteArrayOutputStream:<init>	()V
    //   72: astore 4
    //   74: aload 7
    //   76: invokevirtual 108	net/tsz/afinal/bitmap/download/SimpleDownloader$FlushedInputStream:read	()I
    //   79: istore_2
    //   80: iload_2
    //   81: iconst_m1
    //   82: if_icmpne +43 -> 125
    //   85: aload 4
    //   87: invokevirtual 50	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   90: astore 4
    //   92: aload_3
    //   93: ifnull +7 -> 100
    //   96: aload_3
    //   97: invokevirtual 111	java/net/HttpURLConnection:disconnect	()V
    //   100: iconst_0
    //   101: ifeq +11 -> 112
    //   104: new 113	java/lang/NullPointerException
    //   107: dup
    //   108: invokespecial 114	java/lang/NullPointerException:<init>	()V
    //   111: athrow
    //   112: aload 7
    //   114: ifnull +8 -> 122
    //   117: aload 7
    //   119: invokevirtual 115	net/tsz/afinal/bitmap/download/SimpleDownloader$FlushedInputStream:close	()V
    //   122: aload 4
    //   124: areturn
    //   125: aload 4
    //   127: iload_2
    //   128: invokevirtual 118	java/io/ByteArrayOutputStream:write	(I)V
    //   131: goto -57 -> 74
    //   134: astore 8
    //   136: aload 7
    //   138: astore 5
    //   140: aload_3
    //   141: astore 4
    //   143: getstatic 24	net/tsz/afinal/bitmap/download/SimpleDownloader:TAG	Ljava/lang/String;
    //   146: new 59	java/lang/StringBuilder
    //   149: dup
    //   150: ldc 120
    //   152: invokespecial 64	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   155: aload_1
    //   156: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   159: ldc 70
    //   161: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   164: aload 8
    //   166: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   169: invokevirtual 76	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   172: invokestatic 82	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   175: pop
    //   176: aload_3
    //   177: ifnull +7 -> 184
    //   180: aload_3
    //   181: invokevirtual 111	java/net/HttpURLConnection:disconnect	()V
    //   184: iconst_0
    //   185: ifeq +11 -> 196
    //   188: new 113	java/lang/NullPointerException
    //   191: dup
    //   192: invokespecial 114	java/lang/NullPointerException:<init>	()V
    //   195: athrow
    //   196: aload 7
    //   198: ifnull +8 -> 206
    //   201: aload 7
    //   203: invokevirtual 115	net/tsz/afinal/bitmap/download/SimpleDownloader$FlushedInputStream:close	()V
    //   206: aconst_null
    //   207: areturn
    //   208: astore_1
    //   209: aload 4
    //   211: astore_3
    //   212: aload_3
    //   213: ifnull +7 -> 220
    //   216: aload_3
    //   217: invokevirtual 111	java/net/HttpURLConnection:disconnect	()V
    //   220: iconst_0
    //   221: ifeq +11 -> 232
    //   224: new 113	java/lang/NullPointerException
    //   227: dup
    //   228: invokespecial 114	java/lang/NullPointerException:<init>	()V
    //   231: athrow
    //   232: aload 5
    //   234: ifnull +8 -> 242
    //   237: aload 5
    //   239: invokevirtual 115	net/tsz/afinal/bitmap/download/SimpleDownloader$FlushedInputStream:close	()V
    //   242: aload_1
    //   243: athrow
    //   244: astore_3
    //   245: goto -3 -> 242
    //   248: astore_1
    //   249: aload 7
    //   251: astore 5
    //   253: goto -41 -> 212
    //   256: astore_1
    //   257: goto -51 -> 206
    //   260: astore 8
    //   262: aload 9
    //   264: astore 7
    //   266: aload 6
    //   268: astore_3
    //   269: goto -133 -> 136
    //   272: astore_1
    //   273: goto -151 -> 122
    //
    // Exception table:
    //   from	to	target	type
    //   65	74	134	java/io/IOException
    //   74	80	134	java/io/IOException
    //   85	92	134	java/io/IOException
    //   125	131	134	java/io/IOException
    //   16	31	208	finally
    //   41	65	208	finally
    //   143	176	208	finally
    //   224	232	244	java/io/IOException
    //   237	242	244	java/io/IOException
    //   65	74	248	finally
    //   74	80	248	finally
    //   85	92	248	finally
    //   125	131	248	finally
    //   188	196	256	java/io/IOException
    //   201	206	256	java/io/IOException
    //   16	31	260	java/io/IOException
    //   41	65	260	java/io/IOException
    //   104	112	272	java/io/IOException
    //   117	122	272	java/io/IOException
  }

  public byte[] download(String paramString)
  {
    if (paramString == null);
    do
    {
      while (true)
      {
        return null;
        if (paramString.trim().toLowerCase().startsWith("http"))
          return getFromHttp(paramString);
        if (paramString.trim().toLowerCase().startsWith("file:"))
          try
          {
            Object localObject = new File(new URI(paramString));
            if ((((File)localObject).exists()) && (((File)localObject).canRead()))
            {
              localObject = getFromFile((File)localObject);
              return localObject;
            }
          }
          catch (URISyntaxException localURISyntaxException)
          {
            Log.e(TAG, "Error in read from file - " + paramString + " : " + localURISyntaxException);
            return null;
          }
      }
      paramString = new File(paramString);
    }
    while ((!paramString.exists()) || (!paramString.canRead()));
    return getFromFile(paramString);
  }

  public class FlushedInputStream extends FilterInputStream
  {
    public FlushedInputStream(InputStream arg2)
    {
      super();
    }

    public long skip(long paramLong)
      throws IOException
    {
      long l2;
      for (long l1 = 0L; ; l1 += l2)
      {
        if (l1 >= paramLong);
        do
        {
          return l1;
          long l3 = this.in.skip(paramLong - l1);
          l2 = l3;
          if (l3 != 0L)
            break;
        }
        while (read() < 0);
        l2 = 1L;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.bitmap.download.SimpleDownloader
 * JD-Core Version:    0.6.2
 */