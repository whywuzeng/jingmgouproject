package net.tsz.afinal.core;

import java.util.Collection;

public abstract interface Queue<E> extends Collection<E>
{
  public abstract boolean add(E paramE);

  public abstract E element();

  public abstract boolean offer(E paramE);

  public abstract E peek();

  public abstract E poll();

  public abstract E remove();
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.core.Queue
 * JD-Core Version:    0.6.2
 */