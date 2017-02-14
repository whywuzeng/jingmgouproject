package com.baidu.location.e;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import com.baidu.location.Jni;
import com.baidu.location.b.c;
import com.baidu.location.b.e;
import com.baidu.location.b.m;
import com.baidu.location.b.n;
import com.baidu.location.f;
import com.baidu.location.h.l;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class d extends m
{
  private static d ey = null;
  Handler eA = null;
  String eB = null;
  String eC = null;
  String eD = null;
  String eE = null;
  int ez = 1;

  private boolean aL()
  {
    if (this.eC == null);
    while (new File(com.baidu.location.b.k.ai() + File.separator + this.eC).exists())
      return true;
    return jdMethod_do("http://" + this.eE + "/" + this.eC, this.eC);
  }

  private void aM()
  {
    if (this.eD == null);
    File localFile;
    do
    {
      do
      {
        return;
        localFile = new File(com.baidu.location.b.k.ai() + File.separator + this.eD);
      }
      while ((localFile.exists()) || (!jdMethod_do("http://" + this.eE + "/" + this.eD, this.eD)));
      localObject = com.baidu.location.b.k.jdMethod_if(localFile);
    }
    while ((this.eB == null) || (localObject == null) || (!this.eB.equals(localObject)));
    Object localObject = new File(com.baidu.location.b.k.ai() + File.separator + f.replaceFileName);
    if (((File)localObject).exists())
      ((File)localObject).delete();
    try
    {
      jdMethod_if(localFile, (File)localObject);
      return;
    }
    catch (Exception localException)
    {
      ((File)localObject).delete();
    }
  }

  private Handler aN()
  {
    return this.eA;
  }

  private void aO()
  {
    Object localObject1 = e.int + "/grtcf.dat";
    try
    {
      localObject1 = new File((String)localObject1);
      Object localObject2;
      if (!((File)localObject1).exists())
      {
        localObject2 = new File(e.int);
        if (!((File)localObject2).exists())
          ((File)localObject2).mkdirs();
        if (((File)localObject1).createNewFile())
        {
          localObject2 = new RandomAccessFile((File)localObject1, "rw");
          ((RandomAccessFile)localObject2).seek(2L);
          ((RandomAccessFile)localObject2).writeInt(0);
          ((RandomAccessFile)localObject2).seek(8L);
          byte[] arrayOfByte = "1980_01_01:0".getBytes();
          ((RandomAccessFile)localObject2).writeInt(arrayOfByte.length);
          ((RandomAccessFile)localObject2).write(arrayOfByte);
          ((RandomAccessFile)localObject2).seek(200L);
          ((RandomAccessFile)localObject2).writeBoolean(false);
          ((RandomAccessFile)localObject2).seek(800L);
          ((RandomAccessFile)localObject2).writeBoolean(false);
          ((RandomAccessFile)localObject2).close();
        }
      }
      else
      {
        localObject1 = new RandomAccessFile((File)localObject1, "rw");
        ((RandomAccessFile)localObject1).seek(200L);
        ((RandomAccessFile)localObject1).writeBoolean(true);
        if (this.ez == 1)
        {
          ((RandomAccessFile)localObject1).writeBoolean(true);
          if (this.eB == null)
            break label218;
          localObject2 = this.eB.getBytes();
          ((RandomAccessFile)localObject1).writeInt(localObject2.length);
          ((RandomAccessFile)localObject1).write((byte[])localObject2);
        }
        while (true)
        {
          ((RandomAccessFile)localObject1).close();
          return;
          ((RandomAccessFile)localObject1).writeBoolean(false);
          break;
          label218: ((RandomAccessFile)localObject1).writeInt(0);
        }
      }
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private void aP()
  {
    if (this.eE == null);
    while (!l.a().db())
      return;
    new r(this).start();
  }

  public static d aR()
  {
    if (ey == null)
      ey = new d();
    return ey;
  }

  private static boolean jdMethod_do(String paramString1, String paramString2)
  {
    File localFile = new File(com.baidu.location.b.k.ai() + File.separator + "tmp");
    if (localFile.exists())
      localFile.delete();
    FileOutputStream localFileOutputStream;
    try
    {
      localFileOutputStream = new FileOutputStream(localFile);
      byte[] arrayOfByte = new byte[4096];
      paramString1 = (HttpURLConnection)new URL(paramString1).openConnection();
      BufferedInputStream localBufferedInputStream = new BufferedInputStream(paramString1.getInputStream());
      while (true)
      {
        int i = localBufferedInputStream.read(arrayOfByte);
        if (i <= 0)
          break;
        localFileOutputStream.write(arrayOfByte, 0, i);
      }
    }
    catch (Exception paramString1)
    {
      localFile.delete();
      return false;
    }
    paramString1.disconnect();
    localFileOutputStream.close();
    if (localFile.length() < 10240L)
    {
      localFile.delete();
      return false;
    }
    localFile.renameTo(new File(com.baidu.location.b.k.ai() + File.separator + paramString2));
    return true;
  }

  // ERROR //
  public static void jdMethod_if(File paramFile1, File paramFile2)
    throws java.io.IOException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: new 198	java/io/BufferedInputStream
    //   6: dup
    //   7: new 236	java/io/FileInputStream
    //   10: dup
    //   11: aload_0
    //   12: invokespecial 237	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   15: invokespecial 205	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   18: astore_3
    //   19: new 239	java/io/BufferedOutputStream
    //   22: dup
    //   23: new 184	java/io/FileOutputStream
    //   26: dup
    //   27: aload_1
    //   28: invokespecial 187	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   31: invokespecial 242	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   34: astore_1
    //   35: sipush 5120
    //   38: newarray byte
    //   40: astore 4
    //   42: aload_3
    //   43: aload 4
    //   45: invokevirtual 209	java/io/BufferedInputStream:read	([B)I
    //   48: istore_2
    //   49: iload_2
    //   50: iconst_m1
    //   51: if_icmpeq +33 -> 84
    //   54: aload_1
    //   55: aload 4
    //   57: iconst_0
    //   58: iload_2
    //   59: invokevirtual 243	java/io/BufferedOutputStream:write	([BII)V
    //   62: goto -20 -> 42
    //   65: astore_0
    //   66: aload_3
    //   67: ifnull +7 -> 74
    //   70: aload_3
    //   71: invokevirtual 244	java/io/BufferedInputStream:close	()V
    //   74: aload_1
    //   75: ifnull +7 -> 82
    //   78: aload_1
    //   79: invokevirtual 245	java/io/BufferedOutputStream:close	()V
    //   82: aload_0
    //   83: athrow
    //   84: aload_1
    //   85: invokevirtual 248	java/io/BufferedOutputStream:flush	()V
    //   88: aload_0
    //   89: invokevirtual 96	java/io/File:delete	()Z
    //   92: pop
    //   93: aload_3
    //   94: ifnull +7 -> 101
    //   97: aload_3
    //   98: invokevirtual 244	java/io/BufferedInputStream:close	()V
    //   101: aload_1
    //   102: ifnull +7 -> 109
    //   105: aload_1
    //   106: invokevirtual 245	java/io/BufferedOutputStream:close	()V
    //   109: return
    //   110: astore_0
    //   111: aconst_null
    //   112: astore_1
    //   113: aload 4
    //   115: astore_3
    //   116: goto -50 -> 66
    //   119: astore_0
    //   120: aconst_null
    //   121: astore_1
    //   122: goto -56 -> 66
    //
    // Exception table:
    //   from	to	target	type
    //   35	42	65	finally
    //   42	49	65	finally
    //   54	62	65	finally
    //   84	93	65	finally
    //   3	19	110	finally
    //   19	35	119	finally
  }

  public void aQ()
  {
    long l = n.jdMethod_if().jdMethod_do();
    if (System.currentTimeMillis() - l > 86400000L)
    {
      aN().postDelayed(new g(this), 10000L);
      aN().postDelayed(new j(this), 5000L);
    }
  }

  public void au()
  {
    StringBuffer localStringBuffer = new StringBuffer(128);
    localStringBuffer.append("&sdk=");
    localStringBuffer.append(6.12F);
    localStringBuffer.append("&fw=");
    localStringBuffer.append(f.getFrameVersion());
    localStringBuffer.append("&suit=");
    localStringBuffer.append(2);
    if (c.N().bm == null)
    {
      localStringBuffer.append("&im=");
      localStringBuffer.append(c.N().bo);
    }
    while (true)
    {
      localStringBuffer.append("&mb=");
      localStringBuffer.append(Build.MODEL);
      localStringBuffer.append("&sv=");
      String str2 = Build.VERSION.RELEASE;
      String str1 = str2;
      if (str2 != null)
      {
        str1 = str2;
        if (str2.length() > 10)
          str1 = str2.substring(0, 10);
      }
      localStringBuffer.append(str1);
      localStringBuffer.append("&pack=");
      localStringBuffer.append(c.bn);
      this.c5 = (com.baidu.location.b.k.ac() + "?&it=" + Jni.F(localStringBuffer.toString()));
      return;
      localStringBuffer.append("&cu=");
      localStringBuffer.append(c.N().bm);
    }
  }

  public void jdMethod_int(boolean paramBoolean)
  {
    if (paramBoolean);
    try
    {
      JSONObject localJSONObject = new JSONObject(EntityUtils.toString(this.c6, "utf-8"));
      if ("up".equals(localJSONObject.getString("res")))
      {
        this.eE = localJSONObject.getString("upath");
        if (localJSONObject.has("u1"))
          this.eD = localJSONObject.getString("u1");
        if (localJSONObject.has("u2"))
          this.eC = localJSONObject.getString("u2");
        if (localJSONObject.has("u1_md5"))
          this.eB = localJSONObject.getString("u1_md5");
        aN().post(new q(this));
      }
      if (localJSONObject.has("ison"))
        this.ez = localJSONObject.getInt("ison");
      aO();
      label153: n.jdMethod_if().jdMethod_for(System.currentTimeMillis());
      return;
    }
    catch (Exception localException)
    {
      break label153;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.e.d
 * JD-Core Version:    0.6.2
 */