package cn.sharesdk.framework;

import java.util.Comparator;

class j
  implements Comparator<Platform>
{
  j(i parami)
  {
  }

  public int a(Platform paramPlatform1, Platform paramPlatform2)
  {
    if (paramPlatform1.getSortId() != paramPlatform2.getSortId())
      return paramPlatform1.getSortId() - paramPlatform2.getSortId();
    return paramPlatform1.getPlatformId() - paramPlatform2.getPlatformId();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.j
 * JD-Core Version:    0.6.2
 */