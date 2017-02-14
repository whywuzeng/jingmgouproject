package com.squareup.okhttp.internal;

import com.squareup.okhttp.Protocol;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import okio.Buffer;

public class Platform
{
  private static final Platform PLATFORM = findPlatform();

  static byte[] concatLengthPrefixed(List<Protocol> paramList)
  {
    Buffer localBuffer = new Buffer();
    int i = 0;
    int j = paramList.size();
    if (i < j)
    {
      Protocol localProtocol = (Protocol)paramList.get(i);
      if (localProtocol == Protocol.HTTP_1_0);
      while (true)
      {
        i += 1;
        break;
        localBuffer.writeByte(localProtocol.toString().length());
        localBuffer.writeUtf8(localProtocol.toString());
      }
    }
    return localBuffer.readByteArray();
  }

  // ERROR //
  private static Platform findPlatform()
  {
    // Byte code:
    //   0: ldc 77
    //   2: invokestatic 83	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   5: pop
    //   6: new 85	com/squareup/okhttp/internal/OptionalMethod
    //   9: dup
    //   10: aconst_null
    //   11: ldc 87
    //   13: iconst_1
    //   14: anewarray 79	java/lang/Class
    //   17: dup
    //   18: iconst_0
    //   19: getstatic 93	java/lang/Boolean:TYPE	Ljava/lang/Class;
    //   22: aastore
    //   23: invokespecial 96	com/squareup/okhttp/internal/OptionalMethod:<init>	(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;)V
    //   26: astore 9
    //   28: new 85	com/squareup/okhttp/internal/OptionalMethod
    //   31: dup
    //   32: aconst_null
    //   33: ldc 98
    //   35: iconst_1
    //   36: anewarray 79	java/lang/Class
    //   39: dup
    //   40: iconst_0
    //   41: ldc 54
    //   43: aastore
    //   44: invokespecial 96	com/squareup/okhttp/internal/OptionalMethod:<init>	(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;)V
    //   47: astore 10
    //   49: aconst_null
    //   50: astore 8
    //   52: aconst_null
    //   53: astore_1
    //   54: aconst_null
    //   55: astore 7
    //   57: aconst_null
    //   58: astore 4
    //   60: aconst_null
    //   61: astore 6
    //   63: aconst_null
    //   64: astore 5
    //   66: aload_1
    //   67: astore_3
    //   68: aload 7
    //   70: astore_0
    //   71: aload 8
    //   73: astore_2
    //   74: ldc 100
    //   76: invokestatic 83	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   79: astore 11
    //   81: aload_1
    //   82: astore_3
    //   83: aload 7
    //   85: astore_0
    //   86: aload 8
    //   88: astore_2
    //   89: aload 11
    //   91: ldc 102
    //   93: iconst_1
    //   94: anewarray 79	java/lang/Class
    //   97: dup
    //   98: iconst_0
    //   99: ldc 104
    //   101: aastore
    //   102: invokevirtual 108	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   105: astore_1
    //   106: aload_1
    //   107: astore_3
    //   108: aload 7
    //   110: astore_0
    //   111: aload_1
    //   112: astore_2
    //   113: aload 11
    //   115: ldc 110
    //   117: iconst_1
    //   118: anewarray 79	java/lang/Class
    //   121: dup
    //   122: iconst_0
    //   123: ldc 104
    //   125: aastore
    //   126: invokevirtual 108	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   129: astore 7
    //   131: aload 7
    //   133: astore_2
    //   134: aload_1
    //   135: astore_3
    //   136: aload_2
    //   137: astore_0
    //   138: ldc 112
    //   140: invokestatic 83	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   143: pop
    //   144: aload_1
    //   145: astore_3
    //   146: aload_2
    //   147: astore_0
    //   148: new 85	com/squareup/okhttp/internal/OptionalMethod
    //   151: dup
    //   152: ldc 114
    //   154: ldc 116
    //   156: iconst_0
    //   157: anewarray 79	java/lang/Class
    //   160: invokespecial 96	com/squareup/okhttp/internal/OptionalMethod:<init>	(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;)V
    //   163: astore 4
    //   165: new 85	com/squareup/okhttp/internal/OptionalMethod
    //   168: dup
    //   169: aconst_null
    //   170: ldc 118
    //   172: iconst_1
    //   173: anewarray 79	java/lang/Class
    //   176: dup
    //   177: iconst_0
    //   178: ldc 114
    //   180: aastore
    //   181: invokespecial 96	com/squareup/okhttp/internal/OptionalMethod:<init>	(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;)V
    //   184: astore_3
    //   185: aload 4
    //   187: astore_0
    //   188: new 6	com/squareup/okhttp/internal/Platform$Android
    //   191: dup
    //   192: aload 9
    //   194: aload 10
    //   196: aload_1
    //   197: aload_2
    //   198: aload_0
    //   199: aload_3
    //   200: invokespecial 121	com/squareup/okhttp/internal/Platform$Android:<init>	(Lcom/squareup/okhttp/internal/OptionalMethod;Lcom/squareup/okhttp/internal/OptionalMethod;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Lcom/squareup/okhttp/internal/OptionalMethod;Lcom/squareup/okhttp/internal/OptionalMethod;)V
    //   203: areturn
    //   204: astore_0
    //   205: ldc 123
    //   207: invokestatic 83	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   210: pop
    //   211: goto -205 -> 6
    //   214: astore_0
    //   215: ldc 125
    //   217: invokestatic 83	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   220: astore_0
    //   221: new 127	java/lang/StringBuilder
    //   224: dup
    //   225: invokespecial 128	java/lang/StringBuilder:<init>	()V
    //   228: ldc 125
    //   230: invokevirtual 132	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   233: ldc 134
    //   235: invokevirtual 132	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   238: invokevirtual 135	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   241: invokestatic 83	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   244: astore_1
    //   245: new 127	java/lang/StringBuilder
    //   248: dup
    //   249: invokespecial 128	java/lang/StringBuilder:<init>	()V
    //   252: ldc 125
    //   254: invokevirtual 132	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   257: ldc 137
    //   259: invokevirtual 132	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   262: invokevirtual 135	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   265: invokestatic 83	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   268: astore_2
    //   269: new 127	java/lang/StringBuilder
    //   272: dup
    //   273: invokespecial 128	java/lang/StringBuilder:<init>	()V
    //   276: ldc 125
    //   278: invokevirtual 132	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   281: ldc 139
    //   283: invokevirtual 132	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   286: invokevirtual 135	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   289: invokestatic 83	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   292: astore_3
    //   293: new 9	com/squareup/okhttp/internal/Platform$JdkWithJettyBootPlatform
    //   296: dup
    //   297: aload_0
    //   298: ldc 141
    //   300: iconst_2
    //   301: anewarray 79	java/lang/Class
    //   304: dup
    //   305: iconst_0
    //   306: ldc 143
    //   308: aastore
    //   309: dup
    //   310: iconst_1
    //   311: aload_1
    //   312: aastore
    //   313: invokevirtual 108	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   316: aload_0
    //   317: ldc 144
    //   319: iconst_1
    //   320: anewarray 79	java/lang/Class
    //   323: dup
    //   324: iconst_0
    //   325: ldc 143
    //   327: aastore
    //   328: invokevirtual 108	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   331: aload_0
    //   332: ldc 146
    //   334: iconst_1
    //   335: anewarray 79	java/lang/Class
    //   338: dup
    //   339: iconst_0
    //   340: ldc 143
    //   342: aastore
    //   343: invokevirtual 108	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   346: aload_2
    //   347: aload_3
    //   348: invokespecial 149	com/squareup/okhttp/internal/Platform$JdkWithJettyBootPlatform:<init>	(Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/Class;Ljava/lang/Class;)V
    //   351: astore_0
    //   352: aload_0
    //   353: areturn
    //   354: astore_0
    //   355: new 2	com/squareup/okhttp/internal/Platform
    //   358: dup
    //   359: invokespecial 150	com/squareup/okhttp/internal/Platform:<init>	()V
    //   362: areturn
    //   363: astore_0
    //   364: goto -9 -> 355
    //   367: astore_1
    //   368: aload_3
    //   369: astore_1
    //   370: aload_0
    //   371: astore_2
    //   372: aload 6
    //   374: astore_0
    //   375: aload 5
    //   377: astore_3
    //   378: goto -190 -> 188
    //   381: astore_0
    //   382: aload 4
    //   384: astore_0
    //   385: aload 5
    //   387: astore_3
    //   388: goto -200 -> 188
    //   391: astore_0
    //   392: aload_2
    //   393: astore_1
    //   394: aload 4
    //   396: astore_2
    //   397: aload 6
    //   399: astore_0
    //   400: aload 5
    //   402: astore_3
    //   403: goto -215 -> 188
    //   406: astore_0
    //   407: aload 6
    //   409: astore_0
    //   410: aload 5
    //   412: astore_3
    //   413: goto -225 -> 188
    //   416: astore_0
    //   417: aload 4
    //   419: astore_0
    //   420: aload 5
    //   422: astore_3
    //   423: goto -235 -> 188
    //
    // Exception table:
    //   from	to	target	type
    //   0	6	204	java/lang/ClassNotFoundException
    //   6	49	214	java/lang/ClassNotFoundException
    //   188	204	214	java/lang/ClassNotFoundException
    //   205	211	214	java/lang/ClassNotFoundException
    //   215	352	354	java/lang/ClassNotFoundException
    //   215	352	363	java/lang/NoSuchMethodException
    //   74	81	367	java/lang/NoSuchMethodException
    //   89	106	367	java/lang/NoSuchMethodException
    //   113	131	367	java/lang/NoSuchMethodException
    //   138	144	367	java/lang/NoSuchMethodException
    //   148	165	367	java/lang/NoSuchMethodException
    //   165	185	381	java/lang/NoSuchMethodException
    //   74	81	391	java/lang/ClassNotFoundException
    //   89	106	391	java/lang/ClassNotFoundException
    //   113	131	391	java/lang/ClassNotFoundException
    //   138	144	406	java/lang/ClassNotFoundException
    //   148	165	406	java/lang/ClassNotFoundException
    //   165	185	416	java/lang/ClassNotFoundException
  }

