package com.ismartgo.app.andbase.util;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class AbDialogUtil
{
  private static String mDialogTag = "dialog";

  public static void removeDialog(Context paramContext)
  {
    try
    {
      Object localObject = (FragmentActivity)paramContext;
      paramContext = ((FragmentActivity)localObject).getFragmentManager().beginTransaction();
      paramContext.setTransition(8194);
      localObject = ((FragmentActivity)localObject).getFragmentManager().findFragmentByTag(mDialogTag);
      if (localObject != null)
        paramContext.remove((Fragment)localObject);
      paramContext.addToBackStack(null);
      paramContext.commit();
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }

  public static void removeDialog(View paramView)
  {
    removeDialog(paramView.getContext());
    AbViewUtil.removeSelfFromParent(paramView);
  }

  public static AbAlertDialogFragment showAlertDialog(int paramInt, String paramString, View paramView)
  {
    FragmentActivity localFragmentActivity = (FragmentActivity)paramView.getContext();
    removeDialog(localFragmentActivity);
    paramString = AbAlertDialogFragment.newInstance(paramInt, paramString, null, paramView, null);
    paramView = localFragmentActivity.getFragmentManager().beginTransaction();
    paramView.setTransition(4097);
    paramString.show(paramView, mDialogTag);
    return paramString;
  }

  public static AbAlertDialogFragment showAlertDialog(int paramInt, String paramString, View paramView, AbAlertDialogFragment.AbDialogOnClickListener paramAbDialogOnClickListener)
  {
    FragmentActivity localFragmentActivity = (FragmentActivity)paramView.getContext();
    removeDialog(localFragmentActivity);
    paramString = AbAlertDialogFragment.newInstance(paramInt, paramString, null, paramView, paramAbDialogOnClickListener);
    paramView = localFragmentActivity.getFragmentManager().beginTransaction();
    paramView.setTransition(4097);
    paramString.show(paramView, mDialogTag);
    return paramString;
  }

  public static AbAlertDialogFragment showAlertDialog(Context paramContext, int paramInt, String paramString1, String paramString2)
  {
    paramContext = (FragmentActivity)paramContext;
    removeDialog(paramContext);
    paramString1 = AbAlertDialogFragment.newInstance(paramInt, paramString1, paramString2, null, null);
    paramContext = paramContext.getFragmentManager().beginTransaction();
    paramContext.setTransition(4097);
    paramString1.show(paramContext, mDialogTag);
    return paramString1;
  }

  public static AbAlertDialogFragment showAlertDialog(Context paramContext, int paramInt, String paramString1, String paramString2, AbAlertDialogFragment.AbDialogOnClickListener paramAbDialogOnClickListener)
  {
    paramContext = (FragmentActivity)paramContext;
    removeDialog(paramContext);
    paramString1 = AbAlertDialogFragment.newInstance(paramInt, paramString1, paramString2, null, paramAbDialogOnClickListener);
    paramContext = paramContext.getFragmentManager().beginTransaction();
    paramContext.setTransition(4097);
    paramString1.show(paramContext, mDialogTag);
    return paramString1;
  }

  public static AbAlertDialogFragment showAlertDialog(Context paramContext, String paramString)
  {
    paramContext = (FragmentActivity)paramContext;
    removeDialog(paramContext);
    paramString = AbAlertDialogFragment.newInstance(0, null, paramString, null, null);
    paramContext = paramContext.getFragmentManager().beginTransaction();
    paramContext.setTransition(4097);
    paramString.show(paramContext, mDialogTag);
    return paramString;
  }

  public static AbAlertDialogFragment showAlertDialog(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = (FragmentActivity)paramContext;
    removeDialog(paramContext);
    paramString1 = AbAlertDialogFragment.newInstance(0, paramString1, paramString2, null, null);
    paramContext = paramContext.getFragmentManager().beginTransaction();
    paramContext.setTransition(4097);
    paramString1.show(paramContext, mDialogTag);
    return paramString1;
  }

  public static AbAlertDialogFragment showAlertDialog(Context paramContext, String paramString1, String paramString2, AbAlertDialogFragment.AbDialogOnClickListener paramAbDialogOnClickListener)
  {
    paramContext = (FragmentActivity)paramContext;
    removeDialog(paramContext);
    paramString1 = AbAlertDialogFragment.newInstance(0, paramString1, paramString2, null, paramAbDialogOnClickListener);
    paramContext = paramContext.getFragmentManager().beginTransaction();
    paramContext.setTransition(4097);
    paramString1.show(paramContext, mDialogTag);
    return paramString1;
  }

  public static AbAlertDialogFragment showAlertDialog(View paramView)
  {
    Object localObject = (FragmentActivity)paramView.getContext();
    removeDialog((Context)localObject);
    paramView = AbAlertDialogFragment.newInstance(0, null, null, paramView, null);
    localObject = ((FragmentActivity)localObject).getFragmentManager().beginTransaction();
    ((FragmentTransaction)localObject).setTransition(4097);
    paramView.show((FragmentTransaction)localObject, mDialogTag);
    return paramView;
  }

  public static AbAlertDialogFragment showAlertDialog(String paramString, View paramView)
  {
    FragmentActivity localFragmentActivity = (FragmentActivity)paramView.getContext();
    removeDialog(localFragmentActivity);
    paramString = AbAlertDialogFragment.newInstance(0, paramString, null, paramView, null);
    paramView = localFragmentActivity.getFragmentManager().beginTransaction();
    paramView.setTransition(4097);
    paramString.show(paramView, mDialogTag);
    return paramString;
  }

  public static AbAlertDialogFragment showAlertDialog(String paramString, View paramView, AbAlertDialogFragment.AbDialogOnClickListener paramAbDialogOnClickListener)
  {
    FragmentActivity localFragmentActivity = (FragmentActivity)paramView.getContext();
    removeDialog(localFragmentActivity);
    paramString = AbAlertDialogFragment.newInstance(0, paramString, null, paramView, paramAbDialogOnClickListener);
    paramView = localFragmentActivity.getFragmentManager().beginTransaction();
    paramView.setTransition(4097);
    paramString.show(paramView, mDialogTag);
    return paramString;
  }

  public static AbSampleDialogFragment showDialog(View paramView)
  {
    FragmentActivity localFragmentActivity = (FragmentActivity)paramView.getContext();
    removeDialog(localFragmentActivity);
    AbSampleDialogFragment localAbSampleDialogFragment = AbSampleDialogFragment.newInstance(1, 16973939);
    localAbSampleDialogFragment.setContentView(paramView);
    paramView = localFragmentActivity.getFragmentManager().beginTransaction();
    paramView.setTransition(4097);
    localAbSampleDialogFragment.show(paramView, mDialogTag);
    return localAbSampleDialogFragment;
  }

  @SuppressLint({"NewApi"})
  public static AbSampleDialogFragment showDialog(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    FragmentActivity localFragmentActivity = (FragmentActivity)paramView.getContext();
    removeDialog(localFragmentActivity);
    AbSampleDialogFragment localAbSampleDialogFragment = AbSampleDialogFragment.newInstance(1, 16973939);
    localAbSampleDialogFragment.setContentView(paramView);
    paramView = localFragmentActivity.getFragmentManager().beginTransaction();
    paramView.setCustomAnimations(paramInt1, paramInt2, paramInt3, paramInt4);
    paramView.setTransition(4097);
    localAbSampleDialogFragment.show(paramView, mDialogTag);
    return localAbSampleDialogFragment;
  }

  @SuppressLint({"NewApi"})
  public static AbSampleDialogFragment showDialog(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    FragmentActivity localFragmentActivity = (FragmentActivity)paramView.getContext();
    removeDialog(localFragmentActivity);
    AbSampleDialogFragment localAbSampleDialogFragment = AbSampleDialogFragment.newInstance(1, 16973939);
    localAbSampleDialogFragment.setContentView(paramView);
    paramView = localFragmentActivity.getFragmentManager().beginTransaction();
    paramView.setCustomAnimations(paramInt1, paramInt2, paramInt3, paramInt4);
    paramView.setTransition(4097);
    localAbSampleDialogFragment.setOnCancelListener(paramOnCancelListener);
    localAbSampleDialogFragment.show(paramView, mDialogTag);
    return localAbSampleDialogFragment;
  }

  public static AbSampleDialogFragment showDialog(View paramView, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    FragmentActivity localFragmentActivity = (FragmentActivity)paramView.getContext();
    removeDialog(localFragmentActivity);
    AbSampleDialogFragment localAbSampleDialogFragment = AbSampleDialogFragment.newInstance(1, 16973939);
    localAbSampleDialogFragment.setContentView(paramView);
    paramView = localFragmentActivity.getFragmentManager().beginTransaction();
    paramView.setTransition(4097);
    localAbSampleDialogFragment.setOnCancelListener(paramOnCancelListener);
    localAbSampleDialogFragment.show(paramView, mDialogTag);
    return localAbSampleDialogFragment;
  }

  public static AbSampleDialogFragment showFragment(View paramView)
  {
    removeDialog(paramView);
    FragmentActivity localFragmentActivity = (FragmentActivity)paramView.getContext();
    AbSampleDialogFragment localAbSampleDialogFragment = AbSampleDialogFragment.newInstance(1, 16973934);
    localAbSampleDialogFragment.setContentView(paramView);
    paramView = localFragmentActivity.getFragmentManager().beginTransaction();
    paramView.setTransition(4097);
    paramView.add(16908290, localAbSampleDialogFragment, mDialogTag).addToBackStack(null).commit();
    return localAbSampleDialogFragment;
  }

  public static AbLoadDialogFragment showLoadDialog(Context paramContext, int paramInt, String paramString)
  {
    FragmentActivity localFragmentActivity = (FragmentActivity)paramContext;
    removeDialog(localFragmentActivity);
    paramContext = AbLoadDialogFragment.newInstance(1, 16973939);
    paramContext.setIndeterminateDrawable(paramInt);
    paramContext.setMessage(paramString);
    paramString = localFragmentActivity.getFragmentManager().beginTransaction();
    paramString.setTransition(4097);
    paramContext.show(paramString, mDialogTag);
    return paramContext;
  }

  public static AbLoadDialogFragment showLoadDialog(Context paramContext, int paramInt, String paramString, AbDialogFragment.AbDialogOnLoadListener paramAbDialogOnLoadListener)
  {
    FragmentActivity localFragmentActivity = (FragmentActivity)paramContext;
    removeDialog(localFragmentActivity);
    paramContext = AbLoadDialogFragment.newInstance(1, 16973939);
    paramContext.setIndeterminateDrawable(paramInt);
    paramContext.setMessage(paramString);
    paramContext.setAbDialogOnLoadListener(paramAbDialogOnLoadListener);
    paramString = localFragmentActivity.getFragmentManager().beginTransaction();
    paramString.setTransition(4097);
    paramContext.show(paramString, mDialogTag);
    return paramContext;
  }

  public static AbLoadDialogFragment showLoadPanel(Context paramContext, int paramInt, String paramString)
  {
    FragmentActivity localFragmentActivity = (FragmentActivity)paramContext;
    removeDialog(localFragmentActivity);
    paramContext = AbLoadDialogFragment.newInstance(1, 16973914);
    paramContext.setIndeterminateDrawable(paramInt);
    paramContext.setMessage(paramString);
    paramString = localFragmentActivity.getFragmentManager().beginTransaction();
    paramString.setTransition(4097);
    paramContext.show(paramString, mDialogTag);
    return paramContext;
  }

  public static AbLoadDialogFragment showLoadPanel(Context paramContext, int paramInt, String paramString, AbDialogFragment.AbDialogOnLoadListener paramAbDialogOnLoadListener)
  {
    FragmentActivity localFragmentActivity = (FragmentActivity)paramContext;
    removeDialog(localFragmentActivity);
    paramContext = AbLoadDialogFragment.newInstance(1, 16973914);
    paramContext.setIndeterminateDrawable(paramInt);
    paramContext.setMessage(paramString);
    paramContext.setAbDialogOnLoadListener(paramAbDialogOnLoadListener);
    paramString = localFragmentActivity.getFragmentManager().beginTransaction();
    paramString.setTransition(4097);
    paramContext.show(paramString, mDialogTag);
    return paramContext;
  }

  public static AbSampleDialogFragment showPanel(View paramView)
  {
    FragmentActivity localFragmentActivity = (FragmentActivity)paramView.getContext();
    removeDialog(localFragmentActivity);
    AbSampleDialogFragment localAbSampleDialogFragment = AbSampleDialogFragment.newInstance(1, 16973914);
    localAbSampleDialogFragment.setContentView(paramView);
    paramView = localFragmentActivity.getFragmentManager().beginTransaction();
    paramView.setTransition(4097);
    localAbSampleDialogFragment.show(paramView, mDialogTag);
    return localAbSampleDialogFragment;
  }

  public static AbSampleDialogFragment showPanel(View paramView, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    FragmentActivity localFragmentActivity = (FragmentActivity)paramView.getContext();
    removeDialog(localFragmentActivity);
    AbSampleDialogFragment localAbSampleDialogFragment = AbSampleDialogFragment.newInstance(1, 16973914);
    localAbSampleDialogFragment.setContentView(paramView);
    paramView = localFragmentActivity.getFragmentManager().beginTransaction();
    paramView.setTransition(4097);
    localAbSampleDialogFragment.setOnCancelListener(paramOnCancelListener);
    localAbSampleDialogFragment.show(paramView, mDialogTag);
    return localAbSampleDialogFragment;
  }

  public static AbProgressDialogFragment showProgressDialog(Context paramContext, int paramInt, String paramString)
  {
    paramContext = (FragmentActivity)paramContext;
    removeDialog(paramContext);
    paramString = AbProgressDialogFragment.newInstance(paramInt, paramString);
    paramContext = paramContext.getFragmentManager().beginTransaction();
    paramContext.setTransition(4097);
    paramString.show(paramContext, mDialogTag);
    return paramString;
  }

  public static AbRefreshDialogFragment showRefreshDialog(Context paramContext, int paramInt, String paramString)
  {
    FragmentActivity localFragmentActivity = (FragmentActivity)paramContext;
    removeDialog(localFragmentActivity);
    paramContext = AbRefreshDialogFragment.newInstance(1, 16973939);
    paramContext.setIndeterminateDrawable(paramInt);
    paramContext.setMessage(paramString);
    paramContext.setAbDialogOnLoadListener(null);
    paramString = localFragmentActivity.getFragmentManager().beginTransaction();
    paramString.setTransition(4097);
    paramContext.show(paramString, mDialogTag);
    return paramContext;
  }

  public static AbRefreshDialogFragment showRefreshDialog(Context paramContext, int paramInt, String paramString, AbDialogFragment.AbDialogOnLoadListener paramAbDialogOnLoadListener)
  {
    FragmentActivity localFragmentActivity = (FragmentActivity)paramContext;
    removeDialog(localFragmentActivity);
    paramContext = AbRefreshDialogFragment.newInstance(1, 16973939);
    paramContext.setIndeterminateDrawable(paramInt);
    paramContext.setMessage(paramString);
    paramContext.setAbDialogOnLoadListener(paramAbDialogOnLoadListener);
    paramString = localFragmentActivity.getFragmentManager().beginTransaction();
    paramString.setTransition(4097);
    paramContext.show(paramString, mDialogTag);
    return paramContext;
  }

  public static AbRefreshDialogFragment showRefreshPanel(Context paramContext, int paramInt, String paramString)
  {
    FragmentActivity localFragmentActivity = (FragmentActivity)paramContext;
    removeDialog(localFragmentActivity);
    paramContext = AbRefreshDialogFragment.newInstance(1, 16973914);
    paramContext.setIndeterminateDrawable(paramInt);
    paramContext.setMessage(paramString);
    paramContext.setAbDialogOnLoadListener(null);
    paramString = localFragmentActivity.getFragmentManager().beginTransaction();
    paramString.setTransition(4097);
    paramContext.show(paramString, mDialogTag);
    return paramContext;
  }

  public static AbRefreshDialogFragment showRefreshPanel(Context paramContext, int paramInt, String paramString, AbDialogFragment.AbDialogOnLoadListener paramAbDialogOnLoadListener)
  {
    FragmentActivity localFragmentActivity = (FragmentActivity)paramContext;
    removeDialog(localFragmentActivity);
    paramContext = AbRefreshDialogFragment.newInstance(1, 16973914);
    paramContext.setIndeterminateDrawable(paramInt);
    paramContext.setMessage(paramString);
    paramContext.setAbDialogOnLoadListener(paramAbDialogOnLoadListener);
    paramString = localFragmentActivity.getFragmentManager().beginTransaction();
    paramString.setTransition(4097);
    paramContext.show(paramString, mDialogTag);
    return paramContext;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.andbase.util.AbDialogUtil
 * JD-Core Version:    0.6.2
 */