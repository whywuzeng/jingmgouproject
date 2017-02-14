package org.android.agoo.proxy;

import android.content.Context;
import android.util.Log;
import com.umeng.message.proguard.bM;

public class ProxyFactroy
{
  private static final String TAG = "ProxyFactroy";
  private static final String classPrefix = "update.";

  private static <T> T getBaseService(Context paramContext, String paramString, T paramT)
  {
    try
    {
      bM.d(paramContext, "0", "push");
    }
    catch (Throwable paramContext)
    {
      try
      {
        while (true)
        {
          Log.v("ProxyFactroy", "getInstance[base] successfully");
          paramContext = Class.forName(paramString).newInstance();
          return paramContext;
          paramContext = paramContext;
          Log.w("ProxyFactroy", "setAutoUpdate", paramContext);
        }
      }
      catch (Throwable paramContext)
      {
        Log.w("ProxyFactroy", "instance_base", paramContext);
      }
    }
    return paramT;
  }

  // ERROR //
  public static final <T> T getInstance(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_3
    //   3: astore_2
    //   4: aload_0
    //   5: invokestatic 66	org/android/Config:isEnableSwitchConfig	(Landroid/content/Context;)Z
    //   8: ifeq +91 -> 99
    //   11: aload_3
    //   12: astore_2
    //   13: aload_0
    //   14: invokestatic 69	org/android/Config:ifNeedNotAutoUpdate	(Landroid/content/Context;)Z
    //   17: ifeq +82 -> 99
    //   20: aload_0
    //   21: ldc 24
    //   23: invokestatic 75	org/android/du/DuSdk:getUpdate	(Landroid/content/Context;Ljava/lang/String;)Lorg/android/du/Update;
    //   26: astore 4
    //   28: aload_3
    //   29: astore_2
    //   30: aload 4
    //   32: ifnull +67 -> 99
    //   35: aload 4
    //   37: aload_1
    //   38: new 77	java/lang/StringBuilder
    //   41: dup
    //   42: invokespecial 78	java/lang/StringBuilder:<init>	()V
    //   45: ldc 11
    //   47: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: aload_1
    //   51: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   57: invokevirtual 92	org/android/du/Update:getBean	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;
    //   60: astore_3
    //   61: aload_3
    //   62: astore_2
    //   63: aload_3
    //   64: ifnull +35 -> 99
    //   67: ldc 8
    //   69: new 77	java/lang/StringBuilder
    //   72: dup
    //   73: invokespecial 78	java/lang/StringBuilder:<init>	()V
    //   76: ldc 94
    //   78: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: aload_1
    //   82: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   85: ldc 96
    //   87: invokevirtual 82	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   90: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   93: invokestatic 38	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   96: pop
    //   97: aload_3
    //   98: astore_2
    //   99: aload_2
    //   100: ifnull +16 -> 116
    //   103: aload_2
    //   104: astore_3
    //   105: aload_0
    //   106: invokestatic 100	org/android/Config:getLastAppVersion	(Landroid/content/Context;)I
    //   109: aload_0
    //   110: invokestatic 103	org/android/Config:getAppVersion	(Landroid/content/Context;)I
    //   113: if_icmpeq +18 -> 131
    //   116: aload_0
    //   117: aload_1
    //   118: aload_2
    //   119: invokestatic 105	org/android/agoo/proxy/ProxyFactroy:getBaseService	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;
    //   122: astore_3
    //   123: aload_0
    //   124: ldc 24
    //   126: ldc 22
    //   128: invokestatic 108	com/umeng/message/proguard/bM:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
    //   131: aload_3
    //   132: areturn
    //   133: astore_3
    //   134: aconst_null
    //   135: astore_2
    //   136: ldc 8
    //   138: ldc 110
    //   140: aload_3
    //   141: invokestatic 54	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   144: pop
    //   145: goto -46 -> 99
    //   148: astore 4
    //   150: aload_3
    //   151: astore_2
    //   152: aload 4
    //   154: astore_3
    //   155: goto -19 -> 136
    //
    // Exception table:
    //   from	to	target	type
    //   4	11	133	java/lang/Throwable
    //   13	28	133	java/lang/Throwable
    //   35	61	133	java/lang/Throwable
    //   67	97	148	java/lang/Throwable
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.proxy.ProxyFactroy
 * JD-Core Version:    0.6.2
 */