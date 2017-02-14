package com.umeng.message.proguard;

import dalvik.system.DexClassLoader;
import java.net.URL;

public class bJ extends DexClassLoader
{
  public bJ(String paramString1, String paramString2, String paramString3, ClassLoader paramClassLoader)
  {
    super(paramString1, paramString2, paramString3, paramClassLoader);
  }

  protected Class<?> findClass(String paramString)
    throws ClassNotFoundException
  {
    return super.findClass(paramString);
  }

  public String findLibrary(String paramString)
  {
    return super.findLibrary(paramString);
  }

  protected URL findResource(String paramString)
  {
    return super.findResource(paramString);
  }

  protected Package getPackage(String paramString)
  {
    try
    {
      paramString = super.getPackage(paramString);
      return paramString;
    }
    finally
    {
      paramString = finally;
    }
    throw paramString;
  }

  public String toString()
  {
    return hashCode() + "|" + super.toString();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.bJ
 * JD-Core Version:    0.6.2
 */