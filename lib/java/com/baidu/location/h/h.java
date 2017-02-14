package com.baidu.location.h;

import com.baidu.location.b.f;
import java.util.Locale;

public class h
  implements f
{
  public int kp = -1;
  private boolean kq = false;
  public long kr = 0L;
  public int ks = 2147483647;
  public int kt = -1;
  public int ku = -1;
  public char kv = '\000';
  public int kw = -1;
  public int kx = -1;
  public int ky = 2147483647;

  public h()
  {
    this.kr = System.currentTimeMillis();
  }

  public h(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, char paramChar)
  {
    this.kp = paramInt1;
    this.kt = paramInt2;
    this.kw = paramInt3;
    this.kx = paramInt4;
    this.ku = paramInt5;
    this.kv = paramChar;
    this.kr = System.currentTimeMillis();
  }

  public h(h paramh)
  {
    this(paramh.kp, paramh.kt, paramh.kw, paramh.kx, paramh.ku, paramh.kv);
  }

  public boolean jdMethod_case(h paramh)
  {
    return (this.kp == paramh.kp) && (this.kt == paramh.kt) && (this.kx == paramh.kx) && (this.kw == paramh.kw);
  }

  public boolean dA()
  {
    return (this.kp > -1) && (this.kt > -1) && (this.kx > -1) && (this.kw > -1);
  }

  public String dB()
  {
    StringBuffer localStringBuffer = new StringBuffer(64);
    localStringBuffer.append(String.format(Locale.CHINA, "cell=%d|%d|%d|%d:%d", new Object[] { Integer.valueOf(this.kw), Integer.valueOf(this.kx), Integer.valueOf(this.kp), Integer.valueOf(this.kt), Integer.valueOf(this.ku) }));
    return localStringBuffer.toString();
  }

  public String dC()
  {
    StringBuffer localStringBuffer = new StringBuffer(128);
    localStringBuffer.append(this.kt + 23);
    localStringBuffer.append("H");
    localStringBuffer.append(this.kp + 45);
    localStringBuffer.append("K");
    localStringBuffer.append(this.kx + 54);
    localStringBuffer.append("Q");
    localStringBuffer.append(this.kw + 203);
    return localStringBuffer.toString();
  }

  public boolean dD()
  {
    return (this.kp > -1) && (this.kt > -1) && (this.kx == -1) && (this.kw == -1);
  }

  public void dt()
  {
    this.kq = true;
  }

  public boolean du()
  {
    return (this.kp > -1) && (this.kt > 0);
  }

  public int dv()
  {
    int j = 2;
    int i = j;
    if (this.kw > 0)
    {
      i = j;
      if (du())
      {
        if ((this.kw != 460) && (this.kw != 454) && (this.kw != 455) && (this.kw != 466))
          break label64;
        i = 1;
      }
    }
    return i;
    label64: return 0;
  }

  public boolean dw()
  {
    return System.currentTimeMillis() - this.kr < 3000L;
  }

  public boolean dx()
  {
    return (this.kp == -1) && (this.kt == -1) && (this.kx == -1) && (this.kw == -1);
  }

  public String dy()
  {
    if (du())
      return String.format(Locale.CHINA, "<cell-tower>\n<mcc>%d</mcc><mnc>%d</mnc><lac>%d</lac><ci>%d</ci><rssi>%d</rssi></cell-tower>", new Object[] { Integer.valueOf(this.kw), Integer.valueOf(this.kx), Integer.valueOf(this.kp), Integer.valueOf(this.kt), Integer.valueOf(this.ku) });
    return null;
  }

  public String dz()
  {
    StringBuffer localStringBuffer = new StringBuffer(128);
    localStringBuffer.append("&nw=");
    localStringBuffer.append(this.kv);
    localStringBuffer.append(String.format(Locale.CHINA, "&cl=%d|%d|%d|%d&cl_s=%d", new Object[] { Integer.valueOf(this.kw), Integer.valueOf(this.kx), Integer.valueOf(this.kp), Integer.valueOf(this.kt), Integer.valueOf(this.ku) }));
    if (this.kq)
      localStringBuffer.append("&newcl=1");
    return localStringBuffer.toString();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.h.h
 * JD-Core Version:    0.6.2
 */