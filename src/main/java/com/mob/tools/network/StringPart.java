package com.mob.tools.network;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class StringPart extends HTTPPart
{
  private StringBuilder sb = new StringBuilder();

  public StringPart append(String paramString)
  {
    this.sb.append(paramString);
    return this;
  }

  protected InputStream getInputStream()
    throws Throwable
  {
    return new ByteArrayInputStream(this.sb.toString().getBytes("utf-8"));
  }

  protected long length()
    throws Throwable
  {
    return this.sb.toString().getBytes("utf-8").length;
  }

  public String toString()
  {
    return this.sb.toString();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.network.StringPart
 * JD-Core Version:    0.6.2
 */