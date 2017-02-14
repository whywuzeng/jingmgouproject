package cn.sharesdk.sina.weibo;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import java.util.HashMap;

class d
  implements PlatformActionListener
{
  d(SinaWeibo paramSinaWeibo)
  {
  }

  public void onCancel(Platform paramPlatform, int paramInt)
  {
    if (SinaWeibo.c(this.a) != null)
      SinaWeibo.d(this.a).onCancel(paramPlatform, 1);
  }

  public void onComplete(Platform paramPlatform, int paramInt, HashMap<String, Object> paramHashMap)
  {
    SinaWeibo.a(this.a, 1, null);
  }

  public void onError(Platform paramPlatform, int paramInt, Throwable paramThrowable)
  {
    if (SinaWeibo.a(this.a) != null)
      SinaWeibo.b(this.a).onError(paramPlatform, 1, paramThrowable);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.sina.weibo.d
 * JD-Core Version:    0.6.2
 */