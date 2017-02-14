package com.yolanda.nohttp.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Build.VERSION;
import com.yolanda.nohttp.Logger;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetUtil
{
  private static final Pattern IPV4_PATTERN = Pattern.compile("^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$");
  private static final Pattern IPV6_HEX_COMPRESSED_PATTERN = Pattern.compile("^(([0-9A-Fa-f]{1,4}(:[0-9A-Fa-f]{1,4}){0,5})?)::(([0-9A-Fa-f]{1,4}(:[0-9A-Fa-f]{1,4}){0,5})?)$");
  private static final Pattern IPV6_STD_PATTERN = Pattern.compile("^[0-9a-fA-F]{1,4}(:[0-9a-fA-F]{1,4}){7}$");

  public static String getLocalIPAddress()
  {
    Object localObject = null;
    try
    {
      Enumeration localEnumeration1 = NetworkInterface.getNetworkInterfaces();
      localObject = localEnumeration1;
      if ((localObject == null) || (!localObject.hasMoreElements()))
        return "";
    }
    catch (SocketException localSocketException)
    {
      while (true)
        Logger.w(localSocketException);
      Enumeration localEnumeration2 = ((NetworkInterface)localObject.nextElement()).getInetAddresses();
      InetAddress localInetAddress;
      do
      {
        if (!localEnumeration2.hasMoreElements())
          break;
        localInetAddress = (InetAddress)localEnumeration2.nextElement();
      }
      while ((localInetAddress.isLoopbackAddress()) || (!isIPv4Address(localInetAddress.getHostAddress())));
      return localInetAddress.getHostAddress();
    }
  }

  public static boolean isGPRSOpen(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    Class localClass = paramContext.getClass();
    try
    {
      boolean bool = ((Boolean)localClass.getMethod("getMobileDataEnabled", null).invoke(paramContext, null)).booleanValue();
      return bool;
    }
    catch (Exception paramContext)
    {
      Logger.w(paramContext);
    }
    return false;
  }

  public static boolean isIPv4Address(String paramString)
  {
    return IPV4_PATTERN.matcher(paramString).matches();
  }

  public static boolean isIPv6Address(String paramString)
  {
    return (isIPv6StdAddress(paramString)) || (isIPv6HexCompressedAddress(paramString));
  }

  public static boolean isIPv6HexCompressedAddress(String paramString)
  {
    int j = 0;
    int i = 0;
    while (true)
    {
      if (i >= paramString.length())
      {
        if ((j > 7) || (!IPV6_HEX_COMPRESSED_PATTERN.matcher(paramString).matches()))
          break;
        return true;
      }
      int k = j;
      if (paramString.charAt(i) == ':')
        k = j + 1;
      i += 1;
      j = k;
    }
    return false;
  }

  public static boolean isIPv6StdAddress(String paramString)
  {
    return IPV6_STD_PATTERN.matcher(paramString).matches();
  }

  @SuppressLint({"NewApi"})
  public static boolean isNetworkAvailable(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext == null);
    while (true)
    {
      return false;
      int j;
      int i;
      if (Build.VERSION.SDK_INT >= 21)
      {
        Network[] arrayOfNetwork = paramContext.getAllNetworks();
        j = arrayOfNetwork.length;
        i = 0;
        while (i < j)
        {
          if (paramContext.getNetworkInfo(arrayOfNetwork[i]).getState() == NetworkInfo.State.CONNECTED)
            return true;
          i += 1;
        }
      }
      else
      {
        paramContext = paramContext.getAllNetworkInfo();
        j = paramContext.length;
        i = 0;
        while (i < j)
        {
          if (paramContext[i].getState() == NetworkInfo.State.CONNECTED)
            return true;
          i += 1;
        }
      }
    }
  }

  public static void setGPRSEnable(Context paramContext, boolean paramBoolean)
  {
    try
    {
      paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
      paramContext.getClass().getMethod("setMobileDataEnabled", new Class[] { Boolean.TYPE }).invoke(paramContext, new Object[] { Boolean.valueOf(paramBoolean) });
      return;
    }
    catch (Exception paramContext)
    {
      Logger.w(paramContext);
    }
  }

  @SuppressLint({"NewApi"})
  public boolean isMobileConnected(Context paramContext)
  {
    boolean bool = true;
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    Network[] arrayOfNetwork;
    int i;
    if (Build.VERSION.SDK_INT >= 21)
    {
      arrayOfNetwork = paramContext.getAllNetworks();
      int j = arrayOfNetwork.length;
      i = 0;
      if (i >= j)
        label38: bool = false;
    }
    do
    {
      NetworkInfo localNetworkInfo;
      do
      {
        return bool;
        localNetworkInfo = paramContext.getNetworkInfo(arrayOfNetwork[i]);
        if (localNetworkInfo.getType() != 0)
          break;
      }
      while ((localNetworkInfo.isAvailable()) && (localNetworkInfo.isConnected()));
      return false;
      i += 1;
      break;
      paramContext = paramContext.getNetworkInfo(1);
      if (paramContext == null)
        break label38;
    }
    while ((paramContext.isAvailable()) && (paramContext.isConnected()));
    return false;
  }

  @SuppressLint({"NewApi"})
  public boolean isWifiConnected(Context paramContext)
  {
    boolean bool = true;
    Network[] arrayOfNetwork;
    int i;
    if (paramContext != null)
    {
      paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (Build.VERSION.SDK_INT < 21)
        break label92;
      arrayOfNetwork = paramContext.getAllNetworks();
      int j = arrayOfNetwork.length;
      i = 0;
      if (i < j);
    }
    else
    {
      label42: bool = false;
    }
    label92: 
    do
    {
      NetworkInfo localNetworkInfo;
      do
      {
        return bool;
        localNetworkInfo = paramContext.getNetworkInfo(arrayOfNetwork[i]);
        if (localNetworkInfo.getType() != 1)
          break;
      }
      while ((localNetworkInfo.isAvailable()) && (localNetworkInfo.isConnected()));
      return false;
      i += 1;
      break;
      paramContext = paramContext.getNetworkInfo(1);
      if (paramContext == null)
        break label42;
    }
    while ((paramContext.isAvailable()) && (paramContext.isConnected()));
    return false;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.tools.NetUtil
 * JD-Core Version:    0.6.2
 */