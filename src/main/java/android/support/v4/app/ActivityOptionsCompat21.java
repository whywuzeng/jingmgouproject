package android.support.v4.app;

import android.app.Activity;
import android.app.ActivityOptions;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

class ActivityOptionsCompat21
{
  private final ActivityOptions mActivityOptions;

  private ActivityOptionsCompat21(ActivityOptions paramActivityOptions)
  {
    this.mActivityOptions = paramActivityOptions;
  }

  public static ActivityOptionsCompat21 makeSceneTransitionAnimation(Activity paramActivity, View paramView, String paramString)
  {
    return new ActivityOptionsCompat21(ActivityOptions.makeSceneTransitionAnimation(paramActivity, paramView, paramString));
  }

  public static ActivityOptionsCompat21 makeSceneTransitionAnimation(Activity paramActivity, View[] paramArrayOfView, String[] paramArrayOfString)
  {
    Object localObject = null;
    if (paramArrayOfView != null)
    {
      Pair[] arrayOfPair = new Pair[paramArrayOfView.length];
      int i = 0;
      while (true)
      {
        localObject = arrayOfPair;
        if (i >= arrayOfPair.length)
          break;
        arrayOfPair[i] = Pair.create(paramArrayOfView[i], paramArrayOfString[i]);
        i += 1;
      }
    }
    return new ActivityOptionsCompat21(ActivityOptions.makeSceneTransitionAnimation(paramActivity, localObject));
  }

  public Bundle toBundle()
  {
    return this.mActivityOptions.toBundle();
  }

  public void update(ActivityOptionsCompat21 paramActivityOptionsCompat21)
  {
    this.mActivityOptions.update(paramActivityOptionsCompat21.mActivityOptions);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     android.support.v4.app.ActivityOptionsCompat21
 * JD-Core Version:    0.6.2
 */