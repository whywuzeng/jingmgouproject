package u.aly;

import org.json.JSONArray;

public class ai extends bg
{
  public ai()
  {
  }

  public ai(String paramString)
    throws Exception
  {
    a(new JSONArray(paramString));
  }

  public ai(JSONArray paramJSONArray)
    throws Exception
  {
    a(paramJSONArray);
  }

  private void a(JSONArray paramJSONArray)
    throws Exception
  {
    a(paramJSONArray.getString(0));
    a(paramJSONArray.getInt(1));
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.ai
 * JD-Core Version:    0.6.2
 */