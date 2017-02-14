package com.squareup.okhttp.internal;

import com.squareup.okhttp.HttpUrl;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import okio.Buffer;
import okio.ByteString;
import okio.Source;
import okio.Timeout;

public final class Util
{
  public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
  public static final String[] EMPTY_STRING_ARRAY = new String[0];
  public static final Charset UTF_8 = Charset.forName("UTF-8");

  public static void checkOffsetAndCount(long paramLong1, long paramLong2, long paramLong3)
  {
    if (((paramLong2 | paramLong3) < 0L) || (paramLong2 > paramLong1) || (paramLong1 - paramLong2 < paramLong3))
      throw new ArrayIndexOutOfBoundsException();
  }

  public static void closeAll(Closeable paramCloseable1, Closeable paramCloseable2)
    throws IOException
  {
    Object localObject = null;
    try
    {
      paramCloseable1.close();
      paramCloseable1 = localObject;
    }
    catch (Throwable paramCloseable1)
    {
      try
      {
        while (true)
        {
          paramCloseable2.close();
          paramCloseable2 = paramCloseable1;
          if (paramCloseable2 != null)
            break;
          return;
          paramCloseable1 = paramCloseable1;
        }
      }
      catch (Throwable localThrowable)
      {
        while (true)
        {
          paramCloseable2 = paramCloseable1;
          if (paramCloseable1 == null)
            paramCloseable2 = localThrowable;
        }
        if ((paramCloseable2 instanceof IOException))
          throw ((IOException)paramCloseable2);
        if ((paramCloseable2 instanceof RuntimeException))
          throw ((RuntimeException)paramCloseable2);
        if ((paramCloseable2 instanceof Error))
          throw ((Error)paramCloseable2);
      }
    }
    throw new AssertionError(paramCloseable2);
  }

  public static void closeQuietly(Closeable paramCloseable)
  {
    if (paramCloseable != null);
    try
    {
      paramCloseable.close();
      return;
    }
    catch (RuntimeException paramCloseable)
    {
      throw paramCloseable;
    }
    catch (Exception paramCloseable)
    {
    }
  }

  public static void closeQuietly(ServerSocket paramServerSocket)
  {
    if (paramServerSocket != null);
    try
    {
      paramServerSocket.close();
      return;
    }
    catch (RuntimeException paramServerSocket)
    {
      throw paramServerSocket;
    }
    catch (Exception paramServerSocket)
    {
    }
  }

  public static void closeQuietly(Socket paramSocket)
  {
    if (paramSocket != null);
    try
    {
      paramSocket.close();
      return;
    }
    catch (AssertionError paramSocket)
    {
      while (isAndroidGetsocknameError(paramSocket));
      throw paramSocket;
    }
    catch (RuntimeException paramSocket)
    {
      throw paramSocket;
    }
    catch (Exception paramSocket)
    {
    }
  }

  public static boolean discard(Source paramSource, int paramInt, TimeUnit paramTimeUnit)
  {
    try
    {
      boolean bool = skipAll(paramSource, paramInt, paramTimeUnit);
      return bool;
    }
    catch (IOException paramSource)
    {
    }
    return false;
  }

  public static boolean equal(Object paramObject1, Object paramObject2)
  {
    return (paramObject1 == paramObject2) || ((paramObject1 != null) && (paramObject1.equals(paramObject2)));
  }

  public static String hostHeader(HttpUrl paramHttpUrl)
  {
    if (paramHttpUrl.port() != HttpUrl.defaultPort(paramHttpUrl.scheme()))
      return paramHttpUrl.host() + ":" + paramHttpUrl.port();
    return paramHttpUrl.host();
  }

  public static <T> List<T> immutableList(List<T> paramList)
  {
    return Collections.unmodifiableList(new ArrayList(paramList));
  }

  public static <T> List<T> immutableList(T[] paramArrayOfT)
  {
    return Collections.unmodifiableList(Arrays.asList((Object[])paramArrayOfT.clone()));
  }

  public static <K, V> Map<K, V> immutableMap(Map<K, V> paramMap)
  {
    return Collections.unmodifiableMap(new LinkedHashMap(paramMap));
  }

  private static <T> List<T> intersect(T[] paramArrayOfT1, T[] paramArrayOfT2)
  {
    ArrayList localArrayList = new ArrayList();
    int k = paramArrayOfT1.length;
    int i = 0;
    if (i < k)
    {
      T ? = paramArrayOfT1[i];
      int m = paramArrayOfT2.length;
      int j = 0;
      while (true)
      {
        if (j < m)
        {
          T ? = paramArrayOfT2[j];
          if (?.equals(?))
            localArrayList.add(?);
        }
        else
        {
          i += 1;
          break;
        }
        j += 1;
      }
    }
    return localArrayList;
  }

