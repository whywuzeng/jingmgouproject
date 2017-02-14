package com.baidu.mapapi.favorite;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.Point;
import com.baidu.platform.comapi.favrite.FavSyncPoi;
import org.json.JSONObject;

class a
{
  static FavoritePoiInfo a(FavSyncPoi paramFavSyncPoi)
  {
    if ((paramFavSyncPoi == null) || (paramFavSyncPoi.c == null) || (paramFavSyncPoi.b.equals("")))
      return null;
    FavoritePoiInfo localFavoritePoiInfo = new FavoritePoiInfo();
    localFavoritePoiInfo.a = paramFavSyncPoi.a;
    localFavoritePoiInfo.b = paramFavSyncPoi.b;
    localFavoritePoiInfo.c = new LatLng(paramFavSyncPoi.c.y / 1000000.0D, paramFavSyncPoi.c.x / 1000000.0D);
    localFavoritePoiInfo.e = paramFavSyncPoi.e;
    localFavoritePoiInfo.f = paramFavSyncPoi.f;
    localFavoritePoiInfo.d = paramFavSyncPoi.d;
    localFavoritePoiInfo.g = Long.parseLong(paramFavSyncPoi.h);
    return localFavoritePoiInfo;
  }

  static FavoritePoiInfo a(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null)
      return null;
    FavoritePoiInfo localFavoritePoiInfo = new FavoritePoiInfo();
    JSONObject localJSONObject = paramJSONObject.optJSONObject("pt");
    if (localJSONObject != null)
    {
      int i = localJSONObject.optInt("x");
      localFavoritePoiInfo.c = new LatLng(localJSONObject.optInt("y") / 1000000.0D, i / 1000000.0D);
    }
    localFavoritePoiInfo.b = paramJSONObject.optString("uspoiname");
    localFavoritePoiInfo.g = Long.parseLong(paramJSONObject.optString("addtimesec"));
    localFavoritePoiInfo.d = paramJSONObject.optString("addr");
    localFavoritePoiInfo.f = paramJSONObject.optString("uspoiuid");
    localFavoritePoiInfo.e = paramJSONObject.optString("ncityid");
    localFavoritePoiInfo.a = paramJSONObject.optString("key");
    return localFavoritePoiInfo;
  }

  static FavSyncPoi a(FavoritePoiInfo paramFavoritePoiInfo)
  {
    if ((paramFavoritePoiInfo == null) || (paramFavoritePoiInfo.c == null) || (paramFavoritePoiInfo.b == null) || (paramFavoritePoiInfo.b.equals("")))
      return null;
    FavSyncPoi localFavSyncPoi = new FavSyncPoi();
    localFavSyncPoi.b = paramFavoritePoiInfo.b;
    localFavSyncPoi.c = new Point((int)(paramFavoritePoiInfo.c.longitude * 1000000.0D), (int)(paramFavoritePoiInfo.c.latitude * 1000000.0D));
    localFavSyncPoi.d = paramFavoritePoiInfo.d;
    localFavSyncPoi.e = paramFavoritePoiInfo.e;
    localFavSyncPoi.f = paramFavoritePoiInfo.f;
    localFavSyncPoi.i = false;
    return localFavSyncPoi;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.favorite.a
 * JD-Core Version:    0.6.2
 */