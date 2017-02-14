package com.umeng.message.proguard;

final class aN
{
  static final aN a = new aN();
  static final long b = 65536L;
  long c;
  private aM d;

  aM a()
  {
    try
    {
      if (this.d != null)
      {
        aM localaM = this.d;
        this.d = localaM.e;
        localaM.e = null;
        this.c -= 2048L;
        return localaM;
      }
      return new aM();
    }
    finally
    {
    }
  }

  void a(aM paramaM)
  {
    if ((paramaM.e != null) || (paramaM.f != null))
      throw new IllegalArgumentException();
    try
    {
      if (this.c + 2048L > 65536L)
        return;
      this.c += 2048L;
      paramaM.e = this.d;
      paramaM.d = 0;
      paramaM.c = 0;
      this.d = paramaM;
      return;
    }
    finally
    {
    }
    throw paramaM;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.aN
 * JD-Core Version:    0.6.2
 */