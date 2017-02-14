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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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

class f
{
  private final String a = "HttpAsyncTask";
  private Context b;
  private String c = null;
  private String d = null;
  private List<HashMap<String, String>> e = null;
  private a<String> f = null;

  protected f(Context paramContext)
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

  // ERROR //
  private String a(HttpClient paramHttpClient, String paramString, HashMap<String, String> paramHashMap)
  {
    // Byte code:
    //   0: getstatic 105	com/baidu/lbsapi/auth/a:a	Z
    //   3: ifeq +8 -> 11
    //   6: ldc 107
    //   8: invokestatic 110	com/baidu/lbsapi/auth/a:a	(Ljava/lang/String;)V
    //   11: dconst_0
    //   12: dstore 4
    //   14: getstatic 105	com/baidu/lbsapi/auth/a:a	Z
    //   17: ifeq +9 -> 26
    //   20: invokestatic 116	java/lang/System:currentTimeMillis	()J
    //   23: l2d
    //   24: dstore 4
    //   26: new 118	org/apache/http/client/methods/HttpPost
    //   29: dup
    //   30: aload_2
    //   31: invokespecial 120	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   34: astore 8
    //   36: getstatic 105	com/baidu/lbsapi/auth/a:a	Z
    //   39: ifeq +22 -> 61
    //   42: new 122	java/lang/StringBuilder
    //   45: dup
    //   46: ldc 124
    //   48: invokespecial 125	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   51: aload_2
    //   52: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   55: invokevirtual 132	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   58: invokestatic 110	com/baidu/lbsapi/auth/a:a	(Ljava/lang/String;)V
    //   61: aload_3
    //   62: ifnonnull +9 -> 71
    //   65: ldc 134
    //   67: invokestatic 139	com/baidu/lbsapi/auth/ErrorMessage:a	(Ljava/lang/String;)Ljava/lang/String;
    //   70: areturn
    //   71: new 141	java/util/ArrayList
    //   74: dup
    //   75: aload_3
    //   76: invokevirtual 147	java/util/HashMap:size	()I
    //   79: invokespecial 150	java/util/ArrayList:<init>	(I)V
    //   82: astore 9
    //   84: aload_3
    //   85: invokevirtual 154	java/util/HashMap:entrySet	()Ljava/util/Set;
    //   88: invokeinterface 160 1 0
    //   93: astore_3
    //   94: aload_3
    //   95: invokeinterface 165 1 0
    //   100: ifne +230 -> 330
    //   103: getstatic 105	com/baidu/lbsapi/auth/a:a	Z
    //   106: ifeq +8 -> 114
    //   109: ldc 167
    //   111: invokestatic 110	com/baidu/lbsapi/auth/a:a	(Ljava/lang/String;)V
    //   114: aload 8
    //   116: invokevirtual 171	org/apache/http/client/methods/HttpPost:getAllHeaders	()[Lorg/apache/http/Header;
    //   119: astore_3
    //   120: aload_3
    //   121: arraylength
    //   122: istore 7
    //   124: iconst_0
    //   125: istore 6
    //   127: iload 6
    //   129: iload 7
    //   131: if_icmplt +316 -> 447
    //   134: new 173	org/apache/http/client/entity/UrlEncodedFormEntity
    //   137: dup
    //   138: aload 9
    //   140: ldc 175
    //   142: invokespecial 178	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;Ljava/lang/String;)V
    //   145: astore_3
    //   146: aload 8
    //   148: aload_3
    //   149: invokevirtual 182	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   152: getstatic 105	com/baidu/lbsapi/auth/a:a	Z
    //   155: ifeq +8 -> 163
    //   158: ldc 184
    //   160: invokestatic 110	com/baidu/lbsapi/auth/a:a	(Ljava/lang/String;)V
    //   163: aload_1
    //   164: aload 8
    //   166: invokeinterface 190 2 0
    //   171: astore_1
    //   172: getstatic 105	com/baidu/lbsapi/auth/a:a	Z
    //   175: ifeq +46 -> 221
    //   178: new 122	java/lang/StringBuilder
    //   181: dup
    //   182: ldc 192
    //   184: invokespecial 125	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   187: invokestatic 116	java/lang/System:currentTimeMillis	()J
    //   190: l2d
    //   191: dload 4
    //   193: dsub
    //   194: ldc2_w 193
    //   197: ddiv
    //   198: invokevirtual 197	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   201: ldc 199
    //   203: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   206: aload_2
    //   207: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   210: ldc 201
    //   212: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   215: invokevirtual 132	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   218: invokestatic 110	com/baidu/lbsapi/auth/a:a	(Ljava/lang/String;)V
    //   221: aload_1
    //   222: ifnull +387 -> 609
    //   225: aload_1
    //   226: invokeinterface 207 1 0
    //   231: ifnull +378 -> 609
    //   234: aload_1
    //   235: invokeinterface 207 1 0
    //   240: invokeinterface 212 1 0
    //   245: istore 6
    //   247: iload 6
    //   249: sipush 200
    //   252: if_icmpeq +364 -> 616
    //   255: getstatic 105	com/baidu/lbsapi/auth/a:a	Z
    //   258: ifeq +32 -> 290
    //   261: new 122	java/lang/StringBuilder
    //   264: dup
    //   265: ldc 214
    //   267: invokespecial 125	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   270: aload_1
    //   271: invokeinterface 207 1 0
    //   276: invokeinterface 217 1 0
    //   281: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   284: invokevirtual 132	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   287: invokestatic 110	com/baidu/lbsapi/auth/a:a	(Ljava/lang/String;)V
    //   290: new 122	java/lang/StringBuilder
    //   293: dup
    //   294: ldc 219
    //   296: invokespecial 125	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   299: iload 6
    //   301: invokevirtual 222	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   304: ldc 224
    //   306: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   309: aload_1
    //   310: invokeinterface 207 1 0
    //   315: invokeinterface 217 1 0
    //   320: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   323: invokevirtual 132	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   326: invokestatic 139	com/baidu/lbsapi/auth/ErrorMessage:a	(Ljava/lang/String;)Ljava/lang/String;
    //   329: areturn
    //   330: aload_3
    //   331: invokeinterface 228 1 0
    //   336: checkcast 230	java/util/Map$Entry
    //   339: astore 10
    //   341: aload 10
    //   343: invokeinterface 233 1 0
    //   348: checkcast 71	java/lang/String
    //   351: ldc 235
    //   353: invokevirtual 83	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   356: ifne -262 -> 94
    //   359: getstatic 105	com/baidu/lbsapi/auth/a:a	Z
    //   362: ifeq +47 -> 409
    //   365: new 122	java/lang/StringBuilder
    //   368: dup
    //   369: invokespecial 236	java/lang/StringBuilder:<init>	()V
    //   372: aload 10
    //   374: invokeinterface 233 1 0
    //   379: checkcast 71	java/lang/String
    //   382: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   385: ldc 238
    //   387: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   390: aload 10
    //   392: invokeinterface 241 1 0
    //   397: checkcast 71	java/lang/String
    //   400: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   403: invokevirtual 132	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   406: invokestatic 110	com/baidu/lbsapi/auth/a:a	(Ljava/lang/String;)V
    //   409: aload 9
    //   411: new 243	org/apache/http/message/BasicNameValuePair
    //   414: dup
    //   415: aload 10
    //   417: invokeinterface 233 1 0
    //   422: checkcast 71	java/lang/String
    //   425: aload 10
    //   427: invokeinterface 241 1 0
    //   432: checkcast 71	java/lang/String
    //   435: invokespecial 246	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   438: invokeinterface 251 2 0
    //   443: pop
    //   444: goto -350 -> 94
    //   447: aload_3
    //   448: iload 6
    //   450: aaload
    //   451: astore 10
    //   453: getstatic 105	com/baidu/lbsapi/auth/a:a	Z
    //   456: ifeq +44 -> 500
    //   459: new 122	java/lang/StringBuilder
    //   462: dup
    //   463: ldc 253
    //   465: invokespecial 125	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   468: aload 10
    //   470: invokeinterface 258 1 0
    //   475: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   478: ldc_w 260
    //   481: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   484: aload 10
    //   486: invokeinterface 262 1 0
    //   491: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   494: invokevirtual 132	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   497: invokestatic 110	com/baidu/lbsapi/auth/a:a	(Ljava/lang/String;)V
    //   500: iload 6
    //   502: iconst_1
    //   503: iadd
    //   504: istore 6
    //   506: goto -379 -> 127
    //   509: astore_1
    //   510: new 122	java/lang/StringBuilder
    //   513: dup
    //   514: ldc_w 264
    //   517: invokespecial 125	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   520: aload_1
    //   521: invokevirtual 267	java/io/UnsupportedEncodingException:getMessage	()Ljava/lang/String;
    //   524: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   527: invokevirtual 132	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   530: invokestatic 139	com/baidu/lbsapi/auth/ErrorMessage:a	(Ljava/lang/String;)Ljava/lang/String;
    //   533: areturn
    //   534: astore_1
    //   535: new 122	java/lang/StringBuilder
    //   538: dup
    //   539: ldc_w 269
    //   542: invokespecial 125	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   545: aload_1
    //   546: invokevirtual 270	org/apache/http/conn/ConnectTimeoutException:getMessage	()Ljava/lang/String;
    //   549: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   552: invokevirtual 132	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   555: invokestatic 139	com/baidu/lbsapi/auth/ErrorMessage:a	(Ljava/lang/String;)Ljava/lang/String;
    //   558: areturn
    //   559: astore_1
    //   560: new 122	java/lang/StringBuilder
    //   563: dup
    //   564: ldc_w 272
    //   567: invokespecial 125	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   570: aload_1
    //   571: invokevirtual 273	java/net/SocketTimeoutException:getMessage	()Ljava/lang/String;
    //   574: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   577: invokevirtual 132	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   580: invokestatic 139	com/baidu/lbsapi/auth/ErrorMessage:a	(Ljava/lang/String;)Ljava/lang/String;
    //   583: areturn
    //   584: astore_1
    //   585: new 122	java/lang/StringBuilder
    //   588: dup
    //   589: ldc_w 275
    //   592: invokespecial 125	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   595: aload_1
    //   596: invokevirtual 276	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   599: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   602: invokevirtual 132	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   605: invokestatic 139	com/baidu/lbsapi/auth/ErrorMessage:a	(Ljava/lang/String;)Ljava/lang/String;
    //   608: areturn
    //   609: ldc_w 278
    //   612: invokestatic 139	com/baidu/lbsapi/auth/ErrorMessage:a	(Ljava/lang/String;)Ljava/lang/String;
    //   615: areturn
    //   616: aload_0
    //   617: aload_1
    //   618: invokeinterface 282 1 0
    //   623: ldc 175
    //   625: invokestatic 287	org/apache/http/util/EntityUtils:toString	(Lorg/apache/http/HttpEntity;Ljava/lang/String;)Ljava/lang/String;
    //   628: putfield 31	com/baidu/lbsapi/auth/f:c	Ljava/lang/String;
    //   631: getstatic 105	com/baidu/lbsapi/auth/a:a	Z
    //   634: ifeq +26 -> 660
    //   637: new 122	java/lang/StringBuilder
    //   640: dup
    //   641: ldc_w 289
    //   644: invokespecial 125	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   647: aload_0
    //   648: getfield 31	com/baidu/lbsapi/auth/f:c	Ljava/lang/String;
    //   651: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   654: invokevirtual 132	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   657: invokestatic 110	com/baidu/lbsapi/auth/a:a	(Ljava/lang/String;)V
    //   660: new 291	org/json/JSONObject
    //   663: dup
    //   664: aload_0
    //   665: getfield 31	com/baidu/lbsapi/auth/f:c	Ljava/lang/String;
    //   668: invokespecial 292	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   671: astore_1
    //   672: getstatic 105	com/baidu/lbsapi/auth/a:a	Z
    //   675: ifeq +9 -> 684
    //   678: ldc_w 294
    //   681: invokestatic 110	com/baidu/lbsapi/auth/a:a	(Ljava/lang/String;)V
    //   684: aload_1
    //   685: invokevirtual 295	org/json/JSONObject:toString	()Ljava/lang/String;
    //   688: astore_1
    //   689: aload_1
    //   690: areturn
    //   691: astore_1
    //   692: new 122	java/lang/StringBuilder
    //   695: dup
    //   696: ldc_w 275
    //   699: invokespecial 125	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   702: aload_1
    //   703: invokevirtual 276	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   706: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   709: invokevirtual 132	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   712: invokestatic 139	com/baidu/lbsapi/auth/ErrorMessage:a	(Ljava/lang/String;)Ljava/lang/String;
    //   715: areturn
    //   716: astore_1
    //   717: new 122	java/lang/StringBuilder
    //   720: dup
    //   721: ldc_w 297
    //   724: invokespecial 125	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   727: aload_1
    //   728: invokevirtual 298	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   731: invokevirtual 129	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   734: invokevirtual 132	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   737: invokestatic 139	com/baidu/lbsapi/auth/ErrorMessage:a	(Ljava/lang/String;)Ljava/lang/String;
    //   740: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   134	146	509	java/io/UnsupportedEncodingException
    //   152	163	534	org/apache/http/conn/ConnectTimeoutException
    //   163	172	534	org/apache/http/conn/ConnectTimeoutException
    //   152	163	559	java/net/SocketTimeoutException
    //   163	172	559	java/net/SocketTimeoutException
    //   152	163	584	java/lang/Exception
    //   163	172	584	java/lang/Exception
    //   616	631	691	java/lang/Exception
    //   660	684	716	org/json/JSONException
    //   684	689	716	org/json/JSONException
  }

