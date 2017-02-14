package cn.jpush.android.util;

import java.io.File;

public final class g
{
  private static final String[] z;

  static
  {
    String[] arrayOfString = new String[5];
    int j = 0;
    Object localObject2 = "tP1";
    int i = -1;
    Object localObject1 = arrayOfString;
    char[] arrayOfChar = ((String)localObject2).toCharArray();
    int k = arrayOfChar.length;
    int i1 = 0;
    int m = 0;
    int i3 = i;
    localObject2 = arrayOfChar;
    int i4 = j;
    Object localObject3 = localObject1;
    int n = k;
    Object localObject4;
    int i2;
    if (k <= 1)
    {
      localObject4 = localObject1;
      localObject1 = arrayOfChar;
      i2 = i;
      label67: n = m;
      label70: localObject2 = localObject1;
      i1 = localObject2[m];
      switch (n % 5)
      {
      default:
        i = 96;
      case 0:
      case 1:
      case 2:
      case 3:
      }
    }
    while (true)
    {
      localObject2[m] = ((char)(i ^ i1));
      n += 1;
      if (k == 0)
      {
        m = k;
        break label70;
      }
      i1 = n;
      n = k;
      localObject3 = localObject4;
      i4 = j;
      localObject2 = localObject1;
      i3 = i2;
      i2 = i3;
      localObject1 = localObject2;
      j = i4;
      localObject4 = localObject3;
      k = n;
      m = i1;
      if (n > i1)
        break label67;
      localObject1 = new String((char[])localObject2).intern();
      switch (i3)
      {
      default:
        localObject3[i4] = localObject1;
        j = 1;
        localObject2 = "Tp1K\006K{iK\023\\fr\016\022\031gm\017\005\0034";
        i = 0;
        localObject1 = arrayOfString;
        break;
      case 0:
        localObject3[i4] = localObject1;
        j = 2;
        localObject2 = "\031pkK\016V`$\005\005\\p$\b\b\\woK-}!$\b\017]q(K\022\\`q\031\016\031`v\036\005";
        i = 1;
        localObject1 = arrayOfString;
        break;
      case 1:
        localObject3[i4] = localObject1;
        j = 3;
        localObject2 = "M|aK\r]!$\r\022Vy$\030\005Kba\031@Pg>K";
        i = 2;
        localObject1 = arrayOfString;
        break;
      case 2:
        localObject3[i4] = localObject1;
        j = 4;
        localObject2 = "Tp1K\tW4p\003\005\031wh\002\005M4b\002\f\\.$";
        i = 3;
        localObject1 = arrayOfString;
        break;
      case 3:
        localObject3[i4] = localObject1;
        z = arrayOfString;
        return;
        i = 57;
        continue;
        i = 20;
        continue;
        i = 4;
        continue;
        i = 107;
      }
    }
  }

  public static boolean a(String paramString, File paramFile)
  {
    new StringBuilder(z[1]).append(paramString).toString();
    x.c();
    if ((paramString == null) || ("".equals(paramString)))
    {
      new StringBuilder(z[3]).append(paramString).append(z[2]).toString();
      x.c();
      return true;
    }
    if ((!paramFile.exists()) || (paramFile.length() == 0L))
      return false;
    paramFile = b(paramFile);
    new StringBuilder(z[4]).append(paramFile).toString();
    x.c();
    if ((paramFile != null) && (!"".equals(paramFile)) && (paramFile.equals(paramString)))
    {
      x.c();
      return true;
    }
    x.c();
    return false;
  }

  // ERROR //
  private static byte[] a(File paramFile)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 81	java/io/FileInputStream
    //   5: dup
    //   6: aload_0
    //   7: invokespecial 84	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   10: astore_2
    //   11: aload_2
    //   12: astore_0
    //   13: sipush 1024
    //   16: newarray byte
    //   18: astore 4
    //   20: aload_2
    //   21: astore_0
    //   22: getstatic 34	cn/jpush/android/util/g:z	[Ljava/lang/String;
    //   25: iconst_0
    //   26: aaload
    //   27: invokestatic 90	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   30: astore 5
    //   32: aload_2
    //   33: astore_0
    //   34: aload_2
    //   35: aload 4
    //   37: invokevirtual 96	java/io/InputStream:read	([B)I
    //   40: istore_1
    //   41: iload_1
    //   42: ifle +14 -> 56
    //   45: aload_2
    //   46: astore_0
    //   47: aload 5
    //   49: aload 4
    //   51: iconst_0
    //   52: iload_1
    //   53: invokevirtual 100	java/security/MessageDigest:update	([BII)V
    //   56: iload_1
    //   57: iconst_m1
    //   58: if_icmpne -26 -> 32
    //   61: aload_2
    //   62: ifnull +7 -> 69
    //   65: aload_2
    //   66: invokevirtual 103	java/io/InputStream:close	()V
    //   69: aload 5
    //   71: invokevirtual 107	java/security/MessageDigest:digest	()[B
    //   74: astore_0
    //   75: aload_0
    //   76: areturn
    //   77: astore_0
    //   78: invokestatic 110	cn/jpush/android/util/x:h	()V
    //   81: aconst_null
    //   82: areturn
    //   83: astore_0
    //   84: aconst_null
    //   85: astore_2
    //   86: aload_2
    //   87: astore_0
    //   88: invokestatic 110	cn/jpush/android/util/x:h	()V
    //   91: aload_3
    //   92: astore_0
    //   93: aload_2
    //   94: ifnull -19 -> 75
    //   97: aload_2
    //   98: invokevirtual 103	java/io/InputStream:close	()V
    //   101: aconst_null
    //   102: areturn
    //   103: astore_0
    //   104: invokestatic 110	cn/jpush/android/util/x:h	()V
    //   107: aconst_null
    //   108: areturn
    //   109: astore_2
    //   110: aconst_null
    //   111: astore_0
    //   112: aload_0
    //   113: ifnull +7 -> 120
    //   116: aload_0
    //   117: invokevirtual 103	java/io/InputStream:close	()V
    //   120: aload_2
    //   121: athrow
    //   122: astore_0
    //   123: invokestatic 110	cn/jpush/android/util/x:h	()V
    //   126: aconst_null
    //   127: areturn
    //   128: astore_2
    //   129: goto -17 -> 112
    //   132: astore_0
    //   133: goto -47 -> 86
    //
    // Exception table:
    //   from	to	target	type
    //   65	69	77	java/io/IOException
    //   2	11	83	java/lang/Exception
    //   97	101	103	java/io/IOException
    //   2	11	109	finally
    //   116	120	122	java/io/IOException
    //   13	20	128	finally
    //   22	32	128	finally
    //   34	41	128	finally
    //   47	56	128	finally
    //   88	91	128	finally
    //   13	20	132	java/lang/Exception
    //   22	32	132	java/lang/Exception
    //   34	41	132	java/lang/Exception
    //   47	56	132	java/lang/Exception
  }

  private static String b(File paramFile)
  {
    byte[] arrayOfByte = a(paramFile);
    paramFile = "";
    File localFile = paramFile;
    if (arrayOfByte != null)
    {
      localFile = paramFile;
      if (arrayOfByte.length > 0)
      {
        int i = 0;
        while (true)
        {
          localFile = paramFile;
          if (i >= arrayOfByte.length)
            break;
          paramFile = paramFile + Integer.toString((arrayOfByte[i] & 0xFF) + 256, 16).substring(1);
          i += 1;
        }
      }
    }
    return localFile;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.util.g
 * JD-Core Version:    0.6.2
 */