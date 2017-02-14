package com.baidu.location;

import com.baidu.location.b.f;
import com.baidu.location.f.b.b;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Jni
  implements f
{
  private static int k4;
  private static int k5;
  private static int k6;
  private static int k7;
  private static int k8;
  private static int k9;
  private static boolean la;
  private static int lb;
  private static int lc;
  private static int ld = 0;

  static
  {
    lb = 1;
    lc = 2;
    k7 = 11;
    k9 = 12;
    k5 = 13;
    k4 = 14;
    k8 = 15;
    k6 = 1024;
    la = false;
    try
    {
      System.loadLibrary("locSDK6a");
      return;
    }
    catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
    {
      localUnsatisfiedLinkError.printStackTrace();
      la = true;
    }
  }

  public static String E(String paramString)
  {
    if (la)
      return null;
    paramString = jdMethod_if(encrypt(paramString.getBytes()));
    try
    {
      paramString = URLEncoder.encode(paramString, "UTF-8");
      return paramString;
    }
    catch (Exception paramString)
    {
    }
    return "";
  }

  public static String F(String paramString)
  {
    int i = 740;
    int j = 0;
    if (la)
      return "err!";
    if (paramString == null)
      return "null";
    paramString = paramString.getBytes();
    byte[] arrayOfByte = new byte[k6];
    int k = paramString.length;
    if (k > 740);
    while (true)
    {
      int m;
      for (k = 0; j < i; k = m)
      {
        m = k;
        if (paramString[j] != 0)
        {
          arrayOfByte[k] = paramString[j];
          m = k + 1;
        }
        j += 1;
      }
      try
      {
        paramString = a(arrayOfByte, 132456);
        return paramString;
      }
      catch (UnsatisfiedLinkError paramString)
      {
        paramString.printStackTrace();
        return "err!";
      }
      i = k;
    }
  }

  public static String G(String paramString)
  {
    if (la)
      return "err!";
    try
    {
      paramString = new String(paramString.getBytes(), "UTF-8");
    }
    catch (Exception paramString)
    {
      try
      {
        while (true)
        {
          paramString = encodeNotLimit(paramString, 132456);
          return paramString + "|tp=3";
          paramString = paramString;
          paramString = "";
        }
      }
      catch (UnsatisfiedLinkError paramString)
      {
        while (true)
        {
          paramString.printStackTrace();
          paramString = "err!";
        }
      }
    }
  }

  public static String H(String paramString)
  {
    if (la)
      return "err!";
    return F(paramString) + "|tp=3";
  }

  public static Long I(String paramString)
  {
    if (la)
      return null;
    try
    {
      paramString = new String(paramString.getBytes(), "UTF-8");
    }
    catch (Exception paramString)
    {
      try
      {
        while (true)
        {
          long l = murmur(paramString);
          return Long.valueOf(l);
          paramString = paramString;
          paramString = "";
        }
      }
      catch (UnsatisfiedLinkError paramString)
      {
        paramString.printStackTrace();
      }
    }
    return null;
  }

  public static String J(String paramString)
  {
    if (la)
      return "err!";
    if (paramString == null)
      return "null";
    paramString = paramString.getBytes();
    try
    {
      paramString = c(paramString, 132456);
      return paramString;
    }
    catch (UnsatisfiedLinkError paramString)
    {
      paramString.printStackTrace();
    }
    return "err!";
  }

  public static String K(String paramString)
  {
    if (la)
      return "err!";
    try
    {
      paramString = new String(paramString.getBytes(), "UTF-8");
    }
    catch (Exception paramString)
    {
      try
      {
        while (true)
        {
          paramString = ee(paramString, 132456);
          return paramString + "|tp=4";
          paramString = paramString;
          paramString = "";
        }
      }
      catch (UnsatisfiedLinkError paramString)
      {
        while (true)
        {
          paramString.printStackTrace();
          paramString = "err!";
        }
      }
    }
  }

  public static String L(String paramString)
  {
    if (la);
    while (true)
    {
      return null;
      try
      {
        paramString = g(paramString.getBytes());
        if ((paramString != null) && (paramString.length() >= 2) && (!"no".equals(paramString)))
          return paramString;
      }
      catch (UnsatisfiedLinkError paramString)
      {
      }
    }
    return null;
  }

  private static native String a(byte[] paramArrayOfByte, int paramInt);

  private static native String b(double paramDouble1, double paramDouble2, int paramInt1, int paramInt2);

  private static native String c(byte[] paramArrayOfByte, int paramInt);

  public static String dI()
  {
    if (la)
      return "err!";
    try
    {
      String str = sky();
      return str;
    }
    catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
    {
      localUnsatisfiedLinkError.printStackTrace();
    }
    return "err!";
  }

  private static native String ee(String paramString, int paramInt);

  private static native String encodeNotLimit(String paramString, int paramInt);

  private static native byte[] encrypt(byte[] paramArrayOfByte);

  private static native void f(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);

  private static native String g(byte[] paramArrayOfByte);

  private static native String ib(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2);

  private static String jdMethod_if(byte[] paramArrayOfByte)
  {
    try
    {
      paramArrayOfByte = b.a(paramArrayOfByte, "UTF-8");
      return paramArrayOfByte;
    }
    catch (UnsupportedEncodingException paramArrayOfByte)
    {
    }
    return "";
  }

  public static String jdMethod_if(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    if (la)
      return null;
    return ib(paramArrayOfByte1, paramArrayOfByte2);
  }

  public static double[] jdMethod_if(double paramDouble1, double paramDouble2, String paramString)
  {
    double[] arrayOfDouble = new double[2];
    double[] tmp7_5 = arrayOfDouble;
    tmp7_5[0] = 0.0D;
    double[] tmp11_7 = tmp7_5;
    tmp11_7[1] = 0.0D;
    tmp11_7;
    if (la)
      return tmp11_7;
    int i = -1;
    if (paramString.equals("bd09"))
      i = ld;
    try
    {
      while (true)
      {
        paramString = b(paramDouble1, paramDouble2, i, 132456).split(":");
        tmp11_7[0] = Double.parseDouble(paramString[0]);
        tmp11_7[1] = Double.parseDouble(paramString[1]);
        label81: return tmp11_7;
        if (paramString.equals("bd09ll"))
          i = lb;
        else if (paramString.equals("gcj02"))
          i = lc;
        else if (paramString.equals("gps2gcj"))
          i = k7;
        else if (paramString.equals("bd092gcj"))
          i = k9;
        else if (paramString.equals("bd09ll2gcj"))
          i = k5;
        else if (paramString.equals("wgs842mc"))
          i = k8;
      }
    }
    catch (UnsatisfiedLinkError paramString)
    {
      break label81;
    }
  }

  public static void jdMethod_int(String paramString1, String paramString2)
  {
    if (la)
      return;
    try
    {
      f(paramString1.getBytes(), paramString2.getBytes());
      return;
    }
    catch (UnsatisfiedLinkError paramString1)
    {
      paramString1.printStackTrace();
    }
  }

  private static native long murmur(String paramString);

  private static native String sky();
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.Jni
 * JD-Core Version:    0.6.2
 */