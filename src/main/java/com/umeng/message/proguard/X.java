package com.umeng.message.proguard;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.json.JSONException;
import org.json.JSONObject;

public class X
{
  private Vector<W> a = new Vector();

  private JSONObject a(JSONObject paramJSONObject1, JSONObject paramJSONObject2)
  {
    JSONObject localJSONObject1;
    while (true)
    {
      JSONObject localJSONObject3;
      JSONObject localJSONObject2;
      try
      {
        localJSONObject1 = new JSONObject();
        Iterator localIterator = paramJSONObject1.keys();
        if (!localIterator.hasNext())
          break;
        String str = (String)localIterator.next();
        localJSONObject3 = paramJSONObject1.optJSONObject(str);
        if ((localJSONObject3 == null) || (!localJSONObject3.has("content")))
          continue;
        localJSONObject2 = new JSONObject();
        if ("gc_304".equals(localJSONObject3.optString("content")))
        {
          localJSONObject3 = paramJSONObject2.optJSONObject(str);
          localJSONObject2.put("t", localJSONObject3.getLong("t"));
          localJSONObject2.put("content", localJSONObject3.get("content"));
          localJSONObject1.put(str, localJSONObject2);
          continue;
        }
      }
      catch (Exception paramJSONObject1)
      {
        return new JSONObject();
      }
      localJSONObject2.put("t", localJSONObject3.getLong("t"));
      localJSONObject2.put("content", localJSONObject3.get("content"));
    }
    return localJSONObject1;
  }

