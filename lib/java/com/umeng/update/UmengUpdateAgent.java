package com.umeng.update;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;
import com.umeng.update.net.j;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import u.upd.a;
import u.upd.l;
import u.upd.n;

public class UmengUpdateAgent
{
  private static UmengUpdateListener a = null;
  private static UmengDialogButtonListener b = null;
  private static UmengDownloadListener c = null;
  private static Context d;
  private static c e = new c();
  private static Handler f;
  private static boolean g = false;
  private static String h = UmengUpdateAgent.class.getName();

  static void a(int paramInt, Context paramContext, UpdateResponse paramUpdateResponse, File paramFile)
  {
    switch (paramInt)
    {
    case 6:
    default:
    case 5:
    case 7:
    }
    while (true)
    {
      if (b != null)
        b.onClick(paramInt);
      return;
      a(paramContext, paramUpdateResponse, paramFile);
      continue;
      ignoreUpdate(paramContext, paramUpdateResponse);
    }
  }

  private static void a(Context paramContext, UpdateResponse paramUpdateResponse, File paramFile)
  {
    if (paramFile == null)
    {
      startDownload(paramContext, paramUpdateResponse);
      return;
    }
    startInstall(paramContext, paramFile);
  }

  private static void b(int paramInt, UpdateResponse paramUpdateResponse)
  {
    Message localMessage = new Message();
    localMessage.what = paramInt;
    localMessage.obj = paramUpdateResponse;
    f.sendMessage(localMessage);
  }

  private static void b(final Context paramContext)
  {
    if (paramContext == null);
    try
    {
      u.upd.b.b("update", "unexpected null context in update");
      return;
      f = new Handler(paramContext.getMainLooper())
      {
        public void handleMessage(Message paramAnonymousMessage)
        {
          super.handleMessage(paramAnonymousMessage);
          if ((paramAnonymousMessage.what == 0) && (UpdateConfig.isUpdateAutoPopup()))
            UmengUpdateAgent.a(UmengUpdateAgent.a(), (UpdateResponse)paramAnonymousMessage.obj, UpdateConfig.getStyle());
          UmengUpdateAgent.a(paramContext, paramAnonymousMessage);
          UmengUpdateAgent.a(null);
          if (UmengUpdateAgent.b() != null)
            UmengUpdateAgent.b().onUpdateReturned(paramAnonymousMessage.what, (UpdateResponse)paramAnonymousMessage.obj);
        }
      };
      c(paramContext);
      if (a.k(paramContext))
        break label79;
      if (UpdateConfig.isSilentDownload())
      {
        b(2, null);
        return;
      }
    }
    catch (Exception paramContext)
    {
      u.upd.b.b("update", "Exception occurred in Mobclick.update(). ", paramContext);
      return;
    }
    if ((UpdateConfig.isUpdateOnlyWifi()) && (!UpdateConfig.isUpdateForce()))
    {
      b(2, null);
      return;
    }
    label79: if (e.a())
    {
      b(4, null);
      u.upd.b.a("update", "Is updating now.");
      f.post(new Runnable()
      {
        public void run()
        {
          Toast.makeText(this.a, l.b(this.a), 0).show();
        }
      });
      return;
    }
    d = paramContext;
    new Thread(new a(paramContext.getApplicationContext())).start();
  }

  private static void b(Context paramContext, Message paramMessage)
  {
    if ((c()) && (getUpdateFromPushMessage()))
    {
      Intent localIntent = new Intent();
      Bundle localBundle = new Bundle();
      localBundle.putInt("UpdateStatus", paramMessage.what);
      localBundle.putSerializable("UpdateResponse", (UpdateResponse)paramMessage.obj);
      localIntent.putExtra("UpdateListener", localBundle);
      localIntent.setAction("com.umeng.message.action.autoupdate");
      LocalBroadcastManager.getInstance(paramContext).sendBroadcast(localIntent);
      setUpdateFromPushMessage(false);
      u.upd.b.c("UmengUpdateAgent", "UpdateFromPushMessage");
    }
  }

