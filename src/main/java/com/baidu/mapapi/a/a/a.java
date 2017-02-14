package com.baidu.mapapi.a.a;

import com.baidu.mapapi.VersionInfo;
import com.baidu.mapapi.common.BaiduMapSDKException;
import java.io.PrintStream;

public class a
{
  static
  {
    try
    {
      if (!VersionInfo.getApiVersion().equals(b.a()))
        throw new BaiduMapSDKException("the version of util is not match with base");
    }
    catch (Error localError)
    {
      System.out.println(b.b() + " so Failed to load.");
      return;
    }
    System.loadLibrary(b.b());
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.a.a.a
 * JD-Core Version:    0.6.2
 */