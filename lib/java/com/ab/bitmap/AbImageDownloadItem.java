package com.ab.bitmap;

import android.graphics.Bitmap;

public class AbImageDownloadItem
{
  public Bitmap bitmap;
  public int height;
  public String imageUrl;
  public AbImageDownloadListener listener;
  public int type;
  public int width;

  public Bitmap getBitmap()
  {
    return this.bitmap;
  }

  public int getHeight()
  {
    return this.height;
  }

  public String getImageUrl()
  {
    return this.imageUrl;
  }

  public AbImageDownloadListener getListener()
  {
    return this.listener;
  }

  public int getType()
  {
    return this.type;
  }

  public int getWidth()
  {
    return this.width;
  }

  public void setBitmap(Bitmap paramBitmap)
  {
    this.bitmap = paramBitmap;
  }

  public void setHeight(int paramInt)
  {
    this.height = paramInt;
  }

  public void setImageUrl(String paramString)
  {
    this.imageUrl = paramString;
  }

  public void setListener(AbImageDownloadListener paramAbImageDownloadListener)
  {
    this.listener = paramAbImageDownloadListener;
  }

  public void setType(int paramInt)
  {
    this.type = paramInt;
  }

  public void setWidth(int paramInt)
  {
    this.width = paramInt;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.bitmap.AbImageDownloadItem
 * JD-Core Version:    0.6.2
 */