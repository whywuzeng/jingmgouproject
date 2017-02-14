package cn.jpush.android.service;

import android.os.Handler;
import android.os.Message;
import cn.jpush.android.api.TagAliasCallback;
import cn.jpush.android.api.b;
import cn.jpush.android.api.d;
import cn.jpush.android.util.x;

final class n extends Handler
{
  private static final String[] z;

  static
  {
    String[] arrayOfString = new String[4];
    Object localObject1 = "";
    int i = -1;
    int j = 0;
    Object localObject3 = arrayOfString;
    localObject1 = ((String)localObject1).toCharArray();
    int k = localObject1.length;
    int m;
    int n;
    int i1;
    label42: Object localObject2;
    int i2;
    label91: label113: Object localObject4;
    int i3;
    if (k <= 1)
    {
      m = 0;
      n = j;
      i1 = i;
      j = m;
      while (true)
      {
        localObject2 = localObject1;
        i2 = localObject2[m];
        switch (j % 5)
        {
        default:
          i = 86;
          localObject2[m] = ((char)(i ^ i2));
          j += 1;
          if (k != 0)
            break label113;
          m = k;
        case 0:
        case 1:
        case 2:
        case 3:
        }
      }
      i = k;
      localObject4 = localObject3;
      i3 = n;
      localObject2 = localObject1;
      i2 = i1;
    }
    while (true)
    {
      i1 = i2;
      localObject1 = localObject2;
      n = i3;
      localObject3 = localObject4;
      k = i;
      m = j;
      if (i > j)
        break label42;
      localObject1 = new String(localObject2).intern();
      switch (i2)
      {
      default:
        localObject4[i3] = localObject1;
        localObject1 = "i\034,|:T\0348\0357^\026k[7T\021.Yx\035\016.L\037Y]v\035";
        j = 1;
        i = 0;
        break;
      case 0:
        localObject4[i3] = localObject1;
        localObject1 = "^\023eW&H\016#\0237S\0319R?YS\"S\"X\023?\023\025r3\005x\025i4\004s";
        j = 2;
        i = 1;
        break;
      case 1:
        localObject4[i3] = localObject1;
        j = 3;
        localObject1 = "h\023.E&X\036?X2\007]>S>\\\023/Q3Y]&N1\035Pk";
        i = 2;
        break;
      case 2:
        localObject4[i3] = localObject1;
        z = arrayOfString;
        return;
        i = 61;
        break label91;
        i = 125;
        break label91;
        i = 75;
        break label91;
        i = 61;
        break label91;
        m = 0;
        i2 = i;
        localObject2 = localObject1;
        i3 = j;
        localObject4 = localObject3;
        i = k;
        j = m;
      }
    }
  }

  n(PushService paramPushService)
  {
  }

  public final void handleMessage(Message paramMessage)
  {
    int i = 0;
    super.handleMessage(paramMessage);
    switch (paramMessage.what)
    {
    default:
      new StringBuilder(z[3]).append(paramMessage.what).toString();
      x.b();
    case 1001:
    case 1003:
    case 1002:
    case 1004:
    case 1005:
    case 1007:
    case 1008:
    case 1006:
    }
    while (true)
    {
      return;
      x.c();
      PushService.d(this.a);
      this.a.stopSelf();
      this.a.a(0, z[2]);
      return;
      this.a.stopSelf();
      return;
      if ((PushService.e(this.a) == null) || (!PushService.e(this.a).isAlive()))
        continue;
      PushService.f(this.a);
      return;
      PushService.c(this.a, true);
      return;
      PushService.c(this.a, false);
      return;
      x.f();
      PushService.g(this.a);
      return;
      try
      {
        i = ((Integer)paramMessage.obj).intValue();
        if (!PushService.c(this.a, i))
          continue;
        new StringBuilder(z[1]).append(i).append(z[0]).append(paramMessage.what).toString();
        x.f();
        PushService.g(this.a);
        return;
      }
      catch (Exception localException)
      {
        while (true)
          i = 0;
      }
      try
      {
        int j = ((Integer)paramMessage.obj).intValue();
        i = j;
        label270: if (i == 0)
          continue;
        paramMessage = ServiceInterface.a(i);
        if (paramMessage != null)
        {
          TagAliasCallback localTagAliasCallback = paramMessage.c;
          if (localTagAliasCallback != null)
            localTagAliasCallback.gotResult(d.b, paramMessage.a, paramMessage.b);
        }
        ServiceInterface.b(i);
        return;
      }
      catch (Exception paramMessage)
      {
        break label270;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.service.n
 * JD-Core Version:    0.6.2
 */