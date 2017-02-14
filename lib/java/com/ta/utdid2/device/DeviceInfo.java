package com.ta.utdid2.device;

import android.content.Context;
import com.ta.utdid2.android.utils.PhoneInfoUtils;
import com.ta.utdid2.android.utils.StringUtils;
import java.util.zip.Adler32;

public class DeviceInfo
{
  static final Object CREATE_DEVICE_METADATA_LOCK = new Object();
  static String HMAC_KEY;
  static final byte UTDID_VERSION_CODE = 1;
  private static Device mDevice = null;

  static
  {
    HMAC_KEY = "d6fc3a4a06adbde89223bvefedc24fecde188aaa9161";
  }

  private static Device _initDeviceMetadata(Context paramContext)
  {
    if (paramContext != null)
    {
      new Device();
      label140: 
      while (true)
        synchronized (CREATE_DEVICE_METADATA_LOCK)
        {
          String str1 = UTUtdid.instance(paramContext).getValue();
          if (!StringUtils.isEmpty(str1))
          {
            if (!str1.endsWith("\n"))
              break label140;
            str1 = str1.substring(0, str1.length() - 1);
            Device localDevice = new Device();
            long l = System.currentTimeMillis();
            String str2 = PhoneInfoUtils.getImei(paramContext);
            paramContext = PhoneInfoUtils.getImsi(paramContext);
            localDevice.setDeviceId(str2);
            localDevice.setImei(str2);
            localDevice.setCreateTimestamp(l);
            localDevice.setImsi(paramContext);
            localDevice.setUtdid(str1);
            localDevice.setCheckSum(getMetadataCheckSum(localDevice));
            return localDevice;
          }
        }
    }
    return null;
  }

  public static Device getDevice(Context paramContext)
  {
    while (true)
    {
      try
      {
        if (mDevice != null)
        {
          paramContext = mDevice;
          return paramContext;
        }
        if (paramContext != null)
        {
          paramContext = _initDeviceMetadata(paramContext);
          mDevice = paramContext;
          continue;
        }
      }
      finally
      {
      }
      paramContext = null;
    }
  }

  static long getMetadataCheckSum(Device paramDevice)
  {
    if (paramDevice != null)
    {
      paramDevice = String.format("%s%s%s%s%s", new Object[] { paramDevice.getUtdid(), paramDevice.getDeviceId(), Long.valueOf(paramDevice.getCreateTimestamp()), paramDevice.getImsi(), paramDevice.getImei() });
      if (!StringUtils.isEmpty(paramDevice))
      {
        Adler32 localAdler32 = new Adler32();
        localAdler32.reset();
        localAdler32.update(paramDevice.getBytes());
        return localAdler32.getValue();
      }
    }
    return 0L;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ta.utdid2.device.DeviceInfo
 * JD-Core Version:    0.6.2
 */