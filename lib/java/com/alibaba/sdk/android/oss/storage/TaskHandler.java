package com.alibaba.sdk.android.oss.storage;

import java.util.concurrent.atomic.AtomicBoolean;

public class TaskHandler
{
  private AtomicBoolean cancelFlag;

  public TaskHandler(AtomicBoolean paramAtomicBoolean)
  {
    this.cancelFlag = paramAtomicBoolean;
  }

  public void cancel()
  {
    this.cancelFlag.set(true);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.oss.storage.TaskHandler
 * JD-Core Version:    0.6.2
 */