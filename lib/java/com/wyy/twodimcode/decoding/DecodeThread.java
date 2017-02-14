package com.wyy.twodimcode.decoding;

import android.os.Handler;
import android.os.Looper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.ResultPointCallback;
import com.ismartgo.app.activity.CaptureActivity;
import java.util.Hashtable;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

final class DecodeThread extends Thread
{
  public static final String BARCODE_BITMAP = "barcode_bitmap";
  private final CaptureActivity activity;
  private Handler handler;
  private final CountDownLatch handlerInitLatch;
  private final Hashtable<DecodeHintType, Object> hints;

  DecodeThread(CaptureActivity paramCaptureActivity, Vector<BarcodeFormat> paramVector, String paramString, ResultPointCallback paramResultPointCallback)
  {
    this.activity = paramCaptureActivity;
    this.handlerInitLatch = new CountDownLatch(1);
    this.hints = new Hashtable(3);
    if (paramVector != null)
    {
      paramCaptureActivity = paramVector;
      if (!paramVector.isEmpty());
    }
    else
    {
      paramCaptureActivity = new Vector();
      paramCaptureActivity.addAll(DecodeFormatManager.ONE_D_FORMATS);
      paramCaptureActivity.addAll(DecodeFormatManager.QR_CODE_FORMATS);
      paramCaptureActivity.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
    }
    this.hints.put(DecodeHintType.POSSIBLE_FORMATS, paramCaptureActivity);
    if (paramString != null)
      this.hints.put(DecodeHintType.CHARACTER_SET, paramString);
    this.hints.put(DecodeHintType.NEED_RESULT_POINT_CALLBACK, paramResultPointCallback);
  }

  Handler getHandler()
  {
    try
    {
      this.handlerInitLatch.await();
      label7: return this.handler;
    }
    catch (InterruptedException localInterruptedException)
    {
      break label7;
    }
  }

  public void run()
  {
    Looper.prepare();
    this.handler = new DecodeHandler(this.activity, this.hints);
    this.handlerInitLatch.countDown();
    Looper.loop();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.wyy.twodimcode.decoding.DecodeThread
 * JD-Core Version:    0.6.2
 */