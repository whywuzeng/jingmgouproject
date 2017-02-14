package com.umeng.message.proguard;

final class aM
{
  static final int a = 2048;
  final byte[] b = new byte[2048];
  int c;
  int d;
  aM e;
  aM f;

  public aM a()
  {
    if (this.e != this);
    for (aM localaM = this.e; ; localaM = null)
    {
      this.f.e = this.e;
      this.e.f = this.f;
      this.e = null;
      this.f = null;
      return localaM;
    }
  }

  public aM a(int paramInt)
  {
    int i = this.d - this.c - paramInt;
    if ((paramInt <= 0) || (i <= 0))
      throw new IllegalArgumentException();
    if (paramInt < i)
    {
      localaM = aN.a.a();
      System.arraycopy(this.b, this.c, localaM.b, localaM.c, paramInt);
      this.c += paramInt;
      localaM.d += paramInt;
      this.f.a(localaM);
      return localaM;
    }
    aM localaM = aN.a.a();
    System.arraycopy(this.b, this.c + paramInt, localaM.b, localaM.c, i);
    this.d -= i;
    localaM.d = (i + localaM.d);
    a(localaM);
    return this;
  }

  public aM a(aM paramaM)
  {
    paramaM.f = this;
    paramaM.e = this.e;
    this.e.f = paramaM;
    this.e = paramaM;
    return paramaM;
  }

  public void a(aM paramaM, int paramInt)
  {
    if (paramaM.d - paramaM.c + paramInt > 2048)
      throw new IllegalArgumentException();
    if (paramaM.d + paramInt > 2048)
    {
      System.arraycopy(paramaM.b, paramaM.c, paramaM.b, 0, paramaM.d - paramaM.c);
      paramaM.d -= paramaM.c;
      paramaM.c = 0;
    }
    System.arraycopy(this.b, this.c, paramaM.b, paramaM.d, paramInt);
    paramaM.d += paramInt;
    this.c += paramInt;
  }

  public void b()
  {
    if (this.f == this)
      throw new IllegalStateException();
    if (this.f.d - this.f.c + (this.d - this.c) > 2048)
      return;
    a(this.f, this.d - this.c);
    a();
    aN.a.a(this);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.aM
 * JD-Core Version:    0.6.2
 */