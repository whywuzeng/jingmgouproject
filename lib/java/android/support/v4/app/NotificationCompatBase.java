package android.support.v4.app;

import android.app.PendingIntent;
import android.os.Bundle;

class NotificationCompatBase
{
  public static abstract class Action
  {
    protected abstract PendingIntent getActionIntent();

    protected abstract Bundle getExtras();

    protected abstract int getIcon();

    protected abstract RemoteInputCompatBase.RemoteInput[] getRemoteInputs();

    protected abstract CharSequence getTitle();

    public static abstract interface Factory
    {
      public abstract NotificationCompatBase.Action build(int paramInt, CharSequence paramCharSequence, PendingIntent paramPendingIntent, Bundle paramBundle, RemoteInputCompatBase.RemoteInput[] paramArrayOfRemoteInput);

      public abstract NotificationCompatBase.Action[] newArray(int paramInt);
    }
  }

  public static abstract class UnreadConversation
  {
    abstract long getLatestTimestamp();

    abstract String[] getMessages();

    abstract String getParticipant();

    abstract String[] getParticipants();

    abstract PendingIntent getReadPendingIntent();

    abstract RemoteInputCompatBase.RemoteInput getRemoteInput();

    abstract PendingIntent getReplyPendingIntent();

    public static abstract interface Factory
    {
      public abstract NotificationCompatBase.UnreadConversation build(String[] paramArrayOfString1, RemoteInputCompatBase.RemoteInput paramRemoteInput, PendingIntent paramPendingIntent1, PendingIntent paramPendingIntent2, String[] paramArrayOfString2, long paramLong);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     android.support.v4.app.NotificationCompatBase
 * JD-Core Version:    0.6.2
 */