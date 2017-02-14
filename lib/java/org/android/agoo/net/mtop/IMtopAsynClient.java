package org.android.agoo.net.mtop;

import android.content.Context;

public abstract interface IMtopAsynClient
{
  public abstract void getV3(Context paramContext, MtopRequest paramMtopRequest, MtopResponseHandler paramMtopResponseHandler);

  public abstract void setBaseUrl(String paramString);

  public abstract void setDefaultAppSecret(String paramString);

  public abstract void setDefaultAppkey(String paramString);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.mtop.IMtopAsynClient
 * JD-Core Version:    0.6.2
 */