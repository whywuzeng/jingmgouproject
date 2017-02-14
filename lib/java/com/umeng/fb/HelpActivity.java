package com.umeng.fb;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import com.umeng.fb.fragment.FeedbackFragment;
import com.umeng.fb.fragment.QuestionFragment;
import com.umeng.fb.model.Conversation;
import com.umeng.fb.res.b;
import com.umeng.fb.res.c;
import com.umeng.fb.res.e;
import com.umeng.fb.res.f;
import com.umeng.fb.widget.PagerSlidingTabStrip;

public class HelpActivity extends FragmentActivity
{
  private QuestionFragment a;
  private FeedbackFragment b;
  private PagerSlidingTabStrip c;
  private DisplayMetrics d;
  private Context e;

  private void a()
  {
    this.c.setShouldExpand(true);
    this.c.setDividerColor(0);
    this.c.setUnderlineHeight((int)TypedValue.applyDimension(1, 1.0F, this.d));
    this.c.setIndicatorHeight((int)TypedValue.applyDimension(1, 4.0F, this.d));
    this.c.setTextSize((int)TypedValue.applyDimension(2, 16.0F, this.d));
    int i = getResources().getColor(c.a(this.e));
    this.c.setIndicatorColor(i);
    this.c.setSelectedTextColor(i);
    this.c.setTabBackground(0);
  }

  @SuppressLint({"NewApi"})
  @TargetApi(11)
  protected void onCreate(Bundle paramBundle)
  {
    getWindow().setSoftInputMode(32);
    super.onCreate(paramBundle);
    if ((Build.VERSION.SDK_INT >= 11) && (getActionBar() != null))
      getActionBar().setDisplayHomeAsUpEnabled(true);
    setContentView(f.o(this));
    this.e = this;
    this.d = getResources().getDisplayMetrics();
    paramBundle = (ViewPager)findViewById(e.E(this.e));
    this.c = ((PagerSlidingTabStrip)findViewById(e.F(this.e)));
    paramBundle.setAdapter(new a(getSupportFragmentManager()));
    this.c.setViewPager(paramBundle);
    a();
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    return true;
  }

  protected void onNewIntent(Intent paramIntent)
  {
    this.b.refresh();
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default:
      return super.onOptionsItemSelected(paramMenuItem);
    case 16908332:
    }
    finish();
    return true;
  }

  protected void onStop()
  {
    super.onStop();
  }

  public class a extends FragmentPagerAdapter
  {
    private String[] b = HelpActivity.this.getResources().getStringArray(b.c(HelpActivity.this.getApplicationContext()));

    public a(FragmentManager arg2)
    {
      super();
    }

    public int getCount()
    {
      return this.b.length;
    }

    public Fragment getItem(int paramInt)
    {
      switch (paramInt)
      {
      default:
        return null;
      case 1:
        if (HelpActivity.a(HelpActivity.this) == null)
          HelpActivity.a(HelpActivity.this, new QuestionFragment());
        return HelpActivity.a(HelpActivity.this);
      case 0:
      }
      if (HelpActivity.b(HelpActivity.this) == null)
      {
        String str2 = HelpActivity.this.getIntent().getStringExtra("conversation_id");
        String str1 = str2;
        if (str2 == null)
          str1 = new FeedbackAgent(HelpActivity.c(HelpActivity.this)).getDefaultConversation().getId();
        HelpActivity.a(HelpActivity.this, FeedbackFragment.newInstance(str1));
      }
      return HelpActivity.b(HelpActivity.this);
    }

    public CharSequence getPageTitle(int paramInt)
    {
      return this.b[paramInt];
    }

    public Object instantiateItem(ViewGroup paramViewGroup, int paramInt)
    {
      return super.instantiateItem(paramViewGroup, paramInt);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.HelpActivity
 * JD-Core Version:    0.6.2
 */