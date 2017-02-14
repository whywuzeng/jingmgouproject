package com.ismartgo.app.tools;

public class BufferUtils
{
  private static StringBuffer buffer;

  public static String getLocation()
  {
    if (buffer == null)
      return null;
    return buffer.toString();
  }

  public static void setLocationTime(String paramString)
  {
    if (buffer == null)
      buffer = new StringBuffer();
    buffer.append(paramString).append("\n\n");
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.tools.BufferUtils
 * JD-Core Version:    0.6.2
 */