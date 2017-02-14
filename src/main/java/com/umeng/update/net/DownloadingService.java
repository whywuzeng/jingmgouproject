package com.umeng.update.net;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Debug;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.SparseArray;
import android.widget.Toast;
import com.umeng.update.util.DeltaUpdate;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import u.upd.a;
import u.upd.l;
import u.upd.n;

public class DownloadingService extends Service
{
  private static final long C = 8000L;
  private static final long D = 500L;
  private static Map<a.a, Messenger> E = new HashMap();
  private static SparseArray<c.b> F = new SparseArray();
  private static Boolean I = Boolean.valueOf(false);
  static final int a = 1;
  static final int b = 2;
  static final int c = 3;
  static final int d = 4;
  static final int e = 5;
  static final int f = 6;
  public static final int g = 0;
  public static final int h = 1;
  public static final int i = 2;
  public static final int j = 3;
  public static final int k = 4;
  public static final int l = 5;
  public static final int m = 6;
  public static final int n = 7;
  static final int o = 100;
  static final String p = "filename";
  public static boolean r = false;
  private static final String t = DownloadingService.class.getName();
  private static final long w = 104857600L;
  private static final long x = 10485760L;
  private static final long y = 259200000L;
  private static final int z = 3;
  private Context A;
  private Handler B;
  private e G;
  private boolean H = true;
  a q;
  final Messenger s = new Messenger(new c());
  private NotificationManager u;
  private c v;

  static
  {
    r = false;
  }

  private void a(a.a parama)
  {
    int i1 = 0;
    u.upd.b.c(t, "startDownload([mComponentName:" + parama.a + " mTitle:" + parama.b + " mUrl:" + parama.c + "])");
    int i2 = this.v.a(parama);
    b localb = new b(getApplicationContext(), parama, i2, 0, this.q);
    parama = new c.b(parama, i2);
    this.G.a(i2);
    parama.a(F);
    parama.a = localb;
    localb.start();
    e();
    if (r)
      while (i1 < F.size())
      {
        parama = (c.b)F.valueAt(i1);
        u.upd.b.c(t, "Running task " + parama.e.b);
        i1 += 1;
      }
  }

  private void a(final String paramString)
  {
    synchronized (I)
    {
      if (!I.booleanValue())
      {
        u.upd.b.c(t, "show single toast.[" + paramString + "]");
        I = Boolean.valueOf(true);
        this.B.post(new Runnable()
        {
          public void run()
          {
            Toast.makeText(DownloadingService.b(DownloadingService.this), paramString, 0).show();
          }
        });
        this.B.postDelayed(new Runnable()
        {
          public void run()
          {
            DownloadingService.a(Boolean.valueOf(false));
          }
        }
        , 1200L);
      }
      return;
    }
  }

  private void d()
  {
    Iterator localIterator = this.G.a().iterator();
    while (localIterator.hasNext())
    {
      Integer localInteger = (Integer)localIterator.next();
      this.u.cancel(localInteger.intValue());
    }
  }

  private void e()
  {
    if (r)
    {
      int i1 = E.size();
      int i2 = F.size();
      u.upd.b.a(t, "Client size =" + i1 + "   cacheSize = " + i2);
      if (i1 != i2)
        throw new RuntimeException("Client size =" + i1 + "   cacheSize = " + i2);
    }
  }

  public IBinder onBind(Intent paramIntent)
  {
    u.upd.b.c(t, "onBind ");
    return this.s.getBinder();
  }

