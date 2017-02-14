package com.example.android.bitmapfun.util;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class RetainFragment extends Fragment
{
  private static final String TAG = "RetainFragment";
  private Object mObject;

  public static RetainFragment findOrCreateRetainFragment(FragmentManager paramFragmentManager)
  {
    RetainFragment localRetainFragment2 = (RetainFragment)paramFragmentManager.findFragmentByTag("RetainFragment");
    RetainFragment localRetainFragment1 = localRetainFragment2;
    if (localRetainFragment2 == null)
    {
      localRetainFragment1 = new RetainFragment();
      paramFragmentManager.beginTransaction().add(localRetainFragment1, "RetainFragment").commit();
    }
    return localRetainFragment1;
  }

  public Object getObject()
  {
    return this.mObject;
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setRetainInstance(true);
  }

  public void setObject(Object paramObject)
  {
    this.mObject = paramObject;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.example.android.bitmapfun.util.RetainFragment
 * JD-Core Version:    0.6.2
 */