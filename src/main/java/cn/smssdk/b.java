package cn.smssdk;

import java.util.HashSet;
import java.util.Iterator;

class b extends Thread
{
  b(a parama, int paramInt, Object paramObject)
  {
  }

  public void run()
  {
    synchronized (a.a(this.c))
    {
      Iterator localIterator = a.a(this.c).iterator();
      if (localIterator.hasNext())
        ((EventHandler)localIterator.next()).beforeEvent(this.a, this.b);
    }
    a.a(this.c, this.a, this.b);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.b
 * JD-Core Version:    0.6.2
 */