package com.baidu.platform.comjni.map.favorite;

import android.os.Bundle;

public class a
{
  private long a = 0L;
  private JNIFavorite b = null;

  public int a(Bundle paramBundle)
  {
    try
    {
      int i = this.b.GetAll(this.a, paramBundle);
      return i;
    }
    catch (Throwable paramBundle)
    {
    }
    return 0;
  }

  public long a()
  {
    this.a = this.b.Create();
    return this.a;
  }

  public boolean a(int paramInt)
  {
    return this.b.SetType(this.a, paramInt);
  }

  public boolean a(String paramString)
  {
    return this.b.Remove(this.a, paramString);
  }

  public boolean a(String paramString1, String paramString2)
  {
    a.a();
    return this.b.Add(this.a, paramString1, paramString2);
  }

  public boolean a(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, int paramInt3)
  {
    return this.b.Load(this.a, paramString1, paramString2, paramString3, paramInt1, paramInt2, paramInt3);
  }

  public int b()
  {
    return this.b.Release(this.a);
  }

  public String b(String paramString)
  {
    try
    {
      paramString = this.b.GetValue(this.a, paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
    }
    return null;
  }

  public boolean b(String paramString1, String paramString2)
  {
    a.a();
    return this.b.Update(this.a, paramString1, paramString2);
  }

  public boolean c()
  {
    return this.b.Clear(this.a);
  }

  public boolean c(String paramString)
  {
    try
    {
      boolean bool = this.b.IsExist(this.a, paramString);
      return bool;
    }
    catch (Throwable paramString)
    {
    }
    return false;
  }

  public boolean d()
  {
    return this.b.SaveCache(this.a);
  }

  public static class a
  {
    public static boolean a = false;

    private static void b()
    {
      a = true;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.platform.comjni.map.favorite.a
 * JD-Core Version:    0.6.2
 */