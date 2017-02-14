package cn.sharesdk.framework;

import com.mob.tools.utils.Ln;

class g extends Thread
{
  g(f paramf, int paramInt, Object paramObject)
  {
  }

  public void run()
  {
    try
    {
      f.a(this.c);
      if (f.b(this.c).checkAuthorize(this.a, this.b))
        this.c.b(this.a, this.b);
      return;
    }
    catch (Throwable localThrowable)
    {
      Ln.e(localThrowable);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.g
 * JD-Core Version:    0.6.2
 */