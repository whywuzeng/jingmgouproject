package com.squareup.wire;

import com.umeng.message.proguard.aP;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class Wire
{
  final d a = new d();
  private final Map<Class<? extends Message>, MessageAdapter<? extends Message>> b = new LinkedHashMap();
  private final Map<Class<? extends Message.Builder>, a<? extends Message.Builder>> c = new LinkedHashMap();
  private final Map<Class<? extends ProtoEnum>, b<? extends ProtoEnum>> d = new LinkedHashMap();

  public Wire(List<Class<?>> paramList)
  {
    paramList = paramList.iterator();
    if (paramList.hasNext())
    {
      Field[] arrayOfField = ((Class)paramList.next()).getDeclaredFields();
      int j = arrayOfField.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = arrayOfField[i];
        if (((Field)localObject).getType().equals(Extension.class));
        try
        {
          localObject = (Extension)((Field)localObject).get(null);
          this.a.a((Extension)localObject);
          i += 1;
        }
        catch (IllegalAccessException paramList)
        {
          throw new AssertionError(paramList);
        }
      }
    }
  }

  public Wire(Class<?>[] paramArrayOfClass)
  {
    this(Arrays.asList(paramArrayOfClass));
  }

  private <M extends Message> M a(i parami, Class<M> paramClass)
    throws IOException
  {
    return a(paramClass).a(parami);
  }

  public static <T> T get(T paramT1, T paramT2)
  {
    if (paramT1 != null)
      return paramT1;
    return paramT2;
  }

  <M extends Message> MessageAdapter<M> a(Class<M> paramClass)
  {
    try
    {
      MessageAdapter localMessageAdapter2 = (MessageAdapter)this.b.get(paramClass);
      MessageAdapter localMessageAdapter1 = localMessageAdapter2;
      if (localMessageAdapter2 == null)
      {
        localMessageAdapter1 = new MessageAdapter(this, paramClass);
        this.b.put(paramClass, localMessageAdapter1);
      }
      return localMessageAdapter1;
    }
    finally
    {
    }
    throw paramClass;
  }

  <B extends Message.Builder> a<B> b(Class<B> paramClass)
  {
    try
    {
      a locala2 = (a)this.c.get(paramClass);
      a locala1 = locala2;
      if (locala2 == null)
      {
        locala1 = new a(paramClass);
        this.c.put(paramClass, locala1);
      }
      return locala1;
    }
    finally
    {
    }
    throw paramClass;
  }

  <E extends ProtoEnum> b<E> c(Class<E> paramClass)
  {
    try
    {
      b localb2 = (b)this.d.get(paramClass);
      b localb1 = localb2;
      if (localb2 == null)
      {
        localb1 = new b(paramClass);
        this.d.put(paramClass, localb1);
      }
      return localb1;
    }
    finally
    {
    }
    throw paramClass;
  }

  public <M extends Message> M parseFrom(aP paramaP, Class<M> paramClass)
    throws IOException
  {
    f.a(paramaP, "input");
    f.a(paramClass, "messageClass");
    return a(i.a(paramaP), paramClass);
  }

  public <M extends Message> M parseFrom(InputStream paramInputStream, Class<M> paramClass)
    throws IOException
  {
    f.a(paramInputStream, "input");
    f.a(paramClass, "messageClass");
    return a(i.a(paramInputStream), paramClass);
  }

  public <M extends Message> M parseFrom(byte[] paramArrayOfByte, int paramInt1, int paramInt2, Class<M> paramClass)
    throws IOException
  {
    boolean bool2 = true;
    f.a(paramArrayOfByte, "bytes");
    if (paramInt1 >= 0)
    {
      bool1 = true;
      f.a(bool1, "offset < 0");
      if (paramInt2 < 0)
        break label82;
      bool1 = true;
      label30: f.a(bool1, "count < 0");
      if (paramInt1 + paramInt2 > paramArrayOfByte.length)
        break label88;
    }
    label82: label88: for (boolean bool1 = bool2; ; bool1 = false)
    {
      f.a(bool1, "offset + count > bytes");
      f.a(paramClass, "messageClass");
      return a(i.a(paramArrayOfByte, paramInt1, paramInt2), paramClass);
      bool1 = false;
      break;
      bool1 = false;
      break label30;
    }
  }

  public <M extends Message> M parseFrom(byte[] paramArrayOfByte, Class<M> paramClass)
    throws IOException
  {
    f.a(paramArrayOfByte, "bytes");
    f.a(paramClass, "messageClass");
    return a(i.a(paramArrayOfByte), paramClass);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.wire.Wire
 * JD-Core Version:    0.6.2
 */