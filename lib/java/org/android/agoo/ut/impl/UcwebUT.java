package org.android.agoo.ut.impl;

import android.content.Context;
import com.ta.utdid2.device.UTDevice;
import org.android.agoo.ut.UT;

public final class UcwebUT
  implements UT
{
  public final void commitEvent(int paramInt, Object paramObject)
  {
  }

  public final void commitEvent(int paramInt, Object paramObject1, Object paramObject2)
  {
  }

  public final void commitEvent(int paramInt, Object paramObject1, Object paramObject2, Object paramObject3)
  {
  }

  public final void commitEvent(int paramInt, Object paramObject1, Object paramObject2, Object paramObject3, String[] paramArrayOfString)
  {
  }

  public final String getUtdId(Context paramContext)
  {
    try
    {
      paramContext = UTDevice.getUtdid(paramContext);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
    }
    return null;
  }

  public final void onCaughException(Throwable paramThrowable)
  {
  }

  public final void start(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
  }

  public final void stop(Context paramContext)
  {
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.ut.impl.UcwebUT
 * JD-Core Version:    0.6.2
 */