package com.squareup.wire;

import com.umeng.message.proguard.aE;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;

final class MessageAdapter<M extends Message>
{
  private static final String a = "█";
  private static final String b = "██";
  private final Wire c;
  private final Class<M> d;
  private final Class<Message.Builder<M>> e;
  private final Map<String, Integer> f = new LinkedHashMap();
  private final g<FieldInfo> g;

  MessageAdapter(Wire paramWire, Class<M> paramClass)
  {
    this.c = paramWire;
    this.d = paramClass;
    this.e = a(paramClass);
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    paramClass = paramClass.getDeclaredFields();
    int j = paramClass.length;
    int i = 0;
    if (i < j)
    {
      Field localField = paramClass[i];
      ProtoField localProtoField = (ProtoField)localField.getAnnotation(ProtoField.class);
      int k;
      String str;
      Message.Datatype localDatatype;
      if (localProtoField != null)
      {
        k = localProtoField.tag();
        str = localField.getName();
        this.f.put(str, Integer.valueOf(k));
        paramWire = null;
        localDatatype = localProtoField.type();
        if (localDatatype != Message.Datatype.ENUM)
          break label198;
        paramWire = b(localField);
      }
      while (true)
      {
        localLinkedHashMap.put(Integer.valueOf(k), new FieldInfo(k, str, localDatatype, localProtoField.label(), localProtoField.redacted(), paramWire, localField, c(str), null));
        i += 1;
        break;
        label198: if (localDatatype == Message.Datatype.MESSAGE)
          paramWire = a(localField);
      }
    }
    this.g = g.a(localLinkedHashMap);
  }

  private int a(int paramInt, Object paramObject, Message.Datatype paramDatatype)
  {
    return WireOutput.a(paramInt) + a(paramObject, paramDatatype);
  }

  private <E extends ProtoEnum> int a(E paramE)
  {
    return WireOutput.b(this.c.c(paramE.getClass()).a(paramE));
  }

  private <T extends ExtendableMessage<?>> int a(c<T> paramc)
  {
    int j = 0;
    int i = 0;
    if (j < paramc.a())
    {
      Object localObject2 = paramc.a(j);
      Object localObject1 = paramc.b(j);
      int k = ((Extension)localObject2).getTag();
      Message.Datatype localDatatype = ((Extension)localObject2).getDatatype();
      localObject2 = ((Extension)localObject2).getLabel();
      if (((Message.Label)localObject2).isRepeated())
        if (((Message.Label)localObject2).isPacked())
          i += b((List)localObject1, k, localDatatype);
      while (true)
      {
        j += 1;
        break;
        i += a((List)localObject1, k, localDatatype);
        continue;
        i += a(k, localObject1, localDatatype);
      }
    }
    return i;
  }

  private int a(Object paramObject, Message.Datatype paramDatatype)
  {
    int i;
    switch (1.a[paramDatatype.ordinal()])
    {
    default:
      throw new RuntimeException();
    case 1:
      return WireOutput.int32Size(((Integer)paramObject).intValue());
    case 2:
    case 3:
      return WireOutput.a(((Long)paramObject).longValue());
    case 4:
      return WireOutput.b(((Integer)paramObject).intValue());
    case 5:
      return WireOutput.b(WireOutput.g(((Integer)paramObject).intValue()));
    case 6:
      return WireOutput.a(WireOutput.d(((Long)paramObject).longValue()));
    case 7:
      return 1;
    case 8:
      return a((ProtoEnum)paramObject);
    case 9:
      i = d((String)paramObject);
      return i + WireOutput.b(i);
    case 10:
      i = ((aE)paramObject).f();
      return i + WireOutput.b(i);
    case 11:
      return d((Message)paramObject);
    case 12:
    case 13:
    case 14:
      return 4;
    case 15:
    case 16:
    case 17:
    }
    return 8;
  }

  private int a(List<?> paramList, int paramInt, Message.Datatype paramDatatype)
  {
    int i = 0;
    paramList = paramList.iterator();
    while (paramList.hasNext())
      i += a(paramInt, paramList.next(), paramDatatype);
    return i;
  }

  private Message a(i parami, int paramInt)
    throws IOException
  {
    int i = parami.d();
    if (parami.b >= 64)
      throw new IOException("Wire recursion limit exceeded");
    i = parami.d(i);
    parami.b += 1;
    Message localMessage = a(paramInt).a(parami);
    parami.a(0);
    parami.b -= 1;
    parami.e(i);
    return localMessage;
  }

