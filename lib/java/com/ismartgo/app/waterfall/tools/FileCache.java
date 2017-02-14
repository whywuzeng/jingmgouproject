package com.ismartgo.app.waterfall.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileCache
{
  private static FileCache fileCache;
  private String strImgPath;
  private String strJsonPath;

  private FileCache()
  {
    if ("mounted".equals(Environment.getExternalStorageState()));
    for (String str = Environment.getExternalStorageDirectory().toString(); ; str = "/data/data/com.dodola")
    {
      this.strImgPath = (str + "/dodola/cache/IMG/");
      this.strJsonPath = (str + "/dodola/cache/JSON/");
      return;
    }
  }

  public static FileCache getInstance()
  {
    if (fileCache == null)
      fileCache = new FileCache();
    return fileCache;
  }

  private String toHexString(String paramString)
  {
    String str1 = "";
    int i = 0;
    while (true)
    {
      if (i >= paramString.length())
        return "0x" + str1;
      String str2 = Integer.toHexString(paramString.charAt(i));
      str1 = str1 + str2;
      i += 1;
    }
  }

  private String toStringHex(String paramString)
  {
    String str = paramString;
    if ("0x".equals(paramString.substring(0, 2)))
      str = paramString.substring(2);
    paramString = new byte[str.length() / 2];
    int i = 0;
    while (true)
    {
      if (i >= paramString.length);
      try
      {
        paramString = new String(paramString, "utf-8");
        return paramString;
        try
        {
          paramString[i] = ((byte)(Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16) & 0xFF));
          i += 1;
        }
        catch (Exception localException)
        {
          while (true)
            localException.printStackTrace();
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    }
    return str;
  }

  // ERROR //
  private boolean writeToFile(Bitmap paramBitmap, File paramFile)
  {
    // Byte code:
    //   0: aload_2
    //   1: invokevirtual 116	java/io/File:exists	()Z
    //   4: ifeq +8 -> 12
    //   7: aload_2
    //   8: invokevirtual 119	java/io/File:delete	()Z
    //   11: pop
    //   12: aload_2
    //   13: invokevirtual 122	java/io/File:getName	()Ljava/lang/String;
    //   16: astore_3
    //   17: aload_3
    //   18: aload_3
    //   19: bipush 46
    //   21: invokevirtual 126	java/lang/String:lastIndexOf	(I)I
    //   24: aload_3
    //   25: invokevirtual 74	java/lang/String:length	()I
    //   28: invokevirtual 92	java/lang/String:substring	(II)Ljava/lang/String;
    //   31: astore 6
    //   33: aconst_null
    //   34: astore 5
    //   36: aconst_null
    //   37: astore_3
    //   38: aconst_null
    //   39: astore 4
    //   41: new 128	java/io/FileOutputStream
    //   44: dup
    //   45: aload_2
    //   46: invokespecial 131	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   49: astore_2
    //   50: new 133	java/io/BufferedOutputStream
    //   53: dup
    //   54: aload_2
    //   55: invokespecial 136	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   58: astore_3
    //   59: aload_1
    //   60: ifnull +121 -> 181
    //   63: ldc 138
    //   65: aload 6
    //   67: invokevirtual 142	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   70: ifne +13 -> 83
    //   73: ldc 144
    //   75: aload 6
    //   77: invokevirtual 142	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   80: ifeq +32 -> 112
    //   83: aload_1
    //   84: getstatic 150	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   87: bipush 100
    //   89: aload_3
    //   90: invokevirtual 156	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   93: pop
    //   94: aload_3
    //   95: invokevirtual 159	java/io/BufferedOutputStream:flush	()V
    //   98: aload_3
    //   99: invokevirtual 162	java/io/BufferedOutputStream:close	()V
    //   102: aload_2
    //   103: ifnull +7 -> 110
    //   106: aload_2
    //   107: invokevirtual 163	java/io/FileOutputStream:close	()V
    //   110: iconst_1
    //   111: ireturn
    //   112: ldc 165
    //   114: aload 6
    //   116: invokevirtual 142	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   119: ifeq -17 -> 102
    //   122: aload_1
    //   123: getstatic 168	android/graphics/Bitmap$CompressFormat:PNG	Landroid/graphics/Bitmap$CompressFormat;
    //   126: bipush 100
    //   128: aload_3
    //   129: invokevirtual 156	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   132: pop
    //   133: aload_3
    //   134: invokevirtual 159	java/io/BufferedOutputStream:flush	()V
    //   137: aload_3
    //   138: invokevirtual 162	java/io/BufferedOutputStream:close	()V
    //   141: goto -39 -> 102
    //   144: astore_3
    //   145: aload_2
    //   146: astore_1
    //   147: aload_3
    //   148: astore_2
    //   149: aload_1
    //   150: astore_3
    //   151: aload_2
    //   152: invokevirtual 169	java/io/FileNotFoundException:printStackTrace	()V
    //   155: aload_1
    //   156: ifnull +7 -> 163
    //   159: aload_1
    //   160: invokevirtual 163	java/io/FileOutputStream:close	()V
    //   163: iconst_0
    //   164: ireturn
    //   165: astore_1
    //   166: aload_1
    //   167: invokevirtual 170	java/io/IOException:printStackTrace	()V
    //   170: ldc 172
    //   172: ldc 174
    //   174: invokestatic 180	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   177: pop
    //   178: goto -68 -> 110
    //   181: aload_3
    //   182: invokevirtual 162	java/io/BufferedOutputStream:close	()V
    //   185: aload_2
    //   186: ifnull +103 -> 289
    //   189: aload_2
    //   190: invokevirtual 163	java/io/FileOutputStream:close	()V
    //   193: goto -30 -> 163
    //   196: astore_1
    //   197: aload_1
    //   198: invokevirtual 170	java/io/IOException:printStackTrace	()V
    //   201: ldc 172
    //   203: ldc 174
    //   205: invokestatic 180	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   208: pop
    //   209: goto -46 -> 163
    //   212: astore_2
    //   213: aload 5
    //   215: astore_1
    //   216: aload_1
    //   217: astore_3
    //   218: aload_2
    //   219: invokevirtual 170	java/io/IOException:printStackTrace	()V
    //   222: aload_1
    //   223: ifnull -60 -> 163
    //   226: aload_1
    //   227: invokevirtual 163	java/io/FileOutputStream:close	()V
    //   230: goto -67 -> 163
    //   233: astore_1
    //   234: aload_1
    //   235: invokevirtual 170	java/io/IOException:printStackTrace	()V
    //   238: ldc 172
    //   240: ldc 174
    //   242: invokestatic 180	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   245: pop
    //   246: goto -83 -> 163
    //   249: astore_1
    //   250: aload_3
    //   251: ifnull +7 -> 258
    //   254: aload_3
    //   255: invokevirtual 163	java/io/FileOutputStream:close	()V
    //   258: aload_1
    //   259: athrow
    //   260: astore_2
    //   261: aload_2
    //   262: invokevirtual 170	java/io/IOException:printStackTrace	()V
    //   265: ldc 172
    //   267: ldc 174
    //   269: invokestatic 180	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   272: pop
    //   273: goto -15 -> 258
    //   276: astore_1
    //   277: aload_1
    //   278: invokevirtual 170	java/io/IOException:printStackTrace	()V
    //   281: ldc 172
    //   283: ldc 174
    //   285: invokestatic 180	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   288: pop
    //   289: goto -126 -> 163
    //   292: astore_1
    //   293: aload_2
    //   294: astore_3
    //   295: goto -45 -> 250
    //   298: astore_3
    //   299: aload_2
    //   300: astore_1
    //   301: aload_3
    //   302: astore_2
    //   303: goto -87 -> 216
    //   306: astore_2
    //   307: aload 4
    //   309: astore_1
    //   310: goto -161 -> 149
    //
    // Exception table:
    //   from	to	target	type
    //   50	59	144	java/io/FileNotFoundException
    //   63	83	144	java/io/FileNotFoundException
    //   83	102	144	java/io/FileNotFoundException
    //   112	141	144	java/io/FileNotFoundException
    //   181	185	144	java/io/FileNotFoundException
    //   106	110	165	java/io/IOException
    //   159	163	196	java/io/IOException
    //   41	50	212	java/io/IOException
    //   226	230	233	java/io/IOException
    //   41	50	249	finally
    //   151	155	249	finally
    //   218	222	249	finally
    //   254	258	260	java/io/IOException
    //   189	193	276	java/io/IOException
    //   50	59	292	finally
    //   63	83	292	finally
    //   83	102	292	finally
    //   112	141	292	finally
    //   181	185	292	finally
    //   50	59	298	java/io/IOException
    //   63	83	298	java/io/IOException
    //   83	102	298	java/io/IOException
    //   112	141	298	java/io/IOException
    //   181	185	298	java/io/IOException
    //   41	50	306	java/io/FileNotFoundException
  }

  // ERROR //
  private boolean writeToFile(String paramString, File paramFile)
  {
    // Byte code:
    //   0: aload_2
    //   1: invokevirtual 116	java/io/File:exists	()Z
    //   4: ifeq +8 -> 12
    //   7: aload_2
    //   8: invokevirtual 119	java/io/File:delete	()Z
    //   11: pop
    //   12: aconst_null
    //   13: astore 5
    //   15: aconst_null
    //   16: astore_3
    //   17: aconst_null
    //   18: astore 4
    //   20: new 128	java/io/FileOutputStream
    //   23: dup
    //   24: aload_2
    //   25: invokespecial 131	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   28: astore_2
    //   29: new 133	java/io/BufferedOutputStream
    //   32: dup
    //   33: aload_2
    //   34: invokespecial 136	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   37: astore_3
    //   38: aload_3
    //   39: aload_1
    //   40: invokevirtual 185	java/lang/String:getBytes	()[B
    //   43: invokevirtual 189	java/io/BufferedOutputStream:write	([B)V
    //   46: aload_3
    //   47: invokevirtual 159	java/io/BufferedOutputStream:flush	()V
    //   50: aload_3
    //   51: invokevirtual 162	java/io/BufferedOutputStream:close	()V
    //   54: aload_2
    //   55: ifnull +7 -> 62
    //   58: aload_2
    //   59: invokevirtual 163	java/io/FileOutputStream:close	()V
    //   62: iconst_1
    //   63: ireturn
    //   64: astore_1
    //   65: aload_1
    //   66: invokevirtual 170	java/io/IOException:printStackTrace	()V
    //   69: goto -7 -> 62
    //   72: astore_2
    //   73: aload 4
    //   75: astore_1
    //   76: aload_1
    //   77: astore_3
    //   78: aload_2
    //   79: invokevirtual 169	java/io/FileNotFoundException:printStackTrace	()V
    //   82: aload_1
    //   83: ifnull +7 -> 90
    //   86: aload_1
    //   87: invokevirtual 163	java/io/FileOutputStream:close	()V
    //   90: iconst_0
    //   91: ireturn
    //   92: astore_1
    //   93: aload_1
    //   94: invokevirtual 170	java/io/IOException:printStackTrace	()V
    //   97: goto -7 -> 90
    //   100: astore_2
    //   101: aload 5
    //   103: astore_1
    //   104: aload_1
    //   105: astore_3
    //   106: aload_2
    //   107: invokevirtual 170	java/io/IOException:printStackTrace	()V
    //   110: aload_1
    //   111: ifnull -21 -> 90
    //   114: aload_1
    //   115: invokevirtual 163	java/io/FileOutputStream:close	()V
    //   118: goto -28 -> 90
    //   121: astore_1
    //   122: aload_1
    //   123: invokevirtual 170	java/io/IOException:printStackTrace	()V
    //   126: goto -36 -> 90
    //   129: astore_1
    //   130: aload_3
    //   131: ifnull +7 -> 138
    //   134: aload_3
    //   135: invokevirtual 163	java/io/FileOutputStream:close	()V
    //   138: aload_1
    //   139: athrow
    //   140: astore_2
    //   141: aload_2
    //   142: invokevirtual 170	java/io/IOException:printStackTrace	()V
    //   145: goto -7 -> 138
    //   148: astore_1
    //   149: aload_2
    //   150: astore_3
    //   151: goto -21 -> 130
    //   154: astore_3
    //   155: aload_2
    //   156: astore_1
    //   157: aload_3
    //   158: astore_2
    //   159: goto -55 -> 104
    //   162: astore_3
    //   163: aload_2
    //   164: astore_1
    //   165: aload_3
    //   166: astore_2
    //   167: goto -91 -> 76
    //
    // Exception table:
    //   from	to	target	type
    //   58	62	64	java/io/IOException
    //   20	29	72	java/io/FileNotFoundException
    //   86	90	92	java/io/IOException
    //   20	29	100	java/io/IOException
    //   114	118	121	java/io/IOException
    //   20	29	129	finally
    //   78	82	129	finally
    //   106	110	129	finally
    //   134	138	140	java/io/IOException
    //   29	54	148	finally
    //   29	54	154	java/io/IOException
    //   29	54	162	java/io/FileNotFoundException
  }

  public int clearAllData()
  {
    Object localObject2 = new File(this.strImgPath);
    Object localObject1 = new File(this.strJsonPath);
    localObject2 = ((File)localObject2).listFiles();
    localObject1 = ((File)localObject1).listFiles();
    int n = localObject2.length;
    int i1 = localObject1.length;
    int j = 0;
    int m = 0;
    int k = 0;
    if (k >= n)
    {
      k = 0;
      if (k >= i1)
      {
        if ((j != n) || (m != i1))
          break label174;
        return 1;
      }
    }
    else
    {
      if (localObject2[k].exists())
      {
        i = j;
        if (!localObject2[k].delete());
      }
      for (i = j + 1; ; i = j + 1)
      {
        k += 1;
        j = i;
        break;
      }
    }
    if (localObject1[k].exists())
    {
      i = m;
      if (!localObject1[k].delete());
    }
    for (int i = m + 1; ; i = m + 1)
    {
      k += 1;
      m = i;
      break;
    }
    label174: return 0;
  }

  public boolean clearImgByImgUrl(String paramString)
  {
    paramString = getImgFile(paramString);
    if (paramString.exists())
    {
      paramString.delete();
      return true;
    }
    return false;
  }

  public Bitmap getBmp(String paramString)
  {
    paramString = paramString.substring(paramString.lastIndexOf('/') + 2, paramString.length());
    paramString = new File(this.strImgPath + paramString);
    if (paramString.exists())
      try
      {
        paramString = BitmapFactory.decodeStream(new FileInputStream(paramString));
        return paramString;
      }
      catch (FileNotFoundException paramString)
      {
        paramString.printStackTrace();
      }
    while (true)
    {
      return null;
      Log.v("提醒", "要请求的图片文件不存在");
    }
  }

  public Bitmap getBmpByName(String paramString)
  {
    paramString = new File(this.strImgPath + paramString);
    if (paramString.exists())
      try
      {
        paramString = BitmapFactory.decodeStream(new FileInputStream(paramString));
        return paramString;
      }
      catch (FileNotFoundException paramString)
      {
        paramString.printStackTrace();
      }
    while (true)
    {
      return null;
      Log.v("提醒", "要请求的图片文件不存在");
    }
  }

  public File getImgFile(String paramString)
  {
    paramString = paramString.substring(paramString.lastIndexOf('/') + 2, paramString.length());
    return new File(this.strImgPath + paramString);
  }

  // ERROR //
  public String getJson(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokespecial 219	com/ismartgo/app/waterfall/tools/FileCache:toHexString	(Ljava/lang/String;)Ljava/lang/String;
    //   5: astore_1
    //   6: new 33	java/io/File
    //   9: dup
    //   10: new 38	java/lang/StringBuilder
    //   13: dup
    //   14: aload_0
    //   15: getfield 58	com/ismartgo/app/waterfall/tools/FileCache:strJsonPath	Ljava/lang/String;
    //   18: invokestatic 42	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   21: invokespecial 45	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   24: aload_1
    //   25: invokevirtual 51	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   28: ldc 221
    //   30: invokevirtual 51	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: invokevirtual 52	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   36: invokespecial 191	java/io/File:<init>	(Ljava/lang/String;)V
    //   39: astore_2
    //   40: new 223	java/lang/StringBuffer
    //   43: dup
    //   44: invokespecial 224	java/lang/StringBuffer:<init>	()V
    //   47: astore_1
    //   48: aload_2
    //   49: invokevirtual 116	java/io/File:exists	()Z
    //   52: ifeq +49 -> 101
    //   55: new 226	java/io/FileReader
    //   58: dup
    //   59: aload_2
    //   60: invokespecial 227	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   63: astore_2
    //   64: new 229	java/io/BufferedReader
    //   67: dup
    //   68: aload_2
    //   69: invokespecial 232	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   72: astore_2
    //   73: aload_2
    //   74: invokevirtual 235	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   77: astore_3
    //   78: aload_3
    //   79: ifnonnull +8 -> 87
    //   82: aload_1
    //   83: invokevirtual 236	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   86: areturn
    //   87: aload_1
    //   88: aload_3
    //   89: invokevirtual 239	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   92: pop
    //   93: goto -20 -> 73
    //   96: astore_1
    //   97: aload_1
    //   98: invokevirtual 169	java/io/FileNotFoundException:printStackTrace	()V
    //   101: aconst_null
    //   102: areturn
    //   103: astore_1
    //   104: aload_1
    //   105: invokevirtual 170	java/io/IOException:printStackTrace	()V
    //   108: goto -7 -> 101
    //   111: astore_1
    //   112: goto -8 -> 104
    //   115: astore_1
    //   116: goto -19 -> 97
    //
    // Exception table:
    //   from	to	target	type
    //   64	73	96	java/io/FileNotFoundException
    //   73	78	96	java/io/FileNotFoundException
    //   82	87	96	java/io/FileNotFoundException
    //   87	93	96	java/io/FileNotFoundException
    //   55	64	103	java/io/IOException
    //   64	73	111	java/io/IOException
    //   73	78	111	java/io/IOException
    //   82	87	111	java/io/IOException
    //   87	93	111	java/io/IOException
    //   55	64	115	java/io/FileNotFoundException
  }

  public boolean savaBmpData(String paramString, Bitmap paramBitmap)
  {
    paramString = paramString.substring(paramString.lastIndexOf('/') + 2, paramString.length());
    File localFile = new File(this.strImgPath);
    if (!localFile.exists())
      localFile.mkdirs();
    paramString = new File(this.strImgPath + paramString);
    if (paramString.exists())
      paramString.delete();
    writeToFile(paramBitmap, paramString);
    return true;
  }

  public boolean savaJsonData(String paramString1, String paramString2)
  {
    paramString1 = toHexString(paramString1);
    File localFile = new File(this.strJsonPath);
    if (!localFile.exists())
      localFile.mkdirs();
    paramString1 = new File(this.strJsonPath + paramString1 + ".txt");
    if (paramString1.exists())
      paramString1.delete();
    writeToFile(paramString2, paramString1);
    return true;
  }

  public boolean saveBmpDataByName(String paramString, Bitmap paramBitmap)
  {
    File localFile = new File(this.strImgPath);
    if (!localFile.exists())
      localFile.mkdirs();
    paramString = new File(this.strImgPath + paramString);
    if (paramString.exists())
      paramString.delete();
    writeToFile(paramBitmap, paramString);
    return true;
  }

  public boolean saveData(String paramString1, String paramString2, String paramString3, Bitmap paramBitmap)
  {
    paramString1 = toHexString(paramString1);
    paramString3 = paramString3.substring(paramString3.lastIndexOf('/') + 2, paramString3.length());
    File localFile1 = new File(this.strJsonPath);
    File localFile2 = new File(this.strImgPath);
    if (!localFile1.exists())
      localFile1.mkdirs();
    if (!localFile2.exists())
      localFile2.mkdirs();
    paramString1 = new File(this.strJsonPath + paramString1 + ".txt");
    paramString3 = new File(this.strImgPath + paramString3);
    writeToFile(paramString2, paramString1);
    writeToFile(paramBitmap, paramString3);
    return true;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.waterfall.tools.FileCache
 * JD-Core Version:    0.6.2
 */