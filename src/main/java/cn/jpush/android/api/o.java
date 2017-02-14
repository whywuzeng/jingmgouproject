package cn.jpush.android.api;

import android.os.Bundle;
import android.os.Message;
import cn.jpush.android.data.d;

final class o
  implements cn.jpush.android.util.p
{
  private static final String[] z;

  static
  {
    String[] arrayOfString = new String[3];
    Object localObject2 = "\037\036mbS\030\003i";
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
    label98: label120: Object localObject4;
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
          i = 3;
          localObject2[m] = ((char)(i ^ i2));
          j += 1;
          if (k != 0)
            break label120;
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
        localObject2 = "\020\004Rr`\032";
        j = 1;
        i = 0;
        break;
      case 0:
        localObject4[i3] = localObject1;
        j = 2;
        localObject2 = "\024\022rtb\036\022Hc";
        i = 1;
        break;
      case 1:
        localObject4[i3] = localObject1;
        z = arrayOfString;
        return;
        i = 121;
        break label98;
        i = 119;
        break label98;
        i = 1;
        break label98;
        i = 7;
        break label98;
        m = 0;
        i2 = i;
        i3 = j;
        localObject4 = localObject1;
        i = k;
        j = m;
      }
    }
  }

  o(p paramp, int paramInt, d paramd)
  {
  }

  public final void a(boolean paramBoolean, String paramString)
  {
    Message localMessage = this.a.obtainMessage(this.b);
    Bundle localBundle = new Bundle();
    localBundle.putBoolean(z[1], paramBoolean);
    localBundle.putString(z[0], paramString);
    localBundle.putString(z[2], this.c.c);
    localMessage.setData(localBundle);
    localMessage.sendToTarget();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.api.o
 * JD-Core Version:    0.6.2
 */