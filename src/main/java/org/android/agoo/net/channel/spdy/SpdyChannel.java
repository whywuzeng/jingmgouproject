package org.android.agoo.net.channel.spdy;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.message.proguard.bG;
import com.umeng.message.proguard.bd;
import com.umeng.message.proguard.bm;
import com.umeng.message.proguard.bo;
import com.umeng.message.proguard.bw;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.android.agoo.net.channel.ChannelError;
import org.android.agoo.net.channel.ChannelState;
import org.android.agoo.net.channel.IDataChannel;
import org.android.agoo.net.channel.IPullHandler;
import org.android.agoo.net.channel.IPushHandler;
import org.android.spdy.RequestPriority;
import org.android.spdy.SessionCb;
import org.android.spdy.SpdyAgent;
import org.android.spdy.SpdyDataProvider;
import org.android.spdy.SpdyRequest;
import org.android.spdy.SpdySession;
import org.android.spdy.SpdySessionKind;
import org.android.spdy.SpdyVersion;
import org.android.spdy.Spdycb;
import org.android.spdy.SuperviseConnectInfo;
import org.android.spdy.SuperviseData;

public class SpdyChannel
  implements IDataChannel, Spdycb
{
  public static final String b = "agoo_push_errorid";
  public static final String c = "agoo_push_path";
  public static final String d = "agoo_connect_type";
  private static final String h = "SpdyClient";
  private static final String i = ":status";
  protected volatile ChannelState a = ChannelState.d;
  protected volatile Context e;
  protected volatile bm f = null;
  protected volatile bo g = null;
  private volatile SpdyAgent j = null;
  private volatile SpdySession k = null;
  private volatile String l;
  private volatile IPushHandler m;
  private volatile Map<String, a> n = new HashMap();
  private volatile Map<String, WeakReference<IPullHandler>> o = new HashMap();
  private AtomicBoolean p;
  private volatile long q = -1L;
  private volatile URL r;
  private volatile Object s = null;
  private volatile long t = -1L;
  private volatile long u = -1L;
  private final SessionCb v = new SessionCb()
  {
    public void spdyCustomControlFrameRecvCallback(SpdySession paramAnonymousSpdySession, Object paramAnonymousObject, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4, byte[] paramAnonymousArrayOfByte)
    {
    }

    public void spdyPingRecvCallback(SpdySession paramAnonymousSpdySession, long paramAnonymousLong, Object paramAnonymousObject)
    {
      try
      {
        bd.c("SpdyClient", "spdyPingRecvCallback[" + paramAnonymousLong + "]");
        if (SpdyChannel.c(SpdyChannel.this) == paramAnonymousLong)
          return;
        SpdyChannel.b(SpdyChannel.this, paramAnonymousLong);
        SpdyChannel.e(SpdyChannel.this).onPing(SpdyChannel.d(SpdyChannel.this), paramAnonymousLong);
        return;
      }
      catch (Throwable paramAnonymousSpdySession)
      {
        SpdyChannel.this.f.f(Integer.toString(ChannelError.e.getErrorCode()));
        SpdyChannel.this.f.i(bw.a(System.currentTimeMillis()));
        SpdyChannel.this.f.j("2");
        SpdyChannel.a(SpdyChannel.this, ChannelError.e, new HashMap(), null, SpdyChannel.this.f);
      }
    }

    public void spdySessionCloseCallback(SpdySession paramAnonymousSpdySession, Object paramAnonymousObject, SuperviseConnectInfo paramAnonymousSuperviseConnectInfo, int paramAnonymousInt)
    {
      if (TextUtils.equals(SpdyChannel.a(SpdyChannel.this), (String)paramAnonymousObject))
      {
        SpdyChannel.this.f.f("spdySessionCloseCallback");
        SpdyChannel.this.f.k(bw.a(System.currentTimeMillis()));
        SpdyChannel.this.f.j("2");
        SpdyChannel.this.a = ChannelState.c;
      }
      try
      {
        SpdyChannel.e(SpdyChannel.this).onDisconnected(SpdyChannel.d(SpdyChannel.this), SpdyChannel.f(SpdyChannel.this), null);
        label94: SpdyChannel.this.a = ChannelState.d;
        return;
      }
      catch (Throwable paramAnonymousSpdySession)
      {
        break label94;
      }
    }

    public void spdySessionConnectCB(SpdySession paramAnonymousSpdySession, SuperviseConnectInfo paramAnonymousSuperviseConnectInfo)
    {
      SpdyChannel.a(SpdyChannel.this, paramAnonymousSuperviseConnectInfo.connectTime);
      bd.c("SpdyClient", "connect connect_time[" + paramAnonymousSuperviseConnectInfo.connectTime + "] ");
    }

    public void spdySessionFailedError(SpdySession paramAnonymousSpdySession, int paramAnonymousInt, Object paramAnonymousObject)
    {
      paramAnonymousSpdySession = (String)paramAnonymousObject;
      if (TextUtils.equals(SpdyChannel.a(SpdyChannel.this), paramAnonymousSpdySession))
      {
        bd.c("SpdyClient", "spdySessionFailedError[" + paramAnonymousInt + "][" + paramAnonymousObject + "]");
        SpdyChannel.this.a = ChannelState.c;
        SpdyChannel.b(SpdyChannel.this);
      }
      try
      {
        SpdyChannel.a(SpdyChannel.this, SpdyChannel.this.e, Integer.toString(paramAnonymousInt), paramAnonymousSpdySession);
        SpdyChannel.this.f.f(Integer.toString(paramAnonymousInt));
        SpdyChannel.this.f.j("2");
        SpdyChannel.this.f.k(bw.a(System.currentTimeMillis()));
        SpdyChannel.a(SpdyChannel.this, ChannelError.getEasySpdy(paramAnonymousInt), new HashMap(), null, SpdyChannel.this.f);
        label161: SpdyChannel.this.a = ChannelState.d;
        return;
      }
      catch (Throwable paramAnonymousSpdySession)
      {
        break label161;
      }
    }
  };

  public SpdyChannel()
  {
    try
    {
      this.p = new AtomicBoolean(false);
      this.j = SpdyAgent.getInstance(this.e, SpdyVersion.SPDY3, SpdySessionKind.NONE_SESSION);
      return;
    }
    catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
    {
      this.f.f(Integer.toString(ChannelError.g.getErrorCode()));
      this.f.i(bw.a(System.currentTimeMillis()));
      a(ChannelError.g, new HashMap(), localUnsatisfiedLinkError, this.f);
      return;
    }
    catch (Throwable localThrowable)
    {
      this.f.f(Integer.toString(ChannelError.f.getErrorCode()));
      this.f.i(bw.a(System.currentTimeMillis()));
      a(ChannelError.f, new HashMap(), localThrowable, this.f);
    }
  }

  private final String a(List<String> paramList)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i2 = paramList.size();
    int i1 = 0;
    while (i1 < i2)
    {
      localStringBuffer.append((String)paramList.get(i1));
      if (i1 < i2 - 1)
        localStringBuffer.append(",");
      i1 += 1;
    }
    return localStringBuffer.toString();
  }

  private final Map<String, String> a(Map<String, List<String>> paramMap)
  {
    HashMap localHashMap = new HashMap();
    try
    {
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        paramMap = (Map.Entry)localIterator.next();
        String str1 = (String)paramMap.getKey();
        if (!TextUtils.isEmpty(str1))
        {
          String str2 = a((List)paramMap.getValue());
          if (!TextUtils.isEmpty(str2))
          {
            paramMap = str1;
            if (!str1.startsWith(":"))
              paramMap = str1.toLowerCase();
            localHashMap.put(paramMap, str2);
          }
        }
      }
    }
    catch (Throwable paramMap)
    {
    }
    return localHashMap;
  }

  private final void a()
  {
    if (this.k != null);
    try
    {
      bd.d("SpdyClient", "session.streamReset(" + this.t + ")");
      this.k.streamReset(this.t, -2014);
      try
      {
        label56: bd.d("SpdyClient", "session.close()");
        this.k.closeSession();
        this.k = null;
        return;
      }
      catch (Throwable localThrowable1)
      {
        while (true)
          bd.d("SpdyClient", "disconnect", localThrowable1);
      }
    }
    catch (Throwable localThrowable2)
    {
      break label56;
    }
  }

  private final void a(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramContext = paramContext.getSharedPreferences("AppStore", 4).edit();
      paramContext.putString("agoo_push_errorid", paramString1);
      paramContext.putString("agoo_push_path", paramString2);
      paramContext.putString("agoo_connect_type", "spdy");
      paramContext.commit();
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  private final void a(String paramString, Map<String, String> paramMap)
    throws Throwable
  {
    if (!TextUtils.isEmpty(paramString))
      bd.c("SpdyClient", "connect url[" + paramString + "]");
    if (paramMap != null)
    {
      paramString = paramMap.entrySet().iterator();
      while (paramString.hasNext())
      {
        Object localObject = (Map.Entry)paramString.next();
        if (localObject != null)
        {
          paramMap = (String)((Map.Entry)localObject).getKey();
          localObject = (String)((Map.Entry)localObject).getValue();
          if ((!TextUtils.isEmpty(paramMap)) && (!TextUtils.isEmpty((CharSequence)localObject)))
            bd.c("SpdyClient", "header--->[" + paramMap + ":" + (String)localObject + "]");
        }
      }
    }
  }

  private final void a(ChannelError paramChannelError, Map<String, String> paramMap, Throwable paramThrowable, bm parambm)
  {
    if ((this.m != null) && (hasCallError()))
    {
      callError(false);
      this.a = ChannelState.d;
      this.m.onError(this.s, this.t, paramChannelError, paramMap, paramThrowable, parambm);
    }
  }

  public final void asyncDisconnect()
  {
    this.a = ChannelState.c;
    a();
    callError(false);
    this.a = ChannelState.d;
  }

  public final void callError(boolean paramBoolean)
  {
    this.p.set(paramBoolean);
  }

  public final void close()
  {
    try
    {
      if (this.j != null)
      {
        bd.d("SpdyClient", "closing");
        a();
        this.j.close();
        this.j = null;
        bd.d("SpdyClient", "closed");
      }
      return;
    }
    catch (Throwable localThrowable)
    {
    }
  }

  public final void connect(Object paramObject, Context paramContext, String paramString1, Map<String, String> paramMap, long paramLong, IPushHandler paramIPushHandler, bm parambm, String paramString2)
  {
    this.f = parambm;
    if ((paramObject == null) || (TextUtils.isEmpty(paramString1)) || (paramIPushHandler == null))
      throw new NullPointerException("connectContext==null||url==null || eventHandler==null");
    this.e = paramContext;
    this.s = paramObject;
    callError(true);
    this.m = paramIPushHandler;
    try
    {
      paramObject = this.e.getSharedPreferences("AppStore", 4).edit();
      paramObject.putString("agoo_connect_type", "spdy");
      paramObject.commit();
      try
      {
        label90: a(paramString1, paramMap);
        this.a = ChannelState.a;
        if (this.j != null)
        {
          this.l = paramString1;
          this.r = new URL(paramString1);
          paramObject = new SpdyRequest(this.r, "GET", RequestPriority.DEFAULT_PRIORITY);
          if ((paramMap != null) && (paramMap.size() > 0))
            paramObject.addHeaders(paramMap);
          paramContext = new SpdyDataProvider((byte[])null);
          this.k = this.j.submitRequest(paramObject, paramContext, this.l, this.l, this, this.v, 2);
          this.f.g(paramString2 + "-" + System.currentTimeMillis());
        }
        return;
      }
      catch (UnsatisfiedLinkError paramObject)
      {
        this.f.f(Integer.toString(ChannelError.g.getErrorCode()));
        this.f.j("1");
        a(ChannelError.g, new HashMap(), paramObject, this.f);
        return;
      }
      catch (Throwable paramObject)
      {
        this.f.f(Integer.toString(ChannelError.a.getErrorCode()));
        this.f.j("1");
        a(ChannelError.a, new HashMap(), paramObject, this.f);
        return;
      }
    }
    catch (Throwable paramObject)
    {
      break label90;
    }
  }

  public final boolean hasCallError()
  {
    return this.p.get();
  }

  public final boolean hasPushSteam(String paramString)
  {
    return TextUtils.equals(this.l, paramString);
  }

  public final long ping()
  {
    int i2 = -1;
    int i1 = i2;
    try
    {
      if (this.k != null)
        i1 = this.k.submitPing();
      return i1;
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        this.f.f(Integer.toString(ChannelError.e.getErrorCode()));
        this.f.i(bw.a(System.currentTimeMillis()));
        this.f.j("1");
        a(ChannelError.e, new HashMap(), localThrowable, this.f);
        i1 = i2;
      }
    }
  }

  public final ChannelState readyChannelState()
  {
    return this.a;
  }

  public final int send(String paramString, byte[] paramArrayOfByte, IPullHandler paramIPullHandler, bo parambo)
  {
    int i2 = 0;
    Object localObject = null;
    int i3 = -1;
    if (parambo != null);
    try
    {
      this.g = parambo;
      int i1 = i3;
      if (this.a == ChannelState.b)
      {
        i1 = i3;
        if (this.k != null)
        {
          i1 = i3;
          if (this.r != null)
          {
            i1 = i3;
            if (!TextUtils.isEmpty(paramString))
            {
              String str = String.format("http://%s:%d%s", new Object[] { this.r.getHost(), Integer.valueOf(this.r.getPort()), paramString });
              bd.c("SpdyClient", "send[baseUrl:" + str + "]");
              parambo = new SpdyRequest(new URL(str), "POST", RequestPriority.DEFAULT_PRIORITY);
              i1 = i2;
              paramString = localObject;
              if (paramArrayOfByte != null)
              {
                i1 = i2;
                paramString = localObject;
                if (paramArrayOfByte.length > 0)
                {
                  paramString = new SpdyDataProvider(paramArrayOfByte);
                  i1 = Arrays.hashCode(paramArrayOfByte);
                }
              }
              paramArrayOfByte = String.format("%s_%d", new Object[] { str, Integer.valueOf(i1) });
              if (paramIPullHandler != null)
                this.o.put(paramArrayOfByte, new WeakReference(paramIPullHandler));
              i1 = this.k.submitRequest(parambo, paramString, paramArrayOfByte, this);
            }
          }
        }
      }
      return i1;
    }
    catch (Throwable paramString)
    {
    }
    return -1;
  }

  public final void shutdown()
  {
    bd.d("SpdyClient", "shutdown.....");
    bG.a(new Runnable()
    {
      public void run()
      {
        bd.d("SpdyClient", "shutdown");
        SpdyChannel.this.close();
      }
    });
  }

  public final void spdyDataChunkRecvCB(SpdySession paramSpdySession, boolean paramBoolean, long paramLong, byte[] paramArrayOfByte, int paramInt, Object paramObject)
  {
    try
    {
      paramSpdySession = (String)paramObject;
      if (TextUtils.isEmpty(paramSpdySession))
        return;
      if (paramArrayOfByte == null)
        Log.d("SpdyClient", "spdyDataChunkRecvCB,data=null,streamId=" + paramLong);
      Log.d("SpdyClient", "spdyDataChunkRecvCB,data=" + paramArrayOfByte);
      if (TextUtils.equals(paramSpdySession, this.l))
      {
        if (!hasCallError())
          return;
        this.m.onData(this.s, paramLong, paramSpdySession, paramArrayOfByte, this.g);
        return;
      }
    }
    catch (Throwable paramSpdySession)
    {
      a(ChannelError.c, new HashMap(), paramSpdySession, this.f);
      return;
    }
    paramObject = (a)this.n.get(paramSpdySession);
    if (paramObject != null)
    {
      paramObject.a(paramArrayOfByte);
      this.n.put(paramSpdySession, paramObject);
    }
  }

  public final void spdyDataRecvCallback(SpdySession paramSpdySession, boolean paramBoolean, long paramLong, int paramInt, Object paramObject)
  {
    try
    {
      paramSpdySession = (String)paramObject;
      if (TextUtils.isEmpty(paramSpdySession))
        return;
      paramObject = (WeakReference)this.o.get(paramSpdySession);
      if (paramObject != null)
      {
        paramObject = (IPullHandler)paramObject.get();
        if (paramObject != null)
        {
          a locala = (a)this.n.get(paramSpdySession);
          if (locala != null)
          {
            paramObject.onResponse(this.s, paramSpdySession, locala.b(), locala.c(), locala.a());
            this.o.remove(paramSpdySession);
            this.n.remove(paramSpdySession);
            return;
          }
        }
      }
    }
    catch (Throwable paramSpdySession)
    {
      bd.e("SpdyClient", "spdyDataRecvCallback", paramSpdySession);
    }
  }

  public final void spdyDataSendCallback(SpdySession paramSpdySession, boolean paramBoolean, long paramLong, int paramInt, Object paramObject)
  {
  }

  public final void spdyOnStreamResponse(SpdySession paramSpdySession, long paramLong, Map<String, List<String>> paramMap, Object paramObject)
  {
    Map localMap = a(paramMap);
    while (true)
    {
      try
      {
        paramObject = (String)paramObject;
        if (localMap.get(":status") == null)
          break label372;
        i1 = Integer.parseInt((String)localMap.get(":status"));
        bd.c("SpdyClient", "spdyOnStreamResponse PATH[" + paramObject + "][" + i1 + "]");
        if (hasPushSteam(paramObject))
        {
          this.t = paramLong;
          if (i1 == 200)
          {
            this.a = ChannelState.b;
            this.f.e("y");
            this.f.i(bw.a(System.currentTimeMillis()));
            this.m.onConnected(this.s, paramLong, this.q, localMap, this.f);
            paramMap.remove(":status");
            return;
          }
          this.f.e("n");
          this.f.f(Integer.toString(i1));
          this.f.i(bw.a(System.currentTimeMillis()));
          a(ChannelError.get(i1), localMap, new Throwable("http httpStatusCode[" + paramObject + "]==" + i1), this.f);
          continue;
        }
      }
      catch (Throwable paramSpdySession)
      {
        this.f.e("n");
        this.f.f(Integer.toString(ChannelError.b.getErrorCode()));
        this.f.i(bw.a(System.currentTimeMillis()));
        a(ChannelError.b, localMap, paramSpdySession, this.f);
        return;
      }
      paramMap = (a)this.n.get(paramObject);
      paramSpdySession = paramMap;
      if (paramMap == null)
        paramSpdySession = new a(i1, localMap);
      this.n.put(paramObject, paramSpdySession);
      return;
      label372: int i1 = -1;
    }
  }

  @Deprecated
  public final void spdyRequestRecvCallback(SpdySession paramSpdySession, long paramLong, Object paramObject)
  {
  }

  public void spdyStreamCloseCallback(SpdySession paramSpdySession, long paramLong, int paramInt, Object paramObject, SuperviseData paramSuperviseData)
  {
  }

  @Deprecated
  public final void syncDisconnect()
  {
    this.a = ChannelState.c;
    a();
    callError(false);
    this.a = ChannelState.d;
  }

  class a
  {
    private int b;
    private Map<String, String> c;
    private ByteArrayOutputStream d = null;

    public a(Map<String, String> arg2)
    {
      int i;
      this.b = i;
      Object localObject;
      this.c = localObject;
      this.d = new ByteArrayOutputStream();
    }

    public void a(byte[] paramArrayOfByte)
      throws IOException
    {
      this.d.write(paramArrayOfByte);
    }

    public byte[] a()
    {
      try
      {
        byte[] arrayOfByte = this.d.toByteArray();
        return arrayOfByte;
      }
      catch (Throwable localThrowable)
      {
      }
      return null;
    }

    public int b()
    {
      return this.b;
    }

    public Map<String, String> c()
    {
      return this.c;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.channel.spdy.SpdyChannel
 * JD-Core Version:    0.6.2
 */