package org.android.agoo.net.channel;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.umeng.message.proguard.bD;
import com.umeng.message.proguard.bE;
import com.umeng.message.proguard.bd;
import com.umeng.message.proguard.bl;
import com.umeng.message.proguard.bm;
import com.umeng.message.proguard.bn;
import com.umeng.message.proguard.bw;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.android.agoo.net.async.RequestParams;
import org.android.agoo.net.async.SyncHttpClient;
import org.android.agoo.net.async.SyncHttpClient.a;
import org.android.agoo.net.async.c;
import org.apache.http.HttpHost;

class DNSManager
{
  public static final String a = "agoo_dns_errorid";
  public static final String b = "agoo_dns_path";
  public static final String c = "agoo_dns_eventid";
  private static final String d = "DNSManager";
  private static final String e = "AGOO_HOST";
  private static final String f = "AGOO_HOST_SIZE";
  private static final String g = "AGOO_HOST_TYPE";
  private static final String h = "AGOO_HOST_VALUE_";
  private static final String i = "spdy";
  private static final String j = "off";
  private volatile double A = 0.0D;
  private volatile double B = 0.0D;
  private volatile bm C = null;
  private volatile SyncHttpClient D = null;
  private volatile String k;
  private volatile String l;
  private volatile String m;
  private volatile String n;
  private volatile String o;
  private volatile String p;
  private volatile String q;
  private volatile int r = -1;
  private volatile long s = -1L;
  private volatile IHostHandler t = null;
  private volatile ChannelType u = ChannelType.b;
  private volatile ChannelType v = ChannelType.b;
  private volatile Context w = null;
  private volatile int x = 0;
  private volatile boolean y = false;
  private volatile a z = null;

  DNSManager(Context paramContext)
  {
    this.w = paramContext;
    this.D = new SyncHttpClient();
    this.z = new a();
    this.u = ChannelType.b;
    this.v = ChannelType.b;
  }

  private final String a()
  {
    String str = bD.b(this.w);
    if (!TextUtils.isEmpty(str))
    {
      if ((str.startsWith("46000")) || (str.startsWith("46002")))
        return "china_mobile";
      if (str.startsWith("46001"))
        return "china_unicom";
      if (str.startsWith("46003"))
        return "china_telecom";
    }
    return null;
  }

  // ERROR //
  private LinkedHashMap<String, String> a(Context paramContext)
  {
    // Byte code:
    //   0: aload_1
    //   1: ldc 152
    //   3: iconst_4
    //   4: invokevirtual 158	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   7: astore 6
    //   9: aload 6
    //   11: ldc 160
    //   13: aconst_null
    //   14: invokeinterface 166 3 0
    //   19: astore 5
    //   21: aload 6
    //   23: ldc 168
    //   25: iconst_m1
    //   26: invokeinterface 172 3 0
    //   31: istore_2
    //   32: new 174	com/umeng/message/proguard/bl
    //   35: dup
    //   36: aload_1
    //   37: invokespecial 176	com/umeng/message/proguard/bl:<init>	(Landroid/content/Context;)V
    //   40: invokevirtual 178	com/umeng/message/proguard/bl:c	()Ljava/lang/String;
    //   43: astore 6
    //   45: aload_1
    //   46: ldc 180
    //   48: iconst_4
    //   49: invokevirtual 158	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   52: ldc 182
    //   54: lconst_0
    //   55: invokeinterface 186 4 0
    //   60: lstore_3
    //   61: new 188	java/util/LinkedHashMap
    //   64: dup
    //   65: invokespecial 189	java/util/LinkedHashMap:<init>	()V
    //   68: astore_1
    //   69: aload_1
    //   70: ldc 191
    //   72: aload 5
    //   74: invokevirtual 195	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   77: pop
    //   78: aload_1
    //   79: ldc 197
    //   81: iload_2
    //   82: invokestatic 203	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   85: invokevirtual 195	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   88: pop
    //   89: aload_1
    //   90: ldc 205
    //   92: aload 6
    //   94: invokevirtual 195	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   97: pop
    //   98: aload_1
    //   99: ldc 207
    //   101: aload_0
    //   102: getfield 209	org/android/agoo/net/channel/DNSManager:k	Ljava/lang/String;
    //   105: invokevirtual 195	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   108: pop
    //   109: aload_1
    //   110: ldc 211
    //   112: aload_0
    //   113: getfield 213	org/android/agoo/net/channel/DNSManager:l	Ljava/lang/String;
    //   116: invokevirtual 195	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   119: pop
    //   120: aload_1
    //   121: ldc 215
    //   123: lload_3
    //   124: invokestatic 220	java/lang/Long:toString	(J)Ljava/lang/String;
    //   127: invokevirtual 195	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   130: pop
    //   131: aload_1
    //   132: areturn
    //   133: astore 5
    //   135: aconst_null
    //   136: astore_1
    //   137: ldc 222
    //   139: new 224	java/lang/StringBuilder
    //   142: dup
    //   143: invokespecial 225	java/lang/StringBuilder:<init>	()V
    //   146: ldc 227
    //   148: invokevirtual 231	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: aload 5
    //   153: invokevirtual 234	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   156: invokevirtual 231	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   159: invokevirtual 236	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   162: invokestatic 241	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   165: pop
    //   166: aload_1
    //   167: areturn
    //   168: astore 5
    //   170: goto -33 -> 137
    //
    // Exception table:
    //   from	to	target	type
    //   0	69	133	java/lang/Throwable
    //   69	131	168	java/lang/Throwable
  }

