package com.umeng.update;

import android.content.Context;
import java.io.Serializable;
import org.json.JSONObject;
import u.upd.c;
import u.upd.i;
import u.upd.n;

public class UpdateResponse extends i
  implements Serializable
{
  private static final long a = -7768683594079202710L;
  public boolean delta = false;
  public boolean display_ads;
  public boolean hasUpdate = false;
  public String new_md5;
  public String origin;
  public String patch_md5;
  public String path;
  public String proto_ver;
  public String size;
  public String target_size;
  public String updateLog = null;
  public String version = null;

  public UpdateResponse(JSONObject paramJSONObject)
  {
    super(paramJSONObject);
    a(paramJSONObject);
  }

  private void a(JSONObject paramJSONObject)
  {
    try
    {
      this.hasUpdate = "Yes".equalsIgnoreCase(paramJSONObject.optString("update"));
      if (this.hasUpdate)
      {
        this.updateLog = paramJSONObject.getString("update_log");
        this.version = paramJSONObject.getString("version");
        this.path = paramJSONObject.getString("path");
        this.target_size = paramJSONObject.optString("target_size");
        this.new_md5 = paramJSONObject.optString("new_md5");
        this.delta = paramJSONObject.optBoolean("delta");
        this.display_ads = paramJSONObject.optBoolean("display_ads", false);
        if (this.delta)
        {
          this.patch_md5 = paramJSONObject.optString("patch_md5");
          this.size = paramJSONObject.optString("size");
          this.origin = paramJSONObject.optString("origin");
        }
      }
      return;
    }
    catch (Exception paramJSONObject)
    {
      paramJSONObject.printStackTrace();
    }
  }

  public String a(Context paramContext, boolean paramBoolean)
  {
    String str1 = paramContext.getString(c.a(paramContext).f("UMNewVersion"));
    String str2 = paramContext.getString(c.a(paramContext).f("UMTargetSize"));
    String str4 = paramContext.getString(c.a(paramContext).f("UMUpdateSize"));
    String str3 = paramContext.getString(c.a(paramContext).f("UMUpdateContent"));
    paramContext = paramContext.getString(c.a(paramContext).f("UMDialog_InstallAPK"));
    if (paramBoolean)
      return String.format("%s %s\n%s\n\n%s\n%s\n", new Object[] { str1, this.version, paramContext, str3, this.updateLog });
    if (this.delta);
    for (paramContext = String.format("\n%s %s", new Object[] { str4, n.c(this.size) }); ; paramContext = "")
      return String.format("%s %s\n%s %s%s\n\n%s\n%s\n", new Object[] { str1, this.version, str2, n.c(this.target_size), paramContext, str3, this.updateLog });
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.update.UpdateResponse
 * JD-Core Version:    0.6.2
 */