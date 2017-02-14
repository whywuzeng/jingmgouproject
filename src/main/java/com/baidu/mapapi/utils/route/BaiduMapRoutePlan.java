package com.baidu.mapapi.utils.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.navi.IllegalNaviArgumentException;
import com.baidu.mapapi.utils.OpenClientUtil;
import com.baidu.mapapi.utils.a;
import com.baidu.mapapi.utils.poi.IllegalPoiSearchArgumentException;

public class BaiduMapRoutePlan
{
  private static boolean a = true;

  private static void a(RouteParaOption paramRouteParaOption, Context paramContext, int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("http://api.map.baidu.com/direction?");
    localStringBuilder.append("origin=");
    label221: Object localObject;
    if ((paramRouteParaOption.a != null) && (paramRouteParaOption.c != null) && (!paramRouteParaOption.c.equals("")))
    {
      localStringBuilder.append("latlng:");
      localStringBuilder.append(paramRouteParaOption.a.latitude);
      localStringBuilder.append(",");
      localStringBuilder.append(paramRouteParaOption.a.longitude);
      localStringBuilder.append("|");
      localStringBuilder.append("name:");
      localStringBuilder.append(paramRouteParaOption.c);
      localStringBuilder.append("&destination=");
      if ((paramRouteParaOption.b == null) || (paramRouteParaOption.d == null) || (paramRouteParaOption.d.equals("")))
        break label428;
      localStringBuilder.append("latlng:");
      localStringBuilder.append(paramRouteParaOption.b.latitude);
      localStringBuilder.append(",");
      localStringBuilder.append(paramRouteParaOption.b.longitude);
      localStringBuilder.append("|");
      localStringBuilder.append("name:");
      localStringBuilder.append(paramRouteParaOption.d);
      localObject = "";
      switch (paramInt)
      {
      default:
        label252: localStringBuilder.append("&mode=");
        localStringBuilder.append((String)localObject);
        localStringBuilder.append("&region=");
        if ((paramRouteParaOption.getCityName() == null) || (paramRouteParaOption.getCityName().equals("")))
          localStringBuilder.append("全国");
        break;
      case 0:
      case 1:
      case 2:
      }
    }
    while (true)
    {
      localStringBuilder.append("&output=html");
      localStringBuilder.append("&src=");
      localStringBuilder.append(paramContext.getPackageName());
      paramRouteParaOption = Uri.parse(localStringBuilder.toString());
      localObject = new Intent();
      ((Intent)localObject).setAction("android.intent.action.VIEW");
      ((Intent)localObject).setFlags(268435456);
      ((Intent)localObject).setData(paramRouteParaOption);
      paramContext.startActivity((Intent)localObject);
      return;
      if (paramRouteParaOption.a != null)
      {
        localStringBuilder.append(paramRouteParaOption.a.latitude);
        localStringBuilder.append(",");
        localStringBuilder.append(paramRouteParaOption.a.longitude);
        break;
      }
      localStringBuilder.append(paramRouteParaOption.c);
      break;
      label428: if (paramRouteParaOption.b != null)
      {
        localStringBuilder.append(paramRouteParaOption.b.latitude);
        localStringBuilder.append(",");
        localStringBuilder.append(paramRouteParaOption.b.longitude);
        break label221;
      }
      localStringBuilder.append(paramRouteParaOption.d);
      break label221;
      localObject = "driving";
      break label252;
      localObject = "transit";
      break label252;
      localObject = "walking";
      break label252;
      localStringBuilder.append(paramRouteParaOption.getCityName());
    }
  }

  public static void finish(Context paramContext)
  {
    if (paramContext != null)
      a.a(paramContext);
  }

  public static boolean openBaiduMapDrivingRoute(RouteParaOption paramRouteParaOption, Context paramContext)
  {
    if ((paramRouteParaOption == null) || (paramContext == null))
      throw new IllegalPoiSearchArgumentException("para or context can not be null.");
    if ((paramRouteParaOption.b == null) && (paramRouteParaOption.a == null) && (paramRouteParaOption.d == null) && (paramRouteParaOption.c == null))
      throw new IllegalNaviArgumentException("startPoint and endPoint and endName and startName not all null.");
    if ((paramRouteParaOption.c == null) && (paramRouteParaOption.a == null))
      throw new IllegalNaviArgumentException("startPoint and startName not all null.");
    if ((paramRouteParaOption.d == null) && (paramRouteParaOption.b == null))
      throw new IllegalNaviArgumentException("endPoint and endName not all null.");
    if (((paramRouteParaOption.c != null) && (!paramRouteParaOption.c.equals(""))) || ((paramRouteParaOption.a == null) || (((paramRouteParaOption.d == null) || (paramRouteParaOption.d.equals(""))) && (paramRouteParaOption.b == null))))
    {
      Log.e(BaiduMapRoutePlan.class.getName(), "poi startName or endName can not be empty string while pt is null");
      return false;
    }
    if (paramRouteParaOption.f == null)
      paramRouteParaOption.f = RouteParaOption.EBusStrategyType.bus_recommend_way;
    int i = OpenClientUtil.getBaiduMapVersion(paramContext);
    if (i != 0)
    {
      if (i >= 810)
        return a.a(paramRouteParaOption, paramContext, 0);
      Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.1");
      if (a)
      {
        a(paramRouteParaOption, paramContext, 0);
        return true;
      }
      throw new IllegalPoiSearchArgumentException("Baidumap app version is too lowl.Version is greater than 8.1");
    }
    Log.e("baidumapsdk", "BaiduMap app is not installed.");
    if (a)
    {
      a(paramRouteParaOption, paramContext, 0);
      return true;
    }
    throw new IllegalPoiSearchArgumentException("BaiduMap app is not installed.");
  }

