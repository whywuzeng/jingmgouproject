package cn.smssdk.framework.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.View;
import cn.smssdk.framework.network.e;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Array;

public class BitmapHelper
{
  private static Bitmap a(Bitmap paramBitmap, int paramInt, boolean paramBoolean)
  {
    if (paramBoolean);
    while (paramInt < 1)
    {
      return null;
      paramBitmap = paramBitmap.copy(paramBitmap.getConfig(), true);
    }
    int i12 = paramBitmap.getWidth();
    int i13 = paramBitmap.getHeight();
    int[] arrayOfInt1 = new int[i12 * i13];
    paramBitmap.getPixels(arrayOfInt1, 0, i12, 0, 0, i12, i13);
    int i11 = i12 - 1;
    int i14 = i13 - 1;
    int i = i12 * i13;
    int i15 = paramInt + paramInt + 1;
    int[] arrayOfInt2 = new int[i];
    int[] arrayOfInt3 = new int[i];
    int[] arrayOfInt4 = new int[i];
    int[] arrayOfInt5 = new int[Math.max(i12, i13)];
    i = i15 + 1 >> 1;
    int j = i * i;
    int[] arrayOfInt6 = new int[j * 256];
    i = 0;
    while (i < j * 256)
    {
      arrayOfInt6[i] = (i / j);
      i += 1;
    }
    int[][] arrayOfInt = (int[][])Array.newInstance(Integer.TYPE, new int[] { i15, 3 });
    int i16 = paramInt + 1;
    int i7 = 0;
    i = 0;
    int i6 = 0;
    int i8;
    int m;
    int n;
    int i1;
    int i2;
    int i3;
    int i4;
    int i5;
    int k;
    int i9;
    int[] arrayOfInt7;
    int i10;
    int i17;
    while (i6 < i13)
    {
      j = 0;
      i8 = -paramInt;
      m = 0;
      n = 0;
      i1 = 0;
      i2 = 0;
      i3 = 0;
      i4 = 0;
      i5 = 0;
      k = 0;
      if (i8 <= paramInt)
      {
        i9 = arrayOfInt1[(Math.min(i11, Math.max(i8, 0)) + i)];
        arrayOfInt7 = arrayOfInt[(i8 + paramInt)];
        arrayOfInt7[0] = ((0xFF0000 & i9) >> 16);
        arrayOfInt7[1] = ((0xFF00 & i9) >> 8);
        arrayOfInt7[2] = (i9 & 0xFF);
        i9 = i16 - Math.abs(i8);
        i5 += arrayOfInt7[0] * i9;
        i4 += arrayOfInt7[1] * i9;
        i3 += i9 * arrayOfInt7[2];
        if (i8 > 0)
        {
          m += arrayOfInt7[0];
          k += arrayOfInt7[1];
          j += arrayOfInt7[2];
        }
        while (true)
        {
          i8 += 1;
          break;
          i2 += arrayOfInt7[0];
          i1 += arrayOfInt7[1];
          n += arrayOfInt7[2];
        }
      }
      i8 = i4;
      i10 = 0;
      i4 = paramInt;
      i9 = i5;
      i5 = i3;
      i3 = i10;
      while (i3 < i12)
      {
        arrayOfInt2[i] = arrayOfInt6[i9];
        arrayOfInt3[i] = arrayOfInt6[i8];
        arrayOfInt4[i] = arrayOfInt6[i5];
        arrayOfInt7 = arrayOfInt[((i4 - paramInt + i15) % i15)];
        int i18 = arrayOfInt7[0];
        i17 = arrayOfInt7[1];
        i10 = arrayOfInt7[2];
        if (i6 == 0)
          arrayOfInt5[i3] = Math.min(i3 + paramInt + 1, i11);
        int i19 = arrayOfInt1[(arrayOfInt5[i3] + i7)];
        arrayOfInt7[0] = ((0xFF0000 & i19) >> 16);
        arrayOfInt7[1] = ((0xFF00 & i19) >> 8);
        arrayOfInt7[2] = (i19 & 0xFF);
        m += arrayOfInt7[0];
        k += arrayOfInt7[1];
        j += arrayOfInt7[2];
        i9 = i9 - i2 + m;
        i8 = i8 - i1 + k;
        i5 = i5 - n + j;
        i4 = (i4 + 1) % i15;
        arrayOfInt7 = arrayOfInt[(i4 % i15)];
        i2 = i2 - i18 + arrayOfInt7[0];
        i1 = i1 - i17 + arrayOfInt7[1];
        n = n - i10 + arrayOfInt7[2];
        m -= arrayOfInt7[0];
        k -= arrayOfInt7[1];
        j -= arrayOfInt7[2];
        i += 1;
        i3 += 1;
      }
      i7 += i12;
      i6 += 1;
    }
    i = 0;
    while (i < i12)
    {
      i5 = 0;
      i7 = -paramInt * i12;
      i6 = -paramInt;
      i3 = 0;
      n = 0;
      i1 = 0;
      i2 = 0;
      j = 0;
      m = 0;
      k = 0;
      i4 = 0;
      if (i6 <= paramInt)
      {
        i8 = Math.max(0, i7) + i;
        arrayOfInt7 = arrayOfInt[(i6 + paramInt)];
        arrayOfInt7[0] = arrayOfInt2[i8];
        arrayOfInt7[1] = arrayOfInt3[i8];
        arrayOfInt7[2] = arrayOfInt4[i8];
        i9 = i16 - Math.abs(i6);
        i10 = arrayOfInt2[i8];
        i11 = arrayOfInt3[i8];
        i17 = arrayOfInt4[i8];
        if (i6 > 0)
        {
          i3 += arrayOfInt7[0];
          i4 += arrayOfInt7[1];
          i5 += arrayOfInt7[2];
        }
        while (true)
        {
          i8 = i7;
          if (i6 < i14)
            i8 = i7 + i12;
          i6 += 1;
          j = i17 * i9 + j;
          m = i11 * i9 + m;
          k = i10 * i9 + k;
          i7 = i8;
          break;
          i2 += arrayOfInt7[0];
          i1 += arrayOfInt7[1];
          n += arrayOfInt7[2];
        }
      }
      i9 = m;
      i10 = k;
      i11 = 0;
      k = i;
      i6 = i5;
      i7 = i4;
      i8 = i3;
      m = n;
      n = i1;
      i1 = i2;
      i2 = paramInt;
      i5 = i10;
      i4 = i9;
      i3 = j;
      j = i11;
      while (j < i13)
      {
        arrayOfInt1[k] = (0xFF000000 & arrayOfInt1[k] | arrayOfInt6[i5] << 16 | arrayOfInt6[i4] << 8 | arrayOfInt6[i3]);
        arrayOfInt7 = arrayOfInt[((i2 - paramInt + i15) % i15)];
        i11 = arrayOfInt7[0];
        i10 = arrayOfInt7[1];
        i9 = arrayOfInt7[2];
        if (i == 0)
          arrayOfInt5[j] = (Math.min(j + i16, i14) * i12);
        i17 = arrayOfInt5[j] + i;
        arrayOfInt7[0] = arrayOfInt2[i17];
        arrayOfInt7[1] = arrayOfInt3[i17];
        arrayOfInt7[2] = arrayOfInt4[i17];
        i8 += arrayOfInt7[0];
        i7 += arrayOfInt7[1];
        i6 += arrayOfInt7[2];
        i5 = i5 - i1 + i8;
        i4 = i4 - n + i7;
        i3 = i3 - m + i6;
        i2 = (i2 + 1) % i15;
        arrayOfInt7 = arrayOfInt[i2];
        i1 = i1 - i11 + arrayOfInt7[0];
        n = n - i10 + arrayOfInt7[1];
        m = m - i9 + arrayOfInt7[2];
        i8 -= arrayOfInt7[0];
        i7 -= arrayOfInt7[1];
        i6 -= arrayOfInt7[2];
        k += i12;
        j += 1;
      }
      i += 1;
    }
    paramBitmap.setPixels(arrayOfInt1, 0, i12, 0, 0, i12, i13);
    return paramBitmap;
  }

