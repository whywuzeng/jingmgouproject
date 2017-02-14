package cn.sharesdk.framework;

import cn.sharesdk.framework.statistics.a;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.Ln;
import java.util.HashMap;

class m extends Thread
{
  m(k paramk, a parama)
  {
  }

  public void run()
  {
    try
    {
      String str = this.a.g(k.a(this.b));
      HashMap localHashMap1 = new Hashon().fromJson(str);
      HashMap localHashMap2 = new HashMap();
      if (k.a(this.b, this.a, localHashMap1, localHashMap2))
        this.b.b(localHashMap2);
      this.a.a(k.a(this.b), str);
      return;
    }
    catch (Throwable localThrowable)
    {
      Ln.e(localThrowable);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.m
 * JD-Core Version:    0.6.2
 */