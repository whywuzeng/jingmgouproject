package org.android.agoo.net.channel.chunked;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.umeng.message.proguard.bF;
import com.umeng.message.proguard.bd;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.android.agoo.net.channel.ChannelError;

public class HttpURLChunkedChannel extends a
{
  public static final String h = "agoo_push_errorid";
  public static final String i = "agoo_push_path";
  public static final String j = "agoo_connect_type";
  private static final String k = "HttpURLChunked";
  private volatile HttpURLConnection l = null;

  private final String a(List<String> paramList)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int n = paramList.size();
    int m = 0;
    while (m < n)
    {
      localStringBuffer.append((String)paramList.get(m));
      if (m < n - 1)
        localStringBuffer.append(",");
      m += 1;
    }
    return localStringBuffer.toString();
  }

  private final Map<String, String> a(HttpURLConnection paramHttpURLConnection)
  {
    HashMap localHashMap = new HashMap();
    paramHttpURLConnection = paramHttpURLConnection.getHeaderFields().entrySet().iterator();
    while (paramHttpURLConnection.hasNext())
    {
      Object localObject = (Map.Entry)paramHttpURLConnection.next();
      String str = (String)((Map.Entry)localObject).getKey();
      if (!TextUtils.isEmpty(str))
      {
        localObject = a((List)((Map.Entry)localObject).getValue());
        if (!TextUtils.isEmpty((CharSequence)localObject))
          localHashMap.put(str.toLowerCase(), localObject);
      }
    }
    return localHashMap;
  }

  private final void a(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramContext = paramContext.getSharedPreferences("AppStore", 4).edit();
      paramContext.putString("agoo_push_errorid", paramString1);
      paramContext.putString("agoo_push_path", paramString2);
      paramContext.putString("agoo_connect_type", "httpchunk");
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  private final void b(HttpURLConnection paramHttpURLConnection)
    throws IOException
  {
    if (paramHttpURLConnection == null)
      throw new IOException();
    this.b = paramHttpURLConnection.getInputStream();
  }

  protected final void a(String paramString, Map<String, String> paramMap)
  {
    try
    {
      Object localObject = this.f.getSharedPreferences("AppStore", 4).edit();
      ((SharedPreferences.Editor)localObject).putString("agoo_connect_type", "httpchunk");
      ((SharedPreferences.Editor)localObject).commit();
      while (true)
      {
        try
        {
          label37: bd.c("HttpURLChunked", "http chunked connectId:[" + b() + "]==>" + paramString);
          localObject = new URL(paramString);
          if (k())
          {
            bd.c("HttpURLChunked", "http chunked setPoxy:[" + super.i() + "][" + super.j() + "]");
            this.l = ((HttpURLConnection)((URL)localObject).openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(InetAddress.getByName(super.i()), super.j()))));
            this.l.setRequestProperty("User-agent", String.format("Agoo-sdk-%s", new Object[] { Double.valueOf(2.0D) }));
            if (paramMap == null)
              break;
            localObject = paramMap.keySet().iterator();
            if (!((Iterator)localObject).hasNext())
              break;
            String str = (String)((Iterator)localObject).next();
            this.l.setRequestProperty(str, (String)paramMap.get(str));
            continue;
          }
        }
        catch (Throwable paramString)
        {
          if (!hasCallError())
          {
            callError(true);
            bd.d("HttpURLChunked", "http chunked connectId:[" + b() + "]==>[Throwable]", paramString);
            a(ChannelError.s, paramString);
          }
          return;
        }
        this.l = ((HttpURLConnection)((URL)localObject).openConnection());
      }
      this.l.setReadTimeout(2147483647);
      this.l.setChunkedStreamingMode(2147483647);
      long l1 = System.currentTimeMillis();
      this.l.connect();
      long l2 = System.currentTimeMillis();
      int m = this.l.getResponseCode();
      paramMap = a(this.l);
      if (200 == m)
      {
        this.e = System.currentTimeMillis();
        b(this.l);
        a(l2 - l1, paramMap);
        e();
        return;
      }
      bd.d("HttpURLChunked", "http chunked connectId:[" + b() + "]==>[" + m + "]");
      if (!bF.a(Integer.toString(m)))
        a(this.f, Integer.toString(m), paramString);
      a(ChannelError.get(m), paramMap, new Throwable("http httpStatusCode==" + m));
      f();
      return;
    }
    catch (Throwable localThrowable)
    {
      break label37;
    }
  }

  protected final void c()
  {
    if (this.l != null)
    {
      this.l.disconnect();
      this.l = null;
    }
  }

  protected final void d()
  {
    if (this.l != null)
    {
      this.l.disconnect();
      this.l = null;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.channel.chunked.HttpURLChunkedChannel
 * JD-Core Version:    0.6.2
 */