package com.mob.tools.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Process;
import android.os.SystemClock;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;

public class DeviceHelper
{
  private static DeviceHelper deviceHelper;
  private Context context;

  private DeviceHelper(Context paramContext)
  {
    this.context = paramContext.getApplicationContext();
  }

  public static DeviceHelper getInstance(Context paramContext)
  {
    if ((deviceHelper == null) && (paramContext != null))
      deviceHelper = new DeviceHelper(paramContext);
    return deviceHelper;
  }

  private String getLocalDeviceKey()
    throws Throwable
  {
    if (!getSdcardState());
    do
    {
      do
      {
        return null;
        localObject = new File(getSdcardPath(), "ShareSDK");
      }
      while (!((File)localObject).exists());
      localObject = new File((File)localObject, ".dk");
    }
    while (!((File)localObject).exists());
    ObjectInputStream localObjectInputStream = new ObjectInputStream(new FileInputStream((File)localObject));
    Object localObject = localObjectInputStream.readObject();
    if ((localObject != null) && ((localObject instanceof char[])));
    for (localObject = String.valueOf((char[])localObject); ; localObject = null)
    {
      localObjectInputStream.close();
      return localObject;
    }
  }

  private Object getSystemService(String paramString)
  {
    try
    {
      paramString = this.context.getSystemService(paramString);
      return paramString;
    }
    catch (Throwable paramString)
    {
      Ln.e(paramString);
    }
    return null;
  }

