package cn.jpush.android.data;

import cn.jpush.android.util.ai;

public final class e
{
  private static final String[] z;
  public String a;
  public String b;

  static
  {
    String[] arrayOfString = new String[2];
    Object localObject2 = "{|\016\003l2.\\\005~2\003C\037}\b5JL'w";
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
          i = 26;
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
        localObject2 = ":/I3s3|\023L";
        m = 1;
        k = 0;
        break;
      case 0:
        localObject4[i3] = localObject1;
        z = arrayOfString;
        return;
        i = 87;
        break label99;
        i = 92;
        break label99;
        i = 46;
        break label99;
        i = 108;
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

  public e(d paramd1, d paramd2)
  {
    this.a = paramd2.c;
    this.b = paramd2.d;
  }

  public final boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof e));
    do
    {
      do
      {
        return false;
        paramObject = (e)paramObject;
      }
      while ((ai.a(this.a)) || (ai.a(paramObject.a)) || (!ai.a(this.a, paramObject.a)));
      if ((ai.a(this.b)) && (ai.a(paramObject.b)))
        return true;
    }
    while ((ai.a(this.b)) || (ai.a(paramObject.b)) || (!ai.a(this.b, paramObject.b)));
    return true;
  }

  public final String toString()
  {
    return z[1] + this.a + z[0] + this.b;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.data.e
 * JD-Core Version:    0.6.2
 */