  private MessageAdapter<? extends Message> a(int paramInt)
  {
    FieldInfo localFieldInfo = (FieldInfo)this.g.a(paramInt);
    if ((localFieldInfo != null) && (localFieldInfo.h != null))
      return localFieldInfo.h;
    MessageAdapter localMessageAdapter = this.c.a(c(paramInt));
    if (localFieldInfo != null)
      localFieldInfo.h = localMessageAdapter;
    return localMessageAdapter;
  }

  private Class<Message.Builder<M>> a(Class<M> paramClass)
  {
    try
    {
      Class localClass = Class.forName(paramClass.getName() + "$Builder");
      return localClass;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
    }
    throw new IllegalArgumentException("No builder class found for message type " + paramClass.getName());
  }

  private Class<? extends Message> a(Field paramField)
  {
    Class localClass = paramField.getType();
    if (Message.class.isAssignableFrom(localClass))
      return localClass;
    if (List.class.isAssignableFrom(localClass))
      return ((ProtoField)paramField.getAnnotation(ProtoField.class)).messageType();
    return null;
  }

  private Object a(i parami, int paramInt, Message.Datatype paramDatatype)
    throws IOException
  {
    switch (1.a[paramDatatype.ordinal()])
    {
    default:
      throw new RuntimeException();
    case 1:
    case 4:
      return Integer.valueOf(parami.d());
    case 2:
    case 3:
      return Long.valueOf(parami.e());
    case 5:
      return Integer.valueOf(i.c(parami.d()));
    case 6:
      return Long.valueOf(i.a(parami.e()));
    case 7:
      if (parami.d() != 0);
      for (boolean bool = true; ; bool = false)
        return Boolean.valueOf(bool);
    case 8:
      paramDatatype = b(paramInt);
      paramInt = parami.d();
      try
      {
        parami = paramDatatype.a(paramInt);
        return parami;
      }
      catch (IllegalArgumentException parami)
      {
        return Integer.valueOf(paramInt);
      }
    case 9:
      return parami.b();
    case 10:
      return parami.c();
    case 11:
      return a(parami, paramInt);
    case 12:
    case 13:
      return Integer.valueOf(parami.f());
    case 15:
    case 16:
      return Long.valueOf(parami.g());
    case 14:
      return Float.valueOf(Float.intBitsToFloat(parami.f()));
    case 17:
    }
    return Double.valueOf(Double.longBitsToDouble(parami.g()));
  }

  private void a(ExtendableMessage.ExtendableBuilder paramExtendableBuilder, Extension<?, ?> paramExtension, Object paramObject)
  {
    paramExtendableBuilder.setExtension(paramExtension, paramObject);
  }

  private void a(Message.Builder paramBuilder, i parami, int paramInt, WireType paramWireType)
    throws IOException
  {
    switch (1.b[paramWireType.ordinal()])
    {
    default:
      throw new RuntimeException("Unsupported wire type: " + paramWireType);
    case 1:
      paramBuilder.a().a(paramInt, Long.valueOf(parami.e()));
    case 6:
      return;
    case 2:
      paramBuilder.a().a(paramInt, Integer.valueOf(parami.f()));
      return;
    case 3:
      paramBuilder.a().b(paramInt, Long.valueOf(parami.g()));
      return;
    case 4:
      int i = parami.d();
      paramBuilder.a().a(paramInt, parami.b(i));
      return;
    case 5:
    }
    parami.i();
  }

  private <E extends ProtoEnum> void a(E paramE, WireOutput paramWireOutput)
    throws IOException
  {
    paramWireOutput.e(this.c.c(paramE.getClass()).a(paramE));
  }

  private void a(WireOutput paramWireOutput, int paramInt, Object paramObject, Message.Datatype paramDatatype)
    throws IOException
  {
    paramWireOutput.a(paramInt, paramDatatype.wireType());
    a(paramWireOutput, paramObject, paramDatatype);
  }