  private List<HashMap<String, String>> a(HashMap<String, String> paramHashMap, String[] paramArrayOfString)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject2;
    if ((paramArrayOfString != null) && (paramArrayOfString.length > 0))
    {
      int i = 0;
      if (i >= paramArrayOfString.length)
        return localArrayList;
      localObject1 = new HashMap();
      localObject2 = paramHashMap.keySet().iterator();
      while (true)
      {
        if (!((Iterator)localObject2).hasNext())
        {
          ((HashMap)localObject1).put("mcode", paramArrayOfString[i]);
          localArrayList.add(localObject1);
          i += 1;
          break;
        }
        String str = ((Iterator)localObject2).next().toString();
        ((HashMap)localObject1).put(str, (String)paramHashMap.get(str));
      }
    }
    paramArrayOfString = new HashMap();
    Object localObject1 = paramHashMap.keySet().iterator();
    while (true)
    {
      if (!((Iterator)localObject1).hasNext())
      {
        localArrayList.add(paramArrayOfString);
        return localArrayList;
      }
      localObject2 = ((Iterator)localObject1).next().toString();
      paramArrayOfString.put(localObject2, (String)paramHashMap.get(localObject2));
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
              break label86;
          }
          label86: for (paramString = paramString.toString(); ; paramString = new JSONObject().toString())
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

