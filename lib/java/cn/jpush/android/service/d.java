package cn.jpush.android.service;

import android.app.NotificationManager;
import android.os.Handler;
import cn.jpush.android.data.l;
import cn.jpush.android.data.p;
import cn.jpush.android.data.y;
import cn.jpush.android.util.x;
import java.util.concurrent.ConcurrentLinkedQueue;

final class d
  implements c
{
  private static final String[] z;

  static
  {
    String[] arrayOfString = new String[3];
    Object localObject2 = ";XJ%Yy\024A+Jr\034\024";
    int i = -1;
    int j = 0;
    Object localObject1 = arrayOfString;
    localObject2 = ((String)localObject2).toCharArray();
    int k = localObject2.length;
    int m;
    Object localObject3;
    int n;
    int i1;
    label50: int i2;
    label99: label121: Object localObject4;
    int i3;
    if (k <= 1)
    {
      m = 0;
      localObject3 = localObject1;
      n = j;
      localObject1 = localObject2;
      i1 = i;
      j = m;
      while (true)
      {
        localObject2 = localObject1;
        i2 = localObject2[m];
        switch (j % 5)
        {
        default:
          i = 46;
          localObject2[m] = ((char)(i ^ i2));
          j += 1;
          if (k != 0)
            break label121;
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
        break label50;
      localObject1 = new String((char[])localObject2).intern();
      switch (i2)
      {
      default:
        localObject4[i3] = localObject1;
        localObject2 = ";XZ%Zv\024\024";
        j = 1;
        i = 0;
        break;
      case 0:
        localObject4[i3] = localObject1;
        j = 2;
        localObject2 = "g\035M/@cB";
        i = 1;
        break;
      case 1:
        localObject4[i3] = localObject1;
        z = arrayOfString;
        return;
        i = 23;
        break label99;
        i = 120;
        break label99;
        i = 46;
        break label99;
        i = 74;
        break label99;
        m = 0;
        i2 = i;
        i3 = j;
        localObject4 = localObject1;
        i = k;
        j = m;
      }
    }
  }

  d(DownloadService paramDownloadService, boolean paramBoolean, int paramInt)
  {
  }

  public final void a(int paramInt)
  {
    DownloadService.c(this.c).cancel(this.b);
    if (a.a(paramInt))
    {
      DownloadService.a(this.c).L = true;
      ServiceInterface.a(DownloadService.a(this.c).c, 1011, this.c);
    }
    try
    {
      String str1 = ((l)DownloadService.a(this.c)).ac;
      ServiceInterface.a(DownloadService.a(this.c).c, 1022, cn.jpush.android.util.a.b(this.c, str1), this.c);
      DownloadService.a(this.c).M = true;
      DownloadService.a(this.c, this.b, DownloadService.a(this.c), paramInt);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        String str2 = "";
    }
  }

  public final void a(long paramLong1, long paramLong2)
  {
    int i = (int)((float)paramLong1 / (float)paramLong2 * 100.0F);
    new StringBuilder(z[2]).append(i).append(z[0]).append(paramLong1).append(z[1]).append(paramLong2).toString();
    x.c();
    if (!this.a)
      DownloadService.a(this.c, DownloadService.a(this.c), this.b, paramLong1, paramLong2);
  }

  public final void a(String paramString, boolean paramBoolean)
  {
    DownloadService.a(this.c).N = true;
    DownloadService.a.remove(DownloadService.a(this.c));
    int j = 1001;
    int i;
    if (DownloadService.a(this.c).a())
    {
      l locall = (l)DownloadService.a(this.c);
      locall.ah = paramString;
      locall.ai = false;
      i = j;
      if (p.a(locall.Y, locall.Z, this.c))
      {
        i = 1003;
        locall.ai = true;
      }
    }
    while (true)
    {
      if (paramBoolean)
        i = 1013;
      ServiceInterface.a(DownloadService.a(this.c).c, i, this.c);
      if (!DownloadService.a(this.c).f())
        DownloadService.b(this.c).sendEmptyMessageDelayed(this.b, 500L);
      DownloadService.a(this.c).M = true;
      DownloadService.a(this.c, DownloadService.a(this.c));
      return;
      if (DownloadService.a(this.c).b())
      {
        ((y)DownloadService.a(this.c)).aa = paramString;
        i = 1004;
      }
      else
      {
        i = j;
        if (DownloadService.a(this.c).f())
        {
          DownloadService.a(this.c).U = paramString;
          i = j;
        }
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.service.d
 * JD-Core Version:    0.6.2
 */