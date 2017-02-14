package com.umeng.message.proguard;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.android.agoo.ut.UTFactroy;
import org.android.du.CpuType;

public class bO
{
  public static File a(Context paramContext, String paramString)
  {
    try
    {
      paramString = new File(bM.g(paramContext, paramString));
      if ((paramString != null) && (paramString.exists()))
      {
        bM.d(paramContext, "5", "push");
        return paramString;
      }
      bM.d(paramContext, "4", "push");
      UTFactroy.getInstance().commitEvent(paramContext, null, new String[] { "签名失败或者找不到升级包" });
      Log.e("LoadJarUtil", "sign check failed");
      return null;
    }
    catch (Throwable paramString)
    {
      bM.d(paramContext, "4", "push");
    }
    return null;
  }

  // ERROR //
  public static Certificate a(File paramFile, Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +200 -> 201
    //   4: aload_0
    //   5: invokevirtual 28	java/io/File:exists	()Z
    //   8: ifeq +193 -> 201
    //   11: new 67	java/util/jar/JarFile
    //   14: dup
    //   15: aload_0
    //   16: invokespecial 70	java/util/jar/JarFile:<init>	(Ljava/io/File;)V
    //   19: astore 6
    //   21: sipush 2048
    //   24: newarray byte
    //   26: astore 7
    //   28: aload 6
    //   30: invokevirtual 74	java/util/jar/JarFile:entries	()Ljava/util/Enumeration;
    //   33: astore 8
    //   35: aconst_null
    //   36: astore 4
    //   38: aload 4
    //   40: astore_0
    //   41: aload 8
    //   43: invokeinterface 79 1 0
    //   48: ifeq +118 -> 166
    //   51: aload 4
    //   53: astore_0
    //   54: aload 8
    //   56: invokeinterface 83 1 0
    //   61: checkcast 85	java/util/jar/JarEntry
    //   64: astore 5
    //   66: aload 4
    //   68: astore_0
    //   69: aload 5
    //   71: invokevirtual 89	java/util/jar/JarEntry:getName	()Ljava/lang/String;
    //   74: ldc 91
    //   76: invokevirtual 95	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   79: ifne -41 -> 38
    //   82: aload 4
    //   84: astore_0
    //   85: aload 6
    //   87: aload 5
    //   89: aload 7
    //   91: invokestatic 98	com/umeng/message/proguard/bO:a	(Ljava/util/jar/JarFile;Ljava/util/jar/JarEntry;[B)[Ljava/security/cert/Certificate;
    //   94: astore 9
    //   96: aload 9
    //   98: ifnull +55 -> 153
    //   101: iconst_0
    //   102: istore_2
    //   103: aload 4
    //   105: astore 5
    //   107: aload 5
    //   109: astore 4
    //   111: aload 5
    //   113: astore_0
    //   114: iload_2
    //   115: aload 9
    //   117: arraylength
    //   118: if_icmpge -80 -> 38
    //   121: aload 5
    //   123: astore 4
    //   125: aload 5
    //   127: ifnonnull +9 -> 136
    //   130: aload 9
    //   132: iload_2
    //   133: aaload
    //   134: astore 4
    //   136: aload 4
    //   138: astore_0
    //   139: aload 4
    //   141: aload 9
    //   143: iload_2
    //   144: aaload
    //   145: invokevirtual 104	java/security/cert/Certificate:equals	(Ljava/lang/Object;)Z
    //   148: istore_3
    //   149: iload_3
    //   150: ifne +5 -> 155
    //   153: aconst_null
    //   154: areturn
    //   155: iload_2
    //   156: iconst_1
    //   157: iadd
    //   158: istore_2
    //   159: aload 4
    //   161: astore 5
    //   163: goto -56 -> 107
    //   166: aload 4
    //   168: astore_0
    //   169: aload_0
    //   170: areturn
    //   171: astore 4
    //   173: aconst_null
    //   174: astore_0
    //   175: invokestatic 44	org/android/agoo/ut/UTFactroy:getInstance	()Lorg/android/agoo/ut/UTFactroy;
    //   178: aload_1
    //   179: aload 4
    //   181: iconst_1
    //   182: anewarray 46	java/lang/String
    //   185: dup
    //   186: iconst_0
    //   187: ldc 106
    //   189: aastore
    //   190: invokevirtual 52	org/android/agoo/ut/UTFactroy:commitEvent	(Landroid/content/Context;Ljava/lang/Object;[Ljava/lang/String;)V
    //   193: goto -24 -> 169
    //   196: astore 4
    //   198: goto -23 -> 175
    //   201: aconst_null
    //   202: astore_0
    //   203: goto -34 -> 169
    //
    // Exception table:
    //   from	to	target	type
    //   11	35	171	java/io/IOException
    //   41	51	196	java/io/IOException
    //   54	66	196	java/io/IOException
    //   69	82	196	java/io/IOException
    //   85	96	196	java/io/IOException
    //   114	121	196	java/io/IOException
    //   139	149	196	java/io/IOException
  }

