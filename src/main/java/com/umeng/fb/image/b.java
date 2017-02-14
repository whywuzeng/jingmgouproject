package com.umeng.fb.image;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.umeng.fb.fragment.FeedbackFragment;
import java.io.IOException;
import java.io.InputStream;

public class b
{
  private static final String a = b.class.getName();

  private static int a(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    if (localDisplayMetrics.heightPixels > localDisplayMetrics.widthPixels)
      return localDisplayMetrics.heightPixels;
    return localDisplayMetrics.widthPixels;
  }

  public static int a(BitmapFactory.Options paramOptions, int paramInt)
  {
    int i = paramOptions.outHeight;
    int j = paramOptions.outWidth;
    float f = paramInt * 0.36F;
    if (i > j);
    for (f = i / f; ; f = j / f)
      return (int)f;
  }

  public static Bitmap a(Bitmap paramBitmap)
  {
    if (paramBitmap == null)
      localObject = null;
    int i;
    do
    {
      return localObject;
      i = paramBitmap.getHeight();
      localObject = paramBitmap;
    }
    while (paramBitmap.getRowBytes() * paramBitmap.getHeight() / 1024 / 1024 <= 15);
    Object localObject = new Matrix();
    ((Matrix)localObject).postScale(0.5F, 0.5F);
    localObject = Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), i, (Matrix)localObject, true);
    b(paramBitmap);
    return localObject;
  }

  public static Bitmap a(String paramString, int paramInt)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, localOptions);
    localOptions.inSampleSize = a(localOptions, paramInt);
    localOptions.inJustDecodeBounds = false;
    return BitmapFactory.decodeFile(paramString, localOptions);
  }

  public static void a(Context paramContext, final Uri paramUri, final String paramString)
  {
    new AsyncTask()
    {
      protected Boolean a(Void[] paramAnonymousArrayOfVoid)
      {
        return Boolean.valueOf(b.b(this.a, paramUri, paramString));
      }

      protected void a(Boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean.booleanValue())
        {
          paramAnonymousBoolean = new Message();
          paramAnonymousBoolean.what = 4;
          paramAnonymousBoolean.obj = paramString;
          FeedbackFragment.getHandler().sendMessage(paramAnonymousBoolean);
        }
      }
    }
    .execute(new Void[0]);
  }

  public static boolean a(Context paramContext, Uri paramUri)
  {
    Object localObject3 = null;
    Object localObject2 = null;
    Object localObject4 = paramContext.getContentResolver();
    paramContext = localObject2;
    Object localObject1 = localObject3;
    try
    {
      localObject4 = ((ContentResolver)localObject4).openInputStream(paramUri);
      paramContext = localObject2;
      localObject1 = localObject3;
      BitmapFactory.Options localOptions = new BitmapFactory.Options();
      paramContext = localObject2;
      localObject1 = localObject3;
      localOptions.inJustDecodeBounds = false;
      paramContext = localObject2;
      localObject1 = localObject3;
      localOptions.inSampleSize = 4;
      paramContext = localObject2;
      localObject1 = localObject3;
      paramUri = BitmapFactory.decodeStream((InputStream)localObject4, null, localOptions);
      paramContext = paramUri;
      localObject1 = paramUri;
      ((InputStream)localObject4).close();
      paramContext = paramUri;
      localObject1 = paramUri;
      if (localOptions.outWidth != -1)
      {
        paramContext = paramUri;
        localObject1 = paramUri;
        int i = localOptions.outHeight;
        if (i != -1);
      }
      else
      {
        b(paramUri);
        return false;
      }
      b(paramUri);
      return true;
    }
    catch (Exception paramUri)
    {
      b(paramContext);
      return false;
    }
    finally
    {
      b((Bitmap)localObject1);
    }
    throw paramContext;
  }

  private static Bitmap b(Context paramContext, Uri paramUri)
    throws IOException
  {
    Object localObject2 = null;
    while (true)
    {
      BitmapFactory.Options localOptions;
      try
      {
        ContentResolver localContentResolver = paramContext.getContentResolver();
        Object localObject1 = localContentResolver.openInputStream(paramUri);
        localOptions = new BitmapFactory.Options();
        localOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream((InputStream)localObject1, null, localOptions);
        ((InputStream)localObject1).close();
        localObject1 = localObject2;
        if (localOptions.outWidth != -1)
        {
          i = localOptions.outHeight;
          if (i == -1)
            localObject1 = localObject2;
        }
        else
        {
          return localObject1;
        }
        if (localOptions.outHeight > localOptions.outWidth)
        {
          i = localOptions.outHeight;
          int j = a(paramContext);
          if (i <= j)
            break label166;
          i /= j;
          localOptions.inJustDecodeBounds = false;
          localOptions.inSampleSize = i;
          paramContext = localContentResolver.openInputStream(paramUri);
          localObject1 = BitmapFactory.decodeStream(paramContext, null, localOptions);
          paramContext.close();
          continue;
        }
      }
      finally
      {
      }
      int i = localOptions.outWidth;
      continue;
      label166: i = 1;
    }
  }

  private static void b(Bitmap paramBitmap)
  {
    if ((paramBitmap != null) && (!paramBitmap.isRecycled()))
      paramBitmap.recycle();
  }

  // ERROR //
  private static boolean c(Context paramContext, Uri paramUri, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 6
    //   6: aconst_null
    //   7: astore 5
    //   9: new 159	java/io/File
    //   12: dup
    //   13: aload_0
    //   14: aload_2
    //   15: invokestatic 164	com/umeng/fb/util/c:b	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   18: invokespecial 167	java/io/File:<init>	(Ljava/lang/String;)V
    //   21: astore 7
    //   23: aload_0
    //   24: aload_1
    //   25: invokestatic 169	com/umeng/fb/image/b:b	(Landroid/content/Context;Landroid/net/Uri;)Landroid/graphics/Bitmap;
    //   28: invokestatic 171	com/umeng/fb/image/b:a	(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;
    //   31: astore_0
    //   32: aload_0
    //   33: astore_1
    //   34: aload 6
    //   36: astore_2
    //   37: new 173	java/io/FileOutputStream
    //   40: dup
    //   41: aload 7
    //   43: invokespecial 176	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   46: astore 4
    //   48: aload_0
    //   49: ifnull +19 -> 68
    //   52: aload_0
    //   53: getstatic 182	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   56: bipush 80
    //   58: aload 4
    //   60: invokevirtual 186	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   63: istore_3
    //   64: iload_3
    //   65: ifeq +3 -> 68
    //   68: aload_0
    //   69: invokestatic 92	com/umeng/fb/image/b:b	(Landroid/graphics/Bitmap;)V
    //   72: aload 4
    //   74: invokevirtual 187	java/io/FileOutputStream:close	()V
    //   77: iconst_1
    //   78: ireturn
    //   79: astore_0
    //   80: aload_0
    //   81: invokevirtual 190	java/io/IOException:printStackTrace	()V
    //   84: iconst_1
    //   85: ireturn
    //   86: astore 4
    //   88: aconst_null
    //   89: astore_0
    //   90: aload_0
    //   91: astore_1
    //   92: aload 5
    //   94: astore_2
    //   95: aload 7
    //   97: invokevirtual 193	java/io/File:delete	()Z
    //   100: pop
    //   101: aload_0
    //   102: astore_1
    //   103: aload 5
    //   105: astore_2
    //   106: aload 4
    //   108: invokevirtual 194	java/lang/Exception:printStackTrace	()V
    //   111: aload_0
    //   112: invokestatic 92	com/umeng/fb/image/b:b	(Landroid/graphics/Bitmap;)V
    //   115: aload 5
    //   117: invokevirtual 187	java/io/FileOutputStream:close	()V
    //   120: iconst_0
    //   121: ireturn
    //   122: astore_0
    //   123: aload_0
    //   124: invokevirtual 190	java/io/IOException:printStackTrace	()V
    //   127: iconst_0
    //   128: ireturn
    //   129: astore_1
    //   130: aconst_null
    //   131: astore_0
    //   132: aload 4
    //   134: astore_2
    //   135: aload_1
    //   136: astore 4
    //   138: aload_0
    //   139: invokestatic 92	com/umeng/fb/image/b:b	(Landroid/graphics/Bitmap;)V
    //   142: aload_2
    //   143: invokevirtual 187	java/io/FileOutputStream:close	()V
    //   146: aload 4
    //   148: athrow
    //   149: astore_0
    //   150: aload_0
    //   151: invokevirtual 190	java/io/IOException:printStackTrace	()V
    //   154: goto -8 -> 146
    //   157: astore 4
    //   159: aload_1
    //   160: astore_0
    //   161: goto -23 -> 138
    //   164: astore_1
    //   165: aload 4
    //   167: astore_2
    //   168: aload_1
    //   169: astore 4
    //   171: goto -33 -> 138
    //   174: astore 4
    //   176: goto -86 -> 90
    //   179: astore_1
    //   180: aload 4
    //   182: astore 5
    //   184: aload_1
    //   185: astore 4
    //   187: goto -97 -> 90
    //
    // Exception table:
    //   from	to	target	type
    //   72	77	79	java/io/IOException
    //   23	32	86	java/lang/Exception
    //   115	120	122	java/io/IOException
    //   23	32	129	finally
    //   142	146	149	java/io/IOException
    //   37	48	157	finally
    //   95	101	157	finally
    //   106	111	157	finally
    //   52	64	164	finally
    //   37	48	174	java/lang/Exception
    //   52	64	179	java/lang/Exception
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.image.b
 * JD-Core Version:    0.6.2
 */