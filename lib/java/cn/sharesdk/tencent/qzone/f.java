package cn.sharesdk.tencent.qzone;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.a.a;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.authorize.b;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.Ln;
import com.mob.tools.utils.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class f extends cn.sharesdk.framework.e
{
  private static final String[] b = { "get_user_info", "get_simple_userinfo", "get_user_profile", "get_app_friends", "add_share", "list_album", "upload_pic", "add_album", "set_user_face", "get_vip_info", "get_vip_rich_info", "get_intimate_friends_weibo", "match_nick_tips_weibo", "add_t", "add_pic_t" };
  private static f c;
  private String d;
  private String e;
  private String f;
  private a g = a.a();
  private String[] h;

  private f(Platform paramPlatform)
  {
    super(paramPlatform);
  }

  public static f a(Platform paramPlatform)
  {
    if (c == null)
      c = new f(paramPlatform);
    return c;
  }

  private String b()
  {
    int i = 0;
    if (this.h == null);
    StringBuilder localStringBuilder;
    for (String[] arrayOfString = b; ; arrayOfString = this.h)
    {
      localStringBuilder = new StringBuilder();
      int k = arrayOfString.length;
      int j = 0;
      while (i < k)
      {
        String str = arrayOfString[i];
        if (j > 0)
          localStringBuilder.append(',');
        localStringBuilder.append(str);
        j += 1;
        i += 1;
      }
    }
    return localStringBuilder.toString();
  }

  public HashMap<String, Object> a(String paramString1, String paramString2)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new KVPair("access_token", this.f));
    localArrayList.add(new KVPair("oauth_consumer_key", this.d));
    localArrayList.add(new KVPair("openid", this.e));
    localArrayList.add(new KVPair("format", "json"));
    if (!TextUtils.isEmpty(paramString2))
    {
      String str = paramString2;
      if (paramString2.length() > 200)
        str = paramString2.substring(0, 199) + "…";
      localArrayList.add(new KVPair("photodesc", str));
    }
    localArrayList.add(new KVPair("mobile", "1"));
    paramString1 = new KVPair("picture", paramString1);
    paramString2 = new ArrayList();
    paramString2.add(new KVPair("User-Agent", System.getProperties().getProperty("http.agent") + " ArzenAndroidSDK"));
    paramString1 = this.g.a("https://graph.qq.com/photo/upload_pic", localArrayList, paramString1, paramString2, "/photo/upload_pic", c());
    if ((paramString1 != null) && (paramString1.length() > 0))
      return new Hashon().fromJson(paramString1);
    return null;
  }

  public HashMap<String, Object> a(String paramString1, String paramString2, HashMap<String, Object> paramHashMap, HashMap<String, String> paramHashMap1)
  {
    if (paramString2 == null)
      return null;
    ArrayList localArrayList = new ArrayList();
    if ((paramHashMap != null) && (paramHashMap.size() > 0))
    {
      paramHashMap = paramHashMap.entrySet().iterator();
      while (paramHashMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramHashMap.next();
        localArrayList.add(new KVPair((String)localEntry.getKey(), String.valueOf(localEntry.getValue())));
      }
    }
    localArrayList.add(new KVPair("access_token", this.f));
    localArrayList.add(new KVPair("oauth_consumer_key", this.d));
    localArrayList.add(new KVPair("openid", this.e));
    localArrayList.add(new KVPair("format", "json"));
    if ((paramHashMap1 != null) && (paramHashMap1.size() > 0))
    {
      paramHashMap1 = paramHashMap1.entrySet().iterator();
      for (paramHashMap = null; paramHashMap1.hasNext(); paramHashMap = new KVPair((String)paramHashMap.getKey(), paramHashMap.getValue()))
        paramHashMap = (Map.Entry)paramHashMap1.next();
    }
    while (true)
    {
      while (true)
      {
        paramHashMap1 = new ArrayList();
        paramHashMap1.add(new KVPair("User-Agent", System.getProperties().getProperty("http.agent") + " ArzenAndroidSDK"));
        try
        {
          if ("GET".equals(paramString2.toUpperCase()));
          for (paramString1 = new NetworkHelper().httpGet(paramString1, localArrayList, paramHashMap1, null); (paramString1 != null) && (paramString1.length() > 0); paramString1 = new NetworkHelper().httpPost(paramString1, localArrayList, paramHashMap, paramHashMap1, null))
          {
            return new Hashon().fromJson(paramString1);
            if (!"POST".equals(paramString2.toUpperCase()))
              break label381;
          }
        }
        catch (Throwable paramString1)
        {
          while (true)
          {
            Ln.e(paramString1);
            label381: paramString1 = null;
          }
        }
      }
      paramHashMap = null;
    }
  }

  public void a(AuthorizeListener paramAuthorizeListener, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      b(paramAuthorizeListener);
      return;
    }
    a(new g(this, paramAuthorizeListener));
  }

  public void a(String paramString)
  {
    this.d = paramString;
  }

  public void a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, PlatformActionListener paramPlatformActionListener)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("mqqapi://share/to_qzone?");
    localStringBuilder.append("src_type=app&");
    localStringBuilder.append("version=1&");
    localStringBuilder.append("file_type=news&");
    if (!TextUtils.isEmpty(paramString4))
      localStringBuilder.append("image_url=").append(Base64.encodeToString(paramString4.getBytes("utf-8"), 2)).append("&");
    localStringBuilder.append("title=").append(Base64.encodeToString(paramString1.getBytes("utf-8"), 2)).append("&");
    localStringBuilder.append("description=").append(Base64.encodeToString(paramString3.getBytes("utf-8"), 2)).append("&");
    localStringBuilder.append("app_name=").append(Base64.encodeToString(paramString5.getBytes("utf-8"), 2)).append("&");
    localStringBuilder.append("open_id=&");
    localStringBuilder.append("share_id=").append(this.d).append("&");
    localStringBuilder.append("url=").append(Base64.encodeToString(paramString2.getBytes("utf-8"), 2)).append("&");
    localStringBuilder.append("req_type=MQ==&");
    if (a());
    for (Object localObject = "1"; ; localObject = "0")
    {
      localStringBuilder.append("cflag=").append(Base64.encodeToString(((String)localObject).getBytes("utf-8"), 2));
      localObject = new Intent("android.intent.action.VIEW");
      ((Intent)localObject).setData(Uri.parse(localStringBuilder.toString()));
      if (this.a.getContext().getPackageManager().resolveActivity((Intent)localObject, 1) != null)
        break;
      b(paramString1, paramString2, paramString3, paramString4, paramString5, paramPlatformActionListener);
      return;
    }
    paramString1 = new i();
    paramString1.a(localStringBuilder.toString(), true);
    paramString1.a(paramPlatformActionListener);
    paramString1.a(this.d);
    paramString1.show(this.a.getContext(), null);
  }

  public void a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, boolean paramBoolean, PlatformActionListener paramPlatformActionListener)
  {
    if (TextUtils.isEmpty(paramString2))
      throw new Throwable("titleUrl is needed");
    if (TextUtils.isEmpty(paramString3))
      throw new Throwable("text is needed");
    String str1 = paramString1;
    if (paramString1.length() > 200)
      str1 = paramString1.substring(0, 200);
    if (paramString3.length() > 600);
    for (String str2 = paramString3.substring(0, 600); ; str2 = paramString3)
    {
      paramString1 = paramString5;
      if (TextUtils.isEmpty(paramString5))
        paramString1 = DeviceHelper.getInstance(this.a.getContext()).getAppName();
      if (paramString1.length() > 20)
        paramString1 = paramString1.substring(0, 20) + "...";
      while (true)
      {
        if (TextUtils.isEmpty(str1))
        {
          int i = R.getStringRes(this.a.getContext(), "share_to_qzone_default");
          if (i > 0)
            paramString3 = this.a.getContext().getString(i, new Object[] { paramString1 });
        }
        while (true)
          if (paramBoolean)
          {
            a(paramString3, paramString2, str2, paramString4, paramString1, paramPlatformActionListener);
            return;
            paramString3 = paramString1;
          }
          else
          {
            b(paramString3, paramString2, str2, paramString4, paramString1, paramPlatformActionListener);
            return;
            paramString3 = str1;
          }
      }
    }
  }

  public void a(String[] paramArrayOfString)
  {
    this.h = paramArrayOfString;
  }

  public boolean a()
  {
    boolean bool2 = false;
    try
    {
      localObject = this.a.getContext().getPackageManager().getPackageInfo("com.tencent.mobileqq", 0).versionName;
      localObject = ((String)localObject).split("\\.");
      arrayOfInt = new int[localObject.length];
      i = 0;
      if (i >= arrayOfInt.length);
    }
    catch (Throwable localThrowable1)
    {
      int[] arrayOfInt;
      try
      {
        while (true)
        {
          Object localObject;
          arrayOfInt[i] = R.parseInt(localObject[i]);
          i += 1;
        }
        localThrowable1 = localThrowable1;
        Ln.w(localThrowable1);
        String str = "0";
      }
      catch (Throwable localThrowable2)
      {
        while (true)
        {
          int i;
          Ln.e(localThrowable2);
          arrayOfInt[i] = 0;
        }
      }
      boolean bool1 = bool2;
      if (arrayOfInt.length > 1)
      {
        bool1 = bool2;
        if (arrayOfInt[1] >= 5)
          bool1 = true;
      }
      return bool1;
    }
  }

  public HashMap<String, Object> b(String paramString1, String paramString2)
  {
    int i;
    String str1;
    label18: String str2;
    ArrayList localArrayList;
    if (!TextUtils.isEmpty(paramString1))
    {
      i = 1;
      if (i == 0)
        break label229;
      str1 = "/t/add_pic_t";
      str2 = "https://graph.qq.com" + str1;
      localArrayList = new ArrayList();
      localArrayList.add(new KVPair("oauth_consumer_key", this.d));
      localArrayList.add(new KVPair("access_token", this.f));
      localArrayList.add(new KVPair("openid", this.e));
      localArrayList.add(new KVPair("format", "json"));
      localArrayList.add(new KVPair("content", paramString2));
      if (i == 0)
        break label237;
      paramString1 = new KVPair("pic", paramString1);
      paramString1 = this.g.a(str2, localArrayList, paramString1, str1, c());
    }
    while (true)
      if ((paramString1 != null) && (paramString1.length() > 0))
      {
        paramString2 = new Hashon().fromJson(paramString1);
        if (((Integer)paramString2.get("ret")).intValue() != 0)
        {
          throw new Throwable(paramString1);
          i = 0;
          break;
          label229: str1 = "/t/add_t";
          break label18;
          label237: paramString1 = this.g.b(str2, localArrayList, str1, c());
          continue;
        }
        return paramString2;
      }
    return null;
  }

  public void b(String paramString)
  {
    this.e = paramString;
  }

  public void b(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, PlatformActionListener paramPlatformActionListener)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("http://openmobile.qq.com/api/check2?");
    localStringBuilder.append("page=qzshare.html&");
    localStringBuilder.append("loginpage=loginindex.html&");
    localStringBuilder.append("logintype=qzone&");
    localStringBuilder.append("action=shareToQQ&");
    localStringBuilder.append("sdkv=2.6&");
    localStringBuilder.append("sdkp=a&");
    Object localObject = DeviceHelper.getInstance(this.a.getContext());
    localStringBuilder.append("status_os=").append(Data.urlEncode(((DeviceHelper)localObject).getOSVersionName(), "utf-8")).append("&");
    localStringBuilder.append("status_machine=").append(Data.urlEncode(((DeviceHelper)localObject).getModel(), "utf-8")).append("&");
    localStringBuilder.append("status_version=").append(Data.urlEncode(((DeviceHelper)localObject).getOSVersion(), "utf-8")).append("&");
    localStringBuilder.append("appId=").append(this.d).append("&");
    localObject = ((DeviceHelper)localObject).getAppName();
    if (!TextUtils.isEmpty((CharSequence)localObject))
      localStringBuilder.append("appName=").append(Data.urlEncode((String)localObject, "utf-8")).append("&");
    while (true)
    {
      localObject = paramString1;
      if (paramString1.length() > 40)
        localObject = paramString1.substring(40) + "...";
      localStringBuilder.append("title=").append(Data.urlEncode((String)localObject, "utf-8")).append("&");
      if (((String)localObject).length() > 80)
        new StringBuilder().append(((String)localObject).substring(80)).append("...").toString();
      localStringBuilder.append("summary=").append(Data.urlEncode(paramString3, "utf-8")).append("&");
      localStringBuilder.append("targeturl=").append(Data.urlEncode(paramString2, "utf-8")).append("&");
      if (!TextUtils.isEmpty(paramString4))
        localStringBuilder.append("imageUrl=").append(Data.urlEncode(paramString4, "utf-8")).append("&");
      localStringBuilder.append("site=").append(Data.urlEncode(paramString5, "utf-8")).append("&");
      localStringBuilder.append("type=1");
      paramString1 = new i();
      paramString1.a(localStringBuilder.toString(), false);
      paramString1.a(paramPlatformActionListener);
      paramString1.a(this.d);
      paramString1.show(this.a.getContext(), null);
      return;
      localStringBuilder.append("appName=").append(Data.urlEncode(paramString5, "utf-8")).append("&");
    }
  }

  public void c(String paramString)
  {
    this.f = paramString;
  }

  public HashMap<String, Object> d(String paramString)
  {
    Object localObject1 = null;
    paramString = new ArrayList();
    paramString.add(new KVPair("access_token", this.f));
    paramString.add(new KVPair("oauth_consumer_key", this.d));
    paramString.add(new KVPair("openid", this.e));
    Object localObject2 = new ArrayList();
    ((ArrayList)localObject2).add(new KVPair("User-Agent", System.getProperties().getProperty("http.agent") + " ArzenAndroidSDK"));
    localObject2 = this.g.a("https://graph.qq.com/user/get_simple_userinfo", paramString, (ArrayList)localObject2, null, "/user/get_simple_userinfo", c());
    paramString = localObject1;
    if (localObject2 != null)
    {
      paramString = localObject1;
      if (((String)localObject2).length() > 0)
        paramString = new Hashon().fromJson((String)localObject2);
    }
    return paramString;
  }

  public HashMap<String, Object> e(String paramString)
  {
    Object localObject2 = null;
    Object localObject1 = new ArrayList();
    ((ArrayList)localObject1).add(new KVPair("access_token", paramString));
    paramString = new ArrayList();
    paramString.add(new KVPair("User-Agent", System.getProperties().getProperty("http.agent") + " ArzenAndroidSDK"));
    paramString = this.g.a("https://graph.qq.com/oauth2.0/me", (ArrayList)localObject1, paramString, null, "/oauth2.0/me", c());
    localObject1 = paramString;
    if (paramString.startsWith("callback"))
    {
      for (localObject1 = paramString; ; localObject1 = ((String)localObject1).substring(1))
      {
        paramString = (String)localObject1;
        if (((String)localObject1).startsWith("{"))
          break;
        paramString = (String)localObject1;
        if (((String)localObject1).length() <= 0)
          break;
      }
      while (true)
      {
        localObject1 = paramString;
        if (paramString.endsWith("}"))
          break;
        localObject1 = paramString;
        if (paramString.length() <= 0)
          break;
        paramString = paramString.substring(0, paramString.length() - 1);
      }
    }
    paramString = localObject2;
    if (localObject1 != null)
    {
      paramString = localObject2;
      if (((String)localObject1).length() > 0)
        paramString = new Hashon().fromJson((String)localObject1);
    }
    return paramString;
  }

  public String getAuthorizeUrl()
  {
    ShareSDK.logApiEvent("/oauth2.0/authorize", c());
    String str3 = b();
    String str1 = getRedirectUri();
    try
    {
      str1 = Data.urlEncode(str1, "utf-8");
      return "https://graph.qq.com/oauth2.0/m_authorize?response_type=token&client_id=" + this.d + "&" + "redirect_uri=" + str1 + "&" + "display=mobile&" + "scope=" + str3;
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        Ln.e(localThrowable);
        String str2 = getRedirectUri();
      }
    }
  }

  public b getAuthorizeWebviewClient(cn.sharesdk.framework.authorize.g paramg)
  {
    return new d(paramg);
  }

  public String getRedirectUri()
  {
    return "auth://tauth.qq.com/";
  }

  public cn.sharesdk.framework.authorize.f getSSOProcessor(cn.sharesdk.framework.authorize.e parame)
  {
    parame = new h(parame);
    parame.a(5656);
    parame.a(this.d, b());
    return parame;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.tencent.qzone.f
 * JD-Core Version:    0.6.2
 */