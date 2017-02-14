package com.baidu.mapapi.map.offline;

import com.baidu.mapapi.model.LatLng;

public class MKOLUpdateElement
{
  public static final int DOWNLOADING = 1;
  public static final int FINISHED = 4;
  public static final int SUSPENDED = 3;
  public static final int UNDEFINED = 0;
  public static final int WAITING = 2;
  public static final int eOLDSFormatError = 9;
  public static final int eOLDSIOError = 7;
  public static final int eOLDSInstalling = 10;
  public static final int eOLDSMd5Error = 5;
  public static final int eOLDSNetError = 6;
  public static final int eOLDSWifiError = 8;
  public int cityID;
  public String cityName;
  public LatLng geoPt;
  public int level;
  public int ratio;
  public int serversize;
  public int size;
  public int status;
  public boolean update;
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.offline.MKOLUpdateElement
 * JD-Core Version:    0.6.2
 */