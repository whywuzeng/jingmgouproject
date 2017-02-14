package u.upd;

import org.json.JSONObject;

public abstract class h
{
  protected static String GET = "GET";
  protected static String POST = "POST";
  protected String baseUrl;

  public h(String paramString)
  {
    this.baseUrl = paramString;
  }

  public String getBaseUrl()
  {
    return this.baseUrl;
  }

  protected String getHttpMethod()
  {
    return POST;
  }

  public void setBaseUrl(String paramString)
  {
    this.baseUrl = paramString;
  }

  public abstract String toGetUrl();

  public abstract JSONObject toJson();
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.upd.h
 * JD-Core Version:    0.6.2
 */