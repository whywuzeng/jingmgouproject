package com.ismartgo.app.andbase.util;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class AbViewUtil
{
  public static final int INVALID = -2147483648;

  public static float applyDimension(int paramInt, float paramFloat, DisplayMetrics paramDisplayMetrics)
  {
    float f = paramFloat;
    switch (paramInt)
    {
    default:
      f = 0.0F;
    case 0:
      return f;
    case 1:
      return paramFloat * paramDisplayMetrics.density;
    case 2:
      return paramFloat * paramDisplayMetrics.scaledDensity;
    case 3:
      return paramDisplayMetrics.xdpi * paramFloat * 0.01388889F;
    case 4:
      return paramFloat * paramDisplayMetrics.xdpi;
    case 5:
    }
    return paramDisplayMetrics.xdpi * paramFloat * 0.03937008F;
  }

  public static float dip2px(Context paramContext, float paramFloat)
  {
    return applyDimension(1, paramFloat, AbAppUtil.getDisplayMetrics(paramContext));
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

  public static int getViewHeight(View paramView)
  {
    measureView(paramView);
    return paramView.getMeasuredHeight();
  }

  public static int getViewWidth(View paramView)
  {
    measureView(paramView);
    return paramView.getMeasuredWidth();
  }

  public static void measureView(View paramView)
  {
    ViewGroup.LayoutParams localLayoutParams2 = paramView.getLayoutParams();
    ViewGroup.LayoutParams localLayoutParams1 = localLayoutParams2;
    if (localLayoutParams2 == null)
      localLayoutParams1 = new ViewGroup.LayoutParams(-1, -2);
    int j = ViewGroup.getChildMeasureSpec(0, 0, localLayoutParams1.width);
    int i = localLayoutParams1.height;
    if (i > 0);
    for (i = View.MeasureSpec.makeMeasureSpec(i, 1073741824); ; i = View.MeasureSpec.makeMeasureSpec(0, 0))
    {
      paramView.measure(j, i);
      return;
    }
  }

  public static float px2dip(Context paramContext, float paramFloat)
  {
    return paramFloat / AbAppUtil.getDisplayMetrics(paramContext).density;
  }

  public static float px2sp(Context paramContext, float paramFloat)
  {
    return paramFloat / AbAppUtil.getDisplayMetrics(paramContext).scaledDensity;
  }

  public static void removeSelfFromParent(View paramView)
  {
    ViewParent localViewParent = paramView.getParent();
    if ((localViewParent != null) && ((localViewParent instanceof ViewGroup)))
      ((ViewGroup)localViewParent).removeView(paramView);
  }

  public static int scale(int paramInt1, int paramInt2, float paramFloat)
  {
    if (paramFloat == 0.0F)
      return 0;
    float f1 = 1.0F;
    float f2 = paramInt1;
    try
    {
      f2 = Math.min(f2 / AbAppConfig.UI_WIDTH, paramInt2 / AbAppConfig.UI_HEIGHT);
      f1 = f2;
      label36: return Math.round(paramFloat * f1 + 0.5F);
    }
    catch (Exception localException)
    {
      break label36;
    }
  }

  public static int scale(Context paramContext, float paramFloat)
  {
    paramContext = AbAppUtil.getDisplayMetrics(paramContext);
    return scale(paramContext.widthPixels, paramContext.heightPixels, paramFloat);
  }

  public static void scaleContentView(ViewGroup paramViewGroup)
  {
    scaleView(paramViewGroup);
    int i;
    if (paramViewGroup.getChildCount() > 0)
    {
      i = 0;
      if (i < paramViewGroup.getChildCount());
    }
    else
    {
      return;
    }
    if ((paramViewGroup.getChildAt(i) instanceof ViewGroup))
      scaleContentView((ViewGroup)paramViewGroup.getChildAt(i));
    while (true)
    {
      i += 1;
      break;
      scaleView(paramViewGroup.getChildAt(i));
    }
  }

  public static void scaleView(View paramView)
  {
    if ((paramView instanceof TextView))
    {
      localObject = (TextView)paramView;
      setTextSize((TextView)localObject, ((TextView)localObject).getTextSize());
    }
    Object localObject = paramView.getLayoutParams();
    if (localObject != null)
    {
      int j = -2147483648;
      int k = -2147483648;
      int i = j;
      if (((ViewGroup.LayoutParams)localObject).width != -2)
      {
        i = j;
        if (((ViewGroup.LayoutParams)localObject).width != -1)
          i = ((ViewGroup.LayoutParams)localObject).width;
      }
      j = k;
      if (((ViewGroup.LayoutParams)localObject).height != -2)
      {
        j = k;
        if (((ViewGroup.LayoutParams)localObject).height != -1)
          j = ((ViewGroup.LayoutParams)localObject).height;
      }
      setViewSize(paramView, i, j);
      setPadding(paramView, paramView.getPaddingLeft(), paramView.getPaddingTop(), paramView.getPaddingRight(), paramView.getPaddingBottom());
    }
    if ((paramView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams))
    {
      localObject = (ViewGroup.MarginLayoutParams)paramView.getLayoutParams();
      if (localObject != null)
        setMargin(paramView, ((ViewGroup.MarginLayoutParams)localObject).leftMargin, ((ViewGroup.MarginLayoutParams)localObject).topMargin, ((ViewGroup.MarginLayoutParams)localObject).rightMargin, ((ViewGroup.MarginLayoutParams)localObject).bottomMargin);
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

  public static void setMargin(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = scale(paramView.getContext(), paramInt1);
    int j = scale(paramView.getContext(), paramInt2);
    int k = scale(paramView.getContext(), paramInt3);
    int m = scale(paramView.getContext(), paramInt4);
    if ((paramView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams))
    {
      ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)paramView.getLayoutParams();
      if (localMarginLayoutParams != null)
      {
        if (paramInt1 != -2147483648)
          localMarginLayoutParams.leftMargin = i;
        if (paramInt3 != -2147483648)
          localMarginLayoutParams.rightMargin = k;
        if (paramInt2 != -2147483648)
          localMarginLayoutParams.topMargin = j;
        if (paramInt4 != -2147483648)
          localMarginLayoutParams.bottomMargin = m;
        paramView.setLayoutParams(localMarginLayoutParams);
      }
    }
  }

  public static void setPadding(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramView.setPadding(scale(paramView.getContext(), paramInt1), scale(paramView.getContext(), paramInt2), scale(paramView.getContext(), paramInt3), scale(paramView.getContext(), paramInt4));
  }

  public static void setSPTextSize(TextView paramTextView, float paramFloat)
  {
    paramTextView.setTextSize(scale(paramTextView.getContext(), paramFloat));
  }

  public static void setTextSize(Context paramContext, Paint paramPaint, float paramFloat)
  {
    paramPaint.setTextSize(scale(paramContext, paramFloat));
  }

  public static void setTextSize(Context paramContext, TextPaint paramTextPaint, float paramFloat)
  {
    paramTextPaint.setTextSize(scale(paramContext, paramFloat));
  }

  public static void setTextSize(TextView paramTextView, float paramFloat)
  {
    paramTextView.setTextSize(0, scale(paramTextView.getContext(), paramFloat));
  }

  public static void setViewSize(View paramView, int paramInt1, int paramInt2)
  {
    int i = scale(paramView.getContext(), paramInt1);
    int j = scale(paramView.getContext(), paramInt2);
    ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
    if (localLayoutParams == null)
    {
      AbLogUtil.e(AbViewUtil.class, "setViewSize出错,如果是代码new出来的View，需要设置一个适合的LayoutParams");
      return;
    }
    if (paramInt1 != -2147483648)
      localLayoutParams.width = i;
    if (paramInt2 != -2147483648)
      localLayoutParams.height = j;
    paramView.setLayoutParams(localLayoutParams);
  }

  public static float sp2px(Context paramContext, float paramFloat)
  {
    return applyDimension(2, paramFloat, AbAppUtil.getDisplayMetrics(paramContext));
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.andbase.util.AbViewUtil
 * JD-Core Version:    0.6.2
 */