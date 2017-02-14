package com.baidu.platform.comapi.map;

import android.graphics.Point;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comjni.map.basemap.a;
import org.json.JSONException;
import org.json.JSONObject;

public class F
{
  private a a;

  public F(a parama)
  {
    this.a = parama;
  }

  public Point a(GeoPoint paramGeoPoint)
  {
    if (paramGeoPoint == null)
      paramGeoPoint = null;
    Point localPoint;
    String str;
    do
    {
      return paramGeoPoint;
      localPoint = new Point(0, 0);
      str = this.a.b((int)paramGeoPoint.getLongitudeE6(), (int)paramGeoPoint.getLatitudeE6());
      paramGeoPoint = localPoint;
    }
    while (str == null);
    try
    {
      paramGeoPoint = new JSONObject(str);
      localPoint.x = paramGeoPoint.getInt("scrx");
      localPoint.y = paramGeoPoint.getInt("scry");
      return localPoint;
    }
    catch (JSONException paramGeoPoint)
    {
      paramGeoPoint.printStackTrace();
    }
    return localPoint;
  }

  public GeoPoint a(int paramInt1, int paramInt2)
  {
    Object localObject = this.a.a(paramInt1, paramInt2);
    GeoPoint localGeoPoint = new GeoPoint(0.0D, 0.0D);
    if (localObject != null)
      try
      {
        localObject = new JSONObject((String)localObject);
        localGeoPoint.setLongitudeE6(((JSONObject)localObject).getInt("geox"));
        localGeoPoint.setLatitudeE6(((JSONObject)localObject).getInt("geoy"));
        return localGeoPoint;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    return null;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.map.F
 * JD-Core Version:    0.6.2
 */