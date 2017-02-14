package com.umeng.fb.audio;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.fb.util.Log;
import java.io.File;

public class AudioAgent
{
  protected static final int a = 16000;
  protected static final int b = 2;
  protected static final int c = 1;
  protected static final int d = 16;
  protected static final int e = 4;
  protected static final int f = 3;
  private static AudioAgent j;
  private final String g = AudioAgent.class.getName();
  private b h;
  private c i;
  private boolean k;
  private String l;
  private Context m;
  private Thread n;
  private a o;

  private AudioAgent(Context paramContext)
  {
    this.m = paramContext;
    this.h = new b();
    this.i = new c(this.m);
    this.i.a(new a());
  }

  public static AudioAgent getInstance(Context paramContext)
  {
    if (j == null)
      j = new AudioAgent(paramContext);
    return j;
  }

  public float getAudioDuration()
  {
    if (this.h.c() <= 60L)
      return (float)this.h.c();
    return 60.0F;
  }

  public boolean getAudioInitStatus()
  {
    return this.k;
  }

  public boolean getPlayStatus()
  {
    return this.n != null;
  }

  public boolean getRecordStatus()
  {
    return this.h.b();
  }

  public void recordShortStop()
  {
    this.h.a();
  }

  public boolean recordStart(String paramString)
  {
    if (!com.umeng.fb.util.c.a(com.umeng.fb.util.c.c(this.m)))
      return false;
    this.l = paramString;
    String str = com.umeng.fb.util.c.c(this.m) + paramString + ".raw";
    paramString = com.umeng.fb.util.c.c(this.m) + paramString + ".wav";
    this.k = this.h.a(str, paramString);
    return this.k;
  }

  public int recordStop()
  {
    int i1 = this.h.a();
    if (!new File(com.umeng.fb.util.c.c(this.m) + this.l + ".raw").exists())
      return -1;
    this.i.a(this.l, i1);
    return i1;
  }

  public void startPlay(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
      this.i.a(paramString);
  }

  public void stopPlayer()
  {
    try
    {
      if (this.n != null)
      {
        this.o.b();
        this.n.interrupt();
        this.n = null;
      }
      return;
    }
    catch (Exception localException)
    {
      Log.b(this.g, localException.getMessage());
    }
  }

  class a
    implements c.a
  {
    a()
    {
    }

    public void a(String paramString, int paramInt)
    {
      if (!new File(paramString).exists())
        return;
      try
      {
        AudioAgent.a(AudioAgent.this, new a(paramString));
        if (AudioAgent.c(AudioAgent.this) == null)
          AudioAgent.a(AudioAgent.this, new Thread(new AudioAgent.b(AudioAgent.this)));
        AudioAgent.c(AudioAgent.this).start();
        return;
      }
      catch (Exception paramString)
      {
        Log.b(AudioAgent.b(AudioAgent.this), paramString.getMessage());
      }
    }
  }

  class b
    implements Runnable
  {
    b()
    {
    }

    public void run()
    {
      try
      {
        if (AudioAgent.a(AudioAgent.this) != null)
          AudioAgent.a(AudioAgent.this).a();
        return;
      }
      catch (Exception localException)
      {
        Log.b(AudioAgent.b(AudioAgent.this), localException.getMessage());
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.audio.AudioAgent
 * JD-Core Version:    0.6.2
 */