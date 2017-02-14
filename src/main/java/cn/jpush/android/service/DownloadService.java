package cn.jpush.android.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.widget.RemoteViews;
import cn.jpush.android.api.m;
import cn.jpush.android.data.l;
import cn.jpush.android.data.p;
import cn.jpush.android.util.x;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DownloadService extends IntentService
{
  public static ConcurrentLinkedQueue<cn.jpush.android.data.d> a;
  private static Bundle b;
  private static final String[] z;
  private NotificationManager c;
  private cn.jpush.android.data.d d;
  private f e;
  private Notification f;
  private RemoteViews g;
  private Integer h = Integer.valueOf(0);
  private Integer i = Integer.valueOf(0);
  private Integer j = Integer.valueOf(0);
  private Integer k = Integer.valueOf(0);
  private Integer l = Integer.valueOf(0);
  private Handler m = new e(this);

  static
  {
    String[] arrayOfString = new String[22];
    int i1 = 0;
    Object localObject2 = "b\002h+\tt\037.o\030o\b4.\020eB//\rd\0022o7N8\017\0070B-\022\b6O3\017\017*U-\n\r&B \017\0022D(";
    int n = -1;
    Object localObject1 = arrayOfString;
    char[] arrayOfChar = ((String)localObject2).toCharArray();
    int i2 = arrayOfChar.length;
    int i5 = 0;
    int i3 = 0;
    int i7 = n;
    localObject2 = arrayOfChar;
    int i8 = i1;
    Object localObject3 = localObject1;
    int i4 = i2;
    Object localObject4;
    int i6;
    if (i2 <= 1)
    {
      localObject4 = localObject1;
      localObject1 = arrayOfChar;
      i6 = n;
      label68: i4 = i3;
      label71: localObject2 = localObject1;
      i5 = localObject2[i3];
      switch (i4 % 5)
      {
      default:
        n = 121;
      case 0:
      case 1:
      case 2:
      case 3:
      }
    }
    while (true)
    {
      localObject2[i3] = ((char)(n ^ i5));
      i4 += 1;
      if (i2 == 0)
      {
        i3 = i2;
        break label71;
      }
      i5 = i4;
      i4 = i2;
      localObject3 = localObject4;
      i8 = i1;
      localObject2 = localObject1;
      i7 = i6;
      i6 = i7;
      localObject1 = localObject2;
      i1 = i8;
      localObject4 = localObject3;
      i2 = i4;
      i3 = i5;
      if (i4 > i5)
        break label68;
      localObject1 = new String((char[])localObject2).intern();
      switch (i7)
      {
      default:
        localObject3[i8] = localObject1;
        i1 = 1;
        localObject2 = "c\003\"8";
        n = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i8] = localObject1;
        i1 = 2;
        localObject2 = "曵旜爎杭嶋上輑寊殔ｵ诶烕冽寈袼〃";
        n = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i8] = localObject1;
        i1 = 3;
        localObject2 = "守袩千嶳乲轼寠殓ｍ讎炸冗寏袄ほ";
        n = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i8] = localObject1;
        i1 = 4;
        localObject2 = "";
        n = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i8] = localObject1;
        i1 = 5;
        localObject2 = "";
        n = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i8] = localObject1;
        i1 = 6;
        localObject2 = "u\0052-\034";
        n = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i8] = localObject1;
        i1 = 7;
        localObject2 = "";
        n = 6;
        localObject1 = arrayOfString;
        break;
      case 6:
        localObject3[i8] = localObject1;
        i1 = 8;
        localObject2 = "u\t>5&q\036)&\013d\0375";
        n = 7;
        localObject1 = arrayOfString;
        break;
      case 7:
        localObject3[i8] = localObject1;
        i1 = 9;
        localObject2 = "o\0032(\037h\017'5\020n\002\031-\030x\00335";
        n = 8;
        localObject1 = arrayOfString;
        break;
      case 8:
        localObject3[i8] = localObject1;
        i1 = 10;
        localObject2 = "SH/%";
        n = 9;
        localObject1 = arrayOfString;
        break;
      case 9:
        localObject3[i8] = localObject1;
        i1 = 11;
        localObject2 = "o\0032(\037h\017'5\020n\002";
        n = 10;
        localObject1 = arrayOfString;
        break;
      case 10:
        localObject3[i8] = localObject1;
        i1 = 12;
        localObject2 = "q\036)&\013d\0375\036\033`\036";
        n = 11;
        localObject1 = arrayOfString;
        break;
      case 11:
        localObject3[i8] = localObject1;
        i1 = 13;
        localObject2 = "h\017)/";
        n = 12;
        localObject1 = arrayOfString;
        break;
      case 12:
        localObject3[i8] = localObject1;
        i1 = 14;
        localObject2 = "上輑贂滑奈敉の讱穌呷炸冗醋旱乲轼ｭ";
        n = 13;
        localObject1 = arrayOfString;
        break;
      case 13:
        localObject3[i8] = localObject1;
        i1 = 15;
        localObject2 = "归刡缗纝乴叮畄い穌呷伛纋纫乊輄＀";
        n = 14;
        localObject1 = arrayOfString;
        break;
      case 14:
        localObject3[i8] = localObject1;
        i1 = 16;
        localObject2 = "上輑奷赤ほ诶穡呈烸冂里旜乍輼ｸ";
        n = 15;
        localObject1 = arrayOfString;
        break;
      case 15:
        localObject3[i8] = localObject1;
        i1 = 17;
        localObject2 = "l\0033/\rd\b";
        n = 16;
        localObject1 = arrayOfString;
        break;
      case 16:
        localObject3[i8] = localObject1;
        i1 = 18;
        localObject2 = "上輑乫oW/L";
        n = 17;
        localObject1 = arrayOfString;
        break;
      case 17:
        localObject3[i8] = localObject1;
        i1 = 19;
        localObject2 = "E\0031/\025n\r\"\022\034s\032/\"\034";
        n = 18;
        localObject1 = arrayOfString;
        break;
      case 18:
        localObject3[i8] = localObject1;
        i1 = 20;
        localObject2 = "";
        n = 19;
        localObject1 = arrayOfString;
        break;
      case 19:
        localObject3[i8] = localObject1;
        i1 = 21;
        localObject2 = "o\0032\036\030t\030)3\fo";
        n = 20;
        localObject1 = arrayOfString;
        break;
      case 20:
        localObject3[i8] = localObject1;
        z = arrayOfString;
        a = new ConcurrentLinkedQueue();
        return;
        n = 1;
        continue;
        n = 108;
        continue;
        n = 70;
        continue;
        n = 65;
      }
    }
  }

  public DownloadService()
  {
    super(z[19]);
  }

  public static void a(Context paramContext)
  {
    int n = a.size();
    new StringBuilder(z[5]).append(n).toString();
    x.c();
    Object localObject = new ArrayList();
    while (true)
    {
      cn.jpush.android.data.d locald = (cn.jpush.android.data.d)a.poll();
      if (locald == null)
        break;
      if (locald.M)
      {
        new StringBuilder(z[4]).append(locald.c).toString();
        x.c();
        ServiceInterface.a(paramContext, locald);
      }
      else
      {
        x.b();
        ((ArrayList)localObject).add(locald);
      }
    }
    paramContext = ((ArrayList)localObject).iterator();
    while (paramContext.hasNext())
    {
      localObject = (cn.jpush.android.data.d)paramContext.next();
      a.offer(localObject);
    }
  }

  private void a(cn.jpush.android.data.d paramd, int paramInt, long paramLong1, long paramLong2)
  {
    if (this.f == null)
    {
      this.f = new Notification();
      this.f.icon = 17301633;
      this.f.when = System.currentTimeMillis();
      this.f.flags = 2;
      this.f.defaults = 4;
      localObject = new Intent();
      localObject = PendingIntent.getActivity(getApplicationContext(), paramInt, (Intent)localObject, 134217728);
      this.f.contentIntent = ((PendingIntent)localObject);
    }
    String str = paramd.A;
    Object localObject = z[18];
    int n = (int)((float)paramLong1 / (float)paramLong2 * 100.0F);
    paramd = (cn.jpush.android.data.d)localObject;
    if (paramLong2 > 0L)
      paramd = (String)localObject + n + "%";
    if ((this.l != null) && (this.l.intValue() > 0))
    {
      if (this.g == null)
      {
        this.g = new RemoteViews(getPackageName(), this.l.intValue());
        this.g.setTextViewText(this.h.intValue(), str);
        this.g.setImageViewResource(this.j.intValue(), cn.jpush.android.a.a);
      }
      this.g.setTextViewText(this.k.intValue(), n + "%");
      this.g.setProgressBar(this.i.intValue(), 100, n, false);
      this.f.contentView = this.g;
    }
    while (true)
    {
      this.c.notify(paramInt, this.f);
      return;
      localObject = new Intent();
      localObject = PendingIntent.getActivity(getApplicationContext(), paramInt, (Intent)localObject, 134217728);
      this.f.setLatestEventInfo(this, str, paramd, (PendingIntent)localObject);
    }
  }

  private void a(cn.jpush.android.data.d paramd, boolean paramBoolean)
  {
    boolean bool1 = true;
    boolean bool2 = paramd.c();
    Intent localIntent;
    if ((paramd.a()) && (!paramBoolean))
    {
      localIntent = new Intent();
      localIntent.putExtra(z[1], paramd);
      localIntent.setClass(getApplicationContext(), PushReceiver.class);
      localIntent.setAction(z[0]);
      if (paramd.c())
      {
        paramd.B = z[2];
        paramBoolean = bool1;
      }
    }
    while (true)
    {
      int n = m.a(paramd, 0);
      paramd = m.a(getApplicationContext(), n, localIntent, paramd, bool2, paramBoolean);
      if (paramd == null)
        break;
      this.c.notify(n, paramd);
      return;
      paramd.B = z[3];
      paramBoolean = bool1;
      continue;
      localIntent = cn.jpush.android.util.a.a(getApplicationContext(), paramd, false);
      paramBoolean = false;
    }
    x.f();
  }

  public static boolean a()
  {
    return a.size() > 0;
  }

  public void onCreate()
  {
    x.c();
    super.onCreate();
    this.e = new f(this, getApplicationContext());
    this.c = ((NotificationManager)getSystemService(z[11]));
    if (b == null)
      b = new Bundle();
    try
    {
      if (this.l.intValue() == 0)
      {
        HashMap localHashMap = m.a(z[7], new String[] { z[9] });
        if (localHashMap.size() > 0)
          this.l = ((Integer)localHashMap.get(z[9]));
        localHashMap = m.a(z[10], new String[] { z[6], z[12], z[13], z[8] });
        if (localHashMap.size() > 0)
        {
          this.h = ((Integer)localHashMap.get(z[6]));
          this.i = ((Integer)localHashMap.get(z[12]));
          this.j = ((Integer)localHashMap.get(z[13]));
          this.k = ((Integer)localHashMap.get(z[8]));
        }
      }
      return;
    }
    catch (Exception localException)
    {
      x.i();
    }
  }

  public void onDestroy()
  {
    x.c();
    super.onDestroy();
  }

  protected void onHandleIntent(Intent paramIntent)
  {
    x.c();
    this.d = ((cn.jpush.android.data.d)paramIntent.getSerializableExtra(z[1]));
    if (this.d == null)
    {
      x.e();
      return;
    }
    if (!Environment.getExternalStorageState().equals(z[17]))
    {
      x.e();
      this.e.sendEmptyMessage(0);
      return;
    }
    if (this.d.N)
    {
      x.c();
      return;
    }
    if (this.d.L)
      ServiceInterface.a(this.d.c, 1012, this);
    if (!a.contains(this.d))
      a.offer(this.d);
    int i1 = m.a(this.d, 1);
    paramIntent = this.d;
    boolean bool;
    if (paramIntent.f())
    {
      bool = true;
      Thread.currentThread().setPriority(1);
      new a(this, this.d, b, new d(this, bool, i1), 3000);
      return;
    }
    int n;
    if (paramIntent.a())
    {
      l locall = (l)paramIntent;
      if (!p.a(locall.Y, locall.Z, this))
      {
        a(paramIntent, i1, 0L, 0L);
        n = 0;
      }
    }
    while (true)
    {
      if ((n != 0) || (paramIntent.b()))
      {
        bool = true;
        break;
        n = 1;
        continue;
      }
      bool = false;
      break;
      n = 0;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.service.DownloadService
 * JD-Core Version:    0.6.2
 */