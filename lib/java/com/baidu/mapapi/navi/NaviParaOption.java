package com.baidu.mapapi.navi;

import com.baidu.mapapi.model.LatLng;

public class NaviParaOption
{
  LatLng a;
  String b;
  LatLng c;
  String d;

  public NaviParaOption endName(String paramString)
  {
    this.d = paramString;
    return this;
  }

  public NaviParaOption endPoint(LatLng paramLatLng)
  {
    this.c = paramLatLng;
    return this;
  }

  public String getEndName()
  {
    return this.d;
  }

  public LatLng getEndPoint()
  {
    return this.c;
  }

  public String getStartName()
  {
    return this.b;
  }

  public LatLng getStartPoint()
  {
    return this.a;
  }

  public NaviParaOption startName(String paramString)
  {
    this.b = paramString;
    return this;
  }

  public NaviParaOption startPoint(LatLng paramLatLng)
  {
    this.a = paramLatLng;
    return this;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.navi.NaviParaOption
 * JD-Core Version:    0.6.2
 */