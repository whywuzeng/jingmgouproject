package cn.smssdk.framework;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import cn.smssdk.framework.utils.UIHandler;
import java.util.HashMap;

public class FakeActivity
{
  private static Class<? extends ShareSDKUIShell> shell;
  protected Activity activity;
  private View contentView;
  private HashMap<String, Object> result;
  private FakeActivity resultReceiver;

  public static void setShell(Class<? extends ShareSDKUIShell> paramClass)
  {
    shell = paramClass;
  }

  public final void finish()
  {
    this.activity.finish();
  }

  public View getContentView()
  {
    return this.contentView;
  }

  public Context getContext()
  {
    return this.activity;
  }

  public FakeActivity getParent()
  {
    return this.resultReceiver;
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
  }

  public void onCreate()
  {
  }

  public void onDestroy()
  {
  }

  public boolean onFinish()
  {
    return false;
  }

  public boolean onKeyEvent(int paramInt, KeyEvent paramKeyEvent)
  {
    return false;
  }

  protected void onNewIntent(Intent paramIntent)
  {
  }

  public void onPause()
  {
  }

  public void onRestart()
  {
  }

  public void onResult(HashMap<String, Object> paramHashMap)
  {
  }

  public void onResume()
  {
  }

  public void onStart()
  {
  }

  public void onStop()
  {
  }

  public void runOnUIThread(Runnable paramRunnable)
  {
    UIHandler.sendEmptyMessage(0, new b(this, paramRunnable));
  }

  public void runOnUIThread(Runnable paramRunnable, long paramLong)
  {
    UIHandler.sendEmptyMessageDelayed(0, paramLong, new c(this, paramRunnable));
  }

  void sendResult()
  {
    if (this.resultReceiver != null)
      this.resultReceiver.onResult(this.result);
  }

  public void setActivity(Activity paramActivity)
  {
    this.activity = paramActivity;
  }

  final void setContentView(View paramView)
  {
    this.contentView = paramView;
  }

  public final void setResult(HashMap<String, Object> paramHashMap)
  {
    this.result = paramHashMap;
  }

  public void show(Context paramContext, Intent paramIntent)
  {
    showForResult(paramContext, paramIntent, null);
  }

  public void showForResult(Context paramContext, Intent paramIntent, FakeActivity paramFakeActivity)
  {
    this.resultReceiver = paramFakeActivity;
    Message localMessage = new Message();
    if (shell == null);
    for (paramFakeActivity = ShareSDKUIShell.class; ; paramFakeActivity = shell)
    {
      paramFakeActivity = new Intent(paramContext, paramFakeActivity);
      paramFakeActivity.putExtra("launch_time", ShareSDKUIShell.registerExecutor(this));
      if (paramIntent != null)
        paramFakeActivity.putExtras(paramIntent);
      localMessage.obj = new Object[] { paramContext, paramFakeActivity };
      UIHandler.sendMessage(localMessage, new a(this));
      return;
    }
  }

  public void startActivity(Intent paramIntent)
  {
    startActivityForResult(paramIntent, -1);
  }

  public void startActivityForResult(Intent paramIntent, int paramInt)
  {
    this.activity.startActivityForResult(paramIntent, paramInt);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.framework.FakeActivity
 * JD-Core Version:    0.6.2
 */