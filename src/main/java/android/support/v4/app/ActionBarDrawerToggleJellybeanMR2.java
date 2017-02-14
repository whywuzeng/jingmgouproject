package android.support.v4.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

class ActionBarDrawerToggleJellybeanMR2
{
  private static final String TAG = "ActionBarDrawerToggleImplJellybeanMR2";
  private static final int[] THEME_ATTRS = { 16843531 };

  public static Drawable getThemeUpIndicator(Activity paramActivity)
  {
    Object localObject = paramActivity.getActionBar();
    if (localObject != null)
      paramActivity = ((ActionBar)localObject).getThemedContext();
    while (true)
    {
      paramActivity = paramActivity.obtainStyledAttributes(null, THEME_ATTRS, 16843470, 0);
      localObject = paramActivity.getDrawable(0);
      paramActivity.recycle();
      return localObject;
    }
  }

  public static Object setActionBarDescription(Object paramObject, Activity paramActivity, int paramInt)
  {
    paramActivity = paramActivity.getActionBar();
    if (paramActivity != null)
      paramActivity.setHomeActionContentDescription(paramInt);
    return paramObject;
  }

  public static Object setActionBarUpIndicator(Object paramObject, Activity paramActivity, Drawable paramDrawable, int paramInt)
  {
    paramActivity = paramActivity.getActionBar();
    if (paramActivity != null)
    {
      paramActivity.setHomeAsUpIndicator(paramDrawable);
      paramActivity.setHomeActionContentDescription(paramInt);
    }
    return paramObject;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     android.support.v4.app.ActionBarDrawerToggleJellybeanMR2
 * JD-Core Version:    0.6.2
 */