package com.umeng.update.net;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.SparseArray;
import android.widget.RemoteViews;
import com.umeng.update.util.DeltaUpdate;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import u.upd.a;
import u.upd.k;
import u.upd.l;
import u.upd.n;

class c
{
  static final int a = 0;
  static final int b = 1;
  static final int c = 1;
  static final int d = 2;
  private static final String e = c.class.getName();
  private SparseArray<b> f;
  private Map<a.a, Messenger> g;
  private e h;

  public c(SparseArray<b> paramSparseArray, Map<a.a, Messenger> paramMap, e parame)
  {
    this.f = paramSparseArray;
    this.g = paramMap;
    this.h = parame;
  }

  int a(a.a parama)
  {
    return Math.abs((int)((parama.b.hashCode() >> 2) + (parama.c.hashCode() >> 3) + System.currentTimeMillis()));
  }

  a a(Context paramContext, a.a parama, int paramInt1, int paramInt2)
  {
    paramContext = paramContext.getApplicationContext();
    a locala = new a(paramContext);
    Object localObject1 = PendingIntent.getActivity(paramContext, 0, new Intent(), 134217728);
    locala.d(paramContext.getString(l.j(paramContext))).a(17301633).a((PendingIntent)localObject1).a(System.currentTimeMillis());
    localObject1 = new RemoteViews(paramContext.getPackageName(), k.a(paramContext));
    int i;
    int j;
    if (Build.VERSION.SDK_INT >= 14)
    {
      i = paramContext.getResources().getDimensionPixelSize(17104901);
      j = paramContext.getResources().getDimensionPixelSize(17104902);
      ((RemoteViews)localObject1).setInt(u.upd.j.b(paramContext), "setWidth", i);
      ((RemoteViews)localObject1).setInt(u.upd.j.b(paramContext), "setHeight", j);
    }
    while (true)
    {
      try
      {
        Field localField1 = Class.forName("com.android.internal.R$drawable").getDeclaredField("notify_panel_notification_icon_bg_tile");
        localField1.setAccessible(true);
        i = localField1.getInt(null);
        ((RemoteViews)localObject1).setInt(u.upd.j.b(paramContext), "setBackgroundResource", i);
        locala.a((RemoteViews)localObject1);
        locala.b(paramContext.getResources().getString(l.g(paramContext)) + parama.b).a(paramInt2 + "%").a(100, paramInt2, false);
        if (!parama.g)
          break;
        locala.b((RemoteViews)localObject1);
        locala.e();
        parama = f.b(paramContext, f.a(paramInt1, "continue"));
        localObject1 = f.b(paramContext, f.a(paramInt1, "cancel"));
        a(paramContext, locala, paramInt1, 2);
        locala.a(parama, (PendingIntent)localObject1).c().a(true).b(false);
        return locala;
      }
      catch (Exception localException1)
      {
        u.upd.b.a(e, "No notification icon background found:", localException1);
        continue;
      }
      try
      {
        Field localField2 = Class.forName("com.android.internal.R$drawable").getDeclaredField("status_bar_notification_icon_bg");
        localField2.setAccessible(true);
        i = localField2.getInt(null);
        ((RemoteViews)localObject1).setInt(u.upd.j.b(paramContext), "setBackgroundResource", i);
      }
      catch (Exception localException2)
      {
        try
        {
          Object localObject2 = Class.forName("com.android.internal.R$dimen");
          Field localField3 = ((Class)localObject2).getDeclaredField("status_bar_edge_ignore");
          localField3.setAccessible(true);
          i = localField3.getInt(null);
          i = paramContext.getResources().getDimensionPixelSize(i);
          localObject2 = ((Class)localObject2).getDeclaredField("status_bar_height");
          ((Field)localObject2).setAccessible(true);
          int k = ((Field)localObject2).getInt(null);
          j = paramContext.getResources().getDimensionPixelSize(k);
          k = paramContext.getResources().getDimensionPixelSize(k);
          ((RemoteViews)localObject1).setInt(u.upd.j.b(paramContext), "setWidth", k + (i + 0 + j));
        }
        catch (Exception localException3)
        {
          u.upd.b.a(e, "No notification size found:", localException3);
        }
      }
    }
    locala.a().a(true).b(false);
    return locala;
  }

