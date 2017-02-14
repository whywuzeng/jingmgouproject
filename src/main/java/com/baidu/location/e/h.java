package com.baidu.location.e;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Environment;
import android.os.Handler;
import com.baidu.location.Jni;
import com.baidu.location.b.m;
import com.baidu.location.h.j;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class h
  implements com.baidu.location.b.f
{
  private static final String g1 = "1";
  private static final String g5 = "%d_%02d_%02d";
  private static final long gL = 86400000L;
  private static final String gV = "utf-8";
  private static final int gX = 400;
  private static final String hg = "http://loc.map.baidu.com/cc.php";
  private static final String hh = "0";
  private static final int hq = 10000;
  private static h hr = null;
  public static String ht = "0";
  private long g0 = 0L;
  private int g2 = 0;
  private int g3 = 1;
  private int g4 = 307200;
  StringBuilder g6 = null;
  private float g7 = 0.0F;
  private double g8 = 3.0D;
  Location g9 = null;
  private Handler gJ = null;
  private int gK = 15;
  private String gM = "3G|4G";
  private double gN = 3.5D;
  private float gO = 0.0F;
  private int gP = 60;
  private boolean gQ = false;
  private double gR = 0.699999988079071D;
  private int gS = 500;
  private int gT = 0;
  private boolean gU = false;
  private byte[] gW = null;
  private a gY = null;
  private int gZ = 1;
  long ha = 0L;
  private int hb = 0;
  private double hc = 0.5D;
  private List hd = null;
  long he = 0L;
  double hf = 116.22345545D;
  private boolean hi = false;
  private int hj = 0;
  private int hk = 60;
  private int hl = 1;
  private long hm = 0L;
  double hn = 40.245667322999999D;
  int ho = 0;
  private byte[] hp = new byte[4];
  Location hs = null;
  private int hu = 300;

  public static h bK()
  {
    if (hr == null)
      hr = new h();
    return hr;
  }

  private void bN()
  {
    this.hd = null;
    this.ha = 0L;
    this.hj = 0;
    this.g9 = null;
    this.hs = null;
    this.g7 = 0.0F;
    this.gO = 0.0F;
  }

  private void bO()
  {
  }

  private void bP()
  {
    int i = 0;
    this.hd.add(Byte.valueOf((byte)0));
    this.hd.add(Byte.valueOf((byte)0));
    if (ht.equals("0"))
      this.hd.add(Byte.valueOf((byte)110));
    while (true)
    {
      this.hd.add(Byte.valueOf((byte)0));
      this.hd.add(Byte.valueOf(this.hp[0]));
      this.hd.add(Byte.valueOf(this.hp[1]));
      this.hd.add(Byte.valueOf(this.hp[2]));
      this.hd.add(Byte.valueOf(this.hp[3]));
      int j = this.gW.length;
      this.hd.add(Byte.valueOf((byte)(j + 1 & 0xFF)));
      while (i < j)
      {
        this.hd.add(Byte.valueOf(this.gW[i]));
        i += 1;
      }
      this.hd.add(Byte.valueOf((byte)126));
    }
  }

  private void bQ()
  {
    Object localObject = null;
    if (0 == 0)
      localObject = "6.1.2";
    localObject = ((String)localObject).split("\\.");
    int j = localObject.length;
    this.hp[0] = 0;
    this.hp[1] = 0;
    this.hp[2] = 0;
    this.hp[3] = 0;
    int i = j;
    if (j >= 4)
      i = 4;
    j = 0;
    while (j < i)
      try
      {
        this.hp[j] = ((byte)(Integer.valueOf(localObject[j]).intValue() & 0xFF));
        j += 1;
      }
      catch (Exception localException)
      {
      }
    this.gW = q(com.baidu.location.b.c.bn + ":" + com.baidu.location.b.c.N().bm);
  }

  private void bR()
  {
    if (this.hi)
      return;
    this.hi = true;
    r(com.baidu.location.b.c.bn);
    bU();
    bQ();
  }

  private boolean bS()
  {
    if (this.gQ)
      if (this.gU)
        if (this.g7 < this.hc)
        {
          this.g2 += this.gK;
          if ((this.g2 > this.hu) && (System.currentTimeMillis() - this.g0 <= this.gP * 1000))
            break label186;
        }
    do
    {
      do
      {
        return true;
        this.g2 = 0;
        this.gU = false;
        return true;
      }
      while (this.g7 >= this.hc);
      this.gU = true;
      this.g2 = 0;
      this.g2 += this.gK;
      return true;
      if ((this.g7 >= this.gN) || (this.gO >= this.g8))
      {
        this.gQ = true;
        return true;
      }
    }
    while ((this.gT == 1) && (System.currentTimeMillis() - this.g0 > this.hk * 1000));
    label186: return false;
  }

  private void bT()
  {
    if ((this.ha == 0L) || (System.currentTimeMillis() - this.ha < this.gK * 1000));
    do
    {
      return;
      if (com.baidu.location.f.getServiceContext().getSharedPreferences("loc_navi_mode", 4).getBoolean("is_navi_on", false))
      {
        bN();
        return;
      }
      if ((this.g3 == 1) && (!bS()))
      {
        bN();
        return;
      }
      if (!jdMethod_if(com.baidu.location.b.c.bn, com.baidu.location.f.getServiceContext()))
      {
        bN();
        return;
      }
    }
    while (this.hd == null);
    int j = this.hd.size();
    this.hd.set(0, Byte.valueOf((byte)(j & 0xFF)));
    this.hd.set(1, Byte.valueOf((byte)((0xFF00 & j) >> 8)));
    this.hd.set(3, Byte.valueOf((byte)(this.hj & 0xFF)));
    byte[] arrayOfByte = new byte[j];
    int i = 0;
    while (i < j)
    {
      arrayOfByte[i] = ((Byte)this.hd.get(i)).byteValue();
      i += 1;
    }
    Object localObject;
    if (Environment.getExternalStorageState().equals("mounted"))
    {
      localObject = new File(Environment.getExternalStorageDirectory(), "baidu/tempdata");
      if (!((File)localObject).exists())
        ((File)localObject).mkdirs();
      if (((File)localObject).exists())
      {
        localObject = new File((File)localObject, "intime.dat");
        if (((File)localObject).exists())
          ((File)localObject).delete();
      }
    }
    try
    {
      localObject = new BufferedOutputStream(new FileOutputStream((File)localObject));
      ((BufferedOutputStream)localObject).write(arrayOfByte);
      ((BufferedOutputStream)localObject).flush();
      ((BufferedOutputStream)localObject).close();
      new t(this).start();
      label338: bN();
      this.g0 = System.currentTimeMillis();
      return;
    }
    catch (Exception localException)
    {
      break label338;
    }
  }

  private void bU()
  {
    if (System.currentTimeMillis() - this.hm > 86400000L)
    {
      if (this.gY == null)
        this.gY = new a();
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append(com.baidu.location.b.c.N().jdMethod_do(false));
      localStringBuffer.append(c.br().bu());
      this.gY.a(localStringBuffer.toString());
    }
    bO();
  }

  private void jdMethod_do(Location paramLocation)
  {
    jdMethod_new(paramLocation);
    bT();
  }

  private byte[] jdMethod_for(int paramInt)
  {
    return new byte[] { (byte)(paramInt & 0xFF), (byte)((0xFF00 & paramInt) >> 8), (byte)((0xFF0000 & paramInt) >> 16), (byte)((0xFF000000 & paramInt) >> 24) };
  }

  private String jdMethod_if(File paramFile, String paramString)
  {
    String str = UUID.randomUUID().toString();
    try
    {
      paramString = (HttpURLConnection)new URL(paramString).openConnection();
      paramString.setReadTimeout(10000);
      paramString.setConnectTimeout(10000);
      paramString.setDoInput(true);
      paramString.setDoOutput(true);
      paramString.setUseCaches(false);
      paramString.setRequestMethod("POST");
      paramString.setRequestProperty("Charset", "utf-8");
      paramString.setRequestProperty("connection", "keep-alive");
      paramString.setRequestProperty("Content-Type", "multipart/form-data" + ";boundary=" + str);
      if ((paramFile != null) && (paramFile.exists()))
      {
        localOutputStream = paramString.getOutputStream();
        localDataOutputStream = new DataOutputStream(localOutputStream);
        Object localObject = new StringBuffer();
        ((StringBuffer)localObject).append("--");
        ((StringBuffer)localObject).append(str);
        ((StringBuffer)localObject).append("\r\n");
        ((StringBuffer)localObject).append("Content-Disposition: form-data; name=\"location_dat\"; filename=\"" + paramFile.getName() + "\"" + "\r\n");
        ((StringBuffer)localObject).append("Content-Type: application/octet-stream; charset=utf-8" + "\r\n");
        ((StringBuffer)localObject).append("\r\n");
        localDataOutputStream.write(((StringBuffer)localObject).toString().getBytes());
        paramFile = new FileInputStream(paramFile);
        localObject = new byte[1024];
        while (true)
        {
          i = paramFile.read((byte[])localObject);
          if (i == -1)
            break;
          localDataOutputStream.write((byte[])localObject, 0, i);
        }
      }
    }
    catch (MalformedURLException paramFile)
    {
      int i;
      do
      {
        OutputStream localOutputStream;
        DataOutputStream localDataOutputStream;
        return "0";
        paramFile.close();
        localDataOutputStream.write("\r\n".getBytes());
        localDataOutputStream.write(("--" + str + "--" + "\r\n").getBytes());
        localDataOutputStream.flush();
        i = paramString.getResponseCode();
        localOutputStream.close();
        this.hb += 400;
        jdMethod_new(this.hb);
      }
      while (i != 200);
      return "1";
    }
    catch (IOException paramFile)
    {
      label307: break label307;
    }
  }

  private void jdMethod_if(Location paramLocation)
  {
    int j = 0;
    this.ha = System.currentTimeMillis();
    jdMethod_int((int)(this.ha / 1000L));
    jdMethod_int((int)(paramLocation.getLongitude() * 1000000.0D));
    jdMethod_int((int)(paramLocation.getLatitude() * 1000000.0D));
    int i;
    if (paramLocation.hasBearing())
    {
      i = 0;
      if (!paramLocation.hasSpeed())
        break label115;
      label65: if (i <= 0)
        break label121;
      this.hd.add(Byte.valueOf((byte)32));
      label84: if (j <= 0)
        break label157;
      this.hd.add(Byte.valueOf((byte)-128));
    }
    while (true)
    {
      this.g9 = paramLocation;
      return;
      i = 1;
      break;
      label115: j = 1;
      break label65;
      label121: byte b = (byte)((byte)((int)(paramLocation.getBearing() / 15.0F) & 0xFF) & 0xFFFFFFDF);
      this.hd.add(Byte.valueOf(b));
      break label84;
      label157: b = (byte)((byte)((int)(paramLocation.getSpeed() * 3.6D / 4.0D) & 0xFF) & 0x7F);
      this.hd.add(Byte.valueOf(b));
    }
  }

  private boolean jdMethod_if(String paramString, Context paramContext)
  {
    boolean bool4 = false;
    boolean bool3 = false;
    boolean bool1 = false;
    boolean bool2 = bool4;
    label127: 
    while (true)
    {
      try
      {
        paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
        if (paramContext != null)
        {
          bool2 = bool4;
          paramContext = paramContext.iterator();
          bool2 = bool1;
          bool3 = bool1;
          if (paramContext.hasNext())
          {
            bool2 = bool1;
            ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
            bool2 = bool1;
            if (!localRunningAppProcessInfo.processName.equals(paramString))
              break label127;
            bool2 = bool1;
            int i = localRunningAppProcessInfo.importance;
            if ((i != 200) && (i != 100))
              break label127;
            bool1 = true;
            continue;
          }
        }
      }
      catch (Exception paramString)
      {
        bool3 = bool2;
      }
      return bool3;
    }
  }

  private void jdMethod_int(int paramInt)
  {
    byte[] arrayOfByte = jdMethod_for(paramInt);
    paramInt = 0;
    while (paramInt < 4)
    {
      this.hd.add(Byte.valueOf(arrayOfByte[paramInt]));
      paramInt += 1;
    }
  }

  private void jdMethod_int(Location paramLocation)
  {
    int m = (int)((paramLocation.getLongitude() - this.g9.getLongitude()) * 100000.0D);
    int n = (int)((paramLocation.getLatitude() - this.g9.getLatitude()) * 100000.0D);
    int i;
    int j;
    label58: int k;
    label66: label81: byte b2;
    if (paramLocation.hasBearing())
    {
      i = 0;
      if (!paramLocation.hasSpeed())
        break label380;
      j = 0;
      if (m <= 0)
        break label386;
      k = 0;
      int i1 = Math.abs(m);
      if (n <= 0)
        break label392;
      m = 0;
      n = Math.abs(n);
      if (this.hj > 1)
      {
        this.hs = null;
        this.hs = this.g9;
      }
      this.g9 = paramLocation;
      if ((this.g9 != null) && (this.hs != null) && (this.g9.getTime() > this.hs.getTime()) && (this.g9.getTime() - this.hs.getTime() < 5000L))
      {
        long l = this.g9.getTime() - this.hs.getTime();
        float[] arrayOfFloat = new float[2];
        Location.distanceBetween(this.g9.getAltitude(), this.g9.getLongitude(), this.hs.getLatitude(), this.hs.getLongitude(), arrayOfFloat);
        double d = 2.0F * (arrayOfFloat[0] - this.hs.getSpeed() * (float)l) / (float)(l * l);
        if (d > this.gO)
          this.gO = ((float)d);
      }
      this.hd.add(Byte.valueOf((byte)(i1 & 0xFF)));
      this.hd.add(Byte.valueOf((byte)(n & 0xFF)));
      if (i <= 0)
        break label398;
      b1 = 32;
      if (m > 0)
        b1 = (byte)96;
      b2 = b1;
      if (k > 0)
        b2 = (byte)(b1 | 0xFFFFFF80);
      this.hd.add(Byte.valueOf(b2));
    }
    while (true)
    {
      if (j <= 0)
        break label460;
      this.hd.add(Byte.valueOf((byte)-128));
      return;
      i = 1;
      break;
      label380: j = 1;
      break label58;
      label386: k = 1;
      break label66;
      label392: m = 1;
      break label81;
      label398: b2 = (byte)((byte)((int)(paramLocation.getBearing() / 15.0F) & 0xFF) & 0x1F);
      b1 = b2;
      if (m > 0)
        b1 = (byte)(b2 | 0x40);
      b2 = b1;
      if (k > 0)
        b2 = (byte)(b1 | 0xFFFFFF80);
      this.hd.add(Byte.valueOf(b2));
    }
    label460: byte b1 = (byte)((byte)((int)(paramLocation.getSpeed() * 3.6D / 4.0D) & 0xFF) & 0x7F);
    this.hd.add(Byte.valueOf(b1));
  }

  private void jdMethod_new(int paramInt)
  {
    if (paramInt == 0);
    while (true)
    {
      return;
      Object localObject1 = com.baidu.location.b.e.int + "/grtcf.dat";
      try
      {
        localObject1 = new File((String)localObject1);
        Object localObject2;
        if (!((File)localObject1).exists())
        {
          localObject2 = new File(com.baidu.location.b.e.int);
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
          ((RandomAccessFile)localObject1).seek(8L);
          localObject2 = (o("%d_%02d_%02d") + ":" + paramInt).getBytes();
          ((RandomAccessFile)localObject1).writeInt(localObject2.length);
          ((RandomAccessFile)localObject1).write((byte[])localObject2);
          ((RandomAccessFile)localObject1).close();
          return;
        }
      }
      catch (Exception localException)
      {
      }
    }
  }

  private void jdMethod_new(Location paramLocation)
  {
    if ((System.currentTimeMillis() - this.he < this.gS) || (paramLocation == null))
      return;
    if ((paramLocation != null) && (paramLocation.hasSpeed()) && (paramLocation.getSpeed() > this.g7))
      this.g7 = paramLocation.getSpeed();
    try
    {
      if (this.hd == null)
      {
        this.hd = new ArrayList();
        bP();
        jdMethod_if(paramLocation);
      }
      while (true)
      {
        label80: this.hj += 1;
        return;
        jdMethod_int(paramLocation);
      }
    }
    catch (Exception paramLocation)
    {
      break label80;
    }
  }

  private String o(String paramString)
  {
    Calendar localCalendar = Calendar.getInstance();
    return String.format(paramString, new Object[] { Integer.valueOf(localCalendar.get(1)), Integer.valueOf(localCalendar.get(2) + 1), Integer.valueOf(localCalendar.get(5)) });
  }

  private void p(String paramString)
  {
    if (paramString != null);
    try
    {
      paramString = new JSONObject(paramString);
      if (paramString.has("on"))
        this.gZ = paramString.getInt("on");
      if (paramString.has("bash"))
        this.gR = paramString.getDouble("bash");
      if (paramString.has("net"))
        this.gM = paramString.getString("net");
      if (paramString.has("tcon"))
        this.g3 = paramString.getInt("tcon");
      if (paramString.has("tcsh"))
        this.g4 = paramString.getInt("tcsh");
      if (paramString.has("per"))
        this.gK = paramString.getInt("per");
      if (paramString.has("chdron"))
        this.hl = paramString.getInt("chdron");
      if (paramString.has("spsh"))
        this.gN = paramString.getDouble("spsh");
      if (paramString.has("acsh"))
        this.g8 = paramString.getDouble("acsh");
      if (paramString.has("stspsh"))
        this.hc = paramString.getDouble("stspsh");
      if (paramString.has("drstsh"))
        this.hu = paramString.getInt("drstsh");
      if (paramString.has("stper"))
        this.gP = paramString.getInt("stper");
      if (paramString.has("nondron"))
        this.gT = paramString.getInt("nondron");
      if (paramString.has("nondrper"))
        this.hk = paramString.getInt("nondrper");
      if (paramString.has("uptime"))
        this.hm = paramString.getLong("uptime");
      bO();
      return;
    }
    catch (JSONException paramString)
    {
    }
  }

  private byte[] q(String paramString)
  {
    int m = 0;
    if (paramString == null)
      return null;
    paramString = paramString.getBytes();
    int i = (byte)new Random().nextInt(255);
    int j = (byte)new Random().nextInt(255);
    byte[] arrayOfByte = new byte[paramString.length + 2];
    int n = paramString.length;
    int k = 0;
    while (m < n)
    {
      arrayOfByte[k] = ((byte)(paramString[m] ^ i));
      m += 1;
      k += 1;
    }
    m = k + 1;
    arrayOfByte[k] = i;
    arrayOfByte[m] = j;
    return arrayOfByte;
  }

  // ERROR //
  private void r(String paramString)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_3
    //   2: new 233	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 234	java/lang/StringBuilder:<init>	()V
    //   9: getstatic 628	com/baidu/location/b/e:int	Ljava/lang/String;
    //   12: invokevirtual 243	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   15: ldc_w 630
    //   18: invokevirtual 243	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   21: invokevirtual 256	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   24: astore 8
    //   26: new 334	java/io/File
    //   29: dup
    //   30: aload 8
    //   32: invokespecial 631	java/io/File:<init>	(Ljava/lang/String;)V
    //   35: astore 8
    //   37: aload 8
    //   39: invokevirtual 346	java/io/File:exists	()Z
    //   42: ifeq +215 -> 257
    //   45: new 636	java/io/RandomAccessFile
    //   48: dup
    //   49: aload 8
    //   51: ldc_w 638
    //   54: invokespecial 639	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   57: astore 8
    //   59: aload 8
    //   61: ldc2_w 640
    //   64: invokevirtual 645	java/io/RandomAccessFile:seek	(J)V
    //   67: aload 8
    //   69: invokevirtual 756	java/io/RandomAccessFile:readInt	()I
    //   72: istore 4
    //   74: aload 8
    //   76: ldc2_w 649
    //   79: invokevirtual 645	java/io/RandomAccessFile:seek	(J)V
    //   82: aload 8
    //   84: invokevirtual 756	java/io/RandomAccessFile:readInt	()I
    //   87: istore_2
    //   88: iload_2
    //   89: newarray byte
    //   91: astore 9
    //   93: aload 8
    //   95: aload 9
    //   97: iconst_0
    //   98: iload_2
    //   99: invokevirtual 759	java/io/RandomAccessFile:read	([BII)I
    //   102: pop
    //   103: new 208	java/lang/String
    //   106: dup
    //   107: aload 9
    //   109: invokespecial 761	java/lang/String:<init>	([B)V
    //   112: astore 9
    //   114: iload_3
    //   115: istore_2
    //   116: aload 9
    //   118: aload_0
    //   119: ldc 16
    //   121: invokespecial 665	com/baidu/location/e/h:o	(Ljava/lang/String;)Ljava/lang/String;
    //   124: invokevirtual 765	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   127: ifeq +53 -> 180
    //   130: aload 9
    //   132: ldc 245
    //   134: invokevirtual 765	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   137: istore 5
    //   139: iload_3
    //   140: istore_2
    //   141: iload 5
    //   143: ifeq +37 -> 180
    //   146: aload 9
    //   148: ldc 245
    //   150: invokevirtual 222	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   153: astore 9
    //   155: iload_3
    //   156: istore_2
    //   157: aload 9
    //   159: arraylength
    //   160: iconst_1
    //   161: if_icmple +19 -> 180
    //   164: aload_0
    //   165: aload 9
    //   167: iconst_1
    //   168: aaload
    //   169: invokestatic 227	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   172: invokevirtual 231	java/lang/Integer:intValue	()I
    //   175: putfield 135	com/baidu/location/e/h:hb	I
    //   178: iload_3
    //   179: istore_2
    //   180: iload_2
    //   181: iload 4
    //   183: if_icmpgt +69 -> 252
    //   186: iload_2
    //   187: sipush 2048
    //   190: imul
    //   191: i2l
    //   192: lstore 6
    //   194: aload 8
    //   196: lload 6
    //   198: invokevirtual 645	java/io/RandomAccessFile:seek	(J)V
    //   201: aload 8
    //   203: invokevirtual 756	java/io/RandomAccessFile:readInt	()I
    //   206: istore_3
    //   207: iload_3
    //   208: newarray byte
    //   210: astore 9
    //   212: aload 8
    //   214: aload 9
    //   216: iconst_0
    //   217: iload_3
    //   218: invokevirtual 759	java/io/RandomAccessFile:read	([BII)I
    //   221: pop
    //   222: new 208	java/lang/String
    //   225: dup
    //   226: aload 9
    //   228: invokespecial 761	java/lang/String:<init>	([B)V
    //   231: astore 9
    //   233: aload_1
    //   234: ifnull +24 -> 258
    //   237: aload 9
    //   239: aload_1
    //   240: invokevirtual 765	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   243: ifeq +15 -> 258
    //   246: aload_0
    //   247: aload 9
    //   249: invokespecial 767	com/baidu/location/e/h:p	(Ljava/lang/String;)V
    //   252: aload 8
    //   254: invokevirtual 661	java/io/RandomAccessFile:close	()V
    //   257: return
    //   258: iload_2
    //   259: iconst_1
    //   260: iadd
    //   261: istore_2
    //   262: goto -82 -> 180
    //   265: astore_1
    //   266: return
    //   267: astore 9
    //   269: iload_3
    //   270: istore_2
    //   271: goto -91 -> 180
    //
    // Exception table:
    //   from	to	target	type
    //   26	114	265	java/lang/Exception
    //   116	139	265	java/lang/Exception
    //   194	233	265	java/lang/Exception
    //   237	252	265	java/lang/Exception
    //   252	257	265	java/lang/Exception
    //   146	155	267	java/lang/Exception
    //   157	178	267	java/lang/Exception
  }

  private void s(String paramString)
  {
    Object localObject1 = com.baidu.location.b.e.int + "/grtcf.dat";
    try
    {
      localObject1 = new File((String)localObject1);
      Object localObject2;
      int j;
      int i;
      if (!((File)localObject1).exists())
      {
        localObject2 = new File(com.baidu.location.b.e.int);
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
        ((RandomAccessFile)localObject1).seek(2L);
        j = ((RandomAccessFile)localObject1).readInt();
        i = 1;
      }
      while (true)
      {
        if (i <= j)
        {
          ((RandomAccessFile)localObject1).seek(i * 2048);
          int k = ((RandomAccessFile)localObject1).readInt();
          localObject2 = new byte[k];
          ((RandomAccessFile)localObject1).read((byte[])localObject2, 0, k);
          if (!new String((byte[])localObject2).contains(com.baidu.location.b.c.bn));
        }
        else
        {
          if (i >= j)
          {
            ((RandomAccessFile)localObject1).seek(2L);
            ((RandomAccessFile)localObject1).writeInt(i);
          }
          ((RandomAccessFile)localObject1).seek(i * 2048);
          paramString = paramString.getBytes();
          ((RandomAccessFile)localObject1).writeInt(paramString.length);
          ((RandomAccessFile)localObject1).write(paramString);
          ((RandomAccessFile)localObject1).close();
          return;
        }
        i += 1;
      }
    }
    catch (Exception paramString)
    {
    }
  }

  public void bL()
  {
    if (!this.hi)
      return;
    this.hi = false;
    bN();
  }

  public void bM()
  {
    this.gJ.postDelayed(new u(this), 2000L);
  }

  public void jdMethod_for(Location paramLocation)
  {
    if (!this.hi)
      bR();
    if ((this.gZ != 1) || (e.bx().by() >= this.gR * 100.0D) || (!this.gM.contains(com.baidu.location.h.c.a(com.baidu.location.h.c.a().cR()))) || ((this.g3 == 1) && (this.hb > this.g4)))
      return;
    this.gJ.post(new s(this, paramLocation));
  }

  class a extends m
  {
    String b = null;

    public a()
    {
      this.c7 = new ArrayList();
    }

    public void a(String paramString)
    {
      this.b = paramString;
      ao();
    }

    public void au()
    {
      this.c5 = "http://loc.map.baidu.com/cc.php";
      String str = Jni.H(this.b);
      this.b = null;
      this.c7.add(new BasicNameValuePair("q", str));
    }

    public void jdMethod_int(boolean paramBoolean)
    {
      if ((paramBoolean) && (this.c6 != null));
      try
      {
        JSONObject localJSONObject = new JSONObject(EntityUtils.toString(this.c6, "utf-8"));
        localJSONObject.put("prod", com.baidu.location.b.c.bn);
        localJSONObject.put("uptime", System.currentTimeMillis());
        h.jdMethod_if(h.this, localJSONObject.toString());
        label59: if (this.c7 != null)
          this.c7.clear();
        return;
      }
      catch (Exception localException)
      {
        break label59;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.e.h
 * JD-Core Version:    0.6.2
 */