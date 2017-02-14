package de.greenrobot.event;

import java.util.concurrent.ExecutorService;

final class BackgroundPoster
  implements Runnable
{
  private final EventBus eventBus;
  private volatile boolean executorRunning;
  private final PendingPostQueue queue;

  BackgroundPoster(EventBus paramEventBus)
  {
    this.eventBus = paramEventBus;
    this.queue = new PendingPostQueue();
  }

  public void enqueue(Subscription paramSubscription, Object paramObject)
  {
    paramSubscription = PendingPost.obtainPendingPost(paramSubscription, paramObject);
    try
    {
      this.queue.enqueue(paramSubscription);
      if (!this.executorRunning)
      {
        this.executorRunning = true;
        EventBus.executorService.execute(this);
      }
      return;
    }
    finally
    {
    }
    throw paramSubscription;
  }

  public void run()
  {
    try
    {
      PendingPost localPendingPost2 = this.queue.poll(1000);
      localPendingPost1 = localPendingPost2;
      if (localPendingPost2 != null);
    }
    catch (InterruptedException localInterruptedException)
    {
    }
    finally
    {
      PendingPost localPendingPost1;
      this.executorRunning = false;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     de.greenrobot.event.BackgroundPoster
 * JD-Core Version:    0.6.2
 */