package com.umeng.update.net;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import u.upd.b;

public class a
{
  private static final String b = a.class.getName();
  final Messenger a = new Messenger(new b());
  private Context c;
  private d d;
  private Messenger e;
  private String f;
  private String g;
  private String h;
  private String i;
  private String j;
  private String[] k;
  private boolean l = false;
  private boolean m = false;
  private boolean n = false;
  private ServiceConnection o = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
    {
      b.c(a.b(), "ServiceConnection.onServiceConnected");
      a.a(a.this, new Messenger(paramAnonymousIBinder));
      try
      {
        paramAnonymousComponentName = Message.obtain(null, 4);
        paramAnonymousIBinder = new a.a(a.d(a.this), a.e(a.this), a.f(a.this));
        paramAnonymousIBinder.d = a.g(a.this);
        paramAnonymousIBinder.e = a.h(a.this);
        paramAnonymousIBinder.f = a.i(a.this);
        paramAnonymousIBinder.g = a.j(a.this);
        paramAnonymousIBinder.h = a.k(a.this);
        paramAnonymousIBinder.i = a.l(a.this);
        paramAnonymousComponentName.setData(paramAnonymousIBinder.a());
        paramAnonymousComponentName.replyTo = a.this.a;
        a.m(a.this).send(paramAnonymousComponentName);
        return;
      }
      catch (RemoteException paramAnonymousComponentName)
      {
      }
    }

    public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
    {
      b.c(a.b(), "ServiceConnection.onServiceDisconnected");
      a.a(a.this, null);
    }
  };

  public a(Context paramContext, String paramString1, String paramString2, String paramString3, d paramd)
  {
    this.c = paramContext.getApplicationContext();
    this.f = paramString1;
    this.g = paramString2;
    this.h = paramString3;
    this.d = paramd;
  }

  public void a()
  {
    Intent localIntent = new Intent(this.c, DownloadingService.class);
    this.c.bindService(localIntent, this.o, 1);
    this.c.startService(new Intent(this.c, DownloadingService.class));
  }

  public void a(String paramString)
  {
    this.i = paramString;
  }

  public void a(boolean paramBoolean)
  {
    this.l = paramBoolean;
  }

  public void a(String[] paramArrayOfString)
  {
    this.k = paramArrayOfString;
  }

  public void b(String paramString)
  {
    this.j = paramString;
  }

  public void b(boolean paramBoolean)
  {
    this.m = paramBoolean;
  }

  public void c(boolean paramBoolean)
  {
    this.n = paramBoolean;
  }

  static class a
  {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String[] f = null;
    public boolean g = false;
    public boolean h = false;
    public boolean i = false;

    public a(String paramString1, String paramString2, String paramString3)
    {
      this.a = paramString1;
      this.b = paramString2;
      this.c = paramString3;
    }

    public static a a(Bundle paramBundle)
    {
      a locala = new a(paramBundle.getString("mComponentName"), paramBundle.getString("mTitle"), paramBundle.getString("mUrl"));
      locala.d = paramBundle.getString("mMd5");
      locala.e = paramBundle.getString("mTargetMd5");
      locala.f = paramBundle.getStringArray("reporturls");
      locala.g = paramBundle.getBoolean("rich_notification");
      locala.h = paramBundle.getBoolean("mSilent");
      locala.i = paramBundle.getBoolean("mWifiOnly");
      return locala;
    }

    public Bundle a()
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("mComponentName", this.a);
      localBundle.putString("mTitle", this.b);
      localBundle.putString("mUrl", this.c);
      localBundle.putString("mMd5", this.d);
      localBundle.putString("mTargetMd5", this.e);
      localBundle.putStringArray("reporturls", this.f);
      localBundle.putBoolean("rich_notification", this.g);
      localBundle.putBoolean("mSilent", this.h);
      localBundle.putBoolean("mWifiOnly", this.i);
      return localBundle;
    }
  }

  class b extends Handler
  {
    b()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      while (true)
      {
        try
        {
          b.c(a.b(), "DownloadAgent.handleMessage(" + paramMessage.what + "): ");
          switch (paramMessage.what)
          {
          case 4:
            super.handleMessage(paramMessage);
            return;
          case 1:
            if (a.a(a.this) == null)
              return;
            a.a(a.this).onStart();
            return;
          case 3:
          case 5:
          case 2:
          }
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          b.c(a.b(), "DownloadAgent.handleMessage(" + paramMessage.what + "): " + localException.getMessage());
          return;
        }
        if (a.a(a.this) == null)
          break;
        a.a(a.this).onProgressUpdate(paramMessage.arg1);
        return;
        a.c(a.this).unbindService(a.b(a.this));
        if (a.a(a.this) == null)
          break;
        if ((paramMessage.arg1 == 1) || (paramMessage.arg1 == 3) || (paramMessage.arg1 == 5))
        {
          a.a(a.this).onEnd(paramMessage.arg1, paramMessage.arg2, paramMessage.getData().getString("filename"));
          return;
        }
        a.a(a.this).onEnd(0, 0, null);
        b.c(a.b(), "DownloadAgent.handleMessage(DownloadingService.DOWNLOAD_COMPLETE_FAIL): ");
        return;
        a.a(a.this).onStatus(paramMessage.arg1);
        return;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.update.net.a
 * JD-Core Version:    0.6.2
 */