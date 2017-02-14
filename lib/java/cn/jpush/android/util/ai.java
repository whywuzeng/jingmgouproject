package cn.jpush.android.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class ai
{
  private static String a;
  private static final String[] z;

  static
  {
    String[] arrayOfString = new String[2];
    Object localObject2 = "\006\013_";
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
          i = 34;
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
        localObject2 = "{~X\026\026~y]\035\033\n\r)ag\r";
        m = 1;
        k = 0;
        break;
      case 0:
        localObject4[i3] = localObject1;
        z = arrayOfString;
        a = z[1];
        return;
        i = 75;
        break label99;
        i = 79;
        break label99;
        i = 106;
        break label99;
        i = 37;
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

  public static boolean a(String paramString)
  {
    if (paramString == null);
    while ((paramString.length() == 0) || (paramString.trim().length() == 0))
      return true;
    return false;
  }

  public static boolean a(String paramString1, String paramString2)
  {
    if (paramString1 == null);
    while (paramString2 == null)
      return false;
    return paramString1.equals(paramString2);
  }

  public static String b(String paramString)
  {
    int i = 0;
    if ((paramString == null) || ("".equals(paramString)))
      return null;
    try
    {
      Object localObject = MessageDigest.getInstance(z[0]);
      ((MessageDigest)localObject).update(paramString.getBytes());
      paramString = ((MessageDigest)localObject).digest();
      if (paramString == null)
        return "";
      localObject = new StringBuffer(paramString.length * 2);
      while (i < paramString.length)
      {
        int j = paramString[i];
        ((StringBuffer)localObject).append(z[1].charAt(j >> 4 & 0xF)).append(z[1].charAt(j & 0xF));
        i += 1;
      }
      paramString = ((StringBuffer)localObject).toString();
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
    }
    return null;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.util.ai
 * JD-Core Version:    0.6.2
 */