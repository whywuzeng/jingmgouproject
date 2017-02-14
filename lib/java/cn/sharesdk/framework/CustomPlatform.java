package cn.sharesdk.framework;

import android.content.Context;
import cn.sharesdk.framework.statistics.b.f.a;
import java.util.HashMap;

public abstract class CustomPlatform extends Platform
{
  public CustomPlatform(Context paramContext)
  {
    super(paramContext);
  }

  protected abstract boolean checkAuthorize(int paramInt, Object paramObject);

  protected void doAuthorize(String[] paramArrayOfString)
  {
  }

  protected void doCustomerProtocol(String paramString1, String paramString2, int paramInt, HashMap<String, Object> paramHashMap, HashMap<String, String> paramHashMap1)
  {
  }

  protected void doShare(Platform.ShareParams paramShareParams)
  {
  }

  protected final f.a filterShareContent(Platform.ShareParams paramShareParams, HashMap<String, Object> paramHashMap)
  {
    return null;
  }

  protected void follow(String paramString)
  {
  }

  protected int getCustomPlatformId()
  {
    return 1;
  }

  protected void getFriendList(int paramInt1, int paramInt2, String paramString)
  {
  }

  public abstract String getName();

  protected final int getPlatformId()
  {
    return -Math.abs(getCustomPlatformId());
  }

  public int getVersion()
  {
    return 0;
  }

  protected final void initDevInfo(String paramString)
  {
  }

  protected final void setNetworkDevinfo()
  {
  }

  protected void timeline(int paramInt1, int paramInt2, String paramString)
  {
  }

  protected void userInfor(String paramString)
  {
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.CustomPlatform
 * JD-Core Version:    0.6.2
 */