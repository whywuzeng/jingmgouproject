package com.baidu.location;

public final class LocationClientOption
{
  public static final int GpsFirst = 1;
  public static final int GpsOnly = 3;
  public static final int MIN_SCAN_SPAN = 1000;
  public static final int NetWorkFirst = 2;
  protected LocationMode a;
  public String addrType = "detail";
  public String coorType = "gcj02";
  public boolean disableLocCache = true;
  public boolean enableSimulateGps = false;
  public boolean isIgnoreCacheException = false;
  public boolean isIgnoreKillProcess = true;
  public boolean isNeedAltitude = false;
  public boolean isNeedAptag = false;
  public boolean isNeedAptagd = false;
  public boolean isNeedPoiRegion = false;
  public boolean isNeedRegular = false;
  public boolean location_change_notify = false;
  public boolean mIsNeedDeviceDirect = false;
  public boolean openGps = false;
  public int priority = 1;
  public String prodName = "SDK6.0";
  public int scanSpan = 0;
  public String serviceName = "com.baidu.location.service_v2.9";
  public int timeOut = 12000;

  public LocationClientOption()
  {
  }

  public LocationClientOption(LocationClientOption paramLocationClientOption)
  {
    this.coorType = paramLocationClientOption.coorType;
    this.addrType = paramLocationClientOption.addrType;
    this.openGps = paramLocationClientOption.openGps;
    this.scanSpan = paramLocationClientOption.scanSpan;
    this.timeOut = paramLocationClientOption.timeOut;
    this.prodName = paramLocationClientOption.prodName;
    this.priority = paramLocationClientOption.priority;
    this.location_change_notify = paramLocationClientOption.location_change_notify;
    this.serviceName = paramLocationClientOption.serviceName;
    this.disableLocCache = paramLocationClientOption.disableLocCache;
    this.isIgnoreCacheException = paramLocationClientOption.isIgnoreCacheException;
    this.isIgnoreKillProcess = paramLocationClientOption.isIgnoreKillProcess;
    this.enableSimulateGps = paramLocationClientOption.enableSimulateGps;
    this.a = paramLocationClientOption.a;
    this.isNeedAptag = paramLocationClientOption.isNeedAptag;
    this.isNeedAptagd = paramLocationClientOption.isNeedAptagd;
    this.isNeedPoiRegion = paramLocationClientOption.isNeedPoiRegion;
    this.isNeedRegular = paramLocationClientOption.isNeedRegular;
    this.mIsNeedDeviceDirect = paramLocationClientOption.mIsNeedDeviceDirect;
    this.isNeedAltitude = paramLocationClientOption.isNeedAltitude;
  }

  private void a(boolean paramBoolean)
  {
    this.isNeedAltitude = paramBoolean;
  }

  public void SetIgnoreCacheException(boolean paramBoolean)
  {
    this.isIgnoreCacheException = paramBoolean;
  }

  public void disableCache(boolean paramBoolean)
  {
    this.disableLocCache = paramBoolean;
  }

  public boolean equals(LocationClientOption paramLocationClientOption)
  {
    return (this.coorType.equals(paramLocationClientOption.coorType)) && (this.addrType.equals(paramLocationClientOption.addrType)) && (this.openGps == paramLocationClientOption.openGps) && (this.scanSpan == paramLocationClientOption.scanSpan) && (this.timeOut == paramLocationClientOption.timeOut) && (this.prodName.equals(paramLocationClientOption.prodName)) && (this.location_change_notify == paramLocationClientOption.location_change_notify) && (this.priority == paramLocationClientOption.priority) && (this.disableLocCache == paramLocationClientOption.disableLocCache) && (this.isIgnoreCacheException == paramLocationClientOption.isIgnoreCacheException) && (this.isIgnoreKillProcess == paramLocationClientOption.isIgnoreKillProcess) && (this.isNeedAptag == paramLocationClientOption.isNeedAptag) && (this.isNeedAptagd == paramLocationClientOption.isNeedAptagd) && (this.isNeedPoiRegion == paramLocationClientOption.isNeedPoiRegion) && (this.isNeedRegular == paramLocationClientOption.isNeedRegular) && (this.mIsNeedDeviceDirect == paramLocationClientOption.mIsNeedDeviceDirect) && (this.isNeedAltitude == paramLocationClientOption.isNeedAltitude) && (this.a == paramLocationClientOption.a);
  }

