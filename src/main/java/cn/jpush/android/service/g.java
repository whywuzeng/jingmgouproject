package cn.jpush.android.service;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import cn.jpush.android.data.k;
import cn.jpush.android.util.x;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class g
{
  private static g a;
  private static ExecutorService b;
  private static cn.jpush.android.data.i f;
  private static k g;
  private static final String[] z;
  private Handler c = null;
  private Context d = null;
  private String e = "";

  static
  {
    String[] arrayOfString = new String[14];
    int j = 0;
    Object localObject2 = "*\022\\[N,-JIJ.-]A@\t!H\\O\017,jMH\024'[";
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
        i = 38;
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
        localObject2 = "\001&M\bj\017!HDh\0176@NO\003#]AI\016";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "N2LZK\t1ZAI\016lcxs3\nvec3\021hoc";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "\r'Z[G\007'";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "\003,\007BV\0251A\006G\016&[GO\004l@FR\005,]\006h/\026`no#\003}ai.\035{me%\013mb?\022{g~9";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = "\016-]A@\t!HAI\016\035]QV\005";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i4] = localObject1;
        j = 6;
        localObject2 = "\0012YaB";
        i = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i4] = localObject1;
        j = 7;
        localObject2 = "\023'GLC\022\013M";
        i = 6;
        localObject1 = arrayOfString;
        break;
      case 6:
        localObject3[i4] = localObject1;
        j = 8;
        localObject2 = "\t,@\\\006,-JIJ.-]A@\t!H\\O\017,\tKG\0236\tM^\0206\023";
        i = 7;
        localObject1 = arrayOfString;
        break;
      case 7:
        localObject3[i4] = localObject1;
        j = 9;
        localObject2 = "\0240@OA\0050efm\t.ExT\017!L[UZb";
        i = 8;
        localObject1 = arrayOfString;
        break;
      case 8:
        localObject3[i4] = localObject1;
        j = 10;
        localObject2 = "\020-Z\\\006\004'EI_\005&\t\022\006";
        i = 9;
        localObject1 = arrayOfString;
        break;
      case 9:
        localObject3[i4] = localObject1;
        j = 11;
        localObject2 = "\003.LIT@#ED\006\f-JIJ@,F\\O\006+JIR\t-G\b";
        i = 10;
        localObject1 = arrayOfString;
        break;
      case 10:
        localObject3[i4] = localObject1;
        j = 12;
        localObject2 = "\022'DGP\005beGE\001.gGR\t$@KG\024+FF\006";
        i = 11;
        localObject1 = arrayOfString;
        break;
      case 11:
        localObject3[i4] = localObject1;
        j = 13;
        localObject2 = "\022'DGP\005bEGE\001.\tKI\025,]\b\034@";
        i = 12;
        localObject1 = arrayOfString;
        break;
      case 12:
        localObject3[i4] = localObject1;
        z = arrayOfString;
        a = null;
        b = Executors.newSingleThreadExecutor();
        f = null;
        g = new k();
        return;
        i = 96;
        continue;
        i = 66;
        continue;
        i = 41;
        continue;
        i = 40;
      }
    }
  }

  private g(Context paramContext)
  {
    x.c();
    this.c = new Handler(Looper.getMainLooper());
    this.d = paramContext;
    this.e = this.d.getPackageName();
  }

  public static g a(Context paramContext)
  {
    x.c();
    if (a == null)
      a = new g(paramContext);
    return a;
  }

  private void a(long paramLong1, long paramLong2)
  {
    x.c();
    if (paramLong1 < 0L)
      x.f();
    while (this.c == null)
      return;
    i locali = new i(this, paramLong1);
    if (paramLong2 <= 0L)
    {
      x.c();
      this.c.post(locali);
      return;
    }
    new StringBuilder(z[10]).append(paramLong2).toString();
    x.c();
    this.c.postDelayed(locali, paramLong2);
  }

  private void a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    x.c();
    Intent localIntent = new Intent(z[4]);
    localIntent.putExtra(z[7], paramString3);
    localIntent.putExtra(z[6], paramString2);
    localIntent.putExtra(z[3], paramString1);
    localIntent.putExtra(z[5], 1);
    localIntent.addCategory(paramString2);
    paramContext.sendOrderedBroadcast(localIntent, paramString2 + z[2]);
    x.c();
  }

  // ERROR //
  public static boolean a(Context paramContext, long paramLong)
  {
    // Byte code:
    //   0: getstatic 66	cn/jpush/android/service/g:z	[Ljava/lang/String;
    //   3: iconst_0
    //   4: aaload
    //   5: getstatic 66	cn/jpush/android/service/g:z	[Ljava/lang/String;
    //   8: bipush 12
    //   10: aaload
    //   11: invokestatic 232	cn/jpush/android/util/x:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   14: getstatic 78	cn/jpush/android/service/g:f	Lcn/jpush/android/data/i;
    //   17: ifnonnull +14 -> 31
    //   20: new 177	cn/jpush/android/data/i
    //   23: dup
    //   24: aload_0
    //   25: invokespecial 178	cn/jpush/android/data/i:<init>	(Landroid/content/Context;)V
    //   28: putstatic 78	cn/jpush/android/service/g:f	Lcn/jpush/android/data/i;
    //   31: aconst_null
    //   32: astore_0
    //   33: aload_0
    //   34: astore_3
    //   35: getstatic 78	cn/jpush/android/service/g:f	Lcn/jpush/android/data/i;
    //   38: invokevirtual 180	cn/jpush/android/data/i:a	()V
    //   41: aload_0
    //   42: astore_3
    //   43: getstatic 78	cn/jpush/android/service/g:f	Lcn/jpush/android/data/i;
    //   46: lload_1
    //   47: iconst_0
    //   48: invokevirtual 235	cn/jpush/android/data/i:a	(JI)Landroid/database/Cursor;
    //   51: astore_0
    //   52: aload_0
    //   53: astore_3
    //   54: getstatic 78	cn/jpush/android/service/g:f	Lcn/jpush/android/data/i;
    //   57: astore 4
    //   59: aload_0
    //   60: astore_3
    //   61: aload_0
    //   62: getstatic 84	cn/jpush/android/service/g:g	Lcn/jpush/android/data/k;
    //   65: invokestatic 198	cn/jpush/android/data/i:a	(Landroid/database/Cursor;Lcn/jpush/android/data/k;)V
    //   68: aload_0
    //   69: astore_3
    //   70: getstatic 84	cn/jpush/android/service/g:g	Lcn/jpush/android/data/k;
    //   73: invokevirtual 238	cn/jpush/android/data/k:b	()I
    //   76: ifle +67 -> 143
    //   79: aload_0
    //   80: astore_3
    //   81: new 135	java/lang/StringBuilder
    //   84: dup
    //   85: getstatic 66	cn/jpush/android/service/g:z	[Ljava/lang/String;
    //   88: bipush 13
    //   90: aaload
    //   91: invokespecial 138	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   94: getstatic 84	cn/jpush/android/service/g:g	Lcn/jpush/android/data/k;
    //   97: invokevirtual 238	cn/jpush/android/data/k:b	()I
    //   100: invokevirtual 241	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   103: invokevirtual 145	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   106: pop
    //   107: aload_0
    //   108: astore_3
    //   109: invokestatic 99	cn/jpush/android/util/x:c	()V
    //   112: aload_0
    //   113: astore_3
    //   114: getstatic 78	cn/jpush/android/service/g:f	Lcn/jpush/android/data/i;
    //   117: lload_1
    //   118: iconst_0
    //   119: iconst_1
    //   120: iconst_0
    //   121: getstatic 84	cn/jpush/android/service/g:g	Lcn/jpush/android/data/k;
    //   124: invokevirtual 200	cn/jpush/android/data/k:d	()Ljava/lang/String;
    //   127: getstatic 84	cn/jpush/android/service/g:g	Lcn/jpush/android/data/k;
    //   130: invokevirtual 206	cn/jpush/android/data/k:f	()J
    //   133: getstatic 84	cn/jpush/android/service/g:g	Lcn/jpush/android/data/k;
    //   136: invokevirtual 208	cn/jpush/android/data/k:e	()J
    //   139: invokevirtual 211	cn/jpush/android/data/i:b	(JIIILjava/lang/String;JJ)Z
    //   142: pop
    //   143: aload_0
    //   144: astore_3
    //   145: getstatic 78	cn/jpush/android/service/g:f	Lcn/jpush/android/data/i;
    //   148: invokevirtual 216	cn/jpush/android/data/i:b	()V
    //   151: aload_0
    //   152: ifnull +9 -> 161
    //   155: aload_0
    //   156: invokeinterface 219 1 0
    //   161: iconst_1
    //   162: ireturn
    //   163: astore_0
    //   164: aload_3
    //   165: ifnull -4 -> 161
    //   168: aload_3
    //   169: invokeinterface 219 1 0
    //   174: goto -13 -> 161
    //   177: astore_0
    //   178: aconst_null
    //   179: astore 4
    //   181: aload_0
    //   182: astore_3
    //   183: aload 4
    //   185: ifnull +10 -> 195
    //   188: aload 4
    //   190: invokeinterface 219 1 0
    //   195: aload_3
    //   196: athrow
    //   197: astore_3
    //   198: aload_0
    //   199: astore 4
    //   201: goto -18 -> 183
    //
    // Exception table:
    //   from	to	target	type
    //   35	41	163	java/lang/Exception
    //   43	52	163	java/lang/Exception
    //   54	59	163	java/lang/Exception
    //   61	68	163	java/lang/Exception
    //   70	79	163	java/lang/Exception
    //   81	107	163	java/lang/Exception
    //   109	112	163	java/lang/Exception
    //   114	143	163	java/lang/Exception
    //   145	151	163	java/lang/Exception
    //   35	41	177	finally
    //   43	52	177	finally
    //   54	59	197	finally
    //   61	68	197	finally
    //   70	79	197	finally
    //   81	107	197	finally
    //   109	112	197	finally
    //   114	143	197	finally
    //   145	151	197	finally
  }

  public static void b(Context paramContext)
  {
    x.b(z[0], z[11]);
    if (f == null)
      f = new cn.jpush.android.data.i(paramContext);
    f.a();
    if (f.c())
      x.c();
    f.b();
  }

  // ERROR //
  public final boolean a(Context paramContext, cn.jpush.android.data.JPushLocalNotification paramJPushLocalNotification)
  {
    // Byte code:
    //   0: getstatic 66	cn/jpush/android/service/g:z	[Ljava/lang/String;
    //   3: iconst_0
    //   4: aaload
    //   5: getstatic 66	cn/jpush/android/service/g:z	[Ljava/lang/String;
    //   8: iconst_1
    //   9: aaload
    //   10: invokestatic 232	cn/jpush/android/util/x:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   13: invokestatic 186	java/lang/System:currentTimeMillis	()J
    //   16: lstore_3
    //   17: aload_2
    //   18: invokevirtual 251	cn/jpush/android/data/JPushLocalNotification:getBroadcastTime	()J
    //   21: lload_3
    //   22: lsub
    //   23: lstore 5
    //   25: aload_1
    //   26: invokestatic 257	cn/jpush/android/service/ServiceInterface:j	(Landroid/content/Context;)Z
    //   29: ifeq +6 -> 35
    //   32: invokestatic 99	cn/jpush/android/util/x:c	()V
    //   35: getstatic 78	cn/jpush/android/service/g:f	Lcn/jpush/android/data/i;
    //   38: ifnonnull +14 -> 52
    //   41: new 177	cn/jpush/android/data/i
    //   44: dup
    //   45: aload_1
    //   46: invokespecial 178	cn/jpush/android/data/i:<init>	(Landroid/content/Context;)V
    //   49: putstatic 78	cn/jpush/android/service/g:f	Lcn/jpush/android/data/i;
    //   52: aconst_null
    //   53: astore_1
    //   54: aload_1
    //   55: astore 7
    //   57: getstatic 78	cn/jpush/android/service/g:f	Lcn/jpush/android/data/i;
    //   60: invokevirtual 180	cn/jpush/android/data/i:a	()V
    //   63: aload_1
    //   64: astore 7
    //   66: getstatic 78	cn/jpush/android/service/g:f	Lcn/jpush/android/data/i;
    //   69: aload_2
    //   70: invokevirtual 260	cn/jpush/android/data/JPushLocalNotification:getNotificationId	()J
    //   73: iconst_0
    //   74: invokevirtual 235	cn/jpush/android/data/i:a	(JI)Landroid/database/Cursor;
    //   77: astore_1
    //   78: aload_1
    //   79: astore 7
    //   81: getstatic 78	cn/jpush/android/service/g:f	Lcn/jpush/android/data/i;
    //   84: astore 8
    //   86: aload_1
    //   87: astore 7
    //   89: aload_1
    //   90: getstatic 84	cn/jpush/android/service/g:g	Lcn/jpush/android/data/k;
    //   93: invokestatic 198	cn/jpush/android/data/i:a	(Landroid/database/Cursor;Lcn/jpush/android/data/k;)V
    //   96: aload_1
    //   97: astore 7
    //   99: getstatic 84	cn/jpush/android/service/g:g	Lcn/jpush/android/data/k;
    //   102: invokevirtual 204	cn/jpush/android/data/k:a	()J
    //   105: aload_2
    //   106: invokevirtual 260	cn/jpush/android/data/JPushLocalNotification:getNotificationId	()J
    //   109: lcmp
    //   110: ifeq +69 -> 179
    //   113: aload_1
    //   114: astore 7
    //   116: getstatic 78	cn/jpush/android/service/g:f	Lcn/jpush/android/data/i;
    //   119: aload_2
    //   120: invokevirtual 260	cn/jpush/android/data/JPushLocalNotification:getNotificationId	()J
    //   123: iconst_1
    //   124: iconst_0
    //   125: iconst_0
    //   126: aload_2
    //   127: invokevirtual 263	cn/jpush/android/data/JPushLocalNotification:toJSON	()Ljava/lang/String;
    //   130: aload_2
    //   131: invokevirtual 251	cn/jpush/android/data/JPushLocalNotification:getBroadcastTime	()J
    //   134: lload_3
    //   135: invokevirtual 266	cn/jpush/android/data/i:a	(JIIILjava/lang/String;JJ)J
    //   138: pop2
    //   139: aload_1
    //   140: astore 7
    //   142: getstatic 78	cn/jpush/android/service/g:f	Lcn/jpush/android/data/i;
    //   145: invokevirtual 216	cn/jpush/android/data/i:b	()V
    //   148: aload_1
    //   149: ifnull +9 -> 158
    //   152: aload_1
    //   153: invokeinterface 219 1 0
    //   158: lload 5
    //   160: ldc2_w 267
    //   163: lcmp
    //   164: ifge +80 -> 244
    //   167: aload_0
    //   168: aload_2
    //   169: invokevirtual 260	cn/jpush/android/data/JPushLocalNotification:getNotificationId	()J
    //   172: lload 5
    //   174: invokespecial 270	cn/jpush/android/service/g:a	(JJ)V
    //   177: iconst_1
    //   178: ireturn
    //   179: aload_1
    //   180: astore 7
    //   182: getstatic 78	cn/jpush/android/service/g:f	Lcn/jpush/android/data/i;
    //   185: aload_2
    //   186: invokevirtual 260	cn/jpush/android/data/JPushLocalNotification:getNotificationId	()J
    //   189: iconst_1
    //   190: iconst_0
    //   191: iconst_0
    //   192: aload_2
    //   193: invokevirtual 263	cn/jpush/android/data/JPushLocalNotification:toJSON	()Ljava/lang/String;
    //   196: aload_2
    //   197: invokevirtual 251	cn/jpush/android/data/JPushLocalNotification:getBroadcastTime	()J
    //   200: lload_3
    //   201: invokevirtual 211	cn/jpush/android/data/i:b	(JIIILjava/lang/String;JJ)Z
    //   204: pop
    //   205: goto -66 -> 139
    //   208: astore_1
    //   209: aload 7
    //   211: ifnull -53 -> 158
    //   214: aload 7
    //   216: invokeinterface 219 1 0
    //   221: goto -63 -> 158
    //   224: astore_1
    //   225: aconst_null
    //   226: astore 7
    //   228: aload_1
    //   229: astore_2
    //   230: aload 7
    //   232: ifnull +10 -> 242
    //   235: aload 7
    //   237: invokeinterface 219 1 0
    //   242: aload_2
    //   243: athrow
    //   244: iconst_1
    //   245: ireturn
    //   246: astore_2
    //   247: aload_1
    //   248: astore 7
    //   250: goto -20 -> 230
    //
    // Exception table:
    //   from	to	target	type
    //   57	63	208	java/lang/Exception
    //   66	78	208	java/lang/Exception
    //   81	86	208	java/lang/Exception
    //   89	96	208	java/lang/Exception
    //   99	113	208	java/lang/Exception
    //   116	139	208	java/lang/Exception
    //   142	148	208	java/lang/Exception
    //   182	205	208	java/lang/Exception
    //   57	63	224	finally
    //   66	78	224	finally
    //   81	86	246	finally
    //   89	96	246	finally
    //   99	113	246	finally
    //   116	139	246	finally
    //   142	148	246	finally
    //   182	205	246	finally
  }

  // ERROR //
  public final void c(Context paramContext)
  {
    // Byte code:
    //   0: invokestatic 99	cn/jpush/android/util/x:c	()V
    //   3: getstatic 78	cn/jpush/android/service/g:f	Lcn/jpush/android/data/i;
    //   6: ifnonnull +14 -> 20
    //   9: new 177	cn/jpush/android/data/i
    //   12: dup
    //   13: aload_1
    //   14: invokespecial 178	cn/jpush/android/data/i:<init>	(Landroid/content/Context;)V
    //   17: putstatic 78	cn/jpush/android/service/g:f	Lcn/jpush/android/data/i;
    //   20: aconst_null
    //   21: astore_1
    //   22: aload_1
    //   23: astore 4
    //   25: getstatic 78	cn/jpush/android/service/g:f	Lcn/jpush/android/data/i;
    //   28: invokevirtual 180	cn/jpush/android/data/i:a	()V
    //   31: aload_1
    //   32: astore 4
    //   34: invokestatic 186	java/lang/System:currentTimeMillis	()J
    //   37: lstore_2
    //   38: aload_1
    //   39: astore 4
    //   41: getstatic 78	cn/jpush/android/service/g:f	Lcn/jpush/android/data/i;
    //   44: lload_2
    //   45: ldc2_w 267
    //   48: invokevirtual 273	cn/jpush/android/data/i:a	(JJ)Landroid/database/Cursor;
    //   51: astore_1
    //   52: aload_1
    //   53: astore 4
    //   55: aload_1
    //   56: invokeinterface 195 1 0
    //   61: ifeq +54 -> 115
    //   64: aload_1
    //   65: astore 4
    //   67: getstatic 78	cn/jpush/android/service/g:f	Lcn/jpush/android/data/i;
    //   70: astore 5
    //   72: aload_1
    //   73: astore 4
    //   75: aload_1
    //   76: getstatic 84	cn/jpush/android/service/g:g	Lcn/jpush/android/data/k;
    //   79: invokestatic 198	cn/jpush/android/data/i:a	(Landroid/database/Cursor;Lcn/jpush/android/data/k;)V
    //   82: aload_1
    //   83: astore 4
    //   85: aload_0
    //   86: getstatic 84	cn/jpush/android/service/g:g	Lcn/jpush/android/data/k;
    //   89: invokevirtual 204	cn/jpush/android/data/k:a	()J
    //   92: getstatic 84	cn/jpush/android/service/g:g	Lcn/jpush/android/data/k;
    //   95: invokevirtual 206	cn/jpush/android/data/k:f	()J
    //   98: lload_2
    //   99: lsub
    //   100: invokespecial 270	cn/jpush/android/service/g:a	(JJ)V
    //   103: aload_1
    //   104: astore 4
    //   106: aload_1
    //   107: invokeinterface 214 1 0
    //   112: ifne -48 -> 64
    //   115: aload_1
    //   116: astore 4
    //   118: getstatic 78	cn/jpush/android/service/g:f	Lcn/jpush/android/data/i;
    //   121: invokevirtual 216	cn/jpush/android/data/i:b	()V
    //   124: aload_1
    //   125: ifnull +84 -> 209
    //   128: aload_1
    //   129: invokeinterface 219 1 0
    //   134: return
    //   135: astore_1
    //   136: aload 4
    //   138: ifnull +71 -> 209
    //   141: aload 4
    //   143: invokeinterface 219 1 0
    //   148: return
    //   149: astore_1
    //   150: new 135	java/lang/StringBuilder
    //   153: dup
    //   154: getstatic 66	cn/jpush/android/service/g:z	[Ljava/lang/String;
    //   157: bipush 8
    //   159: aaload
    //   160: invokespecial 138	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   163: aload_1
    //   164: invokevirtual 276	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   167: invokevirtual 145	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   170: pop
    //   171: invokestatic 99	cn/jpush/android/util/x:c	()V
    //   174: aload_1
    //   175: invokevirtual 228	java/lang/Exception:printStackTrace	()V
    //   178: return
    //   179: astore_1
    //   180: aconst_null
    //   181: astore 5
    //   183: aload_1
    //   184: astore 4
    //   186: aload 5
    //   188: ifnull +10 -> 198
    //   191: aload 5
    //   193: invokeinterface 219 1 0
    //   198: aload 4
    //   200: athrow
    //   201: astore 4
    //   203: aload_1
    //   204: astore 5
    //   206: goto -20 -> 186
    //   209: return
    //
    // Exception table:
    //   from	to	target	type
    //   25	31	135	java/lang/Exception
    //   34	38	135	java/lang/Exception
    //   41	52	135	java/lang/Exception
    //   55	64	135	java/lang/Exception
    //   67	72	135	java/lang/Exception
    //   75	82	135	java/lang/Exception
    //   85	103	135	java/lang/Exception
    //   106	115	135	java/lang/Exception
    //   118	124	135	java/lang/Exception
    //   3	20	149	java/lang/Exception
    //   128	134	149	java/lang/Exception
    //   141	148	149	java/lang/Exception
    //   191	198	149	java/lang/Exception
    //   198	201	149	java/lang/Exception
    //   25	31	179	finally
    //   34	38	179	finally
    //   41	52	179	finally
    //   55	64	201	finally
    //   67	72	201	finally
    //   75	82	201	finally
    //   85	103	201	finally
    //   106	115	201	finally
    //   118	124	201	finally
  }

  public final void d(Context paramContext)
  {
    x.c();
    b.execute(new h(this, paramContext));
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.service.g
 * JD-Core Version:    0.6.2
 */