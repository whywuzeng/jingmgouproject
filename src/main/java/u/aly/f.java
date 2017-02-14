package u.aly;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.b;
import com.umeng.analytics.h;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class f
{
  private static final String a = ".imprint";
  private static final byte[] b = "pbl0".getBytes();
  private static f e;
  private w c;
  private ba d = null;
  private Context f;

  f(Context paramContext)
  {
    this.f = paramContext;
  }

  private int a(String paramString)
  {
    ba localba = this.d;
    if ((localba == null) || (!localba.f()))
      return -1;
    paramString = (bb)localba.d().get(paramString);
    if ((paramString == null) || (TextUtils.isEmpty(paramString.c())))
      return -1;
    try
    {
      int i = Integer.parseInt(paramString.c().trim());
      return i;
    }
    catch (Exception paramString)
    {
    }
    return -1;
  }

  private ba a(ba paramba1, ba paramba2)
  {
    if (paramba2 == null)
      return paramba1;
    Map localMap = paramba1.d();
    Iterator localIterator = paramba2.d().entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (((bb)localEntry.getValue()).e())
        localMap.put(localEntry.getKey(), localEntry.getValue());
      else
        localMap.remove(localEntry.getKey());
    }
    paramba1.a(paramba2.h());
    paramba1.a(a(paramba1));
    return paramba1;
  }

  public static f a(Context paramContext)
  {
    try
    {
      if (e == null)
      {
        e = new f(paramContext);
        e.b();
      }
      paramContext = e;
      return paramContext;
    }
    finally
    {
    }
    throw paramContext;
  }

  private boolean c(ba paramba)
  {
    if (!paramba.k().equals(a(paramba)))
      return false;
    paramba = paramba.d().values().iterator();
    while (paramba.hasNext())
    {
      Object localObject = (bb)paramba.next();
      byte[] arrayOfByte = b.a(((bb)localObject).j());
      localObject = a((bb)localObject);
      int i = 0;
      while (i < 4)
      {
        if (arrayOfByte[i] != localObject[i])
          return false;
        i += 1;
      }
    }
    return true;
  }

  private void e()
  {
    if (this.c == null);
    int i;
    do
    {
      return;
      i = a("defcon");
      if (i != -1)
      {
        this.c.a(i);
        h.a(this.f).a(i);
      }
      i = a("latent");
      if (i != -1)
      {
        i *= 1000;
        this.c.b(i);
        h.a(this.f).b(i);
      }
      i = a("codex");
    }
    while ((i != 0) && (i != 1) && (i != -1));
    this.c.c(i);
    h.a(this.f).c(i);
  }

  public String a(ba paramba)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = new TreeMap(paramba.d()).entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localStringBuilder.append((String)localEntry.getKey());
      localStringBuilder.append(((bb)localEntry.getValue()).c());
      localStringBuilder.append(((bb)localEntry.getValue()).f());
      localStringBuilder.append(((bb)localEntry.getValue()).j());
    }
    localStringBuilder.append(paramba.b);
    return cd.a(localStringBuilder.toString()).toLowerCase(Locale.US);
  }

  public ba a()
  {
    try
    {
      ba localba = this.d;
      return localba;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void a(w paramw)
  {
    this.c = paramw;
  }

  public byte[] a(bb parambb)
  {
    Object localObject = ByteBuffer.allocate(8);
    ((ByteBuffer)localObject).order(null);
    ((ByteBuffer)localObject).putLong(parambb.f());
    parambb = ((ByteBuffer)localObject).array();
    localObject = b;
    byte[] arrayOfByte = new byte[4];
    int i = 0;
    while (i < 4)
    {
      arrayOfByte[i] = ((byte)(parambb[i] ^ localObject[i]));
      i += 1;
    }
    return arrayOfByte;
  }

  // ERROR //
  public void b()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore 4
    //   5: new 248	java/io/File
    //   8: dup
    //   9: aload_0
    //   10: getfield 39	u/aly/f:f	Landroid/content/Context;
    //   13: invokevirtual 254	android/content/Context:getFilesDir	()Ljava/io/File;
    //   16: ldc 8
    //   18: invokespecial 257	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   21: invokevirtual 260	java/io/File:exists	()Z
    //   24: ifne +4 -> 28
    //   27: return
    //   28: aload_0
    //   29: getfield 39	u/aly/f:f	Landroid/content/Context;
    //   32: ldc 8
    //   34: invokevirtual 264	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   37: astore_1
    //   38: aload_1
    //   39: astore_2
    //   40: aload_1
    //   41: invokestatic 267	u/aly/cd:b	(Ljava/io/InputStream;)[B
    //   44: astore_3
    //   45: aload_3
    //   46: astore_2
    //   47: aload_1
    //   48: invokestatic 270	u/aly/cd:c	(Ljava/io/InputStream;)V
    //   51: aload_2
    //   52: ifnull -25 -> 27
    //   55: new 44	u/aly/ba
    //   58: dup
    //   59: invokespecial 271	u/aly/ba:<init>	()V
    //   62: astore_1
    //   63: new 273	u/aly/ck
    //   66: dup
    //   67: invokespecial 274	u/aly/ck:<init>	()V
    //   70: aload_1
    //   71: aload_2
    //   72: invokevirtual 277	u/aly/ck:a	(Lu/aly/ch;[B)V
    //   75: aload_0
    //   76: aload_1
    //   77: putfield 37	u/aly/f:d	Lu/aly/ba;
    //   80: return
    //   81: astore_1
    //   82: aload_1
    //   83: invokevirtual 280	java/lang/Exception:printStackTrace	()V
    //   86: return
    //   87: astore_3
    //   88: aconst_null
    //   89: astore_1
    //   90: aload_1
    //   91: astore_2
    //   92: aload_3
    //   93: invokevirtual 280	java/lang/Exception:printStackTrace	()V
    //   96: aload_1
    //   97: invokestatic 270	u/aly/cd:c	(Ljava/io/InputStream;)V
    //   100: aload 4
    //   102: astore_2
    //   103: goto -52 -> 51
    //   106: astore_1
    //   107: aload_2
    //   108: invokestatic 270	u/aly/cd:c	(Ljava/io/InputStream;)V
    //   111: aload_1
    //   112: athrow
    //   113: astore_1
    //   114: goto -7 -> 107
    //   117: astore_3
    //   118: goto -28 -> 90
    //
    // Exception table:
    //   from	to	target	type
    //   55	80	81	java/lang/Exception
    //   28	38	87	java/lang/Exception
    //   28	38	106	finally
    //   40	45	113	finally
    //   92	96	113	finally
    //   40	45	117	java/lang/Exception
  }

  public void b(ba paramba)
  {
    if (paramba == null);
    while (true)
    {
      return;
      if (c(paramba))
        try
        {
          ba localba = this.d;
          if (localba == null);
          while (true)
          {
            this.d = paramba;
            if (this.d == null)
              break;
            e();
            return;
            paramba = a(localba, paramba);
          }
        }
        finally
        {
        }
    }
    throw paramba;
  }

  public void c()
  {
    if (this.d == null)
      return;
    try
    {
      byte[] arrayOfByte = new cq().a(this.d);
      cd.a(new File(this.f.getFilesDir(), ".imprint"), arrayOfByte);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public boolean d()
  {
    return new File(this.f.getFilesDir(), ".imprint").delete();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.f
 * JD-Core Version:    0.6.2
 */