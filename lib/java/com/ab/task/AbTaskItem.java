package com.ab.task;

public class AbTaskItem
{
  public AbTaskListener listener;
  public int position;
  private Object result;

  public AbTaskListener getListener()
  {
    return this.listener;
  }

  public int getPosition()
  {
    return this.position;
  }

  public Object getResult()
  {
    return this.result;
  }

  public void setListener(AbTaskListener paramAbTaskListener)
  {
    this.listener = paramAbTaskListener;
  }

  public void setPosition(int paramInt)
  {
    this.position = paramInt;
  }

  public void setResult(Object paramObject)
  {
    this.result = paramObject;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.task.AbTaskItem
 * JD-Core Version:    0.6.2
 */