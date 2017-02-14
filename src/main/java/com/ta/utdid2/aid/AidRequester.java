package com.ta.utdid2.aid;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.ta.utdid2.android.utils.DebugUtils;
import com.ta.utdid2.android.utils.NetworkUtils;
import com.ut.device.AidCallback;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class AidRequester
{
  private static final String AIDFUNCNAME = "/get_aid/";
  private static final String AIDSERVER = "http://hydra.alibaba.com/";
  private static final String NAME_AID = "&aid=";
  private static final String NAME_ID = "&id=";
  private static final String NAME_RESULT_ACTION = "action";
  private static final String NAME_RESULT_AID = "aid";
  private static final String NAME_RESUTL_DATA = "data";
  private static final String NAME_TOKEN = "auth[token]=";
  private static final String NAME_TYPE = "&type=";
  private static final String RSP_ACTION_CHANGED = "changed";
  private static final String RSP_ACTION_NEW = "new";
  private static final String RSP_ACTION_UNCHANGED = "unchanged";
  private static final int SESSION_TIME_OUT = 1000;
  private static final String TAG = AidRequester.class.getName();
  private static final String TYPE_UTDID = "utdid";
  private static final int WEAK_SESSION_TIME_OUT = 3000;
  private static AidRequester sAidRequester = null;
  private Context mContext;
  private Object mLock = new Object();

  public AidRequester(Context paramContext)
  {
    this.mContext = paramContext;
  }

  private static String getAidFromJsonRsp(String paramString1, String paramString2)
  {
    String str1 = paramString2;
    if (!TextUtils.isEmpty(paramString1));
    try
    {
      paramString1 = new JSONObject(paramString1);
      str1 = paramString2;
      if (paramString1.has("data"))
      {
        paramString1 = paramString1.getJSONObject("data");
        str1 = paramString2;
        if (paramString1.has("action"))
        {
          str1 = paramString2;
          if (paramString1.has("aid"))
          {
            String str2 = paramString1.getString("action");
            if (!str2.equalsIgnoreCase("new"))
            {
              str1 = paramString2;
              if (!str2.equalsIgnoreCase("changed"));
            }
            else
            {
              str1 = paramString1.getString("aid");
            }
          }
        }
      }
      return str1;
    }
    catch (JSONException paramString1)
    {
      Log.e(TAG, paramString1.toString());
      return paramString2;
    }
    catch (Exception paramString1)
    {
      Log.e(TAG, paramString1.toString());
    }
    return paramString2;
  }

  public static AidRequester getInstance(Context paramContext)
  {
    try
    {
      if (sAidRequester == null)
        sAidRequester = new AidRequester(paramContext);
      paramContext = sAidRequester;
      return paramContext;
    }
    finally
    {
    }
    throw paramContext;
  }

  private static String getPostUrl(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    return "http://hydra.alibaba.com/" + paramString1 + "/get_aid/" + "?" + "auth[token]=" + paramString2 + "&type=" + "utdid" + "&id=" + paramString3 + "&aid=" + paramString4;
  }

  public String postRest(String paramString1, String arg2, String paramString3, String paramString4)
  {
    paramString1 = getPostUrl(paramString1, ???, paramString3, paramString4);
    int i;
    if (NetworkUtils.isConnectedToWeakNetwork(this.mContext))
    {
      i = 3000;
      if (DebugUtils.DBG)
        Log.d(TAG, "url:" + paramString1 + "; timeout:" + i);
      paramString1 = new a(new HttpPost(paramString1));
      paramString1.start();
    }
    try
    {
      synchronized (this.mLock)
      {
        this.mLock.wait(i);
        paramString1 = paramString1.a();
        if (DebugUtils.DBG)
          Log.d(TAG, "mLine:" + paramString1);
        return getAidFromJsonRsp(paramString1, paramString4);
        i = 1000;
      }
    }
    catch (Exception )
    {
      while (true)
        Log.e(TAG, ???.toString());
    }
  }

  public void postRestAsync(String paramString1, String paramString2, String paramString3, String paramString4, AidCallback paramAidCallback)
  {
    paramString3 = getPostUrl(paramString1, paramString2, paramString3, paramString4);
    if (DebugUtils.DBG)
      Log.d(TAG, "url:" + paramString3 + "; len:" + paramString3.length());
    new a(new HttpPost(paramString3), paramAidCallback, paramString4, paramString1, paramString2).start();
  }

  class a extends Thread
  {
    HttpPost a;
    String b = "";
    AidCallback c;
    String d;
    String e;
    String f = "";

    public a(HttpPost arg2)
    {
      Object localObject;
      this.a = localObject;
    }

    public a(HttpPost paramAidCallback, AidCallback paramString1, String paramString2, String paramString3, String arg6)
    {
      this.a = paramAidCallback;
      this.c = paramString1;
      this.d = paramString2;
      this.e = paramString3;
      Object localObject;
      this.f = localObject;
    }

    public String a()
    {
      return this.b;
    }

    public void run()
    {
      String str2 = null;
      if (this.c != null)
        this.c.onAidEventChanged(1000, this.d);
      ??? = new DefaultHttpClient();
      try
      {
        ??? = ((HttpClient)???).execute(this.a);
        if (??? == null);
      }
      catch (Exception localIOException)
      {
        try
        {
          ??? = new BufferedReader(new InputStreamReader(((HttpResponse)???).getEntity().getContent(), Charset.forName("UTF-8")));
          if (??? != null)
          {
            try
            {
              while (true)
              {
                str2 = ((BufferedReader)???).readLine();
                if (str2 == null)
                  break;
                if (DebugUtils.DBG)
                  Log.d(AidRequester.TAG, str2);
                this.b = str2;
              }
            }
            catch (Exception localException5)
            {
              if (this.c != null)
                this.c.onAidEventChanged(1002, this.d);
              Log.e(AidRequester.TAG, localException5.toString());
            }
            if (??? == null);
          }
        }
        catch (Exception localIOException)
        {
          try
          {
            ((BufferedReader)???).close();
            if (DebugUtils.DBG)
              Log.d(AidRequester.TAG, "close the bufferreader");
            if (this.c != null);
          }
          catch (IOException localIOException)
          {
            synchronized (AidRequester.this.mLock)
            {
              while (true)
              {
                AidRequester.this.mLock.notifyAll();
                return;
                localException1 = localException1;
                if (this.c != null)
                  this.c.onAidEventChanged(1002, this.d);
                Log.e(AidRequester.TAG, localException1.toString());
                Exception localException2 = null;
                continue;
                Log.e(AidRequester.TAG, "response is null!");
                localException2 = localException5;
                continue;
                localException3 = localException3;
                if (this.c != null)
                  this.c.onAidEventChanged(1002, this.d);
                Log.e(AidRequester.TAG, localException3.toString());
                Exception localException4 = localException5;
                continue;
                Log.e(AidRequester.TAG, "BufferredReader is null!");
              }
              localIOException = localIOException;
              Log.e(AidRequester.TAG, localIOException.toString());
            }
          }
        }
      }
      String str1 = AidRequester.getAidFromJsonRsp(this.b, this.d);
      this.c.onAidEventChanged(1001, str1);
      AidStorageController.setAidValueToSP(AidRequester.this.mContext, this.e, str1, this.f);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ta.utdid2.aid.AidRequester
 * JD-Core Version:    0.6.2
 */