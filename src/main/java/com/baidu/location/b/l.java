package com.baidu.location.b;

import com.baidu.location.h.b;
import com.baidu.location.h.e;
import java.util.Locale;

public class l
  implements f
{
  public static final String c0 = "skys";
  public static final String c2 = "ons";
  public static final String cV = "onf";
  public static final String cW = "off";
  public static final String cX = "skyf";
  public static final String cZ = "ofs";
  private String c1 = null;
  private long c3 = 0L;
  private int c4 = 0;
  private long cR = 0L;
  private String cS = null;
  private long cT = 0L;
  private String cU = null;
  private long cY = 0L;

  public void aj()
  {
    this.cT = 0L;
    this.c3 = 0L;
    this.cR = 0L;
    this.cY = 0L;
    this.c4 = 0;
    this.c1 = null;
    this.cU = null;
    this.cS = null;
  }

  public String ak()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (e.dg().db());
    for (this.c1 = "&cn=32"; ; this.c1 = String.format(Locale.CHINA, "&cn=%d", new Object[] { Integer.valueOf(b.cW().cR()) }))
    {
      localStringBuffer.append(this.c1);
      localStringBuffer.append(String.format(Locale.CHINA, "&fir=%d&tim=%d&dsc=%d&det=%d&ded=%d&typ=%s", new Object[] { Integer.valueOf(this.c4), Long.valueOf(this.cT), Long.valueOf(this.c3 - this.cT), Long.valueOf(this.cR - this.c3), Long.valueOf(this.cY - this.cR), this.cU }));
      if (this.cS != null)
        localStringBuffer.append(this.cS);
      localStringBuffer.append(c.N().jdMethod_do(false));
      localStringBuffer.append(com.baidu.location.e.c.br().bu());
      return localStringBuffer.toString();
    }
  }

  public void jdMethod_char(String paramString)
  {
    this.cU = paramString;
  }

  public void jdMethod_do(long paramLong)
  {
    this.cT = paramLong;
  }

  public void jdMethod_else(String paramString)
  {
    if (this.cS == null)
    {
      this.cS = paramString;
      return;
    }
    this.cS = String.format("%s%s", new Object[] { this.cS, paramString });
  }

  public void jdMethod_for(long paramLong)
  {
    this.cR = paramLong;
  }

  public void jdMethod_for(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.c4 = 1;
      return;
    }
    this.c4 = 0;
  }

  public void jdMethod_if(long paramLong)
  {
    this.c3 = paramLong;
  }

  public void jdMethod_int(long paramLong)
  {
    this.cY = paramLong;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.baidu.location.b.l
 * JD-Core Version:    0.6.2
 */