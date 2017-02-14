package com.umeng.update.net;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import u.upd.b;

public class e
{
  private static final String a = "UMENG_RUNTIME_CACHE";
  private static final String b = e.class.getName();
  private final Context c;

  public e(Context paramContext)
  {
    this.c = paramContext;
  }

  public List<Integer> a()
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      SharedPreferences localSharedPreferences = this.c.getSharedPreferences("UMENG_RUNTIME_CACHE", 0);
      Iterator localIterator = localSharedPreferences.getAll().keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        try
        {
          int i = Integer.parseInt(str);
          localArrayList.add(Integer.valueOf(i));
          b.a(b, "get nid [" + i + "]");
        }
        catch (NumberFormatException localNumberFormatException)
        {
        }
      }
      localSharedPreferences.edit().clear().commit();
      return localArrayList;
    }
    catch (Exception localException)
    {
    }
    return localArrayList;
  }

  public void a(int paramInt)
  {
    try
    {
      SharedPreferences localSharedPreferences = this.c.getSharedPreferences("UMENG_RUNTIME_CACHE", 0);
      SharedPreferences.Editor localEditor = localSharedPreferences.edit();
      try
      {
        localEditor.putString("" + paramInt, "");
        localEditor.commit();
        b.a(b, "add nid [" + paramInt + "] to runtime cache.");
        return;
      }
      finally
      {
      }
    }
    catch (Exception localException)
    {
    }
  }

  public void b(int paramInt)
  {
    try
    {
      Object localObject = this.c.getSharedPreferences("UMENG_RUNTIME_CACHE", 0);
      if (((SharedPreferences)localObject).contains("" + paramInt))
      {
        localObject = ((SharedPreferences)localObject).edit();
        ((SharedPreferences.Editor)localObject).remove("" + paramInt);
        ((SharedPreferences.Editor)localObject).commit();
      }
      b.a(b, "remove nid [" + paramInt + "] to runtime cache.");
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public boolean b()
  {
    boolean bool = false;
    if (this.c.getSharedPreferences("UMENG_RUNTIME_CACHE", 0).getAll().size() > 0)
      bool = true;
    return bool;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.update.net.e
 * JD-Core Version:    0.6.2
 */