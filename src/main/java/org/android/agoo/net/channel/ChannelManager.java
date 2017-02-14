package org.android.agoo.net.channel;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.message.proguard.bD;
import com.umeng.message.proguard.bF;
import com.umeng.message.proguard.bd;
import com.umeng.message.proguard.bm;
import com.umeng.message.proguard.bn;
import com.umeng.message.proguard.bo;
import com.umeng.message.proguard.bv;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.android.agoo.net.async.RequestParams;
import org.android.agoo.net.channel.chunked.HttpURLChunkedChannel;
import org.android.agoo.net.channel.spdy.SpdyChannel;

public class ChannelManager
{
  private static final int I = -1;
  public static final int a = 307;
  public static final int b = 401;
  public static final int c = 403;
  public static final int d = 404;
  public static final int e = 408;
  private static final String f = "ChannelManager";
  private static final String g = "AGOO_CONNECT";
  private static final String h = "AGOO_CONNECT_COUNT";
  private static final String i = "AGOO_CONNECT_CLIENT_CREATE_TIME";
  private static final String j = "AGOO_CONNECT_LAST_RECONNECT_TIME";
  private static volatile Map<String, String> k;
  private static volatile long l = 30000L;
  private static volatile Map<String, String> m;
  private static volatile ChannelType n = ChannelType.b;
  private static volatile AndroidEvent o = AndroidEvent.a;
  private static volatile Context p = null;
  private static volatile IPushHandler q = null;
  private static volatile String r = null;
  private static volatile String s = null;
  private static volatile String t = null;
  private static volatile String u;
  private static volatile boolean v = true;
  private static volatile DNSManager w = null;
  private static volatile VoteResult x = VoteResult.a;
  private static volatile boolean y = false;
  private volatile int A = -1;
  private volatile IDataChannel B = null;
  private volatile short C;
  private volatile long D = -1L;
  private volatile long E = -1L;
  private final DNSManager.IHostHandler F = new DNSManager.IHostHandler()
  {
    public void onFailure(ChannelError paramAnonymousChannelError, String paramAnonymousString)
    {
      ChannelManager.a(ChannelManager.this, false);
      ChannelManager.a(ChannelManager.this, paramAnonymousChannelError, paramAnonymousString);
    }

    public void onHost(ChannelType paramAnonymousChannelType, String paramAnonymousString1, int paramAnonymousInt, bm paramAnonymousbm, String paramAnonymousString2)
    {
      try
      {
        if ((!TextUtils.isEmpty(paramAnonymousString1)) && (paramAnonymousInt > 0))
        {
          ChannelManager.a(false);
          ChannelManager.a(ChannelManager.this, paramAnonymousString1);
          ChannelManager.a(ChannelManager.this, paramAnonymousInt);
          ChannelManager.a(paramAnonymousChannelType);
        }
        ChannelManager.this.connenct(paramAnonymousbm, paramAnonymousString2);
        ChannelManager.a(ChannelManager.this, false);
        return;
      }
      catch (Throwable paramAnonymousChannelType)
      {
        while (true)
          ChannelManager.a(ChannelManager.this, ChannelError.y, "host [" + paramAnonymousString1 + "] failed");
      }
    }

    public void onReportDNS(bn paramAnonymousbn)
    {
      ChannelManager.a(ChannelManager.this, paramAnonymousbn);
    }
  };
  private volatile boolean G = false;
  private volatile Object H = null;
  private volatile String z = null;

  private ChannelManager()
  {
    w.a(this.F);
  }

