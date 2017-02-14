package com.ab.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import com.ab.global.AbAppData;
import com.ab.util.dct.FDCT;
import java.io.File;

public class AbImageUtil
{
  private static final boolean D = AbAppData.DEBUG;
  private static String TAG = "AbImageUtil";

  // ERROR //
  public static byte[] bitmap2Bytes(Bitmap paramBitmap, Bitmap.CompressFormat paramCompressFormat, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 7
    //   3: aconst_null
    //   4: astore 6
    //   6: aconst_null
    //   7: astore_3
    //   8: aconst_null
    //   9: astore 5
    //   11: new 31	java/io/ByteArrayOutputStream
    //   14: dup
    //   15: invokespecial 32	java/io/ByteArrayOutputStream:<init>	()V
    //   18: astore 4
    //   20: aload 7
    //   22: astore_3
    //   23: aload_0
    //   24: aload_1
    //   25: bipush 100
    //   27: aload 4
    //   29: invokevirtual 38	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   32: pop
    //   33: aload 7
    //   35: astore_3
    //   36: aload 4
    //   38: invokevirtual 42	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   41: astore_1
    //   42: iload_2
    //   43: ifeq +9 -> 52
    //   46: aload_1
    //   47: astore_3
    //   48: aload_0
    //   49: invokevirtual 45	android/graphics/Bitmap:recycle	()V
    //   52: aload 4
    //   54: ifnull +70 -> 124
    //   57: aload 4
    //   59: invokevirtual 48	java/io/ByteArrayOutputStream:close	()V
    //   62: aload_1
    //   63: astore_3
    //   64: aload_3
    //   65: areturn
    //   66: astore 4
    //   68: aload 6
    //   70: astore_0
    //   71: aload 5
    //   73: astore_1
    //   74: aload_1
    //   75: astore_3
    //   76: aload 4
    //   78: invokevirtual 51	java/lang/Exception:printStackTrace	()V
    //   81: aload_0
    //   82: astore_3
    //   83: aload_1
    //   84: ifnull -20 -> 64
    //   87: aload_1
    //   88: invokevirtual 48	java/io/ByteArrayOutputStream:close	()V
    //   91: aload_0
    //   92: areturn
    //   93: astore_1
    //   94: aload_1
    //   95: invokevirtual 51	java/lang/Exception:printStackTrace	()V
    //   98: aload_0
    //   99: areturn
    //   100: astore_0
    //   101: aload_3
    //   102: ifnull +7 -> 109
    //   105: aload_3
    //   106: invokevirtual 48	java/io/ByteArrayOutputStream:close	()V
    //   109: aload_0
    //   110: athrow
    //   111: astore_1
    //   112: aload_1
    //   113: invokevirtual 51	java/lang/Exception:printStackTrace	()V
    //   116: goto -7 -> 109
    //   119: astore_0
    //   120: aload_0
    //   121: invokevirtual 51	java/lang/Exception:printStackTrace	()V
    //   124: aload_1
    //   125: areturn
    //   126: astore_0
    //   127: aload 4
    //   129: astore_3
    //   130: goto -29 -> 101
    //   133: astore_0
    //   134: aload 4
    //   136: astore_1
    //   137: aload_0
    //   138: astore 4
    //   140: aload_3
    //   141: astore_0
    //   142: goto -68 -> 74
    //
    // Exception table:
    //   from	to	target	type
    //   11	20	66	java/lang/Exception
    //   87	91	93	java/lang/Exception
    //   11	20	100	finally
    //   76	81	100	finally
    //   105	109	111	java/lang/Exception
    //   57	62	119	java/lang/Exception
    //   23	33	126	finally
    //   36	42	126	finally
    //   48	52	126	finally
    //   23	33	133	java/lang/Exception
    //   36	42	133	java/lang/Exception
    //   48	52	133	java/lang/Exception
  }

  public static Drawable bitmapToDrawable(Bitmap paramBitmap)
  {
    Object localObject = null;
    if (paramBitmap == null)
      return null;
    try
    {
      paramBitmap = new BitmapDrawable(paramBitmap);
      return paramBitmap;
    }
    catch (Exception paramBitmap)
    {
      while (true)
      {
        paramBitmap.printStackTrace();
        paramBitmap = localObject;
      }
    }
  }

