package com.umeng.message.proguard;

public class af
{
  // ERROR //
  public static a a(java.lang.Throwable paramThrowable)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +262 -> 263
    //   4: new 6	com/umeng/message/proguard/af$a
    //   7: dup
    //   8: invokespecial 14	com/umeng/message/proguard/af$a:<init>	()V
    //   11: astore 4
    //   13: aload_0
    //   14: invokevirtual 20	java/lang/Throwable:getCause	()Ljava/lang/Throwable;
    //   17: astore_3
    //   18: aload_3
    //   19: astore_2
    //   20: aload_3
    //   21: ifnonnull +5 -> 26
    //   24: aload_0
    //   25: astore_2
    //   26: aload_2
    //   27: ifnull +166 -> 193
    //   30: aload_2
    //   31: invokevirtual 24	java/lang/Throwable:getStackTrace	()[Ljava/lang/StackTraceElement;
    //   34: astore_3
    //   35: aload_3
    //   36: arraylength
    //   37: ifle +156 -> 193
    //   40: aload_3
    //   41: iconst_0
    //   42: aaload
    //   43: ifnull +150 -> 193
    //   46: aload_2
    //   47: invokevirtual 28	java/lang/Throwable:toString	()Ljava/lang/String;
    //   50: astore_3
    //   51: ldc 30
    //   53: astore_2
    //   54: new 32	java/io/StringWriter
    //   57: dup
    //   58: invokespecial 33	java/io/StringWriter:<init>	()V
    //   61: astore 5
    //   63: new 35	java/io/PrintWriter
    //   66: dup
    //   67: aload 5
    //   69: invokespecial 38	java/io/PrintWriter:<init>	(Ljava/io/Writer;)V
    //   72: astore 6
    //   74: aload_0
    //   75: aload 6
    //   77: invokevirtual 42	java/lang/Throwable:printStackTrace	(Ljava/io/PrintWriter;)V
    //   80: aload 5
    //   82: invokevirtual 43	java/io/StringWriter:toString	()Ljava/lang/String;
    //   85: astore_0
    //   86: aload 6
    //   88: invokevirtual 46	java/io/PrintWriter:close	()V
    //   91: aload 5
    //   93: invokevirtual 47	java/io/StringWriter:close	()V
    //   96: aload_3
    //   97: ldc 49
    //   99: invokevirtual 55	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   102: istore_1
    //   103: iload_1
    //   104: ifle +127 -> 231
    //   107: aload_3
    //   108: iload_1
    //   109: iconst_2
    //   110: iadd
    //   111: invokevirtual 59	java/lang/String:substring	(I)Ljava/lang/String;
    //   114: invokevirtual 62	java/lang/String:trim	()Ljava/lang/String;
    //   117: astore_2
    //   118: aload 4
    //   120: aload_2
    //   121: invokevirtual 65	com/umeng/message/proguard/af$a:a	(Ljava/lang/String;)V
    //   124: aload_0
    //   125: astore_2
    //   126: aload_0
    //   127: invokestatic 71	com/ta/utdid2/android/utils/StringUtils:isEmpty	(Ljava/lang/String;)Z
    //   130: ifne +12 -> 142
    //   133: aload_0
    //   134: ldc 73
    //   136: ldc 75
    //   138: invokevirtual 79	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   141: astore_2
    //   142: aload 4
    //   144: aload_2
    //   145: invokevirtual 82	com/umeng/message/proguard/af$a:c	(Ljava/lang/String;)V
    //   148: aload 4
    //   150: aload_2
    //   151: invokevirtual 86	java/lang/String:getBytes	()[B
    //   154: invokestatic 92	com/umeng/message/proguard/am:b	([B)Ljava/lang/String;
    //   157: invokevirtual 94	com/umeng/message/proguard/af$a:b	(Ljava/lang/String;)V
    //   160: aload_2
    //   161: ldc 96
    //   163: invokevirtual 100	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   166: ifne +21 -> 187
    //   169: aload_2
    //   170: ldc 102
    //   172: invokevirtual 100	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   175: ifne +12 -> 187
    //   178: aload_2
    //   179: ldc 104
    //   181: invokevirtual 100	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   184: ifeq +70 -> 254
    //   187: aload 4
    //   189: iconst_1
    //   190: invokevirtual 107	com/umeng/message/proguard/af$a:a	(Z)V
    //   193: aload 4
    //   195: areturn
    //   196: astore_0
    //   197: aload 6
    //   199: invokevirtual 46	java/io/PrintWriter:close	()V
    //   202: aload 5
    //   204: invokevirtual 47	java/io/StringWriter:close	()V
    //   207: aload_2
    //   208: astore_0
    //   209: goto -113 -> 96
    //   212: astore_0
    //   213: aload_2
    //   214: astore_0
    //   215: goto -119 -> 96
    //   218: astore_0
    //   219: aload 6
    //   221: invokevirtual 46	java/io/PrintWriter:close	()V
    //   224: aload 5
    //   226: invokevirtual 47	java/io/StringWriter:close	()V
    //   229: aload_0
    //   230: athrow
    //   231: aload_3
    //   232: ldc 109
    //   234: invokevirtual 55	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   237: istore_1
    //   238: aload_3
    //   239: astore_2
    //   240: iload_1
    //   241: ifle -123 -> 118
    //   244: aload_3
    //   245: iconst_0
    //   246: iload_1
    //   247: invokevirtual 112	java/lang/String:substring	(II)Ljava/lang/String;
    //   250: astore_2
    //   251: goto -133 -> 118
    //   254: aload 4
    //   256: iconst_0
    //   257: invokevirtual 107	com/umeng/message/proguard/af$a:a	(Z)V
    //   260: goto -67 -> 193
    //   263: aconst_null
    //   264: areturn
    //   265: astore_2
    //   266: goto -37 -> 229
    //   269: astore_2
    //   270: goto -174 -> 96
    //
    // Exception table:
    //   from	to	target	type
    //   74	86	196	java/lang/Exception
    //   197	207	212	java/lang/Exception
    //   74	86	218	finally
    //   219	229	265	java/lang/Exception
    //   86	96	269	java/lang/Exception
  }

  public static class a
  {
    String a = null;
    String b = null;
    String c = null;
    boolean d = false;

    public String a()
    {
      return this.a;
    }

    public void a(String paramString)
    {
      this.a = paramString;
    }

    public void a(boolean paramBoolean)
    {
      this.d = paramBoolean;
    }

    public String b()
    {
      return this.b;
    }

    public void b(String paramString)
    {
      this.b = paramString;
    }

    public String c()
    {
      return this.c;
    }

    public void c(String paramString)
    {
      this.c = paramString;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.af
 * JD-Core Version:    0.6.2
 */