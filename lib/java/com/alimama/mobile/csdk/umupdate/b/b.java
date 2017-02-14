package com.alimama.mobile.csdk.umupdate.b;

import com.alimama.mobile.csdk.umupdate.a.j;
import u.upd.g;

public class b extends g
{
  public f a(e parame)
  {
    f localf = null;
    int i = 0;
    while (true)
    {
      if (i < a.c.length)
      {
        parame.setBaseUrl(a.c[i]);
        localf = (f)setHeader(j.a()).execute(parame, f.class);
        if ((localf == null) || (localf.b == null));
      }
      else
      {
        return localf;
      }
      i += 1;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alimama.mobile.csdk.umupdate.b.b
 * JD-Core Version:    0.6.2
 */