package com.umeng.message;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.text.TextUtils;
import com.umeng.common.message.Log;
import com.umeng.message.entity.UMessage;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import org.json.JSONObject;

public class UmengDownloadResourceService extends Service
{
  public static final String TAG = UmengDownloadResourceService.class.getSimpleName();
  private static final String d = ".tmp";
  private static final String e = "RETRY_TIME";
  private static final String f = "OPERATIOIN";
  private static final int g = 1;
  private static final int h = 2;
  private static final long i = 1048576L;
  private static final long j = 86400000L;
  private static final int k = 300000;
  private static final int l = 3;
  private static Thread m;
  ScheduledThreadPoolExecutor a;
  Context b;
  ArrayList<String> c;

  private static long a(File paramFile)
  {
    long l2;
    if ((paramFile == null) || (!paramFile.exists()) || (!paramFile.isDirectory()))
    {
      l2 = 0L;
      return l2;
    }
    Stack localStack = new Stack();
    localStack.clear();
    localStack.push(paramFile);
    long l1 = 0L;
    while (true)
    {
      l2 = l1;
      if (localStack.isEmpty())
        break;
      paramFile = ((File)localStack.pop()).listFiles();
      int i1 = paramFile.length;
      int n = 0;
      while (n < i1)
      {
        Object localObject = paramFile[n];
        l2 = l1;
        if (!localObject.isDirectory())
          l2 = l1 + localObject.length();
        n += 1;
        l1 = l2;
      }
    }
  }

  private PendingIntent a(UMessage paramUMessage, int paramInt)
  {
    Object localObject = paramUMessage.getRaw().toString();
    int n = paramUMessage.msg_id.hashCode();
    Intent localIntent = new Intent(this.b, UmengDownloadResourceService.class);
    localIntent.putExtra("body", (String)localObject);
    localIntent.putExtra("id", paramUMessage.message_id);
    localIntent.putExtra("task_id", paramUMessage.task_id);
    localIntent.putExtra("OPERATIOIN", 2);
    localIntent.putExtra("RETRY_TIME", paramInt);
    localObject = PendingIntent.getService(this.b, n, localIntent, 134217728);
    Log.a(TAG, "PendingIntent: msgId:" + paramUMessage.msg_id + ",requestCode:" + n + ",retryTime:" + paramInt);
    return localObject;
  }

  private static void b(File paramFile, long paramLong)
  {
    if ((paramFile == null) || (!paramFile.exists()) || (!paramFile.canWrite()) || (!paramFile.isDirectory()));
    while (true)
    {
      return;
      paramFile = paramFile.listFiles();
      int i1 = paramFile.length;
      int n = 0;
      while (n < i1)
      {
        Object localObject = paramFile[n];
        if ((!localObject.isDirectory()) && (System.currentTimeMillis() - localObject.lastModified() > paramLong))
          localObject.delete();
        n += 1;
      }
    }
  }

  public static void checkDir(File arg0, long paramLong1, final long paramLong2)
    throws IOException
  {
    if ((???.exists()) && (a(???.getCanonicalFile()) > paramLong1))
    {
      if (m == null)
        m = new Thread(new Runnable()
        {
          public void run()
          {
            UmengDownloadResourceService.a(this.a, paramLong2);
            UmengDownloadResourceService.a(null);
          }
        });
      synchronized (m)
      {
        m.start();
        return;
      }
    }
  }

  public static String getMessageResourceFolder(Context paramContext, UMessage paramUMessage)
  {
    String str = paramContext.getCacheDir() + "/umeng_push/";
    paramContext = str;
    if (paramUMessage != null)
    {
      paramContext = str;
      if (paramUMessage.msg_id != null)
        paramContext = str + paramUMessage.msg_id + "/";
    }
    return paramContext;
  }

  public void checkCache()
  {
    try
    {
      checkDir(new File(getMessageResourceFolder(this.b, null)), 1048576L, 86400000L);
      return;
    }
    catch (Throwable localThrowable)
    {
    }
  }

  public void close(Closeable paramCloseable)
  {
    if (paramCloseable != null);
    try
    {
      paramCloseable.close();
      return;
    }
    catch (Exception paramCloseable)
    {
      paramCloseable.printStackTrace();
    }
  }

  public void deleteAlarm(UMessage paramUMessage, int paramInt)
  {
    Log.a(TAG, "deleteAlarm");
    paramUMessage = a(paramUMessage, paramInt);
    ((AlarmManager)getSystemService("alarm")).cancel(paramUMessage);
  }

