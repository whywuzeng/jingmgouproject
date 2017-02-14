package com.ismartgo.app.jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import cn.jpush.android.api.JPushInterface;
import com.ismartgo.app.activity.HomeActivity;
import com.ismartgo.app.activity.WebViewActivity;
import com.ismartgo.app.grid.utils.Helper;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class JPushReceiver extends BroadcastReceiver
{
  private static final String TAG = "JPush";
  public static boolean isAppRunning = false;
  private static StringBuilder sb;
  private boolean isLauncherApp;
  private String url;

  private static String printBundle(Bundle paramBundle)
  {
    sb = new StringBuilder();
    Iterator localIterator = paramBundle.keySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return sb.toString();
      String str = (String)localIterator.next();
      if (str.equals(JPushInterface.EXTRA_NOTIFICATION_ID))
        sb.append("\nkey:" + str + ", value:" + paramBundle.getInt(str) + "--int");
      else if (str.equals(JPushInterface.EXTRA_CONNECTION_CHANGE))
        sb.append("\nkey:" + str + ", value:" + paramBundle.getBoolean(str) + "--boolean");
      else
        sb.append("\nkey:" + str + ", value:" + paramBundle.getString(str) + "--str");
    }
  }

  private void processCustomMessage(Context paramContext, Bundle paramBundle)
  {
    Object localObject;
    String str;
    if (HomeActivity.isForeground)
    {
      localObject = paramBundle.getString(JPushInterface.EXTRA_MESSAGE);
      str = paramBundle.getString(JPushInterface.EXTRA_EXTRA);
      paramBundle = new Intent("com.ismartgo.app.MESSAGE_RECEIVED_ACTION");
      paramBundle.putExtra("message", (String)localObject);
      if (JPushUtil.isEmpty(str));
    }
    try
    {
      localObject = new JSONObject(str);
      if ((localObject != null) && (((JSONObject)localObject).length() > 0))
        paramBundle.putExtra("extras", str);
      label79: paramContext.sendBroadcast(paramBundle);
      return;
    }
    catch (JSONException localJSONException)
    {
      break label79;
    }
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Bundle localBundle = paramIntent.getExtras();
    Log.d("JPush", "[MyReceiver] onReceive - " + paramIntent.getAction() + ", extras: " + printBundle(localBundle));
    if (JPushInterface.ACTION_REGISTRATION_ID.equals(paramIntent.getAction()))
    {
      paramContext = localBundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
      Log.d("JPush", "[MyReceiver] 接收Registration Id : " + paramContext);
    }
    while (true)
    {
      return;
      if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(paramIntent.getAction()))
      {
        Log.d("JPush", "[MyReceiver] 接收到推送下来的自定义消息: " + localBundle.getString(JPushInterface.EXTRA_MESSAGE));
        return;
      }
      if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(paramIntent.getAction()))
      {
        Log.d("JPush", "[MyReceiver] 接收到推送下来的通知");
        int i = localBundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
        Log.d("JPush", "[MyReceiver] 接收到推送下来的通知的ID: " + i);
        return;
      }
      if (!JPushInterface.ACTION_NOTIFICATION_OPENED.equals(paramIntent.getAction()))
        break label409;
      Log.d("JPush", "[MyReceiver] 用户点击打开了通知");
      if (localBundle == null)
        break label384;
      if (localBundle.getString(JPushInterface.EXTRA_EXTRA) != null)
      {
        paramIntent = localBundle.getString(JPushInterface.EXTRA_EXTRA);
        try
        {
          this.url = new JSONObject(paramIntent).optString("url");
          Log.i("URL", "接收到的地址: " + this.url);
          if (this.url != null)
            if (Helper.isAppAlive(paramContext))
            {
              Log.i("NotificationReceiver", "the app process is alive");
              paramIntent = new Intent(paramContext, WebViewActivity.class);
              paramIntent.setFlags(268435456);
              paramIntent.putExtra("url", this.url);
              paramContext.startActivity(paramIntent);
              return;
            }
        }
        catch (JSONException paramContext)
        {
          Log.i("URL", "接收到的地址出现异常");
          paramContext.printStackTrace();
          return;
        }
      }
    }
    Log.i("NotificationReceiver", "the app process is dead");
    paramIntent = paramContext.getPackageManager().getLaunchIntentForPackage(paramContext.getPackageName());
    paramIntent.setFlags(270532608);
    com.ismartgo.app.common.CommonUtils.JPUSH_EXTRA = this.url;
    paramContext.startActivity(paramIntent);
    return;
    label384: paramIntent = paramContext.getPackageManager().getLaunchIntentForPackage(paramContext.getPackageName());
    paramIntent.setFlags(270532608);
    paramContext.startActivity(paramIntent);
    return;
    label409: if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(paramIntent.getAction()))
    {
      Log.d("JPush", "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + localBundle.getString(JPushInterface.EXTRA_EXTRA));
      return;
    }
    if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(paramIntent.getAction()))
    {
      boolean bool = paramIntent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
      Log.w("JPush", "[MyReceiver]" + paramIntent.getAction() + " connected state change to " + bool);
      return;
    }
    Log.d("JPush", "[MyReceiver] Unhandled intent - " + paramIntent.getAction());
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.jpush.JPushReceiver
 * JD-Core Version:    0.6.2
 */