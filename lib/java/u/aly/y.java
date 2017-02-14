package u.aly;

import android.content.Context;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.h;
import com.umeng.analytics.h.a;
import com.umeng.analytics.h.b;
import java.io.File;

public class y
{
  private static final int a = 1;
  private static final int b = 2;
  private static final int c = 3;
  private d d;
  private f e;
  private final int f = 1;
  private Context g;
  private aa h;
  private t i;
  private bn j;
  private boolean k = false;
  private boolean l;

  public y(Context paramContext, aa paramaa)
  {
    this.d = d.a(paramContext);
    this.e = f.a(paramContext);
    this.g = paramContext;
    this.h = paramaa;
    this.i = new t(paramContext);
    this.i.a(this.h);
  }

  private int a(byte[] paramArrayOfByte)
  {
    bj localbj = new bj();
    ck localck = new ck(new cz.a());
    try
    {
      localck.a(localbj, paramArrayOfByte);
      if (localbj.a == 1)
      {
        this.e.b(localbj.j());
        this.e.c();
      }
      br.a("MobclickAgent", "send log:" + localbj.f());
      if (localbj.a == 1)
        return 2;
    }
    catch (Exception paramArrayOfByte)
    {
      while (true)
        paramArrayOfByte.printStackTrace();
    }
    return 3;
  }

  private void b()
  {
    h.a(this.g).i().a(new h.b()
    {
      public void a(File paramAnonymousFile)
      {
      }

      // ERROR //
      public boolean b(File paramAnonymousFile)
      {
        // Byte code:
        //   0: new 26	java/io/FileInputStream
        //   3: dup
        //   4: aload_1
        //   5: invokespecial 28	java/io/FileInputStream:<init>	(Ljava/io/File;)V
        //   8: astore_3
        //   9: aload_3
        //   10: invokestatic 33	u/aly/cd:b	(Ljava/io/InputStream;)[B
        //   13: astore_1
        //   14: aload_3
        //   15: invokestatic 37	u/aly/cd:c	(Ljava/io/InputStream;)V
        //   18: aload_0
        //   19: getfield 17	u/aly/y$1:a	Lu/aly/y;
        //   22: invokestatic 40	u/aly/y:a	(Lu/aly/y;)Lu/aly/t;
        //   25: aload_1
        //   26: invokevirtual 45	u/aly/t:a	([B)[B
        //   29: astore_1
        //   30: aload_1
        //   31: ifnonnull +51 -> 82
        //   34: iconst_1
        //   35: istore_2
        //   36: iload_2
        //   37: iconst_2
        //   38: if_icmpne +26 -> 64
        //   41: aload_0
        //   42: getfield 17	u/aly/y$1:a	Lu/aly/y;
        //   45: invokestatic 48	u/aly/y:b	(Lu/aly/y;)Lu/aly/aa;
        //   48: invokevirtual 54	u/aly/aa:m	()Z
        //   51: ifeq +13 -> 64
        //   54: aload_0
        //   55: getfield 17	u/aly/y$1:a	Lu/aly/y;
        //   58: invokestatic 48	u/aly/y:b	(Lu/aly/y;)Lu/aly/aa;
        //   61: invokevirtual 57	u/aly/aa:l	()V
        //   64: aload_0
        //   65: getfield 17	u/aly/y$1:a	Lu/aly/y;
        //   68: invokestatic 60	u/aly/y:c	(Lu/aly/y;)Z
        //   71: ifeq +23 -> 94
        //   74: iconst_1
        //   75: ireturn
        //   76: aload_3
        //   77: invokestatic 37	u/aly/cd:c	(Ljava/io/InputStream;)V
        //   80: aload_1
        //   81: athrow
        //   82: aload_0
        //   83: getfield 17	u/aly/y$1:a	Lu/aly/y;
        //   86: aload_1
        //   87: invokestatic 63	u/aly/y:a	(Lu/aly/y;[B)I
        //   90: istore_2
        //   91: goto -55 -> 36
        //   94: iload_2
        //   95: iconst_1
        //   96: if_icmpne +9 -> 105
        //   99: iconst_0
        //   100: ireturn
        //   101: astore_1
        //   102: goto -26 -> 76
        //   105: iconst_1
        //   106: ireturn
        //   107: astore_1
        //   108: aconst_null
        //   109: astore_3
        //   110: goto -34 -> 76
        //   113: astore_1
        //   114: iconst_0
        //   115: ireturn
        //
        // Exception table:
        //   from	to	target	type
        //   9	14	101	finally
        //   0	9	107	finally
        //   14	30	113	java/lang/Exception
        //   41	64	113	java/lang/Exception
        //   64	74	113	java/lang/Exception
        //   76	82	113	java/lang/Exception
        //   82	91	113	java/lang/Exception
      }

      public void c(File paramAnonymousFile)
      {
        y.b(y.this).k();
      }
    });
  }

  private byte[] b(bn parambn)
  {
    if (parambn == null)
      return null;
    try
    {
      byte[] arrayOfByte = new cq().a(parambn);
      if (br.a)
        br.c("MobclickAgent", parambn.toString());
      return arrayOfByte;
    }
    catch (Exception parambn)
    {
      br.b("MobclickAgent", "Fail to serialize log ...", parambn);
    }
    return null;
  }

  private void c()
  {
    this.d.a();
    Object localObject = this.j;
    ((bn)localObject).a(this.d.b());
    localObject = b((bn)localObject);
    if (localObject == null)
    {
      br.e("MobclickAgent", "message is null");
      return;
    }
    label65: byte[] arrayOfByte;
    if (!this.k)
    {
      localObject = c.a(this.g, AnalyticsConfig.getAppkey(this.g), (byte[])localObject);
      localObject = ((c)localObject).c();
      h.a(this.g).g();
      arrayOfByte = this.i.a((byte[])localObject);
      if (arrayOfByte != null)
        break label170;
    }
    label170: for (int m = 1; ; m = a(arrayOfByte))
      switch (m)
      {
      default:
        return;
      case 1:
        if (!this.l)
          h.a(this.g).b((byte[])localObject);
        br.b("MobclickAgent", "connection error");
        return;
        localObject = c.b(this.g, AnalyticsConfig.getAppkey(this.g), (byte[])localObject);
        break label65;
      case 2:
      case 3:
      }
    if (this.h.m())
      this.h.l();
    this.d.d();
    this.h.k();
    return;
    this.h.k();
  }

  public void a()
  {
    if (this.j != null)
    {
      c();
      return;
    }
    b();
  }

  public void a(bn parambn)
  {
    this.j = parambn;
  }

  public void a(w paramw)
  {
    this.e.a(paramw);
  }

  public void a(boolean paramBoolean)
  {
    this.k = paramBoolean;
  }

  public void b(boolean paramBoolean)
  {
    this.l = paramBoolean;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.y
 * JD-Core Version:    0.6.2
 */