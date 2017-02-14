package org.android.agoo.impl;

import android.content.Context;
import com.umeng.message.proguard.bF;
import org.android.agoo.IMtopService;
import org.android.agoo.a;
import org.android.agoo.client.BaseRegistrar;
import org.android.agoo.client.MtopProxyRequest;
import org.android.agoo.client.MtopProxyResponseHandler;
import org.android.agoo.client.MtopSyncResult;
import org.android.agoo.net.mtop.IMtopAsynClient;
import org.android.agoo.net.mtop.IMtopSynClient;
import org.android.agoo.net.mtop.MtopAsyncClientV3;
import org.android.agoo.net.mtop.MtopRequest;
import org.android.agoo.net.mtop.MtopResponseHandler;
import org.android.agoo.net.mtop.MtopSyncClientV3;
import org.android.agoo.net.mtop.Result;

public final class MtopService
  implements IMtopService
{
  public final MtopSyncResult getV3(Context paramContext, MtopProxyRequest paramMtopProxyRequest)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramContext != null)
    {
      localObject1 = localObject2;
      if (paramMtopProxyRequest == null);
    }
    try
    {
      localObject1 = new MtopRequest();
      ((MtopRequest)localObject1).setApi(paramMtopProxyRequest.getApi());
      ((MtopRequest)localObject1).setV(paramMtopProxyRequest.getV());
      ((MtopRequest)localObject1).setDeviceId(BaseRegistrar.getRegistrationId(paramContext));
      if (!bF.a(paramMtopProxyRequest.getSId()))
        ((MtopRequest)localObject1).setSId(paramMtopProxyRequest.getSId());
      ((MtopRequest)localObject1).setAppKey(a.f(paramContext));
      ((MtopRequest)localObject1).setAppSecret(a.j(paramContext));
      ((MtopRequest)localObject1).putParams(paramMtopProxyRequest.getParams());
      ((MtopRequest)localObject1).putSysParams(paramMtopProxyRequest.getSysParams());
      paramMtopProxyRequest = new MtopSyncClientV3();
      paramMtopProxyRequest.setBaseUrl(a.G(paramContext));
      paramContext = paramMtopProxyRequest.getV3(paramContext, (MtopRequest)localObject1);
      localObject1 = localObject2;
      if (paramContext != null)
      {
        localObject1 = new MtopSyncResult();
        ((MtopSyncResult)localObject1).setSuccess(paramContext.isSuccess());
        ((MtopSyncResult)localObject1).setData(paramContext.getData());
        ((MtopSyncResult)localObject1).setRetDesc(paramContext.getRetDesc());
        ((MtopSyncResult)localObject1).setRetCode(paramContext.getRetCode());
      }
      return localObject1;
    }
    catch (Throwable paramContext)
    {
      paramMtopProxyRequest = new MtopSyncResult();
      paramMtopProxyRequest.setSuccess(false);
      paramMtopProxyRequest.setRetDesc(paramContext.getMessage());
    }
    return paramMtopProxyRequest;
  }

  public final void sendMtop(Context paramContext, MtopProxyRequest paramMtopProxyRequest)
  {
    if ((paramContext != null) && (paramMtopProxyRequest != null));
    try
    {
      MtopRequest localMtopRequest = new MtopRequest();
      localMtopRequest.setApi(paramMtopProxyRequest.getApi());
      localMtopRequest.setV(paramMtopProxyRequest.getV());
      localMtopRequest.setDeviceId(BaseRegistrar.getRegistrationId(paramContext));
      if (!bF.a(paramMtopProxyRequest.getSId()))
        localMtopRequest.setSId(paramMtopProxyRequest.getSId());
      localMtopRequest.putParams(paramMtopProxyRequest.getParams());
      localMtopRequest.putSysParams(paramMtopProxyRequest.getSysParams());
      paramMtopProxyRequest = new MtopAsyncClientV3();
      paramMtopProxyRequest.setDefaultAppkey(a.f(paramContext));
      paramMtopProxyRequest.setDefaultAppSecret(a.j(paramContext));
      paramMtopProxyRequest.setBaseUrl(a.G(paramContext));
      paramMtopProxyRequest.getV3(paramContext, localMtopRequest, new MtopResponseHandler()
      {
        public final void onFailure(String paramAnonymousString1, String paramAnonymousString2)
        {
        }

        public final void onSuccess(String paramAnonymousString)
        {
        }
      });
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public final void sendMtop(Context paramContext, MtopProxyRequest paramMtopProxyRequest, final MtopProxyResponseHandler paramMtopProxyResponseHandler)
  {
    if ((paramContext != null) && (paramMtopProxyRequest != null) && (paramMtopProxyResponseHandler != null));
    try
    {
      MtopRequest localMtopRequest = new MtopRequest();
      localMtopRequest.setApi(paramMtopProxyRequest.getApi());
      localMtopRequest.setV(paramMtopProxyRequest.getV());
      localMtopRequest.setDeviceId(BaseRegistrar.getRegistrationId(paramContext));
      if (!bF.a(paramMtopProxyRequest.getSId()))
        localMtopRequest.setSId(paramMtopProxyRequest.getSId());
      localMtopRequest.putParams(paramMtopProxyRequest.getParams());
      localMtopRequest.putSysParams(paramMtopProxyRequest.getSysParams());
      paramMtopProxyRequest = new MtopAsyncClientV3();
      paramMtopProxyRequest.setDefaultAppkey(a.f(paramContext));
      paramMtopProxyRequest.setDefaultAppSecret(a.j(paramContext));
      paramMtopProxyRequest.setBaseUrl(a.G(paramContext));
      paramMtopProxyRequest.getV3(paramContext, localMtopRequest, new MtopResponseHandler()
      {
        public final void onFailure(String paramAnonymousString1, String paramAnonymousString2)
        {
          paramMtopProxyResponseHandler.onFailure(paramAnonymousString1, paramAnonymousString2);
        }

        public final void onSuccess(String paramAnonymousString)
        {
          paramMtopProxyResponseHandler.onSuccess(paramAnonymousString);
        }
      });
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.impl.MtopService
 * JD-Core Version:    0.6.2
 */