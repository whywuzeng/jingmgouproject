package de.greenrobot.event;

import android.os.Looper;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventBus
{
  private static final String DEFAULT_METHOD_NAME = "onEvent";
  public static String TAG = "Event";
  private static volatile EventBus defaultInstance;
  private static final Map<Class<?>, List<Class<?>>> eventTypesCache = new HashMap();
  static ExecutorService executorService = Executors.newCachedThreadPool();
  private final AsyncPoster asyncPoster = new AsyncPoster(this);
  private final BackgroundPoster backgroundPoster = new BackgroundPoster(this);
  private final ThreadLocal<PostingThreadState> currentPostingThreadState = new ThreadLocal()
  {
    protected EventBus.PostingThreadState initialValue()
    {
      return new EventBus.PostingThreadState();
    }
  };
  private boolean logSubscriberExceptions = true;
  private final HandlerPoster mainThreadPoster = new HandlerPoster(this, Looper.getMainLooper(), 10);
  private final Map<Class<?>, Object> stickyEvents = new ConcurrentHashMap();
  private boolean subscribed;
  private final SubscriberMethodFinder subscriberMethodFinder = new SubscriberMethodFinder();
  private final Map<Class<?>, CopyOnWriteArrayList<Subscription>> subscriptionsByEventType = new HashMap();
  private final Map<Object, List<Class<?>>> typesBySubscriber = new HashMap();

  static void addInterfaces(List<Class<?>> paramList, Class<?>[] paramArrayOfClass)
  {
    int j = paramArrayOfClass.length;
    int i = 0;
    while (true)
    {
      if (i >= j)
        return;
      Class<?> localClass = paramArrayOfClass[i];
      if (!paramList.contains(localClass))
      {
        paramList.add(localClass);
        addInterfaces(paramList, localClass.getInterfaces());
      }
      i += 1;
    }
  }

  public static void clearCaches()
  {
    SubscriberMethodFinder.clearCaches();
    eventTypesCache.clear();
  }

  public static void clearSkipMethodNameVerifications()
  {
    SubscriberMethodFinder.clearSkipMethodVerifications();
  }

  private List<Class<?>> findEventTypes(Class<?> paramClass)
  {
    synchronized (eventTypesCache)
    {
      Object localObject2 = (List)eventTypesCache.get(paramClass);
      Object localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject2 = new ArrayList();
        localObject1 = paramClass;
        if (localObject1 == null)
        {
          eventTypesCache.put(paramClass, localObject2);
          localObject1 = localObject2;
        }
      }
      else
      {
        return localObject1;
      }
      ((List)localObject2).add(localObject1);
      addInterfaces((List)localObject2, ((Class)localObject1).getInterfaces());
      localObject1 = ((Class)localObject1).getSuperclass();
    }
  }

  public static EventBus getDefault()
  {
    if (defaultInstance == null);
    try
    {
      if (defaultInstance == null)
        defaultInstance = new EventBus();
      return defaultInstance;
    }
    finally
    {
    }
  }

  private void postSingleEvent(Object paramObject, PostingThreadState paramPostingThreadState)
    throws Error
  {
    Class localClass = paramObject.getClass();
    List localList = findEventTypes(localClass);
    int j = 0;
    int m = localList.size();
    int i = 0;
    if (i >= m)
    {
      if (j == 0)
      {
        Log.d(TAG, "No subscribers registered for event " + localClass);
        if ((localClass != NoSubscriberEvent.class) && (localClass != SubscriberExceptionEvent.class))
          post(new NoSubscriberEvent(this, paramObject));
      }
      return;
    }
    Object localObject = (Class)localList.get(i);
    while (true)
    {
      try
      {
        localObject = (CopyOnWriteArrayList)this.subscriptionsByEventType.get(localObject);
        int k = j;
        if (localObject != null)
        {
          k = j;
          if (!((CopyOnWriteArrayList)localObject).isEmpty())
          {
            localObject = ((CopyOnWriteArrayList)localObject).iterator();
            if (((Iterator)localObject).hasNext())
              break label181;
            k = 1;
          }
        }
        i += 1;
        j = k;
        break;
      }
      finally
      {
      }
      label181: Subscription localSubscription = (Subscription)((Iterator)localObject).next();
      paramPostingThreadState.event = paramObject;
      paramPostingThreadState.subscription = localSubscription;
      try
      {
        postToSubscription(localSubscription, paramObject, paramPostingThreadState.isMainThread);
        boolean bool = paramPostingThreadState.canceled;
        paramPostingThreadState.event = null;
        paramPostingThreadState.subscription = null;
        paramPostingThreadState.canceled = false;
        if (!bool)
          continue;
      }
      finally
      {
        paramPostingThreadState.event = null;
        paramPostingThreadState.subscription = null;
        paramPostingThreadState.canceled = false;
      }
    }
  }

  private void postToSubscription(Subscription paramSubscription, Object paramObject, boolean paramBoolean)
  {
    switch ($SWITCH_TABLE$de$greenrobot$event$ThreadMode()[paramSubscription.subscriberMethod.threadMode.ordinal()])
    {
    default:
      throw new IllegalStateException("Unknown thread mode: " + paramSubscription.subscriberMethod.threadMode);
    case 1:
      invokeSubscriber(paramSubscription, paramObject);
      return;
    case 2:
      if (paramBoolean)
      {
        invokeSubscriber(paramSubscription, paramObject);
        return;
      }
      this.mainThreadPoster.enqueue(paramSubscription, paramObject);
      return;
    case 3:
      if (paramBoolean)
      {
        this.backgroundPoster.enqueue(paramSubscription, paramObject);
        return;
      }
      invokeSubscriber(paramSubscription, paramObject);
      return;
    case 4:
    }
    this.asyncPoster.enqueue(paramSubscription, paramObject);
  }

  private void register(Object paramObject, String paramString, boolean paramBoolean, int paramInt)
  {
    try
    {
      paramString = this.subscriberMethodFinder.findSubscriberMethods(paramObject.getClass(), paramString).iterator();
      while (true)
      {
        boolean bool = paramString.hasNext();
        if (!bool)
          return;
        subscribe(paramObject, (SubscriberMethod)paramString.next(), paramBoolean, paramInt);
      }
    }
    finally
    {
    }
    throw paramObject;
  }

  private void register(Object paramObject, String paramString, boolean paramBoolean, Class<?> paramClass, Class<?>[] paramArrayOfClass)
  {
    label130: 
    while (true)
    {
      Object localObject;
      try
      {
        localObject = paramObject.getClass();
        paramString = this.subscriberMethodFinder.findSubscriberMethods((Class)localObject, paramString).iterator();
        boolean bool = paramString.hasNext();
        if (!bool)
          return;
        localObject = (SubscriberMethod)paramString.next();
        if (paramClass == ((SubscriberMethod)localObject).eventType)
        {
          subscribe(paramObject, (SubscriberMethod)localObject, paramBoolean, 0);
          continue;
        }
      }
      finally
      {
      }
      if (paramArrayOfClass != null)
      {
        int j = paramArrayOfClass.length;
        int i = 0;
        while (true)
        {
          if (i >= j)
            break label130;
          if (paramArrayOfClass[i] == ((SubscriberMethod)localObject).eventType)
          {
            subscribe(paramObject, (SubscriberMethod)localObject, paramBoolean, 0);
            break;
          }
          i += 1;
        }
      }
    }
  }

  public static void skipMethodVerificationFor(Class<?> paramClass)
  {
    SubscriberMethodFinder.skipMethodVerificationFor(paramClass);
  }

  private void subscribe(Object arg1, SubscriberMethod paramSubscriberMethod, boolean paramBoolean, int paramInt)
  {
    this.subscribed = true;
    Class localClass = paramSubscriberMethod.eventType;
    Object localObject = (CopyOnWriteArrayList)this.subscriptionsByEventType.get(localClass);
    Subscription localSubscription = new Subscription(???, paramSubscriberMethod, paramInt);
    int i;
    if (localObject == null)
    {
      paramSubscriberMethod = new CopyOnWriteArrayList();
      this.subscriptionsByEventType.put(localClass, paramSubscriberMethod);
      i = paramSubscriberMethod.size();
      paramInt = 0;
      if (paramInt <= i)
        break label260;
      label82: localObject = (List)this.typesBySubscriber.get(???);
      paramSubscriberMethod = (SubscriberMethod)localObject;
      if (localObject == null)
      {
        paramSubscriberMethod = new ArrayList();
        this.typesBySubscriber.put(???, paramSubscriberMethod);
      }
      paramSubscriberMethod.add(localClass);
      if (!paramBoolean);
    }
    while (true)
    {
      synchronized (this.stickyEvents)
      {
        paramSubscriberMethod = this.stickyEvents.get(localClass);
        if (paramSubscriberMethod != null)
        {
          if (Looper.getMainLooper() == Looper.myLooper())
          {
            paramBoolean = true;
            postToSubscription(localSubscription, paramSubscriberMethod, paramBoolean);
          }
        }
        else
        {
          return;
          Iterator localIterator = ((CopyOnWriteArrayList)localObject).iterator();
          paramSubscriberMethod = (SubscriberMethod)localObject;
          if (!localIterator.hasNext())
            break;
          if (!((Subscription)localIterator.next()).equals(localSubscription))
            continue;
          throw new EventBusException("Subscriber " + ???.getClass() + " already registered to event " + localClass);
          label260: if ((paramInt == i) || (localSubscription.priority > ((Subscription)paramSubscriberMethod.get(paramInt)).priority))
          {
            paramSubscriberMethod.add(paramInt, localSubscription);
            break label82;
          }
          paramInt += 1;
        }
      }
      paramBoolean = false;
    }
  }

  private void unubscribeByEventType(Object paramObject, Class<?> paramClass)
  {
    paramClass = (List)this.subscriptionsByEventType.get(paramClass);
    int j;
    int i;
    if (paramClass != null)
    {
      j = paramClass.size();
      i = 0;
    }
    while (true)
    {
      if (i >= j)
        return;
      Subscription localSubscription = (Subscription)paramClass.get(i);
      int m = i;
      int k = j;
      if (localSubscription.subscriber == paramObject)
      {
        localSubscription.active = false;
        paramClass.remove(i);
        m = i - 1;
        k = j - 1;
      }
      i = m + 1;
      j = k;
    }
  }

  public void cancelEventDelivery(Object paramObject)
  {
    PostingThreadState localPostingThreadState = (PostingThreadState)this.currentPostingThreadState.get();
    if (!localPostingThreadState.isPosting)
      throw new EventBusException("This method may only be called from inside event handling methods on the posting thread");
    if (paramObject == null)
      throw new EventBusException("Event may not be null");
    if (localPostingThreadState.event != paramObject)
      throw new EventBusException("Only the currently handled event may be aborted");
    if (localPostingThreadState.subscription.subscriberMethod.threadMode != ThreadMode.PostThread)
      throw new EventBusException(" event handlers may only abort the incoming event");
    localPostingThreadState.canceled = true;
  }

  public void configureLogSubscriberExceptions(boolean paramBoolean)
  {
    if (this.subscribed)
      throw new EventBusException("This method must be called before any registration");
    this.logSubscriberExceptions = paramBoolean;
  }

  public <T> T getStickyEvent(Class<T> paramClass)
  {
    synchronized (this.stickyEvents)
    {
      paramClass = paramClass.cast(this.stickyEvents.get(paramClass));
      return paramClass;
    }
  }

  void invokeSubscriber(PendingPost paramPendingPost)
  {
    Object localObject = paramPendingPost.event;
    Subscription localSubscription = paramPendingPost.subscription;
    PendingPost.releasePendingPost(paramPendingPost);
    if (localSubscription.active)
      invokeSubscriber(localSubscription, localObject);
  }

  void invokeSubscriber(Subscription paramSubscription, Object paramObject)
    throws Error
  {
    try
    {
      paramSubscription.subscriberMethod.method.invoke(paramSubscription.subscriber, new Object[] { paramObject });
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      Throwable localThrowable = localInvocationTargetException.getCause();
      if ((paramObject instanceof SubscriberExceptionEvent))
      {
        Log.e(TAG, "SubscriberExceptionEvent subscriber " + paramSubscription.subscriber.getClass() + " threw an exception", localThrowable);
        paramSubscription = (SubscriberExceptionEvent)paramObject;
        Log.e(TAG, "Initial event " + paramSubscription.causingEvent + " caused exception in " + paramSubscription.causingSubscriber, paramSubscription.throwable);
        return;
      }
      if (this.logSubscriberExceptions)
        Log.e(TAG, "Could not dispatch event: " + paramObject.getClass() + " to subscribing class " + paramSubscription.subscriber.getClass(), localThrowable);
      post(new SubscriberExceptionEvent(this, localThrowable, paramObject, paramSubscription.subscriber));
      return;
    }
    catch (IllegalAccessException paramSubscription)
    {
    }
    throw new IllegalStateException("Unexpected exception", paramSubscription);
  }

  public boolean isRegistered(Object paramObject)
  {
    try
    {
      boolean bool = this.typesBySubscriber.containsKey(paramObject);
      return bool;
    }
    finally
    {
      paramObject = finally;
    }
    throw paramObject;
  }

  public void post(Object paramObject)
  {
    PostingThreadState localPostingThreadState = (PostingThreadState)this.currentPostingThreadState.get();
    List localList = localPostingThreadState.eventQueue;
    localList.add(paramObject);
    if (localPostingThreadState.isPosting)
      return;
    if (Looper.getMainLooper() == Looper.myLooper());
    for (boolean bool = true; ; bool = false)
    {
      localPostingThreadState.isMainThread = bool;
      localPostingThreadState.isPosting = true;
      if (!localPostingThreadState.canceled)
        break;
      throw new EventBusException("Internal error. Abort state was not reset");
    }
    try
    {
      do
      {
        postSingleEvent(localList.remove(0), localPostingThreadState);
        bool = localList.isEmpty();
      }
      while (!bool);
      return;
    }
    finally
    {
      localPostingThreadState.isPosting = false;
      localPostingThreadState.isMainThread = false;
    }
    throw paramObject;
  }

  public void postSticky(Object paramObject)
  {
    synchronized (this.stickyEvents)
    {
      this.stickyEvents.put(paramObject.getClass(), paramObject);
      post(paramObject);
      return;
    }
  }

  public void register(Object paramObject)
  {
    register(paramObject, "onEvent", false, 0);
  }

  public void register(Object paramObject, int paramInt)
  {
    register(paramObject, "onEvent", false, paramInt);
  }

  @Deprecated
  public void register(Object paramObject, Class<?> paramClass, Class<?>[] paramArrayOfClass)
  {
    register(paramObject, "onEvent", false, paramClass, paramArrayOfClass);
  }

  @Deprecated
  public void register(Object paramObject, String paramString)
  {
    register(paramObject, paramString, false, 0);
  }

  @Deprecated
  public void register(Object paramObject, String paramString, Class<?> paramClass, Class<?>[] paramArrayOfClass)
  {
    register(paramObject, paramString, false, paramClass, paramArrayOfClass);
  }

  public void registerSticky(Object paramObject)
  {
    register(paramObject, "onEvent", true, 0);
  }

  public void registerSticky(Object paramObject, int paramInt)
  {
    register(paramObject, "onEvent", true, paramInt);
  }

  @Deprecated
  public void registerSticky(Object paramObject, Class<?> paramClass, Class<?>[] paramArrayOfClass)
  {
    register(paramObject, "onEvent", true, paramClass, paramArrayOfClass);
  }

  @Deprecated
  public void registerSticky(Object paramObject, String paramString)
  {
    register(paramObject, paramString, true, 0);
  }

  @Deprecated
  public void registerSticky(Object paramObject, String paramString, Class<?> paramClass, Class<?>[] paramArrayOfClass)
  {
    register(paramObject, paramString, true, paramClass, paramArrayOfClass);
  }

  public void removeAllStickyEvents()
  {
    synchronized (this.stickyEvents)
    {
      this.stickyEvents.clear();
      return;
    }
  }

  public <T> T removeStickyEvent(Class<T> paramClass)
  {
    synchronized (this.stickyEvents)
    {
      paramClass = paramClass.cast(this.stickyEvents.remove(paramClass));
      return paramClass;
    }
  }

  public boolean removeStickyEvent(Object paramObject)
  {
    synchronized (this.stickyEvents)
    {
      Class localClass = paramObject.getClass();
      if (paramObject.equals(this.stickyEvents.get(localClass)))
      {
        this.stickyEvents.remove(localClass);
        return true;
      }
      return false;
    }
  }

  public void unregister(Object paramObject)
  {
    while (true)
    {
      try
      {
        Object localObject = (List)this.typesBySubscriber.get(paramObject);
        if (localObject != null)
        {
          localObject = ((List)localObject).iterator();
          if (!((Iterator)localObject).hasNext())
          {
            this.typesBySubscriber.remove(paramObject);
            return;
          }
          unubscribeByEventType(paramObject, (Class)((Iterator)localObject).next());
          continue;
        }
      }
      finally
      {
      }
      Log.w(TAG, "Subscriber to unregister was not registered before: " + paramObject.getClass());
    }
  }

  @Deprecated
  public void unregister(Object paramObject, Class<?>[] paramArrayOfClass)
  {
    try
    {
      if (paramArrayOfClass.length == 0)
        throw new IllegalArgumentException("Provide at least one event class");
    }
    finally
    {
    }
    List localList = (List)this.typesBySubscriber.get(paramObject);
    int i;
    if (localList != null)
    {
      int j = paramArrayOfClass.length;
      i = 0;
      if (i >= j)
        if (localList.isEmpty())
          this.typesBySubscriber.remove(paramObject);
    }
    while (true)
    {
      return;
      Class<?> localClass = paramArrayOfClass[i];
      unubscribeByEventType(paramObject, localClass);
      localList.remove(localClass);
      i += 1;
      break;
      Log.w(TAG, "Subscriber to unregister was not registered before: " + paramObject.getClass());
    }
  }

  static abstract interface PostCallback
  {
    public abstract void onPostCompleted(List<SubscriberExceptionEvent> paramList);
  }

  static final class PostingThreadState
  {
    boolean canceled;
    Object event;
    List<Object> eventQueue = new ArrayList();
    boolean isMainThread;
    boolean isPosting;
    Subscription subscription;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.event.EventBus
 * JD-Core Version:    0.6.2
 */