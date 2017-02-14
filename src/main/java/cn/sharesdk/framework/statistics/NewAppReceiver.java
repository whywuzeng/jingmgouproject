package cn.sharesdk.framework.statistics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Ln;
import com.mob.tools.utils.LocalDB;
import com.mob.tools.utils.R;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class NewAppReceiver extends BroadcastReceiver
  implements Handler.Callback
{
  private static final String[] a = { "android.intent.action.PACKAGE_ADDED", "android.intent.action.PACKAGE_CHANGED", "android.intent.action.PACKAGE_REMOVED", "android.intent.action.PACKAGE_REPLACED" };
  private static NewAppReceiver b;
  private Context c;
  private IntentFilter[] d;
  private Handler e;

  private NewAppReceiver(Context paramContext)
  {
    this.c = paramContext;
    this.d = new IntentFilter[] { new IntentFilter(), new IntentFilter() };
    this.d[0].addAction("cn.sharesdk.START_UP");
    paramContext = a;
    int j = paramContext.length;
    while (i < j)
    {
      String str = paramContext[i];
      this.d[1].addAction(str);
      i += 1;
    }
    this.d[1].addDataScheme("package");
    this.e = new Handler(this);
    this.e.sendEmptyMessage(1);
  }

  public static void a()
  {
    try
    {
      NewAppReceiver localNewAppReceiver = b;
      if (localNewAppReceiver != null);
      try
      {
        b.c.unregisterReceiver(b);
        return;
      }
      catch (Throwable localThrowable)
      {
        while (true)
          Ln.w(localThrowable);
      }
    }
    finally
    {
    }
  }

  public static void a(Context paramContext)
  {
    try
    {
      if (b == null)
        b = new NewAppReceiver(paramContext);
      a();
      try
      {
        IntentFilter[] arrayOfIntentFilter = b.d;
        int j = arrayOfIntentFilter.length;
        int i = 0;
        while (i < j)
        {
          IntentFilter localIntentFilter = arrayOfIntentFilter[i];
          paramContext.registerReceiver(b, localIntentFilter);
          i += 1;
        }
      }
      catch (Throwable paramContext)
      {
        Ln.w(paramContext);
        return;
      }
    }
    finally
    {
    }
    throw paramContext;
  }

  private boolean a(String paramString)
  {
    boolean bool2 = false;
    String[] arrayOfString = a;
    int j = arrayOfString.length;
    int i = 0;
    while (true)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        if (arrayOfString[i].equals(paramString))
          bool1 = true;
      }
      else
        return bool1;
      i += 1;
    }
  }

  public boolean handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 1:
    }
    while (true)
    {
      return false;
      b.a(this.c);
    }
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str = null;
    if (paramIntent != null)
      str = paramIntent.getAction();
    int i;
    if ("cn.sharesdk.START_UP".equals(str))
    {
      paramContext = DeviceHelper.getInstance(paramContext).getPackageName();
      paramIntent = paramIntent.getStringExtra("packageName");
      if ((paramIntent == null) || (!paramIntent.equals(paramContext)))
        break label119;
      i = 1;
    }
    while (true)
    {
      if (i != 0)
      {
        Ln.d("========= receive broadcast: " + str, new Object[0]);
        this.e.removeMessages(1);
        this.e.sendEmptyMessageDelayed(1, 60000L);
      }
      return;
      if (a(str))
        i = 1;
      else
        label119: i = 0;
    }
  }

  private static class a
  {
    private LocalDB a;

    public a(Context paramContext)
    {
      try
      {
        Object localObject = DeviceHelper.getInstance(paramContext);
        paramContext = R.getCachePath(paramContext, null);
        if (((DeviceHelper)localObject).getSdcardState())
        {
          localObject = new File(((DeviceHelper)localObject).getSdcardPath(), "ShareSDK");
          if (((File)localObject).exists())
          {
            this.a = new LocalDB();
            paramContext = new File((File)localObject, ".ba");
            this.a.open(paramContext.getAbsolutePath());
            return;
          }
        }
        this.a = new LocalDB();
        paramContext = new File(paramContext, ".ba");
        if (!paramContext.getParentFile().exists())
          paramContext.getParentFile().mkdirs();
        this.a.open(paramContext.getAbsolutePath());
        return;
      }
      catch (Exception paramContext)
      {
        Ln.e(paramContext);
        if (this.a == null)
          this.a = new LocalDB();
      }
    }

    public ArrayList<HashMap<String, String>> a()
    {
      Object localObject = this.a.getObject("buffered_apps");
      if (localObject == null)
        return new ArrayList();
      return (ArrayList)localObject;
    }

    public void a(long paramLong)
    {
      this.a.putLong("buffered_apps_time", Long.valueOf(paramLong));
    }

    public void a(ArrayList<HashMap<String, String>> paramArrayList)
    {
      this.a.putObject("buffered_apps", paramArrayList);
    }

    public long b()
    {
      return this.a.getLong("buffered_apps_time");
    }
  }

  private static class b extends Thread
  {
    private Context a;
    private NewAppReceiver.a b;

    private b(Context paramContext)
    {
      this.a = paramContext;
      this.b = new NewAppReceiver.a(paramContext);
    }

    private ArrayList<HashMap<String, String>> a(HashMap<String, HashMap<String, String>> paramHashMap)
    {
      ArrayList localArrayList = new ArrayList();
      paramHashMap = paramHashMap.entrySet().iterator();
      while (paramHashMap.hasNext())
        localArrayList.add(((Map.Entry)paramHashMap.next()).getValue());
      return localArrayList;
    }

    private HashMap<String, HashMap<String, String>> a(ArrayList<HashMap<String, String>> paramArrayList)
    {
      HashMap localHashMap1 = new HashMap();
      paramArrayList = paramArrayList.iterator();
      while (paramArrayList.hasNext())
      {
        HashMap localHashMap2 = (HashMap)paramArrayList.next();
        String str = (String)localHashMap2.get("pkg");
        if (!TextUtils.isEmpty(str))
          localHashMap1.put(str, localHashMap2);
      }
      return localHashMap1;
    }

    public static void a(Context paramContext)
    {
      new b(paramContext).start();
    }

    // ERROR //
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 17	cn/sharesdk/framework/statistics/NewAppReceiver$b:a	Landroid/content/Context;
      //   4: invokestatic 96	com/mob/tools/utils/DeviceHelper:getInstance	(Landroid/content/Context;)Lcom/mob/tools/utils/DeviceHelper;
      //   7: astore_2
      //   8: aload_2
      //   9: iconst_0
      //   10: invokevirtual 100	com/mob/tools/utils/DeviceHelper:getInstalledApp	(Z)Ljava/util/ArrayList;
      //   13: astore_3
      //   14: aload_0
      //   15: getfield 23	cn/sharesdk/framework/statistics/NewAppReceiver$b:b	Lcn/sharesdk/framework/statistics/NewAppReceiver$a;
      //   18: invokevirtual 103	cn/sharesdk/framework/statistics/NewAppReceiver$a:a	()Ljava/util/ArrayList;
      //   21: astore 4
      //   23: aload_0
      //   24: getfield 23	cn/sharesdk/framework/statistics/NewAppReceiver$b:b	Lcn/sharesdk/framework/statistics/NewAppReceiver$a;
      //   27: aload_3
      //   28: invokevirtual 106	cn/sharesdk/framework/statistics/NewAppReceiver$a:a	(Ljava/util/ArrayList;)V
      //   31: aload_0
      //   32: aload_3
      //   33: invokespecial 108	cn/sharesdk/framework/statistics/NewAppReceiver$b:a	(Ljava/util/ArrayList;)Ljava/util/HashMap;
      //   36: astore 6
      //   38: aload_0
      //   39: aload 4
      //   41: invokespecial 108	cn/sharesdk/framework/statistics/NewAppReceiver$b:a	(Ljava/util/ArrayList;)Ljava/util/HashMap;
      //   44: astore 5
      //   46: aload 4
      //   48: invokevirtual 64	java/util/ArrayList:iterator	()Ljava/util/Iterator;
      //   51: astore 7
      //   53: aload 7
      //   55: invokeinterface 46 1 0
      //   60: ifeq +42 -> 102
      //   63: aload 7
      //   65: invokeinterface 50 1 0
      //   70: checkcast 30	java/util/HashMap
      //   73: ldc 66
      //   75: invokevirtual 70	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   78: checkcast 72	java/lang/String
      //   81: astore 8
      //   83: aload 8
      //   85: invokestatic 78	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   88: ifne -35 -> 53
      //   91: aload 6
      //   93: aload 8
      //   95: invokevirtual 111	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
      //   98: pop
      //   99: goto -46 -> 53
      //   102: aload_3
      //   103: invokevirtual 64	java/util/ArrayList:iterator	()Ljava/util/Iterator;
      //   106: astore 7
      //   108: aload 7
      //   110: invokeinterface 46 1 0
      //   115: ifeq +42 -> 157
      //   118: aload 7
      //   120: invokeinterface 50 1 0
      //   125: checkcast 30	java/util/HashMap
      //   128: ldc 66
      //   130: invokevirtual 70	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   133: checkcast 72	java/lang/String
      //   136: astore 8
      //   138: aload 8
      //   140: invokestatic 78	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   143: ifne -35 -> 108
      //   146: aload 5
      //   148: aload 8
      //   150: invokevirtual 111	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
      //   153: pop
      //   154: goto -46 -> 108
      //   157: aload_0
      //   158: aload 6
      //   160: invokespecial 113	cn/sharesdk/framework/statistics/NewAppReceiver$b:a	(Ljava/util/HashMap;)Ljava/util/ArrayList;
      //   163: astore 6
      //   165: aload_0
      //   166: aload 5
      //   168: invokespecial 113	cn/sharesdk/framework/statistics/NewAppReceiver$b:a	(Ljava/util/HashMap;)Ljava/util/ArrayList;
      //   171: astore 5
      //   173: invokestatic 119	java/lang/System:currentTimeMillis	()J
      //   176: aload_0
      //   177: getfield 23	cn/sharesdk/framework/statistics/NewAppReceiver$b:b	Lcn/sharesdk/framework/statistics/NewAppReceiver$a;
      //   180: invokevirtual 121	cn/sharesdk/framework/statistics/NewAppReceiver$a:b	()J
      //   183: lsub
      //   184: ldc2_w 122
      //   187: lcmp
      //   188: iflt +93 -> 281
      //   191: iconst_1
      //   192: istore_1
      //   193: iload_1
      //   194: ifne +11 -> 205
      //   197: aload 4
      //   199: invokevirtual 127	java/util/ArrayList:size	()I
      //   202: ifgt +93 -> 295
      //   205: aload_0
      //   206: getfield 23	cn/sharesdk/framework/statistics/NewAppReceiver$b:b	Lcn/sharesdk/framework/statistics/NewAppReceiver$a;
      //   209: invokestatic 119	java/lang/System:currentTimeMillis	()J
      //   212: invokevirtual 130	cn/sharesdk/framework/statistics/NewAppReceiver$a:a	(J)V
      //   215: aload_0
      //   216: getfield 17	cn/sharesdk/framework/statistics/NewAppReceiver$b:a	Landroid/content/Context;
      //   219: invokestatic 135	cn/sharesdk/framework/statistics/a:a	(Landroid/content/Context;)Lcn/sharesdk/framework/statistics/a;
      //   222: ldc 137
      //   224: aload_3
      //   225: invokevirtual 140	cn/sharesdk/framework/statistics/a:a	(Ljava/lang/String;Ljava/util/ArrayList;)V
      //   228: aload 5
      //   230: invokevirtual 127	java/util/ArrayList:size	()I
      //   233: ifle +47 -> 280
      //   236: new 142	java/lang/StringBuilder
      //   239: dup
      //   240: invokespecial 143	java/lang/StringBuilder:<init>	()V
      //   243: ldc 145
      //   245: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   248: aload_2
      //   249: invokevirtual 153	com/mob/tools/utils/DeviceHelper:getPackageName	()Ljava/lang/String;
      //   252: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   255: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   258: iconst_0
      //   259: anewarray 158	java/lang/Object
      //   262: invokestatic 164	com/mob/tools/utils/Ln:d	(Ljava/lang/Object;[Ljava/lang/Object;)I
      //   265: pop
      //   266: aload_0
      //   267: getfield 17	cn/sharesdk/framework/statistics/NewAppReceiver$b:a	Landroid/content/Context;
      //   270: invokestatic 135	cn/sharesdk/framework/statistics/a:a	(Landroid/content/Context;)Lcn/sharesdk/framework/statistics/a;
      //   273: ldc 166
      //   275: aload 5
      //   277: invokevirtual 140	cn/sharesdk/framework/statistics/a:a	(Ljava/lang/String;Ljava/util/ArrayList;)V
      //   280: return
      //   281: iconst_0
      //   282: istore_1
      //   283: goto -90 -> 193
      //   286: astore_3
      //   287: aload_3
      //   288: invokestatic 170	com/mob/tools/utils/Ln:e	(Ljava/lang/Throwable;)I
      //   291: pop
      //   292: goto -64 -> 228
      //   295: aload 6
      //   297: invokevirtual 127	java/util/ArrayList:size	()I
      //   300: ifle -72 -> 228
      //   303: new 142	java/lang/StringBuilder
      //   306: dup
      //   307: invokespecial 143	java/lang/StringBuilder:<init>	()V
      //   310: ldc 172
      //   312: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   315: aload_2
      //   316: invokevirtual 153	com/mob/tools/utils/DeviceHelper:getPackageName	()Ljava/lang/String;
      //   319: invokevirtual 149	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   322: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   325: iconst_0
      //   326: anewarray 158	java/lang/Object
      //   329: invokestatic 164	com/mob/tools/utils/Ln:d	(Ljava/lang/Object;[Ljava/lang/Object;)I
      //   332: pop
      //   333: aload_0
      //   334: getfield 17	cn/sharesdk/framework/statistics/NewAppReceiver$b:a	Landroid/content/Context;
      //   337: invokestatic 135	cn/sharesdk/framework/statistics/a:a	(Landroid/content/Context;)Lcn/sharesdk/framework/statistics/a;
      //   340: ldc 174
      //   342: aload 6
      //   344: invokevirtual 140	cn/sharesdk/framework/statistics/a:a	(Ljava/lang/String;Ljava/util/ArrayList;)V
      //   347: goto -119 -> 228
      //   350: astore_3
      //   351: aload_3
      //   352: invokestatic 170	com/mob/tools/utils/Ln:e	(Ljava/lang/Throwable;)I
      //   355: pop
      //   356: goto -128 -> 228
      //   359: astore_2
      //   360: aload_2
      //   361: invokestatic 170	com/mob/tools/utils/Ln:e	(Ljava/lang/Throwable;)I
      //   364: pop
      //   365: return
      //
      // Exception table:
      //   from	to	target	type
      //   215	228	286	java/lang/Throwable
      //   333	347	350	java/lang/Throwable
      //   266	280	359	java/lang/Throwable
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.sharesdk.framework.statistics.NewAppReceiver
 * JD-Core Version:    0.6.2
 */