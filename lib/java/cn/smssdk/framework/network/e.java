package cn.smssdk.framework.network;

import android.content.Context;
import cn.smssdk.framework.utils.Data;
import cn.smssdk.framework.utils.R;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

public class e
{
  private HttpClient a()
  {
    Object localObject = KeyStore.getInstance(KeyStore.getDefaultType());
    ((KeyStore)localObject).load(null, null);
    localObject = new f((KeyStore)localObject);
    ((SSLSocketFactory)localObject).setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    HttpProtocolParams.setVersion(localBasicHttpParams, HttpVersion.HTTP_1_1);
    HttpProtocolParams.setContentCharset(localBasicHttpParams, "UTF-8");
    SchemeRegistry localSchemeRegistry = new SchemeRegistry();
    localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
    localSchemeRegistry.register(new Scheme("https", (SocketFactory)localObject, 443));
    return new DefaultHttpClient(new ThreadSafeClientConnManager(localBasicHttpParams, localSchemeRegistry), localBasicHttpParams);
  }

  public String a(Context paramContext, String paramString1, String paramString2, boolean paramBoolean)
  {
    long l = System.currentTimeMillis();
    cn.smssdk.framework.utils.d.b("downloading: " + paramString1, new Object[0]);
    if (paramBoolean)
    {
      localObject1 = new File(R.getCachePath(paramContext, paramString2), Data.MD5(paramString1));
      if ((paramBoolean) && (((File)localObject1).exists()))
      {
        cn.smssdk.framework.utils.d.b("use time: " + (System.currentTimeMillis() - l), new Object[0]);
        return ((File)localObject1).getAbsolutePath();
      }
    }
    Object localObject1 = new HttpGet(paramString1);
    if (paramString1.startsWith("https://"));
    HttpResponse localHttpResponse;
    int i;
    Object localObject2;
    for (Object localObject3 = a(); ; localObject3 = new DefaultHttpClient())
    {
      localHttpResponse = ((HttpClient)localObject3).execute((HttpUriRequest)localObject1);
      i = localHttpResponse.getStatusLine().getStatusCode();
      if (i != 200)
        break label789;
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
        break label864;
      localObject1 = localObject1[0].getValue().trim();
      if (!((String)localObject1).startsWith("image/"))
        break label525;
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
        cn.smssdk.framework.utils.d.b("use time: " + (System.currentTimeMillis() - l), new Object[0]);
        return paramContext.getAbsolutePath();
        label525: i = paramString1.lastIndexOf('/');
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
          cn.smssdk.framework.utils.d.b("use time: " + (System.currentTimeMillis() - l), new Object[0]);
          return paramContext.getAbsolutePath();
        }
        catch (Throwable paramString1)
        {
          if (paramContext.exists())
            paramContext.delete();
          throw paramString1;
        }
        label789: paramContext = EntityUtils.toString(localHttpResponse.getEntity(), "utf-8");
        paramString1 = new HashMap();
        paramString1.put("error", paramContext);
        paramString1.put("status", Integer.valueOf(i));
        ((HttpClient)localObject3).getConnectionManager().shutdown();
        throw new Throwable(new cn.smssdk.framework.utils.c().a(paramString1));
        label864: localObject1 = localObject2;
      }
    }
  }

  public void a(String paramString, ArrayList<d<String>> paramArrayList, c paramc, RawNetworkCallback paramRawNetworkCallback)
  {
    long l = System.currentTimeMillis();
    cn.smssdk.framework.utils.d.b("raw post: " + paramString, new Object[0]);
    HttpPost localHttpPost = new HttpPost(paramString);
    if (paramArrayList != null)
    {
      paramArrayList = paramArrayList.iterator();
      while (paramArrayList.hasNext())
      {
        d locald = (d)paramArrayList.next();
        localHttpPost.setHeader(locald.a, (String)locald.b);
      }
    }
    localHttpPost.setEntity(paramc.c());
    if (paramString.startsWith("https://"));
    int i;
    for (paramString = a(); ; paramString = new DefaultHttpClient())
    {
      paramArrayList = paramString.execute(localHttpPost);
      i = paramArrayList.getStatusLine().getStatusCode();
      if (i != 200)
        break;
      cn.smssdk.framework.utils.d.b("use time: " + (System.currentTimeMillis() - l), new Object[0]);
      if (paramRawNetworkCallback != null)
        paramRawNetworkCallback.onResponse(paramArrayList.getEntity().getContent());
      paramString.getConnectionManager().shutdown();
      return;
    }
    paramArrayList = EntityUtils.toString(paramArrayList.getEntity(), "utf-8");
    paramc = new HashMap();
    paramc.put("error", paramArrayList);
    paramc.put("status", Integer.valueOf(i));
    paramString.getConnectionManager().shutdown();
    throw new Throwable(new cn.smssdk.framework.utils.c().a(paramc));
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.framework.network.e
 * JD-Core Version:    0.6.2
 */