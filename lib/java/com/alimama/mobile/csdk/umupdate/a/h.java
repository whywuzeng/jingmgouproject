package com.alimama.mobile.csdk.umupdate.a;

import android.content.Context;
import android.content.res.Resources;

public class h
{
  private String a;
  private Resources b;

  public h(Context paramContext)
  {
    this.b = paramContext.getResources();
    this.a = paramContext.getPackageName();
  }

  public int a()
  {
    return this.b.getIdentifier("tb_munion_adview", "layout", this.a);
  }

  public int b()
  {
    return this.b.getIdentifier("tb_munion_aditem", "layout", this.a);
  }

  public String c()
  {
    int i = this.b.getIdentifier("tb_munion_tip_download_prefix", "string", this.a);
    return this.b.getString(i);
  }

  public int d()
  {
    return this.b.getIdentifier("progress_frame", "id", this.a);
  }

  public int e()
  {
    return this.b.getIdentifier("promoter_frame", "id", this.a);
  }

  public int f()
  {
    return this.b.getIdentifier("status_msg", "id", this.a);
  }

  public int g()
  {
    return this.b.getIdentifier("loading", "id", this.a);
  }

  public int h()
  {
    return this.b.getIdentifier("ad_image", "id", this.a);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alimama.mobile.csdk.umupdate.a.h
 * JD-Core Version:    0.6.2
 */