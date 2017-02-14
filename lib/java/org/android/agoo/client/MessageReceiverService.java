package org.android.agoo.client;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.umeng.message.proguard.bd;
import org.android.agoo.service.ElectionReceiverService.Stub;
import org.android.agoo.service.SendMessage.Stub;

public abstract class MessageReceiverService extends Service
{
  private static final String TAG = "MessageReceiverService";
  ElectionReceiverService.Stub binderElecRecv = new ElectionReceiverService.Stub()
  {
    public boolean sendElectionResult(Intent paramAnonymousIntent)
    {
      try
      {
        BaseIntentService.runIntentInService(MessageReceiverService.this.getApplicationContext(), paramAnonymousIntent, MessageReceiverService.this.getIntentServiceClassName(MessageReceiverService.this.getApplicationContext()));
        return true;
      }
      catch (Throwable paramAnonymousIntent)
      {
      }
      return false;
    }
  };
  SendMessage.Stub binderMsgRecv = new SendMessage.Stub()
  {
    public int doSend(Intent paramAnonymousIntent)
      throws RemoteException
    {
      BaseIntentService.runIntentInService(MessageReceiverService.this.getApplicationContext(), paramAnonymousIntent, MessageReceiverService.this.getIntentServiceClassName(MessageReceiverService.this.getApplicationContext()));
      return 0;
    }
  };

  public abstract String getIntentServiceClassName(Context paramContext);

  public IBinder onBind(Intent paramIntent)
  {
    bd.a("MessageReceiverService", new Object[] { "Message receiver aidl was binded {}", paramIntent.getAction() });
    if ("org.android.agoo.client.ElectionReceiverService".equals(paramIntent.getAction()))
      return this.binderElecRecv;
    if ("org.android.agoo.client.MessageReceiverService".equals(paramIntent.getAction()))
      return this.binderMsgRecv;
    return this.binderMsgRecv;
  }

  public void onCreate()
  {
    super.onCreate();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.client.MessageReceiverService
 * JD-Core Version:    0.6.2
 */