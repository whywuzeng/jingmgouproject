package com.ismartgo.app.andbase.util;

import android.util.SparseArray;
import android.view.View;

public class AbViewHolder
{
  public static <T extends View> T get(View paramView, int paramInt)
  {
    Object localObject2 = (SparseArray)paramView.getTag();
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject1 = new SparseArray();
      paramView.setTag(localObject1);
    }
    View localView = (View)((SparseArray)localObject1).get(paramInt);
    localObject2 = localView;
    if (localView == null)
    {
      localObject2 = paramView.findViewById(paramInt);
      ((SparseArray)localObject1).put(paramInt, localObject2);
    }
    return localObject2;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.andbase.util.AbViewHolder
 * JD-Core Version:    0.6.2
 */