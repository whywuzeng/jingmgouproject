package com.wyy.twodimcode.camera;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.google.zxing.LuminanceSource;

public final class PlanarYUVLuminanceSource extends LuminanceSource
{
  private final int dataHeight;
  private final int dataWidth;
  private final int left;
  private final int top;
  private final byte[] yuvData;

  public PlanarYUVLuminanceSource(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    super(paramInt5, paramInt6);
    if ((paramInt3 + paramInt5 > paramInt1) || (paramInt4 + paramInt6 > paramInt2))
      throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
    this.yuvData = paramArrayOfByte;
    this.dataWidth = paramInt1;
    this.dataHeight = paramInt2;
    this.left = paramInt3;
    this.top = paramInt4;
  }

  public int getDataHeight()
  {
    return this.dataHeight;
  }

  public int getDataWidth()
  {
    return this.dataWidth;
  }

  public byte[] getMatrix()
  {
    int k = getWidth();
    int m = getHeight();
    Object localObject;
    if ((k == this.dataWidth) && (m == this.dataHeight))
    {
      localObject = this.yuvData;
      return localObject;
    }
    int i = k * m;
    byte[] arrayOfByte1 = new byte[i];
    int j = this.top * this.dataWidth + this.left;
    if (k == this.dataWidth)
    {
      System.arraycopy(this.yuvData, j, arrayOfByte1, 0, i);
      return arrayOfByte1;
    }
    byte[] arrayOfByte2 = this.yuvData;
    i = 0;
    while (true)
    {
      localObject = arrayOfByte1;
      if (i >= m)
        break;
      System.arraycopy(arrayOfByte2, j, arrayOfByte1, i * k, k);
      j += this.dataWidth;
      i += 1;
    }
  }

  public byte[] getRow(int paramInt, byte[] paramArrayOfByte)
  {
    if ((paramInt < 0) || (paramInt >= getHeight()))
      throw new IllegalArgumentException("Requested row is outside the image: " + paramInt);
    int i = getWidth();
    byte[] arrayOfByte;
    if (paramArrayOfByte != null)
    {
      arrayOfByte = paramArrayOfByte;
      if (paramArrayOfByte.length >= i);
    }
    else
    {
      arrayOfByte = new byte[i];
    }
    int j = this.top;
    int k = this.dataWidth;
    int m = this.left;
    System.arraycopy(this.yuvData, (j + paramInt) * k + m, arrayOfByte, 0, i);
    return arrayOfByte;
  }

  public boolean isCropSupported()
  {
    return true;
  }

  public Bitmap renderCroppedGreyscaleBitmap()
  {
    int m = getWidth();
    int n = getHeight();
    int[] arrayOfInt = new int[m * n];
    Object localObject = this.yuvData;
    int j = this.top * this.dataWidth + this.left;
    int i = 0;
    if (i >= n)
    {
      localObject = Bitmap.createBitmap(m, n, Bitmap.Config.ARGB_8888);
      ((Bitmap)localObject).setPixels(arrayOfInt, 0, m, 0, 0, m, n);
      return localObject;
    }
    int k = 0;
    while (true)
    {
      if (k >= m)
      {
        j += this.dataWidth;
        i += 1;
        break;
      }
      arrayOfInt[(i * m + k)] = (0xFF000000 | 65793 * (localObject[(j + k)] & 0xFF));
      k += 1;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.wyy.twodimcode.camera.PlanarYUVLuminanceSource
 * JD-Core Version:    0.6.2
 */