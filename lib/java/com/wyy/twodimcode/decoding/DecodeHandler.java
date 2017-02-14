package com.wyy.twodimcode.decoding;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.ismartgo.app.activity.CaptureActivity;
import java.util.Hashtable;

final class DecodeHandler extends Handler
{
  private static final String TAG = DecodeHandler.class.getSimpleName();
  private final CaptureActivity activity;
  private final MultiFormatReader multiFormatReader = new MultiFormatReader();

  DecodeHandler(CaptureActivity paramCaptureActivity, Hashtable<DecodeHintType, Object> paramHashtable)
  {
    this.multiFormatReader.setHints(paramHashtable);
    this.activity = paramCaptureActivity;
  }

  // ERROR //
  private void decode(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: aload_1
    //   1: arraylength
    //   2: newarray byte
    //   4: astore 10
    //   6: iconst_0
    //   7: istore 4
    //   9: iload 4
    //   11: iload_3
    //   12: if_icmplt +154 -> 166
    //   15: invokestatic 48	java/lang/System:currentTimeMillis	()J
    //   18: lstore 6
    //   20: aconst_null
    //   21: astore_1
    //   22: invokestatic 54	com/wyy/twodimcode/camera/CameraManager:get	()Lcom/wyy/twodimcode/camera/CameraManager;
    //   25: aload 10
    //   27: iload_3
    //   28: iload_2
    //   29: invokevirtual 58	com/wyy/twodimcode/camera/CameraManager:buildLuminanceSource	([BII)Lcom/wyy/twodimcode/camera/PlanarYUVLuminanceSource;
    //   32: astore 11
    //   34: new 60	com/google/zxing/BinaryBitmap
    //   37: dup
    //   38: new 62	com/google/zxing/common/HybridBinarizer
    //   41: dup
    //   42: aload 11
    //   44: invokespecial 65	com/google/zxing/common/HybridBinarizer:<init>	(Lcom/google/zxing/LuminanceSource;)V
    //   47: invokespecial 68	com/google/zxing/BinaryBitmap:<init>	(Lcom/google/zxing/Binarizer;)V
    //   50: astore 10
    //   52: aload_0
    //   53: getfield 30	com/wyy/twodimcode/decoding/DecodeHandler:multiFormatReader	Lcom/google/zxing/MultiFormatReader;
    //   56: aload 10
    //   58: invokevirtual 72	com/google/zxing/MultiFormatReader:decodeWithState	(Lcom/google/zxing/BinaryBitmap;)Lcom/google/zxing/Result;
    //   61: astore 10
    //   63: aload 10
    //   65: astore_1
    //   66: aload_0
    //   67: getfield 30	com/wyy/twodimcode/decoding/DecodeHandler:multiFormatReader	Lcom/google/zxing/MultiFormatReader;
    //   70: invokevirtual 75	com/google/zxing/MultiFormatReader:reset	()V
    //   73: aload_1
    //   74: ifnull +164 -> 238
    //   77: invokestatic 48	java/lang/System:currentTimeMillis	()J
    //   80: lstore 8
    //   82: ldc 77
    //   84: new 79	java/lang/StringBuilder
    //   87: dup
    //   88: ldc 81
    //   90: invokespecial 84	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   93: lload 8
    //   95: lload 6
    //   97: lsub
    //   98: invokevirtual 88	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   101: ldc 90
    //   103: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: aload_1
    //   107: invokevirtual 98	com/google/zxing/Result:toString	()Ljava/lang/String;
    //   110: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   113: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   116: invokestatic 105	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   119: pop
    //   120: aload_0
    //   121: getfield 36	com/wyy/twodimcode/decoding/DecodeHandler:activity	Lcom/ismartgo/app/activity/CaptureActivity;
    //   124: invokevirtual 111	com/ismartgo/app/activity/CaptureActivity:getHandler	()Landroid/os/Handler;
    //   127: ldc 112
    //   129: aload_1
    //   130: invokestatic 118	android/os/Message:obtain	(Landroid/os/Handler;ILjava/lang/Object;)Landroid/os/Message;
    //   133: astore_1
    //   134: new 120	android/os/Bundle
    //   137: dup
    //   138: invokespecial 121	android/os/Bundle:<init>	()V
    //   141: astore 10
    //   143: aload 10
    //   145: ldc 123
    //   147: aload 11
    //   149: invokevirtual 129	com/wyy/twodimcode/camera/PlanarYUVLuminanceSource:renderCroppedGreyscaleBitmap	()Landroid/graphics/Bitmap;
    //   152: invokevirtual 133	android/os/Bundle:putParcelable	(Ljava/lang/String;Landroid/os/Parcelable;)V
    //   155: aload_1
    //   156: aload 10
    //   158: invokevirtual 137	android/os/Message:setData	(Landroid/os/Bundle;)V
    //   161: aload_1
    //   162: invokevirtual 140	android/os/Message:sendToTarget	()V
    //   165: return
    //   166: iconst_0
    //   167: istore 5
    //   169: iload 5
    //   171: iload_2
    //   172: if_icmplt +12 -> 184
    //   175: iload 4
    //   177: iconst_1
    //   178: iadd
    //   179: istore 4
    //   181: goto -172 -> 9
    //   184: aload 10
    //   186: iload 5
    //   188: iload_3
    //   189: imul
    //   190: iload_3
    //   191: iadd
    //   192: iload 4
    //   194: isub
    //   195: iconst_1
    //   196: isub
    //   197: aload_1
    //   198: iload 4
    //   200: iload_2
    //   201: imul
    //   202: iload 5
    //   204: iadd
    //   205: baload
    //   206: bastore
    //   207: iload 5
    //   209: iconst_1
    //   210: iadd
    //   211: istore 5
    //   213: goto -44 -> 169
    //   216: astore 10
    //   218: aload_0
    //   219: getfield 30	com/wyy/twodimcode/decoding/DecodeHandler:multiFormatReader	Lcom/google/zxing/MultiFormatReader;
    //   222: invokevirtual 75	com/google/zxing/MultiFormatReader:reset	()V
    //   225: goto -152 -> 73
    //   228: astore_1
    //   229: aload_0
    //   230: getfield 30	com/wyy/twodimcode/decoding/DecodeHandler:multiFormatReader	Lcom/google/zxing/MultiFormatReader;
    //   233: invokevirtual 75	com/google/zxing/MultiFormatReader:reset	()V
    //   236: aload_1
    //   237: athrow
    //   238: aload_0
    //   239: getfield 36	com/wyy/twodimcode/decoding/DecodeHandler:activity	Lcom/ismartgo/app/activity/CaptureActivity;
    //   242: invokevirtual 111	com/ismartgo/app/activity/CaptureActivity:getHandler	()Landroid/os/Handler;
    //   245: ldc 141
    //   247: invokestatic 144	android/os/Message:obtain	(Landroid/os/Handler;I)Landroid/os/Message;
    //   250: invokevirtual 140	android/os/Message:sendToTarget	()V
    //   253: return
    //
    // Exception table:
    //   from	to	target	type
    //   52	63	216	com/google/zxing/ReaderException
    //   52	63	228	finally
  }

  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
      return;
    case 2131230721:
      decode((byte[])paramMessage.obj, paramMessage.arg1, paramMessage.arg2);
      return;
    case 2131230727:
    }
    Looper.myLooper().quit();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.wyy.twodimcode.decoding.DecodeHandler
 * JD-Core Version:    0.6.2
 */