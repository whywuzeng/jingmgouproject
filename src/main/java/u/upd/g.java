package u.upd;

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

public class g
{
  private static final String TAG = g.class.getName();
  private Map<String, String> headers;

  private JSONObject HttpRequestGet(String paramString)
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
          b.b(TAG, i + ":\tInvalid baseUrl.");
          return null;
        }
        b.a(TAG, i + ":\tget: " + paramString);
        Object localObject1 = new HttpGet(paramString);
        if ((this.headers != null) && (this.headers.size() > 0))
        {
          localObject2 = this.headers.keySet().iterator();
          if (((Iterator)localObject2).hasNext());
        }
        else
        {
          localObject2 = new DefaultHttpClient(getHttpParams()).execute((HttpUriRequest)localObject1);
          if (((HttpResponse)localObject2).getStatusLine().getStatusCode() != 200)
            break label472;
          localObject1 = ((HttpResponse)localObject2).getEntity();
          if (localObject1 == null)
            break label522;
          localObject1 = ((HttpEntity)localObject1).getContent();
          localObject2 = ((HttpResponse)localObject2).getFirstHeader("Content-Encoding");
          if ((localObject2 == null) || (!((Header)localObject2).getValue().equalsIgnoreCase("gzip")))
            continue;
          b.a(TAG, i + "  Use GZIPInputStream get data....");
          localObject1 = new GZIPInputStream((InputStream)localObject1);
          localObject1 = convertStreamToString((InputStream)localObject1);
          b.a(TAG, i + ":\tresponse: " + str1 + (String)localObject1);
          if (localObject1 != null)
            break label463;
          return null;
        }
        String str2 = (String)((Iterator)localObject2).next();
        ((HttpGet)localObject1).addHeader(str2, (String)this.headers.get(str2));
        continue;
      }
      catch (ClientProtocolException localClientProtocolException)
      {
        b.c(TAG, i + ":\tClientProtocolException,Failed to send message." + paramString, localClientProtocolException);
        return null;
        if ((localObject2 == null) || (!((Header)localObject2).getValue().equalsIgnoreCase("deflate")))
          break label524;
        b.a(TAG, i + "  Use InflaterInputStream get data....");
        InflaterInputStream localInflaterInputStream = new InflaterInputStream(localClientProtocolException);
        continue;
      }
      catch (Exception localException)
      {
        b.c(TAG, i + ":\tIOException,Failed to send message." + paramString, localException);
        return null;
      }
      label463: return new JSONObject(localException);
      label472: b.c(TAG, i + ":\tFailed to send message. StatusCode = " + ((HttpResponse)localObject2).getStatusLine().getStatusCode() + n.a + paramString);
      return null;
    }
  }

  private JSONObject HttpRequestPost(String paramString, JSONObject paramJSONObject)
  {
    Object localObject2 = paramJSONObject.toString();
    int i = new Random().nextInt(1000);
    b.c(TAG, i + ":\trequest: " + paramString + n.a + (String)localObject2);
    paramJSONObject = new HttpPost(paramString);
    Object localObject1 = new DefaultHttpClient(getHttpParams());
    label491: 
    while (true)
      try
      {
        if (shouldCompressData())
        {
          localObject2 = m.a("content=" + (String)localObject2, Charset.defaultCharset().toString());
          paramJSONObject.addHeader("Content-Encoding", "deflate");
          paramJSONObject.setEntity(new InputStreamEntity(new ByteArrayInputStream((byte[])localObject2), localObject2.length));
          localObject1 = ((HttpClient)localObject1).execute(paramJSONObject);
          if (((HttpResponse)localObject1).getStatusLine().getStatusCode() != 200)
            continue;
          paramJSONObject = ((HttpResponse)localObject1).getEntity();
          if (paramJSONObject == null)
            break;
          paramJSONObject = paramJSONObject.getContent();
          localObject1 = ((HttpResponse)localObject1).getFirstHeader("Content-Encoding");
          if ((localObject1 == null) || (!((Header)localObject1).getValue().equalsIgnoreCase("deflate")))
            break label491;
          paramJSONObject = new InflaterInputStream(paramJSONObject);
          paramJSONObject = convertStreamToString(paramJSONObject);
          b.a(TAG, i + ":\tresponse: " + n.a + paramJSONObject);
          if (paramJSONObject == null)
            return null;
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
        b.c(TAG, i + ":\tClientProtocolException,Failed to send message." + paramString, paramJSONObject);
        return null;
        return new JSONObject(paramJSONObject);
        b.c(TAG, i + ":\tFailed to send message. StatusCode = " + ((HttpResponse)localObject1).getStatusLine().getStatusCode() + n.a + paramString);
        return null;
      }
      catch (IOException paramJSONObject)
      {
        b.c(TAG, i + ":\tIOException,Failed to send message." + paramString, paramJSONObject);
        return null;
      }
      catch (JSONException paramJSONObject)
      {
        b.c(TAG, i + ":\tIOException,Failed to send message." + paramString, paramJSONObject);
        return null;
      }
    return null;
  }

  // ERROR //
  private static String convertStreamToString(InputStream paramInputStream)
  {
    // Byte code:
    //   0: new 281	java/io/BufferedReader
    //   3: dup
    //   4: new 283	java/io/InputStreamReader
    //   7: dup
    //   8: aload_0
    //   9: invokespecial 284	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   12: sipush 8192
    //   15: invokespecial 287	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   18: astore_1
    //   19: new 52	java/lang/StringBuilder
    //   22: dup
    //   23: invokespecial 288	java/lang/StringBuilder:<init>	()V
    //   26: astore_2
    //   27: aload_1
    //   28: invokevirtual 291	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   31: astore_3
    //   32: aload_3
    //   33: ifnonnull +12 -> 45
    //   36: aload_0
    //   37: invokevirtual 296	java/io/InputStream:close	()V
    //   40: aload_2
    //   41: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   44: areturn
    //   45: aload_2
    //   46: new 52	java/lang/StringBuilder
    //   49: dup
    //   50: aload_3
    //   51: invokestatic 299	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   54: invokespecial 59	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   57: ldc_w 301
    //   60: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   66: invokevirtual 65	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: pop
    //   70: goto -43 -> 27
    //   73: astore_1
    //   74: getstatic 19	u/upd/g:TAG	Ljava/lang/String;
    //   77: ldc_w 303
    //   80: aload_1
    //   81: invokestatic 305	u/upd/b:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V
    //   84: aload_0
    //   85: invokevirtual 296	java/io/InputStream:close	()V
    //   88: aconst_null
    //   89: areturn
    //   90: astore_0
    //   91: getstatic 19	u/upd/g:TAG	Ljava/lang/String;
    //   94: ldc_w 303
    //   97: aload_0
    //   98: invokestatic 305	u/upd/b:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V
    //   101: aconst_null
    //   102: areturn
    //   103: astore_1
    //   104: aload_0
    //   105: invokevirtual 296	java/io/InputStream:close	()V
    //   108: aload_1
    //   109: athrow
    //   110: astore_0
    //   111: getstatic 19	u/upd/g:TAG	Ljava/lang/String;
    //   114: ldc_w 303
    //   117: aload_0
    //   118: invokestatic 305	u/upd/b:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V
    //   121: aconst_null
    //   122: areturn
    //   123: astore_0
    //   124: getstatic 19	u/upd/g:TAG	Ljava/lang/String;
    //   127: ldc_w 303
    //   130: aload_0
    //   131: invokestatic 305	u/upd/b:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V
    //   134: aconst_null
    //   135: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   27	32	73	java/io/IOException
    //   45	70	73	java/io/IOException
    //   84	88	90	java/io/IOException
    //   27	32	103	finally
    //   45	70	103	finally
    //   74	84	103	finally
    //   104	108	110	java/io/IOException
    //   36	40	123	java/io/IOException
  }

  private HttpParams getHttpParams()
  {
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 10000);
    HttpConnectionParams.setSoTimeout(localBasicHttpParams, 20000);
    HttpProtocolParams.setUserAgent(localBasicHttpParams, System.getProperty("http.agent"));
    return localBasicHttpParams;
  }

  private void verifyMethod(String paramString)
  {
    if ((n.d(paramString)) || (!(h.GET.equals(paramString.trim()) ^ h.POST.equals(paramString.trim()))))
      throw new RuntimeException("验证请求方式失败[" + paramString + "]");
  }

  public <T extends i> T execute(h paramh, Class<T> paramClass)
  {
    String str = paramh.getHttpMethod().trim();
    verifyMethod(str);
    if (h.GET.equals(str))
      paramh = HttpRequestGet(paramh.toGetUrl());
    while (true)
      if (paramh == null)
      {
        return null;
        if (h.POST.equals(str))
          paramh = HttpRequestPost(paramh.baseUrl, paramh.toJson());
      }
      else
      {
        try
        {
          paramh = (i)paramClass.getConstructor(new Class[] { JSONObject.class }).newInstance(new Object[] { paramh });
          return paramh;
        }
        catch (SecurityException paramh)
        {
          b.b(TAG, "SecurityException", paramh);
          return null;
        }
        catch (NoSuchMethodException paramh)
        {
          while (true)
            b.b(TAG, "NoSuchMethodException", paramh);
        }
        catch (IllegalArgumentException paramh)
        {
          while (true)
            b.b(TAG, "IllegalArgumentException", paramh);
        }
        catch (InstantiationException paramh)
        {
          while (true)
            b.b(TAG, "InstantiationException", paramh);
        }
        catch (IllegalAccessException paramh)
        {
          while (true)
            b.b(TAG, "IllegalAccessException", paramh);
        }
        catch (InvocationTargetException paramh)
        {
          while (true)
            b.b(TAG, "InvocationTargetException", paramh);
        }
        paramh = null;
      }
  }

  public g setHeader(Map<String, String> paramMap)
  {
    this.headers = paramMap;
    return this;
  }

  public boolean shouldCompressData()
  {
    return false;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     u.upd.g
 * JD-Core Version:    0.6.2
 */