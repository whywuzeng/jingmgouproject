package net.tsz.afinal.core;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractCollection<E>
  implements Collection<E>
{
  public boolean add(E paramE)
  {
    throw new UnsupportedOperationException();
  }

  public boolean addAll(Collection<? extends E> paramCollection)
  {
    boolean bool = false;
    paramCollection = paramCollection.iterator();
    while (true)
    {
      if (!paramCollection.hasNext())
        return bool;
      if (add(paramCollection.next()))
        bool = true;
    }
  }

  public void clear()
  {
    Iterator localIterator = iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      localIterator.next();
      localIterator.remove();
    }
  }

  public boolean contains(Object paramObject)
  {
    boolean bool2 = true;
    Iterator localIterator = iterator();
    if (paramObject != null)
      if (localIterator.hasNext());
    while (true)
    {
      boolean bool1 = false;
      return bool1;
      if (!paramObject.equals(localIterator.next()))
        break;
      return true;
      do
      {
        bool1 = bool2;
        if (localIterator.next() == null)
          break;
      }
      while (localIterator.hasNext());
    }
  }

  public boolean containsAll(Collection<?> paramCollection)
  {
    paramCollection = paramCollection.iterator();
    do
      if (!paramCollection.hasNext())
        return true;
    while (contains(paramCollection.next()));
    return false;
  }

  public boolean isEmpty()
  {
    return size() == 0;
  }

  public abstract Iterator<E> iterator();

  public boolean remove(Object paramObject)
  {
    Iterator localIterator = iterator();
    if (paramObject != null)
      if (localIterator.hasNext());
    while (true)
    {
      return false;
      if (!paramObject.equals(localIterator.next()))
        break;
      localIterator.remove();
      return true;
      do
        if (localIterator.next() == null)
        {
          localIterator.remove();
          return true;
        }
      while (localIterator.hasNext());
    }
  }

  public boolean removeAll(Collection<?> paramCollection)
  {
    boolean bool = false;
    Iterator localIterator = iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return bool;
      if (paramCollection.contains(localIterator.next()))
      {
        localIterator.remove();
        bool = true;
      }
    }
  }

  public boolean retainAll(Collection<?> paramCollection)
  {
    boolean bool = false;
    Iterator localIterator = iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return bool;
      if (!paramCollection.contains(localIterator.next()))
      {
        localIterator.remove();
        bool = true;
      }
    }
  }

  public abstract int size();

  public Object[] toArray()
  {
    int j = size();
    Iterator localIterator = iterator();
    Object[] arrayOfObject = new Object[j];
    int i = 0;
    while (true)
    {
      if (i >= j)
        return arrayOfObject;
      arrayOfObject[i] = localIterator.next();
      i += 1;
    }
  }

  public <T> T[] toArray(T[] paramArrayOfT)
  {
    int j = size();
    int i = 0;
    Object localObject = paramArrayOfT;
    if (j > paramArrayOfT.length)
      localObject = (Object[])Array.newInstance(paramArrayOfT.getClass().getComponentType(), j);
    paramArrayOfT = iterator();
    while (true)
    {
      if (!paramArrayOfT.hasNext())
      {
        if (i < localObject.length)
          localObject[i] = null;
        return localObject;
      }
      localObject[i] = ((Object)paramArrayOfT.next());
      i += 1;
    }
  }

  public String toString()
  {
    if (isEmpty())
      return "[]";
    StringBuilder localStringBuilder = new StringBuilder(size() * 16);
    localStringBuilder.append('[');
    Iterator localIterator = iterator();
    label103: 
    while (true)
    {
      if (!localIterator.hasNext())
      {
        localStringBuilder.append(']');
        return localStringBuilder.toString();
      }
      Object localObject = localIterator.next();
      if (localObject != this)
        localStringBuilder.append(localObject);
      while (true)
      {
        if (!localIterator.hasNext())
          break label103;
        localStringBuilder.append(", ");
        break;
        localStringBuilder.append("(this Collection)");
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.core.AbstractCollection
 * JD-Core Version:    0.6.2
 */