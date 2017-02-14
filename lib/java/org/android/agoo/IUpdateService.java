package org.android.agoo;

import android.content.Context;

public abstract interface IUpdateService
{
  public abstract boolean checkUpdateJar(int paramInt, String paramString);

  public abstract void downloadUpdate(Context paramContext, String paramString1, String paramString2);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.IUpdateService
 * JD-Core Version:    0.6.2
 */