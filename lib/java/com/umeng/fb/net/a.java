package com.umeng.fb.net;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.umeng.fb.model.Reply;
import com.umeng.fb.model.Store;
import com.umeng.fb.model.UserInfo;
import com.umeng.fb.util.Log;
import com.umeng.fb.util.b;
import com.umeng.fb.util.c;
import com.umeng.fb.util.d;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class a
{
  public static final String a = "http://fb.umeng.com";
  public static final String b = "http://fb.umeng.com/api/v2/user/getuid";
  public static final String c = "http://fb.umeng.com/api/v2/feedback/reply/new";
  public static final String d = "http://fb.umeng.com/api/v2/feedback/reply/show";
  public static final String e = "http://fb.umeng.com/api/v2/feedback/new";
  public static final String f = "http://fb.umeng.com/api/v2/user/update";
  private static final String g = a.class.getName();
  private static final int n = 10000;
  private Context h;
  private final String i = "multipart/form-data";
  private final String j = "UTF-8";
  private final String k = "--";
  private final String l = UUID.randomUUID().toString();
  private final String m = "\r\n";

  public a(Context paramContext)
  {
    this.h = paramContext;
    b();
  }

  private String a(Map<String, String> paramMap)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      localStringBuffer.append("--");
      localStringBuffer.append(this.l);
      localStringBuffer.append("\r\n");
      localStringBuffer.append("Content-Disposition: form-data; name=\"" + (String)localEntry.getKey() + "\"" + "\r\n");
      localStringBuffer.append("Content-Type: text/plain; charset=UTF-8\r\n");
      localStringBuffer.append("\r\n");
      localStringBuffer.append((String)localEntry.getValue());
      localStringBuffer.append("\r\n");
    }
    return localStringBuffer.toString();
  }

  private Map<String, String> a(String paramString1, String paramString2)
  {
    HashMap localHashMap = new HashMap();
    JSONObject localJSONObject = d.a(this.h);
    try
    {
      Iterator localIterator = localJSONObject.keys();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        localHashMap.put(str, localJSONObject.getString(str));
      }
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
      localHashMap.put("feedback_id", paramString1);
      localHashMap.put("reply_id", paramString2);
      localHashMap.put("device_uuid", Store.getInstance(this.h).getDeviceUUID());
      localHashMap.put("type", "user_reply");
    }
    return localHashMap;
  }

  private JSONObject a(Reply paramReply, Map<String, String> paramMap, String paramString)
    throws IOException
  {
    try
    {
      localHttpURLConnection = (HttpURLConnection)new URL(paramString).openConnection();
      localHttpURLConnection.setConnectTimeout(10000);
      localHttpURLConnection.setReadTimeout(10000);
      localHttpURLConnection.setDoOutput(true);
      localHttpURLConnection.setUseCaches(false);
      localHttpURLConnection.setRequestMethod("POST");
      localHttpURLConnection.setRequestProperty("Charset", "UTF-8");
      localHttpURLConnection.setRequestProperty("Connection", "keep-alive");
      localHttpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + this.l);
      localDataOutputStream = new DataOutputStream(localHttpURLConnection.getOutputStream());
      localDataOutputStream.write(a(paramMap).getBytes());
      if ("audio_reply".equals(paramReply.content_type))
        paramString = "voice";
      for (paramMap = new File(c.b(this.h) + paramReply.reply_id + ".opus"); !paramMap.exists(); paramMap = new File(c.b(this.h, paramReply.reply_id)))
      {
        Log.c("uploadFile", "File does not exist --" + paramMap.getAbsolutePath());
        return null;
        paramString = "pic";
      }
    }
    catch (MalformedURLException paramReply)
    {
      paramReply.printStackTrace();
      return null;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("--");
      localStringBuilder.append(this.l);
      localStringBuilder.append("\r\n");
      localStringBuilder.append("Content-Disposition: form-data; name=\"" + paramString + "\"; filename=\"" + paramReply.reply_id + "\"" + "\r\n");
      localStringBuilder.append("Content-Type: application/octet-stream\r\n");
      localStringBuilder.append("\r\n");
      localDataOutputStream.write(localStringBuilder.toString().getBytes());
      paramReply = new FileInputStream(paramMap);
      paramMap = new byte[1024];
      while (true)
      {
        int i1 = paramReply.read(paramMap);
        if (i1 == -1)
          break;
        localDataOutputStream.write(paramMap, 0, i1);
      }
    }
    catch (JSONException paramReply)
    {
      HttpURLConnection localHttpURLConnection;
      DataOutputStream localDataOutputStream;
      while (true)
        paramReply.printStackTrace();
      paramReply.close();
      localDataOutputStream.write("\r\n".getBytes());
      localDataOutputStream.write(("--" + this.l + "--" + "\r\n").getBytes());
      localDataOutputStream.flush();
      localDataOutputStream.close();
      paramReply = a(localHttpURLConnection.getInputStream());
      localHttpURLConnection.disconnect();
    }
    return paramReply;
  }

  private static JSONObject a(InputStream paramInputStream)
    throws IOException, JSONException
  {
    paramInputStream = new BufferedReader(new InputStreamReader(paramInputStream));
    StringBuilder localStringBuilder = new StringBuilder();
    while (true)
    {
      String str = paramInputStream.readLine();
      if (str == null)
        break;
      localStringBuilder.append(str);
    }
    paramInputStream.close();
    return new JSONObject(localStringBuilder.toString());
  }

  private static JSONObject a(String paramString)
    throws IOException
  {
    try
    {
      paramString = (HttpURLConnection)new URL(paramString).openConnection();
      paramString.setRequestMethod("GET");
      paramString.setConnectTimeout(10000);
      paramString.setReadTimeout(10000);
      if (paramString.getResponseCode() != 200)
        throw new RuntimeException("Failed : HTTP error code : " + paramString.getResponseCode());
    }
    catch (MalformedURLException paramString)
    {
      paramString.printStackTrace();
      return null;
      JSONObject localJSONObject = a(paramString.getInputStream());
      paramString.disconnect();
      Log.c(g, "\t " + localJSONObject);
      return localJSONObject;
    }
    catch (JSONException paramString)
    {
      while (true)
        paramString.printStackTrace();
    }
  }

  private static JSONObject a(String paramString, Map<String, Object> paramMap)
    throws IOException
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    paramString = new StringBuilder(paramString);
    if ((paramString.charAt(paramString.length() - 1) != '?') && (paramMap.size() > 0))
      paramString.append('?');
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str1 = (String)localIterator.next();
      String str2 = "" + paramMap.get(str1);
      paramString.append(URLEncoder.encode(str1, "UTF-8") + "=" + URLEncoder.encode(str2, "UTF-8") + "&");
    }
    if ('&' == paramString.charAt(paramString.length() - 1))
      paramString.deleteCharAt(paramString.length() - 1);
    return a(paramString.toString());
  }

  private static JSONObject a(JSONObject paramJSONObject, String paramString)
    throws IOException
  {
    try
    {
      paramString = (HttpURLConnection)new URL(paramString).openConnection();
      paramString.setDoOutput(true);
      paramString.setRequestMethod("POST");
      paramString.setRequestProperty("Content-Type", "application/json");
      paramString.setConnectTimeout(10000);
      paramString.setReadTimeout(10000);
      paramJSONObject = paramJSONObject.toString();
      BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(paramString.getOutputStream());
      localBufferedOutputStream.write(paramJSONObject.getBytes());
      localBufferedOutputStream.flush();
      if (paramString.getResponseCode() != 200)
        throw new RuntimeException("Failed : HTTP error code : " + paramString.getResponseCode());
    }
    catch (MalformedURLException paramJSONObject)
    {
      paramJSONObject.printStackTrace();
      return null;
      paramJSONObject = a(paramString.getInputStream());
      paramString.disconnect();
      Log.c(g, "\t response:\n" + paramJSONObject);
      return paramJSONObject;
    }
    catch (JSONException paramJSONObject)
    {
      while (true)
        paramJSONObject.printStackTrace();
    }
  }

  private Map<String, String> b(JSONObject paramJSONObject)
  {
    HashMap localHashMap = new HashMap();
    try
    {
      if (c(paramJSONObject))
      {
        String str = paramJSONObject.getJSONObject("data").getString("feedback_id");
        long l1 = paramJSONObject.getJSONObject("data").getLong("created_at");
        localHashMap.put("feedback_id", str);
        localHashMap.put("created_at", Long.valueOf(l1));
      }
      return localHashMap;
    }
    catch (JSONException paramJSONObject)
    {
      paramJSONObject.printStackTrace();
    }
    return localHashMap;
  }

  private static void b()
  {
    if (Integer.parseInt(Build.VERSION.SDK) < 8)
      System.setProperty("http.keepAlive", "false");
  }

  private boolean c(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null);
    while (true)
    {
      return false;
      try
      {
        paramJSONObject = paramJSONObject.getString("status");
        if (paramJSONObject != null)
        {
          boolean bool = paramJSONObject.equals("200");
          if (bool)
            return true;
        }
      }
      catch (JSONException paramJSONObject)
      {
        paramJSONObject.printStackTrace();
      }
    }
    return false;
  }

  public String a()
  {
    try
    {
      JSONObject localJSONObject = d.a(this.h);
      localStringBuilder = new StringBuilder("http://fb.umeng.com/api/v2/user/getuid");
      localStringBuilder.append("?");
      Iterator localIterator = localJSONObject.keys();
      while (localIterator.hasNext())
      {
        String str1 = (String)localIterator.next();
        String str2 = localJSONObject.get(str1).toString();
        localStringBuilder.append(URLEncoder.encode(str1, "UTF-8") + "=" + URLEncoder.encode(str2, "UTF-8") + "&");
      }
    }
    catch (Exception localException)
    {
      StringBuilder localStringBuilder;
      localException.printStackTrace();
      Object localObject1 = "";
      while (true)
      {
        return localObject1;
        if ('&' == localStringBuilder.charAt(localStringBuilder.length() - 1))
          localStringBuilder.deleteCharAt(localStringBuilder.length() - 1);
        localObject1 = a(localStringBuilder.toString());
        if (!c((JSONObject)localObject1))
          break;
        localObject1 = ((JSONObject)localObject1).getJSONObject("data").getString("uid");
        Store.getInstance(this.h).setUid((String)localObject1);
      }
    }
    finally
    {
    }
  }

  public List<Reply> a(String paramString, long paramLong)
    throws IOException, JSONException
  {
    Object localObject;
    try
    {
      localObject = new HashMap();
      ((Map)localObject).put("feedback_id", paramString);
      ((Map)localObject).put("appkey", b.p(this.h));
      ((Map)localObject).put("updated_at", Long.valueOf(paramLong));
      paramString = a("http://fb.umeng.com/api/v2/feedback/reply/show", (Map)localObject);
      Log.c(g, "getDevReply resp: " + paramString);
      paramString = paramString.getJSONObject("data").getJSONArray("result");
      localObject = new ArrayList();
      int i1 = 0;
      while (true)
      {
        int i2 = paramString.length();
        if (i1 < i2)
          try
          {
            ((List)localObject).add(Reply.fromJson(paramString.getJSONObject(i1)));
            i1 += 1;
          }
          catch (JSONException localJSONException)
          {
            while (true)
              localJSONException.printStackTrace();
          }
      }
    }
    finally
    {
    }
    return localObject;
  }

  public Map a(String paramString, Reply paramReply)
  {
    try
    {
      JSONObject localJSONObject;
      if ("text_reply".equals(paramReply.content_type))
      {
        localJSONObject = d.a(this.h);
        localJSONObject.put("content", paramReply.content);
        localJSONObject.put("feedback_id", paramString);
        localJSONObject.put("reply_id", paramReply.reply_id);
        localJSONObject.put("device_uuid", Store.getInstance(this.h).getDeviceUUID());
        localJSONObject.put("type", "user_reply");
      }
      for (paramString = a(localJSONObject, "http://fb.umeng.com/api/v2/feedback/reply/new"); ; paramString = a(paramReply, a(paramString, paramReply.reply_id), "http://fb.umeng.com/api/v2/feedback/reply/new"))
      {
        paramString = b(paramString);
        return paramString;
      }
    }
    catch (Exception paramString)
    {
      while (true)
      {
        paramString.printStackTrace();
        paramString = null;
      }
    }
    finally
    {
    }
    throw paramString;
  }

  public boolean a(JSONObject paramJSONObject)
  {
    try
    {
      JSONObject localJSONObject = d.a(this.h);
      localJSONObject.put("uid", Store.getInstance(this.h).getUid());
      localJSONObject.put("userinfo", paramJSONObject.toString());
      bool = c(a(localJSONObject, "http://fb.umeng.com/api/v2/user/update"));
      return bool;
    }
    catch (Exception paramJSONObject)
    {
      while (true)
      {
        paramJSONObject.printStackTrace();
        boolean bool = false;
      }
    }
    finally
    {
    }
    throw paramJSONObject;
  }

  public Map b(String paramString, Reply paramReply)
  {
    try
    {
      new HashMap();
      try
      {
        JSONObject localJSONObject;
        if ("text_reply".equals(paramReply.content_type))
        {
          localJSONObject = d.a(this.h);
          localJSONObject.put("content", paramReply.content);
          localJSONObject.put("uid", Store.getInstance(this.h).getUid());
          localJSONObject.put("device_uuid", Store.getInstance(this.h).getDeviceUUID());
          localJSONObject.put("feedback_id", paramString);
          if (Store.getInstance(this.h).getUserInfo().getRemarkJson() != null)
            localJSONObject.put("remark", Store.getInstance(this.h).getUserInfo().getRemarkJson().toString());
        }
        for (paramString = a(localJSONObject, "http://fb.umeng.com/api/v2/feedback/new"); ; paramString = a(paramReply, a(paramString, paramReply.reply_id), "http://fb.umeng.com/api/v2/feedback/new"))
        {
          paramString = b(paramString);
          return paramString;
        }
      }
      catch (Exception paramString)
      {
        while (true)
        {
          paramString.printStackTrace();
          paramString = null;
        }
      }
    }
    finally
    {
    }
    throw paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.net.a
 * JD-Core Version:    0.6.2
 */