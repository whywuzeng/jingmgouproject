package com.baidu.lbsapi.auth;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.json.JSONException;
import org.json.JSONObject;

class c
{
  private final String a = "HttpAsyncTask";
  private Context b;
  private String c = null;
  private String d = null;
  private HashMap<String, String> e = null;
  private a<String> f = null;

  protected c(Context paramContext)
  {
    this.b = paramContext;
  }

  private String a(Context paramContext)
  {
    String str1 = "wifi";
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((paramContext == null) || (!paramContext.isAvailable()))
      return "";
    String str2 = paramContext.getExtraInfo();
    paramContext = str1;
    if (str2 != null)
      if ((!str2.trim().toLowerCase().equals("cmwap")) && (!str2.trim().toLowerCase().equals("uniwap")) && (!str2.trim().toLowerCase().equals("3gwap")))
      {
        paramContext = str1;
        if (!str2.trim().toLowerCase().equals("ctwap"));
      }
      else
      {
        if (!str2.trim().toLowerCase().equals("ctwap"))
          break label123;
      }
    label123: for (paramContext = "ctwap"; ; paramContext = "cmwap")
      return paramContext;
  }

  private HashMap<String, String> a(HashMap<String, String> paramHashMap)
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramHashMap.keySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localHashMap;
      String str = localIterator.next().toString();
      localHashMap.put(str, (String)paramHashMap.get(str));
    }
  }

  private HttpClient a()
  {
    try
    {
      Object localObject = KeyStore.getInstance(KeyStore.getDefaultType());
      ((KeyStore)localObject).load(null, null);
      localObject = new b((KeyStore)localObject);
      ((org.apache.http.conn.ssl.SSLSocketFactory)localObject).setHostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
      BasicHttpParams localBasicHttpParams = new BasicHttpParams();
      HttpProtocolParams.setVersion(localBasicHttpParams, HttpVersion.HTTP_1_1);
      HttpProtocolParams.setContentCharset(localBasicHttpParams, "UTF-8");
      SchemeRegistry localSchemeRegistry = new SchemeRegistry();
      localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
      localSchemeRegistry.register(new Scheme("https", (SocketFactory)localObject, 443));
      localObject = new DefaultHttpClient(new ThreadSafeClientConnManager(localBasicHttpParams, localSchemeRegistry), localBasicHttpParams);
      return localObject;
    }
    catch (Exception localException)
    {
    }
    return new DefaultHttpClient();
  }

  private void a(String paramString)
  {
    try
    {
      paramString = new JSONObject(paramString);
    }
    catch (JSONException paramString)
    {
      try
      {
        if (!paramString.has("status"))
        {
          paramString.put("status", -1);
          a locala;
          if (this.f != null)
          {
            locala = this.f;
            if (paramString == null)
              break label83;
          }
          label83: for (paramString = paramString.toString(); ; paramString = new JSONObject().toString())
          {
            while (true)
            {
              locala.a(paramString);
              return;
              paramString = paramString;
              paramString = new JSONObject();
              try
              {
                paramString.put("status", -1);
              }
              catch (JSONException localJSONException)
              {
                localJSONException.printStackTrace();
              }
            }
            break;
          }
        }
      }
      catch (JSONException paramString)
      {
        while (true);
      }
    }
  }

  private void a(String paramString, HashMap<String, String> paramHashMap)
  {
    if (a.a)
      a.a("syncConnect start Thread id = " + String.valueOf(Thread.currentThread().getId()));
    HttpClient localHttpClient = a();
    b(localHttpClient);
    if (a(localHttpClient))
      a(localHttpClient, paramString, paramHashMap);
    while (true)
    {
      if (a.a)
        a.a("syncConnect end");
      return;
      a(ErrorMessage.a("Current network is not available."));
    }
  }

  // ERROR //
  private void a(HttpClient paramHttpClient, String paramString, HashMap<String, String> paramHashMap)
  {
    // Byte code:
    //   0: getstatic 242	com/baidu/lbsapi/auth/a:a	Z
    //   3: ifeq +9 -> 12
    //   6: ldc_w 298
    //   9: invokestatic 268	com/baidu/lbsapi/auth/a:a	(Ljava/lang/String;)V
    //   12: dconst_0
    //   13: dstore 4
    //   15: getstatic 242	com/baidu/lbsapi/auth/a:a	Z
    //   18: ifeq +9 -> 27
    //   21: invokestatic 303	java/lang/System:currentTimeMillis	()J
    //   24: l2d
    //   25: dstore 4
    //   27: new 305	org/apache/http/client/methods/HttpPost
    //   30: dup
    //   31: aload_2
    //   32: invokespecial 306	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   35: astore 8
    //   37: getstatic 242	com/baidu/lbsapi/auth/a:a	Z
    //   40: ifeq +23 -> 63
    //   43: new 244	java/lang/StringBuilder
    //   46: dup
    //   47: ldc_w 308
    //   50: invokespecial 247	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   53: aload_2
    //   54: invokevirtual 265	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: invokevirtual 266	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   60: invokestatic 268	com/baidu/lbsapi/auth/a:a	(Ljava/lang/String;)V
    //   63: aload_3
    //   64: ifnonnull +14 -> 78
    //   67: aload_0
    //   68: ldc_w 310
    //   71: invokestatic 288	com/baidu/lbsapi/auth/ErrorMessage:a	(Ljava/lang/String;)Ljava/lang/String;
    //   74: invokespecial 289	com/baidu/lbsapi/auth/c:a	(Ljava/lang/String;)V
    //   77: return
    //   78: new 312	java/util/ArrayList
    //   81: dup
    //   82: aload_3
    //   83: invokevirtual 316	java/util/HashMap:size	()I
    //   86: invokespecial 319	java/util/ArrayList:<init>	(I)V
    //   89: astore 9
    //   91: aload_3
    //   92: invokevirtual 322	java/util/HashMap:entrySet	()Ljava/util/Set;
    //   95: invokeinterface 104 1 0
    //   100: astore_3
    //   101: aload_3
    //   102: invokeinterface 109 1 0
    //   107: ifne +246 -> 353
    //   110: getstatic 242	com/baidu/lbsapi/auth/a:a	Z
    //   113: ifeq +9 -> 122
    //   116: ldc_w 324
    //   119: invokestatic 268	com/baidu/lbsapi/auth/a:a	(Ljava/lang/String;)V
    //   122: aload 8
    //   124: invokevirtual 328	org/apache/http/client/methods/HttpPost:getAllHeaders	()[Lorg/apache/http/Header;
    //   127: astore_3
    //   128: aload_3
    //   129: arraylength
    //   130: istore 7
    //   132: iconst_0
    //   133: istore 6
    //   135: iload 6
    //   137: iload 7
    //   139: if_icmplt +333 -> 472
    //   142: new 330	org/apache/http/client/entity/UrlEncodedFormEntity
    //   145: dup
    //   146: aload 9
    //   148: ldc_w 332
    //   151: invokespecial 335	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;Ljava/lang/String;)V
    //   154: astore_3
    //   155: aload 8
    //   157: aload_3
    //   158: invokevirtual 339	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   161: getstatic 242	com/baidu/lbsapi/auth/a:a	Z
    //   164: ifeq +9 -> 173
    //   167: ldc_w 341
    //   170: invokestatic 268	com/baidu/lbsapi/auth/a:a	(Ljava/lang/String;)V
    //   173: aload_1
    //   174: aload 8
    //   176: invokeinterface 347 2 0
    //   181: astore_1
    //   182: getstatic 242	com/baidu/lbsapi/auth/a:a	Z
    //   185: ifeq +49 -> 234
    //   188: new 244	java/lang/StringBuilder
    //   191: dup
    //   192: ldc_w 349
    //   195: invokespecial 247	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   198: invokestatic 303	java/lang/System:currentTimeMillis	()J
    //   201: l2d
    //   202: dload 4
    //   204: dsub
    //   205: ldc2_w 350
    //   208: ddiv
    //   209: invokevirtual 354	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   212: ldc_w 356
    //   215: invokevirtual 265	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   218: aload_2
    //   219: invokevirtual 265	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   222: ldc_w 358
    //   225: invokevirtual 265	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   228: invokevirtual 266	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   231: invokestatic 268	com/baidu/lbsapi/auth/a:a	(Ljava/lang/String;)V
    //   234: iconst_0
    //   235: istore 6
    //   237: aload_1
    //   238: ifnull +413 -> 651
    //   241: aload_1
    //   242: invokeinterface 364 1 0
    //   247: ifnull +404 -> 651
    //   250: aload_1
    //   251: invokeinterface 364 1 0
    //   256: invokeinterface 369 1 0
    //   261: istore 6
    //   263: iload 6
    //   265: sipush 200
    //   268: if_icmpeq +396 -> 664
    //   271: getstatic 242	com/baidu/lbsapi/auth/a:a	Z
    //   274: ifeq +33 -> 307
    //   277: new 244	java/lang/StringBuilder
    //   280: dup
    //   281: ldc_w 371
    //   284: invokespecial 247	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   287: aload_1
    //   288: invokeinterface 364 1 0
    //   293: invokeinterface 374 1 0
    //   298: invokevirtual 265	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   301: invokevirtual 266	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   304: invokestatic 268	com/baidu/lbsapi/auth/a:a	(Ljava/lang/String;)V
    //   307: aload_0
    //   308: new 244	java/lang/StringBuilder
    //   311: dup
    //   312: ldc_w 376
    //   315: invokespecial 247	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   318: iload 6
    //   320: invokevirtual 379	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   323: ldc_w 381
    //   326: invokevirtual 265	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   329: aload_1
    //   330: invokeinterface 364 1 0
    //   335: invokeinterface 374 1 0
    //   340: invokevirtual 265	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   343: invokevirtual 266	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   346: invokestatic 288	com/baidu/lbsapi/auth/ErrorMessage:a	(Ljava/lang/String;)Ljava/lang/String;
    //   349: invokespecial 289	com/baidu/lbsapi/auth/c:a	(Ljava/lang/String;)V
    //   352: return
    //   353: aload_3
    //   354: invokeinterface 113 1 0
    //   359: checkcast 383	java/util/Map$Entry
    //   362: astore 10
    //   364: aload 10
    //   366: invokeinterface 386 1 0
    //   371: checkcast 71	java/lang/String
    //   374: ldc_w 388
    //   377: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   380: ifne -279 -> 101
    //   383: getstatic 242	com/baidu/lbsapi/auth/a:a	Z
    //   386: ifeq +48 -> 434
    //   389: new 244	java/lang/StringBuilder
    //   392: dup
    //   393: invokespecial 389	java/lang/StringBuilder:<init>	()V
    //   396: aload 10
    //   398: invokeinterface 386 1 0
    //   403: checkcast 71	java/lang/String
    //   406: invokevirtual 265	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   409: ldc_w 391
    //   412: invokevirtual 265	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   415: aload 10
    //   417: invokeinterface 394 1 0
    //   422: checkcast 71	java/lang/String
    //   425: invokevirtual 265	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   428: invokevirtual 266	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   431: invokestatic 268	com/baidu/lbsapi/auth/a:a	(Ljava/lang/String;)V
    //   434: aload 9
    //   436: new 396	org/apache/http/message/BasicNameValuePair
    //   439: dup
    //   440: aload 10
    //   442: invokeinterface 386 1 0
    //   447: checkcast 71	java/lang/String
    //   450: aload 10
    //   452: invokeinterface 394 1 0
    //   457: checkcast 71	java/lang/String
    //   460: invokespecial 399	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   463: invokeinterface 404 2 0
    //   468: pop
    //   469: goto -368 -> 101
    //   472: aload_3
    //   473: iload 6
    //   475: aaload
    //   476: astore 10
    //   478: getstatic 242	com/baidu/lbsapi/auth/a:a	Z
    //   481: ifeq +45 -> 526
    //   484: new 244	java/lang/StringBuilder
    //   487: dup
    //   488: ldc_w 406
    //   491: invokespecial 247	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   494: aload 10
    //   496: invokeinterface 411 1 0
    //   501: invokevirtual 265	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   504: ldc_w 413
    //   507: invokevirtual 265	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   510: aload 10
    //   512: invokeinterface 415 1 0
    //   517: invokevirtual 265	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   520: invokevirtual 266	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   523: invokestatic 268	com/baidu/lbsapi/auth/a:a	(Ljava/lang/String;)V
    //   526: iload 6
    //   528: iconst_1
    //   529: iadd
    //   530: istore 6
    //   532: goto -397 -> 135
    //   535: astore_1
    //   536: aload_0
    //   537: new 244	java/lang/StringBuilder
    //   540: dup
    //   541: ldc_w 417
    //   544: invokespecial 247	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   547: aload_1
    //   548: invokevirtual 420	java/io/UnsupportedEncodingException:getMessage	()Ljava/lang/String;
    //   551: invokevirtual 265	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   554: invokevirtual 266	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   557: invokestatic 288	com/baidu/lbsapi/auth/ErrorMessage:a	(Ljava/lang/String;)Ljava/lang/String;
    //   560: invokespecial 289	com/baidu/lbsapi/auth/c:a	(Ljava/lang/String;)V
    //   563: return
    //   564: astore_1
    //   565: aload_0
    //   566: new 244	java/lang/StringBuilder
    //   569: dup
    //   570: ldc_w 422
    //   573: invokespecial 247	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   576: aload_1
    //   577: invokevirtual 423	org/apache/http/conn/ConnectTimeoutException:getMessage	()Ljava/lang/String;
    //   580: invokevirtual 265	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   583: invokevirtual 266	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   586: invokestatic 288	com/baidu/lbsapi/auth/ErrorMessage:a	(Ljava/lang/String;)Ljava/lang/String;
    //   589: invokespecial 289	com/baidu/lbsapi/auth/c:a	(Ljava/lang/String;)V
    //   592: return
    //   593: astore_1
    //   594: aload_0
    //   595: new 244	java/lang/StringBuilder
    //   598: dup
    //   599: ldc_w 425
    //   602: invokespecial 247	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   605: aload_1
    //   606: invokevirtual 426	java/net/SocketTimeoutException:getMessage	()Ljava/lang/String;
    //   609: invokevirtual 265	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   612: invokevirtual 266	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   615: invokestatic 288	com/baidu/lbsapi/auth/ErrorMessage:a	(Ljava/lang/String;)Ljava/lang/String;
    //   618: invokespecial 289	com/baidu/lbsapi/auth/c:a	(Ljava/lang/String;)V
    //   621: return
    //   622: astore_1
    //   623: aload_0
    //   624: new 244	java/lang/StringBuilder
    //   627: dup
    //   628: ldc_w 428
    //   631: invokespecial 247	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   634: aload_1
    //   635: invokevirtual 429	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   638: invokevirtual 265	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   641: invokevirtual 266	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   644: invokestatic 288	com/baidu/lbsapi/auth/ErrorMessage:a	(Ljava/lang/String;)Ljava/lang/String;
    //   647: invokespecial 289	com/baidu/lbsapi/auth/c:a	(Ljava/lang/String;)V
    //   650: return
    //   651: aload_0
    //   652: ldc_w 431
    //   655: invokestatic 288	com/baidu/lbsapi/auth/ErrorMessage:a	(Ljava/lang/String;)Ljava/lang/String;
    //   658: invokespecial 289	com/baidu/lbsapi/auth/c:a	(Ljava/lang/String;)V
    //   661: goto -398 -> 263
    //   664: aload_0
    //   665: aload_1
    //   666: invokeinterface 435 1 0
    //   671: ldc_w 332
    //   674: invokestatic 440	org/apache/http/util/EntityUtils:toString	(Lorg/apache/http/HttpEntity;Ljava/lang/String;)Ljava/lang/String;
    //   677: putfield 31	com/baidu/lbsapi/auth/c:c	Ljava/lang/String;
    //   680: getstatic 242	com/baidu/lbsapi/auth/a:a	Z
    //   683: ifeq +26 -> 709
    //   686: new 244	java/lang/StringBuilder
    //   689: dup
    //   690: ldc_w 442
    //   693: invokespecial 247	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   696: aload_0
    //   697: getfield 31	com/baidu/lbsapi/auth/c:c	Ljava/lang/String;
    //   700: invokevirtual 265	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   703: invokevirtual 266	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   706: invokestatic 268	com/baidu/lbsapi/auth/a:a	(Ljava/lang/String;)V
    //   709: aload_0
    //   710: aload_0
    //   711: getfield 31	com/baidu/lbsapi/auth/c:c	Ljava/lang/String;
    //   714: invokespecial 289	com/baidu/lbsapi/auth/c:a	(Ljava/lang/String;)V
    //   717: getstatic 242	com/baidu/lbsapi/auth/a:a	Z
    //   720: ifeq -643 -> 77
    //   723: ldc_w 444
    //   726: invokestatic 268	com/baidu/lbsapi/auth/a:a	(Ljava/lang/String;)V
    //   729: return
    //   730: astore_1
    //   731: aload_0
    //   732: new 244	java/lang/StringBuilder
    //   735: dup
    //   736: ldc_w 428
    //   739: invokespecial 247	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   742: aload_1
    //   743: invokevirtual 429	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   746: invokevirtual 265	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   749: invokevirtual 266	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   752: invokestatic 288	com/baidu/lbsapi/auth/ErrorMessage:a	(Ljava/lang/String;)Ljava/lang/String;
    //   755: invokespecial 289	com/baidu/lbsapi/auth/c:a	(Ljava/lang/String;)V
    //   758: return
    //
    // Exception table:
    //   from	to	target	type
    //   142	155	535	java/io/UnsupportedEncodingException
    //   161	173	564	org/apache/http/conn/ConnectTimeoutException
    //   173	182	564	org/apache/http/conn/ConnectTimeoutException
    //   161	173	593	java/net/SocketTimeoutException
    //   173	182	593	java/net/SocketTimeoutException
    //   161	173	622	java/lang/Exception
    //   173	182	622	java/lang/Exception
    //   664	680	730	java/lang/Exception
  }

  private boolean a(HttpClient paramHttpClient)
  {
    if (a.a)
      a.a("checkNetwork 1 start");
    Object localObject = ((ConnectivityManager)this.b.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localObject == null) || (!((NetworkInfo)localObject).isAvailable()))
      return false;
    localObject = a(this.b);
    if (a.a)
      a.a("checkNetwork = " + (String)localObject);
    if (((String)localObject).equals("cmwap"))
    {
      localObject = new HttpHost("10.0.0.172", 80, "http");
      paramHttpClient.getParams().setParameter("http.route.default-proxy", localObject);
    }
    while (true)
    {
      if (a.a)
        a.a("checkNetwork 4 end");
      return true;
      if (((String)localObject).equals("ctwap"))
      {
        localObject = new HttpHost("10.0.0.200", 80, "http");
        paramHttpClient.getParams().setParameter("http.route.default-proxy", localObject);
      }
    }
  }

  private void b(HttpClient paramHttpClient)
  {
    if (a.a)
      a.a("setTimeout start");
    paramHttpClient.getParams().setIntParameter("http.socket.timeout", 50000);
    paramHttpClient.getParams().setIntParameter("http.connection.timeout", 50000);
    if (a.a)
      a.a("setTimeout end");
  }

  protected void a(HashMap<String, String> paramHashMap, a<String> parama)
  {
    this.e = a(paramHashMap);
    this.f = parama;
    this.d = ((String)this.e.get("url"));
    new Thread(new d(this)).start();
  }

  static abstract interface a<Result>
  {
    public abstract void a(Result paramResult);
  }

  class b extends org.apache.http.conn.ssl.SSLSocketFactory
  {
    SSLContext a = SSLContext.getInstance("TLS");

    public b(KeyStore arg2)
      throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException
    {
      super();
      this$1 = new e(this);
      this.a.init(null, new TrustManager[] { c.this }, null);
    }

    public Socket createSocket()
      throws IOException
    {
      return this.a.getSocketFactory().createSocket();
    }

    public Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
      throws IOException, UnknownHostException
    {
      return this.a.getSocketFactory().createSocket(paramSocket, paramString, paramInt, paramBoolean);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.lbsapi.auth.c
 * JD-Core Version:    0.6.2
 */