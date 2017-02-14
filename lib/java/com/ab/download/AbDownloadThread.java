package com.ab.download;

import com.ab.global.AbAppData;
import java.io.File;

public class AbDownloadThread extends Thread
{
  private static final boolean D = AbAppData.DEBUG;
  private static final String TAG = "DownloadThread";
  private AbFileDownloader downloader;
  private boolean finish = false;
  private boolean flag = false;
  private DownFile mDownFile = null;
  private File saveFile;

  public AbDownloadThread(AbFileDownloader paramAbFileDownloader, DownFile paramDownFile, File paramFile)
  {
    this.saveFile = paramFile;
    this.downloader = paramAbFileDownloader;
    this.mDownFile = paramDownFile;
  }

  public File getSaveFile()
  {
    return this.saveFile;
  }

  public boolean isFinish()
  {
    return this.finish;
  }

  // ERROR //
  public void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: iconst_1
    //   2: putfield 38	com/ab/download/AbDownloadThread:flag	Z
    //   5: aconst_null
    //   6: astore 8
    //   8: aconst_null
    //   9: astore_3
    //   10: aconst_null
    //   11: astore 6
    //   13: aconst_null
    //   14: astore 7
    //   16: aload_0
    //   17: getfield 34	com/ab/download/AbDownloadThread:mDownFile	Lcom/ab/download/DownFile;
    //   20: invokevirtual 57	com/ab/download/DownFile:getDownLength	()J
    //   23: aload_0
    //   24: getfield 34	com/ab/download/AbDownloadThread:mDownFile	Lcom/ab/download/DownFile;
    //   27: invokevirtual 60	com/ab/download/DownFile:getTotalLength	()J
    //   30: lcmp
    //   31: ifge +352 -> 383
    //   34: aload_3
    //   35: astore_2
    //   36: aload 6
    //   38: astore 4
    //   40: aload 8
    //   42: astore 5
    //   44: new 62	java/net/URL
    //   47: dup
    //   48: aload_0
    //   49: getfield 34	com/ab/download/AbDownloadThread:mDownFile	Lcom/ab/download/DownFile;
    //   52: invokevirtual 66	com/ab/download/DownFile:getDownUrl	()Ljava/lang/String;
    //   55: invokespecial 69	java/net/URL:<init>	(Ljava/lang/String;)V
    //   58: invokevirtual 73	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   61: checkcast 75	java/net/HttpURLConnection
    //   64: astore 9
    //   66: aload_3
    //   67: astore_2
    //   68: aload 6
    //   70: astore 4
    //   72: aload 8
    //   74: astore 5
    //   76: aload 9
    //   78: sipush 5000
    //   81: invokevirtual 79	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   84: aload_3
    //   85: astore_2
    //   86: aload 6
    //   88: astore 4
    //   90: aload 8
    //   92: astore 5
    //   94: aload 9
    //   96: ldc 81
    //   98: invokevirtual 84	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   101: aload_3
    //   102: astore_2
    //   103: aload 6
    //   105: astore 4
    //   107: aload 8
    //   109: astore 5
    //   111: aload 9
    //   113: ldc 86
    //   115: ldc 88
    //   117: invokevirtual 92	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   120: aload_3
    //   121: astore_2
    //   122: aload 6
    //   124: astore 4
    //   126: aload 8
    //   128: astore 5
    //   130: aload 9
    //   132: ldc 94
    //   134: ldc 96
    //   136: invokevirtual 92	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   139: aload_3
    //   140: astore_2
    //   141: aload 6
    //   143: astore 4
    //   145: aload 8
    //   147: astore 5
    //   149: aload 9
    //   151: ldc 98
    //   153: aload_0
    //   154: getfield 34	com/ab/download/AbDownloadThread:mDownFile	Lcom/ab/download/DownFile;
    //   157: invokevirtual 66	com/ab/download/DownFile:getDownUrl	()Ljava/lang/String;
    //   160: invokevirtual 92	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   163: aload_3
    //   164: astore_2
    //   165: aload 6
    //   167: astore 4
    //   169: aload 8
    //   171: astore 5
    //   173: aload 9
    //   175: ldc 100
    //   177: ldc 102
    //   179: invokevirtual 92	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   182: aload_3
    //   183: astore_2
    //   184: aload 6
    //   186: astore 4
    //   188: aload 8
    //   190: astore 5
    //   192: aload 9
    //   194: ldc 104
    //   196: new 106	java/lang/StringBuilder
    //   199: dup
    //   200: ldc 108
    //   202: invokespecial 109	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   205: aload_0
    //   206: getfield 34	com/ab/download/AbDownloadThread:mDownFile	Lcom/ab/download/DownFile;
    //   209: invokevirtual 57	com/ab/download/DownFile:getDownLength	()J
    //   212: invokevirtual 113	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   215: ldc 115
    //   217: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: aload_0
    //   221: getfield 34	com/ab/download/AbDownloadThread:mDownFile	Lcom/ab/download/DownFile;
    //   224: invokevirtual 60	com/ab/download/DownFile:getTotalLength	()J
    //   227: invokevirtual 113	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   230: invokevirtual 121	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   233: invokevirtual 92	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   236: aload_3
    //   237: astore_2
    //   238: aload 6
    //   240: astore 4
    //   242: aload 8
    //   244: astore 5
    //   246: aload 9
    //   248: ldc 123
    //   250: ldc 125
    //   252: invokevirtual 92	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   255: aload_3
    //   256: astore_2
    //   257: aload 6
    //   259: astore 4
    //   261: aload 8
    //   263: astore 5
    //   265: aload 9
    //   267: ldc 127
    //   269: ldc 129
    //   271: invokevirtual 92	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   274: aload_3
    //   275: astore_2
    //   276: aload 6
    //   278: astore 4
    //   280: aload 8
    //   282: astore 5
    //   284: aload 9
    //   286: invokevirtual 133	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   289: astore_3
    //   290: aload_3
    //   291: astore_2
    //   292: aload 6
    //   294: astore 4
    //   296: aload_3
    //   297: astore 5
    //   299: sipush 1024
    //   302: newarray byte
    //   304: astore 8
    //   306: aload_3
    //   307: astore_2
    //   308: aload 6
    //   310: astore 4
    //   312: aload_3
    //   313: astore 5
    //   315: new 135	java/io/RandomAccessFile
    //   318: dup
    //   319: aload_0
    //   320: getfield 40	com/ab/download/AbDownloadThread:saveFile	Ljava/io/File;
    //   323: ldc 137
    //   325: invokespecial 140	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   328: astore 6
    //   330: aload 6
    //   332: aload_0
    //   333: getfield 34	com/ab/download/AbDownloadThread:mDownFile	Lcom/ab/download/DownFile;
    //   336: invokevirtual 57	com/ab/download/DownFile:getDownLength	()J
    //   339: invokevirtual 144	java/io/RandomAccessFile:seek	(J)V
    //   342: aload_0
    //   343: getfield 38	com/ab/download/AbDownloadThread:flag	Z
    //   346: ifeq +19 -> 365
    //   349: aload_3
    //   350: aload 8
    //   352: iconst_0
    //   353: sipush 1024
    //   356: invokevirtual 150	java/io/InputStream:read	([BII)I
    //   359: istore_1
    //   360: iload_1
    //   361: iconst_m1
    //   362: if_icmpne +22 -> 384
    //   365: aload 6
    //   367: ifnull +8 -> 375
    //   370: aload 6
    //   372: invokevirtual 153	java/io/RandomAccessFile:close	()V
    //   375: aload_3
    //   376: ifnull +266 -> 642
    //   379: aload_3
    //   380: invokevirtual 154	java/io/InputStream:close	()V
    //   383: return
    //   384: getstatic 27	com/ab/download/AbDownloadThread:D	Z
    //   387: ifeq +25 -> 412
    //   390: ldc 10
    //   392: new 106	java/lang/StringBuilder
    //   395: dup
    //   396: ldc 156
    //   398: invokespecial 109	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   401: iload_1
    //   402: invokevirtual 159	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   405: invokevirtual 121	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   408: invokestatic 165	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   411: pop
    //   412: iload_1
    //   413: ifeq -71 -> 342
    //   416: aload 6
    //   418: aload 8
    //   420: iconst_0
    //   421: iload_1
    //   422: invokevirtual 169	java/io/RandomAccessFile:write	([BII)V
    //   425: aload_0
    //   426: getfield 34	com/ab/download/AbDownloadThread:mDownFile	Lcom/ab/download/DownFile;
    //   429: aload_0
    //   430: getfield 34	com/ab/download/AbDownloadThread:mDownFile	Lcom/ab/download/DownFile;
    //   433: invokevirtual 57	com/ab/download/DownFile:getDownLength	()J
    //   436: iload_1
    //   437: i2l
    //   438: ladd
    //   439: invokevirtual 172	com/ab/download/DownFile:setDownLength	(J)V
    //   442: getstatic 27	com/ab/download/AbDownloadThread:D	Z
    //   445: ifeq +46 -> 491
    //   448: ldc 10
    //   450: new 106	java/lang/StringBuilder
    //   453: dup
    //   454: ldc 174
    //   456: invokespecial 109	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   459: aload_0
    //   460: getfield 34	com/ab/download/AbDownloadThread:mDownFile	Lcom/ab/download/DownFile;
    //   463: invokevirtual 57	com/ab/download/DownFile:getDownLength	()J
    //   466: invokevirtual 113	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   469: ldc 176
    //   471: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   474: aload_0
    //   475: getfield 34	com/ab/download/AbDownloadThread:mDownFile	Lcom/ab/download/DownFile;
    //   478: invokevirtual 60	com/ab/download/DownFile:getTotalLength	()J
    //   481: invokevirtual 113	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   484: invokevirtual 121	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   487: invokestatic 165	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   490: pop
    //   491: aload_0
    //   492: getfield 42	com/ab/download/AbDownloadThread:downloader	Lcom/ab/download/AbFileDownloader;
    //   495: aload_0
    //   496: getfield 34	com/ab/download/AbDownloadThread:mDownFile	Lcom/ab/download/DownFile;
    //   499: invokevirtual 182	com/ab/download/AbFileDownloader:update	(Lcom/ab/download/DownFile;)V
    //   502: aload_0
    //   503: getfield 34	com/ab/download/AbDownloadThread:mDownFile	Lcom/ab/download/DownFile;
    //   506: invokevirtual 57	com/ab/download/DownFile:getDownLength	()J
    //   509: aload_0
    //   510: getfield 34	com/ab/download/AbDownloadThread:mDownFile	Lcom/ab/download/DownFile;
    //   513: invokevirtual 60	com/ab/download/DownFile:getTotalLength	()J
    //   516: lcmp
    //   517: ifne -175 -> 342
    //   520: aload_0
    //   521: iconst_1
    //   522: putfield 36	com/ab/download/AbDownloadThread:finish	Z
    //   525: aload_0
    //   526: iconst_0
    //   527: putfield 38	com/ab/download/AbDownloadThread:flag	Z
    //   530: goto -165 -> 365
    //   533: astore_2
    //   534: aload 6
    //   536: astore 5
    //   538: aload_2
    //   539: astore 6
    //   541: aload_3
    //   542: astore_2
    //   543: aload 5
    //   545: astore 4
    //   547: aload 6
    //   549: invokevirtual 185	java/lang/Exception:printStackTrace	()V
    //   552: aload_3
    //   553: astore_2
    //   554: aload 5
    //   556: astore 4
    //   558: aload_0
    //   559: getfield 34	com/ab/download/AbDownloadThread:mDownFile	Lcom/ab/download/DownFile;
    //   562: ldc2_w 186
    //   565: invokevirtual 172	com/ab/download/DownFile:setDownLength	(J)V
    //   568: aload 5
    //   570: ifnull +8 -> 578
    //   573: aload 5
    //   575: invokevirtual 153	java/io/RandomAccessFile:close	()V
    //   578: aload_3
    //   579: ifnull -196 -> 383
    //   582: aload_3
    //   583: invokevirtual 154	java/io/InputStream:close	()V
    //   586: return
    //   587: astore_2
    //   588: return
    //   589: astore_3
    //   590: aload 4
    //   592: ifnull +8 -> 600
    //   595: aload 4
    //   597: invokevirtual 153	java/io/RandomAccessFile:close	()V
    //   600: aload_2
    //   601: ifnull +7 -> 608
    //   604: aload_2
    //   605: invokevirtual 154	java/io/InputStream:close	()V
    //   608: aload_3
    //   609: athrow
    //   610: astore_2
    //   611: return
    //   612: astore_2
    //   613: goto -5 -> 608
    //   616: astore 5
    //   618: aload 6
    //   620: astore 4
    //   622: aload_3
    //   623: astore_2
    //   624: aload 5
    //   626: astore_3
    //   627: goto -37 -> 590
    //   630: astore 6
    //   632: aload 5
    //   634: astore_3
    //   635: aload 7
    //   637: astore 5
    //   639: goto -98 -> 541
    //   642: return
    //
    // Exception table:
    //   from	to	target	type
    //   330	342	533	java/lang/Exception
    //   342	360	533	java/lang/Exception
    //   384	412	533	java/lang/Exception
    //   416	491	533	java/lang/Exception
    //   491	530	533	java/lang/Exception
    //   573	578	587	java/io/IOException
    //   582	586	587	java/io/IOException
    //   44	66	589	finally
    //   76	84	589	finally
    //   94	101	589	finally
    //   111	120	589	finally
    //   130	139	589	finally
    //   149	163	589	finally
    //   173	182	589	finally
    //   192	236	589	finally
    //   246	255	589	finally
    //   265	274	589	finally
    //   284	290	589	finally
    //   299	306	589	finally
    //   315	330	589	finally
    //   547	552	589	finally
    //   558	568	589	finally
    //   370	375	610	java/io/IOException
    //   379	383	610	java/io/IOException
    //   595	600	612	java/io/IOException
    //   604	608	612	java/io/IOException
    //   330	342	616	finally
    //   342	360	616	finally
    //   384	412	616	finally
    //   416	491	616	finally
    //   491	530	616	finally
    //   44	66	630	java/lang/Exception
    //   76	84	630	java/lang/Exception
    //   94	101	630	java/lang/Exception
    //   111	120	630	java/lang/Exception
    //   130	139	630	java/lang/Exception
    //   149	163	630	java/lang/Exception
    //   173	182	630	java/lang/Exception
    //   192	236	630	java/lang/Exception
    //   246	255	630	java/lang/Exception
    //   265	274	630	java/lang/Exception
    //   284	290	630	java/lang/Exception
    //   299	306	630	java/lang/Exception
    //   315	330	630	java/lang/Exception
  }

  public void setFlag(boolean paramBoolean)
  {
    this.flag = paramBoolean;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.download.AbDownloadThread
 * JD-Core Version:    0.6.2
 */