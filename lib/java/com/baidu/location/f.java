package com.baidu.location;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.baidu.location.a.a;
import com.baidu.location.b.e;
import com.baidu.location.b.k;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.RandomAccessFile;

public class f extends Service
  implements com.baidu.location.b.f
{
  public static boolean isServing = false;
  private static final String lZ = "app.jar";
  public static Context mC;
  public static String replaceFileName = "repll.jar";
  LLSInterface l0 = null;
  LLSInterface l1 = null;
  LLSInterface lY = null;

  static
  {
    mC = null;
  }

  private boolean jdMethod_do(File paramFile)
  {
    boolean bool1 = false;
    boolean bool4 = false;
    boolean bool3 = false;
    Object localObject1 = e.int + "/grtcf.dat";
    boolean bool2 = bool4;
    try
    {
      localObject1 = new File((String)localObject1);
      bool2 = bool4;
      if (((File)localObject1).exists())
      {
        bool2 = bool4;
        localObject1 = new RandomAccessFile((File)localObject1, "rw");
        bool2 = bool4;
        ((RandomAccessFile)localObject1).seek(200L);
        bool1 = bool3;
        bool2 = bool4;
        if (((RandomAccessFile)localObject1).readBoolean())
        {
          bool1 = bool3;
          bool2 = bool4;
          if (((RandomAccessFile)localObject1).readBoolean())
          {
            bool2 = bool4;
            int i = ((RandomAccessFile)localObject1).readInt();
            bool1 = bool3;
            if (i != 0)
            {
              bool2 = bool4;
              Object localObject2 = new byte[i];
              bool2 = bool4;
              ((RandomAccessFile)localObject1).read((byte[])localObject2, 0, i);
              bool2 = bool4;
              localObject2 = new String((byte[])localObject2);
              bool2 = bool4;
              paramFile = k.jdMethod_if(paramFile);
              bool1 = bool3;
              if (localObject2 != null)
              {
                bool1 = bool3;
                if (paramFile != null)
                {
                  bool1 = bool3;
                  bool2 = bool4;
                  if (paramFile.equals(localObject2))
                    bool1 = true;
                }
              }
            }
          }
        }
        bool2 = bool1;
        ((RandomAccessFile)localObject1).close();
      }
      return bool1;
    }
    catch (Exception paramFile)
    {
    }
    return bool2;
  }

  public static float getFrameVersion()
  {
    return 6.12F;
  }

  public static String getJarFileName()
  {
    return "app.jar";
  }

  public static Context getServiceContext()
  {
    return mC;
  }

  public IBinder onBind(Intent paramIntent)
  {
    return this.l1.onBind(paramIntent);
  }

  public void onCreate()
  {
    mC = getApplicationContext();
    System.currentTimeMillis();
    this.l0 = new a();
    try
    {
      File localFile1 = new File(k.ai() + File.separator + replaceFileName);
      File localFile2 = new File(k.ai() + File.separator + "app.jar");
      if (localFile1.exists())
      {
        if (localFile2.exists())
          localFile2.delete();
        localFile1.renameTo(localFile2);
      }
      if (localFile2.exists())
        this.lY = ((LLSInterface)new DexClassLoader(k.ai() + File.separator + "app.jar", k.ai(), null, getClassLoader()).loadClass("com.baidu.serverLoc.LocationService").newInstance());
      if ((this.lY != null) && (this.lY.getVersion() >= this.l0.getVersion()) && (jdMethod_do(new File(k.ai() + File.separator + "app.jar"))))
      {
        this.l1 = this.lY;
        this.l0 = null;
        isServing = true;
        this.l1.onCreate(this);
        return;
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        this.lY = null;
        continue;
        this.l1 = this.l0;
        this.lY = null;
      }
    }
  }

  public void onDestroy()
  {
    isServing = false;
    this.l1.onDestroy();
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    return this.l1.onStartCommand(paramIntent, paramInt1, paramInt2);
  }

  public boolean onUnbind(Intent paramIntent)
  {
    return this.l1.onUnBind(paramIntent);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.f
 * JD-Core Version:    0.6.2
 */