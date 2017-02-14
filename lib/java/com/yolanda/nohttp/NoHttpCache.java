package com.yolanda.nohttp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Process;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class NoHttpCache
{
  private static final int MAX_COUNT = 2147483647;
  private static final int MAX_SIZE = 50000000;
  public static final int TIME_DAY = 86400;
  public static final int TIME_HOUR = 3600;
  private static Map<String, NoHttpCache> mInstanceMap = new HashMap();
  private NoHttpCacheManager mCache;
  private Context mContext;

  private NoHttpCache(Context paramContext, File paramFile, long paramLong, int paramInt)
  {
    this.mContext = paramContext;
    if (!paramFile.exists())
      paramFile.mkdirs();
    while (true)
    {
      this.mCache = new NoHttpCacheManager(paramFile, paramLong, paramInt, null);
      return;
      if (paramFile.isFile())
      {
        paramFile.delete();
        paramFile.mkdirs();
      }
    }
  }

  public static NoHttpCache get(Context paramContext)
  {
    return get(paramContext, "NoHttpCache");
  }

  public static NoHttpCache get(Context paramContext, File paramFile, long paramLong, int paramInt)
  {
    NoHttpCache localNoHttpCache2 = (NoHttpCache)mInstanceMap.get(paramFile.getAbsoluteFile() + myPid());
    NoHttpCache localNoHttpCache1 = localNoHttpCache2;
    if (localNoHttpCache2 == null)
    {
      localNoHttpCache1 = new NoHttpCache(paramContext, paramFile, paramLong, paramInt);
      mInstanceMap.put(paramFile.getAbsolutePath() + myPid(), localNoHttpCache1);
    }
    return localNoHttpCache1;
  }

  public static NoHttpCache get(Context paramContext, String paramString)
  {
    return get(paramContext, paramString, 50000000L, 2147483647);
  }

  public static NoHttpCache get(Context paramContext, String paramString, long paramLong, int paramInt)
  {
    return get(paramContext, new File(paramContext.getCacheDir(), paramString), paramLong, paramInt);
  }

  private static String myPid()
  {
    return "_" + Process.myPid();
  }

  public void clear()
  {
    this.mCache.clear();
  }

  public File file(String paramString)
  {
    paramString = this.mCache.newFile(paramString);
    if (paramString.exists())
      return paramString;
    return null;
  }

  // ERROR //
  public byte[] getBinary(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 6
    //   6: aload 4
    //   8: astore_3
    //   9: aload_0
    //   10: getfield 56	com/yolanda/nohttp/NoHttpCache:mCache	Lcom/yolanda/nohttp/NoHttpCache$NoHttpCacheManager;
    //   13: aload_1
    //   14: invokestatic 159	com/yolanda/nohttp/NoHttpCache$NoHttpCacheManager:access$7	(Lcom/yolanda/nohttp/NoHttpCache$NoHttpCacheManager;Ljava/lang/String;)Ljava/io/File;
    //   17: astore 5
    //   19: aload 4
    //   21: astore_3
    //   22: aload 5
    //   24: invokevirtual 48	java/io/File:exists	()Z
    //   27: istore_2
    //   28: iload_2
    //   29: ifne +35 -> 64
    //   32: iconst_0
    //   33: ifeq +11 -> 44
    //   36: new 161	java/lang/NullPointerException
    //   39: dup
    //   40: invokespecial 162	java/lang/NullPointerException:<init>	()V
    //   43: athrow
    //   44: iconst_0
    //   45: ifeq +9 -> 54
    //   48: aload_0
    //   49: aload_1
    //   50: invokevirtual 166	com/yolanda/nohttp/NoHttpCache:remove	(Ljava/lang/String;)Z
    //   53: pop
    //   54: aconst_null
    //   55: areturn
    //   56: astore_3
    //   57: aload_3
    //   58: invokevirtual 169	java/io/IOException:printStackTrace	()V
    //   61: goto -17 -> 44
    //   64: aload 4
    //   66: astore_3
    //   67: new 171	java/io/RandomAccessFile
    //   70: dup
    //   71: aload 5
    //   73: ldc 173
    //   75: invokespecial 174	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   78: astore 4
    //   80: aload 4
    //   82: invokevirtual 178	java/io/RandomAccessFile:length	()J
    //   85: l2i
    //   86: newarray byte
    //   88: astore_3
    //   89: aload 4
    //   91: aload_3
    //   92: invokevirtual 182	java/io/RandomAccessFile:read	([B)I
    //   95: pop
    //   96: aload_3
    //   97: invokestatic 186	com/yolanda/nohttp/NoHttpCache$Utils:access$4	([B)Z
    //   100: ifne +40 -> 140
    //   103: aload_3
    //   104: invokestatic 189	com/yolanda/nohttp/NoHttpCache$Utils:access$5	([B)[B
    //   107: astore_3
    //   108: aload 4
    //   110: ifnull +8 -> 118
    //   113: aload 4
    //   115: invokevirtual 192	java/io/RandomAccessFile:close	()V
    //   118: iconst_0
    //   119: ifeq +9 -> 128
    //   122: aload_0
    //   123: aload_1
    //   124: invokevirtual 166	com/yolanda/nohttp/NoHttpCache:remove	(Ljava/lang/String;)Z
    //   127: pop
    //   128: aload_3
    //   129: areturn
    //   130: astore 4
    //   132: aload 4
    //   134: invokevirtual 169	java/io/IOException:printStackTrace	()V
    //   137: goto -19 -> 118
    //   140: aload 4
    //   142: ifnull +8 -> 150
    //   145: aload 4
    //   147: invokevirtual 192	java/io/RandomAccessFile:close	()V
    //   150: iconst_1
    //   151: ifeq +9 -> 160
    //   154: aload_0
    //   155: aload_1
    //   156: invokevirtual 166	com/yolanda/nohttp/NoHttpCache:remove	(Ljava/lang/String;)Z
    //   159: pop
    //   160: aconst_null
    //   161: areturn
    //   162: astore_3
    //   163: aload_3
    //   164: invokevirtual 169	java/io/IOException:printStackTrace	()V
    //   167: goto -17 -> 150
    //   170: astore 5
    //   172: aload 6
    //   174: astore 4
    //   176: aload 4
    //   178: astore_3
    //   179: aload 5
    //   181: invokevirtual 193	java/lang/Exception:printStackTrace	()V
    //   184: aload 4
    //   186: ifnull +8 -> 194
    //   189: aload 4
    //   191: invokevirtual 192	java/io/RandomAccessFile:close	()V
    //   194: iconst_0
    //   195: ifeq -141 -> 54
    //   198: aload_0
    //   199: aload_1
    //   200: invokevirtual 166	com/yolanda/nohttp/NoHttpCache:remove	(Ljava/lang/String;)Z
    //   203: pop
    //   204: aconst_null
    //   205: areturn
    //   206: astore_3
    //   207: aload_3
    //   208: invokevirtual 169	java/io/IOException:printStackTrace	()V
    //   211: goto -17 -> 194
    //   214: astore 4
    //   216: aload_3
    //   217: ifnull +7 -> 224
    //   220: aload_3
    //   221: invokevirtual 192	java/io/RandomAccessFile:close	()V
    //   224: iconst_0
    //   225: ifeq +9 -> 234
    //   228: aload_0
    //   229: aload_1
    //   230: invokevirtual 166	com/yolanda/nohttp/NoHttpCache:remove	(Ljava/lang/String;)Z
    //   233: pop
    //   234: aload 4
    //   236: athrow
    //   237: astore_3
    //   238: aload_3
    //   239: invokevirtual 169	java/io/IOException:printStackTrace	()V
    //   242: goto -18 -> 224
    //   245: astore 5
    //   247: aload 4
    //   249: astore_3
    //   250: aload 5
    //   252: astore 4
    //   254: goto -38 -> 216
    //   257: astore 5
    //   259: goto -83 -> 176
    //
    // Exception table:
    //   from	to	target	type
    //   36	44	56	java/io/IOException
    //   113	118	130	java/io/IOException
    //   145	150	162	java/io/IOException
    //   9	19	170	java/lang/Exception
    //   22	28	170	java/lang/Exception
    //   67	80	170	java/lang/Exception
    //   189	194	206	java/io/IOException
    //   9	19	214	finally
    //   22	28	214	finally
    //   67	80	214	finally
    //   179	184	214	finally
    //   220	224	237	java/io/IOException
    //   80	108	245	finally
    //   80	108	257	java/lang/Exception
  }

  public Bitmap getBitmap(String paramString)
  {
    if (getBinary(paramString) == null)
      return null;
    return Utils.Bytes2Bimap(getBinary(paramString));
  }

  public Drawable getDrawable(String paramString)
  {
    if (getBinary(paramString) == null)
      return null;
    return Utils.bitmap2Drawable(this.mContext.getResources(), Utils.access$7(getBinary(paramString)));
  }

  // ERROR //
  public Object getObject(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aload_0
    //   4: aload_1
    //   5: invokevirtual 197	com/yolanda/nohttp/NoHttpCache:getBinary	(Ljava/lang/String;)[B
    //   8: astore 8
    //   10: aload 6
    //   12: astore_1
    //   13: aload 8
    //   15: ifnull +60 -> 75
    //   18: aconst_null
    //   19: astore_2
    //   20: aconst_null
    //   21: astore 7
    //   23: aconst_null
    //   24: astore_3
    //   25: aconst_null
    //   26: astore 5
    //   28: aconst_null
    //   29: astore 4
    //   31: new 213	java/io/ByteArrayInputStream
    //   34: dup
    //   35: aload 8
    //   37: invokespecial 216	java/io/ByteArrayInputStream:<init>	([B)V
    //   40: astore_1
    //   41: new 218	java/io/ObjectInputStream
    //   44: dup
    //   45: aload_1
    //   46: invokespecial 221	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   49: astore_2
    //   50: aload_2
    //   51: invokevirtual 225	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   54: astore_3
    //   55: aload_1
    //   56: ifnull +7 -> 63
    //   59: aload_1
    //   60: invokevirtual 226	java/io/ByteArrayInputStream:close	()V
    //   63: aload_3
    //   64: astore_1
    //   65: aload_2
    //   66: ifnull +9 -> 75
    //   69: aload_2
    //   70: invokevirtual 227	java/io/ObjectInputStream:close	()V
    //   73: aload_3
    //   74: astore_1
    //   75: aload_1
    //   76: areturn
    //   77: astore_1
    //   78: aload_1
    //   79: invokevirtual 169	java/io/IOException:printStackTrace	()V
    //   82: goto -19 -> 63
    //   85: astore_1
    //   86: aload_1
    //   87: invokevirtual 169	java/io/IOException:printStackTrace	()V
    //   90: aload_3
    //   91: areturn
    //   92: astore 5
    //   94: aload 7
    //   96: astore_1
    //   97: aload_1
    //   98: astore_2
    //   99: aload 4
    //   101: astore_3
    //   102: aload 5
    //   104: invokevirtual 193	java/lang/Exception:printStackTrace	()V
    //   107: aload_1
    //   108: ifnull +7 -> 115
    //   111: aload_1
    //   112: invokevirtual 226	java/io/ByteArrayInputStream:close	()V
    //   115: aload 6
    //   117: astore_1
    //   118: aload 4
    //   120: ifnull -45 -> 75
    //   123: aload 4
    //   125: invokevirtual 227	java/io/ObjectInputStream:close	()V
    //   128: aconst_null
    //   129: areturn
    //   130: astore_1
    //   131: aload_1
    //   132: invokevirtual 169	java/io/IOException:printStackTrace	()V
    //   135: aconst_null
    //   136: areturn
    //   137: astore_1
    //   138: aload_1
    //   139: invokevirtual 169	java/io/IOException:printStackTrace	()V
    //   142: goto -27 -> 115
    //   145: astore_1
    //   146: aload_2
    //   147: ifnull +7 -> 154
    //   150: aload_2
    //   151: invokevirtual 226	java/io/ByteArrayInputStream:close	()V
    //   154: aload_3
    //   155: ifnull +7 -> 162
    //   158: aload_3
    //   159: invokevirtual 227	java/io/ObjectInputStream:close	()V
    //   162: aload_1
    //   163: athrow
    //   164: astore_2
    //   165: aload_2
    //   166: invokevirtual 169	java/io/IOException:printStackTrace	()V
    //   169: goto -15 -> 154
    //   172: astore_2
    //   173: aload_2
    //   174: invokevirtual 169	java/io/IOException:printStackTrace	()V
    //   177: goto -15 -> 162
    //   180: astore 4
    //   182: aload_1
    //   183: astore_2
    //   184: aload 5
    //   186: astore_3
    //   187: aload 4
    //   189: astore_1
    //   190: goto -44 -> 146
    //   193: astore 4
    //   195: aload_2
    //   196: astore_3
    //   197: aload_1
    //   198: astore_2
    //   199: aload 4
    //   201: astore_1
    //   202: goto -56 -> 146
    //   205: astore 5
    //   207: goto -110 -> 97
    //   210: astore 5
    //   212: aload_2
    //   213: astore 4
    //   215: goto -118 -> 97
    //
    // Exception table:
    //   from	to	target	type
    //   59	63	77	java/io/IOException
    //   69	73	85	java/io/IOException
    //   31	41	92	java/lang/Exception
    //   123	128	130	java/io/IOException
    //   111	115	137	java/io/IOException
    //   31	41	145	finally
    //   102	107	145	finally
    //   150	154	164	java/io/IOException
    //   158	162	172	java/io/IOException
    //   41	50	180	finally
    //   50	55	193	finally
    //   41	50	205	java/lang/Exception
    //   50	55	210	java/lang/Exception
  }

  // ERROR //
  public String getString(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aload_0
    //   4: getfield 56	com/yolanda/nohttp/NoHttpCache:mCache	Lcom/yolanda/nohttp/NoHttpCache$NoHttpCacheManager;
    //   7: aload_1
    //   8: invokestatic 159	com/yolanda/nohttp/NoHttpCache$NoHttpCacheManager:access$7	(Lcom/yolanda/nohttp/NoHttpCache$NoHttpCacheManager;Ljava/lang/String;)Ljava/io/File;
    //   11: astore_3
    //   12: aload_3
    //   13: invokevirtual 48	java/io/File:exists	()Z
    //   16: ifne +8 -> 24
    //   19: aload 5
    //   21: astore_3
    //   22: aload_3
    //   23: areturn
    //   24: aconst_null
    //   25: astore_2
    //   26: aconst_null
    //   27: astore 4
    //   29: new 231	java/io/BufferedReader
    //   32: dup
    //   33: new 233	java/io/FileReader
    //   36: dup
    //   37: aload_3
    //   38: invokespecial 236	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   41: invokespecial 239	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   44: astore_3
    //   45: ldc 241
    //   47: astore_2
    //   48: aload_3
    //   49: invokevirtual 244	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   52: astore 4
    //   54: aload 4
    //   56: ifnonnull +37 -> 93
    //   59: aload_2
    //   60: invokestatic 247	com/yolanda/nohttp/NoHttpCache$Utils:access$1	(Ljava/lang/String;)Z
    //   63: ifne +61 -> 124
    //   66: aload_2
    //   67: invokestatic 250	com/yolanda/nohttp/NoHttpCache$Utils:access$2	(Ljava/lang/String;)Ljava/lang/String;
    //   70: astore_2
    //   71: aload_3
    //   72: ifnull +7 -> 79
    //   75: aload_3
    //   76: invokevirtual 251	java/io/BufferedReader:close	()V
    //   79: aload_2
    //   80: astore_3
    //   81: iconst_0
    //   82: ifeq -60 -> 22
    //   85: aload_0
    //   86: aload_1
    //   87: invokevirtual 166	com/yolanda/nohttp/NoHttpCache:remove	(Ljava/lang/String;)Z
    //   90: pop
    //   91: aload_2
    //   92: areturn
    //   93: new 72	java/lang/StringBuilder
    //   96: dup
    //   97: aload_2
    //   98: invokestatic 107	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   101: invokespecial 110	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   104: aload 4
    //   106: invokevirtual 88	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: invokevirtual 91	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   112: astore_2
    //   113: goto -65 -> 48
    //   116: astore_3
    //   117: aload_3
    //   118: invokevirtual 169	java/io/IOException:printStackTrace	()V
    //   121: goto -42 -> 79
    //   124: aload_3
    //   125: ifnull +7 -> 132
    //   128: aload_3
    //   129: invokevirtual 251	java/io/BufferedReader:close	()V
    //   132: aload 5
    //   134: astore_3
    //   135: iconst_1
    //   136: ifeq -114 -> 22
    //   139: aload_0
    //   140: aload_1
    //   141: invokevirtual 166	com/yolanda/nohttp/NoHttpCache:remove	(Ljava/lang/String;)Z
    //   144: pop
    //   145: aconst_null
    //   146: areturn
    //   147: astore_2
    //   148: aload_2
    //   149: invokevirtual 169	java/io/IOException:printStackTrace	()V
    //   152: goto -20 -> 132
    //   155: astore_2
    //   156: aload 4
    //   158: astore_3
    //   159: aload_2
    //   160: astore 4
    //   162: aload_3
    //   163: astore_2
    //   164: aload 4
    //   166: invokevirtual 169	java/io/IOException:printStackTrace	()V
    //   169: aload_3
    //   170: ifnull +7 -> 177
    //   173: aload_3
    //   174: invokevirtual 251	java/io/BufferedReader:close	()V
    //   177: aload 5
    //   179: astore_3
    //   180: iconst_0
    //   181: ifeq -159 -> 22
    //   184: aload_0
    //   185: aload_1
    //   186: invokevirtual 166	com/yolanda/nohttp/NoHttpCache:remove	(Ljava/lang/String;)Z
    //   189: pop
    //   190: aconst_null
    //   191: areturn
    //   192: astore_2
    //   193: aload_2
    //   194: invokevirtual 169	java/io/IOException:printStackTrace	()V
    //   197: goto -20 -> 177
    //   200: astore_3
    //   201: aload_2
    //   202: ifnull +7 -> 209
    //   205: aload_2
    //   206: invokevirtual 251	java/io/BufferedReader:close	()V
    //   209: iconst_0
    //   210: ifeq +9 -> 219
    //   213: aload_0
    //   214: aload_1
    //   215: invokevirtual 166	com/yolanda/nohttp/NoHttpCache:remove	(Ljava/lang/String;)Z
    //   218: pop
    //   219: aload_3
    //   220: athrow
    //   221: astore_2
    //   222: aload_2
    //   223: invokevirtual 169	java/io/IOException:printStackTrace	()V
    //   226: goto -17 -> 209
    //   229: astore 4
    //   231: aload_3
    //   232: astore_2
    //   233: aload 4
    //   235: astore_3
    //   236: goto -35 -> 201
    //   239: astore 4
    //   241: goto -79 -> 162
    //
    // Exception table:
    //   from	to	target	type
    //   75	79	116	java/io/IOException
    //   128	132	147	java/io/IOException
    //   29	45	155	java/io/IOException
    //   173	177	192	java/io/IOException
    //   29	45	200	finally
    //   164	169	200	finally
    //   205	209	221	java/io/IOException
    //   48	54	229	finally
    //   59	71	229	finally
    //   93	113	229	finally
    //   48	54	239	java/io/IOException
    //   59	71	239	java/io/IOException
    //   93	113	239	java/io/IOException
  }

  public void put(String paramString, Bitmap paramBitmap)
  {
    put(paramString, Utils.Bitmap2Bytes(paramBitmap));
  }

  public void put(String paramString, Bitmap paramBitmap, int paramInt)
  {
    put(paramString, Utils.Bitmap2Bytes(paramBitmap), paramInt);
  }

  public void put(String paramString, Drawable paramDrawable)
  {
    put(paramString, Utils.drawable2Bitmap(paramDrawable));
  }

  public void put(String paramString, Drawable paramDrawable, int paramInt)
  {
    put(paramString, Utils.drawable2Bitmap(paramDrawable), paramInt);
  }

  public void put(String paramString, Serializable paramSerializable)
  {
    put(paramString, paramSerializable, -1);
  }

  // ERROR //
  public void put(String paramString, Serializable paramSerializable, int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 6
    //   6: aconst_null
    //   7: astore 5
    //   9: new 279	java/io/ByteArrayOutputStream
    //   12: dup
    //   13: invokespecial 280	java/io/ByteArrayOutputStream:<init>	()V
    //   16: astore 7
    //   18: new 282	java/io/ObjectOutputStream
    //   21: dup
    //   22: aload 7
    //   24: invokespecial 285	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   27: astore 4
    //   29: aload 4
    //   31: aload_2
    //   32: invokevirtual 289	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   35: aload 7
    //   37: invokevirtual 293	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   40: astore_2
    //   41: iload_3
    //   42: iconst_m1
    //   43: if_icmpeq +16 -> 59
    //   46: aload_0
    //   47: aload_1
    //   48: aload_2
    //   49: iload_3
    //   50: invokevirtual 263	com/yolanda/nohttp/NoHttpCache:put	(Ljava/lang/String;[BI)V
    //   53: aload 4
    //   55: invokevirtual 294	java/io/ObjectOutputStream:close	()V
    //   58: return
    //   59: aload_0
    //   60: aload_1
    //   61: aload_2
    //   62: invokevirtual 259	com/yolanda/nohttp/NoHttpCache:put	(Ljava/lang/String;[B)V
    //   65: goto -12 -> 53
    //   68: astore_1
    //   69: aload 4
    //   71: astore_2
    //   72: aload_2
    //   73: astore 4
    //   75: aload_1
    //   76: invokevirtual 193	java/lang/Exception:printStackTrace	()V
    //   79: aload_2
    //   80: invokevirtual 294	java/io/ObjectOutputStream:close	()V
    //   83: return
    //   84: astore_1
    //   85: return
    //   86: astore_1
    //   87: aload 4
    //   89: invokevirtual 294	java/io/ObjectOutputStream:close	()V
    //   92: aload_1
    //   93: athrow
    //   94: astore_1
    //   95: return
    //   96: astore_2
    //   97: goto -5 -> 92
    //   100: astore_1
    //   101: aload 6
    //   103: astore 4
    //   105: goto -18 -> 87
    //   108: astore_1
    //   109: goto -22 -> 87
    //   112: astore_1
    //   113: aload 5
    //   115: astore_2
    //   116: goto -44 -> 72
    //   119: astore_1
    //   120: aload 5
    //   122: astore_2
    //   123: goto -51 -> 72
    //
    // Exception table:
    //   from	to	target	type
    //   29	41	68	java/lang/Exception
    //   46	53	68	java/lang/Exception
    //   59	65	68	java/lang/Exception
    //   79	83	84	java/io/IOException
    //   9	18	86	finally
    //   75	79	86	finally
    //   53	58	94	java/io/IOException
    //   87	92	96	java/io/IOException
    //   18	29	100	finally
    //   29	41	108	finally
    //   46	53	108	finally
    //   59	65	108	finally
    //   9	18	112	java/lang/Exception
    //   18	29	119	java/lang/Exception
  }

  // ERROR //
  public void put(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 56	com/yolanda/nohttp/NoHttpCache:mCache	Lcom/yolanda/nohttp/NoHttpCache$NoHttpCacheManager;
    //   4: aload_1
    //   5: invokestatic 150	com/yolanda/nohttp/NoHttpCache$NoHttpCacheManager:access$5	(Lcom/yolanda/nohttp/NoHttpCache$NoHttpCacheManager;Ljava/lang/String;)Ljava/io/File;
    //   8: astore 5
    //   10: aconst_null
    //   11: astore_1
    //   12: aconst_null
    //   13: astore 4
    //   15: new 297	java/io/BufferedWriter
    //   18: dup
    //   19: new 299	java/io/FileWriter
    //   22: dup
    //   23: aload 5
    //   25: invokespecial 300	java/io/FileWriter:<init>	(Ljava/io/File;)V
    //   28: sipush 1024
    //   31: invokespecial 303	java/io/BufferedWriter:<init>	(Ljava/io/Writer;I)V
    //   34: astore_3
    //   35: aload_3
    //   36: aload_2
    //   37: invokevirtual 306	java/io/BufferedWriter:write	(Ljava/lang/String;)V
    //   40: aload_3
    //   41: ifnull +11 -> 52
    //   44: aload_3
    //   45: invokevirtual 309	java/io/BufferedWriter:flush	()V
    //   48: aload_3
    //   49: invokevirtual 310	java/io/BufferedWriter:close	()V
    //   52: aload_0
    //   53: getfield 56	com/yolanda/nohttp/NoHttpCache:mCache	Lcom/yolanda/nohttp/NoHttpCache$NoHttpCacheManager;
    //   56: aload 5
    //   58: invokestatic 313	com/yolanda/nohttp/NoHttpCache$NoHttpCacheManager:access$6	(Lcom/yolanda/nohttp/NoHttpCache$NoHttpCacheManager;Ljava/io/File;)V
    //   61: return
    //   62: astore_3
    //   63: aload 4
    //   65: astore_2
    //   66: aload_2
    //   67: astore_1
    //   68: aload_3
    //   69: invokestatic 319	com/yolanda/nohttp/Logger:e	(Ljava/lang/Throwable;)V
    //   72: aload_2
    //   73: ifnull +11 -> 84
    //   76: aload_2
    //   77: invokevirtual 309	java/io/BufferedWriter:flush	()V
    //   80: aload_2
    //   81: invokevirtual 310	java/io/BufferedWriter:close	()V
    //   84: aload_0
    //   85: getfield 56	com/yolanda/nohttp/NoHttpCache:mCache	Lcom/yolanda/nohttp/NoHttpCache$NoHttpCacheManager;
    //   88: aload 5
    //   90: invokestatic 313	com/yolanda/nohttp/NoHttpCache$NoHttpCacheManager:access$6	(Lcom/yolanda/nohttp/NoHttpCache$NoHttpCacheManager;Ljava/io/File;)V
    //   93: return
    //   94: astore_2
    //   95: aload_1
    //   96: ifnull +11 -> 107
    //   99: aload_1
    //   100: invokevirtual 309	java/io/BufferedWriter:flush	()V
    //   103: aload_1
    //   104: invokevirtual 310	java/io/BufferedWriter:close	()V
    //   107: aload_0
    //   108: getfield 56	com/yolanda/nohttp/NoHttpCache:mCache	Lcom/yolanda/nohttp/NoHttpCache$NoHttpCacheManager;
    //   111: aload 5
    //   113: invokestatic 313	com/yolanda/nohttp/NoHttpCache$NoHttpCacheManager:access$6	(Lcom/yolanda/nohttp/NoHttpCache$NoHttpCacheManager;Ljava/io/File;)V
    //   116: aload_2
    //   117: athrow
    //   118: astore_1
    //   119: goto -67 -> 52
    //   122: astore_1
    //   123: goto -16 -> 107
    //   126: astore_2
    //   127: aload_3
    //   128: astore_1
    //   129: goto -34 -> 95
    //   132: astore_1
    //   133: goto -49 -> 84
    //   136: astore_1
    //   137: aload_3
    //   138: astore_2
    //   139: aload_1
    //   140: astore_3
    //   141: goto -75 -> 66
    //
    // Exception table:
    //   from	to	target	type
    //   15	35	62	java/io/IOException
    //   15	35	94	finally
    //   68	72	94	finally
    //   44	52	118	java/io/IOException
    //   99	107	122	java/io/IOException
    //   35	40	126	finally
    //   76	84	132	java/io/IOException
    //   35	40	136	java/io/IOException
  }

  public void put(String paramString1, String paramString2, int paramInt)
  {
    put(paramString1, Utils.newStringWithDateInfo(paramInt, paramString2));
  }

  // ERROR //
  public void put(String paramString, byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 56	com/yolanda/nohttp/NoHttpCache:mCache	Lcom/yolanda/nohttp/NoHttpCache$NoHttpCacheManager;
    //   4: aload_1
    //   5: invokestatic 150	com/yolanda/nohttp/NoHttpCache$NoHttpCacheManager:access$5	(Lcom/yolanda/nohttp/NoHttpCache$NoHttpCacheManager;Ljava/lang/String;)Ljava/io/File;
    //   8: astore 5
    //   10: aconst_null
    //   11: astore_1
    //   12: aconst_null
    //   13: astore 4
    //   15: new 328	java/io/FileOutputStream
    //   18: dup
    //   19: aload 5
    //   21: invokespecial 329	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   24: astore_3
    //   25: aload_3
    //   26: aload_2
    //   27: invokevirtual 331	java/io/FileOutputStream:write	([B)V
    //   30: aload_3
    //   31: ifnull +11 -> 42
    //   34: aload_3
    //   35: invokevirtual 332	java/io/FileOutputStream:flush	()V
    //   38: aload_3
    //   39: invokevirtual 333	java/io/FileOutputStream:close	()V
    //   42: aload_0
    //   43: getfield 56	com/yolanda/nohttp/NoHttpCache:mCache	Lcom/yolanda/nohttp/NoHttpCache$NoHttpCacheManager;
    //   46: aload 5
    //   48: invokestatic 313	com/yolanda/nohttp/NoHttpCache$NoHttpCacheManager:access$6	(Lcom/yolanda/nohttp/NoHttpCache$NoHttpCacheManager;Ljava/io/File;)V
    //   51: return
    //   52: astore_3
    //   53: aload 4
    //   55: astore_2
    //   56: aload_2
    //   57: astore_1
    //   58: aload_3
    //   59: invokevirtual 193	java/lang/Exception:printStackTrace	()V
    //   62: aload_2
    //   63: ifnull +11 -> 74
    //   66: aload_2
    //   67: invokevirtual 332	java/io/FileOutputStream:flush	()V
    //   70: aload_2
    //   71: invokevirtual 333	java/io/FileOutputStream:close	()V
    //   74: aload_0
    //   75: getfield 56	com/yolanda/nohttp/NoHttpCache:mCache	Lcom/yolanda/nohttp/NoHttpCache$NoHttpCacheManager;
    //   78: aload 5
    //   80: invokestatic 313	com/yolanda/nohttp/NoHttpCache$NoHttpCacheManager:access$6	(Lcom/yolanda/nohttp/NoHttpCache$NoHttpCacheManager;Ljava/io/File;)V
    //   83: return
    //   84: astore_1
    //   85: aload_1
    //   86: invokevirtual 169	java/io/IOException:printStackTrace	()V
    //   89: goto -15 -> 74
    //   92: astore_2
    //   93: aload_1
    //   94: ifnull +11 -> 105
    //   97: aload_1
    //   98: invokevirtual 332	java/io/FileOutputStream:flush	()V
    //   101: aload_1
    //   102: invokevirtual 333	java/io/FileOutputStream:close	()V
    //   105: aload_0
    //   106: getfield 56	com/yolanda/nohttp/NoHttpCache:mCache	Lcom/yolanda/nohttp/NoHttpCache$NoHttpCacheManager;
    //   109: aload 5
    //   111: invokestatic 313	com/yolanda/nohttp/NoHttpCache$NoHttpCacheManager:access$6	(Lcom/yolanda/nohttp/NoHttpCache$NoHttpCacheManager;Ljava/io/File;)V
    //   114: aload_2
    //   115: athrow
    //   116: astore_1
    //   117: aload_1
    //   118: invokevirtual 169	java/io/IOException:printStackTrace	()V
    //   121: goto -16 -> 105
    //   124: astore_1
    //   125: aload_1
    //   126: invokevirtual 169	java/io/IOException:printStackTrace	()V
    //   129: goto -87 -> 42
    //   132: astore_2
    //   133: aload_3
    //   134: astore_1
    //   135: goto -42 -> 93
    //   138: astore_1
    //   139: aload_3
    //   140: astore_2
    //   141: aload_1
    //   142: astore_3
    //   143: goto -87 -> 56
    //
    // Exception table:
    //   from	to	target	type
    //   15	25	52	java/lang/Exception
    //   66	74	84	java/io/IOException
    //   15	25	92	finally
    //   58	62	92	finally
    //   97	105	116	java/io/IOException
    //   34	42	124	java/io/IOException
    //   25	30	132	finally
    //   25	30	138	java/lang/Exception
  }

  public void put(String paramString, byte[] paramArrayOfByte, int paramInt)
  {
    put(paramString, Utils.newByteArrayWithDateInfo(paramInt, paramArrayOfByte));
  }

  public boolean remove(String paramString)
  {
    return this.mCache.remove(paramString);
  }

  public class NoHttpCacheManager
  {
    private final AtomicInteger cacheCount;
    protected File cacheDir;
    private final AtomicLong cacheSize;
    private final int countLimit;
    private final Map<File, Long> lastUsageDates = Collections.synchronizedMap(new HashMap());
    private final long sizeLimit;

    private NoHttpCacheManager(File paramLong, long arg3, int arg5)
    {
      this.cacheDir = paramLong;
      this.sizeLimit = ???;
      int i;
      this.countLimit = i;
      this.cacheSize = new AtomicLong();
      this.cacheCount = new AtomicInteger();
      calculateCacheSizeAndCacheCount();
    }

    private void calculateCacheSizeAndCacheCount()
    {
      new Thread(new Runnable()
      {
        public void run()
        {
          int k = 0;
          int j = 0;
          File[] arrayOfFile = NoHttpCache.NoHttpCacheManager.this.cacheDir.listFiles();
          int m;
          int i;
          if (arrayOfFile != null)
          {
            m = arrayOfFile.length;
            i = 0;
          }
          while (true)
          {
            if (i >= m)
            {
              NoHttpCache.NoHttpCacheManager.this.cacheSize.set(k);
              NoHttpCache.NoHttpCacheManager.this.cacheCount.set(j);
              return;
            }
            File localFile = arrayOfFile[i];
            k = (int)(k + NoHttpCache.NoHttpCacheManager.this.calculateSize(localFile));
            j += 1;
            NoHttpCache.NoHttpCacheManager.this.lastUsageDates.put(localFile, Long.valueOf(localFile.lastModified()));
            i += 1;
          }
        }
      }).start();
    }

    private long calculateSize(File paramFile)
    {
      return paramFile.length();
    }

    private void clear()
    {
      this.lastUsageDates.clear();
      this.cacheSize.set(0L);
      File[] arrayOfFile = this.cacheDir.listFiles();
      int j;
      int i;
      if (arrayOfFile != null)
      {
        j = arrayOfFile.length;
        i = 0;
      }
      while (true)
      {
        if (i >= j)
          return;
        arrayOfFile[i].delete();
        i += 1;
      }
    }

    private File get(String paramString)
    {
      paramString = newFile(paramString);
      Long localLong = Long.valueOf(System.currentTimeMillis());
      paramString.setLastModified(localLong.longValue());
      this.lastUsageDates.put(paramString, localLong);
      return paramString;
    }

    private File newFile(String paramString)
    {
      return new File(this.cacheDir, paramString.hashCode());
    }

    private void put(File paramFile)
    {
      int i = this.cacheCount.get();
      long l2;
      if (i + 1 <= this.countLimit)
      {
        this.cacheCount.addAndGet(1);
        l2 = calculateSize(paramFile);
      }
      for (long l1 = this.cacheSize.get(); ; l1 = this.cacheSize.addAndGet(-l1))
      {
        if (l1 + l2 <= this.sizeLimit)
        {
          this.cacheSize.addAndGet(l2);
          Long localLong = Long.valueOf(System.currentTimeMillis());
          paramFile.setLastModified(localLong.longValue());
          this.lastUsageDates.put(paramFile, localLong);
          return;
          l1 = removeNext();
          this.cacheSize.addAndGet(-l1);
          i = this.cacheCount.addAndGet(-1);
          break;
        }
        l1 = removeNext();
      }
    }

    private boolean remove(String paramString)
    {
      return get(paramString).delete();
    }

    private long removeNext()
    {
      long l1;
      if (this.lastUsageDates.isEmpty())
        l1 = 0L;
      while (true)
      {
        return l1;
        Object localObject2 = null;
        File localFile = null;
        Object localObject3 = this.lastUsageDates.entrySet();
        synchronized (this.lastUsageDates)
        {
          Iterator localIterator = ((Set)localObject3).iterator();
          Map.Entry localEntry;
          label139: 
          do
          {
            while (true)
            {
              if (!localIterator.hasNext())
              {
                long l2 = calculateSize(localFile);
                l1 = l2;
                if (!localFile.delete())
                  break;
                this.lastUsageDates.remove(localFile);
                return l2;
              }
              localEntry = (Map.Entry)localIterator.next();
              if (localFile != null)
                break label139;
              localFile = (File)localEntry.getKey();
              localObject2 = (Long)localEntry.getValue();
            }
            localObject3 = (Long)localEntry.getValue();
          }
          while (((Long)localObject3).longValue() >= ((Long)localObject2).longValue());
          localObject2 = localObject3;
          localFile = (File)localEntry.getKey();
        }
      }
    }
  }

  private static class Utils
  {
    private static final char mSeparator = ' ';

    private static byte[] Bitmap2Bytes(Bitmap paramBitmap)
    {
      if (paramBitmap == null)
        return null;
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localByteArrayOutputStream);
      return localByteArrayOutputStream.toByteArray();
    }

    private static Bitmap Bytes2Bimap(byte[] paramArrayOfByte)
    {
      if (paramArrayOfByte.length == 0)
        return null;
      return BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length);
    }

    private static Drawable bitmap2Drawable(Resources paramResources, Bitmap paramBitmap)
    {
      if (paramBitmap == null)
        return null;
      return new BitmapDrawable(paramResources, paramBitmap);
    }

    private static String clearDateInfo(String paramString)
    {
      String str = paramString;
      if (paramString != null)
      {
        str = paramString;
        if (hasDateInfo(paramString.getBytes()))
          str = paramString.substring(paramString.indexOf(' ') + 1, paramString.length());
      }
      return str;
    }

    private static byte[] clearDateInfo(byte[] paramArrayOfByte)
    {
      byte[] arrayOfByte = paramArrayOfByte;
      if (hasDateInfo(paramArrayOfByte))
        arrayOfByte = copyOfRange(paramArrayOfByte, indexOf(paramArrayOfByte, ' ') + 1, paramArrayOfByte.length);
      return arrayOfByte;
    }

    private static byte[] copyOfRange(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    {
      int i = paramInt2 - paramInt1;
      if (i < 0)
        throw new IllegalArgumentException(paramInt1 + " > " + paramInt2);
      byte[] arrayOfByte = new byte[i];
      System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, 0, Math.min(paramArrayOfByte.length - paramInt1, i));
      return arrayOfByte;
    }

    private static String createDateInfo(int paramInt)
    {
      for (String str = System.currentTimeMillis(); ; str = "0" + str)
        if (str.length() >= 13)
          return str + "-" + paramInt + ' ';
    }

    private static Bitmap drawable2Bitmap(Drawable paramDrawable)
    {
      if (paramDrawable == null)
        return null;
      int i = paramDrawable.getIntrinsicWidth();
      int j = paramDrawable.getIntrinsicHeight();
      if (paramDrawable.getOpacity() != -1);
      for (Object localObject = Bitmap.Config.ARGB_8888; ; localObject = Bitmap.Config.RGB_565)
      {
        localObject = Bitmap.createBitmap(i, j, (Bitmap.Config)localObject);
        Canvas localCanvas = new Canvas((Bitmap)localObject);
        paramDrawable.setBounds(0, 0, i, j);
        paramDrawable.draw(localCanvas);
        return localObject;
      }
    }

    private static String[] getDateInfoFromDate(byte[] paramArrayOfByte)
    {
      if (hasDateInfo(paramArrayOfByte))
        return new String[] { new String(copyOfRange(paramArrayOfByte, 0, 13)), new String(copyOfRange(paramArrayOfByte, 14, indexOf(paramArrayOfByte, ' '))) };
      return null;
    }

    private static boolean hasDateInfo(byte[] paramArrayOfByte)
    {
      return (paramArrayOfByte != null) && (paramArrayOfByte.length > 15) && (paramArrayOfByte[13] == 45) && (indexOf(paramArrayOfByte, ' ') > 14);
    }

    private static int indexOf(byte[] paramArrayOfByte, char paramChar)
    {
      int i = 0;
      while (true)
      {
        int j;
        if (i >= paramArrayOfByte.length)
          j = -1;
        do
        {
          return j;
          j = i;
        }
        while (paramArrayOfByte[i] == paramChar);
        i += 1;
      }
    }

    private static boolean isDue(String paramString)
    {
      return isDue(paramString.getBytes());
    }

    private static boolean isDue(byte[] paramArrayOfByte)
    {
      String[] arrayOfString = getDateInfoFromDate(paramArrayOfByte);
      if ((arrayOfString != null) && (arrayOfString.length == 2))
        for (paramArrayOfByte = arrayOfString[0]; ; paramArrayOfByte = paramArrayOfByte.substring(1, paramArrayOfByte.length()))
          if (!paramArrayOfByte.startsWith("0"))
          {
            long l1 = Long.valueOf(paramArrayOfByte).longValue();
            long l2 = Long.valueOf(arrayOfString[1]).longValue();
            if (System.currentTimeMillis() <= 1000L * l2 + l1)
              break;
            return true;
          }
      return false;
    }

    private static byte[] newByteArrayWithDateInfo(int paramInt, byte[] paramArrayOfByte)
    {
      byte[] arrayOfByte1 = createDateInfo(paramInt).getBytes();
      byte[] arrayOfByte2 = new byte[arrayOfByte1.length + paramArrayOfByte.length];
      System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, arrayOfByte1.length);
      System.arraycopy(paramArrayOfByte, 0, arrayOfByte2, arrayOfByte1.length, paramArrayOfByte.length);
      return arrayOfByte2;
    }

    private static String newStringWithDateInfo(int paramInt, String paramString)
    {
      return createDateInfo(paramInt) + paramString;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.NoHttpCache
 * JD-Core Version:    0.6.2
 */