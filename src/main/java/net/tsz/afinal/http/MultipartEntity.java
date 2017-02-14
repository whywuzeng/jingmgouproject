package net.tsz.afinal.http;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;

class MultipartEntity
  implements HttpEntity
{
  private static final char[] MULTIPART_CHARS = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
  private String boundary = null;
  boolean isSetFirst = false;
  boolean isSetLast = false;
  ByteArrayOutputStream out = new ByteArrayOutputStream();

  public MultipartEntity()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Random localRandom = new Random();
    int i = 0;
    while (true)
    {
      if (i >= 30)
      {
        this.boundary = localStringBuffer.toString();
        return;
      }
      localStringBuffer.append(MULTIPART_CHARS[localRandom.nextInt(MULTIPART_CHARS.length)]);
      i += 1;
    }
  }

  public void addPart(String paramString, File paramFile, boolean paramBoolean)
  {
    try
    {
      addPart(paramString, paramFile.getName(), new FileInputStream(paramFile), paramBoolean);
      return;
    }
    catch (FileNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
  }

  public void addPart(String paramString1, String paramString2)
  {
    writeFirstBoundaryIfNeeds();
    try
    {
      this.out.write(("Content-Disposition: form-data; name=\"" + paramString1 + "\"\r\n\r\n").getBytes());
      this.out.write(paramString2.getBytes());
      this.out.write(("\r\n--" + this.boundary + "\r\n").getBytes());
      return;
    }
    catch (IOException paramString1)
    {
      paramString1.printStackTrace();
    }
  }

  // ERROR //
  public void addPart(String paramString1, String paramString2, InputStream paramInputStream, String paramString3, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 86	net/tsz/afinal/http/MultipartEntity:writeFirstBoundaryIfNeeds	()V
    //   4: new 88	java/lang/StringBuilder
    //   7: dup
    //   8: ldc 115
    //   10: invokespecial 93	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   13: aload 4
    //   15: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   18: ldc 111
    //   20: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   26: astore 4
    //   28: aload_0
    //   29: getfield 38	net/tsz/afinal/http/MultipartEntity:out	Ljava/io/ByteArrayOutputStream;
    //   32: new 88	java/lang/StringBuilder
    //   35: dup
    //   36: ldc 90
    //   38: invokespecial 93	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   41: aload_1
    //   42: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: ldc 117
    //   47: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: aload_2
    //   51: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: ldc 119
    //   56: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   62: invokevirtual 103	java/lang/String:getBytes	()[B
    //   65: invokevirtual 107	java/io/ByteArrayOutputStream:write	([B)V
    //   68: aload_0
    //   69: getfield 38	net/tsz/afinal/http/MultipartEntity:out	Ljava/io/ByteArrayOutputStream;
    //   72: aload 4
    //   74: invokevirtual 103	java/lang/String:getBytes	()[B
    //   77: invokevirtual 107	java/io/ByteArrayOutputStream:write	([B)V
    //   80: aload_0
    //   81: getfield 38	net/tsz/afinal/http/MultipartEntity:out	Ljava/io/ByteArrayOutputStream;
    //   84: ldc 121
    //   86: invokevirtual 103	java/lang/String:getBytes	()[B
    //   89: invokevirtual 107	java/io/ByteArrayOutputStream:write	([B)V
    //   92: sipush 4096
    //   95: newarray byte
    //   97: astore_1
    //   98: aload_3
    //   99: aload_1
    //   100: invokevirtual 127	java/io/InputStream:read	([B)I
    //   103: istore 6
    //   105: iload 6
    //   107: iconst_m1
    //   108: if_icmpne +54 -> 162
    //   111: iload 5
    //   113: ifne +37 -> 150
    //   116: aload_0
    //   117: getfield 38	net/tsz/afinal/http/MultipartEntity:out	Ljava/io/ByteArrayOutputStream;
    //   120: new 88	java/lang/StringBuilder
    //   123: dup
    //   124: ldc 109
    //   126: invokespecial 93	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   129: aload_0
    //   130: getfield 33	net/tsz/afinal/http/MultipartEntity:boundary	Ljava/lang/String;
    //   133: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   136: ldc 111
    //   138: invokevirtual 96	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   141: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   144: invokevirtual 103	java/lang/String:getBytes	()[B
    //   147: invokevirtual 107	java/io/ByteArrayOutputStream:write	([B)V
    //   150: aload_0
    //   151: getfield 38	net/tsz/afinal/http/MultipartEntity:out	Ljava/io/ByteArrayOutputStream;
    //   154: invokevirtual 130	java/io/ByteArrayOutputStream:flush	()V
    //   157: aload_3
    //   158: invokevirtual 133	java/io/InputStream:close	()V
    //   161: return
    //   162: aload_0
    //   163: getfield 38	net/tsz/afinal/http/MultipartEntity:out	Ljava/io/ByteArrayOutputStream;
    //   166: aload_1
    //   167: iconst_0
    //   168: iload 6
    //   170: invokevirtual 136	java/io/ByteArrayOutputStream:write	([BII)V
    //   173: goto -75 -> 98
    //   176: astore_1
    //   177: aload_1
    //   178: invokevirtual 112	java/io/IOException:printStackTrace	()V
    //   181: aload_3
    //   182: invokevirtual 133	java/io/InputStream:close	()V
    //   185: return
    //   186: astore_1
    //   187: aload_1
    //   188: invokevirtual 112	java/io/IOException:printStackTrace	()V
    //   191: return
    //   192: astore_1
    //   193: aload_3
    //   194: invokevirtual 133	java/io/InputStream:close	()V
    //   197: aload_1
    //   198: athrow
    //   199: astore_2
    //   200: aload_2
    //   201: invokevirtual 112	java/io/IOException:printStackTrace	()V
    //   204: goto -7 -> 197
    //   207: astore_1
    //   208: aload_1
    //   209: invokevirtual 112	java/io/IOException:printStackTrace	()V
    //   212: return
    //
    // Exception table:
    //   from	to	target	type
    //   4	98	176	java/io/IOException
    //   98	105	176	java/io/IOException
    //   116	150	176	java/io/IOException
    //   150	157	176	java/io/IOException
    //   162	173	176	java/io/IOException
    //   181	185	186	java/io/IOException
    //   4	98	192	finally
    //   98	105	192	finally
    //   116	150	192	finally
    //   150	157	192	finally
    //   162	173	192	finally
    //   177	181	192	finally
    //   193	197	199	java/io/IOException
    //   157	161	207	java/io/IOException
  }

  public void addPart(String paramString1, String paramString2, InputStream paramInputStream, boolean paramBoolean)
  {
    addPart(paramString1, paramString2, paramInputStream, "application/octet-stream", paramBoolean);
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
    return new ByteArrayInputStream(this.out.toByteArray());
  }

  public Header getContentEncoding()
  {
    return null;
  }

  public long getContentLength()
  {
    writeLastBoundaryIfNeeds();
    return this.out.toByteArray().length;
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

  public void writeFirstBoundaryIfNeeds()
  {
    if (!this.isSetFirst);
    try
    {
      this.out.write(("--" + this.boundary + "\r\n").getBytes());
      this.isSetFirst = true;
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
        localIOException.printStackTrace();
    }
  }

  public void writeLastBoundaryIfNeeds()
  {
    if (this.isSetLast)
      return;
    try
    {
      this.out.write(("\r\n--" + this.boundary + "--\r\n").getBytes());
      this.isSetLast = true;
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
        localIOException.printStackTrace();
    }
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    paramOutputStream.write(this.out.toByteArray());
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     net.tsz.afinal.http.MultipartEntity
 * JD-Core Version:    0.6.2
 */