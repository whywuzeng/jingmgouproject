package cn.jpush.android.b.a;

import android.webkit.WebView;
import cn.jpush.android.api.q;
import cn.jpush.android.ui.a;
import cn.jpush.android.util.x;

public class b
{
  private static final String TAG;
  private static final String z;

  static
  {
    Object localObject1 = "";
    int k = -1;
    localObject1 = ((String)localObject1).toCharArray();
    int j = localObject1.length;
    int i;
    int n;
    label27: int m;
    Object localObject2;
    int i1;
    if (j <= 1)
    {
      i = 0;
      n = k;
      m = i;
      for (k = i; ; k = j)
      {
        localObject2 = localObject1;
        i1 = localObject2[k];
        switch (m % 5)
        {
        default:
          i = 9;
          label79: localObject2[k] = ((char)(i ^ i1));
          m += 1;
          if (j != 0)
            break label101;
        case 0:
        case 1:
        case 2:
        case 3:
        }
      }
      label101: k = j;
      localObject2 = localObject1;
      i1 = n;
    }
    while (true)
    {
      n = i1;
      localObject1 = localObject2;
      j = k;
      i = m;
      if (k > m)
        break label27;
      localObject1 = new String(localObject2).intern();
      switch (i1)
      {
      default:
        z = (String)localObject1;
        localObject1 = "\016\002\033%C5>\013>y#";
        k = 0;
        break;
      case 0:
        TAG = (String)localObject1;
        return;
        i = 70;
        break label79;
        i = 109;
        break label79;
        i = 104;
        break label79;
        i = 81;
        break label79;
        m = 0;
        i1 = k;
        localObject2 = localObject1;
        k = j;
      }
    }
  }

  public static void click(WebView paramWebView, String paramString1, String paramString2, String paramString3)
  {
    a.b.b(paramString1, paramString2, paramString3);
  }

  public static void close(WebView paramWebView)
  {
    a.b.a();
  }

  public static void createShortcut(WebView paramWebView, String paramString1, String paramString2, String paramString3)
  {
    a.b.a(paramString1, paramString2, paramString3);
  }

  public static void download(WebView paramWebView, String paramString)
  {
    a.b.c(paramString);
  }

  public static void download(WebView paramWebView, String paramString1, String paramString2)
  {
    a.b.c(paramString1, paramString2);
  }

  public static void download(WebView paramWebView, String paramString1, String paramString2, String paramString3)
  {
    paramWebView = a.b;
    new StringBuilder(z).append(paramString3).toString();
    x.b();
    paramWebView.c(paramString1, paramString2);
  }

  public static void executeMsgMessage(WebView paramWebView, String paramString)
  {
    a.b.e(paramString);
  }

  public static void showToast(WebView paramWebView, String paramString)
  {
    a.b.d(paramString);
  }

  public static void startActivityByIntent(WebView paramWebView, String paramString1, String paramString2)
  {
    a.b.b(paramString1, paramString2);
  }

  public static void startActivityByName(WebView paramWebView, String paramString1, String paramString2)
  {
    a.b.a(paramString1, paramString2);
  }

  public static void startActivityByNameWithSystemAlert(WebView paramWebView, String paramString1, String paramString2)
  {
    q.a.a(paramString1, paramString2);
  }

  public static void startMainActivity(WebView paramWebView, String paramString)
  {
    a.b.b(paramString);
  }

  public static void triggerNativeAction(WebView paramWebView, String paramString)
  {
    a.b.a(paramString);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.b.a.b
 * JD-Core Version:    0.6.2
 */