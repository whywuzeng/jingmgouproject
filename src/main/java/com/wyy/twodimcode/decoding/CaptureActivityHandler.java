package com.wyy.twodimcode.decoding;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.ismartgo.app.activity.CaptureActivity;
import com.wyy.twodimcode.camera.CameraManager;
import com.wyy.twodimcode.view.ViewfinderResultPointCallback;
import java.util.Vector;

public final class CaptureActivityHandler extends Handler
{
  private static final String TAG = CaptureActivityHandler.class.getSimpleName();
  private final CaptureActivity activity;
  private final DecodeThread decodeThread;
  private State state;

  public CaptureActivityHandler(CaptureActivity paramCaptureActivity, Vector<BarcodeFormat> paramVector, String paramString)
  {
    this.activity = paramCaptureActivity;
    this.decodeThread = new DecodeThread(paramCaptureActivity, paramVector, paramString, new ViewfinderResultPointCallback(paramCaptureActivity.getViewfinderView()));
    this.decodeThread.start();
    this.state = State.SUCCESS;
    CameraManager.get().startPreview();
    restartPreviewAndDecode();
  }

  private void restartPreviewAndDecode()
  {
    if (this.state == State.SUCCESS)
    {
      this.state = State.PREVIEW;
      CameraManager.get().requestPreviewFrame(this.decodeThread.getHandler(), 2131230721);
      CameraManager.get().requestAutoFocus(this, 2131230720);
      this.activity.drawViewfinder();
    }
  }

  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    case 2131230721:
    case 2131230724:
    case 2131230725:
    case 2131230727:
    default:
    case 2131230720:
      do
        return;
      while (this.state != State.PREVIEW);
      CameraManager.get().requestAutoFocus(this, 2131230720);
      return;
    case 2131230728:
      Log.d(TAG, "Got restart preview message");
      restartPreviewAndDecode();
      return;
    case 2131230723:
      Log.d(TAG, "Got decode succeeded message");
      this.state = State.SUCCESS;
      Object localObject = paramMessage.getData();
      if (localObject == null);
      for (localObject = null; ; localObject = (Bitmap)((Bundle)localObject).getParcelable("barcode_bitmap"))
      {
        this.activity.handleDecode((Result)paramMessage.obj, (Bitmap)localObject);
        return;
      }
    case 2131230722:
      this.state = State.PREVIEW;
      CameraManager.get().requestPreviewFrame(this.decodeThread.getHandler(), 2131230721);
      return;
    case 2131230729:
      Log.d(TAG, "Got return scan result message");
      this.activity.setResult(-1, (Intent)paramMessage.obj);
      this.activity.finish();
      return;
    case 2131230726:
    }
    Log.d(TAG, "Got product query message");
    paramMessage = new Intent("android.intent.action.VIEW", Uri.parse((String)paramMessage.obj));
    paramMessage.addFlags(524288);
    this.activity.startActivity(paramMessage);
  }

  public void quitSynchronously()
  {
    this.state = State.DONE;
    CameraManager.get().stopPreview();
    Message.obtain(this.decodeThread.getHandler(), 2131230727).sendToTarget();
    try
    {
      this.decodeThread.join();
      label35: removeMessages(2131230723);
      removeMessages(2131230722);
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      break label35;
    }
  }

  private static enum State
  {
    PREVIEW, SUCCESS, DONE;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.wyy.twodimcode.decoding.CaptureActivityHandler
 * JD-Core Version:    0.6.2
 */