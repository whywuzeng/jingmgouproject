package com.baidu.platform.comapi.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class a
{
  public static String a(Context paramContext)
  {
    String str = paramContext.getPackageName();
    paramContext = a(paramContext, str);
    return paramContext + ";" + str;
  }

  private static String a(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 64).signatures;
      paramContext = a((X509Certificate)CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(paramContext[0].toByteArray())));
      paramString = new StringBuffer();
      int i = 0;
      while (i < paramContext.length())
      {
        paramString.append(paramContext.charAt(i));
        if ((i > 0) && (i % 2 == 1) && (i < paramContext.length() - 1))
          paramString.append(":");
        i += 1;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      while (true)
      {
        paramContext.printStackTrace();
        paramContext = "";
      }
    }
    catch (CertificateException paramContext)
    {
      while (true)
      {
        paramContext.printStackTrace();
        paramContext = "";
      }
    }
    return paramString.toString();
  }

  static String a(X509Certificate paramX509Certificate)
  {
    try
    {
      paramX509Certificate = a.a(a(paramX509Certificate.getEncoded()));
      return paramX509Certificate;
    }
    catch (CertificateEncodingException paramX509Certificate)
    {
    }
    return null;
  }

  static byte[] a(byte[] paramArrayOfByte)
  {
    try
    {
      paramArrayOfByte = MessageDigest.getInstance("SHA1").digest(paramArrayOfByte);
      return paramArrayOfByte;
    }
    catch (NoSuchAlgorithmException paramArrayOfByte)
    {
    }
    return null;
  }

  static class a
  {
    public static String a(byte[] paramArrayOfByte)
    {
      char[] arrayOfChar = new char[16];
      char[] tmp6_5 = arrayOfChar;
      tmp6_5[0] = 48;
      char[] tmp11_6 = tmp6_5;
      tmp11_6[1] = 49;
      char[] tmp16_11 = tmp11_6;
      tmp16_11[2] = 50;
      char[] tmp21_16 = tmp16_11;
      tmp21_16[3] = 51;
      char[] tmp26_21 = tmp21_16;
      tmp26_21[4] = 52;
      char[] tmp31_26 = tmp26_21;
      tmp31_26[5] = 53;
      char[] tmp36_31 = tmp31_26;
      tmp36_31[6] = 54;
      char[] tmp42_36 = tmp36_31;
      tmp42_36[7] = 55;
      char[] tmp48_42 = tmp42_36;
      tmp48_42[8] = 56;
      char[] tmp54_48 = tmp48_42;
      tmp54_48[9] = 57;
      char[] tmp60_54 = tmp54_48;
      tmp60_54[10] = 65;
      char[] tmp66_60 = tmp60_54;
      tmp66_60[11] = 66;
      char[] tmp72_66 = tmp66_60;
      tmp72_66[12] = 67;
      char[] tmp78_72 = tmp72_66;
      tmp78_72[13] = 68;
      char[] tmp84_78 = tmp78_72;
      tmp84_78[14] = 69;
      char[] tmp90_84 = tmp84_78;
      tmp90_84[15] = 70;
      tmp90_84;
      StringBuilder localStringBuilder = new StringBuilder(paramArrayOfByte.length * 2);
      int i = 0;
      while (i < paramArrayOfByte.length)
      {
        localStringBuilder.append(arrayOfChar[((paramArrayOfByte[i] & 0xF0) >> 4)]);
        localStringBuilder.append(arrayOfChar[(paramArrayOfByte[i] & 0xF)]);
        i += 1;
      }
      return localStringBuilder.toString();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.platform.comapi.util.a
 * JD-Core Version:    0.6.2
 */