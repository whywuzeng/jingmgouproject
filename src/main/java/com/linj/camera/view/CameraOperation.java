package com.linj.camera.view;

import android.graphics.Bitmap;
import android.hardware.Camera.PictureCallback;

public abstract interface CameraOperation
{
  public abstract CameraView.FlashMode getFlashMode();

  public abstract int getMaxZoom();

  public abstract int getZoom();

  public abstract void setFlashMode(CameraView.FlashMode paramFlashMode);

  public abstract void setZoom(int paramInt);

  public abstract boolean startRecord();

  public abstract Bitmap stopRecord();

  public abstract void switchCamera();

  public abstract void takePicture(Camera.PictureCallback paramPictureCallback, CameraContainer.TakePictureListener paramTakePictureListener);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.linj.camera.view.CameraOperation
 * JD-Core Version:    0.6.2
 */