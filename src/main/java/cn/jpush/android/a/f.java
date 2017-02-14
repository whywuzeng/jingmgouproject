package cn.jpush.android.a;

import android.os.Handler;
import android.os.Message;
import cn.jpush.android.util.x;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

final class f extends Handler
{
  private static final String[] z;
  private float b;
  private JSONArray c;

  static
  {
    String[] arrayOfString = new String[6];
    int j = 0;
    Object localObject2 = "?*\bp_\007\024 \\y\"0\001w1H";
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
        i = 11;
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
        localObject2 = "8\f=M+\002\020!W";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "+\006\"U_\007\024 \\y\"0\001w1HC";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "81\007tJ::nzN$/nzC)-\t|O";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "HHn";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = "H]n";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i4] = localObject1;
        z = arrayOfString;
        return;
        i = 104;
        continue;
        i = 99;
        continue;
        i = 78;
        continue;
        i = 57;
      }
    }
  }

  public final void handleMessage(Message paramMessage)
  {
    int i = 1;
    int j = 0;
    if (d.c(this.a) != this);
    int k;
    int m;
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              return;
              switch (paramMessage.what)
              {
              default:
                return;
              case 1:
                this.c = null;
                this.b = 1.4E-45F;
              case 2:
              case 5:
              case 10:
              }
            }
            while (d.d(this.a) != 2);
            paramMessage = d.b(this.a).c();
            d.b(this.a).g();
            if ((paramMessage != null) && (1.065353E+009F > this.b))
            {
              this.c = paramMessage;
              this.b = 1.065353E+009F;
            }
            sendEmptyMessageDelayed(2, 600L);
            return;
          }
          while (d.d(this.a) != 2);
          removeMessages(2);
          removeMessages(10);
          d.a(this.a, 3);
          if (d.b(this.a).f())
            d.b(this.a).d();
          d.b(this.a).a();
          paramMessage = new JSONArray[2];
          paramMessage[0] = this.c;
          if (d.e(this.a).a())
            paramMessage[1] = d.e(this.a).c();
          if (this.c != null)
          {
            new StringBuilder(z[2]).append(this.c.toString()).toString();
            x.d();
            this.a.a(this.c);
          }
          if (paramMessage[1] != null)
          {
            new StringBuilder(z[0]).append(paramMessage[1].toString()).toString();
            x.d();
            this.a.b(paramMessage[1]);
          }
          d.a(this.a, z[1]);
          return;
        }
        while ((d.d(this.a) != 1) || (d.f(this.a)));
        if (!d.b(this.a).f())
        {
          if (d.g(this.a) == d.b(this.a).a());
          while (i == 0)
          {
            sendEmptyMessageDelayed(10, d.b);
            return;
            i = 0;
          }
        }
      }
      while ((d.h(this.a) == null) || (d.h(this.a).length == 0));
      Object localObject = d.b(this.a).d();
      if ((localObject == null) || (localObject.length == 0))
        break label737;
      if (d.h(this.a)[0] != localObject[0])
        break;
      paramMessage = new ArrayList(d.h(this.a).length / 2);
      ArrayList localArrayList = new ArrayList(localObject.length / 2);
      k = d.h(this.a).length;
      i = 0;
      while (i < k)
      {
        paramMessage.add(Integer.valueOf(d.h(this.a)[i]));
        i += 2;
      }
      i = 0;
      while (i < localObject.length)
      {
        localArrayList.add(Integer.valueOf(localObject[i]));
        i += 2;
      }
      localObject = paramMessage.iterator();
      i = 0;
      while (((Iterator)localObject).hasNext())
        if (localArrayList.contains(((Iterator)localObject).next()))
          i += 1;
      k = paramMessage.size() - i;
      m = localArrayList.size() - i;
      if (k + m > i)
        j = 1;
    }
    while (j == 0);
    paramMessage = new StringBuilder(k).append(z[4]);
    paramMessage.append(m).append(z[5]);
    paramMessage.append(i);
    d.a(this.a, paramMessage.toString());
    return;
    d.a(this.a, z[3]);
    label737: this.a.e();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.a.f
 * JD-Core Version:    0.6.2
 */