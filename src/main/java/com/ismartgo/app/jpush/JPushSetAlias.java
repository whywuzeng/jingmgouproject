package com.ismartgo.app.jpush;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import java.util.HashSet;
import java.util.Set;

public class JPushSetAlias
{
  private static final int MSG_SET_ALIAS = 1001;
  public static String TAG = "JPush";
  Context context;
  private final TagAliasCallback mAliasCallback = new TagAliasCallback()
  {
    public void gotResult(int paramAnonymousInt, String paramAnonymousString, Set<String> paramAnonymousSet)
    {
      switch (paramAnonymousInt)
      {
      default:
        paramAnonymousString = "Failed with errorCode = " + paramAnonymousInt;
        Log.e(JPushSetAlias.TAG, paramAnonymousString);
        return;
      case 0:
        Log.i(JPushSetAlias.TAG, "Set tag and alias success");
        return;
      case 6002:
      }
      Log.i(JPushSetAlias.TAG, "Failed to set alias and tags due to timeout. Try again after 60s.");
      if (JPushUtil.isConnected(JPushSetAlias.this.context.getApplicationContext()))
      {
        JPushSetAlias.this.mHandler.sendMessageDelayed(JPushSetAlias.this.mHandler.obtainMessage(1001, paramAnonymousString), 60000L);
        return;
      }
      Log.i(JPushSetAlias.TAG, "No network");
    }
  };
  private final Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      super.handleMessage(paramAnonymousMessage);
      switch (paramAnonymousMessage.what)
      {
      default:
        Log.i(JPushSetAlias.TAG, "Unhandled msg - " + paramAnonymousMessage.what);
        return;
      case 1001:
      }
      Log.d(JPushSetAlias.TAG, "Set alias in handler. " + (String)paramAnonymousMessage.obj);
      String str = JPushSetAlias.this.context.getPackageName();
      HashSet localHashSet = new HashSet();
      localHashSet.add(str.substring(str.lastIndexOf(".") + 1, str.length()));
      JPushInterface.setAliasAndTags(JPushSetAlias.this.context.getApplicationContext(), (String)paramAnonymousMessage.obj, localHashSet, JPushSetAlias.this.mAliasCallback);
    }
  };

  public JPushSetAlias(Context paramContext)
  {
    this.context = paramContext;
  }

  private void setStyleBasic()
  {
    BasicPushNotificationBuilder localBasicPushNotificationBuilder = new BasicPushNotificationBuilder(this.context);
    localBasicPushNotificationBuilder.statusBarDrawable = 2130837758;
    localBasicPushNotificationBuilder.notificationFlags = 16;
    localBasicPushNotificationBuilder.notificationDefaults = 7;
    JPushInterface.setPushNotificationBuilder(Integer.valueOf(1), localBasicPushNotificationBuilder);
  }

  public void setAlias(String paramString)
  {
    if (TextUtils.isEmpty(paramString));
    while (!JPushUtil.isValidTagAndAlias(paramString))
      return;
    this.mHandler.sendMessage(this.mHandler.obtainMessage(1001, paramString));
    setStyleBasic();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.jpush.JPushSetAlias
 * JD-Core Version:    0.6.2
 */