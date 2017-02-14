package cn.sharesdk.tencent.qzone;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import java.util.HashMap;

class b
  implements PlatformActionListener
{
  b(QZone paramQZone, PlatformActionListener paramPlatformActionListener, Platform.ShareParams paramShareParams)
  {
  }

  public void onCancel(Platform paramPlatform, int paramInt)
  {
    if (this.a != null)
      this.a.onCancel(paramPlatform, 9);
  }

  public void onComplete(Platform paramPlatform, int paramInt, HashMap<String, Object> paramHashMap)
  {
    this.c.setPlatformActionListener(this.a);
    this.c.doShare(this.b);
  }

  public void onError(Platform paramPlatform, int paramInt, Throwable paramThrowable)
  {
    if (this.a != null)
      this.a.onError(paramPlatform, 9, paramThrowable);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.tencent.qzone.b
 * JD-Core Version:    0.6.2
 */