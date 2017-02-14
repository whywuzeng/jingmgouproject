package com.squareup.wire;

import java.util.Arrays;
import java.util.Comparator;

final class b<E extends ProtoEnum>
{
  private static final Comparator<ProtoEnum> a = new Comparator()
  {
    public int a(ProtoEnum paramAnonymousProtoEnum1, ProtoEnum paramAnonymousProtoEnum2)
    {
      return paramAnonymousProtoEnum1.getValue() - paramAnonymousProtoEnum2.getValue();
    }
  };
  private final Class<E> b;
  private final int[] c;
  private final E[] d;
  private final boolean e;

  b(Class<E> paramClass)
  {
    this.b = paramClass;
    this.d = ((ProtoEnum[])paramClass.getEnumConstants());
    Arrays.sort(this.d, a);
    int j = this.d.length;
    if ((this.d[0].getValue() == 1) && (this.d[(j - 1)].getValue() == j))
    {
      this.e = true;
      this.c = null;
    }
    while (true)
    {
      return;
      this.e = false;
      this.c = new int[j];
      int i = 0;
      while (i < j)
      {
        this.c[i] = this.d[i].getValue();
        i += 1;
      }
    }
  }

  public int a(E paramE)
  {
    return paramE.getValue();
  }

  public E a(int paramInt)
  {
    int i;
    if (this.e)
      i = paramInt - 1;
    try
    {
      while (true)
      {
        ProtoEnum localProtoEnum = this.d[i];
        return localProtoEnum;
        i = Arrays.binarySearch(this.c, paramInt);
      }
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
    }
    throw new IllegalArgumentException("Unknown enum tag " + paramInt + " for " + this.b.getCanonicalName());
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.wire.b
 * JD-Core Version:    0.6.2
 */