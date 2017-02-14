package com.ta.utdid2.device;

import android.content.Context;
import com.ta.utdid2.android.utils.StringUtils;

public class UTDevice
{
  public static String getUtdid(Context paramContext)
  {
    paramContext = DeviceInfo.getDevice(paramContext);
    if ((paramContext == null) || (StringUtils.isEmpty(paramContext.getUtdid())))
      return "ffffffffffffffffffffffff";
    return paramContext.getUtdid();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ta.utdid2.device.UTDevice
 * JD-Core Version:    0.6.2
 */