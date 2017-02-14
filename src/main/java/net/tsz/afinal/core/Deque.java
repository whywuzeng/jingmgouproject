package net.tsz.afinal.core;

import java.util.Iterator;

public abstract interface Deque<E> extends Queue<E>
{
  public abstract boolean add(E paramE);

  public abstract void addFirst(E paramE);

  public abstract void addLast(E paramE);

  public abstract boolean contains(Object paramObject);

  public abstract Iterator<E> descendingIterator();

  public abstract E element();

  public abstract E getFirst();

  public abstract E getLast();

  public abstract Iterator<E> iterator();

  public abstract boolean offer(E paramE);

  public abstract boolean offerFirst(E paramE);

  public abstract boolean offerLast(E paramE);

  public abstract E peek();

  public abstract E peekFirst();

  public abstract E peekLast();

  public abstract E poll();

  public abstract E pollFirst();

  public abstract E pollLast();

  public abstract E pop();

  public abstract void push(E paramE);

  public abstract E remove();

  public abstract boolean remove(Object paramObject);

  public abstract E removeFirst();

  public abstract boolean removeFirstOccurrence(Object paramObject);

  public abstract E removeLast();

  public abstract boolean removeLastOccurrence(Object paramObject);

  public abstract int size();
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.core.Deque
 * JD-Core Version:    0.6.2
 */