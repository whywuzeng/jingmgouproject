package com.nostra13.universalimageloader.cache.disc.impl.ext;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.Charset;

final class Util
{
  static final Charset US_ASCII = Charset.forName("US-ASCII");
  static final Charset UTF_8 = Charset.forName("UTF-8");

  static void closeQuietly(Closeable paramCloseable)
  {
    if (paramCloseable != null);
    try
    {
      paramCloseable.close();
      return;
    }
    catch (RuntimeException paramCloseable)
    {
      throw paramCloseable;
    }
    catch (Exception paramCloseable)
    {
    }
  }

  static void deleteContents(File paramFile)
    throws IOException
  {
    File[] arrayOfFile = paramFile.listFiles();
    if (arrayOfFile == null)
      throw new IOException("not a readable directory: " + paramFile);
    int j = arrayOfFile.length;
    int i = 0;
    while (i < j)
    {
      paramFile = arrayOfFile[i];
      if (paramFile.isDirectory())
        deleteContents(paramFile);
      if (!paramFile.delete())
        throw new IOException("failed to delete file: " + paramFile);
      i += 1;
    }
  }

  static String readFully(Reader paramReader)
    throws IOException
  {
    try
    {
      StringWriter localStringWriter = new StringWriter();
      char[] arrayOfChar = new char[1024];
      while (true)
      {
        int i = paramReader.read(arrayOfChar);
        if (i == -1)
          break;
        localStringWriter.write(arrayOfChar, 0, i);
      }
    }
    finally
    {
      paramReader.close();
    }
    String str = localObject.toString();
    paramReader.close();
    return str;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.cache.disc.impl.ext.Util
 * JD-Core Version:    0.6.2
 */