package cn.jpush.android.api;

import android.app.Notification;
import java.util.Map;

public abstract interface PushNotificationBuilder
{
  public abstract Notification a(String paramString, Map<String, String> paramMap);

  public abstract String a();
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.api.PushNotificationBuilder
 * JD-Core Version:    0.6.2
 */