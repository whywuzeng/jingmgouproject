package org.apache.http.entity.mime.content;

public abstract interface ContentDescriptor
{
  public abstract String getCharset();

  public abstract long getContentLength();

  public abstract String getMediaType();

  public abstract String getMimeType();

  public abstract String getSubType();

  public abstract String getTransferEncoding();
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     org.apache.http.entity.mime.content.ContentDescriptor
 * JD-Core Version:    0.6.2
 */