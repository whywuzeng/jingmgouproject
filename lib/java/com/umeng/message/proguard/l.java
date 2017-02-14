package com.umeng.message.proguard;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import com.squareup.wire.Wire;
import com.umeng.common.message.Log;
import com.umeng.common.message.UmengMessageDeviceConfig;
import com.umeng.message.PushAgent;
import com.umeng.message.protobuffer.PushResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class l
{
  public static final int a = 1;
  public static final int b = 2;
  public static final int c = 3;
  private static final String i = l.class.getName();
  private final int d = 1;
  private String e;
  private String f = "10.0.0.172";
  private int g = 80;
  private Context h;

  public l(Context paramContext)
  {
    this.h = paramContext;
    this.e = a(paramContext);
  }

  private PushResponse a(byte[] paramArrayOfByte)
  {
    try
    {
      paramArrayOfByte = (PushResponse)new Wire(new Class[0]).parseFrom(paramArrayOfByte, PushResponse.class);
      if (paramArrayOfByte != null)
        Log.a(i, "NetWork Response code:" + paramArrayOfByte.code + "," + "msg:" + paramArrayOfByte.description + "," + "info:" + paramArrayOfByte.info);
      return paramArrayOfByte;
    }
    catch (IOException paramArrayOfByte)
    {
      while (true)
      {
        paramArrayOfByte.printStackTrace();
        paramArrayOfByte = null;
      }
    }
  }

  private String a(Context paramContext)
  {
    StringBuffer localStringBuffer1 = new StringBuffer();
    localStringBuffer1.append("2.5.0");
    localStringBuffer1.append("/");
    localStringBuffer1.append("2.5.0");
    localStringBuffer1.append(" ");
    try
    {
      StringBuffer localStringBuffer2 = new StringBuffer();
      localStringBuffer2.append(UmengMessageDeviceConfig.getApplicationLable(paramContext));
      localStringBuffer2.append("/");
      localStringBuffer2.append(UmengMessageDeviceConfig.getAppVersionName(paramContext));
      localStringBuffer2.append(" ");
      localStringBuffer2.append(Build.MODEL);
      localStringBuffer2.append("/");
      localStringBuffer2.append(Build.VERSION.RELEASE);
      localStringBuffer2.append(" ");
      localStringBuffer2.append(g.a(PushAgent.getInstance(paramContext).getMessageAppkey()));
      localStringBuffer1.append(URLEncoder.encode(localStringBuffer2.toString(), "UTF-8"));
      return localStringBuffer1.toString();
    }
    catch (Exception paramContext)
    {
      while (true)
        paramContext.printStackTrace();
    }
  }

  private boolean a()
  {
    if (this.h.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", this.h.getPackageName()) != 0)
      return false;
    try
    {
      Object localObject = ((ConnectivityManager)this.h.getSystemService("connectivity")).getActiveNetworkInfo();
      if ((localObject != null) && (((NetworkInfo)localObject).getType() != 1))
      {
        localObject = ((NetworkInfo)localObject).getExtraInfo();
        if (localObject != null)
          if ((!((String)localObject).equals("cmwap")) && (!((String)localObject).equals("3gwap")))
          {
            boolean bool = ((String)localObject).equals("uniwap");
            if (!bool);
          }
          else
          {
            return true;
          }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  private byte[] b(byte[] paramArrayOfByte, String paramString)
  {
    HttpPost localHttpPost = new HttpPost(paramString);
    Object localObject = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout((HttpParams)localObject, 10000);
    HttpConnectionParams.setSoTimeout((HttpParams)localObject, 30000);
    localObject = new DefaultHttpClient((HttpParams)localObject);
    localHttpPost.addHeader("Msg-Type", "envelope");
    try
    {
      localHttpPost.setEntity(new InputStreamEntity(new ByteArrayInputStream(paramArrayOfByte), paramArrayOfByte.length));
      paramArrayOfByte = ((HttpClient)localObject).execute(localHttpPost);
      int j = paramArrayOfByte.getStatusLine().getStatusCode();
      Log.a(i, "status code : " + j);
      if (paramArrayOfByte.getStatusLine().getStatusCode() == 200)
      {
        Log.a(i, "Sent message to " + paramString);
        paramArrayOfByte = paramArrayOfByte.getEntity();
        if (paramArrayOfByte != null)
        {
          paramArrayOfByte = paramArrayOfByte.getContent();
          try
          {
            paramString = g.a(paramArrayOfByte);
            return paramString;
          }
          finally
          {
            g.b(paramArrayOfByte);
          }
        }
      }
    }
    catch (ClientProtocolException paramArrayOfByte)
    {
      Log.b(i, "ClientProtocolException,Failed to send message.", paramArrayOfByte);
      return null;
      return null;
      return null;
    }
    catch (IOException paramArrayOfByte)
    {
      Log.b(i, "IOException,Failed to send message.", paramArrayOfByte);
    }
    return null;
  }

  public PushResponse a(byte[] paramArrayOfByte, String paramString)
  {
    paramArrayOfByte = b(paramArrayOfByte, paramString);
    if (paramArrayOfByte == null)
      return null;
    return a(paramArrayOfByte);
  }

  // ERROR //
  public byte[] a(org.json.JSONObject paramJSONObject)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: iconst_1
    //   4: istore_2
    //   5: aload_0
    //   6: getfield 44	com/umeng/message/proguard/l:h	Landroid/content/Context;
    //   9: invokestatic 316	com/umeng/message/MsgLogStore:getInstance	(Landroid/content/Context;)Lcom/umeng/message/MsgLogStore;
    //   12: invokevirtual 319	com/umeng/message/MsgLogStore:getMsgConfigInfo_SerialNo	()I
    //   15: istore_3
    //   16: iload_3
    //   17: istore_2
    //   18: aload_0
    //   19: getfield 44	com/umeng/message/proguard/l:h	Landroid/content/Context;
    //   22: invokestatic 141	com/umeng/message/PushAgent:getInstance	(Landroid/content/Context;)Lcom/umeng/message/PushAgent;
    //   25: invokevirtual 144	com/umeng/message/PushAgent:getMessageAppkey	()Ljava/lang/String;
    //   28: astore 7
    //   30: invokestatic 325	java/lang/System:currentTimeMillis	()J
    //   33: ldc2_w 326
    //   36: ldiv
    //   37: l2i
    //   38: istore_3
    //   39: iconst_3
    //   40: anewarray 202	java/lang/String
    //   43: dup
    //   44: iconst_0
    //   45: aload_1
    //   46: invokevirtual 330	org/json/JSONObject:toString	()Ljava/lang/String;
    //   49: aastore
    //   50: dup
    //   51: iconst_1
    //   52: ldc_w 332
    //   55: aastore
    //   56: dup
    //   57: iconst_2
    //   58: aload 7
    //   60: iconst_0
    //   61: bipush 16
    //   63: invokevirtual 336	java/lang/String:substring	(II)Ljava/lang/String;
    //   66: aastore
    //   67: invokestatic 341	com/umeng/message/proguard/b:a	([Ljava/lang/String;)Ljava/lang/String;
    //   70: astore 4
    //   72: aload 4
    //   74: invokevirtual 345	java/lang/String:getBytes	()[B
    //   77: iconst_0
    //   78: aload 4
    //   80: invokevirtual 345	java/lang/String:getBytes	()[B
    //   83: arraylength
    //   84: invokestatic 350	com/umeng/message/proguard/aE:a	([BII)Lcom/umeng/message/proguard/aE;
    //   87: astore 5
    //   89: aload 4
    //   91: invokestatic 149	com/umeng/message/proguard/g:a	(Ljava/lang/String;)Ljava/lang/String;
    //   94: astore 6
    //   96: new 65	java/lang/StringBuilder
    //   99: dup
    //   100: invokespecial 66	java/lang/StringBuilder:<init>	()V
    //   103: aload 7
    //   105: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   108: iload_3
    //   109: invokevirtual 278	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   112: aload 4
    //   114: invokestatic 149	com/umeng/message/proguard/g:a	(Ljava/lang/String;)Ljava/lang/String;
    //   117: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   120: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   123: invokestatic 149	com/umeng/message/proguard/g:a	(Ljava/lang/String;)Ljava/lang/String;
    //   126: astore 4
    //   128: getstatic 29	com/umeng/message/proguard/l:i	Ljava/lang/String;
    //   131: new 65	java/lang/StringBuilder
    //   134: dup
    //   135: invokespecial 66	java/lang/StringBuilder:<init>	()V
    //   138: ldc_w 352
    //   141: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: aload_1
    //   145: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   148: ldc_w 354
    //   151: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   154: iload_3
    //   155: invokevirtual 278	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   158: ldc 81
    //   160: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   163: ldc_w 356
    //   166: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   169: aload 6
    //   171: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   174: ldc 81
    //   176: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   179: ldc_w 358
    //   182: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   185: aload 4
    //   187: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: ldc 81
    //   192: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   195: ldc_w 360
    //   198: invokevirtual 72	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   201: iload_2
    //   202: invokevirtual 278	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   205: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   208: invokestatic 362	com/umeng/common/message/Log:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   211: aload_0
    //   212: getfield 44	com/umeng/message/proguard/l:h	Landroid/content/Context;
    //   215: invokestatic 316	com/umeng/message/MsgLogStore:getInstance	(Landroid/content/Context;)Lcom/umeng/message/MsgLogStore;
    //   218: iload_2
    //   219: iconst_1
    //   220: iadd
    //   221: invokevirtual 366	com/umeng/message/MsgLogStore:setMsgConfigInfo_SerialNo	(I)V
    //   224: new 368	com/umeng/message/protobuffer/PushRequest$Builder
    //   227: dup
    //   228: invokespecial 369	com/umeng/message/protobuffer/PushRequest$Builder:<init>	()V
    //   231: ldc_w 371
    //   234: invokevirtual 375	com/umeng/message/protobuffer/PushRequest$Builder:version	(Ljava/lang/String;)Lcom/umeng/message/protobuffer/PushRequest$Builder;
    //   237: iload_2
    //   238: invokestatic 381	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   241: invokevirtual 385	com/umeng/message/protobuffer/PushRequest$Builder:serialNo	(Ljava/lang/Integer;)Lcom/umeng/message/protobuffer/PushRequest$Builder;
    //   244: aload 7
    //   246: invokevirtual 388	com/umeng/message/protobuffer/PushRequest$Builder:signature	(Ljava/lang/String;)Lcom/umeng/message/protobuffer/PushRequest$Builder;
    //   249: iload_3
    //   250: invokestatic 381	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   253: invokevirtual 391	com/umeng/message/protobuffer/PushRequest$Builder:timestamp	(Ljava/lang/Integer;)Lcom/umeng/message/protobuffer/PushRequest$Builder;
    //   256: getstatic 397	com/umeng/message/protobuffer/PushRequest$entityEncodingFormat:JSON_AES	Lcom/umeng/message/protobuffer/PushRequest$entityEncodingFormat;
    //   259: invokevirtual 401	com/umeng/message/protobuffer/PushRequest$Builder:encryption	(Lcom/umeng/message/protobuffer/PushRequest$entityEncodingFormat;)Lcom/umeng/message/protobuffer/PushRequest$Builder;
    //   262: aload 5
    //   264: invokevirtual 405	com/umeng/message/protobuffer/PushRequest$Builder:entity	(Lcom/umeng/message/proguard/aE;)Lcom/umeng/message/protobuffer/PushRequest$Builder;
    //   267: aload 6
    //   269: invokevirtual 408	com/umeng/message/protobuffer/PushRequest$Builder:checksum	(Ljava/lang/String;)Lcom/umeng/message/protobuffer/PushRequest$Builder;
    //   272: aload 4
    //   274: invokevirtual 411	com/umeng/message/protobuffer/PushRequest$Builder:salt	(Ljava/lang/String;)Lcom/umeng/message/protobuffer/PushRequest$Builder;
    //   277: invokevirtual 415	com/umeng/message/protobuffer/PushRequest$Builder:build	()Lcom/umeng/message/protobuffer/PushRequest;
    //   280: invokevirtual 420	com/umeng/message/protobuffer/PushRequest:toByteArray	()[B
    //   283: areturn
    //   284: astore 4
    //   286: aload 4
    //   288: invokevirtual 159	java/lang/Exception:printStackTrace	()V
    //   291: getstatic 29	com/umeng/message/proguard/l:i	Ljava/lang/String;
    //   294: aload 4
    //   296: invokevirtual 421	java/lang/Exception:toString	()Ljava/lang/String;
    //   299: invokestatic 362	com/umeng/common/message/Log:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   302: goto -284 -> 18
    //   305: astore 5
    //   307: aconst_null
    //   308: astore 4
    //   310: aload 5
    //   312: invokevirtual 422	org/json/JSONException:printStackTrace	()V
    //   315: aload 6
    //   317: astore 5
    //   319: goto -230 -> 89
    //   322: astore 5
    //   324: aconst_null
    //   325: astore 4
    //   327: aload 5
    //   329: invokevirtual 159	java/lang/Exception:printStackTrace	()V
    //   332: aload 6
    //   334: astore 5
    //   336: goto -247 -> 89
    //   339: astore_1
    //   340: aload_1
    //   341: invokevirtual 159	java/lang/Exception:printStackTrace	()V
    //   344: getstatic 29	com/umeng/message/proguard/l:i	Ljava/lang/String;
    //   347: aload_1
    //   348: invokevirtual 421	java/lang/Exception:toString	()Ljava/lang/String;
    //   351: invokestatic 362	com/umeng/common/message/Log:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   354: goto -130 -> 224
    //   357: astore 5
    //   359: goto -32 -> 327
    //   362: astore 5
    //   364: goto -54 -> 310
    //
    // Exception table:
    //   from	to	target	type
    //   5	16	284	java/lang/Exception
    //   39	72	305	org/json/JSONException
    //   39	72	322	java/lang/Exception
    //   211	224	339	java/lang/Exception
    //   72	89	357	java/lang/Exception
    //   72	89	362	org/json/JSONException
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.l
 * JD-Core Version:    0.6.2
 */