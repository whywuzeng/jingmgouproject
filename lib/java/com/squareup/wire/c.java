package com.squareup.wire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class c<T extends ExtendableMessage<?>>
{
  private static final int a = 1;
  private Object[] b;
  private int c;

  public <E> c(Extension<T, E> paramExtension, E paramE)
  {
    this.b = new Object[2];
    this.b[0] = paramExtension;
    this.b[1] = paramE;
    this.c = 1;
  }

  public c(c<T> paramc)
  {
    this.b = ((Object[])paramc.b.clone());
    this.c = paramc.c;
  }

  private <E> void a(Extension<T, E> paramExtension, E paramE, int paramInt)
  {
    Object[] arrayOfObject = this.b;
    if (this.b.length < (this.c + 1) * 2)
    {
      arrayOfObject = new Object[this.b.length * 2];
      System.arraycopy(this.b, 0, arrayOfObject, 0, paramInt);
    }
    if (paramInt < this.c)
    {
      System.arraycopy(this.b, this.c + paramInt, arrayOfObject, this.c + paramInt + 2, this.c - paramInt);
      System.arraycopy(this.b, paramInt, arrayOfObject, paramInt + 1, this.c);
    }
    while (true)
    {
      this.c += 1;
      this.b = arrayOfObject;
      this.b[paramInt] = paramExtension;
      this.b[(this.c + paramInt)] = paramE;
      return;
      System.arraycopy(this.b, this.c, arrayOfObject, this.c + 1, this.c);
    }
  }

  public int a()
  {
    return this.c;
  }

  public Extension<T, ?> a(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.c))
      throw new IndexOutOfBoundsException("" + paramInt);
    return (Extension)this.b[paramInt];
  }

  public <E> E a(Extension<T, E> paramExtension)
  {
    int i = Arrays.binarySearch(this.b, 0, this.c, paramExtension);
    if (i < 0)
      return null;
    return this.b[(i + this.c)];
  }

  public <E> void a(Extension<T, E> paramExtension, E paramE)
  {
    int i = Arrays.binarySearch(this.b, 0, this.c, paramExtension);
    if (i >= 0)
    {
      this.b[(i + this.c)] = paramE;
      return;
    }
    a(paramExtension, paramE, -(i + 1));
  }

  public Object b(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.c))
      throw new IndexOutOfBoundsException("" + paramInt);
    return this.b[(this.c + paramInt)];
  }

  public List<Extension<T, ?>> b()
  {
    ArrayList localArrayList = new ArrayList(this.c);
    int i = 0;
    while (i < this.c)
    {
      localArrayList.add((Extension)this.b[i]);
      i += 1;
    }
    return Collections.unmodifiableList(localArrayList);
  }

  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof c));
    do
    {
      return false;
      paramObject = (c)paramObject;
    }
    while (this.c != paramObject.c);
    int i = 0;
    while (true)
    {
      if (i >= this.c * 2)
        break label62;
      if (!this.b[i].equals(paramObject.b[i]))
        break;
      i += 1;
    }
    label62: return true;
  }

  public int hashCode()
  {
    int i = 0;
    int j = 0;
    while (i < this.c * 2)
    {
      j = j * 37 + this.b[i].hashCode();
      i += 1;
    }
    return j;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("{");
    String str = "";
    int i = 0;
    while (i < this.c)
    {
      localStringBuilder.append(str);
      localStringBuilder.append(((Extension)this.b[i]).getTag());
      localStringBuilder.append("=");
      localStringBuilder.append(this.b[(this.c + i)]);
      i += 1;
      str = ", ";
    }
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.wire.c
 * JD-Core Version:    0.6.2
 */