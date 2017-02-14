package de.greenrobot.event;

import android.util.Log;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class SubscriberMethodFinder
{
  private static final int MODIFIERS_IGNORE = 1032;
  private static final Map<String, List<SubscriberMethod>> methodCache = new HashMap();
  private static final Map<Class<?>, Class<?>> skipMethodVerificationForClasses = new ConcurrentHashMap();

  static void clearCaches()
  {
    synchronized (methodCache)
    {
      methodCache.clear();
      return;
    }
  }

  public static void clearSkipMethodVerifications()
  {
    skipMethodVerificationForClasses.clear();
  }

  static void skipMethodVerificationFor(Class<?> paramClass)
  {
    if (!methodCache.isEmpty())
      throw new IllegalStateException("This method must be called before registering anything");
    skipMethodVerificationForClasses.put(paramClass, paramClass);
  }

  List<SubscriberMethod> findSubscriberMethods(Class<?> arg1, String paramString)
  {
    String str1 = ???.getName() + '.' + paramString;
    synchronized (methodCache)
    {
      localObject2 = (List)methodCache.get(str1);
      if (localObject2 != null)
        return localObject2;
    }
    ArrayList localArrayList = new ArrayList();
    Object localObject2 = ???;
    HashSet localHashSet = new HashSet();
    StringBuilder localStringBuilder = new StringBuilder();
    Method[] arrayOfMethod;
    int i;
    while (true)
    {
      if (localObject2 == null);
      do
      {
        if (!localArrayList.isEmpty())
          break;
        throw new EventBusException("Subscriber " + ??? + " has no public methods called " + paramString);
        ??? = ((Class)localObject2).getName();
      }
      while ((((String)???).startsWith("java.")) || (((String)???).startsWith("javax.")) || (((String)???).startsWith("android.")));
      arrayOfMethod = ((Class)localObject2).getMethods();
      int j = arrayOfMethod.length;
      i = 0;
      if (i < j)
        break;
      localObject2 = ((Class)localObject2).getSuperclass();
    }
    Method localMethod = arrayOfMethod[i];
    String str2 = localMethod.getName();
    if (str2.startsWith(paramString))
    {
      int k = localMethod.getModifiers();
      if (((k & 0x1) == 0) || ((k & 0x408) != 0))
        break label465;
      Object localObject3 = localMethod.getParameterTypes();
      if (localObject3.length == 1)
      {
        ??? = str2.substring(paramString.length());
        if (((String)???).length() != 0)
          break label373;
        ??? = ThreadMode.PostThread;
        label296: localObject3 = localObject3[0];
        localStringBuilder.setLength(0);
        localStringBuilder.append(str2);
        localStringBuilder.append('>').append(((Class)localObject3).getName());
        if (localHashSet.add(localStringBuilder.toString()))
          localArrayList.add(new SubscriberMethod(localMethod, (ThreadMode)???, (Class)localObject3));
      }
    }
    while (true)
    {
      i += 1;
      break;
      label373: if (((String)???).equals("MainThread"))
      {
        ??? = ThreadMode.MainThread;
        break label296;
      }
      if (((String)???).equals("BackgroundThread"))
      {
        ??? = ThreadMode.BackgroundThread;
        break label296;
      }
      if (((String)???).equals("Async"))
      {
        ??? = ThreadMode.Async;
        break label296;
      }
      if (!skipMethodVerificationForClasses.containsKey(localObject2))
      {
        throw new EventBusException("Illegal onEvent method, check for typos: " + localMethod);
        label465: if (!skipMethodVerificationForClasses.containsKey(localObject2))
          Log.d(EventBus.TAG, "Skipping method (not public, static or abstract): " + localObject2 + "." + str2);
      }
    }
    synchronized (methodCache)
    {
      methodCache.put(str1, localArrayList);
      return localArrayList;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.event.SubscriberMethodFinder
 * JD-Core Version:    0.6.2
 */