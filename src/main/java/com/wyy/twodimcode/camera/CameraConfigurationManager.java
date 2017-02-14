package com.wyy.twodimcode.camera;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

final class CameraConfigurationManager
{
  private static final Pattern COMMA_PATTERN = Pattern.compile(",");
  private static final int DESIRED_SHARPNESS = 30;
  private static final String TAG = CameraConfigurationManager.class.getSimpleName();
  private static final int TEN_DESIRED_ZOOM = 27;
  private Point cameraResolution;
  private final Context context;
  private int previewFormat;
  private String previewFormatString;
  private Point screenResolution;

  CameraConfigurationManager(Context paramContext)
  {
    this.context = paramContext;
  }

  private static int findBestMotZoomValue(CharSequence paramCharSequence, int paramInt)
  {
    int j = 0;
    paramCharSequence = COMMA_PATTERN.split(paramCharSequence);
    int n = paramCharSequence.length;
    int i = 0;
    while (true)
    {
      if (i >= n)
        return j;
      String str = paramCharSequence[i].trim();
      try
      {
        double d = Double.parseDouble(str);
        int m = (int)(10.0D * d);
        int k = j;
        if (Math.abs(paramInt - d) < Math.abs(paramInt - j))
          k = m;
        i += 1;
        j = k;
      }
      catch (NumberFormatException paramCharSequence)
      {
      }
    }
    return paramInt;
  }

  private static Point findBestPreviewSizeValue(CharSequence paramCharSequence, Point paramPoint)
  {
    int i = 0;
    int j = 0;
    int m = 2147483647;
    paramCharSequence = COMMA_PATTERN.split(paramCharSequence);
    int i4 = paramCharSequence.length;
    int k = 0;
    if (k >= i4)
    {
      if ((i > 0) && (j > 0))
        return new Point(i, j);
    }
    else
    {
      String str = paramCharSequence[k].trim();
      int n = str.indexOf('x');
      int i1;
      if (n < 0)
      {
        Log.w(TAG, "Bad preview-size: " + str);
        i1 = m;
      }
      while (true)
      {
        int i2;
        int i3;
        while (true)
        {
          k += 1;
          m = i1;
          break;
          try
          {
            i2 = Integer.parseInt(str.substring(0, n));
            n = Integer.parseInt(str.substring(n + 1));
            i3 = Math.abs(i2 - paramPoint.x) + Math.abs(n - paramPoint.y);
            if (i3 != 0)
              break label209;
            i = i2;
            j = n;
          }
          catch (NumberFormatException localNumberFormatException)
          {
            Log.w(TAG, "Bad preview-size: " + str);
            i1 = m;
          }
        }
        continue;
        label209: i1 = m;
        if (i3 < m)
        {
          i = i2;
          j = n;
          i1 = i3;
        }
      }
    }
    return null;
  }

  private static Point getCameraResolution(Camera.Parameters paramParameters, Point paramPoint)
  {
    String str = paramParameters.get("preview-size-values");
    Object localObject = str;
    if (str == null)
      localObject = paramParameters.get("preview-size-value");
    paramParameters = null;
    if (localObject != null)
    {
      Log.d(TAG, "preview-size-values parameter: " + (String)localObject);
      paramParameters = findBestPreviewSizeValue((CharSequence)localObject, paramPoint);
    }
    localObject = paramParameters;
    if (paramParameters == null)
      localObject = new Point(paramPoint.x >> 3 << 3, paramPoint.y >> 3 << 3);
    return localObject;
  }

  private void setFlash(Camera.Parameters paramParameters)
  {
    if ((Build.MODEL.contains("Behold II")) && (CameraManager.SDK_INT == 3))
      paramParameters.set("flash-value", 1);
    while (true)
    {
      paramParameters.set("flash-mode", "off");
      return;
      paramParameters.set("flash-value", 2);
    }
  }

