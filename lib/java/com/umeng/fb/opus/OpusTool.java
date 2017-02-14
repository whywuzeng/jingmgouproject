package com.umeng.fb.opus;

import android.content.Context;
import com.umeng.fb.common.b;
import com.umeng.fb.util.Log;
import java.io.File;

public class OpusTool
{
  private static final String a = OpusTool.class.getName();
  private static Context b;

  static
  {
    try
    {
      System.loadLibrary("umeng_opustool");
      return;
    }
    catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
    {
      do
        Log.b(a, "Could not load library of opustool");
      while (!b.a(b).d());
      b.a(b).c(false);
    }
  }

  public OpusTool(Context paramContext)
  {
    b = paramContext;
  }

  public static boolean a()
  {
    return b.a(b).d();
  }

  private native int decode_opus_file(String paramString1, String paramString2);

  private native int encode_wav_file(String paramString1, String paramString2);

  private native String nativeGetString();

  public int a(String paramString1, String paramString2)
  {
    if (!new File(paramString1).exists())
    {
      Log.c(a, "opus is not exists");
      return -1;
    }
    return decode_opus_file(paramString1, paramString2);
  }

  public int b(String paramString1, String paramString2)
  {
    if (!new File(paramString1).exists())
    {
      Log.c(a, "wave is not exists");
      return -1;
    }
    return encode_wav_file(paramString1, paramString2);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.opus.OpusTool
 * JD-Core Version:    0.6.2
 */