package com.baidu.lbsapi.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Locale;

class b
{
  static String a()
  {
    return Locale.getDefault().getLanguage();
  }

  protected static String a(Context paramContext)
  {
    String str = paramContext.getPackageName();
    return a(paramContext, str) + ";" + str;
  }

  private static String a(Context paramContext, String paramString)
  {
    String str = "";
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 64).signatures;
      paramContext = a((X509Certificate)CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(paramContext[0].toByteArray())));
      paramString = new StringBuffer();
      int i = 0;
      while (true)
      {
        if (i >= paramContext.length())
          return paramString.toString();
        paramString.append(paramContext.charAt(i));
        if ((i > 0) && (i % 2 == 1) && (i < paramContext.length() - 1))
          paramString.append(":");
        i += 1;
      }
    }
    catch (CertificateException paramContext)
    {
      while (true)
        paramContext = str;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      while (true)
        paramContext = str;
    }
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

  protected static String[] b(Context paramContext)
  {
    Object localObject = null;
    String str = paramContext.getPackageName();
    String[] arrayOfString = b(paramContext, str);
    paramContext = localObject;
    int i;
    if (arrayOfString != null)
    {
      paramContext = localObject;
      if (arrayOfString.length > 0)
      {
        paramContext = new String[arrayOfString.length];
        i = 0;
      }
    }
    while (true)
    {
      if (i >= paramContext.length)
        return paramContext;
      paramContext[i] = (arrayOfString[i] + ";" + str);
      if (a.a)
        a.a("mcode" + paramContext[i]);
      i += 1;
    }
  }

  private static String[] b(Context paramContext, String paramString)
  {
    Object localObject4 = null;
    Object localObject5 = null;
    Object localObject3 = null;
    Object localObject1 = localObject4;
    Object localObject2 = localObject5;
    try
    {
      paramString = paramContext.getPackageManager().getPackageInfo(paramString, 64).signatures;
      paramContext = localObject3;
      int i;
      if (paramString != null)
      {
        paramContext = localObject3;
        localObject1 = localObject4;
        localObject2 = localObject5;
        if (paramString.length > 0)
        {
          localObject1 = localObject4;
          localObject2 = localObject5;
          paramContext = new String[paramString.length];
          i = 0;
        }
      }
      while (true)
      {
        localObject1 = paramContext;
        localObject2 = paramContext;
        j = paramString.length;
        if (i >= j)
        {
          localObject1 = null;
          paramString = (String)localObject1;
          if (paramContext != null)
          {
            paramString = (String)localObject1;
            if (paramContext.length > 0)
            {
              paramString = new String[paramContext.length];
              i = 0;
              if (i < paramContext.length)
                break;
            }
          }
          return paramString;
        }
        localObject1 = paramContext;
        localObject2 = paramContext;
        paramContext[i] = a((X509Certificate)CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(paramString[i].toByteArray())));
        i += 1;
      }
      localObject1 = new StringBuffer();
      int j = 0;
      while (true)
      {
        if (j >= paramContext[i].length())
        {
          paramString[i] = ((StringBuffer)localObject1).toString();
          i += 1;
          break;
        }
        ((StringBuffer)localObject1).append(paramContext[i].charAt(j));
        if ((j > 0) && (j % 2 == 1) && (j < paramContext[i].length() - 1))
          ((StringBuffer)localObject1).append(":");
        j += 1;
      }
    }
    catch (CertificateException paramContext)
    {
      while (true)
        paramContext = (Context)localObject1;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      while (true)
        paramContext = (Context)localObject2;
    }
  }

  static String c(Context paramContext)
  {
    Object localObject = null;
    if ((0 == 0) || ("".equals(null)))
    {
      localObject = paramContext.getSharedPreferences("mac", 0).getString("macaddr", null);
      if (localObject != null)
        break label123;
      localObject = d(paramContext);
      if (localObject == null)
        break label117;
      String str = Base64.encodeToString(((String)localObject).getBytes(), 0);
      localObject = str;
      if (!TextUtils.isEmpty(str))
      {
        paramContext.getSharedPreferences("mac", 0).edit().putString("macaddr", str).commit();
        localObject = str;
      }
    }
    label117: label123: 
    while (true)
    {
      if (a.a)
        a.a("getMacID mac_adress: " + (String)localObject);
      return localObject;
      localObject = "";
    }
  }

  private static boolean c(Context paramContext, String paramString)
  {
    if (paramContext.checkCallingOrSelfPermission(paramString) != -1);
    for (boolean bool = true; ; bool = false)
    {
      if (a.a)
        a.a("hasPermission " + bool + " | " + paramString);
      return bool;
    }
  }

  static String d(Context paramContext)
  {
    WifiInfo localWifiInfo = null;
    String str2 = null;
    String str1 = str2;
    try
    {
      if (c(paramContext, "android.permission.ACCESS_WIFI_STATE"))
      {
        str1 = str2;
        localWifiInfo = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo();
        str1 = str2;
        str2 = localWifiInfo.getMacAddress();
        str1 = str2;
        if (!TextUtils.isEmpty(str2))
        {
          str1 = str2;
          Base64.encode(str2.getBytes(), 0);
        }
        str1 = str2;
        paramContext = str2;
        if (a.a)
        {
          str1 = str2;
          a.a(String.format("ssid=%s mac=%s", new Object[] { localWifiInfo.getSSID(), localWifiInfo.getMacAddress() }));
          return str2;
        }
      }
      else
      {
        str1 = str2;
        paramContext = localWifiInfo;
        if (a.a)
        {
          str1 = str2;
          a.a("You need the android.Manifest.permission.ACCESS_WIFI_STATE permission. Open AndroidManifest.xml and just before the final </manifest> tag add:android.permission.ACCESS_WIFI_STATE");
          return null;
        }
      }
    }
    catch (Exception localException)
    {
      paramContext = str1;
      if (a.a)
      {
        a.a(localException.toString());
        paramContext = str1;
      }
    }
    return paramContext;
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
      while (true)
      {
        if (i >= paramArrayOfByte.length)
          return localStringBuilder.toString();
        localStringBuilder.append(arrayOfChar[((paramArrayOfByte[i] & 0xF0) >> 4)]);
        localStringBuilder.append(arrayOfChar[(paramArrayOfByte[i] & 0xF)]);
        i += 1;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.lbsapi.auth.b
 * JD-Core Version:    0.6.2
 */