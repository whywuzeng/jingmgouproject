package com.ismartgo.app.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BitmapUtils
{
  public static final int caculateInSampleSize(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
  {
    int k = paramOptions.outHeight;
    int j = paramOptions.outWidth;
    int i = 1;
    if ((paramInt1 == 0) || (paramInt2 == 0))
      return 1;
    if ((k > paramInt2) || (j > paramInt1))
    {
      i = Math.round(k / paramInt2);
      paramInt1 = Math.round(j / paramInt1);
      if (i >= paramInt1)
        break label63;
    }
    while (true)
    {
      return i;
      label63: i = paramInt1;
    }
  }

  public static final Bitmap compressBitmap(String paramString, int paramInt1, int paramInt2)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, localOptions);
    localOptions.inSampleSize = caculateInSampleSize(localOptions, paramInt1, paramInt2);
    localOptions.inJustDecodeBounds = false;
    return BitmapFactory.decodeFile(paramString, localOptions);
  }

  // ERROR //
  public static void copyFileUsingFileChannels(File paramFile1, File paramFile2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore_3
    //   5: aconst_null
    //   6: astore 7
    //   8: aconst_null
    //   9: astore 6
    //   11: aload 6
    //   13: astore_2
    //   14: aload 7
    //   16: astore 4
    //   18: new 50	java/io/FileInputStream
    //   21: dup
    //   22: aload_0
    //   23: invokespecial 53	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   26: invokevirtual 57	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   29: astore_0
    //   30: aload 6
    //   32: astore_2
    //   33: aload_0
    //   34: astore_3
    //   35: aload 7
    //   37: astore 4
    //   39: aload_0
    //   40: astore 5
    //   42: new 59	java/io/FileOutputStream
    //   45: dup
    //   46: aload_1
    //   47: invokespecial 60	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   50: invokevirtual 61	java/io/FileOutputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   53: astore_1
    //   54: aload_1
    //   55: astore_2
    //   56: aload_0
    //   57: astore_3
    //   58: aload_1
    //   59: astore 4
    //   61: aload_0
    //   62: astore 5
    //   64: aload_1
    //   65: aload_0
    //   66: lconst_0
    //   67: aload_0
    //   68: invokevirtual 67	java/nio/channels/FileChannel:size	()J
    //   71: invokevirtual 71	java/nio/channels/FileChannel:transferFrom	(Ljava/nio/channels/ReadableByteChannel;JJ)J
    //   74: pop2
    //   75: aload_0
    //   76: invokevirtual 74	java/nio/channels/FileChannel:close	()V
    //   79: aload_1
    //   80: invokevirtual 74	java/nio/channels/FileChannel:close	()V
    //   83: return
    //   84: astore_0
    //   85: aload_2
    //   86: astore 4
    //   88: aload_3
    //   89: astore 5
    //   91: aload_0
    //   92: invokevirtual 77	java/io/IOException:printStackTrace	()V
    //   95: aload_2
    //   96: astore_1
    //   97: aload_3
    //   98: astore_0
    //   99: goto -24 -> 75
    //   102: astore_0
    //   103: aload 5
    //   105: invokevirtual 74	java/nio/channels/FileChannel:close	()V
    //   108: aload 4
    //   110: invokevirtual 74	java/nio/channels/FileChannel:close	()V
    //   113: aload_0
    //   114: athrow
    //   115: astore_1
    //   116: aload_1
    //   117: invokevirtual 77	java/io/IOException:printStackTrace	()V
    //   120: goto -7 -> 113
    //   123: astore_0
    //   124: aload_0
    //   125: invokevirtual 77	java/io/IOException:printStackTrace	()V
    //   128: return
    //
    // Exception table:
    //   from	to	target	type
    //   18	30	84	java/io/IOException
    //   42	54	84	java/io/IOException
    //   64	75	84	java/io/IOException
    //   18	30	102	finally
    //   42	54	102	finally
    //   64	75	102	finally
    //   91	95	102	finally
    //   103	113	115	java/io/IOException
    //   75	83	123	java/io/IOException
  }

  public static Uri createImageFile()
  {
    Object localObject2 = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + "_";
    File localFile = Environment.getExternalStoragePublicDirectory("temp");
    if (!localFile.exists())
      localFile.mkdirs();
    Object localObject1 = null;
    try
    {
      localObject2 = File.createTempFile((String)localObject2, ".jpg", localFile);
      localObject1 = localObject2;
      return Uri.fromFile(localObject1);
    }
    catch (IOException localIOException)
    {
      while (true)
        localIOException.printStackTrace();
    }
  }

  public static Bitmap getBitmap(InputStream paramInputStream, int paramInt)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inSampleSize = paramInt;
    return BitmapFactory.decodeStream(paramInputStream, null, localOptions);
  }

  public static Bitmap getBitmap(String paramString, int paramInt)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inSampleSize = paramInt;
    return BitmapFactory.decodeFile(paramString, localOptions);
  }

  private static int readPictureDegree(String paramString)
  {
    try
    {
      int i = new ExifInterface(paramString).getAttributeInt("Orientation", 1);
      switch (i)
      {
      case 4:
      case 5:
      case 7:
      default:
        return 0;
      case 6:
        return 90;
      case 3:
        return 180;
      case 8:
      }
      return 270;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return 0;
  }

  private static Bitmap rotaingImageView(int paramInt, Bitmap paramBitmap)
  {
    Matrix localMatrix = new Matrix();
    localMatrix.postRotate(paramInt);
    return Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), localMatrix, true);
  }

  // ERROR //
  public static File scal(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 184	com/ismartgo/app/common/BitmapUtils:readPictureDegree	(Ljava/lang/String;)I
    //   4: istore_3
    //   5: ldc 186
    //   7: new 81	java/lang/StringBuilder
    //   10: dup
    //   11: ldc 188
    //   13: invokespecial 102	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   16: iload_3
    //   17: invokevirtual 191	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   20: invokevirtual 112	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   23: invokestatic 197	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   26: pop
    //   27: new 122	java/io/File
    //   30: dup
    //   31: aload_0
    //   32: invokespecial 198	java/io/File:<init>	(Ljava/lang/String;)V
    //   35: astore 8
    //   37: aload 8
    //   39: invokevirtual 201	java/io/File:length	()J
    //   42: lstore 6
    //   44: lload 6
    //   46: ldc2_w 202
    //   49: lcmp
    //   50: iflt +179 -> 229
    //   53: new 13	android/graphics/BitmapFactory$Options
    //   56: dup
    //   57: invokespecial 29	android/graphics/BitmapFactory$Options:<init>	()V
    //   60: astore 8
    //   62: aload 8
    //   64: iconst_1
    //   65: putfield 33	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
    //   68: aload_0
    //   69: aload 8
    //   71: invokestatic 39	android/graphics/BitmapFactory:decodeFile	(Ljava/lang/String;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   74: pop
    //   75: aload 8
    //   77: getfield 17	android/graphics/BitmapFactory$Options:outHeight	I
    //   80: istore 4
    //   82: aload 8
    //   84: getfield 20	android/graphics/BitmapFactory$Options:outWidth	I
    //   87: istore 5
    //   89: lload 6
    //   91: l2f
    //   92: ldc 204
    //   94: fdiv
    //   95: f2d
    //   96: invokestatic 208	java/lang/Math:sqrt	(D)D
    //   99: dstore_1
    //   100: aload 8
    //   102: iload 4
    //   104: i2d
    //   105: dload_1
    //   106: ddiv
    //   107: d2i
    //   108: putfield 17	android/graphics/BitmapFactory$Options:outHeight	I
    //   111: aload 8
    //   113: iload 5
    //   115: i2d
    //   116: dload_1
    //   117: ddiv
    //   118: d2i
    //   119: putfield 20	android/graphics/BitmapFactory$Options:outWidth	I
    //   122: aload 8
    //   124: dconst_1
    //   125: dload_1
    //   126: dadd
    //   127: d2i
    //   128: putfield 44	android/graphics/BitmapFactory$Options:inSampleSize	I
    //   131: aload 8
    //   133: iconst_0
    //   134: putfield 33	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
    //   137: iload_3
    //   138: aload_0
    //   139: aload 8
    //   141: invokestatic 39	android/graphics/BitmapFactory:decodeFile	(Ljava/lang/String;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   144: invokestatic 210	com/ismartgo/app/common/BitmapUtils:rotaingImageView	(ILandroid/graphics/Bitmap;)Landroid/graphics/Bitmap;
    //   147: astore 9
    //   149: new 122	java/io/File
    //   152: dup
    //   153: invokestatic 212	com/ismartgo/app/common/BitmapUtils:createImageFile	()Landroid/net/Uri;
    //   156: invokevirtual 215	android/net/Uri:getPath	()Ljava/lang/String;
    //   159: invokespecial 198	java/io/File:<init>	(Ljava/lang/String;)V
    //   162: astore 8
    //   164: new 59	java/io/FileOutputStream
    //   167: dup
    //   168: aload 8
    //   170: invokespecial 60	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   173: astore_0
    //   174: aload 9
    //   176: getstatic 221	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   179: bipush 50
    //   181: aload_0
    //   182: invokevirtual 225	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   185: pop
    //   186: aload_0
    //   187: invokevirtual 226	java/io/FileOutputStream:close	()V
    //   190: ldc 186
    //   192: new 81	java/lang/StringBuilder
    //   195: dup
    //   196: ldc 228
    //   198: invokespecial 102	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   201: aload 8
    //   203: invokevirtual 201	java/io/File:length	()J
    //   206: invokevirtual 231	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   209: invokevirtual 112	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   212: invokestatic 197	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   215: pop
    //   216: aload 9
    //   218: invokevirtual 234	android/graphics/Bitmap:isRecycled	()Z
    //   221: ifne +19 -> 240
    //   224: aload 9
    //   226: invokevirtual 237	android/graphics/Bitmap:recycle	()V
    //   229: aload 8
    //   231: areturn
    //   232: astore_0
    //   233: aload_0
    //   234: invokevirtual 77	java/io/IOException:printStackTrace	()V
    //   237: goto -47 -> 190
    //   240: new 122	java/io/File
    //   243: dup
    //   244: invokestatic 212	com/ismartgo/app/common/BitmapUtils:createImageFile	()Landroid/net/Uri;
    //   247: invokevirtual 215	android/net/Uri:getPath	()Ljava/lang/String;
    //   250: invokespecial 198	java/io/File:<init>	(Ljava/lang/String;)V
    //   253: astore_0
    //   254: aload 8
    //   256: aload_0
    //   257: invokestatic 239	com/ismartgo/app/common/BitmapUtils:copyFileUsingFileChannels	(Ljava/io/File;Ljava/io/File;)V
    //   260: aload_0
    //   261: areturn
    //   262: astore_0
    //   263: goto -30 -> 233
    //
    // Exception table:
    //   from	to	target	type
    //   164	174	232	java/io/IOException
    //   174	190	262	java/io/IOException
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.common.BitmapUtils
 * JD-Core Version:    0.6.2
 */