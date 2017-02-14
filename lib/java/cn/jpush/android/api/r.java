package cn.jpush.android.api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import cn.jpush.android.b.a.a;
import cn.jpush.android.b.a.b;
import cn.jpush.android.b.a.e;
import cn.jpush.android.util.x;

final class r
  implements Runnable
{
  private static final String[] z;

  static
  {
    String[] arrayOfString = new String[5];
    Object localObject1 = "\0215j/1\n\022d%\030\003&j\037 \0134l8\r";
    int i = -1;
    int j = 0;
    Object localObject3 = arrayOfString;
    localObject1 = ((String)localObject1).toCharArray();
    int k = localObject1.length;
    int m;
    int n;
    int i1;
    label42: Object localObject2;
    int i2;
    label91: label113: Object localObject4;
    int i3;
    if (k <= 1)
    {
      m = 0;
      n = j;
      i1 = i;
      j = m;
      while (true)
      {
        localObject2 = localObject1;
        i2 = localObject2[m];
        switch (j % 5)
        {
        default:
          i = 82;
          localObject2[m] = ((char)(i ^ i2));
          j += 1;
          if (k != 0)
            break label113;
          m = k;
        case 0:
        case 1:
        case 2:
        case 3:
        }
      }
      i = k;
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
      k = i;
      m = j;
      if (i > j)
        break label42;
      localObject1 = new String(localObject2).intern();
      switch (i2)
      {
      default:
        localObject4[i3] = localObject1;
        localObject1 = "\0259e9=\025";
        j = 1;
        i = 0;
        break;
      case 0:
        localObject4[i3] = localObject1;
        localObject1 = "";
        j = 2;
        i = 1;
        break;
      case 1:
        localObject4[i3] = localObject1;
        localObject1 = "\n$-";
        j = 3;
        i = 2;
        break;
      case 2:
        localObject4[i3] = localObject1;
        j = 4;
        localObject1 = "7\004Mpj";
        i = 3;
        break;
      case 3:
        localObject4[i3] = localObject1;
        z = arrayOfString;
        return;
        i = 98;
        break label91;
        i = 80;
        break label91;
        i = 11;
        break label91;
        i = 93;
        break label91;
        m = 0;
        i2 = i;
        localObject2 = localObject1;
        i3 = j;
        localObject4 = localObject3;
        i = k;
        j = m;
      }
    }
  }

  r(cn.jpush.android.data.d paramd, Context paramContext)
  {
  }

  @SuppressLint({"NewApi"})
  public final void run()
  {
    if (((this.a instanceof cn.jpush.android.data.s)) && (this.a.f()))
    {
      x.c();
      String str = ((cn.jpush.android.data.s)this.a).a;
      WindowManager localWindowManager = (WindowManager)this.b.getSystemService(z[1]);
      WebView localWebView = new WebView(this.b);
      ImageButton localImageButton = new ImageButton(this.b);
      q.a(this.b, localWindowManager, localWebView, localImageButton);
      localWebView.setHorizontalScrollBarEnabled(false);
      localWebView.setVerticalScrollBarEnabled(false);
      localWebView.setScrollbarFadingEnabled(true);
      localWebView.setScrollBarStyle(33554432);
      Object localObject = localWebView.getSettings();
      ((WebSettings)localObject).setJavaScriptEnabled(true);
      ((WebSettings)localObject).setDefaultTextEncodingName(z[4]);
      ((WebSettings)localObject).setSupportZoom(true);
      ((WebSettings)localObject).setCacheMode(2);
      localObject = this.b;
      cn.jpush.android.data.d locald = this.a;
      q.a = new e((Context)localObject, localWindowManager, localWebView, localImageButton);
      localWebView.removeJavascriptInterface(z[0]);
      localWebView.setWebChromeClient(new a(z[2], b.class));
      localWebView.setWebViewClient(new cn.jpush.android.ui.d(this.a));
      if ((!TextUtils.isEmpty(str)) && (str.startsWith(z[3])))
        localWebView.loadUrl(str);
      localImageButton.setOnClickListener(new s(this, localWindowManager, localWebView, localImageButton));
      return;
    }
    x.f();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.api.r
 * JD-Core Version:    0.6.2
 */