package cn.jpush.android.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.webkit.URLUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPOutputStream;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.json.JSONObject;

public final class n
{
  public static boolean a;
  private static final String[] z;

  static
  {
    String[] arrayOfString = new String[43];
    int j = 0;
    Object localObject2 = "\nGwi\"mDfq";
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
        i = 106;
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
        localObject2 = "\025Cd\027R";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "|+GH\030/e\034\004";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "|+L_\0367xPQ\0172eMHT~";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "|+D[\003,rF\004T";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i4] = localObject1;
        j = 5;
        localObject2 = "|+D[\003,rFe\035)cJe\030%cPS\0173)\034";
        i = 4;
        localObject1 = arrayOfString;
        break;
      case 4:
        localObject3[i4] = localObject1;
        j = 6;
        localObject2 = "!tVS\005.-FU\035.{M[\016\tzC]\017`:\002O\030,-";
        i = 5;
        localObject1 = arrayOfString;
        break;
      case 5:
        localObject3[i4] = localObject1;
        j = 7;
        localObject2 = "!gRV\003#vVS\005.8HI\005.";
        i = 6;
        localObject1 = arrayOfString;
        break;
      case 6:
        localObject3[i4] = localObject1;
        j = 8;
        localObject2 = "\030:cJ\032m\\GC";
        i = 7;
        localObject1 = arrayOfString;
        break;
      case 7:
        localObject3[i4] = localObject1;
        j = 9;
        localObject2 = "'mKJ";
        i = 8;
        localObject1 = arrayOfString;
        break;
      case 8:
        localObject3[i4] = localObject1;
        j = 10;
        localObject2 = "spU[\032";
        i = 9;
        localObject1 = arrayOfString;
        break;
      case 9:
        localObject3[i4] = localObject1;
        j = 11;
        localObject2 = "!yFH\005)s\fJ\0172zKI\031)xL\024+\003Tgi9\037Ygn=\017Eie9\024Vv";
        i = 10;
        localObject1 = arrayOfString;
        break;
      case 10:
        localObject3[i4] = localObject1;
        j = 12;
        localObject2 = "\003vVY\002`VQI\0172cKU\004\005ePU\030`cM\032\0136xK^J(cVJJ#{MI\017`tP[\031(7\017\032";
        i = 11;
        localObject1 = arrayOfString;
        break;
      case 11:
        localObject3[i4] = localObject1;
        j = 13;
        localObject2 = "(cVJD2xWN\017nsG\\\0135{V\027\0322xZC";
        i = 12;
        localObject1 = arrayOfString;
        break;
      case 12:
        localObject3[i4] = localObject1;
        j = 14;
        localObject2 = "\001tA_\0324:gT\t/sKT\r";
        i = 13;
        localObject1 = arrayOfString;
        break;
      case 13:
        localObject3[i4] = localObject1;
        j = 15;
        localObject2 = "\001tA_\0324";
        i = 14;
        localObject1 = arrayOfString;
        break;
      case 14:
        localObject3[i4] = localObject1;
        j = 16;
        localObject2 = "q'\f\nDp9\023\rX";
        i = 15;
        localObject1 = arrayOfString;
        break;
      case 15:
        localObject3[i4] = localObject1;
        j = 17;
        localObject2 = "#zU[\032";
        i = 16;
        localObject1 = arrayOfString;
        break;
      case 16:
        localObject3[i4] = localObject1;
        j = 18;
        localObject2 = "#xLT\017#cKL\0034n";
        i = 17;
        localObject1 = arrayOfString;
        break;
      case 17:
        localObject3[i4] = localObject1;
        j = 19;
        localObject2 = "5yKM\0130";
        i = 18;
        localObject1 = arrayOfString;
        break;
      case 18:
        localObject3[i4] = localObject1;
        j = 20;
        localObject2 = "\001bVR\0052~X[\036)xL";
        i = 19;
        localObject1 = arrayOfString;
        break;
      case 19:
        localObject3[i4] = localObject1;
        j = 21;
        localObject2 = "\tXgB\t%gVS\005.-F_\b5p";
        i = 20;
        localObject1 = arrayOfString;
        break;
      case 20:
        localObject3[i4] = localObject1;
        j = 22;
        localObject2 = "";
        i = 21;
        localObject1 = arrayOfString;
        break;
      case 21:
        localObject3[i4] = localObject1;
        j = 23;
        localObject2 = "\003xLN\017.c\017\004#xFS\004'";
        i = 22;
        localObject1 = arrayOfString;
        break;
      case 22:
        localObject3[i4] = localObject1;
        j = 24;
        localObject2 = "3cCN\03737AU\016%-";
        i = 23;
        localObject1 = arrayOfString;
        break;
      case 23:
        localObject3[i4] = localObject1;
        j = 25;
        localObject2 = "\002vQS\t`";
        i = 24;
        localObject1 = arrayOfString;
        break;
      case 24:
        localObject3[i4] = localObject1;
        j = 26;
        localObject2 = "\023rPL\01727GH\030/e\002\027J";
        i = 25;
        localObject1 = arrayOfString;
        break;
      case 25:
        localObject3[i4] = localObject1;
        j = 27;
        localObject2 = "\003xLT\017#cKU\004";
        i = 26;
        localObject1 = arrayOfString;
        break;
      case 26:
        localObject3[i4] = localObject1;
        j = 28;
        localObject2 = "\003vVY\002`Dqv:%rPo\0046rPS\f)rF\022#rRN\003/y\016\032\0024cR\032\t,~GT\036`rZ_\t5cG\032\0172eMHK";
        i = 27;
        localObject1 = arrayOfString;
        break;
      case 27:
        localObject3[i4] = localObject1;
        j = 29;
        localObject2 = "\017cJ_\030``PU\004'7P_\0310xLI\017`dV[\0365d\002\027J";
        i = 28;
        localObject1 = arrayOfString;
        break;
      case 28:
        localObject3[i4] = localObject1;
        j = 30;
        localObject2 = ".xV\032\013#tGJ\036!uN_Pt'\024\032G`";
        i = 29;
        localObject1 = arrayOfString;
        break;
      case 29:
        localObject3[i4] = localObject1;
        j = 31;
        localObject2 = "2rSO\0173c\002N\003-rMO\036z#\022\002Jm7";
        i = 30;
        localObject1 = arrayOfString;
        break;
      case 30:
        localObject3[i4] = localObject1;
        j = 32;
        localObject2 = "\bcVJ\"%{R_\030";
        i = 31;
        localObject1 = arrayOfString;
        break;
      case 31:
        localObject3[i4] = localObject1;
        j = 33;
        localObject2 = "\003{MI\017";
        i = 32;
        localObject1 = arrayOfString;
        break;
      case 32:
        localObject3[i4] = localObject1;
        j = 34;
        localObject2 = "l7WH\006z";
        i = 33;
        localObject1 = arrayOfString;
        break;
      case 33:
        localObject3[i4] = localObject1;
        j = 35;
        localObject2 = "";
        i = 34;
        localObject1 = arrayOfString;
        break;
      case 34:
        localObject3[i4] = localObject1;
        j = 36;
        localObject2 = "\022rSO\0173c\002T\00547CO\036(xPS\020%s\030\016Zq7\017\032";
        i = 35;
        localObject1 = arrayOfString;
        break;
      case 35:
        localObject3[i4] = localObject1;
        j = 37;
        localObject2 = "";
        i = 36;
        localObject1 = arrayOfString;
        break;
      case 36:
        localObject3[i4] = localObject1;
        j = 38;
        localObject2 = "!tVS\005.-JN\0360DKW\032,re_\036`:\002";
        i = 37;
        localObject1 = arrayOfString;
        break;
      case 37:
        localObject3[i4] = localObject1;
        j = 39;
        localObject2 = "\022rSO\0173c\002J\0134\002^\005%d\002T\00547GB\0033c\030\032^p#\002\027J";
        i = 38;
        localObject1 = arrayOfString;
        break;
      case 38:
        localObject3[i4] = localObject1;
        j = 40;
        localObject2 = "!tVS\005.-JN\0360PGNJm7";
        i = 39;
        localObject1 = arrayOfString;
        break;
      case 39:
        localObject3[i4] = localObject1;
        j = 41;
        localObject2 = "\003xLN\017.c\017v\017.pVR";
        i = 40;
        localObject1 = arrayOfString;
        break;
      case 40:
        localObject3[i4] = localObject1;
        j = 42;
        localObject2 = "3rPL\01727P_\0310xLI\017`qCS\0065eG\032G`";
        i = 41;
        localObject1 = arrayOfString;
        break;
      case 41:
        localObject3[i4] = localObject1;
        z = arrayOfString;
        a = false;
        return;
        i = 64;
        continue;
        i = 23;
        continue;
        i = 34;
        continue;
        i = 58;
      }
    }
  }

  public static int a(Context paramContext, JSONObject paramJSONObject, boolean paramBoolean)
  {
    Object localObject = ac.a(2);
    if (!URLUtil.isHttpUrl((String)localObject))
    {
      x.f();
      return -1;
    }
    HttpPost localHttpPost = new HttpPost((String)localObject);
    localObject = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout((HttpParams)localObject, 30000);
    HttpConnectionParams.setSoTimeout((HttpParams)localObject, 30000);
    DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient((HttpParams)localObject);
    if (paramContext.getPackageManager().checkPermission(z[11], paramContext.getPackageName()) == 0)
    {
      localObject = ((ConnectivityManager)paramContext.getSystemService(z[18])).getActiveNetworkInfo();
      if ((localObject != null) && (((NetworkInfo)localObject).getType() != 1))
      {
        localObject = ((NetworkInfo)localObject).getExtraInfo();
        if ((localObject != null) && ((((String)localObject).equals(z[17])) || (((String)localObject).equals(z[10])) || (((String)localObject).equals(z[19]))))
          localDefaultHttpClient.getParams().setParameter(z[13], new HttpHost(z[16], 80));
      }
    }
    localObject = "";
    if (paramJSONObject != null)
      localObject = paramJSONObject.toString();
    if (ai.a((String)localObject))
    {
      x.c();
      return -2;
    }
    localHttpPost.addHeader(z[15], z[7]);
    localHttpPost.addHeader(z[14], z[9]);
    localHttpPost.addHeader(z[23], z[9]);
    localHttpPost.addHeader(z[8], a.u(paramContext));
    int i;
    if (localObject == null)
    {
      paramContext = ac.a(paramContext);
      if (!ai.a(paramContext))
        break label341;
      x.c();
      i = 0;
    }
    while (true)
    {
      if (i != 0)
        break label397;
      x.c();
      return -3;
      paramContext = ac.a(paramContext, (String)localObject, 2);
      break;
      label341: paramContext = ac.b(paramContext);
      if (ai.a(paramContext))
      {
        x.c();
        i = 0;
      }
      else
      {
        localHttpPost.addHeader(z[20], z[25] + paramContext);
        i = 1;
      }
    }
    try
    {
      label397: paramJSONObject = ((String)localObject).getBytes(z[1]);
      paramContext = new ByteArrayOutputStream();
      localObject = new GZIPOutputStream(paramContext);
      ((OutputStream)localObject).write(paramJSONObject);
      ((OutputStream)localObject).close();
      paramJSONObject = paramContext.toByteArray();
      paramContext.close();
      paramContext = new ByteArrayEntity(paramJSONObject);
      paramContext.setContentEncoding(z[9]);
      localHttpPost.setEntity(paramContext);
      try
      {
        i = localDefaultHttpClient.execute(localHttpPost).getStatusLine().getStatusCode();
        new StringBuilder(z[24]).append(i).toString();
        x.c();
        switch (i)
        {
        case 401:
          if (i / 100 == 5)
          {
            return 500;
            x.e();
            return 401;
          }
        case 404:
          return 404;
        case 429:
          return 429;
          return -5;
        default:
        case 200:
        }
      }
      catch (ClientProtocolException paramContext)
      {
        new StringBuilder(z[22]).append(paramContext.getMessage()).toString();
        x.f();
        return -1;
      }
      catch (IOException paramContext)
      {
        while (true)
        {
          new StringBuilder(z[21]).append(paramContext.getMessage()).toString();
          x.f();
        }
      }
      catch (AssertionError paramContext)
      {
        while (true)
        {
          new StringBuilder(z[12]).append(paramContext.toString()).toString();
          x.f();
        }
      }
    }
    catch (UnsupportedEncodingException paramContext)
    {
      return -4;
    }
    catch (IOException paramContext)
    {
      return -4;
    }
    return 200;
  }

  // ERROR //
  public static String a(String paramString, int paramInt, long paramLong)
  {
    // Byte code:
    //   0: new 253	java/lang/StringBuilder
    //   3: dup
    //   4: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   7: bipush 38
    //   9: aaload
    //   10: invokespecial 254	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   13: aload_0
    //   14: invokevirtual 258	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   17: invokevirtual 259	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   20: pop
    //   21: invokestatic 230	cn/jpush/android/util/x:c	()V
    //   24: lload_2
    //   25: ldc2_w 332
    //   28: lcmp
    //   29: iflt +14 -> 43
    //   32: lload_2
    //   33: lstore 8
    //   35: lload_2
    //   36: ldc2_w 334
    //   39: lcmp
    //   40: ifle +8 -> 48
    //   43: ldc2_w 336
    //   46: lstore 8
    //   48: new 339	org/apache/http/client/methods/HttpGet
    //   51: dup
    //   52: aload_0
    //   53: invokespecial 340	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   56: astore 10
    //   58: aload 10
    //   60: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   63: bipush 27
    //   65: aaload
    //   66: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   69: bipush 33
    //   71: aaload
    //   72: invokevirtual 341	org/apache/http/client/methods/HttpGet:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   75: iconst_0
    //   76: istore_1
    //   77: iload_1
    //   78: istore 5
    //   80: iload_1
    //   81: istore 6
    //   83: iload_1
    //   84: istore 7
    //   86: iconst_1
    //   87: putstatic 114	cn/jpush/android/util/n:a	Z
    //   90: iload_1
    //   91: istore 5
    //   93: iload_1
    //   94: istore 6
    //   96: iload_1
    //   97: istore 7
    //   99: invokestatic 344	cn/jpush/android/util/n:a	()Lorg/apache/http/impl/client/DefaultHttpClient;
    //   102: aload 10
    //   104: invokevirtual 302	org/apache/http/impl/client/DefaultHttpClient:execute	(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
    //   107: astore 13
    //   109: aload 13
    //   111: ifnull +136 -> 247
    //   114: iload_1
    //   115: istore 5
    //   117: iload_1
    //   118: istore 6
    //   120: iload_1
    //   121: istore 7
    //   123: aload 13
    //   125: invokeinterface 308 1 0
    //   130: astore 11
    //   132: aload 11
    //   134: ifnull +113 -> 247
    //   137: aload 13
    //   139: invokeinterface 308 1 0
    //   144: invokeinterface 313 1 0
    //   149: istore_1
    //   150: aconst_null
    //   151: astore 11
    //   153: iload_1
    //   154: sipush 200
    //   157: if_icmplt +314 -> 471
    //   160: iload_1
    //   161: sipush 300
    //   164: if_icmpge +307 -> 471
    //   167: aload 13
    //   169: invokeinterface 348 1 0
    //   174: astore 10
    //   176: aload 10
    //   178: astore_0
    //   179: aload 10
    //   181: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   184: iconst_1
    //   185: aaload
    //   186: invokestatic 353	org/apache/http/util/EntityUtils:toString	(Lorg/apache/http/HttpEntity;Ljava/lang/String;)Ljava/lang/String;
    //   189: astore 11
    //   191: aload 11
    //   193: astore_0
    //   194: aload 11
    //   196: ifnonnull +22 -> 218
    //   199: aload 10
    //   201: astore_0
    //   202: invokestatic 356	cn/jpush/android/util/x:h	()V
    //   205: aload 10
    //   207: astore_0
    //   208: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   211: iconst_2
    //   212: aaload
    //   213: astore 11
    //   215: aload 11
    //   217: astore_0
    //   218: aload_0
    //   219: astore 11
    //   221: aload 13
    //   223: ifnull +21 -> 244
    //   226: aload_0
    //   227: astore 11
    //   229: aload 10
    //   231: ifnull +13 -> 244
    //   234: aload 10
    //   236: invokeinterface 361 1 0
    //   241: aload_0
    //   242: astore 11
    //   244: aload 11
    //   246: areturn
    //   247: iload_1
    //   248: iconst_1
    //   249: iadd
    //   250: istore 4
    //   252: iload 4
    //   254: istore_1
    //   255: iload 4
    //   257: iconst_5
    //   258: if_icmplt +62 -> 320
    //   261: iload 4
    //   263: istore 5
    //   265: iload 4
    //   267: istore 6
    //   269: iload 4
    //   271: istore 7
    //   273: aload 10
    //   275: invokevirtual 364	org/apache/http/client/methods/HttpGet:abort	()V
    //   278: iload 4
    //   280: istore 5
    //   282: iload 4
    //   284: istore 6
    //   286: iload 4
    //   288: istore 7
    //   290: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   293: iconst_5
    //   294: aaload
    //   295: astore 11
    //   297: aload 11
    //   299: areturn
    //   300: astore 11
    //   302: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   305: bipush 32
    //   307: aaload
    //   308: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   311: bipush 28
    //   313: aaload
    //   314: invokestatic 366	cn/jpush/android/util/x:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   317: iload 5
    //   319: istore_1
    //   320: lload 8
    //   322: invokestatic 372	java/lang/Thread:sleep	(J)V
    //   325: goto -248 -> 77
    //   328: astore 11
    //   330: goto -253 -> 77
    //   333: astore 11
    //   335: invokestatic 356	cn/jpush/android/util/x:h	()V
    //   338: iload 6
    //   340: istore_1
    //   341: goto -21 -> 320
    //   344: astore 11
    //   346: new 253	java/lang/StringBuilder
    //   349: dup
    //   350: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   353: bipush 12
    //   355: aaload
    //   356: invokespecial 254	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   359: aload 11
    //   361: invokevirtual 324	java/lang/AssertionError:toString	()Ljava/lang/String;
    //   364: invokevirtual 258	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   367: invokevirtual 259	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   370: pop
    //   371: iload 7
    //   373: istore_1
    //   374: invokestatic 140	cn/jpush/android/util/x:f	()V
    //   377: goto -57 -> 320
    //   380: astore 10
    //   382: invokestatic 375	cn/jpush/android/util/x:i	()V
    //   385: aload_0
    //   386: areturn
    //   387: astore_0
    //   388: aconst_null
    //   389: astore 10
    //   391: aload 10
    //   393: astore_0
    //   394: invokestatic 378	cn/jpush/android/util/x:g	()V
    //   397: aload 10
    //   399: astore_0
    //   400: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   403: iconst_2
    //   404: aaload
    //   405: astore 12
    //   407: aload 12
    //   409: astore 11
    //   411: aload 13
    //   413: ifnull -169 -> 244
    //   416: aload 12
    //   418: astore 11
    //   420: aload 10
    //   422: ifnull -178 -> 244
    //   425: aload 10
    //   427: invokeinterface 361 1 0
    //   432: aload 12
    //   434: areturn
    //   435: astore_0
    //   436: invokestatic 375	cn/jpush/android/util/x:i	()V
    //   439: aload 12
    //   441: areturn
    //   442: astore 10
    //   444: aconst_null
    //   445: astore_0
    //   446: aload 13
    //   448: ifnull +13 -> 461
    //   451: aload_0
    //   452: ifnull +9 -> 461
    //   455: aload_0
    //   456: invokeinterface 361 1 0
    //   461: aload 10
    //   463: athrow
    //   464: astore_0
    //   465: invokestatic 375	cn/jpush/android/util/x:i	()V
    //   468: goto -7 -> 461
    //   471: iload_1
    //   472: sipush 400
    //   475: if_icmplt +232 -> 707
    //   478: iload_1
    //   479: sipush 500
    //   482: if_icmpge +225 -> 707
    //   485: sipush 400
    //   488: iload_1
    //   489: if_icmpne +33 -> 522
    //   492: new 253	java/lang/StringBuilder
    //   495: dup
    //   496: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   499: bipush 35
    //   501: aaload
    //   502: invokespecial 254	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   505: aload_0
    //   506: invokevirtual 258	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   509: invokevirtual 259	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   512: pop
    //   513: invokestatic 230	cn/jpush/android/util/x:c	()V
    //   516: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   519: iconst_4
    //   520: aaload
    //   521: areturn
    //   522: sipush 401
    //   525: iload_1
    //   526: if_icmpne +33 -> 559
    //   529: new 253	java/lang/StringBuilder
    //   532: dup
    //   533: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   536: bipush 36
    //   538: aaload
    //   539: invokespecial 254	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   542: aload_0
    //   543: invokevirtual 258	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   546: invokevirtual 259	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   549: pop
    //   550: invokestatic 230	cn/jpush/android/util/x:c	()V
    //   553: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   556: iconst_2
    //   557: aaload
    //   558: areturn
    //   559: sipush 404
    //   562: iload_1
    //   563: if_icmpne +33 -> 596
    //   566: new 253	java/lang/StringBuilder
    //   569: dup
    //   570: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   573: bipush 39
    //   575: aaload
    //   576: invokespecial 254	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   579: aload_0
    //   580: invokevirtual 258	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   583: invokevirtual 259	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   586: pop
    //   587: invokestatic 230	cn/jpush/android/util/x:c	()V
    //   590: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   593: iconst_2
    //   594: aaload
    //   595: areturn
    //   596: sipush 406
    //   599: iload_1
    //   600: if_icmpne +33 -> 633
    //   603: new 253	java/lang/StringBuilder
    //   606: dup
    //   607: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   610: bipush 30
    //   612: aaload
    //   613: invokespecial 254	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   616: aload_0
    //   617: invokevirtual 258	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   620: invokevirtual 259	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   623: pop
    //   624: invokestatic 230	cn/jpush/android/util/x:c	()V
    //   627: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   630: iconst_2
    //   631: aaload
    //   632: areturn
    //   633: sipush 408
    //   636: iload_1
    //   637: if_icmpne +33 -> 670
    //   640: new 253	java/lang/StringBuilder
    //   643: dup
    //   644: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   647: bipush 31
    //   649: aaload
    //   650: invokespecial 254	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   653: aload_0
    //   654: invokevirtual 258	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   657: invokevirtual 259	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   660: pop
    //   661: invokestatic 230	cn/jpush/android/util/x:c	()V
    //   664: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   667: iconst_2
    //   668: aaload
    //   669: areturn
    //   670: sipush 409
    //   673: iload_1
    //   674: if_icmpne -430 -> 244
    //   677: new 253	java/lang/StringBuilder
    //   680: dup
    //   681: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   684: bipush 37
    //   686: aaload
    //   687: invokespecial 254	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   690: aload_0
    //   691: invokevirtual 258	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   694: invokevirtual 259	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   697: pop
    //   698: invokestatic 230	cn/jpush/android/util/x:c	()V
    //   701: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   704: iconst_2
    //   705: aaload
    //   706: areturn
    //   707: iload_1
    //   708: sipush 500
    //   711: if_icmplt +53 -> 764
    //   714: iload_1
    //   715: sipush 600
    //   718: if_icmpge +46 -> 764
    //   721: new 253	java/lang/StringBuilder
    //   724: dup
    //   725: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   728: bipush 26
    //   730: aaload
    //   731: invokespecial 254	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   734: iload_1
    //   735: invokevirtual 316	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   738: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   741: bipush 34
    //   743: aaload
    //   744: invokevirtual 258	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   747: aload_0
    //   748: invokevirtual 258	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   751: invokevirtual 259	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   754: pop
    //   755: invokestatic 230	cn/jpush/android/util/x:c	()V
    //   758: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   761: iconst_2
    //   762: aaload
    //   763: areturn
    //   764: new 253	java/lang/StringBuilder
    //   767: dup
    //   768: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   771: bipush 29
    //   773: aaload
    //   774: invokespecial 254	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   777: iload_1
    //   778: invokevirtual 316	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   781: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   784: bipush 34
    //   786: aaload
    //   787: invokevirtual 258	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   790: aload_0
    //   791: invokevirtual 258	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   794: invokevirtual 259	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   797: pop
    //   798: invokestatic 230	cn/jpush/android/util/x:c	()V
    //   801: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   804: iconst_2
    //   805: aaload
    //   806: areturn
    //   807: astore 10
    //   809: goto -363 -> 446
    //   812: astore_0
    //   813: goto -422 -> 391
    //
    // Exception table:
    //   from	to	target	type
    //   86	90	300	javax/net/ssl/SSLPeerUnverifiedException
    //   99	109	300	javax/net/ssl/SSLPeerUnverifiedException
    //   123	132	300	javax/net/ssl/SSLPeerUnverifiedException
    //   273	278	300	javax/net/ssl/SSLPeerUnverifiedException
    //   290	297	300	javax/net/ssl/SSLPeerUnverifiedException
    //   320	325	328	java/lang/InterruptedException
    //   86	90	333	java/lang/Exception
    //   99	109	333	java/lang/Exception
    //   123	132	333	java/lang/Exception
    //   273	278	333	java/lang/Exception
    //   290	297	333	java/lang/Exception
    //   86	90	344	java/lang/AssertionError
    //   99	109	344	java/lang/AssertionError
    //   123	132	344	java/lang/AssertionError
    //   273	278	344	java/lang/AssertionError
    //   290	297	344	java/lang/AssertionError
    //   234	241	380	java/io/IOException
    //   167	176	387	java/lang/Exception
    //   425	432	435	java/io/IOException
    //   167	176	442	finally
    //   455	461	464	java/io/IOException
    //   179	191	807	finally
    //   202	205	807	finally
    //   208	215	807	finally
    //   394	397	807	finally
    //   400	407	807	finally
    //   179	191	812	java/lang/Exception
    //   202	205	812	java/lang/Exception
    //   208	215	812	java/lang/Exception
  }

  public static DefaultHttpClient a()
  {
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    HttpProtocolParams.setVersion(localBasicHttpParams, HttpVersion.HTTP_1_1);
    HttpProtocolParams.setContentCharset(localBasicHttpParams, z[1]);
    HttpProtocolParams.setUserAgent(localBasicHttpParams, z[0]);
    HttpConnectionParams.setTcpNoDelay(localBasicHttpParams, true);
    HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 30000);
    HttpConnectionParams.setSoTimeout(localBasicHttpParams, 30000);
    return new DefaultHttpClient(localBasicHttpParams);
  }

  public static void a(String paramString1, String paramString2, p paramp)
  {
    try
    {
      new StringBuilder(z[6]).append(paramString1).toString();
      x.b();
      if ((TextUtils.isEmpty(paramString1)) || (paramString2 == null))
        paramp.a(false, "");
      while (true)
      {
        return;
        new Thread(new o(paramString1.trim(), paramString2, paramp)).start();
      }
    }
    finally
    {
    }
    throw paramString1;
  }

  public static boolean a(String paramString)
  {
    return (TextUtils.isEmpty(paramString)) || (paramString.equals(z[2])) || (paramString.equals(z[4])) || (paramString.equals(z[5])) || (paramString.equals(z[3]));
  }

  public static byte[] a(String paramString, int paramInt1, long paramLong, int paramInt2)
  {
    byte[] arrayOfByte1 = null;
    paramInt1 = 0;
    byte[] arrayOfByte2;
    while (true)
    {
      arrayOfByte2 = arrayOfByte1;
      if (paramInt1 >= 4)
        break;
      arrayOfByte1 = b(paramString, 5, 5000L);
      arrayOfByte2 = arrayOfByte1;
      if (arrayOfByte1 != null)
        break;
      paramInt1 += 1;
    }
    return arrayOfByte2;
  }

  // ERROR //
  private static byte[] b(String paramString, int paramInt, long paramLong)
  {
    // Byte code:
    //   0: iload_1
    //   1: ifle +12 -> 13
    //   4: iload_1
    //   5: istore 4
    //   7: iload_1
    //   8: bipush 10
    //   10: if_icmple +6 -> 16
    //   13: iconst_1
    //   14: istore 4
    //   16: lload_2
    //   17: ldc2_w 332
    //   20: lcmp
    //   21: iflt +14 -> 35
    //   24: lload_2
    //   25: lstore 5
    //   27: lload_2
    //   28: ldc2_w 334
    //   31: lcmp
    //   32: ifle +8 -> 40
    //   35: ldc2_w 336
    //   38: lstore 5
    //   40: new 253	java/lang/StringBuilder
    //   43: dup
    //   44: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   47: bipush 40
    //   49: aaload
    //   50: invokespecial 254	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   53: aload_0
    //   54: invokevirtual 258	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: invokevirtual 259	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   60: pop
    //   61: invokestatic 230	cn/jpush/android/util/x:c	()V
    //   64: new 339	org/apache/http/client/methods/HttpGet
    //   67: dup
    //   68: aload_0
    //   69: invokespecial 340	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   72: astore 7
    //   74: aload 7
    //   76: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   79: bipush 27
    //   81: aaload
    //   82: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   85: bipush 33
    //   87: aaload
    //   88: invokevirtual 341	org/apache/http/client/methods/HttpGet:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   91: iconst_0
    //   92: istore_1
    //   93: iconst_1
    //   94: putstatic 114	cn/jpush/android/util/n:a	Z
    //   97: invokestatic 344	cn/jpush/android/util/n:a	()Lorg/apache/http/impl/client/DefaultHttpClient;
    //   100: aload 7
    //   102: invokevirtual 302	org/apache/http/impl/client/DefaultHttpClient:execute	(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
    //   105: astore 8
    //   107: aload 8
    //   109: ifnull +67 -> 176
    //   112: aload 8
    //   114: invokeinterface 308 1 0
    //   119: invokeinterface 313 1 0
    //   124: istore_1
    //   125: sipush 200
    //   128: iload_1
    //   129: if_icmpne +280 -> 409
    //   132: aload 8
    //   134: invokeinterface 348 1 0
    //   139: astore_0
    //   140: aload_0
    //   141: ifnonnull +84 -> 225
    //   144: invokestatic 230	cn/jpush/android/util/x:c	()V
    //   147: aload_0
    //   148: ifnull +9 -> 157
    //   151: aload_0
    //   152: invokeinterface 361 1 0
    //   157: aconst_null
    //   158: areturn
    //   159: astore 8
    //   161: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   164: bipush 32
    //   166: aaload
    //   167: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   170: bipush 28
    //   172: aaload
    //   173: invokestatic 366	cn/jpush/android/util/x:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   176: iload_1
    //   177: iconst_1
    //   178: iadd
    //   179: istore_1
    //   180: iload_1
    //   181: iload 4
    //   183: if_icmplt +18 -> 201
    //   186: aload 7
    //   188: invokevirtual 364	org/apache/http/client/methods/HttpGet:abort	()V
    //   191: aconst_null
    //   192: areturn
    //   193: astore 8
    //   195: invokestatic 356	cn/jpush/android/util/x:h	()V
    //   198: goto -22 -> 176
    //   201: iload_1
    //   202: i2l
    //   203: lstore_2
    //   204: lload_2
    //   205: lload 5
    //   207: lmul
    //   208: invokestatic 372	java/lang/Thread:sleep	(J)V
    //   211: goto -118 -> 93
    //   214: astore 8
    //   216: goto -123 -> 93
    //   219: astore_0
    //   220: invokestatic 356	cn/jpush/android/util/x:h	()V
    //   223: aconst_null
    //   224: areturn
    //   225: aload 8
    //   227: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   230: bipush 41
    //   232: aaload
    //   233: invokeinterface 438 2 0
    //   238: astore 7
    //   240: aload 7
    //   242: ifnull +60 -> 302
    //   245: aload 7
    //   247: invokeinterface 443 1 0
    //   252: astore 7
    //   254: aload 7
    //   256: ifnull +52 -> 308
    //   259: aload 7
    //   261: invokestatic 449	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   264: istore_1
    //   265: aload 8
    //   267: invokeinterface 348 1 0
    //   272: invokestatic 452	org/apache/http/util/EntityUtils:toByteArray	(Lorg/apache/http/HttpEntity;)[B
    //   275: astore 7
    //   277: iload_1
    //   278: ifne +35 -> 313
    //   281: invokestatic 230	cn/jpush/android/util/x:c	()V
    //   284: aload_0
    //   285: ifnull -128 -> 157
    //   288: aload_0
    //   289: invokeinterface 361 1 0
    //   294: aconst_null
    //   295: areturn
    //   296: astore_0
    //   297: invokestatic 356	cn/jpush/android/util/x:h	()V
    //   300: aconst_null
    //   301: areturn
    //   302: aconst_null
    //   303: astore 7
    //   305: goto -51 -> 254
    //   308: iconst_0
    //   309: istore_1
    //   310: goto -45 -> 265
    //   313: aload 7
    //   315: arraylength
    //   316: iload_1
    //   317: if_icmpge +24 -> 341
    //   320: invokestatic 230	cn/jpush/android/util/x:c	()V
    //   323: aload_0
    //   324: ifnull -167 -> 157
    //   327: aload_0
    //   328: invokeinterface 361 1 0
    //   333: aconst_null
    //   334: areturn
    //   335: astore_0
    //   336: invokestatic 356	cn/jpush/android/util/x:h	()V
    //   339: aconst_null
    //   340: areturn
    //   341: aload_0
    //   342: ifnull +9 -> 351
    //   345: aload_0
    //   346: invokeinterface 361 1 0
    //   351: aload 7
    //   353: areturn
    //   354: astore_0
    //   355: invokestatic 356	cn/jpush/android/util/x:h	()V
    //   358: goto -7 -> 351
    //   361: astore_0
    //   362: aconst_null
    //   363: astore_0
    //   364: invokestatic 356	cn/jpush/android/util/x:h	()V
    //   367: aload_0
    //   368: ifnull -211 -> 157
    //   371: aload_0
    //   372: invokeinterface 361 1 0
    //   377: aconst_null
    //   378: areturn
    //   379: astore_0
    //   380: invokestatic 356	cn/jpush/android/util/x:h	()V
    //   383: aconst_null
    //   384: areturn
    //   385: astore 7
    //   387: aconst_null
    //   388: astore_0
    //   389: aload_0
    //   390: ifnull +9 -> 399
    //   393: aload_0
    //   394: invokeinterface 361 1 0
    //   399: aload 7
    //   401: athrow
    //   402: astore_0
    //   403: invokestatic 356	cn/jpush/android/util/x:h	()V
    //   406: goto -7 -> 399
    //   409: sipush 400
    //   412: iload_1
    //   413: if_icmpne +29 -> 442
    //   416: new 253	java/lang/StringBuilder
    //   419: dup
    //   420: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   423: bipush 42
    //   425: aaload
    //   426: invokespecial 254	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   429: aload_0
    //   430: invokevirtual 258	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   433: invokevirtual 259	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   436: pop
    //   437: invokestatic 230	cn/jpush/android/util/x:c	()V
    //   440: aconst_null
    //   441: areturn
    //   442: sipush 404
    //   445: iload_1
    //   446: if_icmpne +29 -> 475
    //   449: new 253	java/lang/StringBuilder
    //   452: dup
    //   453: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   456: bipush 39
    //   458: aaload
    //   459: invokespecial 254	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   462: aload_0
    //   463: invokevirtual 258	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   466: invokevirtual 259	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   469: pop
    //   470: invokestatic 230	cn/jpush/android/util/x:c	()V
    //   473: aconst_null
    //   474: areturn
    //   475: new 253	java/lang/StringBuilder
    //   478: dup
    //   479: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   482: bipush 29
    //   484: aaload
    //   485: invokespecial 254	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   488: iload_1
    //   489: invokevirtual 316	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   492: getstatic 112	cn/jpush/android/util/n:z	[Ljava/lang/String;
    //   495: bipush 34
    //   497: aaload
    //   498: invokevirtual 258	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   501: aload_0
    //   502: invokevirtual 258	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   505: invokevirtual 259	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   508: pop
    //   509: invokestatic 230	cn/jpush/android/util/x:c	()V
    //   512: aconst_null
    //   513: areturn
    //   514: astore 7
    //   516: goto -127 -> 389
    //   519: astore 7
    //   521: goto -132 -> 389
    //   524: astore 7
    //   526: goto -162 -> 364
    //   529: astore_0
    //   530: aconst_null
    //   531: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   93	107	159	javax/net/ssl/SSLPeerUnverifiedException
    //   93	107	193	java/lang/Exception
    //   204	211	214	java/lang/InterruptedException
    //   151	157	219	java/io/IOException
    //   288	294	296	java/io/IOException
    //   327	333	335	java/io/IOException
    //   345	351	354	java/io/IOException
    //   132	140	361	java/lang/Exception
    //   371	377	379	java/io/IOException
    //   132	140	385	finally
    //   393	399	402	java/io/IOException
    //   144	147	514	finally
    //   225	240	514	finally
    //   245	254	514	finally
    //   259	265	514	finally
    //   265	277	514	finally
    //   281	284	514	finally
    //   313	323	514	finally
    //   364	367	519	finally
    //   144	147	524	java/lang/Exception
    //   225	240	524	java/lang/Exception
    //   245	254	524	java/lang/Exception
    //   259	265	524	java/lang/Exception
    //   265	277	524	java/lang/Exception
    //   281	284	524	java/lang/Exception
    //   313	323	524	java/lang/Exception
    //   64	74	529	java/lang/Exception
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.util.n
 * JD-Core Version:    0.6.2
 */