package cn.sharesdk.wechat.moments;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.statistics.b.f.a;
import cn.sharesdk.wechat.utils.WechatClientNotExistException;
import cn.sharesdk.wechat.utils.WechatHelper;
import cn.sharesdk.wechat.utils.WechatHelper.ShareParams;
import cn.sharesdk.wechat.utils.WechatTimelineNotSupportedException;
import cn.sharesdk.wechat.utils.g;
import cn.sharesdk.wechat.utils.k;
import com.mob.tools.utils.Ln;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

public class WechatMoments extends Platform
{
  public static final String NAME = WechatMoments.class.getSimpleName();
  private String a;
  private String b;
  private boolean c;

  public WechatMoments(Context paramContext)
  {
    super(paramContext);
  }

  protected boolean checkAuthorize(int paramInt, Object paramObject)
  {
    WechatHelper localWechatHelper = WechatHelper.a();
    localWechatHelper.a(this.context, this.a);
    if (!localWechatHelper.c())
      if (this.listener != null)
        this.listener.onError(this, paramInt, new WechatClientNotExistException());
    do
    {
      return false;
      if (localWechatHelper.d())
        break;
    }
    while (this.listener == null);
    this.listener.onError(this, 1, new WechatTimelineNotSupportedException());
    return false;
    if ((paramInt == 9) || (isAuthValid()))
      return true;
    if (TextUtils.isEmpty(getDb().get("refresh_token")));
    innerAuthorize(paramInt, paramObject);
    return false;
  }

  protected void doAuthorize(String[] paramArrayOfString)
  {
    if ((TextUtils.isEmpty(this.a)) || (TextUtils.isEmpty(this.b)))
      if (this.listener != null)
        this.listener.onError(this, 8, new Throwable("The params of appID or appSecret is missing !"));
    do
    {
      do
      {
        do
        {
          return;
          paramArrayOfString = WechatHelper.a();
          paramArrayOfString.a(this.context, this.a);
          if (paramArrayOfString.c())
            break;
        }
        while (this.listener == null);
        this.listener.onError(this, 1, new WechatClientNotExistException());
        return;
        if (paramArrayOfString.d())
          break;
      }
      while (this.listener == null);
      this.listener.onError(this, 1, new WechatTimelineNotSupportedException());
      return;
      g localg = new g(this, 23);
      localg.a(this.a, this.b);
      k localk = new k(this);
      localk.a(localg);
      localk.a(new a(this));
      try
      {
        paramArrayOfString.a(localk);
        return;
      }
      catch (Throwable paramArrayOfString)
      {
      }
    }
    while (this.listener == null);
    this.listener.onError(this, 1, paramArrayOfString);
  }

  protected void doCustomerProtocol(String paramString1, String paramString2, int paramInt, HashMap<String, Object> paramHashMap, HashMap<String, String> paramHashMap1)
  {
    if (this.listener != null)
      this.listener.onCancel(this, paramInt);
  }

  protected void doShare(Platform.ShareParams paramShareParams)
  {
    paramShareParams.set("scene", Integer.valueOf(1));
    Object localObject = WechatHelper.a();
    ((WechatHelper)localObject).a(this.context, this.a);
    k localk = new k(this);
    if (this.c);
    do
      while (true)
      {
        try
        {
          ((WechatHelper)localObject).a(localk, paramShareParams, this.listener);
          return;
        }
        catch (Throwable paramShareParams)
        {
          if (this.listener == null)
            continue;
          this.listener.onError(this, 9, paramShareParams);
          return;
        }
        localk.a(paramShareParams, this.listener);
        try
        {
          ((WechatHelper)localObject).b(localk);
          if ((this.listener != null) && (((WechatHelper)localObject).b()))
          {
            localObject = new HashMap();
            ((HashMap)localObject).put("ShareParams", paramShareParams);
            if (this.listener != null)
            {
              this.listener.onComplete(this, 9, (HashMap)localObject);
              return;
            }
          }
        }
        catch (Throwable paramShareParams)
        {
        }
      }
    while (this.listener == null);
    this.listener.onError(this, 9, paramShareParams);
  }

  protected f.a filterShareContent(Platform.ShareParams paramShareParams, HashMap<String, Object> paramHashMap)
  {
    paramHashMap = new f.a();
    String str1 = paramShareParams.getText();
    paramHashMap.b = str1;
    String str2 = paramShareParams.getImageUrl();
    Object localObject = paramShareParams.getImagePath();
    Bitmap localBitmap = paramShareParams.getImageData();
    if (!TextUtils.isEmpty(str2))
      paramHashMap.d.add(str2);
    while (true)
    {
      str2 = paramShareParams.getUrl();
      if (str2 != null)
        paramHashMap.c.add(str2);
      localObject = new HashMap();
      ((HashMap)localObject).put("title", paramShareParams.getTitle());
      ((HashMap)localObject).put("url", str2);
      ((HashMap)localObject).put("extInfo", null);
      ((HashMap)localObject).put("content", str1);
      ((HashMap)localObject).put("image", paramHashMap.d);
      ((HashMap)localObject).put("musicFileUrl", str2);
      paramHashMap.g = ((HashMap)localObject);
      return paramHashMap;
      if (localObject != null)
        paramHashMap.e.add(localObject);
      else if (localBitmap != null)
        paramHashMap.f.add(localBitmap);
    }
  }

