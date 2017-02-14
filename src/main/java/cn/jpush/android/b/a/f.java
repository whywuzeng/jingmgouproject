package cn.jpush.android.b.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;
import cn.jpush.android.api.m;
import cn.jpush.android.data.d;
import cn.jpush.android.data.l;
import cn.jpush.android.data.p;
import cn.jpush.android.data.y;
import cn.jpush.android.service.PushService;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.util.a;
import cn.jpush.android.util.ai;
import cn.jpush.android.util.x;
import java.lang.ref.WeakReference;

public final class f
{
  private static final String[] z;
  private final WeakReference<Activity> a;
  private final d b;

  static
  {
    String[] arrayOfString = new String[20];
    int j = 0;
    Object localObject2 = "\004\020-\f\0222\031#N\0200\036uI\t6\026:X\024\036\006(a\024 \006.K\024sXo";
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
        i = 113;
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
        localObject2 = "U:^\035i";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = ":\026 B87U<D\036&\031+\f\0236U&B\005sXo";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "\004\020-\f\0222\031#N\0200\036uO\0036\024;I\";\032=X\022&\001o\001Q=\024\"IK";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "\032\0339M\035:\021oM\022'\034 B87U)^\036>U\030I\023sXo";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = "\004\020-z\0306\002\007I\035#\020=";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i4] = localObject1;
        j = 6;
        localObject2 = "2\026;E\007:\0016b\020>\020o\021Q";
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
        localObject2 = "";
        i = 7;
        localObject1 = arrayOfString;
        break;
      case 7:
        localObject3[i4] = localObject1;
        j = 9;
        localObject2 = "0\033aF\001&\006'\002\020=\021=C\0307[\016o%\032#\006x(\f%\016~0\036";
        i = 8;
        localObject1 = arrayOfString;
        break;
      case 8:
        localObject3[i4] = localObject1;
        j = 10;
        localObject2 = "\004\020-\f\0222\031#N\0200\036u_\031<\002\033C\020 \001o\001Q";
        i = 9;
        localObject1 = arrayOfString;
        break;
      case 9:
        localObject3[i4] = localObject1;
        j = 11;
        localObject2 = "0\033aF\001&\006'\002\020=\021=C\0307[&B\0056\033;\0020\020!\006c?\f'\006o9\003 \034d.\0204\003`3\0226\004";
        i = 10;
        localObject1 = arrayOfString;
        break;
      case 10:
        localObject3[i4] = localObject1;
        j = 12;
        localObject2 = "\032\0339M\035:\021oA\024 \006.K\024\007\f?IQ5\032=\f\025<\002!@\0362\021o\001Q";
        i = 11;
        localObject1 = arrayOfString;
        break;
      case 11:
        localObject3[i4] = localObject1;
        j = 13;
        localObject2 = "\004\020-\f\0222\031#N\0200\036uH\036$\033#C\0207Ub\f";
        i = 12;
        localObject1 = arrayOfString;
        break;
      case 12:
        localObject3[i4] = localObject1;
        j = 14;
        localObject2 = "\006\033'M\0377\031*\f\030=\001*B\005sOoO\037}\037?Y\002;[.B\025!\032&H_:\033;I\037'[\016o%\032:\001s0\020!\006z8\007,\020c!\026;\013i5";
        i = 13;
        localObject1 = arrayOfString;
        break;
      case 13:
        localObject3[i4] = localObject1;
        j = 15;
        localObject2 = "0\033aF\001&\006'\002\020=\021=C\0307[\nt%\0014";
        i = 14;
        localObject1 = arrayOfString;
        break;
      case 14:
        localObject3[i4] = localObject1;
        j = 16;
        localObject2 = "\006\033'M\0377\031*\f\030=\001*B\005sOo";
        i = 15;
        localObject1 = arrayOfString;
        break;
      case 15:
        localObject3[i4] = localObject1;
        j = 17;
        localObject2 = "U<D\036&\031+o\035<\006*\026";
        i = 16;
        localObject1 = arrayOfString;
        break;
      case 16:
        localObject3[i4] = localObject1;
        j = 18;
        localObject2 = "\004\020-\f\0222\031#N\0200\036uO\035:\026$\f\\s\024,X\030<\033\006HK";
        i = 17;
        localObject1 = arrayOfString;
        break;
      case 17:
        localObject3[i4] = localObject1;
        j = 19;
        localObject2 = "U<D\036&\031+o\020=\026*@?<\001&J\0300\024;E\036=O";
        i = 18;
        localObject1 = arrayOfString;
        break;
      case 18:
        localObject3[i4] = localObject1;
        z = arrayOfString;
        return;
        i = 83;
        continue;
        i = 117;
        continue;
        i = 79;
        continue;
        i = 44;
      }
    }
  }

  public f(Context paramContext, d paramd)
  {
    this.a = new WeakReference((Activity)paramContext);
    this.b = paramd;
  }

  private void f(String paramString)
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
        new StringBuilder(z[4]).append(paramString).toString();
        int i = 1100;
        x.f();
      }
    }
  }

  public final void a()
  {
    if (this.a.get() != null)
    {
      x.c();
      ((Activity)this.a.get()).finish();
    }
  }

  public final void a(String paramString)
  {
    Context localContext = (Context)this.a.get();
    if (localContext == null)
      return;
    a.b(localContext, z[11], paramString);
  }

  public final void a(String paramString1, String paramString2)
  {
    new StringBuilder(z[6]).append(paramString1).toString();
    x.c();
    if (ai.a(paramString1))
      x.e(z[5], z[8]);
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
          paramString1.putExtra(z[9], paramString2);
          paramString1.setFlags(268435456);
          localContext.startActivity(paramString1);
          return;
        }
      }
      catch (Exception paramString1)
      {
        x.e(z[5], z[7]);
      }
    }
  }

  public final void a(String paramString1, String paramString2, String paramString3)
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
        new StringBuilder(z[2]).append(paramString3).toString();
        i = 0;
        x.c();
      }
      new StringBuilder(z[3]).append(paramString1).append(z[1]).append(paramString2).toString();
      x.c();
      a.a((Context)this.a.get(), paramString1, paramString2, m.a(i));
    }
  }

  public final void b(String paramString)
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
      x.e(z[5], z[14]);
    }
  }

  public final void b(String paramString1, String paramString2)
  {
    Context localContext = (Context)this.a.get();
    if (localContext == null)
      return;
    try
    {
      Intent localIntent = new Intent(paramString1);
      localIntent.addCategory(localContext.getPackageName());
      localIntent.putExtra(z[15], paramString2);
      localIntent.setFlags(268435456);
      localContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramString2)
    {
      x.e(z[5], z[16] + paramString1);
    }
  }

  // ERROR //
  public final void b(String paramString1, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 82	cn/jpush/android/b/a/f:a	Ljava/lang/ref/WeakReference;
    //   4: invokevirtual 104	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   7: ifnull +111 -> 118
    //   10: new 113	java/lang/StringBuilder
    //   13: dup
    //   14: getstatic 69	cn/jpush/android/b/a/f:z	[Ljava/lang/String;
    //   17: bipush 18
    //   19: aaload
    //   20: invokespecial 115	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   23: aload_1
    //   24: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: getstatic 69	cn/jpush/android/b/a/f:z	[Ljava/lang/String;
    //   30: bipush 17
    //   32: aaload
    //   33: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: aload_2
    //   37: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: getstatic 69	cn/jpush/android/b/a/f:z	[Ljava/lang/String;
    //   43: bipush 19
    //   45: aaload
    //   46: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   49: aload_3
    //   50: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: invokevirtual 122	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   56: pop
    //   57: invokestatic 128	cn/jpush/android/util/x:c	()V
    //   60: aload_0
    //   61: aload_1
    //   62: invokespecial 192	cn/jpush/android/b/a/f:f	(Ljava/lang/String;)V
    //   65: aload_2
    //   66: invokestatic 197	java/lang/Boolean:parseBoolean	(Ljava/lang/String;)Z
    //   69: istore 4
    //   71: aload_3
    //   72: invokestatic 197	java/lang/Boolean:parseBoolean	(Ljava/lang/String;)Z
    //   75: istore 5
    //   77: iload 5
    //   79: ifeq +21 -> 100
    //   82: aload_0
    //   83: getfield 82	cn/jpush/android/b/a/f:a	Ljava/lang/ref/WeakReference;
    //   86: invokevirtual 104	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   89: checkcast 106	android/content/Context
    //   92: aload_0
    //   93: getfield 84	cn/jpush/android/b/a/f:b	Lcn/jpush/android/data/d;
    //   96: iconst_0
    //   97: invokestatic 200	cn/jpush/android/api/m:a	(Landroid/content/Context;Lcn/jpush/android/data/d;I)V
    //   100: iload 4
    //   102: ifeq +16 -> 118
    //   105: aload_0
    //   106: getfield 82	cn/jpush/android/b/a/f:a	Ljava/lang/ref/WeakReference;
    //   109: invokevirtual 104	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   112: checkcast 77	android/app/Activity
    //   115: invokevirtual 131	android/app/Activity:finish	()V
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

  public final void c(String paramString)
  {
    if (this.a.get() == null)
      return;
    new StringBuilder(z[13]).append(paramString).toString();
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
      new StringBuilder(z[12]).append(locald.o).toString();
      x.e();
      return;
    }
    ServiceInterface.a(localContext, locald);
  }

  public final void c(String paramString1, String paramString2)
  {
    if (this.a.get() == null)
      return;
    f(paramString1);
    c(paramString2);
    m.a((Context)this.a.get(), this.b, 0);
    ((Activity)this.a.get()).finish();
  }

  public final void d(String paramString)
  {
    if (this.a.get() != null)
    {
      new StringBuilder(z[10]).append(paramString).toString();
      x.c();
      Toast.makeText((Context)this.a.get(), paramString, 0).show();
    }
  }

  public final void e(String paramString)
  {
    if (PushService.b)
    {
      new StringBuilder(z[0]).append(paramString).toString();
      x.c();
      if (this.a.get() != null);
    }
    else
    {
      return;
    }
    p.a((Context)this.a.get(), paramString);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.b.a.f
 * JD-Core Version:    0.6.2
 */