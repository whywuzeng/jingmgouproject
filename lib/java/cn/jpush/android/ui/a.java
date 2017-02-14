package cn.jpush.android.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View.OnTouchListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import cn.jpush.android.b.a.f;
import cn.jpush.android.util.x;

public final class a extends RelativeLayout
{
  public static f b;
  private static final String[] z;
  ImageView a;
  View.OnTouchListener c = new b(this);
  private final Context d;
  private WebView e;
  private c f = new c(this);

  static
  {
    String[] arrayOfString = new String[4];
    Object localObject1 = "\030y;$4\006z`";
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
          i = 97;
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
        localObject1 = ">F/3\t#s8";
        j = 1;
        i = 0;
        break;
      case 0:
        localObject4[i3] = localObject1;
        localObject1 = "\007s;2\002\034T58+\025`;\002\023\035r=%>";
        j = 2;
        i = 1;
        break;
      case 1:
        localObject4[i3] = localObject1;
        j = 3;
        localObject1 = "!B\034mY";
        i = 2;
        break;
      case 2:
        localObject4[i3] = localObject1;
        z = arrayOfString;
        b = null;
        return;
        i = 116;
        break label91;
        i = 22;
        break label91;
        i = 90;
        break label91;
        i = 64;
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

  @SuppressLint({"NewApi"})
  public a(Context paramContext, cn.jpush.android.data.d paramd)
  {
    super(paramContext);
    this.d = paramContext;
    ProgressBar localProgressBar = new ProgressBar(paramContext, null, 16842872);
    localProgressBar.setMax(100);
    localProgressBar.setLayoutParams(new RelativeLayout.LayoutParams(-1, cn.jpush.android.util.a.a(paramContext, 5.0F)));
    this.e = new WebView(paramContext);
    this.e.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
    this.e.setScrollbarFadingEnabled(true);
    this.e.setScrollBarStyle(33554432);
    WebSettings localWebSettings = this.e.getSettings();
    localWebSettings.setJavaScriptEnabled(true);
    localWebSettings.setDefaultTextEncodingName(z[3]);
    localWebSettings.setSupportZoom(true);
    localWebSettings.setCacheMode(2);
    b = new f(paramContext, paramd);
    this.e.removeJavascriptInterface(z[2]);
    this.e.setWebChromeClient(new cn.jpush.android.b.a.a(z[1], cn.jpush.android.b.a.b.class));
    this.e.setWebViewClient(new d(paramd));
    this.e.setOnTouchListener(this.c);
    addView(localProgressBar, new RelativeLayout.LayoutParams(-1, cn.jpush.android.util.a.a(paramContext, 5.0F)));
    addView(this.e, new RelativeLayout.LayoutParams(-1, -1));
  }

  public final void a(String paramString)
  {
    if (this.e != null)
    {
      new StringBuilder(z[0]).append(paramString).toString();
      x.c();
      this.e.loadUrl(paramString);
    }
  }

  public final boolean a()
  {
    if (this.e != null)
      return this.e.canGoBack();
    return false;
  }

  public final void b()
  {
    if (this.e != null)
      this.e.goBack();
  }

  public final void c()
  {
    if (this.e != null)
      this.e.onResume();
  }

  public final void d()
  {
    if (this.e != null)
      this.e.onPause();
  }

  public final void e()
  {
    removeAllViews();
    if (this.e != null)
    {
      this.e.removeAllViews();
      this.e.destroy();
      this.e = null;
    }
    if (this.a != null)
      this.a = null;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.ui.a
 * JD-Core Version:    0.6.2
 */