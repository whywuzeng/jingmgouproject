package com.ismartgo.app.grid.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class DisplayUtil
{
  public static int dip2px(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat * paramContext.getResources().getDisplayMetrics().density + 0.5F);
  }

  public static int getItemWidth(Context paramContext)
  {
    return (paramContext.getResources().getDisplayMetrics().widthPixels - (int)(paramContext.getResources().getDisplayMetrics().density * 10.0F * 3.0F)) / 2;
  }

  public static int getItemWidth1(Context paramContext)
  {
    return (paramContext.getResources().getDisplayMetrics().widthPixels - (int)(paramContext.getResources().getDisplayMetrics().density * 45.0F)) / 2;
  }

  public static int getScreenHeight(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().heightPixels;
  }

  public static int getScreenWidth(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().widthPixels;
  }

  public static int px2dip(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat / paramContext.getResources().getDisplayMetrics().density + 0.5F);
  }

  public static int px2sp(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat / paramContext.getResources().getDisplayMetrics().scaledDensity + 0.5F);
  }

  public static int sp2px(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat * paramContext.getResources().getDisplayMetrics().scaledDensity + 0.5F);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.utils.DisplayUtil
 * JD-Core Version:    0.6.2
 */