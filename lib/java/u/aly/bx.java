package u.aly;

import org.json.JSONObject;

public abstract class bx
{
  protected static String b = "POST";
  protected static String c = "GET";
  protected String d;

  public bx(String paramString)
  {
    this.d = paramString;
  }

  public abstract JSONObject a();

  public void a(String paramString)
  {
    this.d = paramString;
  }

  public abstract String b();

  protected String c()
  {
    return b;
  }

  public String d()
  {
    return this.d;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.bx
 * JD-Core Version:    0.6.2
 */