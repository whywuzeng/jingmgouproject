package com.ab.view.slidingmenu;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class SlidingActivityHelper
{
  private Activity mActivity;
  private boolean mBroadcasting = false;
  private boolean mEnableSlide = true;
  private boolean mOnPostCreateCalled = false;
  private SlidingMenu mSlidingMenu;
  private View mViewAbove;
  private View mViewBehind;

  public SlidingActivityHelper(Activity paramActivity)
  {
    this.mActivity = paramActivity;
  }

  public View findViewById(int paramInt)
  {
    if (this.mSlidingMenu != null)
    {
      View localView = this.mSlidingMenu.findViewById(paramInt);
      if (localView != null)
        return localView;
    }
    return null;
  }

  public SlidingMenu getSlidingMenu()
  {
    return this.mSlidingMenu;
  }

  public void onCreate(Bundle paramBundle)
  {
    this.mSlidingMenu = new SlidingMenu(this.mActivity);
  }

  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (this.mSlidingMenu.isMenuShowing()))
    {
      showContent();
      return true;
    }
    return false;
  }

  public void onPostCreate(Bundle paramBundle)
  {
    int i = 1;
    if ((this.mViewBehind == null) || (this.mViewAbove == null))
      throw new IllegalStateException("Both setBehindContentView must be called in onCreate in addition to setContentView.");
    this.mOnPostCreateCalled = true;
    SlidingMenu localSlidingMenu = this.mSlidingMenu;
    Activity localActivity = this.mActivity;
    if (this.mEnableSlide)
      i = 0;
    localSlidingMenu.attachToActivity(localActivity, i);
    final boolean bool1;
    if (paramBundle != null)
      bool1 = paramBundle.getBoolean("SlidingActivityHelper.open");
    for (final boolean bool2 = paramBundle.getBoolean("SlidingActivityHelper.secondary"); ; bool2 = false)
    {
      new Handler().post(new Runnable()
      {
        public void run()
        {
          if (bool1)
          {
            if (bool2)
            {
              SlidingActivityHelper.this.mSlidingMenu.showSecondaryMenu(false);
              return;
            }
            SlidingActivityHelper.this.mSlidingMenu.showMenu(false);
            return;
          }
          SlidingActivityHelper.this.mSlidingMenu.showContent(false);
        }
      });
      return;
      bool1 = false;
    }
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    paramBundle.putBoolean("SlidingActivityHelper.open", this.mSlidingMenu.isMenuShowing());
    paramBundle.putBoolean("SlidingActivityHelper.secondary", this.mSlidingMenu.isSecondaryMenuShowing());
  }

  public void registerAboveContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    if (!this.mBroadcasting)
      this.mViewAbove = paramView;
  }

  public void setBehindContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    this.mViewBehind = paramView;
    this.mSlidingMenu.setMenu(this.mViewBehind);
  }

  public void setContentView(View paramView)
  {
    this.mBroadcasting = true;
    this.mActivity.setContentView(paramView);
  }

  public void setSlidingActionBarEnabled(boolean paramBoolean)
  {
    if (this.mOnPostCreateCalled)
      throw new IllegalStateException("enableSlidingActionBar must be called in onCreate.");
    this.mEnableSlide = paramBoolean;
  }

  public void showContent()
  {
    this.mSlidingMenu.showContent();
  }

  public void showMenu()
  {
    this.mSlidingMenu.showMenu();
  }

  public void showSecondaryMenu()
  {
    this.mSlidingMenu.showSecondaryMenu();
  }

  public void toggle()
  {
    this.mSlidingMenu.toggle();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.slidingmenu.SlidingActivityHelper
 * JD-Core Version:    0.6.2
 */