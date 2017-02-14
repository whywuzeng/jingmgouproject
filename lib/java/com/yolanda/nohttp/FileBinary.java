package com.yolanda.nohttp;

import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileBinary
  implements Binary
{
  private File file;
  private String fileName;

  public FileBinary(File paramFile, String paramString)
  {
    if (paramFile == null)
      throw new IllegalArgumentException("file == null");
    if (!paramFile.isFile())
      throw new IllegalArgumentException("file isn't file");
    this.file = paramFile;
    this.fileName = paramString;
    if (TextUtils.isEmpty(paramString))
      this.fileName = paramFile.getName();
  }

  public byte[] getByteArray()
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      FileInputStream localFileInputStream = new FileInputStream(this.file);
      byte[] arrayOfByte = new byte[1024];
      while (true)
      {
        int i = localFileInputStream.read(arrayOfByte);
        if (i == -1)
        {
          localByteArrayOutputStream.flush();
          localByteArrayOutputStream.close();
          localFileInputStream.close();
          return localByteArrayOutputStream.toByteArray();
        }
        localByteArrayOutputStream.write(arrayOfByte, 0, i);
      }
    }
    catch (IOException localIOException)
    {
      while (true)
        Logger.wtf(localIOException);
    }
  }

  public String getCharset()
  {
    return "UTF-8";
  }

  public String getFileName()
  {
    return this.fileName;
  }

  public String getMimeType()
  {
    return "application/octet-stream";
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.yolanda.nohttp.FileBinary
 * JD-Core Version:    0.6.2
 */