package com.umeng.message.local;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.umeng.common.message.Log;
import com.umeng.common.message.c;
import com.umeng.message.NotificationProxyBroadcastReceiver;
import com.umeng.message.PushAgent;
import com.umeng.message.proguard.g;
import com.umeng.message.proguard.i;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Random;

public class UmengNotificationBuilder
  implements Serializable
{
  private static final long a = -2293674780748522096L;
  private static String b = UmengNotificationBuilder.class.getName();
  private static final int c = 64;
  private static final String d = "umeng_push_notification_default_large_icon";
  private static final String e = "umeng_push_notification_default_small_icon";
  private static final String f = "umeng_push_notification_default_sound";
  private static final String g = "id";
  private static final String h = "localnotification_id";
  private static final String i = "flags";
  private static final String j = "defaults";
  private static final String k = "smallicon_drawable";
  private static final String l = "largeicon_drawable";
  private static final String m = "sound_drawable";
  private static final String n = "play_vibrate";
  private static final String o = "play_lights";
  private static final String p = "play_sound";
  private static final String q = "screen_on";
  private static final String r = "layout_id";
  private static final String s = "layout_title_id";
  private static final String t = "layout_content_id";
  private static final String u = "layout_icon_id";
  private static final String v = "layout_icon_drawable_id";
  private String A;
  private String B;
  private String C;
  private boolean D;
  private boolean E;
  private boolean F;
  private boolean G;
  private int H;
  private int I;
  private int J;
  private int K;
  private int L;
  private String w;
  private String x;
  private int y;
  private int z;

  public UmengNotificationBuilder()
  {
    this.w = g.b();
    this.y = 16;
    this.D = true;
    this.E = true;
    this.F = true;
    this.G = true;
    this.L = -1;
    this.K = -1;
    this.J = -1;
    this.I = -1;
    this.H = -1;
  }

  UmengNotificationBuilder(Cursor paramCursor)
  {
    if (paramCursor != null)
    {
      this.w = paramCursor.getString(paramCursor.getColumnIndex("id"));
      this.x = paramCursor.getString(paramCursor.getColumnIndex("localnotification_id"));
      this.y = paramCursor.getInt(paramCursor.getColumnIndex("flags"));
      this.z = paramCursor.getInt(paramCursor.getColumnIndex("defaults"));
      this.A = paramCursor.getString(paramCursor.getColumnIndex("smallicon_drawable"));
      this.B = paramCursor.getString(paramCursor.getColumnIndex("largeicon_drawable"));
      this.C = paramCursor.getString(paramCursor.getColumnIndex("sound_drawable"));
      if (paramCursor.getInt(paramCursor.getColumnIndex("play_vibrate")) != 1)
        break label327;
      bool1 = true;
      this.D = bool1;
      if (paramCursor.getInt(paramCursor.getColumnIndex("play_lights")) != 1)
        break label332;
      bool1 = true;
      label181: this.E = bool1;
      if (paramCursor.getInt(paramCursor.getColumnIndex("play_sound")) != 1)
        break label337;
      bool1 = true;
      label206: this.F = bool1;
      if (paramCursor.getInt(paramCursor.getColumnIndex("screen_on")) != 1)
        break label342;
    }
    label327: label332: label337: label342: for (boolean bool1 = bool2; ; bool1 = false)
    {
      this.G = bool1;
      this.H = paramCursor.getInt(paramCursor.getColumnIndex("layout_id"));
      this.I = paramCursor.getInt(paramCursor.getColumnIndex("layout_title_id"));
      this.J = paramCursor.getInt(paramCursor.getColumnIndex("layout_content_id"));
      this.K = paramCursor.getInt(paramCursor.getColumnIndex("layout_icon_id"));
      this.L = paramCursor.getInt(paramCursor.getColumnIndex("layout_icon_drawable_id"));
      return;
      bool1 = false;
      break;
      bool1 = false;
      break label181;
      bool1 = false;
      break label206;
    }
  }

  UmengNotificationBuilder(String paramString)
  {
    this.w = g.b();
    this.y = 16;
    this.D = true;
    this.E = true;
    this.F = true;
    this.G = true;
    this.L = -1;
    this.K = -1;
    this.J = -1;
    this.I = -1;
    this.H = -1;
    if (!TextUtils.isEmpty(paramString))
    {
      this.x = paramString;
      return;
    }
    this.x = "";
    Log.b(b, "localNotificationId is null");
  }

  private PendingIntent a(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, NotificationProxyBroadcastReceiver.class);
    localIntent.putExtra("ACTION", 12);
    return PendingIntent.getBroadcast(paramContext, (int)System.currentTimeMillis(), localIntent, 268435456);
  }

  private int b(Context paramContext)
  {
    int i2 = 0;
    int i1 = 0;
    if (f(paramContext));
    do
    {
      return i1;
      if (this.D)
        i2 = 0x0 | (this.z | 0x2);
      i1 = i2;
      if (this.E)
        i1 = i2 | (this.z | 0x4);
      i2 = i1;
      if (this.F)
        i2 = i1 | (this.z | 0x1);
      i1 = i2;
    }
    while (!this.G);
    g(paramContext);
    return i2;
  }

  // ERROR //
  private int c(Context paramContext)
  {
    // Byte code:
    //   0: iconst_m1
    //   1: istore 4
    //   3: iload 4
    //   5: istore_3
    //   6: iload 4
    //   8: istore_2
    //   9: aload_0
    //   10: getfield 150	com/umeng/message/local/UmengNotificationBuilder:A	Ljava/lang/String;
    //   13: invokestatic 161	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   16: ifne +18 -> 34
    //   19: iload 4
    //   21: istore_2
    //   22: aload_1
    //   23: invokestatic 213	com/umeng/common/message/c:a	(Landroid/content/Context;)Lcom/umeng/common/message/c;
    //   26: aload_0
    //   27: getfield 150	com/umeng/message/local/UmengNotificationBuilder:A	Ljava/lang/String;
    //   30: invokevirtual 215	com/umeng/common/message/c:c	(Ljava/lang/String;)I
    //   33: istore_3
    //   34: iload_3
    //   35: istore 4
    //   37: iload_3
    //   38: ifge +16 -> 54
    //   41: iload_3
    //   42: istore_2
    //   43: aload_1
    //   44: invokestatic 213	com/umeng/common/message/c:a	(Landroid/content/Context;)Lcom/umeng/common/message/c;
    //   47: ldc 23
    //   49: invokevirtual 215	com/umeng/common/message/c:c	(Ljava/lang/String;)I
    //   52: istore 4
    //   54: iload 4
    //   56: ifge +63 -> 119
    //   59: iload 4
    //   61: istore_2
    //   62: getstatic 99	com/umeng/message/local/UmengNotificationBuilder:b	Ljava/lang/String;
    //   65: ldc 217
    //   67: invokestatic 219	com/umeng/common/message/Log:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   70: iload 4
    //   72: istore_2
    //   73: aload_1
    //   74: invokevirtual 225	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   77: aload_1
    //   78: invokevirtual 228	android/content/Context:getPackageName	()Ljava/lang/String;
    //   81: iconst_0
    //   82: invokevirtual 234	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   85: getfield 240	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   88: getfield 245	android/content/pm/ApplicationInfo:icon	I
    //   91: istore_3
    //   92: iload_3
    //   93: istore_2
    //   94: iload_2
    //   95: ifge +11 -> 106
    //   98: getstatic 99	com/umeng/message/local/UmengNotificationBuilder:b	Ljava/lang/String;
    //   101: ldc 247
    //   103: invokestatic 170	com/umeng/common/message/Log:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   106: iload_2
    //   107: ireturn
    //   108: astore_1
    //   109: aload_1
    //   110: invokevirtual 250	java/lang/Exception:printStackTrace	()V
    //   113: iload_2
    //   114: ireturn
    //   115: astore_1
    //   116: goto -7 -> 109
    //   119: iload 4
    //   121: istore_2
    //   122: goto -28 -> 94
    //
    // Exception table:
    //   from	to	target	type
    //   9	19	108	java/lang/Exception
    //   22	34	108	java/lang/Exception
    //   43	54	108	java/lang/Exception
    //   62	70	108	java/lang/Exception
    //   73	92	108	java/lang/Exception
    //   98	106	115	java/lang/Exception
  }

  private Bitmap d(Context paramContext)
  {
    int i1;
    if (0 == 0)
      i1 = -1;
    while (true)
    {
      try
      {
        if (!TextUtils.isEmpty(this.B))
          i1 = c.a(paramContext).c(this.B);
        int i2 = i1;
        if (i1 < 0)
          i2 = c.a(paramContext).c("umeng_push_notification_default_large_icon");
        if (i2 > 0)
        {
          localBitmap = BitmapFactory.decodeResource(paramContext.getResources(), i2);
          if (localBitmap == null)
            break;
          if (Build.VERSION.SDK_INT >= 11)
          {
            i1 = (int)paramContext.getResources().getDimension(17104902);
            return Bitmap.createScaledBitmap(localBitmap, i1, i1, true);
          }
          i1 = i.a(64.0F);
          continue;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return null;
      }
      Bitmap localBitmap = null;
    }
    return null;
  }

  private Uri e(Context paramContext)
  {
    Uri localUri = null;
    int i1 = -1;
    while (true)
    {
      try
      {
        if (!TextUtils.isEmpty(this.C))
          i1 = c.a(paramContext).c(this.C);
        int i2 = i1;
        if (i1 < 0)
          i2 = c.a(paramContext).h("umeng_push_notification_default_sound");
        if (i2 > 0)
        {
          paramContext = "android.resource://" + paramContext.getPackageName() + "/" + i2;
          if (paramContext != null)
            localUri = Uri.parse(paramContext);
          return localUri;
        }
      }
      catch (Throwable paramContext)
      {
        return null;
      }
      paramContext = null;
    }
  }

  private boolean f(Context paramContext)
  {
    int i2 = Calendar.getInstance().get(11);
    int i3 = Calendar.getInstance().get(12);
    int i1;
    if (i2 * 60 + i3 >= PushAgent.getInstance(paramContext).getNoDisturbStartHour() * 60 + PushAgent.getInstance(paramContext).getNoDisturbStartMinute())
    {
      i1 = 1;
      if (i2 * 60 + i3 > PushAgent.getInstance(paramContext).getNoDisturbEndHour() * 60 + PushAgent.getInstance(paramContext).getNoDisturbEndMinute())
        break label141;
      i2 = 1;
      label79: if (PushAgent.getInstance(paramContext).getNoDisturbEndHour() * 60 + PushAgent.getInstance(paramContext).getNoDisturbEndMinute() < PushAgent.getInstance(paramContext).getNoDisturbStartHour() * 60 + PushAgent.getInstance(paramContext).getNoDisturbStartMinute())
        break label146;
      i3 = 1;
      label121: if (i3 == 0)
        break label154;
      if ((i1 == 0) || (i2 == 0))
        break label152;
    }
    label141: label146: label152: label154: 
    while ((i1 != 0) || (i2 != 0))
    {
      return true;
      i1 = 0;
      break;
      i2 = 0;
      break label79;
      i3 = 0;
      break label121;
      return false;
    }
    return false;
  }

  @SuppressLint({"NewApi", "Wakelock"})
  private void g(final Context paramContext)
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

  public Notification builder(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    Object localObject = null;
    NotificationCompat.Builder localBuilder = new NotificationCompat.Builder(paramContext);
    int i1 = c(paramContext);
    Log.c(b, "title=" + paramString1);
    Log.c(b, "smallIconId=" + i1);
    boolean bool;
    if (i1 > 0)
    {
      localBuilder.setSmallIcon(i1);
      localObject = d(paramContext);
      if ((this.y & 0x10) == 0)
        break label336;
      bool = true;
      Log.c(b, "flags=" + this.y);
      Log.c(b, "isAutoCancel=" + bool);
      if ((this.H > 0) && (this.I > 0) && (this.J > 0) && (this.K > 0) && (this.L > 0))
        break label342;
      if (localObject != null)
        localBuilder.setLargeIcon((Bitmap)localObject);
      localBuilder.setContentTitle(paramString1).setContentText(paramString2).setTicker(paramString3).setAutoCancel(bool);
      paramString3 = new NotificationCompat.BigTextStyle();
      paramString3.setBigContentTitle(paramString1);
      paramString3.bigText(paramString2);
      localBuilder.setStyle(paramString3);
      paramString1 = localBuilder.build();
      int i2 = b(paramContext);
      i1 = i2;
      if ((i2 & 0x1) != 0)
      {
        paramString2 = e(paramContext);
        if (paramString2 != null)
          paramString1.sound = paramString2;
        i1 = i2;
        if (paramString2 != null)
          i1 = i2 ^ 0x1;
      }
      paramString1.defaults = i1;
    }
    while (true)
    {
      paramString1.contentIntent = a(paramContext);
      localObject = paramString1;
      return localObject;
      label336: bool = false;
      break;
      label342: paramString3 = new RemoteViews(paramContext.getPackageName(), this.H);
      paramString3.setTextViewText(this.I, paramString1);
      paramString3.setTextViewText(this.J, paramString2);
      paramString3.setImageViewBitmap(this.L, (Bitmap)localObject);
      paramString3.setImageViewResource(this.K, i1);
      localBuilder.setContent(paramString3);
      localBuilder.setAutoCancel(bool);
      paramString1 = localBuilder.build();
      paramString1.contentView = paramString3;
    }
  }

  public ContentValues getContentValues()
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("id", this.w);
    localContentValues.put("localnotification_id", this.x);
    localContentValues.put("flags", Integer.valueOf(this.y));
    localContentValues.put("defaults", Integer.valueOf(this.z));
    localContentValues.put("smallicon_drawable", this.A);
    localContentValues.put("largeicon_drawable", this.B);
    localContentValues.put("sound_drawable", this.C);
    localContentValues.put("play_vibrate", Boolean.valueOf(this.D));
    localContentValues.put("play_lights", Boolean.valueOf(this.E));
    localContentValues.put("play_sound", Boolean.valueOf(this.F));
    localContentValues.put("screen_on", Boolean.valueOf(this.G));
    localContentValues.put("layout_id", Integer.valueOf(this.H));
    localContentValues.put("layout_title_id", Integer.valueOf(this.I));
    localContentValues.put("layout_content_id", Integer.valueOf(this.J));
    localContentValues.put("layout_icon_id", Integer.valueOf(this.K));
    localContentValues.put("layout_icon_drawable_id", Integer.valueOf(this.L));
    return localContentValues;
  }

  public int getDefaults()
  {
    return this.z;
  }

  public int getFlags()
  {
    return this.y;
  }

  public String getId()
  {
    return this.w;
  }

  public String getLargeIconDrawable()
  {
    return this.B;
  }

  public int getLayoutContentId()
  {
    return this.J;
  }

  public int getLayoutIconDrawableId()
  {
    return this.L;
  }

  public int getLayoutIconId()
  {
    return this.K;
  }

  public int getLayoutId()
  {
    return this.H;
  }

  public int getLayoutTitleId()
  {
    return this.I;
  }

  public String getLocalNotificationId()
  {
    return this.x;
  }

  public String getSmallIconDrawable()
  {
    return this.A;
  }

  public String getSoundDrawable()
  {
    return this.C;
  }

  public boolean isPlayLights()
  {
    return this.E;
  }

  public boolean isPlaySound()
  {
    return this.F;
  }

  public boolean isPlayVibrate()
  {
    return this.D;
  }

  public boolean isScreenOn()
  {
    return this.G;
  }

  public void setDefaults(int paramInt)
  {
    this.z = paramInt;
  }

  public void setFlags(int paramInt)
  {
    this.y = paramInt;
  }

  public void setId(String paramString)
  {
    this.w = paramString;
  }

  public void setLargeIconDrawable(String paramString)
  {
    this.B = paramString;
  }

  public void setLayoutContentId(int paramInt)
  {
    this.J = paramInt;
  }

  public void setLayoutIconDrawableId(int paramInt)
  {
    this.L = paramInt;
  }

  public void setLayoutIconId(int paramInt)
  {
    this.K = paramInt;
  }

  public void setLayoutId(int paramInt)
  {
    this.H = paramInt;
  }

  public void setLayoutTitleId(int paramInt)
  {
    this.I = paramInt;
  }

  public void setLocalNotificationId(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      this.x = paramString;
      return;
    }
    this.x = "";
    Log.b(b, "localNotificationId is null");
  }

  public void setPlayLights(boolean paramBoolean)
  {
    this.E = paramBoolean;
  }

  public void setPlaySound(boolean paramBoolean)
  {
    this.F = paramBoolean;
  }

  public void setPlayVibrate(boolean paramBoolean)
  {
    this.D = paramBoolean;
  }

  public void setScreenOn(boolean paramBoolean)
  {
    this.G = paramBoolean;
  }

  public void setSmallIconDrawable(String paramString)
  {
    this.A = paramString;
  }

  public void setSoundDrawable(String paramString)
  {
    this.C = paramString;
  }

  public void showNotification(Context paramContext, Notification paramNotification)
  {
    try
    {
      NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
      int i2 = 20100401;
      try
      {
        if (!PushAgent.getInstance(paramContext).getMergeNotificaiton())
          i1 = new Random().nextInt();
        while (true)
        {
          if ((!g.c(paramContext)) || (!g.b(paramContext)) || (PushAgent.getInstance(paramContext).getNotificationOnForeground()))
            localNotificationManager.notify(i1, paramNotification);
          return;
          if ((g.c(paramContext)) && (g.b(paramContext)))
          {
            i1 = i2;
            if (!PushAgent.getInstance(paramContext).getNotificationOnForeground());
          }
          else
          {
            localNotificationManager.cancel(20100401);
            i1 = i2;
          }
        }
      }
      catch (Exception localException)
      {
        while (true)
        {
          localException.printStackTrace();
          int i1 = i2;
        }
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.local.UmengNotificationBuilder
 * JD-Core Version:    0.6.2
 */