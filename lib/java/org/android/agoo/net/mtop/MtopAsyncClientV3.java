package org.android.agoo.net.mtop;

import android.content.Context;
import com.umeng.message.proguard.bd;
import org.android.agoo.net.async.AsyncHttpClient;

public class MtopAsyncClientV3 extends AsyncHttpClient
  implements IMtopAsynClient
{
  private static final String a = "MtopAsyncClientV3";
  private String b;
  private String c;
  private String d;

  public final void getV3(Context paramContext, MtopRequest paramMtopRequest, MtopResponseHandler paramMtopResponseHandler)
  {
    try
    {
      MtopRequestHelper.checkAppKeyAndAppSecret(paramMtopRequest, this.b, this.c);
      paramMtopRequest = MtopRequestHelper.getUrlWithRequestParams(paramContext, paramMtopRequest);
      get(paramContext, this.d, paramMtopRequest, paramMtopResponseHandler);
      return;
    }
    catch (Throwable paramContext)
    {
      bd.e("MtopAsyncClientV3", "getV3", paramContext);
      paramMtopResponseHandler.onFailure(paramContext, null, null);
    }
  }

  public final void setBaseUrl(String paramString)
  {
    this.d = paramString;
  }

  public final void setDefaultAppSecret(String paramString)
  {
    this.c = paramString;
  }

  public final void setDefaultAppkey(String paramString)
  {
    this.b = paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.mtop.MtopAsyncClientV3
 * JD-Core Version:    0.6.2
 */