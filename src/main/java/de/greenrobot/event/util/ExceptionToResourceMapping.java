package de.greenrobot.event.util;

import android.util.Log;
import de.greenrobot.event.EventBus;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ExceptionToResourceMapping
{
  public final Map<Class<? extends Throwable>, Integer> throwableToMsgIdMap = new HashMap();

  public ExceptionToResourceMapping addMapping(Class<? extends Throwable> paramClass, int paramInt)
  {
    this.throwableToMsgIdMap.put(paramClass, Integer.valueOf(paramInt));
    return this;
  }

  public Integer mapThrowable(Throwable paramThrowable)
  {
    Object localObject1 = paramThrowable;
    int i = 20;
    Object localObject2;
    do
    {
      localObject2 = mapThrowableFlat((Throwable)localObject1);
      if (localObject2 != null)
        return localObject2;
      localObject2 = ((Throwable)localObject1).getCause();
      i -= 1;
      if ((i <= 0) || (localObject2 == paramThrowable))
        break;
      localObject1 = localObject2;
    }
    while (localObject2 != null);
    Log.d(EventBus.TAG, "No specific message ressource ID found for " + paramThrowable);
    return null;
  }

  protected Integer mapThrowableFlat(Throwable paramThrowable)
  {
    Class localClass2 = paramThrowable.getClass();
    paramThrowable = (Integer)this.throwableToMsgIdMap.get(localClass2);
    Object localObject = paramThrowable;
    Iterator localIterator;
    if (paramThrowable == null)
    {
      localObject = null;
      localIterator = this.throwableToMsgIdMap.entrySet().iterator();
    }
    while (true)
    {
      if (!localIterator.hasNext())
      {
        localObject = paramThrowable;
        return localObject;
      }
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Class localClass1 = (Class)localEntry.getKey();
      if ((localClass1.isAssignableFrom(localClass2)) && ((localObject == null) || (((Class)localObject).isAssignableFrom(localClass1))))
      {
        localObject = localClass1;
        paramThrowable = (Integer)localEntry.getValue();
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.event.util.ExceptionToResourceMapping
 * JD-Core Version:    0.6.2
 */