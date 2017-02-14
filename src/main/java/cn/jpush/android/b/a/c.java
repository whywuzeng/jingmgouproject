package cn.jpush.android.b.a;

import android.util.Log;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class c extends WebChromeClient
{
  private static final String[] z;
  private final String a = z[0];
  private d b;
  private boolean c;

  static
  {
    String[] arrayOfString = new String[2];
    Object localObject2 = "\016@D/\0333KJ\t\0205AC/;+GK$\f";
    int k = -1;
    int m = 0;
    Object localObject1 = arrayOfString;
    localObject2 = ((String)localObject2).toCharArray();
    int j = localObject2.length;
    int i;
    Object localObject3;
    int n;
    int i1;
    label50: int i2;
    label99: label121: Object localObject4;
    int i3;
    if (j <= 1)
    {
      i = 0;
      localObject3 = localObject1;
      n = m;
      localObject1 = localObject2;
      i1 = k;
      m = i;
      for (k = i; ; k = j)
      {
        localObject2 = localObject1;
        i2 = localObject2[k];
        switch (m % 5)
        {
        default:
          i = 120;
          localObject2[k] = ((char)(i ^ i2));
          m += 1;
          if (j != 0)
            break label121;
        case 0:
        case 1:
        case 2:
        case 3:
        }
      }
      k = j;
      localObject4 = localObject3;
      i3 = n;
      localObject2 = localObject1;
      i2 = i1;
    }
    while (true)
    {
      i1 = i2;
      localObject1 = localObject2;
      n = i3;
      localObject3 = localObject4;
      j = k;
      i = m;
      if (k > m)
        break label50;
      localObject1 = new String((char[])localObject2).intern();
      switch (i2)
      {
      default:
        localObject4[i3] = localObject1;
        localObject2 = "gG@ \035$Z\016 \013gG@>\0355HO)\035gMA'\b+KZ/\024>\016A$X7\\A-\n\"]]j";
        m = 1;
        k = 0;
        break;
      case 0:
        localObject4[i3] = localObject1;
        z = arrayOfString;
        return;
        i = 71;
        break label99;
        i = 46;
        break label99;
        i = 46;
        break label99;
        i = 74;
        break label99;
        i = 0;
        i2 = k;
        i3 = m;
        localObject4 = localObject1;
        k = j;
        m = i;
      }
    }
  }

  public c(String paramString, Class paramClass)
  {
    this.b = new d(paramString, paramClass);
  }

  public boolean onJsAlert(WebView paramWebView, String paramString1, String paramString2, JsResult paramJsResult)
  {
    paramJsResult.confirm();
    return true;
  }

  public boolean onJsPrompt(WebView paramWebView, String paramString1, String paramString2, String paramString3, JsPromptResult paramJsPromptResult)
  {
    paramJsPromptResult.confirm(this.b.a(paramWebView, paramString2));
    return true;
  }

  public void onProgressChanged(WebView paramWebView, int paramInt)
  {
    if (paramInt <= 25)
      this.c = false;
    while (true)
    {
      super.onProgressChanged(paramWebView, paramInt);
      return;
      if (!this.c)
      {
        paramWebView.loadUrl(this.b.a());
        this.c = true;
        Log.d(z[0], z[1] + paramInt);
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.b.a.c
 * JD-Core Version:    0.6.2
 */