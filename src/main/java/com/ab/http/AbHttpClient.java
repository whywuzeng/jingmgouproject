package com.ab.http;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.ab.global.AbAppException;
import com.ab.task.AbTaskPool;
import com.ab.util.AbAppUtil;
import com.ab.util.AbFileUtil;
import java.util.concurrent.ExecutorService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

public class AbHttpClient
{
  private static final int BUFFER_SIZE = 4096;
  private static final int DEFAULT_MAX_CONNECTIONS = 10;
  private static final int DEFAULT_MAX_RETRIES = 5;
  private static final int DEFAULT_SOCKET_BUFFER_SIZE = 8192;
  private static final int DEFAULT_SOCKET_TIMEOUT = 10000;
  protected static final int FAILURE_MESSAGE = 1;
  protected static final int FINISH_MESSAGE = 3;
  protected static final int PROGRESS_MESSAGE = 4;
  protected static final int RETRY_MESSAGE = 5;
  protected static final int START_MESSAGE = 2;
  protected static final int SUCCESS_MESSAGE = 0;
  private static final String TAG = "AbHttpClient";
  private static ExecutorService executorService = null;
  private boolean debug = false;
  private Context mContext;
  private int timeout = 10000;

  public AbHttpClient(Context paramContext)
  {
    executorService = AbTaskPool.getExecutorService();
    this.mContext = paramContext;
  }

