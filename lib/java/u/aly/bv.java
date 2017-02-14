package u.aly;

import org.json.JSONObject;

public class bv extends by
{
  public a a;

  public bv(JSONObject paramJSONObject)
  {
    super(paramJSONObject);
    if (("ok".equalsIgnoreCase(paramJSONObject.optString("status"))) || ("ok".equalsIgnoreCase(paramJSONObject.optString("success"))))
    {
      this.a = a.a;
      return;
    }
    this.a = a.b;
  }

  public static enum a
  {
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.bv
 * JD-Core Version:    0.6.2
 */