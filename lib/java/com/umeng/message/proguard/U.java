package com.umeng.message.proguard;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class U
{
  private static U a = new U();
  private List<T> b = new LinkedList();

  public static U a()
  {
    return a;
  }

  public void a(int paramInt, Object paramObject)
  {
    try
    {
      if (this.b.size() > 0)
      {
        Iterator localIterator = this.b.iterator();
        while (localIterator.hasNext())
        {
          T localT = (T)localIterator.next();
          try
          {
            localT.a(paramInt, paramObject);
          }
          catch (Throwable localThrowable)
          {
          }
        }
      }
      return;
    }
    finally
    {
    }
    throw paramObject;
  }

  public void a(T paramT)
  {
    if (paramT != null);
    try
    {
      if (!this.b.contains(paramT))
        this.b.add(paramT);
      return;
    }
    finally
    {
      paramT = finally;
    }
    throw paramT;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.U
 * JD-Core Version:    0.6.2
 */