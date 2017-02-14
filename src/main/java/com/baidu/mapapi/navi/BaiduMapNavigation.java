package com.baidu.mapapi.navi;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.util.Log;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.utils.OpenClientUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BaiduMapNavigation extends com.baidu.mapapi.a.a.a
{
  private static boolean a = true;

  private static String a(Context paramContext)
  {
    Object localObject = null;
    try
    {
      localPackageManager = paramContext.getApplicationContext().getPackageManager();
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      try
      {
        PackageManager localPackageManager;
        for (paramContext = localPackageManager.getApplicationInfo(paramContext.getPackageName(), 0); ; paramContext = localObject)
        {
          return (String)localPackageManager.getApplicationLabel(paramContext);
          paramContext = paramContext;
          localPackageManager = null;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        while (true)
          paramContext = localObject;
      }
    }
  }

  private static void a(NaviParaOption paramNaviParaOption, Context paramContext)
    throws IllegalNaviArgumentException
  {
    if ((paramNaviParaOption == null) || (paramContext == null))
      throw new IllegalNaviArgumentException("para or context can not be null.");
    if ((paramNaviParaOption.a != null) && (paramNaviParaOption.c != null))
    {
      GeoPoint localGeoPoint1 = CoordUtil.ll2mc(paramNaviParaOption.a);
      GeoPoint localGeoPoint2 = CoordUtil.ll2mc(paramNaviParaOption.c);
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("http://app.navi.baidu.com/mobile/#navi/naving/");
      ((StringBuilder)localObject).append("&sy=0");
      ((StringBuilder)localObject).append("&endp=");
      ((StringBuilder)localObject).append("&start=");
      ((StringBuilder)localObject).append("&startwd=");
      ((StringBuilder)localObject).append("&endwd=");
      ((StringBuilder)localObject).append("&fromprod=map_sdk");
      ((StringBuilder)localObject).append("&app_version=");
      ((StringBuilder)localObject).append("3_6_0");
      JSONArray localJSONArray = new JSONArray();
      JSONObject localJSONObject1 = new JSONObject();
      JSONObject localJSONObject2 = new JSONObject();
      try
      {
        localJSONObject1.put("type", "1");
        if ((paramNaviParaOption.b != null) && (!paramNaviParaOption.b.equals("")))
          localJSONObject1.put("keyword", paramNaviParaOption.b);
        while (true)
        {
          localJSONObject1.put("xy", String.valueOf(localGeoPoint1.getLongitudeE6()) + "," + String.valueOf(localGeoPoint1.getLatitudeE6()));
          localJSONArray.put(localJSONObject1);
          localJSONObject2.put("type", "1");
          if ((paramNaviParaOption.d == null) || (paramNaviParaOption.d.equals("")))
            break;
          localJSONObject1.put("keyword", paramNaviParaOption.d);
          localJSONObject2.put("xy", String.valueOf(localGeoPoint2.getLongitudeE6()) + "," + String.valueOf(localGeoPoint2.getLatitudeE6()));
          localJSONArray.put(localJSONObject2);
          if (localJSONArray.length() > 0)
          {
            ((StringBuilder)localObject).append("&positions=");
            ((StringBuilder)localObject).append(localJSONArray.toString());
          }
          ((StringBuilder)localObject).append("&ctrl_type=");
          ((StringBuilder)localObject).append("&mrsl=");
          ((StringBuilder)localObject).append("/vt=map&state=entry");
          paramNaviParaOption = Uri.parse(((StringBuilder)localObject).toString());
          localObject = new Intent();
          ((Intent)localObject).setAction("android.intent.action.VIEW");
          ((Intent)localObject).setFlags(268435456);
          ((Intent)localObject).setData(paramNaviParaOption);
          paramContext.startActivity((Intent)localObject);
          return;
          localJSONObject1.put("keyword", "");
        }
      }
      catch (JSONException paramNaviParaOption)
      {
        while (true)
        {
          paramNaviParaOption.printStackTrace();
          continue;
          localJSONObject1.put("keyword", "");
        }
      }
    }
    throw new IllegalNaviArgumentException("you must set start and end point.");
  }

  public static void finish(Context paramContext)
  {
    if (paramContext != null)
      com.baidu.mapapi.utils.a.a(paramContext);
  }

  public static boolean openBaiduMapBikeNavi(NaviParaOption paramNaviParaOption, Context paramContext)
  {
    if ((paramNaviParaOption == null) || (paramContext == null))
      throw new IllegalNaviArgumentException("para or context can not be null.");
    if ((paramNaviParaOption.c == null) || (paramNaviParaOption.a == null))
      throw new IllegalNaviArgumentException("start point or end point can not be null.");
    int i = OpenClientUtil.getBaiduMapVersion(paramContext);
    if (i != 0)
    {
      if (i >= 869)
        return com.baidu.mapapi.utils.a.a(paramNaviParaOption, paramContext, 8);
      Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.6.6");
      return false;
    }
    Log.e("baidumapsdk", "BaiduMap app is not installed.");
    return false;
  }

  public static boolean openBaiduMapNavi(NaviParaOption paramNaviParaOption, Context paramContext)
  {
    if ((paramNaviParaOption == null) || (paramContext == null))
      throw new IllegalNaviArgumentException("para or context can not be null.");
    if ((paramNaviParaOption.c == null) || (paramNaviParaOption.a == null))
      throw new IllegalNaviArgumentException("start point or end point can not be null.");
    int i = OpenClientUtil.getBaiduMapVersion(paramContext);
    if (i != 0)
    {
      if (i >= 830)
        return com.baidu.mapapi.utils.a.a(paramNaviParaOption, paramContext, 5);
      Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.2");
      if (a)
      {
        a(paramNaviParaOption, paramContext);
        return true;
      }
      throw new BaiduMapAppNotSupportNaviException("Baidumap app version is too lowl.Version is greater than 8.2");
    }
    Log.e("baidumapsdk", "BaiduMap app is not installed.");
    if (a)
    {
      a(paramNaviParaOption, paramContext);
      return true;
    }
    throw new BaiduMapAppNotSupportNaviException("BaiduMap app is not installed.");
  }

  public static boolean openBaiduMapWalkNavi(NaviParaOption paramNaviParaOption, Context paramContext)
  {
    if ((paramNaviParaOption == null) || (paramContext == null))
      throw new IllegalNaviArgumentException("para or context can not be null.");
    if ((paramNaviParaOption.c == null) || (paramNaviParaOption.a == null))
      throw new IllegalNaviArgumentException("start point or end point can not be null.");
    int i = OpenClientUtil.getBaiduMapVersion(paramContext);
    if (i != 0)
    {
      if (i >= 869)
        return com.baidu.mapapi.utils.a.a(paramNaviParaOption, paramContext, 7);
      Log.e("baidumapsdk", "Baidumap app version is too lowl.Version is greater than 8.6.6");
      return false;
    }
    Log.e("baidumapsdk", "BaiduMap app is not installed.");
    return false;
  }

  @Deprecated
  public static void openWebBaiduMapNavi(NaviParaOption paramNaviParaOption, Context paramContext)
    throws IllegalNaviArgumentException
  {
    if ((paramNaviParaOption == null) || (paramContext == null))
      throw new IllegalNaviArgumentException("para or context can not be null.");
    Object localObject;
    if ((paramNaviParaOption.a != null) && (paramNaviParaOption.c != null))
    {
      localObject = CoordUtil.ll2mc(paramNaviParaOption.a);
      paramNaviParaOption = CoordUtil.ll2mc(paramNaviParaOption.c);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("http://daohang.map.baidu.com/mobile/#navi/naving/start=");
      localStringBuilder.append(((GeoPoint)localObject).getLongitudeE6());
      localStringBuilder.append(",");
      localStringBuilder.append(((GeoPoint)localObject).getLatitudeE6());
      localStringBuilder.append("&endp=");
      localStringBuilder.append(paramNaviParaOption.getLongitudeE6());
      localStringBuilder.append(",");
      localStringBuilder.append(paramNaviParaOption.getLatitudeE6());
      localStringBuilder.append("&fromprod=");
      localStringBuilder.append(a(paramContext));
      localStringBuilder.append("/vt=map&state=entry");
      paramNaviParaOption = Uri.parse(localStringBuilder.toString());
      localObject = new Intent();
      ((Intent)localObject).setAction("android.intent.action.VIEW");
      ((Intent)localObject).setFlags(268435456);
      ((Intent)localObject).setData(paramNaviParaOption);
      paramContext.startActivity((Intent)localObject);
      return;
    }
    if ((paramNaviParaOption.b != null) && (!paramNaviParaOption.b.equals("")) && (paramNaviParaOption.d != null) && (!paramNaviParaOption.d.equals("")))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("http://daohang.map.baidu.com/mobile/#search/search/qt=nav&sn=2$$$$$$");
      ((StringBuilder)localObject).append(paramNaviParaOption.b);
      ((StringBuilder)localObject).append("$$$$$$&en=2$$$$$$");
      ((StringBuilder)localObject).append(paramNaviParaOption.d);
      ((StringBuilder)localObject).append("$$$$$$&fromprod=");
      ((StringBuilder)localObject).append(a(paramContext));
      paramNaviParaOption = Uri.parse(((StringBuilder)localObject).toString());
      localObject = new Intent();
      ((Intent)localObject).setAction("android.intent.action.VIEW");
      ((Intent)localObject).setData(paramNaviParaOption);
      paramContext.startActivity((Intent)localObject);
      return;
    }
    throw new IllegalNaviArgumentException("you must set start and end point or set the start and end name.");
  }

  public static void setSupportWebNavi(boolean paramBoolean)
  {
    a = paramBoolean;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.navi.BaiduMapNavigation
 * JD-Core Version:    0.6.2
 */