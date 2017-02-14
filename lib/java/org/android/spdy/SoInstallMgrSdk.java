package org.android.spdy;

import android.content.Context;
import android.os.Build;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class SoInstallMgrSdk
{
  private static final String ARMEABI = "armeabi";
  private static final int EventID_SO_INIT = 21033;
  static final String LOGTAG = "INIT_SO";
  private static final String MIPS = "mips";
  private static final String X86 = "x86";
  static Context mContext = null;

  private static String _cpuType()
  {
    String str2 = _getFieldReflectively(new Build(), "CPU_ABI");
    String str1;
    if ((str2 != null) && (str2.length() != 0))
    {
      str1 = str2;
      if (!str2.equals("Unknown"));
    }
    else
    {
      str1 = "armeabi";
    }
    return str1.toLowerCase();
  }

  private static String _getFieldReflectively(Build paramBuild, String paramString)
  {
    try
    {
      paramBuild = Build.class.getField(paramString).get(paramBuild).toString();
      return paramBuild;
    }
    catch (Exception paramBuild)
    {
    }
    return "Unknown";
  }

  static boolean _loadUnzipSo(String paramString, int paramInt, ClassLoader paramClassLoader)
  {
    boolean bool = true;
    try
    {
      if (isExist(paramString, paramInt))
      {
        if (paramClassLoader == null)
        {
          System.load(_targetSoFile(paramString, paramInt));
          return true;
        }
        Runtime localRuntime = Runtime.getRuntime();
        Method localMethod = Runtime.class.getDeclaredMethod("load", new Class[] { String.class, ClassLoader.class });
        localMethod.setAccessible(true);
        localMethod.invoke(localRuntime, new Object[] { _targetSoFile(paramString, paramInt), paramClassLoader });
        return true;
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      return false;
    }
    catch (UnsatisfiedLinkError paramString)
    {
      paramString.printStackTrace();
      return false;
    }
    catch (Error paramString)
    {
      paramString.printStackTrace();
      bool = false;
    }
    return bool;
  }

  static String _targetSoFile(String paramString, int paramInt)
  {
    Object localObject = mContext;
    if (localObject == null)
      return "";
    String str = "/data/data/" + ((Context)localObject).getPackageName() + "/files";
    localObject = ((Context)localObject).getFilesDir();
    if (localObject != null)
      str = ((File)localObject).getPath();
    return str + "/lib" + paramString + "bk" + paramInt + ".so";
  }

  public static void init(Context paramContext)
  {
    mContext = paramContext;
  }

  public static boolean initSo(String paramString, int paramInt)
  {
    return initSo(paramString, paramInt, null);
  }

  // ERROR //
  public static boolean initSo(String paramString, int paramInt, ClassLoader paramClassLoader)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore 4
    //   3: aload_2
    //   4: ifnonnull +40 -> 44
    //   7: aload_0
    //   8: invokestatic 170	java/lang/System:loadLibrary	(Ljava/lang/String;)V
    //   11: iload 4
    //   13: istore_3
    //   14: iload 4
    //   16: ifne +162 -> 178
    //   19: aload_0
    //   20: iload_1
    //   21: invokestatic 83	org/android/spdy/SoInstallMgrSdk:isExist	(Ljava/lang/String;I)Z
    //   24: ifeq +121 -> 145
    //   27: aload_0
    //   28: iload_1
    //   29: aload_2
    //   30: invokestatic 172	org/android/spdy/SoInstallMgrSdk:_loadUnzipSo	(Ljava/lang/String;ILjava/lang/ClassLoader;)Z
    //   33: istore_3
    //   34: iload_3
    //   35: ifeq +105 -> 140
    //   38: iload_3
    //   39: istore 4
    //   41: iload 4
    //   43: ireturn
    //   44: invokestatic 99	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   47: astore 5
    //   49: ldc 95
    //   51: ldc 173
    //   53: iconst_2
    //   54: anewarray 60	java/lang/Class
    //   57: dup
    //   58: iconst_0
    //   59: ldc 43
    //   61: aastore
    //   62: dup
    //   63: iconst_1
    //   64: ldc 102
    //   66: aastore
    //   67: invokevirtual 106	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   70: astore 6
    //   72: aload 6
    //   74: iconst_1
    //   75: invokevirtual 112	java/lang/reflect/Method:setAccessible	(Z)V
    //   78: aload 6
    //   80: aload 5
    //   82: iconst_2
    //   83: anewarray 4	java/lang/Object
    //   86: dup
    //   87: iconst_0
    //   88: aload_0
    //   89: aastore
    //   90: dup
    //   91: iconst_1
    //   92: aload_2
    //   93: aastore
    //   94: invokevirtual 116	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   97: pop
    //   98: goto -87 -> 11
    //   101: astore 5
    //   103: aload 5
    //   105: invokevirtual 119	java/lang/Exception:printStackTrace	()V
    //   108: iconst_0
    //   109: istore 4
    //   111: goto -100 -> 11
    //   114: astore 5
    //   116: aload 5
    //   118: invokevirtual 120	java/lang/UnsatisfiedLinkError:printStackTrace	()V
    //   121: iconst_0
    //   122: istore 4
    //   124: goto -113 -> 11
    //   127: astore 5
    //   129: aload 5
    //   131: invokevirtual 121	java/lang/Error:printStackTrace	()V
    //   134: iconst_0
    //   135: istore 4
    //   137: goto -126 -> 11
    //   140: aload_0
    //   141: iload_1
    //   142: invokestatic 177	org/android/spdy/SoInstallMgrSdk:removeSoIfExit	(Ljava/lang/String;I)V
    //   145: invokestatic 179	org/android/spdy/SoInstallMgrSdk:_cpuType	()Ljava/lang/String;
    //   148: astore 5
    //   150: iload 4
    //   152: istore_3
    //   153: aload 5
    //   155: ldc 17
    //   157: invokevirtual 183	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   160: ifne +18 -> 178
    //   163: aload 5
    //   165: ldc 20
    //   167: invokevirtual 183	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   170: istore_3
    //   171: iload_3
    //   172: ifeq +15 -> 187
    //   175: iload 4
    //   177: istore_3
    //   178: iload_3
    //   179: istore 4
    //   181: iload_3
    //   182: ifne -141 -> 41
    //   185: iload_3
    //   186: ireturn
    //   187: aload_0
    //   188: iload_1
    //   189: aload_2
    //   190: invokestatic 186	org/android/spdy/SoInstallMgrSdk:unZipSelectedFiles	(Ljava/lang/String;ILjava/lang/ClassLoader;)Z
    //   193: istore_3
    //   194: goto -16 -> 178
    //   197: astore_0
    //   198: aload_0
    //   199: invokevirtual 187	java/util/zip/ZipException:printStackTrace	()V
    //   202: iload 4
    //   204: istore_3
    //   205: goto -27 -> 178
    //   208: astore_0
    //   209: aload_0
    //   210: invokevirtual 119	java/lang/Exception:printStackTrace	()V
    //   213: iconst_0
    //   214: istore_3
    //   215: goto -37 -> 178
    //   218: astore_0
    //   219: aload_0
    //   220: invokevirtual 188	java/io/IOException:printStackTrace	()V
    //   223: iload 4
    //   225: istore_3
    //   226: goto -48 -> 178
    //   229: astore_0
    //   230: aload_0
    //   231: invokevirtual 120	java/lang/UnsatisfiedLinkError:printStackTrace	()V
    //   234: iconst_0
    //   235: istore_3
    //   236: goto -58 -> 178
    //   239: astore_0
    //   240: aload_0
    //   241: invokevirtual 121	java/lang/Error:printStackTrace	()V
    //   244: iconst_0
    //   245: istore_3
    //   246: goto -68 -> 178
    //
    // Exception table:
    //   from	to	target	type
    //   7	11	101	java/lang/Exception
    //   44	98	101	java/lang/Exception
    //   7	11	114	java/lang/UnsatisfiedLinkError
    //   44	98	114	java/lang/UnsatisfiedLinkError
    //   7	11	127	java/lang/Error
    //   44	98	127	java/lang/Error
    //   187	194	197	java/util/zip/ZipException
    //   19	34	208	java/lang/Exception
    //   140	145	208	java/lang/Exception
    //   145	150	208	java/lang/Exception
    //   153	171	208	java/lang/Exception
    //   187	194	208	java/lang/Exception
    //   198	202	208	java/lang/Exception
    //   219	223	208	java/lang/Exception
    //   187	194	218	java/io/IOException
    //   19	34	229	java/lang/UnsatisfiedLinkError
    //   140	145	229	java/lang/UnsatisfiedLinkError
    //   145	150	229	java/lang/UnsatisfiedLinkError
    //   153	171	229	java/lang/UnsatisfiedLinkError
    //   187	194	229	java/lang/UnsatisfiedLinkError
    //   198	202	229	java/lang/UnsatisfiedLinkError
    //   219	223	229	java/lang/UnsatisfiedLinkError
    //   19	34	239	java/lang/Error
    //   140	145	239	java/lang/Error
    //   145	150	239	java/lang/Error
    //   153	171	239	java/lang/Error
    //   187	194	239	java/lang/Error
    //   198	202	239	java/lang/Error
    //   219	223	239	java/lang/Error
  }

  static boolean isExist(String paramString, int paramInt)
  {
    return new File(_targetSoFile(paramString, paramInt)).exists();
  }

  static void removeSoIfExit(String paramString, int paramInt)
  {
    paramString = new File(_targetSoFile(paramString, paramInt));
    if (paramString.exists())
      paramString.delete();
  }

  // ERROR //
  static boolean unZipSelectedFiles(String paramString, int paramInt, ClassLoader paramClassLoader)
    throws java.util.zip.ZipException, java.io.IOException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 9
    //   3: aconst_null
    //   4: astore 8
    //   6: new 125	java/lang/StringBuilder
    //   9: dup
    //   10: invokespecial 126	java/lang/StringBuilder:<init>	()V
    //   13: ldc 199
    //   15: invokevirtual 132	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   18: aload_0
    //   19: invokevirtual 132	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: ldc 158
    //   24: invokevirtual 132	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: invokevirtual 140	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   30: astore 7
    //   32: ldc 123
    //   34: astore 6
    //   36: getstatic 26	org/android/spdy/SoInstallMgrSdk:mContext	Landroid/content/Context;
    //   39: astore 11
    //   41: aload 11
    //   43: ifnonnull +5 -> 48
    //   46: iconst_0
    //   47: ireturn
    //   48: aload 11
    //   50: invokevirtual 203	android/content/Context:getApplicationInfo	()Landroid/content/pm/ApplicationInfo;
    //   53: astore 10
    //   55: aload 10
    //   57: ifnull +10 -> 67
    //   60: aload 10
    //   62: getfield 208	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
    //   65: astore 6
    //   67: new 210	java/util/zip/ZipFile
    //   70: dup
    //   71: aload 6
    //   73: invokespecial 211	java/util/zip/ZipFile:<init>	(Ljava/lang/String;)V
    //   76: astore 10
    //   78: aload 10
    //   80: invokevirtual 215	java/util/zip/ZipFile:entries	()Ljava/util/Enumeration;
    //   83: astore 6
    //   85: aload 6
    //   87: invokeinterface 220 1 0
    //   92: ifeq +225 -> 317
    //   95: aload 6
    //   97: invokeinterface 224 1 0
    //   102: checkcast 226	java/util/zip/ZipEntry
    //   105: astore 12
    //   107: aload 12
    //   109: invokevirtual 229	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   112: aload 7
    //   114: invokevirtual 232	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   117: istore 5
    //   119: iload 5
    //   121: ifeq -36 -> 85
    //   124: aload_0
    //   125: iload_1
    //   126: invokestatic 177	org/android/spdy/SoInstallMgrSdk:removeSoIfExit	(Ljava/lang/String;I)V
    //   129: aload 10
    //   131: aload 12
    //   133: invokevirtual 236	java/util/zip/ZipFile:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   136: astore 7
    //   138: aload 11
    //   140: new 125	java/lang/StringBuilder
    //   143: dup
    //   144: invokespecial 126	java/lang/StringBuilder:<init>	()V
    //   147: ldc 238
    //   149: invokevirtual 132	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   152: aload_0
    //   153: invokevirtual 132	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: ldc 153
    //   158: invokevirtual 132	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   161: iload_1
    //   162: invokevirtual 156	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   165: ldc 158
    //   167: invokevirtual 132	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   170: invokevirtual 140	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   173: iconst_0
    //   174: invokevirtual 242	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   177: astore 6
    //   179: aload 6
    //   181: astore 8
    //   183: aload 9
    //   185: astore 6
    //   187: aload 8
    //   189: invokevirtual 248	java/io/FileOutputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   192: astore 9
    //   194: aload 9
    //   196: astore 6
    //   198: sipush 1024
    //   201: newarray byte
    //   203: astore 11
    //   205: iconst_0
    //   206: istore_3
    //   207: aload 9
    //   209: astore 6
    //   211: aload 7
    //   213: aload 11
    //   215: invokevirtual 254	java/io/InputStream:read	([B)I
    //   218: istore 4
    //   220: iload 4
    //   222: ifle +29 -> 251
    //   225: aload 9
    //   227: astore 6
    //   229: aload 9
    //   231: aload 11
    //   233: iconst_0
    //   234: iload 4
    //   236: invokestatic 260	java/nio/ByteBuffer:wrap	([BII)Ljava/nio/ByteBuffer;
    //   239: invokevirtual 266	java/nio/channels/FileChannel:write	(Ljava/nio/ByteBuffer;)I
    //   242: pop
    //   243: iload_3
    //   244: iload 4
    //   246: iadd
    //   247: istore_3
    //   248: goto -41 -> 207
    //   251: aload 7
    //   253: ifnull +8 -> 261
    //   256: aload 7
    //   258: invokevirtual 269	java/io/InputStream:close	()V
    //   261: aload 9
    //   263: ifnull +8 -> 271
    //   266: aload 9
    //   268: invokevirtual 270	java/nio/channels/FileChannel:close	()V
    //   271: aload 8
    //   273: ifnull +8 -> 281
    //   276: aload 8
    //   278: invokevirtual 271	java/io/FileOutputStream:close	()V
    //   281: aload 10
    //   283: ifnull +8 -> 291
    //   286: aload 10
    //   288: invokevirtual 272	java/util/zip/ZipFile:close	()V
    //   291: iload_3
    //   292: ifle +125 -> 417
    //   295: aload_0
    //   296: iload_1
    //   297: aload_2
    //   298: invokestatic 172	org/android/spdy/SoInstallMgrSdk:_loadUnzipSo	(Ljava/lang/String;ILjava/lang/ClassLoader;)Z
    //   301: ireturn
    //   302: astore 6
    //   304: aload 6
    //   306: invokevirtual 119	java/lang/Exception:printStackTrace	()V
    //   309: goto -48 -> 261
    //   312: astore_0
    //   313: aload_0
    //   314: invokevirtual 188	java/io/IOException:printStackTrace	()V
    //   317: iconst_0
    //   318: ireturn
    //   319: astore 6
    //   321: aload 6
    //   323: invokevirtual 119	java/lang/Exception:printStackTrace	()V
    //   326: goto -55 -> 271
    //   329: astore 6
    //   331: aload 6
    //   333: invokevirtual 119	java/lang/Exception:printStackTrace	()V
    //   336: goto -55 -> 281
    //   339: astore_0
    //   340: aconst_null
    //   341: astore_2
    //   342: aconst_null
    //   343: astore 7
    //   345: aload 8
    //   347: astore 6
    //   349: aload 7
    //   351: ifnull +8 -> 359
    //   354: aload 7
    //   356: invokevirtual 269	java/io/InputStream:close	()V
    //   359: aload 6
    //   361: ifnull +8 -> 369
    //   364: aload 6
    //   366: invokevirtual 270	java/nio/channels/FileChannel:close	()V
    //   369: aload_2
    //   370: ifnull +7 -> 377
    //   373: aload_2
    //   374: invokevirtual 271	java/io/FileOutputStream:close	()V
    //   377: aload 10
    //   379: ifnull +8 -> 387
    //   382: aload 10
    //   384: invokevirtual 272	java/util/zip/ZipFile:close	()V
    //   387: aload_0
    //   388: athrow
    //   389: astore 7
    //   391: aload 7
    //   393: invokevirtual 119	java/lang/Exception:printStackTrace	()V
    //   396: goto -37 -> 359
    //   399: astore 6
    //   401: aload 6
    //   403: invokevirtual 119	java/lang/Exception:printStackTrace	()V
    //   406: goto -37 -> 369
    //   409: astore_2
    //   410: aload_2
    //   411: invokevirtual 119	java/lang/Exception:printStackTrace	()V
    //   414: goto -37 -> 377
    //   417: iconst_0
    //   418: ireturn
    //   419: astore_0
    //   420: aconst_null
    //   421: astore_2
    //   422: aload 8
    //   424: astore 6
    //   426: goto -77 -> 349
    //   429: astore_0
    //   430: aload 8
    //   432: astore_2
    //   433: goto -84 -> 349
    //
    // Exception table:
    //   from	to	target	type
    //   256	261	302	java/lang/Exception
    //   36	41	312	java/io/IOException
    //   48	55	312	java/io/IOException
    //   60	67	312	java/io/IOException
    //   67	85	312	java/io/IOException
    //   85	119	312	java/io/IOException
    //   256	261	312	java/io/IOException
    //   266	271	312	java/io/IOException
    //   276	281	312	java/io/IOException
    //   286	291	312	java/io/IOException
    //   295	302	312	java/io/IOException
    //   304	309	312	java/io/IOException
    //   321	326	312	java/io/IOException
    //   331	336	312	java/io/IOException
    //   354	359	312	java/io/IOException
    //   364	369	312	java/io/IOException
    //   373	377	312	java/io/IOException
    //   382	387	312	java/io/IOException
    //   387	389	312	java/io/IOException
    //   391	396	312	java/io/IOException
    //   401	406	312	java/io/IOException
    //   410	414	312	java/io/IOException
    //   266	271	319	java/lang/Exception
    //   276	281	329	java/lang/Exception
    //   124	138	339	finally
    //   354	359	389	java/lang/Exception
    //   364	369	399	java/lang/Exception
    //   373	377	409	java/lang/Exception
    //   138	179	419	finally
    //   187	194	429	finally
    //   198	205	429	finally
    //   211	220	429	finally
    //   229	243	429	finally
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.android.spdy.SoInstallMgrSdk
 * JD-Core Version:    0.6.2
 */