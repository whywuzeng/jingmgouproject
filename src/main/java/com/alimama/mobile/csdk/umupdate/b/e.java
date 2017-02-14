package com.alimama.mobile.csdk.umupdate.b;

import com.alimama.mobile.csdk.umupdate.a.j;
import java.util.Map;
import org.json.JSONObject;
import u.upd.h;

public class e extends h
{
  private Map<String, Object> a;

  public e(Map<String, Object> paramMap)
  {
    super("");
    this.a = paramMap;
  }

  public String getHttpMethod()
  {
    return h.GET;
  }

  public void setBaseUrl(String paramString)
  {
    this.baseUrl = paramString;
  }

  public String toGetUrl()
  {
    return j.a(this.baseUrl, this.a).toString();
  }

  public JSONObject toJson()
  {
    return null;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alimama.mobile.csdk.umupdate.b.e
 * JD-Core Version:    0.6.2
 */