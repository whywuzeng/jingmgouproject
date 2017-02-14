package com.umeng.update;

import android.content.Context;
import com.umeng.update.util.DeltaUpdate;
import org.json.JSONObject;
import u.upd.a;
import u.upd.b;
import u.upd.h;
import u.upd.n;

public class d extends h
{
  private final String a = d.class.getName();
  private final String b = "update";
  private JSONObject c = a(paramContext);

  public d(Context paramContext)
  {
    super(null);
  }

  private JSONObject a(Context paramContext)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("type", "update");
      localJSONObject.put("appkey", UpdateConfig.getAppkey(paramContext));
      localJSONObject.put("version_code", a.c(paramContext));
      localJSONObject.put("package", a.u(paramContext));
      localJSONObject.put("idmd5", n.b(a.f(paramContext)));
      localJSONObject.put("channel", UpdateConfig.getChannel(paramContext));
      localJSONObject.put("proto_ver", "1.4");
      localJSONObject.put("sdk_version", "2.6.0.1.20150312");
      localJSONObject.put("old_md5", DeltaUpdate.b(paramContext));
      if ((DeltaUpdate.a()) && (UpdateConfig.isDeltaUpdate()));
      for (boolean bool = true; ; bool = false)
      {
        localJSONObject.put("delta", bool);
        return localJSONObject;
      }
    }
    catch (Exception paramContext)
    {
      b.b(this.a, "exception in updateInternal", paramContext);
    }
    return null;
  }

  public String toGetUrl()
  {
    return this.baseUrl;
  }

  public JSONObject toJson()
  {
    return this.c;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.update.d
 * JD-Core Version:    0.6.2
 */