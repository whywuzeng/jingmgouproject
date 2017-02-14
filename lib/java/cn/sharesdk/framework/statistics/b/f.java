package cn.sharesdk.framework.statistics.b;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Base64;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.Ln;
import java.util.ArrayList;
import java.util.HashMap;

public class f extends c
{
  private static int p;
  private static long q;
  public int a;
  public String b;
  public String c;
  public a d = new a();
  public String n;
  public String[] o;

  protected String a()
  {
    return "[SHR]";
  }

  protected void a(long paramLong)
  {
    q = paramLong;
  }

  protected int b()
  {
    return 5000;
  }

  protected int c()
  {
    return 30;
  }

  protected long d()
  {
    return p;
  }

  protected long e()
  {
    return q;
  }

  protected void f()
  {
    p += 1;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(super.toString());
    localStringBuilder.append('|').append(this.a);
    localStringBuilder.append('|').append(this.b);
    Object localObject2 = localStringBuilder.append('|');
    Object localObject1;
    if (TextUtils.isEmpty(this.c))
      localObject1 = "";
    while (true)
    {
      ((StringBuilder)localObject2).append((String)localObject1);
      localObject2 = "";
      localObject1 = localObject2;
      if (this.o != null)
      {
        localObject1 = localObject2;
        if (this.o.length > 0)
          localObject1 = "[\"" + TextUtils.join("\",\"", this.o) + "\"]";
      }
      localStringBuilder.append('|').append((String)localObject1);
      localStringBuilder.append('|');
      if (this.d != null)
        localObject1 = this.d.toString();
      try
      {
        localObject2 = Base64.encodeToString(Data.AES128Encode(this.f.substring(0, 16), (String)localObject1), 0);
        localObject1 = localObject2;
        if (((String)localObject2).contains("\n"))
          localObject1 = ((String)localObject2).replace("\n", "");
        localStringBuilder.append((String)localObject1);
        localStringBuilder.append('|');
        if (!TextUtils.isEmpty(this.m))
          localStringBuilder.append(this.m);
        localStringBuilder.append('|');
        if (TextUtils.isEmpty(this.n));
      }
      catch (Throwable localThrowable1)
      {
        try
        {
          localObject2 = Base64.encodeToString(Data.AES128Encode(this.f.substring(0, 16), this.n), 0);
          localObject1 = localObject2;
          if (!TextUtils.isEmpty((CharSequence)localObject2))
          {
            localObject1 = localObject2;
            if (((String)localObject2).contains("\n"))
              localObject1 = ((String)localObject2).replace("\n", "");
          }
          localStringBuilder.append((String)localObject1);
          return localStringBuilder.toString();
          localObject1 = this.c;
          continue;
          localThrowable1 = localThrowable1;
          Ln.e(localThrowable1);
        }
        catch (Throwable localThrowable2)
        {
          while (true)
            Ln.e(localThrowable2);
        }
      }
    }
  }

  public static class a
  {
    public String a = "";
    public String b;
    public ArrayList<String> c = new ArrayList();
    public ArrayList<String> d = new ArrayList();
    public ArrayList<String> e = new ArrayList();
    public ArrayList<Bitmap> f = new ArrayList();
    public HashMap<String, Object> g;

    public String toString()
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("text", this.b);
      localHashMap.put("url", this.c);
      if ((this.d != null) && (this.d.size() > 0))
        localHashMap.put("imgs", this.d);
      if (this.g != null)
        localHashMap.put("attch", new Hashon().fromHashMap(this.g));
      return new Hashon().fromHashMap(localHashMap);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.statistics.b.f
 * JD-Core Version:    0.6.2
 */