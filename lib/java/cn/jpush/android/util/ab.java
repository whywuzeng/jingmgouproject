package cn.jpush.android.util;

public final class ab extends f
{
  private static final String z;

  static
  {
    Object localObject1 = "G56nRr6\"k_r&6nNB(\034OD)'xH".toCharArray();
    int j = localObject1.length;
    int m = 0;
    int i = 0;
    Object localObject2 = localObject1;
    int k = j;
    label30: int n;
    if (j <= 1)
    {
      m = i;
      k = i;
      localObject2 = localObject1;
      n = localObject2[k];
      switch (m % 5)
      {
      default:
        i = 58;
      case 0:
      case 1:
      case 2:
      case 3:
      }
    }
    while (true)
    {
      localObject2[k] = ((char)(i ^ n));
      m += 1;
      if (j == 0)
      {
        k = j;
        break label30;
      }
      k = j;
      localObject2 = localObject1;
      localObject1 = localObject2;
      j = k;
      i = m;
      if (k > m)
        break;
      z = new String(localObject2).intern();
      return;
      i = 45;
      continue;
      i = 69;
      continue;
      i = 67;
      continue;
      i = 29;
    }
  }

  public static String a(String paramString)
  {
    return b(z + paramString, "");
  }

  public static void c(String paramString1, String paramString2)
  {
    a(z + paramString1, paramString2);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.util.ab
 * JD-Core Version:    0.6.2
 */