  private final void a(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramContext = paramContext.getSharedPreferences("AppStore", 4).edit();
      paramContext.putString("agoo_dns_errorid", paramString1);
      paramContext.putString("agoo_dns_path", paramString2);
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  // ERROR //
  private final void a(ChannelType paramChannelType, String paramString1, String paramString2, bn parambn)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 98	org/android/agoo/net/channel/DNSManager:y	Z
    //   4: ifne +4 -> 8
    //   7: return
    //   8: aload_2
    //   9: invokestatic 127	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   12: ifeq +42 -> 54
    //   15: aload_0
    //   16: getstatic 268	org/android/agoo/net/channel/ChannelError:y	Lorg/android/agoo/net/channel/ChannelError;
    //   19: new 224	java/lang/StringBuilder
    //   22: dup
    //   23: invokespecial 225	java/lang/StringBuilder:<init>	()V
    //   26: ldc_w 270
    //   29: invokevirtual 231	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: aload_2
    //   33: invokevirtual 231	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: ldc_w 272
    //   39: invokevirtual 231	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: invokevirtual 236	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   45: invokevirtual 275	org/android/agoo/net/channel/DNSManager:a	(Lorg/android/agoo/net/channel/ChannelError;Ljava/lang/String;)V
    //   48: aload_0
    //   49: iconst_0
    //   50: putfield 98	org/android/agoo/net/channel/DNSManager:y	Z
    //   53: return
    //   54: aload_2
    //   55: ldc_w 277
    //   58: invokestatic 281	android/text/TextUtils:split	(Ljava/lang/String;Ljava/lang/String;)[Ljava/lang/String;
    //   61: astore 9
    //   63: aconst_null
    //   64: astore 8
    //   66: iconst_m1
    //   67: istore 6
    //   69: aload 8
    //   71: astore 7
    //   73: iload 6
    //   75: istore 5
    //   77: aload 9
    //   79: ifnull +43 -> 122
    //   82: aload 8
    //   84: astore 7
    //   86: iload 6
    //   88: istore 5
    //   90: aload 9
    //   92: arraylength
    //   93: ifle +29 -> 122
    //   96: aload 9
    //   98: iconst_0
    //   99: aaload
    //   100: astore 7
    //   102: bipush 80
    //   104: istore 5
    //   106: aload 9
    //   108: iconst_1
    //   109: aaload
    //   110: invokestatic 285	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   113: invokevirtual 289	java/lang/Integer:intValue	()I
    //   116: istore 6
    //   118: iload 6
    //   120: istore 5
    //   122: aload_0
    //   123: getfield 84	org/android/agoo/net/channel/DNSManager:t	Lorg/android/agoo/net/channel/DNSManager$IHostHandler;
    //   126: ifnull +22 -> 148
    //   129: aload_0
    //   130: getfield 84	org/android/agoo/net/channel/DNSManager:t	Lorg/android/agoo/net/channel/DNSManager$IHostHandler;
    //   133: aload_1
    //   134: aload 7
    //   136: iload 5
    //   138: aload_0
    //   139: getfield 106	org/android/agoo/net/channel/DNSManager:C	Lcom/umeng/message/proguard/bm;
    //   142: aload_3
    //   143: invokeinterface 293 6 0
    //   148: aload 4
    //   150: aload 7
    //   152: invokevirtual 298	com/umeng/message/proguard/bn:g	(Ljava/lang/String;)V
    //   155: aload 4
    //   157: iload 5
    //   159: invokestatic 203	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   162: invokevirtual 300	com/umeng/message/proguard/bn:h	(Ljava/lang/String;)V
    //   165: aload_0
    //   166: iconst_0
    //   167: putfield 98	org/android/agoo/net/channel/DNSManager:y	Z
    //   170: return
    //   171: astore_1
    //   172: aload_0
    //   173: getstatic 268	org/android/agoo/net/channel/ChannelError:y	Lorg/android/agoo/net/channel/ChannelError;
    //   176: new 224	java/lang/StringBuilder
    //   179: dup
    //   180: invokespecial 225	java/lang/StringBuilder:<init>	()V
    //   183: ldc_w 270
    //   186: invokevirtual 231	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   189: aload_2
    //   190: invokevirtual 231	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   193: ldc_w 272
    //   196: invokevirtual 231	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   199: invokevirtual 236	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   202: invokevirtual 275	org/android/agoo/net/channel/DNSManager:a	(Lorg/android/agoo/net/channel/ChannelError;Ljava/lang/String;)V
    //   205: aload_0
    //   206: iconst_0
    //   207: putfield 98	org/android/agoo/net/channel/DNSManager:y	Z
    //   210: return
    //   211: astore_1
    //   212: aload_0
    //   213: iconst_0
    //   214: putfield 98	org/android/agoo/net/channel/DNSManager:y	Z
    //   217: aload_1
    //   218: athrow
    //   219: astore 8
    //   221: goto -99 -> 122
    //
    // Exception table:
    //   from	to	target	type
    //   8	48	171	java/lang/Throwable
    //   54	63	171	java/lang/Throwable
    //   90	96	171	java/lang/Throwable
    //   106	118	171	java/lang/Throwable
    //   122	148	171	java/lang/Throwable
    //   148	165	171	java/lang/Throwable
    //   8	48	211	finally
    //   54	63	211	finally
    //   90	96	211	finally
    //   106	118	211	finally
    //   122	148	211	finally
    //   148	165	211	finally
    //   172	205	211	finally
    //   106	118	219	java/lang/RuntimeException
  }

