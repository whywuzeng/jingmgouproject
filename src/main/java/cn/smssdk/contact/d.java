package cn.smssdk.contact;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import cn.smssdk.framework.utils.Data;
import cn.smssdk.utils.Protocols;
import cn.smssdk.utils.b;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class d
  implements Handler.Callback
{
  private Handler a = new Handler(this);
  private a b;
  private b c;
  private Protocols d;

  public d(Context paramContext, a parama)
  {
    this.b = parama;
    this.c = b.a(paramContext, "SMSSDK");
    this.d = Protocols.a(paramContext);
  }

  private String a(Object paramObject)
  {
    if (paramObject == null)
      return null;
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localByteArrayOutputStream);
    localObjectOutputStream.writeObject(paramObject);
    localObjectOutputStream.flush();
    localObjectOutputStream.close();
    return Data.CRC32(localByteArrayOutputStream.toByteArray());
  }

  public void a()
  {
    this.a.removeMessages(1);
    this.a.sendEmptyMessageDelayed(1, 100000L);
  }

  public boolean handleMessage(Message paramMessage)
  {
    new e(this).start();
    return false;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.contact.d
 * JD-Core Version:    0.6.2
 */