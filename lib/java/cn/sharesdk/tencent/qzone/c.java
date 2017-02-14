package cn.sharesdk.tencent.qzone;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import java.util.HashMap;

class c
  implements PlatformActionListener
{
  c(QZone paramQZone, Platform.ShareParams paramShareParams)
  {
  }

  public void onCancel(Platform paramPlatform, int paramInt)
  {
    if (QZone.m(this.b) != null)
      QZone.n(this.b).onCancel(this.b, 9);
  }

  public void onComplete(Platform paramPlatform, int paramInt, HashMap<String, Object> paramHashMap)
  {
    if (QZone.k(this.b) != null)
    {
      paramHashMap.put("ShareParams", this.a);
      QZone.l(this.b).onComplete(this.b, 9, paramHashMap);
    }
  }

  public void onError(Platform paramPlatform, int paramInt, Throwable paramThrowable)
  {
    if (QZone.i(this.b) != null)
      QZone.j(this.b).onError(this.b, 9, paramThrowable);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.tencent.qzone.c
 * JD-Core Version:    0.6.2
 */