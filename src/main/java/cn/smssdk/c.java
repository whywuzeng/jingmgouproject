package cn.smssdk;

import android.os.Handler.Callback;
import android.os.Message;

class c
  implements Handler.Callback
{
  c(a parama)
  {
  }

  public boolean handleMessage(Message paramMessage)
  {
    if (paramMessage.arg1 > 0)
      a.a(this.a, 7, -1, Integer.valueOf(paramMessage.arg1));
    return false;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.c
 * JD-Core Version:    0.6.2
 */