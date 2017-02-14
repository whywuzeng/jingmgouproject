package com.umeng.message.proguard;

import java.net.URLEncoder;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import org.json.JSONObject;

public class P
{
  private static Random b = new Random();
  private static P e = null;
  private boolean a = false;
  private boolean c = false;
  private Object d = new Object();

  public static P a()
  {
    try
    {
      if (e == null)
        e = new P();
      P localP = e;
      return localP;
    }
    finally
    {
    }
  }

  public void a(String paramString)
  {
    synchronized (this.d)
    {
      if (this.c)
        return;
      if ((paramString != null) && (!this.a) && (1 == b.nextInt(100)))
      {
        this.a = true;
        new a(paramString).start();
        return;
      }
    }
  }

  private class a extends Thread
  {
    private String b = null;

    public a(String arg2)
    {
      Object localObject;
      this.b = localObject;
    }

    public void run()
    {
      synchronized (P.a(P.this))
      {
        if (P.b(P.this))
          return;
        P.a(P.this, true);
      }
      try
      {
        Object localObject3 = Z.c("http://adash.m.taobao.com/rest/abtest");
        ??? = URLEncoder.encode(UUID.randomUUID().toString(), "UTF-8");
        localObject3 = String.format("%s&logid=%s", new Object[] { localObject3, ??? });
        y.b(2, "request[abtest]", localObject3);
        localObject3 = aj.a(1, (String)localObject3, null, false);
        if ((localObject3 != null) && (localObject3.length > 0))
        {
          localObject3 = new String((byte[])localObject3, "UTF-8");
          y.b(2, "result[abtest]", localObject3);
          if (ah.a((String)localObject3))
          {
            long l = new JSONObject((String)localObject3).getLong("t");
            localObject3 = Q.a(this.b);
            if ((localObject3 != null) && (((Map)localObject3).size() > 0) && (((Map)localObject3).containsKey(v.H.toString())))
              ((Map)localObject3).remove(v.H.toString());
            ((Map)localObject3).put(v.B.toString(), "" + l);
            ((Map)localObject3).put(v.C.toString(), "ABTest");
            ((Map)localObject3).put(v.D.toString(), "6677");
            ((Map)localObject3).put(v.E.toString(), "" + (String)???);
            ((Map)localObject3).put(v.F.toString(), "-");
            ((Map)localObject3).put(v.G.toString(), "-");
            ((Map)localObject3).put(v.H.toString(), "-");
            L.a().a((Map)localObject3);
          }
        }
        return;
        localObject4 = finally;
        throw localObject4;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        return;
      }
      finally
      {
        P.a(P.this, false);
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.P
 * JD-Core Version:    0.6.2
 */