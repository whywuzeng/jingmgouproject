package cn.sharesdk.framework;

import com.mob.tools.utils.Ln;

class h extends Thread
{
  h(f paramf, String[] paramArrayOfString)
  {
  }

  public void run()
  {
    try
    {
      f.a(this.b);
      f.b(this.b).doAuthorize(this.a);
      return;
    }
    catch (Throwable localThrowable)
    {
      Ln.e(localThrowable);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.h
 * JD-Core Version:    0.6.2
 */