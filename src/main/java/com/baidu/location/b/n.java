package com.baidu.location.b;

import java.io.File;
import java.io.RandomAccessFile;

public class n
{
  static n jdField_if;
  int a = 20;
  int jdField_do = 0;
  int jdField_for = 3164;
  int jdField_int = 60;
  int jdField_new = 40;
  String jdField_try = "firll.dat";

  // ERROR //
  private long a(int paramInt)
  {
    // Byte code:
    //   0: invokestatic 44	com/baidu/location/b/k:ai	()Ljava/lang/String;
    //   3: astore 5
    //   5: aload 5
    //   7: ifnonnull +7 -> 14
    //   10: ldc2_w 45
    //   13: lreturn
    //   14: new 48	java/lang/StringBuilder
    //   17: dup
    //   18: invokespecial 49	java/lang/StringBuilder:<init>	()V
    //   21: aload 5
    //   23: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   26: getstatic 58	java/io/File:separator	Ljava/lang/String;
    //   29: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: aload_0
    //   33: getfield 22	com/baidu/location/b/n:jdField_try	Ljava/lang/String;
    //   36: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   42: astore 5
    //   44: aconst_null
    //   45: astore 6
    //   47: new 63	java/io/RandomAccessFile
    //   50: dup
    //   51: aload 5
    //   53: ldc 65
    //   55: invokespecial 68	java/io/RandomAccessFile:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   58: astore 5
    //   60: iload_1
    //   61: i2l
    //   62: lstore_3
    //   63: aload 5
    //   65: lload_3
    //   66: invokevirtual 72	java/io/RandomAccessFile:seek	(J)V
    //   69: aload 5
    //   71: invokevirtual 76	java/io/RandomAccessFile:readInt	()I
    //   74: istore_1
    //   75: aload 5
    //   77: invokevirtual 80	java/io/RandomAccessFile:readLong	()J
    //   80: lstore_3
    //   81: aload 5
    //   83: invokevirtual 76	java/io/RandomAccessFile:readInt	()I
    //   86: istore_2
    //   87: iload_1
    //   88: iload_2
    //   89: if_icmpne +15 -> 104
    //   92: aload 5
    //   94: ifnull +8 -> 102
    //   97: aload 5
    //   99: invokevirtual 83	java/io/RandomAccessFile:close	()V
    //   102: lload_3
    //   103: lreturn
    //   104: aload 5
    //   106: ifnull -96 -> 10
    //   109: aload 5
    //   111: invokevirtual 83	java/io/RandomAccessFile:close	()V
    //   114: ldc2_w 45
    //   117: lreturn
    //   118: astore 5
    //   120: ldc2_w 45
    //   123: lreturn
    //   124: astore 5
    //   126: aload 6
    //   128: astore 5
    //   130: aload 5
    //   132: ifnull -122 -> 10
    //   135: aload 5
    //   137: invokevirtual 83	java/io/RandomAccessFile:close	()V
    //   140: ldc2_w 45
    //   143: lreturn
    //   144: astore 5
    //   146: ldc2_w 45
    //   149: lreturn
    //   150: astore 6
    //   152: aconst_null
    //   153: astore 5
    //   155: aload 5
    //   157: ifnull +8 -> 165
    //   160: aload 5
    //   162: invokevirtual 83	java/io/RandomAccessFile:close	()V
    //   165: aload 6
    //   167: athrow
    //   168: astore 5
    //   170: goto -68 -> 102
    //   173: astore 5
    //   175: goto -10 -> 165
    //   178: astore 6
    //   180: goto -25 -> 155
    //   183: astore 6
    //   185: goto -55 -> 130
    //
    // Exception table:
    //   from	to	target	type
    //   109	114	118	java/io/IOException
    //   47	60	124	java/lang/Exception
    //   135	140	144	java/io/IOException
    //   47	60	150	finally
    //   97	102	168	java/io/IOException
    //   160	165	173	java/io/IOException
    //   63	87	178	finally
    //   63	87	183	java/lang/Exception
  }

  private void a(int paramInt, long paramLong)
  {
    Object localObject = k.ai();
    if (localObject == null)
      return;
    localObject = (String)localObject + File.separator + this.jdField_try;
    try
    {
      localObject = new RandomAccessFile((String)localObject, "rw");
      ((RandomAccessFile)localObject).seek(paramInt);
      ((RandomAccessFile)localObject).writeInt(this.jdField_for);
      ((RandomAccessFile)localObject).writeLong(paramLong);
      ((RandomAccessFile)localObject).writeInt(this.jdField_for);
      ((RandomAccessFile)localObject).close();
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public static n jdField_if()
  {
    if (jdField_if == null)
      jdField_if = new n();
    return jdField_if;
  }

  public long a()
  {
    return a(this.a);
  }

  public void a(long paramLong)
  {
    a(this.jdField_int, paramLong);
  }

  public long jdField_do()
  {
    return a(this.jdField_do);
  }

  public void jdField_do(long paramLong)
  {
    a(this.a, paramLong);
  }

  public long jdField_for()
  {
    return a(this.jdField_int);
  }

  public void jdField_for(long paramLong)
  {
    a(this.jdField_do, paramLong);
  }

  public void jdField_if(long paramLong)
  {
    a(this.jdField_new, paramLong);
  }

  public long jdField_int()
  {
    return a(this.jdField_new);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.b.n
 * JD-Core Version:    0.6.2
 */