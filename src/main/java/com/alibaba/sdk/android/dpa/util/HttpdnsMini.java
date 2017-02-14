package com.alibaba.sdk.android.dpa.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONObject;

public class HttpdnsMini
{
  private static final int EMPTY_RESULT_HOST_TTL = 30;
  private static final int MAX_HOLD_HOST_NUM = 100;
  private static final int MAX_THREAD_NUM = 5;
  private static final int RESOLVE_TIMEOUT_IN_SEC = 10;
  private static final String SERVER_HOST = "httpdns.aliyuncs.com";
  private static final String SERVER_IP = "140.205.143.143";
  private static final String TAG = "HttpDnsMini";
  private static final int THRESHOLD_DEGRADE_HOST = 5;
  private static AtomicInteger globalNetworkError = new AtomicInteger(0);
  private static HttpdnsMini instance = new HttpdnsMini();
  private ConcurrentMap<String, HostObject> hostManager = new ConcurrentHashMap();
  private ExecutorService pool = Executors.newFixedThreadPool(5);

  public static HttpdnsMini getInstance()
  {
    return instance;
  }

  public String getIpByHost(String paramString)
  {
    HostObject localHostObject = (HostObject)this.hostManager.get(paramString);
    if ((localHostObject == null) || (localHostObject.isExpired()))
    {
      DpaLog.logD("HttpDnsMini", "[getIpByHost] - refresh host: " + paramString);
      paramString = this.pool.submit(new QueryHostTask(paramString));
      try
      {
        paramString = (String)paramString.get();
        return paramString;
      }
      catch (Exception paramString)
      {
        if (DpaLog.isEnableLog())
          paramString.printStackTrace();
        return null;
      }
    }
    return localHostObject.getIp();
  }

  public String getIpByHostAsync(String paramString)
  {
    Object localObject = null;
    HostObject localHostObject = (HostObject)this.hostManager.get(paramString);
    if ((localHostObject == null) || (localHostObject.isExpired()))
    {
      DpaLog.logD("HttpDnsMini", "[getIpByHostAsync] - refresh host: " + paramString);
      this.pool.submit(new QueryHostTask(paramString));
    }
    paramString = localObject;
    if (localHostObject != null)
    {
      paramString = localObject;
      if (localHostObject.isStillAvailable())
        paramString = localHostObject.getIp();
    }
    return paramString;
  }

  class HostObject
  {
    private String hostName;
    private String ip;
    private long queryTime;
    private long ttl;

    HostObject()
    {
    }

    public String getHostName()
    {
      return this.hostName;
    }

    public String getIp()
    {
      return this.ip;
    }

    public long getQueryTime()
    {
      return this.queryTime;
    }

    public long getTtl()
    {
      return this.ttl;
    }

    public boolean isExpired()
    {
      return getQueryTime() + this.ttl < System.currentTimeMillis() / 1000L;
    }

    public boolean isStillAvailable()
    {
      return getQueryTime() + this.ttl + 600L > System.currentTimeMillis() / 1000L;
    }

    public void setHostName(String paramString)
    {
      this.hostName = paramString;
    }

    public void setIp(String paramString)
    {
      this.ip = paramString;
    }

    public void setQueryTime(long paramLong)
    {
      this.queryTime = paramLong;
    }

    public void setTtl(long paramLong)
    {
      this.ttl = paramLong;
    }

    public String toString()
    {
      return "HostObject [hostName=" + this.hostName + ", ip=" + this.ip + ", ttl=" + this.ttl + ", queryTime=" + this.queryTime + "]";
    }
  }

  class QueryHostTask
    implements Callable<String>
  {
    private boolean hasRetryed = false;
    private String hostName;

    public QueryHostTask(String arg2)
    {
      Object localObject;
      this.hostName = localObject;
    }

    public String call()
    {
      Object localObject1 = "140.205.143.143";
      if (HttpdnsMini.globalNetworkError.get() > 5)
        localObject1 = "httpdns.aliyuncs.com";
      localObject1 = "http://" + (String)localObject1 + "/d?host=" + this.hostName;
      DpaLog.logD("HttpDnsMini", "[run] - buildUrl: " + (String)localObject1);
      try
      {
        localObject1 = (HttpURLConnection)new URL((String)localObject1).openConnection();
        ((HttpURLConnection)localObject1).setConnectTimeout(10000);
        ((HttpURLConnection)localObject1).setReadTimeout(10000);
        if (((HttpURLConnection)localObject1).getResponseCode() != 200)
        {
          DpaLog.logD("HttpDnsMini", "[run] - responseCodeNot 200, but: " + ((HttpURLConnection)localObject1).getResponseCode());
          if (!this.hasRetryed)
          {
            this.hasRetryed = true;
            localObject3 = call();
            return localObject3;
          }
        }
        else
        {
          HttpdnsMini.globalNetworkError.decrementAndGet();
          localObject1 = new BufferedReader(new InputStreamReader(((HttpURLConnection)localObject1).getInputStream(), "UTF-8"));
          localObject3 = new StringBuilder();
          while (true)
          {
            localObject4 = ((BufferedReader)localObject1).readLine();
            if (localObject4 == null)
              break;
            ((StringBuilder)localObject3).append((String)localObject4);
          }
        }
      }
      catch (Exception localException)
      {
        Object localObject3;
        long l2;
        do
        {
          while (true)
          {
            HttpdnsMini.globalNetworkError.incrementAndGet();
            if (DpaLog.isEnableLog())
              localException.printStackTrace();
          }
          localObject2 = new JSONObject(((StringBuilder)localObject3).toString());
          localObject3 = ((JSONObject)localObject2).getString("host");
          l2 = ((JSONObject)localObject2).getLong("ttl");
          localObject2 = ((JSONObject)localObject2).getJSONArray("ips");
        }
        while (localObject3 == null);
        long l1 = l2;
        if (l2 == 0L)
          l1 = 30L;
        Object localObject4 = new HttpdnsMini.HostObject(HttpdnsMini.this);
        if (localObject2 == null);
        for (Object localObject2 = null; ; localObject2 = ((JSONArray)localObject2).getString(0))
        {
          DpaLog.logD("HttpDnsMini", "[run] - resolve host:" + (String)localObject3 + " ip:" + (String)localObject2 + " ttl:" + l1);
          ((HttpdnsMini.HostObject)localObject4).setHostName((String)localObject3);
          ((HttpdnsMini.HostObject)localObject4).setTtl(l1);
          ((HttpdnsMini.HostObject)localObject4).setIp((String)localObject2);
          ((HttpdnsMini.HostObject)localObject4).setQueryTime(System.currentTimeMillis() / 1000L);
          localObject3 = localObject2;
          if (HttpdnsMini.this.hostManager.size() >= 100)
            break;
          HttpdnsMini.this.hostManager.put(this.hostName, localObject4);
          return localObject2;
        }
      }
      return null;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.sdk.android.dpa.util.HttpdnsMini
 * JD-Core Version:    0.6.2
 */