  protected void follow(String paramString)
  {
    if (this.listener != null)
      this.listener.onCancel(this, 6);
  }

  protected void getFriendList(int paramInt1, int paramInt2, String paramString)
  {
    if (this.listener != null)
      this.listener.onCancel(this, 2);
  }

  public String getName()
  {
    return NAME;
  }

  public int getPlatformId()
  {
    return 23;
  }

  public int getVersion()
  {
    return 1;
  }

  protected void initDevInfo(String paramString)
  {
    this.a = getDevinfo("AppId");
    this.b = getDevinfo("AppSecret");
    this.c = "true".equals(getDevinfo("BypassApproval"));
    if ((this.a == null) || (this.a.length() <= 0))
    {
      this.a = getDevinfo("Wechat", "AppId");
      this.c = "true".equals(getDevinfo("Wechat", "BypassApproval"));
      if ((this.a == null) || (this.a.length() <= 0))
        break label158;
      copyDevinfo("Wechat", NAME);
      this.a = getDevinfo("AppId");
      this.c = "true".equals(getDevinfo("BypassApproval"));
      if (ShareSDK.isDebug())
        System.err.println("Try to use the dev info of Wechat, this will cause Id and SortId field are always 0.");
    }
    label158: 
    do
    {
      do
      {
        return;
        this.a = getDevinfo("WechatFavorite", "AppId");
      }
      while ((this.a == null) || (this.a.length() <= 0));
      copyDevinfo("WechatFavorite", NAME);
      this.a = getDevinfo("AppId");
    }
    while (!ShareSDK.isDebug());
    System.err.println("Try to use the dev info of WechatFavorite, this will cause Id and SortId field are always 0.");
  }

  public boolean isClientValid()
  {
    WechatHelper localWechatHelper = WechatHelper.a();
    localWechatHelper.a(this.context, this.a);
    return (localWechatHelper.c()) && (localWechatHelper.d());
  }

  @Deprecated
  public boolean isValid()
  {
    WechatHelper localWechatHelper = WechatHelper.a();
    localWechatHelper.a(this.context, this.a);
    return (localWechatHelper.c()) && (localWechatHelper.d()) && (super.isValid());
  }

  protected void setNetworkDevinfo()
  {
    this.a = getNetworkDevinfo("app_id", "AppId");
    this.b = getNetworkDevinfo("app_secret", "AppSecret");
    if ((this.a == null) || (this.a.length() <= 0))
    {
      this.a = getNetworkDevinfo(22, "app_id", "AppId");
      if ((this.a == null) || (this.a.length() <= 0))
        break label200;
      copyNetworkDevinfo(22, 23);
      this.a = getNetworkDevinfo("app_id", "AppId");
      if (ShareSDK.isDebug())
        System.err.println("Try to use the dev info of Wechat, this will cause Id and SortId field are always 0.");
    }
    if ((this.b == null) || (this.b.length() <= 0))
    {
      this.b = getNetworkDevinfo(22, "app_secret", "AppSecret");
      if ((this.b == null) || (this.b.length() <= 0))
        break label271;
      copyNetworkDevinfo(22, 23);
      this.b = getNetworkDevinfo("app_secret", "AppSecret");
      if (ShareSDK.isDebug())
        System.err.println("Try to use the dev info of Wechat, this will cause Id and SortId field are always 0.");
    }
    label200: 
    do
    {
      do
      {
        return;
        this.a = getNetworkDevinfo(37, "app_id", "AppId");
        if ((this.a == null) || (this.a.length() <= 0))
          break;
        copyNetworkDevinfo(23, 23);
        this.a = getNetworkDevinfo("app_id", "AppId");
        if (!ShareSDK.isDebug())
          break;
        System.err.println("Try to use the dev info of WechatFavorite, this will cause Id and SortId field are always 0.");
        break;
        this.b = getNetworkDevinfo(37, "app_secret", "AppSecret");
      }
      while ((this.b == null) || (this.b.length() <= 0));
      copyNetworkDevinfo(23, 23);
      this.b = getNetworkDevinfo("app_secret", "AppSecret");
    }
    while (!ShareSDK.isDebug());
    label271: System.err.println("Try to use the dev info of WechatFavorite, this will cause Id and SortId field are always 0.");
  }

  protected void timeline(int paramInt1, int paramInt2, String paramString)
  {
    if (this.listener != null)
      this.listener.onCancel(this, 7);
  }

  protected void userInfor(String paramString)
  {
    if ((TextUtils.isEmpty(this.a)) || (TextUtils.isEmpty(this.b)))
      if (this.listener != null)
        this.listener.onError(this, 8, new Throwable("The params of appID or appSecret is missing !"));
    do
    {
      return;
      paramString = new g(this, 23);
      paramString.a(this.a, this.b);
      try
      {
        paramString.a(this.listener);
        return;
      }
      catch (Throwable paramString)
      {
        Ln.e(paramString);
      }
    }
    while (this.listener == null);
    this.listener.onError(this, 8, paramString);
  }

  public static class ShareParams extends WechatHelper.ShareParams
  {
    public ShareParams()
    {
      this.scene = 1;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.wechat.moments.WechatMoments
 * JD-Core Version:    0.6.2
 */