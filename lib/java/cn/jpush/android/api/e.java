package cn.jpush.android.api;

import android.app.Activity;
import android.app.Application;
import android.app.TabActivity;
import android.content.Context;
import cn.jpush.android.service.PushService;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.af;
import cn.jpush.android.util.ai;
import cn.jpush.android.util.x;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

public class e
{
  public static boolean a;
  public static boolean b;
  private static final String c;
  private static e e;
  private static final String[] z;
  private ExecutorService d = Executors.newSingleThreadExecutor();
  private String f = null;
  private String g = null;
  private ArrayList<a> h = new ArrayList();
  private long i = 30L;
  private long j = 0L;
  private long k = 0L;
  private boolean l = false;
  private boolean m = true;
  private boolean n = true;
  private boolean o = false;
  private boolean p = true;
  private WeakReference<JSONObject> q = null;
  private JSONObject r = null;
  private Object s = new Object();

  static
  {
    String[] arrayOfString = new String[23];
    int i2 = 0;
    Object localObject2 = "H+la";
    int i1 = -1;
    Object localObject1 = arrayOfString;
    char[] arrayOfChar = ((String)localObject2).toCharArray();
    int i3 = arrayOfChar.length;
    int i6 = 0;
    int i4 = 0;
    int i8 = i1;
    localObject2 = arrayOfChar;
    int i9 = i2;
    Object localObject3 = localObject1;
    int i5 = i3;
    Object localObject4;
    int i7;
    if (i3 <= 1)
    {
      localObject4 = localObject1;
      localObject1 = arrayOfChar;
      i7 = i1;
      label68: i5 = i4;
      label71: localObject2 = localObject1;
      i6 = localObject2[i4];
      switch (i5 % 5)
      {
      default:
        i1 = 58;
      case 0:
      case 1:
      case 2:
      case 3:
      }
    }
    while (true)
    {
      localObject2[i4] = ((char)(i1 ^ i6));
      i5 += 1;
      if (i3 == 0)
      {
        i4 = i3;
        break label71;
      }
      i6 = i5;
      i5 = i3;
      localObject3 = localObject4;
      i9 = i2;
      localObject2 = localObject1;
      i8 = i7;
      i7 = i8;
      localObject1 = localObject2;
      i2 = i9;
      localObject4 = localObject3;
      i3 = i5;
      i4 = i6;
      if (i5 > i6)
        break label68;
      localObject1 = new String((char[])localObject2).intern();
      switch (i8)
      {
      default:
        localObject3[i9] = localObject1;
        i2 = 1;
        localObject2 = "X#ua";
        i1 = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i9] = localObject1;
        i2 = 2;
        localObject2 = "-op_D6!wRS7m`\032^'!eT\034\003bpSJ+u}\032S,!pRU1!i_H*n`\032\006b";
        i1 = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i9] = localObject1;
        i2 = 3;
        localObject2 = "v\022twRo\003/kTn'rqWYjBkTH'yp\023\034/twN\034 d$SR4no_Xbhj\032}!umLU6x*UR\020dwOQ')-";
        i1 = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i9] = localObject1;
        i2 = 4;
        localObject2 = "U6hi_";
        i1 = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i9] = localObject1;
        i2 = 5;
        localObject2 = "P#rpeL#tw_";
        i1 = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i9] = localObject1;
        i2 = 6;
        localObject2 = "]!umLY\035uaHQ+oeNY";
        i1 = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i9] = localObject1;
        i2 = 7;
        localObject2 = "O'rwSS,^m^";
        i1 = 6;
        localObject1 = arrayOfString;
        break;
      case 6:
        localObject3[i9] = localObject1;
        i2 = 8;
        localObject2 = "H;qa";
        i1 = 7;
        localObject1 = arrayOfString;
        break;
      case 7:
        localObject3[i9] = localObject1;
        i2 = 9;
        localObject2 = "V2twRc1ueNc!`gRYlkwUR";
        i1 = 8;
        localObject1 = arrayOfString;
        break;
      case 8:
        localObject3[i9] = localObject1;
        i2 = 10;
        localObject2 = "S,QeOO'";
        i1 = 9;
        localObject1 = arrayOfString;
        break;
      case 9:
        localObject3[i9] = localObject1;
        i2 = 11;
        localObject2 = "v\022twRu,uaHZ#ba\024S,QeOO')-\032Q7rp\032^'!g[P.d`\032]$uaH\034!`hVY&!NjI1iMTH'sb[_'/kTn'rqWYj($[R&!NjI1iMTH'sb[_'/kTl#tw_\0341ikOP&!jUHbca\032_#mh_XblkHYbumWYbhj\032H*hw\032}!umLU6x$UNbGv[[/djN\034y!";
        i1 = 10;
        localObject1 = arrayOfString;
        break;
      case 10:
        localObject3[i9] = localObject1;
        i2 = 12;
        localObject2 = "L#fa\032R#la\032X+ej\035HbleN_*!pRYbmeIHbnj_\0342`wIY&!fC\034-oV_O7la";
        i1 = 11;
        localObject1 = arrayOfString;
        break;
      case 11:
        localObject3[i9] = localObject1;
        i2 = 13;
        localObject2 = "v\022twRu,uaHZ#ba\024S,QeOO')-\032Q7rp\032^'!g[P.d`\032]$uaH\034!`hVY&!NjI1iMTH'sb[_'/kTn'rqWYj($SRbulSOb@gNU4hpC\034-s$|N#fi_R6";
        i1 = 12;
        localObject1 = arrayOfString;
        break;
      case 12:
        localObject3[i9] = localObject1;
        i2 = 14;
        localObject2 = "S,SaII/d";
        i1 = 13;
        localObject1 = arrayOfString;
        break;
      case 13:
        localObject3[i9] = localObject1;
        i2 = 15;
        localObject2 = "v\022twRu,uaHZ#ba\024S,SaII/d,\023\034/twN\034 d$Y].ma^\034#gp_NbbeVP'e$pl7rlsR6dv\\]!d*UR\022`qIYj($[R&!NjI1iMTH'sb[_'/kTn'rqWYbrlUI.e$TS6!f_\034!`hVY&!iUN'!pSQ'!mT\034.`wN\034\003bpSJ+u}\032S0!BH]%laTHb!";
        i1 = 14;
        localObject1 = arrayOfString;
        break;
      case 14:
        localObject3[i9] = localObject1;
        i2 = 16;
        localObject2 = "v\022twRu,uaHZ#ba\024S,SaII/d,\023\034/twN\034 d$Y].ma^\034#gp_NbbeVP'e$pl7rlsR6dv\\]!d*UR\022`qIYj($SRbmeIHb@gNU4hpC\034-s$|N#fi_R6";
        i1 = 15;
        localObject1 = arrayOfString;
        break;
      case 15:
        localObject3[i9] = localObject1;
        i2 = 17;
        localObject2 = "]!umLU6haI";
        i1 = 16;
        localObject1 = arrayOfString;
        break;
      case 16:
        localObject3[i9] = localObject1;
        i2 = 18;
        localObject2 = "I6g)\002";
        i1 = 17;
        localObject1 = arrayOfString;
        break;
      case 17:
        localObject3[i9] = localObject1;
        i2 = 19;
        localObject2 = "_7s[IY'rmUR\035dj^";
        i1 = 18;
        localObject1 = arrayOfString;
        break;
      case 18:
        localObject3[i9] = localObject1;
        i2 = 20;
        localObject2 = "X7seNU-o";
        i1 = 19;
        localObject1 = arrayOfString;
        break;
      case 19:
        localObject3[i9] = localObject1;
        i2 = 21;
        localObject2 = "_7s[IY1rmUR\035rp[N6";
        i1 = 20;
        localObject1 = arrayOfString;
        break;
      case 20:
        localObject3[i9] = localObject1;
        i2 = 22;
        localObject2 = "]!umLY\035meOR!i";
        i1 = 21;
        localObject1 = arrayOfString;
        break;
      case 21:
        localObject3[i9] = localObject1;
        z = arrayOfString;
        c = e.class.getSimpleName();
        e = null;
        a = false;
        b = false;
        return;
        i1 = 60;
        continue;
        i1 = 66;
        continue;
        i1 = 1;
        continue;
        i1 = 4;
      }
    }
  }

  private JSONObject a(Context paramContext, long paramLong)
  {
    af.a().b(paramContext, z[21], this.j);
    StringBuilder localStringBuilder = new StringBuilder();
    String str = cn.jpush.android.util.a.u(paramContext);
    if (!ai.a(str))
      localStringBuilder.append(str);
    str = cn.jpush.android.util.a.k(paramContext);
    if (!ai.a(str))
      localStringBuilder.append(str);
    localStringBuilder.append(paramLong);
    this.g = cn.jpush.android.util.a.a(localStringBuilder.toString());
    af.a().b(paramContext, z[7], this.g);
    paramContext = new JSONObject();
    try
    {
      if (!cn.jpush.android.a.j)
      {
        paramContext = c;
        x.e();
        return null;
      }
      a(paramContext);
      paramContext.put(z[4], PushService.m + Math.abs(paramLong / 1000L - PushService.n));
      paramContext.put(z[7], this.g);
      paramContext.put(z[8], z[22]);
      return paramContext;
    }
    catch (JSONException paramContext)
    {
    }
    return null;
  }

  private static void a(JSONObject paramJSONObject)
  {
    String str2 = cn.jpush.android.util.i.a();
    String str1 = str2.split("_")[0];
    str2 = str2.split("_")[1];
    paramJSONObject.put(z[1], str1);
    paramJSONObject.put(z[0], str2);
  }

  private static boolean a(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = false;
    StackTraceElement[] arrayOfStackTraceElement = new Throwable().getStackTrace();
    int i1;
    if (arrayOfStackTraceElement.length >= 2)
      i1 = 0;
    while (true)
    {
      bool2 = bool1;
      try
      {
        if (i1 < arrayOfStackTraceElement.length)
        {
          Object localObject = arrayOfStackTraceElement[i1];
          bool2 = bool1;
          if (!((StackTraceElement)localObject).getMethodName().equals(paramString))
            break label105;
          for (localObject = Class.forName(((StackTraceElement)localObject).getClassName()); ; localObject = ((Class)localObject).getSuperclass())
          {
            bool2 = bool1;
            if (((Class)localObject).getSuperclass() == null)
              break;
            if (((Class)localObject).getSuperclass() == Activity.class)
            {
              bool2 = true;
              break;
            }
          }
        }
      }
      catch (Exception paramString)
      {
        bool2 = bool1;
      }
      return bool2;
      label105: i1 += 1;
      bool1 = bool2;
    }
  }

  public static e b()
  {
    if (e == null);
    try
    {
      e = new e();
      return e;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private boolean c(Context paramContext, String paramString)
  {
    if (!this.p)
    {
      paramContext = c;
      x.d();
      return false;
    }
    if (paramContext == null)
    {
      paramContext = c;
      x.d();
      return false;
    }
    if ((paramContext instanceof Application))
    {
      x.e(c, z[2] + paramString);
      return false;
    }
    if (!a(paramString))
      throw new SecurityException(z[3]);
    return true;
  }

  private JSONObject d(Context paramContext)
  {
    if (this.r == null)
      this.r = ac.a(paramContext, z[9]);
    return this.r;
  }

  public final void a(long paramLong)
  {
    this.i = paramLong;
  }

  public final void a(Context paramContext)
  {
    if (!c(paramContext, z[14]))
      return;
    a = true;
    try
    {
      if ((Class.forName(paramContext.getClass().getName()).newInstance() instanceof TabActivity))
        this.o = false;
      if (this.o)
      {
        x.e(c, z[15]);
        return;
      }
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
      this.o = true;
      this.j = System.currentTimeMillis();
      this.f = paramContext.getClass().getName();
      try
      {
        this.d.execute(new h(this, paramContext));
        return;
      }
      catch (Exception paramContext)
      {
      }
    }
  }

  public final void a(Context paramContext, String paramString)
  {
    if (this.o)
    {
      x.e(c, z[16]);
      return;
    }
    this.o = true;
    this.f = paramString;
    this.j = System.currentTimeMillis();
    try
    {
      this.d.execute(new f(this, paramContext));
      return;
    }
    catch (Exception paramContext)
    {
    }
  }

  public final void a(boolean paramBoolean)
  {
    this.p = paramBoolean;
  }

  public final boolean a()
  {
    return this.p;
  }

  public final void b(Context paramContext)
  {
    if (!c(paramContext, z[10]))
      return;
    b = true;
    try
    {
      if ((Class.forName(paramContext.getClass().getName()).newInstance() instanceof TabActivity))
        this.o = true;
      if (!this.o)
      {
        x.e(c, z[11]);
        return;
      }
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
      this.o = false;
      if ((this.f != null) && (this.f.equals(paramContext.getClass().getName())))
      {
        this.k = System.currentTimeMillis();
        long l1 = (this.k - this.j) / 1000L;
        a locala = new a(this.f, l1);
        this.h.add(locala);
        try
        {
          this.d.execute(new i(this, paramContext));
          return;
        }
        catch (Exception paramContext)
        {
          return;
        }
      }
      paramContext = c;
      x.d();
    }
  }

  public final void b(Context paramContext, String paramString)
  {
    if (!this.o)
    {
      x.e(c, z[13]);
      return;
    }
    this.o = false;
    if ((this.f != null) && (this.f.equals(paramString)))
    {
      this.k = System.currentTimeMillis();
      long l1 = (this.k - this.j) / 1000L;
      paramString = new a(this.f, l1);
      this.h.add(paramString);
      try
      {
        this.d.execute(new g(this, paramContext));
        return;
      }
      catch (Exception paramContext)
      {
        return;
      }
    }
    x.e(c, z[12]);
  }

  // ERROR //
  public final void c(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 126	cn/jpush/android/api/e:f	Ljava/lang/String;
    //   4: ifnull +73 -> 77
    //   7: aload_0
    //   8: getfield 149	cn/jpush/android/api/e:o	Z
    //   11: ifeq +66 -> 77
    //   14: aload_0
    //   15: invokestatic 266	java/lang/System:currentTimeMillis	()J
    //   18: putfield 141	cn/jpush/android/api/e:k	J
    //   21: aload_0
    //   22: getfield 141	cn/jpush/android/api/e:k	J
    //   25: aload_0
    //   26: getfield 139	cn/jpush/android/api/e:j	J
    //   29: lsub
    //   30: ldc2_w 219
    //   33: ldiv
    //   34: lstore_2
    //   35: new 345	cn/jpush/android/api/a
    //   38: dup
    //   39: aload_0
    //   40: getfield 126	cn/jpush/android/api/e:f	Ljava/lang/String;
    //   43: lload_2
    //   44: invokespecial 352	cn/jpush/android/api/a:<init>	(Ljava/lang/String;J)V
    //   47: astore 4
    //   49: aload_0
    //   50: getfield 133	cn/jpush/android/api/e:h	Ljava/util/ArrayList;
    //   53: aload 4
    //   55: invokevirtual 355	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   58: pop
    //   59: aload_0
    //   60: getfield 124	cn/jpush/android/api/e:d	Ljava/util/concurrent/ExecutorService;
    //   63: new 442	cn/jpush/android/api/j
    //   66: dup
    //   67: aload_0
    //   68: aload_1
    //   69: invokespecial 443	cn/jpush/android/api/j:<init>	(Lcn/jpush/android/api/e;Landroid/content/Context;)V
    //   72: invokeinterface 429 2 0
    //   77: return
    //   78: astore_1
    //   79: return
    //   80: astore_1
    //   81: return
    //
    // Exception table:
    //   from	to	target	type
    //   0	59	78	java/lang/Exception
    //   59	77	80	java/lang/Exception
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.api.e
 * JD-Core Version:    0.6.2
 */