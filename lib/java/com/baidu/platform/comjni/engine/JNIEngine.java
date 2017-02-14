package com.baidu.platform.comjni.engine;

import android.content.Context;
import android.os.Bundle;

public class JNIEngine
{
  public static native boolean InitEngine(Context paramContext, Bundle paramBundle);

  public static native void SetProxyInfo(String paramString, int paramInt);

  public static native boolean StartSocketProc();

  public static native boolean UnInitEngine();

  public static native int initClass(Object paramObject, int paramInt);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.platform.comjni.engine.JNIEngine
 * JD-Core Version:    0.6.2
 */