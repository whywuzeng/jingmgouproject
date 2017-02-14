package cn.sharesdk.sina.weibo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.a.a;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.authorize.b;
import cn.sharesdk.framework.authorize.g;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.Ln;
import com.mob.tools.utils.R;
import com.mob.tools.utils.UIHandler;
import java.io.File;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class i extends cn.sharesdk.framework.e
{
  private static i b;
  private String c;
  private String d;
  private String e;
  private String f;
  private String[] g;
  private a h = a.a();

  private i(Platform paramPlatform)
  {
    super(paramPlatform);
  }

  public static i a(Platform paramPlatform)
  {
    try
    {
      if (b == null)
        b = new i(paramPlatform);
      paramPlatform = b;
      return paramPlatform;
    }
    finally
    {
    }
    throw paramPlatform;
  }

  private HashMap<String, Object> a(String paramString, float paramFloat1, float paramFloat2)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new KVPair("source", this.c));
    localArrayList.add(new KVPair("access_token", this.f));
    localArrayList.add(new KVPair("status", paramString));
    localArrayList.add(new KVPair("long", String.valueOf(paramFloat1)));
    localArrayList.add(new KVPair("lat", String.valueOf(paramFloat2)));
    paramString = this.h.b("https://api.weibo.com/2/statuses/update.json", localArrayList, "/2/statuses/update.json", c());
    if (paramString != null)
      return new Hashon().fromJson(paramString);
    return null;
  }

  private HashMap<String, Object> a(String paramString1, String paramString2, float paramFloat1, float paramFloat2)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new KVPair("source", this.c));
    localArrayList.add(new KVPair("access_token", this.f));
    localArrayList.add(new KVPair("status", paramString1));
    localArrayList.add(new KVPair("long", String.valueOf(paramFloat1)));
    localArrayList.add(new KVPair("lat", String.valueOf(paramFloat2)));
    localArrayList.add(new KVPair("url", paramString2));
    paramString1 = this.h.b("https://api.weibo.com/2/statuses/upload_url_text.json", localArrayList, "/2/statuses/upload_url_text.json", c());
    if (paramString1 != null)
      return new Hashon().fromJson(paramString1);
    return null;
  }

  private HashMap<String, Object> b(String paramString1, String paramString2, float paramFloat1, float paramFloat2)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new KVPair("source", this.c));
    localArrayList.add(new KVPair("access_token", this.f));
    localArrayList.add(new KVPair("status", paramString2));
    localArrayList.add(new KVPair("long", String.valueOf(paramFloat1)));
    localArrayList.add(new KVPair("lat", String.valueOf(paramFloat2)));
    paramString1 = new KVPair("pic", paramString1);
    paramString1 = this.h.a("https://api.weibo.com/2/statuses/upload.json", localArrayList, paramString1, "/2/statuses/upload.json", c());
    if (paramString1 != null)
      return new Hashon().fromJson(paramString1);
    return null;
  }

  public String a(Context paramContext, String paramString)
  {
    paramContext = new ArrayList();
    paramContext.add(new KVPair("client_id", this.c));
    paramContext.add(new KVPair("client_secret", this.d));
    paramContext.add(new KVPair("redirect_uri", this.e));
    paramContext.add(new KVPair("grant_type", "authorization_code"));
    paramContext.add(new KVPair("code", paramString));
    paramContext = this.h.b("https://api.weibo.com/oauth2/access_token", paramContext, "/oauth2/access_token", c());
    ShareSDK.logApiEvent("/oauth2/access_token", c());
    return paramContext;
  }

  public HashMap<String, Object> a(int paramInt1, int paramInt2, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new KVPair("source", this.c));
    int i = 1;
    try
    {
      R.parseLong(paramString);
      if (i != 0)
      {
        localArrayList.add(new KVPair("uid", paramString));
        localArrayList.add(new KVPair("count", String.valueOf(paramInt1)));
        localArrayList.add(new KVPair("page", String.valueOf(paramInt2)));
        paramString = this.h.a("https://api.weibo.com/2/statuses/user_timeline.json", localArrayList, "/2/statuses/user_timeline.json", c());
        if (paramString == null)
          break label156;
        return new Hashon().fromJson(paramString);
      }
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        i = 0;
        continue;
        localArrayList.add(new KVPair("screen_name", paramString));
      }
    }
    label156: return null;
  }

  public HashMap<String, Object> a(String paramString1, String paramString2, String paramString3, float paramFloat1, float paramFloat2)
  {
    if ((TextUtils.isEmpty(paramString1)) && (TextUtils.isEmpty(paramString2)) && (TextUtils.isEmpty(paramString3)))
      throw new Throwable("weibo content can not be null!");
    if (!TextUtils.isEmpty(paramString3))
      return b(paramString3, paramString1, paramFloat1, paramFloat2);
    if (!TextUtils.isEmpty(paramString2))
      return a(paramString1, paramString2, paramFloat1, paramFloat2);
    return a(paramString1, paramFloat1, paramFloat2);
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
    localArrayList.add(new KVPair("source", this.c));
    if (this.f != null)
      localArrayList.add(new KVPair("access_token", this.f));
    if ((paramHashMap1 != null) && (paramHashMap1.size() > 0))
    {
      paramHashMap1 = paramHashMap1.entrySet().iterator();
      for (paramHashMap = null; paramHashMap1.hasNext(); paramHashMap = new KVPair((String)paramHashMap.getKey(), paramHashMap.getValue()))
        paramHashMap = (Map.Entry)paramHashMap1.next();
    }
    while (true)
    {
      try
      {
        if ("GET".equals(paramString2.toUpperCase()))
        {
          paramString1 = new NetworkHelper().httpGet(paramString1, localArrayList, null, null);
          if ((paramString1 == null) || (paramString1.length() <= 0))
            break;
          return new Hashon().fromJson(paramString1);
        }
        if ("POST".equals(paramString2.toUpperCase()))
        {
          paramString1 = new NetworkHelper().httpPost(paramString1, localArrayList, paramHashMap, null, null);
          continue;
        }
      }
      catch (Throwable paramString1)
      {
        Ln.e(paramString1);
        paramString1 = null;
        continue;
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
    a(new j(this, paramAuthorizeListener));
  }

  public void a(String paramString)
  {
    this.e = paramString;
  }

  public void a(String paramString, Platform.ShareParams paramShareParams, PlatformActionListener paramPlatformActionListener)
  {
    Object localObject2 = paramShareParams.getImagePath();
    Object localObject3 = paramShareParams.getImageUrl();
    Object localObject1 = localObject2;
    if (TextUtils.isEmpty((CharSequence)localObject2))
    {
      localObject1 = localObject2;
      if (!TextUtils.isEmpty((CharSequence)localObject3))
      {
        localObject3 = new File(BitmapHelper.downloadBitmap(this.a.getContext(), (String)localObject3));
        localObject1 = localObject2;
        if (((File)localObject3).exists())
          localObject1 = ((File)localObject3).getAbsolutePath();
      }
    }
    localObject3 = new Intent("android.intent.action.SEND");
    ((Intent)localObject3).putExtra("android.intent.extra.TEXT", paramString);
    if (!TextUtils.isEmpty((CharSequence)localObject1))
    {
      paramString = new File((String)localObject1);
      if (paramString.exists())
      {
        if (!((String)localObject1).startsWith("/data/"))
          break label419;
        localObject2 = new File(R.getCachePath(this.a.getContext(), "images"), System.currentTimeMillis() + paramString.getName());
        String str = ((File)localObject2).getAbsolutePath();
        ((File)localObject2).createNewFile();
        if (!R.copyFile((String)localObject1, str))
          break label419;
        paramString = (String)localObject2;
      }
    }
    label396: label419: 
    while (true)
    {
      ((Intent)localObject3).putExtra("android.intent.extra.STREAM", Uri.fromFile(paramString));
      localObject1 = URLConnection.getFileNameMap().getContentTypeFor((String)localObject1);
      if (localObject1 != null)
      {
        paramString = (String)localObject1;
        if (((String)localObject1).length() > 0);
      }
      else
      {
        paramString = "image/*";
      }
      ((Intent)localObject3).setType(paramString);
      if (b())
        ((Intent)localObject3).setPackage("com.sina.weibo");
      while (true)
      {
        ((Intent)localObject3).addFlags(268435456);
        this.a.getContext().startActivity((Intent)localObject3);
        paramString = DeviceHelper.getInstance(this.a.getContext());
        localObject1 = this.a.getContext().getPackageName();
        localObject2 = new HashMap();
        ((HashMap)localObject2).put("ShareParams", paramShareParams);
        if (!TextUtils.isEmpty(paramString.getTopTaskPackageName()))
          break label396;
        if ((paramPlatformActionListener != null) && (paramPlatformActionListener != null))
          paramPlatformActionListener.onComplete(this.a, 9, (HashMap)localObject2);
        return;
        ((Intent)localObject3).setType("text/plain");
        break;
        ((Intent)localObject3).setClassName("com.sina.weibo", "com.sina.weibo.EditActivity");
      }
      UIHandler.sendEmptyMessageDelayed(0, 2000L, new k(this, paramString, (String)localObject1, paramPlatformActionListener, (HashMap)localObject2));
      return;
    }
  }

  public void a(String paramString1, String paramString2)
  {
    this.c = paramString1;
    this.d = paramString2;
  }

  public void a(String[] paramArrayOfString)
  {
    this.g = paramArrayOfString;
  }

  public boolean a()
  {
    boolean bool = false;
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setPackage("com.sina.weibo");
    localIntent.setType("image/*");
    if (this.a.getContext().getPackageManager().resolveActivity(localIntent, 0) != null)
      bool = true;
    return bool;
  }

  public HashMap<String, Object> b(int paramInt1, int paramInt2, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new KVPair("source", this.c));
    if (this.f != null)
      localArrayList.add(new KVPair("access_token", this.f));
    int i = 1;
    try
    {
      R.parseLong(paramString);
      if (i != 0)
      {
        localArrayList.add(new KVPair("uid", paramString));
        localArrayList.add(new KVPair("count", String.valueOf(paramInt1)));
        localArrayList.add(new KVPair("cursor", String.valueOf(paramInt2 * paramInt1)));
        paramString = this.h.a("https://api.weibo.com/2/friendships/friends.json", localArrayList, "/2/friendships/friends.json", c());
        if (paramString == null)
          break label187;
        return new Hashon().fromJson(paramString);
      }
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        i = 0;
        continue;
        localArrayList.add(new KVPair("screen_name", paramString));
      }
    }
    label187: return null;
  }

  public void b(String paramString)
  {
    this.f = paramString;
  }

  // ERROR //
  public boolean b()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: aload_0
    //   3: getfield 32	cn/sharesdk/sina/weibo/i:a	Lcn/sharesdk/framework/Platform;
    //   6: invokevirtual 277	cn/sharesdk/framework/Platform:getContext	()Landroid/content/Context;
    //   9: invokevirtual 442	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   12: ldc_w 376
    //   15: iconst_0
    //   16: invokevirtual 458	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   19: getfield 464	android/content/pm/PackageInfo:versionCode	I
    //   22: istore_1
    //   23: new 313	java/lang/StringBuilder
    //   26: dup
    //   27: invokespecial 314	java/lang/StringBuilder:<init>	()V
    //   30: ldc_w 466
    //   33: invokevirtual 330	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: iload_1
    //   37: invokevirtual 469	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   40: invokevirtual 333	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   43: iconst_0
    //   44: anewarray 471	java/lang/Object
    //   47: invokestatic 475	com/mob/tools/utils/Ln:i	(Ljava/lang/Object;[Ljava/lang/Object;)I
    //   50: pop
    //   51: iload_1
    //   52: sipush 1914
    //   55: if_icmplt +5 -> 60
    //   58: iconst_1
    //   59: istore_2
    //   60: iload_2
    //   61: ireturn
    //   62: astore_3
    //   63: iconst_0
    //   64: istore_1
    //   65: aload_3
    //   66: invokestatic 251	com/mob/tools/utils/Ln:e	(Ljava/lang/Throwable;)I
    //   69: pop
    //   70: goto -19 -> 51
    //   73: astore_3
    //   74: goto -9 -> 65
    //
    // Exception table:
    //   from	to	target	type
    //   2	23	62	java/lang/Throwable
    //   23	51	73	java/lang/Throwable
  }

  public HashMap<String, Object> c(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new KVPair("source", this.c));
    if (this.f != null)
      localArrayList.add(new KVPair("access_token", this.f));
    int i = 1;
    try
    {
      R.parseLong(paramString);
      if (i != 0)
      {
        localArrayList.add(new KVPair("uid", paramString));
        paramString = this.h.a("https://api.weibo.com/2/users/show.json", localArrayList, "/2/users/show.json", c());
        if (paramString == null)
          break label137;
        return new Hashon().fromJson(paramString);
      }
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        i = 0;
        continue;
        localArrayList.add(new KVPair("screen_name", paramString));
      }
    }
    label137: return null;
  }

  public HashMap<String, Object> d(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new KVPair("source", this.c));
    localArrayList.add(new KVPair("access_token", this.f));
    int i = 1;
    try
    {
      R.parseLong(paramString);
      if (i != 0)
      {
        localArrayList.add(new KVPair("uid", paramString));
        paramString = this.h.b("https://api.weibo.com/2/friendships/create.json", localArrayList, "/2/friendships/create.json", c());
        if (paramString == null)
          break label130;
        return new Hashon().fromJson(paramString);
      }
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        i = 0;
        continue;
        localArrayList.add(new KVPair("screen_name", paramString));
      }
    }
    label130: return null;
  }

  public String getAuthorizeUrl()
  {
    Object localObject = new ArrayList();
    ((ArrayList)localObject).add(new KVPair("client_id", this.c));
    ((ArrayList)localObject).add(new KVPair("response_type", "code"));
    ((ArrayList)localObject).add(new KVPair("redirect_uri", this.e));
    if ((this.g != null) && (this.g.length > 0))
      ((ArrayList)localObject).add(new KVPair("scope", TextUtils.join(",", this.g)));
    ((ArrayList)localObject).add(new KVPair("display", "mobile"));
    localObject = "https://api.weibo.com/oauth2/authorize?" + R.encodeUrl((ArrayList)localObject);
    ShareSDK.logApiEvent("/oauth2/authorize", c());
    return localObject;
  }

  public b getAuthorizeWebviewClient(g paramg)
  {
    return new f(paramg);
  }

  public String getRedirectUri()
  {
    if (TextUtils.isEmpty(this.e))
      return "https://api.weibo.com/oauth2/default.html";
    return this.e;
  }

  public cn.sharesdk.framework.authorize.f getSSOProcessor(cn.sharesdk.framework.authorize.e parame)
  {
    parame = new h(parame);
    parame.a(32973);
    parame.a(this.c, this.e, new String[0]);
    return parame;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.sina.weibo.i
 * JD-Core Version:    0.6.2
 */