  private static void b(Context paramContext, UpdateResponse paramUpdateResponse, int paramInt)
  {
    while (true)
    {
      File localFile;
      try
      {
        if (isIgnore(paramContext, paramUpdateResponse))
          return;
        localFile = downloadedFile(paramContext, paramUpdateResponse);
        if (localFile != null)
        {
          bool = true;
          if (bool)
            break label99;
          if (UpdateConfig.isSilentDownload())
            break;
          break label99;
          e.a(paramContext, paramUpdateResponse, bool, localFile);
          return;
        }
      }
      catch (Exception paramContext)
      {
        u.upd.b.b("update", "Fail to create update dialog box.", paramContext);
        return;
      }
      boolean bool = false;
      continue;
      ((NotificationManager)paramContext.getSystemService("notification")).notify(0, e.b(paramContext, paramUpdateResponse, bool, localFile).a());
      return;
    }
    startDownload(paramContext, paramUpdateResponse);
    return;
    label99: switch (paramInt)
    {
    case 0:
    case 1:
    }
  }

  private static boolean c()
  {
    try
    {
      Class localClass = Class.forName("com.umeng.message.PushAgent");
      if (localClass != null)
        return true;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      while (true)
      {
        u.upd.b.e(h, "isIncludesUmengPushSDK", localClassNotFoundException);
        Object localObject = null;
      }
    }
    return false;
  }

