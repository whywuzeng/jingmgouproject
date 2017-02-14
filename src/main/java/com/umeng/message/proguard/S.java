package com.umeng.message.proguard;

public class S
{
  public static final S a = new S();
  private boolean b = false;

  public static S a()
  {
    return a;
  }

  public boolean b()
  {
    return this.b;
  }

  public void c()
  {
    this.b = true;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.S
 * JD-Core Version:    0.6.2
 */