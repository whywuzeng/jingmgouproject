package cn.sharesdk.tencent.qq;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import java.util.HashMap;

class b
  implements PlatformActionListener
{
  b(QQ paramQQ, Platform.ShareParams paramShareParams)
  {
  }

  public void onCancel(Platform paramPlatform, int paramInt)
  {
    if (QQ.m(this.b) != null)
      QQ.n(this.b).onCancel(this.b, 9);
  }

  public void onComplete(Platform paramPlatform, int paramInt, HashMap<String, Object> paramHashMap)
  {
    paramPlatform = new HashMap();
    if (paramHashMap != null)
      paramPlatform.putAll(paramHashMap);
    paramPlatform.put("ShareParams", this.a);
    if (QQ.k(this.b) != null)
      QQ.l(this.b).onComplete(this.b, 9, paramPlatform);
  }

  public void onError(Platform paramPlatform, int paramInt, Throwable paramThrowable)
  {
    if (QQ.i(this.b) != null)
      QQ.j(this.b).onError(this.b, 9, paramThrowable);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.tencent.qq.b
 * JD-Core Version:    0.6.2
 */