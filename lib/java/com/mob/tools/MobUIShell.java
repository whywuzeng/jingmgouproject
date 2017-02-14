package com.mob.tools;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.mob.tools.utils.Ln;
import java.util.HashMap;

public class MobUIShell extends Activity
{
  private static HashMap<String, FakeActivity> executors = new HashMap();
  private FakeActivity executor;

  static
  {
    Ln.d("===============================", new Object[0]);
    String str = "2015-04-28".replace("-0", "-").replace("-", ".");
    Ln.d("MobTools " + str, new Object[0]);
    Ln.d("===============================", new Object[0]);
  }

  public static String registerExecutor(FakeActivity paramFakeActivity)
  {
    return registerExecutor(String.valueOf(System.currentTimeMillis()), paramFakeActivity);
  }

  public static String registerExecutor(String paramString, FakeActivity paramFakeActivity)
  {
    executors.put(paramString, paramFakeActivity);
    return paramString;
  }

  public void finish()
  {
    if ((this.executor != null) && (this.executor.onFinish()))
      return;
    super.finish();
  }

  public FakeActivity getExecutor()
  {
    return this.executor;
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (this.executor != null)
      this.executor.onActivityResult(paramInt1, paramInt2, paramIntent);
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (this.executor != null)
      this.executor.onConfigurationChanged(paramConfiguration);
  }

  protected void onCreate(Bundle paramBundle)
  {
    Object localObject = getIntent();
    String str1 = ((Intent)localObject).getStringExtra("launch_time");
    String str2 = ((Intent)localObject).getStringExtra("executor_name");
    this.executor = ((FakeActivity)executors.remove(str1));
    if (this.executor == null)
    {
      localObject = ((Intent)localObject).getScheme();
      this.executor = ((FakeActivity)executors.remove(localObject));
      if (this.executor == null)
      {
        Ln.e(new RuntimeException("Executor lost! launchTime = " + str1 + ", executorName: " + str2));
        super.onCreate(paramBundle);
        finish();
        return;
      }
    }
    Ln.i("MobUIShell found executor: " + this.executor.getClass(), new Object[0]);
    this.executor.setActivity(this);
    super.onCreate(paramBundle);
    this.executor.onCreate();
  }

  protected void onDestroy()
  {
    if (this.executor != null)
    {
      this.executor.sendResult();
      this.executor.onDestroy();
    }
    super.onDestroy();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    boolean bool = false;
    if (this.executor != null)
      bool = this.executor.onKeyEvent(paramInt, paramKeyEvent);
    if (bool)
      return true;
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    boolean bool = false;
    if (this.executor != null)
      bool = this.executor.onKeyEvent(paramInt, paramKeyEvent);
    if (bool)
      return true;
    return super.onKeyUp(paramInt, paramKeyEvent);
  }

  protected void onNewIntent(Intent paramIntent)
  {
    if (this.executor == null)
    {
      super.onNewIntent(paramIntent);
      return;
    }
    this.executor.onNewIntent(paramIntent);
  }

  protected void onPause()
  {
    if (this.executor != null)
      this.executor.onPause();
    super.onPause();
  }

  protected void onRestart()
  {
    if (this.executor != null)
      this.executor.onRestart();
    super.onRestart();
  }

  protected void onResume()
  {
    if (this.executor != null)
      this.executor.onResume();
    super.onResume();
  }

  protected void onStart()
  {
    if (this.executor != null)
      this.executor.onStart();
    super.onStart();
  }

  protected void onStop()
  {
    if (this.executor != null)
      this.executor.onStop();
    super.onStop();
  }

  public void setContentView(int paramInt)
  {
    setContentView(LayoutInflater.from(this).inflate(paramInt, null));
  }

  public void setContentView(View paramView)
  {
    if (paramView == null);
    do
    {
      return;
      super.setContentView(paramView);
    }
    while (this.executor == null);
    this.executor.setContentView(paramView);
  }

  public void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    if (paramView == null);
    while (true)
    {
      return;
      if (paramLayoutParams == null)
        super.setContentView(paramView);
      while (this.executor != null)
      {
        this.executor.setContentView(paramView);
        return;
        super.setContentView(paramView, paramLayoutParams);
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.MobUIShell
 * JD-Core Version:    0.6.2
 */