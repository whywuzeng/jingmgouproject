package com.umeng.fb;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import com.umeng.fb.fragment.FeedbackFragment;
import com.umeng.fb.model.Conversation;
import com.umeng.fb.res.e;
import com.umeng.fb.res.f;

public class ConversationActivity extends FragmentActivity
{
  private final String a = ConversationActivity.class.getName();
  private FeedbackFragment b;

  @SuppressLint({"NewApi"})
  @TargetApi(11)
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if ((Build.VERSION.SDK_INT >= 11) && (getActionBar() != null))
      getActionBar().setDisplayHomeAsUpEnabled(true);
    setContentView(f.a(this));
    if (paramBundle == null)
    {
      String str = getIntent().getStringExtra("conversation_id");
      paramBundle = str;
      if (str == null)
        paramBundle = new FeedbackAgent(this).getDefaultConversation().getId();
      this.b = FeedbackFragment.newInstance(paramBundle);
      getSupportFragmentManager().beginTransaction().add(e.r(this), this.b).commit();
    }
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
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.ConversationActivity
 * JD-Core Version:    0.6.2
 */