package cn.jpush.android.util;

import cn.jpush.android.service.PushService;

public final class aj
{
  private static final String[] z;
  private String a;
  private String b;
  private boolean c = false;
  private long d;

  static
  {
    String[] arrayOfString = new String[3];
    Object localObject2 = "7\023 a0sSha\023{Jn)\001h\036k.\026:\031";
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
          i = 100;
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
        localObject2 = "=\004-";
        j = 1;
        i = 0;
        break;
      case 0:
        localObject4[i3] = localObject1;
        j = 2;
        localObject2 = "wM";
        i = 1;
        break;
      case 1:
        localObject4[i3] = localObject1;
        z = arrayOfString;
        return;
        i = 26;
        break label99;
        i = 62;
        break label99;
        i = 13;
        break label99;
        i = 65;
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

  public aj(String paramString1, String paramString2)
  {
    if (PushService.b)
    {
      this.a = paramString1;
      this.b = paramString2;
      this.d = System.currentTimeMillis();
    }
  }

  public final void a()
  {
    if (PushService.b)
    {
      String str = this.a;
      new StringBuilder(z[0]).append(this.b).append(z[1]).append(System.currentTimeMillis() - this.d).append(z[2]).toString();
      x.c();
      if (!this.c)
        this.d = System.currentTimeMillis();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.util.aj
 * JD-Core Version:    0.6.2
 */