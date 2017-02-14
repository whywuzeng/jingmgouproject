package u.aly;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.json.JSONException;
import org.json.JSONObject;

public class bw
{
  private static final String a = bw.class.getName();
  private Map<String, String> b;

  // ERROR //
  private static String a(InputStream paramInputStream)
  {
    // Byte code:
    //   0: new 28	java/io/BufferedReader
    //   3: dup
    //   4: new 30	java/io/InputStreamReader
    //   7: dup
    //   8: aload_0
    //   9: invokespecial 33	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   12: sipush 8192
    //   15: invokespecial 36	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   18: astore_1
    //   19: new 38	java/lang/StringBuilder
    //   22: dup
    //   23: invokespecial 39	java/lang/StringBuilder:<init>	()V
    //   26: astore_2
    //   27: aload_1
    //   28: invokevirtual 42	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   31: astore_3
    //   32: aload_3
    //   33: ifnonnull +12 -> 45
    //   36: aload_0
    //   37: invokevirtual 47	java/io/InputStream:close	()V
    //   40: aload_2
    //   41: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   44: areturn
    //   45: aload_2
    //   46: new 38	java/lang/StringBuilder
    //   49: dup
    //   50: aload_3
    //   51: invokestatic 56	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   54: invokespecial 59	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   57: ldc 61
    //   59: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   65: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   68: pop
    //   69: goto -42 -> 27
    //   72: astore_1
    //   73: getstatic 19	u/aly/bw:a	Ljava/lang/String;
    //   76: ldc 67
    //   78: aload_1
    //   79: invokestatic 72	u/aly/br:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V
    //   82: aload_0
    //   83: invokevirtual 47	java/io/InputStream:close	()V
    //   86: aconst_null
    //   87: areturn
    //   88: astore_0
    //   89: getstatic 19	u/aly/bw:a	Ljava/lang/String;
    //   92: ldc 67
    //   94: aload_0
    //   95: invokestatic 72	u/aly/br:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V
    //   98: aconst_null
    //   99: areturn
    //   100: astore_1
    //   101: aload_0
    //   102: invokevirtual 47	java/io/InputStream:close	()V
    //   105: aload_1
    //   106: athrow
    //   107: astore_0
    //   108: getstatic 19	u/aly/bw:a	Ljava/lang/String;
    //   111: ldc 67
    //   113: aload_0
    //   114: invokestatic 72	u/aly/br:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V
    //   117: aconst_null
    //   118: areturn
    //   119: astore_0
    //   120: getstatic 19	u/aly/bw:a	Ljava/lang/String;
    //   123: ldc 67
    //   125: aload_0
    //   126: invokestatic 72	u/aly/br:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V
    //   129: aconst_null
    //   130: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   27	32	72	java/io/IOException
    //   45	69	72	java/io/IOException
    //   82	86	88	java/io/IOException
    //   27	32	100	finally
    //   45	69	100	finally
    //   73	82	100	finally
    //   101	105	107	java/io/IOException
    //   36	40	119	java/io/IOException
  }

  private JSONObject a(String paramString)
  {
    int i = new Random().nextInt(1000);
    label522: label524: 
    while (true)
    {
      Object localObject2;
      try
      {
        String str1 = System.getProperty("line.separator");
        if (paramString.length() <= 1)
        {
          br.b(a, i + ":\tInvalid baseUrl.");
          return null;
        }
        br.a(a, i + ":\tget: " + paramString);
        Object localObject1 = new HttpGet(paramString);
        if ((this.b != null) && (this.b.size() > 0))
        {
          localObject2 = this.b.keySet().iterator();
          if (((Iterator)localObject2).hasNext());
        }
        else
        {
          localObject2 = new DefaultHttpClient(b()).execute((HttpUriRequest)localObject1);
          if (((HttpResponse)localObject2).getStatusLine().getStatusCode() != 200)
            break label472;
          localObject1 = ((HttpResponse)localObject2).getEntity();
          if (localObject1 == null)
            break label522;
          localObject1 = ((HttpEntity)localObject1).getContent();
          localObject2 = ((HttpResponse)localObject2).getFirstHeader("Content-Encoding");
          if ((localObject2 == null) || (!((Header)localObject2).getValue().equalsIgnoreCase("gzip")))
            continue;
          br.a(a, i + "  Use GZIPInputStream get data....");
          localObject1 = new GZIPInputStream((InputStream)localObject1);
          localObject1 = a((InputStream)localObject1);
          br.a(a, i + ":\tresponse: " + str1 + (String)localObject1);
          if (localObject1 != null)
            break label463;
          return null;
        }
        String str2 = (String)((Iterator)localObject2).next();
        ((HttpGet)localObject1).addHeader(str2, (String)this.b.get(str2));
        continue;
      }
      catch (ClientProtocolException localClientProtocolException)
      {
        br.c(a, i + ":\tClientProtocolException,Failed to send message." + paramString, localClientProtocolException);
        return null;
        if ((localObject2 == null) || (!((Header)localObject2).getValue().equalsIgnoreCase("deflate")))
          break label524;
        br.a(a, i + "  Use InflaterInputStream get data....");
        InflaterInputStream localInflaterInputStream = new InflaterInputStream(localClientProtocolException);
        continue;
      }
      catch (Exception localException)
      {
        br.c(a, i + ":\tIOException,Failed to send message." + paramString, localException);
        return null;
      }
      label463: return new JSONObject(localException);
      label472: br.c(a, i + ":\tFailed to send message. StatusCode = " + ((HttpResponse)localObject2).getStatusLine().getStatusCode() + cd.a + paramString);
      return null;
    }
  }

