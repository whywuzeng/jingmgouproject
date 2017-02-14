package com.baidu.location.h;

import android.location.Location;
import android.os.Handler;
import android.os.Message;
import com.baidu.location.f;

class i extends Handler
{
  i(a parama)
  {
  }

  public void handleMessage(Message paramMessage)
  {
    if (!f.isServing);
    do
    {
      return;
      switch (paramMessage.what)
      {
      default:
        return;
      case 1:
        a.jdMethod_if(this.a, (Location)paramMessage.obj);
        return;
      case 2:
      case 3:
      case 4:
      }
    }
    while (a.jdMethod_do(this.a) == null);
    a.jdMethod_do(this.a).a((String)paramMessage.obj);
    return;
    a.jdMethod_if(this.a, "&og=1", (Location)paramMessage.obj);
    return;
    a.jdMethod_if(this.a, "&og=2", (Location)paramMessage.obj);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.h.i
 * JD-Core Version:    0.6.2
 */