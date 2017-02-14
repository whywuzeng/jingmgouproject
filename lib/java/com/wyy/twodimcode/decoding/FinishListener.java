package com.wyy.twodimcode.decoding;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;

public final class FinishListener
  implements DialogInterface.OnClickListener, DialogInterface.OnCancelListener, Runnable
{
  private final Activity activityToFinish;

  public FinishListener(Activity paramActivity)
  {
    this.activityToFinish = paramActivity;
  }

  public void onCancel(DialogInterface paramDialogInterface)
  {
    run();
  }

  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    run();
  }

  public void run()
  {
    this.activityToFinish.finish();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.wyy.twodimcode.decoding.FinishListener
 * JD-Core Version:    0.6.2
 */