package org.android.agoo.ut;

import android.content.Context;

public abstract interface UT
{
  public static final int AGOO_EVENT_ID = 273791437;
  public static final int NEW_EVENT_ID = 66002;

  public abstract void commitEvent(int paramInt, Object paramObject);

  public abstract void commitEvent(int paramInt, Object paramObject1, Object paramObject2);

  public abstract void commitEvent(int paramInt, Object paramObject1, Object paramObject2, Object paramObject3);

  public abstract void commitEvent(int paramInt, Object paramObject1, Object paramObject2, Object paramObject3, String[] paramArrayOfString);

  public abstract String getUtdId(Context paramContext);

  public abstract void onCaughException(Throwable paramThrowable);

  public abstract void start(Context paramContext, String paramString1, String paramString2, String paramString3);

  public abstract void stop(Context paramContext);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.ut.UT
 * JD-Core Version:    0.6.2
 */