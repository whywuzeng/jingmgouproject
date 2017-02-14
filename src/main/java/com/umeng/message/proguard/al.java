package com.umeng.message.proguard;

import java.util.Arrays;
import java.util.Comparator;

public class al
{
  private static al a = null;
  private b b = new b(null);
  private a c = new a(null);

  public static al a()
  {
    try
    {
      if (a == null)
        a = new al();
      al localal = a;
      return localal;
    }
    finally
    {
    }
  }

  public String[] a(String[] paramArrayOfString, boolean paramBoolean)
  {
    if (paramBoolean);
    for (Object localObject = this.c; (localObject != null) && (paramArrayOfString != null) && (paramArrayOfString.length > 0); localObject = this.b)
    {
      Arrays.sort(paramArrayOfString, (Comparator)localObject);
      return paramArrayOfString;
    }
    return null;
  }

  private class a
    implements Comparator<String>
  {
    private a()
    {
    }

    public int a(String paramString1, String paramString2)
    {
      if ((!at.a(paramString1)) && (!at.a(paramString2)))
        return paramString1.compareTo(paramString2);
      return 0;
    }
  }

  private class b
    implements Comparator<String>
  {
    private b()
    {
    }

    public int a(String paramString1, String paramString2)
    {
      if ((!at.a(paramString1)) && (!at.a(paramString2)))
        return paramString1.compareTo(paramString2) * -1;
      return 0;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.al
 * JD-Core Version:    0.6.2
 */