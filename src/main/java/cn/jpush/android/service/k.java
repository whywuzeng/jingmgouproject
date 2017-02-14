package cn.jpush.android.service;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build.VERSION;
import cn.jpush.android.util.a;
import cn.jpush.android.util.ae;
import cn.jpush.android.util.x;
import java.util.List;

final class k
  implements Runnable
{
  private static final String[] z;

  static
  {
    String[] arrayOfString = new String[6];
    int j = 0;
    Object localObject2 = "G~\rc\013x\003gG\0061\002c\024_]\017w\tHy:k\nN1S\"";
    int i = -1;
    Object localObject1 = arrayOfString;
    char[] arrayOfChar = ((String)localObject2).toCharArray();
    int k = arrayOfChar.length;
    int i1 = 0;
    int m = 0;
    int i3 = i;
    localObject2 = arrayOfChar;
    int i4 = j;
    Object localObject3 = localObject1;
    int n = k;
    Object localObject4;
    int i2;
    if (k <= 1)
    {
      localObject4 = localObject1;
      localObject1 = arrayOfChar;
      i2 = i;
      label68: n = m;
      label71: localObject2 = localObject1;
      i1 = localObject2[m];
      switch (n % 5)
      {
      default:
        i = 103;
      case 0:
      case 1:
      case 2:
      case 3:
      }
    }
    while (true)
    {
      localObject2[m] = ((char)(i ^ i1));
      n += 1;
      if (k == 0)
      {
        m = k;
        break label71;
      }
      i1 = n;
      n = k;
      localObject3 = localObject4;
      i4 = j;
      localObject2 = localObject1;
      i3 = i2;
      i2 = i3;
      localObject1 = localObject2;
      j = i4;
      localObject4 = localObject3;
      k = n;
      m = i1;
      if (n > i1)
        break label68;
      localObject1 = new String((char[])localObject2).intern();
      switch (i3)
      {
      default:
        localObject3[i4] = localObject1;
        j = 1;
        localObject2 = "_x\003gGE~\0318";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "Gp\033l\004Ct\n]\023B|\013";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "{d\035j4Nc\030k\004N";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = "\0071\002c\024_1\002c\022Er\006g\003\013e\007o\002\021";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i4] = localObject1;
        z = arrayOfString;
        return;
        i = 43;
        continue;
        i = 17;
        continue;
        i = 110;
        continue;
        i = 2;
      }
    }
  }

  k(PushService paramPushService, long paramLong)
  {
  }

  public final void run()
  {
    try
    {
      Thread.sleep(5000L);
      long l1 = System.currentTimeMillis() / 1000L;
      long l2 = ae.a(this.b.getApplicationContext(), z[2], -1L);
      new StringBuilder(z[1]).append(l1).append(z[5]).append(l2).toString();
      x.c();
      List localList;
      int i;
      if ((l2 == -1L) || (Math.abs(l1 - l2) > this.a))
      {
        localList = a.x(this.b.getApplicationContext());
        ae.b(this.b.getApplicationContext(), z[2], l1);
        i = 0;
      }
      while (i < localList.size())
      {
        Intent localIntent = new Intent();
        localIntent.setComponent((ComponentName)localList.get(i));
        if (Build.VERSION.SDK_INT >= 12)
          localIntent.setFlags(32);
        this.b.startService(localIntent);
        i += 1;
        continue;
        new StringBuilder(z[0]).append(l1 - l2).toString();
        x.c();
      }
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException.printStackTrace();
      return;
    }
    catch (SecurityException localSecurityException)
    {
      x.d(z[4], z[3]);
      localSecurityException.printStackTrace();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.service.k
 * JD-Core Version:    0.6.2
 */