package cn.jpush.android.data;

public final class k
{
  private static final String[] z;
  private long a = 0L;
  private int b = 0;
  private int c = 0;
  private int d = 0;
  private String e = "";
  private long f = 0L;
  private long g = 0L;

  static
  {
    String[] arrayOfString = new String[7];
    int j = 0;
    Object localObject2 = "";
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
        i = 106;
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
        localObject2 = "|\020\030851T\020\t\0369]\021k";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "|\020\03085$I\0043W";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "|\020\03085\"U\0319\0345\r";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = "|\020\030853_\0018\036m";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i4] = localObject1;
        j = 6;
        localObject2 = "|\020\03085$B\0351\r5B+\"\003=UI";
        i = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i4] = localObject1;
        z = arrayOfString;
        return;
        i = 80;
        continue;
        i = 48;
        continue;
        i = 116;
        continue;
        i = 86;
      }
    }
  }

  public final long a()
  {
    return this.a;
  }

  public final void a(int paramInt)
  {
    this.b = paramInt;
  }

  public final void a(long paramLong)
  {
    this.a = paramLong;
  }

  public final void a(String paramString)
  {
    this.e = paramString;
  }

  public final int b()
  {
    return this.b;
  }

  public final void b(int paramInt)
  {
    this.c = paramInt;
  }

  public final void b(long paramLong)
  {
    this.g = paramLong;
  }

  public final int c()
  {
    return this.c;
  }

  public final void c(int paramInt)
  {
    this.d = paramInt;
  }

  public final void c(long paramLong)
  {
    this.f = paramLong;
  }

  public final String d()
  {
    return this.e;
  }

  public final long e()
  {
    return this.g;
  }

  public final long f()
  {
    return this.f;
  }

  public final String toString()
  {
    return z[4] + this.a + z[5] + this.b + z[3] + this.c + z[2] + this.d + z[0] + this.e + z[6] + this.f + z[1] + this.g + "]";
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.data.k
 * JD-Core Version:    0.6.2
 */