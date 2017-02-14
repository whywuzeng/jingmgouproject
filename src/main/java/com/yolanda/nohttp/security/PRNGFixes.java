package com.yolanda.nohttp.security;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.Process;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.SecureRandomSpi;
import java.security.Security;

public final class PRNGFixes
{
  private static final byte[] BUILD_FINGERPRINT_AND_DEVICE_SERIAL = getBuildFingerprintAndDeviceSerial();
  private static final int VERSION_CODE_JELLY_BEAN = 16;
  private static final int VERSION_CODE_JELLY_BEAN_MR2 = 18;

  public static void apply()
  {
    applyOpenSSLFix();
    installLinuxPRNGSecureRandom();
  }

  private static void applyOpenSSLFix()
    throws SecurityException
  {
    if ((Build.VERSION.SDK_INT < 16) || (Build.VERSION.SDK_INT > 18));
    while (true)
    {
      return;
      try
      {
        Class.forName("org.apache.harmony.xnet.provider.jsse.NativeCrypto").getMethod("RAND_seed", new Class[] { [B.class }).invoke(null, new Object[] { generateSeed() });
        int i = ((Integer)Class.forName("org.apache.harmony.xnet.provider.jsse.NativeCrypto").getMethod("RAND_load_file", new Class[] { String.class, Long.TYPE }).invoke(null, new Object[] { "/dev/urandom", Integer.valueOf(1024) })).intValue();
        if (i == 1024)
          continue;
        throw new IOException("Unexpected number of bytes read from Linux PRNG: " + i);
      }
      catch (Exception localException)
      {
        throw new SecurityException("Failed to seed OpenSSL PRNG", localException);
      }
    }
  }

  private static byte[] generateSeed()
  {
    try
    {
      Object localObject = new ByteArrayOutputStream();
      DataOutputStream localDataOutputStream = new DataOutputStream((OutputStream)localObject);
      localDataOutputStream.writeLong(System.currentTimeMillis());
      localDataOutputStream.writeLong(System.nanoTime());
      localDataOutputStream.writeInt(Process.myPid());
      localDataOutputStream.writeInt(Process.myUid());
      localDataOutputStream.write(BUILD_FINGERPRINT_AND_DEVICE_SERIAL);
      localDataOutputStream.close();
      localObject = ((ByteArrayOutputStream)localObject).toByteArray();
      return localObject;
    }
    catch (IOException localIOException)
    {
      throw new SecurityException("Failed to generate seed", localIOException);
    }
  }

  private static byte[] getBuildFingerprintAndDeviceSerial()
  {
    Object localObject = new StringBuilder();
    String str = Build.FINGERPRINT;
    if (str != null)
      ((StringBuilder)localObject).append(str);
    str = getDeviceSerialNumber();
    if (str != null)
      ((StringBuilder)localObject).append(str);
    try
    {
      localObject = ((StringBuilder)localObject).toString().getBytes("UTF-8");
      return localObject;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw new RuntimeException("UTF-8 encoding not supported");
  }

  private static String getDeviceSerialNumber()
  {
    try
    {
      String str = (String)Build.class.getField("SERIAL").get(null);
      return str;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  private static void installLinuxPRNGSecureRandom()
    throws SecurityException
  {
    if (Build.VERSION.SDK_INT > 18);
    while (true)
    {
      return;
      Object localObject = Security.getProviders("SecureRandom.SHA1PRNG");
      if ((localObject == null) || (localObject.length < 1) || (!LinuxPRNGSecureRandomProvider.class.equals(localObject[0].getClass())))
        Security.insertProviderAt(new LinuxPRNGSecureRandomProvider(), 1);
      localObject = new SecureRandom();
      if (!LinuxPRNGSecureRandomProvider.class.equals(((SecureRandom)localObject).getProvider().getClass()))
        throw new SecurityException("new SecureRandom() backed by wrong Provider: " + ((SecureRandom)localObject).getProvider().getClass());
      try
      {
        localObject = SecureRandom.getInstance("SHA1PRNG");
        if (LinuxPRNGSecureRandomProvider.class.equals(((SecureRandom)localObject).getProvider().getClass()))
          continue;
        throw new SecurityException("SecureRandom.getInstance(\"SHA1PRNG\") backed by wrong Provider: " + ((SecureRandom)localObject).getProvider().getClass());
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        throw new SecurityException("SHA1PRNG not available", localNoSuchAlgorithmException);
      }
    }
  }

  public static class LinuxPRNGSecureRandom extends SecureRandomSpi
  {
    private static final File URANDOM_FILE = new File("/dev/urandom");
    private static final Object sLock = new Object();
    private static DataInputStream sUrandomIn;
    private static OutputStream sUrandomOut;
    private static final long serialVersionUID = 140354302415L;
    private boolean mSeeded;

    private DataInputStream getUrandomInputStream()
    {
      synchronized (sLock)
      {
        DataInputStream localDataInputStream = sUrandomIn;
        if (localDataInputStream == null);
        try
        {
          sUrandomIn = new DataInputStream(new FileInputStream(URANDOM_FILE));
          localDataInputStream = sUrandomIn;
          return localDataInputStream;
        }
        catch (IOException localIOException)
        {
          throw new SecurityException("Failed to open " + URANDOM_FILE + " for reading", localIOException);
        }
      }
    }

    private OutputStream getUrandomOutputStream()
      throws IOException
    {
      synchronized (sLock)
      {
        if (sUrandomOut == null)
          sUrandomOut = new FileOutputStream(URANDOM_FILE);
        OutputStream localOutputStream = sUrandomOut;
        return localOutputStream;
      }
    }

    protected byte[] engineGenerateSeed(int paramInt)
    {
      byte[] arrayOfByte = new byte[paramInt];
      engineNextBytes(arrayOfByte);
      return arrayOfByte;
    }

    // ERROR //
    protected void engineNextBytes(byte[] paramArrayOfByte)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 96	com/yolanda/nohttp/security/PRNGFixes$LinuxPRNGSecureRandom:mSeeded	Z
      //   4: ifne +10 -> 14
      //   7: aload_0
      //   8: invokestatic 100	com/yolanda/nohttp/security/PRNGFixes:access$0	()[B
      //   11: invokevirtual 103	com/yolanda/nohttp/security/PRNGFixes$LinuxPRNGSecureRandom:engineSetSeed	([B)V
      //   14: getstatic 39	com/yolanda/nohttp/security/PRNGFixes$LinuxPRNGSecureRandom:sLock	Ljava/lang/Object;
      //   17: astore_2
      //   18: aload_2
      //   19: monitorenter
      //   20: aload_0
      //   21: invokespecial 105	com/yolanda/nohttp/security/PRNGFixes$LinuxPRNGSecureRandom:getUrandomInputStream	()Ljava/io/DataInputStream;
      //   24: astore_3
      //   25: aload_2
      //   26: monitorexit
      //   27: aload_3
      //   28: monitorenter
      //   29: aload_3
      //   30: aload_1
      //   31: invokevirtual 108	java/io/DataInputStream:readFully	([B)V
      //   34: aload_3
      //   35: monitorexit
      //   36: return
      //   37: astore_1
      //   38: aload_2
      //   39: monitorexit
      //   40: aload_1
      //   41: athrow
      //   42: astore_1
      //   43: new 59	java/lang/SecurityException
      //   46: dup
      //   47: new 61	java/lang/StringBuilder
      //   50: dup
      //   51: ldc 110
      //   53: invokespecial 64	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   56: getstatic 33	com/yolanda/nohttp/security/PRNGFixes$LinuxPRNGSecureRandom:URANDOM_FILE	Ljava/io/File;
      //   59: invokevirtual 68	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   62: invokevirtual 77	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   65: aload_1
      //   66: invokespecial 80	java/lang/SecurityException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
      //   69: athrow
      //   70: astore_1
      //   71: aload_3
      //   72: monitorexit
      //   73: aload_1
      //   74: athrow
      //
      // Exception table:
      //   from	to	target	type
      //   20	27	37	finally
      //   38	40	37	finally
      //   14	20	42	java/io/IOException
      //   27	29	42	java/io/IOException
      //   40	42	42	java/io/IOException
      //   73	75	42	java/io/IOException
      //   29	36	70	finally
      //   71	73	70	finally
    }

    protected void engineSetSeed(byte[] paramArrayOfByte)
    {
      try
      {
        synchronized (sLock)
        {
          OutputStream localOutputStream = getUrandomOutputStream();
          localOutputStream.write(paramArrayOfByte);
          localOutputStream.flush();
          return;
        }
      }
      catch (IOException paramArrayOfByte)
      {
        Log.w(PRNGFixes.class.getSimpleName(), "Failed to mix seed into " + URANDOM_FILE);
        return;
      }
      finally
      {
        this.mSeeded = true;
      }
    }
  }

  private static class LinuxPRNGSecureRandomProvider extends Provider
  {
    private static final long serialVersionUID = 1354323215L;

    public LinuxPRNGSecureRandomProvider()
    {
      super(1.0D, "A Linux-specific random number provider that uses /dev/urandom");
      put("SecureRandom.SHA1PRNG", PRNGFixes.LinuxPRNGSecureRandom.class.getName());
      put("SecureRandom.SHA1PRNG ImplementedIn", "Software");
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.security.PRNGFixes
 * JD-Core Version:    0.6.2
 */