package com.mob.tools.network;

import org.apache.http.HttpResponse;

public abstract interface HttpResponseCallback
{
  public abstract void onResponse(HttpResponse paramHttpResponse)
    throws Throwable;
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.network.HttpResponseCallback
 * JD-Core Version:    0.6.2
 */