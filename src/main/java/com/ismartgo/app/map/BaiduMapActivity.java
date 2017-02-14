package com.ismartgo.app.map;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.ab.view.ioc.AbIocView;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus.Builder;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.ismartgo.app.activity.BaseActivity;
import com.ismartgo.app.tools.StringUtils;
import com.umeng.analytics.MobclickAgent;

@SuppressLint({"InflateParams"})
public class BaiduMapActivity extends BaseActivity
{
  BitmapDescriptor bitmap;

  @AbIocView(id=2131231082)
  private ImageView imgv;
  private double lati;
  private double lon;
  BaiduMap mBaiduMap;
  BitmapDescriptor mCurrentMarker;
  private InfoWindow mInfoWindow;
  public LocationClient mLocationClient = null;
  MapView mMapView;
  View mPopView;
  LayoutInflater popLayout;
  String shopAddress;
  String shopName;
  private TextView tv_left;
  private ImageView tv_right;
  private TextView tv_title;

  public void addOverlay(double paramDouble1, double paramDouble2)
  {
    Object localObject1 = new LatLng(paramDouble2, paramDouble1);
    Object localObject2 = BitmapDescriptorFactory.fromResource(2130837780);
    localObject2 = new MarkerOptions().position((LatLng)localObject1).icon((BitmapDescriptor)localObject2);
    setPopTips(paramDouble1, paramDouble2, this.shopName, this.shopAddress);
    localObject1 = MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().target((LatLng)localObject1).zoom(5.0F).build());
    this.mBaiduMap.addOverlay((OverlayOptions)localObject2);
    this.mBaiduMap.setMapStatus((MapStatusUpdate)localObject1);
  }

  public void getExtrasValue()
  {
    this.lon = getIntent().getDoubleExtra("storeLon", 0.0D);
    this.lati = getIntent().getDoubleExtra("storeLat", 0.0D);
    this.shopName = getIntent().getStringExtra("shopName");
    this.shopAddress = getIntent().getStringExtra("storeAddress");
    Log.i("map", this.lon + "--" + this.lati + "--" + this.shopName + "--" + this.shopAddress);
  }

  public void initView()
  {
    initTitleBar();
    this.tv_left = ((TextView)findViewById(2131231000));
    this.tv_left.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        BaiduMapActivity.this.finish();
      }
    });
    this.tv_title = ((TextView)findViewById(2131231001));
    this.tv_title.setText("商店地址");
    this.tv_right = ((ImageView)findViewById(2131231002));
    this.tv_right.setVisibility(4);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    SDKInitializer.initialize(getApplicationContext());
    setContentView(2130903104);
    initView();
    getExtrasValue();
    this.mMapView = ((MapView)findViewById(2131231086));
    this.mBaiduMap = this.mMapView.getMap();
    this.mBaiduMap.setMyLocationEnabled(true);
    this.mBaiduMap.setMapType(1);
    paramBundle = new LatLng(this.lati, this.lon);
    addOverlay(this.lon, this.lati);
    paramBundle = MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().target(paramBundle).zoom(18.0F).build());
    this.mBaiduMap.setMapStatus(paramBundle);
  }

  protected void onDestroy()
  {
    super.onDestroy();
    this.mMapView.onDestroy();
  }

  protected void onPause()
  {
    super.onPause();
    this.mMapView.onPause();
    MobclickAgent.onPause(this);
  }

  protected void onResume()
  {
    super.onResume();
    this.mMapView.onResume();
    MobclickAgent.onResume(this);
  }

  public void setPopTips(double paramDouble1, double paramDouble2, String paramString1, String paramString2)
  {
    LatLng localLatLng = new LatLng(paramDouble2, paramDouble1);
    View localView = LayoutInflater.from(getApplicationContext()).inflate(2130903181, null);
    ((TextView)localView.findViewById(2131231288)).setText(StringUtils.StringFilter(StringUtils.ToDBC(paramString1)));
    paramString2 = StringUtils.StringFilter(StringUtils.ToDBC(paramString2));
    paramString1 = paramString2;
    if (paramString2.length() > 15)
      paramString1 = paramString2.substring(0, 15) + "\n" + paramString2.substring(15);
    ((TextView)localView.findViewById(2131231289)).setText(paramString1);
    this.mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(localView), localLatLng, -BitmapFactory.decodeResource(getResources(), 2130837780).getHeight(), null);
    this.mBaiduMap.showInfoWindow(this.mInfoWindow);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.map.BaiduMapActivity
 * JD-Core Version:    0.6.2
 */