package com.ismartgo.app.andbase.util;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AbSampleFragment extends DialogFragment
{
  private View mContentView;

  public static AbSampleFragment newInstance()
  {
    return new AbSampleFragment();
  }

  public View getContentView()
  {
    return this.mContentView;
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return this.mContentView;
  }

  public void setContentView(View paramView)
  {
    this.mContentView = paramView;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.andbase.util.AbSampleFragment
 * JD-Core Version:    0.6.2
 */