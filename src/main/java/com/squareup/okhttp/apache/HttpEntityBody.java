package com.squareup.okhttp.apache;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import java.io.IOException;
import okio.BufferedSink;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

final class HttpEntityBody extends RequestBody
{
  private static final MediaType DEFAULT_MEDIA_TYPE = MediaType.parse("application/octet-stream");
  private final HttpEntity entity;
  private final MediaType mediaType;

  HttpEntityBody(HttpEntity paramHttpEntity, String paramString)
  {
    this.entity = paramHttpEntity;
    if (paramString != null)
    {
      this.mediaType = MediaType.parse(paramString);
      return;
    }
    if (paramHttpEntity.getContentType() != null)
    {
      this.mediaType = MediaType.parse(paramHttpEntity.getContentType().getValue());
      return;
    }
    this.mediaType = DEFAULT_MEDIA_TYPE;
  }

  public long contentLength()
  {
    return this.entity.getContentLength();
  }

  public MediaType contentType()
  {
    return this.mediaType;
  }

  public void writeTo(BufferedSink paramBufferedSink)
    throws IOException
  {
    this.entity.writeTo(paramBufferedSink.outputStream());
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.apache.HttpEntityBody
 * JD-Core Version:    0.6.2
 */