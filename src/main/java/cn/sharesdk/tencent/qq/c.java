package cn.sharesdk.tencent.qq;

import android.os.Bundle;
import android.webkit.WebView;
import cn.sharesdk.framework.authorize.AuthorizeHelper;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.authorize.b;
import cn.sharesdk.framework.authorize.g;
import java.net.URLDecoder;
import java.util.HashMap;

public class c extends b
{
  public c(g paramg)
  {
    super(paramg);
  }

  private void a(HashMap<String, String> paramHashMap)
  {
    String str1 = (String)paramHashMap.get("access_token");
    String str2 = (String)paramHashMap.get("expires_in");
    Object localObject = (String)paramHashMap.get("error");
    paramHashMap = (String)paramHashMap.get("error_description");
    if ((str1 != null) && (str1.trim().length() > 0))
    {
      try
      {
        paramHashMap = e.a(this.a.a().getPlatform()).c(str1);
        if ((paramHashMap == null) || (paramHashMap.size() <= 0))
        {
          if (this.c == null)
            return;
          this.c.onError(new Throwable());
          return;
        }
        if (paramHashMap.containsKey("openid"))
          break label162;
        if (this.c == null)
          return;
        this.c.onError(new Throwable());
        return;
      }
      catch (Throwable paramHashMap)
      {
        if (this.c == null)
          return;
      }
      this.c.onError(paramHashMap);
      return;
      label162: localObject = new Bundle();
      ((Bundle)localObject).putString("access_token", str1);
      ((Bundle)localObject).putString("open_id", String.valueOf(paramHashMap.get("openid")));
      ((Bundle)localObject).putString("expires_in", str2);
      if (this.c != null)
        this.c.onComplete((Bundle)localObject);
    }
    else if ((localObject != null) && (((String)localObject).trim().length() > 0))
    {
      paramHashMap = paramHashMap + " (" + (String)localObject + ")";
      if (this.c != null)
        this.c.onError(new Throwable(paramHashMap));
    }
    else
    {
      this.c.onError(new Throwable());
    }
  }

  protected void a(String paramString)
  {
    Object localObject = paramString;
    if (paramString.startsWith(this.b))
      localObject = paramString.substring(paramString.indexOf('#') + 1);
    localObject = ((String)localObject).split("&");
    HashMap localHashMap = new HashMap();
    int j = localObject.length;
    int i = 0;
    while (i < j)
    {
      paramString = localObject[i].split("=");
      if (paramString.length < 2)
      {
        localHashMap.put(URLDecoder.decode(paramString[0]), "");
        i += 1;
      }
      else
      {
        String str = URLDecoder.decode(paramString[0]);
        if (paramString[1] == null);
        for (paramString = ""; ; paramString = paramString[1])
        {
          localHashMap.put(str, URLDecoder.decode(paramString));
          break;
        }
      }
    }
    a(localHashMap);
  }

  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    if (paramString.startsWith(this.b))
    {
      paramWebView.setVisibility(4);
      paramWebView.stopLoading();
      this.a.finish();
      new d(this, paramString).start();
      return true;
    }
    paramWebView.loadUrl(paramString);
    return true;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.tencent.qq.c
 * JD-Core Version:    0.6.2
 */