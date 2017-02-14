package com.ismartgo.app.andbase.util;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

public class AbProgressDialogFragment extends DialogFragment
{
  static View mContentView;
  int mIndeterminateDrawable;
  String mMessage;

  public static AbProgressDialogFragment newInstance(int paramInt, String paramString)
  {
    AbProgressDialogFragment localAbProgressDialogFragment = new AbProgressDialogFragment();
    Bundle localBundle = new Bundle();
    localBundle.putInt("indeterminateDrawable", paramInt);
    localBundle.putString("message", paramString);
    localAbProgressDialogFragment.setArguments(localBundle);
    return localAbProgressDialogFragment;
  }

  public Dialog onCreateDialog(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mIndeterminateDrawable = getArguments().getInt("indeterminateDrawable");
    this.mMessage = getArguments().getString("message");
    paramBundle = new ProgressDialog(getActivity(), 3);
    if (this.mIndeterminateDrawable > 0)
      paramBundle.setIndeterminateDrawable(getActivity().getResources().getDrawable(this.mIndeterminateDrawable));
    if (this.mMessage != null)
      paramBundle.setMessage(this.mMessage);
    return paramBundle;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.andbase.util.AbProgressDialogFragment
 * JD-Core Version:    0.6.2
 */