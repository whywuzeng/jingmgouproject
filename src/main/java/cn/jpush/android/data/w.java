package cn.jpush.android.data;

public final class w
{
  private static final String[] z;
  private String a = "";
  private String b = "";
  private String c = "";
  private String d = "";
  private String e = "";
  private int f = 0;
  private int g = 0;
  private int h = 0;
  private int i = 0;
  private int j = 0;
  private int k = 0;

  static
  {
    String[] arrayOfString = new String[11];
    int n = 0;
    Object localObject2 = "m\017\005&=93Y\fb}Q";
    int m = -1;
    Object localObject1 = arrayOfString;
    char[] arrayOfChar = ((String)localObject2).toCharArray();
    int i1 = arrayOfChar.length;
    int i4 = 0;
    int i2 = 0;
    int i6 = m;
    localObject2 = arrayOfChar;
    int i7 = n;
    Object localObject3 = localObject1;
    int i3 = i1;
    Object localObject4;
    int i5;
    if (i1 <= 1)
    {
      localObject4 = localObject1;
      localObject1 = arrayOfChar;
      i5 = m;
      label68: i3 = i2;
      label71: localObject2 = localObject1;
      i4 = localObject2[i2];
      switch (i3 % 5)
      {
      default:
        m = 83;
      case 0:
      case 1:
      case 2:
      case 3:
      }
    }
    while (true)
    {
      localObject2[i2] = ((char)(m ^ i4));
      i3 += 1;
      if (i1 == 0)
      {
        i2 = i1;
        break label71;
      }
      i4 = i3;
      i3 = i1;
      localObject3 = localObject4;
      i7 = n;
      localObject2 = localObject1;
      i6 = i5;
      i5 = i6;
      localObject1 = localObject2;
      n = i7;
      localObject4 = localObject3;
      i1 = i3;
      i2 = i4;
      if (i3 > i4)
        break label68;
      localObject1 = new String((char[])localObject2).intern();
      switch (i6)
      {
      default:
        localObject3[i7] = localObject1;
        n = 1;
        localObject2 = "m\030\005'2!Q";
        m = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i7] = localObject1;
        n = 2;
        localObject2 = "m\037\036\f=(\030W";
        m = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i7] = localObject1;
        n = 3;
        localObject2 = "m\037\036\f \"\031\03006p";
        m = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i7] = localObject1;
        n = 4;
        localObject2 = "m\017\005&=93[cn";
        m = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i7] = localObject1;
        n = 5;
        localObject2 = "m\017\005&=93[n";
        m = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i7] = localObject1;
        n = 6;
        localObject2 = "";
        m = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i7] = localObject1;
        n = 7;
        localObject2 = "m\037\036\f0\"\002\004\f:=Q";
        m = 6;
        localObject1 = arrayOfString;
        break;
      case 6:
        localObject3[i7] = localObject1;
        n = 8;
        localObject2 = "m\017\005&=93[\f`p";
        m = 7;
        localObject1 = arrayOfString;
        break;
      case 7:
        localObject3[i7] = localObject1;
        n = 9;
        localObject2 = "m\n\013:?(\bW";
        m = 8;
        localObject1 = arrayOfString;
        break;
      case 8:
        localObject3[i7] = localObject1;
        n = 10;
        localObject2 = "m\037\036\f?\"\017\013?\f)\002\031n";
        m = 9;
        localObject1 = arrayOfString;
        break;
      case 9:
        localObject3[i7] = localObject1;
        z = arrayOfString;
        return;
        m = 83;
        continue;
        m = 77;
        continue;
        m = 108;
        continue;
        m = 106;
      }
    }
  }

  public final String a()
  {
    return this.a;
  }

  public final void a(int paramInt)
  {
    this.f = paramInt;
  }

  public final void a(String paramString)
  {
    this.a = paramString;
  }

  public final String b()
  {
    return this.b;
  }

  public final void b(int paramInt)
  {
    this.g = paramInt;
  }

  public final void b(String paramString)
  {
    this.b = paramString;
  }

  public final String c()
  {
    return this.c;
  }

  public final void c(int paramInt)
  {
    this.h = paramInt;
  }

  public final void c(String paramString)
  {
    this.c = paramString;
  }

  public final String d()
  {
    return this.d;
  }

  public final void d(int paramInt)
  {
    this.i = paramInt;
  }

  public final void d(String paramString)
  {
    this.d = paramString;
  }

  public final String e()
  {
    return this.e;
  }

  public final void e(int paramInt)
  {
    this.j = paramInt;
  }

  public final void e(String paramString)
  {
    this.e = paramString;
  }

  public final int f()
  {
    return this.f;
  }

  public final void f(int paramInt)
  {
    this.k = paramInt;
  }

  public final int g()
  {
    return this.g;
  }

  public final int h()
  {
    return this.h;
  }

  public final int i()
  {
    return this.i;
  }

  public final int j()
  {
    return this.j;
  }

  public final int k()
  {
    return this.k;
  }

  public final String toString()
  {
    return z[6] + this.a + z[2] + this.b + z[7] + this.c + z[10] + this.d + z[3] + this.e + z[9] + this.f + z[1] + this.g + z[5] + this.h + z[8] + this.i + z[0] + this.j + z[4] + this.k + "]";
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.data.w
 * JD-Core Version:    0.6.2
 */