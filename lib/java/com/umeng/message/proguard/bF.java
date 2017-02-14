package com.umeng.message.proguard;

import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;

public final class bF
{
  public static final String a = "UTF-8";
  public static Charset b;

  static
  {
    try
    {
      b = Charset.forName("UTF-8");
      return;
    }
    catch (RuntimeException localRuntimeException)
    {
    }
  }

  public static final <T extends CharSequence> String a(Collection<T> paramCollection, char paramChar)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if ((paramCollection != null) && (paramCollection.size() > 0))
    {
      paramCollection = paramCollection.iterator();
      while (paramCollection.hasNext())
        localStringBuilder.append((CharSequence)paramCollection.next());
    }
    return localStringBuilder.toString();
  }

  public static final String a(Object[] paramArrayOfObject)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0))
    {
      int j = paramArrayOfObject.length;
      int i = 0;
      while (i < j)
      {
        localStringBuilder.append(paramArrayOfObject[i]);
        i += 1;
      }
    }
    return localStringBuilder.toString();
  }

  public static final boolean a(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.bF
 * JD-Core Version:    0.6.2
 */