  public static <T> T[] intersect(Class<T> paramClass, T[] paramArrayOfT1, T[] paramArrayOfT2)
  {
    paramArrayOfT1 = intersect(paramArrayOfT1, paramArrayOfT2);
    return paramArrayOfT1.toArray((Object[])Array.newInstance(paramClass, paramArrayOfT1.size()));
  }

  public static boolean isAndroidGetsocknameError(AssertionError paramAssertionError)
  {
    return (paramAssertionError.getCause() != null) && (paramAssertionError.getMessage() != null) && (paramAssertionError.getMessage().contains("getsockname failed"));
  }

  public static String md5Hex(String paramString)
  {
    try
    {
      paramString = ByteString.of(MessageDigest.getInstance("MD5").digest(paramString.getBytes("UTF-8"))).hex();
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      throw new AssertionError(paramString);
    }
    catch (UnsupportedEncodingException paramString)
    {
      label24: break label24;
    }
  }

  public static ByteString sha1(ByteString paramByteString)
  {
    try
    {
      paramByteString = ByteString.of(MessageDigest.getInstance("SHA-1").digest(paramByteString.toByteArray()));
      return paramByteString;
    }
    catch (NoSuchAlgorithmException paramByteString)
    {
    }
    throw new AssertionError(paramByteString);
  }

  public static String shaBase64(String paramString)
  {
    try
    {
      paramString = ByteString.of(MessageDigest.getInstance("SHA-1").digest(paramString.getBytes("UTF-8"))).base64();
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      throw new AssertionError(paramString);
    }
    catch (UnsupportedEncodingException paramString)
    {
      label24: break label24;
    }
  }

  public static boolean skipAll(Source paramSource, int paramInt, TimeUnit paramTimeUnit)
    throws IOException
  {
    long l2 = System.nanoTime();
    long l1;
    if (paramSource.timeout().hasDeadline())
      l1 = paramSource.timeout().deadlineNanoTime() - l2;
    while (true)
    {
      paramSource.timeout().deadlineNanoTime(Math.min(l1, paramTimeUnit.toNanos(paramInt)) + l2);
      try
      {
        paramTimeUnit = new Buffer();
        while (paramSource.read(paramTimeUnit, 2048L) != -1L)
          paramTimeUnit.clear();
      }
      catch (InterruptedIOException paramTimeUnit)
      {
        if (l1 == 9223372036854775807L)
          paramSource.timeout().clearDeadline();
        while (true)
        {
          return false;
          l1 = 9223372036854775807L;
          break;
          if (l1 == 9223372036854775807L)
            paramSource.timeout().clearDeadline();
          while (true)
          {
            return true;
            paramSource.timeout().deadlineNanoTime(l2 + l1);
          }
          paramSource.timeout().deadlineNanoTime(l2 + l1);
        }
      }
      finally
      {
        if (l1 != 9223372036854775807L)
          break label188;
      }
    }
    paramSource.timeout().clearDeadline();
    while (true)
    {
      throw paramTimeUnit;
      label188: paramSource.timeout().deadlineNanoTime(l2 + l1);
    }
  }

  public static ThreadFactory threadFactory(String paramString, final boolean paramBoolean)
  {
    return new ThreadFactory()
    {
      public Thread newThread(Runnable paramAnonymousRunnable)
      {
        paramAnonymousRunnable = new Thread(paramAnonymousRunnable, this.val$name);
        paramAnonymousRunnable.setDaemon(paramBoolean);
        return paramAnonymousRunnable;
      }
    };
  }

  public static String toHumanReadableAscii(String paramString)
  {
    int i = 0;
    int m = paramString.length();
    int j;
    while (true)
    {
      localObject = paramString;
      if (i >= m)
        break label119;
      j = paramString.codePointAt(i);
      if ((j <= 31) || (j >= 127))
        break;
      i += Character.charCount(j);
    }
    Object localObject = new Buffer();
    ((Buffer)localObject).writeUtf8(paramString, 0, i);
    if (i < m)
    {
      int k = paramString.codePointAt(i);
      if ((k > 31) && (k < 127));
      for (j = k; ; j = 63)
      {
        ((Buffer)localObject).writeUtf8CodePoint(j);
        i += Character.charCount(k);
        break;
      }
    }
    localObject = ((Buffer)localObject).readUtf8();
    label119: return localObject;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.Util
 * JD-Core Version:    0.6.2
 */