  private boolean is4GMobileNetwork()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)getSystemService("phone");
    if (localTelephonyManager == null)
      return false;
    if (localTelephonyManager.getNetworkType() == 13);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  private boolean isFastMobileNetwork()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)getSystemService("phone");
    if (localTelephonyManager == null)
      return false;
    switch (localTelephonyManager.getNetworkType())
    {
    default:
      return false;
    case 7:
      return false;
    case 4:
      return false;
    case 2:
      return false;
    case 5:
      return true;
    case 6:
      return true;
    case 1:
      return false;
    case 8:
      return true;
    case 10:
      return true;
    case 9:
      return true;
    case 3:
      return true;
    case 14:
      return true;
    case 12:
      return true;
    case 15:
      return true;
    case 11:
      return false;
    case 13:
      return true;
    case 0:
    }
    return false;
  }

  private boolean isSystemApp(PackageInfo paramPackageInfo)
  {
    boolean bool = false;
    int i;
    if ((paramPackageInfo.applicationInfo.flags & 0x1) == 1)
    {
      i = 1;
      if ((paramPackageInfo.applicationInfo.flags & 0x80) != 1)
        break label54;
    }
    label54: for (int j = 1; ; j = 0)
    {
      if ((i != 0) || (j != 0))
        bool = true;
      return bool;
      i = 0;
      break;
    }
  }

  private void saveLocalDeviceKey(String paramString)
    throws Throwable
  {
    if (!getSdcardState())
      return;
    Object localObject = new File(getSdcardPath(), "ShareSDK");
    if (!((File)localObject).exists())
      ((File)localObject).mkdirs();
    localObject = new File((File)localObject, ".dk");
    if (((File)localObject).exists())
      ((File)localObject).delete();
    localObject = new ObjectOutputStream(new FileOutputStream((File)localObject));
    ((ObjectOutputStream)localObject).writeObject(paramString.toCharArray());
    ((ObjectOutputStream)localObject).flush();
    ((ObjectOutputStream)localObject).close();
  }

  // ERROR //
  public String Base64AES(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_2
    //   1: aload_1
    //   2: invokestatic 156	com/mob/tools/utils/Data:AES128Encode	(Ljava/lang/String;Ljava/lang/String;)[B
    //   5: iconst_0
    //   6: invokestatic 162	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
    //   9: astore_1
    //   10: aload_1
    //   11: astore_2
    //   12: aload_1
    //   13: ldc 164
    //   15: invokevirtual 168	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   18: ifeq +12 -> 30
    //   21: aload_1
    //   22: ldc 164
    //   24: ldc 170
    //   26: invokevirtual 174	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   29: astore_2
    //   30: aload_2
    //   31: areturn
    //   32: astore_2
    //   33: aconst_null
    //   34: astore_1
    //   35: aload_2
    //   36: invokestatic 177	com/mob/tools/utils/Ln:w	(Ljava/lang/Throwable;)I
    //   39: pop
    //   40: aload_1
    //   41: areturn
    //   42: astore_2
    //   43: goto -8 -> 35
    //
    // Exception table:
    //   from	to	target	type
    //   0	10	32	java/lang/Throwable
    //   12	30	42	java/lang/Throwable
  }

  public boolean checkPermission(String paramString)
    throws Throwable
  {
    return this.context.getPackageManager().checkPermission(paramString, getPackageName()) == 0;
  }

  public String getAdvertisingID()
  {
    try
    {
      Object localObject1 = new Intent("com.google.android.gms.ads.identifier.service.START");
      ((Intent)localObject1).setPackage("com.google.android.gms");
      Object localObject2 = new GSConnection(null);
      this.context.bindService((Intent)localObject1, (ServiceConnection)localObject2, 1);
      Object localObject3 = ((GSConnection)localObject2).takeBinder();
      localObject1 = Parcel.obtain();
      localObject2 = Parcel.obtain();
      ((Parcel)localObject1).writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
      ((IBinder)localObject3).transact(1, (Parcel)localObject1, (Parcel)localObject2, 0);
      ((Parcel)localObject2).readException();
      localObject3 = ((Parcel)localObject2).readString();
      ((Parcel)localObject2).recycle();
      ((Parcel)localObject1).recycle();
      Ln.i("getAdvertisingID === %s", new Object[] { localObject3 });
      return localObject3;
    }
    catch (Throwable localThrowable)
    {
      Ln.w(localThrowable);
    }
    return null;
  }

  public String getAndroidID()
  {
    String str = Settings.Secure.getString(this.context.getContentResolver(), "android_id");
    Ln.i("getAndroidID === %s", new Object[] { str });
    return str;
  }

  public String getAppName()
  {
    String str = this.context.getApplicationInfo().name;
    if (str != null);
    int i;
    do
    {
      return str;
      i = this.context.getApplicationInfo().labelRes;
    }
    while (i <= 0);
    return this.context.getString(i);
  }

  public int getAppVersion()
  {
    try
    {
      int i = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Throwable localThrowable)
    {
      Ln.d(localThrowable);
    }
    return 0;
  }

  public String getAppVersionName()
  {
    try
    {
      String str = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionName;
      return str;
    }
    catch (Throwable localThrowable)
    {
      Ln.d(localThrowable);
    }
    return "1.0";
  }

  public String getBluetoothName()
  {
    try
    {
      Object localObject = BluetoothAdapter.getDefaultAdapter();
      if (localObject != null)
      {
        localObject = ((BluetoothAdapter)localObject).getName();
        return localObject;
      }
    }
    catch (Exception localException)
    {
      Ln.e(localException);
    }
    return null;
  }

  public String getBssid()
  {
    Object localObject = (WifiManager)getSystemService("wifi");
    if (localObject == null);
    do
    {
      return null;
      localObject = ((WifiManager)localObject).getConnectionInfo();
    }
    while (localObject == null);
    String str = ((WifiInfo)localObject).getBSSID();
    localObject = str;
    if (str == null)
      localObject = null;
    return localObject;
  }

  public String getCarrier()
  {
    Object localObject = (TelephonyManager)getSystemService("phone");
    if (localObject == null)
      localObject = "-1";
    String str;
    do
    {
      return localObject;
      str = ((TelephonyManager)localObject).getSimOperator();
      localObject = str;
    }
    while (!TextUtils.isEmpty(str));
    return "-1";
  }

  public String getCharAndNumr(int paramInt)
  {
    long l1 = System.currentTimeMillis();
    long l2 = SystemClock.elapsedRealtime();
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(l1 ^ l2);
    Random localRandom = new Random();
    int i = 0;
    if (i < paramInt)
    {
      String str;
      if (localRandom.nextInt(2) % 2 == 0)
      {
        str = "char";
        label60: if (!"char".equalsIgnoreCase(str))
          break label106;
        localStringBuffer.insert(i + 1, (char)(localRandom.nextInt(26) + 97));
      }
      while (true)
      {
        i += 1;
        break;
        str = "num";
        break label60;
        label106: localStringBuffer.insert(localStringBuffer.length(), localRandom.nextInt(10));
      }
    }
    return localStringBuffer.toString().substring(0, 40);
  }

  public String getDetailNetworkTypeForStatic()
  {
    String str2 = getNetworkType().toLowerCase();
    String str1;
    if ((TextUtils.isEmpty(str2)) || ("none".equals(str2)))
      str1 = "none";
    do
    {
      return str1;
      if (str2.startsWith("wifi"))
        return "wifi";
      if (str2.startsWith("4g"))
        return "4g";
      if (str2.startsWith("3g"))
        return "3g";
      if (str2.startsWith("2g"))
        return "2g";
      str1 = str2;
    }
    while (!str2.startsWith("bluetooth"));
    return "bluetooth";
  }

  public String getDeviceData()
  {
    return Base64AES(getModel() + "|" + getOSVersion() + "|" + getManufacturer() + "|" + getCarrier() + "|" + getScreenSize(), getDeviceKey().substring(0, 16));
  }

  public String getDeviceDataNotAES()
  {
    return getModel() + "|" + getOSVersion() + "|" + getManufacturer() + "|" + getCarrier() + "|" + getScreenSize();
  }

  public String getDeviceId()
  {
    String str2 = getMime();
    String str1 = str2;
    if (str2 == null)
      str1 = getSerialNo();
    return str1;
  }

  public String getDeviceKey()
  {
    Object localObject2 = null;
    while (true)
    {
      Object localObject1;
      try
      {
        String str1 = getLocalDeviceKey();
        if (str1 != null)
        {
          localObject2 = str1;
          return localObject2;
        }
      }
      catch (Throwable localThrowable1)
      {
        Ln.w(localThrowable1);
        localObject1 = null;
        continue;
      }
      try
      {
        localObject1 = getMacAddress();
        String str2 = getDeviceId();
        String str3 = getModel();
        localObject1 = Data.byteToHex(Data.SHA1((String)localObject1 + ":" + str2 + ":" + str3));
        localObject2 = localObject1;
        localObject1 = localObject2;
        if (TextUtils.isEmpty(localObject2))
          localObject1 = getCharAndNumr(40);
        localObject2 = localObject1;
        if (localObject1 == null)
          continue;
        try
        {
          saveLocalDeviceKey((String)localObject1);
          return localObject1;
        }
        catch (Throwable localThrowable3)
        {
          Ln.w(localThrowable3);
          return localObject1;
        }
      }
      catch (Throwable localThrowable2)
      {
        while (true)
          Ln.d(localThrowable2);
      }
    }
  }

  public ArrayList<HashMap<String, String>> getInstalledApp(boolean paramBoolean)
  {
    ArrayList localArrayList;
    try
    {
      PackageManager localPackageManager = this.context.getPackageManager();
      Object localObject = localPackageManager.getInstalledPackages(0);
      localArrayList = new ArrayList();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if ((paramBoolean) || (!isSystemApp(localPackageInfo)))
        {
          HashMap localHashMap = new HashMap();
          localHashMap.put("pkg", localPackageInfo.packageName);
          localHashMap.put("name", localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString());
          localHashMap.put("version", localPackageInfo.versionName);
          localArrayList.add(localHashMap);
        }
      }
    }
    catch (Throwable localThrowable)
    {
      Ln.w(localThrowable);
      return new ArrayList();
    }
    return localArrayList;
  }

  public String getLine1Number()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)getSystemService("phone");
    if (localTelephonyManager == null)
      return "-1";
    return localTelephonyManager.getLine1Number();
  }

  public String getMacAddress()
  {
    Object localObject = (WifiManager)getSystemService("wifi");
    if (localObject == null);
    do
    {
      return null;
      localObject = ((WifiManager)localObject).getConnectionInfo();
    }
    while (localObject == null);
    String str = ((WifiInfo)localObject).getMacAddress();
    localObject = str;
    if (str == null)
      localObject = null;
    return localObject;
  }

  public String getManufacturer()
  {
    return Build.MANUFACTURER;
  }

  public String getMime()
  {
    Object localObject = (TelephonyManager)getSystemService("phone");
    String str;
    if (localObject == null)
      str = null;
    do
    {
      return str;
      str = ((TelephonyManager)localObject).getDeviceId();
      localObject = "";
      if (str != null)
        localObject = new String(str).replace("0", "");
    }
    while ((str != null) && (((String)localObject).length() > 0));
    return null;
  }

  public String getModel()
  {
    return Build.MODEL;
  }

  public String getNetworkOperator()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)getSystemService("phone");
    if (localTelephonyManager == null)
      return null;
    return localTelephonyManager.getNetworkOperator();
  }

  public String getNetworkType()
  {
    Object localObject = (ConnectivityManager)getSystemService("connectivity");
    if (localObject == null)
      return "none";
    localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
    if ((localObject == null) || (!((NetworkInfo)localObject).isAvailable()))
      return "none";
    int i = ((NetworkInfo)localObject).getType();
    switch (i)
    {
    case 2:
    case 3:
    case 4:
    case 5:
    default:
      return String.valueOf(i);
    case 1:
      return "wifi";
    case 0:
      if (is4GMobileNetwork())
        return "4G";
      if (isFastMobileNetwork())
        return "3G";
      return "2G";
    case 7:
      return "bluetooth";
    case 8:
      return "dummy";
    case 9:
      return "ethernet";
    case 6:
    }
    return "wimax";
  }

  public String getNetworkTypeForStatic()
  {
    String str = getNetworkType().toLowerCase();
    if ((TextUtils.isEmpty(str)) || ("none".equals(str)))
      return "none";
    if ((str.startsWith("4g")) || (str.startsWith("3g")) || (str.startsWith("2g")))
      return "cell";
    if (str.startsWith("wifi"))
      return "wifi";
    return "other";
  }

  public String getOSLanguage()
  {
    return Locale.getDefault().getLanguage();
  }

  public String getOSVersion()
  {
    return String.valueOf(Build.VERSION.SDK_INT);
  }

  public String getOSVersionName()
  {
    return Build.VERSION.RELEASE;
  }

  public String getPackageName()
  {
    return this.context.getPackageName();
  }

  public int getPlatformCode()
  {
    return 1;
  }

  public JSONArray getRunningApp()
  {
    JSONArray localJSONArray = new JSONArray();
    Object localObject = (ActivityManager)getSystemService("activity");
    if (localObject == null)
      return localJSONArray;
    localObject = ((ActivityManager)localObject).getRunningAppProcesses();
    if (localObject == null)
      return localJSONArray;
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
      localJSONArray.put(((ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next()).processName);
    return localJSONArray;
  }

  public String getRunningAppStr()
    throws JSONException
  {
    JSONArray localJSONArray = getRunningApp();
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < localJSONArray.length())
    {
      if (i > 0)
        localStringBuilder.append(',');
      localStringBuilder.append(String.valueOf(localJSONArray.get(i)));
      i += 1;
    }
    return localStringBuilder.toString();
  }

  public String getSSID()
  {
    Object localObject = (WifiManager)getSystemService("wifi");
    if (localObject == null);
    do
    {
      return null;
      localObject = ((WifiManager)localObject).getConnectionInfo();
    }
    while (localObject == null);
    String str = ((WifiInfo)localObject).getSSID();
    localObject = str;
    if (str == null)
      localObject = null;
    return localObject;
  }

  public String getScreenSize()
  {
    int[] arrayOfInt = R.getScreenSize(this.context);
    if (this.context.getResources().getConfiguration().orientation == 1)
      return arrayOfInt[0] + "x" + arrayOfInt[1];
    return arrayOfInt[1] + "x" + arrayOfInt[0];
  }

  public String getSdcardPath()
  {
    return Environment.getExternalStorageDirectory().getAbsolutePath();
  }

  public boolean getSdcardState()
  {
    try
    {
      boolean bool = "mounted".equals(Environment.getExternalStorageState());
      return bool;
    }
    catch (Throwable localThrowable)
    {
      Ln.w(localThrowable);
    }
    return false;
  }

  public String getSerialNo()
  {
    if (Build.VERSION.SDK_INT >= 9)
      try
      {
        Object localObject = Class.forName("android.os.SystemProperties");
        localObject = (String)((Class)localObject).getMethod("get", new Class[] { String.class, String.class }).invoke(localObject, new Object[] { "ro.serialno", "unknown" });
        return localObject;
      }
      catch (Throwable localThrowable)
      {
        Ln.d(localThrowable);
        return null;
      }
    return null;
  }

  public String getSignMD5()
  {
    try
    {
      String str = Data.MD5(this.context.getPackageManager().getPackageInfo(getPackageName(), 64).signatures[0].toByteArray());
      return str;
    }
    catch (Exception localException)
    {
      Ln.e(localException);
    }
    return null;
  }

  public String getSimSerialNumber()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)getSystemService("phone");
    if (localTelephonyManager == null)
      return "-1";
    return localTelephonyManager.getSimSerialNumber();
  }

  // ERROR //
  public String getTopTaskPackageName()
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc_w 716
    //   4: invokevirtual 718	com/mob/tools/utils/DeviceHelper:checkPermission	(Ljava/lang/String;)Z
    //   7: istore_1
    //   8: iload_1
    //   9: ifeq +60 -> 69
    //   12: aload_0
    //   13: ldc_w 601
    //   16: invokespecial 99	com/mob/tools/utils/DeviceHelper:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   19: checkcast 603	android/app/ActivityManager
    //   22: astore_2
    //   23: aload_2
    //   24: ifnonnull +16 -> 40
    //   27: aconst_null
    //   28: areturn
    //   29: astore_2
    //   30: aload_2
    //   31: invokestatic 177	com/mob/tools/utils/Ln:w	(Ljava/lang/Throwable;)I
    //   34: pop
    //   35: iconst_0
    //   36: istore_1
    //   37: goto -29 -> 8
    //   40: aload_2
    //   41: iconst_1
    //   42: invokevirtual 721	android/app/ActivityManager:getRunningTasks	(I)Ljava/util/List;
    //   45: iconst_0
    //   46: invokeinterface 722 2 0
    //   51: checkcast 724	android/app/ActivityManager$RunningTaskInfo
    //   54: getfield 728	android/app/ActivityManager$RunningTaskInfo:topActivity	Landroid/content/ComponentName;
    //   57: invokevirtual 731	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   60: astore_2
    //   61: aload_2
    //   62: areturn
    //   63: astore_2
    //   64: aload_2
    //   65: invokestatic 177	com/mob/tools/utils/Ln:w	(Ljava/lang/Throwable;)I
    //   68: pop
    //   69: aconst_null
    //   70: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	8	29	java/lang/Throwable
    //   12	23	63	java/lang/Throwable
    //   40	61	63	java/lang/Throwable
  }

  public void hideSoftInput(View paramView)
  {
    Object localObject = getSystemService("input_method");
    if (localObject == null)
      return;
    ((InputMethodManager)localObject).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
  }

  public boolean isMainProcess(Context paramContext)
  {
    if (paramContext == null)
      return false;
    int i = Process.myPid();
    Object localObject = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
    do
    {
      if (!((Iterator)localObject).hasNext())
        break;
      localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
    }
    while (localRunningAppProcessInfo.pid != i);
    for (localObject = localRunningAppProcessInfo.processName; ; localObject = null)
      return paramContext.getPackageName().equals(localObject);
  }

  public boolean isRooted()
  {
    return false;
  }

  public void showSoftInput(View paramView)
  {
    Object localObject = getSystemService("input_method");
    if (localObject == null)
      return;
    ((InputMethodManager)localObject).toggleSoftInputFromWindow(paramView.getWindowToken(), 2, 0);
  }

  private class GSConnection
    implements ServiceConnection
  {
    boolean got = false;
    private final BlockingQueue<IBinder> iBinders = new LinkedBlockingQueue();

    private GSConnection()
    {
    }

    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      try
      {
        this.iBinders.put(paramIBinder);
        return;
      }
      catch (Throwable paramComponentName)
      {
        Ln.w(paramComponentName);
      }
    }

    public void onServiceDisconnected(ComponentName paramComponentName)
    {
    }

    public IBinder takeBinder()
      throws InterruptedException
    {
      if (this.got)
        throw new IllegalStateException();
      this.got = true;
      return (IBinder)this.iBinders.poll(1500L, TimeUnit.MILLISECONDS);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.utils.DeviceHelper
 * JD-Core Version:    0.6.2
 */