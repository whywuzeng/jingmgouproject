package net.tsz.afinal.bitmap.display;

import android.graphics.Bitmap;
import android.view.View;
import net.tsz.afinal.bitmap.core.BitmapDisplayConfig;

public abstract interface Displayer
{
  public abstract void loadCompletedisplay(View paramView, Bitmap paramBitmap, BitmapDisplayConfig paramBitmapDisplayConfig);

  public abstract void loadFailDisplay(View paramView, Bitmap paramBitmap);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.bitmap.display.Displayer
 * JD-Core Version:    0.6.2
 */