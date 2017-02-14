package com.ismartgo.app.tools;

import android.content.Context;
import com.ismartgo.app.grid.utils.DeviceIMEIUtils;

public class TelephoneUtils
{
  public static String getIMEI(Context paramContext)
  {
    return DeviceIMEIUtils.getUDID(paramContext);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.tools.TelephoneUtils
 * JD-Core Version:    0.6.2
 */