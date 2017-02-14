package cn.smssdk.framework;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import cn.smssdk.framework.utils.UIHandler;
import cn.smssdk.framework.utils.d;
import java.util.HashMap;

public class ShareSDKUIShell extends Activity
{
  private static HashMap<String, FakeActivity> executors = new HashMap();
  private FakeActivity executor;

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
    UIHandler.prepare();
    Object localObject = getIntent();
    String str = ((Intent)localObject).getStringExtra("launch_time");
    this.executor = ((FakeActivity)executors.remove(str));
    if (this.executor == null)
    {
      localObject = ((Intent)localObject).getScheme();
      this.executor = ((FakeActivity)executors.remove(localObject));
      if (this.executor == null)
      {
        d.c(new RuntimeException("Executor lost! launchTime = " + str));
        super.onCreate(paramBundle);
        finish();
        return;
      }
    }
    d.b("ShareSDKUIShell found executor: " + this.executor.getClass(), new Object[0]);
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
    super.setContentView(paramView);
    if (this.executor != null)
      this.executor.setContentView(paramView);
  }

  public void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    super.setContentView(paramView, paramLayoutParams);
    if (this.executor != null)
      this.executor.setContentView(paramView);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.framework.ShareSDKUIShell
 * JD-Core Version:    0.6.2
 */