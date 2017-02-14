package com.umeng.message.proguard;

import java.util.HashMap;

public class aY
{
  public static final String a = "hasAvailableUpdate";
  public static final String b = "newBaseList";
  public static final String c = "updateInfo";
  public static final String d = "info";
  public static final String e = "name";
  public static final String f = "type";
  public static final String g = "size";
  public static final String h = "url";
  public static final String i = "version";
  private String j;
  private HashMap<String, Object> k;

  public final String a()
  {
    return this.j;
  }

  public final void a(String paramString)
  {
    this.j = paramString;
  }

  public final void a(HashMap<String, Object> paramHashMap)
  {
    this.k = paramHashMap;
  }

  public final aZ b()
  {
    return (aZ)this.k.get("updateInfo");
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.aY
 * JD-Core Version:    0.6.2
 */