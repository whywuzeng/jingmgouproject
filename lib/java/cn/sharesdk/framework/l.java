package cn.sharesdk.framework;

import com.mob.tools.utils.Ln;
import java.util.HashMap;

class l extends Thread
{
  l(k paramk)
  {
  }

  public void run()
  {
    try
    {
      HashMap localHashMap = new HashMap();
      if ((!this.a.f()) && (this.a.a(localHashMap)))
        this.a.b(localHashMap);
      return;
    }
    catch (Throwable localThrowable)
    {
      Ln.e(localThrowable);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.l
 * JD-Core Version:    0.6.2
 */