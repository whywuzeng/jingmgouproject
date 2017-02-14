package com.mob.tools.network;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class FilePart extends HTTPPart
{
  private File file;

  protected InputStream getInputStream()
    throws Throwable
  {
    return new FileInputStream(this.file);
  }

  protected long length()
    throws Throwable
  {
    return this.file.length();
  }

  public void setFile(File paramFile)
  {
    this.file = paramFile;
  }

  public void setFile(String paramString)
  {
    this.file = new File(paramString);
  }

  public String toString()
  {
    return this.file.toString();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.network.FilePart
 * JD-Core Version:    0.6.2
 */