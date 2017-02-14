package com.baidu.platform.comapi.util;

import android.os.Bundle;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.model.inner.Point;
import com.baidu.platform.comjni.tools.JNITools;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class b
{
  static Bundle a = new Bundle();
  static double[] b = { 12890594.859999999D, 8362377.8700000001D, 5591021.0D, 3481989.8300000001D, 1678043.1200000001D, 0.0D };
  static double[] c = { 75000000.0D, 60000000.0D, 45000000.0D, 30000000.0D, 15000000.0D, 0.0D };
  static double[][] d;
  static double[][] e = { { -0.0015702102444D, 111320.7020616939D, 1704480524535203.0D, -10338987376042340.0D, 26112667856603880.0D, -35149669176653700.0D, 26595700718403920.0D, -10725012454188240.0D, 1800819912950474.0D, 82.5D }, { 0.000827782451617253D, 111320.70204635779D, 647795574.66716075D, -4082003173.6413159D, 10774905663.511419D, -15171875531.515591D, 12053065338.62167D, -5124939663.5774717D, 913311935.95120323D, 67.5D }, { 0.00337398766765D, 111320.70202021619D, 4481351.0458903648D, -23393751.199316621D, 79682215.471864551D, -115964993.2797253D, 97236711.156021446D, -43661946.337528206D, 8477230.5011352338D, 52.5D }, { 0.00220636496208D, 111320.70202091279D, 51751.861128411307D, 3796837.7494702451D, 992013.73977910134D, -1221952.21711287D, 1340652.697009075D, -620943.69909843116D, 144416.92938062409D, 37.5D }, { -0.0003441963504368392D, 111320.7020576856D, 278.23539807727519D, 2485758.6900353939D, 6070.7509632433776D, 54821.183453521182D, 9540.6066333042363D, -2710.5532674664501D, 1405.483844121726D, 22.5D }, arrayOfDouble1 };

  static
  {
    double[] arrayOfDouble1 = { 1.410526172116255E-008D, 8.98305509648872E-006D, -1.9939833816331D, 200.98243831067961D, -187.2403703815547D, 91.608751666984304D, -23.38765649603339D, 2.57121317296198D, -0.03801003308653D, 17337981.199999999D };
    double[] arrayOfDouble2 = { -7.435856389565537E-009D, 8.983055097726239E-006D, -0.78625201886289D, 96.326875997598464D, -1.85204757529826D, -59.369359054858769D, 47.400335492967372D, -16.50741931063887D, 2.28786674699375D, 10260144.859999999D };
    double[] arrayOfDouble3 = { -3.030883460898826E-008D, 8.98305509983578E-006D, 0.30071316287616D, 59.742936184422767D, 7.357984074871D, -25.383710026647449D, 13.45380521110908D, -3.29883767235584D, 0.32710905363475D, 6856817.3700000001D };
    double[] arrayOfDouble4 = { 3.09191371068437E-009D, 8.983055096812155E-006D, 6.995724061999999E-005D, 23.109343041449009D, -0.00023663490511D, -0.6321817810242D, -0.00663494467273D, 0.03430082397953D, -0.00466043876332D, 2555164.3999999999D };
    double[] arrayOfDouble5 = { 2.890871144776878E-009D, 8.983055095805407E-006D, -3.068298E-008D, 7.47137025468032D, -3.53937994E-006D, -0.02145144861037D, -1.234426596E-005D, 0.00010322952773D, -3.23890364E-006D, 826088.5D };
    d = new double[][] { arrayOfDouble1, arrayOfDouble2, arrayOfDouble3, { -1.981981304930552E-008D, 8.983055099779535E-006D, 0.03278182852591D, 40.316785277057441D, 0.65659298677277D, -4.44255534477492D, 0.85341911805263D, 0.12923347998204D, -0.04625736007561D, 4482777.0599999996D }, arrayOfDouble4, arrayOfDouble5 };
    arrayOfDouble1 = new double[] { -0.0003218135878613132D, 111320.7020701615D, 0.00369383431289D, 823725.64027957176D, 0.46104986909093D, 2351.3431413312919D, 1.58060784298199D, 8.77738589078284D, 0.37238884252424D, 7.45D };
  }

  public static int a(LatLng paramLatLng, int paramInt)
  {
    double d1 = paramLatLng.latitude;
    Object localObject = new LatLng(paramInt / 111000.0D + d1, paramLatLng.longitude);
    paramLatLng = a(paramLatLng);
    localObject = a((LatLng)localObject);
    d1 = Math.pow(paramLatLng.getLatitudeE6() - ((GeoPoint)localObject).getLatitudeE6(), 2.0D);
    return (int)Math.sqrt(Math.pow(paramLatLng.getLongitudeE6() - ((GeoPoint)localObject).getLongitudeE6(), 2.0D) + d1);
  }

  public static LatLng a(GeoPoint paramGeoPoint)
  {
    a locala1 = new a();
    locala1.a = paramGeoPoint.getLongitudeE6();
    locala1.b = paramGeoPoint.getLatitudeE6();
    a locala2 = new a();
    locala2.a = locala1.a;
    label93: int i;
    if (locala2.a > 20037508.342D)
    {
      locala2.a = 20037508.342D;
      locala2.b = locala1.b;
      if ((locala2.b >= 1.0E-006D) || (locala2.b < 0.0D))
        break label170;
      locala2.b = 1.0E-006D;
      locala1 = null;
      i = 0;
    }
    while (true)
    {
      paramGeoPoint = locala1;
      if (i < 6)
      {
        if (Math.abs(locala2.b) > b[i])
          paramGeoPoint = d[i];
      }
      else
      {
        paramGeoPoint = a(locala2, paramGeoPoint);
        return new LatLng(paramGeoPoint.b, paramGeoPoint.a);
        if (locala2.a >= -20037508.342D)
          break;
        locala2.a = -20037508.342D;
        break;
        label170: if ((locala2.b < 0.0D) && (locala2.b > -1.0E-006D))
        {
          locala2.b = -1.0E-006D;
          break label93;
        }
        if (locala2.b > 20037508.342D)
        {
          locala2.b = 20037508.342D;
          break label93;
        }
        if (locala2.b >= -20037508.342D)
          break label93;
        locala2.b = -20037508.342D;
        break label93;
      }
      i += 1;
    }
  }

  public static LatLng a(String paramString)
  {
    if ((paramString == null) || (paramString.length() <= 0))
      return null;
    a.clear();
    a.putString("strkey", paramString);
    JNITools.TransGeoStr2Pt(a);
    paramString = new GeoPoint(0.0D, 0.0D);
    paramString.setLongitudeE6(a.getInt("ptx"));
    paramString.setLatitudeE6(a.getInt("pty"));
    return a(paramString);
  }

  public static GeoPoint a(LatLng paramLatLng)
  {
    a locala = new a();
    locala.b = Math.abs(paramLatLng.latitude * 1000000.0D);
    if (locala.b < 0.1D)
      locala.b = 0.1D;
    int i = 0;
    if (i < c.length)
      if (locala.b <= c[i]);
    for (double[] arrayOfDouble = e[i]; ; arrayOfDouble = null)
    {
      locala.a = paramLatLng.longitude;
      locala.b = paramLatLng.latitude;
      paramLatLng = a(locala, arrayOfDouble);
      return new GeoPoint(paramLatLng.b, paramLatLng.a);
      i += 1;
      break;
    }
  }

  public static Point a(float paramFloat1, float paramFloat2, String paramString)
  {
    if (paramString == null);
    do
    {
      do
      {
        return null;
        localObject = paramString;
        if (paramString.equals(""))
          localObject = "bd09ll";
      }
      while ((!((String)localObject).equals("bd09ll")) && (!((String)localObject).equals("bd09mc")) && (!((String)localObject).equals("gcj02")) && (!((String)localObject).equals("wgs84")));
      if (((String)localObject).equals("bd09mc"))
        return new Point((int)paramFloat1, (int)paramFloat2);
      paramString = new Bundle();
      JNITools.CoordinateEncryptEx(paramFloat1, paramFloat2, (String)localObject, paramString);
    }
    while (paramString.isEmpty());
    Object localObject = new Point(0, 0);
    ((Point)localObject).setmPtx((int)paramString.getDouble("x"));
    ((Point)localObject).setmPty((int)paramString.getDouble("y"));
    return localObject;
  }

  static a a(a parama, double[] paramArrayOfDouble)
  {
    int j = -1;
    a locala = new a();
    locala.a = (paramArrayOfDouble[0] + paramArrayOfDouble[1] * Math.abs(parama.a));
    double d1 = Math.abs(parama.b) / paramArrayOfDouble[9];
    double d2 = paramArrayOfDouble[2];
    double d3 = paramArrayOfDouble[3];
    double d4 = paramArrayOfDouble[4];
    double d5 = paramArrayOfDouble[5];
    double d6 = paramArrayOfDouble[6];
    double d7 = paramArrayOfDouble[7];
    locala.b = (d1 * (paramArrayOfDouble[8] * d1 * d1 * d1 * d1 * d1) + (d2 + d3 * d1 + d4 * d1 * d1 + d5 * d1 * d1 * d1 + d6 * d1 * d1 * d1 * d1 + d7 * d1 * d1 * d1 * d1 * d1));
    d1 = locala.a;
    if (parama.a < 0.0D)
    {
      i = -1;
      locala.a = (d1 * i);
      d1 = locala.b;
      if (parama.b >= 0.0D)
        break label212;
    }
    label212: for (int i = j; ; i = 1)
    {
      locala.b = (i * d1);
      return locala;
      i = 1;
      break;
    }
  }

  public static LatLng b(String paramString)
  {
    if ((paramString == null) || (paramString.length() <= 0))
      return null;
    a.clear();
    a.putString("strkey", paramString);
    JNITools.TransNodeStr2Pt(a);
    return a(new GeoPoint(a.getDouble("pty"), a.getDouble("ptx")));
  }

  public static Point b(LatLng paramLatLng)
  {
    if (paramLatLng == null)
      return null;
    return a((float)paramLatLng.longitude, (float)paramLatLng.latitude, "bd09ll");
  }

  public static List<LatLng> c(String paramString)
  {
    Object localObject = com.baidu.platform.comjni.tools.a.a(paramString);
    paramString = new ArrayList();
    if ((localObject == null) || (((com.baidu.mapapi.model.inner.a)localObject).d == null))
      return null;
    localObject = ((com.baidu.mapapi.model.inner.a)localObject).d;
    if (((ArrayList)localObject).size() > 0)
    {
      localObject = (ArrayList)((ArrayList)localObject).get(0);
      int i = 0;
      while (i < ((ArrayList)localObject).size())
      {
        Point localPoint = (Point)((ArrayList)localObject).get(i);
        paramString.add(a(new GeoPoint(localPoint.y / 100, localPoint.x / 100)));
        i += 1;
      }
    }
    return paramString;
  }

  public static List<List<LatLng>> d(String paramString)
  {
    paramString = com.baidu.platform.comjni.tools.a.a(paramString);
    if ((paramString == null) || (paramString.d == null))
      return null;
    Object localObject1 = paramString.d;
    paramString = new ArrayList();
    localObject1 = ((ArrayList)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (ArrayList)((Iterator)localObject1).next();
      ArrayList localArrayList = new ArrayList();
      localObject2 = ((ArrayList)localObject2).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        Point localPoint = (Point)((Iterator)localObject2).next();
        localArrayList.add(a(new GeoPoint(localPoint.y / 100, localPoint.x / 100)));
      }
      paramString.add(localArrayList);
    }
    return paramString;
  }

  static class a
  {
    double a;
    double b;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.util.b
 * JD-Core Version:    0.6.2
 */