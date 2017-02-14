package com.ismartgo.app.grid.wave;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class UiUtils
{
  public static int dipToPx(Context paramContext, int paramInt)
  {
    return (int)(paramInt * getScreenDensity(paramContext) + 0.5F);
  }

  public static float getScreenDensity(Context paramContext)
  {
    try
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
      float f = localDisplayMetrics.density;
      return f;
    }
    catch (Exception paramContext)
    {
    }
    return 160.0F;
  }

  public static int getScreenWidthPixels(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.widthPixels;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.wave.UiUtils
 * JD-Core Version:    0.6.2
 */