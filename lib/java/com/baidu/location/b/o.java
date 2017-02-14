package com.baidu.location.b;

import com.baidu.location.Jni;
import java.io.File;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class o
  implements f
{
  private static o e0 = null;
  private static final int e2 = 128;
  private static String e3 = "LogSDK";
  private static int e4 = 0;
  private static final int e5 = 32;
  private static int e8 = 0;
  private static final int e9 = 1040;
  private static final String eZ = e.int + "/llg.dat";
  private static final String fb = e.int + "/ller.dat";
  private static final int fc = 1000;
  public static final String fd = e.int + "/llin.dat";
  private l e1 = null;
  private long e6 = 0L;
  private SimpleDateFormat e7 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  private a fa = null;

  private o()
  {
    if (this.e1 == null)
      this.e1 = new l();
  }

  public static o aY()
  {
    if (e0 == null);
    try
    {
      if (e0 == null)
        e0 = new o();
      return e0;
    }
    finally
    {
    }
  }

  // ERROR //
  public static void jdMethod_for(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: new 107	java/io/File
    //   6: dup
    //   7: aload_0
    //   8: invokespecial 108	java/io/File:<init>	(Ljava/lang/String;)V
    //   11: astore 9
    //   13: aload 9
    //   15: invokevirtual 112	java/io/File:exists	()Z
    //   18: ifne +7 -> 25
    //   21: aload_0
    //   22: invokestatic 115	com/baidu/location/b/o:i	(Ljava/lang/String;)V
    //   25: new 117	java/io/RandomAccessFile
    //   28: dup
    //   29: aload 9
    //   31: ldc 119
    //   33: invokespecial 122	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   36: astore_0
    //   37: aload_0
    //   38: ldc2_w 123
    //   41: invokevirtual 128	java/io/RandomAccessFile:seek	(J)V
    //   44: aload_0
    //   45: invokevirtual 132	java/io/RandomAccessFile:readInt	()I
    //   48: istore_3
    //   49: aload_0
    //   50: invokevirtual 132	java/io/RandomAccessFile:readInt	()I
    //   53: istore 6
    //   55: aload_0
    //   56: invokevirtual 132	java/io/RandomAccessFile:readInt	()I
    //   59: istore_2
    //   60: aload_0
    //   61: invokevirtual 132	java/io/RandomAccessFile:readInt	()I
    //   64: istore 4
    //   66: aload_0
    //   67: invokevirtual 132	java/io/RandomAccessFile:readInt	()I
    //   70: istore 5
    //   72: iload_2
    //   73: iload_3
    //   74: if_icmpge +90 -> 164
    //   77: aload_0
    //   78: iload 6
    //   80: iload_2
    //   81: imul
    //   82: sipush 128
    //   85: iadd
    //   86: i2l
    //   87: invokevirtual 128	java/io/RandomAccessFile:seek	(J)V
    //   90: new 50	java/lang/StringBuilder
    //   93: dup
    //   94: invokespecial 53	java/lang/StringBuilder:<init>	()V
    //   97: aload_1
    //   98: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: iconst_0
    //   102: invokevirtual 135	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   105: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   108: invokevirtual 141	java/lang/String:getBytes	()[B
    //   111: astore_1
    //   112: aload_0
    //   113: aload_1
    //   114: arraylength
    //   115: invokevirtual 145	java/io/RandomAccessFile:writeInt	(I)V
    //   118: aload_0
    //   119: aload_1
    //   120: iconst_0
    //   121: aload_1
    //   122: arraylength
    //   123: invokevirtual 149	java/io/RandomAccessFile:write	([BII)V
    //   126: iload_2
    //   127: iconst_1
    //   128: iadd
    //   129: istore_3
    //   130: iload 4
    //   132: istore_2
    //   133: aload_0
    //   134: ldc2_w 150
    //   137: invokevirtual 128	java/io/RandomAccessFile:seek	(J)V
    //   140: aload_0
    //   141: iload_3
    //   142: invokevirtual 145	java/io/RandomAccessFile:writeInt	(I)V
    //   145: aload_0
    //   146: iload_2
    //   147: invokevirtual 145	java/io/RandomAccessFile:writeInt	(I)V
    //   150: aload_0
    //   151: iload 5
    //   153: invokevirtual 145	java/io/RandomAccessFile:writeInt	(I)V
    //   156: aload_0
    //   157: invokevirtual 154	java/io/RandomAccessFile:close	()V
    //   160: ldc 2
    //   162: monitorexit
    //   163: return
    //   164: iload 4
    //   166: iload 6
    //   168: imul
    //   169: sipush 128
    //   172: iadd
    //   173: i2l
    //   174: lstore 7
    //   176: aload_0
    //   177: lload 7
    //   179: invokevirtual 128	java/io/RandomAccessFile:seek	(J)V
    //   182: new 50	java/lang/StringBuilder
    //   185: dup
    //   186: invokespecial 53	java/lang/StringBuilder:<init>	()V
    //   189: aload_1
    //   190: invokevirtual 62	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   193: iconst_0
    //   194: invokevirtual 135	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   197: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   200: invokevirtual 141	java/lang/String:getBytes	()[B
    //   203: astore_1
    //   204: aload_0
    //   205: aload_1
    //   206: arraylength
    //   207: invokevirtual 145	java/io/RandomAccessFile:writeInt	(I)V
    //   210: aload_0
    //   211: aload_1
    //   212: iconst_0
    //   213: aload_1
    //   214: arraylength
    //   215: invokevirtual 149	java/io/RandomAccessFile:write	([BII)V
    //   218: iload 4
    //   220: iconst_1
    //   221: iadd
    //   222: istore 4
    //   224: iload 4
    //   226: iload_2
    //   227: if_icmple +24 -> 251
    //   230: iconst_0
    //   231: istore 4
    //   233: iload_2
    //   234: istore_3
    //   235: iload 4
    //   237: istore_2
    //   238: goto -105 -> 133
    //   241: astore_0
    //   242: ldc 2
    //   244: monitorexit
    //   245: aload_0
    //   246: athrow
    //   247: astore_0
    //   248: goto -88 -> 160
    //   251: iload_2
    //   252: istore_3
    //   253: iload 4
    //   255: istore_2
    //   256: goto -123 -> 133
    //
    // Exception table:
    //   from	to	target	type
    //   3	25	241	finally
    //   25	72	241	finally
    //   77	126	241	finally
    //   133	160	241	finally
    //   176	218	241	finally
    //   25	72	247	java/lang/Exception
    //   77	126	247	java/lang/Exception
    //   133	160	247	java/lang/Exception
    //   176	218	247	java/lang/Exception
  }

  private static void i(String paramString)
  {
    try
    {
      File localFile = new File(paramString);
      if (!localFile.exists())
      {
        paramString = new File(e.int);
        if (!paramString.exists())
          paramString.mkdirs();
        paramString = localFile;
        if (!localFile.createNewFile())
          paramString = null;
        paramString = new RandomAccessFile(paramString, "rw");
        paramString.seek(0L);
        paramString.writeInt(32);
        paramString.writeInt(1000);
        paramString.writeInt(1040);
        paramString.writeInt(0);
        paramString.writeInt(0);
        paramString.writeInt(0);
        paramString.close();
      }
      return;
    }
    catch (Exception paramString)
    {
    }
  }

  public static boolean jdMethod_if(String paramString, List paramList)
  {
    paramString = new File(paramString);
    if (!paramString.exists())
      return false;
    while (true)
    {
      int i;
      int j;
      int k;
      boolean bool2;
      try
      {
        paramString = new RandomAccessFile(paramString, "rw");
        paramString.seek(8L);
        int n = paramString.readInt();
        i = paramString.readInt();
        m = paramString.readInt();
        byte[] arrayOfByte = new byte[e4];
        j = e8;
        j += 1;
        bool1 = false;
        long l;
        if ((j > 0) && (i > 0))
        {
          k = m;
          if (i < m)
            k = 0;
          l = (i - 1) * n + 128;
        }
        try
        {
          paramString.seek(l);
          m = paramString.readInt();
          bool2 = bool1;
          if (m > 0)
          {
            bool2 = bool1;
            if (m < n)
            {
              paramString.read(arrayOfByte, 0, m);
              bool2 = bool1;
              if (arrayOfByte[(m - 1)] == 0)
              {
                paramList.add(0, new String(arrayOfByte, 0, m - 1));
                bool2 = true;
                break label220;
                paramString.seek(12L);
                paramString.writeInt(i);
                paramString.writeInt(m);
                paramString.close();
                return bool1;
              }
            }
          }
        }
        catch (Exception paramString)
        {
          return bool1;
        }
      }
      catch (Exception paramString)
      {
        return false;
      }
      label220: j -= 1;
      i -= 1;
      boolean bool1 = bool2;
      int m = k;
    }
  }

  public l aV()
  {
    return this.e1;
  }

  public void aW()
  {
    if (this.fa == null)
      this.fa = new a();
    if (System.currentTimeMillis() - this.e6 < 3600000L);
    while (true)
    {
      return;
      if (!this.fa.a())
        try
        {
          ArrayList localArrayList = new ArrayList();
          jdMethod_if(fb, localArrayList);
          int i;
          int j;
          if (localArrayList.size() > 0)
          {
            i = 0;
            j = 1;
          }
          while (true)
          {
            JSONArray localJSONArray = new JSONArray();
            JSONObject localJSONObject = new JSONObject();
            if (localArrayList.size() <= 0)
              break;
            int m = localArrayList.size();
            int k = 0;
            while (true)
              if (k < m)
              {
                localJSONArray.put((String)localArrayList.get(k));
                k += 1;
                continue;
                jdMethod_if(eZ, localArrayList);
                if (localArrayList.size() != 0)
                  break label238;
                jdMethod_if(fd, localArrayList);
                i = 1;
                j = 0;
                break;
              }
            if (j != 0)
              localJSONObject.put("locpt", localJSONArray);
            while (true)
            {
              this.fa.a(localJSONObject.toString());
              return;
              if (i != 0)
                localJSONObject.put("locup", localJSONArray);
              else
                localJSONObject.put("loctc", localJSONArray);
            }
            label238: i = 0;
            j = 0;
          }
        }
        catch (Exception localException)
        {
        }
    }
  }

  public void aX()
  {
    if (this.e1 != null)
    {
      String str = Jni.H(this.e1.ak());
      jdMethod_for(eZ, str);
      this.e1.aj();
    }
  }

  public void jdMethod_if(l paraml)
  {
    if (paraml != null)
    {
      paraml = Jni.H(paraml.ak());
      jdMethod_for(eZ, paraml);
    }
  }

  public void j(String paramString)
  {
    if (paramString != null);
    try
    {
      StringBuffer localStringBuffer = new StringBuffer();
      Object localObject = new Date();
      localObject = this.e7.format((Date)localObject);
      localStringBuffer.append("&time=");
      localStringBuffer.append((String)localObject);
      localStringBuffer.append("&err=");
      localStringBuffer.append(paramString);
      localStringBuffer.append(c.N().jdMethod_do(false));
      localStringBuffer.append(com.baidu.location.e.c.br().bu());
      paramString = Jni.H(localStringBuffer.toString());
      jdMethod_for(fb, paramString);
      return;
    }
    catch (Exception paramString)
    {
    }
  }

  private class a extends m
  {
    private String c = null;
    private boolean d = false;

    a()
    {
      this.c7 = new ArrayList();
    }

    public void a(String paramString)
    {
      this.c = paramString;
      if (this.c != null)
      {
        ao();
        this.d = true;
      }
    }

    public boolean a()
    {
      return this.d;
    }

    public void au()
    {
      this.c7.clear();
      this.c7.add(new BasicNameValuePair("qt", "stat"));
      this.c7.add(new BasicNameValuePair("req", this.c));
      this.c5 = "http://loc.map.baidu.com/statloc";
    }

    public void jdMethod_int(boolean paramBoolean)
    {
      this.d = false;
      if ((paramBoolean) && (this.c6 != null));
      try
      {
        EntityUtils.toString(this.c6, "utf-8");
        return;
        o.jdMethod_if(o.this, System.currentTimeMillis());
        return;
      }
      catch (Exception localException)
      {
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.b.o
 * JD-Core Version:    0.6.2
 */