  private static Bitmap a(InputStream paramInputStream, int paramInt)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inPreferredConfig = Bitmap.Config.RGB_565;
    localOptions.inPurgeable = true;
    localOptions.inInputShareable = true;
    localOptions.inSampleSize = paramInt;
    return BitmapFactory.decodeStream(paramInputStream, null, localOptions);
  }

  public static Bitmap blur(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    paramInt1 = (int)(paramInt1 / paramInt2 + 0.5F);
    Bitmap localBitmap = Bitmap.createBitmap((int)(i / paramInt2 + 0.5F), (int)(j / paramInt2 + 0.5F), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    localCanvas.scale(1.0F / paramInt2, 1.0F / paramInt2);
    Paint localPaint = new Paint();
    localPaint.setFlags(2);
    localCanvas.drawBitmap(paramBitmap, 0.0F, 0.0F, localPaint);
    a(localBitmap, paramInt1, true);
    return localBitmap;
  }

  public static Bitmap captureView(View paramView, int paramInt1, int paramInt2)
  {
    Bitmap localBitmap = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
    paramView.draw(new Canvas(localBitmap));
    return localBitmap;
  }

  public static String downloadBitmap(Context paramContext, String paramString)
  {
    return new e().a(paramContext, paramString, "images", true);
  }

  public static Bitmap getBitmap(Context paramContext, String paramString)
  {
    return getBitmap(downloadBitmap(paramContext, paramString));
  }

  public static Bitmap getBitmap(File paramFile, int paramInt)
  {
    paramFile = new FileInputStream(paramFile);
    Bitmap localBitmap = a(paramFile, paramInt);
    paramFile.close();
    return localBitmap;
  }

  public static Bitmap getBitmap(String paramString)
  {
    return getBitmap(paramString, 1);
  }

  public static Bitmap getBitmap(String paramString, int paramInt)
  {
    return getBitmap(new File(paramString), paramInt);
  }

  public static Bitmap roundBitmap(Bitmap paramBitmap, int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    Rect localRect1 = new Rect(0, 0, i, j);
    if ((i != paramInt1) || (j != paramInt2));
    for (Bitmap localBitmap = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888); ; localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), Bitmap.Config.ARGB_8888))
    {
      Canvas localCanvas = new Canvas(localBitmap);
      Paint localPaint = new Paint();
      Rect localRect2 = new Rect(0, 0, paramInt1, paramInt2);
      localPaint.setAntiAlias(true);
      localCanvas.drawARGB(0, 0, 0, 0);
      localPaint.setColor(-12434878);
      Object localObject = new float[8];
      localObject[0] = paramFloat1;
      localObject[1] = paramFloat1;
      localObject[2] = paramFloat2;
      localObject[3] = paramFloat2;
      localObject[4] = paramFloat3;
      localObject[5] = paramFloat3;
      localObject[6] = paramFloat4;
      localObject[7] = paramFloat4;
      localObject = new ShapeDrawable(new RoundRectShape((float[])localObject, new RectF(0.0F, 0.0F, 0.0F, 0.0F), (float[])localObject));
      ((ShapeDrawable)localObject).setBounds(localRect2);
      ((ShapeDrawable)localObject).draw(localCanvas);
      localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
      localCanvas.drawBitmap(paramBitmap, localRect1, localRect2, localPaint);
      return localBitmap;
    }
  }

  public static String saveViewToImage(View paramView)
  {
    return saveViewToImage(paramView, paramView.getWidth(), paramView.getHeight());
  }

  public static String saveViewToImage(View paramView, int paramInt1, int paramInt2)
  {
    Bitmap localBitmap = captureView(paramView, paramInt1, paramInt2);
    paramView = new File(R.getCachePath(paramView.getContext(), "screenshot"), String.valueOf(System.currentTimeMillis()) + ".jpg");
    FileOutputStream localFileOutputStream = new FileOutputStream(paramView);
    localBitmap.compress(Bitmap.CompressFormat.JPEG, 100, localFileOutputStream);
    localFileOutputStream.flush();
    localFileOutputStream.close();
    return paramView.getAbsolutePath();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.framework.utils.BitmapHelper
 * JD-Core Version:    0.6.2
 */