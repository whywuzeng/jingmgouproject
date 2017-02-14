package com.umeng.fb.push;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat.Builder;
import com.umeng.fb.ConversationActivity;
import com.umeng.fb.model.Reply;
import com.umeng.fb.model.Store;
import com.umeng.fb.res.g;
import com.umeng.fb.util.Log;
import com.umeng.fb.util.d;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.entity.UMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class b
  implements c
{
  private static final String a = b.class.getName();
  private static c b;
  private Context c;
  private Class<?> d;
  private SharedPreferences e;
  private final String f = "feedback_push";
  private final String g = "alias";
  private final String h = "umeng_feedback";
  private final String i = "feedback_id";
  private final String j = "switch";
  private List k;
  private c.a l;
  private boolean m = false;
  private String n;
  private boolean o;
  private int p;
  private String q;

  private b(Context paramContext)
  {
    this.c = paramContext;
    this.k = new ArrayList();
    this.e = this.c.getSharedPreferences("feedback_push", 0);
  }

  public static c a(Context paramContext)
  {
    if (b == null)
      b = new b(paramContext);
    return b;
  }

  private void a()
  {
    Log.c(a, "setAlias UUID " + Store.getInstance(this.c).getDeviceUUID());
    if (!this.e.getBoolean("alias", false))
      new Thread()
      {
        public void run()
        {
          int i = 0;
          while (true)
            if (i < 10)
              try
              {
                if (PushAgent.getInstance(b.a(b.this)).addAlias(Store.getInstance(b.a(b.this)).getDeviceUUID(), "umeng_feedback"))
                {
                  d.a(b.b(b.this).edit().putBoolean("alias", true));
                  return;
                }
                sleep(1000L);
                i += 1;
              }
              catch (Exception localException)
              {
                localException.printStackTrace();
              }
        }
      }
      .start();
  }

  private void a(List paramList, String paramString1, String paramString2)
  {
    if (this.p == 1)
    {
      paramList = this.c.getResources().getString(g.b(this.c));
      paramList = String.format(Locale.US, paramList, new Object[] { paramString2 });
    }
    try
    {
      NotificationManager localNotificationManager = (NotificationManager)this.c.getSystemService("notification");
      String str = this.c.getString(g.a(this.c));
      int i1 = this.c.getPackageManager().getPackageInfo(this.c.getPackageName(), 0).applicationInfo.icon;
      if (this.d != null);
      for (paramString2 = new Intent(this.c, this.d); ; paramString2 = new Intent(this.c, ConversationActivity.class))
      {
        paramString2.setFlags(131072);
        paramString2.putExtra("conversation_id", paramString1);
        int i2 = (int)SystemClock.uptimeMillis();
        paramString1 = PendingIntent.getActivity(this.c, i2, paramString2, 134217728);
        localNotificationManager.notify(0, new NotificationCompat.Builder(this.c).setSmallIcon(i1).setContentTitle(str).setTicker(str).setContentText(paramList).setAutoCancel(true).setContentIntent(paramString1).build());
        return;
        paramList = this.c.getResources().getString(g.c(this.c));
        paramList = String.format(Locale.US, paramList, new Object[] { Integer.valueOf(this.p) });
        break;
      }
    }
    catch (Exception paramList)
    {
      Log.c(a, paramList.toString());
      paramList.printStackTrace();
    }
  }

  private boolean a(String paramString)
  {
    try
    {
      paramString = new JSONObject(paramString).optString("feedback_id", null);
      if (paramString == null)
        return false;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
      return false;
    }
    return true;
  }

  public void clearPushInfo()
  {
    this.p = 0;
    this.q = "";
  }

  public boolean dealFBMessage(FBMessage paramFBMessage)
  {
    Log.c(a, "received push message  - " + paramFBMessage.custom);
    if (!a(paramFBMessage.custom))
      return false;
    if (this.e == null)
      this.e = this.c.getSharedPreferences("feedback_push", 0);
    this.o = this.e.getBoolean("switch", false);
    if (!this.o)
      return true;
    while (true)
    {
      String str;
      try
      {
        paramFBMessage = new JSONObject(paramFBMessage.custom);
        str = paramFBMessage.getString("feedback_id");
        if (str != null)
        {
          if (this.p < 1)
            this.q = Reply.fromJson(paramFBMessage).content;
          this.k.add(str);
          this.p += 1;
        }
        if (!this.m)
          break label210;
        if ((this.n != null) && (this.n.endsWith(str)))
        {
          this.l.onAddPushDevReply();
          return true;
        }
      }
      catch (JSONException paramFBMessage)
      {
        paramFBMessage.printStackTrace();
        return false;
      }
      a(this.k, str, this.q);
      continue;
      label210: a(this.k, str, this.q);
    }
  }

  public void disable()
  {
    d.a(this.e.edit().putBoolean("switch", false));
    this.o = false;
  }

  public void enable()
  {
    d.a(this.e.edit().putBoolean("switch", true));
    this.o = true;
  }

  public void init(Class<?> paramClass, boolean paramBoolean)
  {
    this.d = paramClass;
    init(paramBoolean);
  }

  public void init(boolean paramBoolean)
  {
    a();
    if (paramBoolean)
      return;
    try
    {
      UmengMessageHandler local1 = new UmengMessageHandler()
      {
        public void dealWithCustomMessage(Context paramAnonymousContext, UMessage paramAnonymousUMessage)
        {
          b.this.dealFBMessage(new FBMessage(paramAnonymousUMessage.custom));
        }
      };
      PushAgent.getInstance(this.c).setMessageHandler(local1);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public boolean onFBMessage(Intent paramIntent)
  {
    paramIntent = paramIntent.getStringExtra("body");
    try
    {
      paramIntent = new UMessage(new JSONObject(paramIntent));
      return dealFBMessage(new FBMessage(paramIntent.custom));
    }
    catch (JSONException paramIntent)
    {
      paramIntent.printStackTrace();
    }
    return false;
  }

  public void setConversationId(String paramString)
  {
    this.n = paramString;
  }

  public void setFBPushCallbacks(c.a parama)
  {
    this.l = parama;
  }

  public void setFbFragmentTag(boolean paramBoolean)
  {
    this.m = paramBoolean;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.push.b
 * JD-Core Version:    0.6.2
 */