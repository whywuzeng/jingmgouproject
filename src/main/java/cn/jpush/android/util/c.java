package cn.jpush.android.util;

import java.io.UnsupportedEncodingException;

public class c
{
  private static final String z;

  static
  {
    boolean bool = false;
    Object localObject1 = "\037\021b<{\t\013\006".toCharArray();
    int j = localObject1.length;
    int i;
    int m;
    int k;
    Object localObject2;
    if (j <= 1)
    {
      i = 0;
      m = i;
      for (k = i; ; k = j)
      {
        localObject2 = localObject1;
        int n = localObject2[k];
        switch (m % 5)
        {
        default:
          i = 40;
          label71: localObject2[k] = ((char)(i ^ n));
          m += 1;
          if (j != 0)
            break label93;
        case 0:
        case 1:
        case 2:
        case 3:
        }
      }
      label93: k = j;
      localObject2 = localObject1;
    }
    while (true)
    {
      localObject1 = localObject2;
      j = k;
      i = m;
      if (k > m)
        break;
      z = new String(localObject2).intern();
      if (!c.class.desiredAssertionStatus())
        bool = true;
      a = bool;
      return;
      i = 74;
      break label71;
      i = 66;
      break label71;
      i = 79;
      break label71;
      i = 125;
      break label71;
      m = 0;
      localObject2 = localObject1;
      k = j;
    }
  }

  public static String a(byte[] paramArrayOfByte, int paramInt)
  {
    int i = 1;
    while (true)
    {
      int k;
      e locale;
      int j;
      try
      {
        k = paramArrayOfByte.length;
        locale = new e(10, null);
        j = k / 3 * 4;
        if (locale.d)
        {
          paramInt = j;
          if (k % 3 > 0)
            paramInt = j + 4;
          if ((!locale.e) || (k <= 0))
            break label182;
          j = (k - 1) / 57;
          if (!locale.f)
            break label185;
          i = 2;
          break label185;
          locale.a = new byte[paramInt];
          locale.a(paramArrayOfByte, 0, k, true);
          if ((a) || (locale.b == paramInt))
            break label164;
          throw new AssertionError();
        }
      }
      catch (UnsupportedEncodingException paramArrayOfByte)
      {
        throw new AssertionError(paramArrayOfByte);
      }
      paramInt = j;
      switch (k % 3)
      {
      case 0:
        paramArrayOfByte = new String(locale.a, z);
        return paramArrayOfByte;
        continue;
        paramInt = i * (j + 1) + paramInt;
        break;
      default:
        paramInt = j;
        break;
      case 1:
        paramInt = j + 2;
        break;
      case 2:
        label164: label182: label185: paramInt = j + 3;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.util.c
 * JD-Core Version:    0.6.2
 */