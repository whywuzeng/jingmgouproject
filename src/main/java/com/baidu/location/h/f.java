package com.baidu.location.h;

import android.net.wifi.ScanResult;
import android.os.Build.VERSION;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class f
  implements com.baidu.location.b.f
{
  private static final long kj = 10L;
  private long kk = 0L;
  private boolean kl = false;
  public List km = null;
  private long kn = 0L;
  private boolean ko;

  public f(f paramf)
  {
    if (paramf != null)
    {
      this.km = paramf.km;
      this.kk = paramf.kk;
      this.kn = paramf.kn;
      this.kl = paramf.kl;
    }
  }

  public f(List paramList, long paramLong)
  {
    this.kk = paramLong;
    this.km = paramList;
    this.kn = System.currentTimeMillis();
    di();
  }

  private void di()
  {
    if (dn() < 1)
      return;
    int j = this.km.size() - 1;
    int i = 1;
    label23: int k;
    if ((j >= 1) && (i != 0))
    {
      k = 0;
      i = 0;
      label36: if (k < j)
      {
        if (((ScanResult)this.km.get(k)).level >= ((ScanResult)this.km.get(k + 1)).level)
          break label147;
        ScanResult localScanResult = (ScanResult)this.km.get(k + 1);
        this.km.set(k + 1, this.km.get(k));
        this.km.set(k, localScanResult);
        i = 1;
      }
    }
    label147: 
    while (true)
    {
      k += 1;
      break label36;
      j -= 1;
      break label23;
      break;
    }
  }

  public static String jdMethod_if(int paramInt, List paramList)
  {
    if (paramList.size() < 1)
      return null;
    StringBuffer localStringBuffer = new StringBuffer(512);
    int i = paramList.size();
    if (i > paramInt)
      i = paramInt;
    while (true)
    {
      int j = 0;
      paramInt = 1;
      while (j < i)
        if (((ScanResult)paramList.get(j)).level == 0)
        {
          j += 1;
        }
        else
        {
          if (paramInt != 0)
            paramInt = 0;
          while (true)
          {
            localStringBuffer.append(((ScanResult)paramList.get(j)).BSSID.replace(":", ""));
            int m = ((ScanResult)paramList.get(j)).level;
            int k = m;
            if (m < 0)
              k = -m;
            localStringBuffer.append(String.format(Locale.CHINA, ";%d;", new Object[] { Integer.valueOf(k) }));
            break;
            localStringBuffer.append("|");
          }
        }
      if (paramInt == 0)
        return localStringBuffer.toString();
      return null;
    }
  }

  public String d(int paramInt)
  {
    if (dn() < 1)
      return null;
    StringBuffer localStringBuffer = new StringBuffer(512);
    int i = this.km.size();
    if (i > paramInt)
      i = paramInt;
    while (true)
    {
      int j = 0;
      paramInt = 1;
      while (j < i)
        if (((ScanResult)this.km.get(j)).level == 0)
        {
          j += 1;
        }
        else
        {
          if (paramInt != 0)
            paramInt = 0;
          while (true)
          {
            localStringBuffer.append(((ScanResult)this.km.get(j)).BSSID.replace(":", ""));
            int m = ((ScanResult)this.km.get(j)).level;
            int k = m;
            if (m < 0)
              k = -m;
            localStringBuffer.append(String.format(Locale.CHINA, ";%d;", new Object[] { Integer.valueOf(k) }));
            break;
            localStringBuffer.append("|");
          }
        }
      if (paramInt == 0)
        return localStringBuffer.toString();
      return null;
    }
  }

  public int dh()
  {
    int k = 0;
    int i = 0;
    while (true)
    {
      int j = k;
      if (i < dn())
      {
        j = -((ScanResult)this.km.get(i)).level;
        if (j <= 0);
      }
      else
      {
        return j;
      }
      i += 1;
    }
  }

  public boolean dj()
  {
    return this.kl;
  }

  public String dk()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("wifi=");
    if (this.km == null)
      return localStringBuilder.toString();
    int i = 0;
    while (i < this.km.size())
    {
      int j = ((ScanResult)this.km.get(i)).level;
      localStringBuilder.append(((ScanResult)this.km.get(i)).BSSID.replace(":", ""));
      localStringBuilder.append(String.format(Locale.CHINA, ",%d;", new Object[] { Integer.valueOf(j) }));
      i += 1;
    }
    return localStringBuilder.toString();
  }

  public boolean dl()
  {
    return System.currentTimeMillis() - this.kk < 3000L;
  }

  public String dm()
  {
    try
    {
      String str = e(15);
      return str;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public int dn()
  {
    if (this.km == null)
      return 0;
    return this.km.size();
  }

  public String jdMethod_do(int paramInt, boolean paramBoolean)
  {
    if (dn() < 1)
      return null;
    int m = 0;
    Object localObject2 = new Random();
    StringBuffer localStringBuffer = new StringBuffer(512);
    Object localObject1 = new ArrayList();
    Object localObject3 = l.a().c9();
    int j = 0;
    int i = j;
    if (Build.VERSION.SDK_INT >= 17);
    label898: label901: 
    while (true)
      try
      {
        l = SystemClock.elapsedRealtimeNanos() / 1000L;
        i = j;
        if (l > 0L)
          i = 1;
        if ((i != 0) && (i != 0) && (paramBoolean));
        k = 0;
        i = 0;
        int n = this.km.size();
        j = 1;
        if (n <= paramInt)
          break label901;
        n = paramInt;
        i1 = 0;
        paramInt = m;
        if (i1 < n)
          if (((ScanResult)this.km.get(i1)).level == 0)
          {
            m = j;
            j = paramInt;
            paramInt = i;
            i = m;
            i1 += 1;
            m = paramInt;
            paramInt = j;
            j = i;
            i = m;
            continue;
          }
      }
      catch (Error localError)
      {
        int k;
        int i1;
        long l = 0L;
        continue;
        int i2;
        if (j != 0)
        {
          j = 0;
          localStringBuffer.append("&wf=");
          String str = ((ScanResult)this.km.get(i1)).BSSID.replace(":", "");
          localStringBuffer.append(str);
          i2 = ((ScanResult)this.km.get(i1)).level;
          m = i2;
          if (i2 < 0)
            m = -i2;
          localStringBuffer.append(String.format(Locale.CHINA, ";%d;", new Object[] { Integer.valueOf(m) }));
          m = k + 1;
          k = i;
          if (localObject3 != null)
          {
            k = i;
            if (((String)localObject3).equals(str))
            {
              this.ko = l.a().D(((ScanResult)this.km.get(i1)).capabilities);
              l.a().dc();
              k = m;
            }
          }
          if (paramInt != 0)
            continue;
        }
        try
        {
          if ((((Random)localObject2).nextInt(10) != 2) || (((ScanResult)this.km.get(i1)).SSID == null) || (((ScanResult)this.km.get(i1)).SSID.length() >= 30))
            break label898;
          localStringBuffer.append(((ScanResult)this.km.get(i1)).SSID);
          paramInt = 1;
          i2 = paramInt;
          i = j;
          paramInt = k;
          k = m;
          j = i2;
          continue;
          localStringBuffer.append("|");
          continue;
          if ((paramInt != 1) || (((Random)localObject2).nextInt(20) != 1) || (((ScanResult)this.km.get(i1)).SSID == null) || (((ScanResult)this.km.get(i1)).SSID.length() >= 30))
            break label898;
          localStringBuffer.append(((ScanResult)this.km.get(i1)).SSID);
          paramInt = 2;
          continue;
        }
        catch (Exception localException)
        {
          i = j;
          j = paramInt;
          paramInt = k;
          k = m;
        }
        continue;
        if (j == 0)
        {
          localStringBuffer.append("&wf_n=" + i);
          if ((0L > 10L) && (((List)localObject1).size() > 0))
          {
            localObject2 = new StringBuffer(128);
            ((StringBuffer)localObject2).append("&wf_ut=");
            localObject3 = (Long)((List)localObject1).get(0);
            localObject1 = ((List)localObject1).iterator();
            paramInt = 1;
            if (((Iterator)localObject1).hasNext())
            {
              Long localLong = (Long)((Iterator)localObject1).next();
              if (paramInt != 0)
              {
                ((StringBuffer)localObject2).append(localLong.longValue());
                paramInt = 0;
                ((StringBuffer)localObject2).append("|");
                continue;
              }
              l = localLong.longValue() - ((Long)localObject3).longValue();
              if (l != 0L)
                ((StringBuffer)localObject2).append("" + l);
              continue;
            }
            localStringBuffer.append(((StringBuffer)localObject2).toString());
          }
          localStringBuffer.append("&wf_st=");
          localStringBuffer.append(this.kk);
          localStringBuffer.append("&wf_et=");
          localStringBuffer.append(this.kn);
          localStringBuffer.append("&wf_vt=");
          localStringBuffer.append(e.j7);
          if (i > 0)
          {
            this.kl = true;
            localStringBuffer.append("&wf_en=");
            if (this.ko)
            {
              paramInt = 1;
              localStringBuffer.append(paramInt);
            }
          }
          else
          {
            return localStringBuffer.toString();
          }
          paramInt = 0;
          continue;
        }
        return null;
      }
  }

  public boolean dp()
  {
    return System.currentTimeMillis() - this.kn < 5000L;
  }

  public String dq()
  {
    StringBuffer localStringBuffer = new StringBuffer(512);
    localStringBuffer.append("wifi info:");
    if (dn() < 1)
      return localStringBuffer.toString();
    int i = this.km.size();
    int j = i;
    if (i > 10)
      j = 10;
    int k = 0;
    i = 1;
    if (k < j)
    {
      if (((ScanResult)this.km.get(k)).level == 0);
      while (true)
      {
        k += 1;
        break;
        if (i != 0)
        {
          localStringBuffer.append("wifi=");
          localStringBuffer.append(((ScanResult)this.km.get(k)).BSSID.replace(":", ""));
          i = ((ScanResult)this.km.get(k)).level;
          localStringBuffer.append(String.format(Locale.CHINA, ";%d;", new Object[] { Integer.valueOf(i) }));
          i = 0;
        }
        else
        {
          localStringBuffer.append(";");
          localStringBuffer.append(((ScanResult)this.km.get(k)).BSSID.replace(":", ""));
          int m = ((ScanResult)this.km.get(k)).level;
          localStringBuffer.append(String.format(Locale.CHINA, ",%d;", new Object[] { Integer.valueOf(m) }));
        }
      }
    }
    return localStringBuffer.toString();
  }

  public String dr()
  {
    try
    {
      String str = jdMethod_do(com.baidu.location.b.k.cD, true);
      return str;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public boolean ds()
  {
    return System.currentTimeMillis() - this.kn < 3000L;
  }

  public String e(int paramInt)
  {
    return jdMethod_do(paramInt, false);
  }

  public String h(int paramInt)
  {
    StringBuffer localStringBuffer = new StringBuffer(512);
    if (dn() < 1)
      return localStringBuffer.toString();
    int i = this.km.size();
    if (i > paramInt)
      i = paramInt;
    while (true)
    {
      int j = 0;
      paramInt = 1;
      if (j < i)
      {
        if (((ScanResult)this.km.get(j)).level == 0);
        while (true)
        {
          j += 1;
          break;
          if (paramInt != 0)
          {
            localStringBuffer.append(((ScanResult)this.km.get(j)).BSSID.replace(":", ""));
            paramInt = -((ScanResult)this.km.get(j)).level;
            localStringBuffer.append(String.format(Locale.CHINA, ";%d|", new Object[] { Integer.valueOf(paramInt) }));
            paramInt = 0;
          }
          else
          {
            localStringBuffer.append(((ScanResult)this.km.get(j)).BSSID.replace(":", ""));
            int k = -((ScanResult)this.km.get(j)).level;
            localStringBuffer.append(String.format(Locale.CHINA, ";%d|", new Object[] { Integer.valueOf(k) }));
          }
        }
      }
      return localStringBuffer.toString();
    }
  }

  public String i(int paramInt)
  {
    int i = 0;
    try
    {
      if (dn() < 1)
        return null;
      Object localObject = new StringBuffer(512);
      int j = this.km.size();
      if (j > paramInt);
      while (true)
      {
        if (i < paramInt)
        {
          if (((ScanResult)this.km.get(i)).level != 0)
            ((StringBuffer)localObject).append(String.format(Locale.CHINA, "<access-point>\n<mac>%s</mac>\n<signal-strength>%d</signal-strength>\n</access-point>\n", new Object[] { ((ScanResult)this.km.get(i)).BSSID.replace(":", ""), Integer.valueOf(((ScanResult)this.km.get(i)).level) }));
        }
        else
        {
          localObject = ((StringBuffer)localObject).toString();
          return localObject;
          paramInt = j;
          continue;
        }
        i += 1;
      }
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public boolean jdMethod_int(f paramf)
  {
    if ((this.km != null) && (paramf != null) && (paramf.km != null))
    {
      int i;
      int j;
      if (this.km.size() < paramf.km.size())
      {
        i = this.km.size();
        j = 0;
      }
      while (true)
      {
        if (j >= i)
          break label167;
        String str1 = ((ScanResult)this.km.get(j)).BSSID;
        int k = ((ScanResult)this.km.get(j)).level;
        String str2 = ((ScanResult)paramf.km.get(j)).BSSID;
        int m = ((ScanResult)paramf.km.get(j)).level;
        if ((!str1.equals(str2)) || (k != m))
        {
          return false;
          i = paramf.km.size();
          break;
        }
        j += 1;
      }
      label167: return true;
    }
    return false;
  }

  public String j(int paramInt)
  {
    if ((paramInt == 0) || (dn() < 1))
      return null;
    StringBuffer localStringBuffer = new StringBuffer(256);
    if (this.km.size() > com.baidu.location.b.k.cD)
      i = com.baidu.location.b.k.cD;
    int k = 0;
    int i = 1;
    int j = 0;
    if (j < com.baidu.location.b.k.cD)
    {
      int m = k;
      if ((i & paramInt) != 0)
      {
        if (k != 0)
          break label163;
        localStringBuffer.append("&ssid=");
      }
      while (true)
      {
        localStringBuffer.append(((ScanResult)this.km.get(j)).BSSID.replace(":", ""));
        localStringBuffer.append(";");
        localStringBuffer.append(((ScanResult)this.km.get(j)).SSID);
        m = k + 1;
        i <<= 1;
        j += 1;
        k = m;
        break;
        label163: localStringBuffer.append("|");
      }
    }
    return localStringBuffer.toString();
  }

  public boolean jdMethod_new(f paramf)
  {
    if ((this.km != null) && (paramf != null) && (paramf.km != null))
    {
      int i;
      int j;
      if (this.km.size() < paramf.km.size())
      {
        i = this.km.size();
        j = 0;
      }
      while (true)
      {
        if (j >= i)
          break label116;
        if (!((ScanResult)this.km.get(j)).BSSID.equals(((ScanResult)paramf.km.get(j)).BSSID))
        {
          return false;
          i = paramf.km.size();
          break;
        }
        j += 1;
      }
      label116: return true;
    }
    return false;
  }

  public boolean jdMethod_try(f paramf)
  {
    return e.jdMethod_if(paramf, this, com.baidu.location.b.k.bF);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.h.f
 * JD-Core Version:    0.6.2
 */