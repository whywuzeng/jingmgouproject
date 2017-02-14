package com.umeng.update;

import android.content.Context;
import com.umeng.update.util.DeltaUpdate;
import u.upd.g;

public class b extends g
{
  private static final String c = b.class.getName();
  Context a;
  private final String[] b = { "http://au.umeng.com/api/check_app_update", "http://au.umeng.co/api/check_app_update" };

  public b(Context paramContext)
  {
    this.a = paramContext;
  }

  public UpdateResponse a()
  {
    u.upd.b.c(c, String.format("is .so file ready: %b", new Object[] { Boolean.valueOf(DeltaUpdate.a()) }));
    d locald = new d(this.a);
    UpdateResponse localUpdateResponse = null;
    int i = 0;
    while (true)
    {
      if (i < this.b.length)
      {
        locald.setBaseUrl(this.b[i]);
        localUpdateResponse = (UpdateResponse)execute(locald, UpdateResponse.class);
        if (localUpdateResponse == null);
      }
      else
      {
        return localUpdateResponse;
      }
      i += 1;
    }
  }

  public boolean shouldCompressData()
  {
    return false;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.update.b
 * JD-Core Version:    0.6.2
 */