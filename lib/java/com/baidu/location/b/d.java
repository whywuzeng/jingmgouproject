package com.baidu.location.b;

import com.baidu.location.h.c;
import com.baidu.location.h.j;
import java.io.File;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.net.InetAddress;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class d
  implements f
{
  private static d bw = null;
  private String bA = "loc.map.baidu.com";
  private String br = null;
  private long bs = 0L;
  private a bt = new a();
  private long bu = 0L;
  private String bv = null;
  private int bx = 0;
  private long by = 0L;
  private String bz = "dns.map.baidu.com";

  private d()
  {
    T();
  }

  private void Q()
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
        ((RandomAccessFile)localObject1).seek(800L);
        localObject2 = R();
        if (localObject2 != null)
        {
          ((RandomAccessFile)localObject1).writeBoolean(true);
          localObject2 = ((String)localObject2).getBytes();
          ((RandomAccessFile)localObject1).writeInt(localObject2.length);
          ((RandomAccessFile)localObject1).write((byte[])localObject2);
        }
        while (true)
        {
          ((RandomAccessFile)localObject1).close();
          return;
          ((RandomAccessFile)localObject1).writeBoolean(false);
        }
      }
    }
    catch (Exception localException)
    {
    }
  }

  private String R()
  {
    try
    {
      Object localObject = new JSONObject();
      if (this.bv != null)
        ((JSONObject)localObject).put("dnsServer", this.bv);
      if (this.br != null)
        ((JSONObject)localObject).put("locServer", this.br);
      if (this.bA != null)
        ((JSONObject)localObject).put("address", this.bA);
      if (this.bz != null)
        ((JSONObject)localObject).put("dnsServerIp", this.bz);
      ((JSONObject)localObject).put("DnsProxyTime", this.bs);
      ((JSONObject)localObject).put("DnsExtraTime", this.bu);
      ((JSONObject)localObject).put("DnsExtraUpdateTime", this.by);
      ((JSONObject)localObject).put("enable", this.bx);
      localObject = ((JSONObject)localObject).toString();
      return localObject;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  private void T()
  {
    Object localObject = e.int + "/grtcf.dat";
    try
    {
      localObject = new File((String)localObject);
      if (((File)localObject).exists())
      {
        localObject = new RandomAccessFile((File)localObject, "rw");
        ((RandomAccessFile)localObject).seek(800L);
        if (((RandomAccessFile)localObject).readBoolean())
        {
          int i = ((RandomAccessFile)localObject).readInt();
          byte[] arrayOfByte = new byte[i];
          ((RandomAccessFile)localObject).read(arrayOfByte, 0, i);
          jdMethod_case(new String(arrayOfByte));
        }
        ((RandomAccessFile)localObject).close();
      }
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public static d U()
  {
    if (bw == null)
      bw = new d();
    return bw;
  }

  private void jdMethod_case(String paramString)
  {
    try
    {
      paramString = new JSONObject(paramString);
      if (paramString.has("dnsServer"))
        this.bv = paramString.getString("dnsServer");
      if (paramString.has("locServer"))
        this.br = paramString.getString("locServer");
      if (paramString.has("address"))
        this.bA = paramString.getString("address");
      if (paramString.has("locServer"))
        this.bz = paramString.getString("dnsServerIp");
      if (paramString.has("DnsProxyTime"))
        this.bs = paramString.getLong("DnsProxyTime");
      if (paramString.has("DnsExtraTime"))
        this.bu = paramString.getLong("DnsExtraTime");
      if (paramString.has("DnsExtraUpdateTime"))
        this.by = paramString.getLong("DnsExtraUpdateTime");
      if (paramString.has("enable"))
        this.bx = paramString.getInt("enable");
      return;
    }
    catch (Exception paramString)
    {
    }
  }

  public String S()
  {
    String str2 = "loc.map.baidu.com";
    String str1 = str2;
    if (this.bx == 1)
    {
      str1 = str2;
      if (System.currentTimeMillis() - this.bs < 360000L)
        str1 = this.bA;
    }
    if (System.currentTimeMillis() - this.bs > 300000L)
      this.bt.a();
    return str1;
  }

  public String V()
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (System.currentTimeMillis() - this.bu > 3600000L)
    {
      localObject1 = localObject2;
      if (System.currentTimeMillis() - this.by < 43200000L)
      {
        localObject1 = localObject2;
        if (this.bv != null)
        {
          localObject1 = localObject2;
          if (this.br != null)
          {
            localObject1 = "&dsvr=" + this.bv + "&lsvr=" + this.br;
            this.bu = System.currentTimeMillis();
            Q();
          }
        }
      }
    }
    return localObject1;
  }

  private class a extends m
  {
    private boolean c = false;

    public a()
    {
    }

    void a()
    {
      if (this.c)
        return;
      this.c = true;
      as();
    }

    public void au()
    {
      if ((d.jdMethod_for(d.this).equals("dns.map.baidu.com")) || (System.currentTimeMillis() - d.jdMethod_do(d.this) > 720000L))
        switch (c.a().cS())
        {
        default:
          d.jdMethod_int(d.this, "dns.map.baidu.com");
        case 1:
        case 3:
        case 2:
        }
      while (true)
      {
        this.c5 = ("http://" + d.jdMethod_for(d.this) + ":80/remotedns?pid=lbs-geolocation");
        return;
        d.jdMethod_int(d.this, "111.13.100.247");
        continue;
        d.jdMethod_int(d.this, "180.97.33.196");
        continue;
        d.jdMethod_int(d.this, "111.206.37.190");
      }
    }

    void b()
    {
      if (System.currentTimeMillis() - d.jdMethod_if(d.this) < 1200000L)
        d.jdMethod_int(d.this);
      while (true)
      {
        return;
        try
        {
          str = (String)Class.forName("android.os.SystemProperties").getMethod("get", new Class[] { String.class }).invoke(null, new Object[] { "net.dns1" });
        }
        catch (Exception localException1)
        {
          try
          {
            while (true)
            {
              String str;
              InetAddress localInetAddress = InetAddress.getByName("loc.map.baidu.com");
              if ((localInetAddress == null) || (localInetAddress.getHostAddress() == null) || (str == null) || ("".equals(str)))
                break;
              d.jdMethod_do(d.this, str);
              d.jdMethod_if(d.this, localInetAddress.getHostAddress());
              d.jdMethod_do(d.this, System.currentTimeMillis());
              d.jdMethod_int(d.this);
              return;
              localException1 = localException1;
              Object localObject1 = null;
            }
          }
          catch (Exception localException2)
          {
            while (true)
              Object localObject2 = null;
          }
        }
      }
    }

    public void jdMethod_int(boolean paramBoolean)
    {
      String str = null;
      if ((paramBoolean) && (this.c6 != null));
      try
      {
        JSONObject localJSONObject = new JSONObject(EntityUtils.toString(this.c6, "utf-8"));
        if ((localJSONObject.getInt("errno") == 0) && (localJSONObject.has("data")))
        {
          Object localObject3 = localJSONObject.getJSONArray("data");
          Object localObject1 = ((JSONArray)localObject3).getJSONObject(0);
          localObject3 = ((JSONArray)localObject3).getJSONObject(1);
          if (((JSONObject)localObject1).has("loc.map.baidu.com"))
          {
            localObject1 = ((JSONObject)localObject1).getJSONArray("loc.map.baidu.com").getJSONObject(0).getString("ip");
            if (((JSONObject)localObject3).has("dns.map.baidu.com"))
              str = ((JSONObject)localObject3).getJSONArray("dns.map.baidu.com").getJSONObject(0).getString("ip");
            if ((localObject1 != null) && (str != null))
            {
              d.jdMethod_int(d.this, str);
              d.jdMethod_for(d.this, (String)localObject1);
            }
            if (localJSONObject.has("switch"))
              d.jdMethod_if(d.this, localJSONObject.getInt("switch"));
            d.jdMethod_if(d.this, System.currentTimeMillis());
            b();
          }
        }
        else
        {
          this.c = false;
          return;
        }
      }
      catch (Exception localException)
      {
        while (true)
        {
          continue;
          Object localObject2 = null;
        }
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.b.d
 * JD-Core Version:    0.6.2
 */