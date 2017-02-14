package com.umeng.fb;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import com.umeng.fb.model.Conversation;
import com.umeng.fb.model.Reply;
import com.umeng.fb.model.Store;
import com.umeng.fb.model.UserInfo;
import com.umeng.fb.net.a;
import com.umeng.fb.push.c;
import com.umeng.fb.res.g;
import com.umeng.fb.util.Log;
import java.util.List;
import java.util.Locale;

public class FeedbackAgent
{
  private static final String a = FeedbackAgent.class.getName();
  private static boolean d = false;
  private Context b;
  private Store c;

  public FeedbackAgent(Context paramContext)
  {
    this.b = paramContext;
    this.c = Store.getInstance(this.b);
    a();
  }

  private void a()
  {
    if (!this.c.isMigrated())
      this.c.migrateData();
    if (TextUtils.isEmpty(this.c.getUid()))
      new Thread()
      {
        public void run()
        {
          new a(FeedbackAgent.a(FeedbackAgent.this)).a();
        }
      }
      .start();
  }

  public static void setDebug(boolean paramBoolean)
  {
    Log.LOG = paramBoolean;
  }

  public void closeAudioFeedback()
  {
    com.umeng.fb.common.b.a(this.b).c(false);
  }

  public void closeFeedbackPush()
  {
    com.umeng.fb.push.b.a(this.b).disable();
  }

  public List<String> getAllConversationIds()
  {
    return this.c.getAllConversationIds();
  }

  public Conversation getConversationById(String paramString)
  {
    return this.c.getConversationById(paramString);
  }

  public Conversation getDefaultConversation()
  {
    List localList = getAllConversationIds();
    if ((localList == null) || (localList.size() < 1))
    {
      Log.c(a, "getDefaultConversation: No conversation saved locally. Create a new one.");
      return Conversation.newInstance(this.b);
    }
    Log.c(a, "getDefaultConversation: There are " + localList.size() + " saved locally, use the first one by default.");
    return getConversationById((String)localList.get(0));
  }

  public UserInfo getUserInfo()
  {
    return this.c.getUserInfo();
  }

  public long getUserInfoLastUpdateAt()
  {
    return this.c.getUserInfoLastUpdateAt();
  }

  public void openAudioFeedback()
  {
    com.umeng.fb.common.b.a(this.b).c(true);
  }

  public void openFeedbackPush()
  {
    com.umeng.fb.push.b.a(this.b).enable();
  }

  public void removeWelcomeInfo()
  {
    com.umeng.fb.common.b.a(this.b).b(false);
  }

  public void setUserInfo(UserInfo paramUserInfo)
  {
    this.c.saveUserInfo(paramUserInfo);
  }

  public void setWelcomeInfo()
  {
    com.umeng.fb.common.b.a(this.b).b(true);
  }

  public void setWelcomeInfo(String paramString)
  {
    com.umeng.fb.common.b.a(this.b).b(true);
    if (paramString != null)
      com.umeng.fb.common.b.a(this.b).a(paramString);
  }

  public void showReplyNotification(List<Reply> paramList)
  {
    Object localObject1;
    if (paramList.size() == 1)
    {
      localObject1 = this.b.getResources().getString(g.b(this.b));
      paramList = String.format(Locale.US, (String)localObject1, new Object[] { ((Reply)paramList.get(0)).content });
    }
    try
    {
      while (true)
      {
        localObject1 = (NotificationManager)this.b.getSystemService("notification");
        String str = this.b.getString(g.a(this.b));
        Object localObject2 = new Intent(this.b, ConversationActivity.class);
        ((Intent)localObject2).setFlags(131072);
        int i = (int)SystemClock.uptimeMillis();
        localObject2 = PendingIntent.getActivity(this.b, i, (Intent)localObject2, 134217728);
        i = this.b.getPackageManager().getPackageInfo(this.b.getPackageName(), 0).applicationInfo.icon;
        ((NotificationManager)localObject1).notify(0, new NotificationCompat.Builder(this.b).setSmallIcon(i).setContentTitle(str).setTicker(str).setContentText(paramList).setAutoCancel(true).setContentIntent((PendingIntent)localObject2).build());
        return;
        localObject1 = this.b.getResources().getString(g.c(this.b));
        paramList = String.format(Locale.US, (String)localObject1, new Object[] { Integer.valueOf(paramList.size()) });
      }
    }
    catch (Exception paramList)
    {
      paramList.printStackTrace();
    }
  }

  public void startFeedbackActivity()
  {
    try
    {
      Intent localIntent = new Intent();
      localIntent.setClass(this.b, ConversationActivity.class);
      this.b.startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void startFeedbackActivity2()
  {
    try
    {
      Intent localIntent = new Intent();
      localIntent.setClass(this.b, HelpActivity.class);
      this.b.startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void sync()
  {
    SyncListener local2 = new SyncListener()
    {
      public void onReceiveDevReply(List<Reply> paramAnonymousList)
      {
        if ((paramAnonymousList == null) || (paramAnonymousList.size() < 1))
          return;
        FeedbackAgent.this.showReplyNotification(paramAnonymousList);
      }

      public void onSendUserReply(List<Reply> paramAnonymousList)
      {
      }
    };
    getDefaultConversation().sync(local2);
  }

  public boolean updateUserInfo()
  {
    return new a(this.b).a(Store.getInstance(this.b).getUserInfo().toJson());
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.FeedbackAgent
 * JD-Core Version:    0.6.2
 */