package cn.jpush.android.data;

import android.content.Context;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.util.ai;

final class n extends Thread
{
  private static final String[] z;

  static
  {
    String[] arrayOfString = new String[2];
    Object localObject2 = "IY\034P,VW";
    int k = -1;
    int m = 0;
    Object localObject1 = arrayOfString;
    localObject2 = ((String)localObject2).toCharArray();
    int j = localObject2.length;
    int i;
    Object localObject3;
    int n;
    int i1;
    label50: int i2;
    label99: label121: Object localObject4;
    int i3;
    if (j <= 1)
    {
      i = 0;
      localObject3 = localObject1;
      n = m;
      localObject1 = localObject2;
      i1 = k;
      m = i;
      for (k = i; ; k = j)
      {
        localObject2 = localObject1;
        i2 = localObject2[k];
        switch (m % 5)
        {
        default:
          i = 115;
          localObject2[k] = ((char)(i ^ i2));
          m += 1;
          if (j != 0)
            break label121;
        case 0:
        case 1:
        case 2:
        case 3:
        }
      }
      k = j;
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
      j = k;
      i = m;
      if (k > m)
        break label50;
      localObject1 = new String((char[])localObject2).intern();
      switch (i2)
      {
      default:
        localObject4[i3] = localObject1;
        localObject2 = "IY\034P,VS";
        m = 1;
        k = 0;
        break;
      case 0:
        localObject4[i3] = localObject1;
        z = arrayOfString;
        return;
        i = 63;
        break label99;
        i = 48;
        break label99;
        i = 121;
        break label99;
        i = 39;
        break label99;
        i = 0;
        i2 = k;
        i3 = m;
        localObject4 = localObject1;
        k = j;
        m = i;
      }
    }
  }

  n(l paraml1, Context paramContext, int paramInt, l paraml2)
  {
  }

  public final void run()
  {
    if (p.a(this.d.ad.b))
      this.d.ad.l = d.a(this.d.ad.b, this.d.c, z[1], this.a);
    if (p.a(this.d.ad.h))
      this.d.ad.m = d.a(this.d.ad.h, this.d.c, z[0], this.a);
    if ((ai.a(this.d.ad.l)) || (ai.a(this.d.ad.m)))
    {
      ServiceInterface.a(this.d.c, 1014, this.a);
      return;
    }
    ServiceInterface.a(this.d.c, this.b, this.a);
    l.a(this.d, this.c, this.a);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.data.n
 * JD-Core Version:    0.6.2
 */