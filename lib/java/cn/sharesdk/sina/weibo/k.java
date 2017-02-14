package cn.sharesdk.sina.weibo;

import android.os.Handler.Callback;
import android.os.Message;
import cn.sharesdk.framework.PlatformActionListener;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.UIHandler;
import java.util.HashMap;

class k
  implements Handler.Callback
{
  int a = 0;

  k(i parami, DeviceHelper paramDeviceHelper, String paramString, PlatformActionListener paramPlatformActionListener, HashMap paramHashMap)
  {
  }

  public boolean handleMessage(Message paramMessage)
  {
    paramMessage = this.b.getTopTaskPackageName();
    if (!this.c.equals(paramMessage))
      if (this.d != null)
        this.d.onComplete(i.a(this.f), 9, this.e);
    while (true)
    {
      return true;
      if (this.a < 5)
      {
        this.a += 1;
        UIHandler.sendEmptyMessageDelayed(0, 500L, this);
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.sina.weibo.k
 * JD-Core Version:    0.6.2
 */