  private <T extends ExtendableMessage<?>> void a(WireOutput paramWireOutput, c<T> paramc)
    throws IOException
  {
    int i = 0;
    if (i < paramc.a())
    {
      Object localObject2 = paramc.a(i);
      Object localObject1 = paramc.b(i);
      int j = ((Extension)localObject2).getTag();
      Message.Datatype localDatatype = ((Extension)localObject2).getDatatype();
      localObject2 = ((Extension)localObject2).getLabel();
      if (((Message.Label)localObject2).isRepeated())
        if (((Message.Label)localObject2).isPacked())
          b(paramWireOutput, (List)localObject1, j, localDatatype);
      while (true)
      {
        i += 1;
        break;
        a(paramWireOutput, (List)localObject1, j, localDatatype);
        continue;
        a(paramWireOutput, j, localObject1, localDatatype);
      }
    }
  }

  private void a(WireOutput paramWireOutput, Object paramObject, Message.Datatype paramDatatype)
    throws IOException
  {
    switch (1.a[paramDatatype.ordinal()])
    {
    default:
      throw new RuntimeException();
    case 1:
      paramWireOutput.d(((Integer)paramObject).intValue());
      return;
    case 2:
    case 3:
      paramWireOutput.b(((Long)paramObject).longValue());
      return;
    case 4:
      paramWireOutput.e(((Integer)paramObject).intValue());
      return;
    case 5:
      paramWireOutput.e(WireOutput.g(((Integer)paramObject).intValue()));
      return;
    case 6:
      paramWireOutput.b(WireOutput.d(((Long)paramObject).longValue()));
      return;
    case 7:
      if (((Boolean)paramObject).booleanValue());
      for (int i = 1; ; i = 0)
      {
        paramWireOutput.c(i);
        return;
      }
    case 8:
      a((ProtoEnum)paramObject, paramWireOutput);
      return;
    case 9:
      paramObject = ((String)paramObject).getBytes("UTF-8");
      paramWireOutput.e(paramObject.length);
      paramWireOutput.b(paramObject);
      return;
    case 10:
      paramObject = (aE)paramObject;
      paramWireOutput.e(paramObject.f());
      paramWireOutput.b(paramObject.g());
      return;
    case 11:
      b((Message)paramObject, paramWireOutput);
      return;
    case 12:
    case 13:
      paramWireOutput.f(((Integer)paramObject).intValue());
      return;
    case 15:
    case 16:
      paramWireOutput.c(((Long)paramObject).longValue());
      return;
    case 14:
      paramWireOutput.f(Float.floatToIntBits(((Float)paramObject).floatValue()));
      return;
    case 17:
    }
    paramWireOutput.c(Double.doubleToLongBits(((Double)paramObject).doubleValue()));
  }

