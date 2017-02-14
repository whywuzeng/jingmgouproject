package cn.smssdk.contact;

import cn.smssdk.utils.Protocols;
import cn.smssdk.utils.b;
import java.util.ArrayList;

class e extends Thread
{
  e(d paramd)
  {
  }

  public void run()
  {
    try
    {
      d.a(this.a).b();
      ArrayList localArrayList = d.a(this.a).a(false);
      String str1 = d.a(this.a, localArrayList);
      String str2 = d.b(this.a).d();
      if ((str1 != null) && (!str1.equals(str2)))
        d.c(this.a).a(localArrayList);
      d.b(this.a).a(localArrayList);
      d.b(this.a).c(str1);
      return;
    }
    catch (Throwable localThrowable)
    {
      cn.smssdk.framework.utils.d.b(localThrowable);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.contact.e
 * JD-Core Version:    0.6.2
 */