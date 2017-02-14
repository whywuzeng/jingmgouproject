package cn.smssdk.a;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import cn.smssdk.utils.Protocols;
import java.util.ArrayList;

public class b
  implements Handler.Callback
{
  private Handler a = new Handler(this);
  private cn.smssdk.contact.a b;
  private cn.smssdk.utils.b c;
  private Protocols d;
  private a e;
  private int f;
  private Handler.Callback g;

  public b(Context paramContext, a parama)
  {
    this.b = cn.smssdk.contact.a.a(paramContext);
    this.c = cn.smssdk.utils.b.a(paramContext, "SMSSDK");
    this.d = Protocols.a(paramContext);
    this.e = parama;
  }

  public int a()
  {
    int m = 0;
    this.b.b();
    String[] arrayOfString1 = this.b.c();
    String[] arrayOfString2 = this.c.i();
    this.c.a(arrayOfString1);
    ArrayList localArrayList = new ArrayList();
    int i1 = arrayOfString1.length;
    int i = 0;
    while (i < i1)
    {
      String str = arrayOfString1[i];
      if (str == null)
      {
        i += 1;
      }
      else
      {
        int n = 1;
        int i2 = arrayOfString2.length;
        int j = 0;
        while (true)
        {
          int k = n;
          if (j < i2)
          {
            if (str.equals(arrayOfString2[j]))
              k = 0;
          }
          else
          {
            if (k == 0)
              break;
            localArrayList.add(str);
            break;
          }
          j += 1;
        }
      }
    }
    i = m;
    if (localArrayList.size() > 0)
    {
      arrayOfString1 = new String[localArrayList.size()];
      i = 0;
      while (i < arrayOfString1.length)
      {
        arrayOfString1[i] = ((String)localArrayList.get(i));
        i += 1;
      }
      localArrayList = this.d.a(arrayOfString1);
      localArrayList = this.e.a(localArrayList);
      this.c.c(localArrayList);
      this.c.h();
      i = localArrayList.size();
    }
    return i;
  }

  public void a(int paramInt, Handler.Callback paramCallback)
  {
    this.a.removeMessages(1);
    this.f = paramInt;
    this.g = paramCallback;
    this.a.sendEmptyMessageDelayed(1, 5000L);
  }

  public boolean handleMessage(Message paramMessage)
  {
    new c(this).start();
    return false;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.a.b
 * JD-Core Version:    0.6.2
 */