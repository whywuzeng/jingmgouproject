package cn.sharesdk.onekeyshare;

import cn.sharesdk.framework.Platform;
import java.util.HashMap;

public abstract interface ThemeShareCallback
{
  public abstract void doShare(HashMap<Platform, HashMap<String, Object>> paramHashMap);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.onekeyshare.ThemeShareCallback
 * JD-Core Version:    0.6.2
 */