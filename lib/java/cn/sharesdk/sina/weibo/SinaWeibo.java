package cn.sharesdk.sina.weibo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.statistics.b.f.a;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.Ln;
import com.mob.tools.utils.R;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class SinaWeibo extends Platform
{
  public static final String NAME = SinaWeibo.class.getSimpleName();
  private String a;
  private String b;
  private String c;
  private boolean d;

  public SinaWeibo(Context paramContext)
  {
    super(paramContext);
  }

  private void a(Platform.ShareParams paramShareParams)
  {
    if ((paramShareParams.getImageData() == null) && (TextUtils.isEmpty(paramShareParams.getImagePath())) && (!TextUtils.isEmpty(paramShareParams.getImageUrl())));
    try
    {
      Object localObject = new File(BitmapHelper.downloadBitmap(getContext(), paramShareParams.getImageUrl()));
      if (((File)localObject).exists())
        paramShareParams.setImagePath(((File)localObject).getAbsolutePath());
      localObject = new Intent();
      ((Intent)localObject).putExtra("platformID", 1);
      ((Intent)localObject).putExtra("action", 2);
      a locala = new a(this, this.a, this.c);
      locala.a(this.listener, paramShareParams, this.d);
      locala.show(getContext(), (Intent)localObject);
      return;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        Ln.e(localThrowable);
    }
  }

  private void a(String[] paramArrayOfString)
  {
    a locala = new a(this, this.a, this.c);
    locala.a(new d(this), paramArrayOfString, isSSODisable());
    paramArrayOfString = new Intent();
    paramArrayOfString.putExtra("platformID", 1);
    paramArrayOfString.putExtra("action", 1);
    locala.show(getContext(), paramArrayOfString);
  }

  private void b(Platform.ShareParams paramShareParams)
  {
    Object localObject = paramShareParams.getText();
    if (TextUtils.isEmpty((CharSequence)localObject))
    {
      int i = R.getStringRes(getContext(), "weibo_upload_content");
      if (i > 0)
        localObject = getContext().getString(i);
    }
    while (true)
    {
      i locali = i.a(this);
      localObject = getShortLintk((String)localObject, false);
      String str1 = paramShareParams.getImagePath();
      String str2 = paramShareParams.getImageUrl();
      if ((this.d) && (locali.a()));
      do
      {
        do
        {
          while (true)
          {
            try
            {
              locali.a((String)localObject, paramShareParams, this.listener);
              return;
            }
            catch (Throwable paramShareParams)
            {
              this.listener.onError(this, 9, paramShareParams);
              return;
            }
            try
            {
              float f = paramShareParams.getLatitude();
              localObject = locali.a((String)localObject, str2, str1, paramShareParams.getLongitude(), f);
              if (localObject == null)
              {
                if (this.listener == null)
                  continue;
                this.listener.onError(this, 9, new Throwable());
              }
            }
            catch (Throwable paramShareParams)
            {
              this.listener.onError(this, 9, paramShareParams);
              return;
            }
          }
          if ((!((HashMap)localObject).containsKey("error_code")) || (((Integer)((HashMap)localObject).get("error_code")).intValue() == 0))
            break;
        }
        while (this.listener == null);
        paramShareParams = new Hashon().fromHashMap((HashMap)localObject);
        this.listener.onError(this, 9, new Throwable(paramShareParams));
        return;
        ((HashMap)localObject).put("ShareParams", paramShareParams);
      }
      while (this.listener == null);
      this.listener.onComplete(this, 9, (HashMap)localObject);
      return;
    }
  }

  private void b(String[] paramArrayOfString)
  {
    i locali = i.a(this);
    locali.a(this.a, this.b);
    locali.a(this.c);
    locali.a(paramArrayOfString);
    locali.a(new e(this, locali), isSSODisable());
  }

  protected boolean checkAuthorize(int paramInt, Object paramObject)
  {
    while (true)
    {
      try
      {
        ActivityInfo[] arrayOfActivityInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 1).activities;
        int j = arrayOfActivityInfo.length;
        i = 0;
        if (i < j)
          if ("cn.sharesdk.sina.weibo.SinaActivity".equals(arrayOfActivityInfo[i].name))
          {
            i = 1;
            if (i != 0)
              continue;
            throw new Throwable("cn.sharesdk.sina.weibo.SinaActivity is not registered");
          }
      }
      catch (Throwable localThrowable)
      {
        i = 0;
        i locali = i.a(this);
        if ((i != 0) && (paramInt == 9) && (locali.a()))
        {
          return true;
          i += 1;
          continue;
          Class.forName("com.sina.weibo.sdk.auth.AuthInfo");
          i = 1;
          continue;
        }
        if ((paramInt == 9) && (this.d) && (locali.a()))
          continue;
        if (isAuthValid())
        {
          locali.a(this.a, this.b);
          locali.b(this.db.getToken());
          return true;
        }
        innerAuthorize(paramInt, paramObject);
        return false;
      }
      int i = 0;
    }
  }

  protected void doAuthorize(String[] paramArrayOfString)
  {
    int j = 1;
    while (true)
    {
      try
      {
        ActivityInfo[] arrayOfActivityInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 1).activities;
        int k = arrayOfActivityInfo.length;
        i = 0;
        if (i >= k)
          break label99;
        if ("cn.sharesdk.sina.weibo.SinaActivity".equals(arrayOfActivityInfo[i].name))
        {
          i = j;
          if (i != 0)
            break label86;
          throw new Throwable("cn.sharesdk.sina.weibo.SinaActivity is not registered");
        }
      }
      catch (Throwable localThrowable)
      {
        b(paramArrayOfString);
        return;
      }
      i += 1;
      continue;
      label86: Class.forName("com.sina.weibo.sdk.auth.AuthInfo");
      a(paramArrayOfString);
      return;
      label99: int i = 0;
    }
  }

  protected void doCustomerProtocol(String paramString1, String paramString2, int paramInt, HashMap<String, Object> paramHashMap, HashMap<String, String> paramHashMap1)
  {
    try
    {
      paramString1 = i.a(this).a(paramString1, paramString2, paramHashMap, paramHashMap1);
      if ((paramString1 == null) || (paramString1.size() <= 0))
      {
        if (this.listener == null)
          return;
        this.listener.onError(this, paramInt, new Throwable());
        return;
      }
      if ((paramString1.containsKey("error_code")) && (((Integer)paramString1.get("error_code")).intValue() != 0))
      {
        if (this.listener == null)
          return;
        paramString1 = new Hashon().fromHashMap(paramString1);
        this.listener.onError(this, paramInt, new Throwable(paramString1));
        return;
      }
    }
    catch (Throwable paramString1)
    {
      this.listener.onError(this, paramInt, paramString1);
      return;
    }
    if (this.listener != null)
      this.listener.onComplete(this, paramInt, paramString1);
  }

  protected void doShare(Platform.ShareParams paramShareParams)
  {
    int j = 1;
    while (true)
    {
      try
      {
        ActivityInfo[] arrayOfActivityInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 1).activities;
        int k = arrayOfActivityInfo.length;
        i = 0;
        if (i >= k)
          break label99;
        if ("cn.sharesdk.sina.weibo.SinaActivity".equals(arrayOfActivityInfo[i].name))
        {
          i = j;
          if (i != 0)
            break label86;
          throw new Throwable("cn.sharesdk.sina.weibo.SinaActivity is not registered");
        }
      }
      catch (Throwable localThrowable)
      {
        b(paramShareParams);
        return;
      }
      i += 1;
      continue;
      label86: Class.forName("com.sina.weibo.sdk.api.share.WeiboShareSDK");
      a(paramShareParams);
      return;
      label99: int i = 0;
    }
  }

  protected f.a filterShareContent(Platform.ShareParams paramShareParams, HashMap<String, Object> paramHashMap)
  {
    f.a locala = new f.a();
    locala.b = paramShareParams.getText();
    if (paramHashMap != null)
    {
      locala.a = String.valueOf(paramHashMap.get("id"));
      locala.d.add(String.valueOf(paramHashMap.get("original_pic")));
      locala.g = paramHashMap;
    }
    return locala;
  }

  protected void follow(String paramString)
  {
    i locali = i.a(this);
    try
    {
      paramString = locali.d(paramString);
      if (paramString == null)
      {
        if (this.listener == null)
          return;
        this.listener.onError(this, 6, new Throwable());
        return;
      }
      if ((paramString.containsKey("error_code")) && (((Integer)paramString.get("error_code")).intValue() != 0))
      {
        if (this.listener == null)
          return;
        paramString = new Hashon().fromHashMap(paramString);
        this.listener.onError(this, 6, new Throwable(paramString));
        return;
      }
    }
    catch (Throwable paramString)
    {
      this.listener.onError(this, 6, paramString);
      return;
    }
    if (this.listener != null)
      this.listener.onComplete(this, 6, paramString);
  }

  protected void getFriendList(int paramInt1, int paramInt2, String paramString)
  {
    Object localObject = paramString;
    if (TextUtils.isEmpty(paramString))
      localObject = this.db.getUserId();
    paramString = (String)localObject;
    if (TextUtils.isEmpty((CharSequence)localObject))
      paramString = this.db.get("nickname");
    if (TextUtils.isEmpty(paramString))
      if (this.listener != null)
        this.listener.onError(this, 2, new RuntimeException("Both weibo id and screen_name are null"));
    do
    {
      do
      {
        while (true)
        {
          return;
          localObject = i.a(this);
          try
          {
            paramString = ((i)localObject).b(paramInt1, paramInt2, paramString);
            if (paramString == null)
            {
              if (this.listener == null)
                continue;
              this.listener.onError(this, 2, new Throwable());
            }
          }
          catch (Throwable paramString)
          {
            this.listener.onError(this, 2, paramString);
            return;
          }
        }
        if ((!paramString.containsKey("error_code")) || (((Integer)paramString.get("error_code")).intValue() == 0))
          break;
      }
      while (this.listener == null);
      paramString = new Hashon().fromHashMap(paramString);
      this.listener.onError(this, 2, new Throwable(paramString));
      return;
    }
    while (this.listener == null);
    this.listener.onComplete(this, 2, paramString);
  }

  public String getName()
  {
    return NAME;
  }

  protected int getPlatformId()
  {
    return 1;
  }

  public int getVersion()
  {
    return 1;
  }

  protected void initDevInfo(String paramString)
  {
    this.a = getDevinfo("AppKey");
    this.b = getDevinfo("AppSecret");
    this.c = getDevinfo("RedirectUrl");
    this.d = "true".equals(getDevinfo("ShareByAppClient"));
  }

  public boolean isClientValid()
  {
    return i.a(this).a();
  }

  protected void setNetworkDevinfo()
  {
    this.a = getNetworkDevinfo("app_key", "AppKey");
    this.b = getNetworkDevinfo("app_secret", "AppSecret");
    this.c = getNetworkDevinfo("redirect_uri", "RedirectUrl");
  }

  protected void timeline(int paramInt1, int paramInt2, String paramString)
  {
    Object localObject = paramString;
    if (TextUtils.isEmpty(paramString))
      localObject = this.db.getUserId();
    paramString = (String)localObject;
    if (TextUtils.isEmpty((CharSequence)localObject))
      paramString = this.db.get("nickname");
    if (TextUtils.isEmpty(paramString))
      if (this.listener != null)
        this.listener.onError(this, 7, new RuntimeException("Both weibo id and screen_name are null"));
    do
    {
      do
      {
        while (true)
        {
          return;
          localObject = i.a(this);
          try
          {
            paramString = ((i)localObject).a(paramInt1, paramInt2, paramString);
            if (paramString == null)
            {
              if (this.listener == null)
                continue;
              this.listener.onError(this, 7, new Throwable());
            }
          }
          catch (Throwable paramString)
          {
            this.listener.onError(this, 7, paramString);
            return;
          }
        }
        if ((!paramString.containsKey("error_code")) || (((Integer)paramString.get("error_code")).intValue() == 0))
          break;
      }
      while (this.listener == null);
      paramString = new Hashon().fromHashMap(paramString);
      this.listener.onError(this, 7, new Throwable(paramString));
      return;
    }
    while (this.listener == null);
    this.listener.onComplete(this, 7, paramString);
  }

  protected void userInfor(String paramString)
  {
    int j = 1;
    int i = 0;
    String str = paramString;
    if (TextUtils.isEmpty(paramString))
    {
      str = this.db.getUserId();
      i = 1;
    }
    if (TextUtils.isEmpty(str))
    {
      str = this.db.get("nickname");
      i = j;
    }
    while (true)
    {
      if (TextUtils.isEmpty(str))
        if (this.listener != null)
          this.listener.onError(this, 8, new RuntimeException("Both weibo id and screen_name are null"));
      do
      {
        while (true)
        {
          return;
          paramString = i.a(this);
          try
          {
            paramString = paramString.c(str);
            if (paramString == null)
            {
              if (this.listener == null)
                continue;
              this.listener.onError(this, 8, new Throwable());
            }
          }
          catch (Throwable paramString)
          {
            this.listener.onError(this, 8, paramString);
            return;
          }
        }
        if ((!paramString.containsKey("error_code")) || (((Integer)paramString.get("error_code")).intValue() == 0))
          break;
      }
      while (this.listener == null);
      paramString = new Hashon().fromHashMap(paramString);
      this.listener.onError(this, 8, new Throwable(paramString));
      return;
      if (i != 0)
      {
        this.db.putUserId(String.valueOf(paramString.get("id")));
        this.db.put("nickname", String.valueOf(paramString.get("screen_name")));
        this.db.put("icon", String.valueOf(paramString.get("avatar_hd")));
        if (!String.valueOf(paramString.get("verified")).equals("true"))
          break label526;
        this.db.put("secretType", "1");
        label300: this.db.put("secret", String.valueOf(paramString.get("verified_reason")));
        str = String.valueOf(paramString.get("gender"));
        if (!str.equals("m"))
          break label542;
        this.db.put("gender", "0");
      }
      while (true)
      {
        this.db.put("snsUserUrl", "http://weibo.com/" + String.valueOf(paramString.get("profile_url")));
        this.db.put("resume", String.valueOf(paramString.get("description")));
        this.db.put("followerCount", String.valueOf(paramString.get("followers_count")));
        this.db.put("favouriteCount", String.valueOf(paramString.get("friends_count")));
        this.db.put("shareCount", String.valueOf(paramString.get("statuses_count")));
        long l = R.dateToLong(String.valueOf(paramString.get("created_at")));
        this.db.put("snsregat", String.valueOf(l));
        if (this.listener == null)
          break;
        this.listener.onComplete(this, 8, paramString);
        return;
        label526: this.db.put("secretType", "0");
        break label300;
        label542: if (str.equals("f"))
          this.db.put("gender", "1");
        else
          this.db.put("gender", "2");
      }
    }
  }

  public static class ShareParams extends Platform.ShareParams
  {

    @Deprecated
    public String imageUrl;

    @Deprecated
    public float latitude;

    @Deprecated
    public float longitude;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.sina.weibo.SinaWeibo
 * JD-Core Version:    0.6.2
 */