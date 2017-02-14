package cn.jpush.android.service;

import android.os.AsyncTask;
import java.net.InetAddress;
import java.net.UnknownHostException;

final class p extends AsyncTask<Void, Void, Void>
{
  String a = "";
  int b = 0;

  public p(PushService paramPushService, String paramString, int paramInt)
  {
    this.a = paramString;
    this.b = paramInt;
    paramPushService.I = null;
    PushService.a(paramPushService, false);
    paramPushService.J = null;
  }

  private Void a()
  {
    try
    {
      PushService.a(this.c, false);
      if (this.b == 0)
      {
        this.c.I = InetAddress.getAllByName(this.a);
        if (this.c.I != null)
          PushService.a(this.c, true);
      }
      else if (1 == this.b)
      {
        this.c.J = InetAddress.getByName(this.a);
      }
    }
    catch (UnknownHostException localUnknownHostException)
    {
    }
    return null;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.service.p
 * JD-Core Version:    0.6.2
 */