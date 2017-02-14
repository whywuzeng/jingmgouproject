package com.alimama.mobile.csdk.umupdate.models;

public enum e
{
  public static e a(String paramString)
  {
    e[] arrayOfe = values();
    int j = arrayOfe.length;
    int i = 0;
    while (i < j)
    {
      e locale = arrayOfe[i];
      if (locale.toString().equals(paramString))
        return locale;
      i += 1;
    }
    return null;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alimama.mobile.csdk.umupdate.models.e
 * JD-Core Version:    0.6.2
 */