  public void onCreate()
  {
    super.onCreate();
    if (r)
    {
      u.upd.b.a = true;
      Debug.waitForDebugger();
    }
    u.upd.b.c(t, "onCreate ");
    this.u = ((NotificationManager)getSystemService("notification"));
    this.A = this;
    this.G = new e(this.A);
    this.v = new c(F, E, this.G);
    this.B = new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        switch (paramAnonymousMessage.what)
        {
        default:
          return;
        case 5:
          locala = (a.a)paramAnonymousMessage.obj;
          i = paramAnonymousMessage.arg2;
          while (true)
          {
            try
            {
              localObject1 = paramAnonymousMessage.getData().getString("filename");
              j.a((String)localObject1, 39, -1, -1);
              u.upd.b.c(DownloadingService.a(), "Cancel old notification....");
              localObject2 = new Intent("android.intent.action.VIEW");
              ((Intent)localObject2).addFlags(268435456);
              ((Intent)localObject2).setDataAndType(Uri.fromFile(new File((String)localObject1)), "application/vnd.android.package-archive");
              localObject3 = PendingIntent.getActivity(DownloadingService.b(DownloadingService.this), 0, (Intent)localObject2, 134217728);
              if (locala.h)
              {
                paramAnonymousMessage = new Notification(17301634, DownloadingService.b(DownloadingService.this).getString(l.m(DownloadingService.b(DownloadingService.this))), System.currentTimeMillis());
                paramAnonymousMessage.setLatestEventInfo(DownloadingService.b(DownloadingService.this), locala.b, DownloadingService.b(DownloadingService.this).getString(l.m(DownloadingService.b(DownloadingService.this))), (PendingIntent)localObject3);
                paramAnonymousMessage.flags = 16;
                DownloadingService.a(DownloadingService.this, (NotificationManager)DownloadingService.this.getSystemService("notification"));
                DownloadingService.c(DownloadingService.this).notify(i + 1, paramAnonymousMessage);
                u.upd.b.c(DownloadingService.a(), "Show new  notification....");
                boolean bool = DownloadingService.a(DownloadingService.this).a(DownloadingService.b(DownloadingService.this));
                u.upd.b.c(DownloadingService.a(), String.format("isAppOnForeground = %1$B", new Object[] { Boolean.valueOf(bool) }));
                if ((bool) && (!locala.h))
                {
                  DownloadingService.c(DownloadingService.this).cancel(i + 1);
                  DownloadingService.b(DownloadingService.this).startActivity((Intent)localObject2);
                }
                u.upd.b.a(DownloadingService.a(), String.format("%1$10s downloaded. Saved to: %2$s", new Object[] { locala.b, localObject1 }));
                return;
              }
            }
            catch (Exception paramAnonymousMessage)
            {
              u.upd.b.b(DownloadingService.a(), "can not install. " + paramAnonymousMessage.getMessage());
              DownloadingService.c(DownloadingService.this).cancel(i + 1);
              return;
            }
            paramAnonymousMessage = new Notification(17301634, DownloadingService.b(DownloadingService.this).getString(l.k(DownloadingService.b(DownloadingService.this))), System.currentTimeMillis());
            paramAnonymousMessage.setLatestEventInfo(DownloadingService.b(DownloadingService.this), locala.b, DownloadingService.b(DownloadingService.this).getString(l.k(DownloadingService.b(DownloadingService.this))), (PendingIntent)localObject3);
          }
        case 6:
        }
        a.a locala = (a.a)paramAnonymousMessage.obj;
        int i = paramAnonymousMessage.arg2;
        paramAnonymousMessage = paramAnonymousMessage.getData().getString("filename");
        DownloadingService.c(DownloadingService.this).cancel(i);
        Object localObject1 = new Notification(17301633, DownloadingService.b(DownloadingService.this).getString(l.n(DownloadingService.b(DownloadingService.this))), System.currentTimeMillis());
        Object localObject2 = PendingIntent.getActivity(DownloadingService.b(DownloadingService.this), 0, new Intent(), 134217728);
        ((Notification)localObject1).setLatestEventInfo(DownloadingService.b(DownloadingService.this), a.v(DownloadingService.b(DownloadingService.this)), DownloadingService.b(DownloadingService.this).getString(l.n(DownloadingService.b(DownloadingService.this))), (PendingIntent)localObject2);
        DownloadingService.c(DownloadingService.this).notify(i + 1, (Notification)localObject1);
        localObject1 = paramAnonymousMessage.replace(".patch", ".apk");
        localObject2 = DeltaUpdate.a(DownloadingService.this);
        Object localObject3 = DownloadingService.a(DownloadingService.this);
        localObject3.getClass();
        new c.c((c)localObject3, DownloadingService.b(DownloadingService.this), i, locala, (String)localObject1).execute(new String[] { localObject2, localObject1, paramAnonymousMessage });
      }
    };
    this.q = new a()
    {
      SparseArray<Long> a = new SparseArray();

      public void a(int paramAnonymousInt)
      {
        int j = 0;
        if (DownloadingService.b().indexOfKey(paramAnonymousInt) >= 0)
        {
          c.b localb = (c.b)DownloadingService.b().get(paramAnonymousInt);
          Object localObject = localb.f;
          int i = j;
          if (localObject != null)
          {
            i = j;
            if (localObject[1] > 0L)
            {
              j = (int)((float)localObject[0] / (float)localObject[1] * 100.0F);
              i = j;
              if (j > 100)
                i = 99;
            }
          }
          if (!localb.e.h)
          {
            this.a.put(paramAnonymousInt, Long.valueOf(-1L));
            localObject = DownloadingService.a(DownloadingService.this).a(DownloadingService.this, localb.e, paramAnonymousInt, i);
            localb.b = ((c.a)localObject);
            DownloadingService.c(DownloadingService.this).notify(paramAnonymousInt, ((c.a)localObject).d());
          }
        }
      }

      public void a(int paramAnonymousInt1, int paramAnonymousInt2)
      {
        if (DownloadingService.b().indexOfKey(paramAnonymousInt1) >= 0)
        {
          Object localObject = (c.b)DownloadingService.b().get(paramAnonymousInt1);
          a.a locala = ((c.b)localObject).e;
          long l = System.currentTimeMillis();
          if ((!locala.h) && (l - ((Long)this.a.get(paramAnonymousInt1)).longValue() > 500L))
          {
            this.a.put(paramAnonymousInt1, Long.valueOf(l));
            localObject = ((c.b)localObject).b;
            ((c.a)localObject).a(100, paramAnonymousInt2, false).a(String.valueOf(paramAnonymousInt2) + "%");
            DownloadingService.c(DownloadingService.this).notify(paramAnonymousInt1, ((c.a)localObject).d());
          }
          u.upd.b.c(DownloadingService.a(), String.format("%3$10s Notification: mNotificationId = %1$15s\t|\tprogress = %2$15s", new Object[] { Integer.valueOf(paramAnonymousInt1), Integer.valueOf(paramAnonymousInt2), locala.b }));
        }
      }

      public void a(int paramAnonymousInt, Exception paramAnonymousException)
      {
        if (DownloadingService.b().indexOfKey(paramAnonymousInt) >= 0)
          DownloadingService.a(DownloadingService.this).b(DownloadingService.b(DownloadingService.this), paramAnonymousInt);
      }

      public void a(int paramAnonymousInt, String paramAnonymousString)
      {
        Object localObject;
        Bundle localBundle;
        if (DownloadingService.b().indexOfKey(paramAnonymousInt) >= 0)
        {
          localObject = (c.b)DownloadingService.b().get(paramAnonymousInt);
          if (localObject != null)
          {
            localObject = ((c.b)localObject).e;
            b.a(DownloadingService.b(DownloadingService.this)).a(((a.a)localObject).a, ((a.a)localObject).c, 100);
            localBundle = new Bundle();
            localBundle.putString("filename", paramAnonymousString);
            if (!((a.a)localObject).a.equalsIgnoreCase("delta_update"))
              break label126;
            paramAnonymousString = Message.obtain();
            paramAnonymousString.what = 6;
            paramAnonymousString.arg1 = 1;
            paramAnonymousString.obj = localObject;
            paramAnonymousString.arg2 = paramAnonymousInt;
            paramAnonymousString.setData(localBundle);
            DownloadingService.d(DownloadingService.this).sendMessage(paramAnonymousString);
          }
        }
        return;
        label126: paramAnonymousString = Message.obtain();
        paramAnonymousString.what = 5;
        paramAnonymousString.arg1 = 1;
        paramAnonymousString.obj = localObject;
        paramAnonymousString.arg2 = paramAnonymousInt;
        paramAnonymousString.setData(localBundle);
        DownloadingService.d(DownloadingService.this).sendMessage(paramAnonymousString);
        paramAnonymousString = Message.obtain();
        paramAnonymousString.what = 5;
        paramAnonymousString.arg1 = 1;
        paramAnonymousString.arg2 = paramAnonymousInt;
        paramAnonymousString.setData(localBundle);
        try
        {
          if (DownloadingService.c().get(localObject) != null)
            ((Messenger)DownloadingService.c().get(localObject)).send(paramAnonymousString);
          DownloadingService.a(DownloadingService.this).b(DownloadingService.b(DownloadingService.this), paramAnonymousInt);
          return;
        }
        catch (RemoteException paramAnonymousString)
        {
          DownloadingService.a(DownloadingService.this).b(DownloadingService.b(DownloadingService.this), paramAnonymousInt);
        }
      }
    };
  }

  public void onDestroy()
  {
    try
    {
      b.a(getApplicationContext()).a(259200);
      b.a(getApplicationContext()).finalize();
      super.onDestroy();
      return;
    }
    catch (Exception localException)
    {
      while (true)
        u.upd.b.b(t, localException.getMessage());
    }
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    if ((paramIntent != null) && (paramIntent.getExtras() != null) && (paramIntent.getExtras().containsKey("com.umeng.broadcast.download.msg")))
      this.v.a(this, paramIntent);
    if ((Build.VERSION.SDK_INT >= 19) && ((this.G.b()) || (this.H)));
    try
    {
      paramIntent = new Intent(getApplicationContext(), getClass());
      paramIntent.setPackage(getPackageName());
      paramIntent = PendingIntent.getService(getApplicationContext(), 1, paramIntent, 1073741824);
      ((AlarmManager)getApplicationContext().getSystemService("alarm")).set(3, SystemClock.elapsedRealtime() + 5000L, paramIntent);
      label122: if (this.H)
      {
        d();
        this.H = false;
      }
      return 1;
    }
    catch (Exception paramIntent)
    {
      break label122;
    }
  }

  static abstract interface a
  {
    public abstract void a(int paramInt);

    public abstract void a(int paramInt1, int paramInt2);

    public abstract void a(int paramInt, Exception paramException);

    public abstract void a(int paramInt, String paramString);
  }

  class b extends Thread
  {
    private Context b;
    private boolean c;
    private File d;
    private int e = 0;
    private long f = -1L;
    private long g = -1L;
    private int h = -1;
    private int i;
    private DownloadingService.a j;
    private a.a k;

    public b(Context parama, a.a paramInt1, int paramInt2, int parama1, DownloadingService.a arg6)
    {
      try
      {
        this.b = parama;
        this.k = paramInt1;
        this.e = parama1;
        if (DownloadingService.b().indexOfKey(paramInt2) >= 0)
        {
          this$1 = ((c.b)DownloadingService.b().get(paramInt2)).f;
          if ((DownloadingService.this != null) && (DownloadingService.this.length > 1))
          {
            this.f = DownloadingService.this[0];
            this.g = DownloadingService.this[1];
          }
        }
        Object localObject;
        this.j = localObject;
        this.i = paramInt2;
        this$1 = new boolean[1];
        this.d = j.a("/apk", parama, DownloadingService.this);
        this.c = DownloadingService.this[0];
        if (this.c);
        for (long l = 104857600L; ; l = 10485760L)
        {
          j.a(this.d, l, 259200000L);
          this$1 = a(this.k);
          this.d = new File(this.d, DownloadingService.this);
          return;
        }
      }
      catch (Exception this$1)
      {
        u.upd.b.c(DownloadingService.a(), DownloadingService.this.getMessage(), DownloadingService.this);
        this.j.a(this.i, DownloadingService.this);
      }
    }

    private String a(a.a parama)
    {
      if (this.k.e != null);
      for (parama = this.k.e + ".apk.tmp"; ; parama = n.a(this.k.c) + ".apk.tmp")
      {
        Object localObject = parama;
        if (this.k.a.equalsIgnoreCase("delta_update"))
          localObject = parama.replace(".apk", ".patch");
        return localObject;
      }
    }

    private HttpURLConnection a(URL paramURL, File paramFile)
      throws IOException
    {
      paramURL = (HttpURLConnection)paramURL.openConnection();
      paramURL.setRequestMethod("GET");
      paramURL.setRequestProperty("Accept-Encoding", "identity");
      paramURL.addRequestProperty("Connection", "keep-alive");
      paramURL.setConnectTimeout(5000);
      paramURL.setReadTimeout(10000);
      if ((paramFile.exists()) && (paramFile.length() > 0L))
      {
        u.upd.b.c(DownloadingService.a(), String.format(this.k.b + " getFileLength: %1$15s", new Object[] { Long.valueOf(paramFile.length()) }));
        paramURL.setRequestProperty("Range", "bytes=" + paramFile.length() + "-");
      }
      return paramURL;
    }

    private void a()
    {
      u.upd.b.c(DownloadingService.a(), "wait for repeating Test network repeat count=" + this.e);
      try
      {
        if (!this.k.g)
        {
          Thread.sleep(8000L);
          if (this.g < 1L)
          {
            a(false);
            return;
          }
          a(true);
          return;
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        a(localInterruptedException);
        DownloadingService.a(DownloadingService.this).b(this.b, this.i);
        return;
      }
      Object localObject = (c.b)DownloadingService.b().get(this.i);
      ((c.b)localObject).f[0] = this.f;
      ((c.b)localObject).f[1] = this.g;
      ((c.b)localObject).f[2] = this.e;
      localObject = f.a(this.i, "continue");
      Intent localIntent = new Intent(this.b, DownloadingService.class);
      localIntent.putExtra("com.umeng.broadcast.download.msg", (String)localObject);
      DownloadingService.a(DownloadingService.this).a(DownloadingService.this, localIntent);
      DownloadingService.a(DownloadingService.this, this.b.getString(l.c(this.b)));
      u.upd.b.c(DownloadingService.a(), "changed play state button on op-notification.");
    }

    private void a(File paramFile, String paramString)
      throws RemoteException
    {
      u.upd.b.c(DownloadingService.a(), "itemMd5 " + this.k.d);
      u.upd.b.c(DownloadingService.a(), "fileMd5 " + n.a(paramFile));
      if ((this.k.d != null) && (!this.k.d.equalsIgnoreCase(n.a(paramFile))))
      {
        if (!this.k.a.equalsIgnoreCase("delta_update"))
          break label233;
        DownloadingService.c(DownloadingService.this).cancel(this.i);
        paramFile = new Bundle();
        paramFile.putString("filename", paramString);
        paramString = Message.obtain();
        paramString.what = 5;
        paramString.arg1 = 3;
        paramString.arg2 = this.i;
        paramString.setData(paramFile);
      }
      label233: 
      do
      {
        try
        {
          if (DownloadingService.c().get(this.k) != null)
            ((Messenger)DownloadingService.c().get(this.k)).send(paramString);
          DownloadingService.a(DownloadingService.this).b(this.b, this.i);
          return;
        }
        catch (RemoteException paramFile)
        {
          DownloadingService.a(DownloadingService.this).b(this.b, this.i);
          return;
        }
        ((Messenger)DownloadingService.c().get(this.k)).send(Message.obtain(null, 5, 0, 0));
      }
      while (this.k.h);
      DownloadingService.a(DownloadingService.this).b(this.b, this.i);
      paramFile = new Notification(17301634, this.b.getString(l.i(this.b)), System.currentTimeMillis());
      paramString = PendingIntent.getActivity(this.b, 0, new Intent(), 0);
      paramFile.setLatestEventInfo(this.b, a.v(this.b), this.k.b + this.b.getString(l.i(this.b)), paramString);
      paramFile.flags |= 16;
      DownloadingService.c(DownloadingService.this).notify(this.i, paramFile);
    }

    private void a(Exception paramException)
    {
      u.upd.b.b(DownloadingService.a(), "can not install. " + paramException.getMessage());
      if (this.j != null)
        this.j.a(this.i, paramException);
      DownloadingService.a(DownloadingService.this).a(this.k, this.f, this.g, this.e);
    }

    // ERROR //
    private void a(boolean paramBoolean)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore 11
      //   3: iconst_0
      //   4: istore 4
      //   6: aload_0
      //   7: getfield 81	com/umeng/update/net/DownloadingService$b:d	Ljava/io/File;
      //   10: invokevirtual 413	java/io/File:getName	()Ljava/lang/String;
      //   13: astore 12
      //   15: new 415	java/io/FileOutputStream
      //   18: dup
      //   19: aload_0
      //   20: getfield 81	com/umeng/update/net/DownloadingService$b:d	Ljava/io/File;
      //   23: iconst_1
      //   24: invokespecial 418	java/io/FileOutputStream:<init>	(Ljava/io/File;Z)V
      //   27: astore 10
      //   29: aload_0
      //   30: getfield 83	com/umeng/update/net/DownloadingService$b:c	Z
      //   33: ifne +2133 -> 2166
      //   36: aload_0
      //   37: getfield 81	com/umeng/update/net/DownloadingService$b:d	Ljava/io/File;
      //   40: invokevirtual 421	java/io/File:getAbsolutePath	()Ljava/lang/String;
      //   43: iconst_3
      //   44: invokestatic 424	com/umeng/update/net/j:a	(Ljava/lang/String;I)Z
      //   47: ifne +2119 -> 2166
      //   50: aload 10
      //   52: invokevirtual 427	java/io/FileOutputStream:close	()V
      //   55: aload_0
      //   56: getfield 48	com/umeng/update/net/DownloadingService$b:b	Landroid/content/Context;
      //   59: aload 12
      //   61: ldc_w 428
      //   64: invokevirtual 432	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
      //   67: astore 11
      //   69: aload_0
      //   70: aload_0
      //   71: getfield 48	com/umeng/update/net/DownloadingService$b:b	Landroid/content/Context;
      //   74: aload 12
      //   76: invokevirtual 436	android/content/Context:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
      //   79: putfield 81	com/umeng/update/net/DownloadingService$b:d	Ljava/io/File;
      //   82: aload 11
      //   84: astore 10
      //   86: invokestatic 103	com/umeng/update/net/DownloadingService:a	()Ljava/lang/String;
      //   89: ldc_w 438
      //   92: iconst_2
      //   93: anewarray 213	java/lang/Object
      //   96: dup
      //   97: iconst_0
      //   98: aload_0
      //   99: getfield 50	com/umeng/update/net/DownloadingService$b:k	Lcom/umeng/update/net/a$a;
      //   102: getfield 155	com/umeng/update/net/a$a:c	Ljava/lang/String;
      //   105: aastore
      //   106: dup
      //   107: iconst_1
      //   108: aload_0
      //   109: getfield 81	com/umeng/update/net/DownloadingService$b:d	Ljava/io/File;
      //   112: invokevirtual 421	java/io/File:getAbsolutePath	()Ljava/lang/String;
      //   115: aastore
      //   116: invokestatic 223	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   119: invokestatic 225	u/upd/b:c	(Ljava/lang/String;Ljava/lang/String;)V
      //   122: aload_0
      //   123: new 165	java/net/URL
      //   126: dup
      //   127: aload_0
      //   128: getfield 50	com/umeng/update/net/DownloadingService$b:k	Lcom/umeng/update/net/a$a;
      //   131: getfield 155	com/umeng/update/net/a$a:c	Ljava/lang/String;
      //   134: invokespecial 440	java/net/URL:<init>	(Ljava/lang/String;)V
      //   137: aload_0
      //   138: getfield 81	com/umeng/update/net/DownloadingService$b:d	Ljava/io/File;
      //   141: invokespecial 442	com/umeng/update/net/DownloadingService$b:a	(Ljava/net/URL;Ljava/io/File;)Ljava/net/HttpURLConnection;
      //   144: astore 12
      //   146: aload 12
      //   148: invokevirtual 445	java/net/HttpURLConnection:connect	()V
      //   151: aload 12
      //   153: invokevirtual 449	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
      //   156: astore 11
      //   158: iload_1
      //   159: ifne +209 -> 368
      //   162: lconst_0
      //   163: lstore 8
      //   165: lload 8
      //   167: lstore 6
      //   169: aload 10
      //   171: astore 14
      //   173: aload 11
      //   175: astore 13
      //   177: aload_0
      //   178: getfield 81	com/umeng/update/net/DownloadingService$b:d	Ljava/io/File;
      //   181: invokevirtual 203	java/io/File:exists	()Z
      //   184: ifeq +46 -> 230
      //   187: lload 8
      //   189: lstore 6
      //   191: aload 10
      //   193: astore 14
      //   195: aload 11
      //   197: astore 13
      //   199: aload_0
      //   200: getfield 81	com/umeng/update/net/DownloadingService$b:d	Ljava/io/File;
      //   203: invokevirtual 207	java/io/File:length	()J
      //   206: lconst_0
      //   207: lcmp
      //   208: ifle +22 -> 230
      //   211: aload 10
      //   213: astore 14
      //   215: aload 11
      //   217: astore 13
      //   219: lconst_0
      //   220: aload_0
      //   221: getfield 81	com/umeng/update/net/DownloadingService$b:d	Ljava/io/File;
      //   224: invokevirtual 207	java/io/File:length	()J
      //   227: ladd
      //   228: lstore 6
      //   230: aload 10
      //   232: astore 14
      //   234: aload 11
      //   236: astore 13
      //   238: aload_0
      //   239: lload 6
      //   241: putfield 42	com/umeng/update/net/DownloadingService$b:f	J
      //   244: aload 10
      //   246: astore 14
      //   248: aload 11
      //   250: astore 13
      //   252: aload_0
      //   253: lload 6
      //   255: aload 12
      //   257: invokevirtual 453	java/net/HttpURLConnection:getContentLength	()I
      //   260: i2l
      //   261: ladd
      //   262: putfield 44	com/umeng/update/net/DownloadingService$b:g	J
      //   265: aload 10
      //   267: astore 14
      //   269: aload 11
      //   271: astore 13
      //   273: invokestatic 103	com/umeng/update/net/DownloadingService:a	()Ljava/lang/String;
      //   276: ldc_w 455
      //   279: iconst_1
      //   280: anewarray 213	java/lang/Object
      //   283: dup
      //   284: iconst_0
      //   285: aload_0
      //   286: getfield 42	com/umeng/update/net/DownloadingService$b:f	J
      //   289: invokestatic 219	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   292: aastore
      //   293: invokestatic 223	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   296: invokestatic 225	u/upd/b:c	(Ljava/lang/String;Ljava/lang/String;)V
      //   299: aload 10
      //   301: astore 14
      //   303: aload 11
      //   305: astore 13
      //   307: invokestatic 103	com/umeng/update/net/DownloadingService:a	()Ljava/lang/String;
      //   310: ldc_w 457
      //   313: iconst_1
      //   314: anewarray 213	java/lang/Object
      //   317: dup
      //   318: iconst_0
      //   319: aload 12
      //   321: invokevirtual 453	java/net/HttpURLConnection:getContentLength	()I
      //   324: invokestatic 462	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   327: aastore
      //   328: invokestatic 223	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   331: invokestatic 225	u/upd/b:c	(Ljava/lang/String;Ljava/lang/String;)V
      //   334: aload 10
      //   336: astore 14
      //   338: aload 11
      //   340: astore 13
      //   342: invokestatic 103	com/umeng/update/net/DownloadingService:a	()Ljava/lang/String;
      //   345: ldc_w 464
      //   348: iconst_1
      //   349: anewarray 213	java/lang/Object
      //   352: dup
      //   353: iconst_0
      //   354: aload_0
      //   355: getfield 44	com/umeng/update/net/DownloadingService$b:g	J
      //   358: invokestatic 219	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   361: aastore
      //   362: invokestatic 223	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   365: invokestatic 225	u/upd/b:c	(Ljava/lang/String;Ljava/lang/String;)V
      //   368: aload 10
      //   370: astore 14
      //   372: aload 11
      //   374: astore 13
      //   376: sipush 4096
      //   379: newarray byte
      //   381: astore 12
      //   383: aload 10
      //   385: astore 14
      //   387: aload 11
      //   389: astore 13
      //   391: invokestatic 103	com/umeng/update/net/DownloadingService:a	()Ljava/lang/String;
      //   394: new 125	java/lang/StringBuilder
      //   397: dup
      //   398: invokespecial 126	java/lang/StringBuilder:<init>	()V
      //   401: aload_0
      //   402: getfield 50	com/umeng/update/net/DownloadingService$b:k	Lcom/umeng/update/net/a$a;
      //   405: getfield 209	com/umeng/update/net/a$a:b	Ljava/lang/String;
      //   408: invokevirtual 130	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   411: ldc_w 466
      //   414: invokevirtual 130	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   417: aload_0
      //   418: getfield 44	com/umeng/update/net/DownloadingService$b:g	J
      //   421: invokestatic 469	java/lang/String:valueOf	(J)Ljava/lang/String;
      //   424: invokevirtual 130	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   427: invokevirtual 135	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   430: invokestatic 225	u/upd/b:c	(Ljava/lang/String;Ljava/lang/String;)V
      //   433: aload 10
      //   435: astore 14
      //   437: aload 11
      //   439: astore 13
      //   441: aload_0
      //   442: getfield 48	com/umeng/update/net/DownloadingService$b:b	Landroid/content/Context;
      //   445: invokestatic 474	com/umeng/update/net/b:a	(Landroid/content/Context;)Lcom/umeng/update/net/b;
      //   448: aload_0
      //   449: getfield 50	com/umeng/update/net/DownloadingService$b:k	Lcom/umeng/update/net/a$a;
      //   452: getfield 137	com/umeng/update/net/a$a:a	Ljava/lang/String;
      //   455: aload_0
      //   456: getfield 50	com/umeng/update/net/DownloadingService$b:k	Lcom/umeng/update/net/a$a;
      //   459: getfield 155	com/umeng/update/net/a$a:c	Ljava/lang/String;
      //   462: invokevirtual 477	com/umeng/update/net/b:a	(Ljava/lang/String;Ljava/lang/String;)Z
      //   465: pop
      //   466: iconst_0
      //   467: istore_2
      //   468: aload 10
      //   470: astore 14
      //   472: aload 11
      //   474: astore 13
      //   476: aload_0
      //   477: getfield 46	com/umeng/update/net/DownloadingService$b:h	I
      //   480: ifge +1681 -> 2161
      //   483: aload 10
      //   485: astore 14
      //   487: aload 11
      //   489: astore 13
      //   491: aload 11
      //   493: aload 12
      //   495: invokevirtual 483	java/io/InputStream:read	([B)I
      //   498: istore_3
      //   499: iload_3
      //   500: ifle +1661 -> 2161
      //   503: aload 10
      //   505: astore 14
      //   507: aload 11
      //   509: astore 13
      //   511: aload 10
      //   513: aload 12
      //   515: iconst_0
      //   516: iload_3
      //   517: invokevirtual 487	java/io/FileOutputStream:write	([BII)V
      //   520: aload 10
      //   522: astore 14
      //   524: aload 11
      //   526: astore 13
      //   528: aload_0
      //   529: aload_0
      //   530: getfield 42	com/umeng/update/net/DownloadingService$b:f	J
      //   533: iload_3
      //   534: i2l
      //   535: ladd
      //   536: putfield 42	com/umeng/update/net/DownloadingService$b:f	J
      //   539: iload_2
      //   540: iconst_1
      //   541: iadd
      //   542: istore_3
      //   543: iload_2
      //   544: bipush 50
      //   546: irem
      //   547: ifne +1609 -> 2156
      //   550: aload 10
      //   552: astore 14
      //   554: aload 11
      //   556: astore 13
      //   558: aload_0
      //   559: getfield 48	com/umeng/update/net/DownloadingService$b:b	Landroid/content/Context;
      //   562: invokestatic 491	u/upd/a:l	(Landroid/content/Context;)Z
      //   565: ifne +150 -> 715
      //   568: iload 4
      //   570: istore_2
      //   571: aload 10
      //   573: astore 14
      //   575: aload 11
      //   577: astore 13
      //   579: aload 11
      //   581: invokevirtual 492	java/io/InputStream:close	()V
      //   584: aload 10
      //   586: astore 14
      //   588: aload 11
      //   590: astore 13
      //   592: aload 10
      //   594: invokevirtual 427	java/io/FileOutputStream:close	()V
      //   597: aload 10
      //   599: astore 14
      //   601: aload 11
      //   603: astore 13
      //   605: aload_0
      //   606: getfield 46	com/umeng/update/net/DownloadingService$b:h	I
      //   609: iconst_1
      //   610: if_icmpne +513 -> 1123
      //   613: aload 10
      //   615: astore 14
      //   617: aload 11
      //   619: astore 13
      //   621: invokestatic 53	com/umeng/update/net/DownloadingService:b	()Landroid/util/SparseArray;
      //   624: aload_0
      //   625: getfield 72	com/umeng/update/net/DownloadingService$b:i	I
      //   628: invokevirtual 63	android/util/SparseArray:get	(I)Ljava/lang/Object;
      //   631: checkcast 65	com/umeng/update/net/c$b
      //   634: astore 12
      //   636: aload 10
      //   638: astore 14
      //   640: aload 11
      //   642: astore 13
      //   644: aload 12
      //   646: getfield 68	com/umeng/update/net/c$b:f	[J
      //   649: iconst_0
      //   650: aload_0
      //   651: getfield 42	com/umeng/update/net/DownloadingService$b:f	J
      //   654: lastore
      //   655: aload 10
      //   657: astore 14
      //   659: aload 11
      //   661: astore 13
      //   663: aload 12
      //   665: getfield 68	com/umeng/update/net/c$b:f	[J
      //   668: iconst_1
      //   669: aload_0
      //   670: getfield 44	com/umeng/update/net/DownloadingService$b:g	J
      //   673: lastore
      //   674: aload 10
      //   676: astore 14
      //   678: aload 11
      //   680: astore 13
      //   682: aload 12
      //   684: getfield 68	com/umeng/update/net/c$b:f	[J
      //   687: iconst_2
      //   688: aload_0
      //   689: getfield 38	com/umeng/update/net/DownloadingService$b:e	I
      //   692: i2l
      //   693: lastore
      //   694: aload 11
      //   696: ifnull +8 -> 704
      //   699: aload 11
      //   701: invokevirtual 492	java/io/InputStream:close	()V
      //   704: aload 10
      //   706: ifnull +8 -> 714
      //   709: aload 10
      //   711: invokevirtual 427	java/io/FileOutputStream:close	()V
      //   714: return
      //   715: aload 10
      //   717: astore 14
      //   719: aload 11
      //   721: astore 13
      //   723: aload_0
      //   724: getfield 48	com/umeng/update/net/DownloadingService$b:b	Landroid/content/Context;
      //   727: invokestatic 494	u/upd/a:k	(Landroid/content/Context;)Z
      //   730: ifne +207 -> 937
      //   733: aload 10
      //   735: astore 14
      //   737: aload 11
      //   739: astore 13
      //   741: aload_0
      //   742: getfield 50	com/umeng/update/net/DownloadingService$b:k	Lcom/umeng/update/net/a$a;
      //   745: getfield 496	com/umeng/update/net/a$a:i	Z
      //   748: ifeq +189 -> 937
      //   751: aload 10
      //   753: astore 14
      //   755: aload 11
      //   757: astore 13
      //   759: invokestatic 103	com/umeng/update/net/DownloadingService:a	()Ljava/lang/String;
      //   762: ldc_w 498
      //   765: invokestatic 407	u/upd/b:b	(Ljava/lang/String;Ljava/lang/String;)V
      //   768: aload 10
      //   770: astore 14
      //   772: aload 11
      //   774: astore 13
      //   776: new 163	java/io/IOException
      //   779: dup
      //   780: ldc_w 498
      //   783: invokespecial 499	java/io/IOException:<init>	(Ljava/lang/String;)V
      //   786: athrow
      //   787: astore 12
      //   789: invokestatic 103	com/umeng/update/net/DownloadingService:a	()Ljava/lang/String;
      //   792: aload 12
      //   794: invokevirtual 500	java/io/IOException:getMessage	()Ljava/lang/String;
      //   797: aload 12
      //   799: invokestatic 111	u/upd/b:c	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V
      //   802: aload_0
      //   803: getfield 38	com/umeng/update/net/DownloadingService$b:e	I
      //   806: iconst_1
      //   807: iadd
      //   808: istore_2
      //   809: aload_0
      //   810: iload_2
      //   811: putfield 38	com/umeng/update/net/DownloadingService$b:e	I
      //   814: iload_2
      //   815: iconst_3
      //   816: if_icmple +1023 -> 1839
      //   819: aload_0
      //   820: getfield 50	com/umeng/update/net/DownloadingService$b:k	Lcom/umeng/update/net/a$a;
      //   823: getfield 244	com/umeng/update/net/a$a:g	Z
      //   826: istore_1
      //   827: iload_1
      //   828: ifne +1011 -> 1839
      //   831: invokestatic 103	com/umeng/update/net/DownloadingService:a	()Ljava/lang/String;
      //   834: ldc_w 502
      //   837: invokestatic 407	u/upd/b:b	(Ljava/lang/String;Ljava/lang/String;)V
      //   840: invokestatic 350	com/umeng/update/net/DownloadingService:c	()Ljava/util/Map;
      //   843: aload_0
      //   844: getfield 50	com/umeng/update/net/DownloadingService$b:k	Lcom/umeng/update/net/a$a;
      //   847: invokeinterface 355 2 0
      //   852: checkcast 357	android/os/Messenger
      //   855: aconst_null
      //   856: iconst_5
      //   857: iconst_0
      //   858: iconst_0
      //   859: invokestatic 364	android/os/Message:obtain	(Landroid/os/Handler;III)Landroid/os/Message;
      //   862: invokevirtual 361	android/os/Messenger:send	(Landroid/os/Message;)V
      //   865: aload_0
      //   866: getfield 33	com/umeng/update/net/DownloadingService$b:a	Lcom/umeng/update/net/DownloadingService;
      //   869: invokestatic 259	com/umeng/update/net/DownloadingService:a	(Lcom/umeng/update/net/DownloadingService;)Lcom/umeng/update/net/c;
      //   872: aload_0
      //   873: getfield 48	com/umeng/update/net/DownloadingService$b:b	Landroid/content/Context;
      //   876: aload_0
      //   877: getfield 72	com/umeng/update/net/DownloadingService$b:i	I
      //   880: invokevirtual 264	com/umeng/update/net/c:b	(Landroid/content/Context;I)V
      //   883: aload_0
      //   884: aload 12
      //   886: invokespecial 256	com/umeng/update/net/DownloadingService$b:a	(Ljava/lang/Exception;)V
      //   889: aload_0
      //   890: getfield 33	com/umeng/update/net/DownloadingService$b:a	Lcom/umeng/update/net/DownloadingService;
      //   893: invokestatic 505	com/umeng/update/net/DownloadingService:d	(Lcom/umeng/update/net/DownloadingService;)Landroid/os/Handler;
      //   896: new 9	com/umeng/update/net/DownloadingService$b$1
      //   899: dup
      //   900: aload_0
      //   901: invokespecial 508	com/umeng/update/net/DownloadingService$b$1:<init>	(Lcom/umeng/update/net/DownloadingService$b;)V
      //   904: invokevirtual 514	android/os/Handler:post	(Ljava/lang/Runnable;)Z
      //   907: pop
      //   908: aload 11
      //   910: ifnull +8 -> 918
      //   913: aload 11
      //   915: invokevirtual 492	java/io/InputStream:close	()V
      //   918: aload 10
      //   920: ifnull -206 -> 714
      //   923: aload 10
      //   925: invokevirtual 427	java/io/FileOutputStream:close	()V
      //   928: return
      //   929: astore 10
      //   931: aload 10
      //   933: invokevirtual 517	java/io/IOException:printStackTrace	()V
      //   936: return
      //   937: aload 10
      //   939: astore 14
      //   941: aload 11
      //   943: astore 13
      //   945: aload_0
      //   946: getfield 42	com/umeng/update/net/DownloadingService$b:f	J
      //   949: l2f
      //   950: ldc_w 518
      //   953: fmul
      //   954: aload_0
      //   955: getfield 44	com/umeng/update/net/DownloadingService$b:g	J
      //   958: l2f
      //   959: fdiv
      //   960: f2i
      //   961: istore 5
      //   963: iload 5
      //   965: istore_2
      //   966: iload 5
      //   968: bipush 100
      //   970: if_icmple +6 -> 976
      //   973: bipush 99
      //   975: istore_2
      //   976: aload 10
      //   978: astore 14
      //   980: aload 11
      //   982: astore 13
      //   984: aload_0
      //   985: getfield 70	com/umeng/update/net/DownloadingService$b:j	Lcom/umeng/update/net/DownloadingService$a;
      //   988: ifnull +25 -> 1013
      //   991: aload 10
      //   993: astore 14
      //   995: aload 11
      //   997: astore 13
      //   999: aload_0
      //   1000: getfield 70	com/umeng/update/net/DownloadingService$b:j	Lcom/umeng/update/net/DownloadingService$a;
      //   1003: aload_0
      //   1004: getfield 72	com/umeng/update/net/DownloadingService$b:i	I
      //   1007: iload_2
      //   1008: invokeinterface 521 3 0
      //   1013: aload 10
      //   1015: astore 14
      //   1017: aload 11
      //   1019: astore 13
      //   1021: aload_0
      //   1022: iload_2
      //   1023: invokespecial 523	com/umeng/update/net/DownloadingService$b:b	(I)V
      //   1026: aload 10
      //   1028: astore 14
      //   1030: aload 11
      //   1032: astore 13
      //   1034: aload_0
      //   1035: getfield 48	com/umeng/update/net/DownloadingService$b:b	Landroid/content/Context;
      //   1038: invokestatic 474	com/umeng/update/net/b:a	(Landroid/content/Context;)Lcom/umeng/update/net/b;
      //   1041: aload_0
      //   1042: getfield 50	com/umeng/update/net/DownloadingService$b:k	Lcom/umeng/update/net/a$a;
      //   1045: getfield 137	com/umeng/update/net/a$a:a	Ljava/lang/String;
      //   1048: aload_0
      //   1049: getfield 50	com/umeng/update/net/DownloadingService$b:k	Lcom/umeng/update/net/a$a;
      //   1052: getfield 155	com/umeng/update/net/a$a:c	Ljava/lang/String;
      //   1055: iload_2
      //   1056: invokevirtual 526	com/umeng/update/net/b:a	(Ljava/lang/String;Ljava/lang/String;I)V
      //   1059: iload_3
      //   1060: istore_2
      //   1061: goto -593 -> 468
      //   1064: astore 10
      //   1066: aload 10
      //   1068: invokevirtual 517	java/io/IOException:printStackTrace	()V
      //   1071: return
      //   1072: astore 11
      //   1074: aload 11
      //   1076: invokevirtual 517	java/io/IOException:printStackTrace	()V
      //   1079: aload 10
      //   1081: ifnull -367 -> 714
      //   1084: aload 10
      //   1086: invokevirtual 427	java/io/FileOutputStream:close	()V
      //   1089: return
      //   1090: astore 10
      //   1092: aload 10
      //   1094: invokevirtual 517	java/io/IOException:printStackTrace	()V
      //   1097: return
      //   1098: astore 11
      //   1100: aload 10
      //   1102: ifnull +8 -> 1110
      //   1105: aload 10
      //   1107: invokevirtual 427	java/io/FileOutputStream:close	()V
      //   1110: aload 11
      //   1112: athrow
      //   1113: astore 10
      //   1115: aload 10
      //   1117: invokevirtual 517	java/io/IOException:printStackTrace	()V
      //   1120: goto -10 -> 1110
      //   1123: aload 10
      //   1125: astore 14
      //   1127: aload 11
      //   1129: astore 13
      //   1131: aload_0
      //   1132: getfield 46	com/umeng/update/net/DownloadingService$b:h	I
      //   1135: iconst_2
      //   1136: if_icmpne +140 -> 1276
      //   1139: aload 10
      //   1141: astore 14
      //   1143: aload 11
      //   1145: astore 13
      //   1147: aload_0
      //   1148: getfield 33	com/umeng/update/net/DownloadingService$b:a	Lcom/umeng/update/net/DownloadingService;
      //   1151: invokestatic 259	com/umeng/update/net/DownloadingService:a	(Lcom/umeng/update/net/DownloadingService;)Lcom/umeng/update/net/c;
      //   1154: aload_0
      //   1155: getfield 50	com/umeng/update/net/DownloadingService$b:k	Lcom/umeng/update/net/a$a;
      //   1158: aload_0
      //   1159: getfield 42	com/umeng/update/net/DownloadingService$b:f	J
      //   1162: aload_0
      //   1163: getfield 44	com/umeng/update/net/DownloadingService$b:g	J
      //   1166: aload_0
      //   1167: getfield 38	com/umeng/update/net/DownloadingService$b:e	I
      //   1170: i2l
      //   1171: invokevirtual 410	com/umeng/update/net/c:a	(Lcom/umeng/update/net/a$a;JJJ)V
      //   1174: aload 10
      //   1176: astore 14
      //   1178: aload 11
      //   1180: astore 13
      //   1182: aload_0
      //   1183: getfield 33	com/umeng/update/net/DownloadingService$b:a	Lcom/umeng/update/net/DownloadingService;
      //   1186: invokestatic 315	com/umeng/update/net/DownloadingService:c	(Lcom/umeng/update/net/DownloadingService;)Landroid/app/NotificationManager;
      //   1189: aload_0
      //   1190: getfield 72	com/umeng/update/net/DownloadingService$b:i	I
      //   1193: invokevirtual 320	android/app/NotificationManager:cancel	(I)V
      //   1196: aload 11
      //   1198: ifnull +8 -> 1206
      //   1201: aload 11
      //   1203: invokevirtual 492	java/io/InputStream:close	()V
      //   1206: aload 10
      //   1208: ifnull -494 -> 714
      //   1211: aload 10
      //   1213: invokevirtual 427	java/io/FileOutputStream:close	()V
      //   1216: return
      //   1217: astore 10
      //   1219: aload 10
      //   1221: invokevirtual 517	java/io/IOException:printStackTrace	()V
      //   1224: return
      //   1225: astore 11
      //   1227: aload 11
      //   1229: invokevirtual 517	java/io/IOException:printStackTrace	()V
      //   1232: aload 10
      //   1234: ifnull -520 -> 714
      //   1237: aload 10
      //   1239: invokevirtual 427	java/io/FileOutputStream:close	()V
      //   1242: return
      //   1243: astore 10
      //   1245: aload 10
      //   1247: invokevirtual 517	java/io/IOException:printStackTrace	()V
      //   1250: return
      //   1251: astore 11
      //   1253: aload 10
      //   1255: ifnull +8 -> 1263
      //   1258: aload 10
      //   1260: invokevirtual 427	java/io/FileOutputStream:close	()V
      //   1263: aload 11
      //   1265: athrow
      //   1266: astore 10
      //   1268: aload 10
      //   1270: invokevirtual 517	java/io/IOException:printStackTrace	()V
      //   1273: goto -10 -> 1263
      //   1276: iload_2
      //   1277: ifne +165 -> 1442
      //   1280: aload 10
      //   1282: astore 14
      //   1284: aload 11
      //   1286: astore 13
      //   1288: invokestatic 103	com/umeng/update/net/DownloadingService:a	()Ljava/lang/String;
      //   1291: new 125	java/lang/StringBuilder
      //   1294: dup
      //   1295: invokespecial 126	java/lang/StringBuilder:<init>	()V
      //   1298: ldc_w 528
      //   1301: invokevirtual 130	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1304: aload_0
      //   1305: getfield 38	com/umeng/update/net/DownloadingService$b:e	I
      //   1308: invokevirtual 242	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1311: invokevirtual 135	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1314: invokestatic 407	u/upd/b:b	(Ljava/lang/String;Ljava/lang/String;)V
      //   1317: aload 10
      //   1319: astore 14
      //   1321: aload 11
      //   1323: astore 13
      //   1325: invokestatic 350	com/umeng/update/net/DownloadingService:c	()Ljava/util/Map;
      //   1328: aload_0
      //   1329: getfield 50	com/umeng/update/net/DownloadingService$b:k	Lcom/umeng/update/net/a$a;
      //   1332: invokeinterface 355 2 0
      //   1337: checkcast 357	android/os/Messenger
      //   1340: aconst_null
      //   1341: iconst_5
      //   1342: iconst_0
      //   1343: iconst_0
      //   1344: invokestatic 364	android/os/Message:obtain	(Landroid/os/Handler;III)Landroid/os/Message;
      //   1347: invokevirtual 361	android/os/Messenger:send	(Landroid/os/Message;)V
      //   1350: aload 10
      //   1352: astore 14
      //   1354: aload 11
      //   1356: astore 13
      //   1358: aload_0
      //   1359: getfield 33	com/umeng/update/net/DownloadingService$b:a	Lcom/umeng/update/net/DownloadingService;
      //   1362: invokestatic 259	com/umeng/update/net/DownloadingService:a	(Lcom/umeng/update/net/DownloadingService;)Lcom/umeng/update/net/c;
      //   1365: aload_0
      //   1366: getfield 48	com/umeng/update/net/DownloadingService$b:b	Landroid/content/Context;
      //   1369: aload_0
      //   1370: getfield 72	com/umeng/update/net/DownloadingService$b:i	I
      //   1373: invokevirtual 264	com/umeng/update/net/c:b	(Landroid/content/Context;I)V
      //   1376: aload 10
      //   1378: astore 14
      //   1380: aload 11
      //   1382: astore 13
      //   1384: aload_0
      //   1385: getfield 70	com/umeng/update/net/DownloadingService$b:j	Lcom/umeng/update/net/DownloadingService$a;
      //   1388: ifnull +25 -> 1413
      //   1391: aload 10
      //   1393: astore 14
      //   1395: aload 11
      //   1397: astore 13
      //   1399: aload_0
      //   1400: getfield 70	com/umeng/update/net/DownloadingService$b:j	Lcom/umeng/update/net/DownloadingService$a;
      //   1403: aload_0
      //   1404: getfield 72	com/umeng/update/net/DownloadingService$b:i	I
      //   1407: aconst_null
      //   1408: invokeinterface 116 3 0
      //   1413: aload 11
      //   1415: ifnull +8 -> 1423
      //   1418: aload 11
      //   1420: invokevirtual 492	java/io/InputStream:close	()V
      //   1423: aload 10
      //   1425: ifnull -711 -> 714
      //   1428: aload 10
      //   1430: invokevirtual 427	java/io/FileOutputStream:close	()V
      //   1433: return
      //   1434: astore 10
      //   1436: aload 10
      //   1438: invokevirtual 517	java/io/IOException:printStackTrace	()V
      //   1441: return
      //   1442: aload 10
      //   1444: astore 14
      //   1446: aload 11
      //   1448: astore 13
      //   1450: aload_0
      //   1451: invokespecial 530	com/umeng/update/net/DownloadingService$b:b	()V
      //   1454: aload 10
      //   1456: astore 14
      //   1458: aload 11
      //   1460: astore 13
      //   1462: new 95	java/io/File
      //   1465: dup
      //   1466: aload_0
      //   1467: getfield 81	com/umeng/update/net/DownloadingService$b:d	Ljava/io/File;
      //   1470: invokevirtual 533	java/io/File:getParent	()Ljava/lang/String;
      //   1473: aload_0
      //   1474: getfield 81	com/umeng/update/net/DownloadingService$b:d	Ljava/io/File;
      //   1477: invokevirtual 413	java/io/File:getName	()Ljava/lang/String;
      //   1480: ldc_w 535
      //   1483: ldc_w 537
      //   1486: invokevirtual 153	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
      //   1489: invokespecial 539	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
      //   1492: astore 12
      //   1494: aload 10
      //   1496: astore 14
      //   1498: aload 11
      //   1500: astore 13
      //   1502: aload_0
      //   1503: getfield 81	com/umeng/update/net/DownloadingService$b:d	Ljava/io/File;
      //   1506: aload 12
      //   1508: invokevirtual 543	java/io/File:renameTo	(Ljava/io/File;)Z
      //   1511: pop
      //   1512: aload 10
      //   1514: astore 14
      //   1516: aload 11
      //   1518: astore 13
      //   1520: aload 12
      //   1522: invokevirtual 421	java/io/File:getAbsolutePath	()Ljava/lang/String;
      //   1525: astore 15
      //   1527: aload 10
      //   1529: astore 14
      //   1531: aload 11
      //   1533: astore 13
      //   1535: aload_0
      //   1536: aload 12
      //   1538: aload 15
      //   1540: invokespecial 545	com/umeng/update/net/DownloadingService$b:a	(Ljava/io/File;Ljava/lang/String;)V
      //   1543: aload 10
      //   1545: astore 14
      //   1547: aload 11
      //   1549: astore 13
      //   1551: aload_0
      //   1552: getfield 70	com/umeng/update/net/DownloadingService$b:j	Lcom/umeng/update/net/DownloadingService$a;
      //   1555: ifnull -142 -> 1413
      //   1558: aload 10
      //   1560: astore 14
      //   1562: aload 11
      //   1564: astore 13
      //   1566: aload_0
      //   1567: getfield 70	com/umeng/update/net/DownloadingService$b:j	Lcom/umeng/update/net/DownloadingService$a;
      //   1570: aload_0
      //   1571: getfield 72	com/umeng/update/net/DownloadingService$b:i	I
      //   1574: aload 15
      //   1576: invokeinterface 548 3 0
      //   1581: goto -168 -> 1413
      //   1584: astore 12
      //   1586: aload 10
      //   1588: astore 14
      //   1590: aload 11
      //   1592: astore 13
      //   1594: aload_0
      //   1595: getfield 33	com/umeng/update/net/DownloadingService$b:a	Lcom/umeng/update/net/DownloadingService;
      //   1598: invokestatic 259	com/umeng/update/net/DownloadingService:a	(Lcom/umeng/update/net/DownloadingService;)Lcom/umeng/update/net/c;
      //   1601: aload_0
      //   1602: getfield 48	com/umeng/update/net/DownloadingService$b:b	Landroid/content/Context;
      //   1605: aload_0
      //   1606: getfield 72	com/umeng/update/net/DownloadingService$b:i	I
      //   1609: invokevirtual 264	com/umeng/update/net/c:b	(Landroid/content/Context;I)V
      //   1612: aload 10
      //   1614: astore 14
      //   1616: aload 11
      //   1618: astore 13
      //   1620: aload 12
      //   1622: invokevirtual 549	android/os/RemoteException:printStackTrace	()V
      //   1625: aload 11
      //   1627: ifnull +8 -> 1635
      //   1630: aload 11
      //   1632: invokevirtual 492	java/io/InputStream:close	()V
      //   1635: aload 10
      //   1637: ifnull -923 -> 714
      //   1640: aload 10
      //   1642: invokevirtual 427	java/io/FileOutputStream:close	()V
      //   1645: return
      //   1646: astore 10
      //   1648: aload 10
      //   1650: invokevirtual 517	java/io/IOException:printStackTrace	()V
      //   1653: return
      //   1654: astore 11
      //   1656: aload 11
      //   1658: invokevirtual 517	java/io/IOException:printStackTrace	()V
      //   1661: aload 10
      //   1663: ifnull -949 -> 714
      //   1666: aload 10
      //   1668: invokevirtual 427	java/io/FileOutputStream:close	()V
      //   1671: return
      //   1672: astore 10
      //   1674: aload 10
      //   1676: invokevirtual 517	java/io/IOException:printStackTrace	()V
      //   1679: return
      //   1680: astore 11
      //   1682: aload 10
      //   1684: ifnull +8 -> 1692
      //   1687: aload 10
      //   1689: invokevirtual 427	java/io/FileOutputStream:close	()V
      //   1692: aload 11
      //   1694: athrow
      //   1695: astore 10
      //   1697: aload 10
      //   1699: invokevirtual 517	java/io/IOException:printStackTrace	()V
      //   1702: goto -10 -> 1692
      //   1705: astore 13
      //   1707: aload 13
      //   1709: invokevirtual 549	android/os/RemoteException:printStackTrace	()V
      //   1712: aload_0
      //   1713: getfield 33	com/umeng/update/net/DownloadingService$b:a	Lcom/umeng/update/net/DownloadingService;
      //   1716: invokestatic 259	com/umeng/update/net/DownloadingService:a	(Lcom/umeng/update/net/DownloadingService;)Lcom/umeng/update/net/c;
      //   1719: aload_0
      //   1720: getfield 48	com/umeng/update/net/DownloadingService$b:b	Landroid/content/Context;
      //   1723: aload_0
      //   1724: getfield 72	com/umeng/update/net/DownloadingService$b:i	I
      //   1727: invokevirtual 264	com/umeng/update/net/c:b	(Landroid/content/Context;I)V
      //   1730: aload_0
      //   1731: aload 12
      //   1733: invokespecial 256	com/umeng/update/net/DownloadingService$b:a	(Ljava/lang/Exception;)V
      //   1736: aload_0
      //   1737: getfield 33	com/umeng/update/net/DownloadingService$b:a	Lcom/umeng/update/net/DownloadingService;
      //   1740: invokestatic 505	com/umeng/update/net/DownloadingService:d	(Lcom/umeng/update/net/DownloadingService;)Landroid/os/Handler;
      //   1743: new 9	com/umeng/update/net/DownloadingService$b$1
      //   1746: dup
      //   1747: aload_0
      //   1748: invokespecial 508	com/umeng/update/net/DownloadingService$b$1:<init>	(Lcom/umeng/update/net/DownloadingService$b;)V
      //   1751: invokevirtual 514	android/os/Handler:post	(Ljava/lang/Runnable;)Z
      //   1754: pop
      //   1755: goto -847 -> 908
      //   1758: astore 13
      //   1760: aload 11
      //   1762: astore 12
      //   1764: aload 13
      //   1766: astore 11
      //   1768: aload 12
      //   1770: ifnull +8 -> 1778
      //   1773: aload 12
      //   1775: invokevirtual 492	java/io/InputStream:close	()V
      //   1778: aload 10
      //   1780: ifnull +8 -> 1788
      //   1783: aload 10
      //   1785: invokevirtual 427	java/io/FileOutputStream:close	()V
      //   1788: aload 11
      //   1790: athrow
      //   1791: astore 13
      //   1793: aload_0
      //   1794: getfield 33	com/umeng/update/net/DownloadingService$b:a	Lcom/umeng/update/net/DownloadingService;
      //   1797: invokestatic 259	com/umeng/update/net/DownloadingService:a	(Lcom/umeng/update/net/DownloadingService;)Lcom/umeng/update/net/c;
      //   1800: aload_0
      //   1801: getfield 48	com/umeng/update/net/DownloadingService$b:b	Landroid/content/Context;
      //   1804: aload_0
      //   1805: getfield 72	com/umeng/update/net/DownloadingService$b:i	I
      //   1808: invokevirtual 264	com/umeng/update/net/c:b	(Landroid/content/Context;I)V
      //   1811: aload_0
      //   1812: aload 12
      //   1814: invokespecial 256	com/umeng/update/net/DownloadingService$b:a	(Ljava/lang/Exception;)V
      //   1817: aload_0
      //   1818: getfield 33	com/umeng/update/net/DownloadingService$b:a	Lcom/umeng/update/net/DownloadingService;
      //   1821: invokestatic 505	com/umeng/update/net/DownloadingService:d	(Lcom/umeng/update/net/DownloadingService;)Landroid/os/Handler;
      //   1824: new 9	com/umeng/update/net/DownloadingService$b$1
      //   1827: dup
      //   1828: aload_0
      //   1829: invokespecial 508	com/umeng/update/net/DownloadingService$b$1:<init>	(Lcom/umeng/update/net/DownloadingService$b;)V
      //   1832: invokevirtual 514	android/os/Handler:post	(Ljava/lang/Runnable;)Z
      //   1835: pop
      //   1836: aload 13
      //   1838: athrow
      //   1839: aload_0
      //   1840: invokespecial 551	com/umeng/update/net/DownloadingService$b:a	()V
      //   1843: goto -935 -> 908
      //   1846: astore 11
      //   1848: aload 11
      //   1850: invokevirtual 517	java/io/IOException:printStackTrace	()V
      //   1853: aload 10
      //   1855: ifnull -1141 -> 714
      //   1858: aload 10
      //   1860: invokevirtual 427	java/io/FileOutputStream:close	()V
      //   1863: return
      //   1864: astore 10
      //   1866: aload 10
      //   1868: invokevirtual 517	java/io/IOException:printStackTrace	()V
      //   1871: return
      //   1872: astore 11
      //   1874: aload 10
      //   1876: ifnull +8 -> 1884
      //   1879: aload 10
      //   1881: invokevirtual 427	java/io/FileOutputStream:close	()V
      //   1884: aload 11
      //   1886: athrow
      //   1887: astore 10
      //   1889: aload 10
      //   1891: invokevirtual 517	java/io/IOException:printStackTrace	()V
      //   1894: goto -10 -> 1884
      //   1897: astore 11
      //   1899: aload 11
      //   1901: invokevirtual 517	java/io/IOException:printStackTrace	()V
      //   1904: aload 10
      //   1906: ifnull -1192 -> 714
      //   1909: aload 10
      //   1911: invokevirtual 427	java/io/FileOutputStream:close	()V
      //   1914: return
      //   1915: astore 10
      //   1917: aload 10
      //   1919: invokevirtual 517	java/io/IOException:printStackTrace	()V
      //   1922: return
      //   1923: astore 11
      //   1925: aload 10
      //   1927: ifnull +8 -> 1935
      //   1930: aload 10
      //   1932: invokevirtual 427	java/io/FileOutputStream:close	()V
      //   1935: aload 11
      //   1937: athrow
      //   1938: astore 10
      //   1940: aload 10
      //   1942: invokevirtual 517	java/io/IOException:printStackTrace	()V
      //   1945: goto -10 -> 1935
      //   1948: astore 10
      //   1950: aload 10
      //   1952: invokevirtual 517	java/io/IOException:printStackTrace	()V
      //   1955: goto -167 -> 1788
      //   1958: astore 12
      //   1960: aload 12
      //   1962: invokevirtual 517	java/io/IOException:printStackTrace	()V
      //   1965: aload 10
      //   1967: ifnull -179 -> 1788
      //   1970: aload 10
      //   1972: invokevirtual 427	java/io/FileOutputStream:close	()V
      //   1975: goto -187 -> 1788
      //   1978: astore 10
      //   1980: aload 10
      //   1982: invokevirtual 517	java/io/IOException:printStackTrace	()V
      //   1985: goto -197 -> 1788
      //   1988: astore 11
      //   1990: aload 10
      //   1992: ifnull +8 -> 2000
      //   1995: aload 10
      //   1997: invokevirtual 427	java/io/FileOutputStream:close	()V
      //   2000: aload 11
      //   2002: athrow
      //   2003: astore 10
      //   2005: aload 10
      //   2007: invokevirtual 517	java/io/IOException:printStackTrace	()V
      //   2010: goto -10 -> 2000
      //   2013: astore 11
      //   2015: aconst_null
      //   2016: astore 10
      //   2018: aconst_null
      //   2019: astore 12
      //   2021: goto -253 -> 1768
      //   2024: astore 11
      //   2026: aconst_null
      //   2027: astore 12
      //   2029: goto -261 -> 1768
      //   2032: astore 13
      //   2034: aload 11
      //   2036: astore 10
      //   2038: aconst_null
      //   2039: astore 12
      //   2041: aload 13
      //   2043: astore 11
      //   2045: goto -277 -> 1768
      //   2048: astore 11
      //   2050: aconst_null
      //   2051: astore 12
      //   2053: goto -285 -> 1768
      //   2056: astore 11
      //   2058: aload 14
      //   2060: astore 10
      //   2062: aload 13
      //   2064: astore 12
      //   2066: goto -298 -> 1768
      //   2069: astore 12
      //   2071: aconst_null
      //   2072: astore 10
      //   2074: aconst_null
      //   2075: astore 11
      //   2077: goto -491 -> 1586
      //   2080: astore 12
      //   2082: aconst_null
      //   2083: astore 11
      //   2085: goto -499 -> 1586
      //   2088: astore 12
      //   2090: aload 11
      //   2092: astore 10
      //   2094: aconst_null
      //   2095: astore 11
      //   2097: goto -511 -> 1586
      //   2100: astore 12
      //   2102: aconst_null
      //   2103: astore 11
      //   2105: goto -519 -> 1586
      //   2108: astore 12
      //   2110: aconst_null
      //   2111: astore 13
      //   2113: aload 11
      //   2115: astore 10
      //   2117: aload 13
      //   2119: astore 11
      //   2121: goto -1332 -> 789
      //   2124: astore 12
      //   2126: aconst_null
      //   2127: astore 11
      //   2129: goto -1340 -> 789
      //   2132: astore 12
      //   2134: aconst_null
      //   2135: astore 13
      //   2137: aload 11
      //   2139: astore 10
      //   2141: aload 13
      //   2143: astore 11
      //   2145: goto -1356 -> 789
      //   2148: astore 12
      //   2150: aconst_null
      //   2151: astore 11
      //   2153: goto -1364 -> 789
      //   2156: iload_3
      //   2157: istore_2
      //   2158: goto -1690 -> 468
      //   2161: iconst_1
      //   2162: istore_2
      //   2163: goto -1592 -> 571
      //   2166: goto -2080 -> 86
      //
      // Exception table:
      //   from	to	target	type
      //   177	187	787	java/io/IOException
      //   199	211	787	java/io/IOException
      //   219	230	787	java/io/IOException
      //   238	244	787	java/io/IOException
      //   252	265	787	java/io/IOException
      //   273	299	787	java/io/IOException
      //   307	334	787	java/io/IOException
      //   342	368	787	java/io/IOException
      //   376	383	787	java/io/IOException
      //   391	433	787	java/io/IOException
      //   441	466	787	java/io/IOException
      //   476	483	787	java/io/IOException
      //   491	499	787	java/io/IOException
      //   511	520	787	java/io/IOException
      //   528	539	787	java/io/IOException
      //   558	568	787	java/io/IOException
      //   579	584	787	java/io/IOException
      //   592	597	787	java/io/IOException
      //   605	613	787	java/io/IOException
      //   621	636	787	java/io/IOException
      //   644	655	787	java/io/IOException
      //   663	674	787	java/io/IOException
      //   682	694	787	java/io/IOException
      //   723	733	787	java/io/IOException
      //   741	751	787	java/io/IOException
      //   759	768	787	java/io/IOException
      //   776	787	787	java/io/IOException
      //   945	963	787	java/io/IOException
      //   984	991	787	java/io/IOException
      //   999	1013	787	java/io/IOException
      //   1021	1026	787	java/io/IOException
      //   1034	1059	787	java/io/IOException
      //   1131	1139	787	java/io/IOException
      //   1147	1174	787	java/io/IOException
      //   1182	1196	787	java/io/IOException
      //   1288	1317	787	java/io/IOException
      //   1325	1350	787	java/io/IOException
      //   1358	1376	787	java/io/IOException
      //   1384	1391	787	java/io/IOException
      //   1399	1413	787	java/io/IOException
      //   1450	1454	787	java/io/IOException
      //   1462	1494	787	java/io/IOException
      //   1502	1512	787	java/io/IOException
      //   1520	1527	787	java/io/IOException
      //   1535	1543	787	java/io/IOException
      //   1551	1558	787	java/io/IOException
      //   1566	1581	787	java/io/IOException
      //   923	928	929	java/io/IOException
      //   709	714	1064	java/io/IOException
      //   699	704	1072	java/io/IOException
      //   1084	1089	1090	java/io/IOException
      //   699	704	1098	finally
      //   1074	1079	1098	finally
      //   1105	1110	1113	java/io/IOException
      //   1211	1216	1217	java/io/IOException
      //   1201	1206	1225	java/io/IOException
      //   1237	1242	1243	java/io/IOException
      //   1201	1206	1251	finally
      //   1227	1232	1251	finally
      //   1258	1263	1266	java/io/IOException
      //   1428	1433	1434	java/io/IOException
      //   177	187	1584	android/os/RemoteException
      //   199	211	1584	android/os/RemoteException
      //   219	230	1584	android/os/RemoteException
      //   238	244	1584	android/os/RemoteException
      //   252	265	1584	android/os/RemoteException
      //   273	299	1584	android/os/RemoteException
      //   307	334	1584	android/os/RemoteException
      //   342	368	1584	android/os/RemoteException
      //   376	383	1584	android/os/RemoteException
      //   391	433	1584	android/os/RemoteException
      //   441	466	1584	android/os/RemoteException
      //   476	483	1584	android/os/RemoteException
      //   491	499	1584	android/os/RemoteException
      //   511	520	1584	android/os/RemoteException
      //   528	539	1584	android/os/RemoteException
      //   558	568	1584	android/os/RemoteException
      //   579	584	1584	android/os/RemoteException
      //   592	597	1584	android/os/RemoteException
      //   605	613	1584	android/os/RemoteException
      //   621	636	1584	android/os/RemoteException
      //   644	655	1584	android/os/RemoteException
      //   663	674	1584	android/os/RemoteException
      //   682	694	1584	android/os/RemoteException
      //   723	733	1584	android/os/RemoteException
      //   741	751	1584	android/os/RemoteException
      //   759	768	1584	android/os/RemoteException
      //   776	787	1584	android/os/RemoteException
      //   945	963	1584	android/os/RemoteException
      //   984	991	1584	android/os/RemoteException
      //   999	1013	1584	android/os/RemoteException
      //   1021	1026	1584	android/os/RemoteException
      //   1034	1059	1584	android/os/RemoteException
      //   1131	1139	1584	android/os/RemoteException
      //   1147	1174	1584	android/os/RemoteException
      //   1182	1196	1584	android/os/RemoteException
      //   1288	1317	1584	android/os/RemoteException
      //   1325	1350	1584	android/os/RemoteException
      //   1358	1376	1584	android/os/RemoteException
      //   1384	1391	1584	android/os/RemoteException
      //   1399	1413	1584	android/os/RemoteException
      //   1450	1454	1584	android/os/RemoteException
      //   1462	1494	1584	android/os/RemoteException
      //   1502	1512	1584	android/os/RemoteException
      //   1520	1527	1584	android/os/RemoteException
      //   1535	1543	1584	android/os/RemoteException
      //   1551	1558	1584	android/os/RemoteException
      //   1566	1581	1584	android/os/RemoteException
      //   1640	1645	1646	java/io/IOException
      //   1418	1423	1654	java/io/IOException
      //   1666	1671	1672	java/io/IOException
      //   1418	1423	1680	finally
      //   1656	1661	1680	finally
      //   1687	1692	1695	java/io/IOException
      //   831	865	1705	android/os/RemoteException
      //   789	814	1758	finally
      //   819	827	1758	finally
      //   865	908	1758	finally
      //   1712	1755	1758	finally
      //   1793	1839	1758	finally
      //   1839	1843	1758	finally
      //   831	865	1791	finally
      //   1707	1712	1791	finally
      //   913	918	1846	java/io/IOException
      //   1858	1863	1864	java/io/IOException
      //   913	918	1872	finally
      //   1848	1853	1872	finally
      //   1879	1884	1887	java/io/IOException
      //   1630	1635	1897	java/io/IOException
      //   1909	1914	1915	java/io/IOException
      //   1630	1635	1923	finally
      //   1899	1904	1923	finally
      //   1930	1935	1938	java/io/IOException
      //   1783	1788	1948	java/io/IOException
      //   1773	1778	1958	java/io/IOException
      //   1970	1975	1978	java/io/IOException
      //   1773	1778	1988	finally
      //   1960	1965	1988	finally
      //   1995	2000	2003	java/io/IOException
      //   15	29	2013	finally
      //   29	69	2024	finally
      //   69	82	2032	finally
      //   86	158	2048	finally
      //   177	187	2056	finally
      //   199	211	2056	finally
      //   219	230	2056	finally
      //   238	244	2056	finally
      //   252	265	2056	finally
      //   273	299	2056	finally
      //   307	334	2056	finally
      //   342	368	2056	finally
      //   376	383	2056	finally
      //   391	433	2056	finally
      //   441	466	2056	finally
      //   476	483	2056	finally
      //   491	499	2056	finally
      //   511	520	2056	finally
      //   528	539	2056	finally
      //   558	568	2056	finally
      //   579	584	2056	finally
      //   592	597	2056	finally
      //   605	613	2056	finally
      //   621	636	2056	finally
      //   644	655	2056	finally
      //   663	674	2056	finally
      //   682	694	2056	finally
      //   723	733	2056	finally
      //   741	751	2056	finally
      //   759	768	2056	finally
      //   776	787	2056	finally
      //   945	963	2056	finally
      //   984	991	2056	finally
      //   999	1013	2056	finally
      //   1021	1026	2056	finally
      //   1034	1059	2056	finally
      //   1131	1139	2056	finally
      //   1147	1174	2056	finally
      //   1182	1196	2056	finally
      //   1288	1317	2056	finally
      //   1325	1350	2056	finally
      //   1358	1376	2056	finally
      //   1384	1391	2056	finally
      //   1399	1413	2056	finally
      //   1450	1454	2056	finally
      //   1462	1494	2056	finally
      //   1502	1512	2056	finally
      //   1520	1527	2056	finally
      //   1535	1543	2056	finally
      //   1551	1558	2056	finally
      //   1566	1581	2056	finally
      //   1594	1612	2056	finally
      //   1620	1625	2056	finally
      //   15	29	2069	android/os/RemoteException
      //   29	69	2080	android/os/RemoteException
      //   69	82	2088	android/os/RemoteException
      //   86	158	2100	android/os/RemoteException
      //   15	29	2108	java/io/IOException
      //   29	69	2124	java/io/IOException
      //   69	82	2132	java/io/IOException
      //   86	158	2148	java/io/IOException
    }

    private void b()
    {
      if (this.k.f != null)
      {
        HashMap localHashMap = new HashMap();
        localHashMap.put("dsize", String.valueOf(this.g));
        localHashMap.put("dtime", n.a().split(" ")[1]);
        localHashMap.put("ptimes", String.valueOf(this.e));
        DownloadingService.a(DownloadingService.this).a(localHashMap, true, this.k.f);
      }
    }

    private void b(int paramInt)
      throws RemoteException
    {
      try
      {
        if (DownloadingService.c().get(this.k) != null)
          ((Messenger)DownloadingService.c().get(this.k)).send(Message.obtain(null, 3, paramInt, 0));
        return;
      }
      catch (DeadObjectException localDeadObjectException)
      {
        u.upd.b.b(DownloadingService.a(), String.format("Service Client for downloading %1$15s is dead. Removing messenger from the service", new Object[] { this.k.b }));
        DownloadingService.c().put(this.k, null);
      }
    }

    public void a(int paramInt)
    {
      this.h = paramInt;
    }

    public void run()
    {
      boolean bool = false;
      this.e = 0;
      try
      {
        if (this.j != null)
          this.j.a(this.i);
        if (this.f > 0L)
          bool = true;
        a(bool);
        if (DownloadingService.c().size() <= 0)
          DownloadingService.this.stopSelf();
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }

  class c extends Handler
  {
    c()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      u.upd.b.c(DownloadingService.a(), "IncomingHandler(msg.what:" + paramMessage.what + " msg.arg1:" + paramMessage.arg1 + " msg.arg2:" + paramMessage.arg2 + " msg.replyTo:" + paramMessage.replyTo);
      switch (paramMessage.what)
      {
      default:
        super.handleMessage(paramMessage);
        return;
      case 4:
      }
      Object localObject = paramMessage.getData();
      u.upd.b.c(DownloadingService.a(), "IncomingHandler(msg.getData():" + localObject);
      localObject = a.a.a((Bundle)localObject);
      if (DownloadingService.a(DownloadingService.this).a((a.a)localObject, DownloadingService.r, paramMessage.replyTo))
      {
        u.upd.b.a(DownloadingService.a(), ((a.a)localObject).b + " is already in downloading list. ");
        int i = DownloadingService.a(DownloadingService.this).b((a.a)localObject);
        if ((i != -1) && (((c.b)DownloadingService.b().get(i)).a == null))
        {
          paramMessage = f.a(i, "continue");
          localObject = new Intent(DownloadingService.b(DownloadingService.this), DownloadingService.class);
          ((Intent)localObject).putExtra("com.umeng.broadcast.download.msg", paramMessage);
          DownloadingService.a(DownloadingService.this).a(DownloadingService.this, (Intent)localObject);
          return;
        }
        Toast.makeText(DownloadingService.b(DownloadingService.this), l.b(DownloadingService.b(DownloadingService.this)), 0).show();
        localObject = Message.obtain();
        ((Message)localObject).what = 2;
        ((Message)localObject).arg1 = 2;
        ((Message)localObject).arg2 = 0;
        try
        {
          paramMessage.replyTo.send((Message)localObject);
          return;
        }
        catch (RemoteException paramMessage)
        {
          paramMessage.printStackTrace();
          return;
        }
      }
      if (a.l(DownloadingService.this.getApplicationContext()))
      {
        DownloadingService.c().put(localObject, paramMessage.replyTo);
        Message localMessage = Message.obtain();
        localMessage.what = 1;
        localMessage.arg1 = 1;
        localMessage.arg2 = 0;
        try
        {
          paramMessage.replyTo.send(localMessage);
          DownloadingService.a(DownloadingService.this, (a.a)localObject);
          return;
        }
        catch (RemoteException paramMessage)
        {
          while (true)
            paramMessage.printStackTrace();
        }
      }
      Toast.makeText(DownloadingService.b(DownloadingService.this), l.a(DownloadingService.b(DownloadingService.this)), 0).show();
      localObject = Message.obtain();
      ((Message)localObject).what = 2;
      ((Message)localObject).arg1 = 4;
      ((Message)localObject).arg2 = 0;
      try
      {
        paramMessage.replyTo.send((Message)localObject);
        return;
      }
      catch (RemoteException paramMessage)
      {
        paramMessage.printStackTrace();
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.update.net.DownloadingService
 * JD-Core Version:    0.6.2
 */