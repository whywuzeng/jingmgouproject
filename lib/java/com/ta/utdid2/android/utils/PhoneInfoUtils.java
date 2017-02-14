package com.ta.utdid2.android.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import java.util.Random;

public class PhoneInfoUtils
{
  public static String getImei(Context paramContext)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramContext != null);
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext != null)
      {
        paramContext = paramContext.getDeviceId();
        localObject1 = paramContext;
        paramContext = (Context)localObject1;
        if (StringUtils.isEmpty((String)localObject1))
          paramContext = getUniqueID();
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      while (true)
      {
        localObject1 = localObject2;
        continue;
        paramContext = null;
      }
    }
  }

  public static String getImsi(Context paramContext)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramContext != null);
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext != null)
      {
        paramContext = paramContext.getSubscriberId();
        localObject1 = paramContext;
        paramContext = (Context)localObject1;
        if (StringUtils.isEmpty((String)localObject1))
          paramContext = getUniqueID();
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      while (true)
      {
        localObject1 = localObject2;
        continue;
        paramContext = null;
      }
    }
  }

  public static final String getUniqueID()
  {
    int i = (int)(System.currentTimeMillis() / 1000L);
    int j = (int)System.nanoTime();
    int k = new Random().nextInt();
    int m = new Random().nextInt();
    byte[] arrayOfByte1 = IntUtils.getBytes(i);
    byte[] arrayOfByte2 = IntUtils.getBytes(j);
    byte[] arrayOfByte3 = IntUtils.getBytes(k);
    byte[] arrayOfByte4 = IntUtils.getBytes(m);
    byte[] arrayOfByte5 = new byte[16];
    System.arraycopy(arrayOfByte1, 0, arrayOfByte5, 0, 4);
    System.arraycopy(arrayOfByte2, 0, arrayOfByte5, 4, 4);
    System.arraycopy(arrayOfByte3, 0, arrayOfByte5, 8, 4);
    System.arraycopy(arrayOfByte4, 0, arrayOfByte5, 12, 4);
    return Base64.encodeToString(arrayOfByte5, 2);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ta.utdid2.android.utils.PhoneInfoUtils
 * JD-Core Version:    0.6.2
 */