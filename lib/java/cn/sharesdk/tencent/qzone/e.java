package cn.sharesdk.tencent.qzone;

import com.mob.tools.utils.Ln;

class e extends Thread
{
  e(d paramd, String paramString)
  {
  }

  public void run()
  {
    try
    {
      this.b.a(this.a);
      return;
    }
    catch (Throwable localThrowable)
    {
      Ln.e(localThrowable);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.tencent.qzone.e
 * JD-Core Version:    0.6.2
 */