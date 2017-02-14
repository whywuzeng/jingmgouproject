package com.squareup.okhttp;

import okio.Buffer;

public final class FormEncodingBuilder
{
  private static final MediaType CONTENT_TYPE = MediaType.parse("application/x-www-form-urlencoded");
  private final Buffer content = new Buffer();

  public FormEncodingBuilder add(String paramString1, String paramString2)
  {
    if (this.content.size() > 0L)
      this.content.writeByte(38);
    HttpUrl.canonicalize(this.content, paramString1, 0, paramString1.length(), " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, true);
    this.content.writeByte(61);
    HttpUrl.canonicalize(this.content, paramString2, 0, paramString2.length(), " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, true);
    return this;
  }

  public FormEncodingBuilder addEncoded(String paramString1, String paramString2)
  {
    if (this.content.size() > 0L)
      this.content.writeByte(38);
    HttpUrl.canonicalize(this.content, paramString1, 0, paramString1.length(), " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, true);
    this.content.writeByte(61);
    HttpUrl.canonicalize(this.content, paramString2, 0, paramString2.length(), " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, true);
    return this;
  }

  public RequestBody build()
  {
    return RequestBody.create(CONTENT_TYPE, this.content.snapshot());
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.FormEncodingBuilder
 * JD-Core Version:    0.6.2
 */