  private static boolean c(Context paramContext)
  {
    if (!UpdateConfig.isUpdateCheck())
      return true;
    while (true)
    {
      int m;
      int i1;
      int n;
      try
      {
        while (true)
        {
          boolean bool1 = Class.forName(paramContext.getPackageName() + ".BuildConfig").getField("DEBUG").getBoolean(null);
          if (!bool1)
            break;
          try
          {
            if (UpdateConfig.getAppkey(paramContext) == null)
            {
              f.post(new Runnable()
              {
                public void run()
                {
                  Toast.makeText(this.a, "Please set umeng appkey!", 1).show();
                }
              });
              return false;
            }
            localPackageInfo = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 4101);
            localObject = localPackageInfo.activities;
            if (localObject == null)
              break label575;
            i = 0;
            bool2 = false;
            bool3 = bool2;
            bool1 = bool2;
          }
          catch (Exception paramContext)
          {
            try
            {
              PackageInfo localPackageInfo;
              if (i < localPackageInfo.activities.length)
              {
                bool1 = bool2;
                if (!"com.umeng.update.UpdateDialogActivity".equals(localPackageInfo.activities[i].name))
                  break label568;
                bool2 = true;
                break label568;
              }
              if (!bool3)
              {
                bool1 = bool3;
                f.post(new Runnable()
                {
                  public void run()
                  {
                    Toast.makeText(this.a, "Please add Activity in AndroidManifest!", 1).show();
                  }
                });
                return false;
              }
              Object localObject = localPackageInfo.services;
              if (localObject == null)
                break label588;
              i = 0;
              bool2 = false;
              bool3 = bool2;
              bool1 = bool2;
              if (i < localPackageInfo.services.length)
              {
                bool1 = bool2;
                if (!"com.umeng.update.net.DownloadingService".equals(localPackageInfo.services[i].name))
                  break label581;
                bool2 = true;
                break label581;
              }
              if (!bool3)
              {
                bool1 = bool3;
                f.post(new Runnable()
                {
                  public void run()
                  {
                    Toast.makeText(this.a, "Please add Service in AndroidManifest!", 1).show();
                  }
                });
                return false;
              }
              int i2;
              if (localPackageInfo.requestedPermissions != null)
              {
                m = 0;
                int k = 0;
                j = 0;
                i = 0;
                i2 = k;
                i1 = j;
                n = i;
                if (m < localPackageInfo.requestedPermissions.length)
                {
                  if ("android.permission.WRITE_EXTERNAL_STORAGE".equals(localPackageInfo.requestedPermissions[m]))
                  {
                    i1 = 1;
                    n = j;
                    break label594;
                  }
                  if ("android.permission.ACCESS_NETWORK_STATE".equals(localPackageInfo.requestedPermissions[m]))
                  {
                    n = 1;
                    i1 = i;
                    break label594;
                  }
                  bool1 = "android.permission.INTERNET".equals(localPackageInfo.requestedPermissions[m]);
                  n = j;
                  i1 = i;
                  if (!bool1)
                    break label594;
                  k = 1;
                  n = j;
                  i1 = i;
                  break label594;
                }
              }
              else
              {
                i2 = 0;
                i1 = 0;
                n = 0;
              }
              if ((n != 0) && (i1 != 0) && (i2 != 0));
              for (bool1 = true; !bool1; bool1 = false)
              {
                f.post(new Runnable()
                {
                  public void run()
                  {
                    Toast.makeText(this.a, "Please add Permission in AndroidManifest!", 1).show();
                  }
                });
                return false;
              }
              while (true)
              {
                try
                {
                  bool1 = "2.6.0.1.20150312".equals(paramContext.getString(Class.forName(paramContext.getPackageName() + ".R$string").getField("UMUpdateCheck").getInt(null)));
                  if (!bool1)
                    break label562;
                  bool2 = true;
                  if (bool2)
                    break;
                  bool1 = bool2;
                  f.post(new Runnable()
                  {
                    public void run()
                    {
                      Toast.makeText(this.a, "Please copy all resources (res/) from SDK to your project!", 1).show();
                    }
                  });
                  bool1 = bool2;
                  label537: return bool1;
                }
                catch (Exception localException)
                {
                  bool2 = false;
                  continue;
                }
                paramContext = paramContext;
                bool1 = false;
              }
            }
            catch (Exception paramContext)
            {
              break label537;
            }
          }
        }
      }
      catch (Exception paramContext)
      {
        return true;
      }
      label562: boolean bool2 = false;
      continue;
      label568: i += 1;
      continue;
      label575: boolean bool3 = false;
      continue;
      label581: i += 1;
      continue;
      label588: bool3 = false;
      continue;
      label594: m += 1;
      int j = n;
      int i = i1;
    }
  }

  public static File downloadedFile(Context paramContext, UpdateResponse paramUpdateResponse)
  {
    String str = paramUpdateResponse.new_md5 + ".apk";
    try
    {
      paramContext = new File(j.a("/apk", paramContext, new boolean[1]), str);
      if (paramContext.exists())
      {
        boolean bool = paramUpdateResponse.new_md5.equalsIgnoreCase(n.a(paramContext));
        if (bool)
          return paramContext;
      }
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
      return null;
    }
    return null;
  }

  public static void forceUpdate(Context paramContext)
  {
    UpdateConfig.setUpdateForce(true);
    UpdateConfig.setSilentDownload(false);
    b(paramContext.getApplicationContext());
  }

  public static boolean getUpdateFromPushMessage()
  {
    return g;
  }

  public static void ignoreUpdate(Context paramContext, UpdateResponse paramUpdateResponse)
  {
    UpdateConfig.saveIgnoreMd5(paramContext, paramUpdateResponse.new_md5);
  }

  public static boolean isIgnore(Context paramContext, UpdateResponse paramUpdateResponse)
  {
    return (paramUpdateResponse.new_md5 != null) && (paramUpdateResponse.new_md5.equalsIgnoreCase(UpdateConfig.getIgnoreMd5(paramContext))) && (!UpdateConfig.isUpdateForce());
  }

  public static void setAppkey(String paramString)
  {
    UpdateConfig.setAppkey(paramString);
  }

  public static void setChannel(String paramString)
  {
    UpdateConfig.setChannel(paramString);
  }

  public static void setDefault()
  {
    setDeltaUpdate(true);
    setUpdateAutoPopup(true);
    setUpdateOnlyWifi(true);
    setRichNotification(true);
    setDialogListener(null);
    setDownloadListener(null);
    setUpdateListener(null);
    setAppkey(null);
    setChannel(null);
    setUpdateUIStyle(0);
  }

  public static void setDeltaUpdate(boolean paramBoolean)
  {
    UpdateConfig.setDeltaUpdate(paramBoolean);
  }

  public static void setDialogListener(UmengDialogButtonListener paramUmengDialogButtonListener)
  {
    b = paramUmengDialogButtonListener;
  }

  public static void setDownloadListener(UmengDownloadListener paramUmengDownloadListener)
  {
    c = paramUmengDownloadListener;
  }

  public static void setRichNotification(boolean paramBoolean)
  {
    UpdateConfig.setRichNotification(paramBoolean);
  }

  public static void setSlotId(String paramString)
  {
    UpdateConfig.setSlotId(paramString);
  }

  public static void setUpdateAutoPopup(boolean paramBoolean)
  {
    UpdateConfig.setUpdateAutoPopup(paramBoolean);
  }

  public static void setUpdateCheckConfig(boolean paramBoolean)
  {
    UpdateConfig.setUpdateCheck(paramBoolean);
  }

  public static void setUpdateFromPushMessage(boolean paramBoolean)
  {
    g = paramBoolean;
  }

  public static void setUpdateListener(UmengUpdateListener paramUmengUpdateListener)
  {
    a = paramUmengUpdateListener;
  }

  public static void setUpdateOnlyWifi(boolean paramBoolean)
  {
    UpdateConfig.setUpdateOnlyWifi(paramBoolean);
  }

  public static void setUpdateUIStyle(int paramInt)
  {
    UpdateConfig.setStyle(paramInt);
  }

  public static void showUpdateDialog(Context paramContext, UpdateResponse paramUpdateResponse)
  {
    b(paramContext, paramUpdateResponse, 0);
  }

  public static void showUpdateNotification(Context paramContext, UpdateResponse paramUpdateResponse)
  {
    b(paramContext, paramUpdateResponse, 1);
  }

  public static void silentUpdate(Context paramContext)
  {
    UpdateConfig.setUpdateForce(false);
    UpdateConfig.setSilentDownload(true);
    b(paramContext.getApplicationContext());
  }

  public static void startDownload(Context paramContext, UpdateResponse paramUpdateResponse)
  {
    if ((paramUpdateResponse.delta) && (UpdateConfig.isDeltaUpdate()))
    {
      e.a(paramContext, paramUpdateResponse.origin, paramUpdateResponse.new_md5, paramUpdateResponse.path, paramUpdateResponse.patch_md5, c);
      e.b();
      return;
    }
    e.a(paramContext, paramUpdateResponse.path, paramUpdateResponse.new_md5, null, null, c);
    e.c();
  }

  public static void startInstall(Context paramContext, File paramFile)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.addFlags(268435456);
    localIntent.setDataAndType(Uri.fromFile(paramFile), "application/vnd.android.package-archive");
    paramContext.startActivity(localIntent);
  }

  public static void update(Context paramContext)
  {
    UpdateConfig.setUpdateForce(false);
    UpdateConfig.setSilentDownload(false);
    b(paramContext.getApplicationContext());
  }

  public static void update(Context paramContext, String paramString1, String paramString2)
  {
    UpdateConfig.setAppkey(paramString1);
    UpdateConfig.setChannel(paramString2);
    update(paramContext);
  }

  static class a
    implements Runnable
  {
    Context a;

    public a(Context paramContext)
    {
      this.a = paramContext;
    }

    public void run()
    {
      try
      {
        UpdateResponse localUpdateResponse = new b(this.a).a();
        if (localUpdateResponse == null)
        {
          UmengUpdateAgent.a(3, null);
          return;
        }
        if (!localUpdateResponse.hasUpdate)
        {
          UmengUpdateAgent.a(1, localUpdateResponse);
          return;
        }
      }
      catch (Exception localException)
      {
        UmengUpdateAgent.a(1, null);
        u.upd.b.a("update", "request update error", localException);
        return;
        UmengUpdateAgent.a(0, localException);
        return;
      }
      catch (Error localError)
      {
        u.upd.b.a("update", "request update error" + localError.getMessage());
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.update.UmengUpdateAgent
 * JD-Core Version:    0.6.2
 */