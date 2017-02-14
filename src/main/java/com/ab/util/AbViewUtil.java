package com.ab.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class AbViewUtil
{
  public static int dip2px(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat * paramContext.getResources().getDisplayMetrics().density + 0.5F);
  }

  public static int getAbsListViewHeight(AbsListView paramAbsListView, int paramInt1, int paramInt2)
  {
    int j = 0;
    int i = 0;
    int k = View.MeasureSpec.makeMeasureSpec(0, 0);
    int m = View.MeasureSpec.makeMeasureSpec(0, 0);
    paramAbsListView.measure(k, m);
    ListAdapter localListAdapter = (ListAdapter)paramAbsListView.getAdapter();
    if (localListAdapter == null)
      return 0;
    int n = localListAdapter.getCount();
    if ((paramAbsListView instanceof ListView))
    {
      paramInt1 = 0;
      if (paramInt1 >= n)
      {
        if (n != 0)
          break label112;
        i = paramInt2;
      }
    }
    while (true)
    {
      return i;
      View localView = localListAdapter.getView(paramInt1, null, paramAbsListView);
      localView.measure(k, m);
      i += localView.getMeasuredHeight();
      paramInt1 += 1;
      break;
      label112: i += ((ListView)paramAbsListView).getDividerHeight() * (n - 1);
      continue;
      i = j;
      if ((paramAbsListView instanceof GridView))
      {
        j = n % paramInt1;
        i = j;
        if (j > 0)
          i = 1;
        if (localListAdapter.getCount() == 0)
        {
          i = paramInt2;
        }
        else
        {
          paramAbsListView = localListAdapter.getView(0, null, paramAbsListView);
          paramAbsListView.measure(k, m);
          paramInt1 = n / paramInt1 + i;
          i = paramAbsListView.getMeasuredHeight() * paramInt1 + (paramInt1 - 1) * paramInt2;
        }
      }
    }
  }

  public static void measureView(View paramView)
  {
    if (paramView == null)
      return;
    paramView.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
  }

  public static int px2dip(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat / paramContext.getResources().getDisplayMetrics().density + 0.5F);
  }

  public static int resizeTextSize(int paramInt1, int paramInt2, int paramInt3)
  {
    float f1 = 1.0F;
    float f2 = paramInt1 / 480.0F;
    float f3 = paramInt2 / 800.0F;
    try
    {
      f2 = Math.min(f2, f3);
      f1 = f2;
      label28: return Math.round(paramInt3 * f1);
    }
    catch (Exception localException)
    {
      break label28;
    }
  }

  public static void setAbsListViewHeight(AbsListView paramAbsListView, int paramInt1, int paramInt2)
  {
    paramInt1 = getAbsListViewHeight(paramAbsListView, paramInt1, paramInt2);
    ViewGroup.LayoutParams localLayoutParams = paramAbsListView.getLayoutParams();
    localLayoutParams.height = paramInt1;
    ((ViewGroup.MarginLayoutParams)localLayoutParams).setMargins(0, 0, 0, 0);
    paramAbsListView.setLayoutParams(localLayoutParams);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.util.AbViewUtil
 * JD-Core Version:    0.6.2
 */