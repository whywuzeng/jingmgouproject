package cn.jpush.android.util;

import cn.jpush.android.api.d;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class z
{
  public static final Pattern a;
  public static final Pattern b;
  public static final Pattern c;
  public static final Pattern d;
  public static final Pattern e;
  public static final Pattern f = Pattern.compile((String)localObject1);
  private static final String[] z;

  static
  {
    Object localObject2 = new String[3];
    int j = 0;
    Object localObject3 = "\0346乿\005鿸r@FIp8,Rr\002\037F[";
    int i = -1;
    Object localObject1 = localObject2;
    int k;
    int i1;
    int m;
    int i4;
    Object localObject4;
    int n;
    int i2;
    while (true)
    {
      char[] arrayOfChar = ((String)localObject3).toCharArray();
      k = arrayOfChar.length;
      i1 = 0;
      m = 0;
      int i3 = i;
      localObject3 = arrayOfChar;
      i4 = j;
      localObject4 = localObject1;
      n = k;
      Object localObject5;
      if (k <= 1)
      {
        localObject5 = localObject1;
        localObject1 = arrayOfChar;
        i2 = i;
      }
      label143: 
      do
      {
        n = m;
        while (true)
        {
          localObject3 = localObject1;
          i1 = localObject3[m];
          switch (n % 5)
          {
          default:
            i = 93;
            localObject3[m] = ((char)(i ^ i1));
            n += 1;
            if (k != 0)
              break label143;
            m = k;
          case 0:
          case 1:
          case 2:
          case 3:
          }
        }
        i1 = n;
        n = k;
        localObject4 = localObject5;
        i4 = j;
        localObject3 = localObject1;
        i3 = i2;
        i2 = i3;
        localObject1 = localObject3;
        j = i4;
        localObject5 = localObject4;
        k = n;
        m = i1;
      }
      while (n > i1);
      localObject1 = new String((char[])localObject3).intern();
      switch (i3)
      {
      default:
        localObject4[i4] = localObject1;
        j = 1;
        localObject3 = "\0346乿\005鿸r@FIp8,Rr\002\037\026O\004ir\020[";
        i = 0;
        localObject1 = localObject2;
        break;
      case 0:
        localObject4[i4] = localObject1;
        j = 2;
        localObject3 = "";
        i = 1;
        localObject1 = localObject2;
      case 1:
      }
    }
    localObject4[i4] = localObject1;
    z = (String[])localObject2;
    localObject1 = "";
    i = -1;
    while (true)
    {
      label294: localObject1 = ((String)localObject1).toCharArray();
      j = localObject1.length;
      n = 0;
      k = 0;
      i2 = i;
      localObject2 = localObject1;
      m = j;
      if (j <= 1)
        i1 = i;
      label329: 
      do
      {
        m = k;
        localObject2 = localObject1;
        n = localObject2[k];
        switch (m % 5)
        {
        default:
          i = 93;
        case 0:
        case 1:
        case 2:
        case 3:
        }
        while (true)
        {
          localObject2[k] = ((char)(i ^ n));
          m += 1;
          if (j != 0)
            break label662;
          k = j;
          break label329;
          i = 66;
          break;
          i = 109;
          break;
          i = 127;
          break;
          i = 40;
          break;
          c = Pattern.compile((String)localObject1);
          localObject3 = new StringBuilder(z[2]).append(a);
          localObject1 = "k\021".toCharArray();
          j = localObject1.length;
          m = 0;
          i = 0;
          localObject2 = localObject1;
          k = j;
          if (j <= 1);
          do
          {
            m = i;
            k = i;
            localObject2 = localObject1;
            n = localObject2[k];
            switch (m % 5)
            {
            default:
              i = 93;
            case 0:
            case 1:
            case 2:
            case 3:
            }
            while (true)
            {
              localObject2[k] = ((char)(i ^ n));
              m += 1;
              if (j != 0)
                break label573;
              k = j;
              break;
              i = 66;
              continue;
              i = 109;
              continue;
              i = 127;
              continue;
              i = 40;
            }
            k = j;
            localObject2 = localObject1;
            localObject1 = localObject2;
            j = k;
            i = m;
          }
          while (k > m);
          d = Pattern.compile(new String((char[])localObject2).intern() + c + ")");
          localObject1 = "\031\fRR\034o7O\005d\036F#\006\001\0351Ztp\036F\"Sln_J\036 \036-$Ip8,RrmoT\"s<o\027>\005\007r@Ftp\037\026O\004kv\020Wts\031\fRR\034o7O\005d\0376\036\005'\003@%\030p{1Ru&rAM\035 kF";
          i = 2;
          break label294;
          i = 66;
          continue;
          i = 109;
          continue;
          i = 127;
          continue;
          i = 40;
        }
        n = m;
        m = j;
        localObject2 = localObject1;
        i2 = i1;
        i1 = i2;
        localObject1 = localObject2;
        j = m;
        k = n;
      }
      while (m > n);
      label573: localObject1 = new String((char[])localObject2).intern();
      label662: switch (i2)
      {
      case 1:
      default:
        a = Pattern.compile((String)localObject1);
        localObject1 = "";
        i = 0;
        break;
      case 0:
        b = Pattern.compile((String)localObject1);
        localObject1 = "";
        i = 1;
        break;
      case 2:
        e = Pattern.compile((String)localObject1);
        localObject1 = "j1TsmoT\"\003\006\036@_ts\037GV\027u\036E$\030p{0Ttt\0311R\b\001l0U\001bj6O\005d\0376O\005d\036@_ts\0376O\005d\036@_ts\037F$\030p{0V";
        i = 3;
      case 3:
      }
    }
  }

  public static int a(Set<String> paramSet)
  {
    if ((paramSet == null) || (paramSet.isEmpty()))
      return 0;
    if (paramSet.size() > 100)
      return d.g;
    paramSet = paramSet.iterator();
    while (paramSet.hasNext())
    {
      String str = (String)paramSet.next();
      if (str == null)
        return d.e;
      if (str.getBytes().length > 40)
        return d.f;
      if (!Pattern.compile(z[0]).matcher(str).matches())
        return d.e;
    }
    return 0;
  }

  public static boolean a(String paramString)
  {
    if (paramString == null)
      return false;
    return Pattern.compile(z[1]).matcher(paramString).matches();
  }

  public static int b(String paramString)
  {
    if ((paramString == null) || (ai.a(paramString)));
    do
    {
      return 0;
      if (paramString.getBytes().length > 40)
        return d.d;
    }
    while (Pattern.compile(z[0]).matcher(paramString).matches());
    return d.c;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.util.z
 * JD-Core Version:    0.6.2
 */