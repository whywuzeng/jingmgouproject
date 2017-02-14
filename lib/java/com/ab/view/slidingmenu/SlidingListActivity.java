package com.ab.view.slidingmenu;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;

public class SlidingListActivity extends ListActivity
  implements SlidingActivityBase
{
  private SlidingActivityHelper mHelper;

  public View findViewById(int paramInt)
  {
    View localView = super.findViewById(paramInt);
    if (localView != null)
      return localView;
    return this.mHelper.findViewById(paramInt);
  }

  public SlidingMenu getSlidingMenu()
  {
    return this.mHelper.getSlidingMenu();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mHelper = new SlidingActivityHelper(this);
    this.mHelper.onCreate(paramBundle);
    paramBundle = new ListView(this);
    paramBundle.setId(16908298);
    setContentView(paramBundle);
  }

  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    boolean bool = this.mHelper.onKeyUp(paramInt, paramKeyEvent);
    if (bool)
      return bool;
    return super.onKeyUp(paramInt, paramKeyEvent);
  }

  public void onPostCreate(Bundle paramBundle)
  {
    super.onPostCreate(paramBundle);
    this.mHelper.onPostCreate(paramBundle);
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    this.mHelper.onSaveInstanceState(paramBundle);
  }

  public void setBehindContentView(int paramInt)
  {
    setBehindContentView(getLayoutInflater().inflate(paramInt, null));
  }

  public void setBehindContentView(View paramView)
  {
    setBehindContentView(paramView, new ViewGroup.LayoutParams(-1, -1));
  }

  public void setBehindContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    this.mHelper.setBehindContentView(paramView, paramLayoutParams);
  }

  public void setContentView(int paramInt)
  {
    setContentView(getLayoutInflater().inflate(paramInt, null));
  }

  public void setContentView(View paramView)
  {
    setContentView(paramView, new ViewGroup.LayoutParams(-1, -1));
  }

  public void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    super.setContentView(paramView, paramLayoutParams);
    this.mHelper.registerAboveContentView(paramView, paramLayoutParams);
  }

  public void setSlidingActionBarEnabled(boolean paramBoolean)
  {
    this.mHelper.setSlidingActionBarEnabled(paramBoolean);
  }

  public void showContent()
  {
    this.mHelper.showContent();
  }

  public void showMenu()
  {
    this.mHelper.showMenu();
  }

  public void showSecondaryMenu()
  {
    this.mHelper.showSecondaryMenu();
  }

  public void toggle()
  {
    this.mHelper.toggle();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.slidingmenu.SlidingListActivity
 * JD-Core Version:    0.6.2
 */