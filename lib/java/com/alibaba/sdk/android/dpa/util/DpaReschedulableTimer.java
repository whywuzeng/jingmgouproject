package com.alibaba.sdk.android.dpa.util;

import java.util.Timer;
import java.util.TimerTask;

public class DpaReschedulableTimer extends Timer
{
  private Runnable task;
  private Timer timer = new Timer();
  private TimerTask timerTask;

  public void reschedule(long paramLong)
  {
    this.timerTask.cancel();
    this.timerTask = new TimerTask()
    {
      public void run()
      {
        DpaReschedulableTimer.this.task.run();
      }
    };
    this.timer.schedule(this.timerTask, paramLong);
  }

  public void schedule(Runnable paramRunnable, long paramLong)
  {
    if (this.task != null)
    {
      reschedule(paramLong);
      return;
    }
    this.task = paramRunnable;
    this.timerTask = new TimerTask()
    {
      public void run()
      {
        DpaReschedulableTimer.this.task.run();
      }
    };
    this.timer.schedule(this.timerTask, paramLong);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.dpa.util.DpaReschedulableTimer
 * JD-Core Version:    0.6.2
 */