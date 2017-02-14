package com.ta.utdid2.aid;

import android.content.Context;
import android.util.Log;
import com.ta.utdid2.android.utils.NetworkUtils;
import com.ta.utdid2.android.utils.StringUtils;
import com.ta.utdid2.android.utils.TimeUtils;
import com.ut.device.AidCallback;

public class AidManager
{
  private static final int NUM_DAY_OUT_OF_DATE = 1;
  private static final String TAG = AidManager.class.getName();
  private static AidManager sAidManager = null;
  private Context mContext;

  private AidManager(Context paramContext)
  {
    this.mContext = paramContext;
  }

  private String genAidValue(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      String str;
      if (this.mContext == null)
      {
        Log.e(TAG, "no context!");
        str = "";
      }
      while (true)
      {
        return str;
        str = "";
        if (NetworkUtils.isConnected(this.mContext))
          str = AidRequester.getInstance(this.mContext).postRest(paramString1, paramString2, paramString3, AidStorageController.getAidValueFromSP(this.mContext, paramString1, paramString2));
        AidStorageController.setAidValueToSP(this.mContext, paramString1, str, paramString2);
      }
    }
    finally
    {
    }
    throw paramString1;
  }

  public static AidManager getInstance(Context paramContext)
  {
    try
    {
      if (sAidManager == null)
        sAidManager = new AidManager(paramContext);
      paramContext = sAidManager;
      return paramContext;
    }
    finally
    {
    }
    throw paramContext;
  }

  public String getValue(String paramString1, String paramString2, String paramString3)
  {
    boolean bool2 = true;
    Object localObject;
    boolean bool1;
    if ((this.mContext == null) || (StringUtils.isEmpty(paramString1)) || (StringUtils.isEmpty(paramString2)))
    {
      paramString3 = TAG;
      localObject = new StringBuilder().append("mContext:").append(this.mContext).append("; has appName:");
      if (!StringUtils.isEmpty(paramString1))
      {
        bool1 = true;
        paramString1 = ((StringBuilder)localObject).append(bool1).append("; has token:");
        if (StringUtils.isEmpty(paramString2))
          break label115;
        bool1 = bool2;
        label88: Log.e(paramString3, bool1);
        localObject = "";
      }
    }
    label115: 
    do
    {
      String str;
      do
      {
        return localObject;
        bool1 = false;
        break;
        bool1 = false;
        break label88;
        str = AidStorageController.getAidValueFromSP(this.mContext, paramString1, paramString2);
        if (StringUtils.isEmpty(str))
          break label160;
        localObject = str;
      }
      while (TimeUtils.isUpToDate(AidStorageController.getAidGenTimeFromSP(this.mContext, paramString1, paramString2), 1));
      localObject = str;
    }
    while (!NetworkUtils.isConnected(this.mContext));
    label160: return genAidValue(paramString1, paramString2, paramString3);
  }

  public void requestAid(String paramString1, String paramString2, String paramString3, AidCallback paramAidCallback)
  {
    boolean bool2 = true;
    if (paramAidCallback == null)
    {
      Log.e(TAG, "callback is null!");
      return;
    }
    if ((this.mContext == null) || (StringUtils.isEmpty(paramString1)) || (StringUtils.isEmpty(paramString2)))
    {
      paramString3 = TAG;
      localObject = new StringBuilder().append("mContext:").append(this.mContext).append("; callback:").append(paramAidCallback).append("; has appName:");
      if (!StringUtils.isEmpty(paramString1))
      {
        bool1 = true;
        paramString1 = ((StringBuilder)localObject).append(bool1).append("; has token:");
        if (StringUtils.isEmpty(paramString2))
          break label146;
      }
      label146: for (boolean bool1 = bool2; ; bool1 = false)
      {
        Log.e(paramString3, bool1);
        paramAidCallback.onAidEventChanged(1002, "");
        return;
        bool1 = false;
        break;
      }
    }
    Object localObject = AidStorageController.getAidValueFromSP(this.mContext, paramString1, paramString2);
    if ((!StringUtils.isEmpty((String)localObject)) && (TimeUtils.isUpToDate(AidStorageController.getAidGenTimeFromSP(this.mContext, paramString1, paramString2), 1)))
    {
      paramAidCallback.onAidEventChanged(1001, (String)localObject);
      return;
    }
    if (NetworkUtils.isConnected(this.mContext))
    {
      AidRequester.getInstance(this.mContext).postRestAsync(paramString1, paramString2, paramString3, (String)localObject, paramAidCallback);
      return;
    }
    paramAidCallback.onAidEventChanged(1003, (String)localObject);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ta.utdid2.aid.AidManager
 * JD-Core Version:    0.6.2
 */