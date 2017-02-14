package cn.jpush.android.api;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.widget.RemoteViews;
import cn.jpush.android.a;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.util.ai;
import cn.jpush.android.util.x;

final class p extends Handler
{
  private static final String[] z;
  private NotificationManager a;
  private Notification b;

  static
  {
    String[] arrayOfString = new String[3];
    Object localObject2 = "";
    int i = -1;
    int j = 0;
    Object localObject1 = arrayOfString;
    localObject2 = ((String)localObject2).toCharArray();
    int k = localObject2.length;
    int m;
    Object localObject3;
    int n;
    int i1;
    label50: int i2;
    label99: label121: Object localObject4;
    int i3;
    if (k <= 1)
    {
      m = 0;
      localObject3 = localObject1;
      n = j;
      localObject1 = localObject2;
      i1 = i;
      j = m;
      while (true)
      {
        localObject2 = localObject1;
        i2 = localObject2[m];
        switch (j % 5)
        {
        default:
          i = 18;
          localObject2[m] = ((char)(i ^ i2));
          j += 1;
          if (k != 0)
            break label121;
          m = k;
        case 0:
        case 1:
        case 2:
        case 3:
        }
      }
      i = k;
      localObject4 = localObject3;
      i3 = n;
      localObject2 = localObject1;
      i2 = i1;
    }
    while (true)
    {
      i1 = i2;
      localObject1 = localObject2;
      n = i3;
      localObject3 = localObject4;
      k = i;
      m = j;
      if (i > j)
        break label50;
      localObject1 = new String((char[])localObject2).intern();
      switch (i2)
      {
      default:
        localObject4[i3] = localObject1;
        localObject2 = "\016f[\001q\004";
        j = 1;
        i = 0;
        break;
      case 0:
        localObject4[i3] = localObject1;
        j = 2;
        localObject2 = "\001|d\021B\006a`";
        i = 1;
        break;
      case 1:
        localObject4[i3] = localObject1;
        z = arrayOfString;
        return;
        i = 103;
        break label99;
        i = 21;
        break label99;
        i = 8;
        break label99;
        i = 116;
        break label99;
        m = 0;
        i2 = i;
        i3 = j;
        localObject4 = localObject1;
        i = k;
        j = m;
      }
    }
  }

  public p(Looper paramLooper, Notification paramNotification, NotificationManager paramNotificationManager)
  {
    super(paramLooper);
    this.b = paramNotification;
    this.a = paramNotificationManager;
  }

  public final void handleMessage(Message paramMessage)
  {
    super.handleMessage(paramMessage);
    Object localObject1 = paramMessage.getData();
    boolean bool = ((Bundle)localObject1).getBoolean(z[1]);
    Object localObject2 = ((Bundle)localObject1).getString(z[2]);
    if ((bool) && (!TextUtils.isEmpty((CharSequence)localObject2)));
    while (true)
    {
      try
      {
        localObject2 = BitmapFactory.decodeFile((String)localObject2);
        if (localObject2 != null)
          this.b.contentView.setImageViewBitmap(16908294, (Bitmap)localObject2);
        localObject1 = ((Bundle)localObject1).getString(z[0]);
        if (!ai.a((String)localObject1))
          ServiceInterface.a((String)localObject1, 1018, a.d);
        this.a.notify(paramMessage.what, this.b);
        return;
      }
      catch (Exception localException)
      {
        x.j();
        continue;
      }
      x.b();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.api.p
 * JD-Core Version:    0.6.2
 */