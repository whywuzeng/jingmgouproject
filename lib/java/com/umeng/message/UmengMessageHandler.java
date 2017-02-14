package com.umeng.message;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationCompat.Style;
import android.text.TextUtils;
import com.umeng.common.message.Log;
import com.umeng.common.message.c;
import com.umeng.message.entity.UMessage;
import com.umeng.message.proguard.g;
import com.umeng.message.proguard.i;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import org.json.JSONObject;

public class UmengMessageHandler
  implements UHandler
{
  private static int a = 0;
  private static final String b = UmengMessageHandler.class.getName();
  private static Date c;
  private static String d = "9999999999999";
  private static final String f = "umeng_push_notification_default_large_icon";
  private static final String g = "umeng_push_notification_default_small_icon";
  private static final String h = "umeng_push_notification_default_sound";
  private UMessage e = null;

  @SuppressLint({"NewApi", "Wakelock"})
  private void a(final Context paramContext)
  {
    try
    {
      paramContext = (PowerManager)paramContext.getSystemService("power");
      boolean bool = false;
      if (Build.VERSION.SDK_INT >= 7)
        bool = new Object()
        {
          boolean a()
          {
            return paramContext.isScreenOn();
          }
        }
        .a();
      while (true)
      {
        Log.c(b, "screen on................................." + bool);
        if (bool)
          break;
        paramContext.newWakeLock(805306374, "MyLock").acquire(10000L);
        return;
        Log.c(b, "android os version < 7, skip checking screen on status");
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }

  private void a(Context paramContext, Notification paramNotification, boolean paramBoolean, UMessage paramUMessage)
  {
    while (true)
    {
      int j;
      try
      {
        NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
        j = 20100401;
        try
        {
          if (!PushAgent.getInstance(paramContext).getMergeNotificaiton())
          {
            i = new Random().nextInt();
            if (((true == paramBoolean) || (!PushAgent.getInstance(paramContext).getMergeNotificaiton())) && ((!g.c(paramContext)) || (!g.b(paramContext)) || (PushAgent.getInstance(paramContext).getNotificationOnForeground())))
            {
              localNotificationManager.notify(i, paramNotification);
              UTrack.getInstance(paramContext).trackMsgDisplay(paramUMessage);
            }
            if (((true == paramBoolean) || (!PushAgent.getInstance(paramContext).getMergeNotificaiton())) && (g.c(paramContext)) && (g.b(paramContext)) && (!PushAgent.getInstance(paramContext).getNotificationOnForeground()))
            {
              UTrack.getInstance(paramContext).setClearPrevMessage(false);
              UTrack.getInstance(paramContext).trackMsgDismissed(paramUMessage);
            }
            return;
          }
          if (true == paramBoolean)
          {
            if ((g.c(paramContext)) && (g.b(paramContext)))
            {
              i = j;
              if (!PushAgent.getInstance(paramContext).getNotificationOnForeground())
                continue;
            }
            localNotificationManager.cancel(20100401);
            Log.c(b, "prevMessage=" + this.e);
            UTrack.getInstance(paramContext).setClearPrevMessage(false);
            UTrack.getInstance(paramContext).trackMsgDismissed(this.e);
            this.e = paramUMessage;
            i = j;
            continue;
          }
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          i = j;
          continue;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return;
      }
      UTrack.getInstance(paramContext).setClearPrevMessage(false);
      UTrack.getInstance(paramContext).trackMsgDismissed(paramUMessage);
      int i = j;
    }
  }

  private boolean a(Context paramContext, NotificationCompat.Builder paramBuilder, UMessage paramUMessage)
  {
    int i = getSmallIconId(paramContext, paramUMessage);
    paramContext = getLargeIcon(paramContext, paramUMessage);
    if (i < 0)
      return false;
    paramBuilder.setSmallIcon(i);
    if (paramContext != null)
      paramBuilder.setLargeIcon(paramContext);
    return true;
  }

  public void dealWithCustomMessage(Context paramContext, UMessage paramUMessage)
  {
  }

  public void dealWithNotificationMessage(Context paramContext, UMessage paramUMessage)
  {
    Log.c(b, "notify: " + paramUMessage.getRaw().toString());
    if ((paramUMessage.hasResourceFromInternet()) && (!MessageSharedPrefs.getInstance(paramContext).b(paramUMessage.msg_id)) && (startDownloadResourceService(paramContext, paramUMessage)))
      return;
    Object localObject1 = MessageSharedPrefs.getInstance(paramContext).j();
    label85: Object localObject2;
    label140: boolean bool;
    if ("".equals(localObject1))
    {
      localObject1 = "";
      if ((paramUMessage.msg_id == null) || (22 != paramUMessage.msg_id.length()) || (!paramUMessage.msg_id.startsWith("u")))
        break label416;
      MessageSharedPrefs.getInstance(paramContext).e(paramUMessage.msg_id);
      localObject2 = paramUMessage.msg_id.substring(7, 20);
      if (!"".equals(localObject1))
        break label424;
      bool = true;
    }
    while (true)
    {
      MessageSharedPrefs.getInstance(paramContext).d(paramUMessage.msg_id);
      localObject2 = getNotification(paramContext, paramUMessage);
      if ((localObject2 != null) && (((Notification)localObject2).icon == 0))
      {
        i = getSmallIconId(paramContext, paramUMessage);
        if (i <= 0)
          break;
        ((Notification)localObject2).icon = i;
      }
      localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = new NotificationCompat.Builder(paramContext);
        if (!a(paramContext, (NotificationCompat.Builder)localObject1, paramUMessage))
          break;
        ((NotificationCompat.Builder)localObject1).setContentTitle(paramUMessage.title).setContentText(paramUMessage.text).setTicker(paramUMessage.ticker).setAutoCancel(true);
        localObject2 = new NotificationCompat.BigTextStyle();
        ((NotificationCompat.BigTextStyle)localObject2).setBigContentTitle(paramUMessage.title);
        ((NotificationCompat.BigTextStyle)localObject2).bigText(paramUMessage.text);
        ((NotificationCompat.Builder)localObject1).setStyle((NotificationCompat.Style)localObject2);
        localObject1 = ((NotificationCompat.Builder)localObject1).build();
      }
      localObject2 = getClickPendingIntent(paramContext, paramUMessage);
      ((Notification)localObject1).deleteIntent = getDismissPendingIntent(paramContext, paramUMessage);
      ((Notification)localObject1).contentIntent = ((PendingIntent)localObject2);
      int j = getNotificationDefaults(paramContext, paramUMessage);
      int i = j;
      if ((j & 0x1) != 0)
      {
        localObject2 = getSound(paramContext, paramUMessage);
        if (localObject2 != null)
          ((Notification)localObject1).sound = getSound(paramContext, paramUMessage);
        i = j;
        if (localObject2 != null)
          i = j ^ 0x1;
      }
      ((Notification)localObject1).defaults = i;
      a(paramContext, (Notification)localObject1, bool, paramUMessage);
      return;
      localObject1 = ((String)localObject1).substring(7, 20);
      break label85;
      label416: localObject2 = d;
      break label140;
      label424: if (((String)localObject2).compareToIgnoreCase((String)localObject1) >= 0)
        bool = true;
      else
        bool = false;
    }
  }

  public PendingIntent getClickPendingIntent(Context paramContext, UMessage paramUMessage)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, NotificationProxyBroadcastReceiver.class);
    localIntent.putExtra("MSG", paramUMessage.getRaw().toString());
    localIntent.putExtra("ACTION", 10);
    localIntent.putExtra("MESSAGE_ID", paramUMessage.message_id);
    localIntent.putExtra("TASK_ID", paramUMessage.task_id);
    return PendingIntent.getBroadcast(paramContext, (int)System.currentTimeMillis(), localIntent, 268435456);
  }

  public PendingIntent getDismissPendingIntent(Context paramContext, UMessage paramUMessage)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, NotificationProxyBroadcastReceiver.class);
    localIntent.putExtra("MSG", paramUMessage.getRaw().toString());
    localIntent.putExtra("ACTION", 11);
    localIntent.putExtra("MESSAGE_ID", paramUMessage.message_id);
    localIntent.putExtra("TASK_ID", paramUMessage.task_id);
    return PendingIntent.getBroadcast(paramContext, (int)(System.currentTimeMillis() + 1L), localIntent, 268435456);
  }

  public Bitmap getLargeIcon(Context paramContext, UMessage paramUMessage)
  {
    while (true)
    {
      try
      {
        if (!paramUMessage.isLargeIconFromInternet())
          break label158;
        localBitmap = BitmapFactory.decodeFile(UmengDownloadResourceService.getMessageResourceFolder(paramContext, paramUMessage) + paramUMessage.img.hashCode());
        if (localBitmap == null)
        {
          int i = -1;
          if (!TextUtils.isEmpty(paramUMessage.largeIcon))
            i = c.a(paramContext).c(paramUMessage.largeIcon);
          int j = i;
          if (i < 0)
            j = c.a(paramContext).c("umeng_push_notification_default_large_icon");
          if (j > 0)
          {
            paramUMessage = BitmapFactory.decodeResource(paramContext.getResources(), j);
            if (paramUMessage == null)
              break;
            if (Build.VERSION.SDK_INT >= 11)
            {
              i = (int)paramContext.getResources().getDimension(17104902);
              return Bitmap.createScaledBitmap(paramUMessage, i, i, true);
            }
            i = i.a(a);
            continue;
          }
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return null;
      }
      paramUMessage = localBitmap;
      continue;
      label158: Bitmap localBitmap = null;
    }
    return null;
  }

  public Notification getNotification(Context paramContext, UMessage paramUMessage)
  {
    return null;
  }

  public int getNotificationDefaults(Context paramContext, UMessage paramUMessage)
  {
    int j = 0;
    int i = 0;
    boolean bool = isInNoDisturbTime(paramContext);
    long l = MessageSharedPrefs.getInstance(paramContext).k();
    if (bool);
    do
    {
      do
        return i;
      while ((c != null) && (Calendar.getInstance().getTimeInMillis() - c.getTime() < l * 1L * 1000L));
      if (paramUMessage.play_vibrate)
        j = 2;
      i = j;
      if (paramUMessage.play_lights)
        i = j | 0x4;
      j = i;
      if (paramUMessage.play_sound)
        j = i | 0x1;
      c = Calendar.getInstance().getTime();
      i = j;
    }
    while (!paramUMessage.screen_on);
    a(paramContext);
    return j;
  }

  // ERROR //
  public int getSmallIconId(Context paramContext, UMessage paramUMessage)
  {
    // Byte code:
    //   0: iconst_m1
    //   1: istore 5
    //   3: iload 5
    //   5: istore 4
    //   7: iload 5
    //   9: istore_3
    //   10: aload_2
    //   11: getfield 498	com/umeng/message/entity/UMessage:icon	Ljava/lang/String;
    //   14: invokestatic 422	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   17: ifne +19 -> 36
    //   20: iload 5
    //   22: istore_3
    //   23: aload_1
    //   24: invokestatic 427	com/umeng/common/message/c:a	(Landroid/content/Context;)Lcom/umeng/common/message/c;
    //   27: aload_2
    //   28: getfield 498	com/umeng/message/entity/UMessage:icon	Ljava/lang/String;
    //   31: invokevirtual 429	com/umeng/common/message/c:c	(Ljava/lang/String;)I
    //   34: istore 4
    //   36: iload 4
    //   38: istore 5
    //   40: iload 4
    //   42: ifge +17 -> 59
    //   45: iload 4
    //   47: istore_3
    //   48: aload_1
    //   49: invokestatic 427	com/umeng/common/message/c:a	(Landroid/content/Context;)Lcom/umeng/common/message/c;
    //   52: ldc 22
    //   54: invokevirtual 429	com/umeng/common/message/c:c	(Ljava/lang/String;)I
    //   57: istore 5
    //   59: iload 5
    //   61: ifge +67 -> 128
    //   64: iload 5
    //   66: istore_3
    //   67: getstatic 39	com/umeng/message/UmengMessageHandler:b	Ljava/lang/String;
    //   70: ldc_w 500
    //   73: invokestatic 97	com/umeng/common/message/Log:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   76: iload 5
    //   78: istore_3
    //   79: aload_1
    //   80: invokevirtual 504	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   83: aload_1
    //   84: invokevirtual 507	android/content/Context:getPackageName	()Ljava/lang/String;
    //   87: iconst_0
    //   88: invokevirtual 513	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   91: getfield 519	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   94: getfield 522	android/content/pm/ApplicationInfo:icon	I
    //   97: istore 4
    //   99: iload 4
    //   101: istore_3
    //   102: iload_3
    //   103: ifge +12 -> 115
    //   106: getstatic 39	com/umeng/message/UmengMessageHandler:b	Ljava/lang/String;
    //   109: ldc_w 524
    //   112: invokestatic 526	com/umeng/common/message/Log:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   115: iload_3
    //   116: ireturn
    //   117: astore_1
    //   118: aload_1
    //   119: invokevirtual 117	java/lang/Exception:printStackTrace	()V
    //   122: iload_3
    //   123: ireturn
    //   124: astore_1
    //   125: goto -7 -> 118
    //   128: iload 5
    //   130: istore_3
    //   131: goto -29 -> 102
    //
    // Exception table:
    //   from	to	target	type
    //   10	20	117	java/lang/Exception
    //   23	36	117	java/lang/Exception
    //   48	59	117	java/lang/Exception
    //   67	76	117	java/lang/Exception
    //   79	99	117	java/lang/Exception
    //   106	115	124	java/lang/Exception
  }

  public Uri getSound(Context paramContext, UMessage paramUMessage)
  {
    Object localObject3 = null;
    while (true)
    {
      try
      {
        if (paramUMessage.isSoundFromInternet())
        {
          Object localObject2 = UmengDownloadResourceService.getMessageResourceFolder(paramContext, paramUMessage) + paramUMessage.sound.hashCode();
          localObject1 = localObject2;
          if (!new File((String)localObject2).exists())
            localObject1 = null;
          localObject2 = localObject1;
          if (localObject1 == null)
          {
            int i = -1;
            if (!TextUtils.isEmpty(paramUMessage.sound))
              i = c.a(paramContext).h(paramUMessage.sound);
            int j = i;
            if (i < 0)
              j = c.a(paramContext).h("umeng_push_notification_default_sound");
            localObject2 = localObject1;
            if (j > 0)
              localObject2 = "android.resource://" + paramContext.getPackageName() + "/" + j;
          }
          paramContext = localObject3;
          if (localObject2 != null)
            paramContext = Uri.parse((String)localObject2);
          return paramContext;
        }
      }
      catch (Throwable paramContext)
      {
        return null;
      }
      Object localObject1 = null;
    }
  }

  public void handleMessage(Context paramContext, UMessage paramUMessage)
  {
    if ("notification".equals(paramUMessage.display_type))
      dealWithNotificationMessage(paramContext, paramUMessage);
    while (!"custom".equals(paramUMessage.display_type))
      return;
    UTrack.getInstance(paramContext).setClearPrevMessage(false);
    dealWithCustomMessage(paramContext, paramUMessage);
  }

  public boolean isInNoDisturbTime(Context paramContext)
  {
    int j = Calendar.getInstance().get(11);
    int k = Calendar.getInstance().get(12);
    int i;
    if (j * 60 + k >= PushAgent.getInstance(paramContext).getNoDisturbStartHour() * 60 + PushAgent.getInstance(paramContext).getNoDisturbStartMinute())
    {
      i = 1;
      if (j * 60 + k > PushAgent.getInstance(paramContext).getNoDisturbEndHour() * 60 + PushAgent.getInstance(paramContext).getNoDisturbEndMinute())
        break label141;
      j = 1;
      label79: if (PushAgent.getInstance(paramContext).getNoDisturbEndHour() * 60 + PushAgent.getInstance(paramContext).getNoDisturbEndMinute() < PushAgent.getInstance(paramContext).getNoDisturbStartHour() * 60 + PushAgent.getInstance(paramContext).getNoDisturbStartMinute())
        break label146;
      k = 1;
      label121: if (k == 0)
        break label154;
      if ((i == 0) || (j == 0))
        break label152;
    }
    label141: label146: label152: label154: 
    while ((i != 0) || (j != 0))
    {
      return true;
      i = 0;
      break;
      j = 0;
      break label79;
      k = 0;
      break label121;
      return false;
    }
    return false;
  }

  public void setPrevMessage(UMessage paramUMessage)
  {
    this.e = paramUMessage;
  }

  public boolean startDownloadResourceService(Context paramContext, UMessage paramUMessage)
  {
    try
    {
      Intent localIntent = new Intent(paramContext, UmengDownloadResourceService.class);
      localIntent.putExtra("body", paramUMessage.getRaw().toString());
      localIntent.putExtra("id", paramUMessage.message_id);
      localIntent.putExtra("task_id", paramUMessage.task_id);
      paramContext.startService(localIntent);
      return true;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.UmengMessageHandler
 * JD-Core Version:    0.6.2
 */