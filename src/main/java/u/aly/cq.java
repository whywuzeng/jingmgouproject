package u.aly;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

public class cq
{
  private final ByteArrayOutputStream a = new ByteArrayOutputStream();
  private final ds b = new ds(this.a);
  private dg c;

  public cq()
  {
    this(new da.a());
  }

  public cq(di paramdi)
  {
    this.c = paramdi.a(this.b);
  }

  public String a(ch paramch, String paramString)
    throws cn
  {
    try
    {
      paramch = new String(a(paramch), paramString);
      return paramch;
    }
    catch (UnsupportedEncodingException paramch)
    {
    }
    throw new cn("JVM DOES NOT SUPPORT ENCODING: " + paramString);
  }

  public byte[] a(ch paramch)
    throws cn
  {
    this.a.reset();
    paramch.b(this.c);
    return this.a.toByteArray();
  }

  public String b(ch paramch)
    throws cn
  {
    return new String(a(paramch));
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.cq
 * JD-Core Version:    0.6.2
 */