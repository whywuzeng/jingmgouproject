package cn.sharesdk.framework.authorize;

import android.os.Bundle;

public abstract interface AuthorizeListener
{
  public abstract void onCancel();

  public abstract void onComplete(Bundle paramBundle);

  public abstract void onError(Throwable paramThrowable);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.authorize.AuthorizeListener
 * JD-Core Version:    0.6.2
 */