package com.ismartgo.app.http;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import cn.sharesdk.onekeyshare.CustomerLogo;
import com.ismartgo.app.activity.BaseActivity;
import com.ismartgo.app.andbase.util.AbBase64;
import com.ismartgo.app.application.AndroidApplication;
import com.ismartgo.app.bean.Session;
import com.ismartgo.app.bean.User;
import com.ismartgo.app.common.CommonMethod;
import com.ismartgo.app.grid.utils.Helper;
import com.ismartgo.app.tools.DataUtil;
import com.ismartgo.app.tools.SharedPreferenceUtil;
import com.ismartgo.app.tools.TelephoneUtils;
import com.ismartgo.app.url.Url;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.Response;
import com.yolanda.nohttp.tools.NetUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.json.JSONObject;

public class HttpRequest
  implements HttpCallback<String>
{
  private static final int CONNECT_TIMEOUT = 10000;
  private static final int READ_TIMEOUT = 10000;
  private static HttpRequest mHttpRequest;
  private HttpCallback<String> callback;
  private Context context;
  private SessionHandler handler;
  private Map<String, String> param;
  private Session session;
  private String url;
  private int what;

  private Request<String> addHttpHeader(Request<String> paramRequest, Map<String, String> paramMap)
  {
    StringBuilder localStringBuilder = new StringBuilder("android`");
    if (BaseActivity.loginUser == null);
    for (String str = "0"; ; str = BaseActivity.loginUser.getId())
    {
      paramRequest.addHeader("_dev_code", AbBase64.encode(str + "`" + TelephoneUtils.getIMEI(AndroidApplication.getInstance()), "utf-8"));
      paramRequest.addHeader("_app_key", DataUtil.md5(CommonMethod.formatMap(paramMap) + "#" + CustomerLogo.U));
      if ((this.session != null) && (this.session.getSessionid() != null))
        paramRequest.addHeader("_session_id", this.session.getSessionid());
      return paramRequest;
    }
  }

  private void executePostJsonRequest(Context paramContext, String paramString, int paramInt, Map<String, String> paramMap, HttpCallback<JSONObject> paramHttpCallback)
  {
    if (!NetUtil.isNetworkAvailable(paramContext))
    {
      paramHttpCallback.onFailed(paramInt, null, null, "网络异常");
      return;
    }
    paramString = new JsonRequest(paramString, 1);
    paramString.setAllowHttps(true);
    paramString.setConnectTimeout(10000);
    paramString.setReadTimeout(10000);
    if ((paramMap != null) && (!paramMap.isEmpty()))
      paramString.add(paramMap);
    CallServer.getRequestInstance().add(paramContext, paramInt, paramString, paramHttpCallback);
  }

  public static HttpRequest getInstance()
  {
    mHttpRequest = new HttpRequest();
    return mHttpRequest;
  }

  private Request<String> setRequest(String paramString)
  {
    paramString = NoHttp.createStringRequest(paramString, 1);
    paramString.setAllowHttps(true);
    paramString.setConnectTimeout(10000);
    paramString.setReadTimeout(10000);
    return paramString;
  }

  public void checkVersion(Session paramSession)
  {
    if (SharedPreferenceUtil.getHomeBottonVersion(this.context) < paramSession.getHomebuttonversion())
    {
      SharedPreferenceUtil.setHomeBottonVersion(this.context, paramSession.getHomebuttonversion());
      SharedPreferenceUtil.setHomeBottonFlag(this.context, 0);
    }
    if (SharedPreferenceUtil.getReceiptShopVersion(this.context) < paramSession.getReceiptshoptypeversion())
    {
      SharedPreferenceUtil.setReceiptShopVersion(this.context, paramSession.getReceiptshoptypeversion());
      SharedPreferenceUtil.setReceiptShopFlag(this.context, 0);
    }
    String str = SharedPreferenceUtil.getReceiptSvUrl(this.context);
    if ((CommonMethod.isEmpty(str)) || (!str.equals(paramSession.getReceiptsvurl())))
      SharedPreferenceUtil.setReceiptSvUrl(this.context, paramSession.getReceiptsvurl());
  }

  public void clickCount(Context paramContext, String paramString)
  {
    if (BaseActivity.loginUser != null)
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("cityName", BaseActivity.city);
      localHashMap.put("uid", BaseActivity.loginUser.getId());
      localHashMap.put("goodsid", paramString);
      localHashMap.put("lon", BaseActivity.log);
      localHashMap.put("lat", BaseActivity.lat);
      Log.i("HomeActivity", "url: " + Url.GOODS_CLICK_URL + "?" + localHashMap);
      getInstance().executePostStringRequest(paramContext, Url.GOODS_CLICK_URL, 6, localHashMap, new HttpCallback()
      {
        public void onFailed(int paramAnonymousInt, String paramAnonymousString, Object paramAnonymousObject, CharSequence paramAnonymousCharSequence)
        {
        }

        public void onSucceed(int paramAnonymousInt, Response<String> paramAnonymousResponse)
        {
        }
      });
    }
  }

  public void execute()
  {
    executePostStringRequest(this.context, this.url, this.what, this.param, this.callback);
  }

  public void executePostStringRequest(Context paramContext, String paramString, int paramInt, Map<String, String> paramMap, HttpCallback<String> paramHttpCallback)
  {
    if (!NetUtil.isNetworkAvailable(paramContext))
    {
      paramHttpCallback.onFailed(paramInt, null, null, "亲，您的网络好像出问题了~~");
      return;
    }
    this.context = paramContext;
    this.url = paramString;
    this.what = paramInt;
    this.param = paramMap;
    this.callback = paramHttpCallback;
    Object localObject = SharedPreferenceUtil.getSessionExpire(AndroidApplication.getInstance());
    String str1 = SharedPreferenceUtil.getSessionId(AndroidApplication.getInstance());
    String str2 = SharedPreferenceUtil.getSessionVersion(AndroidApplication.getInstance());
    String str3 = Helper.getVersion(AndroidApplication.getInstance());
    String str4 = CommonMethod.getCurrentDate("yyyy-MM-dd HH:mm:ss");
    if ((!TextUtils.isEmpty((CharSequence)localObject)) && (((String)localObject).compareTo(str4) >= 0) && (str2.equals(str3)))
    {
      this.session = new Session();
      this.session.setSessionid(str1);
      this.session.setExpire((String)localObject);
      localObject = setRequest(paramString);
      paramString = paramMap;
      if (paramMap == null)
        paramString = new HashMap();
      if (!paramString.containsKey("uid"))
        if (BaseActivity.loginUser != null)
          break label248;
      label248: for (paramMap = "0"; ; paramMap = BaseActivity.loginUser.getId())
      {
        paramString.put("uid", paramMap);
        paramString.put("devcode", TelephoneUtils.getIMEI(AndroidApplication.getInstance()));
        paramMap = addHttpHeader((Request)localObject, paramString);
        paramMap.add(paramString);
        CallServer.getRequestInstance().add(paramContext, paramInt, paramMap, paramHttpCallback);
        return;
      }
    }
    try
    {
      this.handler = new SessionHandler(Looper.myLooper());
      new Thread(new SessionThread(UUID.randomUUID().toString(), str4)).start();
      Thread.sleep(10L);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }

  public void getSession(String paramString)
  {
    new Thread()
    {
      public void run()
      {
        super.run();
      }
    }
    .start();
  }

  public void onFailed(int paramInt, String paramString, Object paramObject, CharSequence paramCharSequence)
  {
  }

  public void onSucceed(int paramInt, Response<String> paramResponse)
  {
    if (paramInt == 1)
    {
      if ((paramResponse == null) || (paramResponse.get() == null))
        Toast.makeText(this.context, "获取session失败", 0).show();
    }
    else
      return;
    this.session = HttpJsonParse.sessionJsonParse(((String)paramResponse.get()).toString());
    if ((this.session == null) || (TextUtils.isEmpty(this.session.getSessionid())))
    {
      Toast.makeText(this.context, "获取session失败", 0).show();
      return;
    }
    executePostStringRequest(this.context, this.url, this.what, this.param, this.callback);
  }

  private class SessionHandler extends Handler
  {
    public SessionHandler()
    {
    }

    public SessionHandler(Handler.Callback arg2)
    {
      super();
    }

    public SessionHandler(Looper arg2)
    {
      super();
    }

    public SessionHandler(Looper paramCallback, Handler.Callback arg3)
    {
      super(localCallback);
    }

    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      if (paramMessage.what == 1)
      {
        HttpRequest.this.execute();
        return;
      }
      int i = paramMessage.what;
    }
  }

  class SessionThread
    implements Runnable
  {
    private String date;
    private String threadId;

    public SessionThread(String paramString1, String arg3)
    {
      this.threadId = paramString1;
      Object localObject;
      this.date = localObject;
    }

    // ERROR //
    private void taskHandler(String paramString)
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: ldc 36
      //   4: ldc 38
      //   6: invokestatic 44	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
      //   9: pop
      //   10: new 46	java/net/URL
      //   13: dup
      //   14: getstatic 51	com/ismartgo/app/url/Url:GET_APP_SESSION	Ljava/lang/String;
      //   17: invokespecial 53	java/net/URL:<init>	(Ljava/lang/String;)V
      //   20: invokevirtual 57	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   23: checkcast 59	java/net/HttpURLConnection
      //   26: astore_1
      //   27: aload_1
      //   28: sipush 5000
      //   31: invokevirtual 63	java/net/HttpURLConnection:setConnectTimeout	(I)V
      //   34: aload_1
      //   35: sipush 5000
      //   38: invokevirtual 66	java/net/HttpURLConnection:setReadTimeout	(I)V
      //   41: aload_1
      //   42: iconst_1
      //   43: invokevirtual 70	java/net/HttpURLConnection:setDoOutput	(Z)V
      //   46: aload_1
      //   47: iconst_1
      //   48: invokevirtual 73	java/net/HttpURLConnection:setDoInput	(Z)V
      //   51: aload_1
      //   52: iconst_0
      //   53: invokevirtual 76	java/net/HttpURLConnection:setUseCaches	(Z)V
      //   56: aload_1
      //   57: ldc 78
      //   59: invokevirtual 81	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
      //   62: aload_1
      //   63: ldc 83
      //   65: ldc 85
      //   67: invokevirtual 89	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   70: aload_0
      //   71: getfield 25	com/ismartgo/app/http/HttpRequest$SessionThread:date	Ljava/lang/String;
      //   74: invokestatic 95	com/ismartgo/app/http/HttpRequestParam:getSessionParam	(Ljava/lang/String;)Ljava/util/Map;
      //   77: astore 4
      //   79: new 97	java/lang/StringBuffer
      //   82: dup
      //   83: invokespecial 98	java/lang/StringBuffer:<init>	()V
      //   86: astore_3
      //   87: aload 4
      //   89: invokeinterface 104 1 0
      //   94: invokeinterface 110 1 0
      //   99: astore 4
      //   101: aload 4
      //   103: invokeinterface 116 1 0
      //   108: ifne +165 -> 273
      //   111: aload_3
      //   112: aload_3
      //   113: invokevirtual 120	java/lang/StringBuffer:length	()I
      //   116: iconst_1
      //   117: isub
      //   118: invokevirtual 124	java/lang/StringBuffer:deleteCharAt	(I)Ljava/lang/StringBuffer;
      //   121: pop
      //   122: new 126	java/io/DataOutputStream
      //   125: dup
      //   126: aload_1
      //   127: invokevirtual 130	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   130: invokespecial 133	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
      //   133: astore 4
      //   135: aload 4
      //   137: aload_3
      //   138: invokevirtual 137	java/lang/StringBuffer:toString	()Ljava/lang/String;
      //   141: invokevirtual 143	java/lang/String:getBytes	()[B
      //   144: invokevirtual 147	java/io/DataOutputStream:write	([B)V
      //   147: aload 4
      //   149: invokevirtual 150	java/io/DataOutputStream:flush	()V
      //   152: aload 4
      //   154: invokevirtual 153	java/io/DataOutputStream:close	()V
      //   157: aload_1
      //   158: invokevirtual 156	java/net/HttpURLConnection:getResponseCode	()I
      //   161: sipush 200
      //   164: if_icmpne +274 -> 438
      //   167: aload_1
      //   168: invokevirtual 160	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
      //   171: astore_1
      //   172: new 162	java/io/ByteArrayOutputStream
      //   175: dup
      //   176: invokespecial 163	java/io/ByteArrayOutputStream:<init>	()V
      //   179: astore_3
      //   180: sipush 1024
      //   183: newarray byte
      //   185: astore 4
      //   187: aload_1
      //   188: aload 4
      //   190: invokevirtual 169	java/io/InputStream:read	([B)I
      //   193: istore_2
      //   194: iload_2
      //   195: iconst_m1
      //   196: if_icmpne +151 -> 347
      //   199: aload_1
      //   200: invokevirtual 170	java/io/InputStream:close	()V
      //   203: aload_3
      //   204: invokevirtual 171	java/io/ByteArrayOutputStream:close	()V
      //   207: new 139	java/lang/String
      //   210: dup
      //   211: aload_3
      //   212: invokevirtual 174	java/io/ByteArrayOutputStream:toByteArray	()[B
      //   215: ldc 85
      //   217: invokespecial 177	java/lang/String:<init>	([BLjava/lang/String;)V
      //   220: astore_1
      //   221: aload_0
      //   222: getfield 18	com/ismartgo/app/http/HttpRequest$SessionThread:this$0	Lcom/ismartgo/app/http/HttpRequest;
      //   225: aload_1
      //   226: invokestatic 183	com/ismartgo/app/http/HttpJsonParse:sessionJsonParse	(Ljava/lang/String;)Lcom/ismartgo/app/bean/Session;
      //   229: invokestatic 187	com/ismartgo/app/http/HttpRequest:access$0	(Lcom/ismartgo/app/http/HttpRequest;Lcom/ismartgo/app/bean/Session;)V
      //   232: aload_0
      //   233: getfield 18	com/ismartgo/app/http/HttpRequest$SessionThread:this$0	Lcom/ismartgo/app/http/HttpRequest;
      //   236: invokestatic 191	com/ismartgo/app/http/HttpRequest:access$1	(Lcom/ismartgo/app/http/HttpRequest;)Lcom/ismartgo/app/bean/Session;
      //   239: ifnull +19 -> 258
      //   242: aload_0
      //   243: getfield 18	com/ismartgo/app/http/HttpRequest$SessionThread:this$0	Lcom/ismartgo/app/http/HttpRequest;
      //   246: invokestatic 191	com/ismartgo/app/http/HttpRequest:access$1	(Lcom/ismartgo/app/http/HttpRequest;)Lcom/ismartgo/app/bean/Session;
      //   249: invokevirtual 196	com/ismartgo/app/bean/Session:getSessionid	()Ljava/lang/String;
      //   252: invokestatic 202	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   255: ifeq +115 -> 370
      //   258: aload_0
      //   259: getfield 18	com/ismartgo/app/http/HttpRequest$SessionThread:this$0	Lcom/ismartgo/app/http/HttpRequest;
      //   262: invokestatic 206	com/ismartgo/app/http/HttpRequest:access$2	(Lcom/ismartgo/app/http/HttpRequest;)Lcom/ismartgo/app/http/HttpRequest$SessionHandler;
      //   265: iconst_0
      //   266: invokevirtual 212	com/ismartgo/app/http/HttpRequest$SessionHandler:sendEmptyMessage	(I)Z
      //   269: pop
      //   270: aload_0
      //   271: monitorexit
      //   272: return
      //   273: aload 4
      //   275: invokeinterface 216 1 0
      //   280: checkcast 218	java/util/Map$Entry
      //   283: astore 5
      //   285: aload_3
      //   286: aload 5
      //   288: invokeinterface 221 1 0
      //   293: checkcast 139	java/lang/String
      //   296: invokevirtual 225	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   299: ldc 227
      //   301: invokevirtual 225	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   304: aload 5
      //   306: invokeinterface 230 1 0
      //   311: checkcast 139	java/lang/String
      //   314: invokevirtual 225	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   317: ldc 232
      //   319: invokevirtual 225	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   322: pop
      //   323: goto -222 -> 101
      //   326: astore_1
      //   327: aload_1
      //   328: invokevirtual 235	java/net/MalformedURLException:printStackTrace	()V
      //   331: ldc 36
      //   333: ldc 237
      //   335: invokestatic 44	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
      //   338: pop
      //   339: goto -69 -> 270
      //   342: astore_1
      //   343: aload_0
      //   344: monitorexit
      //   345: aload_1
      //   346: athrow
      //   347: aload_3
      //   348: aload 4
      //   350: iconst_0
      //   351: iload_2
      //   352: invokevirtual 240	java/io/ByteArrayOutputStream:write	([BII)V
      //   355: aload_3
      //   356: invokevirtual 241	java/io/ByteArrayOutputStream:flush	()V
      //   359: goto -172 -> 187
      //   362: astore_1
      //   363: aload_1
      //   364: invokevirtual 242	java/net/ProtocolException:printStackTrace	()V
      //   367: goto -36 -> 331
      //   370: getstatic 248	java/lang/System:out	Ljava/io/PrintStream;
      //   373: new 250	java/lang/StringBuilder
      //   376: dup
      //   377: ldc 252
      //   379: invokespecial 253	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   382: aload_0
      //   383: getfield 18	com/ismartgo/app/http/HttpRequest$SessionThread:this$0	Lcom/ismartgo/app/http/HttpRequest;
      //   386: invokestatic 191	com/ismartgo/app/http/HttpRequest:access$1	(Lcom/ismartgo/app/http/HttpRequest;)Lcom/ismartgo/app/bean/Session;
      //   389: invokevirtual 256	com/ismartgo/app/bean/Session:getExpire	()Ljava/lang/String;
      //   392: invokevirtual 259	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   395: invokevirtual 260	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   398: invokevirtual 265	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   401: aload_0
      //   402: getfield 18	com/ismartgo/app/http/HttpRequest$SessionThread:this$0	Lcom/ismartgo/app/http/HttpRequest;
      //   405: aload_0
      //   406: getfield 18	com/ismartgo/app/http/HttpRequest$SessionThread:this$0	Lcom/ismartgo/app/http/HttpRequest;
      //   409: invokestatic 191	com/ismartgo/app/http/HttpRequest:access$1	(Lcom/ismartgo/app/http/HttpRequest;)Lcom/ismartgo/app/bean/Session;
      //   412: invokevirtual 269	com/ismartgo/app/http/HttpRequest:checkVersion	(Lcom/ismartgo/app/bean/Session;)V
      //   415: aload_0
      //   416: getfield 18	com/ismartgo/app/http/HttpRequest$SessionThread:this$0	Lcom/ismartgo/app/http/HttpRequest;
      //   419: invokestatic 206	com/ismartgo/app/http/HttpRequest:access$2	(Lcom/ismartgo/app/http/HttpRequest;)Lcom/ismartgo/app/http/HttpRequest$SessionHandler;
      //   422: iconst_1
      //   423: invokevirtual 212	com/ismartgo/app/http/HttpRequest$SessionHandler:sendEmptyMessage	(I)Z
      //   426: pop
      //   427: goto -96 -> 331
      //   430: astore_1
      //   431: aload_1
      //   432: invokevirtual 270	java/io/IOException:printStackTrace	()V
      //   435: goto -104 -> 331
      //   438: aload_0
      //   439: getfield 18	com/ismartgo/app/http/HttpRequest$SessionThread:this$0	Lcom/ismartgo/app/http/HttpRequest;
      //   442: invokestatic 206	com/ismartgo/app/http/HttpRequest:access$2	(Lcom/ismartgo/app/http/HttpRequest;)Lcom/ismartgo/app/http/HttpRequest$SessionHandler;
      //   445: iconst_0
      //   446: invokevirtual 212	com/ismartgo/app/http/HttpRequest$SessionHandler:sendEmptyMessage	(I)Z
      //   449: pop
      //   450: goto -119 -> 331
      //
      // Exception table:
      //   from	to	target	type
      //   10	101	326	java/net/MalformedURLException
      //   101	187	326	java/net/MalformedURLException
      //   187	194	326	java/net/MalformedURLException
      //   199	258	326	java/net/MalformedURLException
      //   258	270	326	java/net/MalformedURLException
      //   273	323	326	java/net/MalformedURLException
      //   347	359	326	java/net/MalformedURLException
      //   370	427	326	java/net/MalformedURLException
      //   438	450	326	java/net/MalformedURLException
      //   2	10	342	finally
      //   10	101	342	finally
      //   101	187	342	finally
      //   187	194	342	finally
      //   199	258	342	finally
      //   258	270	342	finally
      //   273	323	342	finally
      //   327	331	342	finally
      //   331	339	342	finally
      //   347	359	342	finally
      //   363	367	342	finally
      //   370	427	342	finally
      //   431	435	342	finally
      //   438	450	342	finally
      //   10	101	362	java/net/ProtocolException
      //   101	187	362	java/net/ProtocolException
      //   187	194	362	java/net/ProtocolException
      //   199	258	362	java/net/ProtocolException
      //   258	270	362	java/net/ProtocolException
      //   273	323	362	java/net/ProtocolException
      //   347	359	362	java/net/ProtocolException
      //   370	427	362	java/net/ProtocolException
      //   438	450	362	java/net/ProtocolException
      //   10	101	430	java/io/IOException
      //   101	187	430	java/io/IOException
      //   187	194	430	java/io/IOException
      //   199	258	430	java/io/IOException
      //   258	270	430	java/io/IOException
      //   273	323	430	java/io/IOException
      //   347	359	430	java/io/IOException
      //   370	427	430	java/io/IOException
      //   438	450	430	java/io/IOException
    }

    public void run()
    {
      taskHandler(this.threadId);
      Log.i("session", "session thread id: " + this.threadId);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.http.HttpRequest
 * JD-Core Version:    0.6.2
 */