  private String[] a(String paramString)
  {
    String[] arrayOfString = TextUtils.split(paramString, "\\|");
    int i2 = arrayOfString.length;
    int i1 = 0;
    while (true)
    {
      paramString = arrayOfString;
      if (i1 < i2)
      {
        if (!b(arrayOfString[i1]))
          paramString = null;
      }
      else
        return paramString;
      i1 += 1;
    }
  }

  private final String b()
  {
    try
    {
      String str = this.w.getPackageManager().getPackageInfo(this.w.getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
    }
    return null;
  }

  private final boolean b(String paramString)
  {
    String str = "(" + "(2[0-4]\\d)|(25[0-5])" + ")|(" + "1\\d{2}" + ")|(" + "[1-9]\\d" + ")|(" + "\\d" + ")";
    return Pattern.compile("(" + str + ").(" + str + ").(" + str + ").(" + str + "):\\d*$").matcher(paramString).matches();
  }

  private final void c(bn parambn)
  {
    while (true)
    {
      Object localObject2;
      Object localObject1;
      try
      {
        localObject2 = new RequestParams();
        ((RequestParams)localObject2).put("deviceId", this.l);
        ((RequestParams)localObject2).put("app_version_code", "" + b());
        ((RequestParams)localObject2).put("agoo_version_code", "" + this.s);
        if (!TextUtils.isEmpty(this.k))
          ((RequestParams)localObject2).put("appkey", "" + this.k);
        int i1 = this.r;
        switch (1.a[this.u.ordinal()])
        {
        case 1:
          localObject1 = String.format("%s/%s/", new Object[] { this.m, "spdyip" });
          i1 = 80;
          bd.c("DNSManager", "apollUrl" + (String)localObject1);
          localObject3 = new bl(this.w);
          String str = ((bl)localObject3).c();
          if (!TextUtils.isEmpty(str))
            ((RequestParams)localObject2).put("nt", str);
          localObject3 = ((bl)localObject3).b();
          if (!TextUtils.isEmpty((CharSequence)localObject3))
            ((RequestParams)localObject2).put("apn", (String)localObject3);
          localObject3 = a();
          if (!TextUtils.isEmpty((CharSequence)localObject3))
            ((RequestParams)localObject2).put("agoo_operators", (String)localObject3);
          if (!TextUtils.isEmpty(this.o))
            ((RequestParams)localObject2).put("ttid", "" + this.o);
          if (!TextUtils.isEmpty(this.p))
            ((RequestParams)localObject2).put("imei", "" + this.p);
          if (!TextUtils.isEmpty(this.q))
            ((RequestParams)localObject2).put("imsi", "" + this.q);
          if (new BigDecimal(this.B).compareTo(new BigDecimal(0.0D)) != 0)
            ((RequestParams)localObject2).put("lac", "" + this.B);
          if (new BigDecimal(this.A).compareTo(new BigDecimal(0.0D)) != 0)
            ((RequestParams)localObject2).put("lat", "" + this.A);
          if ((!TextUtils.isEmpty(this.n)) && (this.r != -1))
          {
            localObject3 = new HttpHost(this.n, i1);
            localObject2 = this.D.get(this.w, (HttpHost)localObject3, (String)localObject1, (RequestParams)localObject2);
            if (localObject2 != null)
              continue;
            localObject1 = new c(this.w, "dnsRequestError");
            localObject2 = a(this.w);
            ((LinkedHashMap)localObject2).put("faileReasons", "result == null");
            ((c)localObject1).a((LinkedHashMap)localObject2);
            parambn.f(ChannelError.o.toString());
            a(this.w, ChannelError.o.toString(), this.m);
            a(ChannelError.o, this.m);
            b(parambn);
            return;
            localObject1 = String.format("%s/%s/", new Object[] { this.m, "activeip" });
            continue;
          }
          localObject2 = this.D.get(this.w, (String)localObject1, (RequestParams)localObject2);
          continue;
          localObject3 = ((SyncHttpClient.a)localObject2).b;
          if ((localObject3 == null) || (((Map)localObject3).isEmpty()))
          {
            parambn.j(Integer.toString(((SyncHttpClient.a)localObject2).a));
            parambn.f("headers==null");
            parambn.i(null);
            parambn.k(((SyncHttpClient.a)localObject2).c);
            b(parambn);
            parambn = new c(this.w, "dnsRequestError");
            localObject2 = a(this.w);
            ((LinkedHashMap)localObject2).put("faileReasons", "headers==null");
            parambn.a((LinkedHashMap)localObject2);
            a(this.w, "headers==null", (String)localObject1);
            a(ChannelError.o, "get [" + (String)localObject1 + "] error");
            return;
          }
          break;
        }
      }
      catch (Throwable parambn)
      {
        bd.e("DNSManager", "host Throwable", parambn);
        a(ChannelError.x, "remote get apoll failed");
        return;
      }
      parambn.i(((SyncHttpClient.a)localObject2).b.toString());
      Object localObject3 = (String)((Map)localObject3).get("server");
      if (TextUtils.isEmpty((CharSequence)localObject3))
      {
        parambn.j(Integer.toString(((SyncHttpClient.a)localObject2).a));
        parambn.f("[serverName==null]");
        parambn.k(((SyncHttpClient.a)localObject2).c);
        bd.c("DNSManager", "register--->[serverName==null]");
        b(parambn);
        parambn = new c(this.w, "dnsRequestError");
        localObject2 = a(this.w);
        ((LinkedHashMap)localObject2).put("faileReasons", "[serverName==null]");
        parambn.a((LinkedHashMap)localObject2);
        a(this.w, "serverName==null", (String)localObject1);
        a(ChannelError.o, "get [" + (String)localObject1 + "] error");
        return;
      }
      if (!bE.a((String)localObject3))
      {
        parambn.j(Integer.toString(((SyncHttpClient.a)localObject2).a));
        parambn.f("[serverName!=wjas]");
        parambn.k(((SyncHttpClient.a)localObject2).c);
        bd.c("DNSManager", "register--->[serverName!=wjas]");
        localObject2 = new c(this.w, "dnsRequestError");
        localObject3 = a(this.w);
        ((LinkedHashMap)localObject3).put("faileReasons", "[serverName!=wjas]");
        ((c)localObject2).a((LinkedHashMap)localObject3);
        b(parambn);
        a(this.w, "serverName!=wjas", (String)localObject1);
        a(ChannelError.o, "get [" + (String)localObject1 + "] error");
        return;
      }
      if ((300 <= ((SyncHttpClient.a)localObject2).a) && (400 > ((SyncHttpClient.a)localObject2).a))
      {
        parambn.f("300<=statusCode<400");
        parambn.j(Integer.toString(((SyncHttpClient.a)localObject2).a));
        parambn.k(((SyncHttpClient.a)localObject2).c);
        b(parambn);
        parambn = new c(this.w, "dnsRequestError");
        localObject3 = a(this.w);
        ((LinkedHashMap)localObject3).put("faileReasons", Integer.toString(((SyncHttpClient.a)localObject2).a));
        parambn.a((LinkedHashMap)localObject3);
        a(this.w, "300<=statusCode<400", (String)localObject1);
        a(ChannelError.o, "get [" + (String)localObject1 + "] error");
        return;
      }
      if ((400 <= ((SyncHttpClient.a)localObject2).a) && (500 > ((SyncHttpClient.a)localObject2).a))
      {
        parambn.f("400<=statusCode<500");
        parambn.j(Integer.toString(((SyncHttpClient.a)localObject2).a));
        parambn.k(((SyncHttpClient.a)localObject2).c);
        b(parambn);
        parambn = new c(this.w, "dnsRequestError");
        localObject3 = a(this.w);
        ((LinkedHashMap)localObject3).put("faileReasons", Integer.toString(((SyncHttpClient.a)localObject2).a));
        parambn.a((LinkedHashMap)localObject3);
        a(this.w, "400<=statusCode<500", (String)localObject1);
        a(ChannelError.z, "get [" + (String)localObject1 + "] error");
        return;
      }
      if (200 != ((SyncHttpClient.a)localObject2).a)
      {
        parambn.f(Integer.toString(((SyncHttpClient.a)localObject2).a));
        parambn.j(Integer.toString(((SyncHttpClient.a)localObject2).a));
        parambn.k(((SyncHttpClient.a)localObject2).c);
        b(parambn);
        parambn = new c(this.w, "dnsRequestError");
        localObject3 = a(this.w);
        ((LinkedHashMap)localObject3).put("faileReasons", Integer.toString(((SyncHttpClient.a)localObject2).a));
        parambn.a((LinkedHashMap)localObject3);
        a(this.w, Integer.toString(((SyncHttpClient.a)localObject2).a), (String)localObject1);
        a(ChannelError.z, "get [" + (String)localObject1 + "] error");
        return;
      }
      if (TextUtils.isEmpty(((SyncHttpClient.a)localObject2).c))
      {
        parambn.j(Integer.toString(((SyncHttpClient.a)localObject2).a));
        parambn.f("responseBody is null");
        parambn.k(((SyncHttpClient.a)localObject2).c);
        b(parambn);
        parambn = new c(this.w, "dnsRequestError");
        localObject2 = a(this.w);
        ((LinkedHashMap)localObject2).put("faileReasons", "responseBody is null");
        parambn.a((LinkedHashMap)localObject2);
        a(this.w, "responseBody is null", (String)localObject1);
        a(ChannelError.o, "get [" + (String)localObject1 + "] error");
        return;
      }
      localObject3 = a(((SyncHttpClient.a)localObject2).c);
      if ((localObject3 == null) || (localObject3.length <= 0))
      {
        parambn.j(Integer.toString(((SyncHttpClient.a)localObject2).a));
        parambn.f("ips==null");
        parambn.k(((SyncHttpClient.a)localObject2).c);
        b(parambn);
        a(this.w, "ips==null", (String)localObject1);
        parambn = new c(this.w, "dnsRequestError");
        localObject2 = a(this.w);
        ((LinkedHashMap)localObject2).put("faileReasons", "ips==null");
        parambn.a((LinkedHashMap)localObject2);
        a(ChannelError.o, "get [" + (String)localObject1 + "] error");
        return;
      }
      this.v = this.u;
      if ((((SyncHttpClient.a)localObject2).b != null) && (TextUtils.equals((CharSequence)((SyncHttpClient.a)localObject2).b.get("spdy"), "off")))
        this.v = ChannelType.a;
      bd.c("DNSManager", " initChannel[" + this.u.getDesc() + "]--> resultChannel[" + this.v.getDesc() + "]");
      parambn.j(Integer.toString(((SyncHttpClient.a)localObject2).a));
      parambn.k(((SyncHttpClient.a)localObject2).c);
      parambn.e("y");
      new c(this.w, "dnsRequestSuccess").a(a(this.w));
      a((String[])localObject3, this.v);
      a(this.v, localObject3[0], this.l, parambn);
      b(parambn);
      return;
    }
  }

  public final void a(String paramString, int paramInt)
  {
    this.n = paramString;
    this.r = paramInt;
  }

  public final void a(String paramString1, String paramString2, long paramLong)
  {
    a(paramString1, null, paramString2, paramLong);
  }

  public final void a(String paramString1, String paramString2, String paramString3)
  {
    this.o = paramString1;
    this.p = paramString2;
    this.q = paramString3;
  }

  public final void a(String paramString1, String paramString2, String paramString3, long paramLong)
  {
    this.m = paramString1;
    this.k = paramString2;
    this.l = paramString3;
    this.s = paramLong;
  }

  // ERROR //
  public final void a(ChannelError paramChannelError, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 98	org/android/agoo/net/channel/DNSManager:y	Z
    //   4: ifne +4 -> 8
    //   7: return
    //   8: aload_0
    //   9: getfield 84	org/android/agoo/net/channel/DNSManager:t	Lorg/android/agoo/net/channel/DNSManager$IHostHandler;
    //   12: ifnull +14 -> 26
    //   15: aload_0
    //   16: getfield 84	org/android/agoo/net/channel/DNSManager:t	Lorg/android/agoo/net/channel/DNSManager$IHostHandler;
    //   19: aload_1
    //   20: aload_2
    //   21: invokeinterface 584 3 0
    //   26: aload_0
    //   27: iconst_0
    //   28: putfield 98	org/android/agoo/net/channel/DNSManager:y	Z
    //   31: return
    //   32: astore_1
    //   33: aload_0
    //   34: iconst_0
    //   35: putfield 98	org/android/agoo/net/channel/DNSManager:y	Z
    //   38: return
    //   39: astore_1
    //   40: aload_0
    //   41: iconst_0
    //   42: putfield 98	org/android/agoo/net/channel/DNSManager:y	Z
    //   45: aload_1
    //   46: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   8	26	32	java/lang/Throwable
    //   8	26	39	finally
  }

  public final void a(ChannelType paramChannelType)
  {
    this.u = paramChannelType;
  }

  public final void a(ChannelType paramChannelType, bm parambm)
  {
    if (this.y)
    {
      bd.c("DNSManager", "DNSRemote[runing....]");
      return;
    }
    this.C = parambm;
    this.y = true;
    try
    {
      this.z.a(paramChannelType);
      new Thread(this.z, "agoo-dns").start();
      return;
    }
    catch (Throwable paramChannelType)
    {
      a(ChannelError.s, "thread target  failed");
    }
  }

  public final void a(IHostHandler paramIHostHandler)
  {
    this.t = paramIHostHandler;
  }

  final void a(String[] paramArrayOfString, ChannelType paramChannelType)
  {
    int i3;
    for (int i2 = 0; ; i2 = i3)
    {
      int i1;
      try
      {
        SharedPreferences.Editor localEditor = this.w.getSharedPreferences("AGOO_HOST", 4).edit();
        localEditor.clear();
        int i4 = paramArrayOfString.length;
        i1 = 0;
        if (i1 < i4)
        {
          i3 = i2;
          if (!TextUtils.isEmpty(paramArrayOfString[i1]))
          {
            localEditor.putString("AGOO_HOST_VALUE_" + i2, paramArrayOfString[i1]);
            i3 = i2 + 1;
          }
        }
        else
        {
          localEditor.putInt("AGOO_HOST_TYPE", paramChannelType.getValue());
          localEditor.putInt("AGOO_HOST_SIZE", i2);
          localEditor.commit();
          this.x = 0;
          return;
        }
      }
      catch (Throwable paramArrayOfString)
      {
        return;
      }
      i1 += 1;
    }
  }

  final boolean a(bn parambn)
  {
    boolean bool4 = false;
    boolean bool3 = false;
    boolean bool1 = bool4;
    try
    {
      Object localObject = this.w.getSharedPreferences("AGOO_HOST", 4);
      bool1 = bool4;
      int i1 = ((SharedPreferences)localObject).getInt("AGOO_HOST_SIZE", 0);
      boolean bool2 = bool3;
      if (i1 > 0)
      {
        bool2 = bool3;
        bool1 = bool4;
        if (this.x < i1)
        {
          bool1 = bool4;
          String str = ((SharedPreferences)localObject).getString("AGOO_HOST_VALUE_" + this.x, null);
          bool1 = bool4;
          ChannelType localChannelType = ChannelType.get(((SharedPreferences)localObject).getInt("AGOO_HOST_TYPE", ChannelType.b.getValue()));
          bool1 = bool4;
          bd.c("DNSManager", "refreshLocalHost,mHostIndex=" + this.x);
          bool2 = bool3;
          bool1 = bool4;
          if (!TextUtils.isEmpty(str))
          {
            bool1 = bool4;
            localObject = ((SharedPreferences)localObject).edit();
            bool1 = bool4;
            ((SharedPreferences.Editor)localObject).remove("AGOO_HOST_VALUE_" + this.x);
            bool1 = bool4;
            ((SharedPreferences.Editor)localObject).commit();
            bool1 = bool4;
            a(localChannelType, str, this.l, parambn);
            bool1 = true;
            bool2 = true;
            this.x += 1;
          }
        }
      }
      return bool2;
    }
    catch (Throwable parambn)
    {
      a(ChannelError.s, "refresh failed");
    }
    return bool1;
  }

  public final void b(bn parambn)
  {
    try
    {
      if (this.t != null)
        this.t.onReportDNS(parambn);
      return;
    }
    catch (Throwable parambn)
    {
    }
  }

  public static abstract interface IHostHandler
  {
    public abstract void onFailure(ChannelError paramChannelError, String paramString);

    public abstract void onHost(ChannelType paramChannelType, String paramString1, int paramInt, bm parambm, String paramString2);

    public abstract void onReportDNS(bn parambn);
  }

  final class a
    implements Runnable
  {
    private volatile boolean b = false;
    private volatile ChannelType c = ChannelType.b;

    a()
    {
    }

    public void a(ChannelType paramChannelType)
    {
      this.c = paramChannelType;
    }

    public void run()
    {
      try
      {
        if (this.b)
        {
          bd.c("DNSManager", "DNSRemote[runing....]");
          return;
        }
        bn localbn = new bn();
        localbn.d(bw.a(System.currentTimeMillis()));
        localbn.e("n");
        this.b = true;
        if (DNSManager.a(DNSManager.this) != this.c)
        {
          bd.c("DNSManager", "currentChannleType[" + DNSManager.a(DNSManager.this).getDesc() + "]!=channelType[" + this.c.getDesc() + "]");
          DNSManager.a(DNSManager.this, this.c);
          DNSManager.a(DNSManager.this, localbn);
          return;
        }
        if (DNSManager.this.a(localbn))
        {
          bd.c("DNSManager", "refreshLocalHost successfully");
          return;
        }
        DNSManager.a(DNSManager.this, localbn);
        return;
      }
      catch (Throwable localThrowable)
      {
        bd.e("DNSManager", "host Throwable", localThrowable);
        DNSManager.this.a(ChannelError.s, "remote get apoll failed");
        return;
      }
      finally
      {
        this.b = false;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.channel.DNSManager
 * JD-Core Version:    0.6.2
 */