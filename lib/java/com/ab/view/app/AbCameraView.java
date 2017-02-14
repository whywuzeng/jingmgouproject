package com.ab.view.app;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class AbCameraView extends SurfaceView
  implements SurfaceHolder.Callback
{
  private Camera camera;
  private SurfaceHolder surfaceHolder = getHolder();

  public AbCameraView(Context paramContext)
  {
    super(paramContext);
    this.surfaceHolder.addCallback(this);
    this.surfaceHolder.setType(3);
  }

  public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3)
  {
    if (this.camera != null)
      this.camera.startPreview();
  }

  public void surfaceCreated(SurfaceHolder paramSurfaceHolder)
  {
    try
    {
      this.camera = Camera.open();
      this.camera.setPreviewDisplay(paramSurfaceHolder);
      return;
    }
    catch (Exception paramSurfaceHolder)
    {
      paramSurfaceHolder.printStackTrace();
    }
  }

  public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder)
  {
    if (this.camera != null)
    {
      this.camera.setPreviewCallback(null);
      this.camera.stopPreview();
      this.camera.release();
      this.camera = null;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.app.AbCameraView
 * JD-Core Version:    0.6.2
 */