package cn.jpush.android.service;

import android.database.Cursor;
import cn.jpush.android.data.k;
import cn.jpush.android.util.x;

final class i
  implements Runnable
{
  i(g paramg, long paramLong)
  {
  }

  public final void run()
  {
    if ((g.a() == null) && (g.a(this.b) != null))
      g.a(new cn.jpush.android.data.i(g.a(this.b)));
    Cursor localCursor = null;
    Object localObject2 = localCursor;
    try
    {
      g.a().a();
      localObject2 = localCursor;
      localCursor = g.a().a(this.a, 0);
      localObject2 = localCursor;
    }
    catch (Exception localException1)
    {
      while (true)
      {
        long l;
        if (localException1 != null)
          localException1.close();
        throw localObject3;
        Exception localException2 = localException1;
        g.a(this.b, g.a(this.b), g.b().d(), g.b(this.b), "");
        localException2 = localException1;
        g.a().b(g.b().a(), 0, 0, 0, g.b().d(), g.b().f(), g.b().e());
        continue;
        localException2 = localException1;
        x.c();
      }
    }
    finally
    {
      while (true)
        Object localObject1 = null;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.service.i
 * JD-Core Version:    0.6.2
 */