  public static CpuType a()
  {
    CpuType localCpuType2 = CpuType.armeabi;
    Object localObject = localCpuType2;
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"), 8192);
      localObject = localCpuType2;
      String str = localBufferedReader.readLine();
      CpuType localCpuType1 = localCpuType2;
      localObject = localCpuType2;
      if (!TextUtils.isEmpty(str))
      {
        localObject = localCpuType2;
        if (!str.contains(CpuType.x86.name()))
          break label76;
        localObject = localCpuType2;
        localCpuType1 = CpuType.x86;
      }
      while (true)
      {
        localObject = localCpuType1;
        localBufferedReader.close();
        return localCpuType1;
        label76: localObject = localCpuType2;
        if (str.contains(CpuType.mips.name()))
        {
          localObject = localCpuType2;
          localCpuType1 = CpuType.mips;
        }
        else
        {
          localObject = localCpuType2;
          localCpuType1 = CpuType.armeabi;
        }
      }
    }
    catch (IOException localIOException)
    {
    }
    return localObject;
  }

  public static boolean a(Context paramContext, String paramString1, String paramString2)
  {
    Object localObject = new File(paramString2);
    if (!((File)localObject).exists())
      ((File)localObject).mkdirs();
    while (true)
    {
      try
      {
        int i = paramString1.lastIndexOf(File.separator);
        if (i == -1)
          break label158;
        localObject = paramString1.substring(i + 1);
        paramString1 = paramContext.getAssets().open(paramString1);
        paramString2 = new FileOutputStream(paramString2 + File.separator + (String)localObject);
        localObject = new byte[1024];
        i = 0;
        int j = paramString1.read((byte[])localObject);
        if (j != -1)
        {
          i += j;
          paramString2.write((byte[])localObject, 0, j);
          continue;
        }
      }
      catch (Throwable paramString1)
      {
        UTFactroy.getInstance().commitEvent(paramContext, paramString1, new String[] { "copyAssertFile2 error" });
        return true;
      }
      paramString1.close();
      paramString2.close();
      return true;
      label158: localObject = paramString1;
    }
  }

  public static boolean a(Context paramContext, CpuType paramCpuType, String paramString1, String paramString2)
  {
    Object localObject = null;
    try
    {
      String[] arrayOfString = paramContext.getAssets().list(paramString1);
      localObject = arrayOfString;
      if ((localObject != null) && (localObject.length > 0))
      {
        i = 0;
        while (true)
        {
          if (i >= localObject.length)
            break label159;
          if (-1 != localObject[i].indexOf("."))
            break;
          if (localObject[i].contains(paramCpuType.name()))
            a(paramContext, paramCpuType, paramString1 + File.separator + localObject[i], paramString2);
          i += 1;
        }
      }
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        int i;
        localIOException.printStackTrace();
        continue;
        a(paramContext, paramString1 + File.separator + localObject[i], paramString2);
      }
    }
    label159: return false;
  }

  public static boolean a(String paramString1, String paramString2)
  {
    int i = 0;
    int j = 0;
    Object localObject1 = a();
    Object localObject2 = new File(paramString1);
    if (((File)localObject2).exists())
    {
      if (((File)localObject2).isDirectory())
      {
        localObject2 = ((File)localObject2).listFiles();
        i = j;
        while (i < localObject2.length)
        {
          if (((localObject2[i].isDirectory()) && (localObject2[i].getName().contains(((CpuType)localObject1).name()))) || (localObject2[i].isFile()))
            a(paramString1 + File.separator + localObject2[i].getName(), paramString2);
          i += 1;
        }
      }
      j = paramString1.lastIndexOf(File.separator);
      localObject1 = paramString1;
      if (j != -1)
        localObject1 = paramString1.substring(j + 1);
      paramString2 = new File(paramString2 + (String)localObject1);
      if (!paramString2.getParentFile().exists())
        paramString2.getParentFile().mkdirs();
      if (paramString2.exists())
        paramString2.delete();
      try
      {
        paramString1 = new FileInputStream((File)localObject2);
        paramString2 = new FileOutputStream(paramString2);
        localObject1 = new byte[1024];
        while (true)
        {
          j = paramString1.read((byte[])localObject1);
          if (j == -1)
            break;
          i += j;
          paramString2.write((byte[])localObject1, 0, j);
        }
      }
      catch (Throwable paramString1)
      {
        paramString1.printStackTrace();
      }
    }
    while (true)
    {
      return true;
      paramString1.close();
      paramString2.close();
    }
  }

  private static Certificate[] a(JarFile paramJarFile, JarEntry paramJarEntry, byte[] paramArrayOfByte)
  {
    Object localObject = null;
    try
    {
      paramJarFile = new BufferedInputStream(paramJarFile.getInputStream(paramJarEntry));
      while (paramJarFile.read(paramArrayOfByte, 0, paramArrayOfByte.length) != -1);
      paramJarFile.close();
      paramJarFile = localObject;
      if (paramJarEntry != null)
        paramJarFile = paramJarEntry.getCertificates();
      return paramJarFile;
    }
    catch (RuntimeException paramJarFile)
    {
      paramJarFile.printStackTrace();
      return null;
    }
    catch (IOException paramJarFile)
    {
    }
    return null;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.bO
 * JD-Core Version:    0.6.2
 */