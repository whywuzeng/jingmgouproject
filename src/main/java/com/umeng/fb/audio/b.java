package com.umeng.fb.audio;

import android.media.AudioRecord;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class b
{
  private static String e;
  private static String f;
  private final String a = b.class.getName();
  private int b = 0;
  private AudioRecord c;
  private boolean d = false;
  private long g;

  private boolean d()
  {
    boolean bool = true;
    this.b = (AudioRecord.getMinBufferSize(16000, 16, 2) * 2);
    if (-2 == this.b)
      bool = false;
    do
    {
      return bool;
      this.c = new AudioRecord(1, 16000, 16, 2, this.b);
    }
    while (this.c.getState() == 1);
    return false;
  }

  private void e()
  {
    if (this.c == null)
      return;
    byte[] arrayOfByte = new byte[this.b];
    try
    {
      Object localObject1 = new File(e);
      if (((File)localObject1).exists())
        ((File)localObject1).delete();
      localObject1 = new FileOutputStream((File)localObject1);
      this.c.startRecording();
      if ((this.d != true) || (-3 == this.c.read(arrayOfByte, 0, this.b)))
      {
        a();
        try
        {
          ((FileOutputStream)localObject1).close();
          return;
        }
        catch (IOException localIOException1)
        {
          localIOException1.printStackTrace();
          return;
        }
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        Object localObject2 = null;
        continue;
        try
        {
          localObject2.write(arrayOfByte);
          this.g += this.b;
        }
        catch (IOException localIOException2)
        {
          localIOException2.printStackTrace();
        }
      }
    }
  }

  protected int a()
  {
    try
    {
      this.d = false;
      if (this.c != null)
      {
        i = this.c.getRecordingState();
        if (1 != i);
      }
      for (int i = -1; ; i = this.b)
      {
        return i;
        this.c.stop();
        this.c.release();
        this.c = null;
      }
    }
    finally
    {
    }
  }

  protected boolean a(String paramString1, String paramString2)
  {
    try
    {
      e = paramString1;
      f = paramString2;
      boolean bool = d();
      if (bool)
      {
        this.d = true;
        this.g = 0L;
        new Thread(new a()).start();
      }
      return bool;
    }
    finally
    {
    }
    throw paramString1;
  }

  protected boolean b()
  {
    boolean bool = true;
    if (this.c.getRecordingState() == 1)
      bool = false;
    return bool;
  }

  public long c()
  {
    return this.g / 16000L / 2L;
  }

  class a
    implements Runnable
  {
    a()
    {
    }

    public void run()
    {
      if (b.a(b.this) != null)
        b.b(b.this);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.audio.b
 * JD-Core Version:    0.6.2
 */