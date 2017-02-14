package com.wyy.twodimcode.camera;

import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.os.Handler;
import android.util.Log;

final class AutoFocusCallback
  implements Camera.AutoFocusCallback
{
  private static final long AUTOFOCUS_INTERVAL_MS = 1500L;
  private static final String TAG = AutoFocusCallback.class.getSimpleName();
  private Handler autoFocusHandler;
  private int autoFocusMessage;

  public void onAutoFocus(boolean paramBoolean, Camera paramCamera)
  {
    if (this.autoFocusHandler != null)
    {
      paramCamera = this.autoFocusHandler.obtainMessage(this.autoFocusMessage, Boolean.valueOf(paramBoolean));
      this.autoFocusHandler.sendMessageDelayed(paramCamera, 1500L);
      this.autoFocusHandler = null;
      return;
    }
    Log.d(TAG, "Got auto-focus callback, but no handler for it");
  }

  void setHandler(Handler paramHandler, int paramInt)
  {
    this.autoFocusHandler = paramHandler;
    this.autoFocusMessage = paramInt;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.wyy.twodimcode.camera.AutoFocusCallback
 * JD-Core Version:    0.6.2
 */