  public static TransitionDrawable bitmapToTransitionDrawable(Bitmap paramBitmap)
  {
    Object localObject = null;
    if (paramBitmap == null)
      return null;
    try
    {
      paramBitmap = new TransitionDrawable(new Drawable[] { new ColorDrawable(17170445), new BitmapDrawable(paramBitmap) });
      return paramBitmap;
    }
    catch (Exception paramBitmap)
    {
      while (true)
      {
        paramBitmap.printStackTrace();
        paramBitmap = localObject;
      }
    }
  }

  public static Bitmap bytes2Bimap(byte[] paramArrayOfByte)
  {
    Bitmap localBitmap = null;
    try
    {
      if (paramArrayOfByte.length != 0)
        localBitmap = BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length);
      return localBitmap;
    }
    catch (Exception paramArrayOfByte)
    {
      paramArrayOfByte.printStackTrace();
    }
    return null;
  }

  public static Bitmap cutImg(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    Object localObject1;
    if (paramBitmap == null)
      localObject1 = null;
    while (true)
    {
      return localObject1;
      if ((paramInt1 <= 0) || (paramInt2 <= 0))
        throw new IllegalArgumentException("裁剪图片的宽高设置不能小于0");
      localObject1 = null;
      try
      {
        int i = paramBitmap.getWidth();
        int j = paramBitmap.getHeight();
        if ((i <= 0) || (j <= 0))
          return null;
        int m = 0;
        int k = 0;
        if (i > paramInt1)
        {
          m = (i - paramInt1) / 2;
          i = paramInt1;
          paramInt1 = m;
          label84: if (j <= paramInt2)
            break label138;
          k = (j - paramInt2) / 2;
          j = paramInt2;
        }
        label138: for (paramInt2 = k; ; paramInt2 = k)
        {
          Bitmap localBitmap = Bitmap.createBitmap(paramBitmap, paramInt1, paramInt2, i, j);
          localObject1 = localBitmap;
          if (localBitmap == paramBitmap)
            break;
          paramBitmap.recycle();
          return localBitmap;
          paramInt1 = m;
          break label84;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return null;
      }
      finally
      {
        if (paramBitmap != null)
          paramBitmap.recycle();
      }
    }
  }

  public static Bitmap cutImg(File paramFile, int paramInt1, int paramInt2)
  {
    Object localObject1 = null;
    if ((paramInt1 <= 0) || (paramInt2 <= 0))
      throw new IllegalArgumentException("裁剪图片的宽高设置不能小于0");
    Object localObject2 = new BitmapFactory.Options();
    ((BitmapFactory.Options)localObject2).inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramFile.getPath(), (BitmapFactory.Options)localObject2);
    int m = ((BitmapFactory.Options)localObject2).outWidth;
    int k = ((BitmapFactory.Options)localObject2).outHeight;
    int j = 0;
    int i = 0;
    int i1 = paramInt1 * 2;
    int n = paramInt2 * 2;
    double d = 0.0D;
    if ((m < i1) || (k < n))
    {
      d = 0.0D;
      j = m;
      i = k;
      ((BitmapFactory.Options)localObject2).inPreferredConfig = Bitmap.Config.RGB_565;
      ((BitmapFactory.Options)localObject2).inPurgeable = true;
      ((BitmapFactory.Options)localObject2).inInputShareable = true;
      if (d <= 1.0D)
        break label250;
    }
    label250: for (((BitmapFactory.Options)localObject2).inSampleSize = ((int)d); ; ((BitmapFactory.Options)localObject2).inSampleSize = 1)
    {
      ((BitmapFactory.Options)localObject2).outHeight = i;
      ((BitmapFactory.Options)localObject2).outWidth = j;
      ((BitmapFactory.Options)localObject2).inJustDecodeBounds = false;
      ((BitmapFactory.Options)localObject2).inDither = false;
      localObject2 = BitmapFactory.decodeFile(paramFile.getPath(), (BitmapFactory.Options)localObject2);
      paramFile = localObject1;
      if (localObject2 != null)
        paramFile = cutImg((Bitmap)localObject2, paramInt1, paramInt2);
      return paramFile;
      if (m > i1)
      {
        d = m / i1;
        j = i1;
        i = (int)(k / d);
        break;
      }
      if (k <= n)
        break;
      d = k / n;
      i = n;
      j = (int)(m / d);
      break;
    }
  }

  public static Bitmap drawableToBitmap(Drawable paramDrawable)
  {
    int i = paramDrawable.getIntrinsicWidth();
    int j = paramDrawable.getIntrinsicHeight();
    if (paramDrawable.getOpacity() != -1);
    for (Object localObject = Bitmap.Config.ARGB_8888; ; localObject = Bitmap.Config.RGB_565)
    {
      localObject = Bitmap.createBitmap(i, j, (Bitmap.Config)localObject);
      Canvas localCanvas = new Canvas((Bitmap)localObject);
      paramDrawable.setBounds(0, 0, paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight());
      paramDrawable.draw(localCanvas);
      return localObject;
    }
  }

  public static TransitionDrawable drawableToTransitionDrawable(Drawable paramDrawable)
  {
    Object localObject = null;
    if (paramDrawable == null)
      return null;
    try
    {
      paramDrawable = new TransitionDrawable(new Drawable[] { new ColorDrawable(17170445), paramDrawable });
      return paramDrawable;
    }
    catch (Exception paramDrawable)
    {
      while (true)
      {
        paramDrawable.printStackTrace();
        paramDrawable = localObject;
      }
    }
  }

  // ERROR //
  public static Bitmap getBitmapFormURL(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 7
    //   3: aconst_null
    //   4: astore 8
    //   6: aconst_null
    //   7: astore 6
    //   9: aload 6
    //   11: astore 4
    //   13: aload 8
    //   15: astore 5
    //   17: new 182	java/net/URL
    //   20: dup
    //   21: aload_0
    //   22: invokespecial 183	java/net/URL:<init>	(Ljava/lang/String;)V
    //   25: invokevirtual 187	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   28: astore_0
    //   29: aload 6
    //   31: astore 4
    //   33: aload 8
    //   35: astore 5
    //   37: aload_0
    //   38: iconst_1
    //   39: invokevirtual 193	java/net/URLConnection:setDoInput	(Z)V
    //   42: aload 6
    //   44: astore 4
    //   46: aload 8
    //   48: astore 5
    //   50: aload_0
    //   51: invokevirtual 196	java/net/URLConnection:connect	()V
    //   54: aload 6
    //   56: astore 4
    //   58: aload 8
    //   60: astore 5
    //   62: aload_0
    //   63: invokevirtual 200	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   66: astore 6
    //   68: aload 6
    //   70: astore 4
    //   72: aload 6
    //   74: astore 5
    //   76: aload 6
    //   78: aconst_null
    //   79: aconst_null
    //   80: invokestatic 204	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   83: astore_0
    //   84: iload_1
    //   85: ifne +37 -> 122
    //   88: aload 6
    //   90: astore 4
    //   92: aload 6
    //   94: astore 5
    //   96: aload_0
    //   97: iload_2
    //   98: iload_3
    //   99: invokestatic 148	com/ab/util/AbImageUtil:cutImg	(Landroid/graphics/Bitmap;II)Landroid/graphics/Bitmap;
    //   102: astore_0
    //   103: aload_0
    //   104: astore 5
    //   106: aload 6
    //   108: ifnull +11 -> 119
    //   111: aload 6
    //   113: invokevirtual 207	java/io/InputStream:close	()V
    //   116: aload_0
    //   117: astore 5
    //   119: aload 5
    //   121: areturn
    //   122: iload_1
    //   123: iconst_1
    //   124: if_icmpne +21 -> 145
    //   127: aload 6
    //   129: astore 4
    //   131: aload 6
    //   133: astore 5
    //   135: aload_0
    //   136: iload_2
    //   137: iload_3
    //   138: invokestatic 210	com/ab/util/AbImageUtil:scaleImg	(Landroid/graphics/Bitmap;II)Landroid/graphics/Bitmap;
    //   141: astore_0
    //   142: goto -39 -> 103
    //   145: goto -42 -> 103
    //   148: astore_0
    //   149: aload 4
    //   151: astore 5
    //   153: getstatic 21	com/ab/util/AbImageUtil:D	Z
    //   156: ifeq +31 -> 187
    //   159: aload 4
    //   161: astore 5
    //   163: getstatic 14	com/ab/util/AbImageUtil:TAG	Ljava/lang/String;
    //   166: new 212	java/lang/StringBuilder
    //   169: dup
    //   170: invokespecial 213	java/lang/StringBuilder:<init>	()V
    //   173: aload_0
    //   174: invokevirtual 216	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   177: invokevirtual 220	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   180: invokevirtual 223	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   183: invokestatic 229	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   186: pop
    //   187: aload 7
    //   189: astore 5
    //   191: aload 4
    //   193: ifnull -74 -> 119
    //   196: aload 4
    //   198: invokevirtual 207	java/io/InputStream:close	()V
    //   201: aconst_null
    //   202: areturn
    //   203: astore_0
    //   204: aload_0
    //   205: invokevirtual 51	java/lang/Exception:printStackTrace	()V
    //   208: aconst_null
    //   209: areturn
    //   210: astore_0
    //   211: aload 5
    //   213: ifnull +8 -> 221
    //   216: aload 5
    //   218: invokevirtual 207	java/io/InputStream:close	()V
    //   221: aload_0
    //   222: athrow
    //   223: astore 4
    //   225: aload 4
    //   227: invokevirtual 51	java/lang/Exception:printStackTrace	()V
    //   230: goto -9 -> 221
    //   233: astore 4
    //   235: aload 4
    //   237: invokevirtual 51	java/lang/Exception:printStackTrace	()V
    //   240: aload_0
    //   241: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   17	29	148	java/lang/Exception
    //   37	42	148	java/lang/Exception
    //   50	54	148	java/lang/Exception
    //   62	68	148	java/lang/Exception
    //   76	84	148	java/lang/Exception
    //   96	103	148	java/lang/Exception
    //   135	142	148	java/lang/Exception
    //   196	201	203	java/lang/Exception
    //   17	29	210	finally
    //   37	42	210	finally
    //   50	54	210	finally
    //   62	68	210	finally
    //   76	84	210	finally
    //   96	103	210	finally
    //   135	142	210	finally
    //   153	159	210	finally
    //   163	187	210	finally
    //   216	221	223	java/lang/Exception
    //   111	116	233	java/lang/Exception
  }

  // ERROR //
  public static int getByteCount(Bitmap paramBitmap, Bitmap.CompressFormat paramCompressFormat)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: aconst_null
    //   3: astore 4
    //   5: aconst_null
    //   6: astore 6
    //   8: new 31	java/io/ByteArrayOutputStream
    //   11: dup
    //   12: invokespecial 32	java/io/ByteArrayOutputStream:<init>	()V
    //   15: astore 5
    //   17: aload_0
    //   18: aload_1
    //   19: bipush 100
    //   21: aload 5
    //   23: invokevirtual 38	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   26: pop
    //   27: aload 5
    //   29: invokevirtual 42	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   32: arraylength
    //   33: istore_3
    //   34: aload 5
    //   36: ifnull +66 -> 102
    //   39: aload 5
    //   41: invokevirtual 48	java/io/ByteArrayOutputStream:close	()V
    //   44: iload_3
    //   45: istore_2
    //   46: iload_2
    //   47: ireturn
    //   48: astore_1
    //   49: aload 6
    //   51: astore_0
    //   52: aload_0
    //   53: astore 4
    //   55: aload_1
    //   56: invokevirtual 51	java/lang/Exception:printStackTrace	()V
    //   59: aload_0
    //   60: ifnull -14 -> 46
    //   63: aload_0
    //   64: invokevirtual 48	java/io/ByteArrayOutputStream:close	()V
    //   67: iconst_0
    //   68: ireturn
    //   69: astore_0
    //   70: aload_0
    //   71: invokevirtual 51	java/lang/Exception:printStackTrace	()V
    //   74: iconst_0
    //   75: ireturn
    //   76: astore_0
    //   77: aload 4
    //   79: ifnull +8 -> 87
    //   82: aload 4
    //   84: invokevirtual 48	java/io/ByteArrayOutputStream:close	()V
    //   87: aload_0
    //   88: athrow
    //   89: astore_1
    //   90: aload_1
    //   91: invokevirtual 51	java/lang/Exception:printStackTrace	()V
    //   94: goto -7 -> 87
    //   97: astore_0
    //   98: aload_0
    //   99: invokevirtual 51	java/lang/Exception:printStackTrace	()V
    //   102: iload_3
    //   103: ireturn
    //   104: astore_0
    //   105: aload 5
    //   107: astore 4
    //   109: goto -32 -> 77
    //   112: astore_1
    //   113: aload 5
    //   115: astore_0
    //   116: goto -64 -> 52
    //
    // Exception table:
    //   from	to	target	type
    //   8	17	48	java/lang/Exception
    //   63	67	69	java/lang/Exception
    //   8	17	76	finally
    //   55	59	76	finally
    //   82	87	89	java/lang/Exception
    //   39	44	97	java/lang/Exception
    //   17	34	104	finally
    //   17	34	112	java/lang/Exception
  }

  public static int[] getColorHistogram(Bitmap paramBitmap)
  {
    int i1 = paramBitmap.getWidth();
    int i2 = paramBitmap.getHeight();
    int[] arrayOfInt = new int[64];
    int m = 0;
    int n;
    while (true)
    {
      if (m >= i1)
        return arrayOfInt;
      n = 0;
      if (n < i2)
        break;
      m += 1;
    }
    int i = paramBitmap.getPixel(m, n);
    int i5 = i >> 16 & 0xFF;
    int i4 = i >> 8 & 0xFF;
    int i3 = i & 0xFF;
    i = 0;
    int j = 0;
    int k = 0;
    if (i5 >= 192)
    {
      i = 3;
      label102: if (i4 < 192)
        break label187;
      j = 3;
      label112: if (i3 < 192)
        break label222;
      k = 3;
    }
    while (true)
    {
      i = i * 16 + j * 4 + k;
      arrayOfInt[i] += 1;
      n += 1;
      break;
      if (i5 >= 128)
      {
        i = 2;
        break label102;
      }
      if (i5 >= 64)
      {
        i = 1;
        break label102;
      }
      if (i5 < 0)
        break label102;
      i = 0;
      break label102;
      label187: if (i4 >= 128)
      {
        j = 2;
        break label112;
      }
      if (i4 >= 64)
      {
        j = 1;
        break label112;
      }
      if (i4 < 0)
        break label112;
      j = 0;
      break label112;
      label222: if (i3 >= 128)
        k = 2;
      else if (i3 >= 64)
        k = 1;
      else if (i3 >= 0)
        k = 0;
    }
  }

  public static String getDCTHashCode(Bitmap paramBitmap)
  {
    paramBitmap = Bitmap.createScaledBitmap(paramBitmap, 32, 32, false);
    int k = paramBitmap.getWidth();
    int m = paramBitmap.getHeight();
    Log.i("th", "将图片缩小到32x32的尺寸:" + k + "*" + m);
    Object localObject = new int[k * m];
    int i = 0;
    int j;
    if (i >= k)
    {
      releaseBitmap(paramBitmap);
      localObject = AbMathUtil.matrixToArray(FDCT.fDctTransform(AbMathUtil.intToDoubleMatrix(AbMathUtil.arrayToMatrix((int[])localObject, k, m))));
      j = AbMathUtil.average((double[])localObject);
      paramBitmap = new int[64];
      i = 0;
      if (i < paramBitmap.length)
        break label173;
      localObject = new StringBuffer();
      i = 0;
    }
    while (true)
    {
      if (i >= paramBitmap.length)
      {
        return ((StringBuffer)localObject).toString();
        j = 0;
        while (true)
        {
          if (j >= m)
          {
            i += 1;
            break;
          }
          localObject[(i * m + j)] = rgbToGray(paramBitmap.getPixel(i, j));
          j += 1;
        }
        label173: if (localObject[i] >= j)
          paramBitmap[i] = 1;
        while (true)
        {
          i += 1;
          break;
          paramBitmap[i] = 0;
        }
      }
      ((StringBuffer)localObject).append(AbMathUtil.binaryToHex(paramBitmap[i] * (int)Math.pow(2.0D, 3.0D) + paramBitmap[(i + 1)] * (int)Math.pow(2.0D, 2.0D) + paramBitmap[(i + 2)] * (int)Math.pow(2.0D, 1.0D) + paramBitmap[(i + 2)]));
      i += 4;
    }
  }

  public static String getHashCode(Bitmap paramBitmap)
  {
    Object localObject = Bitmap.createScaledBitmap(paramBitmap, 8, 8, false);
    int k = ((Bitmap)localObject).getWidth();
    int m = ((Bitmap)localObject).getHeight();
    Log.i("th", "将图片缩小到8x8的尺寸:" + k + "*" + m);
    paramBitmap = new int[k * m];
    int i = 0;
    int j;
    if (i >= k)
    {
      releaseBitmap((Bitmap)localObject);
      j = AbMathUtil.average(paramBitmap);
      localObject = new int[k * m];
      i = 0;
      if (i < localObject.length)
        break label160;
      paramBitmap = new StringBuffer();
      i = 0;
    }
    while (true)
    {
      if (i >= localObject.length)
      {
        return paramBitmap.toString();
        j = 0;
        while (true)
        {
          if (j >= m)
          {
            i += 1;
            break;
          }
          paramBitmap[(i * m + j)] = rgbToGray(((Bitmap)localObject).getPixel(i, j));
          j += 1;
        }
        label160: if (paramBitmap[i] >= j)
          localObject[i] = 1;
        while (true)
        {
          i += 1;
          break;
          localObject[i] = 0;
        }
      }
      paramBitmap.append(AbMathUtil.binaryToHex(localObject[i] * (int)Math.pow(2.0D, 3.0D) + localObject[(i + 1)] * (int)Math.pow(2.0D, 2.0D) + localObject[(i + 2)] * (int)Math.pow(2.0D, 1.0D) + localObject[(i + 2)]));
      i += 4;
    }
  }

  public static int hammingDistance(String paramString1, String paramString2)
  {
    int j = 0;
    int m = paramString1.length();
    int i = 0;
    while (true)
    {
      if (i >= m)
        return j;
      int k = j;
      if (paramString1.charAt(i) != paramString2.charAt(i))
        k = j + 1;
      i += 1;
      j = k;
    }
  }

  public static Bitmap imageView2Bitmap(ImageView paramImageView)
  {
    Object localObject = null;
    try
    {
      Bitmap localBitmap = Bitmap.createBitmap(paramImageView.getDrawingCache());
      localObject = localBitmap;
      paramImageView.setDrawingCacheEnabled(false);
      return localBitmap;
    }
    catch (Exception paramImageView)
    {
      paramImageView.printStackTrace();
    }
    return localObject;
  }

  public static void main(String[] paramArrayOfString)
  {
  }

  public static Bitmap originalImg(File paramFile)
  {
    try
    {
      paramFile = BitmapFactory.decodeFile(paramFile.getPath());
      return paramFile;
    }
    catch (Exception paramFile)
    {
      paramFile.printStackTrace();
    }
    return null;
  }

  public static void releaseBitmap(Bitmap paramBitmap)
  {
    if (paramBitmap != null);
    try
    {
      if (!paramBitmap.isRecycled())
      {
        if (D)
          Log.d(TAG, "Bitmap释放" + paramBitmap.toString());
        paramBitmap.recycle();
      }
      label48: return;
    }
    catch (Exception paramBitmap)
    {
      break label48;
    }
  }

  public static void releaseBitmapArray(Bitmap[] paramArrayOfBitmap)
  {
    if (paramArrayOfBitmap != null)
    {
      int j;
      int i;
      do
        try
        {
          j = paramArrayOfBitmap.length;
          i = 0;
          continue;
          Bitmap localBitmap = paramArrayOfBitmap[i];
          if ((localBitmap != null) && (!localBitmap.isRecycled()))
          {
            if (D)
              Log.d(TAG, "Bitmap释放" + localBitmap.toString());
            localBitmap.recycle();
          }
          i += 1;
        }
        catch (Exception paramArrayOfBitmap)
        {
          return;
        }
      while (i < j);
    }
  }

  private static int rgbToGray(int paramInt)
  {
    return (int)(0.3D * (paramInt >> 16 & 0xFF) + 0.59D * (paramInt >> 8 & 0xFF) + 0.11D * (paramInt & 0xFF));
  }

  public static Bitmap rotateBitmap(Bitmap paramBitmap, float paramFloat)
  {
    try
    {
      Matrix localMatrix = new Matrix();
      localMatrix.setRotate(paramFloat % 360.0F);
      paramBitmap = Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), localMatrix, false);
      return paramBitmap;
    }
    catch (Exception paramBitmap)
    {
      paramBitmap.printStackTrace();
    }
    return null;
  }

  public static Bitmap rotateBitmapTranslate(Bitmap paramBitmap, float paramFloat)
  {
    try
    {
      Matrix localMatrix = new Matrix();
      int j;
      if (paramFloat / 90.0F % 2.0F != 0.0F)
        j = paramBitmap.getWidth();
      for (int i = paramBitmap.getHeight(); ; i = paramBitmap.getWidth())
      {
        j /= 2;
        i /= 2;
        localMatrix.preTranslate(-j, -i);
        localMatrix.postRotate(paramFloat);
        localMatrix.postTranslate(j, i);
        return null;
        j = paramBitmap.getHeight();
      }
    }
    catch (Exception paramBitmap)
    {
      paramBitmap.printStackTrace();
    }
    return null;
  }

  public static Bitmap scaleImg(Bitmap paramBitmap, float paramFloat)
  {
    Object localObject3 = null;
    try
    {
      int i = paramBitmap.getWidth();
      int j = paramBitmap.getHeight();
      Object localObject1 = new Matrix();
      ((Matrix)localObject1).postScale(paramFloat, paramFloat);
      localObject1 = Bitmap.createBitmap(paramBitmap, 0, 0, i, j, (Matrix)localObject1, true);
      localObject3 = localObject1;
      if (localObject1 != paramBitmap)
      {
        paramBitmap.recycle();
        localObject3 = localObject1;
      }
      return localObject3;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return null;
    }
    finally
    {
      if (paramBitmap != null)
        paramBitmap.recycle();
    }
  }

  public static Bitmap scaleImg(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    Object localObject2 = null;
    if (paramBitmap == null);
    int i;
    int j;
    do
    {
      return null;
      if ((paramInt1 <= 0) || (paramInt2 <= 0))
        throw new IllegalArgumentException("缩放图片的宽高设置不能小于0");
      i = paramBitmap.getWidth();
      j = paramBitmap.getHeight();
    }
    while ((i <= 0) || (j <= 0));
    float f1 = paramInt1 / i;
    float f2 = paramInt2 / j;
    if (f1 > f2);
    while (true)
    {
      Object localObject1 = localObject2;
      if (paramBitmap != null)
      {
        localObject1 = localObject2;
        if (f1 != 1.0F)
          localObject1 = scaleImg(paramBitmap, f1);
      }
      return localObject1;
      f1 = f2;
    }
  }

  public static Bitmap scaleImg(File paramFile, int paramInt1, int paramInt2)
  {
    if ((paramInt1 <= 0) || (paramInt2 <= 0))
      throw new IllegalArgumentException("缩放图片的宽高设置不能小于0");
    Object localObject = new BitmapFactory.Options();
    ((BitmapFactory.Options)localObject).inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramFile.getPath(), (BitmapFactory.Options)localObject);
    int k = ((BitmapFactory.Options)localObject).outWidth;
    int m = ((BitmapFactory.Options)localObject).outHeight;
    int j = k;
    int i = m;
    float f1 = paramInt1 / k;
    float f2 = paramInt2 / m;
    if (f1 > f2)
    {
      paramInt2 = i;
      paramInt1 = j;
      if (f1 != 0.0F)
      {
        paramInt1 = (int)(j / f1);
        paramInt2 = (int)(i / f1);
      }
      ((BitmapFactory.Options)localObject).inPreferredConfig = Bitmap.Config.RGB_565;
      ((BitmapFactory.Options)localObject).inPurgeable = true;
      ((BitmapFactory.Options)localObject).inInputShareable = true;
      if (f1 <= 1.0F)
        break label214;
    }
    label214: for (((BitmapFactory.Options)localObject).inSampleSize = ((int)f1); ; ((BitmapFactory.Options)localObject).inSampleSize = 1)
    {
      ((BitmapFactory.Options)localObject).outHeight = paramInt2;
      ((BitmapFactory.Options)localObject).outWidth = paramInt1;
      ((BitmapFactory.Options)localObject).inJustDecodeBounds = false;
      ((BitmapFactory.Options)localObject).inDither = false;
      localObject = BitmapFactory.decodeFile(paramFile.getPath(), (BitmapFactory.Options)localObject);
      paramFile = (File)localObject;
      if (localObject != null)
      {
        paramFile = (File)localObject;
        if (f1 != 1.0F)
          paramFile = scaleImg((Bitmap)localObject, f1);
      }
      return paramFile;
      f1 = f2;
      break;
    }
  }

  public static Bitmap toReflectionBitmap(Bitmap paramBitmap)
  {
    if (paramBitmap == null)
      return null;
    try
    {
      int i = paramBitmap.getWidth();
      int j = paramBitmap.getHeight();
      Object localObject1 = new Matrix();
      ((Matrix)localObject1).preScale(1.0F, -1.0F);
      Object localObject2 = Bitmap.createBitmap(paramBitmap, 0, j / 2, i, j / 2, (Matrix)localObject1, false);
      localObject1 = Bitmap.createBitmap(i, j / 2 + j, Bitmap.Config.ARGB_8888);
      Canvas localCanvas = new Canvas((Bitmap)localObject1);
      localCanvas.drawBitmap(paramBitmap, 0.0F, 0.0F, null);
      Paint localPaint = new Paint();
      localCanvas.drawRect(0.0F, j, i, j + 1, localPaint);
      localCanvas.drawBitmap((Bitmap)localObject2, 0.0F, j + 1, null);
      localObject2 = new Paint();
      ((Paint)localObject2).setShader(new LinearGradient(0.0F, paramBitmap.getHeight(), 0.0F, ((Bitmap)localObject1).getHeight() + 1, 1895825407, 16777215, Shader.TileMode.CLAMP));
      ((Paint)localObject2).setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
      localCanvas.drawRect(0.0F, j, i, ((Bitmap)localObject1).getHeight() + 1, (Paint)localObject2);
      return localObject1;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return paramBitmap;
  }

  public static Bitmap toRoundBitmap(Bitmap paramBitmap)
  {
    if (paramBitmap == null)
      return null;
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    float f6;
    float f1;
    float f4;
    float f5;
    float f3;
    if (i <= j)
    {
      f6 = i / 2;
      f1 = i;
      f4 = 0.0F;
      f5 = i;
      j = i;
      f3 = i;
    }
    for (float f2 = i; ; f2 = j)
    {
      Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
      Canvas localCanvas = new Canvas(localBitmap);
      Paint localPaint = new Paint();
      Rect localRect1 = new Rect((int)f4, (int)0.0F, (int)f5, (int)f1);
      Rect localRect2 = new Rect((int)0.0F, (int)0.0F, (int)f3, (int)f2);
      RectF localRectF = new RectF(localRect2);
      localPaint.setAntiAlias(true);
      localCanvas.drawARGB(0, 0, 0, 0);
      localPaint.setColor(-12434878);
      localCanvas.drawRoundRect(localRectF, f6, f6, localPaint);
      localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
      localCanvas.drawBitmap(paramBitmap, localRect1, localRect2, localPaint);
      return localBitmap;
      f6 = j / 2;
      f1 = (i - j) / 2;
      f4 = f1;
      f5 = i - f1;
      f1 = j;
      i = j;
      f3 = j;
    }
  }

  public static Bitmap view2Bitmap(View paramView)
  {
    Bitmap localBitmap = null;
    if (paramView != null);
    try
    {
      paramView.setDrawingCacheEnabled(true);
      paramView.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
      paramView.layout(0, 0, paramView.getMeasuredWidth(), paramView.getMeasuredHeight());
      paramView.buildDrawingCache();
      localBitmap = paramView.getDrawingCache();
      return localBitmap;
    }
    catch (Exception paramView)
    {
      paramView.printStackTrace();
    }
    return null;
  }

  public static byte[] view2Bytes(View paramView, Bitmap.CompressFormat paramCompressFormat)
  {
    try
    {
      paramView = bitmap2Bytes(view2Bitmap(paramView), paramCompressFormat, true);
      return paramView;
    }
    catch (Exception paramView)
    {
      paramView.printStackTrace();
    }
    return null;
  }

  public static Drawable view2Drawable(View paramView)
  {
    Object localObject = null;
    try
    {
      Bitmap localBitmap = view2Bitmap(paramView);
      paramView = localObject;
      if (localBitmap != null)
        paramView = new BitmapDrawable(localBitmap);
      return paramView;
    }
    catch (Exception paramView)
    {
      paramView.printStackTrace();
    }
    return null;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.util.AbImageUtil
 * JD-Core Version:    0.6.2
 */