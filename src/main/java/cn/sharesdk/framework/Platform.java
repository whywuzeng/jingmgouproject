package cn.sharesdk.framework;

import android.content.Context;
import android.graphics.Bitmap;
import cn.sharesdk.framework.statistics.b.f.a;
import java.util.HashMap;

public abstract class Platform
{
  public static final int ACTION_AUTHORIZING = 1;
  protected static final int ACTION_CUSTOMER = 655360;
  public static final int ACTION_FOLLOWING_USER = 6;
  public static final int ACTION_GETTING_FRIEND_LIST = 2;
  public static final int ACTION_SENDING_DIRECT_MESSAGE = 5;
  public static final int ACTION_SHARE = 9;
  public static final int ACTION_TIMELINE = 7;
  public static final int ACTION_USER_INFOR = 8;
  public static final int CUSTOMER_ACTION_MASK = 65535;
  public static final int SHARE_APPS = 7;
  public static final int SHARE_EMOJI = 9;
  public static final int SHARE_FILE = 8;
  public static final int SHARE_IMAGE = 2;
  public static final int SHARE_MUSIC = 5;
  public static final int SHARE_TEXT = 1;
  public static final int SHARE_VIDEO = 6;
  public static final int SHARE_WEBPAGE = 4;
  private f a;
  protected final Context context;
  protected final PlatformDb db;
  protected PlatformActionListener listener;

  public Platform(Context paramContext)
  {
    this.context = paramContext;
    this.a = new f(this, paramContext);
    this.db = this.a.g();
    this.listener = this.a.i();
  }

  public void SSOSetting(boolean paramBoolean)
  {
    this.a.a(paramBoolean);
  }

  void a()
  {
    this.a.a(false);
    this.a.a(getName());
  }

  protected void afterRegister(int paramInt, Object paramObject)
  {
    this.a.b(paramInt, paramObject);
  }

  public void authorize()
  {
    authorize(null);
  }

  public void authorize(String[] paramArrayOfString)
  {
    this.a.a(paramArrayOfString);
  }

  boolean b()
  {
    return this.a.f();
  }

  protected abstract boolean checkAuthorize(int paramInt, Object paramObject);

  protected void copyDevinfo(String paramString1, String paramString2)
  {
    ShareSDK.a(paramString1, paramString2);
  }

  protected void copyNetworkDevinfo(int paramInt1, int paramInt2)
  {
    ShareSDK.a(paramInt1, paramInt2);
  }

  public void customerProtocol(String paramString1, String paramString2, short paramShort, HashMap<String, Object> paramHashMap, HashMap<String, String> paramHashMap1)
  {
    this.a.a(paramString1, paramString2, paramShort, paramHashMap, paramHashMap1);
  }

  protected abstract void doAuthorize(String[] paramArrayOfString);

  protected abstract void doCustomerProtocol(String paramString1, String paramString2, int paramInt, HashMap<String, Object> paramHashMap, HashMap<String, String> paramHashMap1);

  protected abstract void doShare(ShareParams paramShareParams);

  protected abstract f.a filterShareContent(ShareParams paramShareParams, HashMap<String, Object> paramHashMap);

  protected abstract void follow(String paramString);

  public void followFriend(String paramString)
  {
    this.a.b(paramString);
  }

  public Context getContext()
  {
    return this.context;
  }

  public PlatformDb getDb()
  {
    return this.db;
  }

  public String getDevinfo(String paramString)
  {
    return getDevinfo(getName(), paramString);
  }

  public String getDevinfo(String paramString1, String paramString2)
  {
    return ShareSDK.b(paramString1, paramString2);
  }

  protected abstract void getFriendList(int paramInt1, int paramInt2, String paramString);

  public int getId()
  {
    return this.a.a();
  }

  public abstract String getName();

  protected String getNetworkDevinfo(int paramInt, String paramString1, String paramString2)
  {
    return this.a.a(paramInt, paramString1, paramString2);
  }

  protected String getNetworkDevinfo(String paramString1, String paramString2)
  {
    return getNetworkDevinfo(getPlatformId(), paramString1, paramString2);
  }

  public PlatformActionListener getPlatformActionListener()
  {
    return this.a.c();
  }

  protected abstract int getPlatformId();

  public String getShortLintk(String paramString, boolean paramBoolean)
  {
    return this.a.a(paramString, paramBoolean);
  }

  public int getSortId()
  {
    return this.a.b();
  }

  public void getTimeLine(String paramString, int paramInt1, int paramInt2)
  {
    this.a.a(paramString, paramInt1, paramInt2);
  }

  public abstract int getVersion();

  protected abstract void initDevInfo(String paramString);

  protected void innerAuthorize(int paramInt, Object paramObject)
  {
    this.a.a(paramInt, paramObject);
  }

  public boolean isAuthValid()
  {
    return this.a.d();
  }

  public boolean isClientValid()
  {
    return false;
  }

  public boolean isSSODisable()
  {
    return this.a.e();
  }

  @Deprecated
  public boolean isValid()
  {
    return this.a.d();
  }

  public void listFriend(int paramInt1, int paramInt2, String paramString)
  {
    this.a.a(paramInt1, paramInt2, paramString);
  }

  @Deprecated
  public void removeAccount()
  {
    this.a.h();
  }

  public void removeAccount(boolean paramBoolean)
  {
    this.a.h();
    ShareSDK.removeCookieOnAuthorize(paramBoolean);
  }

  protected abstract void setNetworkDevinfo();

  public void setPlatformActionListener(PlatformActionListener paramPlatformActionListener)
  {
    this.a.a(paramPlatformActionListener);
  }

  public void share(ShareParams paramShareParams)
  {
    this.a.a(paramShareParams);
  }

  public void showUser(String paramString)
  {
    this.a.c(paramString);
  }

  protected abstract void timeline(int paramInt1, int paramInt2, String paramString);

  protected String uploadImageToFileServer(Bitmap paramBitmap)
  {
    return this.a.a(paramBitmap);
  }

  protected String uploadImageToFileServer(String paramString)
  {
    return this.a.d(paramString);
  }

  protected abstract void userInfor(String paramString);

  public static class ShareParams extends d
  {

    @Deprecated
    public String imagePath;

    @Deprecated
    public String text;

    public ShareParams()
    {
    }

    public ShareParams(String paramString)
    {
      super();
    }

    public ShareParams(HashMap<String, Object> paramHashMap)
    {
      super();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.Platform
 * JD-Core Version:    0.6.2
 */