  private void a(List<HashMap<String, String>> paramList)
  {
    if (a.a)
      a.a("syncConnect start Thread id = " + String.valueOf(Thread.currentThread().getId()));
    ArrayList localArrayList;
    int i;
    if ((paramList != null) && (paramList.size() > 0))
    {
      localArrayList = new ArrayList();
      i = 0;
    }
    while (true)
    {
      if (i >= paramList.size())
      {
        if (a.a)
          a.a("---------------------------iiiiii:" + i + "<><>paramList.size():" + paramList.size() + "<><>authResults.size():" + localArrayList.size());
        if ((paramList.size() <= 0) || (i != paramList.size()) || (localArrayList.size() <= 0) || (i != localArrayList.size()) || (i - 1 <= 0));
      }
      try
      {
        paramList = new JSONObject((String)localArrayList.get(i - 1));
        if ((paramList.has("status")) && (paramList.getInt("status") != 0))
        {
          if (a.a)
            a.a("i-1 result is not 0,return first result");
          a((String)localArrayList.get(0));
        }
        return;
        if (a.a)
          a.a("auth sync resuest " + i + "  start!!!");
        Object localObject1 = (HashMap)paramList.get(i);
        Object localObject2 = a();
        b((HttpClient)localObject2);
        String str = (String)((HashMap)localObject1).get("url");
        if (a((HttpClient)localObject2))
        {
          localObject1 = a((HttpClient)localObject2, str, (HashMap)localObject1);
          if (a.a)
            a.a("auth sync resuest " + i + "  result:" + (String)localObject1);
          localArrayList.add(localObject1);
          try
          {
            localObject2 = new JSONObject((String)localObject1);
            if ((((JSONObject)localObject2).has("status")) && (((JSONObject)localObject2).getInt("status") == 0))
            {
              if (a.a)
                a.a("auth end and break");
              a((String)localObject1);
              return;
            }
          }
          catch (JSONException localJSONException)
          {
            if (a.a)
              a.a("continue-------------------------------");
          }
        }
        while (true)
        {
          if (a.a)
            a.a("syncConnect end");
          i += 1;
          break;
          if (a.a)
            a.a("Current network is not available.");
          localArrayList.add(ErrorMessage.a("Current network is not available."));
        }
      }
      catch (JSONException paramList)
      {
        a(ErrorMessage.a("JSONException:" + paramList.getMessage()));
      }
    }
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

  protected void a(HashMap<String, String> paramHashMap, String[] paramArrayOfString, a<String> parama)
  {
    this.e = a(paramHashMap, paramArrayOfString);
    this.f = parama;
    new Thread(new g(this)).start();
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
      this$1 = new h(this);
      this.a.init(null, new TrustManager[] { f.this }, null);
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
 * Qualified Name:     com.baidu.lbsapi.auth.f
 * JD-Core Version:    0.6.2
 */