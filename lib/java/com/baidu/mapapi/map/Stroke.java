package com.baidu.mapapi.map;

import android.os.Bundle;

public final class Stroke
{
  public final int color;
  public final int strokeWidth;

  public Stroke(int paramInt1, int paramInt2)
  {
    int i = paramInt1;
    if (paramInt1 <= 0)
      i = 5;
    this.strokeWidth = i;
    this.color = paramInt2;
  }

  Bundle a(Bundle paramBundle)
  {
    paramBundle.putInt("width", this.strokeWidth);
    Overlay.a(this.color, paramBundle);
    return paramBundle;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.Stroke
 * JD-Core Version:    0.6.2
 */