package com.byl.datepicker.wheelview.adapter;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

public abstract interface WheelViewAdapter
{
  public abstract View getEmptyItem(View paramView, ViewGroup paramViewGroup);

  public abstract View getItem(int paramInt, View paramView, ViewGroup paramViewGroup);

  public abstract int getItemsCount();

  public abstract void registerDataSetObserver(DataSetObserver paramDataSetObserver);

  public abstract void unregisterDataSetObserver(DataSetObserver paramDataSetObserver);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.byl.datepicker.wheelview.adapter.WheelViewAdapter
 * JD-Core Version:    0.6.2
 */