  public static boolean openBaiduMapTransitRoute(RouteParaOption paramRouteParaOption, Context paramContext)
  {
    if ((paramRouteParaOption == null) || (paramContext == null))
      throw new IllegalPoiSearchArgumentException("para or context can not be null.");
    if ((paramRouteParaOption.b == null) && (paramRouteParaOption.a == null) && (paramRouteParaOption.d == null) && (paramRouteParaOption.c == null))
      throw new IllegalNaviArgumentException("startPoint and endPoint and endName and startName not all null.");
    if ((paramRouteParaOption.c == null) && (paramRouteParaOption.a == null))
      throw new IllegalNaviArgumentException("startPoint and startName not all null.");
    if ((paramRouteParaOption.d == null) && (paramRouteParaOption.b == null))
      throw new IllegalNaviArgumentException("endPoint and endName not all null.");
    if (((paramRouteParaOption.c != null) && (!paramRouteParaOption.c.equals(""))) || ((paramRouteParaOption.a == null) || (((paramRouteParaOption.d == null) || (paramRouteParaOption.d.equals(""))) && (paramRouteParaOption.b == null))))
    {
      Log.e(BaiduMapRoutePlan.class.getName(), "poi startName or endName can not be empty string while pt is null");
      return false;
    }
    if (paramRouteParaOption.f == null)
      paramRouteParaOption.f = RouteParaOption.EBusStrategyType.bus_recommend_way;
    int i = OpenClientUtil.getBaiduMapVersion(paramContext);
    if (i != 0)
    {
      if (i >= 810)
        return a.a(paramRouteParaOption, paramContext, 1);
      Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.1");
      if (a)
      {
        a(paramRouteParaOption, paramContext, 1);
        return true;
      }
      throw new IllegalPoiSearchArgumentException("Baidumap app version is too lowl.Version is greater than 8.1");
    }
    Log.e("baidumapsdk", "BaiduMap app is not installed.");
    if (a)
    {
      a(paramRouteParaOption, paramContext, 1);
      return true;
    }
    throw new IllegalPoiSearchArgumentException("BaiduMap app is not installed.");
  }

  public static boolean openBaiduMapWalkingRoute(RouteParaOption paramRouteParaOption, Context paramContext)
  {
    if ((paramRouteParaOption == null) || (paramContext == null))
      throw new IllegalPoiSearchArgumentException("para or context can not be null.");
    if ((paramRouteParaOption.b == null) && (paramRouteParaOption.a == null) && (paramRouteParaOption.d == null) && (paramRouteParaOption.c == null))
      throw new IllegalNaviArgumentException("startPoint and endPoint and endName and startName not all null.");
    if ((paramRouteParaOption.c == null) && (paramRouteParaOption.a == null))
      throw new IllegalNaviArgumentException("startPoint and startName not all null.");
    if ((paramRouteParaOption.d == null) && (paramRouteParaOption.b == null))
      throw new IllegalNaviArgumentException("endPoint and endName not all null.");
    if (((paramRouteParaOption.c != null) && (!paramRouteParaOption.c.equals(""))) || ((paramRouteParaOption.a == null) || (((paramRouteParaOption.d == null) || (paramRouteParaOption.d.equals(""))) && (paramRouteParaOption.b == null))))
    {
      Log.e(BaiduMapRoutePlan.class.getName(), "poi startName or endName can not be empty string while pt is null");
      return false;
    }
    if (paramRouteParaOption.f == null)
      paramRouteParaOption.f = RouteParaOption.EBusStrategyType.bus_recommend_way;
    int i = OpenClientUtil.getBaiduMapVersion(paramContext);
    if (i != 0)
    {
      if (i >= 810)
        return a.a(paramRouteParaOption, paramContext, 2);
      Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.1");
      if (a)
      {
        a(paramRouteParaOption, paramContext, 2);
        return true;
      }
      throw new IllegalPoiSearchArgumentException("Baidumap app version is too lowl.Version is greater than 8.1");
    }
    Log.e("baidumapsdk", "BaiduMap app is not installed.");
    if (a)
    {
      a(paramRouteParaOption, paramContext, 2);
      return true;
    }
    throw new IllegalPoiSearchArgumentException("BaiduMap app is not installed.");
  }

  public static void setSupportWebRoute(boolean paramBoolean)
  {
    a = paramBoolean;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.utils.route.BaiduMapRoutePlan
 * JD-Core Version:    0.6.2
 */