  private final String a(String paramString1, Map<String, String> paramMap, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    RequestParams localRequestParams;
    try
    {
      localRequestParams = new RequestParams();
      if (paramMap == null)
        break label130;
      paramMap = paramMap.entrySet().iterator();
      if (paramMap == null)
        break label130;
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        if ((localEntry != null) && (!bF.a((String)localEntry.getKey())) && (!bF.a((String)localEntry.getValue())))
          localRequestParams.put((String)localEntry.getKey(), (String)localEntry.getValue());
      }
    }
    catch (Throwable paramString1)
    {
    }
    return localStringBuilder.toString();
    label130: localStringBuilder.append(paramString1);
    paramMap = localRequestParams.getParamString();
    if (TextUtils.indexOf(paramString1, "?") == -1)
      localStringBuilder.append("?");
    while (true)
    {
      localStringBuilder.append("s=" + paramString2);
      if (TextUtils.isEmpty(paramMap))
        break;
      localStringBuilder.append("&");
      localStringBuilder.append(paramMap);
      break;
      localStringBuilder.append("&");
    }
  }

  // ERROR //
  private final void a(bm parambm)
  {
    // Byte code:
    //   0: getstatic 97	org/android/agoo/net/channel/ChannelManager:p	Landroid/content/Context;
    //   3: ldc 30
    //   5: iconst_4
    //   6: invokevirtual 248	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   9: astore 5
    //   11: invokestatic 254	java/lang/System:currentTimeMillis	()J
    //   14: lstore_2
    //   15: aload_0
    //   16: aload 5
    //   18: ldc 36
    //   20: lload_2
    //   21: invokeinterface 260 4 0
    //   26: putfield 133	org/android/agoo/net/channel/ChannelManager:E	J
    //   29: aload_0
    //   30: aload 5
    //   32: ldc 39
    //   34: lload_2
    //   35: invokeinterface 260 4 0
    //   40: putfield 131	org/android/agoo/net/channel/ChannelManager:D	J
    //   43: aload 5
    //   45: invokeinterface 264 1 0
    //   50: astore 4
    //   52: aload_0
    //   53: getfield 133	org/android/agoo/net/channel/ChannelManager:E	J
    //   56: lload_2
    //   57: lcmp
    //   58: ifne +14 -> 72
    //   61: aload 4
    //   63: ldc 36
    //   65: lload_2
    //   66: invokeinterface 270 4 0
    //   71: pop
    //   72: aload_0
    //   73: aload 5
    //   75: ldc 33
    //   77: ldc_w 272
    //   80: invokeinterface 276 3 0
    //   85: invokestatic 282	java/lang/Short:parseShort	(Ljava/lang/String;)S
    //   88: putfield 284	org/android/agoo/net/channel/ChannelManager:C	S
    //   91: ldc 27
    //   93: new 159	java/lang/StringBuilder
    //   96: dup
    //   97: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   100: ldc_w 286
    //   103: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: lload_2
    //   107: invokevirtual 289	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   110: ldc_w 291
    //   113: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   116: aload_0
    //   117: getfield 133	org/android/agoo/net/channel/ChannelManager:E	J
    //   120: invokevirtual 289	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   123: ldc_w 293
    //   126: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   129: aload_0
    //   130: getfield 131	org/android/agoo/net/channel/ChannelManager:D	J
    //   133: invokevirtual 289	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   136: ldc_w 295
    //   139: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   145: invokestatic 299	com/umeng/message/proguard/bd:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   148: aload_0
    //   149: aload_0
    //   150: getfield 284	org/android/agoo/net/channel/ChannelManager:C	S
    //   153: iconst_1
    //   154: iadd
    //   155: i2s
    //   156: putfield 284	org/android/agoo/net/channel/ChannelManager:C	S
    //   159: aload 4
    //   161: ldc 39
    //   163: lload_2
    //   164: invokeinterface 270 4 0
    //   169: pop
    //   170: aload 4
    //   172: ldc 33
    //   174: new 159	java/lang/StringBuilder
    //   177: dup
    //   178: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   181: ldc_w 301
    //   184: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   187: aload_0
    //   188: getfield 284	org/android/agoo/net/channel/ChannelManager:C	S
    //   191: invokevirtual 304	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   194: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   197: invokeinterface 308 3 0
    //   202: pop
    //   203: aload 4
    //   205: invokeinterface 311 1 0
    //   210: pop
    //   211: aload_1
    //   212: aload_0
    //   213: getfield 284	org/android/agoo/net/channel/ChannelManager:C	S
    //   216: invokestatic 316	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   219: invokevirtual 321	com/umeng/message/proguard/bm:m	(Ljava/lang/String;)V
    //   222: return
    //   223: astore_1
    //   224: return
    //   225: astore 5
    //   227: goto -136 -> 91
    //
    // Exception table:
    //   from	to	target	type
    //   0	72	223	java/lang/Throwable
    //   91	222	223	java/lang/Throwable
    //   72	91	225	java/lang/Throwable
  }

  private final void a(bm parambm, String paramString)
  {
    Object localObject2;
    HashMap localHashMap;
    Object localObject3;
    try
    {
      g();
      if (this.B == null)
      {
        bd.c("ChannelManager", "dataChannel==null");
        return;
      }
      a(parambm);
      localObject2 = SignHelper.generatorClient(p, n.getValue(), this.C, this.D, this.E, o.getValue(), x.getValue());
      localHashMap = new HashMap();
      localHashMap.putAll(k);
      localObject1 = new HashMap();
      m.put("ov", Build.VERSION.RELEASE);
      localObject3 = bD.g(p);
      m.put("sv", ((LinkedHashMap)localObject3).get("agooReleaseTime"));
      m.put("pm", bv.a(Build.MODEL.getBytes()));
      ((Map)localObject1).putAll(m);
      if (!TextUtils.isEmpty((CharSequence)localObject2))
        ((Map)localObject1).put("c", localObject2);
      int i1 = h();
      localObject2 = String.format("http://%s:%d/%s/%s/%d/%s", new Object[] { this.z, Integer.valueOf(this.A), s, t, Integer.valueOf(i1), r });
      localObject3 = SignHelper.generatorSign(p, (String)localObject2, (Map)localObject1, t, u);
      if (TextUtils.isEmpty((CharSequence)localObject3))
      {
        parambm.f(ChannelError.v.toString());
        a(ChannelError.v, "");
        return;
      }
    }
    catch (Throwable parambm)
    {
      bd.d("ChannelManager", "_connenct", parambm);
      return;
    }
    Object localObject1 = a((String)localObject2, (Map)localObject1, (String)localObject3);
    Log.d("ChannelManager", "mCurrentChannelType = " + n);
    switch (2.a[n.ordinal()])
    {
    case 1:
    case 2:
    }
    while (true)
    {
      bd.c("ChannelManager", "connenct  [SpdyChannel]");
      while (true)
      {
        localObject2 = p.getSharedPreferences("AGOO_CONNECT", 4).edit();
        ((SharedPreferences.Editor)localObject2).putString("AGOO_CONNECT_HOST", this.z);
        ((SharedPreferences.Editor)localObject2).putInt("AGOO_CONNECT_PORT", this.A);
        ((SharedPreferences.Editor)localObject2).commit();
        this.B.connect(this.H, p, (String)localObject1, localHashMap, l, q, parambm, paramString);
        return;
        bd.c("ChannelManager", "connenct  [SpdyChannel]");
        continue;
        bd.c("ChannelManager", "connenct  [ChunkedChannel]");
      }
    }
  }

  private final void a(bn parambn)
  {
    try
    {
      if (q != null)
        q.onReportDNS(parambn);
      return;
    }
    catch (Throwable parambn)
    {
    }
  }

  private final void a(ChannelError paramChannelError, String paramString)
  {
    try
    {
      if (q != null)
        q.onError(this.H, -1L, paramChannelError, null, null, null);
      return;
    }
    catch (Throwable paramChannelError)
    {
    }
  }

  private final void g()
  {
    switch (2.a[n.ordinal()])
    {
    default:
    case 1:
    case 2:
    }
    while (true)
    {
      return;
      try
      {
        if ((this.B != null) && (y) && ((this.B instanceof SpdyChannel)))
        {
          this.B.close();
          this.B = new SpdyChannel();
          y = false;
        }
        if ((this.B != null) && ((this.B instanceof SpdyChannel)))
          continue;
        this.B = new SpdyChannel();
        return;
      }
      catch (Throwable localThrowable1)
      {
        bd.d("ChannelManager", "builder.changeChannel.initSpdy", localThrowable1);
        a(ChannelError.f, "builder.changeChannel.initSpdy error");
        return;
      }
      try
      {
        if ((this.B == null) || (!(this.B instanceof HttpURLChunkedChannel)))
        {
          this.B = new HttpURLChunkedChannel();
          return;
        }
      }
      catch (Throwable localThrowable2)
      {
        a(ChannelError.n, "builder.changeChannel.initSpdy error");
        bd.d("ChannelManager", "builder.changeChannel.initChunked", localThrowable2);
      }
    }
  }

  private static final int h()
  {
    try
    {
      int i1 = p.getPackageManager().getPackageInfo(p.getPackageName(), 0).versionCode;
      return i1;
    }
    catch (Throwable localThrowable)
    {
      bd.d("ChannelManager", "getAppVersionCode", localThrowable);
    }
    return -1;
  }

  // ERROR //
  public final int ACK(String paramString1, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 127	org/android/agoo/net/channel/ChannelManager:B	Lorg/android/agoo/net/channel/IDataChannel;
    //   4: ifnull +456 -> 460
    //   7: aload_0
    //   8: getfield 127	org/android/agoo/net/channel/ChannelManager:B	Lorg/android/agoo/net/channel/IDataChannel;
    //   11: invokeinterface 526 1 0
    //   16: getstatic 531	org/android/agoo/net/channel/ChannelState:b	Lorg/android/agoo/net/channel/ChannelState;
    //   19: if_acmpne +441 -> 460
    //   22: new 343	java/util/HashMap
    //   25: dup
    //   26: invokespecial 344	java/util/HashMap:<init>	()V
    //   29: astore 6
    //   31: aload_1
    //   32: invokestatic 228	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   35: ifeq +19 -> 54
    //   38: aload_2
    //   39: invokestatic 228	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   42: ifeq +12 -> 54
    //   45: aload_3
    //   46: invokestatic 228	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   49: ifeq +5 -> 54
    //   52: iconst_m1
    //   53: ireturn
    //   54: aload 6
    //   56: getstatic 350	org/android/agoo/net/channel/ChannelManager:m	Ljava/util/Map;
    //   59: invokeinterface 348 2 0
    //   64: aload 6
    //   66: ldc_w 533
    //   69: aload_1
    //   70: invokeinterface 360 3 0
    //   75: pop
    //   76: aload_2
    //   77: invokestatic 228	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   80: ifne +15 -> 95
    //   83: aload 6
    //   85: ldc_w 535
    //   88: aload_2
    //   89: invokeinterface 360 3 0
    //   94: pop
    //   95: aload_3
    //   96: invokestatic 228	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   99: ifne +15 -> 114
    //   102: aload 6
    //   104: ldc_w 537
    //   107: aload_3
    //   108: invokeinterface 360 3 0
    //   113: pop
    //   114: invokestatic 394	org/android/agoo/net/channel/ChannelManager:h	()I
    //   117: istore 4
    //   119: ldc_w 396
    //   122: bipush 6
    //   124: anewarray 4	java/lang/Object
    //   127: dup
    //   128: iconst_0
    //   129: aload_0
    //   130: getfield 123	org/android/agoo/net/channel/ChannelManager:z	Ljava/lang/String;
    //   133: aastore
    //   134: dup
    //   135: iconst_1
    //   136: aload_0
    //   137: getfield 125	org/android/agoo/net/channel/ChannelManager:A	I
    //   140: invokestatic 400	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   143: aastore
    //   144: dup
    //   145: iconst_2
    //   146: ldc_w 539
    //   149: aastore
    //   150: dup
    //   151: iconst_3
    //   152: getstatic 105	org/android/agoo/net/channel/ChannelManager:t	Ljava/lang/String;
    //   155: aastore
    //   156: dup
    //   157: iconst_4
    //   158: iload 4
    //   160: invokestatic 400	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   163: aastore
    //   164: dup
    //   165: iconst_5
    //   166: getstatic 101	org/android/agoo/net/channel/ChannelManager:r	Ljava/lang/String;
    //   169: aastore
    //   170: invokestatic 404	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   173: astore_1
    //   174: getstatic 97	org/android/agoo/net/channel/ChannelManager:p	Landroid/content/Context;
    //   177: aload_1
    //   178: aload 6
    //   180: getstatic 105	org/android/agoo/net/channel/ChannelManager:t	Ljava/lang/String;
    //   183: getstatic 406	org/android/agoo/net/channel/ChannelManager:u	Ljava/lang/String;
    //   186: invokestatic 410	org/android/agoo/net/channel/SignHelper:generatorSign	(Landroid/content/Context;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   189: astore_2
    //   190: aload_2
    //   191: invokestatic 228	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   194: ifeq +98 -> 292
    //   197: ldc 27
    //   199: ldc_w 541
    //   202: invokestatic 543	com/umeng/message/proguard/bd:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   205: aload_0
    //   206: getstatic 415	org/android/agoo/net/channel/ChannelError:v	Lorg/android/agoo/net/channel/ChannelError;
    //   209: ldc_w 301
    //   212: invokespecial 421	org/android/agoo/net/channel/ChannelManager:a	(Lorg/android/agoo/net/channel/ChannelError;Ljava/lang/String;)V
    //   215: iconst_m1
    //   216: ireturn
    //   217: astore_1
    //   218: iconst_m1
    //   219: istore 4
    //   221: aconst_null
    //   222: astore_2
    //   223: new 545	org/android/agoo/net/async/c
    //   226: dup
    //   227: getstatic 97	org/android/agoo/net/channel/ChannelManager:p	Landroid/content/Context;
    //   230: ldc_w 547
    //   233: invokespecial 550	org/android/agoo/net/async/c:<init>	(Landroid/content/Context;Ljava/lang/String;)V
    //   236: astore_3
    //   237: getstatic 97	org/android/agoo/net/channel/ChannelManager:p	Landroid/content/Context;
    //   240: invokestatic 365	com/umeng/message/proguard/bD:g	(Landroid/content/Context;)Ljava/util/LinkedHashMap;
    //   243: astore 6
    //   245: aload 6
    //   247: ldc_w 552
    //   250: iload 4
    //   252: invokestatic 316	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   255: invokevirtual 553	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   258: pop
    //   259: aload_3
    //   260: aload 6
    //   262: invokevirtual 556	org/android/agoo/net/async/c:a	(Ljava/util/LinkedHashMap;)V
    //   265: aload_0
    //   266: getfield 127	org/android/agoo/net/channel/ChannelManager:B	Lorg/android/agoo/net/channel/IDataChannel;
    //   269: aload_2
    //   270: aconst_null
    //   271: aconst_null
    //   272: aconst_null
    //   273: invokeinterface 560 5 0
    //   278: istore 4
    //   280: ldc 27
    //   282: ldc_w 562
    //   285: aload_1
    //   286: invokestatic 426	com/umeng/message/proguard/bd:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   289: iload 4
    //   291: ireturn
    //   292: new 564	java/net/URI
    //   295: dup
    //   296: aload_0
    //   297: aload_1
    //   298: aload 6
    //   300: aload_2
    //   301: invokespecial 428	org/android/agoo/net/channel/ChannelManager:a	(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;)Ljava/lang/String;
    //   304: invokespecial 566	java/net/URI:<init>	(Ljava/lang/String;)V
    //   307: astore_1
    //   308: new 159	java/lang/StringBuilder
    //   311: dup
    //   312: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   315: aload_1
    //   316: invokevirtual 569	java/net/URI:getPath	()Ljava/lang/String;
    //   319: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   322: ldc 216
    //   324: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   327: aload_1
    //   328: invokevirtual 572	java/net/URI:getQuery	()Ljava/lang/String;
    //   331: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   334: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   337: astore_1
    //   338: ldc 27
    //   340: new 159	java/lang/StringBuilder
    //   343: dup
    //   344: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   347: ldc_w 574
    //   350: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   353: aload_1
    //   354: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   357: ldc_w 295
    //   360: invokevirtual 211	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   363: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   366: invokestatic 576	com/umeng/message/proguard/bd:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   369: aload_0
    //   370: getfield 127	org/android/agoo/net/channel/ChannelManager:B	Lorg/android/agoo/net/channel/IDataChannel;
    //   373: aload_1
    //   374: aconst_null
    //   375: aconst_null
    //   376: aconst_null
    //   377: invokeinterface 560 5 0
    //   382: istore 4
    //   384: iload 4
    //   386: istore 5
    //   388: iload 4
    //   390: ifeq +73 -> 463
    //   393: iload 4
    //   395: istore 5
    //   397: iload 4
    //   399: iconst_m1
    //   400: if_icmpeq +63 -> 463
    //   403: new 545	org/android/agoo/net/async/c
    //   406: dup
    //   407: getstatic 97	org/android/agoo/net/channel/ChannelManager:p	Landroid/content/Context;
    //   410: ldc_w 547
    //   413: invokespecial 550	org/android/agoo/net/async/c:<init>	(Landroid/content/Context;Ljava/lang/String;)V
    //   416: astore_2
    //   417: getstatic 97	org/android/agoo/net/channel/ChannelManager:p	Landroid/content/Context;
    //   420: invokestatic 365	com/umeng/message/proguard/bD:g	(Landroid/content/Context;)Ljava/util/LinkedHashMap;
    //   423: astore_3
    //   424: aload_3
    //   425: ldc_w 552
    //   428: iload 4
    //   430: invokestatic 316	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   433: invokevirtual 553	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   436: pop
    //   437: aload_2
    //   438: aload_3
    //   439: invokevirtual 556	org/android/agoo/net/async/c:a	(Ljava/util/LinkedHashMap;)V
    //   442: aload_0
    //   443: getfield 127	org/android/agoo/net/channel/ChannelManager:B	Lorg/android/agoo/net/channel/IDataChannel;
    //   446: aload_1
    //   447: aconst_null
    //   448: aconst_null
    //   449: aconst_null
    //   450: invokeinterface 560 5 0
    //   455: istore 5
    //   457: iload 5
    //   459: ireturn
    //   460: iconst_m1
    //   461: istore 5
    //   463: iload 5
    //   465: ireturn
    //   466: astore_3
    //   467: iconst_m1
    //   468: istore 4
    //   470: aload_1
    //   471: astore_2
    //   472: aload_3
    //   473: astore_1
    //   474: goto -251 -> 223
    //   477: astore_3
    //   478: aload_1
    //   479: astore_2
    //   480: aload_3
    //   481: astore_1
    //   482: goto -259 -> 223
    //
    // Exception table:
    //   from	to	target	type
    //   0	52	217	java/lang/Throwable
    //   54	95	217	java/lang/Throwable
    //   95	114	217	java/lang/Throwable
    //   114	215	217	java/lang/Throwable
    //   292	338	217	java/lang/Throwable
    //   338	384	466	java/lang/Throwable
    //   403	457	477	java/lang/Throwable
  }

  public final void connenct(bm parambm, String paramString)
  {
    try
    {
      if ((v) || (this.z == null) || (-1 == this.A))
      {
        if (this.G)
        {
          bd.c("ChannelManager", "connenct[dnsing]");
          return;
        }
        this.G = true;
        w.a(n, parambm);
        return;
      }
    }
    catch (Throwable parambm)
    {
      bd.d("ChannelManager", "connenct", parambm);
      return;
    }
    if (readyChannelState() == ChannelState.a)
    {
      bd.c("ChannelManager", "connenct[connecting]");
      return;
    }
    a(parambm, paramString);
  }

  public final void disconnect()
  {
    try
    {
      if ((this.B != null) && (readyChannelState() == ChannelState.b))
        this.B.asyncDisconnect();
      return;
    }
    catch (Throwable localThrowable)
    {
      bd.d("ChannelManager", "disconnect", localThrowable);
    }
  }

  public final bo hisMessage(bo parambo)
  {
    try
    {
      if ((this.B != null) && (this.B.readyChannelState() == ChannelState.b))
      {
        Object localObject = new HashMap();
        ((Map)localObject).putAll(m);
        int i1 = h();
        String str1 = String.format("http://%s:%d/%s/%s/%d/%s", new Object[] { this.z, Integer.valueOf(this.A), "h", t, Integer.valueOf(i1), r });
        String str2 = SignHelper.generatorSign(p, str1, (Map)localObject, t, u);
        if (TextUtils.isEmpty(str2))
        {
          parambo.f("[sgin==null]");
          bd.d("ChannelManager", "hisMessage[sgin==null]");
          a(ChannelError.v, "");
          return parambo;
        }
        localObject = new URI(a(str1, (Map)localObject, str2));
        localObject = ((URI)localObject).getPath() + "?" + ((URI)localObject).getQuery();
        bd.b("ChannelManager", "hisMessage url [" + (String)localObject + "]");
        i1 = this.B.send((String)localObject, null, null, parambo);
        if (i1 == -1)
        {
          parambo.f(Integer.toString(i1));
          parambo.e("n");
        }
        parambo.e("y");
        return parambo;
      }
    }
    catch (Throwable localThrowable)
    {
      parambo.f(localThrowable.toString());
      parambo.e("n");
      bd.d("ChannelManager", "send", localThrowable);
    }
    return parambo;
  }

  public final long ping()
  {
    long l1 = -1L;
    try
    {
      if (this.B != null)
        l1 = this.B.ping();
      return l1;
    }
    catch (Throwable localThrowable)
    {
      bd.d("ChannelManager", "ping", localThrowable);
    }
    return -1L;
  }

  public final ChannelState readyChannelState()
  {
    try
    {
      if (this.B != null)
      {
        ChannelState localChannelState = this.B.readyChannelState();
        return localChannelState;
      }
    }
    catch (Throwable localThrowable)
    {
      bd.d("ChannelManager", "readyChannelState", localThrowable);
    }
    return ChannelState.d;
  }

  public final ChannelType readyChannelType()
  {
    return n;
  }

  public final void send(String paramString, byte[] paramArrayOfByte, IPullHandler paramIPullHandler)
  {
    try
    {
      if (this.B != null)
        this.B.send(paramString, paramArrayOfByte, paramIPullHandler, null);
      return;
    }
    catch (Throwable paramString)
    {
      bd.d("ChannelManager", "send", paramString);
    }
  }

  public final void setConnectContext(Object paramObject)
  {
    this.H = paramObject;
  }

  public final void shutdown()
  {
    try
    {
      if (this.B != null)
        this.B.shutdown();
      return;
    }
    catch (Throwable localThrowable)
    {
      bd.d("ChannelManager", "shutdown", localThrowable);
    }
  }

  public static final class Builder
  {
    public Builder(Context paramContext, String paramString1, String paramString2)
    {
      ChannelManager.a(paramContext);
      ChannelManager.a(new DNSManager(paramContext));
      ChannelManager.a(new HashMap());
      ChannelManager.b(new HashMap());
      ChannelManager.a(true);
      ChannelManager.a(paramString1);
      ChannelManager.b(paramString2);
    }

    public final Builder addHeader(String paramString1, String paramString2)
    {
      ChannelManager.f().put(paramString1, paramString2);
      return this;
    }

    public final Builder addHeaders(Map<String, String> paramMap)
    {
      ChannelManager.f().putAll(paramMap);
      return this;
    }

    public final Builder addLastTcpConnectedEndTime(long paramLong)
    {
      return this;
    }

    public final Builder addLastTcpConnectedSuccessfully(long paramLong1, long paramLong2)
    {
      return this;
    }

    public final Builder addPushHandler(IPushHandler paramIPushHandler)
    {
      ChannelManager.a(paramIPushHandler);
      return this;
    }

    public final Builder addServiceStartTime(long paramLong)
    {
      return this;
    }

    public final ChannelManager build()
    {
      return new ChannelManager(null);
    }

    public final Builder forceInit()
    {
      ChannelManager.b(true);
      return this;
    }

    public final Builder putParams(String paramString1, String paramString2)
    {
      ChannelManager.d().put(paramString1, paramString2);
      return this;
    }

    public final Builder putParams(Map<String, String> paramMap)
    {
      ChannelManager.d().putAll(paramMap);
      return this;
    }

    public final Builder refreshHost()
    {
      ChannelManager.a(true);
      return this;
    }

    public final Builder removeHeader(String paramString)
    {
      ChannelManager.f().remove(paramString);
      return this;
    }

    public final Builder setApi(String paramString)
    {
      ChannelManager.c(paramString);
      return this;
    }

    public final Builder setAppSecret(String paramString)
    {
      ChannelManager.d(paramString);
      return this;
    }

    public final Builder setChannel(ChannelType paramChannelType)
    {
      ChannelManager.a(paramChannelType);
      ChannelManager.c().a(paramChannelType);
      return this;
    }

    public final Builder setDNS(String paramString, long paramLong)
    {
      ChannelManager.c().a(paramString, ChannelManager.a(), ChannelManager.b(), paramLong);
      return this;
    }

    public final Builder setDNSProxy(String paramString, int paramInt)
    {
      ChannelManager.c().a(paramString, paramInt);
      return this;
    }

    public final Builder setIfNeedMore(boolean paramBoolean)
    {
      if (paramBoolean)
      {
        ChannelManager.d().put("c0", Build.BRAND);
        ChannelManager.d().put("c1", Build.MODEL);
        ChannelManager.d().put("c2", bD.d(ChannelManager.e()));
        ChannelManager.d().put("c3", bD.e(ChannelManager.e()));
        ChannelManager.d().put("c4", bD.c(ChannelManager.e()));
        ChannelManager.d().put("c5", bD.a());
        ChannelManager.d().put("c6", bD.f(ChannelManager.e()));
      }
      return this;
    }

    public final Builder setRequestInfo(String paramString1, String paramString2, String paramString3)
    {
      ChannelManager.c().a(paramString1, paramString2, paramString3);
      return this;
    }

    public final Builder setTimeout(long paramLong)
    {
      ChannelManager.a(paramLong);
      return this;
    }

    public final Builder setVote(VoteResult paramVoteResult)
    {
      ChannelManager.a(paramVoteResult);
      return this;
    }

    public final Builder setlastEventType(AndroidEvent paramAndroidEvent)
    {
      ChannelManager.a(paramAndroidEvent);
      return this;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.channel.ChannelManager
 * JD-Core Version:    0.6.2
 */