  private JSONObject a(String paramString, JSONObject paramJSONObject)
  {
    int i = 1;
    Object localObject2 = paramJSONObject.toString();
    int j = new Random().nextInt(1000);
    br.c(a, j + ":\trequest: " + paramString + cd.a + (String)localObject2);
    paramJSONObject = new HttpPost(paramString);
    Object localObject1 = new DefaultHttpClient(b());
    label519: 
    while (true)
      try
      {
        if (a())
        {
          localObject2 = cc.a((String)localObject2, Charset.defaultCharset().name());
          paramJSONObject.addHeader("Content-Encoding", "deflate");
          paramJSONObject.setEntity(new InputStreamEntity(new ByteArrayInputStream((byte[])localObject2), localObject2.length));
          localObject1 = ((HttpClient)localObject1).execute(paramJSONObject);
          paramJSONObject = ((HttpResponse)localObject1).getFirstHeader("Content-Type");
          if (((HttpResponse)localObject1).getStatusLine().getStatusCode() == 200)
          {
            boolean bool = cd.a(paramJSONObject, "application/json");
            if ((i == 0) || (!bool))
              continue;
            paramJSONObject = ((HttpResponse)localObject1).getEntity();
            if (paramJSONObject == null)
              break;
            paramJSONObject = paramJSONObject.getContent();
            localObject1 = ((HttpResponse)localObject1).getFirstHeader("Content-Encoding");
            if ((localObject1 == null) || (!((Header)localObject1).getValue().equalsIgnoreCase("deflate")))
              break label519;
            paramJSONObject = new InflaterInputStream(paramJSONObject);
            paramJSONObject = a(paramJSONObject);
            br.a(a, j + ":\tresponse: " + cd.a + paramJSONObject);
            if (paramJSONObject != null)
              continue;
            return null;
          }
        }
        else
        {
          ArrayList localArrayList = new ArrayList(1);
          localArrayList.add(new BasicNameValuePair("content", (String)localObject2));
          paramJSONObject.setEntity(new UrlEncodedFormEntity(localArrayList, "UTF-8"));
          continue;
        }
      }
      catch (ClientProtocolException paramJSONObject)
      {
        br.c(a, j + ":\tClientProtocolException,Failed to send message." + paramString, paramJSONObject);
        return null;
        i = 0;
        continue;
        return new JSONObject(paramJSONObject);
        br.c(a, j + ":\tFailed to send message. StatusCode = " + ((HttpResponse)localObject1).getStatusLine().getStatusCode() + cd.a + paramString);
        return null;
      }
      catch (IOException paramJSONObject)
      {
        br.c(a, j + ":\tIOException,Failed to send message." + paramString, paramJSONObject);
        return null;
      }
      catch (JSONException paramJSONObject)
      {
        br.c(a, j + ":\tIOException,Failed to send message." + paramString, paramJSONObject);
        return null;
      }
    return null;
  }

  private HttpParams b()
  {
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 10000);
    HttpConnectionParams.setSoTimeout(localBasicHttpParams, 20000);
    HttpProtocolParams.setUserAgent(localBasicHttpParams, System.getProperty("http.agent"));
    return localBasicHttpParams;
  }

  private void b(String paramString)
  {
    if ((cd.d(paramString)) || (!(bx.c.equals(paramString.trim()) ^ bx.b.equals(paramString.trim()))))
      throw new RuntimeException("验证请求方式失败[" + paramString + "]");
  }

  public bw a(Map<String, String> paramMap)
  {
    this.b = paramMap;
    return this;
  }

  public <T extends by> T a(bx parambx, Class<T> paramClass)
  {
    String str = parambx.c().trim();
    b(str);
    if (bx.c.equals(str))
      parambx = a(parambx.b());
    while (true)
      if (parambx == null)
      {
        return null;
        if (bx.b.equals(str))
          parambx = a(parambx.d, parambx.a());
      }
      else
      {
        try
        {
          parambx = (by)paramClass.getConstructor(new Class[] { JSONObject.class }).newInstance(new Object[] { parambx });
          return parambx;
        }
        catch (SecurityException parambx)
        {
          br.b(a, "SecurityException", parambx);
          return null;
        }
        catch (NoSuchMethodException parambx)
        {
          while (true)
            br.b(a, "NoSuchMethodException", parambx);
        }
        catch (IllegalArgumentException parambx)
        {
          while (true)
            br.b(a, "IllegalArgumentException", parambx);
        }
        catch (InstantiationException parambx)
        {
          while (true)
            br.b(a, "InstantiationException", parambx);
        }
        catch (IllegalAccessException parambx)
        {
          while (true)
            br.b(a, "IllegalAccessException", parambx);
        }
        catch (InvocationTargetException parambx)
        {
          while (true)
            br.b(a, "InvocationTargetException", parambx);
        }
        parambx = null;
      }
  }

  public boolean a()
  {
    return false;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.aly.bw
 * JD-Core Version:    0.6.2
 */