package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class d
{
  public static d a;
  private final String b = "umeng_it.cache";
  private File c;
  private az d = null;
  private long e;
  private long f;
  private Set<a> g = new HashSet();
  private a h = null;

  d(Context paramContext)
  {
    this.c = new File(paramContext.getFilesDir(), "umeng_it.cache");
    this.f = 86400000L;
    this.h = new a(paramContext);
    this.h.b();
  }

  public static d a(Context paramContext)
  {
    try
    {
      if (a == null)
      {
        a = new d(paramContext);
        a.a(new e(paramContext));
        a.a(new g(paramContext));
        a.a(new b(paramContext));
        a.a(new j(paramContext));
        a.a(new i(paramContext));
        a.a(new h());
        a.e();
      }
      paramContext = a;
      return paramContext;
    }
    finally
    {
    }
    throw paramContext;
  }

  private void a(az paramaz)
  {
    if (paramaz != null);
    try
    {
      try
      {
        paramaz = new cq().a(paramaz);
        if (paramaz != null)
          cd.a(this.c, paramaz);
        return;
      }
      finally
      {
      }
    }
    catch (Exception paramaz)
    {
      paramaz.printStackTrace();
    }
  }

  private void g()
  {
    az localaz = new az();
    HashMap localHashMap = new HashMap();
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.g.iterator();
    while (localIterator.hasNext())
    {
      a locala = (a)localIterator.next();
      if (locala.c())
      {
        if (locala.d() != null)
          localHashMap.put(locala.b(), locala.d());
        if ((locala.e() != null) && (!locala.e().isEmpty()))
          localArrayList.addAll(locala.e());
      }
    }
    localaz.a(localArrayList);
    localaz.a(localHashMap);
    try
    {
      this.d = localaz;
      return;
    }
    finally
    {
    }
  }

  // ERROR //
  private az h()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 53	u/aly/d:c	Ljava/io/File;
    //   4: invokevirtual 169	java/io/File:exists	()Z
    //   7: ifne +5 -> 12
    //   10: aconst_null
    //   11: areturn
    //   12: new 171	java/io/FileInputStream
    //   15: dup
    //   16: aload_0
    //   17: getfield 53	u/aly/d:c	Ljava/io/File;
    //   20: invokespecial 174	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   23: astore_2
    //   24: aload_2
    //   25: astore_1
    //   26: aload_2
    //   27: invokestatic 177	u/aly/cd:b	(Ljava/io/InputStream;)[B
    //   30: astore_3
    //   31: aload_2
    //   32: astore_1
    //   33: new 108	u/aly/az
    //   36: dup
    //   37: invokespecial 109	u/aly/az:<init>	()V
    //   40: astore 4
    //   42: aload_2
    //   43: astore_1
    //   44: new 179	u/aly/ck
    //   47: dup
    //   48: invokespecial 180	u/aly/ck:<init>	()V
    //   51: aload 4
    //   53: aload_3
    //   54: invokevirtual 183	u/aly/ck:a	(Lu/aly/ch;[B)V
    //   57: aload_2
    //   58: invokestatic 186	u/aly/cd:c	(Ljava/io/InputStream;)V
    //   61: aload 4
    //   63: areturn
    //   64: astore_3
    //   65: aconst_null
    //   66: astore_2
    //   67: aload_2
    //   68: astore_1
    //   69: aload_3
    //   70: invokevirtual 106	java/lang/Exception:printStackTrace	()V
    //   73: aload_2
    //   74: invokestatic 186	u/aly/cd:c	(Ljava/io/InputStream;)V
    //   77: aconst_null
    //   78: areturn
    //   79: astore_1
    //   80: aconst_null
    //   81: astore_3
    //   82: aload_1
    //   83: astore_2
    //   84: aload_3
    //   85: invokestatic 186	u/aly/cd:c	(Ljava/io/InputStream;)V
    //   88: aload_2
    //   89: athrow
    //   90: astore_2
    //   91: aload_1
    //   92: astore_3
    //   93: goto -9 -> 84
    //   96: astore_3
    //   97: goto -30 -> 67
    //
    // Exception table:
    //   from	to	target	type
    //   12	24	64	java/lang/Exception
    //   12	24	79	finally
    //   26	31	90	finally
    //   33	42	90	finally
    //   44	57	90	finally
    //   69	73	90	finally
    //   26	31	96	java/lang/Exception
    //   33	42	96	java/lang/Exception
    //   44	57	96	java/lang/Exception
  }

  public void a()
  {
    long l = System.currentTimeMillis();
    if (l - this.e >= this.f)
    {
      Iterator localIterator = this.g.iterator();
      int i = 0;
      while (localIterator.hasNext())
      {
        a locala = (a)localIterator.next();
        if (locala.c())
          if (locala.a())
          {
            int j = 1;
            i = j;
            if (!locala.c())
            {
              this.h.b(locala.b());
              i = j;
            }
          }
      }
      if (i != 0)
      {
        g();
        this.h.a();
        f();
      }
      this.e = l;
    }
  }

  public void a(long paramLong)
  {
    this.f = paramLong;
  }

  public boolean a(a parama)
  {
    if (this.h.a(parama.b()))
      return this.g.add(parama);
    return false;
  }

  public az b()
  {
    return this.d;
  }

  public String c()
  {
    return null;
  }

  public void d()
  {
    Iterator localIterator = this.g.iterator();
    int i = 0;
    while (localIterator.hasNext())
    {
      a locala = (a)localIterator.next();
      if (locala.c())
      {
        if ((locala.e() == null) || (locala.e().isEmpty()))
          break label84;
        locala.a(null);
        i = 1;
      }
    }
    label84: 
    while (true)
    {
      break;
      if (i != 0)
      {
        this.d.b(false);
        f();
      }
      return;
    }
  }

  public void e()
  {
    Object localObject2 = h();
    if (localObject2 == null)
      return;
    ArrayList localArrayList = new ArrayList(this.g.size());
    try
    {
      this.d = ((az)localObject2);
      localObject2 = this.g.iterator();
      while (((Iterator)localObject2).hasNext())
      {
        a locala = (a)((Iterator)localObject2).next();
        locala.a(this.d);
        if (!locala.c())
          localArrayList.add(locala);
      }
    }
    finally
    {
    }
    Iterator localIterator = localObject1.iterator();
    while (localIterator.hasNext())
    {
      localObject2 = (a)localIterator.next();
      this.g.remove(localObject2);
    }
    g();
  }

  public void f()
  {
    if (this.d != null)
      a(this.d);
  }

  public static class a
  {
    private Context a;
    private Set<String> b = new HashSet();

    public a(Context paramContext)
    {
      this.a = paramContext;
    }

    public void a()
    {
      if (!this.b.isEmpty())
      {
        StringBuilder localStringBuilder = new StringBuilder();
        Iterator localIterator = this.b.iterator();
        while (localIterator.hasNext())
        {
          localStringBuilder.append((String)localIterator.next());
          localStringBuilder.append(',');
        }
        localStringBuilder.deleteCharAt(localStringBuilder.length() - 1);
        x.a(this.a).edit().putString("invld_id", localStringBuilder.toString()).commit();
      }
    }

    public boolean a(String paramString)
    {
      return !this.b.contains(paramString);
    }

    public void b()
    {
      Object localObject = x.a(this.a).getString("invld_id", null);
      if (!TextUtils.isEmpty((CharSequence)localObject))
      {
        localObject = ((String)localObject).split(",");
        if (localObject != null)
        {
          int j = localObject.length;
          int i = 0;
          while (i < j)
          {
            CharSequence localCharSequence = localObject[i];
            if (!TextUtils.isEmpty(localCharSequence))
              this.b.add(localCharSequence);
            i += 1;
          }
        }
      }
    }

    public void b(String paramString)
    {
      this.b.add(paramString);
    }

    public void c(String paramString)
    {
      this.b.remove(paramString);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.d
 * JD-Core Version:    0.6.2
 */