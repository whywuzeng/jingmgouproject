package org.android.du;

import android.content.Context;
import android.util.Log;
import com.umeng.message.proguard.bM;
import com.umeng.message.proguard.bO;
import java.io.File;
import java.security.cert.Certificate;
import java.util.HashMap;
import org.android.agoo.IUpdateService;

public class DuSdk
{
  private static final String AGOO_COMMAND_LOADUPDATEJAR = "loadUpdateJar";
  private static final String TAG = "DuSdk";
  private static final String className = "org.android.agoo.impl.UpdateService";
  private static final String classPrefix = "update.";
  static HashMap<String, Update> maps = new HashMap();

  public static final boolean checkUpdateJar(Context paramContext)
  {
    while (true)
    {
      try
      {
        Object localObject1 = new File(bM.g(paramContext, "push"));
        if ((localObject1 == null) || (!((File)localObject1).exists()))
          break;
        localObject1 = bO.a((File)localObject1, paramContext);
        if (localObject1 != null)
        {
          i = ((Certificate)localObject1).getPublicKey().hashCode();
          try
          {
            localObject1 = (IUpdateService)Class.forName("update.org.android.agoo.impl.UpdateService").newInstance();
            Object localObject2 = localObject1;
            if (localObject1 == null)
              localObject2 = (IUpdateService)Class.forName("org.android.agoo.impl.UpdateService").newInstance();
            boolean bool = ((IUpdateService)localObject2).checkUpdateJar(i, bM.c(paramContext, "push"));
            Log.i("DuSdk", "checkUpdateJar is " + bool);
            return bool;
          }
          catch (Throwable localThrowable)
          {
            IUpdateService localIUpdateService = (IUpdateService)Class.forName("org.android.agoo.impl.UpdateService").newInstance();
            continue;
          }
        }
      }
      catch (Throwable paramContext)
      {
        return false;
      }
      int i = 0;
    }
    return false;
  }

  public static void destroy()
  {
    try
    {
      maps.clear();
      return;
    }
    catch (Throwable localThrowable)
    {
      Log.w("DuSdk", "destroy", localThrowable);
    }
  }

  // ERROR //
  public static Update getUpdate(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 29	org/android/du/DuSdk:maps	Ljava/util/HashMap;
    //   6: aload_1
    //   7: invokevirtual 125	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   10: ifeq +19 -> 29
    //   13: getstatic 29	org/android/du/DuSdk:maps	Ljava/util/HashMap;
    //   16: aload_1
    //   17: invokevirtual 129	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   20: checkcast 131	org/android/du/Update
    //   23: astore_1
    //   24: ldc 2
    //   26: monitorexit
    //   27: aload_1
    //   28: areturn
    //   29: aload_0
    //   30: ifnull +83 -> 113
    //   33: aload_1
    //   34: ifnull +79 -> 113
    //   37: aload_0
    //   38: invokestatic 133	org/android/du/DuSdk:checkUpdateJar	(Landroid/content/Context;)Z
    //   41: ifeq +72 -> 113
    //   44: new 131	org/android/du/Update
    //   47: dup
    //   48: aload_0
    //   49: aload_1
    //   50: invokespecial 136	org/android/du/Update:<init>	(Landroid/content/Context;Ljava/lang/String;)V
    //   53: astore_2
    //   54: getstatic 29	org/android/du/DuSdk:maps	Ljava/util/HashMap;
    //   57: aload_1
    //   58: aload_2
    //   59: invokevirtual 140	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   62: pop
    //   63: aload_0
    //   64: ldc 8
    //   66: invokestatic 146	org/android/agoo/intent/IntentUtil:createComandIntent	(Landroid/content/Context;Ljava/lang/String;)Landroid/content/Intent;
    //   69: astore_3
    //   70: aload_2
    //   71: astore_1
    //   72: aload_3
    //   73: ifnull -49 -> 24
    //   76: aload_0
    //   77: aload_3
    //   78: invokevirtual 152	android/content/Context:sendBroadcast	(Landroid/content/Intent;)V
    //   81: aload_2
    //   82: astore_1
    //   83: goto -59 -> 24
    //   86: astore_0
    //   87: aload_2
    //   88: astore_1
    //   89: ldc 11
    //   91: ldc 153
    //   93: aload_0
    //   94: invokestatic 119	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   97: pop
    //   98: goto -74 -> 24
    //   101: astore_0
    //   102: ldc 2
    //   104: monitorexit
    //   105: aload_0
    //   106: athrow
    //   107: astore_0
    //   108: aconst_null
    //   109: astore_1
    //   110: goto -21 -> 89
    //   113: aconst_null
    //   114: astore_1
    //   115: goto -91 -> 24
    //
    // Exception table:
    //   from	to	target	type
    //   54	70	86	java/lang/Throwable
    //   76	81	86	java/lang/Throwable
    //   3	24	101	finally
    //   37	54	101	finally
    //   54	70	101	finally
    //   76	81	101	finally
    //   89	98	101	finally
    //   3	24	107	java/lang/Throwable
    //   37	54	107	java/lang/Throwable
  }

  public static void setting(Context paramContext, String paramString1, String paramString2)
  {
  }

  public static void unInit(String paramString)
  {
    try
    {
      Log.e("DuSdk", "unInit");
      if (maps.containsKey(paramString))
      {
        Log.e("DuSdk", "unInit success");
        maps.remove(paramString);
      }
      return;
    }
    catch (Throwable paramString)
    {
      Log.w("DuSdk", "unInit", paramString);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.du.DuSdk
 * JD-Core Version:    0.6.2
 */