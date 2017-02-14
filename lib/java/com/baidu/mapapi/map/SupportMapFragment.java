package com.baidu.mapapi.map;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SupportMapFragment extends Fragment
{
  private static final String a = SupportMapFragment.class.getSimpleName();
  private MapView b;
  private BaiduMapOptions c;

  public SupportMapFragment()
  {
  }

  private SupportMapFragment(BaiduMapOptions paramBaiduMapOptions)
  {
    this.c = paramBaiduMapOptions;
  }

  public static SupportMapFragment newInstance()
  {
    return new SupportMapFragment();
  }

  public static SupportMapFragment newInstance(BaiduMapOptions paramBaiduMapOptions)
  {
    return new SupportMapFragment(paramBaiduMapOptions);
  }

  public BaiduMap getBaiduMap()
  {
    if (this.b == null)
      return null;
    return this.b.getMap();
  }

  public MapView getMapView()
  {
    return this.b;
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
  }

  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.b = new MapView(getActivity(), this.c);
    return this.b;
  }

  public void onDestroy()
  {
    super.onDestroy();
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    this.b.onDestroy();
  }

  public void onDetach()
  {
    super.onDetach();
  }

  public void onPause()
  {
    super.onPause();
    this.b.onPause();
  }

  public void onResume()
  {
    super.onResume();
    this.b.onResume();
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
  }

  public void onStart()
  {
    super.onStart();
  }

  public void onStop()
  {
    super.onStop();
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
  }

  public void onViewStateRestored(Bundle paramBundle)
  {
    super.onViewStateRestored(paramBundle);
    if (paramBundle == null);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.map.SupportMapFragment
 * JD-Core Version:    0.6.2
 */