package com.wyy.twodimcode.camera;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import com.ismartgo.app.grid.utils.DisplayUtil;
import java.io.IOException;

public final class CameraManager
{
  private static final int MAX_FRAME_HEIGHT = 406;
  private static final int MAX_FRAME_WIDTH = 652;
  private static final int MIN_FRAME_HEIGHT = 406;
  private static final int MIN_FRAME_WIDTH = 652;
  static final int SDK_INT;
  private static final String TAG = CameraManager.class.getSimpleName();
  private static CameraManager cameraManager;
  private final AutoFocusCallback autoFocusCallback;
  private Camera camera;
  private final CameraConfigurationManager configManager;
  private final Context context;
  private Rect framingRect;
  private Rect framingRectInPreview;
  private boolean initialized;
  private final PreviewCallback previewCallback;
  private boolean previewing;
  private final boolean useOneShotPreviewCallback;

  static
  {
    try
    {
      i = Build.VERSION.SDK_INT;
      SDK_INT = i;
      return;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      while (true)
        int i = 10000;
    }
  }

  private CameraManager(Context paramContext)
  {
    this.context = paramContext;
    this.configManager = new CameraConfigurationManager(paramContext);
    if (Build.VERSION.SDK_INT > 3);
    for (boolean bool = true; ; bool = false)
    {
      this.useOneShotPreviewCallback = bool;
      this.previewCallback = new PreviewCallback(this.configManager, this.useOneShotPreviewCallback);
      this.autoFocusCallback = new AutoFocusCallback();
      return;
    }
  }

  public static CameraManager get()
  {
    return cameraManager;
  }

  public static void init(Context paramContext)
  {
    if (cameraManager == null)
      cameraManager = new CameraManager(paramContext);
  }

  public PlanarYUVLuminanceSource buildLuminanceSource(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    Rect localRect = getFramingRectInPreview();
    int i = this.configManager.getPreviewFormat();
    String str = this.configManager.getPreviewFormatString();
    switch (i)
    {
    default:
      if ("yuv420p".equals(str))
        return new PlanarYUVLuminanceSource(paramArrayOfByte, paramInt1, paramInt2, localRect.left, localRect.top, localRect.width(), localRect.height());
      break;
    case 16:
    case 17:
      return new PlanarYUVLuminanceSource(paramArrayOfByte, paramInt1, paramInt2, localRect.left, localRect.top, localRect.width(), localRect.height());
    }
    throw new IllegalArgumentException("Unsupported picture format: " + i + '/' + str);
  }

  public void closeDriver()
  {
    if (this.camera != null)
    {
      FlashlightManager.disableFlashlight();
      this.camera.release();
      this.camera = null;
    }
  }

  public void closeSplashLight()
  {
    if (this.camera == null)
      return;
    Camera.Parameters localParameters = this.camera.getParameters();
    localParameters.setFlashMode("off");
    this.camera.setParameters(localParameters);
  }

  public Camera getCamera()
  {
    return this.camera;
  }

  public Rect getFramingRect()
  {
    Point localPoint = this.configManager.getScreenResolution();
    if (this.framingRect == null)
    {
      if (this.camera == null)
        return null;
      int i = DisplayUtil.getScreenWidth(this.context);
      int j = i * 406 / 652;
      int k = (localPoint.y - j) / 2;
      this.framingRect = new Rect(50, k, i - 50, k + j);
      Log.d(TAG, "Calculated framing rect: " + this.framingRect);
    }
    return this.framingRect;
  }

  public Rect getFramingRectInPreview()
  {
    if (this.framingRectInPreview == null)
    {
      Rect localRect = new Rect(getFramingRect());
      Point localPoint1 = this.configManager.getCameraResolution();
      Point localPoint2 = this.configManager.getScreenResolution();
      localRect.left = (localRect.left * localPoint1.y / localPoint2.x);
      localRect.right = (localRect.right * localPoint1.y / localPoint2.x);
      localRect.top = (localRect.top * localPoint1.x / localPoint2.y);
      localRect.bottom = (localRect.bottom * localPoint1.x / localPoint2.y);
      this.framingRectInPreview = localRect;
    }
    return this.framingRectInPreview;
  }

  public void openDriver(SurfaceHolder paramSurfaceHolder)
    throws IOException
  {
    if (this.camera == null)
    {
      this.camera = Camera.open();
      if (this.camera == null)
        throw new IOException();
      this.camera.setPreviewDisplay(paramSurfaceHolder);
      if (!this.initialized)
      {
        this.initialized = true;
        this.configManager.initFromCameraParameters(this.camera);
      }
      this.configManager.setDesiredCameraParameters(this.camera);
      FlashlightManager.enableFlashlight();
    }
  }

  public void openSplashLight()
  {
    if (this.camera == null)
      return;
    Camera.Parameters localParameters = this.camera.getParameters();
    localParameters.setFlashMode("torch");
    this.camera.setParameters(localParameters);
  }

  public void requestAutoFocus(Handler paramHandler, int paramInt)
  {
    if ((this.camera != null) && (this.previewing))
    {
      this.autoFocusCallback.setHandler(paramHandler, paramInt);
      this.camera.autoFocus(this.autoFocusCallback);
    }
  }

  public void requestPreviewFrame(Handler paramHandler, int paramInt)
  {
    if ((this.camera != null) && (this.previewing))
    {
      this.previewCallback.setHandler(paramHandler, paramInt);
      if (this.useOneShotPreviewCallback)
        this.camera.setOneShotPreviewCallback(this.previewCallback);
    }
    else
    {
      return;
    }
    this.camera.setPreviewCallback(this.previewCallback);
  }

  public void startPreview()
  {
    if ((this.camera != null) && (!this.previewing))
    {
      this.camera.startPreview();
      this.previewing = true;
    }
  }

  public void stopPreview()
  {
    if ((this.camera != null) && (this.previewing))
    {
      if (!this.useOneShotPreviewCallback)
        this.camera.setPreviewCallback(null);
      this.camera.stopPreview();
      this.previewCallback.setHandler(null, 0);
      this.autoFocusCallback.setHandler(null, 0);
      this.previewing = false;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.wyy.twodimcode.camera.CameraManager
 * JD-Core Version:    0.6.2
 */