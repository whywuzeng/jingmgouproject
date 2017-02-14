package com.umeng.message.proguard;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class aJ
{
  private static final Logger a = Logger.getLogger(aJ.class.getName());

  public static aC a(aO paramaO)
  {
    return new aK(paramaO);
  }

  public static aD a(aP paramaP)
  {
    return new aL(paramaP);
  }

  public static aO a(OutputStream paramOutputStream)
  {
    return a(paramOutputStream, new aQ());
  }

  private static aO a(final OutputStream paramOutputStream, aQ paramaQ)
  {
    return new aO()
    {
      public void b(aB paramAnonymousaB, long paramAnonymousLong)
        throws IOException
      {
        aR.a(paramAnonymousaB.b, 0L, paramAnonymousLong);
        while (paramAnonymousLong > 0L)
        {
          this.a.g();
          aM localaM = paramAnonymousaB.a;
          int i = (int)Math.min(paramAnonymousLong, localaM.d - localaM.c);
          paramOutputStream.write(localaM.b, localaM.c, i);
          localaM.c += i;
          long l = paramAnonymousLong - i;
          paramAnonymousaB.b -= i;
          paramAnonymousLong = l;
          if (localaM.c == localaM.d)
          {
            paramAnonymousaB.a = localaM.a();
            aN.a.a(localaM);
            paramAnonymousLong = l;
          }
        }
      }

      public void close()
        throws IOException
      {
        paramOutputStream.close();
      }

      public void s()
        throws IOException
      {
        paramOutputStream.flush();
      }

      public aQ t()
      {
        return this.a;
      }

      public String toString()
      {
        return "sink(" + paramOutputStream + ")";
      }
    };
  }

  public static aO a(final Socket paramSocket)
    throws IOException
  {
    az localaz = c(paramSocket);
    return new aO()
    {
      public void b(aB paramAnonymousaB, long paramAnonymousLong)
        throws IOException
      {
        this.a.a();
        try
        {
          this.b.b(paramAnonymousaB, paramAnonymousLong);
          this.a.a(true);
          return;
        }
        finally
        {
          this.a.a(false);
        }
        throw paramAnonymousaB;
      }

      public void close()
        throws IOException
      {
        this.a.a();
        try
        {
          this.b.close();
          this.a.a(true);
          return;
        }
        finally
        {
          this.a.a(false);
        }
      }

      public void s()
        throws IOException
      {
        this.a.a();
        try
        {
          this.b.s();
          this.a.a(true);
          return;
        }
        finally
        {
          this.a.a(false);
        }
      }

      public aQ t()
      {
        return this.a;
      }

      public String toString()
      {
        return "sink(" + paramSocket + ")";
      }
    };
  }

  public static aP a(InputStream paramInputStream)
  {
    return a(paramInputStream, new aQ());
  }

  private static aP a(final InputStream paramInputStream, aQ paramaQ)
  {
    return new aP()
    {
      public long c(aB paramAnonymousaB, long paramAnonymousLong)
        throws IOException
      {
        if (paramAnonymousLong < 0L)
          throw new IllegalArgumentException("byteCount < 0: " + paramAnonymousLong);
        this.a.g();
        aM localaM = paramAnonymousaB.f(1);
        int i = (int)Math.min(paramAnonymousLong, 2048 - localaM.d);
        i = paramInputStream.read(localaM.b, localaM.d, i);
        if (i == -1)
          return -1L;
        localaM.d += i;
        paramAnonymousaB.b += i;
        return i;
      }

      public void close()
        throws IOException
      {
        paramInputStream.close();
      }

      public aQ t()
      {
        return this.a;
      }

      public String toString()
      {
        return "source(" + paramInputStream + ")";
      }
    };
  }

  public static aP b(final Socket paramSocket)
    throws IOException
  {
    az localaz = c(paramSocket);
    return new aP()
    {
      public long c(aB paramAnonymousaB, long paramAnonymousLong)
        throws IOException
      {
        this.a.a();
        try
        {
          paramAnonymousLong = this.b.c(paramAnonymousaB, paramAnonymousLong);
          this.a.a(true);
          return paramAnonymousLong;
        }
        finally
        {
          this.a.a(false);
        }
        throw paramAnonymousaB;
      }

      public void close()
        throws IOException
      {
        try
        {
          this.b.close();
          this.a.a(true);
          return;
        }
        finally
        {
          this.a.a(false);
        }
      }

      public aQ t()
      {
        return this.a;
      }

      public String toString()
      {
        return "source(" + paramSocket + ")";
      }
    };
  }

  private static az c(Socket paramSocket)
  {
    return new az()
    {
      protected void c()
      {
        try
        {
          this.a.close();
          return;
        }
        catch (Exception localException)
        {
          aJ.a().log(Level.WARNING, "Failed to close timed out socket " + this.a, localException);
        }
      }
    };
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.aJ
 * JD-Core Version:    0.6.2
 */