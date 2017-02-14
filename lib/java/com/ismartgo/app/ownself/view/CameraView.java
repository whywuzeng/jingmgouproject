package com.ismartgo.app.ownself.view;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import java.io.IOException;

public class CameraView extends SurfaceView
  implements SurfaceHolder.Callback
{
  private Camera.AutoFocusCallback autoFocus = new Camera.AutoFocusCallback()
  {
    public void onAutoFocus(boolean paramAnonymousBoolean, Camera paramAnonymousCamera)
    {
      if (paramAnonymousBoolean)
        paramAnonymousCamera.setOneShotPreviewCallback(null);
    }
  };
  private Camera mCamera;
  private SurfaceHolder mHolder;
  private ToastDefine toast;

  public CameraView(Context paramContext)
  {
    super(paramContext);
    initSurfaceView();
  }

  public CameraView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initSurfaceView();
  }

  public CameraView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initSurfaceView();
  }

  private void initSurfaceView()
  {
    this.mHolder = getHolder();
    this.mHolder.addCallback(this);
    this.mHolder.setType(3);
    this.toast = new ToastDefine(getContext());
  }

  public void autoFocus()
  {
    if (this.mCamera != null);
    try
    {
      this.mCamera.autoFocus(this.autoFocus);
      return;
    }
    catch (Exception localException)
    {
      this.mCamera.stopPreview();
      this.mCamera.release();
    }
  }

  public Camera getCamera()
  {
    return this.mCamera;
  }

  public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3)
  {
    paramSurfaceHolder = this.mCamera.getParameters();
    paramSurfaceHolder.setPictureFormat(256);
    paramSurfaceHolder.setPreviewSize(1280, 720);
    paramSurfaceHolder.setPictureSize(1280, 720);
    this.mCamera.setParameters(paramSurfaceHolder);
    this.mCamera.setDisplayOrientation(90);
    this.mCamera.startPreview();
    this.mCamera.autoFocus(this.autoFocus);
  }

  public void surfaceCreated(SurfaceHolder paramSurfaceHolder)
  {
    this.mCamera = Camera.open();
    try
    {
      this.mCamera.setPreviewDisplay(paramSurfaceHolder);
      return;
    }
    catch (IOException paramSurfaceHolder)
    {
      this.mCamera.release();
      this.mCamera = null;
    }
  }

  public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder)
  {
    this.mCamera.setPreviewCallback(null);
    this.mCamera.stopPreview();
    this.mCamera.release();
    this.mCamera = null;
  }

  public void takePicture(Camera.ShutterCallback paramShutterCallback, Camera.PictureCallback paramPictureCallback1, Camera.PictureCallback paramPictureCallback2)
  {
    if (this.mCamera == null)
    {
      this.toast.setMessage("重新打开相机");
      this.toast.show();
      return;
    }
    this.mCamera.takePicture(paramShutterCallback, paramPictureCallback1, paramPictureCallback2);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.ownself.view.CameraView
 * JD-Core Version:    0.6.2
 */