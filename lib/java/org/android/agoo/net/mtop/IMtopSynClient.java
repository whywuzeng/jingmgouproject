package org.android.agoo.net.mtop;

import android.content.Context;
import java.util.Map;
import org.android.agoo.net.async.RequestParams;
import org.android.agoo.net.async.SyncHttpClient.a;

public abstract interface IMtopSynClient
{
  public abstract SyncHttpClient.a get(Context paramContext, String paramString, RequestParams paramRequestParams)
    throws Throwable;

  public abstract Result getV3(Context paramContext, MtopRequest paramMtopRequest);

  public abstract Map getV3ForRegister(Context paramContext, MtopRequest paramMtopRequest);

  public abstract void setBaseUrl(String paramString);

  public abstract void setDefaultAppSecret(String paramString);

  public abstract void setDefaultAppkey(String paramString);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.mtop.IMtopSynClient
 * JD-Core Version:    0.6.2
 */