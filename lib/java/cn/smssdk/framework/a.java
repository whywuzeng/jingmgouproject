package cn.smssdk.framework;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler.Callback;
import android.os.Message;

class a
  implements Handler.Callback
{
  a(FakeActivity paramFakeActivity)
  {
  }

  public boolean handleMessage(Message paramMessage)
  {
    Object localObject = (Object[])paramMessage.obj;
    paramMessage = (Context)localObject[0];
    localObject = (Intent)localObject[1];
    if (!(paramMessage instanceof Activity))
      ((Intent)localObject).addFlags(268435456);
    paramMessage.startActivity((Intent)localObject);
    return false;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.framework.a
 * JD-Core Version:    0.6.2
 */