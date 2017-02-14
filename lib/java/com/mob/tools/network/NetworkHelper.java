package com.mob.tools.network;

import android.content.Context;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.Ln;
import com.mob.tools.utils.R;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

public class NetworkHelper
{
  public static int connectionTimeout;
  public static int readTimout;

  private HttpPost getFilePost(String paramString, ArrayList<KVPair<String>> paramArrayList1, ArrayList<KVPair<String>> paramArrayList2)
    throws Throwable
  {
    String str = UUID.randomUUID().toString();
    HttpPost localHttpPost = new HttpPost(paramString);
    localHttpPost.setHeader("Content-Type", "multipart/form-data; boundary=" + str);
    MultiPart localMultiPart = new MultiPart();
    paramString = new StringPart();
    KVPair localKVPair;
    if (paramArrayList1 != null)
    {
      paramArrayList1 = paramArrayList1.iterator();
      while (paramArrayList1.hasNext())
      {
        localKVPair = (KVPair)paramArrayList1.next();
        paramString.append("--").append(str).append("\r\n");
        paramString.append("Content-Disposition: form-data; name=\"").append(localKVPair.name).append("\"\r\n\r\n");
        paramString.append((String)localKVPair.value).append("\r\n");
      }
    }
    localMultiPart.append(paramString);
    paramArrayList2 = paramArrayList2.iterator();
    if (paramArrayList2.hasNext())
    {
      localKVPair = (KVPair)paramArrayList2.next();
      StringPart localStringPart = new StringPart();
      paramString = new File((String)localKVPair.value);
      localStringPart.append("--").append(str).append("\r\n");
      localStringPart.append("Content-Disposition: form-data; name=\"").append(localKVPair.name).append("\"; filename=\"").append(paramString.getName()).append("\"\r\n");
      paramArrayList1 = URLConnection.getFileNameMap().getContentTypeFor((String)localKVPair.value);
      if (paramArrayList1 != null)
      {
        paramString = paramArrayList1;
        if (paramArrayList1.length() > 0);
      }
      else
      {
        if ((!((String)localKVPair.value).toLowerCase().endsWith("jpg")) && (!((String)localKVPair.value).toLowerCase().endsWith("jpeg")))
          break label405;
        paramString = "image/jpeg";
      }
      while (true)
      {
        localStringPart.append("Content-Type: ").append(paramString).append("\r\n\r\n");
        localMultiPart.append(localStringPart);
        paramString = new FilePart();
        paramString.setFile((String)localKVPair.value);
        localMultiPart.append(paramString);
        paramString = new StringPart();
        paramString.append("\r\n");
        localMultiPart.append(paramString);
        break;
        label405: if (((String)localKVPair.value).toLowerCase().endsWith("png"))
        {
          paramString = "image/png";
        }
        else if (((String)localKVPair.value).toLowerCase().endsWith("gif"))
        {
          paramString = "image/gif";
        }
        else
        {
          paramString = new FileInputStream((String)localKVPair.value);
          paramArrayList1 = URLConnection.guessContentTypeFromStream(paramString);
          paramString.close();
          if (paramArrayList1 != null)
          {
            paramString = paramArrayList1;
            if (paramArrayList1.length() > 0);
          }
          else
          {
            paramString = "application/octet-stream";
          }
        }
      }
    }
    paramString = new StringPart();
    paramString.append("--").append(str).append("--\r\n");
    localMultiPart.append(paramString);
    localHttpPost.setEntity(localMultiPart.getInputStreamEntity());
    return localHttpPost;
  }

  private HttpClient getSSLHttpClient()
    throws Throwable
  {
    Object localObject = KeyStore.getInstance(KeyStore.getDefaultType());
    ((KeyStore)localObject).load(null, null);
    localObject = new SSLSocketFactoryEx((KeyStore)localObject);
    ((SSLSocketFactory)localObject).setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    HttpProtocolParams.setVersion(localBasicHttpParams, HttpVersion.HTTP_1_1);
    HttpProtocolParams.setContentCharset(localBasicHttpParams, "UTF-8");
    SchemeRegistry localSchemeRegistry = new SchemeRegistry();
    localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
    localSchemeRegistry.register(new Scheme("https", (SocketFactory)localObject, 443));
    return new DefaultHttpClient(new ThreadSafeClientConnManager(localBasicHttpParams, localSchemeRegistry), localBasicHttpParams);
  }

