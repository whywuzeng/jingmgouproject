package com.ismartgo.app.activity;

import android.app.Application;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ab.view.ioc.AbIocSelect;
import com.ab.view.ioc.AbIocView;
import com.ab.view.titlebar.AbBottomBar;
import com.ab.view.titlebar.AbTitleBar;
import com.ismartgo.app.andbase.util.AbIocEventListener;
import com.umeng.analytics.MobclickAgent;

import java.lang.reflect.Field;

public abstract class AbActivity extends FragmentActivity
{
  public Application abApplication = null;
  public RelativeLayout ab_base = null;
  protected RelativeLayout contentLayout = null;
  private AbBottomBar mAbBottomBar = null;
  private AbTitleBar mAbTitleBar = null;
  public LayoutInflater mInflater;

  private void initIocView()
  {
    int i = 0;
    Field[] arrayOfField = getClass().getDeclaredFields();
    int j;
    if ((arrayOfField != null) && (arrayOfField.length > 0))
      j = arrayOfField.length;
    while (true)
    {
      if (i >= j)
        return;
      Field localField = arrayOfField[i];
      try
      {
        localField.setAccessible(true);
        if (localField.get(this) == null)
        {
          Object localObject = (AbIocView)localField.getAnnotation(AbIocView.class);
          if (localObject != null)
          {
            localField.set(this, findViewById(((AbIocView)localObject).id()));
            setListener(localField, ((AbIocView)localObject).click(), 0);
            setListener(localField, ((AbIocView)localObject).longClick(), 1);
            setListener(localField, ((AbIocView)localObject).itemClick(), 2);
            setListener(localField, ((AbIocView)localObject).itemLongClick(), 3);
            localObject = ((AbIocView)localObject).select();
            if (!TextUtils.isEmpty(((AbIocSelect)localObject).selected()))
              setViewSelectListener(localField, ((AbIocSelect)localObject).selected(), ((AbIocSelect)localObject).noSelected());
          }
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      i += 1;
    }
  }

  private void setListener(Field paramField, String paramString, int paramInt)
    throws Exception
  {
    if ((paramString == null) || (paramString.trim().length() == 0));
    do
    {
      do
      {
        do
        {
          do
          {
            return;
            paramField = paramField.get(this);
            switch (paramInt)
            {
            default:
              return;
            case 0:
            case 2:
            case 1:
            case 3:
            }
          }
          while (!(paramField instanceof View));
          ((View)paramField).setOnClickListener(new AbIocEventListener(this).click(paramString));
          return;
        }
        while (!(paramField instanceof AbsListView));
        ((AbsListView)paramField).setOnItemClickListener(new AbIocEventListener(this).itemClick(paramString));
        return;
      }
      while (!(paramField instanceof View));
      ((View)paramField).setOnLongClickListener(new AbIocEventListener(this).longClick(paramString));
      return;
    }
    while (!(paramField instanceof AbsListView));
    ((AbsListView)paramField).setOnItemLongClickListener(new AbIocEventListener(this).itemLongClick(paramString));
  }

  private void setViewSelectListener(Field paramField, String paramString1, String paramString2)
    throws Exception
  {
    paramField = paramField.get(this);
    if ((paramField instanceof View))
      ((AbsListView)paramField).setOnItemSelectedListener(new AbIocEventListener(this).select(paramString1).noSelect(paramString2));
  }

  public void finish()
  {
    super.finish();
  }

  public AbBottomBar getBottomBar()
  {
    return this.mAbBottomBar;
  }

  public AbTitleBar getTitleBar()
  {
    this.mAbTitleBar.setVisibility(0);
    return this.mAbTitleBar;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    //Ab 手写布局
    requestWindowFeature(1);
    this.mInflater = LayoutInflater.from(this);
    this.mAbTitleBar = new AbTitleBar(this);
    this.ab_base = new RelativeLayout(this);
    this.ab_base.setBackgroundColor(Color.rgb(255, 255, 255));
    this.contentLayout = new RelativeLayout(this);
    this.contentLayout.setPadding(0, 0, 0, 0);
    this.mAbBottomBar = new AbBottomBar(this);
    this.ab_base.addView(this.mAbTitleBar, new LinearLayout.LayoutParams(-1, -2));
    this.mAbTitleBar.setVisibility(8);
    paramBundle = new RelativeLayout.LayoutParams(-1, -2);
    paramBundle.addRule(12, -1);
    this.ab_base.addView(this.mAbBottomBar, paramBundle);
    paramBundle = new RelativeLayout.LayoutParams(-1, -2);
    paramBundle.addRule(3, this.mAbTitleBar.getId());
    paramBundle.addRule(2, this.mAbBottomBar.getId());
    this.ab_base.addView(this.contentLayout, paramBundle);
    this.abApplication = getApplication();
    setContentView(this.ab_base, new LinearLayout.LayoutParams(-1, -1));
  }

  protected void onPause()
  {
    super.onPause();
    MobclickAgent.onPause(this);
  }

  protected void onResume()
  {
    super.onResume();
    MobclickAgent.onResume(this);
  }

  public void setAbContentView(int paramInt)
  {
    setAbContentView(this.mInflater.inflate(paramInt, null));
  }

  public void setAbContentView(View paramView)
  {
    this.contentLayout.removeAllViews();
    this.contentLayout.addView(paramView, new LinearLayout.LayoutParams(-1, -1));
    initIocView();
  }

  public void setContentView(int paramInt)
  {
    super.setContentView(paramInt);
    initIocView();
  }

  public void setContentView(View paramView)
  {
    super.setContentView(paramView);
    initIocView();
  }

  public void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    super.setContentView(paramView, paramLayoutParams);
    initIocView();
  }

  public void setTitleBarOverlay(boolean paramBoolean)
  {
    this.ab_base.removeAllViews();
    if (paramBoolean)
    {
      localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
      localLayoutParams.addRule(2, this.mAbBottomBar.getId());
      this.ab_base.addView(this.contentLayout, localLayoutParams);
      localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
      localLayoutParams.addRule(10, -1);
      this.ab_base.addView(this.mAbTitleBar, localLayoutParams);
      localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
      localLayoutParams.addRule(12, -1);
      this.ab_base.addView(this.mAbBottomBar, localLayoutParams);
      return;
    }
    this.ab_base.addView(this.mAbTitleBar, new LinearLayout.LayoutParams(-1, -2));
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams.addRule(12, -1);
    this.ab_base.addView(this.mAbBottomBar, localLayoutParams);
    localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams.addRule(3, this.mAbTitleBar.getId());
    localLayoutParams.addRule(2, this.mAbBottomBar.getId());
    this.ab_base.addView(this.contentLayout, localLayoutParams);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.activity.AbActivity
 * JD-Core Version:    0.6.2
 */