  @SuppressLint({"NewApi"})
  public void downloadResource(UMessage paramUMessage, int paramInt)
  {
    paramUMessage = new DownloadResourceTask(paramUMessage, paramInt);
    if (Build.VERSION.SDK_INT >= 11)
    {
      paramUMessage.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
      return;
    }
    paramUMessage.execute(new Void[0]);
  }

  public void notification(UMessage paramUMessage)
  {
    Object localObject = PushAgent.getInstance(this).getMessageHandler();
    if (localObject != null)
    {
      if (!TextUtils.equals("autoupdate", paramUMessage.display_type))
        break label53;
      localObject = (UmengMessageHandler)PushAgent.getInstance(this.b).getMessageHandler();
      if (localObject != null)
        ((UmengMessageHandler)localObject).dealWithNotificationMessage(this.b, paramUMessage);
    }
    return;
    label53: ((UHandler)localObject).handleMessage(this, paramUMessage);
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onCreate()
  {
    this.a = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 4);
    this.b = this;
    this.c = new ArrayList();
  }

  @SuppressLint({"NewApi"})
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    if (paramIntent == null)
      return super.onStartCommand(paramIntent, paramInt1, paramInt2);
    int n = paramIntent.getIntExtra("OPERATIOIN", 2);
    int i1 = paramIntent.getIntExtra("RETRY_TIME", 3);
    Object localObject = paramIntent.getStringExtra("body");
    try
    {
      localObject = new UMessage(new JSONObject((String)localObject));
      ((UMessage)localObject).message_id = paramIntent.getStringExtra("id");
      ((UMessage)localObject).task_id = paramIntent.getStringExtra("task_id");
      if (this.c.contains(((UMessage)localObject).msg_id))
        return super.onStartCommand(paramIntent, paramInt1, paramInt2);
      this.c.add(((UMessage)localObject).msg_id);
      switch (n)
      {
      default:
      case 2:
        while (true)
        {
          return super.onStartCommand(paramIntent, paramInt1, paramInt2);
          Log.a(TAG, "Start Download Resource");
          n = i1 - 1;
          setAlarm((UMessage)localObject, n);
          checkCache();
          downloadResource((UMessage)localObject, n);
        }
      case 1:
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        continue;
        deleteAlarm(localException, i1);
        Log.a(TAG, "Show Notification After Downloaded Resource");
        notification(localException);
        this.c.remove(localException.msg_id);
        if (this.c.size() == 0)
          stopSelf();
      }
    }
  }

  public void setAlarm(UMessage paramUMessage, int paramInt)
  {
    Log.a(TAG, "setAlarm");
    paramUMessage = a(paramUMessage, paramInt);
    ((AlarmManager)getSystemService("alarm")).set(1, System.currentTimeMillis() + 300000L, paramUMessage);
  }

  public class DownloadResourceTask extends AsyncTask<Void, Void, Boolean>
  {
    UMessage a;
    ArrayList<String> b;
    int c;

    public DownloadResourceTask(UMessage paramInt, int arg3)
    {
      this.a = paramInt;
      this.b = new ArrayList();
      if (paramInt.isLargeIconFromInternet())
        this.b.add(paramInt.img);
      if (paramInt.isSoundFromInternet())
        this.b.add(paramInt.sound);
      int i;
      this.c = i;
    }

    protected Boolean a(Void[] paramArrayOfVoid)
    {
      paramArrayOfVoid = this.b.iterator();
      for (boolean bool = true; paramArrayOfVoid.hasNext(); bool = download((String)paramArrayOfVoid.next()) & bool);
      return Boolean.valueOf(bool);
    }

    protected void a(Boolean paramBoolean)
    {
      super.onPostExecute(paramBoolean);
      UmengDownloadResourceService.this.c.remove(this.a.msg_id);
      if ((paramBoolean.booleanValue()) || (this.c <= 0))
      {
        MessageSharedPrefs.getInstance(UmengDownloadResourceService.this.b).c(this.a.msg_id);
        paramBoolean = this.a.getRaw().toString();
        localIntent = new Intent(UmengDownloadResourceService.this.b, UmengDownloadResourceService.class);
        localIntent.putExtra("body", paramBoolean);
        localIntent.putExtra("id", this.a.message_id);
        localIntent.putExtra("task_id", this.a.task_id);
        localIntent.putExtra("OPERATIOIN", 1);
        localIntent.putExtra("RETRY_TIME", this.c);
        UmengDownloadResourceService.this.startService(localIntent);
      }
      while (UmengDownloadResourceService.this.c.size() != 0)
      {
        Intent localIntent;
        return;
      }
      UmengDownloadResourceService.this.stopSelf();
    }

    // ERROR //
    public boolean download(String paramString)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore 6
      //   3: aconst_null
      //   4: astore 5
      //   6: aload_1
      //   7: invokestatic 170	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   10: ifeq +5 -> 15
      //   13: iconst_1
      //   14: ireturn
      //   15: new 172	java/lang/StringBuilder
      //   18: dup
      //   19: invokespecial 173	java/lang/StringBuilder:<init>	()V
      //   22: aload_1
      //   23: invokevirtual 176	java/lang/String:hashCode	()I
      //   26: invokevirtual 180	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   29: ldc 182
      //   31: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   34: invokevirtual 186	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   37: astore 8
      //   39: aload_0
      //   40: getfield 21	com/umeng/message/UmengDownloadResourceService$DownloadResourceTask:d	Lcom/umeng/message/UmengDownloadResourceService;
      //   43: getfield 99	com/umeng/message/UmengDownloadResourceService:b	Landroid/content/Context;
      //   46: aload_0
      //   47: getfield 26	com/umeng/message/UmengDownloadResourceService$DownloadResourceTask:a	Lcom/umeng/message/entity/UMessage;
      //   50: invokestatic 190	com/umeng/message/UmengDownloadResourceService:getMessageResourceFolder	(Landroid/content/Context;Lcom/umeng/message/entity/UMessage;)Ljava/lang/String;
      //   53: astore 4
      //   55: new 192	java/io/File
      //   58: dup
      //   59: aload 4
      //   61: new 172	java/lang/StringBuilder
      //   64: dup
      //   65: invokespecial 173	java/lang/StringBuilder:<init>	()V
      //   68: aload 8
      //   70: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   73: ldc 194
      //   75: invokevirtual 185	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   78: invokevirtual 186	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   81: invokespecial 197	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
      //   84: astore 7
      //   86: new 192	java/io/File
      //   89: dup
      //   90: aload 4
      //   92: aload 8
      //   94: invokespecial 197	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
      //   97: astore 8
      //   99: aload 8
      //   101: invokevirtual 200	java/io/File:exists	()Z
      //   104: istore_3
      //   105: iload_3
      //   106: ifeq +21 -> 127
      //   109: aload_0
      //   110: getfield 21	com/umeng/message/UmengDownloadResourceService$DownloadResourceTask:d	Lcom/umeng/message/UmengDownloadResourceService;
      //   113: aconst_null
      //   114: invokevirtual 204	com/umeng/message/UmengDownloadResourceService:close	(Ljava/io/Closeable;)V
      //   117: aload_0
      //   118: getfield 21	com/umeng/message/UmengDownloadResourceService$DownloadResourceTask:d	Lcom/umeng/message/UmengDownloadResourceService;
      //   121: aconst_null
      //   122: invokevirtual 204	com/umeng/message/UmengDownloadResourceService:close	(Ljava/io/Closeable;)V
      //   125: iconst_1
      //   126: ireturn
      //   127: new 192	java/io/File
      //   130: dup
      //   131: aload 4
      //   133: invokespecial 206	java/io/File:<init>	(Ljava/lang/String;)V
      //   136: astore 4
      //   138: aload 4
      //   140: invokevirtual 200	java/io/File:exists	()Z
      //   143: ifne +9 -> 152
      //   146: aload 4
      //   148: invokevirtual 209	java/io/File:mkdirs	()Z
      //   151: pop
      //   152: aload 7
      //   154: invokevirtual 200	java/io/File:exists	()Z
      //   157: ifeq +9 -> 166
      //   160: aload 7
      //   162: invokevirtual 212	java/io/File:delete	()Z
      //   165: pop
      //   166: new 214	java/net/URL
      //   169: dup
      //   170: new 216	java/net/URI
      //   173: dup
      //   174: aload_1
      //   175: invokespecial 217	java/net/URI:<init>	(Ljava/lang/String;)V
      //   178: invokevirtual 220	java/net/URI:toASCIIString	()Ljava/lang/String;
      //   181: invokespecial 221	java/net/URL:<init>	(Ljava/lang/String;)V
      //   184: invokevirtual 225	java/net/URL:openStream	()Ljava/io/InputStream;
      //   187: astore_1
      //   188: new 227	java/io/FileOutputStream
      //   191: dup
      //   192: aload 7
      //   194: invokespecial 230	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
      //   197: astore 4
      //   199: sipush 10240
      //   202: newarray byte
      //   204: astore 5
      //   206: aload_1
      //   207: aload 5
      //   209: invokevirtual 236	java/io/InputStream:read	([B)I
      //   212: istore_2
      //   213: iload_2
      //   214: ifle +49 -> 263
      //   217: aload 4
      //   219: aload 5
      //   221: iconst_0
      //   222: iload_2
      //   223: invokevirtual 240	java/io/FileOutputStream:write	([BII)V
      //   226: goto -20 -> 206
      //   229: astore 6
      //   231: aload 4
      //   233: astore 5
      //   235: aload 6
      //   237: astore 4
      //   239: aload 4
      //   241: invokevirtual 243	java/lang/Exception:printStackTrace	()V
      //   244: aload_0
      //   245: getfield 21	com/umeng/message/UmengDownloadResourceService$DownloadResourceTask:d	Lcom/umeng/message/UmengDownloadResourceService;
      //   248: aload_1
      //   249: invokevirtual 204	com/umeng/message/UmengDownloadResourceService:close	(Ljava/io/Closeable;)V
      //   252: aload_0
      //   253: getfield 21	com/umeng/message/UmengDownloadResourceService$DownloadResourceTask:d	Lcom/umeng/message/UmengDownloadResourceService;
      //   256: aload 5
      //   258: invokevirtual 204	com/umeng/message/UmengDownloadResourceService:close	(Ljava/io/Closeable;)V
      //   261: iconst_0
      //   262: ireturn
      //   263: aload 7
      //   265: aload 8
      //   267: invokevirtual 247	java/io/File:renameTo	(Ljava/io/File;)Z
      //   270: pop
      //   271: aload_0
      //   272: getfield 21	com/umeng/message/UmengDownloadResourceService$DownloadResourceTask:d	Lcom/umeng/message/UmengDownloadResourceService;
      //   275: aload_1
      //   276: invokevirtual 204	com/umeng/message/UmengDownloadResourceService:close	(Ljava/io/Closeable;)V
      //   279: aload_0
      //   280: getfield 21	com/umeng/message/UmengDownloadResourceService$DownloadResourceTask:d	Lcom/umeng/message/UmengDownloadResourceService;
      //   283: aload 4
      //   285: invokevirtual 204	com/umeng/message/UmengDownloadResourceService:close	(Ljava/io/Closeable;)V
      //   288: iconst_1
      //   289: ireturn
      //   290: astore 4
      //   292: aconst_null
      //   293: astore_1
      //   294: aload 6
      //   296: astore 5
      //   298: aload_0
      //   299: getfield 21	com/umeng/message/UmengDownloadResourceService$DownloadResourceTask:d	Lcom/umeng/message/UmengDownloadResourceService;
      //   302: aload_1
      //   303: invokevirtual 204	com/umeng/message/UmengDownloadResourceService:close	(Ljava/io/Closeable;)V
      //   306: aload_0
      //   307: getfield 21	com/umeng/message/UmengDownloadResourceService$DownloadResourceTask:d	Lcom/umeng/message/UmengDownloadResourceService;
      //   310: aload 5
      //   312: invokevirtual 204	com/umeng/message/UmengDownloadResourceService:close	(Ljava/io/Closeable;)V
      //   315: aload 4
      //   317: athrow
      //   318: astore 4
      //   320: aload 6
      //   322: astore 5
      //   324: goto -26 -> 298
      //   327: astore 6
      //   329: aload 4
      //   331: astore 5
      //   333: aload 6
      //   335: astore 4
      //   337: goto -39 -> 298
      //   340: astore 4
      //   342: goto -44 -> 298
      //   345: astore 4
      //   347: aconst_null
      //   348: astore_1
      //   349: goto -110 -> 239
      //   352: astore 4
      //   354: goto -115 -> 239
      //
      // Exception table:
      //   from	to	target	type
      //   199	206	229	java/lang/Exception
      //   206	213	229	java/lang/Exception
      //   217	226	229	java/lang/Exception
      //   263	271	229	java/lang/Exception
      //   15	105	290	finally
      //   127	152	290	finally
      //   152	166	290	finally
      //   166	188	290	finally
      //   188	199	318	finally
      //   199	206	327	finally
      //   206	213	327	finally
      //   217	226	327	finally
      //   263	271	327	finally
      //   239	244	340	finally
      //   15	105	345	java/lang/Exception
      //   127	152	345	java/lang/Exception
      //   152	166	345	java/lang/Exception
      //   166	188	345	java/lang/Exception
      //   188	199	352	java/lang/Exception
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.UmengDownloadResourceService
 * JD-Core Version:    0.6.2
 */