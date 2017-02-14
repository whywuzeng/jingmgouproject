package com.ismartgo.app.andbase.util;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;

public class AbAlertDialogFragment extends DialogFragment
{
  static View mContentView;
  static AbDialogOnClickListener mOnClickListener;
  int mIcon;
  String mMessage;
  String mTitle;

  public static AbAlertDialogFragment newInstance(int paramInt, String paramString1, String paramString2, View paramView, AbDialogOnClickListener paramAbDialogOnClickListener)
  {
    AbAlertDialogFragment localAbAlertDialogFragment = new AbAlertDialogFragment();
    mOnClickListener = paramAbDialogOnClickListener;
    mContentView = paramView;
    paramView = new Bundle();
    paramView.putInt("icon", paramInt);
    paramView.putString("title", paramString1);
    paramView.putString("message", paramString2);
    localAbAlertDialogFragment.setArguments(paramView);
    return localAbAlertDialogFragment;
  }

  public Dialog onCreateDialog(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mIcon = getArguments().getInt("icon");
    this.mTitle = getArguments().getString("title");
    this.mMessage = getArguments().getString("message");
    paramBundle = new AlertDialog.Builder(getActivity(), 3);
    if (this.mIcon > 0)
      paramBundle.setIcon(this.mIcon);
    if (this.mTitle != null)
      paramBundle.setTitle(this.mTitle);
    if (this.mMessage != null)
      paramBundle.setMessage(this.mMessage);
    if (mContentView != null)
      paramBundle.setView(mContentView);
    if (mOnClickListener != null)
    {
      paramBundle.setPositiveButton("确认", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          if (AbAlertDialogFragment.mOnClickListener != null)
            AbAlertDialogFragment.mOnClickListener.onPositiveClick();
        }
      });
      paramBundle.setNegativeButton("取消", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          if (AbAlertDialogFragment.mOnClickListener != null)
            AbAlertDialogFragment.mOnClickListener.onNegativeClick();
        }
      });
    }
    return paramBundle.create();
  }

  public static abstract interface AbDialogOnClickListener
  {
    public abstract void onNegativeClick();

    public abstract void onPositiveClick();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.andbase.util.AbAlertDialogFragment
 * JD-Core Version:    0.6.2
 */