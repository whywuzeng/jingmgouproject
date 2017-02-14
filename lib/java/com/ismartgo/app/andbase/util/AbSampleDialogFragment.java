package com.ismartgo.app.andbase.util;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AbSampleDialogFragment extends DialogFragment
{
  private View mContentView;
  private DialogInterface.OnCancelListener mOnCancelListener = null;
  private DialogInterface.OnDismissListener mOnDismissListener = null;
  private int mStyle;
  private int mTheme;

  public static AbSampleDialogFragment newInstance(int paramInt1, int paramInt2)
  {
    AbSampleDialogFragment localAbSampleDialogFragment = new AbSampleDialogFragment();
    Bundle localBundle = new Bundle();
    localBundle.putInt("style", paramInt1);
    localBundle.putInt("theme", paramInt2);
    localAbSampleDialogFragment.setArguments(localBundle);
    return localAbSampleDialogFragment;
  }

  public View getContentView()
  {
    return this.mContentView;
  }

  public DialogInterface.OnCancelListener getOnCancelListener()
  {
    return this.mOnCancelListener;
  }

  public DialogInterface.OnDismissListener getOnDismissListener()
  {
    return this.mOnDismissListener;
  }

  public void onCancel(DialogInterface paramDialogInterface)
  {
    if (this.mOnCancelListener != null)
      this.mOnCancelListener.onCancel(paramDialogInterface);
    super.onCancel(paramDialogInterface);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mStyle = getArguments().getInt("style");
    this.mTheme = getArguments().getInt("theme");
    setStyle(this.mStyle, this.mTheme);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return this.mContentView;
  }

  public void onDismiss(DialogInterface paramDialogInterface)
  {
    if (this.mOnDismissListener != null)
      this.mOnDismissListener.onDismiss(paramDialogInterface);
    super.onDismiss(paramDialogInterface);
  }

  public void setContentView(View paramView)
  {
    this.mContentView = paramView;
  }

  public void setOnCancelListener(DialogInterface.OnCancelListener paramOnCancelListener)
  {
    this.mOnCancelListener = paramOnCancelListener;
  }

  public void setOnDismissListener(DialogInterface.OnDismissListener paramOnDismissListener)
  {
    this.mOnDismissListener = paramOnDismissListener;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.andbase.util.AbSampleDialogFragment
 * JD-Core Version:    0.6.2
 */