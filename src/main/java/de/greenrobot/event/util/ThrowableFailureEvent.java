package de.greenrobot.event.util;

public class ThrowableFailureEvent
  implements HasExecutionScope
{
  private Object executionContext;
  protected final boolean suppressErrorUi;
  protected final Throwable throwable;

  public ThrowableFailureEvent(Throwable paramThrowable)
  {
    this.throwable = paramThrowable;
    this.suppressErrorUi = false;
  }

  public ThrowableFailureEvent(Throwable paramThrowable, boolean paramBoolean)
  {
    this.throwable = paramThrowable;
    this.suppressErrorUi = paramBoolean;
  }

  public Object getExecutionScope()
  {
    return this.executionContext;
  }

  public Throwable getThrowable()
  {
    return this.throwable;
  }

  public boolean isSuppressErrorUi()
  {
    return this.suppressErrorUi;
  }

  public void setExecutionScope(Object paramObject)
  {
    this.executionContext = paramObject;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.event.util.ThrowableFailureEvent
 * JD-Core Version:    0.6.2
 */