package cn.sharesdk.framework;

import android.text.TextUtils;
import cn.sharesdk.framework.statistics.b.f;
import cn.sharesdk.framework.statistics.b.f.a;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Ln;
import java.util.HashMap;

public class a
  implements PlatformActionListener
{
  private PlatformActionListener a;

  private String a(Platform paramPlatform)
  {
    paramPlatform = paramPlatform.getDb();
    try
    {
      paramPlatform = a(paramPlatform, new String[] { "nickname", "icon", "gender", "snsUserUrl", "resume", "secretType", "secret", "birthday", "followerCount", "favouriteCount", "shareCount", "snsregat", "snsUserLevel", "educationJSONArrayStr", "workJSONArrayStr" });
      return paramPlatform;
    }
    catch (Throwable paramPlatform)
    {
      Ln.e(paramPlatform);
    }
    return null;
  }

  private String a(PlatformDb paramPlatformDb, String[] paramArrayOfString)
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    StringBuilder localStringBuilder2 = new StringBuilder();
    int k = paramArrayOfString.length;
    int i = 0;
    int j = 0;
    if (i < k)
    {
      String str = paramArrayOfString[i];
      if (j > 0)
      {
        localStringBuilder2.append('|');
        localStringBuilder1.append('|');
      }
      j += 1;
      str = paramPlatformDb.get(str);
      if (TextUtils.isEmpty(str));
      while (true)
      {
        i += 1;
        break;
        localStringBuilder1.append(str);
        localStringBuilder2.append(Data.urlEncode(str, "utf-8"));
      }
    }
    Ln.e("======UserData: " + localStringBuilder1.toString(), new Object[0]);
    return localStringBuilder2.toString();
  }

  private void a(Platform paramPlatform, int paramInt, HashMap<String, Object> paramHashMap)
  {
    this.a = new b(this, this.a, paramInt, paramHashMap);
    paramPlatform.showUser(null);
  }

  private String b(Platform paramPlatform)
  {
    paramPlatform = paramPlatform.getDb();
    try
    {
      paramPlatform = a(paramPlatform, new String[] { "gender", "birthday", "secretType", "educationJSONArrayStr", "workJSONArrayStr" });
      return paramPlatform;
    }
    catch (Throwable paramPlatform)
    {
      Ln.e(paramPlatform);
    }
    return null;
  }

  private void b(Platform paramPlatform, int paramInt, HashMap<String, Object> paramHashMap)
  {
    if (paramHashMap != null);
    for (Object localObject = (Platform.ShareParams)paramHashMap.remove("ShareParams"); ; localObject = null)
      try
      {
        HashMap localHashMap = (HashMap)paramHashMap.clone();
        if (localObject != null)
        {
          f localf = new f();
          localf.o = ((Platform.ShareParams)localObject).getCustomFlag();
          if ("TencentWeibo".equals(paramPlatform.getName()))
          {
            str = paramPlatform.getDb().get("name");
            localf.b = str;
            localf.a = paramPlatform.getPlatformId();
            localObject = paramPlatform.filterShareContent((Platform.ShareParams)localObject, localHashMap);
            if (localObject != null)
            {
              localf.c = ((f.a)localObject).a;
              localf.d = ((f.a)localObject);
            }
            localf.n = b(paramPlatform);
            cn.sharesdk.framework.statistics.b.a(paramPlatform.getContext()).a(localf);
          }
        }
        else if (this.a == null);
      }
      catch (Throwable localThrowable)
      {
        try
        {
          while (true)
          {
            this.a.onComplete(paramPlatform, paramInt, paramHashMap);
            return;
            localThrowable = localThrowable;
            Ln.e(localThrowable);
            HashMap<String, Object> localHashMap1 = paramHashMap;
          }
          String str = paramPlatform.getDb().getUserId();
        }
        catch (Throwable paramPlatform)
        {
          Ln.w(paramPlatform);
          return;
        }
      }
  }

  PlatformActionListener a()
  {
    return this.a;
  }

  void a(Platform paramPlatform, int paramInt, Object paramObject)
  {
    this.a = new c(this, this.a, paramInt, paramObject);
    paramPlatform.doAuthorize(null);
  }

  void a(PlatformActionListener paramPlatformActionListener)
  {
    this.a = paramPlatformActionListener;
  }

  public void onCancel(Platform paramPlatform, int paramInt)
  {
    if (this.a != null)
      this.a.onCancel(paramPlatform, paramInt);
  }

  public void onComplete(Platform paramPlatform, int paramInt, HashMap<String, Object> paramHashMap)
  {
    if ((paramPlatform instanceof CustomPlatform))
      if (this.a != null)
        this.a.onComplete(paramPlatform, paramInt, paramHashMap);
    do
    {
      return;
      switch (paramInt)
      {
      default:
      case 1:
      case 9:
      }
    }
    while (this.a == null);
    this.a.onComplete(paramPlatform, paramInt, paramHashMap);
    return;
    a(paramPlatform, paramInt, paramHashMap);
    return;
    b(paramPlatform, paramInt, paramHashMap);
  }

  public void onError(Platform paramPlatform, int paramInt, Throwable paramThrowable)
  {
    if (this.a != null)
      this.a.onError(paramPlatform, paramInt, paramThrowable);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.a
 * JD-Core Version:    0.6.2
 */