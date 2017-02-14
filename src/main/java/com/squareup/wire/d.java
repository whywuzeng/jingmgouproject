package com.squareup.wire;

import java.util.LinkedHashMap;
import java.util.Map;

final class d
{
  private final Map<Class<? extends ExtendableMessage>, Map<Integer, Extension<?, ?>>> a = new LinkedHashMap();
  private final Map<Class<? extends ExtendableMessage>, Map<String, Extension<?, ?>>> b = new LinkedHashMap();

  public <T extends ExtendableMessage<?>, E> Extension<T, E> a(Class<T> paramClass, int paramInt)
  {
    paramClass = (Map)this.a.get(paramClass);
    if (paramClass == null)
      return null;
    return (Extension)paramClass.get(Integer.valueOf(paramInt));
  }

  public <T extends ExtendableMessage<?>, E> Extension<T, E> a(Class<T> paramClass, String paramString)
  {
    paramClass = (Map)this.b.get(paramClass);
    if (paramClass == null)
      return null;
    return (Extension)paramClass.get(paramString);
  }

  public <T extends ExtendableMessage<?>, E> void a(Extension<T, E> paramExtension)
  {
    Class localClass = paramExtension.getExtendedType();
    Map localMap = (Map)this.a.get(localClass);
    Object localObject2 = (Map)this.b.get(localClass);
    Object localObject1 = localMap;
    if (localMap == null)
    {
      localObject1 = new LinkedHashMap();
      localObject2 = new LinkedHashMap();
      this.a.put(localClass, localObject1);
      this.b.put(localClass, localObject2);
    }
    ((Map)localObject1).put(Integer.valueOf(paramExtension.getTag()), paramExtension);
    ((Map)localObject2).put(paramExtension.getName(), paramExtension);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.wire.d
 * JD-Core Version:    0.6.2
 */