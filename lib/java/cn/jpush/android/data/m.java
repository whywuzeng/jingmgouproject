package cn.jpush.android.data;

import android.content.Context;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.util.a;
import cn.jpush.android.util.k;
import cn.jpush.android.util.n;
import cn.jpush.android.util.x;

final class m extends Thread
{
  private static final String[] z;

  static
  {
    String[] arrayOfString = new String[2];
    Object localObject2 = "#B\"\037\r";
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
          i = 97;
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
        localObject2 = "kC:\027[\"\005";
        m = 1;
        k = 0;
        break;
      case 0:
        localObject4[i3] = localObject1;
        z = arrayOfString;
        return;
        i = 13;
        break label99;
        i = 42;
        break label99;
        i = 86;
        break label99;
        i = 114;
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

  m(l paraml1, l paraml2, Context paramContext, int paramInt)
  {
  }

  public final void run()
  {
    String str2 = this.a.ad.j;
    String str3 = this.a.c;
    if (!p.a(str2))
    {
      ServiceInterface.a(this.a.c, 996, this.b);
      return;
    }
    if (!this.a.ad.i)
    {
      ServiceInterface.a(str3, this.c, this.b);
      l.a(this.d, this.a, this.b);
      return;
    }
    String str1 = null;
    int i = 0;
    if (i < 4)
    {
      str1 = n.a(str2, 5, 5000L);
      if (n.a(str1));
    }
    for (i = 1; ; i = 0)
    {
      if (i == 0)
      {
        ServiceInterface.a(str3, 1014, this.b);
        ServiceInterface.a(str3, 1021, a.b(this.b, str2), this.b);
        x.c();
        return;
        i += 1;
        break;
      }
      str2 = str2.substring(0, str2.lastIndexOf("/") + 1);
      if (!d.a(this.a.ad.k, this.b, str2, str3, this.a.f()))
      {
        x.c();
        ServiceInterface.a(str3, 1014, this.b);
        return;
      }
      if (this.a.f());
      for (str2 = k.b(this.b, str3) + str3 + z[0]; cn.jpush.android.util.m.a(str2, str1, this.b); str2 = k.a(this.b, str3) + str3)
      {
        this.a.ad.n = (z[1] + str2);
        ServiceInterface.a(str3, this.c, this.b);
        l.a(this.d, this.a, this.b);
        return;
      }
      ServiceInterface.a(str3, 1014, this.b);
      return;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.data.m
 * JD-Core Version:    0.6.2
 */