  private HttpPost getTextPost(String paramString, ArrayList<KVPair<String>> paramArrayList)
    throws Throwable
  {
    paramString = new HttpPost(paramString);
    if (paramArrayList != null)
    {
      StringPart localStringPart = new StringPart();
      localStringPart.append(kvPairsToUrl(paramArrayList));
      paramArrayList = localStringPart.getInputStreamEntity();
      paramArrayList.setContentType("application/x-www-form-urlencoded");
      paramString.setEntity(paramArrayList);
    }
    return paramString;
  }

  private String kvPairsToUrl(ArrayList<KVPair<String>> paramArrayList)
    throws Throwable
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramArrayList.iterator();
    if (localIterator.hasNext())
    {
      paramArrayList = (KVPair)localIterator.next();
      String str = Data.urlEncode(paramArrayList.name, "utf-8");
      if (paramArrayList.value != null);
      for (paramArrayList = Data.urlEncode((String)paramArrayList.value, "utf-8"); ; paramArrayList = "")
      {
        if (localStringBuilder.length() > 0)
          localStringBuilder.append('&');
        localStringBuilder.append(str).append('=').append(paramArrayList);
        break;
      }
    }
    return localStringBuilder.toString();
  }

  public String downloadCache(Context paramContext, String paramString1, String paramString2, boolean paramBoolean)
    throws Throwable
  {
    long l = System.currentTimeMillis();
    Ln.i("downloading: " + paramString1, new Object[0]);
    if (paramBoolean)
    {
      localObject1 = new File(R.getCachePath(paramContext, paramString2), Data.MD5(paramString1));
      if ((paramBoolean) && (((File)localObject1).exists()))
      {
        Ln.i("use time: " + (System.currentTimeMillis() - l), new Object[0]);
        return ((File)localObject1).getAbsolutePath();
      }
    }
    Object localObject1 = new HttpGet(paramString1);
    if (paramString1.startsWith("https://"));
    HttpResponse localHttpResponse;
    int i;
    Object localObject2;
    for (Object localObject3 = getSSLHttpClient(); ; localObject3 = new DefaultHttpClient())
    {
      localHttpResponse = ((HttpClient)localObject3).execute((HttpUriRequest)localObject1);
      i = localHttpResponse.getStatusLine().getStatusCode();
      if (i != 200)
        break label803;
      String[] arrayOfString = null;
      localObject1 = null;
      Header[] arrayOfHeader = localHttpResponse.getHeaders("Content-Disposition");
      localObject2 = arrayOfString;
      if (arrayOfHeader == null)
        break;
      localObject2 = arrayOfString;
      if (arrayOfHeader.length <= 0)
        break;
      arrayOfString = arrayOfHeader[0].getValue().split(";");
      int j = arrayOfString.length;
      i = 0;
      while (true)
      {
        localObject2 = localObject1;
        if (i >= j)
          break;
        localObject2 = arrayOfString[i];
        if (((String)localObject2).trim().startsWith("filename"))
        {
          localObject2 = localObject2.split("=")[1];
          localObject1 = localObject2;
          if (((String)localObject2).startsWith("\""))
          {
            localObject1 = localObject2;
            if (((String)localObject2).endsWith("\""))
              localObject1 = ((String)localObject2).substring(1, ((String)localObject2).length() - 1);
          }
        }
        i += 1;
      }
    }
    localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject2 = Data.MD5(paramString1);
      localObject1 = localHttpResponse.getHeaders("Content-Type");
      if ((localObject1 == null) || (localObject1.length <= 0))
        break label878;
      localObject1 = localObject1[0].getValue().trim();
      if (!((String)localObject1).startsWith("image/"))
        break label538;
      localObject1 = ((String)localObject1).substring("image/".length());
      localObject2 = new StringBuilder().append((String)localObject2).append(".");
      paramString1 = (String)localObject1;
      if ("jpeg".equals(localObject1))
        paramString1 = "jpg";
      localObject1 = paramString1;
    }
    while (true)
    {
      paramContext = new File(R.getCachePath(paramContext, paramString2), (String)localObject1);
      if ((paramBoolean) && (paramContext.exists()))
      {
        ((HttpClient)localObject3).getConnectionManager().shutdown();
        Ln.i("use time: " + (System.currentTimeMillis() - l), new Object[0]);
        return paramContext.getAbsolutePath();
        label538: i = paramString1.lastIndexOf('/');
        localObject1 = null;
        if (i > 0)
          localObject1 = paramString1.substring(i + 1);
        if ((localObject1 != null) && (((String)localObject1).length() > 0))
        {
          i = ((String)localObject1).lastIndexOf('.');
          if ((i > 0) && (((String)localObject1).length() - i < 10))
            localObject1 = (String)localObject2 + ((String)localObject1).substring(i);
        }
      }
      else
      {
        if (!paramContext.getParentFile().exists())
          paramContext.getParentFile().mkdirs();
        if (paramContext.exists())
          paramContext.delete();
        try
        {
          paramString1 = localHttpResponse.getEntity().getContent();
          paramString2 = new FileOutputStream(paramContext);
          localObject1 = new byte[1024];
          for (i = paramString1.read((byte[])localObject1); i > 0; i = paramString1.read((byte[])localObject1))
            paramString2.write((byte[])localObject1, 0, i);
          paramString2.flush();
          paramString1.close();
          paramString2.close();
          ((HttpClient)localObject3).getConnectionManager().shutdown();
          Ln.i("use time: " + (System.currentTimeMillis() - l), new Object[0]);
          return paramContext.getAbsolutePath();
        }
        catch (Throwable paramString1)
        {
          if (paramContext.exists())
            paramContext.delete();
          throw paramString1;
        }
        label803: paramContext = EntityUtils.toString(localHttpResponse.getEntity(), "utf-8");
        paramString1 = new HashMap();
        paramString1.put("error", paramContext);
        paramString1.put("status", Integer.valueOf(i));
        ((HttpClient)localObject3).getConnectionManager().shutdown();
        throw new Throwable(new Hashon().fromHashMap(paramString1));
        label878: localObject1 = localObject2;
      }
    }
  }

  public void getHttpPostResponse(String paramString, ArrayList<KVPair<String>> paramArrayList1, KVPair<String> paramKVPair, ArrayList<KVPair<String>> paramArrayList2, ArrayList<KVPair<?>> paramArrayList, HttpResponseCallback paramHttpResponseCallback)
    throws Throwable
  {
    long l = System.currentTimeMillis();
    Ln.i("httpPost: " + paramString, new Object[0]);
    ArrayList localArrayList;
    if ((paramKVPair != null) && (paramKVPair.value != null) && (new File((String)paramKVPair.value).exists()))
    {
      localArrayList = new ArrayList();
      localArrayList.add(paramKVPair);
    }
    for (paramArrayList1 = getFilePost(paramString, paramArrayList1, localArrayList); paramArrayList2 != null; paramArrayList1 = getTextPost(paramString, paramArrayList1))
    {
      paramKVPair = paramArrayList2.iterator();
      while (paramKVPair.hasNext())
      {
        paramArrayList2 = (KVPair)paramKVPair.next();
        paramArrayList1.setHeader(paramArrayList2.name, (String)paramArrayList2.value);
      }
    }
    paramKVPair = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout(paramKVPair, connectionTimeout);
    HttpConnectionParams.setSoTimeout(paramKVPair, readTimout);
    if (paramArrayList != null)
      paramArrayList2 = paramArrayList.iterator();
    while (true)
    {
      if (paramArrayList2.hasNext())
        paramArrayList = (KVPair)paramArrayList2.next();
      try
      {
        int i = R.parseInt(String.valueOf(paramArrayList.value));
        paramKVPair.setIntParameter(paramArrayList.name, i);
        continue;
        paramArrayList1.setParams(paramKVPair);
        if (paramString.startsWith("https://"));
        for (paramString = getSSLHttpClient(); ; paramString = new DefaultHttpClient())
        {
          paramArrayList1 = paramString.execute(paramArrayList1);
          if (paramHttpResponseCallback != null)
            paramHttpResponseCallback.onResponse(paramArrayList1);
          paramString.getConnectionManager().shutdown();
          Ln.i("use time: " + (System.currentTimeMillis() - l), new Object[0]);
          return;
        }
      }
      catch (Exception paramArrayList)
      {
      }
    }
  }

  public String httpGet(String paramString, ArrayList<KVPair<String>> paramArrayList1, ArrayList<KVPair<String>> paramArrayList2, ArrayList<KVPair<?>> paramArrayList)
    throws Throwable
  {
    long l = System.currentTimeMillis();
    Ln.i("httpGet: " + paramString, new Object[0]);
    String str = paramString;
    if (paramArrayList1 != null)
    {
      paramArrayList1 = kvPairsToUrl(paramArrayList1);
      str = paramString;
      if (paramArrayList1.length() > 0)
        str = paramString + "?" + paramArrayList1;
    }
    paramArrayList1 = new HttpGet(str);
    if (paramArrayList2 != null)
    {
      paramString = paramArrayList2.iterator();
      while (paramString.hasNext())
      {
        paramArrayList2 = (KVPair)paramString.next();
        paramArrayList1.setHeader(paramArrayList2.name, (String)paramArrayList2.value);
      }
    }
    paramString = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout(paramString, connectionTimeout);
    HttpConnectionParams.setSoTimeout(paramString, readTimout);
    if (paramArrayList != null)
      paramArrayList2 = paramArrayList.iterator();
    while (true)
    {
      if (paramArrayList2.hasNext())
        paramArrayList = (KVPair)paramArrayList2.next();
      try
      {
        int i = R.parseInt(String.valueOf(paramArrayList.value));
        paramString.setIntParameter(paramArrayList.name, i);
        continue;
        paramArrayList1.setParams(paramString);
        if (str.startsWith("https://"));
        for (paramString = getSSLHttpClient(); ; paramString = new DefaultHttpClient())
        {
          paramArrayList1 = paramString.execute(paramArrayList1);
          i = paramArrayList1.getStatusLine().getStatusCode();
          if (i != 200)
            break;
          paramArrayList1 = EntityUtils.toString(paramArrayList1.getEntity(), "utf-8");
          paramString.getConnectionManager().shutdown();
          Ln.i("use time: " + (System.currentTimeMillis() - l), new Object[0]);
          return paramArrayList1;
        }
        paramArrayList1 = EntityUtils.toString(paramArrayList1.getEntity(), "utf-8");
        paramArrayList2 = new HashMap();
        paramArrayList2.put("error", paramArrayList1);
        paramArrayList2.put("status", Integer.valueOf(i));
        paramString.getConnectionManager().shutdown();
        throw new Throwable(new Hashon().fromHashMap(paramArrayList2));
      }
      catch (Exception paramArrayList)
      {
      }
    }
  }

  public ArrayList<KVPair<String>> httpHead(String paramString, ArrayList<KVPair<String>> paramArrayList1, KVPair<String> paramKVPair, ArrayList<KVPair<String>> paramArrayList2, ArrayList<KVPair<?>> paramArrayList)
    throws Throwable
  {
    long l = System.currentTimeMillis();
    Ln.i("httpHead: " + paramString, new Object[0]);
    paramKVPair = paramString;
    if (paramArrayList1 != null)
    {
      paramArrayList1 = kvPairsToUrl(paramArrayList1);
      paramKVPair = paramString;
      if (paramArrayList1.length() > 0)
        paramKVPair = paramString + "?" + paramArrayList1;
    }
    paramArrayList1 = new HttpHead(paramKVPair);
    if (paramArrayList2 != null)
    {
      paramString = paramArrayList2.iterator();
      while (paramString.hasNext())
      {
        paramArrayList2 = (KVPair)paramString.next();
        paramArrayList1.setHeader(paramArrayList2.name, (String)paramArrayList2.value);
      }
    }
    paramString = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout(paramString, connectionTimeout);
    HttpConnectionParams.setSoTimeout(paramString, readTimout);
    if (paramArrayList != null)
      paramArrayList2 = paramArrayList.iterator();
    while (true)
    {
      if (paramArrayList2.hasNext())
        paramArrayList = (KVPair)paramArrayList2.next();
      try
      {
        int i = R.parseInt(String.valueOf(paramArrayList.value));
        paramString.setIntParameter(paramArrayList.name, i);
        continue;
        paramArrayList1.setParams(paramString);
        if (paramKVPair.startsWith("https://"));
        for (paramString = getSSLHttpClient(); ; paramString = new DefaultHttpClient())
        {
          paramArrayList1 = paramString.execute(paramArrayList1).getAllHeaders();
          paramKVPair = new ArrayList();
          if (paramArrayList1 == null)
            break;
          int j = paramArrayList1.length;
          i = 0;
          while (i < j)
          {
            paramArrayList2 = paramArrayList1[i];
            paramKVPair.add(new KVPair(paramArrayList2.getName(), paramArrayList2.getValue()));
            i += 1;
          }
        }
        paramString.getConnectionManager().shutdown();
        Ln.i("use time: " + (System.currentTimeMillis() - l), new Object[0]);
        return paramKVPair;
      }
      catch (Exception paramArrayList)
      {
      }
    }
  }

  public String httpPatch(String paramString, ArrayList<KVPair<String>> paramArrayList1, KVPair<String> paramKVPair, long paramLong, ArrayList<KVPair<String>> paramArrayList2, ArrayList<KVPair<?>> paramArrayList, OnReadListener paramOnReadListener)
    throws Throwable
  {
    final HashMap localHashMap = new HashMap();
    httpPatch(paramString, paramArrayList1, paramKVPair, paramLong, paramArrayList2, paramArrayList, paramOnReadListener, new HttpResponseCallback()
    {
      public void onResponse(HttpResponse paramAnonymousHttpResponse)
        throws Throwable
      {
        int i = paramAnonymousHttpResponse.getStatusLine().getStatusCode();
        localHashMap.put("status", Integer.valueOf(i));
        if ((i == 200) || (i == 201))
        {
          localHashMap.put("resp", EntityUtils.toString(paramAnonymousHttpResponse.getEntity(), "utf-8"));
          return;
        }
        paramAnonymousHttpResponse = EntityUtils.toString(paramAnonymousHttpResponse.getEntity(), "utf-8");
        HashMap localHashMap = new HashMap();
        localHashMap.put("error", paramAnonymousHttpResponse);
        localHashMap.put("status", Integer.valueOf(i));
        localHashMap.put("resp", new Hashon().fromHashMap(localHashMap));
      }
    });
    int i = ((Integer)localHashMap.get("status")).intValue();
    paramString = (String)localHashMap.get("resp");
    if (i == 200)
      return paramString;
    throw new Throwable(paramString);
  }

  public void httpPatch(String paramString, ArrayList<KVPair<String>> paramArrayList1, KVPair<String> paramKVPair, long paramLong, ArrayList<KVPair<String>> paramArrayList2, ArrayList<KVPair<?>> paramArrayList, OnReadListener paramOnReadListener, HttpResponseCallback paramHttpResponseCallback)
    throws Throwable
  {
    long l = System.currentTimeMillis();
    Ln.i("httpPatch: " + paramString, new Object[0]);
    String str = paramString;
    if (paramArrayList1 != null)
    {
      paramArrayList1 = kvPairsToUrl(paramArrayList1);
      str = paramString;
      if (paramArrayList1.length() > 0)
        str = paramString + "?" + paramArrayList1;
    }
    paramArrayList1 = new HttpPatch(str);
    if (paramArrayList2 != null)
    {
      paramString = paramArrayList2.iterator();
      while (paramString.hasNext())
      {
        paramArrayList2 = (KVPair)paramString.next();
        paramArrayList1.setHeader(paramArrayList2.name, (String)paramArrayList2.value);
      }
    }
    paramString = new FilePart();
    paramString.setOnReadListener(paramOnReadListener);
    paramString.setFile((String)paramKVPair.value);
    paramString.setOffset(paramLong);
    paramString = paramString.getInputStreamEntity();
    paramString.setContentEncoding("application/offset+octet-stream");
    paramArrayList1.setEntity(paramString);
    paramString = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout(paramString, connectionTimeout);
    HttpConnectionParams.setSoTimeout(paramString, readTimout);
    if (paramArrayList != null)
      paramKVPair = paramArrayList.iterator();
    while (true)
    {
      if (paramKVPair.hasNext())
        paramArrayList2 = (KVPair)paramKVPair.next();
      try
      {
        int i = R.parseInt(String.valueOf(paramArrayList2.value));
        paramString.setIntParameter(paramArrayList2.name, i);
        continue;
        paramArrayList1.setParams(paramString);
        if (str.startsWith("https://"));
        for (paramString = getSSLHttpClient(); ; paramString = new DefaultHttpClient())
        {
          paramArrayList1 = paramString.execute(paramArrayList1);
          if (paramHttpResponseCallback != null)
            paramHttpResponseCallback.onResponse(paramArrayList1);
          paramString.getConnectionManager().shutdown();
          Ln.i("use time: " + (System.currentTimeMillis() - l), new Object[0]);
          return;
        }
      }
      catch (Exception paramArrayList2)
      {
      }
    }
  }

  public String httpPost(String paramString, ArrayList<KVPair<String>> paramArrayList1, KVPair<String> paramKVPair, ArrayList<KVPair<String>> paramArrayList2, ArrayList<KVPair<?>> paramArrayList)
    throws Throwable
  {
    ArrayList localArrayList = new ArrayList();
    if ((paramKVPair != null) && (paramKVPair.value != null) && (new File((String)paramKVPair.value).exists()))
      localArrayList.add(paramKVPair);
    return httpPostFiles(paramString, paramArrayList1, localArrayList, paramArrayList2, paramArrayList);
  }

  public void httpPost(String paramString, ArrayList<KVPair<String>> paramArrayList1, ArrayList<KVPair<String>> paramArrayList2, ArrayList<KVPair<String>> paramArrayList3, ArrayList<KVPair<?>> paramArrayList, HttpResponseCallback paramHttpResponseCallback)
    throws Throwable
  {
    long l = System.currentTimeMillis();
    Ln.i("httpPost: " + paramString, new Object[0]);
    if ((paramArrayList2 != null) && (paramArrayList2.size() > 0));
    for (paramArrayList1 = getFilePost(paramString, paramArrayList1, paramArrayList2); paramArrayList3 != null; paramArrayList1 = getTextPost(paramString, paramArrayList1))
    {
      paramArrayList2 = paramArrayList3.iterator();
      while (paramArrayList2.hasNext())
      {
        paramArrayList3 = (KVPair)paramArrayList2.next();
        paramArrayList1.setHeader(paramArrayList3.name, (String)paramArrayList3.value);
      }
    }
    paramArrayList2 = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout(paramArrayList2, connectionTimeout);
    HttpConnectionParams.setSoTimeout(paramArrayList2, readTimout);
    Ln.i("before set SO_TIMEOUT :" + paramArrayList2.getIntParameter("http.socket.timeout", readTimout), new Object[0]);
    Ln.i("before set CONNECTION_TIMEOUT :" + paramArrayList2.getIntParameter("http.connection.timeout", connectionTimeout), new Object[0]);
    if (paramArrayList != null)
      paramArrayList3 = paramArrayList.iterator();
    while (true)
    {
      if (paramArrayList3.hasNext())
        paramArrayList = (KVPair)paramArrayList3.next();
      try
      {
        int i = R.parseInt(String.valueOf(paramArrayList.value));
        paramArrayList2.setIntParameter(paramArrayList.name, i);
        continue;
        Ln.i("before set SO_TIMEOUT :" + paramArrayList2.getIntParameter("http.socket.timeout", readTimout), new Object[0]);
        Ln.i("before set CONNECTION_TIMEOUT :" + paramArrayList2.getIntParameter("http.connection.timeout", connectionTimeout), new Object[0]);
        paramArrayList1.setParams(paramArrayList2);
        if (paramString.startsWith("https://"))
          paramString = getSSLHttpClient();
        while (true)
        {
          paramArrayList1 = paramString.execute(paramArrayList1);
          if (paramHttpResponseCallback != null);
          try
          {
            paramHttpResponseCallback.onResponse(paramArrayList1);
            paramString.getConnectionManager().shutdown();
            Ln.i("use time: " + (System.currentTimeMillis() - l), new Object[0]);
            return;
            paramString = new DefaultHttpClient();
          }
          catch (Throwable paramArrayList1)
          {
            paramString.getConnectionManager().shutdown();
            throw paramArrayList1;
          }
        }
      }
      catch (Exception paramArrayList)
      {
      }
    }
  }

  public String httpPostFiles(String paramString, ArrayList<KVPair<String>> paramArrayList1, ArrayList<KVPair<String>> paramArrayList2, ArrayList<KVPair<String>> paramArrayList3, ArrayList<KVPair<?>> paramArrayList)
    throws Throwable
  {
    final HashMap localHashMap = new HashMap();
    httpPost(paramString, paramArrayList1, paramArrayList2, paramArrayList3, paramArrayList, new HttpResponseCallback()
    {
      public void onResponse(HttpResponse paramAnonymousHttpResponse)
        throws Throwable
      {
        int i = paramAnonymousHttpResponse.getStatusLine().getStatusCode();
        if ((i == 200) || (i == 201))
        {
          localHashMap.put("resp", EntityUtils.toString(paramAnonymousHttpResponse.getEntity(), "utf-8"));
          return;
        }
        paramAnonymousHttpResponse = EntityUtils.toString(paramAnonymousHttpResponse.getEntity(), "utf-8");
        HashMap localHashMap = new HashMap();
        localHashMap.put("error", paramAnonymousHttpResponse);
        localHashMap.put("status", Integer.valueOf(i));
        throw new Throwable(new Hashon().fromHashMap(localHashMap));
      }
    });
    return (String)localHashMap.get("resp");
  }

  public String httpPut(String paramString, ArrayList<KVPair<String>> paramArrayList1, KVPair<String> paramKVPair, ArrayList<KVPair<String>> paramArrayList2, ArrayList<KVPair<?>> paramArrayList)
    throws Throwable
  {
    long l = System.currentTimeMillis();
    Ln.i("httpPut: " + paramString, new Object[0]);
    String str = paramString;
    if (paramArrayList1 != null)
    {
      paramArrayList1 = kvPairsToUrl(paramArrayList1);
      str = paramString;
      if (paramArrayList1.length() > 0)
        str = paramString + "?" + paramArrayList1;
    }
    paramArrayList1 = new HttpPut(str);
    if (paramArrayList2 != null)
    {
      paramString = paramArrayList2.iterator();
      while (paramString.hasNext())
      {
        paramArrayList2 = (KVPair)paramString.next();
        paramArrayList1.setHeader(paramArrayList2.name, (String)paramArrayList2.value);
      }
    }
    paramString = new FilePart();
    paramString.setFile((String)paramKVPair.value);
    paramString = paramString.getInputStreamEntity();
    paramString.setContentEncoding("application/octet-stream");
    paramArrayList1.setEntity(paramString);
    paramString = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout(paramString, connectionTimeout);
    HttpConnectionParams.setSoTimeout(paramString, readTimout);
    if (paramArrayList != null)
      paramKVPair = paramArrayList.iterator();
    while (true)
    {
      if (paramKVPair.hasNext())
        paramArrayList2 = (KVPair)paramKVPair.next();
      try
      {
        int i = R.parseInt(String.valueOf(paramArrayList2.value));
        paramString.setIntParameter(paramArrayList2.name, i);
        continue;
        paramArrayList1.setParams(paramString);
        if (str.startsWith("https://"));
        for (paramString = getSSLHttpClient(); ; paramString = new DefaultHttpClient())
        {
          paramArrayList1 = paramString.execute(paramArrayList1);
          i = paramArrayList1.getStatusLine().getStatusCode();
          if ((i != 200) && (i != 201))
            break;
          paramArrayList1 = EntityUtils.toString(paramArrayList1.getEntity(), "utf-8");
          paramString.getConnectionManager().shutdown();
          Ln.i("use time: " + (System.currentTimeMillis() - l), new Object[0]);
          return paramArrayList1;
        }
        paramArrayList1 = EntityUtils.toString(paramArrayList1.getEntity(), "utf-8");
        paramKVPair = new HashMap();
        paramKVPair.put("error", paramArrayList1);
        paramKVPair.put("status", Integer.valueOf(i));
        paramString.getConnectionManager().shutdown();
        throw new Throwable(new Hashon().fromHashMap(paramKVPair));
      }
      catch (Exception paramArrayList2)
      {
      }
    }
  }

  public String jsonPost(String paramString, ArrayList<KVPair<String>> paramArrayList1, ArrayList<KVPair<String>> paramArrayList2, ArrayList<KVPair<?>> paramArrayList)
    throws Throwable
  {
    long l = System.currentTimeMillis();
    Ln.i("jsonPost: " + paramString, new Object[0]);
    HttpPost localHttpPost = new HttpPost(paramString);
    StringPart localStringPart = new StringPart();
    if (paramArrayList1 != null)
    {
      HashMap localHashMap = new HashMap();
      paramArrayList1 = paramArrayList1.iterator();
      while (paramArrayList1.hasNext())
      {
        KVPair localKVPair = (KVPair)paramArrayList1.next();
        localHashMap.put(localKVPair.name, localKVPair.value);
      }
      localStringPart.append(new Hashon().fromHashMap(localHashMap));
    }
    paramArrayList1 = localStringPart.getInputStreamEntity();
    paramArrayList1.setContentType("application/json");
    localHttpPost.setEntity(paramArrayList1);
    if (paramArrayList2 != null)
    {
      paramArrayList1 = paramArrayList2.iterator();
      while (paramArrayList1.hasNext())
      {
        paramArrayList2 = (KVPair)paramArrayList1.next();
        localHttpPost.setHeader(paramArrayList2.name, (String)paramArrayList2.value);
      }
    }
    paramArrayList1 = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout(paramArrayList1, connectionTimeout);
    HttpConnectionParams.setSoTimeout(paramArrayList1, readTimout);
    if (paramArrayList != null)
      paramArrayList2 = paramArrayList.iterator();
    while (true)
    {
      if (paramArrayList2.hasNext())
        paramArrayList = (KVPair)paramArrayList2.next();
      try
      {
        int i = R.parseInt(String.valueOf(paramArrayList.value));
        paramArrayList1.setIntParameter(paramArrayList.name, i);
        continue;
        localHttpPost.setParams(paramArrayList1);
        if (paramString.startsWith("https://"));
        for (paramString = getSSLHttpClient(); ; paramString = new DefaultHttpClient())
        {
          paramArrayList1 = paramString.execute(localHttpPost);
          i = paramArrayList1.getStatusLine().getStatusCode();
          if ((i != 200) && (i != 201))
            break;
          paramArrayList1 = EntityUtils.toString(paramArrayList1.getEntity(), "utf-8");
          paramString.getConnectionManager().shutdown();
          Ln.i("use time: " + (System.currentTimeMillis() - l), new Object[0]);
          return paramArrayList1;
        }
        paramArrayList1 = EntityUtils.toString(paramArrayList1.getEntity(), "utf-8");
        paramArrayList2 = new HashMap();
        paramArrayList2.put("error", paramArrayList1);
        paramArrayList2.put("status", Integer.valueOf(i));
        paramString.getConnectionManager().shutdown();
        throw new Throwable(new Hashon().fromHashMap(paramArrayList2));
      }
      catch (Exception paramArrayList)
      {
      }
    }
  }

  public void rawGet(String paramString, RawNetworkCallback paramRawNetworkCallback)
    throws Throwable
  {
    Object localObject = new HttpGet(paramString);
    if (paramString.startsWith("https://"));
    int i;
    for (paramString = getSSLHttpClient(); ; paramString = new DefaultHttpClient())
    {
      localObject = paramString.execute((HttpUriRequest)localObject);
      i = ((HttpResponse)localObject).getStatusLine().getStatusCode();
      if (i != 200)
        break;
      if (paramRawNetworkCallback != null)
        paramRawNetworkCallback.onResponse(((HttpResponse)localObject).getEntity().getContent());
      paramString.getConnectionManager().shutdown();
      return;
    }
    paramRawNetworkCallback = EntityUtils.toString(((HttpResponse)localObject).getEntity(), "utf-8");
    localObject = new HashMap();
    ((HashMap)localObject).put("error", paramRawNetworkCallback);
    ((HashMap)localObject).put("status", Integer.valueOf(i));
    paramString.getConnectionManager().shutdown();
    throw new Throwable(new Hashon().fromHashMap((HashMap)localObject));
  }

  public void rawPost(String paramString, ArrayList<KVPair<String>> paramArrayList, HTTPPart paramHTTPPart, HttpResponseCallback paramHttpResponseCallback)
    throws Throwable
  {
    long l = System.currentTimeMillis();
    Ln.i("raw post: " + paramString, new Object[0]);
    HttpPost localHttpPost = new HttpPost(paramString);
    if (paramArrayList != null)
    {
      paramArrayList = paramArrayList.iterator();
      while (paramArrayList.hasNext())
      {
        KVPair localKVPair = (KVPair)paramArrayList.next();
        localHttpPost.setHeader(localKVPair.name, (String)localKVPair.value);
      }
    }
    localHttpPost.setEntity(paramHTTPPart.getInputStreamEntity());
    if (paramString.startsWith("https://"));
    for (paramString = getSSLHttpClient(); ; paramString = new DefaultHttpClient())
    {
      paramArrayList = paramString.execute(localHttpPost);
      if (paramHttpResponseCallback != null)
        paramHttpResponseCallback.onResponse(paramArrayList);
      paramString.getConnectionManager().shutdown();
      Ln.i("use time: " + (System.currentTimeMillis() - l), new Object[0]);
      return;
    }
  }

  public void rawPost(String paramString, ArrayList<KVPair<String>> paramArrayList, HTTPPart paramHTTPPart, RawNetworkCallback paramRawNetworkCallback)
    throws Throwable
  {
    long l = System.currentTimeMillis();
    Ln.i("raw post: " + paramString, new Object[0]);
    HttpPost localHttpPost = new HttpPost(paramString);
    if (paramArrayList != null)
    {
      paramArrayList = paramArrayList.iterator();
      while (paramArrayList.hasNext())
      {
        KVPair localKVPair = (KVPair)paramArrayList.next();
        localHttpPost.setHeader(localKVPair.name, (String)localKVPair.value);
      }
    }
    localHttpPost.setEntity(paramHTTPPart.getInputStreamEntity());
    if (paramString.startsWith("https://"));
    int i;
    for (paramString = getSSLHttpClient(); ; paramString = new DefaultHttpClient())
    {
      paramArrayList = paramString.execute(localHttpPost);
      i = paramArrayList.getStatusLine().getStatusCode();
      if (i != 200)
        break;
      Ln.i("use time: " + (System.currentTimeMillis() - l), new Object[0]);
      if (paramRawNetworkCallback != null)
        paramRawNetworkCallback.onResponse(paramArrayList.getEntity().getContent());
      paramString.getConnectionManager().shutdown();
      return;
    }
    paramArrayList = EntityUtils.toString(paramArrayList.getEntity(), "utf-8");
    paramHTTPPart = new HashMap();
    paramHTTPPart.put("error", paramArrayList);
    paramHTTPPart.put("status", Integer.valueOf(i));
    paramString.getConnectionManager().shutdown();
    throw new Throwable(new Hashon().fromHashMap(paramHTTPPart));
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.network.NetworkHelper
 * JD-Core Version:    0.6.2
 */