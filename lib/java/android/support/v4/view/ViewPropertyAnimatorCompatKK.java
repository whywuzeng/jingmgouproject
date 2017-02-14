package android.support.v4.view;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.view.View;
import android.view.ViewPropertyAnimator;

class ViewPropertyAnimatorCompatKK
{
  public static void setUpdateListener(final View paramView, ViewPropertyAnimatorUpdateListener paramViewPropertyAnimatorUpdateListener)
  {
    paramView.animate().setUpdateListener(new ValueAnimator.AnimatorUpdateListener()
    {
      public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
      {
        this.val$listener.onAnimationUpdate(paramView);
      }
    });
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     android.support.v4.view.ViewPropertyAnimatorCompatKK
 * JD-Core Version:    0.6.2
 */