  private void doGet(String paramString, AbRequestParams paramAbRequestParams, AbHttpResponseListener paramAbHttpResponseListener)
  {
    while (true)
    {
      int i;
      try
      {
        paramAbHttpResponseListener.sendStartMessage();
        if ((!this.debug) && (!AbAppUtil.isNetworkAvailable(this.mContext)))
        {
          paramAbHttpResponseListener.sendFailureMessage(600, "无法连接到网络", new AbAppException("无法连接到网络"));
          return;
        }
        str = paramString;
        if (paramAbRequestParams != null)
          str = paramString + paramAbRequestParams.getParamString();
        paramString = new HttpGet(str);
        paramAbRequestParams = new BasicHttpParams();
        ConnManagerParams.setTimeout(paramAbRequestParams, 10000L);
        ConnManagerParams.setMaxConnectionsPerRoute(paramAbRequestParams, new ConnPerRouteBean(10));
        ConnManagerParams.setMaxTotalConnections(paramAbRequestParams, 10);
        HttpConnectionParams.setSoTimeout(paramAbRequestParams, 10000);
        HttpConnectionParams.setConnectionTimeout(paramAbRequestParams, 10000);
        HttpConnectionParams.setTcpNoDelay(paramAbRequestParams, true);
        HttpConnectionParams.setSocketBufferSize(paramAbRequestParams, 8192);
        HttpProtocolParams.setVersion(paramAbRequestParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUserAgent(paramAbRequestParams, String.format("andbase-http/%s (http://www.418log.org/)", new Object[] { Double.valueOf(1.0D) }));
        paramString.setParams(paramAbRequestParams);
        paramString = new DefaultHttpClient().execute(paramString);
        i = paramString.getStatusLine().getStatusCode();
        paramAbRequestParams = paramString.getEntity();
        if (i == 200)
        {
          if ((paramAbHttpResponseListener instanceof AbStringHttpResponseListener))
          {
            paramString = EntityUtils.toString(paramAbRequestParams);
            ((AbStringHttpResponseListener)paramAbHttpResponseListener).sendSuccessMessage(i, paramString);
            return;
          }
          if ((paramAbHttpResponseListener instanceof AbBinaryHttpResponseListener))
          {
            readResponseData(paramAbRequestParams, (AbBinaryHttpResponseListener)paramAbHttpResponseListener);
            continue;
          }
        }
      }
      catch (Exception paramString)
      {
        String str;
        paramString.printStackTrace();
        paramAbHttpResponseListener.sendFailureMessage(900, paramString.getMessage(), new AbAppException(paramString));
        return;
        if (!(paramAbHttpResponseListener instanceof AbFileHttpResponseListener))
          continue;
        writeResponseData(paramAbRequestParams, AbFileUtil.getFileNameFromUrl(str, paramString), (AbFileHttpResponseListener)paramAbHttpResponseListener);
        continue;
      }
      finally
      {
        paramAbHttpResponseListener.sendFinishMessage();
      }
      paramAbHttpResponseListener.sendFailureMessage(i, EntityUtils.toString(paramAbRequestParams), new AbAppException("连接远程地址失败"));
    }
  }

  private void doPost(String paramString, AbRequestParams paramAbRequestParams, AbHttpResponseListener paramAbHttpResponseListener)
  {
    while (true)
    {
      Object localObject;
      int i;
      try
      {
        paramAbHttpResponseListener.sendStartMessage();
        if ((!this.debug) && (!AbAppUtil.isNetworkAvailable(this.mContext)))
        {
          paramAbHttpResponseListener.sendFailureMessage(600, "无法连接到网络", new AbAppException("无法连接到网络"));
          return;
        }
        localObject = new HttpPost(paramString);
        if (paramAbRequestParams != null)
          ((HttpPost)localObject).setEntity(paramAbRequestParams.getEntity(paramAbHttpResponseListener));
        paramAbRequestParams = new BasicHttpParams();
        ConnManagerParams.setTimeout(paramAbRequestParams, this.timeout);
        ConnManagerParams.setMaxConnectionsPerRoute(paramAbRequestParams, new ConnPerRouteBean(10));
        ConnManagerParams.setMaxTotalConnections(paramAbRequestParams, 10);
        HttpConnectionParams.setSoTimeout(paramAbRequestParams, this.timeout);
        HttpConnectionParams.setConnectionTimeout(paramAbRequestParams, this.timeout);
        HttpConnectionParams.setTcpNoDelay(paramAbRequestParams, true);
        HttpConnectionParams.setSocketBufferSize(paramAbRequestParams, 8192);
        HttpProtocolParams.setVersion(paramAbRequestParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUserAgent(paramAbRequestParams, String.format("andbase-http/%s (http://www.418log.org/)", new Object[] { Double.valueOf(1.0D) }));
        ((HttpPost)localObject).setParams(paramAbRequestParams);
        paramAbRequestParams = new DefaultHttpClient().execute((HttpUriRequest)localObject);
        i = paramAbRequestParams.getStatusLine().getStatusCode();
        localObject = paramAbRequestParams.getEntity();
        if (i == 200)
        {
          if ((paramAbHttpResponseListener instanceof AbStringHttpResponseListener))
          {
            paramString = EntityUtils.toString((HttpEntity)localObject);
            ((AbStringHttpResponseListener)paramAbHttpResponseListener).sendSuccessMessage(i, paramString);
            return;
          }
          if ((paramAbHttpResponseListener instanceof AbBinaryHttpResponseListener))
          {
            readResponseData((HttpEntity)localObject, (AbBinaryHttpResponseListener)paramAbHttpResponseListener);
            continue;
          }
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        paramAbHttpResponseListener.sendFailureMessage(900, paramString.getMessage(), new AbAppException(paramString));
        return;
        if (!(paramAbHttpResponseListener instanceof AbFileHttpResponseListener))
          continue;
        writeResponseData((HttpEntity)localObject, AbFileUtil.getFileNameFromUrl(paramString, paramAbRequestParams), (AbFileHttpResponseListener)paramAbHttpResponseListener);
        continue;
      }
      finally
      {
        paramAbHttpResponseListener.sendFinishMessage();
      }
      paramAbHttpResponseListener.sendFailureMessage(i, EntityUtils.toString((HttpEntity)localObject), new AbAppException("连接远程地址失败"));
    }
  }

  public void get(String paramString, AbHttpResponseListener paramAbHttpResponseListener)
  {
    get(paramString, null, paramAbHttpResponseListener);
  }

  public void get(final String paramString, final AbRequestParams paramAbRequestParams, final AbHttpResponseListener paramAbHttpResponseListener)
  {
    paramAbHttpResponseListener.setHandler(new ResponderHandler(paramAbHttpResponseListener));
    executorService.submit(new Runnable()
    {
      public void run()
      {
        try
        {
          AbHttpClient.this.doGet(paramString, paramAbRequestParams, paramAbHttpResponseListener);
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    });
  }

  public boolean isDebug()
  {
    return this.debug;
  }

  public void post(String paramString, AbHttpResponseListener paramAbHttpResponseListener)
  {
    post(paramString, null, paramAbHttpResponseListener);
  }

  public void post(final String paramString, final AbRequestParams paramAbRequestParams, final AbHttpResponseListener paramAbHttpResponseListener)
  {
    paramAbHttpResponseListener.setHandler(new ResponderHandler(paramAbHttpResponseListener));
    executorService.submit(new Runnable()
    {
      public void run()
      {
        try
        {
          AbHttpClient.this.doPost(paramString, paramAbRequestParams, paramAbHttpResponseListener);
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    });
  }

  // ERROR //
  public void readResponseData(HttpEntity paramHttpEntity, AbBinaryHttpResponseListener paramAbBinaryHttpResponseListener)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +4 -> 5
    //   4: return
    //   5: aconst_null
    //   6: astore 10
    //   8: aconst_null
    //   9: astore 7
    //   11: aconst_null
    //   12: astore 11
    //   14: aconst_null
    //   15: astore 12
    //   17: aload 11
    //   19: astore 9
    //   21: aload_1
    //   22: invokeinterface 304 1 0
    //   27: astore 8
    //   29: aload 8
    //   31: astore 7
    //   33: aload 11
    //   35: astore 9
    //   37: aload 8
    //   39: astore 10
    //   41: new 306	java/io/ByteArrayOutputStream
    //   44: dup
    //   45: invokespecial 307	java/io/ByteArrayOutputStream:<init>	()V
    //   48: astore 11
    //   50: aload_1
    //   51: invokeinterface 311 1 0
    //   56: lstore 5
    //   58: aload 8
    //   60: ifnull +25 -> 85
    //   63: iconst_0
    //   64: istore_3
    //   65: sipush 4096
    //   68: newarray byte
    //   70: astore_1
    //   71: aload 8
    //   73: aload_1
    //   74: invokevirtual 317	java/io/InputStream:read	([B)I
    //   77: istore 4
    //   79: iload 4
    //   81: iconst_m1
    //   82: if_icmpne +36 -> 118
    //   85: aload_2
    //   86: sipush 200
    //   89: aload 11
    //   91: invokevirtual 321	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   94: invokevirtual 324	com/ab/http/AbBinaryHttpResponseListener:sendSuccessMessage	(I[B)V
    //   97: aload 8
    //   99: ifnull +8 -> 107
    //   102: aload 8
    //   104: invokevirtual 327	java/io/InputStream:close	()V
    //   107: aload 11
    //   109: ifnull +135 -> 244
    //   112: aload 11
    //   114: invokevirtual 328	java/io/ByteArrayOutputStream:close	()V
    //   117: return
    //   118: iload_3
    //   119: iload 4
    //   121: iadd
    //   122: istore_3
    //   123: aload 11
    //   125: aload_1
    //   126: iconst_0
    //   127: iload 4
    //   129: invokevirtual 332	java/io/ByteArrayOutputStream:write	([BII)V
    //   132: aload_2
    //   133: iload_3
    //   134: lload 5
    //   136: l2i
    //   137: invokevirtual 336	com/ab/http/AbBinaryHttpResponseListener:sendProgressMessage	(II)V
    //   140: goto -69 -> 71
    //   143: astore 7
    //   145: aload 11
    //   147: astore_1
    //   148: aload 7
    //   150: astore 11
    //   152: aload 8
    //   154: astore 7
    //   156: aload_1
    //   157: astore 9
    //   159: aload 11
    //   161: invokevirtual 242	java/lang/Exception:printStackTrace	()V
    //   164: aload 8
    //   166: astore 7
    //   168: aload_1
    //   169: astore 9
    //   171: aload_2
    //   172: sipush 602
    //   175: ldc_w 338
    //   178: aload 11
    //   180: invokevirtual 339	com/ab/http/AbBinaryHttpResponseListener:sendFailureMessage	(ILjava/lang/String;Ljava/lang/Throwable;)V
    //   183: aload 8
    //   185: ifnull +8 -> 193
    //   188: aload 8
    //   190: invokevirtual 327	java/io/InputStream:close	()V
    //   193: aload_1
    //   194: ifnull -190 -> 4
    //   197: aload_1
    //   198: invokevirtual 328	java/io/ByteArrayOutputStream:close	()V
    //   201: return
    //   202: astore_1
    //   203: aload_1
    //   204: invokevirtual 242	java/lang/Exception:printStackTrace	()V
    //   207: return
    //   208: astore_1
    //   209: aload 7
    //   211: ifnull +8 -> 219
    //   214: aload 7
    //   216: invokevirtual 327	java/io/InputStream:close	()V
    //   219: aload 9
    //   221: ifnull +8 -> 229
    //   224: aload 9
    //   226: invokevirtual 328	java/io/ByteArrayOutputStream:close	()V
    //   229: aload_1
    //   230: athrow
    //   231: astore_2
    //   232: aload_2
    //   233: invokevirtual 242	java/lang/Exception:printStackTrace	()V
    //   236: goto -7 -> 229
    //   239: astore_1
    //   240: aload_1
    //   241: invokevirtual 242	java/lang/Exception:printStackTrace	()V
    //   244: return
    //   245: astore_1
    //   246: aload 11
    //   248: astore 9
    //   250: aload 8
    //   252: astore 7
    //   254: goto -45 -> 209
    //   257: astore 11
    //   259: aload 10
    //   261: astore 8
    //   263: aload 12
    //   265: astore_1
    //   266: goto -114 -> 152
    //
    // Exception table:
    //   from	to	target	type
    //   50	58	143	java/lang/Exception
    //   65	71	143	java/lang/Exception
    //   71	79	143	java/lang/Exception
    //   85	97	143	java/lang/Exception
    //   123	140	143	java/lang/Exception
    //   188	193	202	java/lang/Exception
    //   197	201	202	java/lang/Exception
    //   21	29	208	finally
    //   41	50	208	finally
    //   159	164	208	finally
    //   171	183	208	finally
    //   214	219	231	java/lang/Exception
    //   224	229	231	java/lang/Exception
    //   102	107	239	java/lang/Exception
    //   112	117	239	java/lang/Exception
    //   50	58	245	finally
    //   65	71	245	finally
    //   71	79	245	finally
    //   85	97	245	finally
    //   123	140	245	finally
    //   21	29	257	java/lang/Exception
    //   41	50	257	java/lang/Exception
  }

  public void setDebug(boolean paramBoolean)
  {
    this.debug = paramBoolean;
  }

  public void setTimeout(int paramInt)
  {
    this.timeout = paramInt;
  }

  // ERROR //
  public void writeResponseData(HttpEntity paramHttpEntity, String paramString, AbFileHttpResponseListener paramAbFileHttpResponseListener)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +4 -> 5
    //   4: return
    //   5: aload_3
    //   6: invokevirtual 347	com/ab/http/AbFileHttpResponseListener:getFile	()Ljava/io/File;
    //   9: ifnonnull +8 -> 17
    //   12: aload_3
    //   13: aload_2
    //   14: invokevirtual 350	com/ab/http/AbFileHttpResponseListener:setFile	(Ljava/lang/String;)V
    //   17: aconst_null
    //   18: astore 10
    //   20: aconst_null
    //   21: astore_2
    //   22: aconst_null
    //   23: astore 11
    //   25: aconst_null
    //   26: astore 12
    //   28: aload 11
    //   30: astore 9
    //   32: aload_1
    //   33: invokeinterface 304 1 0
    //   38: astore 8
    //   40: aload 8
    //   42: astore_2
    //   43: aload 11
    //   45: astore 9
    //   47: aload 8
    //   49: astore 10
    //   51: aload_1
    //   52: invokeinterface 311 1 0
    //   57: lstore 6
    //   59: aload 8
    //   61: astore_2
    //   62: aload 11
    //   64: astore 9
    //   66: aload 8
    //   68: astore 10
    //   70: new 352	java/io/FileOutputStream
    //   73: dup
    //   74: aload_3
    //   75: invokevirtual 347	com/ab/http/AbFileHttpResponseListener:getFile	()Ljava/io/File;
    //   78: invokespecial 355	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   81: astore_1
    //   82: aload 8
    //   84: ifnull +35 -> 119
    //   87: sipush 4096
    //   90: newarray byte
    //   92: astore_2
    //   93: iconst_0
    //   94: istore 4
    //   96: aload 8
    //   98: aload_2
    //   99: invokevirtual 317	java/io/InputStream:read	([B)I
    //   102: istore 5
    //   104: iload 5
    //   106: iconst_m1
    //   107: if_icmpeq +12 -> 119
    //   110: invokestatic 361	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   113: invokevirtual 364	java/lang/Thread:isInterrupted	()Z
    //   116: ifeq +33 -> 149
    //   119: aload_3
    //   120: sipush 200
    //   123: invokevirtual 366	com/ab/http/AbFileHttpResponseListener:sendSuccessMessage	(I)V
    //   126: aload 8
    //   128: ifnull +8 -> 136
    //   131: aload 8
    //   133: invokevirtual 327	java/io/InputStream:close	()V
    //   136: aload_1
    //   137: ifnull +138 -> 275
    //   140: aload_1
    //   141: invokevirtual 369	java/io/FileOutputStream:flush	()V
    //   144: aload_1
    //   145: invokevirtual 370	java/io/FileOutputStream:close	()V
    //   148: return
    //   149: iload 4
    //   151: iload 5
    //   153: iadd
    //   154: istore 4
    //   156: aload_1
    //   157: aload_2
    //   158: iconst_0
    //   159: iload 5
    //   161: invokevirtual 371	java/io/FileOutputStream:write	([BII)V
    //   164: aload_3
    //   165: iload 4
    //   167: lload 6
    //   169: l2i
    //   170: invokevirtual 372	com/ab/http/AbFileHttpResponseListener:sendProgressMessage	(II)V
    //   173: goto -77 -> 96
    //   176: astore 11
    //   178: aload 8
    //   180: astore_2
    //   181: aload_1
    //   182: astore 9
    //   184: aload 11
    //   186: invokevirtual 242	java/lang/Exception:printStackTrace	()V
    //   189: aload 8
    //   191: astore_2
    //   192: aload_1
    //   193: astore 9
    //   195: aload_3
    //   196: sipush 602
    //   199: ldc_w 338
    //   202: aload 11
    //   204: invokevirtual 373	com/ab/http/AbFileHttpResponseListener:sendFailureMessage	(ILjava/lang/String;Ljava/lang/Throwable;)V
    //   207: aload 8
    //   209: ifnull +8 -> 217
    //   212: aload 8
    //   214: invokevirtual 327	java/io/InputStream:close	()V
    //   217: aload_1
    //   218: ifnull -214 -> 4
    //   221: aload_1
    //   222: invokevirtual 369	java/io/FileOutputStream:flush	()V
    //   225: aload_1
    //   226: invokevirtual 370	java/io/FileOutputStream:close	()V
    //   229: return
    //   230: astore_1
    //   231: aload_1
    //   232: invokevirtual 374	java/io/IOException:printStackTrace	()V
    //   235: return
    //   236: astore_1
    //   237: aload_2
    //   238: ifnull +7 -> 245
    //   241: aload_2
    //   242: invokevirtual 327	java/io/InputStream:close	()V
    //   245: aload 9
    //   247: ifnull +13 -> 260
    //   250: aload 9
    //   252: invokevirtual 369	java/io/FileOutputStream:flush	()V
    //   255: aload 9
    //   257: invokevirtual 370	java/io/FileOutputStream:close	()V
    //   260: aload_1
    //   261: athrow
    //   262: astore_2
    //   263: aload_2
    //   264: invokevirtual 374	java/io/IOException:printStackTrace	()V
    //   267: goto -7 -> 260
    //   270: astore_1
    //   271: aload_1
    //   272: invokevirtual 374	java/io/IOException:printStackTrace	()V
    //   275: return
    //   276: astore_3
    //   277: aload 8
    //   279: astore_2
    //   280: aload_1
    //   281: astore 9
    //   283: aload_3
    //   284: astore_1
    //   285: goto -48 -> 237
    //   288: astore 11
    //   290: aload 10
    //   292: astore 8
    //   294: aload 12
    //   296: astore_1
    //   297: goto -119 -> 178
    //
    // Exception table:
    //   from	to	target	type
    //   87	93	176	java/lang/Exception
    //   96	104	176	java/lang/Exception
    //   110	119	176	java/lang/Exception
    //   119	126	176	java/lang/Exception
    //   156	173	176	java/lang/Exception
    //   212	217	230	java/io/IOException
    //   221	229	230	java/io/IOException
    //   32	40	236	finally
    //   51	59	236	finally
    //   70	82	236	finally
    //   184	189	236	finally
    //   195	207	236	finally
    //   241	245	262	java/io/IOException
    //   250	260	262	java/io/IOException
    //   131	136	270	java/io/IOException
    //   140	148	270	java/io/IOException
    //   87	93	276	finally
    //   96	104	276	finally
    //   110	119	276	finally
    //   119	126	276	finally
    //   156	173	276	finally
    //   32	40	288	java/lang/Exception
    //   51	59	288	java/lang/Exception
    //   70	82	288	java/lang/Exception
  }

  private static class ResponderHandler extends Handler
  {
    private Object[] response;
    private AbHttpResponseListener responseListener;

    public ResponderHandler(AbHttpResponseListener paramAbHttpResponseListener)
    {
      this.responseListener = paramAbHttpResponseListener;
    }

    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
      case 0:
        do
        {
          do
          {
            return;
            this.response = ((Object[])paramMessage.obj);
          }
          while (this.response == null);
          if ((this.responseListener instanceof AbStringHttpResponseListener))
          {
            if (this.response.length >= 2)
            {
              ((AbStringHttpResponseListener)this.responseListener).onSuccess(((Integer)this.response[0]).intValue(), (String)this.response[1]);
              return;
            }
            Log.e("AbHttpClient", "SUCCESS_MESSAGE 参数没有包含足够的值");
            return;
          }
          if ((this.responseListener instanceof AbBinaryHttpResponseListener))
          {
            if (this.response.length >= 2)
            {
              ((AbBinaryHttpResponseListener)this.responseListener).onSuccess(((Integer)this.response[0]).intValue(), (byte[])this.response[1]);
              return;
            }
            Log.e("AbHttpClient", "SUCCESS_MESSAGE 参数没有包含足够的值");
            return;
          }
        }
        while (!(this.responseListener instanceof AbFileHttpResponseListener));
        if (this.response.length >= 1)
        {
          paramMessage = (AbFileHttpResponseListener)this.responseListener;
          paramMessage.onSuccess(((Integer)this.response[0]).intValue(), paramMessage.getFile());
          return;
        }
        Log.e("AbHttpClient", "SUCCESS_MESSAGE 参数没有包含足够的值");
        return;
      case 1:
        this.response = ((Object[])paramMessage.obj);
        if ((this.response != null) && (this.response.length >= 3))
        {
          paramMessage = new AbAppException((Exception)this.response[2]);
          this.responseListener.onFailure(((Integer)this.response[0]).intValue(), (String)this.response[1], paramMessage);
          return;
        }
        Log.e("AbHttpClient", "FAILURE_MESSAGE 参数没有包含足够的值");
        return;
      case 2:
        this.responseListener.onStart();
        return;
      case 3:
        this.responseListener.onFinish();
        return;
      case 4:
        this.response = ((Object[])paramMessage.obj);
        if ((this.response != null) && (this.response.length >= 2))
        {
          this.responseListener.onProgress(((Integer)this.response[0]).intValue(), ((Integer)this.response[1]).intValue());
          return;
        }
        Log.e("AbHttpClient", "PROGRESS_MESSAGE 参数没有包含足够的值");
        return;
      case 5:
      }
      this.responseListener.onRetry();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.http.AbHttpClient
 * JD-Core Version:    0.6.2
 */