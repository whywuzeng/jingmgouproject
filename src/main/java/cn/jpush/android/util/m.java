package cn.jpush.android.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public final class m
{
  private static final String[] z;

  static
  {
    String[] arrayOfString = new String[4];
    Object localObject1 = "\033j\\zD";
    int i = -1;
    int j = 0;
    Object localObject3 = arrayOfString;
    localObject1 = ((String)localObject1).toCharArray();
    int k = localObject1.length;
    int m;
    int n;
    int i1;
    label42: Object localObject2;
    int i2;
    label91: label113: Object localObject4;
    int i3;
    if (k <= 1)
    {
      m = 0;
      n = j;
      i1 = i;
      j = m;
      while (true)
      {
        localObject2 = localObject1;
        i2 = localObject2[m];
        switch (j % 5)
        {
        default:
          i = 124;
          localObject2[m] = ((char)(i ^ i2));
          j += 1;
          if (k != 0)
            break label113;
          m = k;
        case 0:
        case 1:
        case 2:
        case 3:
        }
      }
      i = k;
      localObject4 = localObject3;
      i3 = n;
      localObject2 = localObject1;
      i2 = i1;
    }
    while (true)
    {
      i1 = i2;
      localObject1 = localObject2;
      n = i3;
      localObject3 = localObject4;
      k = i;
      m = j;
      if (i > j)
        break label42;
      localObject1 = new String(localObject2).intern();
      switch (i2)
      {
      default:
        localObject4[i3] = localObject1;
        localObject1 = "/]n>\023 \004y%\031/J\037\b#R\\>\020+\0367w\032'R\007\035:V ";
        j = 1;
        i = 0;
        break;
      case 0:
        localObject4[i3] = localObject1;
        localObject1 = "b\036y8\022:[t#F";
        j = 2;
        i = 1;
        break;
      case 1:
        localObject4[i3] = localObject1;
        j = 3;
        localObject1 = "";
        i = 2;
        break;
      case 2:
        localObject4[i3] = localObject1;
        z = arrayOfString;
        return;
        i = 78;
        break label91;
        i = 62;
        break label91;
        i = 26;
        break label91;
        i = 87;
        break label91;
        m = 0;
        i2 = i;
        localObject2 = localObject1;
        i3 = j;
        localObject4 = localObject3;
        i = k;
        j = m;
      }
    }
  }

  public static ArrayList<String> a(InputStream paramInputStream)
  {
    ArrayList localArrayList = new ArrayList();
    BufferedReader localBufferedReader;
    try
    {
      paramInputStream = new InputStreamReader(paramInputStream, z[0]);
      localBufferedReader = new BufferedReader(paramInputStream, 2048);
      while (true)
      {
        String str = localBufferedReader.readLine();
        if (str == null)
          break;
        str = str.trim();
        if (!"".equals(str))
          localArrayList.add(str);
      }
    }
    catch (Exception paramInputStream)
    {
      paramInputStream.getMessage();
      x.f();
      return localArrayList;
    }
    paramInputStream.close();
    localBufferedReader.close();
    return localArrayList;
  }

  public static void a(String paramString)
  {
    paramString = new File(paramString);
    if (paramString.exists())
    {
      if (paramString.isDirectory())
      {
        File[] arrayOfFile = paramString.listFiles();
        int j = arrayOfFile.length;
        int i = 0;
        while (i < j)
        {
          File localFile = arrayOfFile[i];
          a(localFile.getAbsolutePath());
          localFile.delete();
          i += 1;
        }
      }
      paramString.delete();
    }
  }

  // ERROR //
  public static boolean a(String paramString1, String paramString2, android.content.Context paramContext)
  {
    // Byte code:
    //   0: aload_2
    //   1: ifnonnull +16 -> 17
    //   4: new 109	java/lang/IllegalArgumentException
    //   7: dup
    //   8: getstatic 32	cn/jpush/android/util/m:z	[Ljava/lang/String;
    //   11: iconst_3
    //   12: aaload
    //   13: invokespecial 110	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   16: athrow
    //   17: new 112	java/lang/StringBuilder
    //   20: dup
    //   21: getstatic 32	cn/jpush/android/util/m:z	[Ljava/lang/String;
    //   24: iconst_1
    //   25: aaload
    //   26: invokespecial 113	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   29: aload_0
    //   30: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: getstatic 32	cn/jpush/android/util/m:z	[Ljava/lang/String;
    //   36: iconst_2
    //   37: aaload
    //   38: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: aload_1
    //   42: invokevirtual 117	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: invokevirtual 120	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   48: pop
    //   49: invokestatic 123	cn/jpush/android/util/x:b	()V
    //   52: aload_0
    //   53: invokestatic 129	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   56: ifne +77 -> 133
    //   59: aload_1
    //   60: invokestatic 129	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   63: ifne +70 -> 133
    //   66: new 83	java/io/File
    //   69: dup
    //   70: aload_0
    //   71: invokespecial 85	java/io/File:<init>	(Ljava/lang/String;)V
    //   74: astore_0
    //   75: aload_0
    //   76: invokevirtual 89	java/io/File:exists	()Z
    //   79: ifne +8 -> 87
    //   82: aload_0
    //   83: invokevirtual 132	java/io/File:createNewFile	()Z
    //   86: pop
    //   87: new 134	java/io/FileOutputStream
    //   90: dup
    //   91: aload_0
    //   92: invokespecial 137	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   95: astore_2
    //   96: aload_2
    //   97: aload_1
    //   98: getstatic 32	cn/jpush/android/util/m:z	[Ljava/lang/String;
    //   101: iconst_0
    //   102: aaload
    //   103: invokevirtual 141	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   106: invokevirtual 145	java/io/FileOutputStream:write	([B)V
    //   109: aload_2
    //   110: invokevirtual 148	java/io/FileOutputStream:flush	()V
    //   113: aload_2
    //   114: invokevirtual 149	java/io/FileOutputStream:close	()V
    //   117: iconst_1
    //   118: ireturn
    //   119: aload_1
    //   120: ifnull +7 -> 127
    //   123: aload_1
    //   124: invokevirtual 149	java/io/FileOutputStream:close	()V
    //   127: aload_0
    //   128: athrow
    //   129: astore_0
    //   130: invokestatic 152	cn/jpush/android/util/x:h	()V
    //   133: iconst_0
    //   134: ireturn
    //   135: astore_0
    //   136: aload_2
    //   137: astore_1
    //   138: goto -19 -> 119
    //   141: astore_0
    //   142: aconst_null
    //   143: astore_1
    //   144: goto -25 -> 119
    //
    // Exception table:
    //   from	to	target	type
    //   66	87	129	java/io/IOException
    //   113	117	129	java/io/IOException
    //   123	127	129	java/io/IOException
    //   127	129	129	java/io/IOException
    //   96	113	135	finally
    //   87	96	141	finally
  }

  // ERROR //
  public static boolean a(String paramString, byte[] paramArrayOfByte, android.content.Context paramContext)
  {
    // Byte code:
    //   0: aload_2
    //   1: invokestatic 158	cn/jpush/android/util/k:a	(Landroid/content/Context;)V
    //   4: aload_0
    //   5: invokestatic 129	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   8: ifne +66 -> 74
    //   11: aload_1
    //   12: arraylength
    //   13: ifle +61 -> 74
    //   16: new 83	java/io/File
    //   19: dup
    //   20: aload_0
    //   21: invokespecial 85	java/io/File:<init>	(Ljava/lang/String;)V
    //   24: astore_0
    //   25: aload_0
    //   26: invokevirtual 89	java/io/File:exists	()Z
    //   29: ifne +8 -> 37
    //   32: aload_0
    //   33: invokevirtual 132	java/io/File:createNewFile	()Z
    //   36: pop
    //   37: new 134	java/io/FileOutputStream
    //   40: dup
    //   41: aload_0
    //   42: invokespecial 137	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   45: astore_2
    //   46: aload_2
    //   47: aload_1
    //   48: invokevirtual 145	java/io/FileOutputStream:write	([B)V
    //   51: aload_2
    //   52: invokevirtual 148	java/io/FileOutputStream:flush	()V
    //   55: aload_2
    //   56: invokevirtual 149	java/io/FileOutputStream:close	()V
    //   59: iconst_1
    //   60: ireturn
    //   61: astore_0
    //   62: aconst_null
    //   63: astore_1
    //   64: aload_1
    //   65: ifnull +7 -> 72
    //   68: aload_1
    //   69: invokevirtual 149	java/io/FileOutputStream:close	()V
    //   72: aload_0
    //   73: athrow
    //   74: iconst_0
    //   75: ireturn
    //   76: astore_0
    //   77: aload_2
    //   78: astore_1
    //   79: goto -15 -> 64
    //
    // Exception table:
    //   from	to	target	type
    //   37	46	61	finally
    //   46	55	76	finally
  }

  public static String b(String paramString)
  {
    if (!ai.a(paramString))
    {
      int i = paramString.lastIndexOf(".");
      int j = paramString.length();
      if ((i > 0) && (i + 1 != j))
        return paramString.substring(i, paramString.length());
    }
    return "";
  }

  public static String c(String paramString)
  {
    if (ai.a(paramString))
      return "";
    return paramString.substring(paramString.lastIndexOf("/") + 1, paramString.length());
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.jpush.android.util.m
 * JD-Core Version:    0.6.2
 */