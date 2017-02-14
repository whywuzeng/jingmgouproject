package cn.jpush.android.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.widget.Toast;
import cn.jpush.android.api.m;
import cn.jpush.android.data.d;
import cn.jpush.android.data.l;
import cn.jpush.android.data.p;
import cn.jpush.android.data.y;
import cn.jpush.android.service.PushService;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.util.JavascriptCallback;
import cn.jpush.android.util.a;
import cn.jpush.android.util.ai;
import cn.jpush.android.util.x;
import java.lang.ref.WeakReference;

public class WebViewCallback
  implements JavascriptCallback
{
  private static final String[] z;
  private final WeakReference<Activity> a;
  private final d b;

  static
  {
    String[] arrayOfString = new String[20];
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
        i = 72;
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
        localObject2 = "\003\037\00581\036\tB\n:\001\001B\033-\fVB";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "'\002\024\r$\007\bB\r+\032\005\r\002\001\nL\004\036'\003L5\t*NAB";
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
        localObject2 = "";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i4] = localObject1;
        j = 6;
        localObject2 = "";
        i = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i4] = localObject1;
        j = 7;
        localObject2 = "";
        i = 6;
        localObject1 = arrayOfString;
        break;
      case 6:
        localObject3[i4] = localObject1;
        j = 8;
        localObject2 = ":\004\007L)\r\030\013\032!\032\025B\002)\003\tB\005;N\005\f\032)\002\005\006@h)\005\024\th\033\034LB";
        i = 7;
        localObject1 = arrayOfString;
        break;
      case 7:
        localObject3[i4] = localObject1;
        j = 9;
        localObject2 = "";
        i = 8;
        localObject1 = arrayOfString;
        break;
      case 8:
        localObject3[i4] = localObject1;
        j = 10;
        localObject2 = "";
        i = 9;
        localObject1 = arrayOfString;
        break;
      case 9:
        localObject3[i4] = localObject1;
        j = 11;
        localObject2 = "BL\027\036$T";
        i = 10;
        localObject1 = arrayOfString;
        break;
      case 10:
        localObject3[i4] = localObject1;
        j = 12;
        localObject2 = "";
        i = 11;
        localObject1 = arrayOfString;
        break;
      case 11:
        localObject3[i4] = localObject1;
        j = 13;
        localObject2 = "";
        i = 12;
        localObject1 = arrayOfString;
        break;
      case 12:
        localObject3[i4] = localObject1;
        j = 14;
        localObject2 = "";
        i = 13;
        localObject1 = arrayOfString;
        break;
      case 13:
        localObject3[i4] = localObject1;
        j = 15;
        localObject2 = "";
        i = 14;
        localObject1 = arrayOfString;
        break;
      case 14:
        localObject3[i4] = localObject1;
        j = 16;
        localObject2 = "";
        i = 15;
        localObject1 = arrayOfString;
        break;
      case 15:
        localObject3[i4] = localObject1;
        j = 17;
        localObject2 = "";
        i = 16;
        localObject1 = arrayOfString;
        break;
      case 16:
        localObject3[i4] = localObject1;
        j = 18;
        localObject2 = "";
        i = 17;
        localObject1 = arrayOfString;
        break;
      case 17:
        localObject3[i4] = localObject1;
        j = 19;
        localObject2 = "";
        i = 18;
        localObject1 = arrayOfString;
        break;
      case 18:
        localObject3[i4] = localObject1;
        z = arrayOfString;
        return;
        i = 110;
        continue;
        i = 108;
        continue;
        i = 98;
        continue;
        i = 108;
      }
    }
  }

  public WebViewCallback(Context paramContext, d paramd)
  {
    this.a = new WeakReference((Activity)paramContext);
    this.b = paramd;
  }

  @JavascriptInterface
  private void userClick(String paramString)
  {
    try
    {
      i = Integer.parseInt(paramString);
      ServiceInterface.a(this.b.c, i, (Context)this.a.get());
      return;
    }
    catch (Exception localException)
    {
      while (true)
      {
        new StringBuilder(z[3]).append(paramString).toString();
        int i = 1100;
        x.f();
      }
    }
  }

  // ERROR //
  @JavascriptInterface
  public void click(String paramString1, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 84	cn/jpush/android/ui/WebViewCallback:a	Ljava/lang/ref/WeakReference;
    //   4: invokevirtual 107	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   7: ifnull +111 -> 118
    //   10: new 116	java/lang/StringBuilder
    //   13: dup
    //   14: getstatic 71	cn/jpush/android/ui/WebViewCallback:z	[Ljava/lang/String;
    //   17: bipush 15
    //   19: aaload
    //   20: invokespecial 118	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   23: aload_1
    //   24: invokevirtual 122	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: getstatic 71	cn/jpush/android/ui/WebViewCallback:z	[Ljava/lang/String;
    //   30: bipush 16
    //   32: aaload
    //   33: invokevirtual 122	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: aload_2
    //   37: invokevirtual 122	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: getstatic 71	cn/jpush/android/ui/WebViewCallback:z	[Ljava/lang/String;
    //   43: bipush 17
    //   45: aaload
    //   46: invokevirtual 122	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   49: aload_3
    //   50: invokevirtual 122	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   56: pop
    //   57: invokestatic 135	cn/jpush/android/util/x:c	()V
    //   60: aload_0
    //   61: aload_1
    //   62: invokespecial 137	cn/jpush/android/ui/WebViewCallback:userClick	(Ljava/lang/String;)V
    //   65: aload_2
    //   66: invokestatic 143	java/lang/Boolean:parseBoolean	(Ljava/lang/String;)Z
    //   69: istore 4
    //   71: aload_3
    //   72: invokestatic 143	java/lang/Boolean:parseBoolean	(Ljava/lang/String;)Z
    //   75: istore 5
    //   77: iload 5
    //   79: ifeq +21 -> 100
    //   82: aload_0
    //   83: getfield 84	cn/jpush/android/ui/WebViewCallback:a	Ljava/lang/ref/WeakReference;
    //   86: invokevirtual 107	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   89: checkcast 109	android/content/Context
    //   92: aload_0
    //   93: getfield 86	cn/jpush/android/ui/WebViewCallback:b	Lcn/jpush/android/data/d;
    //   96: iconst_0
    //   97: invokestatic 148	cn/jpush/android/api/m:a	(Landroid/content/Context;Lcn/jpush/android/data/d;I)V
    //   100: iload 4
    //   102: ifeq +16 -> 118
    //   105: aload_0
    //   106: getfield 84	cn/jpush/android/ui/WebViewCallback:a	Ljava/lang/ref/WeakReference;
    //   109: invokevirtual 107	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   112: checkcast 79	android/app/Activity
    //   115: invokevirtual 151	android/app/Activity:finish	()V
    //   118: return
    //   119: astore_1
    //   120: iconst_0
    //   121: istore 4
    //   123: iconst_0
    //   124: istore 5
    //   126: goto -49 -> 77
    //   129: astore_1
    //   130: goto -7 -> 123
    //
    // Exception table:
    //   from	to	target	type
    //   65	71	119	java/lang/Exception
    //   71	77	129	java/lang/Exception
  }

  @JavascriptInterface
  public void close()
  {
    if (this.a.get() != null)
    {
      x.c();
      ((Activity)this.a.get()).finish();
    }
  }

  @JavascriptInterface
  public void createShortcut(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      i = Integer.parseInt(paramString3);
      if (this.a.get() == null)
        return;
    }
    catch (Exception localException)
    {
      int i;
      while (true)
      {
        new StringBuilder(z[12]).append(paramString3).toString();
        i = 0;
        x.c();
      }
      new StringBuilder(z[10]).append(paramString1).append(z[11]).append(paramString2).toString();
      x.c();
      a.a((Context)this.a.get(), paramString1, paramString2, m.a(i));
    }
  }

  @JavascriptInterface
  public void download(String paramString)
  {
    if (this.a.get() == null)
      return;
    new StringBuilder(z[19]).append(paramString).toString();
    x.c();
    Context localContext = (Context)this.a.get();
    d locald = this.b;
    Object localObject;
    if (locald.a())
    {
      localObject = (l)locald;
      if (TextUtils.isEmpty(((l)localObject).ac))
        ((l)localObject).ac = paramString;
      if (!TextUtils.isEmpty(((l)localObject).ah))
      {
        a.e(localContext, ((l)localObject).ah);
        m.a(localContext, (d)localObject, 0);
        m.a(localContext, (d)localObject, 1);
      }
    }
    else if (locald.b())
    {
      localObject = (y)locald;
      if (TextUtils.isEmpty(((y)localObject).W))
        ((y)localObject).W = paramString;
      if (!TextUtils.isEmpty(((y)localObject).aa))
        localContext.startActivity(a.a(localContext, locald, false));
    }
    else
    {
      new StringBuilder(z[18]).append(locald.o).toString();
      x.e();
      return;
    }
    ServiceInterface.a(localContext, locald);
  }

  @JavascriptInterface
  public void download(String paramString1, String paramString2)
  {
    if (this.a.get() == null)
      return;
    userClick(paramString1);
    download(paramString2);
    m.a((Context)this.a.get(), this.b, 0);
    ((Activity)this.a.get()).finish();
  }

  @JavascriptInterface
  public void download(String paramString1, String paramString2, String paramString3)
  {
    new StringBuilder(z[1]).append(paramString3).toString();
    x.b();
    download(paramString1, paramString2);
  }

  @JavascriptInterface
  public void executeMsgMessage(String paramString)
  {
    if (PushService.b)
    {
      new StringBuilder(z[2]).append(paramString).toString();
      x.c();
      if (this.a.get() != null);
    }
    else
    {
      return;
    }
    p.a((Context)this.a.get(), paramString);
  }

  @JavascriptInterface
  public void showToast(String paramString)
  {
    if (this.a.get() != null)
    {
      new StringBuilder(z[0]).append(paramString).toString();
      x.c();
      Toast.makeText((Context)this.a.get(), paramString, 0).show();
    }
  }

  @JavascriptInterface
  public void startActivityByIntent(String paramString1, String paramString2)
  {
    Context localContext = (Context)this.a.get();
    if (localContext == null)
      return;
    try
    {
      Intent localIntent = new Intent(paramString1);
      localIntent.addCategory(localContext.getPackageName());
      localIntent.putExtra(z[13], paramString2);
      localIntent.setFlags(268435456);
      localContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramString2)
    {
      x.e(z[5], z[14] + paramString1);
    }
  }

  @JavascriptInterface
  public void startActivityByName(String paramString1, String paramString2)
  {
    if (ai.a(paramString1))
      x.e(z[5], z[7]);
    Context localContext = (Context)this.a.get();
    if (localContext == null);
    while (true)
    {
      return;
      try
      {
        paramString1 = Class.forName(paramString1);
        if (paramString1 != null)
        {
          paramString1 = new Intent(localContext, paramString1);
          paramString1.putExtra(z[6], paramString2);
          paramString1.setFlags(268435456);
          localContext.startActivity(paramString1);
          return;
        }
      }
      catch (Exception paramString1)
      {
        x.e(z[5], z[8]);
      }
    }
  }

  @JavascriptInterface
  public void startMainActivity(String paramString)
  {
    Context localContext = (Context)this.a.get();
    if (localContext == null)
      return;
    try
    {
      a.h(localContext, paramString);
      return;
    }
    catch (Exception paramString)
    {
      x.e(z[5], z[4]);
    }
  }

  @JavascriptInterface
  public void triggerNativeAction(String paramString)
  {
    Context localContext = (Context)this.a.get();
    if (localContext == null)
      return;
    a.b(localContext, z[9], paramString);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.ui.WebViewCallback
 * JD-Core Version:    0.6.2
 */