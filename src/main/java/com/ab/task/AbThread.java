package com.ab.task;

import android.os.Handler;
import android.os.Message;

public class AbThread extends Thread
{
  private static final boolean D = true;
  private static String TAG = "AbHttpThread";
  private static Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      ((AbTaskItem)paramAnonymousMessage.obj).listener.update();
    }
  };
  public AbTaskItem item = null;

  public void execute(AbTaskItem paramAbTaskItem)
  {
    this.item = paramAbTaskItem;
    start();
  }

  public void run()
  {
    if ((this.item != null) && (this.item.listener != null))
    {
      this.item.listener.get();
      Message localMessage = handler.obtainMessage();
      localMessage.obj = this.item;
      handler.sendMessage(localMessage);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.task.AbThread
 * JD-Core Version:    0.6.2
 */