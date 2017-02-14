package cn.jpush.android.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import cn.jpush.android.a;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.util.x;

public final class d extends WebViewClient
{
  private static final String[] z;
  private final cn.jpush.android.data.d a;

  static
  {
    String[] arrayOfString = new String[22];
    int j = 0;
    Object localObject2 = "C\006N~";
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
        i = 16;
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
        localObject2 = "\005\037J:";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "\005A]~";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "J\007^g\004X";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "F\023SbdD";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = "PPO||\tH\030+c\t\017";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i4] = localObject1;
        j = 6;
        localObject2 = "J\034^|B\026\024g~_\027Tz>N\nN|q\0057wOYg";
        i = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i4] = localObject1;
        j = 7;
        localObject2 = "J\034^|B\026\024g~_\027Tz>N\nN|q\005&VD";
        i = 6;
        localObject1 = arrayOfString;
        break;
      case 6:
        localObject3[i4] = localObject1;
        j = 8;
        localObject2 = "\r\026S|uH\006\007hqG\001_";
        i = 7;
        localObject1 = arrayOfString;
        break;
      case 7:
        localObject3[i4] = localObject1;
        j = 9;
        localObject2 = "[\036[g~\004\006_vd";
        i = 8;
        localObject1 = arrayOfString;
        break;
      case 8:
        localObject3[i4] = localObject1;
        j = 10;
        localObject2 = "J\034^|B\026\024g~_\027Tz>N\nN|q\005!oLZn1n";
        i = 9;
        localObject1 = arrayOfString;
        break;
      case 9:
        localObject3[i4] = localObject1;
        j = 11;
        localObject2 = "\024\026S|uH\006\007hqG\001_";
        i = 10;
        localObject1 = arrayOfString;
        break;
      case 10:
        localObject3[i4] = localObject1;
        j = 12;
        localObject2 = "O\033Hks_O";
        i = 11;
        localObject1 = arrayOfString;
        break;
      case 11:
        localObject3[i4] = localObject1;
        j = 13;
        localObject2 = "J\034^|B\026\024g~_\027Tz>J\021NgE\\iK^o";
        i = 12;
        localObject1 = arrayOfString;
        break;
      case 12:
        localObject3[i4] = localObject1;
        j = 14;
        localObject2 = "z\007_|ix\006Hg~LH\032";
        i = 13;
        localObject1 = arrayOfString;
        break;
      case 13:
        localObject3[i4] = localObject1;
        j = 15;
        localObject2 = "]\033^k\004X";
        i = 14;
        localObject1 = arrayOfString;
        break;
      case 14:
        localObject3[i4] = localObject1;
        j = 16;
        localObject2 = "\005\037J=";
        i = 15;
        localObject1 = arrayOfString;
        break;
      case 15:
        localObject3[i4] = localObject1;
        j = 17;
        localObject2 = "_\033Nbu\026";
        i = 16;
        localObject1 = arrayOfString;
        break;
      case 16:
        localObject3[i4] = localObject1;
        j = 18;
        localObject2 = "\r\021U`dN\034N3";
        i = 17;
        localObject1 = arrayOfString;
        break;
      case 17:
        localObject3[i4] = localObject1;
        j = 19;
        localObject2 = "J\034^|B\026\024g~_\027Tz>J\021NgE\\lGU|";
        i = 18;
        localObject1 = arrayOfString;
        break;
      case 18:
        localObject3[i4] = localObject1;
        j = 20;
        localObject2 = "";
        i = 19;
        localObject1 = arrayOfString;
        break;
      case 19:
        localObject3[i4] = localObject1;
        j = 21;
        localObject2 = "";
        i = 20;
        localObject1 = arrayOfString;
        break;
      case 20:
        localObject3[i4] = localObject1;
        z = arrayOfString;
        return;
        i = 43;
        continue;
        i = 114;
        continue;
        i = 58;
        continue;
        i = 14;
      }
    }
  }

  public d(cn.jpush.android.data.d paramd)
  {
    this.a = paramd;
  }

  public final void onLoadResource(WebView paramWebView, String paramString)
  {
    super.onLoadResource(paramWebView, paramString);
  }

  public final void onPageFinished(WebView paramWebView, String paramString)
  {
    super.onPageFinished(paramWebView, paramString);
  }

  public final void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
  {
    super.onPageStarted(paramWebView, paramString, paramBitmap);
  }

  public final boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    Object localObject1 = paramWebView.getContext();
    new StringBuilder(z[21]).append(paramString).toString();
    x.d();
    String str1;
    try
    {
      str1 = String.format(z[5], new Object[] { paramString });
      if (this.a.O)
      {
        ((Context)localObject1).startActivity(new Intent(z[19], Uri.parse(paramString)));
        ServiceInterface.a(this.a.c, 1016, str1, a.d);
        return true;
      }
      if (paramString.endsWith(z[16]))
      {
        localObject1 = new Intent(z[19]);
        ((Intent)localObject1).setDataAndType(Uri.parse(paramString), z[3]);
        paramWebView.getContext().startActivity((Intent)localObject1);
        return true;
      }
    }
    catch (Exception paramWebView)
    {
      x.f();
      return true;
    }
    if ((paramString.endsWith(z[1])) || (paramString.endsWith(z[2])))
    {
      localObject1 = new Intent(z[19]);
      ((Intent)localObject1).setDataAndType(Uri.parse(paramString), z[15]);
      paramWebView.getContext().startActivity((Intent)localObject1);
      return true;
    }
    if (paramString.startsWith(z[0]))
    {
      paramWebView.loadUrl(paramString);
      ServiceInterface.a(this.a.c, 1016, str1, a.d);
    }
    else if ((paramString != null) && (paramString.startsWith(z[4])))
    {
      paramWebView = paramString;
      if (paramString.lastIndexOf(z[12]) < 0)
      {
        paramWebView = paramString;
        if (!paramString.startsWith(z[4]))
          if (paramString.indexOf("?") <= 0)
            break label622;
      }
      label622: for (paramWebView = paramString + z[8]; ; paramWebView = paramString + z[11])
      {
        paramWebView.lastIndexOf(z[12]);
        int i = paramWebView.indexOf("?");
        Object localObject2 = paramWebView.substring(0, i);
        String str2 = paramWebView.substring(i);
        new StringBuilder(z[20]).append((String)localObject2).toString();
        x.b();
        new StringBuilder(z[14]).append(str2).toString();
        x.b();
        paramString = null;
        paramWebView = paramString;
        if (((String)localObject2).startsWith(z[4]))
        {
          localObject2 = ((String)localObject2).split(":");
          paramWebView = paramString;
          if (localObject2 != null)
          {
            paramWebView = paramString;
            if (localObject2.length == 2)
            {
              i = str2.indexOf(z[17]);
              int j = str2.indexOf(z[18]);
              paramString = str2.substring(i + 6, j);
              str2 = str2.substring(j + 9);
              localObject2 = localObject2[1];
              paramWebView = new Intent(z[13]);
              paramWebView.setType(z[9]);
              paramWebView.putExtra(z[6], new String[] { localObject2 });
              paramWebView.putExtra(z[10], paramString);
              paramWebView.putExtra(z[7], str2);
            }
          }
        }
        if (paramWebView != null)
          ((Context)localObject1).startActivity(paramWebView);
        ServiceInterface.a(this.a.c, 1016, str1, a.d);
        return true;
      }
    }
    return false;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.ui.d
 * JD-Core Version:    0.6.2
 */