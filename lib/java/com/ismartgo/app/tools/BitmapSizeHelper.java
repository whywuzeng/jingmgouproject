package com.ismartgo.app.tools;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import java.io.ByteArrayOutputStream;

public class BitmapSizeHelper
{
  public static byte[] bmpToByteArray(Bitmap paramBitmap, boolean paramBoolean)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localByteArrayOutputStream);
    if (paramBoolean)
      paramBitmap.recycle();
    paramBitmap = localByteArrayOutputStream.toByteArray();
    try
    {
      localByteArrayOutputStream.close();
      return paramBitmap;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return paramBitmap;
  }

  public static Rect calculateDstRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, ScalingLogic paramScalingLogic)
  {
    if (paramScalingLogic == ScalingLogic.FIT)
    {
      float f = paramInt1 / paramInt2;
      if (f > paramInt3 / paramInt4)
        return new Rect(0, 0, paramInt3, (int)(paramInt3 / f));
      return new Rect(0, 0, (int)(paramInt4 * f), paramInt4);
    }
    return new Rect(0, 0, paramInt3, paramInt4);
  }

  public static int calculateSampleSize(int paramInt1, int paramInt2, int paramInt3, int paramInt4, ScalingLogic paramScalingLogic)
  {
    if (paramScalingLogic == ScalingLogic.FIT)
    {
      if (paramInt1 / paramInt2 > paramInt3 / paramInt4)
        return paramInt1 / paramInt3;
      return paramInt2 / paramInt4;
    }
    if (paramInt1 / paramInt2 > paramInt3 / paramInt4)
      return paramInt2 / paramInt4;
    return paramInt1 / paramInt3;
  }

  public static Rect calculateSrcRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, ScalingLogic paramScalingLogic)
  {
    if (paramScalingLogic == ScalingLogic.CROP)
    {
      float f1 = paramInt1 / paramInt2;
      float f2 = paramInt3 / paramInt4;
      if (f1 > f2)
      {
        paramInt3 = (int)(paramInt2 * f2);
        paramInt1 = (paramInt1 - paramInt3) / 2;
        return new Rect(paramInt1, 0, paramInt1 + paramInt3, paramInt2);
      }
      paramInt3 = (int)(paramInt1 / f2);
      paramInt2 = (paramInt2 - paramInt3) / 2;
      return new Rect(0, paramInt2, paramInt1, paramInt2 + paramInt3);
    }
    return new Rect(0, 0, paramInt1, paramInt2);
  }

  public static Bitmap createScaledBitmap(Bitmap paramBitmap, int paramInt1, int paramInt2, ScalingLogic paramScalingLogic)
  {
    Rect localRect = calculateSrcRect(paramBitmap.getWidth(), paramBitmap.getHeight(), paramInt1, paramInt2, paramScalingLogic);
    paramScalingLogic = calculateDstRect(paramBitmap.getWidth(), paramBitmap.getHeight(), paramInt1, paramInt2, paramScalingLogic);
    Bitmap localBitmap = Bitmap.createBitmap(paramScalingLogic.width(), paramScalingLogic.height(), Bitmap.Config.ARGB_8888);
    new Canvas(localBitmap).drawBitmap(paramBitmap, localRect, paramScalingLogic, new Paint(2));
    return localBitmap;
  }

  public static Bitmap getBitmapFromByteArray(byte[] paramArrayOfByte, int paramInt1, int paramInt2, ScalingLogic paramScalingLogic)
  {
    try
    {
      BitmapFactory.Options localOptions = new BitmapFactory.Options();
      localOptions.inJustDecodeBounds = true;
      BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length, localOptions);
      localOptions.inJustDecodeBounds = false;
      localOptions.inSampleSize = calculateSampleSize(localOptions.outWidth, localOptions.outHeight, paramInt1, paramInt2, paramScalingLogic);
      paramArrayOfByte = BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length, localOptions);
      return paramArrayOfByte;
    }
    catch (Exception paramArrayOfByte)
    {
      paramArrayOfByte.printStackTrace();
    }
    return null;
  }

  public static Bitmap getBitmapFromPath(String paramString, int paramInt1, int paramInt2, ScalingLogic paramScalingLogic)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, localOptions);
    localOptions.inJustDecodeBounds = false;
    localOptions.inSampleSize = calculateSampleSize(localOptions.outWidth, localOptions.outHeight, paramInt1, paramInt2, paramScalingLogic);
    return BitmapFactory.decodeFile(paramString, localOptions);
  }

  public static Bitmap getBitmapFromResources(Resources paramResources, int paramInt1, int paramInt2, int paramInt3, ScalingLogic paramScalingLogic)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeResource(paramResources, paramInt1, localOptions);
    localOptions.inJustDecodeBounds = false;
    localOptions.inSampleSize = calculateSampleSize(localOptions.outWidth, localOptions.outHeight, paramInt2, paramInt3, paramScalingLogic);
    return BitmapFactory.decodeResource(paramResources, paramInt1, localOptions);
  }

  public static Bitmap toRoundBitmap(Bitmap paramBitmap)
  {
    int i = paramBitmap.getWidth() / 2;
    Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint();
    localPaint.setColor(-16777216);
    localPaint.setAntiAlias(true);
    Path localPath = new Path();
    localPath.addCircle(i, i, i, Path.Direction.CW);
    localCanvas.clipPath(localPath);
    localCanvas.drawBitmap(paramBitmap, 0.0F, 0.0F, localPaint);
    return localBitmap;
  }

  public static Bitmap zoomImage(Bitmap paramBitmap, double paramDouble1, double paramDouble2)
  {
    float f1 = paramBitmap.getWidth();
    float f2 = paramBitmap.getHeight();
    float f3 = (float)paramDouble1 / f1;
    float f4 = (float)paramDouble2 / f2;
    Matrix localMatrix = new Matrix();
    localMatrix.postScale(f3, f4);
    return Bitmap.createBitmap(paramBitmap, 0, 0, (int)f1, (int)f2, localMatrix, true);
  }

  public static enum ScalingLogic
  {
    CROP, FIT;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.tools.BitmapSizeHelper
 * JD-Core Version:    0.6.2
 */