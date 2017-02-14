package com.alimama.mobile.csdk.umupdate.models;

public enum f
{
  public static f a(String paramString)
  {
    try
    {
      paramString = paramString.split("\\.")[0];
      f[] arrayOff = values();
      int j = arrayOff.length;
      int i = 0;
      while (i < j)
      {
        f localf = arrayOff[i];
        boolean bool = localf.toString().equals(paramString);
        if (bool)
          return localf;
        i += 1;
      }
    }
    catch (Exception paramString)
    {
    }
    return null;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alimama.mobile.csdk.umupdate.models.f
 * JD-Core Version:    0.6.2
 */