  public String getAddrType()
  {
    return this.addrType;
  }

  public String getCoorType()
  {
    return this.coorType;
  }

  public LocationMode getLocationMode()
  {
    return this.a;
  }

  public int getPriority()
  {
    return this.priority;
  }

  public String getProdName()
  {
    return this.prodName;
  }

  public int getScanSpan()
  {
    return this.scanSpan;
  }

  public String getServiceName()
  {
    return this.serviceName;
  }

  public int getTimeOut()
  {
    return this.timeOut;
  }

  public boolean isDisableCache()
  {
    return this.disableLocCache;
  }

  public boolean isLocationNotify()
  {
    return this.location_change_notify;
  }

  public boolean isOpenGps()
  {
    return this.openGps;
  }

  public void setAddrType(String paramString)
  {
    this.addrType = paramString;
    if ("all".equals(this.addrType))
    {
      setIsNeedAddress(true);
      return;
    }
    setIsNeedAddress(false);
  }

  public void setCoorType(String paramString)
  {
    paramString = paramString.toLowerCase();
    if ((paramString.equals("gcj02")) || (paramString.equals("bd09")) || (paramString.equals("bd09ll")))
      this.coorType = paramString;
  }

  public void setEnableSimulateGps(boolean paramBoolean)
  {
    this.enableSimulateGps = paramBoolean;
  }

  public void setIgnoreKillProcess(boolean paramBoolean)
  {
    this.isIgnoreKillProcess = paramBoolean;
  }

  public void setIsNeedAddress(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.addrType = "all";
      return;
    }
    this.addrType = "noaddr";
  }

  public void setIsNeedLocationDescribe(boolean paramBoolean)
  {
    this.isNeedAptag = paramBoolean;
  }

  public void setIsNeedLocationPoiList(boolean paramBoolean)
  {
    this.isNeedAptagd = paramBoolean;
  }

  public void setLocationMode(LocationMode paramLocationMode)
  {
    switch (1.a[paramLocationMode.ordinal()])
    {
    default:
      throw new IllegalArgumentException("Illegal this mode : " + paramLocationMode);
    case 1:
      this.openGps = true;
      this.priority = 1;
    case 2:
    case 3:
    }
    while (true)
    {
      this.a = paramLocationMode;
      return;
      this.openGps = false;
      this.priority = 2;
      continue;
      this.priority = 3;
      this.openGps = true;
    }
  }

  public void setLocationNotify(boolean paramBoolean)
  {
    this.location_change_notify = paramBoolean;
  }

  public void setNeedDeviceDirect(boolean paramBoolean)
  {
    this.mIsNeedDeviceDirect = paramBoolean;
  }

  public void setOpenGps(boolean paramBoolean)
  {
    this.openGps = paramBoolean;
  }

  public void setPriority(int paramInt)
  {
    if ((paramInt == 1) || (paramInt == 2))
      this.priority = paramInt;
  }

  public void setProdName(String paramString)
  {
    String str = paramString;
    if (paramString.length() > 64)
      str = paramString.substring(0, 64);
    this.prodName = str;
  }

  public void setScanSpan(int paramInt)
  {
    this.scanSpan = paramInt;
  }

  public void setSema(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    this.isNeedAptag = paramBoolean1;
    this.isNeedPoiRegion = paramBoolean2;
    this.isNeedRegular = paramBoolean3;
  }

  public void setServiceName(String paramString)
  {
    this.serviceName = paramString;
  }

  public void setTimeOut(int paramInt)
  {
    this.timeOut = paramInt;
  }

  public static enum LocationMode
  {
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.LocationClientOption
 * JD-Core Version:    0.6.2
 */