  private void a(String paramString, JSONObject paramJSONObject)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject(paramString);
      if (localJSONObject != null)
      {
        Object localObject = null;
        paramString = (String)localObject;
        if (localJSONObject.has("data"))
        {
          paramString = (String)localObject;
          if (!at.a(localJSONObject.getString("data")))
            paramString = localJSONObject.getJSONObject("data");
        }
        localObject = paramString;
        if (paramString == null)
          localObject = new JSONObject();
        paramString = a((JSONObject)localObject, paramJSONObject);
        b(paramString);
        a(paramString);
      }
      return;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
  }

  private void a(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null)
      while (true)
      {
        W localW;
        try
        {
          if (this.a == null)
            break;
          Iterator localIterator = this.a.iterator();
          if (!localIterator.hasNext())
            break;
          localW = (W)localIterator.next();
          if (localW == null)
            continue;
          Object localObject = localW.d();
          if ((localObject == null) || (((List)localObject).size() <= 0))
            continue;
          localObject = ((List)localObject).iterator();
          if (((Iterator)localObject).hasNext())
          {
            String str = (String)((Iterator)localObject).next();
            boolean bool = paramJSONObject.has(str);
            if (!bool)
              break label149;
            try
            {
              localW.a(str, ((JSONObject)paramJSONObject.get(str)).getString("content"));
            }
            catch (Exception localException1)
            {
              localException1.printStackTrace();
            }
            continue;
          }
          continue;
        }
        finally
        {
        }
        try
        {
          label149: localW.b(localException1);
        }
        catch (Exception localException2)
        {
          localException2.printStackTrace();
        }
      }
  }

  private JSONObject b()
  {
    try
    {
      Object localObject1 = w.a().k();
      localObject1 = ((Context)localObject1).getSharedPreferences(ao.a((Context)localObject1, "UTMCConf"), 0);
      if (localObject1 != null)
      {
        localObject1 = ((SharedPreferences)localObject1).getString("conf_cache", "");
        if (!at.a((String)localObject1))
        {
          localObject1 = ai.a(((String)localObject1).getBytes("UTF-8"), 2);
          if ((localObject1 != null) && (localObject1.length > 0))
          {
            localObject1 = new JSONObject(new String((byte[])localObject1, "UTF-8"));
            return localObject1;
          }
        }
      }
    }
    catch (Exception localException)
    {
      while (true)
        Object localObject2 = null;
    }
    finally
    {
    }
  }

  @TargetApi(9)
  private void b(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null);
    try
    {
      Object localObject = w.a().k();
      localObject = ((Context)localObject).getSharedPreferences(ao.a((Context)localObject, "UTMCConf"), 0);
      if (localObject != null)
      {
        localObject = ((SharedPreferences)localObject).edit();
        ((SharedPreferences.Editor)localObject).putString("conf_cache", ai.b(paramJSONObject.toString().getBytes(), 2));
        if (Build.VERSION.SDK_INT < 9)
          break label71;
        as.a((SharedPreferences.Editor)localObject);
      }
      while (true)
      {
        return;
        label71: ((SharedPreferences.Editor)localObject).commit();
      }
    }
    finally
    {
    }
    throw paramJSONObject;
  }

  private String c(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null)
      return "";
    JSONObject localJSONObject1;
    try
    {
      localJSONObject1 = new JSONObject();
      Iterator localIterator = paramJSONObject.keys();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        JSONObject localJSONObject2 = paramJSONObject.optJSONObject(str);
        if ((localJSONObject2 != null) && (localJSONObject2.has("t")) && (localJSONObject2.optLong("t") > 0L))
        {
          JSONObject localJSONObject3 = new JSONObject();
          localJSONObject3.put("t", localJSONObject2.getLong("t"));
          localJSONObject1.put(str, localJSONObject3);
        }
      }
    }
    catch (Exception paramJSONObject)
    {
      return "";
    }
    paramJSONObject = localJSONObject1.toString();
    return paramJSONObject;
  }

  private void c()
  {
    Iterator localIterator = this.a.iterator();
    while (localIterator.hasNext())
    {
      W localW = (W)localIterator.next();
      Object localObject = localW.d();
      if ((localObject != null) && (((List)localObject).size() > 0))
      {
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext())
          localW.b((String)((Iterator)localObject).next());
      }
    }
  }

  public void a()
  {
    Object localObject = null;
    try
    {
      String str = Z.b("http://adash.m.taobao.com/rest/gc");
      localObject = str;
      if (!at.a((String)localObject))
      {
        localObject = new a((String)localObject);
        ((a)localObject).setDaemon(true);
        ((a)localObject).start();
      }
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public void a(W paramW)
  {
    if (paramW != null);
    try
    {
      this.a.add(paramW);
      return;
    }
    finally
    {
      paramW = finally;
    }
    throw paramW;
  }

  private class a extends Thread
  {
    private String b = null;

    public a(String arg2)
    {
      Object localObject;
      this.b = localObject;
    }

    public void run()
    {
      if (!ap.a(w.a().k()))
      {
        y.b(2, "sync configuration", "skip[No ActiveNetworkInfo]");
        localJSONObject = X.a(X.this);
        if (localJSONObject != null)
        {
          y.b(2, "sync configuration", "dispatch local config");
          X.a(X.this, localJSONObject);
        }
        return;
      }
      JSONObject localJSONObject = X.a(X.this);
      if (localJSONObject != null)
        X.a(X.this, localJSONObject);
      Object localObject;
      if (!at.a(this.b))
      {
        y.b(1, "url", this.b);
        localJSONObject = X.a(X.this);
        localObject = X.b(X.this, localJSONObject);
        HashMap localHashMap = new HashMap();
        localHashMap.put("cf", localObject);
        localObject = aj.a(3, this.b, localHashMap, true);
        if ((localObject == null) || (localObject.length <= 0));
      }
      try
      {
        localObject = new String((byte[])localObject, 0, localObject.length, "UTF-8");
        y.b(1, "result", localObject);
        try
        {
          if (ah.a((String)localObject))
          {
            X.a(X.this, (String)localObject, localJSONObject);
            return;
          }
        }
        catch (Exception localException)
        {
        }
        X.b(X.this);
        X.b(X.this);
        return;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        localUnsupportedEncodingException.printStackTrace();
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.X
 * JD-Core Version:    0.6.2
 */