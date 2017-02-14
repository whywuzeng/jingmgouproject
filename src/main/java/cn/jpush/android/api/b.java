package cn.jpush.android.api;

import java.util.Set;

public final class b
{
  private static final String[] z;
  public String a;
  public Set<String> b;
  public TagAliasCallback c;

  static
  {
    String[] arrayOfString = new String[2];
    Object localObject2 = "0Rb\034\004oH";
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
          i = 99;
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
        localObject2 = "_\023z\021!}\021}-\002n\023{\016C1Rw\021\n}\001,";
        m = 1;
        k = 0;
        break;
      case 0:
        localObject4[i3] = localObject1;
        z = arrayOfString;
        return;
        i = 28;
        break label99;
        i = 114;
        break label99;
        i = 22;
        break label99;
        i = 125;
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

  public b(String paramString, Set<String> paramSet, TagAliasCallback paramTagAliasCallback)
  {
    this.a = paramString;
    this.b = paramSet;
    this.c = paramTagAliasCallback;
  }

  public final String toString()
  {
    return z[1] + this.a + z[0] + this.b;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.api.b
 * JD-Core Version:    0.6.2
 */