package com.ismartgo.app.andbase.util;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.view.View;

public class AbDialogFragment extends DialogFragment
{
  private AbDialogOnLoadListener mAbDialogOnLoadListener = null;
  private View mIndeterminateView = null;
  public String mMessage;
  private DialogInterface.OnCancelListener mOnCancelListener = null;
  private DialogInterface.OnDismissListener mOnDismissListener = null;

  public AbDialogOnLoadListener getAbDialogOnLoadListener()
  {
    return this.mAbDialogOnLoadListener;
  }

  public String getMessage()
  {
    return this.mMessage;
  }

  public DialogInterface.OnCancelListener getOnCancelListener()
  {
    return this.mOnCancelListener;
  }

  public DialogInterface.OnDismissListener getOnDismissListener()
  {
    return this.mOnDismissListener;
  }

  public void load(View paramView)
  {
    if (this.mAbDialogOnLoadListener != null)
      this.mAbDialogOnLoadListener.onLoad();
    this.mIndeterminateView = paramView;
    AbAnimationUtil.playRotateAnimation(this.mIndeterminateView, 300L, -1, 1);
  }

  public void loadFinish()
  {
    loadStop();
    AbDialogUtil.removeDialog(getActivity());
  }

  public void loadStop()
  {
    this.mIndeterminateView.postDelayed(new Runnable()
    {
      public void run()
      {
        AbDialogFragment.this.mIndeterminateView.clearAnimation();
      }
    }
    , 200L);
  }

  public void onCancel(DialogInterface paramDialogInterface)
  {
    if (this.mOnCancelListener != null)
      this.mOnCancelListener.onCancel(paramDialogInterface);
    super.onCancel(paramDialogInterface);
  }

  public void onDismiss(DialogInterface paramDialogInterface)
  {
    if (this.mOnDismissListener != null)
      this.mOnDismissListener.onDismiss(paramDialogInterface);
    super.onDismiss(paramDialogInterface);
  }

  public void setAbDialogOnLoadListener(AbDialogOnLoadListener paramAbDialogOnLoadListener)
  {
    this.mAbDialogOnLoadListener = paramAbDialogOnLoadListener;
  }

  public void setMessage(String paramString)
  {
    this.mMessage = paramString;
  }

  public void setOnCancelListener(DialogInterface.OnCancelListener paramOnCancelListener)
  {
    this.mOnCancelListener = paramOnCancelListener;
  }

  public void setOnDismissListener(DialogInterface.OnDismissListener paramOnDismissListener)
  {
    this.mOnDismissListener = paramOnDismissListener;
  }

  public static abstract interface AbDialogOnLoadListener
  {
    public abstract void onLoad();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.andbase.util.AbDialogFragment
 * JD-Core Version:    0.6.2
 */