package com.squareup.wire;

import java.util.Collections;
import java.util.List;

public abstract class ExtendableMessage<T extends ExtendableMessage<?>> extends Message
{
  private static final long c = 0L;
  transient c<T> a;

  protected int a()
  {
    if (this.a == null)
      return 0;
    return this.a.hashCode();
  }

  protected void a(ExtendableBuilder<T> paramExtendableBuilder)
  {
    super.a(paramExtendableBuilder);
    if (paramExtendableBuilder.a != null)
      this.a = new c(paramExtendableBuilder.a);
  }

  protected boolean a(ExtendableMessage<T> paramExtendableMessage)
  {
    if (this.a == null)
      return paramExtendableMessage.a == null;
    return this.a.equals(paramExtendableMessage.a);
  }

  String b()
  {
    if (this.a == null)
      return "{}";
    return this.a.toString();
  }

  public <E> E getExtension(Extension<T, E> paramExtension)
  {
    if (this.a == null)
      return null;
    return this.a.a(paramExtension);
  }

  public List<Extension<T, ?>> getExtensions()
  {
    if (this.a == null)
      return Collections.emptyList();
    return this.a.b();
  }

  public static abstract class ExtendableBuilder<T extends ExtendableMessage<?>> extends Message.Builder<T>
  {
    c<T> a;

    protected ExtendableBuilder()
    {
    }

    protected ExtendableBuilder(ExtendableMessage<T> paramExtendableMessage)
    {
      super();
      if ((paramExtendableMessage != null) && (paramExtendableMessage.a != null))
        this.a = new c(paramExtendableMessage.a);
    }

    public <E> E getExtension(Extension<T, E> paramExtension)
    {
      if (this.a == null)
        return null;
      return this.a.a(paramExtension);
    }

    public <E> ExtendableBuilder<T> setExtension(Extension<T, E> paramExtension, E paramE)
    {
      if (this.a == null)
      {
        this.a = new c(paramExtension, paramE);
        return this;
      }
      this.a.a(paramExtension, paramE);
      return this;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.wire.ExtendableMessage
 * JD-Core Version:    0.6.2
 */