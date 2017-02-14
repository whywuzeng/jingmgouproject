package com.wyy.twodimcode.camera;

import android.os.IBinder;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

final class FlashlightManager
{
  private static final String TAG = FlashlightManager.class.getSimpleName();
  private static final Object iHardwareService = getHardwareService();
  private static final Method setFlashEnabledMethod = getSetFlashEnabledMethod(iHardwareService);

  static
  {
    if (iHardwareService == null)
    {
      Log.v(TAG, "This device does supports control of a flashlight");
      return;
    }
    Log.v(TAG, "This device does not support control of a flashlight");
  }

  static void disableFlashlight()
  {
    setFlashlight(false);
  }

  static void enableFlashlight()
  {
    setFlashlight(true);
  }

  private static Object getHardwareService()
  {
    Object localObject1 = maybeForName("android.os.ServiceManager");
    if (localObject1 == null);
    Object localObject2;
    do
    {
      do
      {
        do
        {
          do
          {
            return null;
            localObject1 = maybeGetMethod((Class)localObject1, "getService", new Class[] { String.class });
          }
          while (localObject1 == null);
          localObject1 = invoke((Method)localObject1, null, new Object[] { "hardware" });
        }
        while (localObject1 == null);
        localObject2 = maybeForName("android.os.IHardwareService$Stub");
      }
      while (localObject2 == null);
      localObject2 = maybeGetMethod((Class)localObject2, "asInterface", new Class[] { IBinder.class });
    }
    while (localObject2 == null);
    return invoke((Method)localObject2, null, new Object[] { localObject1 });
  }

  private static Method getSetFlashEnabledMethod(Object paramObject)
  {
    if (paramObject == null)
      return null;
    return maybeGetMethod(paramObject.getClass(), "setFlashlightEnabled", new Class[] { Boolean.TYPE });
  }

  private static Object invoke(Method paramMethod, Object paramObject, Object[] paramArrayOfObject)
  {
    try
    {
      paramObject = paramMethod.invoke(paramObject, paramArrayOfObject);
      return paramObject;
    }
    catch (IllegalAccessException paramObject)
    {
      Log.w(TAG, "Unexpected error while invoking " + paramMethod, paramObject);
      return null;
    }
    catch (InvocationTargetException paramObject)
    {
      Log.w(TAG, "Unexpected error while invoking " + paramMethod, paramObject.getCause());
      return null;
    }
    catch (RuntimeException paramObject)
    {
      Log.w(TAG, "Unexpected error while invoking " + paramMethod, paramObject);
    }
    return null;
  }

  private static Class<?> maybeForName(String paramString)
  {
    try
    {
      Class localClass = Class.forName(paramString);
      return localClass;
    }
    catch (ClassNotFoundException paramString)
    {
      return null;
    }
    catch (RuntimeException localRuntimeException)
    {
      Log.w(TAG, "Unexpected error while finding class " + paramString, localRuntimeException);
    }
    return null;
  }

  private static Method maybeGetMethod(Class<?> paramClass, String paramString, Class<?>[] paramArrayOfClass)
  {
    try
    {
      paramClass = paramClass.getMethod(paramString, paramArrayOfClass);
      return paramClass;
    }
    catch (NoSuchMethodException paramClass)
    {
      return null;
    }
    catch (RuntimeException paramClass)
    {
      Log.w(TAG, "Unexpected error while finding method " + paramString, paramClass);
    }
    return null;
  }

  private static void setFlashlight(boolean paramBoolean)
  {
    if (iHardwareService != null)
      invoke(setFlashEnabledMethod, iHardwareService, new Object[] { Boolean.valueOf(paramBoolean) });
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.wyy.twodimcode.camera.FlashlightManager
 * JD-Core Version:    0.6.2
 */