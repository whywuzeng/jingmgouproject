package com.alimama.mobile.csdk.umupdate.models;

public enum d
{
  public static d a(String paramString)
  {
    d[] arrayOfd = values();
    int j = arrayOfd.length;
    int i = 0;
    while (i < j)
    {
      d locald = arrayOfd[i];
      if (locald.toString().equals(paramString))
        return locald;
      i += 1;
    }
    return null;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alimama.mobile.csdk.umupdate.models.d
 * JD-Core Version:    0.6.2
 */