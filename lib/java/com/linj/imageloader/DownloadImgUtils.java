package com.linj.imageloader;

public class DownloadImgUtils
{
  // ERROR //
  public static android.graphics.Bitmap downloadImgByUrl(java.lang.String paramString, android.widget.ImageView paramImageView)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore_3
    //   5: aload 4
    //   7: astore_2
    //   8: new 17	java/net/URL
    //   11: dup
    //   12: aload_0
    //   13: invokespecial 20	java/net/URL:<init>	(Ljava/lang/String;)V
    //   16: invokevirtual 24	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   19: checkcast 26	java/net/HttpURLConnection
    //   22: astore 5
    //   24: aload 4
    //   26: astore_2
    //   27: new 28	java/io/BufferedInputStream
    //   30: dup
    //   31: aload 5
    //   33: invokevirtual 32	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   36: invokespecial 35	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   39: astore_0
    //   40: aload_0
    //   41: aload_0
    //   42: invokevirtual 41	java/io/InputStream:available	()I
    //   45: invokevirtual 45	java/io/InputStream:mark	(I)V
    //   48: new 47	android/graphics/BitmapFactory$Options
    //   51: dup
    //   52: invokespecial 48	android/graphics/BitmapFactory$Options:<init>	()V
    //   55: astore_2
    //   56: aload_2
    //   57: iconst_1
    //   58: putfield 52	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
    //   61: aload_0
    //   62: aconst_null
    //   63: aload_2
    //   64: invokestatic 58	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   67: pop
    //   68: aload_1
    //   69: invokestatic 64	com/linj/imageloader/ImageSizeUtil:getImageViewSize	(Landroid/widget/ImageView;)Lcom/linj/imageloader/ImageSizeUtil$ImageSize;
    //   72: astore_1
    //   73: aload_2
    //   74: aload_2
    //   75: aload_1
    //   76: getfield 70	com/linj/imageloader/ImageSizeUtil$ImageSize:width	I
    //   79: aload_1
    //   80: getfield 73	com/linj/imageloader/ImageSizeUtil$ImageSize:height	I
    //   83: invokestatic 77	com/linj/imageloader/ImageSizeUtil:caculateInSampleSize	(Landroid/graphics/BitmapFactory$Options;II)I
    //   86: putfield 80	android/graphics/BitmapFactory$Options:inSampleSize	I
    //   89: aload_2
    //   90: iconst_0
    //   91: putfield 52	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
    //   94: aload_0
    //   95: invokevirtual 83	java/io/InputStream:reset	()V
    //   98: aload_0
    //   99: aconst_null
    //   100: aload_2
    //   101: invokestatic 58	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   104: astore_1
    //   105: aload 5
    //   107: invokevirtual 86	java/net/HttpURLConnection:disconnect	()V
    //   110: aload_0
    //   111: ifnull +7 -> 118
    //   114: aload_0
    //   115: invokevirtual 89	java/io/InputStream:close	()V
    //   118: iconst_0
    //   119: ifeq +11 -> 130
    //   122: new 91	java/lang/NullPointerException
    //   125: dup
    //   126: invokespecial 92	java/lang/NullPointerException:<init>	()V
    //   129: athrow
    //   130: aload_1
    //   131: areturn
    //   132: astore_1
    //   133: aload_3
    //   134: astore_0
    //   135: aload_0
    //   136: astore_2
    //   137: aload_1
    //   138: invokevirtual 95	java/lang/Exception:printStackTrace	()V
    //   141: aload_0
    //   142: ifnull +7 -> 149
    //   145: aload_0
    //   146: invokevirtual 89	java/io/InputStream:close	()V
    //   149: iconst_0
    //   150: ifeq +11 -> 161
    //   153: new 91	java/lang/NullPointerException
    //   156: dup
    //   157: invokespecial 92	java/lang/NullPointerException:<init>	()V
    //   160: athrow
    //   161: aconst_null
    //   162: areturn
    //   163: astore_0
    //   164: aload_2
    //   165: ifnull +7 -> 172
    //   168: aload_2
    //   169: invokevirtual 89	java/io/InputStream:close	()V
    //   172: iconst_0
    //   173: ifeq +11 -> 184
    //   176: new 91	java/lang/NullPointerException
    //   179: dup
    //   180: invokespecial 92	java/lang/NullPointerException:<init>	()V
    //   183: athrow
    //   184: aload_0
    //   185: athrow
    //   186: astore_0
    //   187: goto -69 -> 118
    //   190: astore_0
    //   191: goto -61 -> 130
    //   194: astore_0
    //   195: goto -46 -> 149
    //   198: astore_0
    //   199: goto -38 -> 161
    //   202: astore_1
    //   203: goto -31 -> 172
    //   206: astore_1
    //   207: goto -23 -> 184
    //   210: astore_1
    //   211: aload_0
    //   212: astore_2
    //   213: aload_1
    //   214: astore_0
    //   215: goto -51 -> 164
    //   218: astore_1
    //   219: goto -84 -> 135
    //
    // Exception table:
    //   from	to	target	type
    //   8	24	132	java/lang/Exception
    //   27	40	132	java/lang/Exception
    //   8	24	163	finally
    //   27	40	163	finally
    //   137	141	163	finally
    //   114	118	186	java/io/IOException
    //   122	130	190	java/io/IOException
    //   145	149	194	java/io/IOException
    //   153	161	198	java/io/IOException
    //   168	172	202	java/io/IOException
    //   176	184	206	java/io/IOException
    //   40	110	210	finally
    //   40	110	218	java/lang/Exception
  }

  // ERROR //
  public static boolean downloadImgByUrl(java.lang.String paramString, java.io.File paramFile)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: aconst_null
    //   3: astore 8
    //   5: aconst_null
    //   6: astore 7
    //   8: aconst_null
    //   9: astore 6
    //   11: aconst_null
    //   12: astore 4
    //   14: aload 8
    //   16: astore 5
    //   18: new 17	java/net/URL
    //   21: dup
    //   22: aload_0
    //   23: invokespecial 20	java/net/URL:<init>	(Ljava/lang/String;)V
    //   26: invokevirtual 24	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   29: checkcast 26	java/net/HttpURLConnection
    //   32: invokevirtual 32	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   35: astore_0
    //   36: aload 8
    //   38: astore 5
    //   40: aload_0
    //   41: astore 4
    //   43: aload_0
    //   44: astore 6
    //   46: new 98	java/io/FileOutputStream
    //   49: dup
    //   50: aload_1
    //   51: invokespecial 101	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   54: astore_1
    //   55: sipush 512
    //   58: newarray byte
    //   60: astore 4
    //   62: aload_0
    //   63: aload 4
    //   65: invokevirtual 105	java/io/InputStream:read	([B)I
    //   68: istore_2
    //   69: iload_2
    //   70: iconst_m1
    //   71: if_icmpne +27 -> 98
    //   74: aload_1
    //   75: invokevirtual 108	java/io/FileOutputStream:flush	()V
    //   78: aload_0
    //   79: ifnull +7 -> 86
    //   82: aload_0
    //   83: invokevirtual 89	java/io/InputStream:close	()V
    //   86: aload_1
    //   87: ifnull +7 -> 94
    //   90: aload_1
    //   91: invokevirtual 109	java/io/FileOutputStream:close	()V
    //   94: iconst_1
    //   95: istore_3
    //   96: iload_3
    //   97: ireturn
    //   98: aload_1
    //   99: aload 4
    //   101: iconst_0
    //   102: iload_2
    //   103: invokevirtual 113	java/io/FileOutputStream:write	([BII)V
    //   106: goto -44 -> 62
    //   109: astore 4
    //   111: aload_0
    //   112: astore 6
    //   114: aload_1
    //   115: astore_0
    //   116: aload 4
    //   118: astore_1
    //   119: aload_0
    //   120: astore 5
    //   122: aload 6
    //   124: astore 4
    //   126: aload_1
    //   127: invokevirtual 95	java/lang/Exception:printStackTrace	()V
    //   130: aload 6
    //   132: ifnull +8 -> 140
    //   135: aload 6
    //   137: invokevirtual 89	java/io/InputStream:close	()V
    //   140: aload_0
    //   141: ifnull -45 -> 96
    //   144: aload_0
    //   145: invokevirtual 109	java/io/FileOutputStream:close	()V
    //   148: iconst_0
    //   149: ireturn
    //   150: astore_0
    //   151: iconst_0
    //   152: ireturn
    //   153: astore_0
    //   154: aload 4
    //   156: ifnull +8 -> 164
    //   159: aload 4
    //   161: invokevirtual 89	java/io/InputStream:close	()V
    //   164: aload 5
    //   166: ifnull +8 -> 174
    //   169: aload 5
    //   171: invokevirtual 109	java/io/FileOutputStream:close	()V
    //   174: aload_0
    //   175: athrow
    //   176: astore_0
    //   177: goto -91 -> 86
    //   180: astore_0
    //   181: goto -87 -> 94
    //   184: astore_1
    //   185: goto -45 -> 140
    //   188: astore_1
    //   189: goto -25 -> 164
    //   192: astore_1
    //   193: goto -19 -> 174
    //   196: astore 6
    //   198: aload_1
    //   199: astore 5
    //   201: aload_0
    //   202: astore 4
    //   204: aload 6
    //   206: astore_0
    //   207: goto -53 -> 154
    //   210: astore_1
    //   211: aload 7
    //   213: astore_0
    //   214: goto -95 -> 119
    //
    // Exception table:
    //   from	to	target	type
    //   55	62	109	java/lang/Exception
    //   62	69	109	java/lang/Exception
    //   74	78	109	java/lang/Exception
    //   98	106	109	java/lang/Exception
    //   144	148	150	java/io/IOException
    //   18	36	153	finally
    //   46	55	153	finally
    //   126	130	153	finally
    //   82	86	176	java/io/IOException
    //   90	94	180	java/io/IOException
    //   135	140	184	java/io/IOException
    //   159	164	188	java/io/IOException
    //   169	174	192	java/io/IOException
    //   55	62	196	finally
    //   62	69	196	finally
    //   74	78	196	finally
    //   98	106	196	finally
    //   18	36	210	java/lang/Exception
    //   46	55	210	java/lang/Exception
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.linj.imageloader.DownloadImgUtils
 * JD-Core Version:    0.6.2
 */