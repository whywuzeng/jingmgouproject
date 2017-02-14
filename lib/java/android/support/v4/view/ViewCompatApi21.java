package android.support.v4.view;

import android.view.View;
import android.view.View.OnApplyWindowInsetsListener;
import android.view.WindowInsets;

class ViewCompatApi21
{
  public static float getElevation(View paramView)
  {
    return paramView.getElevation();
  }

  public static String getTransitionName(View paramView)
  {
    return paramView.getTransitionName();
  }

  public static float getTranslationZ(View paramView)
  {
    return paramView.getTranslationZ();
  }

  public static void requestApplyInsets(View paramView)
  {
    paramView.requestApplyInsets();
  }

  public static void setElevation(View paramView, float paramFloat)
  {
    paramView.setElevation(paramFloat);
  }

  public static void setOnApplyWindowInsetsListener(View paramView, OnApplyWindowInsetsListener paramOnApplyWindowInsetsListener)
  {
    paramView.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener()
    {
      public WindowInsets onApplyWindowInsets(View paramAnonymousView, WindowInsets paramAnonymousWindowInsets)
      {
        paramAnonymousWindowInsets = new WindowInsetsCompatApi21(paramAnonymousWindowInsets);
        return ((WindowInsetsCompatApi21)this.val$listener.onApplyWindowInsets(paramAnonymousView, paramAnonymousWindowInsets)).unwrap();
      }
    });
  }

  public static void setTransitionName(View paramView, String paramString)
  {
    paramView.setTransitionName(paramString);
  }

  public static void setTranslationZ(View paramView, float paramFloat)
  {
    paramView.setTranslationZ(paramFloat);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     android.support.v4.view.ViewCompatApi21
 * JD-Core Version:    0.6.2
 */