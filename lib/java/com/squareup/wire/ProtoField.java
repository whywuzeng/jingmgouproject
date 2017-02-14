package com.squareup.wire;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.FIELD})
public @interface ProtoField
{
  public abstract boolean deprecated();

  public abstract Class<? extends Enum> enumType();

  public abstract Message.Label label();

  public abstract Class<? extends Message> messageType();

  public abstract boolean redacted();

  public abstract int tag();

  public abstract Message.Datatype type();
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.wire.ProtoField
 * JD-Core Version:    0.6.2
 */