  public static Platform get()
  {
    return PLATFORM;
  }

  public void afterHandshake(SSLSocket paramSSLSocket)
  {
  }

  public void configureTlsExtensions(SSLSocket paramSSLSocket, String paramString, List<Protocol> paramList)
  {
  }

  public void connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress, int paramInt)
    throws IOException
  {
    paramSocket.connect(paramInetSocketAddress, paramInt);
  }

  public String getPrefix()
  {
    return "OkHttp";
  }

  public String getSelectedProtocol(SSLSocket paramSSLSocket)
  {
    return null;
  }

  public void logW(String paramString)
  {
    System.out.println(paramString);
  }

  public void tagSocket(Socket paramSocket)
    throws SocketException
  {
  }

  public void untagSocket(Socket paramSocket)
    throws SocketException
  {
  }

  private static class Android extends Platform
  {
    private final OptionalMethod<Socket> getAlpnSelectedProtocol;
    private final OptionalMethod<Socket> setAlpnProtocols;
    private final OptionalMethod<Socket> setHostname;
    private final OptionalMethod<Socket> setUseSessionTickets;
    private final Method trafficStatsTagSocket;
    private final Method trafficStatsUntagSocket;

    public Android(OptionalMethod<Socket> paramOptionalMethod1, OptionalMethod<Socket> paramOptionalMethod2, Method paramMethod1, Method paramMethod2, OptionalMethod<Socket> paramOptionalMethod3, OptionalMethod<Socket> paramOptionalMethod4)
    {
      this.setUseSessionTickets = paramOptionalMethod1;
      this.setHostname = paramOptionalMethod2;
      this.trafficStatsTagSocket = paramMethod1;
      this.trafficStatsUntagSocket = paramMethod2;
      this.getAlpnSelectedProtocol = paramOptionalMethod3;
      this.setAlpnProtocols = paramOptionalMethod4;
    }

    public void configureTlsExtensions(SSLSocket paramSSLSocket, String paramString, List<Protocol> paramList)
    {
      if (paramString != null)
      {
        this.setUseSessionTickets.invokeOptionalWithoutCheckedException(paramSSLSocket, new Object[] { Boolean.valueOf(true) });
        this.setHostname.invokeOptionalWithoutCheckedException(paramSSLSocket, new Object[] { paramString });
      }
      if ((this.setAlpnProtocols != null) && (this.setAlpnProtocols.isSupported(paramSSLSocket)))
      {
        paramString = concatLengthPrefixed(paramList);
        this.setAlpnProtocols.invokeWithoutCheckedException(paramSSLSocket, new Object[] { paramString });
      }
    }

    public void connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress, int paramInt)
      throws IOException
    {
      try
      {
        paramSocket.connect(paramInetSocketAddress, paramInt);
        return;
      }
      catch (SecurityException paramSocket)
      {
        paramInetSocketAddress = new IOException("Exception in connect");
        paramInetSocketAddress.initCause(paramSocket);
      }
      throw paramInetSocketAddress;
    }

    public String getSelectedProtocol(SSLSocket paramSSLSocket)
    {
      if (this.getAlpnSelectedProtocol == null);
      while (!this.getAlpnSelectedProtocol.isSupported(paramSSLSocket))
        return null;
      paramSSLSocket = (byte[])this.getAlpnSelectedProtocol.invokeWithoutCheckedException(paramSSLSocket, new Object[0]);
      if (paramSSLSocket != null);
      for (paramSSLSocket = new String(paramSSLSocket, Util.UTF_8); ; paramSSLSocket = null)
        return paramSSLSocket;
    }

    public void tagSocket(Socket paramSocket)
      throws SocketException
    {
      if (this.trafficStatsTagSocket == null)
        return;
      try
      {
        this.trafficStatsTagSocket.invoke(null, new Object[] { paramSocket });
        return;
      }
      catch (IllegalAccessException paramSocket)
      {
        throw new RuntimeException(paramSocket);
      }
      catch (InvocationTargetException paramSocket)
      {
      }
      throw new RuntimeException(paramSocket.getCause());
    }

    public void untagSocket(Socket paramSocket)
      throws SocketException
    {
      if (this.trafficStatsUntagSocket == null)
        return;
      try
      {
        this.trafficStatsUntagSocket.invoke(null, new Object[] { paramSocket });
        return;
      }
      catch (IllegalAccessException paramSocket)
      {
        throw new RuntimeException(paramSocket);
      }
      catch (InvocationTargetException paramSocket)
      {
      }
      throw new RuntimeException(paramSocket.getCause());
    }
  }

  private static class JdkWithJettyBootPlatform extends Platform
  {
    private final Class<?> clientProviderClass;
    private final Method getMethod;
    private final Method putMethod;
    private final Method removeMethod;
    private final Class<?> serverProviderClass;

    public JdkWithJettyBootPlatform(Method paramMethod1, Method paramMethod2, Method paramMethod3, Class<?> paramClass1, Class<?> paramClass2)
    {
      this.putMethod = paramMethod1;
      this.getMethod = paramMethod2;
      this.removeMethod = paramMethod3;
      this.clientProviderClass = paramClass1;
      this.serverProviderClass = paramClass2;
    }

    public void afterHandshake(SSLSocket paramSSLSocket)
    {
      try
      {
        this.removeMethod.invoke(null, new Object[] { paramSSLSocket });
        return;
      }
      catch (IllegalAccessException paramSSLSocket)
      {
        throw new AssertionError();
      }
      catch (InvocationTargetException paramSSLSocket)
      {
        label19: break label19;
      }
    }

    public void configureTlsExtensions(SSLSocket paramSSLSocket, String paramString, List<Protocol> paramList)
    {
      paramString = new ArrayList(paramList.size());
      int i = 0;
      int j = paramList.size();
      Object localObject;
      if (i < j)
      {
        localObject = (Protocol)paramList.get(i);
        if (localObject == Protocol.HTTP_1_0);
        while (true)
        {
          i += 1;
          break;
          paramString.add(((Protocol)localObject).toString());
        }
      }
      try
      {
        paramList = Platform.class.getClassLoader();
        localObject = this.clientProviderClass;
        Class localClass = this.serverProviderClass;
        paramString = new Platform.JettyNegoProvider(paramString);
        paramString = Proxy.newProxyInstance(paramList, new Class[] { localObject, localClass }, paramString);
        this.putMethod.invoke(null, new Object[] { paramSSLSocket, paramString });
        return;
      }
      catch (InvocationTargetException paramSSLSocket)
      {
        throw new AssertionError(paramSSLSocket);
      }
      catch (IllegalAccessException paramSSLSocket)
      {
        label147: break label147;
      }
    }

    public String getSelectedProtocol(SSLSocket paramSSLSocket)
    {
      try
      {
        paramSSLSocket = (Platform.JettyNegoProvider)Proxy.getInvocationHandler(this.getMethod.invoke(null, new Object[] { paramSSLSocket }));
        if ((!Platform.JettyNegoProvider.access$000(paramSSLSocket)) && (Platform.JettyNegoProvider.access$100(paramSSLSocket) == null))
        {
          Internal.logger.log(Level.INFO, "ALPN callback dropped: SPDY and HTTP/2 are disabled. Is alpn-boot on the boot class path?");
          return null;
        }
        if (!Platform.JettyNegoProvider.access$000(paramSSLSocket))
        {
          paramSSLSocket = Platform.JettyNegoProvider.access$100(paramSSLSocket);
          return paramSSLSocket;
        }
      }
      catch (InvocationTargetException paramSSLSocket)
      {
        throw new AssertionError();
      }
      catch (IllegalAccessException paramSSLSocket)
      {
        label65: break label65;
      }
      return null;
    }
  }

  private static class JettyNegoProvider
    implements InvocationHandler
  {
    private final List<String> protocols;
    private String selected;
    private boolean unsupported;

    public JettyNegoProvider(List<String> paramList)
    {
      this.protocols = paramList;
    }

    public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
      throws Throwable
    {
      String str = paramMethod.getName();
      Class localClass = paramMethod.getReturnType();
      paramObject = paramArrayOfObject;
      if (paramArrayOfObject == null)
        paramObject = Util.EMPTY_STRING_ARRAY;
      if ((str.equals("supports")) && (Boolean.TYPE == localClass))
        return Boolean.valueOf(true);
      if ((str.equals("unsupported")) && (Void.TYPE == localClass))
      {
        this.unsupported = true;
        return null;
      }
      if ((str.equals("protocols")) && (paramObject.length == 0))
        return this.protocols;
      if (((str.equals("selectProtocol")) || (str.equals("select"))) && (String.class == localClass) && (paramObject.length == 1) && ((paramObject[0] instanceof List)))
      {
        paramObject = (List)paramObject[0];
        int i = 0;
        int j = paramObject.size();
        while (i < j)
        {
          if (this.protocols.contains(paramObject.get(i)))
          {
            paramObject = (String)paramObject.get(i);
            this.selected = paramObject;
            return paramObject;
          }
          i += 1;
        }
        paramObject = (String)this.protocols.get(0);
        this.selected = paramObject;
        return paramObject;
      }
      if (((str.equals("protocolSelected")) || (str.equals("selected"))) && (paramObject.length == 1))
      {
        this.selected = ((String)paramObject[0]);
        return null;
      }
      return paramMethod.invoke(this, paramObject);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.Platform
 * JD-Core Version:    0.6.2
 */