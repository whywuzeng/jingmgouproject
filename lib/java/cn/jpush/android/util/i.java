package cn.jpush.android.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class i
{
  public static String a;
  private static final String z;

  static
  {
    Object localObject1 = "a\r|EJU9(X\003G<M\006\nuNvO";
    int k = -1;
    localObject1 = ((String)localObject1).toCharArray();
    int j = localObject1.length;
    int i;
    int n;
    label27: int m;
    Object localObject2;
    int i1;
    if (j <= 1)
    {
      i = 0;
      n = k;
      m = i;
      for (k = i; ; k = j)
      {
        localObject2 = localObject1;
        i1 = localObject2[k];
        switch (m % 5)
        {
        default:
          i = 103;
          label79: localObject2[k] = ((char)(i ^ i1));
          m += 1;
          if (j != 0)
            break label101;
        case 0:
        case 1:
        case 2:
        case 3:
        }
      }
      label101: k = j;
      localObject2 = localObject1;
      i1 = n;
    }
    while (true)
    {
      n = i1;
      localObject1 = localObject2;
      j = k;
      i = m;
      if (k > m)
        break label27;
      localObject1 = new String(localObject2).intern();
      switch (i1)
      {
      default:
        z = (String)localObject1;
        localObject1 = "a\r|E*U\020ac/P\031h";
        k = 0;
        break;
        i = 24;
        break label79;
        i = 116;
        break label79;
        i = 5;
        break label79;
        i = 60;
        break;
      case 0:
        a = (String)localObject1;
        return;
        m = 0;
        i1 = k;
        localObject2 = localObject1;
        k = j;
      }
    }
  }

  public static String a()
  {
    return new SimpleDateFormat(z).format(new Date());
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.util.i
 * JD-Core Version:    0.6.2
 */