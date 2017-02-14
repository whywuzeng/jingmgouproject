package cn.jpush.android.b.a;

import android.content.Context;
import android.content.Intent;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageButton;
import cn.jpush.android.api.q;
import cn.jpush.android.util.ai;
import cn.jpush.android.util.x;

public final class e
{
  private static final String[] z;
  private Context a = null;
  private WindowManager b = null;
  private WebView c = null;
  private ImageButton d = null;

  static
  {
    String[] arrayOfString = new String[6];
    int j = 0;
    Object localObject2 = "";
    int i = -1;
    Object localObject1 = arrayOfString;
    char[] arrayOfChar = ((String)localObject2).toCharArray();
    int k = arrayOfChar.length;
    int i1 = 0;
    int m = 0;
    int i3 = i;
    localObject2 = arrayOfChar;
    int i4 = j;
    Object localObject3 = localObject1;
    int n = k;
    Object localObject4;
    int i2;
    if (k <= 1)
    {
      localObject4 = localObject1;
      localObject1 = arrayOfChar;
      i2 = i;
      label68: n = m;
      label71: localObject2 = localObject1;
      i1 = localObject2[m];
      switch (n % 5)
      {
      default:
        i = 126;
      case 0:
      case 1:
      case 2:
      case 3:
      }
    }
    while (true)
    {
      localObject2[m] = ((char)(i ^ i1));
      n += 1;
      if (k == 0)
      {
        m = k;
        break label71;
      }
      i1 = n;
      n = k;
      localObject3 = localObject4;
      i4 = j;
      localObject2 = localObject1;
      i3 = i2;
      i2 = i3;
      localObject1 = localObject2;
      j = i4;
      localObject4 = localObject3;
      k = n;
      m = i1;
      if (n > i1)
        break label68;
      localObject1 = new String((char[])localObject2).intern();
      switch (i3)
      {
      default:
        localObject3[i4] = localObject1;
        j = 1;
        localObject2 = "";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "\034/X\003S\021r\024\\\037\\qU\024^";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "Pa\001G\021_\"X\003S\021q\001O\fEC\026Z\027Gk\001W<HL\024C\033\034/X\003S\034/XO\035Ek\003G\nHL\024C\033\0218U";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = "b{\006Z\033\\C\031K\fEU\020L(Xg\002m\037]n\027O\035Z";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i4] = localObject1;
        z = arrayOfString;
        return;
        i = 49;
        continue;
        i = 2;
        continue;
        i = 117;
        continue;
        i = 46;
      }
    }
  }

  public e(Context paramContext, WindowManager paramWindowManager, WebView paramWebView, ImageButton paramImageButton)
  {
    x.c();
    this.a = paramContext;
    this.b = paramWindowManager;
    this.c = paramWebView;
    this.d = paramImageButton;
  }

  public final void a(String paramString1, String paramString2)
  {
    new StringBuilder(z[3]).append(paramString1).append(z[2]).append(paramString2).toString();
    x.c();
    if (ai.a(paramString1))
      x.e(z[5], z[0]);
    if (this.a == null);
    while (true)
    {
      return;
      try
      {
        paramString1 = Class.forName(paramString1);
        if (paramString1 != null)
        {
          paramString1 = new Intent(this.a, paramString1);
          paramString1.putExtra(z[4], paramString2);
          paramString1.setFlags(268435456);
          this.a.startActivity(paramString1);
          x.c();
          q.a(this.b, this.c, this.d);
          return;
        }
      }
      catch (Exception paramString1)
      {
        x.e(z[5], z[1]);
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.b.a.e
 * JD-Core Version:    0.6.2
 */