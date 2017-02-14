package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import com.mob.tools.utils.Hashon;
import java.util.HashMap;

public class k
{
  private Platform a;
  private Platform.ShareParams b;
  private PlatformActionListener c;
  private AuthorizeListener d;
  private g e;

  public k(Platform paramPlatform)
  {
    this.a = paramPlatform;
  }

  public Platform.ShareParams a()
  {
    return this.b;
  }

  public void a(Platform.ShareParams paramShareParams, PlatformActionListener paramPlatformActionListener)
  {
    this.b = paramShareParams;
    this.c = paramPlatformActionListener;
  }

  public void a(AuthorizeListener paramAuthorizeListener)
  {
    this.d = paramAuthorizeListener;
  }

  public void a(WechatResp paramWechatResp)
  {
    Object localObject;
    switch (paramWechatResp.f)
    {
    case -1:
    default:
      localObject = new HashMap();
      ((HashMap)localObject).put("req", paramWechatResp.getClass().getSimpleName());
      ((HashMap)localObject).put("errCode", Integer.valueOf(paramWechatResp.f));
      ((HashMap)localObject).put("errStr", paramWechatResp.g);
      ((HashMap)localObject).put("transaction", paramWechatResp.h);
      new Throwable(new Hashon().fromHashMap((HashMap)localObject)).printStackTrace();
    case 0:
    case -2:
    case -3:
    case -4:
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  return;
                  switch (paramWechatResp.a())
                  {
                  default:
                    return;
                  case 1:
                  case 2:
                  }
                }
                while (this.d == null);
                localObject = new Bundle();
                paramWechatResp.b((Bundle)localObject);
                this.e.a((Bundle)localObject, this.d);
                return;
              }
              while (this.c == null);
              paramWechatResp = new HashMap();
              paramWechatResp.put("ShareParams", this.b);
              this.c.onComplete(this.a, 9, paramWechatResp);
              return;
              switch (paramWechatResp.a())
              {
              default:
                return;
              case 1:
              case 2:
              }
            }
            while (this.d == null);
            this.d.onCancel();
            return;
          }
          while (this.c == null);
          this.c.onCancel(this.a, 9);
          return;
          localObject = new HashMap();
          ((HashMap)localObject).put("errCode", Integer.valueOf(paramWechatResp.f));
          ((HashMap)localObject).put("errStr", paramWechatResp.g);
          ((HashMap)localObject).put("transaction", paramWechatResp.h);
          localObject = new Throwable(new Hashon().fromHashMap((HashMap)localObject));
          switch (paramWechatResp.a())
          {
          default:
            return;
          case 1:
          case 2:
          }
        }
        while (this.d == null);
        this.d.onError((Throwable)localObject);
        return;
      }
      while (this.c == null);
      this.c.onError(this.a, 9, (Throwable)localObject);
      return;
      localObject = new HashMap();
      ((HashMap)localObject).put("errCode", Integer.valueOf(paramWechatResp.f));
      ((HashMap)localObject).put("errStr", paramWechatResp.g);
      ((HashMap)localObject).put("transaction", paramWechatResp.h);
      localObject = new Throwable(new Hashon().fromHashMap((HashMap)localObject));
      switch (paramWechatResp.a())
      {
      default:
        return;
      case 1:
      }
    }
    while (this.d == null);
    this.d.onError((Throwable)localObject);
  }

  public void a(g paramg)
  {
    this.e = paramg;
  }

  public Platform b()
  {
    return this.a;
  }

  public PlatformActionListener c()
  {
    return this.c;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.wechat.utils.k
 * JD-Core Version:    0.6.2
 */