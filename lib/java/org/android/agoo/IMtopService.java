package org.android.agoo;

import android.content.Context;
import org.android.agoo.client.MtopProxyRequest;
import org.android.agoo.client.MtopProxyResponseHandler;
import org.android.agoo.client.MtopSyncResult;

public abstract interface IMtopService
{
  public abstract MtopSyncResult getV3(Context paramContext, MtopProxyRequest paramMtopProxyRequest);

  public abstract void sendMtop(Context paramContext, MtopProxyRequest paramMtopProxyRequest);

  public abstract void sendMtop(Context paramContext, MtopProxyRequest paramMtopProxyRequest, MtopProxyResponseHandler paramMtopProxyResponseHandler);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.IMtopService
 * JD-Core Version:    0.6.2
 */