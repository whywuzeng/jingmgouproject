package org.android.agoo.net.channel.chunked;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.message.proguard.bd;
import com.umeng.message.proguard.bl;
import com.umeng.message.proguard.bm;
import com.umeng.message.proguard.bo;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;
import org.android.agoo.net.channel.ChannelError;
import org.android.agoo.net.channel.ChannelState;
import org.android.agoo.net.channel.IDataChannel;
import org.android.agoo.net.channel.IPullHandler;
import org.android.agoo.net.channel.IPushHandler;
import org.apache.http.HttpException;

abstract class a
  implements IDataChannel
{
  protected static final int g = 8192;
  private static final char h = '\r';
  private static final char i = '\n';
  private static final String j = "UTF-8";
  private static final String k = "HttpChunked";
  private static final char[] v = { ' ' };
  protected volatile ChannelState a = ChannelState.d;
  protected volatile InputStream b = null;
  protected volatile int c = -1;
  protected volatile boolean d = true;
  protected volatile long e = -1L;
  protected volatile Context f;
  private volatile ThreadPoolExecutor l = (ThreadPoolExecutor)Executors.newFixedThreadPool(5);
  private volatile IPushHandler m;
  private volatile Future<?> n = null;
  private volatile Future<?> o = null;
  private volatile String p;
  private volatile int q;
  private volatile String r;
  private volatile int s = -1;
  private volatile Object t = null;
  private AtomicBoolean u = new AtomicBoolean(false);

  private final void a(Context paramContext)
  {
    try
    {
      paramContext = new bl(paramContext);
      if (paramContext.a())
      {
        this.p = paramContext.d();
        this.q = paramContext.e();
        return;
      }
      this.p = null;
      this.q = -1;
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static final char byteToChar(byte[] paramArrayOfByte)
  {
    return (char)((paramArrayOfByte[0] & 0xFF) << 8 | paramArrayOfByte[1] & 0xFF);
  }

  private final void l()
  {
    this.s = -1;
  }

  private final boolean m()
  {
    return (this.a == ChannelState.c) || (this.a == ChannelState.d);
  }

  protected final void a()
  {
    callError(false);
    this.s = new Random().nextInt(10000);
  }

  protected final void a(long paramLong, Map<String, String> paramMap)
  {
    if (this.m != null)
    {
      this.a = ChannelState.b;
      this.m.onConnected(this.t, this.s, paramLong, paramMap, null);
    }
  }

  protected final void a(String paramString)
  {
    this.m.onData(this.t, this.s, this.r, paramString.getBytes(), null);
  }

  protected abstract void a(String paramString, Map<String, String> paramMap);

  protected final void a(ChannelError paramChannelError, Throwable paramThrowable)
  {
    syncDisconnect();
    a(paramChannelError, new HashMap(), paramThrowable);
  }

  protected final void a(ChannelError paramChannelError, Map<String, String> paramMap, Throwable paramThrowable)
  {
    syncDisconnect();
    if (this.m != null)
      this.m.onError(this.t, this.s, paramChannelError, paramMap, paramThrowable, null);
  }

  protected final void a(char[] paramArrayOfChar)
  {
    if ((this.m != null) && (paramArrayOfChar.length == 1))
      this.m.onPing(this.t, 2L);
  }

  public final void asyncDisconnect()
  {
    bd.c("HttpChunked", "http chunked disconnect(" + b() + ")");
    if (m())
    {
      bd.c("HttpChunked", "http chunked connect[" + b() + "] connection has been closed");
      return;
    }
    this.a = ChannelState.c;
    this.l.submit(new Runnable()
    {
      public void run()
      {
        try
        {
          a.this.f();
          a.this.g();
          bd.c("HttpChunked", "http chunked connect[" + a.this.b() + "] connection disconnecting");
          a.this.c();
          bd.c("HttpChunked", "http chunked connect[" + a.this.b() + "] connection disconnected");
          a.this.h();
          return;
        }
        catch (Throwable localThrowable)
        {
        }
      }
    });
    this.a = ChannelState.d;
  }

  protected final int b()
  {
    return this.s;
  }

  protected abstract void c();

  public final void callError(boolean paramBoolean)
  {
    this.u.set(paramBoolean);
  }

  public final void close()
  {
    try
    {
      syncDisconnect();
      bd.c("HttpChunked", "http chunked closing");
      d();
      bd.c("HttpChunked", "http chunked closed");
      l();
      return;
    }
    catch (Throwable localThrowable)
    {
    }
  }

  public final void connect(Object paramObject, Context paramContext, final String paramString1, final Map<String, String> paramMap, final long paramLong, IPushHandler paramIPushHandler, bm parambm, String paramString2)
  {
    this.f = this.f;
    try
    {
      parambm = this.f.getSharedPreferences("AppStore", 4).edit();
      parambm.putString("agoo_connect_type", "httpchunk");
      parambm.commit();
      label47: if (paramIPushHandler == null)
      {
        bd.c("HttpChunked", "eventHandler == null ");
        return;
      }
      if ((this.a == ChannelState.b) || (this.a == ChannelState.a))
      {
        bd.c("HttpChunked", "http chunked connect url: [" + paramString1 + "] connectId:[" + b() + "] connecting......");
        return;
      }
      this.t = paramObject;
      a(paramContext);
      this.m = paramIPushHandler;
      this.a = ChannelState.a;
      this.n = this.l.submit(new Runnable()
      {
        public void run()
        {
          try
          {
            a.this.a();
            a.this.a(paramString1, paramMap);
            return;
          }
          catch (Throwable localThrowable)
          {
          }
        }
      });
      this.o = this.l.submit(new Runnable()
      {
        public void run()
        {
          try
          {
            SystemClock.sleep(paramLong);
            label7: if ((a.this.a == ChannelState.a) && (!a.this.hasCallError()))
            {
              a.this.callError(true);
              a.this.a(ChannelError.r, new HttpException("connectId:[" + a.this.b() + "] http Status code==" + ChannelError.r.getErrorCode()));
              a.a(a.this);
              a.this.g();
            }
            return;
          }
          catch (Throwable localThrowable)
          {
            break label7;
          }
        }
      });
      return;
    }
    catch (Throwable parambm)
    {
      break label47;
    }
  }

  protected abstract void d();

  protected final void e()
  {
    InputStreamReader localInputStreamReader;
    StringBuffer localStringBuffer;
    int i2;
    int i4;
    int i3;
    try
    {
      char[] arrayOfChar = new char[6];
      localInputStreamReader = new InputStreamReader(this.b, "UTF-8");
      localStringBuffer = new StringBuffer(8192);
      i2 = 0;
      while (true)
      {
        i4 = localInputStreamReader.read();
        if (i4 == -1)
          break label347;
        i3 = i4;
        if (i4 != 32)
          break;
        a(v);
      }
    }
    catch (Throwable localThrowable1)
    {
      if (!hasCallError())
      {
        callError(true);
        bd.d("HttpChunked", "Throwable connectId:[" + b() + "]==>", localThrowable1);
        a(ChannelError.s, localThrowable1);
      }
    }
    label127: return;
    int i5;
    while (true)
      if (i3 == 64)
      {
        int i1 = (char)i3;
        localThrowable1[0] = i1;
        i5 = 0;
        i4 = i3;
        label148: i3 = i4;
        if (i5 >= 5)
          continue;
        try
        {
          i3 = localInputStreamReader.read();
          localThrowable1[(i5 + 1)] = ((char)i3);
          if (i5 >= 4)
          {
            i4 = i3;
            a(localThrowable1);
          }
        }
        catch (Throwable localThrowable2)
        {
          bd.d("HttpChunked", "onSysData:[" + b() + "]==>", localThrowable2);
        }
      }
    while (true)
    {
      label228: i3 = i2;
      if (i4 != 10)
      {
        i3 = i2;
        if (i2 == 0)
          break label456;
        localStringBuffer.append('\r');
        i3 = 0;
      }
      while (true)
      {
        label256: i5 = localInputStreamReader.read();
        i2 = i3;
        i4 = i5;
        if (i5 >= 0)
          break label228;
        if (!TextUtils.isEmpty(localStringBuffer.toString()))
        {
          Log.d("HttpChunked", "http msg is comming...........result=" + localStringBuffer.toString());
          a(localStringBuffer.toString());
          localStringBuffer.setLength(0);
          i2 = i3;
          break;
        }
        label347: 
        do
        {
          localStringBuffer.append((char)i4);
          break label256;
          if (this.a != ChannelState.b)
            break label127;
          bd.c("HttpChunked", "connectId:[" + b() + "]==>server data is abort");
          if (!hasCallError())
          {
            callError(true);
            a(ChannelError.s, new IOException("connectId:[" + b() + "] server data is abort"));
          }
          syncDisconnect();
          return;
          i2 = i3;
          break;
        }
        while (i4 != 13);
        label456: i3 = 1;
      }
      i5 += 1;
      i4 = i3;
      break label148;
      i4 = i3;
    }
  }

  protected final void f()
  {
    if (this.o != null)
      this.o.cancel(true);
  }

  protected final void g()
  {
    if (this.n != null)
      this.n.cancel(true);
  }

  protected final void h()
  {
    if ((this.m == null) && (m()));
    while (this.a != ChannelState.b)
      return;
    this.m.onDisconnected(this.t, this.s, null);
  }

  public final boolean hasCallError()
  {
    return this.u.get();
  }

  protected final String i()
  {
    return this.p;
  }

  protected final int j()
  {
    return this.q;
  }

  protected final boolean k()
  {
    return (this.p != null) && (this.q != -1);
  }

  @Deprecated
  public final long ping()
  {
    return -1L;
  }

  public final ChannelState readyChannelState()
  {
    return this.a;
  }

  @Deprecated
  public final int send(String paramString, byte[] paramArrayOfByte, IPullHandler paramIPullHandler, bo parambo)
  {
    return -1;
  }

  public final void shutdown()
  {
    try
    {
      this.l.submit(new Runnable()
      {
        public void run()
        {
          a.this.close();
        }
      });
      if ((this.l != null) && (this.l.isShutdown()))
        this.l.shutdownNow();
      return;
    }
    catch (Throwable localThrowable)
    {
    }
  }

  @Deprecated
  public final void syncDisconnect()
  {
    bd.c("HttpChunked", "http chunked disconnect(" + b() + ")");
    if (m())
    {
      bd.c("HttpChunked", "http chunked connect[" + b() + "] connection has been closed");
      return;
    }
    this.a = ChannelState.c;
    try
    {
      f();
      g();
      bd.c("HttpChunked", "http chunked connect[" + b() + "] connection disconnecting");
      c();
      bd.c("HttpChunked", "http chunked connect[" + b() + "] connection disconnected");
      h();
      label161: this.a = ChannelState.d;
      return;
    }
    catch (Throwable localThrowable)
    {
      break label161;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.channel.chunked.a
 * JD-Core Version:    0.6.2
 */