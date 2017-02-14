package org.android.agoo.net.async;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.umeng.message.proguard.bd;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.util.EntityUtils;

public class AsyncHttpResponseHandler
{
  private static final String a = "AsyncHttpResponseHandler";
  protected static final int c = 0;
  protected static final int d = 1;
  protected static final int e = 2;
  protected static final int f = 3;
  private volatile Handler b = null;

  private final Map<String, String> a(Header[] paramArrayOfHeader)
  {
    HashMap localHashMap = new HashMap();
    int j = paramArrayOfHeader.length;
    int i = 0;
    if (i < j)
    {
      Object localObject = paramArrayOfHeader[i];
      String str = ((Header)localObject).getName();
      if (TextUtils.isEmpty(str));
      while (true)
      {
        i += 1;
        break;
        localObject = ((Header)localObject).getValue();
        if (!TextUtils.isEmpty((CharSequence)localObject))
          localHashMap.put(str.toLowerCase(), localObject);
      }
    }
    return localHashMap;
  }

  private void a(Object paramObject)
  {
    try
    {
      if ((paramObject instanceof a))
      {
        paramObject = (a)paramObject;
        int i = paramObject.a;
        Map localMap = paramObject.c;
        String str = paramObject.d;
        bd.b("AsyncHttpResponseHandler", "onHandleMessage[" + str + "]");
        if (i == 200)
        {
          a(localMap, str);
          return;
        }
        onFailure(new HttpResponseException(i, paramObject.b), localMap, str);
        return;
      }
    }
    catch (Throwable paramObject)
    {
      bd.b("AsyncHttpResponseHandler", "onHandleMessage", paramObject);
      onFailure(paramObject, null, null);
      return;
    }
    bd.b("AsyncHttpResponseHandler", "onHandleMessage", new RuntimeException("!result instanceof IResponse"));
    onFailure(new RuntimeException("!result instanceof IResponse"), null, null);
  }

  Message a(int paramInt, Object paramObject)
  {
    try
    {
      if (this.b != null)
        return this.b.obtainMessage(paramInt, paramObject);
      Message localMessage = new Message();
      try
      {
        localMessage.what = paramInt;
        localMessage.obj = paramObject;
        return localMessage;
      }
      catch (Throwable paramObject)
      {
        return localMessage;
      }
    }
    catch (Throwable paramObject)
    {
    }
    return null;
  }

  final void a()
  {
    b(a(2, null));
  }

  final void a(Message paramMessage)
  {
    try
    {
      switch (paramMessage.what)
      {
      case 0:
        a(paramMessage.obj);
        return;
      case 1:
        b((Throwable)paramMessage.obj);
        return;
      case 2:
        onStart();
        return;
      case 3:
        onFinish();
        return;
      }
      return;
    }
    catch (Throwable paramMessage)
    {
    }
  }

  final void a(Throwable paramThrowable)
  {
    b(a(1, paramThrowable));
  }

  protected void a(Map<String, String> paramMap, String paramString)
  {
    onSuccess(paramMap, paramString);
  }

  final void a(a parama)
  {
    b(a(0, parama));
  }

  final void a(HttpResponse paramHttpResponse)
  {
    try
    {
      StatusLine localStatusLine = paramHttpResponse.getStatusLine();
      a locala = new a(null);
      locala.b = localStatusLine.getReasonPhrase();
      locala.c = a(paramHttpResponse.getAllHeaders());
      locala.a = localStatusLine.getStatusCode();
      paramHttpResponse = paramHttpResponse.getEntity();
      if (paramHttpResponse != null)
        locala.d = EntityUtils.toString(new BufferedHttpEntity(paramHttpResponse), "UTF-8");
      a(locala);
      return;
    }
    catch (Throwable paramHttpResponse)
    {
      a(paramHttpResponse);
    }
  }

  final void b()
  {
    b(a(3, null));
  }

  final void b(Message paramMessage)
  {
    try
    {
      if ((this.b != null) && (!Thread.currentThread().isInterrupted()))
      {
        this.b.sendMessage(paramMessage);
        return;
      }
      a(paramMessage);
      return;
    }
    catch (Throwable paramMessage)
    {
    }
  }

  protected void b(Throwable paramThrowable)
  {
    onFailure(paramThrowable, null, null);
  }

  public void onFailure(Throwable paramThrowable)
  {
  }

  public void onFailure(Throwable paramThrowable, String paramString)
  {
    onFailure(paramThrowable);
  }

  public void onFailure(Throwable paramThrowable, Map<String, String> paramMap, String paramString)
  {
    onFailure(paramThrowable, paramString);
  }

  public final void onFinish()
  {
  }

  public final void onStart()
  {
  }

  public void onSuccess(String paramString)
  {
  }

  public void onSuccess(Map<String, String> paramMap, String paramString)
  {
    onSuccess(paramString);
  }

  private class a
  {
    int a = -1;
    String b = "";
    Map<String, String> c = new HashMap();
    String d = "";

    private a()
    {
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.async.AsyncHttpResponseHandler
 * JD-Core Version:    0.6.2
 */