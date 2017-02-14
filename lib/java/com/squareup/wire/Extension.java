package com.squareup.wire;

import com.umeng.message.proguard.aE;
import java.util.List;

public final class Extension<T extends ExtendableMessage<?>, E>
  implements Comparable<Extension<?, ?>>
{
  private final Class<T> a;
  private final Class<? extends Message> b;
  private final Class<? extends ProtoEnum> c;
  private final String d;
  private final int e;
  private final Message.Datatype f;
  private final Message.Label g;

  private Extension(Class<T> paramClass, Class<? extends Message> paramClass1, Class<? extends ProtoEnum> paramClass2, String paramString, int paramInt, Message.Label paramLabel, Message.Datatype paramDatatype)
  {
    this.a = paramClass;
    this.d = paramString;
    this.e = paramInt;
    this.f = paramDatatype;
    this.g = paramLabel;
    this.b = paramClass1;
    this.c = paramClass2;
  }

  public static <T extends ExtendableMessage<?>> Builder<T, Boolean> boolExtending(Class<T> paramClass)
  {
    return new Builder(paramClass, Message.Datatype.BOOL, null);
  }

  public static <T extends ExtendableMessage<?>> Builder<T, aE> bytesExtending(Class<T> paramClass)
  {
    return new Builder(paramClass, Message.Datatype.BYTES, null);
  }

  public static <T extends ExtendableMessage<?>> Builder<T, Double> doubleExtending(Class<T> paramClass)
  {
    return new Builder(paramClass, Message.Datatype.DOUBLE, null);
  }

  public static <T extends ExtendableMessage<?>, E extends Enum,  extends ProtoEnum> Builder<T, E> enumExtending(Class<E> paramClass, Class<T> paramClass1)
  {
    return new Builder(paramClass1, null, paramClass, Message.Datatype.ENUM, null);
  }

  public static <T extends ExtendableMessage<?>> Builder<T, Integer> fixed32Extending(Class<T> paramClass)
  {
    return new Builder(paramClass, Message.Datatype.FIXED32, null);
  }

  public static <T extends ExtendableMessage<?>> Builder<T, Long> fixed64Extending(Class<T> paramClass)
  {
    return new Builder(paramClass, Message.Datatype.FIXED64, null);
  }

  public static <T extends ExtendableMessage<?>> Builder<T, Float> floatExtending(Class<T> paramClass)
  {
    return new Builder(paramClass, Message.Datatype.FLOAT, null);
  }

  public static <T extends ExtendableMessage<?>> Builder<T, Integer> int32Extending(Class<T> paramClass)
  {
    return new Builder(paramClass, Message.Datatype.INT32, null);
  }

  public static <T extends ExtendableMessage<?>> Builder<T, Long> int64Extending(Class<T> paramClass)
  {
    return new Builder(paramClass, Message.Datatype.INT64, null);
  }

  public static <T extends ExtendableMessage<?>, M extends Message> Builder<T, M> messageExtending(Class<M> paramClass, Class<T> paramClass1)
  {
    return new Builder(paramClass1, paramClass, null, Message.Datatype.MESSAGE, null);
  }

  public static <T extends ExtendableMessage<?>> Builder<T, Integer> sfixed32Extending(Class<T> paramClass)
  {
    return new Builder(paramClass, Message.Datatype.SFIXED32, null);
  }

  public static <T extends ExtendableMessage<?>> Builder<T, Long> sfixed64Extending(Class<T> paramClass)
  {
    return new Builder(paramClass, Message.Datatype.SFIXED64, null);
  }

  public static <T extends ExtendableMessage<?>> Builder<T, Integer> sint32Extending(Class<T> paramClass)
  {
    return new Builder(paramClass, Message.Datatype.SINT32, null);
  }

  public static <T extends ExtendableMessage<?>> Builder<T, Long> sint64Extending(Class<T> paramClass)
  {
    return new Builder(paramClass, Message.Datatype.SINT64, null);
  }

  public static <T extends ExtendableMessage<?>> Builder<T, String> stringExtending(Class<T> paramClass)
  {
    return new Builder(paramClass, Message.Datatype.STRING, null);
  }

  public static <T extends ExtendableMessage<?>> Builder<T, Integer> uint32Extending(Class<T> paramClass)
  {
    return new Builder(paramClass, Message.Datatype.UINT32, null);
  }

  public static <T extends ExtendableMessage<?>> Builder<T, Long> uint64Extending(Class<T> paramClass)
  {
    return new Builder(paramClass, Message.Datatype.UINT64, null);
  }

  public int compareTo(Extension<?, ?> paramExtension)
  {
    if (paramExtension == this);
    do
    {
      return 0;
      if (this.e != paramExtension.e)
        return this.e - paramExtension.e;
      if (this.f != paramExtension.f)
        return this.f.value() - paramExtension.f.value();
      if (this.g != paramExtension.g)
        return this.g.value() - paramExtension.g.value();
      if ((this.a != null) && (!this.a.equals(paramExtension.a)))
        return this.a.getName().compareTo(paramExtension.a.getName());
      if ((this.b != null) && (!this.b.equals(paramExtension.b)))
        return this.b.getName().compareTo(paramExtension.b.getName());
    }
    while ((this.c == null) || (this.c.equals(paramExtension.c)));
    return this.c.getName().compareTo(paramExtension.c.getName());
  }

  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof Extension)) && (compareTo((Extension)paramObject) == 0);
  }

  public Message.Datatype getDatatype()
  {
    return this.f;
  }

  public Class<? extends ProtoEnum> getEnumType()
  {
    return this.c;
  }

  public Class<T> getExtendedType()
  {
    return this.a;
  }

  public Message.Label getLabel()
  {
    return this.g;
  }

  public Class<? extends Message> getMessageType()
  {
    return this.b;
  }

  public String getName()
  {
    return this.d;
  }

  public int getTag()
  {
    return this.e;
  }

  public int hashCode()
  {
    int j = 0;
    int k = this.e;
    int m = this.f.value();
    int n = this.g.value();
    int i1 = this.a.hashCode();
    if (this.b != null);
    for (int i = this.b.hashCode(); ; i = 0)
    {
      if (this.c != null)
        j = this.c.hashCode();
      return (i + (((k * 37 + m) * 37 + n) * 37 + i1) * 37) * 37 + j;
    }
  }

  public String toString()
  {
    return String.format("[%s %s %s = %d]", new Object[] { this.g, this.f, this.d, Integer.valueOf(this.e) });
  }

  public static final class Builder<T extends ExtendableMessage<?>, E>
  {
    private final Class<T> a;
    private final Class<? extends Message> b;
    private final Class<? extends ProtoEnum> c;
    private final Message.Datatype d;
    private String e = null;
    private int f = -1;
    private Message.Label g = null;

    private Builder(Class<T> paramClass, Message.Datatype paramDatatype)
    {
      this.a = paramClass;
      this.b = null;
      this.c = null;
      this.d = paramDatatype;
    }

    private Builder(Class<T> paramClass, Class<? extends Message> paramClass1, Class<? extends ProtoEnum> paramClass2, Message.Datatype paramDatatype)
    {
      this.a = paramClass;
      this.b = paramClass1;
      this.c = paramClass2;
      this.d = paramDatatype;
    }

    private void a()
    {
      if (this.a == null)
        throw new IllegalArgumentException("extendedType == null");
      if (this.e == null)
        throw new IllegalArgumentException("name == null");
      if (this.d == null)
        throw new IllegalArgumentException("datatype == null");
      if (this.g == null)
        throw new IllegalArgumentException("label == null");
      if (this.f <= 0)
        throw new IllegalArgumentException("tag == " + this.f);
      if (this.d == Message.Datatype.MESSAGE)
      {
        if ((this.b == null) || (this.c != null))
          throw new IllegalStateException("Message w/o messageType or w/ enumType");
      }
      else if (this.d == Message.Datatype.ENUM)
      {
        if ((this.b != null) || (this.c == null))
          throw new IllegalStateException("Enum w/ messageType or w/o enumType");
      }
      else if ((this.b != null) || (this.c != null))
        throw new IllegalStateException("Scalar w/ messageType or enumType");
    }

    public Extension<T, E> buildOptional()
    {
      this.g = Message.Label.OPTIONAL;
      a();
      return new Extension(this.a, this.b, this.c, this.e, this.f, this.g, this.d, null);
    }

    public Extension<T, List<E>> buildPacked()
    {
      this.g = Message.Label.PACKED;
      a();
      return new Extension(this.a, this.b, this.c, this.e, this.f, this.g, this.d, null);
    }

    public Extension<T, List<E>> buildRepeated()
    {
      this.g = Message.Label.REPEATED;
      a();
      return new Extension(this.a, this.b, this.c, this.e, this.f, this.g, this.d, null);
    }

    public Extension<T, E> buildRequired()
    {
      this.g = Message.Label.REQUIRED;
      a();
      return new Extension(this.a, this.b, this.c, this.e, this.f, this.g, this.d, null);
    }

    public Builder<T, E> setName(String paramString)
    {
      this.e = paramString;
      return this;
    }

    public Builder<T, E> setTag(int paramInt)
    {
      this.f = paramInt;
      return this;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.wire.Extension
 * JD-Core Version:    0.6.2
 */