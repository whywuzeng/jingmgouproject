package de.greenrobot.event;

final class Subscription
{
  volatile boolean active;
  final int priority;
  final Object subscriber;
  final SubscriberMethod subscriberMethod;

  Subscription(Object paramObject, SubscriberMethod paramSubscriberMethod, int paramInt)
  {
    this.subscriber = paramObject;
    this.subscriberMethod = paramSubscriberMethod;
    this.priority = paramInt;
    this.active = true;
  }

  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if ((paramObject instanceof Subscription))
    {
      paramObject = (Subscription)paramObject;
      bool1 = bool2;
      if (this.subscriber == paramObject.subscriber)
      {
        bool1 = bool2;
        if (this.subscriberMethod.equals(paramObject.subscriberMethod))
          bool1 = true;
      }
    }
    return bool1;
  }

  public int hashCode()
  {
    return this.subscriber.hashCode() + this.subscriberMethod.methodString.hashCode();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.event.Subscription
 * JD-Core Version:    0.6.2
 */