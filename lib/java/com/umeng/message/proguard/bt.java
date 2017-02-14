package com.umeng.message.proguard;

import android.os.Process;

public class bt
{
  public static int a()
  {
    return Process.myPid();
  }

  // ERROR //
  public static void a(android.content.Context paramContext, java.lang.String paramString)
  {
    // Byte code:
    //   0: new 21	java/io/File
    //   3: dup
    //   4: aload_0
    //   5: invokevirtual 27	android/content/Context:getFilesDir	()Ljava/io/File;
    //   8: aload_1
    //   9: invokespecial 30	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   12: astore_0
    //   13: ldc 31
    //   15: aload_0
    //   16: invokevirtual 35	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   19: invokestatic 41	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   22: pop
    //   23: aload_0
    //   24: invokevirtual 45	java/io/File:exists	()Z
    //   27: ifeq +8 -> 35
    //   30: aload_0
    //   31: invokevirtual 48	java/io/File:delete	()Z
    //   34: pop
    //   35: aload_0
    //   36: invokevirtual 51	java/io/File:createNewFile	()Z
    //   39: pop
    //   40: invokestatic 53	com/umeng/message/proguard/bt:a	()I
    //   43: istore_2
    //   44: new 55	java/io/FileWriter
    //   47: dup
    //   48: aload_0
    //   49: invokespecial 58	java/io/FileWriter:<init>	(Ljava/io/File;)V
    //   52: astore_1
    //   53: aload_1
    //   54: astore_0
    //   55: aload_1
    //   56: iload_2
    //   57: invokestatic 64	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   60: invokevirtual 68	java/lang/String:toCharArray	()[C
    //   63: invokevirtual 72	java/io/FileWriter:write	([C)V
    //   66: aload_1
    //   67: invokevirtual 75	java/io/FileWriter:flush	()V
    //   70: aload_1
    //   71: invokevirtual 78	java/io/FileWriter:close	()V
    //   74: return
    //   75: astore_0
    //   76: ldc 80
    //   78: ldc 82
    //   80: aload_0
    //   81: invokestatic 86	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   84: pop
    //   85: return
    //   86: astore_0
    //   87: ldc 88
    //   89: ldc 90
    //   91: aload_0
    //   92: invokestatic 86	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   95: pop
    //   96: return
    //   97: astore_3
    //   98: aconst_null
    //   99: astore_1
    //   100: aload_1
    //   101: astore_0
    //   102: ldc 88
    //   104: ldc 90
    //   106: aload_3
    //   107: invokestatic 86	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   110: pop
    //   111: aload_1
    //   112: invokevirtual 75	java/io/FileWriter:flush	()V
    //   115: aload_1
    //   116: invokevirtual 78	java/io/FileWriter:close	()V
    //   119: return
    //   120: astore_0
    //   121: ldc 88
    //   123: ldc 90
    //   125: aload_0
    //   126: invokestatic 86	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   129: pop
    //   130: return
    //   131: astore_1
    //   132: aconst_null
    //   133: astore_0
    //   134: aload_0
    //   135: invokevirtual 75	java/io/FileWriter:flush	()V
    //   138: aload_0
    //   139: invokevirtual 78	java/io/FileWriter:close	()V
    //   142: aload_1
    //   143: athrow
    //   144: astore_0
    //   145: ldc 88
    //   147: ldc 90
    //   149: aload_0
    //   150: invokestatic 86	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   153: pop
    //   154: goto -12 -> 142
    //   157: astore_1
    //   158: goto -24 -> 134
    //   161: astore_3
    //   162: goto -62 -> 100
    //
    // Exception table:
    //   from	to	target	type
    //   35	40	75	java/io/IOException
    //   66	74	86	java/io/IOException
    //   44	53	97	java/io/IOException
    //   111	119	120	java/io/IOException
    //   44	53	131	finally
    //   134	142	144	java/io/IOException
    //   55	66	157	finally
    //   102	111	157	finally
    //   55	66	161	java/io/IOException
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.bt
 * JD-Core Version:    0.6.2
 */