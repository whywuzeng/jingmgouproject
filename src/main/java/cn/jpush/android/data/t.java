package cn.jpush.android.data;

import android.content.Context;
import cn.jpush.android.api.q;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.util.a;
import cn.jpush.android.util.k;
import cn.jpush.android.util.n;
import cn.jpush.android.util.x;
import java.util.ArrayList;

final class t extends Thread
{
  private static final String[] z;

  static
  {
    String[] arrayOfString = new String[7];
    int j = 0;
    Object localObject2 = "q\0040sI8B";
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
        i = 115;
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
        localObject2 = "9\005({\037";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "";
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
        localObject2 = "";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = "";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i4] = localObject1;
        j = 6;
        localObject2 = ";M2s\026s=.s\037x\f8D\026d\002)d\020r";
        i = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i4] = localObject1;
        z = arrayOfString;
        return;
        i = 23;
        continue;
        i = 109;
        continue;
        i = 92;
        continue;
        i = 22;
      }
    }
  }

  t(s params1, Context paramContext, s params2)
  {
  }

  public final void run()
  {
    new StringBuilder(z[5]).append(this.c.X).append(z[4]).append(this.c.S).append(z[6]).append(this.c.Y).toString();
    x.b();
    if (this.c.X == 1)
    {
      ServiceInterface.a(this.c.c, 995, this.a);
      cn.jpush.android.api.m.a(this.a, this.c);
    }
    String str3;
    String str2;
    do
    {
      str3 = this.b.c;
      str2 = this.b.a;
      str1 = this.b.W;
      if (this.c.Y)
        break;
      ServiceInterface.a(str3, 995, this.a);
      cn.jpush.android.api.m.a(this.a, this.b);
      do
      {
        return;
        if (this.c.X == 2)
        {
          x.c();
          q.a(this.a, this.b);
          break;
        }
      }
      while ((this.c.S.equals("0")) || (this.c.S.length() > 1));
    }
    while (this.c.X == 0);
    new StringBuilder(z[2]).append(this.c.X).toString();
    x.c();
    return;
    if ((this.b.Z.isEmpty()) && (str1.equals("")))
    {
      this.b.ac = this.b.a;
      ServiceInterface.a(str3, 995, this.a);
      cn.jpush.android.api.m.a(this.a, this.b);
      return;
    }
    String str1 = null;
    int i = 0;
    label326: if (i < 4)
    {
      str1 = n.a(str2, 5, 5000L);
      if (n.a(str1));
    }
    for (i = 1; ; i = 0)
    {
      if (i == 0)
      {
        x.c();
        ServiceInterface.a(str3, 1014, this.a);
        ServiceInterface.a(str3, 1021, a.b(this.a, str2), this.a);
        return;
        i += 1;
        break label326;
      }
      String str4 = str2.substring(0, str2.lastIndexOf("/") + 1);
      boolean bool = d.a(this.b.Z, this.a, str4, str3, this.b.f());
      if ((this.c.q) && (!bool))
      {
        x.c();
        ServiceInterface.a(str3, 1014, this.a);
        return;
      }
      String str5 = k.b(this.a, str3);
      if (this.b.f());
      for (str2 = str5 + str3 + z[1]; ; str2 = str5 + str3)
      {
        if (!cn.jpush.android.util.m.a(str2, str1.replaceAll(z[3] + str4, z[3] + str5), this.a))
          break label664;
        this.b.ac = (z[0] + str2);
        ServiceInterface.a(str3, 995, this.a);
        if (this.c.X != 0)
          break;
        cn.jpush.android.api.m.a(this.a, this.b);
        return;
      }
      label664: ServiceInterface.a(str3, 1014, this.a);
      return;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.data.t
 * JD-Core Version:    0.6.2
 */