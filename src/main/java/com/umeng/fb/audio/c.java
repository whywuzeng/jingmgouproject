package com.umeng.fb.audio;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import com.umeng.fb.fragment.FeedbackFragment;
import com.umeng.fb.opus.OpusTool;
import com.umeng.fb.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class c
{
  private final String a = c.class.getName();
  private OpusTool b;
  private int c;
  private final int d = 5;
  private Context e;
  private a f;

  public c(Context paramContext)
  {
    this.e = paramContext;
    this.b = new OpusTool(paramContext);
  }

  private void a(FileOutputStream paramFileOutputStream, long paramLong1, long paramLong2, long paramLong3, int paramInt, long paramLong4)
    throws IOException
  {
    paramFileOutputStream.write(new byte[] { 82, 73, 70, 70, (byte)(int)(0xFF & paramLong2), (byte)(int)(paramLong2 >> 8 & 0xFF), (byte)(int)(paramLong2 >> 16 & 0xFF), (byte)(int)(paramLong2 >> 24 & 0xFF), 87, 65, 86, 69, 102, 109, 116, 32, 16, 0, 0, 0, 1, 0, (byte)paramInt, 0, (byte)(int)(0xFF & paramLong3), (byte)(int)(paramLong3 >> 8 & 0xFF), (byte)(int)(paramLong3 >> 16 & 0xFF), (byte)(int)(paramLong3 >> 24 & 0xFF), (byte)(int)(0xFF & paramLong4), (byte)(int)(paramLong4 >> 8 & 0xFF), (byte)(int)(paramLong4 >> 16 & 0xFF), (byte)(int)(paramLong4 >> 24 & 0xFF), (byte)(paramInt * 16 / 8), 0, 16, 0, 100, 97, 116, 97, (byte)(int)(0xFF & paramLong1), (byte)(int)(paramLong1 >> 8 & 0xFF), (byte)(int)(paramLong1 >> 16 & 0xFF), (byte)(int)(paramLong1 >> 24 & 0xFF) }, 0, 44);
  }

  private void a(String paramString1, String paramString2, int paramInt)
  {
    long l1 = 32000;
    byte[] arrayOfByte = new byte[paramInt];
    try
    {
      localObject = new File(paramString1);
      paramString2 = new File(paramString2);
      if (!((File)localObject).exists())
        return;
      localObject = new FileInputStream((File)localObject);
      paramString2 = new FileOutputStream(paramString2);
      long l2 = ((FileInputStream)localObject).available();
      a(paramString2, l2, 36L + l2, 16000L, 1, l1);
      while (((FileInputStream)localObject).read(arrayOfByte) != -1)
        paramString2.write(arrayOfByte);
    }
    catch (FileNotFoundException paramString1)
    {
      Object localObject;
      paramString1.printStackTrace();
      return;
      ((FileInputStream)localObject).close();
      paramString2.close();
      com.umeng.fb.util.c.b(paramString1);
      return;
    }
    catch (IOException paramString1)
    {
      paramString1.printStackTrace();
    }
  }

  private void a(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    if (paramInt != -1)
    {
      File localFile = new File(paramString1);
      paramString2 = new File(paramString2);
      if ((localFile.length() / paramString2.length() > 18L) && (this.c < 5))
      {
        paramString2.delete();
        new b(paramString3, -1, c.b).execute(new Void[0]);
      }
    }
    else
    {
      return;
    }
    paramString2 = new Message();
    paramString2.what = 0;
    paramString3 = FeedbackFragment.getHandler();
    if (paramString3 != null)
      paramString3.sendMessage(paramString2);
    com.umeng.fb.util.c.b(paramString1);
  }

  private int b(String paramString1, String paramString2)
  {
    if (!com.umeng.fb.util.c.c(paramString1));
    try
    {
      Thread.sleep(300L);
      return this.b.b(paramString1, paramString2);
    }
    catch (InterruptedException localInterruptedException)
    {
      while (true)
        localInterruptedException.printStackTrace();
    }
  }

  private void c(String paramString, int paramInt)
  {
    new b(paramString, -1, c.b).execute(new Void[0]);
  }

  private void d(String paramString, int paramInt)
  {
    this.f.a(paramString, paramInt);
  }

  public int a(String paramString1, String paramString2)
  {
    if (!new File(paramString2).exists())
      return this.b.a(paramString1, paramString2);
    return -1;
  }

  public void a(a parama)
  {
    this.f = parama;
  }

  public void a(String paramString)
  {
    this.c = 0;
    new b(paramString, -1, c.c).execute(new Void[0]);
  }

  public void a(String paramString, int paramInt)
  {
    this.c = 0;
    b(paramString, paramInt);
  }

  public void b(String paramString, int paramInt)
  {
    new b(paramString, paramInt, c.a).execute(new Void[0]);
  }

  public static abstract interface a
  {
    public abstract void a(String paramString, int paramInt);
  }

  class b extends AsyncTask<Void, Integer, Integer>
  {
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private int g;
    private c.c h;

    public b(String paramInt, int paramc, c.c arg4)
    {
      this.b = (com.umeng.fb.util.c.c(c.a(c.this)) + paramInt + ".raw");
      this.c = (com.umeng.fb.util.c.c(c.a(c.this)) + paramInt + ".wav");
      this.d = (com.umeng.fb.util.c.c(c.a(c.this)) + paramInt + "cache.wav");
      this.e = (com.umeng.fb.util.c.b(c.a(c.this)) + paramInt + ".opus");
      this.f = paramInt;
      this.g = paramc;
      Object localObject;
      this.h = localObject;
      c.b(c.this);
      Log.c(c.c(c.this), "TranscodeTask create task");
    }

    protected Integer a(Void[] paramArrayOfVoid)
    {
      int i = 0;
      switch (c.1.a[this.h.ordinal()])
      {
      default:
      case 1:
      case 2:
      case 3:
      }
      while (true)
      {
        return Integer.valueOf(i);
        c.a(c.this, this.b, this.c, this.g);
        continue;
        i = c.a(c.this, this.c, this.e);
        continue;
        i = c.this.a(this.e, this.d);
      }
    }

    protected void a(Integer paramInteger)
    {
      switch (c.1.a[this.h.ordinal()])
      {
      default:
        return;
      case 1:
        c.a(c.this, this.f, paramInteger.intValue());
        return;
      case 2:
        c.a(c.this, this.c, this.e, this.f, paramInteger.intValue());
        return;
      case 3:
      }
      c.b(c.this, this.d, paramInteger.intValue());
    }
  }

  static enum c
  {
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.audio.c
 * JD-Core Version:    0.6.2
 */