package cn.jpush.android.service;

import android.content.Context;
import android.os.Bundle;
import cn.jpush.android.util.x;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;

public final class a
{
  public static boolean b;
  private static final String[] z;
  public boolean a = false;
  private b c = null;
  private long d = 0L;
  private long e = 0L;
  private Bundle f;

  static
  {
    String[] arrayOfString = new String[25];
    int j = 0;
    Object localObject2 = "5>-\030\033j";
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
        i = 104;
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
        localObject2 = "\0024<\017E\026 <\023\034";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "\024+6\016\r";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "\005&7\032\r";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = "\024(7\023\r430\022\006";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i4] = localObject1;
        j = 6;
        localObject2 = ":#l]\013?\":\026H25+\022\032{g-\017\021w&>\034\0019gt]";
        i = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i4] = localObject1;
        j = 7;
        localObject2 = "\023(.\023\0048&=]\t0&0\023Dw3+\004H%\"*\tHzg";
        i = 6;
        localObject1 = arrayOfString;
        break;
      case 6:
        localObject3[i4] = localObject1;
        j = 8;
        localObject2 = "\024(7\023\r43y\t\001:\"y\022\035#ky\t\032.g+\030\033#gt]";
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
        localObject2 = "\024(7\t\r93t1\r9 -\025";
        i = 9;
        localObject1 = arrayOfString;
        break;
      case 9:
        localObject3[i4] = localObject1;
        j = 11;
        localObject2 = "\002\023\037PP";
        i = 10;
        localObject1 = arrayOfString;
        break;
      case 10:
        localObject3[i4] = localObject1;
        j = 12;
        localObject2 = "{g?\024\0042\0236\t\t;\013<\023\017#/c";
        i = 11;
        localObject1 = arrayOfString;
        break;
      case 11:
        localObject3[i4] = localObject1;
        j = 13;
        localObject2 = "$36\rH3(.\023\0048&=]\n.g,\016\r%i";
        i = 12;
        localObject1 = arrayOfString;
        break;
      case 12:
        localObject3[i4] = localObject1;
        j = 14;
        localObject2 = "{g?\024\0042\t8\020\rm";
        i = 13;
        localObject1 = arrayOfString;
        break;
      case 13:
        localObject3[i4] = localObject1;
        j = 15;
        localObject2 = "y&)\026";
        i = 14;
        localObject1 = arrayOfString;
        break;
      case 14:
        localObject3[i4] = localObject1;
        j = 16;
        localObject2 = "\007&+\034\005w\"+\017\007%gx\\H\"55G";
        i = 15;
        localObject1 = arrayOfString;
        break;
      case 15:
        localObject3[i4] = localObject1;
        j = 17;
        localObject2 = "{g*\034\0362\0010\021\r\007&-\025R";
        i = 16;
        localObject1 = arrayOfString;
        break;
      case 16:
        localObject3[i4] = localObject1;
        j = 18;
        localObject2 = "6$-\024\0079}=\022\0379+6\034\fwjy\b\032;}";
        i = 17;
        localObject1 = arrayOfString;
        break;
      case 17:
        localObject3[i4] = localObject1;
        j = 19;
        localObject2 = "9\"-\n\007%,y\036\0079)<\036\034w4-\034\034\"4y\036\0073\"y\b\0062?)\030\013#\"=]Ew";
        i = 18;
        localObject1 = arrayOfString;
        break;
      case 18:
        localObject3[i4] = localObject1;
        j = 20;
        localObject2 = "w48\013\r1.5\0308631G";
        i = 19;
        localObject1 = arrayOfString;
        break;
      case 19:
        localObject3[i4] = localObject1;
        j = 21;
        localObject2 = "\003/<]\032246\b\0324\"y\031\00724y\023\007#g<\005\001$3yPH";
        i = 20;
        localObject1 = arrayOfString;
        break;
      case 20:
        localObject3[i4] = localObject1;
        j = 22;
        localObject2 = "w!0\021\r\031&4\030R";
        i = 21;
        localObject1 = arrayOfString;
        break;
      case 21:
        localObject3[i4] = localObject1;
        j = 23;
        localObject2 = "\022?*\024\034w!0\021\rw+<\023\017#/c";
        i = 22;
        localObject1 = arrayOfString;
        break;
      case 22:
        localObject3[i4] = localObject1;
        j = 24;
        localObject2 = "$38\017\034\007(*\t\0018)c]";
        i = 23;
        localObject1 = arrayOfString;
        break;
      case 23:
        localObject3[i4] = localObject1;
        z = arrayOfString;
        b = true;
        return;
        i = 87;
        continue;
        i = 71;
        continue;
        i = 89;
        continue;
        i = 125;
      }
    }
  }

  public a(Context paramContext, cn.jpush.android.data.d paramd, Bundle paramBundle, c paramc, int paramInt)
  {
    x.b();
    this.a = false;
    this.f = paramBundle;
    this.c = new b(this, paramContext.getMainLooper(), paramc);
    this.c.sendEmptyMessageDelayed(0, 2000L);
    int i;
    while (true)
    {
      if (!b)
      {
        x.d();
        this.c.removeCallbacksAndMessages(null);
        this.a = true;
        paramc.a(1);
      }
      do
      {
        do
        {
          return;
          if (this.a)
          {
            x.d();
            this.c.removeCallbacksAndMessages(null);
            paramc.a(1);
            return;
          }
          if (paramd.P != 0)
            break;
          x.e();
        }
        while (paramc == null);
        this.a = true;
        DownloadService.a.remove(paramd);
        this.c.removeCallbacksAndMessages(null);
        paramc.a(2);
        return;
        if (paramInt < 3)
          break;
        x.e();
      }
      while (paramc == null);
      this.a = true;
      DownloadService.a.remove(paramd);
      this.c.removeCallbacksAndMessages(null);
      paramc.a(2);
      return;
      i = a(paramContext, paramc, paramd);
      paramd.P -= 1;
      if (i == -1)
      {
        new StringBuilder(z[8]).append(paramd.P).toString();
        x.c();
        try
        {
          Thread.sleep(3000L);
        }
        catch (InterruptedException paramBundle)
        {
        }
      }
      else if (i == 0)
      {
        new StringBuilder(z[7]).append(paramd.P).toString();
        x.c();
        try
        {
          Thread.sleep(3000L);
        }
        catch (InterruptedException paramBundle)
        {
        }
      }
      else
      {
        if (i == 1)
        {
          x.c();
          this.c.removeCallbacksAndMessages(null);
          this.a = true;
          return;
        }
        if (i != 2)
          break;
        new StringBuilder(z[6]).append(paramd.P).toString();
        x.c();
        paramInt += 1;
      }
    }
    if (i == -3)
    {
      this.a = true;
      DownloadService.a.remove(paramd);
      this.c.removeCallbacksAndMessages(null);
      paramc.a(3);
      return;
    }
    x.c();
    this.a = true;
    DownloadService.a.remove(paramd);
    this.c.removeCallbacksAndMessages(null);
    paramc.a(2);
  }

  private static int a(long paramLong)
  {
    paramLong /= 10485760L;
    int i;
    if (paramLong < 1L)
      i = 10;
    while (true)
    {
      return (int)(i * 1.1D);
      if (paramLong > 5L)
        i = 50;
      else
        i = (int)(paramLong * 10L);
    }
  }

  // ERROR //
  private int a(Context paramContext, c paramc, cn.jpush.android.data.d paramd)
  {
    // Byte code:
    //   0: aload_3
    //   1: invokevirtual 201	cn/jpush/android/data/d:d	()Ljava/lang/String;
    //   4: astore 73
    //   6: aload_3
    //   7: invokevirtual 204	cn/jpush/android/data/d:g	()Ljava/lang/String;
    //   10: astore 72
    //   12: ldc 206
    //   14: astore 12
    //   16: ldc 206
    //   18: astore 11
    //   20: aload_3
    //   21: invokevirtual 209	cn/jpush/android/data/d:a	()Z
    //   24: ifeq +603 -> 627
    //   27: aload_1
    //   28: invokestatic 214	cn/jpush/android/util/k:b	(Landroid/content/Context;)Ljava/lang/String;
    //   31: astore 12
    //   33: new 158	java/lang/StringBuilder
    //   36: dup
    //   37: invokespecial 215	java/lang/StringBuilder:<init>	()V
    //   40: aload_3
    //   41: getfield 218	cn/jpush/android/data/d:c	Ljava/lang/String;
    //   44: invokevirtual 221	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: getstatic 84	cn/jpush/android/service/a:z	[Ljava/lang/String;
    //   50: bipush 15
    //   52: aaload
    //   53: invokevirtual 221	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   56: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   59: astore 11
    //   61: aload 73
    //   63: invokestatic 227	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   66: ifne +3108 -> 3174
    //   69: aload 12
    //   71: invokestatic 227	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   74: ifne +3100 -> 3174
    //   77: aload 11
    //   79: invokestatic 227	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   82: ifne +3092 -> 3174
    //   85: new 158	java/lang/StringBuilder
    //   88: dup
    //   89: getstatic 84	cn/jpush/android/service/a:z	[Ljava/lang/String;
    //   92: bipush 18
    //   94: aaload
    //   95: invokespecial 161	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   98: aload 73
    //   100: invokevirtual 221	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   103: getstatic 84	cn/jpush/android/service/a:z	[Ljava/lang/String;
    //   106: bipush 17
    //   108: aaload
    //   109: invokevirtual 221	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   112: aload 12
    //   114: invokevirtual 221	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   117: getstatic 84	cn/jpush/android/service/a:z	[Ljava/lang/String;
    //   120: bipush 14
    //   122: aaload
    //   123: invokevirtual 221	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: aload 11
    //   128: invokevirtual 221	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   131: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   134: pop
    //   135: invokestatic 125	cn/jpush/android/util/x:d	()V
    //   138: new 229	java/io/File
    //   141: dup
    //   142: aload 12
    //   144: invokespecial 230	java/io/File:<init>	(Ljava/lang/String;)V
    //   147: astore_1
    //   148: aload_1
    //   149: invokevirtual 233	java/io/File:isDirectory	()Z
    //   152: ifne +8 -> 160
    //   155: aload_1
    //   156: invokevirtual 236	java/io/File:mkdirs	()Z
    //   159: pop
    //   160: aload_0
    //   161: getfield 106	cn/jpush/android/service/a:f	Landroid/os/Bundle;
    //   164: aload 73
    //   166: ldc2_w 237
    //   169: invokevirtual 244	android/os/Bundle:getLong	(Ljava/lang/String;J)J
    //   172: lstore 9
    //   174: lconst_0
    //   175: lstore 5
    //   177: aconst_null
    //   178: astore 56
    //   180: aconst_null
    //   181: astore 57
    //   183: aconst_null
    //   184: astore 58
    //   186: aconst_null
    //   187: astore 59
    //   189: aconst_null
    //   190: astore 60
    //   192: aconst_null
    //   193: astore_1
    //   194: aconst_null
    //   195: astore 55
    //   197: aconst_null
    //   198: astore 50
    //   200: aconst_null
    //   201: astore 51
    //   203: aconst_null
    //   204: astore 52
    //   206: aconst_null
    //   207: astore 53
    //   209: aconst_null
    //   210: astore 15
    //   212: aconst_null
    //   213: astore 43
    //   215: aconst_null
    //   216: astore 44
    //   218: aconst_null
    //   219: astore 45
    //   221: aconst_null
    //   222: astore 46
    //   224: aconst_null
    //   225: astore 47
    //   227: aconst_null
    //   228: astore 48
    //   230: aconst_null
    //   231: astore 61
    //   233: aconst_null
    //   234: astore 49
    //   236: aconst_null
    //   237: astore 71
    //   239: aconst_null
    //   240: astore 54
    //   242: aconst_null
    //   243: astore 35
    //   245: aconst_null
    //   246: astore 36
    //   248: aconst_null
    //   249: astore 37
    //   251: aconst_null
    //   252: astore 38
    //   254: aconst_null
    //   255: astore 29
    //   257: aconst_null
    //   258: astore 39
    //   260: aconst_null
    //   261: astore 40
    //   263: aconst_null
    //   264: astore 30
    //   266: aconst_null
    //   267: astore 31
    //   269: aconst_null
    //   270: astore 32
    //   272: aconst_null
    //   273: astore 33
    //   275: aconst_null
    //   276: astore 62
    //   278: aconst_null
    //   279: astore 41
    //   281: aconst_null
    //   282: astore 14
    //   284: aconst_null
    //   285: astore 42
    //   287: aconst_null
    //   288: astore 18
    //   290: aconst_null
    //   291: astore 19
    //   293: aconst_null
    //   294: astore 20
    //   296: aconst_null
    //   297: astore 21
    //   299: aconst_null
    //   300: astore 17
    //   302: aconst_null
    //   303: astore 26
    //   305: aconst_null
    //   306: astore 27
    //   308: aconst_null
    //   309: astore 22
    //   311: aconst_null
    //   312: astore 23
    //   314: aconst_null
    //   315: astore 24
    //   317: aconst_null
    //   318: astore 25
    //   320: aconst_null
    //   321: astore 63
    //   323: aconst_null
    //   324: astore 28
    //   326: aconst_null
    //   327: astore 13
    //   329: aconst_null
    //   330: astore 34
    //   332: aconst_null
    //   333: astore 64
    //   335: aconst_null
    //   336: astore 65
    //   338: aconst_null
    //   339: astore 66
    //   341: aconst_null
    //   342: astore 67
    //   344: aconst_null
    //   345: astore 68
    //   347: aconst_null
    //   348: astore 69
    //   350: aconst_null
    //   351: astore 70
    //   353: aconst_null
    //   354: astore 16
    //   356: lload 9
    //   358: lconst_0
    //   359: lcmp
    //   360: ifgt +1046 -> 1406
    //   363: new 229	java/io/File
    //   366: dup
    //   367: aload 12
    //   369: aload 11
    //   371: invokespecial 247	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   374: astore 22
    //   376: aload 22
    //   378: invokevirtual 250	java/io/File:exists	()Z
    //   381: ifeq +13 -> 394
    //   384: aload 22
    //   386: invokevirtual 254	java/io/File:length	()J
    //   389: lconst_0
    //   390: lcmp
    //   391: ifgt +855 -> 1246
    //   394: invokestatic 104	cn/jpush/android/util/x:b	()V
    //   397: aload 73
    //   399: ldc2_w 237
    //   402: invokestatic 257	cn/jpush/android/service/a:a	(Ljava/lang/String;J)Lorg/apache/http/client/methods/HttpGet;
    //   405: astore_1
    //   406: invokestatic 260	cn/jpush/android/service/a:a	()Lorg/apache/http/impl/client/DefaultHttpClient;
    //   409: astore 11
    //   411: aload 11
    //   413: aload_1
    //   414: invokevirtual 266	org/apache/http/impl/client/DefaultHttpClient:execute	(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
    //   417: astore_1
    //   418: aload_1
    //   419: ifnull +584 -> 1003
    //   422: aload_1
    //   423: invokeinterface 272 1 0
    //   428: invokeinterface 278 1 0
    //   433: istore 4
    //   435: iload 4
    //   437: sipush 200
    //   440: if_icmpne +483 -> 923
    //   443: aload_1
    //   444: invokeinterface 282 1 0
    //   449: astore 11
    //   451: aload 11
    //   453: invokeinterface 287 1 0
    //   458: ifeq +450 -> 908
    //   461: aload_1
    //   462: invokestatic 290	cn/jpush/android/service/a:a	(Lorg/apache/http/HttpResponse;)J
    //   465: lstore 7
    //   467: aload_0
    //   468: getfield 106	cn/jpush/android/service/a:f	Landroid/os/Bundle;
    //   471: aload 73
    //   473: lload 7
    //   475: invokevirtual 294	android/os/Bundle:putLong	(Ljava/lang/String;J)V
    //   478: aload_3
    //   479: lload 7
    //   481: invokestatic 296	cn/jpush/android/service/a:a	(J)I
    //   484: putfield 140	cn/jpush/android/data/d:P	I
    //   487: aload 11
    //   489: invokeinterface 300 1 0
    //   494: astore 12
    //   496: aload 12
    //   498: ifnull +384 -> 882
    //   501: aload 11
    //   503: astore 16
    //   505: aload 71
    //   507: astore 15
    //   509: aload 12
    //   511: astore_3
    //   512: new 302	java/io/BufferedInputStream
    //   515: dup
    //   516: aload 12
    //   518: invokespecial 305	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   521: astore_1
    //   522: aload 22
    //   524: invokevirtual 308	java/io/File:delete	()Z
    //   527: pop
    //   528: aload 22
    //   530: invokevirtual 311	java/io/File:createNewFile	()Z
    //   533: pop
    //   534: new 313	java/io/FileOutputStream
    //   537: dup
    //   538: aload 22
    //   540: invokespecial 316	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   543: astore_3
    //   544: new 318	java/io/BufferedOutputStream
    //   547: dup
    //   548: aload_3
    //   549: invokespecial 321	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   552: astore 13
    //   554: sipush 1024
    //   557: newarray byte
    //   559: astore 14
    //   561: aload_1
    //   562: aload 14
    //   564: invokevirtual 325	java/io/BufferedInputStream:read	([B)I
    //   567: istore 4
    //   569: iload 4
    //   571: iconst_m1
    //   572: if_icmpeq +200 -> 772
    //   575: aload_0
    //   576: getfield 100	cn/jpush/android/service/a:a	Z
    //   579: ifeq +119 -> 698
    //   582: invokestatic 142	cn/jpush/android/util/x:e	()V
    //   585: new 199	cn/jpush/android/d
    //   588: dup
    //   589: getstatic 84	cn/jpush/android/service/a:z	[Ljava/lang/String;
    //   592: bipush 13
    //   594: aaload
    //   595: invokespecial 326	cn/jpush/android/d:<init>	(Ljava/lang/String;)V
    //   598: athrow
    //   599: astore_2
    //   600: aload_3
    //   601: astore 14
    //   603: aload 12
    //   605: astore_3
    //   606: aload_1
    //   607: astore_2
    //   608: aload 14
    //   610: astore_1
    //   611: invokestatic 329	cn/jpush/android/util/x:j	()V
    //   614: aload_3
    //   615: aload_2
    //   616: aload_1
    //   617: aload 13
    //   619: aload 11
    //   621: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   624: bipush 254
    //   626: ireturn
    //   627: aload_3
    //   628: invokevirtual 334	cn/jpush/android/data/d:b	()Z
    //   631: ifeq +18 -> 649
    //   634: aload_1
    //   635: invokestatic 336	cn/jpush/android/util/k:c	(Landroid/content/Context;)Ljava/lang/String;
    //   638: astore 12
    //   640: aload_3
    //   641: getfield 218	cn/jpush/android/data/d:c	Ljava/lang/String;
    //   644: astore 11
    //   646: goto -585 -> 61
    //   649: aload_3
    //   650: invokevirtual 338	cn/jpush/android/data/d:f	()Z
    //   653: ifeq -592 -> 61
    //   656: aload_1
    //   657: aload_3
    //   658: getfield 218	cn/jpush/android/data/d:c	Ljava/lang/String;
    //   661: invokestatic 341	cn/jpush/android/util/k:b	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   664: astore 12
    //   666: new 158	java/lang/StringBuilder
    //   669: dup
    //   670: invokespecial 215	java/lang/StringBuilder:<init>	()V
    //   673: aload_3
    //   674: getfield 218	cn/jpush/android/data/d:c	Ljava/lang/String;
    //   677: invokevirtual 221	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   680: aload_3
    //   681: invokevirtual 201	cn/jpush/android/data/d:d	()Ljava/lang/String;
    //   684: invokestatic 346	cn/jpush/android/util/m:b	(Ljava/lang/String;)Ljava/lang/String;
    //   687: invokevirtual 221	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   690: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   693: astore 11
    //   695: goto -634 -> 61
    //   698: aload 13
    //   700: aload 14
    //   702: iconst_0
    //   703: iload 4
    //   705: invokevirtual 350	java/io/BufferedOutputStream:write	([BII)V
    //   708: lload 5
    //   710: iload 4
    //   712: i2l
    //   713: ladd
    //   714: lstore 5
    //   716: aload_0
    //   717: lload 5
    //   719: putfield 96	cn/jpush/android/service/a:d	J
    //   722: aload_0
    //   723: lload 7
    //   725: putfield 98	cn/jpush/android/service/a:e	J
    //   728: goto -167 -> 561
    //   731: astore_2
    //   732: aload_3
    //   733: astore_2
    //   734: aload 13
    //   736: astore 17
    //   738: aload 11
    //   740: astore 16
    //   742: aload 17
    //   744: astore 13
    //   746: aload_2
    //   747: astore 14
    //   749: aload_1
    //   750: astore 15
    //   752: aload 12
    //   754: astore_3
    //   755: invokestatic 329	cn/jpush/android/util/x:j	()V
    //   758: aload 12
    //   760: aload_1
    //   761: aload_2
    //   762: aload 17
    //   764: aload 11
    //   766: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   769: bipush 254
    //   771: ireturn
    //   772: aload 13
    //   774: invokevirtual 353	java/io/BufferedOutputStream:flush	()V
    //   777: aload 22
    //   779: ifnull +62 -> 841
    //   782: aload 22
    //   784: invokevirtual 254	java/io/File:length	()J
    //   787: lload 7
    //   789: lcmp
    //   790: ifne +51 -> 841
    //   793: aload 72
    //   795: aload 22
    //   797: invokestatic 358	cn/jpush/android/util/g:a	(Ljava/lang/String;Ljava/io/File;)Z
    //   800: ifeq +41 -> 841
    //   803: aload_0
    //   804: getfield 106	cn/jpush/android/service/a:f	Landroid/os/Bundle;
    //   807: aload 73
    //   809: invokevirtual 360	android/os/Bundle:remove	(Ljava/lang/String;)V
    //   812: aload_2
    //   813: ifnull +15 -> 828
    //   816: aload_2
    //   817: aload 22
    //   819: invokevirtual 363	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   822: iconst_0
    //   823: invokeinterface 366 3 0
    //   828: aload 12
    //   830: aload_1
    //   831: aload_3
    //   832: aload 13
    //   834: aload 11
    //   836: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   839: iconst_1
    //   840: ireturn
    //   841: invokestatic 142	cn/jpush/android/util/x:e	()V
    //   844: aload 22
    //   846: invokevirtual 308	java/io/File:delete	()Z
    //   849: ifne +20 -> 869
    //   852: invokestatic 368	cn/jpush/android/util/x:f	()V
    //   855: aload 12
    //   857: aload_1
    //   858: aload_3
    //   859: aload 13
    //   861: aload 11
    //   863: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   866: bipush 254
    //   868: ireturn
    //   869: aload 12
    //   871: aload_1
    //   872: aload_3
    //   873: aload 13
    //   875: aload 11
    //   877: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   880: iconst_2
    //   881: ireturn
    //   882: aload 11
    //   884: astore 16
    //   886: aload 71
    //   888: astore 15
    //   890: aload 12
    //   892: astore_3
    //   893: invokestatic 142	cn/jpush/android/util/x:e	()V
    //   896: aload 12
    //   898: aconst_null
    //   899: aconst_null
    //   900: aconst_null
    //   901: aload 11
    //   903: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   906: iconst_0
    //   907: ireturn
    //   908: invokestatic 368	cn/jpush/android/util/x:f	()V
    //   911: aconst_null
    //   912: aconst_null
    //   913: aconst_null
    //   914: aconst_null
    //   915: aload 11
    //   917: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   920: bipush 254
    //   922: ireturn
    //   923: iload 4
    //   925: sipush 404
    //   928: if_icmpne +39 -> 967
    //   931: new 158	java/lang/StringBuilder
    //   934: dup
    //   935: getstatic 84	cn/jpush/android/service/a:z	[Ljava/lang/String;
    //   938: bipush 21
    //   940: aaload
    //   941: invokespecial 161	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   944: aload 73
    //   946: invokevirtual 221	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   949: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   952: pop
    //   953: invokestatic 170	cn/jpush/android/util/x:c	()V
    //   956: aconst_null
    //   957: aconst_null
    //   958: aconst_null
    //   959: aconst_null
    //   960: aconst_null
    //   961: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   964: bipush 253
    //   966: ireturn
    //   967: new 158	java/lang/StringBuilder
    //   970: dup
    //   971: getstatic 84	cn/jpush/android/service/a:z	[Ljava/lang/String;
    //   974: bipush 19
    //   976: aaload
    //   977: invokespecial 161	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   980: iload 4
    //   982: invokevirtual 165	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   985: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   988: pop
    //   989: invokestatic 142	cn/jpush/android/util/x:e	()V
    //   992: aconst_null
    //   993: aconst_null
    //   994: aconst_null
    //   995: aconst_null
    //   996: aconst_null
    //   997: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   1000: bipush 254
    //   1002: ireturn
    //   1003: invokestatic 142	cn/jpush/android/util/x:e	()V
    //   1006: aconst_null
    //   1007: aconst_null
    //   1008: aconst_null
    //   1009: aconst_null
    //   1010: aconst_null
    //   1011: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   1014: iconst_0
    //   1015: ireturn
    //   1016: astore_1
    //   1017: aconst_null
    //   1018: astore 11
    //   1020: aconst_null
    //   1021: astore 12
    //   1023: aload 50
    //   1025: astore_1
    //   1026: aload 35
    //   1028: astore_2
    //   1029: aload 18
    //   1031: astore 17
    //   1033: aload 11
    //   1035: astore 16
    //   1037: aload 17
    //   1039: astore 13
    //   1041: aload_2
    //   1042: astore 14
    //   1044: aload_1
    //   1045: astore 15
    //   1047: aload 12
    //   1049: astore_3
    //   1050: invokestatic 329	cn/jpush/android/util/x:j	()V
    //   1053: aload 12
    //   1055: aload_1
    //   1056: aload_2
    //   1057: aload 17
    //   1059: aload 11
    //   1061: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   1064: bipush 254
    //   1066: ireturn
    //   1067: astore_1
    //   1068: aconst_null
    //   1069: astore 11
    //   1071: aconst_null
    //   1072: astore 12
    //   1074: aload 51
    //   1076: astore_1
    //   1077: aload 36
    //   1079: astore_2
    //   1080: aload 19
    //   1082: astore 17
    //   1084: aload 11
    //   1086: astore 16
    //   1088: aload 17
    //   1090: astore 13
    //   1092: aload_2
    //   1093: astore 14
    //   1095: aload_1
    //   1096: astore 15
    //   1098: aload 12
    //   1100: astore_3
    //   1101: invokestatic 329	cn/jpush/android/util/x:j	()V
    //   1104: aload 12
    //   1106: aload_1
    //   1107: aload_2
    //   1108: aload 17
    //   1110: aload 11
    //   1112: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   1115: bipush 254
    //   1117: ireturn
    //   1118: astore_1
    //   1119: aconst_null
    //   1120: astore 11
    //   1122: aconst_null
    //   1123: astore 12
    //   1125: aload 52
    //   1127: astore_1
    //   1128: aload 37
    //   1130: astore_2
    //   1131: aload 20
    //   1133: astore 17
    //   1135: aload 11
    //   1137: astore 16
    //   1139: aload 17
    //   1141: astore 13
    //   1143: aload_2
    //   1144: astore 14
    //   1146: aload_1
    //   1147: astore 15
    //   1149: aload 12
    //   1151: astore_3
    //   1152: invokestatic 371	cn/jpush/android/util/x:h	()V
    //   1155: aload 12
    //   1157: aload_1
    //   1158: aload_2
    //   1159: aload 17
    //   1161: aload 11
    //   1163: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   1166: iconst_m1
    //   1167: ireturn
    //   1168: astore_1
    //   1169: aconst_null
    //   1170: astore 11
    //   1172: aconst_null
    //   1173: astore 12
    //   1175: aload 53
    //   1177: astore_1
    //   1178: aload 38
    //   1180: astore_2
    //   1181: aload 21
    //   1183: astore 17
    //   1185: aload 11
    //   1187: astore 16
    //   1189: aload 17
    //   1191: astore 13
    //   1193: aload_2
    //   1194: astore 14
    //   1196: aload_1
    //   1197: astore 15
    //   1199: aload 12
    //   1201: astore_3
    //   1202: invokestatic 374	cn/jpush/android/util/x:i	()V
    //   1205: aload 12
    //   1207: aload_1
    //   1208: aload_2
    //   1209: aload 17
    //   1211: aload 11
    //   1213: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   1216: bipush 254
    //   1218: ireturn
    //   1219: astore_1
    //   1220: aconst_null
    //   1221: astore_3
    //   1222: aload 29
    //   1224: astore 14
    //   1226: aload 17
    //   1228: astore 13
    //   1230: aload 16
    //   1232: astore_2
    //   1233: aload_3
    //   1234: aload 15
    //   1236: aload 14
    //   1238: aload 13
    //   1240: aload_2
    //   1241: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   1244: aload_1
    //   1245: athrow
    //   1246: aload 22
    //   1248: invokevirtual 254	java/io/File:length	()J
    //   1251: lconst_0
    //   1252: lcmp
    //   1253: ifle +147 -> 1400
    //   1256: aload 73
    //   1258: ldc2_w 237
    //   1261: invokestatic 257	cn/jpush/android/service/a:a	(Ljava/lang/String;J)Lorg/apache/http/client/methods/HttpGet;
    //   1264: astore_1
    //   1265: invokestatic 260	cn/jpush/android/service/a:a	()Lorg/apache/http/impl/client/DefaultHttpClient;
    //   1268: astore_3
    //   1269: aload_3
    //   1270: aload_1
    //   1271: invokevirtual 266	org/apache/http/impl/client/DefaultHttpClient:execute	(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
    //   1274: invokestatic 290	cn/jpush/android/service/a:a	(Lorg/apache/http/HttpResponse;)J
    //   1277: lstore 5
    //   1279: aload 22
    //   1281: invokevirtual 254	java/io/File:length	()J
    //   1284: lload 5
    //   1286: lcmp
    //   1287: ifne +35 -> 1322
    //   1290: aload 72
    //   1292: aload 22
    //   1294: invokestatic 358	cn/jpush/android/util/g:a	(Ljava/lang/String;Ljava/io/File;)Z
    //   1297: ifeq +25 -> 1322
    //   1300: invokestatic 170	cn/jpush/android/util/x:c	()V
    //   1303: aload_2
    //   1304: ifnull +2950 -> 4254
    //   1307: aload_2
    //   1308: aload 22
    //   1310: invokevirtual 363	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   1313: iconst_1
    //   1314: invokeinterface 366 3 0
    //   1319: goto +2935 -> 4254
    //   1322: new 158	java/lang/StringBuilder
    //   1325: dup
    //   1326: getstatic 84	cn/jpush/android/service/a:z	[Ljava/lang/String;
    //   1329: bipush 23
    //   1331: aaload
    //   1332: invokespecial 161	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1335: aload 22
    //   1337: invokevirtual 254	java/io/File:length	()J
    //   1340: invokevirtual 377	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1343: getstatic 84	cn/jpush/android/service/a:z	[Ljava/lang/String;
    //   1346: bipush 12
    //   1348: aaload
    //   1349: invokevirtual 221	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1352: lload 5
    //   1354: invokevirtual 377	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1357: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1360: pop
    //   1361: invokestatic 125	cn/jpush/android/util/x:d	()V
    //   1364: aload 22
    //   1366: invokevirtual 308	java/io/File:delete	()Z
    //   1369: ifne +9 -> 1378
    //   1372: invokestatic 368	cn/jpush/android/util/x:f	()V
    //   1375: bipush 254
    //   1377: ireturn
    //   1378: iconst_2
    //   1379: ireturn
    //   1380: astore_1
    //   1381: invokestatic 329	cn/jpush/android/util/x:j	()V
    //   1384: bipush 254
    //   1386: ireturn
    //   1387: astore_1
    //   1388: invokestatic 371	cn/jpush/android/util/x:h	()V
    //   1391: iconst_m1
    //   1392: ireturn
    //   1393: astore_1
    //   1394: invokestatic 374	cn/jpush/android/util/x:i	()V
    //   1397: bipush 254
    //   1399: ireturn
    //   1400: invokestatic 368	cn/jpush/android/util/x:f	()V
    //   1403: bipush 254
    //   1405: ireturn
    //   1406: invokestatic 125	cn/jpush/android/util/x:d	()V
    //   1409: new 229	java/io/File
    //   1412: dup
    //   1413: aload 12
    //   1415: aload 11
    //   1417: invokespecial 247	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   1420: astore 29
    //   1422: aload 29
    //   1424: invokevirtual 250	java/io/File:exists	()Z
    //   1427: ifeq +539 -> 1966
    //   1430: invokestatic 104	cn/jpush/android/util/x:b	()V
    //   1433: aload 29
    //   1435: invokevirtual 254	java/io/File:length	()J
    //   1438: lstore 7
    //   1440: lload 7
    //   1442: lstore 5
    //   1444: new 158	java/lang/StringBuilder
    //   1447: dup
    //   1448: getstatic 84	cn/jpush/android/service/a:z	[Ljava/lang/String;
    //   1451: bipush 24
    //   1453: aaload
    //   1454: invokespecial 161	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1457: lload 7
    //   1459: invokevirtual 377	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1462: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1465: pop
    //   1466: invokestatic 125	cn/jpush/android/util/x:d	()V
    //   1469: aload_3
    //   1470: getfield 140	cn/jpush/android/data/d:P	I
    //   1473: iconst_m1
    //   1474: if_icmpne +15 -> 1489
    //   1477: invokestatic 170	cn/jpush/android/util/x:c	()V
    //   1480: aload_3
    //   1481: lload 9
    //   1483: invokestatic 296	cn/jpush/android/service/a:a	(J)I
    //   1486: putfield 140	cn/jpush/android/data/d:P	I
    //   1489: aload 73
    //   1491: lload 7
    //   1493: invokestatic 257	cn/jpush/android/service/a:a	(Ljava/lang/String;J)Lorg/apache/http/client/methods/HttpGet;
    //   1496: astore 11
    //   1498: invokestatic 260	cn/jpush/android/service/a:a	()Lorg/apache/http/impl/client/DefaultHttpClient;
    //   1501: astore 34
    //   1503: aload 64
    //   1505: astore 16
    //   1507: aload 65
    //   1509: astore 17
    //   1511: aload 66
    //   1513: astore 18
    //   1515: aload 67
    //   1517: astore 19
    //   1519: aload 68
    //   1521: astore_3
    //   1522: aload 63
    //   1524: astore 12
    //   1526: aload 62
    //   1528: astore 13
    //   1530: aload_1
    //   1531: astore 14
    //   1533: aload 61
    //   1535: astore 15
    //   1537: aload 69
    //   1539: astore 20
    //   1541: aload 70
    //   1543: astore 21
    //   1545: aload 34
    //   1547: aload 11
    //   1549: invokevirtual 266	org/apache/http/impl/client/DefaultHttpClient:execute	(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
    //   1552: astore 34
    //   1554: aload 34
    //   1556: ifnull +1353 -> 2909
    //   1559: aload 64
    //   1561: astore 16
    //   1563: aload 65
    //   1565: astore 17
    //   1567: aload 66
    //   1569: astore 18
    //   1571: aload 67
    //   1573: astore 19
    //   1575: aload 68
    //   1577: astore_3
    //   1578: aload 63
    //   1580: astore 12
    //   1582: aload 62
    //   1584: astore 13
    //   1586: aload_1
    //   1587: astore 14
    //   1589: aload 61
    //   1591: astore 15
    //   1593: aload 69
    //   1595: astore 20
    //   1597: aload 70
    //   1599: astore 21
    //   1601: aload 34
    //   1603: invokeinterface 272 1 0
    //   1608: invokeinterface 278 1 0
    //   1613: istore 4
    //   1615: iload 4
    //   1617: sipush 200
    //   1620: if_icmpeq +11 -> 1631
    //   1623: iload 4
    //   1625: sipush 206
    //   1628: if_icmpne +824 -> 2452
    //   1631: aload 64
    //   1633: astore 16
    //   1635: aload 65
    //   1637: astore 17
    //   1639: aload 66
    //   1641: astore 18
    //   1643: aload 67
    //   1645: astore 19
    //   1647: aload 68
    //   1649: astore_3
    //   1650: aload 63
    //   1652: astore 12
    //   1654: aload 62
    //   1656: astore 13
    //   1658: aload_1
    //   1659: astore 14
    //   1661: aload 61
    //   1663: astore 15
    //   1665: aload 69
    //   1667: astore 20
    //   1669: aload 70
    //   1671: astore 21
    //   1673: aload 34
    //   1675: invokeinterface 282 1 0
    //   1680: astore 11
    //   1682: aload 11
    //   1684: astore 16
    //   1686: aload 11
    //   1688: astore 17
    //   1690: aload 11
    //   1692: astore 18
    //   1694: aload 11
    //   1696: astore 19
    //   1698: aload 11
    //   1700: astore_3
    //   1701: aload 63
    //   1703: astore 12
    //   1705: aload 62
    //   1707: astore 13
    //   1709: aload_1
    //   1710: astore 14
    //   1712: aload 61
    //   1714: astore 15
    //   1716: aload 11
    //   1718: astore 20
    //   1720: aload 11
    //   1722: astore 21
    //   1724: aload 11
    //   1726: invokeinterface 287 1 0
    //   1731: ifeq +664 -> 2395
    //   1734: aload 11
    //   1736: astore 16
    //   1738: aload 11
    //   1740: astore 17
    //   1742: aload 11
    //   1744: astore 18
    //   1746: aload 11
    //   1748: astore 19
    //   1750: aload 11
    //   1752: astore_3
    //   1753: aload 63
    //   1755: astore 12
    //   1757: aload 62
    //   1759: astore 13
    //   1761: aload_1
    //   1762: astore 14
    //   1764: aload 61
    //   1766: astore 15
    //   1768: aload 11
    //   1770: astore 20
    //   1772: aload 11
    //   1774: astore 21
    //   1776: aload 34
    //   1778: invokestatic 290	cn/jpush/android/service/a:a	(Lorg/apache/http/HttpResponse;)J
    //   1781: lload 5
    //   1783: ladd
    //   1784: lload 9
    //   1786: lcmp
    //   1787: ifne +406 -> 2193
    //   1790: aload 11
    //   1792: astore 16
    //   1794: aload 11
    //   1796: astore 17
    //   1798: aload 11
    //   1800: astore 18
    //   1802: aload 11
    //   1804: astore 19
    //   1806: aload 11
    //   1808: astore_3
    //   1809: aload 63
    //   1811: astore 12
    //   1813: aload 62
    //   1815: astore 13
    //   1817: aload_1
    //   1818: astore 14
    //   1820: aload 61
    //   1822: astore 15
    //   1824: aload 11
    //   1826: astore 20
    //   1828: aload 11
    //   1830: astore 21
    //   1832: aload 11
    //   1834: invokeinterface 300 1 0
    //   1839: astore_1
    //   1840: aload_1
    //   1841: ifnull +338 -> 2179
    //   1844: new 302	java/io/BufferedInputStream
    //   1847: dup
    //   1848: aload_1
    //   1849: invokespecial 305	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   1852: astore_3
    //   1853: new 313	java/io/FileOutputStream
    //   1856: dup
    //   1857: aload 29
    //   1859: iconst_1
    //   1860: invokespecial 380	java/io/FileOutputStream:<init>	(Ljava/io/File;Z)V
    //   1863: astore 12
    //   1865: new 318	java/io/BufferedOutputStream
    //   1868: dup
    //   1869: aload 12
    //   1871: invokespecial 321	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   1874: astore 13
    //   1876: sipush 1024
    //   1879: newarray byte
    //   1881: astore 14
    //   1883: aload_3
    //   1884: aload 14
    //   1886: invokevirtual 325	java/io/BufferedInputStream:read	([B)I
    //   1889: istore 4
    //   1891: iload 4
    //   1893: iconst_m1
    //   1894: if_icmpeq +172 -> 2066
    //   1897: aload_0
    //   1898: getfield 100	cn/jpush/android/service/a:a	Z
    //   1901: ifeq +87 -> 1988
    //   1904: invokestatic 142	cn/jpush/android/util/x:e	()V
    //   1907: new 199	cn/jpush/android/d
    //   1910: dup
    //   1911: getstatic 84	cn/jpush/android/service/a:z	[Ljava/lang/String;
    //   1914: bipush 13
    //   1916: aaload
    //   1917: invokespecial 326	cn/jpush/android/d:<init>	(Ljava/lang/String;)V
    //   1920: athrow
    //   1921: astore_2
    //   1922: aload_3
    //   1923: astore_2
    //   1924: aload 12
    //   1926: astore 16
    //   1928: aload 13
    //   1930: astore 17
    //   1932: aload 11
    //   1934: astore_3
    //   1935: aload 17
    //   1937: astore 12
    //   1939: aload 16
    //   1941: astore 13
    //   1943: aload_1
    //   1944: astore 14
    //   1946: aload_2
    //   1947: astore 15
    //   1949: invokestatic 329	cn/jpush/android/util/x:j	()V
    //   1952: aload_1
    //   1953: aload_2
    //   1954: aload 16
    //   1956: aload 17
    //   1958: aload 11
    //   1960: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   1963: bipush 254
    //   1965: ireturn
    //   1966: invokestatic 104	cn/jpush/android/util/x:b	()V
    //   1969: lconst_0
    //   1970: lstore 7
    //   1972: aload 29
    //   1974: invokevirtual 311	java/io/File:createNewFile	()Z
    //   1977: pop
    //   1978: goto -534 -> 1444
    //   1981: astore_1
    //   1982: invokestatic 329	cn/jpush/android/util/x:j	()V
    //   1985: bipush 254
    //   1987: ireturn
    //   1988: aload 13
    //   1990: aload 14
    //   1992: iconst_0
    //   1993: iload 4
    //   1995: invokevirtual 350	java/io/BufferedOutputStream:write	([BII)V
    //   1998: lload 5
    //   2000: iload 4
    //   2002: i2l
    //   2003: ladd
    //   2004: lstore 5
    //   2006: aload_0
    //   2007: lload 5
    //   2009: putfield 96	cn/jpush/android/service/a:d	J
    //   2012: aload_0
    //   2013: lload 9
    //   2015: putfield 98	cn/jpush/android/service/a:e	J
    //   2018: goto -135 -> 1883
    //   2021: astore_2
    //   2022: aload_3
    //   2023: astore_2
    //   2024: aload 12
    //   2026: astore 16
    //   2028: aload 13
    //   2030: astore 17
    //   2032: aload 11
    //   2034: astore_3
    //   2035: aload 17
    //   2037: astore 12
    //   2039: aload 16
    //   2041: astore 13
    //   2043: aload_1
    //   2044: astore 14
    //   2046: aload_2
    //   2047: astore 15
    //   2049: invokestatic 329	cn/jpush/android/util/x:j	()V
    //   2052: aload_1
    //   2053: aload_2
    //   2054: aload 16
    //   2056: aload 17
    //   2058: aload 11
    //   2060: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   2063: bipush 254
    //   2065: ireturn
    //   2066: aload 13
    //   2068: invokevirtual 353	java/io/BufferedOutputStream:flush	()V
    //   2071: invokestatic 170	cn/jpush/android/util/x:c	()V
    //   2074: aload 29
    //   2076: ifnull +62 -> 2138
    //   2079: aload 29
    //   2081: invokevirtual 254	java/io/File:length	()J
    //   2084: lload 9
    //   2086: lcmp
    //   2087: ifne +51 -> 2138
    //   2090: aload 72
    //   2092: aload 29
    //   2094: invokestatic 358	cn/jpush/android/util/g:a	(Ljava/lang/String;Ljava/io/File;)Z
    //   2097: ifeq +41 -> 2138
    //   2100: aload_0
    //   2101: getfield 106	cn/jpush/android/service/a:f	Landroid/os/Bundle;
    //   2104: aload 73
    //   2106: invokevirtual 360	android/os/Bundle:remove	(Ljava/lang/String;)V
    //   2109: aload_2
    //   2110: ifnull +15 -> 2125
    //   2113: aload_2
    //   2114: aload 29
    //   2116: invokevirtual 363	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   2119: iconst_0
    //   2120: invokeinterface 366 3 0
    //   2125: aload_1
    //   2126: aload_3
    //   2127: aload 12
    //   2129: aload 13
    //   2131: aload 11
    //   2133: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   2136: iconst_1
    //   2137: ireturn
    //   2138: invokestatic 142	cn/jpush/android/util/x:e	()V
    //   2141: aload 29
    //   2143: invokevirtual 308	java/io/File:delete	()Z
    //   2146: ifne +20 -> 2166
    //   2149: invokestatic 368	cn/jpush/android/util/x:f	()V
    //   2152: aload_1
    //   2153: aload_3
    //   2154: aload 12
    //   2156: aload 13
    //   2158: aload 11
    //   2160: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   2163: bipush 254
    //   2165: ireturn
    //   2166: aload_1
    //   2167: aload_3
    //   2168: aload 12
    //   2170: aload 13
    //   2172: aload 11
    //   2174: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   2177: iconst_2
    //   2178: ireturn
    //   2179: invokestatic 142	cn/jpush/android/util/x:e	()V
    //   2182: aload_1
    //   2183: aconst_null
    //   2184: aconst_null
    //   2185: aconst_null
    //   2186: aload 11
    //   2188: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   2191: iconst_0
    //   2192: ireturn
    //   2193: aload 11
    //   2195: astore 16
    //   2197: aload 11
    //   2199: astore 17
    //   2201: aload 11
    //   2203: astore 18
    //   2205: aload 11
    //   2207: astore 19
    //   2209: aload 11
    //   2211: astore_3
    //   2212: aload 63
    //   2214: astore 12
    //   2216: aload 62
    //   2218: astore 13
    //   2220: aload_1
    //   2221: astore 14
    //   2223: aload 61
    //   2225: astore 15
    //   2227: aload 11
    //   2229: astore 20
    //   2231: aload 11
    //   2233: astore 21
    //   2235: invokestatic 368	cn/jpush/android/util/x:f	()V
    //   2238: aload 11
    //   2240: astore 16
    //   2242: aload 11
    //   2244: astore 17
    //   2246: aload 11
    //   2248: astore 18
    //   2250: aload 11
    //   2252: astore 19
    //   2254: aload 11
    //   2256: astore_3
    //   2257: aload 63
    //   2259: astore 12
    //   2261: aload 62
    //   2263: astore 13
    //   2265: aload_1
    //   2266: astore 14
    //   2268: aload 61
    //   2270: astore 15
    //   2272: aload 11
    //   2274: astore 20
    //   2276: aload 11
    //   2278: astore 21
    //   2280: aload_0
    //   2281: getfield 106	cn/jpush/android/service/a:f	Landroid/os/Bundle;
    //   2284: aload 73
    //   2286: invokevirtual 360	android/os/Bundle:remove	(Ljava/lang/String;)V
    //   2289: aload 11
    //   2291: astore 16
    //   2293: aload 11
    //   2295: astore 17
    //   2297: aload 11
    //   2299: astore 18
    //   2301: aload 11
    //   2303: astore 19
    //   2305: aload 11
    //   2307: astore_3
    //   2308: aload 63
    //   2310: astore 12
    //   2312: aload 62
    //   2314: astore 13
    //   2316: aload_1
    //   2317: astore 14
    //   2319: aload 61
    //   2321: astore 15
    //   2323: aload 11
    //   2325: astore 20
    //   2327: aload 11
    //   2329: astore 21
    //   2331: aload 29
    //   2333: invokevirtual 308	java/io/File:delete	()Z
    //   2336: ifne +48 -> 2384
    //   2339: aload 11
    //   2341: astore 16
    //   2343: aload 11
    //   2345: astore 17
    //   2347: aload 11
    //   2349: astore 18
    //   2351: aload 11
    //   2353: astore 19
    //   2355: aload 11
    //   2357: astore_3
    //   2358: aload 63
    //   2360: astore 12
    //   2362: aload 62
    //   2364: astore 13
    //   2366: aload_1
    //   2367: astore 14
    //   2369: aload 61
    //   2371: astore 15
    //   2373: aload 11
    //   2375: astore 20
    //   2377: aload 11
    //   2379: astore 21
    //   2381: invokestatic 368	cn/jpush/android/util/x:f	()V
    //   2384: aconst_null
    //   2385: aconst_null
    //   2386: aconst_null
    //   2387: aconst_null
    //   2388: aload 11
    //   2390: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   2393: iconst_0
    //   2394: ireturn
    //   2395: aload 11
    //   2397: astore 16
    //   2399: aload 11
    //   2401: astore 17
    //   2403: aload 11
    //   2405: astore 18
    //   2407: aload 11
    //   2409: astore 19
    //   2411: aload 11
    //   2413: astore_3
    //   2414: aload 63
    //   2416: astore 12
    //   2418: aload 62
    //   2420: astore 13
    //   2422: aload_1
    //   2423: astore 14
    //   2425: aload 61
    //   2427: astore 15
    //   2429: aload 11
    //   2431: astore 20
    //   2433: aload 11
    //   2435: astore 21
    //   2437: invokestatic 368	cn/jpush/android/util/x:f	()V
    //   2440: aconst_null
    //   2441: aconst_null
    //   2442: aconst_null
    //   2443: aconst_null
    //   2444: aload 11
    //   2446: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   2449: bipush 254
    //   2451: ireturn
    //   2452: iload 4
    //   2454: sipush 416
    //   2457: if_icmpne +204 -> 2661
    //   2460: aload 64
    //   2462: astore 16
    //   2464: aload 65
    //   2466: astore 17
    //   2468: aload 66
    //   2470: astore 18
    //   2472: aload 67
    //   2474: astore 19
    //   2476: aload 68
    //   2478: astore_3
    //   2479: aload 63
    //   2481: astore 12
    //   2483: aload 62
    //   2485: astore 13
    //   2487: aload_1
    //   2488: astore 14
    //   2490: aload 61
    //   2492: astore 15
    //   2494: aload 69
    //   2496: astore 20
    //   2498: aload 70
    //   2500: astore 21
    //   2502: invokestatic 368	cn/jpush/android/util/x:f	()V
    //   2505: aload 64
    //   2507: astore 16
    //   2509: aload 65
    //   2511: astore 17
    //   2513: aload 66
    //   2515: astore 18
    //   2517: aload 67
    //   2519: astore 19
    //   2521: aload 68
    //   2523: astore_3
    //   2524: aload 63
    //   2526: astore 12
    //   2528: aload 62
    //   2530: astore 13
    //   2532: aload_1
    //   2533: astore 14
    //   2535: aload 61
    //   2537: astore 15
    //   2539: aload 69
    //   2541: astore 20
    //   2543: aload 70
    //   2545: astore 21
    //   2547: aload_0
    //   2548: getfield 106	cn/jpush/android/service/a:f	Landroid/os/Bundle;
    //   2551: aload 73
    //   2553: invokevirtual 360	android/os/Bundle:remove	(Ljava/lang/String;)V
    //   2556: aload 64
    //   2558: astore 16
    //   2560: aload 65
    //   2562: astore 17
    //   2564: aload 66
    //   2566: astore 18
    //   2568: aload 67
    //   2570: astore 19
    //   2572: aload 68
    //   2574: astore_3
    //   2575: aload 63
    //   2577: astore 12
    //   2579: aload 62
    //   2581: astore 13
    //   2583: aload_1
    //   2584: astore 14
    //   2586: aload 61
    //   2588: astore 15
    //   2590: aload 69
    //   2592: astore 20
    //   2594: aload 70
    //   2596: astore 21
    //   2598: aload 29
    //   2600: invokevirtual 308	java/io/File:delete	()Z
    //   2603: ifne +48 -> 2651
    //   2606: aload 64
    //   2608: astore 16
    //   2610: aload 65
    //   2612: astore 17
    //   2614: aload 66
    //   2616: astore 18
    //   2618: aload 67
    //   2620: astore 19
    //   2622: aload 68
    //   2624: astore_3
    //   2625: aload 63
    //   2627: astore 12
    //   2629: aload 62
    //   2631: astore 13
    //   2633: aload_1
    //   2634: astore 14
    //   2636: aload 61
    //   2638: astore 15
    //   2640: aload 69
    //   2642: astore 20
    //   2644: aload 70
    //   2646: astore 21
    //   2648: invokestatic 368	cn/jpush/android/util/x:f	()V
    //   2651: aconst_null
    //   2652: aconst_null
    //   2653: aconst_null
    //   2654: aconst_null
    //   2655: aconst_null
    //   2656: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   2659: iconst_0
    //   2660: ireturn
    //   2661: iload 4
    //   2663: sipush 404
    //   2666: if_icmpne +123 -> 2789
    //   2669: aload 64
    //   2671: astore 16
    //   2673: aload 65
    //   2675: astore 17
    //   2677: aload 66
    //   2679: astore 18
    //   2681: aload 67
    //   2683: astore 19
    //   2685: aload 68
    //   2687: astore_3
    //   2688: aload 63
    //   2690: astore 12
    //   2692: aload 62
    //   2694: astore 13
    //   2696: aload_1
    //   2697: astore 14
    //   2699: aload 61
    //   2701: astore 15
    //   2703: aload 69
    //   2705: astore 20
    //   2707: aload 70
    //   2709: astore 21
    //   2711: new 158	java/lang/StringBuilder
    //   2714: dup
    //   2715: getstatic 84	cn/jpush/android/service/a:z	[Ljava/lang/String;
    //   2718: bipush 21
    //   2720: aaload
    //   2721: invokespecial 161	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2724: aload 73
    //   2726: invokevirtual 221	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2729: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2732: pop
    //   2733: aload 64
    //   2735: astore 16
    //   2737: aload 65
    //   2739: astore 17
    //   2741: aload 66
    //   2743: astore 18
    //   2745: aload 67
    //   2747: astore 19
    //   2749: aload 68
    //   2751: astore_3
    //   2752: aload 63
    //   2754: astore 12
    //   2756: aload 62
    //   2758: astore 13
    //   2760: aload_1
    //   2761: astore 14
    //   2763: aload 61
    //   2765: astore 15
    //   2767: aload 69
    //   2769: astore 20
    //   2771: aload 70
    //   2773: astore 21
    //   2775: invokestatic 170	cn/jpush/android/util/x:c	()V
    //   2778: aconst_null
    //   2779: aconst_null
    //   2780: aconst_null
    //   2781: aconst_null
    //   2782: aconst_null
    //   2783: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   2786: bipush 253
    //   2788: ireturn
    //   2789: aload 64
    //   2791: astore 16
    //   2793: aload 65
    //   2795: astore 17
    //   2797: aload 66
    //   2799: astore 18
    //   2801: aload 67
    //   2803: astore 19
    //   2805: aload 68
    //   2807: astore_3
    //   2808: aload 63
    //   2810: astore 12
    //   2812: aload 62
    //   2814: astore 13
    //   2816: aload_1
    //   2817: astore 14
    //   2819: aload 61
    //   2821: astore 15
    //   2823: aload 69
    //   2825: astore 20
    //   2827: aload 70
    //   2829: astore 21
    //   2831: new 158	java/lang/StringBuilder
    //   2834: dup
    //   2835: getstatic 84	cn/jpush/android/service/a:z	[Ljava/lang/String;
    //   2838: bipush 19
    //   2840: aaload
    //   2841: invokespecial 161	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2844: iload 4
    //   2846: invokevirtual 165	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2849: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2852: pop
    //   2853: aload 64
    //   2855: astore 16
    //   2857: aload 65
    //   2859: astore 17
    //   2861: aload 66
    //   2863: astore 18
    //   2865: aload 67
    //   2867: astore 19
    //   2869: aload 68
    //   2871: astore_3
    //   2872: aload 63
    //   2874: astore 12
    //   2876: aload 62
    //   2878: astore 13
    //   2880: aload_1
    //   2881: astore 14
    //   2883: aload 61
    //   2885: astore 15
    //   2887: aload 69
    //   2889: astore 20
    //   2891: aload 70
    //   2893: astore 21
    //   2895: invokestatic 142	cn/jpush/android/util/x:e	()V
    //   2898: aconst_null
    //   2899: aconst_null
    //   2900: aconst_null
    //   2901: aconst_null
    //   2902: aconst_null
    //   2903: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   2906: bipush 254
    //   2908: ireturn
    //   2909: aload 64
    //   2911: astore 16
    //   2913: aload 65
    //   2915: astore 17
    //   2917: aload 66
    //   2919: astore 18
    //   2921: aload 67
    //   2923: astore 19
    //   2925: aload 68
    //   2927: astore_3
    //   2928: aload 63
    //   2930: astore 12
    //   2932: aload 62
    //   2934: astore 13
    //   2936: aload_1
    //   2937: astore 14
    //   2939: aload 61
    //   2941: astore 15
    //   2943: aload 69
    //   2945: astore 20
    //   2947: aload 70
    //   2949: astore 21
    //   2951: invokestatic 142	cn/jpush/android/util/x:e	()V
    //   2954: aconst_null
    //   2955: aconst_null
    //   2956: aconst_null
    //   2957: aconst_null
    //   2958: aconst_null
    //   2959: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   2962: iconst_0
    //   2963: ireturn
    //   2964: astore_1
    //   2965: aload 45
    //   2967: astore_2
    //   2968: aload 57
    //   2970: astore_1
    //   2971: aload 30
    //   2973: astore 11
    //   2975: aload 22
    //   2977: astore 17
    //   2979: aload 16
    //   2981: astore_3
    //   2982: aload 17
    //   2984: astore 12
    //   2986: aload 11
    //   2988: astore 13
    //   2990: aload_1
    //   2991: astore 14
    //   2993: aload_2
    //   2994: astore 15
    //   2996: invokestatic 329	cn/jpush/android/util/x:j	()V
    //   2999: aload_1
    //   3000: aload_2
    //   3001: aload 11
    //   3003: aload 17
    //   3005: aload 16
    //   3007: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   3010: bipush 254
    //   3012: ireturn
    //   3013: astore_1
    //   3014: aload 46
    //   3016: astore_2
    //   3017: aload 58
    //   3019: astore_1
    //   3020: aload 31
    //   3022: astore 11
    //   3024: aload 23
    //   3026: astore 16
    //   3028: aload 17
    //   3030: astore_3
    //   3031: aload 16
    //   3033: astore 12
    //   3035: aload 11
    //   3037: astore 13
    //   3039: aload_1
    //   3040: astore 14
    //   3042: aload_2
    //   3043: astore 15
    //   3045: invokestatic 329	cn/jpush/android/util/x:j	()V
    //   3048: aload_1
    //   3049: aload_2
    //   3050: aload 11
    //   3052: aload 16
    //   3054: aload 17
    //   3056: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   3059: bipush 254
    //   3061: ireturn
    //   3062: astore_1
    //   3063: aload 47
    //   3065: astore_2
    //   3066: aload 59
    //   3068: astore_1
    //   3069: aload 32
    //   3071: astore 11
    //   3073: aload 24
    //   3075: astore 16
    //   3077: aload 18
    //   3079: astore_3
    //   3080: aload 16
    //   3082: astore 12
    //   3084: aload 11
    //   3086: astore 13
    //   3088: aload_1
    //   3089: astore 14
    //   3091: aload_2
    //   3092: astore 15
    //   3094: invokestatic 371	cn/jpush/android/util/x:h	()V
    //   3097: aload_1
    //   3098: aload_2
    //   3099: aload 11
    //   3101: aload 16
    //   3103: aload 18
    //   3105: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   3108: iconst_m1
    //   3109: ireturn
    //   3110: astore_1
    //   3111: aload 48
    //   3113: astore_2
    //   3114: aload 60
    //   3116: astore_1
    //   3117: aload 33
    //   3119: astore 11
    //   3121: aload 25
    //   3123: astore 16
    //   3125: aload 19
    //   3127: astore_3
    //   3128: aload 16
    //   3130: astore 12
    //   3132: aload 11
    //   3134: astore 13
    //   3136: aload_1
    //   3137: astore 14
    //   3139: aload_2
    //   3140: astore 15
    //   3142: invokestatic 374	cn/jpush/android/util/x:i	()V
    //   3145: aload_1
    //   3146: aload_2
    //   3147: aload 11
    //   3149: aload 16
    //   3151: aload 19
    //   3153: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   3156: bipush 254
    //   3158: ireturn
    //   3159: astore_1
    //   3160: aload 14
    //   3162: aload 15
    //   3164: aload 13
    //   3166: aload 12
    //   3168: aload_3
    //   3169: invokestatic 332	cn/jpush/android/service/a:a	(Ljava/io/InputStream;Ljava/io/BufferedInputStream;Ljava/io/FileOutputStream;Ljava/io/BufferedOutputStream;Lorg/apache/http/HttpEntity;)V
    //   3172: aload_1
    //   3173: athrow
    //   3174: new 158	java/lang/StringBuilder
    //   3177: dup
    //   3178: getstatic 84	cn/jpush/android/service/a:z	[Ljava/lang/String;
    //   3181: bipush 16
    //   3183: aaload
    //   3184: invokespecial 161	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   3187: aload 73
    //   3189: invokevirtual 221	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3192: getstatic 84	cn/jpush/android/service/a:z	[Ljava/lang/String;
    //   3195: bipush 20
    //   3197: aaload
    //   3198: invokevirtual 221	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3201: aload 12
    //   3203: invokevirtual 221	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3206: getstatic 84	cn/jpush/android/service/a:z	[Ljava/lang/String;
    //   3209: bipush 22
    //   3211: aaload
    //   3212: invokevirtual 221	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3215: aload 11
    //   3217: invokevirtual 221	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3220: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3223: pop
    //   3224: invokestatic 368	cn/jpush/android/util/x:f	()V
    //   3227: bipush 254
    //   3229: ireturn
    //   3230: astore_2
    //   3231: aload_1
    //   3232: astore 14
    //   3234: aload_2
    //   3235: astore_1
    //   3236: aload 11
    //   3238: astore_3
    //   3239: aload 28
    //   3241: astore 12
    //   3243: aload 41
    //   3245: astore 13
    //   3247: aload 49
    //   3249: astore 15
    //   3251: goto -91 -> 3160
    //   3254: astore_2
    //   3255: aload_3
    //   3256: astore 15
    //   3258: aload_1
    //   3259: astore 14
    //   3261: aload_2
    //   3262: astore_1
    //   3263: aload 11
    //   3265: astore_3
    //   3266: aload 28
    //   3268: astore 12
    //   3270: aload 41
    //   3272: astore 13
    //   3274: goto -114 -> 3160
    //   3277: astore_2
    //   3278: aload 12
    //   3280: astore 13
    //   3282: aload_3
    //   3283: astore 15
    //   3285: aload_1
    //   3286: astore 14
    //   3288: aload_2
    //   3289: astore_1
    //   3290: aload 11
    //   3292: astore_3
    //   3293: aload 28
    //   3295: astore 12
    //   3297: goto -137 -> 3160
    //   3300: astore 14
    //   3302: aload 12
    //   3304: astore_2
    //   3305: aload_3
    //   3306: astore 15
    //   3308: aload 14
    //   3310: astore_3
    //   3311: aload_1
    //   3312: astore 14
    //   3314: aload_3
    //   3315: astore_1
    //   3316: aload 11
    //   3318: astore_3
    //   3319: aload 13
    //   3321: astore 12
    //   3323: aload_2
    //   3324: astore 13
    //   3326: goto -166 -> 3160
    //   3329: astore_2
    //   3330: aload 11
    //   3332: astore 19
    //   3334: aload 25
    //   3336: astore 16
    //   3338: aload 33
    //   3340: astore 11
    //   3342: aload 48
    //   3344: astore_2
    //   3345: goto -220 -> 3125
    //   3348: astore_2
    //   3349: aload_3
    //   3350: astore_2
    //   3351: aload 11
    //   3353: astore 19
    //   3355: aload 25
    //   3357: astore 16
    //   3359: aload 33
    //   3361: astore 11
    //   3363: goto -238 -> 3125
    //   3366: astore_2
    //   3367: aload_3
    //   3368: astore_2
    //   3369: aload 11
    //   3371: astore 19
    //   3373: aload 25
    //   3375: astore 16
    //   3377: aload 12
    //   3379: astore 11
    //   3381: goto -256 -> 3125
    //   3384: astore_2
    //   3385: aload_3
    //   3386: astore_2
    //   3387: aload 11
    //   3389: astore 19
    //   3391: aload 13
    //   3393: astore 16
    //   3395: aload 12
    //   3397: astore 11
    //   3399: goto -274 -> 3125
    //   3402: astore_2
    //   3403: aload 11
    //   3405: astore 18
    //   3407: aload 24
    //   3409: astore 16
    //   3411: aload 32
    //   3413: astore 11
    //   3415: aload 47
    //   3417: astore_2
    //   3418: goto -341 -> 3077
    //   3421: astore_2
    //   3422: aload_3
    //   3423: astore_2
    //   3424: aload 11
    //   3426: astore 18
    //   3428: aload 24
    //   3430: astore 16
    //   3432: aload 32
    //   3434: astore 11
    //   3436: goto -359 -> 3077
    //   3439: astore_2
    //   3440: aload_3
    //   3441: astore_2
    //   3442: aload 11
    //   3444: astore 18
    //   3446: aload 24
    //   3448: astore 16
    //   3450: aload 12
    //   3452: astore 11
    //   3454: goto -377 -> 3077
    //   3457: astore_2
    //   3458: aload_3
    //   3459: astore_2
    //   3460: aload 11
    //   3462: astore 18
    //   3464: aload 13
    //   3466: astore 16
    //   3468: aload 12
    //   3470: astore 11
    //   3472: goto -395 -> 3077
    //   3475: astore_2
    //   3476: aload 11
    //   3478: astore 17
    //   3480: aload 23
    //   3482: astore 16
    //   3484: aload 31
    //   3486: astore 11
    //   3488: aload 46
    //   3490: astore_2
    //   3491: goto -463 -> 3028
    //   3494: astore_2
    //   3495: aload_3
    //   3496: astore_2
    //   3497: aload 11
    //   3499: astore 17
    //   3501: aload 23
    //   3503: astore 16
    //   3505: aload 31
    //   3507: astore 11
    //   3509: goto -481 -> 3028
    //   3512: astore_2
    //   3513: aload_3
    //   3514: astore_2
    //   3515: aload 11
    //   3517: astore 17
    //   3519: aload 23
    //   3521: astore 16
    //   3523: aload 12
    //   3525: astore 11
    //   3527: goto -499 -> 3028
    //   3530: astore_2
    //   3531: aload_3
    //   3532: astore_2
    //   3533: aload 11
    //   3535: astore 17
    //   3537: aload 13
    //   3539: astore 16
    //   3541: aload 12
    //   3543: astore 11
    //   3545: goto -517 -> 3028
    //   3548: astore_2
    //   3549: aload 11
    //   3551: astore 16
    //   3553: aload 22
    //   3555: astore 17
    //   3557: aload 30
    //   3559: astore 11
    //   3561: aload 45
    //   3563: astore_2
    //   3564: goto -585 -> 2979
    //   3567: astore_2
    //   3568: aload_3
    //   3569: astore_2
    //   3570: aload 11
    //   3572: astore 16
    //   3574: aload 22
    //   3576: astore 17
    //   3578: aload 30
    //   3580: astore 11
    //   3582: goto -603 -> 2979
    //   3585: astore_2
    //   3586: aload_3
    //   3587: astore_2
    //   3588: aload 11
    //   3590: astore 16
    //   3592: aload 22
    //   3594: astore 17
    //   3596: aload 12
    //   3598: astore 11
    //   3600: goto -621 -> 2979
    //   3603: astore_2
    //   3604: aload_3
    //   3605: astore_2
    //   3606: aload 11
    //   3608: astore 16
    //   3610: aload 13
    //   3612: astore 17
    //   3614: aload 12
    //   3616: astore 11
    //   3618: goto -639 -> 2979
    //   3621: astore_1
    //   3622: aload 20
    //   3624: astore 11
    //   3626: aload 27
    //   3628: astore 17
    //   3630: aload 40
    //   3632: astore 16
    //   3634: aload 56
    //   3636: astore_1
    //   3637: aload 44
    //   3639: astore_2
    //   3640: goto -1608 -> 2032
    //   3643: astore_2
    //   3644: aload 27
    //   3646: astore 17
    //   3648: aload 40
    //   3650: astore 16
    //   3652: aload 44
    //   3654: astore_2
    //   3655: goto -1623 -> 2032
    //   3658: astore_2
    //   3659: aload_3
    //   3660: astore_2
    //   3661: aload 27
    //   3663: astore 17
    //   3665: aload 40
    //   3667: astore 16
    //   3669: goto -1637 -> 2032
    //   3672: astore_2
    //   3673: aload_3
    //   3674: astore_2
    //   3675: aload 27
    //   3677: astore 17
    //   3679: aload 12
    //   3681: astore 16
    //   3683: goto -1651 -> 2032
    //   3686: astore_1
    //   3687: aload 21
    //   3689: astore 11
    //   3691: aload 26
    //   3693: astore 17
    //   3695: aload 39
    //   3697: astore 16
    //   3699: aload 55
    //   3701: astore_1
    //   3702: aload 43
    //   3704: astore_2
    //   3705: goto -1773 -> 1932
    //   3708: astore_2
    //   3709: aload 26
    //   3711: astore 17
    //   3713: aload 39
    //   3715: astore 16
    //   3717: aload 43
    //   3719: astore_2
    //   3720: goto -1788 -> 1932
    //   3723: astore_2
    //   3724: aload_3
    //   3725: astore_2
    //   3726: aload 26
    //   3728: astore 17
    //   3730: aload 39
    //   3732: astore 16
    //   3734: goto -1802 -> 1932
    //   3737: astore_2
    //   3738: aload_3
    //   3739: astore_2
    //   3740: aload 26
    //   3742: astore 17
    //   3744: aload 12
    //   3746: astore 16
    //   3748: goto -1816 -> 1932
    //   3751: astore_1
    //   3752: aload 11
    //   3754: astore_2
    //   3755: aconst_null
    //   3756: astore_3
    //   3757: aload 17
    //   3759: astore 13
    //   3761: aload 29
    //   3763: astore 14
    //   3765: goto -2532 -> 1233
    //   3768: astore_1
    //   3769: aload 16
    //   3771: astore_2
    //   3772: goto -2539 -> 1233
    //   3775: astore_3
    //   3776: aload 11
    //   3778: astore_2
    //   3779: aload_1
    //   3780: astore 15
    //   3782: aload_3
    //   3783: astore_1
    //   3784: aload 17
    //   3786: astore 13
    //   3788: aload 29
    //   3790: astore 14
    //   3792: aload 12
    //   3794: astore_3
    //   3795: goto -2562 -> 1233
    //   3798: astore 13
    //   3800: aload_1
    //   3801: astore 15
    //   3803: aload 11
    //   3805: astore_2
    //   3806: aload 13
    //   3808: astore_1
    //   3809: aload 17
    //   3811: astore 13
    //   3813: aload_3
    //   3814: astore 14
    //   3816: aload 12
    //   3818: astore_3
    //   3819: goto -2586 -> 1233
    //   3822: astore 14
    //   3824: aload_1
    //   3825: astore 15
    //   3827: aload 11
    //   3829: astore_2
    //   3830: aload 14
    //   3832: astore_1
    //   3833: aload_3
    //   3834: astore 14
    //   3836: aload 12
    //   3838: astore_3
    //   3839: goto -2606 -> 1233
    //   3842: astore 12
    //   3844: aload_1
    //   3845: astore 14
    //   3847: aload_2
    //   3848: astore 15
    //   3850: aload 11
    //   3852: astore_2
    //   3853: aload 12
    //   3855: astore_1
    //   3856: goto -2623 -> 1233
    //   3859: astore_1
    //   3860: aconst_null
    //   3861: astore 12
    //   3863: aload 21
    //   3865: astore 17
    //   3867: aload 38
    //   3869: astore_2
    //   3870: aload 53
    //   3872: astore_1
    //   3873: goto -2688 -> 1185
    //   3876: astore_1
    //   3877: aload 21
    //   3879: astore 17
    //   3881: aload 38
    //   3883: astore_2
    //   3884: aload 53
    //   3886: astore_1
    //   3887: goto -2702 -> 1185
    //   3890: astore_2
    //   3891: aload 21
    //   3893: astore 17
    //   3895: aload 38
    //   3897: astore_2
    //   3898: goto -2713 -> 1185
    //   3901: astore_2
    //   3902: aload_3
    //   3903: astore_2
    //   3904: aload 21
    //   3906: astore 17
    //   3908: goto -2723 -> 1185
    //   3911: astore_2
    //   3912: aload_3
    //   3913: astore_2
    //   3914: aload 13
    //   3916: astore 17
    //   3918: goto -2733 -> 1185
    //   3921: astore_1
    //   3922: aconst_null
    //   3923: astore 12
    //   3925: aload 20
    //   3927: astore 17
    //   3929: aload 37
    //   3931: astore_2
    //   3932: aload 52
    //   3934: astore_1
    //   3935: goto -2800 -> 1135
    //   3938: astore_1
    //   3939: aload 20
    //   3941: astore 17
    //   3943: aload 37
    //   3945: astore_2
    //   3946: aload 52
    //   3948: astore_1
    //   3949: goto -2814 -> 1135
    //   3952: astore_2
    //   3953: aload 20
    //   3955: astore 17
    //   3957: aload 37
    //   3959: astore_2
    //   3960: goto -2825 -> 1135
    //   3963: astore_2
    //   3964: aload_3
    //   3965: astore_2
    //   3966: aload 20
    //   3968: astore 17
    //   3970: goto -2835 -> 1135
    //   3973: astore_2
    //   3974: aload_3
    //   3975: astore_2
    //   3976: aload 13
    //   3978: astore 17
    //   3980: goto -2845 -> 1135
    //   3983: astore_1
    //   3984: aconst_null
    //   3985: astore 12
    //   3987: aload 19
    //   3989: astore 17
    //   3991: aload 36
    //   3993: astore_2
    //   3994: aload 51
    //   3996: astore_1
    //   3997: goto -2913 -> 1084
    //   4000: astore_1
    //   4001: aload 19
    //   4003: astore 17
    //   4005: aload 36
    //   4007: astore_2
    //   4008: aload 51
    //   4010: astore_1
    //   4011: goto -2927 -> 1084
    //   4014: astore_2
    //   4015: aload 19
    //   4017: astore 17
    //   4019: aload 36
    //   4021: astore_2
    //   4022: goto -2938 -> 1084
    //   4025: astore_2
    //   4026: aload_3
    //   4027: astore_2
    //   4028: aload 19
    //   4030: astore 17
    //   4032: goto -2948 -> 1084
    //   4035: astore_2
    //   4036: aload_3
    //   4037: astore_2
    //   4038: aload 13
    //   4040: astore 17
    //   4042: goto -2958 -> 1084
    //   4045: astore_1
    //   4046: aconst_null
    //   4047: astore 12
    //   4049: aload 18
    //   4051: astore 17
    //   4053: aload 35
    //   4055: astore_2
    //   4056: aload 50
    //   4058: astore_1
    //   4059: goto -3026 -> 1033
    //   4062: astore_1
    //   4063: aload 18
    //   4065: astore 17
    //   4067: aload 35
    //   4069: astore_2
    //   4070: aload 50
    //   4072: astore_1
    //   4073: goto -3040 -> 1033
    //   4076: astore_2
    //   4077: aload 18
    //   4079: astore 17
    //   4081: aload 35
    //   4083: astore_2
    //   4084: goto -3051 -> 1033
    //   4087: astore_2
    //   4088: aload_3
    //   4089: astore_2
    //   4090: aload 18
    //   4092: astore 17
    //   4094: goto -3061 -> 1033
    //   4097: astore_2
    //   4098: aload_3
    //   4099: astore_2
    //   4100: aload 13
    //   4102: astore 17
    //   4104: goto -3071 -> 1033
    //   4107: astore_1
    //   4108: aconst_null
    //   4109: astore 11
    //   4111: aconst_null
    //   4112: astore 12
    //   4114: aload 34
    //   4116: astore 17
    //   4118: aload 42
    //   4120: astore_2
    //   4121: aload 54
    //   4123: astore_1
    //   4124: goto -3386 -> 738
    //   4127: astore_1
    //   4128: aconst_null
    //   4129: astore 12
    //   4131: aload 34
    //   4133: astore 17
    //   4135: aload 42
    //   4137: astore_2
    //   4138: aload 54
    //   4140: astore_1
    //   4141: goto -3403 -> 738
    //   4144: astore_1
    //   4145: aload 34
    //   4147: astore 17
    //   4149: aload 42
    //   4151: astore_2
    //   4152: aload 54
    //   4154: astore_1
    //   4155: goto -3417 -> 738
    //   4158: astore_2
    //   4159: aload 34
    //   4161: astore 17
    //   4163: aload 42
    //   4165: astore_2
    //   4166: goto -3428 -> 738
    //   4169: astore_2
    //   4170: aload_3
    //   4171: astore_2
    //   4172: aload 34
    //   4174: astore 17
    //   4176: goto -3438 -> 738
    //   4179: astore_1
    //   4180: aconst_null
    //   4181: astore 11
    //   4183: aconst_null
    //   4184: astore 13
    //   4186: aconst_null
    //   4187: astore_1
    //   4188: aconst_null
    //   4189: astore_2
    //   4190: aconst_null
    //   4191: astore_3
    //   4192: goto -3581 -> 611
    //   4195: astore_1
    //   4196: aconst_null
    //   4197: astore 13
    //   4199: aconst_null
    //   4200: astore_1
    //   4201: aconst_null
    //   4202: astore_2
    //   4203: aconst_null
    //   4204: astore_3
    //   4205: goto -3594 -> 611
    //   4208: astore_1
    //   4209: aconst_null
    //   4210: astore 13
    //   4212: aconst_null
    //   4213: astore_1
    //   4214: aconst_null
    //   4215: astore_2
    //   4216: aload 12
    //   4218: astore_3
    //   4219: goto -3608 -> 611
    //   4222: astore_2
    //   4223: aconst_null
    //   4224: astore 13
    //   4226: aconst_null
    //   4227: astore 14
    //   4229: aload_1
    //   4230: astore_2
    //   4231: aload 12
    //   4233: astore_3
    //   4234: aload 14
    //   4236: astore_1
    //   4237: goto -3626 -> 611
    //   4240: astore_2
    //   4241: aconst_null
    //   4242: astore 13
    //   4244: aload_1
    //   4245: astore_2
    //   4246: aload_3
    //   4247: astore_1
    //   4248: aload 12
    //   4250: astore_3
    //   4251: goto -3640 -> 611
    //   4254: iconst_1
    //   4255: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   554	561	599	java/lang/NumberFormatException
    //   561	569	599	java/lang/NumberFormatException
    //   575	599	599	java/lang/NumberFormatException
    //   698	708	599	java/lang/NumberFormatException
    //   716	728	599	java/lang/NumberFormatException
    //   772	777	599	java/lang/NumberFormatException
    //   782	812	599	java/lang/NumberFormatException
    //   816	828	599	java/lang/NumberFormatException
    //   841	855	599	java/lang/NumberFormatException
    //   554	561	731	org/apache/http/client/ClientProtocolException
    //   561	569	731	org/apache/http/client/ClientProtocolException
    //   575	599	731	org/apache/http/client/ClientProtocolException
    //   698	708	731	org/apache/http/client/ClientProtocolException
    //   716	728	731	org/apache/http/client/ClientProtocolException
    //   772	777	731	org/apache/http/client/ClientProtocolException
    //   782	812	731	org/apache/http/client/ClientProtocolException
    //   816	828	731	org/apache/http/client/ClientProtocolException
    //   841	855	731	org/apache/http/client/ClientProtocolException
    //   411	418	1016	java/lang/IllegalStateException
    //   422	435	1016	java/lang/IllegalStateException
    //   443	451	1016	java/lang/IllegalStateException
    //   931	956	1016	java/lang/IllegalStateException
    //   967	992	1016	java/lang/IllegalStateException
    //   1003	1006	1016	java/lang/IllegalStateException
    //   411	418	1067	java/io/FileNotFoundException
    //   422	435	1067	java/io/FileNotFoundException
    //   443	451	1067	java/io/FileNotFoundException
    //   931	956	1067	java/io/FileNotFoundException
    //   967	992	1067	java/io/FileNotFoundException
    //   1003	1006	1067	java/io/FileNotFoundException
    //   411	418	1118	java/io/IOException
    //   422	435	1118	java/io/IOException
    //   443	451	1118	java/io/IOException
    //   931	956	1118	java/io/IOException
    //   967	992	1118	java/io/IOException
    //   1003	1006	1118	java/io/IOException
    //   411	418	1168	cn/jpush/android/d
    //   422	435	1168	cn/jpush/android/d
    //   443	451	1168	cn/jpush/android/d
    //   931	956	1168	cn/jpush/android/d
    //   967	992	1168	cn/jpush/android/d
    //   1003	1006	1168	cn/jpush/android/d
    //   411	418	1219	finally
    //   422	435	1219	finally
    //   443	451	1219	finally
    //   931	956	1219	finally
    //   967	992	1219	finally
    //   1003	1006	1219	finally
    //   1269	1303	1380	org/apache/http/client/ClientProtocolException
    //   1307	1319	1380	org/apache/http/client/ClientProtocolException
    //   1322	1375	1380	org/apache/http/client/ClientProtocolException
    //   1269	1303	1387	java/io/IOException
    //   1307	1319	1387	java/io/IOException
    //   1322	1375	1387	java/io/IOException
    //   1269	1303	1393	cn/jpush/android/d
    //   1307	1319	1393	cn/jpush/android/d
    //   1322	1375	1393	cn/jpush/android/d
    //   1876	1883	1921	java/lang/NumberFormatException
    //   1883	1891	1921	java/lang/NumberFormatException
    //   1897	1921	1921	java/lang/NumberFormatException
    //   1988	1998	1921	java/lang/NumberFormatException
    //   2006	2018	1921	java/lang/NumberFormatException
    //   2066	2074	1921	java/lang/NumberFormatException
    //   2079	2109	1921	java/lang/NumberFormatException
    //   2113	2125	1921	java/lang/NumberFormatException
    //   2138	2152	1921	java/lang/NumberFormatException
    //   1972	1978	1981	java/io/IOException
    //   1876	1883	2021	org/apache/http/client/ClientProtocolException
    //   1883	1891	2021	org/apache/http/client/ClientProtocolException
    //   1897	1921	2021	org/apache/http/client/ClientProtocolException
    //   1988	1998	2021	org/apache/http/client/ClientProtocolException
    //   2006	2018	2021	org/apache/http/client/ClientProtocolException
    //   2066	2074	2021	org/apache/http/client/ClientProtocolException
    //   2079	2109	2021	org/apache/http/client/ClientProtocolException
    //   2113	2125	2021	org/apache/http/client/ClientProtocolException
    //   2138	2152	2021	org/apache/http/client/ClientProtocolException
    //   1545	1554	2964	java/lang/IllegalStateException
    //   1601	1615	2964	java/lang/IllegalStateException
    //   1673	1682	2964	java/lang/IllegalStateException
    //   1724	1734	2964	java/lang/IllegalStateException
    //   1776	1790	2964	java/lang/IllegalStateException
    //   1832	1840	2964	java/lang/IllegalStateException
    //   2235	2238	2964	java/lang/IllegalStateException
    //   2280	2289	2964	java/lang/IllegalStateException
    //   2331	2339	2964	java/lang/IllegalStateException
    //   2381	2384	2964	java/lang/IllegalStateException
    //   2437	2440	2964	java/lang/IllegalStateException
    //   2502	2505	2964	java/lang/IllegalStateException
    //   2547	2556	2964	java/lang/IllegalStateException
    //   2598	2606	2964	java/lang/IllegalStateException
    //   2648	2651	2964	java/lang/IllegalStateException
    //   2711	2733	2964	java/lang/IllegalStateException
    //   2775	2778	2964	java/lang/IllegalStateException
    //   2831	2853	2964	java/lang/IllegalStateException
    //   2895	2898	2964	java/lang/IllegalStateException
    //   2951	2954	2964	java/lang/IllegalStateException
    //   1545	1554	3013	java/io/FileNotFoundException
    //   1601	1615	3013	java/io/FileNotFoundException
    //   1673	1682	3013	java/io/FileNotFoundException
    //   1724	1734	3013	java/io/FileNotFoundException
    //   1776	1790	3013	java/io/FileNotFoundException
    //   1832	1840	3013	java/io/FileNotFoundException
    //   2235	2238	3013	java/io/FileNotFoundException
    //   2280	2289	3013	java/io/FileNotFoundException
    //   2331	2339	3013	java/io/FileNotFoundException
    //   2381	2384	3013	java/io/FileNotFoundException
    //   2437	2440	3013	java/io/FileNotFoundException
    //   2502	2505	3013	java/io/FileNotFoundException
    //   2547	2556	3013	java/io/FileNotFoundException
    //   2598	2606	3013	java/io/FileNotFoundException
    //   2648	2651	3013	java/io/FileNotFoundException
    //   2711	2733	3013	java/io/FileNotFoundException
    //   2775	2778	3013	java/io/FileNotFoundException
    //   2831	2853	3013	java/io/FileNotFoundException
    //   2895	2898	3013	java/io/FileNotFoundException
    //   2951	2954	3013	java/io/FileNotFoundException
    //   1545	1554	3062	java/io/IOException
    //   1601	1615	3062	java/io/IOException
    //   1673	1682	3062	java/io/IOException
    //   1724	1734	3062	java/io/IOException
    //   1776	1790	3062	java/io/IOException
    //   1832	1840	3062	java/io/IOException
    //   2235	2238	3062	java/io/IOException
    //   2280	2289	3062	java/io/IOException
    //   2331	2339	3062	java/io/IOException
    //   2381	2384	3062	java/io/IOException
    //   2437	2440	3062	java/io/IOException
    //   2502	2505	3062	java/io/IOException
    //   2547	2556	3062	java/io/IOException
    //   2598	2606	3062	java/io/IOException
    //   2648	2651	3062	java/io/IOException
    //   2711	2733	3062	java/io/IOException
    //   2775	2778	3062	java/io/IOException
    //   2831	2853	3062	java/io/IOException
    //   2895	2898	3062	java/io/IOException
    //   2951	2954	3062	java/io/IOException
    //   1545	1554	3110	cn/jpush/android/d
    //   1601	1615	3110	cn/jpush/android/d
    //   1673	1682	3110	cn/jpush/android/d
    //   1724	1734	3110	cn/jpush/android/d
    //   1776	1790	3110	cn/jpush/android/d
    //   1832	1840	3110	cn/jpush/android/d
    //   2235	2238	3110	cn/jpush/android/d
    //   2280	2289	3110	cn/jpush/android/d
    //   2331	2339	3110	cn/jpush/android/d
    //   2381	2384	3110	cn/jpush/android/d
    //   2437	2440	3110	cn/jpush/android/d
    //   2502	2505	3110	cn/jpush/android/d
    //   2547	2556	3110	cn/jpush/android/d
    //   2598	2606	3110	cn/jpush/android/d
    //   2648	2651	3110	cn/jpush/android/d
    //   2711	2733	3110	cn/jpush/android/d
    //   2775	2778	3110	cn/jpush/android/d
    //   2831	2853	3110	cn/jpush/android/d
    //   2895	2898	3110	cn/jpush/android/d
    //   2951	2954	3110	cn/jpush/android/d
    //   1545	1554	3159	finally
    //   1601	1615	3159	finally
    //   1673	1682	3159	finally
    //   1724	1734	3159	finally
    //   1776	1790	3159	finally
    //   1832	1840	3159	finally
    //   1949	1952	3159	finally
    //   2049	2052	3159	finally
    //   2235	2238	3159	finally
    //   2280	2289	3159	finally
    //   2331	2339	3159	finally
    //   2381	2384	3159	finally
    //   2437	2440	3159	finally
    //   2502	2505	3159	finally
    //   2547	2556	3159	finally
    //   2598	2606	3159	finally
    //   2648	2651	3159	finally
    //   2711	2733	3159	finally
    //   2775	2778	3159	finally
    //   2831	2853	3159	finally
    //   2895	2898	3159	finally
    //   2951	2954	3159	finally
    //   2996	2999	3159	finally
    //   3045	3048	3159	finally
    //   3094	3097	3159	finally
    //   3142	3145	3159	finally
    //   1844	1853	3230	finally
    //   2179	2182	3230	finally
    //   1853	1865	3254	finally
    //   1865	1876	3277	finally
    //   1876	1883	3300	finally
    //   1883	1891	3300	finally
    //   1897	1921	3300	finally
    //   1988	1998	3300	finally
    //   2006	2018	3300	finally
    //   2066	2074	3300	finally
    //   2079	2109	3300	finally
    //   2113	2125	3300	finally
    //   2138	2152	3300	finally
    //   1844	1853	3329	cn/jpush/android/d
    //   2179	2182	3329	cn/jpush/android/d
    //   1853	1865	3348	cn/jpush/android/d
    //   1865	1876	3366	cn/jpush/android/d
    //   1876	1883	3384	cn/jpush/android/d
    //   1883	1891	3384	cn/jpush/android/d
    //   1897	1921	3384	cn/jpush/android/d
    //   1988	1998	3384	cn/jpush/android/d
    //   2006	2018	3384	cn/jpush/android/d
    //   2066	2074	3384	cn/jpush/android/d
    //   2079	2109	3384	cn/jpush/android/d
    //   2113	2125	3384	cn/jpush/android/d
    //   2138	2152	3384	cn/jpush/android/d
    //   1844	1853	3402	java/io/IOException
    //   2179	2182	3402	java/io/IOException
    //   1853	1865	3421	java/io/IOException
    //   1865	1876	3439	java/io/IOException
    //   1876	1883	3457	java/io/IOException
    //   1883	1891	3457	java/io/IOException
    //   1897	1921	3457	java/io/IOException
    //   1988	1998	3457	java/io/IOException
    //   2006	2018	3457	java/io/IOException
    //   2066	2074	3457	java/io/IOException
    //   2079	2109	3457	java/io/IOException
    //   2113	2125	3457	java/io/IOException
    //   2138	2152	3457	java/io/IOException
    //   1844	1853	3475	java/io/FileNotFoundException
    //   2179	2182	3475	java/io/FileNotFoundException
    //   1853	1865	3494	java/io/FileNotFoundException
    //   1865	1876	3512	java/io/FileNotFoundException
    //   1876	1883	3530	java/io/FileNotFoundException
    //   1883	1891	3530	java/io/FileNotFoundException
    //   1897	1921	3530	java/io/FileNotFoundException
    //   1988	1998	3530	java/io/FileNotFoundException
    //   2006	2018	3530	java/io/FileNotFoundException
    //   2066	2074	3530	java/io/FileNotFoundException
    //   2079	2109	3530	java/io/FileNotFoundException
    //   2113	2125	3530	java/io/FileNotFoundException
    //   2138	2152	3530	java/io/FileNotFoundException
    //   1844	1853	3548	java/lang/IllegalStateException
    //   2179	2182	3548	java/lang/IllegalStateException
    //   1853	1865	3567	java/lang/IllegalStateException
    //   1865	1876	3585	java/lang/IllegalStateException
    //   1876	1883	3603	java/lang/IllegalStateException
    //   1883	1891	3603	java/lang/IllegalStateException
    //   1897	1921	3603	java/lang/IllegalStateException
    //   1988	1998	3603	java/lang/IllegalStateException
    //   2006	2018	3603	java/lang/IllegalStateException
    //   2066	2074	3603	java/lang/IllegalStateException
    //   2079	2109	3603	java/lang/IllegalStateException
    //   2113	2125	3603	java/lang/IllegalStateException
    //   2138	2152	3603	java/lang/IllegalStateException
    //   1545	1554	3621	org/apache/http/client/ClientProtocolException
    //   1601	1615	3621	org/apache/http/client/ClientProtocolException
    //   1673	1682	3621	org/apache/http/client/ClientProtocolException
    //   1724	1734	3621	org/apache/http/client/ClientProtocolException
    //   1776	1790	3621	org/apache/http/client/ClientProtocolException
    //   1832	1840	3621	org/apache/http/client/ClientProtocolException
    //   2235	2238	3621	org/apache/http/client/ClientProtocolException
    //   2280	2289	3621	org/apache/http/client/ClientProtocolException
    //   2331	2339	3621	org/apache/http/client/ClientProtocolException
    //   2381	2384	3621	org/apache/http/client/ClientProtocolException
    //   2437	2440	3621	org/apache/http/client/ClientProtocolException
    //   2502	2505	3621	org/apache/http/client/ClientProtocolException
    //   2547	2556	3621	org/apache/http/client/ClientProtocolException
    //   2598	2606	3621	org/apache/http/client/ClientProtocolException
    //   2648	2651	3621	org/apache/http/client/ClientProtocolException
    //   2711	2733	3621	org/apache/http/client/ClientProtocolException
    //   2775	2778	3621	org/apache/http/client/ClientProtocolException
    //   2831	2853	3621	org/apache/http/client/ClientProtocolException
    //   2895	2898	3621	org/apache/http/client/ClientProtocolException
    //   2951	2954	3621	org/apache/http/client/ClientProtocolException
    //   1844	1853	3643	org/apache/http/client/ClientProtocolException
    //   2179	2182	3643	org/apache/http/client/ClientProtocolException
    //   1853	1865	3658	org/apache/http/client/ClientProtocolException
    //   1865	1876	3672	org/apache/http/client/ClientProtocolException
    //   1545	1554	3686	java/lang/NumberFormatException
    //   1601	1615	3686	java/lang/NumberFormatException
    //   1673	1682	3686	java/lang/NumberFormatException
    //   1724	1734	3686	java/lang/NumberFormatException
    //   1776	1790	3686	java/lang/NumberFormatException
    //   1832	1840	3686	java/lang/NumberFormatException
    //   2235	2238	3686	java/lang/NumberFormatException
    //   2280	2289	3686	java/lang/NumberFormatException
    //   2331	2339	3686	java/lang/NumberFormatException
    //   2381	2384	3686	java/lang/NumberFormatException
    //   2437	2440	3686	java/lang/NumberFormatException
    //   2502	2505	3686	java/lang/NumberFormatException
    //   2547	2556	3686	java/lang/NumberFormatException
    //   2598	2606	3686	java/lang/NumberFormatException
    //   2648	2651	3686	java/lang/NumberFormatException
    //   2711	2733	3686	java/lang/NumberFormatException
    //   2775	2778	3686	java/lang/NumberFormatException
    //   2831	2853	3686	java/lang/NumberFormatException
    //   2895	2898	3686	java/lang/NumberFormatException
    //   2951	2954	3686	java/lang/NumberFormatException
    //   1844	1853	3708	java/lang/NumberFormatException
    //   2179	2182	3708	java/lang/NumberFormatException
    //   1853	1865	3723	java/lang/NumberFormatException
    //   1865	1876	3737	java/lang/NumberFormatException
    //   451	496	3751	finally
    //   908	911	3751	finally
    //   512	522	3768	finally
    //   755	758	3768	finally
    //   893	896	3768	finally
    //   1050	1053	3768	finally
    //   1101	1104	3768	finally
    //   1152	1155	3768	finally
    //   1202	1205	3768	finally
    //   522	544	3775	finally
    //   544	554	3798	finally
    //   554	561	3822	finally
    //   561	569	3822	finally
    //   575	599	3822	finally
    //   698	708	3822	finally
    //   716	728	3822	finally
    //   772	777	3822	finally
    //   782	812	3822	finally
    //   816	828	3822	finally
    //   841	855	3822	finally
    //   611	614	3842	finally
    //   451	496	3859	cn/jpush/android/d
    //   908	911	3859	cn/jpush/android/d
    //   512	522	3876	cn/jpush/android/d
    //   893	896	3876	cn/jpush/android/d
    //   522	544	3890	cn/jpush/android/d
    //   544	554	3901	cn/jpush/android/d
    //   554	561	3911	cn/jpush/android/d
    //   561	569	3911	cn/jpush/android/d
    //   575	599	3911	cn/jpush/android/d
    //   698	708	3911	cn/jpush/android/d
    //   716	728	3911	cn/jpush/android/d
    //   772	777	3911	cn/jpush/android/d
    //   782	812	3911	cn/jpush/android/d
    //   816	828	3911	cn/jpush/android/d
    //   841	855	3911	cn/jpush/android/d
    //   451	496	3921	java/io/IOException
    //   908	911	3921	java/io/IOException
    //   512	522	3938	java/io/IOException
    //   893	896	3938	java/io/IOException
    //   522	544	3952	java/io/IOException
    //   544	554	3963	java/io/IOException
    //   554	561	3973	java/io/IOException
    //   561	569	3973	java/io/IOException
    //   575	599	3973	java/io/IOException
    //   698	708	3973	java/io/IOException
    //   716	728	3973	java/io/IOException
    //   772	777	3973	java/io/IOException
    //   782	812	3973	java/io/IOException
    //   816	828	3973	java/io/IOException
    //   841	855	3973	java/io/IOException
    //   451	496	3983	java/io/FileNotFoundException
    //   908	911	3983	java/io/FileNotFoundException
    //   512	522	4000	java/io/FileNotFoundException
    //   893	896	4000	java/io/FileNotFoundException
    //   522	544	4014	java/io/FileNotFoundException
    //   544	554	4025	java/io/FileNotFoundException
    //   554	561	4035	java/io/FileNotFoundException
    //   561	569	4035	java/io/FileNotFoundException
    //   575	599	4035	java/io/FileNotFoundException
    //   698	708	4035	java/io/FileNotFoundException
    //   716	728	4035	java/io/FileNotFoundException
    //   772	777	4035	java/io/FileNotFoundException
    //   782	812	4035	java/io/FileNotFoundException
    //   816	828	4035	java/io/FileNotFoundException
    //   841	855	4035	java/io/FileNotFoundException
    //   451	496	4045	java/lang/IllegalStateException
    //   908	911	4045	java/lang/IllegalStateException
    //   512	522	4062	java/lang/IllegalStateException
    //   893	896	4062	java/lang/IllegalStateException
    //   522	544	4076	java/lang/IllegalStateException
    //   544	554	4087	java/lang/IllegalStateException
    //   554	561	4097	java/lang/IllegalStateException
    //   561	569	4097	java/lang/IllegalStateException
    //   575	599	4097	java/lang/IllegalStateException
    //   698	708	4097	java/lang/IllegalStateException
    //   716	728	4097	java/lang/IllegalStateException
    //   772	777	4097	java/lang/IllegalStateException
    //   782	812	4097	java/lang/IllegalStateException
    //   816	828	4097	java/lang/IllegalStateException
    //   841	855	4097	java/lang/IllegalStateException
    //   411	418	4107	org/apache/http/client/ClientProtocolException
    //   422	435	4107	org/apache/http/client/ClientProtocolException
    //   443	451	4107	org/apache/http/client/ClientProtocolException
    //   931	956	4107	org/apache/http/client/ClientProtocolException
    //   967	992	4107	org/apache/http/client/ClientProtocolException
    //   1003	1006	4107	org/apache/http/client/ClientProtocolException
    //   451	496	4127	org/apache/http/client/ClientProtocolException
    //   908	911	4127	org/apache/http/client/ClientProtocolException
    //   512	522	4144	org/apache/http/client/ClientProtocolException
    //   893	896	4144	org/apache/http/client/ClientProtocolException
    //   522	544	4158	org/apache/http/client/ClientProtocolException
    //   544	554	4169	org/apache/http/client/ClientProtocolException
    //   411	418	4179	java/lang/NumberFormatException
    //   422	435	4179	java/lang/NumberFormatException
    //   443	451	4179	java/lang/NumberFormatException
    //   931	956	4179	java/lang/NumberFormatException
    //   967	992	4179	java/lang/NumberFormatException
    //   1003	1006	4179	java/lang/NumberFormatException
    //   451	496	4195	java/lang/NumberFormatException
    //   908	911	4195	java/lang/NumberFormatException
    //   512	522	4208	java/lang/NumberFormatException
    //   893	896	4208	java/lang/NumberFormatException
    //   522	544	4222	java/lang/NumberFormatException
    //   544	554	4240	java/lang/NumberFormatException
  }

  private static long a(HttpResponse paramHttpResponse)
  {
    long l = Long.valueOf(paramHttpResponse.getFirstHeader(z[10]).getValue()).longValue();
    if (l <= 0L)
      throw new cn.jpush.android.d(z[9]);
    return l;
  }

  private static HttpGet a(String paramString, long paramLong)
  {
    paramString = new HttpGet(paramString);
    paramString.addHeader(z[5], z[2]);
    paramString.addHeader(z[1], z[3]);
    if (paramLong >= 0L)
      paramString.addHeader(z[4], z[0] + paramLong + "-");
    return paramString;
  }

  private static DefaultHttpClient a()
  {
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    HttpProtocolParams.setVersion(localBasicHttpParams, HttpVersion.HTTP_1_1);
    HttpProtocolParams.setContentCharset(localBasicHttpParams, z[11]);
    HttpConnectionParams.setTcpNoDelay(localBasicHttpParams, true);
    HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 30000);
    HttpConnectionParams.setSoTimeout(localBasicHttpParams, 30000);
    return new DefaultHttpClient(localBasicHttpParams);
  }

  private static void a(InputStream paramInputStream, BufferedInputStream paramBufferedInputStream, FileOutputStream paramFileOutputStream, BufferedOutputStream paramBufferedOutputStream, HttpEntity paramHttpEntity)
  {
    if (paramBufferedOutputStream != null);
    try
    {
      paramBufferedOutputStream.close();
      if (paramFileOutputStream == null);
    }
    catch (IOException paramInputStream)
    {
      try
      {
        paramFileOutputStream.close();
        if (paramBufferedInputStream == null);
      }
      catch (IOException paramInputStream)
      {
        try
        {
          paramBufferedInputStream.close();
          if (paramInputStream == null);
        }
        catch (IOException paramInputStream)
        {
          try
          {
            paramInputStream.close();
            if (paramHttpEntity == null);
          }
          catch (IOException paramInputStream)
          {
            try
            {
              while (true)
              {
                paramHttpEntity.consumeContent();
                return;
                paramBufferedOutputStream = paramBufferedOutputStream;
                continue;
                paramFileOutputStream = paramFileOutputStream;
                continue;
                paramBufferedInputStream = paramBufferedInputStream;
              }
              paramInputStream = paramInputStream;
            }
            catch (IOException paramInputStream)
            {
            }
          }
        }
      }
    }
  }

  public static boolean a(int paramInt)
  {
    return (2 == paramInt) || (3 == paramInt);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.service.a
 * JD-Core Version:    0.6.2
 */