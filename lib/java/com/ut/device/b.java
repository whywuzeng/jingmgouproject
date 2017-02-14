package com.ut.device;

import android.content.Context;
import com.ta.utdid2.aid.AidManager;
import com.ta.utdid2.device.UTDevice;

public class b
{
  public static String a(Context paramContext)
  {
    return UTDevice.getUtdid(paramContext);
  }

  public static String a(String paramString1, String paramString2, Context paramContext)
  {
    return AidManager.getInstance(paramContext).getValue(paramString1, paramString2, a(paramContext));
  }

  public static void a(String paramString1, String paramString2, Context paramContext, AidCallback paramAidCallback)
  {
    AidManager.getInstance(paramContext).requestAid(paramString1, paramString2, a(paramContext), paramAidCallback);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ut.device.b
 * JD-Core Version:    0.6.2
 */