  void a(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getApplicationContext();
    NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
    b localb = (b)this.f.get(paramInt);
    localb.b.e();
    a(paramContext, localb.b, paramInt, 1);
    localb.b.b(paramContext.getResources().getString(l.h(paramContext)) + localb.e.b).b().a(false).b(true);
    localNotificationManager.notify(paramInt, localb.b.d());
  }

  void a(Context paramContext, a parama, int paramInt1, int paramInt2)
  {
    PendingIntent localPendingIntent1;
    PendingIntent localPendingIntent2;
    if (Build.VERSION.SDK_INT >= 16)
    {
      localPendingIntent1 = f.b(paramContext, f.a(paramInt1, "continue"));
      localPendingIntent2 = f.b(paramContext, f.a(paramInt1, "cancel"));
      switch (paramInt2)
      {
      default:
      case 1:
      case 2:
      }
    }
    while (true)
    {
      parama.a(17301560, paramContext.getResources().getString(l.f(paramContext)), localPendingIntent2);
      return;
      parama.a(17301540, paramContext.getResources().getString(l.e(paramContext)), localPendingIntent1);
      continue;
      parama.a(17301539, paramContext.getResources().getString(l.d(paramContext)), localPendingIntent1);
    }
  }

  void a(a.a parama, long paramLong1, long paramLong2, long paramLong3)
  {
    if (parama.f != null)
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("dsize", String.valueOf(paramLong1));
      localHashMap.put("dtime", n.a().split(" ")[1]);
      float f1 = 0.0F;
      if (paramLong2 > 0L)
        f1 = (float)paramLong1 / (float)paramLong2;
      localHashMap.put("dpcent", String.valueOf((int)(f1 * 100.0F)));
      localHashMap.put("ptimes", String.valueOf(paramLong3));
      a(localHashMap, false, parama.f);
    }
  }

  final void a(final Map<String, String> paramMap, final boolean paramBoolean, final String[] paramArrayOfString)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        int j = new Random().nextInt(1000);
        if (paramArrayOfString == null)
          u.upd.b.a(c.a(), j + "service report: urls is null");
        while (true)
        {
          return;
          String[] arrayOfString = paramArrayOfString;
          int k = arrayOfString.length;
          int i = 0;
          if (i >= k)
            continue;
          String str1 = arrayOfString[i];
          Object localObject1 = n.a();
          Object localObject2 = localObject1.split(" ")[0];
          String str2 = localObject1.split(" ")[1];
          long l = System.currentTimeMillis();
          localObject1 = new StringBuilder(str1);
          ((StringBuilder)localObject1).append("&data=").append((String)localObject2);
          ((StringBuilder)localObject1).append("&time=").append(str2);
          ((StringBuilder)localObject1).append("&ts=").append(l);
          if (paramBoolean)
            ((StringBuilder)localObject1).append("&action_type=").append(1);
          while (paramMap != null)
          {
            localObject2 = paramMap.keySet().iterator();
            while (((Iterator)localObject2).hasNext())
            {
              str2 = (String)((Iterator)localObject2).next();
              ((StringBuilder)localObject1).append("&").append(str2).append("=").append((String)paramMap.get(str2));
            }
            ((StringBuilder)localObject1).append("&action_type=").append(-2);
          }
          try
          {
            u.upd.b.a(c.a(), j + ": service report:\tget: " + ((StringBuilder)localObject1).toString());
            localObject1 = new HttpGet(((StringBuilder)localObject1).toString());
            localObject2 = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout((HttpParams)localObject2, 10000);
            HttpConnectionParams.setSoTimeout((HttpParams)localObject2, 20000);
            localObject1 = new DefaultHttpClient((HttpParams)localObject2).execute((HttpUriRequest)localObject1);
            u.upd.b.a(c.a(), j + ": service report:status code:  " + ((HttpResponse)localObject1).getStatusLine().getStatusCode());
            int m = ((HttpResponse)localObject1).getStatusLine().getStatusCode();
            if (m == 200)
              continue;
            i += 1;
          }
          catch (ClientProtocolException localClientProtocolException)
          {
            while (true)
              u.upd.b.c(c.a(), j + ": service report:\tClientProtocolException,Failed to send message." + str1, localClientProtocolException);
          }
          catch (IOException localIOException)
          {
            while (true)
              u.upd.b.c(c.a(), j + ": service report:\tIOException,Failed to send message." + str1, localIOException);
          }
        }
      }
    }).start();
  }

  boolean a(Context paramContext)
  {
    Object localObject = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if (localObject == null)
      return false;
    paramContext = paramContext.getPackageName();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
      if ((localRunningAppProcessInfo.importance == 100) && (localRunningAppProcessInfo.processName.equals(paramContext)))
        return true;
    }
    return false;
  }

  // ERROR //
  boolean a(DownloadingService paramDownloadingService, Intent paramIntent)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 412	com/umeng/update/net/DownloadingService:getApplicationContext	()Landroid/content/Context;
    //   4: astore 10
    //   6: aload_2
    //   7: invokevirtual 416	android/content/Intent:getExtras	()Landroid/os/Bundle;
    //   10: ldc_w 418
    //   13: invokevirtual 423	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   16: ldc_w 425
    //   19: invokevirtual 347	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   22: astore_2
    //   23: aload_2
    //   24: iconst_0
    //   25: aaload
    //   26: invokestatic 431	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   29: istore_3
    //   30: aload_2
    //   31: iconst_1
    //   32: aaload
    //   33: invokevirtual 434	java/lang/String:trim	()Ljava/lang/String;
    //   36: astore 11
    //   38: iload_3
    //   39: ifeq +225 -> 264
    //   42: aload 11
    //   44: invokestatic 440	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   47: ifne +217 -> 264
    //   50: aload_0
    //   51: getfield 47	com/umeng/update/net/c:f	Landroid/util/SparseArray;
    //   54: iload_3
    //   55: invokevirtual 443	android/util/SparseArray:indexOfKey	(I)I
    //   58: iflt +206 -> 264
    //   61: aload_0
    //   62: getfield 47	com/umeng/update/net/c:f	Landroid/util/SparseArray;
    //   65: iload_3
    //   66: invokevirtual 287	android/util/SparseArray:get	(I)Ljava/lang/Object;
    //   69: checkcast 11	com/umeng/update/net/c$b
    //   72: astore_2
    //   73: aload_2
    //   74: getfield 446	com/umeng/update/net/c$b:a	Lcom/umeng/update/net/DownloadingService$b;
    //   77: astore 12
    //   79: ldc 229
    //   81: aload 11
    //   83: invokevirtual 406	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   86: ifeq +279 -> 365
    //   89: aload 12
    //   91: ifnonnull +175 -> 266
    //   94: getstatic 40	com/umeng/update/net/c:e	Ljava/lang/String;
    //   97: ldc_w 448
    //   100: invokestatic 451	u/upd/b:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   103: aload 10
    //   105: ldc_w 453
    //   108: invokestatic 458	u/upd/a:a	(Landroid/content/Context;Ljava/lang/String;)Z
    //   111: ifeq +38 -> 149
    //   114: aload 10
    //   116: invokestatic 461	u/upd/a:l	(Landroid/content/Context;)Z
    //   119: ifne +30 -> 149
    //   122: aload 10
    //   124: aload 10
    //   126: invokevirtual 147	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   129: aload 10
    //   131: invokevirtual 88	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   134: invokestatic 462	u/upd/l:a	(Landroid/content/Context;)I
    //   137: invokevirtual 199	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   140: iconst_1
    //   141: invokestatic 468	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   144: invokevirtual 471	android/widget/Toast:show	()V
    //   147: iconst_0
    //   148: ireturn
    //   149: aload_1
    //   150: invokevirtual 475	java/lang/Object:getClass	()Ljava/lang/Class;
    //   153: pop
    //   154: new 477	com/umeng/update/net/DownloadingService$b
    //   157: dup
    //   158: aload_1
    //   159: aload 10
    //   161: aload_2
    //   162: getfield 295	com/umeng/update/net/c$b:e	Lcom/umeng/update/net/a$a;
    //   165: iload_3
    //   166: aload_2
    //   167: getfield 479	com/umeng/update/net/c$b:d	I
    //   170: aload_1
    //   171: getfield 483	com/umeng/update/net/DownloadingService:q	Lcom/umeng/update/net/DownloadingService$a;
    //   174: invokespecial 486	com/umeng/update/net/DownloadingService$b:<init>	(Lcom/umeng/update/net/DownloadingService;Landroid/content/Context;Lcom/umeng/update/net/a$a;IILcom/umeng/update/net/DownloadingService$a;)V
    //   177: astore_1
    //   178: aload_2
    //   179: aload_1
    //   180: putfield 446	com/umeng/update/net/c$b:a	Lcom/umeng/update/net/DownloadingService$b;
    //   183: aload_1
    //   184: invokevirtual 487	com/umeng/update/net/DownloadingService$b:start	()V
    //   187: invokestatic 493	android/os/Message:obtain	()Landroid/os/Message;
    //   190: astore_1
    //   191: aload_1
    //   192: iconst_2
    //   193: putfield 496	android/os/Message:what	I
    //   196: aload_1
    //   197: bipush 7
    //   199: putfield 499	android/os/Message:arg1	I
    //   202: aload_1
    //   203: iload_3
    //   204: putfield 502	android/os/Message:arg2	I
    //   207: aload_0
    //   208: getfield 49	com/umeng/update/net/c:g	Ljava/util/Map;
    //   211: aload_2
    //   212: getfield 295	com/umeng/update/net/c$b:e	Lcom/umeng/update/net/a$a;
    //   215: invokeinterface 505 2 0
    //   220: ifnull +23 -> 243
    //   223: aload_0
    //   224: getfield 49	com/umeng/update/net/c:g	Ljava/util/Map;
    //   227: aload_2
    //   228: getfield 295	com/umeng/update/net/c$b:e	Lcom/umeng/update/net/a$a;
    //   231: invokeinterface 505 2 0
    //   236: checkcast 507	android/os/Messenger
    //   239: aload_1
    //   240: invokevirtual 511	android/os/Messenger:send	(Landroid/os/Message;)V
    //   243: iconst_1
    //   244: ireturn
    //   245: astore_1
    //   246: getstatic 40	com/umeng/update/net/c:e	Ljava/lang/String;
    //   249: ldc_w 513
    //   252: aload_1
    //   253: invokestatic 515	u/upd/b:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V
    //   256: goto -13 -> 243
    //   259: astore_1
    //   260: aload_1
    //   261: invokevirtual 518	java/lang/Exception:printStackTrace	()V
    //   264: iconst_0
    //   265: ireturn
    //   266: getstatic 40	com/umeng/update/net/c:e	Ljava/lang/String;
    //   269: ldc_w 448
    //   272: invokestatic 451	u/upd/b:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   275: aload 12
    //   277: iconst_1
    //   278: invokevirtual 521	com/umeng/update/net/DownloadingService$b:a	(I)V
    //   281: aload_2
    //   282: aconst_null
    //   283: putfield 446	com/umeng/update/net/c$b:a	Lcom/umeng/update/net/DownloadingService$b;
    //   286: aload_0
    //   287: aload 10
    //   289: iload_3
    //   290: invokevirtual 523	com/umeng/update/net/c:a	(Landroid/content/Context;I)V
    //   293: invokestatic 493	android/os/Message:obtain	()Landroid/os/Message;
    //   296: astore_1
    //   297: aload_1
    //   298: iconst_2
    //   299: putfield 496	android/os/Message:what	I
    //   302: aload_1
    //   303: bipush 6
    //   305: putfield 499	android/os/Message:arg1	I
    //   308: aload_1
    //   309: iload_3
    //   310: putfield 502	android/os/Message:arg2	I
    //   313: aload_0
    //   314: getfield 49	com/umeng/update/net/c:g	Ljava/util/Map;
    //   317: aload_2
    //   318: getfield 295	com/umeng/update/net/c$b:e	Lcom/umeng/update/net/a$a;
    //   321: invokeinterface 505 2 0
    //   326: ifnull +23 -> 349
    //   329: aload_0
    //   330: getfield 49	com/umeng/update/net/c:g	Ljava/util/Map;
    //   333: aload_2
    //   334: getfield 295	com/umeng/update/net/c$b:e	Lcom/umeng/update/net/a$a;
    //   337: invokeinterface 505 2 0
    //   342: checkcast 507	android/os/Messenger
    //   345: aload_1
    //   346: invokevirtual 511	android/os/Messenger:send	(Landroid/os/Message;)V
    //   349: iconst_1
    //   350: ireturn
    //   351: astore_1
    //   352: getstatic 40	com/umeng/update/net/c:e	Ljava/lang/String;
    //   355: ldc_w 513
    //   358: aload_1
    //   359: invokestatic 515	u/upd/b:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V
    //   362: goto -13 -> 349
    //   365: ldc 239
    //   367: aload 11
    //   369: invokevirtual 406	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   372: ifeq -108 -> 264
    //   375: getstatic 40	com/umeng/update/net/c:e	Ljava/lang/String;
    //   378: ldc_w 525
    //   381: invokestatic 451	u/upd/b:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   384: aload 12
    //   386: ifnull +73 -> 459
    //   389: aload 12
    //   391: iconst_2
    //   392: invokevirtual 521	com/umeng/update/net/DownloadingService$b:a	(I)V
    //   395: invokestatic 493	android/os/Message:obtain	()Landroid/os/Message;
    //   398: astore_1
    //   399: aload_1
    //   400: iconst_5
    //   401: putfield 496	android/os/Message:what	I
    //   404: aload_1
    //   405: iconst_5
    //   406: putfield 499	android/os/Message:arg1	I
    //   409: aload_1
    //   410: iload_3
    //   411: putfield 502	android/os/Message:arg2	I
    //   414: aload_0
    //   415: getfield 49	com/umeng/update/net/c:g	Ljava/util/Map;
    //   418: aload_2
    //   419: getfield 295	com/umeng/update/net/c$b:e	Lcom/umeng/update/net/a$a;
    //   422: invokeinterface 505 2 0
    //   427: ifnull +23 -> 450
    //   430: aload_0
    //   431: getfield 49	com/umeng/update/net/c:g	Ljava/util/Map;
    //   434: aload_2
    //   435: getfield 295	com/umeng/update/net/c$b:e	Lcom/umeng/update/net/a$a;
    //   438: invokeinterface 505 2 0
    //   443: checkcast 507	android/os/Messenger
    //   446: aload_1
    //   447: invokevirtual 511	android/os/Messenger:send	(Landroid/os/Message;)V
    //   450: aload_0
    //   451: aload 10
    //   453: iload_3
    //   454: invokevirtual 527	com/umeng/update/net/c:b	(Landroid/content/Context;I)V
    //   457: iconst_1
    //   458: ireturn
    //   459: aload_2
    //   460: getfield 530	com/umeng/update/net/c$b:f	[J
    //   463: iconst_0
    //   464: laload
    //   465: lstore 4
    //   467: aload_2
    //   468: getfield 530	com/umeng/update/net/c$b:f	[J
    //   471: iconst_1
    //   472: laload
    //   473: lstore 6
    //   475: aload_2
    //   476: getfield 530	com/umeng/update/net/c$b:f	[J
    //   479: iconst_2
    //   480: laload
    //   481: lstore 8
    //   483: aload_0
    //   484: aload_2
    //   485: getfield 295	com/umeng/update/net/c$b:e	Lcom/umeng/update/net/a$a;
    //   488: lload 4
    //   490: lload 6
    //   492: lload 8
    //   494: invokevirtual 532	com/umeng/update/net/c:a	(Lcom/umeng/update/net/a$a;JJJ)V
    //   497: goto -102 -> 395
    //   500: astore_1
    //   501: invokestatic 493	android/os/Message:obtain	()Landroid/os/Message;
    //   504: astore_1
    //   505: aload_1
    //   506: iconst_5
    //   507: putfield 496	android/os/Message:what	I
    //   510: aload_1
    //   511: iconst_5
    //   512: putfield 499	android/os/Message:arg1	I
    //   515: aload_1
    //   516: iload_3
    //   517: putfield 502	android/os/Message:arg2	I
    //   520: aload_0
    //   521: getfield 49	com/umeng/update/net/c:g	Ljava/util/Map;
    //   524: aload_2
    //   525: getfield 295	com/umeng/update/net/c$b:e	Lcom/umeng/update/net/a$a;
    //   528: invokeinterface 505 2 0
    //   533: ifnull +23 -> 556
    //   536: aload_0
    //   537: getfield 49	com/umeng/update/net/c:g	Ljava/util/Map;
    //   540: aload_2
    //   541: getfield 295	com/umeng/update/net/c$b:e	Lcom/umeng/update/net/a$a;
    //   544: invokeinterface 505 2 0
    //   549: checkcast 507	android/os/Messenger
    //   552: aload_1
    //   553: invokevirtual 511	android/os/Messenger:send	(Landroid/os/Message;)V
    //   556: aload_0
    //   557: aload 10
    //   559: iload_3
    //   560: invokevirtual 527	com/umeng/update/net/c:b	(Landroid/content/Context;I)V
    //   563: goto -106 -> 457
    //   566: astore_1
    //   567: aload_0
    //   568: aload 10
    //   570: iload_3
    //   571: invokevirtual 527	com/umeng/update/net/c:b	(Landroid/content/Context;I)V
    //   574: goto -117 -> 457
    //   577: astore_1
    //   578: aload_0
    //   579: aload 10
    //   581: iload_3
    //   582: invokevirtual 527	com/umeng/update/net/c:b	(Landroid/content/Context;I)V
    //   585: goto -128 -> 457
    //   588: astore_1
    //   589: invokestatic 493	android/os/Message:obtain	()Landroid/os/Message;
    //   592: astore 11
    //   594: aload 11
    //   596: iconst_5
    //   597: putfield 496	android/os/Message:what	I
    //   600: aload 11
    //   602: iconst_5
    //   603: putfield 499	android/os/Message:arg1	I
    //   606: aload 11
    //   608: iload_3
    //   609: putfield 502	android/os/Message:arg2	I
    //   612: aload_0
    //   613: getfield 49	com/umeng/update/net/c:g	Ljava/util/Map;
    //   616: aload_2
    //   617: getfield 295	com/umeng/update/net/c$b:e	Lcom/umeng/update/net/a$a;
    //   620: invokeinterface 505 2 0
    //   625: ifnull +24 -> 649
    //   628: aload_0
    //   629: getfield 49	com/umeng/update/net/c:g	Ljava/util/Map;
    //   632: aload_2
    //   633: getfield 295	com/umeng/update/net/c$b:e	Lcom/umeng/update/net/a$a;
    //   636: invokeinterface 505 2 0
    //   641: checkcast 507	android/os/Messenger
    //   644: aload 11
    //   646: invokevirtual 511	android/os/Messenger:send	(Landroid/os/Message;)V
    //   649: aload_0
    //   650: aload 10
    //   652: iload_3
    //   653: invokevirtual 527	com/umeng/update/net/c:b	(Landroid/content/Context;I)V
    //   656: aload_1
    //   657: athrow
    //   658: astore_2
    //   659: aload_0
    //   660: aload 10
    //   662: iload_3
    //   663: invokevirtual 527	com/umeng/update/net/c:b	(Landroid/content/Context;I)V
    //   666: goto -10 -> 656
    //
    // Exception table:
    //   from	to	target	type
    //   207	243	245	android/os/RemoteException
    //   0	38	259	java/lang/Exception
    //   42	89	259	java/lang/Exception
    //   94	147	259	java/lang/Exception
    //   149	207	259	java/lang/Exception
    //   207	243	259	java/lang/Exception
    //   246	256	259	java/lang/Exception
    //   266	313	259	java/lang/Exception
    //   313	349	259	java/lang/Exception
    //   352	362	259	java/lang/Exception
    //   365	384	259	java/lang/Exception
    //   395	414	259	java/lang/Exception
    //   414	450	259	java/lang/Exception
    //   450	457	259	java/lang/Exception
    //   501	520	259	java/lang/Exception
    //   520	556	259	java/lang/Exception
    //   556	563	259	java/lang/Exception
    //   567	574	259	java/lang/Exception
    //   578	585	259	java/lang/Exception
    //   589	612	259	java/lang/Exception
    //   612	649	259	java/lang/Exception
    //   649	656	259	java/lang/Exception
    //   656	658	259	java/lang/Exception
    //   659	666	259	java/lang/Exception
    //   313	349	351	android/os/RemoteException
    //   389	395	500	java/lang/Exception
    //   459	497	500	java/lang/Exception
    //   520	556	566	android/os/RemoteException
    //   556	563	566	android/os/RemoteException
    //   414	450	577	android/os/RemoteException
    //   450	457	577	android/os/RemoteException
    //   389	395	588	finally
    //   459	497	588	finally
    //   612	649	658	android/os/RemoteException
    //   649	656	658	android/os/RemoteException
  }

  boolean a(a.a parama, boolean paramBoolean, Messenger paramMessenger)
  {
    a.a locala;
    if (paramBoolean)
    {
      int i = new Random().nextInt(1000);
      if (this.g != null)
      {
        localIterator = this.g.keySet().iterator();
        while (localIterator.hasNext())
        {
          locala = (a.a)localIterator.next();
          u.upd.b.c(e, "_" + i + " downling  " + locala.b + "   " + locala.c);
        }
      }
      u.upd.b.c(e, "_" + i + "downling  null");
    }
    if (this.g == null)
      return false;
    Iterator localIterator = this.g.keySet().iterator();
    while (localIterator.hasNext())
    {
      locala = (a.a)localIterator.next();
      if ((parama.e != null) && (parama.e.equals(locala.e)))
      {
        this.g.put(locala, paramMessenger);
        return true;
      }
      if (locala.c.equals(parama.c))
      {
        this.g.put(locala, paramMessenger);
        return true;
      }
    }
    return false;
  }

  int b(a.a parama)
  {
    int i = 0;
    while (i < this.f.size())
    {
      int j = this.f.keyAt(i);
      if ((parama.e != null) && (parama.e.equals(((b)this.f.get(j)).e.e)))
        return ((b)this.f.get(j)).c;
      if (((b)this.f.get(j)).e.c.equals(parama.c))
        return ((b)this.f.get(j)).c;
      i += 1;
    }
    return -1;
  }

  void b(Context paramContext, int paramInt)
  {
    paramContext = (NotificationManager)paramContext.getApplicationContext().getSystemService("notification");
    b localb = (b)this.f.get(paramInt);
    if (localb != null)
    {
      u.upd.b.c(e, "download service clear cache " + localb.e.b);
      if (localb.a != null)
        localb.a.a(2);
      paramContext.cancel(localb.c);
      if (this.g.containsKey(localb.e))
        this.g.remove(localb.e);
      localb.b(this.f);
      this.h.b(paramInt);
    }
  }

  static class a extends com.umeng.update.util.b
  {
    public a(Context paramContext)
    {
      super();
    }

    public a a()
    {
      this.c.contentView.setViewVisibility(u.upd.j.e(this.b), 8);
      this.c.contentView.setViewVisibility(u.upd.j.g(this.b), 8);
      return this;
    }

    public a a(int paramInt1, int paramInt2, boolean paramBoolean)
    {
      if (Build.VERSION.SDK_INT >= 14)
        this.d.setProgress(paramInt1, paramInt2, paramBoolean);
      this.c.contentView.setProgressBar(u.upd.j.c(this.b), 100, paramInt2, false);
      return this;
    }

    public a a(PendingIntent paramPendingIntent1, PendingIntent paramPendingIntent2)
    {
      this.c.contentView.setOnClickPendingIntent(u.upd.j.e(this.b), paramPendingIntent1);
      this.c.contentView.setViewVisibility(u.upd.j.e(this.b), 0);
      this.c.contentView.setViewVisibility(u.upd.j.g(this.b), 0);
      this.c.contentView.setOnClickPendingIntent(u.upd.j.g(this.b), paramPendingIntent2);
      return this;
    }

    public a a(RemoteViews paramRemoteViews)
    {
      this.c.contentView = paramRemoteViews;
      return this;
    }

    public a a(CharSequence paramCharSequence)
    {
      if (Build.VERSION.SDK_INT >= 14)
        this.d.setContentText(paramCharSequence);
      this.c.contentView.setTextViewText(u.upd.j.a(this.b), paramCharSequence);
      return this;
    }

    public void a(int paramInt, String paramString, PendingIntent paramPendingIntent)
    {
      if (Build.VERSION.SDK_INT >= 16)
        this.d.addAction(paramInt, paramString, paramPendingIntent);
    }

    public a b()
    {
      int i = u.upd.j.e(this.b);
      this.c.contentView.setTextViewText(i, this.b.getResources().getString(l.e(this.b.getApplicationContext())));
      this.c.contentView.setInt(i, "setBackgroundResource", u.upd.c.a(this.b).c("umeng_common_gradient_green"));
      return this;
    }

    public a b(CharSequence paramCharSequence)
    {
      if (Build.VERSION.SDK_INT >= 14)
        this.d.setContentTitle(paramCharSequence);
      this.c.contentView.setTextViewText(u.upd.j.d(this.b), paramCharSequence);
      return this;
    }

    public a c()
    {
      int i = u.upd.j.e(this.b);
      this.c.contentView.setTextViewText(i, this.b.getResources().getString(l.d(this.b.getApplicationContext())));
      this.c.contentView.setInt(i, "setBackgroundResource", u.upd.c.a(this.b).c("umeng_common_gradient_orange"));
      return this;
    }

    public Notification d()
    {
      if (Build.VERSION.SDK_INT >= 16)
        return this.d.build();
      if (Build.VERSION.SDK_INT >= 14)
        return this.d.getNotification();
      return this.c;
    }
  }

  static class b
  {
    DownloadingService.b a;
    c.a b;
    int c;
    int d;
    a.a e;
    long[] f = new long[3];

    public b(a.a parama, int paramInt)
    {
      this.c = paramInt;
      this.e = parama;
    }

    public void a(SparseArray<b> paramSparseArray)
    {
      paramSparseArray.put(this.c, this);
    }

    public void b(SparseArray<b> paramSparseArray)
    {
      if (paramSparseArray.indexOfKey(this.c) >= 0)
        paramSparseArray.remove(this.c);
    }
  }

  class c extends AsyncTask<String, Void, Integer>
  {
    public int a;
    public String b;
    private a.a d;
    private Context e;
    private NotificationManager f;

    public c(Context paramInt, int parama, a.a paramString, String arg5)
    {
      this.e = paramInt.getApplicationContext();
      this.f = ((NotificationManager)this.e.getSystemService("notification"));
      this.a = parama;
      this.d = paramString;
      Object localObject;
      this.b = localObject;
    }

    protected Integer a(String[] paramArrayOfString)
    {
      int i = DeltaUpdate.a(paramArrayOfString[0], paramArrayOfString[1], paramArrayOfString[2]) + 1;
      new File(paramArrayOfString[2]).delete();
      if (i == 1)
      {
        if (!n.a(new File(paramArrayOfString[1])).equalsIgnoreCase(this.d.e))
        {
          u.upd.b.a(c.a(), "file patch error");
          return Integer.valueOf(0);
        }
        u.upd.b.a(c.a(), "file patch success");
      }
      while (true)
      {
        return Integer.valueOf(i);
        u.upd.b.a(c.a(), "file patch error");
      }
    }

    protected void a(Integer paramInteger)
    {
      if (paramInteger.intValue() == 1)
      {
        j.a(this.b, 39, -1, -1);
        paramInteger = new Notification(17301634, this.e.getString(l.l(this.e)), System.currentTimeMillis());
        localObject = new Intent("android.intent.action.VIEW");
        ((Intent)localObject).addFlags(268435456);
        ((Intent)localObject).setDataAndType(Uri.fromFile(new File(this.b)), "application/vnd.android.package-archive");
        PendingIntent localPendingIntent = PendingIntent.getActivity(this.e, 0, (Intent)localObject, 134217728);
        paramInteger.setLatestEventInfo(this.e, a.v(this.e), this.e.getString(l.l(this.e)), localPendingIntent);
        paramInteger.flags = 16;
        this.f.notify(this.a + 1, paramInteger);
        if ((c.this.a(this.e)) && (!this.d.h))
        {
          this.f.cancel(this.a + 1);
          this.e.startActivity((Intent)localObject);
        }
        paramInteger = new Bundle();
        paramInteger.putString("filename", this.b);
        localObject = Message.obtain();
        ((Message)localObject).what = 5;
        ((Message)localObject).arg1 = 1;
        ((Message)localObject).arg2 = this.a;
        ((Message)localObject).setData(paramInteger);
        try
        {
          if (c.a(c.this).get(this.d) != null)
            ((Messenger)c.a(c.this).get(this.d)).send((Message)localObject);
          c.this.b(this.e, this.a);
          return;
        }
        catch (RemoteException paramInteger)
        {
          c.this.b(this.e, this.a);
          return;
        }
      }
      this.f.cancel(this.a + 1);
      paramInteger = new Bundle();
      paramInteger.putString("filename", this.b);
      Object localObject = Message.obtain();
      ((Message)localObject).what = 5;
      ((Message)localObject).arg1 = 3;
      ((Message)localObject).arg2 = this.a;
      ((Message)localObject).setData(paramInteger);
      try
      {
        if (c.a(c.this).get(this.d) != null)
          ((Messenger)c.a(c.this).get(this.d)).send((Message)localObject);
        c.this.b(this.e, this.a);
        return;
      }
      catch (RemoteException paramInteger)
      {
        c.this.b(this.e, this.a);
      }
    }

    protected void onPreExecute()
    {
      super.onPreExecute();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.update.net.c
 * JD-Core Version:    0.6.2
 */