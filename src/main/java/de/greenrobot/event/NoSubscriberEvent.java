package de.greenrobot.event;

public final class NoSubscriberEvent
{
  public final EventBus eventBus;
  public final Object originalEvent;

  public NoSubscriberEvent(EventBus paramEventBus, Object paramObject)
  {
    this.eventBus = paramEventBus;
    this.originalEvent = paramObject;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.event.NoSubscriberEvent
 * JD-Core Version:    0.6.2
 */