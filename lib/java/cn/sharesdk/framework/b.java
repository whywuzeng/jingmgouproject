package cn.sharesdk.framework;

import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.Ln;
import java.util.HashMap;

class b
  implements PlatformActionListener
{
  b(a parama, PlatformActionListener paramPlatformActionListener, int paramInt, HashMap paramHashMap)
  {
  }

  public void onCancel(Platform paramPlatform, int paramInt)
  {
    a.a(this.d, this.a);
    if (a.a(this.d) != null)
      a.a(this.d).onComplete(paramPlatform, this.b, this.c);
  }

  public void onComplete(Platform paramPlatform, int paramInt, HashMap<String, Object> paramHashMap)
  {
    a.a(this.d, this.a);
    if (a.a(this.d) != null)
      a.a(this.d).onComplete(paramPlatform, this.b, this.c);
    cn.sharesdk.framework.statistics.b.b localb = new cn.sharesdk.framework.statistics.b.b();
    localb.a = paramPlatform.getPlatformId();
    if ("TencentWeibo".equals(paramPlatform.getName()));
    for (String str = paramPlatform.getDb().get("name"); ; str = paramPlatform.getDb().getUserId())
    {
      localb.b = str;
      localb.c = new Hashon().fromHashMap(paramHashMap);
      localb.d = a.a(this.d, paramPlatform);
      cn.sharesdk.framework.statistics.b.a(paramPlatform.getContext()).a(localb);
      return;
    }
  }

  public void onError(Platform paramPlatform, int paramInt, Throwable paramThrowable)
  {
    Ln.e(paramThrowable);
    a.a(this.d, this.a);
    if (a.a(this.d) != null)
      a.a(this.d).onComplete(paramPlatform, this.b, this.c);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.b
 * JD-Core Version:    0.6.2
 */