  private void setZoom(Camera.Parameters paramParameters)
  {
    String str1 = paramParameters.get("zoom-supported");
    if ((str1 != null) && (!Boolean.parseBoolean(str1)));
    while (true)
    {
      return;
      int j = 27;
      str1 = paramParameters.get("max-zoom");
      int i = j;
      if (str1 != null);
      try
      {
        double d = Double.parseDouble(str1);
        k = (int)(10.0D * d);
        i = j;
        if (27 > k)
          i = k;
        str2 = paramParameters.get("taking-picture-zoom-max");
        j = i;
        if (str2 == null);
      }
      catch (NumberFormatException localNumberFormatException2)
      {
        try
        {
          k = Integer.parseInt(str2);
          j = i;
          if (i > k)
            j = k;
          str3 = paramParameters.get("mot-zoom-values");
          i = j;
          if (str3 != null)
            i = findBestMotZoomValue(str3, j);
          str4 = paramParameters.get("mot-zoom-step");
          j = i;
          if (str4 == null);
        }
        catch (NumberFormatException localNumberFormatException2)
        {
          try
          {
            while (true)
            {
              String str2;
              String str3;
              String str4;
              int k = (int)(10.0D * Double.parseDouble(str4.trim()));
              j = i;
              if (k > 1)
                j = i - i % k;
              if ((str1 != null) || (str3 != null))
                paramParameters.set("zoom", String.valueOf(j / 10.0D));
              if (str2 == null)
                break;
              paramParameters.set("taking-picture-zoom", j);
              return;
              localNumberFormatException1 = localNumberFormatException1;
              Log.w(TAG, "Bad max-zoom: " + str1);
              i = j;
            }
            localNumberFormatException2 = localNumberFormatException2;
            Log.w(TAG, "Bad taking-picture-zoom-max: " + localNumberFormatException1);
            j = i;
          }
          catch (NumberFormatException localNumberFormatException3)
          {
            while (true)
              j = i;
          }
        }
      }
    }
  }

  Point getCameraResolution()
  {
    return this.cameraResolution;
  }

  int getPreviewFormat()
  {
    return this.previewFormat;
  }

  String getPreviewFormatString()
  {
    return this.previewFormatString;
  }

  Point getScreenResolution()
  {
    return this.screenResolution;
  }

  void initFromCameraParameters(Camera paramCamera)
  {
    paramCamera = paramCamera.getParameters();
    this.previewFormat = paramCamera.getPreviewFormat();
    this.previewFormatString = paramCamera.get("preview-format");
    Log.d(TAG, "Default preview format: " + this.previewFormat + '/' + this.previewFormatString);
    Object localObject = ((WindowManager)this.context.getSystemService("window")).getDefaultDisplay();
    this.screenResolution = new Point(((Display)localObject).getWidth(), ((Display)localObject).getHeight());
    Log.d(TAG, "Screen resolution: " + this.screenResolution);
    localObject = new Point();
    ((Point)localObject).x = this.screenResolution.x;
    ((Point)localObject).y = this.screenResolution.y;
    if (this.screenResolution.x < this.screenResolution.y)
    {
      ((Point)localObject).x = this.screenResolution.y;
      ((Point)localObject).y = this.screenResolution.x;
    }
    this.cameraResolution = getCameraResolution(paramCamera, (Point)localObject);
    Log.d(TAG, "Camera resolution: " + this.screenResolution);
  }

  void setDesiredCameraParameters(Camera paramCamera)
  {
    Camera.Parameters localParameters = paramCamera.getParameters();
    Log.d(TAG, "Setting preview size: " + this.cameraResolution);
    localParameters.setPreviewSize(this.cameraResolution.x, this.cameraResolution.y);
    setFlash(localParameters);
    setZoom(localParameters);
    if (Integer.parseInt(Build.VERSION.SDK) >= 8)
      setDisplayOrientation(paramCamera, 90);
    paramCamera.setParameters(localParameters);
  }

  protected void setDisplayOrientation(Camera paramCamera, int paramInt)
  {
    try
    {
      Method localMethod = paramCamera.getClass().getMethod("setDisplayOrientation", new Class[] { Integer.TYPE });
      if (localMethod != null)
        localMethod.invoke(paramCamera, new Object[] { Integer.valueOf(paramInt) });
      return;
    }
    catch (Exception paramCamera)
    {
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.wyy.twodimcode.camera.CameraConfigurationManager
 * JD-Core Version:    0.6.2
 */