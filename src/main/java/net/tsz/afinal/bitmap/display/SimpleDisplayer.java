package net.tsz.afinal.bitmap.display;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import net.tsz.afinal.bitmap.core.BitmapDisplayConfig;

public class SimpleDisplayer
  implements Displayer
{
  private void animationDisplay(View paramView, Bitmap paramBitmap, Animation paramAnimation)
  {
    paramAnimation.setStartTime(AnimationUtils.currentAnimationTimeMillis());
    if ((paramView instanceof ImageView))
      ((ImageView)paramView).setImageBitmap(paramBitmap);
    while (true)
    {
      paramView.startAnimation(paramAnimation);
      return;
      paramView.setBackgroundDrawable(new BitmapDrawable(paramBitmap));
    }
  }

  private void fadeInDisplay(View paramView, Bitmap paramBitmap)
  {
    paramBitmap = new TransitionDrawable(new Drawable[] { new ColorDrawable(17170445), new BitmapDrawable(paramView.getResources(), paramBitmap) });
    if ((paramView instanceof ImageView))
      ((ImageView)paramView).setImageDrawable(paramBitmap);
    while (true)
    {
      paramBitmap.startTransition(300);
      return;
      paramView.setBackgroundDrawable(paramBitmap);
    }
  }

  public void loadCompletedisplay(View paramView, Bitmap paramBitmap, BitmapDisplayConfig paramBitmapDisplayConfig)
  {
    switch (paramBitmapDisplayConfig.getAnimationType())
    {
    default:
      return;
    case 1:
      fadeInDisplay(paramView, paramBitmap);
      return;
    case 0:
    }
    animationDisplay(paramView, paramBitmap, paramBitmapDisplayConfig.getAnimation());
  }

  public void loadFailDisplay(View paramView, Bitmap paramBitmap)
  {
    if ((paramView instanceof ImageView))
    {
      ((ImageView)paramView).setImageBitmap(paramBitmap);
      return;
    }
    paramView.setBackgroundDrawable(new BitmapDrawable(paramBitmap));
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.bitmap.display.SimpleDisplayer
 * JD-Core Version:    0.6.2
 */