package com.ab.http;

import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;

public class AbMultipartEntity
  implements HttpEntity
{
  private static final String APPLICATION_OCTET_STREAM = "application/octet-stream";
  private static final byte[] CR_LF = "\r\n".getBytes();
  private static final char[] MULTIPART_CHARS = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
  private static final String TAG = "AbMultipartEntity";
  private static final byte[] TRANSFER_ENCODING_BINARY = "Content-Transfer-Encoding: binary\r\n".getBytes();
  private String boundary;
  private byte[] boundaryEnd;
  private byte[] boundaryLine;
  private int bytesWritten;
  private List<FilePart> fileParts = new ArrayList();
  private ByteArrayOutputStream out = new ByteArrayOutputStream();
  private AbHttpResponseListener responseListener;
  private int totalSize;

  public AbMultipartEntity(AbHttpResponseListener paramAbHttpResponseListener)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Random localRandom = new Random();
    int i = 0;
    while (true)
    {
      if (i >= 30)
      {
        this.boundary = localStringBuilder.toString();
        this.boundaryLine = ("--" + this.boundary + "\r\n").getBytes();
        this.boundaryEnd = ("--" + this.boundary + "--\r\n").getBytes();
        this.responseListener = paramAbHttpResponseListener;
        return;
      }
      localStringBuilder.append(MULTIPART_CHARS[localRandom.nextInt(MULTIPART_CHARS.length)]);
      i += 1;
    }
  }

  private byte[] createContentDisposition(String paramString)
  {
    return ("Content-Disposition: form-data; name=\"" + paramString + "\"\r\n").getBytes();
  }

  private byte[] createContentDisposition(String paramString1, String paramString2)
  {
    return ("Content-Disposition: form-data; name=\"" + paramString1 + "\"; filename=\"" + paramString2 + "\"\r\n").getBytes();
  }

  private byte[] createContentType(String paramString)
  {
    return ("Content-Type: " + paramString + "\r\n").getBytes();
  }

  private void updateProgress(int paramInt)
  {
    this.bytesWritten += paramInt;
    this.responseListener.sendProgressMessage(this.bytesWritten, this.totalSize);
  }

  public void addPart(String paramString, File paramFile)
  {
    addPart(paramString, paramFile, null);
  }

  public void addPart(String paramString1, File paramFile, String paramString2)
  {
    String str = paramString2;
    if (paramString2 == null)
      str = "application/octet-stream";
    this.fileParts.add(new FilePart(paramString1, paramFile, str));
  }

  public void addPart(String paramString1, String paramString2)
  {
    addPart(paramString1, paramString2, "text/plain; charset=UTF-8");
  }

  public void addPart(String paramString1, String paramString2, InputStream paramInputStream, String paramString3)
    throws IOException
  {
    String str = paramString3;
    if (paramString3 == null)
      str = "application/octet-stream";
    this.out.write(this.boundaryLine);
    this.out.write(createContentDisposition(paramString1, paramString2));
    this.out.write(createContentType(str));
    this.out.write(TRANSFER_ENCODING_BINARY);
    this.out.write(CR_LF);
    paramString1 = new byte[4096];
    while (true)
    {
      int i = paramInputStream.read(paramString1);
      if (i == -1)
      {
        this.out.write(CR_LF);
        this.out.flush();
      }
      try
      {
        paramInputStream.close();
        return;
        this.out.write(paramString1, 0, i);
      }
      catch (IOException paramString1)
      {
        Log.w("AbMultipartEntity", "Cannot close input stream", paramString1);
      }
    }
  }

  public void addPart(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      this.out.write(this.boundaryLine);
      this.out.write(createContentDisposition(paramString1));
      this.out.write(createContentType(paramString3));
      this.out.write(CR_LF);
      this.out.write(paramString2.getBytes());
      this.out.write(CR_LF);
      return;
    }
    catch (IOException paramString1)
    {
      Log.e("AbMultipartEntity", "addPart ByteArrayOutputStream exception", paramString1);
    }
  }

  public void consumeContent()
    throws IOException, UnsupportedOperationException
  {
    if (isStreaming())
      throw new UnsupportedOperationException("Streaming entity does not implement #consumeContent()");
  }

  public InputStream getContent()
    throws IOException, UnsupportedOperationException
  {
    throw new UnsupportedOperationException("getContent() is not supported. Use writeTo() instead.");
  }

  public Header getContentEncoding()
  {
    return null;
  }

  public long getContentLength()
  {
    long l1 = this.out.size();
    Iterator localIterator = this.fileParts.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return l1 + this.boundaryEnd.length;
      long l2 = ((FilePart)localIterator.next()).getTotalLength();
      if (l2 < 0L)
        return -1L;
      l1 += l2;
    }
  }

  public Header getContentType()
  {
    return new BasicHeader("Content-Type", "multipart/form-data; boundary=" + this.boundary);
  }

  public boolean isChunked()
  {
    return false;
  }

  public boolean isRepeatable()
  {
    return false;
  }

  public boolean isStreaming()
  {
    return false;
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    this.bytesWritten = 0;
    this.totalSize = ((int)getContentLength());
    this.out.writeTo(paramOutputStream);
    updateProgress(this.out.size());
    Iterator localIterator = this.fileParts.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        paramOutputStream.write(this.boundaryEnd);
        updateProgress(this.boundaryEnd.length);
        return;
      }
      ((FilePart)localIterator.next()).writeTo(paramOutputStream);
    }
  }

  private class FilePart
  {
    public File file;
    public byte[] header;

    public FilePart(String paramFile, File paramString1, String arg4)
    {
      String str;
      this.header = createHeader(paramFile, paramString1.getName(), str);
      this.file = paramString1;
    }

    private byte[] createHeader(String paramString1, String paramString2, String paramString3)
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      try
      {
        localByteArrayOutputStream.write(AbMultipartEntity.this.boundaryLine);
        localByteArrayOutputStream.write(AbMultipartEntity.this.createContentDisposition(paramString1, paramString2));
        localByteArrayOutputStream.write(AbMultipartEntity.this.createContentType(paramString3));
        localByteArrayOutputStream.write(AbMultipartEntity.TRANSFER_ENCODING_BINARY);
        localByteArrayOutputStream.write(AbMultipartEntity.CR_LF);
        return localByteArrayOutputStream.toByteArray();
      }
      catch (IOException paramString1)
      {
        while (true)
          Log.e("AbMultipartEntity", "createHeader ByteArrayOutputStream exception", paramString1);
      }
    }

    public long getTotalLength()
    {
      long l = this.file.length();
      return this.header.length + l;
    }

    public void writeTo(OutputStream paramOutputStream)
      throws IOException
    {
      paramOutputStream.write(this.header);
      AbMultipartEntity.this.updateProgress(this.header.length);
      FileInputStream localFileInputStream = new FileInputStream(this.file);
      byte[] arrayOfByte = new byte[4096];
      while (true)
      {
        int i = localFileInputStream.read(arrayOfByte);
        if (i == -1)
        {
          paramOutputStream.write(AbMultipartEntity.CR_LF);
          AbMultipartEntity.this.updateProgress(AbMultipartEntity.CR_LF.length);
          paramOutputStream.flush();
        }
        try
        {
          localFileInputStream.close();
          return;
          paramOutputStream.write(arrayOfByte, 0, i);
          AbMultipartEntity.this.updateProgress(i);
        }
        catch (IOException paramOutputStream)
        {
          Log.w("AbMultipartEntity", "Cannot close input stream", paramOutputStream);
        }
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.http.AbMultipartEntity
 * JD-Core Version:    0.6.2
 */