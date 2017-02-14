package com.baidu.mapapi.map;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import org.json.JSONObject;

public final class MapPoi
{
  private static final String c = MapPoi.class.getSimpleName();
  String a;
  LatLng b;

  void a(JSONObject paramJSONObject)
  {
    this.a = paramJSONObject.optString("tx");
    this.b = CoordUtil.decodeNodeLocation(paramJSONObject.optString("geo"));
  }

  public String getName()
  {
    return this.a;
  }

  public LatLng getPosition()
  {
    return this.b;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.MapPoi
 * JD-Core Version:    0.6.2
 */