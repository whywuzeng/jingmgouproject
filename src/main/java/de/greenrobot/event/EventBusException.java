package de.greenrobot.event;

public class EventBusException extends RuntimeException
{
  private static final long serialVersionUID = -2912559384646531479L;

  public EventBusException(String paramString)
  {
    super(paramString);
  }

  public EventBusException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }

  public EventBusException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.event.EventBusException
 * JD-Core Version:    0.6.2
 */