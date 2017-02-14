package com.baidu.platform.comjni.tools;

public class JNITools
{
  public static native boolean CoordinateEncryptEx(float paramFloat1, float paramFloat2, String paramString, Object paramObject);

  public static native void GetDistanceByMC(Object paramObject);

  public static native String GetToken();

  public static native boolean TransGeoStr2ComplexPt(Object paramObject);

  public static native boolean TransGeoStr2Pt(Object paramObject);

  public static native void TransNodeStr2Pt(Object paramObject);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.platform.comjni.tools.JNITools
 * JD-Core Version:    0.6.2
 */