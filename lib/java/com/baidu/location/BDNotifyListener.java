package com.baidu.location;

import android.util.Log;
import com.baidu.location.d.a;

public abstract class BDNotifyListener
{
  public int Notified = 0;
  public float differDistance = 0.0F;
  public boolean isAdded = false;
  public String mCoorType = null;
  public double mLatitude = 4.9E-324D;
  public double mLatitudeC = 4.9E-324D;
  public double mLongitude = 4.9E-324D;
  public double mLongitudeC = 4.9E-324D;
  public a mNotifyCache = null;
  public float mRadius = 0.0F;

  public void SetNotifyLocation(double paramDouble1, double paramDouble2, float paramFloat, String paramString)
  {
    this.mLatitude = paramDouble1;
    this.mLongitude = paramDouble2;
    if (paramFloat < 0.0F)
    {
      this.mRadius = 200.0F;
      if ((!paramString.equals("gcj02")) && (!paramString.equals("bd09")) && (!paramString.equals("bd09ll")) && (!paramString.equals("gps")))
        break label127;
    }
    label127: for (this.mCoorType = paramString; ; this.mCoorType = "gcj02")
    {
      if (this.mCoorType.equals("gcj02"))
      {
        this.mLatitudeC = this.mLatitude;
        this.mLongitudeC = this.mLongitude;
      }
      if (this.isAdded)
      {
        this.Notified = 0;
        this.mNotifyCache.jdMethod_if(this);
      }
      return;
      this.mRadius = paramFloat;
      break;
    }
  }

  public void onNotify(BDLocation paramBDLocation, float paramFloat)
  {
    Log.d("baidu_location_service", "new location, not far from the destination..." + paramFloat);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.BDNotifyListener
 * JD-Core Version:    0.6.2
 */