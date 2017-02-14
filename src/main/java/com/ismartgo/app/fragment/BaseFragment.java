package com.ismartgo.app.fragment;

import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment
{
  protected boolean isVisible;

  protected abstract void lazyLoad();

  protected void onInvisible()
  {
  }

  protected void onVisible()
  {
    lazyLoad();
  }

  public void setUserVisibleHint(boolean paramBoolean)
  {
    super.setUserVisibleHint(paramBoolean);
    if (getUserVisibleHint())
    {
      this.isVisible = true;
      onVisible();
      return;
    }
    this.isVisible = false;
    onInvisible();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.fragment.BaseFragment
 * JD-Core Version:    0.6.2
 */