  private void a(WireOutput paramWireOutput, List<?> paramList, int paramInt, Message.Datatype paramDatatype)
    throws IOException
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
      a(paramWireOutput, paramInt, paramList.next(), paramDatatype);
  }

  private int b(List<?> paramList, int paramInt, Message.Datatype paramDatatype)
  {
    int i = 0;
    paramList = paramList.iterator();
    while (paramList.hasNext())
      i += a(paramList.next(), paramDatatype);
    return i + (WireOutput.b(WireOutput.makeTag(paramInt, WireType.LENGTH_DELIMITED)) + WireOutput.b(i));
  }

  private b<? extends ProtoEnum> b(int paramInt)
  {
    FieldInfo localFieldInfo = (FieldInfo)this.g.a(paramInt);
    if ((localFieldInfo != null) && (localFieldInfo.i != null))
      return localFieldInfo.i;
    b localb = this.c.c(e(paramInt));
    if (localFieldInfo != null)
      localFieldInfo.i = localb;
    return localb;
  }

  private Class<? extends Enum> b(Field paramField)
  {
    Class localClass = paramField.getType();
    if (Enum.class.isAssignableFrom(localClass))
      return localClass;
    if (List.class.isAssignableFrom(localClass))
      return ((ProtoField)paramField.getAnnotation(ProtoField.class)).enumType();
    return null;
  }

  private <M extends Message> void b(M paramM, WireOutput paramWireOutput)
    throws IOException
  {
    paramWireOutput.e(paramM.getSerializedSize());
    this.c.a(paramM.getClass()).a(paramM, paramWireOutput);
  }

  private void b(WireOutput paramWireOutput, List<?> paramList, int paramInt, Message.Datatype paramDatatype)
    throws IOException
  {
    int i = 0;
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
      i += a(localIterator.next(), paramDatatype);
    paramWireOutput.a(paramInt, WireType.LENGTH_DELIMITED);
    paramWireOutput.e(i);
    paramList = paramList.iterator();
    while (paramList.hasNext())
      a(paramWireOutput, paramList.next(), paramDatatype);
  }

  private Class<Message> c(int paramInt)
  {
    Object localObject1 = (FieldInfo)this.g.a(paramInt);
    if (localObject1 == null);
    for (localObject1 = null; ; localObject1 = ((FieldInfo)localObject1).f)
    {
      Object localObject2 = localObject1;
      if (localObject1 == null)
      {
        Extension localExtension = d(paramInt);
        localObject2 = localObject1;
        if (localExtension != null)
          localObject2 = localExtension.getMessageType();
      }
      return localObject2;
    }
  }

  private Field c(String paramString)
  {
    try
    {
      Field localField = this.e.getField(paramString);
      return localField;
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
    }
    throw new AssertionError("No builder field " + this.e.getName() + "." + paramString);
  }

  private <M extends Message> int d(M paramM)
  {
    int i = paramM.getSerializedSize();
    return i + WireOutput.b(i);
  }

  private int d(String paramString)
  {
    int k = 0;
    int m = paramString.length();
    int j = 0;
    if (k < m)
    {
      int i = paramString.charAt(k);
      if (i <= 127)
        j += 1;
      while (true)
      {
        k += 1;
        break;
        if (i <= 2047)
        {
          j += 2;
        }
        else if (Character.isHighSurrogate(i))
        {
          j += 4;
          k += 1;
        }
        else
        {
          j += 3;
        }
      }
    }
    return j;
  }

  private Extension<ExtendableMessage<?>, ?> d(int paramInt)
  {
    d locald = this.c.a;
    if (locald == null)
      return null;
    return locald.a(this.d, paramInt);
  }

  private Class<? extends ProtoEnum> e(int paramInt)
  {
    Object localObject1 = (FieldInfo)this.g.a(paramInt);
    if (localObject1 == null);
    for (localObject1 = null; ; localObject1 = ((FieldInfo)localObject1).e)
    {
      Object localObject2 = localObject1;
      if (localObject1 == null)
      {
        Extension localExtension = d(paramInt);
        localObject2 = localObject1;
        if (localExtension != null)
          localObject2 = localExtension.getEnumType();
      }
      return localObject2;
    }
  }

  int a(M paramM)
  {
    Object localObject1 = b().iterator();
    int i = 0;
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject3 = (FieldInfo)((Iterator)localObject1).next();
      Object localObject2 = a(paramM, (FieldInfo)localObject3);
      if (localObject2 != null)
      {
        j = ((FieldInfo)localObject3).a;
        Message.Datatype localDatatype = ((FieldInfo)localObject3).c;
        localObject3 = ((FieldInfo)localObject3).d;
        if (((Message.Label)localObject3).isRepeated())
          if (((Message.Label)localObject3).isPacked())
            i = b((List)localObject2, j, localDatatype) + i;
        while (true)
        {
          break;
          i = a((List)localObject2, j, localDatatype) + i;
          continue;
          i = a(j, localObject2, localDatatype) + i;
        }
      }
    }
    int j = i;
    if ((paramM instanceof ExtendableMessage))
    {
      localObject1 = (ExtendableMessage)paramM;
      j = i;
      if (((ExtendableMessage)localObject1).a != null)
        j = i + a(((ExtendableMessage)localObject1).a);
    }
    return paramM.getUnknownFieldsSerializedSize() + j;
  }

  Message.Builder<M> a()
  {
    try
    {
      Message.Builder localBuilder = (Message.Builder)this.e.newInstance();
      return localBuilder;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new AssertionError(localIllegalAccessException);
    }
    catch (InstantiationException localInstantiationException)
    {
      throw new AssertionError(localInstantiationException);
    }
  }

  M a(i parami)
    throws IOException
  {
    while (true)
    {
      Message.Builder localBuilder;
      b localb;
      int j;
      int i;
      try
      {
        localBuilder = (Message.Builder)this.e.newInstance();
        localb = new b(null);
        j = parami.a();
        i = j >> 3;
        localObject2 = WireType.valueOf(j);
        if (i != 0)
          break label150;
        parami = localb.a().iterator();
        if (parami.hasNext())
        {
          i = ((Integer)parami.next()).intValue();
          if (this.g.b(i))
          {
            a(localBuilder, i, localb.a(i));
            continue;
          }
        }
      }
      catch (IllegalAccessException parami)
      {
        throw new RuntimeException(parami);
        a((ExtendableMessage.ExtendableBuilder)localBuilder, d(i), localb.a(i));
        continue;
      }
      catch (InstantiationException parami)
      {
        throw new RuntimeException(parami);
      }
      return localBuilder.build();
      label150: Object localObject1 = (FieldInfo)this.g.a(i);
      Message.Datatype localDatatype;
      Message.Label localLabel;
      label185: long l;
      int k;
      if (localObject1 != null)
      {
        localDatatype = ((FieldInfo)localObject1).c;
        localLabel = ((FieldInfo)localObject1).d;
        localObject1 = null;
        if ((localLabel.isPacked()) && (localObject2 == WireType.LENGTH_DELIMITED))
        {
          j = parami.d();
          l = parami.h();
          k = parami.d(j);
        }
      }
      else
      {
        while (true)
        {
          if (parami.h() >= j + l)
            break label329;
          localObject1 = a(parami, i, localDatatype);
          if ((localDatatype == Message.Datatype.ENUM) && ((localObject1 instanceof Integer)))
          {
            localBuilder.addVarint(i, ((Integer)localObject1).intValue());
            continue;
            localObject1 = d(i);
            if (localObject1 == null)
            {
              a(localBuilder, parami, i, (WireType)localObject2);
              break;
            }
            localDatatype = ((Extension)localObject1).getDatatype();
            localLabel = ((Extension)localObject1).getLabel();
            break label185;
          }
          localb.a(i, localObject1);
        }
        label329: parami.e(k);
        if (parami.h() == j + l)
          continue;
        throw new IOException("Packed data had wrong length!");
      }
      Object localObject2 = a(parami, i, localDatatype);
      if ((localDatatype == Message.Datatype.ENUM) && ((localObject2 instanceof Integer)))
        localBuilder.addVarint(i, ((Integer)localObject2).intValue());
      else if (localLabel.isRepeated())
        localb.a(i, localObject2);
      else if (localObject1 != null)
        a((ExtendableMessage.ExtendableBuilder)localBuilder, (Extension)localObject1, localObject2);
      else
        a(localBuilder, i, localObject2);
    }
  }

  FieldInfo a(String paramString)
  {
    paramString = (Integer)this.f.get(paramString);
    if (paramString == null)
      return null;
    return (FieldInfo)this.g.a(paramString.intValue());
  }

  Object a(M paramM, FieldInfo paramFieldInfo)
  {
    if (FieldInfo.a(paramFieldInfo) == null)
      throw new AssertionError("Field is not of type \"Message\"");
    try
    {
      paramM = FieldInfo.a(paramFieldInfo).get(paramM);
      return paramM;
    }
    catch (IllegalAccessException paramM)
    {
    }
    throw new AssertionError(paramM);
  }

  public void a(Message.Builder<M> paramBuilder, int paramInt, Object paramObject)
  {
    try
    {
      FieldInfo.b((FieldInfo)this.g.a(paramInt)).set(paramBuilder, paramObject);
      return;
    }
    catch (IllegalAccessException paramBuilder)
    {
    }
    throw new AssertionError(paramBuilder);
  }

  void a(M paramM, WireOutput paramWireOutput)
    throws IOException
  {
    Object localObject1 = b().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject3 = (FieldInfo)((Iterator)localObject1).next();
      Object localObject2 = a(paramM, (FieldInfo)localObject3);
      if (localObject2 != null)
      {
        int i = ((FieldInfo)localObject3).a;
        Message.Datatype localDatatype = ((FieldInfo)localObject3).c;
        localObject3 = ((FieldInfo)localObject3).d;
        if (((Message.Label)localObject3).isRepeated())
        {
          if (((Message.Label)localObject3).isPacked())
            b(paramWireOutput, (List)localObject2, i, localDatatype);
          else
            a(paramWireOutput, (List)localObject2, i, localDatatype);
        }
        else
          a(paramWireOutput, i, localObject2, localDatatype);
      }
    }
    if ((paramM instanceof ExtendableMessage))
    {
      localObject1 = (ExtendableMessage)paramM;
      if (((ExtendableMessage)localObject1).a != null)
        a(paramWireOutput, ((ExtendableMessage)localObject1).a);
    }
    paramM.writeUnknownFieldMap(paramWireOutput);
  }

  Extension<ExtendableMessage<?>, ?> b(String paramString)
  {
    d locald = this.c.a;
    if (locald == null)
      return null;
    return locald.a(this.d, paramString);
  }

  Collection<FieldInfo> b()
  {
    return this.g.a();
  }

  byte[] b(M paramM)
  {
    byte[] arrayOfByte = new byte[a(paramM)];
    try
    {
      a(paramM, WireOutput.a(arrayOfByte));
      return arrayOfByte;
    }
    catch (IOException paramM)
    {
    }
    throw new RuntimeException(paramM);
  }

  String c(M paramM)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.d.getSimpleName());
    localStringBuilder.append("{");
    Iterator localIterator = b().iterator();
    Object localObject1 = "";
    while (localIterator.hasNext())
    {
      FieldInfo localFieldInfo = (FieldInfo)localIterator.next();
      Object localObject2 = a(paramM, localFieldInfo);
      if (localObject2 != null)
      {
        localStringBuilder.append((String)localObject1);
        String str = ", ";
        localStringBuilder.append(localFieldInfo.b);
        localStringBuilder.append("=");
        if (localFieldInfo.g);
        for (localObject1 = "██"; ; localObject1 = localObject2)
        {
          localStringBuilder.append(localObject1);
          localObject1 = str;
          break;
        }
      }
    }
    if ((paramM instanceof ExtendableMessage))
    {
      paramM = (ExtendableMessage)paramM;
      localStringBuilder.append((String)localObject1);
      localStringBuilder.append("{extensions=");
      localStringBuilder.append(paramM.b());
      localStringBuilder.append("}");
    }
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }

  public static final class FieldInfo
  {
    final int a;
    final String b;
    final Message.Datatype c;
    final Message.Label d;
    final Class<? extends ProtoEnum> e;
    final Class<? extends Message> f;
    final boolean g;
    MessageAdapter<? extends Message> h;
    b<? extends ProtoEnum> i;
    private final Field j;
    private final Field k;

    private FieldInfo(int paramInt, String paramString, Message.Datatype paramDatatype, Message.Label paramLabel, boolean paramBoolean, Class<?> paramClass, Field paramField1, Field paramField2)
    {
      this.a = paramInt;
      this.b = paramString;
      this.c = paramDatatype;
      this.d = paramLabel;
      this.g = paramBoolean;
      if (paramDatatype == Message.Datatype.ENUM)
      {
        this.e = paramClass;
        this.f = null;
      }
      while (true)
      {
        this.j = paramField1;
        this.k = paramField2;
        return;
        if (paramDatatype == Message.Datatype.MESSAGE)
        {
          this.f = paramClass;
          this.e = null;
        }
        else
        {
          this.e = null;
          this.f = null;
        }
      }
    }
  }

  static class a<T> extends AbstractList<T>
    implements Serializable, Cloneable, RandomAccess
  {
    private final List<T> a = new ArrayList();

    public Object clone()
    {
      return this;
    }

    public T get(int paramInt)
    {
      return this.a.get(paramInt);
    }

    public int size()
    {
      return this.a.size();
    }
  }

  private static class b
  {
    private Map<Integer, MessageAdapter.a<Object>> a;

    List<Object> a(int paramInt)
    {
      if (this.a == null)
        return null;
      return (MessageAdapter.a)this.a.get(Integer.valueOf(paramInt));
    }

    Set<Integer> a()
    {
      if (this.a == null)
        return Collections.emptySet();
      return this.a.keySet();
    }

    void a(int paramInt, Object paramObject)
    {
      if (this.a == null);
      for (MessageAdapter.a locala1 = null; ; locala1 = (MessageAdapter.a)this.a.get(Integer.valueOf(paramInt)))
      {
        MessageAdapter.a locala2 = locala1;
        if (locala1 == null)
        {
          locala2 = new MessageAdapter.a();
          if (this.a == null)
            this.a = new LinkedHashMap();
          this.a.put(Integer.valueOf(paramInt), locala2);
        }
        MessageAdapter.a.a(locala2).add(paramObject);
        return;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.wire.MessageAdapter
 * JD-Core Version:    0.6.2
 */