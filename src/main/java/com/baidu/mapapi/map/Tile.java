package com.baidu.mapapi.map;

import android.os.Bundle;

public class Tile
{
  public final byte[] data;
  public final int height;
  public final int width;

  public Tile(int paramInt1, int paramInt2, byte[] paramArrayOfByte)
  {
    this.width = paramInt1;
    this.height = paramInt2;
    this.data = paramArrayOfByte;
  }

  Bundle a()
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("image_width", this.width);
    localBundle.putInt("image_height", this.height);
    localBundle.putByteArray("image_data", this.data);
    return localBundle;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.Tile
 * JD-Core Version:    0.6.2
 */