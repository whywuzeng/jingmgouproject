package com.umeng.fb.util;

import android.content.Context;
import java.io.File;

public class c
{
  private static final String a = c.class.getName();

  public static void a(Context paramContext)
  {
    b(c(paramContext));
  }

  public static void a(Context paramContext, String paramString)
  {
    b(b(paramContext) + paramString + ".opus");
  }

  public static boolean a(String paramString)
  {
    try
    {
      paramString = new File(paramString);
      if (!paramString.exists())
        paramString.mkdirs();
      return true;
    }
    finally
    {
      paramString = finally;
    }
    throw paramString;
  }

  public static String b(Context paramContext)
  {
    return paramContext.getFilesDir().getAbsolutePath() + "/umeng/fb/audio/";
  }

  public static String b(Context paramContext, String paramString)
  {
    return d(paramContext) + paramString + ".jpg";
  }

  public static void b(String paramString)
  {
    try
    {
      new Thread(new Runnable()
      {
        public void run()
        {
          Object localObject = new File(this.a);
          if (!((File)localObject).exists());
          while (true)
          {
            return;
            if (((File)localObject).isFile())
            {
              ((File)localObject).delete();
              return;
            }
            if (((File)localObject).isDirectory())
            {
              localObject = ((File)localObject).listFiles();
              int j = localObject.length;
              int i = 0;
              while (i < j)
              {
                localObject[i].delete();
                i += 1;
              }
            }
          }
        }
      }).start();
      return;
    }
    finally
    {
      paramString = finally;
    }
    throw paramString;
  }

  public static String c(Context paramContext)
  {
    paramContext = paramContext.getFilesDir().getAbsolutePath() + "/umeng/fb/audio/cache/";
    Log.c(a, "getAudioCachePath=" + paramContext);
    return paramContext;
  }

  public static boolean c(String paramString)
  {
    return new File(paramString).exists();
  }

  public static String d(Context paramContext)
  {
    paramContext = paramContext.getFilesDir().getAbsolutePath() + "/umeng/fb/image/";
    a(paramContext);
    return paramContext;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.util.c
 * JD-Core Version:    0.6.2
 */