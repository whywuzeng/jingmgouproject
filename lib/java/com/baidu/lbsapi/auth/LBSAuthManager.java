package com.baidu.lbsapi.auth;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import com.baidu.android.bbalbs.common.util.CommonParam;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class LBSAuthManager
{
  private static final String API_KEY = "com.baidu.lbsapi.API_KEY";
  private static final int AUTH_TIMEOUT = 3600000;
  protected static final String AUTH_URL = "https://sapi.map.baidu.com/sdkcs/verify";
  private static final String CACHE_FILE_NAME = "authStatus";
  private static final String CACHE_KEY = "status";
  public static final int CODE_AUTHENTICATE_SUCC = 0;
  public static final int CODE_AUTHENTICATING = 602;
  protected static final int CODE_INNER_ERROR = -1;
  public static final int CODE_UNAUTHENTICATE = 601;
  public static final String VERSION = "1.0.4";
  private static LBSAuthManager instance;
  private static Hashtable<String, LBSAuthManagerListener> listenerTable = new Hashtable();
  private static int mAuthCounter;
  private static Context mContext;
  private static m mThreadLooper = null;
  private boolean debug = true;
  private final Handler mHandler = new i(this, Looper.getMainLooper());
  private c mHttpAsyncTask = null;
  private f mHttpSyncTask = null;

  static
  {
    mAuthCounter = 0;
  }

  private LBSAuthManager(Context paramContext)
  {
    mContext = paramContext;
    if ((mThreadLooper != null) && (!mThreadLooper.isAlive()))
      mThreadLooper = null;
    createAuthThread();
  }

  private int authenticate(LBSAuthManagerListener paramLBSAuthManagerListener)
  {
    return authenticate(false, "", null, paramLBSAuthManagerListener);
  }

  private int authenticate(boolean paramBoolean, Hashtable<String, String> paramHashtable, LBSAuthManagerListener paramLBSAuthManagerListener)
  {
    return authenticate(paramBoolean, "", paramHashtable, paramLBSAuthManagerListener);
  }

  private void callbackToMainThread(String paramString1, String paramString2)
  {
    Object localObject = paramString1;
    if (paramString1 == null);
    try
    {
      localObject = getAuthString();
      paramString1 = this.mHandler.obtainMessage();
      int j = -1;
      int i = j;
      try
      {
        localObject = new JSONObject((String)localObject);
        i = j;
        if (!((JSONObject)localObject).has("status"))
        {
          i = j;
          ((JSONObject)localObject).put("status", -1);
        }
        i = j;
        if (!((JSONObject)localObject).has("current"))
        {
          i = j;
          ((JSONObject)localObject).put("current", System.currentTimeMillis());
        }
        i = j;
        saveAuthString(((JSONObject)localObject).toString());
        i = j;
        if (((JSONObject)localObject).has("current"))
        {
          i = j;
          ((JSONObject)localObject).remove("current");
        }
        i = j;
        j = ((JSONObject)localObject).getInt("status");
        i = j;
        paramString1.what = j;
        i = j;
        paramString1.obj = ((JSONObject)localObject).toString();
        i = j;
        localObject = new Bundle();
        i = j;
        ((Bundle)localObject).putString("listenerKey", paramString2);
        i = j;
        paramString1.setData((Bundle)localObject);
        i = j;
        this.mHandler.sendMessage(paramString1);
        mThreadLooper.c();
        mAuthCounter -= 1;
        if (a.a)
          a.a("httpRequest called mAuthCounter-- = " + mAuthCounter);
        if (mAuthCounter == 0)
        {
          mThreadLooper.a();
          if (mThreadLooper != null)
            mThreadLooper = null;
        }
        return;
      }
      catch (JSONException localJSONException)
      {
        while (true)
        {
          localJSONException.printStackTrace();
          paramString1.what = i;
          paramString1.obj = new JSONObject();
          Bundle localBundle = new Bundle();
          localBundle.putString("listenerKey", paramString2);
          paramString1.setData(localBundle);
          this.mHandler.sendMessage(paramString1);
        }
      }
    }
    finally
    {
    }
    throw paramString1;
  }

  private boolean checkAkChanged(String paramString)
  {
    String str = getPublicKey(mContext, paramString);
    Object localObject = getAuthString();
    paramString = "";
    try
    {
      localObject = new JSONObject((String)localObject);
      if (!((JSONObject)localObject).has("ak"))
        return true;
      localObject = ((JSONObject)localObject).getString("ak");
      paramString = (String)localObject;
      if ((str == null) || (paramString == null) || (str.equals(paramString)))
        return false;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        localJSONException.printStackTrace();
    }
    return true;
  }

  private void createAuthThread()
  {
    try
    {
      if (mThreadLooper == null)
      {
        mThreadLooper = new m("auth");
        mThreadLooper.start();
      }
      while (true)
      {
        if (mThreadLooper.a != null)
          return;
        try
        {
          if (a.a)
            a.a("wait for create auth thread.");
          Thread.sleep(3L);
        }
        catch (InterruptedException localInterruptedException)
        {
          localInterruptedException.printStackTrace();
        }
      }
    }
    finally
    {
    }
  }

  private String getAuthString()
  {
    return mContext.getSharedPreferences("authStatus_" + getCurProcessName(mContext), 0).getString("status", "{\"status\":601}");
  }

  private String getCurProcessName(Context paramContext)
  {
    int i = Process.myPid();
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
    do
    {
      if (!paramContext.hasNext())
        return null;
      localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
    }
    while (localRunningAppProcessInfo.pid != i);
    return localRunningAppProcessInfo.processName;
  }

  public static LBSAuthManager getInstance(Context paramContext)
  {
    try
    {
      if (instance == null)
        instance = new LBSAuthManager(paramContext);
      while (true)
      {
        paramContext = instance;
        return paramContext;
        mContext = paramContext;
      }
    }
    finally
    {
    }
    throw paramContext;
  }

  private String getPublicKey(Context paramContext, String paramString)
  {
    String str2 = "";
    Object localObject = paramContext.getPackageName();
    String str1 = str2;
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo((String)localObject, 128);
      str1 = str2;
      if (paramContext.metaData == null)
      {
        str1 = str2;
        localObject = (LBSAuthManagerListener)listenerTable.get(paramString);
        paramContext = str2;
        if (localObject != null)
        {
          str1 = str2;
          ((LBSAuthManagerListener)localObject).onAuthResult(-1, ErrorMessage.a("AndroidManifest.xml的application中没有meta-data标签"));
          return "";
        }
      }
      else
      {
        str1 = str2;
        str2 = paramContext.metaData.getString("com.baidu.lbsapi.API_KEY");
        if (str2 != null)
        {
          str1 = str2;
          paramContext = str2;
          if (!str2.equals(""));
        }
        else
        {
          str1 = str2;
          localObject = (LBSAuthManagerListener)listenerTable.get(paramString);
          paramContext = str2;
          if (localObject != null)
          {
            str1 = str2;
            ((LBSAuthManagerListener)localObject).onAuthResult(-1, ErrorMessage.a("无法在AndroidManifest.xml中获取com.baidu.android.lbs.API_KEY的值"));
            return str2;
          }
        }
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramString = (LBSAuthManagerListener)listenerTable.get(paramString);
      paramContext = str1;
      if (paramString != null)
      {
        paramString.onAuthResult(-1, ErrorMessage.a("无法在AndroidManifest.xml中获取com.baidu.android.lbs.API_KEY的值"));
        paramContext = str1;
      }
    }
    return paramContext;
  }

  private int parseAuthMessage(String paramString)
  {
    int i = -1;
    int j = i;
    int k;
    try
    {
      paramString = new JSONObject(paramString);
      j = i;
      if (!paramString.has("status"))
      {
        j = i;
        paramString.put("status", -1);
      }
      j = i;
      k = paramString.getInt("status");
      i = k;
      j = k;
      long l1;
      long l2;
      if (paramString.has("current"))
      {
        i = k;
        if (k == 0)
        {
          j = k;
          l1 = paramString.getLong("current");
          j = k;
          l2 = System.currentTimeMillis();
          j = k;
          if ((l2 - l1) / 3600000.0D < 24.0D)
            break label171;
          i = 601;
        }
      }
      while (true)
      {
        j = i;
        k = i;
        if (!paramString.has("current"))
          break;
        k = i;
        if (i != 602)
          break;
        j = i;
        l1 = paramString.getLong("current");
        j = i;
        k = i;
        if ((System.currentTimeMillis() - l1) / 1000L <= 180.0D)
          break;
        return 601;
        label171: j = k;
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        j = k;
        boolean bool = localSimpleDateFormat.format(Long.valueOf(l2)).equals(localSimpleDateFormat.format(Long.valueOf(l1)));
        i = k;
        if (!bool)
          i = 601;
      }
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
      k = j;
    }
    return k;
  }

  private void saveAuthString(String paramString)
  {
    mContext.getSharedPreferences("authStatus_" + getCurProcessName(mContext), 0).edit().putString("status", paramString).commit();
  }

  private void sendAuthRequest(boolean paramBoolean, String paramString1, Hashtable<String, String> paramHashtable, String paramString2)
  {
    String str = getPublicKey(mContext, paramString2);
    if ((str == null) || (str.equals("")))
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("url", "https://sapi.map.baidu.com/sdkcs/verify");
    localHashMap.put("output", "json");
    localHashMap.put("ak", str);
    localHashMap.put("mcode", b.a(mContext));
    localHashMap.put("from", "lbs_yunsdk");
    if ((paramHashtable != null) && (paramHashtable.size() > 0))
      paramHashtable = paramHashtable.entrySet().iterator();
    while (true)
    {
      if (!paramHashtable.hasNext())
        paramHashtable = "";
      try
      {
        str = CommonParam.a(mContext);
        paramHashtable = str;
        label140: if (!TextUtils.isEmpty(paramHashtable))
        {
          localHashMap.put("cuid", paramHashtable);
          label157: localHashMap.put("pcn", mContext.getPackageName());
          localHashMap.put("version", "1.0.4");
          paramHashtable = "";
        }
        try
        {
          str = b.c(mContext);
          paramHashtable = str;
          label197: if (!TextUtils.isEmpty(paramHashtable))
          {
            localHashMap.put("macaddr", paramHashtable);
            label214: paramHashtable = "";
          }
          try
          {
            str = b.a();
            paramHashtable = str;
            label225: if (!TextUtils.isEmpty(paramHashtable))
            {
              localHashMap.put("language", paramHashtable);
              label242: if (paramBoolean)
                if (!paramBoolean)
                  break label414;
            }
            label414: for (paramHashtable = "1"; ; paramHashtable = "0")
            {
              localHashMap.put("force", paramHashtable);
              localHashMap.put("from_service", paramString1);
              this.mHttpAsyncTask = new c(mContext);
              this.mHttpAsyncTask.a(localHashMap, new k(this, paramString2));
              return;
              Object localObject = (Map.Entry)paramHashtable.next();
              str = (String)((Map.Entry)localObject).getKey();
              localObject = (String)((Map.Entry)localObject).getValue();
              if ((TextUtils.isEmpty(str)) || (TextUtils.isEmpty((CharSequence)localObject)))
                break;
              localHashMap.put(str, localObject);
              break;
              localHashMap.put("cuid", "");
              break label157;
              localHashMap.put("macaddr", "");
              break label214;
              localHashMap.put("language", "");
              break label242;
            }
          }
          catch (Exception localException1)
          {
            break label225;
          }
        }
        catch (Exception localException2)
        {
          break label197;
        }
      }
      catch (Exception localException3)
      {
        break label140;
      }
    }
  }

  private void sendAuthRequests(boolean paramBoolean, String paramString1, Hashtable<String, String> paramHashtable, String[] paramArrayOfString, String paramString2)
  {
    String str = getPublicKey(mContext, paramString2);
    if ((str == null) || (str.equals("")))
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("url", "https://sapi.map.baidu.com/sdkcs/verify");
    localHashMap.put("output", "json");
    localHashMap.put("ak", str);
    localHashMap.put("from", "lbs_yunsdk");
    if ((paramHashtable != null) && (paramHashtable.size() > 0))
      paramHashtable = paramHashtable.entrySet().iterator();
    while (true)
    {
      if (!paramHashtable.hasNext())
        paramHashtable = "";
      try
      {
        str = CommonParam.a(mContext);
        paramHashtable = str;
        label125: if (!TextUtils.isEmpty(paramHashtable))
        {
          localHashMap.put("cuid", paramHashtable);
          localHashMap.put("pcn", mContext.getPackageName());
          localHashMap.put("version", "1.0.4");
          paramHashtable = "";
        }
        try
        {
          label142: str = b.c(mContext);
          paramHashtable = str;
          label182: if (!TextUtils.isEmpty(paramHashtable))
          {
            localHashMap.put("macaddr", paramHashtable);
            label199: paramHashtable = "";
          }
          try
          {
            str = b.a();
            paramHashtable = str;
            label210: if (!TextUtils.isEmpty(paramHashtable))
            {
              localHashMap.put("language", paramHashtable);
              label227: if (paramBoolean)
                if (!paramBoolean)
                  break label401;
            }
            label401: for (paramHashtable = "1"; ; paramHashtable = "0")
            {
              localHashMap.put("force", paramHashtable);
              localHashMap.put("from_service", paramString1);
              this.mHttpSyncTask = new f(mContext);
              this.mHttpSyncTask.a(localHashMap, paramArrayOfString, new l(this, paramString2));
              return;
              Object localObject = (Map.Entry)paramHashtable.next();
              str = (String)((Map.Entry)localObject).getKey();
              localObject = (String)((Map.Entry)localObject).getValue();
              if ((TextUtils.isEmpty(str)) || (TextUtils.isEmpty((CharSequence)localObject)))
                break;
              localHashMap.put(str, localObject);
              break;
              localHashMap.put("cuid", "");
              break label142;
              localHashMap.put("macaddr", "");
              break label199;
              localHashMap.put("language", "");
              break label227;
            }
          }
          catch (Exception localException1)
          {
            break label210;
          }
        }
        catch (Exception localException2)
        {
          break label182;
        }
      }
      catch (Exception localException3)
      {
        break label125;
      }
    }
  }

  // ERROR //
  public int authenticate(boolean paramBoolean, String paramString, Hashtable<String, String> paramHashtable, LBSAuthManagerListener paramLBSAuthManagerListener)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: new 219	java/lang/StringBuilder
    //   6: dup
    //   7: invokestatic 167	java/lang/System:currentTimeMillis	()J
    //   10: invokestatic 518	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   13: invokespecial 222	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   16: invokevirtual 227	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   19: astore 6
    //   21: aload 4
    //   23: ifnull +14 -> 37
    //   26: getstatic 62	com/baidu/lbsapi/auth/LBSAuthManager:listenerTable	Ljava/util/Hashtable;
    //   29: aload 6
    //   31: aload 4
    //   33: invokevirtual 519	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   36: pop
    //   37: aload_0
    //   38: getstatic 86	com/baidu/lbsapi/auth/LBSAuthManager:mContext	Landroid/content/Context;
    //   41: aload 6
    //   43: invokespecial 239	com/baidu/lbsapi/auth/LBSAuthManager:getPublicKey	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   46: astore 4
    //   48: aload 4
    //   50: ifnull +13 -> 63
    //   53: aload 4
    //   55: ldc 129
    //   57: invokevirtual 251	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   60: ifeq +8 -> 68
    //   63: ldc 2
    //   65: monitorexit
    //   66: iconst_m1
    //   67: ireturn
    //   68: getstatic 55	com/baidu/lbsapi/auth/LBSAuthManager:mAuthCounter	I
    //   71: iconst_1
    //   72: iadd
    //   73: putstatic 55	com/baidu/lbsapi/auth/LBSAuthManager:mAuthCounter	I
    //   76: getstatic 217	com/baidu/lbsapi/auth/a:a	Z
    //   79: ifeq +25 -> 104
    //   82: new 219	java/lang/StringBuilder
    //   85: dup
    //   86: ldc_w 521
    //   89: invokespecial 222	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   92: getstatic 55	com/baidu/lbsapi/auth/LBSAuthManager:mAuthCounter	I
    //   95: invokevirtual 226	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   98: invokevirtual 227	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   101: invokestatic 229	com/baidu/lbsapi/auth/a:a	(Ljava/lang/String;)V
    //   104: aload_0
    //   105: invokespecial 141	com/baidu/lbsapi/auth/LBSAuthManager:getAuthString	()Ljava/lang/String;
    //   108: astore 4
    //   110: getstatic 217	com/baidu/lbsapi/auth/a:a	Z
    //   113: ifeq +24 -> 137
    //   116: new 219	java/lang/StringBuilder
    //   119: dup
    //   120: ldc_w 523
    //   123: invokespecial 222	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   126: aload 4
    //   128: invokevirtual 281	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   131: invokevirtual 227	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   134: invokestatic 229	com/baidu/lbsapi/auth/a:a	(Ljava/lang/String;)V
    //   137: aload_0
    //   138: aload 4
    //   140: invokespecial 525	com/baidu/lbsapi/auth/LBSAuthManager:parseAuthMessage	(Ljava/lang/String;)I
    //   143: istore 5
    //   145: iload 5
    //   147: sipush 601
    //   150: if_icmpne +25 -> 175
    //   153: aload_0
    //   154: new 149	org/json/JSONObject
    //   157: dup
    //   158: invokespecial 235	org/json/JSONObject:<init>	()V
    //   161: ldc 20
    //   163: sipush 602
    //   166: invokevirtual 159	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   169: invokevirtual 173	org/json/JSONObject:toString	()Ljava/lang/String;
    //   172: invokespecial 176	com/baidu/lbsapi/auth/LBSAuthManager:saveAuthString	(Ljava/lang/String;)V
    //   175: aload_0
    //   176: invokespecial 95	com/baidu/lbsapi/auth/LBSAuthManager:createAuthThread	()V
    //   179: getstatic 217	com/baidu/lbsapi/auth/a:a	Z
    //   182: ifeq +28 -> 210
    //   185: new 219	java/lang/StringBuilder
    //   188: dup
    //   189: ldc_w 527
    //   192: invokespecial 222	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   195: getstatic 53	com/baidu/lbsapi/auth/LBSAuthManager:mThreadLooper	Lcom/baidu/lbsapi/auth/m;
    //   198: getfield 261	com/baidu/lbsapi/auth/m:a	Landroid/os/Handler;
    //   201: invokevirtual 530	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   204: invokevirtual 227	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   207: invokestatic 229	com/baidu/lbsapi/auth/a:a	(Ljava/lang/String;)V
    //   210: getstatic 53	com/baidu/lbsapi/auth/LBSAuthManager:mThreadLooper	Lcom/baidu/lbsapi/auth/m;
    //   213: getfield 261	com/baidu/lbsapi/auth/m:a	Landroid/os/Handler;
    //   216: new 532	com/baidu/lbsapi/auth/j
    //   219: dup
    //   220: aload_0
    //   221: iload 5
    //   223: iload_1
    //   224: aload 6
    //   226: aload_2
    //   227: aload_3
    //   228: invokespecial 535	com/baidu/lbsapi/auth/j:<init>	(Lcom/baidu/lbsapi/auth/LBSAuthManager;IZLjava/lang/String;Ljava/lang/String;Ljava/util/Hashtable;)V
    //   231: invokevirtual 539	android/os/Handler:post	(Ljava/lang/Runnable;)Z
    //   234: pop
    //   235: ldc 2
    //   237: monitorexit
    //   238: iload 5
    //   240: ireturn
    //   241: astore_2
    //   242: ldc 2
    //   244: monitorexit
    //   245: aload_2
    //   246: athrow
    //   247: astore 4
    //   249: aload 4
    //   251: invokevirtual 234	org/json/JSONException:printStackTrace	()V
    //   254: goto -79 -> 175
    //
    // Exception table:
    //   from	to	target	type
    //   3	21	241	finally
    //   26	37	241	finally
    //   37	48	241	finally
    //   53	63	241	finally
    //   63	66	241	finally
    //   68	104	241	finally
    //   104	137	241	finally
    //   137	145	241	finally
    //   153	175	241	finally
    //   175	210	241	finally
    //   210	238	241	finally
    //   242	245	241	finally
    //   249	254	241	finally
    //   153	175	247	org/json/JSONException
  }

  public String getPublicKey(Context paramContext)
    throws PackageManager.NameNotFoundException
  {
    String str = paramContext.getPackageName();
    return paramContext.getPackageManager().getApplicationInfo(str, 128).metaData.getString("com.baidu.lbsapi.API_KEY");
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.lbsapi.auth.LBSAuthManager
 * JD-Core Version:    0.6.2
 */